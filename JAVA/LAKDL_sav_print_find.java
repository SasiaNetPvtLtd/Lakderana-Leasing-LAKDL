import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import sun.misc.BASE64Decoder;
import java.sql.*;
//import LAKDAC_wbacc_methods;
import oracle.jdbc.driver.*;
//--------------------SAVE FIND PRINTERS--------------------------
public class LAKDL_sav_print_find extends HttpServlet {
	
	public ResultSet rs;
	Connection conn;	
	Statement stmt;
	CallableStatement callstmt1;
	
	File file1;
	int m_count_no=0;
	String m_msg,m_url;
	boolean m_chk;
	String m_deal_type,m_sendval;
	String m_chksql,m_dispno,m_specific_code,m_inv_no,m_val_date,m_stat,m_class_url;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	String m_username,reqstr;
	
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_wbacc_methods = new LAKDL_AF_CO_conn_methods();
			//conn = m_wbacc_methods.met_user_validate(req,res); 
			conn =m_wbacc_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			
			String m_client_name = m_wbacc_methods.client_name.trim();
			String m_schema_name = m_wbacc_methods.schema_name.trim();
			//**************************************************************					
			
			PrintStream out = new PrintStream(res.getOutputStream());
			String m_username = m_wbacc_methods.username;
			m_html_client_url = m_wbacc_methods.html_client_url;
			m_servlet_client_url= m_wbacc_methods.servlet_client_url;
			m_client_t3_port= m_wbacc_methods.client_t3_port; 
			m_class_url=m_wbacc_methods.servlet_client_url.trim()+":"+m_wbacc_methods.client_t3_port.trim();
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			LAKDL_Printer_find  printerfinder = new LAKDL_Printer_find();
			String reqstr = input.readLine();	
			
			int count=0;
			
			
			stmt = conn.createStatement ();
			
			m_url = m_class_url;  //m_html_client_url+"/LAKDL_MK_MAS_Printer_find ";
			
			m_msg = "'Printer Saved Successfully'";
			
			javax.print.PrintService[] m_print_services = printerfinder.get_server_printers();
			
			for(int i=0;i<m_print_services.length;i++){
				
				callstmt1 =	conn.prepareCall( "BEGIN "+m_schema_name+".SAV_FIND_PRINTER(?,?);END;");
				callstmt1.setString(1,m_print_services[i].getName().toUpperCase());
				callstmt1.setInt(2,i);
				callstmt1.execute();
			}
			conn.commit();
			conn.setAutoCommit(true);
			callstmt1.close();			
			conn.close();
			this.destroy();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_Printer_find?chksql=main_page';");
			//out.println("window.location.href='"+m_url+"/"+m_client_name+"MK_MAS_dashboard_add_sql';"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			out.close();			
		}
		catch (Throwable th) {
			try{
				conn.rollback();
				conn.setAutoCommit(true);
				callstmt1.close();
				conn.close();
			}catch(Exception e){
			}
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
				ps.println("Error When Saving Details");
				ps.flush();
				ps.close();
				
			}
			catch (Exception e) {
				out.println("Error When Saving Details");
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
		}finally{
			try{conn.setAutoCommit(true);
			}catch(Exception e){
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