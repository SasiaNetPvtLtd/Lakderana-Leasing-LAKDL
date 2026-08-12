

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_FA_UG_Operations_new extends javax.servlet.http.HttpServlet { 

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
						//-------------Credit
						out.println("<p class=ug_headin><a NAME='Operations'></a><u>Operations</u> ");
						out.println("<p align='justify' class=rep-body>Once the facility is activated, or in other words, when the factoring facility is operational, the entry you created and have been updating, moves to the Operations module. Here you will be entering details related to payments and invoices, returns and realizations and disputes and comments, etc.  ");

						//-----------------Invoice Process - Invoice Entry ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Invoice_Process_Invoice_Entry'></a><u>Invoice Process - Invoice Entry</u> ");
						out.println("<p align='justify' class=rep-body>When the client's customers, i.e. debtors, make payments, you can generate receipts with the use of this screen. For a given client, you can select a debtor. For a debtor you can enter multiple invoices with different invoice numbers. ");
						
						//-----------------Invoice Process - Invoice Approval (Cash)  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Invoice_Process_Invoice_Approval_cash'></a><u>Invoice Process - Invoice Approval (Cash)</u> ");
						out.println("<p align='justify' class=rep-body>This screen lets you view pending invoices individually or grouped by facility. If you view individually, each pending invoice will be displayed in each row. When grouped by the facility, each facility will be shown in a row in the table. You can see receipt details for any facility you want and approve, reject or take no action for each of the invoice.");

						//-----------------Invoice Process - Invoice Approval (Credit) User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Invoice_Process_Invoice_Approval'>");
						out.println("</a><u>Invoice Process - Invoice Approval (Credit)</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Only difference in this screen and the above screen (Invoice Process - Invoice Approval) is the fact that this screen contains pending receipts for credit payments and the other contains the same for cash payments. You can get someone to follow up by selecting the action Follow up.");
						
						//----------------- Invoice Process - Invoice Confirmation  User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Invoice_Process_Invoice_Confirmation'>");
						out.println("</a><u>Invoice Process - Invoice Confirmation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Approved invoices are subject to confirmation by a senior user. The user, who is authorized to confirm invoices, can use this screen for that task.");
						
						//----------------- Adjustments - Cr/Dr Notes and Reversals - Invoice Adjustments  ----------------------------------------------------------
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Invoice_Adjustments'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Invoice Adjustments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can select a facility and a batch of invoices to make adjustments by way of credit notes or debit notes. ");
						
						
						
						//-------------Credit_Evaluation_Approval
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Invoice_Reassignments'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Invoice Reassignments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you change the responsibility of collection of one or more invoices from you to the client. In the event of existence of invoices that cannot be processed due to reasons such as return of goods, etc, you can reassign those invoices back to the client. ");
						
						///-------------------Adjustments - Cr/Dr Notes and Reversals - Client Adjustments
						
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Client_Adjustments'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Client Adjustments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Based on the performance of clients, you can change the maximum invoice amounts upwards or downwards. After selecting a client and a facility, you can adjust the limit by way of a credit note or a debit note.");
						
						//------------------------Adjustments - Cr/Dr Notes and Reversals - Adjustments - Approval
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Adjustments_Approval'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Adjustments - Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Adjustments that are made previously have to be approved before they become effective. You can select different types of adjustments, such as Invoice Adjustments, Invoice Reassignments and Client Adjustments and approve adjustments that should be approved.");
                         
						//---------------------------Adjustments - Cr/Dr Notes and Reversals - Charges Addition
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Charges_Addition'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Charges Addition</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Although you specify the amounts to be charged for each fee within the fee package originally, you can change the amounts or add new fees that the client has to bear. This screen lets you select fee types within the package and lets you change the value once you specify the facility.");
					
					//-----------------------Adjustments - Cr/Dr Notes and Reversals - Charges Reversal
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Charges_Reversal'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Charges Reversal </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Previously changed amounts for fees within the fee package can be reversed with the use of this screen. It lets you select the client and the facility and the fee to be reversed.");

                     //---------------Activate Client-Debtor
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Non_Sales_and_Excess_Funds_Transfer'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Non Sales and Excess Funds Transfer</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A Non-sale is an instance where the client tries to factor an invoice that does not belong to a true sale (such as return of goods). Excess funds arise when debtors pay more than expected amount, particularly in the event where the client does not factor the invoice but the debtor pays unknowingly to you. In the event of the former, the amount will be charged back from the client by way of a debtor and when the latter occurs, the amount will be directly debited to the client's account. This screen helps you to make such entries.");
					//-----------------Facility Activation
					
						out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Cheque_Return_Transfer'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Cheque Return Transfer</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body> Through this screen a new cheque which is brought to replace a returned cheque can be entered. ");

            out.println("<p class=ug_headin><a NAME='Adjustments_Cr/Dr_Notes_and_Reversals_Non_Sales_and_Excess_Funds_Transfer_Approval'>");
						out.println("</a><u>Adjustments - Cr/Dr Notes and Reversals - Non Sales and Excess Funds Transfer Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Entries that are made related to Non-sales or Excess funds have to be approved in order to activate them. This screen is to approve or disapprove such entries. ");
					
					//-------------------------------P D and Receipt Entry - PD Cheque Entry and Allocation 
            out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_PD_Cheque_Entry_and_Allocation'>");
						out.println("</a><u>P D and Receipt Entry - PD Cheque Entry and Allocation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can feed details of post dated cheques into the system here. You can directly allocate these post dated cheques to invoices, which is the default method, allocate them to debtors on request of debtors or to clients at termination or any other special event.  ");
						
					//------------------P D and Receipt Entry - PD Cheque Receipt Generation
						out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_PD_Cheque_Receipt_Generation'>");
						out.println("</a><u>P D and Receipt Entry - PD Cheque Receipt Generation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can view post dated cheques that are in the system within a specified time period. On the cheque dates you can bank those cheques while generating receipts to send to debtors acknowledging payments. ");
					
					//-------------P D and Receipt Entry - Settlement Receipt Entry
					
						out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_Settlement_Receipt_Entry'>");
						out.println("</a><u>P D and Receipt Entry - Settlement Receipt Entry</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the debtors of the clients settle their invoices, you can generate receipts for those settlements here. Such settlements can be assigned to clients, debtors or specific invoices on request of the debtor. After specifying the client, facility and the debtor, you can make an entry of the settlement amount. Redeposit information related to settlement receipts also can be fed into the system with the use of this screen. ");
                    //----------------------------P_D_and_Receipt_Entry_Settlement_Receipt_Approval----------
          	out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_Settlement_Receipt_Approval'>");
						out.println("</a><u>P D and Receipt Entry - Settlement Receipt Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Post dated cheques and settlement receipts that are entered through the system should be approved as the next step of data entry. This screen helps you to approve or disapprove such cheques and settlement receipts. ");
					
						out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_Settlement_Deposit'>");
						out.println("</a><u>P D and Receipt Entry - Settlement Deposit</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Approved settlement receipts are ready to be deposited in bank accounts. You simply can select the relevant account number, bank and the branch and deposit receipts in bulk. ");
					
					//--------------------P_D_and_Receipt_Entry_Return/Realization
						out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_Return_Realization'>");
						out.println("</a><u>P D and Receipt Entry - Return/Realization</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You need to select a bank account to view cheques deposited to that account. The list of cheques deposited can be processed further by selecting whether Returned or Realized. If the cheque is dishonored due to a reason such as lack of fund availability in the payer's account, you will have to select Return. If the cheque is successfully converted into cash, you need to select Realized. ");
					//-----------------P_D_and_Receipt_Entry_Cheque_Return_Settlement
						out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_Cheque_Return_Settlement'>");
						out.println("</a><u>P D and Receipt Entry - Cheque Return Settlement</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>By selecting the facility and the client, and then the debtor details, you can make an entry of a settlement that has to be made due to a return of a cheque. You can accept partial payments also as such returned cheque settlements.");
					
					//----------------P_D_and_Receipt_Entry_Cheque_Re_Banking
						out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_Cheque_Re_Banking'>");
						out.println("</a><u>P D and Receipt Entry - Cheque Re-Banking</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Cheque return settlements that are made by debtors have to be re-banked. This screen lists down such settlements from which you can select payments to re-bank.  ");
					
					//----------------------P_D_and_Receipt_Entry_Invoice_Allocation
						out.println("<p class=ug_headin><a NAME='P_D_and_Receipt_Entry_Invoice_Allocation'>");
						out.println("</a><u>P D and Receipt Entry - Invoice Allocation</u>");
						out.println(" ");
						
						out.println("<p align='justify' class=rep-body>Debtor payments can be allocated to facilities in three levels. First, you can broadly assign such payments to clients without selecting the debtor or the invoice. Second, you can assign them to debtors ");
					
					//------------------Client Payments - Client Availability
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Availability'>");
						out.println("</a><u>Client Payments - Client Availability</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen assists you to identify the limit of the amount the client should be paid at any particular time. For this, the system considers the invoice availability and the current account balance. The difference of those two is considered as Client Availability. This calculation considers the Active Invoice Batches (invoices that are within the tolerance period) and Due Date Exceeded Invoices (invoices that exceed the tolerance period) separately. ");
					
					//------------------Client Payments - Client Payments
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Payments'>");
						out.println("</a><u>Client Payments - Client Payments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When you make payments to the client, you have to record details of those payments here. You have to select the facility and the client, whether a cash payment or a cheque, and the account to which the amount has to be deposited has to be selected. ");
                        //------------------Client Payments - Client Payment Approval
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Payment_Approval'>");
						out.println("</a><u>Client Payments - Client Payment Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Before the client receives the payments, previously entered client payment details should be approved by two users. This screen lets you give the first approval for such client payments. You can select the record you want to approve and approve, disapprove or leave it as it is as a pending approval. ");
					
					//------------------Client Payments - Client Payment Higher Approval
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Payment_Higher_Approval'>");
						out.println("</a><u>Client Payments - Client Payment Higher Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Similar to the first approval, this screen lets you give the final approval for a client payment entry.  ");
					
					
					//------------------Client Payments - Client Payment Confirmation
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Payment_Confirmation'>");
						out.println("</a><u>Client Payments - Client Payment Confirmation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>In order to activate a payment to a client, that has to be confirmed by a senior user. This is the screen that lets you confirm a payment. ");
					
					//------------------Client Payments - Cheque Print
						out.println("<p class=ug_headin><a NAME='Client_Payments_Cheque_Print'>");
						out.println("</a><u>Client Payments - Cheque Print</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>All confirmed cheque payments for clients are listed in this screen. You can select the payments you want to proceed and print the cheques for those payments. ");

                        //-------------Client_Payments_Cheque_Disbursement
						out.println("<p class=ug_headin><a NAME='Client_Payments_Cheque_Disbursement'>");
						out.println("</a><u> Client Payments - Cheque Disbursement</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When you issue cheques to your clients for the invoices they produce, this is the screen you should make an entry for them. ");
					
						out.println("<p class=ug_headin><a NAME='Client_Payments_Debtor_Performance'>");
						out.println("</a><u>Client Payments - Debtor Performance</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Through this screen a summarized view of the overall debtor performance can be viewed.  ");
					
						out.println("<p class=ug_headin><a NAME='Client_Payments_Debtor_Performance'>");
						out.println("</a><u>Client Payments - Debtor Performance</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Through this screen a summarized view of the overall debtor performance can be viewed.  ");
					
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Payment_Realization'>");
						out.println("</a><u>Client Payments - Client Payment Realization</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the cheques, you issue to clients, are realized, you can enter realization dates of the cheques here. All disbursed cheques are listed here and you can select the records of which you know the realization dates to update.  ");
					
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Payment_Realization'>");
						out.println("</a><u>Client Payments - Client Payment Realization</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the cheques, you issue to clients, are realized, you can enter realization dates of the cheques here. All disbursed cheques are listed here and you can select the records of which you know the realization dates to update.  ");
					//----------------Client_Payments_Client_Availability_Performance
						out.println("<p class=ug_headin><a NAME='Client_Payments_Client_Availability_Performance'>");
						out.println("</a><u>Client Payments - Client Availability Performance</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen gives you a snapshot of the client's account for the last 6 months. It is a summary of all activities related to the facility you select. ");
					
						out.println("<p class=ug_headin><a NAME='Cheque_Return_Reminders_Reminder'>");
						out.println("</a><u>Cheque Return Reminders - Reminder</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Within a period of time you specify, you can view the list of dishonored cheques. For these cheques you can send reminder letters to debtors of your clients, requesting to make payments. You can send three types of letters as Initial Cheque Return Reminder, Final Cheque Return Reminder Without Remark and Final Cheque Return Reminder With Remark.  ");
					
						out.println("<p class=ug_headin><a NAME='Cheque_Return_Reminders_Legal_Letter'>");
						out.println("</a><u>Cheque Return Reminders - Legal Letter</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>In the event of failure to settle payments for dishonored cheques, you will need to send letters to debtors of the clients informing about the legal actions you wish to take and implications. This screen helps you to generate such letters including all necessary daa such as amount to be paid, due date, etc.  ");
           
						out.println("<p class=ug_headin><a NAME='Invoice_Reminders'>");
						out.println("</a><u>Invoice Reminders</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here, once you specify the date range you will see a list of debtors for active facilities with outstanding payments within the given period of time.  You can generate reminders for required debtors by clicking the Reminder button. You also can hold invoices for debtors if necessary. Through this screen you can print invoices in bulk. ");
                              
						out.println("<p class=ug_headin><a NAME='Client_Statements_Statement'>");
						out.println("</a><u>Client Statements - Statement</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can find different types of documents that are to be sent to clients at different occasions, which are related to clients. ");
					
					 	out.println("<p class=ug_headin><a NAME='Client_Statements_Generate_Tax_Invoice'>");
						out.println("</a><u>Client Statements - Generate Tax Invoice</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The tax invoices which are to be sent to clients can be printed through this screen. ");
					//-------------Client_Statements_CRIB_Information_Update_Report
            out.println("<p class=ug_headin><a NAME='Client_Statements_CRIB_Information_Update_Report'>");
						out.println("</a><u>Client Statements - CRIB Information Update Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Every month you need to send a report containing your client and debtor information to the Credit Information Bureau (CRIB) of Sri Lanka. This screen lets you select the month and the year and generates an updated document with the data in the system. You only have to sign the document and send to the CRIB. ");
                         
						 //-----------Client Disputes
						out.println("<p class=ug_headin><a NAME='Client_Disputes'>");
						out.println("</a><u>Client Disputes</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can use this screen to enter comments about clients. Details that are expected to be entered are facts such as the accuracy of invoices, high level of stock returns, etc. ");
					//------------Client/Debtor_Credit_Comments
					 	out.println("<p class=ug_headin><a NAME='Client/Debtor_Credit_Comments'>");
						out.println("</a><u>Client/Debtor Credit Comments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Comments for a debtor can be placed using this screen. This is only a memorandum record. ");
					
					//------------Cancel Cheques
					
						out.println("<p class=ug_headin><a NAME='Cancel_Cheques'>");
						out.println("</a><u>Cancel Cheques</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>If during the receipt generation process a receipt is disapproved for a PD cheque receipt the process of canceling the cheque can be done through this screen. ");
					//-----------Return Cheques
						out.println("<p class=ug_headin><a NAME='Return_Cheques'>");
						out.println("</a><u>Return Cheques</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When a cheque gets returned through this screen the return cheque details can be entered. ");
					
					//----------Receipt_Bulk_Printing
						out.println("<p class=ug_headin><a NAME='Receipt_Bulk_Printing'>");
						out.println("</a><u>Receipt Bulk Printing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you print all payment receipt details within a specific date range. You have to specify the start date and the end date of the period and print the receipts in bulk. ");
					
					//-----------Invoice Review
						out.println("<p class=ug_headin><a NAME='Invoice_Review'>");
						out.println("</a><u>Invoice Review</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>If there is a change to be made in the debtor's invoice, through this screen it can be sent back for changes ");
					
					//-----------Invoice Review
						out.println("<p class=ug_headin><a NAME='Charges_Hold'>");
						out.println("</a><u>Charges Hold</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When a charge which gets applied at day-end or month-end routines needs to be held for a particular routine it can be done using this screen ");
					
						out.println("<p class=ug_headin><a NAME='Factoring_Finance_Interest_Rate'>");
						out.println("</a><u>Factoring Finance Interest Rate</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This rate is the interest rate you will maintain for your factoring service. You can select a new interest rate and the year and month from which the new rate is effective.");


















						

						
					
		
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
