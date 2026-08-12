
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_Save_System_reports_coll_target_setup extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt, callstmt1;
    String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();

			LAKDL_AF_CO_conn_methods  m_sn_methods = new LAKDL_AF_CO_conn_methods (); 
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
			String m_date;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      		String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_screen_name=(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");			
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_COLL_TARGET_DEL(:1,:2); END;");
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_TYPE")).trim());
			callstmt.setString(2,m_username);
			callstmt.execute();
			
			
			int row_count = Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"element_size"));
			
			out.println(" row_count : " + row_count);
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_COLL_TARGET(:1,:2,:3,:4,:5,:6); END;");
			
			m_date=m_sn_methods.met_formdata(reqstr,"TXT_EFFECTIVE_DATE_DD")+"-"+
									 m_sn_methods.met_formdata(reqstr,"TXT_EFFECTIVE_DATE_MM")+"-"+
									 m_sn_methods.met_formdata(reqstr,"TXT_EFFECTIVE_DATE_YY");
			
				for(int k=0;k<row_count;k++){
					
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_TYPE")).trim());
					callstmt1.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ITEM_SUB_CAT"+(Integer.toString(k)))));
					callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ITEM_CAT_CODE"+(Integer.toString(k)))));
					callstmt1.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CHK_REG"+(Integer.toString(k)))));
					callstmt1.setString(6,m_username);
					callstmt1.execute();	
				}
				
			callstmt1.close();
			callstmt.close();

	   		out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_System_reports_coll_target_setup?chksql=main_page';");
			
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
