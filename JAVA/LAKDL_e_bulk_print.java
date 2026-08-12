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

class Invoice_bulk_print implements Printable {

public String m_group_invno;
public String m_inv_type;
public String m_client_code;

java.text.NumberFormat nf;

public Invoice_bulk_print(String m_rec1,String m_rec2,String m_rec3){

nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_group_invno=m_rec1;
m_inv_type=m_rec3;
m_client_code=m_rec2;
}

public Invoice_bulk_print(){

}

public int print (Graphics g, PageFormat pageFormat, int page) {
	
	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (), pageFormat.getImageableY ());
		
	try{
	
		LAKDL_print_methods m_print_method=new LAKDL_print_methods();
		
		Connection conn;
		ResultSet rs;
		Statement stmt;
	
		String m_schema_name = m_print_method.schema_name.trim();		
		
		conn=m_print_method.get_print_connection();
		stmt=conn.createStatement();
	
		g2d.setPaint (Color.black);
		
		g2d.setStroke (new BasicStroke (5));
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		double titleX = (pageFormat.getImageableWidth()/2);
		double titleY = 72/2;
		
		Font titleFont =new Font ("Tahoma",Font.PLAIN,7);
		g2d.setFont(titleFont);
		
		String m_vat_status="";
		 
		rs=stmt.executeQuery (" SELECT "+
	  " CLIENT_CODE, "+
	  " FULL_NAME, "+
	  " NVL(ADDRESS1,'-'), "+
	  " NVL(ADDRESS2,'-'), "+
	  " "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) CITY_NAME ,"+
		" NVL(VAT_REG_NO,'-') VAT_REG_NO, "+
		" TO_CHAR(SYSDATE,'DD-MON-YYYY') "+
	  " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
	  " WHERE CLIENT_CODE='"+m_client_code+"' ");
			
			System.out.println("m_client_code"+m_client_code);
		if(rs.next()){
			g2d.drawString(rs.getString(7),36,108);
			g2d.drawString(rs.getString(2),36,118);//10
			if(!rs.getString(3).equals("-")){
				g2d.drawString(rs.getString(3),36,128);
			}
			if(!rs.getString(4).equals("-")){
				g2d.drawString(rs.getString(4),36,138);
			}
			g2d.drawString(rs.getString(5),36,148);
			g2d.drawString("LAKDL VAT NO",324,138);
			g2d.drawString("1304010762-7000",453,138);
			g2d.drawString("INVOICE NO",324,148);
			g2d.drawString(m_group_invno,453,148);
			m_vat_status=rs.getString(6);
		}
		
		titleFont = new Font ("Tahoma",Font.BOLD,12);
		g2d.setFont(titleFont);
		
		if(m_vat_status.equals("-")){
			g2d.drawString("INVOICE",260,72);
		}
		else{
			g2d.drawString("TAX INVOICE",260,72);
		}
		
		titleFont = new Font ("Tahoma",Font.PLAIN,7);
		g2d.setFont(titleFont);
		
		
		rs=stmt.executeQuery(" SELECT "+
    " INVOICE_NO, "+//1
    " TO_CHAR(DUE_DATE,'DD/MM/YYYY'), "+//2
		" NET_AMOUNT, "+//3
		" VAT_AMOUNT ,"+//4
    " TOTAL_AMOUNT, "+//5
		" TO_CHAR(DUE_DATE,'MON YYYY'), "+//6
		" FINANCE_NO "+//7
    " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
    " WHERE GROUP_INV_NO=UPPER('"+m_group_invno+"') AND INVOICE_TYPE=UPPER('"+m_inv_type+"') AND ACTIVE_STATUS='Y' ");

		boolean more=rs.next();
		
		if(more){
			g2d.drawString("Rental Due for the Month    "+rs.getString(6),36,200);
			g2d.drawString("(Amount in Rupees)",453,200);
		}
		
		int i=1;
		
		g2d.drawString("Sno",36,220);
		g2d.drawString("Finance No",72,220);//108
		g2d.drawString("Invoice No",144,220);//180
		g2d.drawString("Due No",216,220);//252
		g2d.drawString("Net Rent",288,220);//324
		g2d.drawString("VAT %",360,220);//396
		g2d.drawString("VAT ",432,220);//468
		g2d.drawString("Gross Rent",504,220);//540
		
		int mtop=240;
		
		double m_net=0;
		double m_vat=0;
		double m_gross=0;
		int num=0;
		String m_value="";
		
		while(more){
			g2d.drawString(""+i,36,mtop);
			g2d.drawString(rs.getString(7),72,mtop);
			g2d.drawString(rs.getString(1),144,mtop);
			g2d.drawString(rs.getString(2),216,mtop);
			g2d.drawString(nf.format(rs.getDouble(3)),288,mtop);
			g2d.drawString("15.00",360,mtop);
			g2d.drawString(nf.format(rs.getDouble(4)),432,mtop);
			g2d.drawString(nf.format(rs.getDouble(5)),504,mtop);
			m_net=m_net+rs.getDouble(3);
			m_vat=m_vat+rs.getDouble(4);
			m_gross=m_gross+rs.getDouble(5);
			more=rs.next();
			mtop=mtop+10;
			i++;
		}
		
		mtop=mtop+10;
		g2d.drawString("Total",216,mtop);
		
		num=0;i=0;
		m_value=nf.format(m_net);
		while(i<m_value.length()){
	  num=num+fontMetrics.charWidth(m_value.charAt(i));
	  i++;
		}
		g2d.drawString(m_value,340-num,mtop);
		
		num=0;i=0;
		m_value=nf.format(m_vat);
		while(i<m_value.length()){
	  num=num+fontMetrics.charWidth(m_value.charAt(i));
	  i++;
		}
		g2d.drawString(m_value,470-num,mtop);
		
		num=0;i=0;
		m_value=nf.format(m_gross);
		while(i<m_value.length()){
	  num=num+fontMetrics.charWidth(m_value.charAt(i));
	  i++;
		}
		g2d.drawString(m_value,560-num,mtop);
		
		mtop=mtop+10;
		mtop=mtop+10;
		g2d.drawString("TIMELY PAYMENT WOULD BE APPRECIATED",200,mtop);
		mtop=mtop+10;
		mtop=mtop+10;
		g2d.drawString("* Cheque payments should be made only in favour of Lakderana Investments Limited - Crossed Account payee only.",36,mtop);
		mtop=mtop+10;
		g2d.drawString("* If the payment is made by a third party cheque, it will be accepted by us only if it is endorsed by the lessee.",36,mtop);
		mtop=mtop+10;
		g2d.drawString("* In the event you are settling the payment in cash, please ensure that you obtain receipt immediately from our officer.",36,mtop);
		mtop=mtop+10;
		g2d.drawString("* Official receipts for cheque/cash payments will be posted within 7 days. If not received, please bring this to the notice of Senior Manager Finance.",36,mtop);
		
		
	}
	catch(Exception e){
	}
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	}
}
