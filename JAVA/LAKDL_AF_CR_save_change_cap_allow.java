
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

// DEVELOPED BY : MAHELA FOR OFSCL LEASING    DATE:22-02-2007

public class LAKDL_AF_CR_save_change_cap_allow extends HttpServlet {
		
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
	   // out.println(reqstr);
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
			String m_chk_complete="";
      m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			//out.println("conn"+conn);
			//out.println(reqstr);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			int m_num=0;
			m_screen_name =(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			m_msg = "'Information saved successfully.'";
			m_url = m_class_url;
			String m_scr_num="0";
			
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			m_num=Integer.parseInt(m_scr_num);

			
			 for(int i=1;i<=m_num;i++){

							callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SAVE_CAPITAL_ALLOW(:1,:2,:3,:4); END;");						
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO_"+i).trim());
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CAPITAL_ALLOW_"+i).trim());
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(4,m_username);			   
							callstmt.execute();
							
				}

			conn.close();
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_change_cap_allow?chksql=main_page';");
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
			//out.println("window.history.back();"); 
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_change_cap_allow?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
	}
	}
}
