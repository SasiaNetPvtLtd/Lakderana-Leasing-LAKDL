
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - DESIGNATION
//ID:1.11 Employee Creation Process
//CREATED BY:M.M. WICKRAMASEKARA
//DATE/TIME:2006.07.19
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_employee_react_stage extends HttpServlet {
		
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
			String m_fschema_name=m_sn_methods.client_name.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
      String m_act_status=(String)m_sn_methods.met_formdata(reqstr,"hid_act_stage"); //status it is going to be changed into.
      String m_pre_status=(String)m_sn_methods.met_formdata(reqstr,"Hid_pre"); //previouse status from which records to be updated.
			

			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
						
			synchronized(this){ 
			
			conn.setAutoCommit(false);	
			
			int m_no=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count")); 

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_EMP_REACT_STAGE(:1,:2,:3,:4,:5); END;");
			
			for (int j=0; j<m_no; j++) 
			{
			String m_chk1=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+j);
			if (m_chk1.equals("")){
			m_chk1="N";
			}
			if (m_chk1.equals("Y")){
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_EMPLOYEE_CODE_"+j)).trim());
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(3,m_username);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"hid_act_stage"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_act_screen_name"));
			callstmt.execute();
			}
			
			}

			conn.setAutoCommit(true);			
			conn.commit();
			}
	

		
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_react_emp_prestage?chksql=main_page&status="+m_act_status+"&pre_status="+m_pre_status+"';");
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
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}

	
	
