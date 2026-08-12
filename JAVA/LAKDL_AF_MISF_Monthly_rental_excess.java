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


public class LAKDL_AF_MISF_Monthly_rental_excess extends javax.servlet.http.HttpServlet { 
	/*
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2,stmt3;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2,rs3;
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		
		ServletOutputStream out = null;
		Connection conn= null;
		java.text.NumberFormat nf= null,nf1= null;
		java.lang.Math a;
		Statement stmt= null,stmt2= null,stmt3= null;
		CallableStatement callstmt1 =null;
		ResultSet rs= null,rs1= null,rs2= null,rs3= null;
		
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
				//String m_user_id=req.getParameter("user_id");
				
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_RENT_EXCES_MONTH(:1,:2,:3);END;");
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_RENT_EXCES_MONT_2(:1,:2,:3);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_username);
					
					
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR "+ex.toString()); 
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Rental and Excess</TITLE>"); 
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
				//out.println("finance_no = document.Form1.TXT_FINANCE.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=run_report&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value;"); 
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
				//	out.println("		m_officer=document.Form1.TXT_USER.value;");
				
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=print_report_new&location=\"+m_location+\"&date=\"+m_date;");	
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
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Rental and Excess - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Rental and Excess - \"+document.Form1.hid_status.value;"); 
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Rental and Excess </td>"); 
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
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				/*
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
				*/
				
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
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_mon="";
				String m_month="";
				String m_last_month="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}else{
					m_location="";
				}
				
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Rental and Excess</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Monthly_rental_excess?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val;");	
				
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Month'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Month') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_mon = rs1.getString(3);
					m_month =  rs1.getString(4);
					m_last_month =  rs1.getString(5);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"'),'ALL'), "+
					" TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH-YYYY') "+
					" FROM DUAL ");
				
				
				boolean more=rs.next();
				if(more){
					m_location_desc=rs.getString(1);
					m_start_date=rs.getString(2);
				}
				
				String Sql_data="";
				
				/*
				Sql_data="   SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE),'-'), SUM(A.DAY_DUE), SUM(A.PROGRESSIVE_DUE), SUM(A.MONTH_DUE), "+
					"          SUM(A.DAY_COLL), SUM(A.EXCESS_PAID), SUM(A.PROGRESSIVE_TO_DATE), "+
					"          SUM(A.TOTAL_BRANCH_COLL), A.NO_OF_CASES, A.NEW_CASES "+
					"     FROM "+m_schema_name+".AF_MISF_DAILY_PERFORM_INDICAT A "+
					"    WHERE A.ENT_USER ='"+m_username+"' AND A.ENT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
					" GROUP BY A.LOCATION_CODE,A.NO_OF_CASES, A.NEW_CASES "+
					" ORDER BY ("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE)) ";
					*/
				
				Sql_data="    SELECT NVL( "+m_schema_name+". AF_CO_GET_LOCATION_DESC ( A.LOCATION_CODE ),'-'), SUM ( A.AMOUNT_1 ), "+
					" SUM ( A.AMOUNT_2 ), SUM ( A.AMOUNT_3 ), SUM ( A.AMOUNT_4 ),"+
					" SUM ( A.AMOUNT_5 ), SUM ( A.AMOUNT_6 ), SUM ( A.AMOUNT_7 ),"+
					" SUM ( A.AMOUNT_8 ), SUM ( A.AMOUNT_9 ), SUM ( A.AMOUNT_10 ),"+
					" SUM ( A.AMOUNT_11 ), SUM ( A.AMOUNT_12 ), SUM ( A.AMOUNT_13 ),"+
					" SUM ( A.AMOUNT_14 ), SUM ( A.AMOUNT_15 ), SUM ( A.AMOUNT_16 ),"+
					" SUM ( A.AMOUNT_17 ), SUM ( A.AMOUNT_18 ), SUM ( A.AMOUNT_19 ),"+
					" SUM ( A.AMOUNT_20 ), SUM ( A.AMOUNT_21 ), SUM ( A.AMOUNT_22 ),"+
					" SUM ( A.AMOUNT_23 ), SUM ( A.AMOUNT_24 ), SUM ( A.AMOUNT_25 ),"+
					" SUM ( A.AMOUNT_26 ), SUM ( A.AMOUNT_27 ), SUM ( A.AMOUNT_28 ),"+
					" SUM ( A.AMOUNT_29 ), SUM ( A.AMOUNT_30 ), SUM ( A.AMOUNT_31 )"+
					" FROM   "+m_schema_name+".AF_MISF_RENTAL_EXCESS_MONTH A"+
					" WHERE   A.ENT_USER = '"+m_username+"'"+
					" AND A.ENT_DATE = TO_DATE ( '"+m_date+"', 'DD-MM-YYYY' ) AND A.LOCATION_CODE LIKE '%"+m_location+"%' "+
					" GROUP BY   A.LOCATION_CODE"+
					" ORDER BY   ( "+m_schema_name+".AF_CO_GET_LOCATION_DESC ( A.LOCATION_CODE ) ) "; 
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				
				
				BigDecimal total_1 = new BigDecimal(0.00);
				BigDecimal total_2 = new BigDecimal(0.00);
				BigDecimal total_3 = new BigDecimal(0.00);
				BigDecimal total_4 = new BigDecimal(0.00);
				BigDecimal total_5 = new BigDecimal(0.00);
				BigDecimal total_6 = new BigDecimal(0.00);
				BigDecimal total_7 = new BigDecimal(0.00);
				BigDecimal total_8 = new BigDecimal(0.00);
				BigDecimal total_9 = new BigDecimal(0.00);
				BigDecimal total_10 = new BigDecimal(0.00);
				BigDecimal total_11 = new BigDecimal(0.00);
				BigDecimal total_12 = new BigDecimal(0.00);
				BigDecimal total_13 = new BigDecimal(0.00);
				BigDecimal total_14 = new BigDecimal(0.00);
				BigDecimal total_15 = new BigDecimal(0.00);
				BigDecimal total_16 = new BigDecimal(0.00);
				BigDecimal total_17 = new BigDecimal(0.00);
				BigDecimal total_18 = new BigDecimal(0.00);
				BigDecimal total_19 = new BigDecimal(0.00);
				BigDecimal total_20 = new BigDecimal(0.00);
				BigDecimal total_21 = new BigDecimal(0.00);
				BigDecimal total_22 = new BigDecimal(0.00);
				BigDecimal total_23 = new BigDecimal(0.00);
				BigDecimal total_24 = new BigDecimal(0.00);
				BigDecimal total_25 = new BigDecimal(0.00);
				BigDecimal total_26 = new BigDecimal(0.00);
				BigDecimal total_27 = new BigDecimal(0.00);
				BigDecimal total_28 = new BigDecimal(0.00);
				BigDecimal total_29 = new BigDecimal(0.00);
				BigDecimal total_30 = new BigDecimal(0.00);
				BigDecimal total_31 = new BigDecimal(0.00);
				BigDecimal total_all = new BigDecimal(0.00);
				
				
				
				
				
				
				out.println("<br>");
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
					
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); 
					
					
					Sql_data=" SELECT DISTINCT	TO_CHAR(A.DATE_1,'DY'), TO_CHAR(A.DATE_2,'DY'), TO_CHAR(A.DATE_3,'DY'), "+
						" TO_CHAR(A.DATE_4,'DY'), TO_CHAR(A.DATE_5,'DY'), TO_CHAR(A.DATE_6,'DY'), TO_CHAR(A.DATE_7,'DY'),"+
						" TO_CHAR(A.DATE_8,'DY'), TO_CHAR(A.DATE_9,'DY'), TO_CHAR(A.DATE_10,'DY'), TO_CHAR(A.DATE_11,'DY'), TO_CHAR(A.DATE_12,'DY'), "+
						" TO_CHAR( A.DATE_13,'DY'),"+
						" TO_CHAR(	A.DATE_14,'DY'), TO_CHAR(A.DATE_15,'DY'),TO_CHAR( A.DATE_16,'DY'), TO_CHAR(A.DATE_17,'DY'), TO_CHAR(A.DATE_18,'DY'), TO_CHAR(A.DATE_19,'DY'),"+
						" TO_CHAR(	A.DATE_20,'DY'), TO_CHAR(A.DATE_21,'DY'), TO_CHAR(A.DATE_22,'DY'),TO_CHAR( A.DATE_23,'DY'), TO_CHAR(A.DATE_24,'DY'),TO_CHAR( A.DATE_25,'DY'),"+
						" TO_CHAR(	A.DATE_26,'DY'), TO_CHAR(A.DATE_27,'DY'), TO_CHAR(A.DATE_28,'DY'), A.DATE_29,TO_CHAR(A.DATE_29,'DY'), A.DATE_30,TO_CHAR(A.DATE_30,'DY'), A.DATE_31,TO_CHAR(A.DATE_31,'DY')"+
						" FROM	"+m_schema_name+".AF_MISF_RENTAL_EXCESS_MONTH A "+
						" WHERE   A.ENT_USER = '"+m_username+"' "+
						" AND A.ENT_DATE = TO_DATE ( '"+m_date+"', 'DD-MM-YYYY' )"; 
					
					
					rs3=stmt3.executeQuery(Sql_data);
					
					
					String day_29 = "";
					String day_30 = "";
					String day_31 = "";
					
					if(rs3.next()){		
						
						day_29 = rs3.getString(29);
						day_30 = rs3.getString(31);
						day_31 = rs3.getString(33);
						
						out.println("<tr>");
						if(m_location != null && !m_location.equals("")){
							if(rs3.getString(29) == null && rs3.getString(31) == null && rs3.getString(33) == null){
								out.println("<td width=\"100%\"  align='center' colspan='30' ><b>Rental and Excess - "+m_start_date+" - "+m_location_desc+"</b></td>"); 
							}
							if(rs3.getString(29) != null && rs3.getString(31) == null && rs3.getString(33) == null){
								out.println("<td width=\"100%\"  align='center' colspan='31' ><b>Rental and Excess - "+m_start_date+" - "+m_location_desc+"</b></td>"); 
							}
							if(rs3.getString(29) != null && rs3.getString(31) != null && rs3.getString(33) == null){
								out.println("<td width=\"100%\"  align='center' colspan='32' ><b>Rental and Excess - "+m_start_date+" - "+m_location_desc+"</b></td>"); 
							}
							if(rs3.getString(29) != null && rs3.getString(31) != null && rs3.getString(33) != null){
								out.println("<td width=\"100%\"  align='center' colspan='33' ><b>Rental and Excess - "+m_start_date+" - "+m_location_desc+"</b></td>"); 
							}
							
						}else{
							
							if(rs3.getString(29) == null && rs3.getString(31) == null && rs3.getString(33) == null){
								out.println("<td width=\"100%\"  align='center' colspan='30' ><b>Rental and Excess - "+m_start_date+"</b></td>"); 
							}
							if(rs3.getString(29) != null && rs3.getString(31) == null && rs3.getString(33) == null){
								out.println("<td width=\"100%\"  align='center' colspan='31' ><b>Rental and Excess - "+m_start_date+"</b></td>"); 
							}
							if(rs3.getString(29) != null && rs3.getString(31) != null && rs3.getString(33) == null){
								out.println("<td width=\"100%\"  align='center' colspan='32' ><b>Rental and Excess - "+m_start_date+"</b></td>"); 
							}
							if(rs3.getString(29) != null && rs3.getString(31) != null && rs3.getString(33) != null){
								out.println("<td width=\"100%\"  align='center' colspan='33' ><b>Rental and Excess - "+m_start_date+"</b></td>"); 
							}
							
							
						}
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Day</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(1)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(2)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(3)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(4)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(5)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(6)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(7)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(8)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(9)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(10)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(11)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(12)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(13)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(14)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(15)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(16)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(17)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(18)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(19)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(20)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(21)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(22)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(23)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(24)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(25)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(26)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(27)+"</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(28)+"</b></td>"); 
						if(rs3.getString(29) != null){
							out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(30)+"</b></td>"); 
						}
						if(rs3.getString(31) != null){
							out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(32)+"</b></td>"); 
						}
						if(rs3.getString(33) != null){
							out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+rs3.getString(34)+"</b></td>"); 
						}
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>&nbsp;</b></td>"); 
						out.println("</tr >");
						out.println("<tr>");
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Date</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>1</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>2</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>3</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>4</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>5</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>6</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>7</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>8</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>9</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>10</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>11</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>12</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>13</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>14</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>15</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>16</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>17</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>18</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>19</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>20</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>21</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>22</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>23</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>24</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>25</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>26</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>27</b></td>"); 
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>28</b></td>"); 
						if(rs3.getString(29) != null){
							out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>29</b></td>"); 
						}
						if(rs3.getString(31) != null){
							out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>30</b></td>"); 
						}
						if(rs3.getString(33) != null){
							out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>31</b></td>"); 
						}
						out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Sub Total</b></td>"); 
						
						out.println("</tr >");
					}
					
					
					
					
					int j=1;
					
					
					
					
					while(more){
						
						out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(1)+"</td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}' >"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(5))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(6))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(7))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}' >"+nf.format(rs.getDouble(8))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(9))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(10))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(11))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(12))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(13))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}' >"+nf.format(rs.getDouble(14))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(15))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(16))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(17))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(18))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(19))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}' >"+nf.format(rs.getDouble(20))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(21))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(22))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(23))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(24))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(25))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}' >"+nf.format(rs.getDouble(26))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(27))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(28))+"</td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(29))+"</td>");
						
						
						
						if(day_29 != null){
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(30))+"</td>");
						}
						
						if(day_30 != null){
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(31))+"</td>");
						}
						
						if(day_31 != null){
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getDouble(32))+"</td>");
						}
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><b>"+nf.format(rs.getBigDecimal(2).add(rs.getBigDecimal(3)).add(rs.getBigDecimal(4)).add(rs.getBigDecimal(5)).
							add(rs.getBigDecimal(6)).add(rs.getBigDecimal(7)).add(rs.getBigDecimal(8)).add(rs.getBigDecimal(9)).add(rs.getBigDecimal(10)).add(rs.getBigDecimal(11)).add(rs.getBigDecimal(12)).add(rs.getBigDecimal(13)).add(rs.getBigDecimal(14)).
							add(rs.getBigDecimal(15)).add(rs.getBigDecimal(16)).add(rs.getBigDecimal(17)).add(rs.getBigDecimal(18)).add(rs.getBigDecimal(19)).add(rs.getBigDecimal(20)).add(rs.getBigDecimal(21)).
							add(rs.getBigDecimal(22)).add(rs.getBigDecimal(23)).add(rs.getBigDecimal(24)).add(rs.getBigDecimal(25)).add(rs.getBigDecimal(26)).add(rs.getBigDecimal(27)).add(rs.getBigDecimal(28)).add(rs.getBigDecimal(29)).add(rs.getBigDecimal(30)).
							add(rs.getBigDecimal(31)).add(rs.getBigDecimal(32)))+"</b></td>");
						
						
						out.println("</tr >");
						
						total_1 = total_1.add(rs.getBigDecimal(2));
						total_2 = total_2.add(rs.getBigDecimal(3));
						total_3 = total_3.add(rs.getBigDecimal(4));
						total_4 = total_4.add(rs.getBigDecimal(5));
						total_5 = total_5.add(rs.getBigDecimal(6));
						total_6 = total_6.add(rs.getBigDecimal(7));
						total_7 = total_7.add(rs.getBigDecimal(8));
						total_8 = total_8.add(rs.getBigDecimal(9));
						total_9 = total_9.add(rs.getBigDecimal(10));
						total_10 = total_10.add(rs.getBigDecimal(11));
						total_11 = total_11.add(rs.getBigDecimal(12));
						total_12 = total_12.add(rs.getBigDecimal(13));
						total_13 = total_13.add(rs.getBigDecimal(14));
						total_14 = total_14.add(rs.getBigDecimal(15));
						total_15 = total_15.add(rs.getBigDecimal(16));
						total_16 = total_16.add(rs.getBigDecimal(17));
						total_17 = total_17.add(rs.getBigDecimal(18));
						total_18 = total_18.add(rs.getBigDecimal(19));
						total_19 = total_19.add(rs.getBigDecimal(20));
						total_20 = total_20.add(rs.getBigDecimal(21));
						total_21 = total_21.add(rs.getBigDecimal(22));
						total_22 = total_22.add(rs.getBigDecimal(23));
						total_23 = total_23.add(rs.getBigDecimal(24));
						total_24 = total_24.add(rs.getBigDecimal(25));
						total_25 = total_25.add(rs.getBigDecimal(26));
						total_26 = total_26.add(rs.getBigDecimal(27));
						total_27 = total_27.add(rs.getBigDecimal(28));
						total_28 = total_28.add(rs.getBigDecimal(29));
						
						if(day_29 != null){
							total_29 = total_29.add(rs.getBigDecimal(30));
						}
						
						if(day_30 != null){
							total_30 = total_30.add(rs.getBigDecimal(31));
						}
						
						if(day_31 != null){
							total_31 = total_31.add(rs.getBigDecimal(32));
						}
						
						total_all = total_all.add(rs.getBigDecimal(2).add(rs.getBigDecimal(3)).add(rs.getBigDecimal(4)).add(rs.getBigDecimal(5)).
							add(rs.getBigDecimal(6)).add(rs.getBigDecimal(7)).add(rs.getBigDecimal(8)).add(rs.getBigDecimal(9)).add(rs.getBigDecimal(10)).add(rs.getBigDecimal(11)).add(rs.getBigDecimal(12)).add(rs.getBigDecimal(13)).add(rs.getBigDecimal(14)).
							add(rs.getBigDecimal(15)).add(rs.getBigDecimal(16)).add(rs.getBigDecimal(17)).add(rs.getBigDecimal(18)).add(rs.getBigDecimal(19)).add(rs.getBigDecimal(20)).add(rs.getBigDecimal(21)).
							add(rs.getBigDecimal(22)).add(rs.getBigDecimal(23)).add(rs.getBigDecimal(24)).add(rs.getBigDecimal(25)).add(rs.getBigDecimal(26)).add(rs.getBigDecimal(27)).add(rs.getBigDecimal(28)).add(rs.getBigDecimal(29)).add(rs.getBigDecimal(30)).
							add(rs.getBigDecimal(31)).add(rs.getBigDecimal(32)));
						
						
						
						
						more=rs.next();
						count+=1;
						j+=1;
						
					}
					
					//total============================
					
					if(count>0){
						out.println("<tr>");
						out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;border:2px groove ;}'  ><b>Day Due Total</b></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}' ><b>"+nf.format(total_1)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_2)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_3)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_4)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_5)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_6)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_7)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_8)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_9)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_10)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_11)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_12)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_13)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_14)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_15)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_16)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_17)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_18)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_19)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_20)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_21)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_22)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_23)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_24)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_25)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_26)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_27)+"</b></td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_28)+"</b></td>");
						
						
						
						if(day_29 != null){
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_29)+"</b></td>");
						}
						
						if(day_30 != null){
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_30)+"</b></td>");
						}
						
						if(day_31 != null){
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_31)+"</b></td>");
						}
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;border:2px groove ;}'  ><b>"+nf.format(total_all)+"</b></td>");
						
						
						out.println("</tr>");	
						
						out.println("<tr>");	
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;border:2px groove ;}'  ><b>Total Rental Due</b></td>"); 
						if(rs3.getString(29) == null && rs3.getString(31) == null && rs3.getString(33) == null){
							out.println("<td  class=factoring-letter-body  colspan='29'  STYLE='{text-align:center;border:2px groove ;}'  ><b>"+nf.format(total_all)+"</b></td>");
							
						}
						if(rs3.getString(29) != null && rs3.getString(31) == null && rs3.getString(33) == null){
							out.println("<td  class=factoring-letter-body  colspan='30'  STYLE='{text-align:center;border:2px groove ;}'  ><b>"+nf.format(total_all)+"</b></td>");
							
						}
						if(rs3.getString(29) != null && rs3.getString(31) != null && rs3.getString(33) == null){
							out.println("<td  class=factoring-letter-body  colspan='31'   STYLE='{text-align:center;border:2px groove ;}'  ><b>"+nf.format(total_all)+"</b></td>");
							
						}
						if(rs3.getString(29) != null && rs3.getString(31) != null && rs3.getString(33) != null){
							out.println("<td  class=factoring-letter-body  colspan='32'  STYLE='{text-align:center;border:2px groove ;}'  ><b>"+nf.format(total_all)+"</b></td>");
							
						}
						
						
						out.println("</tr>");	
						
						
						
						
						//precentage=================================================================================================
						
					}
					
					
					
					
					
					
					
					
					out.println("</table>");
					out.println("<br/>");
					out.println("<br/>");
					
					
					
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td> "); 
					out.println("<br/>");
					out.println("<br/>");
					
					out.println("<table id=mytable align=\"left\" width=\"40%\" border=\"1\" class=\"table\"  cellspacing=0 > "); 
					out.println("<tr>");
					out.println("<td width=\"16%\"  align='center' rowspan='2' ><b>Branch</b></td>"); 
					out.println("<td width=\"52%\"  align='center' colspan='3' ><b>Excess</b></td>"); 
					out.println("<td width=\"16%\"  align='center' rowspan='2' ><b>Total</b></td>"); 
					out.println("<td width=\"16%\"  align='center' rowspan='2' ><b>No of Cases</b></td>"); 
					
					out.println("</tr>");
					
					out.println("<tr>");
					
					out.println("<td width=\"17%\"  align='center' ><b>1 to 10</b></td>"); 
					out.println("<td width=\"17%\"  align='center'  ><b>21 to 20</b></td>"); 
					out.println("<td width=\"17%\"  align='center'  ><b>21 to 25</b></td>"); 
					
					out.println("</tr>");
					
					
					
					
					Sql_data="   SELECT NVL( "+m_schema_name+". AF_CO_GET_LOCATION_DESC ( A.LOCATION_CODE ),'-'), "+
						"    SUM(a.excess_1_10), SUM(a.excess_11_20), SUM(a.excess_21_25),COUNT(*)  "+
						"    FROM "+m_schema_name+".af_misf_rental_excess_month a "+
						"   WHERE   A.ENT_USER = '"+m_username+"' "+
						"   AND A.ENT_DATE = TO_DATE ( '"+m_date+"', 'DD-MM-YYYY' ) AND  A.LOCATION_CODE LIKE '%"+m_location+"%' "+
						"   GROUP BY   A.LOCATION_CODE "+
						"   ORDER BY   ( "+m_schema_name+".AF_CO_GET_LOCATION_DESC ( A.LOCATION_CODE ) )"; 
					
					
					rs=stmt.executeQuery(Sql_data);
					
					
					
					
					BigDecimal total_day_rental_due = new BigDecimal(0.00);
					BigDecimal m_no_of_cases = new BigDecimal(0.00);
					BigDecimal tot_1_to_10_rental = new BigDecimal(0.00);
					BigDecimal tot_11_to_20_rental = new BigDecimal(0.00);
					BigDecimal tot_21_to_25_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						
						out.println("<tr>");
						out.println("<td width=\"16%\"  class=factoring-letter-body  align='left'  >"+rs.getString(1)+"</td>"); 
						out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' >"+nf.format(rs.getBigDecimal(2))+"</td>"); 
						out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' >"+nf.format(rs.getBigDecimal(3))+"</td>"); 
						out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' >"+nf.format(rs.getBigDecimal(2).add(rs.getBigDecimal(3)).add(rs.getBigDecimal(4)))+"</td>"); 
						out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' >"+nf1.format(rs.getBigDecimal(5))+"</td>"); 
						
						out.println("</tr>");
						
						total_day_rental_due = total_day_rental_due.add(rs.getBigDecimal(2).add(rs.getBigDecimal(3)).add(rs.getBigDecimal(4)));
						m_no_of_cases = m_no_of_cases.add(rs.getBigDecimal(5));
						tot_1_to_10_rental = tot_1_to_10_rental.add(rs.getBigDecimal(2));
						tot_11_to_20_rental = tot_11_to_20_rental.add(rs.getBigDecimal(3));
						tot_21_to_25_rental = tot_21_to_25_rental.add(rs.getBigDecimal(4));
						

					}
					
					
					out.println("<tr>");
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='center'  ><b>Total</b></td>"); 
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' ><b>"+nf.format(tot_1_to_10_rental)+"</b></td>"); 
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' ><b>"+nf.format(tot_11_to_20_rental)+"</b></td>"); 
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' ><b>"+nf.format(tot_21_to_25_rental)+"</b></td>"); 
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' ><b>"+nf.format(total_day_rental_due)+"</b></td>"); 
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' ><b>"+nf1.format(m_no_of_cases)+"</b></td>"); 
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='center'  ><b>&nbsp;</b></td>"); 
					out.println("<td width=\"64%\"  class=factoring-letter-body  colspan='4'  align='center' ><b>"+nf.format(total_day_rental_due)+"</b></td>");  
					out.println("<td width=\"16%\"  class=factoring-letter-body  align='right' ><b>&nbsp;</b></td>"); 
					out.println("</tr>");
					

					out.println("</table>");
					
					out.println("</td>");
					out.println("</tr>");
					
					
					
					
					out.println("</table>");
				}
				out.println("<br/>");
				out.println("<br/>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
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
