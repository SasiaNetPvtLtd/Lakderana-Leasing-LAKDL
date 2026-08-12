//created by :sandun on 29-01-2009
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_ODI_Alteration_Save extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;
	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

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
			String m_msg = "";
	    m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			String m_eff_date=(String)m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+
			                  (String)m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+
												(String)m_sn_methods.met_formdata(reqstr,"VAL_YEAR");
			
			
			m_url = m_class_url;
			conn.setAutoCommit(false); 
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_MAS_ODI_RATE_UPDATE(:1,:2,:3,:4); END;");
			
			callstmt.setString(1,m_eff_date);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_ODI_RATE"));
			callstmt.setString(3,m_username);	
			callstmt.setString(4,m_scr_name);
			callstmt.execute();
			
			callstmt.close();
			conn.commit(); 
      
			m_msg = "New ODI Rate Saved Succsessfully.";
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('"+m_msg+"');");			
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_Alteration_Odi';");
	  	out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
				
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
}
