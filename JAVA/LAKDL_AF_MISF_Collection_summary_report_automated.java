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



public class LAKDL_AF_MISF_Collection_summary_report_automated extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		
		ServletOutputStream out = null;
		Connection conn = null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
		Statement stmt=null, stmt2=null, stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null, rs3=null;
		
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
			
			/*	if(m_chksql.equals("run_report")){ 
					
					String m_date=req.getParameter("date");
					String m_finance_no = req.getParameter("finance_no");
					String m_branch = req.getParameter("branch"); // added by udara on 09-10-2013
					String m_coll_off = req.getParameter("coll_off"); // added by udara on 09-10-2013
					String m_region = req.getParameter("region"); // Added By Samith Dilshan On 2015-06-04
					
					try{
						//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REPORT(:1,:2);END;"); // commented by udara on 09-10-2013
						
						callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3);END;"); //callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3,:4);END;");
						
						//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_SUM_BRANCH_REP_2(:1,:2,:3,:4);END;") // Modified By Samith Dilshan On 2015-06-04
						
						callstmt1.setString(1,m_date);
						callstmt1.setString(2,m_username);
						
						//callstmt1.setString(3,m_branch);  // added by udara on 09-10-2013
						callstmt1.setString(3,m_coll_off);  // added by udara on 09-10-2013
						
						//callstmt1.setString(4,m_region);  // Added By udara on Samith Dilshan On 2015-06-04
						
						callstmt1.execute();
						out.print("OK"); 
					}
					catch(Exception ex){
						ex.printStackTrace();
						out.println("ERROR"+ex.toString()); 
					}
					
				}
				*/
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report - Automated</TITLE>"); 
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
				
				// added by udara 27-01-2016
				out.println("function run_report_validation() {");
				out.println("     document.Form1.hid_chk_status.value='run_report_validation'; ");
				out.println("     m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_run_report_validation\";"); // m_prime_chk_LAKDL_AF_MAS_display_location
				//out.println("     window.open(m_url); ");
				out.println("     load_interface(m_url,'XML');");
				out.println("}");
				// end by udara 27-01-2016
				
				
				out.println("function run_report() {");
				
				out.println("   m_branch   = //; "); // out.println("   m_branch   = document.Form1.TXT_LOCATION_CODE.value; "); // added by udara on 09-10-2013
				out.println("   m_coll_off = document.Form1.TXT_USER.value; "); // added by udara on 09-10-2013
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-03 (#16240)
				
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=run_report&date=\"+m_date+\"&branch=\"+m_branch+\"&coll_off=\"+m_coll_off+\"&region=\"+m_region;"); 
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("var reportReady = false;");
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			reportReady = true;"); 
				//out.println("			print_report2();"); // commented by udara 22-12-2015
				out.println("			alert('Collection Summary Report - Automated is generated. Use View Report button to get the view.');"); 
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
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-08 (#16240)
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new&date=\"+m_date;");	 // commented by udara 07-04-2015
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new&date=\"+m_date+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");	 // added by udara 07-04-2015
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new&date=\"+m_date+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&officer=\"+document.Form1.TXT_USER.value;"); // added by udara 26-06-2017
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
				
				// added by udara 27-01-2016
				out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='run_report_validation' ){"); 
				out.println("			    if(data_vec[0]=='RUNNING'){");
				out.println("			       alert('Please wait, currently the report is run by the user - ' + data_vec[1]); ");
				out.println("			    }");
				out.println("			    else{");
				//out.println("			       alert('The report is ready to run'); ");
				out.println("                  run_report(); ");
				out.println("			    }");
				out.println("			}");
				// end by udara 27-01-2016
				
				out.println("}");
				
				
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Collection Summary Report - Automated - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Collection Summary Report - Automated - \"+document.Form1.hid_status.value;"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Summary Report - Automated </td>"); 
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
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report_validation()\" style=\"{width:110px; display:none;}\"></td>");  // run_report // mod by udara 27-01-2016
				out.println("</tr>");
				
				
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				// added by udara 07-04-2015
				out.println("<tr>"); 
				out.println("<td width='20%' > Active/Yard Vehicles </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ACTIVE_STATUS'>"); 
				out.println("<option value='A' > All </option>");
				out.println("<option value='Y' > Active </option>");
				out.println("<option value='N' > Yard Vehicles </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				// end by udara 07-04-2015
				
				
				/*
				// Added By: Samith dilshan  On : 2015-06-03
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				out.println("      <OPTION value='SAB' >Sabaragamuwa</OPTION>");
				out.println("      <OPTION value='WP'  >Western Province</OPTION>");
				out.println("      <OPTION value='CP'  >Central Province</OPTION>");
				out.println("      <OPTION value='CPSP'>Southern Province</OPTION>");
				
				
				out.println(" 	</select>");
				out.println("</td>"); 
				out.println("</tr>");
				*/
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
				out.println("<OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				stmt3 = conn.createStatement();
				
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
			
			
			
			/*else if(m_chksql.equals("drill_down_asset")){		
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
				
			}*/
			
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
				String m_region="";
				
				String  m_active_status = ""; // added by udara 07-04-2015
				String  m_active_status_string = ""; // added by udara 07-04-2015
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				
				// added by udara 26-06-2017
				String m_officer_string = "";
				
				if(!m_officer.equals("")){					
					m_officer_string = " AND A.COLLECTION_OFFICER = '"+m_officer+"'  ";					
				}
				// end by udara 26-06-2017
				
				// added by udara 07-04-2015
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				// end by udara 07-04-2015
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report - Automated</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				//out.println("function show_branch_level_drill_down(mm_location){ ");  // commented by udara 13-08-2015
				out.println("function show_branch_level_drill_down(mm_location,mm_region){ "); // added by udara 13-08-2015
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location;"); // commented by udara 07-04-2015
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location+'&active_status="+m_active_status+"';");  // added by udara 07-04-2015
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location+'&active_status="+m_active_status+"'+'&m_region='+mm_region;"); 
				
				out.println(" m_officer = '"+m_officer+"'; ");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new_drill_level_01&date="+m_date+"&location='+mm_location+'&active_status="+m_active_status+"'+'&m_region='+mm_region+'&officer='+m_officer;"); // added by udara 26-06-2017
				
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
				
				
				// added by udara 26-07-2016
				
				String last_run_date = "";
				
				rs1 = stmt.executeQuery(" "+
					" SELECT TO_CHAR(MAX(ENT_DATE),'DD-MM-YYYY HH:MI:SS') "+
					" FROM "+m_schema_name+".AF_RE_COL_SUM_BRANCH_RPT_DAILY "+
					"  ");
				
				if(rs1.next()){
					last_run_date = rs1.getString(1);
				}
				
				
				// end by udara 26-07-2016
				
				
				
				
				
				String Sql_data="";
				
				
				/*
				Sql_data = "  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
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
					"            SUM ( NVL(A.TOTAL,0) ), "+
					"            NVL (A.REGIONS_CODE, '-') REGIONS_CODE "+ // Added By: Samith Dilshan on 2015-06-08 for Region Code
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_REPORT A"+
					"    WHERE   A.ENT_USER = '"+m_username+"' ";
					
					
					if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
						Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
					}
					
					Sql_data = Sql_data + "    "+m_active_status_string+"   "+ // added by udara 07-04-2015
					" GROUP BY   A.LOCATION , REGIONS_CODE  ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') ";
					*/
				
				/*
				Sql_data = "  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
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
					"            SUM ( NVL(A.TOTAL,0) ), "+
					"            NVL (A.REGIONS_CODE, '-') REGIONS_CODE "+ 
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_RPT_HIS A"+
					"    WHERE   A.ENT_USER = '"+m_username+"' "+
					"    AND     A.REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY')    ";
					
					if(!(m_region.equals("NOT_SELECT"))){ 
						Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
					}
					
					Sql_data = Sql_data + "    "+m_active_status_string+"   ";
					
					Sql_data = Sql_data + " GROUP BY   A.LOCATION , REGIONS_CODE  ";
					
					Sql_data = Sql_data + "  UNION ALL  ";
					
					Sql_data = Sql_data + "  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
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
					"            SUM ( NVL(A.TOTAL,0) ), "+
					"            NVL (A.REGIONS_CODE, '-') REGIONS_CODE "+ 
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_RPT_DAILY A"+
					"    WHERE   A.ENT_USER = '"+m_username+"' "+
					"    AND     A.REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY')    ";
					
					if(!(m_region.equals("NOT_SELECT"))){ 
						Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
					}
					
					Sql_data = Sql_data + "    "+m_active_status_string+"   ";
					Sql_data = Sql_data + " GROUP BY   A.LOCATION , REGIONS_CODE  ";
					
				*/
				
				Sql_data = Sql_data + " "+
					" SELECT "+
					" LOCATION_DESC, "+
					" PENALTY_TO_DATE, "+
					" PENALTY, "+
					" INVOICE_TO_DATE, "+
					" INVOICE,  "+
					" INS_INVOICE_TO_DATE, "+
					" INS_INVOICE, "+
					" REN_INVOICE_TO_DATE, "+
					" REN_INVOICE, "+
					" INSURENCE_TO_DATE, "+
					" INSURENCE, "+
					" ARREARS_TO_DATE, "+
					" ARREARS, "+
					" CLOSING_TO_DATE, "+
					" CLOSING, "+
					" RENTAL_TO_DATE, "+
					" RENTAL, "+
					" EXCESS_TO_DATE, "+
					" EXCESS, "+
					" LOCATION, "+
					" TOTAL_TO_DATE, "+
					" TOTAL, "+
					" REGIONS_CODE, "+
					" DOWN_PAYMENT_TO_DATE, "+ // added by udara 05-05-2017
					" DOWN_PAYMENT "+ // added by udara 26-04-2017 (24)
					
					" FROM ( "+ 
					
					" SELECT "+ 
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') LOCATION_DESC, "+
					" SUM ( A.PENALTY_TO_DATE ) PENALTY_TO_DATE, "+
					" SUM ( A.PENALTY ) PENALTY, "+
					" SUM ( A.INVOICE_TO_DATE ) INVOICE_TO_DATE, "+
					" SUM ( A.INVOICE ) INVOICE, "+
					" SUM ( A.INS_INVOICE_TO_DATE ) INS_INVOICE_TO_DATE, "+
					" SUM ( A.INS_INVOICE ) INS_INVOICE, "+
					" SUM ( A.REN_INVOICE_TO_DATE ) REN_INVOICE_TO_DATE, "+
					" SUM ( A.REN_INVOICE ) REN_INVOICE, "+
					" SUM ( A.INSURENCE_TO_DATE ) INSURENCE_TO_DATE, "+
					" SUM ( A.INSURENCE ) INSURENCE, "+
					" SUM ( A.ARREARS_TO_DATE ) ARREARS_TO_DATE, "+
					" SUM ( A.ARREARS ) ARREARS, "+
					" SUM ( A.CLOSING_TO_DATE ) CLOSING_TO_DATE, "+
					" SUM ( A.CLOSING ) CLOSING, "+
					" SUM ( A.RENTAL_TO_DATE ) RENTAL_TO_DATE, "+
					" SUM ( A.RENTAL ) RENTAL, "+
					" SUM ( A.EXCESS_TO_DATE ) EXCESS_TO_DATE, "+
					" SUM ( A.EXCESS ) EXCESS, "+
					" A.LOCATION LOCATION, "+
					" SUM ( NVL(A.TOTAL_TO_DATE,0) ) TOTAL_TO_DATE, "+
					" SUM ( NVL(A.TOTAL,0) ) TOTAL, "+
					" NVL (A.REGIONS_CODE, '-') REGIONS_CODE, "+
					//" SUM(NVL(A.DOWN_PAYMENT,0)) DOWN_PAYMENT "+ // added by udara 26-04-2017
					" SUM(NVL(A.DOWN_PAYMENT_TO_DATE,0)) DOWN_PAYMENT_TO_DATE, "+ // added by udara 04-05-2017  (24)
					" SUM(NVL(A.DOWN_PAYMENT,0)) DOWN_PAYMENT "+ // added by udara 04-05-2017  (25)
					" FROM "+m_schema_name+".AF_RE_COL_SUM_BRANCH_RPT_HIS A "+
					/*" WHERE A.ENT_USER  = 'LAKDLALL' "+
					" AND A.REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "*/
					" WHERE A.REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') ";
				
				if(!(m_region.equals("NOT_SELECT"))){ 
					Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				Sql_data = Sql_data + "    "+m_active_status_string+"   ";
				
				Sql_data = Sql_data + "    "+m_officer_string+"   "; // added by udara 26-06-2017
				
				Sql_data = Sql_data + " GROUP BY A.LOCATION , REGIONS_CODE "+
					
					" UNION ALL "+
					
					" SELECT  "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') LOCATION_DESC, "+
					" SUM ( A.PENALTY_TO_DATE ) PENALTY_TO_DATE, "+
					" SUM ( A.PENALTY ) PENALTY, "+
					" SUM ( A.INVOICE_TO_DATE ) INVOICE_TO_DATE, "+
					" SUM ( A.INVOICE ) INVOICE, "+
					" SUM ( A.INS_INVOICE_TO_DATE ) INS_INVOICE_TO_DATE, "+
					" SUM ( A.INS_INVOICE ) INS_INVOICE, "+
					" SUM ( A.REN_INVOICE_TO_DATE ) REN_INVOICE_TO_DATE, "+
					" SUM ( A.REN_INVOICE ) REN_INVOICE, "+
					" SUM ( A.INSURENCE_TO_DATE ) INSURENCE_TO_DATE, "+
					" SUM ( A.INSURENCE ) INSURENCE, "+
					" SUM ( A.ARREARS_TO_DATE ) ARREARS_TO_DATE, "+
					" SUM ( A.ARREARS ) ARREARS, "+
					" SUM ( A.CLOSING_TO_DATE ) CLOSING_TO_DATE, "+
					" SUM ( A.CLOSING ) CLOSING, "+
					" SUM ( A.RENTAL_TO_DATE ) RENTAL_TO_DATE, "+
					" SUM ( A.RENTAL ) RENTAL, "+
					" SUM ( A.EXCESS_TO_DATE ) EXCESS_TO_DATE, "+
					" SUM ( A.EXCESS ) EXCESS, "+
					" A.LOCATION LOCATION, "+
					" SUM ( NVL(A.TOTAL_TO_DATE,0) ) TOTAL_TO_DATE, "+
					" SUM ( NVL(A.TOTAL,0) ) TOTAL, "+
					" NVL (A.REGIONS_CODE, '-') REGIONS_CODE,      "+
					//" SUM(NVL(A.DOWN_PAYMENT,0)) DOWN_PAYMENT "+ // added by udara 26-04-2017
					" SUM(NVL(A.DOWN_PAYMENT_TO_DATE,0)) DOWN_PAYMENT_TO_DATE, "+ // added by udara 04-05-2017  (24)
					" SUM(NVL(A.DOWN_PAYMENT,0)) DOWN_PAYMENT "+ // added by udara 04-05-2017  (25)
					" FROM "+m_schema_name+".AF_RE_COL_SUM_BRANCH_RPT_DAILY A "+
					/*" WHERE A.ENT_USER  = 'LAKDLALL' "+
					" AND A.REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "*/
					" WHERE A.REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') ";
				
				if(!(m_region.equals("NOT_SELECT"))){ 
					Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				Sql_data = Sql_data + "    "+m_active_status_string+"   ";
				
				Sql_data = Sql_data + "    "+m_officer_string+"   "; // added by udara 26-06-2017
				
				
				Sql_data = Sql_data + " GROUP BY A.LOCATION , REGIONS_CODE "+   
					" ) "+
					" ORDER BY LOCATION_DESC ";
				
				
				//out.print(Sql_data);
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report - Automated</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				out.println("<br>");
				
				// added by udara 26-07-2016
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: 9pt arial; text-align:laft;}'   ><b>Last updated Date/Time : - </b> "+last_run_date+" </td>"); 
				out.println("</tr >");
				out.println("</table >");
				// end by udara 26-07-2016
				
				out.println("<br>");
				
				if(!(m_region.equals("NOT_SELECT"))){ 
					
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
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>DOWN PAYMENT</b></td>"); // mod by udara 05-05-2017 // added by udara 26-04-2017
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total</b></td>"); 
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>Total w/o Insurance & Invoice Insurance</b></td>"); // Added by udara 09-04-2014
					
					// Added By: Samith Dilshan
					//out.println("<td class=factoring-letter-body ROWSPAN='2' STYLE='{text-align:center; }'><b>REGION</b></td>"); 
					
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
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b> Total to-date </b></td>"); // mod by udara 05-05-2017  // added by udara 26-04-2017 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 05-05-2017
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 09-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 09-04-2014
					
					// Added By: Samith Dilshan
					//out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
					
					out.println("</tr >");
					
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total, m_total_down_payment; // added m_total_down_payment by udara 26-04-2017
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date,m_total_down_payment_to_date; // added m_total_down_payment_to_date 05-05-2017
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = m_total_down_payment = new BigDecimal(0.00); // added m_total_down_payment by udara 26-04-2017
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = m_total_down_payment_to_date = new BigDecimal(0.00); // added m_total_down_payment_to_date 05-05-2017
					
					int j = 1;
					while(more){
						
						
						out.println("<tr  id=tr_id"+j+" onClick=\"\"   >");
						//out.println("<td class=factoring-letter-body  STYLE='{cursor:hand}' onClick=\"show_branch_level_drill_down('"+rs.getString("LOCATION")+"')\" ><b>"+rs.getString(1)+"</b></td>"); // commented by udara 13-08-2015
						out.println("<td class=factoring-letter-body  STYLE='{cursor:hand}' onClick=\"show_branch_level_drill_down('"+rs.getString("LOCATION")+"','"+m_region+"')\" ><b>"+rs.getString(1)+"</b></td>");  // added by udara 13-08-2015
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
						
						m_total_down_payment_to_date = m_total_down_payment_to_date.add(rs.getBigDecimal(24)); // added by udara 05-05-2017
						m_total_down_payment = m_total_down_payment.add(rs.getBigDecimal(25)); // mod by udara 05-05-2017 // added by udara 26-04-2017
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(24))+"</td>"); // added by udara 26-04-2017
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(25))+"</td>"); // added by udara 04-05-2017
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(21))+"</td>"); // total
						m_total = m_total.add(rs.getBigDecimal(22));
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22))+"</td>"); // total to date
						
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(21).subtract(rs.getBigDecimal(6)).subtract(rs.getBigDecimal(10)))+"</td>"); // added by udara 09-04-2014
						out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' >"+nf.format(rs.getBigDecimal(22).subtract(rs.getBigDecimal(7)).subtract(rs.getBigDecimal(11)))+"</td>"); // added by udara 09-04-2014
						
						// Added By: Samith Dilshan on 2015-06-08
						//out.println("<td class=factoring-letter-body  STYLE='{cursor:hand}' ><b>"+rs.getString(23)+"</b></td>");
						
						out.println("</tr >");
						j++;
						more=rs.next();
					}
					
					BigDecimal m_total_to_date_new = new BigDecimal(0.00); // added by udara obn 10-07-2013
					//m_total_to_date_new = m_total_to_date_new.add(m_total_penalty_to_date).add(m_total_invoice_to_date).add(m_total_ins_invoice_to_date).add(m_total_ren_invoice_to_date).add(m_total_insurence_to_date).add(m_total_arrears_to_date).add(m_total_closing_to_date).add(m_total_rental_to_date).add(m_total_excess_to_date); // commented by udara 04-01-2021
					m_total_to_date_new = m_total_to_date_new.add(m_total_penalty_to_date).add(m_total_invoice_to_date).add(m_total_ins_invoice_to_date).add(m_total_ren_invoice_to_date).add(m_total_insurence_to_date).add(m_total_arrears_to_date).add(m_total_closing_to_date).add(m_total_rental_to_date).add(m_total_excess_to_date).add(m_total_down_payment_to_date); // added by udara 04-01-2021
					
					// added by udara 09-04-2014
					BigDecimal m_total_to_date_new_wo_insurance = new BigDecimal(0.00); 
					m_total_to_date_new_wo_insurance = m_total_to_date_new.subtract(m_total_insurence_to_date).subtract(m_total_ins_invoice_to_date); 
					
					BigDecimal m_total_wo_insurance = new BigDecimal(0.00); 
					m_total_wo_insurance = m_total.subtract(m_total_insurence).subtract(m_total_ins_invoice); 
					// added by udara 09-04-2014
					
					out.println("<tr  >");
					out.println("<td class=factoring-letter-body STYLE='{cursor:hand}' onClick=\"show_branch_level_drill_down('ALL','"+m_region+"')\" ><b>Total</b></td>"); 
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
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' > <b>"+nf.format(m_total_down_payment_to_date)+"</b> </td>"); // added by udara 05-05-2017
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' > <b>"+nf.format(m_total_down_payment)+"</b> </td>");  // added by udara 26-04-2017
					
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
				
				String m_active_status = ""; // added by udara 07-04-2015
				String m_active_status_string = ""; // added by udara 07-04-2015
				
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				
				
				// added by udara 07-04-2015
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				// end by udara 07-04-2015
				
				// added by udara 13-08-2015
				String m_region = "";
				String m_region_string = "";
				if(req.getParameter("m_region")!=null ){
					m_region = req.getParameter("m_region").trim();
				}
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					m_region_string = "    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				// end by udara 13-08-2015
				
				
				// added by udara 26-06-2017
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				String m_officer_string = "";
				
				if(!m_officer.equals("")){                
					m_officer_string = "    AND   A.COLLECTION_OFFICER = '"+m_officer+"' ";    
				}
				// end by udara 26-06-2017
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				//[COMMENTED BY MILINDA AND ADDED BELOW ON 23-10-2020 JB20102020-12184]
				/*if(req.getParameter("location")!=null ){
					mm_location=req.getParameter("location").trim();
				}*/
				String mm_location_filter="";
				if(req.getParameter("location")!=null){
					if(!req.getParameter("location").equals( "ALL") ){
						//out.println("1");
						mm_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION = '"+mm_location+"' ";
					}else{
						//out.println("2");
						mm_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION LIKE  '%' ";
					}
				}		
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Summary Report - Automated</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_summary_report_automated?chksql=print_report_new&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				
				
				String Sql_data="";
				
				/*
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
					"     "+m_active_status_string+"       "+ // added by udara 07-04-2015
					"    "+m_region_string+"  "+ // added by udara 13-08-2015
					"    GROUP BY  FINANCE_NO,CLIENT_CODE, A.LOCATION ORDER BY  NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-') "+
					""+
					"";
				*/
				//" ORDER BY   "+m_sort_column+"  "+m_order_by_type+" ";	
				
				
				Sql_data = Sql_data + "  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
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
					"            SUM ( NVL(A.TOTAL,0) ), "+
					//"            SUM(NVL(A.DOWN_PAYMENT,0)) "+ // added by udara 26-04-2017 (24)
					"            SUM(NVL(A.DOWN_PAYMENT_TO_DATE,0)), "+ // added by udara 05-05-2017  (24)
					"            SUM(NVL(A.DOWN_PAYMENT,0)) "+ // added by udara 05-05-2017 (25)
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_RPT_HIS A "+
					//"    WHERE   A.ENT_USER = '"+m_username+"'  "+
					"    WHERE  REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+  
					//"    AND     A.LOCATION = '"+mm_location+"' "+ //[COMMENTED BY MILINDA AND ADDED BELOW ON 23-10-2020 JB20102020-12184]
					"     "+mm_location_filter+" "+
					"     "+m_active_status_string+"       "+ // added by udara 07-04-2015
					"    "+m_region_string+"  "+ // added by udara 13-08-2015
					"    "+m_officer_string+" "+ // added by udara 26-06-2017
					"    GROUP BY  FINANCE_NO,CLIENT_CODE, A.LOCATION ";
				
				Sql_data = Sql_data + " UNION ALL ";
				
				Sql_data = Sql_data + "  SELECT    NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION),'-'),"+
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
					"            SUM ( NVL(A.TOTAL,0) ), "+
					//"            SUM(NVL(A.DOWN_PAYMENT,0)) "+ // added by udara 26-04-2017 (24)
					"            SUM(NVL(A.DOWN_PAYMENT_TO_DATE,0)), "+ // added by udara 05-05-2017  (24)
					"            SUM(NVL(A.DOWN_PAYMENT,0)) "+ // added by udara 05-05-2017 (25)
					"     FROM   "+m_schema_name+".AF_RE_COL_SUM_BRANCH_RPT_DAILY A "+
					//"    WHERE   A.ENT_USER = '"+m_username+"'  "+
					"    WHERE  REPORT_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY') "+  
					//"    AND     A.LOCATION = '"+mm_location+"' "+//[COMMENTED BY MILINDA AND ADDED BELOW ON 23-10-2020 JB20102020-12184]
					"     "+mm_location_filter+" "+
					"     "+m_active_status_string+"       "+ // added by udara 07-04-2015
					"    "+m_region_string+"  "+ // added by udara 13-08-2015
					"    "+m_officer_string+" "+ // added by udara 26-06-2017
					"    GROUP BY  FINANCE_NO,CLIENT_CODE, A.LOCATION  "+
					
					" ";
				
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Collection Summary Report - Automated Branch - "+mm_location+"  </u></td>"); 
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
					
					out.println("<td class=factoring-letter-body COLSPAN='2' STYLE='{ text-align:center; }' ><b>DOWN PAYMENT</b></td>"); // mod by udara 05-05-2017 // added by udara 26-04-2017
					
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
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' > <b>Total to-date</b> </td>"); // mod by udara 05-05-2017 // added by udara 26-04-2017 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:center; }' ><b>"+m_date+"</b></td>");  // added by udara 05-05-2017
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>Total to-date</b></td>"); // added by udara 10-04-2014
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:center; }' ><b>"+m_date+"</b></td>"); // added by udara 10-04-2014
					
					out.println("</tr >");
					
					
					
					
					BigDecimal m_total_penalty,m_total_invoice,m_total_ins_invoice,m_total_ren_invoice,m_total_insurence,m_total_arrears,m_total_closing,m_total_rental,m_total_excess,m_total,m_total_down_payment; // added m_total_down_payment by udara 26-04-2017
					BigDecimal m_total_penalty_to_date,m_total_invoice_to_date,m_total_ins_invoice_to_date,m_total_ren_invoice_to_date,m_total_insurence_to_date,m_total_arrears_to_date,m_total_closing_to_date,m_total_rental_to_date,m_total_excess_to_date,m_total_to_date,m_total_down_payment_to_date; // added m_total_down_payment_to_date 05-05-2017
					
					m_total_penalty = m_total_invoice = m_total_ins_invoice = m_total_ren_invoice = m_total_insurence = m_total_arrears = m_total_closing = m_total_rental = m_total_excess = m_total = new BigDecimal(0.00);
					m_total_penalty_to_date = m_total_invoice_to_date = m_total_ins_invoice_to_date = m_total_ren_invoice_to_date = m_total_insurence_to_date = m_total_arrears_to_date = m_total_closing_to_date = m_total_rental_to_date = m_total_excess_to_date = m_total_to_date = m_total_down_payment = m_total_down_payment_to_date = new BigDecimal(0.00); // added m_total_down_payment_to_date 05-05-2017 // added m_total_down_payment by udara 26-04-2017 
					
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
						
						
						m_total_down_payment_to_date = m_total_down_payment_to_date.add(rs.getBigDecimal(24));  // added by udara 05-05-2017
						m_total_down_payment = m_total_down_payment.add(rs.getBigDecimal(25)); // mod by udara 05-05-2017 // added by udara 26-04-2017
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(24))+"</td>"); // added by udara 26-04-2017
						out.println("<td class=factoring-letter-body  STYLE='{text-align:right; }' >"+nf.format(rs.getBigDecimal(25))+"</td>"); // added by udara 04-09-2017
						
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
					
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' > <b>"+nf.format(m_total_down_payment_to_date)+"</b> </td>"); // added by udara 05-05-2017
					out.println("<td class=factoring-letter-body  STYLE='{ text-align:right; }' > <b>"+nf.format(m_total_down_payment)+"</b> </td>");  // added by udara 26-04-2017
					
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
