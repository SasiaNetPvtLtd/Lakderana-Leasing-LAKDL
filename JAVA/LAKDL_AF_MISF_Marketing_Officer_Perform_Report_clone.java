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


public class LAKDL_AF_MISF_Marketing_Officer_Perform_Report_clone extends javax.servlet.http.HttpServlet { 
	// LAKDL_AF_MISF_Marketing_Officer_Perform_Report_clone
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
		Statement stmt=null,stmt2=null,stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null;
		
		
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
				String m_user_id=req.getParameter("user_id");
				String m_finance_no = req.getParameter("finance_no");
				String m_perform_status = req.getParameter("perform_status");
				String m_collector = req.getParameter("collector");
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ARREARS_RPT(:1,:2,:3,:4,:5);END;");
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ARREARS_RPT_2(:1,:2,:3,:4,:5,:6);END;"); // commented by udara 10-01-2014
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_MASTER_ARREARS_RPT(:1,:2,:3,:4,:5,:6,:7);END;"); // added by udara 10-01-2014
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_MASTER_ARREARS_RPT_2(:1,:2,:3,:4,:5,:6,:7);END;"); 
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_user_id);
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_finance_no);
					callstmt1.setString(6,m_perform_status); // added by udara on 09-10-2013
					callstmt1.setString(7,m_collector);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				
				String m_branch_id = "";
				
				rs2= stmt2.executeQuery(" SELECT "+
						" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
						" FROM DUAL ");
					
				while(rs2.next()){
						m_branch_id = rs2.getString(1);
				}
				
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Arrears Report</TITLE>"); 
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
				
				out.println("   document.Form1.BUT_PRINT_RUN.disabled=true; "); // added by udara 17-05-2017
				
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("finance_no = document.Form1.TXT_FINANCE.value;");
				out.println("m_perform_status = document.Form1.TXT_PERFORM_STATUS.value;"); // added by udara on 09-10-2013
				out.println("collector = document.Form1.MKT_OFFICER.value;");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&perform_status=\"+m_perform_status;"); 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_new?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&perform_status=\"+m_perform_status+\"&collector=\"+collector;"); 
				//	out.println("		window.open(m_url)");
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
				out.println("		m_officer=document.Form1.TXT_USER.value;");
				out.println("		m_slba=document.Form1.TXT_SLAB.value;");//pra---
				out.println("		m_rendate=document.Form1.REN_DAY.value;"); //pra----
				out.println("		m_period=document.Form1.TXT_PERIOD.value;"); // added by udara on 12-03-2013
				out.println("		m_period_2=document.Form1.TXT_PERIOD_2.value;"); // added by udara on 21-08-2013
				
				out.println("		m_perform_status=document.Form1.TXT_PERFORM_STATUS.value;"); // added by udara 17-08-2015
				
				//out.println("alert('m_date'+m_date)")			;
				/*out.println("if(m_team_id!='' && m_sub_team_id!='' && m_user_id!='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_user&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("else if(m_team_id!='' && m_sub_team_id!='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_sub_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				out.println("else if(m_team_id!='' && m_sub_team_id=='' && m_user_id=='' ){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=print_report_team&date=\"+m_date+\"&team_id=\"+document.Form1.TEAM_HEAD.value+\"&user_id=\"+document.Form1.TXT_USER.value+\"&sub_team_id=\"+document.Form1.SUB_TEAM_HEAD.value;"); 
				out.println("	}");
				*/
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date;");	
				
				// commented by udara on 06-09-2013
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2;"); // added by udara on 12-03-2013
				
				// added by udara on 06-09-2013
				//out.println("  			var due_rent_status = document.Form1.TXT_DUE_RENT_STATUS.value;  ");
				
				// ---added by ishani 2013.09.13
				
				out.println("  			var due_month = document.Form1.TXT_MONTH.value;  ");
				out.println("  			var due_year = document.Form1.TXT_YEAR.value;  ");
				//out.println("  			alert(due_month+\"due_month\");  ");
				//out.println("  			alert(due_year+\"due_year\");  ");
				
				
				out.println("           var ins_level       = document.Form1.TXT_INS_LEVEL.value;  ");
				
				out.println("           var cr_officer       = document.Form1.MKT_OFFICER.value;  "); // added by udara 10-12-2013
				
				out.println("           m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-15(#16240)
				
				/*
				out.println("           if(due_month==\"\"&& due_year==\"\") ");
				//out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&cr_officer=\"+cr_officer;"); // m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2;");
				out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&cr_officer=\"+cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");
				out.println("           else ");
				//out.println("           	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level+\"&cr_officer=\"+cr_officer;"); // m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level;");
				out.println("           	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level+\"&cr_officer=\"+cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region;");
				*/
				
				// added by udara 17-08-2015
				out.println("           if(due_month==\"\"&& due_year==\"\") ");
				//out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&cr_officer=\"+cr_officer;"); // m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2;");
				out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&cr_officer=\"+cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;");
				out.println("           else ");
				//out.println("           	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level+\"&cr_officer=\"+cr_officer;"); // m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level;");
				out.println("           	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=print_report_new_2&location=\"+m_location+\"&officer=\"+m_officer+\"&slab=\"+m_slba+\"&rendate=\"+m_rendate+\"&date=\"+m_date+\"&period=\"+m_period+\"&period_2=\"+m_period_2+\"&due_month=\"+due_month+\"&due_year=\"+due_year+\"&ins_level=\"+ins_level+\"&cr_officer=\"+cr_officer+\"&active_status=\"+document.Form1.TXT_ACTIVE_STATUS.value+\"&region=\"+m_region+\"&perform_status=\"+m_perform_status;");
				// end by udara 17-08-2015
				
				// end by udara on 06-09-2013
				
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
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Collection Process - Arrears Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Arrears Report - \"+document.Form1.hid_status.value;"); 
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
				
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		help_update_value_assign_5(oBj);"); 
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
				
				// added by udara 10-12-2013
				out.println("function mk_officer_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				//out.println("   Sql = \"MKOfficerSqlNew\";");//MKOfficerSqlNew Modified By Sandun 25-08-2008
				//out.println("   Crit=document.Form1.MKT_OFFICER.value+\"@AF@\";");
				out.println("    Crit = document.Form1.MKT_OFFICER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_credi_officer','5');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_5(oBj) {"); 
				out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[2];"); 
				out.println("}"); 
				
				// end by udara 10-12-2013
				
				
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_clone?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
				//out.println("  			alert(\"check_Date\");  ");
				
				out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				out.println("function check_Date_2(objDD,objMM,objYY) {");
				//out.println("  			alert(\"check_Date_2\");  ");
				//out.println("  			alert( objDD.value+\" objDD.value\"+ objMM.value+\" objMM.value\"+ objYY.value+\" objYY.value\");  ");
				
				out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date_2.value=document.Form1.TXT_DAY.value+'-'+document.Form1.TXT_MONTH.value+'-'+document.Form1.TXT_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				
				out.println(" document.Form1.TXT_LOCATION_CODE.value  = '"+m_branch_id+"'; ");
				
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
				out.println("<input type=hidden name='hid_date_2' value=\"\">");
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Arrears Report </td>"); 
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
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT_RUN' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" disabled >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled ></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				// added by udara 10-12-2013
				
				out.println("<tr >"); 
				
				out.println("<td width='20%' ><DIV id='DIV_MKT_OFFICER'  class=div_input>Credit Officer *</DIV></td>"); 
				out.println("<td width='*%%' ><input class='txt_input' type='text' name='MKT_OFFICER' maxlength='50' size='10' onblur=\"mk_officer_help()\" >");  //onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\"
				out.println("<input class='but_input' type='button' name='BUT_CONDITION_OF_ASSET' value=\"Help\" onClick=\"mk_officer_help()\"></td>"); 

				out.println("</tr>");
				
				// end by udara 10-12-2013
				
				out.println("<tr >"); //Added By Sandun on 07-11-2008
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='10' style='{width=150px}' size='10' onblur='help_button_finance()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				//prabash at 09-05-2012----**
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'<DIV id='RENDATE' class=div_input>Rental Date </DIV></td>");
				out.println("<td width='*%'><input name=\"REN_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' ><DIV id='SLAB' class=div_input>SLAB </DIV></td>"); 
				out.println("<td width='*%'>");
				out.println("<select name='TXT_SLAB' class='txt_input' style='width:50px'>");
				out.println("<option value='' >All</option>");
				out.println("<option value='6' >6</option>");
				out.println("<option value='12' >12</option>");
				out.println("<option value='18' >18</option>");
				out.println("<option value='24' >24</option>");
				out.println("<option value='30' >30</option>");
				out.println("<option value='36' >36</option>");
				out.println("<option value='42' >42</option>");
				out.println("<option value='48' >48</option>");
				out.println("<option value='54' >54</option>");
				out.println("<option value='60' >60</option>");
				out.println("<option value='66' >66</option>");
				out.println("</td>"); 
				out.println("</tr>");	
				//--------------------**
				
				// added by udara on 12-03-2013
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PERIOD'  class=div_input>Period </DIV></td>"); 
				out.println("<td width='*%' > From &nbsp; <input class='txt_input' type='text' name='TXT_PERIOD' maxlength='3'   style='{width=25px}' size='4' onblur='' >"); 
				out.println(" &nbsp; To &nbsp; <input class='txt_input' type='text' name='TXT_PERIOD_2' maxlength='3' style='{width=25px}' size='4' onblur='' >"); // added by udara on 21-08-2013
				out.println("</td>");
				out.println("</tr>");
				
				// end by udara on 12-03-2013
				
				// added by udara on 06-09-2013
				
				out.println("<tr class=tr_input style='display:none'>");
				out.println("<td width='20%' ><DIV id='SLAB' class=div_input>Due Rental Status</DIV></td>"); 
				out.println("<td width='*%'>");
				out.println("<select name='TXT_DUE_RENT_STATUS' class='txt_input' style='width:50px'>");
				out.println("<option value='ALL'   >All</option>");
				out.println("<option value='PAID'  >Paid</option>");
				out.println("<option value='NOT_PAID' >Not Paid</option>");
				out.println("</td>"); 
				out.println("</tr>");	
				
				out.println("<tr class=tr_input >");
				out.println("<td width='20%' ><DIV id='SLAB' class=div_input> Arrears Month</DIV></td>"); 
				out.println("<td width='*%'><input name=\"TXT_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" style=\"display:none\" class=\"txt_input\" value='30' onchange=check_Date_2(document.Form1.TXT_DAY,document.Form1.TXT_MONTH,document.Form1.TXT_YEAR)> ");
				out.println("   MM <input name=\"TXT_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date_2(document.Form1.TXT_DAY,document.Form1.TXT_MONTH,document.Form1.TXT_YEAR)> ");
				out.println("   YYYY <input name=\"TXT_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date_2(document.Form1.TXT_DAY,document.Form1.TXT_MONTH,document.Form1.TXT_YEAR)> ");
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' ><DIV id='SLAB' class=div_input>Installment Level</DIV></td>"); 
				out.println("<td width='*%'>");
				out.println("<select name='TXT_INS_LEVEL' class='txt_input' style='width:50px'>");
				out.println("<option value='ALL'   >All</option>");
				out.println("<option value='FIRST'  >First</option>");
				//out.println("<option value='NOT_FIRST' >Not First</option>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by udara on 06-09-2013
				
				
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
				// added by udara on 09-10-2013
				
				
				// added by udara 12-05-2015
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Active/Yard Vehicles </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ACTIVE_STATUS'>"); 
				out.println("<option value='A'    > All </option>");
				out.println("<option value='Y'  > Active </option>");
				out.println("<option value='N' > Yard Vehicles </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by udara 12-05-2015
				
				
				
				stmt3 = conn.createStatement();
				
				// Added By: Samith dilshan  On : 2015-06-15
				out.println("<tr style=\"display: none;\" >");   
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
				out.println("<TITLE>Collection - Arrears Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println(m_string);
				out.println("</html>");
				
			}

			
			// =============== end by udara on 06-09-2013 ====================================================================================
			
			
			else if(m_chksql.equals("print_report_new")){		
				String m_period = "";
				String m_period_2 = ""; // added by udara on 21-08-2013
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slba=""; //added by Prabash on 09-05-2012
				String m_rendate=""; //added by Prabash on 09-05-2012
				String  m_active_status = "";
				String  m_active_status_string = "";
				
				// added by udara 17-08-2015
				String m_perform_status = "";
				if(req.getParameter("perform_status") != null ){
					m_perform_status = req.getParameter("perform_status").trim();
				}
				// end by udara 17-08-2015
				
				
				String m_region = "";
				
				// Added By Samith Dilshan on 2015-06-15
				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				
				if(req.getParameter("active_status")!=null ){
					m_active_status=req.getParameter("active_status").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(!m_active_status.equals("A")){
					//m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(FINANCE_NO) = '"+m_active_status+"' "; // commented by udara 23-03-2015
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_active_status+"' "; // added by udara 23-03-2015
				}
				String cr_officer = "";
				
				
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				//Added by Prabash on 09-05-2012---**
				if(req.getParameter("slab")!=null ){
					m_slba=req.getParameter("slab").trim();
				}
				
				
				if(req.getParameter("rendate")!=null ){
					m_rendate=req.getParameter("rendate").trim();
				}
				//---------------------------------**
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				
				// added by udara on 12-03-2013
				if(req.getParameter("period")!=null ){
					m_period=req.getParameter("period").trim();
				}
				
				// added by udara on 21-08-2013
				if(req.getParameter("period_2")!=null ){
					m_period_2=req.getParameter("period_2").trim();
				}
				
				// added by udara 10-12-2013
				if(req.getParameter("cr_officer")!=null ){
					cr_officer=req.getParameter("cr_officer").trim();
				}
				
				
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Arrears Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_new?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_new?chksql=print_report_new&location="+m_location+"&slba="+m_slba+"&rendate="+m_rendate+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				
				if(m_slba.equals("")){
					
					
					
					//if(m_period.equals("")){	// commented by udara on 21-08-2013
					if((m_period.equals("")) && (m_period_2.equals(""))){	// added by udara on 21-08-2013
						
						//out.println(" aaaaa 1 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							//" AND AGE_NEW LIKE '"+m_period+"%'   "+ // added by udara on 12-03-2013
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ // 20 A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    ";    
					}
					
					
					// added by udara on 21-08-2013	
					else if((!m_period.equals("")) && (!m_period_2.equals(""))){	
						
						out.println(" aaaaa 2 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+ 
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							" AND ROUND(AGE_NEW) >= '"+m_period+"'   "+ // added by udara on 12-03-2013
							" AND ROUND(AGE_NEW) <= '"+m_period_2+"'   "+
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT), "+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ //20 A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					
					else if((!m_period.equals("")) && (m_period_2.equals(""))){	
						
						out.println(" aaaaa 3 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT), "+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ //20 A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					else if((m_period.equals("")) && (!m_period_2.equals(""))){	
						
						out.println(" aaaaa 4 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							" AND ROUND(AGE_NEW) = '"+m_period_2+"'   "+ // added by udara on 12-03-2013
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT), "+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ //20 A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					// end by udara on 21-08-2013	
					
					// commented by udara on 21-08-2013
					/*
					else {
						
							Sql_data="  WITH T AS(SELECT  "+
				" FINANCE_NO, "+ //1
				" CLIENT_CODE, "+ //2
				" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
				" VALUE_DATE ,"+ //4
				" AGE_NEW, "+ //5
				" CLIENT_TEL_NO ,"+ //6
				" DUE_RENTAL_AMOUNT ,"+ //7
				" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
				" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
				" NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
				" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
				" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
				" TOTAL_AMOUNT "+//13
				" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
				" WHERE ENT_USER='"+m_username+"' "+
				" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
				" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
				// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
				" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
				" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
				" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
			//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
			
				" ) "+
					" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
					" AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT) "+
				" FROM T "+
				" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
				"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
						
					" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    ";    
					
					}
					*/
					
					
				}
				else{
					
					
					//if(m_period.equals("")){	// commented by udara on 21-08-2013 
					if((m_period.equals("")) && (m_period_2.equals(""))){ // added by udara on 21-08-2013
						
						out.println(" aaaaa 5 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							//" AND AGE_NEW LIKE '"+m_period+"%'   "+ // added by udara on 12-03-2013
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT), "+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ //20 A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
					}
					
					
					// added by udara on 21-08-2013
					
					else if((!m_period.equals("")) && (!m_period_2.equals(""))){ // added by udara on 21-08-2013
						
						out.println(" aaaaa 6 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+ 
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							" AND ROUND(AGE_NEW) >= '"+m_period+"'   "+ // added by udara on 12-03-2013
							" AND ROUND(AGE_NEW) <= '"+m_period_2+"'   "+
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT), "+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ //20  A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					else if((!m_period.equals("")) && (m_period_2.equals(""))){	
						
						out.println(" aaaaa 7 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT), "+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ //20 A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					else if((m_period.equals("")) && (!m_period_2.equals(""))){	
						
						out.println(" aaaaa 8 "); // udara 15-07-2014
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							m_active_status_string+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";

						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}
						
							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							" AND ROUND(AGE_NEW) = '"+m_period_2+"'   "+ // added by udara on 12-03-2013
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT), "+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // 17 added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							", "+m_schema_name+".AF_CO_GET_ARR_RPT_REMARK(FINANCE_NO,CLIENT_CODE) "+ // added by udara 18-02-2014
							" ,"+m_schema_name+".AF_CO_GET_LAST_PAY_AMT_2(FINANCE_NO) "+ //20 A S Silva 04-06-2015
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					// end by udara on 21-08-2013
					
					
					// commented by udara on 21-08-2013
					/*
					else {
						
								Sql_data="  WITH T AS(SELECT  "+
				" FINANCE_NO, "+ //1
				" CLIENT_CODE, "+ //2
				" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
				" VALUE_DATE ,"+ //4
				" AGE_NEW, "+ //5
				" CLIENT_TEL_NO ,"+ //6
				" DUE_RENTAL_AMOUNT ,"+ //7
				" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
				" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
				" NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
				" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
				" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
				" TOTAL_AMOUNT "+ //13
				" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
				" WHERE ENT_USER='"+m_username+"' "+
				" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
				" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
				" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
				" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
				" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
				" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
			//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
			
				" ) "+
				
				" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
					" AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT) "+
				" FROM T "+
				" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
				"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
						
					" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
					
					
					}
					*/
					
					
				}
				
				
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				
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
				double total_bal=0;
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Arrears Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
				out.println("<td width=\"150\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Arrears Report for the</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				
				//End by Dineth on 2008-11-17
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
				
				// added by udara 17-08-2015
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
				// end by udara 17-08-2015
				
				// Added By Samith Dulshan
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<tr>");
						
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Region</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+rs3.getString(1)+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>");  
						out.println("</tr>");
					}
				}

				
				
				out.println("</table>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"2%\"  align='center' >A</td>"); 			
				out.println("<td width=\"7%\"  align='center' >B</td>"); 
				out.println("<td width=\"12%\" align='center'  >C</td>"); 
				out.println("<td width=\"7%\"  align='center' >D</td>"); 
				out.println("<td width=\"7%\"  align='center' >E</td>"); 
				out.println("<td width=\"7%\"  align='center' >F</td>"); 
				out.println("<td width=\"5%\"  align='center' >G</td>"); 
				out.println("<td width=\"7%\"  align='center' >H</td>"); 
				out.println("<td width=\"7%\"  align='center' >I</td>"); 
				out.println("<td width=\"7%\"  align='center' >J</td>"); 
				out.println("<td width=\"7%\"  align='center' >K</td>"); 
				out.println("<td width=\"7%\"  align='center' >L</td>"); 
				out.println("<td width=\"5%\"  align='center' >M</td>"); 
				out.println("<td width=\"7%\"  align='center' >N</td>"); 
				out.println("<td width=\"7%\"  align='center' >0</td>");
				out.println("<td width=\"7%\"  align='center' >P</td>");
				out.println("<td width=\"7%\"  align='center' >Q</td>"); // added by udara on 03-10-2013
				out.println("<td width=\"7%\"  align='center' >R</td>"); // added by udara on 26-12-2013
				out.println("<td width=\"7%\"  align='center' >S</td>");
				out.println("</tr >");
				
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class=factoring-letter-body ><b>No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Agreement No  ' onclick=sort_data('FINANCE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Agreement No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Vehicle No  '   onclick=sort_data('VEHICLE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Vehicle No</b></td>"); //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Client  '       onclick=sort_data('FULL_NAME') STYLE='{text-align:center; cursor:hand; }'       ><b>Client</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Tel  '          onclick=sort_data('CLIENT_TEL_NO') STYLE='{text-align:center; cursor:hand; }'   ><b>Tel</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>SLAB</b></td>");//ADDED BY Prabash on 09-05-2012
				out.println("<td class=factoring-letter-body title='Click here to sort by - Rental Date  '  onclick=sort_data('VALUE_DATE') STYLE='{text-align:center; cursor:hand; }'      ><b><p>Rental<br>Date</p></b></td>");// added by prabash on  09-05-2012-----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Monthly Rental  ' onclick=sort_data('RENTAL_AMOUNT') STYLE='{text-align:center; cursor:hand; }' ><b><p>Rental</p></b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Rental</p></b></td>");  // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Status</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Arrears</p></b></td>");       // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Arrears</p></b></td>"); // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Period</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Remarks</b></td>");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Follow Up</b></td>"); 																																															
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b> Credit Officer </b></td>");//ADDED BY LALANKA ON 15-07-2009  Prev Collection Oficer
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Current Collection Oficer</b></td>"); // added by udara on 03-10-2013
				out.println("<td class=factoring-letter-body ><b>Last Payment Date</b></td>"); // added by udara 26-12-2013
				out.println("<td class=factoring-letter-body ><b>Last Paid Amount</b></td>"); //  added by A S Silva 03/06/2015
				out.println("</tr >");
				
				
				
				
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0,sub_close=0,sub_open=0;
				
				
				
				double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				double  m_total_rental=0,m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				
				String row_colour = ""; 
				double period_val = 0;
				double paid_amount =0;
				String paid_amount1;
				
				while(more){
					
					period_val = Math.round(rs.getDouble(5));
					
					if((period_val>=3) && (period_val<6))
						row_colour = "CCFFFF"; // lightblue
					else if(period_val>=6)
						row_colour = "FFCCFF"; //"FF6699" // red
					else
						row_colour = "FFFFFF";
					
					if(rs.getString(3)!=null && rs.getString(4)!=null && rs.getString(5)!=null && rs.getString(6)!=null && rs.getString(7)!=null && rs.getString(8)!=null&& rs.getString(9)!=null&& rs.getString(10)!=null&& rs.getString(11)!=null&& rs.getString(12)!=null&& rs.getString(13)!=null){
						m_total_rental =rs.getDouble(14); 
						out.println("<TR >");	
						
						
						
						out.println("<tr  id=tr_id"+j+">"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' >"+j+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_transaction_history_new('"+rs.getString(3)+"','"+rs.getString(2)+"')\" STYLE='{text-align:left;cursor:hand; }'   ><u>"+rs.getString(2)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(11)+"</td>"); //thamali 2012.03.29
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_client('"+rs.getString(3)+"')\"  class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString(4)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:left;cursor:hand}'  >"+rs.getString(6)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(12)+"</td>");//Added By Prabash on 09-08-2012
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(1)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:right;}'   >"+nf1.format(rs.getDouble(7))+"</td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > &nbsp;</td>");
						
						// commented by udara 23-12-2013
						/*
						// thamali 2012.03.29
						if(Math.round(rs.getDouble(5)) >= 0 && Math.round(rs.getDouble(5)) < 1){
							out.println("<td  class=factoring-letter-body    >-</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 1 && Math.round(rs.getDouble(5)) < 2 ){
							out.println("<td  class=factoring-letter-body    >NR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 2 && Math.round(rs.getDouble(5)) < 3 ){
							out.println("<td  class=factoring-letter-body    >RR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 3 && Math.round(rs.getDouble(5)) < 4 ){
							out.println("<td  class=factoring-letter-body    >CV</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 4 ){
							out.println("<td  class=factoring-letter-body   >Z</td>"); 
						}
						*/
						
						// added by udara 23-12-2013
						if(rs.getString(17).equals("REPOSSESS")){
							out.println("<td  class=factoring-letter-body  > Seized </td>");
						}
						else{
							if(Math.round(rs.getDouble(5)) >= 0 && Math.round(rs.getDouble(5)) < 1){
							out.println("<td  class=factoring-letter-body    >-</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 1 && Math.round(rs.getDouble(5)) < 2 ){
								out.println("<td  class=factoring-letter-body    >NR</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 2 && Math.round(rs.getDouble(5)) < 3 ){
								out.println("<td  class=factoring-letter-body    >RR</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 3 && Math.round(rs.getDouble(5)) < 4 ){
								out.println("<td  class=factoring-letter-body    >CV</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 4 ){
								out.println("<td  class=factoring-letter-body   >Z</td>"); 
							}
						}
						// end by udara 23-12-2013
						
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(13))+"</td>");			
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  > &nbsp;</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand;text-align:right; >"+Math.round(rs.getDouble(5))+"</td>");
						//out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"add_client_comments('"+rs.getString(3)+"','"+rs.getString(2)+"')\"><u>Remarks</u></td>"); // commented by udara 18-02-2014
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"add_client_comments('"+rs.getString(3)+"','"+rs.getString(2)+"')\"><u> "+rs.getString(19)+" </u></td>"); // added by udara on 18-02-2014
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"show_followup('"+rs.getString(2)+"')\"><u>"+rs.getString(8)+"</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(10)+"</td>");//Added By Lalanka on 15-07-2009
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand > "+rs.getString(16)+" </td>"); // added by udara on 03-10-2013
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  > "+rs.getString(18)+" </td>"); // added by udara 26-12-2013
						if(rs.getString(20)!=null){
					       paid_amount =rs.getDouble(20);
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  > "+nf1.format(paid_amount)+" </td>"); // added by udara 26-12-2013	
					       }
					       else
					       {
						paid_amount1 ="-";
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  > "+paid_amount1+" </td>"); // added by udara 26-12-2013
					     }
						out.println("</tr>");
						total_mon_rental=total_mon_rental+rs.getDouble(7);
						total_open_bal=total_open_bal+rs.getDouble(13);
						j+=1;
					}
					else if( rs.getString(2)==null && rs.getString(1).equals("SUB TOTAL")){
						out.println("<tr>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><B>Total Rental</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(14))+" </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > <B>Total Arrears</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(15))+" </td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  // added by udara on 03-10-2013
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>");  // added by udara on 26-12-2013
						out.println("</tr>");
						
					}
					
					
					more=rs.next();
					count+=1;
					
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Grant Total</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_mon_rental)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_open_bal)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara on 03-10-2013
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); // added by udara 26-12-2013
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); // added by A S Silva 05-06-2015
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
					
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			
			
			else if(m_chksql.equals("print_report_new_2")){		
				String m_period = "";
				String m_period_2 = ""; // added by udara on 21-08-2013
				String m_date="";
				String m_location="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_cur_date="";
				String m_slba=""; //added by Prabash on 09-05-2012
				String m_rendate=""; //added by Prabash on 09-05-2012
				
				String m_due_rent_status = ""; // added by udara on 06-09-2013
				String m_due_month = ""; // added by ishani 2013.09.13
				String m_due_year_year = ""; // added by ishani 2013.09.13
				String m_ins_level = ""; // added by udara on 06-09-2013
				
				
				String m_region = "";
				
				// Added By Samith Dilshan on 2015-06-15
				if(req.getParameter("region") != null ){
					m_region = req.getParameter("region").trim();
				}
				
				
				// added by udara 10-12-2013
				String cr_officer = "";
				
				if(req.getParameter("cr_officer")!=null ){
					cr_officer=req.getParameter("cr_officer").trim();
				}
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}
				
				//Added by Prabash on 09-05-2012---**
				if(req.getParameter("slab")!=null ){
					m_slba=req.getParameter("slab").trim();
				}
				
				
				if(req.getParameter("rendate")!=null ){
					m_rendate=req.getParameter("rendate").trim();
				}
				//---------------------------------**
				if(req.getParameter("officer")!=null ){
					m_officer=req.getParameter("officer").trim();
				}
				
				
				// added by udara on 12-03-2013
				if(req.getParameter("period")!=null ){
					m_period=req.getParameter("period").trim();
				}
				
				// added by udara on 21-08-2013
				if(req.getParameter("period_2")!=null ){
					m_period_2=req.getParameter("period_2").trim();
				}
				
				// added by udara on 06-09-2013
				
				String due_rent_condition  = "";
				String ins_level_condition = "";
				
				if(req.getParameter("due_month")!=null ){
					m_due_month=req.getParameter("due_month").trim();
				}
				
				if(req.getParameter("due_year")!=null ){
					m_due_year_year=req.getParameter("due_year").trim();
				}
				
				if(req.getParameter("ins_level")!=null ){
					m_ins_level=req.getParameter("ins_level").trim();
				}
				
				String m_areas_month_end = "";
				String m_areas_month_start="";
				
				
				int m_due_month_val=Integer.parseInt(m_due_month);
				int m_due_year_val=Integer.parseInt(m_due_year_year);
				int m_due_year=Integer.parseInt(m_due_year_year);
				
				
				
				
				
				/*int m_due_month_s=0;
				int m_due_month_e=0;
				// int m_due_year_val=0;
				int m_due_year_s=0;
				int m_due_year_e=0;
				
				m_due_month_s= m_due_month_val-1;
				
				
				if(m_due_month_val==1){
					m_due_month_s= 12;
					m_due_month_e= 2;
					m_due_year_s=m_due_year_val-1;
				}
				else if(m_due_month_val==12){
					m_due_month_s= 11;
					m_due_month_e= 1;
					m_due_year_e=m_due_year_val+1;
				}
				else{
					m_due_month_s= m_due_month_val-1;
					m_due_month_e= m_due_month_val+1;
					m_due_year=m_due_year_val;
					
				}
				
				if(m_due_month_s==2){	
					m_areas_month_start= "28-"+m_due_month_s+"-"+m_due_year+" ";
					
				}
				else if( m_due_month_s==12 ){	
					m_areas_month_start= "31-"+m_due_month_s+"-"+m_due_year_s+" ";
				} 
				else if(m_due_month_s==1 || m_due_month_s==3 || m_due_month_s==5 || m_due_month_s==7 || m_due_month_s==8 || m_due_month_s==10  ){	
					m_areas_month_start= "31-"+m_due_month_s+"-"+m_due_year+" ";
				}
				else
				{
					m_areas_month_start= "30-"+m_due_month_s+"-"+m_due_year+" ";
				}
				
				
				if(m_due_month_e==1){	
					m_areas_month_end= "01-"+m_due_month_e+"-"+m_due_year_e+" ";
				}
				else{
					m_areas_month_end= "01-"+m_due_month_e+"-"+m_due_year+" ";
				}
				*/
				//out.println(m_areas_month_start+"m_areas_month_start");
				//out.println(m_areas_month_end+"m_areas_month_end");
				
				
				//String m_areas_month= "30-"+m_due_month+"-"+m_due_year+" ";
				
				
				
				if(m_due_rent_status.equals("PAID"))
					due_rent_condition = "AND DUE_RENTAL_AMOUNT = 0 ";
				else if(m_due_rent_status.equals("NOT_PAID"))
					due_rent_condition = "AND DUE_RENTAL_AMOUNT > 0 ";
				
				/*
				if(m_ins_level.equals("FIRST"))
					ins_level_condition = "AND TOTAL_AMOUNT = DUE_RENTAL_AMOUNT ";
				else if(m_due_rent_status.equals("NOT_FIRST"))
					ins_level_condition = "AND TOTAL_AMOUNT <> DUE_RENTAL_AMOUNT ";
				*/
				
				// end by udara on 06-09-2013
				
				// added by udara on 16-09-2013
				if(m_ins_level.equals("FIRST"))
					ins_level_condition = " AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK_2(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0 "; // ins_level_condition = "AND ROUND(AGE_NEW) = 1 ";
				else if(m_due_rent_status.equals("NOT_FIRST"))
					ins_level_condition = " ";
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Arrears Report</TITLE>"); 
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
				// out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_new?chksql=print_report_new&location="+m_location+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Perform_Report_new?chksql=print_report_new&location="+m_location+"&slba="+m_slba+"&rendate="+m_rendate+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
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
				
				//out.println(m_areas_month);
				if(m_slba.equals("")){
					
					//if(m_period.equals("")){	// commented by udara on 21-08-2013
					if((m_period.equals("")) && (m_period_2.equals(""))){	// added by udara on 21-08-2013
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							//due_rent_condition + // added by udara on 06-09-2013 //commented by ishani 2013.09.13
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//" AND AGE_NEW LIKE '"+m_period+"%'   "+ // added by udara on 12-03-2013
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    ";    
					}
					
					
					// added by udara on 21-08-2013	
					else if((!m_period.equals("")) && (!m_period_2.equals(""))){	
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							" AND ROUND(AGE_NEW) >= '"+m_period+"'   "+ // added by udara on 12-03-2013
							" AND ROUND(AGE_NEW) <= '"+m_period_2+"'   "+
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							
							//due_rent_condition + // added by udara on 06-09-2013 // commented by ishani 2013.09.13
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					
					else if((!m_period.equals("")) && (m_period_2.equals(""))){	
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
							//" AND "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month+"','"+m_username+"') > 0 "+ //added by ishani 2013.09.13
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							
							//due_rent_condition + // added by udara on 06-09-2013 // commented by ishani 2013.09.132
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					else if((m_period.equals("")) && (!m_period_2.equals(""))){	
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
							" TOTAL_AMOUNT "+//13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
							" AND ROUND(AGE_NEW) = '"+m_period_2+"'   "+ // added by udara on 12-03-2013
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							
							//due_rent_condition + // added by udara on 06-09-2013 //commented by ishani 2013.09.13
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
							
							" ) "+
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					// end by udara on 21-08-2013	
					
					// commented by udara on 21-08-2013
					/*
					else {
						
							Sql_data="  WITH T AS(SELECT  "+
				" FINANCE_NO, "+ //1
				" CLIENT_CODE, "+ //2
				" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
				" VALUE_DATE ,"+ //4
				" AGE_NEW, "+ //5
				" CLIENT_TEL_NO ,"+ //6
				" DUE_RENTAL_AMOUNT ,"+ //7
				" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
				" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
				" NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
				" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
				" NVL(TOTAL_PERIOD,0)TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012
				" TOTAL_AMOUNT "+//13
				" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
				" WHERE ENT_USER='"+m_username+"' "+
				" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
				" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
				// " AND TOTAL_PERIOD      		like UPPER('%"+m_slba+"%') "+ //added by prabah on 09-05-2012
				" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
				" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012 --Support #5368
				" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
			//	" ORDER BY VALUE_DATE  "+m_order_by_type+" ";	 
			
				" ) "+
					" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
					" AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT) "+
				" FROM T "+
				" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
				"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
						
					" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    ";    
					
					}
					*/
					
					
				}
				else{
					
					
					//if(m_period.equals("")){	// commented by udara on 21-08-2013 
					if((m_period.equals("")) && (m_period_2.equals(""))){ // added by udara on 21-08-2013
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							
							//due_rent_condition + // added by udara on 06-09-2013
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//" AND AGE_NEW LIKE '"+m_period+"%'   "+ // added by udara on 12-03-2013
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
					}
					
					
					// added by udara on 21-08-2013
					
					else if((!m_period.equals("")) && (!m_period_2.equals(""))){ // added by udara on 21-08-2013
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							
							" AND ROUND(AGE_NEW) >= '"+m_period+"'   "+ // added by udara on 12-03-2013
							" AND ROUND(AGE_NEW) <= '"+m_period_2+"'   "+
							//due_rent_condition + // added by udara on 06-09-2013
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					else if((!m_period.equals("")) && (m_period_2.equals(""))){	
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							
							" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
							//due_rent_condition + // added by udara on 06-09-2013
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					else if((m_period.equals("")) && (!m_period_2.equals(""))){	
						
						Sql_data="  WITH T AS(SELECT  "+
							" FINANCE_NO, "+ //1
							" CLIENT_CODE, "+ //2
							" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
							" VALUE_DATE ,"+ //4
							" AGE_NEW, "+ //5
							" CLIENT_TEL_NO ,"+ //6
							" DUE_RENTAL_AMOUNT ,"+ //7
							" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
							" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
							" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(application_no),'-')COLL_OFICER, "+ // " NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
							" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
							" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
							" TOTAL_AMOUNT "+ //13
							" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
							" WHERE ENT_USER='"+m_username+"' "+
							" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
							" AND UPPER(COLLECTER_ID) like UPPER('%"+cr_officer+"%') "+ //ADDED MILINDA FOR ##18376 2015-10-19
							" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') ";
						
						
						if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-15 for Region Code
							Sql_data = Sql_data +" AND REGION_CODE = '"+m_region+"' ";    
						}

							Sql_data = Sql_data + " AND UPPER(NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(application_no),' ')) LIKE UPPER('%"+cr_officer+"%') "+ // added by udara 10-12-2013
							
							" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
							//" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
							" AND NVL(VALUE_DATE,' ')      			like UPPER('%"+m_rendate+"%') "+
							" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
							" AND "+m_schema_name+".AF_ARRS_RPT_MONTH_CHECK(FINANCE_NO,'"+m_due_month_val+"','"+m_due_year_val+"') > 0  "+
							//" AND ("+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_end+"','"+m_username+"') - "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS(FINANCE_NO,CLIENT_CODE,'"+m_areas_month_start+"','"+m_username+"')) > 0 "+ //added by ishani 2013.09.13
							
							" AND ROUND(AGE_NEW) = '"+m_period_2+"'   "+ // added by udara on 12-03-2013
							//due_rent_condition + // added by udara on 06-09-2013
							ins_level_condition + // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','DUE_RENTAL') = '"+m_due_rent_status+"' "+ // added by udara on 06-09-2013
							//" AND "+m_schema_name+".AF_CO_GET_DETAILS_ARREARS_RPT(FINANCE_NO,'"+m_date+"','INSTALLMENT_CHECK') = '"+m_ins_level+"' "+ // added by udara on 06-09-2013
							//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
							
							" ) "+
							
							" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
							" AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT),"+m_schema_name+".AF_GET_COLL_OFFICER_2(FINANCE_NO) "+ // mod by udara 03-10-2013
							" ,"+m_schema_name+".AF_GET_FIN_STATUS(FINANCE_NO) "+ // added by udara 23-12-2013
							" ,NVL("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_2(FINANCE_NO,'"+m_date+"'),'-') "+ // added by udara 26-12-2013
							" FROM T "+
							" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
							
							" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
							" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
							" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
						
					}
					
					// end by udara on 21-08-2013
					
					
					// commented by udara on 21-08-2013
					/*
					else {
						
								Sql_data="  WITH T AS(SELECT  "+
				" FINANCE_NO, "+ //1
				" CLIENT_CODE, "+ //2
				" UPPER(CLIENT_FULL_NAME) FULL_NAME, "+ //3
				" VALUE_DATE ,"+ //4
				" AGE_NEW, "+ //5
				" CLIENT_TEL_NO ,"+ //6
				" DUE_RENTAL_AMOUNT ,"+ //7
				" NVL("+m_schema_name+".AF_CO_GET_FOLLOWUP_REMARK(application_no,'AF','RECOVERY'),'Enter Follow up' )FOL_REMARK,"+ //8
				" NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(CLIENT_CODE),'-')  CITY_NAME, "+//9 Added By Lalanka on 22-06-2009
				" NVL("+m_schema_name+".AF_CO_GET_PRV_COLL_OFICER(application_no),'-')COLL_OFICER, "+//10 Added By Lalanka on 15-07-2009
				" NVL("+m_schema_name+".af_co_get_all_reg_numbers(FINANCE_NO),'-') VEHICLE_NO, "+ //11 THAMALI 2012.03.29
				" NVL(TOTAL_PERIOD,0) TOTAL_PERIOD, "+ //12 //Prabash on 09-05-2012 
				" TOTAL_AMOUNT "+ //13
				" FROM "+m_schema_name+".AF_MASTER_ARREARS_RPT "+
				" WHERE ENT_USER='"+m_username+"' "+
				" AND UPPER(collection_officer) like UPPER('%"+m_officer+"%') "+
				" AND UPPER(LOCATION_CODE)      like UPPER('%"+m_location+"%') "+
				" AND TOTAL_PERIOD      		= UPPER('"+m_slba+"') "+ //added by prabah on 09-05-2012
				" AND VALUE_DATE      			like UPPER('%"+m_rendate+"%') "+ //added by prabah on 09-05-2012
				" AND TOTAL_AMOUNT > 0 "+  //added by prabah on 21-06-2012-Support #5368
				" AND ROUND(AGE_NEW) = '"+m_period+"'   "+ // added by udara on 12-03-2013
			//	" ORDER BY  VALUE_DATE  "+m_order_by_type+" ";	
			
				" ) "+
				
				" SELECT   DECODE (GROUPING (FINANCE_NO), 0, VALUE_DATE, 'SUB TOTAL'), FINANCE_NO,CLIENT_CODE, FULL_NAME, "+
					" AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT, SUM (DUE_RENTAL_AMOUNT),SUM (TOTAL_AMOUNT) "+
				" FROM T "+
				" GROUP BY ROLLUP (VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
				"  CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT)"+
						
					" ORDER BY VALUE_DATE,FINANCE_NO,  CLIENT_CODE, FULL_NAME, AGE_NEW, "+
					" CLIENT_TEL_NO, DUE_RENTAL_AMOUNT, FOL_REMARK, CITY_NAME, COLL_OFICER, "+
					" VEHICLE_NO, TOTAL_PERIOD, TOTAL_AMOUNT    "; 
					
					
					}
					*/
					
					
				}
				
				
				
				
				
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				
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
				double total_bal=0;
				
				stmt3 = conn.createStatement();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Arrears Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				//Added by Dineth on 2008-11-17
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Report Generated Date</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_cur_date+"</td>"); 
				out.println("<td width=\"150\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Arrears Report for the</td>"); 
				out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+m_date+"</td>"); 
				out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>"); 
				out.println("</tr>");
				
				
				//End by Dineth on 2008-11-17
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
				
				// Added By Samith Dulshan
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<tr>");
						
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Region</td>"); 
						out.println("<td width=\"150\" STYLE='{font: bold 8pt arial; text-align:left;}'   >"+rs3.getString(1)+"</td>"); 
						out.println("<td width=\"*\"   STYLE='{font: bold 8pt arial; text-align:left;}'   >&nbsp;</td>");  
						out.println("</tr>");
					}
				}
				
				out.println("</table>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<tr id=tr_id_header class=factoring-letter-body bgcolor=\"#C0C0C0\"  >");
				out.println("<td width=\"2%\"  align='center' >A</td>"); 			
				out.println("<td width=\"7%\"  align='center' >B</td>"); 
				out.println("<td width=\"12%\" align='center'  >C</td>"); 
				out.println("<td width=\"7%\"  align='center' >D</td>"); 
				out.println("<td width=\"7%\"  align='center' >E</td>"); 
				out.println("<td width=\"7%\"  align='center' >F</td>"); 
				out.println("<td width=\"5%\"  align='center' >G</td>"); 
				out.println("<td width=\"7%\"  align='center' >H</td>"); 
				out.println("<td width=\"7%\"  align='center' >I</td>"); 
				out.println("<td width=\"7%\"  align='center' >J</td>"); 
				out.println("<td width=\"7%\"  align='center' >K</td>"); 
				out.println("<td width=\"7%\"  align='center' >L</td>"); 
				out.println("<td width=\"5%\"  align='center' >M</td>"); 
				out.println("<td width=\"7%\"  align='center' >N</td>"); 
				out.println("<td width=\"7%\"  align='center' >0</td>");
				out.println("<td width=\"7%\"  align='center' >P</td>");
				out.println("<td width=\"7%\"  align='center' >Q</td>"); // added by udara on 26-12-2013
				
				out.println("</tr >");
				
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td class=factoring-letter-body ><b>No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Agreement No  ' onclick=sort_data('FINANCE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Agreement No</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Vehicle No  '   onclick=sort_data('VEHICLE_NO') STYLE='{text-align:center; cursor:hand; }'      ><b>Vehicle No</b></td>"); //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Client  '       onclick=sort_data('FULL_NAME') STYLE='{text-align:center; cursor:hand; }'       ><b>Client</b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Tel  '          onclick=sort_data('CLIENT_TEL_NO') STYLE='{text-align:center; cursor:hand; }'   ><b>Tel</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>SLAB</b></td>");//ADDED BY Prabash on 09-05-2012
				out.println("<td class=factoring-letter-body title='Click here to sort by - Rental Date  '  onclick=sort_data('VALUE_DATE') STYLE='{text-align:center; cursor:hand; }'      ><b><p>Rental<br>Date</p></b></td>");// added by prabash on  09-05-2012-----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Monthly Rental  ' onclick=sort_data('RENTAL_AMOUNT') STYLE='{text-align:center; cursor:hand; }' ><b><p>Rental</p></b></td>"); 
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Rental</p></b></td>");  // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Status</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Arrears</p></b></td>");       // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Opening Age  '    onclick=sort_data('Total_Rental') STYLE='{text-align:center; cursor:hand; }'  ><b><p>Total Arrears</p></b></td>"); // added by Prabash on 22-06-2012----*
				out.println("<td class=factoring-letter-body title='Click here to sort by - Status  '    	  onclick=sort_data('AGE_OLD') STYLE='{text-align:center; cursor:hand; }'       ><b><p>Period</p></b></td>");  //thamali 2012.03.29
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Remarks</b></td>");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }'><b>Follow Up</b></td>"); 																																															
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; cursor:hand; }' ><b>Credit Officer </b></td>");//ADDED BY LALANKA ON 15-07-2009 Prev Collection Oficer
				out.println("<td class=factoring-letter-body ><b> Last Payment Date</b></td>"); // added by udara 26-12-2013
				out.println("</tr >");
				
				
				
				
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0,sub_close=0,sub_open=0;
				
				
				
				double  m_total_due_0=0,m_total_due_1=0,m_total_due_2=0,m_total_due_3=0,m_total_due_4=0,m_total_due_5=0,m_total_due_6=0;
				double  m_total_rental=0,m_total_coll_0=0,m_total_coll_1=0,m_total_coll_2=0,m_total_coll_3=0,m_total_coll_4=0,m_total_coll_5=0,m_total_coll_6=0;
				
				String row_colour = ""; 
				double period_val = 0;
				
				while(more){
					
					period_val = Math.round(rs.getDouble(5));
					
					if((period_val>=3) && (period_val<6))
						row_colour = "CCFFFF"; // lightblue
					else if(period_val>=6)
						row_colour = "FFCCFF"; //"FF6699" // red
					else
						row_colour = "FFFFFF";
					
					
					if(rs.getString(3)!=null && rs.getString(4)!=null && rs.getString(5)!=null && rs.getString(6)!=null && rs.getString(7)!=null && rs.getString(8)!=null&& rs.getString(9)!=null&& rs.getString(10)!=null&& rs.getString(11)!=null&& rs.getString(12)!=null&& rs.getString(13)!=null){
						m_total_rental =rs.getDouble(14); 
						out.println("<TR >");	
						
						
						
						out.println("<tr  id=tr_id"+j+">"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' >"+j+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_transaction_history_new('"+rs.getString(3)+"','"+rs.getString(2)+"')\" STYLE='{text-align:left;cursor:hand; }'   ><u>"+rs.getString(2)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(11)+"</td>"); //thamali 2012.03.29
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' onClick=\"show_client('"+rs.getString(3)+"')\"  class=factoring-letter-body STYLE='{text-align:left;cursor:hand; }' ><u>"+rs.getString(4)+"</u></td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:left;cursor:hand}'  >"+rs.getString(6)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(12)+"</td>");//Added By Prabash on 09-08-2012
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:center;}'   >"+rs.getString(1)+"</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  STYLE='{text-align:right;}'   >"+nf1.format(rs.getDouble(7))+"</td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > &nbsp;</td>");
						
						// commented by udara 23-12-2013
						/*
						// thamali 2012.03.29
						if(Math.round(rs.getDouble(5)) >= 0 && Math.round(rs.getDouble(5)) < 1){
							out.println("<td  class=factoring-letter-body    >-</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 1 && Math.round(rs.getDouble(5)) < 2 ){
							out.println("<td  class=factoring-letter-body    >NR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 2 && Math.round(rs.getDouble(5)) < 3 ){
							out.println("<td  class=factoring-letter-body    >RR</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 3 && Math.round(rs.getDouble(5)) < 4 ){
							out.println("<td  class=factoring-letter-body    >CV</td>"); 
						}
						else if(Math.round(rs.getDouble(5)) >= 4 ){
							out.println("<td  class=factoring-letter-body   >Z</td>"); 
						}
						*/
						
						// added by udara 23-12-2013
						// added by udara 23-12-2013
						if(rs.getString(17).equals("REPOSSESS")){
							out.println("<td  class=factoring-letter-body  > Seized </td>");
						}
						else{
							if(Math.round(rs.getDouble(5)) >= 0 && Math.round(rs.getDouble(5)) < 1){
							out.println("<td  class=factoring-letter-body    >-</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 1 && Math.round(rs.getDouble(5)) < 2 ){
								out.println("<td  class=factoring-letter-body    >NR</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 2 && Math.round(rs.getDouble(5)) < 3 ){
								out.println("<td  class=factoring-letter-body    >RR</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 3 && Math.round(rs.getDouble(5)) < 4 ){
								out.println("<td  class=factoring-letter-body    >CV</td>"); 
							}
							else if(Math.round(rs.getDouble(5)) >= 4 ){
								out.println("<td  class=factoring-letter-body   >Z</td>"); 
							}
						}
						// end by udara 23-12-2013
						
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  >"+nf1.format(rs.getDouble(13))+"</td>");			
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' STYLE='{text-align:right;}'  > &nbsp;</td>"); 
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand;text-align:right; >"+Math.round(rs.getDouble(5))+"</td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"add_client_comments('"+rs.getString(3)+"','"+rs.getString(2)+"')\"><u>Remarks</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand onClick=\"show_followup('"+rs.getString(2)+"')\"><u>"+rs.getString(8)+"</u></td>");
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"' style=cursor:hand >"+rs.getString(10)+"</td>");//Added By Lalanka on 15-07-2009
						out.println("<td  class=factoring-letter-body   bgcolor='"+row_colour+"'  >"+rs.getString(17)+"</td>"); // added by udara 26-12-2013
						out.println("</tr>");
						total_mon_rental=total_mon_rental+rs.getDouble(7);
						total_open_bal=total_open_bal+rs.getDouble(13);
						j+=1;
					}
					else if( rs.getString(2)==null && rs.getString(1).equals("SUB TOTAL")){
						out.println("<tr>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  ><B>Total Rental</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(14))+" </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > <B>Total Arrears</B></td>"); 
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > "+nf1.format(rs.getDouble(15))+" </td>");
						out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  > </td>"); // added by udara 26-12-2013
						out.println("</tr>");
						
					}
					
					
					more=rs.next();
					count+=1;
					
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;}' >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;cursor:hand;}'    >&nbsp;</u></td>"); 
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'   ><b>Grant Total</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'   ><b>"+nf1.format(total_mon_rental)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf1.format(total_open_bal)+"</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;}'   >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>"); 
					out.println("<td STYLE='{font:  bold 8pt  arial; text-align:right;}'  >&nbsp;</td>");  // added by udara 26-12-2013
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
					
					
				}
				
				
				
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
				out.println("<TITLE>Collection - Arrears Report</TITLE>"); 
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
				//	out.println(Sql_data);
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
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Arrears Report</u></td>"); 
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
					if ((rs.getDouble(7)+rs.getDouble(4))!=0)
					{
						achievement=(rs.getDouble(8)/(rs.getDouble(7)+rs.getDouble(4)))*100;
					}
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
					if(total_open_bal!=0)
					{
						open_pre=(total_open_bal/total_open_bal)*100;
						cur_pre=(total_cur_due/total_open_bal)*100;
					}
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
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// added by udara 18-05-2017
			
			
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
