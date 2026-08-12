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

class Pre_Reminder_bulk_print implements Printable {

public String m_facility_no;
public String m_client_no;
public String m_debtor_no;
public String m_date;
public String m_status;
java.text.NumberFormat nf;

public Pre_Reminder_bulk_print(String mm_facility_no,String mm_client_code,String mm_debtor_no,String mm_date,String mm_status){

nf = java.text.NumberFormat.getInstance(Locale.US);
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);
m_facility_no=mm_facility_no;
m_client_no=mm_client_code;
m_debtor_no=mm_debtor_no;
m_date=mm_date;
m_status=mm_status;
}

public Pre_Reminder_bulk_print(){

}

public int print (Graphics g, PageFormat pageFormat, int page) {
	
	Graphics2D g2d = (Graphics2D) g;
		
	g2d.translate (pageFormat.getImageableX (), pageFormat.getImageableY ());
		
	try{
	
		LAKDL_print_methods m_print_method=new LAKDL_print_methods();
		
		Connection conn;
		ResultSet rs,rs1,rs2,rs3;
		Statement stmt,stmt1,stmt2,stmt3;
	
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
		 
		Font titleFont =new Font ("Tahoma",Font.PLAIN,7);
		g2d.setFont(titleFont);
		
		String m_Letter_date="";
		rs1 = stmt1.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");								
		if(rs1.next()){
		m_Letter_date=rs1.getString(1);
		}
		
		String m_client_name="";
		rs1 = stmt1.executeQuery ("SELECT CLIENT_CODE,NVL(FULL_NAME,' ') "+
		" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
		" WHERE CLIENT_CODE='"+m_client_no+"' ");
		if(rs1.next()){
		m_client_name = rs1.getString(2);
		} 
		  
		//------------------------------------------------------------
		int m_row_num=80;
		g2d.drawString("Co.Reg.No. PB75",42,m_row_num);
		m_row_num=m_row_num+10;
		/*g2d.drawString("FACTORING DIVISION OF",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("ORIENT FINANCIAL SERVICES CORPORATION LTD",42,m_row_num);
		m_row_num=m_row_num+10; 
		g2d.drawString("525, UNION PLACE ,",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("COLOMBO 02.",42,m_row_num);
		m_row_num=m_row_num+10;*/
		g2d.drawString(m_Letter_date,42,m_row_num);
		m_row_num=m_row_num+50;
		
		rs1 = stmt1.executeQuery (	" SELECT "+
				  " NVL(A.CLIENT_CODE,'-'), "+//1
				  " NVL(UPPER(A.FULL_NAME),' '), "+//2
				  " NVL(UPPER(A.REGISTERED_ADDRESS1),' '), "+//3
				  " NVL(UPPER(A.REGISTERED_ADDRESS2),' '), "+//4
				  " UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ')), "+//5
					" NVL(UPPER(B.CONTACT_PERSON),' '),"+//6
					" NVL(UPPER(B.DESIGNATION_PAYMENT),' '), "+//7
					" A.CLIENT_TYPE "+//8
				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B "+
				  " WHERE B.DEBTOR_CODE=A.CLIENT_CODE "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_no+"' "+
					" AND A.CLIENT_CODE='"+m_debtor_no+"' ");
			
		while(rs1.next()){
			if(rs1.getString(8).equals("C")){
			g2d.drawString(rs1.getString(7),42,m_row_num);
			m_row_num=m_row_num+10;
			}
			g2d.drawString(rs1.getString(2),42,m_row_num);
			m_row_num=m_row_num+10;
			g2d.drawString(rs1.getString(3),42,m_row_num);
			m_row_num=m_row_num+10;
			g2d.drawString(rs1.getString(4),42,m_row_num);
			m_row_num=m_row_num+10;
			g2d.drawString(rs1.getString(5),42,m_row_num);
			m_row_num=m_row_num+100;
		}
		
		g2d.drawString("PRE REMINDER",350,m_row_num);
		m_row_num=m_row_num+20;
		g2d.drawString("Dear Sir,",42,m_row_num);
		m_row_num=m_row_num+20;
		g2d.drawString("Supplier code :- "+m_client_no,42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("Supplier Name :- "+m_client_name,42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("Debtor code :- "+m_debtor_no,42,m_row_num);
		m_row_num=m_row_num+20;
		
		g2d.drawString("We refer to the factoring agreement we have with your above named supplier under which you have been advised to pay all debts due to the said supplier, which have been ",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString(" assigned to us, direct to Lakderana Investments Limited carrying on factoring business under the name and style of 'Lakderana Factor' at No. 100,   Buthgamuwa Road,  Rajagiriya.",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("For your convenience the amount falling due is given below.",42,m_row_num);
		m_row_num=m_row_num+20;
			
		g2d.drawString("Invoice No",42,m_row_num);
		g2d.drawString("Invoice Date",100,m_row_num);
		g2d.drawString("Due Date",150,m_row_num);
		g2d.drawString("Invoice Amount",250,m_row_num);
		g2d.drawString("Balance Amount",350,m_row_num);
		
		if(m_status.equals("N")){
		rs2 = stmt2.executeQuery ("SELECT NVL(A.BATCH_NO,'-'),"+
		" NVL(A.INVOICE_NO,'-'), "+
		" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+
		" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
		" NVL(A.INVOICE_AMOUNT,0), "+
		" NVL(A.BALANCE_AMOUNT,0) "+
		" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B  "+
		" WHERE B.FACILITY_NO='"+m_facility_no+"'"+
		" AND A.DEBTOR_CODE='"+m_debtor_no+"' "+
		" AND A.BATCH_NO=B.BATCH_NO "+
		" AND B.CLIENT_CODE='"+m_client_no+"' "+
		" AND A.BALANCE_AMOUNT>0 "+
		" AND A.INVOICE_STATUS='CONF' "+
		" AND A.INVOICE_SEQ_NO IN (SELECT INVOICE_SEQ_NO "+
	  " FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
	  " WHERE LETTER_NAME='PRE_INVOICE_REMINDER'"+
		" AND CLIENT_CODE='"+m_client_no+"'"+
		" AND FACILITY_NO='"+m_facility_no+"'"+
		" AND DEBTOR_CODE='"+m_debtor_no+"'"+
		" AND LETTER_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
		" AND PRINT_STATUS='N' "+
		" )"+
		" ORDER BY A.INVOICE_DATE ");
		}
		else{
		rs2 = stmt2.executeQuery ("SELECT NVL(A.BATCH_NO,'-'),"+
		" NVL(A.INVOICE_NO,'-'), "+
		" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+
		" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
		" NVL(A.INVOICE_AMOUNT,0), "+
		" NVL(A.BALANCE_AMOUNT,0) "+
		" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B  "+
		" WHERE B.FACILITY_NO='"+m_facility_no+"'"+
		" AND A.DEBTOR_CODE='"+m_debtor_no+"' "+
		" AND A.BATCH_NO=B.BATCH_NO "+
		" AND B.CLIENT_CODE='"+m_client_no+"' "+
		" AND A.BALANCE_AMOUNT>0 "+
		" AND A.INVOICE_STATUS='CONF' "+
		" AND A.INVOICE_SEQ_NO IN (SELECT INVOICE_SEQ_NO "+
	  " FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
	  " WHERE LETTER_NAME='PRE_INVOICE_REMINDER'"+
		" AND CLIENT_CODE='"+m_client_no+"'"+
		" AND FACILITY_NO='"+m_facility_no+"'"+
		" AND DEBTOR_CODE='"+m_debtor_no+"'"+
		" AND LETTER_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
		" AND PRINT_STATUS='Y' "+
		" )"+
		" ORDER BY A.INVOICE_DATE ");
		}
		
		m_row_num=m_row_num+20;
		while(rs2.next()){
		g2d.drawString(rs2.getString(2),42,m_row_num);
		g2d.drawString(rs2.getString(3),100,m_row_num);
		g2d.drawString(rs2.getString(4),150,m_row_num);
		g2d.drawString(nf.format(rs2.getDouble(5)),250,m_row_num);
		g2d.drawString(nf.format(rs2.getDouble(6)),350,m_row_num);
		m_row_num=m_row_num+10;
		}
		
		m_row_num=m_row_num+20;
		g2d.drawString("If you have already made appropriate remittances in advance please advise us accordingly at your earliest convenience. Also if you have any queries concerning this account",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString(" please do not hesitate to contact the undersigned. ",42,m_row_num);
	
		m_row_num=m_row_num+20;
		g2d.drawString("Early attention to this matter is highly appreciated.  ",42,m_row_num);
		
		m_row_num=m_row_num+20;
		g2d.drawString("Thanking you ",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("Yours faithfully, ",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("Factoring Division of  ",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("Lakderana Investments Limited  ",42,m_row_num);
		m_row_num=m_row_num+40;
		g2d.drawString("................................",42,m_row_num);
		m_row_num=m_row_num+10;
		g2d.drawString("Authorized Signatory ",42,m_row_num);
	}
	catch(Exception e){
	}
	g2d.dispose();
	System.gc();
	return (PAGE_EXISTS);
	}
}
