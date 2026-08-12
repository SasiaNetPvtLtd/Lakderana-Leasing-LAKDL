
//--
//SCREEN NAME:user menu
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_Main_Menu extends javax.servlet.http.HttpServlet { 

		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

			try { 
	
				nf = java.text.NumberFormat.getInstance(Locale.US);
				nf.setMinimumFractionDigits(2);
	
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
				
				
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				String m_username=m_sn_methods.username;
				
				res.setStatus(HttpServletResponse.SC_OK); 
				res.setContentType("text/html"); 
	
				ServletOutputStream out = res.getOutputStream(); 

				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<TITLE>Asset Financing System</TITLE> ");
				out.println("</HEAD>");
				out.println("");
				out.println("<script language=\"JavaScript\">");
				out.println("function load_frame(){");
				out.println("document.frames[0].location.replace(\""+m_class_url+"/LAKDL_AF_CO_User_Menu\");");
				out.println("}");
				out.println("</script>");
				out.println("");
				out.println("");
				out.println("<script language=\"JavaScript\" type=\"text/javascript\">");
				out.println("document.write(\"<frameset name=\\\"Main_Screen\\\" cols=\\\"30%,*%\\\" frameborder=\\\"No\\\"  onload=\\\"load_frame()\\\"> \");");
				out.println("document.write(\"<frame name=\\\"trad_det\\\" src=\\\"\\\" marginwidth=\\\"0\\\" marginheight=\\\"0\\\" scrolling=\\\"Yes\\\" frameborder=\\\"Yes\\\"> \");");
				out.println("document.write(\"<frame name=\\\"fram3_det\\\" src=\\\"\\\" marginwidth=\\\"0\\\" marginheight=\\\"0\\\" scrolling=\\\"Yes\\\" frameborder=\\\"Yes\\\"> \");");
				out.println("document.write(\"</Frameset>\");");
				out.println("</script>");
				out.println("");
				out.println("<BODY >");
				out.println("</BODY>");
				out.println("</HTML>");

				out.flush();
				out.close();
			
			}
			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


