
//--
//SCREEN NAME:SYSTEM ADNINISTRATION - CURRENCY
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_currency extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods  m_sn_methods = new LAKDL_AF_CO_conn_methods (); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			int b_flag_date=0;
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Currencies</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag_date=0;");

			out.println("function get_vector(data_vec) {");
			//out.println("alert(document.Form1.SCREEN_NAME.value)");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value=='t1'){");
			//modified by madhawa 2009-10-15 change the error msg
			out.println("				alert('Currency Already exists');");
			out.println("				new_window();");
			out.println("			}");
			//modified by madhawa add if condition to give error msg when reporting currency exists for a new currency situation
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value=='MC1'&&document.Form1.TXT_CURR_CODE.value!=''&&document.Form1.TXT_CURR_SYMBOL.value!=''){");
			out.println("				alert('Reporting Currency already exists');");
			out.println("				new_window();");
			out.println("			}");
			//end modified by madhawa add if condition to give error msg when reporting currency exists for a new currency situation
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_CURR_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			//out.println("   alert('Selected District code is incorrect,use help...!')");
			out.println("   help_update();");
			out.println("}");
			//modified by madhawa add 2009-10-15 add last two conditions in else if
			out.println("else if(data_vec.length>0 && document.Form1.hid_help_status.value=='M2'&&document.Form1.TXT_CURR_CODE.value!=''&&document.Form1.TXT_CURR_SYMBOL.value!='' ){");
			out.println("if(data_vec[0]==1){");
			out.println("   alert('Please remove the active reporting currency and Then add new reporting currency')");
			out.println("document.Form1.TXT_REP_CURR.value=\"N\"");
			out.println("}");
			out.println("}");
			
			
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_CURR_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("document.Form1.TXT_CURR_CODE.value=data_vec[0];"); 
			out.println("document.Form1.TXT_CURR_SYMBOL.value=data_vec[1];"); 
			out.println("document.Form1.TXT_REP_CURR.value=data_vec[2];"); 
			//out.println("document.Form1.TXT_TRN_DATE.value=data_vec[3];"); 
			//out.println("document.Form1.TXT_TRN_DD.value=data_vec[3].substring(0,2)");
			//out.println("document.Form1.TXT_TRN_MM.value=data_vec[3].substring(3,5)");
			//out.println("document.Form1.TXT_TRN_YY.value=data_vec[3].substring(6,10)");
			
			out.println("getDateValues(data_vec[3]);");

		//	out.println("document.Form1.TXT_CATEGORY_CODE.value=data_vec[4];"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value=data_vec[4];"); 
				
			out.println("}");
			
			
			
		//	out.println("if(data_vec.length==0 && document.Form1.hid_help_status.value=='M2' && document.Form1.TXT_CURR_CODE.value!=''){");
		//	out.println("   alert('Selected Category code is incorrect,use help...!')");
		//	out.println("   help_button_2();");
		//	out.println("}");
			
			

			out.println("}");
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			
			
			
			//out.println("			if(data_vec.length==0 && (document.Form1.SCREEN_NAME.value==\"EDIT\")){");
			//out.println("document.Form1.TXT_CATEGORY_CODE.VALUE='';");
			out.println("}");
			out.println("}");
			
			
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("    if(document.Form1.hid_help_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_currency1&data_val=\"+obj.value;");
			out.println("}");
			out.println("    else");
			out.println("    if((document.Form1.hid_help_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"RACT\")&&(document.Form1.hid_help_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"NEW\")){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_currency&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			
			out.println("else");
    	out.println("    if(document.Form1.hid_help_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\"){");
      out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_currency&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("}");
	      
			out.println("    else");
			out.println("    if(document.Form1.hid_help_status.value=='M2' && obj.value==\"Y\" ){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_currency_rep&data_val=\"+obj.value;");
			out.println("}");
					
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest1(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_currency&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CURR_CODE.value==\"\"){  "); 
		//	out.println("alert('Currency Code Can not Be Blank'); "); 
			out.println("DIV_TXT_CURR_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CURR_SYMBOL.value==\"\"){  "); 
		//	out.println("alert('Currency Symbol Can not Be Blank'); "); 
			out.println("DIV_TXT_CURR_SYMBOL.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//out.println("else if(document.Form1.TXT_REP_CURR.value==\"\"){  "); 
		//	out.println("DIV_TXT_REP_CURR.style.color='red';");
		//	out.println("return false;"); 
		//	out.println("}"); 
			out.println("else if((document.Form1.TXT_TRN_DD.value==\"\")||(document.Form1.TXT_TRN_MM.value==\"\")||(document.Form1.TXT_TRN_YY.value==\"\")){  "); 
	//		out.println("alert('Date Can not Be Blank'); "); 
			out.println("DIV_TXT_TRN_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//out.println("else if(document.Form1.TXT_CATEGORY_CODE.value==\"\"){  "); 
			//out.println("DIV_TXT_CATEGORY_CODE.style.color='red';");
			//out.println("return false;"); 
		//	out.println("}"); 

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
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_currency';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}");
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_currency';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function close_window(){	"); 
			
			out.println("		if(confirm(\"Are you sure you want to close the screen? \")){ "); 
			out.println("		parent.frames[0].close_window();");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_FollowupAlert?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_currency';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_currency\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Currencies - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Currencies - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_CURR_SYMBOL.disabled=true;"); 
			out.println("document.Form1.TXT_REP_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_TRN_DD.disabled=true;"); 
			out.println("document.Form1.TXT_TRN_MM.disabled=true;");
			out.println("document.Form1.TXT_TRN_YY.disabled=true;");
			//out.println("document.Form1.TXT_CATEGORY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 
		//	out.println("document.Form1.BUT_CATEGORY_CODE.disabled=true;");

			out.println("}"); 
			out.println("else{");
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}");
			//--must set this when a table is created other wise will give errors when activating or deactivating
			
			
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.hid_status.value=\"Edit\";"); 
			//out.println("document.Form1.TXT_CURR_CODE.disabled=true;"); 
			
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
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
			
			
			out.println("function clear_data() {");
			
		//	out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_CURR_CODE.value='';");
			out.println("document.Form1.TXT_CURR_CODE.focus();");
	//		out.println("}");
			out.println("}");

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
			out.println("    m_sql = \"m_help_TXT_CURR_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CURR_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

		//	out.println("function help_button_2() {"); 
		//	out.println("    document.Form1.hid_help_type.value=\"2\";"); 
		//	out.println("    m_sql = \"m_help_TXT_CATEGORY_CODE_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_CATEGORY_CODE.value+\"@Y@\";"); 
		//	out.println("    HelpBox('1','10','0');"); 
		//	out.println("}"); 
		//	out.println(""); 

			//out.println("function help_value_assign_2() {"); 
		//	out.println("    document.Form1.TXT_CATEGORY_CODE.value=oBj.valout[2];"); 
		//	out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CURR_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CURR_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CURR_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','3');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CURR_SYMBOL.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_REP_CURR.value=oBj.valout[4];"); 
			out.println("    getDateValues(oBj.valout[5]);"); 
			//out.println("    document.Form1.TXT_TRN_DATE.value=oBj.valout[5];"); 
		//	out.println("    document.Form1.TXT_CATEGORY_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[6];"); 

			out.println("}"); 
			
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_TRN_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_TRN_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_TRN_YY.value=dval.substring(6,10)");
			out.println("}");
			
			out.println(" function assign_help_status(obj){");
      out.println(" document.Form1.hid_help_status.value =obj; ");
      out.println("}");
			
			
			out.println("function val_trn_date(){ ");
			out.println("if((document.Form1.TXT_TRN_DD.value !=\"\")&&(document.Form1.TXT_TRN_MM.value !=\"\")&&(document.Form1.TXT_TRN_YY.value !=\"\")){");
			out.println("checkMonthLength(document.Form1.TXT_TRN_DD,document.Form1.TXT_TRN_MM,document.Form1.TXT_TRN_YY);");
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
			out.println("    m_sql = \"m_help_TXT_CURR_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_CURR_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
			
			
				out.println("function load_c_date(val) {");
			
		//	out.println("alert('date valaue'+val);");
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
     // out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			out.println("  if(document.Form1.hid_cal_date.value=='1' && b_flag_date!=1 ){"); 
			out.println("     document.Form1.TXT_TRN_DD.value=v_date;");
			out.println("     document.Form1.TXT_TRN_MM.value=v_month;");
			out.println("     document.Form1.TXT_TRN_YY.value=val;");
			out.println("  }");				
						
			//out.println("validate_date(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY);");

			
			out.println("  }");				
			
			out.println("}");
			
			
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");

			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Currencies</td>"); 
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
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CURR_CODE'  class=div_input>Currency Code *</DIV></td>"); 
			//modified by madhawa 2009-10-13 change maxlength to 4
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CURR_CODE' maxlength='4' size='10' onblur=\"assign_help_status('M1'),assig('t1'),makeRequest(document.Form1.TXT_CURR_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CURR_SYMBOL'  class=div_input>Currency Symbol *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CURR_SYMBOL' maxlength='4' size='4'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
//			out.println("<tr >"); 
			//out.println("<td width='30%' ><DIV id='DIV_TXT_REP_CURR'  class=div_input>Reporting Currency *</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REP_CURR' maxlength='1' size='1'></td>"); 
		//	out.println("<td width='*%'></td>"); 
		//	out.println("</tr>"); 
		//	out.println("<tr >"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Reporting Currency *</td>"); 
			//modified by madhawa 2009-10-15 call assig('MC1') on onblur event
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_REP_CURR' maxlength='1' size='1' onChange=\"assign_help_status('M2'),assig('MC1'),makeRequest(document.Form1.TXT_REP_CURR)\">");  
			out.println("<option value='N' selected>No</option>");			
			out.println("<option value='Y' >Yes</option>");		
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			

		//	out.println("<td width='30%' ><DIV id='DIV_TXT_TRN_DATE'  class=div_input>Transaction Date('DD-MM-YYYY')*</DIV></td>"); 
		//	out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TRN_DATE' maxlength='10' size='10'></td>"); 
		//	out.println("<td width='*%'></td>"); 
		//	out.println("</tr>"); 
		//	out.println("<tr>"); 
			
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_TRN_DATE'  class=div_input>Transaction Date('DD-MM-YYYY')*</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='hidden' name='TXT_TRN_DATE' maxlength='10' size='10'></td>");
			out.println("<td width='40%' ><input class='txt_input5'  type='text' style=\"width:24px;\"  name='TXT_TRN_DD' maxlength='2' size='1' onblur=\"val_trn_date()\">");
			out.println("<input class='txt_input5' type='text' style=\"width:24px;\"   name='TXT_TRN_MM' maxlength='2' size='1' onblur=\"val_trn_date()\">");
			out.println("<input class='txt_input5' type='text' style=\"width:40px;\"  name='TXT_TRN_YY' maxlength='4' size='2' onblur=\"val_trn_date()\"><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			

		//	out.println("<td width='30%' ><DIV id='DIV_TXT_CATEGORY_CODE'  class=div_input>Category Code *</DIV></td>"); 
		//	out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CATEGORY_CODE' maxlength='10' size='10' onblur=\"assign_help_status('M2'),makeRequest(document.Form1.TXT_CATEGORY_CODE)\">"); 
		//	out.println("<input class='but_input' type='button' name='BUT_CATEGORY_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
		//	out.println("<td width='*%'></td>"); 
		//	out.println("</tr>"); 
		//	out.println("<tr >"); 
		
      out.println("<tr>"); 
			out.println("<td width='30%' >Default Value *</td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'>");  
			out.println("<option value='N' selected>No</option>");			
			out.println("<option value='Y' >Yes</option>");		
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='*%'></td>"); 
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
