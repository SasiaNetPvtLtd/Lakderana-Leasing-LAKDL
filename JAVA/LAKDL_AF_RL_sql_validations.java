import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

//CREATED BY : KANISHKA DILSHAN
//CREATED ON : 27-07-2015

public class LAKDL_AF_RL_sql_validations extends javax.servlet.http.HttpServlet {
	
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
			
			else if (m_chksql.trim().equals("RECOVERY_LETTER_CHARGES")){
				
				String m_Document_code 	= req.getParameter("document_code");
				String m_Letter_code 	= req.getParameter("letter_code");
				String m_Product_code 	= req.getParameter("product_code");
				
				
				
				String m_query = " SELECT CHARGE_CODE,"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(CHARGE_CODE),NVL(CHARGE_AMOUNT,0),DECODE(ACTIVE_STATUS,'Y','YES','NO') "+
					" FROM "+m_schema_name+".AF_RL_DOCUMENT_CHARGE_DET "+
					" WHERE DOCUMENT_ID = '"+m_Document_code+"' "+
					" AND 	LETTER_ID = '"+m_Letter_code+"' "+
					" AND 	TRANSACTION_TYPE = '"+m_Product_code+"' "+
					"";
				
				try{
					rs = stmt.executeQuery(m_query);
				}catch(Exception e){
					res.setContentType("text/html");
					
					throw new Exception("ERROR ==> "+e.toString());
				}
				out.println("<?xml version='1.0' encoding='utf-8' ?>");
				out.println("<rows>");
				int i=0;
				while(rs.next()){
					i++;
					out.print("<row id='"+i+"'>"); 
					out.print("<cell><![CDATA["+ rs.getString(1)+"]]></cell>"); 
					out.print("<cell><![CDATA["+ rs.getString(2)+"]]></cell>"); 
					out.print("<cell><![CDATA["+ nf.format(rs.getDouble(3))+"]]></cell>"); 
					out.print("<cell><![CDATA["+ rs.getString(4)+"]]></cell>"); 
					out.print("<cell><![CDATA[-]]></cell>"); 
					out.print("</row>");
				}
				
				i++;
				out.print("<row id='"+i+"'>"); 
				out.print("<cell><![CDATA[]]></cell>"); 
				out.print("<cell><![CDATA[]]></cell>"); 
				out.print("<cell><![CDATA[0.00]]></cell>"); 
				out.print("<cell><![CDATA[YES]]></cell>"); 
				out.print("<cell><![CDATA[+]]></cell>"); 
				out.print("</row>");
				
				out.print("</rows>");
				
			}
			else if (m_chksql.trim().equals("RECOVERY_LETTER_CLONE_BASE_SETUP")){
				
				String m_base_transaction 	= req.getParameter("base_code");
				
				
				
				String m_query = " SELECT "+m_schema_name+".AF_RL_GET_LETTER_DOC_NAME(A.LETTER_ID,A.TRANSACTION_TYPE), "+
					" 		  	NVL("+m_schema_name+".AF_RL_GET_LETTER_DOC_NAME(A.DEPENDANT_LETTER_ID,A.TRANSACTION_TYPE),'From The Invoice Due Date'), "+
					"        	A.DURATION, "+
					" 			B.ORDER_BY "+
					" FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DURATION A ,"+m_schema_name+".AF_RL_RECOVERY_LETTER_DOCS B  "+
					" WHERE A.LETTER_ID 		= B.DOCUMENT_ID "+
					" AND 	A.TRANSACTION_TYPE 	= B.TRANSACTION_TYPE "+
					" AND 	B.TRANSACTION_TYPE 	= '"+m_base_transaction+"' "+
					" ORDER BY B.ORDER_BY "+
					" ";
				
				try{
					rs = stmt.executeQuery(m_query);
				}catch(Exception e){
					res.setContentType("text/html");
					throw new Exception("ERROR ==> "+e.toString());
				}
				out.println("<?xml version='1.0' encoding='utf-8' ?>");
				out.println("<rows>");
				int i=0;
				while(rs.next()){
					i++;
					out.print("<row id='"+i+"'>"); 
					out.print("<cell><![CDATA["+ rs.getString(1)+"]]></cell>"); 
					out.print("<cell><![CDATA["+ rs.getString(2)+"]]></cell>"); 
					out.print("<cell><![CDATA["+ rs.getString(3)+"]]></cell>"); 
					out.print("</row>");
				}
				out.print("</rows>");
				
			}
			//------------------------------------------------------------------------------------------			
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


