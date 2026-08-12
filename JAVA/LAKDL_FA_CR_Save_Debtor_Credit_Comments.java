// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:22-11-2007

       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_CR_Save_Debtor_Credit_Comments extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
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
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			String m_date="";
			int m_num=0;
			int m_tnum=0;
			String m_scr_num="0";
			    
			//------------------------------------------------------------------------------------------
			String m_facility_code=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			String m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			String m_debtor_code=m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE");
			String m_comment=m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS");
	
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_DEBTOR_CR_COMMENTS(:1,:2,:3,:4,:5); END;"); 

			callstmt2.setString(1,m_facility_code);
			callstmt2.setString(2,m_client_code);
			callstmt2.setString(3,m_debtor_code);
			callstmt2.setString(4,m_comment);
			callstmt2.setString(5,m_username);
			callstmt2.execute();
			callstmt2.close();
			//------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Information saved successfully');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Debtor_Credit_Comments';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
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
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}

