import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LAKDL_AF_INSU_Renewal_details_excel_upload_confirmation
	extends HttpServlet
{
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException
	{
		
		ServletOutputStream out = null;
		Connection conn= null;
		CallableStatement callstmt1= null;
		
		try
		{
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url = m_sn_methods.html_client_url.trim();
			String m_class_url = m_sn_methods.servlet_client_url.trim() + ":" + m_sn_methods.client_t3_port.trim();
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_header_name = m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			//This Variable required Original Tomcat Location
			String m_tomcat_url = "https://dev-lakdl.sasianet.com:/myserver/servlet";
			
			res.setStatus(200);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			conn = m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			String m_msg="Information saved successfully.";
			
			
			
			callstmt1 = conn.prepareCall("BEGIN " + m_schema_name + ".AF_INSU_RENEWAL_EXCEL_CONFIRM(:1,:2,:3,:4); END;");
			callstmt1.setString(1, req.getParameter("batchId"));
			callstmt1.setString(2, req.getParameter("mode"));		
			callstmt1.setString(3, req.getParameter("screen"));		
			callstmt1.setString(4, m_sn_methods.username.trim());		
			callstmt1.execute();
			
			conn.commit();
			
			out.println("<HTML><HEAD></HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println(" alert('" + m_msg + "');");
			
			if(req.getParameter("screen").equals("UPLOAD")){
				out.println(" window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_upload?chksql=main_page';");
			}else if(req.getParameter("screen").equals("APPROVAL")){
				out.println(" window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_INSU_Renewal_details_excel_approval?chksql=main_page';");
			}
		
			out.println("}");
			out.println("</SCRIPT>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</body>");
			out.println("</html>");
			
			out.flush();
			out.close();
		}
		catch (Exception ex)
		{
			try{conn.close();}catch(Exception ee){}
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			ex.printStackTrace(new PrintStream(ostr));
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('error Occured');");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'>"+ostr.toString()+"</body>");
			out.println("</html>");
			out.flush();
		}
		finally
		{
			if (out != null) {
				try
				{
					out.close();
				}
				catch (Exception e) {}
			}
		}
	}
}
