/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
// CREATED BY SANJEEWA ON 2010-07-14
// DISPLAY NAME INVOICE DETAIL REPORT
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
import java.math.*;

public class LAKDL_FA_OP_Insurance_Detail_Report_display extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);

			String query="";
			
			String m_from_date          = req.getParameter("from_date");
			String m_to_date            = req.getParameter("to_date");
			String m_report_type        = req.getParameter("report_type");
			String m_insurance_company  = req.getParameter("insurance_company");
			String m_insurance_done     = req.getParameter("insurance_done");
			String m_branch_code        = req.getParameter("branch_code"); //added by kanchana.
			String m_branch_code_sql ="";
			
			String m_active_status    = "";
			m_active_status    = req.getParameter("active_status"); 
			
			String m_premium_status    = "";
			m_premium_status    = req.getParameter("premium_status"); 
			
			String m_finance_no    = "";
			m_finance_no    = req.getParameter("finance_no");  
			
			
			stmt=conn.createStatement();
			ServletOutputStream out = res.getOutputStream();
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Insurance Details Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("	function show_transaction_info(m_client_code,m_finance_no){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
			out.println("    window.open(m_url); ");
			out.println("	}");
			
			out.println("</script>");
			
			
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
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
			out.println("<td style='height: 30px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Insurance Details Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>");
			out.println("</tr>");
			out.println("</table>"); 
			out.println("</table>"); 

			
			out.println("<br>");

			
			if (m_insurance_done.equals("ALL")) {
				m_insurance_done = "%%";
			}
			
			
			// added by udara 27-09-2017
			
			String m_insurance_done_string = ""; 
			
			if(m_insurance_done.equals("LICENSEE")){
				m_insurance_done_string = " AND C.INSURANCE_DONE_BY = 'LICENSEE' "; 
			}
			else if(m_insurance_done.equals("CLIENT")){
				m_insurance_done_string = " AND C.INSURANCE_DONE_BY = 'CLIENT' "; 		
			}
			else{
				m_insurance_done_string = " "; 
			}
			
			String m_insurance_company_string = "";			
			
			if (m_insurance_company.equals("")) {
				m_insurance_company_string = " ";
			}
			else{
				m_insurance_company_string = " AND    A.INSUR_COM = '" + m_insurance_company + "' ";
			}
			
			String m_finance_no_string = "";
			
			if (m_finance_no.equals("")) {
				m_finance_no_string = " ";
			}
			else{
				m_finance_no_string = " AND    A.FINANCE_NO = '" + m_finance_no + "' ";
			}
			
			// end by udara 27-09-2017
			
			
			
			if (m_active_status.equals("ALL")) {
				m_active_status = " ";
			}
			else if(m_active_status.equals("ACTIVE")){
				m_active_status = " AND C.APPLICATION_STATUS = 'ACTIVATED' "; // added by udara 27-09-2017
			}
			
			// added by udara 20-10-2014
			else if(m_active_status.equals("TERMI")){
				m_active_status = " AND C.APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI') "; // added by udara 27-09-2017
			}
			// end by udara 20-10-2014
			
			else{
				//m_active_status = " AND C.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','NORM_TERMI') "; // commented by udara 27-07-2021 // added by udara 27-09-2017
				m_active_status = " AND C.APPLICATION_STATUS NOT IN ('ACTIVATED','TERMI','TERMINATED','NORM_TERMI') "; // added by udara 27-07-2021
			}

			
			// added by udara 11-09-2014
			if (m_report_type.equals("ALL")) {
				m_report_type = " ";
			}
			else if(m_report_type.equals("NEW")){
				m_report_type = " AND A.BUSINESS_TYPE = 'NEW' ";
			}
			else{ // RENEWAL
				m_report_type = " AND A.BUSINESS_TYPE = 'RENEWAL' ";
			}
			// end by udara 11-09-2014
			
			
			//Added by Kanchana.
			if(!m_branch_code.equals("")){
				m_branch_code_sql  = "	AND    C.BRANCH_CODE = '"+m_branch_code+"'  "; // added by udara 27-09-2017
				
			}
			else if(m_branch_code.equals("")) {
				m_branch_code_sql = " ";
			}
			//ended by Kanchana
			
			
			String sql = null;
			
			String sql1 = null;
			String sql2 = null;
			String sql3 = null;
			
			
			/*
			
			// added by udara 28-03-2016
			
			sql1 = " " +
				"   SELECT DISTINCT TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"   NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+ 
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // 22 added by udara 05-12-2014
				"   A.ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				"	SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+ // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+  // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A, " + m_schema_name + ".AF_CO_PRO_INVOICE B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C " +
				"   WHERE  A.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				
				"   AND    B.VALUE_DATE >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    B.VALUE_DATE <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_active_status+"   "+ // added by udara 18-07-2014
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				"   UNION "+
				
				"   SELECT DISTINCT TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"        NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+ 
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // added by 22 udara 05-12-2014
				"   A.ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				"	SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " +
				"   WHERE  " +
				"       A.FINANCE_NO = C.FINANCE_NO "+
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				// ===================================================================================================================
				
				" UNION "+
				
				" SELECT  START_DATE, "+
				" DEBIT_NOTE_NO, "+
				" POLICY_NO, "+
				" CLIENT_NAME, "+
				" SUM_INSSURED, "+
				" PREMIUM, "+
				" SRCC_TC, "+
				" TAX_DUE, "+
				" BASIC_PREMIUM, "+
				" BASIC_PREMIUM_COMMISION, "+
				" RCC_TC_COMMISION, "+
				" VAT_ON_TOTAL_COMMISION, "+
				" FINANCE_NO, "+
				" VALUE_DATE, "+
				" INSUR_COM, "+
				" PAYABLE_PREMIUM, "+
				" CLIENT_CODE,  "+
				" REMARKS, "+ // 18
				" P_ACTIVE_STATUS, "+ // 19
				" INSURENCE_DONE_BY, "+ // 20 added by udara 29-09-2014
				" BUSINESS_TYPE, "+ // 21 added by udara 05-12-2014
				" ENT_USER, "+ // 22 added by udara 05-12-2014
				" ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				" ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" FINANCE_NO_SORT,"+
				" ENT_DATE_1, "+
				" ENT_DATE_2, "+
				" ENT_DATE_3 "+
				" ,VEHICLE_NO "+ // added by udara 20-07-2017
				" FROM ( "+
				
				" SELECT DISTINCT  "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO, "+
				" A.POLICY_NO POLICY_NO, "+
				"  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" A.SUM_INSSURED SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+	
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER ENT_USER, "+ // added by 22 udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	 // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.REF_DEBIT_NOTE_NO  = B.DEBIT_NOTE_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" UNION "+
				
				" SELECT DISTINCT "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO,  "+
				" A.POLICY_NO POLICY_NO,  "+
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" A.SUM_INSSURED SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+ 
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER ENT_USER, "+ // added by 22 udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+ 
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+		  // Added  by Kanchana fro issure no 19089	
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.POLICY_NO = B.POLICY_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				" AND A.REF_DEBIT_NOTE_NO  IS NULL "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" ) "+
				
				// ===================================================================================================================
				" 	ORDER BY FINANCE_NO_SORT ASC,ENT_DATE_1 DESC,ENT_DATE_2 DESC,ENT_DATE_3 DESC "+  // Added  by Kanchana fro issure no 19089
				" ";
			
			// end by udara 28-03-2016
			
			//out.println(sql1);
			
			
			
			// activated
			sql2 = " " +
				"   SELECT DISTINCT TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"          NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // 22 added by udara 05-12-2014
				
				"   A.ENT_DATE ENT_DATE, "+  //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+  // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A, " + m_schema_name + ".AF_CO_PRO_INVOICE B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C " +
				"   WHERE  A.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				"   AND    B.VALUE_DATE >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    B.VALUE_DATE <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_active_status+"   "+ // added by udara 18-07-2014
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				"   UNION "+
				
				"   SELECT DISTINCT TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"        NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // 22 added by udara 05-12-2014
				"   A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089	
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " +
				"   WHERE  " +
				"       A.FINANCE_NO = C.FINANCE_NO "+
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429

				" 	ORDER BY FINANCE_NO_SORT ASC,ENT_DATE_1 DESC,ENT_DATE_2 DESC,ENT_DATE_3 DESC "+ //Modified by Kanchana fro issure no 19089 on 2016-03-08
				" ";
			
			//out.println(sql2);
			
			// cancel
			sql3 = " " +
				
				" SELECT  START_DATE, "+
				" DEBIT_NOTE_NO, "+
				" POLICY_NO, "+
				" CLIENT_NAME, "+
				" SUM_INSSURED, "+
				" PREMIUM, "+
				" SRCC_TC, "+
				" TAX_DUE, "+
				" BASIC_PREMIUM, "+
				" BASIC_PREMIUM_COMMISION, "+
				" RCC_TC_COMMISION, "+
				" VAT_ON_TOTAL_COMMISION, "+
				" FINANCE_NO, "+
				" VALUE_DATE, "+
				" INSUR_COM, "+
				" PAYABLE_PREMIUM, "+
				" CLIENT_CODE,  "+
				" REMARKS, "+ // 18
				" P_ACTIVE_STATUS, "+ // 19
				" INSURENCE_DONE_BY, "+ // 20 added by udara 29-09-2014
				" BUSINESS_TYPE, "+ // 21 added by udara 05-12-2014
				" ENT_USER, "+ // 22 added by udara 05-12-2014
				" ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				" ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" FINANCE_NO_SORT, "+
				" ENT_DATE_1, "+
				" ENT_DATE_2, "+
				" ENT_DATE_3  "+  // Added  by Kanchana fro issure no 19089
				" ,VEHICLE_NO "+ // added by udara 20-07-2017
				" FROM ( "+
				
				" SELECT DISTINCT  "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO, "+
				" A.POLICY_NO POLICY_NO, "+
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" NVL(A.SUM_INSSURED,0) SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+	
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER ENT_USER, "+ // 22 added by udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.REF_DEBIT_NOTE_NO  = B.DEBIT_NOTE_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" UNION "+
				
				" SELECT DISTINCT "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO,  "+
				" A.POLICY_NO POLICY_NO,  "+
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" NVL(A.SUM_INSSURED,0) SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+ 
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER  ENT_USER, "+ // 22 added by udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+		  // Added  by Kanchana fro issure no 19089	
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.POLICY_NO = B.POLICY_NO "+
				" AND A.REF_DEBIT_NOTE_NO  IS NULL "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" ) "+
				
				// ===================================================================================================================

				" 	ORDER BY FINANCE_NO_SORT ASC,ENT_DATE_1 DESC,ENT_DATE_2 DESC,ENT_DATE_3 DESC "+ //Modified by Kanchana fro issure no 19089 on 2016-03-08
				" ";
			
			
			// end by udara 04-06-2014
			
			*/
			
			
			
			sql1 = " " +
				"   SELECT  TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"        NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+ 
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // added by 22 udara 05-12-2014
				"   A.ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				"	SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+  // Added  by Kanchana fro issure no 19089
				"   TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+".AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,' ' POLICY_TYPE "+
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " +
				"   WHERE  " +
				"       A.FINANCE_NO = C.FINANCE_NO "+
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				// ===================================================================================================================
				
				//ADDED BY JITHENDRA FOR 28-03-2019
				" UNION "+
				
				"   SELECT  TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"        NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // 22 added by udara 05-12-2014
				"   A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089	
				" ,NVL((SELECT VEHICLE_NO FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE WHERE SECURITY_ID = A.SECURITY_ID ),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+".AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,'SECURITY' POLICY_TYPE "+
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA_SEC A, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " +
				"   WHERE  " +
				"       A.FINANCE_NO = C.FINANCE_NO "+
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				
				" UNION "+
				
				" SELECT  START_DATE, "+
				" DEBIT_NOTE_NO, "+
				" POLICY_NO, "+
				" CLIENT_NAME, "+
				" SUM_INSSURED, "+
				" PREMIUM, "+
				" SRCC_TC, "+
				" TAX_DUE, "+
				" BASIC_PREMIUM, "+
				" BASIC_PREMIUM_COMMISION, "+
				" RCC_TC_COMMISION, "+
				" VAT_ON_TOTAL_COMMISION, "+
				" FINANCE_NO, "+
				" VALUE_DATE, "+
				" INSUR_COM, "+
				" PAYABLE_PREMIUM, "+
				" CLIENT_CODE,  "+
				" REMARKS, "+ // 18
				" P_ACTIVE_STATUS, "+ // 19
				" INSURENCE_DONE_BY, "+ // 20 added by udara 29-09-2014
				" BUSINESS_TYPE, "+ // 21 added by udara 05-12-2014
				" ENT_USER, "+ // 22 added by udara 05-12-2014
				" ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				" ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" FINANCE_NO_SORT,"+
				" ENT_DATE_1, "+
				" ENT_DATE_2, "+
				" ENT_DATE_3 "+
				" ,VEHICLE_NO "+ // added by udara 20-07-2017
				//"  ,'' ADDITIONAL_CHARGE "+
				"  ,'0' ADDITIONAL_CHARGE "+
				" ,POLICY_TYPE "+
				" FROM ( "+
				
				//" SELECT DISTINCT  "+
				" SELECT   "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO, "+
				" A.POLICY_NO POLICY_NO, "+
				"  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" A.SUM_INSSURED SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+	
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER ENT_USER, "+ // added by 22 udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	 // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+".AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,' ' POLICY_TYPE "+
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.REF_DEBIT_NOTE_NO  = B.DEBIT_NOTE_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" UNION "+
				
				//" SELECT DISTINCT "+
				" SELECT  "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO,  "+
				" A.POLICY_NO POLICY_NO,  "+
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" A.SUM_INSSURED SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+ 
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER ENT_USER, "+ // added by 22 udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+ 
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+		  // Added  by Kanchana fro issure no 19089	
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+".AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,' ' POLICY_TYPE "+
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.POLICY_NO = B.POLICY_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				" AND A.REF_DEBIT_NOTE_NO  IS NULL "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" ) "+
				
				// ===================================================================================================================
				" 	ORDER BY FINANCE_NO_SORT ASC,ENT_DATE_1 DESC,ENT_DATE_2 DESC,ENT_DATE_3 DESC "+  // Added  by Kanchana fro issure no 19089
				" ";
			
			// end by udara 28-03-2016
			
			//out.println(sql1);
			
			
			
			// activated
			sql2 = " " +
				"   SELECT  TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"        NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // 22 added by udara 05-12-2014
				"   A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089	
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+".AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,' ' POLICY_TYPE "+
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " +
				"   WHERE  " +
				"       A.FINANCE_NO = C.FINANCE_NO "+
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429

				//ADDED BY JITHENDRA FOR 28-03-2019
				" UNION "+
				
				"   SELECT  TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, " +
				"          A.DEBIT_NOTE_NO DEBIT_NOTE_NO, " +
				"          A.POLICY_NO POLICY_NO, " +
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				"          A.SUM_INSSURED SUM_INSSURED, " +
				"          NVL(A.PREMIUM,0) PREMIUM, " +
				"          (RCC + TC) SRCC_TC, " +
				"          NVL(A.TAX_DUE,0) TAX_DUE, " +
				"          A.BASIC_PREMIUM BASIC_PREMIUM, " +
				"          A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, " +
				"          A.RCC_TC_COMMISION RCC_TC_COMMISION, " +
				"          A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, " +
				"          A.FINANCE_NO FINANCE_NO,"+
				
				"          TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+

				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				"        NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+ // 16 added by udara 23-05-2014
				"   C.CLIENT_CODE CLIENT_CODE, "+
				"   '-' REMARKS, "+ // 18
				"   'PACTIVE' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				"   A.BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				"   A.ENT_USER, "+ // 22 added by udara 05-12-2014
				"   A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   NVL(A.REMARKS,'-') ENT_REMARKS, "+ //Added by Kanchana on 2015-01-06 for issue no 19129
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089	
				" ,NVL((SELECT VEHICLE_NO FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE WHERE SECURITY_ID = A.SECURITY_ID ),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+".AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,'SECURITY' POLICY_TYPE "+
				"   FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA_SEC A, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C  " +
				"   WHERE  " +
				"       A.FINANCE_NO = C.FINANCE_NO "+
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" 	ORDER BY FINANCE_NO_SORT ASC,ENT_DATE_1 DESC,ENT_DATE_2 DESC,ENT_DATE_3 DESC "+ //Modified by Kanchana fro issure no 19089 on 2016-03-08
				" ";
			
			//out.println(sql2);
			
			// cancel
			sql3 = " " +
				
				" SELECT  START_DATE, "+
				" DEBIT_NOTE_NO, "+
				" POLICY_NO, "+
				" CLIENT_NAME, "+
				" SUM_INSSURED, "+
				" PREMIUM, "+
				" SRCC_TC, "+
				" TAX_DUE, "+
				" BASIC_PREMIUM, "+
				" BASIC_PREMIUM_COMMISION, "+
				" RCC_TC_COMMISION, "+
				" VAT_ON_TOTAL_COMMISION, "+
				" FINANCE_NO, "+
				" VALUE_DATE, "+
				" INSUR_COM, "+
				" PAYABLE_PREMIUM, "+
				" CLIENT_CODE,  "+
				" REMARKS, "+ // 18
				" P_ACTIVE_STATUS, "+ // 19
				" INSURENCE_DONE_BY, "+ // 20 added by udara 29-09-2014
				" BUSINESS_TYPE, "+ // 21 added by udara 05-12-2014
				" ENT_USER, "+ // 22 added by udara 05-12-2014
				" ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				" ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" FINANCE_NO_SORT, "+
				" ENT_DATE_1, "+
				" ENT_DATE_2, "+
				" ENT_DATE_3  "+  // Added  by Kanchana fro issure no 19089
				" ,VEHICLE_NO "+ // added by udara 20-07-2017
				//" , '' ADDITIONAL_CHARGE "+
				" , '0' ADDITIONAL_CHARGE "+
				" ,POLICY_TYPE "+
				" FROM ( "+
				
				" SELECT   "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO, "+
				" A.POLICY_NO POLICY_NO, "+
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" NVL(A.SUM_INSSURED,0) SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+	
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER ENT_USER, "+ // 22 added by udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+	  // Added  by Kanchana fro issure no 19089
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+"AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,' ' POLICY_TYPE "+
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.REF_DEBIT_NOTE_NO  = B.DEBIT_NOTE_NO "+
				"   AND    A.FINANCE_NO = B.FINANCE_NO "+
				"   AND    B.FINANCE_NO = C.FINANCE_NO "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" UNION "+
				
				" SELECT  "+
				" TO_CHAR(A.START_DATE, 'DD-MM-YYYY') START_DATE, "+
				" A.DEBIT_NOTE_NO DEBIT_NOTE_NO,  "+
				" A.POLICY_NO POLICY_NO,  "+
				"          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE) CLIENT_NAME, " +
				" NVL(A.SUM_INSSURED,0) SUM_INSSURED, "+
				" NVL(A.PREMIUM,0) PREMIUM, "+
				" (RCC + TC) SRCC_TC, "+
				" NVL(A.TAX_DUE,0) TAX_DUE, "+
				" A.BASIC_PREMIUM BASIC_PREMIUM, "+
				" A.BASIC_PREMIUM_COMMISION BASIC_PREMIUM_COMMISION, "+
				" A.RCC_TC_COMMISION RCC_TC_COMMISION, "+
				" A.VAT_ON_TOTAL_COMMISION VAT_ON_TOTAL_COMMISION, "+
				" A.FINANCE_NO FINANCE_NO, "+
				" TO_CHAR(NVL(A.DUE_DATE,A.ENT_DATE),'DD-MM-YYYY') VALUE_DATE, "+
				" NVL(" + m_schema_name + ".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM),'-') INSUR_COM, "+ // added by udara 27-09-2017
				
				" NVL(A.PAYABLE_PREMIUM,0) PAYABLE_PREMIUM, "+
				"   C.CLIENT_CODE CLIENT_CODE, "+
				" B.REMARKS REMARKS, "+
				" 'PCANCEL' P_ACTIVE_STATUS, "+ // 19
				"   C.INSURANCE_DONE_BY INSURENCE_DONE_BY, "+ 
				" A.BUSINESS_TYPE BUSINESS_TYPE,   "+ // 21 added by udara 05-12-2014
				" A.ENT_USER  ENT_USER, "+ // 22 added by udara 05-12-2014
				" A.ENT_DATE ENT_DATE, "+ //Added by Kanchana fro issure no 19089 on 2016-01-04
				"   '-' ENT_REMARKS, "+ //Added by Kanchana fro issure no 19129 on 2016-01-06
				" SUBSTR(NVL(A.FINANCE_NO,'-'),1,2) FINANCE_NO_SORT,"+  // Added  by Kanchana fro issure no 19089
				" TO_CHAR(A.ENT_DATE,'YYYY') ENT_DATE_1, "+
				" TO_CHAR(A.ENT_DATE,'MM') ENT_DATE_2, "+
				" TO_CHAR(A.ENT_DATE,'DD') ENT_DATE_3  "+		  // Added  by Kanchana fro issure no 19089	
				" ,NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(C.APPLICATION_NO),'-') VEHICLE_NO "+ // added by udara 20-07-2017
				" ,"+m_schema_name+"AF_INSU_GET_ADDITIONAL_CHARGE(A.POLICY_NO) ADDITIONAL_CHARGE "+
				" ,' ' POLICY_TYPE "+
				" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK  A, "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG B, " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C "+
				" WHERE A.POLICY_NO = B.POLICY_NO "+
				" AND A.REF_DEBIT_NOTE_NO  IS NULL "+
				
				"   "+m_insurance_company_string+"   "+ // added by udara 27-09-2017
				"   "+m_finance_no_string+"   "+ // added by udara 27-09-2017
				
				"   AND    TRUNC(A.ENT_DATE) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
				"   AND    TRUNC(A.ENT_DATE) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
				//"   AND A.PREMIUM > 0 "+ // added by udara 01-10-2019
				"   "+m_active_status+"   "+ // added by udara 18-07-2014 
				"   "+m_report_type+"      "+ // added by udara 11-09-2014
				"   "+m_insurance_done_string+"   "+ // added by udara 27-09-2017
				" 	"+m_branch_code_sql+"    "+ // Added by Kanchana for issue no 18429
				
				" ) "+
				
				// ===================================================================================================================

				" 	ORDER BY FINANCE_NO_SORT ASC,ENT_DATE_1 DESC,ENT_DATE_2 DESC,ENT_DATE_3 DESC "+ //Modified by Kanchana fro issure no 19089 on 2016-03-08
				" ";
			
			
			// added by udara 25-08-2014
			if (m_premium_status.equals("ALL")) {
				sql = sql1;
			}
			else if(m_premium_status.equals("PACTIVE")){
				sql = sql2;
			}
			else if(m_premium_status.equals("PCANCEL")){
				sql = sql3;
			}
			// end by udara 25-08-2014
			
			out.println("<!--"+sql+"-->"); 
			//out.println(sql);
			
			rs = stmt.executeQuery(sql);
			boolean more = rs.next();
			int j=1;
			if (!more) {
				out.println("<table align = \"center\" width = \"100%\" class = \"table\" border = \"0\">");
				out.println("   <tr>");
				out.println("       <td width = \"20%\" align = \"center\"><font color = \"red\">No Data Found...!</font></td>");
				out.println("   </tr>");
				out.println("<table>");
			}
			else {
				out.println("<table align = \"center\" width = \"1610\" class = \"table\" border = \"0\">");
				out.println("   <tr style = \"font-weight: bold;\" class = \"pdn_txtpos2\">");
				out.println("       <td width = \"10%\" align = \"center\">No</td>");
				out.println("       <td width = \"10%\" align = \"center\">Contract No</td>"); // 1
				out.println("       <td width = \"10%\" align = \"center\">C/N Date</td>"); // 2
				out.println("       <td width = \"10%\" align = \"center\">Debit Note No.</td>"); // 3
				out.println("       <td width = \"10%\" align = \"center\">Policy No.</td>"); // 4
				out.println("       <td width = \"10%\" align = \"center\">Name of Insured</td>"); // 5
				out.println("       <td width = \"10%\" align = \"center\">Sum Insured</td>"); // 6
				out.println("       <td width = \"10%\" align = \"center\">Total Premium</td>"); // 7
				out.println("       <td width = \"10%\" align = \"center\">SRCC/TC</td>"); // 8
				out.println("       <td width = \"10%\" align = \"center\">Taxes & Others</td>"); // 9
				out.println("       <td width = \"10%\" align = \"center\">Basic Premium</td>"); // 10
				out.println("       <td width = \"10%\" align = \"center\">Commision on Premium</td>"); // 11
				out.println("       <td width = \"10%\" align = \"center\">SRCC/TC Commision</td>"); // 12
				out.println("       <td width = \"10%\" align = \"center\">Total Commision</td>"); // 13
				out.println("       <td width = \"10%\" align = \"center\">VAT Portion</td>"); // 14
				
				out.println("       <td width = \"10%\" align = \"center\">Value Date</td>"); // 15 added by udara 23-05-2014
				out.println("       <td width = \"10%\" align = \"center\">Company</td>"); // 16 added by udara 23-05-2014
				out.println("       <td width = \"10%\" align = \"center\">Payable</td>"); // 17 added by udara 23-05-2014
				out.println("       <td width = \"10%\" align = \"center\">Additional Charge</td>"); // Added By KD on 19-Mar-2019
				out.println("       <td width = \"10%\" align = \"center\">Remarks</td>"); // 19
				out.println("       <td width = \"10%\" align = \"center\">Entered Remarks</td>"); // 20
				out.println("       <td width = \"10%\" align = \"center\">Status</td>"); // 21
				out.println("       <td width = \"10%\" align = \"center\">Insurance Done By</td>"); // 22
				
				out.println("       <td width = \"10%\" align = \"center\">New/Renew Status</td>"); // 23
				out.println("       <td width = \"10%\" align = \"center\">Entered User</td>"); // 24
				out.println("       <td width = \"10%\" align = \"center\">Vehicle No.</td>"); // 25 added by udara 20-07-2017
				out.println("       <td width = \"10%\" align = \"center\">Policy Type(Security/Application).</td>"); // 25 added by udara 20-07-2017
				
				out.println("   </tr>");

				
			}
			
			// added by udara 01-09-2015
			double tot_sum_ins = 0;
			double tot_premium = 0;
			double tot_srcc_tc = 0;
			double tot_tax_other = 0;
			double tot_basic_premium = 0;
			double tot_comm_on_premium = 0;
			double tot_srcc_tc_commission = 0;
			double tot_commission = 0;
			double tot_vat_portion = 0;
			double tot_payable = 0;
			// end by udara 01-09-2015
			
			while (more) {
				if (j % 2 == 1) {
					out.println("<tr class = \"tr_input\">");
				}
				else {
					out.println("<tr class = \"tr_input1\">");
				}
				
				//j = j + 1;
				
				//out.println("<td width = \"10%\" align = \"left\">" + rs.getString("FINANCE_NO") + "</td>"); // 1
				out.println("<td width = \"10%\" align = \"left\">" + j + "</td>"); // added by udara 01-09-2015
				out.println("<td width='10%' class=factoring-letter-body align='left' class=div_input style='cursor:hand' onclick=\"show_transaction_info('" + rs.getString("CLIENT_CODE") + "','" + rs.getString("FINANCE_NO") + "')\" ><u> " + rs.getString("FINANCE_NO") + " </u></td>");
				out.println("<td width = \"10%\" align = \"center\">" + rs.getString("START_DATE") + "</td>"); // 2
				out.println("<td width = \"10%\" align = \"left\">" + rs.getString("DEBIT_NOTE_NO") + "</td>"); // 3
				out.println("<td width = \"10%\" align = \"left\">" + rs.getString("POLICY_NO") + "</td>"); // 4
				out.println("<td width = \"10%\" align = \"left\">" + rs.getString("CLIENT_NAME") + "</td>"); // 5
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("SUM_INSSURED")) + "</td>"); // 6
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("PREMIUM")) + "</td>"); // 7
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("SRCC_TC")) + "</td>"); // 8
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("TAX_DUE")) + "</td>"); // 9
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("BASIC_PREMIUM")) + "</td>"); // 10
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("BASIC_PREMIUM_COMMISION")) + "</td>"); // 11
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("RCC_TC_COMMISION")) + "</td>"); // 12
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("BASIC_PREMIUM_COMMISION").add(rs.getBigDecimal("RCC_TC_COMMISION"))) + "</td>"); // 13
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("VAT_ON_TOTAL_COMMISION")) + "</td>"); // 14
				
				out.println("<td width = \"10%\" align = \"left\">" + rs.getString("VALUE_DATE") + "</td>"); // added by udara 23-05-2014
				out.println("<td width = \"10%\" align = \"left\">" + rs.getString("INSUR_COM") + "</td>"); // added by udara 23-05-2014
				
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("PAYABLE_PREMIUM")) + "</td>"); // added by udara 23-05-2014
				out.println("<td width = \"10%\" align = \"right\">" + nf.format(rs.getBigDecimal("ADDITIONAL_CHARGE")) + "</td>"); // added by Kanishka Dilshan on 19-mar-2019
				
				
				
				out.println("<td width = \"10%\" align = \"left\">" + rs.getString("REMARKS") + "</td>"); // 18
				
				out.println("<td width = \"10%\" align = \"left\">" + rs.getString("ENT_REMARKS") + "</td>"); // 19   Added by Kanchana fro issure no 19129 on 2016-01-06
				//out.println("<td width = \"10%\" align = \"left\">" + rs.getString("P_ACTIVE_STATUS") + "</td>"); // 19 // commented by udara 05-12-2014
				
				// commented by udara 05-12-2014
				if(rs.getString("P_ACTIVE_STATUS").equals("PACTIVE"))
					out.println("<td width = \"10%\" align = \"left\"> Active </td>");
				else if(rs.getString("P_ACTIVE_STATUS").equals("PCANCEL"))
					out.println("<td width = \"10%\" align = \"left\"> Cancel </td>");
				// end by udara 05-12-2014
				
				if(rs.getString("INSURENCE_DONE_BY").equals("LICENSEE"))
					out.println("<td width = \"10%\" align = \"left\"> Company </td>");
				else if(rs.getString("INSURENCE_DONE_BY").equals("CLIENT"))
					out.println("<td width = \"10%\" align = \"left\"> Lessee </td>");
				else
					out.println("<td width = \"10%\" align = \"left\">" + rs.getString("INSURENCE_DONE_BY") + "</td>");
				

				if(rs.getString("BUSINESS_TYPE").equals("NEW"))
					out.println("<td width = \"10%\" align = \"left\"> New </td>");
				else if(rs.getString("BUSINESS_TYPE").equals("RENEWAL"))
					out.println("<td width = \"10%\" align = \"left\"> Renewal </td>");
				
				
				out.println("<td width = \"10%\" align = \"left\"> "+rs.getString("ENT_USER")+" </td>"); // 22
				out.println("<td width = \"10%\" align = \"left\"> "+rs.getString("VEHICLE_NO")+" </td>"); // 23 added vby udara 20-07-2017
				
				if(rs.getString("POLICY_TYPE").equals("SECURITY"))
					out.println("<td width = \"10%\" align = \"left\"> Security Insurance</td>");
				else
					out.println("<td width = \"10%\" align = \"left\"> Application Insurance </td>");
				
				out.println("</tr>");
				
				tot_sum_ins = tot_sum_ins + rs.getDouble("SUM_INSSURED");
				tot_premium = tot_premium + rs.getDouble("PREMIUM");
				tot_srcc_tc = tot_srcc_tc + rs.getDouble("SRCC_TC");
				tot_tax_other = tot_tax_other + rs.getDouble("TAX_DUE");
				tot_basic_premium = tot_basic_premium + rs.getDouble("BASIC_PREMIUM");
				tot_comm_on_premium = tot_comm_on_premium + rs.getDouble("BASIC_PREMIUM_COMMISION");
				tot_srcc_tc_commission = tot_srcc_tc_commission + rs.getDouble("RCC_TC_COMMISION");
				tot_commission = tot_commission + rs.getDouble("BASIC_PREMIUM_COMMISION")+(rs.getDouble("RCC_TC_COMMISION"));
				tot_vat_portion = tot_vat_portion + rs.getDouble("VAT_ON_TOTAL_COMMISION");
				tot_payable = tot_payable + rs.getDouble("PAYABLE_PREMIUM");
				
				more = rs.next();
				j = j + 1;
				
				
				
				
				
				
			}
			
			
			// added by udara 01-09-2015
			out.println("<tr>");
			out.println("<td width = \"10%\" align = \"center\"> &nbsp; </td>"); 
			out.println("<td width = \"10%\" align = \"center\"> &nbsp; </td>"); 
			out.println("<td width = \"10%\" align = \"center\"> &nbsp; </td>"); // 2
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>"); // 3
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>"); // 4
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>"); // 5
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_sum_ins)  + "</b></td>"); // 6
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_premium) + "</b></td>"); // 7
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_srcc_tc) + "</b></td>"); // 8
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_tax_other) + "</b></td>"); // 9
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_basic_premium) + "</b></td>"); // 10
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_comm_on_premium) + "</b></td>"); // 11
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_srcc_tc_commission) + "</b></td>"); // 12
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_commission) + "</b></td>"); // 13
			out.println("<td width = \"10%\" align = \"right\"><b>" + nf.format(tot_vat_portion) + "</b></td>"); // 14
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>"); // added by udara 23-05-2014
			out.println("<td width = \"10%\" align = \"left\"> &nbsp;</td>"); // added by udara 23-05-2014
			out.println("<td width = \"10%\" align = \"right\"> <b>" + nf.format(tot_payable) + "</b></td>"); // added by udara 23-05-2014
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>"); // 18
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>");
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>");
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>");
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>"); // 22
			out.println("<td width = \"10%\" align = \"left\"> &nbsp; </td>"); // 23 added by udara 20-07-2017
			out.println("</tr>");
			// end by udara 01-09-2015
			
			
			
			out.println("</table>"); 
			
			
			
			
			
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</BODY>"); 
			out.println("</html>"); 
			out.flush();
			
			
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}









