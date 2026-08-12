import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.*;
import com.lowagie.text.pdf.*;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import com.lowagie.text.*;
/**
 *
 * @Disnaka Jayasuriya
 * @Date - 2009-12-24
 */
public class PDFConversion
{
 /**
 * This method is used to convert the given file to a PDF format
 * @param inputFile - Name and the path of the file
 * @param outputFile - Name and the path where the PDF file to be saved
 */
 public void createPdf(String inputFile, String outputFile)
 {
     /**
     * Create a new instance for Document class
     */
     Document pdfDocument = new Document(PageSize.A4, 50.0f, 50.0f, 50.0f, 50.f);
     //Document pdfDocument = new Document(PageSize.A4.rotate());
     String pdfFilePath = outputFile;
       try
       {
         
         FileOutputStream fileOutputStream = new FileOutputStream(pdfFilePath);
         PdfWriter writer = null;
         writer = PdfWriter.getInstance(pdfDocument, fileOutputStream);
         writer.open();
         pdfDocument.open();
         pdfDocument.addAuthor("SasiaNet Pvt Ltd.");
         pdfDocument.addCreator("OFSCL");
         pdfDocument.addSubject("Reports");
         pdfDocument.addTitle("Client Reports"); 
         
         //BaseFont helvetica = BaseFont.createFont("Helvetica", BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
         //Font font = new Font(helvetica, 10, Font.NORMAL);
         //Font font =new Font(Font.COURIER);
         Font font = new Font(Font.COURIER, 10, Font.NORMAL);
         Paragraph paragraph = new Paragraph();
         
         BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                inputFile),
                                "UTF-8"));
          String line = null;
          while ((line = reader.readLine()) != null) {
               //System.out.println(line);
               pdfDocument.add(new Paragraph(line,font));
         }
         reader.close();
         pdfDocument.close();
         writer.close();
     }
     catch (Exception exception)
     {
      System.out.println("Document Exception!" + exception);
     }
   }


   public void createPdfL(String inputFile, String outputFile)
   {
     /**
     * Create a new instance for Document class
     */
       //Document pdfDocument = new Document(PageSize.A4, 50, 50, 50, 50);
     Document pdfDocument = new Document(PageSize.A4.rotate(),50.0f, 50.0f, 50.0f, 50.0f);
     String pdfFilePath = outputFile;
       try
       {
         
         FileOutputStream fileOutputStream = new FileOutputStream(pdfFilePath);
         PdfWriter writer = null;
         writer = PdfWriter.getInstance(pdfDocument, fileOutputStream);
         writer.open();
         pdfDocument.open();
         pdfDocument.addAuthor("SasiaNet Pvt Ltd.");
         pdfDocument.addCreator("NFACTOR");
         pdfDocument.addSubject("Reports");
         pdfDocument.addTitle("Daily Reports"); 
         //BaseFont helvetica = BaseFont.createFont("Helvetica", BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
         //Font font = new Font(helvetica, 10, Font.NORMAL);
         //Font font =new Font(Font.COURIER);
         Font font = new Font(Font.COURIER, 10, Font.NORMAL);
         Paragraph paragraph = new Paragraph();
         
         BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                inputFile),
                                "UTF-8"));
          String line = null;
          while ((line = reader.readLine()) != null) {
           
               //System.out.println(line);
               pdfDocument.add(new Paragraph(line,font));
         }
         reader.close();
         pdfDocument.close();
         writer.close();
     }
     catch (Exception exception)
     {
      System.out.println("Document Exception!" + exception);
     }
   }
   
   public void createPdfLB(String inputFile, String outputFile)
   {
     /**
     * Create a new instance for Document class
     */
       //Document pdfDocument = new Document(PageSize.A4, 50, 50, 50, 50);
     Document pdfDocument = new Document(PageSize.A3.rotate(),50.0f, 50.0f, 50.0f, 50.0f);
     String pdfFilePath = outputFile;
       try
       {
         
         FileOutputStream fileOutputStream = new FileOutputStream(pdfFilePath);
         PdfWriter writer = null;
         writer = PdfWriter.getInstance(pdfDocument, fileOutputStream);
         writer.open();
         pdfDocument.open();
         pdfDocument.addAuthor("SasiaNet Pvt Ltd.");
         pdfDocument.addCreator("NFACTOR");
         pdfDocument.addSubject("Reports");
         pdfDocument.addTitle("Daily Reports"); 
         //BaseFont helvetica = BaseFont.createFont("Helvetica", BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
         //Font font = new Font(helvetica, 10, Font.NORMAL);
         //Font font =new Font(Font.COURIER);
         Font font = new Font(Font.COURIER, 10, Font.NORMAL);
         Paragraph paragraph = new Paragraph();
         
         BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                inputFile),
                                "UTF-8"));
          String line = null;
          while ((line = reader.readLine()) != null) {
           
               //System.out.println(line);
               pdfDocument.add(new Paragraph(line,font));
         }
         reader.close();
         pdfDocument.close();
         writer.close();
     }
     catch (Exception exception)
     {
      System.out.println("Document Exception!" + exception);
     }
   }
   //New methods
   /*public void createPdf(String inputFile, String outputFile,String page,String ori)
   {
     String pageFormat="";
     String page_size="";
      
     page_size=page.trim().toUpperCase();
     
     if (page_size.equals(A0) || page_size.equals(A1) || page_size.equals(A2) || page_size.equals(A3) || page_size.equals(A4) || page_size.equals(A5) || page_size.equals(A6) || page_size.equals(A7) || page_size.equals(A8) || page_size.equals(A9) || page_size.equals(A10) )
         pageFormat="PageSize."+page;
      else if (page_size.equals(A0) || page_size.equals(A1) || page_size.equals(A2) || page_size.equals(A3) || page_size.equals(A4) || page_size.equals(A5) || page_size.equals(A6) || page_size.equals(A7) || page_size.equals(A8) || page_size.equals(A9) || page_size.equals(A10) )
     //if (P.equals(trim(ori))|| p.equals(trim(ori)))
         
     
     pageFormat="PageSize."+page;
     Document pdfDocument = new Document(PageSize.A4.rotate(),50.0f, 50.0f, 50.0f, 50.0f);
     String pdfFilePath = outputFile;
       try
       {
         
         FileOutputStream fileOutputStream = new FileOutputStream(pdfFilePath);
         PdfWriter writer = null;
         writer = PdfWriter.getInstance(pdfDocument, fileOutputStream);
         writer.open();
         pdfDocument.open();
         pdfDocument.addAuthor("SasiaNet Pvt Ltd.");
         pdfDocument.addCreator("NFACTOR");
         pdfDocument.addSubject("Reports");
         pdfDocument.addTitle("Daily Reports"); 
         
         Font font = new Font(Font.COURIER, 10, Font.NORMAL);
         Paragraph paragraph = new Paragraph();
         
         BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                inputFile),
                                "UTF-8"));
          String line = null;
          while ((line = reader.readLine()) != null) {

               pdfDocument.add(new Paragraph(line,font));
         }
         reader.close();
         pdfDocument.close();
         writer.close();
     }
     catch (Exception exception)
     {
      System.out.println("Document Exception!" + exception);
     }
   }
   */
   
   
  /*public static void main(String args[])
   {
      PDFConversion pdfConversion = new PDFConversion();
      pdfConversion.createPdf("D:\\Nfactpdf\\java pdf class\\1st Reminder-176&10.txt","D:\\Nfactpdf\\java pdf class\\1st Reminder-176&10.pdf");
   }*/
}
