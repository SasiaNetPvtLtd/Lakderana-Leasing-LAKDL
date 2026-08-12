//--
//SCREEN NAME:SYSTEM ADMINISTRATION - MODEL 
//ID:1.41 Model  Process
//CREATED BY:N.V.P.Chandana
//DATE/TIME:24-07-2006/3.16pm
//NOTES:
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MAS_display_model_creation  extends javax.servlet.http.HttpServlet { 
	
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
			
			//(2007-01-31 -  modified by Delanjali)******************************************************************************************************************
			//Purpose - To call from reference administration screen ************************************************************************************************
			
			String m_code="";
			String m_close_status="x";
			String m_="";
			m_code= req.getParameter("code");
			m_close_status= req.getParameter("CLSTATUS");
			if(m_close_status==null){
				m_close_status="x";
			}
			
			//*******************************************************************************************************************************************************
			
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Model Creation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value =='H9' ){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			out.println("    else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_MODEL_CODE.value !=''&& document.Form1.hid_help_status.value =='H9' ){");
			out.println("    assign_data(data_vec);");
			out.println("   }");
			out.println("    else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value==\"EDIT\"&& document.Form1.TXT_MODEL_CODE.value !=''&& document.Form1.hid_help_status.value =='D1' ){");
			out.println("    assign_data(data_vec);");
			out.println("   }");
			out.println("    else if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_MODEL_CODE.value !=''&& document.Form1.hid_help_status.value =='H9' ){");
			out.println("   help_update();");
			out.println("   }");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value =='H1'&& document.Form1.TXT_MAKE_CODE.value !='' ){");
			out.println("   help_button_1();");
			out.println("   }");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value =='H2' && document.Form1.TXT_FUEL_TYPE.value !='' ){");
			out.println("   help_button_2();");
			out.println("   }");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value =='H4' && document.Form1.TXT_SUB_CAT_CODE.value !='' ){");
			out.println("   help_button_4();");
			out.println("   }");
			out.println("			else");
			out.println("			if(data_vec.length>0  && document.Form1.TXT_DESCRIPTION.value!=\"\" && document.Form1.hid_help_status.value =='H3' && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("				alert('Record already exists');");
			out.println("     help_update_desc();");
			out.println("			}");
			out.println("    else if(data_vec.length == 0 && document.Form1.hid_help_status.value =='H_COUNTRY' && document.Form1.TXT_COUNTRY_CODE.value !='' ){");
			out.println("   help_button_country();");
			out.println("   }");
			
			out.println("   }");
			
			
			
			out.println("function makeRequest(obj) {");
			out.println("    if(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation1&data_val=\"+obj.value;");
			out.println("}");
			out.println("    else");
			out.println("    if((document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value!=\"RACT\")&&(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value!=\"NEW\")){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("    else");
			out.println("    if(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("    else");						
			out.println("    if(document.Form1.hid_help_status.value =='H1')");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_make_creation&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("    else");
			out.println("    if(document.Form1.hid_help_status.value =='H2')");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_fual&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("    else");
			out.println("    if(document.Form1.hid_help_status.value =='H3' && document.Form1.SCREEN_NAME.value!=\"NEW\")");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation_desc&data_val=\"+obj.value;");
			out.println("    else");
			out.println("    if(document.Form1.hid_help_status.value =='H4')");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_Sub_Cat_Code&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("    else");
			out.println("    if(document.Form1.hid_help_status.value =='D1')");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation&data_val=\"+obj.value+\"&ac_status=P\";");
			
			out.println("    else if(document.Form1.hid_help_status.value == 'H_COUNTRY') ");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_country&data_val=\"+obj.value;");
			
			out.println("    load_interface(m_url,'XML');");
			//out.println("    window.open(m_url);");
			out.println("    }");
			
			
			out.println("   function assign_data(data_vec) { ");
			out.println("    document.Form1.TXT_MODEL_CODE.value=data_vec[0]; ");
			out.println("    document.Form1.TXT_DESCRIPTION.value=data_vec[1]; ");
			//out.println(" 	 alert('data_vec2 @@ '+data_vec[2])	");
			//Modified by Mahela on 15-05-2007
			out.println("if(data_vec[2]=='null'){");
			out.println("data_vec[2]=\"\"");
			out.println("}");
			out.println("    document.Form1.TXT_MAKE_CODE.value=data_vec[2]; ");
			out.println("if(data_vec[3]=='null'){");
			out.println("data_vec[3]=\"\"");
			out.println("}");
			out.println("    document.Form1.TXT_FUEL_TYPE.value=data_vec[3]; ");
			out.println("if(data_vec[4]=='null'){");
			out.println("data_vec[4]=\"\"");
			out.println("}");
			out.println("    document.Form1.TXT_TAX_RATE.value=data_vec[4]; ");
			out.println("    document.Form1.TXT_TAX_FOR_LEASE.value=data_vec[5]; ");
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=data_vec[6]; ");
			out.println("if(data_vec[7]=='null'){");
			out.println("data_vec[7]=\"\"");
			out.println("}");
			out.println("    document.Form1.TXT_SUB_CAT_CODE.value=data_vec[7]; ");
			out.println("    document.Form1.TXT_MAKE_DESCRIPTION.value=data_vec[8]; ");
			
			// _________ Added by nuwan de silva on 10-12-2007 __________________
			out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_OPTION_TYPE.value=data_vec[10];"); 
			out.println("    document.Form1.TXT_COUNTRY_CODE.value=data_vec[11];"); 
			out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=data_vec[12];"); 
			out.println("} "); 
			
			
			out.println(" function assign_help_status(obj){");
			out.println("    document.Form1.hid_help_status.value =obj; ");
			out.println("    }");
			
			// Added by Udara Somathilake on 16/10/2009
			out.println("function help_eng_cap() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_CAPACITY_DESC_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ENGINE_CAPACITY.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			// Added by Udara Somathilake on 16/10/2009
			out.println("function help_value_assign_6() {"); 
			//out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=oBj.valout[2];"); //commented by udara on 17-04-2012
			out.println("    var eng_capacity = ''; ");
			out.println("    eng_capacity = oBj.valout[2]; ");
			out.println("    eng_capacity = eng_capacity.replace(\"CC\",\"\"); ");
			out.println("    eng_capacity = eng_capacity.replace(\"cc\",\"\"); ");
			out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=eng_capacity;");
			out.println("}");
			// Added by Udara Somathilake on 16/10/2009
			out.println("function help_opt_types() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_OPTION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_OPTION_TYPE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			// Added by Udara Somathilake on 16/10/2009
			out.println("function help_value_assign_7() {"); 
			out.println("    document.Form1.TXT_OPTION_TYPE.value=oBj.valout[3];"); 
			out.println("    document.Form1.HID_TXT_OPTION_TYPE.value=oBj.valout[2];"); // added by udara on 24-02-2012
			out.println("}");
			
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DESCRIPTION.value==\"\"){  "); 
			out.println("DIV_TXT_DESCRIPTION.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MAKE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_MAKE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FUEL_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_FUEL_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TAX_RATE.value==\"\"){  "); 
			out.println("DIV_TXT_TAX_RATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TAX_FOR_LEASE.value==\"\"){  "); 
			out.println("DIV_TXT_TAX_FOR_LEASE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			// __________ added by nuwan de silva on 10-12-2007 ____________________
			out.println("else if(document.Form1.TXT_ENGINE_CAPACITY.value==\"\"){  "); 
			out.println("DIV_TXT_ENGINE_CAPACITY.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_OPTION_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_OPTION_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_COUNTRY_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_COUNTRY_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_YEAR_OF_MANUFACTURE.value==\"\"){  "); 
			out.println("DIV_TXT_YEAR_OF_MANUFACTURE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			// __________ end by nuwan de silva on 10-12-2007 _________________________
			
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
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_model_creation ';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}");
			out.println("		}");
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			out.println(" if('"+m_close_status+"'=='A'){");
			out.println("document.Form1.TXT_MODEL_CODE.value='"+m_code+"'");
			out.println("document.Form1.SCREEN_NAME.value='EDIT';"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false"); 
			
			out.println("assign_help_status('D1')");
			out.println("makeRequest(document.Form1.TXT_MODEL_CODE)");
			out.println("}"); 
			
			out.println(" if('"+m_close_status+"'=='B'){");//Added by Chandana on 15/11/2007
			out.println("document.Form1.hid_close_status.value='Y'");
			//out.println("alert('aaaaa'+document.Form1.hid_close_status.value);"); 
			out.println("}"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_model_creation ';"); 
			
			out.println(" if('"+m_close_status+"'=='B'){");//Added by Chandana on 15/11/2007
			out.println("document.Form1.hid_close_status.value='Y'");
			//out.println("alert('aaaaa'+document.Form1.hid_close_status.value);"); 
			out.println("}"); 
			
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function new_window(){	"); 
			out.println("if(document.Form1.hid_close_status.value=='Y'){");			
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_model_creation?CLSTATUS=B';"); 
			out.println("}else {"); 			
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_model_creation ';"); 
			out.println("}"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_model_creation \";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println(" if('"+m_close_status+"'=='A'){");
			out.println("m_val='Edit'");
			out.println("}");
			out.println("help_box.innerHTML=\" System Administration - Model Creation - \"+m_val;"); 
			out.println("}"); 		
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Model Creation - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_MAKE_DESCRIPTION.disabled=true;");
			out.println("document.Form1.TXT_MAKE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FUEL_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_TAX_RATE.disabled=true;"); 
			out.println("document.Form1.TXT_TAX_FOR_LEASE.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_CAT_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_MAKE_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_FUEL_TYPE.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_SUB_CODE.disabled=true;"); 
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			//out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			//out.println("document.Form1.elements[i].disabled=false;");
			//out.println("}");
			out.println("document.Form1.ITEM_SUB_LINK_BUT.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
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
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); // Added by Udara Somathilake on 16/10/2009
			out.println("		help_value_assign_6();"); 
			out.println("		}");
			out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); // Added by Udara Somathilake on 16/10/2009
			out.println("		help_value_assign_7();"); 
			out.println("		}");
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		assign_country();"); 
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
			
			
			out.println("function clear_data() {");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("document.Form1.TXT_MODEL_CODE.value='';"); 
			out.println("document.Form1.TXT_MODEL_CODE.focus();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("document.Form1.TXT_MAKE_CODE.value='';"); 
			out.println("document.Form1.TXT_MAKE_CODE.focus();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("document.Form1.TXT_FUEL_TYPE.value='';"); 
			out.println("document.Form1.TXT_FUEL_TYPE.focus();"); 
			out.println("		}"); 
			
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("document.Form1.TXT_DESCRIPTION.focus();"); 
			out.println("		}"); 
			
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("document.Form1.TXT_SUB_CAT_CODE.value='';"); 
			out.println("document.Form1.TXT_SUB_CAT_CODE.focus();"); 
			out.println("		}"); 
			
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println(" document.Form1.TXT_COUNTRY_CODE.value='';"); 
			out.println(" document.Form1.TXT_COUNTRY_CODE.focus();"); 
			out.println("		}"); 
			
			out.println("}");
			
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next(Start,End,Hid_No){");
			// out.println(" alert('xxxx');");
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_MAKE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_MAKE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_MAKE_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_MAKE_DESCRIPTION.value=oBj.valout[3];"); 
			out.println("}"); 
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CODE_sql_fuel_type\";"); 
			out.println("    m_criteria = document.Form1.TXT_FUEL_TYPE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_FUEL_TYPE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_MODEL_CODE_sql\";"); 
			out.println(" if('"+m_close_status+"'!='A'){");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','5');"); 
			out.println("}"); 
			out.println(" if('"+m_close_status+"'=='A'){");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+\"P@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','5');"); 
			out.println("}"); 
			out.println("}"); 
			
			
			
			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=\"\";"); 
			out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
			out.println("}"); 
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_MODEL_DESC_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_MAKE_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_FUEL_TYPE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_TAX_RATE.value=oBj.valout[6];");
			out.println("  format_number(document.Form1.TXT_TAX_RATE,5); ");
			
			out.println("    document.Form1.TXT_TAX_FOR_LEASE.value=oBj.valout[7];");
			out.println("  format_number(document.Form1.TXT_TAX_FOR_LEASE,5); ");
			
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[8];"); 
			
			//modified by nuwan de silva 19-07-07--------------------------------
			out.println("if(oBj.valout[9]=='null' || oBj.valout[9]=='-' ) {");
			out.println("oBj.valout[9]='';"); 
			out.println("}");
			out.println("    document.Form1.TXT_SUB_CAT_CODE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_MAKE_DESCRIPTION.value=oBj.valout[10];");
			
			// _________ Added by nuwan de silva on 10-12-2007 __________________
			out.println("    document.Form1.TXT_ENGINE_CAPACITY.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_OPTION_TYPE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_COUNTRY_CODE.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_YEAR_OF_MANUFACTURE.value=oBj.valout[14];"); 
			
			
			out.println("}");
			
			
			
			out.println("function val_tax_rate(obj,size){");
			out.println("if(isnumberok(document.Form1.TXT_TAX_RATE,size)){");
			out.println("format_number(document.Form1.TXT_TAX_RATE,size);");
			out.println("}");
			out.println("else");
			out.println("if(document.Form1.TXT_TAX_RATE.value!=\"\"){");
			out.println("alert('Please enter a number');");
			out.println("document.Form1.TXT_TAX_RATE.value='';");
			out.println("}");
			out.println("}");
			
			
			out.println("function val_tax_for_lease(obj,size){");
			out.println("if(isnumberok(document.Form1.TXT_TAX_FOR_LEASE,size)){");
			out.println("format_number(document.Form1.TXT_TAX_FOR_LEASE,size);");
			out.println("}");
			out.println("else");
			out.println("if(document.Form1.TXT_TAX_FOR_LEASE.value!=\"\"){");
			out.println("alert('Please enter a number');");
			out.println("document.Form1.TXT_TAX_FOR_LEASE.value='';");
			out.println("}");
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
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:43em; dialogHeight:25em; center:yes; status:no\");"); 
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
			out.println("    m_sql = \"m_view_TXT_MODEL_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_CAT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_4() {");
			out.println("    document.Form1.TXT_SUB_CAT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function close_screen() {");
			out.println(" if('"+m_close_status+"'!='A'){");
			out.println(" close_window()");
			out.println("}"); 
			out.println(" else{");
			
			out.println("window.close()");
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function load_make(val) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_make_creation?CLSTATUS=B&code=\"+document.Form1.TXT_MAKE_CODE.value+\"\";"); 
			out.println("window.open(m_url,'displayWindow31','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');"); 
			out.println("}"); 
			
			out.println("function load_item_sub(val) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_item_sub_category?CLSTATUS=B&code=\"+document.Form1.TXT_MAKE_CODE.value+\"\";"); 
			out.println("window.open(m_url,'displayWindow33','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');"); 
			out.println("}"); 
			
			// ________________ Added by nuwan de silva on 10-12-2007 --------------------
			out.println("function help_button_country() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_COUNTRY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_COUNTRY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function assign_country() {"); 
			out.println("    document.Form1.TXT_COUNTRY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function val_year(obj){");
			out.println("   if(isNaN(obj) ) { ");
			out.println("   alert('you have typed an incorrect character as a year');");
			out.println("   document.Form1.TXT_YEAR_OF_MANUFACTURE.value='';");
			out.println("   document.Form1.TXT_YEAR_OF_MANUFACTURE.focus();");
			out.println("   }");
			out.println("}");
			
			// _____ end by nuwan de silva on 10-12-2007 ___________________________________
			
			
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			if(!m_close_status.equals("A")){
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			}
			if(m_close_status.equals("A")){
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			}
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_close_status' VALUE=\"N\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='HID_TXT_OPTION_TYPE' VALUE=\"N\">"); // added by udara on 24-02-2012
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Model Creation </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>");  
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr >"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_MODEL_CODE'  class=div_input>Model Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MODEL_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H9'),makeRequest(document.Form1.TXT_MODEL_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_DESCRIPTION'  class=div_input>Model Description *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESCRIPTION' maxlength='100' size='100' onblur=\"assign_help_status('H3'),makeRequest(document.Form1.TXT_DESCRIPTION)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_MAKE_CODE'  class=div_input>Make Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MAKE_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_MAKE_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_MAKE_CODE' value=\"Help\" onClick=\"help_button_1()\">"); 
			out.println("<input class='but_input' style='width:70' type='button' name='MAKE_LINK_BUT' value=\"Create New\" onClick=\"load_make('A')\"></td>"); /*Added by Chandana on 15/11/2007*/
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_MAKE_DESCRIPTION'  class=div_input>Make Description </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MAKE_DESCRIPTION' maxlength='100' size='100' onblur=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_FUEL_TYPE'  class=div_input>Fuel Type *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FUEL_TYPE' maxlength='10' size='10' onblur=\"assign_help_status('H2'),makeRequest(document.Form1.TXT_FUEL_TYPE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FUEL_TYPE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_CAT_CODE'  class=div_input>Item Sub Category code </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_CAT_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H4'),makeRequest(document.Form1.TXT_SUB_CAT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_SUB_CODE' value=\"Help\" onClick=\"help_button_4()\" >"); 
			out.println("<input class='but_input' style='width:70' type='button' name='ITEM_SUB_LINK_BUT' value=\"Create New\" onClick=\"load_item_sub('B')\" disabled></td>"); /*Added by Chandana on 16/11/2007*/
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_TAX_RATE'  class=div_input>Tax Rate *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TAX_RATE' maxlength='5' size='5' STYLE='{text-align:right;}' onblur='val_tax_rate(document.Form1.TXT_TAX_RATE,5)'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_TAX_FOR_LEASE'  class=div_input>Tax For Lease *</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TAX_FOR_LEASE' maxlength='5' size='5' STYLE='{text-align:right;}' onblur='val_tax_for_lease(document.Form1.TXT_TAX_FOR_LEASE,5)'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			// _____________ added by nuwan de silva on 10-12-2007 -------------------------------------------------------
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ENGINE_CAPACITY'  class=div_input>Engine Capacity *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ENGINE_CAPACITY' maxlength='5' size='6' onblur='format_noobject_nodecimal1(this)' value='0'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ENGINE_CAPACITY' value=\"Help\" onClick=\"help_eng_cap()\"></td>"); //Added by Udara Viruwan
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_OPTION_TYPE'  class=div_input>Option Type *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OPTION_TYPE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_OPTION_TYPE' value=\"Help\" onClick=\"help_opt_types()\"></td>"); //Added by Udara Viruwan
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_COUNTRY_CODE'  class=div_input>Country Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_COUNTRY_CODE' maxlength='10' size='10'onblur=\"assign_help_status('H_COUNTRY'),makeRequest(document.Form1.TXT_COUNTRY_CODE)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_COUNTRY_CODE' value=\"Help\" onClick=\"help_button_country()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_YEAR_OF_MANUFACTURE'  class=div_input>Year Of Manufacture *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_YEAR_OF_MANUFACTURE' maxlength='4' size='4' onblur='val_year(this.value)'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			// _____________ end by nuwan de silva on 10-12-2007 -------------------------------------------------------
			
			out.println("<td width='30%' >Default Value </td>");
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'>");
			out.println("<option value='N' selected> No </option>");
			out.println("<option value='Y' >Yes</option>");
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
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
