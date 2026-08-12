//bulk printing option by inditha 6-2-2007





public class Bulk_Printing_Main {
	
	//class specific variables
	public int SUCESS_PRINT=1;
	public int FAIL_PRINT=2;
	public int ERROR_PRINTER=3;
	public int OTHER_ERROR=99;
	//-------------------------
	//1 inch=72
	//operation specific interfaces for printing
	public synchronized int printing_interface_8(String[] m_payment_no,String m_printr_name,String m_acc_payee_status){
		
		
		
		//---------------------------------------------
		java.awt.print.Book m_book = new java.awt.print.Book();
		
		//page and paper settings
		java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
		java.awt.print.Paper m_pa=new java.awt.print.Paper();
		
		int m_page=m_payment_no.length;	
		
		
		m_pa.setSize(252*m_page,540);//(684,252)
		m_pa.setImageableArea(0,0,252*m_page,540);
		m_pf.setPaper(m_pa);
		m_pf.setOrientation(m_pf.LANDSCAPE);//PORTRAIT//LANDSCAPE
		//-------------------------------
		//implement the record specific printable book
		//m_book.append (new Print_cheques_book_bulk_leasing(m_payment_no),m_pf);
		int m_print_status=0;
		for(int i=0;i<m_page;i++){
			m_book.append (new LAKDL_Print_cheques_book(m_payment_no[i],m_acc_payee_status),m_pf);
			//m_book.append (new Print_cheques_book_bulk_leasing(m_payment_no[i]),m_pf);
			m_print_status = Bulk_Printing_Main_Interface(m_book,m_printr_name);//added milinda
		}
		System.out.println("status in bulk"+m_acc_payee_status);
		System.out.println("Printing CODE : "+m_print_status);
		return m_print_status;
	}
	
	
	//start commented by milinda
	//operation specific interfaces for printing
	/*public synchronized int printing_interface_1(String[] m_payment_no){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        //  m_pa.setSize(504,252);
       // m_pa.setImageableArea(0,0,504,252);
        int m_page=m_payment_no.length;	
        
        m_pa.setSize(540,252*m_page);//(684,252)
        m_pa.setImageableArea(0,0,540,252*m_page);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);//LANDSCAPE  PORTRAIT
        //-------------------------------
        //implement the record specific printable book
        m_book.append (new Print_cheques_book_bulk(m_payment_no),m_pf);
        //---------------------------------------------------
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC06\\HP LASERJET 1022");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"TPRINT");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    public synchronized int printing_interface_2(String m_group_invno,String m_client,String m_inv_type){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(576,792);
        m_pa.setImageableArea(0,0,576,792);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new Invoice_bulk_print(m_group_invno,m_client,m_inv_type),m_pf);
        //---------------------------------------------------
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"TPRINT");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    //debtor statement print
    public synchronized int printing_interface_3(String m_facility_no,String m_client_code,String m_start_date,String m_end_date,String m_debtor_no){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);
        m_pa.setImageableArea(0,0,650,864);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new Debtor_Statement_bulk_print(m_facility_no,m_client_code,m_start_date,m_end_date,m_debtor_no),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    //current AC statement print
    public synchronized int printing_interface_4(String m_facility_no,String m_client_code,String m_start_date,String m_end_date){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);
        m_pa.setImageableArea(0,0,650,864);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new Current_Account_Statement_bulk_print(m_facility_no,m_client_code,m_start_date,m_end_date),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\PRINTER\\HPLASERJ");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"TPRINT");
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    //PRE-REMINDERS PRINT
    public synchronized int printing_interface_5(String m_facility_no,String m_client_code,String m_debtor_no,String m_date,String m_status){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);
        m_pa.setImageableArea(0,0,650,864);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new Pre_Reminder_bulk_print(m_facility_no,m_client_code,m_debtor_no,m_date,m_status),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    //1ST-REMINDERS PRINT
    public synchronized int printing_interface_6(String m_facility_no,String m_client_code,String m_debtor_no,String m_invoice_seq_no){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);
        m_pa.setImageableArea(0,0,650,864);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new First_Reminder_bulk_print(m_facility_no,m_client_code,m_debtor_no,m_invoice_seq_no),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    //2ND-REMINDERS PRINT
    public synchronized int printing_interface_7(String m_facility_no,String m_client_code,String m_debtor_no,String m_invoice_seq_no){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);
        m_pa.setImageableArea(0,0,650,864);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new Second_Reminder_bulk_print(m_facility_no,m_client_code,m_debtor_no,m_invoice_seq_no),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    
    //Invoice Bulk Print
    public synchronized int printing_interface_9(String m_ginv_no,String m_from_date,String m_to_date){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        //int m_length=864*m_inv_no.length;
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);  //650,190080
        m_pa.setImageableArea(0,0,650,864); //650,190080
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        //m_book.append (new Print_invoce_book_bulk(m_ginv_no),m_pf);
        
        m_book.append (new Print_invoce_book_bulk(m_ginv_no,m_from_date,m_to_date),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\TEST");  //  OFSCL-FAC04\\FAC_BULK
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\INP01");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\PRINTER\\HP LASERJET P2015 SERIES PCL 6");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
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
    
    //ADDED BY NUWAN DE SILVA ON 22-10-2008
    public synchronized int printing_interface_insurance(String m_finance_no,String m_invoice_no){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        //int m_length=864*m_inv_no.length;
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);  //650,190080
        m_pa.setImageableArea(0,0,650,864); //650,190080
        
        //m_pa.setSize(650,792);  //650,190080
        // m_pa.setImageableArea(0,0,650,792); //650,190080
        
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        m_book.append (new Print_Insurance_Book(m_finance_no,m_invoice_no),m_pf);
        
        //---------------------------------------------------
        // int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\INP01"); 
        // int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-ICHA01\\Epson LX-300+"); 
        // int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-SCAL01\\hp LaserJet 1300 PCL 6"); 
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\REP01"); 
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-MBK05\\TOSHIBA e-STUDIO16/20/25 PCL 6"); 
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\Printer\\hp LaserJet 1300 PCL 6");
        
        
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    
    
    
    //Invoice Individual Print
    public synchronized int printing_interface_invoice_individual(String m_ginv_no,String m_from_date,String m_to_date,String m_client_code){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        //int m_length=864*m_inv_no.length;
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);  //650,190080
        m_pa.setImageableArea(0,0,650,864); //650,190080
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        
        
        //-------------------------------
        
        //implement the record specific printable book
        //m_book.append (new Print_invoce_book_bulk(m_ginv_no),m_pf);
        
        m_book.append (new Print_inovice_book_individual(m_ginv_no,m_from_date,m_to_date,m_client_code),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\TEST");  //  OFSCL-FAC04\\FAC_BULK
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\INP01"); 
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCL-FAC04\\FAC_BULK");
        
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    
    
    
    //Receipt Bulk Print
    public synchronized int printing_interface_10(String[] m_payment_no){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);
        m_pa.setImageableArea(0,0,650,864);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new Print_receipt_book_bulk(m_payment_no),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\TEST"); //HP LASERJET P2015 SERIES PCL 6
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\PRINTER\\HP LASERJET P2015 SERIES PCL 6"); //HP LASERJET P2015 SERIES PCL 6	  \\PRINTER\HP LASERJET P2015 SERIES PCL 6
        System.out.println("Printing OK :"+m_print_status);
        return m_print_status;
    }
    
    
    
    //Monthly Statement Bulk Print
    public synchronized int printing_interface_11(String m_group_inv_no, String m_value_date){
        
        //---------------------------------------------
        java.awt.print.Book m_book = new java.awt.print.Book();
        
        //page and paper settings
        java.awt.print.PageFormat m_pf=new java.awt.print.PageFormat();
        java.awt.print.Paper m_pa=new java.awt.print.Paper();
        m_pa.setSize(650,864);
        m_pa.setImageableArea(0,0,650,864);
        m_pf.setPaper(m_pa);
        m_pf.setOrientation(m_pf.PORTRAIT);
        //-------------------------------
        
        //implement the record specific printable book
        m_book.append (new Print_month_statment_book_bulk(m_group_inv_no,m_value_date),m_pf);
        //---------------------------------------------------
        //int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\192.168.100.133\\HP LASERJET 1022");
        int m_print_status=Bulk_Printing_Main_Interface(m_book,"\\\\OFSCLJWAN01\\TEST");
        System.out.println("Printing OK:"+m_print_status);
        return m_print_status;
    }
    
    */
	
	//commented by milinda end
	
	
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
			
			//added by Roshan 18/02/2020  JB08112019-09654
			javax.print.attribute.PrintServiceAttributeSet m_print_service_atttibute = new javax.print.attribute.getAttributes();
			
			//end roshan
			
			if(m_printer_name.equals("") || m_printer_name==null){
				return ERROR_PRINTER;
			}
			
			int m_count=0;
			
			System.out.println("Required Printer b4 : "+m_printer_name);
			m_printer_name = m_printer_name.replace("/","\\");
			System.out.println("Required Printer after: "+m_printer_name);
			//System.out.println(m_printer_name);
			//select printer and perform the printing
			// System.out.println("Printer List");
			for(int i=0;i<m_print_services.length;i++){
				
				// System.out.println(m_print_services[i].getName());
				
				// Hard Coded For Development By Samitha Kulatilaka
				// if(m_print_services[i].getName().toUpperCase().equals("\\\\PRINTER\\HPLASERJ")){
				//if(m_print_services[i].getName().toUpperCase().equals(m_printer_name)){
				if(m_print_services[i].getName().toUpperCase().equals(m_printer_name.toUpperCase())){
					System.out.println("found"+m_printer_name);
					try{
						javax.print.DocPrintJob m_job= m_print_services[i].createPrintJob();
						m_job.print(m_printable_doc,m_printable_atttib_1);
					}catch(Exception ex){
						System.out.println("Test  : "+ex.toString());
					}
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
