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
 

public class LAKDL_AF_CR_PRO_Sus_Payment_Enter extends javax.servlet.http.HttpServlet { 
	 
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
					out.println("<TITLE>Finance - Finance Activation</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("var vec_len=0 ;");
 					out.println("var ret_sts=\"\" ;");
					out.println("var b_flag=0;"); //added by nuwan de silva 02-08-07

					

					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Sus_Payment_Enter?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
		
					out.println("function new_window(){	"); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Sus_Payment_Enter?chksql=main_page';"); 
					out.println("}"); 
					
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" Credit Process - SusPayment Enter - \"+m_val;"); 
					out.println("}"); 
		
					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Credit Process - SusPayment Enter - \"+document.Form1.hid_status.value;"); 
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
					out.println("document.Form1.TXT_SUS_PAYMENT_NO.disabled=false;");
					out.println("document.Form1.TXT_EFF_VALDATE_DD.disabled=true;");
					out.println("document.Form1.TXT_EFF_VALDATE_MM.disabled=true;");
					out.println("document.Form1.TXT_EFF_VALDATE_YY.disabled=true;");
					out.println("document.Form1.BUT_TXT_SUS_PAYMENT_NO.disabled=false;");
					out.println("document.Form1.TXT_ENTRY_TYPE.disabled=true;");
					out.println("document.Form1.TXT_SUSPENSE_REFERENSE.disabled=true;");
					out.println("document.Form1.BUT_TXT_SUSPENSE_REFERENSE.disabled=true;");
					out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;");
					out.println("document.Form1.BUT_TXT_CLIENT_CODE.disabled=true;");
					out.println("document.Form1.TXT_REFERENCE_CODE.disabled=true;");
					out.println("document.Form1.BUT_TXT_REFERENCE_CODE.disabled=true;");
					out.println("document.Form1.TXT_AMOUNT.disabled=true;");
					
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");  
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
		
					out.println("function MyDialog(){"); 
					out.println("    this.valout   = new Array(10);"); 
					out.println("}		"); 
					
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
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
			out.println("		assign_sus_payment_no(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		suspense_referense_assign(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		client_assign(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='4'){"); 
			out.println("		reference_assign(oBj);"); 
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
			
				//added by nuwan de silva on 22-08-07------------------------
				out.println("function clear_data() {");
				out.println("}");
				
			
				out.println("function assign_DateValues(dval){");
				out.println("document.Form1.TXT_EFF_VALDATE_DD.value=dval.substring(0,2)");
				out.println("document.Form1.TXT_EFF_VALDATE_MM.value=dval.substring(3,5)");
				out.println("document.Form1.TXT_EFF_VALDATE_YY.value=dval.substring(6,10)");
				out.println("}");
					
				out.println("function help_sus_payment_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				//out.println("     Crit = document.Form1.TXT_SUS_PAYMENT_NO.value+\"@\"+document.Form1.TXT_ENTRY_TYPE.value+\"@\"+\"Y@\";"); 
				out.println("    Crit = document.Form1.TXT_SUS_PAYMENT_NO.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'Sus_Payment_Sql','1');");
				out.println("}"); 

				out.println("function assign_sus_payment_no(oBj) {"); 
				out.println("   document.Form1.TXT_SUS_PAYMENT_NO.value=oBj.valout[2];"); 
				out.println("   assign_DateValues(oBj.valout[3]);");
				out.println("   document.Form1.TXT_ENTRY_TYPE.value=oBj.valout[4];"); 
				out.println("   document.Form1.TXT_SUSPENSE_REFERENSE.value=oBj.valout[5];"); 
				out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[6];"); 
				out.println("   document.Form1.TXT_REFERENCE_CODE.value=oBj.valout[7];"); 
				out.println("   document.Form1.TXT_AMOUNT.value=oBj.valout[8];"); 
				out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[9];"); 
				out.println("format_number(document.Form1.TXT_AMOUNT,22);"); 	
				out.println("}"); 	
					
					
					out.println("function help_suspense_referense() {"); 
					out.println("    document.Form1.hid_help_type.value=\"2\";"); 
					out.println("    m_entry_type=document.Form1.TXT_ENTRY_TYPE.value;");
					
					out.println("if(m_entry_type=='E'){"); //Seizer
					out.println("    Sql = \"m_help_suspense_reference_Seizer\";"); 
					out.println("}");
					out.println("else if(m_entry_type=='L'){"); ///Lawyer
					out.println("    Sql = \"m_help_suspense_reference_Lawyer\";"); 
					out.println("}");
					out.println("else if(m_entry_type=='A'){"); //Advertistment
					out.println("    Sql = \"m_help_suspense_reference_Advertistment\";"); 
					out.println("}");
					out.println("else {");
					out.println("    Sql = \"m_help_suspense_reference\";"); 
					out.println("}");
										
					//out.println("    Sql = \"m_help_suspense_reference\";"); 
					//out.println("     Crit = document.Form1.TXT_SUSPENSE_REFERENSE.value+\"@\"+document.Form1.TXT_ENTRY_TYPE.value+\"@\"+\"Y@\";"); 
					out.println("     Crit = document.Form1.TXT_SUSPENSE_REFERENSE.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,Sql,'2');");
					out.println("}"); 
					
					
					out.println("function suspense_referense_assign(oBj) {"); 
					out.println("   document.Form1.TXT_SUSPENSE_REFERENSE.value=oBj.valout[2];"); 
					out.println("   document.Form1.TXT_PAYEE_NAME.value=oBj.valout[3];"); 
					out.println("}"); 	
					
					out.println("function help_client_code() {"); 
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_ClientSql1','3');");
					
					out.println("}");
					
					out.println("function client_assign(oBj) {"); 
					out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
					out.println("   document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
				 	out.println("}"); 	
						
					out.println("function help_reference_code() {"); 
					out.println("    document.Form1.hid_help_type.value=\"4\";"); 
					//out.println("    Crit = document.Form1.TXT_REFERENCE_CODE.value+\"@\"+\"ACTIVATED@\";"); 
					out.println("     Crit = document.Form1.TXT_REFERENCE_CODE.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'FinanceSql_sus_payment','4');");
					out.println("}");
					
					out.println("function reference_assign(oBj) {"); 
					out.println("   document.Form1.TXT_REFERENCE_CODE.value=oBj.valout[2];"); 
				 	out.println("}"); 	
						
					
					out.println("function ckeck_data(){ "); 
					//out.println("alert('No data to save');");
					out.println("b_flag=0;");
					out.println("if(document.Form1.hid_count.value==0){");
					out.println("alert('No data to save');");
					out.println("b_flag=1;");
					out.println("}"); 
					out.println("else{");
					out.println("b_flag=0;");
					out.println("}"); 
					//out.println("alert('b_flag'+b_flag);");
					out.println("}"); 
			
					out.println("function validate_data(){"); 
					out.println("//validations goes here"); 
					
					out.println("if(document.Form1.TXT_EFF_VALDATE_DD.value==\"\" ||  document.Form1.TXT_EFF_VALDATE_MM.value==\"\" || document.Form1.TXT_EFF_VALDATE_YY.value==\"\"){  "); 
					out.println("	DIV_TXT_EFF_VALDATE.style.color='red';");
					out.println("	return false;"); 
					out.println("}"); 
					
					out.println("else if(document.Form1.TXT_SUSPENSE_REFERENSE.value==\"\"){  "); 
					out.println("	DIV_TXT_SUSPENSE_REFERENSE.style.color='red';");
					out.println("	return false;"); 
					out.println("}"); 
					out.println("else if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
					out.println("	DIV_TXT_CLIENT_CODE.style.color='red';");
					out.println("	return false;"); 
					out.println("}"); 
					
					out.println("else if(document.Form1.TXT_REFERENCE_CODE.value==\"\"){  "); 
					out.println("	DIV_TXT_REFERENCE_CODE.style.color='red';");
					out.println("	return false;"); 
					out.println("}"); 
					
					out.println("else if(document.Form1.TXT_AMOUNT.value==\"\"){  "); 
					out.println("	DIV_TXT_AMOUNT.style.color='red';");
					out.println("	return false;"); 
					out.println("}"); 

					
					out.println("else{"); 
					out.println("	return true;"); 
					out.println("}"); 
					out.println("}");
					
					out.println("function before_submit(){ "); 
				//	out.println("ckeck_data();");
					out.println("   m_status = document.Form1.hid_status.value ");
					out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
					out.println("		if(validate_data()){"); 
					out.println("if(b_flag==0)");
				  out.println("		if(confirm(m_save_msg)){ ");
					out.println("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
					out.println("   document.Form1.elements[i].disabled=false;");
					out.println("   }");
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Sus_Payment_Enter';");  
					out.println("		document.Form1.submit();	"); 
					out.println("		}"); 
					out.println("		}");			
					out.println("} "); 
					
					
					out.println("function makeRequest() {");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Other_Payments_Request?chksql=payment_allocation_details&receiver=\"+document.Form1.TXT_SUSPENSE_REFERENSE.value+\"&suspense_entry_type=\"+document.Form1.TXT_ENTRY_TYPE.value+\"\";");
					//out.println("window.open(m_url);");
					
					out.println("load_interface(m_url,'NORM');");
					out.println("}");
					
					
					out.println("function get_vector_normal(http_response) {");
					out.println(" payment_allocation.innerHTML = ''; ");
					out.println(" payment_allocation.innerHTML = http_response; ");
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
					out.println("  }");				
					out.println("}");		
					
					
					out.println("function check_date(obdd,obmm,obyy){ ");
					out.println(" if((obdd.value !=\"\") && (obmm.value !=\"\") && (obyy.value !=\"\")){");
					out.println("  checkMonthLength(obdd,obmm,obyy);");
					out.println(" }");
					out.println("}");
					
					out.println("function load_lock(){	"); 
					out.println("     document.Form1.TXT_EFF_VALDATE_DD.value='"+_m_sys_date_dd+"';");
					out.println("     document.Form1.TXT_EFF_VALDATE_MM.value='"+_m_sys_date_mm+"';");
					out.println("     document.Form1.TXT_EFF_VALDATE_YY.value='"+_m_sys_date_yy+"';");
					//out.println("document.oncontextmenu=new Function(\"return false\");"); 
					out.println("}	"); 
					
					out.println("function check_number(obj,size){");
					out.println("if(obj.value!='')"); 
					out.println("if(isnumberok(obj,size)){"); 
					out.println("format_number(obj,size)"); 
					out.println("}"); 
					out.println("else{");
					out.println("alert('please enter a number');"); 
					out.println("obj.value='';"); 
					out.println("obj.focus();"); 
					out.println("}"); 
					out.println("}"); 



		
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='Hid_Client_Code' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_Guarantor_Code' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_SUS_PAYMENT_ENTER\">"); 
					


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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - SusPayment Enter </td>"); 
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
					out.println("<td width='20%' ><DIV id='DIV_TXT_SUS_PAYMENT_NO'  class=div_input>SusPayment No*</DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUS_PAYMENT_NO' maxlength='19' size='19' onblur=\"help_sus_payment_no()\" disabled>"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_SUS_PAYMENT_NO' value=\"...\" onClick=\"help_sus_payment_no()\" disabled ></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_EFF_VALDATE'  class=div_input>Value Date * </DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_EFF_VALDATE_DD' maxlength='2' size='2'  onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" >"); 
					out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_MM' maxlength='2' size='2' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" >"); 
					out.println("<input class='txt_input5' type='text' name='TXT_EFF_VALDATE_YY' maxlength='4' size='4' onBlur=\"check_date(document.Form1.TXT_EFF_VALDATE_DD,document.Form1.TXT_EFF_VALDATE_MM,document.Form1.TXT_EFF_VALDATE_YY)\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>Calendar</a></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					rs = stmt.executeQuery ("SELECT SUB_TYPE_CODE, DESCRIPTION "+
				" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
				" WHERE ACCOUNT_TYPE='L' "+
				" AND ACTIVE_STATUS='Y' ");
				
				 more = rs.next();
					
					out.println("<tr>");
					out.println("<td width='20%' ><DIV id='DIV_TXT_ENTRY_TYPE'  class=div_input>Entry Type *</DIV></td>"); 
					out.println("<td width='30%' ><select class=txt_input type=text name=TXT_ENTRY_TYPE maxlength=1 size=1>");  
					out.println("<option value=\"E\">Seizer</option>");
					out.println("<option value=\"L\">Lawyer</option>");
					out.println("<option value=\"A\">Advertistment</option>");
					while(more){
					out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					more = rs.next();
					}
					out.println("</select></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					out.println("</table>"); 
					
					out.println("<table border=\"0\" align='center' width='100%' class='table'>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_SUSPENSE_REFERENSE'  class=div_input>Suspense Reference *</DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUSPENSE_REFERENSE' maxlength='19' size='19' onblur=\"help_suspense_referense()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_SUSPENSE_REFERENSE' value=\"...\" onClick=\"help_suspense_referense()\"></td>"); 
					out.println("<td width='10%' ><DIV id='DIV_PAYEE'  class=div_input>Payee Name </DIV></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYEE_NAME' maxlength='19' size='19' onblur=\"\" style={width:350px;} disabled >"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='19' size='19' onblur=\"help_client_code()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"...\" onClick=\"help_client_code()\"></td>"); 
					out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name </DIV></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='19' size='19' onblur=\"\" style={width:350px;} disabled  >"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 
					
					out.println("<table border=\"0\" align='center' width='100%' class='table'>"); 
					
					out.println("<tr>"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_REFERENCE_CODE'  class=div_input>Reference * </DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REFERENCE_CODE' maxlength='20' size='20' onblur=\"help_reference_code()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_REFERENCE_CODE' value=\"...\" onClick=\"help_reference_code()\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 


					out.println("<tr>"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Amount *</DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_AMOUNT' maxlength='22' size='22' style=\"{text-align:right;}\" onblur=\"check_number(this,22)\"  >"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					
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
			
			String m_receiver = req.getParameter("receiver").trim();																		
			String m_suspense_entry_type = req.getParameter("suspense_entry_type").trim();																		
			
			rs = stmt.executeQuery (" SELECT  "+
			" DISTINCT SUS_REF_NO, "+
			" "+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO), "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)), "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))), "+
			" BAL_TO_BE_PAID, "+
			" REF_NO, "+
			" RECEIVER,   "+
			" NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),' ') PAYEE_NAME,  "+
			" NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE    "+
			" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
			" WHERE UPPER(RECEIVER) LIKE UPPER('"+m_receiver+"%') AND UPPER(SUSPENSE_ENTRY_TYPE) =UPPER('"+m_suspense_entry_type+"')  "+
			" ORDER BY REF_NO ");
				
				boolean more = rs.next();
				
				out.println("<table class='table' border='0' width='100%' cellspacing='0' >");

				  if(more){
					out.println("<tr class=pdn_txtpos2 > "); 
			    out.println("<td width='15%' >Ag.No</td>"); 
					out.println("<td width='15%' >Sus Ref No</td>"); 
					out.println("<td width='20%' >Client</td>"); 
					out.println("<td width='20%' >Branch</td>"); 
					out.println("<td width='15%' >Balance To Be Paid </td>"); 
					out.println("<td width='15%' >Amount</td>"); 
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
									
									out.println("<td width='15%' >"+rs.getString(2)+"</td>"); 
									out.println("<td width='15%' >"+rs.getString(1)+"</td>"); 
									out.println("<td width='20%' >"+rs.getString(3)+"</td>"); 
									out.println("<td width='20%' >"+rs.getString(4)+"</td>"); 
									//out.println("<td width='15%' >"+rs.getString(5)+"</td>"); 
									out.println("<td width='15%' ><input class='txt_input' type='text' name=TXT_BALACE_AMOUNT"+j+" maxlength='22' size='22' value="+rs.getString(5)+" disabled onblur=\"\"  >"); 
									out.println("<td width='15%' ><input class='txt_input' type='text' name=TXT_AMOUNT"+j+" maxlength='22' size='22' onblur=\"\"  >"); 
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


