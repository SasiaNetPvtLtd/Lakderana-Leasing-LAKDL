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

class Print_PDC_Book implements Printable {

public String m_receipt_num="";
public int mm_page_count;
public int mm_page_size;
public int mm_page_size2;

java.text.NumberFormat nf;

public Print_PDC_Book(String m_rec){
nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_receipt_num=m_rec;

}

public int print (Graphics g,PageFormat pageFormat, int page) {
	
	String m_client_no="";
	String m_client_code="";
	String m_due_date="";
	
	String m_client_name="";
	String m_client_add1="";
	String m_client_add2="";
	String m_city_desc="";
	String m_vat_reg_no="";
	String m_payamount="";
	String m_payamountword="";
	String m_add1="";
	String m_add2="";
	String m_city="";
	String m_html_client_url="";
	String m_Letter_date="";
	
	String m_vdate="";
	String m_ref_no="";
	String m_pay_type="";
	String m_settle_mode="";
	
	
	String m_orient_name="";
	String m_orient_add1="";
	String m_orient_add2="";
	String m_orient_city_name="";
	String m_orient_tel_no="";
	String m_orient_fax_no="";
	String m_vat_precentage="";			
	String m_LAKDL_vat_no="";	
	String m_orient_email="";
	
	String m_Cheque_no   = "";
	String m_Bank_Branch = "";
	String m_Sett_ref    = "";
	String m_Sett_mod    = "";
	double m_amount      = 0;
	String m_amount_wd   ="";
	double sum_amount    =0.00; 
	
	String m_receipt_no  ="";
	String m_third_party_name="",m_third_party_add="",m_comments="",m_cheque_cash_type="",m_payer_branch="";
	String m_print_name="",m_print_address="";
	String m_address="";

	
	
	int m_y_position=0;
	int m_row_space =0;

	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (),pageFormat.getImageableY());
	
	try{
	
	LAKDL_print_methods m_print_method=new LAKDL_print_methods();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
	Connection conn;
	ResultSet rs,rs1,rs2,rs3;
	Statement stmt,stmt1,stmt2,stmt3;
	
	conn=m_print_method.get_print_connection();
	
	
					
	stmt=conn.createStatement();
	stmt1=conn.createStatement();
	stmt2=conn.createStatement();
	stmt3=conn.createStatement();
	
	//A4 page height -- 835.2
	//A4 page weidth -- 590.4
	
	// for(int z=0;z<=3;z++){  
				// m_receipt_num="SR0080827-26902";
	//		   m_y_position=m_y_position+(z*792);  
					
	 rs = stmt.executeQuery(" SELECT "+
		    " COMPANY_NAME, "+
		    " ADDRESS1, "+
		    " ADDRESS2, "+
		    " CITY, "+
		    " TEL_NO, "+
		    " FAX_NO,  "+
				" VAT_RATE, "+
				" VAT_REG_NO, "+
				" EMAIL "+
				" FROM LAKDL.AF_CO_MAS_COMPANY_DETAILS ");

		boolean  more = rs.next();		
						
						if(more)
						{
						m_orient_name=rs.getString(1);
						m_orient_add1=rs.getString(2);
						m_orient_add2=rs.getString(3);
						m_orient_city_name=rs.getString(4);
						m_orient_tel_no=rs.getString(5);
						m_orient_fax_no=rs.getString(6);
						m_vat_precentage=rs.getString(7);			
						m_LAKDL_vat_no=rs.getString(8);
						m_orient_email=rs.getString(9);
						}
	      //m_receipt_num="SR0080827-26902";
	      System.out.println("m_receipt_num="+m_receipt_num.toString()+"  m_y_position="+m_y_position);
								
			  
			   //m_row_space =25;

		
				rs = stmt.executeQuery ("SELECT CLIENT_CODE,TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),NVL(OTH_COMMENTS,'-') ,NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-') ,NVL(THIRD_PARTY_NAME,'-'), NVL(THIRD_PARTY_ADDRESS,'-') FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE REC_NO=UPPER('"+m_receipt_num+"') ");							
				more = rs.next();
								
				if(more){
				m_client_no=rs.getString(1);
				m_Letter_date=rs.getString(2);
				m_comments=rs.getString(3);
				m_cheque_cash_type=rs.getString(4);
				m_payer_branch=rs.getString(5);
				m_third_party_name=rs.getString(6);
				m_third_party_add =rs.getString(7);
				}
				
				
				rs1 = stmt1.executeQuery (	" SELECT "+
  		  " CLIENT_CODE, "+
  		  " UPPER(FULL_NAME), "+
  		  " NVL(UPPER(ADDRESS1),'ADD1'), "+
  		  " NVL(UPPER(ADDRESS2),'ADD2'), "+
  		  " NVL(UPPER(AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ,"+
				" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
				" FROM AF_CO_MAS_CLIENT "+
  		  " WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				more = rs1.next();
				if(more){
				m_client_name=rs1.getString(2);
				m_add1=rs1.getString(3);
				m_add2=rs1.getString(4);
				m_city_desc=rs1.getString(5);
				m_vat_reg_no=rs1.getString(6);
				}
				
				if(m_add1.equals("ADD1")){
				m_address="";
				}
				else{
				m_address=m_add1;
				}
				if(!m_add2.equals("ADD2")){
				m_address=m_address+","+m_add2;
				}
			
				if(!m_third_party_name.equals("-")){
				m_print_name=m_third_party_name;
				m_print_address=m_third_party_add;
				}
				else{
				m_print_name=m_client_name;
				m_print_address=m_address;
				}
        

 	       rs1 = stmt1.executeQuery (" SELECT  NVL(REC_NO,'-'), "+
 		      " DECODE(SETTLE_MODE,'CASH','Cash',CHEQUE_NO), "+
					" NVL(PAYER_BRANCH_CODE,'-'), "+
					" NVL(LAKDL.AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL(LAKDL.AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
					" NVL(REC_AMOUNT,0), "+
					" DECODE(SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash','DIR_DEP','Derect Deposit','STD_ORD','Standing Order'), "+
					" NVL(PAYER_ACC_NO,'-'), "+
					" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'), "+
					" CLIENT_CODE "+
					" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_num+"%') ");
	      				
				more = rs1.next();	
				
				if(more){
				  m_Cheque_no   = rs1.getString(2);
				  m_Bank_Branch = rs1.getString(4);
				  m_amount      = rs1.getDouble(5);
					m_amount_wd   = rs1.getString(5);
					m_Sett_mod    = rs1.getString(6); 
					m_Sett_ref    = rs1.getString(2);
				}
				
				
			double m_over_payment=0;
			rs2 = stmt2.executeQuery("SELECT NVL(LAKDL.AF_CO_GET_REC_UNALLO_AMOUNT_2('"+m_receipt_num+"'),0) FROM DUAL ");
			more = rs2.next();
			if(more){
			m_over_payment=rs2.getDouble(1);
			}
				
			
			
			String Inv_det =	" SELECT FINANCE_NO,CHEQUE_NO,/*PAYER_BRANCH_CODE,DESCREPTION, */"+
      " NVL(LAKDL.AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL(LAKDL.AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
			" DESCREPTION , "+
      " SETTELED_AMOUNT,ENT_DATE "+
			" FROM "+
			" ( SELECT C.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, "+
			" NVL(LAKDL.AF_CO_GET_SUB_CHARG_DESC(C.INVOICE_TYPE),LAKDL.AF_CO_GET_INVOICE_DESCR(C.INVOICE_TYPE))||'  '||B.INVOICE_NO DESCREPTION, "+
			" SETTELED_AMOUNT,C.ENT_DATE "+
			" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT A, "+
			" LAKDL.AF_CO_PRO_INVOICE_DETAILS B, "+
			" LAKDL.AF_CO_PRO_INVOICE C "+
			" WHERE A.REC_NO = B.RECEIPT_NO AND "+
			" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND A.REC_NO LIKE UPPER('"+m_receipt_num+"%') "+
			" AND B.INVOICE_NO = C.INVOICE_NO "+
			" UNION ALL "+
			" SELECT D.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, "+
			" 'OD Interest' ,"+
			" SUM(SETTELED_AMOUNT),NULL ENT_DATE  "+
			" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT A,  "+
			" LAKDL.AF_CO_PRO_INVOICE_DETAILS B,  "+
			" LAKDL.AF_CO_PRO_OD_INTEREST_MONTHLY C, "+ 
			" LAKDL.AF_CO_PRO_INVOICE D  "+
			" WHERE A.REC_NO = B.RECEIPT_NO AND  "+
			" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND "+
			" A.REC_NO LIKE UPPER('"+m_receipt_num+"%') AND "+
			" B.INVOICE_NO = C.ODI_REF_NO AND "+
			" C.INVOICE_NO = D.INVOICE_NO  "+
			" GROUP BY D.FINANCE_NO,CHEQUE_NO,PAYER_BRANCH_CODE  "+
			
			" ) "+
			" WHERE SETTELED_AMOUNT >0 "+													
			" ORDER BY ENT_DATE ";											

	
			g2d.setPaint (Color.black);
			
			g2d.setStroke (new BasicStroke(5));
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
			
			String m_payamount_all="--"+m_payamountword.toUpperCase()+" ONLY--";

			Font titleFont = new Font ("Times New Roman",Font.PLAIN,10);
			g2d.setFont(titleFont);
			
			String m_print_num_line_1="";
			String m_print_num_line_2="";
			String m_print_num_line_3="";
			
			boolean m_flag1=false;
			boolean m_flag2=false;
			boolean m_flag3=false;
			
			int msapce=0;
			int i=0;
			int num=0;
			
			int mcharwith=0;
			int mcharcount=0;
			
			int m_charspace=0;
			int m_charspacel=0;
		 // m_y_position=-75;
			m_row_space=0;
			///-----------------------------------------Heading 
			g2d.drawString(m_receipt_num,100,m_y_position+70);	
			g2d.drawString(m_print_name,150,m_y_position+90);
			g2d.drawString(m_Letter_date,450,m_y_position+70);
						
			
			rs2 = stmt2.executeQuery (Inv_det);	
			
			boolean more2 = rs2.next();
			if(more2){
			while(more2){
			g2d.drawString(rs2.getString(1),50,m_y_position+m_row_space+150);
			g2d.drawString(rs2.getString(2),130,m_y_position+m_row_space+150);
			g2d.drawString(rs2.getString(3),210,m_y_position+m_row_space+150);
			g2d.drawString(rs2.getString(4),295,m_y_position+m_row_space+150);
			g2d.drawString(nf.format(rs2.getDouble(5)),550,m_y_position+m_row_space+150);
			m_row_space = m_row_space +15;
			sum_amount =sum_amount+rs2.getDouble(5);
			more2 = rs2.next();
      }
			
			if(m_over_payment>0){
			g2d.drawString("Towards Dues of the Contract",295,m_y_position+m_row_space+385);
			g2d.drawString(nf.format(m_over_payment),550,m_y_position+m_row_space+385);
			}
			}
			else {
			m_row_space = 0;
			String charge_desc="Initial Payment / Document Charges / Insuarance / Advance Monthly Instruments / RMV Charges  / ";
			String charge_desc1="Semi luxury / Luxury Tax / Governement Levy / Insuarance Claims / ";
			String charge_desc2="Lease Receivable - Settlement / ODI / Sale Price ";
			g2d.drawString(charge_desc,50,m_y_position+m_row_space+150);
			m_row_space = m_row_space +15;
			g2d.drawString(charge_desc1,50,m_y_position+m_row_space+150);
			m_row_space = m_row_space +15;
			g2d.drawString(charge_desc2,50,m_y_position+m_row_space+150);
			g2d.drawString(nf.format(m_amount),550,m_y_position+150);
			m_row_space = m_row_space +15;
			g2d.drawString("Settle Mode - "+m_Sett_mod+"   AGREEMENT NO: "+m_comments+" ",50,m_y_position+m_row_space+150);
			sum_amount=m_amount;
			}
			
			m_row_space = 0;
			g2d.drawString(nf.format(sum_amount),550,m_y_position+m_row_space+415);
			m_row_space = m_row_space +40;
			
			
			g2d.drawString("RUPEES "+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY",150,m_y_position+m_row_space+415);
						
      m_row_space=0;
			g2d.drawString(m_client_name,150,m_y_position+m_row_space+700);
      m_row_space = m_row_space +15;			
			g2d.drawString(m_print_address,150,m_y_position+m_row_space+700);
      m_row_space = m_row_space +15;			
			g2d.drawString(m_city_desc,150,m_y_position+m_row_space+700);
			  
	
	//	}  //end of for

	}
	catch(Exception ex){
	}
	
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	
	}
}
