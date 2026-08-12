// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:26-01-2007
       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_save_Legal_Letter extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
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
			String m_facility_no="",m_client_no="",m_print="";
			
			m_facility_no=req.getParameter("facility_no");
			m_client_no=req.getParameter("client_no");		
			m_print=req.getParameter("print");
			
			m_msg = "'Information saved successfully ";
			//------------------------------------------------------------------------------------------
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_SAVE_LEGAL_LETTER(:1,:2,:3,:4,:5); END;");
				
				int tnum=0;
				
							callstmt1.setString(1,m_facility_no);
							callstmt1.setString(2,m_client_no);
							callstmt1.setString(3,"LEGAL_LETTER_TO_CLIENT");
							callstmt1.setString(4,"NEW");
							callstmt1.setString(5,m_username);
							callstmt1.execute();
							callstmt1.close();
							
			//-------------------------------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			//out.println("alert("+m_msg+"');");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_OP_Initial_Cheque_Return_Reminder_Letter?chksql=main_page&client_no="+m_client_no+"&debtor_code="+m_debtor_code+"&print=FALSE&receipt_no="+m_receipt_no+"&return_no="+m_return_no+"\";"); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_CR_Legal_Letter?chksql=main_page&facility_no="+m_facility_no+"&print=FALSE&client_no="+m_client_no+"\";"); 
			out.println("window.location.href=m_url;");
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

