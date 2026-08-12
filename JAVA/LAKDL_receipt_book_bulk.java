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

class Print_receipt_book_bulk implements Printable {

public String[] m_receipt_num;
public int mm_page_count;
public int mm_page_size;
public int mm_page_size2;

java.text.NumberFormat nf;

public Print_receipt_book_bulk(String[] m_rec){
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

	
	
	int m_y_position=0;
	int m_row_space =0;

	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (),pageFormat.getImageableY());
	
	try{
	
		
	LAKDL_print_methods m_print_method=new LAKDL_print_methods();
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
	
	

	
	for(int z=0;z<=m_receipt_num.length;z++){
		
			m_y_position=m_y_position+(z*835);
			m_row_space =0;
   
	             rs = stmt.executeQuery ("SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY'),CLIENT_CODE FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE "+
				/*CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND*/  
				" REC_NO = UPPER('"+m_receipt_num[z].toString()+"') ");	
				more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				m_client_no=rs.getString(2);
				m_receipt_no=m_receipt_num[z].toString();
				}
				
   
	rs1 = stmt1.executeQuery (	" SELECT "+
  		  " CLIENT_CODE, "+
  		  " UPPER(FULL_NAME), "+
  		  " NVL(UPPER(ADDRESS1),'ADD1'), "+
  		  " NVL(UPPER(ADDRESS2),'ADD2'), "+
  		  " NVL(UPPER(LAKDL.AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ,"+
				" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
				" FROM LAKDL.AF_CO_MAS_CLIENT "+
  		  " WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				more = rs1.next();
				if(more){
				m_client_name=rs1.getString(2);
				m_client_add1=rs1.getString(3);
				m_client_add2=rs1.getString(4);
				m_city_desc=rs1.getString(5);
				m_vat_reg_no=rs1.getString(6);
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
					" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");
  
	      				
				more = rs1.next();	
				
				if(more){
				  m_Cheque_no   = rs1.getString(2);
				  m_Bank_Branch = rs1.getString(4);
				  m_amount      = rs1.getDouble(5);
					m_amount_wd   = rs1.getString(5);
					m_Sett_mod    = rs1.getString(6); 
					m_Sett_ref    = rs1.getString(2);
				}
		

			
				String Inv_det =	" SELECT FINANCE_NO,CHEQUE_NO,PAYER_BRANCH_CODE,DESCREPTION, "+
	                  " SETTELED_AMOUNT,ENT_DATE "+
										" FROM "+
										" ( SELECT C.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, "+
										" NVL(LAKDL.AF_CO_GET_SUB_CHARG_DESC(C.INVOICE_TYPE),LAKDL.AF_CO_GET_INVOICE_DESCR(C.INVOICE_TYPE))||'  '||B.INVOICE_NO DESCREPTION, "+
										" SETTELED_AMOUNT,C.ENT_DATE "+
										" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT A, "+
										" LAKDL.AF_CO_PRO_INVOICE_DETAILS B, "+
										" LAKDL.AF_CO_PRO_INVOICE C "+
										" WHERE A.REC_NO = B.RECEIPT_NO AND "+
										" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND A.REC_NO LIKE UPPER('"+m_receipt_no+"%') "+
										" AND B.INVOICE_NO = C.INVOICE_NO "+
										" UNION ALL "+
										" SELECT D.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, "+
										//" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(D.INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(D.INVOICE_TYPE))||'  '||B.INVOICE_NO DESCREPTION, "+
										" NVL(LAKDL.AF_CO_GET_SUB_CHARG_DESC('ODI'),LAKDL.AF_CO_GET_INVOICE_DESCR('ODI'))||'  '||B.INVOICE_NO DESCREPTION, "+
										" SETTELED_AMOUNT, D.ENT_DATE "+
										" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT A, "+
										" LAKDL.AF_CO_PRO_INVOICE_DETAILS B, "+
										" LAKDL.AF_CO_PRO_OD_INTEREST_MONTHLY C, "+
										" LAKDL.AF_CO_PRO_INVOICE D "+
										" WHERE A.REC_NO = B.RECEIPT_NO AND "+
										" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND "+
										" A.REC_NO LIKE UPPER('"+m_receipt_no+"%') AND "+
										" B.INVOICE_NO = C.ODI_REF_NO AND "+
										" C.INVOICE_NO = D.INVOICE_NO "+
										" ) "+
										" WHERE SETTELED_AMOUNT >0 "+													
										" ORDER BY ENT_DATE ";	


			
		   
			g2d.setPaint (Color.black);
			
			g2d.setStroke (new BasicStroke(5));
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
			
			String m_payamount_all="--"+m_payamountword.toUpperCase()+" ONLY--";

			Font titleFont = new Font ("Arial",Font.BOLD,7);
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
		
			
			///-----------------------------------------Heading 
				
			g2d.drawString("Receipt Document",461,m_y_position+85);
						
			///-----------------------------------------Company name and address 
			
			g2d.drawString(m_orient_name,50,m_y_position+115);
			
			g2d.drawString(m_orient_add1+m_orient_add2+m_orient_city_name,50,m_y_position+130);
			
			g2d.drawString("Receipt No :"+m_receipt_num[z].toString(),50,m_y_position+166);
			g2d.drawString("Received with thanks from :  "+m_client_name,50,m_y_position+180); 
			
			
			
			g2d.drawString("Sttlement Mode",50,m_y_position+200); 
			g2d.drawString(":"+m_Sett_mod ,200,m_y_position+200);						
			g2d.drawString("Reference No ",50,m_y_position+215);
			g2d.drawString(":"+m_Cheque_no ,200,m_y_position+215);	
			g2d.drawString("Bank & Branch",50,m_y_position+230);
			g2d.drawString(":"+m_Bank_Branch ,200,m_y_position+215);
			g2d.drawString("Amount ",50,m_y_position+245); 
			g2d.drawString(":"+nf.format(m_amount) ,200,m_y_position+215);
			g2d.drawString("Amount in words",50,m_y_position+260); 
			g2d.drawString(":"+m_amount_wd ,200,m_y_position+215);
			

			g2d.drawString("Agreement No",108,m_y_position+310);
			g2d.drawString("Description" ,295,m_y_position+310);
			g2d.drawString("Amount Rs." ,475,m_y_position+310);
			
      
			
			rs2 = stmt2.executeQuery (Inv_det);	
			
			boolean more2 = rs2.next();
			
			while(more2){
			g2d.drawString(rs2.getString(1),108,m_y_position+m_row_space+325);
			g2d.drawString(rs2.getString(4),295,m_y_position+m_row_space+325);
			g2d.drawString(nf.format(rs2.getDouble(5)),475,m_y_position+m_row_space+325);
			m_row_space = m_row_space +15;
			sum_amount =sum_amount+rs2.getDouble(5);
					
			
			more2 = rs2.next();
      }
			
		
			m_row_space = m_row_space +15;
			g2d.drawString("Allocated Amount",295,m_y_position+m_row_space+325);
			g2d.drawString(sum_amount+"",475,m_y_position+m_row_space+325);
			
			m_row_space = m_row_space +15;			
			g2d.drawString("STAMP DUTY HAS BEEN COMPOUNDED IN TERMS OF SECTION 7 OF ",50,m_y_position+m_row_space+325);
      m_row_space = m_row_space +15;			
			g2d.drawString("THE STAMP DUTY(SPECIAL PROVISIONS) ACT NO.12 OF 2006 ",50,m_y_position+m_row_space+325);
			
			
			m_row_space = m_row_space +30;			
			g2d.drawString("Please inform any discrepa```````ncies within 10 days",310,m_y_position+m_row_space+325);
      m_row_space = m_row_space +15;			
			g2d.drawString("Receipt is valid subject to realisation of cheque/s",310,m_y_position+m_row_space+325);
			
			
      m_row_space = m_row_space +30;			
			g2d.drawString("...................................",50,m_y_position+m_row_space+325);			
			g2d.drawString("IF THE CONTRACT IS ALREADY TERMINATED",200,m_y_position+m_row_space+325);			

			m_row_space = m_row_space +15;			
			g2d.drawString("Authorized Signatory",50,m_y_position+m_row_space+325);			
			g2d.drawString("PAYMENT IS ACCEPTED WITHOUT PREJUDICE",200,m_y_position+m_row_space+325);			


      m_row_space = m_row_space +40;			
			g2d.drawString("If undelivered please return to :",100,m_y_position+m_row_space+325);	
			m_row_space = m_row_space +15;
			g2d.drawString(m_orient_name,100,m_y_position+m_row_space+325);
			m_row_space = m_row_space +15;
			g2d.drawString(m_orient_add1+m_orient_add2+m_orient_city_name,100,m_y_position+m_row_space+325);
			m_row_space = m_row_space +15;
			g2d.drawString("Tel :"+m_orient_tel_no+"Fax :"+m_orient_fax_no+"Email :"+m_orient_email ,100,m_y_position+m_row_space+325);



      m_row_space = m_row_space +20;			
			g2d.drawString("To :",100,m_y_position+m_row_space+325);
		
      m_row_space = m_row_space +20;
		
			g2d.drawString(m_client_name,150,m_y_position+m_row_space+325);
			
      m_row_space = m_row_space +15;	
	
			g2d.drawString(m_client_add1,150,m_y_position+m_row_space+325);
			
      m_row_space = m_row_space +15;
		
			g2d.drawString(m_client_add2,150,m_y_position+m_row_space+325);
			
			m_row_space = m_row_space +15;	
			
			g2d.drawString(m_city_desc,50,m_y_position+m_row_space+325);
			
			
			
			
			
			
			
			
			
			
			
			
			///----------------------------------------------------
			
			//-------------------------------------------------cheque body
			/*titleFont = new Font ("Arial",Font.PLAIN,8);
			g2d.setFont(titleFont); */
			/*
			g2d.drawString(m_dd1.substring(0,1),520,m_y_position+32);
			g2d.drawString(m_dd1.substring(1,2),540,m_y_position+32);
			g2d.drawString(m_mm1.substring(0,1),555,m_y_position+32);
			g2d.drawString(m_mm1.substring(1,2),575,m_y_position+32);
			g2d.drawString(m_yy1.substring(0,1),625,m_y_position+32);
			g2d.drawString(m_yy1.substring(1,2),650,m_y_position+32); */
			
			/*titleFont = new Font ("Arial",Font.PLAIN,9);
			g2d.setFont(titleFont);
			
			g2d.drawString(m_pay_name.toUpperCase(),230,m_y_position+72);
			
			
			g2d.drawString("**"+m_payamount+"**",540,m_y_position+120);
			
			titleFont = new Font ("Arial",Font.PLAIN,9);
			g2d.setFont(titleFont);
			if(m_flag1){
			g2d.drawString(m_print_num_line_1,230,m_y_position+100); 
			}
			if(m_flag2){
			g2d.drawString(m_print_num_line_2,216,m_y_position+120); 
			}
			if(m_flag3){
			g2d.drawString(m_print_num_line_3,216,m_y_position+130); 
			}*/
			
		}  //end of for

	}
	catch(Exception ex){
		System.out.println(ex.toString());
	}
	
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	
	}
}
