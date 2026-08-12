

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_administration_inquiry extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String Geographic_Areas_header="";
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
						
						//-----------------Inquiry Areas User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-23
						out.println("<p class=ug_headin><a NAME='inquiry_header'></a><u>Inquiry </u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>In Inquiry section you need to upload data that should appear in dropdown menus in the Inquiry screen of the Marketing module in Asset Finance. Lead source categories, Customer categories, Initiation types are examples.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-23

						//-----------------Inquiry Stages
						out.println("<p class=ug_headin><a NAME='Inquiry_Stages'></a><u>Inquiry Stages</u> ");
						out.println("<p align='justify' class=rep-body>An inquiry stage is the current status of an inquiry. It should reflect the activity that is pending or the activity that was finally performed. \"Quotation generated\" or \"Pending approval for Quotation\" would be meaningful inquiry stages. ");
						//----------------- Customer Categories Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Customer_Categories'>");
						out.println("</a><u>Customer Categories</u>");
						out.println(" ");
						out.println("<p class=rep-body>You can categorize your customers according to your own way of categorizing them. You can categorize them as a 'Regular customer', 'Suspect', or a 'Defaulter' for example.");
												//----------------- Lead source Categories User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Lead_source_Categories'>");
						out.println("</a><u>Lead source Categories </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A lead source is an entity that directs a lead (a business) to your company. In other words, a lead source is someone who introduces your company as a good service provider to someone who seeks service. A Lead Source Category is a classification of those individual/corporate introducers. Such a category can be an 'Agent', a 'Broker', an 'Employee' or an 'Existing client'. ");
											//----------------- Cities User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Lead_Source_Details'>");
						out.println("</a><u>Lead Source Details</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen permits you include personal and contact details of such lead sources. You will require such details when you want to reward the best performers or similar occasions. ");
						//----------------- Area User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Initiation_Types'>");
						out.println("</a><u>Initiation Types</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Initiation type is the mode you receive an inquiry. Here you can define your own marketing campaigns. Common initiation types are Calls and Walks in.");
						
						
											
						
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
