

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Geographic_Areas extends javax.servlet.http.HttpServlet { 

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
						
						//-----------------Geographic Areas User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-23
						out.println("<p class=ug_headin><a NAME='Geographic_Areas_header'></a><u>Geographic Areas </u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>In Geographic Areas, you can create a virtual mini map including the countries that you require in the system. In those countries you will define provinces, districts, cities and areas. This map will be used when you are entering details about clients, guarantors, lawyers, banks and so on.";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-23

						//-----------------Countries
						out.println("<p class=ug_headin><a NAME='Countries'></a><u>Countries</u> ");
						out.println("<p align='justify' class=rep-body>You have to specify countries with the use of this screen. This is required to specify addresses of clients, define bank branches, define branches of your company, entering vehicle yards, etc. ");
						//----------------- Provinces Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Provinces'>");
						out.println("</a><u>Provinces</u>");
						out.println(" ");
						out.println("<p class=rep-body>For a country there can be multiple provinces. You have to specify provinces of the countries for the above purpose.");
												//----------------- Districts User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Districts'>");
						out.println("</a><u>Districts </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Districts are the next level of geographical areas after countries and provinces. Specify the districts here.");
											//----------------- Cities User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Cities'>");
						out.println("</a><u>Cities</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>When entering addresses of clients, lawyers, yards, etc you will have to select the city. This is the screen to define cities in the system and those cities come under districts which come under provinces of a particular country.");
						//----------------- Area User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Area'>");
						out.println("</a><u>Area</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Areas are different locations of a city. A city can have multiple areas.");
						//-----------------Postal codes
						out.println("<p align='justify' class=ug_headin><a NAME='Postal_codes'>");
						out.println("</a><u>Postal codes</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Postal codes that relate with cities can be defined here.");
						
						//--------------Phone area codes
						out.println("<p align='justify' class=ug_headin><a NAME='Phone_area_codes'>");
						out.println("</a><u>Phone area codes</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Phone area codes also can be defined similar to postal codes.");
						
						
											
						
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
