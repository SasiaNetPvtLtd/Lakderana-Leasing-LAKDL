// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
         
  
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
   

public class LAKDL_FA_OP_Client_Payments extends javax.servlet.http.HttpServlet { 

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
			String m_username=m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out=res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			m_username=m_sn_methods.username;
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Client Payments</TITLE>"); 
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
			out.println("		else if(data_vec.length>0 && document.Form1.hid_option.value==\"2\"){");
			out.println("			document.Form1.TXT_PAYMENT_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_PAYMENT_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_PAYMENT_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_CHEQUE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_CHEQUE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_CHEQUE_YY.value=data_vec[2];");
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("	  	get_exchange_rate();");
			out.println("		}");
			out.println("}");
			
			out.println("function get_exchange_rate() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_date=document.Form1.TXT_PAYMENT_DD.value+\"-\"+document.Form1.TXT_PAYMENT_MM.value+\"-\"+document.Form1.TXT_PAYMENT_YY.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_exchange_rate&CURR_CODE=\"+document.Form1.TXT_CURRENCY_CODE.value+\"&VAL_DATE=\"+m_date;");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"2\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_rpt_currency_amt(){"); 
			out.println("		m_amt=0;");
			out.println("		m_amt=parseFloat(unformat_noobject(document.Form1.TXT_EXCHANGE_RATE.value))*parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value));");
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=m_amt;");
			out.println("		format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4);");
			out.println("}");   

			out.println("function validate_data(){"); 
			out.println("	m_flag=true;"); 
			out.println("	if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("		DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			out.println("		DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_PAYMENT_DD.value==\"\" ){  "); 
			out.println("		DIV_TXT_PAYMENT_AMOUNT_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_PAYMENT_MM.value==\"\" ){  "); 
			out.println("		DIV_TXT_PAYMENT_AMOUNT_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_PAYMENT_YY.value==\"\" ){  "); 
			out.println("		DIV_TXT_PAYMENT_AMOUNT_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_PAYMENT_AMOUNT.value==\"\"){  "); 
			out.println("		DIV_TXT_PAYMENT_AMOUNT.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_LICEN_ACCOUNT.value==\"\"){  "); 
			out.println("		DIV_TXT_LICEN_ACCOUNT.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.SCREEN_NAME.value==\"EDIT\"){"); 
			out.println("			if(document.Form1.TXT_PAYMENT_NO.value==\"\"){");
			out.println("				DIV_TXT_PAYMENT_NO.style.color='red';");
			out.println("				m_flag=false;"); 
			out.println("			}");
			out.println("	}");
			out.println("	if(m_flag){");			
			out.println("		return true;"); 
			out.println("	}");
			out.println("	else{"); 
			out.println("		alert('Please enter all required fields marked with a *  and or mark highlighted with red color on screen');");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("}"); 
			   
			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_Client_Payments';");  
			out.println("			document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function create_debtor(){	"); 
			out.println("		if(confirm(\"Are you sure you want to create new Debtor ?\")){ "); 
			out.println("		window.open(\""+m_class_url+"/"+m_fschema_name+"FA_CR_display_client_creation\",\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700\");");
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Client_Payments';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Client_Payments';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_CR_CLIENT_PAYMENTS\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\"  Operation Process - Client Payments - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Operation Process - Client Payments - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("	if(confirm(\"Are You Sure\")){ ");
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
			out.println("			document.Form1.TXT_PAYMENT_NO.disabled=false;");
			out.println("			document.Form1.BUT_HELP_MAIN_10.disabled=false;");
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
			out.println("	}"); 
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

			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");

			out.println("function help_update_client() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_CLIENT_AVAILABLE_ENTER_sql\";");
			out.println(" 	m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println("		HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_1() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_MANAGER_NAME.value=oBj.valout[4];"); 
			out.println("}");
			
			out.println("function help_update_facility() {"); 
			out.println(" document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_sql\";");
			out.println(" m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\";"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_2() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CLIENT_MANAGER_NAME.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_CR_LIMIT.value=oBj.valout[6];"); 
			out.println("		format_num(document.Form1.TXT_CR_LIMIT,4);");
			out.println("}");

			out.println("function help_payer_account() {"); 
			out.println(" document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_PAYER_SETTLE_ACC_DETAILS_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_PAYER_ACCOUNT.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_PAYER_ACCOUNT.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_PAYER_BRANCH.value=oBj.valout[4];"); 
			out.println("}");
			
			out.println("function help_payer_account() {"); 
			out.println(" document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_PAYER_SETTLE_ACC_DETAILS_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_PAYER_ACCOUNT.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_PAYER_ACCOUNT.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_PAYER_BRANCH.value=oBj.valout[4];"); 
			out.println("}");
			
			out.println("function help_licensee_account() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_SETTLEMENT_ACCOUNTS_sql\";"); 
			out.println("	 	m_criteria = document.Form1.TXT_LICEN_ACCOUNT.value+\"@Y@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_4() {"); 
			out.println("		document.Form1.TXT_LICEN_ACCOUNT.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_LICEN_BRANCH.value=oBj.valout[3];"); 
			out.println("}");
			
			out.println("function help_update_payment_no(){");
			out.println(" 	document.Form1.hid_help_type.value=\"6\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_EDIT_PAYMENT_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_PAYMENT_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','17');"); 
			out.println("}"); 
	
			out.println("function help_update_value_6() {"); 
			out.println("		document.Form1.TXT_PAYMENT_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CLIENT_MANAGER_NAME.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[6];"); 
			out.println("		document.Form1.TXT_CR_LIMIT.value=oBj.valout[7];"); 
			out.println("		document.Form1.TXT_PAYMENT_AMOUNT.value=oBj.valout[8];");
			out.println("		document.Form1.TXT_PAYMENT_DD.value=oBj.valout[9].substring(0,2);"); 
			out.println("		document.Form1.TXT_PAYMENT_MM.value=oBj.valout[9].substring(3,5);"); 
			out.println("		document.Form1.TXT_PAYMENT_YY.value=oBj.valout[9].substring(6,10);");  
			out.println("		document.Form1.TXT_SETTLEMENT_MODE.value=oBj.valout[10];");
			out.println("		document.Form1.TXT_LICEN_BRANCH.value=oBj.valout[11];"); 
			out.println("		document.Form1.TXT_LICEN_ACCOUNT.value=oBj.valout[12];"); 
			out.println("		document.Form1.TXT_PAYER_BRANCH.value=oBj.valout[13];"); 
			out.println("		document.Form1.TXT_PAYER_ACCOUNT.value=oBj.valout[14];");
			out.println("		document.Form1.TXT_INVOICE_COMMENTS.value=oBj.valout[15];"); 
			out.println("		document.Form1.TXT_REF_NO.value=oBj.valout[16];");
			out.println("		document.Form1.TXT_CHEQUE_DD.value=oBj.valout[17].substring(0,2);"); 
			out.println("		document.Form1.TXT_CHEQUE_MM.value=oBj.valout[17].substring(3,5);"); 
			out.println("		document.Form1.TXT_CHEQUE_YY.value=oBj.valout[17].substring(6,10);");  
			out.println("		document.Form1.TXT_CURRENCY_CODE.value=oBj.valout[18];"); 
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=oBj.valout[19];"); 
			out.println("		document.Form1.TXT_EXCHANGE_RATE.value=oBj.valout[20];");
			out.println("		document.Form1.TXT_3RD_PARTY.value=oBj.valout[21];");
			out.println("		document.Form1.TXT_3RD_PARTY_NAME.value=oBj.valout[22];");
			out.println("		format_num(document.Form1.TXT_CR_LIMIT,2);");
			out.println("		format_num(document.Form1.TXT_PAYMENT_AMOUNT,2);");
			out.println("		format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,2);");
			out.println("		format_num(document.Form1.TXT_EXCHANGE_RATE,2);");
			out.println("}");
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");

			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_PAYMENT_DD.value=v_dd;");
			out.println("     document.Form1.TXT_PAYMENT_MM.value=v_mm;");
			out.println("     document.Form1.TXT_PAYMENT_YY.value=v_yy;");
			out.println("  }");		
		  out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 	
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_CHEQUE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_CHEQUE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_CHEQUE_YY.value=v_yy;");
			out.println("  }");		
			out.println("}");				
			
			out.println("function show_client_availability() {");
			out.println("		client_availability_data.innerHTML=\"\";");
			out.println("		if(document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.TXT_FACILITY_NO.value!=\"\"){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr?chksql=LOAD_CLIENT_AVAILABILITY&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("		load_interface(m_url,'NO');");
			out.println("		}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		client_availability_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function load_availability_drill(m_client,m_facility,m_option) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr?chksql=LOAD_CLIENT_AVAILABILITY_DRILL&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&OPTION_NO=\"+m_option;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=600,height=450,scrollbars=2\");");
			out.println("}");
			
			out.println("function get_payment_3rd_party() {");
			out.println("		if(document.Form1.TXT_3RD_PARTY.value==\"Y\"){");
			out.println("			document.Form1.TXT_3RD_PARTY_NAME.disabled=false;");
			out.println("		}");
			out.println("		else{");
			out.println("			document.Form1.TXT_3RD_PARTY_NAME.disabled=true;");
			out.println("		}");
			out.println("}");
			
			out.println("function load_availability_drill(m_client,m_facility,m_option) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=LOAD_CLIENT_AVAILABILITY_DRILL&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&OPTION_NO=\"+m_option;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=700,height=450,scrollbars=2\");");
			out.println("}");
			
			out.println("function load_availability_drill_other(m_client,m_facility,m_option,m_date) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=LOAD_CLIENT_AVAILABILITY_DRILL_MORE&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&OPTION_NO=\"+m_option+\"&ST_DATE=\"+m_date;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=700,height=450,scrollbars=2\");");
			out.println("}");
			
			//Added by Dineth on 28-07-2009
			out.println("function copy_window(){");
			out.println("m_url = \""+m_class_url+"/"+m_fschema_name+"FA_OP_display_payment_req\" ;");
			out.println("window.open(m_url,'popupwin2','top=150,left=200,width=650,height=300,toolbar=0,menubar=0,center=yes,resizeble=0');");
			out.println("}");
			//End by Dineth on 28-07-2009
			
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
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process - Client Payments </td>"); 
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
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='copy_window()' value=\"Copy\"></td>"); //Added by Dineth on 28-07-2009
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<br>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PAYMENT_NO'  class=div_input>Payment No *</DIV></td>"); 
			out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_PAYMENT_NO' maxlength='25' size='10' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_10' value=\"Help\" onClick=\"help_update_payment_no()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='20' size='10' onblur=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_CDETAIL' value=\"Details\" onClick=\"show_facility(document.Form1.TXT_FACILITY_NO.value)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Check Client Availability\" onClick=\"show_client_availability()\" style='width:150' ></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code	</DIV></td>");
			out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_client()\" disabled>");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_CDETAIL' value=\"Details\" onClick=\"show_client(document.Form1.TXT_CLIENT_CODE.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_MANAGER_NAME'  class=div_input>Client Manager Name</DIV></td>"); 
			out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_CLIENT_MANAGER_NAME' maxlength='50' size='50'  style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CR_LIMIT'  class=div_input>Credit Limit</DIV></td>"); 
			out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_CR_LIMIT' maxlength='25' size='50' style='width:150;text-align:right;'  disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			out.println("<DIV id='client_availability_data'  class=div_input></DIV>");
			out.println("<hr>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_PAYMENT_DATE\" class=div_input>Value Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_PAYMENT_DD\" maxlength=\"2\" size=\"2\" disabled >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_PAYMENT_MM\" maxlength=\"2\" size=\"2\" disabled  >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_PAYMENT_YY\" maxlength=\"4\" size=\"4\" disabled onblur=\"checkMonthLength(document.Form1.TXT_PAYMENT_DD,document.Form1.TXT_PAYMENT_MM,document.Form1.TXT_PAYMENT_YY)\"> </td>");//<a href style=\"{cursor:hand; }\" onclick=load_calendar(\"1\")>   Calendar</a>
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PAYMENT_AMOUNT'  class=div_input>Payment Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYMENT_AMOUNT' maxlength='25' size='50' style='width:150;text-align:right;'   value=\"0\" onchange=\"get_rpt_currency_amt();format_num(document.Form1.TXT_PAYMENT_AMOUNT,4);\"  tabindex=\"3\"></td>"); 
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
			out.println("<td width='15%' ><DIV id='DIV_TXT_SETTLEMENT_MODE'  class=div_input>Settlemeny Mode *</DIV></td>");
			out.println("<td width='40%' ><SELECT onchange=\"get_exchange_rate()\" name=\"TXT_SETTLEMENT_MODE\" class=\"txt_input\" > ");
			out.println("<OPTION value=\"CASH\" >Cash</OPTION>");
			out.println("<OPTION value=\"CHEQUE\" SELECTED>Cheque</OPTION>");
			out.println("<OPTION value=\"BANKTR\">Bank Transfer</OPTION>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PAYER_ACCOUNT'  class=div_input>Payer Account </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYER_ACCOUNT' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_ACC' value=\"Help\" onClick=\"help_payer_account()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PAYER_BRANCH'  class=div_input>Payer Branch Code </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYER_BRANCH' maxlength='10' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REF_NO'  class=div_input>Reference No/Cheque No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REF_NO' maxlength='10' size='10' style='width:150'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_CHEQUE_DATE\" class=div_input>Cheque Date </DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_CHEQUE_DD,document.Form1.TXT_CHEQUE_MM,document.Form1.TXT_CHEQUE_YY)\"> <a href style=\"{cursor:hand; }\" onclick=load_calendar(\"2\")>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_COMMENTS'  class=div_input>Comments </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_COMMENTS' maxlength='200' size='200' style='width:200' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_LICEN_ACCOUNT'  class=div_input>Licensee Account *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LICEN_ACCOUNT' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_ACC' value=\"Help\" onClick=\"help_licensee_account()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_LICEN_BRANCH'  class=div_input>Licensee Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LICEN_BRANCH' maxlength='10' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_3RD_PARTY1'  class=div_input>If Payment to 3rd Party</DIV></td>");
			out.println("<td width='40%' ><SELECT onchange=\"get_payment_3rd_party()\" name=\"TXT_3RD_PARTY\" class=\"txt_input\" > ");
			out.println("<OPTION value=\"Y\" >Yes</OPTION>");
			out.println("<OPTION value=\"N\" SELECTED>No</OPTION>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_3RD_PARTY'  class=div_input>Payment to 3rd Party</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_3RD_PARTY_NAME' maxlength='100' size='100' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='invoice_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 

			
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
