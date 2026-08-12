//--
//SCREEN NAME:
//CREATED BY: SANDUN 
//DATE/TIME:02/10/2008 
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MISF_Save_Asset_Insurance_Detail_new extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	String m_client_name;
	ServletOutputStream out = null;


	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false); 

			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			
			
      m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			//out.println("conn"+conn);
			//out.println(reqstr);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			
			m_msg = "'Information saved successfully.'";
			m_url = m_class_url;				
			
			String m_start_date=(String)m_sn_methods.met_formdata(reqstr,"start_dd")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"start_mm")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"start_yy");
			
			String m_end_date= (String)m_sn_methods.met_formdata(reqstr,"end_dd")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"end_mm")+"-"+
													(String)m_sn_methods.met_formdata(reqstr,"end_yy");
													
													
     	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_IS_PRO_SAVE_ASET_INSUR_DETA(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
						
			callstmt.setString(1,(String)m_sn_methods.met_formdata(reqstr,"hid_finance_no"));
			callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"txt_policy_no"));
			callstmt.setString(3,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_sum_in")));
			callstmt.setString(4,m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"txt_premium")));
			callstmt.setString(5,(String)m_sn_methods.met_formdata(reqstr,"txt_insurance_done"));
			callstmt.setString(6,(String)m_sn_methods.met_formdata(reqstr,"txt_in_company"));
			callstmt.setString(7,(String)m_sn_methods.met_formdata(reqstr,"hid_asset_deta"));			
			callstmt.setString(8,(String)m_sn_methods.met_formdata(reqstr,"hid_invo_no"));
			callstmt.setString(9,m_start_date);
			callstmt.setString(10,m_end_date);
			callstmt.setString(11,m_username);
			callstmt.setString(12,(String)m_sn_methods.met_formdata(reqstr,"TXT_REMARK"));//Added By Sandun on 09-01-2009
			callstmt.execute();			
		  callstmt.close();
			conn.commit(); 
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg(),window.close()'></body>");
			out.println("</html>");

			out.flush();
      out.close();
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
}
