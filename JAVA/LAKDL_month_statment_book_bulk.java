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

class Print_month_statment_book_bulk implements Printable {

public String m_invoice_num;
public String m_value_date;
public int mm_page_count;
public int mm_page_size;
public int mm_page_size2;

java.text.NumberFormat nf;

public Print_month_statment_book_bulk(String m_rec,String m_val_date){
nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_invoice_num=m_rec;
m_value_date =m_val_date;
}

public int print (Graphics g,PageFormat pageFormat, int page) {
	
	String m_client_no="";
	String m_client_code="";
	String m_due_date="";
	
	String m_Letter_date="";
	String m_Previous_day="";
	String m_Letter_date_month="";
	String m_end_date="";
	String m_month="";
	String m_date="";
  
	
	double _m_tot_net_rental=0.00;
	double _m_tot_vat_rental=0.00;
	double _m_tot_gross_rental=0.00;
	
	
	
	String m_client_name="";
	String m_client_add1="";
	String m_client_add2="";
	String m_city_desc="";
	String m_vat_reg_no="";
	String m_contact_person="";
	String m_payamount="";
	String m_payamountword="";
	String m_add1="";
	String m_add2="";
	String m_city="";
	String m_html_client_url="";
	//String m_Letter_date="";
	
	String m_vdate="";
	String m_ref_no="";
	String m_pay_type="";
	String m_settle_mode="";
	String m_invoice_no="";
	
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
  String m_schema_name = "OFSCL";
	
	
	int m_y_position=0;
	int m_row_space =0;

	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (),pageFormat.getImageableY());
	
	try{
	
	LAKDL_print_methods m_print_method=new LAKDL_print_methods();
	Connection conn;
	ResultSet rs,rs1,rs2,rs3,rs_pend;
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
	
	
	
	
	//for(int z=0;z<=m_invoice_num.length;z++){
		
			//m_y_position=m_y_position+(z*835);
			m_row_space =0;
  
	
	 rs = stmt.executeQuery ("SELECT B.CLIENT_CODE "+
		    " FROM LAKDL.AF_CO_PRO_INVOICE A, LAKDL.AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO =B.FINANCE_NO AND A.GROUP_INV_NO = UPPER('"+m_invoice_num.toString()+"') "+
				" GROUP BY B.CLIENT_CODE "); 
	
				more = rs.next();
				if(more){
	      m_client_no=rs.getString(1);
				m_invoice_no = m_invoice_num.toString();
	      } 
	
	 //rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY'),TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1)),'DD/MM/YY') ,TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'Month YYYY') , TO_CHAR(LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')),'DD/MM/YY') , TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'Month') , TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL "); //,TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'DD-MM-YYYY')
	 rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY'),TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1)),'DD/MM/YY') ,TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'Month YYYY') , TO_CHAR(LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')),'DD/MM/YY') , TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'Month') , TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL");	
		
		
		more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				m_Previous_day=rs.getString(2);
				m_Letter_date_month=rs.getString(3);
				m_end_date=rs.getString(4);
				m_month=rs.getString(5);
				m_date=rs.getString(6);
				}
  





	rs1 = stmt1.executeQuery (	" SELECT "+
  		  " CLIENT_CODE, "+
  		  " UPPER(FULL_NAME), "+
  		  " NVL(UPPER(ADDRESS1),'ADD1'), "+
  		  " NVL(UPPER(ADDRESS2),'ADD2'), "+
  		  " NVL(UPPER(LAKDL.AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ,"+
				" NVL(VAT_REG_NO,'-') VAT_REG_NO, "+
				" NVL(CONTACT_FOR_PAYMENT,' ' ) "+
				" FROM LAKDL.AF_CO_MAS_CLIENT "+
  		  " WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				more = rs1.next();
				if(more){
				m_client_name=rs1.getString(2);
				m_client_add1=rs1.getString(3);
				m_client_add2=rs1.getString(4);
				m_city_desc=rs1.getString(5);
				m_vat_reg_no=rs1.getString(6);
				m_contact_person=rs1.getString(7);
				}
				
 /*
	
	String Sql_invoice=" SELECT "+
					" A.FINANCE_NO,TO_CHAR(A.DUE_DATE,'DD/MM/YYYY'),NVL(SUM(NET_AMOUNT),0),NVL(SUM(VAT_AMOUNT),0),NVL(SUM(TOTAL_AMOUNT),0),B.APPLICATION_NO,NVL(LAKDL.AF_CO_GET_ALL_REG_NUMBERS(A.FINANCE_NO),' ') ,LAKDL.AF_CO_GET_VAT_RATE(B.APPLICATION_NO) ,"+
					" NVL(LAKDL.AF_CO_GET_MAKE(B.APPLICATION_NO),'-') ASSET_DESC "+
					" FROM LAKDL.AF_CO_PRO_INVOICE A,LAKDL.AF_CO_PRO_APPLICATION_DETAILS B"+
					" WHERE A.FINANCE_NO=B.FINANCE_NO"+
					" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)  "+
					" AND   A.DUE_DATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY'))"+
					" AND   B.CLIENT_CODE='"+m_client_no+"' "+
					" AND   B.APPLICATION_STATUS='ACTIVATED'"+
					" AND   A.ACTIVE_STATUS='Y' "+
					" AND   A.INVOICE_TYPE='INV_GENER'  "+
					" GROUP BY A.FINANCE_NO,B.APPLICATION_NO,A.DUE_DATE ";
					
		*/			
	String Sql_invoice=" SELECT "+
	        " A.FINANCE_NO, "+
					" TO_CHAR(A.DUE_DATE,'DD/MM/YYYY'), "+
					" NVL(SUM(NET_AMOUNT),0),NVL(SUM(VAT_AMOUNT),0), "+
					" NVL(SUM(TOTAL_AMOUNT),0), "+
					" B.APPLICATION_NO, "+
					" NVL(LAKDL.AF_CO_GET_ALL_REG_NUMBERS(A.FINANCE_NO),' ') , "+
					" LAKDL.AF_CO_GET_VAT_RATE(B.APPLICATION_NO) , "+
					" NVL(LAKDL.AF_CO_GET_MAKE(B.APPLICATION_NO),'-') ASSET_DESC "+
					" FROM LAKDL.AF_CO_PRO_INVOICE A,LAKDL.AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE A.FINANCE_NO=B.FINANCE_NO "+
					" AND A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1) "+
					" AND A.DUE_DATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')) "+
					" AND B.CLIENT_CODE='"+m_client_no+"' "+    //0000034644
					" AND B.APPLICATION_STATUS='ACTIVATED' "+
					" AND A.ACTIVE_STATUS='Y' "+
					" AND A.INVOICE_TYPE='INV_GENER' "+
					" GROUP BY A.FINANCE_NO,B.APPLICATION_NO,A.DUE_DATE ";
					
					
					
	
	
			more = rs1.next();	
				
				if(more){
				  m_Cheque_no   = rs1.getString(2);
				  m_Bank_Branch = rs1.getString(4);
				  m_amount      = rs1.getDouble(5);
					m_amount_wd   = rs1.getString(5);
					m_Sett_mod    = rs1.getString(6); 
					m_Sett_ref    = rs1.getString(2);
				}
				


		
			g2d.setPaint (Color.black);
			
			g2d.setStroke (new BasicStroke(5));
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
			
			//m_payamountword=m_print_method.numbersToChar(m_print_method.met_unformat_number(nf.format(rs.getDouble(3))));
			//String m_payamount_all="--"+m_payamountword.toUpperCase()+" ONLY--";

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
				
			g2d.drawString("Tax Invoice",274,m_y_position+65);
						
			///-----------------------------------------Client name and address
			
			Font BodyFont = new Font ("Tahoma",Font.PLAIN,7);
			g2d.setFont(BodyFont);
			
			g2d.drawString(m_client_name,50,m_y_position+109);
			g2d.drawString(m_client_add1,50,m_y_position+125);
			g2d.drawString(m_client_add2,50,m_y_position+140);
			g2d.drawString(m_city_desc,50,m_y_position+155); 
			g2d.drawString("Customer VAT No :"+m_vat_reg_no,50,m_y_position+170);
			g2d.drawString("Rental due for  :"+m_Letter_date_month,50,m_y_position+185);
			
			///-----------------------------------------Company vat no,Invoice No
						
			g2d.drawString("OFSCL Vat No ",390,m_y_position+109);
			g2d.drawString("Invoice No   ",390,m_y_position+125); 

      g2d.drawString(":"+m_LAKDL_vat_no,458,m_y_position+109);
			g2d.drawString(": "+m_invoice_num.toString(),458,m_y_position+125); 

      g2d.drawString("Atten ",390,m_y_position+170); 
			g2d.drawString("Amount in Rupees ",390,m_y_position+185);
      
			g2d.drawString(": " +m_contact_person,458,m_y_position+170); 
			g2d.drawString(": ",458,m_y_position+185);
						
			///------------------------------------- Agreements Details 
			g2d.drawString("------------------------------------------------------------------------------------------------------------------------------------------------",50,m_y_position+225);		
			
			g2d.drawString("Agreement No",50,m_y_position+235);						
			g2d.drawString("Due Date ",144,m_y_position+235);
			g2d.drawString("VAT % ",202,m_y_position+235);	
			g2d.drawString("Net Rental ",252,m_y_position+235);
			g2d.drawString("VAT ",311,m_y_position+235);
			g2d.drawString("Total ",367,m_y_position+235); 
			g2d.drawString("Equipment No ",425,m_y_position+235); 
			g2d.drawString("Make ",514,m_y_position+235);
      g2d.drawString("------------------------------------------------------------------------------------------------------------------------------------------------",50,m_y_position+245);		
						
			rs= stmt.executeQuery (Sql_invoice);
			m_row_space=15;
			
			boolean more11 = rs.next();  
			
			while(more11){			
			g2d.drawString(rs.getString(1),50,m_y_position+m_row_space+245);						
			g2d.drawString(rs.getString(2),144,m_y_position+m_row_space+245);
			g2d.drawString(nf.format(rs.getDouble(8)),202,m_y_position+m_row_space+245);	
			g2d.drawString(nf.format(rs.getDouble(3)),252,m_y_position+m_row_space+245);
			g2d.drawString(nf.format(rs.getDouble(4)),311,m_y_position+m_row_space+245);
			g2d.drawString(nf.format(rs.getDouble(5)),367,m_y_position+m_row_space+245); 
			g2d.drawString(rs.getString(7),425,m_y_position+m_row_space+245);
			g2d.drawString(rs.getString(9),524,m_y_position+m_row_space+245);
			m_row_space = m_row_space + 15;
			
			_m_tot_net_rental+=rs.getDouble(3);
			_m_tot_vat_rental+=rs.getDouble(4);
			_m_tot_gross_rental+=rs.getDouble(5);
	    			
			more11 = rs.next(); 	
						
			}
			rs.close();
			
			g2d.drawString("-------------------------------------------------------------------------------",252,m_y_position+m_row_space+245);
			g2d.drawString(nf.format(_m_tot_net_rental),252,m_y_position+m_row_space+255);
			g2d.drawString(nf.format(_m_tot_vat_rental),311,m_y_position+m_row_space+255);
			g2d.drawString(nf.format(_m_tot_gross_rental),367,m_y_position+m_row_space+255); 

      g2d.drawString("-------------------------------------------------------------------------------",252,m_y_position+m_row_space+265);

					
			m_row_space = m_row_space +25;			

			g2d.drawString("Timely payment would be appriciated.",50,m_y_position+m_row_space+265);
			g2d.drawString("This computer generated invoice requires no signatures." ,50,m_y_position+m_row_space+275);
			
			m_row_space = m_row_space +30;			
			g2d.drawString("------------------------------------------------------------------------------------------------------------------------------------------------",50,m_y_position+m_row_space+275);
			g2d.drawString("Statement of Account",250,m_y_position+m_row_space+285);
      g2d.drawString("------------------------------------------------------------------------------------------------------------------------------------------------",50,m_y_position+m_row_space+295);
			g2d.drawString("Gross Rental=G.ren",50,m_y_position+m_row_space+305);

			m_row_space = m_row_space +25;
			
			g2d.drawString("----------------------------------------------------------------------------------------------------------------------------",50,m_y_position+m_row_space+305);
						
			g2d.drawString("Ref",108,m_y_position+m_row_space+315);
			g2d.drawString("G.Ren",152,m_y_position+m_row_space+315);
			g2d.drawString("ODI",218,m_y_position+m_row_space+315);
      g2d.drawString("Insurance",260,m_y_position+m_row_space+315);
      g2d.drawString("Othe chg",324,m_y_position+m_row_space+315);
			g2d.drawString("Total",389,m_y_position+m_row_space+315);
			g2d.drawString("Total Over Due",440,m_y_position+m_row_space+315);
			g2d.drawString("No of months",523,m_y_position+m_row_space+315);
			g2d.drawString("arrears",530,m_y_position+m_row_space+325);

      g2d.drawString("----------------------------------------------------------------------------------------------------------------------------",50,m_y_position+m_row_space+335);

	
			rs= stmt.executeQuery (Sql_invoice);
					String _m_finance_no ="";
					String _m_application_no ="";
					double _m_total_last_month=0;
					double _m_total_receipts=0;
					double _m_bal_g_ren=0;
					double _m_bal_total_charges=0;
					double _m_bal_other_charges=0;
					double _m_bal_insuarance=0;
					double _m_bal_odi_ren=0;
					double _m_total_charges_mont=0;
					double _m_total_charges_month=0;
					double _m_total_over_due=0;
					double _m_total_rental=0;
					double _m_total_payable=0;
					while(rs.next()){
					_m_finance_no     = rs.getString(1);
					_m_application_no = rs.getString(6);
					
			
				rs2= stmt2.executeQuery (" SELECT "+
				" NVL(LAKDL.AF_CO_GET_RENTAL_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) G_REN, "+//1
				" NVL(LAKDL.AF_CO_GET_ODI_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) ODI, "+ //2
				" NVL(LAKDL.AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','INSURA'),0) INSU, "+ //3
				" NVL(LAKDL.AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','OTHER'),0) OTHR, "+ //4
				" NVL(LAKDL.AF_CO_GET_INVOICE_THS_MONTH('"+_m_finance_no+"','"+m_value_date+"'),0) G_THS, "+ //5
				" NVL(LAKDL.AF_CO_GET_ODI_ARREARS_THS_MNTH('"+_m_finance_no+"','"+m_value_date+"'),0) ODI_THS, "+ //6
				" NVL(LAKDL.AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','RENT'),0) RENT, "+ //7
				" NVL(LAKDL.AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','INSUARANCE'),0) INSUARANCE, "+ //8
				" NVL(LAKDL.AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','OTHER'),0) OTHER ,"+ //9
				" NVL(LAKDL.AF_CO_GET_AGE_END_AGR_NO('"+_m_finance_no+"','"+m_value_date+"'),0) AGE ,"+ //10
				" NVL(LAKDL.AF_CO_MAS_ASSET_DESC('"+_m_application_no+"'),'-') ASSET_DESC ,"+ //11
				" NVL(LAKDL.AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','ODI'),0) RENT "+ //12
				
				
				" FROM DUAL ");
						
					if(rs2.next()){
					m_row_space = m_row_space +10;
					g2d.drawString(_m_finance_no+"  ",50,m_y_position+m_row_space+335);
					g2d.drawString(rs2.getString(11),150,m_y_position+m_row_space+335); 
					//m_row_space = m_row_space +15;
					g2d.drawString(rs2.getString(7),300,m_y_position+m_row_space+335);
					
					
					//________ Begining Balance _____________________________________________
					
					Font BodyFontsmal = new Font ("Tahoma",Font.PLAIN,6);
			    g2d.setFont(BodyFontsmal);
					
					m_row_space = m_row_space +8;
					g2d.drawString("Balance "+m_Previous_day,50,m_y_position+m_row_space+335);
					g2d.drawString(" - ",108,m_y_position+m_row_space+335);
										
			    g2d.setFont(BodyFont);
					
					g2d.drawString(nf.format(rs2.getDouble(1)),152,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(2)),223,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(3)),270,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(4)),334,m_y_position+m_row_space+335);
					_m_total_last_month=rs2.getDouble(1)+rs2.getDouble(2)+rs2.getDouble(3)+rs2.getDouble(4);
					
					g2d.drawString(nf.format(_m_total_last_month),389,m_y_position+m_row_space+335);
					g2d.drawString(" ",442,m_y_position+m_row_space+335);
					g2d.drawString(" ",540,m_y_position+m_row_space+335);
					
					
					//__________ Charges ____________________________________________________
										
			    g2d.setFont(BodyFontsmal);					
					
	        m_row_space = m_row_space +8;
					g2d.drawString(m_Letter_date_month+"-Charges",50,m_y_position+m_row_space+335);
					g2d.drawString(" - ",108,m_y_position+m_row_space+335);
				
			    g2d.setFont(BodyFont);
					
					g2d.drawString(nf.format(rs2.getDouble(5)),152,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(6)),223,m_y_position+m_row_space+335);
					
					_m_total_charges_month=rs2.getDouble(5)+rs2.getDouble(6);
					
					g2d.drawString(" ",270,m_y_position+m_row_space+335);
					g2d.drawString(" ",334,m_y_position+m_row_space+335);
					g2d.drawString(" ",389,m_y_position+m_row_space+335);
					g2d.drawString(" ",442,m_y_position+m_row_space+335);
					g2d.drawString(" ",540,m_y_position+m_row_space+335);
						
					
					//______________Receipts ______________________________________________
					g2d.setFont(BodyFontsmal);
					
					m_row_space = m_row_space +8;
					g2d.drawString("Receipts-"+m_Letter_date_month,50,m_y_position+m_row_space+335);
					g2d.setFont(BodyFont);
					g2d.drawString(" - ",108,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(7)),152,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(12)),223,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(8)),270,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(9)),334,m_y_position+m_row_space+335);
					
					_m_total_receipts=rs2.getDouble(7)+rs2.getDouble(8)+rs2.getDouble(9);
										
					g2d.drawString(nf.format(_m_total_receipts),389,m_y_position+m_row_space+335);
					g2d.drawString(" ",442,m_y_position+m_row_space+335);
					g2d.drawString(" ",540,m_y_position+m_row_space+335);
					
								
					//______________End Blalance _______________________________________________
					
					_m_bal_g_ren=(rs2.getDouble(1)+rs2.getDouble(5) ) - rs2.getDouble(7);
					_m_bal_odi_ren=rs2.getDouble(2)+rs2.getDouble(6);
					_m_bal_insuarance=rs2.getDouble(2)+rs2.getDouble(6);
					_m_bal_other_charges=rs2.getDouble(2)+rs2.getDouble(6);
					_m_bal_total_charges=(_m_total_last_month + _m_total_charges_month ) -  _m_total_receipts ;
					
					g2d.setFont(BodyFontsmal);
					
					m_row_space = m_row_space +8;
					g2d.drawString("Balance-"+m_end_date,50,m_y_position+m_row_space+335);
					g2d.setFont(BodyFont);
					g2d.drawString(" - ",108,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(_m_bal_g_ren),152,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(_m_bal_odi_ren),223,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(_m_bal_insuarance),270,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(_m_bal_other_charges),334,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(_m_bal_total_charges),389,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(_m_bal_total_charges),452,m_y_position+m_row_space+335);
					g2d.drawString(nf.format(rs2.getDouble(10)),540,m_y_position+m_row_space+335);
								
					
					_m_total_over_due+=_m_bal_total_charges;
					_m_total_rental+=rs2.getDouble(1);
									
					}
					rs2.close();
			    
			    }
					rs.close();
	 		
      
					
					
					m_row_space = m_row_space +15;
					m_row_space = m_row_space +15;
			/*
			  String sql_return=" SELECT "+
				" REC_NO, "+//1
				" PAYER_BRANCH_CODE, "+ //2
				" PAYER_ACC_NO, "+ //3
				" REC_AMOUNT, "+ //4
				" BRANCH_CODE, "+ //5
				" ACC_NO, "+ //6
				" EFF_VALDATE, "+ //7
				" CHEQUE_NO, "+ //8
				" REC_AMOUNT_REP_CURR, "+ //9
				" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') "+ //10
				" FROM LAKDL.AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE EFF_VALDATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)  "+
				" AND   EFF_VALDATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY'))"+
				" AND   STATUS='RET' "+
				" AND   UPPER(CLIENT_CODE)=UPPER('"+m_client_no+"') ";
			
			
				rs= stmt.executeQuery(sql_return);
					boolean more_return=rs.next();
					if(more_return){
					
					while(more_return){
			
			    g2d.drawString("Cheques Returns"+m_end_date,50,m_y_position+m_row_space+335);
					g2d.drawString(rs.getString(3),108,m_y_position+m_row_space+335);
					g2d.drawString(rs.getString(5),152,m_y_position+m_row_space+335);
					g2d.drawString(rs.getString(10),223,m_y_position+m_row_space+335);
					g2d.drawString("-",260,m_y_position+m_row_space+335);
					g2d.drawString("-",324,m_y_position+m_row_space+335);
					g2d.drawString(" ",389,m_y_position+m_row_space+335);
					g2d.drawString(" ",432,m_y_position+m_row_space+335);
					m_row_space = m_row_space +15;
					
					more_return=rs.next();
					}
					
					}
			      */    										
			
			   
					//rs_pend = stmt.executeQuery (Sql_invoice);
					
					//if(rs_pend.next()){
					g2d.drawString("Total Over Due as at "+m_end_date,50,m_y_position+720);
					g2d.drawString(nf.format(_m_total_over_due),508,m_y_position+720);
					g2d.drawString(" ",152,m_y_position+720);
			    					
					m_row_space = 10;
					g2d.drawString("Gross rentals Due in "+m_Letter_date_month+" for all contracts",50,m_y_position+m_row_space+720);
					g2d.drawString(nf.format(_m_total_rental),508,m_y_position+m_row_space+720);
					g2d.drawString(" ",152,m_y_position+m_row_space+720);
					
					_m_total_payable=_m_total_over_due+_m_total_rental;
										
					m_row_space = m_row_space + 10;
					g2d.drawString("Total payable in "+m_month+" without charges due in "+m_month,50,m_y_position+m_row_space+720);
					
					g2d.drawString("-----------------",508,m_y_position+m_row_space+720);
					g2d.drawString(nf.format(_m_total_payable),508,m_y_position+m_row_space+730);
					g2d.drawString("-----------------",508,m_y_position+m_row_space+740);
			    
					//}
					/*
			    m_row_space = m_row_space +30;
			    g2d.drawString("Payment Slip",50,m_y_position+m_row_space+115);
          m_row_space = m_row_space +15;
			    g2d.drawString("Credit to the "+m_orient_name+" AC ",50,m_y_position+m_row_space+115);
						
			    m_row_space = m_row_space +30; 
			
			    g2d.drawString("Agreement No",50,m_y_position+m_row_space+115);
					g2d.drawString("Vehicle No",108,m_y_position+m_row_space+115);
					g2d.drawString("Due Including "+m_Letter_date_month,152,m_y_position+m_row_space+115);
					g2d.drawString("Cheque No",223,m_y_position+m_row_space+115);
					g2d.drawString("Bank Branch",260,m_y_position+m_row_space+115);
					g2d.drawString("Value Rs.",324,m_y_position+m_row_space+115);

          
   
			    rs= stmt.executeQuery (Sql_invoice);
					
					while(rs.next()){
					_m_finance_no     = rs.getString(1);
					_m_application_no = rs.getString(6);
			   		
					
						rs2= stmt2.executeQuery (" SELECT "+
						" NVL("+m_schema_name+".AF_CO_GET_RENTAL_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) G_REN, "+//1
						" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) ODI, "+ //2
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','INSURA'),0) INSU, "+ //3
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','OTHER'),0) OTHR, "+ //4
						" NVL("+m_schema_name+".AF_CO_GET_INVOICE_THS_MONTH('"+_m_finance_no+"','"+m_value_date+"'),0) G_THS, "+ //5
						" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS_THS_MNTH('"+_m_finance_no+"','"+m_value_date+"'),0) ODI_THS, "+ //6
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','RENT'),0) RENT, "+ //7
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','INSUARANCE'),0) INSUARANCE, "+ //8
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','OTHER'),0) OTHER ,"+ //9
						" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO('"+_m_finance_no+"','"+m_value_date+"'),0) AGE ,"+ //10
						" NVL("+m_schema_name+".AF_CO_MAS_ASSET_DESC('"+_m_application_no+"'),'-') ASSET_DESC ,"+ //11
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','ODI'),0) RENT "+ //12
						" FROM DUAL ");
						rs2.next();
					_m_bal_g_ren=(rs2.getDouble(1)+rs2.getDouble(5) ) - rs2.getDouble(7);
			 
			    m_row_space = m_row_space +15;     
			    g2d.drawString(rs.getString(1),50,m_y_position+m_row_space+115);
					g2d.drawString(rs.getString(7),108,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(_m_bal_g_ren),152,m_y_position+m_row_space+115);
					g2d.drawString(" ",223,m_y_position+m_row_space+115);
					g2d.drawString(" ",260,m_y_position+m_row_space+115);
					g2d.drawString(" ",324,m_y_position+m_row_space+115);
			    
					}
					
					
			    m_row_space = m_row_space +15;     
			    g2d.drawString("Please quote agreement no in the bank deposit slip attached this slip with bank deposit slip.",50,m_y_position+m_row_space+115);
			
			    m_row_space = m_row_space +15;     
			    g2d.drawString("Com bank",50,m_y_position+m_row_space+115);
					g2d.drawString("HNB",108,m_y_position+m_row_space+115);
			  
				  m_row_space = m_row_space +15;     
			    g2d.drawString("Sampath",50,m_y_position+m_row_space+115);
					g2d.drawString("BOC",108,m_y_position+m_row_space+115);
				
				  m_row_space = m_row_space +15;     
			    g2d.drawString("Peoples",50,m_y_position+m_row_space+115);
					g2d.drawString("Union",108,m_y_position+m_row_space+115);
				
				*/
				
				/*
				
					String Sql_receipt=" SELECT A.REC_NO,C.FINANCE_NO, NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Rental Charges ' DESCREPTION,SUM(SETTELED_AMOUNT)  "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+m_schema_name+".AF_CO_PRO_INVOICE C  "+
				" WHERE A.REC_NO = B.RECEIPT_NO  "+
				" AND   B.INVOICE_NO = C.INVOICE_NO  "+
				" AND   A.EFF_VALDATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   A.EFF_VALDATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')) "+
				" AND   UPPER(C.GROUP_INV_NO)=UPPER('"+m_invoice_no+"')"+
				" GROUP BY C.FINANCE_NO,CHEQUE_NO,PAYER_BRANCH_CODE ,A.REC_NO "+
				" ORDER BY A.REC_NO ";

     rs= stmt.executeQuery (Sql_receipt);
		  more=rs.next();
			String _m_rec_no="";
			double _m_receipt_total=0;
			double _m_over_due=0;
		 while(more){
     _m_rec_no=rs.getString(1);
				
			
			
					 m_row_space = m_row_space +15;     
			    g2d.drawString("Date",50,m_y_position+m_row_space+115);
					g2d.drawString(m_value_date,108,m_y_position+m_row_space+115);
			    g2d.drawString("Receipt No",152,m_y_position+m_row_space+115);
					g2d.drawString(rs.getString(1),223,m_y_position+m_row_space+115);
			
			
			    m_row_space = m_row_space +15;     
			    g2d.drawString("Received with thanks from",50,m_y_position+m_row_space+115);
			
			     m_row_space = m_row_space +15;   
			   	g2d.drawString("Agreement No",50,m_y_position+m_row_space+115);
					g2d.drawString("Chq No/Cash",108,m_y_position+m_row_space+115);
					g2d.drawString("Bank/Branch"+m_Letter_date_month,152,m_y_position+m_row_space+115);
					g2d.drawString("Description",223,m_y_position+m_row_space+115);
					g2d.drawString(" ",260,m_y_position+m_row_space+115);
					g2d.drawString(" ",324,m_y_position+m_row_space+115);  
			    g2d.drawString("Amount",324,m_y_position+m_row_space+115);
			
			   while(_m_rec_no.equals(rs.getString(1))){
			
			
			   m_row_space = m_row_space +15;   
			   	g2d.drawString(rs.getString(2),50,m_y_position+m_row_space+115);
					g2d.drawString(rs.getString(3),108,m_y_position+m_row_space+115);
					g2d.drawString(rs.getString(4),152,m_y_position+m_row_space+115);
					g2d.drawString(rs.getString(5),223,m_y_position+m_row_space+115);
					g2d.drawString(" ",260,m_y_position+m_row_space+115);
					g2d.drawString(" ",324,m_y_position+m_row_space+115);  
			    g2d.drawString(nf.format(rs.getDouble(6)),324,m_y_position+m_row_space+115);
							
				 _m_receipt_total=rs.getDouble(6);
				
				
				m_row_space = m_row_space +15; 
				g2d.drawString("Total Amount",50,m_y_position+m_row_space+115);  
			  g2d.drawString(nf.format(_m_receipt_total),108,m_y_position+m_row_space+115);
				
				m_row_space = m_row_space +15; 
			  g2d.drawString("Receipts is valid subject to realisation of the cheque.",50,m_y_position+m_row_space+115);  
					
					
				m_row_space = m_row_space +15; 	
				g2d.drawString("Amount in words RUPEES : "+m_sn_methods.numbersToChar(Double.toString(_m_receipt_total)).toUpperCase()+" ONLY",50,m_y_position+m_row_space+115);	
					
				
				
					String sql_overdue=" SELECT NVL("+m_schema_name+".AF_CO_GET_ARREARS_AGR('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //1
			" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //2
			" NVL("+m_schema_name+".AF_CO_GET_FUTURE_RENTALS('"+rs.getString(2)+"'),0), "+ //3
			" NVL("+m_schema_name+".AF_CO_GET_FUTURE_RENTALS_AMT('"+rs.getString(2)+"'),0), "+ //4
			" NVL("+m_schema_name+".AF_CO_GET_RENTALS_PAID('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //5
			" NVL("+m_schema_name+".AF_CO_GET_RENTALS_PAID_AMT('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //6
			" NVL("+m_schema_name+".AF_CO_GET_ODI_DUE('"+rs.getString(2)+"'),0), "+ //7
			" NVL("+m_schema_name+".AF_CO_GET_ODI_DUE_MONTH('"+rs.getString(2)+"'),0) ,"+ //8
			" NVL("+m_schema_name+".AF_CO_GET_INSU_DUE_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //9
			" NVL("+m_schema_name+".AF_CO_GET_AGE_INSU_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //10
			" NVL("+m_schema_name+".AF_CO_GET_OTHER_DUE_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //11
		  " NVL("+m_schema_name+".AF_CO_GET_AGE_OTHER_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0) "+ //12
			
			" FROM DUAL ";
			//out.println("m_value_date"+m_value_date);
			rs2= stmt2.executeQuery (sql_overdue);
			boolean more2=rs2.next();
			
			m_row_space = m_row_space +15; 
			 	  g2d.drawString("Amount",50,m_y_position+m_row_space+115);
					g2d.drawString("Months",108,m_y_position+m_row_space+115);
			
			m_row_space = m_row_space +15; 
			    g2d.drawString("Rentals",50,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(rs2.getDouble(1)),108,m_y_position+m_row_space+115);  
			    g2d.drawString(nf.format(rs2.getDouble(2)),158,m_y_position+m_row_space+115);  
			
				m_row_space = m_row_space +15; 
			    g2d.drawString("Interest",50,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(rs2.getDouble(7)),108,m_y_position+m_row_space+115);  
			    g2d.drawString(nf.format(rs2.getDouble(8)),158,m_y_position+m_row_space+115);  
			
			m_row_space = m_row_space +15; 
			    g2d.drawString("insuarance",50,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(rs2.getDouble(9)),108,m_y_position+m_row_space+115);  
			    g2d.drawString(nf.format(rs2.getDouble(10)),158,m_y_position+m_row_space+115);  
					
			m_row_space = m_row_space +15; 
			    g2d.drawString("Other dues",50,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(rs2.getDouble(11)),108,m_y_position+m_row_space+115);  
			    g2d.drawString(nf.format(rs2.getDouble(12)),158,m_y_position+m_row_space+115);  		
			
			_m_over_due=rs2.getDouble(1)+rs2.getDouble(7)+rs2.getDouble(9)+rs2.getDouble(11);
			
			
			m_row_space = m_row_space +15; 
			    g2d.drawString("Total overdue",50,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(_m_over_due),108,m_y_position+m_row_space+115);  
			    g2d.drawString(" ",158,m_y_position+m_row_space+115);  		
			
		m_row_space = m_row_space +15; 
			    g2d.drawString(" ",50,m_y_position+m_row_space+115);
					g2d.drawString("No",108,m_y_position+m_row_space+115);  
			    g2d.drawString("Value",158,m_y_position+m_row_space+115);  
			
			m_row_space = m_row_space +15; 
			    g2d.drawString("Future rentals",50,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(rs2.getDouble(3)),108,m_y_position+m_row_space+115);  
			    g2d.drawString(nf.format(rs2.getDouble(4)),158,m_y_position+m_row_space+115);  
		
			m_row_space = m_row_space +15; 
			    g2d.drawString("Total rentals paid",50,m_y_position+m_row_space+115);
					g2d.drawString(nf.format(rs2.getDouble(5)),108,m_y_position+m_row_space+115);  
			    g2d.drawString(nf.format(rs2.getDouble(6)),158,m_y_position+m_row_space+115);  
			
			m_row_space = m_row_space +15; 
			    g2d.drawString("News",50,m_y_position+m_row_space+115);	
			
		m_row_space = m_row_space +30; 
			    g2d.drawString("Stamp duty has been calculated in terms of section 7 of the Stamp Duty (Special Provisions) Act No 12 of 2006.",50,m_y_position+m_row_space+115);	
			
				
		m_row_space = m_row_space +30; 
			    g2d.drawString("Please inform any discrepancies within 10 days.",50,m_y_position+m_row_space+115);	
				
			more=rs.next();
			if(!more){
			break;
			}	
			
						
			}  */


			
		 //}
		
		
			
				
		//}  //end of for

	}
	catch(Exception ex){
	}
	
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	
	}
}
