// created by udara on 12-03-2015
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MISF_Confirmation_Report_approval_save extends HttpServlet {

	ResultSet rs1;
	Statement stmt;
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
			stmt=conn.createStatement();
            //String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int count=0;

			int row_count = Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"REC_COUNT"));
			String m_chk_type = "off"; 
			
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CONFIRMATION_RPT_APPR(:1,:2,:3,:4); END;");
			
				for(int k=0;k<row_count;k++){
					
					m_chk_type = (String)m_sn_methods.met_formdata(reqstr,"CHK_"+(Integer.toString(k)));
			
					if(m_chk_type.equals("on")){
							//Edited by Minal on 16-06-2015 for #17087
						callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(k))));
						callstmt.setString(2,m_username);
						callstmt.setString(3,"APPROVE");
						callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"APPROVE_COMMENT_"+(Integer.toString(k))));
						callstmt.execute();
						
					}
				}
			callstmt.close();
			
			
			conn.commit();
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MISF_Confirmation_Report_approval?chksql=main_page';"); // reload // href
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
		
