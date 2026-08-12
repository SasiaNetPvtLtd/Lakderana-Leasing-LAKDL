import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;

class LAKDL_Print_cheques_book
	implements Printable
{
	public String m_payment_code;
	public String mm_acc_payee_status;
	public int mm_page_count;
	public int mm_page_size;
	public int mm_page_size2;
	NumberFormat nf;
	
	public LAKDL_Print_cheques_book(String paramString,String m_acc_payee_status)
	{
		nf = NumberFormat.getInstance(Locale.US);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		m_payment_code = paramString;
		mm_acc_payee_status = m_acc_payee_status;
	}
	
	public int print(Graphics paramGraphics, PageFormat paramPageFormat, int paramInt)
	{
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		String str5 = "";
		String str6 = "";
		String str7 = "";
		String str8 = "";
		String str9 = "";
		String str10 = "";
		String str11 = "";
		String str12 = "";
		String str13 = "";
		
		
		
		
		int i = -13;
		
		
		
		
		
		
		Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
		
		localGraphics2D.translate(paramPageFormat.getImageableX(), paramPageFormat.getImageableY());
		try
		{
			LAKDL_print_methods localLAKDL_print_methods = new LAKDL_print_methods();
			String str14 = localLAKDL_print_methods.schema_name.trim();
			
			
			
			
			
			Connection localConnection = localLAKDL_print_methods.get_print_connection();
			System.out.println("mm_acc_payee_status********** " + mm_acc_payee_status);
			System.out.println("connMILI " + localConnection);
			
			Statement localStatement = localConnection.createStatement();
			//System.out.println("1 " + localStatement);
			//System.out.println("contract" + m_payment_code);
			
			int j = 0;
			
			
			i = i;
			
			
			
			
			
			
			
			
			
			
			System.out.println("SQL QUERY ==> SELECT ' ' PAYMENT_NO, PAYEE_NAME,  SUM(PAY_AMOUNT),  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),  TO_CHAR(EFF_VALDATE,'DD'),  TO_CHAR(EFF_VALDATE,'MM'),  TO_CHAR(EFF_VALDATE,'YY'),  NVL(FINANCE_NO,'N/A'),  NVL(CHEQUE_NO,' ')  FROM  LAKDL.AF_RE_PRO_SETTLMENT_PAYMENT  WHERE NVL(GROUP_PAYMENT_NO,PAYMENT_NO)='" + m_payment_code + "' " + " GROUP BY PAYEE_NAME,EFF_VALDATE, NVL(FINANCE_NO,'N/A'),NVL(CHEQUE_NO,' ') ");
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			ResultSet localResultSet = localStatement.executeQuery(" SELECT ' ' PAYMENT_NO, PAYEE_NAME, "+
				" SUM(PAY_AMOUNT),  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),"+
				" TO_CHAR(EFF_VALDATE,'DD'),  TO_CHAR(EFF_VALDATE,'MM'), "+
				" TO_CHAR(EFF_VALDATE,'YYYY'),  NVL(FINANCE_NO,'N/A'), "+
				" NVL(CHEQUE_NO,' ') "+
				" FROM  "+str14+".AF_RE_PRO_SETTLMENT_PAYMENT "+
				" WHERE NVL(GROUP_PAYMENT_NO,PAYMENT_NO)='" + m_payment_code + "' " + 
				" GROUP BY PAYEE_NAME,EFF_VALDATE, NVL(FINANCE_NO,'N/A'),NVL(CHEQUE_NO,' ') ");
			*/
			
			ResultSet localResultSet = localStatement.executeQuery(" SELECT ' ' PAYMENT_NO, PAYEE_NAME, "+
				" SUM(PAY_AMOUNT),  TO_CHAR(SYSDATE,'DD-MM-YYYY'),"+
				" TO_CHAR(SYSDATE,'DD'),  TO_CHAR(SYSDATE,'MM'), "+
				" TO_CHAR(SYSDATE,'YYYY'),  NVL(FINANCE_NO,'N/A'), "+
				" NVL(CHEQUE_NO,' ') "+
				" FROM  "+str14+".AF_RE_PRO_SETTLMENT_PAYMENT "+
				" WHERE NVL(GROUP_PAYMENT_NO,PAYMENT_NO)='" + m_payment_code + "' " + 
				" GROUP BY PAYEE_NAME, NVL(FINANCE_NO,'N/A'),NVL(CHEQUE_NO,' ') ");
			
			if (localResultSet.next())
			{
				System.out.println("TESTING");
				str1 = localResultSet.getString(2);
				str3 = nf.format(localResultSet.getDouble(3));
				str4 = localLAKDL_print_methods.numbersToChar(localLAKDL_print_methods.met_unformat_number(nf.format(localResultSet.getDouble(3))));
				str5 = localResultSet.getString(5);
				str6 = localResultSet.getString(6);
				str7 = localResultSet.getString(7);
				str2 = localResultSet.getString(1);
				
				
				str10 = localResultSet.getString(4);
				System.out.println("TESTING str1" + str1);
				System.out.println("TESTING str2" + str2);
				System.out.println("TESTING str3" + str3);
				System.out.println("TESTING str4" + str4);
				System.out.println("TESTING str5" + str5);
				
				System.out.println("TESTING str6" + str6);
				System.out.println("TESTING str7" + str7);
				System.out.println("TESTING str10" + str10);
			}
			localGraphics2D.setPaint(Color.black);
			
			localGraphics2D.setStroke(new BasicStroke(5.0F));
			
			FontMetrics localFontMetrics = localGraphics2D.getFontMetrics();
			
			String str15 = "--" + str4.toUpperCase() + " ONLY--**";
			
			Font localFont = new Font("Arial", 1, 7);
			localGraphics2D.setFont(localFont);
			
			System.out.println("TESTING");
			
			String str16 = "";
			String str17 = "";
			String str18 = "";
			
			int k = 0;
			int m = 0;
			int n = 0;
			
			int i1 = 0;
			int i2 = 0;
			int i3 = 0;
			
			
			
			
			int i4 = -24;
			
			int i5 = 0;
			int i6 = 0;
			
			int i7 = 0;
			int i8 = 0;
			while (i2 < str15.length())
			{
				i5 += localFontMetrics.charWidth(str15.charAt(i2));
				i6++;
				if (str15.charAt(i2) == ' ') {
					i7 = i6;
				} else {
					i8 = i6;
				}
				if (i5 > 420)
				{
					i5 = 0;
					if (k == 0)
					{
						str16 = str15.substring(0, i7);
						k = 1;
					}
					if (str15.length() > i7)
					{
						str17 = str15.substring(i7, str15.length());
						m = 1;
					}
				}
				i2++;
			}
			if (k == 0)
			{
				str16 = str15;
				k = 1;
			}
			localGraphics2D.drawString("                      ", i4 + 240, i + 13);
			localGraphics2D.drawString("                      ", i4 + 240, i + 24);
			localGraphics2D.drawString("                      ", i4 + 240, i + 32);
			
			
			localFont = new Font("Arial", 1, 12);
			localGraphics2D.setFont(localFont);
			i4 += 14;
			localGraphics2D.drawString(str5.substring(0, 1), i4 + 356, i + 32);
			localGraphics2D.drawString(str5.substring(1, 2), i4 + 375, i + 32);
			localGraphics2D.drawString(str6.substring(0, 1), i4 + 394, i + 32);
			localGraphics2D.drawString(str6.substring(1, 2), i4 + 412, i + 32);
			localGraphics2D.drawString(str7.substring(2, 3), i4 + 463, i + 32);
			localGraphics2D.drawString(str7.substring(3, 4), i4 + 481, i + 32);
			
			localFont = new Font("Times New Roman", 3, 10);
			localGraphics2D.setFont(localFont);
			localGraphics2D.drawString(str1.toUpperCase() + " **", i4 + 50, i + 68);
			
			localFont = new Font("Times New Roman", 2, 9);
			localGraphics2D.setFont(localFont);
			if (k != 0) {
				localGraphics2D.drawString(str16, i4 + 60, i + 96);
			}
			if (m != 0) {
				localGraphics2D.drawString(str17, i4 + 60, i + 116);
			}
			if (n != 0) {
				localGraphics2D.drawString(str18, i4 + 60, i + 126);
			}
			localFont = new Font("Arial", 0, 12);
			localGraphics2D.setFont(localFont);
			localGraphics2D.drawString("**" + str3 + "**", i4 + 355, i + 120);
			
			localFont = new Font("Arial", 1, 8);
			localGraphics2D.setFont(localFont);
			
			
			
		System.out.println("********************* last----"+mm_acc_payee_status);
			localFont = new Font("Arial", 1, 6);
			localGraphics2D.setFont(localFont);
			if (mm_acc_payee_status.equals("Y"))
			{
				localGraphics2D.drawString("______________________", i4 + 138, i + 175);
				localGraphics2D.drawString("     A/C PAYEE ONLY   ", i4 + 138, i + 183);
				localGraphics2D.drawString("______________________", i4 + 138, i + 191);
			}
			else
			{
				localGraphics2D.drawString("                      ", i4 + 138, i + 175);
				localGraphics2D.drawString("                      ", i4 + 138, i + 183);
				localGraphics2D.drawString("                      ", i4 + 138, i + 191);
			}
		}
		catch (Exception localException) {}
		localGraphics2D.dispose();
		System.gc();
		return 0;
	}
}

