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
 

public class LAKDL_AF_MISF_Collection_Movement_Report_Age_New_1 extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs2;
	
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
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			
			  String m_sort_column   = "COLLECTION_OFFICER";	
				String m_order_by_type = "ASC";
			  
			
				if(m_chksql.equals("run_report")){ 
			
				String m_date=req.getParameter("date");
				String m_team_id=req.getParameter("team_id");
				String m_sub_team_id=req.getParameter("sub_team_id");
				String m_user_id=req.getParameter("user_id");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_COLL_MOVE_RPT_AGE(:1,:2,:3,:4,:5);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_team_id);
				callstmt1.setString(3,m_sub_team_id);
				callstmt1.setString(4,m_user_id);
				callstmt1.setString(5,m_username);
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}
			
			else if(m_chksql.equals("main_page")){ 
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
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
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=run_report&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				//out.println("			print_report();"); 
				out.println("			alert('Report Generated Successfully');"); 
				out.println("		  m_table.innerHTML=\"\";");
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_team_id=document.Form1.TEAM_HEAD.value;");
				out.println("		m_sub_team_id=document.Form1.SUB_TEAM_HEAD.value;");
				out.println("		m_user_id=document.Form1.TXT_USER.value;");
				
				out.println("if(m_team_id!='' && m_sub_team_id!='' && m_user_id!='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_user&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("else if(m_team_id!='' && m_sub_team_id!='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_sub_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("else if(m_team_id!='' && m_sub_team_id=='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				
				out.println("else if(m_team_id=='' && m_sub_team_id=='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_all&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");

			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
			out.println("     team_help();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TEAM_HEAD.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
			out.println("			document.Form1.TEAM_HEAD.value=data_vec[0]");
			out.println("			}");
		 	out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
			out.println("     help_button_user(data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
			out.println("			document.Form1.TXT_USER.value=data_vec[0]");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M_SYS_DATE'  ){");
			out.println("			document.Form1.VAL_DAY.value=data_vec[0];");
			out.println("			document.Form1.VAL_MONTH.value=data_vec[1];");
			out.println("			document.Form1.VAL_YEAR.value=data_vec[2];");
			out.println("			}");
			out.println("}");
			
				
				out.println("function drill_down_asset(m_finance_no) {");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
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
			out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_team_User&data_val=\"+obj.value+\"&ac_status=Y\";");	
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=main_page&generate=page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=main_page&generate=page';"); 
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
			out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - With Ageing - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - With Ageing - \"+document.Form1.hid_status.value;"); 
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
			out.println("		sub_team_assign(oBj);"); 
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
			out.println("function team_help() {"); 
			out.println("    m_sql = \"m_help_TXT_TEAM_ID_sql\";"); 
			out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
			out.println("}"); 
				
			out.println("function team_assign(oBj){");
			out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[2]");
			out.println(" document.Form1.TEAM_DESC.value =oBj.valout[3]");
			out.println("}");
			
			out.println("function sub_team_help() {"); 
			out.println("    m_sql = \"m_help_txt_sub_team_sql\";"); 
			out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'4');"); 
			out.println("}"); 
				
			out.println("function sub_team_assign(oBj){");
			out.println(" document.Form1.SUB_TEAM_HEAD.value =oBj.valout[2]");
			out.println(" document.Form1.SUB_TEAM_DESC.value =oBj.valout[3]");
			out.println(" document.Form1.TEAM_HEAD.value =oBj.valout[4]");
			out.println(" document.Form1.TEAM_DESC.value =oBj.valout[5]");
			out.println("}");
			
			
				
			out.println("function help_button_user() {"); 
			out.println(" document.Form1.hid_help_type.value='3' ");
			out.println("    m_sql = \"m_help_team_user_id_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TEAM_HEAD.value+\"@\"+document.Form1.SUB_TEAM_HEAD.value+\"@\"+document.Form1.TXT_USER.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'3');"); 
			out.println("}"); 
			

			out.println("function help_value_assign_user(oBj) {"); 
			out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_USER_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.SUB_TEAM_HEAD.value=oBj.valout[4];"); 
			out.println("    document.Form1.SUB_TEAM_DESC.value=oBj.valout[5];"); 
			out.println("    document.Form1.TEAM_HEAD.value=oBj.valout[6];"); 
			out.println("    document.Form1.TEAM_DESC.value=oBj.valout[7];"); 
			out.println("}"); 
			
			out.println("function clear_data(IfCount) {");
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TEAM_HEAD.value='';");
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("document.Form1.TXT_USER.value='';");
	  	out.println("		}"); 
			out.println("}");
			
     
			out.println("function get_rental_dates(date,m_client_code,m_officer){");
			
			out.println("if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
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
			
			
			out.println("function get_system_date() {");
			out.println("assignState('M_SYS_DATE') ;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
				
				
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date()\"> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_MOVEMENT_REPORT\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Movement Report - With Ageing </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
		//	out.println("<td width='10%'></td>");
			//		out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='View_Letter()' value=\"Letter\"></td>"); 
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
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" style=\"{width:110px;}\" onClick=\"print_report()\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" style=\"{width:110px;}\" onClick=\"run_report()\" ></td>"); 
				out.println("</tr>");
				out.println("</table>");
				
		    out.println("<table align='center' width='100%' class='table' border='0'>"); 
			  out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TEAM_HEAD'  class=div_input>Team Id </DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TEAM_HEAD' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_CLIENT'),makeRequest(document.Form1.TEAM_HEAD)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TEAM_HEAD' value=\"Help\" onClick=\"team_help()\">"); 
				out.println("</td>");
				out.println("<td width='10%' >Team Desc </td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TEAM_DESC' maxlength='10' style='{width=250px}' size='10' DISABLED >"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_SUB_TEAM_HEAD'  class=div_input>Sub Team Id </DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='SUB_TEAM_HEAD' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_CLIENT'),makeRequest(document.Form1.TEAM_HEAD)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_SUB_TEAM_HEAD' value=\"Help\" onClick=\"sub_team_help()\">"); 
				out.println("</td>");
				out.println("<td width='10%' >Sub Team Desc </td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='SUB_TEAM_DESC' maxlength='10' style='{width=250px}' size='10' DISABLED >"); 
				out.println("</tr>"); 
								
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Collection Officer </DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onBlur=\"help_button_user()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("<td width='10%' >Officer Name </td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER_NAME' maxlength='10' style='{width=250px}' size='10' DISABLED >"); 
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
		
   else if(m_chksql.equals("print_report_user")){		
	  
		String m_date="";
	  String m_team_head="";
	  String m_officer="";
		String m_sub_team_id="";
	  String m_prev_month="",m_curr_month="",m_curr_date="",m_Letter_date="",m_prev_date="",m_termi_status="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		if(req.getParameter("user_id")!=null ){
		m_officer=req.getParameter("user_id").trim();
		}
					
		stmt = conn.createStatement ();
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'fmddth Month yyyy') LETTER_DATE  "+ //1
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'DD-MM-YYYY')  "+ //2
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'fmddth Mon.YYYY')  "+ //3
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+ //4
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'fmddth Mon.YYYY') "+ //5
			" FROM DUAL ");          
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			m_prev_date=rs.getString(2);	
			m_prev_month=rs.getString(3);	
			m_curr_date=rs.getString(4);	
			m_curr_month=rs.getString(5);	
			}
			rs.close();
		//out.println(
			rs=stmt.executeQuery(			
			" SELECT a.team_id,  "+ //1
			" "+m_schema_name+".af_co_get_emp_name(a.collection_officer) emp_name, "+ //2
			" a.finance_no, "+ //3
			" a.ent_user,  "+ //4
			" a.sub_team_id,  "+ //5
			" a.application_no, "+  //6
			" a.client_code, "+ //7
			" a.full_name,  "+ //8
			//" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //9
			" nvl(arr_amount,0)  arrears, "+
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //10
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //11
			//" CEIL(age) age,"+
			" age age,"+
			//" a.total_amount_old-(nvl(a.settled_amount_old,0) + nvl(a.adjusted_amount_old,0)) arrears, "+ //12
			" nvl(arr_amount_old,0) arrears ,"+
			" (nvl(a.future_receivable_old,0) + nvl(a.arr_capital_portion_old,0) ) cap_out,  "+ //13
			//" ceil((NVL(a.total_rentals_old,0)- NVL(a.rentals_paid_old,0) )) age  ,"+ //14
			//" CEIL(age_OLD) age,"+
			" age_OLD age,"+
			" a.collection_officer"+ //15
			" ,agr_status,"+ //16
			" termi_status, "+//17
			" APPLICATION_STATUS"+
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt_age a "+
			" where a.collection_officer='"+m_officer+"' "+
			" and A.TRANSACTION_TYPE <> 'HIRING' "+
			" AND a.ent_user='"+m_username+"' ");
			
		  boolean more2=rs.next();
			//int m_age=0,m_age_old=0;
			double m_age=0,m_age_old=0;
			int m_cum_value=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_6_count=0,m_age_4_5_count=0,m_age_legal_count=0;
			int m_age_0_count_old=0,m_age_1_count_old=0,m_age_2_count_old=0,m_age_3_count_old=0,m_age_4_count_old=0,m_age_6_count_old=0,m_age_4_5_count_old=0,m_age_legal_count_old=0;
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_6_m_amount=0,m_age_legal_m_amount=0;
			double m_amount_old=0,m_age_0_m_amount_old=0,m_age_1_m_amount_old=0,m_age_2_m_amount_old=0,m_age_3_m_amount_old=0,m_age_6_m_amount_old=0,m_age_legal_m_amount_old=0;
			double m_amount_cap=0;
			double m_amount_cap_old=0;
			double m_age_0_m_amount_cap=0,m_age_1_m_amount_cap=0,m_age_2_m_amount_cap=0,m_age_3_m_amount_cap=0,m_age_6_m_amount_cap=0,m_age_legal_m_amount_cap=0;
			double m_age_0_m_amount_cap_old=0,m_age_1_m_amount_cap_old=0,m_age_2_m_amount_cap_old=0,m_age_3_m_amount_cap_old=0,m_age_6_m_amount_cap_old=0,m_age_legal_m_amount_cap_old=0;
			double m_age_4_5_m_amount=0,m_age_4_5_m_amount_cap=0;
			double m_age_4_5_m_amount_old=0,m_age_4_5_m_amount_cap_old=0;
			String m_collector_name="";
			String m_collector="";
			String m_agr_status="";
			String m_app_status="";
			
			if(more2){
			m_collector_name=rs.getString(2);
			m_collector=rs.getString(15);
			}
			while(more2){
			m_age=rs.getDouble(11);
			m_amount=rs.getDouble(9);
			m_amount_cap=rs.getDouble(10);
			
			m_age_old=rs.getDouble(14);
			m_amount_old=rs.getDouble(12);
			m_amount_cap_old=rs.getDouble(13);
			
			m_agr_status=rs.getString(16);
			m_termi_status =rs.getString(17);
			
			if (m_termi_status.equals("0")){
			
			
			if (!m_app_status.equals("LEGAL")) { //Adde By Nuwan De Silva on 24-11-2009
			if ( m_age <=0 ){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount+=m_amount;
			m_age_0_m_amount_cap=m_age_0_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==1){
			else if (m_age>0 && m_age<=3){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount+=m_amount;
			m_age_1_m_amount_cap=m_age_1_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==2){
			else if (m_age>3 && m_age<6){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount+=m_amount;
			m_age_2_m_amount_cap=m_age_2_m_amount_cap+m_amount_cap;
			}
			else if (m_age>=6 && m_age<12){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount+=m_amount;
			m_age_3_m_amount_cap=m_age_3_m_amount_cap+m_amount_cap;
			}
			//else if (m_age >3 && m_age <=5){
			else if (m_age>=12 && m_age<18){
			m_age_4_5_count=m_age_4_5_count+1;
			m_age_4_5_m_amount+=m_amount;
			m_age_4_5_m_amount_cap=m_age_4_5_m_amount_cap+m_amount_cap;
			}
			//else if(m_age>5 ){
			else if(m_age>=18 ){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount+=m_amount;
			m_age_6_m_amount_cap=m_age_6_m_amount_cap+m_amount_cap;
			}
			
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count=m_age_legal_count+1;
			m_age_legal_m_amount+=m_amount_cap;
			m_age_legal_m_amount_cap=m_age_legal_m_amount_cap+m_amount_cap;
			}
			
			}
			
			if(m_agr_status.equals("OLD") && !m_termi_status.equals("2") && !m_termi_status.equals("3")){
			
			if (!m_app_status.equals("LEGAL")) { //Adde By Nuwan De Silva on 24-11-2009
			
			if ( m_age_old <=0 ){
			m_age_0_count_old=m_age_0_count_old+1;
			m_age_0_m_amount_old+=m_amount_old;
			m_age_0_m_amount_cap_old=m_age_0_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==1){
			else if (m_age_old>0 && m_age_old<=3){
			m_age_1_count_old=m_age_1_count_old+1;
			m_age_1_m_amount_old+=m_amount_old;
			m_age_1_m_amount_cap_old=m_age_1_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==2){
			else if (m_age_old>3 && m_age_old<6){
			m_age_2_count_old=m_age_2_count_old+1;
			m_age_2_m_amount_old+=m_amount_old;
			m_age_2_m_amount_cap_old=m_age_2_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==3){
			else if (m_age_old>=6 && m_age_old<12){
			m_age_3_count_old=m_age_3_count_old+1;
			m_age_3_m_amount_old+=m_amount_old;
			m_age_3_m_amount_cap_old=m_age_3_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old >3 && m_age_old <=5){
			else if (m_age_old>=12 && m_age_old<18){
			m_age_4_5_count_old=m_age_4_5_count_old+1;
			m_age_4_5_m_amount_old+=m_amount_old;
			m_age_4_5_m_amount_cap_old=m_age_4_5_m_amount_cap_old+m_amount_cap_old;
			}
			//else if(m_age_old>5 ){
			else if(m_age_old>=18 ){
		  m_age_6_count_old=m_age_6_count_old+1;
			m_age_6_m_amount_old+=m_amount_old;
			m_age_6_m_amount_cap_old=m_age_6_m_amount_cap_old+m_amount_cap_old;
			}
			
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count_old=m_age_legal_count_old+1;
			m_age_legal_m_amount_old+=m_amount_cap_old;
			m_age_legal_m_amount_cap_old=m_age_legal_m_amount_cap_old+m_amount_cap_old;
			}
			
			}
			
		  more2=rs.next();
			}
			
		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill(m_user_id,m_age){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=drill_down_details&age=\"+m_age+\"&user_id=\"+m_user_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			out.println("function show_movement_drill_old(m_user_id,m_age){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=drill_down_details_old&age=\"+m_age+\"&user_id=\"+m_user_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+m_Letter_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header ------------------------------------------------------- <tr>
			out.println("</tr>");
      out.println("<td colspan='9' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");//<b> Figures As @  "+m_Letter_date+"
      out.println("</tr>");
			out.println("<tr bgcolor='lightgrey' ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total Arr. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");
			out.println("</tr>");
			
			int m_total_count=m_age_1_count+m_age_2_count+m_age_3_count+m_age_4_5_count+m_age_6_count+m_age_legal_count;
			double m_age_tot_amount=m_age_1_m_amount+m_age_2_m_amount+m_age_3_m_amount+m_age_4_5_m_amount+m_age_6_m_amount+m_age_legal_m_amount;
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' style=\"{cursor:hand;}\" onClick=\"show_employee_drill('"+m_collector+"')\" class='rep-body' align='left'><u>"+m_collector_name+"<u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_age_tot_amount)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',1)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_1_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',2)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_2_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',3)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_3_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',5)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_4_5_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',6)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_6_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',7)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_legal_m_amount)+"</u></td>");
		  out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			int m_total_count_old=m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old+m_age_legal_count_old;
			double m_age_tot_amount_old=m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old+m_age_legal_m_amount_old;


			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count_old+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_age_tot_amount_old)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',1)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_1_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',2)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_2_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',3)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_3_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',5)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_4_5_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',6)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_6_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',7)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_legal_m_amount_old)+"</u></td>");
		  out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");			
			//Capital Out Standing --------------------------------------------------------------------------------------------------
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");//class=pdn_txtpos2
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");
			out.println("</tr>");
			
		  double m_tot_age_m_amount_cap=m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap+m_age_legal_m_amount_cap;
      //double m_tot_age_m_amount_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap;

			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}'  class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_tot_age_m_amount_cap)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',1)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_1_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',2)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_2_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',3)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_3_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',5)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_4_5_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',6)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_6_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',7)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_legal_m_amount_cap)+"</u></td>");
		  out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");			
		 double m_tot_age_m_amount_cap_old=m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old+m_age_legal_m_amount_cap_old;
     //double m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;

			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count_old+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_tot_age_m_amount_cap_old)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',1)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_1_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',2)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_2_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',3)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_3_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',5)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_4_5_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',6)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_6_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',7)\" class='rep-body' bgcolor='#FFFFCC' ><u>"+nf.format(m_age_legal_m_amount_cap_old)+"</u></td>");
		  out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("</tr>");			

			
			out.println("</table>");

			
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
}

   else if(m_chksql.equals("print_report_sub_team")){		
	  
		String m_date="";
	  String m_team_id="";
	  String m_officer="";
		String m_sub_team_id="";
	  String m_prev_month="",m_curr_month="",m_curr_date="",m_Letter_date="",m_prev_date="",m_termi_status="";

		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}

		if(req.getParameter("sub_team_id")!=null ){
		m_sub_team_id=req.getParameter("sub_team_id").trim();
		}
		
		stmt = conn.createStatement ();
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'fmddth Month yyyy') LETTER_DATE  "+ //1
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'DD-MM-YYYY')  "+ //2
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'fmddth Mon.YYYY')  "+ //3
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+ //4
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'fmddth Mon.YYYY') "+ //5
			" FROM DUAL ");          
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			m_prev_date=rs.getString(2);	
			m_prev_month=rs.getString(3);	
			m_curr_date=rs.getString(4);	
			m_curr_month=rs.getString(5);	
			}
			rs.close();
			
  	  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("function show_movement_drill(m_user_id,m_age){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=drill_down_details&age=\"+m_age+\"&user_id=\"+m_user_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			out.println("function show_movement_drill_old(m_user_id,m_age){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=drill_down_details_old&age=\"+m_age+\"&user_id=\"+m_user_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	


			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
					
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<tr 1>");
			out.println("<td width=\"*%\" align=center  ><u><b>Monthly Collection Movement as at "+m_Letter_date+" </u></td>"); 
			out.println("</tr >");
			out.println("<tr >");
			out.println("<td width=\"*%\" align=center  ><u><b>Head Office Marketing</u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
		
			rs=stmt.executeQuery(
			" SELECT a.team_id,  "+ //1
			" "+m_schema_name+".af_co_get_emp_name(a.collection_officer) emp_name, "+ //2
			" a.finance_no, "+ //3
			" a.ent_user,  "+ //4
			" a.sub_team_id,  "+ //5
			" a.application_no, "+  //6
			" a.client_code, "+ //7
			" a.full_name,  "+ //8
			//" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //9
			" nvl(arr_amount,0)  arrears, "+
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //10
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //11
			//" CEIL(age) age,"+
			" round(age,2) age,"+
		//	" a.total_amount_old-(nvl(a.settled_amount_old,0) + nvl(a.adjusted_amount_old,0)) arrears, "+ //12
		  " nvl(arr_amount_old,0) arrears ,"+
			" (nvl(a.future_receivable_old,0) + nvl(a.arr_capital_portion_old,0) ) cap_out,  "+ //13
			//" ceil((NVL(a.total_rentals_old,0)- NVL(a.rentals_paid_old,0) )) age  ,"+ //14
			//" CEIL(age_OLD) age,"+
			" round(age_OLD,2) age,"+
			" a.collection_officer "+ //15
			" ,agr_status, "+ //16
			" termi_status, "+//17
			" APPLICATION_STATUS "+ //18
			//" sub_team_desc "+ //16
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt_age a "+
			" where a.sub_team_id='"+m_sub_team_id+"' "+
			" and A.TRANSACTION_TYPE <> 'HIRING' "+
			
			" AND a.ent_user='"+m_username+"' ORDER BY A.collection_officer ");
			
		  boolean more2=rs.next();
			//int m_age=0,m_age_old=0;
			double  m_age=0,m_age_old=0;
			int m_cum_value=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_6_count=0,m_age_4_5_count=0,m_age_legal_count=0;
			int m_age_0_count_old=0,m_age_1_count_old=0,m_age_2_count_old=0,m_age_3_count_old=0,m_age_4_count_old=0,m_age_6_count_old=0,m_age_4_5_count_old=0,m_age_legal_count_old=0;
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_6_m_amount=0,m_age_legal_m_amount=0;
			double m_amount_old=0,m_age_0_m_amount_old=0,m_age_1_m_amount_old=0,m_age_2_m_amount_old=0,m_age_3_m_amount_old=0,m_age_6_m_amount_old=0,m_age_legal_m_amount_old=0;
			double m_amount_cap=0;
			double m_amount_cap_old=0;
			double m_age_0_m_amount_cap=0,m_age_1_m_amount_cap=0,m_age_2_m_amount_cap=0,m_age_3_m_amount_cap=0,m_age_6_m_amount_cap=0,m_age_legal_m_amount_cap=0;
			double m_age_0_m_amount_cap_old=0,m_age_1_m_amount_cap_old=0,m_age_2_m_amount_cap_old=0,m_age_3_m_amount_cap_old=0,m_age_6_m_amount_cap_old=0,m_age_legal_m_amount_cap_old=0;
			double m_age_4_5_m_amount=0,m_age_4_5_m_amount_cap=0;
			double m_age_4_5_m_amount_old=0,m_age_4_5_m_amount_cap_old=0;
			String m_collector="",m_collector_name="";
			String m_app_status="";
			
			double m_rate_count_rent = 0;
			double m_rate_cap_rent   = 0; 
			double m_rate_cap_cap   = 0;
			
			int m_grand_count_total_month_0 = 0;
			int m_grand_count_total_month_1 = 0;
			int m_grand_count_total_month_2 = 0;
			int m_grand_count_total_month_3 = 0;
			int m_grand_count_total_month_4_5 = 0;			
			int m_grand_count_total_month_6 = 0;
			int m_grand_count_total_month_legal = 0;
			int m_grand_count_total = 0;
			double m_grand_amount_total=0,m_grand_amount_total_month_0=0,m_grand_amount_total_month_1=0,m_grand_amount_total_month_2=0,m_grand_amount_total_month_3=0,m_grand_amount_total_month_4_5=0,m_grand_amount_total_month_6=0,m_grand_amount_total_month_legal=0;
			double m_grand_amount_total_old=0,m_grand_amount_total_old_month_0=0,m_grand_amount_total_old_month_1=0,m_grand_amount_total_old_month_2=0,m_grand_amount_total_old_month_3=0,m_grand_amount_total_old_month_4_5=0,m_grand_amount_total_old_month_6=0,m_grand_amount_total_old_month_legal=0;
			int m_grand_count_total_old=0,m_grand_count_total_month_0_old=0,m_grand_count_total_month_1_old=0,m_grand_count_total_month_2_old=0,m_grand_count_total_month_3_old=0,m_grand_count_total_month_4_5_old=0,m_grand_count_total_month_6_old=0,m_grand_count_total_month_legal_old=0;
			double m_grand_count_total_rate=0,m_grand_amont_total_rate=0;
			double m_tot_age_grand_m_amount_cap=0,m_age_0_grand_m_amount_cap=0,m_age_1_grand_m_amount_cap=0,m_age_2_grand_m_amount_cap=0,m_age_3_grand_m_amount_cap=0,m_age_4_5_grand_m_amount_cap=0,m_age_6_grand_m_amount_cap=0,m_age_legal_grand_m_amount_cap=0;
			double m_grand_amount_tot_rate=0,m_tot_age_grand_m_amount_cap_old=0,m_age_0_grand_m_amount_cap_old=0,m_age_1_grand_m_amount_cap_old=0,m_age_2_grand_m_amount_cap_old=0,m_age_3_grand_m_amount_cap_old=0,m_age_4_5_grand_m_amount_cap_old=0,m_age_6_grand_m_amount_cap_old=0,m_age_legal_grand_m_amount_cap_old=0;
			String m_agr_status="";
			
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 					
			
			while(more2){
			m_collector=rs.getString(15);
			m_collector_name=rs.getString(2);
			
      m_age_0_count=0;
			m_age_0_m_amount=0;  
			m_age_0_m_amount_cap=0;
			m_age_1_count=0; 
			m_age_1_m_amount=0;  
			m_age_1_m_amount_cap=0;
			m_age_2_count=0;  
			m_age_2_m_amount=0; 
			m_age_2_m_amount_cap=0;
			m_age_3_count=0; 
			m_age_3_m_amount=0; 
			m_age_3_m_amount_cap=0;
			m_age_4_5_count=0;
			m_age_4_5_m_amount=0;
			m_age_4_5_m_amount_cap=0;
			m_age_6_count=0;
			m_age_6_m_amount=0;
			m_age_6_m_amount_cap=0;
			
			m_age_legal_count=0;
			m_age_legal_m_amount=0;
			m_age_legal_m_amount_cap=0;
			
			m_age_0_count_old=0;
			m_age_0_m_amount_old=0;  
			m_age_0_m_amount_cap_old=0;
			m_age_1_count_old=0; 
			m_age_1_m_amount_old=0;  
			m_age_1_m_amount_cap_old=0;
			m_age_2_count_old=0;  
			m_age_2_m_amount_old=0; 
			m_age_2_m_amount_cap_old=0;
			m_age_3_count_old=0; 
			m_age_3_m_amount_old=0; 
			m_age_3_m_amount_cap_old=0;
			m_age_4_5_count_old=0;
			m_age_4_5_m_amount_old=0;
			m_age_4_5_m_amount_cap_old=0;
			
			m_age_6_count_old=0;
			m_age_6_m_amount_old=0;
			m_age_6_m_amount_cap_old=0;
			
			m_age_legal_count_old=0;
			m_age_legal_m_amount_old=0;
			m_age_legal_m_amount_cap_old=0;
			
			//ADDED NS on 28-05-2009
      m_amount_old=0;
      m_amount=0;
			m_age_old=0;
			m_age=0;
			while(m_collector.equals(rs.getString(15))){
			m_age=rs.getDouble(11);
			m_amount=rs.getDouble(9);
			m_amount_cap=rs.getDouble(10);
			
			m_age_old=rs.getDouble(14);
			m_amount_old=rs.getDouble(12);
			m_amount_cap_old=rs.getDouble(13);
			
			m_agr_status=rs.getString(16);
      m_termi_status = rs.getString(17);
			m_app_status   = rs.getString(18);
		if(m_termi_status.equals("0")){
		
		  if (!m_app_status.equals("LEGAL")) { //Adde By Nuwan De Silva on 24-11-2009
			
			if ( m_age <=0 ){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount+=m_amount;
			m_age_0_m_amount_cap=m_age_0_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==1){
			else if (m_age>0 && m_age<=3){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount+=m_amount;
			m_age_1_m_amount_cap=m_age_1_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==2){
			else if (m_age>3 && m_age<6){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount+=m_amount;
			m_age_2_m_amount_cap=m_age_2_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==3){
			else if (m_age>=6 && m_age<12){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount+=m_amount;
			m_age_3_m_amount_cap=m_age_3_m_amount_cap+m_amount_cap;
			}
			//else if (m_age >3 && m_age <=5){
			else if (m_age>=12 && m_age<18){
			m_age_4_5_count=m_age_4_5_count+1;
			m_age_4_5_m_amount+=m_amount;
			m_age_4_5_m_amount_cap=m_age_4_5_m_amount_cap+m_amount_cap;
			}
			//else if(m_age>5 ){
			else if (m_age>=18){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount+=m_amount;
			m_age_6_m_amount_cap=m_age_6_m_amount_cap+m_amount_cap;
			}
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count=m_age_legal_count+1;
			m_age_legal_m_amount+=m_amount_cap;
			//m_age_legal_m_amount_cap=m_age_legal_m_amount_cap+m_amount_cap;
			m_age_legal_m_amount_cap=m_age_legal_m_amount_cap+m_amount_cap;
			}
						
			}
			
			
			if(m_agr_status.equals("OLD") && !m_termi_status.equals("2") && !m_termi_status.equals("3")){
			
			if (!m_app_status.equals("LEGAL")) { //Adde By Nuwan De Silva on 24-11-2009
			
			if ( m_age_old <=0 ){
			m_age_0_count_old=m_age_0_count_old+1;
			m_age_0_m_amount_old+=m_amount_old;
			m_age_0_m_amount_cap_old=m_age_0_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==1){
			else if (m_age_old>0 && m_age_old<=3){
			m_age_1_count_old=m_age_1_count_old+1;
			m_age_1_m_amount_old+=m_amount_old;
			m_age_1_m_amount_cap_old=m_age_1_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==2){
			else if (m_age_old>3 && m_age_old<6){
			m_age_2_count_old=m_age_2_count_old+1;
			m_age_2_m_amount_old+=m_amount_old;
			m_age_2_m_amount_cap_old=m_age_2_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==3){
			else if (m_age_old>=6 && m_age_old<12){
			m_age_3_count_old=m_age_3_count_old+1;
			m_age_3_m_amount_old+=m_amount_old;
			m_age_3_m_amount_cap_old=m_age_3_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old >3 && m_age_old <=5){
			else if (m_age_old>=12 && m_age_old<18){
			m_age_4_5_count_old=m_age_4_5_count_old+1;
			m_age_4_5_m_amount_old+=m_amount_old;
			m_age_4_5_m_amount_cap_old=m_age_4_5_m_amount_cap_old+m_amount_cap_old;
			}
			//else if(m_age_old>5 ){
			else if (m_age_old>=18){
		  m_age_6_count_old=m_age_6_count_old+1;
			m_age_6_m_amount_old+=m_amount_old;
			m_age_6_m_amount_cap_old=m_age_6_m_amount_cap_old+m_amount_cap_old;
			}
			
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count_old=m_age_legal_count_old+1;
			m_age_legal_m_amount_old+=m_amount_cap_old;
			//m_age_legal_m_amount_cap_old=m_age_legal_m_amount_cap_old+m_amount_cap_old;
			m_age_legal_m_amount_cap_old=m_age_legal_m_amount_cap_old+m_amount_cap_old;
			}
			
			}
		  
			more2=rs.next();
			
			if(!more2){break;}
			}
						
			//out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header ------------------------------------------------------- <tr>
			/*out.println("</tr>");
      out.println("<td colspan='9' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");//<b> Figures As @  "+m_Letter_date+"
      out.println("</tr>");
			*/
			out.println("<tr><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Date</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 - Below</td>");

			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0.01 - 3.00</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3.01 - 5.99</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6.00 - 11.99</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>12.00 - 17.99</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>18.00 - Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("</tr>");
			
			int m_total_count=m_age_0_count+m_age_1_count+m_age_2_count+m_age_3_count+m_age_4_5_count+m_age_6_count+m_age_legal_count;
			double m_age_tot_amount=m_age_0_m_amount+m_age_1_m_amount+m_age_2_m_amount+m_age_3_m_amount+m_age_4_5_m_amount+m_age_6_m_amount+m_age_legal_m_amount;
			int m_total_count_old=m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old+m_age_legal_count_old;
			double m_age_tot_amount_old=m_age_0_m_amount_old+m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old+m_age_legal_m_amount_old;
			
			m_rate_count_rent = (m_total_count_old-m_total_count)/m_total_count_old*100;
			m_rate_cap_rent   = (m_age_tot_amount_old-m_age_tot_amount)/m_age_tot_amount_old*100;
			
			out.println("<tr>");//Added By Sandun on 23-03-2009
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body'>"+m_collector_name+"</td>");
			out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Rental Movement</td>");
			out.println("</tr>");
					
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' style=\"{cursor:hand;}\"  class='rep-body' align='left'>&nbsp;</td>"); //onClick=\"show_employee_drill('"+m_collector+"')\"
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_count_rent)+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',0)\" class='rep-body' ><u>"+nf.format(m_age_0_m_amount)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',1)\" class='rep-body' ><u>"+nf.format(m_age_1_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',2)\" class='rep-body' ><u>"+nf.format(m_age_2_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',3)\" class='rep-body' ><u>"+nf.format(m_age_3_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',5)\" class='rep-body' ><u>"+nf.format(m_age_4_5_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',6)\" class='rep-body' ><u>"+nf.format(m_age_6_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill('"+m_collector+"',7)\" class='rep-body' ><u>"+nf.format(m_age_legal_m_amount)+"</u></td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf.format(m_age_tot_amount)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_cap_rent)+"</td>");
			out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");//legal
			out.println("<td bgcolor='#90EE90'  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//int m_total_count_old=m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old;
			//double m_age_tot_amount_old=m_age_0_m_amount_old+m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old;


			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count_old+"</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count_old+"</td>");//
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' onClick=\"show_movement_drill_old('"+m_collector+"',0)\" class='rep-body' ><u>"+nf.format(m_age_0_m_amount_old)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',1)\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',2)\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',3)\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',5)\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',6)\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_old)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',7)\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_old)+"</u></td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' class='rep-body'  bgcolor='#90EE90' >"+nf.format(m_age_tot_amount_old)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			/*out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");			
			*/
			//Capital Out Standing --------------------------------------------------------------------------------------------------
			/*out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");//class=pdn_txtpos2
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 Month</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O.</td>");
			out.println("</tr>");
			out.println("<tr >
			*/
			out.println("<tr>");//Added By Sandun on 23-03-2009
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body'>&nbsp;</td>");
			out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Capital Movement</td>");
			out.println("</tr>");
			
			
			
		  //double m_tot_age_m_amount_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap;
			double m_tot_age_m_amount_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap+m_age_legal_m_amount_cap;
      double m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old+m_age_legal_m_amount_cap_old;
      m_rate_cap_cap = (m_tot_age_m_amount_cap_old-m_tot_age_m_amount_cap)/m_tot_age_m_amount_cap_old*100;
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}'  class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_count_rent)+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+nf.format(m_age_0_m_amount_cap)+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',1)\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',2)\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',3)\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',5)\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',6)\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill('"+m_collector+"',7)\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_cap)+"</u></td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf.format(m_tot_age_m_amount_cap)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_cap_cap)+"</td>");
			out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");//legal
			out.println("<td  bgcolor='#90EE90' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");			
		 //double m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;
     //---28-04-2009
			//double m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;

			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count_old+"</td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+nf.format(m_age_0_m_amount_cap_old)+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',1)\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',2)\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',3)\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',5)\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',6)\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_old('"+m_collector+"',7)\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_cap_old)+"</u></td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf.format(m_tot_age_m_amount_cap_old)+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td  bgcolor='lightgrey' width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("<td  bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");			
			out.println("</tr>");			

			//-----------------------Total Rental Movement
			m_grand_count_total_month_0   += m_age_0_count;
			m_grand_count_total_month_1   += m_age_1_count;
			m_grand_count_total_month_2   += m_age_2_count;
			m_grand_count_total_month_3   += m_age_3_count;
			m_grand_count_total_month_4_5 += m_age_4_5_count;
			m_grand_count_total_month_6   += m_age_6_count;
			m_grand_count_total_month_legal   += m_age_legal_count;
			
			m_grand_count_total_month_0_old    +=m_age_0_count_old;
			m_grand_count_total_month_1_old    +=m_age_1_count_old;
			m_grand_count_total_month_2_old    +=m_age_2_count_old;
			m_grand_count_total_month_3_old    +=m_age_3_count_old;
			m_grand_count_total_month_4_5_old  +=m_age_4_5_count_old;
			m_grand_count_total_month_6_old    +=m_age_6_count_old;
			m_grand_count_total_month_legal_old    +=m_age_legal_count_old;
			
			m_grand_count_total     = m_grand_count_total_month_0+m_grand_count_total_month_1+m_grand_count_total_month_2+m_grand_count_total_month_3+m_grand_count_total_month_4_5+m_grand_count_total_month_6+m_grand_count_total_month_legal;
			m_grand_count_total_old = m_grand_count_total_month_0_old + m_grand_count_total_month_1_old + m_grand_count_total_month_2_old+ m_grand_count_total_month_3_old + m_grand_count_total_month_4_5_old+m_grand_count_total_month_6_old+m_grand_count_total_month_legal_old;
			
						
			m_grand_amount_total_month_0   += m_age_0_m_amount;
			m_grand_amount_total_month_1   += m_age_1_m_amount;
			m_grand_amount_total_month_2   += m_age_2_m_amount;
			m_grand_amount_total_month_3   += m_age_3_m_amount;
			m_grand_amount_total_month_4_5 += m_age_4_5_m_amount;
			m_grand_amount_total_month_6   += m_age_6_m_amount;
			m_grand_amount_total_month_legal   += m_age_legal_m_amount;
			
			m_grand_amount_total_old_month_0   += m_age_0_m_amount_old;
			m_grand_amount_total_old_month_1   += m_age_1_m_amount_old;
			m_grand_amount_total_old_month_2   += m_age_2_m_amount_old;
			m_grand_amount_total_old_month_3   += m_age_3_m_amount_old;
			m_grand_amount_total_old_month_4_5 += m_age_4_5_m_amount_old;
			m_grand_amount_total_old_month_6   += m_age_6_m_amount_old;
			m_grand_amount_total_old_month_legal   += m_age_legal_m_amount_old;
			
			
			m_grand_amount_total_old = m_grand_amount_total_old_month_0+m_grand_amount_total_old_month_1+m_grand_amount_total_old_month_2+m_grand_amount_total_old_month_3+m_grand_amount_total_old_month_4_5+m_grand_amount_total_old_month_6+m_grand_amount_total_old_month_legal;
			m_grand_amount_total     = m_grand_amount_total_month_0+m_grand_amount_total_month_1+m_grand_amount_total_month_2+m_grand_amount_total_month_3+m_grand_amount_total_month_4_5+m_grand_amount_total_month_6+m_grand_amount_total_month_legal;
			
			m_grand_count_total_rate = (m_grand_count_total_old-m_grand_count_total)/m_grand_count_total_old*100;
			m_grand_amont_total_rate = (m_grand_amount_total_old-m_grand_amount_total)/m_grand_amount_total_old*100;
			
			//----------------Capital Movement
					
			m_age_0_grand_m_amount_cap   += m_age_0_m_amount_cap;
			m_age_1_grand_m_amount_cap   += m_age_1_m_amount_cap;
			m_age_2_grand_m_amount_cap   += m_age_2_m_amount_cap;
			m_age_3_grand_m_amount_cap   += m_age_3_m_amount_cap;
			m_age_4_5_grand_m_amount_cap += m_age_4_5_m_amount_cap;
			m_age_6_grand_m_amount_cap   += m_age_6_m_amount_cap;
			m_age_legal_grand_m_amount_cap   += m_age_legal_m_amount_cap;
			
			
			m_age_0_grand_m_amount_cap_old   += m_age_0_m_amount_cap_old;
			m_age_1_grand_m_amount_cap_old   += m_age_1_m_amount_cap_old;
			m_age_2_grand_m_amount_cap_old   += m_age_2_m_amount_cap_old;
			m_age_3_grand_m_amount_cap_old   += m_age_3_m_amount_cap_old;
			m_age_4_5_grand_m_amount_cap_old += m_age_4_5_m_amount_cap_old;
			m_age_6_grand_m_amount_cap_old   += m_age_6_m_amount_cap_old;
			m_age_legal_grand_m_amount_cap_old   += m_age_legal_m_amount_cap_old;
			
			
			m_tot_age_grand_m_amount_cap     = m_age_0_grand_m_amount_cap+m_age_1_grand_m_amount_cap+m_age_2_grand_m_amount_cap+m_age_3_grand_m_amount_cap+m_age_4_5_grand_m_amount_cap+m_age_6_grand_m_amount_cap+m_age_legal_grand_m_amount_cap;
			m_tot_age_grand_m_amount_cap_old = m_age_0_grand_m_amount_cap_old+m_age_1_grand_m_amount_cap_old+m_age_2_grand_m_amount_cap_old+m_age_3_grand_m_amount_cap_old+m_age_4_5_grand_m_amount_cap_old+m_age_6_grand_m_amount_cap_old+m_age_legal_grand_m_amount_cap_old;
			
			m_grand_amount_tot_rate = (m_tot_age_grand_m_amount_cap_old-m_tot_age_grand_m_amount_cap)/m_tot_age_grand_m_amount_cap_old*100;
			
		//	out.println("</table>");
			}
			out.println("</table>");
			
			
			//---------------Added by Sandun on 29-04-2009-------------------------------------------------------------------------------------------------
        out.println("<br><br>");
        out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 	
				out.println("<tr><td width=100% bgcolor='lightgrey' align='center' colspan=10 STYLE='{font: 10pt  arial;}' class='rep-body'><b>Total</td></tr>");
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>Date</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 - Below </td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0.01 - 3.00</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3.01 - 5.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6.00 - 11.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>12.00 - 17.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>18.00 - Above</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
				out.println("</tr>");
				
				out.println("<tr>");
			  out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' bgcolor='#90EE90'>Total</td>");
			  out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Rental Movement</td>");
			  out.println("</tr>");	
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_curr_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_count_total_rate)+"</td>");
				out.println("</tr>");
				
				
			
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_0)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_1)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_2)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_3)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_4_5)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_6)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_legal)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_amont_total_rate)+"</td>");
				out.println("</tr>");
				
				out.println("<tr bgcolor='#90EE90'>");
				out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  align='center'>&nbsp;</td>");
				out.println("</tr>");
				
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_prev_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_0)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_1)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_2)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_3)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_4_5)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_6)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_legal)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
			  out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' bgcolor='#90EE90'>&nbsp;</td>");
			  out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Capital Movement</td>");
			  out.println("</tr>");	
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_curr_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_count_total_rate)+"</td>");
				out.println("</tr>");
				
				
			
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_0_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_1_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_2_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_3_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_4_5_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_6_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_legal_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_tot_age_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_amount_tot_rate)+"</td>");
				out.println("</tr>");
				
				out.println("<tr bgcolor='#90EE90'>");
				out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  align='center'>&nbsp;</td>");
				out.println("</tr>");
				
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_prev_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_0_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_1_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_2_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_3_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_4_5_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_6_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_legal_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_tot_age_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr >");
				out.println("<td bgcolor='lightgrey' colspan=11 width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("</tr>");		
			
				
				out.println("</table>");
			  out.println("<br><br>");
     //---------------------------end----------------------------------------------------------------------------------------------------------------
			

			

			
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
		
}


    else if(m_chksql.equals("drill_down_total_arr")){		
		
		String m_date="";
		String m_type="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}

		if(req.getParameter("type")!=null ){
		m_type=req.getParameter("type").trim();
		}
		
					
		stmt = conn.createStatement ();
		
			
  	  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill_sub_team(m_sub_team,m_date){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_sub_team&date=\"+m_date+\"&sub_team_id=\"+m_sub_team;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr >");
			//out.println("<td width=\"*%\" align=center  ><u><b>Monthly Collection Movement as at "+m_Letter_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			
			if (m_type.equals("NEW")) {
			
			rs=stmt.executeQuery(
			" SELECT "+
			" FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+
			" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE, "+ 
			" NVL(ARR_AMOUNT,0)  "+
			//" NVL(FUTURE_RECEIVABLE,0) , "+
			//" NVL(FUTURE_RECEIVABLE,0) + NVL(ARR_CAPITAL_PORTION,0) CAP_OS  "+
			" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE   "+
			" WHERE ENT_USER='"+m_username+"'  "+
			" AND TRANSACTION_TYPE <> 'HIRING' "+
			" AND TERMI_STATUS='0' "+
			" ");
			
			}
			else {
			
			rs=stmt.executeQuery(
			" SELECT "+
			" FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+
			" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE, "+ 
			" NVL(ARR_AMOUNT_OLD,0)  "+
			//" NVL(FUTURE_RECEIVABLE_OLD,0) , "+
			//" NVL(FUTURE_RECEIVABLE_OLD,0) + NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS  "+
			" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE   "+
			" WHERE ENT_USER='"+m_username+"'  "+
			" AND TRANSACTION_TYPE <> 'HIRING' "+
			" AND TERMI_STATUS  NOT IN ('2','3') "+
			" ");
			
			
			}

						
			boolean  more_inv =rs.next();
			double sum=0,sum_2=0,sum_3=0,sum_int=0;
			int i=1;
					out.println("<table align='center' width='100%' class='table' border='1'  cellspacing='0' >"); //bordercolor='black'
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%' class=div_input>No</td>");
					out.println("<td width='10%' class=div_input>Finance No</td>");
					out.println("<td width='10%' align='left' class=div_input>Status</td>");
					out.println("<td width='10%' align='left' class=div_input>Termination Date</td>");
					out.println("<td width='10%' align='right' class=div_input>Balance Outstanding</td>");
					//out.println("<td width='10%' align='right' class=div_input>Future Capital</td>");
					//out.println("<td width='10%' align='right' class=div_input>Capital OutStanding</td>");
					

					out.println("</tr>");
					
					while(more_inv){
					out.println("<tr  >"); //bgcolor=\"#FCEBC5\"
					out.println("<td width='1%'  class=div_input>"+i+"</td>");
					out.println("<td width='10%' style= cursor:hand; class=div_input onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\"><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' align='left' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='10%' align='left' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					//out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					//out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("</tr>");
					
					sum+= rs.getDouble(4);
					//sum_2+= rs.getDouble(5);
					//sum_3+= rs.getDouble(6);
					//sum_int+= rs.getDouble();

					more_inv =rs.next();
					i=i+1;
					}
					
					out.println("<tr >");
					out.println("<td width='1%'  class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input><b>Total</td>");
					//out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
					//out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_2)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
					
					out.println("</tr>");

			

			
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
					
		
}
    //Added By Nuwan De Silva 22-11-2009
    else if(m_chksql.equals("drill_down_total_capital")){		
		
		String m_date="";
		String m_type="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}

		if(req.getParameter("type")!=null ){
		m_type=req.getParameter("type").trim();
		}
		
					
		stmt = conn.createStatement ();
		
			
  	  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill_sub_team(m_sub_team,m_date){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_sub_team&date=\"+m_date+\"&sub_team_id=\"+m_sub_team;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr >");
			//out.println("<td width=\"*%\" align=center  ><u><b>Monthly Collection Movement as at "+m_Letter_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			
			if (m_type.equals("NEW")) {
			
			rs=stmt.executeQuery(
			" SELECT "+
			" FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+
			" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE, "+ 
			" NVL(ARR_CAPITAL_PORTION,0) , "+
			" NVL(FUTURE_RECEIVABLE,0) , "+
			" NVL(FUTURE_RECEIVABLE,0) + NVL(ARR_CAPITAL_PORTION,0) CAP_OS  "+
			" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE   "+
			" WHERE ENT_USER='"+m_username+"'  "+
			" AND TRANSACTION_TYPE <> 'HIRING' "+
			" AND TERMI_STATUS='0' "+
			" ");
			
			}
			else {
			
			rs=stmt.executeQuery(
			" SELECT "+
			" FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+
			" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE, "+ 
			" NVL(ARR_CAPITAL_PORTION_OLD,0) , "+
			" NVL(FUTURE_RECEIVABLE_OLD,0) , "+
			" NVL(FUTURE_RECEIVABLE_OLD,0) + NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS  "+
			" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE   "+
			" WHERE ENT_USER='"+m_username+"'  "+
			" AND TRANSACTION_TYPE <> 'HIRING' "+
			" AND TERMI_STATUS  NOT IN ('2','3') "+
			" ");
			
			
			}

						
			boolean  more_inv =rs.next();
			double sum=0,sum_2=0,sum_3=0,sum_int=0;
			int i=1;
					out.println("<table align='center' width='100%' class='table' border='1'  cellspacing='0' >"); //bordercolor='black'
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%' class=div_input>No</td>");
					out.println("<td width='10%' class=div_input>Finance No</td>");
					out.println("<td width='10%' align='left' class=div_input>Status</td>");
					out.println("<td width='10%' align='left' class=div_input>Termination Date</td>");
					out.println("<td width='10%' align='right' class=div_input>Arrears Capital</td>");
					out.println("<td width='10%' align='right' class=div_input>Future Capital</td>");
					out.println("<td width='10%' align='right' class=div_input>Capital OutStanding</td>");
					

					out.println("</tr>");
					
					while(more_inv){
					out.println("<tr  >"); //bgcolor=\"#FCEBC5\"
					out.println("<td width='1%'  class=div_input>"+i+"</td>");
					out.println("<td width='10%' style= cursor:hand; class=div_input onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\"><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' align='left' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='10%' align='left' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("</tr>");
					
					sum+= rs.getDouble(4);
					sum_2+= rs.getDouble(5);
					sum_3+= rs.getDouble(6);
					//sum_int+= rs.getDouble();

					more_inv =rs.next();
					i=i+1;
					}
					
					out.println("<tr >");
					out.println("<td width='1%'  class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input><b>Total</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_2)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_3)+"</td>");
					
					out.println("</tr>");

			

			
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
					
		
}

   else if(m_chksql.equals("print_report_team")){		
		
		String m_date="";
	  String m_team_id="";
	  String m_officer="";
		String m_sub_team_id="";
	  String m_prev_month="",m_curr_month="",m_curr_date="",m_Letter_date="",m_prev_date="",m_termi_status="";

		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}

		if(req.getParameter("sub_team_id")!=null ){
		m_sub_team_id=req.getParameter("sub_team_id").trim();
		}
		
		if(req.getParameter("team_id")!=null ){
		m_team_id=req.getParameter("team_id").trim();
		}
					
		stmt = conn.createStatement ();
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'fmddth Month yyyy') LETTER_DATE  "+ //1
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'DD-MM-YYYY')  "+ //2
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'fmddth Mon.YYYY')  "+ //3
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+ //4
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'fmddth Mon.YYYY') "+ //5
			" FROM DUAL ");          
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			m_prev_date=rs.getString(2);	
			m_prev_month=rs.getString(3);	
			m_curr_date=rs.getString(4);	
			m_curr_month=rs.getString(5);	
			}
			rs.close();
			
  	  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill_sub_team(m_sub_team,m_date){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_sub_team&date=\"+m_date+\"&sub_team_id=\"+m_sub_team;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr >");
			//out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+m_Letter_date+" </u></td>"); 
			out.println("<td width=\"*%\" align=center  ><u><b>Monthly Collection Movement as at "+m_Letter_date+" </u></td>"); 
			out.println("</tr >");
			out.println("<tr >");
			out.println("<td width=\"*%\" align=center  ><u><b>Head Office Marketing </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		

		
			rs=stmt.executeQuery(
			" SELECT a.team_id,  "+ //1
			" "+m_schema_name+".af_co_get_emp_name(a.collection_officer) emp_name, "+ //2
			" a.finance_no, "+ //3
			" a.ent_user,  "+ //4
			" a.sub_team_id,  "+ //5
			" a.application_no, "+  //6
			" a.client_code, "+ //7
			" a.full_name,  "+ //8
			//" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //9
			" nvl(arr_amount,0)  arrears, "+
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //10
			//" CEIL(age) age,"+
			" round(age,2) age,"+
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //11
			//" a.total_amount_old-(nvl(a.settled_amount_old,0) + nvl(a.adjusted_amount_old,0)) arrears, "+ //12
			" nvl(arr_amount_old,0) arrears ,"+
			" (nvl(a.future_receivable_old,0) + nvl(a.arr_capital_portion_old,0) ) cap_out,  "+ //13
			//" ceil((NVL(a.total_rentals_old,0)- NVL(a.rentals_paid_old,0) )) age  ,"+ //14
			//" CEIL(age_OLD) age,"+
			" round(age_OLD,2) age,"+
			" a.collection_officer ,"+ //15
			" sub_team_desc "+ //16
			" ,agr_status,"+ //17
			" termi_status, "+//18
			" APPLICATION_STATUS "+ //19
//			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt_age a "+
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt_age a , "+m_schema_name+".AF_CO_MAS_SUB_TEAMS b "+
			" where a.sub_team_id=b.sub_team_id  "+
			
			" and a.team_id='"+m_team_id+"' "+
			" and b.active_status='Y' "+ //added by nuwan de silva on 12-10-2009
			" and A.TRANSACTION_TYPE <> 'HIRING' "+
			" AND a.ent_user='"+m_username+"' ORDER BY a.sub_team_id,A.collection_officer ");
			
		  boolean more2=rs.next();
			//int m_age=0,m_age_old=0;
			double m_age=0,m_age_old=0;
			int m_cum_value=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_6_count=0,m_age_legal_count=0,m_age_4_5_count=0;
			int m_age_0_count_old=0,m_age_1_count_old=0,m_age_2_count_old=0,m_age_3_count_old=0,m_age_4_count_old=0,m_age_6_count_old=0,m_age_legal_count_old=0,m_age_4_5_count_old=0;
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_6_m_amount=0,m_age_legal_m_amount=0;
			double m_amount_old=0,m_age_0_m_amount_old=0,m_age_1_m_amount_old=0,m_age_2_m_amount_old=0,m_age_3_m_amount_old=0,m_age_6_m_amount_old=0,m_age_legal_m_amount_old=0;
			double m_amount_cap=0;
			double m_amount_cap_old=0;
			double m_age_0_m_amount_cap=0,m_age_1_m_amount_cap=0,m_age_2_m_amount_cap=0,m_age_3_m_amount_cap=0,m_age_6_m_amount_cap=0,m_age_legal_m_amount_cap=0;
			double m_age_0_m_amount_cap_old=0,m_age_1_m_amount_cap_old=0,m_age_2_m_amount_cap_old=0,m_age_3_m_amount_cap_old=0,m_age_6_m_amount_cap_old=0,m_age_legal_m_amount_cap_old=0;
			double m_age_4_5_m_amount=0,m_age_4_5_m_amount_cap=0;
			double m_age_4_5_m_amount_old=0,m_age_4_5_m_amount_cap_old=0;
			String m_collector="",m_collector_name="";
			String m_app_status="";
			int m_total_count=0;
			int m_total_count_old=0;
			double m_age_tot_amount=0;
			double m_age_tot_amount_old=0;
			double m_tot_age_m_amount_cap=0;
			double m_tot_age_m_amount_cap_old=0;
			double m_rate_count_rent = 0;
			double m_rate_cap_rent   = 0; 
			double m_rate_cap_cap   = 0;
			
			int m_grand_count_total_month_0 = 0;
			int m_grand_count_total_month_1 = 0;
			int m_grand_count_total_month_2 = 0;
			int m_grand_count_total_month_3 = 0;
			int m_grand_count_total_month_4_5 = 0;			
			int m_grand_count_total_month_6 = 0;
			int m_grand_count_total_month_legal = 0;
			int m_grand_count_total = 0;
			double m_grand_amount_total=0,m_grand_amount_total_month_0=0,m_grand_amount_total_month_1=0,m_grand_amount_total_month_2=0,m_grand_amount_total_month_3=0,m_grand_amount_total_month_4_5=0,m_grand_amount_total_month_6=0,m_grand_amount_total_month_legal=0;
			double m_grand_amount_total_old=0,m_grand_amount_total_old_month_0=0,m_grand_amount_total_old_month_1=0,m_grand_amount_total_old_month_2=0,m_grand_amount_total_old_month_3=0,m_grand_amount_total_old_month_4_5=0,m_grand_amount_total_old_month_6=0,m_grand_amount_total_old_month_legal=0;
			int m_grand_count_total_old=0,m_grand_count_total_month_0_old=0,m_grand_count_total_month_1_old=0,m_grand_count_total_month_2_old=0,m_grand_count_total_month_3_old=0,m_grand_count_total_month_4_5_old=0,m_grand_count_total_month_6_old=0,m_grand_count_total_month_legal_old=0;
			double m_grand_count_total_rate=0,m_grand_amont_total_rate=0;
			double m_tot_age_grand_m_amount_cap=0,m_age_0_grand_m_amount_cap=0,m_age_1_grand_m_amount_cap=0,m_age_2_grand_m_amount_cap=0,m_age_3_grand_m_amount_cap=0,m_age_4_5_grand_m_amount_cap=0,m_age_6_grand_m_amount_cap=0,m_age_legal_grand_m_amount_cap=0;
			double m_grand_amount_tot_rate=0,m_tot_age_grand_m_amount_cap_old=0,m_age_0_grand_m_amount_cap_old=0,m_age_1_grand_m_amount_cap_old=0,m_age_2_grand_m_amount_cap_old=0,m_age_3_grand_m_amount_cap_old=0,m_age_4_5_grand_m_amount_cap_old=0,m_age_6_grand_m_amount_cap_old=0,m_age_legal_grand_m_amount_cap_old=0;
			String m_agr_status="";
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 					
			
			while(more2){
			m_collector=rs.getString(5);
			m_collector_name=rs.getString(16);
      m_age_0_count=0;
			m_age_0_m_amount=0;  
			m_age_0_m_amount_cap=0;
			m_age_1_count=0; 
			m_age_1_m_amount=0;  
			m_age_1_m_amount_cap=0;
			m_age_2_count=0;  
			m_age_2_m_amount=0; 
			m_age_2_m_amount_cap=0;
			m_age_3_count=0; 
			m_age_3_m_amount=0; 
			m_age_3_m_amount_cap=0;
			m_age_4_5_count=0;
			m_age_4_5_m_amount=0;
			m_age_4_5_m_amount_cap=0;
			m_age_6_count=0;
			m_age_6_m_amount=0;
			m_age_6_m_amount_cap=0;
			m_age_legal_count=0;
			m_age_legal_m_amount=0;
			m_age_legal_m_amount_cap=0;
			
			m_age_0_count_old=0;
			m_age_0_m_amount_old=0;  
			m_age_0_m_amount_cap_old=0;
			m_age_1_count_old=0; 
			m_age_1_m_amount_old=0;  
			m_age_1_m_amount_cap_old=0;
			m_age_2_count_old=0;  
			m_age_2_m_amount_old=0; 
			m_age_2_m_amount_cap_old=0;
			m_age_3_count_old=0; 
			m_age_3_m_amount_old=0; 
			m_age_3_m_amount_cap_old=0;
			m_age_4_5_count_old=0;
			m_age_4_5_m_amount_old=0;
			m_age_4_5_m_amount_cap_old=0;
			m_age_6_count_old=0;
			m_age_6_m_amount_old=0;
			m_age_6_m_amount_cap_old=0;
			
			m_age_legal_count_old=0;
			m_age_legal_m_amount_old=0;
			m_age_legal_m_amount_cap_old=0;
			
			m_total_count=0;
			m_age_tot_amount=0;
			m_total_count_old=0;
			m_age_tot_amount_old=0;
			m_tot_age_m_amount_cap=0;
			m_tot_age_m_amount_cap_old=0;
			
			//ADDED NS on 28-05-2009
      m_amount_old=0;
      m_amount=0;
			m_age_old=0;
			m_age=0;
			
			while(m_collector.equals(rs.getString(5))){
			m_age=rs.getDouble(11);
			m_amount=rs.getDouble(9);
			m_amount_cap=rs.getDouble(10);
			
			m_age_old=rs.getDouble(14);
			m_amount_old=rs.getDouble(12);
			m_amount_cap_old=rs.getDouble(13);
			m_agr_status=rs.getString(17);
			m_termi_status=rs.getString(18);
	    m_app_status=rs.getString(19);
		if(m_termi_status.equals("0")){
		
		  if (!m_app_status.equals("LEGAL")) { //Adde By Nuwan De Silva on 24-11-2009
		
			if ( m_age <=0 ){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount+=m_amount;
			m_age_0_m_amount_cap=m_age_0_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==1){
			else if (m_age>0 && m_age<=3){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount+=m_amount;
			m_age_1_m_amount_cap=m_age_1_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==2){
			else if (m_age>3 && m_age<6){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount+=m_amount;
			m_age_2_m_amount_cap=m_age_2_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==3){
			else if (m_age>=6 && m_age<12){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount+=m_amount;
			m_age_3_m_amount_cap=m_age_3_m_amount_cap+m_amount_cap;
			}
			//else if (m_age >3 && m_age <=5){
			else if (m_age>=12 && m_age<18){
			m_age_4_5_count=m_age_4_5_count+1;
			m_age_4_5_m_amount+=m_amount;
			m_age_4_5_m_amount_cap=m_age_4_5_m_amount_cap+m_amount_cap;
			}
			//else if(m_age>5 ){
			else if (m_age>=18){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount+=m_amount;
			m_age_6_m_amount_cap=m_age_6_m_amount_cap+m_amount_cap;
			}
			
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count=m_age_legal_count+1;
			m_age_legal_m_amount+=m_amount_cap;
			//m_age_legal_m_amount_cap=m_age_legal_m_amount_cap+m_amount_cap;
			m_age_legal_m_amount_cap=m_age_legal_m_amount_cap+m_amount_cap;
			}
			
			}
			
			if(m_agr_status.equals("OLD") && !m_termi_status.equals("2") && !m_termi_status.equals("3")){
			
			if (!m_app_status.equals("LEGAL")) { //Adde By Nuwan De Silva on 24-11-2009
			
			if ( m_age_old <=0 ){
			m_age_0_count_old=m_age_0_count_old+1;
			m_age_0_m_amount_old+=m_amount_old;
			m_age_0_m_amount_cap_old=m_age_0_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==1){
			else if (m_age_old>0 && m_age_old<=3){
			m_age_1_count_old=m_age_1_count_old+1;
			m_age_1_m_amount_old+=m_amount_old;
			m_age_1_m_amount_cap_old=m_age_1_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==2){
			else if (m_age_old>3 && m_age_old<6){
			m_age_2_count_old=m_age_2_count_old+1;
			m_age_2_m_amount_old+=m_amount_old;
			m_age_2_m_amount_cap_old=m_age_2_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==3){
			else if (m_age_old>=6 && m_age_old<12){
			m_age_3_count_old=m_age_3_count_old+1;
			m_age_3_m_amount_old+=m_amount_old;
			m_age_3_m_amount_cap_old=m_age_3_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old >3 && m_age_old <=5){
			else if (m_age_old>=12 && m_age_old<18){
			m_age_4_5_count_old=m_age_4_5_count_old+1;
			m_age_4_5_m_amount_old+=m_amount_old;
			m_age_4_5_m_amount_cap_old=m_age_4_5_m_amount_cap_old+m_amount_cap_old;
			}
			//else if(m_age_old>5 ){
			else if (m_age_old>=18){
		  m_age_6_count_old=m_age_6_count_old+1;
			m_age_6_m_amount_old+=m_amount_old;
			m_age_6_m_amount_cap_old=m_age_6_m_amount_cap_old+m_amount_cap_old;
			}
			
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count_old=m_age_legal_count_old+1;
			m_age_legal_m_amount_old+=m_amount_cap_old;
			//m_age_legal_m_amount_cap_old=m_age_legal_m_amount_cap_old+m_amount_cap_old;
			m_age_legal_m_amount_cap_old=m_age_legal_m_amount_cap_old+m_amount_cap_old;
			}
			
			}

			more2=rs.next();
			if(!more2){break;}
			}
						
			//out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header ------------------------------------------------------- <tr>
			//t.println("</tr>");
      //t.println("<td colspan='9' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");//<b> Figures As @  "+m_Letter_date+"
      //t.println("</tr>");
			out.println("<tr  ><td width='18%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Sub Team</td>");
			out.println("<td width='8%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Date</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 - Below </td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0.01 - 3.00</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3.01 - 5.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6.00 - 11.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>12.00 - 17.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>18.00 - Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");	
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total</td>");
			out.println("<td width='12%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("</tr>");
			
			m_total_count=m_age_0_count+m_age_1_count+m_age_2_count+m_age_3_count+m_age_4_5_count+m_age_6_count+m_age_legal_count;
			m_age_tot_amount=m_age_0_m_amount+m_age_1_m_amount+m_age_2_m_amount+m_age_3_m_amount+m_age_4_5_m_amount+m_age_6_m_amount+m_age_legal_m_amount;
			
			m_total_count_old=m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old+m_age_legal_count_old;
			m_age_tot_amount_old=m_age_0_m_amount_old+m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old+m_age_legal_m_amount_old;
      m_rate_count_rent = (m_total_count_old-m_total_count)/m_total_count_old*100;
			m_rate_cap_rent = (m_age_tot_amount_old-m_age_tot_amount)/m_age_tot_amount_old*100;
			out.println("<tr>");//Added By Sandun on 23-03-2009
			out.println("<tr ><td width='18%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body'>"+m_collector_name+"</td>");
			out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Rental Movement</td>");
			out.println("</tr>");	
			
			
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' style=\"{cursor:hand;}\" class='rep-body' align='left'>&nbsp;</td>"); //onClick=\"show_employee_drill('"+m_collector+"')\"
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_count_rent)+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_0_m_amount)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount)+"</u></td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf.format(m_age_tot_amount)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_cap_rent)+"</td>");
			out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center' bgcolor='#90EE90'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center' bgcolor='#90EE90'>&nbsp;</td>");
		  out.println("</tr>");
			//double m_rate_count = 0;
			//m_total_count_old=m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old;
			//m_age_tot_amount_old=m_age_0_m_amount_old+m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old;
     //m_rate_count = (m_total_count_old-m_total_count)/m_total_count_old;

			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count_old+"</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90'>"+m_total_count_old+"</td>");
      out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+nf.format(m_age_0_m_amount_old)+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_old)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_old)+"</u></td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' class='rep-body'  bgcolor='#90EE90' >"+nf.format(m_age_tot_amount_old)+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			/*out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");		*/
			
			//Capital Out Standing --------------------------------------------------------------------------------------------------
			/*out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");//class=pdn_txtpos2
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 Month</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O.</td>");
			out.println("</tr>");
			*/
			
		  //m_tot_age_m_amount_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap;
		  //-----
			m_tot_age_m_amount_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap+m_age_legal_m_amount_cap;
      m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old+m_age_legal_m_amount_cap_old;
			
			
			m_rate_cap_cap = (m_tot_age_m_amount_cap_old-m_tot_age_m_amount_cap)/m_tot_age_m_amount_cap_old*100;
			
			out.println("<tr>");//Added By Sandun on 23-03-2009
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body'>&nbsp;</td>");
			out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Capital Movement</td>");
			out.println("</tr>");	
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}'  class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90'>"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_count_rent)+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+nf.format(m_age_0_m_amount_cap)+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_cap)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_cap)+"</u></td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf.format(m_tot_age_m_amount_cap)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_cap_cap)+"</td>");
			out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center' bgcolor='#90EE90'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center' bgcolor='#90EE90'>&nbsp;</td>");
		  out.println("</tr>");			
		  //m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;

			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count_old+"</td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'bgcolor='#90EE90'  >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'>"+nf.format(m_age_0_m_amount_cap_old)+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_cap_old)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_sub_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_cap_old)+"</u></td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90'  >"+nf.format(m_tot_age_m_amount_cap_old)+"</td>");
			out.println("<td width='102%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td bgcolor='lightgrey' width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("</tr>");		
			//out.println("</table>");
			
			//-----------------------Total Rental Movement
			m_grand_count_total_month_0   += m_age_0_count;
			m_grand_count_total_month_1   += m_age_1_count;
			m_grand_count_total_month_2   += m_age_2_count;
			m_grand_count_total_month_3   += m_age_3_count;
			m_grand_count_total_month_4_5 += m_age_4_5_count;
			m_grand_count_total_month_6   += m_age_6_count;
			m_grand_count_total_month_legal   += m_age_legal_count;
			
			m_grand_count_total_month_0_old    +=m_age_0_count_old;
			m_grand_count_total_month_1_old    +=m_age_1_count_old;
			m_grand_count_total_month_2_old    +=m_age_2_count_old;
			m_grand_count_total_month_3_old    +=m_age_3_count_old;
			m_grand_count_total_month_4_5_old  +=m_age_4_5_count_old;
			m_grand_count_total_month_6_old    +=m_age_6_count_old;
			m_grand_count_total_month_legal_old    +=m_age_legal_count_old;
			
			m_grand_count_total     = m_grand_count_total_month_0+m_grand_count_total_month_1+m_grand_count_total_month_2+m_grand_count_total_month_3+m_grand_count_total_month_4_5+m_grand_count_total_month_6+m_grand_count_total_month_legal;
			m_grand_count_total_old = m_grand_count_total_month_0_old + m_grand_count_total_month_1_old + m_grand_count_total_month_2_old+ m_grand_count_total_month_3_old + m_grand_count_total_month_4_5_old+m_grand_count_total_month_6_old+m_grand_count_total_month_legal_old;
			
						
			m_grand_amount_total_month_0   += m_age_0_m_amount;
			m_grand_amount_total_month_1   += m_age_1_m_amount;
			m_grand_amount_total_month_2   += m_age_2_m_amount;
			m_grand_amount_total_month_3   += m_age_3_m_amount;
			m_grand_amount_total_month_4_5 += m_age_4_5_m_amount;
			m_grand_amount_total_month_6   += m_age_6_m_amount;
			m_grand_amount_total_month_legal   += m_age_legal_m_amount;
			
			m_grand_amount_total_old_month_0   += m_age_0_m_amount_old;
			m_grand_amount_total_old_month_1   += m_age_1_m_amount_old;
			m_grand_amount_total_old_month_2   += m_age_2_m_amount_old;
			m_grand_amount_total_old_month_3   += m_age_3_m_amount_old;
			m_grand_amount_total_old_month_4_5 += m_age_4_5_m_amount_old;
			m_grand_amount_total_old_month_6   += m_age_6_m_amount_old;
			m_grand_amount_total_old_month_legal   += m_age_legal_m_amount_old;
			
			m_grand_amount_total_old = m_grand_amount_total_old_month_0+m_grand_amount_total_old_month_1+m_grand_amount_total_old_month_2+m_grand_amount_total_old_month_3+m_grand_amount_total_old_month_4_5+m_grand_amount_total_old_month_6+m_grand_amount_total_old_month_legal;
			m_grand_amount_total     = m_grand_amount_total_month_0+m_grand_amount_total_month_1+m_grand_amount_total_month_2+m_grand_amount_total_month_3+m_grand_amount_total_month_4_5+m_grand_amount_total_month_6+m_grand_amount_total_month_legal;
			
			m_grand_count_total_rate = (m_grand_count_total_old-m_grand_count_total)/m_grand_count_total_old*100;
			m_grand_amont_total_rate = (m_grand_amount_total_old-m_grand_amount_total)/m_grand_amount_total_old*100;
			
			//----------------Capital Movement
					
			m_age_0_grand_m_amount_cap   += m_age_0_m_amount_cap;
			m_age_1_grand_m_amount_cap   += m_age_1_m_amount_cap;
			m_age_2_grand_m_amount_cap   += m_age_2_m_amount_cap;
			m_age_3_grand_m_amount_cap   += m_age_3_m_amount_cap;
			m_age_4_5_grand_m_amount_cap += m_age_4_5_m_amount_cap;
			m_age_6_grand_m_amount_cap   += m_age_6_m_amount_cap;
			m_age_legal_grand_m_amount_cap   += m_age_legal_m_amount_cap;
			
			
			m_age_0_grand_m_amount_cap_old   += m_age_0_m_amount_cap_old;
			m_age_1_grand_m_amount_cap_old   += m_age_1_m_amount_cap_old;
			m_age_2_grand_m_amount_cap_old   += m_age_2_m_amount_cap_old;
			m_age_3_grand_m_amount_cap_old   += m_age_3_m_amount_cap_old;
			m_age_4_5_grand_m_amount_cap_old += m_age_4_5_m_amount_cap_old;
			m_age_6_grand_m_amount_cap_old   += m_age_6_m_amount_cap_old;
			m_age_legal_grand_m_amount_cap_old   += m_age_legal_m_amount_cap_old;
			
			m_tot_age_grand_m_amount_cap     = m_age_0_grand_m_amount_cap+m_age_1_grand_m_amount_cap+m_age_2_grand_m_amount_cap+m_age_3_grand_m_amount_cap+m_age_4_5_grand_m_amount_cap+m_age_6_grand_m_amount_cap+m_age_legal_grand_m_amount_cap;
			m_tot_age_grand_m_amount_cap_old = m_age_0_grand_m_amount_cap_old+m_age_1_grand_m_amount_cap_old+m_age_2_grand_m_amount_cap_old+m_age_3_grand_m_amount_cap_old+m_age_4_5_grand_m_amount_cap_old+m_age_6_grand_m_amount_cap_old+m_age_legal_grand_m_amount_cap_old;
			
			m_grand_amount_tot_rate = (m_tot_age_grand_m_amount_cap_old-m_tot_age_grand_m_amount_cap)/m_tot_age_grand_m_amount_cap_old*100;
			
			
			
			
			}
			out.println("</table>");
			
			//---------------Added by Sandun on 29-04-2009-------------------------------------------------------------------------------------------------
        out.println("<br><br>");
        out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 	
				out.println("<tr><td width=100% bgcolor='lightgrey' align='center' colspan=11 STYLE='{font: 10pt  arial;}' class='rep-body'><b>Total</td></tr>");
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>Date</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 - Below</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0.01 - 3.00</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3.01 - 5.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6.00 - 11.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>12.00 - 17.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>18.00 - Above</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
				out.println("</tr>");
				
				out.println("<tr>");
			  out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' bgcolor='#90EE90'>Total</td>");
			  out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Rental Movement</td>");
			  out.println("</tr>");	
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_curr_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_count_total_rate)+"</td>");
				out.println("</tr>");
				
				
			
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_0)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_1)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_2)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_3)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_4_5)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_6)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_month_legal)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_amont_total_rate)+"</td>");
				out.println("</tr>");
				
				out.println("<tr bgcolor='#90EE90'>");
				out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  align='center'>&nbsp;</td>");
				out.println("</tr>");
				
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_prev_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6_old+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal_old+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_0)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_1)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_2)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_3)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_4_5)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_6)+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_legal)+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
			  out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' bgcolor='#90EE90'>&nbsp;</td>");
			  out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Capital Movement</td>");
			  out.println("</tr>");	
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_curr_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_count_total_rate)+"</td>");
				out.println("</tr>");
				
				
			
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_0_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_1_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_2_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_3_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_4_5_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_6_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_legal_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_tot_age_grand_m_amount_cap)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_amount_tot_rate)+"</td>");
				out.println("</tr>");
				
				out.println("<tr bgcolor='#90EE90'>");
				out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  align='center'>&nbsp;</td>");
				out.println("</tr>");
				
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_prev_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6_old+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal_old+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_0_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_1_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_2_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_3_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_4_5_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_6_grand_m_amount_cap_old)+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_legal_grand_m_amount_cap_old)+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_tot_age_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr >");
				out.println("<td bgcolor='lightgrey' colspan=11 width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("</tr>");		
			
				
				out.println("</table>");
			  out.println("<br><br>");
     //---------------------------end----------------------------------------------------------------------------------------------------------------
			

			

			
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
					
		
}

   else if(m_chksql.equals("print_report_all")){		
		
		String m_date="";
	  String m_team_id="";
	  String m_officer="";
		String m_sub_team_id="";
	  String m_prev_month="",m_curr_month="",m_curr_date="",m_Letter_date="",m_prev_date="",m_termi_status="";

		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		stmt = conn.createStatement ();
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'fmddth Month yyyy') LETTER_DATE  "+ //1
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'DD-MM-YYYY')  "+ //2
			", TO_CHAR(ADD_MONTHS(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),-1),'fmddth Mon.YYYY')  "+ //3
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MM-YYYY') "+ //4
			", TO_CHAR(/*LAST_DAY*/(TO_DATE('"+m_date+"','DD-MM-YYYY')),'fmddth Mon.YYYY') "+ //5
			" FROM DUAL ");          
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			m_prev_date=rs.getString(2);	
			m_prev_month=rs.getString(3);	
			m_curr_date=rs.getString(4);	
			m_curr_month=rs.getString(5);	
			}
			rs.close();
			
  	  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Collection Movement Report - With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("function show_movement_drill_team(m_team_id,m_date){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=print_report_team&date=\"+m_date+\"&team_id=\"+m_team_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			//Added By Nuwan De Silva 
			out.println("function show_total_capital_drill(m_date,m_type){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=drill_down_total_capital&date=\"+m_date+\"&type=\"+m_type;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			//Added By Nuwan De Silva 
			out.println("function show_total_arr_drill(m_date,m_type){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=drill_down_total_arr&date=\"+m_date+\"&type=\"+m_type;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			
			out.println("function show_total_arr_age_drill(m_type,m_age){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Movement_Report_Age_New_1?chksql=drill_down_details_total&age=\"+m_age+\"&type=\"+m_type;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			
			
			
			
			
			
						
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr >");
			out.println("<td width=\"*%\" align=center  ><b><u>Monthly Collection Movement as at "+m_Letter_date+" </u></td>"); 
			out.println("</tr >");
			out.println("<tr >");
			out.println("<td width=\"*%\" align=center  ><b><u>Head Office Marketing</u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		

		
			rs=stmt.executeQuery(
			" SELECT a.team_id,  "+ //1
			" "+m_schema_name+".af_co_get_emp_name(a.collection_officer) emp_name, "+ //2
			" a.finance_no, "+ //3
			" a.ent_user,  "+ //4
			" a.sub_team_id,  "+ //5
			" a.application_no, "+  //6
			" a.client_code, "+ //7
			" a.full_name,  "+ //8
			//" a.total_amount-(nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) arrears, "+ //9
			" nvl(arr_amount,0)  arrears, "+
			" (nvl(a.future_receivable,0) + nvl(a.arr_capital_portion,0) ) cap_out,  "+ //10
			//" ceil((NVL(a.total_rentals,0)- NVL(a.rentals_paid,0) )) age ,"+ //11
			//" CEIL(age) age,"+
			" round(age,2) age,"+
			//" a.total_amount_old-(nvl(a.settled_amount_old,0) + nvl(a.adjusted_amount_old,0)) arrears, "+ //12
			" nvl(arr_amount_old,0) arrears ,"+
			" (nvl(a.future_receivable_old,0) + nvl(a.arr_capital_portion_old,0) ) cap_out,  "+ //13
			//" ceil((NVL(a.total_rentals_old,0)- NVL(a.rentals_paid_old,0) )) age  ,"+ //14
			//" CEIL(age_OLD) age,"+
			" round(age_OLD,2) age,"+
			" a.collection_officer ,"+ //15
			" team_desc "+ //16
			" ,agr_status,"+ //17
			" termi_status, "+//18
			" APPLICATION_STATUS "+ //19
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt_age a , "+m_schema_name+".AF_CO_MAS_TEAMS b "+
			" where a.team_id=b.team_id  "+
			//" and B.DIVISION_CODE='AF' "+
			" and B.ACTIVE_STATUS='Y' "+
			" and A.TRANSACTION_TYPE <> 'HIRING' "+
			" AND a.ent_user='"+m_username+"' "+
			" AND termi_status IN ('0','1') "+ //added by ns on 07-10-2011
			" ORDER BY a.team_id,a.sub_team_id,A.collection_officer ");
			
		  boolean more2=rs.next();
			//int m_age=0,m_age_old=0;
			double m_age=0,m_age_old=0;
			int m_cum_value=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_6_count=0,m_age_4_5_count=0,m_age_legal_count=0;
			int m_age_0_count_old=0,m_age_1_count_old=0,m_age_2_count_old=0,m_age_3_count_old=0,m_age_4_count_old=0,m_age_6_count_old=0,m_age_legal_count_old=0,m_age_4_5_count_old=0;
			
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_6_m_amount=0;
			double m_amount_old=0,m_age_0_m_amount_old=0,m_age_1_m_amount_old=0,m_age_2_m_amount_old=0,m_age_3_m_amount_old=0,m_age_6_m_amount_old=0;
			double m_amount_cap=0;
			double m_amount_cap_old=0;
			double m_age_0_m_amount_cap=0,m_age_1_m_amount_cap=0,m_age_2_m_amount_cap=0,m_age_3_m_amount_cap=0,m_age_6_m_amount_cap=0;
			double m_age_0_m_amount_cap_old=0,m_age_1_m_amount_cap_old=0,m_age_2_m_amount_cap_old=0,m_age_3_m_amount_cap_old=0,m_age_6_m_amount_cap_old=0;
			
			//double m_age_legal_count=0;
			double m_age_legal_m_amount=0;
			double m_age_legal_m_amount_cap=0;
			//double m_age_legal_count_old=0;
			double m_age_legal_m_amount_old=0;
			double m_age_legal_m_amount_cap_old=0;
			
			
			double m_age_4_5_m_amount=0,m_age_4_5_m_amount_cap=0;
			double m_age_4_5_m_amount_old=0,m_age_4_5_m_amount_cap_old=0;
			String m_collector="",m_collector_name="";
			int m_total_count=0;
			int m_total_count_old=0;
			double m_age_tot_amount=0;
			double m_age_tot_amount_old=0;
			double m_tot_age_m_amount_cap=0;
			double m_tot_age_m_amount_cap_old=0;
			double m_rate_count_rent = 0;
			double m_rate_cap_rent   = 0; 
			double m_rate_cap_cap   = 0;
			
			int m_grand_count_total_month_0 = 0;
			int m_grand_count_total_month_1 = 0;
			int m_grand_count_total_month_2 = 0;
			int m_grand_count_total_month_3 = 0;
			int m_grand_count_total_month_4_5 = 0;			
			int m_grand_count_total_month_6 = 0;
			int m_grand_count_total_month_legal = 0;
			int m_grand_count_total = 0;
			
			double m_grand_amount_total=0,m_grand_amount_total_month_0=0,m_grand_amount_total_month_1=0,m_grand_amount_total_month_2=0,m_grand_amount_total_month_3=0,m_grand_amount_total_month_4_5=0,m_grand_amount_total_month_6=0,m_grand_amount_total_month_legal=0;
			double m_grand_amount_total_old=0,m_grand_amount_total_old_month_0=0,m_grand_amount_total_old_month_1=0,m_grand_amount_total_old_month_2=0,m_grand_amount_total_old_month_3=0,m_grand_amount_total_old_month_4_5=0,m_grand_amount_total_old_month_6=0,m_grand_amount_total_old_month_legal=0;
			int m_grand_count_total_old=0,m_grand_count_total_month_0_old=0,m_grand_count_total_month_1_old=0,m_grand_count_total_month_2_old=0,m_grand_count_total_month_3_old=0,m_grand_count_total_month_4_5_old=0,m_grand_count_total_month_6_old=0,m_grand_count_total_month_legal_old=0;
			double m_grand_count_total_rate=0,m_grand_amont_total_rate=0;
			double m_tot_age_grand_m_amount_cap=0,m_age_0_grand_m_amount_cap=0,m_age_1_grand_m_amount_cap=0,m_age_2_grand_m_amount_cap=0,m_age_3_grand_m_amount_cap=0,m_age_4_5_grand_m_amount_cap=0,m_age_6_grand_m_amount_cap=0,m_age_legal_grand_m_amount_cap=0;
			double m_grand_amount_tot_rate=0,m_tot_age_grand_m_amount_cap_old=0,m_age_0_grand_m_amount_cap_old=0,m_age_1_grand_m_amount_cap_old=0,m_age_2_grand_m_amount_cap_old=0,m_age_3_grand_m_amount_cap_old=0,m_age_4_5_grand_m_amount_cap_old=0,m_age_6_grand_m_amount_cap_old=0,m_age_legal_grand_m_amount_cap_old=0;
			String m_agr_status="";
			String m_app_status="";
			out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 					
			
			while(more2){
			m_collector=rs.getString(1);
			m_collector_name=rs.getString(16);
			
      m_age_0_count=0;
			m_age_0_m_amount=0;  
			m_age_0_m_amount_cap=0;
			m_age_1_count=0; 
			m_age_1_m_amount=0;  
			m_age_1_m_amount_cap=0;
			m_age_2_count=0;  
			m_age_2_m_amount=0; 
			m_age_2_m_amount_cap=0;
			m_age_3_count=0; 
			m_age_3_m_amount=0; 
			m_age_3_m_amount_cap=0;
			m_age_4_5_count=0;
			m_age_4_5_m_amount=0;
			m_age_4_5_m_amount_cap=0;
			m_age_6_count=0;
			m_age_6_m_amount=0;
			m_age_6_m_amount_cap=0;
			
			m_age_0_count_old=0;
			m_age_0_m_amount_old=0;  
			m_age_0_m_amount_cap_old=0;
			m_age_1_count_old=0; 
			m_age_1_m_amount_old=0;  
			m_age_1_m_amount_cap_old=0;
			m_age_2_count_old=0;  
			m_age_2_m_amount_old=0; 
			m_age_2_m_amount_cap_old=0;
			m_age_3_count_old=0; 
			m_age_3_m_amount_old=0; 
			m_age_3_m_amount_cap_old=0;
			m_age_4_5_count_old=0;
			m_age_4_5_m_amount_old=0;
			m_age_4_5_m_amount_cap_old=0;
			m_age_6_count_old=0;
			m_age_6_m_amount_old=0;
			m_age_6_m_amount_cap_old=0;
			
			
			m_age_legal_count=0;
			m_age_legal_m_amount=0;
			m_age_legal_m_amount_cap=0;
			m_age_legal_count_old=0;
			m_age_legal_m_amount_old=0;
			m_age_legal_m_amount_cap_old=0;
			
						
			m_total_count=0;
			m_age_tot_amount=0;
			m_total_count_old=0;
			m_age_tot_amount_old=0;
			m_tot_age_m_amount_cap=0;
			m_tot_age_m_amount_cap_old=0;
			
			//ADDED NS on 28-05-2009
      m_amount_old=0;
      m_amount=0;
			m_age_old=0;
			m_age=0;
			
			while(m_collector.equals(rs.getString(1))){
			m_age=rs.getDouble(11);
			m_amount=rs.getDouble(9);
			m_amount_cap=rs.getDouble(10);
			
			m_age_old=rs.getDouble(14);
			m_amount_old=rs.getDouble(12);
			m_amount_cap_old=rs.getDouble(13);
			
			m_agr_status=rs.getString(17); //added by nuwan de silva
     	m_termi_status = rs.getString(18);//Added BY SAndun on 15-10-2009
			m_app_status   = rs.getString(19);//Added BY Nuwan De Silva
				
	    if(m_termi_status.equals("0")){
			
			if (!m_app_status.equals("LEGAL")) { //Adde By Nuwan De Silva on 24-11-2009
			
			if ( m_age <=0 ){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount+=m_amount;
			m_age_0_m_amount_cap=m_age_0_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==1){
			else if (m_age>0 && m_age<=3){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount+=m_amount;
			m_age_1_m_amount_cap=m_age_1_m_amount_cap+m_amount_cap;
			
			}
			//else if (m_age ==2){
			else if (m_age>3 && m_age<6){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount+=m_amount;
			m_age_2_m_amount_cap=m_age_2_m_amount_cap+m_amount_cap;
			}
			//else if (m_age ==3){
			else if (m_age>=6 && m_age<12){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount+=m_amount;
			m_age_3_m_amount_cap=m_age_3_m_amount_cap+m_amount_cap;
			}
			//else if (m_age >3 && m_age <=5){
			else if (m_age>=12 && m_age<18){
			m_age_4_5_count=m_age_4_5_count+1;
			m_age_4_5_m_amount+=m_amount;
			m_age_4_5_m_amount_cap=m_age_4_5_m_amount_cap+m_amount_cap;
			}
		//	else if(m_age>5 ){
			else if(m_age>=18 ){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount+=m_amount;
			m_age_6_m_amount_cap=m_age_6_m_amount_cap+m_amount_cap;
			}
			
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count=m_age_legal_count+1;
			m_age_legal_m_amount+=m_amount_cap;
			//m_age_legal_m_amount_cap=m_age_legal_m_amount_cap+m_amount_cap;
			m_age_legal_m_amount_cap=m_age_legal_m_amount_cap+m_amount_cap;
			}
						
			}
			
			if(m_agr_status.equals("OLD") && !m_termi_status.equals("2") && !m_termi_status.equals("3")){
			
			if (!m_app_status.equals("LEGAL")) {
			
			if ( m_age_old <=0 ){
			m_age_0_count_old=m_age_0_count_old+1;
			m_age_0_m_amount_old+=m_amount_old;
			m_age_0_m_amount_cap_old=m_age_0_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==1){
			else if (m_age_old>0 && m_age_old<=3){
			m_age_1_count_old=m_age_1_count_old+1;
			m_age_1_m_amount_old+=m_amount_old;
			m_age_1_m_amount_cap_old=m_age_1_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==2){
			else if (m_age_old>3 && m_age_old<6){
			m_age_2_count_old=m_age_2_count_old+1;
			m_age_2_m_amount_old+=m_amount_old;
			m_age_2_m_amount_cap_old=m_age_2_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old ==3){
			else if (m_age_old>=6 && m_age_old<12){
			m_age_3_count_old=m_age_3_count_old+1;
			m_age_3_m_amount_old+=m_amount_old;
			m_age_3_m_amount_cap_old=m_age_3_m_amount_cap_old+m_amount_cap_old;
			}
			//else if (m_age_old >3 && m_age_old <=5){
			else if (m_age_old>=12 && m_age_old<18){
			m_age_4_5_count_old=m_age_4_5_count_old+1;
			m_age_4_5_m_amount_old+=m_amount_old;
			m_age_4_5_m_amount_cap_old=m_age_4_5_m_amount_cap_old+m_amount_cap_old;
			}
			//else if(m_age_old>5 ){
			else if(m_age_old>=18 ){
		  m_age_6_count_old=m_age_6_count_old+1;
			m_age_6_m_amount_old+=m_amount_old;
			m_age_6_m_amount_cap_old=m_age_6_m_amount_cap_old+m_amount_cap_old;
			}
			
			//added the legal count ns 24-11-2009
			}	else if (m_app_status.equals("LEGAL")) {
			m_age_legal_count_old=m_age_legal_count_old+1;
			m_age_legal_m_amount_old+=m_amount_cap_old;
			//m_age_legal_m_amount_cap_old=m_age_legal_m_amount_cap_old+m_amount_cap_old;
			m_age_legal_m_amount_cap_old=m_age_legal_m_amount_cap_old+m_amount_cap_old;
			}
			
      }
			
			more2=rs.next();
			if(!more2){break;}
			}
						
			//-------report header ------------------------------------------------------- <tr>
			/*out.println("</tr>");
      out.println("<td colspan='9' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");//<b> Figures As @  "+m_Letter_date+"
      out.println("</tr>");
			*/
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Team</td>");//bgcolor='lightgrey'
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>Date</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 - Below </td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0.01 - 3.00</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3.01 - 5.99</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6.00 - 11.99</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>12.00 - 17.99</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>18.00 - Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total</td>");//Without Zero
			
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
			out.println("</tr>");
			
			m_total_count=m_age_0_count+m_age_1_count+m_age_2_count+m_age_3_count+m_age_4_5_count+m_age_6_count+m_age_legal_count;
			m_age_tot_amount=m_age_0_m_amount+m_age_1_m_amount+m_age_2_m_amount+m_age_3_m_amount+m_age_4_5_m_amount+m_age_6_m_amount+m_age_legal_m_amount;
			m_total_count_old=m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old+m_age_legal_count_old;
			m_age_tot_amount_old=m_age_0_m_amount_old+m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old+m_age_legal_m_amount_old;


			m_rate_count_rent = (m_total_count_old-m_total_count)/m_total_count_old*100;
			m_rate_cap_rent   = (m_age_tot_amount_old-m_age_tot_amount)/m_age_tot_amount_old*100;
			out.println("<tr>");//Added By Sandun on 23-03-2009
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body'>"+m_collector_name+"</td>");
			out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Rental Movement</td>");
			out.println("</tr>");			
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' style=\"{cursor:hand;}\" class='rep-body' align='left'><u>&nbsp;<u></td>"); //onClick=\"show_employee_drill('"+m_collector+"')\"
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>"); //legal
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90'>"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_count_rent)+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_0_m_amount)+"</u></td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_age_tot_amount)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\"  ><u>"+nf.format(m_age_1_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\"  ><u>"+nf.format(m_age_2_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\"  ><u>"+nf.format(m_age_3_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\"  ><u>"+nf.format(m_age_4_5_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\"  ><u>"+nf.format(m_age_6_m_amount)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' style=\"{cursor:hand;}\" onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\"  ><u>"+nf.format(m_age_legal_m_amount)+"</u></td>"); //legal
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  bgcolor='#90EE90'>"+nf.format(m_age_tot_amount)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_cap_rent)+"</td>");
			out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>"); //legal
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center' bgcolor='#90EE90'>&nbsp;</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//m_total_count_old=m_age_0_count_old+m_age_1_count_old+m_age_2_count_old+m_age_3_count_old+m_age_4_5_count_old+m_age_6_count_old;
			//m_age_tot_amount_old=m_age_0_m_amount_old+m_age_1_m_amount_old+m_age_2_m_amount_old+m_age_3_m_amount_old+m_age_4_5_m_amount_old+m_age_6_m_amount_old;


			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>"); //legal
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand; }' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'><u>"+nf.format(m_age_0_m_amount_old)+"</u></td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_age_tot_amount_old)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_1_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_old)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_old)+"</u></td>"); //legal
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' class='rep-body' bgcolor='#90EE90'  >"+nf.format(m_age_tot_amount_old)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			/*out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			*/
			//Capital Out Standing --------------------------------------------------------------------------------------------------
			/*out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");//class=pdn_txtpos2
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 Month</td>");
			//out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O.</td>");// Without Zero
			out.println("</tr>");
			*/
			
			out.println("<tr>");//Added By Sandun on 23-03-2009
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body'></td>");
			out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=10 bgcolor='#FFFFCC' ><b>Capital Movement</td>");
			out.println("</tr>");
					
			
			
		  //m_tot_age_m_amount_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap;
      m_tot_age_m_amount_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_4_5_m_amount_cap+m_age_6_m_amount_cap+m_age_legal_m_amount_cap;
      m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old+m_age_legal_m_amount_cap_old;
			
			m_rate_cap_cap = (m_tot_age_m_amount_cap_old-m_tot_age_m_amount_cap)/m_tot_age_m_amount_cap_old*100;
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}'  class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count+"</td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count+"</td>"); //legal
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_count_rent)+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'><u>"+nf.format(m_age_0_m_amount_cap)+"</u></td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_tot_age_m_amount_cap)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_1_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_2_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_3_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_4_5_m_amount_cap)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_6_m_amount_cap)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body' ><u>"+nf.format(m_age_legal_m_amount_cap)+"</u></td>");
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf.format(m_tot_age_m_amount_cap)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+nf1.format(m_rate_cap_cap)+"</td>");
			out.println("</tr>");
			
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");//legal
			
			out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center' bgcolor='#90EE90' >&nbsp;</td>");
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");			
		  //m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;
      //m_tot_age_m_amount_cap_old=m_age_0_m_amount_cap_old+m_age_1_m_amount_cap_old+m_age_2_m_amount_cap_old+m_age_3_m_amount_cap_old+m_age_4_5_m_amount_cap_old+m_age_6_m_amount_cap_old;

			//Previous Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_0_count_old+"</td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_1_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_2_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_3_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_4_5_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_6_count_old+"</td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+m_age_legal_count_old+"</td>"); //legal
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >"+m_total_count_old+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+nf.format(m_age_0_m_amount_cap_old)+"</td>");
			//out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(m_tot_age_m_amount_cap_old)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_1_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_2_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_3_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_4_5_m_amount_cap_old)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_6_m_amount_cap_old)+"</u></td>");
			
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right; cursor:hand;}' onClick=\"show_movement_drill_team('"+m_collector+"','"+m_date+"')\" class='rep-body'  ><u>"+nf.format(m_age_legal_m_amount_cap_old)+"</u></td>"); //legal
			
		  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  bgcolor='#90EE90' >"+nf.format(m_tot_age_m_amount_cap_old)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#90EE90' >&nbsp;</td>");
			out.println("</tr>");
			
			//End Previous Month ---------------------------------------------------------------------------
			out.println("<tr >");
			out.println("<td bgcolor='lightgrey' width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>"); //legal
			
			out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("<td bgcolor='lightgrey' width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("</tr>");		
			//out.println("</table>");
			
			//-----------------------Total Rental Movement
			m_grand_count_total_month_0   += m_age_0_count;
			m_grand_count_total_month_1   += m_age_1_count;
			m_grand_count_total_month_2   += m_age_2_count;
			m_grand_count_total_month_3   += m_age_3_count;
			m_grand_count_total_month_4_5 += m_age_4_5_count;
			m_grand_count_total_month_6   += m_age_6_count;
			m_grand_count_total_month_legal   += m_age_legal_count;
			
			
			
			m_grand_count_total_month_0_old    +=m_age_0_count_old;
			m_grand_count_total_month_1_old    +=m_age_1_count_old;
			m_grand_count_total_month_2_old    +=m_age_2_count_old;
			m_grand_count_total_month_3_old    +=m_age_3_count_old;
			m_grand_count_total_month_4_5_old  +=m_age_4_5_count_old;
			m_grand_count_total_month_6_old    +=m_age_6_count_old;
			
			m_grand_count_total_month_legal_old    +=m_age_legal_count_old;
			
									
			m_grand_count_total     = m_grand_count_total_month_0+m_grand_count_total_month_1+m_grand_count_total_month_2+m_grand_count_total_month_3+m_grand_count_total_month_4_5+m_grand_count_total_month_6+m_grand_count_total_month_legal;
			m_grand_count_total_old = m_grand_count_total_month_0_old + m_grand_count_total_month_1_old + m_grand_count_total_month_2_old+ m_grand_count_total_month_3_old + m_grand_count_total_month_4_5_old+m_grand_count_total_month_6_old+m_grand_count_total_month_legal_old;
			
						
			m_grand_amount_total_month_0   += m_age_0_m_amount;
			m_grand_amount_total_month_1   += m_age_1_m_amount;
			m_grand_amount_total_month_2   += m_age_2_m_amount;
			m_grand_amount_total_month_3   += m_age_3_m_amount;
			m_grand_amount_total_month_4_5 += m_age_4_5_m_amount;
			m_grand_amount_total_month_6   += m_age_6_m_amount;
			m_grand_amount_total_month_legal   += m_age_legal_m_amount;
			
			
			m_grand_amount_total_old_month_0   += m_age_0_m_amount_old;
			m_grand_amount_total_old_month_1   += m_age_1_m_amount_old;
			m_grand_amount_total_old_month_2   += m_age_2_m_amount_old;
			m_grand_amount_total_old_month_3   += m_age_3_m_amount_old;
			m_grand_amount_total_old_month_4_5 += m_age_4_5_m_amount_old;
			m_grand_amount_total_old_month_6   += m_age_6_m_amount_old;
			
			m_grand_amount_total_old_month_legal   += m_age_legal_m_amount_old;
			
			m_grand_amount_total_old = m_grand_amount_total_old_month_0+m_grand_amount_total_old_month_1+m_grand_amount_total_old_month_2+m_grand_amount_total_old_month_3+m_grand_amount_total_old_month_4_5+m_grand_amount_total_old_month_6+m_grand_amount_total_old_month_legal;
			m_grand_amount_total     = m_grand_amount_total_month_0+m_grand_amount_total_month_1+m_grand_amount_total_month_2+m_grand_amount_total_month_3+m_grand_amount_total_month_4_5+m_grand_amount_total_month_6+m_grand_amount_total_month_legal;
			
			m_grand_count_total_rate = (m_grand_count_total_old-m_grand_count_total)/m_grand_count_total_old*100;
			m_grand_amont_total_rate = (m_grand_amount_total_old-m_grand_amount_total)/m_grand_amount_total_old*100;
			
			//----------------Capital Movement
					
			m_age_0_grand_m_amount_cap   += m_age_0_m_amount_cap;
			m_age_1_grand_m_amount_cap   += m_age_1_m_amount_cap;
			m_age_2_grand_m_amount_cap   += m_age_2_m_amount_cap;
			m_age_3_grand_m_amount_cap   += m_age_3_m_amount_cap;
			m_age_4_5_grand_m_amount_cap += m_age_4_5_m_amount_cap;
			m_age_6_grand_m_amount_cap   += m_age_6_m_amount_cap;
			
			m_age_legal_grand_m_amount_cap   += m_age_legal_m_amount_cap; //legal
			
			m_age_0_grand_m_amount_cap_old   += m_age_0_m_amount_cap_old;
			m_age_1_grand_m_amount_cap_old   += m_age_1_m_amount_cap_old;
			m_age_2_grand_m_amount_cap_old   += m_age_2_m_amount_cap_old;
			m_age_3_grand_m_amount_cap_old   += m_age_3_m_amount_cap_old;
			m_age_4_5_grand_m_amount_cap_old += m_age_4_5_m_amount_cap_old;
			m_age_6_grand_m_amount_cap_old   += m_age_6_m_amount_cap_old;
			
			m_age_legal_grand_m_amount_cap_old   += m_age_legal_m_amount_cap_old; //legal
			
			
			m_tot_age_grand_m_amount_cap     = m_age_0_grand_m_amount_cap+m_age_1_grand_m_amount_cap+m_age_2_grand_m_amount_cap+m_age_3_grand_m_amount_cap+m_age_4_5_grand_m_amount_cap+m_age_6_grand_m_amount_cap+m_age_legal_grand_m_amount_cap;
			m_tot_age_grand_m_amount_cap_old = m_age_0_grand_m_amount_cap_old+m_age_1_grand_m_amount_cap_old+m_age_2_grand_m_amount_cap_old+m_age_3_grand_m_amount_cap_old+m_age_4_5_grand_m_amount_cap_old+m_age_6_grand_m_amount_cap_old+m_age_legal_grand_m_amount_cap_old;
			
			m_grand_amount_tot_rate = (m_tot_age_grand_m_amount_cap_old-m_tot_age_grand_m_amount_cap)/m_tot_age_grand_m_amount_cap_old*100;
			
			}
			out.println("</table>");
			
			//---------------Added by Sandun on 29-04-2009-------------------------------------------------------------------------------------------------
        out.println("<br><br>");
        out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 	
				out.println("<tr><td width=100% bgcolor='lightgrey' align='center' colspan=11 STYLE='{font: 10pt  arial;}' class='rep-body'><b>Total</td></tr>");
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>Date</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0 - Below </td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>0.01 - 3.00</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3.01 - 5.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6.00 - 11.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>12.00 - 17.99</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>18.00 - Above</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Legal</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>%</td>");
				out.println("</tr>");
				
				out.println("<tr>");
			  out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' bgcolor='#90EE90'>Total</td>");
			  out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Rental Movement</td>");
			  out.println("</tr>");	
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_curr_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_count_total_rate)+"</td>");
				out.println("</tr>");
				
				
			
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_age_drill('NEW',0)\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_month_0)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_age_drill('NEW',1)\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_month_1)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_age_drill('NEW',2)\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_month_2)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_age_drill('NEW',3)\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_month_3)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_age_drill('NEW',5)\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_month_4_5)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_age_drill('NEW',6)\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_month_6)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_age_drill('NEW',7)\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_month_legal)+"</U></td>"); //legal
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_drill('"+m_date+"','NEW')\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total)+"</U></td>"); //drill call here
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_amont_total_rate)+"</td>");
				out.println("</tr>");
				
				out.println("<tr bgcolor='#90EE90'>");
				out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>"); //legal
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  align='center'>&nbsp;</td>");
				out.println("</tr>");
				
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_prev_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_0)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_1)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_2)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_3)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_4_5)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_6)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_grand_amount_total_old_month_legal)+"</td>"); //legal
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' onClick=\"show_total_arr_drill('"+m_date+"','OLD')\" class='rep-body' align='right'><U>"+nf.format(m_grand_amount_total_old)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
			  out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' bgcolor='#90EE90'>&nbsp;</td>");
			  out.println("<td width='90%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center' colspan=11 bgcolor='#FFFFCC' ><b>Capital Movement</td>");
			  out.println("</tr>");	
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_curr_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6+"</td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal+"</td>"); //legal
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_count_total_rate)+"</td>");
				out.println("</tr>");
				
				
			
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' class='rep-body' onClick=\"show_total_arr_age_drill('NEW',0)\" align='right'><u>"+nf.format(m_age_0_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' class='rep-body' onClick=\"show_total_arr_age_drill('NEW',1)\" align='right'><u>"+nf.format(m_age_1_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' class='rep-body' onClick=\"show_total_arr_age_drill('NEW',2)\" align='right'><u>"+nf.format(m_age_2_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' class='rep-body' onClick=\"show_total_arr_age_drill('NEW',3)\" align='right'><u>"+nf.format(m_age_3_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' class='rep-body' onClick=\"show_total_arr_age_drill('NEW',5)\" align='right'><u>"+nf.format(m_age_4_5_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' class='rep-body' onClick=\"show_total_arr_age_drill('NEW',6)\" align='right'><u>"+nf.format(m_age_6_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }' class='rep-body' onClick=\"show_total_arr_age_drill('NEW',7)\" align='right'><u>"+nf.format(m_age_legal_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }'      onClick=\"show_total_capital_drill('"+m_date+"','NEW')\" class='rep-body' align='right'><u>"+nf.format(m_tot_age_grand_m_amount_cap)+"</u></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf1.format(m_grand_amount_tot_rate)+"</td>");
				out.println("</tr>");
				
				out.println("<tr bgcolor='#90EE90'>");
				out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>"); //legal
				out.println("<td  width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body'  align='center'>&nbsp;</td>");
				out.println("</tr>");
				
				
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' >"+m_prev_month+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_0_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_1_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_2_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_3_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_4_5_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_6_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_month_legal_old+"</td>"); //legal
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+m_grand_count_total_old+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#90EE90'><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' ><b>&nbsp;</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_0_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_1_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_2_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_3_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>"+nf.format(m_age_4_5_grand_m_amount_cap_old)+"</td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;}'  class='rep-body' align='right'><u>"+nf.format(m_age_6_grand_m_amount_cap_old)+"</u></td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;}'  class='rep-body' align='right'><u>"+nf.format(m_age_legal_grand_m_amount_cap_old)+"</u></td>");
				
				out.println("<td width='10%' STYLE='{font:  8pt bold arial;cursor:hand; }'   onClick=\"show_total_capital_drill('"+m_date+"','OLD')\" class='rep-body' align='right'><U>"+nf.format(m_tot_age_grand_m_amount_cap_old)+"</U></td>");
				out.println("<td width='10%' STYLE='{font:  8pt bold arial; }' class='rep-body' align='right'>&nbsp;</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr >");
				out.println("<td bgcolor='lightgrey' colspan=11 width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			  out.println("</tr>");		
			
				
				out.println("</table>");
			  out.println("<br><br>");
     //---------------------------end----------------------------------------------------------------------------------------------------------------
			
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
					
		
}



   else if(m_chksql.equals("drill_down_details_total")){		
	  
		String m_date="";
	  double m_age=0;
	  String m_officer="";
		String m_sub_team_id="";
				
		if(req.getParameter("age")!=null ){
		m_age=Double.parseDouble(req.getParameter("age").trim());
		}
		
		
		if(req.getParameter("user_id")!=null ){
		m_officer=req.getParameter("user_id").trim();
		}
					
		stmt = conn.createStatement ();
		
		if(m_age ==6){
		
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE, "+
		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+ //7
		" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE "+  //8
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE age >= 18 "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 5 "+
		" AND ent_user='"+m_username+"' ");
		
    }
		else if(m_age ==5){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE, "+
		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+ //7
		" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE "+  //8
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE age >=12 "+
		" AND age < 18 "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==3){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE, "+
		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+ //7
		" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE "+  //8
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE age >=6 "+
		" AND age <12 "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==2){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE, "+
		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+ //7
		" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE "+  //8
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE age > 3 "+
		" AND age <  6 "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==1){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" AGE AGE, "+
		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+ //7
		" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE "+  //8
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE age > 0 "+
		" AND age <=  3 "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==0){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" AGE AGE, "+
		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+ //7
		" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE "+  //8
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE age <= 0 "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==7){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		//" NVL(ARR_AMOUNT,0)  ARREARS, "+
		" 0 ARREARS ,"+
		" NVL(FUTURE_RECEIVABLE,0) + NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" nvl(arr_amount,0)  CAP_OS, "+
		" AGE AGE, "+
		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) APP_STATUS, "+ //7
		" NVL(TO_CHAR(TERMI_DATE,'DD-MM-YYYY'),'-') TERMINATION_DATE "+  //8
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE  APPLICATION_STATUS='LEGAL' "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+
		" AND ent_user='"+m_username+"' ");
    }

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Movement Report With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			//out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"1\" class=\"table\">");
		  out.println("<tr class=tr_input1>");
		  out.println("<td width=\"2%\"   class=div_input align=left  ><b>No</td>"); 
			out.println("<td width=\"7%\"   class=div_input align=left  ><b>Finance No</td>"); 
			out.println("<td width=\"10%\"  class=div_input align=left ><b>Application Status</td>"); 
			out.println("<td width=\"10%\"  class=div_input align=left ><b>Termination Date</td>"); 
			out.println("<td width=\"10%\"  class=div_input align=left ><b>Client Name</td>"); 
			out.println("<td width=\"7%\"   class=div_input align=right ><b>Age</td>"); 
			out.println("<td width=\"7%\"   class=div_input align=right ><b>Arrears Amount </td>");  //
			out.println("<td width=\"7%\"   class=div_input align=right ><b>Capital O/S</td>"); 
			out.println("</tr >"); 
			int j=1;
			boolean more=rs.next();
			double m_tot_amt=0,m_tot_cap=0;
			while(more){
		  out.println("<tr >");
			out.println("<td width=\"2%\"  class=div_input  align=left>"+j+"</td>"); 
			out.println("<td width=\"7%\"  class=div_input  align=left>"+rs.getString(1)+"</td>"); 
			out.println("<td width=\"10%\"  class=div_input align=left >"+rs.getString(7)+"</td>"); 
			out.println("<td width=\"10%\"  class=div_input align=left >"+rs.getString(8)+"</td>"); 
			out.println("<td width=\"10%\" class=div_input  align=left style=\"{cursor:hand;}\" onClick=\"show_client('"+rs.getString(2)+"')\" ><u>"+rs.getString(3)+"</u></td>"); 
			out.println("<td width=\"7%\" class=div_input   align=right>"+nf1.format(rs.getDouble(6))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" class=div_input   align=right>"+nf.format(rs.getDouble(4))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" class=div_input   align=right>"+nf.format(rs.getDouble(5))+"&nbsp;</td>"); 
			out.println("</tr >"); 
			m_tot_amt+=rs.getDouble(4);
			m_tot_cap+=rs.getDouble(5);
			more=rs.next();
			j+=1;
			}
			
			out.println("<tr >");
			out.println("<td width=\"2%\" class=div_input  align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" class=div_input  align=left>&nbsp;</td>"); 
			out.println("<td width=\"10%\" class=div_input  align=left>&nbsp;</td>"); 
			out.println("<td width=\"10%\" class=div_input  align=left>&nbsp;</td>"); 
			out.println("<td width=\"10%\" class=div_input  align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" class=div_input align=right><b>Total&nbsp;</td>"); 
			out.println("<td width=\"7%\" class=div_input align=right><b>"+nf.format(m_tot_amt)+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" class=div_input align=right><b>"+nf.format(m_tot_cap)+"&nbsp;</td>"); 
			
			out.println("</tr >"); 
			
			
		out.println("</table >"); 
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
}

   else if(m_chksql.equals("drill_down_details")){		
	  
		String m_date="";
	  double m_age=0;
	  String m_officer="";
		String m_sub_team_id="";
				
		if(req.getParameter("age")!=null ){
		m_age=Double.parseDouble(req.getParameter("age").trim());
		}
		
		
		if(req.getParameter("user_id")!=null ){
		m_officer=req.getParameter("user_id").trim();
		}
					
		stmt = conn.createStatement ();
		
		if(m_age ==6){
		
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		" AND age >= 18 "+
		" AND TERMI_STATUS = '0' "+
		" AND APPLICATION_STATUS <> 'LEGAL' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 5 "+
		" AND ent_user='"+m_username+"' ");
		
    }
		else if(m_age ==5){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 3  "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 5 "+
		//" AND age > 3 "+
		//" AND age <=  5 "+
		" AND age >=12 "+
		" AND age < 18 "+
		" AND TERMI_STATUS = '0' "+
		" AND APPLICATION_STATUS <> 'LEGAL' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==3){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 2 "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <=  3 "+
		//" AND age > 2 "+
		//" AND age <= 3 "+
		" AND age >=6 "+
		" AND age <12 "+
		" AND TERMI_STATUS = '0' "+
		" AND APPLICATION_STATUS <> 'LEGAL' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==2){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" AGE AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 1 "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <=  2 "+
		//" AND age > 1 "+
		//" AND age <=  2 "+
		" AND age > 3 "+
		" AND age <  6 "+
		" AND TERMI_STATUS = '0' "+
		" AND APPLICATION_STATUS <> 'LEGAL' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==1){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" AGE AGE "+
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) >  0 "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <  1 "+
		//" AND age > 0 "+
		//" AND age <=  1 "+
		" AND age > 0 "+
		" AND age <=  3 "+
		" AND TERMI_STATUS = '0' "+
		" AND APPLICATION_STATUS <> 'LEGAL' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==0){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" nvl(arr_amount,0)  arrears, "+
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" AGE AGE "+
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) >  0 "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <  1 "+
		//" AND age > 0 "+
		//" AND age <=  1 "+
		" AND age <= 0 "+
		" AND TERMI_STATUS = '0' "+
		" AND APPLICATION_STATUS <> 'LEGAL' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==7){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		//" nvl(arr_amount,0)  arrears, "+
		" 0  arrears, "+
		//" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" nvl(arr_amount,0)  CAP_OS, "+
		" AGE AGE "+
		//" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		" AND APPLICATION_STATUS = 'LEGAL' "+
		" AND TERMI_STATUS = '0' "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Movement Report With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			//out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"1\" class=\"table\">");
		  out.println("<tr class=tr_input1>");
		  out.println("<td width=\"2%\" align=left  ><b>No</td>"); 
			out.println("<td width=\"7%\" align=left  ><b>Finance No</td>"); 
			out.println("<td width=\"10%\" align=left ><b>Client Name</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Age</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Arrears Amount </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Capital O/S</td>"); 
			out.println("</tr >"); 
			int j=1;
			boolean more=rs.next();
			double m_tot_amt=0,m_tot_cap=0;
			while(more){
		  out.println("<tr >");
			out.println("<td width=\"2%\" align=left>"+j+"</td>"); 
			out.println("<td width=\"7%\" align=left>"+rs.getString(1)+"</td>"); 
			out.println("<td width=\"10%\" align=left style=\"{cursor:hand;}\" onClick=\"show_client('"+rs.getString(2)+"')\" ><u>"+rs.getString(3)+"</u></td>"); 
			out.println("<td width=\"7%\" align=right>"+nf1.format(rs.getDouble(6))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"&nbsp;</td>"); 
			out.println("</tr >"); 
			m_tot_amt+=rs.getDouble(4);
			m_tot_cap+=rs.getDouble(5);
			more=rs.next();
			j+=1;
			}
			
			out.println("<tr >");
			out.println("<td width=\"2%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"10%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>Total&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(m_tot_amt)+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(m_tot_cap)+"&nbsp;</td>"); 
			
			out.println("</tr >"); 
			
			
		out.println("</table >"); 
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
}

   else if(m_chksql.equals("drill_down_details_team")){		
	  
		String m_date="";
	  double m_age=0;
	  String m_officer="";
		String m_sub_team_id="";
				
		if(req.getParameter("age")!=null ){
		m_age=Double.parseDouble(req.getParameter("age").trim());
		}
		
		
		if(req.getParameter("user_id")!=null ){
		m_officer=req.getParameter("user_id").trim();
		}
					
		stmt = conn.createStatement ();
		
		if(m_age ==6){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE sub_team_id='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 5 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) >=18 "+
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==5){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE sub_team_id='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 3  "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <= 5 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) >= 12  "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <18 "+
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==3){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE sub_team_id='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 2 "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <=  3 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) >= 6 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) < 12 "+
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==2){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE sub_team_id='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 1 "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <=  2 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) > 3 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) < 6 "+
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==1){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		" TOTAL_AMOUNT - (NVL(SETTLED_AMOUNT,0)+NVL(ADJUSTED_AMOUNT,0)) ARR, "+ //4
		" NVL(FUTURE_RECEIVABLE,0)+NVL(ARR_CAPITAL_PORTION,0) CAP_OS, "+ //5
		" TOTAL_RENTALS- NVL(RENTALS_PAID,0) AGE "+ //6
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE sub_team_id='"+m_officer+"' "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) >  0 "+
		//" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <  1 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) >  0 "+
		" AND TOTAL_RENTALS- NVL(RENTALS_PAID,0) <=  3 "+
		
		" AND ent_user='"+m_username+"' ");
    }

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Movement Report With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
		  out.println("<tr class=tr_input1>");
		  out.println("<td width=\"2%\" align=left  ><b>No</td>"); 
			out.println("<td width=\"7%\" align=left  ><b>Finance No</td>"); 
			out.println("<td width=\"10%\" align=left ><b>Client Name</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Age</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Arrears Amount </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Capital O/S</td>"); 
			out.println("</tr >"); 
			int j=1;
			boolean more=rs.next();
			double m_tot_amt=0,m_tot_cap=0;
			while(more){
		  out.println("<tr >");
			out.println("<td width=\"2%\" align=left>"+j+"</td>"); 
			out.println("<td width=\"7%\" align=left>"+rs.getString(1)+"</td>"); 
			out.println("<td width=\"10%\" align=left style=\"{cursor:hand;}\" onClick=\"show_client('"+rs.getString(2)+"')\" ><u>"+rs.getString(3)+"</u></td>"); 
			out.println("<td width=\"7%\" align=right>"+nf1.format(rs.getDouble(6))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"&nbsp;</td>"); 
			out.println("</tr >"); 
			m_tot_amt+=rs.getDouble(4);
			m_tot_cap+=rs.getDouble(5);
			more=rs.next();
			j+=1;
			}
			
			out.println("<tr >");
			out.println("<td width=\"2%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"10%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>Total&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(m_tot_amt)+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(m_tot_cap)+"&nbsp;</td>"); 
			
			out.println("</tr >"); 
			
			
		out.println("</table >"); 
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
}

   else if(m_chksql.equals("drill_down_details_old")){		
	  
		String m_date="";
	  double m_age=0;
	  String m_officer="";
		String m_sub_team_id="";
				
		if(req.getParameter("age")!=null ){
		m_age=Double.parseDouble(req.getParameter("age").trim());
		}
		
		
		if(req.getParameter("user_id")!=null ){
		m_officer=req.getParameter("user_id").trim();
		}
					
		stmt = conn.createStatement ();
	
		if(m_age ==6){
		rs=stmt.executeQuery(			
		//out.println(
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT_OLD - (NVL(SETTLED_AMOUNT_OLD,0)+NVL(ADJUSTED_AMOUNT_OLD,0)) ARR, "+ //4
		" nvl(arr_amount_old,0) arrears ,"+
		" NVL(FUTURE_RECEIVABLE_OLD,0)+NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) AGE "+ //6
		" age_old AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) > 5 "+
		" AND age_old >= 18"+
		" AND TERMI_STATUS NOT IN ('2','3') "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==5){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT_OLD - (NVL(SETTLED_AMOUNT_OLD,0)+NVL(ADJUSTED_AMOUNT_OLD,0)) ARR, "+ //4
		" nvl(arr_amount_old,0) arrears ,"+
		" NVL(FUTURE_RECEIVABLE_OLD,0)+NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) AGE "+ //6
		" age_old AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) > 3  "+
		//" AND TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) <= 5 "+
		" AND age_old >=12  "+
		" AND age_old < 18 "+
		" AND TERMI_STATUS NOT IN ('2','3') "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==3){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT_OLD - (NVL(SETTLED_AMOUNT_OLD,0)+NVL(ADJUSTED_AMOUNT_OLD,0)) ARR, "+ //4
		" nvl(arr_amount_old,0) arrears ,"+
		" NVL(FUTURE_RECEIVABLE_OLD,0)+NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) AGE "+ //6
		" age_old AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) > 2 "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) <=  3 "+
		" AND age_old >= 6 "+
		" AND age_old < 12 "+
		" AND TERMI_STATUS NOT IN ('2','3') "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==2){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT_OLD - (NVL(SETTLED_AMOUNT_OLD,0)+NVL(ADJUSTED_AMOUNT_OLD,0)) ARR, "+ //4
		" nvl(arr_amount_old,0) arrears ,"+
		" NVL(FUTURE_RECEIVABLE_OLD,0)+NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) AGE "+ //6
		" age_old AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) > 3 "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) < 6 "+
    " AND age_old > 3 "+
		" AND age_old < 6 "+		
		" AND TERMI_STATUS NOT IN ('2','3') "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		else if(m_age ==1){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT_OLD - (NVL(SETTLED_AMOUNT_OLD,0)+NVL(ADJUSTED_AMOUNT_OLD,0)) ARR, "+ //4
		" nvl(arr_amount_old,0) arrears ,"+
		" NVL(FUTURE_RECEIVABLE_OLD,0)+NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) AGE "+ //6
		" age_old AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) >  0 "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) <  1 "+
		" AND age_old > 0 "+
		" AND age_old <=  3 "+
		" AND TERMI_STATUS NOT IN ('2','3') "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }
		
		else if(m_age ==0){
		rs=stmt.executeQuery(			
		" SELECT "+
		" FINANCE_NO, "+ //1
		" CLIENT_CODE, "+ //2
		" FULL_NAME, "+ //3
		//" TOTAL_AMOUNT_OLD - (NVL(SETTLED_AMOUNT_OLD,0)+NVL(ADJUSTED_AMOUNT_OLD,0)) ARR, "+ //4
		" nvl(arr_amount_old,0) arrears ,"+
		" NVL(FUTURE_RECEIVABLE_OLD,0)+NVL(ARR_CAPITAL_PORTION_OLD,0) CAP_OS, "+ //5
		//" TOTAL_RENTALS_OLD- NVL(RENTALS_PAID_OLD,0) AGE "+ //6
		" age_old AGE "+
		" FROM "+m_schema_name+".AF_RE_TBD_COL_MOVEMENT_RPT_AGE  "+
		" WHERE COLLECTION_OFFICER='"+m_officer+"' "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) >  0 "+
		//" AND TOTAL_RENTALS_OLD - NVL(RENTALS_PAID_OLD,0) <  1 "+
		" AND age_old <= 0 "+
		" AND TERMI_STATUS NOT IN ('2','3') "+
		" AND TRANSACTION_TYPE <> 'HIRING' "+		
		" AND ent_user='"+m_username+"' ");
    }


		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Movement Report With Ageing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			//out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			int j=1;
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
		  out.println("<tr class=tr_input1>");
			out.println("<td width=\"2%\" align=left  ><b>No</td>"); 
			out.println("<td width=\"7%\" align=left  ><b>Finance No</td>"); 
			out.println("<td width=\"10%\" align=left ><b>Client Name</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Age</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Arrears Amount </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Capital O/S</td>"); 
			out.println("</tr >"); 
			
			boolean more=rs.next();
			double m_tot_amt=0,m_tot_cap=0;
			while(more){
		  out.println("<tr >");
			out.println("<td width=\"2%\" align=left>"+j+"</td>"); 
			out.println("<td width=\"7%\" align=left>"+rs.getString(1)+"</td>"); 
			out.println("<td width=\"10%\" align=left style=\"{cursor:hand;}\" onClick=\"show_client('"+rs.getString(2)+"')\" ><u>"+rs.getString(3)+"</u></td>"); 
			out.println("<td width=\"7%\" align=right>"+nf1.format(rs.getDouble(6))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"&nbsp;</td>"); 
			out.println("</tr >"); 
			m_tot_amt+=rs.getDouble(4);
			m_tot_cap+=rs.getDouble(5);
			more=rs.next();
			j+=1;
			}
			
			out.println("<tr >");
			out.println("<td width=\"2%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"10%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>Total&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(m_tot_amt)+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(m_tot_cap)+"&nbsp;</td>"); 
			
			out.println("</tr >"); 
			
			
		out.println("</table >"); 
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
}


		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
