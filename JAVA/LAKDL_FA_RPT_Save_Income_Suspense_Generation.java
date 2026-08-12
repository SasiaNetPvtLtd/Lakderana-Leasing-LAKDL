// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_RPT_Save_Income_Suspense_Generation extends HttpServlet {
		
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
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;

			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			conn.setAutoCommit(false);
			
				
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_RPT_SAVE_INCOME_SUSPENSE(:1,:2,:3,:4,:5); END;");
			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);
			
			String m_eff_date="";
			
			int m_tnum=0;
					
			for(int i=0;i<m_num;i++){
			
				String m_chk=(String)m_sn_methods.met_formdata(reqstr,"TXT_SUS_STATUS_"+i+"");
				String m_facility_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_CODE_"+i+"");
				
				String m_dd=(String)m_sn_methods.met_formdata(reqstr,"TXT_DD_"+i+"");
				String m_mm=(String)m_sn_methods.met_formdata(reqstr,"TXT_MM_"+i+"");
				String m_yy=(String)m_sn_methods.met_formdata(reqstr,"TXT_YY_"+i+"");
				
				m_eff_date=m_dd+"-"+m_mm+"-"+m_yy;
								
				String m_sus_comment=(String)m_sn_methods.met_formdata(reqstr,"TXT_COMMENT_"+i+"");
				
				if(m_chk.equals("on")){
				callstmt.setString(1,m_facility_code);
				callstmt.setString(2,m_sus_comment);
				callstmt.setString(3,m_eff_date);
				callstmt.setString(4,m_screen_name);
				callstmt.setString(5,m_username);
				callstmt.execute();
				}
				
			}
			callstmt.close();
			conn.commit();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_RPT_Income_Suspense_Generation';");
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
