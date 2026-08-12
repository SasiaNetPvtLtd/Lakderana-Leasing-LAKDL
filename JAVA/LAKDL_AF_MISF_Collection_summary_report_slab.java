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



public class LAKDL_AF_MISF_Collection_summary_report_slab extends javax.servlet.http.HttpServlet { 
	
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
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
		Statement stmt=null,stmt2=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null;
		
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
				String m_finance_no = req.getParameter("finance_no");
				String m_branch = req.getParameter("branch"); // added by udara on 09-10-2013
				String m_coll_off = req.getParameter("coll_off"); // added by udara on 09-10-2013
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REPORT(:1,:2);END;"); // commented by udara on 09-10-2013
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3);END;"); //callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_username);
					
					//callstmt1.setString(3,m_branch);  // added by udara on 09-10-2013
					callstmt1.setString(3,m_coll_off);  // added by udara on 09-10-2013
					
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					ex.printStackTrace();
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report</TITLE>"); 
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
				
				out.println("   m_branch   = //; "); // out.println("   m_branch   = document.Form1.TXT_LOCATION_CODE.value; "); // added by udara on 09-10-2013
				out.println("   m_coll_off = document.Form1.TXT_USER.value; "); // added by udara on 09-10-2013
				
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=run_report&date=\"+m_date+\"&branch=\"+m_branch+\"&coll_off=\"+m_coll_off;"); 
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("var reportReady = false;");
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			reportReady = true;"); 
				out.println("			print_report2();"); 
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
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&date=\"+m_date;");	
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				out.println("function show_branch_level_drill_down_all(){ ");  
				out.println("		 m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new_drill_level_all&date='+m_date+'&location='+'';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				
				out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("     help_button_user(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("			document.Form1.TXT_USER.value=data_vec[0]");
				//out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[2]");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M1' ){"); // out.println("			if(data_vec.length==0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("     help_update(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M1' ){"); // out.println("			if(data_vec.length>0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				//out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[0]");
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
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_marketing_offcer&data_val=\"+obj.value+\"&data_val2=\"+\"\"+\"&ac_status=Y\";");	 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_marketing_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_LOCATION_CODE.value+\"&ac_status=Y\";");	
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Collection Summary Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Summary Summary Report - \"+document.Form1.hid_status.value;"); 
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
				//out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
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
				out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+\"\"+\"@Y@\";");  // out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				//out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				//out.println("document.Form1.TXT_LOCATION_CODE.value='';");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Summary Report </td>"); 
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
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View All Branch\" onClick=\"show_branch_level_drill_down_all()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				/*
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				*/
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				
				/*
				out.println("<tr >"); 
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
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				/*added by ns on 07-01-2012*/
				out.println("function show_branch_level_drill_down(mm_location){ ");  
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location;"); 
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
				
				/*
				
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
				*/
				
				String Sql_data="";
				
				
				Sql_data="  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
				//Sql_data="  SELECT    A.LOCATION,"+
					"            SUM ( A.PENALTY_TO_DATE ), SUM ( A.PENALTY ), "+
					"            SUM ( A.INVOICE_TO_DATE ), SUM ( A.INVOICE ),"+
					"            SUM ( A.INS_INVOICE_TO_DATE ), SUM ( A.INS_INVOICE ),"+
					"            SUM ( A.REN_INVOICE_TO_DATE ), SUM ( A.REN_INVOICE ),"+
					"            SUM ( A.INSURENCE_TO_DATE ), SUM ( A.INSURENCE ),"+
					"            SUM ( A.ARREARS_TO_DATE ), SUM ( A.ARREARS ),"+
					"            SUM ( A.CLOSING_TO_DATE ), SUM ( A.CLOSING ),"+
					"            SUM ( A.RENTAL_TO_DATE ), SUM ( A.RENTAL ), SUM ( A.EXCESS_TO_DATE ),"+
					"            SUM ( A.EXCESS ),"+
					"            A.LOCATION  LOCATION, "+
					"            SUM ( NVL(A.TOTAL_TO_DATE,0) ),"+
					"            SUM ( NVL(A.TOTAL,0) ) "+
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_REPORT A"+
					"    WHERE   A.ENT_USER = '"+m_username+"' "+
					" GROUP BY   A.LOCATION ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') ";
				//" ORDER BY   "+m_sort_column+"  "+m_order_by_type+" ";	 
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
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
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>BRANCH</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2'  STYLE='{text-align:center; }'><b>PENALTY</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }'><b>INVOICE</b></td>");
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INS.INVOICE</b></td>"); 
					//out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; display: none; }'  ><b>REN.INVOICE</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INSURANCE</b></td>");
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>ARREARS</b></td>"); 	
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>CLOSING</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>RENTAL</b></td>");    
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>EXCESS</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total w/o Insurance & Invoice Insurance</b></td>"); // Added by udara 09-04-2014
					
					out.println("</tr >");
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					//out.println("<td class=factoring-letter-body ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>Total to-date</b></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 09-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 09-04-2014
					
					out.println("</tr >");
					
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total;
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date;
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = new BigDecimal(0.00);
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = new BigDecimal(0.00);
					
					int j = 1;
					while(more){
						
						
						out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						out.println("<td class=factoring-letter-body  STYLE='{cursor:hand}' onClick=\"show_branch_level_drill_down('"+rs.getString("LOCATION")+"')\" ><b>"+rs.getString(1)+"</b></td>"); 
						m_total_penalty_to_date = m_total_penalty_to_date.add(rs.getBigDecimal(2));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(2))+"</td>");
						m_total_penalty = m_total_penalty.add(rs.getBigDecimal(3));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(3))+"</td>");
						m_total_invoice_to_date = m_total_invoice_to_date.add(rs.getBigDecimal(4));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						m_total_invoice = m_total_invoice.add(rs.getBigDecimal(5));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;  }'  >"+nf.format(rs.getBigDecimal(5))+"</td>"); 
						m_total_ins_invoice_to_date = m_total_ins_invoice_to_date.add(rs.getBigDecimal(6));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(6))+"</td>");
						m_total_ins_invoice = m_total_ins_invoice.add(rs.getBigDecimal(7));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(7))+"</td>"); 	
						m_total_ren_invoice_to_date = m_total_ren_invoice_to_date.add(rs.getBigDecimal(8));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(8))+"</td>"); 
						m_total_ren_invoice = m_total_ren_invoice.add(rs.getBigDecimal(9));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(9))+"</td>");    
						m_total_insurence_to_date = m_total_insurence_to_date.add(rs.getBigDecimal(10));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(10))+"</td>"); 
						m_total_insurence = m_total_insurence.add(rs.getBigDecimal(11));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(11))+"</td>"); 
						m_total_arrears_to_date = m_total_arrears_to_date.add(rs.getBigDecimal(12));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(12))+"</td>"); 
						m_total_arrears = m_total_arrears.add(rs.getBigDecimal(13));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(13))+"</td>");
						m_total_closing_to_date = m_total_closing_to_date.add(rs.getBigDecimal(14));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(14))+"</td>"); 
						m_total_closing = m_total_closing.add(rs.getBigDecimal(15));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(15))+"</td>"); 
						m_total_rental_to_date = m_total_rental_to_date.add(rs.getBigDecimal(16));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(16))+"</td>");
						m_total_rental = m_total_rental.add(rs.getBigDecimal(17));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(17))+"</td>"); 	
						m_total_excess_to_date = m_total_excess_to_date.add(rs.getBigDecimal(18));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(18))+"</td>"); 
						m_total_excess = m_total_excess.add(rs.getBigDecimal(19));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(19))+"</td>");    
						m_total_to_date = m_total_to_date.add(rs.getBigDecimal(22));
							/*.add(rs.getBigDecimal(4))
							.add(rs.getBigDecimal(6))
							.add(rs.getBigDecimal(8))
							.add(rs.getBigDecimal(10))
							.add(rs.getBigDecimal(12))
							.add(rs.getBigDecimal(14))
							.add(rs.getBigDecimal(16))
							.add(rs.getBigDecimal(18));*/
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(21))+"</td>"); // total
						m_total = m_total.add(rs.getBigDecimal(22));
							/*.add(rs.getBigDecimal(5))
							.add(rs.getBigDecimal(7))
							.add(rs.getBigDecimal(9))
							.add(rs.getBigDecimal(11))
							.add(rs.getBigDecimal(13))
							.add(rs.getBigDecimal(15))
							.add(rs.getBigDecimal(17))
							.add(rs.getBigDecimal(19));*/
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22))+"</td>"); // total to date
					
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(21).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"</td>"); // added by udara 09-04-2014
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"</td>"); // added by udara 09-04-2014
						
						out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					BigDecimal m_total_to_date_new = new BigDecimal(0.00); // added by udara obn 10-07-2013
					m_total_to_date_new = m_total_to_date_new.add(m_total_penalty_to_date).add(m_total_invoice_to_date).add(m_total_ins_invoice_to_date).add(m_total_ren_invoice_to_date).add(m_total_insurence_to_date).add(m_total_arrears_to_date).add(m_total_closing_to_date).add(m_total_rental_to_date).add(m_total_excess_to_date);
					
					// added by udara 09-04-2014
					BigDecimal m_total_to_date_new_wo_insurance = new BigDecimal(0.00); 
					m_total_to_date_new_wo_insurance = m_total_to_date_new.subtract(m_total_insurence_to_date).subtract(m_total_ins_invoice_to_date); 
					
					BigDecimal m_total_wo_insurance = new BigDecimal(0.00); 
					m_total_wo_insurance = m_total.subtract(m_total_insurence).subtract(m_total_ins_invoice); 
					// added by udara 09-04-2014
					
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_invoice_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_invoice)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice)+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice_to_date)+"</b></td>"); 
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_closing_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_closing)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental)+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess)+"</b></td>");    
					//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date)+"</b></td>"); // commented by udara on 10-07-2013
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new)+"</b></td>");  // added by udara on 10-07-2012
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total)+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new_wo_insurance)+"</b></td>");  // added by udara on 09-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_wo_insurance)+"</b></td>");  // added by udara on 09-04-2014
					
					out.println("</tr >");
					

					out.println("</tr>");		
					out.println("</table>");		
					out.println("</td> "); 
					out.println("</tr>");		
					out.println("</table>");	
				}
				
				
				
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
				else if(m_chksql.equals("print_report_new_drill_level_01")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String mm_location="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				/*if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				*/
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				if(req.getParameter("location")!=null ){
					mm_location=req.getParameter("location").trim();
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				/*
				
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
				*/
				
				String Sql_data="";
				
				
				Sql_data="  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
					"            SUM ( A.PENALTY_TO_DATE ), SUM ( A.PENALTY ), "+
					"            SUM ( A.INVOICE_TO_DATE ), SUM ( A.INVOICE ),"+
					"            SUM ( A.INS_INVOICE_TO_DATE ), SUM ( A.INS_INVOICE ),"+
					"            SUM ( A.REN_INVOICE_TO_DATE ), SUM ( A.REN_INVOICE ),"+
					"            SUM ( A.INSURENCE_TO_DATE ), SUM ( A.INSURENCE ),"+
					"            SUM ( A.ARREARS_TO_DATE ), SUM ( A.ARREARS ),"+
					"            SUM ( A.CLOSING_TO_DATE ), SUM ( A.CLOSING ),"+
					"            SUM ( A.RENTAL_TO_DATE ), SUM ( A.RENTAL ), SUM ( A.EXCESS_TO_DATE ),"+
					"            SUM ( A.EXCESS )"+
					"            ,FINANCE_NO ,CLIENT_CODE, "+
						"         SUM ( NVL(A.TOTAL_TO_DATE,0) ),"+
					"            SUM ( NVL(A.TOTAL,0) ) "+
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_REPORT A"+
					"    WHERE   A.ENT_USER = '"+m_username+"'  "+
					"    AND     A.LOCATION = '"+mm_location+"' "+
					"    GROUP BY  FINANCE_NO,CLIENT_CODE, A.LOCATION ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') "+
					""+
					"";
				//" ORDER BY   "+m_sort_column+"  "+m_order_by_type+" ";	 
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report Branch - "+mm_location+"  </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
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
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>Finance No</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2'  STYLE='{text-align:center; }'><b>PENALTY</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }'><b>INVOICE</b></td>");
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INS.INVOICE</b></td>"); 
					//out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; display: none; }'  ><b>REN.INVOICE</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INSURANCE</b></td>");
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>ARREARS</b></td>"); 	
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>CLOSING</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>RENTAL</b></td>");    
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>EXCESS</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total w/o Insurance & Invoice Insurance</b></td>"); // added by udara 10-09-2014
					
					
					out.println("</tr >");
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					//out.println("<td class=factoring-letter-body ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>Total to-date</b></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 10-04-2014
					
					out.println("</tr >");
					
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total;
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date;
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = new BigDecimal(0.00);
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = new BigDecimal(0.00);
					
					int j = 1;
					while(more){
						
						
						out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						out.println("<td class=factoring-letter-body onClick=\"show_transaction_history_new('"+rs.getString("CLIENT_CODE")+"','"+rs.getString("FINANCE_NO")+"')\"  STYLE='{font:  8pt arial; text-align:left;cursor:hand; }' ><b>"+rs.getString("FINANCE_NO")+"</b></td>"); 
						m_total_penalty_to_date = m_total_penalty_to_date.add(rs.getBigDecimal(2));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(2))+"</td>");
						m_total_penalty = m_total_penalty.add(rs.getBigDecimal(3));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(3))+"</td>");
						m_total_invoice_to_date = m_total_invoice_to_date.add(rs.getBigDecimal(4));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						m_total_invoice = m_total_invoice.add(rs.getBigDecimal(5));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(5))+"</td>"); 
						m_total_ins_invoice_to_date = m_total_ins_invoice_to_date.add(rs.getBigDecimal(6));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(6))+"</td>");
						m_total_ins_invoice = m_total_ins_invoice.add(rs.getBigDecimal(7));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(7))+"</td>"); 	
						m_total_ren_invoice_to_date = m_total_ren_invoice_to_date.add(rs.getBigDecimal(8));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(8))+"</td>"); 
						m_total_ren_invoice = m_total_ren_invoice.add(rs.getBigDecimal(9));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(9))+"</td>");    
						m_total_insurence_to_date = m_total_insurence_to_date.add(rs.getBigDecimal(10));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(10))+"</td>"); 
						m_total_insurence = m_total_insurence.add(rs.getBigDecimal(11));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(11))+"</td>"); 
						m_total_arrears_to_date = m_total_arrears_to_date.add(rs.getBigDecimal(12));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(12))+"</td>"); 
						m_total_arrears = m_total_arrears.add(rs.getBigDecimal(13));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(13))+"</td>");
						m_total_closing_to_date = m_total_closing_to_date.add(rs.getBigDecimal(14));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(14))+"</td>"); 
						m_total_closing = m_total_closing.add(rs.getBigDecimal(15));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(15))+"</td>"); 
						m_total_rental_to_date = m_total_rental_to_date.add(rs.getBigDecimal(16));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(16))+"</td>");
						m_total_rental = m_total_rental.add(rs.getBigDecimal(17));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(17))+"</td>"); 	
						m_total_excess_to_date = m_total_excess_to_date.add(rs.getBigDecimal(18));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(18))+"</td>"); 
						m_total_excess = m_total_excess.add(rs.getBigDecimal(19));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(19))+"</td>");    
						m_total_to_date = m_total_to_date.add(rs.getBigDecimal(22));
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22))+"</td>"); 
						m_total = m_total.add(rs.getBigDecimal(23));
							
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23))+"</td>"); 
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"</td>"); // added by udara 10-04-2014
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"</td>"); // added by udara 10-04-2014
						
						
						out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					// added by udara 10-04-2014
					BigDecimal m_total_to_date_new_wo_insurance = new BigDecimal(0.00); 
					m_total_to_date_new_wo_insurance = m_total_to_date.subtract(m_total_insurence_to_date).subtract(m_total_ins_invoice_to_date); 
					
					BigDecimal m_total_wo_insurance = new BigDecimal(0.00); 
					m_total_wo_insurance = m_total.subtract(m_total_insurence).subtract(m_total_ins_invoice); 
					// added by udara 10-04-2014
					
					
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_invoice_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_invoice)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice)+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice_to_date)+"</b></td>"); 
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_closing_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_closing)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental)+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total)+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					
					out.println("</tr >");
					
					/*
					m_total_due=rs.getDouble(11)+rs.getDouble(7);
					
					if(rs.getDouble(6) <=0  ){
						m_total_due_0+=m_total_due;
						m_total_coll_0+=rs.getDouble(8);
					}
					else if(rs.getDouble(6) >0 && rs.getDouble(6) <= 1 ){
						m_total_due_1+=m_total_due;
						m_total_coll_1+=rs.getDouble(8);
					}
					else if(rs.getDouble(6) > 1 && rs.getDouble(6) <= 2 ){
						m_total_due_2+=m_total_due;
						m_total_coll_2+=rs.getDouble(8);
					}
					else if(rs.getDouble(6) > 2 && rs.getDouble(6) <= 3 ){
						m_total_due_3+=m_total_due;
						m_total_coll_3+=rs.getDouble(8);
					}
					else if(rs.getDouble(6) > 3 && rs.getDouble(6) <= 4 ){
						m_total_due_4+=m_total_due;
						m_total_coll_4+=rs.getDouble(8);
					}
					else if(rs.getDouble(6) > 4 && rs.getDouble(6) <= 5 ){
						m_total_due_5+=m_total_due;
						m_total_coll_5+=rs.getDouble(8);
					}
					else if(rs.getDouble(6) > 5 && rs.getDouble(6) <= 6 ){
						m_total_due_6+=m_total_due;
						m_total_coll_6+=rs.getDouble(8);
					}
					
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); 
					out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body  bgcolor='lightblue' onClick=\"show_transaction_history_new('"+rs.getString(2)+"','"+rs.getString(1)+"')\" STYLE='{text-align:left;cursor:hand; }'   ><u>"+rs.getString(1)+"</u></td>"); 
					out.println("<td  class=factoring-letter-body   bgcolor='lightblue'  STYLE='{text-align:center;}'   >"+rs.getString(19)+"</td>"); //thamali 2012.03.29
					out.println("<td  class=factoring-letter-body  bgcolor='lightblue' onClick=\"show_client('"+rs.getString(2)+"')\" class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString(3)+"</u></td>"); 
					out.println("<td  class=factoring-letter-body  bgcolor='lightblue' STYLE='{text-align:left;cursor:hand}' onClick=\"update_contract_detail('"+rs.getString(2)+"')\"   >"+rs.getString(10)+"</td>"); 
					out.println("<td  class=factoring-letter-body  bgcolor='lightblue' style=cursor:hand >"+rs.getString(20)+"</td>");//Added By Prabash on 09-08-2012
					out.println("<td  class=factoring-letter-body   bgcolor='lightblue'  STYLE='{text-align:center;}'   >"+rs.getString(5)+"</td>"); 
					out.println("<td  class=factoring-letter-body  bgcolor='lightblue'  STYLE='{text-align:right;}'   >"+nf1.format(rs.getDouble(4))+"</td>"); 
					
					if(rs.getDouble(6) < 0 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=green; }'  ><b>"+nf.format(rs.getDouble(6))+"</td>"); 
					}
					else if(rs.getDouble(6) >= 0 && rs.getDouble(6) < 2 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=green; }'  ><b>"+nf.format(rs.getDouble(6))+"</td>"); 
					}
					else if(rs.getDouble(6) >= 2 && rs.getDouble(6) < 3 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=orange;}'  ><b>"+nf.format(rs.getDouble(6))+"</td>"); 
					}
					else if(rs.getDouble(6) >= 3 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=red; }'   ><b>"+nf.format(rs.getDouble(6))+"</td>"); 
					}
					
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(7))+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(11))+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(m_total_due)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(8))+"</td>"); 
					
					closing_bal=(rs.getDouble(11)+rs.getDouble(7) ) - rs.getDouble(8);
					out.println("<td class=factoring-letter-body    STYLE='{text-align:right;}'  >"+nf1.format(closing_bal)+"</td>"); 
					
					if(rs.getDouble(6) < 0 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=green; }'  ><b>"+nf.format(rs.getDouble(6))+"</td>"); 
					}
					else if(rs.getDouble(9) >= 0 && rs.getDouble(9) < 2 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=green; }'  ><b>"+nf.format(rs.getDouble(9))+"</td>"); 
					}
					else if(rs.getDouble(9) >= 2 && rs.getDouble(9) < 3 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=orange;}'  ><b>"+nf.format(rs.getDouble(9))+"</td>"); 
					}
					else if(rs.getDouble(9) >= 3 ){
						out.println("<td  class=factoring-letter-body  STYLE='{text-align:right; color=red; }'  ><b>"+nf.format(rs.getDouble(9))+"</td>"); 
					}
					
					// thamali 2012.03.29
					if(rs.getDouble(9) >= 0 && rs.getDouble(9) < 1){
						out.println("<td  class=factoring-letter-body    >-</td>"); 
					}
					else if(rs.getDouble(9) >= 1 && rs.getDouble(9) < 2 ){
						out.println("<td  class=factoring-letter-body    >NR</td>"); 
					}
					else if(rs.getDouble(9) >= 2 && rs.getDouble(9) < 3 ){
						out.println("<td  class=factoring-letter-body    >RR</td>"); 
					}
					else if(rs.getDouble(9) >= 3 && rs.getDouble(9) < 4 ){
						out.println("<td  class=factoring-letter-body    >CV</td>"); 
					}
					else if(rs.getDouble(9) >= 4 ){
						out.println("<td  class=factoring-letter-body   >Z</td>"); 
					}
					
					
					
					
					
					
					achievement=(rs.getDouble(8)/m_total_due)*100;
					out.println("<td  class=factoring-letter-body STYLE='{text-align:right;}'  >"+nf1.format(achievement)+"%</td>"); 
					out.println("<td  class=factoring-letter-body bgcolor='lightblue' STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(7)+rs.getDouble(14))+"</td>");  //tot.op
					out.println("<td  class=factoring-letter-body bgcolor='lightblue' STYLE='{text-align:right;}'  >"+nf1.format(closing_bal+rs.getDouble(15))+"</td>"); //tot.col
					out.println("<td  class=factoring-letter-body style=cursor:hand onClick=\"add_client_comments('"+rs.getString(2)+"','"+rs.getString(1)+"')\"><u>Remarks</u></td>");
					out.println("<td  class=factoring-letter-body style=cursor:hand onClick=\"show_followup('"+rs.getString(1)+"')\"><u>"+rs.getString(16)+"</u></td>");
					out.println("<td  class=factoring-letter-body style=cursor:hand >"+rs.getString(17)+"</td>");//Added By Lalanka on 22-06-2009
					out.println("<td  class=factoring-letter-body style=cursor:hand >"+rs.getString(18)+"</td>");//Added By Lalanka on 15-07-2009
					
					out.println("</tr>");
					
					closing_bal=m_total_due - rs.getDouble(8);
					total_mon_rental+=rs.getDouble(4);
					total_open_bal+=rs.getDouble(7);
					sub_open+=rs.getDouble(7)+rs.getDouble(14);
					total_bal+=m_total_due;
					total_curr_due+=rs.getDouble(11);
					total_collection+=rs.getDouble(8);
					total_closing_bal+=closing_bal;
					sub_close+=closing_bal+rs.getDouble(15);
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_mon_rental)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_open_bal)+"</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_curr_due)+"</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_bal)+"</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_collection)+"</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_closing_bal)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}' >&nbsp;</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{arial; text-align:right;}'  ><b>"+nf1.format(sub_open)+"</td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(sub_close)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}' >&nbsp;</td>"); 
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
					open_pre=(total_bal/total_bal)*100;
					//cur_pre=(total_cur_due/total_open_bal)*100;
					col_pre=(total_collection/(total_bal))*100;
					closing_pre=(total_closing_bal/(total_bal))*100;
					
					
					out.println("<tr>");		
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Precentage&nbsp;&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:right;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right;}'  ><b>"+nf1.format(open_pre)+"%</td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right;}'  ><b>"+nf1.format(col_pre)+"%</td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right;}'  ><b>"+nf1.format(closing_pre)+"%</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}' ></td>"); 
					out.println("</tr>");		
					
					
					
				}
				
				
				
				out.println("<tr>");		
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
				
				rs=stmt.executeQuery(
					" SELECT a.age, a.precentage "+
					" FROM "+m_schema_name+".af_co_mas_target_months a "+
					" WHERE A.AGE=0");
				
				if(rs.next()){
					out.println("<tr  >");		
					out.println("<td  >"+rs.getInt(1)+"</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2))+"%</td>"); 
					out.println("<td  >"+nf.format((m_total_coll_0/m_total_due_0)*100)+"%</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2) - ((m_total_coll_0/m_total_due_0)*100))+" %</td>"); 
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
					out.println("<td  >"+nf.format((m_total_coll_1/m_total_due_1)*100)+"%</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2) - ((m_total_coll_1/m_total_due_1)*100))+"% </td>"); 
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
					out.println("<td  >"+nf.format((m_total_coll_2/m_total_due_2)*100)+"%</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2) - ((m_total_coll_2/m_total_due_2)*100))+"% </td>"); 
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
					out.println("<td  >"+nf.format((m_total_coll_3/m_total_due_3)*100)+"%</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2) - ((m_total_coll_3/m_total_due_3)*100))+"% </td>"); 
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
					out.println("<td  >"+nf.format((m_total_coll_4/m_total_due_4)*100)+"%</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2) - ((m_total_coll_4/m_total_due_4)*100))+"% </td>"); 
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
					out.println("<td  >"+nf.format((m_total_coll_5/m_total_due_5)*100)+"%</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2) - ((m_total_coll_5/m_total_due_5)*100))+" %</td>"); 
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
					out.println("<td  >"+nf.format((m_total_coll_6/m_total_due_6)*100)+"%</td>"); 
					out.println("<td  >"+nf.format(rs.getDouble(2) - ((m_total_coll_6/m_total_due_6)*100))+"% </td>"); 
					out.println("</tr>");		
				}
				
				
				out.println("</table>");		
				out.println("</td>"); 
				*/
					out.println("</tr>");		
					out.println("</table>");		
					out.println("</td> "); 
					out.println("</tr>");		
					out.println("</table>");	
				}
				
				
				
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
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
				
				/*out.println("function sort_data(m_sort_col) {");
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
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=print_report&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				*/
				
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
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Weekly Report on Outstanding & Remittance</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
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
					//	out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center;}'   >Due Date</td>");      // Comment by Prabash on 09-05-2012
					out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center;}'   >Rental Date</td>");   // added by Prabash on 09-05-2012 change Due Date as a Rental Date 
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
			
			
			
			/*
			else if(m_chksql.equals("print_report_new_drill_level_all")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String mm_location="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				

				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				if(req.getParameter("location")!=null ){
					mm_location=req.getParameter("location").trim();
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				
				
				
				String Sql_data="";
				
				
				Sql_data="  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
					"            SUM ( A.PENALTY_TO_DATE ), SUM ( A.PENALTY ), "+
					"            SUM ( A.INVOICE_TO_DATE ), SUM ( A.INVOICE ),"+
					"            SUM ( A.INS_INVOICE_TO_DATE ), SUM ( A.INS_INVOICE ),"+
					"            SUM ( A.REN_INVOICE_TO_DATE ), SUM ( A.REN_INVOICE ),"+
					"            SUM ( A.INSURENCE_TO_DATE ), SUM ( A.INSURENCE ),"+
					"            SUM ( A.ARREARS_TO_DATE ), SUM ( A.ARREARS ),"+
					"            SUM ( A.CLOSING_TO_DATE ), SUM ( A.CLOSING ),"+
					"            SUM ( A.RENTAL_TO_DATE ), SUM ( A.RENTAL ), SUM ( A.EXCESS_TO_DATE ),"+
					"            SUM ( A.EXCESS )"+
					"            ,FINANCE_NO ,CLIENT_CODE, "+
						"         SUM ( NVL(A.TOTAL_TO_DATE,0) ),"+
					"            SUM ( NVL(A.TOTAL,0) ) "+
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_REPORT A"+
					"    WHERE   A.ENT_USER = '"+m_username+"'  "+
					//"    AND     A.LOCATION = '"+mm_location+"' "+
					"    GROUP BY  FINANCE_NO,CLIENT_CODE, A.LOCATION ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') "+
					""+
					"";
				//" ORDER BY   "+m_sort_column+"  "+m_order_by_type+" ";	 
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report Branch - "+mm_location+"  </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
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
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>Location</b></td>"); 
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>Finance No</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2'  STYLE='{text-align:center; }'><b>PENALTY</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }'><b>INVOICE</b></td>");
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INS.INVOICE</b></td>"); 
					//out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; display: none; }'  ><b>REN.INVOICE</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INSURANCE</b></td>");
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>ARREARS</b></td>"); 	
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>CLOSING</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>RENTAL</b></td>");    
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>EXCESS</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total w/o Insurance & Invoice Insurance</b></td>"); // added by udara 10-09-2014
					
					
					out.println("</tr >");
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					//out.println("<td class=factoring-letter-body ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>Total to-date</b></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 10-04-2014
					
					out.println("</tr >");
					
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total;
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date;
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = new BigDecimal(0.00);
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = new BigDecimal(0.00);
					
					int j = 1;
					while(more){
						
						
						out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						out.println("<td class=factoring-letter-body   STYLE='{font:  8pt arial; text-align:left;cursor:hand; }' ><b>"+rs.getString(1)+"</b></td>"); 
						out.println("<td class=factoring-letter-body onClick=\"show_transaction_history_new('"+rs.getString("CLIENT_CODE")+"','"+rs.getString("FINANCE_NO")+"')\"  STYLE='{font:  8pt arial; text-align:left;cursor:hand; }' ><b>"+rs.getString("FINANCE_NO")+"</b></td>"); 
						m_total_penalty_to_date = m_total_penalty_to_date.add(rs.getBigDecimal(2));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(2))+"</td>");
						m_total_penalty = m_total_penalty.add(rs.getBigDecimal(3));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(3))+"</td>");
						m_total_invoice_to_date = m_total_invoice_to_date.add(rs.getBigDecimal(4));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						m_total_invoice = m_total_invoice.add(rs.getBigDecimal(5));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(5))+"</td>"); 
						m_total_ins_invoice_to_date = m_total_ins_invoice_to_date.add(rs.getBigDecimal(6));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(6))+"</td>");
						m_total_ins_invoice = m_total_ins_invoice.add(rs.getBigDecimal(7));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(7))+"</td>"); 	
						m_total_ren_invoice_to_date = m_total_ren_invoice_to_date.add(rs.getBigDecimal(8));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(8))+"</td>"); 
						m_total_ren_invoice = m_total_ren_invoice.add(rs.getBigDecimal(9));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(9))+"</td>");    
						m_total_insurence_to_date = m_total_insurence_to_date.add(rs.getBigDecimal(10));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(10))+"</td>"); 
						m_total_insurence = m_total_insurence.add(rs.getBigDecimal(11));
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(11))+"</td>"); 
						m_total_arrears_to_date = m_total_arrears_to_date.add(rs.getBigDecimal(12));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(12))+"</td>"); 
						m_total_arrears = m_total_arrears.add(rs.getBigDecimal(13));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(13))+"</td>");
						m_total_closing_to_date = m_total_closing_to_date.add(rs.getBigDecimal(14));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(14))+"</td>"); 
						m_total_closing = m_total_closing.add(rs.getBigDecimal(15));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(15))+"</td>"); 
						m_total_rental_to_date = m_total_rental_to_date.add(rs.getBigDecimal(16));
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(16))+"</td>");
						m_total_rental = m_total_rental.add(rs.getBigDecimal(17));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(17))+"</td>"); 	
						m_total_excess_to_date = m_total_excess_to_date.add(rs.getBigDecimal(18));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(18))+"</td>"); 
						m_total_excess = m_total_excess.add(rs.getBigDecimal(19));
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(19))+"</td>");    
						m_total_to_date = m_total_to_date.add(rs.getBigDecimal(22));
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22))+"</td>"); 
						m_total = m_total.add(rs.getBigDecimal(23));
							
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23))+"</td>"); 
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"</td>"); // added by udara 10-04-2014
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"</td>"); // added by udara 10-04-2014
						
						
						out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					// added by udara 10-04-2014
					BigDecimal m_total_to_date_new_wo_insurance = new BigDecimal(0.00); 
					m_total_to_date_new_wo_insurance = m_total_to_date.subtract(m_total_insurence_to_date).subtract(m_total_ins_invoice_to_date); 
					
					BigDecimal m_total_wo_insurance = new BigDecimal(0.00); 
					m_total_wo_insurance = m_total.subtract(m_total_insurence).subtract(m_total_ins_invoice); 
					// added by udara 10-04-2014
					
					
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body ><b> &nbsp; </b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_invoice_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_invoice)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice)+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice_to_date)+"</b></td>"); 
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_closing_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_closing)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental)+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total)+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					
					out.println("</tr >");
					
					
					out.println("</tr>");		
					out.println("</table>");		
					out.println("</td> "); 
					out.println("</tr>");		
					out.println("</table>");	
				}
				
				
				
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			*/
			
			else if(m_chksql.equals("print_report_new_drill_level_all")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String mm_location="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				

				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				if(req.getParameter("location")!=null ){
					mm_location=req.getParameter("location").trim();
				}
				
				else{	}
				
				
				/*
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_slab?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				*/
				
				res.setContentType("text/csv");
				res.setHeader("Content-disposition","attachment; filename=Export.csv" );
				
				//Added by Dineth on 2008-11-17
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
				}
				
				
				
				
				String Sql_data="";
				
				
				Sql_data="  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
					"            SUM ( A.PENALTY_TO_DATE ), SUM ( A.PENALTY ), "+
					"            SUM ( A.INVOICE_TO_DATE ), SUM ( A.INVOICE ),"+
					"            SUM ( A.INS_INVOICE_TO_DATE ), SUM ( A.INS_INVOICE ),"+
					"            SUM ( A.REN_INVOICE_TO_DATE ), SUM ( A.REN_INVOICE ),"+
					"            SUM ( A.INSURENCE_TO_DATE ), SUM ( A.INSURENCE ),"+
					"            SUM ( A.ARREARS_TO_DATE ), SUM ( A.ARREARS ),"+
					"            SUM ( A.CLOSING_TO_DATE ), SUM ( A.CLOSING ),"+
					"            SUM ( A.RENTAL_TO_DATE ), SUM ( A.RENTAL ), SUM ( A.EXCESS_TO_DATE ),"+
					"            SUM ( A.EXCESS )"+
					"            ,FINANCE_NO ,CLIENT_CODE, "+
						"         SUM ( NVL(A.TOTAL_TO_DATE,0) ),"+
					"            SUM ( NVL(A.TOTAL,0) ) "+
					"     , A.LOCATION "+ // 24
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_REPORT A"+
					"    WHERE   A.ENT_USER = '"+m_username+"'  "+
					//"    AND     A.LOCATION = '"+mm_location+"' "+
					"    GROUP BY  FINANCE_NO,CLIENT_CODE, A.LOCATION ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') "+
					""+
					"";
				//" ORDER BY   "+m_sort_column+"  "+m_order_by_type+" ";	 
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				/*
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report Branch - "+mm_location+"  </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				*/

				
				if(!more){
					/*
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
					*/
				}else{
					
					/*
					out.println("<table  cellspacing=0 > "); 
					out.println("<tr> "); 
					out.println("<td width='20'> "); 
					out.println("</td> "); 
					out.println("<td> "); 
					out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
					
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>Location</b></td>"); 
					out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>Finance No</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2'  STYLE='{text-align:center; }'><b>PENALTY</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }'><b>INVOICE</b></td>");
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INS.INVOICE</b></td>"); 
					//out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; display: none; }'  ><b>REN.INVOICE</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>INSURANCE</b></td>");
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>ARREARS</b></td>"); 	
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>CLOSING</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{text-align:center; }' ><b>RENTAL</b></td>");    
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>EXCESS</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total w/o Insurance & Invoice Insurance</b></td>"); // added by udara 10-09-2014
					
					
					out.println("</tr >");
					*/
					
					
					out.print("Location,"); 
					out.print("Finance No,"); 
					out.print("PENALTY,,");
					out.print("INVOICE,,");
					out.print("INS.INVOICE,,");
					out.print("INSURANCE,,");
					out.print("ARREARS,,");
					out.print("CLOSING,,");
					out.print("RENTAL,,");
					out.print("EXCESS,,");
					out.print("Total,,");
					out.print("Total w/o Insurance & Invoice Insurance,,");
					out.print("\n");
					
					out.print("Location,"); 
					out.print("Finance No,"); 
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					out.print("Total to-date,");
					out.print(" "+m_date+" ,");
					
					
					out.print("\n");

					/*
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					//out.println("<td class=factoring-letter-body ><b></b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>Total to-date</b></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:center; display: none; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }'><b>"+m_date+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'  ><b>"+m_date+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>Total to-date</b></td>");
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>Total to-date</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 10-04-2014
					
					out.println("</tr >");
					*/
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total;
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date;
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = new BigDecimal(0.00);
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = new BigDecimal(0.00);
					
					int j = 1;
					while(more){
						
						out.print(" "+rs.getString(1)+" - "+rs.getString(24)+" ,");
						out.print(" "+rs.getString("FINANCE_NO")+" ,");
						
						
						
						
						//out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						//out.println("<td class=factoring-letter-body   STYLE='{font:  8pt arial; text-align:left;cursor:hand; }' ><b>"+rs.getString(1)+"</b></td>"); 
						//out.println("<td class=factoring-letter-body onClick=\"show_transaction_history_new('"+rs.getString("CLIENT_CODE")+"','"+rs.getString("FINANCE_NO")+"')\"  STYLE='{font:  8pt arial; text-align:left;cursor:hand; }' ><b>"+rs.getString("FINANCE_NO")+"</b></td>"); 
						
						m_total_penalty_to_date = m_total_penalty_to_date.add(rs.getBigDecimal(2));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(2))+"</td>");
						out.print("\""+nf.format(rs.getBigDecimal(2))+"\",");
						
						m_total_penalty = m_total_penalty.add(rs.getBigDecimal(3));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(3))+"</td>");
						out.print("\""+nf.format(rs.getBigDecimal(3))+"\",");
						
						m_total_invoice_to_date = m_total_invoice_to_date.add(rs.getBigDecimal(4));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(4))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(4))+"\",");
						
						
						m_total_invoice = m_total_invoice.add(rs.getBigDecimal(5));
						//out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(5))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(5))+"\",");
						
						m_total_ins_invoice_to_date = m_total_ins_invoice_to_date.add(rs.getBigDecimal(6));
						//out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(6))+"</td>");
						out.print("\""+nf.format(rs.getBigDecimal(6))+"\",");
						
						m_total_ins_invoice = m_total_ins_invoice.add(rs.getBigDecimal(7));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(7))+"</td>"); 	
						out.print("\""+nf.format(rs.getBigDecimal(7))+"\",");
						
						
						m_total_ren_invoice_to_date = m_total_ren_invoice_to_date.add(rs.getBigDecimal(8));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(8))+"</td>"); 
						m_total_ren_invoice = m_total_ren_invoice.add(rs.getBigDecimal(9));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' >"+nf.format(rs.getBigDecimal(9))+"</td>");    
						
						
						m_total_insurence_to_date = m_total_insurence_to_date.add(rs.getBigDecimal(10));
						//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(10))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(10))+"\",");
						
						m_total_insurence = m_total_insurence.add(rs.getBigDecimal(11));
						//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(11))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(11))+"\",");
						
						
						m_total_arrears_to_date = m_total_arrears_to_date.add(rs.getBigDecimal(12));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(12))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(12))+"\",");
						
						
						m_total_arrears = m_total_arrears.add(rs.getBigDecimal(13));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'>"+nf.format(rs.getBigDecimal(13))+"</td>");
						out.print("\""+nf.format(rs.getBigDecimal(13))+"\",");
						
						
						m_total_closing_to_date = m_total_closing_to_date.add(rs.getBigDecimal(14));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(14))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(14))+"\",");
						
						
						m_total_closing = m_total_closing.add(rs.getBigDecimal(15));
						//out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  >"+nf.format(rs.getBigDecimal(15))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(15))+"\",");
						
						
						m_total_rental_to_date = m_total_rental_to_date.add(rs.getBigDecimal(16));
						//out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(16))+"</td>");
						out.print("\""+nf.format(rs.getBigDecimal(16))+"\",");
						
						
						m_total_rental = m_total_rental.add(rs.getBigDecimal(17));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(17))+"</td>"); 	
						out.print("\""+nf.format(rs.getBigDecimal(17))+"\",");
						
						
						m_total_excess_to_date = m_total_excess_to_date.add(rs.getBigDecimal(18));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(18))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(18))+"\",");
						
						
						m_total_excess = m_total_excess.add(rs.getBigDecimal(19));
						//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(19))+"</td>");    
						out.print("\""+nf.format(rs.getBigDecimal(19))+"\",");
						
						
						m_total_to_date = m_total_to_date.add(rs.getBigDecimal(22));
						
						out.print("\""+nf.format(rs.getBigDecimal(22))+"\",");
						//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22))+"</td>"); 
						m_total = m_total.add(rs.getBigDecimal(23));
							
						//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23))+"</td>"); 
						out.print("\""+nf.format(rs.getBigDecimal(23))+"\",");
						
						//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"</td>"); // added by udara 10-04-2014
						out.print("\""+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"\",");
						
						//out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(23).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"</td>"); // added by udara 10-04-2014
						out.print("\""+nf.format(rs.getBigDecimal(23).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"\",");
						
						out.print("\n");
						
						//out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					// added by udara 10-04-2014
					BigDecimal m_total_to_date_new_wo_insurance = new BigDecimal(0.00); 
					m_total_to_date_new_wo_insurance = m_total_to_date.subtract(m_total_insurence_to_date).subtract(m_total_ins_invoice_to_date); 
					
					BigDecimal m_total_wo_insurance = new BigDecimal(0.00); 
					m_total_wo_insurance = m_total.subtract(m_total_insurence).subtract(m_total_ins_invoice); 
					// added by udara 10-04-2014
					
					
					out.print("Total,"); 
					out.print("Total,"); 
					
					out.print("\""+nf.format(m_total_penalty_to_date)+"\",");
					out.print("\""+nf.format(m_total_penalty)+"\",");
					out.print("\""+nf.format(m_total_invoice_to_date)+"\",");
					out.print("\""+nf.format(m_total_invoice)+"\",");
					out.print("\""+nf.format(m_total_ins_invoice_to_date)+"\",");
					out.print("\""+nf.format(m_total_ins_invoice)+"\",");
					out.print("\""+nf.format(m_total_insurence_to_date)+"\",");
					out.print("\""+nf.format(m_total_insurence)+"\",");
					out.print("\""+nf.format(m_total_arrears_to_date)+"\",");
					out.print("\""+nf.format(m_total_arrears)+"\",");
					out.print("\""+nf.format(m_total_closing_to_date)+"\",");
					out.print("\""+nf.format(m_total_closing)+"\",");
					out.print("\""+nf.format(m_total_rental_to_date)+"\",");
					out.print("\""+nf.format(m_total_rental)+"\",");
					out.print("\""+nf.format(m_total_excess_to_date)+"\",");
					out.print("\""+nf.format(m_total_excess)+"\",");
					out.print("\""+nf.format(m_total_to_date)+"\",");
					out.print("\""+nf.format(m_total)+"\",");
					out.print("\""+nf.format(m_total_to_date_new_wo_insurance)+"\",");
					out.print("\""+nf.format(m_total_wo_insurance)+"\",");
					out.print("\n");
					
					/*
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body ><b> &nbsp; </b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_penalty)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_invoice_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_invoice)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_ins_invoice)+"</b></td>"); 	
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice_to_date)+"</b></td>"); 
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:right; display: none; }' ><b>"+nf.format(m_total_ren_invoice)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_insurence)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }'><b>"+nf.format(m_total_arrears)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_closing_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }'  ><b>"+nf.format(m_total_closing)+"</b></td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental_to_date)+"</b></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_rental)+"</b></td>"); 	
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' ><b>"+nf.format(m_total_excess)+"</b></td>");    
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date)+"</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total)+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_to_date_new_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' ><b>"+nf.format(m_total_wo_insurance)+"</b></td>");  // added by udara on 10-04-2014
					
					out.println("</tr >");
					
					
					out.println("</tr>");		
					out.println("</table>");		
					out.println("</td> "); 
					out.println("</tr>");		
					out.println("</table>");	
					*/
				}
				
				
				/*
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				*/
				
			}
			
			
			
			
			
			
			
			//}
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
