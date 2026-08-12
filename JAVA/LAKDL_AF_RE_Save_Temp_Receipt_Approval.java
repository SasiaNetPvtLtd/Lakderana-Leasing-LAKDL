

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Temp_Receipt_Approval extends HttpServlet {
		
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

      String m_scr_name   = (String)m_sn_methods.met_formdata(reqstr,"hid_src_name"); 
		
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			String m_rec_count = m_sn_methods.met_formdata(reqstr,"hid_no_rec");
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_TEMP_RECEIPT_APPROVAL(:1,:2,:3); END;");
           				
					for(int i=0;i<Integer.parseInt(m_rec_count);i++){
						if(m_sn_methods.met_formdata(reqstr,"CHK_RECEIPT"+i).equals("on")){
									
						callstmt.setString(1 ,m_sn_methods.met_formdata(reqstr,"hid_rec_no_"+i));
						callstmt.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_finance_no_"+i));
						callstmt.setString(3 ,m_username);
						callstmt.execute();
						}
						}				
			
		
			callstmt.close();



	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg(){");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_RE_Temp_Settlement_Approval?chksql=main_page';");
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
