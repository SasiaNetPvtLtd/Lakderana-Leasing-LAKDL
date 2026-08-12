//DEVELOPED BY SANDUN JAYATHILAKE ON 19-11-2008 FOR CLIENT CONTACT DETAIL UPDATING IN COLLECTION REPORT WITH AGE
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Collection_client_contact_updation extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		 
			PrintStream out = new PrintStream(res.getOutputStream());
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;			
			//out.println("-->"+m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
		  callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_CONTACTS(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE").trim());	
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_MOBIL").trim());	
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TEL").trim());	
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_FAX").trim());	
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CON_PERSON").trim());
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"hid_scr_name").trim());	
			callstmt.setString(7,m_username);
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_TEL_OFFICE").trim());
			callstmt.execute();
		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.close()");
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
