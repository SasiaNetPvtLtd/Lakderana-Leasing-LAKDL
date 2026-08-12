
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - LICENCEE SETTLEMENT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_loan_facilities extends HttpServlet {
		
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
			String m_loan_ficility_no="";
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_LOAN_FACILITIES(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			
			m_loan_ficility_no=(m_sn_methods.met_formdata(reqstr,"TXT_REF_NO")).trim();
			if(m_loan_ficility_no.equals("")){
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}else{	
			callstmt.setString(1 ,m_loan_ficility_no);
			}
			
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_LOAN_NO")).trim());
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_NAME")).trim());
			callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_ADD1")).trim());
			callstmt.setString(5,(m_sn_methods.met_formdata(reqstr,"TXT_ADD2")).trim());
			callstmt.setString(6,(m_sn_methods.met_formdata(reqstr,"TXT_TEL")).trim());
			callstmt.setString(7,(m_sn_methods.met_formdata(reqstr,"TXT_FAX")).trim());
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(9,m_scr_name);
			callstmt.setString(10,m_username);
			callstmt.execute();
			callstmt.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_display_loan_facilities';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();

		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
		}
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
