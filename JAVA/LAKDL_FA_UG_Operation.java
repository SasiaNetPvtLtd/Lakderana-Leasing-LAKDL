

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_FA_UG_Operation extends javax.servlet.http.HttpServlet { 

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
						out.println("<body text='#000000' bgcolor='#FFFFFF' link='#0000FF' vlink='#800080' alink='#FF0000'>");
						
						//-----------------Invoice Process User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Operation' ></a><u>3. Operation</u> ");
						out.println("<p align='justify' class=rep-body>Since invoice verification, payment and collection process, Operation process is a vital stage of factoring process. More than eighty percent from total activities are covered by operation process. Following are sub processes of operation process.");
						out.println(" ");
						out.println("<p class=ug_headin><a NAME='Invoice_Process'></a><u>3.1 Invoice Process</u> ");
						out.println("<p align='justify' class=rep-body>This process consists of following sub process.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Enter' target='help-right'>	Invoice Enter </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Approval' target='help-right'>	Invoice Approval </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Confirmation' target='help-right'>	Invoice Confirmation </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Approval_Credit' target='help-right'>	Invoice Approval-Credit </a></p>");
						out.println("</blockquote>");
						
						out.println("<p class=ug_headin><a NAME='Invoice_Enter'></a><u>3.1.1 Invoice Enter</u> ");
						out.println("<p align='justify' class=rep-body>This option is used to enter invoices and allocate those invoices to client. This option consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>In order to enter new invoice can be used this option. Before enter invoice information, select client details and ensure accuracy of that information. Then select relevant debtor, enter invoice details and add that invoice to the invoice list. If next invoice for the same debtor change other fields except debtor code and debtor name. If next invoice for another debtor, clear second phase of the screen using clear option and enter information and save. If user want to delete or edit invoice details before saved use edit and delete option of relevant invoice field in the list. Total invoice amount, invoice batch number and number of invoices fields in the first phase of the screen automatically fill and invoice batch number automatically generates. When saving information invoice date, credit limit of client and invoice number are automatically validated and the comment appears in red.");
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>This option is used to modify invoice information. Using Edit >> Help option of batch no select/enter invoice batch details, modify and save again.");
						
						out.println("<p class=ug_headin><a NAME='Invoice_Approval'></a><u>3.1.2 Invoice Approval</u> ");
						out.println("<p align='justify' class=rep-body>This option is used to approve an invoice. In that invoice can be filtered batch wise or invoice wise. Under batch wise option has to be select invoices using show invoices option. Through facility no, client name and debtor name can be obtain more details."+
												"	Select using arrow under action heading the decision and save."+
												" ");						
						out.println("<p class=ug_headin><a NAME='Invoice_Confirmation'></a><u>3.1.3 Invoice Confirmation</u> ");
						out.println("<p align='justify' class=rep-body>Invoice confirmation is the second approval level of invoice approval. In that, actions which are made regarding invoices approved and disapproved at first approval level can be recorded.Invoiceses can be viewed batch wise and invoice wise yet in approval, actions should be made invoice wise.");
						out.println("<p class=ug_headin><a NAME='Invoice_Approval_Credit'></a><u>3.1.4 Invoice Approval - Credit</u> ");
						out.println("<p align='justify' class=rep-body>This is a special approval level for invoices which are in out of set up critirions.Those invoices can be approved at this level.");

						//-----------------Adjustments - CR/DR Notes User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Adjustments'></a><u>3.2 Adjustments - CR/DR Notes</u> ");
						out.println("<p align='justify' class=rep-body>Invoice adjustment means that to make adjustments to the current value of the invoice. This option consists of following sub processes.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Adjustments' target='help-right'>	Invoice Adjustments </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Adjustments_Approval' target='help-right'>	Adjustments- Approval </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Adjustments' target='help-right'>	Client Adjustments </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Reassignment' target='help-right'>	Invoice Reassignments </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Charges_Reversal' target='help-right'>	Charges Reversal </a></p>");
						out.println("</blockquote>");
						
						out.println("<p class=ug_headin><a NAME='Invoice_Adjustments'></a><u>3.2.1 Invoice Adjustments</u> ");
						out.println("<p align='justify' class=rep-body>This option is used to adjust the value (only for reducing invoice value) of an already approved invoice. Select client, relevant invoice batch, debtor and invoice. Then change the invoice value and save. Successfully adjusted invoice is shifted to adjustments approval level.");
						
						
						out.println("<p class=ug_headin><a NAME='Adjustments_Approval'></a><u>3.2.2 Adjustments Approval</u> ");
						out.println("<p align='justify' class=rep-body>Adjustment approval option can be used to make actions for adjustments done at client adjustments, Invoice adjustments and invoice adjustment levels.");
						
						out.println("<p class=ug_headin><a NAME='Client_Adjustments'></a><u>3.2.3 Client Adjustments</u> ");
						out.println("<p align='justify' class=rep-body>This option is used to adjust client facility limit of an already approved facility. Select client using help option and enter the adjustment. Then select the adjustment type as credit or debit and save.");
						out.println("<p class=ug_headin><a NAME='Invoice_Reassignment'></a><u>3.2.4 Invoice Reassignments</u> ");
						out.println("<p class=ug_headin><a NAME='Charges_Reversal'></a><u>3.2.5 Charges Reversal</u> ");
						
						//----------------- PD & Receipt Entry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='PD_Receipt'></a><u>3.3 PD & Receipt Entry</u> ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#PD_Enter_Allo' target='help-right'>	PD Cheque Enter & Allocation </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Settle_Receipt_Entry' target='help-right'> Settlement Receipt Entry </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Settle_Schedule_Entry' target='help-right'>	Settlement Schedule Entry </a></p>");
						out.println("</blockquote>");
						out.println("<p class=ug_headin><a NAME='PD_Enter_Allo'>");
						out.println("</a><u>3.3.1 PD Cheques Enter and Allocation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option can be used to record post dated cheque details for future actions and temporary allocate PD amount for invoices."+
												"Allocation steps: "+
												"Facility >Debtor > Invoice "+
												"");
						out.println("<p class=ug_headin><a NAME='Settle_Receipt_Entry'>");
						out.println("</a><u>3.3.2 Settlement Receipt Entry </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Settlement Receipt Entry is an optional screen for cheque and cash without invoice schedule. In that, receipts can be entered client wise, debtor wise and invoice wise. 4.8 Settlement Receipts Approval Receipts entered through settlement schedule and settlement receipt entry options can be approved by using this option.");
						out.println("<p class=ug_headin><a NAME='Settle_Schedule_Entry'>");
						out.println("</a><u>3.3.3 Settlement Schedule Entry </u>");
						out.println("<p align='justify' class=rep-body>This option can be used to enter cheque or cash amounts with invoice schedules. First step is select receipt mode, bank account and debtor code.  User can view the list of invoices and allocation can be made in accordance with the presented schedule. Modifications of allocations can be done through edit option.");
						out.println(" ");
						
						//-----------------PD Cheque Receipt Generation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='PD_Receipt_Gen'></a><u>3.4 PD Cheque Receipt Generation</u> ");
						out.println("<p align='justify' class=rep-body>Before deposit receipt should be generated for PD cheques and presented for deposit. This option can be used to generate receipt and forward to deposit level through realise action and save. User can filter PD cheque for specific date range.");
						out.println(" ");
						
						//-----------------Settlement Receipt Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Settle_Receipt_Approval'></a><u>3.5 Settlement Receipt Approval</u> ");
						//out.println("<p align='justify'>Before deposit receipt should be generated for PD cheques and presented for deposit. This option can be used to generate receipt and forward to deposit level through realise action and save. User can filter PD cheque for specific date range.");
						out.println(" ");
						
						//-----------------Settlement Deposit User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Settle_Deposit'></a><u>3.6 Settlements Deposit</u> ");
						out.println("<p align='justify' class=rep-body>All receipts can be deposited using settlement deposit option. User should select relevant licensee account and deposit receipts from pending deposit list.");
						out.println(" ");
						
						//-----------------Return & Realisation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Return_Realisation'></a><u>3.7 Return/Realisation </u> ");
						out.println("<p align='justify' class=rep-body>Status of deposited cheques can be entered through this option base on bank statement.");
						out.println(" ");
						
						//-----------------Invoice Allocation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Invoice_Allocation'></a><u>3.8 Invoice Allocation </u> ");
						out.println("<p align='justify' class=rep-body>This option can be used for permanent invoice allocation. Default allocation is FIFO allocation and user can reallocate in accordance with requirements.");
						out.println(" ");

						//-----------------Client Payments User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payments'></a><u>3.9 Client Payments</u> ");
						out.println("<p align='justify' class=rep-body>Client payments consist of following sub options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Availability' target='help-right'>	Client Availability </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment' target='help-right'>	Client Payment </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment_Approval' target='help-right'>	Client Payment Approval </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment_Confirmation' target='help-right'>	Client Payment Confirmation </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment_Higher_Approval' target='help-right'>	Client Payment Higher Approval </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Cheque_Printing' target='help-right'>	Cheque Print </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Cheque_Disbursement' target='help-right'>	Cheque Disbursement </a></p>");
						out.println("</blockquote>");
						
						out.println("<p class=ug_headin><a NAME='Client_Availability'></a><u>3.9.1 Client Availability</u> ");
						out.println("<p align='justify' class=rep-body>Client availability screen is used for obtaining the maximum and minimum possible amount that can be paid to client. In that client invoice availability is compared with client current account balance and net figure is identified as client availability.");
						out.println("<p align='justify' class=rep-body>Through Help option in client code field can be selected relevant client. Then facility number can be selected using help option in facility number field. Client name can be used for searching client and facility number for filtering facility to the screen.");
						out.println("<p align='justify' class=rep-body>Through Display option can be obtain information with regard to selected client and facility.");
						out.println("<p align='justify' class=rep-body>Active invoice batches table consists of two types of invoices that invoice within tolerance period and invoices exceeded tolerance period but in second and third cycle. Inactive batches table shows the value of invoices that exceeding second and third cycle after tolerance date and these invoices are not considered in calculating client availability.");
						out.println("<p align='justify' class=rep-body>1. Active Invoice Batches Total");
						out.println("<p align='justify' class=rep-body>Active invoice batches total is the total value of invoices that are within tolerance period.This total is obtained without considering funding limits and availability of debtors.");
						out.println("<p align='justify' class=rep-body>2. Due Date Exceeded Invoice Total");
						out.println("<p align='justify' class=rep-body>Due date exceeded invoice total is the total value of invoices that exceeded tolerance date but in second and third cycle. Both Active invoice batches total figure and due date exceeded invoice total are reference figures.");
						out.println("<p align='justify' class=rep-body>3. Gross Payable Amount");
						out.println("<p align='justify' class=rep-body>Gross payable amount is the value of invoices that are within tolerance period. This total is obtained after considering debtor funding limit and availability.");
						out.println("<p align='justify' class=rep-body>4. Payable Amount From Due");
						out.println("<p align='justify' class=rep-body>Payable amount from due figure means the total of invoices that exceeding tolerance period but in second and third cycles, after considering debtor funding limit and availability.");
						out.println("<p align='justify' class=rep-body>5. Net Payable Amount");
						out.println("<p align='justify' class=rep-body>Net figure can be obtained after subtracting payable amount from due from gross payable amount. (3-4) = 5");
						out.println("<p align='justify' class=rep-body>6. Unsettle Cheque Return");
						out.println("<p align='justify' class=rep-body>Unsettle cheque return figure is a reference figure but in calculating minimum invoice payable amount, this figure is taken in to consideration.");
						out.println("<p align='justify' class=rep-body>7. Minimum Invoice Payable Amount");
						out.println("<p align='justify' class=rep-body>Minimum invoice payable amount is the net figure obtained after reduce unsettle cheque return from net payable amount. Minimum invoice payable amount is compared with client's current account balance for final availability calculation.");
						out.println("<p align='justify' class=rep-body>8. Current   Account");
						out.println("<p align='justify' class=rep-body>Current account is displaying summary of all transactions done with client for a specific time period. Following figures can be identified under current account. Total charges, payments to client, tax charges and interest charges increase current account debit balance while receivables and unallocated funds reduced client debit balance.");
						out.println("<p align='justify' class=rep-body>8.1 Opening Balance");
						out.println("<p align='justify' class=rep-body>Balance bought forward from previous month after running month end routine. If client is new client, opening balance is zero.");
						out.println("<p align='justify' class=rep-body>8.2 Total Charges");
						out.println("<p align='justify' class=rep-body>Total value of charges that relate to offered facility.");
						out.println("<p align='justify' class=rep-body>8.3 Tax Charges");
						out.println("<p align='justify' class=rep-body>Tax amount charged from client.");
						out.println("<p align='justify' class=rep-body>8.4 Total Pending Payments");
						out.println("<p align='justify' class=rep-body>Total value of payment in pending level.");
						out.println("<p align='justify' class=rep-body>8.5 Total Payments");
						out.println("<p align='justify' class=rep-body>Total of approved and confirmed payments to client.");
						out.println("<p align='justify' class=rep-body>8.6 Interest ");
						out.println("<p align='justify' class=rep-body>Interest due from client.");
						out.println("<p align='justify' class=rep-body>8.7 Receivables From Active Invoices");
						out.println("<p align='justify' class=rep-body>Receivables for invoices in active batches.");
						out.println("<p align='justify' class=rep-body>8.8 Receivables From Inactive Invoices");
						out.println("<p align='justify' class=rep-body>Receivables for invoices in inactive batches.");
						out.println("<p align='justify' class=rep-body>9. Cheque Bank Pending Realisation");
						out.println("<p align='justify' class=rep-body>Amount of cheque that are deposited but not realised by the bank yet.");
						out.println("<p align='justify' class=rep-body>10. Net Exposure");
						out.println("<p align='justify' class=rep-body>Net exposure is obtained by reducing pending realization cheque amount from current account closing balance.");
						out.println("<p align='justify' class=rep-body>11. Minimum Payable Amount");
						out.println("<p align='justify' class=rep-body>Client availability without considering the total amount of pending realisation cheques and before considering client credit limit.");
						out.println("<p align='justify' class=rep-body>12. Maximum Payable Amount");
						out.println("<p align='justify' class=rep-body>Client availability after considering the total amount of pending realization cheques and before considering client credit limit ");
						out.println("<p align='justify' class=rep-body>Figures under additional information are reference figures and actual client maximum availability is calculated after considering client credit limit.");
						
						out.println("<p class=ug_headin><a NAME='Client_Payment'></a><u>3.9.2 Client Payments</u> ");
						out.println("<p align='justify' class=rep-body>This option is used to request a payment for a client. Payment can be requested for a facility wise and client availability option can be used to obtain availability details before request payment. After entering date, amount and licensee account basically, payment requisition can be done. Using Edit option, existing requests can be modify before approval.");
						
						out.println("<p class=ug_headin><a NAME='Client_Payment_Approval'></a><u>3.9.3 Client Payment Approval</u> ");
						out.println("<p align='justify' class=rep-body>This option is used to approve a payment requisition. Payment can be filtered client wise or all requisition for approval.");
						out.println("<p class=ug_headin><a NAME='Client_Payment_Confirmation'></a><u>3.9.4 Client Payment Conformation</u> ");
						out.println("<p align='justify' class=rep-body>This is the second approval level of payment process.Aprroved payment requisition can be confirmed using this screen.");
						out.println("<p class=ug_headin><a NAME='Client_Payment_Higher_Approval'></a><u>3.9.5 Client Payment Higher Approval</u> ");
						out.println("<p align='justify' class=rep-body>Higher approval is an additional approval level for approving client payments.Availbility sub option can be used to view selected client availability details.");
						out.println("<p class=ug_headin><a NAME='Cheque_Printing'></a><u>3.9.6 Cheque Printing</u> ");
						out.println("<p align='justify' class=rep-body>This option can be used to print cheque for approved payments.");
						out.println("<p class=ug_headin><a NAME='Cheque_Disbursement'></a><u>3.9.7 Cheque Disbursement</u> ");
						out.println("<p align='justify' class=rep-body>Printed cheques can be disbursed using this option.");
						
						//----------------- Cheque Return Reminders User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Return'></a><u>3.10 Cheque Return Reminders</u> ");
						out.println("<p align='justify' class=rep-body>Cheque return reminders can be segregated as follows.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Initial_Cheque' target='help-right'>	Initial Cheque Return Reminder </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Final_Cheque_Without' target='help-right'>	Final Cheque Return Reminder(Without Remark) </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Final_Cheque_With' target='help-right'>	Final Cheque Return Reminder(With Remark) </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Cheque_Return_Reminder' target='help-right'>	Cheque Return Reminder </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Legal_Letter' target='help-right'>	Legal Letter </a></p>");
						out.println("</blockquote>");
						out.println("<p class=ug_headin><a NAME='Initial_Cheque'></a><u>3.10.1 Initial Cheque Return Reminder</u> ");
						out.println("<p align='justify' class=rep-body>This screen views details of return cheques that are in initial level. User can generate letter using Letter option for return cheque.");
						out.println("<p class=ug_headin ><a NAME='Final_Cheque_Without'></a><u>3.10.2 Final Cheque Return Reminder (Without Remark)</u> ");
						out.println("<p align='justify' class=rep-body>This option can be used to generate final reminder letter without remark for return cheques when there is a no respond for initial cheque return reminder.");
						out.println("<p class=ug_headin><a NAME='Final_Cheque_With'></a><u>3.10.3 Final Cheque Return Reminder (With Remark)</u> ");
						out.println("<p align='justify' class=rep-body>This option can be used to generate final reminder letter with remark for return cheques when there is a no respond for initial cheque return reminder. Remark option can be used to enter the remark.");
						out.println("<p class=ug_headin><a NAME='Cheque_Return_Reminder'></a><u>3.10.4 Cheque Return Reminder</u> ");
						out.println("<p align='justify' class=rep-body>This is an additional option to generate reminders. User can view details with regard to return cheques selected time range and letters that sent or pending to sent. ");
						out.println("<p class=ug_headin><a NAME='Legal_Letter'></a><u>3.10.5 Legal Letter</u> ");
						out.println("<p align='justify' class=rep-body>When there is no respond to cheque return reminders, user can generate the legal letter using this option.");
						
						//----------------- Invoice Reminders User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Invoice_Remider'></a><u>3.11 Invoice Reminders</u> ");
						out.println("<p align='justify' class=rep-body>Following reminders are sent for due invoices.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Pre' target='help-right'> Pre Reminder </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#First' target='help-right'> 1st Reminder </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Second' target='help-right'> 2nd Reminder </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Final' target='help-right'> Final Reminder </a></p>");
						out.println("</blockquote>");
						out.println("<p class=ug_headin> <a NAME='Pre'></a><u>3.11.1 Pre Reminder</u> ");
						//out.println("<p align='justify'>This screen views details of return cheques that are in initial level. User can generate letter using Letter option for return cheque.");
						out.println("<p class=ug_headin><a NAME='First'></a><u>3.11.2 1st Reminder</u> ");
						//out.println("<p align='justify'>This option can be used to generate final reminder letter without remark for return cheques when there is a no respond for initial cheque return reminder.");
						out.println("<p class=ug_headin><a NAME='Second'></a><u>3.11.3 2nd Reminder</u> ");
						//out.println("<p align='justify'>This option can be used to generate final reminder letter with remark for return cheques when there is a no respond for initial cheque return reminder. Remark option can be used to enter the remark.");
						out.println("<p class=ug_headin><a NAME='Final'></a><u>3.11.4 Final Reminder</u> ");
						//out.println("<p align='justify'>This is an additional option to generate reminders. User can view details with regard to return cheques selected time range and letters that sent or pending to sent. ");
						
						//----------------- Client Statements User Guide ------------------------------------------------------------------------------------------------------------------------------------------------

						out.println("<p class=ug_headin><a NAME='Client_Statements'></a><u>3.12 Client Statements</u> ");
						//out.println("<p align='justify'>Following reminders are sent for due invoices.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_State' target='help-right'> Client Statement </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Tax_Invoice' target='help-right'> Generate Tax Invoice </a></p>");
						out.println("</blockquote>");
						out.println("<p class=ug_headin><a NAME='Client_State'></a><u>3.12.1 Client Statement </u> ");
						//out.println("<p align='justify'>This screen views details of return cheques that are in initial level. User can generate letter using Letter option for return cheque.");
						out.println("<p class=ug_headin><a NAME='Tax_Invoice'></a><u>3.12.2 Generate Tax Invoice </u> ");
						//out.println("<p align='justify'>This option can be used to generate final reminder letter without remark for return cheques when there is a no respond for initial cheque return reminder.");

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
