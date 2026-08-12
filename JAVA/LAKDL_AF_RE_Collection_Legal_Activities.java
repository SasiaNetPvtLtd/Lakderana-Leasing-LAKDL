
//--
//SCREEN NAME:COLLECTION - LEGAL ACTIVITIES
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Collection_Legal_Activities extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_finance_no="";
			String m_close_status="N";
			String my_screen_name="N";
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			if(req.getParameter("finance_no")!=null){
				m_finance_no = req.getParameter("finance_no");
				m_close_status  = req.getParameter("close_status");
				my_screen_name  = req.getParameter("my_screen_name");
				
			}
			
			
			
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Legal Actions-Assignments</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			
			
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_LEGAL_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     help_update();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_LEGAL_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_LEGAL_POSITION.value=data_vec[3];"); 
			out.println("due_amount();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("     help_button_finance_no();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_LAWYER_CODE.value!=\"\"){");
			out.println("    document.Form1.TXT_LAWYER_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_LAWYER_NAME.value=data_vec[1];"); 
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_LAWYER_CODE.value!=\"\"){");
			out.println("     help_button_lawyer();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M4'){");
			out.println("    document.Form1.TXT_AMOUNT.value=data_vec[0];"); 
			out.println("			}");
			
			
			
			
			out.println("}");
			
			
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_COURT_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_COURT_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_COURT_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			
			
			out.println("function assign_values(data_vec){");
			out.println("    document.Form1.TXT_LEGAL_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[2];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_LAWYER_CODE.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_LAWYER_NAME.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_LEGAL_TYPE.value=data_vec[6];");
			out.println("    document.Form1.TXT_LEGAL_POSITION.value=data_vec[7];"); 
			out.println("    document.Form1.TXT_REMARKS.value=data_vec[8];"); 
			out.println("getDateValues(data_vec[9]);");
			out.println("    document.Form1.TXT_AMOUNT.value=data_vec[10];"); 
			out.println("format_number(document.Form1.TXT_AMOUNT,25);");
			
			out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_legal_no&data_val=\"+obj.value;");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && (document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"EDIT\"))");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_legal_no&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Legal_activities_Finance_no&data_val=\"+obj.value+\"&ac_status=REPOSSESS\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Legal_activities_Lawyer_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			
			
			//	out.println("window.open(m_url);");
			
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			//	out.println("if(document.Form1.TXT_TEMP_REC_NO.value==\"\"){  "); 
			//	out.println("DIV_TXT_TEMP_REC_NO.style.color='red';");
			//	out.println("return false;"); 
			//	out.println("}"); 
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_LEGAL_NO.value==\"\"){  "); 
			out.println("DIV_TXT_LEGAL_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LAWYER_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LAWYER_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LEGAL_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_LEGAL_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			
			out.println("else if(document.Form1.TXT_COURT_DATE_DD.value==\"\" || document.Form1.TXT_COURT_DATE_MM.value==\"\" || document.Form1.TXT_COURT_DATE_YY.value==\"\" ){  "); 
			out.println("DIV_TXT_COURT_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_AMOUNT.style.color='red';");
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
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Legal_Activities';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Legal_Activities';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Legal_Activities';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Collection_Legal_Activities\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Legal Actions-Assignments - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Legal Actions-Assignments - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();"); 
			out.println("document.Form1.TXT_LEGAL_NO.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			
			
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to a Delete record?\")){  ");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_LEGAL_NO.disabled=false;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_COURT_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_COURT_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_COURT_DATE_YY.disabled=true;"); 
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_LAWYER_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_LAWYER_NAME.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_LAWYER_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=true;"); 
			out.println("document.Form1.TXT_LEGAL_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_LEGAL_POSITION.disabled=true;"); 
			out.println("}"); 
			
			out.println("}"); 
			out.println("else{");
			out.println(" if(confirm(\"Are you sure you want to a Modify record?\")){  ");
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_LEGAL_NO.disabled=false;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}");
			
			out.println("}"); 
			
			
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			//out.println("document.Form1.TXT_COLLECTION_OFFICER.disabled=true;"); 
			//out.println("document.Form1.BUT_TXT_COLLECTION_OFFICER.disabled=true;"); 
			//out.println("document.Form1.TXT_RECEIPT_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_REC_BOOK_NO.disabled=true;"); 
			//out.println("document.Form1.BUT_TXT_REC_BOOK_NO.disabled=true;"); 
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			//----------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_finance_no();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_lawyer();"); 
			out.println("		}"); 
			
			
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 
			
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			
			out.println(""); 
			
			
			
			
			
			out.println("function clear_data(IfCount) {");
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("document.Form1.TXT_LEGAL_NO.value='';");
			out.println("document.Form1.TXT_LEGAL_NO.focus();");
			out.println("	}");
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value='';"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value='';"); 
			out.println("document.Form1.TXT_CLIENT_NAME.value='';"); 
			out.println("document.Form1.TXT_FINANCE_NO.focus();"); 
			out.println("}");
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TXT_LAWYER_CODE.value='';"); 
			out.println("document.Form1.TXT_LAWYER_NAME.value='';"); 
			out.println("document.Form1.TXT_LAWYER_CODE.focus();"); 
			out.println("	}");
			
			
			
			out.println("}");
			
			
			
			
			
			
			out.println("function help_button_finance_no() {"); 
			
			
			//out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@REPOSSESS@\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@ACTIVATED@\";"); 
			//out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"VERIFY2@\"+\"ACTIVATED@\";"); 
			
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_LEGAL_ACTIVITIES_sql','1');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_finance_no() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_LEGAL_POSITION.value=oBj.valout[5];"); 
			out.println("due_amount();");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_due_value&data_val=\"+document.Form1.TXT_FINANCE_NO.value;");
			
			//out.println("load_interface(m_url,'XML');");
			//	out.println("window.open(m_url);");
			
			out.println("}"); 
			
			
			
			out.println("function help_button_lawyer() {"); 
			//	out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			//	out.println("    m_sql = \"TXT_CLIENT_CODE\";"); 
			//	out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			out.println("    Crit = document.Form1.TXT_LAWYER_CODE.value+\"@Y@\";"); 
			
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_LAWYER_CODE_sql','2');"); 
			
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_lawyer() {"); 
			
			out.println("    document.Form1.TXT_LAWYER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_LAWYER_NAME.value=oBj.valout[5];"); 
			
			out.println("}"); 
			
			
			
			out.println("function help_update() {"); 
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    Crit = document.Form1.TXT_LEGAL_NO.value+\"@Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    Crit = document.Form1.TXT_LEGAL_NO.value+\"@N@\";"); 
			out.println("    } ");
			
			
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_LEGAL_CODE_ASSIGN_sql','99');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_LEGAL_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_LAWYER_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_LAWYER_NAME.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_LEGAL_TYPE.value=oBj.valout[8];");
			out.println("    document.Form1.TXT_LEGAL_POSITION.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[10];"); 
			out.println("getDateValues(oBj.valout[11]);");
			out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[12];"); 
			out.println("format_number(document.Form1.TXT_AMOUNT,25);");
			
			out.println("}"); 
			
			
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function Fill_Finance_no(){");
			
			out.println("if('"+m_finance_no+"'!=''){");
			//out.println("alert('"+m_finance_no+"');");
			out.println("    document.Form1.HID_CLOSE_STS.value='"+m_close_status+"';");
			out.println("    document.Form1.Hid_my_scr_name.value='"+my_screen_name+"';");
			
			
			out.println("document.Form1.TXT_FINANCE_NO.value='"+m_finance_no+"'");
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true");
			out.println("document.Form1.BUT_TXT_FINANCE_NO.disabled=true");
			out.println("assignState('M2');");
			out.println("makeRequest(document.Form1.TXT_FINANCE_NO);");
			
			out.println("}");
			
			out.println(" }");
			
			out.println("function due_amount(){");
			out.println("assignState('M4');");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_due_value&data_val=\"+document.Form1.TXT_FINANCE_NO.value;");
			
			out.println("load_interface(m_url,'XML');");
			out.println("}")	;
			
			
			out.println("function close_screen() {");
			out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
			out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		      window.close();"); 
			out.println("window.opener.get_Application_numbers('FINANCE_NO','ASC');");			
			out.println("		     }"); 
			out.println("		 }"); 
			out.println("		else { "); 
			out.println("		     close_window();"); 
			out.println("		}"); 
			out.println("}");
			
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("     document.Form1.elements[\"TXT_INS_NEW_DATE_DD_\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_INS_NEW_DATE_MM_\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_INS_NEW_DATE_YY_\"+m_row].value=val;");
			out.println("check_date(document.Form1.elements[\"hid_TXT_INS_DATE_DD_\"+m_row],document.Form1.elements[\"hid_TXT_INS_DATE_MM_\"+m_row],document.Form1.elements[\"hid_TXT_INS_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_INS_NEW_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_INS_NEW_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_INS_NEW_DATE_YY_\"+m_row]) ");
			out.println("  }");				
			out.println("  else if(document.Form1.hid_cal_date.value=='3'){"); 
			
			out.println("     document.Form1.elements[\"TXT_REV_NEW_DATE_DD_\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_REV_NEW_DATE_MM_\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_REV_NEW_DATE_YY_\"+m_row].value=val;");
			
			out.println("check_date(document.Form1.elements[\"hid_TXT_REV_DATE_DD_\"+m_row],document.Form1.elements[\"hid_TXT_REV_DATE_MM_\"+m_row],document.Form1.elements[\"hid_TXT_REV_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_YY_\"+m_row]) ");
			
			out.println("  }");				
			
			
			
			
			out.println("}");
			out.println("}");
			
			
			
			
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"Fill_Finance_no()\">"); //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_LEGAL_ACTIVITIES\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_my_scr_name' VALUE=\"\">"); 
			
			out.println("<input type=hidden name=\"HID_CLOSE_STS\" value=\"N\">");
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Legal Actions-Assignments - New </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
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
			
			out.println("<td width='30%' ><DIV id='DIV_TXT_LEGAL_NO'  class=div_input>Legal Number *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LEGAL_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_LEGAL_NO)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" disabled onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\" >"); //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\"
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"...\" onClick=\"help_button_finance_no()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"\" disabled></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style=\"width:250px;\" maxlength='250' size='22'  onblur=\"\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LAWYER_CODE'  class=div_input>Lawyer Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LAWYER_CODE' maxlength='15' size='15' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_LAWYER_CODE)\" >"); //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\"
			out.println("<input class='but_input' type='button' name='BUT_TXT_LAWYER_CODE' value=\"...\" onClick=\"help_button_lawyer()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LAWYER_NAME'  class=div_input>Lawyer Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LAWYER_NAME' style=\"width:250px;\" maxlength='10' size='10' onblur=\"\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LEGAL_TYPE'  class=div_input>Legal Type * </DIV></td>"); 
			//out.println("<td width='30%' >Legal Type</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LEGAL_TYPE' maxlength='10' size='10' onblur=\"\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			*/
			
			
			out.println("<tr>"); 
			//out.println("<td width='30%' >Default Value </td>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_LEGAL_TYPE'  class=div_input>Legal Type * </DIV></td>"); 
			out.println("<td width='40%' ><select class='txt_input' name='TXT_LEGAL_TYPE'>");
			out.println("<option value='TEST' selected>Test</option>");
			out.println("<option value='COUN' >Count</option>");
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Legal Position</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LEGAL_POSITION' maxlength='10' disabled size='10' onblur=\"\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Remarks</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REMARKS' style=\"width:250px;\" maxlength='200' size='200' onblur=\"\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_COURT_DATE'  class=div_input>Court Date *[DD-MM-YYYY]</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_COURT_DATE_DD' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_COURT_DATE_MM' maxlength='2' size='2' >");
			out.println("                 <input class='txt_input5' type='text' name='TXT_COURT_DATE_YY' maxlength='4' size='4' onBlur='checkMonthLength(document.Form1.TXT_COURT_DATE_DD,document.Form1.TXT_COURT_DATE_MM,document.Form1.TXT_COURT_DATE_YY)'><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Finance Due Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_AMOUNT' maxlength='25' size='22' disabled onBlur=\"check_number(document.Form1.TXT_AMOUNT,25)\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			
			
			/*out.println("<tr class=tr_input>");
			out.println("<td >Settlement Mode</td>");
			out.println("<td><SELECT name=\"TXT_SETTELMENT_MODE\" class=\"txt_input\" onChange=\"\"> ");
			out.println("<OPTION value=\"CHEQUE\" >Cheque  </OPTION>");
			out.println("<OPTION value=\"CASH\"   >Cash    </OPTION>");
				
			out.println("</SELECT></TD>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
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
