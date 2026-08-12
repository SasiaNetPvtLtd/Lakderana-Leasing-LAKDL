
//SCREEN NAME:SYSTEM ADMINISTRATION - PASSWORD POLICY
//CREATED BY:THAMALI JAYATUNGA
//DATE/TIME:2010.07.02
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_password_policy_save extends HttpServlet {
    
    Connection conn;	
    String m_msg,m_url;
    CallableStatement callstmt;
    String reqstr;
    ServletOutputStream out = null;
    public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
        
        
        try {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()), 2000);
            reqstr = input.readLine();
            out = res.getOutputStream();
            /*
            HttpSession session = req.getSession(true);NETFAC_AF_CO_conn_methods m_init_methods = new NETFAC_AF_CO_conn_methods(); String html_client_home_url=m_init_methods.html_client_home_url.trim();
            
            Object done = (String)session.getValue("logon.isDone");  // marker object
            if (done == null) {
                out.println("<html><head>");
                out.println("<script language='JavaScript'>");
                out.println("function displaymsg() {");
                out.println("alert('Your login expired or invalid login');");
                out.println("window.location.href='"+html_client_home_url+"/login.htm' ;");
                out.println("}</script></head>");
                out.println("<body onload='displaymsg();'></body>");
                out.println("</html>");
                out.flush();
                return;
            }
            
            
            NETFAC_AF_CO_conn_methods m_sn_methods = new NETFAC_AF_CO_conn_methods(session);
						
						*/
						LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
						
            conn = m_sn_methods.met_user_validate(req);
            conn.setAutoCommit(false);
						
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
            
						
            callstmt=conn.prepareCall("BEGIN "+m_schema_name+".CO_CO_MAS_SAVE_PASSWORD_POLICY(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
            
					  callstmt.setString(1,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MIN_LENGTH")));
					  callstmt.setString(2,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MAX_LENGTH")));
					  callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MIN_LOWER")));
					  callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MIN_UPPER")));
					  callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MIN_NUM")));
					  callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_NUM_EMBED"));
						callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MIN_SPECIAL")));
						callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CHAR_EMBED"));
					  callstmt.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CHANGE_ATTEMPT")));
					  callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_USER_NAME_ALLOW"));
					  callstmt.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_REPEAT_PW")));
					  callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MIN_GAP")));
					  callstmt.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_EXPIRE_DAYS")));
					  callstmt.setString(14,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_EXPIRE_MSG")));
            callstmt.setString(15,m_username);
            callstmt.execute();
            callstmt.close();
            
            
            out.println("<HTML><HEAD>");
            out.println("<SCRIPT language='JavaScript'>");
            out.println("function displaymsg() {");
            out.println("alert("+m_msg+");");
            out.println("window.location.href='"+m_url+"/" + m_client_name + "AF_MAS_display_password_policy';");
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
				    try{conn.setAutoCommit(true);}catch(Exception e){}
            if(conn!=null){try{conn.close(); }catch(Exception e){}}
            if(out!=null){try{out.close();  }catch(Exception e){}}
        }
        
    }
}
