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

class Print_cheques_book_bulk implements Printable {

public String[] m_payment_code;
public int mm_page_count;
public int mm_page_size;
public int mm_page_size2;

java.text.NumberFormat nf;

public Print_cheques_book_bulk(String[] m_rec){
nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_payment_code=m_rec;

}

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
	
	LAKDL_print_methods m_print_method=new LAKDL_print_methods();
	String m_schema_name = m_print_method.schema_name.trim();		
	Connection conn;
	ResultSet rs;
	Statement stmt;
	
	conn=m_print_method.get_print_connection();
	stmt=conn.createStatement();
	
	for(int z=0;z<=m_payment_code.length;z++){
		
			m_y_position=m_y_position+(z*252);
	
			rs=stmt.executeQuery("SELECT PAYMENT_CODE,"+//1
			" DECODE(PAY_3RD_PARTY_STATUS,'Y',UPPER(PAY_3RD_PARTY_NAME),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)),"+//2
			" PAYMENT_AMOUNT,"+//3
			" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+//4
			" TO_CHAR(PAY_DATE,'DD'), "+//5
			" TO_CHAR(PAY_DATE,'MM'), "+//6
			" TO_CHAR(PAY_DATE,'YY') "+//7
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
			" WHERE PAYMENT_CODE='"+m_payment_code[z].toString()+"'");
			
			if(rs.next()){
				m_pay_name=rs.getString(2);
				m_payamount=nf.format(rs.getDouble(3));
				m_payamountword=m_print_method.numbersToChar(m_print_method.met_unformat_number(nf.format(rs.getDouble(3))));
				m_dd1=rs.getString(5);
				m_mm1=rs.getString(6);
				m_yy1=rs.getString(7);
				m_pay_ref=rs.getString(1);
				//m_date=rs.getString(8);
				//m_ref_no=rs.getString(9);
				m_vdate=rs.getString(4);
				//m_pay_type=rs.getString(10);
				//m_settle_mode=rs.getString(11);
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
			int m_x_pos=36;
			
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
				
				if(mcharwith>450){
		
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
			/*g2d.drawString(m_pay_ref,20,m_y_position+72);
			g2d.drawString("**"+m_payamount+"**",20,m_y_position+100);
			g2d.drawString(m_vdate,20,m_y_position+140); 
			g2d.drawString(m_ref_no,20,m_y_position+160); 
			g2d.drawString(m_date,20,m_y_position+180); 
			g2d.drawString(m_pay_type,20,m_y_position+200); */
			///----------------------------------------------------
			
			//-------------------------------------------------cheque body
			titleFont = new Font ("Arial",Font.PLAIN,7);
			g2d.setFont(titleFont);
			m_x_pos=m_x_pos+14;
			g2d.drawString(m_dd1.substring(0,1),m_x_pos+348,m_y_position+32);//180
			g2d.drawString(m_dd1.substring(1,2),m_x_pos+367,m_y_position+32);
			g2d.drawString(m_mm1.substring(0,1),m_x_pos+386,m_y_position+32);
			g2d.drawString(m_mm1.substring(1,2),m_x_pos+404,m_y_position+32);
			g2d.drawString(m_yy1.substring(0,1),m_x_pos+450,m_y_position+32);
			g2d.drawString(m_yy1.substring(1,2),518,m_y_position+32);
			
			g2d.drawString(m_pay_name.toUpperCase(),m_x_pos+50,m_y_position+72);	

			if(m_flag1){
			g2d.drawString(m_print_num_line_1,m_x_pos+60,m_y_position+100); 
			}
			if(m_flag2){
			g2d.drawString(m_print_num_line_2,m_x_pos+60,m_y_position+120); 
			}
			if(m_flag3){
			g2d.drawString(m_print_num_line_3,m_x_pos+60,m_y_position+130); 
			}
			
			titleFont = new Font ("Arial",Font.PLAIN,8);
			g2d.setFont(titleFont);
			g2d.drawString("**"+m_payamount+"**",m_x_pos+360,m_y_position+120);
			
			titleFont = new Font ("Arial",Font.BOLD,6);
			g2d.setFont(titleFont);
			
			g2d.drawString("....................................",m_x_pos+340,m_y_position+180);
			g2d.drawString("Authorised Signatory",m_x_pos+340,m_y_position+194);//310
			g2d.drawString("....................................",m_x_pos+426,m_y_position+180);
			g2d.drawString("Authorised Signatory",m_x_pos+426,m_y_position+194);//396
			
			g2d.drawString("LAKDERANA INVESTMENTS LIMITED.",m_x_pos+339,m_y_position+144);
			g2d.drawString("Co. Reg. No.    ",m_x_pos+108,m_y_position+160);
			
			g2d.drawString("______________________",m_x_pos+108,m_y_position+172);
			g2d.drawString("     A/C PAYEE ONLY   ",m_x_pos+108,m_y_position+180);
			g2d.drawString("______________________",m_x_pos+108,m_y_position+188);
			
			
		}//end of for

	}
	catch(Exception ex){
	}
	
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	
	}
}
