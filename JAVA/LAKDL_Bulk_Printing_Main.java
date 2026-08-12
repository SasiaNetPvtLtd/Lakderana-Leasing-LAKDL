//bulk printing option by inditha 6-2-2007

public class LAKDL_Bulk_Printing_Main{
	
	//class specific variables
	public int SUCESS_PRINT=1;
	public int FAIL_PRINT=2;
	public int ERROR_PRINTER=3;
	public int OTHER_ERROR=99;
	//-------------------------
	//1 inch=72
	//operation specific interfaces for printing
	public synchronized int printing_interface_chq(String[] m_payment_no,String m_printer_name){
		
		//---------------------------------------------
		java.awt.print.Book m_book = new java.awt.print.Book();
		
		//page and paper settings
		java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
		java.awt.print.Paper m_pa=new java.awt.print.Paper();
		int m_page=m_payment_no.length;
		//int m_page=1;
		
		//m_pa.setSize(252*m_page,700);//(684,252) //504+180(couter foil)  =684 -->700
		//m_pa.setImageableArea(0,0,252*m_page,700);
		
		m_pa.setSize(700,252);//(684,252) //504+180(couter foil)  =684 -->700
		m_pa.setImageableArea(0,0,700,252);
		
		//	m_pa.setSize(700,216);//(684,252) //504+180(couter foil)  =684 -->700
		//	m_pa.setImageableArea(0,0,700,216);
		
		m_pf.setPaper(m_pa);
		m_pf.setOrientation(m_pf.PORTRAIT);//PORTRAIT\\LANDSCAPE
		//-------------------------------
		//implement the record specific printable book
		int m_print_status=0;
		for(int z=0;z<m_payment_no.length;z++){
			//	for(int z=0;z<5;z++){
			System.out.println("NEWLENGTHDIS");
			m_book.append (new LAKDL_Print_letter_book(m_payment_no[z]),m_pf);
			
			
		}
		//---------------------------------------------------
		//int m_print_status=MULTIAC_Bulk_Printing_Main_Interface(m_book,"EPSON LQ Series");
		// m_print_status=MULTIAC_Bulk_Printing_Main_Interface(m_book,"\\\\PRINTER\\HP LASERJ");//COMMENTED BY MILINDA 2014-07-16
		//int m_print_status=MULTIAC_Bulk_Printing_Main_Interface(m_book,"EPSON LQ-2090 ESC/P 2 Ver 2.0"); 
		System.out.println("printer pass: "+m_printer_name);
		m_print_status = MULTIAC_Bulk_Printing_Main_Interface(m_book,m_printer_name);//added milinda
		
		
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