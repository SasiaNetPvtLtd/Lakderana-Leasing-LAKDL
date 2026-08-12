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

class Print_invoce_book_bulk implements Printable {

public String m_group_inv_num,m_from_invoice_date="",m_to_invoice_date="";
public int mm_page_count;
public int mm_page_size;
public int mm_page_size2;

java.text.NumberFormat nf;

public Print_invoce_book_bulk(String m_rec,String m_from_date, String m_to_date){
nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_group_inv_num=m_rec;
m_from_invoice_date=m_from_date;
m_to_invoice_date=m_to_date;

}

public int print (Graphics g,PageFormat pageFormat, int page) {
	
	String m_pay_name="";
	String m_client_code="";
	String m_due_date="";
	String m_value_date="",m_value_date_2="";
	String m_invoice_no="";
	String m_inv_type="";
	String m_pay_ref="";
	String m_client_name="";
	String m_payamount="";
	String m_payamountword="";
	String m_add1="";
	String m_add2="";
	String m_city="";
	String m_html_client_url="";
	String m_date="";
	String m_tax_no="";
	String m_vdate="";
	String m_ref_no="";
	String m_pay_type="";
	String m_settle_mode="";
	String m_fin_no="",m_date_sysdate="";
	
	
	String m_orient_name="";
	String m_orient_add1="";
	String m_orient_add2="";
	String m_orient_city_name="";
	String m_orient_tel_no="";
	String m_orient_fax_no="";
	String m_vat_precentage="";			
	String m_LAKDL_vat_no="";		
	
	
	
	
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
				" VAT_REG_NO "+
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
						//m_vat_precentage=rs.getString(7);			
						m_LAKDL_vat_no=rs.getString(8);			
						}
						
						
	
	
	
	
	//for(int z=0;z<=3;z++){  //m_group_inv_num.length
		
			//m_y_position=m_y_position+(z*864);  //835
			m_row_space =25;

			//comment by nuwan de silva on 24-07-2008-------------																	
			/*rs=stmt.executeQuery(" SELECT	DISTINCT FINANCE_NO, "+
			                     " CLIENT_CODE, "+
													// " TO_CHAR(DUE_DATE,'MON YYYY'), "+
													 " TO_CHAR(VALUE_DATE,'MON YYYY'), "+
													 " GROUP_INV_NO, "+
													 " INVOICE_TYPE, "+
													 " TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+
													 " FROM LAKDL.AF_CO_PRO_INVOICE "+
													 " WHERE INVOICE_TYPE='INV_GENER' "+
													 " AND GROUP_INV_NO = '"+m_group_inv_num.toString()+"' "+
                           " AND VALUE_DATE >= TO_DATE('"+m_from_invoice_date+"' ,'DD-MM-YYYY') "+
       								     " AND VALUE_DATE <= TO_DATE('"+m_to_invoice_date+"' ,'DD-MM-YYYY') "+
													 " GROUP BY   CLIENT_CODE,FINANCE_NO,GROUP_INV_NO,VALUE_DATE,DUE_DATE,INVOICE_TYPE ");
			  */					
				
													rs=stmt.executeQuery(" SELECT	DISTINCT FINANCE_NO, "+
													" CLIENT_CODE, "+
													" TO_CHAR(VALUE_DATE,'MON YYYY'), "+
													" INVOICE_NO, "+
													" INVOICE_TYPE, "+
													" TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+
													//" ,TO_CHAR(VALUE_DATE,'DD-MON-YY') "+
													" ,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+ //changed by ns 
													" FROM LAKDL.AF_CO_PRO_INVOICE "+
													" WHERE INVOICE_TYPE='INV_GENER' "+
													" AND INVOICE_NO = '"+m_group_inv_num.toString()+"' "+
													" AND VALUE_DATE >= TO_DATE('"+m_from_invoice_date+"' ,'DD-MM-YYYY') "+
													" AND VALUE_DATE <= TO_DATE('"+m_to_invoice_date+"' ,'DD-MM-YYYY') ");
													//" GROUP BY   CLIENT_CODE,FINANCE_NO,GROUP_INV_NO,VALUE_DATE,DUE_DATE,INVOICE_TYPE ");
								
			  					

								
								
								
								
					
			System.out.println("m_group_inv_num[z].toString()="+m_group_inv_num.toString()+"  m_y_position="+m_y_position);
			
			if(rs.next()){
						m_fin_no= rs.getString(1);
						m_client_code=rs.getString(2);
						m_due_date=rs.getString(3);
				    m_invoice_no=rs.getString(4);
						m_inv_type =rs.getString(5); 
						m_value_date =rs.getString(6); 
				    m_value_date_2 =rs.getString(7); 
						
						rs1 = stmt1.executeQuery (	" SELECT "+
																		    " CLIENT_CODE, "+
																		    " UPPER(FULL_NAME), "+
																		    " UPPER(ADDRESS1), "+
																		    " UPPER(NVL(ADDRESS2,' ')), "+
																		    " UPPER(NVL(LAKDL.AF_CO_GET_CITY_NAME(CITY_CODE),' ')) CITY_NAME, "+
																				" TO_CHAR(SYSDATE,'DD-MON-YY'), "+
																				" VAT_REG_NO "+
																				" FROM LAKDL.AF_CO_MAS_CLIENT "+
																		    " WHERE CLIENT_CODE='"+m_client_code+"' ");
				boolean more1 = rs1.next();	
				
				if(more1){
				m_client_name=rs1.getString(2);
				m_add1=rs1.getString(3);
				m_add2=rs1.getString(4);
				m_city=rs1.getString(5);
			  m_date="Date "+rs1.getString(6);
				m_tax_no=rs1.getString(7);
				m_date_sysdate=rs1.getString(6);
				}
				
				
				rs1 = stmt1.executeQuery (" SELECT LAKDL.AF_CO_GET_VAT_ON_RENTAL_PRO(transaction_type,'"+m_value_date_2+"')  "+//m_date_sysdate
				" FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  FINANCE_NO='"+m_fin_no+"' ");
				
				if(rs1.next()){
				m_vat_precentage=rs1.getString(1)+" %";
				}
				
				}
				
				
				
			
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
		
     // g2d.drawString("---------"+z+"----------------",128,m_y_position+70);
			
			///-----------------------------------------Heading 
			
			if(m_tax_no!=null){
			g2d.drawString("Tax Invoice",228,m_y_position+105);
			}else{
			g2d.drawString("Invoice",228,m_y_position+105);			
			}
			
			///-----------------------------------------date, name and address 
			/*g2d.drawString(m_date,50,m_y_position+137);
			g2d.drawString(m_client_name,50,m_y_position+166); 
			g2d.drawString(m_add1,50,m_y_position+180); 
			g2d.drawString(m_add2,50,m_y_position+195);  
			*/
			g2d.drawString(m_date,50,m_y_position+137);
			g2d.drawString(m_client_name,50,m_y_position+151);
			g2d.drawString(m_add1,50,m_y_position+166);
			g2d.drawString(m_add2,50,m_y_position+180); 
			g2d.drawString(m_city,50,m_y_position+195);
						
			/*g2d.drawString("OFSCL VAT No ",360,m_y_position+166);
			g2d.drawString("Invoice No",360,m_y_position+180);
			g2d.drawString(m_LAKDL_vat_no,482,m_y_position+166); 
			g2d.drawString(m_invoice_no,482,m_y_position+180); 
			*/
			
			g2d.drawString("OFSCL VAT No ",360,m_y_position+151);
			g2d.drawString("Invoice No",360,m_y_position+166);
			g2d.drawString(m_LAKDL_vat_no,482,m_y_position+151); 
			g2d.drawString(m_invoice_no,482,m_y_position+166); 
			
			if(m_tax_no!=null){
			g2d.drawString("Customer's VAT No.",50,m_y_position+244); 
			}
			g2d.drawString("Rental Due For the Month",50,m_y_position+259); 
			if(m_tax_no!=null){
			g2d.drawString("-" +m_tax_no,317,m_y_position+244);
			}
			g2d.drawString("-" +m_due_date,317,m_y_position+259);
			g2d.drawString("(Amount In Rupees)",468,m_y_position+274);
			
			
			///---------------------- Invoice Details ---------------------------------------
			g2d.drawString("---------------------------------------------------------------------------------------------------------------------------------------------------------------",50,m_y_position+289);
			g2d.drawString("S1No",50,m_y_position+304);
			g2d.drawString("Finance No",79,m_y_position+304);
			g2d.drawString("Invoice No",180,m_y_position+304);
			g2d.drawString("Due Date",288,m_y_position+304);
			g2d.drawString("Net Rent",354,m_y_position+304);
			g2d.drawString("VAT %",404,m_y_position+304);
			g2d.drawString("VAT",456,m_y_position+304);
			g2d.drawString("Gross Rent",508,m_y_position+304);
			
			g2d.drawString("---------------------------------------------------------------------------------------------------------------------------------------------------------------",50,m_y_position+319);
			
			
			/*
			rs3 = stmt3.executeQuery (" SELECT "+
				    " INVOICE_NO, "+
				    //" TO_CHAR(DUE_DATE,'DD/MM/YYYY'), "+
						" TO_CHAR(VALUE_DATE,'DD/MM/YYYY'), "+
						" NET_AMOUNT, "+
						" VAT_AMOUNT ,"+
				    " TOTAL_AMOUNT, "+
						" TO_CHAR(DUE_DATE,'MON YYYY'), "+
						" TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
						" FINANCE_NO "+
				    " FROM LAKDL.AF_CO_PRO_INVOICE "+
				    " WHERE GROUP_INV_NO=UPPER('"+m_invoice_no+"') AND INVOICE_TYPE=UPPER('"+m_inv_type+"') AND ACTIVE_STATUS='Y' "+
						" AND  LAST_DAY(ADD_MONTHS(TO_DATE('22-03-2007','DD-MM-YYYY'),-1)) < TO_DATE(VALUE_DATE,'DD/MM/YYYY') AND "+
						" TO_DATE(VALUE_DATE,'DD/MM/YYYY') <=  LAST_DAY(TO_DATE('22-03-2007','DD-MM-YYYY'))");
				   		*/
								
				
				//comment by nuwan de silva on 24-07-2008 --
				/*rs3 = stmt3.executeQuery ("SELECT "+
				" INVOICE_NO, "+
				" TO_CHAR(VALUE_DATE,'DD/MM/YYYY'), "+
				" NET_AMOUNT, "+
				" VAT_AMOUNT , "+
				" TOTAL_AMOUNT, "+
				" TO_CHAR(DUE_DATE,'MON YYYY'), "+
				" TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
				" FINANCE_NO "+
				" FROM LAKDL.AF_CO_PRO_INVOICE "+
				" WHERE GROUP_INV_NO=UPPER('"+m_invoice_no+"')  AND INVOICE_TYPE=UPPER('"+m_inv_type+"') AND ACTIVE_STATUS='Y' "+
				" AND  TO_DATE(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1)),'DD/MM/YYYY') < TO_DATE(VALUE_DATE,'DD/MM/YYYY') AND "+
				" TO_DATE(VALUE_DATE,'DD/MM/YYYY') <=  TO_DATE(LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')),'DD/MM/YYYY') ");				
				*/				
				
				rs3 = stmt3.executeQuery ("SELECT "+
				" INVOICE_NO, "+
				" TO_CHAR(VALUE_DATE,'DD/MM/YYYY'), "+
				" NET_AMOUNT, "+
				" VAT_AMOUNT , "+
				" TOTAL_AMOUNT, "+
				" TO_CHAR(DUE_DATE,'MON YYYY'), "+
				" TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
				" FINANCE_NO "+
				" FROM LAKDL.AF_CO_PRO_INVOICE "+
				" WHERE INVOICE_NO=UPPER('"+m_invoice_no+"')  "+
				" AND INVOICE_TYPE=UPPER('"+m_inv_type+"')  "+
				" AND ACTIVE_STATUS='Y' "+
				" AND VALUE_DATE >= TO_DATE('"+m_from_invoice_date+"' ,'DD-MM-YYYY') "+
				" AND VALUE_DATE <= TO_DATE('"+m_to_invoice_date+"' ,'DD-MM-YYYY') ");
								
			boolean more3 = rs3.next();
			
			
			int j=1;
			
			double sum_gross=0;
			double sum_net_rent=0;
			double sum_vat=0;
			
			while(more3){
			g2d.drawString(j+"",50,m_y_position+m_row_space+304);
			g2d.drawString(rs3.getString(8),79,m_y_position+m_row_space+304);
			g2d.drawString(rs3.getString(1),180,m_y_position+m_row_space+304);
			g2d.drawString(rs3.getString(2),288,m_y_position+m_row_space+304);
			g2d.drawString(nf.format(rs3.getDouble(3)),354,m_y_position+m_row_space+304);
			g2d.drawString(m_vat_precentage,404,m_y_position+m_row_space+304);
			g2d.drawString(nf.format(rs3.getDouble(4)),456,m_y_position+m_row_space+304);
			g2d.drawString(nf.format(rs3.getDouble(5)),508,m_y_position+m_row_space+304);
			
			
			
			j=j+1;
			sum_net_rent =sum_net_rent+rs3.getDouble(3);
			sum_vat =sum_vat+rs3.getDouble(4);
			sum_gross =sum_gross+rs3.getDouble(5);
			m_row_space = m_row_space +25;
						
			more3 = rs3.next();
			}
			
			g2d.drawString("---------------------------------------------------------------------------------------",279,m_y_position+m_row_space+304);
			m_row_space = m_row_space +15;
			
			g2d.drawString("Total",288,m_y_position+m_row_space+304);
			g2d.drawString(nf.format(sum_net_rent),354,m_y_position+m_row_space+304);
			g2d.drawString(nf.format(sum_vat),456,m_y_position+m_row_space+304);
			g2d.drawString(nf.format(sum_gross),508,m_y_position+m_row_space+304);
			
			m_row_space = m_row_space +15;
			g2d.drawString("---------------------------------------------------------------------------------------",279,m_y_position+m_row_space+304);
			
			m_row_space = m_row_space +45;
			
			
			
			g2d.drawString("TIMELY PAYMENT WOULD BE APPRECIATED",216,m_y_position+m_row_space+304); //468
			m_row_space = m_row_space +15;			
			g2d.drawString("* Cheque payments should be made only in favour of Lakderana Investments Limited - Crossed Account ",50,m_y_position+m_row_space+304); //483
			m_row_space = m_row_space +15;	
			g2d.drawString(" payee only.",50,m_y_position+m_row_space+304); //498
			m_row_space = m_row_space +15;	
			g2d.drawString("* If the payment is made by a third party cheque, it will be accepted by us only if it is endorsed by the lessee.",50,m_y_position+m_row_space+304); //513
			m_row_space = m_row_space +15;	
			g2d.drawString("* In the event you are settling the payment in cash, please ensure that you obtain receipt immediately from our officer.",50,m_y_position+m_row_space+304); //528
			m_row_space = m_row_space +15;	
			g2d.drawString("* Official receipts for cheque/cash payments will be posted within 7 days. If not received, please bring this to the notice",50,m_y_position+m_row_space+304); //543
			m_row_space = m_row_space +15;	
			g2d.drawString("  of AGM - Finance and Treasury.",50,m_y_position+m_row_space+304); //558
			
			///---------------tax invoice details
			
			
			
			
			if(m_tax_no!=null){
			String m_finance_no ="";
			String m_due_date1   ="";
			
			//COMMENT BY NUWAN DE SILVA ON 24-07-2008
			/*rs3 = stmt3.executeQuery (" SELECT "+
				    " FINANCE_NO, "+
						" TO_CHAR(DUE_DATE,'DD-MM-YYYY') "+
				    " FROM LAKDL.AF_CO_PRO_INVOICE "+
				    " WHERE GROUP_INV_NO=UPPER('"+m_invoice_no+"') AND INVOICE_TYPE=UPPER('"+m_inv_type+"') AND ACTIVE_STATUS='Y' ");
			*/
			
			rs3 = stmt3.executeQuery (" SELECT "+
				    " FINANCE_NO, "+
						" TO_CHAR(DUE_DATE,'DD-MM-YYYY') "+
				    " FROM LAKDL.AF_CO_PRO_INVOICE "+
				    " WHERE INVOICE_NO=UPPER('"+m_invoice_no+"') AND INVOICE_TYPE=UPPER('"+m_inv_type+"') AND ACTIVE_STATUS='Y' ");
			
			
			 more3 = rs3.next();
			while(more3){
			
		m_finance_no	= rs3.getString(1); 
		m_due_date1    = rs3.getString(2);
		
		rs2 = stmt2.executeQuery (" SELECT "+
											" LAKDL.AF_CO_GET_GROSS_RENTAL_ARREARS('"+m_finance_no+"','"+m_due_date1+"'), "+
											" LAKDL.AF_CO_GET_CUR_MONTH_RENTAL_DUE('"+m_finance_no+"','"+m_due_date1+"'), "+
											" LAKDL.AF_CO_GET_RENT_RECOV_THIS_MONH('"+m_finance_no+"','"+m_due_date1+"'), "+
											" TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_due_date1+"','DD-MM-YYYY'),-1)),'DD-MM-YYYY') ,"+ 
											" TO_CHAR(LAST_DAY(TO_DATE('"+m_due_date1+"','DD-MM-YYYY')),'DD-MM-YYYY') "+
											" FROM DUAL ");
			boolean more2 = rs2.next();
			
			
			if(more2){
			
			g2d.drawString(" -------------------------------------------------------",50,m_y_position+558);
			g2d.drawString(" Statement of Accounts as at" +rs2.getString(5),50,m_y_position+558);
			
			g2d.drawString(" Gross rental Arrears as on "+rs2.getString(4),50,m_y_position+558);
			g2d.drawString("  -",100,m_y_position+558);
			g2d.drawString(nf.format(rs2.getDouble(1)),200,m_y_position+558);  
			
			g2d.drawString("Gross rental Arrears as on "+m_date,50,m_y_position+558);
			g2d.drawString("  -",100,m_y_position+558);
			g2d.drawString(nf.format(rs2.getDouble(2)),200,m_y_position+558); 
			
			g2d.drawString("Gross rental Arrears as on "+rs2.getString(4),50,m_y_position+558);
			g2d.drawString("  -",100,m_y_position+558);
			g2d.drawString(nf.format(rs2.getDouble(3)),200,m_y_position+558); 
			
			
			g2d.drawString("Gross rental Recovered during "+rs2.getString(5),100,m_y_position+558);
			
			g2d.drawString("Overdue Interest as on "+rs2.getString(5),100,m_y_position+558); 
			
			g2d.drawString("Insuarance Due "+rs2.getString(5),100,m_y_position+558);
			
			g2d.drawString("Other Due as on "+rs2.getString(5),100,m_y_position+558);
			
			g2d.drawString("Total Due as on "+rs2.getString(5),100,m_y_position+558);
			}
					
			
			more3 = rs3.next();
			}
			
			}
			
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
			
		
			
		//}  //end of for

	}
	catch(Exception ex){
	}
	
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	
	}
}
