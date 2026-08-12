// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
   
    
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_CR_Save_client_facility_approval extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
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
 			    
			conn.setAutoCommit(false);
			    
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully ";
			m_url = m_class_url;
					
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_FACILITY_APPROVAL(:1,:2,:3,:4); END;");

			String m_scr_num1=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num1=Integer.parseInt(m_scr_num1);
			
			for(int i=0;i<m_num1;i++){
				String m_action=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_"+i+"");
				
				if(!m_action.equals("O")){
				callstmt1.setString(1,(String)m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO_"+i+""));
				callstmt1.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_COMMENT_"+i+""));
				callstmt1.setString(3,m_action);
				callstmt1.setString(4,m_username);
				callstmt1.execute();
				}
			}
			callstmt1.close();

			   
			conn.commit();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+"');");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_CR_display_client_facility_approval';");
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
