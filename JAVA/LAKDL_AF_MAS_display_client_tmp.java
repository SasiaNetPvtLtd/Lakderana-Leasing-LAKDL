
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - CLIENT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_client_tmp extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Client</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("}");
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_client_tmp&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
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
			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_MAS_save_client_tmp';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_client_tmp';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_client_tmp';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_client_tmp\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Client - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Client - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_BA_NATURE_OF_BUSINESS.disabled=true;"); 
			out.println("document.Form1.TXT_BA_PROFESSION.disabled=true;"); 
			out.println("document.Form1.TXT_BA_QUALIFICATIONS.disabled=true;"); 
			out.println("document.Form1.TXT_BA_DESIGNATION.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_REFERENCE.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_RDESIGNATION.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_NO_OF_CHILDREN.disabled=true;"); 
			out.println("document.Form1.TXT_DEPENDENTS.disabled=true;"); 

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
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_TYPE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_BUSINESS_SUB_SECTOR.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CLIENT_CATEGORY.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_REFERENCE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_EMAIL.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_MOBILE_NO.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_CAT_TYPE_CODE.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_NIC_NO.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_BUSINESS_CERTIFICATE_NO.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_KEY_DECISION_MAKER.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_DESIGNATION.value=oBj.valout[20];"); 
			out.println("    document.Form1.TXT_DIRECT_TEL_NO.value=oBj.valout[21];"); 
			out.println("    document.Form1.TXT_CONTACT_FOR_PAYMENT.value=oBj.valout[22];"); 
			out.println("    document.Form1.TXT_DESIGNATION_PAYMENT.value=oBj.valout[23];"); 
			out.println("    document.Form1.TXT_FACTORY_ADDRESS1.value=oBj.valout[24];"); 
			out.println("    document.Form1.TXT_FACTORY_ADDRESS2.value=oBj.valout[25];"); 
			out.println("    document.Form1.TXT_FACTORY_STATUS.value=oBj.valout[26];"); 
			out.println("    document.Form1.TXT_F_CONTACT_PERSON.value=oBj.valout[27];"); 
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS1.value=oBj.valout[28];"); 
			out.println("    document.Form1.TXT_REGISTERED_ADDRESS2.value=oBj.valout[29];"); 
			out.println("    document.Form1.TXT_REGISTERED_CITY_CODE.value=oBj.valout[30];"); 
			out.println("    document.Form1.TXT_REGISTERED_STATUS.value=oBj.valout[31];"); 
			out.println("    document.Form1.TXT_CORRESPONDENCE_STATUS.value=oBj.valout[32];"); 
			out.println("    document.Form1.TXT_F_TEL_NO.value=oBj.valout[33];"); 
			out.println("    document.Form1.TXT_F_FAX_NO.value=oBj.valout[34];"); 
			out.println("    document.Form1.TXT_F_EMAIL.value=oBj.valout[35];"); 
			out.println("    document.Form1.TXT_ISSUED_SHARE_CAPITAL.value=oBj.valout[36];"); 
			out.println("    document.Form1.TXT_DATE_OF_INCORPORATION.value=oBj.valout[37];"); 
			out.println("    document.Form1.TXT_VAT_REG_NO.value=oBj.valout[38];"); 
			out.println("    document.Form1.TXT_VAT_REG_DATE.value=oBj.valout[39];"); 
			out.println("    document.Form1.TXT_TITLE.value=oBj.valout[40];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[41];"); 
			out.println("    document.Form1.TXT_SURNAME.value=oBj.valout[42];"); 
			out.println("    document.Form1.TXT_INITIALS.value=oBj.valout[43];"); 
			out.println("    document.Form1.TXT_OTHER_NAME.value=oBj.valout[44];"); 
			out.println("    document.Form1.TXT_RESIDENTIAL_STATUS.value=oBj.valout[45];"); 
			out.println("    document.Form1.TXT_DURATION_AT_YEARS.value=oBj.valout[46];"); 
			out.println("    document.Form1.TXT_DURATION_AT_MONTHS.value=oBj.valout[47];"); 
			out.println("    document.Form1.TXT_PASSPORT_NO.value=oBj.valout[48];"); 
			out.println("    document.Form1.TXT_MARITAL_STATUS.value=oBj.valout[49];"); 
			out.println("    document.Form1.TXT_DATE_OF_BIRTH.value=oBj.valout[50];"); 
			out.println("    document.Form1.TXT_NATIONALITY.value=oBj.valout[51];"); 
			out.println("    document.Form1.TXT_GENDER.value=oBj.valout[52];"); 
			out.println("    document.Form1.TXT_BA_NATURE_OF_BUSINESS.value=oBj.valout[53];"); 
			out.println("    document.Form1.TXT_BA_PROFESSION.value=oBj.valout[54];"); 
			out.println("    document.Form1.TXT_BA_QUALIFICATIONS.value=oBj.valout[55];"); 
			out.println("    document.Form1.TXT_BA_DESIGNATION.value=oBj.valout[56];"); 
			out.println("    document.Form1.TXT_EMP_NAME.value=oBj.valout[57];"); 
			out.println("    document.Form1.TXT_EMP_ADDRESS1.value=oBj.valout[58];"); 
			out.println("    document.Form1.TXT_EMP_ADDRESS2.value=oBj.valout[59];"); 
			out.println("    document.Form1.TXT_EMP_REFERENCE.value=oBj.valout[60];"); 
			out.println("    document.Form1.TXT_EMP_RDESIGNATION.value=oBj.valout[61];"); 
			out.println("    document.Form1.TXT_EMP_TEL_NO.value=oBj.valout[62];"); 
			out.println("    document.Form1.TXT_EMP_FAX_NO.value=oBj.valout[63];"); 
			out.println("    document.Form1.TXT_NO_OF_CHILDREN.value=oBj.valout[64];"); 
			out.println("    document.Form1.TXT_DEPENDENTS.value=oBj.valout[65];"); 

			out.println("}"); 

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Client</td>"); 
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

			out.println("<td width='30%' >CLIENT_CODE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >CLIENT_TYPE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_TYPE' maxlength='1' size='1'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >FULL_NAME *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FULL_NAME' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >BUSINESS_SUB_SECTOR *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BUSINESS_SUB_SECTOR' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >CLIENT_CATEGORY *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CATEGORY' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >ADDRESS1 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS1' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >ADDRESS2 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS2' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >CITY_CODE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >REFERENCE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REFERENCE' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >TEL_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >FAX_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FAX_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMAIL *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMAIL' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >OFFICE_TEL_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OFFICE_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >MOBILE_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MOBILE_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >CAT_TYPE_CODE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CAT_TYPE_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >NIC_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NIC_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >BUSINESS_CERTIFICATE_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BUSINESS_CERTIFICATE_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >KEY_DECISION_MAKER *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_KEY_DECISION_MAKER' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DESIGNATION *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESIGNATION' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DIRECT_TEL_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DIRECT_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >CONTACT_FOR_PAYMENT *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CONTACT_FOR_PAYMENT' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DESIGNATION_PAYMENT *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESIGNATION_PAYMENT' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >FACTORY_ADDRESS1 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACTORY_ADDRESS1' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >FACTORY_ADDRESS2 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACTORY_ADDRESS2' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >FACTORY_STATUS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACTORY_STATUS' maxlength='4' size='4'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >F_CONTACT_PERSON *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_CONTACT_PERSON' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >REGISTERED_ADDRESS1 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REGISTERED_ADDRESS1' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >REGISTERED_ADDRESS2 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REGISTERED_ADDRESS2' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >REGISTERED_CITY_CODE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REGISTERED_CITY_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >REGISTERED_STATUS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REGISTERED_STATUS' maxlength='4' size='4'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >CORRESPONDENCE_STATUS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CORRESPONDENCE_STATUS' maxlength='4' size='4'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >F_TEL_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >F_FAX_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_FAX_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >F_EMAIL *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_F_EMAIL' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >ISSUED_SHARE_CAPITAL *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ISSUED_SHARE_CAPITAL' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DATE_OF_INCORPORATION *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DATE_OF_INCORPORATION' maxlength='7' size='7'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >VAT_REG_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >VAT_REG_DATE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG_DATE' maxlength='7' size='7'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >TITLE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TITLE' maxlength='5' size='5'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >FIRST_NAME *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >SURNAME *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SURNAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >INITIALS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INITIALS' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >OTHER_NAME *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OTHER_NAME' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >RESIDENTIAL_STATUS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDENTIAL_STATUS' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DURATION_AT_YEARS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DURATION_AT_YEARS' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DURATION_AT_MONTHS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DURATION_AT_MONTHS' maxlength='2' size='2'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >PASSPORT_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PASSPORT_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >MARITAL_STATUS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MARITAL_STATUS' maxlength='9' size='9'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DATE_OF_BIRTH *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DATE_OF_BIRTH' maxlength='7' size='7'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >NATIONALITY *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NATIONALITY' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >GENDER *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GENDER' maxlength='6' size='6'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >BA_NATURE_OF_BUSINESS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BA_NATURE_OF_BUSINESS' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >BA_PROFESSION *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BA_PROFESSION' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >BA_QUALIFICATIONS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BA_QUALIFICATIONS' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >BA_DESIGNATION *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BA_DESIGNATION' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMP_NAME *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_NAME' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMP_ADDRESS1 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_ADDRESS1' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMP_ADDRESS2 *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_ADDRESS2' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMP_REFERENCE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_REFERENCE' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMP_RDESIGNATION *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_RDESIGNATION' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMP_TEL_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EMP_FAX_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_FAX_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >NO_OF_CHILDREN *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NO_OF_CHILDREN' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DEPENDENTS *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEPENDENTS' maxlength='22' size='22'></td>"); 
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
