//DEVELOED BY : SANDUN   ON 19 01 2009

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_PRO_Save_Payment_Deletion extends HttpServlet {
		
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
			String m_save_procedure_name;
			String m_screen_name="";
			String m_app_no="";
			String m_vendor_code="";
			String m_pur_ord_no1="";
			String m_status="";
	     m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			
		
			m_url = m_class_url;
			conn.setAutoCommit(false); 
		
			
			int m_maxentries  = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
						
				
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_PAYMENT_DELETION(:1,:2,:3,:4); END;");
			
				for (int j = 0; j < m_maxentries; j++) {					      	
			     String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));   
						
							
							if(m_chk_required.equals("on")){							
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"HID_PAY_NO_"+(Integer.toString(j))));
							callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_TYPE"));
							callstmt.setString(3,m_username);
							callstmt.setString(4,m_scr_name);
							callstmt.execute();
							}
							
				}
				m_msg = "'Selected Payment Deleted successfully'";
						
				
			callstmt.close();
			conn.commit(); 

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");			
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_PRO_CR_Payment_Deletion?chksql=main_page';");
	   out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
				
			catch (Exception E) {
		  try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			
			/*
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			*/
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
