import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : UDARA FOR OFSCL CLIENT LOAN FACILITY 07-10-2011

public class LAKDL_AF_RE_PRO_client_guar_details_drill_down extends javax.servlet.http.HttpServlet {
	/*
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	public ServletOutputStream out = null; 
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
		Statement stmt=null,stmt1=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
		 ServletOutputStream out = null; 
		 ResultSet rs=null,rs1=null,rs2=null;
		 String m_chksql=null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
			//--MODIFIED By :delanjali----------------------------------------
			//--DATE				: 2007-07-27--------------------------------------
			
			
			
			String m_html_client_url1=m_sn_methods.html_client_url.trim(); 
			
			String url = "";
			if(req.getParameter("url")!=null){
				url=req.getParameter("url");
			}
			
			if(url.equals("http://www.lakdac.lk")){
				m_html_client_url="http://www.lakdac.lk"; 
				m_class_url="http://www.lakdac.lk:/myserver/servlet"; 
			}
			
			else{
				
				m_html_client_url=m_sn_methods.html_client_url.trim(); 
			}
			//---------------------------------------------------------------
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			out = res.getOutputStream();
			
			m_chksql=req.getParameter("chksql");
			
			
			
			stmt1=conn.createStatement();
			stmt=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			else if(m_chksql.equals("CLIENT_GUAR_DETAILS")){
				String m_string="";				
				String m_client_code=req.getParameter("client_code");
				
				
				// ===================================================================================================================
				
				
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr><td><b><u> Guarantor Details </u></b></td></tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				rs= stmt1.executeQuery(" "+
					" SELECT APPLICATION_NO,RELATIONSHIP,PERIOD,TEL_NO,"+m_schema_name+".AF_CO_GET_FINANCE_NO(APPLICATION_NO) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
					" WHERE  GUARANTOR_CODE = '"+m_client_code+"' "+
					" ");
				
				
				
				
				int i = 0;
				
				while(rs.next()){
					if(i==0){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><b>Application No</b></td>");
						out.println("<td width='10%' class=div_input><b>Finance No</b></td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><b>Relationship</b></td>");
						out.println("<td width='10%' class=div_input><b>Period</b></td>");
						out.println("<td width='10%' class=div_input><b>Tel No.</b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input> "+rs.getString(1)+" </td>");
					out.println("<td width='10%' class=div_input> "+rs.getString(5)+" </td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input> "+rs.getString(2)+" </td>");
					out.println("<td width='10%' class=div_input> "+rs.getString(3)+" </td>");
					out.println("<td width='10%' class=div_input> "+rs.getString(4)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");	
					i = i+1;
				}
				
				if(i==0){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td><p>No Guaranter Details Found</p></td>"); 
					out.println("</tr>");
				}
				
				out.println("</table>");
				out.println("<br>");
				
				// ===================================================================================================================	
				
				// added by udara 19-11-2013
				// ===================================================================================================================
				
				
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr><td><b><u> Client Details (As Co-Applicant) </u></b></td></tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				rs= stmt1.executeQuery(" "+
					" SELECT APPLICATION_NO "+
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE  CO_APPLICANT = '"+m_client_code+"' "+
					" ");
				
				
				
				
				i = 0;
				
				while(rs.next()){
					if(i==0){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><b>Application No</b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input> "+rs.getString(1)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");	
					i = i+1;
				}
				
				if(i==0){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td><p>No Co Applicant Details Found</p></td>"); 
					out.println("</tr>");
				}
				
				out.println("</table>");
				out.println("<br>");
				
				// ===================================================================================================================
				
				
				
				// ================================ Added by udara 25-03-2016 ========================================================
				
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr><td><b><u> Client Details - Co Applicant </u></b></td></tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				rs= stmt1.executeQuery(" "+
					" SELECT "+
					" CO_APPLICANT, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') "+
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE  CLIENT_CODE = '"+m_client_code+"' "+
					" AND CO_APPLICANT IS NOT NULL   "+
					" AND CO_APPLICANT <> '-'   "+
					" ");
				
				i = 0;
				
				
				
				
				while(rs.next()){
					
					if(i==0){
						
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><b> Co Applicant Code </b></td>");
						out.println("<td width='10%' class=div_input><b> Co Applicant Name</b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						
					}
					
					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input onclick=show_client('" + rs.getString(1) + "')><u> "+rs.getString(1)+" </u></td>");
					out.println("<td width='10%' class=div_input> "+rs.getString(2)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");	
					
					i = i+1;
					
				}
				
				if(i==0){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td><p>No Co Applicant Found</p></td>"); 
					out.println("</tr>");
				}	
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</table>");
				out.println("<br>");	
				
				// ================================ End by udara 25-03-2016 ==========================================================
				
				
				
			}
			
			else {
				out.println("Undefined");
			}
			
			//out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try {out.println(e.toString());}catch (Exception eti) {}
			try {
				conn.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			//ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}


