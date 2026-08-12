

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Credit_new extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Credit--header-------
						out.println("<p class=ug_headin><a NAME='Credit_header'></a><u>Credit </u> ");
						out.println("<p align='justify' class=rep-body>To avoid/minimize defaulting, Credit module comprises of credit verification tools and credit approval levels. Separate sections for applicant details, asset details, pro-forma invoices and valuation details, when approving facilities, provide additional security and simplicity. You can raise Purchase Orders and make Payment Requisitions when carrying out activities in the Credit module. Link between NetAsset and Credit Information Bureau (CRIB) aids you to prepare CRIB letters and data files. The Maintenance section in the module is to change status of applications, reverse applications and update monetary values and dates that are related to facilities.");
						out.println(" ");
						
						
						
						//-----------------Credit -  Credit Verification ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Verification'></a><u>Credit Verification</u> ");
						out.println("<p align='justify' class=rep-body>A higher level system user is expected to verify credit related information in order to ensure maintaining defaulting at a minimum level. This comprises of multiple sections as Applicant verification, Guarantor verification, Asset verification, Pro-forma invoice verification and so on. Verification of all of them takes the record to the next level, i.e. Credit Score Evaluation.");
						out.println(" ");
											//----------------- Credit Score Evaluation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Score_Evaluation'>");
						out.println("</a><u>Credit Score Evaluation </u>");
						out.println(" ");
						out.println("<p class=rep-body>After verifying credit status, credit scores have to be evaluated. You can select any credit score model and assign a score for the applications you wish evaluate. ");
					
						//-----------------Credit -  Credit Approval ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Approval'></a><u>Credit Approval - First Approval</u> ");
						out.println("<p align='justify' class=rep-body>This is the initial approval of the application related to credit. The system takes you to screens to verify the applicant, guarantors, asset, invoice and pricing, valuation and documents required. Once all of them are approved, the record will be pushed to the higher approval level.");
						out.println(" ");
						out.println("<p class=ug_headin><a NAME='Credit_Approval2'></a><u> Credit Approval - Second Approval</u> ");
						out.println("<p align='justify' class=rep-body>This is quite similar to the First approval, but should be done by a more senior user than the one who gives the first approval. ");
						
						
						//----------------- Initial_Data_specification ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Initial_Data_specification'>");
						out.println("</a><u>Initiation Data Specification </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is the window to enter facility initiation dates such as Finance number and dates related to initiation and payments. ");
						
						
						//---------------stipulated loss value entry-----------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Stipu_loss_valEntry'>");
						out.println("</a><u>Stipulated Loss Value Entry </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can enter depreciation rates for the asset that is financed so that the system displays the net value for stipulated loss for each year of the financed period. ");
						
						//----------------document generation---------------------
						out.println("<p align='justify' class=ug_headin><a NAME='Document_generation'>");
						out.println("</a><u>Document Generation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For a particular Application number, the system allows you to generate and print mandatory and supporting documents starting from Leasing agreement to Asset replacement document. List of documents that should be applicable for each type of service is different to those of another service and is determined by the way the system is customized.");
					
					
						//----------------- Purchase Order Generation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Purchase_Order_Gen'>");
						out.println("</a><u>Purchase Orders - Generation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can generate Purchase Orders by selecting a record from the table of pending applications. Purchase Orders are generated by specifying necessary payment related and other information. Status of applicable documents has to be entered to the system and Conditions can be specified in this screen. ");
						/*out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>Application number can be selected from the Help box and the supplier recorded at the Proforma Invoice level will be retrieved from the Help option. Invoice number entered for the Application can be selected and user can ensure by marking the documents checklist that all the required documents are in order. Once the Purchase Order is released, system will do the Finance Activation. System date will be the activated date and if user wants can change it.");
						out.println("<p align='justify' class=rep-body>Charges that customer has to pay to the company before releasing the purchase order will be appeared in the amount to be charged field. If client has not made the payment and conditions stated in previous stages is not completed system will prevent issuing the P/O.");
						out.println("<p align='justify' class=rep-body>Once the P/O details are saved letter gets generated, and it can be printed on right click of the mouse or on click of Print option.");
						out.println("<p align='justify' class=rep-body>From the Letter option also letters can be printed, by selecting the Purchase Order number.");
						out.println("<p align='justify' class=rep-body>If the vendor's last transaction date is greater than one year, Purchase Order will be directed to an approval level.");
						*/
						//----------------- Purchase Order Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Purchase_Order_Approval'>");
						out.println("</a><u>Purchase Orders - Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A higher level user can approve Purchase Orders created in the previous screen. ");
						/*out.println("<p align='justify' class=rep-body>This section consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Help</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("<p><li class=rep-body>	Letter</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>From this section Purchase Orders can be approved. Last transaction date, vendor details, vehicle number, engine number, chassis number can be viewed from the view option right to the record. To approve a purchase order, first purchase order no has to be selected and then press save option. Any number of purchase orders can be approved at once. ");
						out.println("<p align='justify' class=rep-body>Purchase order letters for approved purchase orders can be printed from Letter option. User has to select the purchase order number from Help option and should press Go. Then the letter gets generated and can print.");
						out.println("<p align='justify' class=rep-body>Printed copy can be given to the client along with the MTA 3 form stating terms and conditions relevant to the purchase order.");
						*/
						
						
						
						//----------------purchase order printing-----------------------
						out.println("<p align='justify' class=ug_headin><a NAME='Purchase_ord_Printing'>");
						out.println("</a><u>Purchase Orders - Printing </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can print Purchase Orders in this screen.");
						/*out.println("<p align='justify' class=rep-body>User can use the link available through the Application number to go to documents printing section.");
						out.println("<p align='justify' class=rep-body>At their all the documents that are relevant for the finance facility can be printed using the Print option in front of the document name.");
						*/
						
						
						//------------purchase order deletion------------------
						out.println("<p align='justify' class=ug_headin><a NAME='Purchase_ord_Deletion'>");
						out.println("</a><u>Purchase Orders - Deletion </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Purchase Orders can be deleted from the system when you do not require it any more, with the use of this screen. ");


						//-----------------------Payment_Requisitions_Generation
						out.println("<p align='justify' class=ug_headin><a NAME='Payment_Requisitions_Generation'>");
						out.println("</a><u>Payment Requisitions - Generation (Leasing) </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For a selected vendor, you can generate a Payment Requisition. As Payment Requisitions are mapped with applications, generating requisition for higher amounts than the balance amount of an application will be disallowed. Such a requisition is made by the Credit Department to the Finance Department. This screen helps you to generate Payment Requisitions for Leasing operation. ");
						
						//------------------------Payment_Requisitions_Generation_loans
						out.println("<p align='justify' class=ug_headin><a NAME='Payment_Requisitions_Generation_loans'>");
						out.println("</a><u>Payment Requisitions - Generation (Loans) </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The difference in this screen with the previous screen, in business, is that this screen helps you to generate Payment Requisitions for loans when the other generates Payment Requisitions for leases. ");
						
						//-------------------Payment_Requisitions_Special_Approval
						out.println("<p align='justify' class=ug_headin><a NAME='Payment_Requisitions_Special_Approval'>");
						out.println("</a><u>Payment Requisitions - Special Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Generated Payment Requisitions require higher level approval due to high sensitivity. User who has access to this can approve such requisitions.");
						//---------Additional_Approval_Remarks
						out.println("<p align='justify' class=ug_headin><a NAME='Additional_Approval_Remarks'>");
						out.println("</a><u>Additional Approval Remarks </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Users are allowed to enter additional comments for facilities other than through the standard procedure. This screen lets authorized users to enter comments, which appear in the Sanction Letter.");
						
						//-----------------CRIB_Letter_Generation
						out.println("<p align='justify' class=ug_headin><a NAME='CRIB_Letter_Generation'>");
						out.println("</a><u>CRIB - Letter Generation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>If the Credit Information Bureau (CRIB) requires information to be provided by you regarding one of your clients, you can easily generate a letter here. ");
						
						//--------------CRIB_Data_File_Generation
						out.println("<p align='justify' class=ug_headin><a NAME='CRIB_Data_File_Generation'>");
						out.println("</a><u>CRIB - Data File Generation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>As there is a legal requirement for you to send client information to the Credit Information Bureau (CRIB), you can generate a data file including all required information related to your client base during a specified period of time with the use of this screen. The output is a flat file which you can upload to the CRIB directly.");

						//----------------Maintenance_Deletion_Letter
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Deletion_Letter'>");
						out.println("</a><u>Maintenance - Deletion Letter </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Once the leasing period comes to an end, you need to notify the Commissioner of Motor Traffic that the records related to ownership in the Certificate of Registration are to be deleted as the asset's ownership changes. This screen helps you to generate such letters for ended lease agreements.");
						
						
						//------Maintenance_Change_Activated_Date
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Change_Activated_Date'>");
						out.println("</a><u>Maintenance - Change Activated Date </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can change the next Activation date of a facility and the Next Payment date in this screen.");

						//Maintenance_Change_Activated_Date
						
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Change_Future_Rental_Due_Dates'>");
						out.println("</a><u>Maintenance - Change Future Rental Due Dates </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can make changes to the payment plan of a facility by changing the rental dates of each installment related to a facility here.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Change_Rental_Amount'>");
						out.println("</a><u>Maintenance - Change Rental Amount </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you are allowed to change the rental amount of a facility.");

						//-----Maintenance_Change_Insurance_Date
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Change_Insurance_Date'>");
						out.println("</a><u>Maintenance - Change Insurance Date </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Changing of Insurance renewal dates, Revenue license renewal dates, Luxury tax dates and Driving license renewal dates related to a facility here.");
						
						
						//----Maintenance_Facility_Cancellation_after_Purchase_Order
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Facility_Cancellation_after_Purchase_Order'>");
						out.println("</a><u>Maintenance - Facility Cancellation after Purchase Order </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After raising a Purchase Order you can cancel a facility and all related entries with the use of this screen. This includes reversal of the Purchase Order, reversal of application and so on.");

						
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Add_Guarantor'>");
						out.println("</a><u>Maintenance - Add Guarantor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You are facilitated to add guarantors in runtime without consulting your system administrator who has access to the Administration module.");
						//-----------Maintenance_Delete_Guarantor
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Delete_Guarantor'>");
						out.println("</a><u>Maintenance - Delete Guarantor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Guarantors in the system can be deleted here. ");
						//------------------------Maintenance_Standing_Order_Entry
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Standing_Order_Entry'>");
						out.println("</a><u>Maintenance - Standing Order Entry </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Standing Orders, with regard to facilities, can be defined in the system in this screen.  ");
						
						//---------------------------Maintenance_Standing_Order_Approval
						out.println("<p align='justify' class=ug_headin><a NAME='Maintenance_Standing_Order_Approval'>");
						out.println("</a><u>Maintenance - Standing Order Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Entered Standing Orders should be approved by a senior user in order to activate the order. This screen facilitates for that.  ");

						
						
						//----------------- Agreement Printing User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						/*out.println("<p align='justify' class=ug_headin><a NAME='Agreement_Printing'>");
						out.println("</a><u>Credit - Agreement Printing </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the facility is approved, documents (Agreement, Acceptance Receipt, First Letter, Schedule etc.) can be printed from this section.");
						out.println("<p align='justify' class=rep-body>User can use the link available through the Application number to go to documents printing section.");
						out.println("<p align='justify' class=rep-body>At their all the documents that are relevant for the finance facility can be printed using the Print option in front of the document name.");
						*/
						//----------------- Standing Order Entry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p align='justify' class=ug_headin><a NAME='Standing_Order_Entry'>");
						out.println("</a><u>Credit - Standing Order Entry</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When clients are remitting their monthly rental payments by way of standing Order, those details can be entered to the system from this section. Following options are available in this section.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>In order to enter a new record, Lease number Client number, Vehicle number, and Account number have to be selected from the Help option. Amount that the client is remitting through standing order can be entered in the amount field.");
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>If user wants to End making payments through standing order user can select the relevant standing order number and record can be saved using the Save option.");
						*/
						//----------------- Standing Order Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p align='justify' class=ug_headin><a NAME='Standing_Order_Approval'>");
						out.println("</a><u>Credit - Standing Order Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When clients want to change their settlement mode from standing order it may end from the Standing Order section. Those abandoned standing orders will come to this level for approval. To approve relevant record has to be selected and then it should save using the Save option.");
					*/	
						//----------------- Change Activation Date User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
				/*		out.println("<p align='justify' class=ug_headin><a NAME='Change_Activation_Date'>");
						out.println("</a><u>Credit - Change Activation Date </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the Purchase order is released, system will automatically activate the finance facility. Finance Activation date will be the date on which the purchase order is released. Should user wants system facilitate to change the finance activation date from this section.");
						out.println("<p align='justify' class=rep-body>User can date the applicable date from the calendar option provided under the Activated Date and status has to be marked. Then the changed date can be saved using the Save option.");
					*/	
						//----------------- Add Guarantor User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
					/*	
						out.println("<p align='justify' class=ug_headin><a NAME='Add_Guarantor'>");
						out.println("</a><u>Credit - Add Guarantor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option caters to add guarantor for a facility.Relevent facility can be searched through Application number, Finance number and Client code. Using help option under guarantor code can be selected relevant person and relationship and period should be filled before saving. More option assists to add more than one guarantor as same as mention above.");
					*/	
						//----------------- Delete Guarantor User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p align='justify' class=ug_headin><a NAME='Delete_Guarantor'>");
						out.println("</a><u>Credit - Delete Guarantor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option is used to delete existing guarantors from a facility. In that user can searched relevant facility using Application number, Client code or finance number. User should select the guarantor, mention in the tick box and save for complete the process.");
					*/	
						//----------------- Change Future Rental Due Dates User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p align='justify' class=ug_headin><a NAME='Change_Rental_Date'>");
						out.println("</a><u>Credit - Change Future Rental Due Dates </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Existing rental date can be changed using this option.");
					*/	
						//----------------- Change Insurance Date User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
					/*	out.println("<p align='justify' class=ug_headin><a NAME='Change_Insurance_Date'>");
						out.println("</a><u>Credit - Change Insurance Date </u>");
						out.println(" ");
						//out.println("<p align='justify'>Existing rental date can be changed using this option.");
					*/	
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
