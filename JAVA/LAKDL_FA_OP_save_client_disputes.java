// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_OP_save_client_disputes extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
  String reqstr;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			PrintStream out = new PrintStream(res.getOutputStream());
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
  
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");

			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_SAVE_CLIENT_DISPUTES(:1,:2,:3,:4,:5); END;");
			callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS"));
			callstmt1.setString(4,m_screen_name);
			callstmt1.setString(5,m_username);
			callstmt1.execute();
			callstmt1.close();

			conn.commit();
			conn.close();
     
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_OP_display_client_disputes';");
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
