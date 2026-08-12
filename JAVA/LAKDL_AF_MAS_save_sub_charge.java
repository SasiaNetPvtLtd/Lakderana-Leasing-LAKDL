//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - SUB CHARGE
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_sub_charge extends HttpServlet {
		
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
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      conn.setAutoCommit(false); //added by nuwan de silva on 22-11-2007
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_SUB_CHARGE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");

			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_TYPE_CODE")).trim());
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_TYPE_CODE")).trim());
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DESCRIPTION"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_MAINTENANCE_STATUS"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CHARGE_TYPE")); //added by nuwan de silva on 04-12-2007 
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_ACC_TYPE")); //added by Chandana on 08-01-2008
			callstmt.execute();
			callstmt.close();
			conn.commit(); //added by nuwan de silva on 22-11-2007___

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_display_sub_charge';"); //modified by nuwan de silva on 22-11-2007___
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		/*catch (Exception ex) {
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
		}*/
		
							// added by nuwan de silva 22-11-2007
			catch (Exception E) {
			try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			}
			finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			}


	}
}
