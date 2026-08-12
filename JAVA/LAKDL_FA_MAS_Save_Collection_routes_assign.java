// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:21-09-2006


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_MAS_Save_Collection_routes_assign extends HttpServlet {
		
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
			//**************************************************************	
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			//out.println(reqstr);
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			conn.setAutoCommit(false);
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_MAS_SAVE_COLL_ROUTES_ASSIGN(:1,:2,:3,:4,:5); END;");
			
			String m_officer_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_COLLECTION_ROUTE_OFFICER_CODE");
			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"hid_area_count");
			int m_num=Integer.parseInt(m_scr_num);

			int m_tnum=0;
			
			for(int i=1;i<=m_num;i++){
				m_tnum++;
				callstmt1.setString(1,m_officer_code);
				callstmt1.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_ROUTE_CODE_"+i+""));
				callstmt1.setString(3,m_screen_name);
				callstmt1.setString(4,m_username);
				callstmt1.setInt(5,m_tnum);
				callstmt1.execute();
			}
			
			if(m_num == 0) {
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_MAS_SAVE_COL_ROUT_ASIN_DEL(:1,:2,:3); END;");
			callstmt.setString(1,m_officer_code);
			callstmt.setString(2,m_screen_name);
			callstmt.setString(3,m_username);
			callstmt.execute();
			}

			callstmt1.close();
			conn.commit();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_MAS_display_collection_routes_assign';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
 
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
				conn.rollback();
			}catch(Exception e){}
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
