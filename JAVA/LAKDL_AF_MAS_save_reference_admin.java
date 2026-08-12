//--
//SCREEN NAME	:SAVE REFERENCE ADMIN
//CREATED BY	:Delanjali
//DATE/TIME		:30-01-2007
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_reference_admin extends HttpServlet {
		
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

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			
			
			conn.setAutoCommit(false);

			m_url = m_class_url;
			
			int m_count=0;
			int m_count1=0;
			String m_status="";
			String price_no[]=new String[10];
						
						
			m_count = Integer.parseInt(req.getParameter("number"));
	
	
	
	
			for (int j=0; j<=m_count; j++) 
			{
			
			String m_code=m_sn_methods.met_formdata(reqstr,"TXT_CODE_"+j);
			String m_approved=m_sn_methods.met_formdata(reqstr,"chk_app_"+j);
			String m_dis_approved=m_sn_methods.met_formdata(reqstr,"chk_dis_app_"+j);
	
			if(m_approved.equals("")){
			m_approved="N";
			m_status="P";
			}
			if(m_dis_approved.equals("")){
			m_dis_approved="N";
			m_status="P";
			}
			if(m_approved.equals("Y")){
			m_status="Y";
			}
			if(m_dis_approved.equals("Y")){
			m_status="C";
			}
				

			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_REFERENCE_ADMIN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CODE_"+j));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INSERT_SCREEN_"+j));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_CODE_"+j));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_DESCRIPTION_"+j));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_UPDATE_SCREEN_"+j));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ACTIVE_STATUS_"+j));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(8,m_username);
			callstmt.setString(9,Integer.toString(j));
			callstmt.setString(10,m_approved);
			callstmt.setString(11,m_dis_approved);
			
			
			callstmt.setString(12,"AF_CO_MAS_REFERENCE_ADMIN");
			callstmt.setString(13,m_status);
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CHOOSE_CODE_"+j));



			callstmt.execute();
	}		
	
	

	
	

			callstmt.close();

			conn.commit();
	
	
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("		window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_display_reference_admin?chksql=main_page';"); 
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		

