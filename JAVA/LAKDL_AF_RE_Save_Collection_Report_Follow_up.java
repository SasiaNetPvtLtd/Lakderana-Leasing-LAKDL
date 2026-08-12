//SCREEN NAME:save collection follow up
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Collection_Report_Follow_up extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		
			PrintStream out = new PrintStream(res.getOutputStream());
	   // out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
		//	out.println("conn"+conn);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			String m_app_no="";
			String m_vendor_code="";
		
			String m_status="";
			String m_val_date="";
			String m_scr_name="";
			String m_deposit="";
			
	
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
		//	out.println("conn"+conn);
			//out.println(reqstr);
			
      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
		
			/*  callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CONDITIONS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			  callstmt.registerOutParameter(2,java.sql.Types.CHAR);	
			  
					 
				callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no").trim());	
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_NO").trim());	
  			callstmt.setString(3,m_screen_name);
			  callstmt.setString(4,m_username);
			  callstmt.setString(5,m_scr_name);
				callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"));	
				callstmt.setString(7,"COLLE_DOC");//MODIFIED NUWAN DE SILVA
			  callstmt.setString(8,"AF");
			  callstmt.setString(9,"PENDING");
				callstmt.setString(10,"RECOVERY");			
				
				*/
				
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CONDITIONS_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
			  callstmt.registerOutParameter(2,java.sql.Types.CHAR);	
			  
					 
				callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no").trim());	
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_NO").trim());	
  			callstmt.setString(3,m_screen_name);
			  callstmt.setString(4,m_username);
			  callstmt.setString(5,m_scr_name);
				callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"));	
				callstmt.setString(7,"COLLE_DOC");//MODIFIED NUWAN DE SILVA
			  callstmt.setString(8,"AF");
			  callstmt.setString(9,"PENDING");
				callstmt.setString(10,"RECOVERY");		
				
				String m_act_dd =m_sn_methods.met_formdata(reqstr,"VAL_DAY");
				String m_act_mm =m_sn_methods.met_formdata(reqstr,"VAL_MONTH");
				String m_act_yy =m_sn_methods.met_formdata(reqstr,"VAL_YEAR");
				String m_act_date="";
				
				if(m_act_dd.equals("") && m_act_mm.equals("") &&  m_act_yy.equals("") )	{
				m_act_date=m_act_dd+m_act_mm+m_act_yy; 
				}	else{
				m_act_date=m_act_dd+"-"+m_act_mm+"-"+m_act_yy;
				}
				callstmt.setString(11,m_act_date);
			
		  callstmt.execute();
		
			
					
			
		
				
				
				
				
				
				
				
			
			
						
			
			
			
			
			

		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
						
		//	out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page';");
			out.println("window.close()");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
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
	}
	}
}
