/*
	*	DEVELOPED BY : INESH
	*	2018-01-08
	*	JB02012018-02276 NetAsset system documents upload facility
*/
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_document_types_save extends HttpServlet {
		
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
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			conn.setAutoCommit(false);

      		String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			//=============================================================================================================
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES_SAVE(:1,:2,:3,:4,:5,:6); END;"); 
			
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"HID_DOC_CODE")).trim());
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_DOCUMENT_CODE")).trim());
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_DOC_DESCR")).trim());
			callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_NUM_OF_SLOTS")).trim());
			callstmt.setString(5, m_username);
			callstmt.setString(6, m_scr_name);	
			
			//=============================================================================================================
			callstmt.execute();
			conn.setAutoCommit(true);
			callstmt.close();


	   		out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_document_types';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
		
			}catch (Exception E) {
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
			
	
	