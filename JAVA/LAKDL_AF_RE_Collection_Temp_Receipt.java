
//--
//SCREEN NAME:COLLECTION - TEMP RECEIPTS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Temp_Receipt extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Temporary Receipts-Entry</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			

			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_TEMP_REC_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     help_update();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_TEMP_REC_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");
			
		  out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data_vec[2];"); 
			out.println("			}");
			
				out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("     help_button_1();");
      out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
			out.println("			}");

  		out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("     help_button_2();");
      out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.TXT_BRANCH_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_BRANCH_CODE.value=data_vec[0];"); 
			out.println("			}");

  		out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.TXT_BRANCH_CODE.value!=\"\"){");
			out.println("     help_button_4();");
      out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_ACCOUNT_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_ACCOUNT_NO.value=data_vec[0];");
			out.println("    document.Form1.TXT_BRANCH_CODE.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=data_vec[2];");
			out.println("			}");

  		out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_ACCOUNT_NO.value!=\"\"){");
			out.println("     help_button_5();");
      out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M6' && document.Form1.TXT_CURR_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_CURR_CODE.value=data_vec[0];"); 
			out.println("			}");

  		out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M6' && document.Form1.TXT_CURR_CODE.value!=\"\"){");
			out.println("     help_button_7();");
      out.println("			}");

      out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M7' && document.Form1.TXT_COLLECTION_OFFICER.value!=\"\"){");
			out.println("    document.Form1.TXT_COLLECTION_OFFICER.value=data_vec[0];"); 
			out.println("			}");

  		out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M7' && document.Form1.TXT_COLLECTION_OFFICER.value!=\"\"){");
			out.println("     help_button_6();");
      out.println("			}");
			
      out.println("			else");
			out.println("			if(data_vec.length>0  &&document.Form1.hid_chk_status.value=='M8' && document.Form1.TXT_REC_BOOK_NO.value!=\"\" ){");
			out.println("    assign_receipt_no(data_vec)"); 
      out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0  && document.Form1.hid_chk_status.value=='M8' && document.Form1.TXT_REC_BOOK_NO.value!=\"\"){");
			out.println("     help_button_8();");
      out.println("			}");
			
			
			out.println("			else");
			out.println("			if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M10' ){");
			out.println("     assign_val_date(data_vec);"); 
      out.println("     assign_cheque_date(data_vec);");
			out.println("			}");
			
			
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M9' ){");
			out.println("if(data_vec[0]==\"-\"){");
			out.println("    document.Form1.TXT_RECEIPT_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_RECEIPT_NO.disabled=false;"); 
			out.println("			}");
			out.println("else {");
			out.println("var last_receipt_no=0;");
			out.println(" last_receipt_no=parseInt(data_vec[0])+1;");
			out.println("    document.Form1.TXT_RECEIPT_NO.value=last_receipt_no;"); 
			out.println("    document.Form1.TXT_RECEIPT_NO.disabled=true;"); 
      out.println("			}");
		  out.println("			}");	
			
								
			out.println("}");
			
			
		 out.println("function assign_val_date(data_vec){");	//Added by Chandana on 24/10/2007
		 out.println("     document.Form1.VAL_DAY.value         =data_vec[0];"); 
		 out.println("     document.Form1.VAL_MONTH.value       =data_vec[1];"); 
		 out.println("     document.Form1.VAL_YEAR.value        =data_vec[2];"); 
		 out.println("}");
			
     out.println("function assign_cheque_date(data_vec){");	//Added by Chandana on 24/10/2007
		 out.println("     document.Form1.CHEQUE_DAY.value         =data_vec[0];"); 
		 out.println("     document.Form1.CHEQUE_MONTH.value       =data_vec[1];"); 
		 out.println("     document.Form1.CHEQUE_YEAR.value        =data_vec[2];"); 
		 out.println("}");			
			
			
			
			out.println("function assign_receipt_no(data_vec){");
			
			out.println("if(data_vec[0]==\"OLD\"){");
			out.println("assignState('M9')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_Last_Receipt_No&data_val=\"+document.Form1.TXT_COLLECTION_OFFICER.value+\"&data_val2=\"+document.Form1.TXT_REC_BOOK_NO.value;");
			out.println("load_interface(m_url,'XML');");
		//	out.println("window.open(m_url);");
			
			out.println("}");
			out.println("else{");
			out.println("    document.Form1.TXT_RECEIPT_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_RECEIPT_NO.disabled=false;"); 

			out.println("}");
			
			out.println("}");
			
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_TRN_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_TRN_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_TRN_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			
			
			out.println("function assign_values(data_vec){");
			out.println("document.Form1.TXT_TEMP_REC_NO.value=data_vec[0];"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=data_vec[1];"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value=data_vec[2];"); 
			out.println("document.Form1.TXT_CLIENT_NAME.value=data_vec[3];"); 
			
			out.println("getDateValues(data_vec[4])");
			//out.println("document.Form1.TXT_TRN_DATE_DD.value=data_vec[2];"); 
			//out.println("document.Form1.TXT_TRN_DATE_MM.value=data_vec[3];"); 
			//out.println("document.Form1.TXT_TRN_DATE_YY.value=data_vec[4];"); 
			out.println("document.Form1.TXT_AMOUNT.value=data_vec[5];"); 
			out.println("format_number(document.Form1.TXT_AMOUNT,25);");
			out.println("document.Form1.TXT_SETTELMENT_MODE.value=data_vec[6];"); 
			out.println("document.Form1.TXT_BANK_CODE.value=data_vec[7];"); 
			out.println("document.Form1.TXT_BRANCH_CODE.value=data_vec[8];");
			out.println("document.Form1.TXT_BRANCH_NAME.value=data_vec[27];");//TXT_BRANCH_NAME
			out.println("document.Form1.TXT_ACCOUNT_NO.value=data_vec[9];"); 
			out.println("document.Form1.TXT_CURR_CODE.value=data_vec[10];"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE.value=data_vec[11];"); 
			out.println("format_number(document.Form1.TXT_EXCHANGE_RATE,10);");
			out.println("document.Form1.TXT_TRN_AMOUNT_CURR.value=data_vec[12];"); 
			out.println("format_number(document.Form1.TXT_TRN_AMOUNT_CURR,25);");
			out.println("document.Form1.TXT_COLLECTION_OFFICER.value=data_vec[13];"); 
			out.println("document.Form1.TXT_RECEIPT_NO.value=data_vec[14];"); 
			out.println("document.Form1.TXT_REC_BOOK_NO.value=data_vec[15];"); 
			out.println("document.Form1.TXT_CHEQUE_NO.value=data_vec[16];"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE.disabled=true;"); 
			
			
			out.println(" if( data_vec[17]!='0'){");
			out.println("    document.Form1.OTHER_CHARGES.value='Y';");
			out.println("  get_other_charges();");
			out.println("    document.Form1.TXT_RENTAL_OTHER_INV.value=format_noobject(parseFloat(data_vec[17]));"); 
			out.println("    document.Form1.TXT_INSURANCE_PREMIUM.value=format_noobject(parseFloat(data_vec[18]));"); 
			out.println("    document.Form1.TXT_LUX_TAX.value=format_noobject(parseFloat(data_vec[19]));");
			out.println("    document.Form1.TXT_REVENUE_LICENCY.value=format_noobject(parseFloat(data_vec[20]));");
			out.println("    document.Form1.TXT_RMV_REG_FEES.value=format_noobject(parseFloat(data_vec[21]));");
			out.println("    document.Form1.TXT_TOT_ENTERED.value=format_noobject((parseFloat(data_vec[17])+parseFloat(data_vec[18])+parseFloat(data_vec[19])+parseFloat(data_vec[20])+parseFloat(data_vec[21])));");
		  out.println("    document.Form1.TXT_BALANCE_PENDING.value=format_noobject((parseFloat(data_vec[5])-(parseFloat(data_vec[17])+parseFloat(data_vec[18])+parseFloat(data_vec[19])+parseFloat(data_vec[20])+parseFloat(data_vec[21])) ));"); 
			out.println("}"); 
			
			out.println("getValueDate(data_vec[22]);"); 
			out.println("getChequeDate(data_vec[23]);");
				
			
			out.println(" if( data_vec[24]!='-'){");
			out.println("    document.Form1.PAY_TYPE.value='THIRD';"); //KKKKKK 
			out.println("   display_row_third_party();");
			//out.println(" alert('assign123=='+data_vec[25]);");
			
			out.println("    document.Form1.CLIENT_NAME_1.value=data_vec[24];");
			out.println("    document.Form1.CLIENT_ADDRESS_1.value=data_vec[25];"); 
			
			out.println("  document.Form1.hid_TXT_THIRD_PARTY_NAME.value = document.Form1.elements['CLIENT_NAME_1'].value;");
			out.println("  document.Form1.hid_TXT_THIRD_PARTY_ADD.value  = document.Form1.elements['CLIENT_ADDRESS_1'].value;");
						
			out.println("}");
			out.println("    document.Form1.CLIENT_ADDRESS.value=data_vec[26];");
			
			
			
			
     // out.println("}"); 
			
			out.println(" if((document.Form1.SCREEN_NAME.value==\"DACT\" && document.Form1.OTHER_CHARGES.value==\"Y\")||(document.Form1.SCREEN_NAME.value==\"RACT\" && document.Form1.OTHER_CHARGES.value==\"Y\")){"); 
			out.println("    document.Form1.TXT_RENTAL_OTHER_INV.disabled=true;");
			out.println("    document.Form1.TXT_INSURANCE_PREMIUM.disabled=true;");
      out.println("    document.Form1.TXT_LUX_TAX.disabled=true;");
      out.println("    document.Form1.TXT_REVENUE_LICENCY.disabled=true;");
      out.println("    document.Form1.TXT_RMV_REG_FEES.disabled=true;");

			
			out.println("}"); 
				
			//out.println("}");
			
			
			
			out.println("}");
			
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_temp_rec_no&data_val=\"+obj.value+\"&ac_status=N\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && (document.Form1.SCREEN_NAME.value==\"DACT\" || document.Form1.SCREEN_NAME.value==\"EDIT\"))");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_temp_rec_no&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_Finance_no&data_val=\"+obj.value+\"&ac_status=VERIFY2&ac_status2=ACTIVATED\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M4')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M5')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_Receipt_licencee_settlement&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M6')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_Curr_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_User&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M8')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_Receipt_Boook_No&data_val=\"+obj.value;");
				
      out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M10')");				
			out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_XMLFile?chksql=get_sysdate\";");	
				
		//	out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_TEMP_REC_NO.value==\"\" && document.Form1.SCREEN_NAME.value!=\"NEW\" ){  ");  //modified nuwan de silva 27-06-07
			out.println("DIV_TXT_TEMP_REC_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  ");   //added by nuwan de silva 27-06-07
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TRN_DATE_DD.value==\"\" || document.Form1.TXT_TRN_DATE_MM.value==\"\" || document.Form1.TXT_TRN_DATE_YY.value==\"\" ){  "); 
			out.println("DIV_TXT_TRN_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_EXCHANGE_RATE.value==\"\"){  "); 
			out.println("DIV_TXT_EXCHANGE_RATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_COLLECTION_OFFICER.value==\"\"){  "); 
			out.println("DIV_TXT_COLLECTION_OFFICER.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
      out.println("else if(document.Form1.TXT_REC_BOOK_NO.value==\"\"){  "); 
			out.println("DIV_TXT_REC_BOOK_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_RECEIPT_NO.value==\"\"){  "); 
			out.println("DIV_TXT_RECEIPT_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			
			

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println(" assign_hidden_values();");
						
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Temp_Receipt';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Temp_Receipt';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Temp_Receipt';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Collection_Temp_receipt\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Temporary Receipts-Entry - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Temporary Receipts-Entry - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
		//	out.println("document.Form1.BUT_TXT_CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_TRN_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_TRN_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_TRN_DATE_YY.disabled=true;"); 
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_SETTELMENT_MODE.disabled=true;"); 
			out.println("document.Form1.TXT_BANK_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_BRANCH_CODE.disabled=true;"); 
			//out.println("document.Form1.BUT_TXT_BRANCH_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ACCOUNT_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_ACCOUNT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CURR_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_CURR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE.disabled=true;"); 
			out.println("document.Form1.TXT_TRN_AMOUNT_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_COLLECTION_OFFICER.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_COLLECTION_OFFICER.disabled=true;"); 
			out.println("document.Form1.TXT_RECEIPT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REC_BOOK_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_REC_BOOK_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHEQUE_NO.disabled=true;"); 
			out.println("document.Form1.CHEQUE_DAY.disabled=true;"); 
			out.println("document.Form1.CHEQUE_MONTH.disabled=true;"); 
			out.println("document.Form1.CHEQUE_YEAR.disabled=true;"); 
      out.println("document.Form1.OTHER_CHARGES.disabled=true;"); 
      out.println("document.Form1.TXT_EXCHANGE_RATE.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.TXT_COLLECTION_OFFICER.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_COLLECTION_OFFICER.disabled=true;"); 
			out.println("document.Form1.TXT_RECEIPT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REC_BOOK_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_REC_BOOK_NO.disabled=true;"); 
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");
			out.println("document.Form1.hid_save_status.value=\"Deactivate\";"); 
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
			/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			*/
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\"");
			
		//	out.println("window.open(m_url);");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_3();");
			out.println("		}"); 
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
			//out.println("alert('2345');");
	  	out.println("		}"); 
			out.println("		if(IfCount==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
		  out.println("		if(IfCount==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}"); 	
		
		  out.println("		if(IfCount==\"8\"){"); 
			out.println("		help_value_assign_8();"); 
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
			out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
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
			
			
			
			
			
			out.println("function clear_data(IfCount) {");
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("document.Form1.TXT_TEMP_REC_NO.value='';");
		//	out.println("document.Form1.TXT_TEMP_REC_NO.focus();");
			out.println("	}");
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value='';"); 
			//out.println("document.Form1.TXT_FINANCE_NO.focus();"); 
			out.println("}");
			
			out.println("		if(IfCount==\"4\"){"); 
			out.println("document.Form1.TXT_BRANCH_CODE.value='';");
			out.println("document.Form1.TXT_BRANCH_NAME.value='';");
			//out.println("document.Form1.TXT_BRANCH_CODE.focus();"); 
			out.println("	}");
			
			out.println("		if(IfCount==\"5\"){"); 
			out.println("document.Form1.TXT_ACCOUNT_NO.value='';");
			//out.println("document.Form1.TXT_ACCOUNT_NO.focus();");
			out.println("	}");
			
			out.println("		if(IfCount==\"7\"){"); 
			out.println("document.Form1.TXT_CURR_CODE.value='';"); 
			//out.println("document.Form1.TXT_CURR_CODE.focus();"); 
			out.println("}");
			
			out.println("		if(IfCount==\"6\"){"); 
			out.println("document.Form1.TXT_COLLECTION_OFFICER.value='';"); 
			//out.println("document.Form1.TXT_COLLECTION_OFFICER.focus();"); 
			out.println("	}");
			
				out.println("		if(IfCount==\"8\"){"); 
			out.println("document.Form1.TXT_REC_BOOK_NO.value='';"); 
			//out.println("document.Form1.TXT_REC_BOOK_NO.focus();"); 
			out.println("	}");
			
			
					
			
			out.println("}");
			
			
			
			
			
			
			out.println("function help_button_1() {"); 
			//out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			//out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
				//out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@VERIFY2@\";"); 
				out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"VERIFY2@\"+\"ACTIVATED@\";"); 
				
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_sql','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.CLIENT_ADDRESS.value=oBj.valout[5];"); //YYYYYYY
			out.println("}"); 

			out.println("function help_button_2() {"); 
		//	out.println("    document.Form1.hid_help_type.value=\"2\";"); 
		//	out.println("    m_sql = \"TXT_CLIENT_CODE\";"); 
		//	out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CLIENT_CODE','2');"); 
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			//out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			//out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_BANK_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			out.println("    Crit = document.Form1.TXT_BANK_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_BANK_CODE_sql','3');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			//out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			//out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			out.println("    Crit = document.Form1.TXT_BRANCH_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_BRANCH_CODE_sql','4');"); 
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {");
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[4];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			//out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			//out.println("    m_sql = \"m_help_TXT_ACCOUNT_CODE_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_ACCOUNT_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			out.println("    Crit = document.Form1.TXT_ACCOUNT_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_ACCOUNT_CODE_sql','5');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_ACCOUNT_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[5];"); 
			out.println("}"); 
			
			
			
		

			out.println("function help_button_6() {"); 
		//	out.println("    document.Form1.hid_help_type.value=\"6\";"); 
		//	out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
		//	out.println("    m_criteria = document.Form1.TXT_COLLECTION_OFFICER.value+\"@Y@\";"); 
		//	out.println("    HelpBox('1','10','0');"); 
		
			out.println("    Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','4',Crit,'m_help_TXT_USER_ID_sql','6');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_REC_BOOK_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_RECEIPT_NO.value=\"\";"); 
			
			out.println("    document.Form1.TXT_REC_BOOK_NO.disabled=false;"); 
			out.println("    document.Form1.BUT_TXT_REC_BOOK_NO.disabled=false;"); 
			
			out.println("}"); 
			
			
			out.println("function help_button_7() {"); 
			//out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			//out.println("    m_sql = \"m_help_TXT_ACCOUNT_CODE_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_ACCOUNT_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			out.println("    Crit = document.Form1.TXT_CURR_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CURR_CODE_sql','7');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7() {"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_8() {"); 
			
			//out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			//out.println("    m_sql = \"m_help_TXT_ACCOUNT_CODE_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_ACCOUNT_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			//out.println("    Crit = document.Form1.TXT_REC_BOOK_NO.value+\"@Y@\";"); 
			out.println("    Crit =document.Form1.TXT_REC_BOOK_NO.value+\"@\"+document.Form1.TXT_COLLECTION_OFFICER.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_REC_BOOK_NO_sql','8');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_8() {"); 
			out.println("    document.Form1.TXT_REC_BOOK_NO.value=oBj.valout[2];"); 
			out.println("if(oBj.valout[4]==\"OLD\"){");
			out.println("assignState('M9')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_Last_Receipt_No&data_val=\"+document.Form1.TXT_COLLECTION_OFFICER.value+\"&data_val2=\"+document.Form1.TXT_REC_BOOK_NO.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			out.println("else{");
			out.println("    document.Form1.TXT_RECEIPT_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_RECEIPT_NO.disabled=false;"); 

			out.println("}");
			
			
			out.println("}"); 



			out.println("function help_update() {"); 
			//out.println("    document.Form1.hid_help_type.value=\"99\";"); 
		//	out.println("    m_sql = \"m_help_TXT_TEMP_REC_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    Crit = document.Form1.TXT_TEMP_REC_NO.value+\"@Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    Crit = document.Form1.TXT_TEMP_REC_NO.value+\"@N@\";"); 
			out.println("    } ");
			//out.println("    HelpBox('1','10','0');"); 
						
			out.println("    HelpBox('1','10','13',Crit,'m_help_TXT_TEMP_REC_NO_sql','99');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_TEMP_REC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
			//out.println("    document.Form1.TXT_TRN_DATE.value=oBj.valout[5];");
			out.println("getDateValues(oBj.valout[6]);");
			out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[7];"); 
			out.println("format_number(document.Form1.TXT_AMOUNT,25);");
			out.println("    document.Form1.TXT_SETTELMENT_MODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[10];");    //TXT_BRANCH_NAME
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[19];");
			out.println("    document.Form1.TXT_ACCOUNT_NO.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=oBj.valout[13];"); 
			out.println("format_number(document.Form1.TXT_EXCHANGE_RATE,10);");
			out.println("    document.Form1.TXT_TRN_AMOUNT_CURR.value=oBj.valout[14];"); 
			out.println("format_number(document.Form1.TXT_TRN_AMOUNT_CURR,25);");
			out.println("    document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_RECEIPT_NO.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_REC_BOOK_NO.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_CHEQUE_NO.value=oBj.valout[18];");
						
			out.println(" if( oBj.valout[20]!='0'){");
			out.println("    document.Form1.OTHER_CHARGES.value='Y';");
			out.println("  get_other_charges();");
			out.println("    document.Form1.TXT_RENTAL_OTHER_INV.value=format_noobject(parseFloat(oBj.valout[20]));"); 
			out.println("    document.Form1.TXT_INSURANCE_PREMIUM.value=format_noobject(parseFloat(oBj.valout[21]));"); 
			out.println("    document.Form1.TXT_LUX_TAX.value=format_noobject(parseFloat(oBj.valout[22]));");
			out.println("    document.Form1.TXT_REVENUE_LICENCY.value=format_noobject(parseFloat(oBj.valout[23]));");
			out.println("    document.Form1.TXT_RMV_REG_FEES.value=format_noobject(parseFloat(oBj.valout[24]));");
			out.println("    document.Form1.TXT_TOT_ENTERED.value=format_noobject((parseFloat(oBj.valout[20])+parseFloat(oBj.valout[21])+parseFloat(oBj.valout[22])+parseFloat(oBj.valout[23])+parseFloat(oBj.valout[24])));");
			out.println("    document.Form1.TXT_BALANCE_PENDING.value=format_noobject((parseFloat(oBj.valout[7])-(parseFloat(oBj.valout[20])+parseFloat(oBj.valout[21])+parseFloat(oBj.valout[22])+parseFloat(oBj.valout[23])+parseFloat(oBj.valout[24])) ));"); 
			out.println("}"); 
			
			out.println("getValueDate(oBj.valout[25]);"); 
			out.println("getChequeDate(oBj.valout[26]);");
			
			out.println(" if( oBj.valout[27]!='-'){");
			out.println("    document.Form1.PAY_TYPE.value='THIRD';"); //KKKKKK 
			out.println("   display_row_third_party();");
			out.println("    document.Form1.CLIENT_NAME_1.value=oBj.valout[27];");
			out.println("    document.Form1.CLIENT_ADDRESS_1.value=oBj.valout[28];"); 
			out.println("  document.Form1.hid_TXT_THIRD_PARTY_NAME.value = document.Form1.elements['CLIENT_NAME_1'].value;");
			out.println("  document.Form1.hid_TXT_THIRD_PARTY_ADD.value  = document.Form1.elements['CLIENT_ADDRESS_1'].value;");
			
			out.println("}");
			out.println("    document.Form1.CLIENT_ADDRESS.value=oBj.valout[29];");
			
			out.println(" if((document.Form1.SCREEN_NAME.value==\"DACT\" && document.Form1.OTHER_CHARGES.value==\"Y\")||(document.Form1.SCREEN_NAME.value==\"RACT\" && document.Form1.OTHER_CHARGES.value==\"Y\")){"); 
			out.println("    document.Form1.TXT_RENTAL_OTHER_INV.disabled=true;");
			out.println("    document.Form1.TXT_INSURANCE_PREMIUM.disabled=true;");
      out.println("    document.Form1.TXT_LUX_TAX.disabled=true;");
      out.println("    document.Form1.TXT_REVENUE_LICENCY.disabled=true;");
      out.println("    document.Form1.TXT_RMV_REG_FEES.disabled=true;");
			out.println("}"); 
			
			out.println("document.Form1.TXT_EXCHANGE_RATE.disabled=true;"); 
			out.println("}"); 
			
			
			out.println("function getValueDate(dval){");
			out.println("document.Form1.VAL_DAY.value=dval.substring(0,2)");
			out.println("document.Form1.VAL_MONTH.value=dval.substring(3,5)");
			out.println("document.Form1.VAL_YEAR.value=dval.substring(6,10)");
			out.println("}");
			
			
			out.println("function getChequeDate(dval){");
			out.println("document.Form1.CHEQUE_DAY.value=dval.substring(0,2)");
			out.println("document.Form1.CHEQUE_MONTH.value=dval.substring(3,5)");
			out.println("document.Form1.CHEQUE_YEAR.value=dval.substring(6,10)");
			out.println("}");
			
			
			
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function enable_rate(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE.disabled=false;"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE.focus();;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_TRN_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_TRN_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_TRN_DATE_YY.value=v_yy;");
			out.println("  }");				
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.CHEQUE_DAY.value=v_dd;");
			out.println("     document.Form1.CHEQUE_MONTH.value=v_mm;");
			out.println("     document.Form1.CHEQUE_YEAR.value=v_yy;");
			out.println("  }");				
			out.println("}");		
			
			
			out.println("function calculate_transaction_amount(obj1,size1,obj2,size2){");
			out.println("if(obj1.value!='' && obj2.value!='')"); 
			out.println("if(isnumberok(obj1,size1)){"); 
			out.println("if(isnumberok(obj2,size2))"); 
			
			out.println("document.Form1.TXT_TRN_AMOUNT_CURR.value=format_noobject((parseFloat(unformat_noobject(obj1.value)))*(parseFloat(unformat_noobject(obj2.value))));");
			//out.println("format_noobject(document.Form1.TXT_TRN_AMOUNT_CURR.value);");
			out.println("}"); 
			out.println("}"); 
		
		
		  out.println("function header(){");
			out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><b><u>Other Charges</u></TD>'+");
			out.println("'<td WIDTH=\"30%\"></td>'+");
			out.println("'<td WIDTH=\"20%\"></td>'+");
			out.println("'<td WIDTH=\"30%\"></td></tr>'+");
			out.println("'</table>';");
			out.println("}");				
			
			out.println("function get_other_charges(){");
			out.println("m_table_other_charges.innerHTML=\"\" ");
			//out.println("inv.innerHTML=\"\" ");
			out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\"){");
			out.println("header();");
			out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"26%\"  align=\"left\">Rental and Other Invoices</TD>'+");
			out.println("'<TD WIDTH=\"24%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Total Amount entered</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TOT_ENTERED maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
			out.println("'</tr>'+");
  		out.println("'<TD WIDTH=\"26%\"  align=\"left\">Insurance Premium</TD>'+");
			out.println("'<TD WIDTH=\"24%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_INSURANCE_PREMIUM maxlength=\"23\"style=\"{text-align:right;}\"  size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Balance Pending</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_BALANCE_PENDING maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\" disabled ></TD>'+");
			out.println("'</tr>'+");
			out.println("'<TD WIDTH=\"26%\"  align=\"left\">Luxury Tax</TD>'+"); //modified by nwuan de silva  04-07-07
			out.println("'<TD WIDTH=\"24%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_LUX_TAX maxlength=\"23\" size=\"10\"  style=\"{text-align:right;}\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'<tr>'+");
			out.println("'<TD WIDTH=\"26%\"  align=\"left\">Revenue License</TD>'+");
			out.println("'<TD WIDTH=\"24%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REVENUE_LICENCY maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'<tr>'+");
			out.println("'<TD WIDTH=\"26%\"  align=\"left\">RMV Registration Fees</TD>'+");
			out.println("'<TD WIDTH=\"24%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RMV_REG_FEES maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
	//		out.println("   document.Form1.TXT_TOT_ENTERED.value=format_noobject(document.Form1.AMOUNT.value);");
			out.println("   document.Form1.TXT_BALANCE_PENDING.value=format_noobject(document.Form1.TXT_AMOUNT.value);");
		//	out.println("m_balance=document.Form1.AMOUNT.value");		
			out.println("}");		
		/*	out.println("else {");		
			out.println("if(document.Form1.AMOUNT.value!='' && isnumberok(document.Form1.AMOUNT,25)){");
		  out.println("if(document.Form1.EXCHANE_RATE.value!='' && isnumberok(document.Form1.EXCHANE_RATE,6)){");
      out.println("   makeRequest(m_url,'4','RepAmt');");
			out.println("}");				
			out.println("}");
			out.println("}");		
	*/		
			out.println("}");			

		
		  out.println("function calculate_balance(obj){");
			out.println("m_rental_other_inv=0;");
			out.println("m_insurance_premium=0;");
			out.println("m_lux_tax=0;");
			out.println("m_revenue_lux=0;");
			out.println("m_rmv_reg_fee=0;");
			out.println("m_tendered=0;");
			out.println("m_tot=0;");
			out.println("m_balance=0;");
			out.println("m_amount_entered=0;");
			out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" ){ "); //added by nuwan de silva 06-07-07
			out.println("if(document.Form1.TXT_AMOUNT.value!=\"\" && isnumberok(document.Form1.TXT_AMOUNT,25)){"); 
			out.println("m_amount_entered=parseFloat(unformat_noobject(document.Form1.TXT_AMOUNT.value))");
			out.println("}"); 
			out.println("if(document.Form1.TXT_RENTAL_OTHER_INV.value!=\"\" && isnumberok(document.Form1.TXT_RENTAL_OTHER_INV,25)){"); 
			out.println("m_rental_other_inv=unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value)");
			//out.println("cal_rep_amount(document.Form1.TXT_RENTAL_OTHER_INV.value);");
			//out.println("  get_invoice();"); //Comment By Chandana on 17/09/2007
			//out.println("     get_contract();"); //Comment By Chandana on 19/10/2007
			out.println("}"); 
			out.println("if(document.Form1.TXT_INSURANCE_PREMIUM.value!=\"\" && isnumberok(document.Form1.TXT_INSURANCE_PREMIUM,25)){"); 
			out.println("m_insurance_premium=unformat_noobject(document.Form1.TXT_INSURANCE_PREMIUM.value)");
			out.println("}"); 
			out.println("if(document.Form1.TXT_LUX_TAX.value!=\"\" && isnumberok(document.Form1.TXT_LUX_TAX,25)){"); 
			out.println("m_lux_tax=unformat_noobject(document.Form1.TXT_LUX_TAX.value)");
			out.println("}"); 
			out.println("if(document.Form1.TXT_REVENUE_LICENCY.value!=\"\" && isnumberok(document.Form1.TXT_REVENUE_LICENCY,25)){"); 
			out.println("m_revenue_lux=unformat_noobject(document.Form1.TXT_REVENUE_LICENCY.value)");
			out.println("}"); 
			out.println("if(document.Form1.TXT_RMV_REG_FEES.value!=\"\" && isnumberok(document.Form1.TXT_RMV_REG_FEES,25)){"); 
			out.println("m_rmv_reg_fee=unformat_noobject(document.Form1.TXT_RMV_REG_FEES.value)");
			out.println("}"); 
			out.println("if(document.Form1.TXT_SETTELMENT_MODE.value==\"CASH\" && document.Form1.TEN_AMOUNT.value!=\"\" && isnumberok(document.Form1.TEN_AMOUNT,25)){"); 
			out.println("m_tendered=unformat_noobject(document.Form1.TEN_AMOUNT.value)");
			out.println("}"); 
			//out.println("m_tot=parseFloat(m_rental_other_inv)+parseFloat(m_insurance_premium)+parseFloat(m_lux_tax)+parseFloat(m_revenue_lux)+parseFloat(m_rmv_reg_fee)+parseFloat(m_tendered)");
			out.println("m_tot=parseFloat(m_rental_other_inv)+parseFloat(m_insurance_premium)+parseFloat(m_lux_tax)+parseFloat(m_revenue_lux)+parseFloat(m_rmv_reg_fee)"); //modified by nuwan de silva 06-07-07
			out.println("if(m_tot>m_amount_entered){");
			out.println("alert('The amount entered is more than the total value')"); 
			out.println("obj.value='';"); 
			//out.println("calculate_balance(obj);");
			out.println("}"); 
			out.println("else {"); 
			out.println("document.Form1.TXT_TOT_ENTERED.value=m_tot");
			out.println("m_balance=parseFloat(m_amount_entered)- parseFloat(m_tot)");
			out.println("document.Form1.TXT_BALANCE_PENDING.value=m_balance;");
			out.println("}"); 
			out.println("}");   //added by nuwan de silva 06-07-07
			out.println("}"); 
		
		
		  out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			 
		 	out.println("function assign_hidden_values(){");

			out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" ){");
			out.println("document.Form1.hid_TXT_RENTAL_OTHER_INV.value=document.Form1.TXT_RENTAL_OTHER_INV.value"); 
			out.println("document.Form1.hid_INSURANCE_PREMIUM.value=document.Form1.TXT_INSURANCE_PREMIUM.value"); 
			out.println("document.Form1.hid_TXT_LUX_TAX.value=document.Form1.TXT_LUX_TAX.value"); 
			out.println("document.Form1.hid_TXT_REVENUE_LICENCY.value=document.Form1.TXT_REVENUE_LICENCY.value"); 
			out.println("document.Form1.hid_TXT_RMV_REG_FEES.value=document.Form1.TXT_RMV_REG_FEES.value"); 
			out.println("m_balance=parseFloat(unformat_noobject(document.Form1.TXT_BALANCE_PENDING.value))");  //added by nwuan de sivla 09-07-07
			out.println("}"); 
			out.println("else if(document.Form1.OTHER_CHARGES.value==\"N\" ){");
			out.println("m_balance=0;"); 
			//out.println("document.Form1.hid_TXT_RENTAL_OTHER_INV.value=\"\""); 
			//out.println("document.Form1.hid_INSURANCE_PREMIUM.value=\"\""); 
			//out.println("document.Form1.hid_TXT_LUX_TAX.value=\"\""); 
			//out.println("document.Form1.hid_TXT_REVENUE_LICENCY.value=\"\""); 
			//out.println("document.Form1.hid_TXT_RMV_REG_FEES.value=\"\""); 
			out.println("}"); 
			out.println("}"); 
		  
			out.println("function get_sysdate(val) {");
			out.println(" document.Form1.hid_chk_status.value='M10';");
			out.println("   makeRequest('SysDate');");
			out.println("}"); 
			
			out.println("function display_row_third_party(){");
			out.println("m_table_third_party_del.innerHTML=\"\" ");
			
			out.println("if(document.Form1.PAY_TYPE.value==\"THIRD\"){"); 
			out.println("m_table_third_party_del.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+");
			out.println("'<td width=\"30%\" id=pay_name>Third Party Name</td>'+");
			out.println("'<td width=\"20%\"><input name=\"CLIENT_NAME_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" ></td>'+");
			out.println("'<td width=\"20%\" id=pay_address>Third Party Address</td>'+");
			out.println("'<td width=\"30%\"> <input name=\"CLIENT_ADDRESS_1\" type=\"text\" style=\"width:300px;\" maxlength=\"200\" class=\"txt_input\" >'+");
			out.println("'</td>'+");
			out.println("'</tr>'+");	
			out.println("'</table>';");
			out.println("}");
			out.println("}");
			
			out.println("function set_address_pay_type(){");
			out.println("if(document.Form1.PAY_TYPE.value!=\"CLIENT\"){");
			out.println("  document.Form1.elements['CLIENT_NAME_1'].value  =document.Form1.TXT_CLIENT_NAME.value;");
			out.println("  document.Form1.elements['CLIENT_ADDRESS_1'].value  =document.Form1.CLIENT_ADDRESS.value;"); 
			out.println("  document.Form1.hid_TXT_THIRD_PARTY_NAME.value = document.Form1.elements['CLIENT_NAME_1'].value;");
			out.println("  document.Form1.hid_TXT_THIRD_PARTY_ADD.value  = document.Form1.elements['CLIENT_ADDRESS_1'].value;");
			out.println("}");				
			out.println("}");				

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_sysdate()\">"); //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_RPT_COLLECTION_TEMP_RECEIPT\">"); 
			out.println("<input type=hidden name=\"hid_TXT_RENTAL_OTHER_INV\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_INSURANCE_PREMIUM\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_TXT_LUX_TAX\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_TXT_REVENUE_LICENCY\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_TXT_RMV_REG_FEES\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_TXT_THIRD_PARTY_NAME\" value=\"\">");
			out.println("<input type=hidden name=\"hid_TXT_THIRD_PARTY_ADD\" value=\"\">");
			
			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Temporary Receipts-Entry - New </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
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


			out.println("<table align='center' width='100%' class='table' border='0'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TEMP_REC_NO'  class=div_input>Temp Receipt Number *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_TEMP_REC_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_TEMP_REC_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("<td width='30%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>");
			out.println("<td >Settlement Mode</td>");
			out.println("<td><SELECT name=\"TXT_SETTELMENT_MODE\" class=\"txt_input\" onChange=\"\"> ");
			out.println("<OPTION value=\"CHEQUE\" >Cheque  </OPTION>");
			out.println("<OPTION value=\"CASH\"   >Cash    </OPTION>");
			out.println("</SELECT></TD>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TRN_DATE'  class=div_input>Transaction Date *[DD-MM-YYYY]</DIV></td>"); 
			//out.println("<td width='25%' >Transaction Date * [DD-MM-YYYY]</td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REG_DATE' maxlength='10' size='10' onBlur=\"checkDate(document.Form1.TXT_REG_DATE.value)\"></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_TRN_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_TRN_DATE_MM' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_TRN_DATE_YY' maxlength='4' size='4' onBlur='checkMonthLength(document.Form1.TXT_TRN_DATE_DD,document.Form1.TXT_TRN_DATE_MM,document.Form1.TXT_TRN_DATE_YY)'><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
		
		  out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TRN_DATE'  class=div_input>Value Date *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='VAL_DAY' maxlength='2' size='2' disabled>");
			out.println("                 <input class='txt_input5' type='text' name='VAL_MONTH' maxlength='2' size='2' disabled>");
			out.println("                 <input class='txt_input5' type='text' name='VAL_YEAR' maxlength='4' size='4' onBlur='checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)' disabled><a href style='{cursor:hand; }' onclick=load_calendar('2') >   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
		
		
			
			
			out.println("<tr>"); 

			//out.println("<td width='30%' >Finance Number</td>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number *</DIV></td>");    //modified by nuwan de silva 27-06-07
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\" >"); //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\"
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_CLIENT_CODE)\" disabled></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style=\"width:250px;\" maxlength='250' size='22'  onblur=\"\" disabled></td>"); 
			out.println("<td width='20%' >Client Address</td>");
			out.println("<td width='30%'> <input name=\"CLIENT_ADDRESS\" type=\"text\" style=\"width:300px;\" maxlength=\"200\" class=\"txt_input\" disabled>");
			out.println("</td>");
			
			
			/*out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");*/
			out.println("</tr>"); 
			
				out.println("<tr>"); //Added by Chandana on 24/10/2007
				out.println("<td width=\"30%\">Payee Type</td>");
				out.println("<td width=\"20%\"><SELECT name=\"PAY_TYPE\" class=\"txt_input\" onChange=\" display_row_third_party(), set_address_pay_type()\" > ");
				out.println("<OPTION value=\"CLIENT\">Client</OPTION>");
				out.println("<OPTION value=\"THIRD\">Third Party</OPTION>");
				out.println("</SELECT></TD>");
				out.println("<td width='*%'>&nbsp;</td>");
				out.println("<td width='*%'>&nbsp;</td> ");
				out.println("</tr>");
								
				out.println("</table>");	   

				out.println("<table align=\"center\" width=\"100%\" class='table' border='0'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_third_party_del'></DIV></td>"); 
		    out.println("</tr>"); 
			  out.println("</table>");
			
			
			
			//out.println("<tr >"); 

		//	out.println("<td width='30%' ><DIV id='DIV_TXT_TRN_DATE'  class=div_input>Transaction Date *</DIV></td>"); 
		//	out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TRN_DATE' maxlength='7' size='7'></td>"); 
		//	out.println("<td width='*%'></td>"); 
		//	out.println("</tr>"); 
		//	out.println("<tr >"); 
			
			
			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TRN_DATE'  class=div_input>Transaction Date *[DD-MM-YYYY]</DIV></td>"); 
			//out.println("<td width='25%' >Transaction Date * [DD-MM-YYYY]</td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REG_DATE' maxlength='10' size='10' onBlur=\"checkDate(document.Form1.TXT_REG_DATE.value)\"></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_TRN_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_TRN_DATE_MM' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_TRN_DATE_YY' maxlength='4' size='4' onBlur='checkMonthLength(document.Form1.TXT_TRN_DATE_DD,document.Form1.TXT_TRN_DATE_MM,document.Form1.TXT_TRN_DATE_YY)'><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
				     
			/*				
			out.println("<tr class=tr_input>");
			out.println("<td >Settlement Mode</td>");
			out.println("<td><SELECT name=\"TXT_SETTELMENT_MODE\" class=\"txt_input\" onChange=\"\"> ");
			out.println("<OPTION value=\"CHEQUE\" >Cheque  </OPTION>");
			out.println("<OPTION value=\"CASH\"   >Cash    </OPTION>");
				
			out.println("</SELECT></TD>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>"); */
			
			/*out.println("<tr >"); 
			out.println("<td width='30%' >Settelment Mode *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SETTELMENT_MODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr >"); 
			out.println("<td width='26%' >Cheque No/ Ref. No </td>"); 
			out.println("<td width='24%' ><input class='txt_input' type='text' name='TXT_CHEQUE_NO' maxlength='8' size='8'></td>"); 
			out.println("<td width='20%'>&nbsp</td>"); 
			out.println("<td width='30%'>&nbsp</td>");
			out.println("</tr>");   
		  
			out.println("<tr>");  //Added by Chandana on 24/10/2007
			out.println("<td  ><DIV id='DIV_TXT_TRN_DATE'  class=div_input>Cheque Date </DIV></td>"); 
			out.println("<td  ><input class='txt_input5' type='text' name='CHEQUE_DAY' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='CHEQUE_MONTH' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='CHEQUE_YEAR' maxlength='4' size='4' onBlur='checkMonthLength(document.Form1.CHEQUE_DAY,document.Form1.CHEQUE_MONTH,document.Form1.CHEQUE_YEAR)' ><a href style='{cursor:hand; }' onclick=load_calendar('3') >   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='*%' >Payer Account </td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_ACCOUNT_NO' maxlength='20' size='20' onblur=\"assignState('M5'),makeRequest(document.Form1.TXT_ACCOUNT_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ACCOUNT_NO' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='*%' >Payer Branch Code</td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10' onblur=\"assignState('M4'),makeRequest(document.Form1.TXT_BRANCH_CODE)\" disabled></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_BRANCH_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			
			out.println("<tr>"); 
			out.println("<td width='*%' >Payer Bank Code</td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_BANK_CODE' maxlength='5' size='5' disabled></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_BANK_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			//------Added by Chandana on 22/05/2007 for Ref No.45 -------------------//
   			out.println("<tr class=tr_input>");
				out.println("<td  width='*%'>Payer Branch Name</td>");
				out.println("<td width='*%' > <input name=\"TXT_BRANCH_NAME\"   type=\"text\" maxlength=\"100\"  onblur=\"\" class=\"txt_input\" style=\"width: 175px\" disabled > ");
				out.println("</td>");
				out.println("<td width='*%'></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
        //-------- End Ref No.45 -----------------------------//


	    /*  out.println("<tr >"); 
				out.println("<td width='28%' >Cheque Number </td>"); 
				out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CHEQUE_NO' maxlength='8' size='8'></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("<td width='*%'></td>");
				out.println("</tr>"); 
       */
		
			
			out.println("<tr >"); 
			out.println("<td width='*%' >Currency Code </td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_CURR_CODE' maxlength='10' size='10' onblur=\"assignState('M6'),makeRequest(document.Form1.TXT_CURR_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CURR_CODE' value=\"Help\" onClick=\"help_button_7()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			
			out.println("<tr class=tr_input>");
			out.println("<td ID=OTH_CHARGE width='*%'>Receipt Amount Include Other Charges </td>");
			out.println("<td width='*%'><SELECT  onchange=get_other_charges() name=OTHER_CHARGES class=\"txt_input\" > ");
			out.println("<OPTION value=\"N\" selected>No</OPTION>");
			out.println("<OPTION value=\"Y\">Yes</OPTION>");
			out.println("</SELECT></TD>");
			out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>");
			
			
			out.println("<tr>"); 
			out.println("<td width='*%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Amount *</DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_AMOUNT' maxlength='25' size='22' onBlur=\"check_number(document.Form1.TXT_AMOUNT,25),enable_rate(document.Form1.TXT_AMOUNT,10),calculate_transaction_amount(document.Form1.TXT_AMOUNT,25,document.Form1.TXT_EXCHANGE_RATE,10)\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 

      out.println("<tr >"); 
			out.println("<td width='*%' ><DIV id='DIV_TXT_EXCHANGE_RATE'  class=div_input>Exchange Rate *</DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_EXCHANGE_RATE' maxlength='10' size='22' onBlur=\"check_number(document.Form1.TXT_EXCHANGE_RATE,10),calculate_transaction_amount(document.Form1.TXT_AMOUNT,25,document.Form1.TXT_EXCHANGE_RATE,10)\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr >"); 

			out.println("<td width='*%' >Transaction Amount </td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_TRN_AMOUNT_CURR' maxlength='25' size='22' onBlur=\"check_number(document.Form1.TXT_TRN_AMOUNT_CURR,25)\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			//Added by Chandana on 22/10/2007
			/* out.println("<tr class=tr_input>");
			out.println("<td ID=OTH_CHARGE width=\"30%\">Receipt Amount Include Other Charges </td>");
			out.println("<td width=\"20%\"><SELECT  onchange=get_other_charges() name=OTHER_CHARGES class=\"txt_input\" > ");
			out.println("<OPTION value=\"N\" selected>No</OPTION>");
			out.println("<OPTION value=\"Y\">Yes</OPTION>");
			out.println("</SELECT></TD>");
			out.println("<td width='*%'></td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>"); */
			out.println("</table>");	   
			
			out.println("<table align=\"center\" width=\"100%\" class='table' border='0'>"); 
		  out.println("<tr > ");  
		  out.println("<td width=\"100%\"><DIV ID='m_table_other_charges'></DIV></td>");
	    out.println("</tr>"); 
		  out.println("</table>");	
								  
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
		  out.println("<tr > ");
		//out.println("<td width='30%' >Collection Officer *</td>"); 
			out.println("<td width='26%' ><DIV id='DIV_TXT_COLLECTION_OFFICER'  class=div_input>Collection Officer *</DIV></td>"); 
			out.println("<td width='24%' ><input class='txt_input' type='text' name='TXT_COLLECTION_OFFICER' style=\"width:170px;\" maxlength='10' size='18' onblur=\"assignState('M7'),makeRequest(document.Form1.TXT_COLLECTION_OFFICER)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_COLLECTION_OFFICER' value=\"Help\" onClick=\"help_button_6()\"></td>"); 
			out.println("<td width='30%'></td>"); 
			out.println("<td width='20%'></td>");
			out.println("</tr>"); 
			
		//	out.println("<tr >"); 
		//	out.println("<td width='30%' >Receipt Number </td>"); 
		//	out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RECEIPT_NO' maxlength='15' size='15'></td>"); 
		//	out.println("<td width='*%'></td>"); 
		//	out.println("</tr>"); 
			
			out.println("<tr >"); 
			//out.println("<td width='30%' >Receipt Book Number * </td>"); 
			out.println("<td width='*%' ><DIV id='DIV_TXT_REC_BOOK_NO'  class=div_input>Receipt Book Number *</DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_REC_BOOK_NO' maxlength='20' size='20' onblur=\"assignState('M8'),makeRequest(document.Form1.TXT_REC_BOOK_NO)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_REC_BOOK_NO' value=\"Help\" onClick=\"help_button_8()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			//out.println("<td width='30%' >Receipt Book Number *</td>"); 
			out.println("<td width='*%' ><DIV id='DIV_TXT_RECEIPT_NO'  class=div_input>Receipt Number *</DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_RECEIPT_NO' maxlength='15' size='15' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
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
      out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
