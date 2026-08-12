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
 

public class LAKDL_AF_RE_Collection_Movement_Report extends javax.servlet.http.HttpServlet { 

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
				String m_report_type = req.getParameter("report_type");

				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_COLL_MOVEMENT_RPT(:1,:2,:3,:4,:5,:6);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_team_id);
				callstmt1.setString(3,m_sub_team_id);
				callstmt1.setString(4,m_user_id);
				callstmt1.setString(5,m_username);
				callstmt1.setString(6,m_report_type);
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
			out.println("<TITLE>Collection - Collection Movement Report</TITLE>"); 
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
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=run_report&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=run_report&report_type=\"+document.Form1.TXT_REPORT_TYPE.value+\"&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				//out.println("alert(m_data);");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report();"); 
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
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_user&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("else if(m_team_id!='' && m_sub_team_id!='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_sub_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("else if(m_team_id!='' && m_sub_team_id=='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("else if(m_team_id=='' && m_sub_team_id=='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_all&date=\"+m_date+\"&report_type=\"+document.Form1.TXT_REPORT_TYPE.value+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
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
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=page';"); 
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
			out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Collection Movement Report - \"+document.Form1.hid_status.value;"); 
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Movement Report </td>"); 
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
				
				
			
		    out.println("<table align='center' width='100%' class='table' border='0'>");//****Added by Sandun-----on 05-08-08*****//		
			  out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REPORT_TYPE'  class=div_input>Report Type</DIV></td>"); 
				out.println("<td width='30%' ><SELECT onchange=\"\" name=\"TXT_REPORT_TYPE\" class=\"txt_input\" > ");
				out.println("<OPTION value=\"N_LEG\" SELECTED>Non Legal</OPTION>");
				out.println("<OPTION value=\"LEG\">Legal</OPTION>");
				out.println("</SELECT></td>");
				out.println("<td width='*%'></td>"); 
			  out.println("</tr>"); 
								
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
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
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
   else if(m_chksql.equals("drill_down_details")){		
	  
		String m_date="";
	  String m_team_head="";
	  String m_officer="";
		String m_sub_team_id="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		if(req.getParameter("user_id")!=null ){
		m_officer=req.getParameter("user_id").trim();
		}
					
		stmt = conn.createStatement ();
		
		 rs=stmt.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY') , TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon')   FROM DUAL ");
			
			rs.next();
			String report_date=rs.getString(1);
			String Month=rs.getString(2);
			
			rs=stmt.executeQuery(
			" SELECT a.team_id,"+ //1
			"	"+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) OFFICER_NAME, "+ //2
			" a.sub_team_id,  "+ //3
			" a.total_amount - (nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0))  balance, "+ //4
			" nvl(a.settled_amount_cur_mon,0) settled_amount, "+ //5
			" nvl(a.tot_inv_gen_cur_mon,0) cur_inv, "+ //6
			" nvl(a.tot_inv_set_cur_mon,0) cur_set ,"+ //7
			" a.location_code ,"+ //8
			" a.finance_no ,"+ //9
			" a.full_name "+ //10
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a "+
			" where a.collection_officer='"+m_officer+"' "+
			" AND a.ent_user='"+m_username+"' "+
			" order by finance_no ");
			

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"1\" class=\"table\" cellspacing='0' >");
			out.println("<br>");			
		  out.println("<tr class=tr_input1>");
			out.println("<td width=\"2%\" align=left  ><b>No</td>"); 
			out.println("<td width=\"10%\" align=left  ><b>Finance No</td>"); 
			out.println("<td width=\"7%\" align=left  ><b>Full Name</td>"); 
			out.println("<td width=\"7%\" align=right ><b>B/F Bal </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection From Balance at "+Month+" </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Invoiced </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Invoiced Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Collected Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Ratio %</td>"); 
			out.println("</tr >"); 
			
			boolean more=rs.next();
			
			int row=0;
		  int j=1;
			double Col_ratio=0;
			double inv_ratio=0;
		  double total_inv_amount=0;
			double total_settle_amount=0;
			double tot_balance=0;
			double tot_ratio=0;
			
			double tot_os =0;
			double tot_os_collect=0;
			double tot_os_balance=0;
			
			double tot_inv =0;
			double tot_inv_collect=0;
			double tot_inv_balance=0;
			
			double tot_all =0;
			double tot_all_collect=0;
			double tot_all_balance=0;
			
			double tot_Col_ratio=0;
			double tot_inv_ratio=0;
			double tot_tot_ratio=0;

			double m_balance=0,m_balance_inv=0;
			int i=1;
			while(more){
			m_balance=rs.getDouble(4)-rs.getDouble(5);
			Col_ratio=(rs.getDouble(5)/rs.getDouble(4)) *100;
			m_balance_inv=rs.getDouble(6)-rs.getDouble(7);
			inv_ratio=(rs.getDouble(7)/rs.getDouble(6)) *100;
			
			total_inv_amount=rs.getDouble(4)+rs.getDouble(6);
			total_settle_amount=rs.getDouble(5)+rs.getDouble(7);
			tot_balance=total_inv_amount-total_settle_amount;
			tot_ratio=(total_settle_amount/total_inv_amount) *100; 
			
			
		  out.println("<tr >");
			out.println("<td width=\"2%\" class=factoring-letter-body align=left>"+i+"</td>"); 
			out.println("<td width=\"10%\" class=factoring-letter-body align=left>"+rs.getString(9)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=left>"+rs.getString(10)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(m_balance)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(m_balance_inv)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body align=right>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body bgcolor=\"#C0C0C0\" align=right>"+nf.format(total_inv_amount)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body bgcolor='lightblue' align=right>"+nf.format(total_settle_amount)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body bgcolor=\"#CCCCCC\" align=right>"+nf.format(tot_balance)+"</td>"); 
			out.println("<td width=\"7%\" class=factoring-letter-body  align=right>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 
			
			tot_os =tot_os+rs.getDouble(4);
			tot_os_collect=tot_os_collect+rs.getDouble(5);
			tot_os_balance=tot_os_balance+m_balance;
			
			tot_inv =tot_inv+rs.getDouble(6);
			tot_inv_collect=tot_inv_collect+rs.getDouble(7);
			tot_inv_balance=tot_inv_balance+m_balance_inv;
			
			tot_all =tot_all+total_inv_amount;
			tot_all_collect=tot_all_collect+total_settle_amount;
			tot_all_balance=tot_all_balance+tot_balance;

			more=rs.next();
			i+=1;
			}
			
			out.println("<tr >");
			out.println("<td width=\"2%\" align=left><b>&nbsp;</td>"); 
			out.println("<td width=\"10%\" align=left><b>Total</td>"); 
			out.println("<td width=\"7%\" align=left><b>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 

			
		out.println("</table >"); 
    out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
}

   else if(m_chksql.equals("print_report_user")){		
	  
		String m_date="";
	  String m_team_head="";
	  String m_officer="";
		String m_sub_team_id="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		if(req.getParameter("user_id")!=null ){
		m_officer=req.getParameter("user_id").trim();
		}
					
		stmt = conn.createStatement ();
		
		 rs=stmt.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY') , TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon')   FROM DUAL ");
			
			rs.next();
			String report_date=rs.getString(1);
			String Month=rs.getString(2);
			
			rs=stmt.executeQuery(
			" SELECT a.team_id,"+ //1
			"	"+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) OFFICER_NAME, "+ //2
			" a.sub_team_id,  "+ //3
			" sum(a.total_amount) - (sum(a.settled_amount) + sum(a.adjusted_amount))  balance, "+ //4
			" sum(a.settled_amount_cur_mon) settled_amount, "+ //5
			" sum(a.tot_inv_gen_cur_mon) cur_inv, "+ //6
			" sum(a.tot_inv_set_cur_mon) cur_set ,"+ //7
			" a.location_code ,"+ //8
			" A.COLLECTION_OFFICER "+ //9
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a "+
			" where a.collection_officer='"+m_officer+"' "+
			" AND a.ent_user='"+m_username+"' "+
			" group by a.collection_officer,a.team_id,sub_team_id ,location_code");
			

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill(m_user_id){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=drill_down_details&user_id=\"+m_user_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<br>");			
		  out.println("<tr class=tr_input1>");
			out.println("<td width=\"10%\" align=left  ><b>Collection Officer</td>"); 
			out.println("<td width=\"7%\" align=left  ><b>Branch</td>"); 
			out.println("<td width=\"7%\" align=right ><b>B/F Bal </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection From Balance at "+Month+" </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Invoiced </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Invoiced Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Collected Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Ratio %</td>"); 
			out.println("</tr >"); 
			
			boolean more=rs.next();
			
			int row=0;
		  int j=1;
			double Col_ratio=0;
			double inv_ratio=0;
		  double total_inv_amount=0;
			double total_settle_amount=0;
			double tot_balance=0;
			double tot_ratio=0;
			
			double tot_os =0;
			double tot_os_collect=0;
			double tot_os_balance=0;
			
			double tot_inv =0;
			double tot_inv_collect=0;
			double tot_inv_balance=0;
			
			double tot_all =0;
			double tot_all_collect=0;
			double tot_all_balance=0;
			
			double tot_Col_ratio=0;
			double tot_inv_ratio=0;
			double tot_tot_ratio=0;

			double m_balance=0,m_balance_inv=0;
			
			while(more){
			m_balance=rs.getDouble(4)-rs.getDouble(5);
			Col_ratio=(rs.getDouble(5)/rs.getDouble(4)) *100;
			m_balance_inv=rs.getDouble(6)-rs.getDouble(7);
			inv_ratio=(rs.getDouble(7)/rs.getDouble(6)) *100;
			
			total_inv_amount=rs.getDouble(4)+rs.getDouble(6);
			total_settle_amount=rs.getDouble(5)+rs.getDouble(7);
			tot_balance=total_inv_amount-total_settle_amount;
			tot_ratio=(total_settle_amount/total_inv_amount) *100; 
			
			
		  out.println("<tr bgcolor=\"#CCCCCC\" onClick=\"show_movement_drill('"+rs.getString(9)+"')\" style=\"{cursor:hand;}\" >");
			out.println("<td width=\"10%\" align=left ><u>"+rs.getString(2)+"</u></td>"); 
			out.println("<td width=\"7%\" align=left>"+rs.getString(8)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_inv_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_settle_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 
			
			tot_os =tot_os+rs.getDouble(4);
			tot_os_collect=tot_os_collect+rs.getDouble(5);
			tot_os_balance=tot_os_balance+m_balance;
			
			tot_inv =tot_inv+rs.getDouble(6);
			tot_inv_collect=tot_inv_collect+rs.getDouble(7);
			tot_inv_balance=tot_inv_balance+m_balance_inv;
			
			tot_all =tot_all+total_inv_amount;
			tot_all_collect=tot_all_collect+total_settle_amount;
			tot_all_balance=tot_all_balance+tot_balance;

			more=rs.next();
			}
			
			out.println("<tr bgcolor='cyan'>");
			out.println("<td width=\"10%\" align=left><b>Total</td>"); 
			out.println("<td width=\"7%\" align=left><b>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 

			
		out.println("</table >"); 
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
				
		if(req.getParameter("sub_team_id")!=null ){
		m_sub_team_id=req.getParameter("sub_team_id").trim();
		}
		
		if(req.getParameter("team_id")!=null ){
		m_team_id=req.getParameter("team_id").trim();
		}
					
		stmt = conn.createStatement ();
		
		 rs=stmt.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY') , TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon')   FROM DUAL ");
			
			rs.next();
			String report_date=rs.getString(1);
			String Month=rs.getString(2);
			
			rs=stmt.executeQuery(
			" SELECT a.team_id,"+ //1
			"	"+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) OFFICER_NAME, "+ //2
			" a.sub_team_id,  "+ //3
			" (sum(a.total_amount) - sum(a.settled_amount) + sum(a.adjusted_amount)) balance, "+ //4
			" sum(a.settled_amount_cur_mon) settled_amount, "+ //5
			" sum(a.tot_inv_gen_cur_mon) cur_inv, "+ //6
			" sum(a.tot_inv_set_cur_mon) cur_set ,"+ //7
			" a.location_code , "+ //8
			" a.collection_officer"+ //9
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a "+
			" where a.sub_team_id='"+m_sub_team_id+"' "+
			" AND a.ent_user='"+m_username+"' "+
			" group by a.collection_officer,a.team_id,a.sub_team_id ,location_code");
			

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill(m_user_id,m_date){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=drill_down_details&date=\"+m_date+\"&user_id=\"+m_user_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<br>");			
		  out.println("<tr class=tr_input1>");
			out.println("<td width=\"10%\" align=left  ><b>Collection Officer</td>"); 
			out.println("<td width=\"7%\" align=left  ><b>Branch</td>"); 
			out.println("<td width=\"7%\" align=right ><b>B/F Bal </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection From Balance at "+Month+" </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Invoiced </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Invoiced Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Collected Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Ratio %</td>"); 
			out.println("</tr >"); 
			
			boolean more=rs.next();
			
			int row=0;
		  int j=1;
			double Col_ratio=0;
			double inv_ratio=0;
		  double total_inv_amount=0;
			double total_settle_amount=0;
			double tot_balance=0;
			double tot_ratio=0;
			
			double tot_os =0;
			double tot_os_collect=0;
			double tot_os_balance=0;
			
			double tot_inv =0;
			double tot_inv_collect=0;
			double tot_inv_balance=0;
			
			double tot_all =0;
			double tot_all_collect=0;
			double tot_all_balance=0;
			
			double tot_Col_ratio=0;
			double tot_inv_ratio=0;
			double tot_tot_ratio=0;

			double m_balance=0,m_balance_inv=0;
			
			while(more){
			m_balance=rs.getDouble(4)-rs.getDouble(5);
			Col_ratio=(rs.getDouble(5)/rs.getDouble(4)) *100;
			m_balance_inv=rs.getDouble(6)-rs.getDouble(7);
			inv_ratio=(rs.getDouble(7)/rs.getDouble(6)) *100;
			
			total_inv_amount=rs.getDouble(4)+rs.getDouble(6);
			total_settle_amount=rs.getDouble(5)+rs.getDouble(7);
			tot_balance=total_inv_amount-total_settle_amount;
			tot_ratio=(total_settle_amount/total_inv_amount) *100; 
			
			
		  out.println("<tr bgcolor=\"#CCCCCC\" onClick=\"show_movement_drill('"+rs.getString(9)+"','"+m_date+"')\" style=\"{cursor:hand;}\" >");
			out.println("<td width=\"10%\" align=left ><u>"+rs.getString(2)+"</u></td>"); 
			out.println("<td width=\"7%\" align=left><b>"+rs.getString(8)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_inv_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_settle_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 
			
			tot_os =tot_os+rs.getDouble(4);
			tot_os_collect=tot_os_collect+rs.getDouble(5);
			tot_os_balance=tot_os_balance+m_balance;
			
			tot_inv =tot_inv+rs.getDouble(6);
			tot_inv_collect=tot_inv_collect+rs.getDouble(7);
			tot_inv_balance=tot_inv_balance+m_balance_inv;
			
			tot_all =tot_all+total_inv_amount;
			tot_all_collect=tot_all_collect+total_settle_amount;
			tot_all_balance=tot_all_balance+tot_balance;

			more=rs.next();
			}
			
			out.println("<tr bgcolor='cyan'>");
			out.println("<td width=\"10%\" align=left><b>Total</td>"); 
			out.println("<td width=\"7%\" align=left><b>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 
			
		out.println("</table >"); 
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
				
		if(req.getParameter("sub_team_id")!=null ){
		m_sub_team_id=req.getParameter("sub_team_id").trim();
		}
		
		if(req.getParameter("team_id")!=null ){
		m_team_id=req.getParameter("team_id").trim();
		}
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}

					
		stmt = conn.createStatement ();
		
		 rs=stmt.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY') , TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon')   FROM DUAL ");
			
			rs.next();
			String report_date=rs.getString(1);
			String Month=rs.getString(2);
			
			rs=stmt.executeQuery(
			" SELECT a.team_id,"+ //1
			" sub_team_desc ,"+ //2
			" a.sub_team_id,  "+ //3
			" (sum(a.total_amount) - sum(a.settled_amount) + sum(a.adjusted_amount)) balance, "+ //4
			" sum(a.settled_amount_cur_mon) settled_amount, "+ //5
			" sum(a.tot_inv_gen_cur_mon) cur_inv, "+ //6
			" sum(a.tot_inv_set_cur_mon) cur_set "+ //7
			//" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a "+
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a , "+m_schema_name+".AF_CO_MAS_SUB_TEAMS b "+
			" where a.sub_team_id=b.sub_team_id  "+
			" and a.team_id='"+m_team_id+"' "+
			" AND a.ent_user='"+m_username+"' "+
			" group by a.team_id,a.sub_team_id,sub_team_desc ");
			

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill_sub_team(m_sub_team_id,m_date){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_sub_team&date=\"+m_date+\"&sub_team_id=\"+m_sub_team_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<br>");			
		  out.println("<tr class=tr_input1>");
			out.println("<td width=\"10%\" align=left  ><b>Sub Team</td>"); 
			out.println("<td width=\"7%\" align=right ><b>B/F Bal </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection From Balance at "+Month+" </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Invoiced </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Invoiced Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Collected Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Ratio %</td>"); 
			out.println("</tr >"); 
			
			boolean more=rs.next();
			
			int row=0;
		  int j=1;
			double Col_ratio=0;
			double inv_ratio=0;
		  double total_inv_amount=0;
			double total_settle_amount=0;
			double tot_balance=0;
			double tot_ratio=0;
			
			double tot_os =0;
			double tot_os_collect=0;
			double tot_os_balance=0;
			
			double tot_inv =0;
			double tot_inv_collect=0;
			double tot_inv_balance=0;
			
			double tot_all =0;
			double tot_all_collect=0;
			double tot_all_balance=0;
			
			double tot_Col_ratio=0;
			double tot_inv_ratio=0;
			double tot_tot_ratio=0;

			double m_balance=0,m_balance_inv=0;
			
			while(more){
			m_balance=rs.getDouble(4)-rs.getDouble(5);
			Col_ratio=(rs.getDouble(5)/rs.getDouble(4)) *100;
			m_balance_inv=rs.getDouble(6)-rs.getDouble(7);
			inv_ratio=(rs.getDouble(7)/rs.getDouble(6)) *100;
			
			total_inv_amount=rs.getDouble(4)+rs.getDouble(6);
			total_settle_amount=rs.getDouble(5)+rs.getDouble(7);
			tot_balance=total_inv_amount-total_settle_amount;
			tot_ratio=(total_settle_amount/total_inv_amount) *100; 
			
			
		  out.println("<tr bgcolor=\"#CCCCCC\" onClick=\"show_movement_drill_sub_team('"+rs.getString(3)+"','"+m_date+"')\" style=\"{cursor:hand;}\" >");
			out.println("<td width=\"10%\" align=left ><u>"+rs.getString(2)+"</u></td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_inv_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_settle_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 
			
			tot_os =tot_os+rs.getDouble(4);
			tot_os_collect=tot_os_collect+rs.getDouble(5);
			tot_os_balance=tot_os_balance+m_balance;
			
			tot_inv =tot_inv+rs.getDouble(6);
			tot_inv_collect=tot_inv_collect+rs.getDouble(7);
			tot_inv_balance=tot_inv_balance+m_balance_inv;
			
			tot_all =tot_all+total_inv_amount;
			tot_all_collect=tot_all_collect+total_settle_amount;
			tot_all_balance=tot_all_balance+tot_balance;

			more=rs.next();
			}
			
			out.println("<tr bgcolor='cyan'>");
			out.println("<td width=\"10%\" align=left><b>Total</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 			
		
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
		String m_report_type="",m_report_desc="";
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		if(req.getParameter("report_type")!=null ){
		m_report_type=req.getParameter("report_type").trim();
		}
		
		if(m_report_type.equals("N_LEG")){ 
		m_report_desc="Live Contracts";
		}else{
		m_report_desc="Legal Contracts";
		}


		stmt = conn.createStatement ();
		
		  rs=stmt.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY') , TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon')   FROM DUAL ");
			
			rs.next();
			String report_date=rs.getString(1);
			String Month=rs.getString(2);
			
			if(m_report_type.equals("N_LEG")){
			
			
			rs=stmt.executeQuery(
			" SELECT a.team_id,"+ //1
			" team_desc, "+
			" '', /*a.sub_team_id */   "+ //3
			" (sum(a.total_amount) - sum(a.settled_amount) + sum(a.adjusted_amount)) balance, "+ //4
			" sum(a.settled_amount_cur_mon) settled_amount, "+ //5
			" sum(a.tot_inv_gen_cur_mon) cur_inv, "+ //6
			" sum(a.tot_inv_set_cur_mon) cur_set "+ //7
			//" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a "+
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a , "+m_schema_name+".AF_CO_MAS_TEAMS b "+
			" where a.team_id=b.team_id  "+
			" and a.ent_user='"+m_username+"' "+
			" group by a.team_id, team_desc ");
			}
			
			else if(m_report_type.equals("LEG")){//Added by Sandun on 05-08-08
				rs=stmt.executeQuery(
			" SELECT a.team_id,"+ //1
			" team_desc, "+
			" '', /*a.sub_team_id */   "+ //3
			" (sum(a.total_amount) - sum(a.settled_amount) + sum(a.adjusted_amount)) balance, "+ //4
			" sum(a.settled_amount_cur_mon) settled_amount, "+ //5
			" sum(a.tot_inv_gen_cur_mon) cur_inv, "+ //6
			" sum(a.tot_inv_set_cur_mon) cur_set "+ //7
			//" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a "+
			" FROM "+m_schema_name+".af_re_tbd_col_movement_rpt a , "+m_schema_name+".AF_CO_MAS_TEAMS b "+
			" where a.team_id=b.team_id  "+
			" and a.ent_user='"+m_username+"' "+
			" and a.collection_officer='L001'"+
			" group by a.team_id, team_desc ");
			
			}

		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_movement_drill_team(m_team_id,m_date){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_team&date=\"+m_date+\"&team_id=\"+m_team_id;"); 
			out.println("window.open(m_url);");	
			out.println("}");	
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr class=tr_input1>");
			out.println("<td width=\"*%\" align=center  ><u><b>Daily Collection Movement as at "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("<tr >");
			out.println("<td width=\"*%\" align=center  >Report Type - "+m_report_desc+" </td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<br>");			
		  out.println("<tr class=tr_input1>");
			out.println("<td width=\"10%\" align=left  ><b>Team</td>"); 
			out.println("<td width=\"7%\" align=right ><b>B/F Bal </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection From Balance at "+Month+" </td>");  //
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Invoiced </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection </td>"); 
			out.println("<td width=\"7%\" align=right ><b>Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Curr.Mon Collection Ratio %</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Invoiced Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Collected Amount</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Balance</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Total Ratio %</td>"); 
			out.println("</tr >"); 
			
			boolean more=rs.next();
			
			int row=0;
		  int j=1;
			double Col_ratio=0;
			double inv_ratio=0;
		  double total_inv_amount=0;
			double total_settle_amount=0;
			double tot_balance=0;
			double tot_ratio=0;
			
			double tot_os =0;
			double tot_os_collect=0;
			double tot_os_balance=0;
			
			double tot_inv =0;
			double tot_inv_collect=0;
			double tot_inv_balance=0;
			
			double tot_all =0;
			double tot_all_collect=0;
			double tot_all_balance=0;
			
			double tot_Col_ratio=0;
			double tot_inv_ratio=0;
			double tot_tot_ratio=0;

			double m_balance=0,m_balance_inv=0;
			
			while(more){
			m_balance=rs.getDouble(4)-rs.getDouble(5);
			Col_ratio=(rs.getDouble(5)/rs.getDouble(4)) *100;
			m_balance_inv=rs.getDouble(6)-rs.getDouble(7);
			inv_ratio=(rs.getDouble(7)/rs.getDouble(6)) *100;
			
			total_inv_amount=rs.getDouble(4)+rs.getDouble(6);
			total_settle_amount=rs.getDouble(5)+rs.getDouble(7);
			tot_balance=total_inv_amount-total_settle_amount;
			tot_ratio=(total_settle_amount/total_inv_amount) *100; 
			
			
		  out.println("<tr bgcolor=\"#CCCCCC\" onClick=\"show_movement_drill_team('"+rs.getString(1)+"','"+m_date+"')\" style=\"{cursor:hand;}\" >");
			out.println("<td width=\"10%\" align=left ><u>"+rs.getString(2)+"</u></td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(m_balance_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_inv_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(total_settle_amount)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 
			
			tot_os =tot_os+rs.getDouble(4);
			tot_os_collect=tot_os_collect+rs.getDouble(5);
			tot_os_balance=tot_os_balance+m_balance;
			
			tot_inv =tot_inv+rs.getDouble(6);
			tot_inv_collect=tot_inv_collect+rs.getDouble(7);
			tot_inv_balance=tot_inv_balance+m_balance_inv;
			
			tot_all =tot_all+total_inv_amount;
			tot_all_collect=tot_all_collect+total_settle_amount;
			tot_all_balance=tot_all_balance+tot_balance;

			more=rs.next();
			}
			
			out.println("<tr bgcolor='cyan'>");
			out.println("<td width=\"10%\" align=left><b>Total</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_os_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(Col_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_inv_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(inv_ratio)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_collect)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_all_balance)+"</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(tot_ratio)+"</td>"); 
			out.println("</tr >"); 
			
		
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
