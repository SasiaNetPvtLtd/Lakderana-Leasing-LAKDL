

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_FA_UG_Credit extends javax.servlet.http.HttpServlet { 

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
						out.println("<body text='#000000' bgcolor='#FFFFFF' >");
						
						//-----------------Client/Debtor Creation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit'></a><u>2. Credit </u>");
						out.println("<p align='justify' class=rep-body>Facility processing can be started from this stage too. Major functions of credit process are appraising ability of client and debtor to obtain the facility, making final decisions regarding approval and termination a facility .Credit process consists of following sub modules.");
						out.println(" ");
						out.println("<p class=ug_headin><a NAME='Client_Debtor'></a><u>2.1 Client/Debtor Creation</u> ");
						out.println("<p align='justify' class=rep-body>Client/debtor creation may be started from this stage too. This option consists of following sub options.");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>This option is used to create new clients and debtors and directly shifted them to client/debtor creation approval stage. Fields with '*' are essential fields that should be filled before save client details. Inquiry field can be used to filter client details from inquiry level by using client name, telephone number, NIC or business registration number or address. Changing factoring relationship as debtor, user can enter debtor information and save.");
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>Edit option is used to modify exiting clients as well as through Edit >> Help option, leasing clients can also be filtered to the factoring purposes. By using client name, telephone numbers, NIC/business registration or address user can easily search clients/debtors.");
						out.println("<p align='justify' class=rep-body>Cancel");
						out.println("<p align='justify' class=rep-body>Cancel option assists user to clear the interface of the screen.");
						out.println("<p align='justify' class=rep-body>Close");
						out.println("<p align='justify' class=rep-body>Through close option user can close current screen and move to main menu.");
						
						//----------------- Client/Debtor Creation Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Client_Debtor_Approval'>");
						out.println("</a><u>2.2 Client/Debtor Creation Approval </u>");
						out.println(" ");
						out.println("<p class=rep-body>Successfully saved clients/debtors are automatically shifted to the client/debtor creation approval stage. The major role of this option is to make decisions regarding clients and debtors for further processing. This option assists to user to mention the decisions such as approve, disapprove and follow up. User can select relevant decision by down arrow under action heading and save for further processing. Before save action taken by authorized person should fill client manager and marketing executive fields.");
						out.println("<p align='justify' class=rep-body>Factoring relationship");
						out.println("<p align='justify' class=rep-body>Through factoring relationship field user can select factoring relationship as client, debtor or client or debtor and get list of records under each relationship category.");
						out.println("<p align='justify' class=rep-body>Client Code");
						out.println("<p align='justify' class=rep-body>If user requires more information regarding a client or debtor should click on code.");
						out.println("<p align='justify' class=rep-body>Cancel");
						out.println("<p align='justify' class=rep-body>Cancel option assists user to clear the screen");
						out.println("<p align='justify' class=rep-body>Close");
						out.println("<p align='justify' class=rep-body>Through close option user can close current screen and move to main menu.");
						
						//-----------------Create Client Facility User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Create_Client_Facility'></a><u>2.3 Create Client Facility</u> ");
						out.println("<p align='justify' class=rep-body>Successfully approved clients in the client creation approval level are filtered to client facility creation stage. This option is consists of following sub options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>When crate a new facility for approved client, user can enter new records through this option. If that client was approved in quotation level can easily be filtered information to this screen otherwise have to be entered. In product package and fee package can be selected relevant product and fee and based on requirements user can add additional features for a product as well as fees for a fee structure. Facility number is created by the system. Fields with '*' mark is required to be filled before saved. Successfully saved client are directly shifted to the client facility approval level.");
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>Clients in facility approval level can be modify by using Edit >>Help option. User allows modifying credit limit, credit period, tolerance period, reserve margin and interest rate and product and fee packages before approve the facility.");
						out.println("<p align='justify' class=rep-body>Cancel");
						out.println("<p align='justify' class=rep-body>Cancel option assists user to clear the interface of the screen.");
						out.println("<p align='justify' class=rep-body>Close");
						out.println("<p align='justify' class=rep-body>Through close option user can close current screen and move to main menu.");
						
						
						//----------------- Client Facility Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Client_Facility_Approval'></a><u>2.4 Client Facility Approval Level 1</u> ");
						out.println("<p align='justify' class=rep-body>Successfully saved client facilities directly shift to the client facility approval level. This sub module consists of following options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>Save");
						out.println("<p align='justify' class=rep-body>When make a new decision regarding a client facility such as approve, Disapprove or follow up can be saved by using this option. If user requires knowing more details regarding facility should be doubled clicked on facility no and for client details should be double clicked on client code.");
						out.println("<p align='justify' class=rep-body>Cancel");
						out.println("<p align='justify' class=rep-body>Cancel option assists user to clear the interface of the screen.");
						out.println("<p align='justify' class=rep-body>Close");
						out.println("<p align='justify' class=rep-body>Through close option user can close current screen and move to main menu.");
						
						//----------------- Client Facility Confirmation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Client_Facility_Confirmation'></a><u>2.5 Client Facility Confirmation</u> ");
						//out.println("<p align='justify'>Successfully saved client facilities directly shift to the client facility approval level. This sub module consists of following options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						/*out.println("<p align='justify'>Save");
						out.println("<p align='justify'>When make a new decision regarding a client facility such as approve, Disapprove or follow up can be saved by using this option. If user requires knowing more details regarding facility should be doubled clicked on facility no and for client details should be double clicked on client code.");
						out.println("<p align='justify'>Cancel");
						out.println("<p align='justify'>Cancel option assists user to clear the interface of the screen.");
						out.println("<p align='justify'>Close");
						out.println("<p align='justify'>Through close option user can close current screen and move to main menu.");*/
						
						//----------------- Credit Evaluation - Factoring User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Evaluation'></a><u>2.6 Credit Evaluation - Factoring</u> ");
						//out.println("<p align='justify'>Successfully saved client facilities directly shift to the client facility approval level. This sub module consists of following options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Credit_Score_Entry' target='help-right'>	Credit Score Entry </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Credit_Score_Approval' target='help-right'>	Credit Score Approval </a></p>");
						//out.println("<p><li>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Score_Entry'></a><u>2.6.1 Credit Score Entry</u>");
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Score_Approval'></a><u>2.6.2 Credit Score Approval</u>");
						/*out.println("<p align='justify'>When make a new decision regarding a client facility such as approve, Disapprove or follow up can be saved by using this option. If user requires knowing more details regarding facility should be doubled clicked on facility no and for client details should be double clicked on client code.");
						out.println("<p align='justify'>Cancel");
						out.println("<p align='justify'>Cancel option assists user to clear the interface of the screen.");
						out.println("<p align='justify'>Close");
						out.println("<p align='justify'>Through close option user can close current screen and move to main menu.");*/
						
						//----------------- Assign Client-Debtor User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Assign_Client_Debtor'></a><u>2.7 Assign Client - Debtor</u> ");
						out.println("<p align='justify' class=rep-body>This option is used to assign debtors for approved facilities and consists of following sub options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	New</p>");
						out.println("<p><li class=rep-body>	Edit</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>New");
						out.println("<p align='justify' class=rep-body>When assign new debtor for new facility, user can use this option by selecting debtor from Help option in second phase of the screen and save with facility details and client details. Through details boxes user can get more details of client and debtor.");
						out.println("<p align='justify' class=rep-body>Edit");
						out.println("<p align='justify' class=rep-body>Through this option user can modify only debtors by adding new debtors or modifying existing debtors.");
						out.println("<p align='justify' class=rep-body>Close");
						out.println("<p align='justify' class=rep-body>Through close option user can close current screen and move to main menu.");
						
						//----------------- Activate Client-Debtor User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Activate_Client_Debtor'></a><u>2.8 Activate Client Debtor</u> ");
						out.println("<p align='justify' class=rep-body>This screen is used to approve client debtor assignment and consists of following sub options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>Save");
						out.println("<p align='justify' class=rep-body>This option is used to save new decisions such as approve, disapprove or Follow up made by authorized person regarding a debtor assignment for a facility. It should be double clicked for more details regarding facility on facility number as well as client code and debtor code. ");
						out.println("<p align='justify' class=rep-body>Cancel");
						out.println("<p align='justify' class=rep-body>Cancel option assists user to clear the screen.");
						out.println("<p align='justify' class=rep-body>Close");
						out.println("<p align='justify' class=rep-body>Through close option user can close current screen and move to main menu.");
						
						//----------------- Facility Activation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Facility_Activation'></a><u>2.9 Facility Activation</u> ");
						//out.println("<p align='justify'>This screen is used to approve client debtor assignment and consists of following sub options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body>	Save</p>");
						out.println("<p><li class=rep-body>	Cancel</p>");
						out.println("<p><li class=rep-body>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=rep-body>Save");
						//out.println("<p align='justify'>This option is used to save new decisions such as approve, disapprove or Follow up made by authorized person regarding a debtor assignment for a facility. It should be double clicked for more details regarding facility on facility number as well as client code and debtor code. ");
						out.println("<p align='justify' class=rep-body>Cancel");
						out.println("<p align='justify' class=rep-body>Cancel option assists user to clear the screen.");
						out.println("<p align='justify' class=rep-body>Close");
						out.println("<p align='justify' class=rep-body>Through close option user can close current screen and move to main menu.");
						
						//----------------- Legal Letters User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Legal_Letters'></a><u>2.10 Legal Letters</u> ");
						//out.println("<p align='justify'>Successfully saved client facilities directly shift to the client facility approval level. This sub module consists of following options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Agreement_Printing' target='help-right' >	Agreement Printing </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Legal_Letter' target='help-right' >	Legal Letter - Client </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Agreement_Enhance' target='help-right' >	Agreement Enhancement </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Legal_Letter_To_Debtor' target='help-right' >	Legal Letter To Debtor </a></p>");
						//out.println("<p><li>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=ug_headin><a NAME='Agreement_Printing'></a><u>2.10.1 Agreement Printing </u>");
						out.println("<p align='justify' class=ug_headin><a NAME='Legal_Letter'></a><u>2.10.2 Legal Letter - Client</u>");
						out.println("<p align='justify' class=ug_headin><a NAME='Agreement_Enhance'></a><u>2.10.3 Agreement Enhancement</u>");
						out.println("<p align='justify' class=ug_headin><a NAME='Legal_Letter_To_Debtor'></a><u>2.10.4 Legal Letter To Debtor</u>");
						/*out.println("<p align='justify'>When make a new decision regarding a client facility such as approve, Disapprove or follow up can be saved by using this option. If user requires knowing more details regarding facility should be doubled clicked on facility no and for client details should be double clicked on client code.");
						out.println("<p align='justify'>Cancel");
						out.println("<p align='justify'>Cancel option assists user to clear the interface of the screen.");
						out.println("<p align='justify'>Close");
						out.println("<p align='justify'>Through close option user can close current screen and move to main menu.");*/
						
						//----------------- Credit Maintenance User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Maintenace'></a><u>2.11 Credit Maintenace</u> ");
						//out.println("<p align='justify'>Successfully saved client facilities directly shift to the client facility approval level. This sub module consists of following options.");
						out.println(" ");
						out.println("<blockquote>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Facility_Main' target='help-right' >	Client Facility Maintenace </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Debtor_Relation' target='help-right' >	Client/Debtor Relation Maintenance </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Debtor_Main' target='help-right' >	Client/Debtor Maintenance </a></p>");
						out.println("<p><li class=rep-body><a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Debtor_Limit' target='help-right' >	Debtor Limit Adjustments </a></p>");
						//out.println("<p><li>	Close</p>");
						out.println("</blockquote>");
						out.println("<p align='justify' class=ug_headin><a NAME='Client_Facility_Main'></a><u>2.11.1 Client Facility Maintenace </u>");
						out.println("<p align='justify' class=ug_headin><a NAME='Client_Debtor_Relation'></a><u>2.11.2 Client/Debtor Relation Maintenance</u>");
						out.println("<p align='justify' class=ug_headin><a NAME='Client_Debtor_Main'></a><u>2.11.3 Client/Debtor Maintenance</u>");
						out.println("<p align='justify' class=ug_headin><a NAME='Debtor_Limit'></a><u>2.11.4 Debtor Limit Adjustments</u>");
						/*out.println("<p align='justify'>When make a new decision regarding a client facility such as approve, Disapprove or follow up can be saved by using this option. If user requires knowing more details regarding facility should be doubled clicked on facility no and for client details should be double clicked on client code.");
						out.println("<p align='justify'>Cancel");
						out.println("<p align='justify'>Cancel option assists user to clear the interface of the screen.");
						out.println("<p align='justify'>Close");
						out.println("<p align='justify'>Through close option user can close current screen and move to main menu.");*/
						
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
