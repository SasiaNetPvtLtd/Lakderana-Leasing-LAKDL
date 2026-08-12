

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_FA_UG_Marketing extends javax.servlet.http.HttpServlet { 

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
						out.println("   <meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'>");
						out.println("   <meta name='Generator' content='Microsoft FrontPage 5.0'>");
						out.println("   <meta name='GENERATOR' content='Mozilla/4.7 [en] (Win98; I) [Netscape]'>");
						out.println("   <title>MIDDLE OFFICE</title>");
						out.println("</head>");
						out.println("<body text='#000000' bgcolor='#FFFFFF' link='#0000FF' vlink='#800080' alink='#FF0000'>");
						
						//-----------------Inquiry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin ><a NAME='Marketing'></a><u>1. Marketing </u>");
						out.println("<p class=rep-body align='justify'>Marketing module consists of initial processing activities such as inquiry, client/debtor collection, initial verification, quotation and quotation approval.");
						out.println(" ");
						out.println("<p class=ug_headin><a NAME='Inquiry'></a><u>1.1 Inquiry </u>");
						out.println("<p align='justify' class=rep-body>The factoring process is commenced from here. The major function of inquiry level is to collect client information. Marketing officer collects client information and enter those data to the system through inquiry option. This option consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Report</p>");
						out.println("</blockquote>");
						
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>This option is used to enter information of new client who requires a factoring facility and save using the save option. In that system automatically generates an inquiry number and this basically consists of year month and inquiry date. "+
												"Ex. If inquiry enter on 01st of January of 2006 and it is the first inquiry for that month,     inquiry number is IQ20060101-0001 "+
												"Following reference objects are used for inquiry process."+
												"");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Initiation type </p>");
						out.println("<p><li class=rep-body>	Customer type </p>");
						out.println("<p><li class=rep-body>	Customer category </p>");
						out.println("<p><li class=rep-body>	Lead source category  </p>");
						out.println("</blockquote>");
						
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>Through Edit >> Help option, inquiry records can be modify except inquiry number Then save again. In addition to modify client information, this option is used to search clients by name, NIC/business registration, telephone number for modifications or review purposes.");
						out.println("<p align='justify' class=rep-body>Report");						
						out.println("<p align='justify' class=rep-body>In order to obtain meaningful information briefly  such as enter time and date, inquiry category, transaction type, name of the client, officer, supervisor and team regarding already exists client in inquiry level, Repot option can be used.");

						//----------------- Client/Debtor Infor Collection User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Client'>");
						out.println("</a><u>1.2 Client /Debtor Information Collection </u>");
						out.println("");
						out.println("<p class=rep-body>This option is used to initially create the client/debtor for initial credit verification. In this stage marketing officer is collecting information and save in the system other than create client/debtor. This option is consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Deactivate</p>");
						out.println("<p><li class=rep-body>	Reactivate</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>In order to enter client/debtor information should be used this option. Selecting client type as individual or corporate is the initial step of client/debtor information collection. Then factoring relationship has to be decided as client, debtor or client or debtor. By Searching  inquiry by name, NIC/business registration, telephone numbers client information in inquiry level can easily be filtered to the client screen other wise enter one by one and save. Before saving required fields mark with (*) should be filled. In that system generates the client/debtor code.");
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>In that edit option can be used in two ways. In order to modify client/debtor information except client /debtor code is first way. In that through Edit>>Help option can be entered/selected client or debtor for modify and save again. Second is to filter client from leasing section to factoring when client who has lease facility applies for factoring too. In accordance with requirements that filtered client's type and factory relationship can be modified.");
						out.println("<p align='justify' class=rep-body>Deactivate");
						out.println("<p align='justify' class=rep-body>Client display in Deactivate>>Help option client list can be deactivate through this option. Select /enter client/debtor from Deactivate >>Help option, then deactivate and save. ");
						out.println("<p align='justify' class=rep-body>Reactivate");
						out.println("<p align='justify' class=rep-body>Select client/debtor through Reactivate >> Help option, then reactivate and save.");
						
						//----------------- Initial Credit Verification User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Verification'>");
						out.println("</a><u>1.3 Initial Credit Verification </u> ");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option is used to initially appraise client credit worthiness for further processing. Successfully saved client is shifted to initial credit verification. Through client code can be obtain more information in this stage before approval. After initially appraising client's ability to obtain factoring facility, decision can be select and enter using down arrow under action heading .Then save selected decision. Successfully save client is shifted to client creation approval level and quotation level.");
						
						//----------------- Quotation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Quotation'>");
						out.println("</a><u>1.4 Quotation. </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option is used to enter information to prepare quotation. Successfully verified client in initial credit verification is shifted to quotation level. Select and enter client information using client code help option and adjust default values if require and select relevant product and fee package. Then save quotation.");
						
						//----------------- Quotation Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Quotation_Approval'>");
						out.println("</a><u>1.5 Quotation Approval</u>");
						out.println("");
						out.println("<p align='justify' class=rep-body>This option is used to approve successfully saved quotation appears in quotation list to be approved. Before approving quotation can be obtain more information through quotation number and client name. Select the decision from decisions mention under action heading and save.");

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
