//bulk first letter printing option by Amila 21-08-2007

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

public class LAKDL_Print_Letters_New_Bulk_print_main{
	
	Statement stmt,stmt1,stmt2,stmt3;
	
	public ResultSet rs;
	public ResultSet rs1,rs2,rs3;
	
	//class specific variables
	public int SUCESS_PRINT=1;
	public int FAIL_PRINT=2;
	public int ERROR_PRINTER=3;
	public int OTHER_ERROR=99;
	//-------------------------
	//1 inch=72
	//operation specific interfaces for printing
	//public synchronized int printing_interface_chq(String[] m_fin_no,String[] m_print_type,String m_printer_name, String m_letter_category){ // commented by udara 05-11-2019
	public synchronized int printing_interface_chq(String[] m_fin_no,String[] m_print_type, String[] m_statement_date, String m_printer_name, String m_letter_category){ // added by udara 05-11-2019
	//  public synchronized int printing_interface_chq(String[] m_fin_no,String m_printer_name){
		
		
		
			// ===================================================================================================================================================
			java.awt.print.PrinterJob printJobChk = java.awt.print.PrinterJob.getPrinterJob();
			javax.print.PrintService[] m_disp_printers = printJobChk.lookupPrintServices();
			
			for(int x=0;x<m_disp_printers.length;x++){
				System.out.println(" ======================================================================================= ");
				System.out.println("Printer US and AS Test ("+x+") => " + m_disp_printers[x].getName().toUpperCase());
			   	System.out.println(" ======================================================================================= ");
			}
			// ===================================================================================================================================================
			
		
		//---------------------------------------------
			java.awt.print.Book m_book = new java.awt.print.Book();
			java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
			java.awt.print.Paper m_pa=new java.awt.print.Paper();
			
			int pageHeight = 790;//864
			int pageWidth = 612;
			
			m_pa.setSize(pageWidth,pageHeight);  
			m_pa.setImageableArea(0,0,pageWidth,pageHeight);
			m_pf.setPaper(m_pa);
			m_pf.setOrientation(m_pf.PORTRAIT);
			
			String m_schema_name = "";
			Connection conn;
			
			try{
			
		          LAKDL_print_methods m_print_method=new LAKDL_print_methods();
			      m_schema_name = m_print_method.schema_name.trim();		
			
			      conn=m_print_method.get_print_connection();
			      stmt=conn.createStatement();
				  stmt1=conn.createStatement();
				  stmt2=conn.createStatement();	
			
		    }
			catch(Exception ex){
			     System.out.println(" Error from connection creation "+ex.toString());
		    }
		
		  	int m_print_status=0;
				
			System.out.println("m_fin_no.length " + m_fin_no.length);
		
			for(int z=0;z<m_fin_no.length;z++){
			//	for(int z=0;z<5;z++){
			System.out.println("NEWLENGTHDIS");
            System.out.println("m_fin_no*************** ="+m_fin_no[z]);
			System.out.println("m_print_type*************** ="+m_print_type[z]);
			System.out.println("m_print_type*************** ="+m_statement_date[z]); 
			
		/*	for(int c=0;c<m_print_type.length;c++){
			//	for(int z=0;z<5;z++){
			System.out.println("NEWLENGTHDIS");
            System.out.println("m_print_type*************** ="+m_print_type[c]);
		*/	
			try{
				
					//System.out.println("Client ");	
						
							rs=stmt.executeQuery("SELECT CLIENT_CODE,  -1 GUAR_ID "+
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								" WHERE FINANCE_NO='"+m_fin_no[z]+"'  "+
						        " ORDER BY GUAR_ID ");

					
					while(rs.next()){
						
						if(m_letter_category.equals("1STREIM")){
							//m_book.append (new LAKDL_Print_Letters_New_Bulk_Book(m_fin_no[z],rs.getString(1)),m_pf); // commented by udara 05-11-2019
							m_book.append (new LAKDL_Print_Letters_New_Bulk_Book(m_fin_no[z],rs.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						}
						else if(m_letter_category.equals("FINREIM")){
							//m_book.append (new LAKDL_Print_Letters_New_Bulk_Book_2(m_fin_no[z],rs.getString(1)),m_pf); // commented by udara 05-11-2019
							m_book.append (new LAKDL_Print_Letters_New_Bulk_Book_2(m_fin_no[z],rs.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						}
						else if(m_letter_category.equals("LETTERM")){
							//m_book.append (new LAKDL_Print_Letters_New_Bulk_Book_3(m_fin_no[z],rs.getString(1)),m_pf); // commented by udara 05-11-2019
							m_book.append (new LAKDL_Print_Letters_New_Bulk_Book_3(m_fin_no[z],rs.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						}
						else if(m_letter_category.equals("NOTTERM")){
							//m_book.append (new LAKDL_Print_Letters_New_Bulk_Book_4(m_fin_no[z],rs.getString(1)),m_pf); // commented by udara 05-11-2019
							m_book.append (new LAKDL_Print_Letters_New_Bulk_Book_4(m_fin_no[z],rs.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						}
						
						//m_book.append (new LAKDL_Print_Letters_New_Bulk_Book(m_fin_no[z],rs.getString(1)),m_pf);
						 // GUARANTOR BULK PRINT GO TO  
							System.out.println("GUARANTOR BULK PRINT GO TO  ");
							
							//rs2=stmt2.executeQuery(" SELECT GUARANTOR_CODE, GUAR_ID "+
							 String Guarantor_Data="SELECT GUARANTOR_CODE, GUAR_ID "+	
								" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B  "+
								" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
								" AND B.FINANCE_NO='"+m_fin_no[z]+"'  "+
								" AND A.ACTIVE_STATUS='Y' "+
								" ORDER BY GUAR_ID "+
								" ";
							  rs2=stmt2.executeQuery(Guarantor_Data);
					          boolean more =rs2.next();	
							
							//while(rs2.next()){
							  while(more){
								
								if(m_letter_category.equals("1STREIM")){
							         //m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book(m_fin_no[z],rs2.getString(1)),m_pf); // commented by udara 05-11-2019
										m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book(m_fin_no[z],rs2.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						         }
								 else if(m_letter_category.equals("FINREIM")){
							         //m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book_2(m_fin_no[z],rs2.getString(1)),m_pf); // commented by udara 05-11-2019
										m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book_2(m_fin_no[z],rs2.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						         }
								 else if(m_letter_category.equals("LETTERM")){
							         //m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book_3(m_fin_no[z],rs2.getString(1)),m_pf); // commented by udara 05-11-2019
										m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book_3(m_fin_no[z],rs2.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						         }
						         else if(m_letter_category.equals("NOTTERM")){
							         //m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book_4(m_fin_no[z],rs2.getString(1)),m_pf); // commented by udara 05-11-2019
										m_book.append (new LAKDL_Print_Letters_Guarantor_Bulk_Book_4(m_fin_no[z],rs2.getString(1),m_statement_date[z]),m_pf); // added by udara 05-11-2019
						         }	
								 more =rs2.next();
								
							}
					 		
						
						
						
					}
			
			}
			catch(Exception ex){
			     System.out.println(" Error from record set "+ex.toString());
		    }
			
			//m_book.append (new LAKDL_Print_Letters_New_Bulk_Book(m_fin_no[z]),m_pf);
			
			
		}
		//---------------------------------------------------

		System.out.println("printer pass: "+m_printer_name);
		//m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\PRINTER\\HPLASERJ");
		//m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"HP LASERJET PROFESSIONAL P1102");
		// m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\PRINTER\\HP LASERJET_NEW");
	//	m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\147.120.100.174\\EPSON ESC/P");
	
		//m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"EPSON LQ-310 ESCP2"); // LAKDL DEVEPOMENT PRINT // commented by udara 25-04-2019
		m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,m_printer_name); // added by udara 25-04-2019
		
		
	//	m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\manoj-pc\\OKI ML5790");  // LAKDL LATEST LIVE WORK 
	//    m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\malitha-pc\\TestPrinter");  // MALITHA LATEST LIVE WORK 
	//	m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\172.20.20.52\\TestPrinter");  // MALITHA LATEST LIVE WORK 
	//    m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\172.20.20.208\\TestPrinter");  // MALITHA LATEST LIVE WORK 
		
		
		System.out.println("Printing OK:"+m_print_status);
		return m_print_status;
	}
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------
	//pls dont make nay chages 
	//main interface for handele the printing operation
	public synchronized int MULTIAC_Bulk_Printing_Main_Interface(java.awt.print.Book m_printerable_book,String mm_printer_name) {
		try{
			
			String m_printer_name=mm_printer_name;
			//m_printer_name="TPRINT";
			
			javax.print.DocFlavor m_print_format=javax.print.DocFlavor.BYTE_ARRAY.SERVICE_FORMATTED.PAGEABLE;
			javax.print.Doc m_printable_doc=new javax.print.SimpleDoc(m_printerable_book,m_print_format,null);
			
			javax.print.attribute.PrintRequestAttributeSet m_printable_atttib_1=new javax.print.attribute.HashPrintRequestAttributeSet();
			m_printable_atttib_1.add(new javax.print.attribute.standard.Copies(1));
			
			java.awt.print.PrinterJob printJob = java.awt.print.PrinterJob.getPrinterJob();
			javax.print.PrintService[] m_print_services=printJob.lookupPrintServices();
			
			if(m_printer_name.equals("") || m_printer_name==null){
				return ERROR_PRINTER;
			}
			
			int m_count=0;
			
			System.out.println("Required Printer b4 : "+m_printer_name);
			m_printer_name = m_printer_name.replace("/","\\");//IN LIVE SERVER TRNASFER THIS PART MUST COMMENT
			System.out.println("Required Printer after: "+m_printer_name);
			//System.out.println(m_printer_name);
			//select printer and perform the printing
			for(int i=0;i<m_print_services.length;i++){
				//System.out.println("$$$$$$$$$$$$Print$$$$$$$$$$$$$$$$$");
				System.out.println(m_print_services[i].getName().toUpperCase());//get the printer service name
				//System.out.println(m_print_services[i].getName().toUpperCase());
				//System.out.println(m_printer_name);
				if(m_print_services[i].getName().toUpperCase().equals(m_printer_name.toUpperCase())){
					System.out.println("found Printer - "+m_printer_name);
					//try part tem added 
					try{
						javax.print.DocPrintJob m_job= m_print_services[i].createPrintJob();
						m_job.print(m_printable_doc,m_printable_atttib_1);
					}catch(Exception ex){
						System.out.println("Test  : "+ex.toString());
					}
					//javax.print.DocPrintJob m_job=m_print_services[i].createPrintJob();
					//m_job.print(m_printable_doc,m_printable_atttib_1);
					m_count++;
					System.out.println("m_count ="+m_count);
					break;
				}
			}
			
			//clear the garbage
			System.gc();
			if(m_count>0)
				return SUCESS_PRINT;
			else
				return ERROR_PRINTER;
		}
		catch(Exception e){
			System.out.println("Printing Error:"+e.toString());
			return OTHER_ERROR;
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------------
}