

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Finance_new extends javax.servlet.http.HttpServlet { 

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
						//-------------Finance_head
						out.println("<p class=ug_headin><a NAME='Finance_head'></a><u>Finance </u> ");
						out.println("<p align='justify' class=rep-body>Processing of payment requisitions made by the Credit department, payment approvals and cheque printing are the dominant activities performed in the Finance module. Termination related activities such as letter generation, approvals and legal terminations are also grouped as a section in this module. The specialty in this module is the fact that the user is directed towards entering accurate details to the system and assists making correct financing decisions. ");

						//-----------------Payment_Requisitions_Processing  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Requisitions_Processing'></a><u>Payment Requisitions - Processing</u> ");
						out.println("<p align='justify' class=rep-body>Payment Requisition - Processing window allows you to approve a Payment Requisition generated in the Credit module. This is an activity that should be assigned to a user who is authorized to process a payment requisition.");
						/*out.println("<p align='justify' class=rep-body>Details of the payment are listed according to the Purchase Order No, Finance No and Application No. To make the request user has to view the details of the payment on click of view option. In that section account number from which the cheque is drawing, Engine, Chassis, Registration number of the vehicle, Details about the Revenue License and status of the required documents can be recorded.");
						out.println("<p align='justify' class=rep-body>If there is a difference between the engine, chassis and vehicle numbers which were recorded in the Pro-forma Invoice will go to a Temporary approval level.");
						out.println("<p align='justify' class=rep-body>Any conditions entered in previous stages will be displayed at this level and if user wants new conditions can be added. Using the link available from Follow up, status of the existing conditions can be changed.");
						out.println("<p align='justify' class=rep-body> After completing the request Payment will be directed for the approval. Documents which are not received at this level will go to Follow up -Outstanding Activity Report as a pending activity for future follow up.");
						*/
						//-----------------Finance -  Payment Requisition Special Approval  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Requisition_Special_Approval'></a><u>Payment Requisitions - Special Approval</u> ");
						out.println("<p align='justify' class=rep-body>You can define criteria for which a payment requires special approval from a senior user. For example, if the amount is higher than a certain amount, the entry can be sent through special approval process. Definition of criteria is done in the Administration module. This screen allows a senior user to give the special approval.");

						//----------------- Payment Approval 1 User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Approval1'>");
						out.println("</a><u>Payment Approval - First Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After approval of the Payment Requisition, upon receipt of Certificate of Registration of the asset financed, a payment should be made to the vendor. Payments for vendors have to be approved before activated. This screen allows you to give the first approval for such payments.");
						/*out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>Details of the payment are listed according to the Purchase Order No, Finance No and Application No. To approve the payment user has to view the details of that payment on click of view option. In that section person who is approving the payment can check details about supplier, amount to be approved and status of the required documents can be recorded. After completing that section user can approve the payment by saving the record. Any conditions entered in previous stages will be displayed at this level and if user wants new conditions can be added. Using the link available from Follow up, status of the existing conditions can be changed .Documents which are not received at this level will go to Follow up -Outstanding Activity Report as a pending activity for future follow up.");
						out.println("<p align='justify' class=rep-body>Delete");
						out.println("<p align='justify' class=rep-body>Approved payment records can be deleted from the Delete option. Once it is deleted the record will return to the Payment Approval screen.");
						*/
						//----------------- Payment Approval 2 User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Approval2'>");
						out.println("</a><u>Payment Approval - Second Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Payments for vendors require a higher level approval too. This activity is expected to be done by a higher level user who can authorize a payment.");
						
						//----------------- Other_Payment_generation ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Other_Payment_generation'>");
						out.println("</a><u>Other Payments - Generation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Payments other than those which are made to vendors are considered as other payments. Such payments made to lawyers, seizers, brokers, insurance companies, advertising companies, etc. are generated in this screen.");

						//-----------Other_Payments_Processing
						out.println("<p class=ug_headin><a NAME='Other_Payments_Processing'>");
						out.println("</a><u>Other Payments - Processing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Generation of the payment should be followed by processing of the same. This screen allows you to select the payee and amount to be allocated.");
						//------------Other Payments -Authorization
						out.println("<p class=ug_headin><a NAME='Other_Payments_Authorization'>");
						out.println("</a><u>Other Payments - Authorization</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen helps you to authorize other payments made and processed. A higher level user will have the right to approve such payments.");
						//---------Other Payments - Account Selection
						out.println("<p class=ug_headin><a NAME='Other_Payments_Account_Selection'>");
						out.println("</a><u>Other Payments - Account Selection</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The account number from which the amount to be paid has to be selected as the next stage of an other payment.");
						//---------------Other Payments - First Approval
						out.println("<p class=ug_headin><a NAME='Other_Payments_First_Approval'>");
						out.println("</a><u>Other Payments - First Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can approve or disapprove a selected payment that is considered as other payment in this screen.");
						//----------Other Payments - Second Approval
						out.println("<p class=ug_headin><a NAME='Other_Payments_Second_Approval'>");
						out.println("</a><u>Other Payments - Second Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A senior level user has to give the final approval to activate other payments. ");
						//------------Other Payments - View Vouchers
						out.println("<p class=ug_headin><a NAME='Other_Payments_View_Vouchers'>");
						out.println("</a><u>Other Payments - View Vouchers</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Within a given date range, you can view payment vouchers that are active in the system through this screen. ");

						
						//----------------- Cheque Printing User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Printing'>");
						out.println("</a><u>Cheques - Printing </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After selecting an account from which a cheque to be drawn, you can select and enter the receivers and other details and print details on a cheque leaf. You can also generate payment vouchers for your documentation purposes.");
						/*out.println("<p align='justify' class=rep-body>If the payer name is different from the person to whom the Purchase order is released user can make the Payee Category as 3rd party & can change the Payer name.");
						out.println("<p align='justify' class=rep-body>Cheque to be printed can be selected by marking the relevant tick box and upon saving cheque will be printed.");
						*/
						//----------------- Cheque Disbursement User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Disbursement'>");
						out.println("</a><u>Cheque - Disbursement </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Cheques that are to be disbursed can be disbursed here, after selecting the person to whom the cheque has to be disbursed and entering other verification details. ");
						
						//----------------- Cheque Cancellation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Cancellation'>");
						out.println("</a><u>Cheque - Cancellation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you cancel printed cheques within 7 days of printing. After 7 days the cheque can be automatically deleted from the system. You can select the account number from the list of account numbers that contain cheques which are not disbursed. ");
						//out.println("<p align='justify' class=rep-body>User can first select the relevant account from which cheque is drawn. Then cheques which are hold for more than 7 days and drawn from that account will be shown. User can select the relevant cheque by marking the relevant tick box and then can save the record using the Save option. Once the cancellation is saved cheque will return to printing stage.");
						//-----------------Termination - Termination Letter
						out.println("<p class=ug_headin><a NAME='Termination_Termination_Letter'>");
						out.println("</a><u>Termination - Termination Letter </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For normal or early termination, you can generate the letter to be sent to the client using this screen. As the system can readily access due amounts and other details, you have to specify only the finance number that can be searched from the list of finances.");
						//-------------Termination - First Termination Approval
						out.println("<p class=ug_headin><a NAME='Termination_First_Termination_Approval'>");
						out.println("</a><u>Termination - First Termination Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Termination action has to be approved by two user levels in order to effect. This screen lets you approve the termination and also allows viewing the details such as financial impact of the termination. ");
						//------------------Termination - Second Termination Approval
						out.println("<p class=ug_headin><a NAME='Termination_Second_Termination_Approval'>");
						out.println("</a><u>Termination - Second Termination Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Similar to the first approval, second approval allows you to do the same. The only difference is that this is accessible to higher level users.");
						//------------------Termination - Termination Calculation
						out.println("<p class=ug_headin><a NAME='Termination_Termination_Calculation'>");
						out.println("</a><u>Termination - Termination Calculation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can calculate the financial implications of the termination of a facility by specifying termination rate, termination date and so on. ");
						//-------------------Termination - Allocation of Receipts
						out.println("<p class=ug_headin><a NAME='Termination_Allocation_of_Receipts'>");
						out.println("</a><u>Termination - Allocation of Receipts</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Receipts from the client to settle outstanding amounts have to be allocated against the termination values. ");
						//--------------Termination - Termination Processing
						out.println("<p class=ug_headin><a NAME='Termination_Termination_Processing'>");
						out.println("</a><u>Termination - Termination Processing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>As the final step, termination has to be processed with the use of this screen, once all transactions related to a facility are over. Regardless of the termination type, i.e. whether early termination, normal termination or partial termination, etc, you have to use this screen to close a facility.");
						//---------Legal Termination Processing
						out.println("<p class=ug_headin><a NAME='Legal_Termination_Processing'>");
						out.println("</a><u>Legal Termination Processing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A legal termination is effected once you, the lessor, cannot recover the financed amount on asset after reselling the asset or when you are unable to locate the asset to seize it. In such a situation you will terminate the contract with legal actions taken. Here you can initiate feeding details about such a legal termination into the system.");
						//--------------3 Termination - Legal Termination First Approval
						out.println("<p class=ug_headin><a NAME='Termination_Legal_Termination_First_Approval'>");
						out.println("</a><u>Termination - Legal Termination First Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After a legal termination is entered into the system, that should be approved by two users. The first approval can be given with the use of this screen.");
						//--------------Termination - Legal Termination Second Approval
						out.println("<p class=ug_headin><a NAME='Termination_Legal_Termination_Second_Approval'>");
						out.println("</a><u>Termination - Legal Termination Second Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Another user needs to approve the legal termination for the second time. This screen lists all legal terminations that are given the first approval.");
						//----------------Legal Termination Confirmation
						out.println("<p class=ug_headin><a NAME='Legal_Termination_Confirmation'>");
						out.println("</a><u>Legal Termination Confirmation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Legal terminations, which are given the second approval, will wait for confirmation in order to become active. Through this screen you can confirm a legal termination to activate that.");
						//----Security File - Movement Entry
						out.println("<p class=ug_headin><a NAME='Security_File_Movement_Entry'>");
						out.println("</a><u>Security File - Movement Entry</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Documents related to securities, which are entered into the system, should be able to be moved out and again returned back upon request of the provider of the document. The system allows you to make an entry for such movements by providing a reason why the movement is necessary.");
						//----------Security File - Movement Approval
						out.println("<p class=ug_headin><a NAME='Security_File_Movement_Approval'>");
						out.println("</a><u>Security File - Movement Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Movement of Security Documents, which is entered previously, requires approval of another user. This screen should be used for that.");
			
						//---Invoicing - Invoice Generation
						out.println("<p class=ug_headin><a NAME='Invoicing_Invoice_Generation'>");
						out.println("</a><u>Invoicing - Invoice Generation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Invoice generation is done by running the Invoice Routine Cycle. You need to set the duration for which the invoices should be generated. Then exceptions can be generated without changing dates of the Invoice Cycle.");
						//---------------Invoicing - Invoice Adjustments
						out.println("<p class=ug_headin><a NAME='Invoicing_Invoice_Adjustments'>");
						out.println("</a><u>Invoicing - Invoice Adjustments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Generated invoices can be adjusted before proceeding. You can change the amount, CR/DR narrations, invoice dates, etc and you are facilitated to generate a letter for the adjustment.");
						//----------Invoicing - Invoice Printing
						out.println("<p class=ug_headin><a NAME='Invoicing_Invoice_Printing'>");
						out.println("</a><u>Invoicing - Invoice Printing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After invoices are generated, they can be printed and sent to the client. Generated invoices are grouped by the Finance number for later reference.");
						//-------------Invoicing - Invoice Reversal
						out.println("<p class=ug_headin><a NAME='Invoicing_Invoice_Reversal'>");
						out.println("</a><u>Invoicing - Invoice Reversal</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>There is a separate invoice number for each payment in a facility as there are multiple installments. You can reverse one or many invoices in case of a cheque return or any other reason.");
						//---------Finance Activation
						out.println("<p class=ug_headin><a NAME='Finance_Activation'>");
						out.println("</a><u>Finance Activation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Activation of finance is generally automatic and triggered by approval of a Purchase Order. If there are multiple assets, the user has to activate contracts manually for selected assets. ");
						//-------------Payment Deletion
						out.println("<p class=ug_headin><a NAME='Payment_Deletion'>");
						out.println("</a><u>Payment Deletion</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen allows you to select the payments in the system, from a list of all payments within a specified date range. Separate lists exist for cheque disbursements and cheque printing.");
						//-------Payment Clearing
						out.println("<p class=ug_headin><a NAME='Payment_Clearing'>");
						out.println("</a><u>Payment Clearing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>If you charge any amount from your clients other than the rental amount (e.g. for insurance or maintenance), that are to be paid for other third parties, you will have to clear these payments after you make the payment. This is necessary as you might charge from your client slightly more than what is actually required. Such amounts should be cleared as they appear as a pending payment for the payee otherwise.");
						//---Capital Allowance Rate adjustment
						out.println("<p class=ug_headin><a NAME='Capital_Allowance_Rate_adjustment'>");
						out.println("</a><u>Capital Allowance Rate Adjustment</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can adjust the Capital Allowance rate for any facility. ");
						//-------------Sales Price Entry
						out.println("<p class=ug_headin><a NAME='Sales_Price_Entry'>");
						out.println("</a><u>Sales Price Entry</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>As you will charge a nominal amount from your client when transferring the ownership of the asset to the client. You can set the amounts you wish to charge for each type of asset, after specifying the period for which that amounts are applicable. ");
						//-------------Over Drafts - Adjustments
						out.println("<p class=ug_headin><a NAME='Over_Drafts_Adjustments'>");
						out.println("</a><u>Over Due Interest - Adjustments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You have to select a client and a finance number in order to increase or decrease the overdue interest rate that is applicable for the client.");
						//------------Over Drafts - Adjustments First Approval
						out.println("<p class=ug_headin><a NAME='Over_Drafts_Adjustments_First_Approval'>");
						out.println("</a><u>Over Due Interest - Adjustments First Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Two users have to approve the overdue interest change entry created in the previous screen. This screen facilitates for the first level approval.");
						//-----------Over Drafts - Adjustments Second Approval
						out.println("<p class=ug_headin><a NAME='Over_Drafts_Adjustments_Second_Approval'>");
						out.println("</a><u>Over Due Interest - Adjustments Second Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here a higher level user is required to give the final approval for the overdue interest rate adjustment for clients.");
						//--------------DR/CR Notes - Debit Note
						out.println("<p class=ug_headin><a NAME='DR/CR_Notes_Debit_Note'>");
						out.println("</a><u>DR/CR Notes - Debit Note</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When you are to charge any fees from your clients, other than from installments, you will have to issue debit notes, requesting clients to pay the stated amount. This screen helps you to generate such Debit Notes.");
						//----------------DR/CR Notes - Debit Note Cancellation
						out.println("<p class=ug_headin><a NAME='DR/CR_Notes_Debit_Note_Cancellation'>");
						out.println("</a><u>DR/CR Notes - Debit Note Cancellation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can cancel generated debit notes with the use of this screen. ");

						//------------DR/CR_Notes_Credit_Note_Approval_Cancellation
						out.println("<p class=ug_headin><a NAME='DR/CR_Notes_Credit_Note_Approval_Cancellation'>");
						out.println("</a><u>DR/CR Notes - Credit Note Approval/Cancellation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen permits you to approve or disapprove credit notes you generate. From the list of credit notes that appears, you can tick the relevant check boxes in front of each credit note and select approve or disapprove to change the status.");
						//------------Change Payee
						out.println("<p class=ug_headin><a NAME='Change_Payee'>");
						out.println("</a><u>Change Payee</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can change the payees, which were originally defined in the system, at a later stage. This screen facilitates you to change payees for individual transactions as well as for bulk transactions where all payments for a particular payee are assigned to a new payee.");
						//-------------DR/Income_Suspense_Provisioning
						out.println("<p class=ug_headin><a NAME='Income_Suspense_Provisioning'>");
						out.println("</a><u>Income Suspense & Provisioning</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>According to the Central Bank regulations, you will need to maintain bad and doubtful debts provisioning for late receivables. This screen lets you run income suspense and provisioning routine and view a report containing the details. Along with the amounts that can be recognized as income, it shows the breakdown of the receipts that are delayed from 3 months to 6 months, 6 months to 9 months and likewise.");

						//-----------Legal Provisioning
						out.println("<p class=ug_headin><a NAME='Legal_Provisioning'>");
						out.println("</a><u>Legal Provisioning</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can view details of facilities that are subject to legal termination. All records are classified as Operating Leases, Finance Leases and Hire Purchase agreements. You can view the amount that should be recognized as income and amount to be provisioned, etc according to the delay of payments. ");

						
						//----------------- Invoice Adjustments User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p class=ug_headin><a NAME='Invoice_Adjustments'>");
						out.println("</a><u>Finance - Invoice Adjustments </u>");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("<p><li class=rep-body>	View Letter</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						*/
						//out.println("<p align='justify'>In order to calculate termination value for an application, Termination date and client name can be selected from the Help option. Upon selection of client name existing application numbers for that client will come for the Help box and user can select the relevant Application number. Then the Client name, rate offered at Pricing level, outstanding amount if any, and number of termination calculations have been done will get updated. If there is any other charges relevant for termination can be entered to other charges field. After entering termination date and the rate user can press on Calculate option to do the calculation. ");
						//out.println("<p align='justify'>Termination schedule will display present values of future rentals and Termination amount. Then user can save the termination calculation.");
						//out.println("<p align='justify' class=rep-body>Delete");
						//out.println("<p align='justify'>In order to delete a termination calculation, termination number has to be selected. Then termination schedule and other details will display in the screen. To delete record has to be saved.");
						//out.println("<p align='justify'>Printed cheques can be cancelled from this section. Printed cheque will be hold only for 7 days and after 7 days it will be cancelled. Cheques which are hold for more than 7 days will come to this section. ");
						//out.println("<p align='justify'>User can first select the relevant account from which cheque is drawn. Then cheques which are hold for more than 7 days and drawn from that account will be shown. User can select the relevant cheque by marking the relevant tick box and then can save the record using the Save option. Once the cancellation is saved cheque will return to printing stage.");
						
						//----------------- Termination - Calculation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p class=ug_headin><a NAME='Termination_Cal'>");
						out.println("</a><u>Finance - Termination - Calculation </u>");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>In order to calculate termination value for an application, Termination date and client name can be selected from the Help option. Upon selection of client name existing application numbers for that client will come for the Help box and user can select the relevant Application number. Then the Client name, rate offered at Pricing level, outstanding amount if any, and number of termination calculations have been done will get updated. If there is any other charges relevant for termination can be entered to other charges field. After entering termination date and the rate user can press on Calculate option to do the calculation. ");
						out.println("<p align='justify' class=rep-body>Termination schedule will display present values of future rentals and Termination amount. Then user can save the termination calculation.");
						out.println("<p align='justify' class=rep-body>Delete");
						out.println("<p align='justify' class=rep-body>In order to delete a termination calculation, termination number has to be selected. Then termination schedule and other details will display in the screen. To delete record has to be saved.");
						*/
						//----------------- Termination - Allocaton of Receipts User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
					/*	
						out.println("<p align='justify' class=ug_headin><a NAME='Termination_Allo_Receipts'>");
						out.println("</a><u>Finance - Termination - Allocation of Receipts </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Receipts from the client should be allocated against Termination value. Following options are available for Termination Allocate / Unallocate. ");						
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>To allocate a receipt Termination number should be selected first. Then the balance due and any receipts from that client will display in the screen. User can allocate or unallocate receipts. The amount to be allocated can be entered to the amount field and then can mark on the relevant tick box in front of the record. Unallocation also can be done by entering a tick in the relevant tick box. Then the amount will be zero. Using the save option records can be saved.");
						out.println("<p align='justify' class=rep-body>Delete");
						out.println("<p align='justify' class=rep-body>In order to delete a termination allocation, termination number has to be selected. Then allocation details will display in the screen. To delete record has to be saved. ");
					*/	
						//----------------- Termination - Processing ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p class=ug_headin><a NAME='Termination_Processing'>");
						out.println("</a><u>Finance - Termination - Processing </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Termination procedure will be completed at this level. Termination type can be selected from the drop down menu and the tick box gets activated only when the due amount from the client becomes zero. To save termination that tick box has to be filled and using save option entry can be saved.");
						*/
						//----------------- OD Interest User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p class=ug_headin><a NAME='OD_Interest'>");
						out.println("</a><u>Finance - OD Interest </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option caters to view the OD interest, client wise and facility wise for a selected client. User can select OD status as stop or start and adjust. Then tick and save for complete the process.");
						*/
						//----------------- Finance Activation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						/*out.println("<p class=ug_headin><a NAME='Finance_Activation'>");
						out.println("</a><u>Finance - Finance Activation </u>");
						out.println(" ");
						//out.println("<p align='justify'>This option caters to view the OD interest, client wise and facility wise for a selected client. User can select OD status as stop or start and adjust. Then tick and save for complete the process.");
						*/
						//----------------- Change Capital Allowance Rate User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
					/*	
						out.println("<p class=ug_headin><a NAME='Change_Cap_Allow'>");
						out.println("</a><u>Finance - Change Capital Allowance Rates </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For change capital allowance rate of a leased assets can be used this option. User can search relevant asset, adjust the capital allowance rate and save. For multiple assets, using help option of invoice number field can be selected assets one by one for adjust the capital allowance rate and save.");
					*/	
						//----------------- Security File Movement Entry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p align='justify' class=ug_headin><a NAME='Security_File_Movement'>");
						out.println("</a><u>Finance - Security File Movement Entry </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Movement of security documents in and out of the file must be recorded in the system. Person who wants those documents has to make a request. These requests and return of those documents will be recorded through this screen. This section comprises of following options. ");						
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Request</p>");
						out.println("<p><li class=rep-body>	Return</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>Request");
						out.println("<p align='justify' class=rep-body>In order to enter a request Finance number has to be selected from the help option then relevant Application number, Performa Invoice Number and documents will be displayed in the screen. Reason for the request has to be entered. By marking a tick in the relevant tick box can save the Request using the Save option.");
						out.println("<p align='justify' class=rep-body>Return");
						out.println("<p align='justify' class=rep-body>Approved requests will come for the Return. Return can be saved by marking a tick in the tick box.  ");
						*/
						//----------------- Security File Movement Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p align='justify' class=ug_headin><a NAME='Security_File_Movement_Approval'>");
						out.println("</a><u>Finance - Security File Movement Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Requests and returns entered from Security and Market file movement screen have to be approved. This section comprises of following options. ");						
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Request Approval</p>");
						out.println("<p><li class=rep-body>	Return Approval</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>Request Approval");
						out.println("<p align='justify' class=rep-body>To approve a request, Finance number should be selected from the Help option. Requested documents from that file will be listed in the screen. Invoice number, Document code, Document name, Document status, Requested user and Requested date will be displayed in the screen. To approve the request record can select by marking a tick and entry can be saved using the Save option.");
						out.println("<p align='justify' class=rep-body>Return Approval");
						out.println("<p align='justify' class=rep-body>Saved Returns through the Security and market file movement screen will come to this section for approval. For approval Finance number must be selected. Documents returned to the file bearing selected Finance number will be displayed in the screen. User can select the document and entry can save using the Save option. ");
					*/	
						//----------------- Day End Routine User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						/*out.println("<p align='justify' class=ug_headin><a NAME='Day_End_Routine'>");
						out.println("</a><u>Finance - Day End Routine </u>");
						out.println(" ");
						*/
						//out.println("<p align='justify'>Requests and returns entered from Security and Market file movement screen have to be approved. This section comprises of following options. ");						
		
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
