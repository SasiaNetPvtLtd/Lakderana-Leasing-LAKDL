import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CO_PRO_sql_validations extends javax.servlet.http.HttpServlet {
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
				out.println("idle");
			}	
			
							
			//Added By Nuwan De Silva 
			//Purpose :Validate The Item Sub Category
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CO_PRO_item_sub_category_del")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
														
					
		    rs= stmt.executeQuery (" SELECT "+
			      " A.ITEM_SUB_CAT_CODE ITEM_SUB_CAT_CODE ,"+
						" B.DESCRIPTION DESCRIPTION, "+
			      " TO_CHAR(A.APP_DATE,'DD-MM-YYYY') APP_DATE , "+
						" A.VAT_RATE VAT_RATE "+
			      " FROM "+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL A,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY B "+
						" WHERE A.ITEM_SUB_CAT_CODE=B.ITEM_SUB_CAT AND (A.ITEM_SUB_CAT_CODE=UPPER('"+m_val+"') OR UPPER(B.DESCRIPTION)=UPPER('"+m_val+"'))  ");
    

				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			
			
						//Added By Nuwan De Silva 
			//Purpose :Validate The Item Sub Category
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CO_PRO_item_sub_category")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
						
		    rs= stmt.executeQuery (" SELECT "+
			" ITEM_SUB_CAT ,"+
      " DESCRIPTION "+
      " FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
			" WHERE (ITEM_SUB_CAT=UPPER('"+m_val+"') OR UPPER(DESCRIPTION)=UPPER('"+m_val+"'))  AND ACTIVE_STATUS=('"+m_status+"') "+
			" ORDER BY ITEM_SUB_CAT ");
			
			//-----------------------------------------------------------------------------------------------------------
			  rs= stmt.executeQuery ("SELECT TRAN_CODE,DESCRIPTION FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
				" WHERE (UPPER(TRAN_CODE)=UPPER('"+m_val+"')  OR  UPPER(DESCRIPTION)=UPPER('"+m_val+"'))AND ACTIVE_STATUS='"+m_status+"' ");

				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
							
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
        //added by nuwan de silva 30-07-07====================================================================
				else if (m_chksql.trim().equals("m_prime_chk_validate_transaction_code_vat_on_rental")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();

			  rs= stmt.executeQuery ("SELECT TRAN_CODE,DESCRIPTION FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
				" WHERE (UPPER(TRAN_CODE)=UPPER('"+m_val+"')  OR  UPPER(DESCRIPTION)=UPPER('"+m_val+"'))AND ACTIVE_STATUS='"+m_status+"' ");

				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
							
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			
				else if (m_chksql.trim().equals("m_prime_chk_validate_transaction_code_vat_on_rental_del")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
														
					
		    rs= stmt.executeQuery (" SELECT "+
			      " A.TRN_CODE TRN_CODE ,"+
						" B.DESCRIPTION DESCRIPTION, "+
			      " TO_CHAR(A.APP_DATE,'DD-MM-YYYY') APP_DATE , "+
						" A.VAT_RATE VAT_RATE "+
			      " FROM "+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
						" WHERE A.TRN_CODE=B.TRAN_CODE AND (A.TRN_CODE=UPPER('"+m_val+"') OR UPPER(B.DESCRIPTION)=UPPER('"+m_val+"'))  ");
    

				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			
			else if(m_chksql.trim().equals("get_sys_date")){
					
				rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");

				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getString(3)+"</R3>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
    }
			
			

			//==================================================================================================
			
			
			  

			
					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

			
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


