
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - CLIENT BLACKLIST
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_client_backlisting extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Client Blacklisting</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			
			//out.println("if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			//out.println("alert('Record already exsist');");
			//out.println("new_window();");
			//out.println("}");
			/*out.println("if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"BLACKLIST\" && document.Form1.TXT_CLIENT_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
		//	out.println("alert('Selected Client code is incorrect,use help...!')");
			//out.println("alert('t2')");
			//out.println("fill_data1()");
			out.println("help_update();");
			out.println("}");*/
	
		out.println("if(data_vec.length==0 && document.Form1.TXT_CLIENT_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
		//	out.println("alert('Selected Client code is incorrect,use help...!')");
			//out.println("alert('t2')");
			//out.println("fill_data1()");
			out.println("help_update();");
			out.println("}");
	
			/*out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"BLACKLIST\" && document.Form1.TXT_CLIENT_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("fill_data(data_vec)");
			//out.println("alert('t')");
			out.println("}");
			out.println("}");
			*/
			
			out.println("else if(data_vec.length>0 && document.Form1.TXT_CLIENT_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("fill_data(data_vec)");
			//out.println("alert('t')");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("if (document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_backlisting&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			//out.println("window.open(m_url)");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

	
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println("if(document.Form1.SCREEN_NAME.value==\"BLACKLIST\"||document.Form1.SCREEN_NAME.value==\"TEMPB\"||document.Form1.SCREEN_NAME.value==\"DACT\" ){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"RACT\" ){");
			out.println("document.Form1.hid_st.value='B';");
			out.println("}");
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
			/*
			out.println("else if(document.Form1.TXT_CAT_TYPE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CAT_TYPE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			*/
			out.println("else if(document.Form1.TXT_ACTIVE_STATUS.value==\"\"){  "); 
			out.println("DIV_TXT_ACTIVE_STATUS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_client_backlisting';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
				out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} ");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_backlisting';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_backlisting';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_client_backlisting\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Client Blacklisting - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Client Blacklisting - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			//out.println("if(m_val==\"TEMPB\"){"); 
			//out.println("alert('fhhh')");
			//out.println("new_window();"); 
			//out.println("}");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_CLIENT_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_FULL_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_SURNAME.disabled=true;"); 
			out.println("document.Form1.TXT_INITIALS.disabled=true;"); 
			out.println("document.Form1.TXT_OTHER_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NIC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PASSPORT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MARITAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_BIRTH.disabled=true;"); 
			out.println("document.Form1.TXT_NATIONALITY.disabled=true;"); 
			out.println("document.Form1.TXT_GENDER.disabled=true;"); 
			out.println("document.Form1.TXT_BUSINESS_SUB_SECTOR.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CATEGORY.disabled=true;"); 
			out.println("document.Form1.TXT_CAT_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REFERENCE.disabled=true;"); 
			out.println("document.Form1.TXT_ACTIVE_STATUS.disabled=true;"); 
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
			out.println("document.Form1.TXT_RESIDENTIAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_YEARS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_MONTHS.disabled=true;"); 

//-------------------
			//out.println("document.Form1.BUT_NATIONALITY.disabled=true;");
			//out.println("document.Form1.BUT_BUSINESS_SUB_SECTOR.disabled=true;");
			//out.println("document.Form1.BUT_CLIENT_CATEGORY.disabled=true;");
			//out.println("document.Form1.BUT_CITY_CODE.disabled=true;");
			//out.println("document.Form1.BUT_REGISTERED_CITY_CODE.disabled=true;");

//---------------------


			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"BLACKLIST\"){");
			//out.println("alert('hh')");
			out.println("document.Form1.hid_status.value=\"Blacklist\";"); 
			out.println("document.Form1.hid_save.value=\"Blacklist\";"); 
			out.println("}else if(m_val==\"EDIT\"){");
			
			//out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			//out.println("enable()"); 
			
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Blacklist\";");  
			out.println("document.Form1.hid_save.value=\"Blacklist\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save.value=\"Activate\";");
			out.println("}else if(m_val==\"TEMPB\"){");  
			out.println("document.Form1.hid_status.value=\"TempBlacklist\";"); 
			out.println("document.Form1.hid_save.value=\"Temp Blacklist\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}");
			
			out.println("function fill_data(){");
			/*out.println("document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
			out.println("document.Form1.TXT_ACTIVE_STATUS.value=data_vec[1];");
			out.println("document.Form1.TXT_CLIENT_TYPE.value=data_vec[2];"); 
			
			
			out.println("document.Form1.TXT_FULL_NAME.value=data_vec[3];"); 
			out.println("document.Form1.TXT_TITLE.value=data_vec[4];"); 
			out.println("document.Form1.TXT_FIRST_NAME.value=data_vec[5];"); 
			out.println("document.Form1.TXT_SURNAME.value=data_vec[6];"); 
			out.println("document.Form1.TXT_INITIALS.value=data_vec[7];"); 
			out.println("document.Form1.TXT_OTHER_NAME.value=data_vec[8];"); 
			out.println("document.Form1.TXT_TEL_NO.value=data_vec[9];"); 
			out.println("document.Form1.TXT_FAX_NO.value=data_vec[10];"); 
			out.println("document.Form1.TXT_EMAIL.value=data_vec[11];"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.value=data_vec[12];"); 
			out.println("document.Form1.TXT_MOBILE_NO.value=data_vec[13];"); 
			out.println("document.Form1.TXT_ADDRESS1.value=data_vec[14];"); 
			out.println("document.Form1.TXT_ADDRESS2.value=data_vec[15];"); 
			out.println("document.Form1.TXT_CITY_CODE.value=data_vec[16];"); 
			
			//out.println("document.Form1.BUT_CITY_CODE.value=data_vec[17];");
			
			out.println("document.Form1.TXT_NIC_NO.value=data_vec[17];"); 
			out.println("document.Form1.TXT_PASSPORT_NO.value=data_vec[18];"); 
			out.println("document.Form1.TXT_MARITAL_STATUS.value=data_vec[19];"); 
			out.println("document.Form1.TXT_DATE_OF_BIRTH.value=data_vec[20];"); 
			
			out.println("document.Form1.TXT_NATIONALITY.value=data_vec[21];"); 
			//out.println("document.Form1.BUT_NATIONALITY.value=data_vec[22];"); 
			
			out.println("document.Form1.TXT_GENDER.value=data_vec[22];"); 
			
			out.println("document.Form1.TXT_BUSINESS_SUB_SECTOR.value=data_vec[23];"); 
			//out.println("document.Form1.BUT_BUSINESS_SUB_SECTOR.value=data_vec[26];");
			
			out.println("document.Form1.TXT_CLIENT_CATEGORY.value=data_vec[24];"); 
			//out.println("document.Form1.BUT_CLIENT_CATEGORY.value=data_vec[];"); 
			
			out.println("document.Form1.TXT_CAT_TYPE_CODE.value=data_vec[25];"); 
			out.println("document.Form1.TXT_REFERENCE.value=data_vec[26];"); 
			//out.println("document.Form1.TXT_ACTIVE_STATUS.value=data_vec[30];"); 
			out.println("document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=data_vec[27];"); 
			out.println("document.Form1.TXT_KEY_DECISION_MAKER.value=data_vec[28];"); 
			out.println("document.Form1.TXT_DESIGNATION.value=data_vec[29];"); 
			out.println("document.Form1.TXT_DIRECT_TEL_NO.value=data_vec[30];"); 
			out.println("document.Form1.TXT_CONTACT_FOR_PAYMENT.value=data_vec[31];"); 
			out.println("document.Form1.TXT_DESIGNATION_PAYMENT.value=data_vec[32];"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS1.value=data_vec[33];"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS2.value=data_vec[34];"); 
			out.println("document.Form1.TXT_FACTORY_STATUS.value=data_vec[35];"); 
			out.println("document.Form1.TXT_F_CONTACT_PERSON.value=data_vec[36];"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS1.value=data_vec[37];"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS2.value=data_vec[38];");
			
			out.println("document.Form1.TXT_REGISTERED_CITY_CODE.value=data_vec[39];"); 
			//out.println("document.Form1.BUT_REGISTERED_CITY_CODE.value=data_vec[43];"); 
			
			out.println("document.Form1.TXT_REGISTERED_STATUS.value=data_vec[40];"); 
			out.println("document.Form1.TXT_CORRESPONDENCE_STATUS.value=data_vec[41];"); 
			out.println("document.Form1.TXT_F_TEL_NO.value=data_vec[42];"); 
			out.println("document.Form1.TXT_F_FAX_NO.value=data_vec[43];"); 
			out.println("document.Form1.TXT_F_EMAIL.value=data_vec[44];"); 
			out.println("document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=data_vec[45];"); 
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION.value=data_vec[46];"); 
			out.println("document.Form1.TXT_VAT_REG_NO.value=data_vec[47];"); 
			out.println("document.Form1.TXT_VAT_REG_DATE.value=data_vec[48];"); 
			out.println("document.Form1.TXT_RESIDENTIAL_STATUS.value=data_vec[49];"); 
			out.println("document.Form1.TXT_DURATION_AT_YEARS.value=data_vec[50];"); 
			out.println("document.Form1.TXT_DURATION_AT_MONTHS.value=data_vec[51];");*/
			
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
			out.println(" if(data_vec[1]==''||data_vec[1]=='null'){");
			out.println("    document.Form1.TXT_ACTIVE_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ACTIVE_STATUS.value=data_vec[1];"); 
			out.println(" }");
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_FULL_NAME.value=data_vec[3];"); 
			out.println(" if(data_vec[4]==''||data_vec[4]=='null'){");
			out.println("    document.Form1.TXT_TITLE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_TITLE.value=data_vec[4];"); 
			out.println(" }");
			
			out.println(" if(data_vec[5]==''||data_vec[5]=='null'){");
			out.println("    document.Form1.TXT_FIRST_NAME.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FIRST_NAME.value=data_vec[5];"); 
			out.println(" }");
					
			
			out.println(" if(data_vec[6]==''||data_vec[6]=='null'){");
			out.println("    document.Form1.TXT_SURNAME.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_SURNAME.value=data_vec[6];");
			out.println(" }");

			out.println(" if(data_vec[7]==''||data_vec[7]=='null'){");
			out.println("    document.Form1.TXT_INITIALS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_INITIALS.value=data_vec[7];");
			out.println(" }");
			
					
			out.println(" if(data_vec[8]==''||data_vec[8]=='null'){");
			out.println("    document.Form1.TXT_OTHER_NAME.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_OTHER_NAME.value=data_vec[8];"); 
			out.println(" }");

			out.println(" if(data_vec[9]==''||data_vec[9]=='null'){");
			out.println("    document.Form1.TXT_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");	
			out.println("    document.Form1.TXT_TEL_NO.value=data_vec[9];"); 
			out.println(" }");

			out.println(" if(data_vec[10]==''||data_vec[10]=='null'){");
			out.println("    document.Form1.TXT_FAX_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[10];"); 
			out.println(" }");

			out.println(" if(data_vec[11]==''||data_vec[11]=='null'){");
			out.println("    document.Form1.TXT_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_EMAIL.value=data_vec[11];");
			out.println(" }");

			out.println(" if(data_vec[12]==''||data_vec[12]=='null'){");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=data_vec[12];");
			out.println(" }");

			out.println(" if(data_vec[13]==''||data_vec[13]=='null'){");
			out.println("    document.Form1.TXT_MOBILE_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_MOBILE_NO.value=data_vec[13];"); 
			out.println(" }");

				
			out.println(" if(data_vec[14]==''||data_vec[14]=='null'){");
			//	out.println("alert(data_vec[15])");
			out.println("    document.Form1.TXT_ADDRESS1.value='';"); 
			out.println(" }");
			out.println(" else {");
		
			out.println("    document.Form1.TXT_ADDRESS1.value=data_vec[14];");
			out.println(" }");

			out.println(" if(data_vec[15]==''||data_vec[15]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2.value=data_vec[15];"); 
			out.println(" }");
	
					
			out.println(" if(data_vec[16]==''||data_vec[16]=='null'){");
			out.println("    document.Form1.TXT_CITY_CODE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[16];"); 
			out.println(" }");

			
			out.println(" if(data_vec[17]==''||data_vec[17]=='null'){");
			out.println("    document.Form1.TXT_NIC_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_NIC_NO.value=data_vec[17];"); 
			out.println(" }");
			
			out.println(" if(data_vec[18]==''||data_vec[18]=='null'){");
			out.println("    document.Form1.TXT_PASSPORT_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_PASSPORT_NO.value=data_vec[18];"); 
			out.println(" }");
			
			out.println(" if(data_vec[19]==''||data_vec[19]=='null'){");
			out.println("    document.Form1.TXT_MARITAL_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_MARITAL_STATUS.value=data_vec[19];"); 
			out.println(" }");
			
			out.println(" if(data_vec[20]==''||data_vec[20]=='null'){");
			out.println("    document.Form1.TXT_MARITAL_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DATE_OF_BIRTH.value=data_vec[20];"); 
			out.println(" }");
	
			out.println(" if(data_vec[21]==''||data_vec[21]=='null'){");
			out.println("    document.Form1.TXT_NATIONALITY.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_NATIONALITY.value=data_vec[21];"); 
			out.println(" }");
		
			out.println(" if(data_vec[22]==''||data_vec[22]=='null'){");
			out.println("    document.Form1.TXT_GENDER.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GENDER.value=data_vec[22];"); 
			out.println(" }");
		
			out.println(" if(data_vec[23]==''||data_vec[23]=='null'){");
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=data_vec[23];"); 
			out.println(" }");
		
			//out.println(" if(document.Form1.TXT_CLIENT_CATEGORY.value==''){");
			//out.println("    document.Form1.TXT_CLIENT_CATEGORY.value='';"); 
			//out.println(" }");
			//out.println(" else {");
			out.println("    document.Form1.TXT_CLIENT_CATEGORY.value=data_vec[24];"); 
			//out.println(" }");
			
			//out.println(" if(document.Form1.TXT_CAT_TYPE_CODE.value==''){");
			//out.println("    document.Form1.TXT_CAT_TYPE_CODE.value='';"); 
			//out.println(" }");
			//out.println(" else {");
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=data_vec[25];"); 
			//out.println(" }");
			
			out.println(" if(data_vec[26]==''||data_vec[26]=='null'){");
			out.println("    document.Form1.TXT_REFERENCE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REFERENCE.value=data_vec[26];");
			out.println(" }");

			out.println(" if(data_vec[27]==''||data_vec[27]=='null'){");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=data_vec[27];"); 
			out.println(" }");

			out.println(" if(data_vec[28]==''||data_vec[28]=='null'){");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=data_vec[28];");
			out.println(" }");
				
			out.println(" if(data_vec[29]==''||data_vec[29]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION.value=data_vec[29];"); 
			out.println(" }");

			out.println(" if(data_vec[30]==''||data_vec[30]=='null'){");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=data_vec[30];"); 
			out.println(" }");

			out.println(" if(data_vec[31]==''||data_vec[31]=='null'){");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=data_vec[31];"); 
			out.println(" }");
	
			out.println(" if(data_vec[32]==''||data_vec[32]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value=data_vec[32];"); 
			out.println(" }");

			out.println(" if(data_vec[33]==''||data_vec[33]=='null'){");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value=data_vec[33];"); 
			out.println(" }");

			out.println(" if(data_vec[34]==''||data_vec[34]=='null'){");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value=data_vec[34];"); 
			out.println(" }");

			out.println(" if(data_vec[35]==''||data_vec[35]=='null'){");
			out.println("    document.Form1.TXT_FACTORY_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=data_vec[35];"); 
			out.println(" }");

			out.println(" if(data_vec[36]==''||data_vec[36]=='null'){");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=data_vec[36];"); 
			out.println(" }");

			out.println(" if(data_vec[37]==''||data_vec[37]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value=data_vec[37];"); 
			out.println(" }");

			out.println(" if(data_vec[38]==''||data_vec[38]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value=data_vec[38];"); 
			out.println(" }");
		
			out.println(" if(data_vec[39]==''||data_vec[39]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=data_vec[39];"); 
			out.println(" }");

			out.println(" if(data_vec[40]==''||data_vec[40]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value=data_vec[40];"); 
			out.println(" }");
			
			out.println(" if(data_vec[41]==''||data_vec[41]=='null'){");
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value=data_vec[41];"); 
			out.println(" }");

			out.println(" if(data_vec[42]==''||data_vec[42]=='null'){");
			out.println("    document.Form1.TXT_F_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_TEL_NO.value=data_vec[42];"); 
			out.println(" }");

			out.println(" if(data_vec[43]==''||data_vec[43]=='null'){");
			out.println("    document.Form1.TXT_F_FAX_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_FAX_NO.value=data_vec[43];"); 
			out.println(" }");

			out.println(" if(data_vec[44]==''||data_vec[44]=='null'){");
			out.println("    document.Form1.TXT_F_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_EMAIL.value=data_vec[44];"); 
			out.println(" }");
			
			out.println(" if(data_vec[45]==''||data_vec[45]=='null'){");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=data_vec[45];"); 
			out.println(" }");
			
			out.println(" if(data_vec[46]==''||data_vec[46]=='null'){");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value=data_vec[46];"); 
			out.println(" }");

					
			out.println(" if(data_vec[47]==''||data_vec[47]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=data_vec[47];"); 
			out.println(" }");

			out.println(" if(data_vec[48]==''||data_vec[48]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_DATE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_VAT_REG_DATE.value=data_vec[48];"); 
			out.println(" }");

			out.println(" if(data_vec[49]==''||data_vec[49]=='null'){");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value=data_vec[49];"); 
			out.println(" }");

			out.println(" if(data_vec[50]==''||data_vec[50]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value=data_vec[50];"); 
			out.println(" }");

			out.println(" if(data_vec[51]==''||data_vec[51]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value=data_vec[51];"); 
			out.println(" }");
		
			
			out.println("}"); 
			//out.println("}"); 
			
			out.println("function fill_data1(){");
			out.println("document.Form1.TXT_CLIENT_CODE.value='';"); 
			out.println("document.Form1.TXT_CLIENT_CODE.focus();"); 
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("document.Form1.TXT_ACTIVE_STATUS.value='';"); 
			out.println("document.Form1.TXT_CLIENT_TYPE.value='';"); 
			out.println("document.Form1.TXT_FULL_NAME.value='';"); 
			out.println("document.Form1.TXT_TITLE.value='';"); 
			out.println("document.Form1.TXT_FIRST_NAME.value='';"); 
			out.println("document.Form1.TXT_SURNAME.value='';"); 
			out.println("document.Form1.TXT_INITIALS.value='';"); 
			out.println("document.Form1.TXT_OTHER_NAME.value='';"); 
			out.println("document.Form1.TXT_TEL_NO.value='';"); 
			out.println("document.Form1.TXT_FAX_NO.value='';"); 
			out.println("document.Form1.TXT_EMAIL.value='';"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.value='';"); 
			out.println("document.Form1.TXT_MOBILE_NO.value='';"); 
			out.println("document.Form1.TXT_ADDRESS1.value='';"); 
			out.println("document.Form1.TXT_ADDRESS2.value='';"); 
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 
			
			//out.println("document.Form1.BUT_CITY_CODE.value='';");
			
			out.println("document.Form1.TXT_NIC_NO.value='';"); 
			out.println("document.Form1.TXT_PASSPORT_NO.value='';"); 
			out.println("document.Form1.TXT_MARITAL_STATUS.value='';"); 
			out.println("document.Form1.TXT_DATE_OF_BIRTH.value='';"); 
			
			out.println("document.Form1.TXT_NATIONALITY.value='';"); 
			//out.println("document.Form1.BUT_NATIONALITY.value='';"); 
			
			out.println("document.Form1.TXT_GENDER.value='';"); 
			
			out.println("document.Form1.TXT_BUSINESS_SUB_SECTOR.value='';"); 
			//out.println("document.Form1.BUT_BUSINESS_SUB_SECTOR.value='';");
			
			out.println("document.Form1.TXT_CLIENT_CATEGORY.value='';"); 
			//out.println("document.Form1.BUT_CLIENT_CATEGORY.value='';"); 
			
			out.println("document.Form1.TXT_CAT_TYPE_CODE.value='';"); 
			out.println("document.Form1.TXT_REFERENCE.value='';"); 
			
			out.println("document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value='';"); 
			out.println("document.Form1.TXT_KEY_DECISION_MAKER.value='';"); 
			out.println("document.Form1.TXT_DESIGNATION.value='';"); 
			out.println("document.Form1.TXT_DIRECT_TEL_NO.value='';"); 
			out.println("document.Form1.TXT_CONTACT_FOR_PAYMENT.value='';"); 
			out.println("document.Form1.TXT_DESIGNATION_PAYMENT.value='';"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS1.value='';"); 
			out.println("document.Form1.TXT_FACTORY_ADDRESS2.value='';"); 
			out.println("document.Form1.TXT_FACTORY_STATUS.value='';"); 
			out.println("document.Form1.TXT_F_CONTACT_PERSON.value='';"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS1.value='';"); 
			out.println("document.Form1.TXT_REGISTERED_ADDRESS2.value='';");
			
			out.println("document.Form1.TXT_REGISTERED_CITY_CODE.value='';"); 
			//out.println("document.Form1.BUT_REGISTERED_CITY_CODE.value='';"); 
			
			out.println("document.Form1.TXT_REGISTERED_STATUS.value='';"); 
			out.println("document.Form1.TXT_CORRESPONDENCE_STATUS.value='';"); 
			out.println("document.Form1.TXT_F_TEL_NO.value='';"); 
			out.println("document.Form1.TXT_F_FAX_NO.value='';"); 
			out.println("document.Form1.TXT_F_EMAIL.value='';"); 
			out.println("document.Form1.TXT_ISSUED_SHARE_CAPITAL.value='';"); 
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION.value='';"); 
			out.println("document.Form1.TXT_VAT_REG_NO.value='';"); 
			out.println("document.Form1.TXT_VAT_REG_DATE.value='';"); 
			out.println("document.Form1.TXT_RESIDENTIAL_STATUS.value='';"); 
			out.println("document.Form1.TXT_DURATION_AT_YEARS.value='';"); 
			out.println("document.Form1.TXT_DURATION_AT_MONTHS.value='';");

			out.println("}"); 
			
			
			
			
			
			
			/*out.println("function fill_data(){"); 
			out.println("{");
			out.println("alert('R')");
			//out.println("document.Form1.BUT_HELP_MAIN.value=false;"); 
			out.println("document.Form1.TXT_CLIENT_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_FULL_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_SURNAME.disabled=true;"); 
			out.println("document.Form1.TXT_INITIALS.disabled=true;"); 
			out.println("document.Form1.TXT_OTHER_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			
			out.println("document.Form1.BUT_CITY_CODE.disabled=true;");
			
			out.println("document.Form1.TXT_NIC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PASSPORT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MARITAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_BIRTH.disabled=true;"); 
			
			out.println("document.Form1.TXT_NATIONALITY.disabled=true;"); 
			out.println("document.Form1.BUT_NATIONALITY.disabled=true;"); 
			
			out.println("document.Form1.TXT_GENDER.disabled=true;"); 
			
			out.println("document.Form1.TXT_BUSINESS_SUB_SECTOR.disabled=true;"); 
			out.println("document.Form1.BUT_BUSINESS_SUB_SECTOR.disabled=true;");
			
			out.println("document.Form1.TXT_CLIENT_CATEGORY.disabled=true;"); 
			//out.println("document.Form1.BUT_CLIENT_CATEGORY.disabled=true;"); 
			
			out.println("document.Form1.TXT_CAT_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REFERENCE.disabled=true;"); 
			out.println("document.Form1.TXT_ACTIVE_STATUS.disabled=false;"); 
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
			out.println("document.Form1.BUT_REGISTERED_CITY_CODE.disabled=true;"); 
			
			out.println("document.Form1.TXT_REGISTERED_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_CORRESPONDENCE_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_F_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_F_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_F_EMAIL.disabled=true;"); 
			out.println("document.Form1.TXT_ISSUED_SHARE_CAPITAL.disabled=true;"); 
			out.println("document.Form1.TXT_DATE_OF_INCORPORATION.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_RESIDENTIAL_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_YEARS.disabled=true;"); 
			out.println("document.Form1.TXT_DURATION_AT_MONTHS.disabled=true;");

			out.println("}"); 
			out.println("}"); 
*/


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
			out.println("	}		"); 
				out.println("	else{");
			out.println("	clear_data1();");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			//out.println("Close();"); 
			
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      out.println("window.close();");
      out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("fill_data1()");
			out.println("}");
			
			
			
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
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";");
			//out.println("    m_sql = \"m_help_TXT_NATIONALITY_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_NATIONALITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_NATIONALITY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_NATIONALITY.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_SECTOR_CODE_sql\";");
			//out.println("    m_sql = \"m_help_TXT_BUSINESS_SUB_SECTOR_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BUSINESS_SUB_SECTOR.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CATEGORY_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CATEGORY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_CLIENT_CATEGORY.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			
			//out.println("    m_sql = \"m_help_TXT_REGISTERED_CITY_CODE_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_REGISTERED_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"RACT\"){");
			
			//out.println("    document.Form1.hid_a.value=\"A\";"); 
			//out.println("}");		
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"DACT\"){");
			
			//out.println("    document.Form1.hid_a.value=\"B\";"); 
			//out.println("}");	
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"BLACKLIST\" || document.Form1.SCREEN_NAME.value==\"DACT\" || document.Form1.SCREEN_NAME.value==\"TEMPB\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"B@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			out.println(" }");
			//out.println("    HelpBox('1','10','49');"); 
			out.println("    HelpBox('1','10','34');");
			out.println("}"); 

			out.println("function help_update_value_assign_99() {");
			
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[4];"); 
			out.println(" if(oBj.valout[5]==''||oBj.valout[5]=='null'){");
			out.println("    document.Form1.TXT_TITLE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_TITLE.value=oBj.valout[5];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[6]==''||oBj.valout[6]=='null'){");
			out.println("    document.Form1.TXT_FIRST_NAME.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[6];"); 
			out.println(" }");
					
			
			out.println(" if(oBj.valout[7]==''||oBj.valout[7]=='null'){");
			out.println("    document.Form1.TXT_SURNAME.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_SURNAME.value=oBj.valout[7];");
			out.println(" }");

			out.println(" if(oBj.valout[8]==''||oBj.valout[8]=='null'){");
			out.println("    document.Form1.TXT_INITIALS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_INITIALS.value=oBj.valout[8];");
			out.println(" }");
			
					
			out.println(" if(oBj.valout[9]==''||oBj.valout[9]=='null'){");
			out.println("    document.Form1.TXT_OTHER_NAME.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_OTHER_NAME.value=oBj.valout[9];"); 
			out.println(" }");

			out.println(" if(oBj.valout[10]==''||oBj.valout[10]=='null'){");
			out.println("    document.Form1.TXT_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");	
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[10];"); 
			out.println(" }");

			out.println(" if(oBj.valout[11]==''||oBj.valout[11]=='null'){");
			out.println("    document.Form1.TXT_FAX_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FAX_NO.value=oBj.valout[11];"); 
			out.println(" }");

			out.println(" if(oBj.valout[12]==''||oBj.valout[12]=='null'){");
			out.println("    document.Form1.TXT_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_EMAIL.value=oBj.valout[12];");
			out.println(" }");

			out.println(" if(oBj.valout[13]==''||oBj.valout[13]=='null'){");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=oBj.valout[13];");
			out.println(" }");

			out.println(" if(oBj.valout[14]==''||oBj.valout[14]=='null'){");
			out.println("    document.Form1.TXT_MOBILE_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_MOBILE_NO.value=oBj.valout[14];"); 
			out.println(" }");

				
			out.println(" if(oBj.valout[15]==''||oBj.valout[15]=='null'){");
			//	out.println("alert(oBj.valout[15])");
			out.println("    document.Form1.TXT_ADDRESS1.value='';"); 
			out.println(" }");
			out.println(" else {");
		
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[15];");
			out.println(" }");

			out.println(" if(oBj.valout[16]==''||oBj.valout[16]=='null'){");
			out.println("    document.Form1.TXT_ADDRESS2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[16];"); 
			out.println(" }");
	
					
			out.println(" if(oBj.valout[17]==''||oBj.valout[17]=='null'){");
			out.println("    document.Form1.TXT_CITY_CODE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[17];"); 
			out.println(" }");

			
			out.println(" if(oBj.valout[18]==''||oBj.valout[18]=='null'){");
			out.println("    document.Form1.TXT_NIC_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_NIC_NO.value=oBj.valout[18];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[19]==''||oBj.valout[19]=='null'){");
			out.println("    document.Form1.TXT_PASSPORT_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_PASSPORT_NO.value=oBj.valout[19];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[20]==''||oBj.valout[20]=='null'){");
			out.println("    document.Form1.TXT_MARITAL_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_MARITAL_STATUS.value=oBj.valout[20];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[21]==''||oBj.valout[21]=='null'){");
			out.println("    document.Form1.TXT_MARITAL_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DATE_OF_BIRTH.value=oBj.valout[21];"); 
			out.println(" }");
	
			out.println(" if(oBj.valout[22]==''||oBj.valout[22]=='null'){");
			out.println("    document.Form1.TXT_NATIONALITY.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_NATIONALITY.value=oBj.valout[22];"); 
			out.println(" }");
		
			out.println(" if(oBj.valout[23]==''||oBj.valout[23]=='null'){");
			out.println("    document.Form1.TXT_GENDER.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_GENDER.value=oBj.valout[23];"); 
			out.println(" }");
		
			out.println(" if(oBj.valout[24]==''||oBj.valout[24]=='null'){");
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=oBj.valout[24];"); 
			out.println(" }");
		
			//out.println(" if(document.Form1.TXT_CLIENT_CATEGORY.value==''){");
			//out.println("    document.Form1.TXT_CLIENT_CATEGORY.value='';"); 
			//out.println(" }");
			//out.println(" else {");
			out.println("    document.Form1.TXT_CLIENT_CATEGORY.value=oBj.valout[25];"); 
			//out.println(" }");
			
			out.println(" if(oBj.valout[26]=='' || oBj.valout[26]==\"null\"){");
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=oBj.valout[26];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[27]==''||oBj.valout[27]=='null'){");
			out.println("    document.Form1.TXT_REFERENCE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REFERENCE.value=oBj.valout[27];");
			out.println(" }");

			out.println(" if(oBj.valout[28]==''||oBj.valout[28]=='null'){");
			out.println("    document.Form1.TXT_ACTIVE_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ACTIVE_STATUS.value=oBj.valout[28];"); 
			out.println(" }");

			out.println(" if(oBj.valout[29]==''||oBj.valout[29]=='null'){");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=oBj.valout[29];"); 
			out.println(" }");

			out.println(" if(oBj.valout[30]==''||oBj.valout[30]=='null'){");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=oBj.valout[30];");
			out.println(" }");
				
			out.println(" if(oBj.valout[31]==''||oBj.valout[31]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION.value=oBj.valout[31];"); 
			out.println(" }");

			out.println(" if(oBj.valout[32]==''||oBj.valout[32]=='null'){");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=oBj.valout[32];"); 
			out.println(" }");

			out.println(" if(oBj.valout[33]==''||oBj.valout[33]=='null'){");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=oBj.valout[33];"); 
			out.println(" }");
	
			out.println(" if(oBj.valout[34]==''||oBj.valout[34]=='null'){");
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value=oBj.valout[34];"); 
			out.println(" }");

			out.println(" if(oBj.valout[35]==''||oBj.valout[35]=='null'){");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value=oBj.valout[35];"); 
			out.println(" }");

			out.println(" if(oBj.valout[36]==''||oBj.valout[36]=='null'){");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value=oBj.valout[36];"); 
			out.println(" }");

			out.println(" if(oBj.valout[37]==''||oBj.valout[37]=='null'){");
			out.println("    document.Form1.TXT_FACTORY_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=oBj.valout[37];"); 
			out.println(" }");

			out.println(" if(oBj.valout[38]==''||oBj.valout[38]=='null'){");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=oBj.valout[38];"); 
			out.println(" }");

			out.println(" if(oBj.valout[39]==''||oBj.valout[39]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value=oBj.valout[39];"); 
			out.println(" }");

			out.println(" if(oBj.valout[40]==''||oBj.valout[40]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value=oBj.valout[40];"); 
			out.println(" }");
		
			out.println(" if(oBj.valout[41]==''||oBj.valout[41]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[41];"); 
			out.println(" }");

			out.println(" if(oBj.valout[42]==''||oBj.valout[42]=='null'){");
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value=oBj.valout[42];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[43]==''||oBj.valout[43]=='null'){");
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value=oBj.valout[43];"); 
			out.println(" }");

			out.println(" if(oBj.valout[44]==''||oBj.valout[44]=='null'){");
			out.println("    document.Form1.TXT_F_TEL_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_TEL_NO.value=oBj.valout[44];"); 
			out.println(" }");

			out.println(" if(oBj.valout[45]==''||oBj.valout[45]=='null'){");
			out.println("    document.Form1.TXT_F_FAX_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_FAX_NO.value=oBj.valout[45];"); 
			out.println(" }");

			out.println(" if(oBj.valout[46]==''||oBj.valout[46]=='null'){");
			out.println("    document.Form1.TXT_F_EMAIL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_F_EMAIL.value=oBj.valout[46];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[47]==''||oBj.valout[47]=='null'){");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=oBj.valout[47];"); 
			out.println(" }");
			
			out.println(" if(oBj.valout[48]==''||oBj.valout[48]=='null'){");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value=oBj.valout[48];"); 
			out.println(" }");

					
			out.println(" if(oBj.valout[49]==''||oBj.valout[49]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_NO.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_VAT_REG_NO.value=oBj.valout[49];"); 
			out.println(" }");

			out.println(" if(oBj.valout[50]==''||oBj.valout[50]=='null'){");
			out.println("    document.Form1.TXT_VAT_REG_DATE.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_VAT_REG_DATE.value=oBj.valout[50];"); 
			out.println(" }");

			out.println(" if(oBj.valout[51]==''||oBj.valout[51]=='null'){");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value=oBj.valout[51];"); 
			out.println(" }");

			out.println(" if(oBj.valout[52]==''||oBj.valout[52]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value=oBj.valout[52];"); 
			out.println(" }");

			out.println(" if(oBj.valout[53]==''||oBj.valout[53]=='null'){");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value='';"); 
			out.println(" }");
			out.println(" else {");
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value=oBj.valout[53];"); 
			out.println(" }");
		
			

			out.println("}"); 
			
  		//Added by Mahela on 08-05-2007
			out.println("function load_report_blacklist() {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_rpt_blacklists?chksql=LOAD_CLIENT_BLACKLIST_REPORT\";");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			
			out.println("</script>"); 
			//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('Temporary Blacklist')\">"); // commented by udara on 06-11-2013
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('Blacklist'),load_screen_status('DACT')\" >");  // added by udara on 06-11-2013
		    
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='BLACKLIST' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Blacklist\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_a' VALUE=\"\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Temp Blacklist\">");
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Client Blacklisting</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" style='display:none' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Temporary Blacklist\");' onClick='load_screen_status(\"TEMPB\")' value=\"T-Blacklist\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Blacklist\");' onClick='load_screen_status(\"DACT\")' value=\"Blacklist\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Activate\");' onClick='load_screen_status(\"RACT\")' value=\"Activate\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Report\");' onClick='load_report_blacklist()' value=\"Report\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"assig('t1'),makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ACTIVE_STATUS'  class=div_input>Active Status *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACTIVE_STATUS' maxlength='1' size='1'></td>"); 
			//out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_ACTIVE_STATUS' maxlength='1' size='1'>");  
			//out.println("<option value='N'>No</option>");			
			//out.println("<option value='Y' selected>Yes</option>");		
			//out.println("</select>");
			//out.println("</td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_TYPE'  class=div_input>Client Type *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_TYPE' maxlength='1' size='1'></td>"); 
			
			//out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_CLIENT_TYPE' maxlength='1' size='1'>");  
			//out.println("<option value='I' selected>Individual</option>");			
			//out.println("<option value='G'>Group</option>");		
			//out.println("</select>");
			//out.println("</td>");
					
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_FULL_NAME'  class=div_input>Full Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FULL_NAME' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Title </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TITLE' maxlength='5' size='5'></td>"); 
			//out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_TITLE' maxlength='1' size='1'>");  
			//out.println("<option value='1' selected>Mr.</option>");			
			//out.println("<option value='2'>Mrs.</option>");
			//out.println("<option value='3'>Miss</option>");
			//out.println("</select>");
			//out.println("</td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >First Name</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Surname</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SURNAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Initials</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INITIALS' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Other Name</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OTHER_NAME' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Telephone No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Fax No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FAX_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Email</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMAIL' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Office Telephone No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OFFICE_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Mobile No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MOBILE_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Address1</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS1' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Address2</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS2' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >City Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10'  >"); 
			//out.println("<input class='but_input' type='button' name='BUT_CITY_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >NIC No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NIC_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Passport No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PASSPORT_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Marital Status</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MARITAL_STATUS' maxlength='9' size='9'></td>"); 
			//out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_MARITAL_STATUS' maxlength='1' size='1'>");  
			//out.println("<option value='S' selected>Single</option>");			
			//out.println("<option value='M' >Married</option>");		
			//out.println("</select>");
			//out.println("</td>");

			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Date of Birth</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DATE_OF_BIRTH' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >Nationality</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_NATIONALITY' maxlength='10' size='10' >"); 
			//out.println("<input class='but_input' type='button' name='BUT_NATIONALITY' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Gender</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GENDER' maxlength='6' size='6'></td>"); 
			//out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_GENDER' maxlength='1' size='1'>");  
			//out.println("<option value='M' selected>Male</option>");			
			//out.println("<option value='F'>Female</option>");		
			//out.println("</select>");
			//out.println("</td>");

			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Bussiness Sub Sectors</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BUSINESS_SUB_SECTOR' maxlength='10' size='10'>"); 
			//out.println("<input class='but_input' type='button' name='BUT_BUSINESS_SUB_SECTOR' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 


			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CATEGORY'  class=div_input>Client Category*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CATEGORY' maxlength='10' size='10'>"); 
			/*out.println("<input class='but_input' type='button' name='BUT_CLIENT_CATEGORY' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_CLIENT_CATEGORY' maxlength='1' size='1' >");  
			out.println("<option value='C'>Coperate</option>");			
			out.println("<option value='I' selected>Incoperate</option>");		
			out.println("</select>");
			out.println("</td>");*/

			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CAT_TYPE_CODE'  class=div_input>Category Type Code</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CAT_TYPE_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Reference</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REFERENCE' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			/*out.println("<td width='30%' ><DIV id='DIV_TXT_ACTIVE_STATUS'  class=div_input>Active Status *</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACTIVE_STATUS' maxlength='1' size='1'></td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_ACTIVE_STATUS' maxlength='1' size='1'>");  
			out.println("<option value='N'>No</option>");			
			out.println("<option value='Y' selected>Yes</option>");		
			out.println("</select>");
			out.println("</td>");*/

			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' >Business Certification No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BUSINESS_CERTIFICATE_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Key Decision Maker</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_KEY_DECISION_MAKER' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Designation</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESIGNATION' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Direct Telephone No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DIRECT_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Contact for Payment</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CONTACT_FOR_PAYMENT' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Designation Payment</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESIGNATION_PAYMENT' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Factory Address1</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACTORY_ADDRESS1' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Factory Address2</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACTORY_ADDRESS2' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Factory Status</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACTORY_STATUS' maxlength='4' size='4'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Factory Contact Person</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_CONTACT_PERSON' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Registered Address1</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REGISTERED_ADDRESS1' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Registered Address2</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REGISTERED_ADDRESS2' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Registered City Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REGISTERED_CITY_CODE' maxlength='10' size='10' >"); 
			//out.println("<input class='but_input' type='button' name='BUT_REGISTERED_CITY_CODE' value=\"Help\" onClick=\"help_button_6()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Registered Status</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REGISTERED_STATUS' maxlength='4' size='4'></td>"); 
			/*out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_REGISTERED_STATUS' maxlength='1' size='1'>");  
			out.println("<option value='N'>No</option>");			
			out.println("<option value='Y' selected>Yes</option>");		
			out.println("</select>");
			out.println("</td>");*/
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Correspondence Status</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CORRESPONDENCE_STATUS' maxlength='4' size='4'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Factory Telephone No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Factory Fax No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_FAX_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Factory Email</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_EMAIL' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Issued Share Capital</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ISSUED_SHARE_CAPITAL' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Date of Incorporation</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DATE_OF_INCORPORATION' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Vat Registered No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Vat Registered Date</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG_DATE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Residential Status</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Duration at Years</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DURATION_AT_YEARS' maxlength='3' size='22' onchange='format_number(document.Form1.TXT_DURATION_AT_YEARS,3);'></td>"); // Validation added by Chatura Jayawardena
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Duration at Months</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DURATION_AT_MONTHS' maxlength='2' size='2' onchange='format_number(document.Form1.TXT_DURATION_AT_MONTHS,2);'></td>"); // Validation added by Chatura Jayawardena
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

			
					
