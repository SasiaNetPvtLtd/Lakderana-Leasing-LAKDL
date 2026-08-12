

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_BulkPrinting_new extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String marketing_header="";
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
						out.println("<!doctype html public '-//w3c//dtd html 4.0 transitional//en'>");
						out.println("<html>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
						out.println("<head>");
						out.println("	<STYLE TYPE='text/css'> ");
						out.println("	a:link {color:black; text-decoration:none font-size: 12px; color: #ED0306;}");//
						out.println("	a:hover {color:red; text-decoration:underline font-size: 12px; color: #ED0306;} ");
						out.println("	a:active {color:blue; text-decoration:underline font-size: 12px; color: #ED0306;}");
						out.println(" </STYLE>");
						out.println("   <meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'>");
						out.println("   <meta name='Generator' content='Microsoft FrontPage 5.0'>");
						out.println("   <meta name='GENERATOR' content='Mozilla/4.7 [en] (Win98; I) [Netscape]'>");
						out.println("   <title>MIDDLE OFFICE</title>");
						out.println("</head>");
						out.println("<body text='#000000' bgcolor='#FFFFFF' link='#000000' vlink='#000000' alink='#000000'>");
						
						
						out.println("<p class=ug_headin><a NAME='Bulkprinting_header'></a><u>Bulk Printing </u> ");
						
						marketing_header="<p align='justify' class=rep-body>Bulk printing of documents is separated for your easy access. You  can easily print invoices, monthly statements, post-dated cheques and insurance documents in bulk with the use of this Bulk Printing module.";
						out.println(marketing_header);
						

						
						out.println("<p class=ug_headin><a NAME='Bulk_Printing_Invoices'></a><u>Bulk Printing - Invoices</u> ");
						out.println("<p align='justify' class=rep-body>Within a specified date range, the system runs the invoices and prints them. Before you click Pending or All buttons, reconsider if you require printing all the invoices between the specified dates.");
										
						out.println("<p class=ug_headin><a NAME='Bulk_Printing_Monthly_Statements'>");
						out.println("</a><u>Bulk Printing - Monthly Statements </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Monthly statements with regard to a specific client can be printed after specifying the date range of which monthly statements are available. Before you click the Print button you are warned to reconsider the relevance of those printouts as the amount of resource requirement (such as paper and ink) is high.");
											
						
						out.println("<p class=ug_headin><a NAME='Bulk_Printing_PDC'>");
						out.println("</a><u>Bulk Printing - PDC </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Post-dated cheques during a given period of time can be printed as a bulk with this screen. Be aware that the number of printouts will be high and you may not be able to cancel printing once started.");

						
						out.println("<p align='justify' class=ug_headin><a NAME='Bulk_Printing_Insurance '>");
						out.println("</a><u>Bulk Printing - Insurance  </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Insurance documents can also be printed in bulk with the use of this screen. You have to specify the date range between which you require to print insurance documents before you print. Be aware that there can be a large number of documents within the specified period and you may not be able to cancel printing after it starts.");
				
						
						
						
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
