

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL user guide on 24-05-2007

public class LAKDL_AF_UG_System_User extends javax.servlet.http.HttpServlet { 

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
						out.println("<p class=ug_headin><a NAME='System_User_header'></a><u>System User </u> ");
						
						System_User_header="<p align='justify' class=rep-body>In order to provide access to the system, System User section lets the System Administrator to create user accounts and user groups. The System Administrator has to determine what screens should be displayed to the each user/user group and what screens should be given for execution for them. Password changing for users has to be done here.";
						out.println(System_User_header);
						//end added by madhawa 2009-11-23

						//-----------------User Group
						out.println("<p class=ug_headin><a NAME='User_Group'></a><u>User Group</u> ");
						out.println("<p align='justify' class=rep-body>User groups are categories of users who are allowed to use the system. You can define user groups and specify access rights those groups should have. This is done by selecting screens a user group should be allowed to view and to make changes. By creating user groups and assigning users to user groups, you can reduce the burden of specifying access rights to individual users every time you add users. ");
						//----------------- Users User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p class=ug_headin><a NAME='Users'>");
						out.println("</a><u>Users</u>");
						out.println(" ");
						out.println("<p class=rep-body>Users are individual entities that are authorized to use the system. After selecting an employee as a user, you can assign the user to a User Group which is created previously. Then you do not have to set access rights for each and every screen. ");
												//----------------- Quotation User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='User_Approval_1'>");
						out.println("</a><u>User Approval 1 </u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Every time a user account is created or modified, it should be approved by two uses. This is the screen to give first approval.");
											//----------------- Quotation Approval User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='User_Approval_2'>");
						out.println("</a><u>User Approval 2</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>With this screen you can give the second approval for a user account creation or modification.");
						//----------------- Application Process User Guide ------------------------------------------------------------------------------------------------------------------------------------------------
						
						out.println("<p align='justify' class=ug_headin><a NAME='User_List'>");
						out.println("</a><u>User List</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>User list shows the list of all users in the system. You can generate a document that contains the list of modules a particular user has access.");
						
						out.println("<p align='justify' class=ug_headin><a NAME='Change_Password'>");
						out.println("</a><u>Change Password</u>");
						out.println(" ");
						out.println("<p align='justify' class=rep-body>Users are allowed to change the current password and have a new password. This screen facilitates for that.");

						
						
					
						
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
