//--
//SCREEN NAME	:SECURITY FILE MOVEMENT APPROVAL
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_PRO_display_security_and_marketing_approval extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3,rs4;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();

      m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			String m_fin_code,m_invoice;
			
			String m_schema_name = m_sn_methods.schema_name;
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
		 if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Finance - Security File Movement Approval</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_cnt=0");
			out.println("var m_chk=0");
			out.println("var b_flag=0;");
				
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			out.println(" ");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_st.value='T'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_approval?chksql=request_details&m_val=\"+document.Form1.hid_status.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("return true;"); 
			out.println("}"); 


			out.println("function count_docs(){ ");
			out.println("count=0;");
			out.println("for(i=0;i<document.Form1.hid_count.value;i++){");
			out.println("m_chk=\"chk_app_\"+i;");
			out.println("if(document.Form1.elements[m_chk].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			out.println("}");		
			out.println("if(count>0){");
			out.println("return true;");
			out.println("}"); 
			out.println("else{");
			out.println("return false;");
			out.println("}"); 
			out.println("}"); 
			
			
						
			out.println("function check_select(){ "); 
			out.println("if(request_details.innerHTML==\"\"){");
			out.println("alert('Please Select Finance no');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("else if(!count_docs()){"); 
			out.println("alert('Please Select Document');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
      out.println("}"); 


			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("if(b_flag==1){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_save_security_and_marketing_approval?m_count='+m_cnt+'';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please select a document\");");
			out.println("} "); 
			out.println("} "); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
  		out.println("} "); 

			out.println("function load_lock(){	"); 
			//t.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_approval?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_approval?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_PRO_display_security_and_marketing_approval\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Finance - Security File Movement Approval - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Finance - Security File Movement Approval - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PRO_FORMA_INVOICE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_DOCUMENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_DOCUMENT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_REASON.disabled=true;"); 
			out.println("document.Form1.TXT_REQUESTED_USER.disabled=true;"); 
			out.println("document.Form1.TXT_REQUESTED_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_APPRO_USER.disabled=true;"); 
			out.println("document.Form1.TXT_APPRO_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_COUNT.disabled=true;"); 
			out.println("document.Form1.TXT_IN_COUNT.disabled=true;"); 
			out.println("document.Form1.TXT_OUT_COUNT.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"Request-Approval\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
			out.println("document.Form1.hid_save.value=\"Return\";"); 
			out.println("document.Form1.hid_status.value=\"Return-Approval\";");  
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
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[4] ==\" \"){"); 
			out.println(" document.Form1.TXT_FINANCE_NO.value = \"\" ");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" }");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("if(document.Form1.hid_help_type.value=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
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
			out.println("clear_data();"); 
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println("function clear_data(){"); 
			out.println(" document.Form1.TXT_FINANCE_NO.value = \"\" ");
			out.println(" request_details.innerHTML = ''; ");
			out.println("}		"); 
			out.println(""); 


			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
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
			out.println("    m_sql = \"m_help_TXT_REQ_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_REQ_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_REQ_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){");
			out.println("    m_sql = \"FinanceSql_return\";"); 
			out.println("    m_criteria =document.Form1.TXT_FINANCE_NO.value+\"@\"+\"REQUESTED@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value=='EDIT'){");
			out.println("    m_sql = \"FinanceSql_return\";"); 
			out.println("    m_criteria =document.Form1.TXT_FINANCE_NO.value+\"@\"+\"RETURNED@\";");
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			out.println("}");

			out.println("function help_value_assign_2(oBj) {"); 
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println(" 	makeRequest(); ");
			
			out.println("}");

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_PRO_FORMA_INVOICE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PRO_FORMA_INVOICE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_PRO_FORMA_INVOICE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_DOCUMENT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DOCUMENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_DOCUMENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_REQ_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_REQ_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_REQ_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_REQ_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_PRO_FORMA_INVOICE_NO.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_DOCUMENT_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_DOCUMENT_STATUS.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_REASON.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_REQUESTED_USER.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_REQUESTED_DATE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_APPRO_USER.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_APPRO_DATE.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_TOTAL_COUNT.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_IN_COUNT.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_OUT_COUNT.value=oBj.valout[16];"); 
			out.println("}"); 
			
			out.println("function change(row1,row) {"); 
			out.println(" b_flag=0");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==false){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value=\"N\";"); 
			out.println(" b_flag=0");
			out.println("}");
			out.println("else if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==true){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value=\"Y\";"); 
			out.println(" b_flag=1");
			out.println("}");
			out.println("}"); 
			
			
			out.println("function change_C(row) {"); 
			out.println(" b_flag=0");
			out.println("   if(document.Form1.elements[\"chk_app_C_\"+row].checked==false){"); 
			out.println("    document.Form1.elements[\"chk_app_C_\"+row].value=\"N\";"); 
			out.println(" b_flag=0");
			out.println("}");
			out.println("else if(document.Form1.elements[\"chk_app_C_\"+row].checked==true){"); 
			out.println("    document.Form1.elements[\"chk_app_C_\"+row].value=\"Y\";"); 
			out.println(" b_flag=1");
			out.println("}");
			out.println("}"); 


			out.println("function clear_screen(){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("request_details.innerHTML = ''; ");
			out.println("}"); 
			
			
//________________________________________________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('Request-Approval')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Request-Approval\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Security File Movement Approval</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input style='{width:110}' type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Request-Approval\");' onclick='load_screen_status(\"NEW\")' value=\"Request-Approval\"></td>");  
			out.println("<td width='10%' align='center'><input style='{width:110}' type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Return-Approval\");' onClick='load_screen_status(\"EDIT\"),clear_screen()' value=\"Return-Approval\"></td>");  
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

			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO' class=div_input>Finance No*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' value=\"\" type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"makeRequest()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"...\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=request_details></div></td></tr></table>");
			
			
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
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	

			else if(m_chksql.equals("request_details")){
			int i=0;		
			  
			String m_finance_no = req.getParameter("finance_no");	
			String m_screen_name= req.getParameter("m_val");	
			
			 if(m_screen_name.equals("Request-Approval")){
			 int d = 0; 
			
			rs1 = stmt1.executeQuery ("SELECT DISTINCT b.PRO_FORMA_INVOICE_NO,A.TOTAL_COUNT,A.IN_COUNT,REQ_NO,A.APPLICATION_NO "+
			"FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
      "WHERE A.FINANCE_NO=B.FINANCE_NO "+
			"AND DOC_APP_TYPE='ASSET' "+
			"AND B.PRO_FORMA_INVOICE_NO IS NOT NULL "+
			"AND B.DOCUMENT_CODE=CODE "+
      "AND B.DOCUMENT_STATUS='REQUESTED' "+//approved_out
      "AND A.FINANCE_NO='"+m_finance_no+"' ");
					
			boolean more1 = rs1.next();

			
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			//out.println("<br>");			

		
			while(more1){
				
			if(d==0){
						out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");

			out.println("<tr>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Application No</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>:-  "+rs1.getString(5)+"<input class='txt_input' type='hidden' name=TXT_APPLICATION_NO value=\""+rs1.getString(5)+"\"></td>"); 
			out.println("<td width='70%'></td>");
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Request No</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>:-  "+rs1.getString(4)+"<input class='txt_input' type='hidden' name=TXT_REQ value=\""+rs1.getString(4)+"\"></td>"); 
			out.println("<td width='70%'></td>");
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			out.println("</table>");
		
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
		  out.println("<tr  ><td width='15%' style='{text-align:left;}'><b><u>Asset Wise</td></tr>");
	  
			out.println("<tr></tr>");
			out.println("<tr></tr>");
		
		
			out.println("<tr  class=\"pdn_txtpos2\"><td width='15%' style='{text-align:left;}'><b>Invoice No </td>");
		  out.println("<td width='15%' style='{text-align:left;}'><b>Document Code</td>");
			out.println("<td width='30%' style='{text-align:left;}'><b>Document Name</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Document Status</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Requested User</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Requested Date</td>");
			out.println("<td width='10%' style='{text-align:center;}'><b>Select</td>");
			out.println("</tr>");	
				out.println("<table>");
		}		
			//		out.println("<br>");	
	
			rs = stmt.executeQuery
			("SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,b.PRO_FORMA_INVOICE_NO, "+
        "A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,B.DOCUMENT_CODE, "+
        "B.DOCUMENT_STATUS,NVL(B.REASON,'-'),B.REQUESTED_USER,TO_CHAR(B.REQUESTED_DATE,'DD-MM-YYYY'),DESCRIPTION "+
        "FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
        "WHERE A.FINANCE_NO=B.FINANCE_NO "+
				"AND DOC_APP_TYPE='ASSET' "+
				"AND B.PRO_FORMA_INVOICE_NO IS NOT NULL "+
				"AND b.PRO_FORMA_INVOICE_NO='"+rs1.getString(1)+"'  "+
				"AND REQ_NO='"+rs1.getString(4)+"'  "+
				"AND B.DOCUMENT_CODE=CODE "+
        "AND B.DOCUMENT_STATUS='REQUESTED' "+
        "AND A.FINANCE_NO='"+m_finance_no+"' "); 
	
			boolean more = rs.next();



		out.println("<tr class=\"tr_input\">");
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		out.println("<input class='txt_input' type='hidden' name=TXT_INVOICE_"+d+" value=\""+rs1.getString(1)+"\"><input class='txt_input' type='hidden' name=TXT_TOT_COUNT_"+d+" value=\""+rs1.getString(2)+"\"><input class='txt_input' type='hidden' name=TXT_IN_COUNT_"+d+" value=\""+rs1.getString(3)+"\">");
	  
		int j = 0; 
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");

		while(more){

		if(j>0 && j%2==1){
	  out.println("<tr class=tr_input1 >");
		}
		else{
		out.println("<tr class=tr_input >");
		}
		
		out.println("<td width='15%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"</td>");
		out.println("<td width='15%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs.getString(8)+"')\"><U>"+rs.getString(8)+"<input class='txt_input' type='hidden' name=TXT_DOC_CODE_"+d+"_"+j+" value=\""+rs.getString(8)+"\"></td>");
		out.println("<td width='30%' style='{text-align:left;}'>"+rs.getString(13)+"<input class='txt_input' type='hidden' name=TXT_DOC_NAME_"+d+"_"+j+" value=\""+rs.getString(13)+"\"></td>");
		out.println("<td width='15%' style='{text-align:left;}'>"+rs.getString(9)+"<input class='txt_input' type='hidden' name=TXT_DOC_STATUS_"+d+"_"+j+" value=\""+rs.getString(9)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(11)+"<input class='txt_input' type='hidden' name=TXT_REQ_USER_"+d+"_"+j+" value=\""+rs.getString(11)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(12)+"<input class='txt_input' type='hidden' name=TXT_REQ_DATE_"+d+"_"+j+" value=\""+rs.getString(12)+"\"><input class='txt_input' type='hidden' name=TXT_REASON_"+d+"_"+j+" value=\""+rs.getString(12)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:center;}'><input type='checkbox' name=chk_app_"+d+"_"+j+" value=\"N\" unchecked onClick=\"change("+d+","+j+")\"><input class='txt_input' type='hidden' name=TXT_INVOICE_1_"+d+"_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
		out.println("</tr>");
		more=rs.next();
		j=j+1;
		
		if (!more)
		{
		break;
		}
		
		}		

		out.println("<input type=hidden name=hid_count value="+j+">");
		out.println("</table>");
		out.println("<br>");
		out.println("<br>");
		out.println("</table>");
		more1=rs1.next();

		d=d+1;	

			if (!more1)
		{
		break;
		}
		}


		out.println("<input type=hidden name=hid_count_inv value="+d+">");	
		out.println("</table>");
		
		rs4 = stmt4.executeQuery ("SELECT DISTINCT b.PRO_FORMA_INVOICE_NO,A.TOTAL_COUNT,A.IN_COUNT,REQ_NO,A.APPLICATION_NO "+
		"FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
    "WHERE A.FINANCE_NO=B.FINANCE_NO "+
		"AND DOC_APP_TYPE='CLIENT' "+
		"AND B.PRO_FORMA_INVOICE_NO  IS NULL "+
		"AND B.DOCUMENT_CODE=CODE "+
    "AND B.DOCUMENT_STATUS='REQUESTED' "+//approved_out
    "AND A.FINANCE_NO='"+m_finance_no+"' ");
					
		boolean more4 = rs4.next();


		int h=0;

		while(more4){

				rs3 = stmt3.executeQuery ("SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,b.PRO_FORMA_INVOICE_NO, "+
        "A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,B.DOCUMENT_CODE, "+
        "B.DOCUMENT_STATUS,NVL(B.REASON,'-'),B.REQUESTED_USER,TO_CHAR(B.REQUESTED_DATE,'DD-MM-YYYY'),DESCRIPTION "+
        "FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
        "WHERE A.FINANCE_NO=B.FINANCE_NO "+
				"AND DOC_APP_TYPE='CLIENT' "+
				"AND B.PRO_FORMA_INVOICE_NO IS NULL "+
				"AND REQ_NO='"+rs4.getString(4)+"'  "+
				"AND B.DOCUMENT_CODE=CODE "+
        "AND B.DOCUMENT_STATUS='REQUESTED' "+
        "AND A.FINANCE_NO='"+m_finance_no+"' "); 
	
			boolean more3 = rs3.next();
		
		
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					if(h==0){

			out.println("<tr  ><td width='15%' style='{text-align:left;}'><b><u>Client Wise</td></tr>");

			out.println("<tr></tr>");
			out.println("<tr></tr>");

		
			out.println("<tr class=\"pdn_txtpos2\"><td width='15%'>Invoice</td>");
	
			out.println("<td width='15%'  style='{text-align:left;}'><b>Document Code</td>");
			out.println("<td width='30%'  style='{text-align:left;}'><b>Document Name</td>");
			out.println("<td width='15%'  style='{text-align:left;}'><b>Document Status</td>");
			out.println("<td width='10%'  style='{text-align:left;}'><b>Requested User</td>");
			out.println("<td width='10%'  style='{text-align:left;}'><b>Requested Date</td>");
			out.println("<td width='10%'  style='{text-align:center;}'><b>Select</td>");
			out.println("</tr>");	
			
			}

		int a=0;

			while(more3){	
			
			out.println("<tr><td width='10%'><input class='txt_input' type='hidden' name=TXT_REQ_C value=\""+rs4.getString(4)+"\"></td></tr>");
	
			
			if(a>0 && a%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			out.println("<td width='15%'>-</td>");
			out.println("<td width='15%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs3.getString(8)+"')\"><U>"+rs3.getString(8)+"<input class='txt_input' type='hidden' name=TXT_DOC_CODE_C_"+a+" value=\""+rs3.getString(8)+"\"></td>");
			out.println("<td width='30%' style='{text-align:left;}'>"+rs3.getString(13)+"<input class='txt_input' type='hidden' name=TXT_DOC_NAME_C_"+a+" value=\""+rs3.getString(13)+"\"></td>");
			out.println("<td width='15%' style='{text-align:left;}'>"+rs3.getString(9)+"<input class='txt_input' type='hidden' name=TXT_DOC_STATUS_C_"+a+" value=\""+rs3.getString(9)+"\"></td>"); 
			out.println("<td width='10%' style='{text-align:left;}'>"+rs3.getString(11)+"<input class='txt_input' type='hidden' name=TXT_REQ_USER_C_"+a+" value=\""+rs3.getString(11)+"\"></td>"); 
			out.println("<td width='10%' style='{text-align:left;}'>"+rs3.getString(12)+"<input class='txt_input' type='hidden' name=TXT_REQ_DATE_C_"+a+" value=\""+rs3.getString(12)+"\"><input class='txt_input' type='hidden' name=TXT_REASON_C_"+a+" value=\""+rs3.getString(12)+"\"></td>"); 
			out.println("<td width='10%' style='{text-align:center;}'><input type='checkbox' name=chk_app_C_"+a+" value=\"N\" unchecked onClick=\"change_C("+a+")\"></td>"); 
		
			out.println("</tr>");
			more3=rs3.next();
			a=a+1;
			
			if (!more3)
			{
			break;
			}
			
			}		
	
			out.println("<input type=hidden name=hid_count_client value="+a+">");
		
			out.println("</table>");
			more4=rs4.next();
			h=h+1;
			
			if (!more1)
			{
			break;
			}
			}
			out.println("</table>");

			out.println("<input type=hidden name=hid_count_client_1 value="+h+">");
	
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<tr><td>");

			out.println("<td ><input type=hidden name=hid_no_val value=\"1\"></td>");
			out.println("</tr>");
			out.println("</table>");
		
		}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------	
		else if(m_screen_name.equals("Return-Approval")){
	    int d = 0; 

	
			rs1 = stmt1.executeQuery ("SELECT DISTINCT b.PRO_FORMA_INVOICE_NO,A.TOTAL_COUNT,A.IN_COUNT,REQ_NO,A.APPLICATION_NO "+
			"FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
      "WHERE A.FINANCE_NO=B.FINANCE_NO "+
			"AND B.DOCUMENT_CODE=CODE "+
			"AND DOC_APP_TYPE='ASSET' "+
			"AND B.PRO_FORMA_INVOICE_NO IS NOT NULL "+
      "AND B.DOCUMENT_STATUS='RETURNED' "+//approved_out
      "AND A.FINANCE_NO='"+m_finance_no+"' ");
					
			boolean more1 = rs1.next();

			
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			//out.println("<br>");			

	
			while(more1){

			if(d==0){
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");

			out.println("<tr>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Application No</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>:-  "+rs1.getString(5)+"<input class='txt_input' type='hidden' name=TXT_APPLICATION_NO value=\""+rs1.getString(5)+"\"></td>"); 
			out.println("<td width='70%'></td>");
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Request No</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>:-  "+rs1.getString(4)+"<input class='txt_input' type='hidden' name=TXT_REQ value=\""+rs1.getString(4)+"\"></td>"); 
			out.println("<td width='70%'></td>");
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			out.println("</table>");
			//out.println("<br>");		
			}

			rs = stmt.executeQuery ("SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,b.PRO_FORMA_INVOICE_NO, "+
        "A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,B.DOCUMENT_CODE, "+
        "B.DOCUMENT_STATUS,NVL(B.REASON,'-'),B.REQUESTED_USER,TO_CHAR(B.REQUESTED_DATE,'DD-MM-YYYY'),DESCRIPTION,APPRO_USER,TO_CHAR(APPRO_DATE,'DD-MM-YYYY') "+
        "FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
        "WHERE A.FINANCE_NO=B.FINANCE_NO "+
				"AND b.PRO_FORMA_INVOICE_NO='"+rs1.getString(1)+"'  "+
				"AND DOC_APP_TYPE='ASSET' "+
				"AND b.PRO_FORMA_INVOICE_NO is not null "+
				"AND REQ_NO='"+rs1.getString(4)+"'  "+
				"AND B.DOCUMENT_CODE=CODE "+
        "AND B.DOCUMENT_STATUS='RETURNED' "+
        "AND A.FINANCE_NO='"+m_finance_no+"' "); 
	
			boolean more = rs.next();

			out.println("<input class='txt_input' type='hidden' name=TXT_INVOICE_"+d+" value=\""+rs1.getString(1)+"\"><input class='txt_input' type='hidden' name=TXT_TOT_COUNT_"+d+" value=\""+rs1.getString(2)+"\"><input class='txt_input' type='hidden' name=TXT_IN_COUNT_"+d+" value=\""+rs1.getString(3)+"\">"); 
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			int j = 0; 

	
				if(j==0){

			out.println("<tr  ><td width='15%' style='{text-align:left;}'><b><u>Asset Wise</td></tr>");
			out.println("<tr  class=\"pdn_txtpos2\"><td width='10%' style='{text-align:left;}'><b>Invoice No </td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Document Code</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Document Name</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Document Status</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Requested User</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Requested Date</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Req-Approved User</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Req-Approved Date</td>");
			out.println("<td width='5%' style='{text-align:center;}'><b>Select</td>");
			out.println("</tr>");

			}


			while(more){

			if(j>0 && j%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td width='10%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"</td>");
			out.println("<td width='10%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs.getString(8)+"')\"><U>"+rs.getString(8)+"<input class='txt_input' type='hidden' name=TXT_DOC_CODE_"+d+"_"+j+" value=\""+rs.getString(8)+"\"></td>");
			out.println("<td width='15%' style='{text-align:left;}'>"+rs.getString(13)+"<input class='txt_input' type='hidden' name=TXT_DOC_NAME_"+d+"_"+j+" value=\""+rs.getString(13)+"\"></td>");
			out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(9)+"<input class='txt_input' type='hidden' name=TXT_DOC_STATUS_"+d+"_"+j+" value=\""+rs.getString(9)+"\"></td>"); 
			out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(11)+"<input class='txt_input' type='hidden' name=TXT_REQ_USER_"+d+"_"+j+" value=\""+rs.getString(11)+"\"></td>"); 
			out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(12)+"<input class='txt_input' type='hidden' name=TXT_REQ_DATE_"+d+"_"+j+" value=\""+rs.getString(12)+"\"><input class='txt_input' type='hidden' name=TXT_REASON_"+d+"_"+j+" value=\""+rs.getString(12)+"\"></td>"); 
			out.println("<td width='15%' style='{text-align:left;}'>"+rs.getString(14)+"</td>"); 
			out.println("<td width='15%' style='{text-align:left;}'>"+rs.getString(15)+"</td>"); 
			out.println("<td width='5%' style='{text-align:center;}'><input type='checkbox' name=chk_app_"+d+"_"+j+" value=\"N\" unchecked onClick=\"change("+d+","+j+")\"><input class='txt_input' type='hidden' name=TXT_INVOICE_1__"+d+"_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
			out.println("</tr>");
			more=rs.next();
			j=j+1;
			
			if (!more)
			{
			break;
			}
			}		
			out.println("</tr>");
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
			more1=rs1.next();

			d=d+1;	
	
		
			if (!more1)
			{
			break;
			}
			}

			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<input type=hidden name=hid_count_inv value="+d+">");
			out.println("</table>");
		

		rs4 = stmt4.executeQuery 
			("SELECT DISTINCT b.PRO_FORMA_INVOICE_NO,A.TOTAL_COUNT,A.IN_COUNT,REQ_NO,A.APPLICATION_NO "+
			"FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
      "WHERE A.FINANCE_NO=B.FINANCE_NO "+
			"AND B.DOCUMENT_CODE=CODE "+
			"AND DOC_APP_TYPE='CLIENT' "+
			"AND B.PRO_FORMA_INVOICE_NO  IS NULL "+
      "AND B.DOCUMENT_STATUS='RETURNED' "+//approved_out
      "AND A.FINANCE_NO='"+m_finance_no+"' ");
					
			boolean more4 = rs4.next();
	
			out.println("<tr></tr>");
			out.println("<tr></tr>");



		  int h=0;
			while(more4){
			if(h==0){
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");

			out.println("<tr>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Application No</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>:-  "+rs4.getString(5)+"</td>"); 
			out.println("<td width='70%'></td>");
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			out.println("<tr>");	
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Request No</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>:-  "+rs4.getString(4)+"</td>"); 
			out.println("<td width='70%'></td>");
			out.println("</tr>");
			out.println("</table>");	
			out.println("<br>");		
			}
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");

			out.println("<tr  ><td width='15%' style='{text-align:left;}'><b><u>Client Wise</td></tr>");

			if(h==0){


			out.println("<tr  class=\"pdn_txtpos2\"><td width='10%' style='{text-align:left;}'><b>Invoice No </td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Document Code</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Document Name</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Document Status</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Requested User</td>");
			out.println("<td width='10%' style='{text-align:left;}'><b>Requested Date</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Req-Approved User</td>");
			out.println("<td width='15%' style='{text-align:left;}'><b>Req-Approved Date</td>");
			out.println("<td width='5%' style='{text-align:center;}'><b>Select</td>");
			out.println("</tr>");
}
			out.println("<input class='txt_input' type='hidden' name=TXT_REQ_C value=\""+rs4.getString(4)+"\">");

		
			
			rs3 = stmt3.executeQuery ("SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,b.PRO_FORMA_INVOICE_NO, "+
        "A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,B.DOCUMENT_CODE, "+
        "B.DOCUMENT_STATUS,NVL(B.REASON,'-'),B.REQUESTED_USER,TO_CHAR(B.REQUESTED_DATE,'DD-MM-YYYY'),DESCRIPTION,APPRO_USER,TO_CHAR(APPRO_DATE,'DD-MM-YYYY'),b.req_no "+
        "FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
        "WHERE A.FINANCE_NO=B.FINANCE_NO "+
				"AND DOC_APP_TYPE='CLIENT' "+
				"AND b.PRO_FORMA_INVOICE_NO is null "+
				"AND REQ_NO='"+rs4.getString(4)+"'  "+
				"AND B.DOCUMENT_CODE=CODE "+
        "AND B.DOCUMENT_STATUS='RETURNED' "+
        "AND A.FINANCE_NO='"+m_finance_no+"' "); 
	
		boolean more3 = rs3.next();


		out.println("<br>");
		
		int a = 0; 
		while(more3){


		if(a>0 && a%2==1){
	  out.println("<tr class=tr_input1 >");
		}
		else{
		out.println("<tr class=tr_input >");
		}
		out.println("<td width='10%' style='{text-align:left;}'>-</td>");
		out.println("<td width='10%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs3.getString(8)+"')\"><U>"+rs3.getString(8)+"<input class='txt_input' type='hidden' name=TXT_DOC_CODE_C_"+a+" value=\""+rs3.getString(8)+"\"></td>");
		out.println("<td width='15%' style='{text-align:left;}'>"+rs3.getString(13)+"<input class='txt_input' type='hidden' name=TXT_DOC_NAME_C_"+a+" value=\""+rs3.getString(13)+"\"></td>");
		out.println("<td width='10%' style='{text-align:left;}'>"+rs3.getString(9)+"<input class='txt_input' type='hidden' name=TXT_DOC_STATUS_C_"+a+" value=\""+rs3.getString(9)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:left;}'>"+rs3.getString(11)+"<input class='txt_input' type='hidden' name=TXT_REQ_USER_C_"+a+" value=\""+rs3.getString(11)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:left;}'>"+rs3.getString(12)+"<input class='txt_input' type='hidden' name=TXT_REQ_DATE_C_"+a+" value=\""+rs3.getString(12)+"\"><input class='txt_input' type='hidden' name=TXT_REASON_C_"+a+" value=\""+rs3.getString(12)+"\"></td>"); 
		out.println("<td width='15%' style='{text-align:left;}'>"+rs3.getString(14)+"</td>"); 
		out.println("<td width='15%' style='{text-align:left;}'>"+rs3.getString(15)+"</td>"); 
		out.println("<td width='5%' style='{text-align:center;}'><input type='checkbox' name=chk_app_C_"+a+" value=\"N\" unchecked onClick=\"change_C("+a+")\"></td>"); 
		out.println("</tr>");
		more3=rs3.next();
		a=a+1;
		
		if (!more3)
		{
		break;
		}
		
		}		
		out.println("</tr>");
		out.println("<input type=hidden name=hid_count_client value="+a+">");
		more4=rs4.next();
		h=h+1;
		if (!more4)
		{
		break;
		}
		}
		
		
		out.println("</table>");
		out.println("<input type=hidden name=hid_count_client_1 value="+h+">");

		
		//.........................................................................................

	
	}
}
			
		
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
