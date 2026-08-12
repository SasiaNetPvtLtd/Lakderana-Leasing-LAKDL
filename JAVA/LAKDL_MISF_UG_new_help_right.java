

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL on 24-05-2007

public class LAKDL_MISF_UG_new_help_right extends javax.servlet.http.HttpServlet { 

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
			out.println("	<TITLE>Leasing And Loans - User Guide</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY bgcolor='#FFFFFF'>");
			out.println("<br>&nbsp;");
			out.println("<br>&nbsp;");
			out.println("<br>&nbsp;");
			out.println("<br>&nbsp;");
			out.println("<br>&nbsp;");
			out.println("<br>&nbsp;");
			out.println("<center><img src='"+m_html_client_url+"/images/h-guide.gif' width=160 height=23 border=0></center>");
			out.println("<center><img src='"+m_html_client_url+"/images/logo-old.gif' width=150 height=29 border=0></center>");
			out.println("</BODY>");
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
