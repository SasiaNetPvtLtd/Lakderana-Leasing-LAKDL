
//--
//SCREEN NAME	:SYSTEM ADMINISTRATION - DOCUMENT APPLICABLE
//ID					:1.51 Applicable Document Type Creation Process
//CREATED BY	:N.V.P.Chandana
//DATE/TIME		:25-07-2006/4.30pm
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_document_applicable extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1,rs2;
	Statement stmt,stmt1;
	Connection conn;
	String reqstr;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
		
			String m_arry[]=new String[10];
			String m_div_details = "";
			rs1=stmt.executeQuery(" SELECT DIVISION_CODE,DESCRIPTION "+
													   " FROM   "+m_schema_name+".CO_CO_MAS_DIVISION ");

			boolean more2=rs1.next();
			
			while(more2){
			m_div_details =m_div_details+"<option value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</option>";			
			more2=rs1.next();
			} 
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Documents Applicable</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function get_vector(data_vec) {");
			out.println("     if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_doc_app' ){");
			out.println("       display_applicable_doc(data_vec);");
      out.println("     }");
			out.println("     else if(data_vec.length>0 && document.Form1.hid_help_status.value == 'H_doc_app_fill' ){");
			out.println("       display_applicable_doc_fill(data_vec);");
      out.println("     }");
			out.println("     else if(data_vec.length==0 && document.Form1.hid_help_status.value == 'H_doc_app_fill' ){");
			out.println("     e_applicable_doc.innerHTML='' ");
      out.println("     }");
			out.println("}");
			
			out.println("function makeRequest1(obj) {");
			out.println("    if(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value==\"NEW\") {");
			out.println("    document.Form1.hid_help_stat.value='t1';");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable1&data_val=\"+obj.value;");
			out.println("    } else");
			out.println("    if((document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value!=\"RACT\")&&(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value!=\"NEW\")){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("    } ");
      out.println("    if(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value==\"RACT\")");
      out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable&data_val=\"+obj.value+\"&ac_status=N\";");
     	out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest2(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_doc_app'; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable&data_val=\"+obj;");
			out.println("	load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function makeRequest_new(obj1,val,obj3) {");
			out.println("var obj2=\"\"");
			out.println("if(obj1.value==\"ASSET\"){");
			out.println("obj2=document.Form1.TXT_ASSET_TYPE");
			out.println("}");
			out.println("if(obj1.value==\"CLIENT\"){");
			out.println("obj2=document.Form1.TXT_CLIENT_TYPE");
			out.println("}");

			out.println(" document.Form1.hid_help_status.value='H_doc_app_fill'; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill_new&product=\"+obj3.value+\"&type=\"+obj1.value+\"&client=\"+obj2.value;");
			//out.println("window.open(m_url)"); // test
			out.println("	load_interface(m_url,'XML');");
			out.println("}");

				
			out.println("function makeRequest3(obj,obj2) {");
			out.println(" document.Form1.hid_help_status.value='H_doc_app_fill'; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill&data_val=\"+obj+\"&data_val2=\"+obj2;");
			//out.println("window.open(m_url)");
			out.println("	load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest4(obj,obj2) {");
			out.println(" document.Form1.hid_help_status.value='H_doc_app_fill'; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill2&data_val=\"+obj+\"&data_val2=\"+obj2;");
			out.println("	load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest_new_1(obj1,obj2,obj3) {");
			out.println(" document.Form1.hid_help_status.value='H_doc_app_fill'; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill_new_1&product=\"+obj3.value+\"&type=\"+obj1.value+\"&asset=\"+obj2.value;");
			out.println("window.open(m_url)");
			
			out.println("	load_interface(m_url,'XML');");
			out.println("}");

			out.println("function makeRequest(obj) {");	
			out.println("   if(document.Form1.hid_help_status.value == 'H1'&& document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_ENTITY_TYPE.value!=\"\") {");
      out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_legal_entity&data_val=\"+obj.value+\"&ac_status=Y\";");
      out.println("		}");
      out.println("   if(document.Form1.hid_help_status.value == 'H2'&& document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_STAGE.value!=\"\") {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_stage&data_val=\"+obj.value+\"&ac_status=Y\";");
      out.println("		}");
      out.println("   if(document.Form1.hid_help_status.value == 'H3'&& document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_ITEM_CAT_CODE.value!=\"\") {");
      out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_item_category&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("		}");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			

      out.println(" function assign_help_status(obj){");
      out.println(" document.Form1.hid_help_status.value =obj; ");
      out.println("}");


		  out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.hid_doc_type.value==\"CLIENT\" && document.Form1.TXT_CLIENT_TYPE.value == \"\"  ){  "); 
			out.println(" alert('Please select the client type '); ");
			out.println("e_type.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.hid_doc_type.value==\"ASSET\" && document.Form1.TXT_ASSET_TYPE.value == \"\"  ){  "); 
			out.println(" alert('Please select the asset type '); ");
			out.println("DIV_TXT_ASSET.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if( e_applicable_doc.innerHTML==''){ ");
			out.println(" alert('No records to save '); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("   m_status = document.Form1.hid_status.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"Edit\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
			out.println("   }"); 
			out.println("		if(validate_data()){"); 

			out.println("		if(confirm(m_save_msg)){ ");
			out.println("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("   document.Form1.elements[i].disabled=false;");
			out.println("   }");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_document_applicable';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}");			
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_document_applicable';"); 
			out.println("		}"); 
			out.println("}"); 
			
		
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_document_applicable';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_document_applicable\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Documents Applicable - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Documents Applicable - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_ENTITY_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_STAGE.disabled=true;"); 
			out.println("document.Form1.TXT_ITEM_CAT_CODE.disabled=true;"); 
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
			
			out.println(" function assign_help_status(obj){");   
      out.println("    document.Form1.hid_help_status.value =obj; ");
      out.println("    }");

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
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
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
			
			out.println("function clear_data() {");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("document.Form1.TXT_CODE.value='';"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("document.Form1.TXT_ENTITY_TYPE.value='';"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("document.Form1.TXT_STAGE.value='';"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value='';"); 
	  	out.println("		}"); 
	
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
			out.println("    m_sql = \"m_help_TXT_ENTITY_TYPE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ENTITY_TYPE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_ENTITY_TYPE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_STAGE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_STAGE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_STAGE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_CAT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

		

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_CODE_sql_2\";");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_ENTITY_TYPE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_STAGE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[5];"); 
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
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
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
			out.println("    m_sql = \"m_view_TXT_CODE_sql_2\";");
			out.println("    m_criteria = \"\"+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function setContent(obj) {");
			out.println(" e_applicable_doc.innerHTML=''; ");
			out.println(" if(obj == 'CLIENT'){ ");
			out.println("e_type.innerHTML='<td width=\"35%\" >Client Type*</td>';");
			out.println("e_client_type.innerHTML='<td width=\"65%\" >'+");		
			//--MODIFIED BY : DELANJALI-------------------------------------------------------------------------------------------------------------------------
			//--date				: 2007-06-21------------------------------------------------------------------------------------------------------------------------
			//out.println("'<select name=\"TXT_CLIENT_TYPE\" class=\"txt_input\"  onChange=\"makeRequest3(this.value,document.Form1.TXT_TYPE.value)\" >'+");
			out.println("'<select name=\"TXT_CLIENT_TYPE\" class=\"txt_input\" onChange=\"makeRequest_new(document.Form1.TXT_TYPE,document.Form1.TXT_CLIENT_TYPE,document.Form1.TXT_PRODUCT_CODE)\">'+");

			rs=stmt.executeQuery(" SELECT ENTITY_CODE,DESCRIPTION  "+
													 " FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
													 " WHERE ACTIVE_STATUS='Y'  ");												
			boolean more=rs.next();
			
			while(more){
			out.println("'<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>'+");			
			more=rs.next();
			} 
			out.println("'<option value=\"\" selected > --</option>'+");			
			out.println("'</SELECT></td>';");
			out.println(" }"); 
			out.println(" else if(obj == 'ASSET' ){");
			out.println("e_type.innerHTML='<td width=\"35%\" >Asset Type *</td>';");
			out.println("e_client_type.innerHTML='<td width=\"65%\" >'+");		
			//--MODIFIED BY : DELANJALI-------------------------------------------------------------------------------------------------------------------------
			//--date				: 2007-06-21------------------------------------------------------------------------------------------------------------------------
			//out.println("'<select name=\"TXT_ASSET_TYPE\" class=\"txt_input\"  onChange=\"makeRequest4(this.value,document.Form1.TXT_TYPE.value)\" >'+");
				out.println("'<select name=\"TXT_ASSET_TYPE\" class=\"txt_input\" onChange=\"makeRequest_new(document.Form1.TXT_TYPE,document.Form1.TXT_ASSET_TYPE,document.Form1.TXT_PRODUCT_CODE)\">'+");
		
			rs1=stmt.executeQuery(" SELECT ITEM_CAT_CODE,DESCRIPTION "+
													   " FROM   "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
															 " WHERE ACTIVE_STATUS='Y'  ");		

			boolean more1=rs1.next();
			while(more1){
			out.println("'<option value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</option>'+");			
			more1=rs1.next();
			} 
			out.println("'<option value=\"\" selected > --</option>'+");			
			out.println("'</SELECT></td>';");
			out.println("}"); 
			out.println(" document.Form1.hid_doc_type.value = obj; ");
			out.println("}"); 
			
			out.println("function change_val_status_doc(row_no){")	;
			out.println("m_chk_status=\"CHK_STATUS\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("}");	
			out.println("}");	
			
			out.println("function display_applicable_doc(data_vec){");
			out.println(" var i=0; "); 
			out.println(" var line_doc=0; "); 
			out.println("  while(i<data_vec.length ) {  ");			
			out.println(" m_desc = '<td width=\"20%\">'+data_vec[i+1].replace('*','&')+'<input type=hidden name=hid_doc_code'+line_doc+' value=\"'+data_vec[i]+'\" ></td>'");
			out.println(" m_from = '<TD WIDTH=\"10%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_FROM'+line_doc+' VALUE=\"\" maxlength=\"10\" size=\"20\" STYLE=\"{text-align:right;}\" ></td>';");		
			out.println(" m_to = '<TD WIDTH=\"10%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_TO'+line_doc+' VALUE=\"\" maxlength=\"10\" size=\"20\" STYLE=\"{text-align:right;}\" ></td>';");		
			out.println(" m_status = '<TD WIDTH=\"5%\"><INPUT TYPE=\"checkbox\" NAME=CHK_STATUS'+line_doc+' VALUE=\"off\" onclick=\"change_val_status_doc('+line_doc+')\"></td>';");			

			out.println(" m_division = '<td width=\"20%\" >'+");		
			out.println("'<select name=TXT_DIVISION'+line_doc+' class=\"txt_input\"  onChange=\"\" >'+");
			rs1=stmt.executeQuery(" SELECT DIVISION_CODE,DESCRIPTION "+
													   " FROM   "+m_schema_name+".CO_CO_MAS_DIVISION ");

			 more2=rs1.next();
			while(more2){
			out.println("'<option value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</option>'+");			
			more2=rs1.next();
			} 
			out.println("'</SELECT></td>'");
			out.println(" m_write_data = '<TR>'+m_desc+m_from+m_to+m_status+m_division+'</TR>' ");
			out.println(" 		e_applicable_doc.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" 		m_write_data ");
			out.println(" 		'</table>'");
			out.println(" i=i+2; ");
			out.println(" line_doc=line_doc+1; ");
			out.println("	 }");
			out.println(" document.Form1.hid_app_doc_lineno.value=line_doc ; ");
			out.println("}"); 
			
			out.println("function val_number(obj,size){ ");
			out.println("if(!isnumberok(obj,size)){");
			out.println(" alert('Please enter a number '); ");
			out.println(" obj.value=''");
			out.println(" obj.focus();");
			out.println("}");
			out.println("}");

			out.println("function val_max_1(obj,row,pre) {");
			out.println("if(parseInt(obj.value) >parseInt(document.Form1.elements[\"TXT_TO\"+row].value)){");
			out.println(" alert(' Value cannot be greater than To Screen No ' ); ");
			out.println("document.Form1.elements[\"TXT_FROM\"+row].selectedIndex=[0]");			
			out.println("	}");
			out.println("	}");

			out.println("function val_max_2(obj,row,pre) {");
			out.println("if(parseInt(obj.value) <parseInt(document.Form1.elements[\"TXT_FROM\"+row].value)){");
			out.println(" alert(' Value cannot be less than From Screen no' ); ");
			out.println("document.Form1.elements[\"TXT_TO\"+row].selectedIndex=[0]");			
			out.println("	}");
		  out.println("}");
			
			out.println("function check_from_val(obj,obj2){ ");
			out.println(" if(parseFloat(obj.value)>parseFloat(obj2.value)){ ");
			out.println(" alert('From screen value should be less than To screen value  '); ");
			out.println(" obj.value=''");
			out.println(" obj.focus();");
			out.println(" }");
			out.println("}");
			
			
			out.println("function display_applicable_doc_fill(data_vec){");
			out.println("var m_pro=document.Form1.TXT_PRODUCT_CODE.value");
			out.println("if(data_vec[2]!='ASSET'){");
			out.println("if(data_vec[6]!=\"\" || data_vec[6]!='null'){");
			out.println("document.Form1.TXT_PRODUCT_CODE.value =data_vec[6]");
			out.println("} ");
			out.println("if(data_vec[6]==\"\" || data_vec[6]=='null'){");
			out.println("document.Form1.TXT_PRODUCT_CODE.value =m_pro");
			out.println("} ");
			out.println("} ");
			out.println("if(data_vec[2]=='ASSET'){");
		
			out.println("if(data_vec[7]!=\"\" || data_vec[7]!='null'){");
			out.println("document.Form1.TXT_PRODUCT_CODE.value =data_vec[7]");
			out.println("} ");
			out.println("if(data_vec[7]==\"\" || data_vec[7]=='null'){");
			out.println("document.Form1.TXT_PRODUCT_CODE.value =m_pro");
			out.println("} ");
			out.println("} ");
			
			out.println(" m_division4 = ''; ");
			out.println(" var i=0; "); 
			out.println(" var line_doc=0; "); 
			out.println(" e_applicable_doc.innerHTML='' ");
			out.println("		e_applicable_doc.innerHTML='<table   align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println("	'<TR><TD width=\"25%\"><B>Document Description </B></TD>'+");
			out.println("	'<TD width=\"23%\"><B>Division</B></TD>' +");
			out.println("	'<TD width=\"23%\" ><B>From Screen</B></TD>'+");
			out.println("	'<TD width=\"23%\" ><B>To Screen </B></TD>'+");
		
			out.println("	'<TD width=\"6%\" align=center><B>Status</B></TD></TR>'+");
			out.println(" 	'</table>';");
			out.println("  while(i<data_vec.length ) {  ");			
			out.println(" m_desc = '<td width=\"25%\">'+data_vec[i+1].replace('*','&')+'<input type=hidden name=hid_doc_code'+line_doc+' value=\"'+data_vec[i]+'\" ></td>';");
			out.println(" m_division_all = '<td width=\"23%\"  ><select name=TXT_DIVISION'+line_doc+' class=\"txt_input\" VALUE=\"'+data_vec[i+5]+'\" onChange=\"\"  style=\"width: 200px\" >';");
	
			rs1=stmt.executeQuery(" SELECT DIVISION_CODE,DESCRIPTION "+
													   " FROM   "+m_schema_name+".CO_CO_MAS_DIVISION ");	

			boolean more3=rs1.next();
			while(more3){
			
			out.println(" m_div_code = '"+rs1.getString(1)+"' ");
			out.println("if(data_vec[i+5] == m_div_code ) ");
			out.println("{  ");
			out.println(" m_division_all =m_division_all+ ' <option value=\""+rs1.getString(1)+"\" selected >"+rs1.getString(2)+"</option>';");			
			out.println("} "); 
			out.println("else {	");
			out.println(" m_division_all =m_division_all+'<option value=\""+rs1.getString(1)+"\" >"+rs1.getString(2)+"</option>';");			
			out.println("} "); 
					
			more3=rs1.next();
			} 
			
			out.println("m_division_all = m_division_all+'</SELECT></td>';");
			
			 
			out.println("m_from='<td width=\"23%\" ><select class=\"txt_input\" type=\"text\" name=TXT_FROM'+line_doc+' maxlength=1 style=\"width: 200px\" onChange=\"val_max_1(this,'+line_doc+','+data_vec[i+3]+')\">';");  
			rs2 = stmt1.executeQuery ("SELECT "+
   														 	"DISPLAY_NAME,POSITION,SCREEN_NAME "+
																"FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
																"WHERE POSITION IS NOT NULL "+
																"AND DIVISION_CODE='AF' "+
																"ORDER BY POSITION ASC");
																
			boolean more5 = rs2.next();		
			
			while(more5){
			out.println("if(data_vec[i+3]=='"+rs2.getString(2)+"'){");
			out.println("m_from=m_from+'<option value="+rs2.getString(2)+" selected>"+rs2.getString(1)+"</option>';");
			out.println("}");
			out.println("else if(data_vec[i+3]!='"+rs2.getString(2)+"'){");
			out.println("m_from=m_from+'<option value="+rs2.getString(2)+" >"+rs2.getString(1)+"</option>';");
			out.println("}");
		
			more5=rs2.next();
			}
			
			out.println("m_from=m_from+'</select>';");
			out.println("m_to='<td width=\"23%\" ><select class=\"txt_input\" type=\"text\" name=TXT_TO'+line_doc+' maxlength=1 style=\"width: 200px\" onChange=\"val_max_2(this,'+line_doc+','+data_vec[i+4]+')\" >';");  

			rs2 = stmt1.executeQuery ("SELECT "+
   														 	"DISPLAY_NAME,POSITION,SCREEN_NAME "+
																"FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
																"WHERE POSITION IS NOT NULL "+
																"ORDER BY POSITION ASC");

			  more5 = rs2.next();		
			while(more5){
			out.println("if(data_vec[i+4]=='"+rs2.getString(2)+"'){");
			out.println("m_to=m_to+'<option value="+rs2.getString(2)+" selected>"+rs2.getString(1)+"</option>';");
			out.println("}");
			out.println("else if(data_vec[i+4]!='"+rs2.getString(2)+"'){");
			out.println("m_to=m_to+'<option value="+rs2.getString(2)+" >"+rs2.getString(1)+"</option>';");
			out.println("}");

			more5=rs2.next();
			}
			
			out.println("m_to=m_to+'</select>';");
			
			out.println("if(data_vec[i+2]!='ASSET'){");
			
			//out.println(" if(data_vec[i+2] == document.Form1.TXT_CLIENT_TYPE.value  ){ ");	// commented by udara 06-08-2015
			out.println(" if((data_vec[i+2] == document.Form1.TXT_CLIENT_TYPE.value) && (data_vec[i+8]=='Y')  ){ "); // added by udara 06-08-2015
			out.println(" m_status = '<TD WIDTH=\"6%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_STATUS'+line_doc+' VALUE=\"on\" onclick=\"change_val_status_doc('+line_doc+')\" checked ></td>';");			
			out.println("	}");
			out.println(" else { ");
			out.println(" m_status = '<TD WIDTH=\"6%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_STATUS'+line_doc+' VALUE=\"off\" onclick=\"change_val_status_doc('+line_doc+')\"  ></td>';");			
			out.println("	}");
			out.println("	}");
			out.println("else if(data_vec[i+2]=='ASSET'){");
			
			//out.println("   alert(data_vec[i+2] + '   ' + data_vec[i+6] + '    ' + document.Form1.TXT_ASSET_TYPE.value);   "); // test
			
			//out.println(" if(data_vec[i+6] == document.Form1.TXT_ASSET_TYPE.value  ){ ");	// commented by udara 08-06-2015
			out.println(" if((data_vec[i+6] == document.Form1.TXT_ASSET_TYPE.value) && (data_vec[i+8]=='Y')  ){ ");	// added by udara 08-06-2015
			out.println(" m_status = '<TD WIDTH=\"6%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_STATUS'+line_doc+' VALUE=\"on\" onclick=\"change_val_status_doc('+line_doc+')\" checked ></td>';");			
			out.println("	}");
			out.println(" else { ");
			out.println(" m_status = '<TD WIDTH=\"6%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_STATUS'+line_doc+' VALUE=\"off\" onclick=\"change_val_status_doc('+line_doc+')\"  ></td>';");			
			out.println("	}");
			out.println("	}");
			out.println(" m_write_data = '<TR>'+m_desc+m_division_all+m_from+m_to+m_status+'</TR>' ");
			out.println(" 		e_applicable_doc.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" 		m_write_data ");
			out.println(" 		'</table>'");
			//out.println(" i=i+8; "); // commented by udara 06-08-2015
			out.println(" i=i+9; "); // added by udara 06-08-2015
			out.println(" line_doc=line_doc+1; ");
			out.println("	 }");
		
			out.println(" ");
			out.println(" document.Form1.hid_app_doc_lineno.value=line_doc ; ");
			out.println("}"); 
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New'),setContent('CLIENT')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_doc_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_stat' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_doc_lineno' VALUE=\"0\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_product' VALUE=\"\">");
				
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Documents Applicable</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
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


			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr >"); 
			out.println("<td width='35%' ><DIV id='DIV_TXT_CODE'  class=div_input>Document Type *</DIV></td>"); 
			out.println("<td width='65%' > ");
			out.println("<select name=\"TXT_TYPE\" class=\"txt_input\" onChange=\"setContent(document.Form1.TXT_TYPE.value)\"  >");
			out.println("<OPTION value=\"CLIENT\">Client </option>");
			out.println("<OPTION value=\"ASSET\">Asset </option>");
			out.println("</SELECT></td>");
			out.println("</tr>"); 			


			out.println("<tr>" );
			out.println("<td width='35%' ><DIV ID=e_type></DIV>&nbsp</td>");				
		  out.println("<td width='65%' ><DIV ID=e_client_type></DIV></td>");				
			out.println("</tr>" );
		
			out.println("<tr >"); 
			out.println("<td width='35%'><DIV id='DIV_TXT_PRO_CODE'  class=div_input>Product Code*</DIV></td>"); 
			out.println("<td width='65%'>");
			out.println("<select name=\"TXT_PRODUCT_CODE\" class=\"txt_input\" onChange=\"makeRequest_new(document.Form1.TXT_TYPE,'',document.Form1.TXT_PRODUCT_CODE)\">");
			
							
			rs = stmt.executeQuery (" SELECT "+
													    " TRAN_CODE, "+
													    " INITCAP(DESCRIPTION) "+
  														" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE ");
			more = rs.next();		
			while(more){
			out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
			more=rs.next();
		
			if(!more){
			break;
			}	}
			out.println("</SELECT></td>");
			out.println("</tr>"); 		
	    out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_applicable_doc></DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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
