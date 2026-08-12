
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

public class LAKDL_AF_MAS_Change_User_Password extends javax.servlet.http.HttpServlet { 

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
			String m_expired_status="NO";
			
			
			if(req.getParameter("status")!=null){
			m_expired_status = req.getParameter("status");
			}
			
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
		
			String m_minimum_length="",m_maximum_length="",m_lowercase_char="",m_uppercase_char="",m_numeric_char="";
			String m_numeric_embedded="",m_special_char="",m_special_char_embedded="",m_password_change_attempt="";
			String m_allow_user_name="",m_repeat_password="",m_password_change_minimum_gap="",m_password_expiration_days="",m_warning_message_days="";
			
			      rs = stmt.executeQuery(" " +
                    "   SELECT  " +
										"		NVL(MINIMUM_LENGTH,0), " +
										"		NVL(MAXIMUM_LENGTH,0), " +
										"		NVL(LOWERCASE_CHAR,0), " +
										"		NVL(UPPERCASE_CHAR,0), " +
										"		NVL(NUMERIC_CHAR,0), " +
										"		NUMERIC_EMBEDDED, " +
										"		NVL(SPECIAL_CHAR,0), " +
										"		SPECIAL_CHAR_EMBEDDED, " +
										"		NVL(PASSWORD_CHANGE_ATTEMPT,0), " +
										"		ALLOW_USER_NAME, " +
										"		NVL(REPEAT_PASSWORD,0), " +
										"		NVL(PASSWORD_CHANGE_MINIMUM_GAP,0), " +
										"		NVL(PASSWORD_EXPIRATION_DAYS,0), " +
										"		NVL(WARNING_MESSAGE_DAYS,0) " +
                    "   FROM " + m_schema_name + ".CO_CO_MAS_PASSWORD_POLICY A " );
										
     if(rs.next()){
			m_minimum_length              = rs.getString(1);
			m_maximum_length              = rs.getString(2);
			m_lowercase_char              = rs.getString(3);
			m_uppercase_char              = rs.getString(4);
			m_numeric_char                = rs.getString(5);
			m_numeric_embedded            = rs.getString(6);
			m_special_char                = rs.getString(7);
			m_special_char_embedded       = rs.getString(8);
			m_password_change_attempt     = rs.getString(9);
			m_allow_user_name             = rs.getString(10);
			m_repeat_password             = rs.getString(11);
			m_password_change_minimum_gap = rs.getString(12);
			m_password_expiration_days    = rs.getString(13);
			m_warning_message_days        = rs.getString(14);
			}										

			
			
			
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Area</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
      
			out.println("var b_staus=0;"); 
			out.println("var m_password='';"); 
			
			
			
			out.println(" var m_minimum_length              ='"+m_minimum_length+"';  ");
			out.println(" var m_maximum_length              ='"+m_maximum_length+"';  ");
			out.println(" var m_lowercase_char              ='"+m_lowercase_char+"';  ");
			out.println(" var m_uppercase_char              ='"+m_uppercase_char+"';  ");
			out.println(" var m_numeric_char                ='"+m_numeric_char+"';  ");
			out.println(" var m_numeric_embedded            ='"+m_numeric_embedded+"';  ");
			out.println(" var m_special_char                ='"+m_special_char+"';  ");
			out.println(" var m_special_char_embedded       ='"+m_special_char_embedded+"';  ");
			out.println(" var m_password_change_attempt     ='"+m_password_change_attempt+"';  ");
			out.println(" var m_allow_user_name             ='"+m_allow_user_name+"';  ");
			out.println(" var m_repeat_password             ='"+m_repeat_password+"';  ");
			out.println(" var m_password_change_minimum_gap ='"+m_password_change_minimum_gap+"';  ");
			out.println(" var m_password_expiration_days    ='"+m_password_expiration_days+"';  ");
			out.println(" var m_warning_message_days        ='"+m_warning_message_days+"';  ");
			out.println(" var m_uname                       ='"+m_username+"';");
						//validate password
			out.println("function passwordvalidator(obj) {");
			
			out.println("if(obj.value!=''){");

			out.println("var passed = validatePassword(obj.value, {");
	    out.println("length:   [m_minimum_length, m_maximum_length], ");
	    out.println("lower:    m_lowercase_char,");
	    out.println("upper:    m_uppercase_char,");
	    out.println("numeric:  m_numeric_char,");
	    out.println("special:  m_special_char,");
			
			out.println("badWords: [\"password\",\"sasianet\",\"netasset\"],");
	    out.println("badSequenceLength: 0");
      out.println("});");
			
			out.println("if(!passed){");
			out.println("var msg=\"The password supplied does not meet the minimum complexity requrements.please select "+
			                      "another password that match all the following criteria. is at least \"+m_minimum_length+\" characters; "+
												    //"has not been used in the previous \"+m_password_change_minimum_gap+\" passwords; "+
														//"must not have changed with the last \"+m_password_change_minimum_gap+\" days; "+
														//"does not contain your username;"+
														"contains at least three of the following character groups.;"+
														"at least \"+m_uppercase_char+\" english characters(A throu Z);"+
														"at least \"+m_lowercase_char+\" english lowercase characters(a throu z);"+
														"at least \"+m_numeric_char+\"   numberic(0 throu 9);"+
														"at least \"+m_special_char+\"   non-apphabetic characters(such as !,#,%);"+
														"type the requirements in oth text boxes.\"");
												
			out.println("alert(msg);");
			out.println("obj.value='';");
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			out.println("function validate_password(obj) {");
			out.println("if(obj.value!=''){");
			//out.println("if(obj.value.toUpperCase()!=m_password.toUpperCase()){	"); // commented by udara 17-12-2014
			out.println("if(obj.value!=m_password){	"); // added by udara 17-12-2014
			out.println("		alert('Old Password is not Correct');	"); //modified by nuwan de silva 06-08-2007
			out.println("			obj.value='';");
			out.println("			obj.focus();");
			out.println("			}");
			out.println("			}");			
			out.println("			}");
			
			out.println("function validate_password_new(obj) {");
			out.println("if(obj.value!=''){");
			//out.println("if(obj.value.toUpperCase()==m_password.toUpperCase()){	"); // commented by udara 17-12-2014
			out.println("if(obj.value==m_password){	"); // added by udara 17-12-2014
			//MODIFIED BY DELANJALI FOR REF NO 803 ON 2007-08-16--------
			out.println("		alert('Entered password is similar to old password');	");
			out.println("			obj.value='';");
			out.println("			obj.focus();");
			out.println("			}");
			out.println("			}");
			
			out.println("			}");
			
			out.println("function validate_password_confirm(obj,obj1) {");
			out.println("if(obj1.value!=''){");
			//out.println("if(obj.value.toUpperCase()!=obj1.value.toUpperCase()){	"); // commented by udara 17-12-2014
			out.println("if(obj.value!=obj1.value){	"); // added by udara 17-12-2014
			//out.println("		alert('New Password Not Match');	");
			out.println("		alert('The password confirmation does not match');	"); //modified by nuwan de silva 20-08-07
			
			out.println("			obj1.value='';");
			out.println("			obj.value='';");
			out.println("			obj.focus();");
			out.println("			}");
			out.println("			}");
			
			out.println("			}");
			
			
			
			
			

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_OLD_PASSWORD.value==\"\"){  "); 
		//	out.println("alert('Area Code Can not Be Blank');"); 
			out.println("DIV_TXT_OLD_PASSWORD.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NEW_PASSWORD.value==\"\"){  "); 
		//	out.println("alert('Area Description Can not Be Blank');"); 
			out.println("DIV_TXT_NEW_PASSWORD.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CONFIRM_PASSWORD.value==\"\"){  "); 
		//	out.println("alert('City Code Can not Be Blank');"); 
			out.println("DIV_TXT_CONFIRM_PASSWORD.style.color='red';");
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
			out.println("   document.Form1.hid_expired_status.value='"+m_expired_status+"';"); //added by ns on 09-12-2010
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Save_Change_User_Password';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.Form1.TXT_USER_NAME.value='"+m_username+"';");
			
			out.println("m_password='"+m_password+"'");
			
			//	out.println("alert('pwd'+m_password)");
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Change_User_Password';"); 
			out.println("		}"); 
			out.println("}"); 
			
			/*out.println("function close_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to close the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_FollowupAlert?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); */
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"_AF_MAS_Change_User_Password';"); 
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
			out.println("help_box.innerHTML=\" System Administration - Change Password - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Change Password - \"+document.Form1.hid_status.value;"); 
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
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_AD_USER_CHANGE_PASSWORD\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_expired_status' VALUE=\"0\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Change Password/td>"); 
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

			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AREA_CODE'  class=div_input>Area Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_AREA_CODE' maxlength='10' size='10' onblur=\"assignState('M1'), makeRequest(document.Form1.TXT_AREA_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER_NAME'  class=div_input>User Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_USER_NAME' value=\""+m_username+"\" style=\"width:175px;\" maxlength='50' onblur=\"\" Disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_OLD_PASSWORD'  class=div_input>Old Password *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='password' name='TXT_OLD_PASSWORD'  style=\"width:150px;\" maxlength='50'  onblur=\"validate_password(document.Form1.TXT_OLD_PASSWORD)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NEW_PASSWORD'  class=div_input>New Password *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='password' name='TXT_NEW_PASSWORD' style=\"width:150px;\" maxlength='50' onblur=\"passwordvalidator(this);validate_password_new(document.Form1.TXT_NEW_PASSWORD)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CONFIRM_PASSWORD'  class=div_input>Confirm New Password *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='password' name='TXT_CONFIRM_PASSWORD' style=\"width:150px;\" maxlength='50' onblur=\"passwordvalidator(this);validate_password_confirm(document.Form1.TXT_NEW_PASSWORD,document.Form1.TXT_CONFIRM_PASSWORD)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_CITY_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >City Description</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CITY_DESC' maxlength='50' size='20' disabled ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			

			
			out.println("<tr >"); 
			out.println("<td width='30%' >Default Value</td>"); 
			out.println("<td width='40%' ><select name='TXT_DEFAULT_VALUE' class='txt_input'>");
			out.println("<option value=\"Y\"         >Yes</option>");
			out.println("<option value=\"N\" SELECTED>No </option>");
			out.println("</select>");
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validatepassword.js'></SCRIPT>"); 

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
