//bulk printing option by inditha 6-2-2007

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder; 
import java.lang.*;
import java.sql.*;
import java.net.*;


public class Bulk_Printing_Main {
	
	//class specific variables
	public int SUCESS_PRINT=1;
	public int FAIL_PRINT=2;
	public int ERROR_PRINTER=3;
	public int OTHER_ERROR=99;
	//-------------------------
	//1 inch=72
	//operation specific interfaces for printing
	public synchronized int printing_interface_1(String[] m_payment_no,String m_printr_name){
		
		//---------------------------------------------
		java.awt.print.Book m_book = new java.awt.print.Book();
		//System.out.println("----------");
		//page and paper settings
		java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
		java.awt.print.Paper m_pa=new java.awt.print.Paper();
		/*  m_pa.setSize(504,252);
		m_pa.setImageableArea(0,0,504,252);*/
		int m_page=m_payment_no.length;	
		
		m_pa.setSize(540,252*m_page);//(684,252)
		//m_pa.setImageableArea(0,0,540,252*m_page);
		m_pa.setImageableArea(0,0,540,252*m_page);
		m_pf.setPaper(m_pa);
		m_pf.setOrientation(m_pf.PORTRAIT);
		//-------------------------------
		//implement the record specific printable book
		m_book.append (new Print_cheques_book_bulk(m_payment_no),m_pf);
		//---------------------------------------------------
		
		//int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\ASANKA\\LEXMARK 2391 PLUS");
		int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLEMAH01\\FIN_CP1");
		System.out.println("Printing OK:"+m_print_status); //192.168.100.133  HP LASERJET 1022
		
		
		return m_print_status;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------
	//pls dont make nay chages 
	//main interface for handele the printing operation
	public synchronized int Bulk_Printing_Main_Interface(java.awt.print.Book m_printerable_book,String m_printer_name) {
		try{
			
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
			System.out.println(m_printer_name);
			//select printer and perform the printing
			// pout.write(("m_print_services.length="+m_print_services.length+"\r\n").getBytes());
			
			for(int i=0;i<m_print_services.length;i++){
				
				if(m_print_services[i].getName().toUpperCase().equals(m_printer_name.toUpperCase())){
					javax.print.DocPrintJob m_job=m_print_services[i].createPrintJob();
					m_job.print(m_printable_doc,m_printable_atttib_1);
					m_count++;
					break;
				}
			}
			
			//clear the garbage
			System.gc();
			if(m_count>0){
				return SUCESS_PRINT;
			}else{
				return ERROR_PRINTER;
			}	
		}
		catch(Exception e){
			System.out.println("Printing Error:"+e.toString());
			return OTHER_ERROR;
			
			
			
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------------
}
