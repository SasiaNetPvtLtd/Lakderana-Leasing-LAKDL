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


public class LAKDL_AF_MISF_Zero_Payments_Report extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
		Statement stmt=null,stmt2=null,stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null;
		
		
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
			
			if(m_chksql.equals("run_report")){ 
				
				String m_date=req.getParameter("date");
				String m_location_id=req.getParameter("location_id");
				String m_user_id=req.getParameter("user_id");
				String m_finance_no = req.getParameter("finance_no");
				//String m_perform_status = req.getParameter("perform_status");
				String m_perform_status = req.getParameter("n_perform_status"); // added by udara 14-08-2015
				String m_rendate=req.getParameter("rendate").trim();
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ARREARS_RPT(:1,:2,:3,:4,:5);END;");
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ARREARS_RPT_2(:1,:2,:3,:4,:5,:6);END;"); // commented by udara 10-01-2014
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ZERO_PAYMENTS(:1,:2,:3,:4,:5,:6,:7);END;"); // added by udara 10-01-2014
					callstmt1.setString(1,m_rendate); // Ruwani 2016-01-11
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_user_id);   
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_finance_no);
					callstmt1.setString(6,m_perform_status); // added by udara on 09-10-2013
					callstmt1.setString(7,m_date); // added by ruwani 2016-01-11
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Zero Payments Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println(" 		finance_no = '';");
				out.println(" 		m_perform_status = '';");
				out.println(" 		m_perform_status = document.Form1.TXT_POSSITIVE_STATUS.value;"); 
				out.println(" 		m_rendate  = document.Form1.AT_DAY.value+'-'+document.Form1.AT_MONTH.value+'-'+document.Form1.AT_YEAR.value;  ");
				out.println(" 		mm_perform_status = document.Form1.TXT_PERFORM_STATUS.value;"); // added by udara 14-08-2015
				
				out.println("           var m_from_date     = document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;  ");
				out.println("           var m_to_date       = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;  ");
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&perform_status=\"+m_perform_status;"); 
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+\"\"+\"&perform_status=\"+m_perform_status;"); 
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&rendate=\"+m_rendate+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+\"\"+\"&perform_status=\"+m_perform_status+\"&n_perform_status=\"+mm_perform_status;"); // added by udara 14-08-2015
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report_v2?chksql=run_report&date=\"+m_date+\"&rendate=\"+m_rendate+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&perform_status=\"+m_perform_status+\"&n_perform_status=\"+mm_perform_status+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;"); // added by Kanishka 24-May-2017
				//	out.println("		window.open(m_url)");
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				out.println("function print_report2(){");
				
				out.println("	clearTimeout(timerID);");
				out.println("	m_table.innerHTML=\"\";");
				
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("			m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("			m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("			m_officer='';");
				out.println("			m_slba='';");
				out.println("			m_rendate='';"); 
				out.println("			m_period='';"); 
				out.println("			m_period_2='';"); 
				out.println("  			var due_month = '';  ");
				out.println("  			var due_year = '';  ");
				out.println("           var ins_level = '';  ");
				
				out.println("           var m_from_date     = document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;  ");
				out.println("           var m_to_date       = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;  ");
				out.println("           m_rendate           = document.Form1.AT_DAY.value+'-'+document.Form1.AT_MONTH.value+'-'+document.Form1.AT_YEAR.value;  "); //Ruwani 2015-12-29 // released by udara 25-01-2016
				
				//out.println("           m_rendate =''"); // commented by udara 25-01-2016
				out.println("           var m_possitive_status  = document.Form1.TXT_POSSITIVE_STATUS.value;  "); // added by udara 04-11-2014
				
				out.println("   		m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-15(#16240)
				
				out.println("           var m_perform_status = document.Form1.TXT_PERFORM_STATUS.value;   "); // added by udara 17-08-2015
				
				/*
				out.println("           if(due_month==\"\"&& due_year==\"\") ");
				out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&possitive_status=\"+m_possitive_status+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");
				out.println("           else ");
				out.println("           	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&possitive_status=\"+m_possitive_status+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value;");
				*/
				
				// commented by udara 26-09-2017
				/*
				// added by udara 17-08-2015
				out.println("           if(due_month==\"\"&& due_year==\"\") ");
				//out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&possitive_status=\"+m_possitive_status+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;");
				out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report_v2?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&possitive_status=\"+m_possitive_status+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;");
				out.println("           else ");
				out.println("           	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&possitive_status=\"+m_possitive_status+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&perform_status=\"+m_perform_status;");
				//out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report_v2?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&possitive_status=\"+m_possitive_status+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;");//[Added milinda for testing]
				// end by udara 17-08-2015
				
				// end by udara on 06-09-2013
				
				*/
				
				out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report_v2?chksql=print_report_new_logic&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&possitive_status=\"+m_possitive_status+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;"); // added by udara 26-09-2017
				
				out.println("			window.open(m_url);");
				
				out.println("	}");
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
				
				out.println("}");
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Zero Payments Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Zero Payments Report - \"+document.Form1.hid_status.value;"); 
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				
				
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.VAL_DAY.value=v_date;");
				out.println("     document.Form1.VAL_MONTH.value=v_month;");
				out.println("     document.Form1.VAL_YEAR.value=val;");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//---added by Prabash on 09-05-2012-----**
				out.println("else  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.REN_DAY.value=v_date;");		
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//--------------------------------------**
				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				//out.println("  			alert(\"check_Date\");  ");
				
				out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				out.println("function check_Date_2(objDD,objMM,objYY) {");
				//out.println("  			alert(\"check_Date_2\");  ");
				//out.println("  			alert( objDD.value+\" objDD.value\"+ objMM.value+\" objMM.value\"+ objYY.value+\" objYY.value\");  ");
				
				out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date_2.value=document.Form1.TXT_DAY.value+'-'+document.Form1.TXT_MONTH.value+'-'+document.Form1.TXT_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
					
					out.println("document.Form1.FROM_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.FROM_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.FROM_YEAR.value='"+rs2.getString(3)+"';");
					
					out.println("document.Form1.AT_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.AT_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.AT_YEAR.value='"+rs2.getString(3)+"';");
					
				}
				out.println("}"); 
				
				
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
				out.println("<input type=hidden name='hid_date_2' value=\"\">");
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Zero Payments Report </td>"); 
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
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>From Date</td>");
				out.println("<td width='*%'><input name=\"FROM_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>To Date</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>"); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>As At Date</td>");
				out.println("<td width='*%'><input name=\"AT_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)> ");
				out.println("    <input name=\"AT_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)> ");
				out.println("    <input name=\"AT_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)>"); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
				out.println("</tr>");
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 	
				
				// added by udara 04-11-2014
				out.println("<tr>"); 
				out.println("<td width='20%' > Arrears Status </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_POSSITIVE_STATUS'>"); 
				out.println("<option value='ALL'       > All </option>");
				out.println("<option value='POSSITIVE' > Possitive </option>");
				out.println("<option value='NEGATIVE'  > Negative </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				// end by udara 04-11-2014
				
				// added by CJ 13-01-2015
				out.println("<tr>"); 
				out.println("<td width='20%' > Active/Yard Vehicles </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ACTIVE_STATUS'>"); 
				out.println("<option value='A'    > All </option>");
				out.println("<option value='Y'  > Active </option>");
				out.println("<option value='N' > Yard Vehicles </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by CJ 13-01-2015
				stmt3 = conn.createStatement();
				
				// Added By: Samith dilshan  On : 2015-06-03
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
				out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
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
				
				// added by udara 14-08-2015
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Perform Status </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_PERFORM_STATUS'>"); 
				out.println("<option value=''    > All </option>");
				out.println("<option value='PERFORM'  > Perform </option>");//Added by Dineth on 2008-12-16
				out.println("<option value='NPERFORM' > Non Perform </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by udara 14-08-2015
				
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
			
			
			
			else if(m_chksql.equals("drill_down_asset")){		
				String m_string="";
				String m_finance_no=req.getParameter("finance_no");
				
				stmt2 = conn.createStatement ();
				
				rs2= stmt2.executeQuery (" SELECT "+
					" B.ASSET_ID, "+
					" D.MAKE_CODE, "+
					" B.MODEL_CODE, "+
					" B.SUB_MODEL_CODE, "+
					" C.DESCRIPTION, "+
					" DECODE(B.STATUS,'N','New',DECODE(B.STATUS,'R','Re-Condition','Used') ), "+
					" DECODE(B.PURPOSE,'P','Private Use','Business'), "+
					" C.ENGINE_CAPACITY, "+
					" C.YEAR_OF_MANUFACTURE "+
					" FROM "+
					" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B,"+m_schema_name+".AF_CO_MAS_SUB_MODLE C ,"+m_schema_name+".AF_CO_MAS_MODEL D"+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
					" UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
					"       B.SUB_MODEL_CODE=C.SUB_CODE AND       "+
					"       D.MODEL_CODE=C.MODEL_CODE AND       "+
					"       B.ACTIVE_STATUS='Y' AND "+
					"       C.ACTIVE_STATUS='Y' "+
					"       ORDER BY B.ASSET_ID ");
				
				
				
				
				
				m_string=m_string+"<table class='table' border='1' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left' class='pdn_txtpos2' >ASSET DETAILS FOR THE FINANCE NO: "+m_finance_no+"</td></tr>"; 
				m_string=m_string+"</table>"; 
				
				m_string=m_string+"<br>"; 
				
				m_string=m_string+"</table>"; 
				
				m_string=m_string+"<br>"; 
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\">";
				m_string=m_string+"<tr class='pdn_txtpos2' >";
				//m_string=m_string+"<th width='1%'></th>"; 
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Asset Id</DIV></th>";
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Make Code</DIV></th>"; 
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Sub Model Code</DIV></th>";
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Model Code</DIV></th>"; 
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Description</DIV></th>";
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Status</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Purpose</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Engine Capacity</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Year of Manufacture</DIV></th>"; 
				m_string=m_string+"</tr>";
				
				int i=0;
				
				while(rs2.next()){
					
					m_string=m_string+"<tr>";
					//m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(1)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(3)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(4)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(5)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(6)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(7)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(8)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(9)+"</DIV></td>";
					
					m_string=m_string+"</tr>";
					i++;
				}
				
				m_string=m_string+"</table>";
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Zero Payments Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println(m_string);
				out.println("</html>");
				
			}
			
			else if(m_chksql.equals("print_report_new")){		
				String m_period = "";
				String m_period_2 = ""; // added by udara on 21-08-2013
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slba=""; //added by Prabash on 09-05-2012
				String m_rendate=""; //added by Prabash on 09-05-2012
				
				String m_from_date="";
				String m_to_date="";
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				
				String m_region = "";
				
				// added by udara 17-08-2015
				String m_perform_status = "";
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}
				// end by udara 17-08-2015
				
				// Added By Samith Dilshan on 2015-06-15
				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(FINANCE_NO) = '"+m_active_status+"' "; // commeted by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				//Added by Prabash on 09-05-2012---**
				if(req.getParameter("slab")!=null ){
					m_slba=req.getParameter("slab").trim();
				}
				
				
				if(req.getParameter("rendate")!=null ){
					m_rendate=req.getParameter("rendate").trim();
				}
				//---------------------------------**
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				
				// added by udara on 12-03-2013
				if(req.getParameter("period")!=null ){
					m_period=req.getParameter("period").trim();
				}
				
				// added by udara on 21-08-2013
				if(req.getParameter("period_2")!=null ){
					m_period_2=req.getParameter("period_2").trim();
				}
				
				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				// added by udara 04-11-2014
				String m_possitive_status = req.getParameter("possitive_status");
				String m_possitive_status_str = "";
				
				//  DECODE(DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT),0,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT)) 
				
				if(m_possitive_status.equals("ALL"))
					m_possitive_status_str = " ";
				else if(m_possitive_status.equals("POSSITIVE"))
					m_possitive_status_str = " AND DECODE(DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT),0,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT))  >= 0 "; //m_possitive_status_str = " AND TOTAL_AMOUNT >= 0 ";
				else if(m_possitive_status.equals("NEGATIVE"))
					m_possitive_status_str = " AND DECODE(DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT),0,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT))  < 0 "; // m_possitive_status_str = " AND TOTAL_AMOUNT < 0 ";
				
				// end by udara 04-11-2014
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Zero Payments Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Zero_Payments_Report?chksql=print_report_new&location="+m_location+"&slba="+m_slba+"&rendate="+m_rendate+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function update_contract_detail(val_1){ ");  //Added By Sandun on 01-12-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=update_contact_detail&client_code='+val_1;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
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
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				//Added by Dineth on 2008-11-17
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
					m_start_date=rs.getString(3);
					m_end_date=rs.getString(4);
				}
				
				String Sql_data="";
				
				Sql_data="  WITH T AS(SELECT  "+
					" FINANCE_NO, "+ //1
					" CLIENT_CODE, "+ //2
					" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
					" VALUE_DATE ,"+ //4
					" AGE_NEW, "+ //5
					" CLIENT_TEL_NO ,"+ //6
					" DUE_RENTAL_AMOUNT ,"+ //7
					" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
					" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
					
					//" NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+ // commented by udara 18-11-2015 //10 Added By Lalanka on 15-07-2009
					" NVL("+m_schema_name+".AF_GET_COLL_OFFICER_2(application_no),'-') COLL_OFICER, "+ // added by udara 18-11-2015
					
					" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
					" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
					//" DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT) TOTAL_AMOUNT "+ // commented by udara 30-04-2014 // mod by udara 11-02-2014 //" SIGN(TOTAL_AMOUNT,1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT) TOTAL_AMOUNT "+ // added by udara 05-02-2014
					//" DECODE(DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT),0,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),DECODE(SIGN(TOTAL_AMOUNT),1,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT)) TOTAL_AMOUNT "+ // added by udara 30-04-2014 // commented by udara 12-01-2015
					
					//" DECODE( TOTAL_AMOUNT,0,"+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO),TOTAL_AMOUNT) TOTAL_AMOUNT "+ // added by udara 12-01-2015
					" DECODE( TOTAL_AMOUNT,0,"+m_schema_name+".AF_CO_GET_ARREAS_4(FINANCE_NO,'"+m_to_date+"'),TOTAL_AMOUNT) TOTAL_AMOUNT "+  // added by udara 01-
					
					//" TOTAL_AMOUNT "+//13 // commented by udara 20-12-2013
					//" "+m_schema_name+".AF_CO_GET_ARREAS_3(FINANCE_NO) TOTAL_AMOUNT "+ // 13 // added by udara 20-12-2013
					" FROM "+m_schema_name+".AF_RE_MAS_ZERO_PAYMENTS "+
					" WHERE ENT_USER='"+m_username+"' "+
					" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
					" AND TOTAL_AMOUNT <> 0 "+ // added by udara 01-09-2016
					" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
				// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
				//	" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "; //added by prabah on 09-05-2012
				//" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
				
				
				
				if(!(m_region.equals("NOT_SELECT"))){    // Added By: Samith Dilshan on 2015-06-15 for Region Code
					Sql_data = Sql_data +"  AND  REGION_CODE = '"+m_region+"' ";    
				}
				
				
				
				Sql_data = Sql_data + "  "+m_possitive_status_str+" "+ // added by udara 04-11-2014
					m_active_status_string +
					
					//" AND "+m_schema_name+".AF_ZERO_PAYMENT_RPT_SETT(FINANCE_NO,'"+m_from_date+"','"+m_to_date+"') > 0  "+ // commented by udara 12-12-2013
					" AND "+m_schema_name+".AF_ZERO_PAYMENT_RPT_SETT(FINANCE_NO,'"+m_from_date+"','"+m_to_date+"') = 0  "+ // added by udara 12-12-2013
					
					
					//" AND AGE_NEW LIKE '"+m_period+"%'   "+ // added by udara on 12-03-2013
					//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
					
					" ) "+
					" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
					" AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
					" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_to_date+"'),'-') "+ // added by udara 26-12-2013
					" FROM T "+
					" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
					
					" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    ";    
				
				
				
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				
				more=rs.next();
				int count=0;
				double closing_bal=0;
				double total_open_bal=0;
				double total_cur_due=0;
				double total_collection=0;
				double total_closing_bal=0;
				double achievement=0;
				double open_pre=0;
				double cur_pre=0;
				double col_pre=0;
				double closing_pre=0;
				double total_bal=0;
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Zero Payment Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
				out.println("<td width=\"150\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Zero Payment Report for the</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_rendate+"</td>");   // m_date // mod by udara 25-01-2016
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				
				//End by Dineth on 2008-11-17
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Marketing Executive</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Branch</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_location_desc+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				// added by udara 17-08-2015
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(m_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(m_perform_status.equals("NPERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Non Perform</td>");
				
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active/Yard Vehicles :- </td>"); 
				
				if(m_active_status.equals("A"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_active_status.equals("Y"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active</td>");
				else if(m_active_status.equals("N"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Yard Vehicles</td>");
				
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Arrears Status</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_possitive_status+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_from_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_to_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				// end by udara 17-08-2015
				
				
				// Added By Samith Dulshan
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<tr>");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Region</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+rs3.getString(1)+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
					}
				}
				
				
				out.println("</table>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"2%\"  align='center' >A</td>"); 			
				out.println("<td width=\"7%\"  align='center' >B</td>"); 
				out.println("<td width=\"12%\" align='center'  >C</td>"); 
				out.println("<td width=\"7%\"  align='center' >D</td>"); 
				out.println("<td width=\"7%\"  align='center' >E</td>"); 
				out.println("<td width=\"7%\"  align='center' >F</td>"); 
				out.println("<td width=\"5%\"  align='center' >G</td>"); 
				out.println("<td width=\"7%\"  align='center' >H</td>"); 
				out.println("<td width=\"7%\"  align='center' >I</td>"); 
				out.println("<td width=\"7%\"  align='center' >J</td>"); 
				out.println("<td width=\"7%\"  align='center' >K</td>"); 
				out.println("<td width=\"7%\"  align='center' >L</td>"); 
				out.println("<td width=\"5%\"  align='center' >M</td>"); 
				out.println("<td width=\"7%\"  align='center' >N</td>"); 
				out.println("<td width=\"7%\"  align='center' >0</td>");
				out.println("<td width=\"7%\"  align='center' >P</td>");
				out.println("<td width=\"7%\"  align='center' >Q</td>"); // added by udara on 03-10-2013
				out.println("<td width=\"7%\"  align='center' >R</td>"); // added by udara on 26-12-2013
				out.println("</tr >");
				
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class=factoring-letter-body ><b>No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Agreement No  ' onclick=sort_data('FINANCE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Agreement No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Vehicle No  '   onclick=sort_data('VEHICLE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Vehicle No</b></td>"); //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Client  '       onclick=sort_data('FULL_NAME') STYLE='{text-align:center; cursor:hand; }'       ><b>Client</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Tel  '          onclick=sort_data('CLIENT_TEL_NO') STYLE='{text-align:center; cursor:hand; }'   ><b>Tel</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>SLAB</b></td>");//ADDED BY Prabash on 09-05-2012
				out.println("<td class=factoring-letter-body title='Click here to sort by - Rental Date  '  onclick=sort_data('VALUE_DATE') STYLE='{text-align:center; cursor:hand; }'      ><b><p>Rental<br>Date</p></b></td>");// added by prabash on  09-05-2012-----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Monthly Rental  ' onclick=sort_data('RENTAL_AMOUNT') STYLE='{text-align:center; cursor:hand; }' ><b><p>Rental</p></b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Rental</p></b></td>");  // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Status</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Arrears</p></b></td>");       // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Arrears</p></b></td>"); // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Period</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Remarks</b></td>");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Follow Up</b></td>"); 																																															
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Prev Collection Oficer</b></td>");//ADDED BY LALANKA ON 15-07-2009
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Current Collection Oficer</b></td>"); // added by udara on 03-10-2013
				out.println("<td class=factoring-letter-body ><b> Last Payment Date</b></td>"); // added by udara 26-12-2013
				out.println("</tr >");
				
				
				
				
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0,sub_close=0,sub_open=0;
				
				
				
				double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				double  m_total_rental=0,m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				
				String row_colour = "white"; 
				double period_val = 0;
				
				while(more){
					
					period_val = Math.round(rs.getDouble(5));
					
					/*
					if((period_val>=3) && (period_val<6))
						row_colour = "CCFFFF"; // lightblue
					else if(period_val>=6)
						row_colour = "FFCCFF"; //"FF6699" // red
					else
						row_colour = "FFFFFF";
					*/
					
					if(rs.getString(3)!=null && rs.getString(4)!=null && rs.getString(5)!=null && rs.getString(6)!=null && rs.getString(7)!=null && rs.getString(8)!=null&& rs.getString(9)!=null&& rs.getString(10)!=null&& rs.getString(11)!=null&& rs.getString(12)!=null&& rs.getString(13)!=null){
						m_total_rental =rs.getDouble(14); 
						out.println("<TR >");	
						
						
						
						out.println("<tr  id=tr_id"+j+">"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' >"+j+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_transaction_history_new('"+rs.getString(3)+"','"+rs.getString(2)+"')\" STYLE='{text-align:left;cursor:hand; }'   ><u>"+rs.getString(2)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(11)+"</td>"); //thamali 2012.03.29
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_client('"+rs.getString(3)+"')\"  class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString(4)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:left;cursor:hand}'  >"+rs.getString(6)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(12)+"</td>");//Added By Prabash on 09-08-2012
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(1)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:right;}'   >"+nf1.format(rs.getDouble(7))+"</td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > &nbsp;</td>");
						
						// thamali 2012.03.29
						if(Math.round(rs.getDouble(5)) >= 0 && Math.round(rs.getDouble(5)) < 1){
							out.println("<td  class=factoring-letter-body    >-</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 1 && Math.round(rs.getDouble(5)) < 2 ){
							out.println("<td  class=factoring-letter-body    >NR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 2 && Math.round(rs.getDouble(5)) < 3 ){
							out.println("<td  class=factoring-letter-body    >RR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 3 && Math.round(rs.getDouble(5)) < 4 ){
							out.println("<td  class=factoring-letter-body    >CV</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 4 ){
							out.println("<td  class=factoring-letter-body   >Z</td>"); 
						}
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(13))+"</td>");			
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  > &nbsp;</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand;text-align:right; >"+Math.round(rs.getDouble(5))+"</td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"add_client_comments('"+rs.getString(3)+"','"+rs.getString(2)+"')\"><u>Remarks</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"show_followup('"+rs.getString(2)+"')\"><u>"+rs.getString(8)+"</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(10)+"</td>");//Added By Lalanka on 15-07-2009
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString(16)+" </td>"); // added by udara on 03-10-2013
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString(17)+" </td>"); // added by udara 26-12-2013
						out.println("</tr>");
						total_mon_rental=total_mon_rental+rs.getDouble(7);
						total_open_bal=total_open_bal+rs.getDouble(13);
						j+=1;
					}
					else if( rs.getString(2)==null && rs.getString(1).equals("SUB TOTAL")){
						out.println("<tr>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><B>Total Rental</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(14))+" </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > <B>Total Arrears</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(15))+" </td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  // added by udara on 03-10-2013
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  // added by udara on 26-12-2013
						out.println("</tr>");
						
					}
					
					
					more=rs.next();
					count+=1;
					
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Grant Total</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_mon_rental)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_open_bal)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 03-10-2013
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 26-12-2013
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
					
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(rs3!=null){try{rs3.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
			
			
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}

