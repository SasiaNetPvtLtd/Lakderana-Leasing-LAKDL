//--
//SCREEN NAME: Rental Details Report
//CREATED BY : CHANDANA
//DATE/TIME  : 20/04/2007
//NOTES			 :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_Rental_Detail_Report extends javax.servlet.http.HttpServlet { 

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
			PreparedStatement pstmt;
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();

      m_chksql=req.getParameter("chksql");
			out = res.getOutputStream();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			String m_fin_code,m_invoice;
			
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_sys_date_dd="";
			String m_sys_date_mm="";
			String m_sys_date_yy="";
			

		if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> System Administration - Rental Details Report </TITLE>"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_client?chksql=main_page';"); 
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
			out.println("help_box.innerHTML=\"System Administration - Rental Details Report  \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"System Administration - Rental Details Report  \"+document.Form1.hid_status.value;"); 
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
			out.println("		if(document.Form1.hid_help_type.value==\"101\"){"); 
			out.println("		help_update_value_assign_101();"); 
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
			out.println("}else if(document.Form1.hid_help_type.value==\"100\"){ ");
			out.println(" document.Form1.TXT_MKT_OFFICER.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"101\"){ ");
			out.println(" document.Form1.TXT_SUP_NAME.value=\"\";");
			out.println("} ");
			out.println("} ");
			out.println("");
			
			
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_NAME.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','3');"); 
			out.println("}"); 
			
			
			
			

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_REQ_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    m_sql = \"m_help_TXT_EMPLOYEE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_MKT_OFFICER.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"101\";"); 
			out.println("    m_sql = \"m_help_TXT_EMPLOYEE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUP_NAME.value+\"@\"+\"Y@\";"); 
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
			out.println("    document.Form1.hid_fin_no.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.hid_add1.value=oBj.valout[4];");
			out.println("    document.Form1.hid_add2.value=oBj.valout[5];");
			out.println("    document.Form1.hid_add3.value=oBj.valout[6];");
			out.println("}"); 
			
			out.println("function help_update_value_assign_100() {"); 
			out.println("    document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];");
			out.println("    document.Form1.hid_disignation.value=oBj.valout[4];");
			out.println("}");
			
			out.println("function help_update_value_assign_101() {"); 
			out.println("    document.Form1.TXT_SUP_NAME.value=oBj.valout[2];");
			out.println("    document.Form1.hid_sup_disignation.value=oBj.valout[4];");
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
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
			
			
			out.println("function getDateValues(dval){");
			out.println("if(dval!='null'){");
			out.println("document.Form1.VAL_DAY.value=dval.substring(0,2)");
			out.println("document.Form1.VAL_MONTH.value=dval.substring(3,5)");
			out.println("document.Form1.VAL_YEAR.value=dval.substring(6,10)");
			out.println("}");
			out.println("}");
			
			
						out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
			
						
			
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("}");


     out.println("function Generate_Report() {");
			out.println(" 	m_from_dd = document.Form1.VAL_DAY.value ");
			out.println(" 	m_from_mm = document.Form1.VAL_MONTH.value ");
			out.println(" 	m_from_yy = document.Form1.VAL_YEAR.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
					
     out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Rental_Detail_Report?chksql=Report&from_date=\"+m_from_date+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
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
			out.println("<INPUT TYPE='Hidden' NAME='hid_fin_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_disignation' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_sup_disignation' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> System Administration - Rental Details Report </td>"); 
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

			
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");
								
					boolean more1 = rs.next();
						
							if (more1){
              			
										m_sys_date_dd=rs.getString(1);
										m_sys_date_mm=rs.getString(2);
										m_sys_date_yy=rs.getString(3);
										}

			
			
			
			
			
      out.println("<tr>");
			out.println("<td ID=VDATE>Report Date</td>");
			out.println("<td>           <input name=\"VAL_DAY\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value='"+m_sys_date_dd+"'> ");
			out.println("                <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value='"+m_sys_date_mm+"' > ");
			out.println("                <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value='"+m_sys_date_yy+"' onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("</td>");
			out.println("<td align='center'><input type=\"button\" class='mainbut' onClick='Generate_Report()' value=\"View\"></td>");
								
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



else if(m_chksql.equals("Report")){

double m_capital_amt=0.00;
double m_interest_amt=0.00;


      String m_from_date = req.getParameter("from_date");
			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Advertisement Offers Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("function rental_detail_drill(app_no) {");
								
     out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Rental_Detail_Report?chksql=DRILL_DOWN&from_date="+m_from_date+"&appl_no=\"+app_no+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=350,width=700,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
     out.println("}");
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
					
			pstmt = conn.prepareStatement("SELECT APPLICATION_NO ,SUM(GRENTAL_AMOUNT) "+
			         " FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT "+
							 "	WHERE TO_DATE(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
							" APPLICATION_NO IN (SELECT APPLICATION_NO "+
							" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS "+
							" WHERE APPLICATION_STATUS='ACTIVATED') "+
							" GROUP BY APPLICATION_NO     ");
      
 

			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			out.println("<DIV STYLE='{position:absolute; top:40; left:0 cursor: hand;}'>");
			out.println("<table align='center' width='900' class='table' border=1>"); 
			while(more){
						out.println("<tr >"); 
						
			String m_app_no = rs.getString(1);			
						
					rs1 =	stmt.executeQuery(" SELECT CAPITAL_AMOUNT,INTEREST_AMOUNT "+
					      " FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT "+
								" WHERE TO_CHAR(RENTAL_DATE,'MON-YY') = TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),'MON-YY') AND "+
								" APPLICATION_NO = '"+m_app_no+"' ");
						
					boolean more1 = rs1.next(); 	
						
						if(more1){
						
						
						 m_capital_amt = rs1.getDouble(1);
             m_interest_amt= rs1.getDouble(2);
						
						}
						
											
						
						out.println("<TD width='225' class='txt_report_data' align='center' style= cursor:hand;cursor-color:blue onclick=rental_detail_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></TD>");
						out.println("<TD width='225' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(2))+"</TD>");
						out.println("<TD width='225' class='txt_report_data' align='right'>"+nf.format(m_capital_amt)+"</TD>");
						out.println("<TD width='225' class='txt_report_data' align='right'>"+nf.format(m_interest_amt)+"</TD>");
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>");
			out.println("</DIV>");
			
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
   		out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 900; height: 15'>");
			out.println("<table align='center' width='900' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='225' class='txt_report_column'>APPLICATION NO</td>"); 
			out.println("<td width='225' class='txt_report_column'>GRENTAL AMOUNT</td>"); 
			out.println("<td width='225' class='txt_report_column'>CAPITAL AMOUNT</td>"); 
			out.println("<td width='225' class='txt_report_column'>INTEREST AMOUNT</td>"); 
			out.println("</tr >"); 
			out.println("</table>");  
			out.println("</div>"); 
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
			conn.close();
			out.flush();
			out.close();
			






}

else if(m_chksql.equals("DRILL_DOWN")){

String m_appl_no   = req.getParameter("appl_no");
String m_from_date = req.getParameter("from_date");



	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Advertisement Offers Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>RENTAL DETAILS - APPLICATION NO "+m_appl_no+"</td>"); 
			out.println("</table>");
			
			out.println("<BR>");
             
																		
																			
																			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='17.5%' class='txt_report_column'>Pricing No</td>"); 
			out.println("<td width='15%' class='txt_report_column'>Instalment No</td>");
			out.println("<td width='15%' class='txt_report_column'>Rental Date</td>");
			out.println("<td width='17.5%' class='txt_report_column'>Rental Amount</td>"); 
			out.println("<td width='17.5%' class='txt_report_column'>Capital Amount</td>"); 
			out.println("<td width='17.5%' class='txt_report_column'>Interest Amount</td>"); 
			out.println("</tr >"); 
			
			
										rs =	stmt.executeQuery(" SELECT APPLICATION_NO,PRICING_NO,INSTALLMENT_NO,TO_CHAR(RENTAL_DATE,'DD-MON-YY'),GRENTAL_AMOUNT,CAPITAL_AMOUNT,INTEREST_AMOUNT "+
							                        " FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT "+
																			" WHERE TO_DATE(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
																			" APPLICATION_NO = '"+m_appl_no+"' "+
																			" ORDER BY  RENTAL_DATE ");
						boolean more = rs.next();													
																			
			while(more){																
			out.println("<tr >"); 
			out.println("<td width='17.5%' class='txt_report_data' align='center'>"+rs.getString(2)+"</td>"); 
			out.println("<td width='15%' class='txt_report_data' align='center'>"+rs.getString(3)+"</td>");
			out.println("<td width='15%' class='txt_report_data' align='center'>"+rs.getString(4)+"</td>");
			out.println("<td width='17.5%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width='17.5%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width='17.5%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("</tr >"); 																
			
			more = rs.next();
			
			}
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			//pstmt.close();
			conn.close();
			out.flush();
			out.close();
			

}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	
		
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
