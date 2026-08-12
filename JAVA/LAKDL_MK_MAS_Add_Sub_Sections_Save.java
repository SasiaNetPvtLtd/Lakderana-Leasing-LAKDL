

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_MK_MAS_Add_Sub_Sections_Save extends HttpServlet {
	
	Connection conn,conn2;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt2;
	String reqstr;
	ServletOutputStream out = null;
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
			conn.setAutoCommit(false);
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			String m_sub_sec_id="";
			String m_desc_1="";
			String m_desc_2="";
			String m_height="";
			String m_header_id="";
			
			
			
			//int i1 = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_tax_num"));
			int m_records =	Integer.parseInt(m_sn_methods.met_formdata(reqstr,"row_count"));
			
			callstmt2=conn2.prepareCall("BEGIN "+m_schema_name+".PW_CO_UPDATE_DH_DASH_HEADER(:1,:2,:3,:4); END;");
			//callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_ADD1").trim());
			callstmt2.setString(1,(String)m_sn_methods.met_formdata(reqstr,"HID_HEADER_ID"));
			callstmt2.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_HEADER_DESC"));
			callstmt2.setString(3,(String)m_sn_methods.met_formdata(reqstr,"TXT_HEADER_COM_NAME"));
			callstmt2.setString(4,(String)m_sn_methods.met_formdata(reqstr,"TXT_HEADER_HEIGHT"));
			
			callstmt2.execute();
			//out.println(m_records);
			/*************************************************************************************************/
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".PW_CO_SAVE_SUB_SECTIONS_GR(:1,:2,:3,:4,:5,:6,:7); END;");
			int count = 0;
			for(int j=0;j < m_records ; j++){
				count++;
				//out.println(m_records);
				
				
				String temp = (String)m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(j)));
				//out.println(temp + "************");
				out.println(temp == null);
				
				if(temp != null){
					//out.println("^^^^^^^^^^^^^^^^^^^^");
					m_sub_sec_id 				= (String)m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(j)));
					m_desc_1 					= (String)m_sn_methods.met_formdata(reqstr,"HID_APP_DESC1_"+(Integer.toString(j)));
					m_desc_2 					= (String)m_sn_methods.met_formdata(reqstr,"HID_APP_DESC2_"+(Integer.toString(j)));
					m_height 					= (String)m_sn_methods.met_formdata(reqstr,"HID_APP_HEIGHT_"+(Integer.toString(j)));
					m_header_id 				= (String)m_sn_methods.met_formdata(reqstr,"HID_HEADER_ID");
					
					
					/*out.println(" HEADER ID " + m_header_id);
					out.println(" SUB SEC ID " + m_sub_sec_id);
					out.println(" DESC1 " + m_desc_1);
					out.println(" DESC2 " + m_desc_2);
					out.println(" HEIGHT " + m_height);
					out.println(" COUNT " + count);
					*/
					callstmt.setString(1,m_header_id);
					callstmt.setString(2,m_sub_sec_id);
					callstmt.setString(3,m_desc_1);
					callstmt.setString(4,m_desc_2);
					callstmt.setString(5,m_height);
					callstmt.setString(6,"Y");
					callstmt.setInt(7,count);
					
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
			out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_Dashboard_add';"); 
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