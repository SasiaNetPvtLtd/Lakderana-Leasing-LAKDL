//---------CREATED BY DINETH ON 2009-01-30
//---------SCREEN NAME AF_MISF_CONTRACT_LVL_BAL

//TOTALY MODIFIED BY SANDUN ON 21-05-2009
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_contract_level_bal extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt1,stmt,stmt2,stmt_cr,stmt_dr;
		public ResultSet rs,rs1,rs2;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;
		CallableStatement callstmt1 =null;
		ServletOutputStream out = null;
		public ResultSet rs_cr,rs_dr;
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try {
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req);
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
				stmt1=conn.createStatement();
				stmt=conn.createStatement();
				stmt2=conn.createStatement();
				stmt_cr=conn.createStatement();
				stmt_dr=conn.createStatement();
			
				
				
				String m_chksql= req.getParameter("chksql");
				
				if(m_chksql.equals("run_report")){ 
			  
				String m_from_date = req.getParameter("from_date");
				String m_to_date   = req.getParameter("to_date");
				String m_div_code  = req.getParameter("devision_code");
				String m_check     = req.getParameter("check_box");
				String m_acc_no    = req.getParameter("acc_no");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_CONTRACT_BALANCE_NEW(:1,:2,:3,:4,:5,:6);END;");
				callstmt1.setString(1,m_from_date);
				callstmt1.setString(2,m_to_date);
				callstmt1.setString(3,m_username);
				callstmt1.setString(4,m_div_code);
				callstmt1.setString(5,m_check);
				callstmt1.setString(6,m_acc_no);
				
				callstmt1.execute();
				
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}

			 else if(m_chksql.trim().equals("main_page")){
		  
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Contract Level Balance New </TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
					
					out.println("var m_sav_msg='';");
					out.println("var timerID;");
				  out.println("var durationID=0;");
				
					out.println("function set_timer_actions() {");
					out.println("   durationID=durationID+1;");
					out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
					out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
					out.println("}");
				
					
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
					
					out.println("function show_contract_level_balance_report() {");
					out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
					out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
					out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
					out.println(" m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
					out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
					out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
					out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
					out.println(" m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; "); 
					out.println("finance_no = document.Form1.TXT_FINANCE.value;");
					out.println("check_select= document.Form1.CHK_REQ.value;"); 
					out.println("		if(validate_date()) {");
					out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_BAL_REPORT_LEVEL_1&finance_no=\"+finance_no+\"&chk=\"+check_select+\"&to_date=\"+m_to_date+\"&from_date=\"+m_from_date+\"&div_code=\"+document.Form1.TXT_DIVISION_CODE.value+\"&acc_type_code=\"+document.Form1.TXT_ACC_CODE.value;");//
					out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
					out.println("   }");
					out.println("}");
			
					out.println("function get_system_date() {");
					out.println("	  document.Form1.hid_option.value=\"1\";");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
					out.println("		load_interface(m_url,'XML');");
					out.println("}");
					
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
					
					out.println("function load_calendar(num) {");
      		out.println(" document.Form1.hid_cal_date.value=num;"); 
					out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=200,width=320,height=230\");"); 
					//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
					out.println("}");
							
					out.println("function load_c_date(val) {");
					out.println("var date1='' ");
					out.println("var date2='' ");
		  		out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
					out.println("		v_date=val.substr(0,val.indexOf('-'));");
					out.println("		if(v_date.length<2)");
					out.println("			v_date=0+v_date");
					out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("			v_month=val.substr(0,val.indexOf('-'));");
					out.println("		if(v_month.length<2)");
					out.println("			v_month=0+v_month");
					out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_date;");
					out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_month;");
					out.println("     document.Form1.TXT_FROM_DATE_YY.value=val;");
					//out.println("			date1=v_date+'-'+v_month+'-'+val;");
					//out.println("			document.Form1.hid_from_date.value=date1");
					out.println("	}");
					out.println(" if(document.Form1.hid_cal_date.value=='2'){"); 
					out.println("		v_date=val.substr(0,val.indexOf('-'));");
					out.println("		if(v_date.length<2)");
					out.println("			v_date=0+v_date");
					out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("			v_month=val.substr(0,val.indexOf('-'));");
					out.println("		if(v_month.length<2)");
					out.println("			v_month=0+v_month");
					out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("     document.Form1.TXT_TO_DATE_DD.value=v_date;");
					out.println("     document.Form1.TXT_TO_DATE_MM.value=v_month;");
					out.println("     document.Form1.TXT_TO_DATE_YY.value=val;");
					//out.println("			date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
					//out.println("			document.Form1.hid_to_date.value=date2");
					out.println(" }");					
					out.println("}");
					
					out.println("function validate_data(){"); 
					out.println("	return true;"); 
					out.println("}"); 			
	

					out.println("function load_lock(){	"); 
					//out.println("document.oncontextmenu=new Function(\"return false\");"); 
					out.println("}	"); 

					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
					out.println("function new_window(){	"); 
				
					out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=main_page';"); 
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
					out.println("	help_box.innerHTML=\" Contract Level Balance New - \"+m_val;"); 
					out.println("}"); 

					out.println("function load_roll_out_value(){");
					out.println("	help_box.innerHTML=\" Contract Level Balance New \";"); 
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
			
					out.println("function help_update_division() {"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					out.println("    m_sql = \"m_help_TXT_DIVISION_CODE_sql\";"); 
					out.println("    m_criteria = document.Form1.TXT_DIVISION_CODE.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 

					out.println("function help_update_value_assign_1() {"); 
					out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[2];"); 
					out.println("}");
					
					out.println("function help_update_value_assign_2() {"); 
					out.println("    document.Form1.TXT_PRODUCT_CODE.value=oBj.valout[2];"); 
					out.println("}");
			
					out.println("function help_update_account() {"); 
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					out.println("    m_sql = \"m_help_TXT_ACC_CODE_sql\";"); 
					out.println("    m_criteria = document.Form1.TXT_ACC_CODE.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
				
					out.println("function help_update_value_assign_3() {"); 
					out.println("    document.Form1.TXT_ACC_CODE.value=oBj.valout[2];"); 
					//out.println("    document.Form1.TXT_DESCRIPTION.value=oBj.valout[3];"); 
					out.println("}");
			    
					
					
					out.println("function help_finance_no() {"); 
					out.println("    document.Form1.hid_help_type.value=\"5\";"); 
					out.println("    m_sql = \"m_help_Fin_No_Sql\";"); 
					out.println("    m_criteria = document.Form1.TXT_FINANCE.value+\"@\";"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
					out.println("function help_finance_assign_5(){");
					out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
					out.println("}"); 
					
					
					
					out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
					out.println("    oBj = new MyDialog();"); 
					out.println("    oBj.valout[1]  = \" \";"); 
					out.println("    oBj.valout[2]  = \" \";"); 
					out.println("    oBj.valout[3]  = \" \";"); 
					out.println("	"); 
					out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
					out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
					out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
					out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
					out.println("	"); 
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("		if(oBj.valout[1] !=\"Close\"){"); 
					out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("				if(oBj.valout[1]!=\"Next\"){"); 
					out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
					out.println("						help_update_value_assign_1();"); 
	  			out.println("					}"); 
					out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
					out.println("						help_update_value_assign_2();"); 
	  			out.println("					}"); 
					out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
					out.println("						help_update_value_assign_3();"); 
	  			out.println("					}"); 
					out.println("					if(document.Form1.hid_help_type.value==\"4\"){"); 
					out.println("						help_update_value_assign_4();"); 
	  			out.println("					}"); 
					out.println("					if(document.Form1.hid_help_type.value==\"5\"){"); 
					out.println("						help_finance_assign_5();"); 
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
				
		     out.println("function change_state(){");
				 out.println("if(document.Form1.CHK_REQ.checked){");
				 out.println("document.Form1.CHK_REQ.value=\"on\";"); 
				 out.println("document.Form1.TXT_FROM_DATE_DD.disabled=true;");
				 out.println("document.Form1.TXT_FROM_DATE_MM.disabled=true;");
				 out.println("document.Form1.TXT_FROM_DATE_YY.disabled=true;");
				 out.println("}"); 	
				 out.println("else{");
				 out.println("document.Form1.CHK_REQ.value=\"off\";"); 		
				 out.println("document.Form1.TXT_FROM_DATE_DD.disabled=false;");
				 out.println("document.Form1.TXT_FROM_DATE_MM.disabled=false;");
				 out.println("document.Form1.TXT_FROM_DATE_YY.disabled=false;");
				 out.println("}");
			   //out.println("alert(document.Form1.CHK_REQ.value);");
					out.println("}"); 		
					
				//Modified by Disnaka Jayasuriya on 2009-10-14 for date validation	
				out.println("   function run_report() {");
				out.println("   if (validate_date()){");
				out.println("   	if(document.Form1.TXT_FINANCE.value==\"\"){");
				out.println("   	if(document.Form1.CHK_REQ.value==\"off\"){");
				out.println("	  	if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" && document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
				out.println("			m_from_date = document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("			m_to_date   = document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=run_report&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&check_box=OFF&devision_code=\"+document.Form1.TXT_DIVISION_CODE.value+\"&acc_no=\"+document.Form1.TXT_ACC_CODE.value;"); 
				out.println("   	set_timer_actions();");
				out.println("			load_interface(m_url,'NORM');");
				out.println("			}");
				out.println("			}else{");
				out.println("	  	if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
				out.println("			m_to_date   = document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=run_report&to_date=\"+m_to_date+\"&check_box=ON&devision_code=\"+document.Form1.TXT_DIVISION_CODE.value+\"&acc_no=\"+document.Form1.TXT_ACC_CODE.value;"); 
				out.println("   	set_timer_actions();");
				out.println("			load_interface(m_url,'NORM');");
				out.println("			}");
				out.println("	}");
				out.println("	}else{");
				out.println("	print_report();");
				out.println("	}");
				out.println(" } ");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				//Modified by Disnaka Jayasuriya on 2009-10-14 for date validation
				out.println("function print_report(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("		m_from_date = document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_to_date   = document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("   if (validate_date()){");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_BAL_REPORT_LEVEL_1&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&chk=\"+document.Form1.CHK_REQ.value+\"&finance_no=\"+document.Form1.TXT_FINANCE.value+\"&devision_code=\"+document.Form1.TXT_DIVISION_CODE.value+\"&acc_type_code=\"+document.Form1.TXT_ACC_CODE.value;"); 
				out.println("    	window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println(" 	} ");
				out.println("}");
					
					
			  	out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Contract Level Balance New </td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'></td>");  
					//out.println("<td width='10%' align='center'></td>");  
					//out.println("<td width='10%' align='center'></td>");  
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
					out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
					out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\">");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>  ");	
					out.println("</td> ");
					out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
					out.println(" <TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" > <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");	
					out.println("</td> ");
					out.println("<td width='5%' ><input type='checkbox' name='CHK_REQ' value=\"off\" onclick='change_state()' unckecked></td>");
					out.println("<td width='*%' ></td>");
					out.println("</tr> ");
					out.println("</table>");  
					out.println("<table class='table' width='100%'  >"); 
					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input><b>Division Code</b></DIV></td>"); 
					out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_DIVISION_CODE' maxlength='10' size='50' style='width:100' VALUE='AF' onblur=\"help_update_division()\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_division()\" ></td>"); 
					out.println("<td width='*%' align='left' ></td>");
					out.println("</tr>"); 
					out.println("</table>");  
					out.println("<table class='table' width='100%'  >"); 					
					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_FINANCE'  class=div_input><b>Finance No</b></DIV></td>"); 
					out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='20' size='10' style='width:100' onblur=\"help_finance_no()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_FIN' value=\"Help\" onClick=\"help_finance_no()\">");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_ACC_CODE'  class=div_input><b>Account Type Code </b></DIV></td>"); 
					out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_ACC_CODE' maxlength='10' size='10' style='width:100' onblur=\"help_update_account()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Help\" onClick=\"help_update_account()\">");
					out.println("</td>");
					//out.println("<td width='*%'><input class='but_input' type='button' name='BUT_VIEW_MAIN' value=\"View\" onClick=\"show_contract_level_balance_report()\"></td>"); 
					
					out.println("<td width='*%'><input class='but_input' type='button' name='BUT_VIEW_MAIN' style='width:150px' value=\"View Report\" onClick=\"print_report()\">&nbsp;<input class='but_input' type='button' style='width:150px' name='BUT_RUN_MAIN' value=\"Run Report\" onClick=\"run_report()\"></td>"); 
									
					out.println("</table>"); 
					
					out.println("<br><br>"); 
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>");  
					out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					out.flush();

			
			}
			
			else if(m_chksql.equals("LOAD_BAL_REPORT_LEVEL_1")){
				
				String m_string        = "";				
				String m_sql           = "";	
				String m_date_range    = "";
				String m_display_range = ""; 				
				String m_finance_no    = req.getParameter("finance_no");	
				String m_from_date     = req.getParameter("from_date");
				String m_to_date       = req.getParameter("to_date");
				String m_div_code      = req.getParameter("devision_code");
				String m_acc_type_code = req.getParameter("acc_type_code");
				String m_select        = req.getParameter("chk");
				
				if(m_select.equals("on")){
				m_display_range = "As On Date "+m_to_date+" ";
				}
				else{
				m_display_range = "From "+m_from_date+" To "+m_to_date+" ";
				}
					
				
				if(m_finance_no.equals("")){
				
				rs1= stmt1.executeQuery(" SELECT  "+
																		 " A.ACC_TYPE_CODE, "+
																		 " A.ACC_TYPE_DESC, "+
																		 " SUM(DECODE(A.DRCR_STATUS,'CR', A.TRN_AMOUNT*-1)) CR, "+
																		 " SUM(DECODE(A.DRCR_STATUS,'DR', A.TRN_AMOUNT)) DR "+
																		 " FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL_NEW A "+
																		 " WHERE A.ENT_USER = '"+m_username+"' "+
																		 " GROUP BY  A.ACC_TYPE_CODE,A.ACC_TYPE_DESC ");
				
				}	
				else{				
            if(m_select.equals("on")){
						
						rs1= stmt1.executeQuery(" SELECT  ACC_TYPE_CODE,ACC_TYPE_DESC,NVL(SUM(CR),0),NVL(SUM(DR),0) "+
                                    " FROM( "+
						                        "	SELECT  A.ACC_TYPE_CODE, "+
																		" C.ACC_TYPE_DESC, "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+ 
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE C "+
																		" WHERE A.DOCREFNO = B.INVOICE_NO "+
																		" AND   A.ACC_TYPE_CODE = C.ACC_TYPE_CODE "+ 
																		" AND  B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		/*" AND  B.INVOICE_NO NOT IN(SELECT C.INVOICE_NO "+
																		"         FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C) "+ */
																		" GROUP BY A.ACC_TYPE_CODE,C.ACC_TYPE_DESC,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//--ODI---------------
																		" SELECT  A.ACC_TYPE_CODE, "+
																		" D.ACC_TYPE_DESC, "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE D "+
																		" WHERE A.DOCREFNO      = C.ODI_REF_NO "+ 
																		" AND   B.INVOICE_NO    = C.INVOICE_NO "+
																		" AND   A.ACC_TYPE_CODE = D.ACC_TYPE_CODE "+ 
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" GROUP BY A.ACC_TYPE_CODE,D.ACC_TYPE_DESC,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//---RECEIPTS----------------
																		" SELECT C.ACC_TYPE_CODE, "+
																		" D.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+ 
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE D "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = D.ACC_TYPE_CODE "+ 
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NULL "+
																		" GROUP BY C.ACC_TYPE_CODE,D.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT C.ACC_TYPE_CODE, "+
																		" D.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE D "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = D.ACC_TYPE_CODE "+ 
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NOT NULL "+ 
																		" GROUP BY C.ACC_TYPE_CODE,D.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		
																		" UNION ALL "+
																		//-------PAYMENTS---------
																		" SELECT C.ACC_TYPE_CODE, "+
																		" E.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE E "+
																		" WHERE B.PAYMENT_NO = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = E.ACC_TYPE_CODE "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE = 'V' "+
																		" GROUP BY C.ACC_TYPE_CODE,E.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT E.ACC_TYPE_CODE, "+
																		" F.ACC_TYPE_DESC, "+
																		" SUM(DECODE(E.DRCR_STATUS,'CR',E.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(E.DRCR_STATUS,'DR',E.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D ,"+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT E,"+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE F "+
																		" WHERE A.APPLICATION_NO = B.REF_NO "+
																		" AND   B.SUS_REF_NO     = C.SUS_REF_NO "+
																		" AND   C.PAYMENT_NO     = D.PAYMENT_NO "+
																		" AND   D.PAYMENT_NO     = E.DOCREFNO "+
																		" AND   E.ACC_TYPE_CODE  = F.ACC_TYPE_CODE "+
																		" AND   B.REF_NO LIKE 'AP%' "+
																		" AND   D.ENTRY_TYPE <> 'V' "+
																		" AND   E.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   E.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   E.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" GROUP BY E.ACC_TYPE_CODE,F.ACC_TYPE_DESC,E.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT F.ACC_TYPE_CODE, "+
																		" G.ACC_TYPE_DESC, "+
																		" SUM(DECODE(F.DRCR_STATUS,'CR',F.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(F.DRCR_STATUS,'DR',F.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																		" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																		" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+ 
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT F, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE G "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO  "+
																		" AND   B.INVOICE_NO     = C.REF_NO   "+
																		" AND   C.SUS_REF_NO     = D.SUS_REF_NO "+
																		" AND   D.PAYMENT_NO     = E.PAYMENT_NO "+ 
																		" AND   F.ACC_TYPE_CODE  = G.ACC_TYPE_CODE "+ 
																		" AND   C.REF_NO LIKE 'PI%'  "+
																		" AND   F.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   F.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   F.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   E.ENTRY_TYPE <> 'V' "+
																		" GROUP BY F.ACC_TYPE_CODE,G.ACC_TYPE_DESC,F.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT C.ACC_TYPE_CODE, "+
																		" E.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM  "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT D, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE E "+
																		" WHERE B.PAYMENT_NO    = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = E.ACC_TYPE_CODE "+
																		" AND   B.SUS_REF_NO    = D.SUS_REF_NO "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE <> 'V' "+
																		" AND   D.REF_NO NOT LIKE 'PI%' "+
																		" AND   D.REF_NO NOT LIKE 'AP%' "+
																		" GROUP BY C.ACC_TYPE_CODE,E.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT  A.ACC_TYPE_CODE, "+
																		" B.ACC_TYPE_DESC, "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE B "+
																		" WHERE A.ACC_TYPE_CODE = B.ACC_TYPE_CODE "+
																		" AND  A.DOCREFNO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND  A.DOCREFNO NOT LIKE 'IN%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SP%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SR%' "+
																		" AND  A.DOCREFNO NOT LIKE 'OD%' "+
																		" GROUP BY A.ACC_TYPE_CODE,B.ACC_TYPE_DESC,A.DRCR_STATUS "+
																		" ) "+
                                    " GROUP BY ACC_TYPE_CODE,ACC_TYPE_DESC");
						
						}
						else{
						rs1= stmt1.executeQuery(" SELECT  ACC_TYPE_CODE,ACC_TYPE_DESC,NVL(SUM(CR),0),NVL(SUM(DR),0) "+
                                    " FROM( "+
						                        "	SELECT  A.ACC_TYPE_CODE, "+
																		" C.ACC_TYPE_DESC, "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+ 
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE C "+
																		" WHERE A.DOCREFNO = B.INVOICE_NO "+
																		" AND   A.ACC_TYPE_CODE = C.ACC_TYPE_CODE "+ 
																		" AND  B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		/*" AND  B.INVOICE_NO NOT IN(SELECT C.INVOICE_NO "+
																		"         FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C) "+ */
																		" GROUP BY A.ACC_TYPE_CODE,C.ACC_TYPE_DESC,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//--ODI---------------
																		" SELECT  A.ACC_TYPE_CODE, "+
																		" D.ACC_TYPE_DESC, "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE D "+
																		" WHERE A.DOCREFNO      = C.ODI_REF_NO "+ 
																		" AND   B.INVOICE_NO    = C.INVOICE_NO "+
																		" AND   A.ACC_TYPE_CODE = D.ACC_TYPE_CODE "+ 
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" GROUP BY A.ACC_TYPE_CODE,D.ACC_TYPE_DESC,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//---RECEIPTS----------------
																		" SELECT C.ACC_TYPE_CODE, "+
																		" D.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+ 
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE D "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = D.ACC_TYPE_CODE "+ 
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NULL "+
																		" GROUP BY C.ACC_TYPE_CODE,D.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT C.ACC_TYPE_CODE, "+
																		" D.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE D "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = D.ACC_TYPE_CODE "+ 
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NOT NULL "+ 
																		" GROUP BY C.ACC_TYPE_CODE,D.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		
																		" UNION ALL "+
																		//-------PAYMENTS---------
																		" SELECT C.ACC_TYPE_CODE, "+
																		" E.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE E "+
																		" WHERE B.PAYMENT_NO = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = E.ACC_TYPE_CODE "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE = 'V' "+
																		" GROUP BY C.ACC_TYPE_CODE,E.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT E.ACC_TYPE_CODE, "+
																		" F.ACC_TYPE_DESC, "+
																		" SUM(DECODE(E.DRCR_STATUS,'CR',E.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(E.DRCR_STATUS,'DR',E.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D ,"+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT E,"+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE F "+
																		" WHERE A.APPLICATION_NO = B.REF_NO "+
																		" AND   B.SUS_REF_NO     = C.SUS_REF_NO "+
																		" AND   C.PAYMENT_NO     = D.PAYMENT_NO "+
																		" AND   D.PAYMENT_NO     = E.DOCREFNO "+
																		" AND   E.ACC_TYPE_CODE  = F.ACC_TYPE_CODE "+
																		" AND   B.REF_NO LIKE 'AP%' "+
																		" AND   D.ENTRY_TYPE <> 'V' "+
																		" AND   E.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   E.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   E.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   E.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" GROUP BY E.ACC_TYPE_CODE,F.ACC_TYPE_DESC,E.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT F.ACC_TYPE_CODE, "+
																		" G.ACC_TYPE_DESC, "+
																		" SUM(DECODE(F.DRCR_STATUS,'CR',F.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(F.DRCR_STATUS,'DR',F.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																		" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																		" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+ 
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT F, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE G "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO  "+
																		" AND   B.INVOICE_NO     = C.REF_NO   "+
																		" AND   C.SUS_REF_NO     = D.SUS_REF_NO "+
																		" AND   D.PAYMENT_NO     = E.PAYMENT_NO "+ 
																		" AND   F.ACC_TYPE_CODE  = G.ACC_TYPE_CODE "+ 
																		" AND   C.REF_NO LIKE 'PI%'  "+
																		" AND   F.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   F.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   F.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   F.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   E.ENTRY_TYPE <> 'V' "+
																		" GROUP BY F.ACC_TYPE_CODE,G.ACC_TYPE_DESC,F.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT C.ACC_TYPE_CODE, "+
																		" E.ACC_TYPE_DESC, "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR "+
																		" FROM  "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT D, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE E "+
																		" WHERE B.PAYMENT_NO    = C.DOCREFNO "+
																		" AND   C.ACC_TYPE_CODE = E.ACC_TYPE_CODE "+
																		" AND   B.SUS_REF_NO    = D.SUS_REF_NO "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE <> 'V' "+
																		" AND   D.REF_NO NOT LIKE 'PI%' "+
																		" AND   D.REF_NO NOT LIKE 'AP%' "+
																		" GROUP BY C.ACC_TYPE_CODE,E.ACC_TYPE_DESC,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT  A.ACC_TYPE_CODE, "+
																		" B.ACC_TYPE_DESC, "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE B "+
																		" WHERE A.ACC_TYPE_CODE = B.ACC_TYPE_CODE "+
																		" AND  A.DOCREFNO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND  A.DOCREFNO NOT LIKE 'IN%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SP%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SR%' "+
																		" AND  A.DOCREFNO NOT LIKE 'OD%' "+
																		" GROUP BY A.ACC_TYPE_CODE,B.ACC_TYPE_DESC,A.DRCR_STATUS "+
																		" ) "+
                                    " GROUP BY ACC_TYPE_CODE,ACC_TYPE_DESC");
								}
								
				}
					

						  String m_acc_code="",m_acc_desc="";
			        boolean m_dataflag=false;							
		          boolean mflag=true;							
				 	    String client_code = "";
					    boolean more = rs1.next();
						  double m_cr_tot=0,m_dr_tot=0;	
							double m_differ=0;
							double m_cr_tot_1=0,m_dr_tot_1=0;
							
							
								
					
							out.println("<HTML><HEAD><TITLE>Contract Level Balance New </TITLE></HEAD>");
					 		out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 		out.println("<SCRIPT language=\"JavaScript\">"); 
					 		out.println("	function show_account_type(m_acc_code){");
					 		out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 		out.println("   window.open(m_url,'popupwin6','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 		out.println("	}");
					 		out.println("	function show_account_entry(m_trans_code){");
					 		out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
					 		out.println("   window.open(m_url,'popupwin5','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 		out.println("	}");
					 		out.println("	function show_second_level_drill(m_acc_type_code,m_acc_desc){");
							out.println(" 	 m_chk = '"+m_select+"'; ");	
							out.println(" 	 m_finance = '"+m_finance_no+"'; ");	
			     		out.println(" 	 m_from_date = '"+m_from_date+"'; ");
			     		out.println(" 	 m_to_date = '"+m_to_date+"'; ");
					 		out.println(" 	 m_div_code = '"+m_div_code+"'; ");
					 		out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_BAL_REPORT_LEVEL_2&chk=\"+m_chk+\"&finance_no=\"+m_finance+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&div_code=\"+m_div_code+\"&acc_type_code=\"+m_acc_type_code+\"&acc_desc=\"+m_acc_desc;");	
							out.println("   window.open(m_url,'popupwin9','left=150,top=100,width=600,height=600, menubar=0,scrollbars=1,height=450,width=800');	");//
					 		out.println("	}");
								
								out.println("function mouse_over(id){");
								out.println("document.getElementById(id).style.textDecoration='underline';");
								out.println("}");
								
								out.println("function mouse_out(id){");
								out.println("document.getElementById(id).style.textDecoration='none';");
								out.println("}");								
									
					 		out.println("</SCRIPT>");
					 		out.println("<br>");
		 			 		out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
					 		out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 		out.println("<FORM NAME='Form1' method='post'>"); 							
					 		out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 		out.println("<TR><TD align='Center' ><B> Contract Level Balance New "+m_display_range+"</B></TD></TR>");// To "+m_to_date+" 
					 		out.println("</TABLE>");
					 		out.println("<hr>");	
					 		out.println("<BR>");	
						
							if(!more){
					
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");	

					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Account Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Credit</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Balance</b></DIV></td>");
						out.println("<td width='2%' align='right' ><DIV class=div_input></DIV></td>");
						out.println("</tr>"); 
					  m_dataflag=true; 
					}
					int j = 1; 
					while(more){
					  	
							m_acc_code=rs1.getString(1); 	
							m_acc_desc=rs1.getString(2); 	
							double m_dr_amount=0,m_cr_amount=0;							
							m_cr_amount = rs1.getDouble(3);
							m_dr_amount = rs1.getDouble(4); 
							
							
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
								out.println("<td width='1%'></td>"); 
								out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(1)+"')\" style='cursor:hand' onmouseover=\"mouse_over('account_"+j+"')\" onmouseout=\"mouse_out('account_"+j+"')\" id=\"account_"+j+"\">"+rs1.getString(1)+"</td>");
								out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");

							m_differ = m_dr_amount+m_cr_amount;
							m_dr_tot_1=m_dr_tot_1+m_dr_amount;
							m_cr_tot_1=m_cr_tot_1+m_cr_amount;
							
							out.println("<td width='12%' align='right' class=div_input onClick=\"\"  >"+nf.format(Math.abs(m_dr_amount))+"</u></td>");
							out.println("<td width='12%' align='right' class=div_input onClick=\"\"  >"+nf.format(Math.abs(m_cr_amount))+"</u></td>");
							out.println("<td width='12%' align='right' class=div_input onmouseover=\"mouse_over('balance_"+j+"')\" onmouseout=\"mouse_out('balance_"+j+"')\" onClick=\"show_second_level_drill('"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='cursor:hand' id=\"balance_"+j+"\" >"+nf.format(Math.abs(m_dr_amount+m_cr_amount))+" </td>");
							if(Math.abs(m_dr_amount) < Math.abs(m_cr_amount)){
							out.println("<td width='2%' align='center' class=div_input>Cr</td>");
							}else{
							out.println("<td width='2%' align='center' class=div_input>Dr</td>");
							}
							out.println("</tr>");
							j++;
							more = rs1.next();
							
							
					}	
					       
      	 		out.println("</table>");
						if(m_dataflag) {	
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr class=pdn_txtpos2>");
								out.println("<td width='33%' align='center'> <B>Total</B> </td>"); 
								out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(Math.abs(m_dr_tot_1))+"</b></DIV></td>"); 
								out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(Math.abs(m_cr_tot_1))+"</b></DIV></td>");
								out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(Math.abs(m_dr_tot_1+m_cr_tot_1))+"</b></DIV></td>");
								out.println("<td width='2%' align='center' class=div_input>&nbsp;</td>");
								out.println("</tr>");
								out.println("</table>");
								}
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
}

		else if(m_chksql.equals("LOAD_ACC_TYPE_CODE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_acc_type_code=req.getParameter("acc_type_code");	
					
					
					 rs= stmt1.executeQuery(" SELECT "+
						  "  ACC_TYPE_CODE,"+//1
						  "  NVL(ACC_TYPE_DESC,'-'),"+//2
						  "  NVL(ACC_TYPE_CATEGORY,'-'),"+//3
						  "  NVL(DECODE(STATUS,'Y','Yes','N','No'),'-'),"+//4
						  "  INITCAP(DECODE(DIVISION_CODE,'AF','ASSET FINANCE - LEASING / LOANS','AD','ADMINISTRATION','FA','FACTORING','MISF','MANAGEMENT INFORMATION')) "+//5
						" FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE "+
						" WHERE ACC_TYPE_CODE='"+m_acc_type_code+"' ");

				boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Account Type Details - Account Type Code: "+m_acc_type_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Account Type Details - Account Type Code: "+m_acc_type_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Account Type Code: "+m_acc_type_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Account Type Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Account Type Category</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<tr>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("LOAD_BAL_REPORT_LEVEL_2")){
				
				String m_string        = "";				
				String m_sql           = "";
				String m_date_range    = "";
				String m_display_range = "";
							
				String m_from_date     = req.getParameter("from_date");
				String m_to_date       = req.getParameter("to_date");
				String m_div_code      = req.getParameter("div_code");
				String m_acc_type_code = req.getParameter("acc_type_code");
				String m_acc_desc      = req.getParameter("acc_desc");
				String m_finance_no    = req.getParameter("finance_no"); 
				String m_select        = req.getParameter("chk");
				
				
				if(m_select.equals("on")){
				     
							m_display_range = "As On Date "+m_to_date+" ";
															
				    }
				    else{
				    m_display_range = "From "+m_from_date+" To "+m_to_date+" ";
											
													
							
					}
																		
					if(m_finance_no.equals("")){
							rs1= stmt1.executeQuery(" SELECT A.FINANCE_NO, "+
																		  " SUM(DECODE(A.DRCR_STATUS,'CR',A.TRN_AMOUNT*-1,0)) CR, "+
																		  " SUM(DECODE(A.DRCR_STATUS,'DR',A.TRN_AMOUNT,0)) DR,   "+
																		  " SUM(DECODE(A.DRCR_STATUS,'DR',A.TRN_AMOUNT,0))+ SUM(DECODE(A.DRCR_STATUS,'CR',A.TRN_AMOUNT*-1,0))   "+
																		  " FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL_NEW A "+
																			" WHERE A.ENT_USER = '"+m_username+"' "+
																			" AND A.ACC_TYPE_CODE = '"+m_acc_type_code+"'"+
																		  " GROUP BY A.FINANCE_NO " );
								
				   }
						else{
						
					if(m_select.equals("on")){
						
						rs1= stmt1.executeQuery(" SELECT  FINANCE_NO,NVL(SUM(CR),0),NVL(SUM(DR),0),NVL(SUM(DR+CR),0) "+
                                    " FROM( "+
						                        "	SELECT  SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A ,"+ 
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																		" WHERE A.DOCREFNO = B.INVOICE_NO "+
																		" AND  B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		/*" AND  B.INVOICE_NO NOT IN(SELECT C.INVOICE_NO "+
																		"         FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C) "+ */
																		" GROUP BY B.FINANCE_NO,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//--ODI---------------
																		" SELECT SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO"+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
																		" WHERE A.DOCREFNO      = C.ODI_REF_NO "+ 
																		" AND   B.INVOICE_NO    = C.INVOICE_NO "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" GROUP BY B.FINANCE_NO,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//---RECEIPTS----------------
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+ 
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NULL "+
																		" GROUP BY A.FINANCE_NO,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NOT NULL "+ 
																		" GROUP BY A.FINANCE_NO,C.DRCR_STATUS "+
																		
																		
																		" UNION ALL "+
																		//-------PAYMENTS---------
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO"+
																		" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE B.PAYMENT_NO = C.DOCREFNO "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE = 'V' "+
																		" GROUP BY B.FINANCE_NO,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(E.DRCR_STATUS,'CR',E.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(E.DRCR_STATUS,'DR',E.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO"+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D ,"+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT E "+
																		" WHERE A.APPLICATION_NO = B.REF_NO "+
																		" AND   B.SUS_REF_NO     = C.SUS_REF_NO "+
																		" AND   C.PAYMENT_NO     = D.PAYMENT_NO "+
																		" AND   D.PAYMENT_NO     = E.DOCREFNO "+
																		" AND   B.REF_NO LIKE 'AP%' "+
																		" AND   D.ENTRY_TYPE <> 'V' "+
																		" AND   E.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   E.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   E.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" GROUP BY A.FINANCE_NO,E.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(F.DRCR_STATUS,'CR',F.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(F.DRCR_STATUS,'DR',F.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO"+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																		" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																		" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+ 
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT F "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO  "+
																		" AND   B.INVOICE_NO     = C.REF_NO   "+
																		" AND   C.SUS_REF_NO     = D.SUS_REF_NO "+
																		" AND   D.PAYMENT_NO     = E.PAYMENT_NO "+ 
																		" AND   C.REF_NO LIKE 'PI%'  "+
																		" AND   F.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   F.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   F.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   E.ENTRY_TYPE <> 'V' "+
																		" GROUP BY A.FINANCE_NO,F.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO "+
																		" FROM  "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT D "+
																		" WHERE B.PAYMENT_NO    = C.DOCREFNO "+
																		" AND   B.SUS_REF_NO    = D.SUS_REF_NO "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE <> 'V' "+
																		" AND   D.REF_NO NOT LIKE 'PI%' "+
																		" AND   D.REF_NO NOT LIKE 'AP%' "+
																		" GROUP BY B.FINANCE_NO,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT   SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" A.DOCREFNO  FINANCE_NO "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+
																		" WHERE A.DOCREFNO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND  A.DOCREFNO NOT LIKE 'IN%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SP%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SR%' "+
																		" AND  A.DOCREFNO NOT LIKE 'OD%' "+
																		" GROUP BY A.DOCREFNO,A.DRCR_STATUS "+
																		" ) "+
                                    " GROUP BY FINANCE_NO ");
						
						}
						else{
						rs1= stmt1.executeQuery(" SELECT  FINANCE_NO,NVL(SUM(CR),0),NVL(SUM(DR),0),NVL(SUM(DR+CR),0) "+
                                    " FROM( "+
						                        "	SELECT  SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A ,"+ 
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																		" WHERE A.DOCREFNO = B.INVOICE_NO "+
																		" AND  B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		/*" AND  B.INVOICE_NO NOT IN(SELECT C.INVOICE_NO "+
																		"         FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C) "+ */
																		" GROUP BY B.FINANCE_NO,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//--ODI---------------
																		" SELECT SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO"+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
																		" WHERE A.DOCREFNO      = C.ODI_REF_NO "+ 
																		" AND   B.INVOICE_NO    = C.INVOICE_NO "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" GROUP BY B.FINANCE_NO,A.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		//---RECEIPTS----------------
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+ 
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NULL "+
																		" GROUP BY A.FINANCE_NO,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NOT NULL "+ 
																		" GROUP BY A.FINANCE_NO,C.DRCR_STATUS "+
																		
																		
																		" UNION ALL "+
																		//-------PAYMENTS---------
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO"+
																		" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE B.PAYMENT_NO = C.DOCREFNO "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE = 'V' "+
																		" GROUP BY B.FINANCE_NO,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(E.DRCR_STATUS,'CR',E.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(E.DRCR_STATUS,'DR',E.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO"+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D ,"+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT E "+
																		" WHERE A.APPLICATION_NO = B.REF_NO "+
																		" AND   B.SUS_REF_NO     = C.SUS_REF_NO "+
																		" AND   C.PAYMENT_NO     = D.PAYMENT_NO "+
																		" AND   D.PAYMENT_NO     = E.DOCREFNO "+
																		" AND   B.REF_NO LIKE 'AP%' "+
																		" AND   D.ENTRY_TYPE <> 'V' "+
																		" AND   E.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   E.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   E.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   E.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" GROUP BY A.FINANCE_NO,E.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(F.DRCR_STATUS,'CR',F.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(F.DRCR_STATUS,'DR',F.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO"+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																		" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																		" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+ 
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT F "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO  "+
																		" AND   B.INVOICE_NO     = C.REF_NO   "+
																		" AND   C.SUS_REF_NO     = D.SUS_REF_NO "+
																		" AND   D.PAYMENT_NO     = E.PAYMENT_NO "+ 
																		" AND   C.REF_NO LIKE 'PI%'  "+
																		" AND   F.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   F.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   F.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   F.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   E.ENTRY_TYPE <> 'V' "+
																		" GROUP BY A.FINANCE_NO,F.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO "+
																		" FROM  "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT D "+
																		" WHERE B.PAYMENT_NO    = C.DOCREFNO "+
																		" AND   B.SUS_REF_NO    = D.SUS_REF_NO "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE <> 'V' "+
																		" AND   D.REF_NO NOT LIKE 'PI%' "+
																		" AND   D.REF_NO NOT LIKE 'AP%' "+
																		" GROUP BY B.FINANCE_NO,C.DRCR_STATUS "+
																		
																		" UNION ALL "+
																		
																		" SELECT   SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" A.DOCREFNO  FINANCE_NO "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+
																		" WHERE A.DOCREFNO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND  A.DOCREFNO NOT LIKE 'IN%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SP%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SR%' "+
																		" AND  A.DOCREFNO NOT LIKE 'OD%' "+
																		" GROUP BY A.DOCREFNO,A.DRCR_STATUS "+
																		" ) "+
                                    " GROUP BY FINANCE_NO ");
								}
						
						
					}
				
				
					 boolean mflag=true;							
		 	     String client_code = "";
			     boolean more = rs1.next();
				   double m_cr_tot=0,m_dr_tot=0;	
				   double m_cr_amt=0,m_dr_amt=0,m_tot=0;
						
					 out.println("<HTML><HEAD><TITLE>Contract Level Balance New </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin4','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_account_entry(m_trans_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
					 out.println("   window.open(m_url,'popupwin3','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_third_level_drill(fin_no){");
					 out.println(" 	 m_chk = '"+m_select+"'; ");
			     out.println(" 	 m_from_date = '"+m_from_date+"'; ");
			     out.println(" 	 m_to_date = '"+m_to_date+"'; ");
					 out.println(" 	 m_div_code = '"+m_div_code+"'; ");	
					 out.println(" 	 m_acc_type_code = '"+m_acc_type_code+"'; ");		
					 out.println("   trans_desc   = '"+m_acc_desc+"'; ");
			     out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_BAL_REPORT_LEVEL_3&chk=\"+m_chk+\"&trans_desc=\"+trans_desc+\"&fin_no=\"+fin_no+\"&to_date=\"+m_to_date+\"&from_date=\"+m_from_date+\"&div_code=\"+m_div_code+\"&acc_type_code=\"+m_acc_type_code+\"\";");	//
					// out.println("alert(m_url);"); 
						out.println("   window.open(m_url,'popupwin2','top=150,left=100,menubar=0,scrollbars=1,height=550,width=800 resizeable=1');	");
					 
						out.println("	}");	
						
						out.println("function mouse_over(id){");
						out.println("document.getElementById(id).style.textDecoration='underline';");
						out.println("}");
						
						
						out.println("function mouse_out(id){");
						out.println("document.getElementById(id).style.textDecoration='none';");
						out.println("}");
					 out.println("</SCRIPT>");
						
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					  out.println("<TR><TD align='Center' ><B> Contract Level Balance New "+m_display_range+" </B></TD></TR>");//To "+m_to_date+"
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> "+m_acc_desc+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						/*out.println("<td width='12%' ><DIV class=div_input><b>Transaction Code</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Credit</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Balance</b></DIV></td>"); 
						*/
						out.println("<td width='12%' ><DIV class=div_input><b>Agreement No</b></DIV></td>"); 
						//out.println("<td width='20%' ><DIV class=div_input><b>Refrence No</b></DIV></td>"); 
					//	out.println("<td width='20%' ><DIV class=div_input><b>Transaction Date</b></DIV></td>");
						out.println("<td width='20%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>");
						out.println("<td width='20%' align='right'><DIV class=div_input><b>Credit</b></DIV></td>");
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Amount</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					double m_dr_amount=0,m_dr_tot_2=0;
					double m_cr_amount=0,m_cr_tot_2=0;
					
					int j = 1;
					while(more){
								
				/*
				if(rs1.getString(4).equals("DR")){					
					m_dr_amount = rs1.getDouble(2);
					}else{
					m_cr_amount = rs1.getDouble(2);
					}
						*/	
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='1%'></td>"); 
													
							out.println("<td width='20%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand' id=\"finance_"+j+"\" onmouseover=\"mouse_over('finance_"+j+"')\" onmouseout=\"mouse_out('finance_"+j+"')\">"+rs1.getString(1)+"</u></td>");
							//out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
							//out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
							
							out.println("<td width='20%' class=div_input align='right' >"+nf.format(Math.abs(rs1.getDouble(3)))+"</td>");
							
             out.println("<td width='20%' class=div_input align='right' >"+nf.format(Math.abs(rs1.getDouble(2)))+"</td>");
						
							
							out.println("<td width='20%' class=div_input align='right' style='cursor:hand' onclick=\"show_third_level_drill('"+rs1.getString(1)+"')\" onmouseover=\"mouse_over('bal_"+j+"')\" onmouseout=\"mouse_out('bal_"+j+"')\" id=\"bal_"+j+"\" >"+nf.format(Math.abs(rs1.getDouble(4)))+"</td>");							
							out.println("</tr>");
							m_dr_tot_2 = m_dr_tot_2+rs1.getDouble(4);
							m_dr_tot = m_dr_tot+rs1.getDouble(3);
							m_cr_tot = m_cr_tot+rs1.getDouble(2);
							//m_cr_tot_2 = m_cr_tot_2+m_cr_amount;
							j++;
							more = rs1.next();
							
							// m_dr_amount=0;
					    // m_cr_amount=0;
								}
													 				
								out.println("<tr class=pdn_txtpos2>");
								out.println("<td width='1%'></td>");
								out.println("<td width='*%' align='right'> <B>Total</B> </td>"); 
								out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(Math.abs(m_dr_tot))+"</b></DIV></td>"); 
								out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(Math.abs(m_cr_tot))+"</b></DIV></td>");
								out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(Math.abs(m_dr_tot_2))+"</b></DIV></td>");
								out.println("</tr>");
								out.println("</table>");
							out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");


			
			}
			
			else if(m_chksql.equals("LOAD_BAL_REPORT_LEVEL_3")){
				   
				String m_string="";				
				String m_sql="";	
				String m_display_range ="";
				String m_date_range = "";
							
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_div_code=req.getParameter("div_code");				
				String m_acc_type_code=req.getParameter("acc_type_code");
				String m_ref_no=req.getParameter("ref_no");
				String m_finance_no=req.getParameter("fin_no");
				String m_select=req.getParameter("chk");
				String m_trans_desc=req.getParameter("trans_desc");
				
				
			//--------------------------TRAIL BALANCE REPORT - LEVEL 3 -------------------------------
			if(m_select.equals("on")){
				    	m_display_range = "As On Date "+m_to_date+" ";
				    }
				    else{
				      m_display_range = "From "+m_from_date+" To "+m_to_date+" ";
					}
					
			if(m_finance_no.equals("")){
			
			rs1= stmt1.executeQuery(" SELECT  A.FINANCE_NO, "+
															" A.DOCREFNO,   "+
															" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRN_AMOUNT*-1,0)) CR, "+
															" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRN_AMOUNT,0)) DR,  "+
															" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRN_AMOUNT,0))+ SUM(DECODE(A.DRCR_STATUS,'CR',A.TRN_AMOUNT*-1,0)) , "+
															" TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),  "+
															" A.PROC_DESC, "+
															" A.TRANSACTION_CODE, "+
															" A.ACC_TYPE_CODE, "+
															" A.CORR_ACC_NO ,"+
															" A.TRN_DATE  "+
															" FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL_NEW A "+
															" WHERE A.FINANCE_NO = '"+m_finance_no+"' "+
															" AND   A.ENT_USER   = '"+m_username+"' "+
															" AND   A.ACC_TYPE_CODE = '"+m_acc_type_code+"' "+
															" GROUP BY  A.FINANCE_NO,A.DOCREFNO,A.TRN_DATE,A.PROC_DESC,A.TRANSACTION_CODE,A.ACC_TYPE_CODE,A.CORR_ACC_NO ");
															
															
						}else{
						
						
						if(m_select.equals("on")){
						
						rs1= stmt1.executeQuery(" SELECT  FINANCE_NO,DOCREFNO,NVL(SUM(CR),0),NVL(SUM(DR),0),NVL(SUM(DR+CR),0),TRN_DATE,PROC_DESC,TRANSACTION_CODE,ACC_TYPE_CODE,CORR_ACC_NO,TRNDATE "+
                                    " FROM( "+
						                        "	SELECT   "+
																		" A.DOCREFNO ,   "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(A.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" A.PROC_DESC, "+
																		" A.TRANSACTION_CODE, "+
																		" A.ACC_TYPE_CODE, "+
																		" A.CORR_ACC_NO, "+
																		" A.TRNDATE "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A ,"+ 
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																		" WHERE A.DOCREFNO = B.INVOICE_NO "+
																		" AND  B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		/*" AND  B.INVOICE_NO NOT IN(SELECT C.INVOICE_NO "+
																		"         FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C) "+ */
																		" GROUP BY B.FINANCE_NO,A.DOCREFNO,A.TRNDATE,A.DRCR_STATUS,A.PROC_DESC,A.TRANSACTION_CODE,A.ACC_TYPE_CODE,A.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		//--ODI---------------
																		"	SELECT   "+
																		" A.DOCREFNO ,   "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(A.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" A.PROC_DESC, "+
																		" A.TRANSACTION_CODE, "+
																		" A.ACC_TYPE_CODE, "+
																		" A.CORR_ACC_NO, "+
																		" A.TRNDATE "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
																		" WHERE A.DOCREFNO      = C.ODI_REF_NO "+ 
																		" AND   B.INVOICE_NO    = C.INVOICE_NO "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" GROUP BY B.FINANCE_NO,A.DOCREFNO,A.TRNDATE,A.DRCR_STATUS,A.PROC_DESC,A.TRANSACTION_CODE,A.ACC_TYPE_CODE,A.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		//---RECEIPTS----------------
																		"	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+ 
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NULL "+
																		" GROUP BY A.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																	  "	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE , "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NOT NULL "+ 
																		" GROUP BY A.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		
																		" UNION ALL "+
																		//-------PAYMENTS---------
																		"	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE B.PAYMENT_NO = C.DOCREFNO "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE = 'V' "+
																		" GROUP BY B.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" E.DOCREFNO ,   "+
																		" SUM(DECODE(E.DRCR_STATUS,'CR',E.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(E.DRCR_STATUS,'DR',E.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(E.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" E.PROC_DESC, "+
																		" E.TRANSACTION_CODE, "+
																		" E.ACC_TYPE_CODE, "+
																		" E.CORR_ACC_NO, "+
																		" E.TRNDATE "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D ,"+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT E "+
																		" WHERE A.APPLICATION_NO = B.REF_NO "+
																		" AND   B.SUS_REF_NO     = C.SUS_REF_NO "+
																		" AND   C.PAYMENT_NO     = D.PAYMENT_NO "+
																		" AND   D.PAYMENT_NO     = E.DOCREFNO "+
																		" AND   B.REF_NO LIKE 'AP%' "+
																		" AND   D.ENTRY_TYPE <> 'V' "+
																		" AND   E.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   E.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   E.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" GROUP BY A.FINANCE_NO,E.DOCREFNO,E.TRNDATE,E.DRCR_STATUS,E.PROC_DESC,E.TRANSACTION_CODE,E.ACC_TYPE_CODE,E.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" F.DOCREFNO ,   "+
																		" SUM(DECODE(F.DRCR_STATUS,'CR',F.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(F.DRCR_STATUS,'DR',F.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(F.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" F.PROC_DESC, "+
																		" F.TRANSACTION_CODE, "+
																		" F.ACC_TYPE_CODE, "+
																		" F.CORR_ACC_NO, "+
																		" F.TRNDATE "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																		" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																		" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+ 
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT F "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO  "+
																		" AND   B.INVOICE_NO     = C.REF_NO   "+
																		" AND   C.SUS_REF_NO     = D.SUS_REF_NO "+
																		" AND   D.PAYMENT_NO     = E.PAYMENT_NO "+ 
																		" AND   C.REF_NO LIKE 'PI%'  "+
																		" AND   F.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   F.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   F.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   E.ENTRY_TYPE <> 'V' "+
																		" GROUP BY A.FINANCE_NO,F.DOCREFNO,F.TRNDATE,F.DRCR_STATUS,F.PROC_DESC,F.TRANSACTION_CODE,F.ACC_TYPE_CODE,F.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM  "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT D "+
																		" WHERE B.PAYMENT_NO    = C.DOCREFNO "+
																		" AND   B.SUS_REF_NO    = D.SUS_REF_NO "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE <> 'V' "+
																		" AND   D.REF_NO NOT LIKE 'PI%' "+
																		" AND   D.REF_NO NOT LIKE 'AP%' "+
																		" GROUP BY B.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" A.DOCREFNO ,   "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" A.DOCREFNO FINANCE_NO, "+
																		" TO_CHAR(A.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" A.PROC_DESC, "+
																		" A.TRANSACTION_CODE, "+
																		" A.ACC_TYPE_CODE, "+
																		" A.CORR_ACC_NO, "+
																		" A.TRNDATE "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+
																		" WHERE A.DOCREFNO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND  A.DOCREFNO NOT LIKE 'IN%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SP%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SR%' "+
																		" AND  A.DOCREFNO NOT LIKE 'OD%' "+
																		" GROUP BY A.DOCREFNO,A.TRNDATE,A.DRCR_STATUS,A.PROC_DESC,A.TRANSACTION_CODE,A.ACC_TYPE_CODE,A.CORR_ACC_NO "+
																		" ) "+
                                    " GROUP BY FINANCE_NO ,DOCREFNO,TRN_DATE,PROC_DESC,TRANSACTION_CODE,ACC_TYPE_CODE,CORR_ACC_NO,TRNDATE ");
						
						}
						else{
						rs1= stmt1.executeQuery(" SELECT  FINANCE_NO,DOCREFNO,NVL(SUM(CR),0),NVL(SUM(DR),0),NVL(SUM(DR+CR),0),TRN_DATE,PROC_DESC,TRANSACTION_CODE,ACC_TYPE_CODE,CORR_ACC_NO,TRNDATE "+
                                    " FROM( "+
						                        "	SELECT   "+
																		" A.DOCREFNO ,   "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(A.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" A.PROC_DESC, "+
																		" A.TRANSACTION_CODE, "+
																		" A.ACC_TYPE_CODE, "+
																		" A.CORR_ACC_NO, "+
																		" A.TRNDATE "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A ,"+ 
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																		" WHERE A.DOCREFNO = B.INVOICE_NO "+
																		" AND  B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND  A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		/*" AND  B.INVOICE_NO NOT IN(SELECT C.INVOICE_NO "+
																		"         FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C) "+ */
																		" GROUP BY B.FINANCE_NO,A.DOCREFNO,A.TRNDATE,A.DRCR_STATUS,A.PROC_DESC,A.TRANSACTION_CODE,A.ACC_TYPE_CODE,A.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		//--ODI---------------
																		"	SELECT   "+
																		" A.DOCREFNO ,   "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(A.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" A.PROC_DESC, "+
																		" A.TRANSACTION_CODE, "+
																		" A.ACC_TYPE_CODE, "+
																		" A.CORR_ACC_NO, "+
																		" A.TRNDATE "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
																		" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
																		" WHERE A.DOCREFNO      = C.ODI_REF_NO "+ 
																		" AND   B.INVOICE_NO    = C.INVOICE_NO "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" GROUP BY B.FINANCE_NO,A.DOCREFNO,A.TRNDATE,A.DRCR_STATUS,A.PROC_DESC,A.TRANSACTION_CODE,A.ACC_TYPE_CODE,A.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		//---RECEIPTS----------------
																		"	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+ 
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NULL "+
																		" GROUP BY A.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																	  "	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE , "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
																		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE A.REC_NO = B.REC_NO "+
																		" AND   B.REC_NO = C.DOCREFNO "+
																		" AND   A.FINANCE_NO    = '"+m_finance_no+"' "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   GROUP_REC_NO IS NOT NULL "+ 
																		" GROUP BY A.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		
																		" UNION ALL "+
																		//-------PAYMENTS---------
																		"	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C "+
																		" WHERE B.PAYMENT_NO = C.DOCREFNO "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE = 'V' "+
																		" GROUP BY B.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" E.DOCREFNO ,   "+
																		" SUM(DECODE(E.DRCR_STATUS,'CR',E.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(E.DRCR_STATUS,'DR',E.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(E.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" E.PROC_DESC, "+
																		" E.TRANSACTION_CODE, "+
																		" E.ACC_TYPE_CODE, "+
																		" E.CORR_ACC_NO, "+
																		" E.TRNDATE "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D ,"+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT E "+
																		" WHERE A.APPLICATION_NO = B.REF_NO "+
																		" AND   B.SUS_REF_NO     = C.SUS_REF_NO "+
																		" AND   C.PAYMENT_NO     = D.PAYMENT_NO "+
																		" AND   D.PAYMENT_NO     = E.DOCREFNO "+
																		" AND   B.REF_NO LIKE 'AP%' "+
																		" AND   D.ENTRY_TYPE <> 'V' "+
																		" AND   E.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   E.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   E.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   E.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" GROUP BY A.FINANCE_NO,E.DOCREFNO,E.TRNDATE,E.DRCR_STATUS,E.PROC_DESC,E.TRANSACTION_CODE,E.ACC_TYPE_CODE,E.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" F.DOCREFNO ,   "+
																		" SUM(DECODE(F.DRCR_STATUS,'CR',F.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(F.DRCR_STATUS,'DR',F.TRNAMOUNT,0)) DR, "+
																		" A.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(F.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" F.PROC_DESC, "+
																		" F.TRANSACTION_CODE, "+
																		" F.ACC_TYPE_CODE, "+
																		" F.CORR_ACC_NO, "+
																		" F.TRNDATE "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																		" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																		" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+ 
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT F "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO  "+
																		" AND   B.INVOICE_NO     = C.REF_NO   "+
																		" AND   C.SUS_REF_NO     = D.SUS_REF_NO "+
																		" AND   D.PAYMENT_NO     = E.PAYMENT_NO "+ 
																		" AND   C.REF_NO LIKE 'PI%'  "+
																		" AND   F.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   F.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   F.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   A.FINANCE_NO  = '"+m_finance_no+"'  "+
																		" AND   F.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   E.ENTRY_TYPE <> 'V' "+
																		" GROUP BY A.FINANCE_NO,F.DOCREFNO,F.TRNDATE,F.DRCR_STATUS,F.PROC_DESC,F.TRANSACTION_CODE,F.ACC_TYPE_CODE,F.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" C.DOCREFNO ,   "+
																		" SUM(DECODE(C.DRCR_STATUS,'CR',C.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(C.DRCR_STATUS,'DR',C.TRNAMOUNT,0)) DR, "+
																		" B.FINANCE_NO FINANCE_NO, "+
																		" TO_CHAR(C.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" C.PROC_DESC, "+
																		" C.TRANSACTION_CODE, "+
																		" C.ACC_TYPE_CODE, "+
																		" C.CORR_ACC_NO, "+
																		" C.TRNDATE "+
																		" FROM  "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
																		" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
																		" "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT C, "+
																		" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT D "+
																		" WHERE B.PAYMENT_NO    = C.DOCREFNO "+
																		" AND   B.SUS_REF_NO    = D.SUS_REF_NO "+
																		" AND   C.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   C.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND   C.ACC_TYPE_CODE  LIKE '%"+m_acc_type_code+"%' "+
																		" AND   B.FINANCE_NO = '"+m_finance_no+"' "+
																		" AND   C.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND   B.ENTRY_TYPE <> 'V' "+
																		" AND   D.REF_NO NOT LIKE 'PI%' "+
																		" AND   D.REF_NO NOT LIKE 'AP%' "+
																		" GROUP BY B.FINANCE_NO,C.DOCREFNO,C.TRNDATE,C.DRCR_STATUS,C.PROC_DESC,C.TRANSACTION_CODE,C.ACC_TYPE_CODE,C.CORR_ACC_NO "+
																		
																		" UNION ALL "+
																		
																		"	SELECT   "+
																		" A.DOCREFNO ,   "+
																		" SUM(DECODE(A.DRCR_STATUS,'CR',A.TRNAMOUNT*-1,0)) CR, "+ 
																		" SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,0)) DR, "+
																		" A.DOCREFNO FINANCE_NO, "+
																		" TO_CHAR(A.TRNDATE,'DD-MM-YYYY') TRN_DATE, "+
																		" A.PROC_DESC, "+
																		" A.TRANSACTION_CODE, "+
																		" A.ACC_TYPE_CODE, "+
																		" A.CORR_ACC_NO, "+
																		" A.TRNDATE "+
																		" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+
																		" WHERE A.DOCREFNO = '"+m_finance_no+"' "+
																		" AND   A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND  A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																		" AND  A.ACC_TYPE_CODE LIKE '%"+m_acc_type_code+"%' "+
																		" AND  A.DIVISION_CODE LIKE '%"+m_div_code+"%' "+
																		" AND  A.DOCREFNO NOT LIKE 'IN%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SP%' "+
																		" AND  A.DOCREFNO NOT LIKE 'SR%' "+
																		" AND  A.DOCREFNO NOT LIKE 'OD%' "+
																		" GROUP BY A.DOCREFNO,A.TRNDATE,A.DRCR_STATUS,A.PROC_DESC,A.TRANSACTION_CODE,A.ACC_TYPE_CODE,A.CORR_ACC_NO "+
																		" ) "+
                                    " GROUP BY FINANCE_NO ,DOCREFNO,TRN_DATE,PROC_DESC,TRANSACTION_CODE,ACC_TYPE_CODE,CORR_ACC_NO,TRNDATE ");
								}
						
						
						}
			
		         boolean mflag=true;							
				 	   String client_code = "";
					   boolean more = rs1.next();
						 double m_cr_tot=0,m_dr_tot=0,m_dr_amt=0,m_cr_amt=0;	
					   double m_diff_cr=0,m_diff_dr=0;
					 out.println("<HTML><HEAD><TITLE>Contract Level Balance New </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin8','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_account_entry(m_trans_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_contract_level_bal?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
					 out.println("   window.open(m_url,'popupwin7','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
						
						
					out.println("function mouse_over(id){");
					out.println("document.getElementById(id).style.textDecoration='underline';");
					out.println("}");
					
						
					out.println("function mouse_out(id){");
					out.println("document.getElementById(id).style.textDecoration='none';");
					out.println("}");
						
						
					 out.println("</SCRIPT>");
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					  out.println("<TR><TD align='Center' ><B> Contract Level Balance New "+m_display_range+" </B></TD></TR>");	//To "+m_to_date+" 
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B>"+m_trans_desc+"</B></TD></TR>");	
					 out.println("</TABLE>");
					 out.println("<hr>");		
					 out.println("<BR>");	
						
							if(!more){
								out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
								out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
								out.println("</TABLE>");
							}					
						  else{
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr class=pdn_txtpos2>");
								out.println("<td width='1%'></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Finance No</b></DIV></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Document Ref No</b></DIV></td>");
								out.println("<td width='15%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
								out.println("<td width='12%' ><DIV class=div_input><b>Trans. Date</b></DIV></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Trans. Code</b></DIV></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Acc. Code</b></DIV></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Corres. Acc. Code</b></DIV></td>");
								out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
								out.println("<td width='12%' align='right'><DIV class=div_input><b>Credit</b></DIV></td>"); 
								out.println("</tr>"); 
							
							int j=1;
							while(more){
							// if(rs1.getString(9).equals("DR")){
								m_dr_amt = rs1.getDouble(4);
							//	}
							//	else{
								m_cr_amt = rs1.getDouble(3);
							//	}
						
									if(mflag){
										out.println("<tr class=tr_input>");
										mflag=false;
									}
									else{
										out.println("<tr class=tr_input1>");
										mflag=true;
									}
									out.println("<td width='1%'></td>"); 
									out.println("<td width='12%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand' onmouseout=\"mouse_out('finance_"+j+"')\" onmouseover=\"mouse_over('finance_"+j+"')\" id=\"finance_"+j+"\">"+rs1.getString(1)+"</td>");
									if((rs1.getString(2).substring(0,2)).equals("SR")){
									out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(2)+"')\" style='cursor:hand' onmouseout=\"mouse_out('sr_"+j+"')\" onmouseover=\"mouse_over('sr_"+j+"')\" id=\"sr_"+j+"\" >"+rs1.getString(2)+"</td>");
									}
									else if((rs1.getString(2).substring(0,2)).equals("IN")){
									out.println("<td width='12%' class=div_input onClick=\"show_invoice_drill('"+rs1.getString(2)+"')\" style='cursor:hand' onmouseout=\"mouse_out('in_"+j+"')\" onmouseover=\"mouse_over('in_"+j+"')\" id=\"in_"+j+"\">"+rs1.getString(2)+"</td>");
									}
									else if((rs1.getString(2).substring(0,2)).equals("AP")){
									out.println("<td width='12%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(2)+"')\" style='cursor:hand' onmouseout=\"mouse_out('ap_"+j+"')\" onmouseover=\"mouse_over('ap_"+j+"')\" id=\"ap_"+j+"\" >"+rs1.getString(2)+"</td>");
									}
									else if((rs1.getString(2).substring(0,2)).equals("FI")){
									out.println("<td width='12%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand' onmouseout=\"mouse_out('fi_"+j+"')\" onmouseover=\"mouse_over('fi_"+j+"')\" id=\"fi_"+j+"\">"+rs1.getString(2)+"</td>");
									}
									//Added by nuwan de silva==========================
									else if((rs1.getString(2).substring(0,2)).equals("SP")){
									out.println("<td width='12%' class=div_input onClick=\"show_payment_drill('"+rs1.getString(2)+"')\" style='cursor:hand' onmouseout=\"mouse_out('sp_"+j+"')\" onmouseover=\"mouse_over('sp_"+j+"')\" id=\"sp_"+j+"\">"+rs1.getString(2)+"</td>");
									}
									//=====================================================
									else {
									out.println("<td width='12%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand' onmouseout=\"mouse_out('cn_"+j+"')\" onmouseover=\"mouse_over('cn_"+j+"')\"  id=\"cn_"+j+"\">"+rs1.getString(2)+"</td>");
									}
									out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
									out.println("<td width='12%' class=div_input onClick=\"\" >"+rs1.getString(6)+"</td>");
									out.println("<td width='12%' class=div_input onClick=\"show_account_entry('"+rs1.getString(8)+"')\" style='cursor:hand' onmouseout=\"mouse_out('trn_"+j+"')\" onmouseover=\"mouse_over('trn_"+j+"')\" id=\"trn_"+j+"\" >"+rs1.getString(8)+"</td>");
									out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(9)+"')\" style='cursor:hand'  onmouseout=\"mouse_out('acc_"+j+"')\" onmouseover=\"mouse_over('acc_"+j+"')\" id=\"acc_"+j+"\" >"+rs1.getString(9)+"</td>");
									out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(10)+"')\" style='cursor:hand' onmouseout=\"mouse_out('cor_"+j+"')\" onmouseover=\"mouse_over('cor_"+j+"')\" id=\"cor_"+j+"\" >"+rs1.getString(10)+"</td>");
									out.println("<td width='15%' align='right' class=div_input >"+nf.format(Math.abs(m_dr_amt))+"</td>");
									out.println("<td width='15%' align='right' class=div_input >"+nf.format(Math.abs(m_cr_amt))+"</td>");
									out.println("</tr>");
									j++;
									m_dr_tot = m_dr_tot+m_dr_amt;
									m_cr_tot = m_cr_tot+m_cr_amt;
									
									more = rs1.next();
									m_dr_amt =0;
									m_cr_amt =0;
							}	
					   
      	 		
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>");
						out.println("<td width='81%' align='right' colspan=7> <B>Total</B> </td>"); 
						out.println("<td width='11%' align='right'><DIV class=div_input><b>"+nf.format(Math.abs(m_dr_tot))+"</b></DIV></td>"); 
						out.println("<td width='11%' align='right'><DIV class=div_input><b>"+nf.format(Math.abs(m_cr_tot))+"</b></DIV></td>"); 
						out.println("</tr>");
						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>");
						if((m_dr_tot+m_cr_tot)>0){
						m_diff_dr = m_dr_tot+m_cr_tot;
						}else{
						m_diff_cr = m_dr_tot+m_cr_tot;
						}
						out.println("<td width='81%' align='right' colspan=7> <B>Difference </B> </td>"); 
						out.println("<td width='11%' align='right' ><DIV class=div_input><b>"+nf.format(Math.abs(m_diff_dr))+"</b></DIV></td>"); 
						out.println("<td width='11%' align='right' ><DIV class=div_input><b>"+nf.format(Math.abs(m_diff_cr))+"</b></DIV></td>"); 
						out.println("</tr>");
						}
						out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }

			
		}
			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


