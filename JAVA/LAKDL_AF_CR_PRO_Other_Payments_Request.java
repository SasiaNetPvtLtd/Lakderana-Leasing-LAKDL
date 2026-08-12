//--
//SCREEN NAME : FINANCE ACTIVATION
//CREATED BY  : 
//DATE/TIME   :	
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_CR_PRO_Other_Payments_Request extends javax.servlet.http.HttpServlet { 
	
	// commented by udara 13-05-2019
	/*
	ServletOutputStream out =  null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res) { // commented by udara 13-05-2019
	public  void service(HttpServletRequest req, HttpServletResponse res) { // added by udara 13-05-2019
		
		// added by udara 13-05-2019
		ServletOutputStream out =  null;
		String m_chksql =  null;
		Connection conn =  null;
		Statement stmt=null, stmt1=null;
		java.text.NumberFormat nf=null,nf1=null;
		ResultSet rs=null,rs1=null;
		// end by udara 13-05-2019
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			conn = m_sn_methods.met_user_validate(req); 
			String m_username 						= m_sn_methods.username;
			m_chksql=req.getParameter("chksql");
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			
			stmt=conn.createStatement();
			out = res.getOutputStream();
			String  _m_sys_date_dd ="",_m_sys_date_mm ="",_m_sys_date_yy ="";
			
			//=============================================================================================================================		 
			if(m_chksql.equals("main_page")){
				
				
				String _m_location_desc="";
				
				rs = stmt.executeQuery ("SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE)"+
					" FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" WHERE  UPPER(USER_ID) = UPPER('"+m_username+"') ");
				
				boolean more = rs.next();
				if(more){
					_m_location_desc=rs.getString(1);
				}
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL");
				more = rs.next();
				if(more){
					_m_sys_date_dd = rs.getString(1);
					_m_sys_date_mm = rs.getString(2);
					_m_sys_date_yy = rs.getString(3);
				}
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Finance - Other Payments</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("var vec_len=0 ;");
				out.println("var ret_sts=\"\" ;");
				out.println("var b_flag=0;"); //added by nuwan de silva 02-08-07
				out.println(" var sum=0; ");
				out.println(" var wht_sum=0; ");
				out.println(" var net=0; ");
				out.println(" var b_flag_ok=0; ");
				out.println(" var hid_status_cal=''; ");
				
				out.println("var selected_records=new Array();");
				
				
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("if(b_flag_ok==0 ) {");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				out.println("else{");
				out.println("		m_table.innerHTML=\"<p><b></b></P>\";");
				out.println("}");
				out.println("}");
				
				
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=main_page';"); 
				out.println("}"); 
				
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
   				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
   				out.println("    window.open(m_url); ");
    			out.println(" }");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Finance - Other Payments - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Finance - Other Payments - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"\";");  //m_help_msg_LAKDL_AF_PRO_CR_finance_activation
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("	new_window();"); 
				out.println("}");
				out.println("else if(m_val==\"HELP\"){"); 
				out.println(" load_help_msg();");
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else if(m_val==\"DELETE\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");  
				out.println("document.Form1.TXT_PAYMENT_NO.disabled=false;"); 
				out.println("document.Form1.BUT_TXT_PAYMENT_NO.disabled=false;"); 
				out.println("document.Form1.TXT_EFF_VALDATE_DD.disabled=true;"); 
				out.println("document.Form1.TXT_EFF_VALDATE_MM.disabled=true;"); 
				out.println("document.Form1.TXT_EFF_VALDATE_YY.disabled=true;"); 
				out.println("document.Form1.TXT_ENTRY_TYPE.disabled=true;"); 
				out.println("document.Form1.TXT_SUSPENSE_REFERENSE.disabled=true;"); 
				out.println("document.Form1.BUT_TXT_SUSPENSE_REFERENSE.disabled=true;"); 
				out.println("document.Form1.TXT_PAYEE_NAME.disabled=true;"); 
				out.println("document.Form1.TXT_VOUCHER_ADDRESS1.disabled=true;"); 
				out.println("document.Form1.TXT_VOUCHER_ADDRESS2.disabled=true;"); 
				out.println("document.Form1.TXT_VOUCHER_ADDRESS3.disabled=true;"); 
				out.println("document.Form1.TXT_SETTLE_MODE.disabled=true;"); 
				out.println("document.Form1.TXT_LIC_ACC_NO.disabled=true;"); 
				//out.println("document.Form1.BUT_TXT_LIC_ACC_NO.disabled=true;"); 
				out.println("document.Form1.TXT_LIC_BRANCH_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_LIC_BRANCH_NAME.disabled=true;"); 
				out.println("document.Form1.TXT_REMARKS.disabled=true;"); 
				
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				
				/*out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data();");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				
				
				out.println("if(IfCount=='1'){"); 
				out.println("		payment_no_assign(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		suspense_referense_assign(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("		help_value_assign_guarantor(oBj);"); 
				out.println("}");		
				out.println("else if(IfCount=='5'){"); 
				out.println("		assign_licence_account_no(oBj);"); 
				out.println("}");		
				
				
				
				
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
				out.println("	clear_data();");//Added To The Clear The Area Code
				out.println("	}");
				
				
				out.println("	}	"); //
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
				*/
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(document.Form1.hid_help_type.value);");
				out.println("	}else");
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				/*out.println("if(document.Form1.hid_help_type.value=='99'){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value=='1'){"); 
				out.println("		help_value_assign_1(oBj);"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
				out.println("		help_value_assign_2(oBj);"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
				out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value=='4'){"); 
				out.println("		help_value_branch(oBj);"); 
				out.println("}");
				*/
				
				out.println("if(document.Form1.hid_help_type.value=='1'){"); 
				out.println("		payment_no_assign(oBj);"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
				out.println("		suspense_referense_assign(oBj);"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
				out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value=='4'){"); 
				out.println("		help_value_assign_guarantor(oBj);"); 
				out.println("}");		
				out.println("else if(document.Form1.hid_help_type.value=='5'){"); 
				out.println("		assign_licence_account_no(oBj);"); 
				out.println("}");		
				
				
				
				out.println("	}"); //end next
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); //end prev
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("	}	"); 
				out.println("	}		"); ///close
				out.println("	else{");
				out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
				out.println("	}");
				
				
				out.println("	}	"); //
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
				
				//added by nuwan de silva on 22-08-07------------------------
				out.println("function clear_data() {");
				//out.println("document.Form1.TXT_FINANCE_NO.value=\"\";"); 
				//out.println(" finance_details.innerHTML = ''; ");
				out.println("}");
				
				//Added by Dineth on 01-06-2009
				out.println("function clear_data(val) {");
				out.println(" if(val==\"2\"){ "); 
				out.println("   document.Form1.TXT_SUSPENSE_REFERENSE.value='';");
				out.println(" } ");
				out.println("}");
				//End by Dineth on 01-06-2009
				
				out.println("function assign_DateValues(dval){");
				out.println("document.Form1.TXT_EFF_VALDATE_DD.value=dval.substring(0,2)");
				out.println("document.Form1.TXT_EFF_VALDATE_MM.value=dval.substring(3,5)");
				out.println("document.Form1.TXT_EFF_VALDATE_YY.value=dval.substring(6,10)");
				out.println("}");
				
				out.println("function help_payment_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    Sql = \"m_help_payment_no_other_payment\";"); 
				out.println("    Crit = document.Form1.TXT_PAYMENT_NO.value+\"@\"+\"Y@\";"); 
				//out.println("    m_sql = Sql;"); 
				//out.println("    m_criteria = Crit"); 
				//out.println("    HelpBox('1','10','5');"); 
				out.println("    m_sql = Sql;"); 
				out.println("    m_criteria = Crit"); 
				out.println("    HelpBox('1','10','0');"); 
				//out.println("    HelpBox('1','10','5',Crit,Sql,'1');");
				out.println("}"); 
				
				out.println("function payment_no_assign(oBj) {"); 
				out.println("   document.Form1.TXT_PAYMENT_NO.value=oBj.valout[2];"); 
				out.println("assign_DateValues(oBj.valout[9])");
				out.println("   document.Form1.TXT_PAYEE_NAME.value=oBj.valout[10];"); 
				out.println("   document.Form1.TXT_ENTRY_TYPE.value=oBj.valout[5];"); 
				out.println("   document.Form1.TXT_SUSPENSE_REFERENSE.value=oBj.valout[11];"); 
				out.println("   document.Form1.TXT_SETTLE_MODE.value=oBj.valout[4];"); 
				out.println("   document.Form1.TXT_LIC_ACC_NO.value=oBj.valout[8];"); 
				out.println("   document.Form1.TXT_LIC_BRANCH_CODE.value=oBj.valout[7];"); 
				out.println("   document.Form1.TXT_PAYMENT_AMOUNT.value=oBj.valout[6];"); 
				out.println("   format_number(document.Form1.TXT_PAYMENT_AMOUNT,22);");
				out.println("   document.Form1.TXT_REMARKS.value=oBj.valout[12];"); 
				out.println("   document.Form1.TXT_LIC_BRANCH_NAME.value=oBj.valout[13];"); 
				out.println("   document.Form1.TXT_WHT_AMT.value=oBj.valout[14];"); 
				out.println("   document.Form1.TXT_NET_AMT.value=oBj.valout[15];"); 
				//out.println(" 	makeRequest_delete(); ");
				out.println("}"); 	
				
				out.println("function help_suspense_referense() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("    Sql = \"m_help_suspense_reference\";"); 
				out.println("     Crit = document.Form1.TXT_SUSPENSE_REFERENSE.value+\"@\"+document.Form1.TXT_ENTRY_TYPE.value+\"@\"+\"Y@\";"); 
				//out.println("    HelpBox('1','10','0',Crit,Sql,'2');");
				out.println("    m_sql = Sql;"); 
				out.println("    m_criteria = Crit"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function suspense_referense_assign(oBj) {"); 
				out.println("   document.Form1.TXT_SUSPENSE_REFERENSE.value=oBj.valout[2];"); 
				
				out.println("if (oBj.valout[3]=='-' ||  oBj.valout[3]=='null' ) {");
				out.println("   document.Form1.TXT_PAYEE_NAME.value='';"); 
				out.println("}"); 	
				out.println("else {"); 	
				out.println("   document.Form1.TXT_PAYEE_NAME.value=oBj.valout[3];"); 
				out.println("}"); 	
				
				out.println("if (oBj.valout[5]=='-' ||  oBj.valout[5]=='null' ) {");
				out.println("   document.Form1.TXT_VOUCHER_ADDRESS1.value='';"); 
				out.println("}"); 	
				out.println("else {"); 	
				out.println("   document.Form1.TXT_VOUCHER_ADDRESS1.value=oBj.valout[5];"); 
				out.println("}"); 	
				
				//out.println("   document.Form1.TXT_VOUCHER_ADDRESS1.value=oBj.valout[5];"); 
				//out.println("   document.Form1.TXT_VOUCHER_ADDRESS2.value=oBj.valout[6];"); 
				
				out.println("if (oBj.valout[6]=='-' ||  oBj.valout[6]=='null' ) {");
				out.println("   document.Form1.TXT_VOUCHER_ADDRESS2.value='';"); 
				out.println("}"); 	
				out.println("else {"); 	
				out.println("   document.Form1.TXT_VOUCHER_ADDRESS2.value=oBj.valout[6];"); 
				out.println("}"); 	
				
				
				//out.println(" 	makeRequest(); ");
				out.println("}"); 	
				
				
				
				out.println("function ckeck_data(){ "); 
				
				//out.println("alert('No data to save');");
				out.println("b_flag=0;");
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DELETE\")");
				//out.println("if(document.Form1.hid_count.value==0){");
				out.println("if(payment_allocation.innerHTML == \"\") {");
				out.println("alert('No data to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				//out.println("alert('b_flag'+b_flag);");
				out.println("}"); 
				
				out.println("function validate_data(){"); 
				out.println("if (document.Form1.SCREEN_NAME.value!=\"DELETE\") { ");
				out.println("if(document.Form1.TXT_EFF_VALDATE_DD.value==\"\" ||  document.Form1.TXT_EFF_VALDATE_MM.value==\"\" || document.Form1.TXT_EFF_VALDATE_YY.value==\"\"){  "); 
				out.println("	DIV_TXT_EFF_VALDATE.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_SUSPENSE_REFERENSE.value==\"\"){  "); 
				out.println("	DIV_TXT_SUSPENSE_REFERENSE.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_PAYEE_NAME.value==\"\"){  "); 
				out.println("	DIV_TXT_PAYEE_NAME.style.color='red';");
				out.println("	return false;"); 
				out.println("}");
				out.println("else if(document.Form1.TXT_AUTHO.value==\"\"){  "); //Adde By Sandun on 07-01-2009
				out.println("	DIV_TXT_AUTHO.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				
				/*out.println("else if(document.Form1.TXT_LIC_ACC_NO.value==\"\"){  "); 
				out.println("	DIV_TXT_LIC_ACC_NO.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				*/
				out.println("else if(document.Form1.TXT_PAYMENT_AMOUNT.value==\"\"){  "); 
				out.println("	DIV_TXT_PAYMENT_AMOUNT.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				
				out.println("else{"); 
				out.println("	return true;"); 
				out.println("}"); 
				
				out.println("}");
				out.println("else if(document.Form1.TXT_PAYMENT_NO.value==\"\"){  "); 
				out.println("	DIV_TXT_PAYMENT_NO.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("	return true;"); 
				out.println("}"); 
				out.println("}");
				
				// added by nuwan de silva on 28-08-2008----------
				out.println("function disable_un_select_items(){ "); 
				out.println("var c=0;");
				out.println("   for (var i=0; i < parseFloat(document.Form1.hid_no_of_rec.value); i++ ) {");
				out.println("m_chk_required=\"CHK_REQUIRED\"+i;");
				out.println("if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].disabled=true");
				out.println("}");
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("selected_records[c]=i");
				out.println("}");
				out.println("c=c+1;");
				out.println("}");
				
				//out.println("alert('done'+document.Form1.hid_no_of_rec.value);");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Other_Payment_Request';");  
				//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Other_Payment_Request?rows='+selected_records;");  
				
				out.println("		document.Form1.submit();	"); 
				out.println("}");
				
				out.println("function before_submit(){ "); 
				out.println("   m_status = document.Form1.hid_status.value ");
				out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
				out.println("		if(validate_data()){"); 
				out.println("ckeck_data();");
				out.println("		if(before_save()){");
				out.println("if(b_flag==0)");
				out.println("		if(confirm(m_save_msg)){ ");
				
				// commented by udara 29-08-2019
				/*
				out.println("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("   document.Form1.elements[i].disabled=false;");
				out.println("   }");
				*/
				
				// added by udara 29-08-2019
				out.println("   document.Form1.TXT_BRANCH.disabled=false; ");
				
				out.println("   for (var i=0; i < parseFloat(document.Form1.hid_no_of_rec.value); i++ ) {");
				
				out.println("       m_chk_required=\"CHK_REQUIRED\"+i;");
				out.println("       m_txt_balance_amount=\"TXT_BALANCE_AMOUNT\"+i;");
				out.println("       m_txt_adjust_amount=\"TXT_ADJUST_MODE\"+i;");
				
				out.println("       if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("          document.Form1.elements[m_txt_balance_amount].disabled=false; ");
				out.println("          document.Form1.elements[m_txt_adjust_amount].disabled=false; ");
				out.println("       }");
				
				out.println("   }");
				// end by udara 29-08-2019
				
				//out.println("   disable_un_select_items();"); // added by nuwan de silva on 28-08-2008
				
				//out.println("       alert('befor submit');  "); // test 29-08-2019
				
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Other_Payment_Request';");  
				out.println("		document.Form1.submit();	"); 
				
				//out.println("       alert('after submit');  "); // test 29-08-2019
				
				out.println("		}"); 
				out.println("		}");					
				out.println("		}");	
				out.println("} "); 
				
				
				out.println("function makeRequest() {");
				
				out.println("     document.Form1.hid_date1.value=document.Form1.TXT_EFF_VALDATE_DD1.value+'-'+document.Form1.TXT_EFF_VALDATE_MM1.value+'-'+document.Form1.TXT_EFF_VALDATE_YY1.value;");	// added by udara 18-08-2015		
				out.println("     document.Form1.hid_date2.value=document.Form1.TXT_EFF_VALDATE_DD2.value+'-'+document.Form1.TXT_EFF_VALDATE_MM2.value+'-'+document.Form1.TXT_EFF_VALDATE_YY2.value;");	// added by udara 18-08-2015
				
				
				out.println("     if(document.Form1.TXT_NO_OF_DAYS.value==''){ ");
				out.println("         alert('No of days cannot be blank'); ");
				out.println("     }");
				out.println("     else{");
				
				out.println("         set_timer_actions();");
				//out.println("       document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD2.value+'-'+document.Form1.TXT_EFF_VALDATE_MM2.value+'-'+document.Form1.TXT_EFF_VALDATE_YY2.value;"); // commented by udara 18-08-2015 			
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=payment_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&value_date=\"+document.Form1.hid_date.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";"); // commented by udara 23-05-2014
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=payment_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&value_date=\"+document.Form1.hid_date.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"&no_of_days=\"+document.Form1.TXT_NO_OF_DAYS.value;"); // commented by udara 18-08-2015 // added by udara 23-05-2014
				out.println("         m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=payment_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&from_date=\"+document.Form1.hid_date1.value+\"&to_date=\"+document.Form1.hid_date2.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"&no_of_days=\"+document.Form1.TXT_NO_OF_DAYS.value;"); 
				//out.println("       window.open(m_url);");
				out.println("         load_interface(m_url,'NORM');");
				out.println("     }");
				
				
				out.println("}");
				
				out.println("function makeRequest_new() {");
				out.println("     document.Form1.hid_date1.value=document.Form1.TXT_EFF_VALDATE_DD1.value+'-'+document.Form1.TXT_EFF_VALDATE_MM1.value+'-'+document.Form1.TXT_EFF_VALDATE_YY1.value;");			
				out.println("     document.Form1.hid_date2.value=document.Form1.TXT_EFF_VALDATE_DD2.value+'-'+document.Form1.TXT_EFF_VALDATE_MM2.value+'-'+document.Form1.TXT_EFF_VALDATE_YY2.value;");			
				out.println("   set_timer_actions();");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=payment_allocation_details_new&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&from_date=\"+document.Form1.hid_date1.value+\"&to_date=\"+document.Form1.hid_date2.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";"); // commented by udara 23-05-2014
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=payment_allocation_details_new&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&from_date=\"+document.Form1.hid_date1.value+\"&to_date=\"+document.Form1.hid_date2.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"&no_of_days=\"+document.Form1.TXT_NO_OF_DAYS.value;"); // added by udara 23-05-2014
				//out.println("window.open(m_url);");
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				
				//ADDED BY MADHAWA 2012-02-17
				
				out.println("function select_all()");
				out.println("{ ");
				out.println("      for(k=0;k<parseInt(document.Form1.hid_count.value);k++)");
				out.println("      { ");
				out.println("   		if(document.Form1.SELECT_ALL_CHKBX.checked==true)  ");
				out.println("   		{ ");
				out.println("         		 document.Form1.elements[\"CHK_REQUIRED\"+k].checked=true; ");
				out.println("           }else ");
				out.println("           { ");
				out.println("         		 document.Form1.elements[\"CHK_REQUIRED\"+k].checked=false; ");
				out.println("           } ");
				out.println("          change_val_req(k); ");//inside this function checked and unchecked situtations both are handled
				out.println("      } ");
				
				out.println("} ");
				
				//end add by madhawa 2012-02-17
				
				
				out.println("function makeRequest_delete() {");
				out.println("   set_timer_actions();");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=delete_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&value_date=\"+document.Form1.hid_date.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";");
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				
				
				out.println("function get_vector_normal(http_response) {");
				out.println("b_flag_ok=1;");
				out.println(" payment_allocation.innerHTML = ''; ");
				out.println(" payment_allocation.innerHTML = http_response; ");
				out.println("document.Form1.hid_no_of_rec.value=document.Form1.hid_count.value ");
				out.println(" var total = 0 ");
				out.println(" for(var i = 0; i<document.Form1.hid_no_of_rec.value; i++ ){");
				out.println("   total += parseFloat(unformat_noobject(document.Form1.elements['TXT_AMOUNT'+i].value)); ");
				out.println(" }");
				out.println(" DIV_TXT_PAYMENT_AMOUNT.innerHTML = '<B>'+format_noobject(total)+'</B>';");
				out.println(" DIV_TXT_NET_AMT.innerHTML = '<B>'+format_noobject(total)+'</B>';");
				out.println(" document.Form1.TXT_PAYMENT_AMOUNT.value = total;");
				out.println(" document.Form1.TXT_NET_AMT.value = total;");
				out.println("sum     = parseFloat(total);");
				out.println("wht_sum = parseFloat(0);");
				
				out.println(" if(hid_status_cal=='calculate') {");
				
				out.println("DIV_TXT_PAYMENT_AMOUNT.innerHTML = ''; ");
				out.println("DIV_TXT_PAYMENT_AMOUNT.innerHTML = http_response; ");
				
				out.println("DIV_TXT_WHT_AMT.innerHTML = ''; ");
				out.println("DIV_TXT_WHT_AMT.innerHTML = http_response; ");
				
				out.println("DIV_TXT_WHT_AMT.innerHTML = ''; ");
				out.println("DIV_TXT_WHT_AMT.innerHTML = http_response; ");
				
				out.println("}");
				
				
				
				out.println("}");
				
				
				
				out.println("function get_vector(data) {");
				
				out.println("DIV_TXT_PAYMENT_AMOUNT.innerHTML = ''; ");
				out.println("DIV_TXT_PAYMENT_AMOUNT.innerHTML = '<B>'+format_noobject(data_vec[0])+'</B>'; ");
				
				out.println("DIV_TXT_WHT_AMT.innerHTML = ''; ");
				out.println("DIV_TXT_WHT_AMT.innerHTML = '<B>'+format_noobject(data_vec[1])+'</B>'; ");
				
				out.println("DIV_TXT_NET_AMT.innerHTML = ''; ");
				out.println("DIV_TXT_NET_AMT.innerHTML = '<B>'+ format_noobject(parseFloat(data_vec[0]) - parseFloat(data_vec[1]))+'</B>'; ");
				
				out.println("sum     = parseFloat(data_vec[0]);");
				out.println("wht_sum = parseFloat(data_vec[1]);");
				
				out.println("document.Form1.TXT_PAYMENT_AMOUNT.value=data_vec[0];");
				out.println("document.Form1.TXT_WHT_AMT.value=data_vec[1];");
				out.println("document.Form1.TXT_NET_AMT.value=parseFloat(data_vec[0]) - parseFloat(data_vec[1]);");
				
				out.println("}");
				
				out.println("	function chk_comment_length(obj){ ");
				out.println(" var remarks_length=obj.value.toString().length;");
				out.println("if(remarks_length>obj.maxlength) ");
				out.println("		window.event.keyCode=\"\"; ");
				out.println("} ");
				
				out.println("function count_length(obj){ ");
				out.println("var remarks_length=obj.value.toString().length; ");
				out.println("var remarks=obj.value.toString(); ");
				out.println("if(remarks_length>obj.maxlength){ ");
				out.println("obj.value=remarks.substring(0,obj.maxlength); ");
				out.println("} ");
				out.println("} ");
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println("}");
				
				
				// comm ented by udara 26-05-2014
				/*
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
				out.println("     document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD.value+'-'+document.Form1.TXT_EFF_VALDATE_MM.value+'-'+document.Form1.TXT_EFF_VALDATE_YY.value;");			
				out.println("  }");				
				out.println("}");
				*/
				
				// added by udara 26-05-2014
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
				out.println("     document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD.value+'-'+document.Form1.TXT_EFF_VALDATE_MM.value+'-'+document.Form1.TXT_EFF_VALDATE_YY.value;");			
				out.println("  }");	
				out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.TXT_EFF_VALDATE_DD2.value=v_dd;");
				out.println("     document.Form1.TXT_EFF_VALDATE_MM2.value=v_mm;");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY2.value=v_yy;");
				out.println("     document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD2.value+'-'+document.Form1.TXT_EFF_VALDATE_MM2.value+'-'+document.Form1.TXT_EFF_VALDATE_YY2.value;");			
				out.println("  }");
				out.println("  else if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.TXT_EFF_VALDATE_DD1.value=v_dd;");
				out.println("     document.Form1.TXT_EFF_VALDATE_MM1.value=v_mm;");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY1.value=v_yy;");
				out.println("     document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD1.value+'-'+document.Form1.TXT_EFF_VALDATE_MM1.value+'-'+document.Form1.TXT_EFF_VALDATE_YY1.value;");			
				out.println("  }");
				
				
				out.println("}");
				
				
				out.println("function check_date(obdd,obmm,obyy){ ");
				out.println(" if((obdd.value !=\"\") && (obmm.value !=\"\") && (obyy.value !=\"\")){");
				out.println(" checkMonthLength(obdd,obmm,obyy);");
				out.println(" document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD.value+'-'+document.Form1.TXT_EFF_VALDATE_MM.value+'-'+document.Form1.TXT_EFF_VALDATE_YY.value;");			
				out.println(" }");
				out.println("}");
				
				out.println("function check_date1(obdd,obmm,obyy){ ");
				out.println(" if((obdd.value !=\"\") && (obmm.value !=\"\") && (obyy.value !=\"\")){");
				out.println(" checkMonthLength(obdd,obmm,obyy);");
				out.println(" document.Form1.hid_date1.value=document.Form1.TXT_EFF_VALDATE_DD1.value+'-'+document.Form1.TXT_EFF_VALDATE_MM1.value+'-'+document.Form1.TXT_EFF_VALDATE_YY1.value;");			
				out.println(" }");
				out.println("}");
				
				out.println("function check_date2(obdd,obmm,obyy){ ");
				out.println(" if((obdd.value !=\"\") && (obmm.value !=\"\") && (obyy.value !=\"\")){");
				out.println(" checkMonthLength(obdd,obmm,obyy);");
				out.println(" document.Form1.hid_date2.value=document.Form1.TXT_EFF_VALDATE_DD2.value+'-'+document.Form1.TXT_EFF_VALDATE_MM2.value+'-'+document.Form1.TXT_EFF_VALDATE_YY2.value;");			
				out.println(" }");
				out.println("}");
				
				out.println("function load_lock(){	"); 
				
				//out.println("    alert('test'); ");
				
				out.println("     document.Form1.TXT_BRANCH.value='"+_m_location_desc+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_DD.value='"+_m_sys_date_dd+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_MM.value='"+_m_sys_date_mm+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY.value='"+_m_sys_date_yy+"';");
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("     document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD.value+'-'+document.Form1.TXT_EFF_VALDATE_MM.value+'-'+document.Form1.TXT_EFF_VALDATE_YY.value;");						
				
				out.println("     document.Form1.TXT_EFF_VALDATE_DD1.value='"+_m_sys_date_dd+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_MM1.value='"+_m_sys_date_mm+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY1.value='"+_m_sys_date_yy+"';");
				
				out.println("     document.Form1.TXT_BRANCH.value='"+_m_location_desc+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_DD2.value='"+_m_sys_date_dd+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_MM2.value='"+_m_sys_date_mm+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY2.value='"+_m_sys_date_yy+"';");
				
				out.println("}	"); 
				
				
				
				out.println("function enable_adjusted_type(obj,row){	"); 
				
				out.println("if (parseFloat(obj.value) > parseFloat(0) ) {");
				out.println("	document.Form1.elements[\"TXT_ADJUST_MODE\"+row].disabled=false;"); 
				//out.println("calculate_amount();");
				out.println("}	"); 
				out.println("else {");
				out.println("	document.Form1.elements[\"TXT_ADJUST_MODE\"+row].disabled=true;"); 
				out.println("calculate_amount();");
				out.println("}	"); 
				out.println("}	"); 
				
				/*out.println("function re_calculate_amount(obj,row){	"); 
				out.println("calculate_amount()");
				out.println("var m_adjust_amount=0;");
				out.println("var m_payment_amount=0;");
				out.println("m_adjust_amount=\"TXT_ADJUST_AMOUNT\"+row;");
				out.println("m_payment_amount=parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value));");
				out.println("m_adjust_amount=parseFloat(unformat_noobject(document.Form1.elements[m_adjust_amount].value));");
				out.println("if(obj.value==\"ADD\") {");
				out.println("m_payment_amount+=m_adjust_amount;");
				out.println("}	"); 
				out.println("else if(obj.value==\"MIN\") {");
				out.println("m_payment_amount-=m_adjust_amount;");
				out.println("}	"); 
				out.println("document.Form1.TXT_PAYMENT_AMOUNT.value=m_payment_amount;");
				out.println("format_number(document.Form1.TXT_PAYMENT_AMOUNT,22);");
				out.println("}	"); 
				*/
				
				
				out.println("function check_amount(obj,size,row){")	;
				out.println("if(obj.value!='')"); 
				out.println("amount=\"TXT_AMOUNT\"+row;");
				out.println("balance=\"TXT_BALANCE_AMOUNT\"+row;");
				out.println("if(isnumberok(obj,size)){"); 
				out.println("_m_amount=parseFloat(unformat_noobject(document.Form1.elements[amount].value));");
				out.println("_m_balance=parseFloat(unformat_noobject(document.Form1.elements[balance].value));");
				out.println("if(_m_amount >_m_balance) {");
				out.println("alert('Amount can not be greater than balance amount');");
				out.println("obj.value=0;");
				out.println("}");
				out.println("else {");
				out.println("format_number(obj,size)"); 
				out.println("}");
				
				//out.println("calculate_amount();");
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value='';"); 
				out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}	"); 
				
				
				out.println("function calculate_amount_new(i){");
				
				out.println(" var m_send_val=''; ");
				out.println("m_adjust_amount=\"TXT_ADJUST_AMOUNT\"+i;");
				out.println("adjust_type=\"TXT_ADJUST_MODE\"+i;");
				out.println("m_amount=\"TXT_AMOUNT\"+i;");
				out.println("m_wht_amount=\"TXT_WHT_AMOUNT\"+i;");
				out.println("m_chk_deposit_tmp=\"CHK_REQUIRED\"+i;");
				
				out.println("  ");
				
				//out.println("	 m_send_val= \"chksql=calculate_amount_new&amount=\"+unformat_noobject(document.Form1.elements[m_amount].value)+\"&wht_amount=\"+unformat_noobject(document.Form1.elements[m_wht_amount].value)+");
				//out.println("	             \"&adjust_type=\"+document.Form1.elements[adjust_type].value+\"&sum=\"+document.Form1.TXT_PAYMENT_AMOUNT.value+\"&wht_sum=\"+document.Form1.TXT_WHT_AMT.value+\"&adjust_amount=\"+document.Form1.elements[m_adjust_amount].value+\"&chk_deposit_tmp=\"+document.Form1.elements[m_chk_deposit_tmp].value+\"\";");
				
				out.println("	   m_send_val= \"chksql=calculate_amount_new&amount=\"+unformat_noobject(document.Form1.elements[m_amount].value)+\"&wht_amount=\"+unformat_noobject(document.Form1.elements[m_wht_amount].value)+");
				out.println("	             \"&adjust_type=\"+document.Form1.elements[adjust_type].value+\"&sum=\"+sum+\"&wht_sum=\"+wht_sum+\"&adjust_amount=\"+document.Form1.elements[m_adjust_amount].value+\"&chk_deposit_tmp=\"+document.Form1.elements[m_chk_deposit_tmp].value+\"\";");
				
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_sql_validations?\"+m_send_val;");
				
				/*out.println("m_adjust_amount=parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_adjust_amount].value))*100)/100)");
				out.println("m_adjust_type=document.Form1.elements[adjust_type].value;");
				out.println("if(m_adjust_amount >0){");
				out.println("if(m_adjust_type ==\"ADD\"){");
				out.println("sum=sum+m_adjust_amount;");
				out.println("}");			
				out.println("else if(m_adjust_type ==\"MIN\"){");
				out.println("sum=sum-m_adjust_amount");
				out.println("}");			
				out.println("}");			
				*/
				out.println("}");	
				
				out.println("else if(document.Form1.elements[m_chk_deposit_tmp].checked==false){");
				
				/*out.println("sum=sum-parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_amount].value))*100)/100);");
				out.println("wht_sum=wht_sum-parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_wht_amount].value))*100)/100);");
				
				out.println("document.Form1.elements[m_amount].value=0;");
				out.println("document.Form1.elements[m_wht_amount].value=0;"); // ADDED BY ASHINI
				*/
				
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_sql_validations?\"+m_send_val;");
				
				out.println("document.Form1.elements[m_amount].value=0;");
				out.println("document.Form1.elements[m_wht_amount].value=0;");
				out.println("document.Form1.elements[m_adjust_amount].value=0;");
				
				out.println("}");	
				
				//out.println("document.Form1.TXT_PAYMENT_AMOUNT.value=format_noobject(sum)");
				//out.println("document.Form1.TXT_WHT_AMT.value=format_noobject(wht_sum)");
				
				out.println("load_interface(m_url,'XML');");
				//out.println("window.open(m_url);");
				
				out.println("check_value();");	// added by ashini 		
				
				out.println("}");	
				
				
				
				/*
				out.println("function calculate_amount_new(i){")	;
				out.println(" var m_send_val=''; ");
				//out.println(" var wht_sum=0; ");
				//out.println("for(i=0;i<parseInt(document.Form1.hid_count.value);i++){");
				out.println("m_adjust_amount=\"TXT_ADJUST_AMOUNT\"+i;");
				out.println("adjust_type=\"TXT_ADJUST_MODE\"+i;");
				out.println("m_amount=\"TXT_AMOUNT\"+i;");
				out.println("m_wht_amount=\"TXT_WHT_AMOUNT\"+i;");
				out.println("m_chk_deposit_tmp=\"CHK_REQUIRED\"+i;");
								
				out.println("	 m_send_val= \"chksql=calculate_amount_new&amount=\"+unformat_noobject(document.Form1.elements[m_amount].value)+\"&wht_amount=\"+unformat_noobject(document.Form1.elements[m_wht_amount].value)+");
				out.println("	             \"&adjust_type=\"+document.Form1.elements[adjust_type].value+\"&adjust_amount=\"+document.Form1.elements[m_adjust_amount].value+\"&chk_deposit_tmp=\"+document.Form1.elements[m_chk_deposit_tmp].value+\"\";");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Other_Payments_Request?chksql=calculate_amount_new&amount=\"+unformat_noobject(document.Form1.elements[m_amount].value)+\"&wht_amount=\"+unformat_noobject(document.Form1.elements[m_wht_amount].value)+\"&adjust_type=\"+document.Form1.elements[adjust_type].value+\"&adjust_amount=\"+document.Form1.elements[m_adjust_amount].value+\"&chk_deposit_tmp=\"+document.Form1.elements[m_chk_deposit_tmp].value+\"\";");
				
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				//out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
				//out.println("wht_sum=wht_sum+parseFloat(unformat_noobject(document.Form1.elements[m_wht_amount].value));");
				
				out.println("sum=sum+parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_amount].value))*100)/100);");
				out.println("wht_sum=wht_sum+parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_wht_amount].value))*100)/100);");
				
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Other_Payments_Request?\"+m_send_val;");
				
				//out.println("m_adjust_amount=parseFloat(unformat_noobject(document.Form1.elements[m_adjust_amount].value));");
				out.println("m_adjust_amount=parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_adjust_amount].value))*100)/100)");
				out.println("m_adjust_type=document.Form1.elements[adjust_type].value;");
				//out.println("alert(wht_sum);");			
				out.println("if(m_adjust_amount >0){");
				out.println("if(m_adjust_type ==\"ADD\"){");
				out.println("sum=sum+m_adjust_amount;");
				out.println("}");			
				out.println("else if(m_adjust_type ==\"MIN\"){");
				out.println("sum=sum-m_adjust_amount");
				out.println("}");			
				out.println("}");			
				out.println("}");	
				
				out.println("else if(document.Form1.elements[m_chk_deposit_tmp].checked==false){");
				//out.println("sum=sum-parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
				out.println("sum=sum-parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_amount].value))*100)/100);");
				
				//out.println("wht_sum=wht_sum-parseFloat(unformat_noobject(document.Form1.elements[m_wht_amount].value));");
				out.println("wht_sum=wht_sum-parseFloat(Math.round((unformat_noobject(document.Form1.elements[m_wht_amount].value))*100)/100);");
				
				out.println("document.Form1.elements[m_amount].value=0;");
				out.println("document.Form1.elements[m_wht_amount].value=0;"); // ADDED BY ASHINI
				
				out.println("}");	
				
				out.println("document.Form1.TXT_PAYMENT_AMOUNT.value=format_noobject(sum)");
				out.println("document.Form1.TXT_WHT_AMT.value=format_noobject(wht_sum)");
				out.println("check_value();");	// added by ashini 		
				//out.println("}");	
				
			
				out.println("}");	
				
				*/
				
				
				
				out.println("function calculate_amount(){")	;
				out.println(" var sum=0; ");
				out.println(" var wht_sum=0; ");
				
				
				out.println("for(i=0;i<parseInt(document.Form1.hid_count.value);i++){");
				out.println("m_adjust_amount=\"TXT_ADJUST_AMOUNT\"+i;");
				out.println("adjust_type=\"TXT_ADJUST_MODE\"+i;");
				out.println("m_amount=\"TXT_AMOUNT\"+i;");
				out.println("m_wht_amount=\"TXT_WHT_AMOUNT\"+i;");
				
				out.println("m_chk_deposit_tmp=\"CHK_REQUIRED\"+i;");
				
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
				out.println("wht_sum=wht_sum+parseFloat(unformat_noobject(document.Form1.elements[m_wht_amount].value));");
				out.println("m_adjust_amount=parseFloat(unformat_noobject(document.Form1.elements[m_adjust_amount].value));");
				out.println("m_adjust_type=document.Form1.elements[adjust_type].value;");
				//out.println("alert(wht_sum);");			
				out.println("if(m_adjust_amount >0){");
				out.println("if(m_adjust_type ==\"ADD\"){");
				out.println("sum=sum+m_adjust_amount;");
				out.println("}");			
				out.println("else if(m_adjust_type ==\"MIN\"){");
				out.println("sum=sum-m_adjust_amount");
				out.println("}");			
				out.println("}");			
				
				out.println("}");			
				out.println("document.Form1.TXT_PAYMENT_AMOUNT.value=format_noobject(sum)");
				out.println("document.Form1.TXT_WHT_AMT.value=format_noobject(wht_sum)");
				out.println("check_value();");	// added by ashini 		
				out.println("}");			
				out.println("}");			
				
				
				out.println("function change_val_req(row_no){")	;
				out.println("var m_amount=0;");
				out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
				out.println("m_txt_amount=\"TXT_AMOUNT\"+row_no;");
				out.println("m_txt_balance_amount=\"TXT_BALANCE_AMOUNT\"+row_no;");
				out.println("m_txt_wht_amount=\"TXT_WHT_AMOUNT\"+row_no;");
				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("m_amount=parseFloat(unformat_noobject(document.Form1.elements[m_txt_amount].value));");
				//out.println("m_total_amount=parseFloat(m_total_amount)+parseFloat(m_amount);");
				out.println("if(m_amount==0) {");
				out.println("document.Form1.elements[m_txt_amount].value=document.Form1.elements[m_txt_balance_amount].value;");
				out.println("}");	
				
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				//out.println("document.Form1.elements[m_txt_amount].value=0;");
				//out.println("document.Form1.elements[m_txt_wht_amount].value=0;"); // ADDED BY ASHINI
				out.println("}");	
				
				//out.println("calculate_amount();"); // comment by nuwan de silva on 30-04-2008
				
				out.println("calculate_amount_new(row_no);"); //added by nuwan de silva on 30-04-2008
				
				
				/*out.println("for(i=0;i<parseInt(document.Form1.hid_count.value);i++){");
				out.println("m_amount=\"TXT_AMOUNT\"+i;");
				out.println("m_chk_deposit_tmp=\"CHK_REQUIRED\"+i;");
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
				out.println("}");			
				out.println("document.Form1.TXT_PAYMENT_AMOUNT.value=format_noobject(sum)");
				out.println("}");			
				*/
				out.println("}");	
				
				
				out.println("function help_licence_account_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				out.println("    Sql = \"m_help_licence_account_no\";");
				out.println("    Crit = document.Form1.TXT_LIC_ACC_NO.value+\"@Y@\";"); 
				//out.println("    HelpBox('1','10','0',Crit,Sql,'5');");
				out.println("    m_sql = Sql;"); 
				out.println("    m_criteria = Crit"); 
				out.println("    HelpBox('1','10','0');"); 
				
				out.println("}"); 
				out.println(""); 
				
				out.println("function assign_licence_account_no(oBj) {"); 
				out.println("    document.Form1.TXT_LIC_ACC_NO.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LIC_BRANCH_CODE.value=oBj.valout[3];"); 
				out.println("    document.Form1.TXT_LIC_BRANCH_NAME.value=oBj.valout[4];");
				out.println("}"); 
				
				//--------------------------------added by ashini on 07-03-2007-----------------
				
				/*out.println("function assign_netamt(){	"); 
				out.println("document.Form1.TXT_NET_AMT.value=document.Form1.TXT_PAYMENT_AMOUNT.value;");
			out.println("document.Form1.TXT_WHT_AMT.value=\"0.00\" ; ");
				out.println("}	");*/ 
				
				out.println("function check_value(){	"); 
				out.println("if(parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value))<parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value))){");
				out.println("alert('Withholding Tax amount cannot be greater than Gross amount');");
				out.println("document.Form1.TXT_WHT_AMT.value=\"0.00\" ; ");
				out.println("}");
				out.println("else{");
				/*out.println("sum=parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value)) - parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value)) ;");
					out.println("document.Form1.TXT_NET_AMT.value=format_noobject(sum)");
			    out.println("wht=parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value));");
					out.println("document.Form1.TXT_WHT_AMT.value=format_noobject(wht)");
					*/
				out.println("net=parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value)) - parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value)) ;");
				out.println("document.Form1.TXT_NET_AMT.value=format_noobject(net)");
				out.println("wht=parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value));");
				out.println("document.Form1.TXT_WHT_AMT.value=format_noobject(wht)");
				out.println("}");
				out.println("}");
				
				
				
				out.println("function check_wht_amount(obj,size,row){")	;
				out.println("if(obj.value!='')"); 
				out.println("amount=\"TXT_AMOUNT\"+row;");
				out.println("wht=\"TXT_WHT_AMOUNT\"+row;");
				out.println("if(isnumberok(obj,size)){"); 
				
				out.println("_m_amount=parseFloat(unformat_noobject(document.Form1.elements[amount].value));");
				out.println("_m_wht=parseFloat(unformat_noobject(document.Form1.elements[wht].value));");
				out.println("if(_m_wht >_m_amount) {");
				out.println("alert('WHT can not be greater than gross payment amount');");
				out.println("obj.value=0;");
				out.println("obj.focus();"); 
				out.println("}");
				out.println("else {");
				out.println("format_number(obj,size)"); 
				out.println("}");
				//out.println("calculate_amount();"); //comment by nuwan de silva on 30-04-2008
				//out.println("calculate_amount_new(row);"); //comment by nuwan de silva on 30-04-2008
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value='';"); 
				out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}	"); 
				
				out.println("function View_Letter(){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payment_Voucher_Generation\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=main_page';"); 
				out.println("}");
				
				out.println("function before_save(){");//Added By Sandun on 18-08-2009
				out.println("var tot_chk_amount=0;");
				out.println("var tot_chk_wht_amount=0;"); //added by ns on 22-09-2009
				
				out.println("var m_selected_amount     = parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value));");
				out.println("var m_selected_amount_wht = parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value));");
				
				//out.println("alert('m_selected_amount - '+m_selected_amount)");
				
				
				//out.println("receipt_count = document.Form1.hid_count.value;"); // commented by udara 17-03-2015
				
				// added by udara 17-03-2015
				out.println("if(document.Form1.hid_status.value=='Delete'){ ");
				out.println("   receipt_count = document.Form1.hid_no_of_rec.value;");
				out.println("} ");
				out.println("else{ ");
				out.println("   receipt_count = document.Form1.hid_count.value;");
				out.println("} ");
				// end by udara 17-03-2015
				
				//out.println("alert('receipt_count - '+receipt_count)");
				out.println("for(i=0;i<receipt_count;++i){");
				out.println("if (document.Form1.elements['CHK_REQUIRED'+i].value==\"on\") {"); //added by nuwan de silva 22-09-2009
				out.println("tot_chk_amount     = tot_chk_amount+parseFloat(unformat_noobject(document.Form1.elements['TXT_AMOUNT'+i].value))");
				out.println("tot_chk_wht_amount = tot_chk_wht_amount+parseFloat(unformat_noobject(document.Form1.elements['TXT_WHT_AMOUNT'+i].value))");
				out.println("}");
				
				out.println("}");
				//	out.println("alert('tot_chk_amount - '+tot_chk_amount)");
				out.println("if(parseFloat(tot_chk_amount)==parseFloat(m_selected_amount) && parseFloat(m_selected_amount_wht) == parseFloat(tot_chk_wht_amount) ){");
				out.println("return true;");
				out.println("}else{");	
				out.println("if(confirm('Amounts are mismatch.Do you want to reset the gross amount?')){");
				
				out.println("document.Form1.TXT_PAYMENT_AMOUNT.value = tot_chk_amount;");
				out.println("document.Form1.TXT_WHT_AMT.value        = tot_chk_wht_amount;");
				
				out.println("DIV_TXT_PAYMENT_AMOUNT.innerHTML = tot_chk_amount;");
				out.println("DIV_TXT_WHT_AMT.innerHTML        = tot_chk_wht_amount;");
				
				out.println("document.Form1.TXT_NET_AMT.value = parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value));");
				out.println("DIV_TXT_NET_AMT.innerHTML = parseFloat(tot_chk_amount)+parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value));");
				out.println("return true;");
				out.println("}");						
				out.println("}");					
				out.println("}");
				
				
				
				
				
				//--------end modifications done by ashini-----------------------------------------
				
				
				// added by udara 08-07-2014
				out.println("function set_total_last(){");
				
				out.println("  var m_count = parseInt(document.Form1.hid_count.value);");
				out.println("  var mm_balance_amount = 0; ");
				out.println("  var mm_adj_amount = 0; ");
				out.println("  var mm_amount = 0; ");
				out.println("  var mm_wht_amount = 0; ");
				
				out.println("   for(i=0; i<m_count; i++){ ");

				//out.println("    alert(  parseFloat(unformat_noobject(document.getElementById('TXT_BALANCE_AMOUNT'+i).value))  ); ");
				out.println("      mm_balance_amount = parseFloat(mm_balance_amount) + parseFloat(unformat_noobject(document.getElementById('TXT_BALANCE_AMOUNT'+i).value));  ");				
				out.println("      mm_adj_amount     = parseFloat(mm_adj_amount)     + parseFloat(unformat_noobject(document.getElementById('TXT_ADJUST_AMOUNT'+i).value));  ");	
				out.println("      mm_amount         = parseFloat(mm_amount)         + parseFloat(unformat_noobject(document.getElementById('TXT_AMOUNT'+i).value));  ");
				out.println("      mm_wht_amount     = parseFloat(mm_wht_amount)     + parseFloat(unformat_noobject(document.getElementById('TXT_WHT_AMOUNT'+i).value));  ");
				
				out.println("   }");
				
				out.println("    document.getElementById('bal_amnt').innerHTML = format_noobject(mm_balance_amount); ");
				out.println("    document.getElementById('adj_amnt').innerHTML = format_noobject(mm_adj_amount); ");
				out.println("    document.getElementById('m_amnt').innerHTML   = format_noobject(mm_amount); ");
				out.println("    document.getElementById('wht_amnt').innerHTML = format_noobject(mm_wht_amount); ");

				//out.println("      alert(mm_balance_amount); ");
				
				out.println("}");
				// end by udara 08-07-2014
				
				
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); //load_lock()
				out.println("<FORM NAME='Form1' method='post'>"); 
				//out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_PRO_PAYMENT_PROSESS\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_Client_Code' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_Guarantor_Code' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
				out.println("<input type=hidden name='hid_date' value=\"\">");
				out.println("<input type=hidden name='hid_date1' value=\"\">");
				out.println("<input type=hidden name='hid_date2' value=\"\">");
				out.println("<input type=hidden name='hid_no_of_rec' value=\"0\">");
				out.println("<input type=hidden name='TXT_LIC_ACC_NO' value=\"\">");
				out.println("<input type=hidden name='TXT_LIC_BRANCH_CODE' value=\"\">");
				out.println("<input type=hidden name='TXT_LIC_BRANCH_NAME' value=\"\">");
				
				
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Other Payments </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");'  onclick='load_screen_status(\"EDIT\")' value=\"Edit\" ></td>");  
				out.println("<td width='6%'>&nbsp;</td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");'  onclick='load_screen_status(\"DELETE\")' value=\"Delete\" ></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\" ></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_letter'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
				
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
				out.println("<tr class='tr_input'>");  
				out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
				out.println("</tr>");  
				out.println("</table>");  
				
				
				
				
				out.println("<table border=\"0\" align='center' width='100%' class='table'>"); 
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYMENT_NO'  class=div_input>Request No*</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYMENT_NO' maxlength='19' size='19' onblur=\"help_payment_no()\" disabled>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_PAYMENT_NO' value=\"...\" onClick=\"help_payment_no()\" disabled ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				//ADDED BY NUWAN DE SILVA ON 01-09-2008-
				
				// added by udara 23-05-2014
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_NO_OF_DAYS'  class=div_input>No. Of Days for Insurance Premium </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NO_OF_DAYS' maxlength='4' size='4' onblur=\"\" style={width:60px;}  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				// added by udara 23-05-2014
				
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_EFF_VALDATE1'  class=div_input>Date From </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input5' type='text' name='TXT_EFF_VALDATE_DD1' maxlength='2' size='2'  onBlur=\"check_date1(document.Form1.TXT_EFF_VALDATE_DD1,document.Form1.TXT_EFF_VALDATE_MM1,document.Form1.TXT_EFF_VALDATE_YY1)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_MM1' maxlength='2' size='2' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD1,document.Form1.TXT_EFF_VALDATE_MM1,document.Form1.TXT_EFF_VALDATE_YY1)\" >"); 
				//out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY1' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD1,document.Form1.TXT_EFF_VALDATE_MM1,document.Form1.TXT_EFF_VALDATE_YY1)\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>Calendar</a></td>"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY1' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD1,document.Form1.TXT_EFF_VALDATE_MM1,document.Form1.TXT_EFF_VALDATE_YY1)\" ><a href style='{cursor:hand; }' onclick=load_calendar('3')>Calendar</a></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_EFF_VALDATE2'  class=div_input>Date To</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input5' type='text' name='TXT_EFF_VALDATE_DD2' maxlength='2' size='2'  onBlur=\"check_date2(document.Form1.TXT_EFF_VALDATE_DD2,document.Form1.TXT_EFF_VALDATE_MM2,document.Form1.TXT_EFF_VALDATE_YY2)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_MM2' maxlength='2' size='2' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD2,document.Form1.TXT_EFF_VALDATE_MM2,document.Form1.TXT_EFF_VALDATE_YY2)\" >"); 
				//out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY2' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD2,document.Form1.TXT_EFF_VALDATE_MM2,document.Form1.TXT_EFF_VALDATE_YY2)\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>Calendar</a></td>"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY2' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD2,document.Form1.TXT_EFF_VALDATE_MM2,document.Form1.TXT_EFF_VALDATE_YY2)\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>Calendar</a></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_EFF_VALDATE'  class=div_input>Request Date </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input5' type='text' name='TXT_EFF_VALDATE_DD' maxlength='2' size='2'  onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_MM' maxlength='2' size='2' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>Calendar</a></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_BRANCH'  class=div_input>Branch</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH' maxlength='19' size='19' onblur=\"\" style={width:200px;} disabled >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				rs = stmt.executeQuery ("SELECT SUB_TYPE_CODE, DESCRIPTION "+
					" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
					" WHERE ACCOUNT_TYPE='L' "+
					" AND ACTIVE_STATUS='Y' ");
				
				more = rs.next();
				
				
				out.println("<tr>");
				out.println("<td width='20%' ><DIV id='DIV_TXT_ENTRY_TYPE'  class=div_input>Entry Type</DIV></td>"); 
				out.println("<td width='40%' ><select class=txt_input type=text name=TXT_ENTRY_TYPE maxlength=1 size=1 style=\"{width:150px;}\" >");  
				out.println("<option value=\"E\">Seizer</option>");
				//out.println("<option value=\"V\">Vendor</option>");
				out.println("<option value=\"L\">Lawyer</option>");
				out.println("<option value=\"A\">Advertistment</option>");
				while(more){
					out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					more = rs.next();
				}
				
				
				out.println("</select></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_SUSPENSE_REFERENSE'  class=div_input>Suspense Reference </DIV></td>"); 
				out.println("<td width='60%' ><input class='txt_input' type='text' name='TXT_SUSPENSE_REFERENSE' maxlength='19' size='19' onblur=\"help_suspense_referense()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_SUSPENSE_REFERENSE' value=\"...\" onClick=\"help_suspense_referense()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_REQUEST' value=\"Display Records\" style=\"{width:110px;}\" onClick=\"makeRequest_new()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_REQUEST' value=\"Records As To Date\" style=\"{width:110px;}\" onClick=\"makeRequest()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYEE_NAME'  class=div_input>Payee Name *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYEE_NAME' maxlength='100' size='19' onblur=\"\" style={width:350px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_VOUCHER'  class=div_input>Voucher Address</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VOUCHER_ADDRESS1' maxlength='100' size='19' onblur=\"\" style={width:250px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' >&nbsp;</td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VOUCHER_ADDRESS2' maxlength='100' size='19' onblur=\"\" style={width:250px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' >&nbsp;</td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VOUCHER_ADDRESS3' maxlength='100' size='19' onblur=\"\" style={width:250px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_TRANSACTION_TYPE'  class=div_input>Transaction Type </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TRANSACTION_TYPE' maxlength='19' size='19' onblur=\"help_txt_type()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_TRANSACTION_TYPE' value=\"Help\" onClick=\"help_txt_type()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				
				out.println("<tr>");
				out.println("<td width='20%' ><DIV id='DIV_TXT_SETTLE_MODE'  class=div_input>Payment Mode </DIV></td>"); 
				out.println("<td width='40%' ><select class=txt_input type=text name=TXT_SETTLE_MODE maxlength=1 size=1>");  
				out.println("<option value=\"CASH\" >Cash</option>");
				out.println("<option value=\"CHQ\"  selected>Cheque</option>");
				out.println("</select></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				//------------------------------Sandun on 07-01-2009--------------------------------------
				out.println("<tr>");
				out.println("<td width='20%' ><DIV id='DIV_TXT_AUTHO'  class=div_input>Authorization Level *</DIV></td>"); 
				out.println("<td width='40%' ><select class=txt_input type=text name=TXT_AUTHO maxlength=1 size=1>");  
				out.println("<option value=\"\" selected>&nbsp;</option>");
				out.println("<option value=\"DIV\" >Authorize By Division</option>");
				out.println("<option value=\"BNK\" >Bank Allocation</option>");
				out.println("</select></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				//----------------------------------------------------------------------------------------
				
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LIC_ACC_NO'  class=div_input>Licensee Account No * </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LIC_ACC_NO' maxlength='19' size='19' onblur=\"help_licence_account_no()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_LIC_ACC_NO' value=\"Help\" onClick=\"help_licence_account_no()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LIC_BRANCH_CODE'  class=div_input>Licensee Branch Code *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LIC_BRANCH_CODE' maxlength='19' size='19'  onblur=\"\"  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
							
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LIC_BRANCH_NAME'  class=div_input>Branch Name</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LIC_BRANCH_NAME' maxlength='19' size='19'  style=\"{width:200px;}\" onblur=\"\"  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYMENT_AMOUNT'  class=div_input>Payment Amount *  </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYMENT_AMOUNT' maxlength='19' size='19' style=\"{text-align:right;}\" onblur=\"\" disabled  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); */ 
				// COMMENETED BY ASHINI TO ADD THE FOLLOWING WHT AND NET PAYMENT
				
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Gross Payment *</DIV></td>"); // Payment Amount changed to Gross payment by ashini on 06.03.208
				out.println("<td width='40%' ><DIV id='DIV_TXT_PAYMENT_AMOUNT'  class=div_input><B>0.00</DIV></td>"); 
				out.println("<input class='txt_input' type='hidden' name='TXT_PAYMENT_AMOUNT'  value=0>");  
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				//--------------------------------added by ashini on 07-03-2007-----------------
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>WHT</DIV></td>"); 
				//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_WHT_AMT' maxlength='22' size='22' style=\"{text-align:right;}\"  disabled >"); 
				out.println("<td width='40%' ><DIV id='DIV_TXT_WHT_AMT'  class=div_input><B>0.00</DIV></td>"); 
				out.println("<input class='txt_input' type='hidden' name='TXT_WHT_AMT'  value=0>");  
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Net Payment</DIV></td>"); 
				//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NET_AMT' maxlength='22' size='22' style=\"{text-align:right;}\" disabled  >"); 
				out.println("<td width='40%' ><DIV id='DIV_TXT_NET_AMT'  class=div_input><B>0.00</DIV></td>"); 
				out.println("<input class='txt_input' type='hidden' name='TXT_NET_AMT'  value=0 >");  
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_EXCHANGE_RATE'  class=div_input>Exchange Rate</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EXCHANGE_RATE' maxlength='19' size='19' style=\"{text-align:right;}\" onblur=\"\" disabled  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYMENT_AMOUNT'  class=div_input>Reporting Amount</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REPORTING_AMOUNT' maxlength='19' size='19' style=\"{text-align:right;}\" onblur=\"\" disabled  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				
				
				/*out.println("<tr>");
				out.println("<td width='20%' ><DIV id='DIV_TXT_REC_STATUS'  class=div_input>Reconcoled Status </DIV></td>"); 
				out.println("<td width='40%' ><select class=txt_input type=text name=TXT_REC_STATUS maxlength=1 size=1>");  
				out.println("<option value=\"Y\" >Yes</option>");
				out.println("<option value=\"N\"  selected>No</option>");
				out.println("</select></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				*/
				
				out.println("<tr>"); 
				out.println("<td width='20%' valign ='top'>Description</td>"); 
				out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_REMARKS' style=\"width:350px; height:50px; \" maxlength='500' size='500'  onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); //onblur=\"count_length(this)\"
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				
				
				
				out.println("</table>"); 
				
				/*out.println("<table border=\"0\" align='center' width='100%' class='table'>"); 
				out.println("<tr>");
				out.println("<td width='25%' ><DIV id='DIV_TXT_LIC_ACC_NO'  class=div_input>Licensee Account No *</DIV></td>"); 
				out.println("<td width='12%' ><DIV id='DIV_TXT_LIC_BRANCH_CODE'  class=div_input>Licensee Branch Code *</DIV></td>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_LIC_BRANCH_NAME'  class=div_input>Branch Name </DIV></td>"); 
				out.println("<td width='12%' ><DIV id='DIV_TXT_PAY_AMOUNT'  class=div_input>Payment Amount *</DIV></td>"); 
				out.println("<td width='12%' ><DIV id='DIV_TXT_EXCHANGE_RATE'  class=div_input>Exchange Rate </DIV></td>"); 
				out.println("<td width='12%' ><DIV id='DIV_TXT_RPT_AMOUNT'  class=div_input>Reporting Amount </DIV></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td width='25%'  align=\"left\"><input class='txt_input' type='text' name='TXT_LIC_ACC_NO' maxlength='20' size='10' onblur=\"check_LIC_acc(document.Form1.TXT_LIC_ACC_NO.value)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_LIC_ACC_NO' value=\"Help\" onClick=\"help_button_7()\"></td>"); 
				out.println("<td width='12%' align=\"left\"><input class='txt_input' type='text' name='TXT_LIC_BRANCH_CODE' maxlength='10' size='10' disabled></td>");
				out.println("<td width='15%' align=\"left\"><input class='txt_input' style='{text-align:left;}' type='text' name='TXT_LIC_BRANCH_NAME' maxlength='22' size='22' value=\"\" onblur=\"\"></td>");
				out.println("<td width='12%' align=\"left\"><input class='txt_input' style='{text-align:right;}' type='text' name='TXT_PAY_AMOUNT' maxlength='22' size='22' value=\"\" onblur=\"calculate(document.Form1.TXT_PAY_AMOUNT.value)\"></td>"); 
				out.println("<td width='12%' align=\"left\"><input class='txt_input'  type='text' name='TXT_EXCHANGE_RATE' maxlength='22' size='22' disabled></td>"); 
				//out.println("<td width='12%' align=\"left\"><select class='txt_input' type=text name=TXT_RECON_STATUS maxlength=1 size=1 style='{height:10px}'>");  
				//out.println("<option value=\"N\" selected>No</option>");
				//out.println("<option value=\"Y\">Yes</option>");
				//out.println("</select></td>");
				out.println("<td width='12%' align=\"left\"><input class='txt_input' style='{text-align:right;}' type='text' name='TXT_RPT_AMOUNT' maxlength='20' size='18' disabled></td>"); 
				out.println("</tr>"); 
				out.println("</table>");   
			*/
				
				out.println("<br>");  
				
				out.println("<table align='center'  border=\"0\" width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><div id='m_table'></div></td>");
				out.println("</tr>"); 	
				out.println("<table align='center'  border=\"0\" width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><div id='payment_allocation'></div></td>");
				out.println("</tr>"); 
				
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
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				out.println("</body>"); 
				out.println("</html>"); 
				
			}		
			
			//=================================================================================================================================				
			
			// commented by udara 18-08-2015
			/*
			else if(m_chksql.equals("payment_allocation_details")){
				
				String m_receiver = req.getParameter("receiver").trim();																		
				String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
				String m_value_date = req.getParameter("value_date").trim();																		
				
				String m_no_of_days = req.getParameter("no_of_days").trim(); // added by udara 23-05-2014
				
				
				
				if(m_no_of_days.equals("")){
						rs = stmt.executeQuery (" SELECT  "+
							" DISTINCT SUS_REF_NO, "+
							" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ') , "+
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
							" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+
							" BAL_TO_BE_PAID, "+
							" REF_NO, "+
							" RECEIVER,   "+
							" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
							" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE,    "+
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))))),' ') "+ //Added by Minal on 14-08-2014 for #13677
							" ,ROUND(SYSDATE - VALUE_DATE) NO_OF_DAYS "+ // udara 20-08-2014
							" ,"+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))) "+ // 12 added by udara 08-05-2015
							" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
							//	" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+   		--- COMMENT BY PRABASH ON 20-07-2011------
							" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('"+m_suspense_entry_type+"')  "+ //--- COMMENT BY PRABASH ON 20-07-2011------
							
							
							" AND VALUE_DATE <= TO_DATE('"+m_value_date+"','DD-MM-YYYY')"+
							" AND BAL_TO_BE_PAID > 0 "+
							//" ORDER BY REF_NO ");
							" ORDER BY NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ')  ");
				}
				else{
					
					
					
					// added by udara 03-06-2015
					rs = stmt.executeQuery (" SELECT  "+
						" DISTINCT A.SUS_REF_NO, "+
						" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)),' ') , "+
						" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)), "+
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO))),' ') , "+
						" A.BAL_TO_BE_PAID, "+
						" A.REF_NO, "+
						" A.RECEIVER,   "+
						" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(A.REF_NO,A.RECEIVER),' ') PAYEE_NAME,  "+
						" NVL(decode(A.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE,    "+
						" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))))),' ') "+ //Added by Minal on 14-08-2014 for #13677
						" ,ROUND(SYSDATE - B.START_DATE) NO_OF_DAYS "+ // udara 20-08-2014
						" ,"+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS(B.FINANCE_NO) "+ // 12 added by udara 08-05-2015
						" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A, "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA B, "+m_schema_name+".AF_CO_PRO_INVOICE C "+
						" WHERE  A.REF_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO(B.FINANCE_NO) "+
						" AND B.REF_DEBIT_NOTE_NO = C.INVOICE_NO "+
						" AND UPPER(A.RECEIVER) LIKE UPPER('"+m_receiver+"%')   "+
						" AND B.START_DATE + "+m_no_of_days+" <= TO_DATE('"+m_value_date+"','DD-MM-YYYY') "+
						" AND A.BAL_TO_BE_PAID > 0 "+
						" AND "+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS(B.FINANCE_NO) IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS')     "+ // added by udara 19-12-2014
						//" AND TO_DATE("+m_schema_name+".AF_CO_GET_INVOICE_DUE_DATE(B.REF_DEBIT_NOTE_NO),'DD-MM-YYYY')  <= TRUNC(SYSDATE)   "+ // commented by udara 20-05-2015
						//" AND (C.DUE_DATE <= TRUNC(SYSDATE) OR C.SETTELE_AMOUNT > 0) "+ // commented by udara 05-06-2015
						//" AND (C.DUE_DATE <= TRUNC(SYSDATE) OR C.ADJUSTED_AMOUNT > 0) "+ // added by udara 05-06-2015
						" AND (C.DUE_DATE <= TRUNC(SYSDATE) OR C.ADJUSTED_AMOUNT > 0 OR C.SETTELE_AMOUNT > 0) "+ // added by udara 09-06-2015
						
						" AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('%"+m_suspense_entry_type+"%')  "+ // released by udara 17-07-2015
						
						" ORDER BY NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)),' ')  ");
					// end by udara 03-06-2015
					
					
				}
				
				
				
				
				
				boolean more = rs.next();
				
				out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
				
				if(more){
					out.println("<tr class=pdn_txtpos2 > ");
					out.println("<td width='11%' >Finance No</td>");
					out.println("<td width='11%' >Registration No.</td>");
					//out.println("<td width='11%' >Sus Ref No</td>");
					out.println("<td width='26%' >Client Name</td>");
					out.println("<td width='13%' align='right'> No. Of Days</td>"); // udara 20-08-2014
					out.println("<td width='1%' > &nbsp; </td>"); // udara 20-08-2014
					out.println("<td width='13%' align='left' >Entry Type</td>");		//---added by prabash on 20-07-2011-----
					//out.println("<td width='5%' >Branch</td>"); 
					out.println("<td width='12%' align='right'>Balance Amount</td>");
					out.println("<td width='15%' align='right'>Adjustment Amount&nbsp;&nbsp;</td>");
					out.println("<td width='10%' >Adjust Type</td>");
					out.println("<td width='12%' align='right'>Amount</td>");
					out.println("<td width='12%' align='right'>WHT</td>");
					out.println("<td width='2%' >&nbsp; </td>"); // <INPUT TYPE=\"checkbox\" NAME=\"SELECT_ALL_CHKBX\" onclick=\"select_all();\"  >
					out.println("</tr>"); 
				}
				int j = 0;   
				
				double bal_amnt = 0;
				double adj_amnt = 0;
				double m_amnt = 0;
				double wht_amnt = 0;
				
				while(more){
					
					
				   bal_amnt = bal_amnt + rs.getDouble(5);
				   adj_amnt = adj_amnt + 0;
				   m_amnt = m_amnt + rs.getDouble(5);
				   wht_amnt = wht_amnt + 0;
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					// added by udara 08-05-2015
					if(j>0 && j%2==1){						
						if(rs.getString(12).equals("TERMI"))
							out.println("<tr class=tr_input1 style='{background-color: red;}' >"); 
						else if(rs.getString(12).equals("TERMINATED"))
							out.println("<tr class=tr_input1 style='{background-color: red;}' >"); 
						else
							out.println("<tr class=tr_input1 >"); 
					}
					else{
						if(rs.getString(12).equals("TERMI"))
							out.println("<tr class=tr_input style='{background-color: red;}' >"); 
						else if(rs.getString(12).equals("TERMINATED"))
							out.println("<tr class=tr_input style='{background-color: red;}' >"); 
						else
							out.println("<tr class=tr_input >"); 
					}
					// end by udara 08-05-2015
					
					out.println("<td width='11%' style='{cursor:hand;}' onClick=\"show_transaction_info('"+rs.getString(3)+"','"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u></td>");  
					//out.println("<td width='11%' style='{cursor:hand;}' onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>"); 
					//out.println("<td width='11%' >"+rs.getString(1)+"</td>");
					out.println("<td width='11%' >"+rs.getString(10)+"</td>"); //added by Minal on 14-08-2014 for #13677
					out.println("<td width='26%' >"+rs.getString(4)+"</td>"); 
					out.println("<td width='13%' align='right' >"+rs.getString(11)+"</td>"); // udara 20-08-2014
					out.println("<td width='1%' > &nbsp; </td>"); // udara 20-08-2014
					out.println("<td width='26%' >"+rs.getString(7)+"</td>"); //---added by prabash on 04-07-2011-----
					//out.println("<td width='5%' >"+rs.getString(4)+"</td>"); 
					//out.println("<td width='15%' >"+rs.getString(5)+"</td>"); 
					out.println("<td width='12%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+j+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"set_total_last();\"  >"); 
					//out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"enable_adjusted_type(this,"+j+");set_total_last();\" style=\"{text-align:right;}\"  >"); // commented by udara 10-03-2015
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"set_total_last();\" style=\"{text-align:right;}\"  >");  // added by udara 10-03-2015
					out.println("<td width='10%' ><select class=txt_input type=text name=TXT_ADJUST_MODE"+j+" maxlength=1 size=1 disabled onChange=\"\"  >");  //calculate_amount()
					out.println("<option value=\"ADD\" >Addition</option>");
					out.println("<option value=\"MIN\"  selected>Deduction</option>");
					out.println("</select></td>");
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+j+");set_total_last();\"  style=\"{text-align:right;}\" value=\""+nf.format(rs.getDouble(5))+"\" ></td>"); 
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_WHT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+j+");check_wht_amount(this,22,"+j+");change_val_req("+j+");set_total_last();\"  style=\"{text-align:right;}\" value=\"0\"  ></td>"); // ADDED BY ASHINI
					out.println("<TD WIDTH=\"2%\" align=\"center\"><INPUT TYPE=\"checkbox\" CHECKED NAME=CHK_REQUIRED"+j+" VALUE=\"on\" onclick=\"change_val_req("+j+")\"  ></td>");		
					out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+j+" value="+rs.getString(1)+" >");
					out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+j+" value="+rs.getString(2)+" >");
					out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+j+" value="+rs.getString(3)+" >");
					
					out.println("</font>"); // added by udara 08-05-2015 	
					
					out.println("</tr>"); 		
					more = rs.next();
					j=j+1;
				}
				
				// added by udara 08-07-2014
				
					out.println("<td width='11%' ><b> Total </b></td>"); 
					out.println("<td width='11%' > &nbsp; </td>"); 
					out.println("<td width='26%' > &nbsp; </td>"); 
					out.println("<td width='13%' > &nbsp; </td>"); 
					out.println("<td width='1%' > &nbsp; </td>"); 
					out.println("<td width='26%' > &nbsp; </td>"); 
					out.println("<td width='12%' align='right'><b> <div id='bal_amnt' > "+nf.format(bal_amnt)+" </div> </b></td>");  //nf.format(0) 
					out.println("<td width='15%' align='right'><b> <div id='adj_amnt' > "+nf.format(adj_amnt)+"</div> </b></td>"); 
					out.println("<td width='10%' > &nbsp; </td>"); 
					out.println("<td width='12%' align='right'><b> <div id='m_amnt'   > "+nf.format(m_amnt)+" </div> </b></td>"); 
					out.println("<td width='12%' align='right'><b> <div id='wht_amnt' > "+nf.format(wht_amnt)+" </div> </b></td>"); 
					out.println("<td width='2%' > &nbsp; </td>"); 	
					out.println("</tr>");
				
				// end by udara 08-07-2014
				
				out.println("</table>");
				
				out.println("<input type=hidden name=hid_count value="+j+"></table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				
				
				
			}
			
			*/
			
			// added by udara 18-08-2015
			
			else if(m_chksql.equals("payment_allocation_details")){
				
				String m_receiver = req.getParameter("receiver").trim();																		
				String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
				String m_from_date = req.getParameter("from_date").trim();																		
				String m_to_date = req.getParameter("to_date").trim();		
			
				String m_no_of_days = req.getParameter("no_of_days").trim(); // added by udara 23-05-2014			

				// added by udara 23-05-2014
				
				if(m_no_of_days.equals("")){
				
					// commented by udara 26-01-2018
					/*
					rs = stmt.executeQuery (" SELECT  "+
						" DISTINCT SUS_REF_NO, "+
						" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ') , "+
						" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+
						" BAL_TO_BE_PAID, "+
						" REF_NO, "+
						" RECEIVER,   "+
						" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
						" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE ,   "+
						" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))))),' ') "+ //Added by Minal on 14-08-2014 for #13677
						" ,ROUND(SYSDATE - VALUE_DATE) NO_OF_DAYS "+ // udara 20-08-2014
						" ,NVL("+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),'-') "+ // 12 added by udara 08-05-2015
						" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
						// " WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+     --- COMMENT BY PRABASH ON 04-07-2011------
						//" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('%"+m_suspense_entry_type+"%')  "+ // commented by udara 31-03-2015 //--- ADDED BY PRABASH 04-07-2011---------
						
						// added by udara 31-03-2015
						" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') "+
						" AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('%"+m_suspense_entry_type+"%')  "+ // released by udara 17-07-2015
						// end by udara 31-03-2015
						
						//" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%')   "+ // added by udara 25-07-2014
						
						" AND VALUE_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+
						
						//" AND VALUE_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND BAL_TO_BE_PAID > 0 "+
						//" ORDER BY REF_NO ");
						" ORDER BY NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ')  ");
						*/
					
					
					// added by udara 26-01-2018
					rs = stmt.executeQuery (" "+
							" SELECT "+
								" C.SUS_REF_NO, "+
								" A.FINANCE_NO, "+
								" A.CLIENT_CODE, "+
								" B.FULL_NAME, "+
								" C.BAL_TO_BE_PAID, "+
								" C.REF_NO, "+
								" C.RECEIVER, "+
								" C.PAYEE_NAME, "+
								" C.SUSPENSE_ENTRY_TYPE, "+
								" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),' '), "+
								" C.NO_OF_DAYS, "+
								" A.APPLICATION_STATUS "+
								" FROM  "+
								" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
								" "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
								" (SELECT      DISTINCT "+
								             " SUS_REF_NO,  "+
								             " "+m_schema_name+".af_co_get_fin_no_n(REF_NO) APP_NO, "+
								             " BAL_TO_BE_PAID, "+
														 " REF_NO, "+
														 " RECEIVER,  "+  
														 " NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
														 " NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE , "+  
								             " ROUND(SYSDATE - VALUE_DATE) NO_OF_DAYS "+
								             " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT   "+
								
														 " WHERE RECEIVER LIKE UPPER('"+m_receiver+"%')  "+
														 //" AND SUSPENSE_ENTRY_TYPE LIKE UPPER('%"+m_suspense_entry_type+"%')   "+
															" AND SUSPENSE_ENTRY_TYPE = '"+m_suspense_entry_type+"'   "+
								
														 " AND VALUE_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
														 " AND BAL_TO_BE_PAID > 0 "+
								             " ) C "+
								" WHERE  A.APPLICATION_NO =  C.APP_NO  "+
								" AND A.CLIENT_CODE = B.CLIENT_CODE "+
								" AND A.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS','CANCEL_PO') "+
								//" ORDER BY A.FINANCE_NO "+ // commented by udara 13-10-2021
								" ORDER BY C.BAL_TO_BE_PAID  "+ // added by udara 13-10-2021
             			" ");
					// end by udara 26-01-2018
	
					
					
				}
				else{
					
					
					// commented by udara 26-01-2018
					/*
					// aqdded by udara 03-09-2015
					rs = stmt.executeQuery ("  "+
						" SELECT   "+
						 " DISTINCT A.SUS_REF_NO,  "+
						 " NVL(B.FINANCE_NO,' ') ,  "+
						 " D.CLIENT_CODE,  "+
						 " NVL(LAKDL.AF_CO_GET_CLIENT_NAME(D.CLIENT_CODE),' ') ,  "+
						 " A.BAL_TO_BE_PAID,  "+
						 " A.REF_NO,  "+
						 " A.RECEIVER,    "+
						 " NVL("+m_schema_name+".AF_CO_GET_REF_NAME(A.REF_NO,A.RECEIVER),' ') PAYEE_NAME,   "+
						 " NVL(DECODE(A.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE ,  "+ 
						 " NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(D.APPLICATION_NO),' ')   "+
						 " ,ROUND(SYSDATE - B.START_DATE) NO_OF_DAYS  "+
						 " ,NVL("+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS(B.FINANCE_NO),'-')   "+
						 " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A, "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA B, "+m_schema_name+".AF_CO_PRO_INVOICE C , AF_CO_PRO_APPLICATION_DETAILS D "+
						 //" WHERE  A.REF_NO =NVL(B.REF_DEBIT_NOTE_NO,D.APPLICATION_NO)  "+ // commented by udara 30-11-2016
							" WHERE  A.REF_NO = B.REF_DEBIT_NOTE_NO  "+ // added by udara 30-11-2016
						 " AND B.REF_DEBIT_NOTE_NO = C.INVOICE_NO  "+
             			 " AND B.FINANCE_NO = D.FINANCE_NO "+
						 " AND UPPER(A.RECEIVER) LIKE UPPER('"+m_receiver+"%') "+
						 " AND B.START_DATE  <= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						 " AND D.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS')    "+ 
						 " AND A.BAL_TO_BE_PAID > 0  "+
						 " AND B.REF_DEBIT_NOTE_NO IS NOT NULL   "+
						 " AND ROUND(SYSDATE - B.START_DATE) >= "+m_no_of_days+"  "+
						 " AND (C.DUE_DATE <= TRUNC(SYSDATE) OR C.ADJUSTED_AMOUNT > 0 OR C.SETTELE_AMOUNT > 0)  "+
						 " AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('"+m_suspense_entry_type+"%')   "+
						 " ORDER BY NVL(B.FINANCE_NO,' ') "+
						" ");
					// end  by udara 03-09-2015
					*/
					
					
					// added by udara 26-01-2018
					rs = stmt.executeQuery (" "+
							" SELECT "+
								" C.SUS_REF_NO, "+
								" A.FINANCE_NO, "+
								" A.CLIENT_CODE, "+
								" B.FULL_NAME, "+
								" C.BAL_TO_BE_PAID, "+
								" C.REF_NO, "+
								" C.RECEIVER, "+
								" C.PAYEE_NAME, "+
								" C.SUSPENSE_ENTRY_TYPE, "+
								" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),' '), "+
								" C.NO_OF_DAYS, "+
								" A.APPLICATION_STATUS "+
								" FROM  "+
								" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
								" "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
								" ( "+
								
									 " SELECT "+ 
										 " DISTINCT X.SUS_REF_NO, "+
										 " "+m_schema_name+".af_co_get_fin_no_n(X.REF_NO) APP_NO, "+
										 " X.BAL_TO_BE_PAID, "+
										 " X.REF_NO, "+ 
										 " X.RECEIVER, "+  
										 " NVL("+m_schema_name+".AF_CO_GET_REF_NAME(X.REF_NO,X.RECEIVER),' ') PAYEE_NAME, "+ 
										 " NVL(decode(X.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE , "+  
										 " ROUND(SYSDATE - Y.START_DATE) NO_OF_DAYS "+
											 " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA Y, "+m_schema_name+".AF_CO_PRO_INVOICE Z "+
											 " WHERE  X.REF_NO = Y.REF_DEBIT_NOTE_NO  "+
											 " AND Y.REF_DEBIT_NOTE_NO = Z.INVOICE_NO "+
											 " AND X.RECEIVER LIKE UPPER('"+m_receiver+"%')   "+ 
											 " AND ROUND(SYSDATE - Y.START_DATE) >= "+m_no_of_days+"  "+
											 " AND Y.START_DATE  <= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
											 " AND X.BAL_TO_BE_PAID > 0 "+
											 " AND Y.REF_DEBIT_NOTE_NO IS NOT NULL  "+
											 " AND (Z.DUE_DATE <= TRUNC(SYSDATE) OR Z.ADJUSTED_AMOUNT > 0 OR Z.SETTELE_AMOUNT > 0) "+
											 //" AND X.SUSPENSE_ENTRY_TYPE LIKE UPPER('%"+m_suspense_entry_type+"%') "+
												" AND X.SUSPENSE_ENTRY_TYPE = '"+m_suspense_entry_type+"' "+ // added by udara 02-08-2018
															
								             " ) C "+
								" WHERE  A.APPLICATION_NO =  C.APP_NO  "+
								" AND A.CLIENT_CODE = B.CLIENT_CODE "+
								" AND A.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS') "+
								//" ORDER BY A.FINANCE_NO "+ // commented by udara 13-10-2021
								" ORDER BY C.BAL_TO_BE_PAID  "+ // added by udara 13-10-2021
             			" ");
					// end by udara 26-01-2018
					
					
				}
				
				// end by udara 23-05-2014
				
				
				
				
				boolean more = rs.next();
				
				out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
				
				if(more){
					out.println("<tr class=pdn_txtpos2 > "); 
					out.println("<td width='11%' >Finance No</td>"); 
					out.println("<td width='11%' >Registration No.</td>");
					//out.println("<td width='11%' >Sus Ref No</td>"); 
					out.println("<td width='26%' >Client Name</td>"); 
					out.println("<td width='13%' align='right' >No. of days</td>");  // udara 20-08-2014
					out.println("<td width='1%'  > &nbsp; </td>");  // udara 20-08-2014
					out.println("<td width='13%' align='left' >Entry Type</td>"); 		//---added by prabash on 04-07-2011-----
					//out.println("<td width='5%' >Branch</td>"); 
					//	out.println("<td width='12%' align='right'>broker</td>");
					out.println("<td width='12%' align='right'>Balance Amount</td>"); 
					out.println("<td width='15%' align='right'>Adjustment Amount&nbsp;&nbsp;</td>"); 
					out.println("<td width='10%' >Adjust Type</td>"); 
					out.println("<td width='12%' align='right'>Amount</td>"); 
					out.println("<td width='12%' align='right'>WHT</td>"); 
					
					out.println("<td width='2%' >&nbsp;</td>"); 
					out.println("</tr>"); 
				}
				int j = 0;    
				
				// added by udara 17-02-2015
				double bal_amnt = 0;
				double adj_amnt = 0;
				double m_amnt = 0;
				double wht_amnt = 0;
				// end by udara 17-02-2015
				
				while(more){
					
					
					
					// added by udara 08-05-2015
					if(j>0 && j%2==1){						
						if(rs.getString(12).equals("TERMI"))
							out.println("<tr class=tr_input1 style='{background-color: red;}' >"); 
						else if(rs.getString(12).equals("TERMINATED"))
							out.println("<tr class=tr_input1 style='{background-color: red;}' >"); 
						else
							out.println("<tr class=tr_input1  >"); 
					}
					else{
						if(rs.getString(12).equals("TERMI"))
							out.println("<tr class=tr_input style='{background-color: red;}' >"); 
						else if(rs.getString(12).equals("TERMINATED"))
							out.println("<tr class=tr_input style='{background-color: red;}' >"); 
						else
							out.println("<tr class=tr_input  >"); 
					}
					// end by udara 08-05-2015
					
					
					// added by udara 17-02-2015
					bal_amnt = bal_amnt + rs.getDouble(5);
				    adj_amnt = adj_amnt + 0;
				    m_amnt = m_amnt + 0;
				    wht_amnt = wht_amnt + 0;
					// end by udara 17-02-2015
					
					out.println("<td width='11%' style='{cursor:hand;}' onClick=\"show_transaction_info('"+rs.getString(3)+"','"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>"); 
					//out.println("<td width='11%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='11%' >"+rs.getString(10)+"</td>"); //added by Minal on 14-08-2014 for #13677
					out.println("<td width='26%' >"+rs.getString(4)+"</td>"); 
					out.println("<td width='13%' align='right' >"+rs.getString(11)+"</td>"); // udara 20-08-2014
					out.println("<td width='1%'  > &nbsp; </td>");  // udara 20-08-2014
					out.println("<td width='13%' >"+rs.getString(7)+"</td>"); //---added by prabash on 04-07-2011-----
					//out.println("<td width='5%' >"+rs.getString(4)+"</td>"); 
					//out.println("<td width='15%' >"+rs.getString(5)+"</td>"); 
					out.println("<td width='12%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+j+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"set_total_last();\"  >"); 
					//out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"enable_adjusted_type(this,"+j+");set_total_last();\" style=\"{text-align:right;}\" >"); // commented by udara 10-03-2015 // added set_total_last(); by udara 17-02-2015
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"set_total_last();\" style=\"{text-align:right;}\" >"); // added by udara 10-03-2015
					out.println("<td width='10%' ><select class=txt_input type=text name=TXT_ADJUST_MODE"+j+" maxlength=1 size=1 disabled onChange=\"\"  >");  //calculate_amount()
					out.println("<option value=\"ADD\" >Addition</option>");
					out.println("<option value=\"MIN\"  selected>Deduction</option>");
					out.println("</select></td>");
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+j+"); set_total_last();\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); // added set_total_last(); by udara 17-02-2015
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_WHT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+j+");check_wht_amount(this,22,"+j+");set_total_last();\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); // ADDED BY ASHINI // added set_total_last(); by udara 17-02-2015
					out.println("<TD WIDTH=\"2%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+");set_total_last();\"  ></td>"); // added set_total_last(); by udara 17-02-2015			
					out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+j+" value="+rs.getString(1)+" >");
					out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+j+" value="+rs.getString(2)+" >");
					out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+j+" value="+rs.getString(3)+" >");
					
					out.println("</font>"); // added by udara 08-05-2015 	
					
					out.println("</tr>"); 		
					more = rs.next();
					j=j+1;
				}
				
				// added by udara 17-02-2015
					out.println("<td width='11%' ><b> Total </b></td>"); 
					out.println("<td width='11%' > &nbsp; </td>"); 
					out.println("<td width='26%' > &nbsp; </td>"); 
					out.println("<td width='13%' > &nbsp; </td>"); 
					out.println("<td width='1%' > &nbsp; </td>"); 
					out.println("<td width='26%' > &nbsp; </td>"); 
					out.println("<td width='12%' align='right'><b> <div id='bal_amnt' > "+nf.format(bal_amnt)+" </div> </b></td>");  //nf.format(0) 
					out.println("<td width='15%' align='right'><b> <div id='adj_amnt' > "+nf.format(adj_amnt)+"</div> </b></td>"); 
					out.println("<td width='10%' > &nbsp; </td>"); 
					out.println("<td width='12%' align='right'><b> <div id='m_amnt'   > "+nf.format(m_amnt)+" </div> </b></td>"); 
					out.println("<td width='12%' align='right'><b> <div id='wht_amnt' > "+nf.format(wht_amnt)+" </div> </b></td>"); 
					out.println("<td width='2%' > &nbsp; </td>"); 	
					out.println("</tr>");
				// end by udara 17-02-2015
				
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				out.println("<input type=hidden name=hid_count value="+j+"></table>");
				
				
				
			}
			
			// end by udara 18-08-2015
			
			else if(m_chksql.equals("payment_allocation_details_new")){
				
				String m_receiver = req.getParameter("receiver").trim();																		
				String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
				String m_from_date = req.getParameter("from_date").trim();																		
				String m_to_date = req.getParameter("to_date").trim();		
			
				String m_no_of_days = req.getParameter("no_of_days").trim(); // added by udara 23-05-2014			

				// added by udara 23-05-2014
				
				if(m_no_of_days.equals("")){
				
					
					// commented by udara 26-01-2018
					/*
					rs = stmt.executeQuery (" SELECT  "+
						" DISTINCT SUS_REF_NO, "+
						" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ') , "+
						" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+
						" BAL_TO_BE_PAID, "+
						" REF_NO, "+
						" RECEIVER,   "+
						" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
						" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE ,   "+
						" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))))),' ') "+ //Added by Minal on 14-08-2014 for #13677
						" ,ROUND(SYSDATE - VALUE_DATE) NO_OF_DAYS "+ // udara 20-08-2014
						" ,NVL("+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),'-') "+ // 12 added by udara 08-05-2015
						" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
						// " WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+     --- COMMENT BY PRABASH ON 04-07-2011------
						//" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('%"+m_suspense_entry_type+"%')  "+ // commented by udara 31-03-2015 //--- ADDED BY PRABASH 04-07-2011---------
						
						// added by udara 31-03-2015
						" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') "+
						" AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('%"+m_suspense_entry_type+"%')  "+ // released by udara 17-07-2015
						// end by udara 31-03-2015
						
						//" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%')   "+ // added by udara 25-07-2014
						" AND VALUE_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+
						" AND VALUE_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND BAL_TO_BE_PAID > 0 "+
						" AND "+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))) IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS','CANCEL_PO')     "+ //Added by Jithendra 27.04.2016
						//" ORDER BY REF_NO ");
						" ORDER BY NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ')  ");
						*/
					
					// added by udara 26-01-2018
					
					rs = stmt.executeQuery (" "+
							" SELECT "+
								" C.SUS_REF_NO, "+
								" A.FINANCE_NO, "+
								" A.CLIENT_CODE, "+
								" B.FULL_NAME, "+
								" C.BAL_TO_BE_PAID, "+
								" C.REF_NO, "+
								" C.RECEIVER, "+
								" C.PAYEE_NAME, "+
								" C.SUSPENSE_ENTRY_TYPE, "+
								" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),' '), "+
								" C.NO_OF_DAYS, "+
								" A.APPLICATION_STATUS "+
								" FROM  "+
								" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
								" "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
								" (SELECT      DISTINCT "+
								             " SUS_REF_NO,  "+
								             " "+m_schema_name+".af_co_get_fin_no_n(REF_NO) APP_NO, "+
								             " BAL_TO_BE_PAID, "+
														 " REF_NO, "+
														 " RECEIVER,  "+  
														 " NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
														 " NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE , "+  
								             " ROUND(SYSDATE - VALUE_DATE) NO_OF_DAYS "+
								             " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT   "+
								
														 " WHERE RECEIVER LIKE UPPER('"+m_receiver+"%')  "+
														 //" AND SUSPENSE_ENTRY_TYPE LIKE UPPER('%"+m_suspense_entry_type+"%')   "+ // commented by udara 02-08-2018
															" AND SUSPENSE_ENTRY_TYPE = '"+m_suspense_entry_type+"'   "+ // added by udara 02-08-2018
								
														 " AND VALUE_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
														 " AND VALUE_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
														 " AND BAL_TO_BE_PAID > 0 "+
								             " ) C "+
								" WHERE  A.APPLICATION_NO =  C.APP_NO  "+
								" AND A.CLIENT_CODE = B.CLIENT_CODE "+
								" AND A.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS','CANCEL_PO') "+
								//" ORDER BY A.FINANCE_NO "+ // commented by udara 13-10-2021
								" ORDER BY C.BAL_TO_BE_PAID  "+ // added by udara 13-10-2021
             			" ");
             
             
					
					
					
				}
				else{
					
					
					// commented by udara 26-01-2018
					/*
					// added by udara 03-06-2015
					rs = stmt.executeQuery (" SELECT  "+
						" DISTINCT A.SUS_REF_NO, "+
						" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)),' ') , "+
						" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)), "+
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO))),' ') , "+
						" A.BAL_TO_BE_PAID, "+
						" A.REF_NO, "+
						" A.RECEIVER,   "+
						" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(A.REF_NO,A.RECEIVER),' ') PAYEE_NAME,  "+
						" NVL(decode(A.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE ,   "+
						" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))))),' ') "+ //Added by Minal on 14-08-2014 for #13677
						" ,ROUND(SYSDATE - B.START_DATE) NO_OF_DAYS "+ // udara 20-08-2014
						" ,NVL("+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS(B.FINANCE_NO),'-') "+ // 12 added by udara 08-05-2015
						" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A, "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA B, "+m_schema_name+".AF_CO_PRO_INVOICE C "+
						" WHERE  A.REF_NO = B.REF_DEBIT_NOTE_NO "+ // added by udara 30-11-2016
						" AND B.REF_DEBIT_NOTE_NO = C.INVOICE_NO "+
						" AND UPPER(A.RECEIVER) LIKE UPPER('"+m_receiver+"%')   "+ // added by udara 25-07-2014
						" AND B.START_DATE + "+m_no_of_days+" >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
						" AND B.START_DATE + "+m_no_of_days+" <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND "+m_schema_name+".AF_GET_DIRECT_CONTRACT_STATUS(B.FINANCE_NO) IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS')     "+ // added by udara 19-12-2014
						" AND A.BAL_TO_BE_PAID > 0 "+
						" AND B.REF_DEBIT_NOTE_NO IS NOT NULL "+ // added by udara 18-02-2015 
						" AND (C.DUE_DATE <= TRUNC(SYSDATE) OR C.ADJUSTED_AMOUNT > 0 OR C.SETTELE_AMOUNT > 0) "+ // added by udara 09-06-2015
						" AND UPPER(SUSPENSE_ENTRY_TYPE) LIKE UPPER('%"+m_suspense_entry_type+"%')  "+ // released by udara 17-07-2015
						" ORDER BY NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)),' ')  ");
						// end by udara 03-06-2015
						*/
					
					rs = stmt.executeQuery (" "+
							" SELECT "+
								" C.SUS_REF_NO, "+
								" A.FINANCE_NO, "+
								" A.CLIENT_CODE, "+
								" B.FULL_NAME, "+
								" C.BAL_TO_BE_PAID, "+
								" C.REF_NO, "+
								" C.RECEIVER, "+
								" C.PAYEE_NAME, "+
								" C.SUSPENSE_ENTRY_TYPE, "+
								" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),' '), "+
								" C.NO_OF_DAYS, "+
								" A.APPLICATION_STATUS "+
								" FROM  "+
								" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
								" "+m_schema_name+".AF_CO_MAS_CLIENT B, "+
								" ( "+
								
									 " SELECT "+ 
										 " DISTINCT X.SUS_REF_NO, "+
										 " "+m_schema_name+".af_co_get_fin_no_n(X.REF_NO) APP_NO, "+
										 " X.BAL_TO_BE_PAID, "+
										 " X.REF_NO, "+ 
										 " X.RECEIVER, "+  
										 " NVL("+m_schema_name+".AF_CO_GET_REF_NAME(X.REF_NO,X.RECEIVER),' ') PAYEE_NAME, "+ 
										 " NVL(decode(X.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE , "+  
										 " ROUND(SYSDATE - Y.START_DATE) NO_OF_DAYS "+
											 " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA Y, "+m_schema_name+".AF_CO_PRO_INVOICE Z "+
											 " WHERE  X.REF_NO = Y.REF_DEBIT_NOTE_NO  "+
											 " AND Y.REF_DEBIT_NOTE_NO = Z.INVOICE_NO "+
											 " AND X.RECEIVER LIKE UPPER('"+m_receiver+"%')   "+ 
											 " AND Y.START_DATE + "+m_no_of_days+" >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  
											 " AND Y.START_DATE + "+m_no_of_days+" <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
											 " AND X.BAL_TO_BE_PAID > 0 "+
											 " AND Y.REF_DEBIT_NOTE_NO IS NOT NULL  "+
											 " AND (Z.DUE_DATE <= TRUNC(SYSDATE) OR Z.ADJUSTED_AMOUNT > 0 OR Z.SETTELE_AMOUNT > 0) "+
											 //" AND UPPER(X.SUSPENSE_ENTRY_TYPE) LIKE UPPER('%"+m_suspense_entry_type+"%') "+ // commented by udara 02-08-2018
												" AND X.SUSPENSE_ENTRY_TYPE = '"+m_suspense_entry_type+"' "+
															
								             " ) C "+
								" WHERE  A.APPLICATION_NO =  C.APP_NO  "+
								" AND A.CLIENT_CODE = B.CLIENT_CODE "+
								" AND A.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','REPOSSESS','CANCEL_PO') "+
								//" ORDER BY A.FINANCE_NO "+ // commented by udara 13-10-2021
								" ORDER BY C.BAL_TO_BE_PAID  "+ // added by udara 13-10-2021
             			" ");

					
					
				}
				
				// end by udara 23-05-2014
				
				
				
				
				boolean more = rs.next();
				
				out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
				
				if(more){
					out.println("<tr class=pdn_txtpos2 > "); 
					out.println("<td width='11%' >Finance No</td>"); 
					out.println("<td width='11%' >Registration No.</td>");
					//out.println("<td width='11%' >Sus Ref No</td>"); 
					out.println("<td width='26%' >Client Name</td>"); 
					out.println("<td width='13%' align='right' >No. of days</td>");  // udara 20-08-2014
					out.println("<td width='1%'  > &nbsp; </td>");  // udara 20-08-2014
					out.println("<td width='13%' align='left' >Entry Type</td>"); 		//---added by prabash on 04-07-2011-----
					//out.println("<td width='5%' >Branch</td>"); 
					//	out.println("<td width='12%' align='right'>broker</td>");
					out.println("<td width='12%' align='right'>Balance Amount</td>"); 
					out.println("<td width='15%' align='right'>Adjustment Amount&nbsp;&nbsp;</td>"); 
					out.println("<td width='10%' >Adjust Type</td>"); 
					out.println("<td width='12%' align='right'>Amount</td>"); 
					out.println("<td width='12%' align='right'>WHT</td>"); 
					
					out.println("<td width='2%' >&nbsp;</td>"); 
					out.println("</tr>"); 
				}
				int j = 0;    
				
				// added by udara 17-02-2015
				double bal_amnt = 0;
				double adj_amnt = 0;
				double m_amnt = 0;
				double wht_amnt = 0;
				// end by udara 17-02-2015
				
				while(more){
					
					/*
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					*/
					
					// added by udara 08-05-2015
					if(j>0 && j%2==1){						
						if(rs.getString(12).equals("TERMI"))
							out.println("<tr class=tr_input1 style='{background-color: red;}' >"); 
						else if(rs.getString(12).equals("TERMINATED"))
							out.println("<tr class=tr_input1 style='{background-color: red;}' >"); 
						else
							out.println("<tr class=tr_input1  >"); 
					}
					else{
						if(rs.getString(12).equals("TERMI"))
							out.println("<tr class=tr_input style='{background-color: red;}' >"); 
						else if(rs.getString(12).equals("TERMINATED"))
							out.println("<tr class=tr_input style='{background-color: red;}' >"); 
						else
							out.println("<tr class=tr_input  >"); 
					}
					// end by udara 08-05-2015
					
					
					// added by udara 17-02-2015
					bal_amnt = bal_amnt + rs.getDouble(5);
				    adj_amnt = adj_amnt + 0;
				    m_amnt = m_amnt + 0;
				    wht_amnt = wht_amnt + 0;
					// end by udara 17-02-2015
					
					out.println("<td width='11%' style='{cursor:hand;}' onClick=\"show_transaction_info('"+rs.getString(3)+"','"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>"); 
					//out.println("<td width='11%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='11%' >"+rs.getString(10)+"</td>"); //added by Minal on 14-08-2014 for #13677
					out.println("<td width='26%' >"+rs.getString(4)+"</td>"); 
					out.println("<td width='13%' align='right' >"+rs.getString(11)+"</td>"); // udara 20-08-2014
					out.println("<td width='1%'  > &nbsp; </td>");  // udara 20-08-2014
					out.println("<td width='13%' >"+rs.getString(7)+"</td>"); //---added by prabash on 04-07-2011-----
					//out.println("<td width='5%' >"+rs.getString(4)+"</td>"); 
					//out.println("<td width='15%' >"+rs.getString(5)+"</td>"); 
					out.println("<td width='12%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+j+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"set_total_last();\"  >"); 
					//out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"enable_adjusted_type(this,"+j+");set_total_last();\" style=\"{text-align:right;}\" >"); // commented by udara 10-03-2015 // added set_total_last(); by udara 17-02-2015
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"set_total_last();\" style=\"{text-align:right;}\" >"); // added by udara 10-03-2015
					out.println("<td width='10%' ><select class=txt_input type=text name=TXT_ADJUST_MODE"+j+" maxlength=1 size=1 disabled onChange=\"\"  >");  //calculate_amount()
					out.println("<option value=\"ADD\" >Addition</option>");
					out.println("<option value=\"MIN\"  selected>Deduction</option>");
					out.println("</select></td>");
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+j+"); set_total_last();\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); // added set_total_last(); by udara 17-02-2015
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_WHT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+j+");check_wht_amount(this,22,"+j+");set_total_last();\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); // ADDED BY ASHINI // added set_total_last(); by udara 17-02-2015
					out.println("<TD WIDTH=\"2%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+");set_total_last();\"  ></td>"); // added set_total_last(); by udara 17-02-2015			
					out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+j+" value="+rs.getString(1)+" >");
					out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+j+" value="+rs.getString(2)+" >");
					out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+j+" value="+rs.getString(3)+" >");
					
					out.println("</font>"); // added by udara 08-05-2015 	
					
					out.println("</tr>"); 		
					more = rs.next();
					j=j+1;
				}
				
				// added by udara 17-02-2015
					out.println("<td width='11%' ><b> Total </b></td>"); 
					out.println("<td width='11%' > &nbsp; </td>"); 
					out.println("<td width='26%' > &nbsp; </td>"); 
					out.println("<td width='13%' > &nbsp; </td>"); 
					out.println("<td width='1%' > &nbsp; </td>"); 
					out.println("<td width='26%' > &nbsp; </td>"); 
					out.println("<td width='12%' align='right'><b> <div id='bal_amnt' > "+nf.format(bal_amnt)+" </div> </b></td>");  //nf.format(0) 
					out.println("<td width='15%' align='right'><b> <div id='adj_amnt' > "+nf.format(adj_amnt)+"</div> </b></td>"); 
					out.println("<td width='10%' > &nbsp; </td>"); 
					out.println("<td width='12%' align='right'><b> <div id='m_amnt'   > "+nf.format(m_amnt)+" </div> </b></td>"); 
					out.println("<td width='12%' align='right'><b> <div id='wht_amnt' > "+nf.format(wht_amnt)+" </div> </b></td>"); 
					out.println("<td width='2%' > &nbsp; </td>"); 	
					out.println("</tr>");
				// end by udara 17-02-2015
				
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				out.println("<input type=hidden name=hid_count value="+j+"></table>");
				
				
				
			}
			
			/*else if(m_chksql.equals("calculate_amount_new")){
									
			String m_adjust_amount		    = req.getParameter("adjust_amount");
			String m_adjust_type            = req.getParameter("adjust_type");
			String m_amount	              = req.getParameter("amount");
			String m_wht_amount		        = req.getParameter("wht_amount");
			String m_chk_deposit_tmp		  = req.getParameter("chk_deposit_tmp");
						
								
			rs = stmt.executeQuery ("SELECT "+m_adjust_amount+",'"+m_adjust_type+"',"+m_amount+","+m_wht_amount+","+
			"       '"+m_chk_deposit_tmp+"' "+
			" FROM DUAL");
			
			if(rs.next()){
			m_adjust_amount=rs.getDouble(1);
			m_amount=rs.getDouble(3);
			m_wht_amount=rs.getDouble(4);
			}
			
			if(m_chk_deposit_tmp.equals("on")){
			sum=sum+m_amount;
			wht_sum=wht_sum+m_wht_amount;
			
			if(m_adjust_amount>0){
			if(m_adjust_type.equals("ADD")){
			sum=sum+m_adjust_amount;
			}
			else if(m_adjust_type.equals("MIN")){
			sum=sum-m_adjust_amount;
			}
			}
			
			}else if(m_chk_deposit_tmp.equals("off")){
			sum=sum-m_amount;
			wht_sum=wht_sum-m_wht_amount;
			}
			}
			
			
			*/
			
			else if(m_chksql.equals("delete_allocation_details")){
				
				String m_receiver = req.getParameter("receiver").trim();																		
				String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
				String m_value_date = req.getParameter("value_date").trim();																		
				
				
				rs = stmt.executeQuery (" SELECT  "+
					" DISTINCT SUS_REF_NO, "+
					" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ') , "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+
					" BAL_TO_BE_PAID, "+
					" REF_NO, "+
					" RECEIVER,   "+
					" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
					" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE    "+
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
					//" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+	--- COMMENT BY PRABASH ON 20-07-2011------
					//" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE)LIKE UPPER('"+m_suspense_entry_type+"')  "+ //--- ADDED BY PRABASH ON 20-07-2011------
					" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND SUSPENSE_ENTRY_TYPE = '"+m_suspense_entry_type+"'  "+ // added by udara 02-08-2018
					//" AND VALUE_DATE <= TO_DATE('"+m_value_date+"','DD-MM-YYYY')"+
					//" AND BAL_TO_BE_PAID > 0 "+
					" ORDER BY REF_NO ");
				
				/*		rs = stmt.executeQuery (" SELECT  "+
					//" PAYMENT_NO,
				" SUS_REF_NO,
					" '' ,"+
					" CLIENT_CODE "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) "+
				" SETTELED_AMOUNT,
					//" EFF_VAL_DATE,
				///" CLIENT_CODE
				" FROM LAKDL.AF_CR_PRO_SET_PAY_BREAKDOWN A  "+
					
					*/		
				boolean more = rs.next();
				
				out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
				
				if(more){
					out.println("<tr class=pdn_txtpos2 > "); 
					out.println("<td width='12%' >Finance No</td>"); 
					//out.println("<td width='12%' >Sus Ref No</td>"); 
					out.println("<td width='13%' >Client Name</td>"); 
					out.println("<td width='13%' align='left' >Entry Type </td>"); 		//---added by prabash on 20-07-2011-----
					out.println("<td width='15%' align='right'>Balance Amount</td>"); 
					out.println("<td width='15%' align='right'>Adjustment Amount&nbsp;&nbsp;</td>"); 
					out.println("<td width='10%' >Adjust Type</td>"); 
					out.println("<td width='12%' align='right'>Amount</td>"); 
					out.println("<td width='3%' >&nbsp;</td>"); 
					out.println("</tr>"); 
				}
				int j = 0;      					
				while(more){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='12%' style='{cursor:hand;}' onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>"); 
					//out.println("<td width='12%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='21%' >"+rs.getString(4)+"</td>"); 
					out.println("<td width='21%' >"+rs.getString(7)+"</td>"); //---added by prabash on 20-07-2011-----
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+j+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"\"  >"); 
					//out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"enable_adjusted_type(this,"+j+")\" style=\"{text-align:right;}\" >"); // commented by udara 10-03-2015
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"\" style=\"{text-align:right;}\" >"); // added by udara 10-03-2015
					out.println("<td width='10%' ><select class=txt_input type=text name=TXT_ADJUST_MODE"+j+" maxlength=1 size=1 disabled onChange=\"\"  >");  //calculate_amount()
					out.println("<option value=\"ADD\" >Addition</option>");
					out.println("<option value=\"MIN\"  selected>Deduction</option>");
					out.println("</select></td>");
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22)\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); 
					out.println("<TD WIDTH=\"3%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"on\" onclick=\"change_val_req("+j+")\"  ></td>");			
					out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+j+" value="+rs.getString(1)+" >");
					out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+j+" value="+rs.getString(2)+" >");
					out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+j+" value="+rs.getString(3)+" >");
					out.println("</tr>"); 		
					more = rs.next();
					j=j+1;
				}
				
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				out.println("<input type=hidden name=hid_count value="+j+"></table>");
				
				
			}
			
			
			//=================================================================================================================================	
			
		}catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			//if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			//if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
			
			// added by udara 13-05-2019
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(rs1   !=null){try{rs1.close();  }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1 !=null){try{stmt1.close();}catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			// end by udara 13-05-2019
			
			
		}
	}//service method
}


