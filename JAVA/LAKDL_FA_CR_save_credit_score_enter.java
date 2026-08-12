// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_CR_save_credit_score_enter extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
  String reqstr;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try { 

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			PrintStream out = new PrintStream(res.getOutputStream());
			
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
  
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_COLS");
			String m_cr_eval=(String)m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_EVAL");
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_CREDIT_SCORE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_EVAL"));
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SCORE_MODEL_CODE"));
			callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_SCORE_APP"));
			callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_SCORE"));
			callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS"));
			callstmt1.setString(8,m_screen_name);
			callstmt1.setString(9,m_username);
			callstmt1.execute();
			callstmt1.close();
			
			
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_CREDIT_SCORE_SAVE_DETAIL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");			
			int m_num=Integer.parseInt(m_scr_num);
			
			for(int i=1;i<m_num;i++){
				String m_sub_cat=(String)m_sn_methods.met_formdata(reqstr,"SUB_CAT_"+i+"");
				String m_rate_code=(String)m_sn_methods.met_formdata(reqstr,"RATE_"+i+"");
				String m_score=(String)m_sn_methods.met_formdata(reqstr,"SCORE_"+i+"");
				String m_comment1=(String)(m_sn_methods.met_formdata(reqstr,"COMMENT_"+i+""));

				callstmt2.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
				callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
				callstmt2.setString(3,m_sub_cat);
				callstmt2.setString(4,m_rate_code);
				callstmt2.setString(5,m_score);
				callstmt2.setString(6,m_comment1);
				callstmt2.setString(7,m_screen_name);
				callstmt2.setString(8,m_username);
				callstmt2.setInt(9,i);
				callstmt2.setString(10,m_cr_eval);
				callstmt2.execute();
			}
			callstmt2.close();
			conn.commit();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_CR_display_credit_score_enter';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
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
	}
	}
}
