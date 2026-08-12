import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_CO_CO_MAS_sql_validations extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
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
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
            else if (m_chksql.trim().equals("GET_PASSWORD_POLICY")) {
                
                rs = stmt.executeQuery(" " +
                    "   SELECT  " +
										"		NVL(MINIMUM_LENGTH,0), " +
										"		NVL(MAXIMUM_LENGTH,0), " +
										"		NVL(LOWERCASE_CHAR,0), " +
										"		NVL(UPPERCASE_CHAR,0), " +
										"		NVL(NUMERIC_CHAR,0), " +
										"		NUMERIC_EMBEDDED, " +
										"		NVL(SPECIAL_CHAR,0), " +
										"		SPECIAL_CHAR_EMBEDDED, " +
										"		NVL(PASSWORD_CHANGE_ATTEMPT,0), " +
										"		ALLOW_USER_NAME, " +
										"		NVL(REPEAT_PASSWORD,0), " +
										"		NVL(PASSWORD_CHANGE_MINIMUM_GAP,0), " +
										"		NVL(PASSWORD_EXPIRATION_DAYS,0), " +
										"		NVL(WARNING_MESSAGE_DAYS,0) " +
                    "   FROM " + m_schema_name + ".CO_CO_MAS_PASSWORD_POLICY A " );
                
                out.print("<DATA>");
                if (rs.next()) {
                    out.print("<ITEM>");
                    out.print("<R1>"+rs.getString(1)+"</R1>");
                    out.print("<R2>"+rs.getString(2)+"</R2>");
                    out.print("<R3>"+rs.getString(3)+"</R3>");
                    out.print("<R4>"+rs.getString(4)+"</R4>");
                    out.print("<R5>"+rs.getString(5)+"</R5>");
                    out.print("<R6>"+rs.getString(6)+"</R6>");
										out.print("<R7>"+rs.getString(7)+"</R7>");
                    out.print("<R8>"+rs.getString(8)+"</R8>");
                    out.print("<R9>"+rs.getString(9)+"</R9>");
                    out.print("<R10>"+rs.getString(10)+"</R10>");
                    out.print("<R11>"+rs.getString(11)+"</R11>");
                    out.print("<R12>"+rs.getString(12)+"</R12>");
                    out.print("<R13>"+rs.getString(13)+"</R13>");
                    out.print("<R14>"+rs.getString(14)+"</R14>");
                    out.print("</ITEM>");
                }
                out.print("</DATA>");
                
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
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}


