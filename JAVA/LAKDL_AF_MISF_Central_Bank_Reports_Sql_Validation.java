import java.io.*; 
				import javax.servlet.*;   
				import javax.servlet.http.*; 
				import java.sql.*; 
				import java.util.*; 
				
				public class LAKDL_AF_MISF_Central_Bank_Reports_Sql_Validation extends javax.servlet.http.HttpServlet { 
				/*
				ServletOutputStream out = null;
				Connection conn;
				Statement stmt,stmt1,stmt2,stmt3;
				java.text.NumberFormat nf,nf1;
				public ResultSet rs,rs1,rs2,rs3;
				
				public String m_chksql;
				*/
				
				//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
				public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException {
					
					ServletOutputStream out = null;
				Connection conn= null;
				Statement stmt= null,stmt1= null,stmt2= null,stmt3= null;
				java.text.NumberFormat nf= null,nf1= null;
				 ResultSet rs= null,rs1= null,rs2= null,rs3= null;
				
				 String m_chksql;
				
				try { 
				
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
				
				stmt1=conn.createStatement();
				stmt2=conn.createStatement();
				
				CallableStatement callstmt1 =null;				
				
				
				if(m_chksql.equals("OEX_1_(i)_(A)")){
				
				String m_string="";				
				String m_sql="";	
				String m_to_date=req.getParameter("date");
				
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				if(m_to_date==null){
				m_to_date="";
				}
				
				if(!m_to_date.equals("")){	
				
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
				" INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 
				boolean mflag=true;						
				
				out.println("<HTML><HEAD><TITLE>Arrears For Three Months Or More Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
					
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				out.println("<br>");
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>ACCOMMADATIONS (REPAID IN FIXED INSTALLMENTS / RENTALS) IN ARREAS FOR THREE MONTHS OR MORE</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr >");
				out.println("<td width='10%' rowspan='2' align='left'><b>Contact no</b></td>"); //1
				out.println("<td width='10%' rowspan='2' align='left'><b>Name</b></td>"); //2
				out.println("<td width='10%' rowspan='2' align='left'><b>Amount Granted</b></td>"); //3
				out.println("<td width='10%' rowspan='2' align='left'><b>Date Granted</b></td>"); //4
				out.println("<td width='10%' rowspan='2' align='left'><b>Date of Expiry</b></td>"); //5
				out.println("<td width='10%' rowspan='2' align='left'><b>Terms of Repayment</b></td>"); //6
				out.println("<td width='10%' colspan='2' align='center'><b>Rentals in Arreas</b></td>"); //7
				out.println("<td width='10%' rowspan='2' align='right'><b>Stock Outstanding</b></td>"); //8
				out.println("<td width='10%' rowspan='2' align='right'><b>Other Receivables/Charges(Accrued)</b></td>"); //9
				out.println("<td width='10%' rowspan='2' align='right'><b>Pre-paid Rentals</b></td>"); //10
				out.println("<td width='10%' rowspan='2' align='right'><b>Interest in Suspence</b></td>"); //11
				out.println("<td width='10%' rowspan='2' align='right'><b>Net Exposure</b></td>"); //12
				out.println("<td width='10%' rowspan='2' align='left'><b>Due date of the Rental paid Last</b></td>"); //13
				out.println("<td width='10%' rowspan='2' align='left'><b>Amount & Date of Last Payment</b></td>"); //14
				out.println("<td width='10%' rowspan='2' align='left'><b>Security</b></td>"); //15
				out.println("<td width='10%' rowspan='2' align='center'><b>Age</b></td>"); //16
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='10%' align='center'><b>No.</b></td>");
				out.println("<td align='right'><b>Amount</b></td>");
				out.println("</tr>");
				
				
				int j=0;
				double m_net_exp=0;
				
			String m_transation_type="";	
			String m_status="";
			
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS, "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //30
			" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//30
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A  "+
			" WHERE  A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH','LOANS') "+
			" AND    A.APPLICATION_STATUS='ACTIVATED' "+
			" AND    A.RENTAL_STREAM=1 "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
				boolean more=rs2.next();
				
				while(more){
				
				m_transation_type=rs2.getString(19);
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>"+m_transation_type+"</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				
				while(m_transation_type.equals(rs2.getString(19))){
				
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(12)+rs2.getDouble(30));
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- rs2.getDouble(30);
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //1
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //2
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(6))+"</td>"); //3
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //4
				out.println("<td  align='left'>"+rs2.getString(4)+"</td>"); //5
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(7))+"</td>"); // arrears age
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(8))+"</td>"); //arrears amount
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(23))+"</td>"); //capital outstanding
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(22))+"</td>"); //other charges
				//out.println("<td  align='right'>"+nf.format(rs2.getDouble(9)+rs2.getDouble(10))+"</td>"); //ami +nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(30))+"</td>"); //nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(12))+"</td>"); //interest suspense
				out.println("<td  align='right'>"+nf.format(m_net_exp)+"</td>"); //net exposure
				out.println("<td  align='left'><b>-</b></td>"); //due date last payment
				out.println("<td  align='left'>"+rs2.getDouble(14)+" / "+rs2.getString(13)+" </td>"); //amount & date of last payment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(27))+"</td>"); //12
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(26))+"</td>"); //13
				out.println("</tr>");
				
				more=rs2.next();
				if(!more){
				break;}
				
				}
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				}
				
			rs2.close();	
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS, "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //30
			//" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//10
			" 0  NIBSM "+
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A  "+
			" WHERE  A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH','LOANS') "+
			" AND    A.APPLICATION_STATUS='LEGAL' "+
			" AND    A.RENTAL_STREAM=1 "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
			  more=rs2.next();
				
				double legal_tot=0;
				
				while(more){
				
				m_transation_type=rs2.getString(19);
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>LEGAL - "+m_transation_type+" </td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				
				while(m_transation_type.equals(rs2.getString(19))){
				
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- rs2.getDouble(30);
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(12)+rs2.getDouble(30));
				legal_tot=rs2.getDouble(8)+rs2.getDouble(23);
				
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //1
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //2
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(6))+"</td>"); //3
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //4
				out.println("<td  align='left'>"+rs2.getString(4)+"</td>"); //5
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(7))+"</td>"); // arrears age
				out.println("<td  align='right'>"+nf.format(legal_tot)+"</td>"); //arrears amount  //rs2.getDouble(8)
				out.println("<td  align='right'>"+nf.format(0.00)+"</td>"); //capital outstanding //rs2.getDouble(23)
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(22))+"</td>"); //other charges
				//out.println("<td  align='right'>"+nf.format(rs2.getDouble(9)+rs2.getDouble(10))+"</td>"); //ami +nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(30))+"</td>"); //nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(12))+"</td>"); //interest suspense
				out.println("<td  align='right'>"+nf.format(m_net_exp)+"</td>"); //net exposure
				out.println("<td  align='left'><b>-</b></td>"); //due date last payment
				out.println("<td  align='left'>"+rs2.getDouble(14)+" / "+rs2.getString(13)+"</td>"); //amount & date of last payment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(27))+"</td>"); //12
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(26))+"</td>"); //13
				out.println("</tr>");
				
				more=rs2.next();
				if(!more){
				break;}
				
				}
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				}

				
				out.println("</table>");
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				}
				}
				
				
				else if(m_chksql.equals("OEX_1_(i)_(B)")){
				
				String m_string="";				
				String m_sql="";	
				String m_to_date=req.getParameter("date");
				
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				if(m_to_date==null){
				m_to_date="";
				}
				
				if(!m_to_date.equals("")){	
				
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
				" INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 
				boolean mflag=true;						
				
				out.println("<HTML><HEAD><TITLE>Arrears For Three Months Or More Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
					
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				out.println("<br>");
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>ACCOMMADATIONS (REPAID IN FIXED INSTALLMENTS / RENTALS) IN ARREAS FOR THREE MONTHS OR MORE</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr >");
				out.println("<td width='10%' rowspan='2' align='left'><b>Contact no</b></td>"); //1
				out.println("<td width='10%' rowspan='2' align='left'><b>Name</b></td>"); //2
				out.println("<td width='10%' rowspan='2' align='left'><b>Amount Granted</b></td>"); //3
				out.println("<td width='10%' rowspan='2' align='left'><b>Date Granted</b></td>"); //4
				out.println("<td width='10%' rowspan='2' align='left'><b>Date of Expiry</b></td>"); //5
				out.println("<td width='10%' rowspan='2' align='left'><b>Terms of Repayment</b></td>"); //6
				out.println("<td width='10%' colspan='2' align='center'><b>Rentals in Arreas</b></td>"); //7
				out.println("<td width='10%' rowspan='2' align='right'><b>Stock Outstanding</b></td>"); //8
				out.println("<td width='10%' rowspan='2' align='right'><b>Other Receivables/Charges(Accrued)</b></td>"); //9
				out.println("<td width='10%' rowspan='2' align='right'><b>Pre-paid Rentals</b></td>"); //10
				out.println("<td width='10%' rowspan='2' align='right'><b>Interest in Suspence</b></td>"); //11
				out.println("<td width='10%' rowspan='2' align='right'><b>Net Exposure</b></td>"); //12
				out.println("<td width='10%' rowspan='2' align='left'><b>Due date of the Rental paid Last</b></td>"); //13
				out.println("<td width='10%' rowspan='2' align='left'><b>Amount & Date of Last Payment</b></td>"); //14
				out.println("<td width='10%' rowspan='2' align='left'><b>Security</b></td>"); //15
				out.println("<td width='10%' rowspan='2' align='center'><b>Age</b></td>"); //16
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='10%' align='center'><b>No.</b></td>");
				out.println("<td align='right'><b>Amount</b></td>");
				out.println("</tr>");
				
				
				int j=0;
				double m_net_exp=0;
				
			String m_transation_type="";	
			String m_status="";
			
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS , "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //30
			" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//30
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A  "+
			" WHERE  A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH','LOANS') "+
			" AND    A.APPLICATION_STATUS='ACTIVATED' "+
			" AND    A.RENTAL_STREAM <> 1 "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
				boolean more=rs2.next();
				
				while(more){
				
				m_transation_type=rs2.getString(19);
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>"+m_transation_type+"</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				
				while(m_transation_type.equals(rs2.getString(19))){
				
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- rs2.getDouble(30);
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(12)+rs2.getDouble(30));
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //1
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //2
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(6))+"</td>"); //3
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //4
				out.println("<td  align='left'>"+rs2.getString(4)+"</td>"); //5
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(7))+"</td>"); // arrears age
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(8))+"</td>"); //arrears amount
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(23))+"</td>"); //capital outstanding
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(22))+"</td>"); //other charges
				//out.println("<td  align='right'>"+nf.format(rs2.getDouble(9)+rs2.getDouble(10))+"</td>"); //ami +nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(30))+"</td>"); //nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(12))+"</td>"); //interest suspense
				out.println("<td  align='right'>"+nf.format(m_net_exp)+"</td>"); //net exposure
				out.println("<td  align='left'><b>-</b></td>"); //due date last payment
				out.println("<td  align='left'>"+rs2.getDouble(14)+" / "+rs2.getString(13)+"</td>"); //amount & date of last payment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(27))+"</td>"); //12
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(26))+"</td>"); //13
				out.println("</tr>");
				
				more=rs2.next();
				if(!more){
				break;}
				
				}
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				}
				
			rs2.close();	
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS , "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //30
			//" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//10
			" 0 NIBSM "+ //30
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A  "+
			" WHERE  A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH','LOANS') "+
			" AND    A.APPLICATION_STATUS='LEGAL' "+
			" AND    A.RENTAL_STREAM <> 1 "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
			  more=rs2.next();
				
				double legal_tot=0;
				
				while(more){
				
				m_transation_type=rs2.getString(19);
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>LEGAL - "+m_transation_type+" </td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				
				while(m_transation_type.equals(rs2.getString(19))){
				
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- rs2.getDouble(30);
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(12)+rs2.getDouble(30));
				legal_tot=rs2.getDouble(8)+rs2.getDouble(23);
				
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //1
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //2
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(6))+"</td>"); //3
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //4
				out.println("<td  align='left'>"+rs2.getString(4)+"</td>"); //5
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(7))+"</td>"); // arrears age
				out.println("<td  align='right'>"+nf.format(legal_tot)+"</td>"); //arrears amount  //rs2.getDouble(8)
				//out.println("<td  align='right'>"+nf.format(rs2.getDouble(23))+"</td>"); //capital outstanding
				out.println("<td  align='right'>"+nf.format(0.00)+"</td>"); //capital outstanding
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(22))+"</td>"); //other charges
				//out.println("<td  align='right'>"+nf.format(rs2.getDouble(9)+rs2.getDouble(10))+"</td>"); //ami +nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(30))+"</td>"); //nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(12))+"</td>"); //interest suspense
				out.println("<td  align='right'>"+nf.format(m_net_exp)+"</td>"); //net exposure
				out.println("<td  align='left'><b>-</b></td>"); //due date last payment
				out.println("<td  align='left'>"+rs2.getDouble(14)+" / "+rs2.getString(13)+"</td>"); //amount & date of last payment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(27))+"</td>"); //12
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(26))+"</td>"); //13
				out.println("</tr>");
				
				more=rs2.next();
				if(!more){
				break;}
				
				}
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				}

				
				out.println("</table>");
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				}
				}
				
				if(m_chksql.equals("OEX_1_(iii)")){
				
				String m_string="";				
				String m_sql="";	
				String m_to_date=req.getParameter("date");
				
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				if(m_to_date==null){
				m_to_date="";
				}
				
				if(!m_to_date.equals("")){	
				
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
				" INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 
				boolean mflag=true;						
				
				out.println("<HTML><HEAD><TITLE>Arrears For Three Months Or More Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
					
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				out.println("<br>");
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>ACCOMMADATIONS (REPAID IN FIXED INSTALLMENTS / RENTALS) IN ARREAS FOR THREE MONTHS OR MORE</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr >");
				out.println("<td width='10%' rowspan='2' align='left'><b>Contact no</b></td>"); //1
				out.println("<td width='10%' rowspan='2' align='left'><b>Name</b></td>"); //2
				out.println("<td width='10%' rowspan='2' align='left'><b>Type/Details of Asset</b></td>"); //3
				out.println("<td width='10%' rowspan='2' align='left'><b>Value of the asset at the inception</b></td>"); //4
				out.println("<td width='10%' rowspan='2' align='left'><b>Date Granted and Date of Expiry</b></td>"); //5
				out.println("<td width='10%' rowspan='2' align='left'><b>Terms of Repayment</b></td>"); //6
				out.println("<td width='10%' colspan='2' align='center'><b>Rentals in Arreas</b></td>"); //7
				out.println("<td width='10%' rowspan='2' align='right'><b>Other Receivables/Charges(Accrued)</b></td>"); //8
				out.println("<td width='10%' rowspan='2' align='right'><b>write Down Value of the Asset</b></td>"); //9
				out.println("<td width='10%' rowspan='2' align='right'><b>Interest in Suspence</b></td>"); //10
				out.println("<td width='10%' rowspan='2' align='right'><b>Security Deposit</b></td>"); //11
				out.println("<td width='10%' colspan='2' align='left'><b>Asset Returned by the Lessess</b></td>"); //12
				out.println("<td width='10%' rowspan='2' align='right'><b>Net Exposure</b></td>"); //13
				out.println("<td width='10%' rowspan='2' align='left'><b>Action Taken on Returned Asset</b></td>"); //14
				out.println("<td width='10%' rowspan='2' align='center'><b>Age</b></td>"); //15
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='10%' align='center'><b>No.</b></td>");
				out.println("<td align='right'><b>Amount</b></td>");
				out.println("<td width='10%' align='center'><b>Date.</b></td>");
				out.println("<td align='right'><b>Valuation of Asset</b></td>");
				out.println("</tr>");
				
				
				int j=0;
				double m_net_exp=0;
				
			String m_transation_type="";	
			String m_status="";
			
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS, "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //30
			" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//10
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A  "+
			" WHERE  A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH','LOANS') "+
			" AND    A.APPLICATION_STATUS='ACTIVATED' "+
			" AND    A.RENTAL_STREAM=1 "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
				boolean more=rs2.next();
				
				while(more){
				
				m_transation_type=rs2.getString(19);
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>"+m_transation_type+"</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				
				while(m_transation_type.equals(rs2.getString(19))){
				
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- rs2.getDouble(30);
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //1
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //2
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(6))+"</td>"); //3
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //4
				out.println("<td  align='left'>"+rs2.getString(4)+"</td>"); //5
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(7))+"</td>"); // arrears age
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(8))+"</td>"); //arrears amount
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(23))+"</td>"); //capital outstanding
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(22))+"</td>"); //other charges
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(9)+rs2.getDouble(10))+"</td>"); //ami +nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(12))+"</td>"); //interest suspense
				out.println("<td  align='right'>"+nf.format(m_net_exp)+"</td>"); //net exposure
				out.println("<td  align='left'><b>-</b></td>"); //due date last payment
				out.println("<td  align='left'>"+rs2.getDouble(14)+" / "+rs2.getString(13)+"</td>"); //amount & date of last payment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(27))+"</td>"); //12
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(26))+"</td>"); //13
				out.println("</tr>");
				
				more=rs2.next();
				if(!more){
				break;}
				
				}
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				}
				
			rs2.close();	
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS, "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //30
			" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//10
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A  "+
			" WHERE  A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH','LOANS') "+
			" AND    A.APPLICATION_STATUS='LEGAL' "+
			" AND    A.RENTAL_STREAM=1 "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
			  more=rs2.next();
				
				while(more){
				
				m_transation_type=rs2.getString(19);
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>"+m_transation_type+"</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				
				while(m_transation_type.equals(rs2.getString(19))){
				
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- rs2.getDouble(30);
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //1
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //2
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(6))+"</td>"); //3
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //4
				out.println("<td  align='left'>"+rs2.getString(4)+"</td>"); //5
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(7))+"</td>"); // arrears age
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(8))+"</td>"); //arrears amount
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(23))+"</td>"); //capital outstanding
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(22))+"</td>"); //other charges
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(9)+rs2.getDouble(10))+"</td>"); //ami +nibsm
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(12))+"</td>"); //interest suspense
				out.println("<td  align='right'>"+nf.format(m_net_exp)+"</td>"); //net exposure
				out.println("<td  align='left'><b>-</b></td>"); //due date last payment
				out.println("<td  align='left'>"+rs2.getDouble(14)+" / "+rs2.getString(13)+"</td>"); //amount & date of last payment
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(27))+"</td>"); //12
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(26))+"</td>"); //13
				out.println("</tr>");
				
				more=rs2.next();
				if(!more){
				break;}
				
				}
				out.println("<tr class=tr_input>");
				out.println("<td colspan=17 align='left'><b>&nbsp;</td>");
				out.println("</tr>");
				}

				
				out.println("</table>");
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				}
				}
				
				
				else if(m_chksql.equals("OEX_2")){
				
				String m_string="";				
				String m_sql="";	
				String m_to_date=req.getParameter("date");
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				if(m_to_date==null){
				m_to_date="";
				}
				
				if(!m_to_date.equals("")){	
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
				" INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 
				boolean mflag=true;						
				String Heading=" Financial Accommodations Granted to Directors, Employees, Major Share Holders, "+
				" Parent Company,Related Companies, Directors of the Parent Company and Employees of the Parent Company	";
				
				out.println("<HTML><HEAD><TITLE>"+Heading+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>"+rs1.getString(1)+"</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>Examination as at "+m_to_date+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>"+Heading+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr >");
				out.println("<td width='10%'  align='left'><b>Name Of the Borrower</b></td>"); //1
				out.println("<td width='10%'  align='left'><b>Relationship</b></td>"); //2
				out.println("<td width='10%'  align='left'><b>Type of advance</b></td>"); //3
				out.println("<td width='10%'  align='left'><b>Contact no</b></td>"); //4
				out.println("<td width='10%'  align='left'><b>Amount Granted</b></td>"); //5
				out.println("<td width='10%'  align='left'><b>Date Granted</b></td>"); //6
				out.println("<td width='10%'  align='left'><b>Terms of Repayment</b></td>"); //7
				out.println("<td width='10%'  align='center'><b>Rate of interest</b></td>"); //8
				out.println("<td width='10%'  align='right'><b>Net Exposure</b></td>"); //9
				out.println("<td width='10%'  align='center'><b>Age</b></td>"); //10
				out.println("<td width='10%'  align='left'><b>Type and value of security</b></td>"); //11
				out.println("<td width='10%'  align='center'><b>Action Taken</b></td>"); //12
				out.println("</tr>");
				
				int j=0;
				double m_net_exp=0;
			
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS , "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			" IRR, "+ //30
			" NVL(SECURITY_TYPE,'-'), "+ //31
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //32
			" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//10
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A , "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP B  "+
			" WHERE  A.CLIENT_CODE     = B.MEMBER_ID "+
			" AND    B.MEMBER_ID!      = '0000010800' "+// OFSCL
			" AND    B.GROUP_MASTER_ID = '0000010800' "+ //
			" AND    A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
				boolean more=rs2.next();
				
				while(more){
				
				//m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- rs2.getDouble(32);
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //name
				out.println("<td  align='right'>Group Companies</td>"); //relationship
				out.println("<td  align='left'>"+rs2.getString(19)+"</td>"); //type of advance
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //contract no
				out.println("<td  align='left'>"+nf.format(rs2.getDouble(6))+"</td>"); //amount granted
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //date granted
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='left'>"+nf.format(rs2.getDouble(30))+"</td>"); //rate of interest
				out.println("<td  align='right'>"+nf.format(m_net_exp)+"</td>"); //net exposure
				out.println("<td  align='right'>"+nf.format(rs2.getDouble(26))+"</td>"); //agregate Age
				out.println("<td  align='right'>"+rs2.getString(31)+"</td>"); //vehicle no /cr book
				out.println("<td  align='right'>N/A</td>"); //action taken
				out.println("</tr>");
				more=rs2.next();
				}
				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				}
				}
				
				else if(m_chksql.equals("OEX_11")){
				
				String m_string="";				
				String m_sql="";	
				String m_to_date=req.getParameter("date");
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				if(m_to_date==null){
				m_to_date="";
				}
				
				if(!m_to_date.equals("")){	
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
				" INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 
				boolean mflag=true;						
				String Heading="Written- Off Accommodations Over Rs 25,000 ";
				
				out.println("<HTML><HEAD><TITLE>"+Heading+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>"+rs1.getString(1)+"</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>Examination as at "+m_to_date+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>"+Heading+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr >");
				out.println("<td width='10%'  align='left'><b>Contact no</b></td>"); //4
				out.println("<td width='10%'  align='left'><b>Name Of the Borrower</b></td>"); //1
				out.println("<td width='10%'  align='left'><b>Amount Written off</b></td>"); //2
				out.println("<td width='10%'  align='left'><b>Date Written off</b></td>"); //2
				out.println("<td width='10%'  align='left'><b>Amount Recovered</b></td>"); //2
				out.println("<td width='10%'  align='left'><b>Balance Outstanding </b></td>"); //2
				out.println("<td width='10%'  align='left'><b>Action Taken </b></td>"); //2
				out.println("</tr>");
				
				int j=0;
				double m_net_exp=0;
			
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS , "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			" IRR, "+ //30
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //31
			" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//10
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A , "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP B  "+
			" WHERE  A.CLIENT_CODE     = B.MEMBER_ID "+
			" AND    B.MEMBER_ID!      = '0000010800' "+//
			" AND    B.GROUP_MASTER_ID = '0000010800' "+ //0000010800
			" AND    A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
				boolean more=rs2.next();
				
			/*	while(more){
				
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //name
				out.println("<td  align='right'>relationship</td>"); //relationship
				out.println("<td  align='left'>"+rs2.getString(19)+"</td>"); //type of advance
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //contract no
				out.println("<td  align='left'>"+rs2.getDouble(6)+"</td>"); //amount granted
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //date granted
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='left'>"+rs2.getDouble(30)+"</td>"); //rate of interest
				out.println("<td  align='right'>"+m_net_exp+"</td>"); //net exposure
				out.println("<td  align='right'>"+rs2.getDouble(26)+"</td>"); //agregate Age
				out.println("<td  align='right'>"+rs2.getDouble(27)+"</td>"); //security
				out.println("<td  align='right'>Action Taken</td>"); //action taken
				out.println("</tr>");
				more=rs2.next();
				}
				*/
				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				}
				}
				
				
				else if(m_chksql.equals("OEX_6")){
				
				String m_string="";				
				String m_sql="";	
				String m_to_date=req.getParameter("date");
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				if(m_to_date==null){
				m_to_date="";
				}
				
				if(!m_to_date.equals("")){	
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
				" INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 
				boolean mflag=true;						
				String Heading=" CONCENTRATION OF ACCOMMODATIONS";
				
				out.println("<HTML><HEAD><TITLE>"+Heading+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>"+rs1.getString(1)+"</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>Examination as at "+m_to_date+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='center' width='*%' ><b>"+Heading+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr >");
				out.println("<td width='10%'  align='left'><b>Name Of the Borrower</b></td>"); //1
				out.println("<td width='10%'  align='left'><b>Contact no</b></td>"); //2
				out.println("<td width='10%'  align='right'><b>Net Exposure</b></td>"); //3
				out.println("<td width='10%'  align='right'><b>Aggregate Net Exposure</b></td>"); //4
				out.println("<td width='10%'  align='right'><b>as a % of capital Funds</b></td>"); //5
				
				out.println("<td width='10%'  align='left'><b>Name Of the Borrower</b></td>"); //6
				out.println("<td width='10%'  align='left'><b>Contact no</b></td>"); //7
				out.println("<td width='10%'  align='right'><b>Net Exposure</b></td>"); //8
				out.println("<td width='10%'  align='right'><b>Aggregate Net Exposure</b></td>"); //9
				out.println("<td width='10%'  align='right'><b>as a % of capital Funds</b></td>"); //5
				
				out.println("</tr>");
				
				int j=0;
				double m_net_exp=0;
			
			rs2 = stmt2.executeQuery(
			//out.println(
			" SELECT  "+
			" DISTINCT A.FINANCE_NO, "+ //1
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
			" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE , "+ //3
			" TO_CHAR(A.EXPIRY_DATE,'DD-MM-YYYY') EXPIRY_DATE , "+ //4
		  " A.PERIOD, "+ //5
			" NVL(A.AMOUNT_FINANCE,0), "+ //6
			" NVL(A.ARREARS_AGE,0),  "+ //7
			" NVL(A.ARREARS_AMOUNT,0), "+ //8
			" NVL(A.AMI,0), "+ //9
			" NVL(A.NIBSM,0),  "+ //10
			" A.NUMBER_OF_ASSETS, "+ //11
			" NVL(A.INT_SUSPENSE,0), "+ //12
			" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+ //13
			" NVL(A.PAY_AMOUNT,0), "+ //14
			" NVL(A.CHARGES,0),  "+ //15
			" NVL(A.MAINTAINCE,0), "+ //16
			" NVL(A.GROSS_AMOUNT,0), "+ //17
			" A.TER_TYPE, "+ //18
			" A.TRANSACTION_TYPE, "+ //19
			" NVL(A.BAL_ARREARS,0),  "+ //20
			" NVL(A.APPLICATION_NO,0), "+ //21
			" NVL(A.OTHER_CHARGES,0), "+ //22
			" NVL(A.FUTURE_RECEIVABLES,0), "+ //23
			" NVL(A.INTEREST_RECEIVED,0), "+ //24
			" NVL(A.FUTURE_INTEREST,0), "+ //25
			" NVL(A.ARREARS_AGE,0) + NVL(A.MONTH_DIFF,0) AGG_AGE  ,  "+ //26
			" NVL(SECURITY_VAL,0), "+ //27
			" A.APPLICATION_STATUS , "+ //28
			" TERMS_OF_PAYMENTS, "+ //29
			" IRR, "+ //30
			//" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS  "+ //31
			" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM "+//10
			" FROM "+m_schema_name+".AF_CO_TBD_DETAILS_CBFSL A , "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP B  "+
			" WHERE  A.CLIENT_CODE     = B.MEMBER_ID "+
			" AND    B.MEMBER_ID!      = 'OFSCL' "+//0000010800
			" AND    B.GROUP_MASTER_ID = 'OFSCL' "+ //0000010800
			" AND    A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+
			" ORDER BY A.TRANSACTION_TYPE , AGG_AGE  ");
			
				boolean more=rs2.next();
				
			/*	while(more){
				
				m_net_exp=rs2.getDouble(8)+rs2.getDouble(23)+rs2.getDouble(22)- (rs2.getDouble(9)+rs2.getDouble(10));
			  out.println("<tr >");
				out.println("<td  align='left'>"+rs2.getString(2)+"</td>"); //name
				out.println("<td  align='right'>relationship</td>"); //relationship
				out.println("<td  align='left'>"+rs2.getString(19)+"</td>"); //type of advance
				out.println("<td  align='left'>"+rs2.getString(1)+"</td>"); //contract no
				out.println("<td  align='left'>"+rs2.getDouble(6)+"</td>"); //amount granted
				out.println("<td  align='left'>"+rs2.getString(3)+"</td>"); //date granted
				out.println("<td  align='left'>"+rs2.getString(29)+"</td>"); //term of repayment
				out.println("<td  align='left'>"+rs2.getDouble(30)+"</td>"); //rate of interest
				out.println("<td  align='right'>"+m_net_exp+"</td>"); //net exposure
				out.println("<td  align='right'>"+rs2.getDouble(26)+"</td>"); //agregate Age
				out.println("<td  align='right'>"+rs2.getDouble(27)+"</td>"); //security
				out.println("<td  align='right'>Action Taken</td>"); //action taken
				out.println("</tr>");
				more=rs2.next();
				}
				*/

				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				}
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
