
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - CLIENT CREATION 
//ID:1.52 Client Creation Process
//CREATED BY:N.V.P.Chandana
//DATE/TIME:01-08-2006
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_client_creation1  extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Client Creation </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			 if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("			 alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("    else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_CLIENT_CODE.value !=''&& document.Form1.hid_help_status.value == 'H9' ){");
      out.println("    assign_data(data_vec);");
      out.println("   }");
      out.println("    else if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_CLIENT_CODE.value !=''&& document.Form1.hid_help_status.value == 'H9' ){");
      out.println("    alert('Invalid Code,Use Help');");
      out.println("    document.Form1.TXT_CLIENT_CODE.value='';");
      out.println("    document.Form1.TXT_CLIENT_CODE.focus();");
    //out.println("    new_window();");
      out.println("   }");
			
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H1'&& document.Form1.TXT_BUSINESS_SUB_SECTOR.value !='' ){");
      out.println("    alert('Invalid Code');");
      out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value='';");
      out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.focus();");
      out.println("    }");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H2'&& document.Form1.TXT_CITY_CODE.value !='' ){");
      out.println("    alert('Invalid Code');");
      out.println("    document.Form1.TXT_CITY_CODE.value='';");
      out.println("    document.Form1.TXT_CITY_CODE.focus();");
      out.println("   }");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H3'&& document.Form1.TXT_CAT_TYPE_CODE.value !='' ){");
      out.println("    alert('Invalid Code');");
      out.println("    document.Form1.TXT_CAT_TYPE_CODE.value='';");
      out.println("    document.Form1.TXT_CAT_TYPE_CODE.focus();");
      out.println("   }");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H4'&& document.Form1.TXT_REGISTERED_CITY_CODE.value !='' ){");
      out.println("    alert('Invalid Code');");
      out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value='';");
      out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.focus();");
      out.println("   }");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H5'&& document.Form1.TXT_NATIONALITY.value !='' ){");
      out.println("    alert('Invalid Code');");
      out.println("    document.Form1.TXT_NATIONALITY.value='';");
      out.println("    document.Form1.TXT_NATIONALITY.focus();");
      out.println("   }");	
			
			
			out.println("}");
			
			
			
			
			out.println("   function assign_data(data_vec) { ");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0]; ");
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=data_vec[1]; ");
			out.println("    document.Form1.TXT_FULL_NAME.value=data_vec[2]; ");
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=data_vec[3]; ");
			out.println("    document.Form1.TXT_CLIENT_CATEGORY.value=data_vec[4]; ");
			out.println("   if(document.Form1.TXT_CLIENT_CATEGORY.value=='IND'){ ");
			out.println("    indElement();");
			out.println("    document.Form1.TXT_ADDRESS1.value=data_vec[5]; ");
			out.println("    document.Form1.TXT_ADDRESS2.value=data_vec[6]; ");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[7]; ");			
			out.println("    document.Form1.TXT_REFERENCE.value=data_vec[8]; ");
			out.println("    document.Form1.TXT_TEL_NO.value=data_vec[9]; ");
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[10]; ");
			out.println("    document.Form1.TXT_EMAIL.value=data_vec[11]; ");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=data_vec[12]; ");
			out.println("    document.Form1.TXT_MOBILE_NO.value=data_vec[13]; ");
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=data_vec[14]; ");
			out.println("    document.Form1.TXT_NIC_NO.value=data_vec[15]; ");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=data_vec[16]; ");
			out.println("    document.Form1.TXT_DESIGNATION.value=data_vec[18]; ");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=data_vec[19]; ");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=data_vec[20]; ");
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value=data_vec[21]; ");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value=data_vec[22]; ");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value=data_vec[23]; ");
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=data_vec[24]; ");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=data_vec[25]; ");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value=data_vec[26]; ");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value=data_vec[27]; ");
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=data_vec[28]; ");
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value=data_vec[29]; ");
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value=data_vec[30]; ");
			out.println("    document.Form1.TXT_F_TEL_NO.value=data_vec[31]; ");
			out.println("    document.Form1.TXT_F_FAX_NO.value=data_vec[32]; ");
			out.println("    document.Form1.TXT_F_EMAIL.value=data_vec[33]; ");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=data_vec[34]; ");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value=data_vec[35]; ");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=data_vec[36]; ");
			out.println("    document.Form1.TXT_VAT_REG_DATE.value=data_vec[37]; ");
			out.println("    document.Form1.TXT_TITLE.value=data_vec[38]; ");
			out.println("    document.Form1.TXT_FIRST_NAME.value=data_vec[39]; ");
			out.println("    document.Form1.TXT_SURNAME.value=data_vec[40]; ");
			out.println("    document.Form1.TXT_INITIALS.value=data_vec[41]; ");
			out.println("    document.Form1.TXT_OTHER_NAME.value=data_vec[42]; ");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value=data_vec[43]; ");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value=data_vec[44]; ");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value=data_vec[45]; ");
			out.println("    document.Form1.TXT_PASSPORT_NO.value=data_vec[46]; ");
			out.println("    document.Form1.TXT_MARITAL_STATUS.value=data_vec[47]; ");
			out.println("    document.Form1.TXT_DATE_OF_BIRTH.value=data_vec[48]; ");
			out.println("    document.Form1.TXT_NATIONALITY.value=data_vec[49]; ");
			out.println("    document.Form1.TXT_GENDER.value=data_vec[50]; ");
			out.println("    }");
			out.println("    else {");
			out.println("    corElement();");
			out.println("    document.Form1.TXT_ADDRESS1.value=data_vec[5]; ");
			out.println("    document.Form1.TXT_ADDRESS2.value=data_vec[6]; ");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[7]; ");
			out.println("    document.Form1.TXT_TEL_NO.value=data_vec[9]; ");
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[10]; ");
			out.println("    document.Form1.TXT_EMAIL.value=data_vec[11]; ");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=data_vec[12]; ");
			out.println("    document.Form1.TXT_MOBILE_NO.value=data_vec[13]; ");
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=data_vec[14]; ");
			out.println("    document.Form1.TXT_NIC_NO.value=data_vec[15]; ");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=data_vec[16]; ");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=data_vec[17]; ");
			out.println("    document.Form1.TXT_DESIGNATION.value=data_vec[18]; ");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=data_vec[19]; ");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=data_vec[20]; ");
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value=data_vec[21]; ");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value=data_vec[22]; ");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value=data_vec[23]; ");
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=data_vec[24]; ");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=data_vec[25]; ");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value=data_vec[26]; ");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value=data_vec[27]; ");
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=data_vec[28]; ");
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value=data_vec[29]; ");
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value=data_vec[30]; ");
			out.println("    document.Form1.TXT_F_TEL_NO.value=data_vec[31]; ");
			out.println("    document.Form1.TXT_F_FAX_NO.value=data_vec[32]; ");
			out.println("    document.Form1.TXT_F_EMAIL.value=data_vec[33]; ");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=data_vec[34]; ");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value=data_vec[35]; ");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=data_vec[36]; ");
			out.println("    document.Form1.TXT_VAT_REG_DATE.value=data_vec[37]; ");
			out.println("   }");
			out.println("   }");
			
			out.println("function makeRequest(obj) {");

			
			out.println("if(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value!=\"RACT\")");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation&data_val=\"+obj.value+\"&ac_status=Y\";");
      out.println("else");
      out.println("if(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value==\"RACT\")");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_creation&data_val=\"+obj.value+\"&ac_status=N\";");
      out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H1')");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_business_sectors&data_val=\"+obj.value;");
      out.println("else");
      out.println("if(document.Form1.hid_help_status.value == 'H2')");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value;");
      out.println("else");
      out.println("if(document.Form1.hid_help_status.value == 'H3')");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_customer_category&data_val=\"+obj.value;");
      out.println("else");
      out.println("if(document.Form1.hid_help_status.value == 'H4')");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value;");
      out.println("else");
      out.println("if(document.Form1.hid_help_status.value == 'H5')");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_nationality&data_val=\"+obj.value;");
      out.println("load_interface(m_url,'XML');");	
      out.println("}");

			
			
			
			


			out.println("function validate_data(){"); 
			out.println("//validations goes here");
			
						
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FULL_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FULL_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		
			out.println("else if(document.Form1.TXT_CLIENT_CATEGORY.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CATEGORY.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
				
			out.println("else if(document.Form1.TXT_CAT_TYPE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CAT_TYPE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
					     
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_client_creation ';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

		  out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation ';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation ';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_client_creation \";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Client Creation  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Client Creation  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
						
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_CLIENT_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_FULL_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_BUSINESS_SUB_SECTOR.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CATEGORY.disabled=true;");
			out.println("if(document.Form1.TXT_CLIENT_CATEGORY.value==\"IND\") {");
			out.println("alert('dddddd');");
      
			out.println(" m=\"TXT_ADDRESS1\" ;");
			
			out.println("document.Form1.elements[m].disabled=true;");
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REFERENCE.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CAT_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NIC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_BUSINESS_CERTIFICATE_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_KEY_DECISION_MAKER.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION.disabled=true;"); 
			out.println("document.Form1.TXT_DIRECT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CONTACT_FOR_PAYMENT.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION_PAYMENT.disabled=true;"); 
			//out.println("document.Form1.TXT_FACTORY_ADDRESS1.disabled=true;"); 
			//out.println("document.Form1.TXT_FACTORY_ADDRESS2.disabled=true;"); 
			//out.println("document.Form1.TXT_FACTORY_STATUS.disabled=true;"); 
			//out.println("document.Form1.TXT_F_CONTACT_PERSON.disabled=true;"); 
			//out.println("document.Form1.TXT_REGISTERED_ADDRESS1.disabled=true;"); 
			//out.println("document.Form1.TXT_REGISTERED_ADDRESS2.disabled=true;"); 
			//out.println("document.Form1.TXT_REGISTERED_CITY_CODE.disabled=true;"); 
			//out.println("document.Form1.TXT_REGISTERED_STATUS.disabled=true;"); 
			//out.println("document.Form1.TXT_CORRESPONDENCE_STATUS.disabled=true;"); 
			//out.println("document.Form1.TXT_F_TEL_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_F_FAX_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_F_EMAIL.disabled=true;"); 
			//out.println("document.Form1.TXT_ISSUED_SHARE_CAPITAL.disabled=true;"); 
			//out.println("document.Form1.TXT_DATE_OF_INCORPORATION.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_SURNAME.disabled=true;"); 
			out.println("document.Form1.TXT_INITIALS.disabled=true;"); 
			out.println("document.Form1.TXT_OTHER_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_RESIDENTIAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_YEARS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_MONTHS.disabled=true;"); 
			out.println("document.Form1.TXT_PASSPORT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MARITAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_BIRTH.disabled=true;"); 
			out.println("document.Form1.TXT_NATIONALITY.disabled=true;"); 
			out.println("document.Form1.TXT_GENDER.disabled=true;"); 
      out.println("}");
						
		/*	out.println("else {");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_CLIENT_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_FULL_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_BUSINESS_SUB_SECTOR.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CATEGORY.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;");
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REFERENCE.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CAT_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NIC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_BUSINESS_CERTIFICATE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_KEY_DECISION_MAKER.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION.disabled=true;"); 
			out.println("document.Form1.TXT_DIRECT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CONTACT_FOR_PAYMENT.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION_PAYMENT.disabled=true;"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_FACTORY_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_F_CONTACT_PERSON.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REGISTERED_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_CORRESPONDENCE_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_F_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_F_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_F_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_ISSUED_SHARE_CAPITAL.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_SURNAME.disabled=true;"); 
			out.println("document.Form1.TXT_INITIALS.disabled=true;"); 
			out.println("document.Form1.TXT_OTHER_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_RESIDENTIAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_YEARS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_MONTHS.disabled=true;"); 
			out.println("document.Form1.TXT_PASSPORT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MARITAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_BIRTH.disabled=true;"); 
			out.println("document.Form1.TXT_NATIONALITY.disabled=true;"); 
			out.println("document.Form1.TXT_GENDER.disabled=true;"); 
			out.println("}"); */
			
			
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 

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
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
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
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_SUB_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BUSINESS_SUB_SECTOR.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_CAT_TYPE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CAT_TYPE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_REGISTERED_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_NATIONALITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_NATIONALITY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_NATIONALITY.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_TYPE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_TYPE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=oBj.valout[2];"); 
			out.println("}"); 
			

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql_1\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"N@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";}"); 
			out.println("    HelpBox('1','10','46');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=oBj.valout[5];");			
			out.println("    document.Form1.TXT_CLIENT_CATEGORY.value=oBj.valout[6];");
			
			
			out.println("    if( document.Form1.TXT_CLIENT_CATEGORY.value=='IND'){");
			out.println("    indElement_Assing();");
			out.println("    }");
			out.println("    else { ");
			out.println("    corElement_Assing();");
			out.println("    }");
  		out.println("}"); 
			
			
			
			 out.println("function select_Inner(cat) {");
			 out.println("   if(cat=='IND'){");
			 out.println("	 indElement();");
			 out.println("   }");
			 out.println("   else  if(cat=='COR') {");
			 out.println("	 corElement();");	
			 out.println("   }");	
			 out.println("}");
			
			
			 out.println(" function assign_help_status(obj){");
       //out.println(" alert('ok');");
       out.println(" document.Form1.hid_help_status.value =obj; ");
       out.println("}");
 

			
			  	
			 out.println("function indElement() {");
				
	     out.println("alert('indElement()');");
				
				
			 out.println(" e_mode.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FIRST_NAME\"  class=\"div_input\">First Name </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FIRST_NAME\" maxlength=\"20\" size=\"20\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_SURNAME\"  class=\"div_input\">Surname </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_SURNAME\" maxlength=\"20\" size=\"20\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_INITIALS\"  class=\"div_input\">Initials </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_INITIALS\" maxlength=\"20\" size=\"20\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");  

		   out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" >Other Name </td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_OTHER_NAME\" maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
				
				
				
				 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_MARITAL_STATUS\"  class=\"div_input\">Marital Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_MARITAL_STATUS\" maxlength=\"9\" size=\"9\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DATE_OF_BIRTH\"  class=\"div_input\">Date Of Birth </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_BIRTH\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_NATIONALITY\"  class=\"div_input\">Nationality </DIV></td>'+ "); 
			 out.println("    '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_NATIONALITY\" maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H5\"),makeRequest(document.Form1.TXT_NATIONALITY)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_NATIONALITY\" value=\"Help\" onClick=\"help_button_5()\"></td>'+ ");
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_GENDER\"  class=\"div_input\">Gender </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_GENDER\" maxlength=\"6\" size=\"6\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
			 
				
				
				
				
				
				
				
				
				
				out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS1\"  class=\"div_input\" > Address1 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1  maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS2\"  class=\"div_input\" > Address2 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS2  maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + ");			
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=\"div_input\" >City Code </DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_CITY_CODE\" maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H2\"),makeRequest(document.Form1.TXT_CITY_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CITY_CODE\" value=\"Help\" onClick=\"help_button_2()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + "); 


			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_REFERENCE\"  class=\"div_input\">Reference </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REFERENCE\" maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
			 
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_TEL_NO\"  class=\"div_input\">Telephone No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_FAX_NO\" class=\"div_input\">Fax No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FAX_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_EMAIL\"  class=\"div_input\">Email </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
			 
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_OFFICE_TEL_NO\"  class=\"div_input\">Office Tel No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_MOBILE_NO\"  class=\"div_input\">Mobile No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+ "); 
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CAT_TYPE_CODE\"  class=\"div_input\">Catogery Type Code *</DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CAT_TYPE_CODE\" maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H3\"),makeRequest(document.Form1.TXT_CAT_TYPE_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CAT_TYPE_CODE\" value=\"Help\" onClick=\"help_button_3()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  ");  
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_NIC_NO\"  class=div_input>NIC No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_NIC_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  "); 
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_PASSPORT_NO\"  class=\"div_input\">Passport No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_PASSPORT_NO\" maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+ ");
				
			 
				
				
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_BUSINESS_CERTIFICATE_NO\"  class=\"div_input\">Business Certificate No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUSINESS_CERTIFICATE_NO\" maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
				
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION\"  class=\"div_input\">Designation </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
		 
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DIRECT_TEL_NO\"  class=\"div_input\">Direct Tet No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DIRECT_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_CONTACT_FOR_PAYMENT\"  class=\"div_input\">Contact For Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CONTACT_FOR_PAYMENT\" maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	    
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION_PAYMENT\"  class=\"div_input\">Designation Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION_PAYMENT\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_ADDRESS1\"  class=\"div_input\">Factory Address1 </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_ADDRESS1\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_ADDRESS2\"  class=\"div_input\">Factory Address2 </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_ADDRESS2\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
			
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_STATUS\"  class=div_input>Factory Status </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_STATUS\" maxlength=\"4\" size=\"4\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
						
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_CONTACT_PERSON\"  class=div_input>Factory Contact Person </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_CONTACT_PERSON\" maxlength=\"100\" size=\"100\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_ADDRESS1\"  class=\"div_input\">Registered Address1 </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_ADDRESS1\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_ADDRESS2\"  class=\"div_input\">Registered Address2 </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_ADDRESS2\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_CITY_CODE\"  class=\"div_input\">Registered City Code </DIV></td>'+ "); 
			// out.println("    '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_CITY_CODE\" maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H4\"),makeRequest(document.Form1.TXT_REGISTERED_CITY_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_REGISTERED_CITY_CODE\" value=\"Help\" onClick=\"help_button_4()\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
			
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_STATUS\"  class=\"div_input\">Registered Status </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_STATUS\" maxlength=\"4\" size=\"4\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_CORRESPONDENCE_STATUS\"  class=\"div_input\">Correspondence Status </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CORRESPONDENCE_STATUS\" maxlength=\"4\" size=\"4\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
		
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_TEL_NO\"  class=\"div_input\">Factory Telephone No </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_FAX_NO\"  class=\"div_input\">Factory Fax No </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_F_FAX_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_EMAIL\"  class=\"div_input\">Factory Email </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_ISSUED_SHARE_CAPITAL\"  class=\"div_input\">Issued Share Capital </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_ISSUED_SHARE_CAPITAL\" maxlength=\"22\" size=\"22\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			// out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DATE_OF_INCORPORATION\"  class=\"div_input\">Date Of Incorporation </DIV></td>'+ "); 
			// out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			// out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_NO\"  class=div_input>Vat Reg No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_NO\" maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_DATE\"  class=\"div_input\">Vat Reg Date </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_DATE\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=\"div_input\">Title </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TITLE\" maxlength=\"5\" size=\"5\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
			
			
			
			
			 			  
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_RESIDENTIAL_STATUS\"  class=\"div_input\">Residential Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_RESIDENTIAL_STATUS\" maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
			 
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DURATION_AT_YEARS\"  class=\"div_input\">Duration at Years </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_YEARS\" maxlength=\"22\" size=\"22\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DURATION_AT_MONTHS\"  class=\"div_input\">Duration at Months </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_MONTHS\" maxlength=\"2\" size=\"2\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>';  "); 

			 			
										 
			 out.println("   }");
			 
				
				out.println("function ind_disable() {");
				
				out.println("if(document.Form1.SCREEN_NAME.value==\"DACT\") {");
				
				out.println("m1='TXT_FIRST_NAME';");				
				out.println("document.Form1.elements[m1].disabled=true;");
				out.println("m2='TXT_SURNAME';");
				out.println("document.Form1.elements[m2].disabled=true;");
				out.println("m3='TXT_INITIALS';");
				out.println("document.Form1.elements[m3].disabled=true;");
				out.println("m4='TXT_OTHER_NAME';");
				out.println("document.Form1.elements[m4].disabled=true;");
				out.println("m5='TXT_MARITAL_STATUS';");
				out.println("document.Form1.elements[m5].disabled=true;");
				out.println("m6='TXT_DATE_OF_BIRTH';");
				out.println("document.Form1.elements[m6].disabled=true;");
				out.println("m7='TXT_NATIONALITY';");
				out.println("document.Form1.elements[m7].disabled=true;");
				out.println("m8='TXT_GENDER';");
				out.println("document.Form1.elements[m8].disabled=true;");
				out.println("m9='TXT_ADDRESS1';");
				out.println("document.Form1.elements[m9].disabled=true;");
				out.println("m10='TXT_ADDRESS2';");
				out.println("document.Form1.elements[m10].disabled=true;");
				out.println("m11='TXT_CITY_CODE';");
				out.println("document.Form1.elements[m11].disabled=true;");
				out.println("m12='TXT_REFERENCE';");
				out.println("document.Form1.elements[m12].disabled=true;");
				out.println("m13='TXT_TEL_NO';");
				out.println("document.Form1.elements[m13].disabled=true;");
				out.println("m14='TXT_FAX_NO';");
				out.println("document.Form1.elements[m14].disabled=true;");
				out.println("m15='TXT_EMAIL';");
				out.println("document.Form1.elements[m15].disabled=true;");
				out.println("m16='TXT_OFFICE_TEL_NO';");
				out.println("document.Form1.elements[m16].disabled=true;");
				out.println("m17='TXT_CAT_TYPE_CODE';");
				out.println("document.Form1.elements[m17].disabled=true;");
				out.println("m18='TXT_MOBILE_NO';");
				out.println("document.Form1.elements[m18].disabled=true;");
				out.println("m19='TXT_NIC_NO';");
				out.println("document.Form1.elements[m19].disabled=true;");
				out.println("m20='TXT_PASSPORT_NO';");
				out.println("document.Form1.elements[m20].disabled=true;");
				out.println("m21='TXT_BUSINESS_CERTIFICATE_NO';");
				out.println("document.Form1.elements[m21].disabled=true;");
				out.println("m22='TXT_DESIGNATION';");
				out.println("document.Form1.elements[m22].disabled=true;");
				out.println("m23='TXT_DIRECT_TEL_NO';");
				out.println("document.Form1.elements[m23].disabled=true;");
				out.println("m24='TXT_CONTACT_FOR_PAYMENT';");
				out.println("document.Form1.elements[m24].disabled=true;");
				out.println("m25='TXT_DESIGNATION_PAYMENT';");
				out.println("document.Form1.elements[m25].disabled=true;");
				out.println("m26='TXT_VAT_REG_NO';");
				out.println("document.Form1.elements[m26].disabled=true;");
				out.println("m27='TXT_VAT_REG_DATE';");
				out.println("document.Form1.elements[m27].disabled=true;");
				out.println("m28='TXT_TITLE';");
				out.println("document.Form1.elements[m28].disabled=true;");
				out.println("m29='TXT_RESIDENTIAL_STATUS';");
				out.println("document.Form1.elements[m29].disabled=true;");
				out.println("m30='TXT_DURATION_AT_YEARS';");
				out.println("document.Form1.elements[m30].disabled=true;");
				out.println("m31='TXT_DURATION_AT_MONTHS';");
				out.println("document.Form1.elements[m31].disabled=true;");
				
				out.println("alert('ind');");
				
				out.println(" }");
				out.println(" }");
				

				
			 out.println("function indElement_Assing() {");
				
				
				out.println("alert('indElement_Assing');");
			
				
				
			 out.println(" e_mode.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FIRST_NAME\"  class=\"div_input\">First Name </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FIRST_NAME\" value='+oBj.valout[41]+' maxlength=\"20\" size=\"20\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_SURNAME\"  class=\"div_input\">Surname </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_SURNAME\" value='+oBj.valout[42]+' maxlength=\"20\" size=\"20\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_INITIALS\"  class=\"div_input\">Initials </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_INITIALS\" value='+oBj.valout[43]+' maxlength=\"20\" size=\"20\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");  

		   out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" >Other Name </td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_OTHER_NAME\" value='+oBj.valout[44]+' maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
				
				
				
				 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_MARITAL_STATUS\"  class=\"div_input\">Marital Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_MARITAL_STATUS\" value='+oBj.valout[49]+' maxlength=\"9\" size=\"9\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DATE_OF_BIRTH\"  class=\"div_input\">Date Of Birth </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_BIRTH\" value='+oBj.valout[50]+' maxlength=\"10\" size=\"10\" AA(oBj.valout[50])></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
				
			

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_NATIONALITY\"  class=\"div_input\">Nationality </DIV></td>'+ "); 
			 out.println("    '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_NATIONALITY\" value='+oBj.valout[51]+' maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H5\"),makeRequest(document.Form1.TXT_NATIONALITY)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_NATIONALITY\" value=\"Help\" onClick=\"help_button_5()\"></td>'+ ");
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_GENDER\"  class=\"div_input\">Gender </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_GENDER\" value='+oBj.valout[52]+' maxlength=\"6\" size=\"6\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>' + ");
				
				
				
				
			 out.println("   	'<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS1\"  class=\"div_input\" > Address1 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1 value='+oBj.valout[7]+'  maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS2\"  class=\"div_input\" > Address2 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS2 value='+oBj.valout[8]+'  maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + ");			
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=\"div_input\" >City Code </DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_CITY_CODE\" value='+oBj.valout[9]+' maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H2\"),makeRequest(document.Form1.TXT_CITY_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CITY_CODE\" value=\"Help\" onClick=\"help_button_2()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + "); 
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_REFERENCE\"  class=\"div_input\">Reference </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REFERENCE\" value='+oBj.valout[10]+' maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
			 
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_TEL_NO\"  class=\"div_input\">Tel No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TEL_NO\" value='+oBj.valout[11]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_FAX_NO\" class=\"div_input\">Fax No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FAX_NO\" value='+oBj.valout[12]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_EMAIL\"  class=\"div_input\">Email </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMAIL\" value='+oBj.valout[13]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
			 
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_OFFICE_TEL_NO\"  class=\"div_input\">Office Telephone No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO\" value='+oBj.valout[14]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_MOBILE_NO\"  class=\"div_input\">Mobile No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO\" value='+oBj.valout[15]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+ "); 
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CAT_TYPE_CODE\"  class=\"div_input\">Catogery Type Code *</DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CAT_TYPE_CODE\" value='+oBj.valout[16]+' maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H3\"),makeRequest(document.Form1.TXT_CAT_TYPE_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CAT_TYPE_CODE\" value=\"Help\" onClick=\"help_button_3()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  ");  
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_NIC_NO\"  class=div_input>NIC No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_NIC_NO\" value='+oBj.valout[17]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  "); 
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_PASSPORT_NO\"  class=\"div_input\">Passport No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_PASSPORT_NO\" value='+oBj.valout[48]+' maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");

				
			
				
				
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_BUSINESS_CERTIFICATE_NO\"  class=\"div_input\">Business Certificate No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUSINESS_CERTIFICATE_NO\" value='+oBj.valout[18]+' maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
				
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION\"  class=\"div_input\">Designation </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION\" value='+oBj.valout[20]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
		 
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DIRECT_TEL_NO\"  class=\"div_input\">Direct Telephone No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DIRECT_TEL_NO\" value='+oBj.valout[21]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_CONTACT_FOR_PAYMENT\"  class=\"div_input\">Contact For Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CONTACT_FOR_PAYMENT\" value='+oBj.valout[22]+' maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	    
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION_PAYMENT\"  class=\"div_input\">Designation Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION_PAYMENT\" value='+oBj.valout[23]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_NO\"  class=div_input>Vat Reg No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_NO\" value='+oBj.valout[38]+' maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_DATE\"  class=\"div_input\">Vat Reg Date </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_DATE\" value='+oBj.valout[39]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=\"div_input\">Title </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TITLE\" value='+oBj.valout[40]+' maxlength=\"5\" size=\"5\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
			
			 			  
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_RESIDENTIAL_STATUS\"  class=\"div_input\">Residential Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_RESIDENTIAL_STATUS\" value='+oBj.valout[45]+' maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
			 
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DURATION_AT_YEARS\"  class=\"div_input\">Duration at Years </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_YEARS\" value='+oBj.valout[46]+' maxlength=\"22\" size=\"22\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DURATION_AT_MONTHS\"  class=\"div_input\">Duration at Months </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DURATION_AT_MONTHS\" value='+oBj.valout[47]+' maxlength=\"2\" size=\"2\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'; "); 

			

			out.println("ind_disable();");
			
				
							 
			 out.println("   }");
				
				
				
				
				
				
				
				
				
				
			 out.println("function corElement() {");
				
				
				out.println("alert('corElement');");
			
			 out.println(" e_mode.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS1\"  class=\"div_input\" > Address1 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS1  maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS2\"  class=\"div_input\" > Address2 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS2  maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=\"div_input\" >City Code </DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_CITY_CODE\" maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H2\"),makeRequest(document.Form1.TXT_CITY_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CITY_CODE\" value=\"Help\" onClick=\"help_button_2()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + "); 
							
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_TEL_NO\"  class=\"div_input\">Telephone No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_FAX_NO\" class=\"div_input\">Fax No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FAX_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_EMAIL\"  class=\"div_input\">Email </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_OFFICE_TEL_NO\"  class=\"div_input\">Office Telephone No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_MOBILE_NO\"  class=\"div_input\">Mobile No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CAT_TYPE_CODE\"  class=\"div_input\">Category Type Code *</DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CAT_TYPE_CODE\" maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H3\"),makeRequest(document.Form1.TXT_CAT_TYPE_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CAT_TYPE_CODE\" value=\"Help\" onClick=\"help_button_3()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  ");  
			
			 // out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_NIC_NO\"  class=\"div_input\">NIC No </DIV></td>'+ "); 
			 //out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_NIC_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 //out.println("   '<td width=\"*%\"></td></tr></table>'+  "); 
								
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_BUSINESS_CERTIFICATE_NO\"  class=\"div_input\">Business Certificate No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUSINESS_CERTIFICATE_NO\" maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
		
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_KEY_DECISION_MAKER\" class=\"div_input\">Key Decision Maker </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_KEY_DECISION_MAKER\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  "); 

		   out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION\"  class=\"div_input\">Designation </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
		 
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DIRECT_TEL_NO\"  class=\"div_input\">Direct Telephone No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DIRECT_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_CONTACT_FOR_PAYMENT\"  class=\"div_input\">Contact For Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CONTACT_FOR_PAYMENT\" maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	    
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION_PAYMENT\"  class=\"div_input\">Designation Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION_PAYMENT\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_ADDRESS1\"  class=\"div_input\">Factory Address1 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_ADDRESS1\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_ADDRESS2\"  class=\"div_input\">Factory Address2 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_ADDRESS2\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
			
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_STATUS\"  class=div_input>Factory Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_STATUS\" maxlength=\"4\" size=\"4\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
						
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_CONTACT_PERSON\"  class=div_input>Factory Contact Person </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_CONTACT_PERSON\" maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_ADDRESS1\"  class=\"div_input\">Registered Address1 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_ADDRESS1\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_ADDRESS2\"  class=\"div_input\">Registered Address2 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_ADDRESS2\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_CITY_CODE\"  class=\"div_input\">Registered City Code </DIV></td>'+ "); 
			 out.println("    '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_CITY_CODE\" maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H4\"),makeRequest(document.Form1.TXT_REGISTERED_CITY_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_REGISTERED_CITY_CODE\" value=\"Help\" onClick=\"help_button_4()\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
			
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_STATUS\"  class=\"div_input\">Registered Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_STATUS\" maxlength=\"4\" size=\"4\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_CORRESPONDENCE_STATUS\"  class=\"div_input\">Correspondence Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CORRESPONDENCE_STATUS\" maxlength=\"4\" size=\"4\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_TEL_NO\"  class=\"div_input\">Factory Telephone No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_TEL_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_FAX_NO\"  class=\"div_input\">Factory Fax No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_F_FAX_NO\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_EMAIL\"  class=\"div_input\">Factory Email </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_EMAIL\" maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_ISSUED_SHARE_CAPITAL\"  class=\"div_input\">Issued Share Capital </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_ISSUED_SHARE_CAPITAL\" maxlength=\"22\" size=\"22\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DATE_OF_INCORPORATION\"  class=\"div_input\">Date Of Incorporation </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_NO\"  class=div_input>Vat Reg No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_NO\" maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_DATE\"  class=\"div_input\">Vat Reg Date </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_DATE\" maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>';  "); 
			
				
				
				
				
			 out.println("   }");	
				
				
				
				out.println("function cor_disable() {");
				
				out.println("alert('cor');");
				
				out.println("m1='TXT_ADDRESS1';");				
				out.println("document.Form1.elements[m1].disabled=true;");
				out.println("m2='TXT_ADDRESS2';");
				out.println("document.Form1.elements[m2].disabled=true;");
				out.println("m3='TXT_CITY_CODE';");
				out.println("document.Form1.elements[m3].disabled=true;");
				out.println("m4='TXT_TEL_NO';");
				out.println("document.Form1.elements[m4].disabled=true;");
				out.println("m5='TXT_FAX_NO';");
				out.println("document.Form1.elements[m5].disabled=true;");
				out.println("m6='TXT_EMAIL';");
				out.println("document.Form1.elements[m6].disabled=true;");
				out.println("m7='TXT_NATIONALITY';");
				out.println("document.Form1.elements[m7].disabled=true;");
				out.println("m8='TXT_GENDER';");
				out.println("document.Form1.elements[m8].disabled=true;");
				out.println("m9='TXT_ADDRESS2';");
				out.println("document.Form1.elements[m9].disabled=true;");
				out.println("m10='TXT_CITY_CODE';");
				out.println("document.Form1.elements[m10].disabled=true;");
				out.println("m11='TXT_REFERENCE';");
				out.println("document.Form1.elements[m11].disabled=true;");
				out.println("m12='TXT_TEL_NO';");
				out.println("document.Form1.elements[m12].disabled=true;");
				out.println("m13='TXT_FAX_NO';");
				out.println("document.Form1.elements[m13].disabled=true;");
				out.println("m14='TXT_EMAIL';");
				out.println("document.Form1.elements[m14].disabled=true;");
				out.println("m15='TXT_OFFICE_TEL_NO';");
				/*out.println("document.Form1.elements[m15].disabled=true;");			
				out.println("m16='TXT_MOBILE_NO';");
				out.println("document.Form1.elements[m16].disabled=true;");
				out.println("m17='TXT_CAT_TYPE_CODE';");
				out.println("document.Form1.elements[m17].disabled=true;");
				out.println("m18='TXT_BUSINESS_CERTIFICATE_NO';");
				out.println("document.Form1.elements[m18].disabled=true;");
				out.println("m19='TXT_KEY_DECISION_MAKER';");
				out.println("document.Form1.elements[m19].disabled=true;");
				out.println("m21='TXT_DESIGNATION';");
				out.println("document.Form1.elements[m21].disabled=true;");
				out.println("m22='TXT_DIRECT_TEL_NO';");
				out.println("document.Form1.elements[m22].disabled=true;");
				out.println("m23='TXT_CONTACT_FOR_PAYMENT';");
				out.println("document.Form1.elements[m23].disabled=true;");
				out.println("m24='TXT_DESIGNATION_PAYMENT';");
				out.println("document.Form1.elements[m24].disabled=true;");
				out.println("m25='TXT_FACTORY_ADDRESS1';");
				out.println("document.Form1.elements[m25].disabled=true;");
				out.println("m26='TXT_VAT_REG_NO';");
				out.println("document.Form1.elements[m26].disabled=true;");
				out.println("m27='TXT_VAT_REG_DATE';");
				out.println("document.Form1.elements[m27].disabled=true;");
				out.println("m28='TXT_TITLE';");
				out.println("document.Form1.elements[m28].disabled=true;");
				out.println("m29='TXT_RESIDENTIAL_STATUS';");
				out.println("document.Form1.elements[m29].disabled=true;");
				out.println("m30='TXT_DURATION_AT_YEARS';");
				out.println("document.Form1.elements[m30].disabled=true;");
				out.println("m31='TXT_DURATION_AT_MONTHS';");
				out.println("document.Form1.elements[m31].disabled=true;");*/
				
				
				
				 out.println("   }");
				
				
				
				
				
				
				
				
				
				
				
			out.println("function corElement_Assing() {");
			
			out.println("alert('corElementAssin');");
			
			 out.println(" e_mode.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS1\"  class=\"div_input\" > Address1 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_ADDRESS1\" value='+oBj.valout[7]+' maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS2\"  class=\"div_input\" > Address2 </DIV></td>' + ");
			 out.println("   '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_ADDRESS2\" value='+oBj.valout[8]+' maxlength=\"10\" size=\"10\">'+"); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=\"div_input\" >City Code </DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_CITY_CODE\" value='+oBj.valout[9]+' maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H2\"),makeRequest(document.Form1.TXT_CITY_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CITY_CODE\" value=\"Help\" onClick=\"help_button_2()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>' + "); 
							
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_TEL_NO\"  class=\"div_input\">Telephone No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_TEL_NO\" value='+oBj.valout[11]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_FAX_NO\" class=\"div_input\">Fax No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FAX_NO\" value='+oBj.valout[12]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
			
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_EMAIL\"  class=\"div_input\">Email </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_EMAIL\" value='+oBj.valout[13]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_OFFICE_TEL_NO\"  class=\"div_input\">Office Telephone No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_OFFICE_TEL_NO\" value='+oBj.valout[14]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_MOBILE_NO\"  class=\"div_input\">Mobile No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_MOBILE_NO\" value='+oBj.valout[15]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+ ");
				
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_CAT_TYPE_CODE\"  class=\"div_input\">Cat Type Code *</DIV></td>'+ "); 
			 out.println("   '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CAT_TYPE_CODE\" value='+oBj.valout[16]+' maxlength=\"10\" size=\"10\"onblur=assign_help_status(\"H3\"),makeRequest(document.Form1.TXT_CAT_TYPE_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CAT_TYPE_CODE\" value=\"Help\" onClick=\"help_button_3()\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  ");  
			
			 //out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_NIC_NO\"  class=\"div_input\">NIC No </DIV></td>'+ "); 
			 //out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_NIC_NO\" value='+oBj.valout[17]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 //out.println("   '<td width=\"*%\"></td></tr></table>'+  "); 
								
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><tr><td width=\"30%\" ><DIV id=\"DIV_TXT_BUSINESS_CERTIFICATE_NO\"  class=\"div_input\">Business Certificate No </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_BUSINESS_CERTIFICATE_NO\" value='+oBj.valout[18]+' maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+ "); 
		
			 out.println("   '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_KEY_DECISION_MAKER\" class=\"div_input\">Key Decision Maker </DIV></td>'+ "); 
			 out.println("   '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_KEY_DECISION_MAKER\" value='+oBj.valout[19]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("   '<td width=\"*%\"></td></tr></table>'+  "); 

		   out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION\"  class=\"div_input\">Designation </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION\" value='+oBj.valout[20]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
		 
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DIRECT_TEL_NO\"  class=\"div_input\">Direct Telephone No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DIRECT_TEL_NO\" value='+oBj.valout[21]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_CONTACT_FOR_PAYMENT\"  class=\"div_input\">Contact For Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CONTACT_FOR_PAYMENT\" value='+oBj.valout[22]+' maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	    
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DESIGNATION_PAYMENT\"  class=\"div_input\">Designation Payment </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DESIGNATION_PAYMENT\" value='+oBj.valout[23]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_ADDRESS1\"  class=\"div_input\">Factory Address1 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_ADDRESS1\" value='+oBj.valout[24]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_ADDRESS2\"  class=\"div_input\">Factory Address2 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_ADDRESS2\" value='+oBj.valout[25]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
			
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_FACTORY_STATUS\"  class=div_input>Factory Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_FACTORY_STATUS\" value='+oBj.valout[26]+' maxlength=\"4\" size=\"4\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
						
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_CONTACT_PERSON\"  class=div_input>Factory Contact Person </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_CONTACT_PERSON\" value='+oBj.valout[27]+' maxlength=\"100\" size=\"100\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_ADDRESS1\"  class=\"div_input\">Registered Address1 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_ADDRESS1\" value='+oBj.valout[28]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_ADDRESS2\"  class=\"div_input\">Registered Address2 </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_ADDRESS2\" value='+oBj.valout[29]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_CITY_CODE\"  class=\"div_input\">Registered City Code </DIV></td>'+ "); 
			 out.println("    '<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_CITY_CODE\" value='+oBj.valout[30]+' maxlength=\"10\" size=\"10\" onblur=assign_help_status(\"H4\"),makeRequest(document.Form1.TXT_REGISTERED_CITY_CODE)> <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_REGISTERED_CITY_CODE\" value=\"Help\" onClick=\"help_button_4()\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
			
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_REGISTERED_STATUS\"  class=\"div_input\">Registered Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_REGISTERED_STATUS\" value='+oBj.valout[31]+' maxlength=\"4\" size=\"4\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_CORRESPONDENCE_STATUS\"  class=\"div_input\">Correspondence Status </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_CORRESPONDENCE_STATUS\" value='+oBj.valout[32]+' maxlength=\"4\" size=\"4\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  ");
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_TEL_NO\"  class=\"div_input\">Factory Telephone No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_TEL_NO\" value='+oBj.valout[33]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_FAX_NO\"  class=\"div_input\">Factory Fax No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\"><input class=\"txt_input\" type=\"text\" name=\"TXT_F_FAX_NO\" value='+oBj.valout[34]+' maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_F_EMAIL\"  class=\"div_input\">Factory Email </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_F_EMAIL\" value='+oBj.valout[35]+' maxlength=\"50\" size=\"50\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_ISSUED_SHARE_CAPITAL\"  class=\"div_input\">Issued Share Capital </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_ISSUED_SHARE_CAPITAL\" value='+oBj.valout[36]+'  maxlength=\"22\" size=\"22\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
	
			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_DATE_OF_INCORPORATION\"  class=\"div_input\">Date Of Incorporation </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_DATE_OF_INCORPORATION\" value='+oBj.valout[37]+'  maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_NO\"  class=div_input>Vat Reg No </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_NO\" value='+oBj.valout[38]+'  maxlength=\"15\" size=\"15\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>'+  "); 
		

			 out.println("    '<table align=\"center\" width=\"100%\" class=\"table\"><td width=\"30%\" ><DIV id=\"DIV_TXT_VAT_REG_DATE\"  class=\"div_input\">Vat Reg Date </DIV></td>'+ "); 
			 out.println("    '<td width=\"40%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_VAT_REG_DATE\" value='+oBj.valout[39]+'  maxlength=\"10\" size=\"10\"></td>'+ "); 
			 out.println("    '<td width=\"*%\"></td></tr></table>';  "); 
			
			out.println("cor_disable();");	
				
			 out.println("   }");		
	
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),indElement()\">"); 
			out.println("<FORM NAME='Form1' method='post'>");
			
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Client Creation </td>"); 
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
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H9'),makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			
			out.println("<td width='30%' >Client Type *</td>");
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_CLIENT_TYPE' maxlength='1' size='1' onChange=\"\">");
			out.println("<option value='A'selected>Applicant</option>");
			out.println("<option value='C' >Co-Applicant</option>");
			out.println("<option value='B' > Both </option>");
			out.println("<option value='G' > Garenters </option>");
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			


			out.println("<td width='30%' ><DIV id='DIV_TXT_FULL_NAME'  class=div_input>Full Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FULL_NAME' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_BUSINESS_SUB_SECTOR'  class=div_input>Business Sub Sector </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BUSINESS_SUB_SECTOR' maxlength='10' size='10' onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_BUSINESS_SUB_SECTOR)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_BUSINESS_SUB_SECTOR' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			
			out.println("<td width='30%' >Client Category *</td>");
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_CLIENT_CATEGORY' maxlength='1' size='1' onChange=\"select_Inner(TXT_CLIENT_CATEGORY.value) \">");
			//out.println("<option value=''selected></option>");
			out.println("<option value='IND'selected>Individual</option>");
			out.println("<option value='COR' >Corperate </option>");
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>"); 
			
	
			
			out.println("</table>");
			
			
		 out.println("<table align='center' width='100%' class='table'>"); 
		 out.println("<tr>" );
			//<TD STYLE="{color: white ;font: 10pt Helvetica;text-align:right;}" WIDTH="100%" >
		 out.println("<td width=\"100%\"><DIV ID=e_mode></DIV>");				
		 out.println("</tr>");
     out.println("</table>");









			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
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
