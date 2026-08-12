
//ID         :CHANGE USER PASSWORD
//SCREEN NAME:SYSTEM ADMINISTRATION - CHANGE USER PASSWORD
//CREATED BY :NUWAN DE SILVA
//DATE/TIME  :01-03-2007
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MAS_display_password_policy extends javax.servlet.http.HttpServlet { 

  Connection conn;
	Statement stmt,stmt1;
  public ResultSet rs,rs1;
	
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req);
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_username=m_sn_methods.username;
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			String m_user_id="";
			String m_password="";
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
						
      stmt = conn.createStatement (); 
				
			 rs=stmt.executeQuery (" SELECT "+
			 " "+m_schema_name+".AF_CO_DATA(PASSWORD,'ECONV') "+
			 " FROM "+m_schema_name+".CO_CO_MAS_USER "+
       " WHERE UPPER(USER_ID)=UPPER('"+m_username+"') ");
   

		boolean more=rs.next();
		
		if(more){
		
		m_password=rs.getString(1);
		}
			
			
			
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Password Policy</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
      out.println("var b_staus=0;"); 
			
			out.println("var m_password='';"); 
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			//out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			
			     out.println("function get_vector(data_vec) {");
	         out.println("		if(data_vec.length>0 ){");
	         out.println("			document.Form1.TXT_MIN_LENGTH.value=data_vec[0];");
	         out.println("			document.Form1.TXT_MAX_LENGTH.value=data_vec[1];");
	         out.println("			document.Form1.TXT_MIN_LOWER.value=data_vec[2];");
	         out.println("			document.Form1.TXT_MIN_UPPER.value=data_vec[3];");
	         out.println("			document.Form1.TXT_MIN_NUM.value=data_vec[4];");
	         out.println("			document.Form1.TXT_NUM_EMBED.value=data_vec[5];");
	         out.println("			document.Form1.TXT_MIN_SPECIAL.value=data_vec[6];");
	         out.println("			document.Form1.TXT_CHAR_EMBED.value=data_vec[7];"); 
	         out.println("			document.Form1.TXT_CHANGE_ATTEMPT.value=data_vec[8];"); 
	         out.println("			document.Form1.TXT_USER_NAME_ALLOW.value=data_vec[9];"); 
	         out.println("			document.Form1.TXT_REPEAT_PW.value=data_vec[10];"); 
	         out.println("			document.Form1.TXT_MIN_GAP.value=data_vec[11];");
	         out.println("			document.Form1.TXT_EXPIRE_DAYS.value=data_vec[12];");
	         out.println("			document.Form1.TXT_EXPIRE_MSG.value=data_vec[13];");
	         out.println("		}");
					 out.println("}");
			
			/*
			
			out.println("function validate_password(obj) {");
			out.println("if(obj.value!=''){");
			out.println("if(obj.value.toUpperCase()!=m_password.toUpperCase()){	");
			out.println("		alert('Old Password is not Correct');	"); //modified by nuwan de silva 06-08-2007
			out.println("			obj.value='';");
			out.println("			obj.focus();");
			out.println("			}");
			out.println("			}");			
			out.println("			}");
			
			out.println("function validate_password_new(obj) {");
			out.println("if(obj.value!=''){");
			out.println("if(obj.value.toUpperCase()==m_password.toUpperCase()){	");
			//MODIFIED BY DELANJALI FOR REF NO 803 ON 2007-08-16--------
			out.println("		alert('Entered password is similar to old password');	");
			out.println("			obj.value='';");
			out.println("			obj.focus();");
			out.println("			}");
			out.println("			}");
			
			out.println("			}");
			
			out.println("function validate_password_confirm(obj,obj1) {");
			out.println("if(obj1.value!=''){");
			out.println("if(obj.value.toUpperCase()!=obj1.value.toUpperCase()){	");
			//out.println("		alert('New Password Not Match');	");
			out.println("		alert('The password confirmation does not match');	"); //modified by nuwan de silva 20-08-07
			
			out.println("			obj1.value='';");
			out.println("			obj.value='';");
			out.println("			obj.focus();");
			out.println("			}");
			out.println("			}");
			
			out.println("			}");
			
			
			*/
			

			out.println("function validate_data(){"); 
			out.println("if(document.Form1.TXT_MIN_LENGTH.value==\"\"){  "); 
			out.println("DIV_TXT_MIN_LENGTH.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MIN_LENGTH.value==\"\"){  "); 
			out.println("DIV_TXT_MIN_LENGTH.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MIN_LENGTH.value==\"\"){  "); 
			out.println("DIV_TXT_MIN_LENGTH.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_password_policy_save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			/*out.println("function load_lock(){	"); 
			out.println("m_password='"+m_password+"'");
			out.println("}	"); 
      */
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_password_policy';"); 
			out.println("		}"); 
			out.println("}"); 
			
			/*out.println("function close_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to close the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_FollowupAlert?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); */
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_password_policy';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_CHANGE_USER_PASSWORD\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - password policy - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 
			
			
			//added by ns on 09-12-2010 for loading policy details
			out.println("function load_policy(){"); 
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"CO_CO_MAS_sql_validations?chksql=GET_PASSWORD_POLICY\";");
	    out.println("    load_interface(m_url,'XML');");
			out.println("}"); 
			
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - password Policy - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{");
			
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New');load_policy();\">"); //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_AD_USER_CHANGE_PASSWORD\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='TXT_CHANGE_ATTEMPT' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='TXT_USER_NAME_ALLOW' VALUE=\"N\">");
			out.println("<INPUT TYPE='Hidden' NAME='TXT_REPEAT_PW' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='TXT_MIN_GAP' VALUE=\"0\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Password Policy/td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_LENGTH'  class=div_input>Minimum Length *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_LENGTH' maxlength='5' size='5'  onBlur=\"check_number(this,5)\"></td>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MAX_LENGTH'  class=div_input>Maxlength Length *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MAX_LENGTH' maxlength='5' size='5' onBlur=\"check_number(this,5)\" ></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_LOWER'  class=div_input>Minimum Number of Lowercase Char </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_LOWER' maxlength='5' size='5' onBlur=\"check_number(this,5)\" ></td>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_UPPER'  class=div_input>Minimum Number of Uppercase Char </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_UPPER' maxlength='5' size='5' onBlur=\"check_number(this,5)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_NUM'  class=div_input>Minimum Number of Numeric Char </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_NUM' maxlength='5' size='5' onBlur=\"check_number(this,5)\"></td>"); 
			out.println("<td width='30%' >Numbers be Embedded</td>");
			out.println("<td width='20%' >");
			out.println("<select name='TXT_NUM_EMBED' class=\"txt_input5\" style= 'text-align:left;' disabled >");
			out.println("<OPTION value=\"Y\" SELECTED >Yes </option>");
			out.println("<OPTION value=\"N\"  >No </option>");
			out.println("</SELECT></td>");
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_SPECIAL'  class=div_input>Minimum Numbers of Special Char </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_SPECIAL' maxlength='5' size='5' onBlur=\"check_number(this,5)\"></td>"); 
			out.println("<td width='30%' >Special Char be Embedded</td>");
			out.println("<td width='20%'>");
			out.println("<select name='TXT_CHAR_EMBED' class=\"txt_input5\" style= 'text-align:left;' disabled>");
			out.println("<OPTION value=\"Y\" SELECTED >Yes </option>");
			out.println("<OPTION value=\"N\"  >No </option>");
			out.println("</SELECT></td>");
			out.println("</tr>"); 
			
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CHANGE_ATTEMPT'  class=div_input>Number of Password Change Attempts </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='hidden' name='TXT_CHANGE_ATTEMPT' maxlength='5' size='5' onBlur=\"check_number(this,5)\"></td>"); 
			out.println("<td width='30%' >Allow User Name in Password</td>");
			out.println("<td width='20%'>");
			out.println("<select name='TXT_USER_NAME_ALLOW' class=\"txt_input5\" style= 'text-align:left;'>");
			out.println("<OPTION value=\"Y\" >Yes </option>");
			out.println("<OPTION value=\"N\" SELECTED >No </option>");
			out.println("</SELECT></td>");
			out.println("</tr>"); 
			*/
			
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_REPEAT_PW'  class=div_input>Allow Repeat a Password After How Many Passwords </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_REPEAT_PW' maxlength='5' size='5' onBlur=\"check_number(this,5)\"></td>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_GAP'  class=div_input>Minimum Gap Before Subsequent Password Change in Minutes </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_GAP' maxlength='5' size='5' onBlur=\"check_number(this,5)\"></td>"); 
			out.println("</tr>"); 
			*/
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_EXPIRE_DAYS'  class=div_input>Period for Password Expiration in Days *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_EXPIRE_DAYS' maxlength='5' size='5' onBlur=\"check_number(this,5)\" ></td>"); //checkExpirationDays(this);
			out.println("<td width='30%' ><DIV id='DIV_TXT_EXPIRE_MSG'  class=div_input>Number of Days Prior to Expiration Date Should the Password Expiry Warning Message Appear *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_EXPIRE_MSG' maxlength='5' size='5' onBlur=\"check_number(this,5)\" ></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 

			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
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
