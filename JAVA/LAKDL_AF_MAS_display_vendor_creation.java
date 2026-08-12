//--
//SCREEN NAME:SYSTEM ADMINISTRATION - VENDOR
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_vendor_creation extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Creation of Vendors</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var y=0;");
			out.println("var no=0;");
			out.println("var val_of=0;");
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var branch_arry=new Array();");
			
			out.println("var loc_arry=new Array();");
			out.println("var title_arry=new Array();");
			out.println("var fname_arry=new Array();");
			out.println("var lname_arry=new Array();");
			out.println("var id_arry=new Array();");
			out.println("var address_arry=new Array();");
			out.println("var city_arry=new Array();");
			out.println("var city_arry_desc=new Array();"); // added by nuwan de silva on 20-11-2007
			out.println("var country_array=new Array();"); // added by nuwan de silva on 20-11-2007
			out.println("var country_desc_array=new Array();"); // added by nuwan de silva on 20-11-2007
			
			
			//out.println("var city_desc_arry=new Array();");
			
			
			out.println("var df1_arry=new Array();");
			out.println("var fax_arry=new Array();");
			out.println("var tel_arry=new Array();");
			
			out.println("var branch=0;");
			out.println("var loc=0;");
			out.println("var city=0;");
			out.println("var id=0;");
			out.println("var b_count;");

			out.println("function chk_for_numbers(){");
			out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar == '32' )");//checking for spaces
			out.println("{");		
			out.println("window.event.keyCode=keyChar;");
			out.println("}");
			out.println("else");
			out.println("{");
			out.println("alert ('Entered ID No is wrong,check for spaces....');");
			out.println("window.event.keyCode='';");
			out.println("}");
			out.println("}");


			out.println("function rem_lead_spaces(no) {");
			out.println(" var m_id_no=\"TXT_ID_NO_\"+no;");
			out.println(" var m_objval	=document.Form1.elements[m_id_no].value;"); 
			out.println("var m_length = m_objval.length;");
			out.println("alert(m_length)");
			out.println("cnt = 0;");
			out.println("var m_char =  m_objval.charAt(cnt);");
			out.println("while (m_char=='' && cnt <= m_length) {");
			out.println("	cnt++;");
			out.println("	m_char =  m_objval.charAt(cnt);");
			out.println("}");
			out.println("m_objval=m_objval.substring(cnt,m_length);");
			out.println("return m_objval;");
			out.println("}");
	
			out.println("function check_nic(no) {");
			out.println("{");
			out.println("var m_nic;");
			out.println("var m_size;");
			out.println("var nicno;");
			out.println("var inputStr;");
			out.println(" m_id_no=\"TXT_ID_NO_\"+no;");
			out.println("nicno	=document.Form1.elements[m_id_no].value;"); 
			out.println("nicno=nicno.toUpperCase();");
			out.println("m_size=nicno.length;");
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = nicno.charAt(i);");
			out.println("if (i==9)"); 
			out.println("{");		
			out.println("if ((inputStr != 'V')&&(inputStr != 'X'))");
			out.println("{");
			out.println("alert('Entered ID No is wrong....!');");
			out.println("document.Form1.elements[m_id_no].value=''");
			out.println("document.Form1.elements[m_id_no].focus()");
			out.println("return false;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			out.println("else if(i>=0 && i<=8)");
			out.println("{");
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered ID No is wrong....!');");
			out.println("document.Form1.elements[m_id_no].value=''");
			out.println("document.Form1.elements[m_id_no].focus()");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			out.println("if(m_size!==10){");
			out.println("alert('Length of ID No is 10....!');");
			out.println("document.Form1.elements[m_id_no].value=''");
			out.println("document.Form1.elements[m_id_no].focus()");
			out.println("return false;");
			out.println("break;	");
			
			out.println("}");
			out.println("if (inputStr==' ')");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered ID No is wrong,check for spaces...!');");
			out.println("document.Form1.elements[m_id_no].value=''");
			out.println("document.Form1.elements[m_id_no].focus()");

			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");

			out.println("}");

				
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value=='t1'){");
			
			out.println("				alert('Record already exists');");
			out.println("    document.Form1.TXT_VENDOR_CODE.value='';"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.focus();"); 
			out.println("			}");
		
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VENDOR_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("    document.Form1.TXT_VENDOR_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_NAME.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_CATEGORY.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_TYPE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=data_vec[5];"); 
			
			out.println("fill_fields(data_vec)");
			
			
			out.println("}");
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VENDOR_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("help_update()");
			out.println("}");

			out.println("else if(data_vec.length==0 && document.Form1.TXT_CATEGORY.value!='' && document.Form1.hid_val.value=='t2'){");
			out.println("help_button_2()");
			out.println("}");
			out.println("else if(data_vec.length==0 && branch!=''  && document.Form1.hid_val.value=='t3' && document.Form1.hid_val.value!='t4'){");
			out.println("help_button_7(document.Form1.hid_use.value)");
			
			out.println("}");
			out.println("else if(data_vec.length==0 && loc!='' && document.Form1.hid_val.value=='t4' &&  document.Form1.hid_val.value!='t3'){");
			out.println("help_button_5(document.Form1.hid_use.value)");
			out.println("}");
			out.println("else if(data_vec.length==0 && city!='' && document.Form1.hid_val.value=='t5'){");
			
			out.println("help_button_6(document.Form1.hid_use.value)");
			out.println("}");

			out.println("else if(data_vec.length>0 && id!='' && document.Form1.hid_val.value=='t6'){");
			out.println("				alert('Record already exists');");
			out.println(" m_id_no=\"TXT_ID_NO_\"+document.Form1.hid_use.value;");
			out.println("document.Form1.elements[m_id_no].value=''");
			out.println("document.Form1.elements[m_id_no].focus()");
			out.println("}");
			
			out.println("else if(data_vec.length==0 && document.Form1.elements[\"TXT_COUNTRY_CODE_\"+document.Form1.hid_use.value].value!=\"\" &&  document.Form1.hid_val.value=='T_COUNTRY'){");
			out.println("help_country()");
			out.println("}");
			out.println("else if(data_vec.length>0  && document.Form1.elements[\"TXT_COUNTRY_CODE_\"+document.Form1.hid_use.value].value!=\"\" && document.Form1.hid_val.value=='T_COUNTRY'){");
			out.println("country_code=\"TXT_COUNTRY_CODE_\"+document.Form1.hid_use.value;");
			out.println("country_code=\"TXT_COUNTRY_DESC_\"+document.Form1.hid_use.value;");
			out.println("document.Form1.elements[country_code].value=data_vec[0]");
			out.println("document.Form1.elements[country_desc].value=data_vec[1]");
			out.println("}");
			//***************Added by Jithendra 21-10-2016************************************/
			out.println("else if( document.Form1.hid_val.value=='NIC_DUP'){");
			out.println("m_count=data_vec[0];");
			out.println("nic_duplication(m_count);");
			out.println("}");
			/******************************END***********************************************/
			
			
			out.println("}");
			
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");

			out.println("}");
			
			
			out.println("function makeRequest(obj) {");
			out.println("	if( document.Form1.SCREEN_NAME.value==\"NEW\" )"); //document.Form1.hid_val.value='t1' &&
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_vendor_creation3&data_val=\"+obj.value;");
			out.println(" else");
			
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_vendor_creation2&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			
			//out.println("window.open(m_url);");
			out.println(" load_interface(m_url,'XML');");
			
			//out.println("}");
			out.println("}");

			out.println("function makeRequest1(obj) {");
			out.println("if(document.Form1.hid_val.value='t2'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_category&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest2(no) {");
			out.println("}");
			
			
			
			out.println("function makeRequest3(no) {");
			out.println("document.Form1.hid_use.value=no");
			out.println("assig('t4')");
			out.println(" m_loc=\"TXT_LOCATION_CODE_\"+no;");
			out.println("m_loc=document.Form1.elements[m_loc].value");
			out.println("loc=m_loc");

			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+m_loc;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest4(no) {");
			out.println("document.Form1.hid_use.value=no");
			out.println("assig('t5')");
			out.println(" m_city=\"TXT_CITY_CODE_\"+no;");
			out.println("m_cy=document.Form1.elements[m_city].value");
			out.println("city=m_cy");

			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+m_cy;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			//_______ added by nuwan de silva on 20-11-2007 ___
			out.println("function validate_country(no) {");
			out.println("document.Form1.hid_use.value=no");
			out.println("assig('T_COUNTRY')");
			out.println("m_country=\"TXT_COUNTRY_CODE_\"+no;");
			out.println("m_country_val=document.Form1.elements[m_country].value");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_country&data_val=\"+m_country_val;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			//_______ end by nuwan de silva  ____________________
			
			
			out.println("function makeRequest5(no) {");
			out.println("document.Form1.hid_use.value=no");
			out.println("assig('t6')");
			out.println(" m_id=\"TXT_ID_NO_\"+document.Form1.hid_use.value;");
			out.println("m_id_no=document.Form1.elements[m_id].value");
			out.println("id=m_id_no");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_id_no&data_val=\"+m_id_no;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("no=parseInt(document.Form1.hid_no.value)");
			out.println("//validations goes here"); 
			/*out.println("if(document.Form1.TXT_VENDOR_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VENDOR_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); */
			out.println("if(document.Form1.TXT_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			// added by udara 14-08-2015
			out.println("else if(document.Form1.TXT_VAT_REG.value==\"\"){  "); 
			out.println("DIV_TXT_VAT_REG.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			// end by udara 14-08-2015

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println("no=parseInt(document.Form1.hid_no.value)");
			out.println("for (var i=0;i<=no;i++){"); 
			//out.println(" m_branch=\"TXT_BRANCH_CODE_\"+i;"); // commented by ashini
			//out.println("m_loc_code=\"TXT_LOCATION_CODE_\"+i;"); //commented by ashini
			
			//out.println("m_title=\"TXT_TITLE_\"+i;");  // commented by ashini
			//out.println("m_fname=\"TXT_FIRST_NAME_\"+i;"); // commented by ashini
		//	out.println("m_lname=\"TXT_LAST_NAME_\"+i;"); // commented by ashini
			//out.println("m_id=\"TXT_ID_NO_\"+i;"); // commented by ashini
			out.println("m_addr=\"TXT_ADDRESS_\"+i;");
			
			out.println("m_city=\"TXT_CITY_CODE_\"+i;");
			out.println("m_df1=\"TXT_DEFAULT_VALUE1_\"+i;");
			
			out.println("m_tel=\"TXT_TEL_NO_\"+i;");
			out.println("m_city=\"TXT_CITY_CODE_\"+i;");
			out.println("}");
			
			out.println("		if(validate_data()){"); 
			
		//	out.println("if(document.Form1.elements[m_branch].value=='' || document.Form1.elements[m_loc_code].value=='' || document.Form1.elements[m_title].value=='' || document.Form1.elements[m_fname].value=='' || document.Form1.elements[m_lname].value=='' || document.Form1.elements[m_id].value=='' || document.Form1.elements[m_addr].value=='' || document.Form1.elements[m_city].value=='' || document.Form1.elements[m_tel].value==''){"); //Comment by Chandana on 24/04/2007
		//	out.println("if(document.Form1.elements[m_branch].value=='' || document.Form1.elements[m_loc_code].value=='' || document.Form1.elements[m_title].value=='' || document.Form1.elements[m_fname].value=='' || document.Form1.elements[m_lname].value=='' || document.Form1.elements[m_addr].value=='' || document.Form1.elements[m_city].value=='' || document.Form1.elements[m_tel].value==''){"); //Added by Chandana on 24/04/2007
			//out.println("if(document.Form1.elements[m_addr].value=='' || document.Form1.elements[m_tel].value==''){"); //Added by Chandana on 24/04/2007 // commented by udara 14-12-2016
			out.println("if(document.Form1.elements[m_addr].value=='' || document.Form1.elements[m_tel].value=='' || document.Form1.elements[m_city].value==''){"); //Added by Chandana on 14-12-2016
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			
			/*
			out.println("if(document.Form1.elements[m_branch].value==''){");
			out.println("document.Form1.elements[m_branch].focus()");
			out.println("}");
			*/ 
			// commented by ashini
			/*
			out.println("if(document.Form1.elements[m_loc_code].value==''){");
			out.println("document.Form1.elements[m_loc_code].focus()");
			out.println("}");
			out.println("else if(document.Form1.elements[m_title].value==''){");
			out.println("document.Form1.elements[m_title].focus()");
			out.println("}");
			out.println("else if(document.Form1.elements[m_fname].value==''){");
			out.println("document.Form1.elements[m_fname].focus()");
			out.println("}");
			out.println("else if(document.Form1.elements[m_lname].value==''){");
			out.println("document.Form1.elements[m_lname].focus()");
			out.println("}"); commented by ashini */ 
			//out.println("else if(document.Form1.elements[m_id].value==''){"); //Comment by Chandana on 24/04/2007
			//out.println("document.Form1.elements[m_id].focus()");
			//out.println("}");
			out.println(" if(document.Form1.elements[m_addr].value==''){");
			out.println("document.Form1.elements[m_addr].focus()");
			out.println("}");
			/*out.println("else if(document.Form1.elements[m_city].value==''){");
			out.println("document.Form1.elements[m_city].focus()");
			out.println("}");*/
			out.println("else if(document.Form1.elements[m_tel].value==''){");
			out.println("document.Form1.elements[m_tel].focus()");
			out.println("}");
			out.println("}");
			out.println("else{");
			//out.println("alert('inside the else part');");
			//out.println("if(check_br()){");  COMMENETD BY ASHINI
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			/*out.println("if(document.Form1.elements[m_id].value==''){"); //Added by Chandana on 24/04/2007
			out.println("document.Form1.elements[m_id].value='-' ");
			out.println("}"); COMMENETD BY ASHINI
			*/
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_vendor_creation?number='+parseInt(document.Form1.hid_no.value)+'';");  
			out.println("		document.Form1.submit();	"); 
			out.println("	}");
			out.println("		}"); 			
			//out.println("		}");
			out.println("}");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_vendor_creation';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_vendor_creation';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}");
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_vendor_creation\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Creation of Vendors - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Creation of Vendors - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;");  //added by ashini
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=false;");  //added by ashini
			out.println("document.Form1.TXT_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_CATEGORY.disabled=true;"); 
			out.println("document.Form1.TXT_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;");
			out.println("document.Form1.BUT_TXT_CATEGORY.disabled=true;");
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=false;");  //added by ashini
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";");
			out.println("document.Form1.hid_save.value=\"Save\";");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";"); 
			out.println("document.Form1.hid_save.value=\"Modify\";");

			out.println("}else if(m_val==\"DACT\"){");
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save.value=\"Deactivate\";");

			out.println("}else if(m_val==\"RACT\"){");
			out.println("document.Form1.hid_status.value=\"Reactivate\";"); 
			out.println("document.Form1.hid_save.value=\"Reactivate\";");
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
			out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"8\"){");  //added by nuwan de silva on 20-11-07
			out.println("		country_assign();"); 
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
			out.println("	clear_data();");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("Close();"); 
			
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      //out.println("window.close();");
      out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");//vendor
			out.println("    document.Form1.TXT_VENDOR_CODE.value='';"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.focus();"); 
			out.println("    document.Form1.TXT_NAME.value='';"); 
			out.println("    document.Form1.TXT_CATEGORY.value='';"); 
			out.println("    document.Form1.TXT_TYPE.value='';"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value='N';"); 
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"2\"){");//category
			out.println("    document.Form1.TXT_CATEGORY.value='';"); 
			out.println("    document.Form1.TXT_CATEGORY.focus();"); 
			out.println("}");
			/*out.println("if(document.Form1.hid_help_type.value==\"5\"){");//category
			out.println("m_loc=\"TXT_LOCATION_CODE_\"+document.Form1.hid_use.value;");
			out.println(" document.Form1.elements[m_loc].value=''"); 
			out.println(" document.Form1.elements[m_loc].focus()"); 
			out.println("}");commented by ashini */
			out.println("if(document.Form1.hid_help_type.value==\"6\"){");//category
			out.println("m_city=\"TXT_CITY_CODE_\"+document.Form1.hid_use.value;");
			out.println("document.Form1.elements[m_city].value=''");
			out.println("document.Form1.elements[m_city].focus()");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"7\"){");//branch
			out.println("m_branch=\"TXT_BRANCH_CODE_\"+document.Form1.hid_use.value;");
			out.println("document.Form1.elements[m_branch].value='';");
			out.println("document.Form1.elements[m_branch].focus();");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"4\"){");//branch
			out.println("m_fax=\"TXT_FAX_NO_\"+document.Form1.hid_use.value;");
			out.println("m_tel=\"TXT_TEL_NO_\"+document.Form1.hid_use.value;");
			
			out.println("document.Form1.elements[m_fax].value=''");
			out.println("document.Form1.elements[m_fax].focus()");
			out.println("document.Form1.elements[m_tel].value='';"); 

			out.println("}");
			
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
			out.println("    m_sql = \"m_help_TXT_VENDOR_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VENDOR_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CATEGORY_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CATEGORY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CATEGORY.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_TYPE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_TYPE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_TYPE.value=oBj.valout[2];"); 
			out.println("}"); 

//------------------------------------------------------------------------------------------------

			out.println("function help_button_4(no) {"); 
			out.println("document.Form1.hid_use.value=no");
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("m_branch='TXT_BRANCH_CODE_'+no;");
			
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_branch].value+\"@\"+\"Y@\";"); 
			
			
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 

			out.println("m_fax=\"TXT_FAX_NO_\"+document.Form1.hid_use.value;");
			out.println("m_tel=\"TXT_TEL_NO_\"+document.Form1.hid_use.value;");
			out.println("if(oBj.valout[9]=='null' ||oBj.valout[9]==''){");
			out.println("    document.Form1.elements[m_fax].value='';}");
			out.println("else {document.Form1.elements[m_fax].value=oBj.valout[9]}");
			out.println("    document.Form1.elements[m_tel].value=oBj.valout[8];"); 
			out.println("}"); 

			out.println("function help_button_5(no) {");
			out.println("document.Form1.hid_use.value=no");
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("m_loc_code='TXT_LOCATION_CODE_'+no;");
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_loc_code].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("m_loc_code=\"TXT_LOCATION_CODE_\"+document.Form1.hid_use.value;");
			out.println("document.Form1.elements[m_loc_code].value=oBj.valout[2]");
			out.println("}"); 
			
//-------------------------------------------------------------------------------------------------
			out.println("function help_button_6(no) {"); 
			out.println("document.Form1.hid_use.value=no");
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("m_city='TXT_CITY_CODE_'+no;");

			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_city].value+\"@Y@\";"); 

			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {");
			out.println("m_city=\"TXT_CITY_CODE_\"+document.Form1.hid_use.value;");
			out.println("m_city_des=\"TXT_CITY_DESC_\"+document.Form1.hid_use.value;"); // added by ashini
			//out.println("alert(oBj.valout[2]);"); 
			//out.println("alert(oBj.valout[3]);"); 
			out.println("document.Form1.elements[m_city].value=oBj.valout[2]");
			out.println("document.Form1.elements[m_city_des].value=oBj.valout[3]");// added by ashini
			out.println("}"); 


//-------------------------------------------------------------------------------------------------


      //_________________ added by nuwan de silva on 20-11-2007_____________________________
			out.println("function help_country(no) {"); 
			out.println("document.Form1.hid_use.value=no");
			out.println("document.Form1.hid_help_type.value=\"8\";"); 
			out.println("m_country='TXT_COUNTRY_CODE_'+no;");
		  out.println("    m_sql = \"m_help_TXT_COUNTRY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_country].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 	
			
			out.println("function country_assign() {");
			out.println("m_country=\"TXT_COUNTRY_CODE_\"+document.Form1.hid_use.value;");
			out.println("m_country_desc=\"TXT_COUNTRY_DESC_\"+document.Form1.hid_use.value;"); 
			out.println("document.Form1.elements[m_country].value=oBj.valout[2]");
			out.println("document.Form1.elements[m_country_desc].value=oBj.valout[3]");
			out.println("}"); 
			//________________ end by nuwan de silva _______________________________________________________


			out.println("function help_button_7(no) {");
			out.println("document.Form1.hid_use.value=no");
			out.println("m_branch='TXT_BRANCH_CODE_'+no;");
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";");
			out.println("var v_code=document.Form1.elements[m_branch].value;");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"|| document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = v_code+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = v_code+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 


			out.println("function help_value_assign_7() {"); 
			out.println(" m_branch=\"TXT_BRANCH_CODE_\"+document.Form1.hid_use.value;");
			/*out.println("m_loc_code=\"TXT_LOCATION_CODE_\"+document.Form1.hid_use.value;");
			out.println("m_title=\"TXT_TITLE_\"+document.Form1.hid_use.value;"); 
			out.println("m_fname=\"TXT_FIRST_NAME_\"+document.Form1.hid_use.value;");
			out.println("m_lname=\"TXT_LAST_NAME_\"+document.Form1.hid_use.value;");
			out.println("m_id=\"TXT_ID_NO_\"+document.Form1.hid_use.value;"); COMMENETD BY ASHINI */
			out.println("m_add=\"TXT_ADDRESS_\"+document.Form1.hid_use.value;");
			out.println("m_city=\"TXT_CITY_CODE_\"+document.Form1.hid_use.value;");
			out.println("m_df1=\"TXT_DEFAULT_VALUE1_\"+document.Form1.hid_use.value;");
			out.println("document.Form1.elements[m_branch].value=oBj.valout[2]");
			out.println("}"); 
//-------------------------------------------------------------------------------------------------

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_VENDOR_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_VENDOR_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_VENDOR_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CATEGORY.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_TYPE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_VAT_REG.value=oBj.valout[7];"); //Added By Sandun
			out.println("assig('t1')");
			out.println("makeRequest(document.Form1.TXT_VENDOR_CODE)");
			out.println("}"); 
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			//out.println("		help_update_value_assign_99();"); 
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
			out.println("    m_sql = \"m_view_TXT_VENDOR_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_VENDOR_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			
			
			out.println("function disable_row(no){");
			out.println("for (var i=0;i<no;i++){"); 
			out.println(" m_branch=\"TXT_BRANCH_CODE_\"+i;");
		 /* COMMENETD BY ASHINI
			out.println("m_loc_code=\"TXT_LOCATION_CODE_\"+i;");
			out.println("m_title=\"TXT_TITLE_\"+i;");
			out.println("m_fname=\"TXT_FIRST_NAME_\"+i;");
			out.println("m_lname=\"TXT_LAST_NAME_\"+i;");
			out.println("m_id=\"TXT_ID_NO_\"+i;");
			*/
			
			out.println("m_addr=\"TXT_ADDRESS_\"+i;");
			
			out.println("m_city=\"TXT_CITY_CODE_\"+i;");
			//out.println("m_city_des=\"TXT_CITY_DESC_\"+i;");
			out.println("m_df1=\"TXT_DEFAULT_VALUE1_\"+i;");
			
			out.println("m_tel=\"TXT_TEL_NO_\"+i;");
			out.println("m_fax=\"TXT_FAX_NO_\"+i;");
			
			out.println("m_add=\"BUT_ADD_\"+i;");
			out.println("if(document.Form1.elements[m_branch].value!=''){");
			out.println("document.Form1.elements[m_branch].disabled=true");
			/* COMMENETD BY ASHINI
			out.println("document.Form1.elements[m_loc_code].disabled=true");
			out.println("document.Form1.elements[m_title].disabled=true");
			out.println("document.Form1.elements[m_fname].disabled=true");
			out.println("document.Form1.elements[m_lname].disabled=true");
			out.println("document.Form1.elements[m_id].disabled=true");
			*/
			out.println("document.Form1.elements[m_addr].disabled=true");

			out.println("document.Form1.elements[m_city].disabled=true");
		//	out.println("document.Form1.elements[m_city_des].disabled=true");
			out.println("document.Form1.elements[m_df1].disabled=true");
			out.println("document.Form1.elements[m_tel].disabled=true");
			out.println("document.Form1.elements[m_fax].disabled=true");
			out.println("document.Form1.elements[m_add].disabled=true");
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function validate_row(){"); 
			out.println("no=document.Form1.hid_no.value");
		
			
			out.println("for (var i=0;i<=no;i++){"); 
			out.println(" m_branch=\"TXT_BRANCH_CODE_\"+i;");
			/*
			out.println("m_loc_code=\"TXT_LOCATION_CODE_\"+i;");
			out.println("m_title=\"TXT_TITLE_\"+i;");
			out.println("m_fname=\"TXT_FIRST_NAME_\"+i;");
			out.println("m_lname=\"TXT_LAST_NAME_\"+i;");
			out.println("m_id=\"TXT_ID_NO_\"+i;");
			COMMENETD BY ASHINI */
			out.println("m_addr=\"TXT_ADDRESS_\"+i;");
			
			out.println("m_city=\"TXT_CITY_CODE_\"+i;");
			out.println("m_df1=\"TXT_DEFAULT_VALUE1_\"+i;");
			
			out.println("m_tel=\"TXT_TEL_NO_\"+i;");
			out.println("m_fax=\"TXT_FAX_NO_\"+i;");
			
			out.println("m_add=\"BUT_ADD_\"+i;");
			out.println("if(document.Form1.elements[m_branch].value==\"\"){  "); 
			out.println("DIV_TXT_BRANCH_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.elements[m_loc_code].value==\"\"){"); 
			out.println("DIV_TXT_LOCATION_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			/*out.println("else if(document.Form1.elements[m_title].value==\"\"){"); 
			out.println("DIV_TXT_TITLE.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println("else if(document.Form1.elements[m_fname].value==\"\"){"); 
			out.println("DIV_TXT_FIRST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println("else if(document.Form1.elements[m_lname].value==\"\"){"); 
			out.println("DIV_TXT_LAST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println("else if(document.Form1.elements[m_id].value==\"\"){"); 
			out.println("DIV_TXT_ID_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			COMMENETD BY ASHINI */
			out.println("else if(document.Form1.elements[m_addr].value==\"\"){"); 
			out.println("DIV_TXT_ADDRESS.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			/*out.println("else if(document.Form1.elements[m_city].value==\"\"){"); 
			out.println("DIV_TXT_CITY_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}");*/
			out.println("else if(document.Form1.elements[m_tel].value==\"\"){"); 
			out.println("DIV_TXT_TEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_br(){");
			out.println("val_new=parseInt(document.Form1.hid_no.value);");
			out.println("val_of=parseInt(document.Form1.hid_no.value);");
			out.println("br_code='TXT_BRANCH_CODE_'+val_of;");
			out.println("brcode=document.Form1.elements[br_code].value;");
			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){"); 			
			out.println("if(brcode!=\"\"){");
			out.println("			   b_count = 0;");
			out.println("for(var i=0;i<parseInt(val_of);i++){");
			out.println("br_code1='TXT_BRANCH_CODE_'+i");
			out.println("brcode1=document.Form1.elements[br_code1].value;");
			out.println(" if(val_new >=1 ){");
			out.println("if(brcode==brcode1)");
			out.println("{");
			out.println("alert('Branch already entered...!');");
			out.println("document.Form1.elements[br_code].value='';");
			out.println("document.Form1.elements[br_code].focus();");
			out.println("b_count=1");
			out.println("return false;");
			out.println("	}");
			out.println("	if(b_count==1){");			
			out.println("	break");				
			out.println("	}");				
			out.println("	}");	
			out.println("	}");	
			out.println("return true");
			out.println("}"); 
			
			out.println("}"); 
      out.println("else{"); 
			out.println("return true");
			out.println("}"); 
			out.println("}"); 

			
			
			
			
			
			out.println("function check_id(no){");
			out.println("val_of=document.Form1.hid_no.value;");
			out.println("var id_code;");
			out.println("var id_code1;");
			out.println("id_code='TXT_ID_NO_'+no;");
			out.println("idcode=document.Form1.elements[id_code].value;");
			out.println("for(var i=0;i<val_of;i++){");
			out.println("id_code1='TXT_ID_NO_'+i");
			
				
			out.println("idcode1=document.Form1.elements[id_code1].value;");
			out.println("if(idcode==idcode1 && (idcode!=\"\" || idcode1!=\"\") &&  (idcode!=\"-\" || idcode1!=\"-\"))");
			out.println("{");
			out.println("alert('ID No already entered...!');");
			out.println("document.Form1.elements[id_code].value='';");
			out.println("document.Form1.elements[id_code].focus();");
			out.println("return false;");
			out.println("break;");
			out.println("	}");
			out.println("	}");	
		  out.println("	}");	
			
			out.println("function init(row){");
			
			out.println("change1.innerHTML+='<HR><table align=\"center\" width=\"100%\" class=\"table\">'+"); 
		
			out.println("'<tr>'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_BRANCH_CODE\"  class=div_input>Branch Code</DIV></td>'+"); 
	//		out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_BRANCH_CODE_'+row+' maxlength=\"10\" size=\"10\" onblur=makeRequest2('+row+'),check_br('+row+')>'+"); 
				out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_BRANCH_CODE_'+row+' maxlength=\"10\" size=\"10\" onblur=makeRequest2('+row+')  disabled >'+"); 
		

			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			/*  COMMENETD BY ASHINI
			out.println("'<tr>'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_LOCATION_CODE\"  class=div_input>Location Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_LOCATION_CODE_'+row+' maxlength=\"10\" size=\"10\" onblur=makeRequest3('+row+')>'+");
			out.println("'<input class=but_input type=button name=BUT_LOCATION_CODE_'+row+' value=\"Help\" onClick=\"help_button_5('+row+')\"></td>'+"); 

			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			*/
      out.println("'<tr >'+"); 
		
			
     /* COMMENETD BY ASHINI
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=div_input>Branch Manager Title *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_TITLE_'+row+' maxlength=\"5\" size=\"1\">'+");  
			out.println("'<option value=\"Mr\" selected>Mr</option>'+");			
			out.println("'<option value=\"Mrs\" >Mrs</option>'+");		
			out.println("'<option value=\"Miss\" >Miss</option>'+");		
			out.println("'<option value=\"Dr\" >Dr</option>'+");		
			out.println("'<option value=\"Prof\" >Professor</option>'+");		
			out.println("'<option value=\"Reve\" >Rev.</option>'+");		
			out.println("'<option value=\"Othr\" >Other</option>'+");		

			out.println("'</select>'+");
			out.println("'</td>'+");
     
			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			
			

			out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_FIRST_NAME\"  class=div_input>Branch Manager First Name *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_FIRST_NAME_'+row+'  maxlength=\"200\" size=\"20\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_LAST_NAME\"  class=div_input>Branch Manager Last Name *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_LAST_NAME_'+row+' maxlength=\"50\" size=\"20\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_ID_NO\" class=div_input>Branch Manager ID No </DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_ID_NO_'+row+' maxlength=\"10\" size=\"10\" onblur=\"check_nic('+row+'),makeRequest5('+row+'),check_id('+row+')\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 
      */

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS\"  class=div_input>Address *</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_ADDRESS_'+row+' maxlength=\"200\" style=\"{width :200px;}\"  size=\"30\" ></td>'+"); 
			//out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+"); 
			//out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=div_input>City Code </DIV></td>'+"); // commented by udara 14-12-2016
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=div_input>City Code * </DIV></td>'+");  // added by udara 14-12-2016
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_CITY_CODE_'+row+' maxlength=\"10\" size=\"10\"  onblur=makeRequest4('+row+')>'+"); 
			out.println("'<input class=but_input type=button name=BUT_CITY_CODE_'+row+' value=\"Help\" onClick=\"help_button_6('+row+')\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_DESC\"  class=div_input>City Description</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_CITY_DESC_'+row+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\" disabled ></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 	
			//_________________added by nuwan de silva ____________________________________________________________
		  out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=div_input>Country Code </DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_COUNTRY_CODE_'+row+' maxlength=\"10\" size=\"10\"  onblur=validate_country('+row+')>'+"); 
			out.println("'<input class=but_input type=button name=BUT_CITY_CODE_'+row+' value=\"Help\" onClick=\"help_country('+row+')\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_DESC\"  class=div_input>Country Description</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_COUNTRY_DESC_'+row+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\" disabled ></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 	
     //________________ end by nuwan de silva ________________________________________________________________
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Default Value(Location)</td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_DEFAULT_VALUE1_'+row+' maxlength=\"1\" size=\"1\" >'+");  
			out.println("'<option value=\"N\" selected>No</option>'+");			
			out.println("'<option value=\"Y\" >Yes</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");
			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'</table>'+"); 
			
			
			
			out.println("'<HR>'+"); 
			out.println("'<table align=center width=\"100%\" class=table>'+"); 

			out.println("'<tr>'+"); 
			out.println("'<td>&nbsp</td>'+");
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_FAX_NO\"  class=div_input>Fax No</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_FAX_NO_'+row+' maxlength=\"60\" size=\"10\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			
			out.println("'<tr >'+");

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TEL_NO\"  class=div_input>Telephone No *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_TEL_NO_'+row+' maxlength=\"60\" size=\"10\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+row+' value=\"Add\" onClick=\"Add('+row+'),disable_row('+row+')\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+row+' value=\"Del\" onClick=\"Del('+row+')\"></td>'+"); 
			out.println("'</tr>'+"); 

			out.println("'<tr >'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'</table>';"); 
			
			out.println("}");
			
			
			
			
			out.println("function init_value(row){");
			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 
		
			out.println("'<tr>'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_BRANCH_CODE\"  class=div_input>Branch Code</DIV></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_BRANCH_CODE_'+row+' maxlength=\"10\" size=\"10\"  onblur=makeRequest2('+row+'),check_br('+row+') value=\"'+branch_arry[row]+'\">'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_BRANCH_CODE_'+row+' maxlength=\"10\" size=\"10\"  onblur=makeRequest2('+row+') value=\"'+branch_arry[row]+'\" disabled  >'+"); 

			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			/*out.println("'<tr>'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_LOCATION_CODE\"  class=div_input>Location Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_LOCATION_CODE_'+row+' maxlength=\"10\" size=\"10\" onblur=makeRequest3('+row+') value=\"'+loc_arry[row]+'\" >'+");
			out.println("'<input class=but_input type=button name=BUT_LOCATION_CODE_'+row+' value=\"Help\" onClick=\"help_button_5('+row+')\"></td>'+"); 
			

			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");  COMMENETD BY ASHINI */
			out.println("'</table >';"); 
			/*out.println("if(title_arry[row] ==\"Mr\" ){ ");
			out.println("  ");


			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=div_input>Branch Manager Title *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_TITLE_'+row+' maxlength=\"5\" size=\"1\">'+");  
			out.println("'<option value=\"Mr\" selected>Mr</option>'+");			
			out.println("'<option value=\"Mrs\" >Mrs</option>'+");		
			out.println("'<option value=\"Miss\" >Miss</option>'+");
			out.println("'<option value=\"Dr\" >Dr</option>'+");		
			out.println("'<option value=\"Prof\" >Professor</option>'+");		
			out.println("'<option value=\"Reve\" >Rev.</option>'+");		
			out.println("'<option value=\"Othr\" >Other</option>'+");		

			
			out.println("'</select>'+");
			out.println("'</td>'+");

			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</table >';"); 
			out.println(" } ");*/
			/*
			out.println("else if(title_arry[row] ==\"Mrs\" ){ ");
			out.println("  ");


			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=div_input>Branch Manager Title *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_TITLE_'+row+' maxlength=\"5\" size=\"1\">'+");  
			out.println("'<option value=\"Mr\" >Mr</option>'+");			
			out.println("'<option value=\"Mrs\" selected>Mrs</option>'+");		
			out.println("'<option value=\"Miss\" >Miss</option>'+");		
			out.println("'<option value=\"Dr\" >Dr</option>'+");		
			out.println("'<option value=\"Prof\" >Professor</option>'+");		
			out.println("'<option value=\"Reve\" >Rev.</option>'+");		
			out.println("'<option value=\"Othr\" >Other</option>'+");		
		
			out.println("'</select>'+");
			out.println("'</td>'+");

			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</table >';"); 
			out.println(" } ");
			*//*
			out.println("else if(title_arry[row] ==\"Miss\" ){ ");
			out.println("  ");


			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=div_input>Branch Manager Title *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_TITLE_'+row+' maxlength=\"5\" size=\"1\">'+");  
			out.println("'<option value=\"Mr\" >Mr</option>'+");			
			out.println("'<option value=\"Mrs\" >Mrs</option>'+");		
			out.println("'<option value=\"Miss\" selected>Miss</option>'+");
			out.println("'<option value=\"Dr\" >Dr</option>'+");		
			out.println("'<option value=\"Prof\" >Professor</option>'+");		
			out.println("'<option value=\"Reve\" >Rev.</option>'+");		
			out.println("'<option value=\"Othr\" >Other</option>'+");		

			
			out.println("'</select>'+");
			out.println("'</td>'+");

			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</table >';"); 
			out.println(" } ");*/
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 
      /* COMMENETD BY ASHINI

			out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_FIRST_NAME\"  class=div_input>Branch Manager First Name *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_FIRST_NAME_'+row+'  maxlength=\"200\" size=\"20\" value=\"'+fname_arry[row]+'\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_LAST_NAME\"  class=div_input>Branch Manager Last Name *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_LAST_NAME_'+row+' maxlength=\"50\" size=\"20\" value=\"'+lname_arry[row]+'\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_ID_NO\" class=div_input>Branch Manager ID No </DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_ID_NO_'+row+' maxlength=\"10\" size=\"10\" onblur=\"check_nic('+row+'),makeRequest5('+row+'),check_id('+row+')\" value=\"'+id_arry[row]+'\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); */
			out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS\"  class=div_input>Address *</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_ADDRESS_'+row+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\" value=\"'+address_arry[row]+'\"></td>'+"); 
			//out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+"); 
      out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=div_input>City Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_CITY_CODE_'+row+' maxlength=\"10\" size=\"10\" onblur=makeRequest4('+row+') value=\"'+city_arry[row]+'\">'+"); 
			out.println("'<input class=but_input type=button name=BUT_CITY_CODE_'+row+' value=\"Help\" onClick=\"help_button_6('+row+')\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_DESC\"  class=div_input>City Description</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_CITY_DESC_'+row+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\"  value=\"'+city_arry_desc[row]+'\" disabled ></td>'+"); //value=\"'+city_desc_arry[row]+'\"
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 	
			
			//____________________Added by nuwan de silva _________________________________________
			
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=div_input>Country Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_COUNTRY_CODE_'+row+' maxlength=\"10\" size=\"10\" onblur=validate_country('+row+') value=\"'+country_array[row]+'\">'+"); 
			out.println("'<input class=but_input type=button name=BUT_CITY_CODE_'+row+' value=\"Help\" onClick=\"help_country('+row+')\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_DESC\"  class=div_input>Country Description</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_COUNTRY_DESC_'+row+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\"  value=\"'+country_desc_array[row]+'\" disabled ></td>'+"); //value=\"'+city_desc_arry[row]+'\"
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 	

			
	    //_______________________ end by nuwan de silva ________________________________________
			
			
			
			out.println("'</table>';");
			out.println("if(df1_arry[row]==\"N\"){");
			out.println(" ");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Default Value(Location)</td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_DEFAULT_VALUE1_'+row+' maxlength=\"1\" size=\"1\" value=\"'+df1_arry[row]+'\">'+");  
			out.println("'<option value=\"N\" selected>No</option>'+");			
			out.println("'<option value=\"Y\" >Yes</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");
			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'</table>';");
			
			out.println("}");
			
			out.println("else if(df1_arry[row]==\"Y\"){");
			out.println(" ");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Default Value(Location)</td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_DEFAULT_VALUE1_'+row+' maxlength=\"1\" size=\"1\" value=\"'+df1_arry[row]+'\">'+");  
			out.println("'<option value=\"N\" >No</option>'+");			
			out.println("'<option value=\"Y\" selected>Yes</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");
			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'</table>';");
			
			out.println("}");
			
			
			//----------------------------------
			out.println("change1.innerHTML+='<HR><table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			
			out.println("'<tr>'+"); 
			out.println("'<td>&nbsp</td>'+");
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_FAX_NO\"  class=div_input>Fax No</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_FAX_NO_'+row+' maxlength=\"60\" size=\"10\" value=\"'+fax_arry[row]+'\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			
			out.println("'<tr >'+");

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TEL_NO\"  class=div_input>Telephone No *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_TEL_NO_'+row+' maxlength=\"60\" size=\"10\" value=\"'+tel_arry[row]+'\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+row+' value=\"Add\" onClick=\"Add('+row+'),disable_row('+row+')\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+row+' value=\"Del\" onClick=\"Del('+row+')\"></td>'+"); 
			out.println("'</tr>'+"); 

			out.println("'<tr >'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'</table>';"); 
			
			out.println("}");
			

	
			out.println("function Add(y) {");
			out.println("branch_arry[y]='';");
			out.println("loc_arry[y]='';");
			/*
			out.println("title_arry[y]='';");
			out.println("fname_arry[y]='';");
			out.println("lname_arry[y]='';");
			out.println("id_arry[y]='';");
			COMMENETD BY ASHINI
     */
			out.println("address_arry[y]='';");
			out.println("city_arry[y]='';");
			out.println("city_arry_desc[y]='';");
			out.println("df1_arry[y]='';");
			out.println("fax_arry[y]='';");
			out.println("tel_arry[y]='';");
			//out.println("city_desc_arry[y]='';");
			out.println("country_array[y]='';"); //added by nuwan de silva 20-11-2007
			out.println("country_desc_array[y]='';"); //added by nuwan de silva 20-11-2007
		
			
			
			out.println("val_of=parseInt(document.Form1.hid_no.value);");
			out.println("var br_code;");
			out.println("var br_code1;");
			out.println("var id_no;");
			out.println("var id_no1;");
			out.println("br_code='TXT_BRANCH_CODE_'+y;");
			
			//out.println("id_no='TXT_ID_NO_'+y;"); 			COMMENETD BY ASHINI

		
			out.println("brcode=document.Form1.elements[br_code].value;");
			
			//out.println("id=document.Form1.elements[id_no].value;"); 			COMMENETD BY ASHINI

			
			out.println("for(var i=0;i<val_of;i++){");
			
			out.println("br_code1='TXT_BRANCH_CODE_'+i");
			//out.println("id_no1='TXT_ID_NO_'+i"); 			COMMENETD BY ASHINI

			
			out.println("brcode1=document.Form1.elements[br_code1].value;");
		//	out.println("id1=document.Form1.elements[id_no1].value;"); 			COMMENETD BY ASHINI

			
			out.println("if(brcode==brcode1 && (brcode!=\"\" || brcode1!=\"\"))");
			out.println("{");
			out.println("alert('Branch already entered...!');");
			out.println("document.Form1.elements[br_code].value='';");
			out.println("document.Form1.elements[br_code].focus();");
			out.println("return false;");
			out.println("break;");
			out.println("	}");
			
			
			//--------------------------------------------------------
			//out.println("else if(id==id1 && (id!=\"\" || id1!=\"\")&& (id!=\"-\" || id1!=\"-\"))");
			//out.println("{");
			//out.println("alert('ID No already entered...!');");
			//out.println("document.Form1.elements[id_no].value='';");
			//out.println("document.Form1.elements[id_no].focus();");
			//out.println("return false;");
			//out.println("break;");
			//out.println("	}");	
			//------------------------COMMENETD BY ASHINI-----------------------
			out.println("	}");			

			
			out.println("y=parseInt(y)+1;");
			out.println("init(y);");
			
			out.println("document.Form1.hid_no.value=parseInt(y);");
			
			out.println("}");
					
					
			out.println("function Del(y) {");
			out.println("var e=0;");
			out.println("val_of=parseInt(document.Form1.hid_no.value);");
			out.println("if(val_of!=0){");
			out.println("for(var i=0;i<=val_of;i++){");
			out.println("m_branch='TXT_BRANCH_CODE_'+i;");
			/*out.println("m_loc_code='TXT_LOCATION_CODE_'+i;");
			out.println("m_title='TXT_TITLE_'+i;");
			out.println("m_fname='TXT_FIRST_NAME_'+i;");
			out.println("m_lname='TXT_LAST_NAME_'+i;");
			out.println("m_id='TXT_ID_NO_'+i;");
			COMMENETD BY ASHINI*/
			out.println("m_coun='TXT_COUNTRY_CODE_'+i;"); //added by nuwan de silva on 20-11-2007
			out.println("m_coun_desc='TXT_COUNTRY_DESC_'+i;"); //added by nuwan de silva on 20-11-2007
			out.println("m_add='TXT_ADDRESS_'+i;");
			out.println("m_city='TXT_CITY_CODE_'+i;");
			out.println("m_city_desc='TXT_CITY_DESC_'+i;"); //added by nuwan de silva on 20-11-2007
			out.println("m_df1='TXT_DEFAULT_VALUE1_'+i;");
			out.println("m_fax='TXT_FAX_NO_'+i;");
			out.println("m_tel='TXT_TEL_NO_'+i;");
			
			out.println(" ");
			out.println("if(y==i)");
			out.println("{");
			out.println("continue;");
			out.println("}");
			
			out.println("if(document.Form1.elements[m_branch].value=='')");
			out.println("{");
			

			out.println("branch_arry[y]='';");
			/*
			out.println("loc_arry[y]='';");
			out.println("title_arry[y]='';");
			out.println("fname_arry[y]='';");
			out.println("lname_arry[y]='';");
			out.println("id_arry[y]='';");
			COMMENETD BY ASHINI*/
      out.println("country_array[y]='';"); //added by nuwan de silva on 20-11-2007
			out.println("country_desc_array[y]='';"); //added by nuwan de silva on 20-11-2007
			out.println("address_arry[y]='';");
			out.println("city_arry[y]='';");
			out.println("city_arry_desc[y]='';");  //added by nuwan de silva on 20-11-2007
			out.println("df1_arry[y]='';");
			out.println("fax_arry[y]='';");
			out.println("tel_arry[y]='';");
			//out.println("city_desc_arry[y]='';");
			out.println("}");
			out.println("else{");
			out.println("branch_arry[e]=document.Form1.elements[m_branch].value;");
			/*
			out.println("loc_arry[e]=document.Form1.elements[m_loc_code].value;");
			out.println("title_arry[e]=document.Form1.elements[m_title].value;");
			out.println("fname_arry[e]=document.Form1.elements[m_fname].value;");
			out.println("lname_arry[e]=document.Form1.elements[m_lname].value;");
			out.println("id_arry[e]=document.Form1.elements[m_id].value;");
			COMMENETD BY ASHINI*/
			out.println("address_arry[e]=document.Form1.elements[m_add].value;");
			out.println("city_arry[e]=document.Form1.elements[m_city].value;");
			out.println("city_arry_desc[e]=document.Form1.elements[m_city_desc].value;");
			out.println("df1_arry[e]=document.Form1.elements[m_df1].value;");
			out.println("fax_arry[e]=document.Form1.elements[m_fax].value;");
			out.println("tel_arry[e]=document.Form1.elements[m_tel].value;");
			out.println("country_array[e]=document.Form1.elements[m_coun].value;"); // added by nuwan de silva on 20-11-2007
			out.println("country_desc_array[e]=document.Form1.elements[m_coun_desc].value;"); // added by nuwan de silva on 20-11-2007
			
			out.println("if(fax_arry[e]==\"\" || fax_arry[e]=='null'){");
			
			out.println("fax_arry[e]=\"\"");
			out.println("}");			
					/*	COMMENETD BY ASHINI	
			//out.println("if(id_arry[e]==\"\" || fax_arry[e]=='null' || id_arry[e]==\"-\" ){");
			//out.println("id_arry[e]=\"\"");
			//out.println("}"); */		
			
			out.println("e=e+1;");
			out.println("}");
			out.println("}");
			out.println("val_of=val_of-1;");
			out.println("change1.innerHTML='';");
			out.println("get_val(val_of)");
			out.println("document.Form1.hid_no.value=parseInt(val_of);");
			out.println("}"); 
			
			out.println("}"); 
			
			
			
			out.println("function get_val(val) {");
			out.println("for(var i=0;i<=val;i++){");
			out.println("init_value(i)");

			out.println("}");
			out.println("document.Form1.hid_no.value=parseInt(val);");
			out.println("}");	
		
		
		/*********************************Added by Jithendra 21-10-2016****************************/
		
		
		out.println("function makeRequest6(m_client,m_nic) {");
			out.println("document.Form1.hid_use.value=no");
			out.println("assig('NIC_DUP')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations4?chksql=m_prime_chk_LAKDL_AF_MAS_display_id_no_duplication&data_val=\"+m_nic;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
		out.println("function nic_duplication_request(nic_obj) {");
        out.println("makeRequest6('',nic_obj.value);");
      	out.println("}");
			
		out.println("function nic_duplication(m_count) {");
		out.println("if(parseInt(m_count)>0){");
        out.println("alert('NIC Number Already Exist');");
		out.println("document.Form1.TXT_VAT_REG.value='';");
		out.println("}");	
		out.println("else{");
		out.println("val_nic(document.Form1.TXT_VAT_REG);");
      	out.println("}");	
		out.println("}");	
		
		/***************************END*************************************/

	//------------------------------------------------------------------------------------------------
	
			out.println("function fill_fields(data_vec){");//To fill data for field codes(new record).
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			out.println("if(document.Form1.TXT_VENDOR_CODE.value!=''){");
			
			out.println("if(data_vec.length==0){");
    
			out.println("init(j)");
			
		  out.println("j=j+1;");	
			out.println("}");
			
			out.println("else{");
			out.println("while(i<data_vec.length){");
			out.println("change1.innerHTML+='<HR><table align=\"center\" width=\"100%\" class=\"table\">'+"); 
		
			out.println("'<tr>'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_BRANCH_CODE\"  class=div_input>Branch Code</DIV></td>'+"); 
		//	out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_BRANCH_CODE_'+j+' maxlength=\"10\" size=\"10\"  onblur=makeRequest2('+j+'),check_br('+j+') value=\"'+data_vec[i+6]+'\">'+"); 
					out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_BRANCH_CODE_'+j+' maxlength=\"10\" size=\"10\"  onblur=makeRequest2('+j+') value=\"'+data_vec[i+6]+'\"  disabled >'+"); 
	

			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			/*out.println("'<tr>'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_LOCATION_CODE\"  class=div_input>Location Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_LOCATION_CODE_'+j+' maxlength=\"10\" size=\"10\" onblur=makeRequest3('+j+') value=\"'+data_vec[i+7]+'\" >'+");
			out.println("'<input class=but_input type=button name=BUT_LOCATION_CODE_'+j+' value=\"Help\" onClick=\"help_button_5('+j+')\"></td>'+"); 
			

			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");   COMMENETD BY ASHINI */
			
			
			
			
			
			
			out.println("'</table>';");
		/*	out.println("if(data_vec[i+8] ==\"Mr\" || data_vec[i+8] ==\"mr\" ){ ");
			out.println("  ");

			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=div_input>Branch Manager Title *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_TITLE_'+j+' maxlength=\"5\" size=\"1\">'+");  
			out.println("'<option value=\"Mr\" selected>Mr</option>'+");			
			out.println("'<option value=\"Mrs\" >Mrs</option>'+");		
			out.println("'<option value=\"Miss\" >Miss</option>'+");		
			out.println("'<option value=\"Dr\" >Dr</option>'+");		
			out.println("'<option value=\"Prof\" >Professor</option>'+");		
			out.println("'<option value=\"Reve\" >Rev.</option>'+");		
			out.println("'<option value=\"Othr\" >Other</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");

			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</table >';"); 
			out.println(" } ");
			
			out.println("else if(data_vec[i+8] ==\"Mrs\" || data_vec[i+8] ==\"mrs\"){ ");
			out.println("  ");


			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=div_input>Branch Manager Title *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_TITLE_'+j+' maxlength=\"5\" size=\"1\">'+");  
			out.println("'<option value=\"Mr\" >Mr</option>'+");			
			out.println("'<option value=\"Mrs\" selected>Mrs</option>'+");		
			out.println("'<option value=\"Miss\" >Miss</option>'+");		
			out.println("'<option value=\"Dr\" >Dr</option>'+");		
			out.println("'<option value=\"Prof\" >Professor</option>'+");		
			out.println("'<option value=\"Reve\" >Rev.</option>'+");		
			out.println("'<option value=\"Othr\" >Other</option>'+");		

			
			out.println("'</select>'+");
			out.println("'</td>'+");

			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</table >';"); 
			out.println(" } ");
			
			out.println("else if(data_vec[i+8] ==\"Miss\" || data_vec[i+8] ==\"miss\"){");
			out.println("  ");


			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TITLE\"  class=div_input>Branch Manager Title *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_TITLE_'+j+' maxlength=\"5\" size=\"1\">'+");  
			out.println("'<option value=\"Mr\" >Mr</option>'+");			
			out.println("'<option value=\"Mrs\" >Mrs</option>'+");		
			out.println("'<option value=\"Miss\" selected>Miss</option>'+");
			out.println("'<option value=\"Dr\" >Dr</option>'+");		
			out.println("'<option value=\"Prof\" >Professor</option>'+");		
			out.println("'<option value=\"Reve\" >Rev.</option>'+");		
			out.println("'<option value=\"Othr\" >Other</option>'+");		

			
			out.println("'</select>'+");
			out.println("'</td>'+");

			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+");
			out.println("'</table >';"); 
			out.println(" } ");

			*/			
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			/*out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_FIRST_NAME\"  class=div_input>Branch Manager First Name *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_FIRST_NAME_'+j+'  maxlength=\"200\" size=\"20\" value=\"'+data_vec[i+9]+'\"></td>'+");  //modified by nuwan de silva 29-06-29
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_LAST_NAME\"  class=div_input>Branch Manager Last Name *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_LAST_NAME_'+j+' maxlength=\"50\" size=\"20\" value=\"'+data_vec[i+10]+'\" ></td>'+");   //modified by nuwan de silva 29-06-29
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_ID_NO\" class=div_input>Branch Manager ID No </DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_ID_NO_'+j+' maxlength=\"10\" size=\"10\" value=\"'+data_vec[i+11]+'\" onblur=\"check_nic('+j+'),makeRequest5('+j+')\"></td>'+"); 
//		out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_ID_NO_'+j+' maxlength=\"10\" size=\"10\" value=\"+data_vec[i+11]+\" onblur=\"check_nic('+j+'),rem_lead_spaces('+j+'),makeRequest5('+j+'),check_id('+j+')\"></td>'+"); 
			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); */ 
			// COMMENETD BY ASHINI

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_ADDRESS\"  class=div_input>Address *</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_ADDRESS_'+j+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\" value=\"'+data_vec[i+12]+'\"></td>'+");   //modified by nuwan de silva 29-06-29
			//out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_CODE\"  class=div_input>City Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_CITY_CODE_'+j+' maxlength=\"10\" size=\"10\" onblur=makeRequest4('+j+') value=\"'+data_vec[i+13]+'\">'+"); 
			out.println("'<input class=but_input type=button name=BUT_CITY_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_CITY_DESC\"  class=div_input>City Description</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_CITY_DESC_'+j+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\" value=\"'+data_vec[i+19]+'\" disabled ></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 	
			
			//____________ added by nuwan de silva on 20-11-2007____________________________________________________
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_COUNTRY_CODE\"  class=div_input>Country Code *</DIV></td>'+"); 
			out.println("'<td width=\"30%\" ><input class=txt_input type=text name=TXT_COUNTRY_CODE_'+j+' maxlength=\"10\" size=\"10\" onblur=validate_country('+j+') value=\"'+data_vec[i+20]+'\">'+"); 
			out.println("'<input class=but_input type=button name=BUT_CITY_CODE_'+j+' value=\"Help\" onClick=\"help_country('+j+')\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_COUNTRY_DESC\"  class=div_input>Country Description</DIV></td>'+"); 
			out.println("'<td width=\"70%\" ><input class=txt_input type=text name=TXT_COUNTRY_DESC_'+j+' maxlength=\"200\" size=\"30\" style=\"{width :200px;}\" value=\"'+data_vec[i+21]+'\" disabled ></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 	
      //______________ end by nuwan de silva __________________________________________________________________
			
			
			out.println("'<tr >'+"); 
			
			
			out.println("'</table>';");
			out.println("if(data_vec[i+15]==\"N\"){");
			
			out.println(" ");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Default Value(Location)</td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_DEFAULT_VALUE1_'+j+' maxlength=\"1\" size=\"1\" >'+");  
			out.println("'<option value=\"N\" selected>No</option>'+");			
			out.println("'<option value=\"Y\" >Yes</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");
			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'</table>';");
			
			out.println("}");
			
			out.println("else if(data_vec[i+15]==\"Y\"){");
			out.println(" ");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+"); 

			out.println("'<tr >'+"); 
			out.println("'<td width=\"30%\" >Default Value(Location)</td>'+"); 
			out.println("'<td width=\"40%\" ><select class=txt_input type=text name=TXT_DEFAULT_VALUE1_'+j+' maxlength=\"1\" size=\"1\">'+");  
			out.println("'<option value=\"N\" >No</option>'+");			
			out.println("'<option value=\"Y\" selected>Yes</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");
			
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'</table>';");
			
			out.println("}");
			
		
			
			out.println("change1.innerHTML+='<HR><table align=\"center\" width=\"100%\" class=\"table\">'+"); 


			out.println("'<tr>'+"); 
			out.println("'<td>&nbsp</td>'+");
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_FAX_NO\"  class=div_input>Fax No</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_FAX_NO_'+j+' maxlength=\"60\" size=\"10\" value=\"'+data_vec[i+17]+'\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			
			out.println("'<tr >'+");

			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_TEL_NO\"  class=div_input>Telephone No *</DIV></td>'+"); 
			out.println("'<td width=\"40%\" ><input class=txt_input type=text name=TXT_TEL_NO_'+j+' maxlength=\"60\" size=\"10\" value=\"'+data_vec[i+16]+'\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_ADD_'+j+' value=\"Add\" onClick=\"Add('+j+'),disable_row('+j+')\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL_'+j+' value=\"Del\" onClick=\"Del('+j+')\"></td>'+"); 
			out.println("'</tr>'+"); 

			out.println("'<tr >'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'</table>';"); 
	
			
			out.println("if(document.Form1.SCREEN_NAME.value==\"RACT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){");
			out.println("disable_row(j)");
			out.println("}");
			
			out.println("j=j+1;");
			//out.println("i=i+20;");
		  out.println("i=i+22;");//added by nuwan de silva on 20-11-2007
			out.println("}"); 
			out.println("}"); 
			

			out.println("document.Form1.hid_no.value=parseInt(j)-1");	
			out.println("}");
			out.println("else {");
			out.println("change1.innerHTML=\"\";");
			out.println("}");
      out.println("}"); 


			
	//----------------------------------------------------------------------------------------------------------		

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"init(0),load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"0\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_use' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_data' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Creation of Vendors</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>"); // commented by udara 16-04-2015
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\" disabled ></td>");  // added by udara 16-04-2015
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
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


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 
			//out.println("<td width='30%' ><DIV id='DIV_TXT_VENDOR_CODE'  class=div_input>Vendor Code *</DIV></td>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VENDOR_CODE'  class=div_input>Vendor Code </DIV></td>"); //modified by ashini on 19-09-2007
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VENDOR_CODE' maxlength='10' size='10' onblur=\"assig('t1'),makeRequest(document.Form1.TXT_VENDOR_CODE)\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_NAME'  class=div_input>Vendor Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NAME' maxlength='200' style='width: 200px' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			//----------------------------------------------------------
			out.println("<tr >"); //Added By Sandun on 05-01-2009
			//out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_REG'  class=div_input>Vat Registration No</DIV></td>"); // commented by udara 18-03-2015
			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_REG'  class=div_input>NIC</DIV></td>");  // added by udara 18-03-2015
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG' maxlength='50' style='width: 200px' onblur=\"val_nic(this)\" size='20'></td>"); // ONBLUR FUNCTION ADDED BY KANCHANA FOR #19197
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG' maxlength='12' style='width: 200px' onblur=\"nic_duplication_request(this)\" size='20'></td>"); // ONBLUR FUNCTION ADDED BY KANCHANA FOR #19197
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			//----------------------------------------------------------
			out.println("<tr>"); 
			out.println("<td width='30%' >Item Category Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CATEGORY' maxlength='10' size='10' onblur=\"assig('t2'),makeRequest1(document.Form1.TXT_CATEGORY)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CATEGORY' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >Vendor Type</td>"); 
            // Change Done By Samitha Kulatilaka On 2011-11-24
			// out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TYPE' maxlength='10' size='10'>"); 
			out.println("<td width='30%' ><select class='txt_input' name='TXT_TYPE'>"); 
			out.println("<option value='I'>Individual</option>"); 
			out.println("<option value='C'>Corporate</option>"); 
			out.println("</select>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Default Value</td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'>");  
			out.println("<option value='N' selected>No</option>");			
			out.println("<option value='Y' >Yes</option>");		
			out.println("</select>");
			out.println("</td>");
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			
			

			out.println("<br>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=change1></div></td></tr></table>");
			
			



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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); //for small screens nic valiations in here.
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
