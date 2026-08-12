import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_MIS_Daily_Reports  {
	
	ServletOutputStream out = null;
	Connection conn = null;
	Statement stmt = null;
	public ResultSet rs = null;
	java.text.NumberFormat nf,nf1;
	
	public synchronized void Bulk_Gen_Report(HttpServletRequest req,String m_report_id,String m_client_code,String m_facility_no,String m_from_date,String m_to_date,String txtFpath,String pdfFpath ) throws Exception {	
		
		
		
		
		LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
		//************************************************************	
		conn =m_sn_methods.met_user_validate(req);
		conn.setAutoCommit(false);
		//**************************************************************		
		
		String m_fschema_name = m_sn_methods.client_name.trim();
		String m_schema_name = m_sn_methods.schema_name.trim();
		String m_client_name = m_sn_methods.client_name.trim();
		String m_username = m_sn_methods.username;
		String m_html_client_url=m_sn_methods.html_client_url;
		String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
		
		
		nf = java.text.NumberFormat.getInstance(Locale.US);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		
		stmt = conn.createStatement();
		
		
		
		String content=new String();
		String m_today="";
		
		//StringBuffer content_buffer = new StringBuffer();
		PrintWriter printwriter = null;
		PDFConversion pdfConversion = new PDFConversion();
		
		try{ // for if block queries
			String printText = null;
			new FileOutputStream(txtFpath);
			printwriter = new PrintWriter(new FileWriter(txtFpath, true), true);
			
			rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
			if(rs.next()){
				m_today=rs.getString(1);
			}
			rs.close();
			
			double m_tax=0;
			rs = stmt.executeQuery(" SELECT NVL(TAX_AMOUNT,0) FROM "+m_schema_name+".FA_OP_PRO_TAX ");
			if(rs.next()){
				m_tax=rs.getDouble(1);
			}
			
			
			String m_vat_status="-";
			
			rs = stmt.executeQuery (" SELECT "+
				" CLIENT_CODE, "+//1
				" FULL_NAME, "+//2
				" NVL(REGISTERED_ADDRESS1,'-'), "+//3
				" NVL(REGISTERED_ADDRESS2,'-'), "+//4
				" INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
				" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
				" NVL(DESIGNATION_PAYMENT,'-'), "+//7
				" NVL(VAT_REG_NO,'-') "+//8
				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				" WHERE CLIENT_CODE='"+m_client_code+"' ");
			
			if(rs.next()){
				printwriter.println(rs.getString(7));
				printwriter.println(rs.getString(2));
				printwriter.println(rs.getString(3));
				printwriter.println(rs.getString(4));
				printwriter.println(rs.getString(5));
				printwriter.println("");
				printwriter.println("CLIENT CODE:"+m_client_code);
				printwriter.println("FACILITY NO:"+m_facility_no);
				printwriter.println("REPORT PERIOD: "+m_from_date+" - "+m_to_date);
				printwriter.println("");
				
				
				m_vat_status=rs.getString(8);
			}
			
			// ========================= Start Client Account Details =====================================
			
			if(m_report_id.equals("FA_MIS_CLIENT_CURRENT_ACC_DET")){
				
				
				
				printwriter.println(m_sn_methods.Add_Space(45)+"CURRENT ACCOUNT STATEMENT");
				printwriter.println(m_sn_methods.Add_Space(40)+"PRINT DATE/TIME "+m_today+"");
				printwriter.println("");
				
				
				
				String header1="Date";
				int header1_width=12;
				
				String header2="Description";
				int header2_width=50;
				
				String header3="DR";
				int header3_width=15;
				
				String header4="CR";
				int header4_width=15;
				
				String header5="Balance (Rs.)";
				int header5_width=23;
				
				
				printwriter.print(header1);
				if(header1_width-header1.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-header1.length()));
				}
				
				printwriter.print(header2);
				if(header2_width-header2.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-header2.length()));
				}
				
				
				if(header3_width-header3.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-header3.length()));
				}
				printwriter.print(header3);
				
				if(header4_width-header4.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-header4.length()));
				}
				printwriter.print(header4);
				
				if(header5_width-header5.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-header5.length()));
				}
				printwriter.print(header5);
				
				
				
				
				String m_rpt_start_date=m_from_date;
				double m_op_balance=0;
				
				rs = stmt.executeQuery ("SELECT NVL(SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC NOT IN('NORMAL INTEREST','OVERPAID INTEREST') "+
					" AND TRNDATE<TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY') ");
				
				if(rs.next()){
					m_op_balance=m_op_balance+rs.getDouble(1);
				}
				
				rs = stmt.executeQuery (" SELECT 'INTEREST_1' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
					" UNION ALL "+
					" SELECT 'INTEREST_2' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')");
				
				while(rs.next()){
					m_op_balance=m_op_balance+rs.getDouble(2);
				}
				
				
				
				
				
				printwriter.println("");
				
				
				printwriter.print(m_rpt_start_date);
				if(header1_width-m_rpt_start_date.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-m_rpt_start_date.length()));
				}
				
				printText= "Opening Balance";
				printwriter.print(printText);
				if(header2_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
				}
				
				if(header3_width-"-".length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-"-".length()));
				}
				printwriter.print("-");
				
				if(header4_width-"-".length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-"-".length()));
				}
				printwriter.print("-");
				
				if(m_op_balance>=0){
					printText= nf.format(m_op_balance);
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				else{
					printText= nf.format(m_op_balance*-1);
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				
				
				
				//content=content_buffer.toString();
				// return content;
				
				
				
				//--------------------------------------------------------------------------
				double m_int_normal=0;
				double m_int_over=0;
				
				rs = stmt.executeQuery (" SELECT 'INTEREST_1' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
					" UNION ALL "+
					" SELECT 'INTEREST_2' CTYPE,NVL(TRNAMOUNT,0) CAMT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_no+"' "+
					" AND PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')");
				
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
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.INVOICE_STATUS='Y' "+ //added by ns
						
						" UNION ALL "+
						" SELECT 'PAY' CTYPE,'CLIENT PAYMENTS-' ||DECODE(A.SETTLE_MODE,'CHEQUE','CHEQUE-'||A.CHEQUE_NO,A.SETTLE_MODE) CDESC,A.PAYMENT_AMOUNT CAMT,A.PAY_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
						" WHERE A.PAY_STATUS IN('CONF','PRINT','DISB') "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" UNION ALL "+
						" SELECT 'CHER' CTYPE,DECODE(A.FEE_CODE,'VAT','V A T',"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE)) CDESC,SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1)) CAMT,A.EFF_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" GROUP BY A.FEE_CODE,A.EFF_DATE "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,DECODE(RECEIPT_TYPE,'CS','INV SETTLEMENT -CLIENT-','INV SETTLEMENT -DEBTOR-') || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE, NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.ALLO_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'NON SALES-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.BALANCE_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE ,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.BALANCE_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS' CDESC,SUM(A.ALLO_AMOUNT) CAMT,B.EFF_VALDATE CDATE,'CR' CATYPE ,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-') DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE B.SUS_REF_NO=A.SETTLE_SCHDULE_REF_NO "+
						" AND B.REC_STATUS<>'Y'  "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" GROUP BY B.RECEIPT_NO,B.EFF_VALDATE,B.DEBTOR_CODE "+
						" UNION ALL "+	
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE ,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C'  "+
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO "+ 
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.ALLO_AMOUNT>0 "+
						" UNION ALL "+///ADD BY MALIK ON 10-9-2008
						" SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE,'' DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+  
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO "+  
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						
						"	UNION ALL "+
						" SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+ 
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
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
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.INVOICE_STATUS='Y' "+ //added by ns
						" UNION ALL "+
						" SELECT 'PAY' CTYPE,'CLIENT PAYMENTS-'||DECODE(A.SETTLE_MODE,'CHEQUE','CHEQUE-'||A.CHEQUE_NO,A.SETTLE_MODE) CDESC,A.PAYMENT_AMOUNT CAMT,A.PAY_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
						" WHERE A.PAY_STATUS IN('CONF','PRINT','DISB') "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" UNION ALL "+
						" SELECT 'CHER' CTYPE,"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE) CDESC,(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))+(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))*("+m_schema_name+".FA_CO_GET_VAT_ON_CHARGES(to_char(eff_date,'dd-mm-yyyy'))/100))) CAMT,A.EFF_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.FEE_CODE=B.FEE_CODE "+
						" AND B.TAX_APPLICABILITY='Y' "+
						" GROUP BY A.FEE_CODE,A.EFF_DATE "+
						" UNION ALL "+
						" SELECT 'CHER' CTYPE,"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE) CDESC,(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))) CAMT,A.EFF_DATE CDATE,'DR' CATYPE,'' DEBTOR_CODE "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.FEE_CODE=B.FEE_CODE "+
						" AND B.TAX_APPLICABILITY='N' "+
						" GROUP BY A.FEE_CODE,A.EFF_DATE "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE, NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-')  DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.ALLO_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'NON SALES-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.BALANCE_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y'  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" AND A.BALANCE_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS' CDESC,SUM(A.ALLO_AMOUNT) CAMT,B.EFF_VALDATE CDATE,'CR' CATYPE ,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) DEBTOR"+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE B.SUS_REF_NO=A.SETTLE_SCHDULE_REF_NO "+
						" AND B.REC_STATUS<>'Y'  "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" GROUP BY B.RECEIPT_NO,B.EFF_VALDATE,B.DEBTOR_CODE "+
						" UNION ALL "+//ADD BY MALIK ON 10-9-2008
						"  SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+  
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO "+  
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						
						"	 UNION ALL "+
						" SELECT 'RETURN' CTYPE,'RETURN-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,B.DEPOSIT_AMOUNT CAMT,A.RECON_DATE CDATE,'DR' CATYPE,'' DEBTOR "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
						" WHERE A.REC_STATUS='C' "+ 
						" AND A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.CHEQUE_NO=B.CHEQUE_NO  "+
						" AND A.RECEIPT_TYPE<>'SSH' "+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+ 
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
						" ) "+//END
						" ORDER BY CDATE,CDESC ");
				}
				double m_run_balance=0;
				
				
				m_run_balance=m_run_balance+m_op_balance;
				
				while(rs.next()){
					
					printwriter.println("");	
					
					printText = rs.getString(4);
					printwriter.print(printText);
					if(header1_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
					}
					
					if(rs.getString(6)==null){
						printText = rs.getString(2);
						printwriter.print(printText);
						if(header2_width-printText.length()>0){
							printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
						}
						
					}else{
						printText = rs.getString(2)+" - "+rs.getString(6);
						printwriter.print(printText);
						if(header2_width-printText.length()>0){
							printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
						}
						
					}
					
					
					
					if(rs.getString(5).equals("DR")){
						if(rs.getDouble(3)>=0){
							printText = nf.format(rs.getDouble(3));
							if(header3_width-printText.length()>0){
								printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
							}
							printwriter.print(printText);
						}
						else{
							printText ="("+nf.format(rs.getDouble(3)*-1)+")";
							if(header3_width-printText.length()>0){
								printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
							}
							printwriter.print(printText);
						}
						printText ="-";
						if(header3_width-printText.length()>0){
							printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
						}
						printwriter.print(printText);
						m_run_balance=m_run_balance+rs.getDouble(3);
					}
					else
					{
						printText ="-";
						if(header4_width-printText.length()>0){
							printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
						}
						printwriter.print(printText);
						if(rs.getDouble(3)>=0){
							
							printText = nf.format(rs.getDouble(3));
							if(header4_width-printText.length()>0){
								printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
							}
							printwriter.print(printText);
						}
						else
						{
							printText ="("+nf.format(rs.getDouble(3)*-1)+")";
							if(header4_width-printText.length()>0){
								printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
							}
							printwriter.print(printText);
							
							
						}
						m_run_balance=m_run_balance-rs.getDouble(3);
						
					}	
					if(m_run_balance>=0){
						printText =nf.format(m_run_balance);
						if(header5_width-printText.length()>0){
							printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
						}
						printwriter.print(printText);
					}
					else{
						printText ="("+nf.format(m_run_balance*-1)+")";
						if(header5_width-printText.length()>0){
							printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
						}
						printwriter.print(printText);
					}
					
					
				}
				
				
				printwriter.println("");
				
				
				printwriter.print(m_to_date);
				if(header1_width-m_to_date.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-m_to_date.length()));
				}
				
				printText = "Discount Charge(Int Amount) as at "+m_to_date;
				printwriter.print(printText);
				if(header2_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
				}
				
				printText = nf.format(m_int_normal);
				if(header3_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
				}
				printwriter.print(printText);
				
				if(header4_width-"-".length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-"-".length()));
				}
				printwriter.print("-");
				
				
				m_run_balance=m_run_balance+m_int_normal;
				if(m_run_balance>0){
					printText = nf.format(m_run_balance);
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				else{
					printText ="("+nf.format(m_run_balance*-1)+")";
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				
				
				printwriter.println("");
				
				
				printwriter.print(m_to_date);
				if(header1_width-m_to_date.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-m_to_date.length()));
				}
				
				printText = "Overpaid Discount(Int Amount) as at "+m_to_date;
				printwriter.print(printText);
				if(header2_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
				}
				
				printText = nf.format(m_int_over);
				if(header3_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
				}
				printwriter.print(printText);
				
				if(header4_width-"-".length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-"-".length()));
				}
				printwriter.print("-");
				
				
				m_run_balance=m_run_balance+m_int_over;
				if(m_run_balance>0){
					printText = nf.format(m_run_balance);
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				else{
					printText ="("+nf.format(m_run_balance*-1)+")";
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				
				printwriter.println("");
				
				
				printwriter.print(m_to_date);
				if(header1_width-m_to_date.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-m_to_date.length()));
				}
				
				printText = "Closing Balance";
				printwriter.print(printText);
				if(header2_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
				}
				
				printText = "-";
				if(header3_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
				}
				printwriter.print(printText);
				printText = "-";
				if(header4_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
				}
				printwriter.print(printText);
				
				
				if(m_run_balance>0){
					printText = nf.format(m_run_balance);
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				else{
					printText ="("+nf.format(m_run_balance*-1)+")";
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
				}
				
				
				
				
				printText = "Any errors or discrepancies should be reported to the Orient Factor within 14 days of receipt of this statement";
				printwriter.println("");
				printwriter.println(printText);
				printwriter.println("");
				
				
				
			}
			else if(m_report_id.equals("FA_MIS_CLIENT_SALES_LEG_SUMMARY")){
				
				printwriter.println(m_sn_methods.Add_Space(45)+"SUMMARY OF SALES LEDGER");
				printwriter.println(m_sn_methods.Add_Space(40)+"PRINT DATE/TIME "+m_today+"");
				printwriter.println("  ");
				printwriter.println("  ");
				double m_active_tot=0;
				double m_inactive_tot=0;
				double m_active_bal=0;
				double m_inactive_bal=0;
				
				rs = stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'OPENABAL','"+m_from_date+"','"+m_to_date+"')), "+//1
					" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'SET','"+m_from_date+"','"+m_to_date+"')),"+//2
					" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'INR','"+m_from_date+"','"+m_to_date+"')),"+//3
					" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'INA','"+m_from_date+"','"+m_to_date+"')) "+//4
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					
					"");
				
				
				double m_ac_open=0;
				double m_sett_open=0;
				double m_reassign_open=0;
				double m_adjust_open=0;
				double mm_active_open=0;
				while(rs.next()){
					m_active_bal=rs.getDouble(1);
					m_inactive_bal=0;
					m_active_tot=m_active_tot+m_active_bal;
					m_inactive_tot=m_inactive_tot+m_inactive_bal;
					m_ac_open=rs.getDouble(1);
					m_sett_open=rs.getDouble(2);
					m_reassign_open=rs.getDouble(3);
					m_adjust_open=rs.getDouble(4);
				}
				
				mm_active_open= m_active_bal+m_adjust_open - (m_sett_open+m_reassign_open);//added by ns on 23-08-2011
				
				
				
				
				String header1="Date";
				int header1_width=12;
				
				String header2="Reference No";
				int header2_width=20;
				
				String header3="Amount of Invoice Batch";
				int header3_width=24;
				
				String header4="Settled Amount";
				int header4_width=15;
				
				String header5="Reassignments";
				int header5_width=15;
				
				
				String header6="Invoice Adjustments";
				int header6_width=20;
				
				
				String header7="Active Balance";
				int header7_width=15;
				
				
				String header8="Inactive Balance";
				int header8_width=17;
				
				
				printwriter.print(header1);
				if(header1_width-header1.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-header1.length()));
				}
				
				printwriter.print(header2);
				if(header2_width-header2.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-header2.length()));
				}
				
				
				if(header3_width-header3.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-header3.length()));
				}
				printwriter.print(header3);
				
				if(header4_width-header4.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-header4.length()));
				}
				printwriter.print(header4);
				
				if(header5_width-header5.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-header5.length()));
				}
				printwriter.print(header5);
				if(header6_width-header6.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header6_width-header6.length()));
				}
				printwriter.print(header6);
				if(header7_width-header7.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header7_width-header7.length()));
				}
				printwriter.print(header7);
				if(header8_width-header8.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header8_width-header8.length()));
				}
				printwriter.print(header8);
				
				
				printwriter.println("");
				
				
				printwriter.print(m_from_date);
				if(header1_width-m_from_date.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-m_from_date.length()));
				}
				printText = "Openning Balance";
				printwriter.print(printText);
				if(header2_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
				}
				
				printText = nf.format(m_ac_open);
				if(header3_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
				}
				printwriter.print(printText);
				
				printText = nf.format(m_sett_open);
				if(header4_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
				}
				printwriter.print(printText);
				
				printText = nf.format(m_reassign_open);
				if(header5_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
				}
				printwriter.print(printText);
				printText = nf.format(m_adjust_open);
				if(header6_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header6_width-printText.length()));
				}
				printwriter.print(printText);
				printText = nf.format(mm_active_open);
				if(header7_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header7_width-printText.length()));
				}
				printwriter.print(printText);
				printText = nf.format(m_inactive_bal);
				if(header8_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header8_width-printText.length()));
				}
				printwriter.print(printText);
				
				//printwriter.println("");
				
				rs = stmt.executeQuery("SELECT A.BATCH_NO,"+//1
					" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),"+//2
					" SUM(A.TOTAL_BATCH_AMOUNT), "+//3
					" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'SET','"+m_from_date+"','"+m_to_date+"'),"+//4
					" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INR','"+m_from_date+"','"+m_to_date+"'),"+//5
					" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INA','"+m_from_date+"','"+m_to_date+"'),"+//6
					" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'ABAL','"+m_from_date+"','"+m_to_date+"'),"+//7
					" 0, "+//8
					" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'ABALI','"+m_from_date+"','"+m_to_date+"')"+//9
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
					" WHERE "+
					" TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
					" AND A.FACILITY_NO='"+m_facility_no+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					
					" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE "+
					" ORDER BY A.INVOICE_BATCH_DATE ");
				
				
				while(rs.next()){
					printwriter.println("");
					
					
					printText = rs.getString(2);
					printwriter.print(printText);
					if(header1_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
					}
					printText = rs.getString(1);
					printwriter.print(printText);
					if(header2_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
					}
					
					printText = nf.format(rs.getDouble(9));
					if(header3_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
					}
					printwriter.print(printText);
					
					printText = nf.format(rs.getDouble(4));
					if(header4_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
					}
					printwriter.print(printText);
					
					printText = nf.format(rs.getDouble(5));
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
					printText = nf.format(rs.getDouble(6));
					if(header6_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header6_width-printText.length()));
					}
					printwriter.print(printText);
					printText = nf.format(rs.getDouble(7));
					if(header7_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header7_width-printText.length()));
					}
					printwriter.print(printText);
					printText = nf.format(rs.getDouble(8));
					if(header8_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header8_width-printText.length()));
					}
					printwriter.print(printText);
				}
				
				rs = stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'CLOSEABAL','"+m_from_date+"','"+m_to_date+"')) "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"'  "+
					
					"");
				
				while(rs.next()){
					m_active_tot=rs.getDouble(1);
					m_inactive_tot=0;
				}
				
				
				
				printwriter.println("");
				
				
				printText = m_to_date;
				printwriter.print(printText);
				if(header1_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
				}
				printText = "Closing Balance";
				printwriter.print(printText);
				if(header2_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
				}
				
				printText = "";
				if(header3_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
				}
				printwriter.print(printText);
				
				printText = "";
				if(header4_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
				}
				printwriter.print(printText);
				
				printText = "";
				if(header5_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
				}
				printwriter.print(printText);
				printText = "";
				if(header6_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header6_width-printText.length()));
				}
				printwriter.print(printText);
				printText = nf.format(m_active_tot);
				if(header7_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header7_width-printText.length()));
				}
				printwriter.print(printText);
				printText = nf.format(m_inactive_tot);
				if(header8_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header8_width-printText.length()));
				}
				printwriter.print(printText);
				
				
				
				printwriter.println("  ");
				printwriter.println("  ");
				printText = "Any errors or discrepancies should be reported to the Orient Factor within 14 days of receipt of this statement";
				printwriter.println(printText);
				printwriter.println("   ");
				
				
			}
			else if(m_report_id.equals("FA_MIS_INV_BATCH_DET")){
				
				printwriter.println(m_sn_methods.Add_Space(45)+"NOTIFICATION SCHEDULE - INVOICE BATCH");
				printwriter.println(m_sn_methods.Add_Space(46)+"PRINT DATE/TIME "+m_today+"");
				printwriter.println("    ");
				printwriter.println("    ");
				
				printwriter.println("Dear Sir");
				printwriter.println("We acknowledge receipt of under mentioned notification schedule(Invoice Batch)");
				printwriter.println("   ");
				printwriter.println("   ");
				
				
				String header1="Debtor Name";
				int header1_width=35;
				
				String header2="Batch No";
				int header2_width=30;
				
				String header3="Invoice No";
				int header3_width=15;
				
				String header4="Invoice Date";
				int header4_width=15;
				
				String header5="Invoice Amount";
				int header5_width=15;
				
				
				
				printwriter.print(header1);
				if(header1_width-header1.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-header1.length()));
				}
				
				printwriter.print(header2);
				if(header2_width-header2.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-header2.length()));
				}
				
				printwriter.print(header3);
				if(header3_width-header3.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-header3.length()));
				}
				
				printwriter.print(header4);
				if(header4_width-header4.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-header4.length()));
				}
				
				if(header5_width-header5.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-header5.length()));
				}
				printwriter.print(header5);
				
				
				double m_total=0;
				
				rs = stmt.executeQuery("SELECT A.BATCH_NO,"+//1
					""+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2
					" A.INVOICE_NO,"+//3
					" A.INVOICE_AMOUNT,"+//4
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY') "+//5
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE A.BATCH_NO=B.BATCH_NO "+
					" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" ORDER BY A.BATCH_NO ");
				
				while(rs.next()){
					
					
					
					printwriter.println("");
					
					
					printText = rs.getString(2);
					printwriter.print(printText);
					if(header1_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
					}
					printText = rs.getString(1);
					printwriter.print(printText);
					if(header2_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
					}
					
					printText = rs.getString(3);
					printwriter.print(printText);
					if(header3_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
					}
					
					
					printText = rs.getString(5);
					printwriter.print(printText);
					if(header4_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
					}
					
					
					printText = nf.format(rs.getDouble(4));
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					m_total=m_total+rs.getDouble(4);
				}
				
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "";
				printwriter.print(printText);
				if(header1_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
				}
				printText = "";
				printwriter.print(printText);
				if(header2_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
				}
				
				printText = "";
				printwriter.print(printText);
				if(header3_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
				}
				
				
				printText = "";
				printwriter.print(printText);
				if(header4_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
				}
				
				
				printText = nf.format(m_total);
				if(header5_width-printText.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
				}
				printwriter.print(printText);
				
				
				
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "If you have any queries pertaining to the above please do not hesitate to contact the undersigned.";
				printwriter.println(printText);
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "Thanking you";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Yours faithfully";
				printwriter.println(printText);
				printwriter.println("   ");
				
				printwriter.println("");
				printText = "Factoring Division of Lakderana Investments Limited";
				printwriter.println(printText);
				printwriter.println("   ");
				printwriter.println("   ");
				
				
				printText = "..........................";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Authorized Signatory";
				printwriter.println(printText);
				printText = "Executive Operations";
				printwriter.println(printText);
				printwriter.println("    ");
				
				
			}
			else if(m_report_id.equals("FA_MIS_INV_SETTLEMENT_DET")){
				
				
				
				
				printwriter.println(m_sn_methods.Add_Space(50)+"INVOICE SETTLEMENT REPORT");
				printwriter.println(m_sn_methods.Add_Space(45)+"PRINT DATE/TIME "+m_today+"");
				printwriter.println("    ");
				printwriter.println("    ");
				
				printwriter.println("Dear Sir");
				printwriter.println("We inform to you that the following invoice amounts were settled against your payments.");
				
				printwriter.println("    ");
				
				String header1="Settle Date";
				int header1_width=12;
				
				String header2="Debtor Name";
				int header2_width=35;
				
				String header3="Chq. No";
				int header3_width=10;
				
				String header4="Branch Name";
				int header4_width=20;
				
				String header5="Chq. Amt";
				int header5_width=15;
				
				String header6="Invoice No";
				int header6_width=15;
				
				String header7="Invoice Amt";
				int header7_width=15;
				
				String header8="Sett. Amt";
				int header8_width=15;
				
				String header9="Bal. Amt";
				int header9_width=15;
				
				printwriter.print(header1);
				if(header1_width-header1.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-header1.length()));
				}
				
				printwriter.print(header2);
				if(header2_width-header2.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-header2.length()));
				}
				
				printwriter.print(header3);
				if(header3_width-header3.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-header3.length()));
				}
				
				printwriter.print(header4);
				if(header4_width-header4.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-header4.length()));
				}
				
				if(header5_width-header5.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-header5.length()));
				}
				printwriter.print(header5);
				
				
				if(header6_width-header6.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header6_width-header6.length()));
				}
				printwriter.print(header6);
				
				if(header7_width-header7.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header7_width-header7.length()));
				}
				printwriter.print(header7);
				
				
				if(header8_width-header8.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header8_width-header8.length()));
				}
				printwriter.print(header8);
				
				
				if(header9_width-header9.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header9_width-header9.length()));
				}
				printwriter.print(header9);
				
				
				
				rs = stmt.executeQuery("SELECT "+
					" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),"+//1
					" A.RECEIPT_NO, "+//2
					" NVL(A.ALLOCATED_AMOUNT,0),"+//3
					" NVL("+m_schema_name+".FA_CLIENT_SETTLE_DATA(A.RECEIPT_NO,'CHQ'),'-'),"+//4
					" NVL("+m_schema_name+".FA_CLIENT_SETTLE_DATA(A.RECEIPT_NO,'BANK'),'-'),"+//5
					" B.INVOICE_NO,"+//6
					" B.DEBTOR_CODE,"+//7
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),"+//8
					" NVL(B.INVOICE_AMOUNT,0),"+//9
					" NVL(B.BALANCE_AMOUNT,0), "+//10
					" NVL(A.RECEIPT_AMOUNT,0), "+//11
					" ("+m_schema_name+".FA_CLIENT_PRE_INV_BAL('"+m_client_code+"','"+m_facility_no+"',A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'))-A.ALLOCATED_AMOUNT) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+
					" WHERE A.INVOICE_NO=B.INVOICE_SEQ_NO "+
					" AND B.BATCH_NO=C.BATCH_NO "+
					" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
					" AND C.FACILITY_NO='"+m_facility_no+"' "+
					" AND C.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.ALLOCATED_AMOUNT>0 "+
					" ORDER BY A.ALLOCATED_DATE ");
				
				while(rs.next()){
					printwriter.println("");
					printText = rs.getString(1);
					printwriter.print(printText);
					if(header1_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
					}
					printText = rs.getString(8);
					printwriter.print(printText);
					if(header2_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
					}
					
					printText = rs.getString(4);
					printwriter.print(printText);
					if(header3_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
					}
					
					
					printText = rs.getString(5);
					printwriter.print(printText);
					if(header4_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
					}
					
					
					printText = nf.format(rs.getDouble(11));
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
					
					printText = rs.getString(6);
					
					if(header6_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header6_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText =  nf.format(rs.getDouble(9));
					if(header7_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header7_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText =  nf.format(rs.getDouble(3));
					if(header8_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header8_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText =  nf.format(rs.getDouble(12));
					if(header9_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header9_width-printText.length()));
					}
					printwriter.print(printText);
				}
				
				
				
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "If you have any queries pertaining to the above please do not hesitate to contact the undersigned.";
				printwriter.println(printText);
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "Thanking you";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Yours faithfully";
				printwriter.println(printText);
				printwriter.println("   ");
				
				printwriter.println("");
				printText = "Factoring Division of Lakderana Investments Limited";
				printwriter.println(printText);
				printwriter.println("   ");
				printwriter.println("   ");
				
				
				printText = "..........................";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Authorized Signatory";
				printwriter.println(printText);
				printText = "Executive Operations";
				printwriter.println(printText);
				printwriter.println("    ");
				
				
				
				
			}
			else if(m_report_id.equals("FA_MIS_INV_REASS_DET")){
				
				
				printwriter.println(m_sn_methods.Add_Space(50)+"LETTER OF REASSIGNED OF THE INVOICES");
				printwriter.println(m_sn_methods.Add_Space(47)+"PRINT DATE/TIME "+m_today+"");
				printwriter.println("    ");
				printwriter.println("    ");
				
				printwriter.println("Dear Sir");
				printwriter.println("We inform to you that the following unsettled invoices amounts which received under notification schedules have been re-assigned and details given below.");
				
				printwriter.println("    ");
				
				String header1="Adj. Date";
				int header1_width=12;
				
				String header2="Debtor Name";
				int header2_width=35;
				
				String header3="Invoice No";
				int header3_width=12;
				
				String header4="Invoice Date";
				int header4_width=16;
				
				String header5="Batch No";
				int header5_width=23;
				
				String header6="Reassign. Amt";
				int header6_width=15;
				
				String header7="Invoice. Amt";
				int header7_width=15;
				
				String header8="Sett. Amt";
				int header8_width=15;
				
				String header9="Comments";
				int header9_width=30;
				
				printwriter.print(header1);
				if(header1_width-header1.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-header1.length()));
				}
				
				printwriter.print(header2);
				if(header2_width-header2.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-header2.length()));
				}
				
				printwriter.print(header3);
				if(header3_width-header3.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-header3.length()));
				}
				
				printwriter.print(header4);
				if(header4_width-header4.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-header4.length()));
				}
				
				printwriter.print(header5);
				if(header5_width-header5.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-header5.length()));
				}
				
				
				
				if(header6_width-header6.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header6_width-header6.length()));
				}
				printwriter.print(header6);
				
				if(header7_width-header7.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header7_width-header7.length()));
				}
				printwriter.print(header7);
				
				
				if(header8_width-header8.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header8_width-header8.length()));
				}
				printwriter.print(header8);
				
				
				
				if(header9_width-header9.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header9_width-header9.length()));
				}
				printwriter.print(header9);
				
				
				
				rs = stmt.executeQuery(" SELECT "+
					" TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),"+//1
					" A.DEBTOR_CODE,"+//2
					" A.INVOICE_NO,"+//3
					" B.INVOICE_AMOUNT,"+//4
					" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'),"+//5
					" A.BATCH_NO,"+//6
					" A.ADJUSTMENT_NO,"+//7
					" "+m_schema_name+".FA_CLIENT_INVICE_AMT_DETAIL(B.BATCH_NO,B.DEBTOR_CODE,B.INVOICE_NO,'INR'),"+//8
					" A.ADJUST_TYPE,"+//9
					" A.SOURCE_DOCUMENT,"+//10
					" NVL(A.ADJUSTMENT_COMMENTS,'-'),"+//11
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//12
					" B.SETTLE_AMOUNT "+//13
					" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+
					" WHERE B.BATCH_NO=C.BATCH_NO "+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND A.INVOICE_NO=B.INVOICE_NO "+
					" AND A.ADJUST_CATEGORY='INR' "+
					" AND A.INVOICE_STATUS='Y' "+
					" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
					" AND C.FACILITY_NO='"+m_facility_no+"' "+
					" AND C.CLIENT_CODE='"+m_client_code+"' "+
					" ORDER BY A.ADJUST_DATE ");
				
				while(rs.next()){
					
					printwriter.println("");
					printText = rs.getString(1);
					printwriter.print(printText);
					if(header1_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
					}
					printText = rs.getString(12)+" "+rs.getString(2);
					printwriter.print(printText);
					if(header2_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
					}
					
					printText = rs.getString(3);
					printwriter.print(printText);
					if(header3_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
					}
					
					
					printText = rs.getString(5);
					printwriter.print(printText);
					if(header4_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
					}
					
					
					printText = rs.getString(6);
					printwriter.print(printText);
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					
					
					printText = nf.format(rs.getDouble(8));
					
					if(header6_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header6_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText =  nf.format(rs.getDouble(4));
					if(header7_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header7_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText =  nf.format(rs.getDouble(13));
					if(header8_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header8_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText =  rs.getString(10)+" "+rs.getString(11);
					
					if(header9_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header9_width-printText.length()));
					}
					printwriter.print(printText);
				}
				
				
				
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "If you have any queries pertaining to the above please do not hesitate to contact the undersigned.";
				printwriter.println(printText);
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "Thanking you";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Yours faithfully";
				printwriter.println(printText);
				printwriter.println("   ");
				
				printwriter.println("");
				printText = "Factoring Division of Lakderana Investments Limited";
				printwriter.println(printText);
				printwriter.println("   ");
				printwriter.println("   ");
				
				
				printText = "..........................";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Authorized Signatory";
				printwriter.println(printText);
				printText = "Executive Operations";
				printwriter.println(printText);
				printwriter.println("    ");
				
				
			}
			else if(m_report_id.equals("FA_MIS_INV_ADJUST_DET")){

				printwriter.println(m_sn_methods.Add_Space(50)+"INVOICE ADJUSTMENT REPORT");
				printwriter.println(m_sn_methods.Add_Space(47)+"PRINT DATE/TIME "+m_today+"");
				printwriter.println("    ");
				printwriter.println("    ");

				printwriter.println("Dear Sir");
				printwriter.println("We inform to you that the following adjustments have been made against your sales ledger.The information as follows");
				
				printwriter.println("    ");

				
				
				String header1="Adj. Date";
				int header1_width=12;
				
				String header2="Debtor Name";
				int header2_width=35;
				
				String header3="Invoice No";
				int header3_width=12;
				
				String header4="Invoice Date";
				int header4_width=16;
				
				String header5="Batch No";
				int header5_width=23;
				
				String header6="Invoice. Amt";
				int header6_width=15;
				
				String header7="Adj. Amt";
				int header7_width=15;
				
				String header8="Comments";
				int header8_width=30;
				
				
				printwriter.print(header1);
				if(header1_width-header1.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header1_width-header1.length()));
				}
				
				printwriter.print(header2);
				if(header2_width-header2.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header2_width-header2.length()));
				}
				
				printwriter.print(header3);
				if(header3_width-header3.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header3_width-header3.length()));
				}
				
				printwriter.print(header4);
				if(header4_width-header4.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header4_width-header4.length()));
				}
				
				
				if(header5_width-header5.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header5_width-header5.length()));
				}
				printwriter.print(header5);
				
				
				if(header6_width-header6.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header6_width-header6.length()));
				}
				printwriter.print(header6);
				
				if(header7_width-header7.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header7_width-header7.length()));
				}
				printwriter.print(header7);
				
				
				if(header8_width-header8.length()>0){
					printwriter.print(m_sn_methods.Add_Space(header8_width-header8.length()));
				}
				printwriter.print(header8);
				

				
				rs = stmt.executeQuery(" SELECT "+
					" TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),"+//1
					" A.DEBTOR_CODE,"+//2
					" A.INVOICE_NO,"+//3
					" B.INVOICE_AMOUNT,"+//4
					" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'),"+//5
					" A.BATCH_NO,"+//6
					" A.ADJUSTMENT_NO,"+//7
					" NVL(A.ADJUST_AMOUNT,0),"+//8
					" A.ADJUST_TYPE,"+//9
					" A.SOURCE_DOCUMENT,"+//10
					" NVL(A.ADJUSTMENT_COMMENTS,'-'),"+//11
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) "+//12
					" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+
					" WHERE B.BATCH_NO=C.BATCH_NO "+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND A.INVOICE_NO=B.INVOICE_NO "+
					" AND A.ADJUST_CATEGORY='INA' "+
					" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+
					" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')"+
					" AND C.FACILITY_NO='"+m_facility_no+"' "+
					" AND C.CLIENT_CODE='"+m_client_code+"' "+
					" ORDER BY A.ADJUST_DATE ");
				
				
				while(rs.next()){

					
					printwriter.println("");
					printText = rs.getString(1);
					printwriter.print(printText);
					if(header1_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header1_width-printText.length()));
					}
					printText = rs.getString(12)+" "+rs.getString(2);
					printwriter.print(printText);
					if(header2_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header2_width-printText.length()));
					}
					
					printText = rs.getString(3);
					printwriter.print(printText);
					if(header3_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header3_width-printText.length()));
					}
					
					
					printText = rs.getString(5);
					printwriter.print(printText);
					if(header4_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header4_width-printText.length()));
					}
					
					
					printText = rs.getString(6);
					
					if(header5_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header5_width-printText.length()));
					}
					printwriter.print(printText);
					
					printText = nf.format(rs.getDouble(4));
					
					if(header6_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header6_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText = nf.format(rs.getDouble(8))+"("+rs.getString(9)+")";
					if(header7_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header7_width-printText.length()));
					}
					printwriter.print(printText);
					
					
					printText =   rs.getString(10)+" "+rs.getString(11);
					if(header8_width-printText.length()>0){
						printwriter.print(m_sn_methods.Add_Space(header8_width-printText.length()));
					}
					printwriter.print(printText);
					

				}
				
				
				
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "If you have any queries pertaining to the above please do not hesitate to contact the undersigned.";
				printwriter.println(printText);
				
				printwriter.println("   ");
				printwriter.println("   ");
				printText = "Thanking you";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Yours faithfully";
				printwriter.println(printText);
				printwriter.println("   ");
				
				printwriter.println("");
				printText = "Factoring Division of Lakderana Investments Limited";
				printwriter.println(printText);
				printwriter.println("   ");
				printwriter.println("   ");
				
				
				printText = "..........................";
				printwriter.println(printText);
				printwriter.println("   ");
				printText = "Authorized Signatory";
				printwriter.println(printText);
				printText = "Executive Operations";
				printwriter.println(printText);
				printwriter.println("    ");
				

				
			
				
				
					
				
				
			}
			
			
			printwriter.close();
			pdfConversion.createPdfLB(txtFpath,pdfFpath);
			/*if (m_report_id.equals("FA_MIS_CLIENT_SALES_LEG_SUMMARY")) {
				pdfConversion.createPdfLB(txtFpath,pdfFpath);
			}else {
				pdfConversion.createPdfL(txtFpath,pdfFpath);
				
			}*/
			conn.close();
			
		} // End Try for if block queries
		
		catch(Exception e){
			try{
				if(conn != null){conn.close();}
			}catch(Exception ex){
			}
			
			printwriter.println(e.toString());
			
		} // End catch for if block queries
		
		//return content;
		
	}
}


