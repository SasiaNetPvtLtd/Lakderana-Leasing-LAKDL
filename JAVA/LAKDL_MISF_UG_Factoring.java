

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_MISF_UG_Factoring extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Pricing User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-26
						out.println("<p class=ug_headin><a NAME='Factoring'></a><u>Factoring</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>Reports under this section are visible for authorized users who provide factoring facilities with the use of the system. This section processes data entered in the factoring system and generates reports in different areas. Details related to your clients, debtors, invoices, users and financial details are some of the inputs whereas reports related to marketing, credit, operations, collections and accounts are the outputs. In order to ensure convenience in accessing, these reports are classified into operational areas as marketing, credit and so on.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

						//--------------Marketing_Inquiry_Details
						out.println("<p class=ug_headin><a NAME='Marketing_Inquiry_Details'></a><u>Marketing - Inquiry Details (User)</u> ");
						out.println("<p align='justify' class=rep-body>This report displays inquires that are received to the company by the user of the current login in which the report is run.");
						//----------------- Charges Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Marketing_Inquiry_Details_all'>");
						out.println("</a><u>Marketing - Inquiry Details (All)</u>");
						out.println(" ");
						out.println("<p class=rep-body>This report displays the inquiries that are received to your company and entered into the system by all marketing officers and front office staff. You are able to view the user who entered the details, date entered, current status of the inquiry and other related details.");
												//----------------- Marketing - Quotation Details Report ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Marketing_Quotation_Details_Report'>");
						out.println("</a><u>Marketing - Quotation Details Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you see a list of quotations that are stored in the system. Other than the basic details you specified for the quotation such as credit limit, credit period and reverse margin, you can view more comprehensive information such as pricing and product details and client details");
											//----------------- Credit - Client Detail Report User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Client_Detail_Report'>");
						out.println("</a><u>Credit - Client Detail Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A list of active factoring clients, along with client related information, can be viewed in this screen.  ");
						//----------------- Credit - Active Debtor List Report User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Active_Debtor_List_Report'>");
						out.println("</a><u>Credit - Active Debtor List Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For a selected client and a facility, you are able to view the list of active clients, related details and the current status through this screen. ");
						//--------Credit - Invoice Verification Report
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Invoice_Verification_Report'>");
						out.println("</a><u>Credit - Invoice Verification Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The movement for a selected invoice can be obtained through this report.");
						//------------Credit - Credit Limit Zero Clients base rates
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Credit_Limit_Zero_Clients'>");
						out.println("</a><u>Credit - Credit Limit Zero Clients</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This give a list of debtors from whom there has been a cheque return and due which their credit limit has been set to zero");
							//------------Operations - Non Sales and Excess Funds Transfer Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Non_Sales_and_Excess_Funds_Transfer_Report'>");
						out.println("</a><u>Operations - Non Sales and Excess Funds Transfer Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report lists the adjustments made based on Non sales and excess funds transfers for a given facility number.");
						//----------Operations - Exception Report
						
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Exception_Report'>");
						out.println("</a><u>Operations - Exception Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report provides a summary of the system through which a high level user can obtain a snapshot of the pending issues available under each level. ");
						
						//--------------Operations - Operation Details
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Operation_Details'>");
						out.println("</a><u>Operations - Operation Details</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report provides the operations details of a given facility number.");
							
						//------------Operations - PD Cheque Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_PD_Cheque_Report'>");
						out.println("</a><u>Operations - PD Cheque Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is a comprehensive report through which information on PD cheques can be obtained.  The user has he option to obtain the report in different view such as available, Assigned OR unassigned to invoice, etc? ");
						//----------------Operations - Realized Settlement Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Realized_Settlement_Report'>");
						out.println("</a><u>Operations - Realized Settlement Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report provides the status of receipts.  This can be filtered according client, facility, debtor and branch and settlement mode.");
						//--------------Operations - Invoice Portfolio Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Invoice_Portfolio_Report'>");
						out.println("</a><u>Operations - Invoice Portfolio Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Invoice portfolio contains all the invoices received from clients. The report groups invoices by the client and sorts them by the client's debtor. This report helps you to figure out the amount issued to clients for the invoices and the effectiveness of collections. You can see the amounts collected from debtors, amounts to be collected from them and other related details for each invoice. Through this you can make advanced opinions on which clients should be retained as clients, against which client legal actions will be taken and so on. You can filter results from the clients name, facility number and debtor code if you wish. ");
							
						//--------------Operations - Client Charges Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Client_Charges_Report'>");
						out.println("</a><u>Operations - Client Charges Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can view fees that are applicable for a client. Once you specify the date range during which the charges are applicable, you can select a client and view descriptions of fees and amounts that have been charged during the period. You can filter results by charge descriptions which are predefined in your system.");
						
						//-------------Operations - PD Age Analysis Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_PD_Age_Analysis_Report'>");
						out.println("</a><u>Operations - PD Age Analysis Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report gives you an analysis of timing to deposit post dated cheques. For example, how many cheques to be deposited today and what is the amount, how many to be deposited within the next 7 days and so on. Also you can have a similar report for receipts that are settled.");
						//-----------------Operations_Reserve_Account_Summary
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Reserve_Account_Summary'>");
						out.println("</a><u>Operations - Reserve Account Summary</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This provides a facility summary report which can be obtained either facility wise or overall.");
						
						//----------------Operations - Return Settlement Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Return_Settlement_Report'>");
						out.println("</a><u>Operations - Return Settlement Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report displays details of cheques that are not yet realized. It separates records as unbanked cheques and cheques with pending realizations. After specifying the date range, you need to select a facility to view these cheque details.");
						
						//-----------------Operations - Return Cheque Exception Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Return_Cheque_Exception_Report'>");
						out.println("</a><u>Operations - Return Cheque Exception Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report lists and provides details on return cheques. ");
						//-----------------Operations - Unallocated Funds Detail Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Unallocated_Funds_Detail_Report'>");
						out.println("</a><u>Operations - Unallocated Funds Detail Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The report provides details on unallocated funds for a given facility.");
						//----------------Operations_Cancel_P_D_Cheque_Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Cancel_P_D_Cheque_Report'>");
						out.println("</a><u>Operations - Cancel P D Cheque Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When clients request to cancel post dated cheques, you will send them the cheque back to the client along with a letter. This screen lets you view such cancellations and letters you sent to those clients. In addition, you can view the date and other details related to such cancellations. The results you obtain in this report can be filtered by the facility. You can view cancellations of post dated cheques for any client in your system.");
						
						//------------------Operations - Unbanked Cheque Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Unbanked_Cheque_Report'>");
						out.println("</a><u>Operations - Unbanked Cheque Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>On request of clients, you can return cheques to clients before banking them. Such events can be taken to a report through this screen. This report shows such cancellations of banking of cheques, client and facility details, letter generated dates and letters sent to the clients along with the cheques. These results appear in the report can be filtered by the facility if you wish. ");
						
						//---------------Operations - PD Cheque and Settlement Receipt Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_PD_Cheque_and_Settlement_Receipt_Report'>");
						out.println("</a><u>Operations - PD Cheque and Settlement Receipt Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can view a report containing all the post dated cheques during a given date range. You can view all related details such as information related to the client, facility, cheque and debtor, etc. You can filter records by the facility number and client if you need specific reports."+ 

						"Similarly you can generate a report for Settlement Receipts during the specified date range. Information such as receipt details, dates, status, client details and facility details are available for retrieval. Records you obtain can be filtered by facility or client if necessary."+
						 "");
						//------------Operations - Invoice Age Analysis Debtor Wise Report
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Invoice_Age_Analysis_Debtor_Wise_Report'>");
						out.println("</a><u>Operations - Invoice Age Analysis Debtor-wise Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report displays debtors with outstanding balances classified by the timing of those payments. You can view the amount a particular debtor is delays a payment by 30 days, 60 days, 90 days and likewise. Through this you are able to determine the performance of debtors, thus performance of clients. Alternatively, you can view the same details only related to a particular facility or a client. ");
						//---------------Operations - Global Availability
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Global_Availability'>");
						out.println("</a><u>Operations - Global Availability</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report provides details on the global availability. This also provides past six months details. The user has the ability to click on a given value to obtain a drill-down which would provide a more comprehensive picture as to how the displayed value was formulated. ");
						//----------------Operations - Client Daily Statement
						out.println("<p align='justify' class=ug_headin><a NAME='Operations_Client_Daily_Statement'>");
						out.println("</a><u>Operations - Client Daily Statement</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can select a client and view a summary of activities that are related to creditworthiness of that client as at a date you select. This is useful to determine the performance of clients and to make decisions regarding the profitability of the customers and so on. ");
						//------------------------Collections - Collection Report
						out.println("<p align='justify' class=ug_headin><a NAME='Collections_Collection_Report'>");
						out.println("</a><u>Collections - Collection Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This comprise of a collection of reports which will enable the factoring company to plan and monitor the collections process. ");
						//------------Collections - Remarks Report
						out.println("<p align='justify' class=ug_headin><a NAME='Collections_Remarks_Report'>");
						out.println("</a><u>Collections - Remarks Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This provides reports based on the remarks given by the users during the collection process.  ");
						//----------------Accounting - Factoring Finance Interest
						out.println("<p align='justify' class=ug_headin><a NAME='Accounting_Factoring_Finance_Interest'>");
						out.println("</a><u>Accounting - Factoring Finance Interest</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is a high level report that shows the summary of daily activities within the specified date range. You can view payments, collections, balances and interest amounts that have charged each day. ");
						//--------------Accounting - Factoring Actual Yield
						out.println("<p align='justify' class=ug_headin><a NAME='Accounting_Factoring_Actual_Yield'>");
						out.println("</a><u>Accounting - Factoring Actual Yield</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The report provides monthly details on yield for a given facility during a given timeframe. ");
						//------------------Accounting - Account Report
						out.println("<p align='justify' class=ug_headin><a NAME='Accounting_Account_Report'>");
						out.println("</a><u>Accounting - Account Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For a given date range this provides the overall accounting information of the factoring system. ");
						//-------------Accounting_Income_Suspense_Generation
						out.println("<p align='justify' class=ug_headin><a NAME='Accounting_Income_Suspense_Generation'>");
						out.println("</a><u>Accounting - Income Suspense Generation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For a given date this report provides details on 4 months movement of active facilities. Based on the movement percentage and conditions the user is able to suspend the selected facility.");
						//------------------Accounting_Income_Suspense_and_Provision_Processing
						out.println("<p align='justify' class=ug_headin><a NAME='Accounting_Income_Suspense_and_Provision_Processing'>");
						out.println("</a><u>Accounting - Income Suspense and Provision Processing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The suspended facilities are listed for a given date range. The user is then able to decide on the provisioning and save the changes. The view report option enables the user to only view the details without the option of entering the provisioning details while the run report option enables the user to enter the provisioning information.");

												
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
