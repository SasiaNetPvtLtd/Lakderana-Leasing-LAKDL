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


public class LAKDL_AF_MISF_Total_Unsettle_Report_V2 extends javax.servlet.http.HttpServlet { 
	
	
	
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
			
			stmt3 = conn.createStatement ();
			
			if(m_chksql.equals("run_report")){ 
				
				String m_date=req.getParameter("date");
				String m_location_id=req.getParameter("location_id");
				String m_user_id=req.getParameter("user_id");
				String m_finance_no = req.getParameter("finance_no");
				String m_cr_offic   = req.getParameter("cr_officer");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".af_re_save_total_unsettle_rpt(:1,:2,:3,:4,:5,:6);END;");//6 mod by milinda
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_user_id);
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_finance_no);
					callstmt1.setString(6,m_cr_offic);//added milinda 2013-10-16
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
				out.println("<TITLE>Collection - Total Unsettle Report Version 2</TITLE>"); 
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
				out.println("finance_no = document.Form1.TXT_FINANCE.value;");
				out.println(" m_cr_officer=document.Form1.MKT_OFFICER.value;");//added milinda 2013-10-16
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value;"); //COMMENTED MILINDA 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&cr_officer=\"+m_cr_officer;"); //MOD BY MILINDA
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				
				out.println("			alert('Total Unsettle Report is generated. Use View Report button to get the view.');");
				
				//out.println("			print_report2();"); 
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
				out.println("		m_officer=document.Form1.TXT_USER.value;");
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10(#16240)
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date;");	
				out.println("var perform_status = document.Form1.TXT_PERFORM_STATUS.value;     ");
				out.println(" m_cr_officer=document.Form1.MKT_OFFICER.value;");//added milinda 2013-10-16
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status;");  // added by udara on 09-05-2013
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report_V2?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status+\"&cr_officer=\"+m_cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");  // added by udara on 09-05-2013 //mod by milinda 2013-10-16
				
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				//[ADDED BY MILINDA ON 10-09-2020 JB09092020-11779]
				out.println("function print_report_month(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("		m_officer=document.Form1.TXT_USER.value;");
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10(#16240)
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date;");	
				out.println("var perform_status = document.Form1.TXT_PERFORM_STATUS.value;     ");
				out.println(" m_cr_officer=document.Form1.MKT_OFFICER.value;");//added milinda 2013-10-16
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status;");  // added by udara on 09-05-2013
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report_V2?chksql=print_report_month_end&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status+\"&cr_officer=\"+m_cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");  // added by udara on 09-05-2013 //mod by milinda 2013-10-16
				
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				
				
				
				out.println("function print_report_prev(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("		m_officer=document.Form1.TXT_USER.value;");
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10(#16240)
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date;");	
				out.println("var perform_status = document.Form1.TXT_PERFORM_STATUS.value;     ");
				out.println(" m_cr_officer=document.Form1.MKT_OFFICER.value;");//added milinda 2013-10-16
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status;");  // added by udara on 09-05-2013
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report_V2?chksql=print_report_previous&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&perform_status=\"+perform_status+\"&cr_officer=\"+m_cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");  // added by udara on 09-05-2013 //mod by milinda 2013-10-16
				
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
				//added milinda
				out.println("			if(data_vec.length==0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("     mk_officer_help(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("			document.Form1.MKT_OFFICER.value=data_vec[0]");
				out.println("			}");
				
				out.println("}");
				
				
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				
				out.println("else if(document.Form1.hid_chk_status.value=='M_MKT_OFFC' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_credit_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.MKT_OFFICER.value+\"&ac_status=Y\";");	
				
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Total Unsettle Report Version 2- \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Total Unsettle Report Version 2- \"+document.Form1.hid_status.value;"); 
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
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		help_cr_assign(oBj);"); 
				out.println("		}"); 
				//out.printrln("      if(IfCount==\"5\"){ ");
				//out.println("       help_cr_assign(oBj);");
				//out.println("		}"); 
				
				
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
				
				
				//credit  officer help
				out.println("function mk_officer_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				out.println("    Crit = document.Form1.MKT_OFFICER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_credi_officer','5');"); 
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
				//added milinda
				out.println("function help_cr_assign() {"); 
				out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[2];"); 
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
				
				out.println("		if(IfCount==\"5\"){"); 
				out.println("document.Form1.MKT_OFFICER.value='';");
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Total Unsettle Report Version 2</td>"); 
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
				
				//out.println("<tr style='display:none'>"); 
				out.println("<tr>");
				out.println("<td width='20%' > Perform Status </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_PERFORM_STATUS'>"); 
				out.println("<option value=''    > All </option>");
				out.println("<option value='PERFORM'  > Perform </option>");//Added by Dineth on 2008-12-16
				out.println("<option value='NPERFORM' > Non Perform </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by udara on 09-05-2013
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT1' value=\"View Report Previous\" onClick=\"print_report_prev()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' style='display: none;' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report-Month End\" onClick=\"print_report_month()\" style=\"{width:150px;}\"></td>");//[ADDED BY MILINDA ON 10-09-2020 JB09092020-11779]
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr  style='display:none' >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr  style='display:none'>"); //Added By Sandun on 07-11-2008
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='10' style='{width=150px}' size='10' onblur='help_button_finance()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				//added milinda
				out.println("<tr  style='display:none'>"); 
				
				out.println("<td width='20%' ><DIV id='DIV_MKT_OFFICER'  class=div_input>Credit Officer *</DIV></td>"); 
				out.println("<td width='*%%' ><input class='txt_input' type='text' name='MKT_OFFICER' maxlength='50' size='10' style=\"{width:150px}\"  onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_CONDITION_OF_ASSET' value=\"Help\" onClick=\"mk_officer_help()\"></td>"); 
				
				
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
				String m_date2="";
				String m_month="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_cr_officer = ""; //added by milinda
				String m_cr_officer_string ="";//added milinda
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_region="";
				
				String mm_perform_status = ""; // added by udara 17-08-2015
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
					mm_perform_status=req.getParameter("perform_status").trim();
				}
				
				// added by udara 07-10-2014
				if(m_perform_status.equals("PERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'PERFORM' ";
				else if(m_perform_status.equals("NPERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'NPERFORM' ";
				else
					m_perform_status = " ";
				// end by udara 07-10-2014
				
				
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
				out.println("window.open(m_url,'slab','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function drill(val){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report_V2?chksql=print_report_normal_drill&perform_status="+mm_perform_status+"&active_status="+m_active_status+"&region="+m_region+"&officer="+m_officer+"&date="+m_date+"&location=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD/MM/YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Month') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_date2 = rs1.getString(3);
					m_month = rs1.getString(4);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
				
				
				Sql_data=" "+
					//[Previous part]
					" SELECT  "+
					//" NVL( A.TOTAL_PERIOD,0), "+ //1
					" COUNT(*), "+
					" COUNT(*), "+ //2
					" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0)) total_arrears, "+ //3 // commented by udara on 08-07-2013
					//" NVL (SUM(DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0) total_arrears, "+ //3 // added by udara on 08-07-2013
					" SUM( A.RENTAL_AMOUNT) , "+ //4
					" SUM (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					" SUM (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					" SUM (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL (SUM (A.total_amount), 0), "+ //8
					" NVL (SUM (A.CLOSING_BALANCE), 0), "+ //9
					" NVL (SUM (A.SETTLED_AMOUNT), 0), "+ //10
					" NVL (SUM (A.ADJUSTED_AMOUNT), 0), "+ //11
					" NVL(SUM(A.FUTURE_RENTAL),0),"+//12
					
					" NVL(SUM(A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
					
					
					" NVL(SUM(A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					
					//" NVL (SUM(DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					
					// added by udara on 29-06-2013
					" NVL ( "+
					" SUM( "+
					" DECODE( "+
					" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL -  "+
					" (A.RENTAL_AMOUNT- "+ 
					" DECODE(	 "+					          
					" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
					" ) "+
					
					" )), "+
					" 1 , "+
					" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+						    
					" SETTLED_AMOUNT_CUR_MON_RENTAL "+
					" ) "+
					" ) "+
					" ,0), "+ // 15
					
					
					//" NVL(SUM(SETTLED_OTH_FROM_EXCESS),0)"+//16
					" SUM(DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)), "+ // 16 added by udara on 02-05-2013
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE   "+ 
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) "+
					" ) NEW_CLOSING, "+ // 17
					
					
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE  "+  
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) > 0 "+
					" ) "+
					" ) THEN   "+
					" 0  "+
					" ELSE    "+ 
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0) "+
					" END "+
					" )  "+
					" ) NEW_FUTURE_RENTAL, "+ // 18
					
					" SUM( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE	 "+			    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END "+
					" ) RENTAL_AMOUNT_NEW	 "+ // 19
					
					
					
					// added by udara 05-11-2013	
					
					" ,SUM( "+
					" CASE WHEN ( "+
					
					// future rental <= 0
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE    "+ 
					" NVL(A.CLOSING_BALANCE,0) "+ 
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN   "+ 
					" 0   "+
					" ELSE   "+   
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  <= 0  "+
					" ) "+
					
					
					// closing >= 0
					" AND "+
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) >= 0 "+
					" ) "+
					
					
					" )  "+
					" THEN   "+
					//1
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) "+
					" ) "+
					
					
					
					" ELSE    "+
					//2
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+ 
					" ELSE    "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) + "+
					
					
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+  
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN    "+
					" 0   "+
					" ELSE      "+
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  "+
					
					" ) "+
					
					
					
					" END "+
					" )  NEW_CASH_TOTAL, "+ // 20
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE)  "+ //21
					
					
					" ,NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) - NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 22 added by udara 18-07-2016
					
					
					//[end]
					/*" SELECT "+
					
					//" TOTAL_PERIOD, "+
					" COUNT(*) COUNT, "+
					" COUNT(*) COUNT, "+
					" SUM(TOTAL_ARREARS), "+
					" SUM(RENTAL_AMOUNT), "+ 
					" SUM(SETTLED_AMOUNT_CUR_MON_RENTAL), "+
					" SUM(SETTLED_AMOUNT_CUR_MON_ARREARS), "+
					" SUM(SETTLED_AMOUNT_CUR_MON), "+
					" SUM(TOTAL_AMOUNT), "+
					" SUM(CLOSING_BALANCE), "+
					" SUM(SETTLED_AMOUNT), "+
					" SUM(ADJUSTED_AMOUNT), "+
					" SUM(FUTURE_RENTAL), "+
					" SUM(EARLY_SETTLE), "+
					" SUM(EARLY_SETTLE_NEXT_MONTH) , "+
					" SUM(SETTL_AMOUNT_CUR_MON_RENTAL_N), "+
					" SUM(SETTLED_OTH_FROM_EXCESS), "+
					" SUM(NEW_CLOSING), "+
					" SUM(NEW_FUTURE_RENTAL), "+
					" SUM(RENTAL_AMOUNT_NEW), "+
					" SUM(NEW_CASH_TOTAL), "+
					" SUM(CR_NOTE_1), "+
					" SUM(CR_NOTE_2)  "+
					
					" FROM ( "+				
					
					" SELECT NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+
					" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+
					" A.RENTAL_AMOUNT RENTAL_AMOUNT, "+
					" A.SETTLED_AMOUNT_CUR_MON_RENTAL SETTLED_AMOUNT_CUR_MON_RENTAL, "+
					" A.SETTLED_AMOUNT_CUR_MON_ARREARS SETTLED_AMOUNT_CUR_MON_ARREARS, "+
					" A.SETTLED_AMOUNT_CUR_MON SETTLED_AMOUNT_CUR_MON, "+
					" NVL (A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+
					" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE, "+
					" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+
					" NVL (A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT, "+
					" NVL(A.FUTURE_RENTAL,0) FUTURE_RENTAL, "+
					" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+
					" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+
					" NVL ( DECODE( SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - (A.RENTAL_AMOUNT- DECODE( SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) ) )), 1 , (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), SETTLED_AMOUNT_CUR_MON_RENTAL )  ,0) SETTL_AMOUNT_CUR_MON_RENTAL_N, "+
					" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS, "+
					
					" ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) ) NEW_CLOSING, "+
					
					" ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) ) NEW_FUTURE_RENTAL, "+
					
					" ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0) -NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) RENTAL_AMOUNT_NEW , "+
					
					" ( "+
					" CASE "+
					" WHEN ( ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) <= 0 ) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) >= 0 ) ) "+
					" THEN ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) ) "+
					" ELSE ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0) -NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) + ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) ) "+
					" END ) NEW_CASH_TOTAL, "+
					
					" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE_1 , "+
					" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE_2 "+*/
					" ,A.LOCATION_CODE LOCATION_CODE "+
					
					" FROM "+m_schema_name+".AF_RE_TOTAL_UNSETTLE_REPORT_V1 A, "+m_schema_name+".af_co_pro_application_details B "+ //ADDED MILINDA
					" WHERE  A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.collection_officer like UPPER('"+m_officer+"%') "+
					" AND A.LOCATION_CODE     like UPPER('"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					
					"  "+m_perform_status+" "+ // added by udara 07-10-2014
					m_active_status_string +
					
					" AND A.APPLICATION_NO = B.APPLICATION_NO "; // ADDED MILINDA
				
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				
				//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') "+ //ADDED MILINDA 
				Sql_data = Sql_data + " AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
					// Sql_data = Sql_data + m_cr_officer_string; // added by milinda
					" GROUP BY A.LOCATION_CODE ORDER BY A.LOCATION_CODE "; 
				
				out.println("<!--"+Sql_data+"-->");
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				
				BigDecimal total_end_total = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_AdjAmount = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>TOTAL UNSETTLED REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				out.println("<table>");
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(mm_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(mm_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(mm_perform_status.equals("NPERFORM"))
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
				out.println("</table>");
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
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
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
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
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Branch</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>To be Collected "+m_date2+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Contracts</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Arrears (A)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Unsettle Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total(B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>End Balance "+m_date2+"</b></td>"); 
				
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				BigDecimal end_balance= new BigDecimal(0.00);
				
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='drill(\""+rs.getString("LOCATION_CODE")+"\")'   >"+rs.getString(21)+"</td>"); 
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(2))+"</td>");
					
					/*if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					*/
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					
					// =================================
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5)); // commented by udara on 01-08-2013
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(19)); // added by udara on 01-08-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(18)); // added by udara on 1-08-2013
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(22)); // added by udara 18-07-2016
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),2,BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9)); // commented by udara on 29-07-2013
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							//closing = closing.add(futu_rent);	// udara						
							//futu_rent = new BigDecimal(0.00);
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					
					//Added By Kanishka On 10-07-2014 [Request the logic by Client Support #13130]
					//Cash_Total = Cash_Total.add(early_sett).add(Rent_Collec).add(Arrea_Collec); // commented by udara 18-07-2016
					
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ; // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					
					
					// added by udara 06-08-2014
					BigDecimal nnn_cash_tot = new BigDecimal(0.00);
					nnn_cash_tot = nnn_cash_tot.add(early_sett.add(Rent_Collec.add(Arrea_Collec)));
					// end by udara 06-08-2014
					
					
					// commented by udara 18-07-2016
					/*
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(nnn_cash_tot.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(nnn_cash_tot)+"</td>");
					}
					*/
					// added by udara 18-07-2016
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					// end by udara 18-07-2016
					
					
					
					// added by udara 06-08-2014
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					// end by udara 06-08-2014
					
					
					end_balance = new BigDecimal(0.00);
					
					//end_balance = end_balance.add(totalab.subtract(nnn_cash_tot));  // added by udara 03-10-2014 // commented by udara 18-07-2016
					//end_balance = end_balance.add(totalab.subtract(Cash_Total));  // added by udara 18-07-2016 // commented by udara 04-08-2016
					end_balance = end_balance.add(BCF); // added by udara 04-08-2016
					
					if(end_balance.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(end_balance.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(end_balance)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,2,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					//total_cash = total_cash.add(Cash_Total); // commented by udara 07-08-2014
					//total_cash = total_cash.add(nnn_cash_tot); // added by udara 07-08-2014 // commented by udara 18-07-2016
					total_cash = total_cash.add(Cash_Total); // added by udara 18-07-2016
					total_bcf= total_bcf.add(BCF);
					total_end_total = total_end_total.add(end_balance);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					
					// commented by udara 28-07-2015
					/*
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					*/
					
					
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;cursor:pointer;}' onclick=\"drill('ALL')\" ><b>Total</b></td>"); 
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					/*
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					*/
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears,2,BigDecimal.ROUND_HALF_EVEN)); // total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle.signum()>0){
						
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,2,BigDecimal.ROUND_HALF_EVEN)); //total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
					}
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					// end by udara on 01-07-2013
					
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_end_total.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_end_total.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_end_total)+"</b></td>"); 
					}
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
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
			//[DRILL]
			
			else if(m_chksql.equals("print_report_normal_drill")){		
				
				
				String m_date="";
				String m_date2="";
				String m_month="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_cr_officer = ""; //added by milinda
				String m_cr_officer_string ="";//added milinda
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_region="";
				
				String mm_perform_status = ""; // added by udara 17-08-2015
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				/*if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}*/
				
				String mm_location_filter="";
				if(req.getParameter("location")!=null){
					if(!req.getParameter("location").equals( "ALL") ){
						//out.println("1");
						m_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION_CODE = '"+m_location+"' ";
					}else{
						//out.println("2");
						m_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION_CODE LIKE  '%' ";
					}
				}		
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
					mm_perform_status=req.getParameter("perform_status").trim();
				}
				
				// added by udara 07-10-2014
				if(m_perform_status.equals("PERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'PERFORM' ";
				else if(m_perform_status.equals("NPERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'NPERFORM' ";
				else
					m_perform_status = " ";
				// end by udara 07-10-2014
				
				
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD/MM/YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Month') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_date2 = rs1.getString(3);
					m_month = rs1.getString(4);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
				
				
				Sql_data=" "+
					//[Previous part]
					" SELECT  "+
					//" NVL( A.TOTAL_PERIOD,0), "+ //1
					" A.FINANCE_NO , "+
					" A.FINANCE_NO, "+ //2
					" NVL ((DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL ( (a.other_charges_curr_month), 0) - (NVL(A.SETTLED_OTH_FROM_EXCESS,0)) total_arrears, "+ //3 // commented by udara on 08-07-2013
					//" NVL ((DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0) total_arrears, "+ //3 // added by udara on 08-07-2013
					" ( A.RENTAL_AMOUNT) , "+ //4
					"  (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					"  (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					"  (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL ( (A.total_amount), 0), "+ //8
					" NVL ( (A.CLOSING_BALANCE), 0), "+ //9
					" NVL ( (A.SETTLED_AMOUNT), 0), "+ //10
					" NVL ( (A.ADJUSTED_AMOUNT), 0), "+ //11
					" NVL((A.FUTURE_RENTAL),0),"+//12
					
					" NVL((A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
					
					
					" NVL((A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					
					//" NVL ((DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					
					// added by udara on 29-06-2013
					" NVL ( "+
					" ( "+
					" DECODE( "+
					" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL -  "+
					" (A.RENTAL_AMOUNT- "+ 
					" DECODE(	 "+					          
					" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
					" ) "+
					
					" )), "+
					" 1 , "+
					" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+						    
					" SETTLED_AMOUNT_CUR_MON_RENTAL "+
					" ) "+
					" ) "+
					" ,0), "+ // 15
					
					
					//" NVL((SETTLED_OTH_FROM_EXCESS),0)"+//16
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)), "+ // 16 added by udara on 02-05-2013
					
					" ( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE   "+ 
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) "+
					" ) NEW_CLOSING, "+ // 17
					
					
					
					" ( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE  "+  
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) > 0 "+
					" ) "+
					" ) THEN   "+
					" 0  "+
					" ELSE    "+ 
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0) "+
					" END "+
					" )  "+
					" ) NEW_FUTURE_RENTAL, "+ // 18
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE	 "+			    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END "+
					" ) RENTAL_AMOUNT_NEW	 "+ // 19
					
					
					
					// added by udara 05-11-2013	
					
					" ,( "+
					" CASE WHEN ( "+
					
					// future rental <= 0
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE    "+ 
					" NVL(A.CLOSING_BALANCE,0) "+ 
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN   "+ 
					" 0   "+
					" ELSE   "+   
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  <= 0  "+
					" ) "+
					
					
					// closing >= 0
					" AND "+
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) >= 0 "+
					" ) "+
					
					
					" )  "+
					" THEN   "+
					//1
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) "+
					" ) "+
					
					
					
					" ELSE    "+
					//2
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+ 
					" ELSE    "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) + "+
					
					
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+  
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN    "+
					" 0   "+
					" ELSE      "+
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  "+
					
					" ) "+
					
					
					
					" END "+
					" )  NEW_CASH_TOTAL, "+ // 20
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE)  "+ //21
					
					
					" ,NVL(("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) - NVL(("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 22 added by udara 18-07-2016
					
					" ,A.CLIENT_CODE CLIENT_CODE "+
					//[end]
					
					
					
					" FROM "+m_schema_name+".AF_RE_TOTAL_UNSETTLE_REPORT_V1 A, "+m_schema_name+".af_co_pro_application_details B "+ //ADDED MILINDA
					" WHERE  A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.collection_officer like UPPER('"+m_officer+"%') "+
					//" AND A.LOCATION_CODE     = UPPER('"+m_location+"') "+
					"     "+mm_location_filter+" "+
					" AND A.TOTAL_PERIOD > 0 "+
					
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					
					"  "+m_perform_status+" "+ // added by udara 07-10-2014
					m_active_status_string +
					
					" AND A.APPLICATION_NO = B.APPLICATION_NO "; // ADDED MILINDA
				
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				
				//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') "+ //ADDED MILINDA 
				Sql_data = Sql_data + " AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
					// Sql_data = Sql_data + m_cr_officer_string; // added by milinda
					" /*GROUP BY A.LOCATION_CODE*/ ORDER BY A.LOCATION_CODE "; 
				
				out.println("<!--"+Sql_data+"-->");
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				
				BigDecimal total_end_total = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_AdjAmount = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>TOTAL UNSETTLED REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				out.println("<table>");
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(mm_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(mm_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(mm_perform_status.equals("NPERFORM"))
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
				out.println("</table>");
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
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
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
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
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>No</b></td>"); 		
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Finance Np</b></td>"); 	
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Branch</b></td>"); 		
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>To be Collected "+m_date2+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Contracts</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Arrears (A)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Unsettle Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total(B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>End Balance "+m_date2+"</b></td>"); 
				
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				BigDecimal end_balance= new BigDecimal(0.00);
				
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body    >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='show_transaction_history_new(\""+rs.getString("CLIENT_CODE")+"\",\""+rs.getString("FINANCE_NO")+"\")' >"+rs.getString(1)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(21)+"</td>"); 
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(2))+"</td>");
					
					/*if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					*/
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					
					// =================================
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5)); // commented by udara on 01-08-2013
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(19)); // added by udara on 01-08-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(18)); // added by udara on 1-08-2013
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(22)); // added by udara 18-07-2016
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),2,BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9)); // commented by udara on 29-07-2013
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							//closing = closing.add(futu_rent);	// udara						
							//futu_rent = new BigDecimal(0.00);
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					
					//Added By Kanishka On 10-07-2014 [Request the logic by Client Support #13130]
					//Cash_Total = Cash_Total.add(early_sett).add(Rent_Collec).add(Arrea_Collec); // commented by udara 18-07-2016
					
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ; // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					
					
					// added by udara 06-08-2014
					BigDecimal nnn_cash_tot = new BigDecimal(0.00);
					nnn_cash_tot = nnn_cash_tot.add(early_sett.add(Rent_Collec.add(Arrea_Collec)));
					// end by udara 06-08-2014
					
					
					// commented by udara 18-07-2016
					/*
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(nnn_cash_tot.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(nnn_cash_tot)+"</td>");
					}
					*/
					// added by udara 18-07-2016
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					// end by udara 18-07-2016
					
					
					
					// added by udara 06-08-2014
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					// end by udara 06-08-2014
					
					
					end_balance = new BigDecimal(0.00);
					
					//end_balance = end_balance.add(totalab.subtract(nnn_cash_tot));  // added by udara 03-10-2014 // commented by udara 18-07-2016
					//end_balance = end_balance.add(totalab.subtract(Cash_Total));  // added by udara 18-07-2016 // commented by udara 04-08-2016
					end_balance = end_balance.add(BCF); // added by udara 04-08-2016
					
					if(end_balance.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(end_balance.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(end_balance)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					//total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,2,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					//total_cash = total_cash.add(Cash_Total); // commented by udara 07-08-2014
					//total_cash = total_cash.add(nnn_cash_tot); // added by udara 07-08-2014 // commented by udara 18-07-2016
					total_cash = total_cash.add(Cash_Total); // added by udara 18-07-2016
					total_bcf= total_bcf.add(BCF);
					total_end_total = total_end_total.add(end_balance);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					
					// commented by udara 28-07-2015
					/*
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					*/
					
					
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					/*if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					*/
					/*
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					*/
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears,2,BigDecimal.ROUND_HALF_EVEN)); // total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle.signum()>0){
						
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,2,BigDecimal.ROUND_HALF_EVEN)); //total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
					}
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					// end by udara on 01-07-2013
					
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_end_total.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_end_total.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_end_total)+"</b></td>"); 
					}
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
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
			
			//[END DRILL]
			
			//[ADDED BY MILINDA ON 10-09-2020 JB09092020-11779]
			else if(m_chksql.equals("print_report_month_end")){		
				
				
				String m_date="";
				String m_date2="";
				String m_month="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_cr_officer = ""; //added by milinda
				String m_cr_officer_string ="";//added milinda
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_region="";
				
				String mm_perform_status = ""; // added by udara 17-08-2015
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
					mm_perform_status=req.getParameter("perform_status").trim();
				}
				
				// added by udara 07-10-2014
				if(m_perform_status.equals("PERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'PERFORM' ";
				else if(m_perform_status.equals("NPERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'NPERFORM' ";
				else
					m_perform_status = " ";
				// end by udara 07-10-2014
				
				
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
				out.println("window.open(m_url,'slab','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function drill(val){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report_V2?chksql=print_report_drill&perform_status="+mm_perform_status+"&active_status="+m_active_status+"&region="+m_region+"&officer="+m_officer+"&date="+m_date+"&location=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD/MM/YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Month') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_date2 = rs1.getString(3);
					m_month = rs1.getString(4);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
				
				
				Sql_data=" "+
					//[Previous part]
					" SELECT  "+
					//" NVL( A.TOTAL_PERIOD,0), "+ //1
					" COUNT(*), "+
					" COUNT(*), "+ //2
					" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0)) total_arrears, "+ //3 // commented by udara on 08-07-2013
					//" NVL (SUM(DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0) total_arrears, "+ //3 // added by udara on 08-07-2013
					" SUM( A.RENTAL_AMOUNT) , "+ //4
					" SUM (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					" SUM (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					" SUM (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL (SUM (A.total_amount), 0), "+ //8
					" NVL (SUM (A.CLOSING_BALANCE), 0), "+ //9
					" NVL (SUM (A.SETTLED_AMOUNT), 0), "+ //10
					" NVL (SUM (A.ADJUSTED_AMOUNT), 0), "+ //11
					" NVL(SUM(A.FUTURE_RENTAL),0),"+//12
					
					" NVL(SUM(A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
					
					
					" NVL(SUM(A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					
					//" NVL (SUM(DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					
					// added by udara on 29-06-2013
					" NVL ( "+
					" SUM( "+
					" DECODE( "+
					" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL -  "+
					" (A.RENTAL_AMOUNT- "+ 
					" DECODE(	 "+					          
					" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
					" ) "+
					
					" )), "+
					" 1 , "+
					" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+						    
					" SETTLED_AMOUNT_CUR_MON_RENTAL "+
					" ) "+
					" ) "+
					" ,0), "+ // 15
					
					
					//" NVL(SUM(SETTLED_OTH_FROM_EXCESS),0)"+//16
					" SUM(DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)), "+ // 16 added by udara on 02-05-2013
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE   "+ 
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) "+
					" ) NEW_CLOSING, "+ // 17
					
					
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE  "+  
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) > 0 "+
					" ) "+
					" ) THEN   "+
					" 0  "+
					" ELSE    "+ 
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0) "+
					" END "+
					" )  "+
					" ) NEW_FUTURE_RENTAL, "+ // 18
					
					" SUM( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE	 "+			    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END "+
					" ) RENTAL_AMOUNT_NEW	 "+ // 19
					
					
					
					// added by udara 05-11-2013	
					
					" ,SUM( "+
					" CASE WHEN ( "+
					
					// future rental <= 0
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE    "+ 
					" NVL(A.CLOSING_BALANCE,0) "+ 
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN   "+ 
					" 0   "+
					" ELSE   "+   
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  <= 0  "+
					" ) "+
					
					
					// closing >= 0
					" AND "+
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) >= 0 "+
					" ) "+
					
					
					" )  "+
					" THEN   "+
					//1
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) "+
					" ) "+
					
					
					
					" ELSE    "+
					//2
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+ 
					" ELSE    "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) + "+
					
					
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+  
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN    "+
					" 0   "+
					" ELSE      "+
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  "+
					
					" ) "+
					
					
					
					" END "+
					" )  NEW_CASH_TOTAL, "+ // 20
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE)  "+ //21
					
					
					" ,NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) - NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 22 added by udara 18-07-2016
					
					
					//[end]
					
					" ,A.LOCATION_CODE LOCATION_CODE "+
					
					" FROM "+m_schema_name+".AF_TOTAL_UNSETTLE_REPORT_MON A, "+m_schema_name+".af_co_pro_application_details B "+ //ADDED MILINDA
					" WHERE  A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.collection_officer like UPPER('"+m_officer+"%') "+
					" AND A.LOCATION_CODE     like UPPER('"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					
					"  "+m_perform_status+" "+ // added by udara 07-10-2014
					m_active_status_string +
					
					" AND A.APPLICATION_NO = B.APPLICATION_NO "; // ADDED MILINDA
				
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				
				//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') "+ //ADDED MILINDA 
				Sql_data = Sql_data + " AND  (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
					// Sql_data = Sql_data + m_cr_officer_string; // added by milinda
					" GROUP BY A.LOCATION_CODE ORDER BY A.LOCATION_CODE "; 
				
				out.println("<!--"+Sql_data+"-->");
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				
				BigDecimal total_end_total = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_AdjAmount = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>TOTAL UNSETTLED REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				out.println("<table>");
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(mm_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(mm_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(mm_perform_status.equals("NPERFORM"))
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
				out.println("</table>");
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
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
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
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
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Branch</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>To be Collected "+m_date2+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Contracts</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Arrears (A)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Unsettle Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total(B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>End Balance "+m_date2+"</b></td>"); 
				
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				BigDecimal end_balance= new BigDecimal(0.00);
				
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='drill(\""+rs.getString("LOCATION_CODE")+"\")'  >"+rs.getString(21)+"</td>"); 
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(2))+"</td>");
					
					/*if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					*/
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					
					// =================================
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5)); // commented by udara on 01-08-2013
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(19)); // added by udara on 01-08-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(18)); // added by udara on 1-08-2013
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(22)); // added by udara 18-07-2016
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),2,BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9)); // commented by udara on 29-07-2013
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							//closing = closing.add(futu_rent);	// udara						
							//futu_rent = new BigDecimal(0.00);
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					
					//Added By Kanishka On 10-07-2014 [Request the logic by Client Support #13130]
					//Cash_Total = Cash_Total.add(early_sett).add(Rent_Collec).add(Arrea_Collec); // commented by udara 18-07-2016
					
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ; // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					
					
					// added by udara 06-08-2014
					BigDecimal nnn_cash_tot = new BigDecimal(0.00);
					nnn_cash_tot = nnn_cash_tot.add(early_sett.add(Rent_Collec.add(Arrea_Collec)));
					// end by udara 06-08-2014
					
					
					// commented by udara 18-07-2016
					/*
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(nnn_cash_tot.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(nnn_cash_tot)+"</td>");
					}
					*/
					// added by udara 18-07-2016
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					// end by udara 18-07-2016
					
					
					
					// added by udara 06-08-2014
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					// end by udara 06-08-2014
					
					
					end_balance = new BigDecimal(0.00);
					
					//end_balance = end_balance.add(totalab.subtract(nnn_cash_tot));  // added by udara 03-10-2014 // commented by udara 18-07-2016
					//end_balance = end_balance.add(totalab.subtract(Cash_Total));  // added by udara 18-07-2016 // commented by udara 04-08-2016
					end_balance = end_balance.add(BCF); // added by udara 04-08-2016
					
					if(end_balance.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(end_balance.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(end_balance)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,2,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					//total_cash = total_cash.add(Cash_Total); // commented by udara 07-08-2014
					//total_cash = total_cash.add(nnn_cash_tot); // added by udara 07-08-2014 // commented by udara 18-07-2016
					total_cash = total_cash.add(Cash_Total); // added by udara 18-07-2016
					total_bcf= total_bcf.add(BCF);
					total_end_total = total_end_total.add(end_balance);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					
					// commented by udara 28-07-2015
					/*
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					*/
					
					
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;cursor:pointer;}' onclick=\"drill('ALL')\"  ><b>Total</b></td>"); 
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					/*
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					*/
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears,2,BigDecimal.ROUND_HALF_EVEN)); // total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle.signum()>0){
						
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,2,BigDecimal.ROUND_HALF_EVEN)); //total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
					}
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					// end by udara on 01-07-2013
					
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_end_total.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_end_total.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_end_total)+"</b></td>"); 
					}
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
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
			
			//[DRILL]
			
			else if(m_chksql.equals("print_report_drill")){		
				
				
				String m_date="";
				String m_date2="";
				String m_month="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_cr_officer = ""; //added by milinda
				String m_cr_officer_string ="";//added milinda
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_region="";
				
				String mm_perform_status = ""; // added by udara 17-08-2015
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				/*if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}*/
				
				String mm_location_filter="";
				if(req.getParameter("location")!=null){
					if(!req.getParameter("location").equals( "ALL") ){
						//out.println("1");
						m_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION_CODE = '"+m_location+"' ";
					}else{
						//out.println("2");
						m_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION_CODE LIKE  '%' ";
					}
				}		
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
					mm_perform_status=req.getParameter("perform_status").trim();
				}
				
				// added by udara 07-10-2014
				if(m_perform_status.equals("PERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'PERFORM' ";
				else if(m_perform_status.equals("NPERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'NPERFORM' ";
				else
					m_perform_status = " ";
				// end by udara 07-10-2014
				
				
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD/MM/YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Month') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_date2 = rs1.getString(3);
					m_month = rs1.getString(4);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
				
				
				Sql_data=" "+
					//[Previous part]
					" SELECT  "+
					//" NVL( A.TOTAL_PERIOD,0), "+ //1
					//" COUNT(*), "+
					//" COUNT(*), "+ //2
					" A.FINANCE_NO FINANCE_NO,"+
					" A.FINANCE_NO FINANCE_NO,"+
					" NVL ((DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL ( (a.other_charges_curr_month), 0) - (NVL(A.SETTLED_OTH_FROM_EXCESS,0)) total_arrears, "+ //3 // commented by udara on 08-07-2013
					//" NVL ((DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0) total_arrears, "+ //3 // added by udara on 08-07-2013
					" ( A.RENTAL_AMOUNT) , "+ //4
					"  (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					"  (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					"  (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL ( (A.total_amount), 0), "+ //8
					" NVL ( (A.CLOSING_BALANCE), 0), "+ //9
					" NVL ( (A.SETTLED_AMOUNT), 0), "+ //10
					" NVL ( (A.ADJUSTED_AMOUNT), 0), "+ //11
					" NVL((A.FUTURE_RENTAL),0),"+//12
					
					" NVL((A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
					
					
					" NVL((A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					
					//" NVL ((DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					
					// added by udara on 29-06-2013
					" NVL ( "+
					" ( "+
					" DECODE( "+
					" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL -  "+
					" (A.RENTAL_AMOUNT- "+ 
					" DECODE(	 "+					          
					" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
					" ) "+
					
					" )), "+
					" 1 , "+
					" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+						    
					" SETTLED_AMOUNT_CUR_MON_RENTAL "+
					" ) "+
					" ) "+
					" ,0), "+ // 15
					
					
					//" NVL((SETTLED_OTH_FROM_EXCESS),0)"+//16
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)), "+ // 16 added by udara on 02-05-2013
					
					" ( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE   "+ 
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) "+
					" ) NEW_CLOSING, "+ // 17
					
					
					
					" ( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE  "+  
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) > 0 "+
					" ) "+
					" ) THEN   "+
					" 0  "+
					" ELSE    "+ 
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0) "+
					" END "+
					" )  "+
					" ) NEW_FUTURE_RENTAL, "+ // 18
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE	 "+			    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END "+
					" ) RENTAL_AMOUNT_NEW	 "+ // 19
					
					
					
					// added by udara 05-11-2013	
					
					" ,( "+
					" CASE WHEN ( "+
					
					// future rental <= 0
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE    "+ 
					" NVL(A.CLOSING_BALANCE,0) "+ 
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN   "+ 
					" 0   "+
					" ELSE   "+   
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  <= 0  "+
					" ) "+
					
					
					// closing >= 0
					" AND "+
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) >= 0 "+
					" ) "+
					
					
					" )  "+
					" THEN   "+
					//1
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) "+
					" ) "+
					
					
					
					" ELSE    "+
					//2
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+ 
					" ELSE    "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) + "+
					
					
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+  
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN    "+
					" 0   "+
					" ELSE      "+
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  "+
					
					" ) "+
					
					
					
					" END "+
					" )  NEW_CASH_TOTAL, "+ // 20
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE)  "+ //21
					
					
					" ,NVL(("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) - NVL(("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 22 added by udara 18-07-2016
					
					
					//[end]
					",A.CLIENT_CODE CLIENT_CODE "+
					
					
					" FROM "+m_schema_name+".AF_TOTAL_UNSETTLE_REPORT_MON A, "+m_schema_name+".af_co_pro_application_details B "+ //ADDED MILINDA
					" WHERE  A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.collection_officer like UPPER('"+m_officer+"%') "+
					//" AND A.LOCATION_CODE     = UPPER('"+m_location+"') "+
					"     "+mm_location_filter+" "+
					" AND A.TOTAL_PERIOD > 0 "+
					
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					
					"  "+m_perform_status+" "+ // added by udara 07-10-2014
					m_active_status_string +
					
					" AND A.APPLICATION_NO = B.APPLICATION_NO "; // ADDED MILINDA
				
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					Sql_data = Sql_data +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
				}
				
				
				//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') "+ //ADDED MILINDA 
				Sql_data = Sql_data + " AND  (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
					// Sql_data = Sql_data + m_cr_officer_string; // added by milinda
					" /*GROUP BY A.LOCATION_CODE*/ ORDER BY A.LOCATION_CODE "; 
				
				out.println("<!--"+Sql_data+"-->");
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				
				BigDecimal total_end_total = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_AdjAmount = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>TOTAL UNSETTLED REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				out.println("<table>");
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(mm_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(mm_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(mm_perform_status.equals("NPERFORM"))
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
				out.println("</table>");
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
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
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
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
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>No</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Finance No</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Branch</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>To be Collected "+m_date2+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Contracts</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Arrears (A)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Unsettle Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total(B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>End Balance "+m_date2+"</b></td>"); 
				
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				BigDecimal end_balance= new BigDecimal(0.00);
				
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body   >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='show_transaction_history_new(\""+rs.getString("CLIENT_CODE")+"\",\""+rs.getString("FINANCE_NO")+"\")'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(21)+"</td>"); 
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(2))+"</td>");
					
					/*if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					*/
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					
					// =================================
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5)); // commented by udara on 01-08-2013
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(19)); // added by udara on 01-08-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(18)); // added by udara on 1-08-2013
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(22)); // added by udara 18-07-2016
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),2,BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9)); // commented by udara on 29-07-2013
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							//closing = closing.add(futu_rent);	// udara						
							//futu_rent = new BigDecimal(0.00);
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					
					//Added By Kanishka On 10-07-2014 [Request the logic by Client Support #13130]
					//Cash_Total = Cash_Total.add(early_sett).add(Rent_Collec).add(Arrea_Collec); // commented by udara 18-07-2016
					
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ; // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					
					
					// added by udara 06-08-2014
					BigDecimal nnn_cash_tot = new BigDecimal(0.00);
					nnn_cash_tot = nnn_cash_tot.add(early_sett.add(Rent_Collec.add(Arrea_Collec)));
					// end by udara 06-08-2014
					
					
					// commented by udara 18-07-2016
					/*
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(nnn_cash_tot.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(nnn_cash_tot)+"</td>");
					}
					*/
					// added by udara 18-07-2016
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					// end by udara 18-07-2016
					
					
					
					// added by udara 06-08-2014
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					// end by udara 06-08-2014
					
					
					end_balance = new BigDecimal(0.00);
					
					//end_balance = end_balance.add(totalab.subtract(nnn_cash_tot));  // added by udara 03-10-2014 // commented by udara 18-07-2016
					//end_balance = end_balance.add(totalab.subtract(Cash_Total));  // added by udara 18-07-2016 // commented by udara 04-08-2016
					end_balance = end_balance.add(BCF); // added by udara 04-08-2016
					
					if(end_balance.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(end_balance.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(end_balance)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					//total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,2,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					//total_cash = total_cash.add(Cash_Total); // commented by udara 07-08-2014
					//total_cash = total_cash.add(nnn_cash_tot); // added by udara 07-08-2014 // commented by udara 18-07-2016
					total_cash = total_cash.add(Cash_Total); // added by udara 18-07-2016
					total_bcf= total_bcf.add(BCF);
					total_end_total = total_end_total.add(end_balance);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					
					// commented by udara 28-07-2015
					/*
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					*/
					
					
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ></td>");
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					/*if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}*/
					
					/*
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					*/
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears,2,BigDecimal.ROUND_HALF_EVEN)); // total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle.signum()>0){
						
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,2,BigDecimal.ROUND_HALF_EVEN)); //total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
					}
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					// end by udara on 01-07-2013
					
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_end_total.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_end_total.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_end_total)+"</b></td>"); 
					}
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
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
			//[END DRILL]
			else if(m_chksql.equals("print_report_previous")){		
				
				
				String m_date="";
				String m_date2="";
				String m_month="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_cr_officer = ""; //added by milinda
				String m_cr_officer_string ="";//added milinda
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_region="";
				
				String mm_perform_status = ""; // added by udara 17-08-2015
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
					mm_perform_status=req.getParameter("perform_status").trim();
				}
				
				// added by udara 07-10-2014
				if(m_perform_status.equals("PERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'PERFORM' ";
				else if(m_perform_status.equals("NPERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'NPERFORM' ";
				else
					m_perform_status = " ";
				// end by udara 07-10-2014
				
				
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_previous&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
				out.println("window.open(m_url,'slab','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function drill(val){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report_V2?chksql=print_report_previous_drill&perform_status="+mm_perform_status+"&active_status="+m_active_status+"&region="+m_region+"&officer="+m_officer+"&date="+m_date+"&location=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD/MM/YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Month') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_date2 = rs1.getString(3);
					m_month = rs1.getString(4);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
				
				
				Sql_data=" "+
					//[Previous part]
					" SELECT  "+
					//" NVL( A.TOTAL_PERIOD,0), "+ //1
					" COUNT(*), "+
					" COUNT(*), "+ //2
					" NVL (SUM(DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL (SUM (a.other_charges_curr_month), 0) - SUM(NVL(A.SETTLED_OTH_FROM_EXCESS,0)) total_arrears, "+ //3 // commented by udara on 08-07-2013
					//" NVL (SUM(DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0) total_arrears, "+ //3 // added by udara on 08-07-2013
					" SUM( A.RENTAL_AMOUNT) , "+ //4
					" SUM (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					" SUM (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					" SUM (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL (SUM (A.total_amount), 0), "+ //8
					" NVL (SUM (A.CLOSING_BALANCE), 0), "+ //9
					" NVL (SUM (A.SETTLED_AMOUNT), 0), "+ //10
					" NVL (SUM (A.ADJUSTED_AMOUNT), 0), "+ //11
					" NVL(SUM(A.FUTURE_RENTAL),0),"+//12
					
					" NVL(SUM(A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
					
					
					" NVL(SUM(A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					
					//" NVL (SUM(DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					
					// added by udara on 29-06-2013
					" NVL ( "+
					" SUM( "+
					" DECODE( "+
					" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL -  "+
					" (A.RENTAL_AMOUNT- "+ 
					" DECODE(	 "+					          
					" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
					" ) "+
					
					" )), "+
					" 1 , "+
					" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+						    
					" SETTLED_AMOUNT_CUR_MON_RENTAL "+
					" ) "+
					" ) "+
					" ,0), "+ // 15
					
					
					//" NVL(SUM(SETTLED_OTH_FROM_EXCESS),0)"+//16
					" SUM(DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)), "+ // 16 added by udara on 02-05-2013
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE   "+ 
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) "+
					" ) NEW_CLOSING, "+ // 17
					
					
					
					" SUM( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE  "+  
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) > 0 "+
					" ) "+
					" ) THEN   "+
					" 0  "+
					" ELSE    "+ 
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0) "+
					" END "+
					" )  "+
					" ) NEW_FUTURE_RENTAL, "+ // 18
					
					" SUM( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE	 "+			    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END "+
					" ) RENTAL_AMOUNT_NEW	 "+ // 19
					
					
					
					// added by udara 05-11-2013	
					
					" ,SUM( "+
					" CASE WHEN ( "+
					
					// future rental <= 0
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE    "+ 
					" NVL(A.CLOSING_BALANCE,0) "+ 
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN   "+ 
					" 0   "+
					" ELSE   "+   
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  <= 0  "+
					" ) "+
					
					
					// closing >= 0
					" AND "+
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) >= 0 "+
					" ) "+
					
					
					" )  "+
					" THEN   "+
					//1
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) "+
					" ) "+
					
					
					
					" ELSE    "+
					//2
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+ 
					" ELSE    "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) + "+
					
					
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+  
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN    "+
					" 0   "+
					" ELSE      "+
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  "+
					
					" ) "+
					
					
					
					" END "+
					" )  NEW_CASH_TOTAL, "+ // 20
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE)  "+ //21
					
					
					" ,NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) - NVL(SUM("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 22 added by udara 18-07-2016
					
					
					//[end]
					/*" SELECT "+
					
					//" TOTAL_PERIOD, "+
					" COUNT(*) COUNT, "+
					" COUNT(*) COUNT, "+
					" SUM(TOTAL_ARREARS), "+
					" SUM(RENTAL_AMOUNT), "+ 
					" SUM(SETTLED_AMOUNT_CUR_MON_RENTAL), "+
					" SUM(SETTLED_AMOUNT_CUR_MON_ARREARS), "+
					" SUM(SETTLED_AMOUNT_CUR_MON), "+
					" SUM(TOTAL_AMOUNT), "+
					" SUM(CLOSING_BALANCE), "+
					" SUM(SETTLED_AMOUNT), "+
					" SUM(ADJUSTED_AMOUNT), "+
					" SUM(FUTURE_RENTAL), "+
					" SUM(EARLY_SETTLE), "+
					" SUM(EARLY_SETTLE_NEXT_MONTH) , "+
					" SUM(SETTL_AMOUNT_CUR_MON_RENTAL_N), "+
					" SUM(SETTLED_OTH_FROM_EXCESS), "+
					" SUM(NEW_CLOSING), "+
					" SUM(NEW_FUTURE_RENTAL), "+
					" SUM(RENTAL_AMOUNT_NEW), "+
					" SUM(NEW_CASH_TOTAL), "+
					" SUM(CR_NOTE_1), "+
					" SUM(CR_NOTE_2)  "+
					
					" FROM ( "+				
					
					" SELECT NVL( A.TOTAL_PERIOD,0) TOTAL_PERIOD,  "+
					" NVL (DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0),0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+
					" A.RENTAL_AMOUNT RENTAL_AMOUNT, "+
					" A.SETTLED_AMOUNT_CUR_MON_RENTAL SETTLED_AMOUNT_CUR_MON_RENTAL, "+
					" A.SETTLED_AMOUNT_CUR_MON_ARREARS SETTLED_AMOUNT_CUR_MON_ARREARS, "+
					" A.SETTLED_AMOUNT_CUR_MON SETTLED_AMOUNT_CUR_MON, "+
					" NVL (A.TOTAL_AMOUNT, 0) TOTAL_AMOUNT, "+
					" NVL (A.CLOSING_BALANCE, 0) CLOSING_BALANCE, "+
					" NVL (A.SETTLED_AMOUNT, 0) SETTLED_AMOUNT, "+
					" NVL (A.ADJUSTED_AMOUNT, 0) ADJUSTED_AMOUNT, "+
					" NVL(A.FUTURE_RENTAL,0) FUTURE_RENTAL, "+
					" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+
					" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+
					" NVL ( DECODE( SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - (A.RENTAL_AMOUNT- DECODE( SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) ) )), 1 , (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), SETTLED_AMOUNT_CUR_MON_RENTAL )  ,0) SETTL_AMOUNT_CUR_MON_RENTAL_N, "+
					" DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0) SETTLED_OTH_FROM_EXCESS, "+
					
					" ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) ) NEW_CLOSING, "+
					
					" ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) ) NEW_FUTURE_RENTAL, "+
					
					" ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0) -NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) RENTAL_AMOUNT_NEW , "+
					
					" ( "+
					" CASE "+
					" WHEN ( ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) <= 0 ) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) >= 0 ) ) "+
					" THEN ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) ) "+
					" ELSE ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) + NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + ( "+
					" CASE "+
					" WHEN (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) "+
					" THEN NVL(A.RENTAL_AMOUNT,0) -NVL(A.EARLY_SETT,0) "+
					" ELSE NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END ) + ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND ( ( "+
					" CASE "+
					" WHEN (((NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) < 0) "+
					" AND (NVL(A.CLOSING_BALANCE,0) > 0)) "+
					" THEN (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE NVL(A.CLOSING_BALANCE,0) "+
					" END ) > 0 ) ) "+
					" THEN 0 "+
					" ELSE NVL ( A.FUTURE_RENTAL, 0) - NVL(A.EARLY_SETT_NEXT,0) "+
					" END ) ) "+
					" END ) NEW_CASH_TOTAL, "+
					
					" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"'),0) - NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE_1 , "+
					" NVL("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"'),0) CR_NOTE_2 "+*/
					" ,A.LOCATION_CODE LOCATION_CODE "+
					
					" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_HIS A, "+m_schema_name+".af_co_pro_application_details B "+ //ADDED MILINDA
					" WHERE  A.REPORT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND (A.collection_officer) like UPPER('%"+m_officer+"%') "+
					" AND (A.LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
					" AND A.TOTAL_PERIOD > 0 "+
					
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					
					"  "+m_perform_status+" "+ // added by udara 07-10-2014
					m_active_status_string +
					
					" AND A.APPLICATION_NO = B.APPLICATION_NO "; // ADDED MILINDA
				
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
					
				}
				
				
				//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') "+ //ADDED MILINDA 
				Sql_data = Sql_data + " AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
					// Sql_data = Sql_data + m_cr_officer_string; // added by milinda
					" GROUP BY A.LOCATION_CODE ORDER BY A.LOCATION_CODE "; 
				
				out.println("<!--"+Sql_data+"-->");
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				
				BigDecimal total_end_total = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_AdjAmount = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>TOTAL UNSETTLED REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				out.println("<table>");
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(mm_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(mm_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(mm_perform_status.equals("NPERFORM"))
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
				out.println("</table>");
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
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
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
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
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Branch</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>To be Collected "+m_date2+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Contracts</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Arrears (A)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Unsettle Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total(B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>End Balance "+m_date2+"</b></td>"); 
				
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				BigDecimal end_balance= new BigDecimal(0.00);
				
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='drill(\""+rs.getString("LOCATION_CODE")+"\")'   >"+rs.getString(21)+"</td>"); 
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(2))+"</td>");
					
					/*if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}*/
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					
					// =================================
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5)); // commented by udara on 01-08-2013
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(19)); // added by udara on 01-08-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(18)); // added by udara on 1-08-2013
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(22)); // added by udara 18-07-2016
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),2,BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9)); // commented by udara on 29-07-2013
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							//closing = closing.add(futu_rent);	// udara						
							//futu_rent = new BigDecimal(0.00);
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					
					//Added By Kanishka On 10-07-2014 [Request the logic by Client Support #13130]
					//Cash_Total = Cash_Total.add(early_sett).add(Rent_Collec).add(Arrea_Collec); // commented by udara 18-07-2016
					
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ; // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					
					
					// added by udara 06-08-2014
					BigDecimal nnn_cash_tot = new BigDecimal(0.00);
					nnn_cash_tot = nnn_cash_tot.add(early_sett.add(Rent_Collec.add(Arrea_Collec)));
					// end by udara 06-08-2014
					
					
					// commented by udara 18-07-2016
					/*
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(nnn_cash_tot.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(nnn_cash_tot)+"</td>");
					}
					*/
					// added by udara 18-07-2016
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					// end by udara 18-07-2016
					
					
					
					// added by udara 06-08-2014
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					// end by udara 06-08-2014
					
					
					end_balance = new BigDecimal(0.00);
					
					//end_balance = end_balance.add(totalab.subtract(nnn_cash_tot));  // added by udara 03-10-2014 // commented by udara 18-07-2016
					//end_balance = end_balance.add(totalab.subtract(Cash_Total));  // added by udara 18-07-2016 // commented by udara 04-08-2016
					end_balance = end_balance.add(BCF); // added by udara 04-08-2016
					
					if(end_balance.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(end_balance.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(end_balance)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,2,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					//total_cash = total_cash.add(Cash_Total); // commented by udara 07-08-2014
					//total_cash = total_cash.add(nnn_cash_tot); // added by udara 07-08-2014 // commented by udara 18-07-2016
					total_cash = total_cash.add(Cash_Total); // added by udara 18-07-2016
					total_bcf= total_bcf.add(BCF);
					total_end_total = total_end_total.add(end_balance);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					
					// commented by udara 28-07-2015
					/*
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					*/
					
					
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;cursor:pointer;}' onclick=\"drill('ALL')\"  ><b>Total</b></td>"); 
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					
					/*if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}*/
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears,2,BigDecimal.ROUND_HALF_EVEN)); // total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle.signum()>0){
						
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,2,BigDecimal.ROUND_HALF_EVEN)); //total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
					}
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					// end by udara on 01-07-2013
					
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_end_total.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_end_total.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_end_total)+"</b></td>"); 
					}
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
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
			
			//[DRILL]
			else if(m_chksql.equals("print_report_previous_drill")){		
				
				
				String m_date="";
				String m_date2="";
				String m_month="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_date_format="";
				String m_perform_status = ""; // added by udara on 09-05-2013
				String m_cr_officer = ""; //added by milinda
				String m_cr_officer_string ="";//added milinda
				
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				String m_region="";
				
				String mm_perform_status = ""; // added by udara 17-08-2015
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(A.FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				
				
				
				/*if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}*/
				String mm_location_filter="";
				if(req.getParameter("location")!=null){
					if(!req.getParameter("location").equals( "ALL") ){
						//out.println("1");
						m_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION_CODE = '"+m_location+"' ";
					}else{
						//out.println("2");
						m_location=req.getParameter("location").trim();
						mm_location_filter= " AND     A.LOCATION_CODE LIKE  '%' ";
					}
				}	
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// added by udara on 09-05-2013
				if(req.getParameter("perform_status")!=null ){
					m_perform_status=req.getParameter("perform_status").trim();
					mm_perform_status=req.getParameter("perform_status").trim();
				}
				
				// added by udara 07-10-2014
				if(m_perform_status.equals("PERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'PERFORM' ";
				else if(m_perform_status.equals("NPERFORM"))
					m_perform_status = " AND A.PERFORM_STATUS = 'NPERFORM' ";
				else
					m_perform_status = " ";
				// end by udara 07-10-2014
				
				
				
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_previous&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new_drill&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&slab=\"+val+\"&cr_officer="+m_cr_officer+"\";");	
				
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
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD/MM/YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Month') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
					m_date2 = rs1.getString(3);
					m_month = rs1.getString(4);
				}
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
				
				
				Sql_data=" "+
					//[Previous part]
					" SELECT  "+
					//" NVL( A.TOTAL_PERIOD,0), "+ //1
					" A.FINANCE_NO FINANCE_NO , "+
					" A.FINANCE_NO FINANCE_NO, "+ //2
					" NVL ((DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0)),0)+ NVL ( (a.other_charges_curr_month), 0) - (NVL(A.SETTLED_OTH_FROM_EXCESS,0)) total_arrears, "+ //3 // commented by udara on 08-07-2013
					//" NVL ((DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0)),0) total_arrears, "+ //3 // added by udara on 08-07-2013
					" ( A.RENTAL_AMOUNT) , "+ //4
					"  (A.SETTLED_AMOUNT_CUR_MON_RENTAL), "+ //5
					"  (A.SETTLED_AMOUNT_CUR_MON_ARREARS), "+ //6
					"  (A.SETTLED_AMOUNT_CUR_MON), "+  //7	
					" NVL ( (A.total_amount), 0), "+ //8
					" NVL ( (A.CLOSING_BALANCE), 0), "+ //9
					" NVL ( (A.SETTLED_AMOUNT), 0), "+ //10
					" NVL ( (A.ADJUSTED_AMOUNT), 0), "+ //11
					" NVL((A.FUTURE_RENTAL),0),"+//12
					
					" NVL((A.EARLY_SETT),0) EARLY_SETTLE, "+ // 13
					
					
					" NVL((A.EARLY_SETT_NEXT),0) EARLY_SETTLE_NEXT_MONTH, "+ // 14
					
					//" NVL ((DECODE(SIGN(SETTLED_AMOUNT_CUR_MON_RENTAL - (RENTAL_AMOUNT- DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)))), 1 ,(RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1)-RENTAL_AMOUNT),1,RENTAL_AMOUNT,(DECODE(SIGN(TOTAL_AMOUNT),-1,TOTAL_AMOUNT,0)*-1))),SETTLED_AMOUNT_CUR_MON_RENTAL)),0), "+//15
					
					// added by udara on 29-06-2013
					" NVL ( "+
					" ( "+
					" DECODE( "+
					" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL -  "+
					" (A.RENTAL_AMOUNT- "+ 
					" DECODE(	 "+					          
					" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
					" ) "+
					
					" )), "+
					" 1 , "+
					" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+						    
					" SETTLED_AMOUNT_CUR_MON_RENTAL "+
					" ) "+
					" ) "+
					" ,0), "+ // 15
					
					
					//" NVL((SETTLED_OTH_FROM_EXCESS),0)"+//16
					" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0)), "+ // 16 added by udara on 02-05-2013
					
					" ( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE   "+ 
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) "+
					" ) NEW_CLOSING, "+ // 17
					
					
					
					" ( "+
					" ( "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) "+
					" ELSE  "+  
					" NVL(A.CLOSING_BALANCE,0) "+
					" END "+
					" ) > 0 "+
					" ) "+
					" ) THEN   "+
					" 0  "+
					" ELSE    "+ 
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0) "+
					" END "+
					" )  "+
					" ) NEW_FUTURE_RENTAL, "+ // 18
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0) "+
					" ELSE	 "+			    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) "+
					" END "+
					" ) RENTAL_AMOUNT_NEW	 "+ // 19
					
					
					
					// added by udara 05-11-2013	
					
					" ,( "+
					" CASE WHEN ( "+
					
					// future rental <= 0
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE    "+ 
					" NVL(A.CLOSING_BALANCE,0) "+ 
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN   "+ 
					" 0   "+
					" ELSE   "+   
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  <= 0  "+
					" ) "+
					
					
					// closing >= 0
					" AND "+
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+ 
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) >= 0 "+
					" ) "+
					
					
					" )  "+
					" THEN   "+
					//1
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) "+
					" ) "+
					
					
					
					" ELSE    "+
					//2
					
					" ( "+
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN   "+
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+ 
					" ELSE    "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) +  "+
					" NVL(A.SETTLED_AMOUNT_CUR_MON_ARREARS,0) + NVL(A.SETTLED_AMOUNT,0) + "+
					
					" ( "+
					" CASE WHEN  (NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0) > (NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0))) THEN  "+
					" NVL(A.RENTAL_AMOUNT,0)-NVL(A.EARLY_SETT,0)  "+
					" ELSE	 		 "+	    
					" NVL(A.SETTLED_AMOUNT_CUR_MON_RENTAL,0)  "+
					" END  "+
					" ) + "+
					
					
					" (  "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (  "+
					" (   "+
					" CASE WHEN (((NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)) < 0) AND (NVL(A.CLOSING_BALANCE,0) > 0)) THEN  "+  
					" (NVL(A.CLOSING_BALANCE,0) - NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0))  "+
					" ELSE     "+
					" NVL(A.CLOSING_BALANCE,0)  "+
					" END  "+
					" ) > 0  "+
					" )  "+
					" ) THEN    "+
					" 0   "+
					" ELSE      "+
					" NVL ( A.FUTURE_RENTAL, 0)  - NVL(A.EARLY_SETT_NEXT,0)  "+
					" END  "+
					" )  "+
					
					" ) "+
					
					
					
					" END "+
					" )  NEW_CASH_TOTAL, "+ // 20
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE)  "+ //21
					
					
					" ,NVL(("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT(A.FINANCE_NO,'"+m_date+"')),0) - NVL(("+m_schema_name+".GET_CR_NOTE_AMNT_RECOV_RPT_2(A.FINANCE_NO,'"+m_date+"')),0) "+ // 22 added by udara 18-07-2016
					" ,A.CLIENT_CODE CLIENT_CODE "+
					
					//[end]
					
					
					
					" FROM "+m_schema_name+".AF_MIS_RECOVERY_REPORT_HIS A, "+m_schema_name+".af_co_pro_application_details B "+ //ADDED MILINDA
					" WHERE  A.REPORT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND (A.collection_officer) like UPPER('%"+m_officer+"%') "+
					//" AND (A.LOCATION_CODE)      = UPPER('"+m_location+"') "+
					"     "+mm_location_filter+" "+
					" AND A.TOTAL_PERIOD > 0 "+
					
					" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
					
					"  "+m_perform_status+" "+ // added by udara 07-10-2014
					m_active_status_string +
					
					" AND A.APPLICATION_NO = B.APPLICATION_NO "; // ADDED MILINDA
				
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					Sql_data = Sql_data +"    AND   A.REGION_CODE = '"+m_region+"' ";    
					
				}
				
				
				//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO) = UPPER('"+m_cr_officer+"') "+ //ADDED MILINDA 
				Sql_data = Sql_data + " AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
					// Sql_data = Sql_data + m_cr_officer_string; // added by milinda
					" /*GROUP BY A.LOCATION_CODE*/ ORDER BY A.LOCATION_CODE "; 
				
				out.println("<!--"+Sql_data+"-->");
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00);
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				BigDecimal ODI_Amount  = new BigDecimal(0.00);
				
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_early_sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				
				BigDecimal total_end_total = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_AdjAmount = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>TOTAL UNSETTLED REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				out.println("<table>");
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(mm_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(mm_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(mm_perform_status.equals("NPERFORM"))
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
				out.println("</table>");
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
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
				
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
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
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				
				//		out.println("<tr class='factoring-letter-body' bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>No</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Finance No</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>Branch</b></td>"); 			
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'  ><b>NO of Cases</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>To be Collected "+m_date2+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Contracts</b></td>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Arrears (A)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>"); // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Rental Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>"+m_month+" Unsettle Collected</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total(B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>End Balance "+m_date2+"</b></td>"); 
				
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				BigDecimal end_balance= new BigDecimal(0.00);
				
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(10)); // added by udara on 01-07-2013
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body   >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='show_transaction_history_new(\""+rs.getString("CLIENT_CODE")+"\",\""+rs.getString("FINANCE_NO")+"\")'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(21)+"</td>"); 
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add(rs.getBigDecimal(11)));
					}
					
					//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(2))+"</td>");
					
					/*if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}*/
					
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(11)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add(rs.getBigDecimal(11)))+"</td>");
					}
					
					
					futu_rent = new BigDecimal(0.00);
					early_sett = new BigDecimal(0.00) ;
					early_sett_next_month = new BigDecimal(0.00);
					
					
					
					early_sett = early_sett.add(rs.getBigDecimal(13));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(14));
					
					
					if(early_sett.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(early_sett.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(early_sett)+"</td>");
					}
					
					
					
					// added by udara on 02-05-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(16)); 
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					Rent_Collec = new BigDecimal(0.00);
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(15)); 
					
					
					// =================================
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5)); // commented by udara on 01-08-2013
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(19)); // added by udara on 01-08-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 01-08-2013 //added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 01-08-2013
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(18)); // added by udara on 1-08-2013
					
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					
					
					rental_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(4).signum()>0){
						rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6).add(rs.getBigDecimal(10)));	
					
					Arrea_Collec = Arrea_Collec.subtract(rs.getBigDecimal(22)); // added by udara 18-07-2016
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					
					Arrears_pre  = new BigDecimal(0.00);
					if(rs.getBigDecimal(3).signum()>0){
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(11)),2,BigDecimal.ROUND_HALF_EVEN));
					}else if(rs.getBigDecimal(10).signum()>0){
						
						Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(11),2,BigDecimal.ROUND_HALF_EVEN));
					}
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					
					closing = new BigDecimal(0.00);
					//closing = closing.add(rs.getBigDecimal(9)); // commented by udara on 29-07-2013
					closing = closing.add(rs.getBigDecimal(17)); // added by udara on 29-07-2013
					Cash_Total = new BigDecimal(0.00);
					
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							//closing = closing.add(futu_rent);	// udara						
							//futu_rent = new BigDecimal(0.00);
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							//	out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						//out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					
					
					if((rs.getBigDecimal(18).signum()<0) && (rs.getBigDecimal(17).signum()>0)){
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					
					// end by udara on 29-07-2013
					
					Cash_Total = rs.getBigDecimal(20); // added by udara on 05-11-2013
					
					//Added By Kanishka On 10-07-2014 [Request the logic by Client Support #13130]
					//Cash_Total = Cash_Total.add(early_sett).add(Rent_Collec).add(Arrea_Collec); // commented by udara 18-07-2016
					
					
					BCF = new BigDecimal(0.00);
					//BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month).add(closing) ; // commented by udara on 08-07-2013
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month) ;  // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					
					
					// added by udara 06-08-2014
					BigDecimal nnn_cash_tot = new BigDecimal(0.00);
					nnn_cash_tot = nnn_cash_tot.add(early_sett.add(Rent_Collec.add(Arrea_Collec)));
					// end by udara 06-08-2014
					
					
					// commented by udara 18-07-2016
					/*
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(nnn_cash_tot.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(nnn_cash_tot)+"</td>");
					}
					*/
					// added by udara 18-07-2016
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					// end by udara 18-07-2016
					
					
					
					// added by udara 06-08-2014
					if(rs.getBigDecimal(3).signum()>=0){
						
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(11)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(11));
					}
					// end by udara 06-08-2014
					
					
					end_balance = new BigDecimal(0.00);
					
					//end_balance = end_balance.add(totalab.subtract(nnn_cash_tot));  // added by udara 03-10-2014 // commented by udara 18-07-2016
					//end_balance = end_balance.add(totalab.subtract(Cash_Total));  // added by udara 18-07-2016 // commented by udara 04-08-2016
					end_balance = end_balance.add(BCF); // added by udara 04-08-2016
					
					if(end_balance.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(end_balance.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(end_balance)+"</td>");
					}
					
					
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					//total_Cases =total_Cases.add(rs.getBigDecimal(2));
					
					
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					total_Ear_Sett = total_Ear_Sett.add(early_sett);
					total_early_sett_next_month = total_early_sett_next_month.add(early_sett_next_month);
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					//out.println("total_Rental_colle "+total_Rental_colle+" Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent) "+Rent_Collec.subtract(rs.getBigDecimal(10)).subtract(futu_rent));
					total_Rent = total_Rent.add(tot_rent);
					
					total_Rental_Pres = new BigDecimal(0.00);
					if(total_Due_Rental.signum()>0){
						total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,2,BigDecimal.ROUND_HALF_EVEN));
					}
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					total_Arrears_Pres = new BigDecimal(0.00);
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					//total_cash = total_cash.add(Cash_Total); // commented by udara 07-08-2014
					//total_cash = total_cash.add(nnn_cash_tot); // added by udara 07-08-2014 // commented by udara 18-07-2016
					total_cash = total_cash.add(Cash_Total); // added by udara 18-07-2016
					total_bcf= total_bcf.add(BCF);
					total_end_total = total_end_total.add(end_balance);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					
					// commented by udara 28-07-2015
					/*
					Sql_data=" SELECT A.NEW_FUTURE_RENTAL"+
						" FROM "+m_schema_name+".AF_RECOVERY_RPT_SUMMERY A "+
						" WHERE ENTER_USER='"+m_username+"' "+
						" AND ENTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
					
					
					
					rs=stmt.executeQuery(Sql_data);
					
					BigDecimal m_new_future_rental = new BigDecimal(0.00);
					
					
					while(rs.next()){
						m_new_future_rental = rs.getBigDecimal(1);
						
					}
					*/
					
					
					
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					/*if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}*/
					
					
					/*if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}*/
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					
					// added by udara on 01-07-2013
					if(total_Arrears.signum()>0){
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears,2,BigDecimal.ROUND_HALF_EVEN)); // total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
					}else if(total_Settle.signum()>0){
						
						total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,2,BigDecimal.ROUND_HALF_EVEN)); //total_Arrears_Pres= total_Arrears_Pres.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
					}
					
					if(total_Arrears_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					// end by udara on 01-07-2013
					
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_end_total.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_end_total.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_end_total)+"</b></td>"); 
					}
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
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
			//[END DRILL]
			
			
			else if(m_chksql.equals("print_report_new_drill")){		
				
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slab="";
				String m_date_format="";
				
				String m_cr_officer = ""; // udara 22-10-2013
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				// udara 22-10-2013
				if(req.getParameter("cr_officer")!=null ){
					m_cr_officer=req.getParameter("cr_officer").trim();
				}
				
				m_slab = req.getParameter("slab").trim();
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Total Unsettle Report Version 2</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Total_Unsettle_Report?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); // commented by udara on 09-05-2013
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
				
				out.println("function show_followup(val){ ");
				out.println("	m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;");
				out.println("	window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				rs1 = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DDTH MONTH YYYY') FROM DUAL");
				
				if(rs1.next()){
					m_cur_date = rs1.getString(1);
					m_date_format = rs1.getString(2);
				}
				
				
				
				//End by Dineth on 2008-11-17
				
				
				
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
				
				if (m_slab.equals("NEW") ){
					Sql_data=" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0), "+ //1
						" 1, "+ //2
						" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ //3 // commented by udara on 08-07-2013
						//" NVL ( DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0), 0) total_arrears, "+ //3 // added by udara on 08-07-2013
						" NVL (A.RENTAL_AMOUNT, 0), "+ //4
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0), "+ //5
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0), "+ //6
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0), "+  //7	
						" A.FINANCE_NO, "+ //8
						" NVL ( A.total_amount, 0), "+ //9
						" NVL (A.CLOSING_BALANCE, 0), "+ //10
						" NVL (A.SETTLED_AMOUNT, 0), "+ //11
						" NVL ( A.ADJUSTED_AMOUNT, 0), "+ //12
						" NVL ( A.FUTURE_RENTAL, 0) "+ //13
						" ,A.CLIENT_CODE "+ //14
						" ,DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0), "+ // 15 added by udara on 02-05-2013
						
						
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+ // 16
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+ // 17
						
						" NVL ( "+
						" DECODE( "+
						
						" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - "+
						
						" (A.RENTAL_AMOUNT-  "+
						" DECODE( "+
						
						" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT, "+
						" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
						" ) "+
						
						" )), "+
						" 1 , "+
						" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)-A.RENTAL_AMOUNT),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+
						
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL "+
						
						" ) "+
						" ,0) Rental_amount "+	// 18					
						
						
						
						" FROM "+m_schema_name+".AF_RE_TOTAL_UNSETTLE_REPORT A, "+m_schema_name+".af_co_pro_application_details B "+ // mod 22-10-2013
						" WHERE A.ENT_USER='"+m_username+"' "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ // mod 22-10-2013
						" AND A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						//" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
						" AND A.ACTIVATED_DATE  >= TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND A.ACTIVATED_DATE  <= TRUNC(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						" ORDER BY A.TOTAL_PERIOD "; 
				}
				else {
					
					Sql_data=" SELECT  "+
						
						" NVL( A.TOTAL_PERIOD,0), "+ //1
						" 1, "+ //2
						" NVL ( DECODE(SIGN(A.TOTAL_AMOUNT),1,A.TOTAL_AMOUNT,0), 0)+ NVL (a.other_charges_curr_month, 0) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) total_arrears, "+ //3 // commented by udara on 08-07-2013
						//" NVL ( DECODE(SIGN(TOTAL_AMOUNT),1,TOTAL_AMOUNT,0), 0) total_arrears, "+ //3 // added by udara on 08-07-2013
						" NVL (A.RENTAL_AMOUNT, 0), "+ //4
						" NVL (A.SETTLED_AMOUNT_CUR_MON_RENTAL, 0), "+ //5
						" NVL (A.SETTLED_AMOUNT_CUR_MON_ARREARS, 0), "+ //6
						" NVL (A.SETTLED_AMOUNT_CUR_MON, 0), "+  //7	
						" A.FINANCE_NO, "+ //8
						" NVL ( A.total_amount, 0), "+ //9
						" NVL (A.CLOSING_BALANCE, 0), "+ //10
						" NVL (A.SETTLED_AMOUNT, 0), "+ //11
						" NVL ( A.ADJUSTED_AMOUNT, 0), "+ //12
						" NVL ( A.FUTURE_RENTAL, 0) "+ //13
						" ,A.CLIENT_CODE "+ //14
						" ,DECODE(SIGN(A.TOTAL_AMOUNT),-1,NVL(A.SETTLED_OTH_FROM_EXCESS,0),0), "+ // 15 added by udara on 02-05-2013
						
						
						
						" NVL(A.EARLY_SETT,0) EARLY_SETTLE, "+ // 16
						" NVL(A.EARLY_SETT_NEXT,0) EARLY_SETTLE_NEXT_MONTH, "+ // 17
						
						" NVL ( "+
						" DECODE( "+
						
						" SIGN(A.SETTLED_AMOUNT_CUR_MON_RENTAL - "+
						
						" (A.RENTAL_AMOUNT-  "+
						" DECODE( "+
						
						" SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) - NVL(A.SETTLED_OTH_FROM_EXCESS,0) -NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT, "+
						" (DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1) "+
						" ) "+
						
						" )), "+
						" 1 , "+
						" (A.RENTAL_AMOUNT -DECODE(SIGN((DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1)- NVL(A.SETTLED_OTH_FROM_EXCESS,0) - NVL(A.RENTAL_AMOUNT,0)),1,A.RENTAL_AMOUNT,(DECODE(SIGN(A.TOTAL_AMOUNT),-1,A.TOTAL_AMOUNT,0)*-1))), "+
						
						" A.SETTLED_AMOUNT_CUR_MON_RENTAL "+
						
						" ) "+
						" ,0) Rental_amount "+	// 18
						
						
						
						" FROM "+m_schema_name+".AF_RE_TOTAL_UNSETTLE_REPORT A, "+m_schema_name+".af_co_pro_application_details B "+ // mod 22-10-2013
						" WHERE A.ENT_USER='"+m_username+"' "+
						" AND A.APPLICATION_NO = B.APPLICATION_NO "+ // mod 22-10-2013
						" AND A.ENT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND UPPER(A.collection_officer) like UPPER('%"+m_officer+"%') "+
						" AND UPPER(A.LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
						" AND A.TOTAL_PERIOD > 0 "+
						" AND A.TOTAL_PERIOD = '"+m_slab+"' "+
						//" AND STATUS IN ('1','2') "+ //added by ns on 21-03-2013 
						" AND A.ACTIVATED_DATE  < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+
						" AND UPPER (NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER (B.INQUARY_NO),' ')) LIKE UPPER('"+m_cr_officer+"%') "+
						////" AND TRUNC(A.ENT_DATE) = TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" ORDER BY A.TOTAL_PERIOD "; 
					
				}
				
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				int count=0;
				BigDecimal closing_bal = new BigDecimal(0.00);
				//Prabash---------------------**	
				BigDecimal totalab= new BigDecimal(0.00);
				BigDecimal tot_rent= new BigDecimal(0.00); 
				BigDecimal futu_rent = new BigDecimal(0.00); 
				BigDecimal Arrears_pre = new BigDecimal(0.00);
				BigDecimal rental_pre = new BigDecimal(0.00);
				BigDecimal Arrea_Collec = new BigDecimal(0.00);
				BigDecimal Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal Rent_Collec = new BigDecimal(0.00);
				BigDecimal early_sett = new BigDecimal(0.00);
				BigDecimal early_sett_next_month = new BigDecimal(0.00);
				BigDecimal Cash_Total = new BigDecimal(0.00);
				BigDecimal BCF = new BigDecimal(0.00);
				BigDecimal closing  = new BigDecimal(0.00);
				
				BigDecimal total_AdjAmount  = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_Arrears_pre = new BigDecimal(0.00); // added by udara on 01-07-2013
				BigDecimal total_Settle = new BigDecimal(0.00); // added by udara on 01-07-2013
				
				
				BigDecimal total_Cases = new BigDecimal(0.00);
				BigDecimal total_Arrears = new BigDecimal(0.00);
				BigDecimal total_Due_Rental = new BigDecimal(0.00);
				BigDecimal total_AB = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett = new BigDecimal(0.00);
				BigDecimal total_Ear_Sett_next_month = new BigDecimal(0.00);
				BigDecimal total_Rental_colle = new BigDecimal(0.00);
				BigDecimal total_Other_charges_coll_from_excess = new BigDecimal(0.00); // added by udara on 02-05-2013
				BigDecimal total_Rent = new BigDecimal(0.00);
				BigDecimal total_Rental_Pres = new BigDecimal(0.00);
				BigDecimal total_Arresrs_colle = new BigDecimal(0.00);
				BigDecimal total_Arrears_Pres = new BigDecimal(0.00);
				BigDecimal total_Future_ren = new BigDecimal(0.00);
				BigDecimal total_Future_ren_show = new BigDecimal(0.00); // added by udara on 29-07-2013
				BigDecimal total_Closing = new BigDecimal(0.00);
				BigDecimal total_cash = new BigDecimal(0.00);
				BigDecimal total_bcf = new BigDecimal(0.00);	
				//-------------------------------------**	
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>RECOVERY REPORT</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>AS AT "+m_date_format+"</u></td>"); 
				out.println("</tr >");
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Slab "+m_slab+"</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   >&nbsp</td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font:  8pt arial; text-align:center;}'   >[All figures in Rs.]</td>"); 
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
				}
				
				out.println("<table  cellspacing=0 > "); 
				out.println("<tr> "); 
				out.println("<td width='20'> "); 
				out.println("</td> "); 
				out.println("<td> "); 
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				//	out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2'><b>SLAB</b></td>"); 
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>Finance Number</b></td>"); 
				out.println("<td width='40%' class=div_input colspan=\"5\" align='center' bgcolor='lightblue' ><B> AMOUNTS TO BE RECOVERED</B></td>");
				out.println("<td width='40%' class=div_input colspan=\"9\" align='center' bgcolor='lightblue' ><B> AMOUNTS RECOVERED</B></td>"); // modified by udara on 02-05-2013 from 8 columns to 9
				out.println("<td width=\"5%\"  align='center' ROWSPAN='2' ><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;' ><b>Total Arrears</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Monthly Due Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total (A+B)</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Early Settlment Next Month</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Other Charges Collected from Excess</b></td>");  // added by udara on 02-05-2013
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Retal Collected During the Period</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Total Rental</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Arrears Collection</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>%</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Future Rentals</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Closing</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>Cash Total</b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:center;'><b>B/C/F </b></td>"); 
				out.println("</tr >");
				
				
				//=================================================
				
				
				int j=1;
				BigDecimal m_total_due= new BigDecimal(0.00);
				BigDecimal total_mon_rental= new BigDecimal(0.00);
				BigDecimal total_curr_due=  new BigDecimal(0.00);
				BigDecimal sub_close= new BigDecimal(0.00);
				BigDecimal sub_open= new BigDecimal(0.00);
				
				
				
				while(more){
					
					total_Settle = total_Settle.add(rs.getBigDecimal(11)); // added by udara on 01-07-2013   
					total_AdjAmount = total_AdjAmount.add(rs.getBigDecimal(12)); // added by udara on 01-07-2013
					
					out.println("<tr  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					//		out.println("<td  class=factoring-letter-body   bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+rs.getString(1)+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;cursor:pointer;}' onclick='show_transaction_history_new(\""+rs.getString("CLIENT_CODE")+"\",\""+rs.getString(8)+"\")'  ><u>"+rs.getString(8)+"</u></td>");
					if(rs.getBigDecimal(3).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format((rs.getBigDecimal(12)))+"</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(3).add((rs.getBigDecimal(12))))+"</td>");
					}
					
					if(rs.getBigDecimal(4).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(4).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(4))+"</td>");
					}
					
					totalab  = new BigDecimal(0.00);
					if((rs.getBigDecimal(3)).signum()>=0){
						totalab= totalab.add(rs.getBigDecimal(3).add((rs.getBigDecimal(12)))).add(rs.getBigDecimal(4));
					}else{
						totalab= totalab.add(rs.getBigDecimal(4).add((rs.getBigDecimal(12))));
					}
					
					if(totalab.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(totalab.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(totalab)+"</td>");
					}
					
					
					early_sett = new BigDecimal(0.00);
					futu_rent = new BigDecimal(0.00);
					early_sett_next_month = new BigDecimal(0.00);
					
					
					
					// added by udara on 08-07-2013
					
					early_sett = early_sett.add(rs.getBigDecimal(16));
					early_sett_next_month = early_sett_next_month.add(rs.getBigDecimal(17));
					
					
					
					// added by udara on 29-06-2013
					if(rs.getBigDecimal(16).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(16).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(16))+"</td>");
					}
					
					
					if(rs.getBigDecimal(17).signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rs.getBigDecimal(17).negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rs.getBigDecimal(17))+"</td>");
					}
					
					
					
					// added by udara on 02-015-2013
					Other_charges_coll_from_excess = new BigDecimal(0.00);
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.add(rs.getBigDecimal(15));
					Other_charges_coll_from_excess = Other_charges_coll_from_excess.subtract(Other_charges_coll_from_excess); // added by udara on 08-07-2013
					
					
					if(Other_charges_coll_from_excess.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Other_charges_coll_from_excess.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Other_charges_coll_from_excess)+"</td>");
					}
					
					// end by udara on 02-015-2013
					
					Rent_Collec = new BigDecimal(0.00);
					//Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5).subtract(rs.getBigDecimal(11)));
					Rent_Collec = Rent_Collec.add(rs.getBigDecimal(5));
					
					
					
					if(Rent_Collec.doubleValue() > (rs.getBigDecimal(4).subtract(early_sett)).doubleValue()){
						Rent_Collec = rs.getBigDecimal(4).subtract(early_sett);
					}
					
					//Rent_Collec = Rent_Collec.add(Other_charges_coll_from_excess); // commented by udara on 02-07-2013 // added by udara on 02-05-2013
					
					tot_rent = new BigDecimal(0.00);
					//tot_rent = tot_rent.add(early_sett).add(Rent_Collec).subtract(Other_charges_coll_from_excess); // commented by udara on 03-07-2013//added by ns on 03-05-2013
					tot_rent = tot_rent.add(early_sett).add(Rent_Collec); // added by udara on 03-07-2013
					
					
					
					futu_rent = futu_rent.add(rs.getBigDecimal(13).subtract(rs.getBigDecimal(17)));  // added by udara on 03-07-2013
					
					if(Rent_Collec.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Rent_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Rent_Collec)+"</td>");
					}
					
					
					
					
					if(tot_rent.signum()<0){
						
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(tot_rent.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(tot_rent)+"</td>");
					}
					
					
					try{
						rental_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(4).signum()>0){
							rental_pre= rental_pre.add((tot_rent).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(4),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					if(rental_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(rental_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(rental_pre)+"%</td>");
					}
					
					
					
					Arrea_Collec  = new BigDecimal(0.00);
					Arrea_Collec = Arrea_Collec.add(rs.getBigDecimal(6)).add(rs.getBigDecimal(11));	
					
					if(Arrea_Collec.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrea_Collec.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrea_Collec)+"</td>");
					}
					
					
					try{
						Arrears_pre  = new BigDecimal(0.00);
						if(rs.getBigDecimal(3).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(3).add(rs.getBigDecimal(12)),BigDecimal.ROUND_HALF_EVEN));
						}else if(rs.getBigDecimal(11).signum()>0){
							Arrears_pre= Arrears_pre.add((Arrea_Collec).multiply(new BigDecimal(100)).divide(rs.getBigDecimal(12),BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						rental_pre  = new BigDecimal(0.00);
					}
					
					if(Arrears_pre.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Arrears_pre.negate())+")%</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Arrears_pre)+"%</td>");
					}
					
					closing = new BigDecimal(0.00);
					closing = closing.add(rs.getBigDecimal(10));
					Cash_Total = new BigDecimal(0.00);
					
					
					
					// added by udara on 29-07-2013
					if(futu_rent.signum()<0){
						if((futu_rent.signum()<0) && (closing.signum()>0)){
							closing = closing.add(futu_rent);
							//futu_rent = new BigDecimal(0.00);
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(0)+"</td>");
						}
						else{
							total_Future_ren_show = total_Future_ren_show.add(futu_rent);
							out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(futu_rent.negate())+")</td>");
						}
					}else{
						total_Future_ren_show = total_Future_ren_show.add(futu_rent);
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(futu_rent)+"</td>");
					}
					// end by udara on 29-07-2013
					
					
					
					if((futu_rent.signum()<= 0) && (closing.signum()>= 0)){ // added by udara 05-11-2013
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec);
					}
					else{
						Cash_Total = Cash_Total.add(closing).add(Arrea_Collec).add(Rent_Collec).add(futu_rent);
					}
					// end by udara on 29-07-2013
					
					BCF = new BigDecimal(0.00);
					
					BCF = BCF.add(totalab).subtract(Cash_Total).subtract(early_sett).subtract(early_sett_next_month); // added by udara on 08-07-2013 // commented by udara on 29-07-2013
					
					
					
					if(closing.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(closing.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(closing)+"</td>");
					}
					
					if(Cash_Total.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(Cash_Total.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(Cash_Total)+"</td>");
					}
					
					if(BCF.signum()<0){
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >("+nf.format(BCF.negate())+")</td>");
						
					}else{
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(BCF)+"</td>");
					}
					
					
					out.println("</tr>");
					
					//		closing_bal=m_total_due - rs.getDouble(8);
					total_Cases =total_Cases.add(rs.getBigDecimal(2));
					if(rs.getBigDecimal(3).signum()>=0){
						total_Arrears = total_Arrears.add(rs.getBigDecimal(3).add(rs.getBigDecimal(12)));
					}else{
						total_Arrears = total_Arrears.add(rs.getBigDecimal(12));
					}
					total_Due_Rental = total_Due_Rental.add(rs.getBigDecimal(4));
					total_AB = total_AB.add(totalab);
					
					// commented below by udara 29-06-2013
					//total_Ear_Sett = total_Ear_Sett.add(early_sett);
					//total_Ear_Sett_next_month = total_Ear_Sett_next_month.add(early_sett_next_month);
					
					// added below by udara 29-06-2013
					total_Ear_Sett = total_Ear_Sett.add(rs.getBigDecimal(16));
					total_Ear_Sett_next_month = total_Ear_Sett_next_month.add(rs.getBigDecimal(17));
					
					total_Rental_colle = total_Rental_colle.add(Rent_Collec);
					total_Other_charges_coll_from_excess = total_Other_charges_coll_from_excess.add(Other_charges_coll_from_excess); // added by udara on 02-05-2013
					total_Rent = total_Rent.add(tot_rent);
					
					try{
						
						total_Rental_Pres = new BigDecimal(0.00);
						if(total_Due_Rental.signum()>0){
							total_Rental_Pres= total_Rental_Pres.add(total_Rent.multiply(new BigDecimal(100)).divide(total_Due_Rental,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Arresrs_colle = total_Arresrs_colle.add(Arrea_Collec);
					
					try{
						total_Arrears_Pres = new BigDecimal(0.00);
						if(total_Arrears.signum()>0){
							total_Arrears_Pres=total_Arrears_Pres.add(total_Arresrs_colle.multiply(new BigDecimal(100)).divide(total_Arrears,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						
					}
					
					total_Future_ren = total_Future_ren.add(futu_rent);
					
					
					total_Closing =total_Closing.add(closing);
					total_cash = total_cash.add(Cash_Total);
					total_bcf= total_bcf.add(BCF);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Total</b></td>"); 
					if(total_Cases.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf1.format(total_Cases.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_Cases)+"</b></td>"); 
					}
					
					
					if(total_Arrears.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears)+"</b></td>"); 
					}
					
					
					if(total_Due_Rental.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Due_Rental.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Due_Rental)+"</b></td>"); 
					}
					
					
					if(total_AB.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_AB.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_AB)+"</b></td>");
					}
					
					
					if(total_Ear_Sett.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett)+"</b></td>");  
					}
					
					
					if(total_Ear_Sett_next_month.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Ear_Sett_next_month.negate())+")</b></td>");  
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Ear_Sett_next_month)+"</b></td>");  
					}
					
					
					// added by udara on 02-05-2013
					if(total_Other_charges_coll_from_excess.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Other_charges_coll_from_excess.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Other_charges_coll_from_excess)+"</b></td>"); 
					}
					// end by udara on 02-05-2013
					
					if(total_Rental_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_colle)+"</b></td>"); 
					}
					
					
					if(total_Rent.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rent.negate())+")</b></td>");
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rent)+"</b></td>");
					}
					
					
					if(total_Rental_Pres.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Rental_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Rental_Pres)+"%</b></td>"); 
					}
					
					
					if(total_Arresrs_colle.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arresrs_colle.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arresrs_colle)+"</b></td>"); 
					}
					
					
					// added by udara on 01-07-2013
					
					try{
						total_Arrears_pre  = new BigDecimal(0.00);
						if(total_Arrears.signum()>0){
							total_Arrears_pre= total_Arrears_pre.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_Arrears.add(total_AdjAmount),BigDecimal.ROUND_HALF_EVEN));
						}else if(total_Settle.signum()>0){
							total_Arrears_pre= total_Arrears_pre.add((total_Arresrs_colle).multiply(new BigDecimal(100)).divide(total_AdjAmount,BigDecimal.ROUND_HALF_EVEN));
						}
					}catch(ArithmeticException ae){
						total_Arrears_pre  = new BigDecimal(0.00);
					}
					
					// end by udara on 01-07-2013
					
					
					//if(total_Arrears_Pres.signum()<0){
					if(total_Arrears_pre.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Arrears_Pres.negate())+")%</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Arrears_Pres)+"%</b></td>"); 
					}
					
					
					
					// added by udara on 29-07-2013
					
					if(total_Future_ren_show.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Future_ren_show.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Future_ren_show)+"</b></td>"); 
					}
					// end by udara on 29-07-2013
					
					
					if(total_Closing.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_Closing.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_Closing)+"</b></td>"); 
					}
					
					
					if(total_cash.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_cash.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_cash)+"</b></td>"); 
					}
					
					
					if(total_bcf.signum()<0){
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>("+nf.format(total_bcf.negate())+")</b></td>"); 
						
					}else{
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf.format(total_bcf)+"</b></td>"); 
					}
					
					
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
				
				
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");	
				out.println("<br/>");	
				out.println("<br/>");	
				out.println("</td> "); 
				out.println("</tr>");		
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
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
				
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report</TITLE>"); 
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
				double totalab=0;
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
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>RECOVERY REPORT</u></td>"); 
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>AS AT "+m_end_date+"</u></td>"); 
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
					
				}
				
				
				
				//=================================================
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				if(more){
					out.println("<tr class=pdn_txtpos2 height=\"30\" >");
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Agreement No</td>"); 
					out.println("<td width=\"10%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Name</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Tel</td>"); 
					out.println("<td width=\"7%\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Monthly Rental</td>"); 
					out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center;}'   >Due Date</td>"); 
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
			
			//}
			
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