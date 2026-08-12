

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Marketing extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Inquiry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Inquiry'></a><u>Marketing - Inquiry</u> ");
						out.println("<p align='justify' class=rep-body>This is where the operator enters the initial transaction details to the system. Normally, ");
						out.println("officers who frequently interact with clients, access this section.");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Any body who is trying to acquire a movable asset with repayment capacity is a prospective client for the business. The preliminary purpose of the customer evaluation is to identify the customer as a potential lessee and whether to proceed with a detailed evaluation of the lease proposal. Details of initial interaction between prospective clients will be recorded from here.There can be so many ways that prospective clients are inquiring about the products of the company. E.g. interview, call, visit etc");
						out.println("<p align='justify' class=rep-body>Some of the reference objects created using administration screens will be used here. Those references can be selected either using help option or drop down menus. Those are");
						out.println("<ol>");
						out.println("<ol>");
						out.println("<li class=rep-body>");
						out.println("Initiation type   (can have a link to the description under administration screens)</li>");
						out.println("<li class=rep-body>");
						out.println("Customer category</li>");
						out.println("<li class=rep-body>");
						out.println("Customer type</li>");
						out.println("<li class=rep-body>");
						out.println("Lead source category</li>");
						out.println("<li class=rep-body>");
						out.println("Transaction type</li>");
						out.println("<li class=rep-body>");
						out.println("Transaction sub type</li>");
						out.println("<li class=rep-body>");
						out.println("Team</li>");
						out.println("<li class=rep-body>");
						out.println("Marketing Officer Code</li>");
						out.println("<li class=rep-body>");
						out.println("Supervisor Code</li>");
						out.println("</ol>");
						out.println("</ol>");
						out.println("<p align='justify' class=rep-body>You can create, modify, reactivate or deactivate those objects using administration screens.");
						out.println("<p align='justify' class=rep-body>Details that are essential to record an Inquiry have been marked with the sign '*'.Inquiry entry can be saved using the Save option .Once the entry is saved, an inquiry number will be generated. Those are system generated in serial order with a 15 character length, comprising Year, Month, Date and the inquiry number.");
						out.println("<p align='justify' class=rep-body>After the entry is saved, system will take the user to another screen where the follow up actions can be set. Next follow up action, next follow up action date, actual action date can be set from here. Status of that inquiry can be selected from the list box. System will include that activity in the Outstanding Activity Report on the day user set as the next action date. Activities will be there in the report until its status is updated as completed.");
						out.println("<p align='justify' class=rep-body>Inquiry details can be filtered by name, company name, e-mail address, phone number and NIC number in Edit option. Blacklisted clients can be detected at the Inquiry level. Status of blacklisted clients will be shown as B in the client Help box.");
						out.println("<p align='justify' class=rep-body>Saved inquiry can be modified using the Edit option. Once the inquiry is modified it has to be saved once more using the Save option.");						
						out.println("<p align='justify' class=rep-body>Report User - option will show the inquiries entered by the logged user to the system. Client name and contact details can be viewed on click of Inq option. Details of follow up actions can be obtained by drilling down the Fol option.");
						out.println("<p align='justify' class=rep-body>Report All - This option will show the inquiries entered to the system by all users. Client name and contact details can be viewed on click of Inq option. Details of follow up actions can be obtained by drilling down the Fol option.");
						
						//----------------- Pricing User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Pricing'>");
						out.println("</a><u>Marketing - Pricing </u>");
						out.println(" ");
						out.println("<p class=rep-body>This is the section where pricing details are entered. There are so many factors to be considered, when calculating rentals. Clients can select their own patterns to pay rentals.");
						out.println("<p class=rep-body>This section consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Calculate</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>In order to create a new pricing scenario, lease cost, VAT rate, (only applicable if the facility is a lease) Interest rate, Period of financing, Repayment Interval etc has to be entered. Details that are essential to create a new pricing scenario have been marked with the sign '*'. After entering necessary details, can click on calculate button.");
						out.println("<p align='justify' class=rep-body>Then the schedule of rental payments will be displayed. Entry can be saved using the Save option. Pricing information can also be saved without assigning an Inquiry number. In Inquiry option there is a facility to search clients by name, address and the phone numbers. When you get the rental schedule, there is an option called cash flow to get the cash flow report.");
						out.println("<p align='justify' class=rep-body>Once the schedule gets generated user can change the rental amount and rental factor according to the client's willingness to make the payments. If user wants to change 3 rentals he/she has to change all three before pressing on calculate option.");
						out.println("<p align='justify' class=rep-body>To modify an existing Pricing scenario, Pricing code should be entered/selected from the Help option. After modifying Pricing details, entry should be saved using the Save option. ");
						out.println("<p align='justify' class=rep-body>User can search inquiries by name, address, and phone number.");
						out.println("<p align='justify' class=rep-body>Interest Rate - Interest rate can be entered from here. This can be varied among transaction type, clients, item category etc. This rate must par with the industry rates. ");
						out.println("<p align='justify' class=rep-body>VAT % on Purchase - Value Added Tax rate will change according to the item category and transaction type. These are tax rates prevailing in the country.");
						out.println("<p align='justify' class=rep-body>VAT % Applicable for Finance- VAT %, which the company can claim from the Inland Revenue, is called VAT applicable. Difference between the VAT rate & VAT applicable has to be added back to the Net amount.");
						out.println("<p align='justify' class=rep-body>VAT % on Rental - Value Added Tax Percentage on rental that should be paid to the Inland Revenue base on rental value.");
						out.println("<p align='justify' class=rep-body>Period of Financing - This records the length of the asset financing period, in terms of no of instalments.");
						out.println("<p align='justify' class=rep-body>Repayment Interval - This describes the pattern chosen by the client to repay the    facility. This can be selected from the drop down menu. "+
												" E.g.: monthly, quarterly"+
												"");
						out.println("<p align='justify' class=rep-body>Amount Gross- Gross amount that the client is willing to finance from the company.");						
						out.println("<p align='justify' class=rep-body>VAT Amount - This amount is calculated by multiplying the amount net from VAT rate.");						
						out.println("<p align='justify' class=rep-body>Amount Net - After deducting the VAT amount from the amount gross can be reached to the amount net. This is automatically calculated by the system.");						
						out.println("<p align='justify' class=rep-body>Amount Financed - Amount Financed is the base value of rental calculation when charges are not amortized with the rental.");						
						out.println("<p align='justify' class=rep-body>NIBSM - Some customers are paying an initial amount as a Non Interest Bearing Security Margin at the beginning of the contract. This amount will be reduced from the asset financing cost.");						
						out.println("<p align='justify' class=rep-body>Residual Value - There is another alternative to NIBSM which is paid at the end of the contract. This is called residual value.");						
						out.println("<p align='justify' class=rep-body>Supplier Credit - This means in which instalment, the supplier's payment is made.");						
						out.println("<p align='justify' class=rep-body>AMI -Number of instalments that are paid along with the offer charges.");						
						out.println("<p align='justify' class=rep-body>Advance & Arrears - If clients are making the rental payment at the beginning of the period, it is called advance and if it is make at the end of the period, it is called arrears.");						
						out.println("<p align='justify' class=rep-body>Variable Interest Rate - Interest rate is determined according to the market rates.");						
						out.println("<p align='justify' class=rep-body>Fixed Interest Rate  - Interest rate is fixed at the beginning of the contract and it is not changing according to the market movements.");						
						out.println("<p align='justify' class=rep-body>Maintenance charges - These charges are applicable for hiring and only if the company is incurring maintenance charges. Present Value of those charges will be added to the asset financing cost when calculating rentals.");						
						out.println("<p align='justify' class=rep-body>Standard Charges and Costs  - To activate an asset financing facility there are some charges that has to be paid by the client .These charges can be set from the Applicable charges screen. Charges will be set according to an item sub category.");						
						out.println("<p align='justify' class=rep-body>Backward Calculations - System facilitates to do backward calculations. To do a backward calculation first user has to select the field that is going to be calculated from the Target Field. Then user has to enter '0' in to the targeted field.");						
						out.println("<p align='justify' class=rep-body>E.g. If user is going to calculate the Interest rate, 0 should be inserted in the Interest rate field and Interest Rate has to be selected from the Target Field drop down menu. Then Calculate option can be selected an Interest rate for the given scenario will be calculated.");						
						out.println("<p align='justify' class=rep-body>Step up ");
						out.println("<p align='justify' class=rep-body>Rental for the facility will increase as the time goes up during the period of financing. For the calculation Rental factor on the schedule can be changed. For step ups rental factor can be increased. User can change the rental factor according to the client's requirements. ");						
						out.println("<p align='justify' class=rep-body>Once the factor is changed system will ask whether to apply this change to all the below rentals. By selecting Yes option change can be applied to all the below rentals on the schedule. After making theses changes calculation has to be done again.");						
						out.println("<p align='justify' class=rep-body>Step Down ");
						out.println("<p align='justify' class=rep-body>Rental for the facility will decrease as the time goes up during the period of financing. For the calculation Rental factor on the schedule can be changed. For step ups rental factor can be decreased. User can change the rental factor according to the client's requirements. ");						
						out.println("<p align='justify' class=rep-body>Once the factor is changed system will ask whether to apply this change to all the below rentals. By selecting Yes option change can be applied to all the below rentals on the schedule. After making theses changes calculation has to be done again.");						
						out.println("<p align='justify' class=rep-body>Some of the reference objects created using administration screens will be used here. Those references can be selected either using help option or drop down menus. Those are");						
						out.println("<ol>");
						out.println("<ol>");
						out.println("<li class=rep-body>");
						out.println("Transaction Type (Can have a link to the description under Administration screens)</li>");
						out.println("<li class=rep-body>");
						out.println("	Transaction Sub Type (Standard, Step Up, Step Down) "+
												"	Condition of Asset "+
												"</li>");
												
						out.println("<li class=rep-body>");
						out.println("Item Category</li>");
						out.println("<li class=rep-body>");
						out.println("Item Sub Category</li>");
						out.println("<li class=rep-body>");
						out.println("Make of Asset</li>");
						out.println("<li class=rep-body>");
						out.println("Sub Model of Asset</li>");
						out.println("<li class=rep-body>");
						out.println("Engine Capacity</li>");
						out.println("<li class=rep-body>");
						out.println("Fuel Type</li>");
						out.println("<li class=rep-body>");
						out.println("Buy Back</li>");
						out.println("<li class=rep-body>");
						out.println("Asset Usage Type</li>");
						out.println("</ol>");
						out.println("</ol>");
						out.println("<p align='justify' class=rep-body>Those reference objects can be Created, Modified, Reactivated or Deactivated using administration screens.");						
						
						//----------------- Quotation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Quotation'>");
						out.println("</a><u>Marketing - Quotation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Quotation is an estimate of monthly rental for different periods, different asset financing costs and for different models of assets. Information recorded in Inquiry and Pricing will be used here to prepare an Indicative Quotation. This will helps clients to make a decision on selecting asset financing method.");
						out.println("<p align='justify' class=rep-body>This section consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Pricing</p>");
						out.println("<p><li class=rep-body>	Inquiry</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("<p><li class=rep-body>	View Letter</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>In order to create a new quotation New option has to be selected. Quotation cannot be issued without assigning an Inquiry. Details that are essential to create a new quotation have been marked with the sign '*'. After entering necessary details, can click on Save button. Once the Pricing number is selected other fields will be filled automatically. If make and model has not been assigned at the Pricing level user can do it at the Quotation level. Quantity, Make, Model and Condition of assets can be changed for selected Pricing number. Any number of alternative options can be added to one quotation.");
						out.println("<p align='justify' class=rep-body>If user wants to add another option, has to select Add option. User can assign more than one Pricing for a one particular option by pressing Add option. On click of Delete button, user can delete an option.");
						out.println("<p align='justify' class=rep-body>Except for the standard conditions, should user wants can add another conditions by approving the conditions stated in the screen. Approved conditions will come to the quotation letter.");
						out.println("<p align='justify' class=rep-body>User can access to Pricing and Inquiry screens while working in the quotation, on click of Pricing and Inquiry buttons. After closing those screens user will return to the quotation screen.");
						out.println("<p align='justify' class=rep-body>Cancel option can be used to clear the screen.");
						out.println("<p align='justify' class=rep-body>From View Letter option user can view the Indicative Quotation letter for approved quotation. User has to select the quotation number from Help option and press Go. Then the letter gets generated and can print.");
						out.println("<p align='justify' class=rep-body>The Interest rate and the period used to quote the facility will be compared with the Internal Rate of Return (IRR) and the period {(Period of Financing/Repayment Interval) < (IRR /Period)}; if there is any discrepancy (only if it is less than IRR and the period) quotation will be directed to Quotation Approval level for another approval. Quotation will be approved at this level, if the interest rate is equal or greater than the company's IRR and the period for that IRR.");
						out.println("<p align='justify' class=rep-body>Once information is saved Indicative Quotation letter will be displayed on the screen. On the right click of your mouse, user can select the print option to print the document. Printed copy can be given to the client stating terms and conditions relevant to the quotation.");
						
						//----------------- Quotation Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Quotation_Approval'>");
						out.println("</a><u>Marketing - Quotation Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Quotations where IRR is greater than the rate offered at Pricing level and where there is difference between period {(Period of Financing/Repayment Interval)< (IRR /Period)} will come to this level for another approval. This section consists of following options.");
						out.println("<blockquote>");
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
						
						//----------------- Application Process User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Application_Process'>");
						out.println("</a><u>Marketing - Application Process</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Application for a prospective client will be processed from this section and then it will be directed for the approval. This section consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Inquiry</p>");
						out.println("<p><li class=rep-body>	Pricing</p>");
						out.println("<p><li class=rep-body>	Client Creation</p>");
						out.println("<p><li class=rep-body>	Guarantor Creation</p>");
						out.println("<p><li class=rep-body>	Bank /Personal Guarantee</p>");
						out.println("<p><li class=rep-body>	Asset Details</p>");
						out.println("<p><li class=rep-body>	Pricing Approval</p>");
						out.println("<p><li class=rep-body>	Pro-forma Invoice </p>");
						out.println("<p><li class=rep-body>	Valuation </p>");
						out.println("<p><li class=rep-body>	Documents Required </p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>When client submits the Application form along with the required documents, the Marketing Executive will enter the details in the application to the system. If the applicant is an existing client, Client code can be selected from the Help option. The user has the facility to search the client by name, NIC number, address or phone numbers. Inquiry number relevant to that applicant can be selected from Inquiry number option. Relevant Inquiry can be filtered by name, address, NIC number, phone numbers or Email address.");
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
						out.println("<p align='justify' class=rep-body>New ");
						out.println("<p align='justify' class=rep-body>In order to enter new Valuation details, Asset ID and Pricing number should be selected from the Help option. Once the Asset ID is selected, the relevant Model No, Sub Model No, will get filled. Other details can be entered manually. Required fields have been marked with '*' on the screen. Entry can be saved using the Save option.");
						out.println("<p align='justify' class=rep-body>Edit ");
						out.println("<p align='justify' class=rep-body>The user can edit the existing valuation details through this option. After editing, valuation details should be saved using the Save option. ");
						out.println("<p align='justify' class=rep-body>Delete ");
						out.println("<p align='justify' class=rep-body>Valuation details can be deleted from this option.");
						out.println("<p align='justify' class=rep-body>View All ");
						out.println("<p align='justify' class=rep-body>All Valuations recorded in the system can be viewed from here.");
						out.println("<p align='justify' class=rep-body>Documents Required");
						out.println("<p align='justify' class=rep-body>Status of the documents required from applicant, guarantor and asset will be recorded to the system using this section. Either status or Follow up status has to be filled. If follow up status is selected it is required to enter a remark. When a Follow up action is entered it will go to the Outstanding Activity Report.");
						out.println("<p align='justify' class=rep-body>After updating the status of the Documents Required entry can be saved using the Save option.");
						
						//----------------- Application Status Report User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Application_Status_Rep'>");
						out.println("</a><u>Marketing - Application Status Report </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Applications which are not completed will be listed in this report. Using the link available in the Application number user can go to the Application Processing screen. ");
						
						//----------------- Change Proforma User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Change_Proforma'>");
						out.println("</a><u>Marketing - Change Performa Invoice </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is an additional option for changing Performa invoice details. Using application number can be picked invoice number and relevant details for selected invoice number. In that enable fields can only be modified and save using save option.");
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
