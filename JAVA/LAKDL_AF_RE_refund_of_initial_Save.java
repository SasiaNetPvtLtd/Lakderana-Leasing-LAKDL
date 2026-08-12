//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY: YOHAN GUNARATHNA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_refund_of_initial_Save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	String m_client_name;
	ServletOutputStream out = null;
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		synchronized(this){ 
			
			try {
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				out = res.getOutputStream();
				//PrintStream out = new PrintStream(res.getOutputStream());
				// out.println(reqstr);
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************	
				conn =m_sn_methods.met_user_validate(req);
				conn.setAutoCommit(false); //added by nuwan de silva on 19-11-2007
				
				//**************************************************************		
				String m_schema_name = m_sn_methods.schema_name.trim();
				m_client_name = m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url;
				String m_class_url;
				String m_class_name_save;
				String m_save_procedure_name;
				String m_app_no1="";
				String m_screen_name="";
				String m_chk_complete="";
				String m_CLOSE="";
				//m_html_client_url=SCREEN_METHODS.html_client_url;
				//m_class_url=SCREEN_METHODS.servlet_client_url.trim()+":"+SCREEN_METHODS.client_t3_port.trim();
				m_html_client_url=m_sn_methods.html_client_url;
				m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				//out.println("conn"+conn);
				//out.println(reqstr);
				
				String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
				m_CLOSE=(String)m_sn_methods.met_formdata(reqstr,"hid_CLOSE"); 
				//m_screen_name =(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
				m_msg = "'Information saved successfully.'";
				m_url = m_class_url;
				
				
				
				//int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
				
				
				
				
				//for (int j = 0; j < m_maxentries; j++) {
					
					
					
					//if(j==0){
						
						callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_REFUND_RECIPTS(:1,:2,:3,:4,:5); END;");
						
					//	String m_app_no=(String)m_sn_methods.met_formdata(reqstr,"RECEIPT_NO");
						
						
						//if(m_app_no.equals("")){
						//	callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
						//}
						//else
						//{
						//	callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"RECEIPT_NO")).trim());
							
							
						//}
						
						callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE"));
						//callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_APPLICANT_CODE"));
						callstmt.setString(2,m_username);
					    callstmt.setString(3,"1");
					    callstmt.setString(4,m_scr_name);
						callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"RECEIPT_NO"));
					
						callstmt.execute();
						
						
						//if(m_screen_name.equals("NEW")){
						//	m_app_no1 =callstmt.getString(1);
						//	m_msg = "'"+m_app_no1+" Application saved successfully.'";
						//}
						
					//}
					
					
				
					
					
					
					
					
				///}
				
				
					
					
					
					// added by udara on 14-12-2012
					// commented by udara on 25-07-2013
					
					
			//	}
				
				callstmt.close();
				conn.commit(); //added by nuwan de silva on 03-09-07----------------------
				conn.close();
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				
				out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Refund_of_initial_receipt?chksql=main_page';");
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				out.flush();
				out.close();
			}
			/*catch (Throwable th) {
				PrintStream out = new PrintStream(res.getOutputStream());
				th.printStackTrace(out);
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Error When Saving Record..');");
				//out.println("window.history.back();"); 
				out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
			out.close();
			}*/
			
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
}
