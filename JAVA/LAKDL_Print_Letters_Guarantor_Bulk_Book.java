import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.FontMetrics;
import java.awt.print.*;
import java.text.*;
import javax.print.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class LAKDL_Print_Letters_Guarantor_Bulk_Book implements Printable {
	
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9,stmt10;
	
	public ResultSet rs;
	public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10;
	
	String m_debtor_code="-",m_client_name="-",m_invoice_seq_no="-",m_batch_no="-",m_return_no="-",m_application_no="-",m_finance_no="-",mm_statement_date="-";
	String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-",m_Letter_date="-";
	String m_contact_person="-",m_contact_desig="-",m_app_no="-",m_lease_date="-",m_client_no="-",m_guarantor_code="-";
	String m_d_contact_person="-",m_d_contact_desig="-";
	String m_master_lease_no="-";
	String m_contract_client_code="-";
	String m_date ="";
	double m_chq_amount=0,m_tot_arrears=0,m_odi_arrears=0,m_pen_tot=0;
	java.text.NumberFormat nf;
	
	
	
	
	//public LAKDL_Print_Letters_Guarantor_Bulk_Book(String finance_no, String guarantor_code){ // commented by udara 05-11-2019
	public LAKDL_Print_Letters_Guarantor_Bulk_Book(String finance_no, String guarantor_code, String m_statement_date){ // added by udara 05-11-2019
		nf = java.text.NumberFormat.getInstance(Locale.US);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		m_finance_no=finance_no;
		m_guarantor_code = guarantor_code;
		mm_statement_date = m_statement_date; // added by udara 05-11-2019
		
		//m_date =m_date_;
	}
	
	public int print (Graphics g,PageFormat pageFormat, int page) {
		//orient data----------			
		String m_lakderana_name="";
		String m_lakderana_add1="";
		String m_lakderana_add2="";
		String m_lakderana_city_name="";
		String m_lakderana_tel_no="";
		String m_lakderana_fax_no="";
		String m_LAKDL_vat_no="",m_LAKDL_reg_no="";
		String m_vat_precentage="";
		
		String m_full_name="**";
		String m_add1="*";
		String m_add2="**";
		String m_city_name="**";
		String m_title="**";
		String m_client_type="**"; 
		String m_nic_no="**";
		
		String m_vehicle_no="-";
		String m_app_no="";
		String m_Ins_count="";
		
		double mm_NET_RENTAL_AMOUNT=0.00;
		String mm_RENTAL_DATE="";
		String mm_VEHICLE_NO="";
		String mm_MATURITY_DATE="";
		String mm_NEXT_RENTAL_DATE="";
		String m_contact_full_name="";
		String m_guarantor_full_name="";
		String m_guarantor_full_name1="";
		String m_guarantor_full_name2="";
		String m_guarantor_full_name3="";
		String m_guarantor_full_name4="";
		String mm_GUARANTOR_CODE="";
		String mm_GUAR_ID="";
		
		
		System.out.println("App no ==> "+m_finance_no);
		//System.out.println("Date  ==> "+m_date);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate (pageFormat.getImageableX(),pageFormat.getImageableY());
		
		try
		{
			LAKDL_print_methods m_print_method=new LAKDL_print_methods();
			String m_schema_name = m_print_method.schema_name.trim();		
			Connection conn;
			
			conn=m_print_method.get_print_connection();
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt8=conn.createStatement();
			stmt9=conn.createStatement();
			stmt10=conn.createStatement();
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");								
			boolean more = rs.next();
			
			if(more){
				m_Letter_date=rs.getString(1);
			}
			
			rs = stmt.executeQuery(" SELECT "+
				" COMPANY_NAME, "+
				" ADDRESS1, "+
				" ADDRESS2, "+
				" CITY, "+
				" TEL_NO, "+
				" FAX_NO,  "+
				" VAT_RATE, "+
				" VAT_REG_NO "+
				" ,REG_NO"+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			
			more = rs.next();		
			
			if(more)
			{
				m_lakderana_name=rs.getString(1);
				m_lakderana_add1=rs.getString(2);
				m_lakderana_add2=rs.getString(3);
				m_lakderana_city_name=rs.getString(4);
				m_lakderana_tel_no=rs.getString(5);
				m_lakderana_fax_no=rs.getString(6);
				m_vat_precentage=rs.getString(7);			
				m_LAKDL_vat_no=rs.getString(8);	
				m_LAKDL_reg_no=rs.getString(9);	
			}
			
			
			
			
			//----- Guarantor Details ------//
			
			String Guarantor_Data=" SELECT  "+
				" 'GUARANTOR', "+ //1
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || UPPER(INITIALS)|| ' ' || INITCAP(SURNAME),'C',/*'MESS' || '. '  || */INITCAP(FULL_NAME)),' ') ,   "+ //2
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
				" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //7
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE   CLIENT_CODE =UPPER('"+m_guarantor_code+"') ";
			
			System.out.println("sql Guarantor_Data ==> "+Guarantor_Data);
			rs2 = stmt2.executeQuery(Guarantor_Data);
			
			more = rs2.next();		
			
			if(more){	
				m_full_name=rs2.getString(2);
				m_add1=rs2.getString(3);
				m_add2=rs2.getString(4);
				m_city_name=rs2.getString(5);
				m_nic_no=rs2.getString(6);
			}
			
			//// -----Contract Client Details ------//
			
			String Contract_Client_Data =" SELECT CLIENT_CODE"+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
				" WHERE FINANCE_NO='"+m_finance_no+"' ";
			
		//	System.out.println("sql Contract_Client_Data ==> "+Contract_Client_Data);
			rs7 = stmt7.executeQuery(Contract_Client_Data);
			
			more = rs7.next();	
			
			if(more){	
				m_contract_client_code=rs7.getString(1);
				
			}
			
			
			String m_designation = "";
			String m_tel_no= "";
			
			String Client_Name=" SELECT  "+
				" 'CLIENT', "+ //1
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || UPPER(INITIALS)|| ' ' || INITCAP(SURNAME),'C',/*'MESS' || '. '  || */INITCAP(FULL_NAME)),' ') ,   "+ //2
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
				" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ') ,   "+ //7
				" DESIGNATION,  "+ // 8
				" NVL(MOBILE_NO,TEL_NO) "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+ // 9
				" WHERE   CLIENT_CODE =UPPER('"+m_contract_client_code+"') ";
			
			System.out.println("sql Client_Data ==> "+Client_Name);
			rs8 = stmt8.executeQuery(Client_Name);
			
			more = rs8.next();		
			
			if(more){	
				m_contact_full_name=rs8.getString(2);
				m_designation=rs8.getString(8);
				m_tel_no=rs8.getString(9);
				
			}
			
			
			//// ---- End Contact Client Detail-----///
			
			
			
			String Agree_Data =" SELECT "+
				" A.APPLICATION_NO, "+
				"  NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A"+
				" WHERE A.FINANCE_NO='"+m_finance_no+"' ";
			
			
			//System.out.println("sql Agree_Data ==> "+Agree_Data);
			rs3 = stmt3.executeQuery(Agree_Data);
			
			more = rs3.next();		
			
			if(more){	
				m_app_no=rs3.getString(1);
				m_vehicle_no=rs3.getString(2);
				
			}
			
			
			rs3.close();
			
			//--------------------------------------------
			
			// -- Arrears Details - Start --
			
			double arr_amount = 0;
			String m_ent_date="";
			
			String arrearsDetails =" SELECT "+
				" A.ARR_AMOUNT, "+
				" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') "+
				" FROM "+m_schema_name+".AF_CO_BULK_LETTER_CONTRACTS A"+
				" WHERE A.FINANCE_NO='"+m_finance_no+"' "+
				" AND A.REMINDER_TYPE = '1STREIM' "+ // added by udara 05-11-2019 
				" AND A.DUE_DATE = TO_DATE('"+mm_statement_date+"','DD-MM-YYYY') "; // added by udara 05-11-2019
			
			rs3 = stmt3.executeQuery(arrearsDetails);
			
			more = rs3.next();
			
			if(more){	
				arr_amount=rs3.getDouble(1);
				m_ent_date=rs3.getString(2);
			}
			
			// -- Arrears Details - End --
			
			
			
			
			///---- End Guarantor Detail
			
			String m_company_name="";
			String m_reg_no="";
			String m_company_det="";
			String m_com_contact="";
				
			String Company_Details=" SELECT  "+
				"  UPPER(COMPANY_NAME), "+
				"  'Registration No : ' || REG_NO, "+
				"  INITCAP(NVL(UPPER(COMPANY_NAME),' '))||','||' No.'|| NVL(UPPER(ADDRESS1),' ')||', '||INITCAP(NVL(UPPER(ADDRESS2),' '))||', '||INITCAP(NVL(UPPER(CITY),' ')), 	"+
				" 'Tel : ' ||NVL(TEL_NO,' ')||' | Fax : '||NVL(FAX_NO,' ')||' | Email :'||NVL(EMAIL,' ')||' | Web :'||NVL(WEB,' ') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
			rs10 = stmt10.executeQuery(Company_Details);
			
				more = rs10.next();
				
				if(more){	
					m_company_name=rs10.getString(1);
					m_reg_no=rs10.getString(2);
					m_company_det=rs10.getString(3);	
					m_com_contact=rs10.getString(4);
				}
			
			g2d.setPaint (Color.black);
			g2d.setStroke (new BasicStroke(5));
			FontMetrics fontMetrics = g2d.getFontMetrics();
			Font bodyFont  		= new Font ("Times New Roman",Font.PLAIN,10);
			Font bodyFont_1 	= new Font ("Times New Roman",Font.BOLD,8);
			Font titleFont 		= new Font ("Times New Roman",Font.BOLD,12);
			Font headtitleFont 	= new Font ("Times New Roman",Font.BOLD,14);
			Font bodyFontSin 	= new Font ("FMBindumathi",Font.PLAIN,10);
			Font titleFontSin 	= new Font ("FMBindumathi",Font.BOLD,12);
			Font bodyFontSin_2 	= new Font ("FMBindumathi",Font.PLAIN,8);
			Font bodyFontSin_3 	= new Font ("FMBindumathi",Font.BOLD,8);
			Font bodyFont2  	= new Font ("Times New Roman",Font.PLAIN,8);
			Font bodyFontTam  	= new Font ("Kalaham",Font.BOLD,8);
			Font titleFontTam   = new Font ("Kalaham",Font.BOLD,12);
			Font bodyFont_3 	= new Font ("Times New Roman",Font.BOLD,12);
			Font bodyFont4  	= new Font ("Times New Roman",Font.PLAIN,10);
			
			
			float breakWidth 	= 0;
			float Ralign_X 	    = 0;
			float drawPosY 		= 0;
			float drawPosX		= 0;
			int paragraphStart 	= 0;
			int paragraphEnd 	= 0;
			
			String Underline="_";
			
			// ---------- Add Image [Begin] ----------
            BufferedImage image1 = null;
			
			float wrappingWidth = 450;
			
			AttributedCharacterIterator paragraph = null;
			AttributedString line = null;
			LineBreakMeasurer lbm = null;
			TextLayout layout     = null;
			
			//drawPosY   = 15;  // STARATING POSITION ON Y axis //35
			drawPosY   = 80;
			breakWidth = 500;  // paragraph line break width
			Ralign_X   = 480;  // Right Alignment Default X pos
			
			
			//----------printing starts here----------------------//
			
			
			FontRenderContext frc = g2d.getFontRenderContext();
			
			
			    //System.out.println("Printing to " + ps);
			   // DocPrintJob job = ps.createPrintJob();
			  
				 String LAKDL_IMAGE="LAKDL";
					try{
					// image1 = ImageIO.read(new File("D:\\SasiaNet_Products\\NetAsset\\LAKDL\\Home\\images\\" + LAKDL_IMAGE + ".gif"));
					      image1 = ImageIO.read(new File("E:\\SasiaNet\\NetAsset\\LAKDL\\Home\\images\\" + LAKDL_IMAGE + ".gif")); // Live
						System.out.println("File Found !!!!!!!!! Image printing");
					}catch(Exception eee){
						System.out.println("File Not Found !!!!!!!!! Image printing "+eee.toString());
					}
                // g2d.drawImage(image1, drawPosX + 300, drawPosY + 152,image1.getWidth(),image1.getHeight(),null);
				//  g2d.drawImage(image1, 300, 152, null);	
				  g2d.drawImage(image1,350,20,null);	
				 //g2d.drawImage(image1,drawPosX,drawPosY+30);	
			  
			  
		    
			
			//-------END COMPANY DETAIL------//	
			
			line = new AttributedString("REGISTERED POST");
			line.addAttribute(TextAttribute.FONT, bodyFont);
			line.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc            = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= layout.isLeftToRight() ? 200 : breakWidth - layout.getAdvance();
				//drawPosX 			= layout.isRightToLeft() ? 440 : breakWidth - layout.getAdvance();
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 15;
			//------ START DATE--------------//
			line = new AttributedString(m_Letter_date);
			System.out.println("m_Letter_date ==> "+m_Letter_date);
			line.addAttribute(TextAttribute.FONT, titleFont);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc            = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				//drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance();
				drawPosX 			= layout.isLeftToRight() ? 470 : breakWidth - layout.getAdvance();
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += layout.getDescent() + layout.getLeading();
			}
			//drawPosY += 10;
			//------END START DATE--------------//	
			
			
			line = new AttributedString(m_full_name);
			System.out.println("m_full_name ==> "+m_full_name);
			line.addAttribute(TextAttribute.FONT, titleFont);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc            = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				//drawPosX 			= layout.isLeftToRight() ? 130 : breakWidth - layout.getAdvance();
				 drawPosX 			= layout.isLeftToRight() ? 80 : 150 - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += layout.getDescent() + layout.getLeading();
			}
			
			if(!m_add1.equals(" ")){
				line = new AttributedString(m_add1);
				System.out.println("m_add1 ==> "+m_add1);
				line.addAttribute(TextAttribute.FONT, titleFont);
				paragraph      = line.getIterator();
				paragraphStart = paragraph.getBeginIndex();
				paragraphEnd   = paragraph.getEndIndex();
				frc = g2d.getFontRenderContext();
				lbm            = new LineBreakMeasurer(paragraph, frc);
				
				lbm.setPosition(paragraphStart);
				while (lbm.getPosition() < paragraphEnd) {
					layout 	            = lbm.nextLayout(breakWidth);
					//drawPosX 			= layout.isLeftToRight() ? 130 : breakWidth - layout.getAdvance();
					drawPosX 			= layout.isLeftToRight() ? 80 : 150 - layout.getAdvance(); // breakWidth cahnge as 150
					drawPosY            += layout.getAscent();
					layout.draw(g2d, drawPosX, drawPosY);
					drawPosY            += layout.getDescent() + layout.getLeading();
				}
			}
			
			if(!m_add2.equals(" ")){
				line = new AttributedString(m_add2);
				System.out.println("m_add2 ==> "+m_add2);
				line.addAttribute(TextAttribute.FONT, titleFont);
				paragraph      = line.getIterator();
				paragraphStart = paragraph.getBeginIndex();
				paragraphEnd   = paragraph.getEndIndex();
				frc = g2d.getFontRenderContext();
				lbm = new LineBreakMeasurer(paragraph, frc);
				
				lbm.setPosition(paragraphStart);
				while (lbm.getPosition() < paragraphEnd) {
					layout 	            = lbm.nextLayout(breakWidth);
				//	drawPosX 			= layout.isLeftToRight() ? 130 : breakWidth - layout.getAdvance();
				    drawPosX 			= layout.isLeftToRight() ? 80 : 150 - layout.getAdvance(); // breakWidth cahnge as 150
					drawPosY            += layout.getAscent();
					layout.draw(g2d, drawPosX, drawPosY);
					drawPosY            += layout.getDescent() + layout.getLeading();
				}
			}
			
			if(!m_city_name.equals(" ")){
				line = new AttributedString(m_city_name);
				System.out.println("m_city_name ==> "+m_city_name);
				line.addAttribute(TextAttribute.FONT, titleFont);
				paragraph      = line.getIterator();
				paragraphStart = paragraph.getBeginIndex();
				paragraphEnd   = paragraph.getEndIndex();
				frc = g2d.getFontRenderContext();
				lbm            = new LineBreakMeasurer(paragraph, frc);
				
				lbm.setPosition(paragraphStart);
				while (lbm.getPosition() < paragraphEnd) {
					layout 	            = lbm.nextLayout(breakWidth);
					//drawPosX 			= layout.isLeftToRight() ? 130 : breakWidth - layout.getAdvance();
					drawPosX 			= layout.isLeftToRight() ? 80 : 150 - layout.getAdvance(); // breakWidth cahnge as 150
					drawPosY            += layout.getAscent();
					layout.draw(g2d, drawPosX, drawPosY);
					drawPosY             += (layout.getDescent() + layout.getLeading())*15;
				}
			}
			
			
			// ===========================================================================================================================
			
		/*	line = new AttributedString("l=,S .eKqïlref.a ku"+m_print_method.Add_Space(15)+".súiqï wxlh"+m_print_method.Add_Space(10)+"jdyk wxlh"+m_print_method.Add_Space(10)+"m%ldYkfha Èkh"+m_print_method.Add_Space(10)+"ysÕ uq¿ uqo,");
			//line = new AttributedString("INSURANCE  "+m_print_method.Add_Space(25)+": ");
			line.addAttribute(TextAttribute.FONT, bodyFontSin_2);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 350);
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
				drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
			
			line = new AttributedString(m_contact_full_name + m_print_method.Add_Space(15)+ m_finance_no + m_print_method.Add_Space(10) + m_vehicle_no + m_print_method.Add_Space(10) + m_Letter_date +m_print_method.Add_Space(10)+nf.format(arr_amount));
			//line = new AttributedString("INSURANCE  "+m_print_method.Add_Space(25)+": ");
			line.addAttribute(TextAttribute.FONT, bodyFont2);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 350);
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
				drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		*/	
		
		   	//line = new AttributedString("l=,S .eKqïlref.a ku"+m_print_method.Add_Space(15)+".súiqï wxlh"+m_print_method.Add_Space(10)+"jdyk wxlh"+m_print_method.Add_Space(10)+"m%ldYkfha Èkh"+m_print_method.Add_Space(10)+"ysÕ uq¿ uqo,");
			//line = new AttributedString("INSURANCE  "+m_print_method.Add_Space(25)+": ");
			
			String lineE_1="HIRER’S NAME";
			String lineE_2="AGREEMENT NO";
			String lineE_3="VEHICLE NO";
			String lineE_4="STATEMENT DATE";
			String lineE_5="TOTAL OUTSTANDING";
			
			line = new AttributedString(lineE_1);
			line.addAttribute(TextAttribute.FONT, bodyFont_1);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			//System.out.println("line_1=");
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
			//	drawPosX 			= (Ralign_X - 350);
			    drawPosX 			= (Ralign_X - 390);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_1="+drawPosX);
		//	System.out.println("line_1="+drawPosY);
			line = new AttributedString(lineE_2);
			line.addAttribute(TextAttribute.FONT, bodyFont_1);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 240);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
		//	System.out.println("line_2="+drawPosX);
		//	System.out.println("line_2="+drawPosY);
			line = new AttributedString(lineE_3);
			line.addAttribute(TextAttribute.FONT, bodyFont_1);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 140);
				//drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_3="+drawPosX);
		//	System.out.println("line_3="+drawPosY);
			line = new AttributedString(lineE_4);
			line.addAttribute(TextAttribute.FONT, bodyFont_1);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 70);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_4="+drawPosX);
		//	System.out.println("line_4="+drawPosY);
			line = new AttributedString(lineE_5);
			line.addAttribute(TextAttribute.FONT, bodyFont_1);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X+10);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
	        drawPosY=drawPosY+15;
			
			String line_1="l=,S .eKqïlref.a ku";
			String line_2=".súiqï wxlh";
			String line_3="jdyk wxlh";
			String line_4="m%ldYkfha Èkh";
			String line_5="ysÕ uq¿ uqo,";
			
			line = new AttributedString(line_1);
			line.addAttribute(TextAttribute.FONT, bodyFontSin_3);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			//System.out.println("line_1=");
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
			//	drawPosX 			= (Ralign_X - 350);
			    drawPosX 			= (Ralign_X - 390);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_1="+drawPosX);
		//	System.out.println("line_1="+drawPosY);
			line = new AttributedString(line_2);
			line.addAttribute(TextAttribute.FONT, bodyFontSin_3);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 240);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
		//	System.out.println("line_2="+drawPosX);
		//	System.out.println("line_2="+drawPosY);
			line = new AttributedString(line_3);
			line.addAttribute(TextAttribute.FONT, bodyFontSin_3);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 140);
				//drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_3="+drawPosX);
		//	System.out.println("line_3="+drawPosY);
			line = new AttributedString(line_4);
			line.addAttribute(TextAttribute.FONT, bodyFontSin_3);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 70);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_4="+drawPosX);
		//	System.out.println("line_4="+drawPosY);
			line = new AttributedString(line_5);
			line.addAttribute(TextAttribute.FONT, bodyFontSin_3);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X+10);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			//drawPosY=+10;
			
		//	System.out.println("line_5="+drawPosX);
		//	System.out.println("line_5="+drawPosY);
	/*		line = new AttributedString(m_contact_full_name + m_print_method.Add_Space(15)+ m_application_no + m_print_method.Add_Space(10) + m_vehicle_no + m_print_method.Add_Space(10) + m_Letter_date +m_print_method.Add_Space(10)+nf.format(arr_amount));
			//line = new AttributedString("INSURANCE  "+m_print_method.Add_Space(25)+": ");
			line.addAttribute(TextAttribute.FONT, bodyFont2);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 350);
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
				drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		*/
	        drawPosY=drawPosY+15;
			
			
				
			String lineT_1="thlif nfhs;tdthsupd; ngau";
			String lineT_2="cld;gbf;if ,y";
			String lineT_3="thfdj;jpd; ,y";
			String lineT_4="$w;W jpfjp";
			//String lineT_5="epYitapYs;s nkhj;j njhif"; // commented by udara 11-06-2019
			String lineT_5="nkhj;j epYit"; // added by udara 11-06-2019
			
			line = new AttributedString(lineT_1);
			line.addAttribute(TextAttribute.FONT, bodyFontTam);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			//System.out.println("line_1=");
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
			//	drawPosX 			= (Ralign_X - 350);
			    drawPosX 			= (Ralign_X - 390);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_1="+drawPosX);
		//	System.out.println("line_1="+drawPosY);
			line = new AttributedString(lineT_2);
			line.addAttribute(TextAttribute.FONT, bodyFontTam);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 240);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
		//	System.out.println("line_2="+drawPosX);
		//	System.out.println("line_2="+drawPosY);
			line = new AttributedString(lineT_3);
			line.addAttribute(TextAttribute.FONT, bodyFontTam);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 140);
				//drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_3="+drawPosX);
		//	System.out.println("line_3="+drawPosY);
			line = new AttributedString(lineT_4);
			line.addAttribute(TextAttribute.FONT, bodyFontTam);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 70);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
		//	System.out.println("line_4="+drawPosX);
		//	System.out.println("line_4="+drawPosY);
			line = new AttributedString(lineT_5);
			line.addAttribute(TextAttribute.FONT, bodyFontTam);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X+10);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
	        drawPosY=drawPosY+5;
			
			//System.out.println("drawPosYGuarantor=");
			
			//System.out.println("drawPosY1111New111111="+drawPosY);
			//System.out.println("drawPosX1111New111111="+drawPosX);
			
			int valY = (int)Math.round(drawPosY);
			int valX = (int)Math.round(drawPosX);
			
			//System.out.println("drawPosY1111Integer111111="+valY);
			//System.out.println("drawPosX1111Integer111111="+valX);
			
			 g2d.setStroke(new BasicStroke(1.0f));
			 g2d.drawLine(90, valY,  700,  valY);//[HR ]	
			
			drawPosY=drawPosY+15;	
	       	line = new AttributedString(m_contact_full_name);
			line.addAttribute(TextAttribute.FONT, bodyFont4);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			System.out.println("line_1=");
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 390);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
			
			line = new AttributedString(m_finance_no);
			line.addAttribute(TextAttribute.FONT, bodyFont4);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 240);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
			//System.out.println("line_2="+drawPosX);
			//System.out.println("line_2="+drawPosY);
			line = new AttributedString(m_vehicle_no);
			line.addAttribute(TextAttribute.FONT, bodyFont4);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 140);
				//drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			//System.out.println("line_3="+drawPosX);
			//System.out.println("line_3="+drawPosY);
			line = new AttributedString(m_ent_date); //m_Letter_date
			line.addAttribute(TextAttribute.FONT, bodyFont4);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 70);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			//System.out.println("line_4="+drawPosX);
			//System.out.println("line_4="+drawPosY);
			String Line_4_det=""+nf.format(arr_amount)+""; 
			line = new AttributedString(Line_4_det);
			line.addAttribute(TextAttribute.FONT, bodyFont4);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X+10);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
				drawPosY            += (layout.getDescent() + layout.getLeading())*15;
			}
			
			
	
			// ===========================================================================================================================
			
			line = new AttributedString("m<uq isysle|ùuhs'");
			//line = new AttributedString(data);
			line.addAttribute(TextAttribute.FONT, titleFontSin);
			line.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);	
				//drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); 
				
				//drawPosX 			= (breakWidth/2)-30;
				drawPosX 			= (breakWidth/2);  
				
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			
			line = new AttributedString("m%sh uy;auhdKks$uy;añhks\"");
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= layout.isLeftToRight() ? 80 : 150 - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += (layout.getDescent() + layout.getLeading())*8;
				
			}
			
			drawPosY += 10;

			
			String data ="";
			
			//data= "Tn úiska tl`. jq mßÈ by; olajk ,o l=,S iskaklalr .súiqu hgf;a wh úh hq;=j mj;sk udisl jdßlh$jdßlhka Tn úiska ksis mßÈ f.jd fkdue;s nj wms fuu.ska okajd isákafkuq' tu fya;=fjka Tnf.ka wm iud.u fj; ys`. Kh jdßl iy ta u; jq fmd<sh jYfhka by; olajd we;s uqo, f.úh hq;=j iy wh úh hq;=j we;'"; 
			  data= "Tn úiska tl`. jq mßÈ by; olajk ,o l=,S iskaklalr .súiqu hgf;a wh úh hq;=j mj;sk udisl jdßlh$jdßlhka Tn úiska ksis "+
					"mßÈ f.jd fkdue;s nj wms fuu.ska okajd isákafkuq' tu fya;=fjka Tnf.ka wm iud.u fj; ys`. Kh jdßl iy ta u; jq fmd<sh "+
					"jYfhka by; olajd we;s uqo, f.úh hq;=j iy wh úh hq;=j we;'";
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data);
			line.addAttribute(TextAttribute.FONT, bodyFontSin); // titleFont
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			
			drawPosY += 10;
			
			
			
			
			String data1 ="";
			
			data1= "ta wkqj\" fuu ,smsh ,eì Èk y; ^07& la bl=;a ùug fmr by; lS f.ùï isÿlrk f,i wms fuu.ska Tn fj; ±kqï fouq'"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data1);
			line.addAttribute(TextAttribute.FONT, bodyFontSin); // titleFont
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			
			drawPosY += 10;
			
			String data2 ="";
			
			data2= ";jo\" tfia isÿlsßug Tn wfmdfydi;a jkafka kï by; i|yka lrk ,o .súiqu hgf;a l%shd lsÍug wm yg isÿjk nj lK.dgqfjka okajd isáuq' tfia fyhska tjka ;;a;ajhla j,lajd,Sug wjYH kï by; b,a,d we;s mßos lghq;= lrk fuka o fuhska okajd isáuq'"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data2);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			String data3 ="";
			
			//data3= ";jo\" Tn fj; jeäÿr meyeÈ<s lsÍï wjYH kï fuu ,smsh ,eî Èk 3 la we;=<; fuys my; i|yka wm iud.fï ks,OdÍ iïnkaO lr .kak'"; 
			 data3= ";jo\" Tn fj; jeäÿr meyeÈ<s lsÍï wjYH kï fuu ,smsh ,eî Èk 3 la we;=<; fuys my; i|yka wm               "+
					"iud.fï ks,OdÍ iïnkaO lr .kak'";
				    
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data3);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			
			String name_1 ="";
			
			//name_1= "Name :- "; // name_1= "Name :- " + m_contact_full_name; 
			name_1= "ku (-"; // name_1= "Name :- " + m_contact_full_name; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(name_1);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			String designation_1 ="";
			
			//designation_1= "Designation :- "; // designation_1= "Designation :- "+m_designation; 
			designation_1= ";k;=r (-"; // designation_1= "Designation :- "+m_designation; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(designation_1);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			String tele_1 ="";
			
			//tele_1= "Telephone No :- ";  // tele_1= "Telephone No :- "+m_tel_no; 
			tele_1= "ÿrl:k wxlh (-";  // tele_1= "Telephone No :- "+m_tel_no; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(tele_1);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			
			
			
			String data5 ="";
			
			data5= "by; olajd we;s ysÕ uqo, iïmQ¾K jYfhka f.jd we;akï fuu ,smsh fkdi,ld yßk f,i okajd isáuq'"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data5);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			String data6 ="";
			
			data6= "fuhg úYajdiS\""; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data6);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			String data8 ="";
			
			data8= "l<ukdlre - whlsÍï"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data8);
			line.addAttribute(TextAttribute.FONT, titleFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			//drawPosY += 10;
			
			
			
			String data9 ="";
			
			//data9= "fuh mß.Kl uqøs; ,smshla fyhska w;aik wjYH fkdfõ'"; // commented by udara 19-06-2019
			data9= " "; // added by udara 19-06-2019
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data9);
			line.addAttribute(TextAttribute.FONT, bodyFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			
			drawPosY += 10;
			
			
			String data7 ="";
			data7= "msgm;a - wemlrejka"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data7);
			line.addAttribute(TextAttribute.FONT, titleFontSin);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm           = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			
			/*
			
			String Guarantor_Client_Name=" SELECT  "+
				"  INITCAP(NVL(LAKDL.AF_CO_GET_CLIENT_TITLE(GUARANTOR_CODE),' '))||' '|| INITCAP(UPPER(LAKDL.AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE)))	"+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR  "+
				" WHERE APPLICATION_NO='"+m_app_no+"'  "+
				" ORDER BY GUAR_ID ";
			
			rs9 = stmt9.executeQuery(Guarantor_Client_Name);
			
			boolean more_gurant =rs9.next();
			
			String guar_text ="";
			int counts = 0;
			
			while (more_gurant){
				
				counts++;

				guar_text= counts+". "+rs9.getString(1);  
				
				line = new AttributedString(guar_text);
				line.addAttribute(TextAttribute.FONT, bodyFont);
				paragraph      = line.getIterator();
				paragraphStart = paragraph.getBeginIndex();
				paragraphEnd   = paragraph.getEndIndex();
				lbm           = new LineBreakMeasurer(paragraph, frc);
				
				lbm.setPosition(paragraphStart);
				while (lbm.getPosition() < paragraphEnd) {
					layout 	= lbm.nextLayout(breakWidth);
					drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
					drawPosY += layout.getAscent();
					layout.draw(g2d, drawPosX, drawPosY);
					drawPosY += layout.getDescent() + layout.getLeading();
				}
				
			}
			
		*/
			
			String guar_text =" ";
			int counts = 0;
			
			String Guarantor_Client_Name=" SELECT  "+
				"  INITCAP(NVL(LAKDL.AF_CO_GET_CLIENT_TITLE(GUARANTOR_CODE),' '))||' '|| INITCAP(UPPER(LAKDL.AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE)))	"+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR  "+
				" WHERE APPLICATION_NO='"+m_app_no+"'  "+
				" ORDER BY GUAR_ID ";
			
			rs9 = stmt9.executeQuery(Guarantor_Client_Name);
			
			while(rs9.next()){
				
				counts++;
				guar_text = counts+"."+rs9.getString(1);
			
			    line = new AttributedString(guar_text);
				line.addAttribute(TextAttribute.FONT, bodyFont);
				paragraph      = line.getIterator();
				paragraphStart = paragraph.getBeginIndex();
				paragraphEnd   = paragraph.getEndIndex();
				lbm           = new LineBreakMeasurer(paragraph, frc);
				
				lbm.setPosition(paragraphStart);
				while (lbm.getPosition() < paragraphEnd) {
					layout 	= lbm.nextLayout(breakWidth);
					drawPosX = layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); // breakWidth cahnge as 150
					drawPosY += layout.getAscent();
					layout.draw(g2d, drawPosX, drawPosY);
					drawPosY += layout.getDescent() + layout.getLeading();
				}
			
			}
			
			//drawPosY += 90;
			
			drawPosX += 10;
			drawPosY += 10;
			
		   //draw a line (starting x,y; ending x,y)
		   // System.out.println("drawPosX_drawLine111111111111111="+drawPosX);
			//System.out.println("drawPosY_drawLine111111111111111="+drawPosY);
			// g2d.setStroke(new BasicStroke(1.0f));
			// g2d.drawLine(80, 700,  600,  700);//[HR ]	
			
			
			
		    drawPosY += 60;
			
			drawPosY 		 = 0;
			drawPosY 		 = 736;

			line = new AttributedString(m_company_name);
			line.addAttribute(TextAttribute.FONT, bodyFont_3);
			//line.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 240);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
			drawPosY += 10;
			
			line = new AttributedString(m_reg_no);
			line.addAttribute(TextAttribute.FONT, bodyFont2);
			//line.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 190);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
			drawPosY += 10;

			line = new AttributedString(m_company_det);
			line.addAttribute(TextAttribute.FONT, bodyFont2);
		//	line.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 250);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
			drawPosY += 10;
			
			line = new AttributedString(m_com_contact);
			line.addAttribute(TextAttribute.FONT, bodyFont2);
			//line.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			frc = g2d.getFontRenderContext();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			while (lbm.getPosition() < paragraphEnd) {
				//layout 	= lbm.nextLayout(180);
				layout 	= lbm.nextLayout(breakWidth);
				drawPosX 			= (Ralign_X - 280);
			//	drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
			//	drawPosY            += layout.getDescent() + layout.getLeading();
				//drawPosY            += layout.getDescent() + layout.getLeading();
			//	drawPosY            += (layout.getDescent() + layout.getLeading())*5;
			}
			
		}
		catch(Exception ex){
			System.out.println("Error (RL) First Letter "+ex.toString());
		}
		
		g2d.dispose();
		System.gc();
		return (PAGE_EXISTS);
		
	}
}
