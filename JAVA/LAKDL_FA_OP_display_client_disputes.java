// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
      
public class LAKDL_FA_OP_display_client_disputes extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
  public ResultSet rs,rs1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();

			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			 
			ServletOutputStream out = res.getOutputStream(); 

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Operation Process - Client Disputes  </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function makeRequest(obj) {");
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
			out.println("try {");
			out.println("http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
			out.println("} catch (e) {");
			out.println("try {");
			out.println("http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("} catch (e) {}");
			out.println("}");
			out.println("}");
			out.println("if (!http_request) {");
			out.println("alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("return false;");
			out.println("}");
			out.println("url=\"\";");
			out.println("if(obj==\"M1\"){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_score_validations?chksql=SCORE_DETAILS_EDIT&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value+\"&facility_no=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("}else{");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_score_validations?chksql=SCORE_DETAILS_ENTER&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value;");
			out.println("}");
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1,obj); ");
			out.println("};");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			out.println("}");
			
			out.println("function alertContents(http_request,count,obj) {");
			out.println(" if (http_request.readyState == 4) {");
			out.println("    if (http_request.status == 200) {");
			out.println("      	if(http_request.responseText!=\"\"){");
			out.println(" 				m_data=http_request.responseText;");
			out.println("					if(obj==\"M1\"){");
			out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("					else{");
			out.println("						//alert(m_data);");
			out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("				}");
			out.println("    } else {");
			out.println("        alert('There was a problem with the request.');");
			out.println("    }");
			out.println(" }");
			out.println("}");
			
			out.println("function valdate_values(obj,min,max){");
			out.println("objname=document.Form1.elements[\"SCORE_\"+obj];");
			out.println("if((parseFloat(objname.value)>parseFloat(max)) || (parseFloat(objname.value)<parseFloat(min))){");
			out.println("alert('Credit score should between the Min and Max value for the Score Category');");
			out.println("objname.value=\"0\";");
			out.println("}");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function load_default_score(val1){");
			out.println("obj_select=document.Form1.elements[\"RATE_\"+val1];");
			out.println("val=obj_select.value;");
			out.println("obj1=document.Form1.elements[\"RATE_VAL_\"+val];");
			out.println("obj2=document.Form1.elements[\"SCORE_\"+val1];");
			out.println("obj3=document.Form1.elements[\"MAX_\"+val1];");
			out.println("obj2.value=format_noobject((parseFloat(obj3.value)/100)*parseFloat(obj1.value));");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function get_vector_normal(http_response) {");
			out.println("	score_details.innerHTML=http_response;");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FACILITY_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_COMMENTS.value==\"\"){  "); 
			out.println("DIV_TXT_COMMENTS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for(var i=0;i<document.Form1.elements.length;i++){");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_save_client_disputes';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_display_client_disputes';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_display_client_disputes';"); 
			out.println("}"); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_CR_display_credit_score_enter\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Operation Process - Client Disputes  - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Operation Process - Client Disputes  - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_TOTAL_SCORE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_update_value_assign_10();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
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
			out.println("if(oBj.valout[2]==' '){");
			//out.println("Close_2();"); 
			out.println("	}	"); 
			out.println("}"); 
			
			out.println("function Close_2(){");
			out.println("clear_data()	");
      out.println("window.close();");
      out.println("}");
			
			out.println("function clear_data() {");
			out.println("if(document.Form1.hid_help_type.value==\"10\"){");
			out.println("document.Form1.TXT_CLIENT_NO.value='';");
			//out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			//out.println("document.Form1.TXT_SCORE_MODEL_CODE.value='';"); 
			//out.println("document.Form1.TXT_TOTAL_SCORE_APP.value='';"); 
			//out.println("document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("document.Form1.TXT_COMMENTS.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"100\"){");
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			out.println("document.Form1.TXT_CREDIT_EVAL.focus();");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_SCORE_MODEL_CODE.value='';");
			out.println("document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
			out.println("}");

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 		
			
			out.println("function help_update_user() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CREDIT_EVAL.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','6');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_100() {"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update_model() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_SCORE_MODEL_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SCORE_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_application() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_DISPUTE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_DIV_TXT_FACILITY_CR_SOCRE_EDIT_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','4');"); 
			out.println("}");
			out.println("}"); 
			
			out.println("function help_update_value_assign_10() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_MK_OFFICER.value=oBj.valout[5];"); 
			out.println("    makeRequest_detail();");
			out.println("}"); 
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("    document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[9];"); 
			out.println("    makeRequest('M1');");
			out.println("}"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[4];"); 
			out.println("    makeRequest('M2');");
			out.println("}"); 
			
			out.println("function load_default_user(){");
			out.println("    document.Form1.TXT_CREDIT_EVAL.value='"+m_username+"';"); 
			out.println("}"); 
			
			out.println("function makeRequest_detail() {");
			//out.println("	  document.Form1.hid_option.value=\"3\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=LOAD_CLIENT_DISPUTES&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("		load_interface(m_url,'NO');");
			out.println("}");
			
			out.println("function load_view_score(){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_score_validations?chksql=SCORE_DETAILS_VIEW&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value+\"&facility_no=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("window.open(url,'win1','left=0,top=1,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			out.println("}"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='1' topmargin='10' marginwidth='0' >"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'>"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='1' valign='top'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process - Client Disputes  </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View\");' onClick='load_view_score();load_screen_status(\"VIEW\")' value=\"View\"></td>");  
			out.println("<td width='10%' align='center'></td>");  
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='25' size='25' onblur=\"help_update_application()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_application()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN1' value=\"Detail\" onClick=\"show_facility(document.Form1.TXT_FACILITY_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NO' maxlength='10' size='10' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN2' value=\"Detail\" onClick=\"show_client(document.Form1.TXT_CLIENT_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='10' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_MK_OFFICER'  class=div_input>Marketing Officer </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MK_OFFICER' maxlength='10' size='10' onblur=\"help_update_user()\" value=\"\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_user()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");  
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_COMMENTS'  class=div_input>Comments *</DIV></td>"); 
			out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_COMMENTS' style='width:550px' style='height:100px' maxlength='1000' ></TEXTAREA></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<DIV id='score_details'  class=div_input></DIV>");
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			out.flush();
			out.close();
			conn.close();
			this.destroy();
			}
			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


