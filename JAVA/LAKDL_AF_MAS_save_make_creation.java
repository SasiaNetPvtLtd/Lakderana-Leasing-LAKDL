
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - MAKE CREATION
//ID:1.40 Make Creation Process
//CREATED BY:N.V.P.Chandana
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_make_creation extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();


      String m_close_status=(String)m_sn_methods.met_formdata(reqstr,"hid_close_status");
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_make_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_MAKE_CODE");
			String m_make_desc=(String)m_sn_methods.met_formdata(reqstr,"TXT_MAKE_DESC");
			String m_screen_name=(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_MAKE_CREATION(:1,:2,:3,:4,:5); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_MAKE_CODE").trim());
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_MAKE_DESC"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.execute();
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			if(m_close_status.equals("Y")){
			out.println("alert("+m_msg+");");
			//out.println("alert(window.opener.document.location);");
			if(!m_screen_name.equals("DACT")){
			out.println("window.opener.document.Form1.TXT_MAKE_CODE.value=\""+m_make_code+"\"");
			out.println("window.opener.document.Form1.TXT_MAKE_DESCRIPTION.value=\""+m_make_desc+"\"");
			}
			out.println("window.close();");
			}else{
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_make_creation';");
			}
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
