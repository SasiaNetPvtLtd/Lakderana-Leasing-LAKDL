// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:21-09-2006

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_receipt_report_new extends javax.servlet.http.HttpServlet { 
	
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
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);   
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			stmt3=conn.createStatement(); 	
			
			if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Receipt Report </TITLE>"); 
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
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; ");
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; ");  // Samith dilshan on 2015-06-15
				out.println("   m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 18-08-2015
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region;");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;"); 
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=TO_date(ENT_DATE,'DD-MM-YYYY HH24:MI:SS')&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;"); 
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				out.println("function makeRequest_detail_active() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; "); // added by udara on 27-02-2013
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; ");  // Samith dilshan on 2015-06-15
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 18-08-2015
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_active_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_active_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region;");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_active_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_active_receipts&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=TO_date(ENT_DATE,'DD-MM-YYYY HH24:MI:SS')&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				
				
				out.println("function makeRequest_detail_cancel() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; "); // added by udara on 27-02-2013
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; ");  // Samith dilshan on 2015-06-15
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 18-08-2015
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_cancel&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region;");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_cancel&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_cancel&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=TO_date(ENT_DATE,'DD-MM-YYYY HH24:MI:SS')&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				
				//Added by Dineth on 2008-09-29
				out.println("function makeRequest_detail_return() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; "); // added by udara on 27-02-2013
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; ");  // Samith dilshan on 2015-06-15
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 18-08-2015
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_return&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_return&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region;");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_return&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_return&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=TO_date(ENT_DATE,'DD-MM-YYYY HH24:MI:SS')&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;"); //modified by kanchana on 2016-07-20
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				
				//End by Dineth on 2008-09-29
				//Added by Dineth on 16-06-2009
				out.println("function makeRequest_detail_PDC() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; ");// addedby udara on 27-02-2013
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; ");  // Samith dilshan on 2015-06-15
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 18-08-2015
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_PDC&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=CHEQUE_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_PDC&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=CHEQUE_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region;");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_PDC&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=CHEQUE_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				//End by Dineth on 16-06-2009
				
				out.println("function makeRequest_detail_Stand_Order() {");//Added By Sandun On 16-09-2009
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; "); // added by udara on 27-02-2013
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; ");  // Samith dilshan on 2015-06-15
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 18-08-2015
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_stdo&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_stdo&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region;");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_stdo&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_stdo&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=TO_date(ENT_DATE,'DD-MM-YYYY HH24:MI:SS')&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				out.println("function makeRequest_detail_Stand_Temp() {");//Added By Sandun On 16-09-2009
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_location_code = document.Form1.LOCATION_CODE.value ");
				out.println(" 	m_user = document.Form1.TXT_USER_ID.value ");
				out.println("   branch_id = document.Form1.TXT_BRANCH_ID.value; "); // added by udaera on 27-02-2013
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; ");  // Samith dilshan on 2015-06-15
				out.println("       m_active_yard_status = document.Form1.TXT_ACTIVE_STATUS.value;"); // added by udara 18-08-2015
				
				out.println("		if(validate_date()) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_temp&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";"); // commented by udara on 27-02-2013
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_temp&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region;");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_temp&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;"); //commented by kanchana on 2016-07-20
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_temp&user=\"+m_user+\"&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=TO_date(ENT_DATE,'DD-MM-YYYY HH24:MI:SS')&sort_by=ASC&branch_id=\"+branch_id+\"&region=\"+m_region+\"&active_yard_status=\"+m_active_yard_status;"); //ent_date added by kanchana on 2016-07-20
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new=main_page';"); 
				out.println("}"); 
				
				out.println("function save_window(){	"); 
				out.println("	before_submit();"); 
				out.println("}"); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_STATEMENT_REPORT\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
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
				//out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 

				//out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			//out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
				//out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
					out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("		if(oBj.valout[1] !=\"Close\"){"); 
				out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("				if(oBj.valout[1]!=\"Next\"){"); 
				out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("						help_value_assign_client_id();"); 
				out.println("					}"); 
				out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("						help_update_value_assign_2();"); 
				out.println("					}"); 
				out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("						help_update_value_assign_3();"); 
				out.println("					}"); 
				out.println("					if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("						help_value_assign_branch_id();"); 
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
				
				out.println("function help_button_user_id() {"); 
			  	out.println(" document.Form1.hid_help_type.value='1';");
				out.println(" var sql = '';");
				out.println(" if(document.Form1.LOCATION_CODE.value == 'ALL'){");
				out.println("  sql = 'm_help_TXT_USERS_sql';");
				out.println(" }");
				out.println(" else{");
				out.println("  sql = 'm_help_TXT_USERS_with_loc_sql'; ");
				out.println(" }");
			  	out.println(" m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+document.Form1.LOCATION_CODE.value+\"@\";"); 
			    out.println(" 	m_sql = sql;"); 
					
			  	out.println(" HelpBox('1','10','0');");
			  	out.println("}"); 
				
				out.println("function help_value_assign_client_id() {"); 
				out.println(" document.Form1.TXT_USER_ID.value=oBj.valout[2];"); 

				//out.println("alert(oBj.valout[8]);");
				out.println("}");
				
				out.println("function clear_user() {"); 
				out.println(" document.Form1.TXT_USER_ID.value='';"); 

				out.println("}");
				
				// added by udara on 27-02-2013
				
				out.println("function help_button_branch_id() {"); 
			  	out.println(" document.Form1.hid_help_type.value='4';");
				out.println(" m_criteria = document.Form1.TXT_BRANCH_ID.value+\"@\"+document.Form1.TXT_USER_ID.value+\"@\";");
				out.println(" m_sql = 'm_help_TXT_BRANCH_sql';"); 
			  	out.println(" HelpBox('1','10','0');");
			  	out.println("}"); 
					
				out.println("function help_value_assign_branch_id() {"); 
				out.println(" 	document.Form1.TXT_BRANCH_ID.value=oBj.valout[2];"); 
				out.println("}");
				
				
				// end by udara on 27-02-2013
				
				
				
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
				
				
				//added by nwuan de silva on 28-03-2011 ---------------------------------------------------------
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch(Contracts)</DIV></td>"); 
				out.println("<td width='*%' >");
				
				out.println("<select name=\"LOCATION_CODE\" class=\"txt_input\" onchange='clear_user()' >");
				
				rs = stmt.executeQuery(
					" SELECT LOCATION_CODE, "+
					"        LOCATION_DESC "+
					" FROM "+m_schema_name+".AF_CO_MAS_LOCATION  WHERE ACTIVE_STATUS='Y' ");
				
				boolean more = rs.next();
				out.println("<OPTION value=\"ALL\" SELECTED >ALL</option>");
				while(more){
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					more = rs.next();	
				}	
				
				out.println("</SELECT></TD>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				//end section added by nwuan de silva on 28-03-2011 -------------------------------------------------
				
				
				out.println("<table class='table' width='100%'>"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_USER_ID'  class=div_input>User </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER_ID' maxlength='15' style='{width=150px}' size='10' onblur=\"help_button_user_id()\"><input class='but_input' type='button' name='BUT_USER_ID' value=\"Help\" onClick=\"help_button_user_id()\">"); 
				out.println("</td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
			
				// added by udara on 27-02-2013
				out.println("<table class='table' width='100%'>"); 
				out.println("<tr>"); // out.println("<tr style = '{display: none;}' >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_BRANCH_ID'  class=div_input>Location Code </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_BRANCH_ID' maxlength='15' style='{width=150px}' size='10' onblur=\"help_button_branch_id()\"><input class='but_input' type='button' name='BUT_BRANCH_ID' value=\"Help\" onClick=\"help_button_branch_id()\">"); 
				out.println("</td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				// Added By: Samith dilshan  On : 2015-06-03
				out.println("<tr>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				rs3 = stmt3.executeQuery (" SELECT REGIONS_CODE, REGIONS_DESC  "+
					" FROM "+m_schema_name+".AF_CO_MAS_REGIONS "+
					" WHERE ACTIVE_STATUS='Y' "+
					" ORDER BY REGIONS_DESC ");
				
				while(rs3.next()){
					out.println("  <OPTION value=\""+rs3.getString(1)+"\">"+rs3.getString(2)+"</OPTION>");
				}
				
				out.println(" 	</select>");
				out.println(" </td>"); 
				out.println("</tr>");

				// added by udara 18-08-2015
				out.println("<tr>"); 
				out.println("<td width='10%' > Active/Yard Vehicles </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ACTIVE_STATUS'>"); 
				out.println("<option value='A' > All </option>");
				out.println("<option value='Y' > Active </option>");
				out.println("<option value='N' > Yard Vehicles </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				// end by udara 18-08-2015
				
				
				out.println("</table>");
				
				// end by udara on 27-02-2013
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
				out.println("<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\"  >"); //value=\"01\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" >"); //value=\"04\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"   >");	 //value=\"2007\"
				out.println("</td> ");
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
				out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
				out.println("</td> ");
				out.println("<td width='*%' >");
				//ENT_DATE modification done for the onclick functions by Kanchana on 2016-07-28
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' style=\"{width:110px;}\" value=\"View All Receipts\" onClick=\"makeRequest_detail()\">");
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_7' style=\"{width:110px;}\" value=\"View Active Receipts\" onClick=\"makeRequest_detail_active()\">");
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_2' style=\"{width:110px;}\" value=\"View Cancel Receipts\" onClick=\"makeRequest_detail_cancel()\">");
				// Added by Dineth on 2008-09-29
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_3' style=\"{width:110px;}\" value=\"View Return Receipts\" onClick=\"makeRequest_detail_return()\">");
				// End by Dineth
				//Added by Dineth on 16-06-2009
				//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_4' style=\"{width:110px;}\" value=\"View PDC Receipts\" onClick=\"makeRequest_detail_PDC()\">");
				//End by Dineth on 16-06-2009
				out.println("</td> ");
				//			out.println("<td width='*%' ></td>");
				
				
				out.println("<tr>");//Added By Sandun on 16-09-2009
				out.println("<td colspan=4>&nbsp;</td>");
				out.println("<td>");
				//Added by Dineth on 16-06-2009
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_4' style=\"{width:110px;}\" value=\"View PDC Receipts\" onClick=\"makeRequest_detail_PDC()\">");
				//End by Dineth on 16-06-2009
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_5' style=\"{width:110px;}\" value=\"View Standing Orders \" onClick=\"makeRequest_detail_Stand_Order()\">");//Added By Sandun on 16-09-2009
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_6' style=\"{width:110px;}\" value=\"View Temp. Receipts\" onClick=\"makeRequest_detail_Stand_Temp()\">");//Added By Sandun on 16-09-2009
				out.println("</td>");
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
			else if(m_chksql.equals("load_receipts")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_region = req.getParameter("region");
				
				double m_tot_rec=0;
				String Sql_data = " SELECT "+
					"  REC_NO, "+//1
					"  REC_AMOUNT, "+//2
					"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					"  ENT_USER, "+//4
					"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
					//"  NVL(CHEQUE_NO,'-'), "+//6
					"  CLIENT_CODE, "+//6
					"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
					" NVL(SUS_REF_NO,'-'), "+//8
					" SETTLE_MODE,"+//9
					" NVL(OTH_COMMENTS,'-') , "+//10
					" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
					" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'RENTAL'),0), "+//Added by Dineth on 11-06-2009
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'ODI'),0), "+//Added by Dineth on 11-06-2009
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'OTHER'),0), "+//Added by Dineth on 11-06-2009
					" BRANCH_CODE,"+
					" NVL("+m_schema_name+".AF_CO_GET_USER_REGION(BRANCH_CODE),'-') REGION "+ //-------Added by Samith Dilshan on 15-06-2015
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08
					" ORDER BY "+m_order_by+" "+m_sort_by+" ";
				
				
				//out.println(Sql_data);	
				//System.out.println(Sql_data);	
				rs1= stmt1.executeQuery(Sql_data);
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
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
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt report from  "+m_from_date+" to "+m_to_date+" </B></TD></TR>");
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
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('EFF_VALDATE') ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Rental Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					out.println("<td width='12%' ><DIV class=div_input ><b>ODI Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					out.println("<td width='12%' ><DIV class=div_input ><b>Other Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(15)+"</td>");//Added by Dineth on 11-06-2009
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(16)+"</td>");//Added by Dineth on 11-06-2009
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(17)+"</td>");//Added by Dineth on 11-06-2009
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//Added by Dineth on 11-06-2009
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//End by Dineth on 11-06-2009
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//Added by Dineth on 11-06-2009
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//End by Dineth on 11-06-2009
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08
					
					" GROUP BY SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08
					
					" GROUP BY ENT_USER,SETTLE_MODE "+
					" ORDER BY ENT_USER,SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
			}
			
			
			else if(m_chksql.equals("load_receipts_cancel")){
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				
				String m_mod_user ="-";//Added By Sandun on 16-01-2009
				String m_mod_date ="-";
				
				double m_tot_rec=0;
				rs1= stmt1.executeQuery(" SELECT "+
					"  A.REC_NO, "+//1
					"  A.REC_AMOUNT, "+//2
					"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					"  A.MOD_USER, "+//4
					"  TO_CHAR(A.MOD_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
					//"  NVL(CHEQUE_NO,'-'), "+//6
					"  A.CLIENT_CODE, "+//6
					"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
					" NVL(A.SUS_REF_NO,'-'), "+//8
					" A.SETTLE_MODE,"+//9
					" NVL(A.OTH_COMMENTS,'-'), "+//10
					" A.ENT_USER , "+//11              
					" NVL(B.COMMENTS,'-'), "+//12    //Added By Sandun on 19-11-2008
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(A.REC_NO,'RENTAL'),0), "+//Added by Dineth on 11-06-2009
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(A.REC_NO,'ODI'),0), "+//Added by Dineth on 11-06-2009
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(A.REC_NO,'OTHER'),0) "+//Added by Dineth on 11-06-2009
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A ,"+m_schema_name+".AF_CO_PRO_RECEIPT_CANCEL B  "+
					" WHERE  A.REC_NO = B.REC_NO "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS ='CAD'"+ //ADDED BY NUWAN ON 01-04-08
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Cancelation Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_cancel&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Cancelation Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
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
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='8%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('EFF_VALDATE') ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>");
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Cancel Remarks</b></DIV></td>");
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Modified User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Enter User</b></DIV></td>");
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Modified User'    onclick=sort_data('MOD_USER') ><DIV class=div_input ><b>Modified User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Modified Date'    onclick=sort_data('MOD_DATE') ><DIV class=div_input ><b>Modified Date</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Rental Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					out.println("<td width='12%' ><DIV class=div_input ><b>ODI Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					out.println("<td width='12%' ><DIV class=div_input ><b>Other Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					//----------------------------------------------------------------------
					rs3= stmt3.executeQuery(" SELECT MOD_USER,MOD_DATE "+  //Added By Sandun on 16-01-2009
						"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BK "+
						"  WHERE REC_NO = '"+rs1.getString(1)+"' "+
						"  AND MOD_DATE IN (SELECT MAX(MOD_DATE) FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BK GROUP BY REC_NO) ");
					
					if(rs3.next()){
						m_mod_user = rs3.getString(1);
						m_mod_date = rs3.getString(2);
					}
					//----------------------------------------------------------------------
					
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='8%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					//out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					//out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+m_mod_user+"</td>");
					out.println("<td width='12%' class=div_input >"+m_mod_date+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(13)+"</td>");//Added by Dineth on 11-06-2009
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(14)+"</td>");//Added by Dineth on 11-06-2009
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(15)+"</td>");//Added by Dineth on 11-06-2009
					
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				
				
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Dineth on 2008-09-29
			
			else if(m_chksql.equals("load_receipts_return")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				
				double m_tot_rec=0;
				rs1= stmt1.executeQuery(" SELECT "+
					"  REC_NO, "+//1
					"  REC_AMOUNT, "+//2
					"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
					"  ENT_USER, "+//4
					"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
					//"  NVL(CHEQUE_NO,'-'), "+//6
					"  CLIENT_CODE, "+//6
					"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
					" NVL(SUS_REF_NO,'-'), "+//8
					" SETTLE_MODE,"+//9
					" NVL(OTH_COMMENTS,'-') , "+//10
					" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
					" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'),"+
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'RENTAL'),0), "+//Added by Dineth on 11-06-2009
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'ODI'),0), "+//Added by Dineth on 11-06-2009
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'OTHER'),0) "+//Added by Dineth on 11-06-2009
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
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
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
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
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('EFF_VALDATE') ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Rental Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					out.println("<td width='12%' ><DIV class=div_input ><b>ODI Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					out.println("<td width='12%' ><DIV class=div_input ><b>Other Invoice Amt</b></DIV></td>"); //Added by Dineth on 11-06-2009
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(15)+"</td>");//Added by Dineth on 11-06-2009
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(16)+"</td>");//Added by Dineth on 11-06-2009
					out.println("<td width='12%' class=div_input >"+rs1.getDouble(17)+"</td>");//Added by Dineth on 11-06-2009
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//Added by Dineth on 11-06-2009
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//End by Dineth on 11-06-2009
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//Added by Dineth on 11-06-2009
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//End by Dineth on 11-06-2009
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
					
					" GROUP BY SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
					
					" GROUP BY ENT_USER,SETTLE_MODE "+
					" ORDER BY ENT_USER,SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
			}
			
			
			
			
			//End by Dineth on 2008-09-29
			//Added by Dineth on 16-06-2009
			else if(m_chksql.equals("load_receipts_PDC")){
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				
				double m_tot_rec=0;
				rs1= stmt1.executeQuery(" SELECT "+
					"  REC_NO, "+//1
					"  REC_AMOUNT, "+//2
					"  TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
					"  ENT_USER, "+//4
					"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
					//"  NVL(CHEQUE_NO,'-'), "+//6
					"  CLIENT_CODE, "+//6
					"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
					" NVL(SUS_REF_NO,'-'), "+//8
					" SETTLE_MODE,"+//9
					" NVL(OTH_COMMENTS,'-') , "+//10
					" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
					" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'),CHEQUE_DATE "+
					//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'RENTAL'),0), "+//Added by Dineth on 11-06-2009
					//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'ODI'),0), "+//Added by Dineth on 11-06-2009
					//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'OTHER'),0) "+//Added by Dineth on 11-06-2009
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND PRINT_STATUS='POD' "+ 
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
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
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
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
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('CHEQUE_DATE') ><DIV class=div_input ><b>Cheque Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND PRINT_STATUS='POD'"+ //Added by Dineth on 16-06-2009
					
					" GROUP BY SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND PRINT_STATUS='POD' "+ //Added by Dineth on 16-06-2009
					
					" GROUP BY ENT_USER,SETTLE_MODE "+
					" ORDER BY ENT_USER,SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
				
				
				
			}
			//End by Dineth on 16-06-2009
			
			else if(m_chksql.equals("load_receipts_temp")){//Added By Sandun on 16-09-2009
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				
				
				double m_tot_rec=0;
				rs1= stmt1.executeQuery(" SELECT "+
					"  REC_NO, "+//1
					"  REC_AMOUNT, "+//2
					"  TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
					"  ENT_USER, "+//4
					"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
					"  CLIENT_CODE, "+//6
					"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
					" NVL(SUS_REF_NO,'-'), "+//8
					" SETTLE_MODE,"+//9
					" NVL(OTH_COMMENTS,'-') , "+//10
					" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
					" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'),NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND REC_TYPE='TMP' "+ 
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
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
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				
				out.println("	function receipt_doc(val1){");
				
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=print_receipt&receipt_no=\"+val1+\" \";");
				//out.println("   window.open(m_url,'popupwin1','left=100,top=10,status=0,menubar=0,scrollbars=1,height=700,width=650');	");
				out.println(" window.location.href=m_url;"); 
				out.println("	}");					
				
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='120%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='120%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('CHEQUE_DATE') ><DIV class=div_input ><b>Cheque Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					//out.println("<td width='12%' >&nbsp;</td>"); 
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					//out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Detail\" onclick=\"receipt_doc('"+rs1.getString(1)+"','"+rs1.getString(6)+"')\"></td>"); 
					//out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Print\" onclick=\"receipt_doc('"+rs1.getString(1)+"')\"></td>"); //Mod By Sandun  on 21-07-2009
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND REC_TYPE='TMP' "+ 
					" GROUP BY SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND REC_TYPE='TMP'  "+ 
					
					" GROUP BY ENT_USER,SETTLE_MODE "+
					" ORDER BY ENT_USER,SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
			}
			
			
			else if(m_chksql.equals("load_receipts_stdo")){//Added By Sandun on 16-09-2009
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				
				
				double m_tot_rec=0;
				rs1= stmt1.executeQuery(" SELECT "+
					"  REC_NO, "+//1
					"  REC_AMOUNT, "+//2
					"  TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
					"  ENT_USER, "+//4
					"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
					"  CLIENT_CODE, "+//6
					"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
					" NVL(SUS_REF_NO,'-'), "+//8
					" SETTLE_MODE,"+//9
					" NVL(OTH_COMMENTS,'-') , "+//10
					" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
					" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'),CHEQUE_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND SETTLE_MODE ='STD_ORD' "+
					" AND STATUS IN ('E','B') "+
					" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_stdo&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				
				out.println("	function receipt_doc(val1){");
				
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=print_receipt&receipt_no=\"+val1+\" \";");
				//out.println("   window.open(m_url,'popupwin1','left=100,top=10,status=0,menubar=0,scrollbars=1,height=700,width=650');	");
				out.println(" window.location.href=m_url;"); 
				out.println("	}");					
				
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='120%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='120%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('CHEQUE_DATE') ><DIV class=div_input ><b>Cheque Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					//out.println("<td width='12%' >&nbsp;</td>"); 
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					out.println("<td width='12%' class=div_input style='WORD-BREAK:BREAK-ALL' >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					//out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Detail\" onclick=\"receipt_doc('"+rs1.getString(1)+"','"+rs1.getString(6)+"')\"></td>"); 
					//out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Print\" onclick=\"receipt_doc('"+rs1.getString(1)+"')\"></td>"); //Mod By Sandun  on 21-07-2009
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND SETTLE_MODE ='STD_ORD' "+ 
					" AND STATUS IN ('E','B') "+
					" GROUP BY SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND SETTLE_MODE ='STD_ORD' "+ 
					" AND STATUS IN ('E','B') "+
					" GROUP BY ENT_USER,SETTLE_MODE "+
					" ORDER BY ENT_USER,SETTLE_MODE ");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");			
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
