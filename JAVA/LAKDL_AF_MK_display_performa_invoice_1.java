
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - PRO FORMA INVOICE
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_display_performa_invoice_1 extends javax.servlet.http.HttpServlet { 
 
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			stmt=conn.createStatement();
							
			String m_prof_inv_no = req.getParameter("prof_inv_no");
			String m_appNo = req.getParameter("APP_NO");

			if (m_appNo.trim().equals("")) {
				out.println("Sorry");
			}
			else{
			 
			
						 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Application Process - Pro Forma Invoice</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_chk_value=0;")	 ;
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already exists.');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("			if(data_vec.length==0 && (document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"EDIT\") && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				help_update('1','10','19','m_help_TXT_INVOICE_NO_sql','99');");
			//out.println("       document.Form1.TXT_INVOICE_NO.focus();"); 
			out.println("			}");
			
			
			out.println("			if(data_vec.length>0 && (document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"EDIT\") && document.Form1.hid_chk_status.value=='M1'){");
			out.println("    document.Form1.TXT_INVOICE_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_MODEL_NO.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=data_vec[5];"); 
			out.println("   if(data_vec[6]!='null') { "); 
			out.println("      document.Form1.TXT_REG_NO.value=data_vec[6];"); 
			out.println("		}");
			out.println("    if(data_vec[7]!='-') {"); 
			out.println("     getDateValues(data_vec[7]);"); 
			out.println("		}");
			out.println("    document.Form1.TXT_PRICING_NO.value=data_vec[8];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_NO.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_COLOUR.value=data_vec[10];"); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=data_vec[11];"); 
			out.println("    document.Form1.TXT_NET_PRICE.value=format_noobject(Number(data_vec[12]) );"); 
			out.println("    document.Form1.TXT_VAT.value=format_noobject(Number(data_vec[13]) );"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=format_noobject(Number(data_vec[14]) );"); 
			out.println("    document.Form1.TXT_TO_BE_DELIVERD_TO.value=data_vec[15];"); 
			out.println("    document.Form1.TXT_VALUE.value=format_noobject(Number(data_vec[16]) );"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=data_vec[17];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=data_vec[18];"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=data_vec[19];"); 
			out.println("    document.Form1.TXT_LOCATION.value=data_vec[20];");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[21];"); 
			out.println("    document.Form1.TXT_VENDOR_NAME.value=data_vec[22];"); 
			out.println("    document.Form1.TXT_INVOICE_DOC_NO.value=data_vec[23];"); 
			out.println("    document.Form1.TXT_FUEL_CONV_STS.value=data_vec[24];"); 
			out.println("    if(data_vec[25]!='null') {"); 
			out.println("     getDateValues_Due(data_vec[25]);"); 
			out.println("		}");
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
			
			out.println("			}");
			
			out.println("else	if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_ASSET_ID.value!=\"\"){");
			out.println("				help_button_2('1','10','4','m_help_TXT_ASSET_ID_sql2','2') ;");
			//out.println("       document.Form1.TXT_ASSET_ID.value=\"\""); 
			out.println("       document.Form1.TXT_ASSET_ID.focus()"); 
		  out.println("			}");
			
			out.println("else	if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_ASSET_ID.value!=\"\"){");
			out.println("    document.Form1.TXT_MODEL_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_NO.value=data_vec[2];"); 
			//out.println("    document.Form1.TXT_REG_NO.value=data_vec[3];"); 
			//out.println("    getDateValues(data_vec[4]);");
			
			out.println(" 	 if(document.Form1.TXT_SUB_MODEL_NO.value!=''){");
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
			out.println("			 }");
			out.println("			}");
			
			out.println("else	if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.TXT_MODEL_NO.value!=\"\"){");
			//out.println("				alert('Invalid Record, Use Help');");
			out.println("       document.Form1.TXT_MODEL_NO.value=\"\""); 
			out.println("       document.Form1.TXT_MODEL_NO.focus()"); 
		
			out.println("			}");
			
		//	out.println("else	if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.TXT_MODEL_NO.value!=\"\"){");
		//	out.println("       document.Form1.TXT_TRANSMISSION.value=data_vec[3];");
		//	out.println("       b_chk_value=1"); //Variable to Hold The Status Of Value innitialization
		//	out.println("			}");
			
			out.println("else	if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_SUB_MODEL_NO.value!=\"\"){");
			//out.println("				alert('Invalid Record, Use Help');");
			//out.println("       document.Form1.TXT_SUB_MODEL_NO.value=\"\""); 
			//out.println("       document.Form1.TXT_SUB_MODEL_NO.focus()"); 
			out.println("			}");
		
			out.println("else	if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_SUB_MODEL_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_TRANSMISSION.value=data_vec[8];"); 
			out.println("			}");
			
			out.println("	else if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M6' && document.Form1.TXT_CITY_CODE.value!=\"\"){");
			out.println("       help_button_6('1','10','2','m_help_TXT_CITY_CODE_sql','6'); "); 
			//out.println("       document.Form1.TXT_CITY_CODE.value=\"\" "); 
			out.println("       document.Form1.TXT_CITY_CODE.focus()  "); 
			out.println("			}");
			
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M7' && document.Form1.TXT_ENGINE_NO.value!=\"\"){");
			out.println("       alert('Record already exists.'); "); 
			out.println("       document.Form1.TXT_ENGINE_NO.value=\"\" "); 
			out.println("       document.Form1.TXT_ENGINE_NO.focus();  "); 
			out.println("			}");
			
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M8' && document.Form1.TXT_CHASSIS_NO.value!=\"\"){");
			out.println("       alert('Record already exists.'); "); 
			out.println("       document.Form1.TXT_CHASSIS_NO.value=\"\" "); 
			out.println("       document.Form1.TXT_CHASSIS_NO.focus();  "); 
			out.println("			}");
			
			out.println("	else if(data_vec.length == 0 && document.Form1.hid_chk_status.value=='M9' && document.Form1.TXT_PRICING_NO.value!=\"\"){");
			out.println("    help_button_5('1','10','15','PriceSql_invoice','5'); "); 
			out.println("    document.Form1.TXT_PRICING_NO.focus(); "); 
			out.println("			}");
			
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M9' && document.Form1.TXT_PRICING_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_VAT.value=format_noobject(data_vec[1]);"); 
			out.println("    document.Form1.TXT_NET_PRICE.value=format_noobject(data_vec[2]);");
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=format_noobject( Number(data_vec[1]) + Number(data_vec[2]) );");
			out.println("    document.Form1.TXT_VALUE.value = document.Form1.TXT_TOTAL_AMOUNT.value;"); 
			out.println("    document.Form1.TXT_CURR_CODE.value = data_vec[3];"); 
			out.println("			}");
			
			out.println("	else if(data_vec.length == 0 && document.Form1.hid_chk_status.value=='M10' && document.Form1.TXT_VENDOR_CODE.value!=\"\"){");
			out.println("    help_vendor('1','10','3','m_help_TXT_VENDOR_CODE_sql','77'); "); 
			out.println("    document.Form1.TXT_VENDOR_CODE.focus(); "); 
			out.println("			}");
			
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M10' && document.Form1.TXT_VENDOR_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_VENDOR_CODE.value=data_vec[0]; "); 
			out.println("    document.Form1.TXT_VENDOR_NAME.value=data_vec[1]; "); 
			out.println("			}");
			
			out.println("	else if(data_vec.length == 0 && document.Form1.hid_chk_status.value=='M11' && document.Form1.TXT_LOCATION_CODE.value!=\"\"){");
			out.println("    help_branch('1','10','3','m_help_TXT_MAS_VENDOR_LOCATION_sql','88'); "); 
			out.println("    document.Form1.TXT_LOCATION_CODE.focus(); "); 
			out.println("			}");
	
	
			out.println("	else if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='T1' && document.Form1.TXT_INVOICE_NO.value!=\"\"){");
			//out.println("alert('tttt')");
			out.println("    assign_invoice_val(data_vec); "); 
			out.println("			}");

			out.println("			}");
				
				
					
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_performa_invoice&data_val=\"+obj.value;");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_performa_invoice&data_val=\"+obj.value+\"&ac_status=N\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M3')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_asset_details&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_asset_details&data_val=\"+obj.value ;");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M4')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M5')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_model&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M9')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_val_pricing&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M10')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_vendor_creation2&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M11')");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_val_vendor_branch_location&data_val=\"+obj.value;");
			
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
					
			out.println("}");
      
			
			out.println("function close_screen() {");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("}");
			
			
			

      out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ASSET_ID.value==\"\"){  "); 
			out.println("DIV_TXT_ASSET_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ENGINE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_ENGINE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CHASSIS_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CHASSIS_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PRICING_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PRICING_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SUB_MODEL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_MODEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			//out.println("DIV_TXT_LOCATION.style.color='red';");
			//out.println("return false;"); 
			//out.println("}"); 
			out.println("else if(document.Form1.TXT_COLOUR.value==\"\"){  "); 
			out.println("DIV_TXT_COLOUR.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SEATING_CAPACITY.value==\"\"){  "); 
			out.println("DIV_TXT_SEATING_CAPACITY.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NET_PRICE.value==\"\"){  "); 
			out.println("DIV_TXT_NET_PRICE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//out.println("else if(format_noobject(document.Form1.TXT_NET_PRICE.value)){  "); 
		//	out.println("return false;"); 
		//	out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT.value==\"\"){  "); 
			out.println("DIV_TXT_VAT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//out.println("else if(!isNaN(format_noobject(document.Form1.TXT_VAT.value))){  "); 
		//	out.println("return false;"); 
		//	out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		//	out.println("else if(format_noobject(document.Form1.TXT_TOTAL_AMOUNT.value)){  "); 
		//	out.println("return false;"); 
		//	out.println("}"); 
			
			out.println("else if(document.Form1.TXT_TO_BE_DELIVERD_TO.value==\"\"){  "); 
			out.println("DIV_TXT_TO_BE_DELIVERD_TO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LOCATION.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VALUE.value==\"\"){  "); 
			out.println("DIV_TXT_VALUE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		//	out.println("else if(format_noobject(document.Form1.TXT_VALUE.value)){  "); 
		//	out.println("return false;"); 
		// 	out.println("}"); 
			/*
      out.println("else if(!checkMonthLength(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)){  "); 
			out.println("return false;"); 
			out.println("}"); 
	    */
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println("m_option = document.Form1.hid_status.value;"); 
			out.println("if(m_option=='New') {");
			out.println("m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Edit') {");
			out.println("m_sav_msg = 'Are you sure you want to Modify?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Delete') {");
			out.println("m_sav_msg = 'Are you sure you want to Delete?'; ");
			out.println("		}"); 
			
			out.println("		if(validate_data()){"); 
			out.println("     for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("       document.Form1.elements[i].disabled=false;");
			out.println("     }");
			out.println("		if(confirm(m_sav_msg)){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_save_performa_invoice';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice_1?APP_NO="+m_appNo+"';"); 
			//out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_performa_invoice?APP_NO='+document.Form1.hid_app_no.value+'';"); 
			
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice_1?APP_NO="+m_appNo+"';"); 
			//out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_performa_invoice?APP_NO='+document.Form1.hid_app_no.value+'';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_display_performa_invoice\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Application Process - Pro Forma Invoice - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Application Process - Pro Forma Invoice - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			//out.println("alert(m_val);");
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
		//	out.println("document.Form1.TXT_INVOICE_NO.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"EDIT\"){"); 
			out.println("     for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("       document.Form1.elements[i].disabled=false;");
			out.println("     }");
			out.println("     document.Form1.TXT_VENDOR_NAME.disabled=true;"); 
			out.println("     document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
			out.println("     document.Form1.TXT_MODEL_NO.disabled=true;"); 
			out.println("     document.Form1.TXT_SUB_MODEL_NO.disabled=true;"); 
			out.println("     document.Form1.TXT_ENGINE_CAPACITY.disabled=true;"); 
			out.println("     document.Form1.TXT_TRANSMISSION.disabled=true;"); 
			out.println("     document.Form1.TXT_YEAR_MANUFACTURE.disabled=true;"); 
			out.println("     document.Form1.TXT_INVOICE_DOC_NO.disabled=true;"); 
			out.println("     document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("     document.Form1.BUT_APPLICATION_NO.disabled=true;"); 
			out.println("     document.Form1.TXT_CURR_CODE.disabled=true;"); 
			out.println("}"); 			
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_INVOICE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE_YY.disabled=true;"); 
			out.println("document.Form1.TXT_PRICING_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_MODEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_COLOUR.disabled=true;"); 
			out.println("document.Form1.TXT_SEATING_CAPACITY.disabled=true;"); 
			out.println("document.Form1.TXT_NET_PRICE.disabled=true;"); 
			out.println("document.Form1.TXT_VAT.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_TO_BE_DELIVERD_TO.disabled=true;"); 
			out.println("document.Form1.TXT_VALUE.disabled=true;"); 
			out.println("document.Form1.TXT_CURR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_LOCATION.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_INVOICE_DOC_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_PRICING_NO.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_CITY.disabled=true;"); 
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_VEN.disabled=true;"); 
			out.println("document.Form1.TXT_LOCATION_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_LOC.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
		//	out.println("document.Form1.TXT_INVOICE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_INVOICE_DOC_NO.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_INVOICE_NO.disabled=false;}"); 
			
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
		//	out.println("    document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
		//	out.println("    document.Form1.TXT_INVOICE_NO.disabled=false;"); 
			//out.println("}else if(m_val==\"DACT\"){");  
			//out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			//out.println("}else if(m_val==\"RACT\"){");  
			//out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 

			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			//out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			//out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			//out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			//out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			out.println("if(oBj.valout[0]=='Next')  {");
				out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[0]=='Prev') {");
				out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[0] == 'Exit'){");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("if(IfCount=='99'){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='1'){"); 
				out.println("		help_value_assign_1(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		help_value_assign_2(oBj);"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("		help_value_assign_3(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("		help_value_assign_4(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("		help_value_assign_5(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='6'){"); 
				out.println("		help_value_assign_6(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='77'){"); 
				out.println("		help_update_value_assign_77(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='88'){"); 
				out.println("		help_update_value_assign_88(oBj);"); 
				out.println("}");
				
			out.println("	}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			out.println("function help_button_1(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			//out.println("    m_sql = \"m_help_TXT_MODEL_CODE_sql\";"); 
		//	out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
			
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','5');"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
			//out.println("    document.Form1.TXT_MODEL_NO.value=oBj.valout[2];");
			//out.println("    document.Form1.TXT_TRANSMISSION.value=oBj.valout[5];");
			out.println("}"); 

			
			
			
			
			out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
		//	out.println("    m_sql = \"m_help_TXT_ASSET_ID_sql\";"); 
			out.println("    Crit = document.Form1.TXT_ASSET_ID.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			//out.println("    HelpBox('1','10','12');"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
		    out.println("document.Form1.TXT_ASSET_ID.value=oBj.valout[2];"); 
		    //out.println("document.Form1.TXT_REG_NO.value=oBj.valout[3];"); 
		    out.println("document.Form1.TXT_MODEL_NO.value=oBj.valout[3];"); 
		    out.println("document.Form1.TXT_SUB_MODEL_NO.value=oBj.valout[4];"); 
			 // out.println("document.Form1.TXT_REG_DATE.value=oBj.valout[4];"); 
				//out.println("getDateValues(oBj.valout[4]);");
		//	  out.println("document.Form1.TXT_PRICING_NO.value=oBj.valout[5];"); 
			//  out.println("document.Form1.TXT_SUB_MODEL_NO.value=oBj.valout[6];"); 
		//	out.println("    document.Form1.TXT_SUB_MODEL_NO.value=oBj.valout[2];"); 
		//	out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=oBj.valout[5];"); 
		//	out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value=oBj.valout[8];"); 
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
			out.println("}"); 

			out.println("function help_button_3(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
		//	out.println("    m_sql = \"m_help_TXT_MODEL_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_MODEL_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','5');"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_MODEL_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TRANSMISSION.value=oBj.valout[5];");
			out.println("}"); 
			
			out.println("function help_button_4(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
		//	out.println("    m_sql = \"m_help_TXT_SUB_M_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_SUB_MODEL_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','5');"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_SUB_MODEL_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value=oBj.valout[8];"); 
			out.println("}"); 
			
			
			out.println("function help_button_5(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
	//		out.println("    m_sql = \"m_help_TXT_PRICING_sql\";"); 
			out.println("    Crit = document.Form1.TXT_PRICING_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','5');"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[2];"); 
			out.println("    net_price= 0 ;"); 
			out.println("    vat= 0 ;"); 
			out.println("    net_price= Number(oBj.valout[11]);"); 
			out.println("    vat= Number(oBj.valout[10]) ;"); 
			out.println("    document.Form1.TXT_NET_PRICE.value=format_noobject(net_price);"); 
			out.println("    document.Form1.TXT_VAT.value=format_noobject(vat);"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value =  format_noobject(net_price + vat);"); 
			out.println("    document.Form1.TXT_VALUE.value = document.Form1.TXT_TOTAL_AMOUNT.value;"); 
			//out.println("alert(oBj.valout[29]);");
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[29];"); 
			out.println("}"); 
			
			
			out.println("function help_button_6(Start,End,Hid_No,Sql,IfCount) {");
			out.println("document.Form1.hid_help_type.value =\"6\"; ");
			out.println("    Crit = document.Form1.TXT_CITY_CODE.value+\"@\"+\"Y@\";");
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("    } ");

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 


			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			//out.println("    m_sql = \"m_help_TXT_ASSET_ID_sql\";"); 
		//	out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";"); 
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			//out.println("    Crit = document.Form1.TXT_INVOICE_NO.value+\"@\"+\"Y@\";"); 
			out.println("    Crit =document.Form1.TXT_INVOICE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    Crit = document.Form1.TXT_INVOICE_NO.value+\"@\"+\"N@\";}"); 
		//	out.println("    HelpBox('1','10','16');"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			
			out.println("function help_vendor(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"77\";"); 
			out.println("    Crit = document.Form1.TXT_VENDOR_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_77() {");
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_VENDOR_NAME.value=oBj.valout[3];"); 
			out.println("}"); 
			
			out.println("function help_branch(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"88\";"); 
			out.println("    Crit = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_88() {");
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_REG_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_REG_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_REG_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			
			out.println("function getDateValues_Due(dval){");
			out.println("document.Form1.TXT_DUE_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_DUE_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_DUE_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_INVOICE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[5];"); 
  		out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[6];"); 
			out.println("   if(oBj.valout[7]!='null') { "); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[7];"); 
			out.println("    } "); 
			out.println("    if(oBj.valout[8]!='null') {"); 
			out.println("     getDateValues(oBj.valout[8])");
			out.println("    } "); 
			out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_NO.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_NET_PRICE.value=format_noobject(Number(oBj.valout[13]) );"); 
			out.println("    document.Form1.TXT_VAT.value=format_noobject(Number(oBj.valout[14]) );"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=format_noobject(Number(oBj.valout[15]) );"); 
			out.println("    document.Form1.TXT_TO_BE_DELIVERD_TO.value=oBj.valout[16];"); 
	  	out.println("    document.Form1.TXT_VALUE.value=format_noobject(Number(oBj.valout[17]) );"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_MODEL_NO.value=oBj.valout[19];"); 
		  out.println("    document.Form1.TXT_TRANSMISSION.value=oBj.valout[20];"); 
		  out.println("    document.Form1.TXT_INVOICE_DOC_NO.value=oBj.valout[21];"); 
		  out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[22];"); 
		  out.println("    document.Form1.TXT_LOCATION.value=oBj.valout[23];"); 
		  out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[24];"); 
		  out.println("    document.Form1.TXT_VENDOR_NAME.value=oBj.valout[25];"); 
		  out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[26];"); 
		  out.println("    document.Form1.TXT_FUEL_CONV_STS.value=oBj.valout[27];"); 
			out.println("    if(oBj.valout[28]!='null') {"); 
			out.println("     getDateValues_Due(oBj.valout[28])");
			out.println("    } "); 
			
			
			
			
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
	
       
			out.println("}"); 
			
			out.println("function load_application_no(){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=\""+m_appNo+"\";"); 
			out.println("    document.Form1.TXT_INVOICE_NO.value=\""+m_prof_inv_no+"\";"); 
	
			out.println("    document.Form1.hid_app_no.value=\""+m_appNo+"\";"); 
			//out.println("    document.Form1.TXT_APPLICATION_NO.value=\"'+document.Form1.hid_app_no.value+'\";"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("    document.Form1.TXT_INVOICE_NO.disabled=true;"); 
			out.println("		  assignState('T1'); ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_performa_invoice&data_val=\"+document.Form1.TXT_INVOICE_NO.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");

			out.println("}"); 
			
			out.println("function assign_invoice_val(val) {"); 
			//out.println(" alert(val)");
			out.println("    document.Form1.TXT_INVOICE_NO.value=val[0];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=val[1];"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=val[2];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=val[3];");
			
			out.println("    document.Form1.TXT_MODEL_NO.value=val[4];"); 

  		out.println("    document.Form1.TXT_CHASSIS_NO.value=val[5];"); 
			out.println("   if(val[6]!='null') { "); 
			out.println("    document.Form1.TXT_REG_NO.value=val[6];"); 
			out.println("    } "); 
			out.println("    if(val[7]!='null') {"); 
			out.println("     getDateValues(val[7])");
			out.println("    } "); 
			out.println("    document.Form1.TXT_PRICING_NO.value=val[8];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_NO.value=val[9];"); 
			out.println("    document.Form1.TXT_COLOUR.value=val[10];"); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=val[11];"); 
			out.println("    document.Form1.TXT_NET_PRICE.value=format_noobject(Number(val[12]) );"); 
			out.println("    document.Form1.TXT_VAT.value=format_noobject(Number(val[13]) );"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=format_noobject(Number(val[14]) );"); 
			out.println("    document.Form1.TXT_TO_BE_DELIVERD_TO.value=val[15];"); 
	  	out.println("    document.Form1.TXT_VALUE.value=format_noobject(Number(val[16]) );"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=val[17];"); 
			
		  out.println("    document.Form1.TXT_TRANSMISSION.value=val[18];"); 
		  
		  out.println("    document.Form1.TXT_VENDOR_CODE.value=val[19];");
					  ; 
		  out.println("    document.Form1.TXT_LOCATION.value=val[20];"); 
			
			out.println("    document.Form1.TXT_CITY_CODE.value=val[21];");
		  out.println("    document.Form1.TXT_VENDOR_NAME.value=val[22];"); 
		  out.println("    document.Form1.TXT_LOCATION_CODE.value=val[23];");
			
			out.println("    document.Form1.TXT_INVOICE_DOC_NO.value=val[24];");
			//out.println("alert(val[25])");
		  out.println("    document.Form1.TXT_FUEL_CONV_STS.value=val[24];"); 
			out.println("    if(val[25]!='null') {"); 
			out.println("     getDateValues_Due(val[25])");
			out.println("    } "); 
			out.println("    } "); 
			
			
			out.println("function val_seating_capacity(){");
			
			out.println("if(!isPosInteger(document.Form1.TXT_SEATING_CAPACITY.value)){");
			out.println("alert('Please enter a number.');");
			out.println("document.Form1.TXT_SEATING_CAPACITY.value=\"\"; ");
			out.println("document.Form1.TXT_SEATING_CAPACITY.focus();}");
			
			out.println("}");
			
			out.println("function val_total_amt() {"); 
			out.println("    m_net_price = 0; "); 
			out.println("    m_vat = 0; "); 
			out.println("    m_tot = 0; "); 
			out.println("    m_net_price =  Number(unformat_number(document.Form1.TXT_NET_PRICE)); "); 
			out.println("    m_vat       =  Number(unformat_number(document.Form1.TXT_VAT)); "); 
			
      out.println("   if( ( !isNaN(m_net_price) &&  !isNaN(m_vat) ) && (m_net_price > 0)  ){");
			out.println("     m_tot = m_net_price + m_vat ;"); 
			out.println("     document.Form1.TXT_TOTAL_AMOUNT.value = format_noobject(m_tot);"); 
			out.println("     document.Form1.TXT_VALUE.value = document.Form1.TXT_TOTAL_AMOUNT.value;"); 
			out.println("   }"); 
			out.println("   else {"); 
      out.println("      if(m_net_price <= 0){");
			out.println("        alert('Please enter a number greater than zero for Net Price.');");
			out.println("        document.Form1.TXT_NET_PRICE.value = \"\" ;"); 
			out.println("        document.Form1.TXT_TOTAL_AMOUNT.value = \"\" ;"); 
			out.println("      }"); 
			out.println("      else {"); 
			out.println("        alert('Please enter a number.');");
			out.println("        document.Form1.TXT_TOTAL_AMOUNT.value = \"\" ;"); 
			out.println("      }"); 
			out.println("   }"); 
			out.println(" }"); 
			
	
	//	out.println("}");
			
			
				out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_View_Help_Servlet?class_in=\"+client_name+\"AF_MK_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:50em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
			
									
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_view_TXT_INVOICE_NO_sql\";");
			out.println("    m_criteria = document.Form1.TXT_INVOICE_NO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function validate_city(){");
			out.println("assignState('M6')");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+document.Form1.TXT_CITY_CODE.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function val_engine_no(){");
			out.println("assignState('M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_eng_no&data_val=\"+document.Form1.TXT_ENGINE_NO.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function val_chassis_no(){");
			out.println("assignState('M8')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_chassi_no&data_val=\"+document.Form1.TXT_CHASSIS_NO.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url); ");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function clear_fields(){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\") {" ); 
			out.println("    document.Form1.TXT_INVOICE_NO.value =''; ");
			out.println("   }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"1\") {" ); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
			out.println("    document.Form1.TXT_ASSET_ID.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
			out.println("    document.Form1.TXT_MODEL_NO.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"4\") {" ); 
			out.println("    document.Form1.TXT_SUB_MODEL_NO.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" ); 
			out.println("    document.Form1.TXT_PRICING_NO.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"6\") {" ); 
			out.println("    document.Form1.TXT_CITY_CODE.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"77\") {" ); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value =''; ");
			out.println("    document.Form1.TXT_VENDOR_NAME.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"88\") {" ); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value =''; ");
			out.println("  }		"); 
			
			out.println("}		"); 
			
			
			out.println("function check_date(objDD,objMM,objYY){ ");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			//out.println(" if((objDD.value ==\"\")||(objMM.value ==\"\")||(objYY.value ==\"\")){");
			//out.println("   alert('Please enter a valid date.');");
			//out.println("   objDD.value='' ;");
			//out.println("   objMM.value='' ;");
			//out.println("   objYY.value='' ;");
			//out.println(" }");
			
			out.println("}");
			
						
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_application_no()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
			//out.println("<INPUT TYPE='Hidden' NAME='hid_TXT_CURR_CODE' VALUE=\"SLR\">");
			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Application Process - Pro Forma Invoice</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
		//	out.println("<td width='40%'></td>");  
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>"); 
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			//out.println("<td width='20%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_INVOICE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('1','10','19','m_help_TXT_INVOICE_NO_sql','99')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_1('1','10','5','m_help_TXT_APPLICATION_NO_sql','1')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_ASSET_ID'  class=div_input>Asset ID *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ASSET_ID' maxlength='15' size='15' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_ASSET_ID)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ASSET_ID' value=\"Help\" onClick=\"help_button_2('1','10','4','m_help_TXT_ASSET_ID_sql2','2')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_VENDOR_CODE'  class=div_input>Vendor Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VENDOR_CODE' maxlength='10' size='10' onblur=\"assignState('M10'),makeRequest(document.Form1.TXT_VENDOR_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_VEN' value=\"Help\" onClick=\"help_vendor('1','10','3','m_help_TXT_VENDOR_CODE_sql','77')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_VENDOR_NAME'  class=div_input>Vendor Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style=\"width:250px\" type='text' name='TXT_VENDOR_NAME' maxlength='200' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Vendor Branch Location</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M11'),makeRequest(document.Form1.TXT_LOCATION_CODE)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_LOC' value=\"Help\" onClick=\"help_branch('1','10','3','m_help_TXT_MAS_VENDOR_LOCATION_sql','88')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_INVOICE_DOC_NO'  class=div_input>Vendor Document Reference No</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_DOC_NO' maxlength='30' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_CURR_CODE'  class=div_input>Currency Code</DIV></td>"); 
			out.println("<td width='30%' ><select name=\"TXT_CURR_CODE\" class=\"txt_input\" disabled>"); 
			
														
			rs=stmt.executeQuery(" SELECT CURR_CODE,CURR_SYMBOL,REP_CURR,DEFAULT_VALUE "+
													   " FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
														 " WHERE  ACTIVE_STATUS ='Y' ");
			
			boolean more=rs.next();
			while(more){
			if(rs.getString(3).equals("Y")){
			out.println("<option value=\""+rs.getString(1)+"\" SELECTED>"+rs.getString(2)+"</option>+");			
			}
			else{
			out.println("<option value=\""+rs.getString(1)+"\" >"+rs.getString(2)+"</option>+");		
			}
			
			more=rs.next();
			} 
			out.println("</select><td width='*%'></td>"); 
			out.println("</tr>"); 
			
				
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_PRICING_NO'  class=div_input>Pricing No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PRICING_NO' maxlength='15' size='15' onBlur=\"assignState('M9'),makeRequest(document.Form1.TXT_PRICING_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_PRICING_NO' value=\"Help\" onClick=\"help_button_5('1','10','15','PriceSql_invoice','5')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
						

      out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_NET_PRICE'  class=div_input>Net Price *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NET_PRICE' maxlength='25' size='25' onBlur=\"format_number(document.Form1.TXT_NET_PRICE,25),val_total_amt()\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_VAT'  class=div_input>VAT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT' maxlength='25' size='25' onBlur=\"format_number(document.Form1.TXT_VAT,25),val_total_amt()\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_TOTAL_AMOUNT'  class=div_input>Total Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_AMOUNT' maxlength='25' size='25' onBlur=\"format_number(document.Form1.TXT_TOTAL_AMOUNT,25)\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 			
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_VALUE'  class=div_input>Value *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VALUE' maxlength='25' size='25' onBlur=\"format_number(document.Form1.TXT_VALUE,25)\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); out.println("</tr>"); 
			out.println("<tr>"); out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='25%' ><DIV id='DIV_TXT_MODEL_NO'  class=div_input>Model No *</DIV></td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_MODEL_NO' maxlength='20' size='20' onblur=\"assignState('M4'),makeRequest(document.Form1.TXT_MODEL_NO)\" disabled>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_MODEL_NO' value=\"Help\" onClick=\"help_button_3('0','10','5','m_help_TXT_MODEL_CODE_inv_sql','3')\"></td>"); 
			
			//out.println("<td width='20%' ><DIV id='DIV_TXT_SEATING_CAPACITY'  class=div_input>Seating Capacity *</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SEATING_CAPACITY' maxlength='3' size='10' onBlur=\"val_seating_capacity()\"></td>"); 
			//out.println("</tr>"); 
			
												
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_SUB_MODEL_NO'  class=div_input>Sub Model No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_NO' maxlength='20' size='20' onblur=\"assignState('M5'),makeRequest(document.Form1.TXT_SUB_MODEL_NO)\" disabled>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_SUB_MODEL_NO' value=\"Help\" onClick=\"help_button_4('0','10','5','m_help_TXT_SUB_M_CODE_sql','4')\"></td>"); 
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' >Registration No</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REG_NO' maxlength='20' size='20' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' >Date of Registration [DD-MM-YYYY]</td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REG_DATE' maxlength='10' size='10' onBlur=\"checkDate(document.Form1.TXT_REG_DATE.value)\"></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_REG_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_MM' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_YY' maxlength='4' size='4' onBlur='check_date(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_CHASSIS_NO'  class=div_input>Chassis No / Serial No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CHASSIS_NO' maxlength='50' size='20' onblur=\"val_chassis_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
					
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_ENGINE_NO'  class=div_input>Engine No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ENGINE_NO' maxlength='50' size='20' onblur=\"val_engine_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SEATING_CAPACITY'  class=div_input>Seating Capacity *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' STYLE=\"{text-align:right}\" name='TXT_SEATING_CAPACITY' maxlength='3' size='10' onBlur=\"val_seating_capacity()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' >Engine Capacity </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ENGINE_CAPACITY' maxlength='20' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' >Transmission Media </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TRANSMISSION' maxlength='20' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' >Year of Manufacture </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_YEAR_MANUFACTURE' maxlength='20' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_COLOUR'  class=div_input>Colour *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLOUR' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TO_BE_DELIVERD_TO'  class=div_input>To be Delivered to (Name) *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TO_BE_DELIVERD_TO' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_LOCATION'  class=div_input>Address *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style=\"width:300px\" type='text' name='TXT_LOCATION' maxlength='200' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"validate_city()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_CITY' value=\"Help\" onClick=\"help_button_6('1','10','2','m_help_TXT_CITY_CODE_sql','6')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_FUEL_CONV_STS'  class=div_input>Fuel Conversion Status</DIV></td>"); 
			out.println("<td width='30%' ><select name='TXT_FUEL_CONV_STS' class=\"txt_input\" STYLE=\"width:60\" >");
			out.println("<OPTION value=\"N\" SELECTED>No</option>");
			out.println("<OPTION value=\"Y\" >Yes</option>");
			out.println("</SELECT></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='25%' >Due Date [DD-MM-YYYY]</td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_DUE_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_DUE_DATE_MM' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_DUE_DATE_YY' maxlength='4' size='4' onBlur='check_date(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); out.println("</tr>"); 
			out.println("<tr>"); out.println("</tr>"); 
			
			

	   					if  (m_screen.equals("G")){
					out.println("<tr><td ><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change()\" ></td></tr>");
				}

					
			
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
			out.println("</html>"); }
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
