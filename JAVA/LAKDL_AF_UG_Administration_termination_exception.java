

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Administration_termination_exception extends javax.servlet.http.HttpServlet { 

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
						out.println("<p class=ug_headin><a NAME='Termination'></a><u>Termination and Exception</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>Termination related data definitions are handled in Termination and Exception section. Vendor blacklisting, termination rate and discounting rate values are some of the activities that are possible if you are a System Administrator.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

					
												//-----------------Seizers Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Seizers'>");
						out.println("</a><u>Seizers</u>");
						out.println(" ");
						out.println("<p class=rep-body>Details about seizers of assets are to be entered through this screen. You will have to specify contact details as well as payment details. ");
												//----------------- Lawyers User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Lawyers'>");
						out.println("</a><u>Lawyers</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Details of lawyers who are handling your legal actions should be fed into the system here. ");
											//----------------Vendor_Blacklisting User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Vendor_Blacklisting'>");
						out.println("</a><u>Vendor Blacklisting</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Vendors who do not perform can be blacklisted and removed from selection lists with the use of this screen. This will help you to avoid selecting nonfunctioning vendors over and over again. You also can generate a report of blacklisted vendors here.");
						//----------------- Termination Rate User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Termination_Rate'>");
						out.println("</a><u>Termination Rate</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen helps you to define termination rate for facilities. You can specify a rate range so that when a facility is terminated, the rate you specify will be checked against these values to see if the rate is within the range. This will help you to select a termination rate which is comfortable to you and to the client.");
						//-----------Client Blacklisting
						out.println("<p align='justify' class=ug_headin><a NAME='Client_Blacklisting'>");
						out.println("</a><u>Client Blacklisting</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>The system lets you blacklisting clients who default and who you do not require as clients any more. You can blacklist clients permanently and temporarily, and reactivate temporarily blacklisted clients again when you need.");

						
						//---------Discounting Rates
						out.println("<p align='justify' class=ug_headin><a NAME='Discounting_Rates'>");
						out.println("</a><u>Discounting Rates</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>To convert future payments and receipts into present monitory values, you will need a discount rate. You can update the company discount rate, every time the company changes it, with the use of this screen. You only have to specify the new discount rate and the date from which the new rate is applicable.");
							
							//-----------------------Early_Termination_Charges
						out.println("<p align='justify' class=ug_headin><a NAME='Early_Termination_Charges'>");
						out.println("</a><u>Early Termination Charges</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>If you charge additional charges from clients when terminating their facilities before maturity period, you can define such charges here by having a charge identity and an amount charged.");
						//-------------Missing_Assets
						out.println("<p align='justify' class=ug_headin><a NAME='Missing_Assets'>");
						out.println("</a><u>Missing Assets</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>In case of a loss of an asset, you can make a note of such assets here. ");
						//-----------Yards
						out.println("<p align='justify' class=ug_headin><a NAME='Yards'>");
						out.println("</a><u>Yards</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>To park vehicles and keep other assets that you possess after termination of facilities, you are maintaining yards. You can feed details of those yards into the system.");

				
				
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
