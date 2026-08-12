//SCREEN NAME:FOLLOWUP
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_Followup-OLD extends javax.servlet.http.HttpServlet 
{ 
	 
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{ 
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	ResultSet rs;
	String m_chksql;
	ServletOutputStream out = null;
	 
		try { 
			 
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
				 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Followup</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function makeRequest(obj) {");
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("     http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
			out.println("    try {");
			out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
			out.println("    } catch (e) {");
			out.println("        try {");
			out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("        } catch (e) {}");
			out.println("    }");
			out.println("}");
			out.println("if (!http_request) {");
			out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("    return false;");
			out.println("}");
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1); };");
			out.println("url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_followup&data_val=\"+obj.value;");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			out.println("}");

			out.println("function alertContents(http_request,count) {");
			out.println(" if (http_request.readyState == 4) {");
			out.println("    if (http_request.status == 200) {");
			out.println("      	if(http_request.responseText!=\"\"){");
			out.println(" 			m_data=http_request.responseText;");
			out.println("			if(m_data==\"Y\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("		}");
			out.println("    } else {");
			out.println("        alert('There was a problem with the request.');");
			out.println("    }");
			out.println(" }");
			out.println("}");



			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FOLLOW_UP_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FOLLOW_UP_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ACTION_TOBE_TAKEN.value==\"\"){  "); 
			out.println("DIV_TXT_ACTION_TOBE_TAKEN.style.color='red';");
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
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_save_followup';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_display_followup';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_display_followup';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_followup\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Followup - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Followup - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_ID_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_TOBE_TAKEN.disabled=true;"); 
			out.println("document.Form1.TXT_EFF_VAL_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_TOOK.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_TOOK_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_SET_FOR.disabled=true;"); 
			out.println("document.Form1.TXT_SCREEN_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ENT_REMARKS.disabled=true;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=true;"); 
			out.println("document.Form1.TXT_ACTION_ENT_DATE.disabled=true;"); 

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
			out.println("    m_sql = \"m_help_TXT_FOLLOW_UP_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FOLLOW_UP_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_FOLLOW_UP_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_ID_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ID_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_FOLLOW_UP_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_FOLLOW_UP_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_FOLLOW_UP_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_FOLLOW_UP_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_ACTION_TOBE_TAKEN.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_EFF_VAL_DATE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ACTION_TOOK.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ACTION_TOOK_DATE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_ACTION_SET_FOR.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_SCREEN_NAME.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_ENT_REMARKS.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_ACTION_ENT_DATE.value=oBj.valout[13];"); 

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Followup</td>"); 
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
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>");  
			out.println("</table>");  

			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' >Followup No *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FOLLOW_UP_NO' maxlength='16' size='16' onblur=\"makeRequest(document.Form1.TXT_FOLLOW_UP_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			/*out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>"); */
			out.println("<td width='30%' >ID No</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ID_NO' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ACTION_TOBE_TAKEN'  class=div_input>Action To Be Taken *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACTION_TOBE_TAKEN' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			/*out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); */
			out.println("<td width='30%' >Effective Value Date *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EFF_VAL_DATE' maxlength='7' size='7'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' >Action Taken *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACTION_TOOK' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			/*out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); */
			out.println("<td width='30%' >Action Taken Date *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACTION_TOOK_DATE' maxlength='7' size='7'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' >Assign User *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACTION_SET_FOR' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			/*out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' >SCREEN_NAME *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SCREEN_NAME' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' >DIVISION_CODE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DIVISION_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); */
			out.println("<td width='30%' >Enterd User Remarks *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ENT_REMARKS' maxlength='200' size='200'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' >Remarks *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REMARKS' maxlength='200' size='200'></td>"); 
			out.println("<td width='*%'></td>");
			
			out.println("<td width='30%' ></td>"); 
			out.println("<td width='40%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			
			out.println("</tr>"); 
			/*
			out.println("<tr >"); 
			out.println("<td width='1%'></td>"); 
			out.println("<td width='30%' >ACTION_ENT_DATE *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ACTION_ENT_DATE' maxlength='7' size='7'></td>"); 
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
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			out.close();
			}
			}
			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			//ServletOutputStream out = res.getOutputStream();
			//out.println(ostr.toString()); 
			//out.close();
			
			}
	}
}


