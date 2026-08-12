//bulk printing option by inditha 6-2-2007

public class GET_Printing_Name_List {

  //class specific variables
  public int SUCESS_PRINT=1;
  public int FAIL_PRINT=2;
  public int ERROR_PRINTER=3;
  public int OTHER_ERROR=99;
  //-------------------------
public static void main(String a[]){

Bulk_Printing_Main_Interface();

}
  
  //main interface for handele the printing operation
  public static void Bulk_Printing_Main_Interface() {
    try{

      /*javax.print.DocFlavor m_print_format=javax.print.DocFlavor.BYTE_ARRAY.SERVICE_FORMATTED.PAGEABLE;
      javax.print.Doc m_printable_doc=new javax.print.SimpleDoc(m_printerable_book,m_print_format,null);

      javax.print.attribute.PrintRequestAttributeSet m_printable_atttib_1=new javax.print.attribute.HashPrintRequestAttributeSet();
      m_printable_atttib_1.add(new javax.print.attribute.standard.Copies(1));
*/
      java.awt.print.PrinterJob printJob = java.awt.print.PrinterJob.getPrinterJob();
      javax.print.PrintService[] m_print_services=printJob.lookupPrintServices();

      //if(m_printer_name.equals("") || m_printer_name==null){
      //  return ERROR_PRINTER;
      //}

      int m_count=0;

      //select printer and perform the printing
      for(int i=0;i<m_print_services.length;i++){
        System.out.println(m_print_services[i].getName().toUpperCase());//get the printer service name
        //if(m_print_services[i].getName().toUpperCase().equals(m_printer_name)){
        //  javax.print.DocPrintJob m_job=m_print_services[i].createPrintJob();
        //  m_job.print(m_printable_doc,m_printable_atttib_1);
        //  m_count++;
        //  break;
        //}
      }

      //clear the garbage
      System.gc();
      
    }
    catch(Exception e){
      System.out.println("Printing Error:"+e.toString());

    }
  }
  //--------------------------------
}
///\\192.168.100.133\HP LASERJET 1022
