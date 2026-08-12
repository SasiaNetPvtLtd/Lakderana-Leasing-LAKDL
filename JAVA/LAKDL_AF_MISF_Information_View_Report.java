// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:21-09-2006
                 
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Information_View_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
				
				
			if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");
			 
			//To validate from date & to date
			out.println("function validate_date(){");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
			out.println("    if(!checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
			out.println("     return false;"); 
			out.println("     }");
			out.println("    else {");
			out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
			out.println("			else {");
			out.println("   		alert('To Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");
			out.println("    }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From Date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			out.println(" }");
			
			out.println("function makeRequest_detail() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
			//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("}");
			
						
			out.println("function makeRequest_Receipt_Name() {");
			out.println("    m_name=document.Form1.TXT_NAME.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_name!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_receipts_name&division=\"+m_division+\"&name=\"+m_name;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest_Receipt_chq_no() {");
			out.println("    m_name=document.Form1.TXT_CHEQUE_NO.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_name!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_receipts_chq_no&division=\"+m_division+\"&name=\"+m_name;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest_Receipt_doc_ref_no() {");
			out.println("    m_name=document.Form1.TXT_DOC_REF_NO.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_name!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_receipts_doc_ref_no&division=\"+m_division+\"&name=\"+m_name;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest_Receipt_amount() {");
			out.println("    m_amount_from=document.Form1.TXT_AMOUNT_FROM.value");
			out.println("    m_amount_to=document.Form1.TXT_AMOUNT_TO.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_amount_from!='' &&  m_amount_to!='' ) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_receipts_amount&division=\"+m_division+\"&amount_to=\"+m_amount_to+\"&amount_from=\"+m_amount_from;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest_Receipt_date() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_from_date!='' &&  m_to_date!='' ) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_receipts_date&division=\"+m_division+\"&date_from=\"+m_from_date+\"&date_to=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			
			//-------------------- Payment Details -----------------------------------------

			out.println("function makeRequest_PayDetails_Name() {");
			out.println("    m_name=document.Form1.TXT_NAME.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_name!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_Payment_name&division=\"+m_division+\"&name=\"+m_name;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest_PayDetails_chq_no() {");
			out.println("    m_name=document.Form1.TXT_CHEQUE_NO.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_name!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_Payment_chq_no&division=\"+m_division+\"&name=\"+m_name;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			
			out.println("function makeRequest_PcdDetails_chq_no() {");//Added by Sandun Jayathilke on 12-08-2008
			out.println("    m_name=document.Form1.TXT_CHEQUE_NO.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_name!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_Pdc_chq_no&division=\"+m_division+\"&name=\"+m_name;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			
			
			
			out.println("function makeRequest_PayDetails_doc_ref_no() {");
			out.println("    m_name=document.Form1.TXT_DOC_REF_NO.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_name!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_Payment_doc_ref_no&division=\"+m_division+\"&name=\"+m_name;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest_PayDetails_amount() {");
			out.println("    m_amount_from=document.Form1.TXT_AMOUNT_FROM.value");
			out.println("    m_amount_to=document.Form1.TXT_AMOUNT_TO.value");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_amount_from!='' &&  m_amount_to!='' ) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_Payment_amount&division=\"+m_division+\"&amount_to=\"+m_amount_to+\"&amount_from=\"+m_amount_from;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest_PayDetails_date() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("    m_division=document.Form1.TXT_DIVISION.value");
			out.println("if(m_from_date!='' &&  m_to_date!='' ) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=load_Payment_date&division=\"+m_division+\"&date_from=\"+m_from_date+\"&date_to=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			

			
			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	return true;"); 
			out.println("}"); 			

			out.println("function before_submit(){ "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Information_View_Report?chksql=main_page';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_Receipt_Report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Collection - Receipt Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Collection - Receipt Report \";"); 
			out.println("}"); 
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_screen_status(m_val){"); 
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("		}");
			out.println("		else if(m_val==\"DACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("		}");
			out.println("		else if(m_val==\"RACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("		}");
			out.println("		else{");  
			out.println("			document.Form1.hid_status.value=\"\";");  
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Save\";"); 
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
			out.println("		m_sav_msg=\"Modify\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
			out.println("		m_sav_msg=\"Deactivate\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
			out.println("		m_sav_msg=\"Reactivate\";");  
			out.println("	}");
			out.println("	else{");  
			out.println("		m_sav_msg=\"\";");  
			out.println("	}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("	this.valout   = new Array(10);"); 
			out.println("}"); 
			
			out.println("function help_update() {"); 
			out.println(" document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_facility() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_REPORT_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_1() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_3() {"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_assign_1();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_assign_2();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_update_value_assign_3();"); 
	  	out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("	 	}"); 
			out.println("	}"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
		
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection - Receipt Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<table class='table' width='100%'  >"); 
			
			out.println("<tr >"); 
			out.println("<td  width='15%'>Division</td>"); 
			out.println("<td  width='60%'><select name='TXT_DIVISION' class='txt_input' style=\"width:130px;\" >");
			out.println("<option value=\"L\" >Leasing</option>");
			out.println("<option value=\"F\" >Factoring</option>");
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
					
			
			out.println("<tr >"); 
			out.println("<td width='15%' >Name </td>"); 
			out.println("<td width='60%' ><input class='txt_input' type='text' name='TXT_NAME' style=\"width:130px;\" maxlength='50' size='50'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' style=\"width:100px;\" value=\"Receipt Details\" onClick=\"makeRequest_Receipt_Name()\">");	
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_2' style=\"width:100px;\" value=\"Payment Details\" onClick=\"makeRequest_PayDetails_Name()\">");	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' >Cheque No </td>"); 
			out.println("<td width='60%' ><input class='txt_input' type='text' name='TXT_CHEQUE_NO' style=\"width:130px;\" maxlength='50' size='50'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_3' style=\"width:100px;\" value=\"Receipt Details\" onClick=\"makeRequest_Receipt_chq_no()\">");	
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_4' style=\"width:100px;\" value=\"Payment Details\" onClick=\"makeRequest_PayDetails_chq_no()\">");	
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_7' style=\"width:100px;\" value=\"PDC Details\" onClick=\"makeRequest_PcdDetails_chq_no()\">");	//Added By Sandun on 21-08-2008
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' >Doc Ref / No</td>"); 
			out.println("<td width='60%' ><input class='txt_input' type='text' name='TXT_DOC_REF_NO' style=\"width:130px;\" maxlength='50' size='50'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_5' style=\"width:100px;\" value=\"Receipt Details\" onClick=\"makeRequest_Receipt_doc_ref_no()\">");	
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_6' style=\"width:100px;\" value=\"Payment Details\" onClick=\"makeRequest_PayDetails_doc_ref_no()\">");	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' >Amount Range From</td>"); 
			out.println("<td width='60%' ><input class='txt_input' type='text' name='TXT_AMOUNT_FROM' style=\"width:130px;\" maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' >Amount Range To</td>"); 
			out.println("<td width='60%' ><input class='txt_input' type='text' name='TXT_AMOUNT_TO' style=\"width:130px;\" maxlength='50' size='50'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_5' style=\"width:100px;\" value=\"Receipt Details\" onClick=\"makeRequest_Receipt_amount()\">");	
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_6' style=\"width:100px;\" value=\"Payment Details\" onClick=\"makeRequest_PayDetails_amount()\">");	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' >From Date</td>"); 
			// Added By Samitha Kulatilaka On 2009-10-14 (Function Call On onBlur() Event)
			out.println("<TD WIDTH=\"60%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" onBlur='checkMonthLength(TXT_FROM_DATE_DD,TXT_FROM_DATE_MM,TXT_FROM_DATE_YY)' >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" onBlur='checkMonthLength(TXT_FROM_DATE_DD,TXT_FROM_DATE_MM,TXT_FROM_DATE_YY)' >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" onBlur='checkMonthLength(TXT_FROM_DATE_DD,TXT_FROM_DATE_MM,TXT_FROM_DATE_YY)' >");	
			// Added By Samitha Kulatilaka On 2009-10-14 --- End
			out.println("</td> ");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' >To Date</td>"); 
			
			// Added By Samitha Kulatilaka On 2009-10-14 (Function Call On onBlur() Event)
			out.println("<TD WIDTH=\"60%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" onBlur='checkMonthLength(TXT_TO_DATE_DD,TXT_TO_DATE_MM,TXT_TO_DATE_YY)' >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" onBlur='checkMonthLength(TXT_TO_DATE_DD,TXT_TO_DATE_MM,TXT_TO_DATE_YY)' >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\"  onBlur='checkMonthLength(TXT_TO_DATE_DD,TXT_TO_DATE_MM,TXT_TO_DATE_YY)' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");	
			// Added By Samitha Kulatilaka On 2009-10-14 --- End
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_5' style=\"width:100px;\" value=\"Receipt Details\" onClick=\"makeRequest_Receipt_date()\">");	
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_6' style=\"width:100px;\" value=\"Payment Details\" onClick=\"makeRequest_PayDetails_date()\">");	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
						
			out.println("</table>");  
			

			
			out.println("<br>"); 
			out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			
			
				else if(m_chksql.equals("load_receipts_name")){
				String m_name=req.getParameter("name").trim();
				String m_division=req.getParameter("division").trim();			
				
				if (m_division.equals("L")){
					rs1= stmt1.executeQuery(" SELECT "+
					  "  REC_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						//"  A.STATUS,  "+//6
						"  NVL(DECODE(A.STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return'),'-') ,"+//7
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND UPPER(B.FULL_NAME) LIKE UPPER('%"+m_name+"%') "+
						" ORDER BY FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 /*out.println("function sort_data(m_sort_col) {");
					 out.println(" m_from_date ='"+m_from_date+"';");	
					 out.println(" m_to_date='"+m_to_date+"';"); 	
		  		 out.println("	 m_order_by_type = 'ASC'; ");  
					 out.println("	 if(m_sort_col=='"+m_order_by+"'){");
					 out.println("	   if('"+m_sort_by+"'=='DESC'){");
					 out.println("	      m_order_by_type = 'ASC'; ");  
					 out.println("    }else{");
					 out.println("       m_order_by_type = 'DESC'; ");
					 out.println("    }");
					 out.println("  }else{");
					 out.println("    m_order_by_type = 'ASC'; ");
					 out.println("  }");
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					 out.println(" window.location.href=m_url;"); 
					 out.println("}");
 	         */
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						out.println("</tr>"); 
					}
					
					while(more){
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("</tr>");
							more = rs1.next();
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
						
					} else if (m_division.equals("F")){
					
						rs1= stmt1.executeQuery(" SELECT "+
					  "  RECEIPT_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  DECODE(REC_STATUS,'E','Receipt Entry','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised') ,"+//6						
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND UPPER(B.FULL_NAME) LIKE UPPER('%"+m_name+"%') "+
						" ORDER BY B.FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='12%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("</tr>");
							more = rs1.next();
					}	
					     
      	 		out.println("</table>");				
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
					}
					
			}
			
				else if(m_chksql.equals("load_receipts_chq_no")){
				String m_name=req.getParameter("name").trim();
				String m_division=req.getParameter("division").trim();				
					
				if (m_division.equals("L")){
								
					rs1= stmt1.executeQuery(" SELECT "+
					  "  REC_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  NVL(DECODE(A.STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return'),'-') ,"+//6
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_name+"%') "+
						" ORDER BY FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("	function show_account_type(m_acc_code){");
					out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					out.println("	}");	
					
					out.println("</SCRIPT>");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 							
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					//out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
						
						} else if (m_division.equals("F")){
					
						rs1= stmt1.executeQuery(" SELECT "+
					  "  RECEIPT_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  DECODE(REC_STATUS,'E','Receipt Entry','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised') ,"+//6						
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_name+"%') "+
						" ORDER BY B.FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");				
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
					
					}
						

					
					
			}
			
			
				else if(m_chksql.equals("load_receipts_doc_ref_no")){
				String m_name=req.getParameter("name").trim();
				String m_division=req.getParameter("division").trim();				
					
				if (m_division.equals("L")){
				rs1= stmt1.executeQuery(" SELECT "+
					  "  REC_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  NVL(DECODE(A.STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return'),'-') ,"+//7
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND UPPER(A.REC_NO) LIKE UPPER('%"+m_name+"%') "+
						" ORDER BY FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					} else if (m_division.equals("F")){
					
						rs1= stmt1.executeQuery(" SELECT "+
					  "  RECEIPT_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  DECODE(REC_STATUS,'E','Receipt Entry','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised') ,"+//6						
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_name+"%') "+
						" ORDER BY B.FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");				
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
					
					}
					

					
					
			}
			
				else if(m_chksql.equals("load_receipts_amount")){
				String m_amount_from=req.getParameter("amount_from").trim();
				String m_amount_to  =req.getParameter("amount_to").trim();
				String m_division   =req.getParameter("division").trim();		
				double amount_from  =Double.parseDouble(m_amount_from);
				double amount_to    =Double.parseDouble(m_amount_to);
				
				if (m_division.equals("L")){
				rs1= stmt1.executeQuery(" SELECT "+
					  "  REC_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  NVL(DECODE(A.STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return'),'-') ,"+//7
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						//" AND UPPER(A.REC_NO) LIKE UPPER('%"+m_amount_from+"%') "+
						" AND REC_AMOUNT >= "+m_amount_from+" "+
						" AND REC_AMOUNT <= "+m_amount_to+" "+
						" ORDER BY FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					} else if (m_division.equals("F")){
					
						rs1= stmt1.executeQuery(" SELECT "+
					  "  RECEIPT_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  DECODE(REC_STATUS,'E','Receipt Entry','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised') ,"+//6
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						//" AND UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_amount_from+"%') "+
						" AND REC_AMOUNT >= "+m_amount_from+" "+
						" AND REC_AMOUNT <= "+m_amount_to+" "+
						" ORDER BY B.FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");				
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
					
					}
					

					
					
			}
			
				else if(m_chksql.equals("load_receipts_date")){
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to  =req.getParameter("date_to").trim();
				String m_division   =req.getParameter("division").trim();		
				
				
				if (m_division.equals("L")){
				rs1= stmt1.executeQuery(" SELECT "+
					  "  REC_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  NVL(DECODE(A.STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return'),'-') ,"+//7
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND EFF_VALDATE >= TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
						" AND EFF_VALDATE <= TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
						" ORDER BY FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					} else if (m_division.equals("F")){
					
						rs1= stmt1.executeQuery(" SELECT "+
					  "  RECEIPT_NO, "+//1
						"  B.FULL_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  REC_AMOUNT, "+//5
						"  DECODE(REC_STATUS,'E','Receipt Entry','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised') ,"+//6
						"  A.CLIENT_CODE "+//7
			   	  " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
					  " WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
						" AND EFF_VALDATE >= TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
						" AND EFF_VALDATE <= TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
						" ORDER BY B.FULL_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");				
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
					
					}
					

					
					
			}
			
				else if(m_chksql.equals("load_Payment_name")){
				String m_name=req.getParameter("name").trim();
				String m_division   =req.getParameter("division").trim();		
				
				if (m_division.equals("L")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_NO, "+//1
						"  PAYEE_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAY_AMOUNT, "+//5
						"  NVL(DECODE(PROCESS_STATUS,'RE-APP','Payment Requsition','PRINT','Print','CANCEL','Cancel','DISBRS','Disbursed','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-') , "+// 6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
					  "  WHERE UPPER(PAYEE_NAME) LIKE UPPER('%"+m_name+"%') "+
						"  ORDER BY PAYEE_NAME,EFF_VALDATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
					else if (m_division.equals("F")){
          //Commented by Dineth on 2009-01-09
					/*
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_CODE, "+//1
						"  DISB_TO  ,"+//2
						"  TO_CHAR(PAY_DATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAYMENT_AMOUNT, "+//5
						"  DECODE(PAY_STATUS,'PRINT','Payment Printed','ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','DISB','Disbursement') ,"+//6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
					  "  WHERE UPPER(DISB_TO) LIKE UPPER('%"+m_name+"%') "+
						"  ORDER BY DISB_TO,PAY_DATE ");*/
						
				  //Added by Dineth on 2009-01-09
					
					rs1= stmt2.executeQuery(" SELECT "+ 
                                  " A.PAYMENT_CODE, "+//1 
                                  " NVL(A.DISB_TO,'-')  ,"+//2
                                  " TO_CHAR(A.PAY_DATE,'DD-MM-YYYY') EFF_VALDATE, "+ //3
                                  " NVL(A.CHEQUE_NO,'-'), "+ //4
                                  " PAYMENT_AMOUNT, "+ //5
                                  " DECODE(A.PAY_STATUS,'PRINT','Payment Printed','ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','DISB','Disbursement') , "+//6
                                  " A.CLIENT_CODE "+ //7
                                  " FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+ 
                                  " WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
                                  " AND UPPER(B.FULL_NAME) LIKE UPPER('%"+m_name+"%') "+
                                  " ORDER BY DISB_TO,PAY_DATE ");
						
						
	        //End by Dineth on 2009-01-09
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(7)+"</u></td>");//Modified by Dineth on 2009-01-12 instead of 2, 7 placed
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
			}
			
				else if(m_chksql.equals("load_Payment_chq_no")){
				String m_name=req.getParameter("name").trim();
				String m_division   =req.getParameter("division").trim();		
				
				if (m_division.equals("L")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_NO, "+//1
						"  PAYEE_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAY_AMOUNT, "+//5
						"  NVL(DECODE(PROCESS_STATUS,'RE-APP','Payment Requsition','PRINT','Print','CANCEL','Cancel','DISBRS','Disbursed','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-') , "+// 6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
					  "  WHERE UPPER(CHEQUE_NO) LIKE UPPER('%"+m_name+"%') "+
						"  ORDER BY PAYEE_NAME,EFF_VALDATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
					else if (m_division.equals("F")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_CODE, "+//1
						"  DISB_TO  ,"+//2
						"  TO_CHAR(PAY_DATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAYMENT_AMOUNT, "+//5
						" DECODE(PAY_STATUS,'PRINT','Payment Printed','ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','DISB','Disbursement'),"+//6						
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
					  "  WHERE UPPER(CHEQUE_NO) LIKE UPPER('%"+m_name+"%') "+
						"  ORDER BY DISB_TO,PAY_DATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(7)+"</u></td>");//Modified by Dineth on 2009-01-12 instead of 2 , 7 placed
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
			}
				else if(m_chksql.equals("load_Payment_doc_ref_no")){
				String m_name=req.getParameter("name").trim();
				String m_division   =req.getParameter("division").trim();		
				
				if (m_division.equals("L")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_NO, "+//1
						"  PAYEE_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAY_AMOUNT, "+//5
						"  NVL(DECODE(PROCESS_STATUS,'RE-APP','Payment Requsition','PRINT','Print','CANCEL','Cancel','DISBRS','Disbursed','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-') , "+// 6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
					  "  WHERE UPPER(PAYMENT_NO) LIKE UPPER('%"+m_name+"%') "+
						"  ORDER BY PAYEE_NAME,EFF_VALDATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
					else if (m_division.equals("F")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_CODE, "+//1
						"  DISB_TO  ,"+//2
						"  TO_CHAR(PAY_DATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAYMENT_AMOUNT, "+//5
						" DECODE(PAY_STATUS,'PRINT','Payment Printed','ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','DISB','Disbursement'),"+//6						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
					  "  WHERE UPPER(PAYMENT_CODE) LIKE UPPER('%"+m_name+"%') "+
						"  ORDER BY DISB_TO,PAY_DATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
			}
			
				else if(m_chksql.equals("load_Payment_amount")){
				
				String m_amount_from=req.getParameter("amount_from").trim();
				String m_amount_to  =req.getParameter("amount_to").trim();
				String m_division   =req.getParameter("division").trim();		
				double amount_from  =Double.parseDouble(m_amount_from);
				double amount_to    =Double.parseDouble(m_amount_to);
				

				
				if (m_division.equals("L")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_NO, "+//1
						"  PAYEE_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAY_AMOUNT, "+//5
						"  NVL(DECODE(PROCESS_STATUS,'RE-APP','Payment Requsition','PRINT','Print','CANCEL','Cancel','DISBRS','Disbursed','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-') , "+// 6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
						"  WHERE PAY_AMOUNT >= "+m_amount_from+" "+
						"  AND PAY_AMOUNT <= "+m_amount_to+" "+
						"  ORDER BY PAYEE_NAME,EFF_VALDATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
					else if (m_division.equals("F")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_CODE, "+//1
						"  DISB_TO  ,"+//2
						"  TO_CHAR(PAY_DATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAYMENT_AMOUNT, "+//5
  				 	"  DECODE(PAY_STATUS,'PRINT','Payment Printed','ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','DISB','Disbursement'),"+//6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
 						"  WHERE PAYMENT_AMOUNT >= "+m_amount_from+" "+
						"  AND PAYMENT_AMOUNT <= "+m_amount_to+" "+
						"  ORDER BY DISB_TO,PAY_DATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
			}
			
				else if(m_chksql.equals("load_Payment_date")){
				
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to  =req.getParameter("date_to").trim();
				String m_division   =req.getParameter("division").trim();		
				
				if (m_division.equals("L")){
					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_NO, "+//1
						"  PAYEE_NAME  ,"+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAY_AMOUNT, "+//5
						"  NVL(DECODE(PROCESS_STATUS,'RE-APP','Payment Requsition','PRINT','Print','CANCEL','Cancel','DISBRS','Disbursed','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-') , "+// 6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
						"  WHERE EFF_VALDATE >= TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
						"  AND   EFF_VALDATE <= TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
						"  ORDER BY PAYEE_NAME,EFF_VALDATE ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 
						
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
					else if (m_division.equals("F")){

					
					rs1= stmt2.executeQuery(
					  "  SELECT "+
					  "  PAYMENT_CODE, "+//1
						"  DISB_TO  ,"+//2
						"  TO_CHAR(PAY_DATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					  "  NVL(CHEQUE_NO,'-'), "+//4
					  "  PAYMENT_AMOUNT, "+//5
  				 	"  DECODE(PAY_STATUS,'PRINT','Payment Printed','ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','DISB','Disbursement'),"+//6
						"  CLIENT_CODE "+//7
			   	  "  FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
 						"  WHERE PAY_DATE >= TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
						"  AND   PAY_DATE <= TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
						"  ORDER BY DISB_TO,PAY_DATE ");
						
						
					 
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Payment Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}	
					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Payment No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input onClick=\"show_payment_details('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='18%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					}
					
			}
			
				else if(m_chksql.equals("load_Pdc_chq_no")){//Added By Sandun Jayathilake***on 12-08-2008***
				{
				String m_name=req.getParameter("name").trim();
												
					rs1= stmt1.executeQuery(" SELECT "+
   																" POD_REF_NO,"+//1
																  " CHEQUE_NO,"+//2
																  " TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),"+//3
																  " A.CLIENT_CODE,"+//4
																  " NVL(DECODE(STATUS,'INV','Entered','REC','Recipt','WIT','Withdraw','APP','Approve'),'-'),"+//5
																	" A.CHEQUE_AMOUNT,"+//6
																  " NVL(FINANCE_NO,'-'), "+//7
																	" NVL(B.FULL_NAME,'-')"+//8
																  " FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
																	" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
																	" AND CHEQUE_NO='"+m_name+"' ");
					
					boolean mflag=true;							
					boolean more = rs1.next();
					
					out.println("<HTML><HEAD><TITLE>PDC Details</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
									
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>PDC No</b></DIV></td>"); 
						out.println("<td width='12%' style= cursor:hand; ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='18%' style= cursor:hand; ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Amount</b></DIV></td>"); 		
						out.println("<td width='10%' style= cursor:hand; ><DIV class=div_input ><b>Status</b></DIV></td>"); 		
						
						out.println("</tr>"); 
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						
							out.println("<td width='12%' class=div_input >"+rs1.getString(1)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='18%' class=div_input >"+rs1.getString(8)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(6))+"</td>");
								out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
							
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
													
					
			    }		
			}				
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
