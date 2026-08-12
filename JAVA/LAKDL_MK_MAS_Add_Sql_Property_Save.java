

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_MK_MAS_Add_Sql_Property_Save extends HttpServlet {
	
	Connection conn,conn1;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
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
			conn1 =m_sn_methods.met_user_validate(request);
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
			
			
			/********************************/
			String dash_id="";
			String property_name="";
			String property_value="";
			
			
			
			
			out.println("1111111");
			
			int m_records1 =	Integer.parseInt(m_sn_methods.met_formdata(reqstr,"row_count1"));
			//int m_records1  = 5;
			out.println("22222222");
			out.println(m_records1);
			callstmt1=conn1.prepareCall("BEGIN "+m_schema_name+".PW_CO_SAVE_SQL_PROPERTY(:1,:2,:3,:4,:5); END;");
			int count = 0;
			for(int j=0;j < m_records1 ; j++){
				
				String temp = (String)m_sn_methods.met_formdata(reqstr,"HID_PROP_NAME_"+(Integer.toString(j)));
			  
				out.println(temp == null);
				
				if(temp != null){
					
					count++;
					/*out.println(String)m_sn_methods.met_formdata(reqstr,"HID_DASH_ID"));
					out.println(String)m_sn_methods.met_formdata(reqstr,"SUB_SEC_NUM")));
					out.println(String)m_sn_methods.met_formdata(reqstr,"HID_PROP_NAME_"+(Integer.toString(j))));
					out.println(String)m_sn_methods.met_formdata(reqstr,"HID_PROP_VAL_"+(Integer.toString(j))));*/
					
					
					callstmt1.setString(1,(String)m_sn_methods.met_formdata(reqstr,"HID_DASH_ID"));
					callstmt1.setString(2,(String)m_sn_methods.met_formdata(reqstr,"SUB_SEC_NUM"));
					callstmt1.setString(3,(String)m_sn_methods.met_formdata(reqstr,"HID_PROP_NAME_"+(Integer.toString(j))));
					callstmt1.setString(4,(String)m_sn_methods.met_formdata(reqstr,"HID_PROP_VAL_"+(Integer.toString(j))));
					callstmt1.setInt(5,count);
					callstmt1.execute();
					
				}
				else{
					out.println("not working");
					m_records1++;
				}	
			}
			
			
			
			//int m_records =	Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"row_count")));
			//out.println("wwwww" + m_records);
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".PW_CO_UPDATE_QUERY_GR(:1,:2,:3); END;");
			//int count = 0;
			//for(int j=0;j < m_records ; j++){
			//count++;
			//out.println("wwwww");
			
			
			property_name 					= (String)m_sn_methods.met_formdata(reqstr,"SUB_SEC_NUM");
			dash_id							= (String)m_sn_methods.met_formdata(reqstr,"HID_DASH_ID");
			property_value 					= (String)m_sn_methods.met_formdata(reqstr,"TXT_SQL_QUERY");
			
			/*
			out.println(dash_id);
			out.println(property_name);
			out.println(property_value);
			out.println(count + "********");
			*/
			callstmt.setString(1,dash_id);
			callstmt.setString(2,property_name);
			callstmt.setString(3,property_value);
			//callstmt.setInt(4,count);
			callstmt.execute();
			
			
			conn.commit(); 
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_dashboard_add_sql_property';"); 
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