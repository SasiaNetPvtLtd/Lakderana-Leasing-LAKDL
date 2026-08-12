//SCREEN NAME:SYSTEM ADMINISTRATION - PASSWORD POLOICY
//CREATED BY:THAMALI JAYATUNGA
//DATE/TIME:2010.07.01

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MAS_password_policy_display extends javax.servlet.http.HttpServlet {
    
    ServletOutputStream out = null;
    public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        try {
            
            ServletOutputStream out = res.getOutputStream();
           
						/*HttpSession session = req.getSession(true);NETFAC_AF_CO_conn_methods m_init_methods = new NETFAC_AF_CO_conn_methods(); String html_client_home_url=m_init_methods.html_client_home_url.trim();
            String m_screen_url = HttpUtils.getRequestURL(req).toString();
            
            Object done = (String)session.getValue("logon.isDone");  // Marker Object
            if (done == null) {
                String m_target_path = m_screen_url;
                session.putValue("login.target", m_target_path);
                out.println("<html><head>");
                out.println("<script language='JavaScript'>");
                out.println("function displaymsg() {");
                out.println("alert('Your login expired or invalid login');");
                out.println("window.location.href='"+html_client_home_url+"/login.htm' ;");
                out.println("}</script></head>");
                out.println("<body onload='displaymsg();'></body>");
                out.println("</html>");
                out.flush();
                return;
            }
            
            
            int m_index = m_screen_url.lastIndexOf("/");
            m_screen_url = m_screen_url.substring(m_index + 1);
            Vector userScreens = (Vector)session.getValue("logon.screen");
            if (!(userScreens.contains(m_screen_url))) {
                out.println("<html><head>");
                out.println("<script language='JavaScript'>");
                out.println("function displaymsg() {");
                out.println("alert('Logged user have not enough privilages to access this screen');");
                out.println("window.open('"+html_client_home_url+"/login.htm','AFS');");
                out.println("}</script></head>");
                out.println("<body onload='displaymsg();'></body>");
                out.println("</html>");
                out.flush();
                return;
            }
            
						*/
						
            //NETFAC_AF_CO_conn_methods m_sn_methods = new NETFAC_AF_CO_conn_methods(session);
            // LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(req);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
						
            String m_html_client_url=m_sn_methods.html_client_url.trim();
			String m_screen_url = HttpUtils.getRequestURL(req).toString();
           // String m_html_client_home_url = m_sn_methods.html_client_home_url.trim();
            String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
            String m_fschema_name=m_sn_methods.client_name.trim();
            String header_name=m_sn_methods.header_name.trim();
			String username=m_sn_methods.username.trim();  
            String company_name=m_sn_methods.company_name.trim();
            res.setStatus(HttpServletResponse.SC_OK); 
            res.setContentType("text/html"); 
            
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"); 
            out.println("<HTML>"); 
            out.println("<HEAD>"); 
            out.println("<TITLE>System Administration - Nature of Relationship</TITLE>");
            
       /*     out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/jquery/fancybox/jquery-1.4.1.min.js\"></script>");
            out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/jquery/fancybox/jquery.fancybox-1.3.0.pack.js\"></script>");
            out.println("       <script language1.2 = \"JavaScript\" src = \"" + m_html_client_home_url + "/validate.js\"></script>");
            out.println("       <script language1.2 = \"JavaScript\" src = \"" + m_html_client_home_url + "/validate_v1.js\"></script>");
            out.println("       <script language1.2 = \"JavaScript\" src = \"" + m_html_client_home_url + "/ajax_data_gateway.js\"></script>");
            out.println("       <script language1.2 = \"JavaScript\" src = \"" + m_html_client_home_url + "/user_rights_data_gateway.js\"></script>");
			      out.println("       <link rel = \"stylesheet\" type = \"text/css\" href = \"" + m_html_client_url + "/jquery/fancybox/jquery.fancybox-1.3.0.css\" media = \"screen\" />");
            out.println("       <link rel = \"stylesheet\" type = \"text/css\" href = \"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
        */    
            out.println("<SCRIPT language=\"JavaScript\">");
            
            out.println("var bank_arry=new Array();");
            out.println("var dist_arry=new Array();");
            out.println("var alert_msg;");
					  out.println("var error_msg1 = false;");
            out.println("var error_msg2 = false;");
            out.println("var error_msg3 = false;");
					  out.println("var error_msg4 = false;");
            out.println("var error_msg5 = false;");


            //new help box functions
            out.println("var isOpen=false;");
            
            out.println("function init_fancybox() { ");   // This Method should call after every dynamic element addition 
            out.println("   $(\"a.fancylink\").fancybox({"); 
            out.println("    'hideOnContentClick': false, 'width':404 , 'height': 328,"); 
            out.println("    'overlayShow': false, ");
            out.println("    'changeSpeed':0, 'speedIn':200, 'speedOut':200,"); 
            out.println("    'opacity':true,'onClosed' : clearIsOpen, 'centerOnScroll':true, 'easingIn':'swing', "); 
            out.println("    'easingOut':'swing' ,transitionIn:'elastic' ,transitionOut:'elastic',"); 
            out.println("    'type':'iframe','showCloseButton':false,'enableEscapeButton':false,'modal':true,'overlayOpacity':0.1");
            out.println("  });"); 
            out.println("}");
            
            out.println("$(document).ready(function() { ");
            out.println("  init_fancybox();"); 
            out.println("});"); 
            
            //NEW ADDITION
            out.println("$('a').live('click', function() {"); 
            out.println("  init_fancybox();");			
            out.println("}); ");  
            
            
            out.println("function clearIsOpen(){"); 
            out.println("   isOpen=false;"); 
            out.println("}");
            
            out.println("function HelpBox(Start,End,Hid_No,link_id) {");
            out.println("     document.getElementById(link_id).href=servlet_client_url+\":\"+client_t3_port+\"/"+m_fschema_name+"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
            out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
            out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
            out.println("    \"&Hid_No=\"+Hid_No+\"&link_id=\"+link_id;");
            out.println("    $(\"#\"+link_id).click();");
            out.println("}");
            
            out.println("function helpCheck(valout,Hid_No,link_id){");
            out.println("	if(valout[1]==\"Next\"){"); 
            out.println("		Next(valout[2],valout[3],Hid_No,link_id);"); 
            out.println("	} "); 
            out.println("	else if(valout[1]==\"Prev\"){"); 
            out.println("		Prev(valout[2],valout[3],Hid_No,link_id);"); 
            out.println("	}	"); 
            out.println("}  ");
            
            
            out.println("function help_close(){");  
            out.println("   $.fancybox.close();"); 
            out.println("}");
            
            
            out.println("function Prev(Start,End,Hid_No,link_id){"); 
            out.println("	HelpBox(Start,End,Hid_No,link_id);"); 
            out.println("}"); 
            
            
            out.println("function Next (Start,End,Hid_No,link_id){"); 
            out.println("	HelpBox(Start,End,Hid_No,link_id);"); 
            out.println("}");
            
            out.println("function help_update_value_assign_99_newhelp(data) {"); 
            out.println("    document.Form1.TXT_RELATION_TYPE.value=data.colomn_2;"); 
            out.println("    document.Form1.TXT_NAME.value=data.colomn_3;"); 
            out.println("    document.Form1.TXT_DEFAULT_VALUE.value=data.colomn_4;"); 
            out.println("}"); 
            
            
            
            
            out.println("function setData(data){"); 
            out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
            out.println("		help_update_value_assign_99_newhelp(data);"); 
            out.println("		}"); 
				    out.println("		else if(document.Form1.hid_help_type.value==\"101\"){");  
            out.println("		help_update_value_assign_101(data);"); 
            out.println("		}"); 
            out.println("}");
            
            
            out.println("function clear_fields(){"); 
            out.println("		document.Form1.TXT_RELATION_TYPE.value='';"); 
            out.println("		document.Form1.TXT_NAME.value='';"); 
            out.println("		document.Form1.TXT_DEFAULT_VALUE.value='N';"); 
            out.println("}		");
            
            
            //end new help box functions
            
            
           out.println("function get_vector(data_vec) {");
	         out.println("		if(data_vec.length>0 && document.Form1.hid_display_client.value==\"1\"){");
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
					 out.println("		else if(data_vec.length==0 && document.Form1.hid_display_client.value==\"1\"){");
	         out.println("  		makeRequest(99);");
	         out.println("		}");
           out.println("}");
            
            
					 out.println("function makeRequest(m_val) {");
	         out.println("		if(m_val==\"1\"){");
	         out.println("			document.Form1.hid_display_client.value=1;");
	         out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations3?chksql=GET_PASSWORD_POLICY\";");
	         out.println("		}");
					 out.println("		else if(m_val==\"99\"){");
           out.println("			document.Form1.hid_display_client.value=99;");
           out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations3?chksql=idle\";");
           out.println("		}");
           out.println("    load_interface(m_url,'XML');");
           out.println("}");
            
            
            out.println("function makeRequest1(obj) {");
            out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations3?chksql=m_prime_chk_NETFAC_AF_MAS_display_relationship_types_name&data_val=\"+obj.value;");
            out.println("load_interface(m_url,'XML');");
            out.println("}");
            
            
            out.println("function validate_data(){"); 
            out.println("if(document.Form1.TXT_MIN_LENGTH.value==\"\"){  "); 
						out.println("document.getElementById('DIV_TXT_MIN_LENGTH').style.color='red';");
            out.println("document.Form1.TXT_MIN_LENGTH.focus()");
						out.println("error_msg1 = true;"); 
            out.println("return false;"); 
            out.println("}"); 
            out.println("else if(document.Form1.TXT_MAX_LENGTH.value==\"\"){  "); 
            out.println("document.getElementById('DIV_TXT_MAX_LENGTH').style.color='red';");
            out.println("document.Form1.TXT_MAX_LENGTH.focus()");
						out.println("error_msg1 = true;"); 
            out.println("return false;"); 
            out.println("}"); 
						out.println("if(document.Form1.TXT_EXPIRE_DAYS.value==\"\"){  "); 
						out.println("document.getElementById('DIV_TXT_EXPIRE_DAYS').style.color='red';");
            out.println("document.Form1.TXT_EXPIRE_DAYS.focus()");
						out.println("error_msg1 = true;"); 
            out.println("return false;"); 
            out.println("}"); 
            out.println("else if(document.Form1.TXT_EXPIRE_MSG.value==\"\"){  "); 
            out.println("document.getElementById('DIV_TXT_EXPIRE_MSG').style.color='red';");
            out.println("document.Form1.TXT_EXPIRE_MSG.focus()");
						out.println("error_msg1 = true;"); 
            out.println("return false;"); 
            out.println("}");
						out.println("else if(parseInt(document.Form1.TXT_EXPIRE_DAYS.value) == 0){");
						out.println("document.getElementById('DIV_TXT_EXPIRE_DAYS').style.color='red';");
            out.println("document.Form1.TXT_EXPIRE_DAYS.focus()");
						out.println("error_msg3 = true;"); 
						out.println("return false;"); 
            out.println("}"); 
						out.println("else if(parseInt(document.Form1.TXT_EXPIRE_MSG.value) >= parseInt(document.Form1.TXT_EXPIRE_DAYS.value)){");
						out.println("document.getElementById('DIV_TXT_EXPIRE_MSG').style.color='red';");
						//out.println("setTimeout('document.Form1.elements[\\'' + obj.name + '\\'].select()', 1);");
            out.println("document.Form1.TXT_EXPIRE_MSG.focus()");
						out.println("error_msg3 = false;"); 
						out.println("error_msg2 = true;"); 
						out.println("return false;"); 
            out.println("}"); 
						out.println("else if(parseInt(document.Form1.TXT_MAX_LENGTH.value) == 0){");
						out.println("document.getElementById('DIV_TXT_MAX_LENGTH').style.color='red';");
            out.println("document.Form1.TXT_MAX_LENGTH.focus()");
						out.println("error_msg4 = true;"); 
						out.println("return false;"); 
            out.println("}"); 
						out.println("else if(parseInt(document.Form1.TXT_MIN_LENGTH.value) >= parseInt(document.Form1.TXT_MAX_LENGTH.value)){");
						out.println("document.getElementById('DIV_TXT_MIN_LENGTH').style.color='red';");
            out.println("document.Form1.TXT_MIN_LENGTH.focus()");
						out.println("error_msg4 = false;"); 
						out.println("error_msg5 = true;"); 
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
            out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
            //out.println("		if(validate_data()){"); 
            out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_MAS_password_policy_save';");  
            out.println("		document.Form1.submit();	"); 
            //out.println("		}"); 
            out.println("		}"); 
            out.println("		}"); 
						out.println("else {");
            out.println("		if(error_msg1==true)"); 
						out.println("				alert('Please enter all required fields marked with a * on the screen')");
						out.println("		if(error_msg2==true)"); 
						out.println("				alert('Numbers of days to appear password expiry message can not be grater than number of days for passwords expiration');"); 
						out.println("		if(error_msg3==true)"); 
						out.println("				alert('Period for Password Expiration can not be zero')");
						out.println("		if(error_msg4==true)"); 
						out.println("				alert('Maxlength Length can not be zero');"); 
						out.println("		if(error_msg5==true)"); 
						out.println("				alert('Minimum length can not be grater than Maximum length.');"); 
						out.println("} "); 
            out.println("} "); 
						
						out.println("function save_window(){	"); 
            out.println("before_submit();"); 
            out.println("}"); 

            
            out.println("function assign_data(data_vec){ ");
            out.println("    document.Form1.TXT_RELATION_TYPE.value=data_vec[0];"); 
            out.println("    document.Form1.TXT_NAME.value=data_vec[1];"); 
            out.println("    document.Form1.TXT_DEFAULT_VALUE.value=data_vec[2];"); 
            out.println("}");
            
            //out.println("function load_lock(){	"); 
            //out.println("document.oncontextmenu=new Function(\"return false\");"); 
            //out.println("}	"); 
            
            out.println("function clear_window(){	"); 
            out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
            out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_password_policy_display';"); 
            out.println("		}"); 
            out.println("}"); 
            
            out.println("function new_window(){	"); 
            out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_password_policy_display';"); 
            out.println("}"); 
            out.println(""); 
            out.println(""); 
            
            
            out.println(""); 
            
            out.println("function load_help_msg() {"); 
            out.println("    m_help_message = \"m_help_msg_NETFAC_AF_MAS_display_relationship_types\";"); 
            out.println("    HelpBox_msg(m_help_message);"); 
            out.println("}"); 	
            out.println("function HelpBox_msg(m_help_message) {"); 
            out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
            out.println("  \"&help_message_in=\"+m_help_message);"); 
            out.println("}"); 
            
            out.println("function load_roll_value(m_val){"); 
            out.println("document.getElementById('help_box').innerHTML=\" System Administration - Password Policy - \"+m_val;"); 
            out.println("}"); 
            out.println(""); 
			
						out.println("function load_rights(){"); 
						out.println("check_user_rights('"+m_screen_url+"','"+username+"','"+company_name+"');"); //Added by Chandana on 08/06/2010
            out.println("}"); 
            out.println("");
            
            out.println("function load_roll_out_value(){");
            out.println("document.getElementById('help_box').innerHTML=\" System Administration - Password policy - \"+document.Form1.hid_status.value;"); 
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
            out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 
            
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
            
            
            out.println(" function Close(){");
            out.println("clear_data()	");
            out.println(" }");
            
            
            out.println("function clear_data() {");
            out.println("document.Form1.TXT_RELATION_TYPE.value='';"); 
            out.println("document.Form1.TXT_RELATION_TYPE.focus() ;"); 
            out.println("document.Form1.TXT_NAME.value='';"); 
            out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';"); 
            out.println("}");
            
            
            out.println("function help_update(link_id) {");
            out.println("   if(!isOpen){");     
            out.println("      isOpen=true;");  
            //	out.println("bttn_press=1");
            out.println("    document.Form1.hid_help_type.value=\"99\";"); 
            out.println("    m_sql = \"m_help_TXT_RELATION_TYPE_sql\";"); 
            //out.println("			var bank=document.Form1.TXT_BANK_CODE.value.substring(0,1)");
            
            out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
            out.println("    m_criteria = document.Form1.TXT_RELATION_TYPE.value+\"@\"+\"Y@\";"); 
            out.println("    } ");
            out.println("    else{");
            out.println("    m_criteria = document.Form1.TXT_RELATION_TYPE.value+\"@\"+\"N@\";}"); 
            
            out.println("    HelpBox('1','10','0',link_id);"); 
            out.println("}");
            out.println("}");
            
            
            out.println("function help_update_desc(link_id) {"); 
            out.println("   if(!isOpen){");     
            out.println("      isOpen=true;"); 
            out.println("    document.Form1.hid_help_type.value=\"100\";"); 
            out.println("    m_sql = \"m_help_TXT_RELATION_TYPE_NAME_sql\";"); 
            out.println("   var bank =document.Form1.TXT_NAME.value.substring(0,1) ;");
            out.println("    m_criteria = document.Form1.TXT_NAME.value+\"@\";"); 
            out.println("    HelpBox('1','10','1',link_id);"); 
            out.println("}"); 
            out.println("}"); 
				
				// Added by Udar Somathilake on 2-6-2010
				out.println("function help_update_code(link_id) {"); 
            out.println("   if(!isOpen){");     
            out.println("      isOpen=true;"); 
            out.println("    document.Form1.hid_help_type.value=\"101\";"); 
            out.println("    m_sql = \"m_help_TXT_RELATION_TYPE_CODE_sql\";"); 
            out.println("    m_criteria = document.Form1.TXT_RELATION_TYPE.value+\"@\";"); 
            out.println("    HelpBox('1','10','1',link_id);"); 
            out.println("}"); 
            out.println("}"); 
				
				out.println("function help_update_value_assign_101() {"); 
            //out.println("    document.Form1.TXT_RELATION_TYPE.value='';");
				out.println("				new_window();"); 
            out.println("}"); 
            
            
            out.println(" function assign_help_status(obj){");
            //out.println(" alert('ok');");
            out.println(" document.Form1.hid_help_status.value =obj; ");
            out.println("bttn_press=0");
            out.println("enter_pres=0");
            out.println("}");
            
            out.println("function help_update_value_assign_99() {"); 
            out.println("    document.Form1.TXT_RELATION_TYPE.value=oBj.valout[2];"); 
            out.println("    document.Form1.TXT_NAME.value=oBj.valout[3];"); 
            out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[4];"); 
            
            out.println("}"); 
            
            out.println("function init_help_view() { ");   // This Method should call after every dynamic element addition 
            out.println("   $(\"a.helpview\").fancybox({"); 
            out.println("    'hideOnContentClick': false, 'width':750 , 'height': 400,"); 
            out.println("    'overlayShow': true, ");
            out.println("    'changeSpeed':0, 'speedIn':200, 'speedOut':200,"); 
            out.println("    'opacity':true,'onClosed' : clearIsOpen, 'centerOnScroll':true, 'easingIn':'swing', "); 
            out.println("    'easingOut':'swing' ,transitionIn:'elastic' ,transitionOut:'elastic',"); 
            out.println("    'type':'iframe','showCloseButton':true,'enableEscapeButton':true,'modal':false,'overlayOpacity':0.1");
            out.println("  });"); 
            out.println("}"); 
            
            
            out.println("$(document).ready(function() { ");
            out.println("  init_help_view();"); 
            out.println("});"); 
            
            
            out.println("$('a').live('click', function() {"); 
            out.println("  init_help_view();");			
            out.println("}); ");  
            
            out.println("function HelpView(Start,End,Hid_No,link_view_id) {");
            out.println("     document.getElementById(link_view_id).href=servlet_client_url+\":\"+client_t3_port+\"/"+m_fschema_name+"FA_MAS_View_Help_Servlet?class_in=\"+client_name+\"FA_MAS_View_help_select\"+"); 
            out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
            out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
            out.println("    \"&Hid_No=\"+Hid_No+\"&link_id=\"+link_view_id;");
            out.println("    $(\"#\"+link_view_id).trigger(\"click\");");
            out.println("}");
            
            out.println("function viewPrev(Start,End,Hid_No,link_view_id){"); 
            out.println("	HelpView(Start,End,Hid_No,link_view_id);"); 
            out.println("}"); 
            
            
            out.println("function viewNext (Start,End,Hid_No,link_view_id){"); 
            out.println("	HelpView(Start,End,Hid_No,link_view_id);"); 
            out.println("}");
            
            out.println("function help_view_Check(valout,Hid_No,link_view_id){");
            out.println("	if(valout[1]==\"Next\"){"); 
            out.println("		viewNext(valout[2],valout[3],Hid_No,link_view_id);"); 
            out.println("	} "); 
            out.println("	else if(valout[1]==\"Prev\"){"); 
            out.println("		viewPrev(valout[2],valout[3],Hid_No,link_view_id);"); 
            out.println("	}	"); 
            out.println("}  ");
            
            
            out.println("function View_all(link_id){");	
            out.println("   if(!isOpen){");     
            out.println("      isOpen=true;");
            out.println("    m_sql = \"m_help_TXT_RELATION_TYPE_sql\";");
            out.println("    m_criteria = document.Form1.TXT_RELATION_TYPE.value+\"@\"+\"Y@\";"); 
            out.println("    HelpView('1',50,'0',link_id);"); 
            out.println("}"); 
            out.println("}"); 
            
						/*
						out.println("var isPasswordLengthChecked = false;");
						out.println("var isExpirationDaysChecked = false;");

						out.println("function checkPasswordLength(obj) {");
						// out.println("		alert(validateNumber(obj, 0, 999, 1, 'number'));"); 
						out.println("		var isNumberValidated = validateNumber(obj, 0, 999, 0, 'number');");
						out.println("		if(isNumberValidated == false) {");
						out.println("			return false;"); 
						out.println("		}");
						// out.println("		else if((isNumberValidated == true) && (obj.value == '0')) {");
						// out.println("			alert('Value should be larger than zero.');"); 
						// out.println("			setTimeout('document.Form1.elements[\\'' + obj.name + '\\'].select()', 1);");
						// out.println("			return false;"); 
						// out.println("		}"); 
						
						// out.println("		if(isPasswordLengthChecked == false) {"); 
						// out.println("			isPasswordLengthChecked = true;"); 
						// out.println("		}");
						// out.println("		else if(isPasswordLengthChecked == true) {"); 
						// out.println("			isPasswordLengthChecked = false;"); 
						// out.println("		}");
						
						out.println("		var min_length = parseInt(document.Form1.TXT_MIN_LENGTH.value);"); 
						out.println("		var max_length = parseInt(document.Form1.TXT_MAX_LENGTH.value);"); 
						out.println("		if(isPasswordLengthChecked == false) {"); 
						out.println("			if(isNumberValidated == true) {");
						out.println("				if(min_length > max_length){"); 
						out.println("					isPasswordLengthChecked = true;"); 
						out.println("					alert('Minimum length can not be grater than Maximum length.');"); 
						out.println("					setTimeout('document.Form1.elements[\\'' + obj.name + '\\'].select()', 1);");
						out.println("				}"); 
						out.println("			}");
						out.println("		}"); 
						out.println("		else if(isPasswordLengthChecked == true) {");
						out.println("			isPasswordLengthChecked = false;"); 
            out.println("		}"); 
            out.println("}"); 
						
						out.println("function checkExpirationDays(obj){");
						out.println("var exp_msg = parseInt(document.Form1.TXT_EXPIRE_MSG.value);"); 
						out.println("var exp_days = parseInt(document.Form1.TXT_EXPIRE_DAYS.value);"); 
						//out.println("if(isExpirationDaysChecked == false){"); 
						out.println("if(exp_msg >= exp_days){");
						out.println("isExpirationDaysChecked = true;"); 
						out.println("alert('Numbers of days to appear password expiry message can not be grater than number of days for passwords expiration.');"); 
						out.println("setTimeout('document.Form1.elements[\\'' + obj.name + '\\'].select()', 1);");
						out.println("}"); 
						//out.println("}"); 
						//out.println("else {");
						//out.println("isExpirationDaysChecked = false;"); 
						//out.println("}"); 
            out.println("}"); 
						*/
						
            out.println("</script>"); 
            out.println("</HEAD>");
            
            out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_rights(),load_roll_value('New');makeRequest(1)\">"); 
            out.println("<FORM NAME='Form1' method='post'>"); 
            out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
            out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
            out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
            out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
						out.println("<INPUT TYPE='Hidden' NAME='hid_display_client' VALUE=\"99\">");

            out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
            
            out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
            out.println("<tr>"); 
            out.println("<td width='8' valign='top'></td>");   //SANJEEWA 2010/06/14
            out.println("<td class='border_wht' valign='top'> "); 
            out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
            out.println("<tr> "); 
            out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
            out.println("</tr>"); 
            out.println("<tr> "); 
            out.println("<td height='1'></td>");   //SANJEEWA 2010/06/14
            out.println("</tr>"); 
            out.println("<tr>"); 
            out.println("<td");   //sanjeewa 2010/06/14
            out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
            out.println("<tr>"); 
            out.println("<td height='1'></td>");  //sanjeewa 2010/06/14
            out.println("</tr>"); 
            out.println("<tr>"); 
            out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Password Poloicy</td>"); 
            out.println("</tr>"); 
            out.println("<tr>"); 
            out.println("<td  height='10px' class='pdn_txtpos'>"); 
            out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
            out.println("<tr><td width='10%' align='center'><input type=\"button\" id=\"BUT_NEW\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
            //out.println("<td width='10%' align='center'><input type=\"button\" id=\"BUT_EDIT\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
            //out.println("<td width='10%' align='center'><input type=\"button\" id=\"BUT_DEACT\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
            //out.println("<td width='10%' align='center'><input type=\"button\" id=\"BUT_REACT\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
            //out.println("<td width='10%' align='center'><input type=\"button\" id=\"BUT_VIEW\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all(\"view_all\")' value=\"View All\"><a onClick=\"View_all('view_all')\" style=' text-decoration: none;cursor: text;' id=\"view_all\" class=\"helpview iframe\" href=\"\"> </a></td>");
            //out.println("<td width='6%'></td>");  
            out.println("<td width='10%' align='center'><input type=\"button\" id=\"BUT_SAVE\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
            out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
            out.println("<td width='100%'></td>"); 
            out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
            out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
            
            out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
            out.println("</table>");  
            out.println("</td></tr><tr>");  
            out.println("<td class='line' height='1'></td>");    //SANJEEWEA 2010/06/14
            out.println("</tr><tr>");  
            out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
            
						out.println("<br >");
            
            out.println("<table align='center' width='100%' class='table'>"); 
            
            out.println("<tr >"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_LENGTH'  class=div_input>Minimum Length *</DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_LENGTH' maxlength='5' size='5'  onBlur=\"validateNumber(this, 0, 999, 0, 'number');\"></td>"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_MAX_LENGTH'  class=div_input>Maxlength Length *</DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MAX_LENGTH' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number'); \" ></td>"); 
            out.println("</tr>"); 
						
						out.println("<tr >"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_LOWER'  class=div_input>Minimum Number of Lowercase Char </DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_LOWER' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number')\"></td>"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_UPPER'  class=div_input>Minimum Number of Uppercase Char </DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_UPPER' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number')\"></td>"); 
						out.println("<td width='*%'></td>"); 
            out.println("</tr>"); 
						
            out.println("<tr >"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_NUM'  class=div_input>Minimum Number of Numeric Char </DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_NUM' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number')\"></td>"); 
            out.println("<td width='30%' >Numbers be Embedded</td>");
						out.println("<td width='20%' >");
						out.println("<select name='TXT_NUM_EMBED' class=\"txt_input5\" style= 'text-align:left;' >");
            out.println("<OPTION value=\"Y\" >Yes </option>");
            out.println("<OPTION value=\"N\" SELECTED >No </option>");
            out.println("</SELECT></td>");
            out.println("</tr>"); 
						
            out.println("<tr >"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_SPECIAL'  class=div_input>Minimum Numbers of Special Char </DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_SPECIAL' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number')\"></td>"); 
            out.println("<td width='30%' >Special Char be Embedded</td>");
						out.println("<td width='20%'>");
						out.println("<select name='TXT_CHAR_EMBED' class=\"txt_input5\" style= 'text-align:left;'>");
            out.println("<OPTION value=\"Y\" >Yes </option>");
            out.println("<OPTION value=\"N\" SELECTED >No </option>");
            out.println("</SELECT></td>");
            out.println("</tr>"); 
						
						out.println("<tr >"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_CHANGE_ATTEMPT'  class=div_input>Number of Password Change Attempts </DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_CHANGE_ATTEMPT' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number')\"></td>"); 
            out.println("<td width='30%' >Allow User Name in Password</td>");
						out.println("<td width='20%'>");
						out.println("<select name='TXT_USER_NAME_ALLOW' class=\"txt_input5\" style= 'text-align:left;'>");
            out.println("<OPTION value=\"Y\" >Yes </option>");
            out.println("<OPTION value=\"N\" SELECTED >No </option>");
            out.println("</SELECT></td>");
            out.println("</tr>"); 

            out.println("<tr >"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_REPEAT_PW'  class=div_input>Allow Repeat a Password After How Many Passwords </DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_REPEAT_PW' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number')\"></td>"); 
            out.println("<td width='30%' ><DIV id='DIV_TXT_MIN_GAP'  class=div_input>Minimum Gap Before Subsequent Password Change in Minutes </DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_MIN_GAP' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number')\"></td>"); 
            out.println("</tr>"); 
						
						out.println("<tr >"); 
						out.println("<td width='30%' ><DIV id='DIV_TXT_EXPIRE_DAYS'  class=div_input>Period for Password Expiration in Days *</DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_EXPIRE_DAYS' maxlength='5' size='5' onBlur=\"validateNumber(this, 0, 999, 0, 'number'); \" ></td>"); //checkExpirationDays(this);
            out.println("<td width='30%' ><DIV id='DIV_TXT_EXPIRE_MSG'  class=div_input>Number of Days Prior to Expiration Date Should the Password Expiry Warning Message Appear *</DIV></td>"); 
            out.println("<td width='20%' ><input class='txt_input5' type='text' name='TXT_EXPIRE_MSG' maxlength='5' size='5' onBlur=\" validateNumber(this, 0, 999, 0, 'number'); \" ></td>"); 
            out.println("</tr>"); 
						
            out.println("</table>"); 
            out.println("<br>"); 
            out.println("<table align='center' width='100%'>"); 
            out.println("<tr>"); 
            out.println("<td width='100%' class='note'></td>"); 
            out.println("</tr>"); 
            out.println("</table>"); 
            out.println("</form>"); 
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
