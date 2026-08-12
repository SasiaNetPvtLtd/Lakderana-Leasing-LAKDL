import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.print.*;
import java.text.*;
import javax.print.*;
import java.net.URL;

class Current_Account_Statement_bulk_print implements Printable {

public String m_facility_no;
public String m_client_no;
public String m_date_from;
public String m_date_to;

java.text.NumberFormat nf;

public Current_Account_Statement_bulk_print(String mm_facility_no,String mm_client_code,String mm_start_date,String mm_end_date){

nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_facility_no=mm_facility_no;
m_client_no=mm_client_code;
m_date_from=mm_start_date;
m_date_to=mm_end_date;
}

public Current_Account_Statement_bulk_print(){

}

public int print (Graphics g, PageFormat pageFormat, int page) {
	
	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (), pageFormat.getImageableY ());
	
	try{
	
		LAKDL_print_methods m_print_method=new LAKDL_print_methods();
		
		Connection conn;
		ResultSet rs,rs1,rs2,rs3;
		Statement stmt,stmt1,stmt2,stmt3;
		int m_col_hight=0;
		int m_page_count=1;
		
		int m_num=0;
		int i=0;
		String m_value="";
			
		String m_schema_name = m_print_method.schema_name.trim();		
		
		conn=m_print_method.get_print_connection();
		stmt=conn.createStatement();
		stmt1=conn.createStatement();
		stmt2=conn.createStatement();
		stmt3=conn.createStatement();
		
		g2d.setPaint (Color.black);
		
		g2d.setStroke (new BasicStroke (5));
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		double titleX = (pageFormat.getImageableWidth()/2);
		double titleY = 72/2;
		 
		Font titleFont =new Font ("Arial",Font.PLAIN,7);
		g2d.setFont(titleFont);
		
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
			
		//------------------------------------------------------------
		
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

		while(rs1.next()){
			m_vat_status=rs1.getString(8);
				
			g2d.drawString(m_today,42,108);
			g2d.drawString(rs1.getString(7),42,118);
			g2d.drawString(rs1.getString(2),42,128);
			g2d.drawString(rs1.getString(3),42,138);
			g2d.drawString(rs1.getString(4),42,148);
			g2d.drawString(rs1.getString(5),42,158);
			g2d.drawString("CLIENT CODE:"+m_client_no,42,168);
			g2d.drawString("FACILITY NO:"+m_facility_no,42,178);
			g2d.drawString("REPORT PERIOD: "+m_date_from+" - "+m_date_to,42,188);
			g2d.drawString("CURRENT ACCOUNT STATEMENT",612/2,198);
			g2d.drawString("PRINT DATE/TIME "+m_today,612/2,208);
			
			
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
			" CATYPE "+//5
			" FROM( "+
			" SELECT 'ADJ' CTYPE,'CLIENT ADJUSTMENTS' CDESC,A.ADJUST_AMOUNT CAMT,A.ADJUST_DATE CDATE,A.ADJUST_TYPE CATYPE "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A "+
			" WHERE ADJUST_CATEGORY='CLA' "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" UNION ALL "+
			" SELECT 'PAY' CTYPE,'CLIENT PAYMENTS' CDESC,A.PAYMENT_AMOUNT CAMT,A.PAY_DATE CDATE,'DR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
			" WHERE A.PAY_STATUS IN('CONF','PRINT','DISB') "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" UNION ALL "+
			" SELECT 'CHER' CTYPE,DECODE(A.FEE_CODE,'VAT','V A T',"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE)) CDESC,SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1)) CAMT,A.EFF_DATE CDATE,'DR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
			" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" GROUP BY A.FEE_CODE,A.EFF_DATE "+
			" UNION ALL "+
			" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
			" WHERE A.REC_STATUS='Y'  "+
			" AND A.RECEIPT_TYPE<>'SSH' "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" AND A.ALLO_AMOUNT>0 "+
			" UNION ALL "+
			" SELECT 'SETTLE' CTYPE,'NON SALES-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.BALANCE_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
			" WHERE A.REC_STATUS='Y'  "+
			" AND A.RECEIPT_TYPE<>'SSH' "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" AND A.BALANCE_AMOUNT>0 "+
			" UNION ALL "+
			" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS' CDESC,SUM(A.ALLO_AMOUNT) CAMT,B.EFF_VALDATE CDATE,'CR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
			" WHERE B.SUS_REF_NO=A.SETTLE_SCHDULE_REF_NO "+
			" AND B.REC_STATUS<>'Y'  "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" GROUP BY B.RECEIPT_NO,B.EFF_VALDATE "+
			" ) "+
			" ORDER BY CDATE,CDESC ");
			}
			else{
		
			rs = stmt.executeQuery (" SELECT CTYPE,"+//1
			" INITCAP(CDESC),"+//2
			" NVL(CAMT,0),"+//3
			" TO_CHAR(CDATE,'DD-MM-YYYY'),"+//4
			" CATYPE "+//5
			" FROM( "+
			" SELECT 'ADJ' CTYPE,'CLIENT ADJUSTMENTS' CDESC,A.ADJUST_AMOUNT CAMT,A.ADJUST_DATE CDATE,A.ADJUST_TYPE CATYPE "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A "+
			" WHERE ADJUST_CATEGORY='CLA' "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" UNION ALL "+
			" SELECT 'PAY' CTYPE,'CLIENT PAYMENTS' CDESC,A.PAYMENT_AMOUNT CAMT,A.PAY_DATE CDATE,'DR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
			" WHERE A.PAY_STATUS IN('CONF','PRINT','DISB') "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" UNION ALL "+
			" SELECT 'CHER' CTYPE,"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE) CDESC,(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))+(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))*("+m_tax+"/100))) CAMT,A.EFF_DATE CDATE,'DR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B "+
			" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" AND A.FEE_CODE=B.FEE_CODE "+
			" AND B.TAX_APPLICABILITY='Y' "+
			" GROUP BY A.FEE_CODE,A.EFF_DATE "+
			" UNION ALL "+
			" SELECT 'CHER' CTYPE,"+m_schema_name+".FA_GET_FEE_NAME(A.FEE_CODE) CDESC,(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))) CAMT,A.EFF_DATE CDATE,'DR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B "+
			" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" AND A.FEE_CODE=B.FEE_CODE "+
			" AND B.TAX_APPLICABILITY='N' "+
			" GROUP BY A.FEE_CODE,A.EFF_DATE "+
			" UNION ALL "+
			" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.ALLO_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
			" WHERE A.REC_STATUS='Y'  "+
			" AND A.RECEIPT_TYPE<>'SSH' "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" AND A.ALLO_AMOUNT>0 "+
			" UNION ALL "+
			" SELECT 'SETTLE' CTYPE,'NON SALES-' || DECODE(A.SETTLE_MODE,'CASH','CASH',A.CHEQUE_NO) CDESC,A.BALANCE_AMOUNT CAMT,A.RECON_DATE CDATE,'CR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
			" WHERE A.REC_STATUS='Y'  "+
			" AND A.RECEIPT_TYPE<>'SSH' "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" AND A.BALANCE_AMOUNT>0 "+
			" UNION ALL "+
			" SELECT 'SETTLE' CTYPE,'CLIENT SETTLELEMNTS' CDESC,SUM(A.ALLO_AMOUNT) CAMT,B.EFF_VALDATE CDATE,'CR' CATYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
			" WHERE B.SUS_REF_NO=A.SETTLE_SCHDULE_REF_NO "+
			" AND B.REC_STATUS<>'Y'  "+
			" AND A.FACILITY_NO='"+m_facility_no+"' "+
			" AND A.CLIENT_CODE='"+m_client_no+"' "+
			" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_rpt_start_date+"','DD-MM-YYYY')"+
			" AND TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
			" GROUP BY B.RECEIPT_NO,B.EFF_VALDATE "+
			" ) "+
			" ORDER BY CDATE,CDESC ");
			}
			double m_run_balance=0;
			
			g2d.drawString("Date",42,218);
			g2d.drawString("Description",80,218);
			g2d.drawString("DR",250,218);
			g2d.drawString("CR",350,218);
			g2d.drawString("Balance (Rs.)",450,218);
			
			m_col_hight=230;
			
			g2d.drawString(m_rpt_start_date,42,m_col_hight);
			g2d.drawString("Opening Balance",80,m_col_hight);
			g2d.drawString("-",250,m_col_hight);
			g2d.drawString("-",350,m_col_hight);
			
		
			if(m_op_balance>=0){
					//-----------------------------------
					m_num=0;i=0;m_value="";
					m_value=nf.format(m_op_balance);
					while(i<m_value.length()){
					m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
					i++;
					}
					//----------------------------------
					g2d.drawString(nf.format(m_op_balance),450,m_col_hight);
			}
			else{
					//-----------------------------------
					m_num=0;i=0;m_value="";
					m_value="("+nf.format(m_op_balance*-1)+")";
					while(i<m_value.length()){
					m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
					i++;
					}
					//----------------------------------
					g2d.drawString("("+nf.format(m_op_balance*-1)+")",450,m_col_hight);
			}
			m_col_hight=m_col_hight+10;
			m_run_balance=m_run_balance+m_op_balance;
			

			while(rs.next()){
				g2d.drawString(rs.getString(4),42,m_col_hight);
				g2d.drawString(rs.getString(2),80,m_col_hight);

				if(rs.getString(5).equals("DR")){
					if(rs.getDouble(3)>=0){
						//-----------------------------------
						m_num=0;i=0;m_value="";
						m_value=nf.format(rs.getDouble(3));
						while(i<m_value.length()){
						m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
						i++;
						}
						//----------------------------------
						g2d.drawString(nf.format(rs.getDouble(3)),250,m_col_hight);
					}
					else{
						//-----------------------------------
						m_num=0;i=0;m_value="";
						m_value="("+nf.format(rs.getDouble(3)*-1)+")";
						while(i<m_value.length()){
						m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
						i++;
						}
						//----------------------------------
						g2d.drawString("("+nf.format(rs.getDouble(3)*-1)+")",250,m_col_hight);
					}
					g2d.drawString("-",350,m_col_hight);
					m_run_balance=m_run_balance+rs.getDouble(3);
				}
				else{
					g2d.drawString("-",250,m_col_hight);
					if(rs.getDouble(3)>=0){
						//-----------------------------------
						m_num=0;i=0;m_value="";
						m_value=nf.format(rs.getDouble(3));
						while(i<m_value.length()){
						m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
						i++;
						}
						//----------------------------------
						g2d.drawString(nf.format(rs.getDouble(3)),350,m_col_hight);
					}
					else{
						//-----------------------------------
						m_num=0;i=0;m_value="";
						m_value="("+nf.format(rs.getDouble(3)*-1)+")";
						while(i<m_value.length()){
						m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
						i++;
						}
						//----------------------------------
						g2d.drawString("("+nf.format(rs.getDouble(3)*-1)+")",350,m_col_hight);
					}
					m_run_balance=m_run_balance-rs.getDouble(3);
				}
				
				if(m_run_balance>=0){
					//-----------------------------------
					m_num=0;i=0;m_value="";
					m_value=nf.format(m_run_balance);
					while(i<m_value.length()){
					m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
					i++;
					}
					//----------------------------------
					g2d.drawString(nf.format(m_run_balance),450,m_col_hight);
				}
				else{
					//-----------------------------------
					m_num=0;i=0;m_value="";
					m_value="("+nf.format(m_run_balance*-1)+")";
					while(i<m_value.length()){
					m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
					i++;
					}
					//----------------------------------
					g2d.drawString("("+nf.format(m_run_balance*-1)+")",450,m_col_hight);
				}
				m_col_hight=m_col_hight+10;
				
				
				if(m_col_hight>800){//handele next page event
					m_col_hight=((864*m_page_count)-m_col_hight)+108;
					m_page_count++;
				}
				
			}
			
			g2d.drawString(m_date_to,42,m_col_hight);
			g2d.drawString("Interest Amount as at "+m_date_to+"",80,m_col_hight);
			//-----------------------------------
			m_num=0;i=0;m_value="";
			m_value=nf.format(m_int_normal);
			while(i<m_value.length()){
			m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
			i++;
			}
			//----------------------------------
			g2d.drawString(nf.format(m_int_normal),250,m_col_hight);
			g2d.drawString("-",350,m_col_hight);
			m_run_balance=m_run_balance+m_int_normal;
			//-----------------------------------
			m_num=0;i=0;m_value="";
			m_value=nf.format(m_run_balance);
			while(i<m_value.length()){
			m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
			i++;
			}
			//----------------------------------
			if(m_run_balance>0){
				//-----------------------------------
				m_num=0;i=0;m_value="";
				m_value=nf.format(m_run_balance);
				while(i<m_value.length()){
				m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
				i++;
				}
				//----------------------------------
				g2d.drawString(nf.format(m_run_balance),450,m_col_hight);
			}
			else{
				//-----------------------------------
				m_num=0;i=0;m_value="";
				m_value="("+nf.format(m_run_balance*-1)+")";
				while(i<m_value.length()){
				m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
				i++;
				}
				//----------------------------------
				g2d.drawString("("+nf.format(m_run_balance*-1)+")",450,m_col_hight);
			}
			m_col_hight=m_col_hight+10;
			
			g2d.drawString(m_date_to,42,m_col_hight);
			g2d.drawString("Overpaid Interest Amount as at "+m_date_to+"",80,m_col_hight);
			//-----------------------------------
			m_num=0;i=0;m_value="";
			m_value=nf.format(m_int_over);
			while(i<m_value.length()){
			m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
			i++;
			}
			//----------------------------------
			g2d.drawString(nf.format(m_int_over),250,m_col_hight);
			g2d.drawString("-",350,m_col_hight);
			m_run_balance=m_run_balance+m_int_over;
			//-----------------------------------
			if(m_run_balance>0){
				//-----------------------------------
				m_num=0;i=0;m_value="";
				m_value=nf.format(m_run_balance);
				while(i<m_value.length()){
				m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
				i++;
				}
				//----------------------------------
				g2d.drawString(nf.format(m_run_balance),450,m_col_hight);
			}
			else{
				//-----------------------------------
				m_num=0;i=0;m_value="";
				m_value="("+nf.format(m_run_balance*-1)+")";
				while(i<m_value.length()){
				m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
				i++;
				}
				//----------------------------------
				g2d.drawString("("+nf.format(m_run_balance*-1)+")",450,m_col_hight);
			}
			m_col_hight=m_col_hight+10;
			
			g2d.drawString(m_date_to,42,m_col_hight);
			g2d.drawString("Closing Balance "+m_date_to+"",80,m_col_hight);
			g2d.drawString("-",250,m_col_hight);
			g2d.drawString("-",350,m_col_hight);

			if(m_run_balance>0){
				//-----------------------------------
				m_num=0;i=0;m_value="";
				m_value=nf.format(m_run_balance);
				while(i<m_value.length()){
				m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
				i++;
				}
				//----------------------------------
				g2d.drawString(nf.format(m_run_balance),450,m_col_hight);
			}
			else{
				//-----------------------------------
				m_num=0;i=0;m_value="";
				m_value="("+nf.format(m_run_balance*-1)+")";
				while(i<m_value.length()){
				m_num=m_num+fontMetrics.charWidth(m_value.charAt(i));
				i++;
				}
				//----------------------------------
				g2d.drawString("("+nf.format(m_run_balance*-1)+")",450,m_col_hight);
			}
			m_col_hight=m_col_hight+10;
			
	
			g2d.drawString("Any errors or discrepancies should be reported to the Orient Factor within 14 days of receipt of this statement",42,m_col_hight);

		}
	}
	catch(Exception e){
	}
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	}
}
