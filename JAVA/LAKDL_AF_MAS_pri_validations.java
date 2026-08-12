import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_pri_validations extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
	
	public ResultSet rs,rs1;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
		Statement stmt=null,stmt1=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null;
		java.text.NumberFormat nf1=null;
		
		 ResultSet rs=null,rs1=null;
		 String m_chksql=null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods ();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);      
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/xml");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			
			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			
			//m_prime_chk_
			
			if (m_chksql.trim().equals("idle")) 
			{
				out.println("idle");
			}	
			else if (m_chksql.trim().equals("print_info"))
			{
				
				String m_follow_up_num = req.getParameter("user_id").trim();
				
				rs = stmt.executeQuery(" SELECT NVL(PRINTER,' '),SEL_TYPE  "+
					 " FROM "+m_schema_name+".REF_USER_PRINTER where user_name='"+m_follow_up_num+"' and SEL_TYPE='Y' group by PRINTER,SEL_TYPE ");
				
				//rs= stmt.executeQuery ("SELECT STAGE_CODE,DESCRIPTION,DIVISION_CODE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_PROCESS_STAGE "+
				//	" WHERE UPPER(STAGE_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next())
				{
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			//------------------------------------------------------------------------------------------			
			else {
				out.println("Undefined");
			}
			
			out.close();
			conn.close();
			this.destroy();
			
			
		}
		catch (Exception e) 
		{
			try 
			{
				conn.close();
			}
			catch (Exception eti) 
			{}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}

