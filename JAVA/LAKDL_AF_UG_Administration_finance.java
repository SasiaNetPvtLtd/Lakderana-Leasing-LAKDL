

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Administration_finance extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Credit User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-26
						out.println("<p class=ug_headin><a NAME='Finance'></a><u>Finance</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>Finance section, which is linked with the Finance module of Asset Finance, helps you to setup your General Ledger structure. You can set authorization limits for users when approving payments here.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

						//---------------Account Codes
						out.println("<p class=ug_headin><a NAME='Account_Codes'></a><u>Account Codes</u> ");
						out.println("<p align='justify' class=rep-body>This screen assists you to create the charter of accounts in your company. ");
						//----------------- Licensee Bank Accounts Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Licensee_Bank_Accounts'>");
						out.println("</a><u>Licensee Bank Accounts</u>");
						out.println(" ");
						out.println("<p class=rep-body>You can define bank accounts you own, i.e. bank accounts your company has.  ");
												//----------------- Authorization Limits User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Authorization_Limits'>");
						out.println("</a><u>Authorization Limits </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can set different authorization levels for different users of the system for approvals, reversals, disbursements and deletions, etc. If the amounts exceed the limits the user will be disallowed to process further. For example, you can limit your Credit Officers to approve credit when it is less than a certain amount and let the Credit Manager to look after higher credit amounts. In this screen, you can select the user and the field to which limit should be set and set limits. ");
											//-----------------Cheque Return Narrationss User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Cheque_Return_Narrations'>");
						out.println("</a><u>Cheque Return Narrations</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Cheque return narrations are standard statements that are selected from a list when entering a cheque return scenario into the system and when the client is being informed about a cheque return. Such narrations can be predefined through this screen.");
						//----------------- Loan Facilities User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Loan_Facilities'>");
						out.println("</a><u>Loan Facilities</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A Credit Score model is a set of applicable credit score categories and sub categories. To evaluate creditworthiness of a client, you can prepare a set of parameters by collecting credit score categories and sub categories based on your preference. Through this you can evaluate your clients from all aspects rather than considering only one area of performance.");
						//-----------Loan Facilities Assign
						out.println("<p align='justify' class=ug_headin><a NAME='Loan_Facilities_Assign'>");
						out.println("</a><u>Loan Facilities Assign</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you assign facilities you wish to pledge for loans you get from other financial institutions. As your facilities change over time, you will have to assign facilities time to time dynamically.");

						
						//---------ODI Alterations
						out.println("<p align='justify' class=ug_headin><a NAME='ODI_Alterations'>");
						out.println("</a><u>ODI Alterations</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>As Overdue interest rate at your company is not fixed, you will need to update the interest rate occasionally. This screen helps you to change the ODI rate after the effective date you specify.");
					/*	//------------Variable base rates
						out.println("<p align='justify' class=ug_headin><a NAME='Variable_base_rates'>");
						out.println("</a><u>Variable base rates</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can enter the external rate that determines your internal interest rates. For instance, if your interest rate is maintaining at 4% higher than the 3-month treasury bill interest rate, you can change the 3-month treasury bill rate here, along with the date from which the new rate is effective.");
							//------------Repayment Intervals
						out.println("<p align='justify' class=ug_headin><a NAME='Repayment_Intervals'>");
						out.println("</a><u>Repayment Intervals</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you to name repayment intervals. You simply have to name the interval and enter the repayment duration. For instance, if number of payments per year is 1, it is an Annual payment. If number of payments per year is 365, it is a Daily payment.");
						//----------Applicable Charges by Sub Items
						
						out.println("<p align='justify' class=ug_headin><a NAME='Applicable_Charges_by_Sub_Items'>");
						out.println("</a><u>Applicable Charges by Sub Items</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Through this screen you can assign sub charges to different asset types, specifically for Item Categories and Sub-categories. After selecting the asset type you can enter an amount or a percentage that are to be incurred for that particular asset type.");
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
