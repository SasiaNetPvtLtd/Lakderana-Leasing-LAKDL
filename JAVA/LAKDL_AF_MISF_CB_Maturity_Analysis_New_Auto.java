                   


// Addedby ishani jayasundara 2013.08.08
				import java.io.*; 
				import javax.servlet.*;   
				import javax.servlet.http.*; 
				import java.sql.*; 
				import java.util.*; 
				
				public class LAKDL_AF_MISF_CB_Maturity_Analysis_New_Auto extends javax.servlet.http.HttpServlet { 
				
				ServletOutputStream out = null;
				Connection conn;
				Statement stmt,stmt1,stmt2,stmt3;
				java.text.NumberFormat nf,nf1;
				
				
				public String m_chksql;
				
				public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
				
				try { 
					
				 ResultSet rs,rs1,rs2,rs3;	
				    
					rs=null;
					rs1=null;
					rs2=null;
					rs3=null;	
					
					
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
				out = res.getOutputStream(); 
				conn = m_sn_methods.met_user_validate(req); 
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_schema_name = m_sn_methods.schema_name;
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_header_name=m_sn_methods.header_name.trim();
				String m_username = m_sn_methods.username;
				nf = java.text.NumberFormat.getInstance(Locale.US);
				nf.setMinimumFractionDigits(2);
				nf.setMaximumFractionDigits(2);      
				res.setStatus(HttpServletResponse.SC_OK); 
				res.setContentType("text/html"); 
				m_chksql=req.getParameter("chksql");
				stmt=conn.createStatement();
				stmt1=conn.createStatement();
				stmt2=conn.createStatement(); 
				CallableStatement callstmt1 =null;			
				
			
							
			
				
				if(m_chksql.equals("load_details")){
					
				String m_string="";				
				String m_sql="";	
				
				String m_to_date=req.getParameter("m_to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_branch = req.getParameter("m_branch");
				
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				String m_branch_sql = ""; // added by udara 27-07-2017
				
				if(m_branch!=null){
				
					if(!m_branch.equals("")){
						m_branch_sql = " AND A.BRANCH_CODE = '"+m_branch+"' ";
					}
					else{
						m_branch_sql = "  ";
					}
				
				}
				else{
					m_branch_sql = "  ";
				}
				
			
				// added by udara 09-03-2018
				if(conn==null){
						conn = m_sn_methods.met_user_validate(req); 
						stmt1  = conn.createStatement ();
				}
				// end by udara 09-03-2018
							
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(ADD_MONTHS(LAST_DAY(TRUNC(SYSDATE)),-1),'DD')||' '|| "+
				" INITCAP(TO_CHAR(ADD_MONTHS(LAST_DAY(TRUNC(SYSDATE)),-1),'MONTH'))||' '|| "+
				" TO_CHAR(ADD_MONTHS(LAST_DAY(TRUNC(SYSDATE)),-1),'YYYY'), "+
				" TO_CHAR(ADD_MONTHS(LAST_DAY(TRUNC(SYSDATE)),-1),'DD-MM-YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 				
				m_to_date =rs1.getString(3);	 
					
				
    		out.println("<HTML><HEAD><TITLE>Maturity Analysis Month End</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
					
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				
				out.println("<br>");
				out.println("<br>");
				out.println("<table class='table' align='center' border='0' width='1600'>");
				out.println("<tr >");
				out.println("<td align='left' width='*%' ><B>"+rs1.getString(1).toUpperCase()+"</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='*%' ><B>PRODUCT - HIRE PURCHASE	</B></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' ><B>REPORT - MATURITY ANALYSIS AS AT "+m_to_date+" </td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				
						
				out.println("<table width='2562'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr BGCOLOR=#a5b6c6 >");
				out.println("<td width='12' rowspan='3' align='left'><b>No.</b></td>");//0
				out.println("<td width='200' rowspan='3' align='left'><b>Name of the Borrower</b></td>");//1
				out.println("<td width='100' rowspan='3' align='left'><b>Contact no</b></td>");//2
				
				out.println("<td width='100' rowspan='3' align='left'><b>Application Status</b></td>");////added by jithendra 26-06-2018
				out.println("<td width='100' rowspan='3' align='left'><b>IRR</b></td>");////added by jithendra 26-06-2018
				out.println("<td width='100' rowspan='3' align='left'><b>Rental</b></td>");//added by jithendra 26-06-2018
				
				out.println("<td width='100' rowspan='3' align='left'><b>Date Granted</b></td>");//2
				out.println("<td width='100' rowspan='3' align='left'><b>Date Settlement</b></td>");//2
				
				out.println("<td width='100' rowspan='3' align='right'><b>Total O/S</b></td>");//3
				out.println("<td width='100' rowspan='3' align='right'><b>Capital</b></td>");//4
				out.println("<td width='100' rowspan='3' align='right'><b>Interest</b></td>");//5
				out.println("<td width='2550' colspan='51' align='center'><b>ANALYSIS OF FUTURE CASH OUTFLOWS ENHANCED</b></td>");
				out.println("</tr>");
				out.println("<tr BGCOLOR=#a5b6c6 height=10>");
				out.println("<td width='150' colspan='3' align='center'><b>1 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>2 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>3 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>4 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>5 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>6 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>7 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>8 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>9 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>10 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>11 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>12 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 12-24 Months &nbsp;&nbsp;&nbsp; ( 1-2  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 24-36 Months &nbsp;&nbsp;&nbsp; ( 2-3  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 36-48 Months &nbsp;&nbsp;&nbsp; ( 3-4  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 48-60 Months &nbsp;&nbsp;&nbsp; ( 3-5  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>Above 60 Months(more than 5 years)</b></td>");
				out.println("</tr>");
				out.println("<tr BGCOLOR=#a5b6c6 height=10>");
				// 1 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//7
				out.println("<td width='50' align='right'><b>Interest</b></td>");//8
				out.println("<td width='50' align='right'><b>Total</b></td>");//10
				// 2 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//11
				out.println("<td width='50' align='right'><b>Interest</b></td>");//12
				out.println("<td width='50' align='right'><b>Total</b></td>");//14
				// 3 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//15
				out.println("<td width='50' align='right'><b>Interest</b></td>");//16
				out.println("<td width='50' align='right'><b>Total</b></td>");//18
				// 4 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//19
				out.println("<td width='50' align='right'><b>Interest</b></td>");//20
				out.println("<td width='50' align='right'><b>Total</b></td>");//22
				// 5 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//23
				out.println("<td width='50' align='right'><b>Interest</b></td>");//24
				out.println("<td width='50' align='right'><b>Total</b></td>");//26
				// 6 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//27
				out.println("<td width='50' align='right'><b>Interest</b></td>");//28
				out.println("<td width='50' align='right'><b>Total</b></td>");//30
				// 7 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//31
				out.println("<td width='50' align='right'><b>Interest</b></td>");//32
				out.println("<td width='50' align='right'><b>Total</b></td>");//34
				// 8 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//35
				out.println("<td width='50' align='right'><b>Interest</b></td>");//36
				out.println("<td width='50' align='right'><b>Total</b></td>");//38
				// 9 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//39
				out.println("<td width='50' align='right'><b>Interest</b></td>");//40
				out.println("<td width='50' align='right'><b>Total</b></td>");//42
				// 10 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//43
				out.println("<td width='50' align='right'><b>Interest</b></td>");//44
				out.println("<td width='50' align='right'><b>Total</b></td>");//46
				// 11 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//47
				out.println("<td width='50' align='right'><b>Interest</b></td>");//48
				out.println("<td width='50' align='right'><b>Total</b></td>");//50
				// 12 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 12-24 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 24-36 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 36-48 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 48-60 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// abov 60 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				
				out.println("</tr>");
				
				// added by udara 23-09-2016
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt  = conn.createStatement ();
					stmt1  = conn.createStatement ();
					stmt2  = conn.createStatement ();
				}
				
								String m_sql_query = "";
								
							
								if(conn==null){
										conn = m_sn_methods.met_user_validate(req); 
										stmt  = conn.createStatement ();
								}
								
								
								
								
								m_sql_query =  m_sql_query + " SELECT DISTINCT A.FINANCE_NO, "+//1
								        " A.APPLICATION_NO, "+//2
										" A.CLIENT_CODE,  "+//3
										" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+//4
										" TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY') , "+ //5
										" TO_CHAR(LAST_PAYMNT_DATE,'DD-MM-YYYY') ,  "+ //6
										" NVL(ARREARS_AMOUNT,0) -NVL(INS_SUSPENSE,0) "+ //7
										" ,IRR,RENTAL_AMOUNT,APPLICATION_STATUS "+ //7//ADDED BY JITHENDRA 26-06-2018
										" FROM "+m_schema_name+".AF_TBD_HIS_MATURITY A "+
										//" WHERE  A.ENT_USER = '"+m_username+"' "+
										" WHERE  STATUS IN ('1','2') "+
										" AND REPORT_DATE=TO_DATE('"+m_to_date+"','DD-MM-YYYY')";
										m_sql_query = m_sql_query + m_branch_sql; 
										m_sql_query = m_sql_query + " ORDER BY FINANCE_NO  ";
								
									
										
								rs = stmt.executeQuery(m_sql_query);
								// end by udara 27-07-2017
	
	
	
	
				
				boolean more = rs.next();
				int j=0;
				String m_app_no="",m_finance_no ="";
				String m_activate_date="", m_last_date="";
				
				double m_cap_0_7=0,m_int_0_7=0,m_oth_0_7;
				double m_cap_1_2=0,m_int_1_2=0;
				double m_cap_2_3=0,m_int_2_3=0;
				double m_cap_3_4=0,m_int_3_4=0;
				double m_cap_4_5=0,m_int_4_5=0;
				double m_cap_5_6=0,m_int_5_6=0;
				double m_cap_6_7=0,m_int_6_7=0;
				double m_cap_7_8=0,m_int_7_8=0;
				double m_cap_8_9=0,m_int_8_9=0;
				double m_cap_9_10=0,m_int_9_10=0;
				double m_cap_10_11=0,m_int_10_11=0;
				double m_cap_11_12=0,m_int_11_12=0;
				double m_cap_12_24=0,m_int_12_24=0;
				double m_cap_24_36=0,m_int_24_36=0;
				double m_cap_36_48=0,m_int_36_48=0;
				double m_cap_48_60=0,m_int_48_60=0;
				double m_cap_60=0,m_int_60=0;
				double m_cap_tot=0,m_int_tot=0;
			    

				double m_sub_cap_tot = 0;
				double m_sub_int_tot = 0;
				double m_sub_oth_0_7 = 0;
				
				// 1 Month
				double m_sub_cap_0_7=0;
				double m_sub_int_0_7=0;
				// 2 Month
				double m_sub_cap_1_2=0;
				double m_sub_int_1_2=0;
				// 3 Month
				double m_sub_cap_2_3=0;
				double m_sub_int_2_3=0;
				// 4 Month
				double m_sub_cap_3_4=0;
				double m_sub_int_3_4=0;
                // 5 Month
				double m_sub_cap_4_5=0;
				double m_sub_int_4_5=0;
				// 6 Month
				double m_sub_cap_5_6=0;
				double m_sub_int_5_6=0;
				// 7 Month
				double m_sub_cap_6_7=0;
				double m_sub_int_6_7=0;
				// 8 Month
				double m_sub_cap_7_8=0;
				double m_sub_int_7_8=0;
				// 9 Month
				double m_sub_cap_8_9=0;
				double m_sub_int_8_9=0;
				// 10 Month
				double m_sub_cap_9_10=0;
				double m_sub_int_9_10=0;
				// 11 Month
				double m_sub_cap_10_11=0;
				double m_sub_int_10_11=0;
				// 12 Month
				double m_sub_cap_11_12=0;
				double m_sub_int_11_12=0;
				// 12-24 Month
				double m_sub_cap_12_24=0;
				double m_sub_int_12_24=0;
				// 24-36 Month
				double m_sub_cap_24_36=0;
				double m_sub_int_24_36=0;
				// 36-48 Month
				double m_sub_cap_36_48=0;
				double m_sub_int_36_48=0;
				// 48-60 Month
				double m_sub_cap_48_60=0;
				double m_sub_int_48_60=0;
				// over 60 Month
				double m_sub_cap_60=0;
				double m_sub_int_60=0;
				
				double m_rent_tot=0;//added by jithendra 28-06-2018
			while(more){	
				j++;
				
				m_app_no = rs.getString(2);
				m_finance_no = rs.getString(1);
				
				m_activate_date = rs.getString(5);
				m_last_date     = rs.getString(6);
				
				
				m_oth_0_7 = rs.getDouble(7);	
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_tot = rs2.getDouble(1);
				m_int_tot = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				//// 1 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),0) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),1) ");
																// " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_0_7 = rs2.getDouble(1);
				m_int_0_7 = rs2.getDouble(2);
				}
				
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 2 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),1) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),2) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_1_2 = rs2.getDouble(1);
				m_int_1_2 = rs2.getDouble(2);
				}
				
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 3 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),2) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),3) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_2_3 = rs2.getDouble(1);
				m_int_2_3 = rs2.getDouble(2);
				}
				
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 4 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),3) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),4) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_3_4 = rs2.getDouble(1);
				m_int_3_4 = rs2.getDouble(2);
				}
				
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 5 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),4) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),5) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_4_5 = rs2.getDouble(1);
				m_int_4_5 = rs2.getDouble(2);
				}
				
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// 6 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),5) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),6) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_5_6 = rs2.getDouble(1);
				m_int_5_6 = rs2.getDouble(2);
				}
				
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// 7 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),6) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),7) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_6_7 = rs2.getDouble(1);
				m_int_6_7 = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// 8 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),7) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),8) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_7_8 = rs2.getDouble(1);
				m_int_7_8 = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// 9 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),8) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),9) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_8_9 = rs2.getDouble(1);
				m_int_8_9 = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// 10 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),9) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),10) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_9_10 = rs2.getDouble(1);
				m_int_9_10 = rs2.getDouble(2);
				}
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// 11 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),10) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),11) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_10_11 = rs2.getDouble(1);
				m_int_10_11 = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// 12 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),11) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),12) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_11_12 = rs2.getDouble(1);
				m_int_11_12 = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 12-24 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),12) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),24) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_12_24 = rs2.getDouble(1);
				m_int_12_24 = rs2.getDouble(2);
				}
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 24-36 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),24) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),36) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_24_36 = rs2.getDouble(1);
				m_int_24_36 = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 36-48 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),36) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),48) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_36_48 = rs2.getDouble(1);
				m_int_36_48 = rs2.getDouble(2);
				}
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				// 48-60 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),48) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),60) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_48_60 = rs2.getDouble(1);
				m_int_48_60 = rs2.getDouble(2);
				}
				
				
				// added by udara 09-03-2018
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt2  = conn.createStatement ();
				}
				// end by udara 09-03-2018
				
				
				// over 60 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_HIS_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),60) ");
																 //" AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_60 = rs2.getDouble(1);
				m_int_60 = rs2.getDouble(2);
				}
								
			
				
				out.println("<tr height=30>");
				out.println("<td width='12' align='left'>"+j+"</td>");//0
				out.println("<td width='200' align='left'>"+rs.getString(4)+"</td>");//1
				out.println("<td width='100' align='left'>"+rs.getString(1)+"</td>");//2
				
				out.println("<td width='100' align='left'>"+rs.getString("APPLICATION_STATUS")+"</td>");////added by jithendra 26-06-2018

				out.println("<td width='100' align='right'>"+rs.getString("IRR")+"</td>");////added by jithendra 26-06-2018
				out.println("<td width='100' align='right'>"+nf.format(rs.getDouble("RENTAL_AMOUNT"))+"</td>");////added by jithendra 26-06-2018
				
				m_rent_tot=m_rent_tot+rs.getDouble("RENTAL_AMOUNT");//added by jithendra 26-06-2018
				
				out.println("<td width='100' align='left'>"+m_activate_date+"</td>");//2
				out.println("<td width='100' align='left'>"+m_last_date+"</td>");//2
				
				
				out.println("<td width='100' align='right'>"+nf.format(m_cap_tot+m_int_tot+m_oth_0_7)+"</td>");//3
				out.println("<td width='100' align='right'>"+nf.format(m_cap_tot)+"</td>");//4
				out.println("<td width='100' align='right'>"+nf.format(m_int_tot)+"</td>");//5
				//out.println("<td width='100' align='right'>"+nf.format(m_oth_0_7)+"</td>");//6
				//1
				out.println("<td width='100' align='right'>"+nf.format(m_cap_0_7)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_0_7)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_0_7+m_int_0_7)+"</td>");//10

				//2
				out.println("<td width='100' align='right'>"+nf.format(m_cap_1_2)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_1_2)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_1_2+m_int_1_2)+"</td>");//10
				//3
				out.println("<td width='100' align='right'>"+nf.format(m_cap_2_3)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_2_3)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_2_3+m_int_2_3)+"</td>");//10
				//4
				out.println("<td width='100' align='right'>"+nf.format(m_cap_3_4)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_3_4)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_3_4+m_int_3_4)+"</td>");//10
				//5
				out.println("<td width='100' align='right'>"+nf.format(m_cap_4_5)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_4_5)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_4_5+m_int_4_5)+"</td>");//10
				//6
				out.println("<td width='100' align='right'>"+nf.format(m_cap_5_6)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_5_6)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_5_6+m_int_5_6)+"</td>");//10
				//7
				out.println("<td width='100' align='right'>"+nf.format(m_cap_6_7)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_6_7)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_6_7+m_int_6_7)+"</td>");//10
				//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_7_8)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_7_8)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_7_8+m_int_7_8)+"</td>");//10
				//9
				out.println("<td width='100' align='right'>"+nf.format(m_cap_8_9)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_8_9)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_8_9+m_int_8_9)+"</td>");//10
				//10
				out.println("<td width='100' align='right'>"+nf.format(m_cap_9_10)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_9_10)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_9_10+m_int_9_10)+"</td>");//10
				//11
				out.println("<td width='100' align='right'>"+nf.format(m_cap_10_11)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_10_11)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_10_11+m_int_10_11)+"</td>");//10
				//12
				out.println("<td width='100' align='right'>"+nf.format(m_cap_11_12)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_11_12)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_11_12+m_int_11_12)+"</td>");//10
				//12-24
				out.println("<td width='100' align='right'>"+nf.format(m_cap_12_24)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_12_24)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_12_24+m_int_12_24)+"</td>");//10
				//24-36
				out.println("<td width='100' align='right'>"+nf.format(m_cap_24_36)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_24_36)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_24_36+m_int_24_36)+"</td>");//10
				//36-48
				out.println("<td width='100' align='right'>"+nf.format(m_cap_36_48)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_36_48)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_36_48+m_int_36_48)+"</td>");//10
				//48-60				
				out.println("<td width='100' align='right'>"+nf.format(m_cap_48_60)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_48_60)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_48_60+m_int_48_60)+"</td>");//10
				//60
				out.println("<td width='100' align='right'>"+nf.format(m_cap_60)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_60)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_60+m_int_60)+"</td>");//10
				
				out.println("</tr>");
				
				more = rs.next();
				
				m_sub_cap_tot = m_sub_cap_tot+m_cap_tot;
				m_sub_int_tot = m_sub_int_tot+m_int_tot;
				m_sub_oth_0_7 = m_sub_oth_0_7+m_oth_0_7;
				
				m_sub_cap_0_7=m_sub_cap_0_7+m_cap_0_7;
				m_sub_int_0_7=m_sub_int_0_7+m_int_0_7;
				
				m_sub_cap_1_2=m_sub_cap_1_2+m_cap_1_2;
				m_sub_int_1_2=m_sub_int_1_2+m_int_1_2;
				
				m_sub_cap_2_3=m_sub_cap_2_3+m_cap_2_3;
				m_sub_int_2_3=m_sub_int_2_3+m_int_2_3;
				
				m_sub_cap_3_4=m_sub_cap_3_4+m_cap_3_4;
				m_sub_int_3_4=m_sub_int_3_4+m_int_3_4;
				
				m_sub_cap_4_5=m_sub_cap_4_5+m_cap_4_5;
				m_sub_int_4_5=m_sub_int_4_5+m_int_4_5;
				
				m_sub_cap_5_6=m_sub_cap_5_6+m_cap_5_6;
				m_sub_int_5_6=m_sub_int_5_6+m_int_5_6;
				
				m_sub_cap_6_7=m_sub_cap_6_7+m_cap_6_7;
				m_sub_int_6_7=m_sub_int_6_7+m_int_6_7;
				
				m_sub_cap_7_8=m_sub_cap_7_8+m_cap_7_8;
				m_sub_int_7_8=m_sub_int_7_8+m_int_7_8;
				
				m_sub_cap_8_9=m_sub_cap_8_9+m_cap_8_9;
				m_sub_int_8_9=m_sub_int_8_9+m_int_8_9;
				
				m_sub_cap_9_10=m_sub_cap_9_10+m_cap_9_10;
				m_sub_int_9_10=m_sub_int_9_10+m_int_9_10;
				
				m_sub_cap_10_11=m_sub_cap_10_11+m_cap_10_11;
				m_sub_int_10_11=m_sub_int_10_11+m_int_10_11;
				
				m_sub_cap_11_12=m_sub_cap_11_12+m_cap_11_12;
				m_sub_int_11_12=m_sub_int_11_12+m_int_11_12;

				m_sub_cap_12_24=m_sub_cap_12_24+m_cap_12_24;
				m_sub_int_12_24=m_sub_int_12_24+m_int_12_24;
				
				m_sub_cap_24_36=m_sub_cap_24_36+m_cap_24_36;
				m_sub_int_24_36=m_sub_int_24_36+m_int_24_36;
				
				m_sub_cap_36_48=m_sub_cap_36_48+m_cap_36_48;
				m_sub_int_36_48=m_sub_int_36_48+m_int_36_48;
				
				m_sub_cap_48_60=m_sub_cap_48_60+m_cap_48_60;
				m_sub_int_48_60=m_sub_int_48_60+m_int_48_60;
				
				m_sub_cap_60=m_sub_cap_60+m_cap_60;
				m_sub_int_60=m_sub_int_60+m_int_60;
				
				
				}
				
				out.println("<tr height=30>");
				out.println("<td width='12' align='left'>&nbsp;</td>");//0
				out.println("<td width='200' align='left'>&nbsp;</td>");//1
				out.println("<td width='100' align='left'>&nbsp;</td>");//2
				out.println("<td width='100' align='left'>&nbsp;</td>");//2
				
				out.println("<td width='100' align='left'><b>Total</td>");//2//Added by Jithendra 28-06-2018
				
				//out.println("<td width='100' align='left'><b>Total</td>");//2
				
				out.println("<td width='100' align='right'><b>"+nf.format(m_rent_tot)+"</td>");//3//added by jithendra 28-06-2018
				
				out.println("<td width='100' align='left'> &nbsp; </td>");
				out.println("<td width='100' align='left'> &nbsp; </td>");
				
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_tot+m_sub_int_tot+m_sub_oth_0_7)+"</td>");//3
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_tot)+"</td>");//4
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_tot)+"</td>");//5
				//out.println("<td width='100' align='right'><b>"+nf.format(m_sub_oth_0_7)+"</td>");//6
				//1 Mont
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_0_7)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_0_7)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_0_7+m_sub_int_0_7)+"</td>");//10
		
				
				//2 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_1_2)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_1_2)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_1_2+m_sub_int_1_2)+"</td>");//10
				//3 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_2_3)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_2_3)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_2_3+m_sub_int_2_3)+"</td>");//10
				//4 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_3_4)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_3_4)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_3_4+m_sub_int_3_4)+"</td>");//10
				//5 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_4_5)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_4_5)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_4_5+m_sub_int_4_5)+"</td>");//10
				//6 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_5_6)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_5_6)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_5_6+m_sub_int_5_6)+"</td>");//10
				//7 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_6_7)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_6_7)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_6_7+m_sub_int_6_7)+"</td>");//10
				//8 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_7_8)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_7_8)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_7_8+m_sub_int_7_8)+"</td>");//10
				//9 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_8_9)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_8_9)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_8_9+m_sub_int_8_9)+"</td>");//10
				//10 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_9_10)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_9_10)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_9_10+m_sub_int_9_10)+"</td>");//10
				//11 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_10_11)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_10_11)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_10_11+m_sub_int_10_11)+"</td>");//10
				//12 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_11_12)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_11_12)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_11_12+m_sub_int_11_12)+"</td>");//10
				//12-24
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_12_24)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_12_24)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_12_24+m_sub_int_12_24)+"</td>");//10
				//24-36
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_24_36)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_24_36)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_24_36+m_sub_int_24_36)+"</td>");//10
				//36-48
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_36_48)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_36_48)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_36_48+m_sub_int_36_48)+"</td>");//10
				//48-60				
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_48_60)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_48_60)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_48_60+m_sub_int_48_60)+"</td>");//10
				//60
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_60)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_60)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_60+m_sub_int_60)+"</td>");//10
				out.println("</tr>");
								
				out.println("</table>");
				
				out.println("<br><br><br><br>");
				
			
				
				out.println("<br><br><br><br>");
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
				}
				
				
				}
				//-------------------------------------------
				catch (Exception ex) {
				try{out.println("Error:"+ex.toString());
				}catch(Exception e){}
				}
				finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
				}
				}
				}