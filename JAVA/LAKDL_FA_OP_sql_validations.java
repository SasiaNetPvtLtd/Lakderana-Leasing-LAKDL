import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_OP_sql_validations extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1;
	public String m_chksql,m_sql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods ();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/xml");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			
		if (m_chksql.trim().equals("idle")) {
				out.println("<DATA>IDLE</DATA>");
		}	
		else if(m_chksql.trim().equals("m_total_settlement_val")){

				String m_client_code = req.getParameter("client_code").trim();
				String m_facility_no = req.getParameter("facility_no").trim();
				
				double m_val=0;
				
				rs = stmt.executeQuery("SELECT NVL(SUM(BALANCE_AMOUNT),0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE REC_STATUS='Y' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_NO='"+m_facility_no+"'");
					
				if(rs.next()){
					m_val=rs.getDouble(1);
				}
				
				out.println("<DATA>");
				out.println("<ITEM>");
				out.println("<R1>"+nf.format(m_val)+"</R1>");
				out.println("</ITEM>");
				out.println("</DATA>");      
    }
		else if (m_chksql.trim().equals("m_tot_pending_bal")){
			
			String m_client_code = req.getParameter("client_code").trim();
			String m_facility_no = req.getParameter("facility_no").trim();
			
			rs=stmt.executeQuery("SELECT NVL(SUM(BALANCE_AMOUNT),0) "+
					" FROM( "+
					" SELECT SUM(A.BALANCE_AMOUNT) BALANCE_AMOUNT "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE A.INVOICE_STATUS='CONF' "+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" ) ");
		 		
				double m_val=0;
				
				if(rs.next()){
					m_val=rs.getDouble(1);
				}
				
				out.println("<DATA>");
				out.println("<ITEM>");
				out.println("<R1>"+nf.format(m_val)+"</R1>");
				out.println("</ITEM>");
				out.println("</DATA>"); 
			
    }
  	else {
			    out.println("Undefined");
		}
			
    out.close();
		conn.close();
		this.destroy();
		}
		catch (Exception e) {
				try {
						conn.close();
				}
				catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}


