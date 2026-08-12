import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : UDARA FOR OFSCL CLIENT LOAN FACILITY 07-10-2011

public class LAKDL_AF_RE_PRO_client_loan_facility_drill_down extends javax.servlet.http.HttpServlet {
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
	
			else if(m_chksql.equals("LOAN_FACILITY_DETAILS")){
				String m_string="";				
				String m_client_code=req.getParameter("client_code");

						
				rs= stmt1.executeQuery(" "+
					" SELECT "+
						" B.LOAN_FACILITY_NO, "+ // 1
						" A.FINANCE_NO,  "+ // 2
						" B.REF_NO, "+  // 3
						" B.REF_NAME,  "+ // 4
						" NVL(B.REF_ADD1,'-'), "+ // 5 
					    " NVL(B.REF_ADD2,'-'), "+ // 6
				        " NVL(B.REF_TEL,'-')  "+ // 7
							 " FROM "+m_schema_name+".AF_CO_MAS_LOAN_FACILI_ASSIGN A,"+m_schema_name+".AF_CO_MAS_LOAN_FACILITIES B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+m_schema_name+".AF_CO_MAS_CLIENT D "+
							 " WHERE A.LOAN_FACILITY_NO = B.LOAN_FACILITY_NO "+
							 " AND A.FINANCE_NO = C.FINANCE_NO "+
	                         " AND C.CLIENT_CODE = D.CLIENT_CODE "+
	                         " AND D.CLIENT_CODE LIKE '%"+m_client_code+"%' "+
							 " ");

					int i = 0;

					while(rs.next()){
						if(i==0){
							out.println("<table align='center' width='100%' class='table' >");
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input><b>Loan Facility No</b></td>");
							out.println("<td width='10%' class=div_input><b>Finance No</b></td>");
							out.println("<td width='10%' class=div_input><b>Ref No</b></td>");
							out.println("<td width='10%' class=div_input><b>Ref Name</b></td>");
							out.println("<td width='12%' class=div_input><b>Ref Address 1</b></td>");
							out.println("<td width='12%' class=div_input><b>Ref Address 2</b></td>");
							out.println("<td width='10%' class=div_input><b>Ref Tel</b></td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
					    }

						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input> "+rs.getString(1)+" </td>");
						out.println("<td width='10%' class=div_input> "+rs.getString(2)+" </td>");
						out.println("<td width='10%' class=div_input> "+rs.getString(3)+" </td>");
						out.println("<td width='10%' class=div_input> "+rs.getString(4)+" </td>");
						out.println("<td width='12%' class=div_input> "+rs.getString(5)+" </td>");
						out.println("<td width='12%' class=div_input> "+rs.getString(6)+" </td>");
						out.println("<td width='10%' class=div_input> "+rs.getString(7)+" </td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");	
						i = i+1;
					}
			
					if(i==0){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td><p>No Loan Details Found</p></td>"); 
						out.println("</tr>");
					}
					
					out.println("</table>");
					out.println("<br>");
				
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


