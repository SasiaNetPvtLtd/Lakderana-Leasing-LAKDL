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


public class LAKDL_Report_check_1 extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
		Statement stmt=null,stmt2=null,stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs_drill_new=null;
		
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
				String m_perform_status=req.getParameter("perform_status");
				String m_location_id=req.getParameter("location_id");
				//String m_user_id=req.getParameter("user_id");
				
				String m_coll_officer=req.getParameter("coll_officer"); // added by udara 13-01-2015
				
				String m_active_status=req.getParameter("active_status"); // added by CJ 14-01-2015
				
				String m_one_year_below=req.getParameter("one_year_below");
				
				if(m_location_id == null || m_location_id.equals("")){
					m_location_id = null;
				}
				
				// added by udara 13-01-2015
				if(m_coll_officer == null || m_coll_officer.equals("")){
					m_coll_officer = null;
				}
				// end by udara 13-01-2015
				
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_AGE_ARREARS(:1,:2);END;");
					
					// commented by udara 23-10-2013
					/*
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_AGE_ARREARS_2(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_username);
					callstmt1.setString(3,m_perform_status);
					callstmt1.setString(4,m_location_id);
					
					callstmt1.execute();
					out.print("OK"); 
					*/
					
					// commented by udara 13-01-2015
					/*
					// added by udara on 23-10-2013
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ARREARS_RPT_N2(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_username);
					callstmt1.setString(3,m_location_id);
					callstmt1.setString(4,m_perform_status);
					
					callstmt1.execute();
					out.print("OK"); 
					*/
					
					// added by udara 13-01-2015
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ARREARS_RPT_N2(:1,:2,:3,:4,:5,:6);END;"); // commented by udara 26-05-2017
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_ANALYSIS_OF_ARREARS(:1,:2,:3,:4,:5,:6,:7);END;"); // added by udara 26-05-2017
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_username);
					callstmt1.setString(3,m_location_id);
					callstmt1.setString(4,m_perform_status);
					callstmt1.setString(5,m_coll_officer);
					callstmt1.setString(6,m_active_status);
					callstmt1.setString(7,m_one_year_below); 
					callstmt1.execute();
					out.print("OK"); 
					// end by udara 13-01-2015
					
					
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				
				stmt2 = conn.createStatement();
				/*
				rs2= stmt2.executeQuery(" SELECT B.LOCATION_CODE FROM "+m_schema_name+".CO_CO_MAS_USER A ,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B "+
					
					" WHERE A.EMP_ID=B.EMP_CODE AND A.USER_ID='"+m_username+"' ");
				
				String mm_location = "HO";
				if(rs2.next()){
					mm_location = rs2.getString(1);
					
				}
				*/
				
				
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Analysis of Arrears</TITLE>"); 
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
				
				out.println("   m_perform_status = document.Form1.TXT_PERFORM_STATUS.value; ");
				out.println("   m_coll_officer = document.Form1.TXT_USER.value; "); // added by udara 13-01-2014
				
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=run_report&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&perform_status=\"+m_perform_status;"); // commented by udara 13-01-2015
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=run_report&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&perform_status=\"+m_perform_status+\"&coll_officer\"+m_coll_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 13-01-2015
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=run_report&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&perform_status=\"+m_perform_status+\"&coll_officer=\"+m_coll_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&one_year_below=\"+document.Form1.TXT_ONE_YEAR_BELOW.value;"); 
				
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				//out.println("			print_report2();");  // commented by udara 25-03-2014
				out.println("			alert('Analysis of arrears report is generated. Use View Report button to get the view.');"); // out.println("			alert('The report is generated. Use View Report button to get the view.');"); // added by udara 25-03-2014
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
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-12(#16240)
				
				out.println("		m_perform_status=document.Form1.TXT_PERFORM_STATUS.value;"); // added by udara 17-08-2015
				
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=print_report_new_1&location=\"+m_location+\"&date=\"+m_date+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");  // added by udara on 09-05-2013
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=print_report_new_1&location=\"+m_location+\"&date=\"+m_date+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;"); 
				out.println("		window.open(m_url);");
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
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Analysis of Arrears - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Analysis of Arrears - \"+document.Form1.hid_status.value;"); 
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Analysis of Arrears </td>"); 
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
				
				
				
				// end by udara on 09-05-2013
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				/*
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>&nbsp;</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='hidden' name='TXT_LOCATION_CODE' maxlength='10' size='10' value='"+mm_location+"' style=\"{width:150px}\" ></td>"); 
				//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				*/
				// added by udara on 09-10-2013
				out.println("<tr>"); 
				out.println("<td width='20%' > Perform Status </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_PERFORM_STATUS'>"); 
				out.println("<option value=''    > All </option>");
				out.println("<option value='PERFORM'  > Perform </option>");//Added by Dineth on 2008-12-16
				out.println("<option value='NPERFORM' > Non Perform </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				// end by udara on 09-10-2013
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>");
			
				// added by udara 21-11-2014
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
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
				
				stmt3 = conn.createStatement ();
				
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
				
				
				// end by udara 21-11-2014
				
				
				// added by udara 05-04-2018
				out.println("<tr>"); 
				out.println("<td width='20%' > One Year and Below </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ONE_YEAR_BELOW'>"); 
				out.println("<option value='ALL'   > All </option>");
				out.println("<option value='BELOW' > Below </option>");
				out.println("<option value='ABOVE' > Above </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				// end by udara 05-04-2018
				
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
			
			//ADDED BY KANISHKA DILSHAN ON 15-05-2013
			
			else if(m_chksql.equals("print_report_new_1_drill")){		//added by kanishka dilshan on 15-05-2013
				
				String m_branch="";
				String m_val="";
				
				if(req.getParameter("branch")!=null ){
					m_branch=req.getParameter("branch").trim();
				}
				if(req.getParameter("val")!=null ){
					m_val=req.getParameter("val").trim();
				}
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Analysis of Arrears</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here	
				// added by udara on 26-07-2013
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				//out.println("    window.open(m_url); ");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				/*rs_drill_new = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'YYYY') FROM DUAL");
				
				if(rs_drill_new.next()){
					m_cur_date = rs_drill_new.getString(1);
					m_date_format = rs_drill_new.getString(2);
					m_year_format = rs_drill_new.getString(3);
				}*/
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Analysis of Arrears</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_data="";
				boolean more;
				
				if (m_val.equals("9")) {
					Sql_data=" SELECT A.FINANCE_NO, "+
						" SUM(NVL(A.ARREARS,0)) "+
						" FROM "+m_schema_name+".AF_TBD_RPT_ARR_AGE A WHERE BRANCH_CODE ='"+m_branch+"' "+
						" AND MATURE_STATUS = 'Y' AND ARREARS > 0 "+ // commented by udara on 22-08-2013
						//" AND ARREARS > 0 "+ // added by udara on 22-08-2013
						" AND ENT_USER='"+m_username+"' "+ // added by udara on 12-07-2013
						" GROUP BY FINANCE_NO";
				}
				else if (m_val.equals("999") ) {
					Sql_data=" SELECT A.FINANCE_NO, "+
						" SUM(NVL(A.ARREARS,0)) "+
						//" FROM "+m_schema_name+".AF_TBD_RPT_ARR_AGE A WHERE BRANCH_CODE ='"+m_branch+"' AND AGE > 0"+ // commented by udara on 22-08-2013
						" FROM "+m_schema_name+".AF_TBD_RPT_ARR_AGE A WHERE BRANCH_CODE ='"+m_branch+"' "+
						" AND ARREARS > 0 "+
						" AND ENT_USER='"+m_username+"' "+ // added by udara on 12-07-2013
						" GROUP BY FINANCE_NO";
				}
				
				else {
					
					Sql_data=" SELECT A.FINANCE_NO, "+
						" SUM(NVL(A.ARREARS,0)) "+
						" FROM "+m_schema_name+".AF_TBD_RPT_ARR_AGE A WHERE BRANCH_CODE ='"+m_branch+"' AND AGE='"+Integer.parseInt(m_val)+"' "+
						" AND MATURE_STATUS = 'N' AND ARREARS > 0 "+
						" AND ENT_USER='"+m_username+"' "+ // added by udara on 12-07-2013
						" GROUP BY FINANCE_NO";
				}
				
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
					out.println("<td class='factoring-letter-body' STYLE='text-align:center; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  >"+rs.getString(1)+"</td>"); 
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
			//ADDED BY KANISHAK DILSHAN ON 15-05-2013
			else if(m_chksql.equals("print_report_new_1")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_year_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_user_id ="";
				String mm_location="";
				
				
				String m_region="";

				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				// added by udara 23-03-2015
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				// end by udara 23-03-2015
				
				// added by udara 17-08-2015
				//String m_perform_status = "";
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}
				// end by udara 17-08-2015
				
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				stmt = conn.createStatement ();
				
				
				
				
				
				
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
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
				
				
				out.println("function Arrears_drill(val1,val2){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"Report_check_1?chksql=print_report_new_1_drill&branch=\"+val1+\"&val=\"+val2;");
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				/*rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_year_format = rs1.getString(3);
				}*/
				
				
				
				
				stmt2 = conn.createStatement ();
				
				rs2= stmt2.executeQuery(" SELECT B.LOCATION_CODE FROM "+m_schema_name+".CO_CO_MAS_USER A ,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B "+
					
					" WHERE A.EMP_ID=B.EMP_CODE AND A.USER_ID='"+m_username+"' ");
				
				mm_location = "HO";
				if(rs2.next()){
					mm_location = rs2.getString(1);
					
				}
				
				
				// added by udara 26-05-2017
				String m_report_run_date = "";
				
				rs2 = stmt2.executeQuery(" "+
					" SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS AM') "+
					" FROM "+m_schema_name+".ARREARS_REPORT_RUN_LOG "+
					" WHERE POINT = 'FINISH' ");
				
				if(rs2.next()){
					m_report_run_date = rs2.getString(1);
				}
				// added by udara 26-05-2017
				
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Analysis of Arrears</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				out.println("<table >");
				
				// added by udara 31-08-2015
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >As At Date :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_date+"</td>");
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				// end by udara 31-08-2015
				
				
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
				
				// added by udara 26-05-2017
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Last Report Run Time </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_report_run_date+"</td>");
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				// end by udara 26-05-2017
				
				out.println("</table >");
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
				if(!(m_region.equals("NOT_SELECT"))){ 
					//out.println("ssssss");
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
				
				
				
				String Sql_data="";
				
				//if(!(m_region.equals("NOT_SELECT"))){    // Added By: Samith Dilshan on 2015-06-12 for Region Code	
					if (mm_location.equals("HO")) {
						
						Sql_data=" SELECT "+
							" A.BRANCH_DESC, A.COL1, A.COL2, A.COL3, A.COL4, "+
							" A.COL5, A.COL6, A.COL7, A.COL8, A.COL9, A.COL10, A.COL11, "+
							" A.COL12, A.COL13, A.COL14, A.COL15, A.COL16, A.SCOL1, A.SCOL2, "+
							" A.SCOL3, A.SCOL4, A.SCOL5, A.SCOL6,A.BRANCH_CODE "+
							" ,A.COL0, A.COL00 "+ // 25 , 26 // added by udara on 22-08-2013 
							" FROM "+m_schema_name+".AF_TBD_RPT_ARR_AGE_SUMMERY A WHERE A.BRANCH_CODE<>'HO' AND ENT_USER='"+m_username+"' ";
						
						if(!(m_region.equals("NOT_SELECT"))){
							Sql_data=Sql_data + " AND  A.REGIONS_CODE = '"+m_region+"' ";
						}
						
						Sql_data = Sql_data + " ORDER BY  A.SCOL5 DESC "; // added by udara 13-11-2015
						
					} else {
						
						Sql_data=" SELECT "+
							" A.BRANCH_DESC, A.COL1, A.COL2, A.COL3, A.COL4, "+
							" A.COL5, A.COL6, A.COL7, A.COL8, A.COL9, A.COL10, A.COL11, "+
							" A.COL12, A.COL13, A.COL14, A.COL15, A.COL16, A.SCOL1, A.SCOL2, "+
							" A.SCOL3, A.SCOL4, A.SCOL5, A.SCOL6,A.BRANCH_CODE "+
							" ,A.COL0, A.COL00 "+ // 25 , 26 // added by udara on 22-08-2013 
							" FROM "+m_schema_name+".AF_TBD_RPT_ARR_AGE_SUMMERY A WHERE A.BRANCH_CODE<>'HO' AND ENT_USER='"+m_username+"' AND BRANCH_CODE='"+mm_location+"' ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){
							Sql_data = Sql_data + " AND  A.REGIONS_CODE = '"+m_region+"' " ;
						}
						
						Sql_data = Sql_data + " ORDER BY  A.SCOL5 DESC "; // added by udara 13-11-2015
						
					}
					
				//}
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				boolean more;
				more=rs.next();
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"1500px\" border=\"0\" class=\"table\">");
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
				out.println("<table id=mytable align=\"left\" width=\"1500px\" border=\"1\" class=\"table\"  cellspacing=0 > "); 
				//=================HEADER ================================
				out.println("<tr border=\"2\">");
				out.println("<td width=\"100px\"  align='center' ROWSPAN='2' ><b>BRANCH</b></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>BEFORE ONE MONTH</B></td>"); // added by udara on 22-08-2013
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>ONE MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>TWO MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>THREE MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>FOUR MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>FIVE MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>SIX MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>OVER SIX MONTHS</B></td>"); 
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>MATURE CASES</B></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='silver'  ><b>Total No of Cases in Arrears</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='#EAB35A' ><b>Total Branch Arrears</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  ><b>Total No. of Branch running Cases</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='#DFC6FF'  ><b>% of Cases in Arrears vs. Branch Total cases</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  ><b>Branch Gross Rental</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='#DFC6FF'  ><b>% of Arrears vs. Gross Rental</b></td>"); 
				out.println("</tr >");
				out.println("<tr >");
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); // added by udara on 22-08-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); // added by udara on 22-08-2013
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("</tr >");
				
				//======================END HEADER===========================
				
				//==============DATA===============================
				int num_row=0;
				//	double tot_col_1 = 0.00;
				int tot_col_2 = 0;
				double tot_col_3 = 0.00;
				int tot_col_4 = 0;
				double tot_col_5 = 0.00;
				int tot_col_6 = 0;
				double tot_col_7 = 0.00;
				int tot_col_8 = 0;
				double tot_col_9 = 0.00;
				int tot_col_10 = 0;
				double tot_col_11 = 0.00;
				int tot_col_12 = 0;
				double tot_col_13 = 0.00;
				int tot_col_14 = 0;
				double tot_col_15 = 0.00;
				int tot_col_16 = 0;
				double tot_col_17 = 0.00;
				int tot_col_18 = 0;
				double tot_col_19 = 0.00;
				int tot_col_20 = 0;
				double tot_col_21 = 0.00;
				double tot_col_22 = 0.00;
				double tot_col_23 = 0.00;
				
				
				int tot_col_0 = 0; // added by udara on 22-08-2013
				double tot_col_00 = 0; // added by udara on 22-08-2013
				
				String m_td_color="";
				
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					
					out.println("<tr >");
					out.println("<td class='factoring-letter-body' onclick=\"\" STYLE='text-align:left;' bgcolor='"+m_td_color+"' ><b>"+rs.getString(1)+"</b></td>"); 
					
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',0);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(25)+"</td>"); // added by udara on 22-08-2013
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',0);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(26))+"</td>");  // end by udara on 22-08-2013
					
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',1);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(2)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',1);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(3))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',2);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(4)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',2);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(5))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',3);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(6)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',3);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(7))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',4);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(8)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',4);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(9))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',5);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(10)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',5);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(11))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',6);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(12)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',6);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(13))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',99);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(14)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',99);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(15))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',9);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(16)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',9);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(17))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',999);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(18)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"Arrears_drill('"+rs.getString(24)+"',999);\" STYLE='cursor:hand; text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(19))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"\" STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+rs.getInt(20)+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"\" STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(21))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"\" STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(22))+"</td>"); 
					out.println("<td class='factoring-letter-body' onclick=\"\" STYLE='text-align:center;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(23))+"</td>");
					out.println("</tr >");
					
					
					tot_col_2 += rs.getInt(2);
					tot_col_3 += rs.getDouble(3);
					tot_col_4 += rs.getInt(4);
					tot_col_5 += rs.getDouble(5);
					tot_col_6 += rs.getInt(6);
					tot_col_7 += rs.getDouble(7);
					tot_col_8 += rs.getInt(8);
					tot_col_9 += rs.getDouble(9);
					tot_col_10 += rs.getInt(10);
					tot_col_11 += rs.getDouble(11);
					tot_col_12 += rs.getInt(12);
					tot_col_13 += rs.getDouble(13);
					tot_col_14 += rs.getInt(14);
					tot_col_15 += rs.getDouble(15);
					tot_col_16 += rs.getInt(16);
					tot_col_17 += rs.getDouble(17);
					tot_col_18 += rs.getInt(18);
					tot_col_19 += rs.getDouble(19);
					tot_col_20 += rs.getInt(20);
					tot_col_21 += rs.getDouble(21);
					tot_col_22 += rs.getDouble(22);
					tot_col_23 += rs.getDouble(23);
					
					tot_col_0 += rs.getInt(25); // added by udara on 22-08-2013
					tot_col_00 += rs.getInt(26); // added by udara on 22-08-2013
					
					num_row++;
					
					more=rs.next();
					
				}
				
				// added by udara 02-01-2014
				double case_in_arr_vs_branch_perc = 0;
				double arr_vs_gross_rental_perc   = 0;
				
				if(tot_col_20>0){
					case_in_arr_vs_branch_perc = ((double)tot_col_18/(double)tot_col_20)* 100 ; // case_in_arr_vs_branch_perc = (tot_col_18/tot_col_20)* 100 ;
				}
				else{
					case_in_arr_vs_branch_perc = 0;
				}
				
				if(tot_col_22>0){
					arr_vs_gross_rental_perc = (tot_col_19/tot_col_22)* 100 ;
				}
				else{
					arr_vs_gross_rental_perc = 0;
				}
				// added by udara 02-01-2014
				
				out.println("<tr border=\"2\">");
				out.println("<td class='factoring-letter-body' rowspan='3' STYLE='text-align:left;' ><b>Corporate data</b></td>"); 
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+tot_col_0+"</b></td>"); // added by udara on 22-08-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_00)+"</b></td>");  // added by udara on 22-08-2013
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+tot_col_2+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_3)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_4+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_5)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_6+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_7)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_8+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_9)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_10+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_11)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_12+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_13)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_14+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_15)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_16+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+nf.format(tot_col_17)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_18+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+nf.format(tot_col_19)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+tot_col_20+"</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+nf.format(tot_col_21)+"</b></td>"); // commented by udara 02-01-2014
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+ nf.format(case_in_arr_vs_branch_perc) +"</b></td>");  // out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+nf.format(case_in_arr_vs_branch_perc)+"</b></td>");  // added by udara 02-01-2014 // case_in_arr_vs_branch_perc // tot_col_18/tot_col_20
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen'  ><b>"+nf.format(tot_col_22)+"</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+nf.format(tot_col_23)+"</b></td>"); // commented by udara 02-01-2014
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='lightgreen' ><b>"+nf.format(arr_vs_gross_rental_perc)+"</b></td>"); // added by udara 02-01-2014
				out.println("</tr >");
				out.println("<tr border=\"2\">");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>BEFORE ONE MONTH</B></td>"); // added by udara on 22-08-2013
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>ONE MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>TWO MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>THREE MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>FOUR MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>FIVE MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>SIX MONTH</B></td>");
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>OVER SIX MONTHS</B></td>"); 
				out.println("<td width='100px' class=div_input colspan=\"2\" align='center' bgcolor='#FFF257' ><B>MATURE CASES</B></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='silver' ><b>Total No of Cases in Arrears</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='#EAB35A' ><b>Total Branch Arrears</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  ><b>Total No. of Branch running Cases</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='#DFC6FF' ><b>% of Cases in Arrears vs. Branch Total cases</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  ><b>Branch Gross Rental</b></td>"); 
				out.println("<td width=\"100px\"  align='center'ROWSPAN='2'  bgcolor='#DFC6FF' ><b>% of Arrears vs. Gross Rental</b></td>"); 
				out.println("</tr>");
				
				out.println("<tr border=\"2\">");
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>");   // added by udara on 22-08-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>");  // added by udara on 22-08-2013
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='silver' ><b>No of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='#EAB35A' ><b>Arrears</b></td>"); 
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
			if(rs_drill_new!=null){try{rs_drill_new.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
			
			
			
			
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
