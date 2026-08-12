///ID         : 1.73 AUTHORIZATION LIMITS PROCESS
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - AUTHORIZATION LIMITS
//CREATED BY: NUWAN DE SILVA
//DATE/TIME : 25-07-06
//NOTES:


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_authorization_limits extends HttpServlet {
		
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
			String m_fschema_name = m_sn_methods.client_name.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			/*
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_AUTH_LIMITS(:1,:2,:3,:4,:5,:6); END;");

			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_USER_ID")).trim());
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_AUTHORIZATION_LEVEL"));
			callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_LIMIT")));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(6,m_username);
			callstmt.execute();
			callstmt.close();
			*/
///////////////////////////////////////////////////////////////////////////////////
			int m_chksql = Integer.parseInt(req.getParameter("number"));

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_AUTH_LIMITS(:1,:2,:3,:4,:5,:6,:7); END;");

			int k=0;
			if(m_chksql!=0){
			for(int i=0;i<m_chksql;i++)	{
					
			String m_status = m_sn_methods.met_formdata(reqstr,"CHK_STATUS_"+i+"");
			String m_sub_cat=m_sn_methods.met_formdata(reqstr,"TXT_SUB_TYPE_CODE_"+i);
			String m_amt=m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT_"+i));
			
			//if(m_amt==null || m_amt.equals("")){
			//m_amt="0";
			//}

			if(m_status.equals("")){
			m_status="off";
			}
			if(m_status.equals("on")){
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_USER_ID")).trim());
			callstmt.setString(2,m_sub_cat);
			callstmt.setString(3,m_amt);
			callstmt.setString(4,"");
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(6,m_username);
			callstmt.setString(7,Integer.toString(i));
			
			if(!m_sub_cat.equals("") && !(m_amt==null || m_amt.equals(""))){
				callstmt.execute();
				k=k+1;
			}
			}
			
			else {
			
			if(k==0){
				callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_USER_ID")).trim());
				callstmt.setString(2,"");
				callstmt.setString(3,"");
				callstmt.setString(4,"");
				callstmt.setString(5,"DELETE");
				callstmt.setString(6,m_username);
				callstmt.setString(7,Integer.toString(k));
				callstmt.execute();
			}
			}

	}
	}
	conn.commit();
	callstmt.close();

//////////////////////////////////////////////////////////////////////////////////

	   	    out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_authorization_limits';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
