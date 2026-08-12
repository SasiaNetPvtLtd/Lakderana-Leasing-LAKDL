


import java.io.*;
import java.util.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
import java.sql.*;

/*added by madhawa trying rich text for mat file creation*/
/*
COMMENTED BY MADHAWA 2012-01-02
import com.lowagie.text.*;
import com.lowagie.text.rtf.*;
import java.text.*;
*/
/*
ADDED BY MADHAWA TO GENERATE pdf */
// ITEXT 2 
import java.io.FileOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Element;	
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;
import com.lowagie.text.Chunk;

import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;

/*
 ITEXT 5.3.1
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
*/

//public class NETFAC_FA_OP_generate_dishonur_letters extends HttpServlet {
public class LAKDL_AF_Re_generater_letters{   
	Connection conn=null;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
	Statement stmt,stmt1,hp_stmt,hp_stmt1,hp_stmt2;	
	ResultSet rs,rs1;	
	String reqstr;
	LAKDL_AF_CO_conn_methods m_sn_methods=null;
	java.text.NumberFormat nf;
	//font declaration 
	private static Font catFont = new Font(Font.getFamilyIndex("Arial"), 8,Font.NORMAL);
	private static Font BoldFont =new Font(Font.getFamilyIndex("Arial"), 8,Font.BOLD);
	private static Font SinhalaFont =new Font(Font.getFamilyIndex("aKandyNew"), 8,Font.BOLD);
	private static Font SinhalaFont_Normal =new Font(Font.getFamilyIndex("aKandyNew"), 8,Font.NORMAL);
	
	
	
	
	
	String m_fschema_name;
	String m_schema_name;
	String company_name_2;		
	String m_client_name;
	String m_username;
	String m_html_client_url;
	String m_class_url;
	
	
	public LAKDL_AF_Re_generater_letters(Connection validConnection,LAKDL_AF_CO_conn_methods m_sn_methods_obj)
	{
		conn=validConnection;
		m_sn_methods=m_sn_methods_obj;
		conn=validConnection;
		
	}		
	
	
	public void generate_letter_Pdfbody(Document document,String m_payement_no,String Lease_type,String Client_code)
	{
		
		try
		{
			
			
			if(Lease_type.equals("HIREPURCH"))
			{
				//FOR HIGHREPURCH REFER LETTER AT AF_RE_NOT_hire_purch?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				
				String m_debtor_code="-",m_client_name="-",m_invoice_seq_no="-",m_batch_no="-",m_return_no="-",m_facility_no="-";
				String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-";
				String m_contact_person="-",m_contact_desig="-";
				String m_d_contact_person="-",m_d_contact_desig="-";
				double m_chq_amount=0,m_tot_arrears=0,m_odi_arrears=0;
				
				//orient data----------			
				String m_orient_name="";
				String m_orient_add1="";
				String m_orient_add2="";
				String m_orient_city_name="";
				String m_orient_tel_no="";
				String m_orient_fax_no="";
				String m_LAKDL_vat_no="",m_LAKDL_reg_no="";
				String m_vat_precentage="";
				
				String m_full_name="";
				String m_add1="";
				String m_add2="";
				String m_city_name="";
				String m_title="";
				String m_client_type=""; 
				String m_nic_no="";
				String m_Letter_date="";
				String m_client_no="";
				String m_app_no="";
				
				nf = java.text.NumberFormat.getInstance(Locale.US);
				nf.setMinimumFractionDigits(2);
				nf.setMaximumFractionDigits(2);
				
				//m_chksql=req.getParameter("chksql");
				m_facility_no=m_payement_no;
				m_client_no=Client_code;			
				
				
				
				hp_stmt = conn.createStatement();
				hp_stmt1 = conn.createStatement();
				hp_stmt2 = conn.createStatement();
				rs = hp_stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'Month DD,YYYY') FROM DUAL ");								
				boolean more = rs.next();
				//out.print("more1"+more);
				if(more)
				{
					m_Letter_date=rs.getString(1);
				}
				
				//added by nuwan de silva on 08-11-07
				rs = hp_stmt.executeQuery(" SELECT "+
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
				//	out.print("more11"+more);
				if(more)
				{
					m_orient_name=rs.getString(1);
					m_orient_add1=rs.getString(2);
					m_orient_add2=rs.getString(3);
					m_orient_city_name=rs.getString(4);
					m_orient_tel_no=rs.getString(5);
					m_orient_fax_no=rs.getString(6);
					m_vat_precentage=rs.getString(7);			
					m_LAKDL_vat_no=rs.getString(8);			
					m_LAKDL_reg_no=rs.getString(9);			
				}
				
				
				//added by nuwan de silva on 08-11-07--------------------------------
				rs1= hp_stmt1.executeQuery("SELECT A.FINANCE_NO,A.CLIENT_CODE ,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) ,SUM(A.BALANCE_TO_BE_RECEIVED) BALANCE, "+
					" B.TRANSACTION_TYPE,B.APPLICATION_NO,TO_CHAR(B.ACTIVATED_DATE,'DD/MM/YYYY') "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
					" AND  TO_DATE(A.DUE_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" AND A.ACTIVE_STATUS='Y' "+
					" AND A.FINANCE_NO ='"+m_facility_no+"' "+
					" AND A.BALANCE_TO_BE_RECEIVED >0 "+
					" GROUP BY A.FINANCE_NO,A.CLIENT_CODE,B.TRANSACTION_TYPE,B.APPLICATION_NO,B.ACTIVATED_DATE  ");
				
				more=rs1.next();
				//out.print("more12"+more);
				if(more)
				{
					m_tot_arrears=rs1.getDouble(4);
					m_app_no=rs1.getString(6);
					m_client_no=rs1.getString(2);
					m_client_name = rs1.getString(3);
				}
				
				// added by nuwan de silva on 01-09-2008---------------
				rs1= hp_stmt1.executeQuery(" SELECT NVL(SUM(ODI_BAL_AMOUNT),0)  "+
					" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE  A.INVOICE_NO=B.INVOICE_NO "+
					" AND    B.FINANCE_NO='"+m_facility_no+"' "+
					//" AND    A.ODI_DATE<=SYSDATE ");
					" AND  TO_DATE(A.ODI_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
				
				more=rs1.next();
				
				if(more)
				{
					m_odi_arrears=rs1.getDouble(1);
				}
				
				
				rs= hp_stmt.executeQuery
					("	SELECT "+	
					" 'CLIENT', "+ //1
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ') ,   "+ //2//INITCAP BY LALANKA on 19-06-2009
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
					" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
					" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //2
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					" WHERE   CLIENT_CODE =UPPER('"+m_client_no+"') ");
				
				
				more = rs.next();		
				//out.print("more112"+more);
				if(more)
				{	
					m_full_name =rs.getString(2);
					m_add1      =rs.getString(3);
					m_add2      =rs.getString(4);
					m_city_name =rs.getString(5);
					m_nic_no    =rs.getString(6);
				}				
				
				
				
				Paragraph p=new Paragraph();
				Chunk topic=new Chunk("BY REGISTERED POST ");
				topic.setUnderline(0.1f, -2f);
				topic.setFont(BoldFont);
				p.add(topic);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(m_Letter_date,catFont));
				p.add(new Paragraph(""));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(m_full_name,catFont));
				p.add(new Paragraph(m_add1,catFont));
				if(m_add2==null)
				{
					m_add2="-";
				}	
				p.add(new Paragraph(m_add2,catFont));
				p.add(new Paragraph(m_city_name,catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Paragraph("Dear Sir ",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				
				
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				Chunk para=new Chunk("NOTICE OF TERMINATION OF HIRE PURCHASE");
				para.setUnderline(0.1f, -2f);
				para.setFont(BoldFont);
				p.add(para);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				para=new Chunk("AGREEMENT NO : "+m_facility_no);
				para.setUnderline(0.1f, -2f);
				para.setFont(BoldFont);
				p.add(para);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				
				
				
				p=new Paragraph();
				p.setSpacingBefore(10);
				p.add(new Paragraph("We refer to the Hire Purchase Agreement executed by you",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);	
				
				
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk("We hereby inform you that despite our repeated requests and reminders, you have failed to pay the arrears of  "+
					" lease rentals due on the above Agreement totaling ",catFont));
				p.add(new Chunk("Rs "+nf.format(m_tot_arrears+m_odi_arrears),BoldFont));
				p.add(new Chunk("/=   in terms of the conditions in the Agreement.",catFont));	
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk("In the circumstances, we have to inform you that, unless you settle the above arrears in full within",catFont));
				p.add(new Chunk(" 14 days",BoldFont));
				p.add(new Chunk("from the date of receipt of this letter, we will be compelled to take steps to terminate the agreement, "+
					" in accordance with the provisions of the Hire Purchase Agreement. ",catFont));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Paragraph(" We urge you therefore to kindly make arrangements to settle the arrears without any further delay. ",catFont)); 
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				
				
				
				p=new Paragraph();
				p.setSpacingBefore(13);
				p.add(new Paragraph("Yours faithfully,",catFont));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				p=new Paragraph();
				//p.setSpacingBefore(6);
				p.add(new Paragraph(m_orient_name.toUpperCase(),BoldFont));
				
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				
				p=new Paragraph();
				p.setSpacingBefore(20);
				p.add(new Paragraph("...............................",BoldFont));
				p.add(new Paragraph("Manager Recoveries ",BoldFont));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				
				p=new Paragraph();
				para=new Chunk("* A copy of this letter is being forwarded to the guarantor/s for their information and necessary action.");
				para.setUnderline(0.1f, -2f);
				para.setFont(BoldFont);
				p.add(para);
				
				
				String sql_gur=" SELECT "+
					"  A.APPLICATION_NO, "+
					"  A.GUARANTOR_CODE, "+
					"  INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE)), "+//INITCAP BY LALANKA ON 19-06-2009
					"  INITCAP(NVL(B.ADDRESS1,'-')), "+
					"  INITCAP(NVL(B.ADDRESS2,'-')), "+
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-')) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ";
				
				
				Vector gur_name=new Vector();
				Vector gur_add1=new Vector();
				Vector gur_add2=new Vector();
				rs1 = hp_stmt1.executeQuery (sql_gur);
				int count=0;
				int i=0,j=1;
				while(rs1.next())
				{
					gur_name.addElement(rs1.getString(3));
					gur_add1.addElement(rs1.getString(4));
					gur_add2.addElement(rs1.getString(5));
					count=count+1;
				}
				
				
				
				
				p=new Paragraph();
				p.setSpacingBefore(20);
				p.add(new Paragraph(""));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				PdfPTable table = new PdfPTable(5); 
				table.getDefaultCell().setBorder(0);
				table.setWidths(new int[]{ 3, 1, 1, 1,3 });
				table.setWidthPercentage(100);
				
				int vector_length=gur_name.size();
				PdfPCell cell_hp = new PdfPCell();
				
				
				
				
				if(count==1) //single gurantor
				{
					
					p=new Paragraph();
					p.setSpacingBefore(6);
					p.add(new Paragraph(gur_name.get(i).toString(),catFont));
					p.add(new Paragraph(gur_add1.get(i).toString(),catFont));
					p.add(new Paragraph(gur_add2.get(i).toString(),catFont));
					p.setAlignment(Element.ALIGN_JUSTIFIED);
					document.add(p);
				}
				else if(count!=0) //not sibngle,either 0,more than one
				{	
					cell_hp= new PdfPCell(new Phrase(gur_name.get(i).toString(),catFont));
					cell_hp.setBorder(Rectangle.NO_BORDER);
					//table.addCell("td"+gur_name.get(i).toString());
					table.addCell(cell_hp);
					table.addCell(" ");
					table.addCell(" ");
					table.addCell(" ");
					if(vector_length>=2)
					{	
						cell_hp= new PdfPCell(new Phrase(gur_name.get(j).toString(),catFont));
						cell_hp.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell_hp);
					}
					else
					{
						table.addCell(" ");
					}	
					
					cell_hp= new PdfPCell(new Phrase(gur_add1.get(i).toString(),catFont));
					cell_hp.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell_hp);
					table.addCell(" ");
					table.addCell(" ");
					table.addCell(" ");
					if(vector_length>=2)
					{	
						cell_hp= new PdfPCell(new Phrase(gur_add1.get(j).toString(),catFont));
						cell_hp.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell_hp);
					}else
					{
						table.addCell(" ");
						
					}	
					
					
					cell_hp= new PdfPCell(new Phrase(gur_add2.get(i).toString(),catFont));
					cell_hp.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell_hp);
					table.addCell(" ");
					table.addCell(" ");
					table.addCell(" ");
					if(vector_length>=2)
					{	
						cell_hp= new PdfPCell(new Phrase(gur_add2.get(j).toString(),catFont));
						cell_hp.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell_hp);
						
					}
					else
					{
						table.addCell(" ");
					}	
					
				}
				
				
				
				
				
				
				
				
				
				/*
				table.addCell("td"+gur_name.get(i).toString());
				table.addCell("1");
				table.addCell("");
				table.addCell("");
				table.addCell(gur_name.get(j).toString());
				
				
				table.addCell("td"+gur_add1.get(i).toString());
				table.addCell("1");
				table.addCell("");
				table.addCell("");
				table.addCell(gur_add1.get(j).toString());
				
				
				table.addCell("td"+gur_add2.get(i).toString());
				table.addCell("1");
				table.addCell("");
				table.addCell("");
				table.addCell(new Paragraph(gur_add2.get(j).toString()));
				*/
				
				
				p=new Paragraph();
				p.setSpacingBefore(20);
				document.add(table);
				
				
				
				
				
				
				
				
			}
			else  //here at the moment FINLEASE AND OTHERS ARE HANDLED WITH SAME LETTER
			{
				//FOR FINLEASE REFER AF_RE_NOT_fin_lease?chksql=main_page&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&print=TRUE\";"); 
				
				
				
				/**taken from original letter **/
				String m_debtor_code="-",m_client_name="-",m_invoice_seq_no="-",m_batch_no="-",m_return_no="-",m_facility_no="-";
				String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-";
				String m_contact_person="-",m_contact_desig="-";
				String m_d_contact_person="-",m_d_contact_desig="-";
				double m_chq_amount=0,m_tot_arrears=0,m_odi_arrears=0;
				
				
				nf = java.text.NumberFormat.getInstance(Locale.US);
				nf.setMinimumFractionDigits(2);
				nf.setMaximumFractionDigits(2);
				
				
				//orient data----------			
				String m_orient_name="";
				String m_orient_add1="";
				String m_orient_add2="";
				String m_orient_city_name="";
				String m_orient_tel_no="";
				String m_orient_fax_no="";
				String m_LAKDL_vat_no="",m_LAKDL_reg_no="";
				String m_vat_precentage="";
				
				String m_full_name="";
				String m_add1="";
				String m_add2="";
				String m_city_name="";
				String m_title="";
				String m_client_type=""; 
				String m_nic_no="";
				String m_print="";
				String m_client_no="";
				
				
				
				//m_facility_no=req.getParameter("finance_no");
				m_facility_no=m_payement_no;
				//m_client_no=req.getParameter("client_code");		
				m_client_no=Client_code;
				//m_print=req.getParameter("print");
				m_print="Y"; //HARDCODE
				String m_master_lease_no="";
				String m_Letter_date="";
				String m_lease_date="";
				String m_app_no="";
				
				
				
				
				
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'Month DD,YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more)
				{
					m_Letter_date=rs.getString(1);
				}
				
				//added by nuwan de silva on 08-11-07
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
					m_orient_name=rs.getString(1);
					m_orient_add1=rs.getString(2);
					m_orient_add2=rs.getString(3);
					m_orient_city_name=rs.getString(4);
					m_orient_tel_no=rs.getString(5);
					m_orient_fax_no=rs.getString(6);
					m_vat_precentage=rs.getString(7);			
					m_LAKDL_vat_no=rs.getString(8);	
					m_LAKDL_reg_no=rs.getString(9);	
				}
				
				
				
				//added by nuwan de silva on 08-11-07------
				rs1= stmt1.executeQuery("SELECT A.FINANCE_NO,A.CLIENT_CODE ,INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) ,SUM(A.BALANCE_TO_BE_RECEIVED) BALANCE, "+
					" B.TRANSACTION_TYPE,B.APPLICATION_NO,TO_CHAR(B.ACTIVATED_DATE,'DD/MM/YYYY') ,NVL(B.MASTER_AGREEMENT_NO,'-') "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
					" AND  TO_DATE(A.DUE_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" AND A.ACTIVE_STATUS='Y' "+
					" AND A.FINANCE_NO ='"+m_facility_no+"' "+
					" AND A.BALANCE_TO_BE_RECEIVED >0 "+
					" GROUP BY A.FINANCE_NO,A.CLIENT_CODE,B.TRANSACTION_TYPE,B.APPLICATION_NO,B.ACTIVATED_DATE ,B.MASTER_AGREEMENT_NO  ");
				
				
				
				more=rs1.next();
				if(more)
				{
					m_tot_arrears=rs1.getDouble(4);
					m_app_no=rs1.getString(6);
					m_lease_date=rs1.getString(7);
					m_client_no=rs1.getString(2);
					m_client_name = rs1.getString(3);
					m_master_lease_no = rs1.getString(8);
				}
				
				
				
				// added by nuwan de silva on 01-09-2008---------------
				rs1= stmt1.executeQuery(" SELECT NVL(SUM(ODI_BAL_AMOUNT),0)  "+
					" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE  A.INVOICE_NO=B.INVOICE_NO "+
					" AND    B.FINANCE_NO='"+m_facility_no+"' "+
					//" AND    A.ODI_DATE<=SYSDATE ");
					" AND  TO_DATE(A.ODI_DATE)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
				
				more=rs1.next();
				
				if(more)
				{
					m_odi_arrears=rs1.getDouble(1);
				}
				
				
				
				String Client_Data=" SELECT  "+
					" 'CLIENT', "+ //1
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(TITLE) || '. ' || INITCAP(FULL_NAME),'C',/*'MESS' || '. '  || */INITCAP(FULL_NAME)),' ') ,   "+ //2//INITCAP By Lalanka on 05-06-2009
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS1),'C',INITCAP(REGISTERED_ADDRESS1)),' '),  "+ //3
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(ADDRESS2),'C',INITCAP(REGISTERED_ADDRESS2)),' ') , "+ //4
					" NVL(INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
					" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
					" NVL(DECODE(CLIENT_TYPE,'I',INITCAP(FULL_NAME),'C',INITCAP(FULL_NAME)),' ')    "+ //2
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					" WHERE   CLIENT_CODE =UPPER('"+m_client_no+"') ";
				
				rs = stmt.executeQuery(Client_Data);
				more = rs.next();		
				
				if(more)
				{	
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_nic_no=rs.getString(6);
				}				
				
				
				
				Paragraph p=new Paragraph();
				Chunk topic=new Chunk("BY REGISTERED POST ");
				topic.setUnderline(0.1f, -2f);
				topic.setFont(BoldFont);
				p.add(topic);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(m_Letter_date,catFont));
				p.add(new Paragraph(""));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(m_full_name,catFont));
				p.add(new Paragraph(m_add1,catFont));
				if(m_add2==null)
				{
					m_add2="-";
				}	
				p.add(new Paragraph(m_add2,catFont));
				p.add(new Paragraph(m_city_name,catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				
				p.setSpacingBefore(6);
				p.add(new Paragraph("Dear Sir/Madam ",catFont));
				
				
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				Chunk para=new Chunk("NOTICE OF SUBSTANTIAL FAILURE OF LEASE ");
				para.setUnderline(0.1f, -2f);
				para.setFont(BoldFont);
				p.add(para);
				
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				para=new Chunk("Master Lease Agreement No : "+m_master_lease_no+"  Date :"+m_lease_date);
				para.setUnderline(0.1f, -2f);
				para.setFont(BoldFont);
				p.add(para);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				para=new Chunk("SCHEDULE NO : "+m_facility_no);
				para.setUnderline(0.1f, -2f);
				para.setFont(BoldFont);
				p.add(para);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.add(new Paragraph(rs.getString(4),catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(10);
				p.add(new Paragraph("We refer to the above Lease Agreement executed by you.",catFont));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);	
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk("We hereby inform you that despite our repeated requests and reminders, you have failed to pay the arrears of "+ 
					" lease rentals due on your above lease totaling Rs ",catFont));
				p.add(new Chunk(nf.format(m_tot_arrears+m_odi_arrears),BoldFont));
				p.add(new Chunk("/=  in terms of the conditions in the lease agreement "+
					" ,your non-payment of lease rentals on the due dates has caused a substantial failure of this lease.",catFont));	
				p.add(new Paragraph(""));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk("In the circumstances, we have to inform you that, unless you settle the above arrears in full within",catFont));
				p.add(new Chunk(" 7 days",BoldFont));
				p.add(new Chunk("from the date of receipt of this letter, we will be compelled to take steps to terminate the lease, "+
					" in accordance with the provisions of the lease agreement. ",catFont));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);	
				
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Paragraph("We urge you therefore to kindly make arrangements to settle the arrears without any further delay.",catFont)); 
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				FontFactory.register("C:\\WINDOWS\\Fonts\\akbn1.ttf","aKandyNewSupplement");
				Font myaKandyNew=FontFactory.getFont("aKandyNew",8,Font.NORMAL);
				BaseFont bf=myaKandyNew.getBaseFont();
				
				FontFactory.register("C:\\WINDOWS\\Fonts\\akbn1s.ttf","aKandyNewSupplement");
				Font myaKandyNewSupp=FontFactory.getFont("aKandyNewSupplement",8,Font.NORMAL);
				BaseFont bf2=myaKandyNewSupp.getBaseFont();
				
				
				/*
				p=new Paragraph();
				p.add(new Chunk("kL bÚ °ìäM aAk  ",myaKandyNew));
				p.add(new Chunk(m_master_lease_no,catFont));
				
				para.setUnderline(0.1f, -2f);
				//para.setFont(SinhalaFont);
				p.add(para);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				*/
				
				
				p=new Paragraph();
				p.add(new Paragraph(""));
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p=new Paragraph();
				para=new Chunk("kL bÚ °ìäM aAk  ");
				para.setUnderline(0.1f, -2f);
				para.setFont(myaKandyNew);
				p.add(para);
				
				para=new Chunk(m_master_lease_no);
				para.setUnderline(0.1f, -2f);
				para.setFont(catFont);
				p.add(para);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				
				
				p=new Paragraph();
				para=new Chunk("up@L");
				para.setUnderline(0.1f, -2f);
				para.setFont(myaKandyNew);
				p.add(para);
				para=new Chunk("k");
				para.setUnderline(0.1f, -2f);
				para.setFont(myaKandyNewSupp);
				p.add(para);
				para=new Chunk("z aAk ");
				para.setUnderline(0.1f, -2f);
				para.setFont(myaKandyNew);
				p.add(para);
				para=new Chunk(m_facility_no);
				para.setUnderline(0.1f, -2f);
				para.setFont(catFont);
				p.add(para);
				para=new Chunk(" Øny - ");
				para.setUnderline(0.1f, -2f);
				para.setFont(myaKandyNew);
				p.add(para);
				para=new Chunk(m_lease_date);
				para.setUnderline(0.1f, -2f);
				para.setFont(catFont);
				p.add(para);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				
				
				
				p=new Paragraph();
				p.setSpacingBefore(10);
				p.add(new Paragraph("ihñN sqhN ob ìâN aWsN ¿Ýmt @yÚÐ kL bÚ °ìä@mö ö< v`Ýk @gìM "+
					" sMbN[@yN ap ìâN evn lq âµ k#>íM h` iLÅM vlt ob@gN ¿âÚ ×ñc`ryK "+
					" @n`l#£Ð bv kzg`é@vN qNv` âçn awr, y@}~Kw kLbÚ °ìä@mö î ob@G b#ÙM "+
					//" h` vgÀM Îâ a`k`r@yN ié ¿Ýmt a@p`@h`sW ím @Hó@k`t@gn ap @qp`R&#92;vyN "+
					" h` vgÀM Îâ a`k`r@yN ié ¿Ýmt a@p`@h`sW ím @Hó@k`t@gn ap @qp`R\\vyN "+ 									 				
					" awr aWsN ¿Ýmt @yÚÐ eÀ kL bÚ °ìä@mö ÎymyN h` @k`N@Qâ kd ¿ÝmK "+
					" âÚv a#w. "+
					" ",myaKandyNew));
				
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Chunk("ihw kL bÚ °ìä@mö ÎymyN h` @k`N@Qâ vlt aÐv qKv` a#ñ Øn 07 k k`ly "+
					" ólÙ sMÖRz ö< Ëql vn r#.",myaKandyNew));
				p.add(new Chunk(nf.format(m_tot_arrears+m_odi_arrears),catFont));
				p.add(new Chunk("K ap a`ywny @vw @gìmt ob a@p`@h`sW  "+
					" îv@g`W Ïñy m°N h` ap @qp`R\\vyN awr el¢mt @yÚn kL bÚ °ìä@mö  "+
					" ×k`ryNt aÐv ap@G aûñv`âkM áy`Wmk krÉN bQq avsN ¿Ýmt ktýó  "+
					" krN@nË. ",myaKandyNew));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Paragraph("ihñN qKv` a#ñ k`l pr`sy ólØ eÀ ög Ëql Óyìmt ob ktýó krÐ a#w#û ap bl`@p`@r`Wó @vË. ",myaKandyNew));		
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				
				p=new Paragraph();
				p.setSpacingBefore(13);
				p.add(new Paragraph("@myt ì|v`â, ",myaKandyNew));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				p=new Paragraph();
				//p.setSpacingBefore(6);
				p.add(new Paragraph("oÝyNT fûn#N;L sRìsS @k~p@R;N ÄÊtD ",myaKandyNew));
				
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				p=new Paragraph();
				p.setSpacingBefore(10);
				p.add(new Paragraph(" shk`r s`mn&[k`Þ  ",myaKandyNew));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				p=new Paragraph();
				para=new Chunk("@mm ÄÓ@Yµ ÓtpwK a#pkr#vN@G q#ÐvW ímt sh av&#92;& æy`m`RgyN sqh` yvÐ l#@B.");
				para.setUnderline(0.1f, -2f);
				para.setFont(myaKandyNew);
				p.add(para);
				
				
				
				String sql_gur=" SELECT "+
					"  A.APPLICATION_NO, "+
					"  A.GUARANTOR_CODE, "+
					"  INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE)), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL(B.ADDRESS1,'-')), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL(B.ADDRESS2,'-')), "+//INITCAP by Lalanka on 05-6-2009
					"  INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-')), "+
					"  NVL(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE)||'.','C',''),' ') "+	//added by madhawa 2012-01-12
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ";
				//out.println(sql_gur);
				
				Vector gur_name=new Vector();
				Vector gur_add1=new Vector();
				Vector gur_add2=new Vector();
				
				rs1 = stmt1.executeQuery (sql_gur);
				
				
				
				
				p=new Paragraph();
				p.setSpacingBefore(6);
				p.add(new Paragraph("(a#pkr#)",myaKandyNew));		
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				int count=0;
				int i=0,j=1;
				
				while(rs1.next())
				{
					gur_name.addElement(rs1.getString(7)+rs1.getString(3));
					gur_add1.addElement(rs1.getString(4));
					gur_add2.addElement(rs1.getString(5));
					count=count+1;
				}
				
				
				
				
				
				//"gur_name.get(i).toString()"
				PdfPTable table = new PdfPTable(5); // Code 1
				//table.getDefaultCell().setBorder(0);
				table.setWidths(new int[]{ 3, 1, 1, 1,3 });
				table.setWidthPercentage(100);
				// Code 2
				int vector_length=gur_name.size();
				
				PdfPCell cell = new PdfPCell();
				
				
				
				
				
				if(count==1) //single gurantor
				{
					
					p=new Paragraph();
					p.setSpacingBefore(6);
					p.add(new Paragraph(gur_name.get(i).toString(),catFont));
					p.add(new Paragraph(gur_add1.get(i).toString(),catFont));
					p.add(new Paragraph(gur_add2.get(i).toString(),catFont));
					p.setAlignment(Element.ALIGN_JUSTIFIED);
					document.add(p);
					
					
					
					
					
				}
				else if(count!=0) //not sibngle,either 0,more than one
				{	
					cell= new PdfPCell(new Phrase(gur_name.get(i).toString(),catFont));
					cell.setBorder(Rectangle.NO_BORDER);
					//table.addCell("td"+gur_name.get(i).toString());
					table.addCell(cell);
					table.addCell(" ");
					table.addCell(" ");
					table.addCell(" ");
					if(vector_length>=2)
					{	
						cell= new PdfPCell(new Phrase(gur_name.get(j).toString(),catFont));
						cell.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell);
					}
					else
					{
						table.addCell(" ");
					}	
					
					cell= new PdfPCell(new Phrase(gur_add1.get(i).toString(),catFont));
					cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
					table.addCell(" ");
					table.addCell(" ");
					table.addCell(" ");
					if(vector_length>=2)
					{	
						cell= new PdfPCell(new Phrase(gur_add1.get(j).toString(),catFont));
						cell.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell);
					}else
					{
						table.addCell(" ");
						
					}	
					
					
					cell= new PdfPCell(new Phrase(gur_add2.get(i).toString(),catFont));
					cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
					table.addCell(" ");
					table.addCell(" ");
					table.addCell(" ");
					if(vector_length>=2)
					{	
						cell= new PdfPCell(new Phrase(gur_add2.get(j).toString(),catFont));
						cell.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell);
						
					}
					else
					{
						table.addCell(" ");
					}	
					
				}
				
				document.add(table);
				
				
				
				//else if(count!=0)
				//{
				/*
				out.println("<br>");						
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_name.get(i).toString()+"</td>");
				out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_name.get(j).toString()+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add1.get(i).toString()+"</td>");
				out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add1.get(j).toString()+"</td></tr>");
				out.println("<tr><td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add2.get(i).toString()+"</td>");
				out.println("<td width='45%' class='rep-body' style='{font:10px;text-align:justify;}'>"+gur_add2.get(j).toString()+"</td></tr>");
				out.println("</table>");
				*/
				//}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				document.close();
			}	
			
			
			/*
			Paragraph p=new Paragraph();
			//p.add(new Paragraph("REGISTERED POST",catFont));
			Chunk topic=new Chunk("REGISTERED POST");
			topic.setUnderline(0.1f, -2f);
			topic.setFont(BoldFont);
			p.add(topic);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			
			String	client_code="";
			String payment_type="";
			String cheque_number="";
			String banking_date="";	
			String bank_name="";
			String	branch_name="";	
			String company_name="";	
			String return_reason="";
			String system_date_dd="";	
			String system_date_mm="";		
			String system_date_yy="";	
			String recept_amount="";	
			double recept_amount_double=0;		
			
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'ddth'),TO_CHAR(SYSDATE,'Month'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL");
			if(rs.next())
			{
				system_date_dd=rs.getString(1);
				system_date_mm=rs.getString(2);
				system_date_yy=rs.getString(3);
				}		
				
				rs=stmt.executeQuery(	"   SELECT  DECODE(REC_SETT_TYPE,'DP',DEBTOR_CODE,'CP',CLIENT_CODE) PAYEE,REC_SETT_TYPE,CHEQUE_NO,TO_CHAR(BANK_DATE,'DD-MM-YYYY'), "+
				"   "+m_schema_name+".AF_CO_GET_BANK_BRANCH_DETAILS(PAYER_BRANCH_CODE,'BRANCH_CODE','BANK_NAME') BANK_NAME, "+
				"   "+m_schema_name+".AF_CO_GET_BANK_BRANCH_DETAILS(PAYER_BRANCH_CODE,'BRANCH_CODE','BRANCH_NAME') BRANCH_NAME,REC_AMOUNT_CURR "+
				"	FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT   "+
				"   WHERE RECEIPT_NO='"+m_payement_no+"'");	
				if(rs.next())
				{
				client_code=rs.getString(1);
				payment_type=rs.getString(2);
				cheque_number=rs.getString(3);
				banking_date=rs.getString(4);
				bank_name=rs.getString(5);
				branch_name=rs.getString(6);
				recept_amount=rs.getString(7);
				recept_amount_double=rs.getDouble(7);
				}		
				
				
				
				rs=stmt.executeQuery(	"   SELECT  NVL(REGISTERED_ADDRESS1,'-'),NVL(REGISTERED_ADDRESS2,'-'),NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')"+
				"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT   "+
				"   WHERE CLIENT_CODE='"+client_code+"'");
				p=new Paragraph();
				if(rs.next())
				{       
				p.add(new Paragraph(system_date_dd+" "+system_date_mm.trim()+" "+system_date_yy.trim(),catFont));  
				p.add(new Paragraph(""));
				p.add(new Paragraph(rs.getString(3),catFont));
				p.add(new Paragraph(rs.getString(1),catFont));
				p.add(new Paragraph(rs.getString(2),catFont));
				p.add(new Paragraph(rs.getString(4),catFont));
				}
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				rs=stmt.executeQuery("SELECT COMPANY_NAME FROM "+m_schema_name+".FA_CO_MAS_COMPANY_DETAILS   ");
				if(rs.next())
				{
				company_name=rs.getString(1);
				
				}	
				
				rs=stmt.executeQuery("SELECT "+m_schema_name+".AF_CO_GET_CHEQUE_NARRA_DESC(RETURN_COMMENTS) FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS WHERE RECEIPT_NO='"+m_payement_no+"'");
				if(rs.next())
				{
				return_reason=rs.getString(1);
				
				}
				
				
				rs=stmt.executeQuery(" SELECT A.NAME,"+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(B.DESIGNATION_CODE) FROM "+m_schema_name+".CO_CO_MAS_USER A,"+
				" "+m_schema_name+".CO_CO_MAS_EMPLOYEE_DET B "+
				" WHERE "+
				//" UPPER(A.EMP_ID) = UPPER('"+authorized_user+"') "+
				" A.EMP_ID = B.EMP_CODE "+	
				" AND B.COMPANY_CODE='"+company_name_2+"'"+	
				" ");
				String designation="";				
				String emp_name="";
				
				if(rs.next())
				{
				emp_name=rs.getString(1);				
				designation=rs.getString(2);				
				
				}	
				p=new Paragraph();
				p.add(new Paragraph("Dear Sir,",catFont));
				p.add(new Paragraph(""));	
				p.add(new Paragraph("CHEQUE RETURNS",BoldFont));
				p.add(new Paragraph("",catFont));
				p.add(new Paragraph(
				"Take notice that the cheque bearing No:"+cheque_number+" dated "+banking_date+" drawn"+
				" on "+bank_name+","+branch_name+" has been returned to us("+company_name+")dishonoured for payment with the following endorsement.",catFont));
				p.add(new Paragraph(""));	
				p.add(new Paragraph(m_sn_methods.Add_Space(25)+"\""+return_reason+"\"",catFont));
				p.add(new Paragraph("This is to demand from you the immediate payment of a sum of Rs."+nf.format(recept_amount_double)+"(Rupees "+m_sn_methods.numbersToChar(recept_amount)+") being the amount due upone aforesaid cheque.Your failure to pay this amount to our Company immediately upone the receipt hereof will result in our "
				+"filing legal action against you for the recovery of the said sum together"+
				"with,the interest and cost of action.",catFont));
				
				p.add(new Paragraph(""));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				//dishonour_letter.append("\r\n\r\n");
				p=new Paragraph();
				p.add(new Paragraph("For your reference,we also would like to point out to you the Debt Recovery"+
				"Law which Provides that dishonouring of a cheque is an offense punishable,after"+
				"a summary trial section 25 of the Debt Recovery Special Provision Act No:02 of "+
				"1990 provides as follows:",catFont));
				p.add(new Paragraph(""));
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				//dishonour_letter.append("\r\n\r\n");
				//dishonour_letter.append("of the Debt Recovery Special Provision Act No:02 of 1990 provides as follows:\r\n\r\n");
				p=new Paragraph();	
				p.add(new Paragraph("(1) Any person who,",catFont));
				p.add(new Paragraph(""));		
				p.setIndentationLeft(40);
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				p=new Paragraph();
				p.add(new Paragraph("(a)Draws a cheque knowing that there is no funds or ,not sufficient"+
				"funds in the bank to honour such cheque;shall be guilty of and "+
				"offence under this Act and shall on conviction by a Magistrate "+
				"after a summary trail be liable to punishment with imprisonment"+
				"of either description for a term which may extend to one year "+
				"or with a fine of ten thousand ruppees or ten per centem of the"+
				"full value of the cheque,order, authority or inaland bill in "+
				"respect of which the offense is commited,which ever is higher"+
				"or with both such fine and imprisonment.",catFont));
				p.add(new Paragraph(""));
				p.setIndentationLeft(50);
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(p);
				
				//p.setIndentationLeft(0);
				p=new Paragraph();
				p.add(new Paragraph("yours faithfully",catFont));
				p.add(new Paragraph(company_name,catFont));
				p.add(new Paragraph(emp_name,catFont));
				p.add(new Paragraph(designation,catFont));
				p.add(new Paragraph(""));
				
				
				//addedd 2011-11-30 adding debtors/clients cc
				
				rs=stmt.executeQuery("SELECT  COUNT(*) "+
				"	FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT   "+
				"   WHERE GROUP_RECEIPT_NO='"+m_payement_no+"'");
				int subReceptCnt=0;
				if(rs.next())
				{
				subReceptCnt=rs.getInt(1);
				}	
				int j=0;
				
				String query="SELECT  DECODE(A.REC_SETT_TYPE,'DP',A.CLIENT_CODE,'CP',A.DEBTOR_CODE) PAID_TOWHOM_BY_PAYEE, "+ //DEBTOR IS PAYYEE, CLIENT IS RECEIVED IN A DEBTOR PAYMENT,IN A CLIENT PAYMENT CLIENT IS PAYED ,DEBTOR IS RECEIVED
				"   NVL(B.REGISTERED_ADDRESS1,'-'),NVL(B.REGISTERED_ADDRESS2,'-'),"+//2,3
				"   NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(A.REC_SETT_TYPE,'DP',A.CLIENT_CODE,'CP',A.DEBTOR_CODE)),'-'),NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-') "+ //4,5
				"	FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".FA_CO_MAS_CLIENT B   "+
				"   WHERE ";
				if(subReceptCnt>0)
				{	
				query=query+"   A.GROUP_RECEIPT_NO='"+m_payement_no+"'";
				}else
				{
				query=query+"   A.RECEIPT_NO='"+m_payement_no+"'";
				}	
				query=query+"   AND DECODE(A.REC_SETT_TYPE,'DP',A.CLIENT_CODE,'CP',A.DEBTOR_CODE)=B.CLIENT_CODE ";
				rs=stmt.executeQuery(query);
				while(rs.next())
				{
				if(j==0)
				{	
					p.add(new Paragraph("Cc:\r\n",catFont));
				}
				
				p.add(new Paragraph(rs.getString(4),catFont));
				p.add(new Paragraph(rs.getString(2),catFont));
				p.add(new Paragraph(rs.getString(3),catFont));
				p.add(new Paragraph(rs.getString(5),catFont));
				
				//dishonour_letter.append("");
				j++;
				}	
				
				
				//addedd 2011-11-30 adding debtors/clients cc
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				//p.setIndentationLeft(50);
				document.add(p);
				document.close();
				
				*/
		}
		catch(Exception e) 
		{
			System.out.println("half letter"+e.toString());
			e.printStackTrace();
			
		}
		
	}	
	
	
	public boolean generate_letter_Pdf_version(String m_payement_no,String Letter_category,String Lease_type,String Client_code)
	{
		boolean sucess_generated=true;
		try 
		{
			
			
			conn.setAutoCommit(false);	
			m_fschema_name = m_sn_methods.client_name.trim();
			m_schema_name = m_sn_methods.schema_name.trim();
			company_name_2=m_sn_methods.company_name;		
			m_client_name = m_sn_methods.client_name.trim();
			m_username = m_sn_methods.username;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);	
			
			String sysdate=""; 
			String m_date="";
			m_msg = "'Dishonour Letter generated for the Receipt ";
			
			
			
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			
			String basic_upload_path="D:\\SasiaNet_Products\\NetAsset\\OFSCL\\UPLOAD";
			String file_path="";
			BufferedWriter file_writer_obj=null;	
			
			/*ADD BY MADHAWA 2010-11-12*/
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");	
			if(rs.next())
			{
				sysdate=rs.getString(1);
			}			
			
			
			String file_name=m_payement_no+"_"+sysdate+".pdf"; //generate PDF
			file_path=basic_upload_path;
			file_path=file_path+"\\LAKDL_LETTERES\\"+Letter_category+"\\"+sysdate+"\\"+file_name;
			File m_new_dir = new File(basic_upload_path +"\\LAKDL_LETTERES\\"+Letter_category+"\\"+sysdate+"\\");
			m_new_dir.mkdirs();	
			
			//added by madhawa 2011-12-02
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file_path));
			document.open();
			generate_letter_Pdfbody(document,m_payement_no,Lease_type,Client_code);
			document.close();
			//addMetaData(document);
			//addTitlePage(document);
			//addContent(document);
			//document.close();
			
			
			
			conn.commit();
			
			
		}
		catch (Exception ex) 
		{
			///try{conn.rollback();}catch(Exception e){} avoid bein rolled back if an error exists with the generation of the text files for the returns
			sucess_generated=false;//failed generating the files
			
			
		}
		finally
		{
			try
			{
				conn.setAutoCommit(true);
			}catch(Exception e)
			{
			}
			if(conn!=null)
			{
				try
				{
					//conn.close(); avoid the connection being closed
				}
				catch(Exception e){
				}
			}
			//if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
		return sucess_generated;
	}
	
	
}

