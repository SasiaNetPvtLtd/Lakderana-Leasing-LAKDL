

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Overall_System_References extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String System_User_header="";
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
						
						//-----------------Inquiry User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-23
						out.println("<p class=ug_headin><a NAME='Overall_System_Refe_header'></a><u>Overall System References </u> ");
						
						System_User_header="<p align='justify' class=rep-body>Overall System References of your system comprise of Nationalities, Banks, Holidays, Currencies, etc. These are more high level terms that are used in different parts of the system for different uses. ";
						out.println(System_User_header);
						//end added by madhawa 2009-11-23

						//-----------------Nationalities
						out.println("<p class=ug_headin><a NAME='Nationalities'></a><u>Nationalities</u> ");
						out.println("<p align='justify' class=rep-body>A nationality is the status of belonging to a particular nation by birth or naturalization. Examples are Sri Lankan, Indian and Japanese. Here you can define nationalities of your customers, employees and other individuals who interact with the system directly or indirectly. ");
						//----------------- Banks Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Banks'>");
						out.println("</a><u>Banks</u>");
						out.println(" ");
						out.println("<p class=rep-body>This screen lets you define banks that you will require to deal with and to refer. It is advisable that all banks that are locally present have to be entered into the system if you operate locally. If you operate internationally, you will have to include foreign banks too. When you entering details related to a corporate client, you will have to select the bank that deals with that organization.");
												//----------------- Bank Branches User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Bank_Branches'>");
						out.println("</a><u>Bank Branches </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>For a selected bank, you can introduce branches here. You will require including at least branches that operate in areas where your clients and other entities reside and work. These entries will be required when making payments, dealing with cheques and depositing receipts, etc.");
											//----------------- Business Sectors User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Business_Sectors'>");
						out.println("</a><u>Business Sectors</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Business sectors are the industries. When you specify client details, etc, this information will be required.");
						//----------------- Application Process User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Business_Subsectors'>");
						out.println("</a><u>Business Subsectors</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Under an industry, there can be multiple sectors. For instance, Tourism is a Business Subsector in the Business Sector Hotels and Travels. For a selected business sector you can define subsectors here. ");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Exposure_Categories'>");
						out.println("</a><u>Exposure Categories</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This refers to types of clients and prospects that you will come across. Examples are Individual, Partnership, Sole Proprietorship, Limited Liability and Public companies. You are required to define them here in order to display in the other modules.");
						
						
						out.println("<p align='justify' class=ug_headin><a NAME='Legal_Entities'>");
						out.println("</a><u>Legal Entities</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This allows to define the different types of customer entered to the system, such as, Individuals, Corporation, Government, Semi-Government, etc.");
						
						
						out.println("<p align='justify' class=ug_headin><a NAME='Holidays'>");
						out.println("</a><u>Holidays</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can specify the holidays that are applicable to your business here. Such holidays will be considered for interest calculation purposes.");

						out.println("<p align='justify' class=ug_headin><a NAME='Currencies'>");
						out.println("</a><u>Currencies</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You are able to create currency types that you are deemed to come across here. The currency type that you will carry out your business should be set as the Reporting currency. The reporting currency will be the default currency in the system. I.e. whenever the currency has to be specified through a drop down menu, that currency will be automatically selected. In screens where currency type is not to be specified, transactions will be done at reporting currency.");

						
					
						
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
