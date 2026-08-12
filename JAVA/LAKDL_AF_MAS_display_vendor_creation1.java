
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - VENDOR DETAILS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_vendor_creation1 extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Vendor Details</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var y=0;");
			out.println("var val_of;");
			out.println("var val_of1;");
			out.println("var x=0;");
			out.println("var t=0;");
			out.println("var branch_arry=new Array();");
			out.println("var sec_arry=new Array();");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("}");
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_vendor_creation&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_VENDOR_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VENDOR_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_BRANCH_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_BRANCH_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TEL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_TEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_COL_609.value==\"\"){  "); 
			out.println("DIV_TXT_COL_609.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_BRANCH.value==\"\"){  "); 
			out.println("DIV_TXT_BRANCH.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LOCATION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TITLE.value==\"\"){  "); 
			out.println("DIV_TXT_TITLE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FIRST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FIRST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LAST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_LAST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ID_NO.value==\"\"){  "); 
			out.println("DIV_TXT_ID_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CITY_CODE.style.color='red';");
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
			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_MAS_save_vendor_creation';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_vendor_creation';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_vendor_creation';"); 
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
			out.println("help_box.innerHTML=\" System Administration - Vendor Details - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Vendor Details - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_CATEGORY.disabled=true;"); 
			out.println("document.Form1.TXT_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 
			out.println("document.Form1.TXT_BRANCH_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_COL_609.disabled=true;"); 
			out.println("document.Form1.TXT_BRANCH.disabled=true;"); 
			out.println("document.Form1.TXT_LOCATION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_LAST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_ID_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
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

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

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
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_COL_609.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_BRANCH.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_TITLE.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_ADDRESS.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[19];"); 

			out.println("}"); 
//----------------------------
			out.println("function Add_Loc(y) {");
			out.println("x=0;");
			out.println("t=0;");
			out.println("val_of=document.Form1.hid_no.value;");
			out.println("var br_code;");
			out.println("br_code='TXT_BRANCH_CODE_'+y;");
			out.println("brcode=document.Form1.elements[br_code].value;");
			out.println("for(var i=0;i<val_of;i++){");
			out.println("br_code1='TXT_BRANCH_CODE_'+i");
			out.println("brcode1=document.Form1.elements[br_code1].value;");

	out.println("if(brcode==brcode1)");
		
				out.println("{");
				out.println("alert('Entered Account No already exits...!');");
				out.println("document.Form1.elements[br_code].value='';");
								out.println("document.Form1.elements[br_code].focus();");

					out.println("return false;");
								out.println("break;");
			out.println("	}");
				
				
		out.println("	}");		
			
			
			
			
			out.println("y=y+1;");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\">BRANCH_CODE *</td>'+"); 
			out.println("'<td >TEL_NO *</td>'+"); 
			out.println("'<td >FAX_NO *</td>'+"); 
			out.println("'<td >COL_609 *</td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE_'+y+' maxlength=\"10\" size=\"10\" >'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_BRANCH_CODE_'+y+' value=\"Help\" onClick=\"help_button_3()\"></td>'+"); 
		
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 

			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 

			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_COL_609_'+y+' maxlength=\"10\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ADD_L_'+y+' value=\"Add_L\" onClick=\"Add_Loc('+y+')\"></td>'+"); 
		 	out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_DEL_L_'+y+' value=\"Del_L\" onClick=\"Del_Loc('+y+')\"></td>'+");
			out.println("'<td width=\"*%\"></td>'+");
			out.println("'</tr>'+"); 
			out.println("'<tr >'+");
			out.println("'<td ><input class=\"but_input\" type=\"button\" name=BUT_ADD_C_'+y+' value=\"Add_C\" onClick=\"Add_Con('+x+')\">'+"); 
			out.println("' <input class=\"but_input\" type=\"button\" name=BUT_DEL_C_'+y+' value=\"Del_C\" onClick=\"Del_Con('+y+')\"></td></tr ></table>';");

			
			//out.println("alert(y);");
			
			out.println("document.Form1.hid_no.value=y;");
			//out.println("x=x+1;");
			out.println("}");
					
			//------
		
			
			//-----------
			
			out.println("function Add_Con(x) {"); 
			out.println("alert(x);");
			//out.println("for(var i=0;i<=
			//out.println("change1.innerHTML=''");
			out.println("var t=x+1;");
			out.println("change2.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+"); 
			out.println("'<tr>'+");
			
			out.println("'<td width=\"30%\">BRANCH *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+x+' value='+x+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 

			/*out.println("'<td >LOCATION_CODE *</td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CODE_'+x+' maxlength=\"10\" size=\"10\" value=TXT_LOCATION_CODE_'+x+'>'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_LOCATION_CODE_'+x+' value=\"Help\" onClick=\"help_button_4()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 

			out.println("'<td >TITLE *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_TITLE_'+x+' maxlength=\"5\" size=\"5\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >FIRST_NAME *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_FIRST_NAME_'+x+' maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >LAST_NAME *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_LAST_NAME_'+x+' maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >ID_NO *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_ID_NO_'+x+' maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >ADDRESS *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS_'+x+' maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 

			out.println("'<td >CITY_CODE *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_CITY_CODE_'+x+' maxlength=\"10\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CITY_CODE_'+x+' value=\"Help\" onClick=\"help_button_5()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >Default Value</td>'+"); 
			out.println("'<td ><select class=\"txt_input\" type=\"text\" name=TXT_DEFAULT_VALUE_'+x+' maxlength=\"1\" size=\"1\">'+");  
			out.println("'<option value=\"N\" selected>No</option>'+");			
			out.println("'<option value=\"Y\" >Yes</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");*/
			out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_ADD_C1_'+t+' value=\"Add_C\" onClick=\"Add_Con('+t+')\"></td>'+");
			out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_DEL_C_'+t+' value=\"Del_C\" onClick=\"Del_Con('+x+')\"></td>'+");
			out.println("'<td width=\"*%\"></td>'+");
		
			//out.println("<td width='100%'><div id=change1></div></td>");
			//out.println("'<tr><td width=\"100%\"><div id=change2></div></td></tr>'+");
		out.println("'</table>';"); 
			
		
//out.println("alert(y);");
			out.println("x=x+1;");
			out.println("document.Form1.hid_no1.value=t;");
			out.println("alert('ttt'+x);");
			out.println("}"); 



			out.println("function Del_Loc(y) {");
			out.println("alert('y'+y)");
			out.println("var e=0;");
			out.println("val_of=document.Form1.hid_no.value;");
			out.println("alert('val_of'+val_of)");
			out.println("for(var i=0;i<=val_of;i++){");
			
			out.println("m_b_code='TXT_BRANCH_CODE_'+i;");
			out.println("alert('b'+m_b_code)");
			out.println("if(y==i)");
			out.println("{");
			out.println("continue;");
			out.println("}");
			out.println("if(document.Form1.elements[m_b_code].value=='')");
			out.println("{");
			out.println("branch_arry[e]=0;");
			//acc_arry[e]=0;
			out.println("alert(branch_arry[e])");
			out.println("}");
			
			out.println("else{");
			out.println("alert(branch_arry[e])");
			out.println("branch_arry[e]=document.Form1.elements[m_b_code].value;");
			//acc_arry[e]=document.form2.elements[m_acc].value;
			out.println("e=e+1;");
			out.println("}");
			
		out.println("}");
			
			out.println("val_of=val_of-1;");
			out.println("alert('pd'+val_of)");
			out.println("change1.innerHTML='';");
			out.println("get_val(val_of)");
			out.println("document.Form1.hid_no.value=val_of;");
			//out.println("}");
	
			
			out.println("}"); 

		out.println("function Del_Con(x) {");
			out.println("alert('1x'+x)");
			out.println("var e=0;");
			out.println("val_of1=document.Form1.hid_no1.value;");
			out.println("alert('val_of1'+val_of1)");
			/*out.println("for(var i=0;i<=val_of1;i++){");
			
			out.println("m_b='TXT_BRANCH_'+i;");
			out.println("alert('b'+m_b)");
			out.println("if(x==i)");
			out.println("{");
			out.println("continue;");
			out.println("}");
			out.println("if(document.Form1.elements[m_b].value=='')");
			out.println("{");
			out.println("sec_arry[e]=0;");
			//acc_arry[e]=0;
			out.println("alert(sec_arry[e])");
			out.println("}");
			
			out.println("else{");
			out.println("alert(sec_arry[e])");
			out.println("sec_arry[e]=document.Form1.elements[m_b].value;");
			//acc_arry[e]=document.form2.elements[m_acc].value;
			out.println("e=e+1;");
			out.println("}");
			
		out.println("}");*/
			
			out.println("val_of1=val_of1-1;");
			out.println("alert('pd'+val_of1)");
			out.println("change2.innerHTML='';");
			out.println("get_con(val_of1)");
			out.println("document.Form1.hid_no1.value=val_of1;");
			//out.println("}");
	
			
			out.println("}"); 



			out.println("function Init() {");
			out.println("change1.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\">BRANCH_CODE *</td>'+"); 
			out.println("'<td >TEL_NO *</td>'+"); 
			out.println("'<td >FAX_NO *</td>'+"); 
			out.println("'<td >COL_609 *</td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE_'+y+' maxlength=\"10\" size=\"10\" >'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_BRANCH_CODE_'+y+' value=\"Help\" onClick=\"help_button_3()\"></td>'+"); 
		
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 

			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO_'+y+' maxlength=\"10\" size=\"10\"></td>'+"); 

			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_COL_609_'+y+' maxlength=\"10\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ADD_L_'+y+' value=\"Add_L\" onClick=\"Add_Loc('+y+')\"></td>'+"); 
		 	out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_DEL_L_'+y+' value=\"Del_L\" onClick=\"Del_Loc('+y+')\"></td>'+");
			out.println("'<td width=\"*%\"></td>'+");
			out.println("'</tr>'+"); 
			out.println("'<tr >'+");
//-------------		
			out.println("'<td width=\"30%\">BRANCH *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+x+' value='+x+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 
			
//------------------
			
			out.println("'<td ><input class=\"but_input\" type=\"button\" name=BUT_ADD_C_'+x+' value=\"Add_C\" onClick=\"Add_Con('+x+')\">'+"); 
			out.println("'  <input class=\"but_input\" type=\"button\" name=BUT_DEL_C_'+x+' value=\"Del_C\" onClick=\"Del_Con('+x+')\"></td></tr ></table>';"); 
			
			
			out.println("document.Form1.hid_no.value=y;");
			out.println("document.Form1.hid_no1.value=x;");
			out.println("}");
			
			
			out.println("function get_val(val) {");
			out.println("alert('pg'+val)");
			out.println("for(var i=0;i<=val;i++){");
			
			out.println("change1.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr>'+"); 
			out.println("'<td width=\"30%\">BRANCH_CODE *</td>'+"); 
			out.println("'<td >TEL_NO *</td>'+"); 
			out.println("'<td >FAX_NO *</td>'+"); 
			out.println("'<td >COL_609 *</td>'+"); 
			out.println("'</tr>'+"); 
			
			out.println("'<tr >'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_CODE_'+i+' maxlength=\"10\" size=\"10\" value='+branch_arry[i]+'>'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_BRANCH_CODE_'+i+' value=\"Help\" onClick=\"help_button_3()\"></td>'+"); 
		
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO_'+i+' maxlength=\"10\" size=\"10\"></td>'+"); 

			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_FAX_NO_'+i+' maxlength=\"10\" size=\"10\"></td>'+"); 

			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_COL_609_'+i+' maxlength=\"10\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ADD_L_'+i+' value=\"Add_L\" onClick=\"Add_Loc('+i+')\"></td>'+"); 
		 	out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_DEL_L_'+i+' value=\"Del_L\" onClick=\"Del_Loc('+i+')\"></td>'+");
			out.println("'<td width=\"*%\"></td>'+");
			out.println("'</tr>'+"); 
			out.println("'<tr >'+");
			out.println("'<td ><input class=\"but_input\" type=\"button\" name=BUT_ADD_C_'+i+' value=\"Add_C\" onClick=\"Add_Con('+i+')\"></td></tr ></table>';"); 
			out.println("}");
			out.println("document.Form1.hid_no.value=val;");
			
			out.println("}");
			
			out.println("function get_con(val) {");
			out.println("alert('psf'+val)");
			out.println("for(var i=0;i<val;i++){");
			
			out.println("change2.innerHTML+='<table align=\"center\" border=\"1\" width=\"100%\" class=\"table\">'+"); 
			out.println("'<tr>'+");
			
			out.println("'<td width=\"30%\">BRANCH *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_BRANCH_'+i+' value='+i+' maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 

			/*out.println("'<td >LOCATION_CODE *</td>'+"); 
			out.println("'<td  ><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION_CODE_'+i+' maxlength=\"10\" size=\"10\" value=TXT_LOCATION_CODE_'+i+'>'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_LOCATION_CODE_'+i+' value=\"Help\" onClick=\"help_button_4()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 

			out.println("'<td >TITLE *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_TITLE_'+i+' maxlength=\"5\" size=\"5\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >FIRST_NAME *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_FIRST_NAME_'+i+' maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >LAST_NAME *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_LAST_NAME_'+i+' maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >ID_NO *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_ID_NO_'+i+' maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >ADDRESS *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS_'+i+' maxlength=\"20\" size=\"20\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr>'+"); 

			out.println("'<td >CITY_CODE *</td>'+"); 
			out.println("'<td ><input class=\"txt_input\" type=\"text\" name=TXT_CITY_CODE_'+i+' maxlength=\"10\" size=\"10\">'+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_CITY_CODE_'+i+' value=\"Help\" onClick=\"help_button_5()\"></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<tr >'+"); 

			out.println("'<td >Default Value</td>'+"); 
			out.println("'<td ><select class=\"txt_input\" type=\"text\" name=TXT_DEFAULT_VALUE_'+i+' maxlength=\"1\" size=\"1\">'+");  
			out.println("'<option value=\"N\" selected>No</option>'+");			
			out.println("'<option value=\"Y\" >Yes</option>'+");		
			out.println("'</select>'+");
			out.println("'</td>'+");*/
			out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_ADD_C1_'+i+' value=\"Add_C\" onClick=\"Add_Con('+i+')\"></td>'+");
			out.println("'<td><input class=\"but_input\" type=\"button\" name=BUT_DEL_C_'+i+' value=\"Del_C\" onClick=\"Del_Con('+i+')\"></td>'+");
			out.println("'<td width=\"*%\"></td>'+");
			out.println("'</table>';"); 
			out.println("}");
			out.println("document.Form1.hid_no1.value=val;");
			
			out.println("}");
			
			
			
			
//----------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"Init(),load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no1' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Vendor Details</td>"); 
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

			out.println("<td width='30%' ><DIV id='DIV_TXT_VENDOR_CODE'  class=div_input>VENDOR_CODE *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VENDOR_CODE' maxlength='10' size='10' onblur=\"makeRequest(document.Form1.TXT_VENDOR_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_NAME'  class=div_input>NAME *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >CATEGORY</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CATEGORY' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CATEGORY' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >TYPE</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TYPE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_TYPE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
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
			
			//------------
			/*out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='30%'><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>BRANCH_CODE *</DIV></td>"); 
			out.println("<td ><DIV id='DIV_TXT_TEL_NO'  class=div_input>TEL_NO *</DIV></td>"); 
			out.println("<td ><DIV id='DIV_TXT_FAX_NO'  class=div_input>FAX_NO *</DIV></td>"); 
			out.println("<td ><DIV id='DIV_TXT_COL_609'  class=div_input>COL_609 *</DIV></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10'><input "); 
			out.println("class='but_input' type='button' name='BUT_TXT_BRANCH_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
		
			out.println("<td ><input class='txt_input' type='text' name='TXT_TEL_NO' maxlength='10' size='10'></td>"); 

			out.println("<td ><input class='txt_input' type='text' name='TXT_FAX_NO' maxlength='10' size='10'></td>"); 

			out.println("<td ><input class='txt_input' type='text' name='TXT_COL_609' maxlength='10' size='10'><input "); 
			out.println("class='but_input' type='button' name='BUT_ADD_L' value=\"Add_L\" onClick=\"Add_Loc('+y+')\"></td>"); 
		
			out.println("<td><input class='but_input' type='button' name='BUT_DEL_L' value=\"Del_L\" onClick=\"Del_Loc('+y+')\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >");
			out.println("<td ><input class='but_input' type='button' name='BUT_ADD_C' value=\"Add_C\" onClick=\"Add_Con()\"></td>"); 
			out.println("</tr >");*/
			//out.println("</table>"); 
			//out.println("<br>"); 

//************************************************************************************
			
			/*out.println("<table align='center' border=\"1\" width='100%' class='table'>"); 
			out.println("<tr>");
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH'  class=div_input>BRANCH *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>LOCATION_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TITLE'  class=div_input>TITLE *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TITLE' maxlength='5' size='5'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_FIRST_NAME'  class=div_input>FIRST_NAME *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LAST_NAME'  class=div_input>LAST_NAME *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LAST_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ID_NO'  class=div_input>ID_NO *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ADDRESS'  class=div_input>ADDRESS *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>CITY_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DEFAULT_VALUE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); */


//------------------------------------------------------------------------------------



//---------THESE ARE FOR LOCATION-------------------------
	/*		out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>BRANCH_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_BRANCH_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TEL_NO'  class=div_input>TEL_NO *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >FAX_NO *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FAX_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_COL_609'  class=div_input>COL_609 *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COL_609' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); */
//---------------------------------------------------

//---------FOR LOCATION CANTACTS---------------------
/*
			out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH'  class=div_input>BRANCH *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>LOCATION_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TITLE'  class=div_input>TITLE *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TITLE' maxlength='5' size='5'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_FIRST_NAME'  class=div_input>FIRST_NAME *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LAST_NAME'  class=div_input>LAST_NAME *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LAST_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ID_NO'  class=div_input>ID_NO *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ADDRESS'  class=div_input>ADDRESS *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>CITY_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >DEFAULT_VALUE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			*/
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change2></div></td>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");
			//out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			//out.println("<td width='100%'><div id=change1></div></td>");
			//out.println("<td width='100%'><div id=change2></div></td></tr></table>");
			
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
