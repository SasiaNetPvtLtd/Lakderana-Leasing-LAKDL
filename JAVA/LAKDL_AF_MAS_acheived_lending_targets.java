// CRAETED BY SAJITH MENDIS ON 13-02-2014


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
//import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_acheived_lending_targets extends javax.servlet.http.HttpServlet {
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public void service(HttpServletRequest req, HttpServletResponse res) // added by udara 18-05-2017
	{
		
		// added by udara 18-05-2017
		Connection conn = null;
		CallableStatement callstmt = null;
		Statement stmt= null,stmt1= null, stmt2= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null, rs2= null;
		String m_chksql= null,m_no_of_due_days= null,m_sys_date= null,m_ac_status= null;
		ServletOutputStream out = null;
		int m_appno_count=0;
		// end by udara 18-05-2017
		
		
		try {
			
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();		
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			LAKDL_AF_MK_CO_methods CO_method = new LAKDL_AF_MK_CO_methods(); //Added By Nishantha Ekanayake on 18-01-2016
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			String m_rep_cur=""; //added by nuwan de silva23-07-07
			
			String m_fschema_name=con_method.client_name.trim();
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			
			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			
					m_chksql         = req.getParameter("chksql");
			//		m_ac_status = req.getParameter("ac_status");
			//	stmt = conn.createStatement ();
				stmt1 = conn.createStatement ();
	
			String user_location = "";
			String user_location_set = "";
			
			try{
				
				stmt = conn.createStatement ();
				
				rs= stmt.executeQuery("  "+
					" SELECT "+
					" NVL("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"'),'-') "+
					" FROM DUAL "+
					" ");
				
				if(rs.next()){
					user_location = rs.getString(1);
				}
				
			}
			catch (Exception eee){
				out.println(eee.toString());
			}
			
			//out.println(user_location);
			
			if(user_location.equals("HEADOFFICE") || user_location.equals("HO")) // modified by udara on 13-09-2013
				user_location_set = "";
			else
				user_location_set = user_location;
			
			// end by udara on 03-12-2012	
			
			
			if(m_chksql.equals("view_report")){		
				
				
				String m_user_location_set= req.getParameter("user_location_set").trim();	
				String m_year = req.getParameter("user_year").trim();
				String m_month = req.getParameter("user_month").trim();
				String m_lead_source = req.getParameter("lead_source").trim();
				String m_region="";
				
				String m_user_location = req.getParameter("user_location_set").trim(); // added by udara 03-08-2016

				
				if(m_user_location_set.equals("ALL") || m_user_location_set.equals("all") || m_user_location_set.equals("")){
					m_user_location_set = "%";
					m_user_location = ""; // added by udara 03-08-2016
				}
				
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_CASE_SUMMARY_NEW(:1,:2,:3,:4,:5); END;");
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_CASE_SUMMARY_NEW3(:1,:2,:3,:4,:5); END;");
				
				callstmt.setString(1,m_month);
				callstmt.setString(2,m_year);
				callstmt.setString(3,m_username);
				//callstmt.setString(4,""); // commented by udara 03-08-2016
				callstmt.setString(4,m_user_location); // added by udara 03-08-2016
				callstmt.setString(5,"");
				callstmt.execute();
				conn.commit(); 
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Lending Summary Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function Branch_drill(val1,val2,val3){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=branch_drill&branch=\"+val1+\"&from_date=\"+val2+\"&to_date=\"+val3;");
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;");
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				
				out.println("function Achieved_Cases(m_location,m_month,m_year,m_app_num){ ");
				//out.println("alert('value');");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_acheived_lending_targets?chksql=print_report_new_1_drill&location=\"+m_location+\"&month=\"+m_month+\"&year=\"+m_year+\"&app_num=\"+m_app_num;");
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}"); 
	
				// added by udara 19-08-2015
				
				out.println("function Achieved_Cases_2(m_location,m_month,m_year,m_app_num){ ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_acheived_lending_targets?chksql=print_report_new_1_drill_2&location=\"+m_location+\"&month=\"+m_month+\"&year=\"+m_year+\"&app_num=\"+m_app_num;");
				out.println("  window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}"); 
				
				// end by udara 19-08-2015
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				
				
				String outer_query ="";
				
				outer_query = " SELECT  "+
									" A.USER_ID, "+
									" "+m_schema_name+".AF_CO_GET_EMP_NAME(A.USER_ID), "+
									" A.DESIGNATION_NAME, "+
									" A.TARGET_CASES, "+
									" A.TARGET_VALUE, "+
									" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) Branch FROM  "+m_schema_name+".CO_MAS_LEND_TARGETS A, "+m_schema_name+".CO_CO_MAS_EMPLOYEE B, "+m_schema_name+".CO_CO_MAS_USER C  "+
									" WHERE A.LOCATION_CODE LIKE '"+m_user_location_set+"' "; // commented by udara 14-07-2016
									//" WHERE B.LOCATION_CODE LIKE '"+m_user_location_set+"' "; // added by udara 14-07-2016
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Ruwani 2015-12-29
					outer_query = outer_query +"    AND   "+m_schema_name+".AF_CO_GET_USER_REGION(A.LOCATION_CODE) = '"+m_region+"' ";    
				}		
				
				outer_query = outer_query + 
				
									" AND  A.USER_ID = B.EMP_CODE   "+
									" AND  A.USER_ID  = C.EMP_ID  "+
									//" AND UPPER(C.ACTIVE_STATUS) = 'Y' "+ 
							//		" AND UPPER(C.ACTIVE_STATUS) NOT IN ('N') "+  //commented by Kanchana for #19214
									" AND UPPER(C.ACTIVE_STATUS) IN ('N','Y') "+  //added by Kanchana for #19214
									" AND A.PERIOD_MONTH = '"+m_month+"' AND A.PERIOD_YEAR = '"+m_year+"'  "+
									//" AND A.TARGET_VALUE > 0   "+ // added by udara 25-01-2016//Commented by Jithedra on 09-11-2016 for SR #22191
									" ORDER BY Branch, A.TARGET_VALUE  " ; // Added By Ruwani
				
				
				
				
				
				rs=stmt.executeQuery(outer_query);
				
				
				boolean more=rs.next();
				//more=rs.next();
				int count=1;
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				//out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>LAKDERANA INVESTMENTS LTD </u></td>"); 
				out.println("</tr >");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>ACHIEVED LENDING TARGETS</u></td>");   // from 01-01-2012 to 10-07-2013 
				out.println("</tr >");
				//out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				//out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				//else{
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td width=\"12%\" STYLE='{text-align:center;}' ><b>Seq No</b></td>"); 			
				out.println("<td STYLE='{text-align:center;}' ><b>Branch Name</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Name</b></td>"); 
				out.println("<td STYLE='{text-align:center;}' ><b>Designation</b></td>"); 
				out.println("<td STYLE='{text-align:center;}' ><b>Target Cases</b></td>"); 
				out.println("<td STYLE='{text-align:center;}' ><b>Target Value</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Achieved Cases</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Achieved Value</b></td>"); 	
				out.println("</tr >");
				
				
				int j=1;
				
				double tot_target_cases   = 0.00;
				double tot_target_value   = 0.00;
				double tot_achieved_cases = 0.00;
				double tot_achieved_value = 0.00;
				
				
				while(more){
					
					double achieved_Cases   = 0.00;
					double archieved_Val    = 0.00;
					
					
					//Added by Nishantha on 19-01-2016
					String table_name = ","+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B ";
					String sql_filter = "  AND A.APPLICATION_NO = B.APPLICATION_NO AND B.LEAD_SOURCE_CATEGORY = '"+m_lead_source+"' ";
					
					//if(m_lead_source.equals("NONE")|| m_lead_source.equals("") ){
					if(m_lead_source.equals("NONE")|| m_lead_source.equals("") || m_lead_source.equals("ALL") ){
						sql_filter = " ";
						table_name = "";
					}
					String sql = "";
								sql =	" SELECT "+					
										" NVL(a.facility_amount,0) "+
										" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_NEW A "+table_name+" "+										
										" WHERE TRUNC(A.ACTIVATED_DATE) >= TO_DATE('01-"+m_month+"-"+m_year+"','DD-MON-YYYY') "+
										" AND TRUNC(A.ACTIVATED_DATE) <= LAST_DAY(TO_DATE('01-"+m_month+"-"+m_year+"','DD-MON-YYYY'))  "+sql_filter+" "+										
										//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER("+m_schema_name+".AF_CO_GET_APPLICAT_NO(A.APPLICATION_NO)) = UPPER('"+rs.getString(1)+"') AND  A.ENT_USER = UPPER('"+m_username+"')"; // commented by udara 14-03-2019
										" AND A.CR_OFFICER = UPPER('"+rs.getString(1)+"') AND  A.ENT_USER = UPPER('"+m_username+"')"; // added by udara 14-03-2019
										
				
								
					rs1=stmt1.executeQuery(sql);
					
					//End Nishantha
					
					boolean more1=rs1.next();
					
					while(more1){
						achieved_Cases++;
						archieved_Val = archieved_Val + rs1.getDouble(1);
						more1=rs1.next();
					}
					
					if(archieved_Val>0){//if added by Jithendra 01-12-2016
					out.println("<tr>");
					out.println("<td width=\"12%\"  STYLE='{text-align:left;}' > "+count+" </td>"); 	// 		
					out.println("<td  STYLE='{text-align:left;}' > "+rs.getString(6)+"   </td>"); 
					out.println("<td  STYLE='{text-align:left;}' > "+rs.getString(2)+" </td>"); 
					out.println("<td  STYLE='{text-align:left;}' > "+rs.getString(3)+" </td>"); 
					out.println("<td  STYLE='{text-align:right;}' > "+nf1.format(rs.getDouble(4))+" </td>"); 
					out.println("<td  STYLE='{text-align:right;}'> "+nf.format(rs.getDouble(5))+" </td>"); 
					out.println("<td  STYLE='cursor:hand; {text-align:right;}'  onclick=\"Achieved_Cases('"+rs.getString(6)+"','"+m_month+"','"+m_year+"','"+rs.getString(1)+"');\" > "+nf1.format(achieved_Cases)+" </td>"); 
					out.println("<td  STYLE='{text-align:right;}' > "+nf.format(archieved_Val)+" </td>"); 
					out.println("</tr>");
					count++;
					
					tot_target_cases   = tot_target_cases   + rs.getInt(4);
					tot_target_value   = tot_target_value   + rs.getDouble(5);
					tot_achieved_cases = tot_achieved_cases + achieved_Cases;
					tot_achieved_value = tot_achieved_value + archieved_Val;
				}
					more=rs.next();
					
					
				}
				
				
				
				out.println("<tr>");
				out.println("<td width=\"12%\" STYLE='{text-align:left;}'></td>"); 			
				out.println("<td STYLE='cursor:hand; {text-align:left;}' ></td>"); 
				out.println("<td STYLE='cursor:hand; {text-align:left;}' ></td>"); 
				out.println("<td STYLE='cursor:hand; {text-align:left;}' ></td>"); 
				out.println("<td STYLE='cursor:hand; {text-align:right;}' ><b>"+nf1.format(tot_target_cases)+"</b></td>"); 
				out.println("<td STYLE='cursor:hand; {text-align:right;}'><b>"+nf.format(tot_target_value)+"</b></td>"); 
				//out.println("<td STYLE='cursor:hand; {text-align:right;}' ><b>"+nf1.format(tot_achieved_cases)+"</b></td>"); 
				out.println("<td STYLE='cursor:hand; {text-align:right;}'  onclick=\"Achieved_Cases_2('"+m_user_location_set+"','"+m_month+"','"+m_year+"','');\" ><b> "+nf1.format(tot_achieved_cases)+" </b></td>");
				out.println("<td STYLE='cursor:hand; {text-align:right;}'><b>"+nf.format(tot_achieved_value)+"</b></td>"); 
				out.println("</tr>");
				
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");		 
				
				
				
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}	
			
			
			if(m_chksql.equals("print_report_new_1_drill")){		//added by kanishka dilshan on 15-05-2013
				
				String m_location="";
				String m_year="";
				String m_month="";
				String m_app_num="";
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				if(req.getParameter("month")!=null ){
					m_month=req.getParameter("month").trim();
				}
				if(req.getParameter("year")!=null ){
					m_year=req.getParameter("year").trim();
				}
				if(req.getParameter("app_num")!=null ){
					m_app_num=req.getParameter("app_num").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Branch Reports - Achieved Lending Targets</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here	
				// added by udara on 26-07-2013
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				//out.println("    window.open(m_url); ");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Achieved Cases</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_data="";
				boolean more;
				
			
					
					Sql_data=" SELECT "+
					//out.println(" SELECT "+
										" NVL(a.FINANCE_NO,'-'), "+
										" NVL(a.FACILITY_AMOUNT,0) "+
										" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_NEW A "+
										" WHERE TRUNC(A.ACTIVATED_DATE) >= TO_DATE('01-"+m_month+"-"+m_year+"','DD-MON-YYYY') "+
										" AND TRUNC(A.ACTIVATED_DATE) <= LAST_DAY(TO_DATE('01-"+m_month+"-"+m_year+"','DD-MON-YYYY')) "+
										//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER("+m_schema_name+".AF_CO_GET_APPLICAT_NO(A.APPLICATION_NO)) = UPPER('"+m_app_num+"') AND  ENT_USER = UPPER('"+m_username+"')";
										" AND A.CR_OFFICER = UPPER('"+m_app_num+"') AND  A.ENT_USER = UPPER('"+m_username+"')"; // added by udara 14-03-2019
										
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				more=rs.next();
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr><td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No.</B></td>");
				out.println("<td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Facility Amount</B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:center; cursor:hand;' bgcolor='"+m_td_color+"' >"+rs.getString(1)+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(2))+"</td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(2);
					num_row++;
					more=rs.next();
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}

			// added by udara 19-08-2015

			if(m_chksql.equals("print_report_new_1_drill_2")){		//added by kanishka dilshan on 15-05-2013
				
				String m_location="";
				String m_year="";
				String m_month="";
				String m_app_num="";
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				if(req.getParameter("month")!=null ){
					m_month=req.getParameter("month").trim();
				}
				if(req.getParameter("year")!=null ){
					m_year=req.getParameter("year").trim();
				}
				if(req.getParameter("app_num")!=null ){
					m_app_num=req.getParameter("app_num").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Branch Reports - Achieved Lending Targets</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here	
				// added by udara on 26-07-2013
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				//out.println("    window.open(m_url); ");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Achieved Cases</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_data="";
				boolean more;
				
			
					
					Sql_data=" SELECT "+
					//out.println(" SELECT "+
										" NVL(a.FINANCE_NO,'-'), "+
										" NVL(a.FACILITY_AMOUNT,0) "+
										" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_NEW A "+
										" WHERE TRUNC(A.ACTIVATED_DATE) >= TO_DATE('01-"+m_month+"-"+m_year+"','DD-MON-YYYY') "+
										" AND  TRUNC(A.ACTIVATED_DATE) <= LAST_DAY(TO_DATE('01-"+m_month+"-"+m_year+"','DD-MON-YYYY')) "+
										" AND  A.LOCATION_CODE LIKE '"+m_location+"%' "+
										//" AND  A.LOCATION_CODE LIKE "+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"')||'%' "+//Commented By Jithednra 04-11-2016
										//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER("+m_schema_name+".AF_CO_GET_APPLICAT_NO(A.APPLICATION_NO)) LIKE '%%' "+//Commented By Jithednra 04-11-2016
										" AND  ENT_USER = UPPER('"+m_username+"')";
			
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				more=rs.next();
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr><td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No.</B></td>");
				out.println("<td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Facility Amount</B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:center; cursor:hand;' bgcolor='"+m_td_color+"' >"+rs.getString(1)+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(2))+"</td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(2);
					num_row++;
					more=rs.next();
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}

			// end by udara 19-08-2015
			
			
			if(m_chksql.equals("main_page")){
				
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var lineno_finanace=0;");
			out.println("var b_check_help=0;");
			
			out.println("var new_data_vec=new Array();");
			
			
			
			
			out.println("function get_vector(data_vec) {");
			out.println("m_user_val=\"TXT_USER\"+document.Form1.hid_row_no.value;");
			out.println("m_finace_val=\"TXT_FINANCE_NO\"+document.Form1.hid_row_no.value;");
			out.println("			if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M2'  && document.Form1.elements[m_user_val].value!=\"\"){");
			out.println("    document.Form1.elements[m_user_val].value=data_vec[0];"); 
			
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.elements[m_user_val].value!=\"\"){");
			out.println("     help_button_user(document.Form1.hid_row_no.value);");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				new_data_vec=data_vec;");
			out.println("				display_data(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				display_message();");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3'){");
			out.println("				new_data_vec=data_vec;");
			out.println("				display_data2(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M4'  && document.Form1.elements[m_finace_val].value!=\"\"){");
			out.println("    document.Form1.elements[m_finace_val].value=data_vec[0];"); 
			out.println("show_data_by_finanace_no(document.Form1.elements[m_finace_val].value,'CLIENT_NAME','ASC');");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.elements[m_finace_val].value!=\"\"){");
			out.println("     help_button_finanace_no(document.Form1.hid_row_no.value);");
			out.println("			}");
			out.println("			else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G4'  && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("   help_update()");			
			out.println("			}");
			out.println("			else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G5'  && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("   help_button_6()");			
			out.println("			}");			
			
			out.println("		 else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G2'  && document.Form1.TXT_NEW_COLL_OFFICER.value!=\"\"){");
			out.println("help_button_user_col_new()");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='G2'  && document.Form1.TXT_NEW_COLL_OFFICER.value!=\"\"){");
			out.println("		 document.Form1.TXT_NEW_COLL_OFFICER.value=data_vec[0];");
			out.println("			}");
			
			
			out.println("		 else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G1'  && document.Form1.TXT_COLL_OFFICER.value!=\"\"){");
			out.println("help_button_user_col()");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='G1'  && document.Form1.TXT_COLL_OFFICER.value!=\"\"){");
			out.println("		 document.Form1.TXT_COLL_OFFICER.value=data_vec[0];");
			out.println("    get_data_col_officer('CLIENT_NAME','ASC')	");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M_OFFICER'  && document.Form1.TXT_COLL_OFFICER.value!=\"\"){");
			out.println("		 new_data_vec=data_vec;");
			out.println("    display_data_officer(data_vec);");
			out.println("			}");
			
			out.println("			}");
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_user(document.Form1.hid_row_no.value);"); 
			out.println("		}"); 
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_finanace_no(document.Form1.hid_row_no.value);"); 
			out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		assign_help_button_user_col();"); 
			out.println("		}"); 
			
			out.println("		if(IfCount==\"5\"){"); 
			out.println("		assign_help_button_user_col_new();"); 
			out.println("		}"); 
			
			
			
			out.println("		if(IfCount==\"6\"){"); 
			out.println("		help_value_assign_6(oBj);"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("		}"); 
			
			// added by udara on 09-10-2012
			
			out.println("		if(IfCount==\"41\"){"); 
			out.println("		help_value_assign_41(oBj);"); 
			out.println("		}"); 
			
			// end by udara on 09-10-2012
			
			
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data();");//Added To The Clear The Area Code
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 
			
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			
			
			
			out.println("function clear_data() {");
			
			out.println("if(b_check_help==1){");
			out.println("m_user_clear=\"TXT_USER\"+document.Form1.hid_row_no.value;");
			out.println("document.Form1.elements[m_user_clear].value=\"\";");
			out.println("document.Form1.elements[m_user_clear].focus();");
			out.println("}");
			
			out.println("if(b_check_help==2){");
			out.println("m_user_clear=\"TXT_FINANCE_NO\"+document.Form1.hid_row_no.value;");
			out.println("document.Form1.elements[m_user_clear].value=\"\";");
			out.println("}");
			
			out.println("}");
			
			out.println("var m_sort_column='CLIENT_NAME';");
			out.println("var m_order_by_type='ASC';");
			
			out.println("function sort_data(m_sort_col) {");
			out.println("	 order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col==m_sort_column){");
			out.println("	   if(m_order_by_type=='DESC'){");
			out.println("	      order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("       order_by_type = 'ASC'; ");
			out.println("  }");
			
			out.println("m_sort_column=m_sort_col;");
			out.println("m_order_by_type=order_by_type;");
			
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
			
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("get_Application_numbers(m_sort_col,m_order_by_type);");
			out.println("}");
			
			out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\" ){");
			out.println("i=0;");
			//out.println("m_assign_finanace=\"TXT_FINANCE_NO\"+i");
			out.println("show_data_by_finanace_no(m_sort_col,m_order_by_type);"); //document.Form1.elements[m_assign_finanace].value,
			out.println("}");
			out.println("}");
			
			out.println("else {");
			out.println("get_data_col_officer(m_sort_col,m_order_by_type);");
			out.println("}");
			
			out.println("}");
			
			
			out.println("function befor_end(m_obj) {");
			//out.println("alert('value'+m_obj);");
			out.println("if(m_obj==\"GO_TOP\"){");
			out.println("m_go_top=\"top_b\";");
			out.println("document.Form1.elements[m_go_top].focus();}");
			out.println("else if(m_obj==\"GO_END\"){");
			out.println("m_go_end=\"end_b\";");
			out.println("document.Form1.elements[m_go_end].focus();}");
			
			//    out.println("   m_obj.focus();");
			out.println("}");
			
			
			
			
			
			
			out.println("function display_message(){");
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
			out.println("'<td  width=\"*%\" align=\"center\"><B>No Records Found</td>'+");
			out.println("'</TR></table>';");		
			
			out.println("}");
			
			
			
			//!--------Display The Header -------------------------------------//
			out.println("function header(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_table3.innerHTML=\"\" ");
			out.println("lineno=0;");
			out.println("arr_size=0;");
			
			out.println("	if(document.Form1.hid_chk_status.value=='M1'){");		
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand;    ><B>Branch Name</td>'+");
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand;    ><B>Client Name</td>'+");
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand;  ><B>Designation</td>'+");
			//out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - City Code     \"    onclick=sort_data(\"CITY_CODE\") ><B>City Code</td>'+");
			/* out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Inquiry No  \"        onclick=sort_data(\"INQUARY_NO\") ><B>Inquiry No</td>'+");
          out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO\") ><B>Finance No</td>'+");
				//	out.println("'<td  width=\"10%\" align=\"left\"><B><u>Core Applicant</u></td>'+");
				//out.println("'<td  width=\"10%\" align=\"left\"><B><u>Facility No</u></td>'+");
					out.println("'<td  width=\"12.5%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Total Finance Amount  \"    onclick=sort_data(\"TOTAL_FINANCE_AMOUNT\") ><B>Total Finance Amount</td>'+");
					out.println("'<td  width=\"12.5%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Current Finance Amount  \"    onclick=sort_data(\"CURRENT_FINANCE_AMOUNT\") ><B>Current Finance Amount</td>'+");*/
			//out.println("'<td  width=\"15%\" align=\"left\" style={width:120px;}><B>Collection Officer</td>'+");
			//out.println("'<td  width=\"5%\" align=\"center\"></td>'+");
			out.println("'<td  width=\"15%\" align=\"center\" style={width:120px;}><B>Target Cases</td>'+");
			out.println("'<td  width=\"15%\" align=\"center\" style={width:120px;}><B>Target Value</td>'+");	
			
			out.println("'</TR></table>';");		
			
			out.println("}");
			out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");		
			out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
			out.println("'<td  width=\"20%\" align=\"left\" ><B>Branch Name</td>'+");
			out.println("'<td  width=\"20%\" align=\"left\" ><B>Client Name</td>'+");
			out.println("'<td  width=\"20%\" align=\"left\" ><B>Designation</td>'+");
			// out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - City Code     \"    onclick=sort_data(\"CITY_CODE\") ><B>City Code</td>'+");
			/*   out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Inquiry No  \"        onclick=sort_data(\"INQUARY_NO\") ><B>Inquiry No</td>'+");
				out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO\") ><B>Finance No</td>'+");
					//	out.println("'<td  width=\"10%\" align=\"left\"><B><u>Core Applicant</u></td>'+");
					//out.println("'<td  width=\"10%\" align=\"left\"><B><u>Facility No</u></td>'+");
						out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Total Finance Amount  \"    onclick=sort_data(\"TOTAL_FINANCE_AMOUNT\") ><B>Total Finance Amount</td>'+");
						out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Currernt Finance Amount  \"    onclick=sort_data(\"CURRENT_FINANCE_AMOUNT\") ><B>Current Finance Amount</td>'+");
						out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Assigned Officer  \"    onclick=sort_data(\"COLLECTION_OFFICER\")><B>Assigned Officer</td>'+");*/
			//out.println("'<td  width=\"15%\" align=\"left\" style={width:120px;}><B>Collection Officer</td>'+");
			//out.println("'<td  width=\"5%\" align=\"center\"></td>'+");
			out.println("'<td  width=\"20%\" align=\"center\" ><B>Target Cases</td>'+");
			out.println("'<td  width=\"20%\" align=\"center\" ><B>Target Value</td>'+");	
			out.println("'</TR></table>';");		
			
			out.println("}");
			out.println("	else if(document.Form1.hid_chk_status.value=='M_OFFICER'){");		
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand;   onclick=sort_data(\"CLIENT_NAME\") ><B>Client Name</td>'+");
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand;   onclick=sort_data(\"DESIGNATION\") ><B>Designation</td>'+");
			//out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - City Code     \"    onclick=sort_data(\"CITY_CODE\") ><B>City Code</td>'+");
			/* out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Inquiry No  \"        onclick=sort_data(\"INQUARY_NO\") ><B>Inquiry No</td>'+");
				out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO\") ><B>Finance No</td>'+");
						out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Total Finance Amount  \"    onclick=sort_data(\"TOTAL_FINANCE_AMOUNT\") ><B>Total Finance Amount</td>'+");
						out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Currernt Finance Amount  \"    onclick=sort_data(\"CURRENT_FINANCE_AMOUNT\") ><B>Current Finance Amount</td>'+");
						out.println("'<td  width=\"8%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Assigned Officer  \"    onclick=sort_data(\"COLLECTION_OFFICER\") ><B>Assigned Officer</td>'+");*/
			out.println("'<td  width=\"12%\" align=\"center\" style={width:120px;}><B>Collection Officer</td>'+");
			////out.println("'<td  width=\"5%\" align=\"center\"></td>'+");
			out.println("'<td  width=\"15%\" align=\"center\" style={width:100px;}><B>Remark</td>'+");
			out.println("'<td  width=\"5%\" align=\"left\" style={width:10px;} ><B>Approve</td>'+");
			out.println("'</TR></table>';");		
			
			out.println("}");
			
			
			
			
			out.println("}");
			
			
			out.println("function change_val_req(row_no){")	;
			out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_required].checked==true){");
			out.println("document.Form1.elements[m_chk_required].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
			out.println("document.Form1.elements[m_chk_required].value='off'");
			out.println("}");	
			out.println("}");	
			
			
			out.println("function help_button_user(row_No) {"); 
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("b_check_help=1;");
			out.println("m_user=\"TXT_USER\"+row_No");
			out.println("    Crit = document.Form1.elements[m_user].value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_USER_ID_sql','1');"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','1');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_user(row_No) {"); 
			out.println("m_user_assign=\"TXT_USER\"+row_No");
			out.println("document.Form1.elements[m_user_assign].value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_user_col() {"); 
			out.println("    Crit = document.Form1.TXT_COLL_OFFICER.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_USER_ID_sql','3');"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','3');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function assign_help_button_user_col() {"); 
			out.println("    document.Form1.TXT_COLL_OFFICER.value=oBj.valout[2];"); 
			out.println(" view_collection_officer_data()");
			out.println("}"); 
			
			out.println("function help_button_user_col_new() {"); 
			out.println("    Crit =document.Form1.TXT_NEW_COLL_OFFICER.value+\"@\"+document.Form1.TXT_COLL_OFFICER.value+\"@Y@\";"); 
			//out.println("    Crit = document.Form1.TXT_NEW_COLL_OFFICER.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_collection_officer_new','5');"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_employee_id_new','5');"); 
			
			out.println("}"); 
			out.println(""); 
			
			out.println("function assign_help_button_user_col_new() {"); 
			out.println("    document.Form1.TXT_NEW_COLL_OFFICER.value=oBj.valout[2];"); 
			out.println("    assign_collection_bulk();"); 
			out.println("}"); 
			
			
			
			out.println("function help_button_finanace_no(row_No) {"); 
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("b_check_help=2;");
			out.println("m_finance=\"TXT_FINANCE_NO\"+row_No");
			out.println("    Crit = document.Form1.elements[m_finance].value+\"@ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql','2');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_finanace_no(row_No) {"); 
			out.println("m_assign_finanace=\"TXT_FINANCE_NO\"+row_No");
			out.println("    document.Form1.elements[m_assign_finanace].value=oBj.valout[2];"); 
			out.println("show_data_by_finanace_no('CLIENT_NAME','ASC');");//document.Form1.elements[m_assign_finanace].value,
			out.println("}"); 
			
			out.println("function val_finanace_no(row_No){");
			out.println("assignState('M4')");
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("m_finance_no_val=\"TXT_FINANCE_NO\"+row_No");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_validate_finace_no&data_val=\"+document.Form1.elements[m_finance_no_val].value+\"&ac_status=ACTIVATED\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			
			out.println("}"); 
			
			out.println("function show_data_by_finanace_no(m_sort_column,m_order_by_type){"); //val,
			out.println("assignState('M3')");
			
			out.println("var m_user_location_set = '"+user_location_set+"'; "); // added by udara on 13-09-2013
			//modified by delanjali for ref 845 on 2007-09-05
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no&finanace_no=\"+val+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			
			//comment by nuwan de silva on 18-10-07-----------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			
			//added by nuwan de silva on 18-10-07-----------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED&finance_no=\"+document.Form1.TXT_CONTRACT_NO.value;"); // commented by udara on 13-09-2013
			
			//SAJITH 12-02-2014
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_users&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED&finance_no=\"+document.Form1.TXT_CONTRACT_NO.value+\"&user_location_set=\"+m_user_location_set;");  // added by udara on 13-09-2013
			//out.println("window.open(m_url)");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_users&user_location_set=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_year=\"+document.Form1.TXT_YEAR.value+\"&user_month=\"+document.Form1.TXT_MONTH.value;");  
			
			out.println("load_interface(m_url,'XML');");
			out.println("}"); 
			
			
			
			//added by nuwan de silva on 19-10-07-----------------------------------------
			out.println("function get_data_col_officer(m_sort_column,m_order_by_type){"); 
			out.println("assignState('M_OFFICER')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_assign_data&col_officer=\"+document.Form1.TXT_COLL_OFFICER.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);"); 
			out.println("}"); 
			
			//added by nuwan de silva on 19-10-07-----------------------------------------
			out.println("function assign_collection_bulk(){"); 
			out.println("for(i=0;i<arr_size;i++){"); 
			out.println("m_new_officer=\"TXT_USER\"+i");
			out.println("m_chk_req=\"CHK_REQUIRED\"+i");
			out.println("document.Form1.elements[m_new_officer].value=document.Form1.TXT_NEW_COLL_OFFICER.value;"); 
			out.println("document.Form1.elements[m_chk_req].checked=true;"); 
			out.println("document.Form1.elements[m_chk_req].value='on';"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function val_user(row_No){");
			out.println("assignState('M2')");
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("m_user_val=\"TXT_USER\"+row_No");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_User&data_val=\"+document.Form1.elements[m_user_val].value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_Assign_Collection_Officer&data_val=\"+document.Form1.elements[m_user_val].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("  function  display_data_officer(data_vec){");
			out.println("header();	");		
			out.println("var i=0;");
			out.println("var j=0;");
			out.println("var c_client=0;");
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_top.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			out.println("while(i<data_vec.length){");
			out.println("m_app_no=       '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_app_details('+[c_client]+')\" ><u>'+data_vec[i]+'</u></TD>';");
			out.println("m_client_code=  '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[c_client+8]+')\"><u>'+data_vec[i+1]+'</u></TD>';");
			//out.println("m_city_code=    '<TD WIDTH=\"10%\"   align=\"left\">'+data_vec[i+2]+'</TD>';");
			out.println("m_inq_no=       '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_inq_details('+[c_client+3]+')\"><u>'+data_vec[i+3]+'</u></TD>';");
			out.println("m_fin_no=       '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_fin_details('+[c_client+4]+')\"><u>'+data_vec[i+4]+'</u></TD>';");
			out.println("m_total_finance='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_cur_finance=  '<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+6]+'</TD>';");		
			out.println("m_ass_officer=  '<TD WIDTH=\"8%\"   align=\"left\">'+data_vec[i+9]+'</TD>';");		
			out.println("m_officer=      '<td width=\"15%\"   align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_USER'+lineno+' style={width:70px;} maxlength=\"15\" size=\"15\" onblur=\"val_user('+lineno+')\">&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></TD>';") ;			
			//out.println("m_btn=          '<td width=\"5%\"    align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></td>';"); 
			out.println("m_remark=       '<td width=\"15%\"   align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK'+lineno+' style={width:100px;} maxlength=\"500\" size=\"15\"></TD>';") ;		// added by Sandun --- on 28/07/2008	
			out.println("m_officer_chk=  '<TD WIDTH=\"5%\"    align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"off\" onclick=\"change_val_req('+lineno+')\"></td>';");			
			out.println("m_hid_input=    '<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i]+'>';");
			
			out.println("if(j>0 && j%2==1){");
			out.println("m_writedata='<TR class=\"tr_input1\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_ass_officer+m_officer+m_remark+m_officer_chk+'</TR>'+m_hid_input;"); 
			out.println("	}");
			out.println("	else{");
			out.println("m_writedata='<TR class=\"tr_input\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_ass_officer+m_officer+m_remark+m_officer_chk+'</TR>'+m_hid_input;"); 
			out.println("	}");
			
			/*out.println("	if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");
			out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			*/
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("j=j+1;");
			out.println("i=i+10;");
			out.println("c_client=c_client+10;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");		
			out.println("}"); //End while loop
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_end.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			
			out.println("    document.Form1.TXT_NEW_COLL_OFFICER.disabled=false;"); 
			out.println("    document.Form1.BUT_HELP_NEW_COLL_OFFICER.disabled=false;"); 
			
			out.println("}");		
			
			
			
			
			out.println("  function  display_data(data_vec){");
			out.println("header();	");		
			out.println("var i=0;");
			out.println("var j=0;");
			out.println("var c_client=0;");
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_top.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			out.println("while(i<data_vec.length){");
			out.println("m_app_no='<TD WIDTH=\"15%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_app_details('+[c_client]+')\" ><u>'+data_vec[i]+'</u></TD>';");
			out.println("m_client_code='<TD WIDTH=\"10%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[c_client+7]+')\"><u>'+data_vec[i+1]+'</u></TD>';");
			//out.println("m_city_code='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+2]+'</TD>';");
			out.println("m_inq_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_inq_details('+[c_client+3]+')\"><u>'+data_vec[i+3]+'</u></TD>';");
			out.println("m_fin_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_fin_details('+[c_client+4]+')\"><u>'+data_vec[i+4]+'</u></TD>';");
			//out.println("m_co_applicant='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+4]+'</TD>';");
			//out.println("m_facility_no='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_total_finance='<TD WIDTH=\"12.5%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_cur_finance='<TD WIDTH=\"12.5%\" align=\"right\">'+data_vec[i+6]+'</TD>';");		
			//out.println("m_officer='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
			out.println("m_officer='<td width=\"15%\" align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_USER'+lineno+' maxlength=\"15\" size=\"15\" onblur=\"val_user('+lineno+')\" style=\"{width:70px;}\" >&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></TD>';") ;
			//out.println("m_btn='<td width=\"5%\" align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></td>';"); 
			out.println("m_remark='<td width=\"15%\"   align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK'+lineno+' style={width:120px;} maxlength=\"500\" size=\"15\"></TD>';") ;		// added by Sandun --- on 28/07/2008	
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i]+'>';");
			//out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i+1]+'>';");
			
			out.println("if(j>0 && j%2==1){");
			//       	out.println("<tr class=\"tr_input1\" >");
			//out.println("m_writedata='<TR class=\"tr_input1\">'+m_app_no+m_client_code+m_city_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_btn+'</TR>'+m_hid_input;"); 
			
			out.println("m_writedata='<TR class=\"tr_input1\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_remark+'</TR>'+m_hid_input;"); 
			out.println("	}");
			out.println("	else{");
			//      	out.println("<tr class=\"tr_input\" >");
			//out.println("m_writedata='<TR class=\"tr_input\">'+m_app_no+m_client_code+m_city_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_btn+'</TR>'+m_hid_input;"); 
			out.println("m_writedata='<TR class=\"tr_input\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_remark+'</TR>'+m_hid_input;"); 
			out.println("	}");
			//   out.println("m_writedata='<TR>'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+'</TR>'+m_hid_input;"); 
			out.println("	if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");
			out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("j=j+1;");
			out.println("i=i+8;");
			out.println("c_client=c_client+8;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");		
			out.println("}"); //End while loop
			
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_end.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			
			out.println("}");		
			
			
			out.println("  function  display_data2(data_vec){");
			out.println("header();	");		
			out.println("var i=0;");
			out.println("var j=0;");
			out.println("var c_client=0;");
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_top_2.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			out.println("while(i<data_vec.length){");
			
			out.println("m_branch_code='<TD WIDTH=\"20%\" align=\"left\"    ><u>'+data_vec[i+5]+'</u></TD>';");
			out.println("m_client_name='<TD WIDTH=\"20%\" align=\"left\"    ><u>'+data_vec[i+1]+'</u></TD>';");
			out.println("m_designation='<TD WIDTH=\"20%\" align=\"left\"    ><u>'+data_vec[i+2]+'</u></TD>';");
			//out.println("m_city_code='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+2]+'</TD>';");
			/*out.println("m_inq_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_inq_details('+[c_client+3]+')\"  ><u>'+data_vec[i+3]+'</u></TD>';");
			out.println("m_fin_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_fin_details('+[c_client+4]+')\" ><u>'+data_vec[i+4]+'</u></TD>';");
			//out.println("m_co_applicant='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+4]+'</TD>';");
			//out.println("m_facility_no='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_total_finance='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_cur_finance='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+6]+'</TD>';");		
			//out.println("m_officer='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
			out.println("m_ass_officer='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+9]+'</TD>';");		*/
			//out.println("m_officer='<td width=\"15%\" align=\"center\" ><input class=\"txt_input\" type=\"text\" name=TXT_USER'+lineno+' maxlength=\"15\" size=\"15\" onblur=\"val_user('+lineno+')\"style={width:70px;}>&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></TD>';") ;
			//out.println("m_officer= '<td width=\"15%\"   align=\"center\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK'+lineno+' style={width:120px;} maxlength=\"500\" size=\"15\"></TD>';") ;		// added by Sandun --- on 28/07/2008	
			//out.println("m_btn='<td width=\"5%\" align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></td>';"); 
			out.println("m_target_cases= '<td width=\"20%\"   align=\"center\" ><input class=\"txt_input\" align=\"right\" type=\"text\" value='+data_vec[i+3]+' name=TXT_TARGET_CASES'+lineno+' style={width:120px;} maxlength=\"500\" size=\"15\"></TD>';") ;		// added by Sandun --- on 28/07/2008	
			out.println("m_target_value= '<td width=\"20%\"   align=\"center\" ><input class=\"txt_input\" align=\"right\" type=\"text\" value='+data_vec[i+4]+' name=TXT_TARGET_VALUE'+lineno+' style={width:120px;} maxlength=\"500\" size=\"15\"></TD>';") ;
			out.println("m_hid_input      ='<INPUT TYPE=\"Hidden\" NAME=HID_USER_ID'+lineno+'	    VALUE='+data_vec[i]+'>';");
			out.println("m_hid_branch_code='<INPUT TYPE=\"Hidden\" NAME=TXT_BRANCH_CODE'+lineno+'	VALUE='+data_vec[i+5]+'>';");
			out.println("m_hid_client_name='<INPUT TYPE=\"Hidden\" NAME=TXT_CLIENT_NAME'+lineno+'	VALUE='+data_vec[i+1]+'>';");
			out.println("m_hid_designatiom='<INPUT TYPE=\"Hidden\" NAME=TXT_DESIGNATION'+lineno+'	VALUE='+data_vec[i+2]+'>';");
			//out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i+1]+'>';");
			out.println("if(j>0 && j%2==1){");
			//       	out.println("<tr class=\"tr_input1\" >");
			out.println("m_writedata='<TR class=\"tr_input1\">'+m_branch_code+m_client_name+m_designation+m_target_cases+m_target_value+'</TR>'+m_hid_input+m_hid_branch_code+m_hid_client_name+m_hid_designatiom;"); 
			out.println("	}");
			out.println("	else{");
			//      	out.println("<tr class=\"tr_input\" >");
			out.println("m_writedata='<TR class=\"tr_input\">'+m_branch_code+m_client_name+m_designation+m_target_cases+m_target_value+'</TR>'+m_hid_input+m_hid_branch_code+m_hid_client_name+m_hid_designatiom;"); 
			out.println("	}");
			//   out.println("m_writedata='<TR>'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+'</TR>'+m_hid_input;"); 
			
			
			out.println("	if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");
			out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("j=j+1;");
			out.println("i=i+6");
			out.println("c_client=c_client+10;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");		
			out.println("}"); //End while loop
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_end_2.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			out.println("}");		
			
			//--------------------------------------------------------------------------		
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function get_Application_numbers(m_sort_column,m_order_by_type){");
			out.println("assignState('M1')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease&ac_status=COMPLETED\";");
			
			//---commented by delanjali on 2007-09-05 for ref no 845-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			//comment by nuwan de silva on 18-10-07---------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			
			//added by nuwan de silva on 18-10-07-------------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED&finance_no=\"+document.Form1.TXT_CONTRACT_NO.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			
			out.println("function validate_data(){"); 
			out.println("return true;"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Save_Lending_targets';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_acheived_lending_targets';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_acheived_lending_targets';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Lease_Assign\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Branch Reports - Achieved Lending Targets - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Branch Reports - Achieved Lending Targets - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			
			
			out.println("}"); 
			out.println("else{}");
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function edit_data(){");
			
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_table2.innerHTML=\"\" ");
			out.println("m_table3.innerHTML=\"\" ");
			out.println("m_table_top.innerHTML=\"\" ");
			out.println("m_table_end.innerHTML=\"\" ");
			out.println("m_table_top_2.innerHTML=\"\" ");
			out.println("m_table_end_2.innerHTML=\"\" ");
			
			
			
			//  out.println("lineno_city=0;");
			
			
			out.println("m_finanace_lable='<td  width=\"30%\" align=\"left\" >Finance No</td>';");
			out.println("m_finance='<td width=\"40%\" align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_FINANCE_NO'+lineno_finanace+' maxlength=\"15\" size=\"15\" onblur=\"val_finanace_no('+lineno_finanace+')\">'+") ;
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_FINANCE_NO'+lineno_finanace+' value=\"Help\" onClick=\"help_button_finanace_no('+lineno_finanace+')\"></td><td width=\"*%\"></td>';"); 
			
			
			out.println("m_writedata='<TR >'+m_finanace_lable+m_finance+'</TR>';"); 
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}"); 
			
			
			out.println("function show_client_details(row_No) {"); 
			out.println("show_client(new_data_vec[row_No]);"); 
			out.println("}"); 
			
			
			out.println("function show_fin_details(row_No) {"); 
			out.println("show_finance_detail_drill(new_data_vec[row_No]);"); 
			out.println("}"); 
			
			out.println("function show_inq_details(row_No) {"); 
			out.println("show_inquiry_drill(new_data_vec[row_No]);"); 
			out.println("}"); 
			
			
			out.println("function show_app_details(row_No) {"); 
			out.println("show_application_detail_drill(new_data_vec[row_No]);"); 
			out.println("}"); 
			
			
			//--added by delanjali for ref 845 on 2007-09-05-----------------------------------------------------------------------------------
			
			
			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";");
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){;"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql_new2\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,m_sql,'6');"); 
			out.println("}");
			out.println("else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql_new\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,m_sql,'6');"); 
			
			out.println("}");
			
			out.println("}");	
			
			out.println("function help_value_assign_6(oBj) {"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){;"); 
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");
			out.println("else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");
			out.println("}");
			
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			//out.println("    m_sql = \"ClientSql1\";"); 
			//out.println("    m_sql = \"ClientSql1_new\";");//Mod by sandun on 25-05-2009 // commented by udara on 05-10-2012
			out.println("    m_sql = \"ClientSql1_new2\";"); // added by udara on 05-10-2012
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			//out.println("    m_sql = \"ClientSql2\";"); 
			//out.println("    m_sql = \"ClientSql_Lease\";"); 
			out.println("    m_sql = \"ClientSql_Lease_new\";");//Mod by sandun on 25-05-2009 // commented by udara on 05-10-2012
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("}");
			out.println("    HelpBox('1','10','0',Crit,m_sql,'4');"); 
			
			out.println("}");
			
			
			// added by udara on 09-10-2012
			
			out.println("function help_button_41() {"); 
			out.println("    document.Form1.hid_help_type.value=\"41\";"); 
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("    var user_location = '"+user_location_set+"'; ");
			out.println("    m_sql = \"ClientSql1_new3\";"); 
			//out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); // commented by udara on 03-12-2012
			out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+user_location+\"@\";"); // added by udara on 04-12-2012
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ "); 
			
			out.println("    var user_location = '"+user_location_set+"'; "); // out.println(" alert('"+user_location_set+"'); ");
			
			out.println("    m_sql = \"ClientSql_Lease_new1\";");
			//out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); // commented by udara on 03-12-2012
			out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+user_location+\"@\";");  // added by udara on 04-12-2012
			out.println("}");
			out.println("    HelpBox('1','10','0',Crit,m_sql,'41');"); 
			out.println("}");
			
			out.println("function help_value_assign_41(oBj) {"); 
			
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("   document.Form1.TXT_CONTRACT_NO.value=oBj.valout[12];");
			out.println("}");
			
			out.println(" else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("   document.Form1.TXT_CONTRACT_NO.value=oBj.valout[2];");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];");
			out.println("}");
			
			out.println("view()");
			out.println("}");
			
			// end by udara on 09-10-2012
			
			
			out.println("function help_value_assign_4(oBj) {"); 
			
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("}");
			
			out.println(" else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];");
			out.println("}");
			
			out.println("view()"); // commented by udara on 17-12-2012 // released by udara on 13-09-2013
			
			// commented by udara on 13-09-2013
			/*
			out.println("if(document.Form1.TXT_CONTRACT_NO.value!='')");
			out.println("   view()");
			out.println("else");
			out.println("   alert('Enter the contract number');");
			*/
			
			out.println("}");
			
			
			/*out.println("function val_user(row_No){");
			out.println("assignState('M2')");
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("m_user_val=\"TXT_USER\"+row_No");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_User&data_val=\"+document.Form1.elements[m_user_val].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		//	out.println("window.open(m_url);");
			out.println("}"); 
			*/
			
			
			out.println("function makeRequest1(obj) {");
			out.println("if(document.Form1.hid_chk_status.value==\"G4\"){");//CLIENT CODE
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_client&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			
			out.println("else if(document.Form1.hid_chk_status.value==\"G1\"){");//COLLECTION OFFICER
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_User&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_Assign_Collection_Officer&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("}");
			
			out.println("else if(document.Form1.hid_chk_status.value==\"G2\"){");//NEW COLLECTION OFFICER
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_assign_user_valiate&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_COLL_OFFICER.value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_Assign_Collection_Officer_New&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_COLL_OFFICER.value+\"&ac_status=Y\";");
			
			out.println("}");
			
			out.println("if(document.Form1.hid_chk_status.value==\"G5\"){");//FINANCE NO
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance_new&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");		
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function view(){");
			out.println("if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			//out.println("get_Application_numbers('CLIENT_NAME','ASC')	"); // commented by udara on 13-09-2013
			out.println("show_data_by_finanace_no('CLIENT_NAME','ASC');"); // added by udara on 13-09-2013
			out.println("}");
			
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("show_data_by_finanace_no('CLIENT_NAME','ASC');"); //document.Form1.TXT_FINANCE_NO.value,
			out.println("}");	
			out.println("}");
			
			
			
			out.println("function view_collection_officer_data(){");
			
			//out.println("if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("get_data_col_officer('CLIENT_NAME','ASC')	");
			//out.println("}");
			//	out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			//	out.println("show_data_by_finanace_no('CLIENT_NAME','ASC');"); //document.Form1.TXT_FINANCE_NO.value,
			//	out.println("}");	
			out.println("}");
			
			
			//-------------------------------------------------------------------------------------------------------------------------------------
			
			
			out.println("function  change_type(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_table_main.innerHTML=\"\" ");
			
			//out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
			/* 
		out.println("m_write='<tr >'+"); 
		out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CLIENT  class=div_input>Client Code</DIV></td>'+"); 
		out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CLIENT_CODE maxlength=25 size=15 onblur=assignState(\"G4\"),makeRequest1(this)>&nbsp;&nbsp;'+"); 
		out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_CLIENT value=\" ... \" onClick=\"help_button_4()\"></td>'+"); 
		//out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view()\"></td>'+"); 
		out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
		out.println("        '</tr>';"); */
			
			out.println("m_write= '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_LOCATION_CODE  class=div_input>Location Code *</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_LOCATION_CODE  maxlength=25 size=15 >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_CLIENT value=\" ... \" onClick=\"help_update()\"></td>'+"); 
			//out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view()\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
			out.println("        '</tr>';"); 
			
			// added below by udara on 09-10-2012
			/*
			out.println("m_write= m_write + '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CONTRACT_NO  class=div_input>Contract No.</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CONTRACT_NO maxlength=25 size=15 >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_CONTRACT value=\" ... \" onClick=\"help_button_41()\"></td>'+"); 
			//out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view()\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
			out.println("        '</tr>';"); 
			*/
			/*
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Location Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_LOCATION_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			// end by udara on 09-10-2012
			
			out.println("m_table_main.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
			out.println("m_write+'</table>';");
			
			
			
			
			out.println("}");
			
			
			out.println("function but_app_all(){");					
			out.println("for(i=0;i<arr_size;i++){"); 
			out.println("txt_remark=\"TXT_REMARK\"+i");
			out.println("document.Form1.elements[txt_remark].value=document.Form1.TXT_REMARK_NEW.value;"); 
			out.println("}"); 
			
			out.println("}");
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";}"); 
			out.println("    HelpBox1('1','10','5');"); 
			out.println("}");
			
			
			out.println("function HelpBox1(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"33\"){"); 
			out.println("		help_new_desc_value_assign_33();"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next1(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev1(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else {");
			out.println("	clear_data1();");
			out.println("	 }");
			out.println(" }	"); 
			out.println("if(oBj.valout[2]==' '){");//**
			//out.println("Close();"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function Prev1(Start,End,Hid_No){"); 
			out.println("    HelpBox1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next1 (Start,End,Hid_No){"); 
			out.println("    HelpBox1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_data1() {");
			out.println(" if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("document.Form1.TXT_LOCATION_CODE.value='';"); 
			out.println("document.Form1.TXT_LOCATION_CODE.focus() ;"); 
			out.println("}"); 
			out.println(" }");
			
			out.println("function help_update_value_assign_99() {"); 
			//out.println("show_data_by_finanace_no();");
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function print_report2(){");
			
			//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_acheived_lending_targets?chksql=view_report&user_location_set=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_year=\"+document.Form1.TXT_YEAR.value+\"&user_month=\"+document.Form1.TXT_MONTH.value+\"&region=\"+document.Form1.TXT_REGION.value;");  
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_acheived_lending_targets?chksql=view_report&user_location_set=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_year=\"+document.Form1.TXT_YEAR.value+\"&user_month=\"+document.Form1.TXT_MONTH.value+\"&region=\"+document.Form1.TXT_REGION.value+\"&lead_source=\"+document.Form1.LEAD_SOURCE_CATEGORY.value;"); // Added by Nishantha on 18-01-2016   
			out.println("			window.open(m_url);");
			out.println("}");
			
			out.println("</Script>");
			
			
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"change_type()\">"); //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_LEASE_ASSIGN\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Branch Reports - Achieved Lending Targets - New</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			/*
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//commented by delanjali on 2007-09-05 for ref 845
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\"),edit_data()' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			//out.println("<td width='10%'></td>");  
			//out.println("<td width='10%'></td>");  
			//out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  */
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>");  
			out.println("</table>"); 
			
			//--added by delanjali for ref 845 on 2007-09-05-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0>"); 
			
			//added by nuwan de silva on 18-10-07--------------	
			
			
			out.println("<tr >"); 
			out.println("<td  >Month</td>"); 
			//out.println("<td width='10%' ><select class='txt_input' type='text' name='TXT_ASSIGN_TYPE' onChange=\"change_type()\" maxlength='1' size='1'>");  
			out.println("<td  ><select class='txt_input' type='text' name='TXT_MONTH'>");  
			out.println("<option value='JAN' selected>January</option>");			
			out.println("<option value='FEB' selected>February</option>");	
			out.println("<option value='MAR' selected>March</option>");	
			out.println("<option value='APR' selected>April</option>");	
			out.println("<option value='MAY' selected>May</option>");	
			out.println("<option value='JUN' selected>June</option>");	
			out.println("<option value='JUL' selected>July</option>");	
			out.println("<option value='AUG' selected>August</option>");	
			out.println("<option value='SEP' selected>September</option>");	
			out.println("<option value='OCT' selected>October</option>");	
			out.println("<option value='NOV' selected>November</option>");	
			out.println("<option value='DEC' selected>December</option>");		
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("<tr>"); 
			out.println("<td>&nbsp;&nbsp;&nbsp;</td>"); 
			out.println("<td>&nbsp;&nbsp;&nbsp;</td>"); 
			out.println("</tr>");
						
			out.println("<tr>"); 
			out.println("<td  >Year</td>"); 
			out.println("<td  ><select class='txt_input' type='text' name='TXT_YEAR' onChange=\"change_type()\" maxlength='1' size='1'>");  
			out.println("<option value='2014' selected>2014</option>");			
			out.println("<option value='2015' selected>2015</option>");	
			out.println("<option value='2016' selected>2016</option>");	
			out.println("<option value='2017' selected>2017</option>");
			out.println("<option value='2018' >2018</option>");
			out.println("<option value='2019' >2019</option>");
			out.println("<option value='2020' >2020</option>");
			out.println("<option value='2021' >2021</option>");
			out.println("<option value='2022' >2022</option>");
			out.println("<option value='2023' >2023</option>");
			out.println("<option value='2024' >2024</option>");
			out.println("<option value='2025' >2025</option>");
			out.println("<option value='2026' >2026</option>");
			out.println("<option value='2027' >2027</option>");
			out.println("<option value='2028' >2028</option>");
			out.println("<option value='2029' >2029</option>");
			out.println("<option value='2030' >2030</option>");
			out.println("<option value='2031' >2031</option>");
			out.println("<option value='2032' >2032</option>");
			out.println("<option value='2033' >2033</option>");
			out.println("<option value='2034' >2034</option>");
			out.println("<option value='2035' >2035</option>");
			out.println("<option value='2036' >2036</option>");
			out.println("<option value='2037' >2037</option>");
			out.println("<option value='2038' >2038</option>");
			out.println("<option value='2039' >2039</option>");
			out.println("<option value='2040' >2040</option>");
			out.println("<option value='2041' >2041</option>");
			out.println("<option value='2042' >2042</option>");
			out.println("<option value='2043' >2043</option>");
			out.println("<option value='2044' >2044</option>");
			out.println("<option value='2045' >2045</option>");
			out.println("<option value='2046' >2046</option>");
			out.println("<option value='2047' >2047</option>");
			out.println("<option value='2048' >2048</option>");
			out.println("<option value='2049' >2049</option>");
			out.println("<option value='2050' >2050</option>");
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>"); 
			
			out.println("</table>");  
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0 >"); 
			out.println("<tr>");  
			out.println("<td ><DIV ID='m_table_main'></DIV></td>"); //width=\"100%\"
			out.println("</tr>"); 
			
		
			
						
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0 >"); 
			
			out.println("<tr>"); 
			out.println("<td>&nbsp;&nbsp;&nbsp;</td>"); 
			out.println("<td>&nbsp;&nbsp;&nbsp;</td>"); 
			out.println("</tr>");
			
		    out.println("<tr>");  
			out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
			out.println("<td ><select class='txt_input' name='TXT_REGION'>");  
	        out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
			stmt2 = conn.createStatement();
					
			rs2 = stmt2.executeQuery (" SELECT REGIONS_CODE, REGIONS_DESC  "+
					" FROM "+m_schema_name+".AF_CO_MAS_REGIONS "+
					" WHERE ACTIVE_STATUS='Y' "+
					" ORDER BY REGIONS_DESC ");
				
			while(rs2.next()){
					out.println("  <OPTION value=\""+rs2.getString(1)+"\">"+rs2.getString(2)+"</OPTION>");
			}
				
			out.println(" 	   </select>");
			out.println(" </td>"); 
			out.println("</tr>"); 
			
			//Added By Nishantha Ekanayake On 18-01-2016
			
			out.println("<tr>"); 
			out.println("<td>&nbsp;&nbsp;&nbsp;</td>"); 
			out.println("<td>&nbsp;&nbsp;&nbsp;</td>"); 
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			//out.println("<td width='20%' >Lead Source Category</td>");
			out.println("<td width='20%' ><DIV id='LEAD_SOURCE_CATEGORY'  class=div_input>Lead Source Category </DIV></td>"); 
			out.println("<td >");				
			out.println("<select name=\"LEAD_SOURCE_CATEGORY\" class=\"txt_input\"  >");
				rs = stmt.executeQuery(CO_method.getLeadSourceCat(m_schema_name,"Y",""));
				boolean more = rs.next();
				out.println("<OPTION value=\"ALL\" > All </option>");
				while(more){
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					more = rs.next();	
				}	
				out.println("</SELECT>");
				out.println("</td>");
				out.println("</tr>"); 
				//End Nishantha
				
				out.println("</table>");
			//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			out.println("<table align='center' width='100%' class='table'>"); 
			
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_top'></DIV></td>");
			out.println("</tr>"); 
			
			out.println("<tr>");  
			out.println("<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"print_report2()\">");	
			out.println("</tr>"); 
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_end'></DIV></td>");
			out.println("</tr>"); 
			
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table2'></DIV></td>");
			out.println("</tr>"); 
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_top_2'></DIV></td>");
			out.println("</tr>"); 
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table3'></DIV></td>");
			out.println("</tr>"); 
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_end_2'></DIV></td>");
			out.println("</tr>"); 	
			
			
			out.println("</table>");
			
			
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			
			out.println("</td></tr>");  
			
			
			
			out.println("</form>");
			out.println("</body>");
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			
			out.println("</html>");
			//    }
			
			//=========================================================================================================================			
			
			//out.close();
			//conn.close();
			//this.destroy();
		}
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}