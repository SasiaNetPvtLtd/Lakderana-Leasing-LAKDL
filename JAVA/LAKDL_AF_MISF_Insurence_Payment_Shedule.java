//Created by : Minal on 29-12-2014 for #15182

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MISF_Insurence_Payment_Shedule extends javax.servlet.http.HttpServlet { 
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
		Connection conn = null ;
		java.text.NumberFormat nf = null,nf1 = null;
		java.lang.Math a;
		Statement stmt = null,stmt2 = null;
		CallableStatement callstmt1 =null;
		ResultSet rs = null,rs1 = null,rs2 = null;
		String report_type="";
		
		
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
				
				String m_date=req.getParameter("date");
				String m_finance_no = req.getParameter("finance_no");
				String m_ins_company=req.getParameter("ins_company");
				String m_date_from=req.getParameter("date_from"); 
				
			/*	try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".af_re_save_runn_case_rpt(:1,:2,:3,:4,:5);END;");
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".af_re_save_runn_case_rpt(:1,:2,:3,:4,:5,:6);END;");//ADDED MILINDA 
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_user_id);
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_finance_no);
					callstmt1.setString(6,m_cr_office);//ADDED MILINDA
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}*/ 
			
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".af_re_save_runn_case_rpt(:1,:2,:3,:4,:5);END;");
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_INS_PAYMENT_SHEDULE_SAVE(:1,:2);END;");
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_INS_PAYMENT_SHEDULE_SAVE(:1,:2,:3);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_username);
					callstmt1.setString(3,m_date_from);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			else if(m_chksql.equals("run_report2")){ 
				
				String m_date=req.getParameter("date");
				String m_finance_no = req.getParameter("finance_no");
				String m_ins_company=req.getParameter("ins_company");
				String m_date_from=req.getParameter("date_from"); 

			
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".af_re_save_runn_case_rpt(:1,:2,:3,:4,:5);END;");
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_INS_PAYMENT_SHEDULE_SAVE(:1,:2);END;");
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_INS_PAYMENT_SHEDULE_SAVE_2(:1,:2,:3);END;"); //WROTE RUN REPORT ACCORDING TO THE ENTER DATE.
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_username);
					callstmt1.setString(3,m_date_from);
					callstmt1.execute();
					out.print("DDD"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			else if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Insurance Payment Shedule</TITLE>"); 
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
				out.println("		m_date_from=document.Form1.VAL_DAY_FROM.value+'-'+document.Form1.VAL_MONTH_FROM.value+'-'+document.Form1.VAL_YEAR_FROM.value;"); // added by udara 07-05-2015
				out.println("       finance_no = document.Form1.TXT_FINANCE.value;");
				out.println("		ins_company=document.Form1.COM_NAME.value;");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&ins_company=\"+ins_company;"); // commented by udara 07-05-2015
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&ins_company=\"+ins_company+\"&date_from=\"+m_date_from;"); // added by udara 07-05-2015
				//	out.println("		window.open(m_url)");
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				
				out.println("function run_report2() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_date_from=document.Form1.VAL_DAY_FROM.value+'-'+document.Form1.VAL_MONTH_FROM.value+'-'+document.Form1.VAL_YEAR_FROM.value;"); // added by udara 07-05-2015
				out.println("       finance_no = document.Form1.TXT_FINANCE.value;");
				out.println("		ins_company=document.Form1.COM_NAME.value;");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&ins_company=\"+ins_company;"); // commented by udara 07-05-2015
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=run_report2&finance_no=\"+finance_no+\"&date=\"+m_date+\"&ins_company=\"+ins_company+\"&ent_report=yes&date_from=\"+m_date_from;"); // added by udara 07-05-2015
				//	out.println("		window.open(m_url)");
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report2();"); 
				out.println("		}");
				out.println("		else if(m_data==\"DDD\"){");
				out.println("			print_report_ent_date();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				//Added by Kanchana on 2015-12-29 for issue no 18429	
				out.println("function print_report_ent_date(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("	if(document.Form1.VAL_DAY_FROM.value!=\"\" && document.Form1.VAL_MONTH_FROM.value!=\"\" && document.Form1.VAL_YEAR_FROM.value!=\"\"){");
				out.println("		m_date2=document.Form1.VAL_DAY_FROM.value+'-'+document.Form1.VAL_MONTH_FROM.value+'-'+document.Form1.VAL_YEAR_FROM.value;");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_finance_no=document.Form1.TXT_FINANCE.value;");
				out.println("		m_ins_company=document.Form1.COM_NAME.value;");
				out.println("   	m_branch_code=document.Form1.TXT_BRANCH_CODE.value;"); //Added by Kanchana on 2015-12-29 for issue no 18429				
				out.println("		m_business_type = document.Form1.TXT_BUSINESS_TYPE.value;"); // added by udara 16-01-2015
				out.println("		m_paid_status   = document.Form1.TXT_PAID_STATUS.value;"); // added by udara 16-01-2015				
				out.println("         m_order_by = 'FINANCE_NO'; ");
				out.println("         m_asc_desc = 'DESC'; ");
				
				//out.println("         m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=print_report_new&finance_no=\"+m_finance_no+\"&date=\"+m_date+\"&ins_company=\"+m_ins_company+\"&business_type=\"+m_business_type+\"&paid_status=\"+m_paid_status;"); // added by udara 16-01-2015
				out.println("         m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=print_report_new&finance_no=\"+m_finance_no+\"&branch_code=\"+m_branch_code+\"&date=\"+m_date+\"&date2=\"+m_date2+\"&ins_company=\"+m_ins_company+\"&business_type=\"+m_business_type+\"&paid_status=\"+m_paid_status+\"&order_by=\"+m_order_by+\"&ent_report=yes&asc_desc=\"+m_asc_desc;");
				
				out.println("		  window.open(m_url);");
				out.println("	}");
				out.println("	}");
				out.println("}");
				//Endded by KAanchana.
				
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_finance_no=document.Form1.TXT_FINANCE.value;");
				out.println("		m_ins_company=document.Form1.COM_NAME.value;");
				out.println("   	m_branch_code=document.Form1.TXT_BRANCH_CODE.value;"); //Added by Kanchana on 2015-12-29 for issue no 18429				
				out.println("		m_business_type = document.Form1.TXT_BUSINESS_TYPE.value;"); // added by udara 16-01-2015
				out.println("		m_paid_status   = document.Form1.TXT_PAID_STATUS.value;"); // added by udara 16-01-2015
				
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
				
				//out.println(" if((document.Form1.DUE_DAY.value=='')||(document.Form1.DUE_MONTH.value=='')||(document.Form1.DUE_YEAR.value=='')) ");
				//out.println("       m_due_date = ''; ");
				//out.println(" else ");
				//out.println("		m_due_date=document.Form1.DUE_DAY.value+'-'+document.Form1.DUE_MONTH.value+'-'+document.Form1.DUE_YEAR.value;"); // added by udara on 18-06-2013
				
				//out.println(" m_due_date=document.Form1.DUE_DAY.value; ");
				//out.println(" m_maturity_type=document.Form1.TXT_MATURITY_STATUS.value; "); // added by udara on 08-10-2013
				//out.println(" m_cr_officer=document.Form1.MKT_OFFICER.value;");//added milinda 2013-10-16
				
				//out.println(" m_perform_status=document.Form1.TXT_PERFORM_STATUS.value;"); // added by udara 17-02-2014
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Runn_Case_Details?chksql=print_report_new&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date+\"&due_date=\"+m_due_date+\"&maturity_type=\"+m_maturity_type;");	 // mod by udara on 18-06-2013 & 08-10-2013//commented by milinda 2013-10-16
				
				//out.println("         m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=print_report_new&finance_no=\"+m_finance_no+\"&date=\"+m_date+\"&ins_company=\"+m_ins_company;"); // commented by udara 16-01-2015	//+\"&due_date=\"+m_due_date
				
				
				out.println("         m_order_by = 'FINANCE_NO'; ");
				out.println("         m_asc_desc = 'DESC'; ");
				
				//out.println("         m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=print_report_new&finance_no=\"+m_finance_no+\"&date=\"+m_date+\"&ins_company=\"+m_ins_company+\"&business_type=\"+m_business_type+\"&paid_status=\"+m_paid_status;"); // added by udara 16-01-2015
				out.println("         m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=print_report_new&finance_no=\"+m_finance_no+\"&branch_code=\"+m_branch_code+\"&date=\"+m_date+\"&ins_company=\"+m_ins_company+\"&business_type=\"+m_business_type+\"&paid_status=\"+m_paid_status+\"&order_by=\"+m_order_by+\"&ent_report=no&asc_desc=\"+m_asc_desc;");
				
				out.println("		  window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				
			/*	out.println("function get_vector(data_vec) {");
				
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
				
				out.println("			if(data_vec.length==0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("     mk_officer_help(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("			document.Form1.MKT_OFFICER.value=data_vec[0]");
				out.println("			}");
				
				out.println("}");*/
				
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
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				
				out.println("}");	
				
				
				
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				
				out.println("function makeRequest(obj) {");
				
				/*out.println("if(document.Form1.hid_chk_status.value=='M_CLIENT' )");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M1' )");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_marketing_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_LOCATION_CODE.value+\"&ac_status=Y\";");	
				
				out.println("else if(document.Form1.hid_chk_status.value=='M_MKT_OFFC' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_credit_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.MKT_OFFICER.value+\"&ac_status=Y\";");	
				
				//out.println("window.open(m_url);");
				*/
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=main_page&generate=page';"); 
				out.println("}"); 
				out.println(""); 
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
				out.println("help_box.innerHTML=\" Insurance Payment Shedule - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Insurance Payment Shedule - \"+document.Form1.hid_status.value;"); 
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
				out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("		if(oBj.valout[1] ==\" \"){"); 
				out.println("    clear_fields(); ");
				out.println("		} else ");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("	if(oBj.valout[1]=='Next')  {");
				out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
				out.println("	}");
				out.println("	else if  (oBj.valout[1]=='Prev') {");
				out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("	}		");
				out.println("	else if(oBj.valout[1] == 'Close'){");
				out.println("	}");
				out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("	if(IfCount=='4'){"); 
				out.println("		help_value_assign_finance(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='99'){"); 
				out.println("		help_company_assign_99(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='7'){"); //Added by Kanchana Karunarathna 2015-12-29 for issue no 18429
				out.println("		branch_assign(oBj);"); 
				out.println("	}");				
				out.println("	}"); 
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	else{	"); 
				out.println("    clear_fields(); ");
				out.println("	}	"); 
				out.println("	}	"); 
				out.println("}");  

	
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				
				out.println("function clear_data() {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.COM_NAME.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"4\"){"); 
				out.println("document.Form1.TXT_FINANCE.value='';");
				out.println("		}");
			
				out.println("}");
				//-----------------------------------------------------------------------------------------------------------------------------------------
				
				out.println(""); 
				
				out.println("function help_button_finance() {"); 
				out.println(" document.Form1.hid_help_type.value='4' ");
				out.println("    Crit = document.Form1.TXT_FINANCE.value+\"@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_branch_sql','4');"); 
				out.println("}"); 
				
				out.println("function help_value_assign_finance(oBj) {"); 
				out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
				//out.println("    document.Form1.COM_NAME.value=oBj.valout[7];"); 
				out.println("}");
				//-----------------------------------
				
				out.println("function help_company() {"); 
					out.println("    document.Form1.hid_help_type.value='99';"); 
					out.println("    m_sql = \"m_help_insurance_company_code\";"); 
					out.println("    Crit = document.Form1.COM_NAME.value+\"@\";"); 
					out.println("    HelpBox('1','10','0',Crit,m_sql,'99');"); 
					out.println("}"); 
					
					out.println("function help_company_assign_99() {");
					out.println("    document.Form1.COM_NAME.value=oBj.valout[2];"); 
					//out.println("    document.Form1.COM_NAME.value=oBj.valout[3];"); 
					out.println("}"); 	
				
				out.println("function print_report(date,finance_no,m_ins_company) {");//Minal
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=main_page&generate=print_report&finance_no=\"+finance_no+\"&date=\"+date+\"&ins_company=\"+m_ins_company;");	
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
				
				
				// added by udara 07-05-2015
				
				out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.VAL_DAY_FROM.value=v_date;");
				out.println("     document.Form1.VAL_MONTH_FROM.value=v_month;");
				out.println("     document.Form1.VAL_YEAR_FROM.value=val;");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY_FROM.value+'-'+document.Form1.VAL_MONTH_FROM.value+'-'+document.Form1.VAL_YEAR_FROM.value;");			
				out.println("  }");	
				
				// end by udara 07-05-2015
				
				
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
				
				
				out.println("     document.Form1.DUE_DAY.value=v_date;");
				out.println("     document.Form1.DUE_MONTH.value=v_month;");
				out.println("     document.Form1.DUE_YEAR.value=val;");
				//out.println("     document.Form1.hid_date.value=document.Form1.DUE_DAY.value+'-'+document.Form1.DUE_MONTH.value+'-'+document.Form1.DUE_YEAR.value;");			
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
					
					// added by udara 07-05-2015
					out.println("document.Form1.VAL_DAY_FROM.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH_FROM.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR_FROM.value='"+rs2.getString(3)+"';");
					// end by udara 07-05-2015
					
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				out.println("}"); 
				
				//Added by Kanchana Karunarathna 2015-12-29 for issue no 18429
				out.println("function branch_help() {"); 
                out.println("    document.Form1.hid_help_type.value=\"3\";"); 
                out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
                out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+\"Y@\"; "); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,7);"); 
                out.println("}");
	
				out.println("function branch_assign(oBj){");			
                out.println("   document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");			
                out.println("}");
				// end by Kanchana 2015-12-29
				
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Insurance Payment Shedule </td>"); 
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
				
				
				// added by udara 07-05-2015
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE_FROM >From Date</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY_FROM\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM,document.Form1.VAL_MONTH_FROM,document.Form1.VAL_YEAR_FROM)> ");
				out.println("    <input name=\"VAL_MONTH_FROM\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM,document.Form1.VAL_MONTH_FROM,document.Form1.VAL_YEAR_FROM)> ");
				out.println("    <input name=\"VAL_YEAR_FROM\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_FROM,document.Form1.VAL_MONTH_FROM,document.Form1.VAL_YEAR_FROM)><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");
				out.println("</td>"); 
				out.println("</tr>");
				
				// end by udara 07-05-2015
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>To Date</td>"); // out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>");  
				out.println("</tr>");
				
				//Added by Kanchana on 2016-02-18.
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Report for enter date</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report_ent_date()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run for Enter Date\" onClick=\"run_report2()\" style=\"{width:110px;}\"></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='10' style='{width=150px}' size='10' onblur='help_button_finance()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr> ");
				out.println("<td width='7%' ><DIV id='DIV_COM_NAME'  class=div_input> Insurance Company</DIV></td>"); 
                out.println("<td width='40%' ><input class='txt_input' type='text' name='COM_NAME' maxlength='10' size='50' style='width:100' >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_COMPANY' value=\"Help\" onClick=\"help_company()\" > ");
				out.println("</td>");
				out.println("</tr>");
				
				
				//Added by Kanchana on 2015-12-29 for issue no 18429
				
				out.println("<tr class=tr_input>");
				out.println("<td width='7%' ID=DIV_BRANCH_CODE>Branch</td>");
				out.println("<td width='40%' ><input name=\"TXT_BRANCH_CODE\"   type=\"text\" maxlength=\"30\" style=\"width: 100px\" class=\"txt_input\" > ");
                out.println("<input class='but_input' type='button' name='HELP_BRANCH_CODE' value=\"Help\" onClick=\"branch_help()\" ></td>"); 	
				out.println("</td>");
				out.println("<td width='*%' > </td>");
				out.println("</tr>");
			
				
				// added by udara 16-01-2015
				
				out.println("<tr>"); ; 
				out.println("<td  width='5%' ><DIV id='DIV_TXT_BUSINESS_TYPE'  class=div_input> Business Type </DIV></td>"); 
				out.println("<td ='40%' ><select name='TXT_BUSINESS_TYPE' class='txt_input' style=\"width:100px;\" >");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"NEW\" >New</option>");
				out.println("<option value=\"RENEWAL\" >Renewal</option>");				
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>"); ; 
				out.println("<td  width='5%' ><DIV id='DIV_TXT_PAID_STATUS'  class=div_input> Paid Status </DIV></td>"); 
				out.println("<td ='40%' ><select name='TXT_PAID_STATUS' class='txt_input' style=\"width:100px;\" >");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"Y\" >Paid</option>");
				out.println("<option value=\"N\" >Not Paid</option>");				
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				
				// end by udara 16-01-2015
			/*	//Added by Kanchana on 2016-02-18.
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Report for enter date</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_PRINT' value=\"View\" onClick=\"print_report_ent_date()\" style=\"{width:110px;}\">"); //Added by Kanchana
				out.println("</td>");
				out.println("</tr>");*/
				
				
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
				String m_date=req.getParameter("date");
				String m_finance_no = req.getParameter("finance_no");
				String m_ins_company = req.getParameter("ins_company").trim();
				String m_business_type = req.getParameter("business_type").trim(); // added by udara 16-01-2015
				String m_paid_status = req.getParameter("paid_status").trim(); // added by udara 16-01-2015
				String m_branch_code=req.getParameter("branch_code");
				// added by udara 17-02-2015
				String m_order_by = req.getParameter("order_by").trim();
				String m_asc_desc = req.getParameter("asc_desc").trim();  
				String m_ent_report=req.getParameter("ent_report");		
				String m_date2=req.getParameter("date2");//FROM DATE Added by Kanchana on 2016-02-18.
				String m_branch_code_sql="";
				// end by udara 17-02-2015
				
				// added by udara 16-01-2015
				/*
				if(m_business_type.equals("ALL"))
					m_business_type = " ";
				else if(m_business_type.equals("NEW"))
					m_business_type = " AND BUSINESS_TYPE = 'NEW' ";
				else if(m_business_type.equals("RENEWAL"))
					m_business_type = " AND BUSINESS_TYPE = 'RENEWAL' ";

				
				if(m_paid_status.equals("ALL"))
					m_paid_status = " ";
				else if(m_paid_status.equals("Y"))
					m_paid_status = " AND PAID_NON_PAID = 'Y' ";
				else if(m_paid_status.equals("N"))
					m_paid_status = " AND PAID_NON_PAID = 'N' ";
				*/
				
				//out.println(" start " + m_business_type);
				
				if(m_business_type.equals("ALL"))
					m_business_type = "";
				else if(m_business_type.equals("NEW"))
					m_business_type = "NEW";
				else if(m_business_type.equals("RENEWAL"))
					m_business_type = "RENEWAL";
				
				//out.println(" end " + m_business_type);

				//out.println(" start " + m_paid_status);
				
				if(m_paid_status.equals("ALL"))
					m_paid_status = "";
				else if(m_paid_status.equals("Y"))
					m_paid_status = "Y";
				else if(m_paid_status.equals("N"))
					m_paid_status = "N";
				
				//out.println(" end " + m_paid_status);
				
				// end by udara 16-01-2015
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Insurance Payment Shedule</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">");  
			
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
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				//out.println("    alert('m_client_code : ' + m_client_code + ' m_finance_no : ' + m_finance_no );   ");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				
				out.println("function sort_data(m_sort_col) {");

				out.println("	   	if('"+m_asc_desc+"'=='DESC'){");
				out.println("	      	m_asc_desc = 'ASC'; ");  
				out.println("    	} ");
				out.println(" 		else{");
				out.println("       	m_asc_desc = 'DESC'; ");
				out.println("    	}");

				out.println("		m_date='"+m_date+"';");
				out.println("		m_finance_no='"+m_finance_no+"';");
				out.println("		m_ins_company='"+m_ins_company+"';");
				
				out.println("		m_business_type = '"+m_business_type+"';"); // added by udara 16-01-2015
				out.println("		m_paid_status   = '"+m_paid_status+"';"); // added by udara 16-01-2015

				out.println("       m_order_by = m_sort_col; ");
 
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Insurence_Payment_Shedule?chksql=print_report_new&finance_no=\"+m_finance_no+\"&date=\"+m_date+\"&ins_company=\"+m_ins_company+\"&business_type=\"+m_business_type+\"&paid_status=\"+m_paid_status+\"&order_by=\"+m_order_by+\"&ent_report=no&asc_desc=\"+m_asc_desc;"); //&ent_report=no added by kanchana.

				out.println("       window.location.href=m_url;"); 
				
				out.println("}");
				
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");
				
				stmt = conn.createStatement ();
				
				String m_ins_company_name = "";
				
				rs=stmt.executeQuery("  "+
								" SELECT AF_CO_GET_SUB_CHARG_PAYEE_NAME('"+m_ins_company+"') "+
								" FROM DUAL "+
					        " ");
				
				if(rs.next()){
					m_ins_company_name = rs.getString(1);
				}
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				//out.println("<td width=\"*\" STYLE='{font: bold 20pt arial; text-align:center;}'   ><u>"+m_ins_company+" Insurance Payment Shedule</u></td>"); 
				
				if( (m_ins_company.equals("")) || (m_ins_company==null) )
					out.println("<td width=\"*\" STYLE='{font: bold 20pt arial; text-align:center;}'   ><u> Insurance Payment Shedule </u></td>"); 
				else
					out.println("<td width=\"*\" STYLE='{font: bold 20pt arial; text-align:center;}'   ><u>"+m_ins_company_name+" Insurance Payment Shedule</u></td>");
				
				out.println("</tr >");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 8pt arial; text-align:center;}'   >Payment to  "+m_date+" </td>"); 
				out.println("</tr>");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				out.println("<tr>");
				out.println("<td class=factoring-letter-body ><b>No</b></td>"); 			
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('FINANCE_NO') ><b>Contract No</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('CLI_NAME')   ><b>Name</b></td>");
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('VEHICLE_NO') ><b><p>Vehicle<br>No</p></b></td>");  
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('PERIOD_OF_OUTSTAND') ><b><p> Period of <br> Outstanding</p></b></td>");  // out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' ><b><p>Due Period <br> Outstanding</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('PAID_NON_PAID') ><b>Paid/Non-Paid</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('PAYMENT') ><b><p>Payable Premium</p></b></td>");  // out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' ><b><p>Payment</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('PREMIUM') ><b>Permium</b></td>"); 																																											
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('BATCH_1') ><b><p>1</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('BATCH_2') ><b><p>2</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('BATCH_3') ><b><p>3</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('BATCH_4') ><b><p>4 (More Than 3)</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('BALANCE_RECEIVABLE') ><b><p>Balance Receivable</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('PERCENTAGE') ><b><p>As a %</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('DIFFERENCE') ><b><p>Difference</p></b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('OTHER_PAYMENT_VAL') ><b><p>Paid Premium</p></b></td>");  // added by udara 17-02-2015
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }' onclick=sort_data('REMARKS') ><b><p>Remarks</p></b></td>"); 
				out.println("</tr >");
				
				if(!m_branch_code.equals("")){
				
					m_branch_code_sql =	" AND "+m_schema_name+".AF_CO_GET_BRANCH_CODE(FINANCE_NO) LIKE '"+m_branch_code+"%'";				
				}else{
					m_branch_code_sql ="";
				}
				
				
				
				
				int count = 0;
				
				double tot_payment = 0;
				double tot_premium = 0;
				double tot_batch_1 = 0;
				double tot_batch_2 = 0;
				double tot_batch_3 = 0;
				double tot_batch_4 = 0;
				double tot_bal_receivable = 0;
				double final_percentage = 0;
				double tot_diff = 0;
				double tot_paid_premium = 0; // added by udara 17-02-2015
				String SQL="";
				//stmt = conn.createStatement ();
				//out.println(" "+
				
				if(m_ent_report.equals("no")){ // condition Added by kanchana on 2016-02-17
			//	rs=stmt.executeQuery("  "+	
			      SQL=	" SELECT "+
						" FINANCE_NO, "+
						" CLI_NAME, "+
						" VEHICLE_NO, "+
						" PERIOD_OF_OUTSTAND, "+
						" DECODE(PAID_NON_PAID,'N','Not Paid','Y','Paid') PAID_NON_PAID, "+
						" NVL(PAYMENT,0), "+
						" NVL(PREMIUM,0), "+
						" NVL(BATCH_1,0), "+
						" NVL(BATCH_2,0), "+
						" NVL(BATCH_3,0),	 "+				
						" NVL(BALANCE_RECEIVABLE,0), "+
						" NVL(PERCENTAGE,0), "+
						" NVL(DIFFERENCE,0), "+
						" NVL(REMARKS,'-'), "+ // " DECODE(REMARKS,'-',' ',NULL,' ',REMARKS), "+ // " REMARKS, "+
						" NVL(BATCH_4,0), "+ // 15
						" NVL(OTHER_PAYMENT_VAL,0), "+ // 16 added by udara 17-02-2015
						" TO_CHAR(DISBURSED_DATE,'DD-MM-YYYY') "+ // 17
							" FROM "+m_schema_name+".AF_INS_PAYMENT_SHEDULE "+
							" WHERE ENT_USER = '"+m_username+"' "+
							" AND START_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
							" AND FINANCE_NO LIKE  '%"+m_finance_no+"%' "+
							" AND INSUR_COM LIKE  '%"+m_ins_company+"%' "+ 
							//" "+m_business_type+" "+ // added by udara 16-01-2015
							//" "+m_paid_status+" "+ // added by udara 16-01-2015
							
							" AND BUSINESS_TYPE LIKE '"+m_business_type+"%'  "+
							" AND PAID_NON_PAID LIKE '"+m_paid_status+"%'  "+
							" "+m_branch_code_sql+" "+ // Added by Kanchana for issue no 18429. (AF_CO_GET_BRANCH_CODE_PAY_SHED)
							" AND  PAYMENT > 0 "+ // added by udara 15-12-2015
							
							//" ORDER BY FINANCE_NO   "+ // added by udara 12-01-2014
							" ORDER BY "+m_order_by+"  "+m_asc_desc+"  "+ 
					" ";//);
			}
				else if(m_ent_report.equals("yes")){ //Added by kanchana on 2016-02-17
				
			//	rs=stmt.executeQuery("  "+	
				SQL=	" SELECT "+
						" FINANCE_NO, "+
						" CLI_NAME, "+
						" VEHICLE_NO, "+
						" PERIOD_OF_OUTSTAND, "+
						" DECODE(PAID_NON_PAID,'N','Not Paid','Y','Paid') PAID_NON_PAID, "+
						" NVL(PAYMENT,0), "+
						" NVL(PREMIUM,0), "+
						" NVL(BATCH_1,0), "+
						" NVL(BATCH_2,0), "+
						" NVL(BATCH_3,0),	 "+				
						" NVL(BALANCE_RECEIVABLE,0), "+
						" NVL(PERCENTAGE,0), "+
						" NVL(DIFFERENCE,0), "+
						" NVL(REMARKS,'-'), "+ // " DECODE(REMARKS,'-',' ',NULL,' ',REMARKS), "+ // " REMARKS, "+
						" NVL(BATCH_4,0), "+ // 15
						" NVL(OTHER_PAYMENT_VAL,0), "+ // 16 added by udara 17-02-2015
						" TO_CHAR(DISBURSED_DATE,'DD-MM-YYYY') "+ // 17
							" FROM "+m_schema_name+".AF_INS_PAYMENT_SHEDULE "+
							" WHERE ENT_USER = '"+m_username+"' "+
						//	" AND TRUNC(ENT_DATE,'DD')  between to_date ('"+m_date2+"', 'DD-MM-YYYY') AND to_date ('"+m_date+"', 'DD-MM-YYYY')  "+ //DIFFER FROM ABOVE QUERY
							"  AND TO_DATE(TO_CHAR(INS_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_date2+"','DD-MM-YYYY')  "+  
							"  AND TO_DATE(TO_CHAR(INS_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+						
							" AND FINANCE_NO LIKE  '%"+m_finance_no+"%' "+
							" AND INSUR_COM LIKE  '%"+m_ins_company+"%' "+ 
							//" "+m_business_type+" "+ // added by udara 16-01-2015
							//" "+m_paid_status+" "+ // added by udara 16-01-2015
							
							" AND BUSINESS_TYPE LIKE '"+m_business_type+"%'  "+
							" AND PAID_NON_PAID LIKE '"+m_paid_status+"%'  "+
							" "+m_branch_code_sql+" "+ // Added by Kanchana for issue no 18429. (AF_CO_GET_BRANCH_CODE_PAY_SHED)
							" AND  PAYMENT > 0 "+ // added by udara 15-12-2015
							
							//" ORDER BY FINANCE_NO   "+ // added by udara 12-01-2014
							" ORDER BY "+m_order_by+"  "+m_asc_desc+"  "+ 
					" " ;//);

				
				}
			
			
			rs=stmt.executeQuery(SQL);
				while(rs.next()){
					
					tot_payment = tot_payment + rs.getDouble(6);
					tot_premium = tot_premium + rs.getDouble(7);
					tot_batch_1 = tot_batch_1 + rs.getDouble(8);
					tot_batch_2 = tot_batch_2 + rs.getDouble(9);
					tot_batch_3 = tot_batch_3 + rs.getDouble(10);
					tot_batch_4 = tot_batch_4 + rs.getDouble(15);
					tot_bal_receivable = tot_bal_receivable + rs.getDouble(11);
					tot_diff = tot_diff + rs.getDouble(13);
					tot_paid_premium = tot_paid_premium + rs.getDouble(16); // added by udara 17-02-2015
					
					count = count + 1;

					out.println("<tr>");
					out.println("<td class=factoring-letter-body > "+count+"  </td>");
					out.println("<td class=factoring-letter-body STYLE='text-align:left; cursor:hand;' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\" ><u> "+rs.getString(1)+"  </u></td>");
					out.println("<td class=factoring-letter-body > "+rs.getString(2)+"  </td>");
					out.println("<td class=factoring-letter-body > "+rs.getString(3)+"  </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+rs.getString(4)+"  </td>");
					//out.println("<td class=factoring-letter-body > "+rs.getString(5)+"  </td>");
					
					if(rs.getString(5).equals("Paid"))
						out.println("<td class=factoring-letter-body > "+rs.getString(5)+" - "+rs.getString(17)+"  </td>");
					else
						out.println("<td class=factoring-letter-body > "+rs.getString(5)+"  </td>");
					
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(6))+"  </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(7))+"  </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(8))+"  </td>"); // 1
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(9))+"  </td>"); // 2
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(10))+" </td>"); // 3
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(15))+" </td>"); // 4
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(11))+" </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(12))+" </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(13))+" </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > "+nf.format(rs.getDouble(16))+" </td>"); // added by udara 17-02-2015
					//out.println("<td class=factoring-letter-body > "+rs.getString(14)+" </td>");
					
					if(rs.getString(14).equals("-"))
						out.println("<td class=factoring-letter-body > &nbsp; </td>");
					else
						out.println("<td class=factoring-letter-body > "+rs.getString(14)+" </td>");
					
					
					out.println("</tr >");
				}
				
					out.println("<tr>");
					out.println("<td class=factoring-letter-body > &nbsp; </td>");
					out.println("<td class=factoring-letter-body STYLE='text-align:left; cursor:hand;' > &nbsp; </td>");
					out.println("<td class=factoring-letter-body > &nbsp; </td>");
					out.println("<td class=factoring-letter-body > &nbsp;  </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' > &nbsp;  </td>");
					out.println("<td class=factoring-letter-body > &nbsp;  </td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_payment)+" </b></td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_premium)+" </b></td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_batch_1)+" </b></td>"); // 1
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_batch_2)+" </b></td>"); // 2
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_batch_3)+" </b></td>"); // 3
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_batch_4)+" </b></td>"); // 4
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_bal_receivable)+" </b></td>");
					
					if(tot_bal_receivable!=0)
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format((tot_bal_receivable/tot_premium)*100)+" </b></td>");
					else
						out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(0)+" </b></td>");
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_diff)+" </b></td>");
					out.println("<td class=factoring-letter-body STYLE='{text-align:right; }' ><b> "+nf.format(tot_paid_premium)+" </b></td>"); // added by udara 17-02-2015
					out.println("<td class=factoring-letter-body > &nbsp; </td>");
					out.println("</tr >");
				
				
					
				out.println("</table>");	
			
			    out.println("<br><br><br><br>");
			
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }'>.................</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }'>.................</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }'>.................</b></td>"); 
				out.println("</tr >");
				out.println("<tr>");
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }'>Prepared By</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }'>Checked By</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center; cursor:hand; }'>Approved By</b></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
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