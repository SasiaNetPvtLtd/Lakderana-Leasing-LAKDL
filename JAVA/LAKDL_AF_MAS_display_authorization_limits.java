//ID         : 1.73 AUTHORIZATION LIMITS PROCESS
//SCREEN NAME:SYSTEM ADMINISTRATION - AUTHORIZATION LIMITS
//CREATED BY: NUWAN DE SILVA
//DATE/TIME : 25-07-06
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_authorization_limits extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	
	Connection conn;
	Statement stmt;
	public ResultSet rs;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_schema_name=m_sn_methods.schema_name.trim();

			stmt = conn.createStatement();

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Authorization Limits</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			/*out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_USER_ID.value!=\"\"){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");*/
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_USER_ID.value!=\"\"  && document.Form1.hid_val.value=='t1'){");
			out.println("	help_update()");
			out.println("			}");

			out.println("			if(data_vec.length > 0  && document.Form1.TXT_USER_ID.value!=\"\" && document.Form1.TXT_AUTHORIZATION_LEVEL.value !=\"\" && document.Form1.hid_val.value=='t2'){");
			out.println("	alert('Record already exsits')");
			//out.println("    document.Form1.TXT_AUTHORIZATION_LEVEL.value=\"\";"); 
			out.println("    document.Form1.TXT_AUTHORIZATION_LEVEL.selectedIndex=[0];");			
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_USER_ID.value!=\"\" && document.Form1.hid_val.value=='t1' ){");
			out.println("	help_update()");
			out.println("			}");
			//out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_USER_ID.value!=\"\" && document.Form1.hid_val.value=='t1' ){");
			//	out.println("	help_update()");

			//out.println("			}");
			
			out.println("}");
			out.println("function makeRequest(obj) {");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_authorization_limits&data_val=\"+obj.value;");
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_authorization_limits&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			//out.println("window.open(m_url)");
			out.println("}");
			//out.println("else");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_authorization_limits_r&data_val1=\"+document.Form1.TXT_AUTHORIZATION_LEVEL.value+\"&data_val=\"+obj.value;");
			//out.println("window.open(m_url)");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function makeRequest2(obj) {");
			//out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_authorization_limits_r&data_val=\"+document.Form1.TXT_USER_ID.value+\"&data_val1=\"+obj.value;");
			//out.println("window.open(m_url)");
		
			out.println("load_interface(m_url,'XML');");
			//out.println("}");
	out.println("}");
			/*out.println("function makeRequest(obj) {");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_authorization_limits&data_val=\"+obj.value;");
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_authorization_limits&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("else");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_authorization_limits_r&data_val=\"+obj.value;");
			
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			*/

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_USER_ID.value==\"\"){  "); 
			out.println("DIV_TXT_USER_ID.style.color='red';");
			out.println("document.Form1.TXT_USER_ID.focus()"); 
			out.println("return false;"); 
			out.println("}"); 
			/*out.println("else if(document.Form1.TXT_AUTHORIZATION_LEVEL.value==\"\"){  "); 
			out.println("DIV_TXT_AUTHORIZATION_LEVEL.style.color='red';");
			//out.println("document.Form1.TXT_AUTHORIZATION_LEVEL.focus()"); 
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LIMIT.value==\"\"){  "); 
			out.println("DIV_TXT_LIMIT.style.color='red';");
			out.println("document.Form1.TXT_LIMIT.focus()"); 

			out.println("return false;"); 
			out.println("}"); 
			*/
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		if(validate_data()){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");

			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_authorization_limits?number='+document.Form1.hid_count.value+'';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}");
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			
			//Purpose  : validate no
			//Added By : Nuwan De Silva
			//Date     : 25-jul-2006
			
			out.println("function validate_no(){");
			/*out.println("m_no=m_val.toString();");
			out.println("m_len=m_val.toString().length;");
								    					
			out.println("for(var i=0;parseInt(i)<m_len;i++){");
										  
			out.println("var oneChar =m_no.charAt(i);");
										
									out.println("if(oneChar >\"9\" || oneChar< \"0\"){");
												out.println("alert('Should Contain Numeric Values');");
												out.println("document.Form1.TXT_LIMIT.value=\"\"");
												out.println("document.Form1.TXT_LIMIT.focus()");
												out.println("break;");
									 		  out.println("}");
			out.println("}"); */
			
			out.println("if(document.Form1.TXT_LIMIT.value!=\"\"){");
			out.println("format_number2(document.Form1.TXT_LIMIT,25)");
			out.println("}");
			
			out.println("}");

			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"||document.Form1.SCREEN_NAME.value==\"EDIT\"||document.Form1.SCREEN_NAME.value==\"DACT\" ){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"RACT\" ){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");

			out.println("}");
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_authorization_limits';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_authorization_limits';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_authorization_limits\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Authorization Limits - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Authorization Limits - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_AUTHORIZATION_LEVEL.disabled=true;"); 
			out.println("document.Form1.TXT_LIMIT.disabled=true;"); 
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
			out.println("if(oBj.valout[2]==' '){");//**
			//out.println("Close();"); 
					out.println("	clear_data();");	
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      out.println("window.close();");
      out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");

			//out.println("if(city_arry.length==0 && document.Form1.hid_val.value=='t1' || document.Form1.hid_val.value=='t3') {");//**
			out.println("    document.Form1.TXT_USER_ID.value='';"); 
			out.println("    document.Form1.TXT_AUTHORIZATION_LEVEL.selectedIndex=[0];"); 
			out.println("    document.Form1.TXT_LIMIT.value='';"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';"); 
			out.println("}");
			//out.println("if(document.Form1.hid_help_type.value==\"2\"){");
			//out.println("if(document.Form1.TXT_CITY_CODE.value!='' && dist_arry.length==0 && document.Form1.hid_val.value=='t2') {");//**
			//out.println("document.Form1.TXT_DISTRICT_CODE.value='';");
			//out.println("document.Form1.TXT_DISTRICT_CODE.focus();"); 
			//out.println("}");
			out.println("}");



			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			//out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\" || document.Form1.SCREEN_NAME.value==\"RACT\" ){ ");

			out.println("    m_sql = \"m_help_TXT_USER_ID_Authorization_sql\";"); 
			out.println("}");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("}");
			
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" || document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"N@\";}"); 
			//out.println("    HelpBox('1','10','2');");  // commented by udara 28-02-2017
			out.println("    HelpBox('1','10','8');"); // added by udara 28-02-2017
			out.println("}"); 
			
			out.println("function makeRequest_sub_charges() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_authorization_limits_details?chksql=auth_limit_details&user_id=\"+document.Form1.TXT_USER_ID.value+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function get_vector_normal(http_response) {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" sub_charge_details.innerHTML = ''; ");
			out.println(" sub_charge_details.innerHTML = http_response; ");
			out.println(" ");
  			out.println("}");
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\" || document.Form1.SCREEN_NAME.value==\"RACT\"){ ");

			out.println("    document.Form1.TXT_USER_ID.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_AUTHORIZATION_LEVEL.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_LIMIT.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[5];"); 
			out.println("}");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    document.Form1.TXT_USER_ID.value=oBj.valout[2];"); 
			out.println("}"); 
			out.println(" makeRequest_sub_charges()");
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
			out.println("    m_sql = \"m_view_TXT_USER_ID_Authorization_sql\";");
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			out.println("function change(row_no){")	;
			out.println("m_chk_status=\"CHK_STATUS_\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("}");	
			out.println("}");	
			
			out.println("function check_amt(row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"TXT_AMOUNT_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("if(document.Form1.elements[nt].value!=\"\"){");
			out.println("format_number(document.Form1.elements[nt],25)");
			out.println("}");
			//out.println("else if(document.Form1.elements[nt].value==\"\"){");
			//out.println("alert('Please enter limit');");
			//out.println("document.Form1.elements[nt].value=\"\""); 
			//out.println("}");
			out.println("}");

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"Save\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"Save\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Authorization Limits</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");
			/*out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  */
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

			out.println("<td width='10%' ><DIV id='DIV_TXT_USER_ID'  class=div_input>User ID *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_USER_ID' maxlength='10' size='10' onblur=\"assig('t1'),makeRequest(document.Form1.TXT_USER_ID)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			


	
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AUTHORIZATION_LEVEL'  class=div_input>Authorization Level *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_AUTHORIZATION_LEVEL' maxlength='10' size='10' onblur=\"assig('t2'),makeRequest2(document.Form1.TXT_AUTHORIZATION_LEVEL)\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");*/
			/*
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AUTHORIZATION_LEVEL'  class=div_input>Authorization Level *</DIV></td>"); 
			out.println("<td width='40%'>");
			out.println("<select name=\"TXT_AUTHORIZATION_LEVEL\" class=\"txt_input\" style='{width:250px}' onchange=\"assig('t2'),makeRequest2(document.Form1.TXT_AUTHORIZATION_LEVEL)\">");
			
							
			rs= stmt.executeQuery ("SELECT DISPLAY_NAME, POSITION "+
			"FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			"WHERE DISPLAY_STATUS='Y' AND POSITION IS NOT NULL "+
			"ORDER BY POSITION ASC ");

			boolean more = rs.next();		
			while(more){
			out.println("<OPTION value=\""+rs.getString(2)+"\" selected>"+rs.getString(1)+"</option>");
			more=rs.next();
		
			if(!more){
			break;
			}
			
			}
			out.println("</SELECT></td>");
			out.println("</tr>"); 		

			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LIMIT'  class=div_input>Authorization Limit *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LIMIT' maxlength='21' size='25' onblur=\"validate_no()\"></td>"); 
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
			
			out.println("<table align=\"LEFT\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=sub_charge_details></div></td></tr></table>");

			
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
