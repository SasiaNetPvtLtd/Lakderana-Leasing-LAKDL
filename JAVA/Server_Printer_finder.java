
import java.io.*;
import java.awt.print.*;
import java.io.PrintStream;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class Server_Printer_finder {
	
	public static synchronized PrintService[] get_server_printers(){
		
		java.awt.print.PrinterJob printJob = java.awt.print.PrinterJob.getPrinterJob();
		javax.print.PrintService[] m_print_services = printJob.lookupPrintServices();
				
		return m_print_services;
		
	}
	
	public static void main(String [] args)
	{
		java.awt.print.PrinterJob printJob = java.awt.print.PrinterJob.getPrinterJob();
		javax.print.PrintService[] m_disp_printers = printJob.lookupPrintServices();
		
		m_disp_printers = get_server_printers();
		
		for(int x=0;x<m_disp_printers.length;x++){
			System.out.println("Printer ("+x+") => "+m_disp_printers[x]);
		}
	}
	
		
}
