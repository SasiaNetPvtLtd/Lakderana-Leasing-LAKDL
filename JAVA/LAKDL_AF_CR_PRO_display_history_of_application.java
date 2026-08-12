//--
//SCREEN NAME	:HISTORY OF APPLICATION
//CREATED BY	:DELANJALI
//DATE/TIME		:02-02-2007
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_display_history_of_application extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	String m_chksql;
	String m_invoice,m_po_no;
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3,rs4;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_schema_name = m_sn_methods.schema_name;
 
				
							stmt=conn.createStatement();
							stmt1=conn.createStatement();
							stmt2=conn.createStatement();
							stmt3=conn.createStatement();
							stmt4=conn.createStatement();
				
		m_chksql=req.getParameter("chksql");
		
		 if(m_chksql.equals("main_page")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>History of application</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.hid_view.value=='D'  && document.Form1.hid_assig.value=='J1' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('No Records to View');");
			out.println("			}");
			out.println("else if(data_vec.length> 0  && document.Form1.hid_view.value=='D' && document.Form1.hid_assig.value=='J1' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_history_of_application?chksql=report_details&client_no=\"+document.Form1.TXT_CLIENT_CODE.value+\"&reges_no=\"+document.Form1.TXT_REG_NO.value+\"&app_no=\"+document.Form1.hid_app.value+\"&finance_no=\"+document.Form1.hid_finance.value+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("			}");
			out.println("			if(data_vec.length==0  && document.Form1.TXT_CLIENT_CODE.value!=\"\"  &&  document.Form1.hid_assig.value=='J3' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("help_button_3()");	
			out.println("			}");
			out.println("		if(data_vec.length==0   && document.Form1.TXT_FINANCE_NO.value!=\"\"  &&  document.Form1.hid_assig.value=='J2' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("help_button_2()");	
			out.println("			}");
			out.println("			if(data_vec.length==0  && document.Form1.TXT_REG_NO.value!=\"\"  &&  document.Form1.hid_assig.value=='J4' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("help_button_4()");	
			out.println("			}");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_assig.value=='J1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application&data_val=\"+obj.value;");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value=='J2'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application_finance&data_val=\"+obj.value;");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value=='J3'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application_client&data_val=\"+obj.value;");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value=='J4'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application_reg&data_val=\"+obj.value;");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_INVOICE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_INVOICE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_REG_NO.value==\"\"){  "); 
			out.println("DIV_TXT_REG_NO.style.color='red';");
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
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_history_of_application';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
		///	out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_history_of_application?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_history_of_application?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_display_history_of_application\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" History of application - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" History of application - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_PRICING_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_NET_PRICE.disabled=true;"); 
			out.println("document.Form1.TXT_CURR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_INSURANCE_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_REVENUE_LICENSE_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_LUXURY_TAX_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS.disabled=true;"); 
			out.println("document.Form1.TXT_BRANCH_ID.disabled=true;"); 
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=true;"); 

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

			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[4] ==\" \"){"); 
			out.println("clear_data()");
			out.println(" }");
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");

			out.println("else if(IfCount=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");

			out.println("else if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("}");
			out.println("}");	

			

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			out.println("function clear_data(){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			out.println("document.Form1.hid_finance.value=\"\";"); 
			out.println("document.Form1.hid_app.value=\"\";"); 
		  out.println("document.Form1.hid_client.value=\"\";"); 

			out.println("document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("document.Form1.TXT_REG_NO.value=\"\";"); 
			out.println("}"); 

			out.println("function clear_data_all(){"); 
			out.println(" if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			out.println("document.Form1.TXT_FINANCE_NO.focus();"); 
			out.println("}"); 
			out.println("if(document.Form1.hid_help_type.value==\"3\"){;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("document.Form1.TXT_CLIENT_CODE.focus();"); 
			out.println("}"); 
			out.println("if( document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("document.Form1.TXT_REG_NO.value=\"\";"); 
			out.println("document.Form1.TXT_REG_NO.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println(""); 
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

	
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" if(document.Form1.TXT_REG_NO.value==\"\" && document.Form1.TXT_CLIENT_CODE.value==\"\" && document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("document.Form1.hid_app.value=\"\"");
			out.println("document.Form1.hid_finance.value=\"\"");
			out.println("document.Form1.hid_client.value=\"\"");
			out.println(" }");
			out.println("    Crit =document.Form1.TXT_REG_NO.value+\"@\"+document.Form1.hid_app.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_INVOICE_NO_app_sql','4');");
			out.println("}"); 
			out.println(""); 
		
			out.println("function help_value_assign_4(oBj) {"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[2];"); 
			out.println("   document.Form1.hid_finance.value=oBj.valout[5];"); 
			out.println("   document.Form1.hid_app.value=oBj.valout[4];"); 
			out.println("}"); 



			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
		  out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("   document.Form1.hid_app.value=\"\";"); 
   	  out.println("    Crit =document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.hid_app.value+\"@Y@\";"); 
			out.println(" }");
			out.println("else{");
			out.println("    Crit =document.Form1.hid_client.value+\"@\"+document.Form1.hid_app.value+\"@Y@\";"); 
			out.println(" }");
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CLIENT_CODE_app','3');");
			out.println("}"); 
			out.println(""); 
		
			out.println("function help_value_assign_3(oBj) {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("   document.Form1.hid_app.value=oBj.valout[4];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'FinanceSql_new','2');");
			out.println("}");
				
			out.println("function help_value_assign_2(oBj) {"); 
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("   document.Form1.hid_finance.value=oBj.valout[2];"); 
			out.println("   document.Form1.hid_app.value=oBj.valout[3];"); 
			out.println("   document.Form1.hid_client.value=oBj.valout[4];"); 
			out.println("}");

			out.println("function assig(val) {"); 
			out.println("document.Form1.hid_assig.value=val");
			out.println("}");

			out.println("function view() {"); 
			out.println(" if(document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("document.Form1.hid_finance.value=\"\"");
			out.println("document.Form1.hid_app.value=\"\"");
			out.println(" }");
			out.println(" if(document.Form1.TXT_CLIENT_CODE.value==\"\"){");
			out.println("document.Form1.hid_client.value=\"\"");
			out.println(" }");
			out.println("document.Form1.hid_view.value=\"D\"");
			out.println("assig('J1')");
			out.println("makeRequest(document.Form1.hid_app)");
			out.println("}");
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_INVOICE_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_INVOICE_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_INVOICE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_REG_DATE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_NET_PRICE.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_CURR_CODE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_INSURANCE_DATE.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_REVENUE_LICENSE_DATE.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_LUXURY_TAX_DATE.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_ADDRESS.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_BRANCH_ID.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[20];"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[21];"); 

			out.println("}"); 
//***************************************************************************************************************************
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_finance' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_view' VALUE=\"\">"); 
					
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>History of application</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO' class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input'  type='text' name='TXT_FINANCE_NO' value=\"\" maxlength='15' size='15' onblur=\"assig('J2'),makeRequest(document.Form1.TXT_FINANCE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
			out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"assig('J3'),makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_CLIENT_CODE' value=\"Help\" onClick=\"help_button_3()\">"); 

			out.println("</tr>");
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_REG_NO'  class=div_input>Vehicle No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REG_NO' maxlength='20' size='20' onblur=\"assig('J4'),makeRequest(document.Form1.TXT_REG_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_REG_NO' value=\"Help\" onClick=\"help_button_4()\"></td>"); 

			out.println("<td width='10%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


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
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		else if(m_chksql.equals("report_details")){		

			int i=0;		
			int x=0;	
			String m_client_code = req.getParameter("client_no");	
			String m_reg= req.getParameter("reges_no");	
			String m_finance = req.getParameter("finance_no");	
			String m_app = req.getParameter("app_no");	
		
		
		   rs4 = stmt4.executeQuery ("SELECT count(*) "+
		   "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER a,"+m_schema_name+".AF_CO_MAS_CURRENCY b "+
		   "WHERE APPLICATION_NO='"+m_app+"' and a.curr_code=b.curr_code ");
			
			boolean more4 = rs4.next();

			if(rs4.getInt(1)!=0){
		
			rs1 = stmt.executeQuery ("SELECT DISTINCT FINANCE_NO,APPLICATION_NO,CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE   upper(FINANCE_NO) like UPPER('"+m_finance+"%') and  upper(CLIENT_CODE) like UPPER('"+m_client_code+"%') "+
			" ORDER BY FINANCE_NO DESC ");
					
			boolean more1 = rs1.next();
	
			out.println("<html><head><font 12pt arial><title>Application History</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
	
			out.println("<body leftmargin='0' topmargin='0' class=body>");
			out.println("<br>");
			out.println("<br>");
			out.println("<div align='center' width='180%' class='rep-body' style='{font: bold;text-align:center;}'>Application History</div>");
			out.println("<form name='form1'>");
			out.println("<table border='1' width='100%' bgcolor='white' style='{color: black; font: bold 10px;}'>");
			out.println("</table>");			
	    out.println("<HR width='100%'>");
	
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	    int d = 0; 
	
			while(more1){
			//************APPLICATION NO**********************************************************************************************************************
			out.println("<tr>");
			out.println("<td width='14%' class='txt_body' style='{text-align:left;}'><b>Application No</td>");
			out.println("<td width='15%' class='txt_body' style='{text-align:left;}'><b>:-  "+rs1.getString(2)+"</td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
			out.println("<tr></tr>");
			out.println("<tr></tr>");
	
			int j = 0; 
			
	   rs = stmt1.executeQuery ("SELECT PURCHASE_ORDER_NO,APPLICATION_NO,nvl(VENDER_CODE,'-'),nvl((select name from "+m_schema_name+".AF_CO_MAS_VENDORS where vendor_code=VENDER_CODE),'-'),nvl(TO_CHAR(ISSUED_DATE,'DD-MM-YYYY'),'-'),nvl(TO_CHAR(DRIVING_LICENSE_DATE,'DD-MM-YYYY'),'-'),to_char(TOTAL_NET,'999,999,999.99'), "+
	    "to_char(TOTAL_VAT,'999,999,999.99'),TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),a.CURR_CODE,curr_symbol,to_char((total_vat+total_net),'999,999,999.99'),EXCHANGE_RATE,ACC_NO,BRANCH_CODE,PRINTED_DATE, "+
	    "PRINTED_USER,APPROVAL_STATUS,SUPPLIER_APPROVAL_DATE,DISB_TO,DISB_BY,DISB_DATE,CHEQUE_NO "+
	    "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER a,"+m_schema_name+".AF_CO_MAS_CURRENCY b "+
	    "WHERE APPLICATION_NO='"+rs1.getString(2)+"' and a.curr_code=b.curr_code ");
	
			boolean more = rs.next();
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			//more1=rs1.next();
			out.println("<tr class=\"pdn_txtpos2\"><td width=\"10%\"><b>Purchase Order No</td>");
			out.println("<td width=\"10%\"><b>Vendor</td>");
			out.println("<td width=\"25%\"><b>Issued Date</td>");
			out.println("<td width=\"9%\"><b>Driving License Date</td>");
			out.println("<td width=\"9%\" align=right><b>Total Net</td>");
			out.println("<td width=\"9%\" align=right><b>Total Vat</td>");
			out.println("<td width=\"9%\" align=right><b>Total</td>");
			out.println("<td width=\"9%\"><b>PO Date</td>");
			out.println("<td width=\"10%\"><b>Currency</td></tr>");
		
				while(more){
				
			//***************PO NO *******************************************************************************************************************
		    rs2 = stmt2.executeQuery ("SELECT A.PURCHASE_ORDER_NO,APPLICATION_NO,VENDER_CODE,PRO_INVOICE_NO,(select finance_no from "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS where application_no='"+rs.getString(2)+"'),TO_CHAR(ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(DRIVING_LICENSE_DATE,'DD-MM-YYYY'),TOTAL_NET, "+
		    "TOTAL_VAT,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),CURR_CODE,EXCHANGE_RATE,ACC_NO,BRANCH_CODE,PRINTED_DATE, "+
		    "PRINTED_USER,APPROVAL_STATUS,SUPPLIER_APPROVAL_DATE,DISB_TO,DISB_BY,DISB_DATE,CHEQUE_NO "+
		    "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
		    "WHERE A.PURCHASE_ORDER_NO='"+rs.getString(1)+"' "+
				"AND A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO ");


				boolean more2 = rs2.next();
			
				if(j>0 && j%2==1){
			  out.println("<tr class=tr_input1 >");
				}
				else{
				out.println("<tr class=tr_input >");
				}
		

				out.println("<td width=\"10%\">"+rs.getString(1)+"</td>");
				out.println("<td width=\"10%\">"+rs.getString(4)+"</td>");
				out.println("<td width=\"25%\">"+rs.getString(5)+"</td>");
				out.println("<td width=\"9%\">"+rs.getString(6)+"</td>");
				out.println("<td width=\"9%\" align=right>"+rs.getString(7)+"</td>");
				out.println("<td width=\"9%\" align=right>"+rs.getString(8)+"</td>");
				out.println("<td width=\"9%\" align=right>"+rs.getString(12)+"</td>");
			
				out.println("<td width=\"9%\">"+rs.getString(9)+"</td>");
				out.println("<td width=\"10%\">"+rs.getString(11)+"</td></tr>");
			
			
				int g=0;
				while(more2){

				//*************INVOICE NO*********************************************************************************************************************
			 rs3 = stmt3.executeQuery ("SELECT REQ_NO,FINANCE_NO,PRO_FORMA_INVOICE_NO,DOCUMENT_CODE,(select description from "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED where code=DOCUMENT_CODE),DOCUMENT_STATUS,REASON,REQUESTED_USER, "+
			 "REQUESTED_DATE,APPRO_USER,APPRO_DATE "+
			 "FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL "+
			 "WHERE upper(FINANCE_NO) =('"+rs2.getString(5)+"') "+
			 "AND PRO_FORMA_INVOICE_NO='"+rs2.getString(4)+"' ");
			
				boolean more3 = rs3.next();

				out.println("<tr>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("</tr>");
		

				out.println("<tr><td></td><td width=\"10%\"><b>Invoice No</td>");
			
				out.println("<td><b>:  "+rs2.getString(4)+"</td></tr>");





					int h=0;


	//**********************************************************************************************************************************

					while(more3){
					if(h==0){
					out.println("<tr ><td></td><td width=\"10%\"><b>Documents</td></tr>");
				
					}
					out.println("<td width=\"15%\">&nbsp</td><td width=\"15%\"></td><td>:  "+rs3.getString(5)+"</td></tr>");
					h=h+1;
					more3=rs3.next();
					if(!more3){
					break;
					}
			}
		//**********************************************************************************************************************************


					out.println("<tr></tr>");
					out.println("<tr></tr>");




					g=g+1;
					more2=rs2.next();
					if(!more2){
					break;
					}
			}

		//**********************************************************************************************************************************


					more=rs.next();
					j=j+1;
					
					if (!more)
					{
					break;
					}
			
					}	
			
					out.println("<input type=hidden name=hid_count value="+j+">");
					d=d+1;	
					more1=rs1.next();
			
						if (!more1)
					{
					break;
					}
					out.println("<tr>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("</tr>");
					//out.println("</table>");
					}
			
					out.println("<input type=hidden name=hid_count_inv value="+d+">");	
					out.println("</table>");
					
				
					out.println("</table>");
					out.println("</form>");
					out.println("</body>");
			    out.println("</html>");
			
							
							}
					
					}	
					
					
//--------------------------------------------------------------------------------------------------------------------			
		
		

			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
