

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Finance extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Finance -  Payment Requisition Generation  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Requisition_Gen'></a><u>Finance - Payment Requisition - Generation</u> ");
						out.println("<p align='justify' class=rep-body>Payment requisition is the request done by the Credit department to Finance department to make the payment to Supplier.");
						out.println("<p align='justify' class=rep-body>Details of the payment are listed according to the Purchase Order No, Finance No and Application No. To make the request user has to view the details of the payment on click of view option. In that section account number from which the cheque is drawing, Engine, Chassis, Registration number of the vehicle, Details about the Revenue License and status of the required documents can be recorded.");
						out.println("<p align='justify' class=rep-body>If there is a difference between the engine, chassis and vehicle numbers which were recorded in the Pro-forma Invoice will go to a Temporary approval level.");
						out.println("<p align='justify' class=rep-body>Any conditions entered in previous stages will be displayed at this level and if user wants new conditions can be added. Using the link available from Follow up, status of the existing conditions can be changed.");
						out.println("<p align='justify' class=rep-body> After completing the request Payment will be directed for the approval. Documents which are not received at this level will go to Follow up -Outstanding Activity Report as a pending activity for future follow up.");
						
						//-----------------Finance -  Payment Requisition Special Approval  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Requisition_Approval'></a><u>Finance - Payment Requisition - Special Approval</u> ");
						out.println("<p align='justify' class=rep-body>When there is a difference between engine, chassis numbers and the vehicle number entered in Proforma Invoice with the details recorded in tax invoice that record will come to this approval level. Person who is approving the payment can access to details about the data entered in Performa Invoice level from the View option. User can mark the tick box in front of the record which is going to approve and can save the record. ");

						//----------------- Payment Approval 1 User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Approval1'>");
						out.println("</a><u>Finance - Payment Approval 1 </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After the Payment Requisition is done, upon receipt of Certificate of Registration stating the absolute ownership to the Finance company, Revenue License, Tax invoice etc, payment will be processed to the system. Payment will be approved for the first time here. This section consists of following options.");
						out.println("<blockquote>");
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
						
						//----------------- Payment Approval 2 User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Payment_Approval2'>");
						out.println("</a><u>Finance - Payment Approval 2 </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Payments got approved in approval 1 section will come to this level. To approve the payment user can select the record and then entry can be saved. Person who is approving the payment can check the necessary details through the View option. After the approval cheque can be printed.");
						
						//----------------- Other Payment User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Other_Payment'>");
						out.println("</a><u>Finance - Other Payments </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Other payments such as seizer payments can be done through Other Payment Screen.");

						//----------------- Cheque Printing User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Printing'>");
						out.println("</a><u>Finance - Cheque Printing </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>To print the cheques first user has to select the account from which the cheque is drawn. Then the relevant branch details will automatically gets filled and list of cheques to be printed will appear on the screen.");
						out.println("<p align='justify' class=rep-body>If the payer name is different from the person to whom the Purchase order is released user can make the Payee Category as 3rd party & can change the Payer name.");
						out.println("<p align='justify' class=rep-body>Cheque to be printed can be selected by marking the relevant tick box and upon saving cheque will be printed.");

						//----------------- Cheque Disbursement User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Disbursement'>");
						out.println("</a><u>Finance - Cheque Disbursement </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After disbursing the cheque, user can disburse it from the system while recording the NIC no and the name of the person to whom it disbursed, posting details (If necessary) and the cheque number. After entering the record can save the record using the Save option.");
						
						//----------------- Cheque Cancellation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Cancellation'>");
						out.println("</a><u>Finance - Cheque Cancellation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Printed cheques can be cancelled from this section. Printed cheque will be hold only for 7 days and after 7 days it will be cancelled. Cheques which are hold for more than 7 days will come to this section. ");
						out.println("<p align='justify' class=rep-body>User can first select the relevant account from which cheque is drawn. Then cheques which are hold for more than 7 days and drawn from that account will be shown. User can select the relevant cheque by marking the relevant tick box and then can save the record using the Save option. Once the cancellation is saved cheque will return to printing stage.");
						
						//----------------- Invoice Adjustments User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Invoice_Adjustments'>");
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
						//out.println("<p align='justify'>In order to calculate termination value for an application, Termination date and client name can be selected from the Help option. Upon selection of client name existing application numbers for that client will come for the Help box and user can select the relevant Application number. Then the Client name, rate offered at Pricing level, outstanding amount if any, and number of termination calculations have been done will get updated. If there is any other charges relevant for termination can be entered to other charges field. After entering termination date and the rate user can press on Calculate option to do the calculation. ");
						//out.println("<p align='justify'>Termination schedule will display present values of future rentals and Termination amount. Then user can save the termination calculation.");
						out.println("<p align='justify' class=rep-body>Delete");
						//out.println("<p align='justify'>In order to delete a termination calculation, termination number has to be selected. Then termination schedule and other details will display in the screen. To delete record has to be saved.");
						//out.println("<p align='justify'>Printed cheques can be cancelled from this section. Printed cheque will be hold only for 7 days and after 7 days it will be cancelled. Cheques which are hold for more than 7 days will come to this section. ");
						//out.println("<p align='justify'>User can first select the relevant account from which cheque is drawn. Then cheques which are hold for more than 7 days and drawn from that account will be shown. User can select the relevant cheque by marking the relevant tick box and then can save the record using the Save option. Once the cancellation is saved cheque will return to printing stage.");
						
						//----------------- Termination - Calculation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Termination_Cal'>");
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
						
						//----------------- Termination - Allocaton of Receipts User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
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
						
						//----------------- Termination - Processing ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Termination_Processing'>");
						out.println("</a><u>Finance - Termination - Processing </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Termination procedure will be completed at this level. Termination type can be selected from the drop down menu and the tick box gets activated only when the due amount from the client becomes zero. To save termination that tick box has to be filled and using save option entry can be saved.");
						
						//----------------- OD Interest User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='OD_Interest'>");
						out.println("</a><u>Finance - OD Interest </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option caters to view the OD interest, client wise and facility wise for a selected client. User can select OD status as stop or start and adjust. Then tick and save for complete the process.");
						
						//----------------- Finance Activation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Finance_Activation'>");
						out.println("</a><u>Finance - Finance Activation </u>");
						out.println(" ");
						//out.println("<p align='justify'>This option caters to view the OD interest, client wise and facility wise for a selected client. User can select OD status as stop or start and adjust. Then tick and save for complete the process.");
						
						//----------------- Change Capital Allowance Rate User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Change_Cap_Allow'>");
						out.println("</a><u>Finance - Change Capital Allowance Rates </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For change capital allowance rate of a leased assets can be used this option. User can search relevant asset, adjust the capital allowance rate and save. For multiple assets, using help option of invoice number field can be selected assets one by one for adjust the capital allowance rate and save.");
						
						//----------------- Security File Movement Entry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Security_File_Movement'>");
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
						
						//----------------- Security File Movement Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Security_File_Movement_Approval'>");
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
						
						//----------------- Day End Routine User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Day_End_Routine'>");
						out.println("</a><u>Finance - Day End Routine </u>");
						out.println(" ");
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
