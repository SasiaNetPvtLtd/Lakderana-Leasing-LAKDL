

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_MK_MAS_add_dashboard_sql_save extends HttpServlet {
	
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
			
			
			
			String dash_id="";
			String property_name="";
			String property_value="";
			
			int m_records1 =	Integer.parseInt(m_sn_methods.met_formdata(reqstr,"row_count"));
				
				callstmt1=conn1.prepareCall("BEGIN "+m_schema_name+".PW_CO_SAVE_SQL_TYPES(:1,:2,:3,:4); END;");
				int count1 = 0;
				//out.println(m_records1);
				for(int k=0;k < m_records1 ; k++){
					count1++;
					String temp1 = (String)m_sn_methods.met_formdata(reqstr,"HID_DASH_ID_"+(Integer.toString(k)));
					out.println(temp1 == null);
					
					if(temp1 != null){
						
						//out.println(" HID_DASH_ID_ ID " + (String)m_sn_methods.met_formdata(reqstr,"HID_DASH_ID_"+(Integer.toString(k))));
						//out.println(" HID_APP_NO_ ID " + (String)m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(k))));
						
						
						callstmt1.setString(1,(String)m_sn_methods.met_formdata(reqstr,"HID_DASH_ID_"+(Integer.toString(k))));
						callstmt1.setString(2,(String)m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(k))));
						callstmt1.setString(3,"");
						callstmt1.setInt(4,count1);	
						callstmt1.execute();
						
					}
					else{
						out.println("not working");
						m_records1++;
					}	
					
					
				}
				
				
				
				
				int m_records =	Integer.parseInt(m_sn_methods.met_formdata(reqstr,"row_count1"));
				//out.println(m_records);
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".PW_CO_SAVE_GRAPH_PROP_GR(:1,:2,:3,:4); END;");
				int count = 0;
				for(int j=0;j < m_records ; j++){
					String temp = (String)m_sn_methods.met_formdata(reqstr,"HID_SERIAL_NO_"+(Integer.toString(j)));
					out.println(temp == null);
					
					if(temp != null){
						
						count++;
						//out.println(m_records);
						
						
						dash_id 						= (String)m_sn_methods.met_formdata(reqstr,"SUB_SEC_NUM");
						property_name 					= (String)m_sn_methods.met_formdata(reqstr,"HID_SERIAL_NO_"+(Integer.toString(j)));
						property_value 					= (String)m_sn_methods.met_formdata(reqstr,"HID_CAT_NAME_"+(Integer.toString(j)));
						 
						
						callstmt.setString(1,dash_id);
						callstmt.setString(2,property_name);
						callstmt.setString(3,property_value);
						callstmt.setInt(4,count);
						callstmt.execute();
						
					}
					else{
						out.println("not working");
						m_records++;
					}	
					
				}
				
				
				
				
				
				conn.commit(); 
				
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_dashboard_add_sql';"); 
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