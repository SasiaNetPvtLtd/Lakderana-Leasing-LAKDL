
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - SEIZER
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MAS_display_seizer extends javax.servlet.http.HttpServlet { 
	
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
			out.println("<TITLE>System Administration - Creation Of Seizers</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function check_tel() {");
			out.println("{");
			out.println("var m_tel;");
			out.println("var m_size;");
			out.println("var telno;");
			out.println("var inputStr;");
			out.println("telno	=    document.Form1.TXT_TEL_NO.value;"); 
			out.println("telno=telno.toUpperCase();");
			out.println("m_size=telno.length;");
			
			
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = telno.charAt(i);");
			out.println("if (m_size!=10)"); 
			out.println("{");		
			out.println("alert('Entered Tel No is wrong....!');");
			out.println("document.Form1.TXT_TEL_NO.value='';");
			out.println("document.Form1.TXT_TEL_NO.focus();");
			out.println("return false;");
			out.println("break;	");
			out.println("}");
			
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Entered Tel No is wrong....!');");
			out.println("document.Form1.TXT_TEL_NO.value='';");
			out.println("document.Form1.TXT_TEL_NO.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			//out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("}");
			
			
			out.println("function check_valdity() {");
			out.println("{");
			out.println("var m_tel;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno	=    document.Form1.TXT_VALIDITY.value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			
			
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
			
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Please enter a number');");
			out.println("document.Form1.TXT_VALIDITY.value='';");
			out.println("document.Form1.TXT_VALIDITY.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			out.println("function check_monthlyfee() {");
			out.println("{");
			out.println("var m_tel;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno	=    document.Form1.TXT_MONTHLY_FEE.value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			
			
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
			
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Please enter a number');");
			out.println("document.Form1.TXT_MONTHLY_FEE.value='';");
			out.println("document.Form1.TXT_MONTHLY_FEE.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			out.println("}");
			out.println("}");
			
			out.println("}");
			
			out.println("function check_fee() {");
			out.println("{");
			out.println("var m_tel;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno	=    document.Form1.TXT_FEE_PER_CASE.value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			
			
			out.println("for (var i=0 ; i<m_size ;i++)");
			out.println("{");
			out.println("inputStr = valno.charAt(i);");
			
			out.println("if (isNaN(inputStr))");
			out.println("{");
			out.println("i++;");
			out.println("alert('Please enter a number');");
			out.println("document.Form1.TXT_FEE_PER_CASE.value='';");
			out.println("document.Form1.TXT_FEE_PER_CASE.focus();");
			out.println("return false;");
			out.println("i=m_size;");
			out.println("break;	");
			out.println("}");
			
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function get_vector(data_vec) {");
			/*out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 	
			out.println("}");*/
			
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SEIZER_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			/*out.println("document.Form1.TXT_SEIZER_CODE.value='';"); 
			out.println("document.Form1.TXT_FIRST_NAME.value='';"); 
			out.println("document.Form1.TXT_LAST_NAME.value='';"); 
			out.println("document.Form1.TXT_ADDRESS1.value='';"); 
			out.println("document.Form1.TXT_ADDRESS2.value='';"); 
			out.println("document.Form1.TXT_MOBILE_NO.value='';"); 
			out.println("document.Form1.TXT_TEL_NO.value='';"); 
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("document.Form1.TXT_FEE_PER_CASE.value='';"); 
			out.println("document.Form1.TXT_MONTHLY_FEE.value='';"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value='';"); 
			out.println("document.Form1.TXT_VALIDITY.value='';"); */
			out.println("help_update();");
			out.println("}");
			out.println("	else");
			out.println("	 if(data_vec.length>0  && document.Form1.TXT_FIRST_NAME.value!=\"\" && document.Form1.hid_val.value=='t3' && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("	 alert('Record already exists');");
			out.println("  help_update_desc();");
			out.println("	}");
			//--------------------NIC VALIDATION-------------------------
			out.println("else if(data_vec.length >0 &&  document.Form1.TXT_NIC.value!='' && document.Form1.hid_val.value=='t4'){");//document.Form1.SCREEN_NAME.value==\"NEW\" &&
			out.println("	 alert('Record already exists for '+data_vec[0]);");
			out.println("    document.Form1.TXT_NIC.value=\"\";");
			out.println("	}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SEIZER_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			//out.println("    document.Form1.TXT_NAME.value=data_vec[0];"); 
			out.println("document.Form1.TXT_SEIZER_CODE.value=data_vec[0];"); 
			out.println("document.Form1.TXT_FIRST_NAME.value=data_vec[1];"); 
			out.println("document.Form1.TXT_LAST_NAME.value=data_vec[2];"); 
			out.println("document.Form1.TXT_ADDRESS1.value=data_vec[3];"); 
			out.println("document.Form1.TXT_ADDRESS2.value=data_vec[4];"); 
			out.println("document.Form1.TXT_MOBILE_NO.value=data_vec[5];"); 
			out.println("document.Form1.TXT_TEL_NO.value=data_vec[6];"); 
			out.println("document.Form1.TXT_CITY_CODE.value=data_vec[7];"); 
			out.println("document.Form1.TXT_FEE_PER_CASE.value=data_vec[8];"); 
			out.println("format_number(document.Form1.TXT_FEE_PER_CASE,20)");
			out.println("document.Form1.TXT_MONTHLY_FEE.value=data_vec[9];"); 
			out.println("format_number(document.Form1.TXT_MONTHLY_FEE,20)");
			out.println("document.Form1.TXT_DEFAULT_VALUE.value=data_vec[10];"); 
			out.println("document.Form1.TXT_VALIDITY.value=data_vec[11];"); 
			out.println("document.Form1.TXT_NIC.value=data_vec[12];"); //nic
			out.println("}");
			
			out.println("else");
			out.println("if(data_vec.length==0 && document.Form1.hid_val.value=='t2' && document.Form1.TXT_CITY_CODE.value!=''){");
			//out.println("help_button_1();");
			out.println("help_button_2();"); //mododfed by nwuan de silva 20-07-07
			out.println("	}");
			
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			
			out.println("    if(document.Form1.hid_val.value=='t1' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_seizer_code&data_val=\"+obj.value;");
			
			out.println("}");
			out.println("    else");
			out.println("    if(document.Form1.hid_val.value=='t1' && document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_seizer&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			//out.println("   window.open(m_url);");
			out.println("}");
			
			out.println("    else");
			out.println("    if(document.Form1.hid_val.value=='t3'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_seizer_desc&data_val=\"+obj.value;");
			out.println("}");
			
			//---------------added milinda for validate nic-----------------
			out.println(" else");
			out.println("    if(document.Form1.hid_val.value=='t4'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_nic&data_val=\"+obj.value;");
			//out.println("window.open(m_url);");
			out.println("}");
			
			
			//	out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");
			
			out.println("function makeRequest1(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
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
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			// commented by udara 24-10-2013
			/*
			out.println("if(document.Form1.TXT_SEIZER_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SEIZER_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			*/
			out.println("if(document.Form1.TXT_FIRST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FIRST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LAST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_LAST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS1.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS1.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS2.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS2.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MOBILE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_MOBILE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			// commented by udara 10-12-2013
			/*
			out.println("else if(document.Form1.TXT_TEL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_TEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			*/
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CITY_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_NIC.value==\"\"){  "); 
			out.println("DIV_TXT_NIC.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			
			// commented by udara 10-12-2013
			/*
			out.println("else if(document.Form1.TXT_FEE_PER_CASE.value==\"\"){  "); 
			out.println("DIV_TXT_FEE_PER_CASE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MONTHLY_FEE.value==\"\"){  "); 
			out.println("DIV_TXT_MONTHLY_FEE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			*/
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_seizer';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); // commented by udara 19-11-2019
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_seizer';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_seizer';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_seizer\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Creation Of Seizers - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Creation Of Seizers - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.TXT_SEIZER_CODE.disabled=true;"); // udara 28-10-2013
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_LAST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FEE_PER_CASE.disabled=true;"); 
			out.println("document.Form1.TXT_MONTHLY_FEE.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 
			out.println("document.Form1.BUT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_VALIDITY.disabled=true;"); 
			
			//out.println("document.Form1.TXT_SEIZER_CODE.disabled=true;"); // added by udara 24-10-2013
			
			out.println("}"); 
			
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");
			//out.println("document.Form1.TXT_SEIZER_CODE.disabled=true;"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Deactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			
			out.println("if(m_val==\"DACT\"){"); 
			out.println("   document.Form1.TXT_SEIZER_CODE.disabled=false;"); 
			out.println("}");
			out.println("if(m_val==\"RACT\"){"); 
			out.println("   document.Form1.TXT_SEIZER_CODE.disabled=false;"); 
			out.println("}"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("   document.Form1.TXT_SEIZER_CODE.disabled=true;"); 
			out.println("}"); 
			out.println("if(m_val==\"EDIT\"){"); 
			out.println("   document.Form1.TXT_SEIZER_CODE.disabled=false;"); 
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
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else"); 
			
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
			
			out.println("function clear_data() {");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("document.Form1.TXT_SEIZER_CODE.value='';");
			out.println("document.Form1.TXT_SEIZER_CODE.focus();");
			out.println("}");
			//modified by nuwan de silva 20-07-07----------------------
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("document.Form1.TXT_CITY_CODE.value='';");
			out.println("}");
			
			
			out.println("}");
			
			out.println(""); 
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_SEIZER_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SEIZER_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_SEIZER_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_SEIZER_CODE_sql_New\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_SEIZER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_SEIZER_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','8');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_SEIZER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_MOBILE_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_FEE_PER_CASE.value=oBj.valout[10];"); 
			out.println("format_number(document.Form1.TXT_FEE_PER_CASE,20)");
			out.println("    document.Form1.TXT_MONTHLY_FEE.value=oBj.valout[11];"); 
			out.println("format_number(document.Form1.TXT_MONTHLY_FEE,20)");
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[12];");
			out.println("    document.Form1.Hid_payee_code.value=oBj.valout[14];"); //added by Sandun on 31-07-2008
			out.println("    document.Form1.TXT_NIC.value=oBj.valout[15];");
			
			out.println("if(oBj.valout[13]==0);{");
			out.println("document.Form1.TXT_VALIDITY.value='';");
			out.println("}");
			//out.println("alert(oBj.valout[13])");
			out.println("if(oBj.valout[13]!=0);{");
			//out.println("alert('DG')");
			out.println("document.Form1.TXT_VALIDITY.value=oBj.valout[13];");
			out.println("}");
			
			out.println("}"); 
			
			
			
			out.println("function help_value_assign_3() {"); 
			//out.println("    document.Form1.TXT_AREA_DESC.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=\"\";"); 
			out.println("    document.Form1.TXT_FIRST_NAME.focus();"); 
			out.println("}"); 
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_SEIZER_DESC_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FIRST_NAME.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
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
			//out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:50em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:50em; dialogHeight:25em; center:yes; status:yes\");"); 
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
			// commented by udara 22-11-2019
			/*
			out.println("    m_sql = \"m_view_TXT_SEIZER_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_SEIZER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			*/
			
			out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_seizer_list\";");
			out.println("    	window.open(m_url); ");
			
			out.println("}"); 
			
			out.println("function val_num(obj,size,obj2){");
			out.println("if(isnumberok(obj,size)){");
			out.println("format_number(obj,size)");
			out.println("if(obj.value!=\"0.00\")");
			out.println("obj2.value=\"0.00\"");
			out.println("}"); 
			out.println("else");
			out.println("if(obj.value!=\"\"){");
			out.println("alert('Please enter a number');");
			out.println("obj.value=\"\"");
			out.println("}"); 
			out.println("}"); 
			
			out.println("function chk_num(obj1,obj2){");
			out.println("if(obj1.value==\"\" && obj2.value==\"\" ){"); 
			out.println("alert(\"Please enter either 'fee per case' or 'monthly fee'\")");
			out.println("}"); 
			out.println("else if((obj1.value!=\"\" && obj1.value!=\"0.00\")  && (obj2.value!=\"\" && obj2.value!=\"0.00\") ){"); 
			out.println("alert(\"Please enter either 'fee per case' or 'monthly fee'\")");
			out.println("obj1.value=\"\";");
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('NEW')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_payee_code' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Creation Of Seizers</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
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
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_SEIZER_CODE'  class=div_input>Seizer Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SEIZER_CODE' maxlength='25' size='10' onblur=\"assig('t1'),makeRequest(document.Form1.TXT_SEIZER_CODE)\" disabled>"); // mod by udara 24-10-2013
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_FIRST_NAME'  class=div_input>First Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' maxlength='200' style=\"width:250px;\" size='20' onblur=\"assig('t3'),makeRequest(document.Form1.TXT_FIRST_NAME)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_LAST_NAME'  class=div_input>Last Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LAST_NAME' maxlength='50' style=\"width:250px;\" size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_ADDRESS1'  class=div_input>Address1 *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS1' maxlength='100' style=\"width:250px;\" size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_ADDRESS2'  class=div_input>Address2 *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS2' maxlength='100' style=\"width:250px;\" size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			//------------------ADDED NIC ##18430 MILINDA ON 2015-10-22------------------------------
			out.println("<td width='20%' ><DIV id='DIV_TXT_NIC'  class=div_input>Nic No *</DIV></td>"); 
	//		out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NIC' maxlength='12' size='10' onblur=\"check_blanks(document.Form1.TXT_NIC),assig('t4'),makeRequest(document.Form1.TXT_NIC)\" onchange=\"val_nic(this)\" ></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NIC' maxlength='12' size='10' onblur=\"assig('t4'),makeRequest(document.Form1.TXT_NIC),val_nic(this)\" ></td>"); // added val_nic function and removed check_blanks by Kanchana.  onchange=\"val_nic_for_nicfield(this)\"
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_MOBILE_NO'  class=div_input>Mobile No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MOBILE_NO' maxlength='30' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_TEL_NO'  class=div_input>Telephone No </DIV></td>");  // out.println("<td width='20%' ><DIV id='DIV_TXT_TEL_NO'  class=div_input>Telephone No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEL_NO' maxlength='60' size='10';></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"assig('t2'),makeRequest1(document.Form1.TXT_CITY_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_CITY_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_FEE_PER_CASE'  class=div_input>Fee Per Case </DIV></td>"); // out.println("<td width='20%' ><DIV id='DIV_TXT_FEE_PER_CASE'  class=div_input>Fee Per Case *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FEE_PER_CASE' maxlength='18' size='22' onblur=\"val_num(document.Form1.TXT_FEE_PER_CASE,20,document.Form1.TXT_MONTHLY_FEE),chk_num(document.Form1.TXT_MONTHLY_FEE,document.Form1.TXT_FEE_PER_CASE)\"; STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_MONTHLY_FEE'  class=div_input>Monthly Fee </DIV></td>"); // out.println("<td width='20%' ><DIV id='DIV_TXT_MONTHLY_FEE'  class=div_input>Monthly Fee *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MONTHLY_FEE' maxlength='18' size='22' onblur=\"val_num(document.Form1.TXT_MONTHLY_FEE,20,document.Form1.TXT_FEE_PER_CASE),chk_num(document.Form1.TXT_MONTHLY_FEE,document.Form1.TXT_FEE_PER_CASE)\"; STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' >Default Value</td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'>");  
			out.println("<option value='N' selected>No</option>");			
			out.println("<option value='Y' >Yes</option>");		
			out.println("</select>");
			out.println("</td>");
			out.println("</tr >");
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALIDITY'  class=div_input>Validity Period(Days)</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VALIDITY' maxlength='3' size='3' onblur=\"check_valdity()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALIDITY'  class=div_input>Vat Reg No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG_NO' maxlength='20' size='3' onblur=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALIDITY'  class=div_input>WHT</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_WHT' maxlength='5' size='3' onblur=\"check_number_precent(this,3)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
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
