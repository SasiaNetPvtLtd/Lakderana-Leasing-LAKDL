

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_Licensee_Organization_Structure extends javax.servlet.http.HttpServlet { 

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
						
						//----------------Licensee Organizational Structure User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						//add by madhawa 2009-11-23
						out.println("<p class=ug_headin><a NAME='Licensee_Organization_Structure_header'></a><u>Licensee Organizational Structure </u> ");
						
						Geographic_Areas_header="<p align='justify' class=rep-body>You have to define your company's organizational structure and communication flow in Licensee Organizational Structure section. You can further create teams and sub-teams and assign your employees to such teams. ";
						out.println(Geographic_Areas_header);
						//end added by madhawa 2009-11-23

						//-----------------Divisions
						out.println("<p class=ug_headin><a NAME='Divisions'></a><u>Divisions</u> ");
						out.println("<p align='justify' class=rep-body>Now you are at the first step to define/change your organizational structure. When you are feeding your organizational structure into the system, this is the most primary level for that. Divisions (or departments) are different functional areas of your company. As you create Divisions the menu bar of the system will be added with a module. ");
						//----------------- Sub Divisions Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Sub_Divisions'>");
						out.println("</a><u>Sub Divisions</u>");
						out.println(" ");
						out.println("<p class=rep-body>Sub Divisions are specific sections under a Division. Sub Divisions you create in the system will appear as sections in the menu under a module (a division).");
												//----------------- Locations User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Locations'>");
						out.println("</a><u>Locations </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Locations comprise of the head office and branches of your organization. You can add multiple contact persons under a location to specify the responsibility for a location.");
											//----------------- Designations User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Designations'>");
						out.println("</a><u>Designations</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Designations are required to generate the virtual hierarchy of your organization. Designation level will be used to identify seniorities. ");
						//----------------- Area User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='Employees_Employee_Entry'>");
						out.println("</a><u>Employees - Employee Entry</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Employees in your organizations have to be defined here. This is required to create system users. Employees' contact details, personal details and identification details are required to define an employee.");
						//-----------------Postal codes
						out.println("<p align='justify' class=ug_headin><a NAME='Employees_Reactivate_Employees'>");
						out.println("</a><u>Employees -Reactivate  Employees</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Employee entries have to be deactivated when employment is terminated due to any reason. If your organization hires the same employee again, you need to reactivate the deactivated entry again. After reactivation it should be approved by two users who are expected to be senior. This screen lets you to give the first approval for such a reactivation.");
						
						//--------------Employees - Approve Reactivation of Employees area codes
						out.println("<p align='justify' class=ug_headin><a NAME='Employees_Approve_Reactivation_of_Employees'>");
						out.println("</a><u>Employees - Approve Reactivation of Employees</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>This screen is to give the final approval for a reactivation of an employee entry. ");
						
						
						//--------------Teams
						out.println("<p align='justify' class=ug_headin><a NAME='Teams'>");
						out.println("</a><u>Teams</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>You can create teams in your company to as a high level employee grouping. Such team can be a team of employees who are from one division or from multiple divisions. Each team has a team leader (Team Head) to lead the team. ");

						//----------Sub Teams
						out.println("<p align='justify' class=ug_headin><a NAME='Sub_Teams'>");
						out.println("</a><u>Sub Teams</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Under a team you can specify one or more sub- teams. Each sub team has a head to lead the sub team and assist the team head to achieve the team's objectives. ");

						//--------------Assigning Employees to Sub Teams
						out.println("<p align='justify' class=ug_headin><a NAME='Assigning_Employees_to_Sub_Teams'>");
						out.println("</a><u>Assigning Employees to Sub Teams</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>After creating your team-sub team structure, you can assign employees to sub teams; then the team composition will be created automatically. ");

						
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
