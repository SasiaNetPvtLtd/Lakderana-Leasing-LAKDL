// CRAETED BY SAJITH MENDIS ON 12-02-2014

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_Save_Lending_targets extends HttpServlet {
		
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
		
			PrintStream out = new PrintStream(res.getOutputStream());
	 //   out.println(reqstr);
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
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
			
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_MAS_LENDING_TARGETS(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			for (int j = 0; j < m_maxentries; j++) {
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"HID_USER_ID"+(Integer.toString(j))));
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NAME"+(Integer.toString(j))));
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION"+(Integer.toString(j))));
							callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_MONTH"));
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_YEAR"));
							callstmt.setString(6,m_username);
							callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_TARGET_CASES"+(Integer.toString(j))));
							callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_TARGET_VALUE"+(Integer.toString(j))));
							callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"+(Integer.toString(j))));
							callstmt.execute();
							//}
				}
				
				////////////////////////////////////////////////////////////////////////////////////////
		  callstmt.close();
			conn.commit(); //added by nuwan de silva on 03-09-07----------------------
			//conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_set_lending_targets';");
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