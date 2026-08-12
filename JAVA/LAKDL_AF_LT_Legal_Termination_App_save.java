// DEVELOP BY :  DISNAKA FOR OFSCL FACTORING    DATE:2011-11-08


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_LT_Legal_Termination_App_save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			
			m_msg = "'Information saved successfully'";
			
			String m_status;
			String m_termination_no;
			String m_approve;
			String m_comment;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_LT_SAVE_LEG_TERMINATION_APP(:1,:2,:3,:4); END;");
			
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);

			if(m_num>0){
				
				
				for(int i=1;i<=m_num;i++){
					
					m_status         = m_sn_methods.met_formdata(reqstr,"received_"+i);
					m_termination_no = m_sn_methods.met_formdata(reqstr,"ltNo_"+i);
					m_approve        = m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_"+i);
					m_comment        = m_sn_methods.met_formdata(reqstr,"comment_"+i);
					
					
					
					if(m_status.equals("on")){
						
					
							callstmt.setString(1,m_termination_no);
							callstmt.setString(2,m_comment);
							callstmt.setString(3,m_approve);
							callstmt.setString(4,m_username);
							callstmt.execute();
						

						
					}
				}
			}
			//callstmt.close();
			
			
			conn.commit();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination_App';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			out.flush();
			
		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
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
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
}
