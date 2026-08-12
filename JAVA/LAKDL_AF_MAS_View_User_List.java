//ID         :USER LIST
//SCREEN NAME:SYSTEM ADMINISTRATION - USER LIST
//CREATED BY :NUWAN DE SILVA
//DATE/TIME  :03-12-2007
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MAS_View_User_List extends javax.servlet.http.HttpServlet { 

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
			String m_chksql="";
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
		
		  /*stmt = conn.createStatement (); 
		
			rs=stmt.executeQuery (" SELECT "+
			" "+m_schema_name+".AF_CO_DATA(PASSWORD,'ECONV') "+
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE UPPER(USER_ID)=UPPER('"+m_username+"') ");
			boolean more=rs.next();
			if(more){
			m_password=rs.getString(1);
			}
			*/
			
			
			m_chksql = req.getParameter("chksql");
			
			if(m_chksql.trim().equals("main_page")){
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Area</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
      out.println("var b_staus=0;"); 
			
			out.println("var m_password='';"); 
			
			
			out.println("function help_user() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"Y@\"+\"M@\";"); 
			out.println("    HelpBox('1','10','4');"); 
			out.println("}"); 

			out.println("function assign_user() {"); 
			out.println("    document.Form1.TXT_USER_ID.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_USER_NAME.value=oBj.valout[3];"); 
      out.println("}"); 
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 

			
			//new
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
			out.println("		assign_user();"); 
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

			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_USER_ID.value='';"); 
			out.println("document.Form1.TXT_NAME.value='';"); 
			out.println("}");
			out.println("}");	


			
			
			
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_View_User_List?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			/*out.println("function close_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to close the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_FollowupAlert?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); */
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_View_User_List?chksql=main_page';"); 
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
			out.println("help_box.innerHTML=\" System Administration - User List - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - User List - \"+document.Form1.hid_status.value;"); 
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
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_user&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 &&  document.Form1.hid_chk_status.value=='M1'){");
			out.println("			help_user();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 &&  document.Form1.hid_chk_status.value=='M1'){");
			out.println("				assign_data(data_vec);");
			out.println("			}");
			out.println("}");
			
			out.println("function assign_data(data_vec){ ");
			out.println("    document.Form1.TXT_USER_ID.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_USER_NAME.value=data_vec[1];"); 
			out.println("}");
			
			
			
			//____ added by nuwan de silva on 23-11-2007 __________
			out.println("function Search_User_Details(m_user_id) {"); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_View_User_List?chksql=load_users&user_id=\"+m_user_id;"); 
			out.println("load_interface(m_url,'NORM');");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table_user.innerHTML = ''; ");
			out.println(" m_table_user.innerHTML = http_response; ");
			out.println("}");
      //____ end by nuwan de silva on 23-11-2007 ______________

      out.println("function view_Rights(m_user_id) {");
			out.println("		window.open(\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_User_rights_details?chksql=main_page&emp_code=\"+m_user_id+\"&close_status=Y\",\"popupwin1\",\"status=0,menubar=0,scrollbars=1,resizable=1, height=700,width=800\");");
			out.println("}");
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_AD_USER_LIST\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - User List/td>"); 
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
			
			out.println("<td width='10%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
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
			out.println("<td width='30%' ><DIV id='div_TXT_USER_ID'  class=div_input>User ID *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_USER_ID' maxlength='10' size='10' style=\"width:175px;\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Search\" onClick=\"Search_User_Details(document.Form1.TXT_USER_ID.value)\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER_NAME'  class=div_input>User Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_USER_NAME'  style=\"width:175px;\" maxlength='50' onblur=\"\" Disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			out.println("</table>"); 
			
			out.println("<br>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_user'></DIV></td>");
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			else if(m_chksql.trim().equals("load_users")){
			
			String _m_user_id=req.getParameter("user_id");
			stmt = conn.createStatement (); 
		
			rs=stmt.executeQuery (" SELECT "+
			" USER_ID ,"+
	    " NAME, "+
      " LOCATION_CODE, "+
      //" USER_TYPE, "+
			" DECODE(USER_TYPE,'IND','Individual','COR','Corporate','SOL','Sole Proprietorship','PAR','Partnership','LIL','Limited Liability','NGN','Non Governmental'),"+
      " DESIGNATION_CODE ,"+
			" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE), "+
			" "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE), "+
			//" ACTIVE_STATUS "+
			" DECODE(ACTIVE_STATUS,'M','User Approval Level','Y','Active','Not Active')"+
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE UPPER(USER_ID) LIKE UPPER('"+_m_user_id+"%')  "); //AND ACTIVE_STATUS='Y'
			
			boolean more=rs.next();
						
			out.println("<table class=table border='0' width='100%' >");
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td  width='10%' >User Id</td>");
      out.println("<td  width='20%' >User Name</td>");
			out.println("<td  width='15%' >Location Desc</td>");
			out.println("<td  width='15%' >User Type</td>");
			out.println("<td  width='15%' >Designation</td>");
			out.println("<td  width='10%' >Status</td>");
		  out.println("<td  width='*%'  >&nbsp;</td>");			
			out.println("</tr>");			
			
			int j=0;
			while(more){
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			out.println("<td  width='10%' >"+rs.getString(1)+"</td>");
      out.println("<td  width='20%' >"+rs.getString(2)+"</td>");
			out.println("<td  width='15%' style=\"{cursor:hand}\" onClick=\"show_location_drill('"+rs.getString(3)+"')\" ><u>"+rs.getString(6)+"</u></td>");
			out.println("<td  width='15%' >"+rs.getString(4)+"</td>");
			//out.println("<td  width='15%' >"+rs.getString(7)+"</td>");
			out.println("<td  width='15%' style=\"{cursor:hand}\" onClick=\"show_designation_drill('"+rs.getString(5)+"')\" ><u>"+rs.getString(7)+"</u></td>");
			out.println("<td  width='10%' >"+rs.getString(8)+"</td>");
		  out.println("<td  width='*%'  ><input type='button' class='but_input' name='btn_permission' value=\"User Rights\" style=\"{width:120px}\" onClick=\"view_Rights('"+rs.getString(1)+"')\" ></td>");			
			
			out.println("</tr>");			
			
		  more=rs.next();	
			j=j+1;
			}
			out.println("</table>");			
			
			
			}
			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
