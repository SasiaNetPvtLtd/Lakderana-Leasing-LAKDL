

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Marketing_new extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Inquiry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-23
						out.println("<p class=ug_headin><a NAME='Marketing_header'></a><u>Marketing </u> ");
						
						marketing_header="<p align='justify' class=rep-body>You can initiate entering data related to a customer in the Marketing module in the event of receiving an inquiry. If your front office or marketing teams receive inquiry related to the services you offer, for instance, if a prospective customer requests about leasing facility, you can enter his contact details and generate a quotation after calculating the price at the presence of the customer. Alternatively, you can enter fresh data in Quotation window for clients who directly come to obtain service and proceed further. The module also guides you through the system to attach asset details, pricing details, pro-forma invoices and valuation reports for assets as well as document details to an application. You may assess the effectiveness of your marketing programs through data entered in this module.";
						out.println(marketing_header);
						//end added by madhawa 2009-11-23

						
						out.println("<p class=ug_headin><a NAME='Inquiry'></a><u>Inquiry</u> ");
						out.println("<p align='justify' class=rep-body>This screen facilitates entering inquiry related details that are used to process pricing details and to generate quotations. It lets quick data entry and quick processing to satisfy your customer. ");
												//----------------- Pricing User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Pricing'>");
						out.println("</a><u>Pricing</u>");
						out.println(" ");
						out.println("<p class=rep-body>In Pricing screen, you can specify asset details and related costs. After selecting payment terms a payment schedule is generated and cash flow details will be displayed. You can modify the payment schedule and proceed for quotation.");
												//----------------- Quotation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Quotation'>");
						out.println("</a><u>Quotation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Quotation preparation is easy as it uses previously entered data in above screens. By completing administration related data and asset related data, you can generate quotations.");
											//----------------- Quotation Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Quotation_Approval'>");
						out.println("</a><u>Quotation Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Sensitive information such as quotations requires approval before proceeding. A higher level user is required to approve the quotation before printing and handing over to the prospective client.");
						/*out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("<p><li class=rep-body>	View Letter</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>From this section quotations can be approved or deleted. Rate offered at pricing level can be viewed from the Help box right to Quotation number. To approve a quotation, first quotation no has to be selected and then press save option. First select Delete option, and mark the quotation to be deleted and then press save to delete. Any number of quotations can be deleted or approved at once. ");
						out.println("<p align='justify' class=rep-body>Indicative Quotation letters for approved quotations can be printed from View Letter option. User has to select the quotation number from Help option and should press Go. Then the letter gets generated and can print.");
						out.println("<p align='justify' class=rep-body>Printed copy can be given to the client stating terms and conditions relevant to the quotation.");
						*/
						//----------------- Application Process User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Application_Process'>");
						out.println("</a><u>Application Entry</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Once a customer is willing to obtain a facility from you, you require entering necessary details in this window. Alternatively, you may pick data from inquiries that you receive from the same customer. Asset details, Pricing details, Pro-forma invoices, Valuations and required documents have to be set up at this level in order to proceed.");
						//out.println("<blockquote>");
						/*out.println("<p><li class=rep-body>	</p>");
						out.println("<p><li class=rep-body>	 </p>");
						out.println("<p><li class=rep-body>	 </p>");
						out.println("<p><li class=rep-body>	 </p>");
						out.println("<p><li class=rep-body>	</p>");
						out.println("<p><li class=rep-body>	</p>");
						out.println("<p><li class=rep-body> </p>");
						out.println("<p><li class=rep-body>	</p>");
						out.println("<p><li class=rep-body>	 </p>");
						*/
						//out.println("</blockquote>");
						/*out.println("<p align='justify' class=rep-body>When client submits the Application form along with the required documents, the Marketing Executive will enter the details in the application to the system. If the applicant is an existing client, Client code can be selected from the Help option. The user has the facility to search the client by name, NIC number, address or phone numbers. Inquiry number relevant to that applicant can be selected from Inquiry number option. Relevant Inquiry can be filtered by name, address, NIC number, phone numbers or Email address.");
						out.println("<p align='justify' class=rep-body>User can create new clients from Client Creation screen. Co-Applicant will be the name of the joint applicant. Same steps has to be followed when creating Guarantors and assigning those Guarantors to the application. To assign more than one guarantor new row can be added on click of More button. User can select by whom the Insurance is done from the drop down menu available.");
						out.println("<p align='justify' class=rep-body>If client is giving a Bank Guarantee code of the bank which is issuing the guarantee can be selected from the Help box .User can enter the amount and start date and end dates can be selected from the Calendar option. ");
						out.println("<p align='justify' class=rep-body>After creating the applicant, assigning guarantors and bank guarantees to the applicant, entry can be saved using Save option. Once it is saved, an application number will be generated by the system. Then the user can select Edit option and the relevant application number from the Help option in front of the Application number. Other options (Asset Details, Pricing, Valuation and Pro-forma Invoice) will get activated subsequently.");
						out.println("<p align='justify' class=rep-body>Asset Details ");
						out.println("<p align='justify' class=rep-body>Details of the assets can be entered from this section. Accounts can be created from this section for the accounting purpose. This option consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New ");
						out.println("<p align='justify' class=rep-body>In order to enter details of a new asset, the Make, Model, Sub model, Status, Quantity, and Purpose should be entered/selected. If user wants to enter more than one record, new rows can be added from 'More' option. Option with a cross can be used to delete rows. Entry can be saved using the Save option.");
						out.println("<p align='justify' class=rep-body>Edit ");
						out.println("<p align='justify' class=rep-body>The user can edit existing Asset Details through this option. After editing Asset details, entry should be saved using the Save option. ");
						out.println("<p align='justify' class=rep-body>Delete ");
						out.println("<p align='justify' class=rep-body>Asset Details can be deleted from this option.");
						out.println("<p align='justify' class=rep-body>Approval of Pricing");
						out.println("<p align='justify' class=rep-body>Pricing for a particular application can be approved from this section. If an Inquiry is assigned to the application at the Pricing level, that pricing schedule will come for the approval upon selection of that Inquiry. User can do a new pricing by selecting the New Pricing option.");
						out.println("<p align='justify' class=rep-body>Person who is approving the pricing can attend to details from 'View' bar. To approve a pricing, user can tick the relevant Pricing numbers and then save.");
						out.println("<p align='justify' class=rep-body>Pro-forma Invoice ");
						out.println("<p align='justify' class=rep-body>Invoice sent by the client containing details of an asset and its prices is called Pro-forma Invoice and those details will be entered to the system from this option. This section consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("<p><li class=rep-body>	View All</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New ");
						out.println("<p align='justify' class=rep-body>In order to enter new Pro-forma Invoice details, Asset ID and Pricing number should be selected from the Help option. Entry can be saved using the Save option. ");
						out.println("<p align='justify' class=rep-body>Edit ");
						out.println("<p align='justify' class=rep-body>The user can edit the details in an existing Pro-forma invoice using this option. After editing Pro-Forma Invoice, entry should be saved using the Save option.");
						out.println("<p align='justify' class=rep-body>Delete ");
						out.println("<p align='justify' class=rep-body>When the Asset ID is selected, the relevant Model No, Sub Model No, Engine Capacity, Transmission Media and Year of Manufacture will automatically be filled.Net Price, VAT and Total amount get filled when the Pricing number is selected. Other details should be entered manually. Required fields are marked with '*' on the screen. Entry can be saved using the Save option.");
						out.println("<p align='justify' class=rep-body>View All ");
						out.println("<p align='justify' class=rep-body>All Pro-Forma Invoices recorded in the system can be viewed from here.");
						out.println("<p align='justify' class=rep-body>Valuation ");
						out.println("<p align='justify' class=rep-body>Details of valuation reports issued by an authorised valuer are recorded to the system from here. This section consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("<p><li class=rep-body>	View All</p>");
						out.println("</blockquote>");
						*/
											//----------------- Application Status Report User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Application_Status_Rep'>");
						out.println("</a><u>Application Status Report </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is a list of application that are to be processed, but pending due to different reasons. Applications for which Purchase Orders are created and approved are listed here. Ideally you should maintain the number of applications in this list at a minimum. ");
						
						//----------------- Change Proforma User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Change_Proforma'>");
						out.println("</a><u>Change Pro-forma Invoice </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can easily modify data you entered in Pro-forma Invoices. ");
						
						
						//------------------client comments------------------------------------------------------------------------------------------------------------------------------------------------------------------------
						out.println("<p align='justify' class=ug_headin><a NAME='Client_comments'>");
						out.println("</a><u>Client Comments </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Marketing Officers can enter comments into the system, which your clients make about your service, here. Such information will be displayed with client information in applications.");

						
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
