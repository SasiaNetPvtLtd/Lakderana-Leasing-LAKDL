import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_sql_validations4 extends HttpServlet {
    /*
    Connection connection;
    Statement statement;
    java.text.NumberFormat nf;
    java.text.NumberFormat nf1;
    
    public ResultSet resultSet;
    public String m_chksql;
	*/
    
    //public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Connection connection=null;
    Statement statement=null;
    java.text.NumberFormat nf=null;
    java.text.NumberFormat nf1=null;
    
     ResultSet resultSet=null;
     String m_chksql=null;
        
        try {
            
            //************************************************************	
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods ();
            connection = m_sn_methods.met_user_validate(req); 
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
            
            m_chksql = req.getParameter("chksql").trim();
            statement=connection.createStatement();
            
            //m_prime_chk_
            
            if (m_chksql.equals("idle")) {
                out.println("idle");
            }
            
            
            // Added By Samitha On 2012-01-31
            else if(m_chksql.equals("m_prime_chk_get_marketing_officer_list")) {
                
                String m_collection_officer = req.getParameter("collection_officer");
                
                resultSet = statement.executeQuery (" " +
                    "   SELECT A.MARKETING_OFFICER, " +
                    "          B.TITLE || ' ' || B.FIRST_NAME || ' ' || B.LAST_NAME MARKETING_OFFICER_NAME " +
                    "   FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP A, " +
                    "          " + m_schema_name + ".CO_CO_MAS_EMPLOYEE B " +
                    "   WHERE  A.MARKETING_OFFICER = B.EMP_CODE " +
                    "   AND    A.COLLECTION_OFFICER = '" + m_collection_officer + "' " +
                    " ");
                
                out.print("<DATA>");
                while (resultSet.next()) {
                    out.print("<ITEM>");
                    out.print("<R1>" + resultSet.getString("MARKETING_OFFICER") + "</R1>");
                    out.print("<R2>" + resultSet.getString("MARKETING_OFFICER_NAME") + "</R2>");
                    out.print("</ITEM>");
                }
                out.print("</DATA>");
            }
            
            //Added by Jithendra on 2016-10-21
			else if(m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_id_no_duplication")) {
				
				String m_val = req.getParameter("data_val").trim();
				
				String SQL22="SELECT COUNT(*)"+
							 " FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
							"	WHERE UPPER(VAT_REG_NO)=UPPER('"+m_val+"')" ;
													
				resultSet = statement.executeQuery (SQL22);
				out.print("<DATA>");
				if (resultSet.next()) {
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getInt(1)+"</R1>");//" + resultSet.getInt(1) + "
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}else if(m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_id_no_duplication_edit")) {
				
				String m_val = req.getParameter("data_val").trim();
				String m_vendor = req.getParameter("m_client").trim();
				
				String SQL22="SELECT COUNT(*)"+
							 " FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
							 "	WHERE UPPER(VAT_REG_NO)=UPPER('"+m_val+"')" +
							 "  AND VENDOR_CODE <>'"+m_vendor+"'";
													
				resultSet = statement.executeQuery (SQL22);
				out.print("<DATA>");
				if (resultSet.next()) {
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getInt(1)+"</R1>");//" + resultSet.getInt(1) + "
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//End Jithendra on 2016-10-20
			
			// added by udara 11-07-2018
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RL_recovery_letter_docs1")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_val3 = req.getParameter("data_val3").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery (" SELECT 'AVAILABILITY' FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DOCS WHERE DOCUMENT_ID = '"+m_val+"' AND LETTER_ID = '"+m_val2+"' AND TRANSACTION_TYPE = '"+m_val3+"' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_excisting_data")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				//String m_status = req.getParameter("ac_status");	
				
				//resultSet= statement.executeQuery (" SELECT 'AVAILABILITY' FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DURATION WHERE TRANSACTION_TYPE = '"+m_val+"' AND LETTER_ID = '"+m_val2+"' ");
				//resultSet= statement.executeQuery (" SELECT 100 FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DURATION WHERE TRANSACTION_TYPE = '"+m_val+"' AND LETTER_ID = '"+m_val2+"' ");
				
				
				String m_sql = " SELECT 'AVAILABILITY' FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DURATION WHERE TRANSACTION_TYPE = '"+m_val+"' AND LETTER_ID = '"+m_val2+"' ";
				resultSet= statement.executeQuery(m_sql);
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_get_chk_mrfl_get_recovery_letter")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet=statement.executeQuery(" SELECT DISTINCT DOCUMENT_ID,DOCUMENT_NAME,B.LETTER_ORDER_BY FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DOCS A, "+m_schema_name+".AF_RL_MAS_RECOVERY_LETTER_TYPE B "+
					" WHERE A.TRANSACTION_TYPE = '"+m_val+"' "+
					" AND A.LETTER_ID = B.LETTER_ID "+
					" AND A.ACTIVE_STATUS = 'Y' "+
					" ORDER BY B.LETTER_ORDER_BY  ");//Added by Minal for #10000 on 09/08/2014
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_get_chk_LAKDL_get_APP_PRODUCT")){//Added By Kanishka Dilshan on 09-09-2015
				String m_val = req.getParameter("data_val").trim();
				
				resultSet=statement.executeQuery(" SELECT A.TRANSACTION_TYPE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A"+
					" WHERE A.FINANCE_NO = '"+m_val+"' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_get_chk_LAKDL_get_recovery_letter")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet=statement.executeQuery(" SELECT DISTINCT DOCUMENT_ID,DOCUMENT_NAME,B.LETTER_ORDER_BY FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DOCS A, "+m_schema_name+".AF_RL_MAS_RECOVERY_LETTER_TYPE B "+
					" WHERE A.TRANSACTION_TYPE = '"+m_val+"' "+
					" AND A.LETTER_ID = B.LETTER_ID "+
					" AND A.ACTIVE_STATUS = 'Y' "+
					" ORDER BY B.LETTER_ORDER_BY  ");//Added by Minal for #10000 on 09/08/2014
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			// end by udara 11-07-2018
            
            
            //------------------------------------------------------------------------------------------			
            else {
                out.println("Undefined");
            }
            
            out.close();
            connection.close();
            this.destroy();
            
            
        }
        catch (Exception e) {
            try {
                connection.close();
            }catch (Exception eti) {}
            
            ByteArrayOutputStream ostr = new ByteArrayOutputStream();
            e.printStackTrace(new PrintWriter(ostr));
            
            ServletOutputStream out = res.getOutputStream();
            out.println(ostr.toString());
            out.close();
            
        }
    }
}
