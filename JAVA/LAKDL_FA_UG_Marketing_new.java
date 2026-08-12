

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_FA_UG_Marketing_new extends javax.servlet.http.HttpServlet { 

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
						//-------------Marketing
						out.println("<p class=ug_headin><a NAME='Marketing'></a><u>Marketing </u> ");
						out.println("<p align='justify' class=rep-body>Marketing module of the system contains all activities that are performed by your front office and marketing teams. When a prospective client requests details about your services, you can feed details about the inquiry and the client into the system and generate a quotation. To help you provide an efficient service to the clients the system is designed in such a way you can generate a quotation quickly, with minimum inputs. ");

						//-----------------Inquiry  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Inquiry'></a><u>Inquiry</u> ");
						out.println("<p align='justify' class=rep-body>This screen facilitates entering inquiry related details that your front office and marketing teams receive, which are used to generate quotations. This screen lets quick data entry and quick processing to satisfy your customer.");
						
						//-----------------Client Information Collection  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Client_Information_Collection'></a><u>Client Information Collection</u> ");
						out.println("<p align='justify' class=rep-body>This screen is to fill in data related to the clients and debtors. You may enter general information, contact details, ownership details of the organization, guarantor details, contact details, etc., however, you are allowed to skip most of the fields initially, as you do not need all of them to generate a quotation. ");

						//----------------- Initial_Credit_Verification User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Initial_Credit_Verification'>");
						out.println("</a><u>Initial Credit Verification </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A senior user needs to give initial approval for a client in order to generate a quotation. This screen shows you the list of all pending verifications so that you can verify one or more clients at a time.");
						
						//----------------- Quotation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Quotation'>");
						out.println("</a><u>Quotation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A quotation is the marketing document that you will generate, to attract a prospective client who is looking for factoring service. You can prepare a quotation with the use of previously entered data related to the client and inquiry. By selecting a product package according to the customer's requirement and an applicable fee package, you can prepare a comprehensive quotation.");
						
						//----------------- Quotation Approval ----------------------------------------------------------
						out.println("<p class=ug_headin><a NAME='Quotation_Approval'>");
						out.println("</a><u>Quotation Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen displays a list of pending quotations that are to be approved. A higher level user who has authorization should approve the quotations before printing.");
						
						
						out.println("<p class=ug_headin><a NAME='Quotation_Print'>");
						out.println("</a><u>Quotation Print </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Approved quotations are available for printing through this screen.");

						

						
					
		
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
