
//--
//SCREEN NAME:SECURITY AND MARKETING FILE
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_PRO_display_security_and_marketing_approval_copy extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;

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
      m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			String m_fin_code,m_invoice;
			
			String m_schema_name = m_sn_methods.schema_name;
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			//int m_cnt=Integer.parseInt(req.getParameter("m_cnt"));
			//String m_cnt1="0";
			//m_cnt1= req.getParameter("m_cnt");
			//out.println(m_cnt1);
		 if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Security and Marketing file</TITLE>"); 
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
			out.println("if(document.Form1.hid_count.value==\"0\"){");
			out.println("new_window()");
			//out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			//out.println(" request_details.innerHTML = ''; ");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_st.value='T'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_approval?chksql=request_details&m_val=\"+document.Form1.hid_status.value+\"&finance_no=\";");
			out.println("window.open(m_url)");
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
			
			
						
			out.println("function ckeck_select(){ ");
			
			out.println("if(!count_docs()){"); 
			out.println("alert('Please Select Document');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
      out.println("}"); 


			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("ckeck_select();");
			out.println("if(b_flag==0){");
			
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_save_security_and_marketing_file?m_count='+m_cnt+'';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_PRO_display_security_and_marketing_file\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Security and Marketing file - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Security and Marketing file - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
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
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"Request\";"); 
			out.println("document.Form1.hid_save.value=\"Request\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
			out.println("document.Form1.hid_save.value=\"Return\";"); 
			out.println("document.Form1.hid_status.value=\"Return\";");  
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
			out.println(" document.Form1.TXT_FINANCE_NO.value = \"\" ");
			out.println(" request_details.innerHTML = ''; ");
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
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'FinanceSql','2');");
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
			
			out.println("function change(row) {"); 
			out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==false){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"N\";"); 
			out.println("m_cnt=m_cnt-1");
			out.println("m_chk=0");
			out.println("}");
			out.println("else if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"Y\";"); 
			out.println("m_cnt=m_cnt+1");
			out.println("m_chk=1");
			out.println("}");
			out.println("}"); 
			
			out.println("function clear_screen(){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("request_details.innerHTML = ''; ");
			out.println("}"); 
			
			
//________________________________________________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),makeRequest()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Request\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Security and Marketing file</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Request\");' onclick='load_screen_status(\"NEW\")' value=\"Request\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Return\");' onClick='load_screen_status(\"EDIT\"),clear_screen()' value=\"Return\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
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

			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO' class=div_input><b>Finance No*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' value=\"AP20061003-0029\" type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"makeRequest()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); */
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
			

rs1 = stmt1.executeQuery ("SELECT DISTINCT A.PRO_FORMA_INVOICE_NO,A.TOTAL_COUNT,A.IN_COUNT,REQ_NO,A.APPLICATION_NO,A.FINANCE_NO "+
			"FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
      "WHERE A.FINANCE_NO=B.FINANCE_NO "+
			"AND B.DOCUMENT_CODE=CODE "+
      "AND A.PRO_FORMA_INVOICE_NO=B.PRO_FORMA_INVOICE_NO "+
			"AND B.DOCUMENT_STATUS='Requested' ");
				
				
				boolean more1 = rs1.next();
				
		
				
		out.println("<br>");			
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		out.println("<br>");			
    
	int d = 0; 
			while(more1)
		{	
			rs = stmt.executeQuery ("SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,A.PRO_FORMA_INVOICE_NO, "+
        "A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,B.DOCUMENT_CODE, "+
        "B.DOCUMENT_STATUS,NVL(B.REASON,'-'),B.REQUESTED_USER,TO_CHAR(B.REQUESTED_DATE,'DD-MM-YYYY'),DESCRIPTION "+
        "FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
        "WHERE A.FINANCE_NO=B.FINANCE_NO "+
				"AND A.FINANCE_NO='"+rs1.getString(6)+"' "+
				"AND B.DOCUMENT_CODE=CODE "+
        "AND A.PRO_FORMA_INVOICE_NO=B.PRO_FORMA_INVOICE_NO "+
        "AND B.DOCUMENT_STATUS='Requested' ");
				
				
				boolean more = rs.next();
				
				
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	  out.println("<tr>");
		out.println("<td width='20%' style='{text-align:left;}'><b>Finance No</td>");
		out.println("<td width='20%' style='{text-align:left;}'><b>:-  "+rs1.getString(6)+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO value=\""+rs1.getString(6)+"\"></td>"); 
		out.println("<td width='60%'></td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width='20%' style='{text-align:left;}'><b>Application No</td>");
		out.println("<td width='20%' style='{text-align:left;}'><b>:-  "+rs1.getString(5)+"<input class='txt_input' type='hidden' name=TXT_APPLICATION_NO value=\""+rs1.getString(5)+"\"></td>"); 
		out.println("<td width='60%'></td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width='20%' style='{text-align:left;}'><b>Request No</td>");
		out.println("<td width='20%' style='{text-align:left;}'><b>:-  "+rs1.getString(4)+"<input class='txt_input' type='hidden' name=TXT_REQ value=\""+rs1.getString(4)+"\"></td>"); 
		out.println("<td width='60%'></td>");
		out.println("</tr>");	
		
		out.println("<tr ><td width='15%' style='{text-align:left;}'><b>Invoice No </td>");
		out.println("<td width='15%'><b>:-  "+rs1.getString(1)+"<input class='txt_input' type='hidden' name=TXT_INVOICE_"+d+" value=\""+rs1.getString(1)+"\"></td>");
		
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td width='15%' style='{text-align:left;}'><b>Total Count</td>");
		out.println("<td width='10%' style='{text-align:left;}'><b>:-  "+rs1.getString(2)+"<input class='txt_input' type='text' name=TXT_TOT_COUNT_"+d+" value=\"TXT_TOT_COUNT_"+d+"\"></td>"); 
		out.println("</tr>");
		//t.println("</tr>");
		
	 //ut.println("</tr>");
		out.println("<tr>");
		out.println("<td width='10%' style='{text-align:left;}'><b>In Count</td>");
		out.println("<td width='10%' style='{text-align:left;}'><b>:-  "+rs1.getString(3)+"<input class='txt_input' type='hidden' name=TXT_IN_COUNT_"+d+" value=\""+rs1.getString(3)+"\"></td>"); 
		out.println("<td width='25%'></td>");
		out.println("</tr>");
	
		out.println("</table>");		
		out.println("<br>");	
	
		out.println("<br>");	
		

		int j = 0; 
		
	
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		out.println("<tr  class=\"pdn_txtpos2\"><td width='20%' style='{text-align:left;}'><b>Document Code</td>");
		out.println("<td width='20%' style='{text-align:left;}'><b>Document Name</td>");
		out.println("<td width='20%' style='{text-align:left;}'><b>Document Status</td>");
		//out.println("<td width='15%' style='{text-align:left;}'><b>Total Count</td>");
		//out.println("<td width='10%' style='{text-align:left;}'><b>In Count</td>");
		out.println("<td width='10%' style='{text-align:left;}'><b>Requested User</td>");
		out.println("<td width='15%' style='{text-align:left;}'><b>Requested Date</td>");
		out.println("<td width='15%' style='{text-align:center;}'><b>Select</td>");
		out.println("</tr>");

		//out.println("<br>");
		
			more1=rs1.next();
			
			
			
		while(more){
		out.println("<tr class=\"tr_input\">");
		out.println("<td width='20%' style='{text-align:left;}'>"+rs.getString(8)+"<input class='txt_input' type='text' name=TXT_DOC_CODE_"+d+"_"+j+" value=\"TXT_DOC_CODE_"+d+"_"+j+"\"></td>");
		out.println("<td width='20%' style='{text-align:left;}'>"+rs.getString(13)+"<input class='txt_input' type='hidden' name=TXT_DOC_NAME_"+d+"_"+j+" value=\""+rs.getString(13)+"\"></td>");
		out.println("<td width='20%' style='{text-align:left;}'>"+rs.getString(9)+"<input class='txt_input' type='hidden' name=TXT_DOC_STATUS_"+d+"_"+j+" value=\""+rs.getString(9)+"\"></td>"); 
		//out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(5)+"<input class='txt_input' type='hidden' name=TXT_TOT_COUNT_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
		//out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(6)+"<input class='txt_input' type='hidden' name=TXT_IN_COUNT_"+j+" value=\""+rs.getString(6)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(11)+"<input class='txt_input' type='hidden' name=TXT_REQ_USER_"+d+"_"+j+" value=\""+rs.getString(11)+"\"></td>"); 
		out.println("<td width='15%' style='{text-align:left;}'>"+rs.getString(12)+"<input class='txt_input' type='hidden' name=TXT_REQ_DATE_"+d+"_"+j+" value=\""+rs.getString(12)+"\"><input class='txt_input' type='hidden' name=TXT_REASON_"+d+"_"+j+" value=\""+rs.getString(10)+"\"></td>"); 

		
		//out.println("<td width='20%' style='{text-align:left;}'><input class='txt_input' style='{width:200;}' type='text' name=TXT_REASON_"+j+" maxlength='200'  value=\"\"></td>"); 
		out.println("<td width='15%' style='{text-align:center;}'><input type='checkbox' name=chk_app_"+d+"_"+j+" value=\"N\" unchecked onClick=\"change("+d+","+j+")\"><input class='txt_input' type='hidden' name=TXT_INVOICE_1_"+j+" value=\""+rs.getString(5)+"\"></td>"); 

		out.println("</tr>");
		more=rs.next();
		j=j+1;
	

		if (!more)
		{
		break;
		}
			
			
		}
		out.println("<input type=hidden name=hid_count value="+j+">");	
		d=d+1;			

		out.println("</table>");
			out.println("<HR>");
		out.println("<br>");
		out.println("<br>");
		if (!more1)
		{
		break;
		}
		

		//}
		//	out.println("<input type=text name=hid_count value="+j+">");	
	
	//}			
		out.println("<input type=hidden name=hid_count_inv value="+d+">");	
				
		out.println("</table>");	
	
	
	}
//	more2=rs2.next();
	
	
	//}
	//	}
		
		/*out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		out.println("<tr><td>");
		out.println("<td ><input type=hidden name=hid_no_val value=\""+rs2.getInt(1)+"\"></td>");
		out.println("</tr>");
		out.println("</table>");
*/
		
//	}
	
	
		
		//}

			
			
			
			
			
		
		/*
		
				rs = stmt.executeQuery ("SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,A.PRO_FORMA_INVOICE_NO, "+
        "A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,B.DOCUMENT_CODE,APPRO_USER,TO_CHAR(APPRO_DATE,'DD-MM-YYYY'), "+
        "B.DOCUMENT_STATUS,B.REASON,B.REQUESTED_USER,TO_CHAR(B.REQUESTED_DATE,'DD-MM-YYYY'),DESCRIPTION "+
        "FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
        "WHERE A.FINANCE_NO=B.FINANCE_NO "+
				"AND B.DOCUMENT_CODE=CODE "+
        "AND A.PRO_FORMA_INVOICE_NO=B.PRO_FORMA_INVOICE_NO "+
        "AND B.DOCUMENT_STATUS='Requested' ");
      //  "AND A.FINANCE_NO='"+m_finance_no+"' "); 
				
				
				boolean more = rs.next();

					
		out.println("<br>");			
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		//out.println("<br>");			
    int j = 0; 
	
		
		while(more)		{
		
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		out.println("<tr ><td width='15%' style='{text-align:left;}'><b>Finance No </td>");
		out.println("<td width='15%' ><b>:-  "+rs.getString(1)+"<input type='hidden' name=TXT_FINANCE_NO value=\""+rs.getString(1)+"\"></td>");
		out.println("<td width='70%'></td>");
	  out.println("</tr>");
		out.println("<tr>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("</tr>");

		out.println("</table>");
		
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		out.println("<tr ><td width='15%' style='{text-align:left;}'><b>Application No </td>");
		out.println("<td width='15%' ><b>:-  "+rs.getString(2)+"<input type='hidden' name=TXT_APPLICATION_"+j+" value=\""+rs.getString(2)+"\"></td>");
		out.println("<td width='70%'></td>");
	  out.println("</tr>");
		out.println("<tr>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("</tr>");

		out.println("</table>");
		
		out.println("<br>");
		
		
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		out.println("<tr  class=\"pdn_txtpos2\"><td width='12%' style='{text-align:left;}'><b>Invoice No </td>");
		out.println("<td width='10%' style='{text-align:left;}'><b>Document Code</td>");
		out.println("<td width='21%' style='{text-align:left;}'><b>Document Name</td>");
		out.println("<td width='12%' style='{text-align:left;}'><b>Document Status</td>");
		out.println("<td width='5%' style='{text-align:left;}'><b>Total Count</td>");
		out.println("<td width='5%' style='{text-align:left;}'><b>In Count</td>");
		out.println("<td width='10%' style='{text-align:left;}'><b>Requested User</td>");
		out.println("<td width='10%' style='{text-align:left;}'><b>Requested Date</td>");
		out.println("<td width='5%' style='{text-align:center;}'><b></td>");
		out.println("</tr>");

		out.println("</table>");
		
				while(more)
		{
			//int d = 0; 
		m_fin_code=rs.getString(1);
		//int m_cnt=0;
		
		//out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	//	out.println("<tr ><td width='15%' style='{text-align:left;}'><b>Invoice No </td>");
		//out.println("<td width='15%'><b>:-  "+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_INVOICE_"+j+" value=\""+rs.getString(3)+"\"></td>");
		//out.println("<td width='70%'></td>");
		//out.println("</tr>");
		//out.println("<tr>");
		//out.println("</tr>");
		//out.println("<tr>");
		//out.println("</tr>");
		//out.println("</table>");
		//out.println("<br>");
		


		while(rs.getString(1).trim().equals(m_fin_code)){
		out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");

		out.println("<tr class=\"tr_input\">");
		out.println("<td width='12%'>"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_INVOICE_"+j+" value=\""+rs.getString(3)+"\"></td>");
		out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(8)+"<input class='txt_input' type='hidden' name=TXT_DOC_CODE_"+j+" value=\""+rs.getString(8)+"\"></td>");
		out.println("<td width='21%' style='{text-align:left;}'>"+rs.getString(15)+"<input class='txt_input' type='hidden' name=TXT_DOC_NAME_"+j+" value=\""+rs.getString(15)+"\"></td>");
		out.println("<td width='12%' style='{text-align:left;}'>"+rs.getString(11)+"<input class='txt_input' type='hidden' name=TXT_DOC_STATUS_"+j+" value=\""+rs.getString(11)+"\"></td>"); 
		out.println("<td width='5%' style='{text-align:left;}'>"+rs.getString(5)+"<input class='txt_input' type='hidden' name=TXT_TOT_COUNT_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
		out.println("<td width='5%' style='{text-align:left;}'>"+rs.getString(6)+"<input class='txt_input' type='hidden' name=TXT_IN_COUNT_"+j+" value=\""+rs.getString(6)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(13)+"<input class='txt_input' type='hidden' name=TXT_REQ_USER_"+j+" value=\""+rs.getString(13)+"\"></td>"); 
		out.println("<td width='10%' style='{text-align:left;}'>"+rs.getString(14)+"<input class='txt_input' type='hidden' name=TXT_REQ_DATE_"+j+" value=\""+rs.getString(14)+"\"></td>"); 

		
		//out.println("<td width='20%' style='{text-align:left;}'><input class='txt_input' style='{width:200;}' type='text' name=TXT_REASON_"+j+" maxlength='200'  value=\"\"></td>"); 
		out.println("<td width='5%' style='{text-align:center;}'><input type='checkbox' name=chk_app_"+j+" value=\"N\" unchecked onClick=\"change("+j+")\"><input class='txt_input' type='hidden' name=TXT_INVOICE_1_"+j+" value=\""+rs.getString(5)+"\"></td>"); 

		out.println("</tr>");
		more=rs.next();
		j=j+1;
	

		if (!more)
		{
		break;
		}
		out.println("</table>");
		}
		//d=d+1;			

		
		out.println("<br>");
		out.println("<br>");
		if (more)
		{
		m_fin_code=rs.getString(1);
		}
		}
		
	}			
		out.println("<input type=hidden name=hid_count value="+j+">");	
					
		out.println("</table>");	
	

	//}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@@@@@@@@@@@@@@@@@@@
	/*
	rs1 = stmt1.executeQuery ("SELECT COUNT(DISTINCT DOCUMENT_TYPE) "+
					" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
					" AND DOCUMENT_TYPE=CODE "+
					" AND PRO_INVOICE_NO =INVOICE_NO "+
					" AND B.APPLICATION_STATUS='ACTIVATED' "+
					" AND D.APPLICATION_NO=B.APPLICATION_NO "+
					" AND  FINANCE_NO='"+m_finance_no+"' "+
					" GROUP BY INVOICE_NO ");
	
		boolean more1 = rs1.next();
while(more1)
			{	
			out.println("<table align='center' width='100%' class='table' border=\"0\"><tr>"); 
			out.println("<tr >"); 
			out.println("<td width='10%' ><input type=hidden name=hid_count_doc_"+i+" value="+rs1.getString(1)+"></td>"); 
			out.println("</tr >"); 
			out.println("</table>");
			more1=rs1.next();
			i=i+1;
			
			if (!more1)
			{
			break;
			}
			}
	
*/
	//@@@@@@@@@@@@@@@
	
	
	
	
	
		
	//	}
			
		
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
