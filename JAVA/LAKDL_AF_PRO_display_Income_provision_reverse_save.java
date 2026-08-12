// DEVELOP BY :  UDARA FOR MRFL FACTORING    DATE:11-10-2013
 
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_display_Income_provision_reverse_save  extends HttpServlet { // LAKDL_AF_LT_Legal_Termination_save
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmtDet;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
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
			
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			String m_start_date  = m_sn_methods.met_formdata(reqstr,"hid_start_date");
			String m_end_date    = m_sn_methods.met_formdata(reqstr,"hid_end_date");
			String m_screen_name = m_sn_methods.met_formdata(reqstr,"hid_screen_name");
			
			String m_finance_no  = "";
			String m_status = "Y";
			String mm_status = "";
			
			m_msg = "'Information saved successfully'";
			
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_count"); 
			int m_num=Integer.parseInt(m_scr_num);
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_NON_PERFORM_ACTIVITY(:1,:2,:3,:4,:5); END;");
			
			out.println(m_screen_name);
			
			for(int i=0;i<m_num;i++){
					
					mm_status     = m_sn_methods.met_formdata(reqstr,"TXT_CHK_SELECT_"+i);
					m_finance_no = m_sn_methods.met_formdata(reqstr,"TXT_HID_FIN_"+i);				
					
					//out.println(mm_status);
					
					if(mm_status.equals("on")){
						
						//out.println(m_finance_no);
						
						
						callstmt.setString(1,m_finance_no);
						callstmt.setString(2,m_start_date);
						callstmt.setString(3,m_screen_name);
						callstmt.setString(4,m_status);
						callstmt.setString(5,m_username);
						callstmt.execute();
						
	
					}
					
					
			}
			
			
			
			//callstmt.close();
			
			
			conn.commit();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.close(); ");
			//out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_Income_Provision_sql_validations_normal?chksql=INCOME_SUSPENCE_REPORT_RPT_SUGGEST&start_date="+m_start_date+"&end_date="+m_end_date+"';");
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

