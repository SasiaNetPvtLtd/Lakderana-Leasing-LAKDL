//===========Created by Dineth Meemenage===================
//===========Date:2008-08-20
//===========Screen Name:AF_MAS_GUAR_THANK_LETTER




import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MAS_thank_letter_for_guarantor extends javax.servlet.http.HttpServlet { 


			ServletOutputStream out = null;
			public String m_chksql;
			Connection conn;
			Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
			java.text.NumberFormat nf,nf1;
			public ResultSet rs,rs1,rs2,rs3,rs4,rs5;
			
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
							stmt5=conn.createStatement();
							String m_data="";
							String m_orient_name="";
							String m_orient_add1="";
							String m_orient_add2="";
							String m_orient_city_name="";
							String m_orient_tel_no="";
							String m_orient_fax_no="";
							String m_orient_vat_rate="";
							m_chksql=req.getParameter("chksql");
							out = res.getOutputStream(); 
							String m_fin_code,m_invoice;
			
							String m_schema_name = m_sn_methods.schema_name;
						  String m_guar_name="";
							String m_guar_add1="";
							String m_guar_add2="";
							String m_guar_city_name="";
							
							if(m_chksql.equals("mein_page")){
									
									
									out.println("<HTML>"); 
									out.println("<HEAD>"); 
									out.println("<TITLE> Collection - Thanking Letter-Guarantor </TITLE>"); 
									out.println("</HEAD>"); 
									out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
									out.println("<SCRIPT language=\"JavaScript\">"); 
									out.println("var m_cnt=0");
									out.println("var m_chk=0");
									out.println("var b_flag=0;");
									
									
									
					out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			//out.println(" alert(document.Form1.hid_no_val.value);");
			out.println("if(document.Form1.TXT_FINANCENO.value!=\"\" && document.Form1.hid_no_val.value==\"0\"){");

			out.println("alert('No records')");
			out.println("document.Form1.TXT_FINANCENO.value=\"\"");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_status.value='New'");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=request_details&m_val=\"+document.Form1.hid_status.value+\"&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_guarantor?chksql=request_details&m_val=\"+document.Form1.hid_status.value+\"&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"\";");

			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FINANCENO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCENO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_MKT_OFFICER.value==\"\"){  "); 
			out.println("DIV_TXT_MKT_OFFICER.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println("if(document.Form1.TXT_SUP_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_SUP_NAME.style.color='red';");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_guarantor?chksql=main_page';"); 
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
			out.println("help_box.innerHTML=\"Collection - Thanking Letter-Guarantor \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Collection - Thanking Letter-Guarantor \"+document.Form1.hid_status.value;"); 
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
			out.println("		help_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"200\"){"); 
			out.println("		help_update_value_assign_200();"); 
	  	out.println("		}"); 	
			out.println("		if(document.Form1.hid_help_type.value==\"201\"){"); 
			out.println("		help_update_value_assign_201();"); 
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
			
			
			/*out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){");
			out.println(" document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("}else{ ");
			out.println(" document.Form1.TXT_FINANCENO.value=\"\";");
			out.println("} ");
			out.println("} ");
			out.println("");*/
			
			out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){");
			out.println(" document.Form1.TXT_FINANCENO.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"200\"){ ");
			out.println(" document.Form1.TXT_MKT_OFFICER.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"201\"){ ");
			out.println(" document.Form1.TXT_SUP_NAME.value=\"\";");
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
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCENO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
				
			out.println("function help_value_assign_100() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){");
			out.println("   document.Form1.TXT_FINANCENO.value=oBj.valout[2];");
			out.println("}"); 	
			out.println("else if(document.Form1.SCREEN_NAME.value=='EDIT'){");
			out.println("   document.Form1.TXT_FINANCENO.value=oBj.valout[2];"); 
			out.println("}"); 
			out.println(" 	makeRequest(); ");
			out.println("}");

      //===============Marketing Officer Help Functions
			
			out.println("function help_value_assign_200() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){");
			out.println("   document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];"); 
			out.println("}"); 	
			out.println("else if(document.Form1.SCREEN_NAME.value=='EDIT'){");
			out.println("   document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];"); 
			out.println("}"); 
			out.println("}");
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"200\";"); 
			out.println("    m_sql = \"m_help_TXT_EMPLOYEE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_MKT_OFFICER.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_200() {"); 
			out.println("    document.Form1.TXT_MKT_OFFICER.value=oBj.valout[2];");
			out.println("    document.Form1.hid_disignation.value=oBj.valout[4];");
			out.println("}");
			
			//===============End Marketing Officer Help Functions
      //===============Supervisor Help Functions
			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"201\";"); 
			out.println("    m_sql = \"m_help_TXT_EMPLOYEE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUP_NAME.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_201() {"); 
			out.println("    document.Form1.TXT_SUP_NAME.value=oBj.valout[2];");
			out.println("    document.Form1.hid_sup_disignation.value=oBj.valout[4];");
			out.println("}");
			//===============End Supervisor Help Functions
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
			
			
			/*out.println("function Generate_Letter() {");
			
			out.println("if(validate_data()){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_bus_introducer?chksql=Letter&client_name=\"+document.Form1.TXT_CLIENT_NAME.value+\"&autho_name=\"+document.Form1.TXT_EMPLOYEE.value+\"&add1=\"+document.Form1.hid_add1.value+\"&add2=\"+document.Form1.hid_add2.value+\"&add3=\"+document.Form1.hid_add3.value+\"&design=\"+document.Form1.hid_disignation.value+\"&print=TRUE\";"); 
			out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}else{");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("}");
			out.println("}");*/
			
		  out.println("function Generate_Letter(val1,val2,val3,val4) {");
			
			out.println("if(validate_data()){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MAS_thank_letter_for_guarantor?chksql=Letter&finance_no=\"+document.Form1.TXT_FINANCENO.value+\"&guar_name=\"+val1+\"&guar_add1=\"+val2+\"&guar_add2=\"+val3+\"&guar_city=\"+val4+\"&mkt_name=\"+document.Form1.TXT_MKT_OFFICER.value+\"&sup_name=\"+document.Form1.TXT_SUP_NAME.value+\"\";"); 
			//out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 

			out.println("}else{");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
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
									out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection - Thanking Letter-Introducer </td>"); 
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
									out.println("<td width='15%' ><DIV id='DIV_TXT_FINANCENO' class=div_input>Finance No *</DIV></td>"); 
									out.println("<td width='30%' ><input class='txt_input' value=\"\" type='text' name='TXT_FINANCENO' maxlength='15' size='15' onblur=\"\">"); 
									out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCENO' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
									out.println("<td width='5%'>&nbsp;</td>");
									
									
									out.println("<td width='15%' ><DIV id='DIV_TXT_MKT_OFFICER' class=div_input>Marketing officer's Name *</DIV></td>"); 
									out.println("<td width='30%' ><input class='txt_input' value=\"\" type='text' name='TXT_MKT_OFFICER' maxlength='15' size='15' onblur=\"\">"); 
									out.println("<input class='but_input' type='button' name='BUT_TXT_MKT_OFFICER' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
									
									out.println("<td width='*%'></td>"); 
									out.println("</tr>"); 
			
									out.println("<tr>");
									out.println("<td colspan=3>&nbsp;</td>");
									out.println("<td width='15%' ><DIV id='DIV_TXT_SUP_NAME' class=div_input>Supervisors Name *</DIV></td>"); 
									out.println("<td width='30%' ><input class='txt_input' value=\"\" type='text' name='TXT_SUP_NAME' maxlength='15' size='15' onblur=\"\">"); 
									out.println("<input class='but_input' type='button' name='BUT_TXT_SUP_NAME' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
									
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
							
							else if(m_chksql.equals("requestdetails")){
							
							      
										String m_hid_val=req.getParameter("m_val");
										String m_fin_no=req.getParameter("finance_no");
										String sql1="SELECT  "+
						" A.GUARANTOR_CODE "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A, "+ 
						" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE UPPER(A.APPLICATION_NO)=UPPER(B.APPLICATION_NO) AND "+
						" B.FINANCE_NO= '"+m_fin_no+"' "+
						" ORDER BY A.APPLICATION_NO DESC";
						
						int val1=0;
						rs1 = stmt1.executeQuery(sql1);
						boolean more2=rs1.next();
						if(more2){
						
						val1=1;
						
						
					}
						
						
										
										
										
										String sql2= "SELECT  "+
						
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //3
						" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //4
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //1
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE IN  "+
						" (SELECT  "+
						" GUARANTOR_CODE "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A, "+ 
						" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE UPPER(A.APPLICATION_NO)=UPPER(B.APPLICATION_NO) AND "+
						" B.FINANCE_NO= '"+m_fin_no+"')";
						  
							
						rs2 = stmt2.executeQuery(sql2);
						boolean more=rs2.next();
						if(more){
						
						out.println("<table class=table border='0' width='100%' >");
						
						out.println("<tr class=pdn_txtpos2 align='left'>");
						out.println("<td  width='20%'  >Full Name</td>");
          	out.println("<td  width='20%'  >Address1</td>");
						out.println("<td  width='20%'  >Address2</td>");
						out.println("<td  width='20%'  >City</td>");
						out.println("<td  width='20%'  >Letter</td>");
						out.println("</tr>");
						int j = 0;
					
					while(more){
					m_guar_name=rs2.getString(1);
					m_guar_add1=rs2.getString(2);
					m_guar_add2=rs2.getString(3);
					m_guar_city_name=rs2.getString(4);
					if(j>0 && j%2==1){
	  out.println("<tr class=tr_input1 >");
		}
		else{
		out.println("<tr class=tr_input >");
		}
					/*out.println("<input type='hidden' name='hid_guar_name' value=\""+m_guar_name+"\">");
					out.println("<input type='hidden' name='hid_guar_add1' value=\""+m_guar_add1+"\">");
					out.println("<input type='hidden' name='hid_guar_add2' value=\""+m_guar_add2+"\">");
					out.println("<input type='hidden' name='hid_guar_city_name' value=\""+m_guar_city_name+"\">");*/

					out.println("<td width='20%'  align='left'>"+m_guar_name +"</td>");
					out.println("<td width='20%'  align='left'>"+m_guar_add1 +"</td>");
          out.println("<td width='20%'  align='left'>"+m_guar_add2 +"</td>");
          out.println("<td width='20%'  align='left'>"+m_guar_city_name +"</td>");
					out.println("<td width='20%'  align='center'><input type=button class='but_input' name=\"letter_generation_but\" value=\"Generate\" onclick=\"Generate_Letter('"+m_guar_name+"','"+m_guar_add1+"','"+m_guar_add2+"','"+m_guar_city_name +"')\" style=\"{width:100px}\"></td>");
					out.println("</tr>");
					/*out.println("<input type='hidden' name='hid_guar_name' value=\""+m_guar_name+"\">");
					out.println("<input type='hidden' name='hid_guar_add1' value=\""+m_guar_add1+"\">");
					out.println("<input type='hidden' name='hid_guar_add2' value=\""+m_guar_add2+"\">");
					out.println("<input type='hidden' name='hid_guar_city_name' value=\""+m_guar_city_name+"\">");*/

					more=rs2.next();
					j=j+1;
		
		
					}
					
					
						}
						
			out.println("</table>");
			
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					out.println("<tr>");
										out.println("<td><input type='hidden' name='hid_no_val' value=\""+val1+"\"></td>");
					
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
								
							}
				else if(m_chksql.equals("Letter")){
						String m_fin_no_1=req.getParameter("finance_no");
						String m_mkt_name_1=req.getParameter("mkt_name");
						String m_sup_name_1=req.getParameter("sup_name");
						String m_guar_name_1=req.getParameter("guar_name");
						String m_guar_add_1=req.getParameter("guar_add1");
						String m_guar_add_2=req.getParameter("guar_add2");
						String m_guar_city_1=req.getParameter("guar_city");
						String client_full_name="";
						String m_Letter_date="";
				/*out.println(m_fin_no_1);
						out.println(m_mkt_name_1);
						out.println(m_sup_name_1);
				out.println(m_guar_name_1);
				out.println(m_guar_add_1);
				out.println(m_guar_add_2);
				out.println(m_guar_city_1);*/
				
				rs4 = stmt4.executeQuery ("SELECT TO_CHAR(SYSDATE,'MONTH DD,YYYY') FROM DUAL ");
			
						boolean more = rs4.next();
						if(more){
								m_Letter_date=rs4.getString(1);
						}
				
				
				String client_sql2="SELECT NVL(UPPER(A.TITLE)||' '||UPPER(A.FULL_NAME),'-') "+ 
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
				" AND B.FINANCE_NO='"+m_fin_no_1+"'";
				rs3=stmt3.executeQuery(client_sql2);
				boolean more3 = rs3.next();
				if(more3){
						client_full_name=rs3.getString(1);
				}
						out.println("<html><head>"); 
						out.println("<title>Collection - Thanking Letter-Guarantor</title></head>");
						out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  		out.println("<script>");
						out.println("function save_data(){");
						//out.println("get_annexure('"+m_application_no+"')");
						out.println("m_table.innerHTML=\"\" ");
						out.println("window.print();");
						out.println("  }");
						out.println("function add_button(){");
						out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
						out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
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
						out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  			out.println("</table>");
						out.println("</font></p></blockquote>");	
			
			
			
	          out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' >Date&nbsp;&nbsp;&nbsp;:&nbsp;"+m_Letter_date+"</td></tr>");
						out.println("</table>");
			
						out.println("<br><br><br>");
						
						
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_guar_name_1+"</b></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_guar_add_1+"</b></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_guar_add_2+"</b></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_guar_city_1+"</b></td></tr>");
						//out.println("</tr>");
						out.println("</table>");
						
						
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' >Dear Sir or Madam:</td></tr>");
						
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='20%' class='rep-body1' style='{text-align:left}' >Lessees Name</td>");
						out.println("<td width='8%' class='rep-body1' style='{text-align:left}'>:</td>");
						out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+client_full_name+"</td></tr>");
						//out.println("<tr><td width='20%' class='rep-body1' style='{text-align:left}'>Assets Details</td><td width='5%' style='{text-align:center}'>:</td>></tr>");
						out.println("</table>");
						
						
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='20%' class='rep-body1' style='{text-align:left}' >Lease Contract No</td>");
						out.println("<td width='8%' class='rep-body1' style='{text-align:left}'>:</td>");
						out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+m_fin_no_1+"</td></tr>");
						//out.println("<tr><td width='20%' class='rep-body1' style='{text-align:left}'>Assets Details</td><td width='5%' style='{text-align:center}'>:</td>></tr>");
						out.println("</table>");
									
						out.println("<br>");
						//m_data="We write to inform you that as per article 17 of the above Lease Agreement, you are legally bound and obliged to arrange to have the ";
						m_data="This is to inform you that the above lessee has fully settle the above contract and you do not have any liability against the above contract.";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						//m_data="We write to inform you that as per article 17 of the above Lease Agreement, you are legally bound and obliged to arrange to have the ";
						m_data="We sincerely appreciate your valuable business relation had with us as a guarantor.";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						
						out.println("<br>");
						//m_data="We write to inform you that as per article 17 of the above Lease Agreement, you are legally bound and obliged to arrange to have the ";
						m_data="We are always happy to serve you and please feel free to contact undersigned for your future requirement.";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						//m_data="We write to inform you that as per article 17 of the above Lease Agreement, you are legally bound and obliged to arrange to have the ";
						m_data="The Orient Family wishes you all the success!!!";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						//m_data="We write to inform you that as per article 17 of the above Lease Agreement, you are legally bound and obliged to arrange to have the ";
						m_data="Thanking you";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						//m_data="We write to inform you that as per article 17 of the above Lease Agreement, you are legally bound and obliged to arrange to have the ";
						m_data="Yours sincerely";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						
						rs5 = stmt5.executeQuery(" SELECT "+
							" INITCAP(NVL(UPPER(COMPANY_NAME),' ')), "+
							" NVL(UPPER(ADDRESS1),' '), "+
							" NVL(UPPER(ADDRESS2),' '), "+
							" NVL(UPPER(CITY),' '), "+
							" NVL(TEL_NO,' '), "+
							" NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0) "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
						boolean more5 = rs5.next();	
						
						if(more5)
						{
							m_orient_name=rs5.getString(1);
							m_orient_add1=rs5.getString(2);
							m_orient_add2=rs5.getString(3);
							m_orient_city_name=rs5.getString(4);
							m_orient_tel_no=rs5.getString(5);
							m_orient_fax_no=rs5.getString(6);
							m_orient_vat_rate=rs5.getString(7);			
						}
						
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'><b>"+m_orient_name+"</b></td></tr>");
						out.println("</table>");
						
						out.println("<br><br><br>");
						
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='12%' style='{text-align:left}'><b>Name</b></td><td class='rep-body1' width='38%' style='{text-align:left}'>"+m_mkt_name_1+"</td>");
						out.println("<td class='rep-body1' width='12%' style='{text-align:left}'><b>Name</b></td><td class='rep-body1' width='38%' style='{text-align:left}'>"+m_sup_name_1+"</td></tr>");

						out.println("<tr><td class='rep-body1' width='12%' style='{text-align:left}'><b>Designation</b></td><td class='rep-body1' width='38%' style='{text-align:left}'>Marketing Executive</td>");
						out.println("<td class='rep-body1' width='12%' style='{text-align:left}'><b>Designation</b></td><td class='rep-body1' width='38%' style='{text-align:left}'>Supervisor</td></tr>");

						out.println("</table>");
						
						out.println("</blockquote></blockquote>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScrpt' src='"+m_html_client_url+"/valdate.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajaxa_data_gateway.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
						
				
				}
				  }catch (Exception ex) {
						try{out.println("Error:"+ex.toString());}catch(Exception e){}
					}
					finally{
						if(out!=null){try{out.close();  }catch(Exception e){}}
					}
			
			
			}



}

