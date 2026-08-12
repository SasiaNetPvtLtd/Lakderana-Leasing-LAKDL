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

class Print_Insurance_Book implements Printable {

public String m_finance_no="",m_invoice_no="";
public int mm_page_count;
public int mm_page_size;
public int mm_page_size2;

java.text.NumberFormat nf;

public Print_Insurance_Book(String finance_no,String invoice_no){
nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_finance_no=finance_no;
m_invoice_no=invoice_no;
}

public int print (Graphics g,PageFormat pageFormat, int page) {

  //insuarance variables
	String m_Letter_date="";
	String m_full_name="",m_add1="",m_add2="",m_city_name="",m_nic_no="";
	String m_asset_desc="", m_renewal_date="",m_reg_no="";
	double m_premium=0,m_sum_insured=0;

	
	
	int m_y_position=0;
	int m_row_space =0;

	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (),pageFormat.getImageableY());
	
	try{
	
	LAKDL_print_methods m_print_method=new LAKDL_print_methods();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
	Connection conn;
	ResultSet rs;
	Statement stmt;
	
	conn=m_print_method.get_print_connection();
	
	
					
	stmt=conn.createStatement();
	
	
	//A4 page height -- 835.2
	//A4 page weidth -- 590.4
	 //m_row_space =25;
		
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'MONTH DD,YYYY') FROM DUAL ");
				
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
				/*	String Client_Data=" SELECT  "+
						" 'CLIENT', "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C', UPPER(FULL_NAME)),' ') ,   "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
						" NVL(UPPER(LAKDL.AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
						" FROM LAKDL.AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE =  "+
						" (SELECT  "+
						" CLIENT_CODE "+ 
						" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS  "+
						" WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')) "+
			
						" UNION "+
			
						" SELECT  "+
						" 'CO-APPLICANT', "+
						" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C', UPPER(FULL_NAME)),   "+
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+
						" NVL(UPPER(LAKDL.AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
						" FROM LAKDL.AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE =  "+
						" (SELECT  "+
		  			" CO_APPLICANT  "+
						" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS  "+
						" WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')) ";
						
			*/		
			
			
						String Client_Data=" SELECT  "+
						" 'CLIENT', "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C', UPPER(FULL_NAME)),' ') ,   "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
						" NVL(UPPER(LAKDL.AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
						" FROM LAKDL.AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE =  "+
						" (SELECT  "+
						" CLIENT_CODE "+ 
						" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS  "+
						" WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')) ";
									
			rs = stmt.executeQuery(Client_Data);
			more = rs.next();		
			
			while(more){	
			m_full_name=rs.getString(2);
			m_add1=rs.getString(3);
			m_add2=rs.getString(4);
			m_city_name=rs.getString(5);
			m_nic_no=rs.getString(6);
			more = rs.next();		
			}
			
			
			 rs = stmt.executeQuery(" SELECT A.POLICY_NO, A.ASSET_DESCRIPTION,TO_CHAR(A.DUE_DATE ,'DD-MM-YYYY') ,B.REG_NO ,"+
			 " NVL(PREMIUM,0) , NVL(SUM_INSSURED,0) "+
			 " FROM  LAKDL.AF_IS_PRO_ASET_INSUR_DETA A , LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS B "+
			 " WHERE A.PRO_INVOICE_NO=B.INVOICE_NO "+
			 " AND A.FINANCE_NO     = '"+m_finance_no+"'   "+
			 " AND   A.PRO_INVOICE_NO = '"+m_invoice_no+"' ");
			more = rs.next();		
			while(more){
			m_asset_desc   = rs.getString(2);
			m_renewal_date = rs.getString(3);
			m_reg_no       = rs.getString(4);
			m_premium      = rs.getDouble(5);
			m_sum_insured  = rs.getDouble(6);
			}
				
	
			g2d.setPaint (Color.black);
			
			g2d.setStroke (new BasicStroke(5));
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
						

			Font titleFont = new Font ("Times New Roman",Font.PLAIN,8);
			g2d.setFont(titleFont);
			
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
			
			/*g2d.drawString(m_receipt_num,100,m_y_position+70);	
			g2d.drawString(m_print_name,150,m_y_position+90);
			g2d.drawString(m_Letter_date,450,m_y_position+70);
			*/
			
			g2d.drawString(m_Letter_date,50,m_y_position+137);
			g2d.drawString(m_full_name,50,m_y_position+151);
			g2d.drawString(m_add1,50,m_y_position+166);
			g2d.drawString(m_add2,50,m_y_position+180); 
			g2d.drawString(m_city_name,50,m_y_position+195);
			
			g2d.drawString("Dear Sir",50,m_y_position+215);
			g2d.drawString("INSURANCE DETAILS",50,m_y_position+215);
			
			g2d.drawString("Lease Agreement No ",50,m_y_position+151);
			g2d.drawString("Vehicle No",360,m_y_position+166);
			g2d.drawString(m_finance_no,482,m_y_position+151); 
			g2d.drawString(m_reg_no,482,m_y_position+166); 
			
			g2d.drawString("This is to inform you that the insurance policy on the above vehicle was due for renewal on " +m_renewal_date+ " as per our records. As you aware it is very essential that you to submit the insurance policy in order to safeguard possible unexpected losses.",216,m_y_position+m_row_space+304); //468
			m_row_space = m_row_space +15;			
			g2d.drawString("We are kindly requesting you to send a copy of the insurance certificate and the schedule /renewal endorsement to No. 100,   Buthgamuwa Road,  Rajagiriya to make sure the insurance cover is in force.",216,m_y_position+m_row_space+304); //468
			m_row_space = m_row_space +15;			
			g2d.drawString("If you are unable to send a copy of the above documents within 15 Days of this letter we are reluctantly compelled to debit your lease account and do the insurance for the mutual benefit.",216,m_y_position+m_row_space+304); //468
			m_row_space = m_row_space +15;			
			g2d.drawString("If you need further clarification on this matter please do not hesitate to contact:",216,m_y_position+m_row_space+304); //468
			m_row_space = m_row_space +15;			
			g2d.drawString("Mr.Damitha Girihagama",216,m_y_position+m_row_space+304); //468
			g2d.drawString("Snr.Manager Insurance 5577577/0777598205",482,m_y_position+304); 
			m_row_space = m_row_space +15;							
			g2d.drawString("Mr.Pubudu Algama",216,m_y_position+m_row_space+304); //468
			g2d.drawString("Assistant Manager Insurance 5577577/0773136568",482,m_y_position+304); 
			m_row_space = m_row_space +15;							
			g2d.drawString("If you have done the insurance through Lakderana Investments Limited please ignore this letter",216,m_y_position+m_row_space+304); //468
			m_row_space = m_row_space +15;							
			g2d.drawString("This is a computer-generator statement. Signature is not required.",216,m_y_position+m_row_space+304); //468			
						
			
		//}  //end of for

	}
	catch(Exception ex){
	}
	
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	
	}
}
