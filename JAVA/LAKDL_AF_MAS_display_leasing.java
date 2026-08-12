
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - ASSET FINANCING PROCESS STAGES.
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_leasing extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Asset Financing Process Stages</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var enter_text=0");//**
			out.println("var enter_text1=0");
			out.println("var div_arry=new Array();");
			out.println("var dist_arry=new Array();");
			out.println("var bttn_pres=0;");
			out.println("var bttn_pres1;");
			out.println("var alert_msg=0;");
			out.println("var division;");
			out.println("var product;");
			out.println("var div1_arry=0;");
			out.println("var hid=0;");
			
			out.println("function get_vector(data_vec) {");

			out.println("if(data_vec.length==0  && document.Form1.SCREEN_NAME.value==\"NEW\" &  document.Form1.TXT_DIVISION.value!='' &&  document.Form1.hid_val.value=='t1'){");//to check whether division is valid.
			out.println("help_update()");
			out.println("			}");

			out.println("if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" &&  document.Form1.TXT_DIVISION.value!='' && document.Form1.hid_val.value=='t1'){");//to check whether division is valid.
			out.println("help_update()");
			out.println("			}");

			out.println("if(data_vec.length>0 && bttn_pres==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");//to check whether this new record already entered in leasing.
			out.println("				alert('Record already exists');");
			out.println("    document.Form1.TXT_TRANSACTION_TYPE.value='';"); 
			out.println("    document.Form1.TXT_TRANSACTION_TYPE.focus();"); 
			//out.println("div1_arry=data_vec");
			out.println("}");
			
			out.println("if(data_vec.length==0 && bttn_pres==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");//to check whether this new record already entered in leasing.
			//out.println("div1_arry=data_vec.length");

			out.println("before_submit();");
			out.println("}");
								
			out.println("if(data_vec.length==0 && enter_text1==1 && document.Form1.hid_val.value=='t2' && document.Form1.TXT_TRANSACTION_TYPE.value!=''){");//when editing if wrong txn type is entered.
			out.println("help_button_2()");
			out.println("	}");
			out.println("}");
			
			
		
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_division&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_leasing_div&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
		
			
			out.println("function makeRequest3(obj1) {");
			out.println("var division=document.Form1.TXT_DIVISION.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_leasing&data_val=\"+obj1+\"&data_val1=\"+division;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest1(obj1) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_transaction1&data_val=\"+obj1.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_DIVISION.value==\"\"){  "); 
			out.println("DIV_TXT_DIVISION.style.color='red';");

			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_TRANSACTION_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_TRANSACTION_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

				
			out.println("function before_submit(){ ");
			//out.println("alert(div1_arry.length)");
			//out.println("if(div1_arry.length==0){");
			out.println("if(bttn_pres==1 && document.Form1.TXT_DIVISION.value=='' ||document.Form1.TXT_TRANSACTION_TYPE.value=='' ){");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("validate_data()"); 
			out.println("		}"); 
			out.println("		else{"); 
			out.println("		if(validate_data()){"); 
			
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			
			
			//out.println("		if(validate_data()){");

			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_leasing';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			
			//out.println("else{");
			//out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			//out.println("		}"); 	
			out.println("} "); 
			out.println("} "); 


			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
				
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_leasing';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_leasing';"); 
			
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	");
			out.println("bttn_pres=1");
			out.println("makeRequest3(document.Form1.TXT_TRANSACTION_TYPE.value)");
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"RACT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){");
			out.println("before_submit();"); 
			out.println("}"); 
			
		
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_leasing\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Asset Financing Process Stages - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Asset Financing Process Stages - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){");
			
			out.println("new_window();");
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}");
			
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_TRANSACTION_TYPE.disabled=true;"); 
			
			//out.println("document.Form1.BUT_PRODUCT_ID.disabled=true;}");
			
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
			out.println("document.Form1.BUT_PRODUCT_ID.disabled=true;"); 
			out.println("document.Form1.TXT_TRANSACTION_TYPE.disabled=true;"); 
			out.println("document.Form1.BUT_SAVE.disabled=true;"); 
			
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");
			out.println("document.Form1.hid_save.value=\"Deactivate\";");  

			out.println("document.Form1.BUT_PRODUCT_ID.disabled=true;");
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save.value=\"Reactivate\";");  

			out.println("document.Form1.BUT_PRODUCT_ID.disabled=true;");
			
			
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
			//out.println(" alert(m_sql)");
			
			out.println("	if(oBj.valout[1] ==\" \"){");  //added by nuwan de silva 20-07-07
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
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			//out.println("Close();"); 
			
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      out.println("window.close();");
      out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			//out.println("if(city_arry.length==0 && document.Form1.hid_val.value=='t1' || document.Form1.hid_val.value=='t3') {");//**
			out.println("document.Form1.TXT_DIVISION.value='';"); 
			out.println("document.Form1.TXT_DIVISION.focus() ;"); 
			out.println("document.Form1.TXT_TRANSACTION_TYPE.value='';"); 
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"2\"){");
			//out.println("if(document.Form1.TXT_CITY_CODE.value!='' && dist_arry.length==0 && document.Form1.hid_val.value=='t2') {");//**
			out.println("document.Form1.TXT_TRANSACTION_TYPE.value='';");
			out.println("document.Form1.TXT_TRANSACTION_TYPE.focus();"); 
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
			out.println("    m_sql = \"m_help_TXT_DIV_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DIVISION.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_DIVISION.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			
			//out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql1\";"); 

			/*out.println("if(document.Form1.TXT_TRANSACTION_TYPE.value!=''){");

			out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql1\";"); 
			out.println("    m_criteria = document.Form1.TXT_TRANSACTION_TYPE.value+\"@Y@\";"); 

			out.println("    } ");
			out.println("if(document.Form1.TXT_TRANSACTION_TYPE.value==''){");

			//out.println("if(document.Form1.TXT_TRANSACTION_TYPE.value=='' && document.Form1.SCREEN_NAME.value==\"EDIT\"  ){");
			out.println("alert('a')");
			out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql_edit\";");
			out.println("    m_criteria = document.Form1.TXT_TRANSACTION_TYPE.value+\"@\"+document.Form1.TXT_DIVISION.value+\"@Y@\";");

			out.println("    } ");
						
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			//out.println("alert('b')");
			out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql1\";"); 
			out.println("    m_criteria = document.Form1.TXT_TRANSACTION_TYPE.value+\"@Y@\";"); 

			out.println("    } ");



		//	out.println("    m_sql = \"m_help_TXT_TRANSACTION_TYPE_sql\";"); 
		//	out.println("    m_criteria = document.Form1.TXT_TRANSACTION_TYPE.value+\"@Y@\";"); 
			
			
			*/
			//out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			//out.println("    m_sql = \"m_help_TXT_TRANSACTION_TYPE_sql\";");
			//out.println("    m_criteria = document.Form1.TXT_TRANSACTION_TYPE.value+\"@Y@\";"); 

			//out.println("    } ");


			out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql1\";"); 
			out.println("    m_criteria = document.Form1.TXT_TRANSACTION_TYPE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
		
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_TRANSACTION_TYPE.value=oBj.valout[2];"); 
			out.println("enter_text1=0");	
			out.println("}"); 

			out.println("function help_update() {");

			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\" || document.Form1.SCREEN_NAME.value==\"RACT\" ){");
			out.println("    m_sql = \"m_help_TXT_DIV_sql\";");
			out.println("    } ");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    m_sql = \"m_help_TXT_DIVISION1_CODE_sql\";");
			out.println("    } ");


			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" || document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_DIVISION.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_DIVISION.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			//out.println("}");
			
			
			out.println("function help_update_value_assign_99() {");
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			
			out.println("    document.Form1.TXT_DIVISION.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TRANSACTION_TYPE.value=oBj.valout[3];"); 
			out.println("}"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.TXT_DIVISION.value=oBj.valout[2];"); 
			out.println("}"); 
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
			out.println("    m_sql = \"m_help_TXT_DIV_sql\";");
			out.println("    m_criteria = document.Form1.TXT_DIVISION.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			out.println("function check_txn() {");
			
		 	out.println("var keyChar=window.event.keyCode;");
			//	out.println("alert(keyChar)");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("enter_text1=1");	
			out.println("}	");	
			out.println("}"); 
			
			out.println("function check_div() {");
			out.println("var keyChar=window.event.keyCode;");
			//	out.println("alert(keyChar)");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("enter_text=1");	
			out.println("}	");	
			out.println("}"); 
			
			out.println("function check_txn1() {");
		 	out.println("var keyChar=window.event.keyCode;");
			out.println("if (keyChar <='48'&& keyChar >='48' && keyChar <= '57' || keyChar == '13' )");
			out.println("{	");	
			out.println("window.event.keyCode=keyChar;	");	
			out.println("}	");	
			out.println("else	");	
			out.println("{	");	
			out.println("enter_text1=1");	
			out.println("}	");	
			out.println("}"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_click' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Asset Financing Process Stages</td>"); 
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
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"BUT_SAVE\" onClick='save_window()' value=\"Save\"></td>");  
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

			out.println("<td width='30%' ><DIV id='DIV_TXT_DIVISION'  class=div_input>Division Code*</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DIVISION' maxlength='10' size='10' onblur=\"assig('t1'),makeRequest(document.Form1.TXT_DIVISION)\" onkeypress=\"check_div()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TRANSACTION_TYPE' class=div_input>Transaction Type*</div></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TRANSACTION_TYPE' maxlength='10' size='10' onblur=\"assig('t2'),makeRequest1(document.Form1.TXT_TRANSACTION_TYPE)\" onkeypress=\"check_txn()\" onkeyup=\"check_txn1()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_PRODUCT_ID' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
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
