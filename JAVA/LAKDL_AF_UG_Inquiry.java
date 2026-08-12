

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Inquiry extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
						out.println("<!doctype html public '-//w3c//dtd html 4.0 transitional//en'>");
						out.println("<html>");
						out.println("<head>");
						out.println("   <meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'>");
						out.println("   <meta name='Generator' content='Microsoft FrontPage 5.0'>");
						//out.println("   <meta name='Template' content='C:\PROGRAM FILES\MICROSOFT OFFICE\OFFICE\html.dot'>");
						out.println("   <meta name='GENERATOR' content='Mozilla/4.7 [en] (Win98; I) [Netscape]'>");
						out.println("   <title>MIDDLE OFFICE</title>");
						out.println("</head>");
						out.println("<body text='#000000' bgcolor='#FFFEEC' link='#0000FF' vlink='#800080' alink='#FF0000'>");
						out.println("<font face='Verdana' style='font-size: 13pt'><b><u>VERIFICATION</u></b> </font>");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>This section is basically for control");
						out.println("purposes and selected middle level officers can be given the authority ");
						out.println("to access it and save information. </font> ");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>The following three menu options </font>");
						out.println("<ol>");
						out.println("<ol>");
						out.println("<li>");
						out.println("<font face='Verdana' style='font-size: 11pt'>Deal Verification</font></li>");
						out.println("<li>");
						out.println("<font face='Verdana' style='font-size: 11pt'>Deal Approval</font></li>");
						
						out.println("</ol>");
						out.println("</ol>");
						out.println("<font face='Verdana' style='font-size: 11pt'>Have to be used in order to confirm");
						out.println("a deal. </font>");
						out.println("<p><font face='Verdana' style='font-size: 13pt'><a NAME='Inquiry'></a></font><b><font face='Verdana'>");
						out.println("<span style='font-size: 13pt'>Inquiry Help</span></font></b><font face='Verdana'><span style='font-size: 13pt'>");
						out.println("</span>");
						out.println("</font>");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>Deals entered in the Front Office");
						out.println("are verified in this section. </font>");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>All the Deals that have been entered");
						out.println("will be displayed in deal verification with their details such as Dealer");
						out.println("No, Dealer Type, Amount and Dealer. Deal details can be obtained on screen");
						out.println("using the <b>Detail</b> option. These details can also be printed if necessary. </font>");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>Once the deal details are verified,");
						out.println("these should be saved using Save option in order to proceed to Deal Approval. A deal can be deleted using the <b>Delete</b> option.");
						out.println("<br>&nbsp; </font>");
						out.println("<p><font face='Verdana'><a NAME='Pricing'><span style='font-size: 13pt'>");
						out.println("</span></a></font><span style='font-size: 13pt'><b><font face='Verdana'>Pricing Help </font></b></span><font face='Verdana'><span style='font-size: 13pt'>");
						out.println("</span> </font>");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>All the Deals that have been verified");
						out.println("will be displayed in Approval with their details such as Dealer ");
						out.println("No, Dealer Type, Amount and Dealer. Deal details can be obtained on screen ");
						out.println("using the <b>Detail</b> option. These details can also be printed if necessary. </font>");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>The deal should be saved using Save ");
						out.println("option in order to proceed to Deal Approval. Also a deal can");
						out.println("be deleted using the <b>Delete</b> option. </font>");
						out.println("<p><font face='Verdana' style='font-size: 11pt'>Deal Approval is similar");
						out.println("to Deal Approval.");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp;");
						out.println("<br>&nbsp; </font>");
						out.println("</body>");
						out.println("</html>");
			
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
