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


public class LAKDL_AF_MISF_System_reports_past_six_months extends javax.servlet.http.HttpServlet { 
	
	
	
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
				
				String m_date = req.getParameter("date");
				
				
				try{

					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SYS_REP_PAST_6_MONTH(:1,:2);END;");
					callstmt1.setString(1,m_date); 
					callstmt1.setString(2,m_username);
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
				out.println("<TITLE>Past Six Month Collection Performance Report</TITLE>"); 
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
				out.println("	if(document.Form1.AT_DAY.value!=\"\" && document.Form1.AT_MONTH.value!=\"\" && document.Form1.AT_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.AT_DAY.value+'-'+document.Form1.AT_MONTH.value+'-'+document.Form1.AT_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=run_report&date=\"+m_date;"); 
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
				
				out.println("	if(document.Form1.AT_DAY.value!=\"\" && document.Form1.AT_MONTH.value!=\"\" && document.Form1.AT_YEAR.value!=\"\"){");
				out.println("			m_date=document.Form1.AT_DAY.value+'-'+document.Form1.AT_MONTH.value+'-'+document.Form1.AT_YEAR.value;");
				out.println("			m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("			m_officer=document.Form1.TXT_USER.value;");
				
				out.println("   		m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-15(#16240)
				
				out.println("           var m_perform_status = document.Form1.TXT_PERFORM_STATUS.value;   "); // added by udara 17-08-2015

				//out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=view_report&date=\"+m_date;");
				out.println("             m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=view_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;");
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=main_page';"); 
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
				out.println("help_box.innerHTML=\" Past Six Month Collection Performance Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Past Six Month Collection Performance Report - \"+document.Form1.hid_status.value;"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Past Six Month Collection Performance Report </td>"); 
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
				
				
				out.println("<tr class=tr_input style=\"display:none;\" >");
				out.println("<td width='20%'ID=VDATE>From Date</td>");
				out.println("<td width='*%'><input name=\"FROM_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input style=\"display:none;\" >");
				out.println("<td width='20%'ID=VDATE>To Date</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>");  
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>As At Date</td>");
				out.println("<td width='*%'><input name=\"AT_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)> ");
				out.println("    <input name=\"AT_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)> ");
				out.println("    <input name=\"AT_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.AT_DAY,document.Form1.AT_MONTH,document.Form1.AT_YEAR)>"); 
				out.println("    <input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				//out.println("    <input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				
				out.println("<tr  >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
			
				out.println("<tr  >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>");
				
				// added by udara 04-11-2014
				out.println("<tr style={display:none;} >"); 
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
				out.println("<tr >"); 
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
				out.println("<tr  >"); 
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
				
				out.println("<tr >"); 
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

			

			else if(m_chksql.equals("view_report")){	
				
				String m_date = "";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_cur_date="";
				String m_active_status = "";
				String m_active_status_string = "";				
				
				String m_perform_status = "";
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}
				
				String m_region = "";
				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' ";
					m_active_status_string = " AND YARD_STATUS = '"+m_active_status+"' ";
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				
				
				String sql_m_region = "  ";
				if(!(m_region.equals("NOT_SELECT"))){ 
					sql_m_region = " AND REGION_CODE = '"+m_region+"' ";    
				}
				
				
				String sql_m_location = "   ";
				if((!m_location.equals("")) && (!m_location.equals(""))){
					sql_m_location = " AND   BRANCH_CODE = '"+m_location+"' ";
				}
				
				String sql_m_officer = "   ";
				if((!m_officer.equals(""))){
					sql_m_officer = " AND   COLL_OFICER = '"+m_officer+"' "; // added by udara 09-08-2017
				}
				
				
				String sql_perform_status = "   ";
				
				if(!m_perform_status.equals("")){
					sql_perform_status = " AND PERFORM_STATUS = '"+m_perform_status+"'  ";
				}
				

				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Past Six Month Collection Performance Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 

				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function view_report_drilldown(m_location){");
				
				out.println("			m_date='"+m_date+"';");
				//out.println("			m_location=location;");
				out.println("			m_officer='"+m_officer+"';");
				out.println("           m_region = '"+m_region+"'; ");
				out.println("           var m_perform_status = '"+m_perform_status+"';   "); // added by udara 17-08-2015
				out.println("           m_active_status='"+m_active_status+"'; ");
				
				//out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=view_report_drilldown&date=\"+m_date+\"&location=\"+m_location;");
				out.println("             m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=view_report_drilldown&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&active_status=\"+m_active_status+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;"); 
				out.println("           window.open(m_url,'display_past_6_drill','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function view_report_drilldown_activated(m_location,m_type){");
				
				out.println("			m_date='"+m_date+"';");
				//out.println("			m_location=location;");
				out.println("			m_officer='"+m_officer+"';");
				out.println("           m_region = '"+m_region+"'; ");
				out.println("           var m_perform_status = '"+m_perform_status+"';   "); // added by udara 17-08-2015
				out.println("           m_active_status='"+m_active_status+"'; ");
				out.println("           m_report_type=m_type; ");
				
				//out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=view_report_drilldown_activated&date=\"+m_date+\"&location=\"+m_location+\"&type=\"+m_type;");
				out.println("             m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_past_six_months?chksql=view_report_drilldown_activated&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&active_status=\"+m_active_status+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status+\"&type=\"+m_type;"); 
				out.println("           window.open(m_url,'display_past_6_drill','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");
			
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Past Six Month Collection Performance Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				out.println("<br>");
				
				
				String m_colour_col_due = "#ffcce0";
				String m_colour_col_collected = "#8cff66"; // "#cce6ff";
				String m_colour_col_cases = "#ffff99";
				String m_colour_region_total = "#b3d1ff";
				

				out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
			
				out.println("<tr>");
				out.println("<td width=\"45%\"  align='center'  colspan='9'  bgcolor='lightgray' > &nbsp; </td>"); 	
		        out.println("<td width=\"35%\"  align='center'  colspan='6'  bgcolor='lightgray' ><b> No. of zero collection </b></td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td width=\"10%\"   align='center' colspan='2' bgcolor='lightgray' > &nbsp; </td>"); 	
				out.println("<td width=\"15%\"  align='center'  colspan='3' bgcolor='lightblue' ><b> Due Rental </b></td>");
				out.println("<td width=\"15%\"  align='center'  colspan='3' bgcolor='lightgreen' ><b> Arrears </b></td>");		
				out.println("<td width=\"5%\"   align='center'  bgcolor='lightgray' > &nbsp; </td>"); 
				out.println("<td width=\"5%\"   align='center'  colspan='6'  bgcolor='lightgray' ><b> Activated Month </b></td>");
				out.println("</tr>");	
				
				
				String Sql_data = "";
				
				stmt = conn.createStatement ();
				
				Sql_data = " "+
						" SELECT "+
							" TO_CHAR(ADD_MONTHS(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM'),-6),'Month') MONTH_1,  "+
							" TO_CHAR(ADD_MONTHS(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM'),-5),'Month') MONTH_2, "+
							" TO_CHAR(ADD_MONTHS(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM'),-4),'Month') MONTH_3, "+
							" TO_CHAR(ADD_MONTHS(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM'),-3),'Month') MONTH_4, "+
							" TO_CHAR(ADD_MONTHS(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM'),-2),'Month') MONTH_5, "+
							" TO_CHAR(ADD_MONTHS(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM'),-1),'Month') MONTH_6 "+
								" FROM DUAL "+
								" ";
				
				String m_MONTH_1 = "";
				String m_MONTH_2 = "";
				String m_MONTH_3 = "";
				String m_MONTH_4 = "";
				String m_MONTH_5 = "";
				String m_MONTH_6 = "";
				
				rs=stmt.executeQuery(Sql_data);
				
				if(rs.next()){
					 m_MONTH_1 = rs.getString("MONTH_1");
					 m_MONTH_2 = rs.getString("MONTH_2");
					 m_MONTH_3 = rs.getString("MONTH_3");
					 m_MONTH_4 = rs.getString("MONTH_4");
					 m_MONTH_5 = rs.getString("MONTH_5");
					 m_MONTH_6 = rs.getString("MONTH_6");
				}
				

				out.println("<tr>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Branch </b></td>"); 	
		        out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> No. of cases </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='"+m_colour_col_due+"' ><b> Due Rental </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='"+m_colour_col_collected+"' ><b> Rental collected as at "+m_date+"</b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='"+m_colour_col_cases+"' ><b> % </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='"+m_colour_col_due+"' ><b> Due arrears </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='"+m_colour_col_collected+"' ><b> Arrears Collected as at "+m_date+"</b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='"+m_colour_col_cases+"' ><b> % </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> All zero cases </b></td>"); 	
		        out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> "+m_MONTH_1+" </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> "+m_MONTH_2+" </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> "+m_MONTH_3+" </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> "+m_MONTH_4+" </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> "+m_MONTH_5+" </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> "+m_MONTH_6+" </b></td>");
				//out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Month 7 </b></td>");
				out.println("</tr>");
				
				//String Sql_data = "";
				
				//stmt = conn.createStatement ();
				
				//out.println(Sql_data);
				
				/*
				
				Sql_data="  "+				
						" SELECT "+
						      " BRANCH_CODE, "+
							  " NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(BRANCH_CODE),BRANCH_CODE) BRANCH_DESC, "+ 
						      " COUNTS, "+
						      " RENTAL_AMOUNT, "+
						      " RENTAL_AMOUNT_NEW, "+
						      " (CASE WHEN RENTAL_AMOUNT_NEW <> 0 THEN "+
						          " (RENTAL_AMOUNT_NEW / RENTAL_AMOUNT)*100 "+
						      " ELSE "+
						          " 0 "+
						      " END) RENTAL_PERCENTAGE, "+
						      " TOTAL_ARREARS, "+
						      " ARR_COLLECTION, "+
						      " (CASE WHEN ARR_COLLECTION <> 0 THEN "+
						          " (ARR_COLLECTION / TOTAL_ARREARS)*100 "+
						      " ELSE "+
						          " 0 "+
						      " END) ARREARS_PERCENTAGE, "+
						      " ALL_ZERO_CASES, "+
						      " MON_LESS_7, "+
						      " MON_LESS_6, "+
						      " MON_LESS_5, "+
						      " MON_LESS_4, "+
						      " MON_LESS_3, "+
						      " MON_LESS_2, "+
						      " MON_LESS_1  "+   
						          " FROM (  "+         
						                  " SELECT "+
								                  " BRANCH_CODE, "+
								                  " COUNT(*) COUNTS, "+
								                  " SUM(RENTAL_AMOUNT) RENTAL_AMOUNT, "+
								                  " SUM(RENTAL_AMOUNT_NEW) RENTAL_AMOUNT_NEW, "+
								                  " SUM(TOTAL_ARREARS) TOTAL_ARREARS, "+
								                  " SUM(ARR_COLLECTION) ARR_COLLECTION, "+
								                  " SUM(ALL_ZERO_CASES) ALL_ZERO_CASES, "+
								                  " SUM(MON_LESS_7) MON_LESS_7, "+
								                  " SUM(MON_LESS_6) MON_LESS_6, "+
								                  " SUM(MON_LESS_5) MON_LESS_5, "+
								                  " SUM(MON_LESS_4) MON_LESS_4, "+
								                  " SUM(MON_LESS_3) MON_LESS_3, "+
								                  " SUM(MON_LESS_2) MON_LESS_2, "+
								                  " SUM(MON_LESS_1) MON_LESS_1 "+
								                  " FROM "+m_schema_name+".AF_TBL_SYS_REP_PAST_6_MONTH "+
								                  " WHERE ENT_USER = '"+m_username+"' "+
								                  " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
													"  "+sql_m_officer+" "+
													"  "+sql_m_location+" "+
													"  "+m_active_status_string+" "+
													"  "+sql_m_region+" "+ 
													"  "+sql_perform_status+"  "+
													
								                  " GROUP BY BRANCH_CODE "+
												  " ORDER BY BRANCH_CODE "+
						" ) ";
				
				*/
				
				
				Sql_data="  "+				
						" SELECT "+
						      " BRANCH_CODE, "+
							  " NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(BRANCH_CODE),BRANCH_CODE) BRANCH_DESC, "+ 
						      " COUNTS, "+
						      " RENTAL_AMOUNT, "+
						      " RENTAL_AMOUNT_NEW, "+
						      " (CASE WHEN RENTAL_AMOUNT_NEW <> 0 THEN "+
						          " (RENTAL_AMOUNT_NEW / RENTAL_AMOUNT)*100 "+
						      " ELSE "+
						          " 0 "+
						      " END) RENTAL_PERCENTAGE, "+
						      " TOTAL_ARREARS, "+
						      " ARR_COLLECTION, "+
						      " (CASE WHEN ARR_COLLECTION <> 0 THEN "+
						          " (ARR_COLLECTION / TOTAL_ARREARS)*100 "+
						      " ELSE "+
						          " 0 "+
						      " END) ARREARS_PERCENTAGE, "+
						      " ALL_ZERO_CASES, "+
						      " MON_LESS_7, "+
						      " MON_LESS_6, "+
						      " MON_LESS_5, "+
						      " MON_LESS_4, "+
						      " MON_LESS_3, "+
						      " MON_LESS_2, "+
						      " MON_LESS_1,  "+
							  " REGION_CODE "+
						          " FROM (  "+         
						                  " SELECT "+
								                  " BRANCH_CODE, "+
								                  " COUNT(*) COUNTS, "+
								                  " SUM(RENTAL_AMOUNT) RENTAL_AMOUNT, "+
								                  " SUM(RENTAL_AMOUNT_NEW) RENTAL_AMOUNT_NEW, "+
								                  " SUM(TOTAL_ARREARS) TOTAL_ARREARS, "+
								                  " SUM(ARR_COLLECTION) ARR_COLLECTION, "+
								                  " SUM(ALL_ZERO_CASES) ALL_ZERO_CASES, "+
								                  " SUM(MON_LESS_7) MON_LESS_7, "+
								                  " SUM(MON_LESS_6) MON_LESS_6, "+
								                  " SUM(MON_LESS_5) MON_LESS_5, "+
								                  " SUM(MON_LESS_4) MON_LESS_4, "+
								                  " SUM(MON_LESS_3) MON_LESS_3, "+
								                  " SUM(MON_LESS_2) MON_LESS_2, "+
								                  " SUM(MON_LESS_1) MON_LESS_1, "+
												  " NVL("+m_schema_name+".AF_CO_GET_REG_DESC(REGION_CODE),'Z - No Region') REGION_CODE "+
								                  " FROM "+m_schema_name+".AF_TBL_SYS_REP_PAST_6_HIS "+
								                  " WHERE REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
													"  "+sql_m_officer+" "+
													"  "+sql_m_location+" "+
													"  "+m_active_status_string+" "+
													"  "+sql_m_region+" "+ 
													"  "+sql_perform_status+"  "+
													
								                  " GROUP BY REGION_CODE, BRANCH_CODE "+
												  " ORDER BY REGION_CODE, BRANCH_CODE "+
						" ) ";
				
				
				 //out.println(Sql_data);
				 rs=stmt.executeQuery(Sql_data);
					
				    double m_RENTAL_AMOUNT = 0;
					double m_RENTAL_AMOUNT_NEW = 0;
					double m_TOTAL_ARREARS = 0;
					double m_ARR_COLLECTION = 0;
					int m_count = 0;
					int m_ALL_ZERO_CASES = 0;
					int m_MON_LESS_7 = 0;
					int m_MON_LESS_6 = 0;
					int m_MON_LESS_5 = 0;
					int m_MON_LESS_4 = 0;
					int m_MON_LESS_3 = 0;
					int m_MON_LESS_2 = 0;
					int m_MON_LESS_1 = 0;
					int m_TEMP_ALL_ZERO_CASES = 0;
					
					double m_sub_RENTAL_AMOUNT = 0;
					double m_sub_RENTAL_AMOUNT_NEW = 0;
					double m_sub_TOTAL_ARREARS = 0;
					double m_sub_ARR_COLLECTION = 0;
					
					int m_sub_ALL_ZERO_CASES = 0;
					int m_sub_MON_LESS_7 = 0;
					int m_sub_MON_LESS_6 = 0;
					int m_sub_MON_LESS_5 = 0;
					int m_sub_MON_LESS_4 = 0;
					int m_sub_MON_LESS_3 = 0;
					int m_sub_MON_LESS_2 = 0;
					int m_sub_MON_LESS_1 = 0;
					int m_sub_TEMP_ALL_ZERO_CASES = 0;
					
					int m_sub_count = 0;

				String region_code = "";	
				
				double sub_tot_due_rental_perc = 0;
				double sub_tot_arr_perc = 0;
					
				while(rs.next()){	
					
						m_RENTAL_AMOUNT     = m_RENTAL_AMOUNT + rs.getDouble("RENTAL_AMOUNT");
						m_RENTAL_AMOUNT_NEW = m_RENTAL_AMOUNT_NEW + rs.getDouble("RENTAL_AMOUNT_NEW");
						m_TOTAL_ARREARS     = m_TOTAL_ARREARS + rs.getDouble("TOTAL_ARREARS");
						m_ARR_COLLECTION    = m_ARR_COLLECTION + rs.getDouble("ARR_COLLECTION");
						m_count             = m_count + rs.getInt("COUNTS");
						//m_ALL_ZERO_CASES  = m_ALL_ZERO_CASES + rs.getInt("ALL_ZERO_CASES");
						m_MON_LESS_7      = m_MON_LESS_7 + rs.getInt("MON_LESS_7");
						m_MON_LESS_6      = m_MON_LESS_6 + rs.getInt("MON_LESS_6");
						m_MON_LESS_5      = m_MON_LESS_5 + rs.getInt("MON_LESS_5");
						m_MON_LESS_4      = m_MON_LESS_4 + rs.getInt("MON_LESS_4");
						m_MON_LESS_3      = m_MON_LESS_3 + rs.getInt("MON_LESS_3");
						m_MON_LESS_2      = m_MON_LESS_2 + rs.getInt("MON_LESS_2");
						m_MON_LESS_1      = m_MON_LESS_1 + rs.getInt("MON_LESS_1");
						
						m_TEMP_ALL_ZERO_CASES = 0;
						m_TEMP_ALL_ZERO_CASES = rs.getInt("MON_LESS_1") + rs.getInt("MON_LESS_2") + rs.getInt("MON_LESS_3") + rs.getInt("MON_LESS_4") + rs.getInt("MON_LESS_5") + rs.getInt("MON_LESS_6") + rs.getInt("MON_LESS_7");

						m_ALL_ZERO_CASES  = m_ALL_ZERO_CASES + m_TEMP_ALL_ZERO_CASES;
						
						/*
					
						out.println("<tr>");
						out.println("<td width=\"5%\"  align='left'   onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:left;cursor:pointer;}'  ><u> "+rs.getString("BRANCH_DESC")+" </u></td>"); 	
				        out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("COUNTS")+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT"))+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT_NEW"))+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+nf1.format(rs.getDouble("RENTAL_PERCENTAGE"))+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+nf1.format(rs.getDouble("TOTAL_ARREARS"))+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+nf1.format(rs.getDouble("ARR_COLLECTION"))+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+nf1.format(rs.getDouble("ARREARS_PERCENTAGE"))+" </u></td>");

				        out.println("<td width=\"5%\"  STYLE='{text-align:right;}' > "+m_TEMP_ALL_ZERO_CASES+" </td>"); 

						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_6');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_6")+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_5');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_5")+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_4');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_4")+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_3');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_3")+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_2');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_2")+" </u></td>");
						out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_1');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_1")+" </u></td>");
						out.println("</tr>");
						
						*/
						
							if(region_code.equals("")){
								
								region_code = rs.getString("REGION_CODE");
								
								m_sub_RENTAL_AMOUNT     = m_sub_RENTAL_AMOUNT + rs.getDouble("RENTAL_AMOUNT");
								m_sub_RENTAL_AMOUNT_NEW = m_sub_RENTAL_AMOUNT_NEW + rs.getDouble("RENTAL_AMOUNT_NEW");
								m_sub_TOTAL_ARREARS     = m_sub_TOTAL_ARREARS + rs.getDouble("TOTAL_ARREARS");
								m_sub_ARR_COLLECTION    = m_sub_ARR_COLLECTION + rs.getDouble("ARR_COLLECTION");
								m_sub_count           = m_sub_count + rs.getInt("COUNTS");
								m_sub_MON_LESS_7      = m_sub_MON_LESS_7 + rs.getInt("MON_LESS_7");
								m_sub_MON_LESS_6      = m_sub_MON_LESS_6 + rs.getInt("MON_LESS_6");
								m_sub_MON_LESS_5      = m_sub_MON_LESS_5 + rs.getInt("MON_LESS_5");
								m_sub_MON_LESS_4      = m_sub_MON_LESS_4 + rs.getInt("MON_LESS_4");
								m_sub_MON_LESS_3      = m_sub_MON_LESS_3 + rs.getInt("MON_LESS_3");
								m_sub_MON_LESS_2      = m_sub_MON_LESS_2 + rs.getInt("MON_LESS_2");
								m_sub_MON_LESS_1      = m_sub_MON_LESS_1 + rs.getInt("MON_LESS_1");
								
								m_sub_TEMP_ALL_ZERO_CASES = 0;
								m_sub_TEMP_ALL_ZERO_CASES = rs.getInt("MON_LESS_1") + rs.getInt("MON_LESS_2") + rs.getInt("MON_LESS_3") + rs.getInt("MON_LESS_4") + rs.getInt("MON_LESS_5") + rs.getInt("MON_LESS_6") + rs.getInt("MON_LESS_7");
								
								out.println("<tr>");
								out.println("<td width=\"5%\"  align='left' > &nbsp; </td>"); 
								//out.println("<td width=\"5%\"  align='left' ><b> "+region_code+" </b></td>"); 	
						        out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>"); 
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>"); 
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
		
						        out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
		
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("</tr>");
						
								out.println("<tr>");
								out.println("<td width=\"5%\"  align='left'   onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:left;cursor:pointer;}'  ><u> "+rs.getString("BRANCH_DESC")+" </u></td>"); 	
						        out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("COUNTS")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_due+" ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_collected+" ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT_NEW"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_cases+" ><u> "+nf1.format(rs.getDouble("RENTAL_PERCENTAGE"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_due+" ><u> "+nf1.format(rs.getDouble("TOTAL_ARREARS"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_collected+" ><u> "+nf1.format(rs.getDouble("ARR_COLLECTION"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_cases+" ><u> "+nf1.format(rs.getDouble("ARREARS_PERCENTAGE"))+" </u></td>");
		
						        out.println("<td width=\"5%\"  STYLE='{text-align:right;}' > "+m_TEMP_ALL_ZERO_CASES+" </td>"); 
		
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_6');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_6")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_5');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_5")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_4');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_4")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_3');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_3")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_2');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_2")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_1');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_1")+" </u></td>");
								out.println("</tr>");
						
							}	
							else if(region_code.equals(rs.getString("REGION_CODE"))){
								
								m_sub_RENTAL_AMOUNT     = m_sub_RENTAL_AMOUNT + rs.getDouble("RENTAL_AMOUNT");
								m_sub_RENTAL_AMOUNT_NEW = m_sub_RENTAL_AMOUNT_NEW + rs.getDouble("RENTAL_AMOUNT_NEW");
								m_sub_TOTAL_ARREARS     = m_sub_TOTAL_ARREARS + rs.getDouble("TOTAL_ARREARS");
								m_sub_ARR_COLLECTION    = m_sub_ARR_COLLECTION + rs.getDouble("ARR_COLLECTION");
								m_sub_count           = m_sub_count + rs.getInt("COUNTS");
								m_sub_MON_LESS_7      = m_sub_MON_LESS_7 + rs.getInt("MON_LESS_7");
								m_sub_MON_LESS_6      = m_sub_MON_LESS_6 + rs.getInt("MON_LESS_6");
								m_sub_MON_LESS_5      = m_sub_MON_LESS_5 + rs.getInt("MON_LESS_5");
								m_sub_MON_LESS_4      = m_sub_MON_LESS_4 + rs.getInt("MON_LESS_4");
								m_sub_MON_LESS_3      = m_sub_MON_LESS_3 + rs.getInt("MON_LESS_3");
								m_sub_MON_LESS_2      = m_sub_MON_LESS_2 + rs.getInt("MON_LESS_2");
								m_sub_MON_LESS_1      = m_sub_MON_LESS_1 + rs.getInt("MON_LESS_1");
								
								//m_sub_TEMP_ALL_ZERO_CASES = 0;
								m_sub_TEMP_ALL_ZERO_CASES = m_sub_TEMP_ALL_ZERO_CASES + rs.getInt("MON_LESS_1") + rs.getInt("MON_LESS_2") + rs.getInt("MON_LESS_3") + rs.getInt("MON_LESS_4") + rs.getInt("MON_LESS_5") + rs.getInt("MON_LESS_6") + rs.getInt("MON_LESS_7");
								
								out.println("<tr>");
								out.println("<td width=\"5%\"  align='left'   onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:left;cursor:pointer;}'  ><u> "+rs.getString("BRANCH_DESC")+" </u></td>"); 	
						        out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("COUNTS")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_due+" ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_collected+" ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT_NEW"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_cases+" ><u> "+nf1.format(rs.getDouble("RENTAL_PERCENTAGE"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_due+" ><u> "+nf1.format(rs.getDouble("TOTAL_ARREARS"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_collected+" ><u> "+nf1.format(rs.getDouble("ARR_COLLECTION"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_cases+" ><u> "+nf1.format(rs.getDouble("ARREARS_PERCENTAGE"))+" </u></td>");
		
						        out.println("<td width=\"5%\"  STYLE='{text-align:right;}' > "+m_TEMP_ALL_ZERO_CASES+" </td>"); 
		
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_6');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_6")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_5');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_5")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_4');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_4")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_3');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_3")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_2');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_2")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_1');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_1")+" </u></td>");
								out.println("</tr>");
								
							}
							else{
								
								sub_tot_due_rental_perc = 0;
								sub_tot_arr_perc = 0;
								
								if(m_sub_RENTAL_AMOUNT>0){
									sub_tot_due_rental_perc = (m_sub_RENTAL_AMOUNT_NEW / m_sub_RENTAL_AMOUNT)*100;					
								}
								else{
									sub_tot_due_rental_perc = 0;
								}
								
								if(m_sub_TOTAL_ARREARS>0){
									sub_tot_arr_perc = (m_sub_ARR_COLLECTION / m_sub_TOTAL_ARREARS)*100;					
								}
								else{
									sub_tot_arr_perc = 0;
								}
								
								out.println("<tr bgcolor="+m_colour_region_total+" >");
								out.println("<td width=\"5%\"  align='left' ><b> <b>"+region_code+"</b> </b></td>"); 	
								out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_count)+" </b></td>"); 
								out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_RENTAL_AMOUNT)+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_RENTAL_AMOUNT_NEW)+" </b></td>"); 
								out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(sub_tot_due_rental_perc)+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_TOTAL_ARREARS)+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_ARR_COLLECTION)+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(sub_tot_arr_perc)+" </b></td>");
		
						        out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_TEMP_ALL_ZERO_CASES+" </b></td>");
		
								out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_6+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_5+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_4+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_3+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_2+" </b></td>");
								out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_1+" </b></td>");
								out.println("</tr>");
								
								
								region_code = rs.getString("REGION_CODE");
								
								m_sub_RENTAL_AMOUNT     = 0;
								m_sub_RENTAL_AMOUNT_NEW = 0;
								m_sub_TOTAL_ARREARS     = 0;
								m_sub_ARR_COLLECTION    = 0;
								m_sub_count           = 0;
								m_sub_MON_LESS_7      = 0;
								m_sub_MON_LESS_6      = 0;
								m_sub_MON_LESS_5      = 0;
								m_sub_MON_LESS_4      = 0;
								m_sub_MON_LESS_3      = 0;
								m_sub_MON_LESS_2      = 0;
								m_sub_MON_LESS_1      = 0;
								
								m_sub_TEMP_ALL_ZERO_CASES = 0;
								
								
								out.println("<tr>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>"); 
								//out.println("<td width=\"5%\"  align='left' ><b> "+region_code+" </b></td>"); 	
						        out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>"); 
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>"); 
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
		
						        out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
		
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("<td width=\"5%\"  align='left' ><b> &nbsp; </b></td>");
								out.println("</tr>");
								
								m_sub_RENTAL_AMOUNT     = m_sub_RENTAL_AMOUNT + rs.getDouble("RENTAL_AMOUNT");
								m_sub_RENTAL_AMOUNT_NEW = m_sub_RENTAL_AMOUNT_NEW + rs.getDouble("RENTAL_AMOUNT_NEW");
								m_sub_TOTAL_ARREARS     = m_sub_TOTAL_ARREARS + rs.getDouble("TOTAL_ARREARS");
								m_sub_ARR_COLLECTION    = m_sub_ARR_COLLECTION + rs.getDouble("ARR_COLLECTION");
								m_sub_count           = m_sub_count + rs.getInt("COUNTS");
								m_sub_MON_LESS_7      = m_sub_MON_LESS_7 + rs.getInt("MON_LESS_7");
								m_sub_MON_LESS_6      = m_sub_MON_LESS_6 + rs.getInt("MON_LESS_6");
								m_sub_MON_LESS_5      = m_sub_MON_LESS_5 + rs.getInt("MON_LESS_5");
								m_sub_MON_LESS_4      = m_sub_MON_LESS_4 + rs.getInt("MON_LESS_4");
								m_sub_MON_LESS_3      = m_sub_MON_LESS_3 + rs.getInt("MON_LESS_3");
								m_sub_MON_LESS_2      = m_sub_MON_LESS_2 + rs.getInt("MON_LESS_2");
								m_sub_MON_LESS_1      = m_sub_MON_LESS_1 + rs.getInt("MON_LESS_1");
								
								//m_sub_TEMP_ALL_ZERO_CASES = 0;
								m_sub_TEMP_ALL_ZERO_CASES = m_sub_TEMP_ALL_ZERO_CASES + rs.getInt("MON_LESS_1") + rs.getInt("MON_LESS_2") + rs.getInt("MON_LESS_3") + rs.getInt("MON_LESS_4") + rs.getInt("MON_LESS_5") + rs.getInt("MON_LESS_6") + rs.getInt("MON_LESS_7");
								
								out.println("<tr>");
								out.println("<td width=\"5%\"  align='left'   onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:left;cursor:pointer;}'  ><u> "+rs.getString("BRANCH_DESC")+" </u></td>"); 	
						        out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("COUNTS")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_due+" ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_collected+" ><u> "+nf1.format(rs.getDouble("RENTAL_AMOUNT_NEW"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_cases+" ><u> "+nf1.format(rs.getDouble("RENTAL_PERCENTAGE"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_due+" ><u> "+nf1.format(rs.getDouble("TOTAL_ARREARS"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_collected+" ><u> "+nf1.format(rs.getDouble("ARR_COLLECTION"))+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown('"+rs.getString("BRANCH_CODE")+"');\" STYLE='{text-align:right;cursor:pointer;}' bgcolor="+m_colour_col_cases+" ><u> "+nf1.format(rs.getDouble("ARREARS_PERCENTAGE"))+" </u></td>");
		
						        out.println("<td width=\"5%\"  STYLE='{text-align:right;}' > "+m_TEMP_ALL_ZERO_CASES+" </td>"); 
		
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_6');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_6")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_5');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_5")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_4');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_4")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_3');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_3")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_2');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_2")+" </u></td>");
								out.println("<td width=\"5%\"  onclick=\"view_report_drilldown_activated('"+rs.getString("BRANCH_CODE")+"','MON_LESS_1');\" STYLE='{text-align:right;cursor:pointer;}' ><u> "+rs.getInt("MON_LESS_1")+" </u></td>");
								out.println("</tr>");
								
							}
						
				}
		
		
				out.println("<tr bgcolor="+m_colour_region_total+" >");
				out.println("<td width=\"5%\"  align='left' ><b> "+region_code+" </b></td>"); 	
				out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_count)+"  </b></td>"); 
				out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_RENTAL_AMOUNT)+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_RENTAL_AMOUNT_NEW)+" </b></td>"); 
				out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(sub_tot_due_rental_perc)+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_TOTAL_ARREARS)+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(m_sub_ARR_COLLECTION)+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+nf1.format(sub_tot_arr_perc)+" </b></td>");
		
				out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_TEMP_ALL_ZERO_CASES+" </b></td>");
		
				out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_6+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_5+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_4+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_3+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_2+" </b></td>");
				out.println("<td width=\"5%\"  align='right' ><b> "+m_sub_MON_LESS_1+" </b></td>");
				out.println("</tr>");
				
				
				double tot_due_rental_perc = 0;
				double tot_arr_perc = 0;
				
				if(m_RENTAL_AMOUNT>0){
					tot_due_rental_perc = (m_RENTAL_AMOUNT_NEW / m_RENTAL_AMOUNT)*100;					
				}
				else{
					tot_due_rental_perc = 0;
				}
				
				if(m_TOTAL_ARREARS>0){
					tot_arr_perc = (m_ARR_COLLECTION / m_TOTAL_ARREARS)*100;					
				}
				else{
					tot_arr_perc = 0;
				}
				
				out.println("<tr bgcolor="+m_colour_region_total+" >");
				out.println("<td width=\"5%\"  align='left'   ><b> Total </b></td>"); 	
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_count)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_RENTAL_AMOUNT)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_RENTAL_AMOUNT_NEW)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(tot_due_rental_perc)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_TOTAL_ARREARS)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_ARR_COLLECTION)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(tot_arr_perc)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+m_ALL_ZERO_CASES+" </b></td>"); 	
				//out.println("<td width=\"5%\"  align='right'  ><b> "+m_MON_LESS_7+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+m_MON_LESS_6+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+m_MON_LESS_5+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+m_MON_LESS_4+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+m_MON_LESS_3+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+m_MON_LESS_2+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+m_MON_LESS_1+" </b></td>");
				out.println("</tr>");

	
				out.println("</table>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
				
			}
			
			else if(m_chksql.equals("view_report_drilldown")){	
				
				String m_date = "";
				String m_location = "";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
			
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				
				
				String m_branch="";
				String m_perform_status = "";
				String m_region = "";
				String m_active_status_string = "";
				String m_officer="";
				//String cr_officer = "";
				String m_active_status = "";
				String m_report_type = "";
				
				
				if(req.getParameter("report_type")!=null ){
					m_report_type=req.getParameter("report_type").trim();
				}

				
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}

				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' ";
					m_active_status_string = " AND YARD_STATUS = '"+m_active_status+"' ";
				}

				
				String sql_m_region = "  ";
				if(!(m_region.equals("NOT_SELECT"))){ 
					sql_m_region = " AND REGION_CODE = '"+m_region+"' ";    
				}

				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				String sql_m_officer = "   ";
				if((!m_officer.equals(""))){
					sql_m_officer = " AND   COLL_OFICER = '"+m_officer+"' "; // added by udara 09-08-2017
				}
				
				
				String sql_perform_status = "   ";
				
				if(!m_perform_status.equals("")){
					sql_perform_status = " AND PERFORM_STATUS = '"+m_perform_status+"'  ";
				}

				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Past Six Month Collection Performance Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 

				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");
			
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Past Six Month Collection Performance Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				out.println("<br>");
				

				out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");	

				out.println("<tr>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Finance No </b></td>"); 	
		        out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Due Rental </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Rental collected as at "+m_date+"</b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> % </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Due arrears </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Arrears Collected as at "+m_date+"</b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> % </b></td>");
				out.println("</tr>");
				
				String Sql_data = "";
				
				stmt = conn.createStatement ();
				
				//out.println(Sql_data);
				
				
				/*
							Sql_data="  "+							
										" SELECT "+
										      " FINANCE_NO, "+
										      " RENTAL_AMOUNT, "+
										      " RENTAL_AMOUNT_NEW, "+     
										      " (CASE WHEN RENTAL_AMOUNT_NEW <> 0 THEN "+
										          " (RENTAL_AMOUNT / RENTAL_AMOUNT_NEW)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) RENTAL_PERCENTAGE, "+      
										      " TOTAL_ARREARS, "+
										      " ARR_COLLECTION, "+     
										      " (CASE WHEN ARR_COLLECTION <> 0 THEN "+
										          " (TOTAL_ARREARS / ARR_COLLECTION)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) ARREARS_PERCENTAGE "+        
										          " FROM (  "+        
										                  " SELECT "+
										                      " FINANCE_NO, "+
										                      " BRANCH_CODE, "+
										                      " RENTAL_AMOUNT, "+
										                      " RENTAL_AMOUNT_NEW, "+
										                      " TOTAL_ARREARS, "+
										                      " ARR_COLLECTION "+
										                          " FROM "+m_schema_name+".AF_TBL_SYS_REP_PAST_6_MONTH "+
										                          " WHERE ENT_USER = '"+m_username+"' "+
										                          " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
										                          " AND BRANCH_CODE = '"+m_location+"' "+
																	
																	"  "+sql_m_officer+" "+
																	"  "+m_active_status_string+" "+
																	"  "+sql_m_region+" "+ 
																	"  "+sql_perform_status+"  "+
																	
										                          " ORDER BY BRANCH_CODE "+
														" )	 "+								
												" ";
							
							*/
				
				Sql_data="  "+							
										" SELECT "+
										      " FINANCE_NO, "+
										      " RENTAL_AMOUNT, "+
										      " RENTAL_AMOUNT_NEW, "+     
										      " (CASE WHEN RENTAL_AMOUNT <> 0 THEN "+
										          " (RENTAL_AMOUNT_NEW / RENTAL_AMOUNT)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) RENTAL_PERCENTAGE, "+      
										      " TOTAL_ARREARS, "+
										      " ARR_COLLECTION, "+     
										      " (CASE WHEN TOTAL_ARREARS <> 0 THEN "+
										          " (ARR_COLLECTION / TOTAL_ARREARS)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) ARREARS_PERCENTAGE "+        
										          " FROM (  "+        
										                  " SELECT "+
										                      " FINANCE_NO, "+
										                      " BRANCH_CODE, "+
										                      " RENTAL_AMOUNT, "+
										                      " RENTAL_AMOUNT_NEW, "+
										                      " TOTAL_ARREARS, "+
										                      " ARR_COLLECTION "+
										                          " FROM "+m_schema_name+".AF_TBL_SYS_REP_PAST_6_HIS "+
										                          //" WHERE ENT_USER = '"+m_username+"' "+
										                          " WHERE REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
										                          " AND BRANCH_CODE = '"+m_location+"' "+
																	
																	"  "+sql_m_officer+" "+
																	"  "+m_active_status_string+" "+
																	"  "+sql_m_region+" "+ 
																	"  "+sql_perform_status+"  "+
																	
										                          " ORDER BY BRANCH_CODE "+
														" )	 "+								
												" ";
				
				

				 rs=stmt.executeQuery(Sql_data);
					
					double m_RENTAL_AMOUNT = 0;
					double m_RENTAL_AMOUNT_NEW = 0;
					double m_TOTAL_ARREARS = 0;
					double m_ARR_COLLECTION = 0;

				while(rs.next()){
					
						m_RENTAL_AMOUNT     = m_RENTAL_AMOUNT + rs.getDouble("RENTAL_AMOUNT");
						m_RENTAL_AMOUNT_NEW = m_RENTAL_AMOUNT_NEW + rs.getDouble("RENTAL_AMOUNT_NEW");
						m_TOTAL_ARREARS     = m_TOTAL_ARREARS + rs.getDouble("TOTAL_ARREARS");
						m_ARR_COLLECTION    = m_ARR_COLLECTION + rs.getDouble("ARR_COLLECTION");

						out.println("<tr>");
						//out.println("<td width=\"5%\"  align='left'    > "+rs.getString("FINANCE_NO")+" </td>"); 	
						out.println("<td width=\"5%\"  align='left'    STYLE='text-align:left; cursor:hand;' onclick=\"show_transaction_info('','"+rs.getString("FINANCE_NO")+"');\" > "+rs.getString("FINANCE_NO")+" </td>");
				        out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("RENTAL_AMOUNT"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("RENTAL_AMOUNT_NEW"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf.format(rs.getDouble("RENTAL_PERCENTAGE"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("TOTAL_ARREARS"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("ARR_COLLECTION"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf.format(rs.getDouble("ARREARS_PERCENTAGE"))+" </td>");
						out.println("</tr>");
						
				}
				
				out.println("<tr>");	
				out.println("<td width=\"5%\"  align='right'  ><b> &nbsp; </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_RENTAL_AMOUNT)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_RENTAL_AMOUNT_NEW)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> &nbsp; </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_TOTAL_ARREARS)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_ARR_COLLECTION)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> &nbsp; </b></td>");
				out.println("</tr>");

				out.println("</table>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			else if(m_chksql.equals("view_report_drilldown_activated")){	
				
				String m_date = "";
				String m_location = "";
				String m_type = "";
				String m_type_string = "";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
			
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("type")!=null ){
					m_type=req.getParameter("type").trim();
				}
				
				
				String m_branch="";
				String m_perform_status = "";
				String m_region = "";
				String m_active_status_string = "";
				String m_officer="";
				//String cr_officer = "";
				String m_active_status = "";
				String m_report_type = "";
				
				
				if(req.getParameter("report_type")!=null ){
					m_report_type=req.getParameter("report_type").trim();
				}

				
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}

				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' ";
					m_active_status_string = " AND YARD_STATUS = '"+m_active_status+"' ";
				}

				
				String sql_m_region = "  ";
				if(!(m_region.equals("NOT_SELECT"))){ 
					sql_m_region = " AND REGION_CODE = '"+m_region+"' ";    
				}

				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				String sql_m_officer = "   ";
				if((!m_officer.equals(""))){
					sql_m_officer = " AND   COLL_OFICER = '"+m_officer+"' "; // added by udara 09-08-2017
				}
				
				
				String sql_perform_status = "   ";
				
				if(!m_perform_status.equals("")){
					sql_perform_status = " AND PERFORM_STATUS = '"+m_perform_status+"'  ";
				}
				
				//if(m_type.equals("ALL_ZERO_CASES"))
					//m_type_string = "AND ALL_ZERO_CASES = 1 ";
				if(m_type.equals("MON_LESS_7"))
					m_type_string = "AND MON_LESS_7 = 1 ";
				else if(m_type.equals("MON_LESS_6"))
					m_type_string = "AND MON_LESS_6 = 1 ";
				else if(m_type.equals("MON_LESS_5"))
					m_type_string = "AND MON_LESS_5 = 1 ";
				else if(m_type.equals("MON_LESS_4"))
					m_type_string = "AND MON_LESS_4 = 1 ";
				else if(m_type.equals("MON_LESS_3"))
					m_type_string = "AND MON_LESS_3 = 1 ";
				else if(m_type.equals("MON_LESS_2"))
					m_type_string = "AND MON_LESS_2 = 1 ";
				else if(m_type.equals("MON_LESS_1"))
					m_type_string = "AND MON_LESS_1 = 1 ";
					

				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Past Six Month Collection Performance Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 

				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");
			
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Past Six Month Collection Performance Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				out.println("<br>");
				

				out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");	

				out.println("<tr>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Finance No </b></td>"); 	
		        out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Due Rental </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Rental collected as at "+m_date+"</b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> % </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Due arrears </b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> Arrears Collected as at "+m_date+"</b></td>");
				out.println("<td width=\"5%\"  align='center'  bgcolor='lightgray' ><b> % </b></td>");
				out.println("</tr>");
				
				String Sql_data = "";
				
				stmt = conn.createStatement ();
				
				//out.println(Sql_data);
				/*
							Sql_data="  "+							
										" SELECT "+
										      " FINANCE_NO, "+
										      " RENTAL_AMOUNT, "+
										      " RENTAL_AMOUNT_NEW, "+     
										      " (CASE WHEN RENTAL_AMOUNT_NEW <> 0 THEN "+
										          " (RENTAL_AMOUNT / RENTAL_AMOUNT_NEW)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) RENTAL_PERCENTAGE, "+      
										      " TOTAL_ARREARS, "+
										      " ARR_COLLECTION, "+     
										      " (CASE WHEN ARR_COLLECTION <> 0 THEN "+
										          " (TOTAL_ARREARS / ARR_COLLECTION)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) ARREARS_PERCENTAGE "+        
										          " FROM (  "+        
										                  " SELECT "+
										                      " FINANCE_NO, "+
										                      " BRANCH_CODE, "+
										                      " RENTAL_AMOUNT, "+
										                      " RENTAL_AMOUNT_NEW, "+
										                      " TOTAL_ARREARS, "+
										                      " ARR_COLLECTION "+
										                          " FROM "+m_schema_name+".AF_TBL_SYS_REP_PAST_6_MONTH "+
										                          " WHERE ENT_USER = '"+m_username+"' "+
										                          " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
										                          " AND BRANCH_CODE = '"+m_location+"' "+
																  " " + m_type_string + " "+
																	
																	"  "+sql_m_officer+" "+
																	"  "+m_active_status_string+" "+
																	"  "+sql_m_region+" "+ 
																	"  "+sql_perform_status+"  "+
																	
										                          " ORDER BY BRANCH_CODE "+
														" )	 "+								
												" ";
							
							*/
				
				Sql_data="  "+							
										" SELECT "+
										      " FINANCE_NO, "+
										      " RENTAL_AMOUNT, "+
										      " RENTAL_AMOUNT_NEW, "+     
										      " (CASE WHEN RENTAL_AMOUNT <> 0 THEN "+
										          " (RENTAL_AMOUNT_NEW / RENTAL_AMOUNT)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) RENTAL_PERCENTAGE, "+      
										      " TOTAL_ARREARS, "+
										      " ARR_COLLECTION, "+     
										      " (CASE WHEN TOTAL_ARREARS <> 0 THEN "+
										          " (ARR_COLLECTION / TOTAL_ARREARS)*100 "+
										      " ELSE "+
										          " 0 "+
										      " END) ARREARS_PERCENTAGE "+        
										          " FROM (  "+        
										                  " SELECT "+
										                      " FINANCE_NO, "+
										                      " BRANCH_CODE, "+
										                      " RENTAL_AMOUNT, "+
										                      " RENTAL_AMOUNT_NEW, "+
										                      " TOTAL_ARREARS, "+
										                      " ARR_COLLECTION "+
										                          " FROM "+m_schema_name+".AF_TBL_SYS_REP_PAST_6_HIS "+
										                          //" WHERE ENT_USER = '"+m_username+"' "+
										                          " WHERE REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
										                          " AND BRANCH_CODE = '"+m_location+"' "+
																  " " + m_type_string + " "+
																	
																	"  "+sql_m_officer+" "+
																	"  "+m_active_status_string+" "+
																	"  "+sql_m_region+" "+ 
																	"  "+sql_perform_status+"  "+
																	
										                          " ORDER BY BRANCH_CODE "+
														" )	 "+								
												" ";
				
				

				 rs=stmt.executeQuery(Sql_data);
					
					double m_RENTAL_AMOUNT = 0;
					double m_RENTAL_AMOUNT_NEW = 0;
					double m_TOTAL_ARREARS = 0;
					double m_ARR_COLLECTION = 0;

				while(rs.next()){
					
						m_RENTAL_AMOUNT     = m_RENTAL_AMOUNT + rs.getDouble("RENTAL_AMOUNT");
						m_RENTAL_AMOUNT_NEW = m_RENTAL_AMOUNT_NEW + rs.getDouble("RENTAL_AMOUNT_NEW");
						m_TOTAL_ARREARS     = m_TOTAL_ARREARS + rs.getDouble("TOTAL_ARREARS");
						m_ARR_COLLECTION    = m_ARR_COLLECTION + rs.getDouble("ARR_COLLECTION");

						out.println("<tr>");
						//out.println("<td width=\"5%\"  align='left'    > "+rs.getString("FINANCE_NO")+" </td>"); 	
						out.println("<td width=\"5%\"  align='left'    STYLE='text-align:left; cursor:hand;' onclick=\"show_transaction_info('','"+rs.getString("FINANCE_NO")+"');\" > "+rs.getString("FINANCE_NO")+" </td>");
				        out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("RENTAL_AMOUNT"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("RENTAL_AMOUNT_NEW"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf.format(rs.getDouble("RENTAL_PERCENTAGE"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("TOTAL_ARREARS"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf1.format(rs.getDouble("ARR_COLLECTION"))+" </td>");
						out.println("<td width=\"5%\"  align='right'   > "+nf.format(rs.getDouble("ARREARS_PERCENTAGE"))+" </td>");
						out.println("</tr>");
						
				}
				
				out.println("<tr>");	
				out.println("<td width=\"5%\"  align='right'  ><b> &nbsp; </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_RENTAL_AMOUNT)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_RENTAL_AMOUNT_NEW)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> &nbsp; </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_TOTAL_ARREARS)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> "+nf1.format(m_ARR_COLLECTION)+" </b></td>");
				out.println("<td width=\"5%\"  align='right'  ><b> &nbsp; </b></td>");
				out.println("</tr>");

				out.println("</table>");
				
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

