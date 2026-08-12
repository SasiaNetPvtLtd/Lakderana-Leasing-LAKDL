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


public class LAKDL_AF_MISF_Termi_profit_loss_report_2 extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn= null;
	java.text.NumberFormat nf= null,nf1= null;
	java.lang.Math a= null;
	Statement stmt= null,stmt2= null;
	CallableStatement callstmt1 =null;
	public ResultSet rs= null,rs1= null,rs2= null;
	
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
			
			String m_sort_column   = "FINANCE_NO_SORT";	
			String m_order_by_type = "ASC";
			
			if(m_chksql.equals("run_report")){ 
				
				String m_to_date=req.getParameter("to_date");
				String m_from_date=req.getParameter("from_date");
				String m_location_id=req.getParameter("location_id");
				
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_RENTAL_SUMMARY_2(:1,:2,:3,:4);END;");  // AF_RPT_RENTAL_SUMMARY
					
					//callstmt1   = conn.prepareCall("BEGIN "+m_schema_name+".AF_TERMI_PROF_LOSS_RPT(:1,:2,:3,:4);END;"); // commented by udara 23-03-2018  //AF_TERMINATION_PRFIT_LOSS_RPT  Procedure changed by Kanchana on 2016-05-31
					callstmt1   = conn.prepareCall("BEGIN "+m_schema_name+".AF_TERMI_PROF_LOSS_RPT_AUTO(:1,:2,:3,:4);END;"); // added by udara 23-03-2018 // AF_TERMI_PROF_LOSS_RPT_N3

					//callstmt1   = conn.prepareCall("BEGIN "+m_schema_name+".AF_NEW_PROFIT_LOSS_REPORT(:1,:2,:3,:4);END;");   
					callstmt1.setString(1,m_to_date);
					callstmt1.setString(2,m_from_date);
					callstmt1.setString(3,m_location_id);
					callstmt1.setString(4,m_username);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'),TO_CHAR(LAST_DAY(SYSDATE),'DD')  FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Termination Profit And Loss Report</TITLE>"); 
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
				out.println("   DIV_TXT_LOCATION_CODE.style.color = 'black'; ");
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"  && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Termi_profit_loss_report_2?chksql=run_report&to_date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date;"); 
				//out.println("		window.open(m_url)");
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}else {");
				out.println("   DIV_TXT_LOCATION_CODE.style.color = 'red'; ");
				out.println("   alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("   }");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				//out.println("			print_report2();"); 
				out.println("			alert('Report is generated. Use View Report button to get the view.');"); // added by udara 30-11-2018
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("   DIV_TXT_LOCATION_CODE.style.color = 'black'; ");
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"  && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("		m_status=document.Form1.TXT_STATUS.value;");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Termi_profit_loss_report_2?chksql=print_report_new&location=\"+m_location+\"&date=\"+m_date+\"&from_date=\"+m_from_date+\"&status=\"+m_status;");	 // mod by udara on 18-06-2013
				out.println("			window.open(m_url);");
				out.println("	}else {");
				out.println("   DIV_TXT_LOCATION_CODE.style.color = 'red'; ");
				out.println("   alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("   }");
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				
				out.println(" 			if(data_vec.length==0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("     				help_update(data_vec);");
				out.println("			}");
				out.println("			else if(data_vec.length>0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("					document.Form1.TXT_LOCATION_CODE.value=data_vec[0]");
				out.println("			}");
				out.println("}");
				
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Runn_Case_Details?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Termi_profit_loss_report_2?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Termi_profit_loss_report_2?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Termination Profit And Loss Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Termination Profit And Loss Report - \"+document.Form1.hid_status.value;"); 
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
				/*
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
				*/
				
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				/*
				out.println("function help_button_user() {"); 
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("}"); 
				*/
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_USER.value='';");
				out.println("		}"); 
				out.println("}");
				
				
				out.println("function print_report(date,m_location,m_officer) {");
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Runn_Case_Details?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				
				// added by udara on 18-06-2013
				out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				//out.println("     document.Form1.DUE_DAY.value=v_date;");
				//out.println("     document.Form1.DUE_MONTH.value=v_month;");
				//out.println("     document.Form1.DUE_YEAR.value=val;");
				//out.println("     document.Form1.hid_date.value=document.Form1.DUE_DAY.value+'-'+document.Form1.DUE_MONTH.value+'-'+document.Form1.DUE_YEAR.value;");			
				out.println("  }");	
				// end by udara on 18-06-2013
				
				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					//out.println("document.Form1.VAL_DAY.value='"+rs2.getString(4)+"';");
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					
					out.println("document.Form1.TXT_FROM_DATE_DD.value='01';");
					out.println("document.Form1.TXT_FROM_DATE_MM.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.TXT_FROM_DATE_YY.value='"+rs2.getString(3)+"';");
					
					//out.println("document.Form1.DUE_DAY.value='"+rs2.getString(1)+"';");  TXT_FROM_DATE_DD
					//out.println("document.Form1.DUE_MONTH.value='"+rs2.getString(2)+"';");
					//out.println("document.Form1.DUE_YEAR.value='"+rs2.getString(3)+"';");
					
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Termination Profit And Loss Report</td>"); 
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
				
				/*
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				out.println("</table>"); 
				*/
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='15%' > Status </td>"); 
				out.println("<td width='40%' ><select class='txt_input' name='TXT_STATUS'>"); 
				out.println("<option value='ALL'   > All </option>");
				out.println("<option value='NORM_TERMI'   > Maturty </option>");
				out.println("<option value='EARLY_TERMI' > Pre - Maturity </option>");
				out.println("<option value='SEIZE_SOLD' > Seize and Sold </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
				out.println("<TD WIDTH=\"40%\"><input class=\"txt_input5\"  name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" onchange=check_Date(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY) >");
				out.println("<input class=\"txt_input5\"  name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" onchange=check_Date(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY) >");
				out.println("<input class=\"txt_input5\"  name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  onchange=check_Date(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY) >");	
				out.println("</td> ");
				out.println("<td width='*%'></td>");  
				
				out.println("</tr >"); 
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
				out.println("<td width='40%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\"  class=\"txt_input5\" size=\"2\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) > ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" class=\"txt_input5\" size=\"2\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) > ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" class=\"txt_input5\" size=\"4\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) > ");
				out.println(" &nbsp;&nbsp;&nbsp;<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println(" &nbsp;<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</td> ");
				out.println("<td width='*%'></td>"); 
				// added by prabash on09-05-2012----**
				out.println("</tr >");
				
				//----------------------------------**
				
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
				//out.println("dssfsdfsdfdsf");
				String m_date="";
				String m_from_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				double total_arrears=0.0;
				double total_trnasport=0.0;
				double total_close_amt=0.0;
				double total_cap_amt=0.0;
				double total_interest_cap=0.0;
				double total_fin_cost=0.0;
				double total_app_close_amt=0.0;				
				double total_collection_amt=0.0;
				double total_nor_los_amt=0.0;
				double total_capital_amt=0.0;
				double total_ac_loss_amt=0.0;
				
				
				String m_due_date=""; // added by udara on 18-06-2013
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				stmt = conn.createStatement ();				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{m_sort_column = "FINANCE_NO";	 m_order_by_type = "ASC";}
				
				String m_status = "";
				String m_status_sql = "";
				if(req.getParameter("status")!=null ){
					m_status=req.getParameter("status").trim();
				}
				
				if(m_status.equals("ALL"))
					m_status_sql = " ";
				else if(m_status.equals("NORM_TERMI"))
					m_status_sql = " WHERE STATUS = 'Normal Termination' ";
				else if(m_status.equals("EARLY_TERMI"))
					m_status_sql = " WHERE STATUS = 'Early Termination' ";
				else if(m_status.equals("SEIZE_SOLD"))
					m_status_sql = " WHERE STATUS = 'Cease and Sold' ";
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Termination Profit And Loss Report</TITLE>"); 
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
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'),  "+
					" TO_CHAR((LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1),'DD-MM-YYYY') , "+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					//m_officer_name=rs.getString(2);
					m_start_date=rs.getString(2);
					m_end_date=rs.getString(3);
				}
				
				String Sql_data="";
				
				
				
				
				// added by udara 05-07-2018
				Sql_data="	"+
				" SELECT CONTRACT_NO, "+
					" NAME, "+
					" VEHICLE_NO, "+
					" STATUS, "+
					" REMARKS, "+
					" CAPITAL, "+
					" RENTAL, "+
					" PERIOD, "+
					" BALANCE, "+//9
					" DUE, "+//10
					" ARREAS, "+//11
					" TRANSPORT_AMT, "+//12
					" CLOSING_AMT, "+//13
					" BALANCE_CAPITAL, "+//14
					" INTREST_CAPITAL, "+//15
					" FINANCE_COST, "+//16
					" APPRO_CLOSE_AMT, "+//17
					" COLLECTED_DUE,"+//18
					" NORMAL_LOS_PROF, "+//19
					" CAP_LOS_PROF, "+//20
					" ACCONTING_LOSS_PROF, "+//21
					" ENT_USER, "+
					" MORATORIUM_STATUS, "+ // 23 added by udara 13-08-2020
					" REBATE_REF "+ // 24 added by udara 08-03-2021
							" FROM ( "+
								" SELECT CONTRACT_NO, "+//1
									" NAME, "+//2
									" NVL(VEHICLE_NO,'-') VEHICLE_NO, "+//3
									" DECODE(YARD_STATUS,'Y',DECODE(STATUS,'ERL_TER','Early Termination','NOR_TER','Normal Termination','PAR_TER','Partial Termination','RESCHEDULE','Reschedule','RENT_REVIS','Rental Revision','BALANCE_RE','Balance Receivable','ASSET_REPL','Asset Replacement','ENHA_DOWN','Enhancement And Downward Revision','BAL_TRANSF','Balance Transfer'),'Cease and Sold') STATUS,      "+ 
									" REMARKS, "+//5
									" CAPITAL,  "+//6
									" RENTAL, "+//7
									" PERIOD, "+//8
									" BALANCE, "+//9
									" DUE, "+//10
									" ARREAS, "+//11
									" TRANSPORT_AMT, "+//12
									" CLOSING_AMT, "+//13
									" BALANCE_CAPITAL, "+//14
									" INTREST_CAPITAL, "+//15
									" FINANCE_COST, "+//16
									" APPRO_CLOSE_AMT, "+//17
									" COLLECTED_DUE,"+//18
									" NORMAL_LOS_PROF, "+//19
									" CAP_LOS_PROF, "+//20
									" ACCONTING_LOSS_PROF, "+//21
									" ENT_USER, "+
									" NVL("+m_schema_name+".AF_CO_GET_MORA_FLAG_STATUS(CONTRACT_NO),'-') MORATORIUM_STATUS, "+ // added by udara 13-08-2020
									" NVL("+m_schema_name+".AF_CO_GET_TERMI_REBATE_REF(CONTRACT_NO),'-') REBATE_REF "+ // added by udara 08-03-2021		
									" FROM "+m_schema_name+".AF_TERMIPROFIT_LOSS_AUTO_RPT "+//  AF_NEW_PROFIT_LOSS_DET
											" WHERE ENT_USER = '"+m_username+"' "+
											//" AND   TO_DATE(RPT_GENRATE_DATE,'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
											//" AND   TO_DATE(RPT_GENRATE_DATE,'DD-MM-YYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
											" AND   TO_DATE(TERMI_DATE,'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
											" AND   TO_DATE(TERMI_DATE,'DD-MM-YYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
											" AND   LOCATION LIKE '"+m_location+"'||'%' "+
													" GROUP BY CONTRACT_NO,NAME,VEHICLE_NO,STATUS,REMARKS,ENT_USER,CAPITAL,  "+
													" RENTAL,PERIOD,BALANCE,DUE,ARREAS,TRANSPORT_AMT,CLOSING_AMT,BALANCE_CAPITAL, "+
													" INTREST_CAPITAL,FINANCE_COST,APPRO_CLOSE_AMT,COLLECTED_DUE,NORMAL_LOS_PROF, "+
													" CAP_LOS_PROF,ACCONTING_LOSS_PROF,YARD_STATUS "+
									" ) "+
									" "+m_status_sql+" "+
					"  ";
				// end by udara 05-07-2018
				//out.println(Sql_data);
				rs=stmt.executeQuery(Sql_data);
				//	out.println(" --"+m_date+"--"+m_location+"--"+m_officer+"--"+Sql_data+"");
				more=rs.next();
				int count=1;				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>LAKDERANA INVESTMENTS LTD - "+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Termination Profit And Loss Report from "+m_from_date+" to "+m_date+"</u></td>");   // from 01-01-2012 to 10-07-2013 
				out.println("</tr >");
				//out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				//out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				int j=1;
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");				
				
				
				
				// Row 1
				// ===========================================================================================================
				out.println("<tr bgcolor=\"#CCCCCC\" height=30px  >");
				
				out.println("<td STYLE='{text-align:center; }' ><b>No</b></td>"); 
				
				out.println("<td STYLE='{text-align:center;  }' ><b>Contract No</b></td>"); 	
				out.println("<td STYLE='{text-align:center;  }' ><b>Name</b></td>"); 	
				out.println("<td STYLE='{text-align:center;  }' ><b>Vehicle No</b></td>");
				
				out.println("<td STYLE='{text-align:center;}' ><b>Status</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Remarks</b></td>"); 
				
				out.println("<td STYLE='{text-align:center;}' ><b>Capital A</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Rental</b></td>");
				
				out.println("<td STYLE='{text-align:center; }' colspan=3 ><b>Duration</b></td>"); 
				
				out.println("<td STYLE='{text-align:center; }' ><b>Arrears B</b></td>"); 	
				out.println("<td STYLE='{text-align:center; }' ><b>Ceasing ,BR,Cash refund & Transport Amount C</b></td>"); 
				out.println("<td STYLE='{text-align:center; }' ><b>Closing Amount With Rebate D</b></td>");
				
				out.println("<td STYLE='{text-align:center; }' ><b>Balance Capital X</b></td>");
				
				out.println("<td STYLE='{text-align:center; }' ><b>Interest For Capital E</b></td>");
				out.println("<td STYLE='{text-align:center; }' ><b>Finance Cost F</b></td>");
				
				out.println("<td STYLE='{text-align:center; }' ><b>Approved Closing Amount G</b></td>");
				out.println("<td STYLE='{text-align:center; }' ><b>Collected from Due Periods H</b></td>");
				
				out.println("<td STYLE='{text-align:center; }' ><b>Normal Loss / Profit (G-D)</b></td>");
				out.println("<td STYLE='{text-align:center; }' ><b>Capital Lost/Profit (G+H)-(A+C+E+F)</b></td>");
				out.println("<td STYLE='{text-align:center; }' ><b>Accounting Loss/Profit (G-(B+X))</b></td>");
				
				out.println("<td STYLE='{text-align:center; }' ><b> Moratorium </b></td>"); // added by udara 13-08-2020
				out.println("<td STYLE='{text-align:center; }' ><b> Rebate Ref </b></td>"); // added by udara 08-03-2021
				
				out.println("</tr>");
				// ===========================================================================================================
				
				// Row 2
				// ===========================================================================================================
				out.println("<tr bgcolor=\"#CCCCCC\" height=30px  >");
				
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>"); 
				
				out.println("<td STYLE='{text-align:center;  }' > &nbsp; </td>"); 	
				out.println("<td STYLE='{text-align:center;  }' > &nbsp; </td>"); 	
				out.println("<td STYLE='{text-align:center;  }' > &nbsp; </td>");
				
				out.println("<td STYLE='{text-align:center;}' > &nbsp; </td>"); 	
				out.println("<td STYLE='{text-align:center;}' > &nbsp; </td>"); 
				
				out.println("<td STYLE='{text-align:center;}' > &nbsp; </td>"); 	
				out.println("<td STYLE='{text-align:center;}' > &nbsp; </td>");
				
				out.println("<td STYLE='{text-align:center; }' > <b>Period</b></td>"); 
				out.println("<td STYLE='{text-align:center; }' ><b>Balance</b></td>"); 
				out.println("<td STYLE='{text-align:center; }' ><b>Due</b></td>"); 
				
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>"); 	
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>"); 
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>");
				
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>"); // added by udara 13-08-2020
				out.println("<td STYLE='{text-align:center; }' > &nbsp; </td>"); // added by udara 08-03-2021
				
				out.println("</tr>");
				
				while(more){					
					/*if (j % 2 == 1) {
						out.println("<tr class = \"tr_input\">");
					}
					else {
						out.println("<tr class = \"tr_input1\">");
					}*/
					out.println("<tr bgcolor=\"#FCEBC5\">");
					out.println("<td width=\"5%\" STYLE='{text-align:left;}' > "+count+" </td>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(1)+" </td>"); 	
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(2)+" </td>"); 
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(3)+" </td>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(4)+" </td>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(5)+" </td>");
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(6))+" </td>"); // Capital A
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(7))+" </td>");
					out.println("<td STYLE='text-align:right;' > "+nf1.format(rs.getDouble(8))+" </td>");
					out.println("<td STYLE='text-align:right;' > "+nf1.format(rs.getDouble(9))+" </td>");
					out.println("<td STYLE='text-align:right;' > "+nf1.format(rs.getDouble(10))+" </td>");
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(11))+" </td>"); // Arrears B
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(12))+" </td>"); // Ceasing ,BR,Cash refund & Transport Amount C
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(13))+" </td>"); // Closing Amount With Rebate D
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(14))+" </td>"); // Balance Capital X
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(15))+" </td>"); // Interest For Capital E
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(16))+" </td>"); // Finance Cost F
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(17))+" </td>"); // Approved Closing Amount G
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(18))+" </td>"); // Collected from Due Periods H

					//out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(19))+" </td>"); // Normal Loss / Profit (G-D)
					out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(17)-rs.getDouble(13))+" </td>"); // Normal Loss / Profit (G-D)
					
					//out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(20))+" </td>"); // Capital Lost/Profit (G+H)-(A+C+E+F)
					out.println("<td STYLE='text-align:right;' > "+nf.format(((rs.getDouble(17)+rs.getDouble(18))-(rs.getDouble(6)+rs.getDouble(12)+rs.getDouble(15)+rs.getDouble(16))))+" </td>"); // Capital Lost/Profit (G+H)-(A+C+E+F)

					//out.println("<td STYLE='text-align:right;' > "+nf.format(rs.getDouble(21))+" </td>"); // Accounting Loss/Profit (G-(B+X))
					out.println("<td STYLE='text-align:right;' > "+nf.format((rs.getDouble(17)-(rs.getDouble(11)+rs.getDouble(14))))+" </td>"); // Accounting Loss/Profit (G-(B+X))
					
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(23)+" </td>"); // added by udara 13-08-2020
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(24)+" </td>"); // added by udara 08-03-2021
					
					out.println("</tr>");
					
					total_arrears    +=rs.getDouble(11);
					total_trnasport  +=rs.getDouble(12);
					total_close_amt  +=rs.getDouble(13);
					total_cap_amt    +=rs.getDouble(14);
					total_interest_cap    +=rs.getDouble(15);
					total_fin_cost +=rs.getDouble(16);
					total_app_close_amt    +=rs.getDouble(17);
					total_collection_amt    +=rs.getDouble(18);
					
					//total_nor_los_amt    +=rs.getDouble(19);
					total_nor_los_amt    += (rs.getDouble(17)-rs.getDouble(13));
					
					//total_capital_amt    +=rs.getDouble(20);
					total_capital_amt    += ((rs.getDouble(17)+rs.getDouble(18))-(rs.getDouble(6)+rs.getDouble(12)+rs.getDouble(15)+rs.getDouble(16)));
					
					//total_ac_loss_amt    +=rs.getDouble(21); 
					total_ac_loss_amt    += (rs.getDouble(17)-(rs.getDouble(11)+rs.getDouble(14))); 
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				out.println("<tr BGCOLOR=\"white\" height=30px  >");
				out.println("<td width=\"5%\" STYLE='{text-align:left;}' >  </td>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' >  </td>"); 	
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' >  </td>"); 
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' ></td>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' ><b>TOTALL</b></td>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > </td>");
					out.println("<td STYLE='text-align:right;' >  </td>");
					out.println("<td STYLE='text-align:right;' >  </td>");
					out.println("<td STYLE='text-align:right;' >  </td>");
					out.println("<td STYLE='text-align:right;' >  </td>");
					out.println("<td STYLE='text-align:right;' ></td>");
					out.println("<td STYLE='text-align:right;' ><b>"+nf.format(total_arrears)+"</b></td>");
					out.println("<td STYLE='text-align:right;' ><b>"+nf.format(total_trnasport)+"</b></td>");
					out.println("<td STYLE='text-align:right;' ><b>"+nf.format(total_close_amt)+"</b></td>");
					out.println("<td STYLE='text-align:right;' ><b>"+nf.format(total_cap_amt)+"</b></td>");					
					out.println("<td STYLE='text-align:right;' ><b> "+nf.format(total_interest_cap)+"</b> </td>");
					out.println("<td STYLE='text-align:right;' ><b> "+nf.format(total_fin_cost)+" </b></td>");
					out.println("<td STYLE='text-align:right;' ><b> "+nf.format(total_app_close_amt)+"</b> </td>");
					out.println("<td STYLE='text-align:right;' ><b> "+nf.format(total_collection_amt)+"</b></td>");
					out.println("<td STYLE='text-align:right;' ><b> "+nf.format(total_nor_los_amt)+" </b></td>");
					out.println("<td STYLE='text-align:right;' ><b> "+nf.format(total_capital_amt)+" </b></td>");
					out.println("<td STYLE='text-align:right;' ><b>"+nf.format(total_ac_loss_amt)+"  </b></td>");
					out.println("<td STYLE='text-align:right;' ></td>"); // added by udara 13-08-2020
					out.println("<td STYLE='text-align:right;' ></td>"); // added by udara 08-03-2021
				out.println("</tr>");
				
				
				// ===========================================================================================================
				
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
			
			
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
			
			
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
