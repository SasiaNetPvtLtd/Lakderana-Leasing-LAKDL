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

class Print_cheques_book_bulk_test implements Printable {

public String m_payment_code;
public int mm_page_count;
public int mm_page_size;
public int mm_page_size2;

java.text.NumberFormat nf;

public Print_cheques_book_bulk(String m_rec){
nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_payment_code=m_rec;
/*
for (int j=0;j<m_payment_code.length;j++){
		System.out.println("inside printer=="+m_payment_code[j].toString());
}
*/

}

public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
			int mcharwith=0;
			
    Graphics2D g2 = (Graphics2D) g;
    g2.translate (pf.getImageableX (),pf.getImageableY());
	  FontMetrics fontMetrics = g2.getFontMetrics();
		mcharwith=mcharwith+fontMetrics.charWidth("ttttttt".charAt(3));
					
		g2.setFont(new Font("Serif", Font.PLAIN, 36));
    g2.setPaint(Color.black);
    g2.setStroke (new BasicStroke(5));
		g2.drawString("www.java2s.com", 100, 100);
    Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
        .getImageableWidth(), pf.getImageableHeight());
    g2.draw(outline);
    return PAGE_EXISTS;
  }
	
	
	
	/*
public int print(Graphics g, PageFormat pageFormat, int page) {
    if (page != 0)
      return NO_SUCH_PAGE;
		
	String m_pay_name="";
	String m_pay_ref="";
	String m_payamount="";
	String m_payamountword="";
	String m_dd1="";
	String m_mm1="";
	String m_yy1="";
	String m_html_client_url="";
	String m_date="";
	String m_vdate="";
	String m_ref_no="";
	String m_pay_type="";
	String m_settle_mode="";
	
	int m_y_position=0;
		
    Graphics2D g2d = (Graphics2D) g;
    g2d.translate (pageFormat.getImageableX (),pageFormat.getImageableY());
	  try{
	
	SITHMA_print_methods m_print_method=new SITHMA_print_methods();
	Connection conn;
	ResultSet rs;
	Statement stmt;
	
	conn=m_print_method.get_print_connection();
	stmt=conn.createStatement();
	int z=0;
	//for(int z=0;z<m_payment_code.length;z++){
		
			//m_y_position=m_y_position+(z*252);
			m_y_position=(z*252);
			
			/*
			rs=stmt.executeQuery("SELECT SUS_REF_NO, "+
			 " PAYEE_NAME, "+
			" PAY_AMOUNT, "+
			" TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
			" TO_CHAR(VALUE_DATE,'DD'), "+
			" TO_CHAR(VALUE_DATE,'MM'), "+
			" TO_CHAR(VALUE_DATE,'YY'), "+
			" TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS'), "+
			" REF_NO, "+
			" DECODE(PAYMENT_TYPE,'A','AGREEMENT','BOND PAYMENT'), "+
			" SETTLE_MODE "+
			" FROM CLAMF.WEBDN_TRN_INVEST_SETTL_PAYMENT "+
			" WHERE SUS_REF_NO ='"+m_payment_code.toString()+"'");	*/
		
			
			rs=stmt.executeQuery("SELECT VOU_NO, "+
			" PAYE_NAME, "+
			" PAY_AMOUNT, "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'DD-MM-YYYY'), "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'DD'), "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'MM'), "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'YYYY'), "+
			" TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS'), "+
			" PAY_REF, "+
			" NARRA1, "+
			" VOU_TYPE "+
			" FROM SITHMA.WEBAC_TRN_PAYMENT_VOUCHER "+
			" WHERE VOU_NO ='"+m_payment_code.toString()+"'");	
			
			
			String m_amt="";
			
			System.out.println("m_payment_code[z].toString()="+m_payment_code.toString()+"  m_y_position="+m_y_position);
			
			if(rs.next()){
				m_pay_name=rs.getString(2);
				m_payamount=nf.format(rs.getDouble(3));
				m_payamountword=m_print_method.numbersToChar(m_print_method.met_unformat_number(nf.format(rs.getDouble(3))));
				m_amt = m_print_method.met_unformat_number(nf.format(rs.getDouble(3)));
				m_dd1=rs.getString(5);
				m_mm1=rs.getString(6);
				m_yy1=rs.getString(7);
				m_pay_ref=rs.getString(1);
				m_date=rs.getString(8);
				m_ref_no=rs.getString(9);
				m_vdate=rs.getString(4);
				m_pay_type=rs.getString(10);
				m_settle_mode=rs.getString(11);
			}
	
			
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
		
			while(i<m_payamount_all.length()){
			  mcharwith=mcharwith+fontMetrics.charWidth(m_payamount_all.charAt(i));
				mcharcount++;
				
				if(m_payamount_all.charAt(i)==(' ')){
				m_charspace=mcharcount;
				}
				else{
				m_charspacel=mcharcount;
				}
				
				if(mcharwith>380){
		
					mcharwith=0;
		
					if(!m_flag1){
						m_print_num_line_1=m_payamount_all.substring(0,m_charspace);
						m_flag1=true;
					}
					if(m_payamount_all.length()>m_charspace){
						m_print_num_line_2=m_payamount_all.substring(m_charspace,m_payamount_all.length());
						m_flag2=true;
					}
				}
				
				i++;
			}	
			
			if(!m_flag1){
				m_print_num_line_1=m_payamount_all;
				m_flag1=true;
			}
			
			g2d.drawString(m_amt,20,m_y_position+72);
			/*
			///-----------------------------------------conter foil
			g2d.drawString(m_pay_ref,20,m_y_position+72);
			g2d.drawString("**"+m_payamount+"**",20,m_y_position+100); 
			g2d.drawString(m_vdate,20,m_y_position+140); 
			g2d.drawString(m_ref_no,20,m_y_position+160); 
			g2d.drawString(m_date,20,m_y_position+180); 
			g2d.drawString(m_pay_type,20,m_y_position+200); 
			///----------------------------------------------------
			*/
			//-------------------------------------------------cheque body
			titleFont = new Font ("Arial",Font.PLAIN,8);
			g2d.setFont(titleFont);
			
			g2d.drawString(m_dd1.substring(0,1),520,m_y_position+26);//32
			g2d.drawString(m_dd1.substring(1,2),540,m_y_position+26);
			g2d.drawString(m_mm1.substring(0,1),555,m_y_position+26);
			g2d.drawString(m_mm1.substring(1,2),575,m_y_position+26);
			g2d.drawString(m_yy1.substring(0,1),625,m_y_position+26);
			g2d.drawString(m_yy1.substring(1,2),650,m_y_position+26);
			
			titleFont = new Font ("Arial",Font.PLAIN,9);
			g2d.setFont(titleFont);
			
			g2d.drawString(m_pay_name.toUpperCase(),230,m_y_position+66); //72
			
			
			g2d.drawString("**"+m_payamount+"**",540,m_y_position+114); //120
			
			titleFont = new Font ("Arial",Font.PLAIN,8);
			g2d.setFont(titleFont);
			if(m_flag1){
			g2d.drawString(m_print_num_line_1,230,m_y_position+94); //100
			}
			if(m_flag2){
			g2d.drawString(m_print_num_line_2,216,m_y_position+114); //120 
			}
			if(m_flag3){
			g2d.drawString(m_print_num_line_3,216,m_y_position+124); //130 
			}
			
		//}//end of for
      return PAGE_EXISTS;

	}
	catch(Exception ex){
	return PAGE_EXISTS;
	} 
		
  }	*/
}

/*
public int print (Graphics g,PageFormat pageFormat, int page) {
	
	String m_pay_name="";
	String m_pay_ref="";
	String m_payamount="";
	String m_payamountword="";
	String m_dd1="";
	String m_mm1="";
	String m_yy1="";
	String m_html_client_url="";
	String m_date="";
	String m_vdate="";
	String m_ref_no="";
	String m_pay_type="";
	String m_settle_mode="";
	
	int m_y_position=0;

	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (),pageFormat.getImageableY());
	
	try{
	
	SITHMA_print_methods m_print_method=new SITHMA_print_methods();
	Connection conn;
	ResultSet rs;
	Statement stmt;
	
	conn=m_print_method.get_print_connection();
	stmt=conn.createStatement();
	int z=0;
	//for(int z=0;z<m_payment_code.length;z++){
		
			//m_y_position=m_y_position+(z*252);
			m_y_position=(z*252);
			
			/ *
			rs=stmt.executeQuery("SELECT SUS_REF_NO, "+
			 " PAYEE_NAME, "+
			" PAY_AMOUNT, "+
			" TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
			" TO_CHAR(VALUE_DATE,'DD'), "+
			" TO_CHAR(VALUE_DATE,'MM'), "+
			" TO_CHAR(VALUE_DATE,'YY'), "+
			" TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS'), "+
			" REF_NO, "+
			" DECODE(PAYMENT_TYPE,'A','AGREEMENT','BOND PAYMENT'), "+
			" SETTLE_MODE "+
			" FROM CLAMF.WEBDN_TRN_INVEST_SETTL_PAYMENT "+
			" WHERE SUS_REF_NO ='"+m_payment_code.toString()+"'");	* /
		
			
			rs=stmt.executeQuery("SELECT VOU_NO, "+
			" PAYE_NAME, "+
			" PAY_AMOUNT, "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'DD-MM-YYYY'), "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'DD'), "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'MM'), "+
			" TO_CHAR(NVL(VALUE_DATE,SYSDATE),'YYYY'), "+
			" TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS'), "+
			" PAY_REF, "+
			" NARRA1, "+
			" VOU_TYPE "+
			" FROM SITHMA.WEBAC_TRN_PAYMENT_VOUCHER "+
			" WHERE VOU_NO ='"+m_payment_code.toString()+"'");	
			
			
			
			
			System.out.println("m_payment_code[z].toString()="+m_payment_code.toString()+"  m_y_position="+m_y_position);
			
			g2d.drawString("TESTTESTTESTTEST MATTA",20,72);
			
			if(rs.next()){
				m_pay_name=rs.getString(2);
				m_payamount=nf.format(rs.getDouble(3));
				m_payamountword=m_print_method.numbersToChar(m_print_method.met_unformat_number(nf.format(rs.getDouble(3))));
				m_dd1=rs.getString(5);
				m_mm1=rs.getString(6);
				m_yy1=rs.getString(7);
				m_pay_ref=rs.getString(1);
				m_date=rs.getString(8);
				m_ref_no=rs.getString(9);
				m_vdate=rs.getString(4);
				m_pay_type=rs.getString(10);
				m_settle_mode=rs.getString(11);
			}
	
			
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
		
			while(i<m_payamount_all.length()){
			  mcharwith=mcharwith+fontMetrics.charWidth(m_payamount_all.charAt(i));
				mcharcount++;
				
				if(m_payamount_all.charAt(i)==(' ')){
				m_charspace=mcharcount;
				}
				else{
				m_charspacel=mcharcount;
				}
				
				if(mcharwith>380){
		
					mcharwith=0;
		
					if(!m_flag1){
						m_print_num_line_1=m_payamount_all.substring(0,m_charspace);
						m_flag1=true;
					}
					if(m_payamount_all.length()>m_charspace){
						m_print_num_line_2=m_payamount_all.substring(m_charspace,m_payamount_all.length());
						m_flag2=true;
					}
				}
				
				i++;
			}	
			
			if(!m_flag1){
				m_print_num_line_1=m_payamount_all;
				m_flag1=true;
			}
			///-----------------------------------------conter foil
			g2d.drawString(m_pay_ref,20,m_y_position+72);
			g2d.drawString("**"+m_payamount+"**",20,m_y_position+100); 
			g2d.drawString(m_vdate,20,m_y_position+140); 
			g2d.drawString(m_ref_no,20,m_y_position+160); 
			g2d.drawString(m_date,20,m_y_position+180); 
			g2d.drawString(m_pay_type,20,m_y_position+200); 
			///----------------------------------------------------
			
			//-------------------------------------------------cheque body
			titleFont = new Font ("Arial",Font.PLAIN,8);
			g2d.setFont(titleFont);
			
			g2d.drawString(m_dd1.substring(0,1),520,m_y_position+26);//32
			g2d.drawString(m_dd1.substring(1,2),540,m_y_position+26);
			g2d.drawString(m_mm1.substring(0,1),555,m_y_position+26);
			g2d.drawString(m_mm1.substring(1,2),575,m_y_position+26);
			g2d.drawString(m_yy1.substring(0,1),625,m_y_position+26);
			g2d.drawString(m_yy1.substring(1,2),650,m_y_position+26);
			
			titleFont = new Font ("Arial",Font.PLAIN,9);
			g2d.setFont(titleFont);
			
			g2d.drawString(m_pay_name.toUpperCase(),230,m_y_position+66); //72
			
			
			g2d.drawString("**"+m_payamount+"**",540,m_y_position+114); //120
			
			titleFont = new Font ("Arial",Font.PLAIN,8);
			g2d.setFont(titleFont);
			if(m_flag1){
			g2d.drawString(m_print_num_line_1,230,m_y_position+94); //100
			}
			if(m_flag2){
			g2d.drawString(m_print_num_line_2,216,m_y_position+114); //120 
			}
			if(m_flag3){
			g2d.drawString(m_print_num_line_3,216,m_y_position+124); //130 
			}
			
		//}//end of for
      return PAGE_EXISTS;

	}
	catch(Exception ex){
	}
	
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	
	}
}*/
