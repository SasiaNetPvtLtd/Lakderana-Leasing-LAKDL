// SCREEN NAME CHANGE PAYEE CODE
// CREATED BY DINETH MEEMANAGE
// CREATED DATE 2008-10-07

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Payee_Code extends HttpServlet {
		
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
	 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			String m_user="";
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			conn.setAutoCommit(false); 	
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_CHANGE_PAYEE_CODE(:1,:2,:3,:4,:5,:6); END;");
			out.println("m_maxentries"+m_maxentries);
			for (int j = 0; j < m_maxentries; j++) {
			
							String m_req="";
							//if(m_req.equals("on")){
			       	
							//callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_TXT_APP_NO"+(Integer.toString(j))));
						//	callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_TXT_APP_NO"+(Integer.toString(j))));
							String m_change_type = (String)m_sn_methods.met_formdata(reqstr,"TXT_ASSIGN_TYPE");
							
							if(m_change_type.equals("I")){
							m_req =(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));
							out.println("m_req"+m_req);
							 m_user =(String)m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NO"+(Integer.toString(j)));
							out.println("m_user"+m_user);
							if(m_user.equals("") || !m_req.equals("on")){
							continue;
							}
							else //if(m_chk_required.equals("on"))
							{
							
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_TXT_REF_NO_I"+(Integer.toString(j))));
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_TXT_PAY_CODE_I"+(Integer.toString(j))));


							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NO"+(Integer.toString(j))));
							
							//break;
							}
							//out.println("chk value"+m_chk_required);
							}
							else if(m_change_type.equals("B")){
							//callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_USER"+(Integer.toString(j))));
							m_user =(String)m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_N"+(Integer.toString(j)));
							m_req =(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));
							out.println("m_req"+m_req);
							
							if(m_user.equals("") || !m_req.equals("on")){
							continue;
							}
							else //if(m_chk_required.equals("on"))
							{
							
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_TXT_REF_NO_B"+(Integer.toString(j))));
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_TXT_PAY_CODE_B"+(Integer.toString(j))));
							
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_N"+(Integer.toString(j))));
							//break;
							}
							
							
							
							
							}
							callstmt.setString(4,m_username);
							
							out.println((String)m_sn_methods.met_formdata(reqstr,"TXT_REMARK"+(Integer.toString(j))));
							
							callstmt.setString(5,m_scr_name);
						  callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REMARK"+(Integer.toString(j))));  
							callstmt.execute();
							//}
				}
				callstmt.close();
				conn.commit(); 
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_scr_name+"'");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Changing_Payee_Code_New?chksql=main_page';");
		  out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
			
			
			}	catch (Exception E) {
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

