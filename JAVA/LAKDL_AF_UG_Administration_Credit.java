

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Administration_Credit extends javax.servlet.http.HttpServlet { 

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
						out.println("<p class=ug_headin><a NAME='Credit'></a><u>Credit</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>This Credit section directly corresponds with the Credit module in Asset Finance. You can create credit score modules that you want to use when evaluating applications and creditors and set rating defaults.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

						//---------------Brokers
						out.println("<p class=ug_headin><a NAME='Brokers'></a><u>Brokers</u> ");
						out.println("<p align='justify' class=rep-body>This screen lets you include details of brokers into the system. You will need the brokers' personal details, contact details and commission details in order to complete filling in the form.");
						//----------------- Credit Score Categories Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Credit_Score_Categories'>");
						out.println("</a><u>Credit Score Categories</u>");
						out.println(" ");
						out.println("<p class=rep-body>You will have to evaluate your customers in terms of creditworthiness before providing them services in order to minimize defaulting. The system allows you to define your own credit score evaluations. As the first step, here you need to define Credit Score Categories. It would be meaningful if you categorize credit scores from the basis of the score, i.e. e.g. Financial statements, CRIB reports, References, Asset cover and so on. ");
												//----------------- Credit Score Sub-categories User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Score_Sub_categories'>");
						out.println("</a><u>Credit Score Sub-categories </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Credit Score sub categories are to have a structure that breaks down broad credit score categories to more meaningful areas. You can generate such subcategories that directly link with categories and create the sequence of subcategories that appear under a category.");
											//----------------- Credit Score Ratings User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Score_Ratings'>");
						out.println("</a><u>Credit Score Ratings</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Credit score ratings are to obtain a measurement scale for credit scores. You can segment the whole range from 0% to 100% into multiple ranges as very bad for 0%-20%, bad for 20%-40% and likewise. ");
						//----------------- Credit Score Models User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Credit_Score_Models'>");
						out.println("</a><u>Credit Score Models</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A Credit Score model is a set of applicable credit score categories and sub categories. To evaluate creditworthiness of a client, you can prepare a set of parameters by collecting credit score categories and sub categories based on your preference. Through this you can evaluate your clients from all aspects rather than considering only one area of performance.");
						//---------RMV Agents Rate Bases
						out.println("<p align='justify' class=ug_headin><a NAME='RMV_Agents'>");
						out.println("</a><u>RMV Agents</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Details of the agents of Registrar of Motor Vehicles (RMV) can be kept in the system. You have to specify contact and personal details and payment details here.");
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
