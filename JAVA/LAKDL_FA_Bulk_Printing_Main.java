public class LAKDL_FA_Bulk_Printing_Main {
	
	//class specific variables
	public int SUCESS_PRINT=1;
	public int FAIL_PRINT=2;
	public int ERROR_PRINTER=3;
	public int OTHER_ERROR=99;
	//-------------------------
	//1 inch=72
	//operation specific interfaces for printing
	public synchronized int printing_interface_factoring_chq(String[] m_payment_no){
		
		//---------------------------------------------
		java.awt.print.Book m_book = new java.awt.print.Book();
		
		//page and paper settings
		java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
		java.awt.print.Paper m_pa=new java.awt.print.Paper();
		int m_page=m_payment_no.length;	
		m_pa.setSize(540,252*m_page);//(684,252)
		m_pa.setImageableArea(0,0,540,252*m_page);
		m_pf.setPaper(m_pa);
		m_pf.setOrientation(m_pf.PORTRAIT);//PORTRAIT
		
		//-------------------------------
		//implement the record specific printable book
		m_book.append (new Print_cheques_book_bulk(m_payment_no),m_pf);
		//---------------------------------------------------
		int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FACT002\\HP LaserJet 1022");
		System.out.println("Printing OK:"+m_print_status);
		return m_print_status;
	}
	//operation specific interfaces for printing overloading here if we select print added milinda 2014-05-02
	public synchronized int printing_interface_1(String m_payment_no,String m_acc_payee_status, String m_printer_name){
		
		//---------------------------------------------
		java.awt.print.Book m_book = new java.awt.print.Book();
		
		//page and paper settings
		java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
		java.awt.print.Paper m_pa=new java.awt.print.Paper();
		int m_page=1;
		System.out.println("m_page="+m_page);
		
		m_pa.setSize(252*m_page,540);//(684,252)
		m_pa.setImageableArea(0,0,252*m_page,540);
		m_pf.setPaper(m_pa);
		m_pf.setOrientation(m_pf.LANDSCAPE);//
		
		//-------------------------------
		
		//implement the record specific printable book
		m_book.append (new Print_cheques_book_bulk(m_payment_no,m_acc_payee_status),m_pf);
		
		int m_print_status=Bulk_Printing_Main_Interface(m_book,m_printer_name); 
		
		System.out.println("Printing :"+m_print_status);
		
		return m_print_status;
	}
	//end mili
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------
	//pls dont make nay chages 
	//main interface for handele the printing operation
	public synchronized int Bulk_Printing_Main_Interface(java.awt.print.Book m_printerable_book,String mm_printer_name) {
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
			
			m_printer_name = m_printer_name.replace("/","\\");
			//System.out.println(m_printer_name);
			//select printer and perform the printing
			for(int i=0;i<m_print_services.length;i++){
				
				
				if(m_print_services[i].getName().toUpperCase().equals(m_printer_name.toUpperCase())){
					System.out.println("found"+m_printer_name);
					javax.print.DocPrintJob m_job=m_print_services[i].createPrintJob();
					m_job.print(m_printable_doc,m_printable_atttib_1);
					m_count++;
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
