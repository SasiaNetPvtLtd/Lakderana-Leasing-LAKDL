
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - MODEL CREATION
//ID:1.41 Model Creation Process
//CREATED BY:N.V.P.Chandana
//DATE/TIME:24-07-2006/3.12pm
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_model_creation  extends HttpServlet {
		
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
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      conn.setAutoCommit(false); //added by nuwan de silva on 22-11-2007
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			String m_close_status=(String)m_sn_methods.met_formdata(reqstr,"hid_close_status");
			String m_model_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE");
			String m_model_desc=(String)m_sn_methods.met_formdata(reqstr,"TXT_DESCRIPTION");
			String m_screen_name=(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			 		
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_MODEL_CREATION (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE")).trim());
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_DESCRIPTION"));
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_MAKE_CODE")).trim());
			callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_FUEL_TYPE")).trim());
			callstmt.setString(5,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_CAT_CODE")).trim());
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TAX_RATE")));
			callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TAX_FOR_LEASE")));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.execute();
		  callstmt.close();
			
			// _________ added by nuwan de silva on 10-12-2007 __________________________________________________________________
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_SUB_MODEL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			//callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_CODE")).trim());
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE")).trim());
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE")).trim());
			//callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE")).trim());
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DESCRIPTION"));
			callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_CAPACITY")));
			//callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_OPTION_TYPE")); // commented by udara on 24-02-2012
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"HID_TXT_OPTION_TYPE")); // added by udara on 24-02-2012
			callstmt.setString(6,(m_sn_methods.met_formdata(reqstr,"TXT_COUNTRY_CODE")).trim());
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_YEAR_OF_MANUFACTURE"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(10,m_username);
			callstmt.execute();
			callstmt.close();
		  // __________________ end by nuwan de silva __________________________________________________________________________
			
			conn.commit(); //added by nuwan de silva on 22-11-2007___

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			if(m_close_status.equals("Y")){
			out.println("alert("+m_msg+");");
			//out.println("alert(window.opener.document.location);");
			if(!m_screen_name.equals("DACT")){
			out.println("window.opener.document.Form1.TXT_MODEL_CODE.value=\""+m_model_code+"\"");
			out.println("window.opener.document.Form1.TXT_MODEL_DESCRIPTION.value=\""+m_model_desc+"\"");
			}
			out.println("window.close();");
			}else{
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_model_creation ';");
			}
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		/*catch (Exception ex) {
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
		}*/
		
				  //added by nuwan de silva on 22-11-2007
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
