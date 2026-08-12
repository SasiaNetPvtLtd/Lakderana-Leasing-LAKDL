//--
//SCREEN NAME	:PAYMENT SETTLEMENT
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_PRO_display_payment_settlement extends javax.servlet.http.HttpServlet { 
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
			String fschema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
      String m_username 						= m_sn_methods.username;
			String m_value="";
			out = res.getOutputStream();
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			
			/*String  m_window = "NEW";
			
			m_window=req.getParameter("window");
			if(m_window==null){
			m_window="NEW";
			
			}
			
out.println(m_window);
*/
	
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Finance - Other Payments</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var curr_code=''");
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0  && document.Form1.hid_assig.value=='J2' && document.Form1.hid_assig.value!='J1' && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.TXT_PAYMENT_NO.value !=\"\"){");
			out.println("help_update()");
			out.println("			}");
			out.println("			if(data_vec.length>0  && document.Form1.hid_assig.value=='J2' && document.Form1.hid_assig.value!='J1' && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.TXT_PAYMENT_NO.value !=\"\"){");
			out.println("    document.Form1.TXT_PAYMENT_NO.value=data_vec[0];"); 
			
			
			out.println("if(data_vec[32]!='-' || data_vec[32]!=\"\"){");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[31];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data_vec[32];"); 
			out.println("}");
			
			
			out.println("if(data_vec[2]!='-' || data_vec[2]!=\"\"){");
			out.println("    document.Form1.TXT_PAYEE_CODE.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_PAYEE_NAME.value=data_vec[30];"); 
			out.println("}");
			
			
			
			out.println(" if(data_vec[31]=='-' || data_vec[31]==\"\"){");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=\"\";"); 
			out.println("}");
			
			
			out.println("    document.Form1.TXT_PAYER_CODE.value=data_vec[33];"); 
			out.println("    document.Form1.TXT_PAYER_NAME.value=data_vec[34];"); 

			out.println("    document.Form1.TXT_SUS_REF_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_SETTLE_MODE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_ENTRY_TYPE.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=data_vec[7];"); 
			
			out.println("if(data_vec[9]=='null' || data_vec[9]==\"\"){");
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=\"\";");
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=\"\";");
			out.println("    document.Form1.TXT_BRANCH_NAME.value=\"\";");
			out.println("}");
			out.println("else if(data_vec[9]!='null' || data_vec[9]!=\"\"){");
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=data_vec[9];");
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=data_vec[8];");
			out.println("}");
			
			out.println("    document.Form1.TXT_EFF_VALDATE.value=data_vec[15];"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_DD.value=data_vec[15].substring(0,2);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_MM.value=data_vec[15].substring(3,5);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_YY.value=data_vec[15].substring(6,10);"); 
			//out.println("    document.Form1.TXT_PAYEE_NAME.value=data_vec[17];"); 
						out.println("    document.Form1.TXT_PAYER_NAME.value=data_vec[17];"); 

			
			out.println("    document.Form1.TXT_RPT_AMOUNT.value=data_vec[22];"); 
			out.println("format_number(document.Form1.TXT_RPT_AMOUNT,18)");
			out.println("if(data_vec[23]=='null' || data_vec[23]==\"\"){");
			out.println("    document.Form1.TXT_COMMENTS.value=\"\";"); 
			out.println("}");
			out.println("else if(data_vec[23]!='null' || data_vec[23]!=\"\"){");
			out.println("    document.Form1.TXT_COMMENTS.value=data_vec[23];"); 
			out.println("}");
			out.println("    document.Form1.TXT_CURR.value=data_vec[24];");
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=data_vec[25];");
			out.println("    document.Form1.TXT_BALANCE.value=data_vec[26];");
			out.println("format_number(document.Form1.TXT_BALANCE,18)");
		  out.println("    document.Form1.TXT_PAYEE_CODE.value=data_vec[27];");
			out.println("    document.Form1.TXT_RECON_STATUS.value=data_vec[12];");
			out.println("			}");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_assig.value=='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			out.println("document.Form1.TXT_CLIENT_NAME.value=\"\"");
			out.println("help_button_2()");
			out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_assig.value=='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			out.println("document.Form1.TXT_CLIENT_CODE.value=data_vec[0]");
			out.println("document.Form1.TXT_CLIENT_NAME.value=data_vec[1]");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_SUS_REF_NO.value!=\"\" && document.Form1.hid_assig.value=='J6' && document.Form1.hid_assig.value!='J4' && document.Form1.hid_assig.value!='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			
			out.println("help_button_3()");
			//out.println("alert('@@@@')");
			out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_SUS_REF_NO.value!=\"\" && document.Form1.hid_assig.value=='J6' && document.Form1.hid_assig.value!='J4' && document.Form1.hid_assig.value!='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			out.println("    document.Form1.TXT_SUS_REF_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_PAYEE_CODE.value=data_vec[3];");
			out.println("    document.Form1.TXT_PAYER_CODE.value=data_vec[2];");
			
			out.println("    document.Form1.hid_Payer.value=data_vec[4];");
			out.println("    document.Form1.TXT_CURR.value=data_vec[11];"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=data_vec[12];"); 
			out.println("    document.Form1.TXT_PAYEE_NAME.value=data_vec[17];"); 
				out.println("    document.Form1.TXT_PAYER_NAME.value=data_vec[13];"); 
		
			out.println("    document.Form1.TXT_BALANCE.value=data_vec[8];"); 
			out.println("    document.Form1.TXT_EFF_VALDATE.value=data_vec[14];"); 
		//	out.println(" alert(data_vec[14])");
			out.println("    document.Form1.TXT_EFF_VALDATE_DD.value=data_vec[14].substring(0,2);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_MM.value=data_vec[14].substring(3,5);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_YY.value=data_vec[14].substring(6,10);"); 
			
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[16];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data_vec[18];"); 
		
			
			
			
			
			
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_LIC_ACC_NO.value!=\"\" && document.Form1.hid_assig.value=='J4' && document.Form1.hid_assig.value!='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=\"\";"); 
			
			out.println("help_button_7()");
		  out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_LIC_ACC_NO.value!=\"\" && document.Form1.hid_assig.value=='J4' && document.Form1.hid_assig.value!='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=data_vec[1];"); 
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_PAYEE_ACC_NO.value!=\"\" && document.Form1.hid_assig.value=='J5' && document.Form1.hid_assig.value!='J4' && document.Form1.hid_assig.value!='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=\"\";");
			out.println("    document.Form1.TXT_BRANCH_NAME.value=\"\";");
			out.println("help_button_9()");
			out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_LIC_ACC_NO.value!=\"\" && document.Form1.hid_assig.value=='J5' && document.Form1.hid_assig.value!='J4' && document.Form1.hid_assig.value!='J3' && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value!='J1' ){");
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=data_vec[1];");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value=='J1'){");
  		out.println("	alert('Payment amount must be less or equal to the balance')		");
			out.println("    document.Form1.TXT_PAY_AMOUNT.value=\"\";");
			out.println("    document.Form1.TXT_RPT_AMOUNT.value=\"\";");
			out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.hid_assig.value!='J2' && document.Form1.hid_assig.value=='J1' && document.Form1.TXT_PAY_AMOUNT.value!=\"\" && document.Form1.TXT_BALANCE.value!=\"\"){");
			out.println("document.Form1.TXT_RPT_AMOUNT.value=data_vec[15];");
			out.println("			}");
			
				out.println("			if(data_vec.length==0 && document.Form1.hid_assig.value=='J8'  && document.Form1.TXT_PAYEE_BRANCH_CODE.value!=\"\"){");
			out.println("help_button_8();");
			out.println("			}");
	
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("document.Form1.hid_assig.value='J2'");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_PRO_display_payment_settlement&data_val=\"+obj.value;");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_PRO_display_payment_settlement_other&data_val=\"+obj.value;");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function check_bal(val) {");
			out.println("document.Form1.hid_assig.value='J1'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_PRO_display_payment_settle_bal&data_val=\"+val+\"&data_val1=\"+document.Form1.TXT_SUS_REF_NO.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function check_client(val) {");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){");
			out.println("document.Form1.TXT_CLIENT_NAME.value=\"\"");
			out.println("}");
			
			out.println("document.Form1.hid_assig.value='J3'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+val;");
		//	out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_sus_ref(val) {");
			out.println("document.Form1.hid_assig.value='J6'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_sus_ref_code1&data_val1=\"+document.Form1.TXT_CLIENT_CODE.value+\"&data_val=\"+val;");
//out.println("window.open(m_url)");
			
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_LIC_acc(val) {");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("document.Form1.hid_assig.value='J4'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_LIC_Acc_code&data_val1=\"+document.Form1.TXT_CLIENT_CODE.value+\"&data_val=\"+val;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("else{");
			out.println("alert('Please enter Client code')");
			out.println("document.Form1.TXT_LIC_ACC_NO.value=\"\";");
			out.println("document.Form1.TXT_CLIENT_CODE.focus();");
			out.println("}");
			out.println("}");
			
			out.println("function check_payee_acc(val) {");
			/*out.println("if(document.Form1.TXT_SUS_REF_NO.value!=\"\"){");
			out.println("document.Form1.hid_assig.value='J5'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_LIC_Acc_code&data_val1=\"+document.Form1.TXT_PAYEE_CODE.value+\"&data_val=\"+val;");
			//out.println("window.open(m_url)");
			
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("else{");
			out.println("alert('Please enter suspense reference no')");
			out.println("document.Form1.TXT_PAYEE_ACC_NO.value=\"\";");
			out.println("document.Form1.TXT_SUS_REF_NO.focus();");
			out.println("}");*/
			out.println("}");

			out.println("function check_branch() {");
			out.println("document.Form1.hid_assig.value='J8'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_settlement_branch_code&data_val=\"+document.Form1.TXT_PAYEE_BRANCH_CODE.value+\"\";");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


//88888888
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
			out.println("     document.Form1.TXT_EFF_VALDATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_EFF_VALDATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_EFF_VALDATE_YY.value=v_yy;");
			out.println("  }");				
			out.println("}");		
			
//88888888

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.TXT_PAYMENT_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PAYMENT_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_ENTRY_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_ENTRY_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SUS_REF_NO.value==\"\"){  "); 
			out.println("DIV_TXT_SUS_REF_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SETTLE_MODE.value==\"\"){  "); 
			out.println("DIV_TXT_SETTLE_MODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LIC_ACC_NO.value==\"\"){  "); 
			out.println("DIV_TXT_LIC_ACC_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAY_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_PAY_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_EFF_VALDATE.value==\"\"){  "); 
			out.println("DIV_TXT_EFF_VALDATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_payment_settlement';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_payment_settlement';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_payment_settlement';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			//out.println("function edit_window(){	"); 
			//out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_display_payment_settlement?window=EDIT';"); 
			//out.println("}"); 
			//out.println(""); 
			//out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_PRO_display_payment_settlement\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Finance - Other Payments - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Finance - Other Payments - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();"); 
			out.println("}"); 
			out.println("}"); 
			//out.println("else if(m_val==\"EDIT\"){"); 
			//out.println(" "+m_window+"='EDIT'");
			
			//out.println("edit_window();"); 
			//out.println("}"); 
			
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			
			out.println("document.Form1.TXT_PORTFOLIO_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_SUS_REF_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_NAME.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_CLIENT_CODE.disabled=true;"); 
			
			
			out.println("document.Form1.TXT_SETTLE_MODE.disabled=true;"); 
			out.println("document.Form1.TXT_ENTRY_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_PAY_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_LIC_BRANCH_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_LIC_ACC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_BRANCH_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_ACC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PROCESS_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_ENTDATE.disabled=true;"); 
			out.println("document.Form1.TXT_RECON_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_RECON_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_RECON_BY.disabled=true;"); 
			out.println("document.Form1.TXT_EFF_VALDATE.disabled=true;"); 
			out.println("document.Form1.TXT_REALISED_DATE.disabled=true;"); 
			
			out.println("document.Form1.TXT_PAYEE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_PAYER_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYER_NAME.disabled=true;"); 
			
			out.println("document.Form1.TXT_PAY_AMOUNT_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE_BANK.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE_REP_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_REC_AMMOUNT_REP_CURR.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_GAIN_LOSS.disabled=true;"); 
			out.println("document.Form1.TXT_COMMENTS.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println("clear_data1(1)");
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("pay_no()");
      out.println("}"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

     /* //========= Comment by Chandana on 23/05/2007 for Ref No.139 ===========//

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"2\"){"); 
			out.println(" document.Form1.TXT_CLIENT_CODE.value = \"\" ");
			out.println(" document.Form1.TXT_CLIENT_NAME.value = \"\" ");
			out.println(" }else");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"9\"){"); 
			out.println("		help_value_assign_9(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"10\"){"); 
			out.println("		help_value_assign_10(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("help_value_assign_2(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"3\"){"); 
			out.println("help_value_assign_3(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"7\"){"); 
			out.println("help_value_assign_7(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"99\"){"); 
			out.println("help_update_value_assign_99(oBj);"); 
	  	out.println("		}");
			out.println("	}"); //end next
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			out.println("	else{");
			out.println("	clear_data(IfCount);");
			out.println("	}");
			out.println("	}	"); //
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"3\"){"); 
			out.println("    document.Form1.TXT_SUS_REF_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_PAYEE_CODE.value=\"\";");
			out.println("    document.Form1.hid_Payer.value=\"\";");
			out.println("    document.Form1.TXT_PAYEE_NAME.value=\"\";"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE.value=\"\";"); 
			out.println("    document.Form1.TXT_CURR.value=\"\";"); 
			out.println("	}	");
			out.println("else	if(oBj.valout[2] ==\" \" && IfCount==\"7\"){"); 
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_NAME.value=\"\";");
			out.println("	}	");
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"9\"){"); 
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=oBj.valout[3];"); 
			out.println("	}	");
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"99\"){"); 
			out.println("clear_data1(2)"); 
			out.println("	}	");
			out.println("}"); 
			out.println(""); */
			
			
			//======================================================================================
			
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		  out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){");		
			
	    out.println("		if(IfCount==\"9\"){"); 
			out.println("		help_value_assign_9(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"10\"){"); 
			out.println("		help_value_assign_10(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("help_value_assign_2(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"3\"){"); 
			out.println("help_value_assign_3(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"7\"){"); 
			out.println("help_value_assign_7(oBj);"); 
	  	out.println("		}");
			
					out.println("		if(IfCount==\"8\"){"); 
			out.println("help_value_assign_8(oBj);"); 
	  	out.println("		}");
	
			out.println("		if(IfCount==\"99\"){"); 
			out.println("help_update_value_assign_99(oBj);"); 
	  	out.println("		}");				
			out.println("	}"); //end next
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			out.println("	else{");
			out.println("	clear_data(IfCount);");//
			out.println("	}");
			out.println("	}	"); //
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"2\"){"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("	}	");

			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"3\"){"); 
			out.println("    document.Form1.TXT_SUS_REF_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_PAYEE_CODE.value=\"\";");
			out.println("    document.Form1.hid_Payer.value=\"\";");
//			out.println("    document.Form1.TXT_PAYEE_NAME.value=\"\";"); 
					out.println("    document.Form1.TXT_PAYER_NAME.value=\"\";"); 
	
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE.value=\"\";"); 
			out.println("    document.Form1.TXT_CURR.value=\"\";"); 
			out.println("	}	");
			out.println("else	if(oBj.valout[2] ==\" \" && IfCount==\"7\"){"); 
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_NAME.value=\"\";");
			out.println("	}	");
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"9\"){"); 
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=\"\";"); ; 
			out.println("	}	");
			
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"8\"){"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=\"\";");  
					out.println("    document.Form1.TXT_BRANCH_NAME.value=\"\";"); 
	
			out.println("	}	");

			
			
			out.println("	if(oBj.valout[2] ==\" \" && IfCount==\"99\"){"); 
			out.println("clear_data1(2)"); 
			out.println("	}	");		
			out.println("}"); 
			out.println(""); 
			

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
						
			//======================================================================================
			
				
			
			out.println("function clear_data1(val) {");
			out.println("if(val==\"1\"){  "); 
			out.println(" }");
			out.println("else if(val==\"2\"){  "); 
			out.println("    document.Form1.TXT_PAYMENT_NO.value=\"\";"); 
			out.println(" }");
			//if(m_window.equals("NEW")){
			out.println("    document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=\"\";"); 
			
			///}
			out.println("    document.Form1.TXT_SUS_REF_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_SETTLE_MODE.value=\"CASH\";"); 
			out.println("    document.Form1.TXT_ENTRY_TYPE.value=\"E\";"); 
			out.println("    document.Form1.TXT_PAY_AMOUNT.value=\"\";"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=\"\";");
			out.println("    document.Form1.TXT_BRANCH_NAME.value=\"\";");
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=\"\";");
			out.println("    document.Form1.TXT_EFF_VALDATE.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_DD.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_MM.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_YY.value=\"\";"); 
			out.println("    document.Form1.TXT_PAYEE_CODE.value=\"\";");
			
					out.println("    document.Form1.TXT_PAYEE_NAME.value=\"\";"); 	
					
					
					out.println("    document.Form1.TXT_PAYER_CODE.value=\"\";"); 				
					out.println("    document.Form1.TXT_PAYER_NAME.value=\"\";"); 	
			out.println("    document.Form1.TXT_RPT_AMOUNT.value=\"\";"); 
			out.println("    document.Form1.TXT_COMMENTS.value=\"\";"); 
			out.println("    document.Form1.TXT_CURR.selectedIndex=[3];");
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=\"\";");
			out.println("    document.Form1.TXT_BALANCE.value=\"\";");
		  //out.println("    document.Form1.TXT_PAYEE_CODE.value=\"\";");
			out.println("}");
			
			out.println("function clear_data(IfCount) {");
			out.println("if(IfCount==\"99\"){");
			out.println("clear_data1(2)");
			out.println("}");
			out.println("else if(IfCount==\"3\"){"); 
			out.println("    document.Form1.TXT_SUS_REF_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_PAYEE_CODE.value=\"\";");
			out.println("    document.Form1.hid_Payer.value=\"\";");
		//	out.println("    document.Form1.TXT_PAYEE_NAME.value=\"\";"); 
						out.println("    document.Form1.TXT_PAYER_NAME.value=\"\";"); 

			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE.value=\"\";"); 
			out.println("    document.Form1.TXT_CURR.value=\"\";"); 
			out.println("    document.Form1.TXT_BALANCE.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_DD.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_MM.value=\"\";"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_YY.value=\"\";"); 
			out.println("    document.Form1.TXT_BALANCE.value=\"\";"); 
			out.println("}");
			out.println("else if(IfCount==\"2\"){"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=\"\";"); 
			out.println("}");
			out.println("else if(IfCount==\"7\"){"); 
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=\"\";"); 
			out.println("}");
			out.println("else if(IfCount==\"9\"){");
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_BRANCH_NAME.value=\"\";");			
			out.println("}");
			out.println("}");
     
			/* //========= Comment by Chandana on 23/05/2007 for Ref No.139 ===========//
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); */
			// ========= End Ref No.139 =====================//
			
			
			out.println(""); 

			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_PAYMENT_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PAYMENT_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_PAYMENT_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("Sql=\"m_help_TXT_CLIENT_CODE\";");
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,2);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2(oBj) {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			//out.println("    Sql = \"m_help_TXT_SUS_REF_NO_sql\";"); 
						out.println("    Sql = \"m_help_TXT_SUS_REF_NO_sql_new\";"); 

			out.println("     Crit = document.Form1.TXT_SUS_REF_NO.value+\"@\"+document.Form1.TXT_ENTRY_TYPE.value+\"@\"+\"Y@\";"); 

			//out.println("     Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.TXT_SUS_REF_NO.value+\"@\"+document.Form1.TXT_ENTRY_TYPE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,3);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_SUS_REF_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[18];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[20];");
			
			out.println("    document.Form1.TXT_PAYEE_CODE.value=oBj.valout[4];");
			out.println("    document.Form1.hid_Payer.value=oBj.valout[5];");
			
			out.println("    document.Form1.TXT_PAYER_CODE.value=oBj.valout[5];");
			
			out.println("    document.Form1.TXT_CURR.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_PAYER_NAME.value=oBj.valout[15];"); 
			
		out.println("    document.Form1.TXT_PAYEE_NAME.value=oBj.valout[19];"); 
		
			out.println("    document.Form1.TXT_BALANCE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_EFF_VALDATE.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_DD.value=oBj.valout[16].substring(0,2);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_MM.value=oBj.valout[16].substring(3,5);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_YY.value=oBj.valout[16].substring(6,10);"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_SETTLE_MODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SETTLE_MODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_SETTLE_MODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_ENTRY_TYPE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ENTRY_TYPE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_ENTRY_TYPE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_LIC_BRANCH_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LIC_BRANCH_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    Sql = \"m_help_TXT_ACCOUNT_NO_sql\";");
			out.println("    Crit = document.Form1.TXT_LIC_ACC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,7);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7(oBj) {"); 
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_NAME.value=oBj.valout[4];");
			out.println("}"); 

			/*out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			
						out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql_new\";"); 

			//out.println("    m_sql = \"m_help_TXT_PAYEE_BRANCH_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PAYEE_BRANCH_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
*/
			out.println("function help_value_assign_8(oBj) {"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=oBj.valout[2];");
				out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[3];");

			out.println("}"); 


			out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    Sql = \"m_help_TXT_BRANCH_CODE_sql_new\";");
			out.println("    Crit = document.Form1.TXT_PAYEE_BRANCH_CODE.value+\"@Y@\";"); 
			//out.println("     Crit = document.Form1.TXT_PAYEE_BRANCH_CODE.value+\"@\"+document.Form1.TXT_PAYEE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,8);"); 
			out.println("}"); 
			out.println(""); 


			out.println("function help_button_9() {"); 
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    Sql = \"m_help_TXT_PAYEE_ACC_NO_sql\";");
			out.println("     Crit = document.Form1.TXT_PAYEE_ACC_NO.value+\"@\"+document.Form1.TXT_PAYEE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,9);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_9(oBj) {"); 
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[4];");
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    Sql = \"m_help_TXT_PAYMENT_NO_sql_other\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    Crit = document.Form1.TXT_PAYMENT_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    Crit = document.Form1.TXT_PAYMENT_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,99);"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99(oBj) {"); 
			out.println("    document.Form1.TXT_PAYMENT_NO.value=oBj.valout[2];"); 
			//out.println("if(oBj.valout[4]!='-' || oBj.valout[4]!=\"\"){");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[35];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[36];"); 
			//out.println("}");	
			//out.println("else if(oBj.valout[4]=='-' || oBj.valout[4]==\"\"){");
			//out.println("    document.Form1.TXT_CLIENT_CODE.value=\"\";");
			//out.println("    document.Form1.TXT_CLIENT_NAME.value=\"\";"); 
			//out.println("}");	
			out.println("    document.Form1.TXT_SUS_REF_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_SETTLE_MODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ENTRY_TYPE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_LIC_ACC_NO.value=oBj.valout[9];");
			out.println("    document.Form1.TXT_LIC_BRANCH_NAME.value=oBj.valout[33];");
			out.println("if(oBj.valout[11]!='null' || oBj.valout[11]!=\"\"){");
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=oBj.valout[10];");
			
			
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[32];");
			
			out.println("}");	
			out.println("else if(oBj.valout[11]=='null' || oBj.valout[11]==\"\"){");
			out.println("    document.Form1.TXT_PAYEE_ACC_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_PAYEE_BRANCH_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=\"\";");
			out.println("}");	
			out.println("    document.Form1.TXT_BALANCE.value=oBj.valout[31];"); 
			out.println("format_number(document.Form1.TXT_BALANCE,18)");
			out.println("    document.Form1.TXT_EFF_VALDATE.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_DD.value=oBj.valout[17].substring(0,2);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_MM.value=oBj.valout[17].substring(3,5);"); 
			out.println("    document.Form1.TXT_EFF_VALDATE_YY.value=oBj.valout[17].substring(6,10);"); 
			
			out.println("    document.Form1.TXT_PAYER_CODE.value=oBj.valout[26];");		
			out.println("    document.Form1.TXT_PAYER_NAME.value=oBj.valout[30];");

			out.println("    document.Form1.TXT_PAYEE_CODE.value=oBj.valout[29];"); 
			out.println("    document.Form1.TXT_PAYEE_NAME.value=oBj.valout[19];");
			
			
			
			
		//out.println("    document.Form1.TXT_PAYEE_NAME.value=oBj.valout[19];"); 
		
		//	out.println("    document.Form1.TXT_PAYEE_NAME.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_RPT_AMOUNT.value=oBj.valout[23];"); 
			out.println("format_number(document.Form1.TXT_RPT_AMOUNT,18)");
			//out.println("    document.Form1.TXT_PAYEE_CODE.value=oBj.valout[29];");
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[25];"); 
			out.println("    document.Form1.TXT_CURR.value=oBj.valout[27];");
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=oBj.valout[28];");
			out.println("    document.Form1.TXT_RECON_STATUS.value=oBj.valout[14];");
			
		
			
			
			out.println("}"); 
				
			out.println("function check_amt() {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("format_number(document.Form1.elements[nt],22)");
			out.println("}");
			
			out.println("function calculate(val) {");
			out.println("if(document.Form1.TXT_BALANCE.value!=\"\"){ ");
			out.println(" if(document.Form1.TXT_PAY_AMOUNT.value!=\"\"  ){");
			out.println("document.Form1.TXT_RPT_AMOUNT.disabled=true");
			out.println("format_number(document.Form1.TXT_PAY_AMOUNT,18)");
			out.println("if(format_number(document.Form1.TXT_PAY_AMOUNT,18)){");
			out.println("val=unformat_noobject(document.Form1.TXT_PAY_AMOUNT.value)");
			out.println("check_bal(val)");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("else{");
			out.println("alert('Please select suspense reference no first')");
			out.println("document.Form1.TXT_PAY_AMOUNT.value=\"\"");
			out.println("}");
			out.println("}");
			
			out.println("function pay_no() {");
			out.println("DIV_TXT_PAYMEMENT_NO.innerHTML='<tr >'+"); 
			out.println("'<td width=\"30%\" ><DIV id=\"DIV_TXT_PAYMENT_NO\"  class=div_input>Payment No *</DIV></td>';"); 
			out.println("change1.innerHTML='<td width=\"30%\" >'+"); 
			out.println("'<input class=\"txt_input\" type=text name=TXT_PAYMENT_NO maxlength=15 size=15 onblur=\"makeRequest(document.Form1.TXT_PAYMENT_NO)\">'+"); 
			out.println("'<input class=\"but_input\" type=button name=BUT_HELP_MAIN value=\"Help\" onClick=\"help_update()\" ></td>'+"); 
			out.println("'<td width=\"*%\"></td>'+"); 
			out.println("'</tr>';"); 
			out.println("}");
			

//__________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_Payer' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Other Payments</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//---modified by : delanjali-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//---date				 :2007-07-17-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//---ref no			 :504 -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//---commented reason			 :its not used-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");' onClick='load_screen_status(\"NEW\")' value=\"Letter\"></td>");  
			//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
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
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYMEMENT_NO'  class=div_input></DIV></td>"); 
			out.println("<td width='30%' ><div id=change1></div>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_ENTRY_TYPE'  class=div_input>Entry Type *</DIV></td>"); 
			out.println("<td width='30%' ><select class=txt_input type=text name=TXT_ENTRY_TYPE maxlength=1 size=1>");  
			//out.println("<option value=\"S\" selected>Supplier</option>");
			out.println("<option value=\"E\">Seizer</option>");
			out.println("<option value=\"V\">Vendor</option>");
			out.println("<option value=\"L\">Lawyer</option>");
			//out.println("<option value=\"R\">Repossession</option>");
			out.println("<option value=\"A\">Advertistment</option>");
			out.println("</select></td>");
			out.println("</tr>");


			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUS_REF_NO'  class=div_input>Suspense Reference No*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUS_REF_NO' maxlength='19' size='19' onblur=\"check_sus_ref(document.Form1.TXT_SUS_REF_NO.value)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_SUS_REF_NO' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"check_client(document.Form1.TXT_CLIENT_CODE.value)\" disabled></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_button_2()\" disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style='{width:250px}' type='text' name='TXT_CLIENT_NAME' maxlength='6' size='6' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_EFF_VALDATE'  class=div_input>Effective Value Date </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_EFF_VALDATE_DD' maxlength='2' size='2' disabled>"); 
			out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_MM' maxlength='2' size='2' disabled>"); 
			out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY' maxlength='4' size='4' disabled><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a><input  type='hidden' name='TXT_EFF_VALDATE' maxlength='10' size='10'></td>"); 

			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
		
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CURR'  class=div_input>Currency Code</DIV></td>"); 

			out.println("<td width=\"30%\" ><select class=txt_input type=text name=TXT_CURR maxlength=1 size=1>");  
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM   "+fschema_name+".AF_CO_MAS_CURRENCY "+
																"ORDER  BY CURR_CODE DESC ");
			boolean more1 = rs.next();		
		 	while(more1){
		
			out.println("<option value="+rs.getString(1)+" selected>"+rs.getString(2)+"</option>");			
			more1=rs.next();
			}
			
			out.println("</select></td>");

			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYER_CODE'  class=div_input>Payer Code</DIV></td>"); 

		//	out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_CODE'  class=div_input>Payee Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PAYER_CODE' maxlength='10' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
	
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYER_NAME'  class=div_input>Payer Name </DIV></td>"); 

		//	out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_NAME'  class=div_input>Payee Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' style='{width:250px}' name='TXT_PAYER_NAME' maxlength='100' size='100' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

			
			
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_CODE'  class=div_input>Payee Code</DIV></td>"); 

		//	out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_CODE'  class=div_input>Payee Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PAYEE_CODE' maxlength='10' size='10' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
	
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_NAME'  class=div_input>Payee Name </DIV></td>"); 

		//	out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_NAME'  class=div_input>Payee Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' style='{width:250px}' name='TXT_PAYEE_NAME' maxlength='100' size='100' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_ACC_NO'  class=div_input>Payee Account No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PAYEE_ACC_NO' maxlength='20' size='20' onblur=\"check_payee_acc(document.Form1.TXT_PAYEE_ACC_NO.value)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_PAYEE_ACC_NO' value=\"Help\" onClick=\"help_button_9()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PAYEE_BRANCH_CODE'  class=div_input>Payee Branch Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PAYEE_BRANCH_CODE' maxlength='10' size='10' onblur=\"check_branch()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_BRANCH_CODE' value=\"Help\" onClick=\"help_button_8()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			
			
			
			
			
			// -------- Added by Chandana on 22/05/2007 for Ref No.45 -------  //
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH_NAME'  class=div_input>Branch Name </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_NAME' style=\"width:200px;\" maxlength='100' size='100'  onblur=\"\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
      // ----------------- End Ref No.45 ---------------  //

		
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BALANCE'  class=div_input>Balance to be paid </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style='{text-align:right}'type='text' name='TXT_BALANCE' maxlength='22' size='22' onblur=\"format_number(document.Form1.TXT_PAY_AMOUNT,18)\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
						
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");	
						
			out.println("<tr>");
			out.println("<td width='30%' ><DIV id='DIV_TXT_SETTLE_MODE'  class=div_input>Settlement Mode *</DIV></td>"); 
			out.println("<td width='30%' ><select class=txt_input type=text name=TXT_SETTLE_MODE maxlength=1 size=1>");  


			out.println("<option value=\"CASH\" selected>Cash</option>");
			out.println("<option value=\"CHQ\">Cheque</option>");
				
			out.println("</select></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_COMMENTS'  class=div_input>Comments </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style='{width:200px}' type='text' name='TXT_COMMENTS' maxlength='100' size='100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

			out.println("</table>"); 
			out.println("<br>");
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
		  out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
	
			out.println("<tr>");
			out.println("<td width='25%' ><DIV id='DIV_TXT_LIC_ACC_NO'  class=div_input>Licensee Account No *</DIV></td>"); 
		  out.println("<td width='12%' ><DIV id='DIV_TXT_LIC_BRANCH_CODE'  class=div_input>Licensee Branch Code *</DIV></td>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_LIC_BRANCH_NAME'  class=div_input>Branch Name </DIV></td>"); 
			out.println("<td width='12%' ><DIV id='DIV_TXT_PAY_AMOUNT'  class=div_input>Payment Amount *</DIV></td>"); 
			out.println("<td width='12%' ><DIV id='DIV_TXT_EXCHANGE_RATE'  class=div_input>Exchange Rate </DIV></td>"); 
			out.println("<td width='12%' ><DIV id='DIV_TXT_RECON_STATUS'  class=div_input>Reconciled Status *</DIV></td>"); 
			out.println("<td width='12%' ><DIV id='DIV_TXT_RPT_AMOUNT'  class=div_input>Reporting Amount </DIV></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='25%'  align=\"left\"><input class='txt_input' type='text' name='TXT_LIC_ACC_NO' maxlength='20' size='10' onblur=\"check_LIC_acc(document.Form1.TXT_LIC_ACC_NO.value)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_LIC_ACC_NO' value=\"Help\" onClick=\"help_button_7()\"></td>"); 
			out.println("<td width='12%' align=\"left\"><input class='txt_input' type='text' name='TXT_LIC_BRANCH_CODE' maxlength='10' size='10' disabled></td>");
			out.println("<td width='15%' align=\"left\"><input class='txt_input' style='{text-align:left;}' type='text' name='TXT_LIC_BRANCH_NAME' maxlength='22' size='22' value=\"\" onblur=\"\"></td>");
			out.println("<td width='12%' align=\"left\"><input class='txt_input' style='{text-align:right;}' type='text' name='TXT_PAY_AMOUNT' maxlength='22' size='22' value=\"\" onblur=\"calculate(document.Form1.TXT_PAY_AMOUNT.value)\"></td>"); 
			out.println("<td width='12%' align=\"left\"><input class='txt_input'  type='text' name='TXT_EXCHANGE_RATE' maxlength='22' size='22' disabled></td>"); 
			out.println("<td width='12%' align=\"left\"><select class='txt_input' type=text name=TXT_RECON_STATUS maxlength=1 size=1 style='{height:10px}'>");  
			out.println("<option value=\"N\" selected>No</option>");
			out.println("<option value=\"Y\">Yes</option>");
			out.println("</select></td>");
			out.println("<td width='12%' align=\"left\"><input class='txt_input' style='{text-align:right;}' type='text' name='TXT_RPT_AMOUNT' maxlength='20' size='18' disabled></td>"); 
			out.println("</tr>"); 
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("</table>");   
			out.println("<br>");
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			out.println("</table>");
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
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
