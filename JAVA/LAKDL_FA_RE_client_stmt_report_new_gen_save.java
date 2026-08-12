// DEVELOPED  BY : UDARA FOR OFSCL FACTORING    DATE: 15-02-2012 
// MODIFIED   BY : UDARA FOR OFSCL FACTORING    DATE: 23-02-2012
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import java.io.FileOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Element;	
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;
import com.lowagie.text.Chunk;

import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;

public class LAKDL_FA_RE_client_stmt_report_new_gen_save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	java.text.NumberFormat nf;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			m_msg = "'Information saved successfully'";
			stmt = conn.createStatement();
			
			String file_upload_path = "D:\\SasiaNet_Products\\NetAsset\\OFSCL\\UPLOAD\\";
			
			String m_facility_no = m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			String m_client_code = m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"); 
			String m_client_no   = m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"); 			
			String m_from_date   = m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY");			
			String m_to_date     = m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_DD")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_MM")+"-"+m_sn_methods.met_formdata(reqstr,"TXT_TO_DATE_YY");
			
			String m_today       = null;
			String m_date_from   = m_from_date;
			String m_date_to     = m_to_date;
			
			rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");
			if(rs.next()){
				m_today=rs.getString(1);
			}
			rs.close();

			String pdfFpath = "";
			String pdfFolderpath = "";

			String m_doc_code= "CLIENT_STATEMENT_BULK";
  
			pdfFolderpath = file_upload_path + "Client_Statements_New\\"+m_doc_code+"\\"+m_today+"\\"+m_client_code+"("+m_facility_no+")\\PDF";

			File dir2 = new File(pdfFolderpath);
			if (!dir2.exists()) {
				dir2.mkdirs();
			}

			pdfFpath = pdfFolderpath+"\\"+m_client_code+"-"+m_doc_code+".pdf";						
			
			String file_name = "";
			String file_path = "";
			
			Font catFont = new Font(Font.getFamilyIndex("Arial"), 7,Font.NORMAL);
			Font BoldFont =new Font(Font.getFamilyIndex("Arial"), 7,Font.BOLD);
			Font catFontHead = new Font(Font.getFamilyIndex("Arial"), 8,Font.NORMAL);
			Font BoldFontHead =new Font(Font.getFamilyIndex("Arial"), 8,Font.BOLD);
			
			try{
				
				file_name=m_client_code+"-"+m_doc_code+".pdf";
				file_path=pdfFolderpath;
				file_path=file_path+"\\"+file_name;
				File m_new_dir = new File(pdfFolderpath);
				m_new_dir.mkdirs();	
	
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(file_path));
				document.open();
				
				// ************************************************************************************
				// ********************************** PDF BODY START **********************************
				// ************************************************************************************
				
				// =========================== Address Section Start ==================================				
				String designation = "";
				String fullName    = "";
				String regAdd1     = "";
				String regAdd2     = "";
				String cityCode    = "";
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
							" WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				if(rs.next()){
					designation = rs.getString(7);
					fullName    = rs.getString(2);
					regAdd1     = rs.getString(3);
					regAdd2     = rs.getString(4);
					cityCode    = rs.getString(5);
					m_vat_status= rs.getString(8);
				}
				
				Paragraph p=new Paragraph();
				p.add(new Paragraph(designation,catFontHead));
				p.add(new Paragraph(fullName,catFontHead));
				p.add(new Paragraph(regAdd1,catFontHead));
				p.add(new Paragraph(regAdd2,catFontHead));
				p.add(new Paragraph(cityCode,catFontHead));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);				
				// =========================== Address Section End ==================================
				
				// =========================== Client Code & Facility No. Start =====================				
				p=new Paragraph();
				p.add(new Paragraph("Client Code : "+m_client_no,BoldFontHead));
				p.add(new Paragraph("Facility No : "+m_facility_no,BoldFontHead));
				p.add(new Paragraph("Report Period : "+m_from_date+" - "+m_to_date,BoldFontHead));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);			
				// =========================== Client Code & Facility No. End =======================
				
				
				rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs.next()){
					m_today=rs.getString(1);
				}
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				// =========================== Current Account Section Start =====================				
				
				p=new Paragraph();
				Chunk topic=new Chunk(" CURRENT ACCOUNT STATEMENT ");
				topic.setFont(BoldFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				topic=new Chunk(" PRINT DATE/TIME "+m_today);
				topic.setFont(catFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				PdfPTable table = new PdfPTable(5); 
				PdfPCell defaultCell = table.getDefaultCell();
				defaultCell.setBorder(PdfPCell.NO_BORDER);
				table.setWidths(new int[]{ 1, 2, 1, 1,1 });
				table.setWidthPercentage(100);
				
				table.addCell(new Paragraph("Date",BoldFont));
				table.addCell(new Paragraph("Description",BoldFont));
				table.addCell(new Paragraph("DR",BoldFont));
				table.addCell(new Paragraph("CR",BoldFont));
				table.addCell(new Paragraph("Balance",BoldFont));
				
				
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
						
						// Row 1
						table.addCell(new Paragraph(m_rpt_start_date,BoldFont));
						table.addCell(new Paragraph("Opening Balance",catFont));
						table.addCell(new Paragraph("-",catFont));
						table.addCell(new Paragraph("-",catFont));
						
						if(m_op_balance>=0)
							table.addCell(new Paragraph(nf.format(m_op_balance),BoldFont));
						else
							table.addCell(new Paragraph("("+nf.format(m_op_balance*-1)+")",BoldFont));
						
						m_run_balance=m_run_balance+m_op_balance;
						
						// Row 2
						while(rs.next()){
							
							table.addCell(new Paragraph(rs.getString(4),catFont)); 
							if(rs.getString(6)==null){
								table.addCell(new Paragraph(rs.getString(2),catFont)); 
							}else{
								table.addCell(new Paragraph(rs.getString(2)+" - "+rs.getString(6),catFont)); 
							}
							if(rs.getString(5).equals("DR")){
								if(rs.getDouble(3)>=0){
									table.addCell(new Paragraph(nf.format(rs.getDouble(3)),catFont)); 
								}
								else{
									table.addCell(new Paragraph("("+nf.format(rs.getDouble(3)*-1)+")",catFont)); 
								}
								table.addCell(new Paragraph("-",catFont)); 
								m_run_balance=m_run_balance+rs.getDouble(3);
							}
							else{
								table.addCell(new Paragraph("-",catFont)); 
								if(rs.getDouble(3)>=0){
									table.addCell(new Paragraph(nf.format(rs.getDouble(3)),catFont)); 
								}
								else{
									table.addCell(new Paragraph("("+nf.format(rs.getDouble(3)*-1)+")",catFont)); 
								}
								m_run_balance=m_run_balance-rs.getDouble(3);
							}
							if(m_run_balance>=0){
								table.addCell(new Paragraph(nf.format(m_run_balance),catFont)); 
							}
							else{
								table.addCell(new Paragraph("("+nf.format(m_run_balance*-1)+")",catFont)); 
							}
							
						}
						

						// Row 3
						table.addCell(new Paragraph(m_date_to,catFont)); 
						table.addCell(new Paragraph("Discount Charge(Int Amount) as at "+m_date_to,catFont)); 
						table.addCell(new Paragraph(nf.format(m_int_normal),catFont)); 
						table.addCell(new Paragraph("-",catFont)); 
						
						m_run_balance=m_run_balance+m_int_normal;
						if(m_run_balance>0){
							table.addCell(new Paragraph(nf.format(m_run_balance),catFont)); 
						}
						else{
							table.addCell(new Paragraph("("+nf.format(m_run_balance*-1)+")",catFont)); 
						}

						
						// Row 4
						table.addCell(new Paragraph(m_date_to,catFont)); 
						table.addCell(new Paragraph("Overpaid Discount(Int Amount) as at "+m_date_to,catFont)); 
						table.addCell(new Paragraph(nf.format(m_int_over),catFont)); 
						table.addCell(new Paragraph("-",catFont)); 
						m_run_balance=m_run_balance+m_int_over;
						if(m_run_balance>0){
							table.addCell(new Paragraph(nf.format(m_run_balance),catFont)); 
						}
						else{
							table.addCell(new Paragraph("("+nf.format(m_run_balance*-1)+")",catFont)); 
						}
						
						// Row 5
						table.addCell(new Paragraph(m_date_to,BoldFont));
						table.addCell(new Paragraph("Closing Balance",catFont)); 
						table.addCell(new Paragraph("-",catFont));
						table.addCell(new Paragraph("-",catFont));
						if(m_run_balance>0){
							table.addCell(new Paragraph(nf.format(m_run_balance),BoldFont)); 
						}
						else{
							table.addCell(new Paragraph("("+nf.format(m_run_balance*-1)+")",BoldFont)); 
						}
						
						document.add(table);

						p=new Paragraph();
						p.setSpacingBefore(6);
						p.add(new Chunk(" Any errors or discrepancies should be reported to the ",catFont));
						p.add(new Chunk(" Orient Factor ",BoldFont));
						p.add(new Chunk(" within 14 days of receipt of this statement.",catFont));	
						p.setAlignment(Element.ALIGN_JUSTIFIED);
						document.add(p);
						
						p=new Paragraph();
						p.add(new Paragraph(" "));
						document.add(p);
				
				// =========================== Current Account Section End =======================				
				
				// =========================== Sales Ledger Section Start ========================				
				
				document.newPage();

				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
			    topic=new Chunk(" SUMMARY OF SALES LEDGER ");
				topic.setFont(BoldFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				topic=new Chunk(" PRINT DATE/TIME "+m_today);
				topic.setFont(catFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
						double m_active_tot=0;
						double m_inactive_tot=0;
						double m_active_bal=0;
						double m_inactive_bal=0;
						
						rs = stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'OPENABAL','"+m_date_from+"','"+m_date_to+"')), "+//1
							" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'SET','"+m_date_from+"','"+m_date_to+"')),"+//2
							" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'INR','"+m_date_from+"','"+m_date_to+"')),"+//3
							" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'INA','"+m_date_from+"','"+m_date_to+"')) "+//4
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
							" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
							" AND B.FACILITY_NO='"+m_facility_no+"' "+
							" AND B.CLIENT_CODE='"+m_client_no+"' "+
							
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
						
						mm_active_open= m_active_bal+m_adjust_open - (m_sett_open+m_reassign_open);
				
				PdfPTable table_sl = new PdfPTable(8); 
				PdfPCell defaultCell_s1 = table_sl.getDefaultCell();
				defaultCell_s1.setBorder(PdfPCell.NO_BORDER);
				table_sl.setWidths(new int[]{ 1, 1, 1, 1, 1, 1, 1, 1 });
				table_sl.setWidthPercentage(100);
				
				table_sl.addCell(new Paragraph("Date",BoldFont));
				table_sl.addCell(new Paragraph("Reference No",BoldFont));
				table_sl.addCell(new Paragraph("Amount of Invoice Batch",BoldFont));
				table_sl.addCell(new Paragraph("Settled Amount",BoldFont));
				table_sl.addCell(new Paragraph("Reassignments",BoldFont));
				table_sl.addCell(new Paragraph("Invoice Adjustments",BoldFont));
				table_sl.addCell(new Paragraph("Active Balance",BoldFont));
				table_sl.addCell(new Paragraph("Inactive Balance",BoldFont));
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				table_sl.addCell(new Paragraph(m_date_from,BoldFont));
				table_sl.addCell(new Paragraph("Openning Balance",BoldFont));
				table_sl.addCell(new Paragraph(nf.format(m_ac_open),BoldFont));
				table_sl.addCell(new Paragraph(nf.format(m_sett_open),BoldFont));
				table_sl.addCell(new Paragraph(nf.format(m_reassign_open),BoldFont));
				table_sl.addCell(new Paragraph(nf.format(m_adjust_open),BoldFont));
				table_sl.addCell(new Paragraph(nf.format(mm_active_open),BoldFont));
				table_sl.addCell(new Paragraph(nf.format(m_inactive_bal),BoldFont));
				
				
				rs = stmt.executeQuery("SELECT A.BATCH_NO,"+//1
						" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),"+//2
						" SUM(A.TOTAL_BATCH_AMOUNT), "+//3
						" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'SET','"+m_date_from+"','"+m_date_to+"'),"+//4
						" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INR','"+m_date_from+"','"+m_date_to+"'),"+//5
						" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INA','"+m_date_from+"','"+m_date_to+"'),"+//6
						" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'ABAL','"+m_date_from+"','"+m_date_to+"'),"+//7
						" 0, "+//8
						" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'ABALI','"+m_date_from+"','"+m_date_to+"')"+//9
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
						" WHERE "+
						" TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND A.CLIENT_CODE='"+m_client_no+"' "+
						
						" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE "+
						" ORDER BY A.INVOICE_BATCH_DATE ");
				
				
					while(rs.next()){
						
						table_sl.addCell(new Paragraph(rs.getString(2),catFont));
						table_sl.addCell(new Paragraph(rs.getString(1),catFont));
						table_sl.addCell(new Paragraph(nf.format(rs.getDouble(9)),catFont));
						table_sl.addCell(new Paragraph(nf.format(rs.getDouble(4)),catFont));
						table_sl.addCell(new Paragraph(nf.format(rs.getDouble(5)),catFont));
						table_sl.addCell(new Paragraph(nf.format(rs.getDouble(6)),catFont));
						table_sl.addCell(new Paragraph(nf.format(rs.getDouble(7)),catFont));
						table_sl.addCell(new Paragraph(nf.format(rs.getDouble(8)),catFont));
						
						
					}
					
					rs = stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'CLOSEABAL','"+m_date_from+"','"+m_date_to+"')) "+
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
							" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
							" AND B.FACILITY_NO='"+m_facility_no+"' "+
							" AND B.CLIENT_CODE='"+m_client_no+"'  "+
					
							"");
						
					while(rs.next()){
							m_active_tot=rs.getDouble(1);
							m_inactive_tot=0;
					}
					
					table_sl.addCell(new Paragraph(m_date_to,BoldFont));
					table_sl.addCell(new Paragraph("Closing Balance",BoldFont));
					table_sl.addCell(new Paragraph("",catFont));
					table_sl.addCell(new Paragraph("",catFont));
					table_sl.addCell(new Paragraph("",catFont));
					table_sl.addCell(new Paragraph("",catFont));
					table_sl.addCell(new Paragraph(nf.format(m_active_tot),BoldFont));
					table_sl.addCell(new Paragraph(nf.format(m_inactive_tot),BoldFont));

				document.add(table_sl);
						
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Any errors or discrepancies should be reported to the ",catFont));
				p.add(new Chunk(" Orient Factor ",BoldFont));
				p.add(new Chunk(" within 14 days of receipt of this statement.",catFont));	
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
						
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);		
				
				// =========================== Sales Ledger Section End ===========================
				
				
				// =========================== Invoice Batch Details Start ========================
				
				document.newPage();

				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
			    topic=new Chunk(" NOTIFICATION SCHEDULE - INVOICE BATCH ");
				topic.setFont(BoldFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				topic=new Chunk(" PRINT DATE/TIME "+m_today);
				topic.setFont(catFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Dear Sir, ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" We acknowledge receipt of under mentioned notification schedule(Invoice Batch) ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);

				
				PdfPTable table_nsib = new PdfPTable(5); 
				PdfPCell defaultCell_nsib = table_nsib.getDefaultCell();
				defaultCell_nsib.setBorder(PdfPCell.NO_BORDER);
				table_nsib.setWidths(new int[]{ 3, 2, 1, 1, 1});
				table_nsib.setWidthPercentage(100);
				
				table_nsib.addCell(new Paragraph("Debtor Name",BoldFont));
				table_nsib.addCell(new Paragraph("Batch No",BoldFont));
				table_nsib.addCell(new Paragraph("Invoice No",BoldFont));
				table_nsib.addCell(new Paragraph("Invoice Date",BoldFont));
				table_nsib.addCell(new Paragraph("Invoice Amount",BoldFont));
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				
				double m_total=0;
						
						rs = stmt.executeQuery("SELECT A.BATCH_NO,"+//1
						""+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2
						" A.INVOICE_NO,"+//3
						" A.INVOICE_AMOUNT,"+//4
		                " TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY') "+//5
		  			    " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
						" WHERE A.BATCH_NO=B.BATCH_NO "+
						" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND B.FACILITY_NO='"+m_facility_no+"' "+
						" AND B.CLIENT_CODE='"+m_client_no+"' "+
						" ORDER BY A.BATCH_NO ");
						
						
				while(rs.next()){

					table_nsib.addCell(new Paragraph(rs.getString(2),catFont));
					table_nsib.addCell(new Paragraph(rs.getString(1),catFont));
					table_nsib.addCell(new Paragraph(rs.getString(3),catFont));
					table_nsib.addCell(new Paragraph(rs.getString(5),catFont));
					table_nsib.addCell(new Paragraph(nf.format(rs.getDouble(4)),catFont));
					
					m_total=m_total+rs.getDouble(4);
				}	
				
				table_nsib.addCell(new Paragraph("",catFont));
				table_nsib.addCell(new Paragraph("",catFont));
				table_nsib.addCell(new Paragraph("",catFont));
				table_nsib.addCell(new Paragraph("",catFont));
				table_nsib.addCell(new Paragraph(nf.format(m_total),BoldFont));
				
				
				document.add(table_nsib);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" If you have any queries pertaining to the above please do not hesitate to contact the undersigned. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Thanking you ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Yours faithfully ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Factoring Division of Lakderana Investments Limited ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" ..................... ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph("Authorized Signatory",catFont));
				p.add(new Paragraph("Executive Operations",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);	
				
				// =========================== Invoice Batch Details End ==========================
				
				
				// ================== Start Invoice Settlement Details ============================
				
				document.newPage();

				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
			    topic=new Chunk(" NOTIFICATION SCHEDULE - INVOICE BATCH ");
				topic.setFont(BoldFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				topic=new Chunk(" PRINT DATE/TIME "+m_today);
				topic.setFont(catFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Dear Sir, ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" We inform to you that the following invoice amounts were settled against your payments. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				PdfPTable table_isd = new PdfPTable(9); 
				PdfPCell defaultCell_isd = table_isd.getDefaultCell();
				defaultCell_isd.setBorder(PdfPCell.NO_BORDER);
				table_isd.setWidths(new int[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1});
				table_isd.setWidthPercentage(100);
				
				table_isd.addCell(new Paragraph("Settle Date",BoldFont));
				table_isd.addCell(new Paragraph("Debtor Name",BoldFont));
				table_isd.addCell(new Paragraph("Chq. No",BoldFont));
				table_isd.addCell(new Paragraph("Branch Name",BoldFont));
				table_isd.addCell(new Paragraph("Chq. Amt",BoldFont));
				table_isd.addCell(new Paragraph("Invoice No",BoldFont));
				table_isd.addCell(new Paragraph("Invoice Amt",BoldFont));
				table_isd.addCell(new Paragraph("Sett. Amt",BoldFont));
				table_isd.addCell(new Paragraph("Bal. Amt",BoldFont));
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
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
								" ("+m_schema_name+".FA_CLIENT_PRE_INV_BAL('"+m_client_no+"','"+m_facility_no+"',A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'))-A.ALLOCATED_AMOUNT) "+
								" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+
								" WHERE A.INVOICE_NO=B.INVOICE_SEQ_NO "+
								" AND B.BATCH_NO=C.BATCH_NO "+
								" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
								" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
								" AND C.FACILITY_NO='"+m_facility_no+"' "+
								" AND C.CLIENT_CODE='"+m_client_no+"' "+
								" AND A.ALLOCATED_AMOUNT>0 "+
								" ORDER BY A.ALLOCATED_DATE ");
				
				
				while(rs.next()){
					
					table_isd.addCell(new Paragraph(rs.getString(1),catFont));
					table_isd.addCell(new Paragraph(rs.getString(8),catFont));
					table_isd.addCell(new Paragraph(rs.getString(4),catFont));
					table_isd.addCell(new Paragraph(rs.getString(5),catFont));
					table_isd.addCell(new Paragraph(nf.format(rs.getDouble(11)),catFont));
					table_isd.addCell(new Paragraph(rs.getString(6),catFont));
					table_isd.addCell(new Paragraph(nf.format(rs.getDouble(9)),catFont));
					table_isd.addCell(new Paragraph(nf.format(rs.getDouble(3)),catFont));
					table_isd.addCell(new Paragraph(nf.format(rs.getDouble(12)),catFont));
					
					
				}
				
				
				document.add(table_isd);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" If you have any queries pertaining to the above please do not hesitate to contact the undersigned. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Thanking you ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Yours faithfully ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Factoring Division of Lakderana Investments Limited ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" ..................... ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph("Authorized Signatory",catFont));
				p.add(new Paragraph("Executive Operations",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);

				// ================== End Invoice Settlement Details ==============================
				

				// ================== Start Invoice Reassignments =================================
				
				document.newPage();

				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
			    topic=new Chunk(" LETTER OF REASSIGNED OF THE INVOICES ");
				topic.setFont(BoldFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				topic=new Chunk(" PRINT DATE/TIME "+m_today);
				topic.setFont(catFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Dear Sir, ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" We inform to you that the following unsettled invoices amounts which received under notification schedules have been re-assigned and details given below. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				PdfPTable table_ri = new PdfPTable(9); 
				PdfPCell defaultCell_ri = table_ri.getDefaultCell();
				defaultCell_ri.setBorder(PdfPCell.NO_BORDER);
				table_ri.setWidths(new int[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1});
				table_ri.setWidthPercentage(100);
				
				table_ri.addCell(new Paragraph("Adj. Date",BoldFont));
				table_ri.addCell(new Paragraph("Debtor Name",BoldFont));
				table_ri.addCell(new Paragraph("Invoice No",BoldFont));
				table_ri.addCell(new Paragraph("Invoice Date",BoldFont));
				table_ri.addCell(new Paragraph("Batch No",BoldFont));
				table_ri.addCell(new Paragraph("Reassign. Amt",BoldFont));
				table_ri.addCell(new Paragraph("Invoice Amt",BoldFont));
				table_ri.addCell(new Paragraph("Sett. Amt",BoldFont));
				table_ri.addCell(new Paragraph("Comments",BoldFont));
				
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
								" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
								" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
								" AND C.FACILITY_NO='"+m_facility_no+"' "+
								" AND C.CLIENT_CODE='"+m_client_no+"' "+
								" ORDER BY A.ADJUST_DATE ");
		
						while(rs.next()){
							
							table_ri.addCell(new Paragraph(rs.getString(1),catFont));
							table_ri.addCell(new Paragraph(rs.getString(12)+" "+rs.getString(2),catFont));
							table_ri.addCell(new Paragraph(rs.getString(3),catFont));
							table_ri.addCell(new Paragraph(rs.getString(5),catFont));
							table_ri.addCell(new Paragraph(rs.getString(6),catFont));
							table_ri.addCell(new Paragraph(nf.format(rs.getDouble(8)),catFont));
							table_ri.addCell(new Paragraph(nf.format(rs.getDouble(4)),catFont));
							table_ri.addCell(new Paragraph(nf.format(rs.getDouble(13)),catFont));
							table_ri.addCell(new Paragraph(rs.getString(10)+" "+rs.getString(11),catFont));
							
						}
				
				
				
				document.add(table_ri);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" If you have any queries pertaining to the above please do not hesitate to contact the undersigned. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Thanking you ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Yours faithfully ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Factoring Division of Lakderana Investments Limited ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" ..................... ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph("Authorized Signatory",catFont));
				p.add(new Paragraph("Executive Operations",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				// ================== End Invoice Reassignments ===================================
				
				// ================== Start Invoice Adjustment Details ============================
				
				document.newPage();

				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
			    topic=new Chunk(" INVOICE ADJUSTMENT REPORT ");
				topic.setFont(BoldFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				topic=new Chunk(" PRINT DATE/TIME "+m_today);
				topic.setFont(catFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Dear Sir, ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" We inform to you that the following adjustments have been made against your sales ledger.The information as follows. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				PdfPTable table_iar = new PdfPTable(8); 
				PdfPCell defaultCell_iar = table_iar.getDefaultCell();
				defaultCell_iar.setBorder(PdfPCell.NO_BORDER);
				table_iar.setWidths(new int[]{ 1, 1, 1, 1, 1, 1, 1, 1});
				table_iar.setWidthPercentage(100);
				
				table_iar.addCell(new Paragraph("Adj. Date",BoldFont));
				table_iar.addCell(new Paragraph("Debtor Name",BoldFont));
				table_iar.addCell(new Paragraph("Invoice No",BoldFont));
				table_iar.addCell(new Paragraph("Invoice Date",BoldFont));
				table_iar.addCell(new Paragraph("Batch No",BoldFont));
				table_iar.addCell(new Paragraph("Invoice Amt",BoldFont));
				table_iar.addCell(new Paragraph("Sett. Amt",BoldFont));
				table_iar.addCell(new Paragraph("Comments",BoldFont));
				
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
								" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
								" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
								" AND C.FACILITY_NO='"+m_facility_no+"' "+
								" AND C.CLIENT_CODE='"+m_client_no+"' "+
								" ORDER BY A.ADJUST_DATE ");
		
						while(rs.next()){
							
							table_iar.addCell(new Paragraph(rs.getString(1),catFont));
							table_iar.addCell(new Paragraph(rs.getString(12),catFont));
							table_iar.addCell(new Paragraph(rs.getString(3),catFont));
							table_iar.addCell(new Paragraph(rs.getString(5),catFont));
							table_iar.addCell(new Paragraph(rs.getString(6),catFont));
							table_iar.addCell(new Paragraph(nf.format(rs.getDouble(4)),catFont));
							table_iar.addCell(new Paragraph(nf.format(rs.getDouble(8))+"("+rs.getString(9)+")",catFont));
							table_iar.addCell(new Paragraph(rs.getString(10)+" "+rs.getString(11),catFont));
							
						}
						
					document.add(table_iar);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" If you have any queries pertaining to the above please do not hesitate to contact the undersigned. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Thanking you ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Yours faithfully ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Factoring Division of Lakderana Investments Limited ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" ..................... ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph("Authorized Signatory",catFont));
				p.add(new Paragraph("Executive Operations",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);		
						
						
				
				// ================== End Invoice Adjustment Details ==============================
				
				
				// ================== Start Unallocated Funds =====================================
				
				document.newPage();

				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
			    topic=new Chunk(" Statement of Unallocated Fund ");
				topic.setFont(BoldFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				topic=new Chunk(" PRINT DATE/TIME "+m_today);
				topic.setFont(catFontHead);
				p.add(topic);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" Dear Sir, ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk(" We inform to you that the following adjustments have been made against your sales ledger.The information as follows. ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(" "));
				document.add(p);
				
				PdfPTable table_uf = new PdfPTable(8); 
				PdfPCell defaultCell_uf = table_uf.getDefaultCell();
				defaultCell_uf.setBorder(PdfPCell.NO_BORDER);
				table_uf.setWidths(new int[]{ 1, 1, 1, 1, 1, 1, 1, 1});
				table_uf.setWidthPercentage(100);
				
				table_uf.addCell(new Paragraph("Date",BoldFont));
				table_uf.addCell(new Paragraph("Client/Debtor Name",BoldFont));
				table_uf.addCell(new Paragraph("Type",BoldFont));
				table_uf.addCell(new Paragraph("Chq No",BoldFont));
				table_uf.addCell(new Paragraph("Chq Date",BoldFont));
				table_uf.addCell(new Paragraph("Chq Amt",BoldFont));
				table_uf.addCell(new Paragraph("Allocated Amt",BoldFont));
				table_uf.addCell(new Paragraph("Excess Amt",BoldFont));
				
				
				rs = stmt.executeQuery("  "+				
								" SELECT  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ // 1
							        " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+ // 2
							        " A.SETTLE_MODE, "+ // 3
									" NVL(A.CHEQUE_NO,'-'), "+ // 4
									" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+ // 5
							        " A.REC_AMOUNT, "+ // 6
							        " A.ALLO_AMOUNT, "+ // 7
							        " A.REC_AMOUNT - A.ALLO_AMOUNT - A.BALANCE_AMOUNT EXCESS "+ // 8
							            " FROM   "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
							            " WHERE  A.FACILITY_NO LIKE '%"+m_facility_no+"%' "+
							            " AND    A.CLIENT_CODE LIKE '%"+m_client_no+"%' "+
							            " AND    TRUNC(A.ENT_DATE) >=  TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+ 
										" AND    TRUNC(A.ENT_DATE) <=  TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+	
										//" AND (A.REC_AMOUNT - A.ALLO_AMOUNT - A.BALANCE_AMOUNT) > 0  "+ // added by udara on 03-02-2012
						" AND (A.REC_AMOUNT - A.ALLO_AMOUNT) > 0  "+ // added by udara on 03-02-2012
										" ");

						while(rs.next()){
							
							table_uf.addCell(new Paragraph(rs.getString(1),catFont));
							table_uf.addCell(new Paragraph(rs.getString(2),catFont));
							table_uf.addCell(new Paragraph(rs.getString(3),catFont));
							table_uf.addCell(new Paragraph(rs.getString(4),catFont));
							table_uf.addCell(new Paragraph(rs.getString(5),catFont));
							table_uf.addCell(new Paragraph(nf.format(rs.getDouble(6)),catFont));
							table_uf.addCell(new Paragraph(nf.format(rs.getDouble(7)),catFont));
							table_uf.addCell(new Paragraph(nf.format(rs.getDouble(8)),catFont));
							
							
						}
				
				
				document.add(table_uf);
				
				
				// ================== End Unallocated Funds =======================================
				
				
				
				// ************************************************************************************
				// ********************************** PDF BODY END ************************************
				// ************************************************************************************
				
				document.close();
			}
			catch (Exception ex){
				out.println("err"+ex.toString());														
			}
			
			
			// Added below by Udara on 23-02-2012
			
			// ****************************************************************************************
			// ********************************** SENDING E-MAIL SECTION ******************************
			// ****************************************************************************************
						
			try{

			}
			catch (Exception exn){				
				out.println("err"+exn.toString());					
			}
			
			// ****************************************************************************************
			// ********************************** END SENDING E-MAIL SECTION **************************
			// ****************************************************************************************
			
			// End by Udara on 23-02-2012
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_RE_client_stmt_report_new';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			out.flush();
			
		}
		catch (Exception ex) {
			try{
				conn.rollback();
			}
			catch(Exception e){
			
			}
			out.println("Error:"+ex.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			out.close();
		}
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
}







