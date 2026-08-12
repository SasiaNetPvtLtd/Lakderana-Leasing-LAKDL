import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_FA_RE_sql_validations extends javax.servlet.http.HttpServlet {

	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
	
	public ResultSet rs,rs1;
	public String m_chksql;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	
		try {
		
		//************************************************************	
		LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods ();
		conn = m_sn_methods.met_user_validate(req); 
		String m_schema_name = m_sn_methods.schema_name.trim();
		//**************************************************************					
		nf = java.text.NumberFormat.getInstance(Locale.US);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		
		res.setStatus(HttpServletResponse.SC_OK);
		res.setContentType("text/xml");
		res.setHeader("Cache-Control", "No-Cache");
		res.setDateHeader("Expires", 0);
		
		ServletOutputStream out = res.getOutputStream();
		
		m_chksql=req.getParameter("chksql");
		stmt=conn.createStatement();
		
		
		if (m_chksql.trim().equals("idle")) {
		out.println("idle");
		}	
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_COLLECTION_PROCES_get_receipt")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" REC_NO, "+
		" DECODE(SETTLE_MODE,'CHEQUE',CHEQUE_NO,'CASH','-'), "+
		" DECODE(SETTLE_MODE,'CHEQUE',PAYER_ACC_NO,'CASH','-'), "+
		" DECODE(SETTLE_MODE,'CHEQUE',"+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'CASH','-'), "+
		" NVL(REC_AMOUNT,0.00) "+
		" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
		" WHERE SETTLE_MODE=UPPER('"+m_val+"') "+
		" AND STATUS=('"+m_status+"') ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_deposit_receipts")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" A.RECEIPT_NO, "+
		" NVL(B.CHEQUE_NO,'-'), "+
		" B.PAYER_ACC_NO, "+
		" "+m_schema_name+".AF_CO_GET_BANK_NAME(B.PAYER_BRANCH_CODE) BANK_NAME, "+
		" A.AMOUNT "+
		
		" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
		" WHERE A.RECEIPT_NO=B.REC_NO AND A.DIPOSIT_NO=UPPER('"+m_val+"') AND  A.STATUS=('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_account_no")){
		
		String m_val = req.getParameter("data_val").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" ACCOUNT_NO, "+
		" BRANCH_CODE, "+
		" REFERENCE "+
		"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
		" WHERE ACCOUNT_NO = UPPER('"+m_val+"')  AND ACTIVE_STATUS='Y' "); 	
		
		
		out.print("<DATA>");	
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_receipt_no")){
		
		String m_val = req.getParameter("data_val").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" REC_NO,REC_AMOUNT,CHEQUE_NO "+
		"	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
		" WHERE REC_NO = UPPER('"+m_val+"')  AND STATUS='E' "); 	
		
		
		out.print("<DATA>");	
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		}		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_client_code")){
		
		String m_val = req.getParameter("data_val").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" CLIENT_CODE, "+
		" FULL_NAME, "+
		" ACTIVE_STATUS "+
		"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
		" WHERE CLIENT_CODE = UPPER('"+m_val+"')  AND ACTIVE_STATUS='Y' "); 	
		
		
		out.print("<DATA>");	
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		
		}
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_finance_no")){
		
		String m_val = req.getParameter("data_val").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" FINANCE_NO, "+
		" CLIENT_CODE, "+
		" CURRENCY_CODE "+
		"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO = UPPER('"+m_val+"') AND  APPLICATION_STATUS='ACTIVATED' "); 	
		
		
		out.print("<DATA>");	
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_invoice_no")){
		
		String m_val = req.getParameter("data_val").trim();
		
		rs= stmt.executeQuery (" SELECT A.INVOICE_NO, A.FINANCE_NO, to_char(A.VALUE_DATE,'DD-MM-YYYY'), "+
		"        A.NET_AMOUNT,A.VAT_AMOUNT,A.TOTAL_AMOUNT,to_char(A.DUE_DATE,'DD-MM-YYYY'),A.CLIENT_CODE CLIENT,  "+
		"        A.REMARKS,A.CURRENCY_CODE, A.EXCHANGE_RATE,A.INVOICE_TYPE "+
		" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
		" WHERE  INVOICE_NO=UPPER('"+m_val+"') AND "+
		//"        OR FINANCE_NO LIKE UPPER('"+m_val+"')) AND "+
		"        ACTIVE_STATUS='Y' AND INVOICE_TYPE NOT IN('INV_GENER')"); 	
		
		
		out.print("<DATA>");	
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("<R9>"+rs.getString(9)+"</R9>");
		out.print("<R10>"+rs.getString(10)+"</R10>");
		out.print("<R11>"+rs.getString(11)+"</R11>");
		out.print("<R12>"+rs.getString(12)+"</R12>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		
		}
		
		else if(m_chksql.trim().equals("get_excharate")){
		
		String m_curren_code = req.getParameter("CURR_CODE");
		String m_value_date  = req.getParameter("VAL_DATE");
		
		rs = stmt.executeQuery(" SELECT EXCHANGE_RATE,CURR_CODE, TRN_DATE "+
		" FROM   "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
		" WHERE  CURR_CODE=UPPER('"+m_curren_code+"') AND "+
		"        TRN_DATE =(SELECT MAX(TRN_DATE) "+
		"                  FROM    "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
		"                  WHERE   TRN_DATE<=TO_DATE('"+m_value_date+"','DD-MM-YYYY'))");
		
		boolean flag = rs.next();
		out.println("<Root>");
		for(; flag; flag = rs.next())				{
		out.println("<ITEM>");
		out.println("<EXR>"     + rs.getString(1)  + "</EXR>");
		out.println("<CUR>"     + rs.getString(2)  + "</CUR>");
		out.println("<TRD>"     + rs.getString(3)  + "</TRD>");
		out.println("</ITEM>");
		}	
		out.println("</Root>");
		}		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_return_realization")){
		
		String m_val = req.getParameter("data_val").trim();
		
		rs= stmt.executeQuery ("SELECT UPPER(REC_NO),"+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO),TO_CHAR(BANK_DATE,'DD-MM-YYYY'),SETTLE_MODE,RECON_STATUS, "+
		"REALISED_DATE,UPPER(CHEQUE_NO),REC_AMOUNT_CURR "+
		"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
		"WHERE STATUS='B' AND REALISED_DATE IS NULL AND REC_NO IN "+
		"(SELECT RECEIPT_NO "+
		"FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
		"WHERE DIPOSIT_NO IN "+
		"(SELECT DIPOSIT_NO "+
		"FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
		"WHERE   ACC_NO=UPPER('"+m_val+"'))) ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_return_realization_del")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_real_date_from = req.getParameter("data_val2").trim();
		String m_real_date_to = req.getParameter("data_val3").trim();
		
		rs= stmt.executeQuery ("SELECT UPPER(REC_NO),"+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO),TO_CHAR(BANK_DATE,'DD-MM-YYYY'),SETTLE_MODE,RECON_STATUS, "+
		"TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),UPPER(CHEQUE_NO),REC_AMOUNT_CURR "+
		"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
		"WHERE RECON_STATUS='Y' AND  "+
		" TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_real_date_from+"','DD-MM-YYYY') AND "+
		" TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_real_date_to+"','DD-MM-YYYY')  AND "+
		" REC_NO IN "+
		"(SELECT RECEIPT_NO "+
		"FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
		"WHERE DIPOSIT_NO IN "+
		"(SELECT DIPOSIT_NO "+
		"FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
		"WHERE   ACC_NO=UPPER('"+m_val+"'))) ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_return_realization2")){
		
		String m_val = req.getParameter("data_val").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" ACC_NO,"+
		" BRANCH_CODE,"+	
		" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+	
		" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME "+		
		" FROM LAKDL.AF_CO_MAS_LICENCEE_SETTLEMENT "+
		" WHERE UPPER(ACC_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('Y')");										
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_val_deposit_code")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		
		
		rs= stmt.executeQuery (" SELECT "+
		" DIPOSIT_NO, "+
		" TO_CHAR(DIPOSIT_DATE,'DD-MM-YYYY') DIPOSIT_DATE, "+
		" "+m_schema_name+".AF_CO_GET_SETTLE_MODE(DIPOSIT_NO) SETTLE_MODE, "+
		" NVL(ACC_NO,'-'), "+
		" NVL(BRANCH_CODE,'-'), "+
		" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
		" NVL(REFERENCE,'-') "+
		" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
		" WHERE DIPOSIT_NO=UPPER('"+m_val+"')  AND  STATUS=('"+m_status+"') ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_val_account_code")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" ACC_NO, "+
		" NVL(BRANCH_CODE,'-'), "+
		" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
		" NVL(ACC_SYS_REFNO,'-') "+
		" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
		" WHERE ACC_NO=UPPER('"+m_val+"') AND  ACTIVE_STATUS=('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_temp_rec_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery ( " SELECT "+
		" TEMP_REC_NO, "+
		" FINANCE_NO, "+
		" CLIENT_CODE, "+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
		" TO_CHAR(TRN_DATE,'DD-MM-YYYY'), "+
		" AMOUNT, "+
		" SETTELMENT_MODE, "+
		" BANK_CODE, "+
		" BRANCH_CODE, "+
		" ACCOUNT_NO, "+
		" CURR_CODE, "+
		" EXCHANGE_RATE, "+
		" TRN_AMOUNT_CURR, "+
		" COLLECTION_OFFICER, "+
		" RECEIPT_NO, "+
		" REC_BOOK_NO, "+
		" CHEQUE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT "+
		" WHERE TEMP_REC_NO = UPPER('"+m_val+"')  AND STATUS=('"+m_status+"') ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("<R9>"+rs.getString(9)+"</R9>");
		out.print("<R10>"+rs.getString(10)+"</R10>");
		out.print("<R11>"+rs.getString(11)+"</R11>");
		out.print("<R12>"+rs.getString(12)+"</R12>");
		out.print("<R13>"+rs.getString(13)+"</R13>");
		out.print("<R14>"+rs.getString(14)+"</R14>");
		out.print("<R15>"+rs.getString(15)+"</R15>");
		out.print("<R16>"+rs.getString(16)+"</R16>");
		out.print("<R17>"+rs.getString(17)+"</R17>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_legal_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery ( " SELECT "+
		" A.LEGAL_NO, "+
		" A.FINANCE_NO, "+
		" A.CLIENT_CODE, "+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ 
		" A.LAWYER_CODE, "+
		" B.NAME_WITH_INITIALS, "+	
		" A.LEGAL_TYPE, "+
		" A.LEGAL_POSITION, "+
		" A.REMARKS, "+
		" TO_CHAR(A.COURT_DATE,'DD-MM-YYYY') COURT_DATE, "+
		" A.AMOUNT_DUE "+
		" FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIVITIES A ,"+m_schema_name+".AF_CO_MAS_LAWYER B "+
		" WHERE A.LAWYER_CODE=B.LAWYER_CODE  "+
		" AND (UPPER(A.LEGAL_NO) = UPPER('"+m_val+"')  "+
		" OR UPPER(A.FINANCE_NO) = UPPER('"+m_val+"')  "+
		" OR UPPER(A.CLIENT_CODE) = UPPER('"+m_val+"') "+
		" OR UPPER(A.LAWYER_CODE) = UPPER('"+m_val+"') "+
		" )AND A.ACTIVE_STATUS=('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("<R9>"+rs.getString(9)+"</R9>");
		out.print("<R10>"+rs.getString(10)+"</R10>");
		out.print("<R11>"+rs.getString(11)+"</R11>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_client_code")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery (" SELECT CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2  "+
		" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
		" WHERE UPPER(CLIENT_CODE) =UPPER('"+m_val+"') OR "+
		" (UPPER(FULL_NAME) =UPPER('"+m_val+"') OR "+
		" UPPER(ADDRESS1)   =UPPER('"+m_val+"') OR "+
		" UPPER(CITY_CODE)  =UPPER('"+m_val+"') OR "+
		" UPPER(MOBILE_NO)  =UPPER('"+m_val+"') OR "+
		" UPPER(TEL_NO)     =UPPER('"+m_val+"') OR "+
		" UPPER(EMAIL)      =UPPER('"+m_val+"') OR "+
		" UPPER(NIC_NO)     =UPPER('"+m_val+"') OR "+
		" UPPER(BUSINESS_CERTIFICATE_NO) =UPPER('"+m_val+"') AND "+
		" ACTIVE_STATUS=('"+m_status+"') "+
		" 	) "+
		" ORDER BY FULL_NAME  ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code")){
		
		String m_val = req.getParameter("data_val");
		
		String m_status = req.getParameter("ac_status");
		
		rs= stmt.executeQuery ("SELECT BRANCH_CODE,BRANCH_NAME,BANK_CODE,ADDRESS1,NVL(ADDRESS2,'N/A'),NVL(CITY_CODE,'N/A'),NVL(TEL_NO,'N/A'),NVL(FAX_NO,'N/A'),DAYS_TO_REALISE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_BANK_BRANCH "+
		" WHERE (UPPER(BRANCH_CODE)=UPPER('"+m_val+"') OR UPPER(BRANCH_NAME)=UPPER('"+m_val+"'))  AND ACTIVE_STATUS='"+m_status+"' ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("<R9>"+rs.getString(9)+"</R9>");
		out.print("<R10>"+rs.getString(10)+"</R10>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Receipt_licencee_settlement")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status");	
		
		rs= stmt.executeQuery (	" SELECT "+
		" A.ACC_NO ACC_NO, "+
		" B.BRANCH_CODE BRANCH_CODE, "+
		" NVL(B.BANK_CODE,'-') BANK_CODE "+
		" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT A, "+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
		" WHERE A.BRANCH_CODE=B.BRANCH_CODE AND A.ACC_NO=UPPER('"+m_val+"') AND A.ACTIVE_STATUS=('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Curr_code")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status");
		
		rs= stmt.executeQuery ("SELECT CURR_CODE,CURR_SYMBOL,REP_CURR,TO_CHAR(TRN_DATE,'DD-MM-YYYY'),DEFAULT_VALUE"+
		" FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
		" WHERE UPPER(CURR_CODE)=UPPER('"+m_val+"') "+
		" AND ACTIVE_STATUS=UPPER('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_User")){
		
		String m_val = req.getParameter("data_val").trim();
		
		
		rs= stmt.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE, USER_TYPE, EMP_ID,"+
		" DIVISION_CODE, DESIGNATION_CODE,PASSWORD FROM "+m_schema_name+".CO_CO_MAS_USER "+
		" WHERE (UPPER(USER_ID)=UPPER('"+m_val+"') OR UPPER(NAME)=UPPER('"+m_val+"')) ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Receipt_Boook_No")){
		
		String m_val = req.getParameter("data_val").trim();
		
		
		rs= stmt.executeQuery ("SELECT "+
		" BOOK_STATUS "+
		" FROM "+m_schema_name+".AF_RE_PRO_COLLECTION_RECEIPT "+
		" WHERE RECEIPT_BOOK_NO=UPPER('"+m_val+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Last_Receipt_No")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		
		rs= stmt.executeQuery (	 " SELECT "+
		" NVL(MAX(RECEIPT_NO),'-') RECEIPT_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT "+
		" WHERE COLLECTION_OFFICER=UPPER('"+m_val+"') AND REC_BOOK_NO=UPPER('"+m_val2+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Finance_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		String m_status2 = req.getParameter("ac_status2").trim();
		
		rs= stmt.executeQuery (	" SELECT "+
		" NVL(FINANCE_NO,'-') FINANCE_NO, "+
		" NVL(CLIENT_CODE,'-') CLIENT_CODE ,"+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO=UPPER('"+m_val+"') "+
		" AND APPLICATION_STATUS IN ('"+m_status+"','"+m_status2+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_activities_Finance_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (	" SELECT "+
		" NVL(FINANCE_NO,'-') FINANCE_NO, "+
		" NVL(CLIENT_CODE,'-') CLIENT_CODE ,"+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
		"APPLICATION_STATUS "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO=UPPER('"+m_val+"') "+
		" AND APPLICATION_STATUS=('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_activities_Lawyer_code")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status");	
		
		rs= stmt.executeQuery ("SELECT LAWYER_CODE,FIRST_NAME,LAST_NAME,NAME_WITH_INITIALS FROM "+m_schema_name+".AF_CO_MAS_LAWYER"+
		" WHERE (UPPER(LAWYER_CODE)=UPPER('"+m_val+"') OR UPPER(FIRST_NAME)=UPPER('"+m_val+"') OR UPPER(LAST_NAME)=UPPER('"+m_val+"') ) AND ACTIVE_STATUS='"+m_status+"' ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease")){
		
		String m_status = req.getParameter("ac_status").trim();
		String m_column = req.getParameter("sort_column").trim();
		String m_type = req.getParameter("order_by_type").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" A.APPLICATION_NO APPLICATION_NO,"+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+
		" NVL(B.CITY_CODE,'-') CITY_CODE, "+
		" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
		" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
		" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
		" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
		" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"') "+
		" AND A.COLLECTION_OFFICER IS NULL "+
		" ORDER BY "+m_column+" "+m_type+" ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
		out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no")){
		
		String m_finanace_no = req.getParameter("finanace_no").trim();
		String m_status = req.getParameter("ac_status").trim();
		String m_column = req.getParameter("sort_column").trim();
		String m_type = req.getParameter("order_by_type").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" A.APPLICATION_NO APPLICATION_NO,"+
		" NVL(B.FULL_NAME,'-') CLIENT_NAME, "+
		" NVL(B.CITY_CODE,'-') CITY_CODE, "+
		" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
		" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
		" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
		" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
		" NVL(A.COLLECTION_OFFICER,'-') COLLECTION_OFFICER "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
		" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"')  "+
		" AND (UPPER(A.FINANCE_NO)=UPPER('"+m_finanace_no+"') OR UPPER(B.FULL_NAME)=UPPER('"+m_finanace_no+"') ) "+
		" ORDER BY "+m_column+" "+m_type+" ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
		out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Due_Status")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		String m_column = req.getParameter("sort_column").trim();
		String m_type = req.getParameter("order_by_type").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" INVOICE_NO, "+
		" FINANCE_NO, "+
		" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY') INVOICE_DATE, "+
		" NO_OF_DAYS_DUE, "+
		" AMOUNT_DUE, "+
		" COLLECTION_OFFICER, "+
		" TO_CHAR(TRN_DATE,'DD-MM-YYYY') TRN_DATE , "+
		" RECEIPT_NOT_ALLO "+
		" FROM LAKDL.AF_RE_PRO_RPT_COLLECTION_DUE "+
		" WHERE CLIENT_CODE=UPPER('"+m_val+"') AND ADD_MONTHS(TRN_DATE,2)>SYSDATE "+
		" ORDER BY "+m_column+" "+m_type+" ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		
		rs= stmt.executeQuery (" SELECT "+
		" REPOSSESSION_NO, "+
		" FINANCE_NO, "+
		" SEIZER_CODE "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE UPPER(REPOSSESSION_NO) =UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_customer_Data")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		
		rs= stmt.executeQuery (" SELECT "+
		" NVL(CLIENT_CODE,'-') CLIENT_CODE, "+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) "+	
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE  UPPER(FINANCE_NO)=UPPER('"+m_val+"') ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_vehicle_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		String m_status = req.getParameter("ac_status").trim();
		String m_status2 = req.getParameter("ac_status2").trim();
		
		
		
		rs= stmt.executeQuery (" SELECT "+
		" DISTINCT VEHICLE_NO, "+
		" ENGINE_NO, "+
		" CHASSIS_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO =( "+
		" SELECT "+
		" APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO =(SELECT FINANCE_NO FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  WHERE UPPER(SEIZER_CODE)=UPPER('"+m_val2+"'))   "+
		" AND APPLICATION_STATUS IN ('"+m_status+"','"+m_status2+"') ) AND UPPER(VEHICLE_NO) =UPPER('"+m_val+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_vehicle_no_edit")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" DISTINCT VEHICLE_NO, "+
		" ENGINE_NO, "+
		" CHASSIS_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  "+
		" VEHICLE_NO IN ( "+
		" SELECT "+
		" VEHICLE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE UPPER(INVENTORY_NO)=UPPER('"+m_val+"') AND  ACTIVE_STATUS=('"+m_status+"')) ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_data")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" ASSET_DESCRIPTION, "+
		" MILEAGE, "+
		" COMMENTS, "+
		" KEY, "+
		" LICENSE, "+
		" INSURANCE, "+ 
		" VEHICLE_ID_CARD, "+
		" CASSETTE, "+
		" RADIO, "+
		" CD_PLAYER, "+
		" TOOL_KIT, "+
		" SPEAR_WHEEL, "+
		" JACK, "+
		" LIGHTER, "+
		" FUEL_CAP, "+
		" CARPETS, "+
		" WHEEL, "+
		" BODY, "+
		" MIRROR, "+
		" LEFT_SIDE_MIRROR, "+
		" RIGHT_SIDE_MIRROR, "+
		" LEFT_SIGNAL_LIGHT_FRONT, "+
		" RIGHT_SIGNAL_LIGHT_FRONT, "+
		" LEFT_SIGNAL_LIGHT_REAR, "+
		" RIGHT_SIGNAL_LIGHT_REAR, "+
		" POLICE_REPORT, "+
		" CUSTOMERS_SIGNATURE, "+
		" SEIZERS_SIGNATURE, "+
		" RECEIVERS_SIGNATURE, "+
		" YARD_CODE "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_val+"') "+ 
		" AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')"+
		" AND ACTIVE_STATUS=('"+m_status+"') ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("<R9>"+rs.getString(9)+"</R9>");
		out.print("<R10>"+rs.getString(10)+"</R10>");
		out.print("<R11>"+rs.getString(11)+"</R11>");
		out.print("<R12>"+rs.getString(12)+"</R12>");
		out.print("<R13>"+rs.getString(13)+"</R13>");
		out.print("<R14>"+rs.getString(14)+"</R14>");
		out.print("<R15>"+rs.getString(15)+"</R15>");
		out.print("<R16>"+rs.getString(16)+"</R16>");
		out.print("<R17>"+rs.getString(17)+"</R17>");
		out.print("<R18>"+rs.getString(18)+"</R18>");
		out.print("<R19>"+rs.getString(19)+"</R19>");
		out.print("<R20>"+rs.getString(20)+"</R20>");
		out.print("<R21>"+rs.getString(21)+"</R21>");
		out.print("<R22>"+rs.getString(22)+"</R22>");
		out.print("<R23>"+rs.getString(23)+"</R23>");
		out.print("<R24>"+rs.getString(24)+"</R24>");
		out.print("<R25>"+rs.getString(25)+"</R25>");
		out.print("<R26>"+rs.getString(26)+"</R26>");
		out.print("<R27>"+rs.getString(27)+"</R27>");
		out.print("<R28>"+rs.getString(28)+"</R28>");
		out.print("<R29>"+rs.getString(29)+"</R29>");
		out.print("<R30>"+rs.getString(30)+"</R30>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_repossission_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		
		rs= stmt.executeQuery (" SELECT "+
		" REPOSSESSION_NO, "+
		" SEIZER_CODE, "+
		" FINANCE_NO ,"+
		" INVENTORY_NO  "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_repossission_no_edit")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		
		rs= stmt.executeQuery (" SELECT "+
		" DISTINCT A.REPOSSESSION_NO, "+
		" A.SEIZER_CODE,  "+
		" B.FINANCE_NO,  "+
		" A.INVENTORY_NO  "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
		" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO)=UPPER('"+m_val+"')  AND A.ACTIVE_STATUS=('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_validate_finace_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" A.FINANCE_NO FINANCE_NO,"+
		" NVL(A.CLIENT_CODE,'-') CITY_CODE, "+
		" NVL(B.FULL_NAME,'-') CLIENT_NAME "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
		" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"') AND A.COLLECTION_OFFICER IS NOT NULL "+
		" AND (UPPER(A.FINANCE_NO)=UPPER('"+m_val+"') OR UPPER(B.FULL_NAME)=UPPER('"+m_val+"') ) ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_Yard_code")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		
		" YARD_CODE ,"+
		" NAME  "+			
		" FROM "+m_schema_name+".AF_CO_MAS_YARD "+
		" WHERE (YARD_CODE =UPPER('"+m_val+"') OR UPPER(NAME)=UPPER('"+m_val+"'))  AND ACTIVE_STATUS=('"+m_status+"') ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_Inv_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" DISTINCT INVENTORY_NO  "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY  "+
		" WHERE UPPER(INVENTORY_NO) =UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') AND COMPLETED_OFFER_NO IS NULL ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_Veh_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		String m_status = req.getParameter("ac_status").trim();
		String m_status2 = req.getParameter("ac_status2").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" DISTINCT VEHICLE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val2+"') AND ACTIVE_STATUS=UPPER('"+m_status+"')  "+
		" AND (KEY=('"+m_status2+"') AND INSURANCE=('"+m_status2+"') AND VEHICLE_ID_CARD=('"+m_status2+"')) AND UPPER(VEHICLE_NO) = UPPER('"+m_val+"') ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_advertisment_data")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" ADVER_NO, "+
		" INVENTORY_NO, "+
		" VEHICLE_NO, "+
		" TO_CHAR(ADVER_DATE,'DD-MM-YYYY') ADVER_DATE, "+
		" AMOUNT, "+
		" VAT_AMOUNT, "+
		" TOTAL_AMOUNT "+
		" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
		" WHERE ADVER_NO=UPPER('"+m_val+"')  AND STATUS=UPPER('"+m_status+"')  ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
		out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
		out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" OFFER_NO, "+
		" FULL_NAME, "+
		" ADDRESS, "+
		" TEL_NO, "+
		" AMOUNT "+
		" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS "+
		" WHERE UPPER(ADVER_NO) =UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') ORDER BY AMOUNT DESC  ");
		
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offer_Issue_validation")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		//String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" ADVETIST_NO, "+
		" INVENTORY_NO, "+
		" VEHICLE_NO, "+
		" NVL(OUTSTANDING_VALUE,0) OUTSTANDING_VALUE, "+
		" NVL(OUTSTANDING_INVOICE_VAL,0) OUTSTANDING_INVOICE_VAL, "+
		" NVL(TOTAL_OUTSTANDING_VAL,0) TOTAL_OUTSTANDING_VAL, "+
		" RELEASE_TYPE "+
		" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+
		" WHERE UPPER(ADVETIST_NO)=UPPER('"+m_val+"') AND UPPER(INVENTORY_NO) =UPPER('"+m_val2+"') ");	
		
		
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
		out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
		out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers_outstanding")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		
		
		rs= stmt.executeQuery (" SELECT SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  "+
		" FROM( "+
		
		" SELECT "+
		" SUM(TOTAL_AMOUNT) TOTAL_AMOUNT "+
		" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') "+ 
		
		
		" UNION  "+
		
		" SELECT "+
		" INVOICE_AMOUNT TOTAL_AMOUNT "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO=( "+
		" SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"')  "+ 
		" ) "+
		
		" UNION "+
		
		
		" SELECT TBL_RE.REMANING_PERIOD*GRENTAL_AMOUNT  TOTAL_AMOUNT "+
		" FROM "+
		" ( "+
		" SELECT (A.PERIOD - B.INSTALMENT_NO) REMANING_PERIOD "+
		" FROM "+
		
		" (SELECT "+
		" PERIOD "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
		" WHERE PRICING_NO ="+
		" ( "+
		" SELECT "+
		" PRICING_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
		" SELECT APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO= "+
		" (SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ 
		" )) "+ 
		" )) A, "+
		
		" (SELECT "+
		" MAX(INSTALLMENT_NO) INSTALMENT_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		" WHERE  PRICING_NO= "+
		" ( "+
		" SELECT "+
		" PRICING_NO  "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
		" SELECT APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO= "+
		" (SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		
		" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		" )) B "+
		" ) TBL_RE, "+
		
		" (SELECT "+
		" DISTINCT GRENTAL_AMOUNT "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		" WHERE  PRICING_NO= "+
		" ( "+
		" SELECT "+
		" PRICING_NO  "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
		" SELECT APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO= "+
		" (SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION"+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		
		" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		" )) TBL_GEN "+
		" ) ");
		
		
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers_outstanding_values")){
		
		String m_val = req.getParameter("data_val").trim();
		
		
		
		rs= stmt.executeQuery (" SELECT A.TOTAL_AMOUNT,B.TOTAL_AMOUNT  "+
		" FROM( "+
		" SELECT "+
		" SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  " +
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE APPLICATION_NO=( "+
		" SELECT APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO= "+
		" (SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ 
		" )) "+
		
		" ) A, "+
		
		
		" (SELECT TBL_RE.REMANING_PERIOD*GRENTAL_AMOUNT TOTAL_AMOUNT "+
		" FROM "+
		" ( "+
		" SELECT (A.PERIOD - B.INSTALMENT_NO) REMANING_PERIOD "+
		" FROM "+
		
		" (SELECT "+
		" DISTINCT PERIOD "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
		" WHERE PRICING_NO ="+
		" ( "+
		" SELECT "+
		" PRICING_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
		" SELECT APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO= "+
		" (SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		" )) A, "+
		
		" (SELECT "+
		" DISTINCT MAX(INSTALLMENT_NO) INSTALMENT_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		" WHERE  PRICING_NO= "+
		" ( "+
		" SELECT "+
		" PRICING_NO  "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
		" SELECT APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO= "+
		" (SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		
		" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		" )) B "+
		" ) TBL_RE, "+
		
		" (SELECT "+
		" DISTINCT GRENTAL_AMOUNT "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		" WHERE  PRICING_NO= "+
		" ( "+
		" SELECT "+
		" PRICING_NO  "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
		" SELECT APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO= "+
		" (SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION"+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
		" REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		
		" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
		" )) TBL_GEN "+
		" )B ");//)
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");
		out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Vehicle_inventory_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" DISTINCT A.INVENTORY_NO,  "+
		" A.SEIZER_CODE,  "+
		" B.FINANCE_NO  "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
		" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO)=UPPER('"+m_val+"')  AND UPPER(A.INVENTORY_NO)=UPPER('"+m_val2+"')  AND A.ACTIVE_STATUS=('"+m_status+"') ");
		
		
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Offer_Invoice_View_Letter")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		
		
		
		rs= stmt.executeQuery (" SELECT "+
		" DISTINCT ADVETIST_NO "+
		" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+
		" WHERE ADVETIST_NO =UPPER('"+m_val+"') AND RELEASE_TYPE=UPPER('"+m_val2+"') AND ACTIVE_STATUS=UPPER('"+m_status+"') ");
		
		
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_Actions_Legal_no")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+
		" A.LEGAL_NO, "+
		" A.FINANCE_NO, "+
		" A.CLIENT_CODE, "+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ 
		" TO_CHAR(A.COURT_NEXT_DATE,'DD-MM-YYYY') COURT_DATE "+
		" FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIVITIES A ,"+m_schema_name+".AF_CO_MAS_LAWYER B "+
		" WHERE A.LAWYER_CODE=B.LAWYER_CODE AND (UPPER(A.LEGAL_NO)=UPPER('"+m_val+"') OR UPPER(A.FINANCE_NO)=UPPER('"+m_val+"') OR UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR UPPER(A.LAWYER_CODE)=UPPER('"+m_val+"')) AND A.ACTIVE_STATUS=('"+m_status+"') ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Vehicle_inventory_valuation")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status").trim();
		
		
		rs= stmt.executeQuery (" SELECT "+	
		" APPLICATION_NO, "+
		" ASSET_ID, "+
		" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,   "+
		" MODEL_CODE, "+
		" SUB_MODEL_CODE, "+
		" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE "+
		" FROM   "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS    "+
		" WHERE ASSET_ID=(SELECT "+
		" ASSET_ID "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE APPLICATION_NO=( "+
		" SELECT "+
		" APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
		" WHERE FINANCE_NO=(SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+ 
		" WHERE REPOSSESSION_NO=(SELECT "+
		" DISTINCT   REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') "+
		" )))AND VEHICLE_NO=(SELECT "+
		" VEHICLE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ) "+
		" ) "+
		" AND "+
		" APPLICATION_NO =(SELECT "+
		" APPLICATION_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO=(SELECT "+
		" FINANCE_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO=(SELECT "+
		" DISTINCT   REPOSSESSION_NO "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') "+
		" ))) ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inspection_and_valuation_report")){
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status");	
		
		rs= stmt.executeQuery ("SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS,"+
		" TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING,"+
		" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR,FILED_CODE,B.STATUS,REMARK,GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	"+
		" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
		" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,APPLICATION_NO,INVENTORY_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET B"+
		" WHERE A.VALUATION_NO=B.VALUATION_NO AND A.VALUATION_NO=UPPER('"+m_val+"')"+
		" AND A.ASSET_ID=B.ASSET_ID AND ACTIVE_STATUS=('"+m_status+"') AND A.INVENTORY_NO IS NOT NULL ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+rs.getString(5)+"</R5>");
		out.print("<R6>"+rs.getString(6)+"</R6>");
		out.print("<R7>"+rs.getString(7)+"</R7>");
		out.print("<R8>"+rs.getString(8)+"</R8>");
		out.print("<R9>"+rs.getString(9)+"</R9>");
		out.print("<R10>"+rs.getString(10)+"</R10>");
		out.print("<R11>"+rs.getString(11)+"</R11>");
		out.print("<R12>"+rs.getString(12)+"</R12>");
		out.print("<R13>"+rs.getString(13)+"</R13>");
		out.print("<R14>"+rs.getString(14)+"</R14>");
		out.print("<R15>"+rs.getString(15)+"</R15>");
		out.print("<R16>"+rs.getString(16)+"</R16>");
		out.print("<R17>"+rs.getString(17)+"</R17>");
		out.print("<R18>"+rs.getString(18)+"</R18>");
		out.print("<R19>"+rs.getString(19)+"</R19>");
		out.print("<R20>"+rs.getString(20)+"</R20>");
		out.print("<R21>"+rs.getString(21)+"</R21>");
		out.print("<R22>"+rs.getString(22)+"</R22>");
		out.print("<R23>"+rs.getString(23)+"</R23>");
		out.print("<R24>"+rs.getString(24)+"</R24>");
		out.print("<R25>"+rs.getString(25)+"</R25>");
		
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement_new")){
		
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status");	
		
		rs= stmt.executeQuery ("SELECT ACC_NO FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH A,"+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT B"+
		" WHERE UPPER(A.BRANCH_CODE)=UPPER(B.BRANCH_CODE) AND UPPER(ACC_NO)=UPPER('"+m_val+"') AND B.ACTIVE_STATUS=('"+m_status+"') ");
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		}		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_finance_no")){
		
		
		String m_val = req.getParameter("data_val").trim();
		String m_status = req.getParameter("ac_status");	
		
		rs= stmt.executeQuery ("SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE UPPER(FINANCE_NO)=UPPER('"+m_val+"') AND APPLICATION_STATUS=('"+m_status+"') ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		}		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Offer_Issues_Offer_Numbers")){
		
		
		String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
		
		rs= stmt.executeQuery (" SELECT "+
		" OFFER_NO, "+
		" OFFER_FULL_NAME, "+ 
		" OFFER_ADDRESS, "+
		" OFFER_TEL_NO, "+
		" OFFER_VALUE "+
		" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+ 
		" WHERE  UPPER(ADVETIST_NO)=UPPER('"+m_val+"')  AND "+
		" UPPER(INVENTORY_NO)=UPPER('"+m_val2+"') ");
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+rs.getString(1)+"</R1>");
		out.print("<R2>"+rs.getString(2)+"</R2>");
		out.print("<R3>"+rs.getString(3)+"</R3>");
		out.print("<R4>"+rs.getString(4)+"</R4>");
		out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		}		
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_due_value")){
		
		String m_val = req.getParameter("data_val").trim();
		
		
		rs= stmt.executeQuery (" SELECT SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  "+
		"	 FROM(  "+
		
		"	 SELECT  "+
		"	 SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  "+
		"	 FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL  "+
		"	 WHERE INVENTORY_NO IN( "+                  
		"	     SELECT DISTINCT INVENTORY_NO "+
		"	     FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
		"	     WHERE  REPOSSESSION_NO IN( "+
		"	     SELECT DISTINCT REPOSSESSION_NO "+
		"	     FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  "+
		"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
		"	     )) AND VEHICLE_NO IN( "+
		"	     SELECT VEHICLE_NO "+
		"	     FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		"	     WHERE APPLICATION_NO IN(     "+
		"	     SELECT "+
		"	     DISTINCT APPLICATION_NO "+
		"	     FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		"	     WHERE FINANCE_NO=UPPER('"+m_val+"') "+ 
		"	     )) "+
		
		
		"	 UNION   "+
		
		"	 SELECT  "+
		"	 INVOICE_AMOUNT TOTAL_AMOUNT  "+
		"	 FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  "+
		"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
		
		"	 UNION  "+
		
		"	 SELECT TBL_RE.REMANING_PERIOD*GRENTAL_AMOUNT  TOTAL_AMOUNT  "+
		"	 FROM  "+
		"	 (  "+
		"	 SELECT (A.PERIOD - B.INSTALMENT_NO) REMANING_PERIOD  "+
		"	 FROM  "+
		
		"	 (SELECT  "+
		"	 DISTINCT PERIOD  "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING  "+ 
		"	 WHERE PRICING_NO =(  "+ 
		"	 SELECT  "+
		"	 DISTINCT PRICING_NO  "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
		"	 WHERE  APPLICATION_NO=(  "+
		"	 SELECT DISTINCT APPLICATION_NO  "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
		"	 WHERE FINANCE_NO=UPPER('"+m_val+"')  "+
		"	 ))) A,  "+
		
		"	 (SELECT  "+
		"	 MAX(INSTALLMENT_NO) INSTALMENT_NO  "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
		"	 WHERE  PRICING_NO=  (  "+ 
		"	 SELECT  "+
		"	 DISTINCT PRICING_NO   "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
		"	 WHERE  APPLICATION_NO=(  "+
		"	 SELECT DISTINCT APPLICATION_NO  "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+ 
		"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
		"	 ))) B  "+ 
		"	 ) TBL_RE,  "+
		
		"	 (SELECT  "+
		"	 DISTINCT GRENTAL_AMOUNT  "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		"	 WHERE  PRICING_NO= (  "+ 
		"	 SELECT  "+
		"	 DISTINCT PRICING_NO   "+ 
		"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
		"	 WHERE  APPLICATION_NO=(  "+
		"	 SELECT DISTINCT APPLICATION_NO  "+
		"	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
		"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
		"	 ))) TBL_GEN  "+
		"	 )  ");
		
		
		
		out.print("<DATA>");
		while(rs.next()){
		out.print("<ITEM>");
		out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");
		out.print("</ITEM>");
		}
		out.print("</DATA>");
		
		}
		
		else {
		out.println("Undefined");
		}
		
		out.close();
		conn.close();
		this.destroy();
		
		
	}
	catch (Exception e) {
	try {
	conn.close();
	}catch (Exception eti) {}
	
	ByteArrayOutputStream ostr = new ByteArrayOutputStream();
	e.printStackTrace(new PrintWriter(ostr));
	
	ServletOutputStream out = res.getOutputStream();
	out.println(ostr.toString());
	out.close();
	
	}
	}
}


