//--
//SCREEN NAME	:MIS - PAYMENT REPORT
//CREATED BY	:DELANJALI
//DATE/TIME		:2007-03-26
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_display_payment_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	java.text.NumberFormat nf,nf1;
	public String m_chksql;
	

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();

      m_chksql=req.getParameter("chksql");
			
			String m_schema_name = m_sn_methods.schema_name;
			
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			if(m_chksql.equals("main_page")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Payment Report Details</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_PAYMENT_NO.value!=\"\" && document.Form1.hid_assig.value==\"G7\"){");
			out.println("help_button_7();");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_SUS_REF_NO.value!=\"\" && document.Form1.hid_assig.value==\"G8\"){");
			out.println("help_button_8();");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_assig.value==\"G2\"){");
			out.println("help_button_2();");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_INVOICE_NO.value!=\"\" && document.Form1.hid_assig.value==\"G3\"){");
			out.println("help_button_3();");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_assig.value==\"G4\"){");
			out.println("help_button_4();");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_assig.value==\"G6\"){");
			out.println("help_button_6();");
			out.println("			}");
			out.println("}");

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_display_payment_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_display_payment_report?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_payment_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Payment Report Details - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Payment Report Details - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
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
			
			out.println("function makeRequest1(obj) {");
			out.println("if(document.Form1.hid_assig.value==\"G7\"){");//PAYMENT NO
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_payment_details_new&data_val=\"+obj.value+\"&ac_status=Y\";");
	//	out.println("window.open(m_url)");		
			out.println("}");
			out.println("if(document.Form1.hid_assig.value==\"G8\"){");//SUS REF NO
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_sus_ref&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value==\"G2\"){");//APPLICATION NO
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_application&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value==\"G3\"){");//INVOICE NO
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_invoice&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value==\"G4\"){");//CLIENT CODE
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_client&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value==\"G5\"){");//INQUIRY NO
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_inquiry&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value==\"G6\"){");//FINANCE NO
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
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
			out.println("	help_value_assign_3()");
	  	out.println("		}"); 
			
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("	help_value_assign_4()");
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("	help_value_assign_5()");
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("	help_value_assign_6()");
	  	out.println("		}"); 
					out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("	help_value_assign_7()");
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("	help_value_assign_8()");
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
			out.println("clear()");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");
			out.println("clear()");
			out.println("	}	"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function clear(){");
			out.println("if(document.Form1.hid_help_type.value==\"7\"){");
			out.println(" document.Form1.TXT_PAYMENT_NO.value=\"\";"); 
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"8\"){");
			out.println(" document.Form1.TXT_SUS_REF_NO.value=\"\";"); 
			out.println("}");

			out.println("if(document.Form1.hid_help_type.value==\"2\"){");
			out.println(" document.Form1.TXT_APPLICATION_NO.value=\"\";"); 
			out.println("   document.Form1.hid_client_code.value=\"\";");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"3\"){");
			out.println(" document.Form1.TXT_INVOICE_NO.value=\"\";"); 
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"4\"){");
			out.println(" document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"5\"){");
			out.println(" document.Form1.TXT_INQUIRY_NO.value=\"\";");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"6\"){");
			out.println(" document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			out.println("}");
			out.println("}");
		
			out.println("function assig(val) {"); 
			out.println("document.Form1.hid_assig.value=val");
			out.println("}");
			

		  out.println("function view() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_payment_report1?chksql=MAIN&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sus_ref_no=\"+document.Form1.TXT_SUS_REF_NO.value+\"&application_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"&payment_no=\"+document.Form1.TXT_PAYMENT_NO.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&invoice_no=\"+document.Form1.TXT_INVOICE_NO.value+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");



			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql_1\";"); 
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"VERIFY@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_2() {"); 
			out.println("   document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
			out.println("   document.Form1.hid_client_code.value=oBj.valout[4];");
			out.println("}");

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_INVOICE_NO_app_sql1\";"); 
			out.println("    m_criteria = document.Form1.TXT_INVOICE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_3() {"); 
			out.println("   document.Form1.TXT_INVOICE_NO.value=oBj.valout[2];");
			out.println("}");

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"ClientSql1\";"); 
			out.println(" if(document.Form1.hid_client_code.value==\"\"){");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("}");
			out.println(" if(document.Form1.hid_client_code.value!=\"\" && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("    m_criteria = document.Form1.hid_client_code.value+\"@Y@\";"); 
			out.println("}");
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_4() {"); 
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("}");

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_INQUIRY_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_INQUIRY_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_INQUIRY_NO.value=oBj.valout[2];"); 
			out.println("}"); 



			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql_new2\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_6() {"); 
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");


			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";");
			out.println("    m_sql = \"m_help_TXT_PAYMENT_NO\";"); 
			out.println("    m_criteria = document.Form1.TXT_PAYMENT_NO.value+\"@\"+document.Form1.TXT_SUS_REF_NO.value+\"@\";"); 

			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_7() {"); 
			out.println("   document.Form1.TXT_PAYMENT_NO.value=oBj.valout[2];"); 
			out.println("}");

			out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";");
			out.println("    m_sql = \"m_help_TXT_SUS_REF_NO\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUS_REF_NO.value+\"@\"+document.Form1.TXT_INVOICE_NO.value+\"@\";"); 

			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_8() {"); 
			out.println("   document.Form1.TXT_SUS_REF_NO.value=oBj.valout[2];"); 
			out.println("}");


			out.println("function clear_client() {"); 
			out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\"){");
			out.println("   document.Form1.hid_client.value=\"\";"); 
			out.println("}");
			out.println("}");


//-----------------------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Payment Report Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>");

			out.println("<table align='center' width='100%' class='table'>"); 
			
			
			out.println("<tr>");		
			out.println("<td width='30%' ><DIV id='DIV_TXT_APP_NO' class=div_input>Application No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assig('G2'),makeRequest1(document.Form1.TXT_APPLICATION_NO),clear_client()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APP_NO' value=\"Help\" onClick=\" help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");		


			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INVOICE'  class=div_input>Invoice No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' onblur=\"assig('G3'),makeRequest1(document.Form1.TXT_INVOICE_NO)\">"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_INVOICE' value=\" Help \" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUS_REF_NO'  class=div_input>Sus Ref No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUS_REF_NO' maxlength='15' size='15' onblur=\"assig('G8'),makeRequest1(document.Form1.TXT_SUS_REF_NO)\">"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_SUS_REF' value=\" Help \" onClick=\"help_button_8()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYMENT_NO'  class=div_input>Payment No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYMENT_NO' maxlength='15' size='15' onblur=\"assig('G7'),makeRequest1(document.Form1.TXT_PAYMENT_NO)\">"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_PAYMENT' value=\" Help \" onClick=\"help_button_7()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			


			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT'  class=div_input>Client Code</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' onblur=\"assig('G4'),makeRequest1(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_CLIENT' value=\" Help \" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 

			out.println("</tr>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assig('G6'),makeRequest1(document.Form1.TXT_FINANCE_NO)\">"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_FINANCE' value=\" Help \" onClick=\"help_button_6()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("<td width='10%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view()\"></td>"); 

			out.println("</tr>"); 



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
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
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
