

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Pricing extends javax.servlet.http.HttpServlet { 

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
						out.println("<p class=ug_headin><a NAME='Pricing'></a><u>Pricing</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>Pricing section lets you define charges and sub-charges that should appear in the system. You can specify different rates for charges such as tax, maintenance, etc.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

						//---------------Pricing_Default_Values
						out.println("<p class=ug_headin><a NAME='Pricing_Default_Values'></a><u>Pricing Default Values</u> ");
						out.println("<p align='justify' class=rep-body>You can enter default values that should appear in the Pricing screen here. In Pricing screen, for any new inquiry, you can specify your company's standard values for Interest rate, VAT percentage and period of financing, etc. This will ease you as you do not require reentering these values every time you fill in the Pricing form. ");
						//----------------- Charges Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Charges'>");
						out.println("</a><u>Charges</u>");
						out.println(" ");
						out.println("<p class=rep-body>You can define expenses that a client will incur here. Examples for charges can be Maintenance and Insurance.");
												//----------------- Sub Charges User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Sub_Charges'>");
						out.println("</a><u>Sub Charges </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Charges can be too broad to assign to a facility. Therefore, you can create sub-charges under charges and assign to facilities. Under the charges Maintenance and Insurance, there can be sub charges as Fuel charges and tyre replacement, and Broker fees and Premium.");
											//----------------- VAT Rates User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='VAT_Rates'>");
						out.println("</a><u>VAT Rates</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can specify VAT rates that apply for transactions, which are predefined, here. ");
						//----------------- Makes User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Assign_Payees_for_Sub_Charges'>");
						out.println("</a><u>Assign Payees for Sub Charges</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Payees are those who receive payments. An entry for a payee can be created by specifying a sub charge and other business details with the use of this screen.");
						//---------Variable Interest Rate Bases
						out.println("<p align='justify' class=ug_headin><a NAME='Variable_Interest_Rate_Bases'>");
						out.println("</a><u>Variable Interest Rate Bases</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When you offer your services at variable interest rates that are dependent on another external factor, you can specify the fixed difference between your rate and the external rate through this screen. For example, if you wish to maintain your interest rate 4% higher than the 3-month treasury bill rate, you have to set the Discount/Premium rate to 4.00.");
						//------------Variable base rates
						out.println("<p align='justify' class=ug_headin><a NAME='Variable_base_rates'>");
						out.println("</a><u>Variable Base Rates</u>");
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
