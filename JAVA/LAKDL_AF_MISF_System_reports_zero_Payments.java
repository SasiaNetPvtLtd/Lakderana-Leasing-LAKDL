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


public class LAKDL_AF_MISF_System_reports_zero_Payments extends javax.servlet.http.HttpServlet { 
	
	
	
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

					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SYS_REP_ZERO_PAYMENT(:1,:2);END;");
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
				out.println("	if(document.Form1.AT_DAY.value!=\"\" && document.Form1.AT_MONTH.value!=\"\" && document.Form1.AT_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.AT_DAY.value+'-'+document.Form1.AT_MONTH.value+'-'+document.Form1.AT_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_zero_Payments?chksql=run_report&date=\"+m_date;"); 
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

				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_zero_Payments?chksql=print_report_system_report_zero&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;");
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_zero_Payments?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_zero_Payments?chksql=main_page';"); 
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


			else if(m_chksql.equals("print_report_system_report_zero")){
				

				String m_date="";
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
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
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


				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				stmt = conn.createStatement ();
				
				String m_report_date = "";
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+
					//" TO_CHAR(TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH')-1,'DD-MM-YYYY') "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_report_date = rs1.getString(2);
				}

				String m_report_run_date = "";
				
				
				/*
				if(!m_date.equals(m_cur_date)){
					m_date =	m_report_date;
				}
				*/
				
				rs1 = stmt.executeQuery(" "+
					" SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS AM') "+
					" FROM "+m_schema_name+".ARREARS_REPORT_RUN_LOG "+
					" WHERE POINT = 'FINISH' ");
				
				if(rs1.next()){
					m_report_run_date = rs1.getString(1);
				}
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"'), "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_officer_name=rs.getString(2);
				}
				
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Zero Payment Report</TITLE>"); 
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

				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_zero_Payments?chksql=print_report_new_logic&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&active_status="+m_active_status+"&region="+m_region+"&perform_status="+m_perform_status+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				
				out.println("  window.location.href=m_url;"); 
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
				
				out.println("function show_drill_down(location,report_type){ "); 
				
				//out.println("   alert(val); ");

				out.println("			m_date='"+m_date+"';");
				out.println("			m_location=location;");
				out.println("			m_officer='"+m_officer+"';");
				out.println("           m_region = '"+m_region+"'; ");
				out.println("           var m_perform_status = '"+m_perform_status+"';   "); // added by udara 17-08-2015
				out.println("           m_active_status='"+m_active_status+"'; ");
				out.println("           m_report_type=report_type; ");
				
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_System_reports_zero_Payments?chksql=print_report_system_report_zero_below_total&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&active_status=\"+m_active_status+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status+\"&report_type=\"+m_report_type;"); // added by udara 26-09-2017
				out.println("           window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 

				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				
				
				String Sql_data="";
				
				
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"80%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Zero Payment Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align=\"center\" width=\"80%\" border=\"0\" class=\"table\">");

				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
				out.println("<td width=\"150\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Zero Payment Report for the</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_date+"</td>"); 
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
				
				out.println("<tr>");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Last Report Run Time</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_report_run_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");

				out.println("</table>");

				out.println("<br>");	
				
				// added by udara 29-11-2018
				out.println("<table align=\"center\" width=\"80%\" border=\"0\" class=\"table\">");
				
				out.println("<tr>");
				out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   > Above 25% </td>"); 
				out.println("<td width=\"10\" STYLE='{font: bold 8pt arial; text-align:left;}' bgcolor='red'  > &nbsp; </td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   > &nbsp; </td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   > 11 to 25 </td>"); 
				out.println("<td width=\"10\" STYLE='{font: bold 8pt arial; text-align:left;}' bgcolor='yellow'  > &nbsp; </td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   > &nbsp; </td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"100\" STYLE='{font: bold 8pt arial; text-align:left;}'   > 10 & Below </td>"); 
				out.println("<td width=\"10\" STYLE='{font: bold 8pt arial; text-align:left;}' bgcolor='green'  > &nbsp; </td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   > &nbsp; </td>"); 
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<br>");
				// end by udara 29-11-2018
				
				out.println("<table align=\"center\" width=\"80%\" border=\"0\" class=\"table\">"); // udara 28-05-2019
				out.println("<tr>"); // udara 28-05-2019
				out.println("<td>"); // udara 28-05-2019
				
				out.println("<table id=mytable align=\"left\" width=\"70%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
			
				out.println("<tr>");
				//out.println("<td width=\"2%\"  align='center'  ROWSPAN='2' bgcolor='lightgray' ><b>No.</b></td>"); 
				out.println("<td width=\"10%\" align='center'  ROWSPAN='2' bgcolor='lightgray' ><b>Branch</b></td>"); 
				out.println("<td width='25%'   class=div_input colspan=\"3\" align='center' bgcolor='lightblue' ><B> Below One Year </B></td>");
				out.println("<td width='25%'   class=div_input colspan=\"3\" align='center' bgcolor='lightblue' ><B> Above One Year </B></td>"); 
				out.println("<td width=\"8%\"  align='center'  ROWSPAN='2' bgcolor='lightgray' ><b> Zero cases last month & current month as at date </b></td>"); 
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> No. of total case </b></td>"); 
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> Zero payment case </b></td>");
				out.println("<td width=\"5%\"   align='right' bgcolor='lightgray' ><b> % </b></td>");
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> No. of total case </b></td>"); 
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> Zero payment case </b></td>");
				out.println("<td width=\"5%\"   align='right' bgcolor='lightgray' ><b> % </b></td>");
				out.println("</tr>");

				
				String sql_m_region = "  ";
				if(!(m_region.equals("NOT_SELECT"))){ 
					sql_m_region = " AND REGION_CODE = '"+m_region+"' ";    
				}
				
				
				String sql_m_location = "   ";
				if((!m_location.equals("")) && (!m_location.equals(""))){
					sql_m_location = " AND   LOCATION_CODE = '"+m_location+"' ";
				}
				
				String sql_m_officer = "   ";
				if((!m_officer.equals(""))){
					sql_m_officer = " AND   COLL_OFICER = '"+m_officer+"' "; // added by udara 09-08-2017
				}
				
				
				String sql_perform_status = "   ";
				
				if(!m_perform_status.equals("")){
					sql_perform_status = " AND PERFORM_STATUS = '"+m_perform_status+"'  ";
				}
				

				String sort_string = " ORDER BY VALUE_DATE,FINANCE_NO,CLIENT_CODE,FULL_NAME,AGE_NEW,CLIENT_TEL_NO,DUE_RENTAL_AMOUNT,FOL_REMARK,CITY_NAME,COLL_OFICER,VEHICLE_NO,TOTAL_PERIOD	 ";
				
				if(m_sort_column.equals(""))
					m_sort_column = " FINANCE_NO ";
				
				if(m_order_by_type.equals(""))
					m_sort_column = " ASC ";
				
				if(!m_sort_column.equals("VALUE_DATE"))
					sort_string = " ORDER BY  VALUE_DATE, "+m_sort_column+"  "+m_order_by_type+"  ";
				else
					sort_string = " ORDER BY  VALUE_DATE "+m_order_by_type+" ";

				
				if(!m_date.equals(m_cur_date)){
				
				 		Sql_data = " "+
						 " SELECT LOCATION_CODE, "+
							    " NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE),LOCATION_CODE) LOCATION_DESC, "+ 
								" TOTAL_CASE_BEFORE, "+
								" ZERO_CASE_BEFORE, "+
								" ARR_CASE_BEFORE, "+
								
								//" CASE WHEN TOTAL_CASE_BEFORE <> 0 AND ARR_CASE_BEFORE <> 0 THEN "+
								//  " ROUND((ARR_CASE_BEFORE / TOTAL_CASE_BEFORE)*100,2) "+  
								" CASE WHEN TOTAL_CASE_BEFORE <> 0 AND ZERO_CASE_BEFORE <> 0 THEN "+
								  " ROUND((ZERO_CASE_BEFORE / TOTAL_CASE_BEFORE)*100,2) "+  
								" ELSE "+
								  " 0 "+
								" END BELOW_PERCENTAGE, "+

								" TOTAL_CASE_AFTER, "+
								" ZERO_CASE_AFTER, "+
								" ARR_CASE_AFTER, "+
								
								//" CASE WHEN TOTAL_CASE_AFTER <> 0 AND ARR_CASE_AFTER <> 0 THEN "+
								//  " ROUND((ARR_CASE_AFTER / TOTAL_CASE_AFTER)*100,2) "+ 
								" CASE WHEN ZERO_CASE_AFTER <> 0 AND ZERO_CASE_AFTER <> 0 THEN "+
								  " ROUND((ZERO_CASE_AFTER / TOTAL_CASE_AFTER)*100,2) "+ 
								" ELSE "+
								  " 0 "+
								" END AFTER_PERCENTAGE, "+
								
								" (ZERO_CASE_BEFORE + ZERO_CASE_AFTER) TOTAL_ZERO_CASE, "+
								" LAST_MONTH_ZERO, "+ 
								//" REGION_CODE "+
								" NVL("+m_schema_name+".AF_CO_GET_REG_DESC(REGION_CODE),'Z - No Region') REGION_CODE "+
								
								" FROM ( "+
								
								      " SELECT LOCATION_CODE, "+
								      " SUM(TOTAL_CASE_BEFORE) TOTAL_CASE_BEFORE, "+
								      " SUM(ZERO_CASE_BEFORE) ZERO_CASE_BEFORE, "+
								      " SUM(ARR_CASE_BEFORE) ARR_CASE_BEFORE, "+
								      " SUM(TOTAL_CASE_AFTER) TOTAL_CASE_AFTER, "+
								      " SUM(ZERO_CASE_AFTER) ZERO_CASE_AFTER, "+
								      " SUM(ARR_CASE_AFTER) ARR_CASE_AFTER, "+
									  " SUM(LAST_MONTH_ZERO) LAST_MONTH_ZERO, "+
									  " REGION_CODE "+
								
								      " FROM( "+
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " COUNT(LOCATION_CODE) TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
								              " WHERE YEAR_STATUS = 'BELOW' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												//" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " COUNT(LOCATION_CODE) ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
								              " WHERE YEAR_STATUS = 'BELOW' "+
								              " AND ZERO_TYPE = 'ZERO' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												//" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " COUNT(LOCATION_CODE) ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
								              " WHERE YEAR_STATUS = 'BELOW' "+
								              " AND TOTAL_AMOUNT > 0 "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
												//" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " COUNT(LOCATION_CODE) TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
								              " WHERE YEAR_STATUS = 'ABOVE' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												//" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " COUNT(LOCATION_CODE) ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
								              " WHERE YEAR_STATUS = 'ABOVE' "+
								              " AND ZERO_TYPE = 'ZERO' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												//" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " COUNT(LOCATION_CODE) ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
								              " WHERE YEAR_STATUS = 'ABOVE' "+
								              " AND TOTAL_AMOUNT > 0 "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												 " AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
												//" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
												
												
											  " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " COUNT(LOCATION_CODE)  LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
								              " WHERE REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ZERO_TYPE = 'ZERO' "+ 
												" AND LAST_MONTH_ZERO = 'Y' "+
												//" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
												
								
								
								      " ) "+
								      " GROUP BY REGION_CODE, LOCATION_CODE "+
								" ) "+
								" ORDER BY REGION_CODE, LOCATION_DESC ";
				
				}
				
				else{
					
					Sql_data = " "+
						 " SELECT LOCATION_CODE, "+
							    " "+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE) LOCATION_DESC, "+ 
								" TOTAL_CASE_BEFORE, "+
								" ZERO_CASE_BEFORE, "+
								" ARR_CASE_BEFORE, "+
								
								//" CASE WHEN TOTAL_CASE_BEFORE <> 0 AND ARR_CASE_BEFORE <> 0 THEN "+
								//  " ROUND((ARR_CASE_BEFORE / TOTAL_CASE_BEFORE)*100,2) "+  
								" CASE WHEN TOTAL_CASE_BEFORE <> 0 AND ZERO_CASE_BEFORE <> 0 THEN "+
								  " ROUND((ZERO_CASE_BEFORE / TOTAL_CASE_BEFORE)*100,2) "+  
								" ELSE "+
								  " 0 "+
								" END BELOW_PERCENTAGE, "+

								" TOTAL_CASE_AFTER, "+
								" ZERO_CASE_AFTER, "+
								" ARR_CASE_AFTER, "+
								
								//" CASE WHEN TOTAL_CASE_AFTER <> 0 AND ARR_CASE_AFTER <> 0 THEN "+
								//  " ROUND((ARR_CASE_AFTER / TOTAL_CASE_AFTER)*100,2) "+ 
								" CASE WHEN TOTAL_CASE_AFTER <> 0 AND ZERO_CASE_AFTER <> 0 THEN "+
								  " ROUND((ZERO_CASE_AFTER / TOTAL_CASE_AFTER)*100,2) "+ 
								" ELSE "+
								  " 0 "+
								" END AFTER_PERCENTAGE, "+
								
								" (ZERO_CASE_BEFORE + ZERO_CASE_AFTER) TOTAL_ZERO_CASE, "+
								" LAST_MONTH_ZERO, "+ 
								//" REGION_CODE "+
								" NVL("+m_schema_name+".AF_CO_GET_REG_DESC(REGION_CODE),'Z - No Region') REGION_CODE "+
								
								" FROM ( "+
								
								      " SELECT LOCATION_CODE, "+
								      " SUM(TOTAL_CASE_BEFORE) TOTAL_CASE_BEFORE, "+
								      " SUM(ZERO_CASE_BEFORE) ZERO_CASE_BEFORE, "+
								      " SUM(ARR_CASE_BEFORE) ARR_CASE_BEFORE, "+
								      " SUM(TOTAL_CASE_AFTER) TOTAL_CASE_AFTER, "+
								      " SUM(ZERO_CASE_AFTER) ZERO_CASE_AFTER, "+
								      " SUM(ARR_CASE_AFTER) ARR_CASE_AFTER, "+
									  " SUM(LAST_MONTH_ZERO) LAST_MONTH_ZERO, "+
									  " REGION_CODE "+
								
								      " FROM( "+
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " COUNT(LOCATION_CODE) TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
								              " WHERE YEAR_STATUS = 'BELOW' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " COUNT(LOCATION_CODE) ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
								              " WHERE YEAR_STATUS = 'BELOW' "+
								              " AND ZERO_TYPE = 'ZERO' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " COUNT(LOCATION_CODE) ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
								              " WHERE YEAR_STATUS = 'BELOW' "+
								              " AND TOTAL_AMOUNT > 0 "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
												" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " COUNT(LOCATION_CODE) TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
								              " WHERE YEAR_STATUS = 'ABOVE' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " COUNT(LOCATION_CODE) ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
								              " WHERE YEAR_STATUS = 'ABOVE' "+
								              " AND ZERO_TYPE = 'ZERO' "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
								
								              " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " COUNT(LOCATION_CODE) ARR_CASE_AFTER, "+
											  " 0 LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
								              " WHERE YEAR_STATUS = 'ABOVE' "+
								              " AND TOTAL_AMOUNT > 0 "+
								              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												 " AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
												" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
												
												
											  " UNION ALL "+
								
								              " SELECT LOCATION_CODE LOCATION_CODE, "+
								              " 0 TOTAL_CASE_BEFORE, "+
								              " 0 ZERO_CASE_BEFORE, "+
								              " 0 ARR_CASE_BEFORE, "+
								              " 0 TOTAL_CASE_AFTER, "+
								              " 0 ZERO_CASE_AFTER, "+
								              " 0 ARR_CASE_AFTER, "+
											  " COUNT(LOCATION_CODE)  LAST_MONTH_ZERO, "+
											  " REGION_CODE "+
								              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
								              " WHERE REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
												" AND ZERO_TYPE = 'ZERO' "+ 
												" AND LAST_MONTH_ZERO = 'Y' "+
												" AND ENT_USER = '"+m_username+"' "+
												"  "+sql_m_officer+" "+
												"  "+sql_m_location+" "+
												"  "+m_active_status_string+" "+
												"  "+sql_m_region+" "+ 
												"  "+sql_perform_status+"  "+
								              " GROUP BY REGION_CODE, LOCATION_CODE "+
												
								
								
								      " ) "+
								      " GROUP BY REGION_CODE, LOCATION_CODE "+
								" ) "+
								" ORDER BY REGION_CODE, LOCATION_DESC ";
					
					
				}
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
			
				int count = 0;
				
				int tot_cases_before  = 0;
				int zero_cases_before = 0;
				int arr_cases_before  = 0;
				
				int tot_cases_after  = 0;
				int zero_cases_after = 0;
				int arr_cases_after  = 0;
				
				int total_zero_cases = 0;
				
				String m_below_colour = "";
				String m_after_colour = "";
				double below_colour_percentage = 0;
				double after_colour_percentage = 0;
				
				String region_code = "";
				
				int sub_tot_cases_before  = 0;
				int sub_zero_cases_before = 0;
				int sub_arr_cases_before  = 0;
				
				int sub_tot_cases_after  = 0;
				int sub_zero_cases_after = 0;
				int sub_arr_cases_after  = 0;
				
				int sub_total_zero_cases = 0;
				
				double sub_tot_below_percentage = 0;
				double sub_tot_after_percentage = 0;
				
				double sub_d_tot_cases_before = 0;
				double sub_d_arr_cases_before = 0;
				
				double sub_d_tot_cases_after = 0;
				double sub_d_arr_cases_after = 0;
				
				String m_below_colour_sub = "";
				String m_after_colour_sub = "";


				while(rs.next()){	
					
					
							below_colour_percentage = rs.getDouble("BELOW_PERCENTAGE");
							after_colour_percentage = rs.getDouble("AFTER_PERCENTAGE");
							
							if(below_colour_percentage<=10.49){
								m_below_colour = "Chartreuse";
							}
							else if(below_colour_percentage>10.49 && below_colour_percentage<=25.49){
								m_below_colour = "yellow";	
							}
							else if(below_colour_percentage>25.49){
								m_below_colour = "red";	
							}
							
							if(after_colour_percentage<=10.49){
								m_after_colour = "Chartreuse";
							}
							else if(after_colour_percentage>10.49 && after_colour_percentage<=25.49){
								m_after_colour = "yellow";	
							}
							else if(after_colour_percentage>25.49){
								m_after_colour = "red";	
							}
					
					
						tot_cases_before  = tot_cases_before  + rs.getInt("TOTAL_CASE_BEFORE");
						zero_cases_before = zero_cases_before + rs.getInt("ZERO_CASE_BEFORE");
						arr_cases_before  = arr_cases_before  + rs.getInt("ARR_CASE_BEFORE");
						
						tot_cases_after   = tot_cases_after  + rs.getInt("TOTAL_CASE_AFTER");
						zero_cases_after  = zero_cases_after + rs.getInt("ZERO_CASE_AFTER");
						arr_cases_after   = arr_cases_after  + rs.getInt("ARR_CASE_AFTER");

						total_zero_cases  = total_zero_cases  + rs.getInt("LAST_MONTH_ZERO"); 
					
						count = count + 1;
						
						
				
						/*
						out.println("<tr>");
						out.println("<td width=\"2%\"   align='right' > "+count+" </td>"); 
						out.println("<td width=\"10%\"  align='left'  > "+rs.getString("LOCATION_DESC")+" </td>");
						out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_BEFORE")+" </u></td>");
						out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_BEFORE")+" </u></td>");
						out.println("<td width=\"10%\"  align='right' bgcolor='"+m_below_colour+"' > "+nf.format(rs.getDouble("BELOW_PERCENTAGE"))+" </td>");
						out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_AFTER")+" </u></td>");
						out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_AFTER")+" </u></td>");
						out.println("<td width=\"10%\"  align='right' bgcolor='"+m_after_colour+"' > "+nf.format(rs.getDouble("AFTER_PERCENTAGE"))+" </td>"); 
						out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','LAST_MONTH_ZERO');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("LAST_MONTH_ZERO")+" </u></td>");
						out.println("</tr>");
						*/
						
						if(region_code.equals("")){
							
							region_code = rs.getString("REGION_CODE");
							
							sub_tot_cases_before  = sub_tot_cases_before  + rs.getInt("TOTAL_CASE_BEFORE");
							sub_zero_cases_before = sub_zero_cases_before + rs.getInt("ZERO_CASE_BEFORE");
							sub_arr_cases_before  = sub_arr_cases_before  + rs.getInt("ARR_CASE_BEFORE");
							
							sub_tot_cases_after   = sub_tot_cases_after  + rs.getInt("TOTAL_CASE_AFTER");
							sub_zero_cases_after  = sub_zero_cases_after + rs.getInt("ZERO_CASE_AFTER");
							sub_arr_cases_after   = sub_arr_cases_after  + rs.getInt("ARR_CASE_AFTER");
	
							sub_total_zero_cases  = sub_total_zero_cases  + rs.getInt("LAST_MONTH_ZERO"); 
							
							out.println("<tr>");
							//out.println("<td width=\"2%\"   align='right' > &nbsp; </td>"); 
							//out.println("<td width=\"10%\"  align='left'  ><b> "+region_code+" </b></td>");
							out.println("<td width=\"10%\"  align='left'  > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"5%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"5%\"  align='right' > &nbsp; </td>"); 
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("</tr>");
							
							out.println("<tr>");
							//out.println("<td width=\"2%\"   align='right' > "+count+" </td>"); 
							out.println("<td width=\"10%\"  align='left'  > "+rs.getString("LOCATION_DESC")+" </td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_BEFORE")+" </u></td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_BEFORE")+" </u></td>");
							out.println("<td width=\"5%\"  align='right' bgcolor='"+m_below_colour+"' > "+nf1.format(rs.getDouble("BELOW_PERCENTAGE"))+" </td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_AFTER")+" </u></td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_AFTER")+" </u></td>");
							out.println("<td width=\"5%\"  align='right' bgcolor='"+m_after_colour+"' > "+nf1.format(rs.getDouble("AFTER_PERCENTAGE"))+" </td>"); 
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','LAST_MONTH_ZERO');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("LAST_MONTH_ZERO")+" </u></td>");
							out.println("</tr>");
							
							
							
						}
						else if(region_code.equals(rs.getString("REGION_CODE"))){
							
							sub_tot_cases_before  = sub_tot_cases_before  + rs.getInt("TOTAL_CASE_BEFORE");
							sub_zero_cases_before = sub_zero_cases_before + rs.getInt("ZERO_CASE_BEFORE");
							sub_arr_cases_before  = sub_arr_cases_before  + rs.getInt("ARR_CASE_BEFORE");
							
							sub_tot_cases_after   = sub_tot_cases_after  + rs.getInt("TOTAL_CASE_AFTER");
							sub_zero_cases_after  = sub_zero_cases_after + rs.getInt("ZERO_CASE_AFTER");
							sub_arr_cases_after   = sub_arr_cases_after  + rs.getInt("ARR_CASE_AFTER");
	
							sub_total_zero_cases  = sub_total_zero_cases  + rs.getInt("LAST_MONTH_ZERO"); 
							
							out.println("<tr>");
							//out.println("<td width=\"2%\"   align='right' > "+count+" </td>"); 
							out.println("<td width=\"10%\"  align='left'  > "+rs.getString("LOCATION_DESC")+" </td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_BEFORE")+" </u></td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_BEFORE")+" </u></td>");
							out.println("<td width=\"5%\"  align='right' bgcolor='"+m_below_colour+"' > "+nf1.format(rs.getDouble("BELOW_PERCENTAGE"))+" </td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_AFTER")+" </u></td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_AFTER")+" </u></td>");
							out.println("<td width=\"5%\"  align='right' bgcolor='"+m_after_colour+"' > "+nf1.format(rs.getDouble("AFTER_PERCENTAGE"))+" </td>"); 
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','LAST_MONTH_ZERO');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("LAST_MONTH_ZERO")+" </u></td>");
							out.println("</tr>");
							
							
						}
						else{
							
							sub_tot_below_percentage = 0;
							sub_tot_after_percentage = 0;
							
							sub_d_tot_cases_before = sub_tot_cases_before;
							sub_d_arr_cases_before = sub_zero_cases_before;
							
							sub_d_tot_cases_after = sub_tot_cases_after;
							sub_d_arr_cases_after = sub_zero_cases_after;
										
							if(sub_tot_cases_before>0){
								sub_tot_below_percentage = (sub_d_arr_cases_before / sub_d_tot_cases_before)*100;
							}
							else{
								sub_tot_below_percentage = 0;
							}
										
							if(sub_tot_cases_after>0){
								sub_tot_after_percentage = (sub_d_arr_cases_after / sub_d_tot_cases_after)*100;
							}
							else{
								sub_tot_after_percentage = 0;
							}
							
							// added by udara 09-11-2018
							if(sub_tot_below_percentage<=10.49){
								m_below_colour_sub = "Chartreuse";
							}
							else if(sub_tot_below_percentage>10.49 && sub_tot_below_percentage<=25.49){
								m_below_colour_sub = "yellow";	
							}
							else if(sub_tot_below_percentage>25.49){
								m_below_colour_sub = "red";	
							}
							
							if(sub_tot_after_percentage<=10.49){
								m_after_colour_sub = "Chartreuse";
							}
							else if(sub_tot_after_percentage>10.49 && sub_tot_after_percentage<=25.49){
								m_after_colour_sub = "yellow";	
							}
							else if(sub_tot_after_percentage>25.49){
								m_after_colour_sub = "red";	
							}
							// end by udara 09-11-2018
							
							
							out.println("<tr>");
							//out.println("<td width=\"2%\"   align='right' > &nbsp; </td>"); 
							out.println("<td width=\"10%\"  align='left'  bgcolor='lightgray' ><b> "+region_code+" </b></td>");
							out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_tot_cases_before+" </b></td>");
							out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_zero_cases_before+" </b></td>");
							out.println("<td width=\"5%\"   align='right' bgcolor="+m_below_colour_sub+" ><b> "+nf1.format(sub_tot_below_percentage)+" </b></td>");
							out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_tot_cases_after+" </b></td>");
							out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_zero_cases_after+" </b></td>");
							out.println("<td width=\"5%\"   align='right' bgcolor="+m_after_colour_sub+" ><b> "+nf1.format(sub_tot_after_percentage)+" </b></td>"); 
							out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_total_zero_cases+" </b></td>");
							out.println("</tr>");
							
							region_code = rs.getString("REGION_CODE");
							
							sub_tot_cases_before  = 0;
							sub_zero_cases_before = 0;
							sub_arr_cases_before  = 0;
							
							sub_tot_cases_after   = 0;
							sub_zero_cases_after  = 0;
							sub_arr_cases_after   = 0;
	
							sub_total_zero_cases  = 0; 
							
							out.println("<tr>");
							//out.println("<td width=\"2%\"   align='right' > &nbsp; </td>"); 
							//out.println("<td width=\"10%\"  align='left'  ><b> "+region_code+" </b></td>");
							out.println("<td width=\"10%\"  align='left'  > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"5%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("<td width=\"5%\"  align='right' > &nbsp; </td>"); 
							out.println("<td width=\"10%\"  align='right' > &nbsp; </td>");
							out.println("</tr>");
							
							sub_tot_cases_before  = sub_tot_cases_before  + rs.getInt("TOTAL_CASE_BEFORE");
							sub_zero_cases_before = sub_zero_cases_before + rs.getInt("ZERO_CASE_BEFORE");
							sub_arr_cases_before  = sub_arr_cases_before  + rs.getInt("ARR_CASE_BEFORE");
							
							sub_tot_cases_after   = sub_tot_cases_after  + rs.getInt("TOTAL_CASE_AFTER");
							sub_zero_cases_after  = sub_zero_cases_after + rs.getInt("ZERO_CASE_AFTER");
							sub_arr_cases_after   = sub_arr_cases_after  + rs.getInt("ARR_CASE_AFTER");
	
							sub_total_zero_cases  = sub_total_zero_cases  + rs.getInt("LAST_MONTH_ZERO"); 
							
							out.println("<tr>");
							//out.println("<td width=\"2%\"   align='right' > "+count+" </td>"); 
							out.println("<td width=\"10%\"  align='left'  > "+rs.getString("LOCATION_DESC")+" </td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_BEFORE")+" </u></td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_BEFORE');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_BEFORE")+" </u></td>");
							out.println("<td width=\"5%\"  align='right' bgcolor='"+m_below_colour+"' > "+nf1.format(rs.getDouble("BELOW_PERCENTAGE"))+" </td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','TOTAL_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("TOTAL_CASE_AFTER")+" </u></td>");
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','ZERO_CASE_AFTER');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("ZERO_CASE_AFTER")+" </u></td>");
							out.println("<td width=\"5%\"  align='right' bgcolor='"+m_after_colour+"' > "+nf1.format(rs.getDouble("AFTER_PERCENTAGE"))+" </td>"); 
							out.println("<td width=\"10%\"  align='right' onclick=\"show_drill_down('"+rs.getString("LOCATION_CODE")+"','LAST_MONTH_ZERO');\" STYLE='cursor:hand; text-align:right;' ><u> "+rs.getInt("LAST_MONTH_ZERO")+" </u></td>");
							out.println("</tr>");
							
							
						}
						
				
				}
				
				sub_tot_below_percentage = 0;
				sub_tot_after_percentage = 0;
				
				sub_d_tot_cases_before = sub_tot_cases_before;
				sub_d_arr_cases_before = sub_zero_cases_before;
				
				sub_d_tot_cases_after = sub_tot_cases_after;
				sub_d_arr_cases_after = sub_zero_cases_after;
							
				if(sub_tot_cases_before>0){
					sub_tot_below_percentage = (sub_d_arr_cases_before / sub_d_tot_cases_before)*100;
				}
				else{
					sub_tot_below_percentage = 0;
				}
							
				if(sub_tot_cases_after>0){
					sub_tot_after_percentage = (sub_d_arr_cases_after / sub_d_tot_cases_after)*100;
				}
				else{
					sub_tot_after_percentage = 0;
				}
				
							// added by udara 09-11-2018
							if(sub_tot_below_percentage<=10.49){
								m_below_colour_sub = "Chartreuse";
							}
							else if(sub_tot_below_percentage>10.49 && sub_tot_below_percentage<=25.49){
								m_below_colour_sub = "yellow";	
							}
							else if(sub_tot_below_percentage>25.49){
								m_below_colour_sub = "red";	
							}
							
							if(sub_tot_after_percentage<=10.49){
								m_after_colour_sub = "Chartreuse";
							}
							else if(sub_tot_after_percentage>10.49 && sub_tot_after_percentage<=25.49){
								m_after_colour_sub = "yellow";	
							}
							else if(sub_tot_after_percentage>25.49){
								m_after_colour_sub = "red";	
							}
							// end by udara 09-11-2018
				
				
				out.println("<tr>");
				//out.println("<td width=\"2%\"   align='right' > &nbsp; </td>"); 
				out.println("<td width=\"10%\"  align='left'  bgcolor='lightgray' ><b> "+region_code+" </b></td>");
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_tot_cases_before+" </b></td>");
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_zero_cases_before+" </b></td>");
				out.println("<td width=\"5%\"   align='right' bgcolor="+m_below_colour_sub+" ><b> "+nf1.format(sub_tot_below_percentage)+" </b></td>");
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_tot_cases_after+" </b></td>");
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_zero_cases_after+" </b></td>");
				out.println("<td width=\"5%\"   align='right' bgcolor="+m_after_colour_sub+" ><b> "+nf1.format(sub_tot_after_percentage)+" </b></td>"); 
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+sub_total_zero_cases+" </b></td>");
				out.println("</tr>");
				
				
				
				double tot_below_percentage = 0;
				double tot_after_percentage = 0;
				
				double d_tot_cases_before = tot_cases_before;
				//double d_arr_cases_before = arr_cases_before;
				double d_arr_cases_before = zero_cases_before;
				
				double d_tot_cases_after = tot_cases_after;
				//double d_arr_cases_after = arr_cases_after;
				double d_arr_cases_after = zero_cases_after;
				
				
				if(d_tot_cases_before>0){
					tot_below_percentage = (d_arr_cases_before / d_tot_cases_before)*100;
				}
				else{
					tot_below_percentage = 0;
				}
				
				if(d_tot_cases_after>0){
					tot_after_percentage = (d_arr_cases_after / d_tot_cases_after)*100;
				}
				else{
					tot_after_percentage = 0;
				}
				
				
				String m_below_colour_tot = "";
				String m_after_colour_tot = "";
				
				// added by udara 09-11-2018
							if(tot_below_percentage<=10.49){
								m_below_colour_tot = "Chartreuse";
							}
							else if(tot_below_percentage>10.49 && tot_below_percentage<=25.49){
								m_below_colour_tot = "yellow";	
							}
							else if(tot_below_percentage>25.49){
								m_below_colour_tot = "red";	
							}
							
							if(tot_after_percentage<=10.49){
								m_after_colour_tot = "Chartreuse";
							}
							else if(tot_after_percentage>10.49 && tot_after_percentage<=25.49){
								m_after_colour_tot = "yellow";	
							}
							else if(tot_after_percentage>25.49){
								m_after_colour_tot = "red";	
							}
							// end by udara 09-11-2018
				
				
				out.println("<tr>");
				//out.println("<td width=\"2%\"   align='right' > &nbsp; </td>"); 
				out.println("<td width=\"10%\"  align='left'  bgcolor='lightgray' > &nbsp; </td>"); 
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+tot_cases_before+" </b></td>"); // No. of total case
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+zero_cases_before+" </b></td>"); // Zero payment case
				out.println("<td width=\"5%\"   align='right' bgcolor='"+m_below_colour_tot+"' ><b> "+nf1.format(tot_below_percentage)+" </b></td>"); // %
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+tot_cases_after+" </b></td>"); // No. of total case
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+zero_cases_after+" </b></td>"); // Zero payment case
				out.println("<td width=\"5%\"   align='right' bgcolor='"+m_after_colour_tot+"' ><b> "+nf1.format(tot_after_percentage)+" </b></td>"); // %
				out.println("<td width=\"10%\"  align='right' bgcolor='lightgray' ><b> "+total_zero_cases+" </b></td>");  
				out.println("</tr>");
						
						
				out.println("</table>");
				
				out.println("</td>"); // udara 28-05-2019
				out.println("</tr>"); // udara 28-05-2019
				out.println("</table>"); // udara 28-05-2019
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
				
			}
			
			
			else if(m_chksql.equals("print_report_system_report_zero_below_total")){	
				
				String m_branch="";
				String m_perform_status = "";
				String m_region = "";
				String m_date="";
				String m_active_status_string = "";
				String m_location="";
				String m_officer="";
				//String cr_officer = "";
				String m_active_status = "";
				String m_report_type = "";
				
				
				if(req.getParameter("report_type")!=null ){
					m_report_type=req.getParameter("report_type").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}

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
				
				String sql_m_region = "  ";
				if(!(m_region.equals("NOT_SELECT"))){ 
					sql_m_region = " AND REGION_CODE = '"+m_region+"' ";    
				}

				
				String sql_m_location = "   ";
				if((!m_location.equals("")) && (!m_location.equals(""))){
					sql_m_location = " AND   LOCATION_CODE = '"+m_location+"' ";
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

				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Analysis of Arrears</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				

				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Zero Payment Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				String m_cur_date = "";
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}
				
				
				String Sql_data="";
				boolean more;
				
				if(!m_date.equals(m_cur_date)){
				
							if(m_report_type.equals("TOTAL_CASE_BEFORE")){
								
								Sql_data="  "+					
								" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
											              " WHERE YEAR_STATUS = 'BELOW' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
									" ";
								
							}
							
							else if(m_report_type.equals("ZERO_CASE_BEFORE")){
								
								Sql_data="  "+	
							" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
											              " WHERE YEAR_STATUS = 'BELOW' "+
											              " AND ZERO_TYPE = 'ZERO' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
								
							else if(m_report_type.equals("ARR_CASE_BEFORE")){
								
								Sql_data="  "+	
							" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
											              " WHERE YEAR_STATUS = 'BELOW' "+
											              " AND TOTAL_AMOUNT > 0 "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							
							else if(m_report_type.equals("TOTAL_CASE_AFTER")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
											              " WHERE YEAR_STATUS = 'ABOVE' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							
							else if(m_report_type.equals("ZERO_CASE_AFTER")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
											              " WHERE YEAR_STATUS = 'ABOVE' "+
											              " AND ZERO_TYPE = 'ZERO' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							
							else if(m_report_type.equals("ARR_CASE_AFTER")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
											              " WHERE YEAR_STATUS = 'ABOVE' "+
											              " AND TOTAL_AMOUNT > 0 "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							else if(m_report_type.equals("LAST_MONTH_ZERO")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAY_HIS_N "+
											              //" WHERE YEAR_STATUS = 'ABOVE' "+
											              //" AND TOTAL_AMOUNT > 0 "+
											              " WHERE REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ZERO_TYPE = 'ZERO' "+ 
															" AND LAST_MONTH_ZERO = 'Y' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							
							
				}
				else{
					
					
					
					if(m_report_type.equals("TOTAL_CASE_BEFORE")){
								
								Sql_data="  "+					
								" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
											              " WHERE YEAR_STATUS = 'BELOW' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ENT_USER = '"+m_username+"' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
									" ";
								
							}
							
							else if(m_report_type.equals("ZERO_CASE_BEFORE")){
								
								Sql_data="  "+	
							" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
											              " WHERE YEAR_STATUS = 'BELOW' "+
											              " AND ZERO_TYPE = 'ZERO' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ENT_USER = '"+m_username+"' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
								
							else if(m_report_type.equals("ARR_CASE_BEFORE")){
								
								Sql_data="  "+	
							" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
											              " WHERE YEAR_STATUS = 'BELOW' "+
											              " AND TOTAL_AMOUNT > 0 "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
															" AND ENT_USER = '"+m_username+"' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							
							else if(m_report_type.equals("TOTAL_CASE_AFTER")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
											              " WHERE YEAR_STATUS = 'ABOVE' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ENT_USER = '"+m_username+"' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							
							else if(m_report_type.equals("ZERO_CASE_AFTER")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
											              " WHERE YEAR_STATUS = 'ABOVE' "+
											              " AND ZERO_TYPE = 'ZERO' "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ENT_USER = '"+m_username+"' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							
							else if(m_report_type.equals("ARR_CASE_AFTER")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
											              " WHERE YEAR_STATUS = 'ABOVE' "+
											              " AND TOTAL_AMOUNT > 0 "+
											              " AND REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ZERO_TYPE = 'ZERO' "+ // udara 09-05-2018
															" AND ENT_USER = '"+m_username+"' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
							else if(m_report_type.equals("LAST_MONTH_ZERO")){
								
								Sql_data="  "+	
									" SELECT FINANCE_NO, "+
										" TOTAL_AMOUNT "+
											              " FROM "+m_schema_name+".AF_TBL_SYS_REP_ZERO_PAYMENT_N "+
											              //" WHERE YEAR_STATUS = 'ABOVE' "+
											              //" AND TOTAL_AMOUNT > 0 "+
											              " WHERE REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND ZERO_TYPE = 'ZERO' "+ 
															" AND ENT_USER = '"+m_username+"' "+
															" AND LAST_MONTH_ZERO = 'Y' "+
															"  "+sql_m_officer+" "+
															"  "+sql_m_location+" "+
															"  "+m_active_status_string+" "+
															"  "+sql_m_region+" "+ 
															"  "+sql_perform_status+"  "+
															" ";
								
							}
					
					
					
				}
				
				
				//out.println(Sql_data);
				
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
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr><td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No.</B></td>");
				out.println("<td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Arrears Amount</B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  >"+rs.getString(1)+"</td>"); 
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

