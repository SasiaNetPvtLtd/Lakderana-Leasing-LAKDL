import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_All_Termination_Letter extends javax.servlet.http.HttpServlet { 

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
			String m_fin_code,m_invoice,m_orient_name="";
			
			String m_schema_name = m_sn_methods.schema_name;

			rs = stmt.executeQuery(	" SELECT "+
											" UPPER(NVL(COMPANY_NAME,' ')) "+
											" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			
			boolean more_3 = rs.next();		
			if(more_3) {
			 m_orient_name=rs.getString(1);
			}
			

			if(m_chksql.equals("main_page")){
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Collection - Deletion Letter-C M T </TITLE>"); 
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
				out.println("if(document.Form1.TXT_TERMINATION_NO.value==\"\"){  "); 
				out.println("DIV_TXT_TERMINATION_NO.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_EMPLOYEE.value==\"\"){  "); 
				out.println("DIV_TXT_EMPLOYEE.style.color='red';");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Deletion_Letter?chksql=main_page';"); 
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
				out.println("help_box.innerHTML=\"Collection - Deletion Letter-C M T \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
	
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"Collection - Deletion Letter-C M T \"+document.Form1.hid_status.value;"); 
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
				out.println(" document.Form1.TXT_TERMINATION_NO.value=\"\";");
				out.println("}else{ ");
				out.println(" document.Form1.TXT_EMPLOYEE.value=\"\";");
				out.println("} ");
				out.println("} ");
				out.println("");
				
				
				
				out.println("function help_button_2() {"); 
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_TXT_TERMINATION_NO_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_TERMINATION_NO.value+\"@\"+\"Y@\";"); 
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
				out.println("    document.Form1.TXT_TERMINATION_NO.value=oBj.valout[2];"); 
				out.println("    document.Form1.hid_vihicle_no.value=oBj.valout[3];"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_100() {"); 
				out.println("    document.Form1.TXT_EMPLOYEE.value=oBj.valout[2];");
				out.println("    document.Form1.hid_disignation.value=oBj.valout[4];");
				out.println("}"); 
				
				
				out.println("function Generate_Letter() {");
				out.println("if(validate_data()) {"); //added by nuwan de silva 17-07-07
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_Deletion_Letter?chksql=Letter&termi_no=\"+document.Form1.TXT_TERMINATION_NO.value+\"&autho_name=\"+document.Form1.TXT_EMPLOYEE.value+\"&vihicle_no=\"+document.Form1.hid_vihicle_no.value+\"&design=\"+document.Form1.hid_disignation.value+\"&print=TRUE\";"); 
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=600,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}");
				out.println("else {");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("}");
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
	
	//___________________________________________________________________________________________________________________________  
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value(' ')\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_vihicle_no' VALUE=\"\">");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection - Deletion Letter-C M T </td>"); 
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
				out.println("<td width='30%' ><DIV id='DIV_TXT_TERMINATION_NO' class=div_input>Termination No *</DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' value=\"\" type='text' name='TXT_TERMINATION_NO' maxlength='15' size='15' onblur=\"\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_TERMINATION_NO' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='30%' ><DIV id='DIV_TXT_EMPLOYEE' class=div_input>Authorize Name *</DIV></td>"); 
				out.println("<td width='60%' ><input class='txt_input' value=\"\" type='text' name='TXT_EMPLOYEE' maxlength='15' size='15' onblur=\"\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_EMPLOYEE' value=\"Help\" onClick=\"help_button_3()\">"); 
				//out.println("<td width='12%' align='center'><input type=\"button\" class='mainbut' onClick='Generate_Letter()' value=\"View Letter\"></td>");
				out.println("<input type=\"button\" class='mainbut' onClick='Generate_Letter()' value=\"View Letter\"></td>");
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
		else if(m_chksql.equals("early_termi_let_le_agg")){
				int i=0;		
				String m_termi_no = req.getParameter("termi_no");	
				String m_autho_name= req.getParameter("autho_name");	
				String m_vihicle_no= req.getParameter("vihicle_no");
				String m_design = req.getParameter("design");
				String m_print = req.getParameter("print");
			

				String m_Letter_date="";
				String m_lessee ="";
				String m_finance_no ="";


				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') ||'  '||TO_CHAR(SYSDATE,'Month')||''||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
											
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
			
		
				rs = stmt.executeQuery (" SELECT A.TERMINATION_NO,A.CLIENT_CODE,B.FULL_NAME,A.FINANCE_NO "+
		                        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
														" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
														" A.TERMINATION_NO='"+m_termi_no+"'	");	
				
				more = rs.next();
				if(more){
					m_lessee = rs.getString(3);
					m_finance_no = rs.getString(4);
				}			

			  	out.println("<html><head>"); 
				out.println("<title>Early termination letter Lease Agreement</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      		out.println("<script>");
			
				out.println("function save_data(){");			
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_Deletion_Letter?chksql=Letter&termi_no="+m_termi_no+"&autho_name="+m_autho_name+"&vihicle_no="+m_vihicle_no+"&design="+m_design+"&print=FALSE\";");  
			  	out.println(" window.location.href=m_url;");		
				out.println("m_table.innerHTML=\"\" ");		
				out.println("window.print();");		
				out.println("}");
			
		
		  		out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else {
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
				}
				out.println("}");
				out.println("</script>");	
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
			
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  	out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
				out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
			 	out.println("</table>");

				out.println("</font></p></blockquote>");	
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
				out.println("</table>");
			
				//	out.println("m_fin_number"+m_fin_number);
			
				out.println("<br>");
			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Name </td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >Address</td></tr>");
				out.println("</table>");	
			
				out.println("<br><br>");
									
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Dear Sir or Madam:</td></tr>");
				out.println("</table>");
				out.println("<br>");
//========================================== Add By Indika ===================================================

			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' >Lessees Name</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
				out.println("<tr><td width='35%' class='rep-body' >Lease Contract No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_vihicle_no+" </td></tr>");
				out.println("</table>");
//========================================= End By Indika ========================================================
				out.println("<br><br>");
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				
				String data="This is to inform you that the above lessee has fully settle the above contract and you do not have any liability against the above contract. ";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");
				
				data="We sincerely appreciate your valuable business relation had with us as a guarantor. ";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				
				data="We are always happy to serve you and please feel free to contact undersigned for your future requirement.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
				
				data="The Orient Family wishes you all the success!!!";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
		///////////////////////////////////////////////////////////////////////////////		

            data="Thanking You,";
				out.println("<br>");																	
									
				//out.println("<table border='0' width='90%' class='table'>"); 		
				//out.println("<tr>");
				//out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				//out.println("</tr></table>");
			
			
			 	data="Yours sincerely,";
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
			
				out.println("<br>");
				out.println("<br>");	
				out.println("<br>");
						
				out.println("<table border='0' width='90%' class='table'>");  		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+m_autho_name+"</td>");
				out.println("</tr>");	
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+m_design+"</td>");
				out.println("</tr></table>");	

				out.println("<br><br><br><br>");
				out.println("<br>");
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >CC: Insurance Company</td>");
				out.println("</tr></table>");	
	    		
				out.println("</font></p></blockquote>");										
		  		out.println("</form></body></html>");

			}
			else if(m_chksql.equals("early_termi_let_le_pur_agg")){
				int i=0;		
				String m_termi_no = req.getParameter("termi_no");	
				String m_autho_name= req.getParameter("autho_name");	
				String m_vihicle_no= req.getParameter("vihicle_no");
				String m_design = req.getParameter("design");
				String m_print = req.getParameter("print");
			

				String m_Letter_date="";
				String m_lessee ="";
				String m_finance_no ="";


				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') ||'  '||TO_CHAR(SYSDATE,'Month')||''||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
											
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
			
		
				rs = stmt.executeQuery (" SELECT A.TERMINATION_NO,A.CLIENT_CODE,B.FULL_NAME,A.FINANCE_NO "+
		                        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
														" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
														" A.TERMINATION_NO='"+m_termi_no+"'	");	
				
				more = rs.next();
				if(more){
					m_lessee = rs.getString(3);
					m_finance_no = rs.getString(4);
				}			

			  	out.println("<html><head>"); 
				out.println("<title>Early termination letter Lease Agreement</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      		out.println("<script>");
			
				out.println("function save_data(){");			
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_Deletion_Letter?chksql=Letter&termi_no="+m_termi_no+"&autho_name="+m_autho_name+"&vihicle_no="+m_vihicle_no+"&design="+m_design+"&print=FALSE\";");  
			  	out.println(" window.location.href=m_url;");		
				out.println("m_table.innerHTML=\"\" ");		
				out.println("window.print();");		
				out.println("}");
			
		
		  		out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else {
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
				}
				out.println("}");
				out.println("</script>");	
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
			
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  	out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
				out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
			 	out.println("</table>");

				out.println("</font></p></blockquote>");	
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
				out.println("</table>");
			
				//	out.println("m_fin_number"+m_fin_number);
			
				out.println("<br>");
			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Name </td></tr>");
				out.println("</table>");	
			
				out.println("<br><br>");
									
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Dear Sir/Madam,</td></tr>");
				out.println("</table>");
				out.println("<br>");
//========================================== Add By Indika ===================================================

			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' >Early Termination Cost of Vehicle/Equipment No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
				out.println("<tr><td width='35%' class='rep-body' >Lease Agreement Noo</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_vihicle_no+" </td></tr>");
				out.println("</table>");
//========================================= End By Indika ========================================================
				out.println("<br><br>");
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");
				
				String data="We refer to your inquiry on the captioned subject and we are pleased to provide the early termination settlement value as at <date>";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >Rs</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Discounted future rentals value with VAT</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >...............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >NIBSM</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >..............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Over due Rentals with VAT</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >..............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Overdue Interest Charges</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >.............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Sale price of the equipment</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Other Charges	</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >(-) NIBSM</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >-------------</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Total settlement figure</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >========</td></tr>");
				out.println("</table>");
				
				data="We would appreciate if you make arrangement to settle above amount on or before above date as the above offer expire on <date>";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				
				data="We are always happy to serve you and please feel free to contact Mr. <name of the Client Manager> for your future requirement.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		///////////////////////////////////////////////////////////////////////////////		

				out.println("<br><br>");																				
			
			 	data="Yours faithfully,";
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
			
				out.println("<br>");
				out.println("<br>");	
				out.println("<br>");
						
				out.println("<table border='0' width='90%' class='table'>");  		
				out.println("<tr>");
				out.println("<td width='25%' class='rep-body' style='text-align:justify' >Authorized Signatory</td>");
				out.println("</tr></table>");	

				out.println("<br><br><br><br>");
				out.println("<br>");
				out.println("</font></p></blockquote>");										
		  		out.println("</form></body></html>");

			}
			else if(m_chksql.equals("normal_ter_le_fin")){
				int i=0;		
				String m_termi_no = req.getParameter("termi_no");	
				String m_autho_name= req.getParameter("autho_name");	
				String m_vihicle_no= req.getParameter("vihicle_no");
				String m_design = req.getParameter("design");
				String m_print = req.getParameter("print");
			

				String m_Letter_date="";
				String m_lessee ="";
				String m_finance_no ="";


				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') ||'  '||TO_CHAR(SYSDATE,'Month')||''||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
											
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
			
		
				rs = stmt.executeQuery (" SELECT A.TERMINATION_NO,A.CLIENT_CODE,B.FULL_NAME,A.FINANCE_NO "+
		                        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
														" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
														" A.TERMINATION_NO='"+m_termi_no+"'	");	
				
				more = rs.next();
				if(more){
					m_lessee = rs.getString(3);
					m_finance_no = rs.getString(4);
				}			

			  	out.println("<html><head>"); 
				out.println("<title>Early termination letter Lease purchase Agreement</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      		out.println("<script>");
			
				out.println("function save_data(){");			
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_Deletion_Letter?chksql=Letter&termi_no="+m_termi_no+"&autho_name="+m_autho_name+"&vihicle_no="+m_vihicle_no+"&design="+m_design+"&print=FALSE\";");  
			  	out.println(" window.location.href=m_url;");		
				out.println("m_table.innerHTML=\"\" ");		
				out.println("window.print();");		
				out.println("}");
			
		
		  		out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else {
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
				}
				out.println("}");
				out.println("</script>");	
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
			
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  	out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
				out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
			 	out.println("</table>");

				out.println("</font></p></blockquote>");	
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
				out.println("</table>");
			
				//	out.println("m_fin_number"+m_fin_number);
			
				out.println("<br>");
			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Name </td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >Address</td></tr>");
				out.println("</table>");	
			
				out.println("<br><br>");
									
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Dear Sir/Madam,</td></tr>");
				out.println("</table>");
				out.println("<br>");
//========================================== Add By Indika ===================================================

			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='50%' class='rep-body' >Early Termination Cost of Vehicle/Equipment No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
				out.println("<tr><td width='35%' class='rep-body' >Lease Purchase Agreement No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_vihicle_no+" </td></tr>");
				out.println("</table>");
//========================================= End By Indika ========================================================
				out.println("<br><br>");
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");									
				
				String data="We refer to your inquiry on the captioned subject and we are pleased to provide the early termination settlement value as at ....................................";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >Rs</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Discounted future rentals value</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >...............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Over due Rentals</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >..............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Overdue Interest Charges</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >..............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Sale price of the equipment</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >.............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Other Charges</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >-------------</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Total settlement figure</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >========</td></tr>");
				out.println("</table>");
		///////////////////////////////////////////////////////////////////////////////		
			
			 	data="Yours faithfully,";
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
			
				out.println("<br>");
				out.println("<br>");	
				out.println("<br>");
						
				out.println("<table border='0' width='90%' class='table'>");  		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >Authorized Signatory</td>");
				out.println("</tr></table>");
				out.println("<br><br><br><br>");	
				out.println("</font></p></blockquote>");										
		  		out.println("</form></body></html>");

			}
			else if(m_chksql.equals("normal_ter_le_pur_hire")){
				int i=0;		
				String m_termi_no = req.getParameter("termi_no");	
				String m_autho_name= req.getParameter("autho_name");	
				String m_vihicle_no= req.getParameter("vihicle_no");
				String m_design = req.getParameter("design");
				String m_print = req.getParameter("print");
			

				String m_Letter_date="";
				String m_lessee ="";
				String m_finance_no ="";


				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') ||'  '||TO_CHAR(SYSDATE,'Month')||''||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
											
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
			
		
				rs = stmt.executeQuery (" SELECT A.TERMINATION_NO,A.CLIENT_CODE,B.FULL_NAME,A.FINANCE_NO "+
		                        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
														" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
														" A.TERMINATION_NO='"+m_termi_no+"'	");	
				
				more = rs.next();
				if(more){
					m_lessee = rs.getString(3);
					m_finance_no = rs.getString(4);
				}			

			  	out.println("<html><head>"); 
				out.println("<title>Normal termination lease  finlease</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      		out.println("<script>");
			
				out.println("function save_data(){");			
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_Deletion_Letter?chksql=Letter&termi_no="+m_termi_no+"&autho_name="+m_autho_name+"&vihicle_no="+m_vihicle_no+"&design="+m_design+"&print=FALSE\";");  
			  	out.println(" window.location.href=m_url;");		
				out.println("m_table.innerHTML=\"\" ");		
				out.println("window.print();");		
				out.println("}");
			
		
		  		out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else {
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
				}
				out.println("}");
				out.println("</script>");	
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()	
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
			
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  	out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
				out.println("<td width=\"20%\" class='rep-body'><b></b></td></tr>");
			 	out.println("</table>");

				out.println("</font></p></blockquote>");	
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</td></tr>");
				out.println("</table>");
			
				//	out.println("m_fin_number"+m_fin_number);
			
				out.println("<br>");
			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Name</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >Client Name</td></tr>");
				out.println("<tr><td width='*%' class='rep-body' >Address</td></tr>");
				out.println("</table>");	
			
				out.println("<br><br>");
									
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >Dear Sir or Madam:</td></tr>");
				out.println("</table>");
				out.println("<br>");
//========================================== Add By Indika ===================================================

			
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' >Vehicle/Equipment No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_finance_no+" </td></tr>");
				out.println("<tr><td width='35%' class='rep-body' >Lease Contract No</td>");
				out.println("<td width='2%' class='rep-body' >:</td>");
				out.println("<td width='*%' class='rep-body' >"+m_vihicle_no+" </td></tr>");
				out.println("</table>");
//========================================= End By Indika ========================================================
				out.println("<br><br>");
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
				
				String data="We sincerely appreciate your valuable business relation had with us and wish to inform you that the above contract will be matured on <date> as per the lease terms.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");			
				
				data="We would appreciate if you make arrangement to pay following amount to get the unfettered rights of the above equipment.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >Rs</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Overdue lease rentals with VAT</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >...............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Future rental with VAT</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >..............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Overdue Interest</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >..............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Sale price of the equipment</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >.............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Other Charges</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >............</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' ></td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >-------------</td></tr>");
				out.println("<tr><td width='40%' class='rep-body' >Total receivables</td>");
				out.println("<td width='10%' class='rep-body' ></td>");
				out.println("<td width='*%' class='rep-body' >========</td></tr>");
				out.println("</table>");
				
				data="In order to finalize of equipment sale, please forward your remittance for Rs...........on or before ............, beyond which further overdue interest will accumulate.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
				
				data="We are always happy to serve you and please feel free to contact Mr. <Client Manager's name>for your future requirement.";  										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		///////////////////////////////////////////////////////////////////////////////		

            data="Thanking You,";
				out.println("<br>");																	

			 	data="Yours sincerely";
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
		 		data=""+m_orient_name+""; //ORIENT FINANCIAL SERVICES CORPORATION LIMITED  //MODIFIED BY nuwan de silva 17-07-07
										
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");	
			
				out.println("<br>");
				out.println("<br>");	
				out.println("<br>");
						
				out.println("<table border='0' width='90%' class='table'>");  		
				out.println("<tr>");
				out.println("<td width='*%' class='rep-body' style='text-align:justify' >Authorized Signatory</td>");
				out.println("</tr></table>");	

				out.println("<br><br><br><br>");
				out.println("<br>");
				out.println("</font></p></blockquote>");										
		  		out.println("</form></body></html>");

			}		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
					if(rs    !=null){try{rs.close();   }catch(Exception e){}}
					if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
					if(conn  !=null){try{conn.close(); }catch(Exception e){}}
					if(out!=null){try{out.close();  }catch(Exception e){}}
				
		}
	}
}
