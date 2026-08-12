//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_PRO_Save_Application_Status_Change_Approval extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt1;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
  ServletOutputStream out = null;

	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			//PrintStream out = new PrintStream(res.getOutputStream());
			out = res.getOutputStream();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
		
			String m_screen_name="";
			String m_app_no="";
			String m_vendor_code="";
			String m_status="";
			String m_val_date="";
			String m_scr_name="";
			String m_deposit="";
	
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			conn.setAutoCommit(false); 
      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_RE_SAVE_APP_STATUS_CHANGE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
											
			for (int j = 0; j < m_maxentries; j++) {
			
					callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no"+(Integer.toString(j))));													
					callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"hid_client_code"+(Integer.toString(j))));													
					callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"hid_app_status"+(Integer.toString(j))));													
					callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"hid_app_status"+(Integer.toString(j))));													
					callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"hid_eff_date"+(Integer.toString(j))));			
					callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_COMMENT_"+(Integer.toString(j))));													
					m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_STATUS_TYPE_"+(Integer.toString(j)));										
					if(m_status.equals("Y")){
					callstmt1.setString(7,"Y");
					}
					else if(m_status.equals("N")){
					callstmt1.setString(7,"N");
					}
					else if(m_status.equals("C")){
					callstmt1.setString(7,"C");
					}
					else if(m_status.equals("D")){
					callstmt1.setString(7,"D");
					}
					callstmt1.setString(8,"APPROVE");
					callstmt1.setString(9,m_username);
					callstmt1.setString(10,m_scr_name);
					callstmt1.execute();							
				}
				
 //   	conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_PRO_Application_Status_Change_Approval?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
		/*catch (Throwable th) {
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
		}*/
		
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
