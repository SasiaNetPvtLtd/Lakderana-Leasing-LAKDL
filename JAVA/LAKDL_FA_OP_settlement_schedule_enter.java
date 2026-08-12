// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
         

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
   

public class LAKDL_FA_OP_settlement_schedule_enter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			String m_username=m_sn_methods.username;
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Settlement Schedule Entry</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_EXCHANGE_RATE.value=data_vec[0];");
			out.println("			format_num(document.Form1.TXT_EXCHANGE_RATE,4);");
			out.println("			document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=0;");
			out.println("			get_rpt_currency_amt();");
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("		}");
			out.println("		else if(data_vec.length==0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_EXCHANGE_RATE.value=0;");
			out.println("			format_num(document.Form1.TXT_EXCHANGE_RATE,4);");
			out.println("			document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=0;");
			out.println("			get_rpt_currency_amt();");
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("		}");
			out.println("	  else if(data_vec.length>0 && document.Form1.hid_option.value==\"2\"){");
			out.println("			document.Form1.TXT_CHEQUE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_CHEQUE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_CHEQUE_YY.value=data_vec[2];");		
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("			get_exchange_rate();");
			out.println("		}");
			out.println("}");
			
			out.println("function get_exchange_rate() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_date=document.Form1.TXT_CHEQUE_DD.value+\"-\"+document.Form1.TXT_CHEQUE_MM.value+\"-\"+document.Form1.TXT_CHEQUE_YY.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_exchange_rate&CURR_CODE=\"+document.Form1.TXT_CURRENCY_CODE.value+\"&VAL_DATE=\"+m_date;");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_rpt_currency_amt(){"); 
			out.println("		m_amt=0;");
			out.println("		m_amt=parseFloat(unformat_noobject(document.Form1.TXT_EXCHANGE_RATE.value))*parseFloat(unformat_noobject(document.Form1.TXT_CHEQUE_AMOUNT.value));");
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=m_amt;");
			out.println("		format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4);");
			out.println("}");   
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"2\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	m_flag=true;"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.TXT_POD_REF_NO.value==\"\" ){  "); 
			out.println("		DIV_TXT_POD_REF_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(!check_balance()){"); 
			out.println("		DIV_TXT_CHEQUE_AMOUNT.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_BRANCH_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_BRANCH_CODE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_CHEQUE_ACC_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_CHEQUE_ACC_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_CHEQUE_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_CHEQUE_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_CHEQUE_DD.value==\"\"){  "); 
			out.println("		DIV_TXT_CHEQUE_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_CHEQUE_MM.value==\"\"){  "); 
			out.println("		DIV_TXT_CHEQUE_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_CHEQUE_YY.value==\"\"){  "); 
			out.println("		DIV_TXT_CHEQUE_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_CHEQUE_AMOUNT.value==\"\"){  "); 
			out.println("		DIV_TXT_CHEQUE_AMOUNT.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_DEBTOR_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_DEBTOR_CODE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(m_flag){"); 
			out.println("		return true;");
			out.println("	}");
			out.println("	else{"); 
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("}"); 			  
			
			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+m_sav_msg+\" ?\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_settlement_schedule_enter';");  
			out.println("			document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("	else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("	}");
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_settlement_schedule_enter';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_settlement_schedule_enter';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_SETTLEMENT_SCHEDULE_ENTER\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Operation Process -  Settlement Schedule Entry - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Operation Process -  Settlement Schedule Entry - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			//out.println("	if(confirm(\"Are You Sure\")){ ");
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("			document.Form1.BUT_HELP_MAIN_1.disabled=true;");
			out.println("			document.Form1.TXT_POD_REF_NO.disabled=true;");
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("			document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("			document.Form1.TXT_POD_REF_NO.disabled=false;");
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
			//out.println("	}"); 
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

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_1();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_2();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_update_value_3();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("						help_update_value_4();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("						help_update_value_5();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("						help_update_value_6();"); 
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
			
			out.println("function help_update_pod_ref_no() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_SETTLE_SCHEDULE_ENTER_EDIT_sql\";"); 
			out.println("	 	m_criteria = document.Form1.TXT_POD_REF_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','5');"); 
			out.println("}"); 
			   
			out.println("function help_update_value_1() {"); 
			out.println("		document.Form1.TXT_POD_REF_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_BRANCH_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_BRANCH_NAME.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CHEQUE_ACC_NO.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_CHEQUE_NO.value=oBj.valout[6];"); 
			out.println("		document.Form1.TXT_CHEQUE_DD.value=oBj.valout[7].substring(0,2);");
			out.println("		document.Form1.TXT_CHEQUE_MM.value=oBj.valout[7].substring(3,5);");
			out.println("		document.Form1.TXT_CHEQUE_YY.value=oBj.valout[7].substring(6,10);");
			out.println("		document.Form1.TXT_CHEQUE_AMOUNT.value=oBj.valout[8];"); 
			out.println("		document.Form1.TXT_CHEQUE_COMMENTS.value=oBj.valout[9];"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[10];"); 
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=oBj.valout[11];"); 
			out.println("		document.Form1.TXT_CURRENCY_CODE.value=oBj.valout[12];"); 
			out.println("		document.Form1.TXT_EXCHANGE_RATE.value=oBj.valout[14];"); 
			out.println("		document.Form1.TXT_TEMP_RECEIPT_NO.value=oBj.valout[15];"); 
			out.println("		document.Form1.TXT_COLL_OFFICER.value=oBj.valout[16];"); 
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=oBj.valout[13];");
			out.println("		format_num(document.Form1.TXT_CHEQUE_AMOUNT,4);");
			out.println("		format_num(document.Form1.TXT_EXCHANGE_RATE,4);");
			out.println("		format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4);");
			out.println("		load_corres_invoice_list_edit();");
			out.println("}");
			
			/*out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_CHEQUE_ENTER_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); */
			out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_SCHEDULE_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_4() {"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=oBj.valout[3];"); 
			out.println("}"); 
			
			out.println("function help_update_value_4() {"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=oBj.valout[3];"); 
			out.println("		load_corres_invoice_list();");
			out.println("}"); 
			
			out.println("function help_update_branch() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_POD_CHEQUE_BRANCH_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_BRANCH_NAME.value=oBj.valout[3];"); 
			out.println("}");
			
			out.println("function help_update_facility() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"5\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_INVOICE_SETTLE_ENTER_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
						
			out.println("function help_update_value_5() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("}");
			
			out.println("function help_collection_officer(){");
			out.println(" 	document.Form1.hid_help_type.value=\"6\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_CR_CLIENT_MGT_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_COLL_OFFICER.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_6() {"); 
			out.println("		document.Form1.TXT_COLL_OFFICER.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function load_calendar() {");
			out.println("		popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\",\"oBj\",\"left=450,top=200,width=320,height=230\");");
			out.println("}");
			
			out.println("function load_c_date(val) {");
     	out.println("	 v_dd = val.substr(0,val.indexOf('-'));");
   		out.println("  if(v_dd.length <2)"); 
   		out.println("  	v_dd = 0+v_dd;");
  		out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("	 v_mm = val.substr(0,val.indexOf('-'));");
  		out.println("  if(v_mm.length <2)");
   	  out.println("		v_mm = 0+v_mm;");
  		out.println("   v_yy = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("		document.Form1.TXT_CHEQUE_DD.value=v_dd;");
   		out.println("   document.Form1.TXT_CHEQUE_MM.value=v_mm;");
   		out.println("   document.Form1.TXT_CHEQUE_YY.value=v_yy;");
 			out.println("}");
   
			out.println("function load_corres_invoice_list() {");
			out.println("		if(document.Form1.TXT_ALLO_TYPE.value==\"IA\"){");
			out.println("			if(document.Form1.TXT_DEBTOR_CODE.value!=\"\"){");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=LOAD_INVOICES_FOR_SCHEDULE_CHEQUES&DEBTOR_CODE=\"+document.Form1.TXT_DEBTOR_CODE.value;");
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("		}");
			out.println("}");
			
			out.println("function load_corres_invoice_list_edit() {");
			out.println("		if(document.Form1.TXT_ALLO_TYPE.value==\"IA\"){");
			out.println("			if(document.Form1.TXT_DEBTOR_CODE.value!=\"\"){");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=LOAD_INVOICES_FOR_SCHEDULE_CHEQUES_EDIT&POD_CODE=\"+document.Form1.TXT_POD_REF_NO.value+\"&DEBTOR_CODE=\"+document.Form1.TXT_DEBTOR_CODE.value;");
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("		}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function clear_selection_list(){");
			out.println("		if(document.Form1.TXT_ALLO_TYPE.value!=\"IA\"){");
			out.println("			invoice_detail_data.innerHTML=\"\";");
			out.println("		}");
			out.println("}");
			
			out.println("function check_status(i){");
			out.println("		if(document.Form1.elements[\"TXT_POD_STATUS_\"+i].checked){");
			out.println("			if(parseFloat(unformat_noobject(document.Form1.elements[\"TXT_INV_BALANCE_\"+i].value))<parseFloat(unformat_noobject(document.Form1.elements[\"TXT_INV_ALLO_\"+i].value))){");
			out.println("				alert(\"Allocation amount should be less or equals to the Balance Amount\");");
			out.println("				document.Form1.elements[\"TXT_INV_ALLO_\"+i].value=0;");
			out.println("				document.Form1.elements[\"TXT_INV_ALLO_\"+i].disabled=false;");
			out.println("				document.Form1.elements[\"TXT_POD_STATUS_\"+i].checked=false;");
			out.println("			}");
			out.println("			else{");
			out.println("				document.Form1.elements[\"TXT_INV_ALLO_\"+i].disabled=true;");
			out.println("				document.Form1.elements[\"TXT_POD_STATUS_\"+i].checked=true;");
			out.println("			}");
			out.println("		}");
			out.println("		else{");
			out.println("				document.Form1.elements[\"TXT_INV_ALLO_\"+i].disabled=false;");
			out.println("		}");
			out.println("}");		
			
			out.println("function check_value(i){");
			out.println("			if(parseFloat(unformat_noobject(document.Form1.elements[\"TXT_INV_BALANCE_\"+i].value))<parseFloat(unformat_noobject(document.Form1.elements[\"TXT_INV_ALLO_\"+i].value))){");
			out.println("				alert(\"Allocation amount should be less or equals to the Balance Amount\");");
			out.println("				document.Form1.elements[\"TXT_INV_ALLO_\"+i].value=0;");
			out.println("				document.Form1.elements[\"TXT_INV_ALLO_\"+i].disabled=false;");
			out.println("				document.Form1.elements[\"TXT_POD_STATUS_\"+i].checked=false;");
			out.println("			}");
			out.println("			else{");
			out.println("				document.Form1.elements[\"TXT_INV_ALLO_\"+i].disabled=true;");
			out.println("				document.Form1.elements[\"TXT_POD_STATUS_\"+i].checked=true;");
			out.println("			}");
			out.println("}");		
			
			out.println("function check_balance(){ "); 
			out.println("	m_pod_value=unformat_noobject(document.Form1.TXT_CHEQUE_AMOUNT.value);");
			out.println("	m_pod_select_value=0;");
			out.println("	for (var i=0;i<document.Form1.NUM_CHKS.value;i++){");
			out.println("		if(document.Form1.elements[\"TXT_POD_STATUS_\"+i].checked){");
			out.println("			m_pod_select_value=parseFloat(m_pod_select_value)+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_INV_ALLO_\"+i].value));");
			out.println("		}");
			out.println("	}");
			out.println("	if(parseFloat(m_pod_select_value)!=parseFloat(m_pod_value)){");
			out.println("		alert('Schedule Total Value should eqauls to Allocated amount');");
			out.println("		return false;");
			out.println("	}");
			out.println("	else{	return true;");
			out.println("	}");
			out.println("} "); 
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock();get_system_date();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process -  Settlement Schedule Entry</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<br>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_POD_REF_NO'  class=div_input>Settlement Schedule Reference No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_POD_REF_NO' maxlength='25' size='25' onblur=\"help_update_pod_ref_no()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_pod_ref_no()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_SETTLEMENT_MODE'  class=div_input>Settlement Mode *</DIV></td>");
			out.println("<td width='40%' ><SELECT name=\"TXT_SETTLEMENT_MODE\" class=\"txt_input\" > ");
			out.println("<OPTION value=\"CASH\" >Cash</OPTION>");
			out.println("<OPTION value=\"CHEQUE\" SELECTED>Cheque</OPTION>");
			//out.println("<OPTION value=\"BANKTR\">Bank Transfer</OPTION>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_branch()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_NAME'  class=div_input>Branch Name </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_NAME' maxlength='50' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CHEQUE_ACC_NO'  class=div_input>Account No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CHEQUE_ACC_NO' maxlength='20' size='50' style='width:100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CHEQUE_NO'  class=div_input>Cheque No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CHEQUE_NO' maxlength='10' size='50' style='width:100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_CHEQUE_DATE\" class=div_input>Value Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_CHEQUE_DD,document.Form1.TXT_CHEQUE_MM,document.Form1.TXT_CHEQUE_YY)\"><a href style=\"{cursor:hand;}\" onclick=\"load_calendar()\">   <u>Calendar</u></a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CHEQUE_AMOUNT'  class=div_input>Value Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CHEQUE_AMOUNT' maxlength='25' size='50' style='width:150;text-align:right;'  onchange=\"get_rpt_currency_amt();format_num(document.Form1.TXT_CHEQUE_AMOUNT,4)\" value=\"0\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_CURRENCY'  class=div_input>Currency *</DIV></td>");
			out.println("<td width='40%' ><SELECT onchange=\"get_exchange_rate()\" name=\"TXT_CURRENCY_CODE\" class=\"txt_input\" > ");
			
			rs = stmt.executeQuery ("SELECT CURR_CODE,CURR_SYMBOL,REP_CURR FROM "+m_schema_name+".AF_CO_MAS_CURRENCY ORDER  BY DEFAULT_VALUE DESC ");
			while(rs.next()){
			out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
			}
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_EXCHANGE_RATE'  class=div_input>Exchange Rate *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EXCHANGE_RATE' maxlength='25' size='50' style='width:150;text-align:right;'  value=\"0\" onchange=\"format_num(document.Form1.TXT_EXCHANGE_RATE,4)\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_RPT_CURR_AMT'  class=div_input>Reporting Currency Amount </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_RPT_CURR_AMT' maxlength='25' size='50' style='width:150;text-align:right;' value=\"0\" onchange=\"format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4)\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CHEQUE_COMMENTS'  class=div_input>Comments </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CHEQUE_COMMENTS' maxlength='200' size='200' style='width:200'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<td width='20%' >Allocation Type</td>"); 
			out.println("<td width='40%' ><select name='TXT_ALLO_TYPE' class='txt_input' style='width:200' onblur=\"clear_selection_list()\">");
			out.println("<option value=\"IA\" SELECTED>Invoice wise allocation</option>");
			//out.println("<option value=\"DA\">Debtor wise allocation</option>");
			//out.println("<option value=\"CA\">Client wise allocation</option>");
			out.println("</select></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_TEMP_RECEIPT_NO'  class=div_input>Temp Receipt No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEMP_RECEIPT_NO' maxlength='20' size='200' style='width:150' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_COLL_OFFICER'  class=div_input>Collection Officer </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLL_OFFICER' maxlength='10' size='10' value=\""+m_username+"\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_COLL' value=\"Help\" onClick=\"help_collection_officer()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");  
			//out.println("<hr>");
			out.println("<table align='center' width='100%' class='table'>");
			//----------------------------------------------
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_DEBTOR_CODE'  class=div_input>Debtor Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEBTOR_CODE' maxlength='10' size='10' onblur=\"help_update_debtor()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Help\" onClick=\"help_update_debtor()\">");
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Details\" onClick=\"show_client(document.Form1.TXT_DEBTOR_CODE.value)\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_DEBTOR_DESC'  class=div_input>Debtor Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEBTOR_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
			//out.println("<hr>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
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
