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

class LAKDL_Print_letter_book implements Printable {
	
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9,stmt10;
	
	public ResultSet rs;
	public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10;
	
	String m_debtor_code="-",m_client_name="-",m_invoice_seq_no="-",m_batch_no="-",m_return_no="-",m_application_no="-";
	String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-",m_Letter_date="-";
	String m_contact_person="-",m_contact_desig="-",m_app_no="-",m_lease_date="-",m_client_no="-";
	String m_d_contact_person="-",m_d_contact_desig="-";
	String m_master_lease_no="-";
	String m_contract_client_code="-";
	String m_date ="";
	double m_chq_amount=0,m_tot_arrears=0,m_odi_arrears=0,m_pen_tot=0;
	java.text.NumberFormat nf;
	
	
	
	
	public LAKDL_Print_letter_book(String m_rec, String m_client_code){
		nf = java.text.NumberFormat.getInstance(Locale.US);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		m_application_no=m_rec;
		m_client_no = m_client_code;
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
		
		
		System.out.println("App no ==> "+m_application_no);
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
			
			
			/*
			String m_sql ="";
			
			m_sql = " SELECT A.CLIENT_CODE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A"+
				" WHERE A.FINANCE_NO='"+m_application_no+"' ";
			
			//System.out.println("sql 1 ==> "+m_sql);
			rs1 = stmt1.executeQuery(m_sql);
			
			if(rs1.next()){
				m_client_no  	= rs1.getString(1);	
				
			}
			*/
			//System.out.println("m_client_no ==> "+m_client_no);
			
			//----- Client Details ------//
			
			String Client_Data=" SELECT  "+
				" 'CLIENT', "+ //1
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || UPPER(INITIALS)|| ' ' || INITCAP(SURNAME),'C',/*'MESS' || '. '  || */INITCAP(FULL_NAME)),' ') ,   "+ //2
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
				" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //7
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE   CLIENT_CODE =UPPER('"+m_client_no+"') ";
			
			//System.out.println("sql Client_Data ==> "+Client_Data);
			rs2 = stmt2.executeQuery(Client_Data);
			
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
				" WHERE FINANCE_NO='"+m_application_no+"' ";
			
		//	System.out.println("sql Contract_Client_Data ==> "+Contract_Client_Data);
			rs7 = stmt7.executeQuery(Contract_Client_Data);
			
			more = rs7.next();	
			
			if(more){	
				m_contract_client_code=rs7.getString(1);
				
			}
			
			
			String Client_Name=" SELECT  "+
				" 'CLIENT', "+ //1
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || UPPER(INITIALS)|| ' ' || INITCAP(SURNAME),'C',/*'MESS' || '. '  || */INITCAP(FULL_NAME)),' ') ,   "+ //2
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
				" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //7
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE   CLIENT_CODE =UPPER('"+m_contract_client_code+"') ";
			
			System.out.println("sql Client_Data ==> "+Client_Name);
			rs8 = stmt8.executeQuery(Client_Name);
			
			more = rs8.next();		
			
			if(more){	
				m_contact_full_name=rs8.getString(2);
				
			}
			
			
			//// ---- End Contact Client Detail-----///
			
			
			
			String Agree_Data =" SELECT "+
				" A.APPLICATION_NO, "+
				"  NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A"+
				" WHERE A.FINANCE_NO='"+m_application_no+"' ";
			
			
			//System.out.println("sql Agree_Data ==> "+Agree_Data);
			rs3 = stmt3.executeQuery(Agree_Data);
			
			more = rs3.next();		
			
			if(more){	
				m_app_no=rs3.getString(1);
				m_vehicle_no=rs3.getString(2);
				
			}
			
			
			rs3.close();
			
			
			
			
			String Ins_Count =" SELECT "+
				" COUNT(*) "+	
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
				" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
				" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
				" AND A.PRICING_NO       = B.PRICING_NO "+
				" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
				" AND A.INSTALLMENT_NO <> 0 "+//Added By Kanishka On 22-11-2017
				" AND B.ACTIVE_STATUS    IN ('T','Y')";
			
			
			
			//System.out.println("sql Ins_Count ==> "+Ins_Count);
			rs4 = stmt4.executeQuery(Ins_Count);
			
			more = rs4.next();		
			
			if(more){	
				m_Ins_count=rs4.getString(1);
				
			}
			
			
			rs4.close();
			
			
			String Rent_Detail =" SELECT SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, "+//1
				" TO_CHAR(RENTAL_DATE,'DDth')||' OF EACH MONTH' RENTAL_DATE, "+
				" NVL(REG_NO,VEHICLE_NO)  VEHICLE_NO, "+
				" "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO) MATURITY_DATE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
				" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
				" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
				" AND A.PRICING_NO       = B.PRICING_NO "+
				" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
				" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
				" AND ROWNUM= 1 "+
				" AND A.INSTALLMENT_NO <> 0  "+ // added by Kanishka 22-11-2017
				" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO ";
			//" ")
			
			
			
			//System.out.println("sql Rent_Detail ==> "+Rent_Detail);
			rs5 = stmt5.executeQuery(Rent_Detail);
			
			more = rs5.next();		
			
			if(more){	
				mm_NET_RENTAL_AMOUNT      = rs5.getDouble("NET_RENTAL_AMOUNT");
				mm_RENTAL_DATE            = rs5.getString("RENTAL_DATE");
				mm_VEHICLE_NO             = rs5.getString("VEHICLE_NO");
				mm_MATURITY_DATE          = rs5.getString("MATURITY_DATE");
				
				
			}
			
			
			rs5.close();
			
			
			String Next_Rent_Date =" SELECT  TO_CHAR(MIN(A.rental_date),'DDth MONTH yyyy') NEXT_RENTAL_DATE"+
				" FROM   AF_CO_PRO_APP_INSTALLMENT A "+
				" WHERE  A.application_no ='"+m_app_no+"'  "+
				" AND A.rental_date> TO_DATE('"+m_Letter_date+"','DD-MM-YYYY') ";
			
			
			
			
			System.out.println("sql NEXT_RENTAL_DATE ==> "+Next_Rent_Date);
			rs6 = stmt6.executeQuery(Next_Rent_Date);
			
			more = rs6.next();		
			
			if(more){	
				
				mm_NEXT_RENTAL_DATE            = rs6.getString("NEXT_RENTAL_DATE");
				
			}
			
			
			rs6.close();
			
			
			///--- Guarantor Detail ------
			/*
			String Guarantor_Date =" SELECT GUARANTOR_CODE, GUAR_ID "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B  "+
				" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
				" AND B.FINANCE_NO='"+m_application_no+"'  "+
				" AND A.ACTIVE_STATUS='Y' "+
				" ORDER BY GUAR_ID ";
			
			
			
			
			System.out.println("sql Guarantor_Date ==> "+Guarantor_Date);
			rs9 = stmt9.executeQuery(Guarantor_Date);
			
			more = rs9.next();		
			
			while(more){	
				//if(more){
				
				mm_GUARANTOR_CODE            = rs9.getString(1);
				mm_GUAR_ID                   = rs9.getString(2);
				
			}
			rs9.next();
			
			rs9.close();
			
			String Guarantor_Client_Name=" SELECT  "+
				" 'CLIENT', "+ //1
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || UPPER(INITIALS)|| ' ' || INITCAP(SURNAME),'C','MESS' || '. '  || INITCAP(FULL_NAME)),' ') ,   "+ //2
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
				" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
				" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //7
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE   CLIENT_CODE =UPPER('"+mm_GUARANTOR_CODE+"') ";
			
			System.out.println("sql Guarantor_Client_Name ==> "+Guarantor_Client_Name);
			rs10 = stmt10.executeQuery(Guarantor_Client_Name);
			
			more = rs10.next();		
			
			if(more){	
				m_guarantor_full_name=rs10.getString(2);
				
			}
			*/
			
			
			
			//--------------------------------------------
			//rs9=stmt9.executeQuery (" SELECT "+
			    String Guarantor_Client_Name=" SELECT  "+
				"  INITCAP(NVL(LAKDL.AF_CO_GET_CLIENT_TITLE(GUARANTOR_CODE),' '))||' '|| INITCAP(UPPER(LAKDL.AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE))),	"+
				"  INITCAP(UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE))),  "+
				"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(GUARANTOR_CODE),' ')),"+
				"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(GUARANTOR_CODE),' ')),"+
				"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(GUARANTOR_CODE),' ')), "+
				"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(GUARANTOR_CODE),' ')) "+ 
				" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR  "+
				" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_app_no+"')  "+
				" ORDER BY GUAR_ID ";	
				//"  ");
		   // System.out.println("sql Guarantor_Client_Name ==> "+Guarantor_Client_Name);
			rs9 = stmt9.executeQuery(Guarantor_Client_Name);
			
			boolean more_gurant =rs9.next();
			
			int count_gurant=0;
			while (more_gurant){
				if(count_gurant==0){
					m_guarantor_full_name=rs9.getString(1);
					/*m_guranteradd0=rs.getString(2);
					m_guranteradd0_2=rs.getString(3);
					m_guranter_city0=rs.getString(4);
					m_gurantertitle0=rs.getString(5);*/
				}
				else if(count_gurant==1){   
					m_guarantor_full_name1=rs9.getString(1);
					/*m_guranteradd1=rs.getString(2);
					m_guranteradd1_2=rs.getString(3);
					m_guranter_city1=rs.getString(4);
					m_gurantertitle1=rs.getString(5);*/
				}
				else if(count_gurant==2){   
					m_guarantor_full_name2=rs9.getString(1);
					/*m_guranteradd2=rs.getString(2);
					m_guranteradd2_2=rs.getString(3);
					m_guranter_city2=rs.getString(4);
					m_gurantertitle2=rs.getString(5);*/
				}
				else if(count_gurant==3){   
					m_guarantor_full_name3=rs9.getString(1);
					/*m_guranteradd3=rs.getString(2);
					m_guranteradd3_2=rs.getString(3);
					m_guranter_city3=rs.getString(4);
					m_gurantertitle3=rs.getString(5);*/
				}
				else if(count_gurant==4){   
					m_guarantor_full_name4=rs9.getString(1);
					/*m_guranteradd4=rs.getString(2);
					m_guranteradd4_2=rs.getString(3);
					m_guranter_city4=rs.getString(4);
					m_gurantertitle4=rs.getString(5);*/
				}
				more_gurant=rs9.next();
				count_gurant=count_gurant+1;
			}
			
			
			rs9.close();
			stmt9.close();
			
			
			///---- End Guarantor Detail
			
			g2d.setPaint (Color.black);
			g2d.setStroke (new BasicStroke(5));
			FontMetrics fontMetrics = g2d.getFontMetrics();
			Font bodyFont  		= new Font ("Times New Roman",Font.PLAIN,10);
			Font titleFont 		= new Font ("Times New Roman",Font.BOLD,12);
			Font headtitleFont 	= new Font ("Times New Roman",Font.BOLD,14);
			Font bodyFontSin 	= new Font ("FMBindumathi",Font.PLAIN,10);
			Font titleFontSin 	= new Font ("FMBindumathi",Font.BOLD,12);
			
			float breakWidth 	= 0;
			float Ralign_X 	    = 0;
			float drawPosY 		= 0;
			float drawPosX		= 0;
			int paragraphStart 	= 0;
			int paragraphEnd 	= 0;
			
			String Underline="_";
			
			float wrappingWidth = 450;
			
			AttributedCharacterIterator paragraph = null;
			AttributedString line = null;
			LineBreakMeasurer lbm = null;
			TextLayout layout     = null;
			
			drawPosY   = 15;  // STARATING POSITION ON Y axis //35
			breakWidth = 500;  // paragraph line break width
			Ralign_X   = 450;  // Right Alignment Default X pos
			
			
			//----------printing starts here----------------------//
			FontRenderContext frc = g2d.getFontRenderContext();
			
			line = new AttributedString(m_lakderana_name);
			System.out.println("m_lakderana_name ==> "+m_lakderana_name);
			line.addAttribute(TextAttribute.FONT, headtitleFont);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				//drawPosX 			= Ralign_X+20;
				//drawPosX 			= (breakWidth/2)-20;
				drawPosX 			= (breakWidth/2)-45;
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += (layout.getDescent() + layout.getLeading())*2;
				
			}
			
			line = new AttributedString(" No."+m_lakderana_add1+","+m_lakderana_add2+","+m_lakderana_city_name+"");
			//line = new AttributedString("No.100, Buthgamuwa Road, Rajagiriya");
			System.out.println("m_lakderana_add1 ==> No."+m_lakderana_add1+","+m_lakderana_add2+","+m_lakderana_city_name+"");
			line.addAttribute(TextAttribute.FONT, titleFont);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);
				//drawPosX 			= Ralign_X+20;
				//drawPosX 			= (breakWidth/2)-20;
				drawPosX 			= (breakWidth/2)-30;
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += (layout.getDescent() + layout.getLeading())*6;
				
			}
			
			//-------END COMPANY DETAIL------//	
			
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
				drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance();
				drawPosY            += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY            += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
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
				drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance();
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
					drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance();
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
					drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance();
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
					drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance();
					drawPosY            += layout.getAscent();
					layout.draw(g2d, drawPosX, drawPosY);
					drawPosY             += (layout.getDescent() + layout.getLeading())*10;
				}
			}
			
			
			line = new AttributedString("Dear Sir/Madam,");
			line.addAttribute(TextAttribute.FONT, titleFont);
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
			
			
			
			line = new AttributedString("HIRE PURCHASE AGREEMENT NO "+m_application_no+" - "+m_contact_full_name+"");
			//line = new AttributedString(data);
			line.addAttribute(TextAttribute.FONT, titleFont);
			line.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			while (lbm.getPosition() < paragraphEnd) {
				layout 	= lbm.nextLayout(breakWidth);	
				drawPosX 			= layout.isLeftToRight() ? 80 : breakWidth - layout.getAdvance(); 
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			drawPosY += 10;
			String data ="";
			
			data= "We refer to the above mentioned HIRE PURCHASE agreement and give below the following particulars."; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data);
			line.addAttribute(TextAttribute.FONT, bodyFont);
			paragraph      = line.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd   = paragraph.getEndIndex();
			lbm            = new LineBreakMeasurer(paragraph, frc);
			
			lbm.setPosition(paragraphStart);
			
			int count = 1;
			while (lbm.getPosition() < paragraphEnd) {
				//System.out.println(" *** lbm.pos ("+count+") --> "+lbm.getPosition()+" ***");
				count++;
				layout 	= lbm.nextLayout(breakWidth);
				layout 				= layout.getJustifiedLayout(wrappingWidth);
				drawPosY += layout.getAscent();
				layout.draw(g2d, drawPosX, drawPosY);
				drawPosY += layout.getDescent() + layout.getLeading();
			}
			
			drawPosY += 20;
			
			//	line = new AttributedString("VEHICLE        :"+m_vehicle_no);
			line = new AttributedString("VEHICLE "+m_print_method.Add_Space(31)+":      "+m_vehicle_no+"");
			//line = new AttributedString("INSURANCE  "+m_print_method.Add_Space(25)+": ");
			line.addAttribute(TextAttribute.FONT, bodyFont);
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
			
			line = new AttributedString("LOC   "+m_print_method.Add_Space(38)+":      "+m_Ins_count+" Months");
			
			
			line.addAttribute(TextAttribute.FONT, bodyFont);
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
			
		//	line = new AttributedString("MONTHLY RENTAL "+m_print_method.Add_Space(12)+":     SLR "+mm_NET_RENTAL_AMOUNT+"");
		    	line = new AttributedString("MONTHLY RENTAL "+m_print_method.Add_Space(12)+":     SLR "+nf.format(mm_NET_RENTAL_AMOUNT)+"");
			
			line.addAttribute(TextAttribute.FONT, bodyFont);
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
			
			
			line = new AttributedString("DUE DATE  "+m_print_method.Add_Space(28)+":      "+mm_RENTAL_DATE+"");
			
			line.addAttribute(TextAttribute.FONT, bodyFont);
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
			
			line = new AttributedString("NEXT RENTAL DUE ON "+m_print_method.Add_Space(6)+":      "+mm_NEXT_RENTAL_DATE+"");
			
			line.addAttribute(TextAttribute.FONT, bodyFont);
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
			
			line = new AttributedString("INSURANCE  "+m_print_method.Add_Space(25)+":      COMPREHENSIVE* ");
			line.addAttribute(TextAttribute.FONT, bodyFont);
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
				drawPosY            += (layout.getDescent() + layout.getLeading());
			}	
			drawPosY += 10;
			
			String data1 ="";
			
			data1= "*Insurance to be renewed yearly with an assignment to LAKDERANA INVESTMENTS "+
				"LTD including (SRCC, FLOOD & TC covers)"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data1);
			line.addAttribute(TextAttribute.FONT, titleFont);
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
			
			data2= "We wish to inform you that OVERDUE INTEREST at 4% P.M is "+
				"charged on all rentals received after the due date. As such "+
				"we request you to kindly ensure that payments are made to us promptly on the due date."; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data2);
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
			drawPosY += 10;
			
			String data3 ="";
			
			data3= "Please quote above agreement number when making payments."; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data3);
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
			drawPosY += 10;
			
			String data4 ="";
			
			data4= "We trust that the information given above is sufficient for your purpose. "+
				"If however, you require any further information or any clarification, "+ 
				"please don't hesitate to contact the undersigned."; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data4);
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
			drawPosY += 10;
			
			String data5 ="";
			
			data5= "Thanking you for the opportunity given us to serve you."; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data5);
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
			drawPosY += 10;
			
			String data6 ="";
			
			data6= "Yours faithfully,"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data6);
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
			drawPosY += 10;
			
			String data10 ="";
			data10= "Lakderana Investments Limited"; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data10);
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
			drawPosY += 10;
			
			
			String data7 ="";
			data7= "Note: This is a computer generated letter and hence no signature is required."; 
			
			//data = String.format("%-100s", data);
			
			line = new AttributedString(data7);
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
			drawPosY += 10;
			
	/*		String data8="";
			
			data8=    "ie hq isxy, mßj¾;kh ioyd miq msg n,kak";
			//data8= "&#3523;&#3536;.&#3514;&#3540;: &#3523;&#3538;&#3458;&#3524;&#3517; &#3508;&#3515;&#3538;&#3520;&#3515;&#3530;&#3501;&#3505;&#3514; &#3523;&#3503;&#3524;&#3535; &#3508;&#3523;&#3540; &#3508;&#3538;&#3495; &#3510;&#3517;&#3505;&#3530;&#3505";
			
			
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
			drawPosY += 20;
		*/	
			//System.out.println("mm_GUAR_ID ="+mm_GUAR_ID);
			if(m_guarantor_full_name !=""){
				
				line = new AttributedString("Guarantor 01 - "+m_guarantor_full_name);
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
				drawPosY += 10;
				
			} if(m_guarantor_full_name1 !=""){
				
				line = new AttributedString("Guarantor 02 - "+m_guarantor_full_name1);
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
				drawPosY += 10;
				
			} if(m_guarantor_full_name2 !=""){
				
				line = new AttributedString("Guarantor 03 - "+m_guarantor_full_name2);
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
				drawPosY += 10;
			} // End if	
			
			
		}
		catch(Exception ex){
			System.out.println("Error (RL) First Letter "+ex.toString());
		}
		
		g2d.dispose();
		System.gc();
		return (PAGE_EXISTS);
		
	}
}
