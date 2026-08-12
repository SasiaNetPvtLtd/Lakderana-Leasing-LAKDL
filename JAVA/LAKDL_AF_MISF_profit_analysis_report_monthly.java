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



public class LAKDL_AF_MISF_profit_analysis_report_monthly extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf,nf1;
		java.lang.Math a;
		Statement stmt,stmt2,stmt3;
		CallableStatement callstmt1 =null;
		ResultSet rs,rs1,rs2,rs3;
		
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
			
			stmt3 = conn.createStatement ();
			
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Profit Analysis Report - Monthly</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println(" if(!reportReady){");
				out.println("       durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println(" }else{");
				out.println("  reportReady = false;");
				out.println("  durationID=0;");
				out.println("}");
				out.println("}");
				
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10 (#16240)
				
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=run_report&region=\"+m_region+\"&date=\"+m_date;"); 
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("var reportReady = false;");
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			reportReady = true;"); 
				//out.println("			print_report2();"); // commented by udara 22-06-2018
				out.println("			alert('Profit Analysis Report - Monthly is generated. Use View Report button to get the view.');"); // added by udara 22-06-2018
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	   if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		 m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10(#16240)
				out.println("   m_product_name   = document.Form1.TXT_PRODUCT.value; "); // added by udara 11-10-2018
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=print_report_new&date=\"+m_date+\"&location=\"+document.Form1.TXT_LOCATION_CODE.value+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=print_report_new&date=\"+m_date+\"&location=\"+document.Form1.TXT_LOCATION_CODE.value+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&product_name=\"+m_product_name;");
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
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Senior Management - Profit Analysis Report - Monthly - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Senior Management - Summary Summary Report - \"+document.Form1.hid_status.value;"); 
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Summary_Movement_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
				out.println("load_interface(m_url,'NORM');");
				//out.println("window.open(m_url);");
				out.println("}"); 
				
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}"); 
				
				
				out.println("function print_report(date,m_location,m_officer) {");
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Summary_summery_report?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Senior Management - Profit Analysis Report - Monthly </td>"); 
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
				
				out.println("<br/>");  
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				
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
				
				// added by udara 11-10-2018
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PRODUCT'  class=div_input> Product Name </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_PRODUCT'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				rs3=stmt3.executeQuery(" SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE "+
					" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE  ACTIVE_STATUS='Y' ");
				
				while(rs3.next()){
					out.println("  <OPTION value=\""+rs3.getString(1)+"\">"+rs3.getString(2)+"</OPTION>");
				}
				
				out.println(" 	   </select>");
				out.println(" </td>"); 
				out.println("</tr>");
				// end by udara 11-10-2018
				
				
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
				
				stmt3 = conn.createStatement ();
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_last_month_start_date="";
				String m_this_month_start_date="";
				String m_cur_date="";
				
				String m_active_status = "";
				String m_active_status_string = "";
				
				String m_region = "";
				
				// added by udara 11-10-2018
				String m_product_name = "";
				
				if(req.getParameter("product_name") != null ){
					m_product_name = req.getParameter("product_name").trim();
				}
				// end by udara 11-10-2018
				
				// Added By Samith Dilshan on 2015-06-10
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
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Senior Management - Profit Analysis Report - Monthly</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report_monthly?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
			//	if(!m_location.equals("")){
				rs1 = stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'-'), TO_CHAR(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM')-1,'DD-Month-YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-Month-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_location_desc= rs1.getString(1);
					m_last_month_start_date=rs1.getString(2);
					m_this_month_start_date=rs1.getString(3);
				}
			  //  }
	
				
				
				String Sql_data="";
				
				//String Sql_data_a_t = ""; // added by udara test purpose 31-07-2019
				//String Sql_data_t = ""; // added by udara test purpose 31-07-2019
				
				
				// added by udara 14-10-2014
				Sql_data=" SELECT A.FINANCE_NO, NVL(A.CVALUE,0), NVL(A.RENTAL,0), NVL(A.INTEREST_PORTION,0), NVL(A.CAPITAL_PORTION,0), "+
                         "        NVL(A.PERIOD,0), NVL(A.NO_OF_RENTALS_UPTO_NOW,0), NVL(A.BALANCE_PERIOD,0), NVL(A.CAPITAL,0), "+
                         "        NVL(A.INTEREST,0), NVL(A.TOTAL,0), NVL(A.VALUE_OF_RENTAL_UPTO_NOW,0), "+
                         "        NVL(A.TOTAL_RENTAL_OVER_THE_PERIOD,0) "+
						 "       ,SUBSTR(A.FINANCE_NO,-4) FINANCE_NO_SORT ,"+ 
						 "            NVL (A.REGIONS_CODE, '-') REGIONS_CODE "+ // Added By: Samith Dilshan on 2015-06-08 for Region Code

						 "       ,NVL(RENT_COLLECT,0) "+ // 16 added by udara 16-07-2015
						 "       ,NVL(INT_COLLECT,0) "+  // 17 added by udara 16-07-2015
						 "       ,NVL(CAP_COLLECT,0) "+  // 18 added by udara 16-07-2015
							
                         "   FROM "+m_schema_name+".AF_RE_PROFIT_ANA_MONTHLY_RPT A"+
						 //"   WHERE A.ENT_USER = '"+m_username+"' "+
							" WHERE A.ENT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							//" AND PERIOD > 0   "+ // added by udara 07-08-2015
						 "     AND A.ACTIVATION_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+ // released by udara 25-08-2015 // commented by udara 07-08-2015
						 m_active_status_string +
						 //"     AND A.CONTRACT_STATUS = 'A' "; // commented by udara 07-08-2015
							"     AND A.CONTRACT_STATUS IN ('A','T') "; // added by udara 07-08-2015
				
				if(!m_location.equals("")){
					Sql_data += " AND A.LOCATION = '"+m_location+"' ";
				}
				
				
				if(!(m_region.equals("NOT_SELECT"))){    // Added By: Samith Dilshan on 2015-06-08 for Region Code
					Sql_data = Sql_data +"  AND  A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				// added by udara 11-10-2018
				if(!(m_product_name.equals("NOT_SELECT"))){    
					Sql_data = Sql_data +"  AND  A.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				}
				// end by udara 11-10-2018
					
				
				Sql_data += " ORDER BY  FINANCE_NO_SORT ";
						
			    //out.println(Sql_data);
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				if(!m_location.equals("")){
				    out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Profit Analysis Report - Monthly as at "+m_date+" Branch - "+m_location+"</u></td>"); 
			    }
				else{
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Profit Analysis Report - Monthly as at "+m_date+"</u></td>"); 
				}
					
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// Added By Samith Dulshan
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: 9pt arial; text-align:laft;}'   >Region : "+rs3.getString(1)+"</td>"); 
						out.println("</tr >");
						out.println("</table >");
					}
				}
				
				
				out.println("<br>");
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}else{
					
					out.println("<table  cellspacing=0 > "); 
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td> "); 
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
					
					
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>No</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Contract Number</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'><b>C.Value</b></td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Rental</b></td>"); 
					
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Rental Collected</b></td>"); // added by udara 16-07-2015 ///////////////
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>Interest Portion</b></td>"); 
					
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Interest Collected</b></td>"); // added by udara 16-07-2015 ///////////////
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Capital Portion</b></td>");
					
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Capital Collected</b></td>"); // added by udara 16-07-2015 //////////////
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Period</b></td>"); 	
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>No of Rentals upto now</b></td>"); 
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Balance Period</b></td>");    
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Future Capital</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Future Interest</b></td>");
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Value of Rental upto now</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Total Rental overthe period</b></td>");
					
					//out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Rental Collected</b></td>"); // added by udara 16-07-2015
					//out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Interest Collected</b></td>"); // added by udara 16-07-2015
					//out.println("<td class=factoring-letter-body STYLE='{ text-align:center; }' ><b>Capital Collected</b></td>"); // added by udara 16-07-2015
	
					out.println("</tr >");
					
					
					

					BigDecimal m_total_rental,m_total_capital,m_total_interest,m_total, m_interest_portion,m_capital_portion,m_value_of_rental_up_to_now,m_total_rental_over_the_period;
					BigDecimal m_tot_rent_collect,m_tot_int_collect,m_tot_cap_collect; // added by udara 16-07-2015
					
					m_total_rental= m_total_capital = m_total_interest = m_total =  m_interest_portion = m_capital_portion = m_value_of_rental_up_to_now = m_total_rental_over_the_period = new BigDecimal(0.00);
					m_tot_rent_collect=m_tot_int_collect=m_tot_cap_collect= new BigDecimal(0.00); // added by udara 16-07-2015
					
					int j = 1;
					while(more){
						
						m_tot_rent_collect = m_tot_rent_collect.add(rs.getBigDecimal(16)); // added by udara 16-07-2015
						m_tot_int_collect  = m_tot_int_collect.add(rs.getBigDecimal(17));  // added by udara 16-07-2015
						m_tot_cap_collect  = m_tot_cap_collect.add(rs.getBigDecimal(18));  // added by udara 16-07-2015
						
						out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						out.println("<td class=factoring-letter-body ><b>"+j+"</b></td>"); 
						out.println("<td class=factoring-letter-body  STYLE='{text-align:left; }'>"+rs.getString(1)+"</td>");

						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(2))+"</td>");
						m_total_rental = m_total_rental.add(rs.getBigDecimal(3));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(3))+"</td>"); 
						
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' > "+nf.format(rs.getBigDecimal(16))+" </td>"); // added by udara 16-07-2015 ///////////////
						
						m_interest_portion = m_interest_portion.add(rs.getBigDecimal(4));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' > "+nf.format(rs.getBigDecimal(17))+" </td>"); // added by udara 16-07-2015 ///////////////
						
						m_capital_portion = m_capital_portion.add(rs.getBigDecimal(5));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(5))+"</td>");
						
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' > "+nf.format(rs.getBigDecimal(18))+" </td>"); // added by udara 16-07-2015 ///////////////
						
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf1.format(rs.getInt(6))+"</td>"); 	
						out.println("<td class=factoreightitengpw82ing-letter-body  STYLE='{text-align:right; }' >"+nf1.format(rs.getInt(7))+"</td>"); 
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf1.format(rs.getInt(8))+"</td>");    
						m_total_capital = m_total_capital.add(rs.getBigDecimal(9));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(9))+"</td>"); 
						m_total_interest = m_total_interest.add(rs.getBigDecimal(10));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(10))+"</td>"); 
						m_total = m_total.add(rs.getBigDecimal(11));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(11))+"</td>"); 
						m_value_of_rental_up_to_now = m_value_of_rental_up_to_now.add(rs.getBigDecimal(12));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(12))+"</td>");
						m_total_rental_over_the_period = m_total_rental_over_the_period.add(rs.getBigDecimal(13));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(13))+"</td>"); 

						
						
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' > "+nf.format(rs.getBigDecimal(16))+" </td>"); // added by udara 16-07-2015
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' > "+nf.format(rs.getBigDecimal(17))+" </td>"); // added by udara 16-07-2015
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' > "+nf.format(rs.getBigDecimal(18))+" </td>"); // added by udara 16-07-2015
	
						out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b></b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b></b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_tot_rent_collect)+"</b></td>"); // added by udara 16-07-2015 ////////////////
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_interest_portion)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_tot_int_collect)+"</b></td>"); // added by udara 16-07-2015 ////////////////
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_capital_portion)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_tot_cap_collect)+"</b></td>"); // added by udara 16-07-2015 ////////////////
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b></b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b></b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_capital)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_interest)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_value_of_rental_up_to_now)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental_over_the_period)+"</b></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_tot_rent_collect)+"</b></td>"); // added by udara 16-07-2015
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_tot_int_collect)+"</b></td>"); // added by udara 16-07-2015
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_tot_cap_collect)+"</b></td>"); // added by udara 16-07-2015

					out.println("</tr >");
				
					out.println("</tr>");		
					out.println("</table>");
				    out.println("</td> "); 
					out.println("</tr>");	
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td>&nbsp; ");
					out.println("</td> ");
					out.println("</tr> "); 
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td> ");
					out.println("<B><U>SUMMARY</U></B> ");
					out.println("</td> ");
					out.println("</tr> "); 
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td>&nbsp; ");
					out.println("</td> ");
					out.println("</tr> "); 
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td> ");
					out.println("<table>");		
					out.println("<tr>");		
					out.println("<td width='60'>&nbsp;</td> "); 
					out.println("<td>&nbsp;</td> "); 
					
					BigDecimal capital_value_of_new_cases = new  BigDecimal(0.00);
					BigDecimal capital_value_of_premature_cases = new  BigDecimal(0.00);
					BigDecimal total_capital_value = new  BigDecimal(0.00);
					BigDecimal interest_value_of_new_cases = new  BigDecimal(0.00);
					BigDecimal interest_value_of_premature_cases = new  BigDecimal(0.00);
					BigDecimal total_interest_value = new  BigDecimal(0.00);
					
					
						Sql_data=" SELECT SUM(A.CAPITAL),  SUM(A.INTEREST) "+
                         "   FROM "+m_schema_name+".AF_RE_PROFIT_ANA_MONTHLY_RPT A"+
						//"   WHERE A.ENT_USER = '"+m_username+"' "+ // commented by udara 07-08-2018
						"   WHERE A.ENT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						"   AND TRUNC(A.ACTIVATION_DATE,'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						m_active_status_string +
						//"     AND A.CONTRACT_STATUS = 'A' "; // commented by udara 22-09-2015
						"     AND A.CONTRACT_STATUS IN ('A','T') "; // added by udara 22-09-2015
				
				if(!m_location.equals("")){
					Sql_data += " AND A.LOCATION = '"+m_location+"' ";
				}
				
				// added by udara 31-07-2019
				if(!(m_region.equals("NOT_SELECT"))){    
					Sql_data = Sql_data +"  AND  A.REGIONS_CODE = '"+m_region+"' ";    
				}
				if(!(m_product_name.equals("NOT_SELECT"))){    
					Sql_data = Sql_data +"  AND  A.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				}
				// end by udara 31-07-2019
				
				Sql_data += " GROUP BY  TRUNC(A.ACTIVATION_DATE,'MM') ";
						
			//out.println(Sql_data);
			
			//Sql_data_a_t = Sql_data; // added by udara 31-07-2019 for test purpose
			
				rs=stmt.executeQuery(Sql_data);
				
				if(rs.next()){
					capital_value_of_new_cases = capital_value_of_new_cases.add(rs.getBigDecimal(1));
					interest_value_of_new_cases = interest_value_of_new_cases.add(rs.getBigDecimal(2));
				}
				
				// commented by udara 25-08-2015
				
				
				// added by udara 25-08-2015
				Sql_data=" SELECT NVL(SUM(A.CAPITAL),0),  NVL(SUM(A.INTEREST),0) "+
                         "   FROM "+m_schema_name+".AF_RE_PROFIT_ANA_MONTHLY_RPT A, "+m_schema_name+".AF_CR_PRO_TERMINATION B "+
						 //"   WHERE A.ENT_USER = '"+m_username+"' "+ // commented by udara 07-08-2018
						 "   WHERE A.ENT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						 "   AND  A.APPLICATION_NO = B.APPLICATION_NO  "+
						 "   AND  B.ACTIVE_STATUS = 'TERM_CHECK' "+
						 "   AND B.TERMINATION_VALIDITY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY' ) "+
						 m_active_status_string +
						 "   AND A.CONTRACT_STATUS = 'T' ";
				
				if(!m_location.equals("")){
					Sql_data += " AND A.LOCATION = '"+m_location+"' ";
				}
				// end by udara 25-08-2015
				
				// added by udara 31-07-2019
				if(!(m_region.equals("NOT_SELECT"))){    
					Sql_data = Sql_data +"  AND  A.REGIONS_CODE = '"+m_region+"' ";    
				}
				if(!(m_product_name.equals("NOT_SELECT"))){    
					Sql_data = Sql_data +"  AND  A.TRANSACTION_TYPE = '"+m_product_name+"' ";    
				}
				// end by udara 31-07-2019
				
				//Sql_data += " GROUP BY  TRUNC(A.ACTIVATION_DATE,'MM') ";
						
			//out.println(Sql_data);
			
			//Sql_data_t = Sql_data; // added by udara 31-07-2019 for test purpose
			
				rs=stmt.executeQuery(Sql_data);
				
				if(rs.next()){
					capital_value_of_premature_cases = capital_value_of_premature_cases.add(rs.getBigDecimal(1));
					interest_value_of_premature_cases = interest_value_of_premature_cases.add(rs.getBigDecimal(2));
				}
				
					
					out.println("<td width='300'>Future Capital of Cases upto "+m_last_month_start_date+"</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;'><B>"+nf.format(m_total_capital)+"</B></td> "); 
					out.println("</tr>");		
					out.println("<tr>");
					out.println("<td width='60'>Add:</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>Future Capital of new cases - "+m_this_month_start_date+"</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;'><B>"+nf.format(capital_value_of_new_cases)+"</B></td> "); 
					out.println("</tr>");		
					out.println("<tr>");
					out.println("<td width='60'>Less:</td> ");
				    out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>Capital Value (Future) of Prematured Cases</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;'><B>"+nf.format(capital_value_of_premature_cases)+"</B></td> "); 
					total_capital_value = total_capital_value.add(m_total_capital).add(capital_value_of_new_cases).subtract(capital_value_of_premature_cases);
					out.println("</tr>");		
					out.println("<tr>");
					out.println("<td width='60'>&nbsp;</td> ");
				    out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>&nbsp;</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;border-color:black;border-top-style:solid;border-bottom-style:solid;border-top-width:2 px;border-bottom-width:2 px;'><B>"+nf.format(total_capital_value)+"</B></td> "); 
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='60'>&nbsp;</td> ");
				    out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>&nbsp;</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("</tr>");
					out.println("<tr>");		
					out.println("<td width='60'>&nbsp;</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>Unearned Interest of Cases upto "+m_last_month_start_date+"</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;'><B>"+nf.format(m_total_interest)+"</B></td> "); 
					out.println("</tr>");		
					out.println("<tr>");
					out.println("<td width='60'>Add:</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>Interest of new Cases - "+m_this_month_start_date+"</td> "); 
					out.println("<td >&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;'><B>"+nf.format(interest_value_of_new_cases)+"</B></td> "); 
					out.println("</tr>");		
					out.println("<tr>");
					out.println("<td width='60'>Less:</td> ");
				    out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>Future Interest of Prematured Cases</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;'><B>"+nf.format(interest_value_of_premature_cases)+"</B></td> "); 
					total_interest_value = total_interest_value.add(m_total_interest).add(interest_value_of_new_cases).subtract(interest_value_of_premature_cases);
					out.println("</tr>");		
					out.println("<tr>");
					out.println("<td width='60'>&nbsp;</td> ");
				    out.println("<td>&nbsp;</td> "); 
					out.println("<td width='300'>&nbsp;</td> "); 
					out.println("<td>&nbsp;</td> "); 
					out.println("<td STYLE='text-align:right;border-color:black;border-top-style:solid;border-bottom-style:solid;border-top-width:2 px;border-bottom-width:2 px;''><B>"+nf.format(total_interest_value)+"</B></td> "); 
					out.println("</tr>");
					out.println("</table>");	
					
					out.println("</td> "); 
					out.println("</tr>");		
					out.println("</table>");	
					
					
					
					
				}
				
				// added by udara 31-07-2019 for test purpose
				/*
				out.println("Sql_data_a_t");
				out.println(Sql_data_a_t);
				out.println("=============");
				out.println("Sql_data_t");
				out.println(Sql_data_t);
				*/
				// end by udara 31-07-2019 for test purpose
				
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			//}
		}
		
		
		catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
