
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - USER
//CREATED BY:NUWAN SW SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_Save_Change_User_Password extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt2;
	PreparedStatement pstmt;
	ResultSet rs;
  String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   
			Vector my_vector = new Vector();
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

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_expired_status=(String)m_sn_methods.met_formdata(reqstr,"hid_expired_status");  //added by ns on 09-12-2010
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CHANGE_PASSWORD(:1,:2,:3,:4,:5); END;");
					

       //String m_user_id = m_sn_methods.met_formdata(reqstr,"TXT_USER_ID");
			 String m_pwd = m_sn_methods.met_formdata(reqstr,"TXT_CONFIRM_PASSWORD");	
			 String m_screen_name = m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	

			callstmt.setString(1,m_username);
			callstmt.setString(2,m_pwd);
			callstmt.setString(3,m_screen_name);
			callstmt.setString(4,m_username);
			callstmt.setString(5,m_scr_name);
			
			callstmt.execute();
			callstmt.close();
			
					
   
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			if (m_expired_status.equals("EXPIERED")){
			//out.println("window.close();");
			out.println("window.open(\""+m_url+"/"+m_client_name+"login?screen_name=AF_LOGIN\",'AFS','left=0,top=0,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=0,fullscreen=1');");			
			}
			else{
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_Change_User_Password';");
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
