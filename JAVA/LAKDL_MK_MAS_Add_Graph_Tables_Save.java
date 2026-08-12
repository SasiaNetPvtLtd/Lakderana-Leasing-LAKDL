

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_MK_MAS_Add_Graph_Tables_Save extends HttpServlet {
	
	Connection conn,conn2;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt2;
	String reqstr;
	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt;
	public synchronized void  service(HttpServletRequest request, HttpServletResponse res)	throws IOException{
		
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			//out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(request);
			conn2 =m_sn_methods.met_user_validate(request);
			stmt=conn.createStatement();
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			String edit="";
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			conn.setAutoCommit(false);
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			rs=stmt.executeQuery("SELECT DASH_ID "+
				//out.println("SELECT DASH_ID "+
				"FROM "+m_schema_name+".DH_DASH_GRAPH_MAIN WHERE DASH_ID = '"+(String)m_sn_methods.met_formdata(reqstr,"TXT_DASHBOARD_ID")+"' ");
			if(rs.next()){
				edit = "TRUE";
			}
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_GRAPH_POSITION(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			
			callstmt.setString(1,(String)m_sn_methods.met_formdata(reqstr,"TXT_DASHBOARD_ID"));
			callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_GRAPH_TYPE"));
			callstmt.setString(3,"Y");
			callstmt.setString(4,(String)m_sn_methods.met_formdata(reqstr,"TXT_X_POSITION"));
			callstmt.setString(5,(String)m_sn_methods.met_formdata(reqstr,"TXT_Y_POSITION"));
			callstmt.setString(6,(String)m_sn_methods.met_formdata(reqstr,"TXT_HEIGHT"));
			callstmt.setString(7,(String)m_sn_methods.met_formdata(reqstr,"TXT_WIDTH"));
			callstmt.setString(8,(String)m_sn_methods.met_formdata(reqstr,"SUB_SEC_NUM"));
			callstmt.setString(9, edit);
			
			callstmt.execute();
			callstmt.close();
			
			conn.commit(); 
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_add_graph_tables';"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			out.flush();
			
		}
		
		
		catch (Exception E) {
			try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); //WHEN GENERATE ERROR AGTER CLICK OK GOING TO HOME
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