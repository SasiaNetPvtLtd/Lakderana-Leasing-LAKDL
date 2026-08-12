
//ID         :1.6 POSTAL CODE CREATION PROCESS
//SCREEN NAME:SYSTEM ADMINISTRATION - POSTAL CODES
//CREATED BY :NUWAN DE SILVA
//DATE/TIME  :20-07-2006
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_postal_codes extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Postal Codes</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already Exists.');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M3'){");
			out.println("				alert('Record already exists.');"); 
			out.println("       help_new_desc();");
			out.println("    document.Form1.TXT_DESCRIPTION.value=\"\" ;"); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_POSTAL_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			//out.println("   alert('Selected Postal code is incorrect,use help...!')");
			//out.println("   document.Form1.TXT_POSTAL_CODE.value=\"\" ;");
			out.println("   help_update();");
		//	out.println("				alert('Invalid Record, Use Help');");
			//out.println("       document.Form1.TXT_POSTAL_CODE.value=\"\" "); 
			//out.println("       document.Form1.TXT_POSTAL_CODE.focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_CITY_CODE.value!=\"\"){");
			//out.println("   alert('Selected City code is incorrect,use help...!')");
			//out.println("     document.Form1.TXT_CITY_CODE.value=\"\"; ");
			out.println("     help_button_1();");
		//	out.println("				alert('Invalid Record, Use Help');");
		//	out.println("       document.Form1.TXT_CITY_CODE.value=\"\" "); 
		//	out.println("       document.Form1.TXT_CITY_CODE.focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_POSTAL_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("    document.Form1.TXT_POSTAL_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_CITY_DESC.value=data_vec[4];"); 
      out.println("			}");
			
			out.println("}");
			
			
		/*	out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");*/
			
			out.println("function makeRequest(obj) {");
			
			//out.println("alert(document.Form1.hid_chk_status.value);");
			
			out.println("    if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\") {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes1&data_val=\"+obj.value;");
			out.println("}");
			
			out.println("    else if((document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"RACT\")&&(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"NEW\")) {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			
    	out.println("    else if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\") {");
      out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("}");		
			
			out.println("    else if(document.Form1.hid_chk_status.value == 'M2' && (document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"NEW\") ) {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");		
			
			//out.println("    else if(document.Form1.hid_chk_status.value=='M3' && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_DESCRIPTION.value==\"\") {");
			//out.println("    alert('Please Enter Postal Code Description.');");
			//out.println("    document.Form1.TXT_DESCRIPTION.focus();");
			//out.println("}");
			
			out.println("    else if(document.Form1.hid_chk_status.value=='M3' && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_DESCRIPTION.value!=\"\") {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes_desc&data_val=\"+obj.value;");
			out.println("}");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_POSTAL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_POSTAL_CODE.style.color='red';");
			//out.println("alert('Please Enter Postal Code.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DESCRIPTION.value==\"\"){  "); 
			out.println("DIV_TXT_DESCRIPTION.style.color='red';");
			//out.println("alert('Please Enter Postal Code Description.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CITY_CODE.style.color='red';");
			//out.println("alert('Please Enter City Code.'); ");
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
			out.println("else if(m_option=='Deactivate') {");
			out.println("m_sav_msg = 'Are you sure you want to Deactivate?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Reactivate') {");
			out.println("m_sav_msg = 'Are you sure you want to Reactivate?'; ");
			out.println("		}"); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(m_sav_msg)){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_postal_codes';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_postal_codes';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_postal_codes';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_postal_codes\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Postal Codes - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Postal Codes - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_DESCRIPTION.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_DESC.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 

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
			out.println("		if(document.Form1.hid_help_type.value==\"33\"){"); 
			out.println("		help_new_desc_value_assign_33();"); 
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
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("Close();"); 
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
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CITY_DESC.value=oBj.valout[3];"); 
			out.println("}"); 

			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_POSTAL_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_POSTAL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_POSTAL_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			
			out.println("function help_new_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"33\";"); 
			out.println("    m_sql = \"m_help_TXT_POSTAL_CODE_NEW_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\";"); 
			out.println("    } ");
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
      
			out.println("function help_new_desc_value_assign_33() {"); 
			//out.println("    document.Form1.TXT_DESCRIPTION.value=\"\" ;"); 
			//out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
			out.println("}");
			
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[5];"); 
      out.println("    assignState('M1');makeRequest(document.Form1.TXT_POSTAL_CODE);"); 
			out.println("}");
			
			
			out.println("function clear_data() {");//**
			//------modified by : delanjali---------------------------------------------------------------------------------------
			//------date				:	2007-07-18--------------------------------------------------------------------------------------
			//------ref No		  :	578--------------------------------------------------------------------------------------
			
			/*out.println("if( document.Form1.hid_chk_status.value == 'M1' && document.Form1.SCREEN_NAME.value!=\"NEW\") {");//**
			out.println("document.Form1.TXT_POSTAL_CODE.value='';"); 
			out.println("document.Form1.TXT_POSTAL_CODE.focus() ;"); 
			out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';"); 
			out.println(" }");*/
			//--------------------------------------------------------------------------------------------------------------------
			out.println("if(document.Form1.hid_help_type.value == '99') {");//**
			out.println("document.Form1.TXT_POSTAL_CODE.value='';"); 
			out.println("document.Form1.TXT_POSTAL_CODE.focus() ;"); 
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("document.Form1.TXT_CITY_DESC.value='';"); 
			out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';"); 
			out.println(" }");
			out.println("if(document.Form1.hid_help_type.value == '1') {");//**
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("document.Form1.TXT_CITY_DESC.value='';"); 
			out.println("document.Form1.TXT_CITY_CODE.focus() ;"); 
			out.println(" }");
			out.println("}");
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      //out.println("window.close();"); /Comment by Chandana on 15/05/2007
      out.println(" }");
			
			
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
			out.println("    m_sql = \"m_help_TXT_POSTAL_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_POSTAL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Postal Codes</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"Deactivate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Reactivate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 
      
      out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_POSTAL_CODE'  class=div_input>Postal Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_POSTAL_CODE' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_POSTAL_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_DESCRIPTION'  class=div_input>Postal Code Description *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESCRIPTION' maxlength='50' size='50' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_DESCRIPTION)\" style='{width:180}' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_CITY_CODE)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CITY_DESC'  class=div_input>City Description</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_DESC' maxlength='20' size='20' onblur=\"\" style='{width:180}' disabled >"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
      out.println("<tr >"); 
			out.println("<td width='30%' >Default Value</td>"); 
			out.println("<td width='40%' ><select name='TXT_DEFAULT_VALUE' class='txt_input'>");
			out.println("<option value=\"Y\" >Yes</option>");
			out.println("<option value=\"N\" SELECTED>No </option>");
			out.println("</select>");
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
