
//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_Save_Screen_Position extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			
		
			PrintStream out = new PrintStream(res.getOutputStream());
	 //   out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			String m_app_no1="";
			String m_screen_name="";
	    m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
						
			int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
			int k=1;
			for (int j = 0; j < m_maxentries; j++) {
			
				
				
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_SCR_POS(:1,:2,:3); END;");
								
							//String m_scr=(String)m_sn_methods.met_formdata(reqstr,"TXT_SCREEN_NAME");
				
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_TXT_SCREEN_NAME"+(Integer.toString(k))));
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_POSITION"+(Integer.toString(k))));
							callstmt.setString(3,m_username);
						//	out.println(m_sn_methods.met_formdata(reqstr,"hid_TXT_SCREEN_NAME"+(Integer.toString(k))));
						//	out.println(m_sn_methods.met_formdata(reqstr,"TXT_POSITION"+(Integer.toString(k))));
							
							callstmt.execute();
             							
               k=k+1;
							
							}
							
	
				
		     
							
		
			
			
						
			
			
			
			
			

		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_Screen_Order';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
	}
	}
}
