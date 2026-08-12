//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_Collection_Movement_Report
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import java.math.*; 


public class LAKDL_AF_MISF_Past_month_payment_summary_rpt_detail extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
		Statement stmt=null,stmt1=null,stmt3=null;
		ResultSet rs=null,rs1=null,rs3=null;
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			
			stmt  = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt3 = conn.createStatement ();

			
			// added by udara 15-02-2016
			conn.setAutoCommit(false);


			if(m_chksql.equals("print_report_new")){	

				
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_string ="";
				String m_officer_name="";
				String m_location_desc="";
				String m_location_string="";
				String m_user_name=""; 
				String m_sys_date=""; 
				String m_cr_off_name=""; 
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; 
				String m_cr_officer = ""; 
				String m_cr_officer_string ="";
				
				String  m_active_status = "";  
				String  m_active_status_string = ""; 
				
				String m_region=""; 
				String m_region_string ="";
				
				String m_finance_no = "";
				String m_finance_no_string = "";
				
				String m_arrears_status = "";
				String m_arrears_status_string = "";

				String m_chk_veh_no = req.getParameter("chk_veh_no");
				String m_chk_cr_officer = req.getParameter("chk_cr_officer");
				String m_chk_coll_officer = req.getParameter("chk_coll_officer");
				String m_chk_rent_date = req.getParameter("chk_rent_date");
				String m_chk_age = req.getParameter("chk_age");
				
				if(req.getParameter("arrears_status").equals("ALL")){
					m_arrears_status=req.getParameter("arrears_status").trim();
					m_arrears_status_string = "";
				}
				else{
					m_arrears_status=req.getParameter("arrears_status").trim();
					m_arrears_status_string = " AND A.ARREARS_STATUS = '"+m_arrears_status+"' ";
				}
				
				if(req.getParameter("finance_no")!=null ){
					m_finance_no=req.getParameter("finance_no").trim();
					if(!m_finance_no.equals(""))
						m_finance_no_string=" AND A.FINANCE_NO = '"+m_finance_no+"' ";
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location_id")!=null ){
					m_location=req.getParameter("location_id").trim();
					if(!m_location.equals(""))
						m_location_string = " AND A.BRANCH_CODE = '"+m_location+"' ";
				}
				
				if(req.getParameter("mkt_officer")!=null ){
					m_officer=req.getParameter("mkt_officer").trim();
					if(!m_officer.equals(""))
						m_officer_string = " AND A.COLLECTION_OFFICER = '"+m_officer+"' ";
				}
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
					if(!m_cr_officer.equals(""))
						m_cr_officer_string = " AND NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(A.APPLICATION_NO),' ') =  '"+m_cr_officer+"' ";
				}

				if(req.getParameter("perform_stat")!=null ){
					m_perform_status=req.getParameter("perform_stat").trim();
				}
				
				
				if(req.getParameter("active_yard_status")!=null ){
					m_active_status=req.getParameter("active_yard_status").trim();
				}
				
				if(!m_active_status.equals("A")){
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; 
				}
				
				if(req.getParameter("region").equals("NOT_SELECT") ){
					m_region = req.getParameter("region").trim();
					m_region_string = "";
				}
				else{
					m_region = req.getParameter("region").trim();
					m_region_string = " AND A.REGION = '"+m_region+"' ";
				}
				
				
				
				String m_from_date_run = "";
				
				if(req.getParameter("from_date_run")!=null ){
					m_from_date_run = req.getParameter("from_date_run").trim();
				}
				
				String m_to_date_run = "";
				
				if(req.getParameter("to_date_run")!=null ){
					m_to_date_run = req.getParameter("to_date_run").trim();
				}
				
				String m_from_date_act = "";
				
				if(req.getParameter("from_date_act")!=null ){
					m_from_date_act = req.getParameter("from_date_act").trim();
				}
				
				String m_to_date_act = "";
				
				if(req.getParameter("to_date_act")!=null ){
					m_to_date_act = req.getParameter("to_date_act").trim();
				}
				
				//stmt = conn.createStatement ();
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Past Month Payment Summary Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function print_report_new_drill(finance_no,ent_user,from_date,to_date){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=print_report_new_drill&finance_no=\"+finance_no+\"&ent_user=\"+ent_user+\"&from_date=\"+from_date+\"&to_date=\"+to_date;");
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");


				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	

				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
				}

				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'All'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+ 
					" TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI PM'), "+ 
					" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_cr_officer+"'),'-') "+ 
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
					m_user_name = rs.getString(5); 
					m_sys_date = rs.getString(6); 
					m_cr_off_name = rs.getString(7); 
				}
				
				String Sql_data="";

				//stmt1 = conn.createStatement ();
				//stmt3 = conn.createStatement ();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr><td align=center ><b><u> Past Months Payment Summary Report </u></b></td></tr>");
				out.println("</table>");


				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				
				out.println("<tr >");
				out.println("<td width='20%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Finance No :- </td>"); 
				if(m_finance_no.equals(""))
					out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else
					out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_finance_no+"</td>");
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='20%' ><b> Date As At :- </b></td>");
				out.println("<td width='*%' > &nbsp; <b> "+m_date+" </b> </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='20%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Branch :- </td>"); 
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_location_desc+"</td>"); 
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='20%' ><b> Months Range (From) :- </b></td>");
				out.println("<td width='*%' > &nbsp; <b> "+m_from_date_run+" </b> </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='20%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Marketing Officer :- </td>"); 
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='20%' ><b> Months Range (To) :- </b></td>");
				out.println("<td width='*%' > &nbsp; <b>  "+m_to_date_run+" </b> </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='20%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Credit Officer :- </td>"); 
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cr_off_name+"</td>"); 
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='20%' ><b> Activated Contracts (From) :- </b></td>");
				out.println("<td width='*%' > &nbsp; <b> "+m_from_date_act+" </b> </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='20%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(m_perform_status.equals(""))
					out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_perform_status.equals("PERFORM"))
					out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(m_perform_status.equals("NPERFORM"))
					out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Non Perform</td>");
				
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='20%' ><b> Activated Contracts (To) :- </b></td>");
				out.println("<td width='*%' > &nbsp; <b> "+m_to_date_act+" </b> </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='20%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active/Yard Vehicles :- </td>"); 
				
				if(m_active_status.equals("A"))
				 	out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_active_status.equals("Y"))
					out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active</td>");
				else if(m_active_status.equals("N"))
					out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Yard Vehicles</td>");

				String m_region_desc = "All";
				
				if(!(m_region.equals("NOT_SELECT"))){ 
						String qry = " SELECT NVL(R.REGIONS_DESC,'All') FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
							
						rs3=stmt3.executeQuery(qry);
							
						boolean more_1=rs3.next();
						
						
						if(more_1){						
							m_region_desc = rs3.getString(1);
						}
			    }
				
				
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='*%' ><b> Region :- "+m_region_desc+" </b> </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='20%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Current Date :- </td>"); 
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_sys_date+"</td>"); 
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='20%' ><b> &nbsp; </b></td>");
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				
				out.println("</table >");
				
				
				out.println("<br>");

				out.println("<table align=\"center\" border=\"0\" class=\"table\">");

				

				int month_count = 0;
				
				rs1=stmt1.executeQuery(" "+
					" SELECT "+
						" TO_CHAR(A.RUNING_TO_DATE,'Month-YYYY') "+ // 1
							" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_RUN_DATES A "+
							" WHERE A.ENT_USER = '"+m_username+"' "+
					" ");
				
				
				//out.println("<td  align='right' width='5%' ><b> Excess Rental </b></td>"); // Excess Rental
				
				while(rs1.next()){
					month_count = month_count + 1;
					
				}

				// Headings End
				
				
				int counts = 0;
				
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt  = conn.createStatement ();
				}
				
				rs=stmt.executeQuery(" "+
				//out.println(" "+
					" SELECT "+
						" A.FINANCE_NO, "+ // 1
						" NVL(A.VEHICLE_NO,'-'), "+ // 2
						" NVL(A.CR_OFFICER,'-'), "+ // 3
						" NVL(A.COLLECTION_OFFICER,'-'), "+ // 4
						" NVL(A.RENTAL_AMOUNT,0), "+ // 5
						" NVL(A.RENTAL_DATE,'-'), "+ // 6
						" NVL(A.TOTAL_ARREARS,0),  "+ // 7
						" NVL(A.PERIOD,0),  "+ // 8
						" TO_CHAR(A.ACTIVATED_DATE,'Month YYYY'), "+ // 9
						" NVL(A.FIRST_MONTH_EXCESS,0), "+ // 10
						" A.APPLICATION_NO, "+
						" A.CLIENT_CODE, "+
						" A.BRANCH_CODE, "+
						" A.APPLICATION_STATUS, "+
						" A.ACTIVATED_DATE, "+
						" A.PERFORM_STATUS, "+
						" A.YARD_STATUS, "+
						" A.REGION, "+
						" NVL(A.CR_OFFICER_NAME,'-'), "+ // 19
						" NVL(A.COLLECTION_OFFICER_NAME,'-'), "+ // 20
						" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+ // 21
						" NVL(CR_OFFICER,'-') CR_OFFICER, "+ // 22 added by udara 06-12-2024 
						" NVL(COLLECTION_OFFICER,'-') COLLECTION_OFFICER "+ // 23 added by udara 06-12-2024
						
							" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_FINANCE_DET A "+
							" WHERE A.ENT_USER = '"+m_username+"' "+
							//" AND A.FINANCE_NO LIKE '"+m_finance_no+"%' "+
			                //" AND A.BRANCH_CODE LIKE '"+m_location+"%' "+
			                //" AND A.COLLECTION_OFFICER LIKE '"+m_officer+"%' "+
			                //" AND NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(A.APPLICATION_NO),' ') LIKE  '"+m_cr_officer+"%' "+
							" "+m_finance_no_string+" "+
							" "+m_location_string+" "+
							" "+m_officer_string+" "+
							" "+m_cr_officer_string+" "+
							" "+m_active_status_string+" "+
							" "+m_arrears_status_string+" "+
							" "+m_region_string+" "+
							" ORDER BY A.ACTIVATED_DATE "+
							" ");

				String m_month_value = "";
				//int colspan_count = (month_count*2) + 11; // commented by udara 06-12-2024
				int colspan_count = (month_count*2) + 12;
				
				int j=0;
				
				while(rs.next()){
					
					if(m_month_value.equals("")){
						m_month_value = rs.getString(9);
						out.println("<tr class=pdn_txtpos2 ><td colspan="+colspan_count+" align=left ><b>"+rs.getString(9)+"</b></td></tr>");
						j=0;
						
						// added by udara 17-12-2015
						// Headings Start
						out.println("<tr class=pdn_txtpos2 >");
						
						out.println("<td width='1%' ><b> No. </b></td>");
						out.println("<td width='5%' ><b> Agreement No </b></td>"); // Agreement No
						
						if(m_chk_veh_no.equals("Y"))
							out.println("<td width='5%' ><b> Vehicle&nbsp;No </b></td>"); // Vehicle No
						
						// commented by udara 06-12-2024
						/*
						if(m_chk_cr_officer.equals("Y"))
							out.println("<td width='5%' ><b> Credit&nbsp;Officer </b></td>"); // Credit Officer
						
						if(m_chk_coll_officer.equals("Y"))
							out.println("<td width='5%' ><b> Collection&nbsp;Officer </b></td>"); // Collection Officer
						*/
						
						// added by udara 06-12-2024
						if(m_chk_cr_officer.equals("Y")){
							out.println("<td width='3%' ><b> Credit&nbsp;Officer </b></td>");
						    out.println("<td width='2%' ><b> Credit&nbsp;Officer&nbsp;Code </b></td>");
						}
						
						if(m_chk_coll_officer.equals("Y")){
							out.println("<td width='3%' ><b> Collection&nbsp;Officer </b></td>");
							out.println("<td width='2%' ><b> Collection&nbsp;Officer&nbsp;Code </b></td>");
						}
						// end by udara 06-12-2024
		
						out.println("<td width='5%' align='right' ><b> Rental&nbsp;Amount </b></td>"); // Rental Amount
		
						
						if(m_chk_rent_date.equals("Y"))
							out.println("<td width='5%' align='center' ><b> R/D </b></td>");

						
						rs1=stmt1.executeQuery(" "+
							" SELECT "+
								" TO_CHAR(A.RUNING_TO_DATE,'Month-YYYY') "+ // 1
									" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_RUN_DATES A "+
									" WHERE A.ENT_USER = '"+m_username+"' "+
							" ");
						
						
						out.println("<td  align='right' width='5%' ><b> Excess Rental </b></td>"); // Excess Rental
						
						while(rs1.next()){

								out.println("<td  align='right' width='5%' ><b> &nbsp; </b></td>");
								out.println("<td  align='right' width='5%' ><b> "+rs1.getString(1)+" </b></td>");		

							
							
						}
						
						out.println("<td width='5%' align='right' ><b> Arrears/Excess </b></td>");
						
						if(m_chk_age.equals("Y"))
							out.println("<td width='5%' align='center' ><b> Age </b></td>");
						
						out.println("</tr>");
						// Headings End

						
					}
					else if(!m_month_value.equals(rs.getString(9))){
						
						
						out.println("<tr><td colspan="+colspan_count+" > &nbsp; </td></tr>");
						out.println("<tr class=pdn_txtpos2 ><td colspan="+colspan_count+" align=left ><b>"+rs.getString(9)+"</b></td></tr>");
						m_month_value = rs.getString(9);
						j=0;
						counts = 0; 
						
						
						// 16-12-2015
						// Headings Start
						out.println("<tr class=pdn_txtpos2 >");
						
						out.println("<td width='1%' ><b> No. </b></td>");
						out.println("<td width='5%' ><b> Agreement No </b></td>"); // Agreement No
						
						if(m_chk_veh_no.equals("Y"))
							out.println("<td width='5%' ><b> Vehicle&nbsp;No </b></td>"); // Vehicle No
						
						// commented by udara 06-12-2024
						/*
						if(m_chk_cr_officer.equals("Y"))
							out.println("<td width='5%' ><b> Credit&nbsp;Officer </b></td>"); // Credit Officer
						
						if(m_chk_coll_officer.equals("Y"))
							out.println("<td width='5%' ><b> Collection&nbsp;Officer </b></td>"); // Collection Officer
						*/
						
						// added by udara 06-12-2024
						if(m_chk_cr_officer.equals("Y")){
							out.println("<td width='3%' ><b> Credit&nbsp;Officer </b></td>");
						    out.println("<td width='2%' ><b> Credit&nbsp;Officer&nbsp;Code </b></td>");
						}
						
						if(m_chk_coll_officer.equals("Y")){
							out.println("<td width='3%' ><b> Collection&nbsp;Officer </b></td>");
							out.println("<td width='2%' ><b> Collection&nbsp;Officer&nbsp;Code </b></td>");
						}
						// end by udara 06-12-2024
		
						out.println("<td width='5%' align='right' ><b> Rental&nbsp;Amount </b></td>"); // Rental Amount
		
						
						if(m_chk_rent_date.equals("Y"))
							out.println("<td width='5%' align='center' ><b> R/D </b></td>");

						
						rs1=stmt1.executeQuery(" "+
							" SELECT "+
								" TO_CHAR(A.RUNING_TO_DATE,'Month-YYYY') "+ // 1
									" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_RUN_DATES A "+
									" WHERE A.ENT_USER = '"+m_username+"' "+
							" ");
						
						
						out.println("<td  align='right' width='5%' ><b> Excess Rental </b></td>"); // Excess Rental
						
						while(rs1.next()){

								out.println("<td  align='right' width='5%' ><b> &nbsp; </b></td>");
								out.println("<td  align='right' width='5%' ><b> "+rs1.getString(1)+" </b></td>");		

							
							
						}
						
						out.println("<td width='5%' align='right' ><b> Arrears/Excess </b></td>");
						
						if(m_chk_age.equals("Y"))
							out.println("<td width='5%' align='center' ><b> Age </b></td>");
						
						out.println("</tr>");
						// Headings End
						
						
					}
					
					counts = counts + 1;
					
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
				
					out.println("<td width='1%' > "+counts+" </td>");
					
					out.println("<td width='5%' STYLE='text-align:left; cursor:hand;' onclick=\"show_transaction_history_new('','"+rs.getString(1)+"');\" ><u>"+rs.getString(1)+"</u></td>"); 
					
					if(m_chk_veh_no.equals("Y"))
						out.println("<td width='5%' > "+rs.getString(2)+" </td>");
					
					// commented by udara 06-12-2024
					/*
					if(m_chk_cr_officer.equals("Y"))
						out.println("<td width='5%' > "+rs.getString(19)+" </td>");
					
					if(m_chk_coll_officer.equals("Y"))
						out.println("<td width='5%' > "+rs.getString(20)+" </td>");
					*/
					
					// added by udara 06-12-2024
						if(m_chk_cr_officer.equals("Y")){
							out.println("<td width='5%' > "+rs.getString(19)+" </td>");
						    out.println("<td width='5%' > "+rs.getString(22)+" </td>");
						}
						
						if(m_chk_coll_officer.equals("Y")){
							out.println("<td width='5%' > "+rs.getString(20)+" </td>");
							out.println("<td width='5%' > "+rs.getString(23)+" </td>");
						}
						// end by udara 06-12-2024
					
					
					out.println("<td width='5%' align='right' > "+nf.format(rs.getDouble(5))+" </td>");
					
					if(m_chk_rent_date.equals("Y"))
						out.println("<td width='5%' align='center' > "+rs.getString(6)+" </td>");
					
					String m_from_run_date = "";
					String m_to_run_date = "";
					
					if(conn==null){
						conn = m_sn_methods.met_user_validate(req); 
						stmt1  = conn.createStatement ();
					}
					
					rs1=stmt1.executeQuery(" "+
					" SELECT "+
					    " TO_CHAR(A.RUNING_FROM_DATE,'DD-MM-YYYY'), "+ // 1
						" TO_CHAR(A.RUNING_TO_DATE,'DD-MM-YYYY') "+ // 2
							" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_RUN_DATES A "+
							" WHERE A.ENT_USER = '"+m_username+"' "+
					" ");
				
					int month_count_first_month = 0;
					
					out.println("<td  align='right' > "+nf.format(rs.getDouble(10))+" </td>"); // first month value of master table
					
					while(rs1.next()){
						
							month_count_first_month = month_count_first_month + 1;
							
							m_from_run_date = rs1.getString(1);
							m_to_run_date = rs1.getString(2);
						
							
							double receipts_amount = 0;
							int receipts_count = 0;
							String rental_day = "-";
							String date_vissibility = "";
						
							/*
							
							rs3=stmt3.executeQuery(" "+
								" SELECT "+
								    " SUM(NVL(A.REC_AMOUNT,0)) , COUNT(REC_NO) "+ // 1
										" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_SUM_RECEIPTS A "+
										" WHERE A.FINANCE_NO = '"+rs.getString(1)+"' "+
										" AND A.ENT_USER = '"+m_username+"' "+
										" AND TRUNC(A.EFF_VALDATE) >= TO_DATE('"+m_from_run_date+"','DD-MM-YYYY')  "+
										" AND TRUNC(A.EFF_VALDATE) <= TO_DATE('"+m_to_run_date+"','DD-MM-YYYY')  "+
							" ");
							
							double receipts_amount = 0;
							int receipts_count = 0;
							
							if(rs3.next()){
								receipts_amount = rs3.getDouble(1);
								receipts_count = rs3.getInt(2);
							}
						

							String rental_day = "-";
						
							rs3=stmt3.executeQuery(" "+
								" SELECT "+
								    " A.INSTALLMENT_NO "+ // 1
										" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_RENTAL_INFO A "+
										" WHERE A.FINANCE_NO = '"+rs.getString(1)+"' "+
										" AND A.ENT_USER = '"+m_username+"' "+
										" AND TRUNC(A.RENTAL_DATE,'MONTH') = TRUNC(TO_DATE('"+m_from_run_date+"','DD-MM-YYYY'),'MONTH')  "+
							" ");
							
							if(rs3.next()){
								rental_day = rs3.getString(1);
							}
						
						
						
							rs3=stmt3.executeQuery(" "+
								" SELECT "+m_schema_name+".AF_GET_PAST_MONTH_DAY_STATUS('"+rs.getString(21)+"','"+m_from_run_date+"') "+
									" FROM  DUAL "+
								" ");
							
							String date_vissibility = "";
							
							if(rs3.next()){
								date_vissibility = rs3.getString(1);
							}
							
							*/
							
							
							if(conn==null){
								conn = m_sn_methods.met_user_validate(req); 
								stmt3  = conn.createStatement ();
							}
							
							rs3=stmt3.executeQuery(" "+
								
										" SELECT A.REC_AMOUNT, A.REC_COUNT, B.INSTALLMENT_NO, "+m_schema_name+".AF_GET_PAST_MONTH_DAY_STATUS('"+rs.getString(21)+"','"+m_from_run_date+"') "+
										" FROM "+
										" ( "+
										        " SELECT SUM(NVL(REC_AMOUNT,0)) REC_AMOUNT, COUNT(REC_NO) REC_COUNT "+ // 1
												" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_SUM_RECEIPTS "+
												" WHERE FINANCE_NO = '"+rs.getString(1)+"' "+
												" AND ENT_USER = '"+m_username+"' "+
												" AND TRUNC(EFF_VALDATE) >= TO_DATE('"+m_from_run_date+"','DD-MM-YYYY')  "+
												" AND TRUNC(EFF_VALDATE) <= TO_DATE('"+m_to_run_date+"','DD-MM-YYYY')  "+
										" ) A, "+
										
										" ( "+
										" SELECT "+
								        " INSTALLMENT_NO "+ 
										" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_RENTAL_INFO "+
										" WHERE FINANCE_NO = '"+rs.getString(1)+"' "+
										" AND ENT_USER = '"+m_username+"' "+
										" AND TRUNC(RENTAL_DATE,'MONTH') = TRUNC(TO_DATE('"+m_from_run_date+"','DD-MM-YYYY'),'MONTH')  "+
										" ) B "+
										
							" ");
							
							if(rs3.next()){
								receipts_amount = rs3.getDouble(1);
								receipts_count = rs3.getInt(2);
								rental_day = rs3.getString(3);
								date_vissibility = rs3.getString(4);
							}
						
						
							if(!date_vissibility.equals("BLANK")){
								out.println("<td   onclick=\"\" STYLE='text-align:center;' > "+rental_day+" </td>");
								out.println("<td   STYLE='text-align:right; cursor:hand;' onclick=\"print_report_new_drill('"+rs.getString(1)+"','"+m_username+"','"+m_from_run_date+"','"+m_to_run_date+"');\" ><u> "+nf.format(receipts_amount)+" </u></td>");
							}
							else{
								out.println("<td> &nbsp; </td>");
								out.println("<td> &nbsp; </td>");
							}

						
						
						
					}
					
					out.println("<td width='5%' align='right' > "+nf.format(rs.getDouble(7))+" </td>");
					
					if(m_chk_age.equals("Y"))
						out.println("<td width='5%' align='center' > "+rs.getInt(8)+" </td>");
					
					out.println("</tr>");
					
					j=j+1;
					
				} // main while end
				
				out.println("</table>");
				
				
				// end by udara 18-11-2013
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}

			//conn.commit(); // added by udara 03-02-2016
			
			//}
		}
		
		
		catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){} // commented by udara 02-02-2016
		}
		finally{
			/*
			if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			*/
			
			// added by udara 15-02-2016
			try{conn.setAutoCommit(true); conn.commit(); }catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			// added by udara 15-02-2016
			
		}
	}
}
