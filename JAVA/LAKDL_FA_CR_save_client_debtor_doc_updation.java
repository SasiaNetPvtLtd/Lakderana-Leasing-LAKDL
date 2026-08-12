 
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_CR_save_client_debtor_doc_updation extends HttpServlet {

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

			int m_num=0;
			String m_scr_num="0";		
				
			m_msg = "'Information saved successfully ";
			//------------------------------------------------------------------------------------------
			String m_client_code=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			String m_facility_no=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			String m_debtor_code=m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE");
			
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			m_num=Integer.parseInt(m_scr_num);
			
			
			if(m_num>0){

				//------------------------------------------------------------------
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLI_DEBT_DOC_UPDATE(:1,:2,:3,:4,:5,:6,:7); END;");
				
				for(int i=1;i<=m_num;i++){

					String m_status=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_"+i).trim();
					String m_doc_code=m_sn_methods.met_formdata(reqstr,"TXT_DOC_CODE_"+i).trim();
					String m_doc_date=m_sn_methods.met_formdata(reqstr,"TXT_DOC_DATE_"+i).trim();
					String m_update_status=m_sn_methods.met_formdata(reqstr,"TXT_UPDATE_STATUS_"+i).trim();
					String m_check_status=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_"+i).trim();
					String m_comment=m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS_"+i).trim();
						
					if(m_update_status.equals("Y") && m_check_status.equals("Y")){
						callstmt1.setString(1,m_client_code);
						callstmt1.setString(2,m_facility_no);
						callstmt1.setString(3,m_debtor_code);
						callstmt1.setString(4,m_doc_code);
						callstmt1.setString(5,m_doc_date);
						callstmt1.setString(6,m_comment);
						callstmt1.setString(7,m_username);
						callstmt1.execute();
					}
				}
				callstmt1.close();
			}
			//-------------------------------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_CR_client_debtor_doc_updation';");
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

