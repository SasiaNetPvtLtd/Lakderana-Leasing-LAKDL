

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Credit extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Credit -  Credit Verification ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Verification'></a><u>Credit - Credit Verification</u> ");
						out.println("<p align='justify' class=rep-body>Once the application is completed, it will submit to the Credit department for the approval. All the completed applications will be listed in the credit verification screen. user can't verify the application from the initial screen. For verification user has to go to next section through the View option. At there user will find options to verify Applicant. Guarantors, Asset, Proforrma Invoice, Valuation and Approval accordingly. To go to next section user has to verify the previous section by marking the tick box which is there in the below of the page. E.g. once user verifies the Applicant the next section (Guarantors) gets activated. Any follow up remarks entered in previous sections will come be displayed at this level.");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Verify Applicant ");
						out.println("<p align='justify' class=rep-body>From the verify option in the initial screen, user can view the client screen and can acknowledge the details by marking a tick in the tick box. Once applicant details are verified Guarantor gets activated.");
						out.println("<p align='justify' class=rep-body>Verify Guarantor");
						out.println("<p align='justify' class=rep-body>From the verify option in the initial screen, user can view the guarantor screen and can acknowledge the details by marking a tick in the tick box. Once guarantor details are verified Asset detail gets activated.");
						out.println("<p align='justify' class=rep-body>Verify Asset");
						out.println("<p align='justify' class=rep-body>User can access to details of assets like make, model, sub model, quantity and purpose from here and can verify those details.");
						out.println("<p align='justify' class=rep-body>Verify Proforma Invoice");
						out.println("<p align='justify' class=rep-body>From the verify option in the initial screen, user can view the Proforma Invoice screen .It is necessary to verify pricing details using the link in verify screen Once Proforma Invoice details and Pricing details are verified Valuation or Approval gets activated.");
						out.println("<p align='justify' class=rep-body>Verify Valuation");
						out.println("<p align='justify' class=rep-body>If there is a Valuation at application processing level this level gets activated after the Proforma Invoice is verified. If there is no valuation directly can go to approval level.");
						out.println("<p align='justify' class=rep-body>Verify Documents Required");
						out.println("<p align='justify' class=rep-body>Status of the documents and follow up conditions entered there can be verified from this screen.");
						out.println("<p align='justify' class=rep-body>Approval Details");
						out.println("<p align='justify' class=rep-body>In this section detail of applicants, guarantors, assets, and pricings are summarised. User can verify the application by marking a tick in the tick box. Any follow up remarks entered in previous stages will be displayed at this level. Using the link available from Follow up, status of the activities can be changed. After the application is verified it will submit for the Credit Score Evaluation.");

						//----------------- Credit Score Evaluation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Score_Evaluation'>");
						out.println("</a><u>Credit - Credit Score Evaluation </u>");
						out.println(" ");
						out.println("<p class=rep-body>Credit scoring can be given to an application, from this section. Applications can be evaluated using a credit scoring model which was created in Administration Screens. (Credit Score Model Creation).This section consists of following options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("<p><li class=rep-body>	View</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>In order to enter new record Application number, and credit score model can be selected from the Help options. User of the system will be the Credit Evaluator.  Upon selection of the credit score model score categories and score sub categories relevant for that particular model will be displayed. Evaluator can give marks to those criteria.  When giving scores, minimum and maximum values defined for a particular criterion, when creating the score model cannot be exceeded. After scoring is completed record can be saved using the Save option.");
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>In order to modify an evaluation, user can select the Application number from the Help option. Then the existing data will be appeared. After modifying the data user can save the record using save option.");
						out.println("<p align='justify' class=rep-body>Delete");
						out.println("<p align='justify' class=rep-body>In order to delete an evaluation, user can select the Application number from the Help option. Then the existing data will be appeared. When it is saved evaluation gets deleted.");
						out.println("<p align='justify' class=rep-body>View");
						out.println("<p align='justify' class=rep-body>Details of score evaluation for selected application number can be viewed from this option.");
						
						//-----------------Credit -  Credit Approval ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Approval'></a><u>Credit - Credit Approval</u> ");
						out.println("<p align='justify' class=rep-body>After credit scoring is completed the scores given in evaluation will be approved in this level. Credit score model used, credit score for application, credit score for model, Name of the evaluator will be listed in this screen alone with the application no. Person who is approving the Credit Score can approve it including a comment if necessary.");
						out.println(" ");
						out.println("<p class=ug_headin><a NAME='Credit_Approval'></a><u>Credit - Credit Approval 1 & 2</u> ");
						out.println("<p align='justify' class=rep-body>Once the application is verified, it will submit for the Credit approval. All the verified applications will be listed in the credit approval screen. User can't approve the application from the initial screen. For approval user has to go to next section through the View option. At there user will find options to verify Applicant. Guarantors, Asset, Proforrma Invoice, Valuation and Approval accordingly. Like verification it is not mandatory to acknowledge all the sections.");
						out.println("<p align='justify' class=rep-body>Verify Applicant ");
						out.println("<p align='justify' class=rep-body>From the verify option in the initial screen, user can view the client screen and can acknowledge the details by marking a tick in the tick box. Once applicant details are verified Guarantor gets activated.");
						out.println("<p align='justify' class=rep-body>Verify Guarantor");
						out.println("<p align='justify' class=rep-body>From the verify option in the initial screen, user can view the guarantor screen and can acknowledge the details by marking a tick in the tick box. Once guarantor details are verified Asset detail gets activated.");
						out.println("<p align='justify' class=rep-body>Verify Asset");
						out.println("<p align='justify' class=rep-body>User can access to details of assets like make, model, sub model, quantity and purpose from here and can verify those details.");
						out.println("<p align='justify' class=rep-body>Verify Proforma Invoice");
						out.println("<p align='justify' class=rep-body>From the verify option in the initial screen, user can view the Proforma Invoice screen .It is necessary to verify pricing details using the link in verify screen Once Proforma Invoice details and Pricing details are verified Valuation or Approval gets activated.");
						out.println("<p align='justify' class=rep-body>Verify Valuation");
						out.println("<p align='justify' class=rep-body>If there is a Valuation at application processing level this level gets activated after the Proforma Invoice is verified. If there is no valuation directly can go to approval level.");
						out.println("<p align='justify' class=rep-body>Verify Documents Required");
						out.println("<p align='justify' class=rep-body>Status of the documents and follow up conditions entered there can be verified from this screen.");
						out.println("<p align='justify' class=rep-body>Approval Details");
						out.println("<p align='justify' class=rep-body>In this section details of the Credit Score Evaluation done at the previous level can be checked. Finally to approve the application user can select Approve from drop down menu. Any follow up remarks entered in previous stages will be displayed at this level. Using the link available from Follow up, status of the activities can be changed. After that this application will go to Purchase Order level.");
						out.println("<p align='justify' class=rep-body>Document Printing");
						out.println("<p align='justify' class=rep-body>When the application is approved at this level system will take the user to Documents printing level and using print option relevant documents for the application can be printed. Print option of irrelevant documents will be disabled.");
						
						//----------------- Finance No Entry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Finance_No_Entry'>");
						out.println("</a><u>Credit - Finance No Entry </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is an option for entering finance number manually to the system. After approval level 02, user can enter finance number, posted address, telephone number and activate date of the facility and save. Using Reverse option can be obtain a list of previously activated leases and Edit option can be used to edit those activated leases.");
						
						//----------------- Purchase Order Generation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Purchase_Order_Gen'>");
						out.println("</a><u>Credit - Purchase Order Generation </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Purchase order is an order sent by the financing company to the vendor, asking them to deliver the asset to client, while promising to make the payment to him. "+
												"Once the application is approved it will come to the Purchase Order level. Before releasing the Purchase Order, it is very important to check all the documents are in order, since after releasing the P/O it cannot withdraw without the consent of the supplier and company is liable to make the payment. Approved applications will be listed in the Purchase Order Main Screen. By drilling down the application number system will take user to the Purchase Order screen. This section consists of following options."+
												"");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Delete</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>Application number can be selected from the Help box and the supplier recorded at the Proforma Invoice level will be retrieved from the Help option. Invoice number entered for the Application can be selected and user can ensure by marking the documents checklist that all the required documents are in order. Once the Purchase Order is released, system will do the Finance Activation. System date will be the activated date and if user wants can change it.");
						out.println("<p align='justify' class=rep-body>Charges that customer has to pay to the company before releasing the purchase order will be appeared in the amount to be charged field. If client has not made the payment and conditions stated in previous stages is not completed system will prevent issuing the P/O.");
						out.println("<p align='justify' class=rep-body>Once the P/O details are saved letter gets generated, and it can be printed on right click of the mouse or on click of Print option.");
						out.println("<p align='justify' class=rep-body>From the Letter option also letters can be printed, by selecting the Purchase Order number.");
						out.println("<p align='justify' class=rep-body>If the vendor's last transaction date is greater than one year, Purchase Order will be directed to an approval level.");
						
						//----------------- Purchase Order Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Purchase_Order_Approval'>");
						out.println("</a><u>Credit - Purchase Order Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>If the vendor's last transaction date is greater than one year or a new supplier, Purchase Order will come to this level. From the detail option person who is approving the deal can access the information relevant to that particular Purchase Order. By marking on the relevant tick box in front of the purchase order user can save the purchase order.");
						out.println("<p align='justify' class=rep-body>This section consists of following options.");
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
						
						//----------------- Agreement Printing User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Agreement_Printing'>");
						out.println("</a><u>Credit - Agreement Printing </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the facility is approved, documents (Agreement, Acceptance Receipt, First Letter, Schedule etc.) can be printed from this section.");
						out.println("<p align='justify' class=rep-body>User can use the link available through the Application number to go to documents printing section.");
						out.println("<p align='justify' class=rep-body>At their all the documents that are relevant for the finance facility can be printed using the Print option in front of the document name.");
						
						//----------------- Standing Order Entry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Standing_Order_Entry'>");
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
						
						//----------------- Standing Order Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Standing_Order_Approval'>");
						out.println("</a><u>Credit - Standing Order Approval </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When clients want to change their settlement mode from standing order it may end from the Standing Order section. Those abandoned standing orders will come to this level for approval. To approve relevant record has to be selected and then it should save using the Save option.");
						
						//----------------- Change Activation Date User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Change_Activation_Date'>");
						out.println("</a><u>Credit - Change Activation Date </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When the Purchase order is released, system will automatically activate the finance facility. Finance Activation date will be the date on which the purchase order is released. Should user wants system facilitate to change the finance activation date from this section.");
						out.println("<p align='justify' class=rep-body>User can date the applicable date from the calendar option provided under the Activated Date and status has to be marked. Then the changed date can be saved using the Save option.");
						
						//----------------- Add Guarantor User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Add_Guarantor'>");
						out.println("</a><u>Credit - Add Guarantor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option caters to add guarantor for a facility.Relevent facility can be searched through Application number, Finance number and Client code. Using help option under guarantor code can be selected relevant person and relationship and period should be filled before saving. More option assists to add more than one guarantor as same as mention above.");
						
						//----------------- Delete Guarantor User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Delete_Guarantor'>");
						out.println("</a><u>Credit - Delete Guarantor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This option is used to delete existing guarantors from a facility. In that user can searched relevant facility using Application number, Client code or finance number. User should select the guarantor, mention in the tick box and save for complete the process.");
						
						//----------------- Change Future Rental Due Dates User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Change_Rental_Date'>");
						out.println("</a><u>Credit - Change Future Rental Due Dates </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Existing rental date can be changed using this option.");
						
						//----------------- Change Insurance Date User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Change_Insurance_Date'>");
						out.println("</a><u>Credit - Change Insurance Date </u>");
						out.println(" ");
						//out.println("<p align='justify'>Existing rental date can be changed using this option.");
						
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
