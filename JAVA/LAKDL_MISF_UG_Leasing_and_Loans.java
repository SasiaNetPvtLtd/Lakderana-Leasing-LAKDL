

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_MISF_UG_Leasing_and_Loans extends javax.servlet.http.HttpServlet { 

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
						out.println("<p class=ug_headin><a NAME='Leasing_and_Loans'></a><u>Leasing and Loans</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>If you provide Leasing and Loans facilities to your customers with the use of the system, this section contains the reports that are applicable for leasing and loans operations. Data you entered related to your customers, assets, employees, users, etc are processed for reporting purposes. Reports related to follow ups, credit, invoicing and collections are useful for the middle level management for decision making. For the senior management, a separate section is reserved with high level, abstract reports such as sales reports and third level reports. ";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

						//---------------Follow Up - Report
						out.println("<p class=ug_headin><a NAME='Follow_Up_Report'></a><u>Follow Up - Report</u> ");
						out.println("<p align='justify' class=rep-body>This report displays the activities that required to be followed up during a specified period of time. You can view completed activities that are already followed up and those which are still pending separately. You can filter those activities by action date, the user who assigned, the user to whom the activity is assigned, division and sub-division. ");
						//----------------- Follow Up - History Report ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Follow_Up_History_Report'>");
						out.println("</a><u>Follow Up - History Report</u>");
						out.println(" ");
						out.println("<p class=rep-body>Within a specified period of time, you can view the follow up history of a particular facility with the use of this report. These follow ups can include both completed and pending activities.");
												//----------------- Invoicing - Adjustment Report ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Invoicing_Adjustment_Report'>");
						out.println("</a><u>Invoicing - Adjustment Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report provides details of the adjustments made to invoices. ");
											//-----------------Invoicing - Details Report User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Invoicing_Details_Report'>");
						out.println("</a><u>Invoicing - Details Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report displays the details related to a selected invoice. ");
						//----------------- Senior Management - Sales Report User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Senior_Management_Sales_Report'>");
						out.println("</a><u>Senior Management - Sales Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The senior management is facilitated to view a sales report for each team you define. This is applicable for marketing teams who bring in sales as performance of those marketing teams can be viewed and compared. Once you select a team, you can view individual performances and also you can view detailed information such as amounts obtained from each sale, etc.");
						//---------Senior Management - Second Level Report
						out.println("<p align='justify' class=ug_headin><a NAME='Senior_Management_Second_Level_Report'>");
						out.println("</a><u>Senior Management - Second Level Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report is to show inquiries and applications in different stages. You can view details of entered inquiries, processed inquiries, pending applications, completed applications, applications that are pending verifications are listed in separate sections.");
						//------------Credit - Agreement Register
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Agreement_Register'>");
						out.println("</a><u>Credit - Agreement Register</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Agreement register is the list of facilities that are confirmed during the specified time period. For all the facilities listed in this list, agreements are signed and finalized. You can view separate lists of agreements for live (active) agreements, terminated agreements and cancelled agreements. You have to initially run a report for a new time period or when the status of facilities are changed after running previously. When you run, previously unprocessed facilities will be processed. Then you can click View Report button to view the facilities that are added to the list by running. ");
							//------------Credit - Application Process Report
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Application_Process_Report'>");
						out.println("</a><u>Credit - Application Process Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is the list of applications that are existent in the system. You can view the status of the application, user who entered data into the system, application details and client details, etc. You can select an application and a client to see details of only that particular application. ");
						//----------Credit - Finance Status Report
						
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Finance_Status_Report'>");
						out.println("</a><u>Credit - Finance Status Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can select an application or an inquiry to view related details. You can view details such as inquiry details, application details, finance details, asset details, client details, etc for the selected facility.");
						
						//-------------Credit_Rental_Details_Report
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Rental_Details_Report'>");
						out.println("</a><u>Credit - Rental Details Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report displays a list of applications along with the capital and interest components due for the date you specify. For each application, you can view pricing details, pro-forma invoice details amounts charged for each installment as capital repayment and interest component.");
						
						//----------------Credit_Contract_Details_Report
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Contract_Details_Report'>");
						out.println("</a><u>Credit - Contract Details Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report is a comprehensive document for contracts. This contains details related to clients, guarantors, assets, rental summary and transaction history in separate sections so that you can view these details clearly.");
						//-----------------Credit_Contract_Balance_Report
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Contract_Balance_Report'>");
						out.println("</a><u>Credit - Contract Balance Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can generate a report that contains details of all facilities. This report can be used to identify balance outstanding, future rental amounts, unearned income, etc. ");
						
						//---------------------Credit - Activation Error Log
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Activation_Error_Log'>");
						out.println("</a><u>Credit - Activation Error Log</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Activation errors you specify, when processing facilities, can be viewed as a report here. Once you specify the date range, the report gives a list of facilities with the error log you have been maintaining.");
						
						
						//------------Credit - Termination Report
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Termination_Report'>");
						out.println("</a><u>Credit - Termination Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report is to display terminated agreements. You can specify the time period during which you need to see terminated facilities. Further you can view facilities under different types of terminations separately.");
						//----------------Credit - Exposure And Arrears Statement
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Exposure_And_Arrears_Statement'>");
						out.println("</a><u>Credit - Exposure And Arrears Statement</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report shows a classification of facilities by the exposure level. If shows how many contracts exist under Rs.1million exposure level and total exposure of them, how many contracts exist between Rs.1million and Rs.5million and total exposure of them and likewise.");
						//--------------Credit - ODI Waved Off Report
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_ODI_Waved_Off_Report'>");
						out.println("</a><u>Credit - ODI Waved Off Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can generate a report related to over due interest wave offs. Once you select a client and a finance number, you can view the overdue amounts and the adjustments made.");
						//--------------------Credit - Old Repayment Schedule
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Old_Repayment_Schedule'>");
						out.println("</a><u>Credit - Old Repayment Schedule</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You have to select a finance number to view payment details of a facility through this screen. You can view due amounts in each installment, amounts paid and outstanding and total balance related to the selected facility.");
						//----------------Credit - Audit Reports - Administration 
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Audit_Reports_Administration'>");
						out.println("</a><u>Credit - Audit Reports - Administration </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Audit Reports display different types of data entries that happened during the specified period of time. For example, you can view the users, banks, holidays, yards, missing assets, and many more records you created mainly in the Administration section. ");
						//------------Credit - Audit Reports - Transactions
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Audit_Reports_Transactions'>");
						out.println("</a><u>Credit - Audit Reports - Transactions</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report displays an audit report related to transaction menus. You can select the necessary transaction menu and view additions and changes to the menu. ");
						
						//-------------Collection - Asset Insurance Details
						out.println("<p align='justify' class=ug_headin><a NAME='Collection_Asset_Insurance_Details'>");
						out.println("</a><u>Collection - Asset Insurance Details </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report helps you to select a facility and view insurance details of assets related to the specified time duration. There you can view the next insurance dates, next registration dates and similar dates that are specific to the asset financed. ");
						
						
						//-----------Collection - Collection Report With Age
						out.println("<p align='justify' class=ug_headin><a NAME='Collection_Collection_Report_With_Age'>");
						out.println("</a><u>Collection - Collection Report With Age </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>By specifying a client and a collection officer, you can view details related to the ageing of the client payments and collection officer performance.  ");
						
						//Collection - Collection Report
						out.println("<p align='justify' class=ug_headin><a NAME='Collection_Collection_Report'>");
						out.println("</a><u>Collection - Collection Report</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Collection Report is a summary of active facilities in terms of collections. As you are allowed to select fields that should appear in the report, you can generate customizable reports that provide only the specific information you require. This report basically shows the amounts collected and amounts to be collected for each facility and totals. It can be made more detailed as it can highlight responsibilities and display amounts of post dated cheques, over dues, rentals and so on. You are facilitated to view Collection Reports that are specific for a particular client or a Collection officer. Through this you can view the creditworthiness of clients and performance of Collection Officers.");
						
						//
						out.println("<p align='justify' class=ug_headin><a NAME='Collection_P_D_Cheque_Report'>");
						out.println("</a><u>Collection - P D Cheque Report </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This report displays the current status of post dated cheques that are entered after a date you specify. It helps you to have an understanding about the amount of post dated cheques that are in the system, current status of the cheque, etc. You can filter records in the report by the bank branch, status of the cheque, finance number or even by the cheque number.  ");
						
						
												
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
