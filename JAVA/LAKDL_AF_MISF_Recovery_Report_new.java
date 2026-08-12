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


public class LAKDL_AF_MISF_Recovery_Report_new extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2,rs_drill_new;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
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
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_RECOVER_RPT(:1,:2,:3,:4,:5);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_user_id);
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_finance_no);
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
				out.println("<TITLE>Collection - Recovery Report</TITLE>"); 
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
				out.println("finance_no = document.Form1.TXT_FINANCE.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value;"); 
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
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
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("		m_officer=document.Form1.TXT_USER.value;");
				
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date;");	
				out.println("var perform_status = document.Form1.TXT_PERFORM_STATUS.value;     ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status;");  // added by udara on 09-05-2013
				
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
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Recovery Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Recovery Report - \"+document.Form1.hid_status.value;"); 
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Recovery Report </td>"); 
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
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='10' style='{width=150px}' size='10' onblur='help_button_finance()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				
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
				out.println("<TITLE>Collection - Collection Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println(m_string);
				out.println("</html>");
				
			}
			
			else if(m_chksql.equals("print_report_new")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				out.println("function show_drill(val){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val;");	
				
				out.println("window.open(m_url,'slab','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
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
				
				
				Sql_data=" SELECT  "+
					" NVL( A.TOTAL_PERIOD,0), "+ //1
					" COUNT(*), "+ //2
					" NVL (SUM(DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) total_arrears, "+ //3
					" SUM( RENTAL_AMOUNT) , "+ //4
					" SUM (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					" SUM (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					" SUM (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL (SUM (total_amount), 0), "+ //8
					" NVL (SUM (CLOSING_BALANCE), 0), "+ //9
					" NVL (SUM (SETTLED_AMOUNT), 0), "+ //10
					" NVL (SUM (ADJUSTED_AMOUNT), 0), "+ //11
					" NVL(SUM(FUTURE_RENTAL),0),"+//12
					" NVL (SUM(DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),0) EARLY_SETTLE,"+//13
					" NVL (SUM(DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT,0)),0) EARLY_SETTLE_NEXT_MONTH,"+//14
					" NVL (SUM(DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					//" NVL(SUM(SETTLED_OTH_FROM_EXCESS),0)"+//16
					" SUM(DECODE(SIGN(TOTAL_AMOUNT),-1,NVL(SETTLED_OTH_FROM_EXCESS,0),0)) "+ // 15 added by udara on 02-05-2013
					
					" FROM "+m_schema_name+".AF_RE_TBD_BRANCH_REPORT A"+
					" WHERE ENT_USER='"+m_username+"' "+
					" AND ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
					" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
					//" AND TRUNC(A.ENT_DATE) = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+ // added by udara on 09-05-2013
					" GROUP BY A.TOTAL_PERIOD ORDER BY A.TOTAL_PERIOD "; 
				
				//out.println(Sql_data);
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				/*		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						
					
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
						out.println("</table>");
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_start_date+"</td>"); 
						out.println("<td width=\"50\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_end_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("</table>");
					*/	
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table  cellspacing=0 > "); 
				out.println("<tr> "); 
				out.println("<td width='20'> "); 
				out.println("</td> "); 
				out.println("<td> "); 
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				//	out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>SLAB</b></td>"); 
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>No. of Cases</b></td>"); 
				out.println("<td width='40%' class=div_input colspan=\"5\" align='center' bgcolor='lightblue' ><B> AMOUNTS TO BE RECOVERED</B></td>");
				out.println("<td width='40%' class=div_input colspan=\"9\" align='center' bgcolor='lightblue' ><B> AMOUNTS RECOVERED</B></td>"); // modifeid by udara on 02-05-2013 colspan=\"8\" to colspan=\"9\" 
				out.println("<td width=\"5%\"  align='center'ROWSPAN='2'  ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>SLAB</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Total Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Monthly Due Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total (A+B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Rental Collected During the Period</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Arrears Collection</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Future Rentals</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Closing</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				
				
				//double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				//double  m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				//out.println("<table id=mytable align=\"left\" width=1750px border=\"1\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
				
				while(more){
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;cursor:pointer;}' onclick= \"show_drill('"+rs.getString(1)+"')\" ><u>"+nf1.format(rs.getDouble(2))+"</u></td>");
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					//comment by ns on 06-03-2013
					/*
					if (rs.getBigDecimal(8).doubleValue() < 0)
					{
						
						
						early_sett = early_sett.add(rs.getBigDecimal(13));
						early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					}	
					
					else
					{
						early_sett = new BigDecimal(0.00) ;
					}
					*/
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					if(early_sett_next_month.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett_next_month.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett_next_month)+"</td>");
					}
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					
					
					if(Other_charges_coll_from_excess.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Other_charges_coll_from_excess.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Other_charges_coll_from_excess)+"</td>");
					}
					// end by udara on 02-05-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					Rent_Collec = Rent_Collec.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					
					/*
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(early_sett)).doubleValue()){ 
						Rent_Collec = rs.getBigDecimal(4).subtract(early_sett);
					}
					*/
					
					
					tot_rent = new BigDecimal(0.00);
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); //added by ns on 03-05-2013
					
					
					/*
					if(rs.getDouble(4) < (tot_rent.subtract(rs.getBigDecimal(10))).doubleValue()){
						futu_rent = futu_rent.add(new BigDecimal((tot_rent.subtract(rs.getBigDecimal(10))).doubleValue()-rs.getDouble(4)));
					}
					*/
					
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(12).subtract(early_sett_next_month));
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					
					closing = new BigDecimal(0.00);
					closing = closing.add(rs.getBigDecimal(9));
					Cash_Total = new BigDecimal(0.00);
					
					//futu_rent =  Cash_Total.subtract(closing).subtract(Arrea_Collec).subtract(Rent_Collec);
					
					
					
					if(futu_rent.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					//Cash_Total = Cash_Total.add(rs.getBigDecimal(7));
					
					//closing  = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9));
					//Cash_Total = new BigDecimal(0.00);
					Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					
					BCF = new BigDecimal(0.00);
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ;
					
					if(closing.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(closing.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(closing)+"</td>");
					}
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					if(BCF.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(BCF.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(BCF)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					if(total_Arrears.signum()>0){
						total_Arrears_Pres=total_Arrears_Pres.add(total_Arresrs_colle.multiply(new BigDecimal(100)).divide(total_Arrears,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					total_cash = total_cash.add(Cash_Total);
					total_bcf= total_bcf.add(BCF);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					
					
					// added by udara on 27-05-2013
					
					Sql_data = "  "+
								" SELECT "+ 
									 " NVL( A.TOTAL_PERIOD,0),  "+ // 1
									 " COUNT(*),  "+ // 2
									 " NVL (SUM(DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0)+ NVL (SUM (A.OTHER_CHARGES_CURR_MONTH), 0) TOTAL_ARREARS,  "+ // 3
									 " SUM( RENTAL_AMOUNT) , "+ // 4
									 " SUM (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ // 5
									 " SUM (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ // 6
									 " SUM (A.SETTLED_AMOUNT_CUR_MON), "+ // 7   
									 " NVL (SUM (TOTAL_AMOUNT), 0), "+ // 8
									 " NVL (SUM (CLOSING_BALANCE), 0), "+ // 9
									 " NVL (SUM (SETTLED_AMOUNT), 0), "+ // 10
									 " NVL (SUM (ADJUSTED_AMOUNT), 0), "+ // 11
									 " NVL(SUM(FUTURE_RENTAL),0), "+ // 12
									 " NVL (SUM(DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),0) EARLY_SETTLE, "+ // 13
									 " NVL (SUM(DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT,0)),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
									 " NVL (SUM(DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+ // 15
									 " SUM(DECODE(SIGN(TOTAL_AMOUNT),-1,NVL(SETTLED_OTH_FROM_EXCESS,0),0)) "+ // 16                
										 " FROM "+m_schema_name+".AF_RE_TBD_BRANCH_REPORT A "+
										 " WHERE ENT_USER='"+m_username+"'  "+
										 " AND ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
										 " AND UPPER(COLLECTION_OFFICER) like UPPER('%"+m_officer+"%') "+
										 " AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
										 " AND A.TOTAL_PERIOD > 0 "+
										 " AND STATUS IN ('1','2')  "+
									 	 " AND A.ACTIVATED_DATE >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
									 	 " AND A.ACTIVATED_DATE <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
									 	 " AND PERFORM_STATUS LIKE '"+m_perform_status+"%'  "+
									 	 " GROUP BY A.TOTAL_PERIOD ORDER BY A.TOTAL_PERIOD "+
									" ";
				
					
					BigDecimal total_Cases_new      = new BigDecimal(0.00); 
					BigDecimal total_Arrears_new    = new BigDecimal(0.00); 
					BigDecimal total_Due_Rental_new = new BigDecimal(0.00);
					BigDecimal totalab_new          = new BigDecimal(0.00);
					BigDecimal total_AB_new         = new BigDecimal(0.00);
					BigDecimal early_sett_new       = new BigDecimal(0.00);
					BigDecimal total_Ear_Sett_new   = new BigDecimal(0.00);
					BigDecimal early_sett_next_month_new = new BigDecimal(0.00);
					BigDecimal total_early_sett_next_month_new = new BigDecimal(0.00);
					BigDecimal Other_charges_coll_from_excess_new = new BigDecimal(0.00);
					BigDecimal total_Other_charges_coll_from_excess_new = new BigDecimal(0.00);
					BigDecimal Rent_Collec_new  = new BigDecimal(0.00);
					BigDecimal total_Rental_colle_new = new BigDecimal(0.00);
					BigDecimal tot_rent_new = new BigDecimal(0.00);
					BigDecimal total_Rent_new = new BigDecimal(0.00);
					BigDecimal total_Rental_Pres_new = new BigDecimal(0.00);
					BigDecimal Arrea_Collec_new = new BigDecimal(0.00);
					BigDecimal total_Arresrs_colle_new = new BigDecimal(0.00);
					BigDecimal total_Arrears_Pres_new = new BigDecimal(0.00);
					BigDecimal closing_new = new BigDecimal(0.00);
					BigDecimal total_Closing_new = new BigDecimal(0.00);
					BigDecimal total_Future_ren_new = new BigDecimal(0.00);
					BigDecimal total_cash_new = new BigDecimal(0.00);
					BigDecimal total_bcf_new = new BigDecimal(0.00);
					BigDecimal futu_rent_new = new BigDecimal(0.00);
					BigDecimal BCF_new = new BigDecimal(0.00);
					BigDecimal Cash_Total_new = new BigDecimal(0.00); 
					
					rs=stmt.executeQuery(Sql_data);
					
					while(rs.next()){
						
						
						total_Cases_new =total_Cases_new.add(rs.getBigDecimal(2));
						
						if(rs.getBigDecimal(3).signum()>=0){   
					      total_Arrears_new = total_Arrears_new.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					    }else{
					      total_Arrears_new = total_Arrears_new.add(rs.getBigDecimal(11));
					    }
						
						total_Due_Rental_new = total_Due_Rental_new.add(rs.getBigDecimal(4));
						
						totalab_new = new BigDecimal(0.00) ;
						if((rs.getBigDecimal(3)).signum()>=0){
						      totalab_new = totalab_new.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
						}else{
						     totalab_new  = totalab_new.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
						}
						total_AB_new = total_AB_new.add(totalab_new);
						
						early_sett_new = new BigDecimal(0.00) ;
						early_sett_new = early_sett_new.add(rs.getBigDecimal(13));
						total_Ear_Sett_new = total_Ear_Sett_new.add(early_sett_new);
						
						early_sett_next_month_new       = new BigDecimal(0.00);
						early_sett_next_month_new       = early_sett_next_month_new.add(rs.getBigDecimal(14));
						total_early_sett_next_month_new = total_early_sett_next_month_new.add(early_sett_next_month_new);
						
						
						Other_charges_coll_from_excess_new       = new BigDecimal(0.00);
						Other_charges_coll_from_excess_new       = Other_charges_coll_from_excess_new.add(rs.getBigDecimal(16)); 
						total_Other_charges_coll_from_excess_new = total_Other_charges_coll_from_excess_new.add(Other_charges_coll_from_excess_new);

						Rent_Collec_new = new BigDecimal(0.00);
						Rent_Collec_new = Rent_Collec_new.add(Other_charges_coll_from_excess_new);
						total_Rental_colle_new = total_Rental_colle_new.add(Rent_Collec_new);
						
						
						tot_rent_new   = new BigDecimal(0.00);
						//tot_rent_new   = tot_rent.add(early_sett_new).add(Rent_Collec_new).subtract(Other_charges_coll_from_excess_new); 
						tot_rent_new   = tot_rent_new.add(early_sett_new).add(Rent_Collec_new).subtract(Other_charges_coll_from_excess_new);
						total_Rent_new = total_Rent_new.add(tot_rent_new);

						total_Rental_Pres_new  = new BigDecimal(0.00);
						if(total_Due_Rental_new.signum()>0){
						      total_Rental_Pres_new= total_Rental_Pres_new.add(total_Rent_new.multiply(new BigDecimal(100)).divide(total_Due_Rental_new,BigDecimal.ROUND_HALF_EVEN));
						}
						
						Arrea_Collec_new  = new BigDecimal(0.00);
						Arrea_Collec_new = Arrea_Collec_new.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));
						total_Arresrs_colle_new = total_Arresrs_colle_new.add(Arrea_Collec_new);
						
						
						total_Arrears_Pres = new BigDecimal(0.00);
						if(total_Arrears_new.signum()>0){
					      total_Arrears_Pres_new=total_Arrears_Pres_new.add(total_Arresrs_colle_new.multiply(new BigDecimal(100)).divide(total_Arrears_new,BigDecimal.ROUND_HALF_EVEN));
					    }
						
						closing_new = new BigDecimal(0.00);
						closing_new = closing_new.add(rs.getBigDecimal(9));
						total_Closing_new =total_Closing_new.add(closing_new);
						
						futu_rent_new = new BigDecimal(0.00);
						futu_rent_new = futu_rent_new.add(rs.getBigDecimal(12).subtract(early_sett_next_month_new));
						total_Future_ren_new = total_Future_ren_new.add(futu_rent_new);

						Cash_Total_new = new BigDecimal(0.00);
						Cash_Total_new = Cash_Total_new.add(closing_new).add(Arrea_Collec_new).add(Rent_Collec_new).add(futu_rent_new);
						//Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
						
						BCF_new = new BigDecimal(0.00);
					    BCF_new = BCF_new.add(totalab_new).subtract(Cash_Total_new).subtract(early_sett_new).subtract(early_sett_next_month_new).add(closing_new) ;
					    total_cash_new = total_cash_new.add(Cash_Total_new);
					    total_bcf_new = total_bcf_new.add(BCF_new);
							
					}
					
					/*adding new row nad the total added by ns on 14-06-2013*/
					total_cash = total_cash.add(total_cash_new);
					
					// added below by udara on 14-06-2013
					total_Arrears      = total_Arrears.add(total_Arrears_new);
					total_Due_Rental   = total_Due_Rental.add(total_Due_Rental_new);
					total_AB           = total_AB.add(total_AB_new);
					total_Ear_Sett     = total_Ear_Sett.add(total_Ear_Sett_new);
					total_early_sett_next_month          = total_early_sett_next_month.add(total_early_sett_next_month_new);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(total_Other_charges_coll_from_excess_new);
					total_Rental_colle  = total_Rental_colle.add(total_Rental_colle_new);
					total_Rent          = total_Rent.add(total_Rent_new);
					total_Rental_Pres   = total_Rental_Pres.add(total_Rental_Pres_new);
					total_Arresrs_colle = total_Arresrs_colle.add(total_Arresrs_colle_new);
					total_Arrears_Pres  = total_Arrears_Pres.add(total_Arrears_Pres_new);
					total_Future_ren    = total_Future_ren.add(total_Future_ren_new);
					total_Closing       = total_Closing.add(total_Closing_new);
					total_bcf           = total_bcf.add(total_bcf_new);
					// end below by udara on 14-06-2013
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>New</b></td>"); 
					if(total_Cases_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}' STYLE='{text-align:right;cursor:pointer;}' onclick= \"show_drill('NEW')\"  ><b>"+nf1.format(total_Cases_new)+"</b></td>"); 
					}
					
					if(total_Arrears_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_new)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental_new)+"</b></td>"); 
					}
					
					
					if(total_AB_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB_new.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB_new)+"</b></td>");
					}
					
					
					if(total_Ear_Sett_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett_new.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett_new)+"</b></td>");  
					}
					
					if(total_early_sett_next_month_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_early_sett_next_month_new.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_early_sett_next_month_new)+"</b></td>");  
					}
					
					
					// added by udara on 02-05-2013
					if(total_Other_charges_coll_from_excess_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess_new)+"</b></td>"); 
					}
					// end by udara on 02-05-2013
					
					if(total_Rental_colle_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle_new)+"</b></td>"); 
					}
					
					
					if(total_Rent_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent_new.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent_new)+"</b></td>");
					}
					
					
					if(total_Rental_Pres_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres_new.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres_new)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle_new)+"</b></td>"); 
					}
					
					
					if(total_Arrears_Pres_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres_new.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres_new)+"%</b></td>"); 
					}
					
					
					//total_Future_ren_new = total_Future_ren_new.add(m_new_future_rental);
					if(total_Future_ren_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren_new)+"</b></td>"); 
					}
					
					
					if(total_Closing_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing_new)+"</b></td>"); 
					}
					
					//total_cash_new = total_cash_new.add(m_new_future_rental);
					
					if(total_cash_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash_new)+"</b></td>"); 
					}
					
					//total_bcf_new = total_bcf_new.subtract(m_new_future_rental);
					if(total_bcf_new.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf_new.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf_new)+"</b></td>"); 
					}
					
					
					
					out.println("</tr>");	
					
					
					
					
					
					
					// end by udara on 27-05-2013
					
					// commented by udara on 27-05-2013
					/*
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>New</b></td>"); 
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");  
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");  
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");  // added by udara on 02-05-2013
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>");
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					if(m_new_future_rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental.negate())+")</td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >"+nf.format(m_new_future_rental)+"</td>"); 
					}
					
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >&nbsp;</td>"); 
					
					
					
					if(m_new_future_rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental.negate())+")</td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >"+nf.format(m_new_future_rental)+"</td>"); 
					}
					
					
					if(m_new_future_rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental.negate())+")</td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   >("+nf.format(m_new_future_rental)+")</td>"); 
					}
					
					
					
					out.println("</tr>");	
					*/
					
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					if(total_early_sett_next_month.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_early_sett_next_month.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_early_sett_next_month)+"</b></td>");  
					}
					
					
					// added by udara on 02-05-2013
					if(total_Other_charges_coll_from_excess.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess)+"</b></td>"); 
					}
					// end by udara on 02-05-2013
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					
					total_Future_ren = total_Future_ren.add(m_new_future_rental);
					if(total_Future_ren.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren)+"</b></td>"); 
					}
					
					
					if(total_Closing.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing)+"</b></td>"); 
					}
					
					total_cash = total_cash.add(m_new_future_rental);
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					total_bcf = total_bcf.subtract(m_new_future_rental);
					if(total_bcf.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf)+"</b></td>"); 
					}
					
					
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
				
				/*		out.println("<tr>");		
				out.println("<td  colspan=18 >"); 
						out.println("<table align=\"left\" width=300px border=\"0\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  ><b>Months</td>"); 
						out.println("<td  ><b>Targets</td>"); 
						out.println("<td  ><b>Acheivement</td>"); 
						out.println("<td  ><b>+/-</td>"); 
						out.println("</tr>");	
				*/
				
				/*
				
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=0");
						
					if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=1");
						
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\" >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=2");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=3");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=4");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=5");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\"> ");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>");  
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=6");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
					
						out.println("</tr>");		
						}
						
				*/		
				
				/*
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				
				
				
				out.println("<br/>");	
				out.println("<br/>");	
				out.println("</td> "); 
				out.println("</tr>");	
				
				out.println("<tr> "); 
				out.println("<td width='20'> ");
				out.println("</td> ");
				
				out.println("<td>");
				*/
				Sql_data=" SELECT A.PENALTY, A.INVOICE, A.RENTAL_ARREARS, A.INSURANCE, A.BBF, A.OPEN_CONTRACT_BAL "+
					" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
					" WHERE ENTER_USER='"+m_username+"' "+
					" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
				
				
				
				rs=stmt.executeQuery(Sql_data);
				
				BigDecimal m_panalty = new BigDecimal(0.00);
				BigDecimal m_invoice = new BigDecimal(0.00);
				BigDecimal m_rental = new BigDecimal(0.00);
				BigDecimal m_insurance = new BigDecimal(0.00);
				BigDecimal m_bbf = new BigDecimal(0.00);
				
				BigDecimal m_open_con_bal = new BigDecimal(0.00); // added by udara on 28-05-2013
				
				while(rs.next()){
					m_panalty = rs.getBigDecimal(1);
					m_invoice = rs.getBigDecimal(2);
					m_rental = rs.getBigDecimal(3);
					m_insurance = rs.getBigDecimal(4);
					m_bbf = rs.getBigDecimal(5);
					m_open_con_bal = rs.getBigDecimal(6); // added by udara on 28-05-2013
				}
				
				//out.println("<table width='100%'>");
				
				out.println("<tr >"); 
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udar on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>"); 
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr  >"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("<u>* Recovered from B/B/F</u>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("<u>ADJUSTMENTS</u>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr  style='border-top-width:0px;'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr  style='border-width:0px;'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udsara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("Penalty");
				out.println("</td>");
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_panalty));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' align='left'>");
				out.println("Insurance");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_insurance));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr  style='border-top-width:0px;'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("Invoice");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_invoice));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' align='left'>");
				out.println("Closing");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(total_Closing));
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0;text-align:left'>");
				out.println("Rental Arrears");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(m_rental));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' align='left'>");
				out.println("Future Rentals");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");	
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println(nf.format(total_Future_ren));
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("Add:");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0' COLSPAN='2' align='left'>");
				out.println("Early Settlment Next Month");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println(nf.format(total_early_sett_next_month));
				out.println("</td>");
				
				out.println("</tr>");
				
				
				
				out.println("<tr style='text-align:right'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // adde4d by udara on 02-05-2013
				
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				out.println("<B>"+nf.format(m_panalty.add(m_invoice).add(m_rental))+"</B>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				out.println("<B>"+nf.format(total_cash.add(m_insurance))+"</B>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udarea on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				//out.println("<td style='border-style:none;z-index: 0'>");
				//out.println("&nbsp;</td>");
				out.println("<td style='text-align:left;border-style:none;z-index: 0' COLSPAN='2' >"); 
				out.println("<B>B/B/F</B></td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				// commented by udara on 28-05-2013
				/*
				if(total_Arrears.subtract(m_bbf).signum() >=0 ){
					out.println(nf.format(total_Arrears.subtract(m_bbf)));
				}else{
					out.println("("+nf.format((total_Arrears.subtract(m_bbf)).negate())+")");
				}
				*/
				
				
				if(m_open_con_bal.signum() >=0 ){
					out.println(nf.format(m_open_con_bal));
				}else{
					out.println("("+nf.format(m_open_con_bal.negate())+")");
				}
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0;border-top-color:black;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-bottom-style:double;border-bottom-width:6px'>"); 
				out.println(nf.format(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month)));
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr style='text-align:right'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				
				out.println("</td>");
				
				out.println("</tr>");
				
				
				
				out.println("<tr style='text-align:right'>"); 
				/*
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				*/
				out.println("<td style='text-align:letf;border-style:none;z-index: 0' COLSPAN='2'>"); 
				out.println("During the Month Arrears</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				
				
				out.println(nf.format(m_bbf));
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("Net[Increase]/Decrease");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>"); 
				/*if((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing)).signum() >=0 ){
					out.println(nf.format(m_bbf.subtract(total_bcf.add(total_Future_ren).add(total_Closing))));
				}else{
					out.println("("+nf.format(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).negate())+")");
				}
				*/
				// added by udara on 11-06-2013
				if((m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))).signum()>=0){
					out.println(nf.format(m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))));
				}
				else{
					out.println(nf.format((m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))).negate()));
				}
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				//out.println("<td style='border-style:none;z-index: 0'>");
				//out.println("</td>");
				out.println("<td style='text-align:left;border-style:none;z-index: 0' COLSPAN='2'>"); 
				out.println("Total Arears</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>");
				out.println(nf.format(total_Arrears));
				
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>"); // added by udara on 02-05-2013
				
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='text-align:right;border-style:none;z-index: 0'>");
				// commented by udara on 14-06-2013
				/*
				if(total_Arrears.subtract(m_bbf).signum() != 0){
					if(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(total_Arrears.subtract(m_bbf),BigDecimal.ROUND_HALF_EVEN).signum() >=0 ){
						out.println(nf.format(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(total_Arrears.subtract(m_bbf),BigDecimal.ROUND_HALF_EVEN)));
					}else{
						out.println("("+nf.format(((total_Arrears.subtract(m_bbf)).subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(total_Arrears.subtract(m_bbf),BigDecimal.ROUND_HALF_EVEN).negate())+")");
					}
					
				} else{
					out.println("0.00");
				}
				*/
				//out.println(nf.format((m_bbf.subtract(total_bcf.add(total_Future_ren).add(total_Closing))).multiply(new BigDecimal(100)).divide(m_bbf,BigDecimal.ROUND_HALF_EVEN)));
				
				// added by udara on 14-06-2013
				if(m_open_con_bal.signum() != 0){
					out.println(nf.format((m_open_con_bal.subtract(total_bcf.add(total_Future_ren).add(total_Closing).add(total_early_sett_next_month))).divide(m_open_con_bal)));
				}
				else{
					out.println("0.00");
				}
				
				
				out.println("%</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("<tr style='text-align:right;border-style:none;z-index: 0'>"); 
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("&nbsp;</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				out.println("<td style='border-style:none;z-index: 0'>");
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("</table>");	
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			
			
			
			else if(m_chksql.equals("print_report_new_drill")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slab="";
				String m_date_format="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				m_slab = req.getParameter("slab").trim();
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Recovery Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report_new?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); // commented by udara on 09-05-2013
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
				
				out.println("function show_followup(val){ ");
				out.println("	m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;");
				out.println("	window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
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
				
				if (m_slab.equals("NEW") ){
				Sql_data=" SELECT  "+
					
					" NVL( A.TOTAL_PERIOD,0), "+ //1
					" 1, "+ //2
					" NVL ( DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) total_arrears, "+ //3
					" NVL (RENTAL_AMOUNT, 0), "+ //4
					" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0), "+ //5
					" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0), "+ //6
					" NVL (A.SETTLED_AMOUNT_CUR_MON, 0), "+  //7	
					" A.FINANCE_NO, "+ //8
					" NVL ( total_amount, 0), "+ //9
					" NVL (CLOSING_BALANCE, 0), "+ //10
					" NVL (SETTLED_AMOUNT, 0), "+ //11
					" NVL ( ADJUSTED_AMOUNT, 0), "+ //12
					" NVL ( FUTURE_RENTAL, 0) "+ //13
					" ,CLIENT_CODE "+ //14
					" ,DECODE(SIGN(TOTAL_AMOUNT),-1,NVL(SETTLED_OTH_FROM_EXCESS,0),0) "+ // 15 added by udara on 02-05-2013
					
					" FROM "+m_schema_name+".AF_RE_TBD_BRANCH_REPORT A"+
					" WHERE ENT_USER='"+m_username+"' "+
					" AND ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
					" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
					" AND ACTIVATED_DATE  >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					" AND ACTIVATED_DATE  <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
					" ORDER BY A.TOTAL_PERIOD "; 
			     }
					else {
						
					Sql_data=" SELECT  "+
					
					" NVL( A.TOTAL_PERIOD,0), "+ //1
					" 1, "+ //2
					" NVL ( DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) total_arrears, "+ //3
					" NVL (RENTAL_AMOUNT, 0), "+ //4
					" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0), "+ //5
					" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0), "+ //6
					" NVL (A.SETTLED_AMOUNT_CUR_MON, 0), "+  //7	
					" A.FINANCE_NO, "+ //8
					" NVL ( total_amount, 0), "+ //9
					" NVL (CLOSING_BALANCE, 0), "+ //10
					" NVL (SETTLED_AMOUNT, 0), "+ //11
					" NVL ( ADJUSTED_AMOUNT, 0), "+ //12
					" NVL ( FUTURE_RENTAL, 0) "+ //13
					" ,CLIENT_CODE "+ //14
					" ,DECODE(SIGN(TOTAL_AMOUNT),-1,NVL(SETTLED_OTH_FROM_EXCESS,0),0) "+ // 15 added by udara on 02-05-2013
					
					" FROM "+m_schema_name+".AF_RE_TBD_BRANCH_REPORT A"+
					" WHERE ENT_USER='"+m_username+"' "+
					" AND ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
					" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					" AND A.TOTAL_PERIOD = '"+m_slab+"' "+
					" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
					" AND ACTIVATED_DATE  < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					////" AND TRUNC(A.ENT_DATE) = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" ORDER BY A.TOTAL_PERIOD "; 
					
					}
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00); 
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Slab "+m_slab+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				/*		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						
					
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
						out.println("</table>");
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_start_date+"</td>"); 
						out.println("<td width=\"50\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To</td>"); 
						out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_end_date+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("</table>");
					*/	
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table  cellspacing=0 > "); 
				out.println("<tr> "); 
				out.println("<td width='20'> "); 
				out.println("</td> "); 
				out.println("<td> "); 
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				//	out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2'><b>SLAB</b></td>"); 
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>Finance Number</b></td>"); 
				out.println("<td width='40%' class=div_input colspan=\"5\" align='center' bgcolor='lightblue' ><B> AMOUNTS TO BE RECOVERED</B></td>");
				out.println("<td width='40%' class=div_input colspan=\"9\" align='center' bgcolor='lightblue' ><B> AMOUNTS RECOVERED</B></td>"); // modified by udara on 02-05-2013 from 8 columns to 9
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>SLAB</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Finance Number</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Total Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Monthly Due Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total (A+B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>");  // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Retal Collected During the Period</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Arrears Collection</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Future Rentals</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Closing</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				
				
				//double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				//double  m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				//out.println("<table id=mytable align=\"left\" width=1750px border=\"1\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
				
				while(more){
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='show_transaction_history_new(\""+rs.getString("CLIENT_CODE")+"\",\""+rs.getString(8)+"\")'  ><u>"+rs.getString(8)+"</u></td>");
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(12)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add((rs.getBigDecimal(12))))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab  = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add((rs.getBigDecimal(12)))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add((rs.getBigDecimal(12))));
					}
					
					if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					
					
					early_sett = new BigDecimal(0.00);
					futu_rent = new BigDecimal(0.00);
					early_sett_next_month = new BigDecimal(0.00);
					
					if (rs.getBigDecimal(9).doubleValue() <0)
					{
						if(rs.getBigDecimal(9).negate().doubleValue() > rs.getDouble(4))
						{ 
							early_sett = early_sett.add(rs.getBigDecimal(4));
							early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(9).negate().subtract(rs.getBigDecimal(4)));
						}
						else
						{
							early_sett = early_sett.add(rs.getBigDecimal(9).negate());
							
						}
					}	
					
					else
					{
						early_sett = new BigDecimal(0.00) ;
					}
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					if(early_sett_next_month.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett_next_month.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett_next_month)+"</td>");
					}
					
					// added by udara on 02-015-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(15));
					
					
					if(Other_charges_coll_from_excess.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Other_charges_coll_from_excess.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Other_charges_coll_from_excess)+"</td>");
					}
					
					// end by udara on 02-015-2013
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5).subtract(rs.getBigDecimal(11)));
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5));
					
					
					
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(early_sett)).doubleValue()){
						Rent_Collec = rs.getBigDecimal(4).subtract(early_sett);
					}
					
					Rent_Collec = Rent_Collec.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					
					tot_rent = new BigDecimal(0.00);
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); //added by ns on 03-05-2013
					
					//if(rs.getDouble(4) < tot_rent.doubleValue()){
					//futu_rent = futu_rent.add( new BigDecimal(tot_rent.doubleValue()-rs.getDouble(4)));
					//futu_rent = futu_rent.add(rs.getBigDecimal(13));
					futu_rent = futu_rent.add(rs.getBigDecimal(13).subtract(early_sett_next_month)); /*added by ns on 23/11/2012*/
					//}
					
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					try{
						rental_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(4).signum()>0){
							rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6)).add(rs.getBigDecimal(11));	
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					try{
						Arrears_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(3).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(12)),BigDecimal.ROUND_HALF_EVEN));
						}else if(rs.getBigDecimal(11).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(12),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					closing = new BigDecimal(0.00);
					closing = closing.add(rs.getBigDecimal(10));
					Cash_Total = new BigDecimal(0.00);
					//Cash_Total = Cash_Total.add(rs.getBigDecimal(7));
					//futu_rent =  Cash_Total.subtract(closing).subtract(Arrea_Collec).subtract(Rent_Collec);
					
					
					
					
					if(futu_rent.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(closing).subtract(futu_rent) ;
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing);
					
					if(closing.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(closing.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(closing)+"</td>");
					}
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					if(BCF.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(BCF.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(BCF)+"</td>");
					}
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					if(rs.getBigDecimal(3).signum()>=0){
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(12)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(12));
					}
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_Ear_Sett_next_month = total_Ear_Sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					total_Rent = total_Rent.add(tot_rent);
					
					try{
						
						total_Rental_Pres = new BigDecimal(0.00);
						if(total_Due_Rental.signum()>0){
							total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					
					try{
						total_Arrears_Pres = new BigDecimal(0.00);
						if(total_Arrears.signum()>0){
							total_Arrears_Pres=total_Arrears_Pres.add(total_Arresrs_colle.multiply(new BigDecimal(100)).divide(total_Arrears,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					total_Closing =total_Closing.add(closing);
					total_cash = total_cash.add(Cash_Total);
					total_bcf= total_bcf.add(BCF);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Ear_Sett_next_month.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett_next_month.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett_next_month)+"</b></td>");  
					}
					
					
					// added by udara on 02-05-2013
					if(total_Other_charges_coll_from_excess.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess)+"</b></td>"); 
					}
					// end by udara on 02-05-2013
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Future_ren.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren)+"</b></td>"); 
					}
					
					
					if(total_Closing.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing)+"</b></td>"); 
					}
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_bcf.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf)+"</b></td>"); 
					}
					
					
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
				
				/*		out.println("<tr>");		
				out.println("<td  colspan=18 >"); 
						out.println("<table align=\"left\" width=300px border=\"0\" class=\"table\"  cellspacing=0>"); //bordercolor='black'
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr>");		
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("</tr>");
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  ><b>Months</td>"); 
						out.println("<td  ><b>Targets</td>"); 
						out.println("<td  ><b>Acheivement</td>"); 
						out.println("<td  ><b>+/-</td>"); 
						out.println("</tr>");	
				*/
				
				/*
				
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=0");
						
					if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=1");
						
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\" >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=2");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=3");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\">");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=4");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=5");
						if(rs.next()){
						out.println("<tr bgcolor=\"#CCCCFF\"> ");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>");  
						out.println("</tr>");		
						}
						
						rs=stmt.executeQuery(
						" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
						" WHERE A.AGE=6");
						if(rs.next()){
						out.println("<tr  >");		
						out.println("<td  >"+rs.getInt(1)+"</td>"); 
						out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
					
						out.println("</tr>");		
						}
						
				*/		
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");	
				out.println("<br/>");	
				out.println("<br/>");	
				out.println("</td> "); 
				out.println("</tr>");		
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			
			else if(m_chksql.equals("print_report")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				/*				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
				m_sort_column = req.getParameter("sort_column");
										m_order_by_type = req.getParameter("order_by_type");
											}
											
									else
											{
											}
											*/
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				
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
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_count' VALUE=\"\"> ");
				out.println("<br>");	
				out.println("<br>");	
				
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
				
				if(m_location.equals("")){
					Sql_data=" SELECT  "+
						" B.FINANCE_NO, "+//1
						" B.CLIENT_CODE, "+//2
						" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)), "+//3 
						" NVL(SUM(BALANCE_TO_BE_RECEIVED),0), "+//4
						" TO_CHAR(VALUE_DATE,'DD'), "+//5
						" NVL("+m_schema_name+".AF_CO_GET_AGE_AGR_NO(B.FINANCE_NO,'"+m_date+"'),0), "+ //6
						" NVL("+m_schema_name+".AF_CO_GET_ARREARS_AGR(B.FINANCE_NO,'"+m_date+"'),0), "+ //7
						" NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMT_AGR(B.FINANCE_NO,'"+m_date+"'),0), "+ //8
						" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO(B.FINANCE_NO,'"+m_date+"'),0), "+ //9
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TEL(B.CLIENT_CODE),'-') "+ //10
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE ACTIVE_STATUS='Y' "+
						" AND TO_DATE((TO_CHAR(VALUE_DATE,'DD-MM-YYYY')),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY') "+
						" AND  VALUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1) "+
						" AND  BALANCE_TO_BE_RECEIVED>0 "+
						" AND  INVOICE_TYPE='INV_GENER' "+ 
						"	AND  A.FINANCE_NO=B.FINANCE_NO "+
						//" AND "+m_schema_name+".AF_CO_GET_MK_OFFICER_ID(INQUARY_NO)='"+m_officer+"' "+
						" AND B.collection_officer='"+m_officer+"' "+
						" GROUP BY B.FINANCE_NO,VALUE_DATE,B.CLIENT_CODE,BRANCH_CODE ";
				}
				else{
					
					Sql_data=" SELECT  "+
						" DISTINCT  B.FINANCE_NO, "+//1
						" B.CLIENT_CODE, "+//2
						" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)), "+//3 
						" NVL(SUM(BALANCE_TO_BE_RECEIVED),0), "+//4
						" TO_CHAR(VALUE_DATE,'DD'), "+//5
						" NVL("+m_schema_name+".AF_CO_GET_AGE_AGR_NO(B.FINANCE_NO,'"+m_date+"'),0), "+ //6
						" NVL("+m_schema_name+".AF_CO_GET_ARREARS_AGR(B.FINANCE_NO,'"+m_date+"'),0), "+ //7
						" NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMT_AGR(B.FINANCE_NO,'"+m_date+"'),0), "+ //8
						" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO(B.FINANCE_NO,'"+m_date+"'),0), "+ //9
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TEL(B.CLIENT_CODE),'-') "+ //10
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE ACTIVE_STATUS='Y' "+
						" AND TO_DATE((TO_CHAR(VALUE_DATE,'DD-MM-YYYY')),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY') "+
						" AND  VALUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1) "+
						//" AND  BALANCE_TO_BE_RECEIVED>0 "+
						" AND  INVOICE_TYPE='INV_GENER' "+ 
						"	AND  A.FINANCE_NO=B.FINANCE_NO "+
						//" AND "+m_schema_name+".AF_CO_GET_MK_OFFICER_ID(INQUARY_NO)='"+m_officer+"' "+
						" AND B.collection_officer LIKE '%"+m_officer+"%' "+
						" AND BRANCH_CODE='"+m_location+"' "+
						" GROUP BY B.FINANCE_NO,VALUE_DATE,B.CLIENT_CODE,BRANCH_CODE ";
				}
				
				rs=stmt.executeQuery(Sql_data);
				
				more=rs.next();
				int count=0;
				double closing_bal=0;
				double totalab=0;
				double total_open_bal=0;
				double total_cur_due=0;
				double total_collection=0;
				double total_closing_bal=0;
				double achievement=0;
				double open_pre=0;
				double cur_pre=0;
				double col_pre=0;
				double closing_pre=0;
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>RECOVERY REPORT</u></td>"); 
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>AS AT "+m_end_date+"</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				//		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//		out.println("<tr >");
				//		out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Marketing Executive</td>"); 
				//		out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_officer_name+"</td>"); 
				//		out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				//		out.println("</tr>");
				//		out.println("<tr>");
				//		out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Branch</td>"); 
				//		out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_location_desc+"</td>"); 
				//		out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				//		out.println("</tr>");
				//		out.println("</table>");
				
				//		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//		out.println("<tr >");
				//		out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >From</td>"); 
				//		out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_start_date+"</td>"); 
				//		out.println("<td width=\"50\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				//		out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >To</td>"); 
				//		out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_end_date+"</td>"); 
				//		out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				//		out.println("</tr>");
				//		out.println("</table>");
				
				if(!more){
					
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
					
				}
				
				
				
				//=================================================
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				if(more){
					out.println("<tr class=pdn_txtpos2 height=\"30\" >");
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Agreement No</td>"); 
					out.println("<td width=\"10%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Name</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Tel</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Monthly Rental</td>"); 
					out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center;}'   >Due Date</td>"); 
					out.println("<td width=\"5%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Age</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Opening balance</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Current Due & Additions</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Total Balance</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Collections</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Closing Balance</td>"); 
					out.println("<td width=\"5%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Age</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'  >Achivement</td>"); 
					out.println("<td width='7%' >Remarks</td>");
					out.println("<td width='7%' >Follow Up</td>");
					out.println("</tr >");
					
				}
				int j=0;
				double m_total_due=0;
				
				while(more){
					m_total_due=rs.getDouble(4)+rs.getDouble(7);
					
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					//out.println("<tr >");
					out.println("<td  onClick=\"show_transaction_history_new('"+rs.getString(2)+"','"+rs.getString(1)+"')\" STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   ><u>"+rs.getString(1)+"</u></td>"); 
					out.println("<td  onClick=\"show_client('"+rs.getString(2)+"')\" class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString(3)+"</u></td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}'    >"+rs.getString(10)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:right;}'   >"+nf1.format(rs.getDouble(4))+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >"+rs.getString(5)+"</td>"); 
					
					if(rs.getDouble(6) >= 0 && rs.getDouble(6) < 2 ){
						out.println("<td  STYLE='{font:  8pt arial; text-align:right; color=green; }'  ><b>"+nf1.format(rs.getDouble(6))+"</td>"); 
					}
					else if(rs.getDouble(6) >= 2 && rs.getDouble(6) < 3 ){
						out.println("<td  STYLE='{font:  8pt arial; text-align:right; color=orange;}'  ><b>"+nf1.format(rs.getDouble(6))+"</td>"); 
					}
					else if(rs.getDouble(6) >= 3 ){
						out.println("<td  STYLE='{font:  8pt arial; text-align:right; color=red; }'   ><b>"+nf1.format(rs.getDouble(6))+"</td>"); 
					}
					
					out.println("<td  STYLE='{font:  8pt arial; text-align:right;}'  >"+nf1.format(rs.getDouble(7))+"</td>"); 
					out.println("<td  STYLE='{font:  8pt arial; text-align:right;}'  >"+nf1.format(rs.getDouble(4))+"</td>"); 
					out.println("<td  STYLE='{font:  8pt arial; text-align:right;}'  >"+nf1.format(m_total_due)+"</td>"); 
					out.println("<td  STYLE='{font:  8pt arial; text-align:right;}'  >"+nf1.format(rs.getDouble(8))+"</td>"); 
					
					
					closing_bal=(rs.getDouble(4)+rs.getDouble(7) ) - rs.getDouble(8);
					out.println("<td  STYLE='{font:  8pt arial; text-align:right;}'  >"+nf1.format(closing_bal)+"&nbsp;&nbsp;</td>"); 
					
					if(rs.getDouble(9) >= 0 && rs.getDouble(9) < 2 ){
						out.println("<td  STYLE='{font:  8pt arial; text-align:right; color=green; }'  ><b>"+nf1.format(rs.getDouble(9))+"&nbsp;&nbsp;</td>"); 
					}
					else if(rs.getDouble(9) >= 2 && rs.getDouble(9) < 3 ){
						out.println("<td  STYLE='{font:  8pt arial; text-align:right; color=orange;}'  ><b>"+nf1.format(rs.getDouble(9))+"&nbsp;&nbsp;</td>"); 
					}
					else if(rs.getDouble(9) >= 3 ){
						out.println("<td  STYLE='{font:  8pt arial; text-align:right; color=red; }'  ><b>"+nf1.format(rs.getDouble(9))+"&nbsp;&nbsp;</td>"); 
					}
					
					achievement=(rs.getDouble(8)/(rs.getDouble(7)+rs.getDouble(4)))*100;
					out.println("<td  STYLE='{font:  8pt arial; text-align:right;}'  >"+nf1.format(achievement)+"%&nbsp;&nbsp;</td>"); 
					
					out.println("<td  class=factoring-letter-body style=cursor:hand onClick=\"add_client_comments('"+rs.getString(2)+"','"+rs.getString(1)+"')\"><u>Remarks</u></td>");
					out.println("<td  class=factoring-letter-body style=cursor:hand onClick=\"show_followup('"+rs.getString(1)+"')\"><u>Follow up</u></td>");
					out.println("</tr>");
					
					closing_bal=(rs.getDouble(4)+rs.getDouble(7) ) - rs.getDouble(8);
					total_open_bal+=rs.getDouble(7);
					total_cur_due+=rs.getDouble(4);
					total_collection+=rs.getDouble(8);
					total_closing_bal+=closing_bal;
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:right;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:left;}'  >Total</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf1.format(total_open_bal)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf1.format(total_cur_due)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf1.format(total_collection)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf1.format(total_closing_bal)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}' >&nbsp;</td>"); 
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
					open_pre=(total_open_bal/total_open_bal)*100;
					cur_pre=(total_cur_due/total_open_bal)*100;
					col_pre=(total_collection/(total_open_bal+total_cur_due))*100;
					closing_pre=(total_closing_bal/(total_open_bal+total_cur_due))*100;
					
					
					out.println("<tr>");		
					out.println("<td  STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td  STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td  STYLE='{font:  8pt arial; text-align:left;}'   >&nbsp;</td>"); 
					out.println("<td  STYLE='{font:  8pt arial; text-align:right;}'   >&nbsp;</td>"); 
					out.println("<td   STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td  STYLE='{font:  bold 8pt  arial; text-align:left;}'  >Percentage</td>"); 
					out.println("<td  STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf.format(open_pre)+"%</td>"); 
					out.println("<td  STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf.format(cur_pre)+"%</td>"); 
					out.println("<td  STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf.format(col_pre)+"%</td>"); 
					out.println("<td  STYLE='{font:  bold 8pt  arial; text-align:right;}'  >"+nf.format(closing_pre)+"%</td>"); 
					out.println("<td  STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td  STYLE='{font:  bold 8pt  arial; text-align:right;}' >&nbsp;</td>"); 
					out.println("</tr>");		 
				}
				
				out.println("</table>");		 
				//====================================
				
				
				out.println("<p><br><br><br><br><br><br><br></p>");	
				out.println("<table align=\"left\" width=\"250\" border=\"1\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font:  bold 8pt  arial; text-align:left;}'  >Collection Target For the month (on total arrears)</td>"); 
				out.println("<td width=\"100\" STYLE='{font:  bold 8pt  arial; text-align:right;}'  >Percentage</td>"); 
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font:  bold 8pt  arial; text-align:left; color=green;}'  >1 month</td>"); 
				out.println("<td width=\"100\" STYLE='{font:  bold 8pt  arial; text-align:right; }'  >100%</td>"); 
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font:  bold 8pt  arial; text-align:left; color=green;}'  >2 month</td>"); 
				out.println("<td width=\"100\" STYLE='{font:  bold 8pt  arial; text-align:right; }'  >75%</td>"); 
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font:  bold 8pt  arial; text-align:left; color=green;}'  >3 month</td>"); 
				out.println("<td width=\"100\" STYLE='{font:  bold 8pt  arial; text-align:right ; }'  >60%</td>"); 
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font:  bold 8pt  arial; text-align:left; color=green;}'  >4 month</td>"); 
				out.println("<td width=\"100\" STYLE='{font:  bold 8pt  arial; text-align:right ;}' >50%</td>"); 
				out.println("</tr>");
				out.println("</table>");				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
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
			
			if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
