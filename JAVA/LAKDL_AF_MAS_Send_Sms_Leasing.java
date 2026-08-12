
//ID         :1.80 SUB DIVISION CREATION PROCESS
//SCREEN NAME:SYSTEM ADMINISTRATION - SUB DIVISION
//CREATED BY:NUWAN DE SILVA
//DATE/TIME : 24-07-06
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;


public class LAKDL_AF_MAS_Send_Sms_Leasing extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_class_url_2=m_sn_methods.servlet_client_url.trim()+":"+"8087/lakdllive/servlet"; // added by udara 02-10-2018
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Send Sms</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function get_vector(data_vec) {");
			
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			
			out.println("}");
			
			out.println("function assignState(val){");
			
			out.println("}");
			
			out.println("function validate_data(){"); 
			
			out.println("DIV_TXT_TO_NUM.style.color='black';");
			out.println("if(document.Form1.TXT_TO_NUM.value==\"\"){  "); 
			out.println("DIV_TXT_TO_NUM.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function before_submit(obj){ "); 
			
			out.println("	get_display_msg();");
			out.println("	if(obj==\"SB\" || validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\" )){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("	            document.Form1.hid_send_type.value=obj;");	
			//out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_SMS_generation_save';");  
			
			//out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_SMS_generation_save_2';"); // dev
			out.println("				document.Form1.action='"+m_class_url_2+"/"+m_fschema_name+"AF_SMS_generation_save_2';"); // live
		
			//out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_SMS_generation_save_3';"); 
			out.println("				document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("} "); 
			
			

			
			
		    out.println("function sendingsms2(obj){ "); 
			out.println("	get_display_msg();");
			out.println("	if(obj==\"SB\" || validate_data()){"); 
			
			out.println(" var url = \"http://groupsms.etisalat.lk/sendsms.php?\";");
			out.println(" var request = new XMLHttpRequest();");
			out.println("request.onload = function () { ");
			out.println("var status = request.status;"); // HTTP response status, e.g., 200 for "200 OK"
			out.println("var data = request.responseText;"); // Returned data, e.g., an HTML document.
			out.println("}");
			out.println("alert('status' +status);");
			out.println("alert('data' +data);");
			out.println("request.open('GET', url, true);");
			out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
			out.println("	 m_send_val = \"USER=lakderena&password=L@k6e9A&MASK=LAKDERANA&NUM=\"+unformat_noobject(document.Form1.TXT_CODE_NUM.value)+\"&MSG=\"+unformat_noobject(document.Form1.TXT_MESSAGE.value);");
			out.println("request.send(m_send_val);");
			out.println("} "); 
			
			//https://groupsms.etisalat.lk/sendsms.php?USER=username&PWD=password&MASK=CallerLineId&NUM=DNumber&MSG=message
			
			out.println("} "); 
			
			
			
			/*
			out.println("function sendingsms(obj){ "); 
			
			out.println("	get_display_msg();");
			out.println("	if(obj==\"SB\" || validate_data()){"); 
			out.println("	 m_url = \"https://groupsms.etisalat.lk/sendsms.php?USER=lakderena&PWD=L@k6e9A&MASK=LAKDERANA&NUM=\"+unformat_noobject(document.Form1.TXT_TO_NUM.value)+\"&MSG=\"+unformat_noobject(document.Form1.TXT_MESSAGE.value);");
			//out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr?chksql=LOAD_CLIENT_AVAILABILITY&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			//out.println("window.open(m_url);");
			//out.println("	 load_interface(m_url,'NO');");
			
			out.println("url = new URL(m_url);");
	        out.println("HttpsURLConnection con = (HttpsURLConnection)url.openConnection();");
			
			out.println("} "); 
			out.println("} "); 
			*/
			out.println("function get_vector_normal(m_data){");
			out.println("   DIV_STATUS.innerHTML=m_data ");
			out.println("}");
			
			
			
			
			
			
			
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Send\";"); 
			out.println("	}");
			out.println("}"); 
			
			
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Send_Sms_Leasing';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Send_Sms_Leasing';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(obj){	"); 
			out.println("before_submit(obj);"); 
			//out.println("sendingsms(obj);"); 
			out.println("}"); 
			out.println(""); 
			
			// added by udara 07-09-2015
			out.println("function save_window_2(obj){	"); 
			out.println("  before_submit_2(obj);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function before_submit_2(obj){ "); 
			
			out.println("	get_display_msg();");
			out.println("	if(obj==\"SB\" || validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\" )){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("	            document.Form1.hid_send_type.value=obj;");	
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_SMS_generation_save_4';");  
			out.println("				document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("	}"); 

			out.println("} "); 
			
			// end by udara 07-09-2015
			
			/*out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_OFSCL_AF_MAS_Send_Sms_Leasing\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
*/
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Send Sms - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Send Sms - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			/*
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
						out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
						out.println("document.Form1.TXT_DIVISION_DESC.disabled=true;"); 
						
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
						out.println("}else if(m_val==\"DACT\"){");  
						out.println("document.Form1.hid_save_status.value=\"Deactivate\";"); 
						out.println("document.Form1.hid_status.value=\"Deactivate\";");  
						out.println("}else if(m_val==\"RACT\"){");  
						out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
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
						out.println(""); 
						
						
						out.println("function clear_data() {");
						
						out.println("if(document.Form1.hid_help_type.value==\"99\"){");
						out.println("document.Form1.TXT_SUB_DIVISION_CODE.value='';");
						out.println("document.Form1.TXT_SUB_DIVISION_CODE.focus();");
						out.println("}");
						out.println("if(document.Form1.hid_help_type.value==\"2\"){");
						out.println("document.Form1.TXT_DIVISION_CODE.value='';");
						out.println("document.Form1.TXT_DIVISION_DESC.value='';");
						out.println("document.Form1.TXT_DESCRIPTION.value='';");
						out.println("document.Form1.TXT_DESCRIPTION.focus();");
						out.println("}");
						
						out.println("}");
						
						out.println("function help_button_1() {"); 
						out.println("    document.Form1.hid_help_type.value=\"1\";"); 
						out.println("    m_sql = \"m_help_TXT_DIVISION_CODE_sql\";"); 
						out.println("    m_criteria = document.Form1.TXT_DIVISION_CODE.value+\"@Y@\";"); 
						out.println("    HelpBox('1','10','0');"); 
						out.println("}"); 
						out.println(""); 
						
						out.println("function help_value_assign_1() {"); 
						out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[2];"); 
						out.println("    document.Form1.TXT_DIVISION_DESC.value=oBj.valout[3];"); 
						out.println("}");
						
						out.println("function help_update() {"); 
						out.println("    document.Form1.hid_help_type.value=\"99\";"); 
						out.println("    m_sql = \"m_help_TXT_SUB_DIVISION_CODE_sql\";"); 
						out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
						out.println("    m_criteria = document.Form1.TXT_SUB_DIVISION_CODE.value+\"@\"+\"Y@\";"); 
						out.println("    } ");
						out.println("    else{");
						out.println("    m_criteria = document.Form1.TXT_SUB_DIVISION_CODE.value+\"@\"+\"N@\";}"); 
						out.println("    HelpBox('1','10','1');"); 
						out.println("}"); 
						
						out.println("function help_update_value_assign_99() {"); 
						out.println("    document.Form1.TXT_SUB_DIVISION_CODE.value=oBj.valout[2];"); 
						out.println("    document.Form1.TXT_DESCRIPTION.value=oBj.valout[3];"); 
						out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[4];"); 
					out.println("		 assignState('M1');makeRequest(document.Form1.TXT_SUB_DIVISION_CODE);	");
						out.println("}"); 
						
						out.println("function help_value_assign_2() {"); 
						//out.println("    document.Form1.TXT_AREA_DESC.value=oBj.valout[3];"); 
						out.println("    document.Form1.TXT_DESCRIPTION.value=\"\";"); 
						out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
						out.println("}"); 
						
						out.println("function help_update_desc() {"); 
						out.println("    document.Form1.hid_help_type.value=\"2\";"); 
						out.println("    m_sql = \"m_help_TXT_SUB_DIVISION_DESC_sql\";"); 
						out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\"+\"Y@\";"); 
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
						out.println("    m_sql = \"m_view_TXT_SUB_DIVISION_CODE_sql\";");
						out.println("    m_criteria = document.Form1.TXT_SUB_DIVISION_CODE.value+\"@\"+\"Y@\";"); 
						out.println("    HelpView('1',50,'0');"); 
						out.println("}"); 
						
						
						*/
			
			
			
			
			/*out.println("function searchKeyPress(e)");
			out.println("{");
			// look for window.event in case event isn't passed in
			out.println("if (typeof e == 'undefined' && window.event) { e = window.event; }");
			//out.println("alert(e.keyCode);");
			out.println("if (e.keyCode == 48 && document.Form1.TXT_TO_NUM.value.length == 0)");
			out.println("{");
			out.println("   document.Form1.TXT_TO_NUM.select();");
			out.println("   document.Form1.TXT_TO_NUM.value='' ;");
			out.println("   alert(\"Can't enter Zero as first charactor\");");
			
			out.println("}");
			out.println("}");*/
			
			
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_send_type' VALUE=\"SS\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Send Sms</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Send\");'  onClick='save_window(\"SS\")' value=\"Send\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Send\");'  onClick='save_window(\"SB\")' value=\"Send Bulk\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Send\");'  onClick='save_window_2(\"SB\")' value=\"Send Bulk Test\"></td>");  
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
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_FROM_NUM'  class=div_input>From *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FROM_NUM' 	value='LAKDL' disabled maxlength='10' size='10' onblur=\"\">"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_TO_NUM'  class=div_input>To *</DIV></td>"); 
			out.println("<td width='40%' ><select style='width: 46px' name='TXT_CODE_NUM' disabled class='txt_input'><option value='+94'>+94</option></select>&nbsp;&nbsp;<input class='txt_input' type='text' name='TXT_TO_NUM'  maxlength='9' size='8' onblur=\"\" ></td>"); 
			out.println("<td width='10%' ><DIV id='label'  class=div_input>E.g. 778712342</DIV></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MESSAGE'  class=div_input>Message </DIV></td>"); 
			out.println("<td width='40%' ><TEXTAREA name='TXT_MESSAGE' rows='5' cols='15' style='width: 260px' class='txt_input'></textarea>"); 
			out.println("</td>"); 
			//out.println("<td width='*%'></td>"); 
			out.println("<td width='*%'><DIV id='DIV_STATUS'  class=div_input></DIV></td>"); 
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