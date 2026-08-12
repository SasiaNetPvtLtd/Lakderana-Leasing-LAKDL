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


public class LAKDL_AF_MISF_Rental_Summary_Report extends javax.servlet.http.HttpServlet { 
	
	/*
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2;
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn = null; 
		java.text.NumberFormat nf = null; 
		java.text.NumberFormat nf1 = null; 
		java.lang.Math a = null;  
		Statement stmt2 = null;
		Statement stmt = null;
		CallableStatement callstmt1 = null;
		ResultSet rs2 = null;
		ResultSet rs1 = null;
		ResultSet rs = null; 
		
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
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_RENTAL_SUMMARY_2(:1,:2,:3,:4);END;");  // AF_RPT_RENTAL_SUMMARY
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
				out.println("<TITLE>Collection - Running Case Details</TITLE>"); 
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
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=run_report&to_date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date;"); 
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
				out.println("			print_report2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("   DIV_TXT_LOCATION_CODE.style.color = 'black'; ");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=print_report_new&location=\"+m_location+\"&date=\"+m_date+\"&from_date=\"+m_from_date;");	 // mod by udara on 18-06-2013
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
				
				/* out.println("function sort_data(m_sort_col) {");
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
	      out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Marketing_Officer_Performance_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				*/
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Rental Summary - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Rental Summary - \"+document.Form1.hid_status.value;"); 
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
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(4)+"';");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Rental Summary</td>"); 
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
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Rental Summary Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Runn_Case_Details?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Runn_Case_Details?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&due_date="+m_due_date+"\";");	
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
				
				out.println("function Rental_drill(val1,val2,val3){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=rental_drill&branch=\"+val1+\"&date=\"+val2+\"&report_type=\"+val3;");
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
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
				
				
				Sql_data=" SELECT "+
					" A.COL1, A.COL2, A.COL3, A.COL4, "+
					" A.COL5, A.COL6, A.COL7, A.COL8, A.COL9, A.COL10,A.BRANCH_CODE,A.M_ID,TO_CHAR(A.REPOTE_DATE,'DD-MM-YYYY') "+
					" ,A.NON_PER_RENT, A.NON_PER_CASES  "+
					" FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY A WHERE A.ENT_USER='"+m_username+"' AND A.BRANCH_CODE='"+m_location+"'  ORDER BY A.M_ID ASC ";
				
				
				
				rs=stmt.executeQuery(Sql_data);
				//	out.println(" --"+m_date+"--"+m_location+"--"+m_officer+"--"+Sql_data+"");
				more=rs.next();
				int count=0;
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>LAKDERANA INVESTMENTS LTD - "+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>SUMMARY  OF RENTALS FROM "+m_from_date+" TO "+m_date+"</u></td>");   // from 01-01-2012 to 10-07-2013 
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
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				//	out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td width=\"12%\" STYLE='{text-align:center;}' ><b>Month</b></td>"); 			
				out.println("<td STYLE='{text-align:center;}' ><b>Opening Rentals</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>In Addition</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Closed Rentals</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Matured Rentals</b></td>");
			
				out.println("<td STYLE='{text-align:center;}' ><b>Non Perform</b></td>"); // added by udara 19-02-2014	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); // added by udara 19-02-2014	
			
				out.println("<td STYLE='{text-align:center;}' ><b>Rentals Total</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				out.println("</tr >");
				
				
				//=================================================
				//}
				
				int j=1;
				
				
				while(more){
					
					/*
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					
					out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(1)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(3)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(8)+"</td>"); //added by Prabash on 16-07-2012
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(4)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(5)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(6))+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(7))+"</td>");
					*/
					out.println("<tr>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+rs.getString(1)+" </td>"); 			
					out.println("<td STYLE='text-align:right;' > "+nf1.format(rs.getDouble(2))+" </td>");  // cursor:hand; onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','OPENING_RENTALS');\"
					out.println("<td STYLE='text-align:right;' > "+rs.getString(3)+" </td>"); // cursor:hand; onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','OPENING_RENTALS');\"
					out.println("<td onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','IN_ADDITION');\"     STYLE='cursor:hand; text-align:right;' > "+nf1.format(rs.getDouble(4))+" </td>"); 
					out.println("<td onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','IN_ADDITION');\"     STYLE='cursor:hand; text-align:right;' > "+rs.getString(5)+" </td>"); 
					out.println("<td onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','CLOSED_RENTALS');\"  STYLE='cursor:hand; text-align:right;' > "+nf1.format(rs.getDouble(6))+" </td>"); 
					out.println("<td onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','CLOSED_RENTALS');\"  STYLE='cursor:hand; text-align:right;' > "+rs.getString(7)+" </td>"); 
					out.println("<td onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','MATURED_RENTALS');\" STYLE='cursor:hand; text-align:right;' > "+nf1.format(rs.getDouble(8))+" </td>"); 
					
					out.println("<td onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','NON_PER_RENTALS');\"  STYLE='cursor:hand; text-align:right;' > "+nf1.format(rs.getDouble(14))+" </td>"); // added by udara 19-02-2014
					out.println("<td onclick=\"Rental_drill('"+rs.getString(11)+"','"+rs.getString(13)+"','NON_PER_RENTALS');\"  STYLE='cursor:hand; text-align:right;' > "+rs.getString(15)+" </td>");  // added by udara 19-02-2014
					
					
					out.println("<td STYLE='{text-align:right;}' > "+nf1.format(rs.getDouble(9))+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+rs.getString(10)+" </td>"); 
					out.println("</tr>");
					
					
					//total_mon_rental+=rs.getDouble(6);
					//total_capital+=rs.getDouble(7);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				/*
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}' colspan=\"6\"   ><b>Total</td>"); 
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_mon_rental)+"</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_capital)+"</td>"); 
					
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				*/
				
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
			
			
			
			else if(m_chksql.equals("rental_drill")){		
				
				String m_branch="";
				String m_date="";
				String m_report_type="";
				
				
				if(req.getParameter("branch")!=null ){
					m_branch=req.getParameter("branch").trim();
				}
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				if(req.getParameter("report_type")!=null ){
					m_report_type=req.getParameter("report_type").trim();
				}
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Rentals Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Rentals Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_data="";
				boolean more;
				if (m_report_type.equals("OPENING_RENTALS")) {
					Sql_data=" SELECT A.FINANCE_NO , "+
						"  SUM(A.GRENTAL_AMOUNT)  "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						"  WHERE A.BRANCH_CODE = '"+m_branch+"'  "+
						"  AND TRUNC(A.RENTAL_DATE, 'MM') < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						"  AND A.ENT_USER='"+m_username+"'  "+
						"  GROUP BY A.FINANCE_NO ";
				}
				// commented by udara on 12-09-2013
				/*
				else if (m_report_type.equals("IN_ADDITION") ) {
					Sql_data=" SELECT A.FINANCE_NO , "+
						"  SUM(A.GRENTAL_AMOUNT)  "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						"  WHERE A.BRANCH_CODE = '"+m_branch+"' AND A.APPLICATION_STATUS = 'ACTIVATED' "+
						"  AND TRUNC(A.ACTIVATED_DATE, 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						"  AND TRUNC(A.RENTAL_DATE, 'MM') >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						"  AND A.ENT_USER='"+m_username+"'  "+
						"  GROUP BY A.FINANCE_NO "+
						"  ";
				}
				*/
				
				// added by udara on 12-09-2013
				else if (m_report_type.equals("IN_ADDITION") ) {
					Sql_data=" SELECT DISTINCT A.FINANCE_NO , "+
						"  A.GRENTAL_AMOUNT  "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						//"  WHERE A.BRANCH_CODE = '"+m_branch+"' AND A.APPLICATION_STATUS = 'ACTIVATED' "+ // commented by udara 29-11-2013
						"  WHERE A.BRANCH_CODE = '"+m_branch+"' AND A.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') "+ // added by udara 29-11-2013
						"  AND TRUNC(A.ACTIVATED_DATE, 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						//"  AND TRUNC(A.RENTAL_DATE, 'MM') >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						"  AND A.ENT_USER='"+m_username+"'  "+
						
						// added by udara 27-12-2013
						" UNION "+
						
						" SELECT DISTINCT A.FINANCE_NO , "+
						"  A.GRENTAL_AMOUNT  "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						//"  WHERE A.BRANCH_CODE = '"+m_branch+"' AND A.APPLICATION_STATUS = 'ACTIVATED' "+ // commented by udara 29-11-2013
						"  WHERE A.BRANCH_CODE = '"+m_branch+"' AND A.APPLICATION_STATUS IN  ('NORM_TERMI','TERMI', 'TERMINATED')  "+ // added by udara 29-11-2013
						"  AND TRUNC(A.ACTIVATED_DATE, 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						//"  AND TRUNC(A.RENTAL_DATE, 'MM') >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						
						" AND   A.FINANCE_NO IN ( "+
				            " SELECT FINANCE_NO "+
				            " FROM   AF_CR_PRO_TERMINATION "+
				            " WHERE  TERMINATION_VALIDITY_DATE >= TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0)), 'MM') "+
				            " AND    ACTIVE_STATUS = 'TERM_CHECK' "+
				          " )   "+
						
						"  AND A.ENT_USER='"+m_username+"'  "+
						// end by udara 27-12-2013
						
						"  ";
				}
				
				/*
				else if (m_report_type.equals("CLOSED_RENTALS") ) {
					Sql_data=" SELECT A.FINANCE_NO , "+
						"  SUM(A.GRENTAL_AMOUNT)  "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						"  WHERE A.BRANCH_CODE = '"+m_branch+"'  "+
						"  AND TRUNC(TO_DATE(AF_CO_GET_TERMINATED_DATE(A.FINANCE_NO),'DD-MM-YYYY'), 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						"  AND A.ENT_USER='"+m_username+"'  "+
						"  GROUP BY A.FINANCE_NO ";
				}
				*/
				
				else if (m_report_type.equals("CLOSED_RENTALS") ) {
					
				   // commented by udara 10-01-2014	
				   /*	
					Sql_data=" SELECT DISTINCT A.FINANCE_NO , "+
						"  A.GRENTAL_AMOUNT  "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						"  WHERE A.BRANCH_CODE = '"+m_branch+"'  "+
						"  AND TRUNC(TO_DATE(AF_CO_GET_TERMINATED_DATE(A.FINANCE_NO),'DD-MM-YYYY'), 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						//"  AND TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') >=  TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+ // added by udara 27-12-2013
						"  AND A.ENT_USER='"+m_username+"' " ;
					*/
					
					// added by udara 10-01-2014
					Sql_data=" "+
						" SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
					    " A.GRENTAL_AMOUNT RENTALS "+
					    " FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+ 
					    " WHERE A.BRANCH_CODE = '"+m_branch+"' "+ 
					    " AND   A.ENT_USER    = '"+m_username+"' "+ 
					    " AND   TRUNC(A.ACTIVATED_DATE, 'MM') <= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM') "+
					    " AND   TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATED_DATE(A.FINANCE_NO),'DD-MM-YYYY'), 'MM') = TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
					    " AND   TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') >=  TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM')  "+
						
						" UNION "+
						
						" SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
					    " A.GRENTAL_AMOUNT RENTALS "+
					    " FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+ 
					    " WHERE A.BRANCH_CODE = '"+m_branch+"' "+ 
					    " AND   A.ENT_USER    = '"+m_username+"' "+ 
					    " AND   TRUNC(A.ACTIVATED_DATE, 'MM') <= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM') "+
					    " AND   TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATED_DATE(A.FINANCE_NO),'DD-MM-YYYY'), 'MM') = TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
					    " AND   TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') <>  TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM')  "+
						
					" ";
					
					
				}
				
				/*
				else if (m_report_type.equals("MATURED_RENTALS") ) {
					Sql_data=" SELECT A.FINANCE_NO , "+
						"  SUM(A.GRENTAL_AMOUNT)  "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						"  WHERE A.BRANCH_CODE = '"+m_branch+"'  "+
						"  AND TRUNC(TO_DATE(AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						//"  AND TRUNC(A.RENTAL_DATE, 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						"  AND A.ENT_USER='"+m_username+"'  "+
						"  GROUP BY A.FINANCE_NO ";
				}
				*/
				
				else if (m_report_type.equals("MATURED_RENTALS") ) {
					
					// commented by udara 10-01-2014
					/*
					Sql_data=" SELECT DISTINCT A.FINANCE_NO , "+
						"  A.GRENTAL_AMOUNT "+
						"  FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
						"  WHERE A.BRANCH_CODE = '"+m_branch+"'  "+
						"  AND TRUNC(TO_DATE(AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						//"  AND TRUNC(A.RENTAL_DATE, 'MM') = TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'), 'MM')"+
						
						 " AND   A.FINANCE_NO IN ( "+
			                " SELECT FINANCE_NO "+
			                " FROM   AF_CR_PRO_TERMINATION "+
			                " WHERE  TERMINATION_VALIDITY_DATE >= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
			                " AND    ACTIVE_STATUS = 'TERM_CHECK' "+
          				" )  "+
						
						"  AND A.ENT_USER='"+m_username+"'  ";
					*/
					
					Sql_data=" "+
							" SELECT "+     
					      	" FINANCE_NO FINANCE_NO, SUM(GRENTAL_AMOUNT) RENTALS FROM( "+
					          " SELECT A.FINANCE_NO FINANCE_NO, A.GRENTAL_AMOUNT GRENTAL_AMOUNT "+
					          " FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
					          " WHERE A.BRANCH_CODE = '"+m_branch+"' "+
					          " AND A.ENT_USER = '"+m_username+"' "+ 
					          " AND TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') = TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
					          " AND A.APPLICATION_STATUS NOT IN ('NORM_TERMI','TERMI', 'TERMINATED')  "+
					          
					          " UNION "+
					          
					          " SELECT A.FINANCE_NO FINANCE_NO, A.GRENTAL_AMOUNT GRENTAL_AMOUNT "+
					          " FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A "+
					          " WHERE A.BRANCH_CODE = '"+m_branch+"' "+
					          " AND A.ENT_USER = '"+m_username+"'  "+
					          " AND TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') = TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
					          " AND A.APPLICATION_STATUS  IN ('NORM_TERMI','TERMI', 'TERMINATED') "+
					          " AND TRUNC(A.ACTIVATED_DATE, 'MM') <= TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0)), 'MM') "+
					              " AND   A.FINANCE_NO IN ( "+
					                " SELECT FINANCE_NO "+
					                " FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION "+
					                " WHERE  TERMINATION_VALIDITY_DATE >= TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0)), 'MM') "+
					                " AND    ACTIVE_STATUS = 'TERM_CHECK' "+
					          " ) "+
					          " AND TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATED_DATE(A.FINANCE_NO),'DD-MM-YYYY'), 'MM') <> TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
					      
					      " ) "+
					      " GROUP BY FINANCE_NO "+
						  " ";
					
					
				}
				
				
				// added by udara 19-02-2014
				
				else if (m_report_type.equals("NON_PER_RENTALS") ) {

					Sql_data=" "+
							" SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
					          " A.GRENTAL_AMOUNT RENTALS "+
					          " FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A, "+m_schema_name+".AF_CO_PERFORMING_CONTACTS B "+
					          " WHERE A.BRANCH_CODE = '"+m_branch+"' "+
					          " AND A.FINANCE_NO = B.FINANCE_NO "+
					          " AND A.ENT_USER = '"+m_username+"'  "+
					          " AND A.APPLICATION_STATUS    IN ('ACTIVATED','TERMI','REPOSSESS','LEGAL') "+
					          " AND TRUNC(A.ACTIVATED_DATE, 'MM') <= TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
					          " AND TRUNC(B.VALUE_DATE)  >= TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
					          " AND TRUNC(B.VALUE_DATE)  <= LAST_DAY(TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM')) "+
					          
					          " UNION "+
					          
					          " SELECT A.FINANCE_NO FINANCE_NO,  "+
					          " A.GRENTAL_AMOUNT GRENTAL_AMOUNT "+
					          " FROM "+m_schema_name+".AF_TBD_RPT_RENTAL_SUMMARY_MAIN A, "+m_schema_name+".AF_CO_PERFORMING_CONTACTS B "+
					          " WHERE A.BRANCH_CODE = '"+m_branch+"' "+
					          " AND A.FINANCE_NO = B.FINANCE_NO "+
					          " AND A.ENT_USER = '"+m_username+"'  "+
					          " AND A.APPLICATION_STATUS  IN ('NORM_TERMI','TERMI', 'TERMINATED')  "+
					          " AND TRUNC(A.ACTIVATED_DATE, 'MM') <= TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0)), 'MM') "+
					          " AND   A.FINANCE_NO IN ( "+
					                      " SELECT FINANCE_NO "+
					                      " FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION "+
					                      " WHERE  TERMINATION_VALIDITY_DATE >= TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0)), 'MM') "+ 
					                      " AND    ACTIVE_STATUS = 'TERM_CHECK' "+
					          " ) "+
					          " AND TRUNC(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATED_DATE(A.FINANCE_NO),'DD-MM-YYYY'), 'MM') <> TRUNC(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0), 'MM') "+
						  " ";
					
					
				}
				
				// end by udara 19-02-2014
				
				
				else {
					
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
				out.println("<td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Rental Amount</B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='"+m_td_color+"' >"+rs.getString(1)+"</td>"); 
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
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
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
