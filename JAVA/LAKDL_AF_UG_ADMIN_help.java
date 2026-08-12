

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL on 24-05-2007

public class LAKDL_AF_UG_ADMIN_help extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Administration - User Guide</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<frameset cols='300,*' frameborder='yes' framespacing='0' border='2' bordercolor='#0000FF'>");
			out.println("    <frame name='help-left' src='"+m_class_url+"/"+m_fschema_name+"AF_UG_admin_help_left' marginwidth='1' marginheight='0' scrolling='Auto' frameborder='yes' noresize bordercolor='#0000FF'>");
			out.println("    <frame name='help-right' src='"+m_class_url+"/"+m_fschema_name+"AF_UG_admin_help_right' marginwidth='1' marginheight='0' scrolling='auto' frameborder='no' noresize>");
			out.println("</frameset>");
			out.println("</HTML>");
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
