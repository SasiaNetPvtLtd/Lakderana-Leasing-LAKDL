//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - LAKDL_AF_MAS_Save_Assign_Payee_to_Sub_Charges
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_Save_Assign_Payee_to_Sub_Charges extends HttpServlet {
	
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
			conn.setAutoCommit(false); //added by nuwan de silva on 22-11-2007
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_SUB_CHA_PAYEE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
			
			
			//callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
			
			String m_payee_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_CODE");
			if(m_payee_code.equals("")){
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
			}
			else
			{
				callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_CODE")).trim());
			}
			
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_TYPE_CODE")).trim());
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NAME").trim());
			//callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_ADD1")+" "+m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_ADD2") );
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_ADD1").trim());
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_ADD2").trim());
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG_NO").trim());
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_WHT").trim());
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE").trim());
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.setString(11,m_scr_name);
			callstmt.setString(12,"");
			callstmt.execute();
			conn.commit(); //added by nuwan de silva on 22-11-2007___
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_Assign_Payee_to_Sub_Charges';"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			out.flush();
			
		}
		
		// added by nuwan de silva 22-11-2007
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
