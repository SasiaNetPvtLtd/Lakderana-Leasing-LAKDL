
//SCREEN NAME	:SYSTEM ADMINISTRATION -
//CREATED BY	:Sandun
//DATE/TIME		:20-10-2008
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MISF_Save_Security_Marketting extends HttpServlet {
		
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
			int m_row_num_as = Integer.parseInt(req.getParameter("as_number")); 
			int m_row_num_cl = Integer.parseInt(req.getParameter("cl_number")); 
      String m_application_no=(String)m_sn_methods.met_formdata(reqstr,"hid_app_no"); 
		
			
			String m_chk_status_cl="";	
			String m_chk_status_as="";	
			
			if(m_row_num_as>0){
		  callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_APP_STATUS(:1,:2,:3,:4,:5,:6,:7); END;");
			
			for(int i=0;i<m_row_num_as;i++)	{			
			m_chk_status_as=(String)m_sn_methods.met_formdata(reqstr,"AS_STATUS_CHKBX_"+(Integer.toString(i)));										
			if(m_chk_status_as.equals("on")){
			
			callstmt.setString(1,m_application_no);      
			callstmt.setString(2,"Y");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"as_hid_inv_no_"+(Integer.toString(i))));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"as_doc_type_"+(Integer.toString(i))));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"as_follo_up_"+(Integer.toString(i))));
		  callstmt.setString(6,"ASSET");
			callstmt.setString(7,m_username);
			callstmt.execute();
			}			
			}
			}
			
			if(m_row_num_cl>0){
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_APP_STATUS(:1,:2,:3,:4,:5,:6,:7); END;");
						
			for(int i=0;i<m_row_num_cl;i++)	{	
			
			m_chk_status_cl=(String)m_sn_methods.met_formdata(reqstr,"CL_STATUS_CHKBX_"+(Integer.toString(i)));										
			//out.println(m_chk_status_cl);
			if(m_chk_status_cl.equals("on")){
		  callstmt.setString(1,m_application_no);      
			callstmt.setString(2,"Y");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"cl_hid_inv_no_"+(Integer.toString(i))));
		  callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"cl_doc_type_"+(Integer.toString(i))));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"cl_follo_up_"+(Integer.toString(i))));
			callstmt.setString(6,"CLIENT");
			callstmt.setString(7,m_username);
			callstmt.execute();
			}			
			}
			}
					
	    callstmt.close();
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.close();");
			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file?chksql=main_page1';");
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
