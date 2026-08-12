

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_Letters_Documt_new extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String marketing_header="";
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
						out.println("<p class=ug_headin><a NAME='LetDocHead'></a><u>Letters & Documents </u> ");
						
						marketing_header="<p align='justify' class=rep-body>Letters & Documents are segregated to ensure easy access. This module includes monthly statements, cheque return letters, termination and thanking letters, etc that you will send to the clients and the outside.";
						out.println(marketing_header);
						//end added by madhawa 2009-11-23

						
						out.println("<p class=ug_headin><a NAME='Monthly_Statement'></a><u>Monthly Statement</u> ");
						out.println("<p align='justify' class=rep-body>Monthly Statements with regard to a client can be generated through the system with the use of this screen. After specifying a date, you can generate statements as at that particular date.");
												//----------------- Cheque_Return_Letter ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Cheque_Return_Letter'>");
						out.println("</a><u>Cheque Return Letter </u>");
						out.println(" ");
						out.println("<p class=rep-body>For a selected cheque number or a receipt number, a predefined error statement can be attached in this screen.");
												//----------------- Termination_Letter ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Termination_Letter'>");
						out.println("</a><u>Termination Letter </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can generate notices of termination and letters of termination ");
											//----------------- Thanking Letters - For Clientl User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Thanking_Letters_For_Client'>");
						out.println("</a><u>Thanking Letters - For Client </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can generate courtesy letters to your clients who have fully paid the agreed amount. ");
						
						//----------------- Thanking_Letters_For_Introducer User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Thanking_Letters_For_Introducer'>");
						out.println("</a><u>Thanking Letters - For Introducer</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen helps you to send a courtesy letter to a lead source (introducer) of a client once that prospective client enters into an agreement with your company. ");
						
						//------------Thanking Letters - For Guarantor
						out.println("<p align='justify' class=ug_headin><a NAME='Thanking_Letters_For_Guarantor'>");
						out.println("</a><u>Thanking Letters - For Guarantor</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>In this screen, you are facilitated to send thanking letters for guarantors for presenting themselves as guarantors and for preparing necessary details to you to provide the services to your clients. ");
						
						// Commented by Thamali Jayatunga on 2009.12.04
						/* 
						//-----------Legal Actions - Initiation
						out.println("<p align='justify' class=ug_headin><a NAME='Legal_Actions_Initiation'>");
						out.println("</a><u>Legal Actions - Initiation</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You will go for legal actions if you are unable to locate an asset after termination of the agreement or if you are unable to cover the outstanding value of the facility after reselling the asset. Initiation of legal actions is the entry point of a legal action to the system. You can generate a legal action entry by selecting a finance number that is related to a client. ");
						//------------Legal_Actions_Update
						out.println("<p align='justify' class=ug_headin><a NAME='Legal_Actions_Update'>");
						out.println("</a><u>Legal Actions - Update</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Decisions taken at the courts, related to legal actions taken against clients, can be entered into the system through this screen. You can update decisions taken at the courts and documents required for the next session also.  ");
            */
						// Endded Comment by Thamali Jayatunga on 2009.12.04
					
						//----------------- Application Status Report User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						/*out.println("<p align='justify' class=ug_headin><a NAME='Application_Status_Rep'>");
						out.println("</a><u>Marketing - Application Status Report </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This is a list of application that are to be processed, but pending due to different reasons. Applications for which Purchase Orders are created and approved are listed here. Ideally you should maintain the number of applications in this list at a minimum. ");
						*/
						//----------------- Change Proforma User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						/*out.println("<p align='justify' class=ug_headin><a NAME='Change_Proforma'>");
						out.println("</a><u>Marketing - Change Performa Invoice </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Here you can easily modify data you entered in Pro-forma Invoices. ");
						*/
						
						//------------------client comments------------------------------------------------------------------------------------------------------------------------------------------------------------------------
						/*out.println("<p align='justify' class=ug_headin><a NAME='Client_comments'>");
						out.println("</a><u>Marketing - Client Comments </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Marketing Officers can enter comments into the system, which your clients make about your service, here. Such information will be displayed with client information in applications.");
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
