
//ID         :1.72 Marketing Team Process
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - ASSIGN TEAM MEMBERS
//CREATED BY : NUWAN DE SILVA
//DATE/TIME  :26-07-06
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_assign_members extends HttpServlet {
		
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

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_no_rec"));
			int k=1;
								
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_ASSIGN_MEMBERS(:1,:2,:3,:4,:5); END;");

					
				for (int j = 0; j < m_maxentries; j++) {
				
				  callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_TEAM_ID")).trim());
					String m_num=Integer.toString(j);
					//String m_user_id=	m_sn_methods.met_formdata(reqstr,"hid_TXT_USER_ID"+(Integer.toString(k)));
					String m_user_id=	m_sn_methods.met_formdata(reqstr,"hid_TXT_EMP_ID"+(Integer.toString(k)));
					if  (m_user_id.trim().equals("N")) {
						j=j-1;
					}
					else {
					
					
						//callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"hid_TXT_USER_ID"+(Integer.toString(k)))).trim());
						callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"hid_TXT_EMP_ID"+(Integer.toString(k)))).trim());
					//	m_user_id=m_sn_methods.met_formdata(reqstr,"hid_TXT_USER_ID"+(Integer.toString(k)));
			  		callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			      callstmt.setString(4,m_username);
						callstmt.setString(5,m_num);
			      if (m_user_id.trim().equals("")) {
						break;
					  }
						callstmt.execute();
						
			      
							}
					k=k+1;
				}
     
			callstmt.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_assign_members';");
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
