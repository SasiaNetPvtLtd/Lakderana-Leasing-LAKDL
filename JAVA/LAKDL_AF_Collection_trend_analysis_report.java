// DEVELOP BY : UDARA ON 02-12-2013

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_Collection_trend_analysis_report extends javax.servlet.http.HttpServlet { 

	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized 
		
		ServletOutputStream out = null;
		Connection conn= null;
		Statement stmt= null,stmt1= null,stmt2= null,stmt3= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null,rs2= null,rs3= null;
		String m_chksql= null;
		
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			//String m_username=m_sn_methods.username;
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2); 
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);   
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			stmt3=conn.createStatement();
			CallableStatement callstmt1 =null;
			String m_username 	= m_sn_methods.username;
			//new report
			if(m_chksql.equals("run_report")){ 
				
				
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_location_id=req.getParameter("location_code");
				String m_user_id=req.getParameter("user");

				
				try{

					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".SAVE_COLLECTION_TREND_ANALYSIS(:1,:2,:3,:4);END;"); 
					callstmt1.setString(1,m_from_date);
					callstmt1.setString(2,m_to_date);
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
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Trend Analysis Report </TITLE>"); 
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
				
				out.println("var m_sav_msg='';");
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
				out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
				out.println("		}");
				out.println("}");
				
				//To validate from date & to date
				out.println("function validate_date(){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
				out.println("    if(!checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
				out.println("     return false;"); 
				out.println("     }");
				out.println("    else {");
				out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
				out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
				out.println("      	  return true;"); 
				out.println("  	  	 }");
				out.println("        else "); 
				out.println("         return false; "); 
				out.println("     }");
				out.println("			else {");
				out.println("   		alert('To Date cannot be null ')");
				out.println("   		return false;"); 
				out.println("     }");
				out.println("    }");
				out.println("  }");
				out.println(" else { ");
				out.println("   alert('From Date cannot be null ')");
				out.println("   return false;"); 
				out.println("  }");
				out.println(" }");
				
				out.println("function makeRequest_detail() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; ");
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_new_receipt_report?chksql=load_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id;"); // added by udara on 27-02-2013
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				out.println("function makeRequest_detail_active() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; "); // added by udara on 27-02-2013
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_active_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_new_receipt_report?chksql=load_active_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id;"); // added by udara on 27-02-2013
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				
				out.println("function validate_data(){"); 
				out.println("	return true;"); 
				out.println("}"); 			
				
				out.println("function before_submit(){ "); 
				out.println("} "); 
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_trend_analysis_report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_trend_analysis_report=main_page';"); 
				out.println("}"); 
				
				out.println("function save_window(){	"); 
				out.println("	before_submit();"); 
				out.println("}"); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_STATEMENT_REPORT\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("	help_box.innerHTML=\" Collection - Collection Trend Analysis Report - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Collection - Collection Trend Analysis Report \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function load_screen_status(m_val){"); 
				out.println("		if(m_val==\"NEW\"){"); 
				out.println("			new_window();"); 
				out.println("		}"); 
				out.println("		else if(m_val==\"HELP\"){"); 
				out.println("			load_help_msg();"); 
				out.println("		}"); 
				out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("		if(m_val==\"NEW\"){");
				out.println("			document.Form1.hid_status.value=\"New\";"); 
				out.println("		}");
				out.println("		else if(m_val==\"EDIT\"){");  
				out.println("			document.Form1.hid_status.value=\"Edit\";");  
				out.println("		}");
				out.println("		else if(m_val==\"DACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("		}");
				out.println("		else if(m_val==\"RACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("		}");
				out.println("		else{");  
				out.println("			document.Form1.hid_status.value=\"\";");  
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function get_display_msg(){"); 
				out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("		m_sav_msg=\"Save\";"); 
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
				out.println("		m_sav_msg=\"Modify\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
				out.println("		m_sav_msg=\"Deactivate\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
				out.println("		m_sav_msg=\"Reactivate\";");  
				out.println("	}");
				out.println("	else{");  
				out.println("		m_sav_msg=\"\";");  
				out.println("	}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("	this.valout   = new Array(10);"); 
				out.println("}"); 
				
				out.println("function help_update() {"); 
				out.println(" document.Form1.hid_help_type.value=\"1\";"); 
				out.println(" m_sql = \"m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql\";"); 
				out.println(" m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
				out.println(" HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function help_update_facility() {"); 
				out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
				out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql\";"); 
				out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
				out.println(" 	HelpBox('1','10','0');"); 
				out.println("}");
				
				out.println("function help_update_debtor() {"); 
				out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
				out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_REPORT_sql\";"); 
				out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
				out.println(" 	HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_1() {"); 
				out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function help_update_value_assign_2() {"); 
				out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function help_update_value_assign_3() {"); 
				out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				//out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 
				
				//out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
				//out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
				//out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
				out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("		if(oBj.valout[1] !=\"Close\"){"); 
				out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("				if(oBj.valout[1]!=\"Next\"){"); 
				out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("						help_value_assign_client_id();"); 
				out.println("					}"); 
				out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("						help_update_value_assign_2();"); 
				out.println("					}"); 
				out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("						help_update_value_assign_3();"); 
				out.println("					}"); 
				out.println("					if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("						help_value_assign_branch_id();"); 
				out.println("					}"); 
				out.println("				}"); 
				out.println("				else{"); 
				out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("					return false;"); 
				out.println("				} "); 
				out.println("			}"); 
				out.println("			else{	"); 
				out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("			}	"); 
				out.println("	 	}"); 
				out.println("	}"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function help_button_user_id() {"); 
				out.println(" document.Form1.hid_help_type.value='1';");
				out.println(" var sql = '';");
				out.println(" if(document.Form1.LOCATION_CODE.value == 'ALL'){");
				out.println("  sql = 'm_help_TXT_USERS_sql';");
				out.println(" }");
				out.println(" else{");
				out.println("  sql = 'm_help_TXT_USERS_with_loc_sql'; ");
				out.println(" }");
				out.println(" m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+document.Form1.LOCATION_CODE.value+\"@\";"); 
				out.println(" 	m_sql = sql;"); 
				
				out.println(" HelpBox('1','10','0');");
				out.println("}"); 
				
				out.println("function help_value_assign_client_id() {"); 
				out.println(" document.Form1.TXT_USER_ID.value=oBj.valout[2];"); 
				
				//out.println("alert(oBj.valout[8]);");
				out.println("}");
				
				out.println("function clear_user() {"); 
				out.println(" document.Form1.TXT_USER_ID.value='';"); 
				
				out.println("}");
				
				// added by udara on 27-02-2013
				
				out.println("function help_button_branch_id() {"); 
				out.println(" document.Form1.hid_help_type.value='4';");
				out.println(" m_criteria = document.Form1.TXT_BRANCH_ID.value+\"@\"+document.Form1.TXT_USER_ID.value+\"@\";");
				out.println(" m_sql = 'm_help_TXT_BRANCH_sql';"); 
				out.println(" HelpBox('1','10','0');");
				out.println("}"); 
				
				out.println("function help_value_assign_branch_id() {"); 
				out.println(" 	document.Form1.TXT_BRANCH_ID.value=oBj.valout[2];"); 
				out.println("}");
				
				
				// end by udara on 27-02-2013
				//report section 
				

				
				out.println("function run_report() {");
				out.println("	if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" && document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" &&document.Form1.TXT_TO_DATE_YY.value!=\"\" ){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value;"); 
				//out.println("		window.open(m_url)");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Collection_trend_analysis_report?chksql=run_report&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;"); // added by udara on 27-02-2013
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Collection_trend_analysis_report?chksql=run_report&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&location_code=\"+m_location_code+\"&user=\"+m_user;"); // added by udara on 27-02-2013
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
				out.println("	if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" && document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" &&document.Form1.TXT_TO_DATE_YY.value!=\"\" ){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				
				//out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; ");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Collection_trend_analysis_report?chksql=print_report2&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&branch_id=\"+branch_id;"); // added by udara on 27-02-2013
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Collection_trend_analysis_report?chksql=print_report2&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&user=\"+m_user+\"&location_code=\"+m_location_code;"); // added by udara on 27-02-2013
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Recovery_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status;");  // added by udara on 09-05-2013
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection - Collection Trend Analysis Report </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='6%'></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch(Contracts)</DIV></td>"); 
				out.println("<td width='*%' >");
				
				out.println("<select name=\"LOCATION_CODE\" class=\"txt_input\" onchange='clear_user();' >");// 
				
				rs = stmt.executeQuery(
					" SELECT LOCATION_CODE, "+
					"        LOCATION_DESC "+
					" FROM "+m_schema_name+".AF_CO_MAS_LOCATION  WHERE ACTIVE_STATUS='Y' ");

				boolean more = rs.next();				
				out.println("<OPTION value=\"ALL\" SELECTED >ALL</option>");
				while(more){
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					more = rs.next();	
				}	
				
				out.println("</SELECT></TD>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("<table class='table' width='100%'>"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_USER_ID'  class=div_input>User </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER_ID' maxlength='15' style='{width=150px}' size='10' onblur=\"help_button_user_id()\"><input class='but_input' type='button' name='BUT_USER_ID' value=\"Help\" onClick=\"help_button_user_id()\">"); 
				out.println("</td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
				out.println("<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\"  >"); //value=\"01\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" >"); //value=\"04\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"   >");	 //value=\"2007\"
				out.println("</td> ");
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
				out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
				out.println("</td> ");
				out.println("<td width='*%' >");
				
				//report section
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				
				//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' style=\"{width:110px;}\" value=\"View All Receipts\" onClick=\"makeRequest_detail()\">");
				
				out.println("</td> ");
				out.println("<tr>");//Added By Sandun on 16-09-2009
				out.println("<td colspan=4>&nbsp;</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("</table>");  
				out.println("<br>"); 
				out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}			
			
			
			
			
			else if(m_chksql.equals("print_report2")){
				
				String m_branch_id = req.getParameter("branch_id");
				String m_to_date=req.getParameter("to_date");
				String m_from_date=req.getParameter("from_date");
				String m_location_code=req.getParameter("location_code");
				String m_user=req.getParameter("user");
				
				out.println("<HTML><HEAD><TITLE>Collection Trend Analysis Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<SCRIPT language=\"JavaScript\">"); 

				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");

				out.println("<br>");
				out.println("<TABLE  WIDTH='100%' class='table' >");
				out.println("<TR><TD align='Center' ><B> Collection Trend Analysis Report </B></TD></TR>");//added by linda 2013-09-26
				out.println("</TABLE>");
				
				int item_count = 0;
				int row_count = 0;
				
				double unsettle_amount      = 0;
				double rental_amount        = 0;
				double unsettle_amount_perc = 0;
				double rental_amount_perc   = 0;
				double ins_amount           = 0;
				double excess_amount        = 0; // added by udara 10-04-2014
				double excess_amount_perc   = 0; // added by udara 10-04-2014
				double closing_amount       = 0; // added by udara 10-04-2014
				
				//double unsettle_amount_perc = 0;
				//double rental_amount_perc   = 0;
				//double excess_amount_perc   = 0;
				
				double total_variables = 0;
		
				rs1= stmt1.executeQuery(" "+
					" SELECT COUNT(TO_CHAR( ADD_MONTHS(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),LEVEL-1), 'DD-MM-YYYY')) "+
					" FROM DUAL CONNECT BY LEVEL <= MONTHS_BETWEEN( TO_DATE('"+m_to_date+"','DD-MM-YYYY'), TO_DATE('"+m_from_date+"','DD-MM-YYYY'))+1 "+
					" ORDER BY ADD_MONTHS(TRUNC(SYSDATE,'MM'),LEVEL-1) "+
				" ");
				
				if(rs1.next()){
					item_count = rs1.getInt(1);
				}
			
				String [][] date_array  = new String [2][item_count];
				
				rs1= stmt1.executeQuery(" "+
							" SELECT TO_CHAR( ADD_MONTHS(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),LEVEL-1), 'DD-MM-YYYY'), "+
							" TO_CHAR( ADD_MONTHS(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),LEVEL-1), 'ddth Mon') "+ 
							" FROM DUAL CONNECT BY LEVEL <= MONTHS_BETWEEN( TO_DATE('"+m_to_date+"','DD-MM-YYYY'), TO_DATE('"+m_from_date+"','DD-MM-YYYY'))+1 "+
							" ORDER BY ADD_MONTHS(TRUNC(SYSDATE,'MM'),LEVEL-1) "+
							" ");

				int i = 0;
				while(rs1.next()){
					date_array[0][i] = rs1.getString(1);
					date_array[1][i] = rs1.getString(2);
					i++;
				}
				
				out.println("<table width='100%' class='table' border=1 >");	
			
				out.println("<tr>");
				out.println("<td width='1%' ><b> # </b></td>"); 	
				out.println("<td width='5%' ><b>Branch</b></td>"); 			
				
				for(int x=0; x<item_count; x++){
					out.println("<td width='5%' ><b> "+date_array[1][x]+"</b></td>"); 
					out.println("<td width='5%' ><b> Unsettle </b></td>");
					out.println("<td width='5%' ><b> % </b></td>");
					out.println("<td width='5%' ><b> Rental </b></td>");
					out.println("<td width='5%' ><b> % </b></td>");
					out.println("<td width='5%' ><b> Excess </b></td>"); // added by udara 10-04-2014
					out.println("<td width='5%' ><b> % </b></td>");      // added by udara 10-04-2014
					out.println("<td width='5%' ><b> Ins. </b></td>");
					out.println("<td width='5%' ><b> Closing </b></td>");// added by udara 10-04-2014
				}

				out.println("</tr>"); 
				
				rs1= stmt1.executeQuery(" "+
					" SELECT LOCATION_CODE, "+
					"        LOCATION_DESC "+
					" FROM "+m_schema_name+".AF_CO_MAS_LOCATION  WHERE ACTIVE_STATUS='Y' ");

				while(rs1.next()){
					
					row_count++;
					
					out.println("<tr>");
					out.println("<td width='1%' ><b> "+row_count+" </b></td>");
					//out.println("<td width='5%' ><b> "+rs1.getString(1)+" </b></td>"); // LOCATION
					out.println("<td width='5%' ><b> "+rs1.getString(2)+" </b></td>"); // LOCATION // 03-02-2014
					
					for(int x=0; x<item_count; x++){
						
						rs= stmt.executeQuery(" "+
							" SELECT SUM(NVL(UNSETTLE,0)), "+
							" SUM(NVL(RENTAL,0)), "+ 
							" SUM(NVL(INSURANCE,0)), "+
							" SUM(NVL(EXCESS,0)), "+ // added by udara 10-04-2014
							" SUM(NVL(CLOSING,0)) "+ // added by udara 10-04-2014
							" FROM "+m_schema_name+".COLLECTION_TREND_ANALYSIS "+
							" WHERE BRANCH   = '"+rs1.getString(1)+"' "+
							" AND   RPT_DATE = TO_DATE('"+date_array[0][x]+"','DD-MM-YYYY') "+
							" AND   ENT_USER = '"+m_username+"' "+
							" ");
						
						
						/*
						rs= stmt.executeQuery(" "+
						  " SELECT "+
							" SUM("+m_schema_name+".AF_GET_COLL_TREND_ANA_UNSETTLE(A.FINANCE_NO,'"+date_array[0][x]+"')) "+
								  " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B, "+m_schema_name+".CO_CO_MAS_EMPLOYEE C "+
								  " WHERE  A.CLIENT_CODE = B.CLIENT_CODE "+
								  " AND    A.COLLECTION_OFFICER = C.EMP_CODE(+) "+
								  " AND    A.APPLICATION_STATUS IN( 'ACTIVATED','TERMI','TERMINATED','NORM_TERMI','LEGAL','REPOSSESS') "+
								  " AND    A.BRANCH_CODE =  '"+rs1.getString(1)+"' "+
								  " AND    A.ACTIVATED_DATE <= TRUNC ( TO_DATE ( '"+date_array[0][x]+"','DD-MM-YYYY' ),'DD' ) "+
								  " AND    (A.TER_TYPE_ENT_DATE IS NULL OR TO_DATE(AF_CO_GET_TERMINATED_DATE(A.FINANCE_NO),'DD-MM-YYYY') >(  "+
								              " LAST_DAY(ADD_MONTHS(TO_DATE ( '"+date_array[0][x]+"','DD-MM-YYYY' ),-1)) "+
								            " ) "+
								         " )   "+
								" ");
						*/					
											
							if(rs.next()){
								unsettle_amount  = rs.getDouble(1);
								rental_amount    = rs.getDouble(2);
								ins_amount       = rs.getDouble(3);
								excess_amount    = rs.getDouble(4); // added by udara 10-04-2014
								closing_amount   = rs.getDouble(5); // added by udara 10-04-2014
						    }
							
							
					    unsettle_amount_perc = 0;
				        rental_amount_perc   = 0;
				        excess_amount_perc   = 0;

						total_variables = unsettle_amount+rental_amount+ins_amount+excess_amount+closing_amount;
						
						if(total_variables!=0){
							unsettle_amount_perc = unsettle_amount / total_variables;
							rental_amount_perc = rental_amount / total_variables;
							excess_amount_perc = excess_amount / total_variables;
						}
						
					    
						out.println("<td width='5%' align=right > "+nf.format(unsettle_amount+rental_amount+ins_amount+excess_amount+closing_amount)+" </td>"); 
						out.println("<td width='5%' align=right > "+nf.format(unsettle_amount)+" </td>"); 
						out.println("<td width='5%' align=right > "+nf.format(unsettle_amount_perc)+" </td>");
						out.println("<td width='5%' align=right > "+nf.format(rental_amount)+" </td>"); 
						out.println("<td width='5%' align=right > "+nf.format(rental_amount_perc)+" </td>");
						out.println("<td width='5%' align=right > "+nf.format(excess_amount)+" </td>"); // added by udara 10-04-2014
						out.println("<td width='5%' align=right > "+nf.format(excess_amount_perc)+" </td>"); // added by udara 10-04-2014
						out.println("<td width='5%' align=right > "+nf.format(ins_amount)+" </td>"); 
						out.println("<td width='5%' align=right > "+nf.format(closing_amount)+" </td>"); // added by udara 10-04-2014
					}

					out.println("</tr>"); 
					
				}
				
				out.println("</table>");
				
				out.println("</table>"); 
				
			}
			
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(rs3!=null){try{rs3.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
	
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
			if(out!=null){
				try{out.close();  
				}catch(Exception e){}
			}
		}
	}
}