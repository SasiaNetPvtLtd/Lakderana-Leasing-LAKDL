//--
//SCREEN NAME: Summary Information letter  
//CREATED BY : CHANDANA
//DATE/TIME  : 19/04/2007
//NOTES			 :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_summary_information_letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;

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

      m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			String m_fin_code,m_invoice;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
			String m_schema_name = m_sn_methods.schema_name;

		if(m_chksql.equals("main_page1")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> System Administration - Summary Letter </TITLE>"); 
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
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_no_val.value==\"0\"){");
			out.println("alert('No records')");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_st.value='T'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=request_details&m_val=\"+document.Form1.hid_status.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"\";");
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
			out.println("for(j=0;j<document.Form1.hid_count.value;j++){");
			out.println("m_chk=\"chk_app_\"+d+\"_\"+j;");
			out.println("m_chk_req=\"chk_not_req_\"+d+\"_\"+j;");
			out.println("if(document.Form1.elements[m_chk_req].checked==true || document.Form1.elements[m_chk].checked==true){");
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

			
			

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_bus_introducer?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
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
			out.println("help_box.innerHTML=\"System Administration - Thanking Letter  \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"System Administration - Thanking Letter  \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.hid_status.value=\"Request\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
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
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 			
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
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

			
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
			
			
			out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){");
			out.println(" document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("}else{ ");
			out.println(" document.Form1.TXT_EMPLOYEE.value=\"\";");
			out.println("} ");
			out.println("} ");
			out.println("");
			
			
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_NAME_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_NAME.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			
			

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_REQ_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    m_sql = \"m_help_TXT_EMPLOYEE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_EMPLOYEE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_2(oBj) {"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){");
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 	
			out.println("else if(document.Form1.SCREEN_NAME.value=='EDIT'){");
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			out.println(" 	makeRequest(); ");
			out.println("}");



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
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.hid_add1.value=oBj.valout[5];");
			out.println("    document.Form1.hid_add2.value=oBj.valout[6];");
			out.println("    document.Form1.hid_add3.value=oBj.valout[7];");
			out.println("}"); 
			
			out.println("function help_update_value_assign_100() {"); 
			out.println("    document.Form1.TXT_EMPLOYEE.value=oBj.valout[2];");
			out.println("    document.Form1.hid_disignation.value=oBj.valout[4];");
			out.println("}"); 
			
			
			out.println("function Generate_Letter() {");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_bus_introducer?chksql=Letter&client_name=\"+document.Form1.TXT_CLIENT_NAME.value+\"&autho_name=\"+document.Form1.TXT_EMPLOYEE.value+\"&add1=\"+document.Form1.hid_add1.value+\"&add2=\"+document.Form1.hid_add2.value+\"&add3=\"+document.Form1.hid_add3.value+\"&design=\"+document.Form1.hid_disignation.value+\"&print=TRUE\";"); 
			out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
		
			
			
			
			out.println("function change(row1,row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==true && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==false && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("}"); 
			
			out.println("function change_not(row1,row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==true && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==false && document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");			
			out.println("}"); 
			
			out.println("function change_not_C(row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_C_\"+row].checked==true && document.Form1.elements[\"chk_not_req_C_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_app_C_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_C_\"+row].checked==false && document.Form1.elements[\"chk_not_req_C_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");			
			out.println("}"); 
	
			out.println("function change_C(row) {");
			out.println("b_flag=0;");
			out.println("   if(document.Form1.elements[\"chk_app_C_\"+row].checked==true && document.Form1.elements[\"chk_not_req_C_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='Y'");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].checked=false");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='N'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_C_\"+row].checked==false && document.Form1.elements[\"chk_not_req_C_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='Y'");
			out.println("b_flag=1;");
			out.println("}");	
			out.println("}"); 

			
			out.println("function check_1(row1,row) {");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row1+\"_\"+row].value='N'");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].checked=true");
			out.println("document.Form1.elements[\"chk_not_req_\"+row1+\"_\"+row].value='Y'");
			out.println("}");	
			out.println("}"); 
			
			
					out.println("function check_C(row) {");
			out.println("   if(document.Form1.elements[\"chk_app_C_\"+row].checked==false){"); 
			out.println("document.Form1.elements[\"chk_app_C_\"+row].value='N'");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].checked=true");
			out.println("document.Form1.elements[\"chk_not_req_C_\"+row].value='Y'");
			out.println("}");	
			out.println("}"); 
			
			out.println("function clear_screen(){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("request_details.innerHTML = ''; ");
			out.println("}"); 

//______________________________________________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value(' ')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_add1' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_add2' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_add3' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_disignation' VALUE=\"\">");
			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> System Administration - Thanking Letter </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_CIENT_NAME' class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' value=\"\" type='text' name='TXT_CLIENT_NAME' maxlength='15' size='15' onblur=\"\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_EMPLOYEE' class=div_input>Authorize Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' value=\"\" type='text' name='TXT_EMPLOYEE' maxlength='15' size='15' onblur=\"\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_EMPLOYEE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onClick='Generate_Letter()' value=\"Letter\"></td>");
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	



else if(m_chksql.equals("Letter")){
			int i=0;
			String m_finance_no = req.getParameter("finance_no");
			String m_print      = req.getParameter("print");
			
			String m_com_add1   = "";
			String m_com_add2   = "";
			String m_com_add3   = "";
			String m_com_tele   = "";
			String m_com_fax    = "";
			String m_com_email  = "";
			
			String m_fin_num    ="";
			String m_app_num    ="";
			String m_client_name= "";	
			String m_client_add1= "";	
			String m_client_add2= "";
			String m_client_add3= "";
			String m_design ="";
			String m_autho_name = "";
			
			

String m_Letter_date1="";
String m_Letter_date2="";


	rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY'), TO_CHAR(SYSDATE,'fmddth') ||'  '||TO_CHAR(SYSDATE,'MONTH')||''||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
								
		boolean more = rs.next();
		if(more){
		m_Letter_date1=rs.getString(1);
		m_Letter_date2=rs.getString(2);
		}
				
			
		
		
   rs = stmt.executeQuery (" SELECT COMPANY_NAME, "+
		                       " ADDRESS1, "+
													 " ADDRESS2, "+
													 " CITY, "+
													 " TEL_NO, "+
													 " FAX_NO, "+													 
													 " EMAIL "+
													 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
		
				
					
		 more = rs.next();
		if(more){
		m_com_add1   = rs.getString(2);
		m_com_add2   = rs.getString(3);
		m_com_add3   = rs.getString(4);
		m_com_tele   = rs.getString(5);
		m_com_fax    = rs.getString(6);
		m_com_email  = rs.getString(7);
		}

					
			
			
		rs = stmt.executeQuery ("	SELECT A.CLIENT_CODE, "+
		                        " A.FINANCE_NO, "+
														" B.TITLE ||' '||B.FULL_NAME, "+
														" NVL(B.ADDRESS1,'-'), "+
														" NVL(B.ADDRESS2,'-'), "+
														" NVL(B.CITY_CODE,'-'), "+
														" A.APPLICATION_NO "+
														" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_NO='"+m_finance_no+"' ");
			
			
			
			 more = rs.next();
		if(more){
		m_fin_num       = rs.getString(2);  
		m_client_name   = rs.getString(3);
		m_client_add1   = rs.getString(4);
		m_client_add2   = rs.getString(5);
		m_client_add3   = rs.getString(6);
		m_app_num       = rs.getString(7);		 
		}

				
				
		
			
				
			  out.println("<html><head>"); 
				out.println("<title>Thanking Letter</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      out.println("<script>");
			
			out.println("function save_data(){");			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_bus_introducer?chksql=Letter&client_name="+m_client_name+"&autho_name="+m_autho_name+"&add1=&add2=&add3=&design="+m_design+"&print=FALSE\";");  
		  out.println(" window.location.href=m_url;");		
			out.println("m_table.innerHTML=\"\" ");		
			out.println("window.print();");		
			out.println("}");
			
		
		  out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			out.println("}");
			
			
			out.println("</script>");
			
				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
      
			out.println("<hr>");		
			
			out.println("<blockquote><font size=3><p style='text-align:center'>");	
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body' align='left' ></td>");
			out.println("</tr>");
		  out.println("</table>");
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body' align='center' >"+m_com_add1+", "+m_com_add2+", "+m_com_add3+", Srilanka. &nbsp Tel: "+m_com_tele+" &nbsp Fax: "+m_com_fax+" &nbsp E-mail: "+m_com_email+"</td>");
			out.println("</tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
			//out.println("m_client_add1"+m_client_add1+"m_client_add2"+m_client_add2+"m_client_add3"+m_client_add3);
			
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			

			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 
			out.println("<tr><td width='*%' class='rep-body' ><B>"+m_Letter_date1+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' ><B>"+m_client_name+"</B></td></tr>");
		
		
			if(!m_client_add1.equals("-")){
			out.println("<tr><td width='*%' class='rep-body' ><B>"+m_client_add1+"</B></td></tr>");
			}
			if(!m_client_add2.equals("-")){
			out.println("<tr><td width='*%' class='rep-body' ><B>"+m_client_add2+"</B></td></tr>");
			}
			if(!m_client_add3.equals("-")){
			out.println("<tr><td width='*%' class='rep-body' ><B>"+m_client_add3+"</B></td></tr>");
			}  
			out.println("</table>");	
			
			out.println("<br><br>");
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><B>Dear Sir,</B></td></tr>");
			out.println("</table>");
			out.println("<br><br>");
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body' >Master Lease Agreement No</td><td width='25%' class='rep-body' ><B>:&nbsp"+m_app_num+"</B></td><td width='10%' class='rep-body' ></td><td width='25%' class='rep-body' ><B></B></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' >Shedule No</td><td width='25%' class='rep-body' ><B>:&nbsp"+m_fin_num+"</B></td><td width='10%' class='rep-body' ><B>Date :</B></td><td width='25%' class='rep-body' ><B>"+m_Letter_date2+"</B></td></tr>");
			out.println("</table>");
			out.println("<HR>");
			out.println("<br>");
			out.println("</font></p></blockquote>");
			
		
	
		 out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
		
		 								
		 String data="For your convenience, a summary of information relating to above mentioned Lease Agreement is given hereunder. ";  						
								
								
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
						
      out.println("<br>");            
     
			data = "LEASE PAYMENT PLAN ";
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><B>"+data+"</B></td>");
			out.println("</tr></table>");				
						
			out.println("<br>");            
     
			String m_start_date="";
			String m_rental_start_date="";
			double m_gross_rental=0.00;
			int    m_period =0;
			double m_security_margin_val=0.00;
			double m_residual_value  =0.00;
			 
				
				rs = stmt.executeQuery (" SELECT "+
				" DISTINCT TO_CHAR(A.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(A.ACTIVATED_DATE, 'Month')|| TO_CHAR(A.ACTIVATED_DATE, 'YYYY') START_DATE, "+
			  " B.PERIOD, "+
			  " TO_CHAR(C.RENTAL_DATE, 'fmddth') rental_date, "+
			  " C.GRENTAL_AMOUNT, "+
			  " B.NIBSM,B.RESIDUAL_VALUE "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B , "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT C "+
        " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			  " A.APPLICATION_NO=C.APPLICATION_NO AND "+
			  " C.INSTALLMENT_NO=0 AND "+
        " A.APPLICATION_NO=UPPER('"+m_app_num+"') ");      //AND B.PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
			
			more=rs.next();
			if(more){
			m_start_date=rs.getString(1);
			m_period=rs.getInt(2);
      m_rental_start_date=rs.getString(3);
			m_gross_rental=rs.getDouble(4);
		  m_security_margin_val=rs.getDouble(5);
			m_residual_value=rs.getDouble(6);
				
			}
			
			
			
			
			String date_of_commens = "";
			String date_of_lastrent = "";
			
			
				
			rs = stmt.executeQuery (" SELECT TO_CHAR(MAX(RENTAL_DATE),'fmddth')||' ' ||INITCAP(TO_CHAR(MAX(RENTAL_DATE),'MONTH'))||'  ' || ' '||TO_CHAR(MAX(RENTAL_DATE),'YYYY') DATE_OF_LAST_PAYMENT, "+
			                        " TO_CHAR(MIN(RENTAL_DATE),'fmddth')||' ' ||INITCAP(TO_CHAR(MIN(RENTAL_DATE),'MONTH'))||'  ' ||' '||TO_CHAR(MIN(RENTAL_DATE),'YYYY') DATE_OF_COMMENSMENT "+
															" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
															" WHERE APPLICATION_NO ='"+m_app_num+"' ");
 			
			more=rs.next();
			if(more){
      date_of_lastrent  = rs.getString(1);
      date_of_commens   = rs.getString(2); 
			}
				
				
				
			
			double m_od_interest_rate =0.00;
				
				rs=stmt.executeQuery (" SELECT "+ 	 
                        " RATE "+
                        " FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE "+
                        " WHERE ACTIVE_STATUS='Y' ");
												
												more=rs.next();
												if(more){
												 m_od_interest_rate=rs.getDouble(1);
												}
				
			
			
			double m_Doc_Charge=0.00;
			
			
			rs=stmt.executeQuery (" SELECT APPLICATION_NO,SUM(AMOUNT) "+
			                      " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES "+
														" WHERE APPLICATION_NO ='"+m_app_num+"' "+
														" GROUP BY APPLICATION_NO ");
			
			
			        more=rs.next();
												if(more){
												 m_Doc_Charge=rs.getDouble(2);
												}
			
			
			
				
				
			
			
			out.println("<table border='0' width='90%' class='table'>"); 	
			
					
			
			
			
			rs = stmt.executeQuery (" SELECT GRENTAL_AMOUNT,COUNT(NO), "+
			                        " TO_CHAR(MIN(RENTAL_DATE),'MON')||'  '||TO_CHAR(MIN(RENTAL_DATE),'YYYY'), "+
															" TO_CHAR(MAX(RENTAL_DATE),'MON')||'  '||TO_CHAR(MAX(RENTAL_DATE),'YYYY') "+
															" FROM "+
															" (SELECT ROWNUM NO, GRENTAL_AMOUNT,RENTAL_DATE "+
															" FROM  LAKDL.AF_CO_PRO_APP_INSTALLMENT "+
															" WHERE APPLICATION_NO ='"+m_app_num+"' "+ //AP20070109-0268
															" ORDER BY RENTAL_DATE,NO ) "+
															" GROUP BY GRENTAL_AMOUNT ");	
						more = rs.next();
				
				int k=0;
				
				while(more){
				
				if(k==0){
				out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' >1.&nbsp Monthly Rental Inclusive of VAT</td> ");
				if(rs.getInt(2)==1){
				out.println("<td width='45%' class='rep-body' style='text-align:justify' >: &nbsp Rs. "+nf.format(rs.getDouble(1))+"/-("+rs.getInt(2)+" Month, "+rs.getString(3)+"  -  "+rs.getString(4)+")</td></tr>");
				}else if(rs.getInt(2)>1){
				out.println("<td width='45%' class='rep-body' style='text-align:justify' >: &nbsp Rs. "+nf.format(rs.getDouble(1))+"/-("+rs.getInt(2)+" Months, "+rs.getString(3)+"  -  "+rs.getString(4)+")</td></tr>");
				}	
								
				}else{
				out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' ></td> ");
				if(rs.getInt(2)==1){
				out.println("<td width='45%' class='rep-body' style='text-align:justify' >: &nbsp Rs. "+nf.format(rs.getDouble(1))+"/-("+rs.getInt(2)+" Month, "+rs.getString(3)+"  -  "+rs.getString(4)+")</td></tr>");
				}else if(rs.getInt(2)>1){
				out.println("<td width='45%' class='rep-body' style='text-align:justify' >: &nbsp Rs. "+nf.format(rs.getDouble(1))+"/-("+rs.getInt(2)+" Months, "+rs.getString(3)+"  -  "+rs.getString(4)+")</td></tr>");
				}
				}
				more = rs.next();
				k=k+1;
				}											
			
			
			out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' >2.&nbsp Documentation Chargers</td><td width='45%' class='rep-body' style='text-align:justify' >: &nbsp Rs."+nf.format(m_Doc_Charge)+"/- </td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' >3.&nbsp Lease Period </td><td width='45%' class='rep-body' style='text-align:justify' >: &nbsp "+m_period+" Months</td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' >4.&nbsp Rental Due Date</td><td width='45%' class='rep-body' style='text-align:justify' >: &nbsp "+m_rental_start_date+" of each Month </td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' >5.&nbsp Date of commencement</td><td width='45%' class='rep-body' style='text-align:justify' >: &nbsp "+date_of_commens+"</td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' >6.&nbsp Date of Last Payment</td><td width='45%' class='rep-body' style='text-align:justify' >: &nbsp "+date_of_lastrent+"</td></tr>");
			out.println("<tr><td width='45%' class='rep-body' style='text-align:justify' >7.&nbsp Overdue Interest Rate</td><td width='45%' class='rep-body' style='text-align:justify' >: &nbsp "+nf.format(m_od_interest_rate)+" % Per Month</td></tr>");
			out.println("</table>");	
			
			
			
			
		///////////////////////////////////////////////////////////////////////////////		
	
		
                 data="The started monthly rental excludes insurance, which essentially needs to be placed through Insurance Division of LAKDERANA INVESTMENTS LIMITED with any insurer of "+
									    "your choice during the tenure of lease. ";
	out.println("<br>");																	
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			
			 data="We look forward to a mutually rewarding business relationship. ";
	out.println("<br>");
	out.println("<br>");
	
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td></tr>");
			out.println("</tr></table>");
				
	out.println("<br>");
	out.println("<br>");	
			
			out.println("<table border='0' width='90%' class='table'>");  		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >Yours faithfully</td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><B>LAKDERANA INVESTMENTS LIMITED</B></td>");
			out.println("</tr></table>");	

out.println("<br><br><br><br>");

      out.println("<table border='0' width='90%' class='table'>");  		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><B>Authorized Signatory</B></td>");
			out.println("</tr>");	
			out.println("</table>");	
    
 out.println("<br><br>");
	
	    data = " When every payment/s are made by cheque/s or cash please ensure that you receive a official receipt/s. If you are issuing "+
			       "Third partie cheque/s, please ensure that those cheque/s are endorsed by you. ";
	    
			out.println("<table border='0' width='90%' class='table'>");  		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><B><U>IMPORTANT</U></B></td>");
			out.println("</tr>");	
			out.println("</table>");
			
	out.println("<br>");   
			
	    out.println("<table border='0' width='90%' class='table'>");  		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><B>"+data+"</B></td>");
			out.println("</tr>");	
			out.println("</table>");
	   
	
	
	
out.println("<br><br><br><br>");    		
		out.println("</font></p></blockquote>");		
		
				
		
			
			
				
		
												
		  out.println("</form></body></html>");












}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			else if(m_chksql.equals("request_details")){
			
			
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
