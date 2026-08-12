import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
// DEVELOP BY : UDARA FOR OFSCL FACTORING    DATE:19-01-2012

public class LAKDL_FA_OP_Current_Account_Statement_new extends javax.servlet.http.HttpServlet {
	
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
			LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_class_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control","No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			m_chksql=req.getParameter("chksql");
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if (m_chksql.trim().equals("current_account_statement")){

				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				String m_today="";
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				rs1.close();
				
				double m_tax=0;
				rs1 = stmt1.executeQuery(" SELECT NVL(TAX_AMOUNT,0) FROM "+m_schema_name+".FA_OP_PRO_TAX ");
				if(rs1.next()){
					m_tax=rs1.getDouble(1);
				}
				
				
				String m_vat_status="-";
				
				rs1 = stmt1.executeQuery (" SELECT "+
					" CLIENT_CODE, "+//1
					" FULL_NAME, "+//2
					" NVL(REGISTERED_ADDRESS1,'-'), "+//3
					" NVL(REGISTERED_ADDRESS2,'-'), "+//4
					" INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
					" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
					" NVL(DESIGNATION_PAYMENT,'-'), "+//7
					" NVL(VAT_REG_NO,'-') "+//8
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					" WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				if(rs1.next()){
					//out.println("<blockquote>");
					out.println("<table border='0' width='100%' class=table >");
					//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");	
					out.println("</table>");
					out.println("<table border='0' width='100%' class=table >");
					out.println("<tr><td width='*%' class='factoring-letter-body'><b>CLIENT CODE:"+m_client_no+"</b></td></tr>");	
					out.println("<tr><td width='*%' class='factoring-letter-body'><b>FACILITY NO:"+m_facility_no+"</b></td></tr>");	
					out.println("<tr><td width='*%' class='factoring-letter-body'><b>REPORT PERIOD: "+m_date_from+" - "+m_date_to+"</b></td></tr>");	
					out.println("</table>");	
					m_vat_status=rs1.getString(8);
				}
				
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center><b>CURRENT ACCOUNT STATEMENT</b><center></td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center>PRINT DATE/TIME "+m_today+"<center></td></tr>");
				out.println("</table>");			
				
				String m_rpt_start_date=m_date_from;
				double m_op_balance=0;
				
				rs = stmt.executeQuery ("SELECT NVL(SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE "+
					" WHERE CLIENT_CODE='"+m_client_no+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC NOT IN('NORMAL INTEREST','OVERPAID INTEREST') "+
					" AND TRNDATE<TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY') ");
				
				if(rs.next()){
					m_op_balance=m_op_balance+rs.getDouble(1);
				}
				
				rs = stmt.executeQuery (" SELECT 'INTEREST_1' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_no+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
					" UNION ALL "+
					" SELECT 'INTEREST_2' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_no+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')");
				
				while(rs.next()){
					m_op_balance=m_op_balance+rs.getDouble(2);
				}
				//--------------------------------------------------------------------------
				double m_int_normal=0;
				double m_int_over=0;
				
				rs = stmt.executeQuery (" SELECT 'INTEREST_1' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_no+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
					" UNION ALL "+
					" SELECT 'INTEREST_2' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_no+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')");
				
				while(rs.next()){
					if(rs.getString(1).equals("INTEREST_1")){
						m_int_normal=m_int_normal+rs.getDouble(2);
					}
					if(rs.getString(1).equals("INTEREST_2")){
						m_int_over=m_int_over+rs.getDouble(2);
					}
				}
				
				if(!m_vat_status.equals("-")){
					
					rs = stmt.executeQuery (" SELECT CTYPE,"+//1
						" INITCAP(CDESC),"+//2
						" NVL(CAMT,0),"+//3
						" TO_CHAR(CDATE,'DD-MM-YYYY'),"+//4
						" CATYPE, "+//5
						" DEBTOR "+//6  // DEBTOR ------Adde By Sandun on 08-07-2009
						" FROM( "+
						" SELECT 'ADJ' CTYPE, NVL(A.NARRATION,'CLIENT ADJUSTMENTS') CDESC,A.ADJUST_AMOUNT CAMT,A.ADJUST_DATE CDATE,A.ADJUST_TYPE CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A "+
						" WHERE ADJUST_CATEGORY='CLA' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.INVOICE_STATUS='Y' "+ //added by ns
						
						" UNION ALL "+
						" SELECT 'PAY' CTYPE,'CLIENT PAYMENTS-' ||DECODE(A.SETTLE_MODE,'CHEQUE','CHEQUE-'||A.CHEQUE_NO,A.SETTLE_MODE) CDESC,A.PAYMENT_AMOUNT CAMT,A.PAY_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
						" WHERE A.PAY_STATUS IN('CONF','PRINT','DISB') "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" UNION ALL "+
						" SELECT 'CHER' CTYPE,DECODE(A.FEE_CODE,'VAT','V A T',"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE)) CDESC,SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1)) CAMT,A.EFF_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" GROUP BY A.FEE_CODE,A.EFF_DATE "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,DECODE(RECEIPT_TYPE,'CS','INV SETTLEMENT -CLIENT-','INV SETTLEMENT -DEBTOR-') || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE, NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.ALLO_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'NON SALES-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.BALANCE_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE ,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.BALANCE_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS' CDESC,SUM(A.ALLO_AMOUNT) CAMT,B.EFF_VALDATE CDATE,'CR' CATYPE ,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-') DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE B.SUS_REF_NO=A.SETTLE_SCHDULE_REF_NO "+
						" AND B.REC_STATUS<>'Y'  "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" GROUP BY B.RECEIPT_NO,B.EFF_VALDATE,B.DEBTOR_CODE "+
						" UNION ALL "+	
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE ,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C'  "+
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO "+ 
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.ALLO_AMOUNT>0 "+
						" UNION ALL "+///ADD BY MALIK ON 10-9-2008
						" SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE,'' DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+  
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO "+  
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						
						"	UNION ALL "+
						" SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+ 
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" ) "+//END
						" ORDER BY CDATE,CDESC ");
				}
				else{
					
					rs = stmt.executeQuery (" SELECT CTYPE,"+//1
						" INITCAP(CDESC),"+//2
						" NVL(CAMT,0),"+//3
						" TO_CHAR(CDATE,'DD-MM-YYYY'),"+//4
						" CATYPE, "+//5
						" DEBTOR "+//6
						" FROM( "+
						" SELECT 'ADJ' CTYPE, NVL(A.NARRATION,'CLIENT ADJUSTMENTS') CDESC,A.ADJUST_AMOUNT CAMT,A.ADJUST_DATE CDATE,A.ADJUST_TYPE CATYPE,'' DEBTOR"+
						" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A "+
						" WHERE ADJUST_CATEGORY='CLA' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.INVOICE_STATUS='Y' "+ //added by ns
						" UNION ALL "+
						" SELECT 'PAY' CTYPE,'CLIENT PAYMENTS-'||DECODE(A.SETTLE_MODE,'CHEQUE','CHEQUE-'||A.CHEQUE_NO,A.SETTLE_MODE) CDESC,A.PAYMENT_AMOUNT CAMT,A.PAY_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
						" WHERE A.PAY_STATUS IN('CONF','PRINT','DISB') "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" UNION ALL "+
						" SELECT 'CHER' CTYPE,"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE) CDESC,(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))+(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))*("+m_schema_name+".FA_CO_GET_VAT_ON_CHARGES(to_char(eff_date,'dd-mm-yyyy'))/100))) CAMT,A.EFF_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.FEE_CODE=B.FEE_CODE "+
						" AND B.TAX_APPLICABILITY='Y' "+
						" GROUP BY A.FEE_CODE,A.EFF_DATE "+
						" UNION ALL "+
						" SELECT 'CHER' CTYPE,"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE) CDESC,(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))) CAMT,A.EFF_DATE CDATE,'DR' CATYPE,'' DEBTOR_CODE "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.FEE_CODE=B.FEE_CODE "+
						" AND B.TAX_APPLICABILITY='N' "+
						" GROUP BY A.FEE_CODE,A.EFF_DATE "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE, NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-')  DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.ALLO_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'NON SALES-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.BALANCE_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.BALANCE_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS' CDESC,SUM(A.ALLO_AMOUNT) CAMT,B.EFF_VALDATE CDATE,'CR' CATYPE ,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE B.SUS_REF_NO=A.SETTLE_SCHDULE_REF_NO "+
						" AND B.REC_STATUS<>'Y'  "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" GROUP BY B.RECEIPT_NO,B.EFF_VALDATE,B.DEBTOR_CODE "+
						" UNION ALL "+//ADD BY MALIK ON 10-9-2008
						"  SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+  
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO "+  
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						
						"	 UNION ALL "+
						" SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+ 
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" ) "+//END
						" ORDER BY CDATE,CDESC ");
				}
				double m_run_balance=0;
				
				out.println("<table border='0' width='90%' class=table cellpadding=\"3\" cellspacing=\"0\" >");
				out.println("<tr><td width='10%' class='factoring-letter-body' align='center'><b>Date</b></td>");
				out.println("<td width='35%' class='factoring-letter-body' align='center'><b>Description</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center' ><b>DR</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center' ><b>CR</b></td>");
				out.println("<td width='20%' class='factoring-letter-body' align='center' ><b>Balance (Rs.)</b></td>");
				out.println("</tr>");
				
				
				out.println("<tr><td width='10%' class='factoring-letter-body'><b>"+m_rpt_start_date+"</b></td>");
				out.println("<td width='35%' class='factoring-letter-body'>Opening Balance</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
				if(m_op_balance>=0){
					out.println("<td width='20%' class='factoring-letter-body' align='right' ><b>"+nf.format(m_op_balance)+"</b></td>");
				}
				else{
					out.println("<td width='20%' class='factoring-letter-body' align='right' ><b>("+nf.format(m_op_balance*-1)+")</b></td>");
				}
				out.println("</tr>");
				m_run_balance=m_run_balance+m_op_balance;
				
				while(rs.next()){
					
					out.println("<tr><td width='10%' class='factoring-letter-body'>"+rs.getString(4)+"</td>");
					if(rs.getString(6)==null){//Adde By Sandun on 08-07-2009
						out.println("<td width='25%' class='factoring-letter-body'>"+rs.getString(2)+"</td>");
					}else{
						out.println("<td width='25%' class='factoring-letter-body'>"+rs.getString(2)+" - "+rs.getString(6)+"</td>");
					}
					if(rs.getString(5).equals("DR")){
						if(rs.getDouble(3)>=0){
							out.println("<td width='10%' class='factoring-letter-body' align='right' >"+nf.format(rs.getDouble(3))+"</td>");
						}
						else{
							out.println("<td width='10%' class='factoring-letter-body' align='right' >("+nf.format(rs.getDouble(3)*-1)+")</td>");
						}
						out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
						m_run_balance=m_run_balance+rs.getDouble(3);
					}
					else{
						out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
						if(rs.getDouble(3)>=0){
							out.println("<td width='10%' class='factoring-letter-body' align='right' >"+nf.format(rs.getDouble(3))+"</td>");
						}
						else{
							out.println("<td width='10%' class='factoring-letter-body' align='right' >("+nf.format(rs.getDouble(3)*-1)+")</td>");
						}
						m_run_balance=m_run_balance-rs.getDouble(3);
					}
					if(m_run_balance>=0){
						out.println("<td width='20%' class='factoring-letter-body' align='right' >"+nf.format(m_run_balance)+"</td>");
					}
					else{
						out.println("<td width='20%' class='factoring-letter-body' align='right' >("+nf.format(m_run_balance*-1)+")</td>");
					}
					out.println("</tr>");
				}
				
				out.println("<tr><td width='10%' class='factoring-letter-body'>"+m_date_to+"</td>");
				//out.println("<td width='25%' class='factoring-letter-body'>Interest Amount as at "+m_date_to+"</td>");//comented by indika 04/09/08
				out.println("<td width='35%' class='factoring-letter-body'>Discount Charge(Int Amount) as at "+m_date_to+"</td>");//edit by indika 04/09/08
				out.println("<td width='10%' class='factoring-letter-body' align='right' >"+nf.format(m_int_normal)+"</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
				m_run_balance=m_run_balance+m_int_normal;
				if(m_run_balance>0){
					out.println("<td width='20%' class='factoring-letter-body' align='right' >"+nf.format(m_run_balance)+"</td>");
				}
				else{
					out.println("<td width='20%' class='factoring-letter-body' align='right' >("+nf.format(m_run_balance*-1)+")</b></td>");
				}
				out.println("</tr>");
				out.println("<tr><td width='10%' class='factoring-letter-body'>"+m_date_to+"</td>");
				//out.println("<td width='25%' class='factoring-letter-body'>Overpaid Interest Amount as at "+m_date_to+"</td>");//comment by indika 04/09/08
				out.println("<td width='35%' class='factoring-letter-body'>Overpaid Discount(Int Amount) as at "+m_date_to+"</td>");//add by indika 04/09/08
				out.println("<td width='10%' class='factoring-letter-body' align='right' >"+nf.format(m_int_over)+"</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
				m_run_balance=m_run_balance+m_int_over;
				if(m_run_balance>0){
					out.println("<td width='20%' class='factoring-letter-body' align='right' >"+nf.format(m_run_balance)+"</td>");
				}
				else{
					out.println("<td width='20%' class='factoring-letter-body' align='right' >("+nf.format(m_run_balance*-1)+")</td>");
				}
				out.println("</tr>");
				out.println("<tr><td width='10%' class='factoring-letter-body'><b>"+m_date_to+"</b></td>");
				out.println("<td width='25%' class='factoring-letter-body'>Closing Balance</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right' >-</td>");
				if(m_run_balance>0){
					out.println("<td width='20%' class='factoring-letter-body' align='right' ><b>"+nf.format(m_run_balance)+"</b></td>");
				}
				else{
					out.println("<td width='20%' class='factoring-letter-body' align='right' ><b>("+nf.format(m_run_balance*-1)+")</b></td>");
				}
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>Any errors or discrepancies should be reported to the <b><i>Orient Factor</i></b> within 14 days of receipt of this statement</td></tr>");
				out.println("</table>");				
			
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

