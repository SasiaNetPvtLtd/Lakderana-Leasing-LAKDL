//--
//SCREEN NAME	:Application Application Approval 
//CREATED BY	:Delanjali
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_application_status_reversal extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt;
	public ResultSet rs;

	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),10000);
			//BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
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
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;


			stmt=conn.createStatement();

			String m_fschema_name=m_sn_methods.client_name.trim();
			int sel_stage=0;
			
			int m_chksql=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count")); 
			
			rs= stmt.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('AF_CR_PRO_APPLICATION_REVERSAL') ");
			if(rs.next()){
			sel_stage=rs.getInt(1);
			}


			for (int k=0; k<m_chksql; k++) 
			{

			String m_app=m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+k);
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
	
			if(m_chk.trim().equals("Y")){
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APP_REVERSAL(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+k));
			//callstmt.setString(2,"ENT_CON");
			callstmt.setString(2,"ENTERED");
			
			callstmt.setInt(3,sel_stage);
			callstmt.setString(4,"REVERSE");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_APP_STATUS_"+k));
			callstmt.setString(7,"AF_CR_PRO_APPLICATION_REVERSAL");
			callstmt.setInt(8,k);
			
			
				if  (!m_app.trim().equals("")) {
				callstmt.execute();
						
			}
							

			}		
			
			}

			
			
			callstmt.close();

			conn.commit();
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
	  	out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_application_status_reversal';");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

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
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}

		}

	}
}
		

