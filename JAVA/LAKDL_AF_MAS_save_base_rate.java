
//SCREEN NAME	:SYSTEM ADMINISTRATION - VARIABLE BASE RATE
//CREATED BY	:Sandun
//DATE/TIME		:16-09-2008
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_base_rate extends HttpServlet {
		
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
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			conn.setAutoCommit(false);
     
			m_msg = "'Information saved successfully'";
			m_url = m_class_url; 
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			int m_row_num = Integer.parseInt(req.getParameter("number"));      
      String m_apply_date="";
		  String m_chk_status="";	
		  callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_BASE_RATE(:1,:2,:3,:4,:5); END;");
			
			for(int i=0;i<m_row_num;i++)	{			
			m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"chk_update_"+(Integer.toString(i)));										
			
			if(m_chk_status.equals("on")){
		  m_apply_date =m_sn_methods.met_formdata(reqstr,"base_rate_dd_"+(Integer.toString(i)))+"-"+
									  m_sn_methods.met_formdata(reqstr,"base_rate_mm_"+(Integer.toString(i)))+"-"+
										m_sn_methods.met_formdata(reqstr,"base_rate_yy_"+(Integer.toString(i)));				
      callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_base_code_"+(Integer.toString(i))));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"txt_new_rate_"+(Integer.toString(i))));
		  callstmt.setString(3,m_apply_date);
			callstmt.setString(4,m_scr_name);
			callstmt.setString(5,m_username);
			callstmt.execute();
			}			
			}
			
	    callstmt.close();
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_var_base_rate?chksql=main_page';");
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
