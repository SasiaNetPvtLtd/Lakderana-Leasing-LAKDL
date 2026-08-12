//bulk printing option by inditha 6-2-2007

public class LAKDL_Bulk_Printing_Main_PDC {

  //class specific variables
  public int SUCESS_PRINT=1;
  public int FAIL_PRINT=2;
  public int ERROR_PRINTER=3;
  public int OTHER_ERROR=99;
  //-------------------------
	//1 inch=72
  //operation specific interfaces for printing
	
	public synchronized int printing_interface_pdc(String m_rec_no){

    //---------------------------------------------
     java.awt.print.Book m_book = new java.awt.print.Book();
		 //int m_length=864*m_inv_no.length;
     //page and paper settings
     java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
     java.awt.print.Paper m_pa=new java.awt.print.Paper();
     //m_pa.setSize(650,864);  //650,190080
     //m_pa.setImageableArea(0,0,650,864); //650,190080
			
			m_pa.setSize(650,792);  //650,190080
      m_pa.setImageableArea(0,0,650,792); //650,190080
			
     m_pf.setPaper(m_pa);
     m_pf.setOrientation(m_pf.PORTRAIT);
     //-------------------------------
			m_book.append (new Print_PDC_Book(m_rec_no),m_pf);
			//m_book.append (new Print_invoce_book_bulk("IN071121-128433","20-04-2007","30-04-2007"),m_pf);
     //---------------------------------------------------
	    // int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\INP01"); 
		  // int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-ICHA01\\Epson LX-300+"); 
			// int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-SCAL01\\hp LaserJet 1300 PCL 6"); 
			 //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
			 int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\REP01");  
			 
				
				
			
		
     System.out.println("Printing OK:"+m_print_status);
     return m_print_status;
  }
	

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
