//--
//SCREEN NAME:
//CREATED BY: SANDUN 
//DATE/TIME:02/10/2008 
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_Save_Insurance_receipt_allo extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
	String reqstr;
	String m_client_name;
	ServletOutputStream out = null;
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		synchronized(this){ 
			
			try {
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				out = res.getOutputStream();
				
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************	
				conn =m_sn_methods.met_user_validate(req);
				conn.setAutoCommit(false); 
				
				//**************************************************************		
				String m_schema_name = m_sn_methods.schema_name.trim();
				m_client_name = m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url;
				String m_class_url;
				String m_screen_name="";
				
				
				m_html_client_url=m_sn_methods.html_client_url;
				m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				
				
				String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
				
				m_msg = "'Information saved successfully.'";
				m_url = m_class_url;				
				
				
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SAVE_INSURENCE_REC_ALLO(?,?,?);END;");
				int m_row_count = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "ROWCOUNT"));
				for (int j = 1; j <=m_row_count; j++) {
					if(((String)m_sn_methods.met_formdata(reqstr,"SELECTION_"+j)).equals("Y")){
						
						callstmt1.setString(1, (String)m_sn_methods.met_formdata(reqstr,"HID_PAYMENT_NO_"+j));
						callstmt1.setString(2, (String)m_sn_methods.met_formdata(reqstr, "HID_SUS_REF_NO_"+(Integer.toString(j))).trim());
						callstmt1.setString(3, (String)m_sn_methods.met_formdata(reqstr,"TXT_RECEIPT_NO_"+(Integer.toString(j))).trim());					
						callstmt1. addBatch();
					}
				}
				
				//callstmt1.execute();
				int [] updateCounts = callstmt1.executeBatch();
				conn.commit(); 
				conn.close();
				
				
				
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Insurance_payment_receipt_allo?chksql=main_page';");
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				out.flush();
				out.close();
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
				System.out.println("Insurence Receipt Allo");
				PrintWriter pw = res.getWriter();
				E.printStackTrace(pw);
				
			}
			finally{
				try{conn.setAutoCommit(true);}catch(Exception e){}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}
			
			
			
		}
	}
}
