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


public class LAKDL_AF_MISF_Past_month_payment_summary_rpt extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
		Statement stmt=null,stmt1=null,stmt2=null,stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs_drill_new=null;
		
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
			
			String m_sort_column   = "FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			// added by udara 15-02-2016
			conn.setAutoCommit(false);
			
			stmt2 = conn.createStatement ();
			stmt  = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt3 = conn.createStatement ();
			
			if(m_chksql.equals("run_report")){ 
				/*
				String m_date=req.getParameter("date");
				String m_location_id=req.getParameter("location_id");
				String m_user_id=req.getParameter("user_id");
				String m_finance_no = req.getParameter("finance_no");
				String m_cr_offic   = req.getParameter("cr_officer");
				String m_perform_stat   = req.getParameter("perform_stat"); // added by udara 21-11-2013
				String m_active_yard_status   = req.getParameter("active_yard_status");   // Added by Samith Dilshan on 27-05-2015
				String m_region   = req.getParameter("region"); // added by udara 13-08-2015
				*/
				
				/*
				String m_FROM_DATE_FOR_RUN = "01-01-2015";
				String m_TO_DATE_FOR_RUN  = "16-10-2015";
				String m_FROM_DATE_FOR_ACTIVATED  = "01-01-2000";
				String m_TO_DATE_FOR_ACTIVATED  = "16-10-2015";
				String m_AS_AT_DATE   = "16-10-2015";  
				String m_BRANCH_ID  = "";
				String m_COLLECTOR_ID  = "";
				String m_CR_OFFICER  = "";
				String m_FINANCE_NO  = "";
				String m_PERFORM  = "";
				String m_ACTIVE_YARD_STATUS  = "";
				String m_REGION_CODE  = "";
				String m_USER  = m_username;
				*/
				
				String m_FROM_DATE_FOR_RUN = req.getParameter("from_date_run");
				String m_TO_DATE_FOR_RUN  = req.getParameter("to_date_run");
				String m_FROM_DATE_FOR_ACTIVATED  = req.getParameter("from_date_act");
				String m_TO_DATE_FOR_ACTIVATED  = req.getParameter("to_date_act");
				String m_AS_AT_DATE   = req.getParameter("date");
				String m_BRANCH_ID  = req.getParameter("location_id");
				String m_COLLECTOR_ID  = req.getParameter("mkt_officer");
				String m_CR_OFFICER  = req.getParameter("cr_officer");
				String m_FINANCE_NO  = req.getParameter("finance_no");
				String m_PERFORM  = req.getParameter("perform_stat");
				String m_ACTIVE_YARD_STATUS  = req.getParameter("active_yard_status");
				String m_REGION_CODE  = req.getParameter("region");
				String m_USER  = m_username;	
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_PAST_MONTH_PAY_SUM_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;"); // added by udara 13-08-2015
					
					callstmt1.setString(1,m_FROM_DATE_FOR_RUN);
					callstmt1.setString(2,m_TO_DATE_FOR_RUN);
					callstmt1.setString(3,m_FROM_DATE_FOR_ACTIVATED);
					callstmt1.setString(4,m_TO_DATE_FOR_ACTIVATED);
					callstmt1.setString(5,m_AS_AT_DATE);
					callstmt1.setString(6,m_BRANCH_ID);
					callstmt1.setString(7,m_COLLECTOR_ID);
					callstmt1.setString(8,m_CR_OFFICER);
					callstmt1.setString(9,m_FINANCE_NO);
					callstmt1.setString(10,m_PERFORM);
					callstmt1.setString(11,m_ACTIVE_YARD_STATUS);
					callstmt1.setString(12,m_REGION_CODE);
					callstmt1.setString(13,m_USER);
					
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				String m_user_location = "";
				
				//stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'),"+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Past Month Payment Summary Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					
					m_user_location = rs2.getString(4);
					
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					
					out.println("document.Form1.VAL_DAY_FROM_DATE_FOR_RUN.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN.value='"+rs2.getString(3)+"';");
					
					out.println("document.Form1.VAL_DAY_TO_DATE_FOR_RUN.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH_TO_DATE_FOR_RUN.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR_TO_DATE_FOR_RUN.value='"+rs2.getString(3)+"';");
					
					out.println("document.Form1.VAL_DAY_FROM_DATE_FOR_ACT.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT.value='"+rs2.getString(3)+"';");
					
					out.println("document.Form1.VAL_DAY_TO_DATE_FOR_ACT.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH_TO_DATE_FOR_ACT.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR_TO_DATE_FOR_ACT.value='"+rs2.getString(3)+"';");
					
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				out.println("}"); 
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				
				out.println("function run_report() {");
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				
				out.println("		m_date = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println(" 		finance_no = document.Form1.TXT_FINANCE.value;");
				out.println(" 		m_cr_officer = document.Form1.MKT_OFFICER.value;");
				out.println("       m_perform_stat = document.Form1.TXT_PERFORM_STATUS.value;"); 
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); 
				out.println(" 		m_region = document.Form1.TXT_REGION.value;"); 
				
				out.println(" 		m_mkt_officer = document.Form1.TXT_USER.value;");
				
				out.println(" 		m_from_date_run = document.Form1.VAL_DAY_FROM_DATE_FOR_RUN.value+'-'+document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN.value+'-'+document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN.value;"); 
				out.println(" 		m_to_date_run   = document.Form1.VAL_DAY_TO_DATE_FOR_RUN.value+'-'+document.Form1.VAL_MONTH_TO_DATE_FOR_RUN.value+'-'+document.Form1.VAL_YEAR_TO_DATE_FOR_RUN.value;"); 
				
				out.println(" 		m_from_date_act = document.Form1.VAL_DAY_FROM_DATE_FOR_ACT.value+'-'+document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT.value+'-'+document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT.value; "); 
				out.println(" 		m_to_date_act   = document.Form1.VAL_DAY_TO_DATE_FOR_ACT.value+'-'+document.Form1.VAL_MONTH_TO_DATE_FOR_ACT.value+'-'+document.Form1.VAL_YEAR_TO_DATE_FOR_ACT.value; ");
				
				out.println("       m_user_location = '"+m_user_location+"'; "); 
				
				out.println("       if(m_user_location=='HO'){ ");
				out.println("          m_user_location = document.Form1.TXT_LOCATION_CODE.value;    ");
				out.println("       } ");
				out.println("       else{ ");
				out.println("          m_user_location = '"+m_user_location+"';    ");
				out.println("       } ");
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&cr_officer=\"+m_cr_officer+\"&active_yard_status=\"+m_active_yard_status+\"&perform_stat=\"+m_perform_stat+\"&region=\"+m_region;"); // added by udara 13-08-2015 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+m_user_location+\"&mkt_officer=\"+m_mkt_officer+\"&cr_officer=\"+m_cr_officer+\"&active_yard_status=\"+m_active_yard_status+\"&perform_stat=\"+m_perform_stat+\"&region=\"+m_region+\"&from_date_run=\"+m_from_date_run+\"&to_date_run=\"+m_to_date_run+\"&from_date_act=\"+m_from_date_act+\"&to_date_act=\"+m_to_date_act;"); 
				
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				//out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('Past Month Payment Summary Report is generated. Use View Report button to get the view.');"); // out.println("			alert('The report is generated. Use View Report button to get the view.');"); // added by udara 25-03-2014
				//out.println("			print_report2();"); // commented by udara 25-03-2014 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("	clearTimeout(timerID);");
				out.println("	m_table.innerHTML=\"\";");
				
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				//out.println("		m_officer=document.Form1.TXT_USER.value;");
				out.println("       m_region   = document.Form1.TXT_REGION.value; "); 
				out.println("       var perform_status = document.Form1.TXT_PERFORM_STATUS.value;     ");
				out.println("       m_cr_officer=document.Form1.MKT_OFFICER.value;");
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); 
				out.println(" 		finance_no = document.Form1.TXT_FINANCE.value;");
				out.println("       m_perform_stat = document.Form1.TXT_PERFORM_STATUS.value;"); 
				
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status+\"&cr_officer=\"+m_cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;"); 
				
				out.println(" 		m_mkt_officer = document.Form1.TXT_USER.value;");
				
				out.println(" 		m_from_date_run = document.Form1.VAL_DAY_FROM_DATE_FOR_RUN.value+'-'+document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN.value+'-'+document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN.value;"); 
				out.println(" 		m_to_date_run   = document.Form1.VAL_DAY_TO_DATE_FOR_RUN.value+'-'+document.Form1.VAL_MONTH_TO_DATE_FOR_RUN.value+'-'+document.Form1.VAL_YEAR_TO_DATE_FOR_RUN.value;"); 
				
				out.println(" 		m_from_date_act = document.Form1.VAL_DAY_FROM_DATE_FOR_ACT.value+'-'+document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT.value+'-'+document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT.value; "); 
				out.println(" 		m_to_date_act   = document.Form1.VAL_DAY_TO_DATE_FOR_ACT.value+'-'+document.Form1.VAL_MONTH_TO_DATE_FOR_ACT.value+'-'+document.Form1.VAL_YEAR_TO_DATE_FOR_ACT.value; ");
				
				out.println(" 		m_arrears_status   = document.Form1.TXT_ARREARS_STATUS.value; ");
				
				
				out.println("       m_user_location = '"+m_user_location+"'; "); 
				
				out.println("       if(m_user_location=='HO'){ ");
				out.println("          m_user_location = document.Form1.TXT_LOCATION_CODE.value;    ");
				out.println("       } ");
				out.println("       else{ ");
				out.println("          m_user_location = '"+m_user_location+"';    ");
				out.println("       } ");
				
				out.println("       m_chk_veh_no =  document.Form1.CHK_VEH_NO.value; ");
				out.println("       m_chk_cr_officer =  document.Form1.CHK_CR_OFFICER.value; ");
				out.println("       m_chk_coll_officer =  document.Form1.CHK_COLL_OFFICER.value; ");
				out.println("       m_chk_rent_date =  document.Form1.CHK_RENT_DATE.value; ");
				out.println("       m_chk_age =  document.Form1.CHK_AGE.value; ");
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=print_report_new&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+m_user_location+\"&mkt_officer=\"+m_mkt_officer+\"&cr_officer=\"+m_cr_officer+\"&active_yard_status=\"+m_active_yard_status+\"&perform_stat=\"+m_perform_stat+\"&region=\"+m_region+\"&from_date_run=\"+m_from_date_run+\"&to_date_run=\"+m_to_date_run+\"&from_date_act=\"+m_from_date_act+\"&to_date_act=\"+m_to_date_act+\"&arrears_status=\"+m_arrears_status;"); // commented by udara 20-11-2015 
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=print_report_new&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+m_user_location+\"&mkt_officer=\"+m_mkt_officer+\"&cr_officer=\"+m_cr_officer+\"&active_yard_status=\"+m_active_yard_status+\"&perform_stat=\"+m_perform_stat+\"&region=\"+m_region+\"&from_date_run=\"+m_from_date_run+\"&to_date_run=\"+m_to_date_run+\"&from_date_act=\"+m_from_date_act+\"&to_date_act=\"+m_to_date_act+\"&arrears_status=\"+m_arrears_status+\"&chk_veh_no=\"+m_chk_veh_no+\"&chk_cr_officer=\"+m_chk_cr_officer+\"&chk_coll_officer=\"+m_chk_coll_officer+\"&chk_rent_date=\"+m_chk_rent_date+\"&chk_age=\"+m_chk_age;"); 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt_detail?chksql=print_report_new&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+m_user_location+\"&mkt_officer=\"+m_mkt_officer+\"&cr_officer=\"+m_cr_officer+\"&active_yard_status=\"+m_active_yard_status+\"&perform_stat=\"+m_perform_stat+\"&region=\"+m_region+\"&from_date_run=\"+m_from_date_run+\"&to_date_run=\"+m_to_date_run+\"&from_date_act=\"+m_from_date_act+\"&to_date_act=\"+m_to_date_act+\"&arrears_status=\"+m_arrears_status+\"&chk_veh_no=\"+m_chk_veh_no+\"&chk_cr_officer=\"+m_chk_cr_officer+\"&chk_coll_officer=\"+m_chk_coll_officer+\"&chk_rent_date=\"+m_chk_rent_date+\"&chk_age=\"+m_chk_age;");
				out.println("		window.open(m_url);");
				//out.println("	}");
				
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				
				out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("     help_button_user(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("			document.Form1.TXT_USER.value=data_vec[0]");
				out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[2]");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("     help_update(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[0]");
				out.println("			}");
				//added milinda
				out.println("			if(data_vec.length==0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("     mk_officer_help(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("			document.Form1.MKT_OFFICER.value=data_vec[0]");
				out.println("			}");
				
				out.println("}");
				
				
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				
				out.println("}");	
				
				
				
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				
				out.println("function makeRequest(obj) {");
				
				out.println("if(document.Form1.hid_chk_status.value=='M_CLIENT' )");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M1' )");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_marketing_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_LOCATION_CODE.value+\"&ac_status=Y\";");	
				
				out.println("else if(document.Form1.hid_chk_status.value=='M_MKT_OFFC' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_credit_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.MKT_OFFICER.value+\"&ac_status=Y\";");	
				
				
				//out.println("window.open(m_url);");
				
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
				out.println("VDATE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=main_page&generate=page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection Process - Past Month Payment Summary Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Past Month Payment Summary Report - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");
				out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				
				//----------------------------------------------------------------------------------------------------------------------------------------
				
				
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		team_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		help_value_assign_user(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		help_value_assign_finance(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		help_cr_assign(oBj);"); 
				out.println("		}"); 
				//out.printrln("      if(IfCount==\"5\"){ ");
				//out.println("       help_cr_assign(oBj);");
				//out.println("		}"); 
				
				
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
				out.println("	clear_data(IfCount);");//Added To The Clear 
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
				
				//-----------------------------------------------------------------------------------------------------------------------------------------
				
				out.println(""); 
				
				out.println("function help_button_finance() {"); 
				out.println(" document.Form1.hid_help_type.value='4' ");
				out.println("    Crit = document.Form1.TXT_FINANCE.value+\"@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_branch_sql','4');"); 
				out.println("}"); 
				
				
				//credit  officer help
				out.println("function mk_officer_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				out.println("    Crit = document.Form1.MKT_OFFICER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_credi_officer','5');"); 
				out.println("}"); 
				
				out.println("function help_value_assign_finance(oBj) {"); 
				out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[9];"); 
				out.println("}");
				
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				//added milinda
				out.println("function help_cr_assign() {"); 
				out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_user() {"); 
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_USER.value='';");
				out.println("		}"); 
				
				out.println("		if(IfCount==\"5\"){"); 
				out.println("document.Form1.MKT_OFFICER.value='';");
				out.println("		}"); 
				
				
				out.println("}");
				
				out.println("function get_rental_dates(date,m_client_code,m_officer){");
				
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
				out.println("load_interface(m_url,'NORM');");
				//out.println("window.open(m_url);");
				out.println("}"); 
				
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}"); 
				
				
				out.println("function print_report(date,m_location,m_officer) {");
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Past_month_payment_summary_rpt?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
				out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}");	
				
				out.println("function ckeck_new_date(){ "); 
				out.println("b_flag=0;");
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No data to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else if(!count_date_selected()){"); 
				out.println("alert('Please enter new date');");
				out.println("b_flag=1;");
				out.println("}"); 
				
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				
				out.println("}"); 
				
				
				
				out.println("function count_date_selected(){ ");
				out.println("count=0;");
				out.println("var arr_size=document.Form1.hid_no_rec.value;");
				
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_new_date_dd=\"TXT_NEW_DATE_DD_\"+i;");
				out.println("m_new_date_mm=\"TXT_NEW_DATE_MM_\"+i;");
				out.println("m_new_date_yy=\"TXT_NEW_DATE_YY_\"+i;");
				
				out.println("if(document.Form1.elements[m_new_date_dd].value!='' && document.Form1.elements[m_new_date_mm].value!='' &&  document.Form1.elements[m_new_date_yy].value!=''){");
				out.println("count=count+1;");
				out.println("}");		
				
				out.println("}");		
				
				out.println("if(count>0)");
				out.println("return true;");
				out.println("else");
				out.println("return false;");
				
				out.println("}"); 
				
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				
				out.println("  if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				
				out.println("  		if(document.Form1.hid_cal_date.value=='2'){"); 
				
				out.println("     		v_date=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_date.length<2)");
				out.println("         		v_date=0+v_date");
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     		v_month=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_month.length<2)");
				out.println("         		v_month=0+v_month");
				
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     		document.Form1.VAL_DAY.value=v_date;");
				out.println("     		document.Form1.VAL_MONTH.value=v_month;");
				out.println("     		document.Form1.VAL_YEAR.value=val;");
				out.println("     		document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  		}");
				
				out.println("  		if(document.Form1.hid_cal_date.value=='3'){"); 
				
				out.println("     		v_date=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_date.length<2)");
				out.println("         		v_date=0+v_date");
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     		v_month=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_month.length<2)");
				out.println("         		v_month=0+v_month");
				
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     		document.Form1.VAL_DAY_FROM_DATE_FOR_RUN.value=v_date;");
				out.println("     		document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN.value=v_month;");
				out.println("     		document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN.value=val;");
				out.println("     		document.Form1.hid_date.value=document.Form1.VAL_DAY_FROM_DATE_FOR_RUN.value+'-'+document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN.value+'-'+document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  		}");
				
				out.println("  		if(document.Form1.hid_cal_date.value=='4'){"); 
				
				out.println("     		v_date=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_date.length<2)");
				out.println("         		v_date=0+v_date");
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     		v_month=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_month.length<2)");
				out.println("         		v_month=0+v_month");
				
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     		document.Form1.VAL_DAY_TO_DATE_FOR_RUN.value=v_date;");
				out.println("     		document.Form1.VAL_MONTH_TO_DATE_FOR_RUN.value=v_month;");
				out.println("     		document.Form1.VAL_YEAR_TO_DATE_FOR_RUN.value=val;");
				out.println("     		document.Form1.hid_date.value=document.Form1.VAL_DAY_TO_DATE_FOR_RUN.value+'-'+document.Form1.VAL_MONTH_TO_DATE_FOR_RUN.value+'-'+document.Form1.VAL_YEAR_TO_DATE_FOR_RUN.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  		}");
				
				out.println("  		if(document.Form1.hid_cal_date.value=='5'){"); 
				
				out.println("     		v_date=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_date.length<2)");
				out.println("         		v_date=0+v_date");
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     		v_month=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_month.length<2)");
				out.println("         		v_month=0+v_month");
				
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     		document.Form1.VAL_DAY_FROM_DATE_FOR_ACT.value=v_date;");
				out.println("     		document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT.value=v_month;");
				out.println("     		document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT.value=val;");
				out.println("     		document.Form1.hid_date.value=document.Form1.VAL_DAY_FROM_DATE_FOR_ACT.value+'-'+document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT.value+'-'+document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  		}");
				
				out.println("  		if(document.Form1.hid_cal_date.value=='6'){"); 
				
				out.println("     		v_date=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_date.length<2)");
				out.println("         		v_date=0+v_date");
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     		v_month=val.substr(0,val.indexOf('-'));");
				out.println("     		if(v_month.length<2)");
				out.println("         		v_month=0+v_month");
				
				out.println("     		val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     		document.Form1.VAL_DAY_TO_DATE_FOR_ACT.value=v_date;");
				out.println("     		document.Form1.VAL_MONTH_TO_DATE_FOR_ACT.value=v_month;");
				out.println("     		document.Form1.VAL_YEAR_TO_DATE_FOR_ACT.value=val;");
				out.println("     		document.Form1.hid_date.value=document.Form1.VAL_DAY_TO_DATE_FOR_ACT.value+'-'+document.Form1.VAL_MONTH_TO_DATE_FOR_ACT.value+'-'+document.Form1.VAL_YEAR_TO_DATE_FOR_ACT.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  		}");
				
				out.println("   }");
				
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				// added by udara 20-11-2015
				out.println("function change_obj_value(obj){");
				out.println("   if(obj.checked==true){ ");
				out.println("      obj.value='Y'; ");
				out.println("   }");
				out.println("   else{");
				out.println("      obj.value='N'; ");
				out.println("   }");
				//out.println("   alert(obj.value); ");
				out.println("}");
				// end by udara 20-11-2015
				
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()' > "); //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Past Month Payment Summary Report </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				// added by udara on 09-05-2013
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Perform Status </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_PERFORM_STATUS'>"); 
				out.println("<option value=''    > All </option>");
				out.println("<option value='PERFORM'  > Perform </option>");//Added by Dineth on 2008-12-16
				out.println("<option value='NPERFORM' > Non Perform </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by udara on 09-05-2013
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				
				// commented by udara 20-11-2015
				// added by udara 20-10-2015
				/*
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>From Date (Running)</td>");
				out.println("<td width='*%'>");
				out.println("    <input name=\"VAL_DAY_FROM_DATE_FOR_RUN\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_RUN,document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN,document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_MONTH_FROM_DATE_FOR_RUN\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_RUN,document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN,document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_YEAR_FROM_DATE_FOR_RUN\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_RUN,document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN,document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>To Date (Running)</td>");
				out.println("<td width='*%'>");
				out.println("    <input name=\"VAL_DAY_TO_DATE_FOR_RUN\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_RUN,document.Form1.VAL_MONTH_TO_DATE_FOR_RUN,document.Form1.VAL_YEAR_TO_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_MONTH_TO_DATE_FOR_RUN\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_RUN,document.Form1.VAL_MONTH_TO_DATE_FOR_RUN,document.Form1.VAL_YEAR_TO_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_YEAR_TO_DATE_FOR_RUN\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_RUN,document.Form1.VAL_MONTH_TO_DATE_FOR_RUN,document.Form1.VAL_YEAR_TO_DATE_FOR_RUN)><a href style='{cursor:hand; }' onclick=load_calendar('4')>   Calendar</a> ");
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>From Date (Activated Date)</td>");
				out.println("<td width='*%'>");
				out.println("    <input name=\"VAL_DAY_FROM_DATE_FOR_ACT\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_ACT,document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT,document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_MONTH_FROM_DATE_FOR_ACT\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_ACT,document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT,document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_YEAR_FROM_DATE_FOR_ACT\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_ACT,document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT,document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT)><a href style='{cursor:hand; }' onclick=load_calendar('5')>   Calendar</a> ");
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>To Date (Activated Date)</td>");
				out.println("<td width='*%'>");
				out.println("    <input name=\"VAL_DAY_TO_DATE_FOR_ACT\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_ACT,document.Form1.VAL_MONTH_TO_DATE_FOR_ACT,document.Form1.VAL_YEAR_TO_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_MONTH_TO_DATE_FOR_ACT\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_ACT,document.Form1.VAL_MONTH_TO_DATE_FOR_ACT,document.Form1.VAL_YEAR_TO_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_YEAR_TO_DATE_FOR_ACT\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_ACT,document.Form1.VAL_MONTH_TO_DATE_FOR_ACT,document.Form1.VAL_YEAR_TO_DATE_FOR_ACT)><a href style='{cursor:hand; }' onclick=load_calendar('6')>   Calendar</a> ");
				out.println("</td>"); 
				out.println("</tr>");
				*/
				// end by udara 20-10-2015
				
				// added by udara 20-11-2015
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Months Range (From)</td>");
				
				out.println("<td width='*%'>");
				
				out.println("    <input name=\"VAL_DAY_FROM_DATE_FOR_RUN\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_RUN,document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN,document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_MONTH_FROM_DATE_FOR_RUN\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_RUN,document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN,document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_YEAR_FROM_DATE_FOR_RUN\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_RUN,document.Form1.VAL_MONTH_FROM_DATE_FOR_RUN,document.Form1.VAL_YEAR_FROM_DATE_FOR_RUN)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				
				out.println("    &nbsp; &nbsp; To &nbsp; &nbsp; ");
				
				out.println("    <input name=\"VAL_DAY_TO_DATE_FOR_RUN\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_RUN,document.Form1.VAL_MONTH_TO_DATE_FOR_RUN,document.Form1.VAL_YEAR_TO_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_MONTH_TO_DATE_FOR_RUN\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_RUN,document.Form1.VAL_MONTH_TO_DATE_FOR_RUN,document.Form1.VAL_YEAR_TO_DATE_FOR_RUN)> ");
				out.println("    <input name=\"VAL_YEAR_TO_DATE_FOR_RUN\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_RUN,document.Form1.VAL_MONTH_TO_DATE_FOR_RUN,document.Form1.VAL_YEAR_TO_DATE_FOR_RUN)><a href style='{cursor:hand; }' onclick=load_calendar('4')>   Calendar</a> ");
				
				out.println("</td>"); 
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				
				out.println("<td width='20%'ID=VDATE>Activated Contracts (From)</td>"); // out.println("<td width='20%'ID=VDATE>Activated Date (From)</td>");
				
				out.println("<td width='*%'>");
				
				out.println("    <input name=\"VAL_DAY_FROM_DATE_FOR_ACT\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_ACT,document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT,document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_MONTH_FROM_DATE_FOR_ACT\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_ACT,document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT,document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_YEAR_FROM_DATE_FOR_ACT\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM_DATE_FOR_ACT,document.Form1.VAL_MONTH_FROM_DATE_FOR_ACT,document.Form1.VAL_YEAR_FROM_DATE_FOR_ACT)><a href style='{cursor:hand; }' onclick=load_calendar('5')>   Calendar</a> ");
				
				out.println("    &nbsp; &nbsp; To &nbsp; &nbsp; ");
				
				out.println("    <input name=\"VAL_DAY_TO_DATE_FOR_ACT\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_ACT,document.Form1.VAL_MONTH_TO_DATE_FOR_ACT,document.Form1.VAL_YEAR_TO_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_MONTH_TO_DATE_FOR_ACT\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_ACT,document.Form1.VAL_MONTH_TO_DATE_FOR_ACT,document.Form1.VAL_YEAR_TO_DATE_FOR_ACT)> ");
				out.println("    <input name=\"VAL_YEAR_TO_DATE_FOR_ACT\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO_DATE_FOR_ACT,document.Form1.VAL_MONTH_TO_DATE_FOR_ACT,document.Form1.VAL_YEAR_TO_DATE_FOR_ACT)><a href style='{cursor:hand; }' onclick=load_calendar('6')>   Calendar</a> ");
				
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by udara 20-11-2015
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr >"); //Added By Sandun on 07-11-2008
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='30' style='{width=150px}' size='10' onblur='help_button_finance()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				//added milinda
				out.println("<tr >"); 
				
				out.println("<td width='20%' ><DIV id='DIV_MKT_OFFICER'  class=div_input>Credit Officer *</DIV></td>"); 
				out.println("<td width='*%%' ><input class='txt_input' type='text' name='MKT_OFFICER' maxlength='50' size='10' style=\"{width:150px}\"  onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_CONDITION_OF_ASSET' value=\"Help\" onClick=\"mk_officer_help()\"></td>"); 
				
				
				out.println("</tr>"); 
				
				// added by CJ 13-01-2015
				out.println("<tr>"); 
				out.println("<td width='20%' > Active/Yard Vehicles </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ACTIVE_STATUS'>"); 
				out.println("<option value='A' > All </option>");
				out.println("<option value='Y' > Active </option>");
				out.println("<option value='N' > Yard Vehicles </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by CJ 13-01-2015
				
				
				// Added By: Samith dilshan  On : 2015-06-03
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
				out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				out.println("      <OPTION value='SAB' >Sabaragamuwa</OPTION>");
				out.println("      <OPTION value='WP'  >Western Province</OPTION>");
				out.println("      <OPTION value='CP'  >Central Province</OPTION>");
				out.println("      <OPTION value='CPSP'>Southern Province</OPTION>");*/
				
				/*rs3 = stmt3.executeQuery (" SELECT REGIONS_CODE, REGIONS_DESC  "+
					" FROM "+m_schema_name+".AF_CO_MAS_REGIONS "+
					" WHERE ACTIVE_STATUS='Y' "+
					" ORDER BY REGIONS_DESC ");
				
				while(rs3.next()){
					out.println("  <OPTION value=\""+rs3.getString(1)+"\">"+rs3.getString(2)+"</OPTION>");
				}
				*/
				/*out.println(" 	</select>");
				out.println("</td>"); 
				out.println("</tr>");*/
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
				out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				stmt3 = conn.createStatement();
				
				rs3 = stmt3.executeQuery (" SELECT REGIONS_CODE, REGIONS_DESC  "+
					" FROM "+m_schema_name+".AF_CO_MAS_REGIONS "+
					" WHERE ACTIVE_STATUS='Y' "+
					" ORDER BY REGIONS_DESC ");
				
				while(rs3.next()){
					out.println("  <OPTION value=\""+rs3.getString(1)+"\">"+rs3.getString(2)+"</OPTION>");
				}
				
				out.println(" 	   </select>");
				out.println(" </td>"); 
				out.println("</tr>");
				
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Arrears Status </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ARREARS_STATUS'>"); 
				out.println("<option value='ALL' > All </option>");
				out.println("<option value='POSSITIVE' > Possitive </option>");
				out.println("<option value='NEGATIVE' > Negative </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// added by udara 20-11-2015
				out.println("<tr>"); 
				out.println("<td width='20%' > Vehicle No </td>"); 
				out.println("<td width='*%' >"); 
				out.println("     <input type='checkbox' value='N' onclick='change_obj_value(this);' id='CHK_VEH_NO' name='CHK_FULL_ADDRESS' > "); 
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Credit Officer </td>"); 
				out.println("<td width='*%' >"); 
				out.println("     <input type='checkbox' value='N' onclick='change_obj_value(this);' id='CHK_CR_OFFICER' name='CHK_CR_OFFICER' > "); 
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Collection Officer </td>"); 
				out.println("<td width='*%' >"); 
				out.println("     <input type='checkbox' value='N' onclick='change_obj_value(this);' id='CHK_COLL_OFFICER' name='CHK_COLL_OFFICER' > "); 
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Rental Date </td>"); 
				out.println("<td width='*%' >"); 
				out.println("     <input type='checkbox' value='N' onclick='change_obj_value(this);' id='CHK_RENT_DATE' name='CHK_RENT_DATE' > "); 
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Age </td>"); 
				out.println("<td width='*%' >"); 
				out.println("     <input type='checkbox' value='N' onclick='change_obj_value(this);' id='CHK_AGE' name='CHK_AGE' > "); 
				out.println("</td>"); 
				out.println("</tr>");
				// end by udara 20-11-2015
				
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			
			
			else if(m_chksql.equals("print_report_new")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
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
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location_id")!=null ){
					m_location=req.getParameter("location_id").trim();
				}
				
				if(req.getParameter("mkt_officer")!=null ){
					m_officer=req.getParameter("mkt_officer").trim();
				}
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
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
				
				
				/*
				// Headings Start
				out.println("<tr class=pdn_txtpos2 >");
				
				out.println("<td width='1%' ><b> No. </b></td>");
				out.println("<td width='5%' ><b> Agreement No </b></td>"); // Agreement No
				
				if(m_chk_veh_no.equals("Y"))
					out.println("<td width='5%' ><b> Vehicle&nbsp;No </b></td>"); // Vehicle No
				
				if(m_chk_cr_officer.equals("Y"))
					out.println("<td width='5%' ><b> Credit&nbsp;Officer </b></td>"); // Credit Officer
				
				if(m_chk_coll_officer.equals("Y"))
					out.println("<td width='5%' ><b> Collection&nbsp;Officer </b></td>"); // Collection Officer

				out.println("<td width='5%' align='right' ><b> Rental&nbsp;Amount </b></td>"); // Rental Amount

				
				if(m_chk_rent_date.equals("Y"))
					out.println("<td width='5%' ><b> R/D </b></td>");
					
					*/
				
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
					
					//out.println("<td  align='right' width='5%' ><b> &nbsp; </b></td>");
					//out.println("<td  align='right' width='5%' ><b> "+rs1.getString(1)+" </b></td>");		
					
					
				}
				
				//out.println("<td width='5%' align='right' ><b> Arrears/Excess </b></td>");
				
				//if(m_chk_age.equals("Y"))
				//out.println("<td width='5%' align='right' ><b> Age </b></td>");
				
				//out.println("</tr>");
				// Headings End
				
				
				int counts = 0;
				
				rs=stmt.executeQuery(" "+
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
					" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') "+ // 21
					" FROM  "+m_schema_name+".AF_PAST_MONTH_PAY_FINANCE_DET A "+
					" WHERE A.ENT_USER = '"+m_username+"' "+
					" AND A.FINANCE_NO LIKE '"+m_finance_no+"%' "+
					" AND A.BRANCH_CODE LIKE '"+m_location+"%' "+
					" AND A.COLLECTION_OFFICER LIKE '"+m_officer+"%' "+
					" AND NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(A.APPLICATION_NO),' ') LIKE  '"+m_cr_officer+"%' "+
					" "+m_active_status_string+" "+
					" "+m_arrears_status_string+" "+
					" "+m_region_string+" "+
					" ORDER BY A.ACTIVATED_DATE "+
					" ");
				
				String m_month_value = "";
				int colspan_count = (month_count*2) + 11;
				
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
						
						if(m_chk_cr_officer.equals("Y"))
							out.println("<td width='5%' ><b> Credit&nbsp;Officer </b></td>"); // Credit Officer
						
						if(m_chk_coll_officer.equals("Y"))
							out.println("<td width='5%' ><b> Collection&nbsp;Officer </b></td>"); // Collection Officer
						
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
						
						if(m_chk_cr_officer.equals("Y"))
							out.println("<td width='5%' ><b> Credit&nbsp;Officer </b></td>"); // Credit Officer
						
						if(m_chk_coll_officer.equals("Y"))
							out.println("<td width='5%' ><b> Collection&nbsp;Officer </b></td>"); // Collection Officer
						
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
					
					if(m_chk_cr_officer.equals("Y"))
						out.println("<td width='5%' > "+rs.getString(19)+" </td>");
					
					if(m_chk_coll_officer.equals("Y"))
						out.println("<td width='5%' > "+rs.getString(20)+" </td>");
					
					
					out.println("<td width='5%' align='right' > "+nf.format(rs.getDouble(5))+" </td>");
					
					if(m_chk_rent_date.equals("Y"))
						out.println("<td width='5%' align='center' > "+rs.getString(6)+" </td>");
					
					String m_from_run_date = "";
					String m_to_run_date = "";
					
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
						
						
						//if(month_count_first_month==1){
						//out.println("<td  align='right' > "+nf.format(rs.getDouble(10))+" </td>"); // first month value of master table
						
						//}
						//else{
						
						
						if(!date_vissibility.equals("BLANK")){
							out.println("<td   onclick=\"\" STYLE='text-align:center;' > "+rental_day+" </td>");
							out.println("<td   STYLE='text-align:right; cursor:hand;' onclick=\"print_report_new_drill('"+rs.getString(1)+"','"+m_username+"','"+m_from_run_date+"','"+m_to_run_date+"');\" ><u> "+nf.format(receipts_amount)+" </u></td>");
						}
						else{
							out.println("<td> &nbsp; </td>");
							out.println("<td> &nbsp; </td>");
						}
						
						
						//}
						
						
						
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
			
			
			else if(m_chksql.equals("print_report_new_drill")){		
				
				String m_finance_no = "";
				String m_from_date = "";
				String m_to_date = "";
				
				if(req.getParameter("finance_no")!=null ){
					m_finance_no=req.getParameter("finance_no").trim();
				}
				
				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				//stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Past Months Payment Summary Report - Drill </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here	
				// added by udara on 26-07-2013
				out.println("	function show_receipt_drill(m_receipt_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_SETTLE_RECEIPT_DRILL&receipt_no=\"+m_receipt_no;");
				//out.println("    window.open(m_url); ");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Past Months Payment Summary Report - Drill</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_data="";
				boolean more;
				
				Sql_data=" SELECT A.REC_NO, "+
					" NVL(A.SUB_REC_NO,'-'), "+
					" NVL(A.REC_AMOUNT,0) "+
					" FROM "+m_schema_name+".AF_PAST_MONTH_PAY_SUM_RECEIPTS A "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' "+
					" AND A.ENT_USER='"+m_username+"' "+
					" AND TRUNC(A.EFF_VALDATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND TRUNC(A.EFF_VALDATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  ";
				
				rs=stmt.executeQuery(Sql_data);
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
				int count = 0;
				
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<td width='10%' class=div_input align='left' bgcolor='lightblue'  ><B> No.</B></td>");
				out.println("<td width='50%' class=div_input align='left' bgcolor='lightblue'  ><B> Receipt No.</B></td>");
				out.println("<td width='50%' class=div_input align='left' bgcolor='lightblue'  ><B> Sub Receipt No.</B></td>");
				out.println("<td width='50%' class=div_input align='right' bgcolor='lightblue' ><B> Receipt Amount</B></td>");
				out.println("</tr>");
				while(more){
					
					count = count + 1;
					
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' >"+count+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_receipt_drill('"+rs.getString(2)+"');\"  ><u>"+rs.getString(1)+"</u></td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_receipt_drill('"+rs.getString(2)+"');\"  ><u>"+rs.getString(2)+"</u></td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(3))+"</td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(3);
					num_row++;
					more=rs.next();
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' > &nbsp; </td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' > &nbsp; </td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			} // end drill
			
			
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
