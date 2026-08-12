import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Follow_Up_Comment extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
    String reqstr;
	Statement stmt;
	public ResultSet rs;
	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	// synchronized(this){ 

		try {

			// BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			// reqstr = input.readLine();  
			// System.out.println("Received request with body: " + reqstr); // Debugging line to check the request body
		 
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

				String m_comment_id = "";

				String m_finance_no = req.getParameter("finance_no");
				String m_action = req.getParameter("txt_action");
				String m_assign_per = "";
				String m_comment = req.getParameter("comment");
				String m_action_code = req.getParameter("action_code");

				String fDay = req.getParameter("FOLLOW_DAY");
				String fMonth = req.getParameter("FOLLOW_MONTH");
				String fYear = req.getParameter("FOLLOW_YEAR");

				String screen_type = req.getParameter("SCREEN_NAME");

				String m_action_new = "";

				if(screen_type.equals("EDIT")) {	
					m_action_new = req.getParameter("txt_action_new");
					m_comment_id = req.getParameter("hid_comment_id");
					m_assign_per = req.getParameter("TXT_ASSIGN_TO");
					m_action = m_action_code;
				}else {
					m_action_new = m_action;
					m_comment_id = "-";
					m_assign_per = req.getParameter("TXT_ASSIGN_PER");
				}	



				String m_follow_up_date = null;
				if (fDay != null && !fDay.trim().equals("")) {
					m_follow_up_date = fDay + "-" + fMonth + "-" + fYear;
				}

			// System.out.println("Finance No: " + m_finance_no);
			// System.out.println("Action: " + m_action);
			// System.out.println("Assign To: " + m_assign_per);
			// System.out.println("Comment: " + m_comment);
			// System.out.println("Follow-Up Date: " + m_follow_up_date);
			
		  	callstmt=conn.prepareCall(" BEGIN "+m_schema_name+".AF_RPT_SAVE_FOLLOW_UP_COMMENTS(:1,:2,:3,:4,:5,:6,:7,:8,:9); END; ");
			callstmt.setString(1, m_comment_id);
			callstmt.setString(2, m_finance_no);
			callstmt.setString(3, m_action);
			callstmt.setString(4, m_follow_up_date); // Passing our combined 'DD-MM-YYYY' string directly
			callstmt.setString(5, m_assign_per);
			callstmt.setString(6, m_comment);
			callstmt.setString(7, m_username);
			callstmt.setString(8, screen_type);
			callstmt.setString(9, m_action_new);
			callstmt.execute();
		
			conn.close();

	   		out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("if (window.opener) { window.opener.location.reload(); }");
			out.println("window.close();");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			// out.println("function displaymsg() {");
			// out.println("alert("+m_msg+");");
			// out.println("window.close()");
			// out.println("}</SCRIPT></HEAD>");
			// out.println("<body onload='displaymsg();'></body>");
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
		// }
	}
	}
}
