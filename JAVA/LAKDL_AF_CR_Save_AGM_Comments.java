//--
//SCREEN NAME	:Application AGM Comments
//CREATED BY	:Nuwan De Silva
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_Save_AGM_Comments extends HttpServlet {
		
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
			String m_screen_name="";
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
				
			
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			//String m_status = req.getParameter("actst1");//ENT_CON /VERIFY 1./VERIFY-M
			//String m_app_status = req.getParameter("actst2");// VERIFY 1 /VERIFY 2
			String m_application = req.getParameter("app_no");//application no


			int m_chksql;
			int sel_stage=0;
			
			String scr_name="";
			String screen_name1="";
			
		//	String m_scr=req.getParameter("scr");//approve/reverse

			/*if (m_status.equals("ENT_CON")){
			sel_stage=1;
			
			}
			else if(m_status.equals("VERIFY-M")){
			sel_stage=2;
			}

			else if(m_status.equals("VERIFY1")){
			sel_stage=3;
			}
			
			if (m_status.equals("ENT_CON")){
			scr_name="APPROVE1";
			}
			else if(m_status.equals("VERIFY-M")){
			scr_name="APPROVE-M";
			}
			else if(m_status.equals("VERIFY1")){
			scr_name="APPROVE2";
			}

			if (m_status.equals("ENT_CON") && m_scr.equals("ENT_CON")){
			screen_name1="APPROVE";
			
			}
			else if(m_status.equals("ENT_CON") && m_scr.equals("VERIFY1")){
			screen_name1="REVERSE";
			}
			
			
			if (m_status.equals("VERIFY1") && m_scr.equals("VERIFY1")){
			screen_name1="APPROVE";
			
			}
			else if(m_status.equals("VERIFY1") && m_scr.equals("VERIFY2")){
			screen_name1="REVERSE";
			}
			
			
			if (m_status.equals("VERIFY-M") && m_scr.equals("VERIFY-M")){
			screen_name1="APPROVE";
			
			}
			else if(m_status.equals("VERIFY-M") && m_scr.equals("VERIFY-M")){
			screen_name1="REVERSE";
			}
			*/
			
			
			
		/*	String m_chk=m_sn_methods.met_formdata(reqstr,"chk_app");
			String m_chk_req=m_sn_methods.met_formdata(reqstr,"chk_rej");
			String m_chk_return=m_sn_methods.met_formdata(reqstr,"chk_return"); //added by nuwan de silva on 04-10-07
			String m_return_status=req.getParameter("return_status"); //added by nuwan de silva on 04-10-07
   */	
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_AGM_COMMENTS(:1,:2,:3,:4,:5,:6); END;");
		  callstmt.setString(1,m_application);
			callstmt.setString(2,"AGM_APP");
			callstmt.setString(3,m_username);
			callstmt.setString(4,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim()); //modified by nuwan de silva 22-05-07
			callstmt.setString(5,"AGM_APP");
			callstmt.setString(6,"APPROVE");
			
			callstmt.execute();
			
			
			callstmt.close();
			conn.commit();
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.close();");
			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_AGM_Comment?chksql=main_page&pre="+m_status+"&appro="+m_app_status+"&qry="+m_status+"&applicaton_no="+m_application+"';");
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
			out.println("window.history.back();"); 
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
		

