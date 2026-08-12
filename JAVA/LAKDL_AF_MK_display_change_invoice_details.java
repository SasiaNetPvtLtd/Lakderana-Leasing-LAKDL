//--
//SCREEN NAME	:Marketting - Change Invoice Details
//CREATED BY	:
//DATE/TIME		:
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MK_display_change_invoice_details extends javax.servlet.http.HttpServlet { 
	
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
			
			// added by udara 26-09-2016
			String m_edit_screen = req.getParameter("edit_screen"); 
			
			if (m_edit_screen==null){
					m_edit_screen="";
			}
			// end by udara 26-09-2016
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Marketing - Change Proforma Invoice </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_chk_value=0;")	 ;
			out.println("var m_sav_msg=''");
			
			out.println("function get_vector(data_vec) {");
			//out.println("alert(document.Form1.hid_chk_status.value||'@'||document.Form1.SCREEN_NAME.value==\"EDIT\")");
			
			
			
			out.println("			if(data_vec.length==0 && (document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"EDIT\") && document.Form1.hid_chk_status.value=='M12'){");
			out.println("				help_update('1','10','19','m_help_TXT_INVOICE_NO_sql','99');");
			out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M12'){");
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
			out.println("    document.Form1.TXT_CITY_NAME.value=data_vec[30];"); //added by nuwan de silva on 11-12-2007
			out.println("    document.Form1.TXT_VENDOR_NAME.value=data_vec[22];"); 
			out.println("    document.Form1.TXT_INVOICE_DOC_NO.value=data_vec[23];"); 
			out.println("    document.Form1.TXT_FUEL_CONV_STS.value=data_vec[24];"); 
			
			out.println("    document.Form1.TXT_SUM_INSURED.value=data_vec[33];"); 
			out.println("    document.Form1.TXT_AREA.value=data_vec[34];"); 
			out.println("    document.Form1.TXT_POLICE.value=data_vec[35];");
			out.println("    document.Form1.TXT_OWN_ADD.value=data_vec[36];");
			out.println("    document.Form1.TXT_COLLE_SEC.value=data_vec[37];"); 
			out.println("    document.Form1.TXT_LIC_AUTH.value=data_vec[38];"); 
			out.println("    document.Form1.VEHICAL_AGA.value=data_vec[39];");
			
			out.println("    if(data_vec[25]!='null') {"); 
			out.println("     getDateValues_Due(data_vec[25]);"); 
			out.println("		}");
			
			// Added by nuwan de silva on 11-12-2007 _______________________________
			out.println("    document.Form1.TXT_MODEL_DESC.value=data_vec[26];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_DESC.value=data_vec[27];"); 
			
			out.println("    asset_desc=document.Form1.TXT_MODEL_DESC.value+' '+document.Form1.TXT_SUB_MODEL_DESC.value;");
			out.println("    document.Form1.TXT_ASSET_DESC.value=asset_desc;"); 
			
			out.println("    if(data_vec[28]!='null') {"); 
			out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value=data_vec[28];"); 
			out.println("		 }");
			out.println("    else {");
			out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value='';"); 
			out.println("		 }");
			out.println("    if(data_vec[29]!='null') {"); 
			out.println("    document.Form1.TXT_EXTRAS.value=data_vec[29];"); 
			
			out.println("		 }");
			
			
			out.println("    else {");
			out.println("    document.Form1.TXT_EXTRAS.value='';"); 
			out.println("		 }");
			
			
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
			out.println("			}");
			
			
			out.println("else	if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_ASSET_ID.value!=\"\"){");
			out.println("				help_button_2('1','10','4','m_help_TXT_ASSET_ID_sql2','2') ;");
			out.println("       document.Form1.TXT_ASSET_ID.focus()"); 
			out.println("			}");
			out.println("else	if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_ASSET_ID.value!=\"\"){");
			out.println("    document.Form1.TXT_MODEL_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_NO.value=data_vec[2];"); 
			out.println(" 	 if(document.Form1.TXT_SUB_MODEL_NO.value!=''){");
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
			out.println("			 }");
			out.println("			}");
			out.println("else	if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.TXT_MODEL_NO.value!=\"\"){");
			out.println("       document.Form1.TXT_MODEL_NO.value=\"\""); 
			out.println("       document.Form1.TXT_MODEL_NO.focus()"); 
			out.println("			}");
			out.println("else	if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_SUB_MODEL_NO.value!=\"\"){");
			out.println("			}");
			out.println("else	if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_SUB_MODEL_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_TRANSMISSION.value=data_vec[8];"); 
			out.println("			}");
			out.println("	else if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M6' && document.Form1.TXT_CITY_CODE.value!=\"\"){");
			out.println("       help_button_6('1','10','2','m_help_TXT_CITY_CODE_sql','6'); "); 
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
			out.println("    assign_invoice_val(data_vec); "); 
			out.println("			}");
			out.println("			}");
			
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M12' && document.Form1.SCREEN_NAME.value!=\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_change_invoice_details&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_change_invoice_details&data_val=\"+obj.value+\"&ac_status=N\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M33')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_display_change_invoice_details_finance&data_val=\"+obj.value+\"&ac_status=N\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M3')");
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
			out.println("else if(document.Form1.hid_chk_status.value=='M2')");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_change_invoice_details&data_val=\"+obj.value+\"&ac_status=ACTIVATED\";");	
			//------
			out.println("if(document.Form1.hid_chk_status.value=='M12' && document.Form1.SCREEN_NAME.value!=\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_change_invoice_details2&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else if(document.Form1.hid_chk_status.value=='M12' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_change_invoice_details2&data_val=\"+obj.value+\"&ac_status=N\";");
			//------
			
			
			//			out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function check_number_2(obj){");
			out.println("if(obj.value!='' && obj.value!='-') ");
			out.println("if(isPosInteger(obj.value)){ ");
			//out.println("format_noobject_nodecimal1(obj) ;");
			out.println("} ");
			out.println("else{");
			out.println("alert('please enter a number'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("} ");
			out.println("} ");
			
			
			
			out.println("function close_screen() {");
			out.println("	if(document.Form1.close2.value==\"Proceed to Next Level\"){");
			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("	if(document.Form1.close2.value==\"Close\"){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
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
			//modification by madhawa add 2009-10-16 validation to invoice no
			out.println("else if(document.Form1.TXT_INVOICE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_INVOICE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			//end modification by madhawa add 2009-10-16 validation to invoice no
			out.println("else if(document.Form1.TXT_ASSET_ID.value==\"\"){  "); 
			out.println("DIV_TXT_ASSET_ID.style.color='red';");
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
			out.println("else if(document.Form1.TXT_COLOUR.value==\"\"){  "); 
			out.println("DIV_TXT_COLOUR.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			/*out.println("else if(document.Form1.TXT_SEATING_CAPACITY.value==\"\"){  "); 
			out.println("DIV_TXT_SEATING_CAPACITY.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			*/
			out.println("else if(document.Form1.TXT_NET_PRICE.value==\"\"){  "); 
			out.println("DIV_TXT_NET_PRICE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT.value==\"\"){  "); 
			out.println("DIV_TXT_VAT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_YEAR_MANUFACTURE.value==\"\"){  "); 
			out.println("DIV_TXT_YEAR_MANUFACTURE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
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
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_save_change_invoice_details';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			out.println("document.Form1.hid_status.value=\"Edit\";"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_change_invoice_details';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_change_invoice_details';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			//Added by Mahela on 21-05-2007 ---------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
			
			out.println("function load_c_date(val) {");
			//	out.println("alert('aasf'+document.Form1.SCREEN_NAME.value);");
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
			out.println("     document.Form1.TXT_REG_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_REG_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_REG_DATE_YY.value=val;");
			out.println("	}");
			
			out.println("  if(document.Form1.hid_cal_date.value=='2' && document.Form1.SCREEN_NAME.value!='NEW'){"); 
			out.println("		v_date=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_date.length<2)");
			out.println("			v_date=0+v_date");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("			v_month=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_month.length<2)");
			out.println("			v_month=0+v_month");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.TXT_DUE_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_DUE_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_DUE_DATE_YY.value=val;");
			out.println("	}");
			out.println("}");
			
			//----------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_display_change_performa_invoice\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Marketing - Change Proforma Invoice - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Marketing - Change Proforma Invoice - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
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
			out.println("     document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
			out.println("     document.Form1.BUT_APPLICATION_NO.disabled=false;"); 
			out.println("     document.Form1.TXT_CURR_CODE.disabled=true;"); 
			out.println("}"); 			
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_INVOICE_NO.disabled=false;"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
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
			out.println("document.Form1.TXT_SUM_INSURED.disabled=true;");	//added by Prabash on 30-04-2012
			out.println("document.Form1.TXT_AREA.disabled=true;");			//added by Prabash on 30-04-2012
			out.println("document.Form1.TXT_POLICE.disabled=true;");		//added by Prabash on 30-04-2012
			out.println("document.Form1.TXT_OWN_ADD.disabled=true;");		//added by Prabash on 30-04-2012
			out.println("document.Form1.TXT_COLLE_SEC.disabled=true;");		//added by Prabash on 30-04-2012
			out.println("document.Form1.TXT_LIC_AUTH.disabled=true;");		//added by Prabash on 30-04-2012
			out.println("document.Form1.VEHICAL_AGA.disabled=true;"); 		//added by Prabash on 30-04-2012
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.TXT_INVOICE_DOC_NO.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_INVOICE_NO.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
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
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
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
			
			out.println("else if(IfCount=='33'){"); 
			out.println("		help_value_assign_33(oBj);");  
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
			out.println("else if(IfCount=='12'){"); 
			out.println("		help_assign_district(oBj);"); 
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
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			
			//out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			
			//out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
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
			
			/*
						out.println("function Prev(Start,End,Hid_No){"); 
						out.println("    HelpBox(Start,End,Hid_No);"); 
						out.println("}"); 
						out.println(""); 
						
						out.println("function Next (Start,End,Hid_No){"); 
						out.println("    HelpBox(Start,End,Hid_No);"); 
						out.println("}"); 
						out.println(""); 
						*/
			
			out.println("function help_button_1(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    Sql = \"m_help_TXT_APPLICATION_NO_1\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@ACTIVATED@\";"); //Added By delanjali in 2007-09-12
			//out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@VERIFY2@\";");  //Commented By delanjali in 2007-09-12
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function help_button_33(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    Sql = \"m_help_TXT_APPLICATION_NO_1\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@ACTIVATED@\";");
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
			out.println("}"); 
			
			out.println("function help_value_assign_33() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];");
			out.println("}"); 
			
			out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    Crit = document.Form1.TXT_ASSET_ID.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";");
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_2() {"); 
			out.println("document.Form1.TXT_ASSET_ID.value=oBj.valout[2];"); 
			out.println("document.Form1.TXT_MODEL_NO.value=oBj.valout[3];"); 
			out.println("document.Form1.TXT_SUB_MODEL_NO.value=oBj.valout[4];"); 
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
			out.println("}"); 
			
			out.println("function help_button_3(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    Crit = document.Form1.TXT_MODEL_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_MODEL_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TRANSMISSION.value=oBj.valout[5];");
			out.println("}"); 
			
			out.println("function help_button_4(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    Crit = document.Form1.TXT_SUB_MODEL_NO.value+\"@Y@\";"); 
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
			out.println("    Crit = document.Form1.TXT_PRICING_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			
			
			out.println("function help_button_district(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"12\";"); 
			out.println("    Crit =document.Form1.TXT_DISTRICT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_assign_district() {"); 
			out.println("    document.Form1.TXT_DISTRICT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
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
			
			
			
			//modified by madhawa add function to clear invoice No 2009-10-16
			//this called after user select an Application No,clear Invoice no text field,avoid fake invoice number which are not relevent to selected application no
			out.println("function clearInvoiceNo(){");
			out.println("document.Form1.TXT_INVOICE_NO.value='';");
			out.println("}");
			//end modified by madhawa add function to clear invoice No 2009-10-16
			
			//modified add by madhawa 2009-10-16
			out.println("function call_help_update_AppliNo_Notnull(){");
			out.println("if(document.Form1.TXT_APPLICATION_NO.value!=''){");
			out.println("help_update('1','10','25','m_help_TXT_INVOICE_NO_sql','99');");
			out.println("}else{");
			out.println("alert('Please enter application number first');}");
			out.println("}");
			//end modified add by madhawa 2009-10-16
			
			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    Crit =document.Form1.TXT_INVOICE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
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
			out.println("    Crit =document.Form1.TXT_LOCATION_CODE.value+\"@\"+document.Form1.TXT_VENDOR_CODE.value+\"@Y@\";"); 
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
			
			//out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[12];"); 
			
			out.println("if(oBj.valout[12]=='-' ||  oBj.valout[12]=='null' ){");
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value='';"); 
			out.println("    } "); 
			out.println("    else{ "); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[12];"); 
			out.println("    } "); 
			
			
			
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
			out.println("    document.Form1.TXT_CITY_NAME.value=oBj.valout[33];");  //added by nuwan de silva on 11-12-07
			out.println("    document.Form1.TXT_LOCATION.value=oBj.valout[23];"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[24];"); 
			out.println("    document.Form1.TXT_VENDOR_NAME.value=oBj.valout[25];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[26];"); 
			out.println("    document.Form1.TXT_FUEL_CONV_STS.value=oBj.valout[27];"); 
			
			//pra----------	
			out.println("    document.Form1.TXT_SUM_INSURED.value=format_noobject(Number(oBj.valout[36]));"); 
			out.println("    document.Form1.TXT_AREA.value=oBj.valout[37];"); 
			out.println("    document.Form1.TXT_POLICE.value=oBj.valout[38];");
			out.println("    document.Form1.TXT_OWN_ADD.value=oBj.valout[39];");
			out.println("    document.Form1.TXT_COLLE_SEC.value=oBj.valout[40];"); 
			out.println("    document.Form1.TXT_LIC_AUTH.value=oBj.valout[41];"); 
			out.println("    document.Form1.VEHICAL_AGA.value=oBj.valout[42];");
			//-------------	
			out.println("    if(oBj.valout[43]=='Y') {"); // // thamali 2013.08.12
			out.println("    	document.Form1.CHK_CR_BOOK_RECEIVED.value=oBj.valout[43];");
			out.println("    	document.Form1.CHK_CR_BOOK_RECEIVED.checked=true;");
			out.println("    } "); 
			out.println("    else{ "); 
			out.println("    	document.Form1.CHK_CR_BOOK_RECEIVED.value=oBj.valout[43];"); 
			out.println("    	document.Form1.CHK_CR_BOOK_RECEIVED.checked=false;"); 
			out.println("    } "); 
			
			
			out.println("    if(oBj.valout[28]!='null') {"); 
			out.println("     getDateValues_Due(oBj.valout[28])");
			out.println("    } "); 
			
			out.println("    document.Form1.TXT_MODEL_DESC.value=oBj.valout[29];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_DESC.value=oBj.valout[30];"); 
			out.println("    document.Form1.TXT_ASSET_DESC.value=oBj.valout[29]+' '+oBj.valout[30]"); //added by nuwan de silva on 15-11-2007
			out.println("    if(oBj.valout[31]!='null') {"); 
			out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value=oBj.valout[31];"); 
			out.println("    } "); 
			out.println("    else {");
			out.println("    document.Form1.TXT_YEAR_MANUFACTURE.value='';"); 
			out.println("    } "); 
			
			out.println("    if(oBj.valout[32]!='null') {"); 
			out.println("    document.Form1.TXT_EXTRAS.value=oBj.valout[32];"); 
			out.println("    } "); 
			
			
			
			
			
			
			out.println("    else {");
			out.println("    document.Form1.TXT_EXTRAS.value='';"); 
			out.println("    } "); 
			
			out.println("		  assignState('M5'); ");
			out.println("		  makeRequest(document.Form1.TXT_SUB_MODEL_NO); ");
			out.println("}"); 
			
			out.println("function val_seating_capacity(){");
			out.println("if(!isPosInteger(document.Form1.TXT_SEATING_CAPACITY.value)){");
			out.println("alert('Please enter a number.');");
			out.println("document.Form1.TXT_SEATING_CAPACITY.value=\"\"; ");
			out.println("document.Form1.TXT_SEATING_CAPACITY.focus();}");
			out.println("}");
			
			
			out.println("function val_change(){");
			out.println("if(document.Form1.CHK_CR_BOOK_RECEIVED.checked==true){");
			out.println("document.Form1.CHK_CR_BOOK_RECEIVED.value=\"Y\"; ");
			out.println("}");
			out.println("else {");
			out.println("document.Form1.CHK_CR_BOOK_RECEIVED.value=\"N\"; ");
			out.println("}");
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
			
			out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";");
			out.println("    m_criteria = document.Form1.TXT_INVOICE_NO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			
			out.println("function HelpBox_View(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_View_Help_Servlet?class_in="+m_fschema_name+"AF_MK_View_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:100em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("ViewNext(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("ViewPrev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
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
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpBox_View(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function ViewNext (Start,End,Hid_No){"); 
			out.println("    HelpBox_View(Start,End,Hid_No);"); 
			out.println("}"); 
			
			
			out.println("function validate_city(){");
			out.println("assignState('M6')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+document.Form1.TXT_CITY_CODE.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function val_engine_no(){");
			out.println("assignState('M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_eng_no&data_val=\"+document.Form1.TXT_ENGINE_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function val_chassis_no(){");
			out.println("assignState('M8')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_display_performa_invoice_chassi_no&data_val=\"+document.Form1.TXT_CHASSIS_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function clear_fields(){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\") {" ); 
			out.println("    document.Form1.TXT_INVOICE_NO.value =''; ");
			out.println("   }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"1\") {" ); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"33\") {" ); 
			out.println("    document.Form1.TXT_FINANCE_NO.value =''; ");
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
			out.println("}");
			
			out.println("function assign_invoice_val(val) {"); 
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
			out.println("    document.Form1.TXT_VENDOR_CODE.value=val[19].replace('$','&');");
			out.println("    document.Form1.TXT_LOCATION.value=val[20];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=val[21];");
			out.println("    document.Form1.TXT_VENDOR_NAME.value=val[22].replace('$','&');"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=val[23];");
			out.println("    document.Form1.TXT_INVOICE_DOC_NO.value=val[24];");
			out.println("    document.Form1.TXT_FUEL_CONV_STS.value=val[24];"); 
			//pra----------	
			/*		out.println("    document.Form1.TXT_SUM_INSURED.value=val[34];"); 
					out.println("    document.Form1.TXT_AREA.value=val[35];"); 
					out.println("    document.Form1.TXT_POLICE.value=val[36];");
					out.println("    document.Form1.TXT_OWN_ADD.value=val[37];");
					out.println("    document.Form1.TXT_COLLE_SEC.value=val[38];"); 
					out.println("    document.Form1.TXT_LIC_AUTH.value=val[39];"); 
					out.println("    document.Form1.VEHICAL_AGA.value=val[40];");
			*/	//-------------
			
			out.println("    if(val[25]!='null') {"); 
			out.println("     getDateValues_Due(val[25])");
			out.println("    } "); 
			out.println("    } "); 
			
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
			
			//-------------------------------------------------------------------------
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Edit\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_PERFORMA_INVOICE\">");
			
			//added by nuwan de silva on 11-12-2007-----------------------------
			out.println("<input type='hidden' name='TXT_MODEL_NO' value=\"\">"); 
			out.println("<input type='hidden' name='TXT_MODEL_DESC' value=\"\">"); 
			out.println("<input type='hidden' name='TXT_SUB_MODEL_NO' value=\"\">"); 
			out.println("<input type='hidden' name='TXT_SUB_MODEL_DESC' value=\"\">"); 
			out.println("<input type='hidden' name='TXT_ENGINE_CAPACITY' value=\"\">"); 
			out.println("<input type='hidden' name='TXT_TRANSMISSION' value=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='TXT_CURR_CODE' VALUE=\"SLR\">");
			
			
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
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Marketing - Change Proforma Invoice</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			
			/*out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_1('1','10','5','m_help_TXT_APPLICATION_NO_1','1')\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_INVOICE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('1','10','25','m_help_TXT_INVOICE_NO_sql','99')\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
      
			// comment by nuwan de silva on 11-12-2007 ------------------------------------------------------
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_ASSET_ID'  class=div_input>Asset ID *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ASSET_ID' maxlength='15' size='15' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_ASSET_ID)\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ASSET_ID' value=\"Help\" onClick=\"help_button_2('1','10','8','m_help_TXT_ASSET_ID_sql2','2')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_VENDOR_CODE'  class=div_input>Vendor Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VENDOR_CODE' maxlength='10' size='10' onblur=\"assignState('M10'),makeRequest(document.Form1.TXT_VENDOR_CODE)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_VEN' value=\"Help\" onClick=\"help_vendor('1','10','3','m_help_TXT_VENDOR_CODE_sql','77')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_VENDOR_NAME'  class=div_input>Vendor Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style=\"width:250px\" type='text' name='TXT_VENDOR_NAME' maxlength='200' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Vendor Branch Location</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M11'),makeRequest(document.Form1.TXT_LOCATION_CODE)\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_LOC' value=\"Help\" onClick=\"help_branch('1','10','7','m_help_TXT_MAS_VENDOR_LOCATION_sql','88')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_INVOICE_DOC_NO'  class=div_input>Vendor Document Reference No</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_INVOICE_DOC_NO' maxlength='30' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CURR_CODE'  class=div_input>Currency Code</DIV></td>"); 
			out.println("<td width='25%' ><select name=\"TXT_CURR_CODE\" class=\"txt_input\" disabled>"); 
			
														
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
			out.println("<td width='20%' ><DIV id='DIV_TXT_PRICING_NO'  class=div_input>Pricing No *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_PRICING_NO' maxlength='15' size='15' onBlur=\"assignState('M9'),makeRequest(document.Form1.TXT_PRICING_NO)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_PRICING_NO' value=\"Help\" onClick=\"help_button_5('1','10','32','PriceSql_invoice','5')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
						

      out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_NET_PRICE'  class=div_input>Net Price *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_NET_PRICE' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_NET_PRICE,25),val_total_amt()\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VAT'  class=div_input>VAT *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_VAT' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_VAT,25),val_total_amt()\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_TOTAL_AMOUNT'  class=div_input>Total Amount *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_TOTAL_AMOUNT' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_TOTAL_AMOUNT,25)\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALUE'  class=div_input>Value *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_VALUE' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_VALUE,25)\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); out.println("</tr>"); 
			out.println("<tr>"); out.println("</tr>"); 
			
			out.println("<tr >");
			out.println("<td width='20%' ><DIV id='DIV_TXT_MODEL_NO'  class=div_input>Model Code *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_MODEL_NO' maxlength='20' size='20' onblur=\"assignState('M4'),makeRequest(document.Form1.TXT_MODEL_NO)\" disabled>"); 
			out.println("<td width='*%'></td>"); 									
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_SUB_MODEL_NO'  class=div_input>Sub Model Code *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_NO' maxlength='20' size='20' onblur=\"assignState('M5'),makeRequest(document.Form1.TXT_SUB_MODEL_NO)\" disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Registration No</td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_REG_NO' maxlength='20' size='20' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Date of Registration [DD-MM-YYYY]</td>"); 
			out.println("<td width='25%' ><input class='txt_input5' type='text' name='TXT_REG_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_MM' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_YY' maxlength='4' size='4' onBlur='check_date(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)'> <a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CHASSIS_NO'  class=div_input>Chassis No / Serial No </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CHASSIS_NO' maxlength='50' size='20' onblur=\"val_chassis_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
					
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_ENGINE_NO'  class=div_input>Engine No </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_ENGINE_NO' maxlength='50' size='20' onblur=\"val_engine_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_COLOUR'  class=div_input>Colour *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_COLOUR' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_SEATING_CAPACITY'  class=div_input>Seating Capacity *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' STYLE=\"{text-align:right}\" name='TXT_SEATING_CAPACITY' maxlength='3' size='10' onBlur=\"val_seating_capacity()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Engine Capacity </td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_ENGINE_CAPACITY' maxlength='20' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Transmission Media </td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_TRANSMISSION' maxlength='20' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Year of Manufacture </td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_YEAR_MANUFACTURE' maxlength='4' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_TO_BE_DELIVERD_TO'  class=div_input>To be Delivered to (Name) *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_TO_BE_DELIVERD_TO' maxlength='100' size='100' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION'  class=div_input>Address *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' style=\"width:300px\" type='text' name='TXT_LOCATION' maxlength='200' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"validate_city()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_CITY' value=\"Help\" onClick=\"help_button_6('1','10','2','m_help_TXT_CITY_CODE_sql','6')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FUEL_CONV_STS'  class=div_input>Fuel Conversion Status</DIV></td>"); 
			out.println("<td width='25%' ><select name='TXT_FUEL_CONV_STS' class=\"txt_input\" STYLE=\"width:60\" disabled>");
			out.println("<OPTION value=\"N\" SELECTED>No</option>");
			out.println("<OPTION value=\"Y\" >Yes</option>");
			out.println("</SELECT></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Due Date [DD-MM-YYYY]</td>"); 
			out.println("<td width='25%' ><input class='txt_input5' type='text' name='TXT_DUE_DATE_DD' maxlength='2' size='2' disabled>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_DUE_DATE_MM' maxlength='2' size='2' disabled>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_DUE_DATE_YY' maxlength='4' size='4' disabled onBlur='check_date(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)'> <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> </td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); out.println("</tr>"); 
			out.println("<tr>"); out.println("</tr>"); 
			
			*/
			
			
			out.println("<table align='center' width='100%' class='table' border='0' >"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); 
			//modified by madhawa 2009-10-16 add function call to clearInvoiceNo on onclick event 
			out.println("<input class='but_input' type='button' name='BUT_APPLICATION_NO' value=\"...\" onClick=\"help_button_1('1','10','5','m_help_TXT_APPLICATION_NO_1','1'),clearInvoiceNo()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			///--- Added by DSP Chathuranga 2013-08-27 for #8996 ---///
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='15' onblur=\"assignState('M33'),makeRequest(document.Form1.TXT_FINANCE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"...\" onClick=\"help_button_33('1','10','5','m_help_TXT_APPLICATION_NO_1','33'),clearInvoiceNo()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			///-------------------- End -----------------------------///
			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No *</DIV></td>"); 
			
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' onblur=\"assignState('M12'),makeRequest(document.Form1.TXT_INVOICE_NO)\">");  // commented by udara 20-10-2016
			
			// added by udara 20-10-2016
			if(m_edit_screen.equals("Y"))
			     out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' onblur=\"assignState('M12'),makeRequest(document.Form1.TXT_INVOICE_NO)\">");
			else
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' onblur=\"assignState('M12'),makeRequest(document.Form1.TXT_INVOICE_NO)\" disabled >");
			// end by udara 20-10-2016
			
			//modified by madhawa 2009-10-16
			//To call help_update function only when Application No is not null,defined a new function (..call_help_update_AppliNo_Notnull..) and called "help_update('1','10','25','m_help_TXT_INVOICE_NO_sql','99')" inside that when Application No is not null
			//call call_help_update_AppliNo_Notnull on onclick event
			
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"call_help_update_AppliNo_Notnull()\" ></td>"); // commented by udara 26-09-2016
			
			// added by udara 26-09-2016
			if(m_edit_screen.equals("Y"))
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"call_help_update_AppliNo_Notnull()\" ></td>");
			else
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"call_help_update_AppliNo_Notnull()\" disabled ></td>");
			// end by udara 26-09-2016
			
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table' border='0' >"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_ASSET_ID'  class=div_input>Asset ID *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_ASSET_ID' maxlength='15' size='15' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_ASSET_ID)\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ASSET_ID' value=\"...\" onClick=\"help_button_2('1','10','8','m_help_TXT_ASSET_ID_sql2','2')\" disabled ></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_ASSET_ID_DETAILS' value=\"Details\" onClick=\"help_button_asset_details(document.Form1.TXT_ASSET_ID)\"></td>"); 
			out.println("<td width='18%' ><DIV id='DIV_TXT_VENDOR_NAME'  class=div_input>Asset Description</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' style=\"width:300px\" type='text' name='TXT_ASSET_DESC' maxlength='200' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 		
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VENDOR_CODE'  class=div_input>Vendor Code *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_VENDOR_CODE' maxlength='10' size='10' onblur=\"assignState('M10'),makeRequest(document.Form1.TXT_VENDOR_CODE)\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_VEN' value=\"...\" onClick=\"help_vendor('1','10','0','m_help_vendor_proforma_invoice','77')\" disabled></td>"); // m_help_vendor_proforma_invoice //m_help_TXT_VENDOR_CODE_sql
			out.println("<td width='18%' ><DIV id='DIV_TXT_VENDOR_NAME'  class=div_input>Vendor Name</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' style=\"width:300px\" type='text' name='TXT_VENDOR_NAME' maxlength='200' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Vendor Branch Location *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M11'),makeRequest(document.Form1.TXT_LOCATION_CODE)\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_LOC' value=\"...\" onClick=\"help_branch('1','10','7','m_help_TXT_MAS_VENDOR_LOCATION_sql','88')\" disabled ></td>"); 
			out.println("<td width='18%' ><DIV id='DIV_TXT_INVOICE_DOC_NO'  class=div_input>Vendor Document Reference No</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_INVOICE_DOC_NO' maxlength='30' size='20' disabled ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			rs=stmt.executeQuery(" SELECT CURR_CODE,CURR_SYMBOL,REP_CURR,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				" WHERE  ACTIVE_STATUS ='Y' "+
				" AND REP_CURR='Y' ");
			
			boolean more=rs.next();
			if(more){
				//out.println("<option value=\""+rs.getString(1)+"\" SELECTED>"+rs.getString(2)+"</option>+");			
				out.println("<input type='hidden' name='TXT_CURR_CODE' value="+rs.getString(1)+">"); 
				
			}
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_PRICING_NO'  class=div_input>Pricing No *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_PRICING_NO' maxlength='15' size='15' onBlur=\"assignState('M9'),makeRequest(document.Form1.TXT_PRICING_NO)\" disabled >"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_PRICING_NO' value=\"Help\" onClick=\"help_button_5('1','10','32','PriceSql_invoice','5')\"></td>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_PRICING_NO' value=\"...\" onClick=\"help_button_5('1','10','32','PriceSql_invoice_new','5')\" disabled ></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_PRICING_NO_DETAILS' value=\"Details\" onClick=\"help_button_pricing_details(document.Form1.TXT_PRICING_NO)\"></td>"); 
			out.println("<td width='18%'>&nbsp;</td>"); 
			out.println("<td width='35%'>&nbsp;</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_NET_PRICE'  class=div_input>Net Price *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_NET_PRICE' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_NET_PRICE,25),val_total_amt()\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='18%' ><DIV id='DIV_TXT_VAT'  class=div_input>VAT *</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_VAT' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_VAT,25),val_total_amt()\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_TOTAL_AMOUNT'  class=div_input>Total Amount *</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_TOTAL_AMOUNT' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_TOTAL_AMOUNT,25)\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='18%' ><DIV id='DIV_TXT_VALUE'  class=div_input>Value *</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_VALUE' maxlength='25' size='25' onBlur=\"check_number(document.Form1.TXT_VALUE,25)\" STYLE='{text-align:right;}' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table' border='0' >"); 			
			//out.println("<tr>"); out.println("</tr>"); 
			//out.println("<tr>"); out.println("</tr>"); 
			
			
			//comment by nuwan de silva 15-11-2007----
			/*out.println("<tr >");
			out.println("<td width='20%' ><DIV id='DIV_TXT_MODEL_NO'  class=div_input>Model Code *</DIV></td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_MODEL_NO' maxlength='20' size='20' onblur=\"assignState('M4'),makeRequest(document.Form1.TXT_MODEL_NO)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_MODEL_NO' value=\"Help\" onClick=\"help_button_3('0','10','5','m_help_TXT_MODEL_CODE_inv_sql','3')\"></td>"); 
			//out.println("<td width='20%' ><DIV id='DIV_TXT_SEATING_CAPACITY'  class=div_input>Seating Capacity *</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SEATING_CAPACITY' maxlength='3' size='10' onBlur=\"val_seating_capacity()\"></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Model Description </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MODEL_DESC' maxlength='20' size='20' style='width:200px' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
															
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_SUB_MODEL_NO'  class=div_input>Sub Model Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_NO' maxlength='20' size='20' onblur=\"assignState('M5'),makeRequest(document.Form1.TXT_SUB_MODEL_NO)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_SUB_MODEL_NO' value=\"Help\" onClick=\"help_button_4('0','10','5','m_help_TXT_SUB_M_CODE_sql','4')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
					
			out.println("<tr>"); 
			out.println("<td width='20%' >Sub Model Description </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_DESC' maxlength='20' size='20' style='width:200px'  disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CHASSIS_NO'  class=div_input>Chassis No / Serial No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CHASSIS_NO' maxlength='50' size='20' onblur=\"val_chassis_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_ENGINE_NO'  class=div_input>Engine No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ENGINE_NO' maxlength='50' size='20' onblur=\"val_engine_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_SEATING_CAPACITY'  class=div_input>Seating Capacity </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' STYLE=\"{text-align:left}\" name='TXT_SEATING_CAPACITY' maxlength='3' size='10' onBlur=\"val_seating_capacity()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_COLOUR'  class=div_input>Colour </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLOUR' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Registration No</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REG_NO' maxlength='20' size='20' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table' border='0' >"); 			
			//added by nuwan de silva on 22-04-2008-----------------------------------------------------------------------------------
			out.println("<tr>"); 
			out.println("<td width='20%' >CR Book No</td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CR_BOOK_NO' maxlength='20' size='20' ></td>"); 
			//out.println("<td width='*%'></td>"); 
			//out.println("</tr>"); 
			
			// thamali 2013.08.12
			//out.println("<tr>"); 
			out.println("<td width='18%' >New CR Book Received</td>"); 
			out.println("<td width='35%' ><input class='' type='checkbox' name='CHK_CR_BOOK_RECEIVED' onClick='val_change()' VALUE = 'N' ></td>");  
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table' border='0' >"); 			
			out.println("<tr>"); 
			out.println("<td width='20%' >District Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DISTRICT_CODE' maxlength='10' size='10' >"); 
			out.println("<input class='but_input' type='button' name='BUT_DISTRICT_CODE' value=\"...\" onClick=\"help_button_district('1','10','0','m_help_TXT_DISTRICT_CODE_sql1','12')\"  ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			
			
			//-----------------------------------------------------------------------------------------------------------------------
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Date of Registration [DD-MM-YYYY]</td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REG_DATE' maxlength='10' size='10' onBlur=\"checkDate(document.Form1.TXT_REG_DATE.value)\"></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_REG_DATE_DD' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)' disabled>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_MM' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)' disabled>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_REG_DATE_YY' maxlength='4' size='4' onBlur='check_date(document.Form1.TXT_REG_DATE_DD,document.Form1.TXT_REG_DATE_MM,document.Form1.TXT_REG_DATE_YY)' disabled><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			
			/*out.println("<tr>"); 
			out.println("<td width='20%' >Engine Capacity </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ENGINE_CAPACITY' maxlength='20' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Transmission Media </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TRANSMISSION' maxlength='20' size='20' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_YEAR_MANUFACTURE'  class=div_input>Year of Manufacture *</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_YEAR_MANUFACTURE' maxlength='4' onblur='check_number_2(this)'  size='20' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); //Added by Chandana on 06/09/2007 for Ref no.847 
			out.println("<td width='20%' >Extras Included </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_EXTRAS' style='width:350px' maxlength='100' size='100' value=\"\" disabled ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_TO_BE_DELIVERD_TO'  class=div_input>To be Delivered to (Name) *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TO_BE_DELIVERD_TO' maxlength='100' style='width:350px' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION'  class=div_input>Address *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION' style='width:350px' maxlength='300' size='300' value=\"\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table' border='0' >"); 			
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"validate_city()\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_CITY' value=\"...\" onClick=\"help_button_6('1','10','2','m_help_TXT_CITY_CODE_sql','6')\" disabled ></td>"); 
			out.println("<td width='18%' ><DIV id='DIV_TXT_CITY_NAME'  class=div_input>City Name</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_CITY_NAME' style='width:300px' maxlength='300' size='300' value=\"\"  disabled ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table' border='0' >"); 			
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FUEL_CONV_STS'  class=div_input>Fuel Conversion Status</DIV></td>"); 
			out.println("<td width='30%' ><select name='TXT_FUEL_CONV_STS' class=\"txt_input\" STYLE=\"width:60\" disabled >");
			out.println("<OPTION value=\"N\" SELECTED>No</option>");
			out.println("<OPTION value=\"Y\" >Yes</option>");
			out.println("</SELECT></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='20%' >Valid Till [DD-MM-YYYY]</td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_DUE_DATE_DD' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)' disabled >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_DUE_DATE_MM' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)' disabled>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_DUE_DATE_YY' maxlength='4' size='4' onBlur='check_date(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)' disabled><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); out.println("</tr>"); 
			out.println("<tr>"); out.println("</tr>"); 
			
			//----added by prabash on 27-04-2012----**
			
			//out.println("<tr ><td width='20%' > &nbsp</td></tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_SUM_INSURED'  class=div_input>Sum Insured </DIV></td>"); 
			out.println("<td width='30%'><input  class='txt_input' type='text' name='TXT_SUM_INSURED' maxlength='15' size='15' STYLE='{text-align:right;}' onBlur=\"check_number(document.Form1.TXT_SUM_INSURED,25) \" >"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</tr><td width='20%' >&nbsp</td></tr >"); 	
			//	out.println("</table>");
			
			//	out.println("<table align='center' width='100%' class='table' border='0' >");
			out.println("<tr >"); 
			out.println("<td width='20%' ><B><U>Vehicle Security Details </B></U></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 	
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_AREA'  class=div_input> Vehicle located address</DIV></td>"); 
			//	out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_AREA' maxlength='15' size='15'  >"); 
			out.println("<td width='25%' ><TEXTAREA name='TXT_AREA' class='txt_input' style='width:200px'></TEXTAREA>"); 
			out.println("</td>"); 
			//out.println("<td width='*%'></td>"); 
			
			out.println("<td width='18%' ><DIV id='DIV_TXT_OWN_ADD'  class=div_input>Owner Address</DIV></td>"); 
			//	out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_AREA' maxlength='15' size='15'  >"); 
			out.println("<td width='35%' ><TEXTAREA name='TXT_OWN_ADD' class='txt_input' style='width:200px'></TEXTAREA>"); 
			out.println("<td width='*%'></td>");
			out.println("</td>"); 
			
			out.println("</tr>"); 
			
			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_POLICE'  class=div_input>Nearest Post Office and police Station</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_POLICE' maxlength='15' size='15'  >"); 
			out.println("</td>"); 
			
			out.println("<td width='18%' ><DIV id='DIV_COLLE_SEC'  class=div_input>Collectral Security</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_COLLE_SEC' maxlength='15' size='15'  >"); 
			out.println("</td>");
			
			out.println("<td width='*%'></td>"); 
			
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_LIC_AUTH'  class=div_input>Licencing Autharity</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_LIC_AUTH' maxlength='15' size='15'  >"); 
			out.println("</td>"); 
			
			out.println("<td width='18%' ><DIV id='DIV_VEHICAL_AGA'  class=div_input>Pradeshiya Sabaha</DIV></td>"); 
			out.println("<td width='35%' ><input class='txt_input' type='text' name='VEHICAL_AGA' maxlength='15' size='15'  >"); 
			out.println("</td>");
			
			out.println("<td width='*%'></td>"); 
			
			out.println("</tr>"); 
			
			
			
			//--------------------------------------**
			
			
			
			out.println("</table>"); 
			out.println("<br>"); 
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table>");  
			
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//	out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"New\"></td>");  
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			
			
			out.println("</table>");  
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			//}
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
