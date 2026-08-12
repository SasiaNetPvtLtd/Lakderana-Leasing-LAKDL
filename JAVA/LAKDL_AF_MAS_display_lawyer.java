
//ID         :1.75 LAWYER PROCESS
//SCREEN NAME:SYSTEM ADMINISTRATION - LAWYER 
//CREATED BY: NUWAN DE SILVA
//DATE/TIME:25-07-06
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_lawyer extends javax.servlet.http.HttpServlet { 

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
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Creation of Lawyers </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already Exists.');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value == 'H_val'){");
			out.println("				alert('Record already Exists.');");
			out.println("    	 	help_update_name();"); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_LAWYER_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				help_update();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_CITY_CODE.value!=\"\"){");
			out.println("				help_button_1();");
			//out.println("       document.Form1.TXT_CITY_CODE.value=\"\" "); 
			//out.println("       document.Form1.TXT_CITY_CODE.focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_LAWYER_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("    document.Form1.TXT_LAWYER_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_NAME_WITH_INITIALS.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=data_vec[7];"); 
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=data_vec[8];"); 
			out.println("    document.Form1.TXT_MOBIL_NO.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[10];"); 
			out.println("    document.Form1.TXT_OFFICE_FAX_NO.value=data_vec[11];"); 
			out.println("    document.Form1.TXT_FEE_PER_CASE.value=data_vec[12];"); 
			out.println("    document.Form1.TXT_MONTHLY_FEE.value=data_vec[13];"); 
      out.println("			}");
			
			out.println("}");
			
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_lawyer2&data_val=\"+obj.value;");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_lawyer&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"RACT\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_lawyer&data_val=\"+obj.value+\"&ac_status=N\";");
			
			//out.println("if(document.Form1.hid_chk_status.value=='M1')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_lawyer&data_val=\"+obj.value;");
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M2')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest1(obj,obj2) {");
			out.println("if(document.Form1.hid_chk_status.value =='H_val' ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_lawyer1&data_val=\"+obj.value+\"&data_val2=\"+obj2.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			//out.println("else");
			out.println("}");
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_LAWYER_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LAWYER_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FIRST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FIRST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LAST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_LAST_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NAME_WITH_INITIALS.value==\"\"){  "); 
			out.println("DIV_TXT_NAME_WITH_INITIALS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS1.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS1.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CITY_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CITY_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TEL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_TEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_OFFICE_TEL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_OFFICE_TEL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MOBIL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_MOBIL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			//out.println("else if(document.Form1.TXT_FAX_NO.value==\"\"){  "); 
			//out.println("DIV_TXT_FAX_NO.style.color='red';");
			//out.println("return false;"); 
			//out.println("}"); 
			//out.println("else if(document.Form1.TXT_OFFICE_FAX_NO.value==\"\"){  "); 
			//out.println("DIV_TXT_OFFICE_FAX_NO.style.color='red';");
			//out.println("return false;"); 
			//out.println("}"); 
			out.println("else if(document.Form1.TXT_FEE_PER_CASE.value==\"\"){  "); 
			out.println("DIV_TXT_FEE_PER_CASE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		//	out.println("else if(!isNumber(document.Form1.TXT_FEE_PER_CASE.value)){  "); 
		//	out.println("return false;"); 
		//	out.println("}");
			out.println("else if(document.Form1.TXT_MONTHLY_FEE.value==\"\"){  "); 
			out.println("DIV_TXT_MONTHLY_FEE.style.color='red';");
			out.println("return false;"); 
			out.println("}");
		//	out.println("else if(!isNumber(document.Form1.TXT_MONTHLY_FEE.value)){  "); 
		//	out.println("return false;"); 
		//	out.println("}");
			
			
			

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ ");
			out.println("   m_status = document.Form1.hid_status.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"Edit\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
			out.println("   }"); 
			out.println("   else if(m_status == \"Deactivate\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Deactivate ? '");
			out.println("   }"); 
			out.println("   else if(m_status == \"Reactivate\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Reactivate ? '");
			out.println("   }"); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(m_save_msg)){ "); 
			out.println("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("   document.Form1.elements[i].disabled=false;");
			out.println("   }");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_lawyer ';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_lawyer ';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_lawyer ';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_lawyer\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Creation of Lawyers  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Creation Of Lawyers  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function clear_fields_new(){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\" ) {" ); 
			out.println("    document.Form1.TXT_LAWYER_CODE.value =''; ");
			out.println("    document.Form1.TXT_LAWYER_CODE.focus(); ");
			out.println("   }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"1\") {" ); 
			out.println("    document.Form1.TXT_CITY_CODE.value =''; ");
			out.println("    document.Form1.TXT_CITY_CODE.focus(); ");
			out.println("  }		"); 
			out.println("}		");
			
			out.println("function check_fields(type){"); 
			out.println("		if(document.Form1.TXT_FEE_PER_CASE.value =='0.00' && document.Form1.TXT_MONTHLY_FEE.value =='0.00' ) {" ); 
			out.println("    alert('Please enter either Monthly Fee or Fee Per Case '); "); 
			out.println("		if(type == '1'){ ");
			out.println("    document.Form1.TXT_FEE_PER_CASE.value =''; ");
			out.println("    document.Form1.TXT_FEE_PER_CASE.focus(); ");
			out.println("   }		"); 
			out.println("		else if(type == '2'){ ");
			out.println("    document.Form1.TXT_MONTHLY_FEE.value =''; ");
			out.println("    document.Form1.TXT_MONTHLY_FEE.focus(); ");
			out.println("   }		"); 
			out.println("   }		"); 
			out.println("		else if(type == '1' && document.Form1.TXT_FEE_PER_CASE.value !='' ){ ");
			out.println("   	if(isnumberok(document.Form1.TXT_FEE_PER_CASE,20)){");
			out.println("       format_number(document.Form1.TXT_FEE_PER_CASE,20);");
			out.println("		    if(document.Form1.TXT_FEE_PER_CASE.value!='0.00')");
			out.println("       document.Form1.TXT_MONTHLY_FEE.value ='0.00'; ");
			out.println("			}		");
			out.println("	    else {");
			out.println("     alert('Please enter a number');");
			out.println("      document.Form1.TXT_FEE_PER_CASE.value =''; ");
			out.println("      document.Form1.TXT_FEE_PER_CASE.focus(); ");
			out.println("			}		");
			out.println("		}		");
			out.println("		else if(type == '2' && document.Form1.TXT_MONTHLY_FEE.value !='' ){ ");
			out.println("   	if(isnumberok(document.Form1.TXT_MONTHLY_FEE,20)){");
			out.println("   	format_number(document.Form1.TXT_MONTHLY_FEE,20);");
			out.println("		  if(document.Form1.TXT_MONTHLY_FEE.value!='0.00') ");
			out.println("      document.Form1.TXT_FEE_PER_CASE.value ='0.00'; ");
			out.println("		  }		");
			out.println("	    else { ");
			out.println("     alert('Please enter a number');");
			out.println("      document.Form1.TXT_MONTHLY_FEE.value =''; ");
			out.println("      document.Form1.TXT_MONTHLY_FEE.focus(); ");
			out.println("			}		");
			out.println("		}		");
			out.println("}		");
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_LAST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_NAME_WITH_INITIALS.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_OFFICE_TEL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBIL_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_OFFICE_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FEE_PER_CASE.disabled=true;");
			out.println("document.Form1.TXT_MONTHLY_FEE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("   document.Form1.elements[i].disabled=false;");
			out.println("   }");
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
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
			out.println("		clear_fields_new();"); 
			out.println("		} else"); 
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
			out.println("	else{	"); 
			out.println("		clear_fields_new();"); 
			out.println("	}	"); 
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
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_LAST_NAME.value='';"); 
			out.println("    document.Form1.TXT_LAST_NAME.focus();"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_LAWYER_CODE_sql_New\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_LAWYER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_LAWYER_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','12');"); 
			out.println("}"); 
			
			
			out.println("function help_update_name() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_LAWYER_NAME_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FIRST_NAME.value+\"@\"+document.Form1.TXT_LAST_NAME.value+\"@\";"); 
			out.println("    HelpBox('1','10','12');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_LAWYER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_NAME_WITH_INITIALS.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_OFFICE_TEL_NO.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_MOBIL_NO.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_OFFICE_FAX_NO.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_FEE_PER_CASE.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_MONTHLY_FEE.value=oBj.valout[15];"); 
			out.println("    document.Form1.Hid_payee_code.value=oBj.valout[16];"); //Added by Sandun on 31-07-2008
     
			out.println("}"); 
			
			out.println("function format_numbers(m_val,size){"); 
			out.println(" if( isnumberok(m_val,size)!= true)  "); 
			out.println("  format_number(m_val,size);"); 
			out.println("}"); 
			out.println(""); 
			
	
	    out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:50em; dialogHeight:25em; center:yes; status:no\");"); 
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
			out.println("    m_sql = \"m_view_TXT_LAWYER_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_LAWYER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
	
	
	
	
	
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_payee_code' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Creation Of Lawyers </td>"); 
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
			out.println("<td width='20%' ><DIV id='DIV_TXT_LAWYER_CODE'  class=div_input>Lawyer Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LAWYER_CODE' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_LAWYER_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_FIRST_NAME'  class=div_input>First Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' maxlength='200'  style=\"width:250px;\"  size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_LAST_NAME'  class=div_input>Last Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LAST_NAME' style=\"width:250px;\"  onblur=\"assignState('H_val'),makeRequest1(document.Form1.TXT_FIRST_NAME,this)\"   maxlength='50' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_NAME_WITH_INITIALS'  class=div_input>Name With Initials *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NAME_WITH_INITIALS'   maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_ADDRESS1'  class=div_input>Address 1 *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS1' maxlength='100' style=\"width:250px;\"  size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' >Address 2 </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS2' maxlength='100' style=\"width:250px;\"  size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_CITY_CODE'  class=div_input>City Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_CITY_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_TEL_NO'  class=div_input>Tel Number *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEL_NO' maxlength='60' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_OFFICE_TEL_NO'  class=div_input>Office Tel Number *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OFFICE_TEL_NO' maxlength='60' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_MOBIL_NO'  class=div_input>Mobile Number *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MOBIL_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_FAX_NO'  class=div_input>Fax Number </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FAX_NO' maxlength='30' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_OFFICE_FAX_NO'  class=div_input>Office Fax Number </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OFFICE_FAX_NO' maxlength='60' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_FEE_PER_CASE'  class=div_input>Fee Per Case *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FEE_PER_CASE' maxlength='18' size='20' value=\"0.00\" onBlur=\"check_fields(1)\" STYLE='{text-align:right;}'  ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_MONTHLY_FEE'  class=div_input>Monthly Fee *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MONTHLY_FEE' maxlength='18' size='20' value=\"0.00\" onBlur=\"check_fields(2)\" STYLE='{text-align:right;}'  ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
						
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALIDITY'  class=div_input>Vat Reg No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG_NO' maxlength='20' size='3' onblur=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALIDITY'  class=div_input>WHT</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_WHT' maxlength='5' size='3' onblur=\"check_number_precent(this,3)\"></td>"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
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
