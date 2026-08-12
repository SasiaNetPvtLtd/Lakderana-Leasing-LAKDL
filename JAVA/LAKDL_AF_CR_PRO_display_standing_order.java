//--
//SCREEN NAME	:Credit Process -STANDING ORDERS
//CREATED BY	:delanjali
//DATE/TIME		:
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_display_standing_order extends javax.servlet.http.HttpServlet { 

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
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();

			String m_schema_name = m_sn_methods.schema_name;
		  String m_chksql=req.getParameter("chksql");

			if(m_chksql.equals("main_page")){
			
	    String  m_type=req.getParameter("type");
			
			if(m_type.equals("")){
				m_type="NEW";
			}


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit - Standing Order Entry</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_type_1='"+m_type+"'");
			
			out.println("function get_vector(data_vec) {");
			out.println("			 if(data_vec.length==0 && document.Form1.TXT_CLIENT_CODE.value!=\"\"   && document.Form1.hid_text.value==\"J1\"){");
			
			out.println("		help_button_2();");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.TXT_LEASE_NO.value!=\"\"  && document.Form1.hid_text.value==\"J2\"){");
			out.println("		help_button_1();");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.TXT_VEHICLE_NO.value!=\"\"  && document.Form1.hid_text.value==\"J3\"){");
			out.println("		help_button_3();");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.TXT_ACC_NO.value!=\"\"  && document.Form1.hid_text.value==\"J4\"){");
			out.println("		help_button_5();");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && m_type_1==\"EDIT\" && document.Form1.TXT_SO_NO.value!=\"\"  && document.Form1.hid_text.value==\"J5\"){");
			out.println("		help_button_4();");
			out.println("			}");
			out.println("			else if(data_vec.length>0 && m_type_1==\"EDIT\" && document.Form1.TXT_SO_NO.value!=\"\"  && document.Form1.hid_text.value==\"J5\"){");
			out.println("document.Form1.TXT_SO_NO.value=data_vec[0]");
			out.println("document.Form1.TXT_LEASE_NO.value=data_vec[1]");
			out.println("document.Form1.TXT_APPLICATION_NO.value=data_vec[2]");
			out.println("document.Form1.TXT_CLIENT_CODE.value=data_vec[3]");
			out.println("document.Form1.TXT_VEHICLE_NO.value=data_vec[4]");
			out.println("document.Form1.TXT_END_DATE_DD.value=data_vec[5].substring(0,2)");
			out.println("document.Form1.TXT_END_DATE_MM.value=data_vec[5].substring(3,5)");
			out.println("document.Form1.TXT_END_DATE_YY.value=data_vec[5].substring(6,10)");
			out.println("document.Form1.TXT_START_DATE_DD.value=data_vec[6].substring(0,2)");
			out.println("document.Form1.TXT_START_DATE_MM.value=data_vec[6].substring(3,5)");
			out.println("document.Form1.TXT_START_DATE_YY.value=data_vec[6].substring(6,10)");
			out.println("document.Form1.TXT_ACC_NO.value=data_vec[7];"); 
			out.println("document.Form1.TXT_BANK_CODE.value=data_vec[8];"); 
			out.println("document.Form1.TXT_AMOUNT.value=data_vec[9]");
			out.println("document.Form1.TXT_BANK_NAME.value=data_vec[10]");
			out.println("load_details()");
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.hid_text.value==\"J6\"){");
			out.println("document.Form1.TXT_END_DATE_DD.value=data_vec[0]");
			out.println("document.Form1.TXT_END_DATE_MM.value=data_vec[1]");
			out.println("document.Form1.TXT_END_DATE_YY.value=data_vec[2]");
			out.println("document.Form1.TXT_START_DATE_DD.value=data_vec[0]");
			out.println("document.Form1.TXT_START_DATE_MM.value=data_vec[1]");
			out.println("document.Form1.TXT_START_DATE_YY.value=data_vec[2]");
			out.println("			}");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order_client&ac_status=ACTIVATED&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_vehicle(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application_reg&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function get_vector_normal(http_response) {");
			out.println(" details.innerHTML = ''; ");
			out.println(" details.innerHTML = http_response; ");
			out.println(" ");
			out.println("}");
			
			out.println("function load_details() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_standing_order?chksql=details&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"&CLIENT_NO=\"+document.Form1.TXT_CLIENT_CODE.value+\"&LEASE_NO=\"+document.Form1.TXT_LEASE_NO.value+\"&REGI_NO=\"+document.Form1.TXT_VEHICLE_NO.value+\"\";"); 
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			if(m_type.equals("EDIT")){
			out.println("if(document.Form1.TXT_SO_NO.value==\"\"){  "); 
			out.println("DIV_TXT_SO_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LEASE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_LEASE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			}
			else{
			out.println("if(document.Form1.TXT_LEASE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_LEASE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			}
			out.println("else if(document.Form1.TXT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			//added by Chatura Jayawardena 2009-10-20
			out.println("function check_date(){	"); 
			out.println(" start_date = new Date(document.Form1.TXT_START_DATE_YY.value,document.Form1.TXT_START_DATE_MM.value-1,document.Form1.TXT_START_DATE_DD.value)");
			out.println(" end_date = new Date(document.Form1.TXT_END_DATE_YY.value,document.Form1.TXT_END_DATE_MM.value-1,document.Form1.TXT_END_DATE_DD.value)");
			out.println("if(start_date<=end_date){"); 
		  out.println("return true;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("alert('End Date should be greater than Start Date');"); 
			out.println("return false"); 
			out.println("}");  
			out.println("}"); 
		
			

			out.println("function before_submit(){ ");  
		  out.println("		if(validate_data() ){"); 
			out.println("		if(check_date() ){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_save_standing_order?type="+m_type+"';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}");
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			if(m_type.equals("EDIT")){
			out.println("disable()");
			}
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_standing_order?chksql=main_page&type="+m_type+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_standing_order?chksql=main_page&type=NEW';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function edit_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_standing_order?chksql=main_page&type=EDIT';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_display_standing_order\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit - Standing Order Entry - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit - Standing Order Entry - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_COLOUR.disabled=true;"); 
			out.println("document.Form1.TXT_SEATING_CAPACITY.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_INSURANCE_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_REVENUE_LICENSE_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_LUXURY_TAX_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_DISTRICT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_BRANCH_ID.disabled=true;"); 
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FUEL_CONVERTION_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("edit_window();"); 

			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("disable()");

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
			out.println(" clear(); ");
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
			out.println("   if(document.Form1.hid_help_type.value==\"1\"){;"); 
			out.println("document.Form1.TXT_LEASE_NO.value=\"\"");
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\"");
			out.println(" details.innerHTML = ''; ");
			out.println("}");
			out.println(" else  if(document.Form1.hid_help_type.value==\"2\"){;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value=\"\"");
			out.println("document.Form1.TXT_LEASE_NO.value=\"\"");
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\"");
			out.println(" details.innerHTML = ''; ");
			out.println("}");
			out.println(" else  if(document.Form1.hid_help_type.value==\"3\"){;");
			
			out.println("document.Form1.TXT_VEHICLE_NO.value=\"\"");
			out.println("document.Form1.TXT_LEASE_NO.value=\"\"");
			//modified by madhawa 2009-10-20 add clear text field Vehicle No
			out.println("document.Form1.TXT_CLIENT_CODE.value=\"\"");
			//modified by madhawa 2009-10-20 add clear text field Vehicle No
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\"");
			out.println(" details.innerHTML = ''; ");
			out.println("}");
			out.println(" else  if(document.Form1.hid_help_type.value==\"5\"){;"); 
			out.println("document.Form1.TXT_ACC_NO.value=\"\"");
			out.println("document.Form1.TXT_BANK_CODE.value=\"\"");
			out.println("}");
			out.println("}");

			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"FinanceSql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LEASE_NO.value+\"@\"+\"ACTIVATED@\";");
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_1(oBj) {"); 
			out.println("    document.Form1.TXT_LEASE_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];");
			out.println("load_details()");
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_app\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println(" if(oBj.valout[7]=='null' || oBj.valout[7]==\"\"){");
			out.println(" oBj.valout[7]=\"\"");
			out.println(" }");
			out.println(" if(oBj.valout[4]=='null' || oBj.valout[4]==\"\"){");
			out.println(" oBj.valout[4]=\"\"");
			out.println(" }");
			out.println("    document.Form1.TXT_LEASE_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[4];"); 
			out.println("load_details()");
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_INVOICE_NO_app_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VEHICLE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[2];"); 
			out.println(" if(oBj.valout[5]=='null' || oBj.valout[5]==\"\"){");
			out.println(" oBj.valout[5]=\"\"");
			out.println(" }");
			out.println(" if(oBj.valout[4]=='null' || oBj.valout[4]==\"\"){");
			out.println(" oBj.valout[4]=\"\"");
			out.println(" }");
			out.println("    document.Form1.TXT_LEASE_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[4];"); 
			out.println("load_details()");
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_ACC_sql\";"); 
			out.println("    m_criteria =document.Form1.TXT_ACC_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5(oBj) {"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_BANK_NAME.value=oBj.valout[6];");
			out.println("}"); 
			
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_SO_NO_sql\";"); 
			out.println("    m_criteria =document.Form1.TXT_SO_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
		  out.println("}"); 
			out.println(""); 


			out.println("function help_value_assign_4(oBj) {");
			out.println("document.Form1.TXT_SO_NO.value=oBj.valout[2]");
			out.println("document.Form1.TXT_LEASE_NO.value=oBj.valout[3]");
			out.println("document.Form1.TXT_APPLICATION_NO.value=oBj.valout[4]");
			out.println("document.Form1.TXT_CLIENT_CODE.value=oBj.valout[5]");
			out.println("document.Form1.TXT_VEHICLE_NO.value=oBj.valout[6]");
			out.println("document.Form1.TXT_END_DATE_DD.value=oBj.valout[7].substring(0,2)");
			out.println("document.Form1.TXT_END_DATE_MM.value=oBj.valout[7].substring(3,5)");
			out.println("document.Form1.TXT_END_DATE_YY.value=oBj.valout[7].substring(6,10)");
			out.println("document.Form1.TXT_START_DATE_DD.value=oBj.valout[8].substring(0,2)");
			out.println("document.Form1.TXT_START_DATE_MM.value=oBj.valout[8].substring(3,5)");
			out.println("document.Form1.TXT_START_DATE_YY.value=oBj.valout[8].substring(6,10)");
			out.println("document.Form1.TXT_ACC_NO.value=oBj.valout[9];"); 
			out.println("document.Form1.TXT_BANK_CODE.value=oBj.valout[10];"); 
			out.println("document.Form1.TXT_AMOUNT.value=oBj.valout[11]");
			
			//modified by nuwan de silva on 20-08-07---------------------------
			out.println("if(oBj.valout[12]=='null' || oBj.valout[12]=='-' ){"); 
			out.println("oBj.valout[12]='';");
			out.println("}"); 
			out.println("document.Form1.TXT_BANK_NAME.value=oBj.valout[12]");
			//-----------------------------------------------------------------
			
			out.println("load_details()");
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
			out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_SEATING_CAPACITY.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_INSURANCE_DATE.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_REVENUE_LICENSE_DATE.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_LUXURY_TAX_DATE.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_DISTRICT_CODE.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_BRANCH_ID.value=oBj.valout[20];"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[21];"); 
			out.println("    document.Form1.TXT_FUEL_CONVERTION_STATUS.value=oBj.valout[22];"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[23];"); 
			out.println("}"); 

			out.println("function disable(){");
			out.println("document.Form1.TXT_LEASE_NO.disabled=true");
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true");
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true");
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true");
			out.println("document.Form1.TXT_END_DATE_DD.disabled=true");
			out.println("document.Form1.TXT_END_DATE_MM.disabled=true");
			out.println("document.Form1.TXT_END_DATE_YY.disabled=true");
			out.println("document.Form1.TXT_START_DATE_DD.disabled=true");
			out.println("document.Form1.TXT_START_DATE_MM.disabled=true");
			out.println("document.Form1.TXT_START_DATE_YY.disabled=true");
			out.println("document.Form1.TXT_ACC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_BANK_CODE.disabled=true;"); 
			//modified by madhawa 2009-10-20 enable amount text field
			//out.println("document.Form1.TXT_AMOUNT.disabled=true");
			//end modified by madhawa 2009-10-20
			out.println("load_details()");
			out.println("}");

			out.println("function clear_app(){");
			out.println("if(document.Form1.TXT_LEASE_NO.value==\"\"){");
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\"");
			out.println("}");
			out.println("}");
			
			out.println("function check_date_end(row){ ");
			out.println("  checkMonthLength(document.Form1.TXT_END_DATE_DD,document.Form1.TXT_END_DATE_MM,document.Form1.TXT_END_DATE_YY);");
			out.println("}");

			out.println("function check_date_start(row){ ");
			out.println("  checkMonthLength(document.Form1.TXT_START_DATE_DD,document.Form1.TXT_START_DATE_MM,document.Form1.TXT_START_DATE_YY);");
			out.println("}");

			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_START_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_START_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_START_DATE_YY.value=v_yy;");
			out.println("  }");	
			 out.println(" else if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_END_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_END_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_END_DATE_YY.value=v_yy;");
			out.println("  }");	
			out.println(" if((document.Form1.TXT_END_DATE_DD.value !=\"\")&&(document.Form1.TXT_END_DATE_MM.value !=\"\")&&(document.Form1.TXT_END_DATE_YY.value !=\"\") && (document.Form1.TXT_START_DATE_DD.value !=\"\")&&(document.Form1.TXT_START_DATE_MM.value !=\"\")&&(document.Form1.TXT_START_DATE_YY.value !=\"\")){");
			out.println("chk_validity()");
			out.println("}");	
			out.println("}");				
			
			out.println("function chk_validity(){  ");			
      out.println("if((parseInt(document.Form1.TXT_END_DATE_DD.value))>=(parseInt(document.Form1.TXT_START_DATE_DD.value))){");
      out.println("if((parseInt(document.Form1.TXT_END_DATE_MM.value))<=(parseInt(document.Form1.TXT_START_DATE_MM.value))){");
      out.println("if((parseInt(document.Form1.TXT_END_DATE_YY.value))<=(parseInt(document.Form1.TXT_START_DATE_YY.value))){");
      out.println(" if(((parseInt(document.Form1.TXT_END_DATE_DD.value))==(parseInt(document.Form1.TXT_START_DATE_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_END_DATE_MM.value))==(parseInt(document.Form1.TXT_START_DATE_MM.value)))&&");
      out.println("((parseInt(document.Form1.TXT_END_DATE_YY.value))==(parseInt(document.Form1.TXT_START_DATE_YY.value)))){");
      out.println("}");
      out.println("else if(((parseInt(document.Form1.TXT_END_DATE_DD.value))>(parseInt(document.Form1.TXT_START_DATE_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_END_DATE_MM.value))==(parseInt(document.Form1.TXT_START_DATE_MM.value)))&&");
      out.println(" ((parseInt(document.Form1.TXT_END_DATE_YY.value))==(parseInt(document.Form1.TXT_START_DATE_YY.value)))){");
      out.println("      alert('Start Date should be greater than End Date');");
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println(" alert('Start Date should be greater than End Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseInt(document.Form1.TXT_END_DATE_YY.value))>=(parseInt(document.Form1.TXT_START_DATE_YY.value))){");
      out.println("    alert('Start Date should be greater than End Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseInt(document.Form1.TXT_END_DATE_MM.value))<=(parseInt(document.Form1.TXT_START_DATE_MM.value))){");
      out.println("  if((document.Form1.TXT_END_DATE_YY.value)<=(document.Form1.TXT_START_DATE_YY.value)){");
      out.println(" }");
      out.println(" else{");
      out.println("   alert('Start Date should be greater than End Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseInt(document.Form1.TXT_END_DATE_YY.value))<(parseInt(document.Form1.TXT_START_DATE_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("  alert('Start Date should be greater than End Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			out.println("document.Form1.TXT_END_DATE_DD.focus();");
			out.println("return true;");
      out.println("}");

			out.println("function check_amt() {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno	=    document.Form1.TXT_AMOUNT.value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("format_number(document.Form1.TXT_AMOUNT,22)");
			out.println("}");
			
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_text.value=val");
			out.println("}");
						
									
			out.println("function check_client(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order&data_val1=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=Y&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

									
			out.println("function check_acc(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order_acc&data_val1=\"+document.Form1.TXT_CLIENT_CODE.value+\"&ac_status=Y&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function check_so(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order_so&ac_status=Y&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function sysdate() {");
			out.println("document.Form1.hid_text.value=\"J6\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_sysdate\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
//-------------------------------------------------------------------------------------------------------------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),sysdate()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_text' VALUE=\"\">");

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Standing Order Entry</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  //modified by nuwan  de silva 20-08-07
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
			
			if(m_type.equals("EDIT")){
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SO_NO' class=div_input>Standing Order No*</div></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SO_NO' maxlength='15' size='15' onblur=\"clear_app(),assig('J5'),check_so(document.Form1.TXT_SO_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_BUTTON_4' value=\"...\" onClick=\"help_button_4()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			}
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LEASE_NO' class=div_input>Finance No*</div></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LEASE_NO' maxlength='15' size='15' onblur=\"clear_app(),assig('J2'),makeRequest(document.Form1.TXT_LEASE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_BUTTON_1' value=\"...\" onClick=\"help_button_1()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Application No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE' class=div_input>Client Code</div></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' onblur=\"assig('J1'),check_client(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_BUTTON_2' value=\"...\" onClick=\"help_button_2()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VEHICLE_NO' class=div_input>Vehicle No</div></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VEHICLE_NO' maxlength='7' size='7' onblur=\"assig('J3'),check_vehicle(document.Form1.TXT_VEHICLE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_BUTTON_3' value=\"...\" onClick=\"help_button_3()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_START_DATE' class=div_input>Start Date</div></td>"); 
			out.println("<td width=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_START_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_start()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_START_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_start()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_START_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_start()\"><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


			out.println("<td width='30%' ><DIV id='DIV_TXT_END_DATE' class=div_input>End Date</div></td>"); 
			out.println("<td width=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_END_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_end()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_END_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_end()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_END_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_end()\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			

			
				
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ACC_NO' class=div_input>Account No</div></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ACC_NO' maxlength='20' size='20' onblur=\"assig('J4'),check_acc(document.Form1.TXT_ACC_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ACC_NO' value=\"...\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Bank Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BANK_CODE' maxlength='5' size='5' disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
      //Added by Chandana on 21/05/2007 for Ref No 45
      out.println("<tr>"); 
			out.println("<td width='30%' >Branch Name</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BANK_NAME' maxlength='100' size='100' disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
      // ---- End Ref No 45 -----//

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AMOUNT' class=div_input>Amount*</div></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style=text-align:right type='text' name='TXT_AMOUNT' maxlength='27'  onblur=\"check_amt()\">"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 

			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=details></div></td></tr></table>");
		
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
			//*****************************************************************************************************************
	
		
			//********************************************************************************************************************************************************************************************************************************
			
			
			else if(m_chksql.equals("details")){
			String	m_app_no=req.getParameter("APP_NO");
			String	m_client=req.getParameter("CLIENT_NO");
			String	m_lease=req.getParameter("LEASE_NO");
			String	m_reg_no=req.getParameter("REGI_NO");
			
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	
			int j=0;
		
			rs = stmt.executeQuery("SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,TO_CHAR(nvl(A.TOTAL_FINANCE_AMOUNT,0),'999,999,999,999,999,999,999.99'),TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') "+
			"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			"WHERE A.APPLICATION_NO=C.APPLICATION_NO "+
			"AND A.CLIENT_CODE=B.CLIENT_CODE "+
			"AND upper(A.CLIENT_CODE) like upper('"+m_client+"%') "+
			"AND UPPER(A.FINANCE_NO) LIKE UPPER('"+m_lease+"%') "+
			"AND UPPER(A.APPLICATION_NO) LIKE UPPER('"+m_app_no+"%') "+
			"AND UPPER(C.REG_NO) LIKE UPPER('"+m_reg_no+"%') "+
			"AND APPLICATION_STATUS='ACTIVATED' ");




			boolean more = rs.next();
	
       while(more){
			out.println("<br>");	
			out.println("<b><HR>");	
			out.println("<br>");	
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
      out.println("<tr class=pdn_txtpos2 align='left'>");
			out.println("<td  width='25%'>Finance No</td>");
      out.println("<td  width='25%'>Application No</td>");
			out.println("<td  width='25%' align=right>Total Amount</td>");
			out.println("<td  width='25%'>Activated Date</td></tr>");


							
					if(j>0 && j%2==1){
              out.println("<tr class=tr_input1 >");
					}
					else{
									
               out.println("<tr class=tr_input >");
					}
							
			out.println("<td width='25%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"</td>");
			out.println("<td width='30%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"</td>");
			out.println("<td width='25%' align='right'>"+rs.getString(4)+"</td>");
			if(rs.getString(5)==null){
		  out.println("<td width='25%' align='left'></td></tr>");
			}
			else{
		  out.println("<td width='25%' align='left'>"+rs.getString(5)+"</td></tr>");
			}			
      out.println("<tr>");      
      out.println("</tr>");      
			out.println("<tr>");      
      out.println("</tr>");      
		  out.println("<tr>");      
      out.println("</tr>");      
			out.println("<tr>");      
      out.println("</tr>");  


			 rs1 = stmt1.executeQuery ("SELECT  distinct CLIENT_CODE,FULL_NAME, "+
				"DECODE(CLIENT_CATEGORY,'INDIVIDUAL','Individual','PUBLIC','Public/ Quoted','CORPORATE','Corporate','LIMITED','Private Limited Liability','PARTNERS','Partnership','SOLEPROPRI','Sole Propritership',CLIENT_CATEGORY) "+ //Added by Chandana on 20/07/2007 for Ref No.634
			 "FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			 "WHERE UPPER(CLIENT_CODE)=UPPER('"+rs.getString(3)+"') ");
	
	
 			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
      out.println("<tr class=pdn_txtpos2 align='left'>");

      out.println("<td width='25%' align='left'>Client Code</td>");
      out.println("<td width='30%' align='left'>Client Name</td>");
      out.println("<td width='25%' align='left'>Client Type</td>");
      out.println("<td width='*%' align='left'>&nbsp</td>");
			out.println("</tr>");
			int a=0;
				
				while(rs1.next()){	
									if(a>0 && a%2==1){
              out.println("<tr class=tr_input1 >");
					}
					else{
									
               out.println("<tr class=tr_input >");
					}
							

      out.println("<td width='25%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs1.getString(1)+"')\"><U>"+rs1.getString(1)+"</td>");
			out.println("<td width='30%' align='left'>"+rs1.getString(2)+"</td>");
			out.println("<td width='25%' align='left'>"+rs1.getString(3)+"</td>");
		  out.println("<td width='*%' align='left'>&nbsp</td>");
			out.println("</tr>");
			a=a+1;
			}
			out.println("</table>");
		  out.println("<br>");
			
			
		  rs2 = stmt2.executeQuery ("SELECT distinct INVOICE_NO,APPLICATION_NO, "+
		  "ENGINE_NO,CHASSIS_NO,nvl(REG_NO,'-'),SUB_MODEL_CODE,TO_CHAR(nvl(TOTAL_AMOUNT,0),'999,999,999,999,999,999.99'), "+
		  "A.MODEL_CODE,nvl(PURCHASE_ORDER_NO,'-'),B.DESCRIPTION ,C.DESCRIPTION "+
		  "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B,"+m_schema_name+".AF_CO_MAS_SUB_MODLE C "+
			"WHERE UPPER(APPLICATION_NO)=UPPER('"+rs.getString(2)+"') "+
			"AND A.MODEL_CODE=B.MODEL_CODE "+
			"AND A.SUB_MODEL_CODE=C.SUB_CODE ");
		 	 
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		  out.println("<tr class=pdn_txtpos2 align='left'>");

			out.println("<td width='12%' align='left'>Invoice No</td>");
		  out.println("<td width='13%' align='left'>Registration No</td>");

      out.println("<td width='10%' align='left'>Engine No</td>");
      out.println("<td width='10%' align='left'>Chassis No</td>");
      out.println("<td width='10%' align='left'>Model Code</td>");
      out.println("<td width='15%' align='left'>Sub Model Code</td>");
			out.println("<td width='10%' align='right'>Total Amount</td>");
      out.println("<td width='20%' align='left'>Purchase Order No</td></tr>");
			int d=0;
			
       while(rs2.next()){				
													if(d>0 && d%2==1){
              out.println("<tr class=tr_input1 >");
					}
					else{
									
               out.println("<tr class=tr_input >");
					}
					
       out.println("<td width='12%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs2.getString(1)+"')\"><U>"+rs2.getString(1)+"</td>");
			 out.println("<td width='13%' align='left'>"+rs2.getString(5)+"</td>");

	     out.println("<td width='10%' align='left'>"+rs2.getString(3)+"</td>");
       out.println("<td width='10%' align='left'>"+rs2.getString(4)+"</td>");
				
       out.println("<td width='10%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model_details_drill('"+rs2.getString(8)+"')\"><U>"+rs2.getString(10)+"</td>");
		   out.println("<td width='15%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sub_model_details_drill('"+rs2.getString(6)+"')\"><U>"+rs2.getString(11)+"</td>");
       out.println("<td width='10%' align='right'>"+rs2.getString(7)+"</td>");
       out.println("<td width='20%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_purchase_order_drill('"+rs2.getString(9)+"')\"><U>"+rs2.getString(9)+"</td>");
			 out.println("</tr>");
				d=d+1;
			}
			 out.println("</table>");
			 out.println("<br>");

				more=rs.next();
				
	     	 j=j+1;
				
				if(!more){
				break;
				}
				out.println("</table>");

			 }
          
				out.println("<tr>");
				out.println("<td><input type=\"hidden\" name=hid_no value="+j+"></td>");
				out.println("</tr>");
				out.println("</table>");
	
			}
			//*****************************************************************************************************************
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
