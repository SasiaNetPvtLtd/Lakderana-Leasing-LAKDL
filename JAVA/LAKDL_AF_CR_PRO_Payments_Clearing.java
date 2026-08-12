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


public class LAKDL_AF_CR_PRO_Payments_Clearing extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out =  null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		
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
				
				
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=main_page';"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Finance - Payments Clearing - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Finance - Payments Clearing - \"+document.Form1.hid_status.value;"); 
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
				//out.println("document.Form1.TXT_PAYMENT_NO.disabled=false;"); 
				//out.println("document.Form1.BUT_TXT_PAYMENT_NO.disabled=false;"); 
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
				out.println("document.Form1.BUT_TXT_LIC_ACC_NO.disabled=true;"); 
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
				
				
				
				out.println("function assign_DateValues(dval){");
				out.println("document.Form1.TXT_EFF_VALDATE_DD.value=dval.substring(0,2)");
				out.println("document.Form1.TXT_EFF_VALDATE_MM.value=dval.substring(3,5)");
				out.println("document.Form1.TXT_EFF_VALDATE_YY.value=dval.substring(6,10)");
				out.println("}");
				
				out.println("function help_payment_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    Sql = \"m_help_payment_no_other_payment\";"); 
				out.println("    Crit = document.Form1.TXT_PAYMENT_NO.value+\"@\"+\"Y@\";"); 
				out.println("    m_sql = Sql;"); 
				out.println("    m_criteria = Crit"); 
				out.println("    HelpBox('1','10','0');"); 
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
				out.println("}"); 	
				
				out.println("function help_suspense_referense() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("    Sql = \"m_help_suspense_reference\";"); 
				out.println("     Crit = document.Form1.TXT_SUSPENSE_REFERENSE.value+\"@\"+document.Form1.TXT_ENTRY_TYPE.value+\"@\"+\"Y@\";"); 
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
				out.println("if (oBj.valout[6]=='-' ||  oBj.valout[6]=='null' ) {");
				out.println("   document.Form1.TXT_VOUCHER_ADDRESS2.value='';"); 
				out.println("}"); 	
				out.println("else {"); 	
				out.println("   document.Form1.TXT_VOUCHER_ADDRESS2.value=oBj.valout[6];"); 
				out.println("}"); 	
				out.println(" 	makeRequest(); ");
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
				/*out.println("else if(document.Form1.TXT_SUSPENSE_REFERENSE.value==\"\"){  "); 
				out.println("	DIV_TXT_SUSPENSE_REFERENSE.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_PAYEE_NAME.value==\"\"){  "); 
				out.println("	DIV_TXT_PAYEE_NAME.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				
				out.println("else if(document.Form1.TXT_LIC_ACC_NO.value==\"\"){  "); 
				out.println("	DIV_TXT_LIC_ACC_NO.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_PAYMENT_AMOUNT.value==\"\"){  "); 
				out.println("	DIV_TXT_PAYMENT_AMOUNT.style.color='red';");
				out.println("	return false;"); 
				out.println("}"); 
				*/
				
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
				
				
				out.println("function before_submit(){ "); 
				
				out.println("   m_status = document.Form1.hid_status.value ");
				out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
				out.println("		if(validate_data()){"); 
				out.println("ckeck_data();");
				out.println("if(b_flag==0)");
				out.println("		if(confirm(m_save_msg)){ ");
				out.println("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("   document.Form1.elements[i].disabled=false;");
				out.println("   }");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Payments_Clearing';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}");			
				
				out.println("} "); 
				
				
				out.println("function makeRequest() {");					
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=payment_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&value_date=\"+document.Form1.hid_date.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";");
				out.println("value_date_to=document.Form1.TXT_EFF_VALDATE_DD_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_MM_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_YY_TO.value;");	
				out.println("payee = document.Form1.CLIENT_CODE.value;");//Added by prabash on17-02-2012
				out.println("pamt_no = Form1.TXT_PAYMENT_NO.value;");//Added by prabash on17-02-2012
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=payment_allocation_details&payee=\"+payee+\"&pamt_no=\"+pamt_no+\"&value_date=\"+document.Form1.hid_date.value+\"&value_date_to=\"+value_date_to+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";");
				//out.println("window.open(m_url);");
				//out.println("alert(m_url);");
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("function makeRequest_view() {");
				out.println("value_date_to=document.Form1.TXT_EFF_VALDATE_DD_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_MM_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_YY_TO.value;");			
				out.println("payee = document.Form1.CLIENT_CODE.value;");//Added by prabash on17-02-2012
				out.println("pamt_no = Form1.TXT_PAYMENT_NO.value;");//Added by prabash on17-02-2012
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=payment_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&value_date=\"+document.Form1.hid_date.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=payment_allocation_details_view&payee=\"+payee+\"&pamt_no=\"+pamt_no+\"&value_date=\"+document.Form1.hid_date.value+\"&value_date_to=\"+value_date_to+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";");
				//out.println("window.open(m_url);");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=20,top=50,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");//added By Sandun on 23-12-2008
				//out.println("load_interface(m_url,'NORM');");
				out.println("}");
				
				
				
				out.println("function makeRequest_delete() {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=delete_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&value_date=\"+document.Form1.hid_date.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";");
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				
				
				out.println("function get_vector_normal(http_response) {");
				out.println(" payment_allocation.innerHTML = ''; ");
				out.println(" payment_allocation.innerHTML = http_response; ");
				out.println("document.Form1.hid_no_of_rec.value=document.Form1.hid_count.value ");
				out.println(" ");
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
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.TXT_EFF_VALDATE_DD_TO.value=v_dd;");
				out.println("     document.Form1.TXT_EFF_VALDATE_MM_TO.value=v_mm;");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY_TO.value=v_yy;");
				out.println("     document.Form1.hid_date_to.value=document.Form1.TXT_EFF_VALDATE_DD_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_MM_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_YY_TO.value;");			
				out.println("  }");		
				out.println("}");		
				
				
				out.println("function check_date(obdd,obmm,obyy){ ");
				out.println(" if((obdd.value !=\"\") && (obmm.value !=\"\") && (obyy.value !=\"\")){");
				out.println(" checkMonthLength(obdd,obmm,obyy);");
				out.println(" document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD.value+'-'+document.Form1.TXT_EFF_VALDATE_MM.value+'-'+document.Form1.TXT_EFF_VALDATE_YY.value;");			
				out.println(" }");
				out.println("}");
				
				out.println("function load_lock(){	"); 
				//out.println("     document.Form1.TXT_BRANCH.value='"+_m_location_desc+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_DD.value='"+_m_sys_date_dd+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_MM.value='"+_m_sys_date_mm+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY.value='"+_m_sys_date_yy+"';");
				
				out.println("     document.Form1.TXT_EFF_VALDATE_DD_TO.value='"+_m_sys_date_dd+"';");//Added By Sandun 23-12-2008
				out.println("     document.Form1.TXT_EFF_VALDATE_MM_TO.value='"+_m_sys_date_mm+"';");
				out.println("     document.Form1.TXT_EFF_VALDATE_YY_TO.value='"+_m_sys_date_yy+"';");
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("     document.Form1.hid_date.value=document.Form1.TXT_EFF_VALDATE_DD.value+'-'+document.Form1.TXT_EFF_VALDATE_MM.value+'-'+document.Form1.TXT_EFF_VALDATE_YY.value;");	
				out.println("     document.Form1.hid_date_to.value=document.Form1.TXT_EFF_VALDATE_DD_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_MM_TO.value+'-'+document.Form1.TXT_EFF_VALDATE_YY_TO.value;");			
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
				
				out.println("calculate_amount();");
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value='';"); 
				out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}	"); 
				
				
				out.println("function calculate_amount_clear(row){")	;
				out.println("m_adjust_amount=\"TXT_ADJUST_AMOUNT\"+row;");
				out.println("m_balance_amount=\"TXT_BALANCE_AMOUNT\"+row;");
				out.println("m_balance=parseFloat(unformat_noobject(document.Form1.elements[m_balance_amount].value));");
				out.println("m_adjust=parseFloat(unformat_noobject(document.Form1.elements[m_adjust_amount].value));");
				
				out.println("if(m_adjust > m_balance){");
				out.println("alert('Clear amount cannot exceed balance amount');");
				out.println("document.Form1.elements[m_adjust_amount].value=0;");
				out.println("}	"); 
				
				out.println("}	"); 
				
				out.println("function calculate_amount(){")	;
				out.println(" var sum=0; ");
				out.println("for(i=0;i<parseInt(document.Form1.hid_count.value);i++){");
				out.println("m_adjust_amount=\"TXT_ADJUST_AMOUNT\"+i;");
				out.println("adjust_type=\"TXT_ADJUST_MODE\"+i;");
				out.println("m_amount=\"TXT_AMOUNT\"+i;");
				out.println("m_chk_deposit_tmp=\"CHK_REQUIRED\"+i;");
				
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
				out.println("m_adjust_amount=parseFloat(unformat_noobject(document.Form1.elements[m_adjust_amount].value));");
				out.println("m_adjust_type=document.Form1.elements[adjust_type].value;");
				
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
				out.println("assign_netamt();");	// added by ashini 		
				out.println("}");			
				out.println("}");			
				
				
				out.println("function change_val_req(row_no){")	;
				out.println("var m_amount=0;");
				out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
				out.println("m_txt_amount=\"TXT_ADJUST_AMOUNT\"+row_no;");
				//out.println("m_txt_balance_amount=\"TXT_BALANCE_AMOUNT\"+row_no;");
				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				//out.println("m_amount=parseFloat(unformat_noobject(document.Form1.elements[m_txt_amount].value));");
				//ou/t.println("if(m_amount==0) {");
				//out.println("document.Form1.elements[m_txt_amount].value=document.Form1.elements[m_txt_balance_amount].value;");
				//out.println("}");	
				
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("document.Form1.elements[m_txt_amount].value=0;");
				out.println("}");	
				
				//out.println("calculate_amount();");
				
				out.println("}");	
				
				out.println("function help_licence_account_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				out.println("    Sql = \"m_help_licence_account_no\";");
				out.println("    Crit = document.Form1.TXT_LIC_ACC_NO.value+\"@Y@\";"); 
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
				
				out.println("function assign_netamt(){	"); 
				out.println("document.Form1.TXT_NET_AMT.value=document.Form1.TXT_PAYMENT_AMOUNT.value;");
				out.println("document.Form1.TXT_WHT_AMT.value=\"0.00\" ; ");
				out.println("}	"); 
				
				out.println("function check_value(){	"); 
				out.println("if(parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value))<parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value))){");
				out.println("alert('Withholding Tax amount cannot be greater than Gross amount');");
				out.println("document.Form1.TXT_WHT_AMT.value=\"0.00\" ; ");
				out.println("}");
				out.println("else{");
				out.println("sum=parseFloat(unformat_noobject(document.Form1.TXT_PAYMENT_AMOUNT.value)) -parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value)) ;");
				out.println("document.Form1.TXT_NET_AMT.value=format_noobject(sum)");
				out.println("wht=parseFloat(unformat_noobject(document.Form1.TXT_WHT_AMT.value));");
				out.println("document.Form1.TXT_WHT_AMT.value=format_noobject(wht)");
				out.println("}");
				out.println("}"); 
				
				//=====Added by Prabash on 17-02-2012 ==========================================
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'cre_payee3','1');");
				out.println("}");
				
				out.println("function payment_no_help(){");
				out.println("Crit=document.Form1.TXT_PAYMENT_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'cre_payee3','2');");
				out.println("}");
				
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");  
				out.println("}");
				out.println("function payment_no_assign(oBj){");
				out.println(" document.Form1.TXT_PAYMENT_NO.value =oBj.valout[4]");  
				out.println("}");
				
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
				
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		payment_no_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
				out.println("		}");
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); 
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
				
				out.println("function check_client(val) {");
				out.println(" document.Form1.TXT_PAYMENT_NO.value = ''  ");
				out.println("}");
				
				
				
				out.println("function check_all_chkbox()");
				out.println("{ ");
				out.println("   check_true=false;    ");
				out.println("       if(document.Form1.CHECK_ALL.checked==true)");
				out.println("       { ");
				out.println("          check_true=true;");
				out.println("       }else  ");
				out.println("       { ");
				out.println("    	    check_true=false;");
				out.println("       } ");
				out.println("   for(m=0;m<parseInt(document.Form1.hid_count.value);m++) ");
				out.println("   { ");
				out.println("        document.Form1.elements[\"CHK_REQUIRED\"+m].checked=check_true; ");
				out.println("        change_val_req(m); ");	
				//out.println("        alert(document.Form1.elements[\"CHK_REQUIRED\"+m].value); ");
				out.println("   } ");	
				out.println("} ");			
				
				//=======================================================================		
				
				
				//--------end modifications done by ashini-----------------------------------------
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); //load_lock()
				out.println("<FORM NAME='Form1' method='post'>"); 
				//out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_Client_Code' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_Guarantor_Code' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
				out.println("<input type=hidden name='hid_date' value=\"\">");
				out.println("<input type=hidden name='hid_date_to' value=\"\">");
				out.println("<input type=hidden name='hid_no_of_rec' value=\"0\">");
				
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Payments Clearing </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");'  onclick='load_screen_status(\"EDIT\")' value=\"Edit\" ></td>");  
				out.println("<td width='6%'>&nbsp;</td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");'  onclick='load_screen_status(\"DELETE\")' value=\"Delete\" ></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\" ></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
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
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYMENT_NO'  class=div_input>Request No*</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYMENT_NO' maxlength='19' size='19' onblur=\"help_payment_no()\" disabled>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_PAYMENT_NO' value=\"Help\" onClick=\"help_payment_no()\" disabled ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_EFF_VALDATE'  class=div_input>Request Date </DIV></td>"); 
				out.println("<td width='5%'>From</td><td width='20%' ><input class='txt_input5' type='text' name='TXT_EFF_VALDATE_DD' maxlength='2' size='2'  onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_MM' maxlength='2' size='2' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>Calendar</a></td>"); 
				
				out.println("<td width='5%' ><DIV id='DIV_TXT_EFF_VALDATE_TO'  class=div_input>To</DIV></td>"); //Added By Sandun on 23-12-2008
				out.println("<td width='40%' ><input class='txt_input5' type='text' name='TXT_EFF_VALDATE_DD_TO' maxlength='2' size='2'  onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD_TO,document.Form1.TXT_EFF_VALDATE_MM_TO,document.Form1.TXT_EFF_VALDATE_YY_TO)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_MM_TO' maxlength='2' size='2' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD_TO,document.Form1.TXT_EFF_VALDATE_MM_TO,document.Form1.TXT_EFF_VALDATE_YY_TO)\" >"); 
				out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY_TO' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD_TO,document.Form1.TXT_EFF_VALDATE_MM_TO,document.Form1.TXT_EFF_VALDATE_YY_TO)\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>Calendar</a></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("</table>");
				
				//== Added by prabash on 15-02-2012====
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 			
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'&gt;</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Payee Name</td>");
				out.println("<td width='30%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='15' style=\"{width:250px;}\" size='15'onBlur='client_help()' >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Debit Note No</td>");
				out.println("<td><input type=text name='TXT_PAYMENT_NO' class='txt_input'  style=width:150px onBlur='payment_no_help()'>");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"payment_no_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");
				
				//=====================================		
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_BRANCH'  class=div_input>Branch</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH' maxlength='19' size='19' onblur=\"\" style={width:200px;} disabled >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
		*/
				rs = stmt.executeQuery ("SELECT SUB_TYPE_CODE, DESCRIPTION "+
					" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
					" WHERE ACCOUNT_TYPE='L' "+
					" AND ACTIVE_STATUS='Y' ");
				
				more = rs.next();
				
				
				out.println("<tr>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_ENTRY_TYPE'  class=div_input>Entry Type *</DIV></td>"); 
				//	out.println("<td width='5%' >&nbsp;</td>"); //comment by Prabash on 17-02-2012
				out.println("<td width='10%' ><select class=txt_input type=text name=TXT_ENTRY_TYPE maxlength=1 size=1 style=\"{width:150px;}\" >");  
				//out.println("<option value=\"E\">Seizer</option>");
				//out.println("<option value=\"V\">Vendor</option>");
				//out.println("<option value=\"L\">Lawyer</option>");
				//out.println("<option value=\"A\">Advertistment</option>");
				while(more){
					out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					more = rs.next();
				}
				
				
				out.println("</select>");
				//out.println("<td width='*%'></td>"); 
				//		out.println("<td width='5%' >&nbsp;</td>"); //comment by Prabash on 17-02-2012
				out.println("<td width='40%' ><input class='but_input' type='button' name='BUT_VIEW' value=\"View Payments\" STYLE=\"{width:110px;}\" onClick=\"makeRequest()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View Payments Report\" STYLE=\"{width:110px;}\" onClick=\"makeRequest_view()\"></td>"); 
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_SUSPENSE_REFERENSE'  class=div_input>Suspense Reference </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUSPENSE_REFERENSE' maxlength='19' size='19' onblur=\"help_suspense_referense()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_SUSPENSE_REFERENSE' value=\"Help\" onClick=\"help_suspense_referense()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYEE_NAME'  class=div_input>Payee Name *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYEE_NAME' maxlength='19' size='19' onblur=\"\" style={width:350px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_VOUCHER'  class=div_input>Voucher Address</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VOUCHER_ADDRESS1' maxlength='19' size='19' onblur=\"\" style={width:250px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' >&nbsp;</td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VOUCHER_ADDRESS2' maxlength='19' size='19' onblur=\"\" style={width:250px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' >&nbsp;</td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VOUCHER_ADDRESS3' maxlength='19' size='19' onblur=\"\" style={width:250px;} >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				
				
				
				/*out.println("<tr>");
				out.println("<td width='20%' ><DIV id='DIV_TXT_SETTLE_MODE'  class=div_input>Payment Mode </DIV></td>"); 
				out.println("<td width='40%' ><select class=txt_input type=text name=TXT_SETTLE_MODE maxlength=1 size=1>");  
				out.println("<option value=\"CASH\" >Cash</option>");
				out.println("<option value=\"CHQ\"  selected>Cheque</option>");
				out.println("</select></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				*/
				
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
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Gross Payment *</DIV></td>"); // Payment Amount changed to Gross payment by ashini on 06.03.208
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYMENT_AMOUNT' maxlength='22' size='22' style=\"{text-align:right;}\"  onblur=\"\" disabled >");  
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				//--------------------------------added by ashini on 07-03-2007-----------------
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>WHT</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_WHT_AMT' maxlength='22' size='22' style=\"{text-align:right;}\" onblur=\"check_value();\"  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Net Payment</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NET_AMT' maxlength='22' size='22' style=\"{text-align:right;}\" disabled  >"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' valign ='top'>Description</td>"); 
				out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_REMARKS' style=\"width:350px; height:50px; \" maxlength='500' size='500'  onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); //onblur=\"count_length(this)\"
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				out.println("</table>"); 
				
				
				out.println("<br>");  
				
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
				out.println("</body>"); 
				out.println("</html>"); 
				
			}		
			
			//=================================================================================================================================				
			
			else if(m_chksql.equals("payment_allocation_details")){
				
				//String m_receiver = req.getParameter("receiver").trim();																		
				String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
				String m_value_date = req.getParameter("value_date").trim();
				String m_value_date_to = req.getParameter("value_date_to").trim(); //Added By Sandun on 23-12-2008
				String m_payee   = req.getParameter("payee");		//added by Prabash on 17-02-2012
				String m_pamt_no   = req.getParameter("pamt_no");	//added by Prabash on 17-02-2012
				
				
				/*	rs = stmt.executeQuery (" SELECT  "+
					" DISTINCT SUS_REF_NO, "+
					" NVL("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO),' ') , "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+
					" BAL_TO_BE_PAID, "+
					" REF_NO, "+
					" RECEIVER,   "+
					" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
					" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE    "+
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
					" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+
					" AND VALUE_DATE <= TO_DATE('"+m_value_date+"','DD-MM-YYYY')"+
					" AND BAL_TO_BE_PAID > 0 "+
					" ORDER BY REF_NO ");
					
				*/	
				
				
				
						//out.println(" SELECT  "+
				/*rs = stmt.executeQuery (" SELECT  "+
					" DISTINCT SUS_REF_NO, "+ //1
					" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)),' ') , "+ //2
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+ //3
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+ //4
					" BAL_TO_BE_PAID, "+  //5
					" REF_NO, "+ //6
					" RECEIVER,   "+ //7
					" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+ //8
					" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE   , "+ //9
					" TOT_SETTLE_AMOUNT, "+  //10
					" INT_BAL_SETTLE_AMOUNT "+  //11
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
					" WHERE UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+
					" AND VALUE_DATE >= TO_DATE('"+m_value_date+"','DD-MM-YYYY') "+
					" AND VALUE_DATE <= TO_DATE('"+m_value_date_to+"','DD-MM-YYYY') "+ //Added By Sandun on 23-12-2008
					" AND   UPPER( REF_NO) LIKE UPPER('%"+m_pamt_no+"%') "+ //added by Prabash on 17-02-2012
					" AND   UPPER( "+m_schema_name+".AF_CO_GET_REF_NAME (REF_NO, RECEIVER)) LIKE UPPER('%"+m_payee+"%') "+ 	//added by Prabash on 17-02-2012
					" AND BAL_TO_BE_PAID > 0 "+
					" ORDER BY REF_NO ");*///COMMENETED BY JITHENDRA 25-05-2018,DEVELOPED FASTER MOR EFFICIENT QUERY
			
				//new section by jithendra 25-05-2018
				String m_pay_no_filter="";
				String m_payee_filter="";
				
				
				if(!m_pamt_no.equals("")){
				m_pay_no_filter=" AND   UPPER( A.REF_NO) LIKE UPPER('%"+m_pamt_no+"%') ";
				}
				
				
				if(!m_payee.equals("")){
				m_payee_filter=" AND   UPPER( "+m_schema_name+".AF_CO_GET_REF_NAME (A.REF_NO, A.RECEIVER)) LIKE UPPER('%"+m_payee+"%') " ;
				}
				
				
				rs = stmt.executeQuery (" SELECT  "+
					" DISTINCT A.SUS_REF_NO, "+ //1
					" NVL(B.FINANCE_NO,' ') , "+ //2
					" B.CLIENT_CODE, "+ //3
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE),' ') , "+ //4
					" A.BAL_TO_BE_PAID, "+  //5
					" A.REF_NO, "+ //6
					" A.RECEIVER,   "+ //7
					" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(A.REF_NO,A.RECEIVER),' ') PAYEE_NAME,  "+ //8
					" NVL(decode(A.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE   , "+ //9
					" A.TOT_SETTLE_AMOUNT, "+  //10
					" A.INT_BAL_SETTLE_AMOUNT "+  //11
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A ,"+
					
					"(SELECT FINANCE_NO,INVOICE_NO,CLIENT_CODE "+
                    " FROM AF_CO_PRO_INVOICE "+
                    " WHERE INVOICE_TYPE='INSURANCE') B "+
					
					" WHERE A.REF_NO=B.INVOICE_NO "+
					" AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+
					" AND A.VALUE_DATE >= TO_DATE('"+m_value_date+"','DD-MM-YYYY') "+
					" AND A.VALUE_DATE <= TO_DATE('"+m_value_date_to+"','DD-MM-YYYY') "+ //Added By Sandun on 23-12-2008
					m_pay_no_filter+
					m_payee_filter+
					" AND A.BAL_TO_BE_PAID > 0 "+
					" ORDER BY A.REF_NO ");
				
				
				
				boolean more = rs.next();
				
				/*	out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
				
						if(more){
						out.println("<tr class=pdn_txtpos2 > "); 
					out.println("<td width='12%' >Ag.No</td>"); 
						out.println("<td width='12%' >Sus Ref No</td>"); 
						out.println("<td width='21%' >Client</td>"); 
						out.println("<td width='15%' align='right'>Balance Amount</td>"); 
						out.println("<td width='15%' align='right'>Clear Amount&nbsp;&nbsp;</td>"); 
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
										
										out.println("<td width='12%' >"+rs.getString(2)+"</td>"); 
										out.println("<td width='12%' >"+rs.getString(1)+"</td>"); 
										out.println("<td width='21%' >"+rs.getString(4)+"</td>"); 
										//out.println("<td width='5%' >"+rs.getString(4)+"</td>"); 
										//out.println("<td width='15%' >"+rs.getString(5)+"</td>"); 
										out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+j+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"\"  >"); 
										out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\"  onChange=\"calculate_amount_clear("+j+")\" style=\"{text-align:right;}\" >"); 
										//out.println("<td width='10%' ><select class=txt_input type=text name=TXT_ADJUST_MODE"+j+" maxlength=1 size=1 disabled onChange=\"calculate_amount()\"  >");  
								//out.println("<option value=\"ADD\" >Addition</option>");
								//out.println("<option value=\"MIN\"  selected>Deduction</option>");
								//out.println("</select></td>");
										//out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22,"+j+")\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); 
										out.println("<TD WIDTH=\"3%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+")\"  ></td>");			
										out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+j+" value="+rs.getString(1)+" >");
										out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+j+" value="+rs.getString(2)+" >");
										out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+j+" value="+rs.getString(3)+" >");
										out.println("</tr>"); 		
										more = rs.next();
										j=j+1;
						}
						
					out.println("</table>");
								
				out.println("<input type=hidden name=hid_count value="+j+"></table>");
						
						*/
				
				if(!more){
					out.println("<br><br><br>");//Added By Sandun on 23-12-2008		
					out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
					out.println("<tr ><td width='*%' align=center><font color=red>No Data Found...!</font></td></tr> ");
					out.println("</table>"); 
				}
				
				
				
				if(more){
					out.println("<hr>"); 		
					out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
					out.println("<br><br>");
					out.println("<tr class=pdn_txtpos2 > "); 
					out.println("<td width='12%' >Payee Name</td>"); 
					out.println("<td width='12%' >Ag.No</td>"); 
					out.println("<td width='12%' >Debit Note No</td>"); 
					out.println("<td width='15%' align='right'>Amount</td>"); 
					out.println("<td width='15%' align='right'>Settled Amount</td>"); 
					out.println("<td width='15%' align='right'>Balance Amount</td>"); 
					out.println("<td width='15%' align='right'>Clear Amount&nbsp;&nbsp;</td>"); 
					out.println("<td width='3%' align='center' > <INPUT TYPE=\"checkbox\" NAME=\"CHECK_ALL\" VALUE=\"on\" onclick=\"check_all_chkbox()\"  ></td>"); 
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
					out.println("<td width='12%' >"+rs.getString(8)+"</td>"); 
					out.println("<td width='12%' >"+rs.getString(2)+"</td>"); 
					out.println("<td width='12%' >"+rs.getString(6)+"</td>"); 
					out.println("<td width='15%' align='right' >"+nf.format(rs.getDouble(10))+"</td>"); 
					out.println("<td width='15%' align='right'  >"+nf.format(rs.getDouble(11))+"</td>"); 
					out.println("<td width='15%'  align='right' >"+nf.format(rs.getDouble(5))+"</td>"); 
					
					//out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+j+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"\"  >"); 
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\"  onChange=\"calculate_amount_clear("+j+")\" style=\"{text-align:right;}\" >"); 
					out.println("<TD WIDTH=\"3%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+")\"  ></td>");			
					out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+j+" value="+rs.getString(1)+" >");
					out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+j+" value="+rs.getString(2)+" >");
					out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+j+" value="+rs.getString(3)+" >");
					out.println("<input type=hidden name=TXT_BALANCE_AMOUNT"+j+" value="+rs.getDouble(5)+" >");
					out.println("</tr>"); 		
					more = rs.next();
					j=j+1;
				}
				
				out.println("</table>");
				
				out.println("<input type=hidden name=hid_count value="+j+"></table>");
				
				
				
				
			}
			
			else if(m_chksql.equals("payment_allocation_details_view")){
				
				
				String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
				String m_value_date = req.getParameter("value_date").trim();																		
				String m_value_date_to = req.getParameter("value_date_to").trim();//Added By Sandun on 23-12-2008	
				String m_payee   = req.getParameter("payee");		//added by Prabash on 17-02-2012
				String m_pamt_no   = req.getParameter("pamt_no");	//added by Prabash on 17-02-2012
				
				
				rs = stmt.executeQuery (" SELECT  "+
					" DISTINCT A.SUS_REF_NO, "+ //1
					" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)),' ') , "+ //2
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)), "+ //3
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO))),' ') , "+ //4
					" A.BAL_TO_BE_PAID, "+  //5
					" A.REF_NO, "+ //6
					" A.RECEIVER,   "+ //7
					" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(A.REF_NO,A.RECEIVER),' ') PAYEE_NAME,  "+ //8
					" NVL(decode(A.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE   , "+ //9
					" A.TOT_SETTLE_AMOUNT, "+  //10
					" A.INT_BAL_SETTLE_AMOUNT, "+  //11
					" TO_CHAR(B.ENT_DATE,'DD-MM-YYYY')"+//12 //Added by Jithendra 04.05.2016
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A , "+m_schema_name+".AF_CR_PRO_CLEAR_PAYMENT B "+//Added Table B to get only cleared payments(Jithendra 27.04.2016)
					" WHERE UPPER(A.SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+
					"AND A.SUS_REF_NO=B.SUS_PAYMENT_NO"+
				//	" AND VALUE_DATE >= TO_DATE('"+m_value_date+"','DD-MM-YYYY')"+//Commented by Jithendra 27.04.2016
				//	" AND VALUE_DATE <= TO_DATE('"+m_value_date_to+"','DD-MM-YYYY')"+//Added By Sandun on 23-12-2008//Commented by Jithendra 27.04.2016
				    
				   " AND TRUNC(B.ENT_DATE,'DD') >= TO_DATE('"+m_value_date+"','DD-MM-YYYY')"+//Added by Jithendra 27.04.2016
				   " AND TRUNC(B.ENT_DATE,'DD') <= TO_DATE('"+m_value_date_to+"','DD-MM-YYYY')"+//Added by Jithendra 27.04.2016
						
					" AND   UPPER( A.REF_NO) LIKE UPPER('%"+m_pamt_no+"%') "+ //added by Prabash on 17-02-2012
					" AND   UPPER( "+m_schema_name+".AF_CO_GET_REF_NAME (A.REF_NO, A.RECEIVER)) LIKE UPPER('%"+m_payee+"%') "+ 	//added by Prabash on 17-02-2012
					" AND A.BAL_TO_BE_PAID >= 0 "+//Made equal to Zero because cleared payments can have zero bal(Jithendra 27.06.2016)
					" ORDER BY REF_NO ");
				
				
				
				boolean more = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Payment Clearing </TITLE></HEAD>");
				//out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				//out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Payment Clearing </B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<br><br>");
				
				if(!more){
					out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
					out.println("<tr><td width='*%' align='center'><font color=red>No Data...!</font></td></tr>");
					out.println("</table>");
				}
				
				out.println("<table class='table' border='1' width='100%' cellspacing='0' >");
				
				
				if(more){
					out.println("<tr bgcolor=\"#CCCCCC\" > "); 
					out.println("<td width='20%' >Payee Name</td>"); 
					out.println("<td width='12%' >Ag.No</td>");  
					out.println("<td width='12%' >Debit Note No</td>"); 
					out.println("<td width='15%' align='right'>Amount</td>"); 
					out.println("<td width='15%' align='right'>Settled Amount</td>"); 
					out.println("<td width='15%' align='right'>Balance Amount</td>"); 
					out.println("<td width='12%' >Cleared Date</td>");//Added by Jithendra 04.05.2016
					out.println("</tr>"); 
				}
				int j = 0;      					
				while(more){
					
					if(j>0 && j%2==1){
						out.println("<tr bgcolor=\"#FCEBC5\" >");
					}
					else{
						out.println("<tr bgcolor=\"#FCEBC5\" >");
					}
					out.println("<td STYLE='{font:  8pt arial; text-align:left;  }' width='20%' >"+rs.getString(8)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;  }' width='12%' >"+rs.getString(2)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:left;  }' width='12%' >"+rs.getString(6)+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:right;  }' width='15%' align='right' >"+nf.format(rs.getDouble(10))+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:right;  }' width='15%' align='right'  >"+nf.format(rs.getDouble(11))+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:right;  }' width='15%'  align='right' >"+nf.format(rs.getDouble(5))+"</td>"); 
					out.println("<td STYLE='{font:  8pt arial; text-align:center;  }' width='12%' >"+rs.getString(12)+"</td>");//Added by Jithendra 04.05.2016
					out.println("</tr>"); 		
					more = rs.next();
					j=j+1;
				}
				
				out.println("</table>");
				
				out.println("</BODY></HTML>");
				
				
				
				
			}
			
			
			else if(m_chksql.equals("delete_allocation_details")){
				
				String m_receiver = req.getParameter("receiver").trim();																		
				String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
				String m_value_date = req.getParameter("value_date").trim();
				
				
				rs = stmt.executeQuery (" SELECT  "+
					" DISTINCT SUS_REF_NO, "+
					" NVL("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO),' ') , "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') , "+
					" BAL_TO_BE_PAID, "+
					" REF_NO, "+
					" RECEIVER,   "+
					" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
					" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE    "+
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
					" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+
					//" AND VALUE_DATE <= TO_DATE('"+m_value_date+"','DD-MM-YYYY')"+
					//" AND BAL_TO_BE_PAID > 0 "+
					" ORDER BY REF_NO ");
				
				boolean more = rs.next();
				
				out.println("<table class='table' border='0' width='100%' cellspacing='0' >");
				
				if(more){
					out.println("<tr class=pdn_txtpos2 > "); 
					out.println("<td width='12%' >Ag.No</td>"); 
					out.println("<td width='12%' >Sus Ref No</td>"); 
					out.println("<td width='21%' >Client</td>"); 
					out.println("<td width='15%' align='right'>Balance Amount</td>"); 
					out.println("<td width='15%' align='right'>Adjustment Amount&nbsp;&nbsp;</td>"); 
					out.println("<td width='10%' >Adjust Type</td>"); 
					out.println("<td width='12%' align='right'>Amount</td>"); 
					out.println("<td width='3%'  align='center' > <INPUT TYPE=\"checkbox\" NAME=\"CHECK_ALL\" VALUE=\"on\" onclick=\"check_all_chkbox()\"  ></td>"); 
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
					
					out.println("<td width='12%' >"+rs.getString(2)+"</td>"); 
					out.println("<td width='12%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='21%' >"+rs.getString(4)+"</td>"); 
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_BALANCE_AMOUNT"+j+" maxlength='22' size='22'  value="+nf.format(rs.getDouble(5))+"   style=\"{text-align:right;}\" disabled onblur=\"\"  >"); 
					out.println("<td width='15%' align='right'><input class='txt_input' type='text' name=TXT_ADJUST_AMOUNT"+j+" maxlength='22' size='22' value=\"0\" onblur=\"enable_adjusted_type(this,"+j+")\" style=\"{text-align:right;}\" >"); 
					out.println("<td width='10%' ><select class=txt_input type=text name=TXT_ADJUST_MODE"+j+" maxlength=1 size=1 disabled onChange=\"calculate_amount()\"  >");  
					out.println("<option value=\"ADD\" >Addition</option>");
					out.println("<option value=\"MIN\"  selected>Deduction</option>");
					out.println("</select></td>");
					out.println("<td width='12%' align='right' ><input class='txt_input' type='text' name=TXT_AMOUNT"+j+" maxlength='22' size='22' onBlur=\"check_amount(this,22)\"  style=\"{text-align:right;}\" value=\"0\" ></td>"); 
					out.println("<TD WIDTH=\"3%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+")\"  ></td>");			
					out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+j+" value="+rs.getString(1)+" >");
					out.println("<input type=hidden name=HID_TXT_FINANCE_NO"+j+" value="+rs.getString(2)+" >");
					out.println("<input type=hidden name=HID_TXT_CLIENT_CODE"+j+" value="+rs.getString(3)+" >");
					out.println("</tr>"); 		
					more = rs.next();
					j=j+1;
				}
				
				out.println("</table>");
				
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
		}
	}//service method
}


