

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_administration_factoring extends javax.servlet.http.HttpServlet { 

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
						out.println("<p class=ug_headin><a NAME='Factoring'></a><u>Factoring</u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>Factoring section is all about the NetAsset Factoring system. All data definitions related to the factoring product need to be done here.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-26

						//---------------Product Features
						out.println("<p class=ug_headin><a NAME='Product_Features'></a><u>Product Features</u> ");
						out.println("<p align='justify' class=rep-body>Under factoring you can have different factoring products (services). These products can be different from each other as there can be different characteristics (features). This screen helps you to define such features so that you can assign them to factoring products later.");
						//----------------- Products ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Products'>");
						out.println("</a><u>Products</u>");
						out.println(" ");
						out.println("<p class=rep-body>You can create different factoring products by combining multiple Product Features that you fed into the system previously. These products are the services or packages that you will be offering to customers.");
												//----------------- Fees  User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Fees'>");
						out.println("</a><u>Fees </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can charge from your customers for the activities you perform or stages you pass. This screen is for you to define any type of fees that are applicable for factoring, thus you will be mapping them with products later. You can enter fees related to clients, debtors and ledgers, fee types - whether an amount or a percentage and activation point, etc.   ");
											//----------------Fee Packages User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Fee_Packages'>");
						out.println("</a><u>Fee Packages</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen lets you bundle fees that are applicable for factoring products. Once you prepare a fee package, you can directly assign it to factoring products. It will reduce your time as you do not need to select applicable fees from the total fee list every time you create a facility.");
						//----------------- Product Category ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Product_Category'>");
						out.println("</a><u>Product Category</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>A Product Category is the type of business a client or a debtor is into. After specifying the Business Sector and the Subsector of the business, this is a further narrowing down when entering data to an application. Here you are facilitated to define such Product Categories.");
						//--------Client/Debtor Classification
						out.println("<p align='justify' class=ug_headin><a NAME='Client_Debtor Classification'>");
						out.println("</a><u>Client/Debtor Classification</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Your clients or debtors can be categorized based on their performance. This will be important when entering client related data as the credit verifier would have a basic idea about the client or the debtor. You can define your own categorization of clients as this field will be used internally only. In documents that are given to clients or debtors, this information will not be included.");
						//-----------Collection Routes
						out.println("<p align='justify' class=ug_headin><a NAME='Collection_Routes'>");
						out.println("</a><u>Collection Routes</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Collection routes are specific roads or lanes the Collection Officers will be using to reach debtors and clients. One Collection Route can be allocated to one or more areas. When there are multiple areas you can specify the collection order, i.e. the order in which the Collection Officer reaches each area. This will improve the effectiveness and productivity of the Collection Officers as clear guidance is given on how the debtors are to be reached.");
							//-----------Collection Routes Assign
						out.println("<p align='justify' class=ug_headin><a NAME='Collection_Routes_Assign'>");
						out.println("</a><u>Collection Routes Assign</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is the screen which helps you to assign collection routes to Collection Officers. One Collection Officer can be assigned with multiple collection routes. ");
						//----------Factoring_Default_Values
						
						
						out.println("<p align='justify' class=ug_headin><a NAME='Factoring_Default_Values'>");
						out.println("</a><u>Factoring Default Values</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can define your company's standards on the credit limit granted to an ordinary client, credit period, interest charged and so on. As policies change in the company you can update these values when required. ");

						out.println("<p align='justify' class=ug_headin><a NAME='Remarks'>");
						out.println("</a><u>Remarks</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Remarks which will be commonly be used during collections can be defined through this screen.");
						
												
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
