
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - CREDIT SCORE MODEL CREATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_score_model_creation extends HttpServlet {
		 
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
 
		  // Modified by Thamali Jayatunga on 2009.10.20, Replaced SCREEN_METHODS with LAKDL_AF_CO_conn_methods
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
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
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_SAVE_SCORE_MODEL(:1,:2,:3,:4,:5); END;");
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_SCORE_MODEL_CODE"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_DESCRIPTION"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_SCORE"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.execute();
			callstmt.close();


			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_SAVE_SCORE_MODEL_DETAIL(:1,:2,:3,:4,:5,:6,:7); END;");

			String m_model_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_SCORE_MODEL_CODE");
			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);

			for(int i=0;i<m_num;i++){
				String m_chk=(String)m_sn_methods.met_formdata(reqstr,"CHK_"+i+"");
				String m_hid_name=(String)m_sn_methods.met_formdata(reqstr,"HID_"+i+"");
				String m_min_val=(String)m_sn_methods.met_formdata(reqstr,"MIN_"+i+"");
				String m_max_val=(String)m_sn_methods.met_formdata(reqstr,"MAX_"+i+"");
				
				if(m_chk.equals("on")){
				callstmt.setString(1,m_model_code);
				callstmt.setString(2,m_hid_name);
				callstmt.setString(3,m_max_val);
				callstmt.setString(4,m_min_val);
				callstmt.setString(5,m_screen_name);
				callstmt.setString(6,m_username);
				callstmt.setInt(7,i);
				callstmt.execute();
				}
			}

			callstmt.close();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_score_model_creation?chksql=NEW';");
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
