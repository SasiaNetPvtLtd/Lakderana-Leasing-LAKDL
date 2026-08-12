//--
//SCREEN NAME	:SYSTEM ADMINISTRATION - MAINTENANCE RATE
//MODIFED BY	:DELANJALI
//DATE/TIME		:16-01-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_maintenance_rate extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1,rs2;
	Statement stmt,stmt1;
	Connection conn;
	String reqstr;
	String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;

			m_chksql=req.getParameter("chksql");
			
			 if(m_chksql.equals("main_page")){
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Maintenance Rate</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			out.println("var b_flag1=0");

			out.println("function get_vector_normal(http_response) {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" sub_charge_details.innerHTML = ''; ");
			out.println(" sub_charge_details.innerHTML = http_response; ");
			out.println(" ");
  		out.println("}");
			out.println("}");
			
			out.println("function makeRequest_sub_charges() {");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_maintenance_rate?chksql=sub_charge_details&m_make=\"+document.Form1.TXT_MAKE_CODE.value+\"&m_sub_model=\"+document.Form1.TXT_SUB_MODEL_CODE.value+\"&m_mileage=\"+document.Form1.TXT_MILEAGE_CODE.value+\"\";");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_maintenance_rate?chksql=sub_charge_details&item_category=\"+document.Form1.TXT_ITEM_CAT_CODE.value+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function chk_rate() {");
			out.println("var m_length=0;");
			out.println("var m_size=0;");
			out.println("var inputStr;");
			out.println("inputStr=document.Form1.TXT_AMOUNT.value;");
			out.println("m_length=inputStr.length;");
			out.println("for (var e = 0; e < m_length; e++)"); 
			out.println("{");
			out.println("if(isNaN(inputStr))");
			out.println("{");
			out.println("alert('Entered Amount is wrong....!');");
			out.println("document.Form1.TXT_AMOUNT.value='';");
		 	out.println("document.Form1.TXT_AMOUNT.focus();");
			out.println("return false;");
			out.println("e=inputStr.length;");
			out.println("break;");
			out.println("}");
			out.println("}");
			out.println("			}");		

			out.println("function get_vector(data_vec) {");
		  out.println("if(data_vec.length==0 && document.Form1.hid_val.value=='t1' && document.Form1.TXT_MAKE_CODE.value!=''){");
			out.println("help_update()");
			out.println("	}");
			out.println("if(data_vec.length==0 && document.Form1.hid_val.value=='t2' && document.Form1.TXT_SUB_MODEL_CODE.value!=''){");
			out.println(" sub_charge_details.innerHTML = ''; ");
			out.println("help_button_2()");
			out.println("	}");
			out.println("if(data_vec.length==0 && document.Form1.hid_val.value=='t3' && document.Form1.TXT_CHARGE_SUB_CODE.value!=''){");
			out.println("	}");
			out.println("if(data_vec.length==0 && document.Form1.hid_val.value=='t4' && document.Form1.TXT_MILEAGE_CODE.value!=''){");
			out.println("help_button_4()");
			out.println("	}");
			
			out.println("else if(data_vec.length==0 && document.Form1.hid_val.value=='t5' && document.Form1.TXT_ITEM_CAT_CODE.value!=''){");
			out.println("help_item_category()");
			out.println("	}");
			
			out.println("}");
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");
			out.println("}");

			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_val.value=='t1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_maintenance_rate&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println("if(document.Form1.hid_val.value=='t2'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_model&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println("else if(document.Form1.hid_val.value=='t3'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_charge&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println("else if(document.Form1.hid_val.value=='t4'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_mileage&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("}");
			out.println("else if(document.Form1.hid_val.value=='t5' ){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_applicable_charges2&data_val=\"+document.Form1.TXT_ITEM_CAT_CODE.value+\"&ac_status=Y\";");
			out.println("}");

			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			//comment by nuwan de silva on 10-12-2007-----------------------
			/*out.println("if(document.Form1.TXT_MAKE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_MAKE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SUB_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			*/
			//added by nuwan de silva on 10-12-2007---------------------------
			out.println("if(document.Form1.TXT_ITEM_CAT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_CAT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 



out.println("function check_for_null(){");
			out.println("for(var g=0;g<document.Form1.hid_count.value;g++){");
			out.println("if(document.Form1.elements[\"TXT_INCREASE_DECREASE_\"+g].value==\"\"){");
			out.println("b_flag=0");
out.println("break");
			out.println("}");
			out.println("else{");
			out.println("b_flag=1");
		out.println("break");
			out.println("}");
			
			out.println("if(document.Form1.elements[\"TXT_AMOUNT_\"+g].value==\"\"){");
			out.println("b_flag1=0");
			out.println("break");
			out.println("}");
						out.println("else{");
			out.println("b_flag1=1");
			out.println("break");
			out.println("}");
			//out.println("if(b_flag==\"0\" && b_flag1==\"0\"){");
			//out.println("break");
			//out.println("}");
			
				out.println("}");
			out.println("}");
			
			
			out.println("function before_submit(){ "); 

			//out.println("check_for_null()");
			//out.println("if(b_flag==\"1\" && b_flag1==\"1\"){");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
			out.println("		if(validate_data()){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_maintenance_rate?number='+document.Form1.hid_count.value+'';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}");
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
					//	out.println("		}");
		//	out.println("else{");
			//out.println("alert(\"Please Amounts\");");
			//out.println("} "); 


			out.println("} "); 

			out.println("function load_lock(){	"); 
		//	out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_maintenance_rate?chksql=main_page';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_maintenance_rate?chksql=main_page';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_maintenance_rate\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Maintenance Rate - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Maintenance Rate - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CHARGE_SUB_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MILEAGE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_INCREASE_DECREASE.disabled=true;"); 
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 
			out.println("document.Form1.BUT_SUB_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_CHARGE_SUB_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_MILEAGE_CODE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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
			
			out.println("if(oBj.valout[2]==' '){");
			out.println("document.Form1.TXT_MAKE_CODE.value=\"\"");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value=\"\"");
			out.println("document.Form1.TXT_MILEAGE_CODE.value=\"\"");
			out.println(" sub_charge_details.innerHTML = ''; ");
			out.println("}");

			
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
			
			out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("		help_value_assign_item_category();"); 
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
			out.println("document.Form1.TXT_MAKE_CODE.value=\"\"");
			out.println("document.Form1.TXT_MAKE_CODE.focus()");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value=\"\"");
			out.println("document.Form1.TXT_MILEAGE_CODE.value=\"\"");
			out.println(" sub_charge_details.innerHTML = ''; ");
	  	out.println("		}");
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value=\"\"");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.focus()");
			out.println(" sub_charge_details.innerHTML = ''; ");
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("document.Form1.TXT_MILEAGE_CODE.value=\"\"");
			out.println("document.Form1.TXT_MILEAGE_CODE.focus()");
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
			out.println("    m_sql = \"m_help_TXT_MAKE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_MAKE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_MAKE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";");
			out.println("    m_sql = \"m_help_TXT_SUB_MODEL_CODE_sql_new\";");
			out.println("    m_criteria = document.Form1.TXT_MAKE_CODE.value+\"@\"+document.Form1.TXT_SUB_MODEL_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_SUB_MODEL_DESC.value=oBj.valout[4];");
			out.println("makeRequest_sub_charges()");
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";");
			out.println("    m_sql = \"m_help_TXT_CHARGE_SUB_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CHARGE_SUB_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_CHARGE_SUB_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_MILEAGE_CODE_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_MODEL_CODE.value+\"@\"+document.Form1.TXT_MILEAGE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','3');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_MILEAGE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			
		  out.println("function help_item_category() {");
			out.println(" sub_charge_details.innerHTML = ''; ");
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			//out.println("    document.Form1.hid_help_type.value=\"99\";");
			//out.println("  help_Item_Sub();");
			//out.println("    } ");
			//out.println("    else{");
			//out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			//out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql_1\";"); 
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			//out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			//out.println("    } ");
			//out.println("    else{");
			//out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"N@\";}"); 
			
			out.println("    document.Form1.hid_help_type.value=\"8\";");
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_ITEM_CAT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			//out.println("}"); 
			out.println("}");
			
			
			out.println("function help_value_assign_item_category() {"); 
			out.println("document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[2];"); 
			//out.println("makeRequest_item(document.Form1.TXT_ITEM_CAT_CODE.value)");
			out.println("makeRequest_sub_charges()");
			out.println("}");
			     


			/*out.println("function help_update() {"); 
			out.println(" sub_charge_details.innerHTML = ''; ");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\");{");
			out.println("    m_sql = \"m_help_TXT_MAKE_CODE_sql\";"); 
			out.println("}");
			out.println("if((document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")||(document.Form1.SCREEN_NAME.value==\"RACT\")){");
			out.println("    m_sql = \"m_help_TXT_MAIN_CODE_sql\";"); 
			out.println("}");
			out.println("    m_criteria = document.Form1.TXT_MAKE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','1');"); 
			out.println("}"); 
      */
			
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\");{");
			out.println("    document.Form1.TXT_MAKE_CODE.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_MAKE_DESC.value=oBj.valout[3];");
			out.println("}");
			out.println("if((document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")||(document.Form1.SCREEN_NAME.value==\"RACT\")){");
			out.println("    document.Form1.TXT_MAKE_CODE.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CHARGE_SUB_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_MILEAGE_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_INCREASE_DECREASE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[8];"); 
			out.println("}");
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
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:47em; dialogHeight:25em; center:yes; status:no\");"); 
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
			out.println("    m_sql = \"m_view_TXT_MAIN_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_MAKE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function change(row_no){")	;
			out.println("m_chk_status=\"CHK_STATUS_\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("}");	
			out.println("}");	
			
			out.println("function check_amt(row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"TXT_AMOUNT_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("if(document.Form1.elements[nt].value!=\"\"){");
			out.println("format_number(document.Form1.elements[nt],22)");
			out.println("}");
			out.println("else if(document.Form1.elements[nt].value==\"\"){");
			out.println("alert('Please enter Amount');");
			out.println("document.Form1.elements[nt].value=\"\""); 
			out.println("}");
			out.println("}");
			
			out.println("function check_inc(row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"TXT_INCREASE_DECREASE_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("if(document.Form1.elements[nt].value!=\"\" ){");
			out.println("format_number(document.Form1.elements[nt],7)");
			out.println("}");
			out.println("else if(document.Form1.elements[nt].value==\"\" ){");
			out.println("alert('Please enter Increase and Decrease');");
			out.println("document.Form1.elements[nt].value=\"\""); 
			out.println("}");
			out.println("}");

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_model_code' VALUE=\"\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Maintenance Rate</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
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


			out.println("<table align='center' width='100%' class='table'>"); 

			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MAKE_CODE'  class=div_input>Make Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MAKE_CODE' maxlength='10' size='10' onblur=\"assig('t1'),makeRequest(document.Form1.TXT_MAKE_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MAKE_DESC'  class=div_input>Make Description</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MAKE_DESC' maxlength='10' size='10' >"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_MODEL_CODE'  class=div_input>Sub Model Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_CODE' maxlength='20' size='20' onblur=\"assig('t2'),makeRequest_sub_charges(),makeRequest(document.Form1.TXT_SUB_MODEL_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_SUB_MODEL_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_MODEL_DESC'  class=div_input>Sub Model Description </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_DESC' maxlength='20' size='20' disabled >"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>"); 
			out.println("<td width='30%' >Mileage Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MILEAGE_CODE' maxlength='10' size='10' onblur=\"assig('t4'),makeRequest(document.Form1.TXT_MILEAGE_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_MILEAGE_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ITEM_CAT_CODE'  class=div_input>Item Category *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ITEM_CAT_CODE' maxlength='10' size='10'  onblur=\"assig('t5'),makeRequest(this)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_item_category()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


			
			out.println("</table>"); 
			
			
			out.println("<br>"); 
						
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=sub_charge_details></div></td></tr></table>");

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
	//*****************************************************************************************
	
	
		else if(m_chksql.equals("sub_charge_details")){		
			int i=0;		
			int x=0;	
			
			//comment by nuwan de silva on 10-12-2007 --------------------------------------------------------
			/*String m_make = req.getParameter("m_make");	
			String m_sub_model= req.getParameter("m_sub_model");	
			String m_mileage=req.getParameter("m_mileage");	
		
		  rs = stmt.executeQuery ("SELECT CHARGE_SUB_CODE, "+
		 "(select TYPE_CODE from "+m_schema_name+".AF_CO_MAS_SUB_CHARGES where SUB_TYPE_CODE=CHARGE_SUB_CODE), "+
		 "(select initcap(DESCRIPTION) from "+m_schema_name+".AF_CO_MAS_SUB_CHARGES where SUB_TYPE_CODE=CHARGE_SUB_CODE), "+
		 "MILEAGE_CODE, "+
		 "to_char(nvl(INCREASE_DECREASE,0),'999,999,999,999,999.99'), "+
		 "to_char(nvl(amount,0),'999,999,999,999,999.99'),a.default_value "+
		 "FROM "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE a,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES b "+
		 "WHERE SUB_TYPE_CODE=CHARGE_SUB_CODE "+
		 "AND UPPER(MAKE_CODE)=UPPER('"+m_make+"') "+
		 "AND UPPER(SUB_MODEL_CODE)=UPPER('"+m_sub_model+"') "+
		 "UNION  "+
		 "SELECT SUB_TYPE_CODE,TYPE_CODE,initcap(DESCRIPTION),'','','','-' "+
		 "FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
		 "where TYPE_CODE='MAINTENANC' "+
		 "AND SUB_TYPE_CODE NOT IN (SELECT CHARGE_SUB_CODE "+
		 "FROM "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A "+
		 "WHERE UPPER(MAKE_CODE)=UPPER('"+m_make+"') "+
		 "AND UPPER(SUB_MODEL_CODE)=UPPER('"+m_sub_model+"')) ");
	  */
		
			
			String _m_item_category= req.getParameter("item_category");	
					
		  rs = stmt.executeQuery ("SELECT CHARGE_SUB_CODE, "+
		 "(select TYPE_CODE from "+m_schema_name+".AF_CO_MAS_SUB_CHARGES where SUB_TYPE_CODE=CHARGE_SUB_CODE), "+
		 "(select initcap(DESCRIPTION) from "+m_schema_name+".AF_CO_MAS_SUB_CHARGES where SUB_TYPE_CODE=CHARGE_SUB_CODE), "+
		 "MILEAGE_CODE, "+
		 "to_char(nvl(INCREASE_DECREASE,0),'999,999,999,999,999.99'), "+
		 "to_char(nvl(amount,0),'999,999,999,999,999.99'),a.default_value "+
		 "FROM "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE a,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES b "+
		 "WHERE SUB_TYPE_CODE=CHARGE_SUB_CODE "+
		 /*"AND UPPER(MAKE_CODE)=UPPER('"+m_make+"') "+*/
		 "AND UPPER(ITEM_CAT_CODE)=UPPER('"+_m_item_category+"') "+
			
		 "UNION  "+
			
		 "SELECT SUB_TYPE_CODE,TYPE_CODE,initcap(DESCRIPTION),'','','','-' "+
		 "FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
		 "where TYPE_CODE='MAINTENANC' "+
		 "AND SUB_TYPE_CODE NOT IN (SELECT CHARGE_SUB_CODE "+
		 "FROM "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A "+
		 "WHERE UPPER(ITEM_CAT_CODE)=UPPER('"+_m_item_category+"')) ");
	  
		
	
		boolean more = rs.next();

			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
			out.println("<br>");			
	

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='25%' >Sub Charges Code </td>"); 
			out.println("<td width='20%' align='right'>Increase Decrease </td>"); 
			out.println("<td width='20%' align='right'>Amount </td>"); 
			out.println("<td width='20%' align='center'>Default Value </td>");
			out.println("<td width='5%' align='center'>Status</td>");
			

			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
	
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");

		  int j = 0; 
			while(more){
			String m_sub=rs.getString(3);
			
			if(m_sub==null){
			m_sub="";
			}
			out.println("<tr >"); 
			out.println("<td width='25%' >"+m_sub+"<input class='txt_input' style='{text-align:right}' type='hidden' name=TXT_TYPE_"+j+" maxlength='22'  value=\""+rs.getString(2).trim()+"\" ></td>");
			if(rs.getString(5)!=null){
			out.println("<td width='20%' align='right'><input class='txt_input' style='{text-align:right}' type='text' name=TXT_INCREASE_DECREASE_"+j+" maxlength='3'  value=\""+rs.getString(5).trim()+"\" onblur=\"check_inc("+j+")\"></td>"); 

	
			}
			else if(rs.getString(5)==null){
			out.println("<td width='20%' align='right'><input class='txt_input' style='{text-align:right}' type='text' name=TXT_INCREASE_DECREASE_"+j+" maxlength='3'  value=\"\" onblur=\"check_inc("+j+")\"></td>"); 
			}
			
			if(rs.getString(6)!=null){
			out.println("<td width='20%' align='right'><input class='txt_input' style='{text-align:right}' type='text' name=TXT_AMOUNT_"+j+" maxlength='20' value=\""+rs.getString(6).trim()+"\" onblur=\"check_amt("+j+")\"></td>"); 

			}
			else if(rs.getString(6)==null){

			out.println("<td width='20%' align='right'><input class='txt_input' style='{text-align:right}' type='text' name=TXT_AMOUNT_"+j+" maxlength='20'  value=\"\" onblur=\"check_amt("+j+")\"></td>"); 
			}
			out.println("<td width='20%' align='center'><select class='txt_input' type='text' name=TXT_DEFAULT_VALUE_"+j+" maxlength='1' size='1'>");
			
			String m_default=rs.getString(7);
			
			if(m_default==null){
			m_default="N";
			}
			if(m_default.equals("Y")){
			out.println("<option value='N' > No </option>");
			out.println("<option value='Y' selected> Yes </option>");


			}
			
			else if(m_default.equals("N")){
			out.println("<option value='N' selected> No </option>");
			out.println("<option value='Y' > Yes </option>");


			}
		
			else {
			out.println("<option value='N' selected> No </option>");
			out.println("<option value='Y' > Yes </option>");

			}
	

			out.println("</select>");

			out.println("</td>");
			
			
			if(rs.getString(5)==null){
			out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=CHK_STATUS_"+j+" value=\"off\" unchecked onclick=\"change("+j+")\"></td>");
			}
			else if(rs.getString(5)!=null){
			out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=CHK_STATUS_"+j+" value=\"on\" checked onclick=\"change("+j+")\"></td>");
			}
			out.println("<input class='txt_input' type='hidden' name='TXT_SUB_TYPE_CODE_"+j+"' maxlength='10' size='10'  value=\""+rs.getString(1)+"\">");

			out.println("</tr >"); 
			more=rs.next();
			j=j+1;
			/*if (!more)
			{
			break;
			}*/
			
			}	
			out.println("</table>");
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
		

}


		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			
			
			
			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
