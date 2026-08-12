//Created By Minal on 17-06-2015 for #17087
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MISF_Confirmation_Report_approval_save_assign_me extends HttpServlet {

	ResultSet rs1;
	Statement stmt;
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
			stmt=conn.createStatement();
            //String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int count=0;

			int row_count = Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"REC_COUNT"));
			String m_chk_type = "off"; 
			String m_chk_type_2 = "off"; 
			String m_screen_url = m_sn_methods.met_formdata(reqstr,"hid_scr_url");
			String m_hid_scr_status = m_sn_methods.met_formdata(reqstr,"hid_scr_status");
			String m_hid_stage = m_sn_methods.met_formdata(reqstr,"hid_stage");
			
				//callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_CONF_BEFORE_SAVE_ASSIGN_ME(:1); END;");
				//callstmt1.setString(1,m_username);
				//callstmt1.execute();
			
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CONFIRMATION_SAVE_ASSIGN_ME(:1,:2,:3,:4); END;");
			
				if(m_hid_scr_status.equals("ASSIGN_ME")){
					
						for(int k=0;k<row_count;k++){
							
							m_chk_type   = (String)m_sn_methods.met_formdata(reqstr,"CHK_ASSIGN_ME_"+(Integer.toString(k)));
							m_chk_type_2 = (String)m_sn_methods.met_formdata(reqstr,"CHK_UN_ASSIGN_ME_"+(Integer.toString(k)));
							
							if(m_chk_type.equals("on")){
							
								callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(k))));
								callstmt.setString(2,m_username);
								callstmt.setString(3,"ASSIGN_ME");
								callstmt.setString(4,m_hid_stage);
								callstmt.execute();
								
							}
							
							if(m_chk_type_2.equals("on")){
								
								callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(k))));
								callstmt.setString(2,m_username);
								callstmt.setString(3,"REMOVE");
								callstmt.setString(4,m_hid_stage);
								callstmt.execute();
								
							}
							
						}
				}
				else{
					callstmt.setString(1,"-");
					callstmt.setString(2,m_username);
					callstmt.setString(3,"UN_ASSIGN_ME");
					callstmt.setString(4,m_hid_stage);
					callstmt.execute();
				}
				
			callstmt.close();
			
			
			conn.commit();
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			//out.println("window.location.href='"+m_url+"/LAKDL_AF_MISF_Confirmation_Report_approval_1?chksql=main_page';"); 
			out.println("window.location.href='"+m_url+"/"+m_screen_url+"?chksql=main_page';"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
		//	}

		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			//out.println("window.history.back();"); 
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
		