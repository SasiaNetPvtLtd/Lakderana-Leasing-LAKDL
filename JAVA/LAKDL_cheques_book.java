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

class Print_cheques_book implements Printable {

public String m_payment_code;

java.text.NumberFormat nf;

public Print_cheques_book(String m_rec){

nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_payment_code=m_rec;
}

public Print_cheques_book(){

}

public int print (Graphics g, PageFormat pageFormat, int page) {
	
	String m_pay_name="";
	String m_payamount="";
	String m_payamountword="";
	String m_dd1="";
	String m_mm1="";
	String m_yy1="";
	String m_html_client_url="";
	
	try{
		LAKDL_print_methods m_print_method=new LAKDL_print_methods();
		
		Connection conn;
		ResultSet rs;
		Statement stmt;
	
		String m_schema_name = m_print_method.schema_name.trim();		
		m_html_client_url=m_print_method.html_client_url.trim();
		
		conn=m_print_method.get_print_connection();
		stmt=conn.createStatement();
		
		rs=stmt.executeQuery("SELECT PAYMENT_CODE,"+
		" DECODE(PAY_3RD_PARTY_STATUS,'N',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),PAY_3RD_PARTY_NAME),"+
		" PAYMENT_AMOUNT,"+
		" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+//4
		" TO_CHAR(PAY_DATE,'DD'), "+//5
		" TO_CHAR(PAY_DATE,'MM'), "+//6
		" TO_CHAR(PAY_DATE,'YY') "+//7
		" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
		" WHERE PAYMENT_CODE='"+m_payment_code+"'");
		
		if(rs.next()){
		m_pay_name=rs.getString(2);
		m_payamount=nf.format(rs.getDouble(3));
		m_payamountword=m_print_method.numbersToChar(m_print_method.met_unformat_number(nf.format(rs.getDouble(3))));
		m_dd1=rs.getString(5);
		m_mm1=rs.getString(6);
		m_yy1=rs.getString(7);
		}
	}
	catch(Exception ex){
	}
	
	Graphics2D g2d = (Graphics2D) g;
	
	g2d.translate (pageFormat.getImageableX (), pageFormat.getImageableY ());
	
	g2d.setPaint (Color.black);
	
	g2d.setStroke (new BasicStroke(5));
	
	FontMetrics fontMetrics = g2d.getFontMetrics();
	double titleX = (pageFormat.getImageableWidth()/2);
	double titleY = 72/2;
	
	String m_payamount_all="--"+m_payamountword.toUpperCase()+" ONLY--";
	
	Font titleFont = new Font ("Arial",Font.BOLD,9);
	g2d.setFont(titleFont);
	
	/*g2d.drawString(m_pay_name.toUpperCase(),51,72);
	g2d.drawString("**"+m_payamount+"**",360,123);*/
	
	g2d.drawString(m_pay_name.toUpperCase(),100,80);
	g2d.drawString("**"+m_payamount+"**",400,120);
	g2d.drawString(m_payamount_all,100,110);
	
	String m_paytext="";
	
	int i=0;
	int num=0;
	int m_y=100;
	int msapce=0;
	/*
	while(i<m_payamount_all.length()){
	  num=num+fontMetrics.charWidth(m_payamount_all.charAt(i));
		if(m_payamount_all.charAt(i)==(' ')){
		msapce=i;
		}
		if(num>400){
			g2d.drawString(m_payamount_all.substring(0,msapce),51,m_y);
			m_y=m_y+22;
			m_payamount_all=m_payamount_all.substring(msapce,m_payamount_all.length());
			num=0;
			break;
		}
		i++;
	}
	
	num=0;
	while(i<m_payamount_all.length()){
	  num=num+fontMetrics.charWidth(m_payamount_all.charAt(i));
	}
		
	if(num<=400){
	g2d.drawString(m_payamount_all.substring(0,m_payamount_all.length()),51,m_y);
	m_y=m_y+22;
	m_payamount_all="";
	}

	while(i<m_payamount_all.length()){
	  num=num+fontMetrics.charWidth(m_payamount_all.charAt(i));
		if(m_payamount_all.charAt(i)==(' ')){
		msapce=i;
		}
		if(num>400){
			g2d.drawString(m_payamount_all.substring(0,msapce),51,m_y);
			m_y=m_y+22;
			num=0;
			m_payamount_all="";
		}
	}
	
	if(m_payamount_all.length()<=400){
	g2d.drawString(m_payamount_all.substring(0,m_payamount_all.length()),51,m_y);
	m_y=m_y+22;
	}
	*/
	g2d.drawString(m_dd1.substring(0,1),395,32);//
	g2d.drawString(m_dd1.substring(1,2),415,32);
	g2d.drawString(m_mm1.substring(0,1),440,32);
	g2d.drawString(m_mm1.substring(1,2),460,32);
	g2d.drawString(m_yy1.substring(0,1),510,34);
	g2d.drawString(m_yy1.substring(1,2),520,34);
	/*g2d.drawString(m_dd1.substring(2,2),360,36);
	g2d.drawString(m_mm1,396,36);
	g2d.drawString(m_yy1,468,36);*/
	
	try{
	 
		//Image image1 = Toolkit.getDefaultToolkit().getImage("\\\\192.168.100.19\\cheque_images\\chq_main.gif");
		Image image2 = Toolkit.getDefaultToolkit().getImage("D:\\cheque_images\\AuthoSign.gif");
		
		//g2d.drawImage(image1,216,36,60,15,null);
		/*g2d.drawImage(image2,288,180,70,40,null);
		g2d.drawImage(image2,396,180,70,40,null);*/
		g2d.drawImage(image2,324,160,70,40,null);
		g2d.drawImage(image2,432,160,70,40,null);
	}
	catch(Exception exp1){
	System.out.println(exp1.toString());
	}
       
	/*int i=0;
	int num=0;
	while(i<"Name & Address of Broker".length()){
	  num=num+fontMetrics.charWidth("Name & Address of Broker".charAt(i));
	  i++;
	
	}
	//System.out.print("num="+num);
	g2d.drawString ("Name & Address of Broker  ***",300-num,30);
	
	 i=0;
	num=0;
	while(i<"Name & Address of Broker 123 eeeeeeeee".length()){
	 num=num+fontMetrics.charWidth("Name & Address of Broker 123 ".charAt(i));
	 i++;
	
	}
	//System.out.print("num="+num);
	g2d.drawString ("Name & Address of Broker 123 eeeeeeeee",300-num,50);*/
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	}
}
