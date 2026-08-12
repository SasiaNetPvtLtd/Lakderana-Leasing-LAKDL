
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - ITEM SUB CATEGORY
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_item_sub_category extends javax.servlet.http.HttpServlet { 

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
			
			String m_code="";
			String m_close_status="x";
			m_code= req.getParameter("code");
			m_close_status= req.getParameter("CLSTATUS"); /*Added by Chandana on 16/11/2007*/
			if(m_close_status==null){
			m_close_status="x";
			}
						
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Item Sub Categories</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var enter_text=0");//**
			out.println("var enter_text1=0");
			out.println("var item_sub_arry=new Array();");
			out.println("var item_arry=new Array();");
			out.println("var dist_arry=new Array();");
			out.println("var bttn_pres;");
			out.println("var bttn_pres1;");
			out.println("var alert_msg;");

			out.println("function get_vector(data_vec) {");
			//out.println("alert('asd'+document.Form1.SCREEN_NAME.value+document.Form1.hid_val.value);");
						
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_ITEM_SUB_CAT.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("help_update();");
			//out.println("item_sub_arry=data_vec");
			out.println("}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_ITEM_SUB_CAT.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value=data_vec[0];"); 
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value=data_vec[1];"); 
			out.println("document.Form1.TXT_DESCRIPTION.value=data_vec[2];"); 
				out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value=data_vec[3];");  
			out.println("document.Form1.TXT_VAT_AMOUNT.value=data_vec[4];"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value=data_vec[5];");
			out.println("document.Form1.TXT_CAP_ALLOWANCE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_DD.value=data_vec[7].substring(0,2);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_MM.value=data_vec[7].substring(3,5);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_YY.value=data_vec[7].substring(6,10);");
			out.println("}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value=='t3'){");
		//	out.println("				alert('Record already exists');");
			out.println("help_update_desc()");
			//out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			//out.println("document.Form1.TXT_DESCRIPTION.focus();"); 
			//out.println("	item_arry=data_vec");
			out.println("	}");
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_val.value=='t2' && document.Form1.TXT_ITEM_CAT_CODE.value!=''){");
			//out.println("alert('Entered Item Code is Wrong.....!');");
			out.println("help_button_2()");
		//	out.println("	item_arry=data_vec");
			out.println("	}");
			//out.println("alert(data_vec.length+document.Form1.SCREEN_NAME.value+document.Form1.hid_val.value+document.Form1.TXT_DESCRIPTION.value)");
		
			out.println("}");
			
			
			out.println("function assig(val) {");
			
			//out.println("if(document.Form1.TXT_ITEM_SUB_CAT.value!='' || document.Form1.TXT_DESCRIPTION.value!='' ){");
			out.println("document.Form1.hid_val.value=val;");
			//out.println("}");
			//out.println("else{");
			//out.println("document.Form1.hid_val.value=pre");
			//out.println("}");
			
			//out.println("alert(document.Form1.hid_val.value)");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");
			//out.println("var pre=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			//out.println("if(document.Form1.hid_val.value=='t1'){");
			out.println("if(document.Form1.SCREEN_NAME.value==\"RACT\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value!=\"RACT\" && document.Form1.SCREEN_NAME.value!=\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
		//	out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category_r&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			//out.println("}");
			//out.println("if(document.Form1.hid_val.value=='t3'){");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category_desc&data_val=\"+obj.value;");

			//out.println("}");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
				
			out.println("}");

			out.println("function makeRequest1(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_category&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest2(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_sub_category_desc1&data_val=\"+obj.value;");
			out.println(" ");
			
			out.println("load_interface(m_url,'XML');");
			
				out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_ITEM_SUB_CAT.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_SUB_CAT.style.color='red';");
			//out.println("alert('Enter Item Sub Category Code..!')");
			out.println("document.Form1.TXT_ITEM_SUB_CAT.focus()");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DESCRIPTION.value==\"\"){  "); 
			out.println("DIV_TXT_DESCRIPTION.style.color='red';");
			//out.println("alert('Enter Item Sub Category Description..!')");
			out.println("document.Form1.TXT_DESCRIPTION.focus()");

			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ITEM_CAT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_CAT_CODE.style.color='red';");
			//out.println("alert('Enter Item Category Code..!')");
			out.println("document.Form1.TXT_ITEM_CAT_CODE.focus()");

			out.println("return false;"); 
			out.println("}"); 
			
		
			out.println("else if(document.Form1.TXT_CAP_ALLOWANCE_DD.value==\"\"){  "); 
			out.println("DIV_TXT_CAP_ALLOWANCE_DATE.style.color='red';");
			out.println("document.Form1.TXT_CAP_ALLOWANCE_DD.focus()");
			out.println("return false;"); 
			out.println("}"); 
			

			
			out.println("else if(document.Form1.TXT_CAP_ALLOWANCE_MM.value==\"\"){  "); 
			out.println("DIV_TXT_CAP_ALLOWANCE_DATE.style.color='red';");
			out.println("document.Form1.TXT_CAP_ALLOWANCE_MM.focus()");
			out.println("return false;"); 
			out.println("}"); 
			
	
			out.println("else if(document.Form1.TXT_CAP_ALLOWANCE_YY.value==\"\"){  "); 
			out.println("DIV_TXT_CAP_ALLOWANCE_DATE.style.color='red';");
			out.println("document.Form1.TXT_CAP_ALLOWANCE_YY.focus()");
			out.println("return false;"); 
			out.println("}"); 
			

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ ");
			out.println("		if( checkMonthLength(document.Form1.TXT_CAP_ALLOWANCE_DD,document.Form1.TXT_CAP_ALLOWANCE_MM,document.Form1.TXT_CAP_ALLOWANCE_YY)){"); // added by Chatura Jayawardena
			out.println("   if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		if(validate_data()){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");

			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_MAS_save_item_sub_category';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
					out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 

			out.println("}} "); 

			out.println("function load_lock(){	"); 
			out.println(" if('"+m_close_status+"'=='B'){");
			out.println("document.Form1.hid_close_status.value='Y'");
			out.println("}"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			
			out.println("if(document.Form1.hid_close_status.value=='Y'){");			
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_item_sub_category?CLSTATUS=B';"); 
			out.println("}else {"); 			
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_item_sub_category';"); 
			out.println("}"); 
			
			//out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_item_sub_category';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("if(document.Form1.hid_close_status.value=='Y'){");			
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_item_sub_category?CLSTATUS=B';"); 
			out.println("}else {"); 			
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_item_sub_category';"); 
			out.println("}"); 
			out.println("}"); 
			//out.println("window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_item_sub_category';"); 
		
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			//out.println("	alert(validate_data())");
			//out.println("	if(validate_data()==false){");
			//out.println("alert('Please enter all required fields marked with a '*' on the screen')"); 
			//out.println("		}"); 
			//out.println("	if(validate_data()==true){");
		
			//out.println("	alert(validate_data())");
			out.println("before_submit();"); 
			//out.println("}"); 
			out.println("}"); 
			
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_item_sub_category\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Item Sub Categories - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Item Sub Categories - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;");
			out.println("document.Form1.BUT_ITEM_CAT_CODE.disabled=true;");
			out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.disabled=true;");
			out.println("document.Form1.TXT_VAT_AMOUNT.disabled=true;");
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}");
			
			out.println("document.Form1.TXT_ITEM_CAT_CODE.disabled=true;");
			
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			//out.println("document.Form1.TXT_ITEM_SUB_CAT.disabled=true;");
			out.println("document.Form1.TXT_ITEM_CAT_CODE.disabled=false;");
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
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			//out.println("		help_update_value_assign_99();"); 
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
			out.println("	clear_data1();");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			//out.println("Close();"); 
			
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println(" function Close(){");//**
			//out.println("alert(document.Form1.hid_val.value)");
			out.println("clear_data()	");
      out.println("window.close();");
      out.println(" }");
		
			out.println("function clear_data1() {");//**
			//out.println("alert('1234');");
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("document.Form1.TXT_ITEM_SUB_CAT.focus();"); 
			//out.println("document.Form1.TXT_ITEM_CAT_CODE.value='';"); 
			//out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			//out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';");
			//out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value='';"); 
			//out.println("document.Form1.TXT_VAT_AMOUNT.value='';"); 
			out.println("}");
			
			out.println("function clear_data() {");//**
			//out.println("alert(enter_text)");
			//out.println("alert('bttn_pres'+bttn_pres)");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 

			///out.println("if(enter_text1!=1 && item_sub_arry.length==0 && document.Form1.hid_val.value=='t1' || document.Form1.hid_val.value=='t3') {");//**
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("document.Form1.TXT_ITEM_SUB_CAT.focus();"); 
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value='';"); 
			out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value='N';");
			out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value='';"); 
			out.println("document.Form1.TXT_VAT_AMOUNT.value='';"); 
			out.println("}");
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 

			//out.println("if(enter_text1==1 && enter_text==0 && document.Form1.TXT_ITEM_CAT_CODE.value!='' && item_arry.length==0) {");//**
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value='';");
			out.println("document.Form1.TXT_ITEM_CAT_CODE.focus();"); 
			out.println("}");
			
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
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[2];"); 
			out.println("}"); 
			
			

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_CODE_sql\";"); 
			//out.println("var item=document.Form1.TXT_ITEM_CAT_CODE.value.substring(0,1)");
			
			//out.println("if(enter_text1!=1){");
			out.println("    m_criteria = document.Form1.TXT_ITEM_CAT_CODE.value+\"@Y@\";"); 
			//out.println("}");
			//out.println("if(enter_text1==1){");
			//out.println("    m_criteria = item+\"@Y@\";"); 
			//out.println("}");
			out.println("    HelpBox('1','10','1');"); 
			out.println("}");
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			

			out.println("function help_update() {"); 
			out.println("bttn_pres=1");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql\";");
			//out.println("var sub=document.Form1.TXT_ITEM_SUB_CAT.value.substring(0,1) ");
			
		//	out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			///out.println("    help_Item_Sub();");
			
		//	out.println("    } ");
			
			//out.println("    if(enter_text!=1){ ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"N@\";}");
			/*out.println("    } ");
			out.println("    if(enter_text==1){ ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = sub+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = sub+\"@\"+\"N@\";}");
			out.println("    } ");
			*/

			
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_DESC_sql\";");
			out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=oBj.valout[3];"); 
			out.println("document.Form1.TXT_VAT_APPLICABLE_AMOUNT.value=oBj.valout[5];"); 
			out.println("format_number(document.Form1.TXT_VAT_APPLICABLE_AMOUNT,6);");
			out.println("document.Form1.TXT_VAT_AMOUNT.value=oBj.valout[6];"); 
			out.println("format_number(document.Form1.TXT_VAT_AMOUNT,6);");
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE.value=oBj.valout[8];"); 
			out.println("format_number(document.Form1.TXT_CAP_ALLOWANCE,6);");
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_DD.value=oBj.valout[9].substring(0,2);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_MM.value=oBj.valout[9].substring(3,5);"); 
			out.println("    document.Form1.TXT_CAP_ALLOWANCE_YY.value=oBj.valout[9].substring(6,10);");
			out.println("bttn_pres=0");
			out.println("enter_text=0");
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
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql_1\";");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function check_item_sub() {");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("enter_text=1");	
			//out.println("window.event.keyCode='';	");	
			out.println("}	");	
			out.println("}"); 
			
			out.println("function check_item() {");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("enter_text1=1");	
			//out.println("window.event.keyCode='';	");	
			out.println("}	");	
			out.println("}"); 
			
			// Modified by Thamali Jayatunga on 2009.10.15, Modified function check_number
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("if(obj.value >=100){");
			out.println("alert('Please enter a number less than 100');");
			out.println("}"); 
			out.println("else {");
			out.println("alert('please enter a number');"); 
			out.println("}");
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function load_calendar() {");
			out.println("		popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\",\"oBj\",\"left=450,top=200,width=320,height=230\");");
			out.println("}");
			
			out.println("function load_c_date(val) {");
     	out.println("	 v_dd = val.substr(0,val.indexOf('-'));");
   		out.println("  if(v_dd.length <2)"); 
   		out.println("  	v_dd = 0+v_dd;");
  		out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("	 v_mm = val.substr(0,val.indexOf('-'));");
  		out.println("  if(v_mm.length <2)");
   	  out.println("		v_mm = 0+v_mm;");
  		out.println("   v_yy = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("		document.Form1.TXT_CAP_ALLOWANCE_DD.value=v_dd;");
   		out.println("   document.Form1.TXT_CAP_ALLOWANCE_MM.value=v_mm;");
   		out.println("   document.Form1.TXT_CAP_ALLOWANCE_YY.value=v_yy;");
 			out.println("}");

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_close_status' VALUE=\"N\">");

			//out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Item Sub Categories</td>"); 
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

			out.println("<td width='30%' ><DIV id='DIV_TXT_ITEM_SUB_CAT'  class=div_input>Item Sub Category Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ITEM_SUB_CAT' maxlength='10' size='10' onblur=\"assig('t1'),makeRequest(document.Form1.TXT_ITEM_SUB_CAT)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='40%' ><DIV id='DIV_TXT_DESCRIPTION'  class=div_input>Item Sub Category Description*</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESCRIPTION' maxlength='50' size='20' onblur=\"assig('t3'),makeRequest2(document.Form1.TXT_DESCRIPTION)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ITEM_CAT_CODE'  class=div_input>Item Category Code*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ITEM_CAT_CODE' maxlength='10' size='10' onblur=\"assig('t2'),makeRequest1(document.Form1.TXT_ITEM_CAT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_ITEM_CAT_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			// Modified by Thamali Jayatunga on 2009.10.15, Changed 2nd argument in check_number function in onblur event. 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_APPLICABLE_AMOUNT'  class=div_input>VAT Applicable Amount </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_APPLICABLE_AMOUNT'   maxlength='6' size='6' onBlur=\"check_number(document.Form1.TXT_VAT_APPLICABLE_AMOUNT,2)\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			// Modified by Thamali Jayatunga on 2009.10.15, Changed 2nd argument in check_number function in onblur event. 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_AMOUNT'  class=div_input>VAT Amount </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_AMOUNT'  maxlength='6' size='6'  onBlur=\"check_number(document.Form1.TXT_VAT_AMOUNT,2)\"  STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			// Modified by Thamali Jayatunga on 2009.10.15, Changed 2nd argument in check_number function in onblur event. 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CAP_ALLOWANCE'  class=div_input>Capital Allowance</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CAP_ALLOWANCE'  maxlength='6' size='6'  onBlur=\"check_number(document.Form1.TXT_CAP_ALLOWANCE,2)\"  STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >");
			out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_CAP_ALLOWANCE_DATE\" class=div_input>Effective Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_CAP_ALLOWANCE_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CAP_ALLOWANCE_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CAP_ALLOWANCE_YY\" maxlength=\"4\" size=\"4\" ><a href style=\"{cursor:hand;}\" onclick=\"load_calendar()\">   <u>Calendar</u></a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_1.js'></SCRIPT>"); 
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
