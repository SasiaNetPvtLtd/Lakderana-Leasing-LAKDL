//Created by MILINDA 2014-04-30 FOR SAVE PRINTERS
import java.io.*;
import java.awt.print.*;
import java.io.PrintStream;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class LAKDL_Printer_find {
	
	protected synchronized PrintService[] get_server_printers(){
		
		java.awt.print.PrinterJob printJob = java.awt.print.PrinterJob.getPrinterJob();
		javax.print.PrintService[] m_print_services = printJob.lookupPrintServices();
		
		return m_print_services;
		
	}
}
