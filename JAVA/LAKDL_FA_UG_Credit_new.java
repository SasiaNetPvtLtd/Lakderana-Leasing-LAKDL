

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_FA_UG_Credit_new extends javax.servlet.http.HttpServlet { 

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
						out.println("<p class=ug_headin><a NAME='Credit'></a><u>Credit </u> ");
						out.println("<p align='justify' class=rep-body>If a prospective customer is willing to obtain the service from you, your record that was in the Marketing module can be moved to the Credit module. You also can start a fresh entry if the client comes directly and agrees for the service. You can evaluate your client's creditworthiness, add debtors, create facilities and generate documents through this module. ");

						//-----------------Client/Debtor_Creation  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Client/Debtor_Creation'></a><u>Client/Debtor Creation</u> ");
						out.println("<p align='justify' class=rep-body>When clients, who require the factoring facility from you, arrive, you do not need the Marketing module and start initiating an entry in the Credit module. In the event of such, this is the screen where you should enter details of a client. Similarly, debtor information also can be fed into the system here by changing the factoring relationship to 'As a debtor'. ");
						
						//-----------------Client/Debtor Creation Approval  ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Client/Debtor_Creation_Approval'></a><u>Client/Debtor Creation Approval</u> ");
						out.println("<p align='justify' class=rep-body>When clients and/or debtors are created in the Credit module, they have to be approved by a senior user who has access to the Credit module, particularly to this screen.");

						//----------------- Create Client Facility User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Create_Client_Facility'>");
						out.println("</a><u>Create Client Facility </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A facility is a unit of product purchased by one client. Once a client purchases one of the products, i.e. obtains one factoring service, a facility has to be created. If an inquiry exists for the facility you wish to create, you can select it so that most of the fields will be populated with the data you entered previously. You can specify the factoring duration and other data to register a facility in your system.");
						
						//----------------- Credit Evaluation - Entry  User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Evaluation_Entry'>");
						out.println("</a><u>Credit Evaluation - Entry </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can initiate a credit score evaluation by selecting a facility you need to proceed with. You can evaluate the clients business, its ownership, references obtained from stakeholders, bank records, products, debtors, etc by assigning values through drop down menus. By this you will assess the overall suitability to obtain factoring facility from you.");
						
						//----------------- Credit Evaluation - Evaluation  ----------------------------------------------------------
						out.println("<p class=ug_headin><a NAME='Credit_Evaluation_Evaluation'>");
						out.println("</a><u>Credit Evaluation - Evaluation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>At the evaluation level, previously entered credit scores will be reevaluated by a senior level user. The user will be able to change the values entered by the user who created the evaluation.");
						
						
						
						//-------------Credit_Evaluation_Approval
						out.println("<p class=ug_headin><a NAME='Credit_Evaluation_Approval'>");
						out.println("</a><u>Credit Evaluation - Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Credit score evaluations previously carried out should be approved by authorized personnel. This screen lets a senior user to approve credit evaluations carried out. Without approval the system does not let you proceed with a facility.");
						
						///-------------------Client_Facility_Approval
						
						out.println("<p class=ug_headin><a NAME='Client_Facility_Approval'>");
						out.println("</a><u>Client Facility Approval</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After credit score is approved, next step is to approve the client facility. A senior user will look at the client details, credit limit and credit score and will approve entered data.");
						
						//------------------------Client_Facility_Confirmation
						out.println("<p class=ug_headin><a NAME='Client_Facility_Confirmation'>");
						out.println("</a><u>Client Facility Confirmation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A facility requires confirmation by a senior user before it becomes effective. At confirmation, a senior user will determine that the client should be provided with factoring service.");
                         
						//---------------------------Assign_Client_Debtor
						out.println("<p class=ug_headin><a NAME='Assign_Client_Debtor'>");
						out.println("</a><u>Assign Client - Debtor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you map debtors you created with clients.");
					
					//-----------------------Client_Debtor_Document_Update
						out.println("<p class=ug_headin><a NAME='Client_Debtor_Document_Update'>");
						out.println("</a><u> Client - Debtor Document Update </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After mapping debtors with clients, you have to specify the status/availability of necessary documents that are applicable for the type of service you offer. Once you select a client and a debtor through the screen, you can view required document and tick the documents that are correctly possessed.");

                     //---------------Activate Client-Debtor
					 	out.println("<p class=ug_headin><a NAME='Activate_Client_Debtor'>");
						out.println("</a><u>Activate Client - Debtor </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You need to activate each client - debtor relationship in order to make them effective. Each row in the table shows a relationship between one client and one debtor. You can select the Approve option for the relationships you wish to approve and save. If the rows are disabled, one or more documents that is essential for the facility is to be collected. If the dropdown menu does not contain Approve button, the relationship cannot be approved due to violation of your policies such as larger credit limit.");
					//-----------------Facility Activation
					
						out.println("<p class=ug_headin><a NAME='Facility_Activation'>");
						out.println("</a><u>Facility Activation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can delay activation of a facility even after entering data into the system. Here you can select or change an activation date. You are allowed to delay or advance an activation date.");

            out.println("<p class=ug_headin><a NAME='Legal_Letters_Agreement_Printing'>");
						out.println("</a><u>Legal Letters - Agreement Printing</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen helps you to print agreements that you enter with your clients. ");
					
					//-------------------------------Legal_Letters_Client/Debtor_Assignment_Letters 
            out.println("<p class=ug_headin><a NAME='Legal_Letters_Client/Debtor_Assignment_Letters'>");
						out.println("</a><u>Legal Letters - Client/Debtor Assignment Letters</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen displays you all client-debtor relationships in each row. You can pick any debtor that is assigned to a client and generate letters that are required at the initiation of a factoring facility. First letter is to inform the debtor about the factoring facility purchased by the client. Second letter carries the duties and responsibilities applicable for the debtor and third letter requests documents from the debtor. You can print these letters and send to the debtors when required. ");
						
					//------------------Legal_Letters_Agreement_Enhancement
						out.println("<p class=ug_headin><a NAME='Legal_Letters_Agreement_Enhancement'>");
						out.println("</a><u>Legal Letters - Agreement Enhancement</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Through this screen the legal letters which get generated when a facility is enhanced can be printed. ");
					
					//-------------Legal_Letters_Cancellation_of_Assigned_Letters
					
						out.println("<p class=ug_headin><a NAME='Legal_Letters_Cancellation_of_Assigned_Letters'>");
						out.println("</a><u>Legal Letters - Cancellation of Assigned Letters</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can remove a debtor from a facility by terminating a client-debtor relationship from the system. If the debtor does not perform according to the agreed payment plans you will need to remove the debtor from the system and pass the responsibility of collections back to the client with this screen. ");
                    //----------------------------Legal Letters - to Clients
          	out.println("<p class=ug_headin><a NAME='Legal_Letters_to_Clients'>");
				  	out.println("</a><u>Legal Letters - to Clients</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can send legal letters to your clients if you are to terminate the contract in an event such as debtor defaulting. After selecting the facility you can generate the letter, which should be printed and posted. ");
					
						out.println("<p class=ug_headin><a NAME='Legal_Letters_to_Debtors'>");
						out.println("</a><u>Legal Letters - to Debtors</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>In similar cases, you can generate a letter to debtors too. Same steps as in Legal letters to Clients have to be followed. ");
					
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Client/Debtor_Maintenance'>");
						out.println("</a><u>Credit Maintenance - Client/Debtor Maintenance</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You are facilitated to edit the status of a client or a debtor here. You can view the list of clients and debtors separately by selecting the necessary Factoring Relationship. You can terminate, deactivate, blacklist, review or follow up a client or a debtor through this screen. ");
					
					
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Client_Facility_Maintenance'>");
						out.println("</a><u>Credit Maintenance - Client Facility Maintenance</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can change the status of a facility with the use of this screen. After picking a facility from the facilities list, you can change the status to Termination, Review or to Follow up.  ");
					
					//----------------------Credit_Maintenance_Client/Debtor_Relation_Maintenance
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Client/Debtor_Relation_Maintenance'>");
						out.println("</a><u>Credit Maintenance - Client/Debtor Relation Maintenance</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen is to change the status of the relationships between clients and debtors. Once you select a client, the system will show the lists of approved debtors and disapproved or deactivated relationships. You can change the relationship status to Termination, Deactivate, Disapprove, Review or Follow up. ");
					
					//------------------Credit Maintenance_Client/Debtor_Reactivation
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Client/Debtor_Reactivation'>");
						out.println("</a><u>Credit Maintenance - Client/Debtor Reactivation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can view the lists of deactivated clients and deactivated debtors from which you can select clients / debtors and make them active or to be followed up. ");
					
					//------------------Credit Maintenance_Client/Debtor_Reactivation
				/*	out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Client/Debtor_Approval_Cheque_Return'>");
						out.println("</a><u>Credit Maintenance - Client/Debtor Approval - Cheque Return</u>");
						out.println(" ");
					out.println("<p align='justify' class=rep-body>When cheques given by debtors are returned, after making an entry, it has to be approved by another user. This is the screen that lets you approve, disapprove or make a cheque return to be followed up. ");
           */
						//------------------Credit_Maintenance_Client/Debtor_Approval_Cheque_Return
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Client/Debtor_Approval_Cheque_Return'>");
						out.println("</a><u>Credit Maintenance - Client/Debtor Approval - Cheque Return</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When cheques given by debtors are returned, after making an entry, it has to be approved by another user. This is the screen that lets you approve, disapprove or make a cheque return to be followed up. ");
					
					//------------------Credit Maintenance - Debtor Limit Adjustments
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Debtor_Limit_Adjustments'>");
						out.println("</a><u>Credit Maintenance - Debtor Limit Adjustments</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen is the approval level of debtor limits becoming zero when a cheque is returned. ");
					
					
					//------------------Credit Maintenance - Debtor Limit Adjustments
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Client/Debtor_Relationship_Reactivation'>");
						out.println("</a><u>Credit Maintenance - Client/Debtor Relationship Reactivation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Deactivated client - debtor relationships can be reactivated here. By selecting a facility, you can view the deactivated debtors so that you can change the status to Review or Follow up. ");
					
					//------------------Credit Maintenance - Facility Extend
						out.println("<p class=ug_headin><a NAME='Credit_Maintenance_Facility_Extend'>");
						out.println("</a><u>Credit Maintenance - Facility Extend</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When a facility is to be extended, this screen can be used to send back the facility to the facility creation level for the necessary editions. ");










						

						
					
		
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
