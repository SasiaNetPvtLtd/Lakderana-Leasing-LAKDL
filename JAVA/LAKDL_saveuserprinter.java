import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;
import oracle.jdbc.driver.*;


public class LAKDL_saveuserprinter extends HttpServlet {
	
	Connection conn;	 
	Statement stmt;
	String m_msg,m_url;
	File file1;
	CallableStatement callstmt1,callstmt;
	
	//----------------------------------DEFINING URLs------------------------------------------- 
	String m_username="";
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	String m_entry_type,m_docref_no,m_inv_ref_no;
	String m_schema_name,m_ent_type,m_client_name;
	//------------------------------------------------------------------------------------------ 
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException
	{
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			String reqstr = input.readLine();
			
			
			//*********************************************************************************************	
			LAKDL_AF_CO_conn_methods m_LAKDAC_wbacc_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_LAKDAC_wbacc_methods.met_user_validate(req); 
			m_username = m_LAKDAC_wbacc_methods.username;
			m_client_name =m_LAKDAC_wbacc_methods.client_name;
			m_html_client_url = m_LAKDAC_wbacc_methods.html_client_url;
			m_servlet_client_url= m_LAKDAC_wbacc_methods.servlet_client_url;
			m_client_t3_port= m_LAKDAC_wbacc_methods.client_t3_port; 
			m_schema_name= m_LAKDAC_wbacc_methods.schema_name;
			String m_class_url=m_LAKDAC_wbacc_methods.servlet_client_url.trim()+":"+m_LAKDAC_wbacc_methods.client_t3_port.trim();
			String		m_scr_name = 	m_LAKDAC_wbacc_methods.met_formdata(reqstr,"Hid_scr_name");
			
			//*********************************************************************************************
			
			m_docref_no="";
			m_inv_ref_no="";
			ServletOutputStream out1 = res.getOutputStream();
			PrintStream out = new PrintStream(res.getOutputStream());
			
			if(m_scr_name.equals("MLL_Printer")){
				//m_msg = "'hiiiiiiiiiiiiii.'";
				
				synchronized (this){
					String  m_count       = m_LAKDAC_wbacc_methods.met_formdata(reqstr,"NUM_CHKS");
					m_msg = "'Select the Printer for the Save.'";
					//m_url=m_html_client_url+"/mllac_printer_user.html";
					m_url = m_class_url; 
					
					// added by udara 23-05-2016
					callstmt=conn.prepareCall( "BEGIN "+m_schema_name+".REF_PRINTER_USER_SYNC(?); END;");
					callstmt.setString(1, m_LAKDAC_wbacc_methods.met_formdata(reqstr,"TXT_USER_ID"));
					callstmt.execute();
					callstmt.close();
					// end by udara 23-05-2016
					
					callstmt1=conn.prepareCall( "BEGIN "+m_schema_name+".REF_PRINTER_USER_APPROVAL(?,?,?,?,?); END;");
					//out.println("**********************m_count"+m_count);
					callstmt1.setString(1, m_LAKDAC_wbacc_methods.met_formdata(reqstr,"TXT_USER_ID"));
					for(int i=1;i <= Integer.parseInt(m_count);i++){
						//	out.println("**********************"+i);
						String m_msg_chk=m_LAKDAC_wbacc_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_"+i).trim();
						//if(m_msg_chk.equals("on")){
						//m_msg_chk="Y";
						//out.println(m_LAKDAC_wbacc_methods.met_formdata(reqstr,"HID_PINTPATH"+i));
						callstmt1.setString(2, m_LAKDAC_wbacc_methods.met_formdata(reqstr,"HID_PRINTER"+i));
						callstmt1.setString(3,m_LAKDAC_wbacc_methods.met_formdata(reqstr,"HID_PINTPATH"+i));
						callstmt1.setString(4, m_LAKDAC_wbacc_methods.met_formdata(reqstr,"HID_SHARE"+i));
						callstmt1.setString(5,m_msg_chk);
						
						callstmt1.execute();
						m_msg = "'Printer has been Saved Successfully.'";	
						
						//}//end if
					}//end for 
					
					callstmt1.close();
					
				}//syncro	
			}//END m_scr_name--Payee_approval
			else {
				out.println("Error");
			} 
			
			conn.close();
			this.destroy();
			
			//---------------------------------------------------
			//     Display message and load new page
			//---------------------------------------------------
			
			if(m_scr_name.equals("Payment_SetUp") &&(m_entry_type.equals("NEW"))){
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Reference ID "+m_docref_no+" .');");
				out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_User_print';");
				//out.println("parent.location.href='"+m_url+"'");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
				out.close();	
			}else if(m_scr_name.equals("Invoice_Entry") &&(m_ent_type.equals("NEW"))){
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Reference ID "+m_inv_ref_no+" .');");
				//out.println("parent.location.href='"+m_url+"'");
				out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_User_print';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
				out.close();
			}else{
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				//out.println("parent.location.href='"+m_url+"'");
				out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_User_print';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
				out.close();			
			}
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
			try {
				callstmt1.close();
				conn.close();
			}	
			catch (Exception eti) {}
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			try {
				PrintStream ps = new PrintStream(buf);
				th.printStackTrace(ps);
				ps.println("Error When Saving Invoice Schedule3");
				ps.flush();
				ps.close();
				
			}
			catch (Exception e) {
				out.println("Error When Saving Invoice Schedule4");
				out.flush();
				out.close();
				try {
					callstmt1.close();
					conn.close();
					this.destroy();
				}	
				catch (Exception eti) {}
				
				ByteArrayOutputStream ostr = new ByteArrayOutputStream();
				e.printStackTrace(new PrintStream(ostr));
				ostr.close();
			}
		}
	}
	
	public String getServletInfo() {
		return getServletInfo();
	}
	
	public ServletConfig retServletConfig() {
		return getServletConfig();
	}
}