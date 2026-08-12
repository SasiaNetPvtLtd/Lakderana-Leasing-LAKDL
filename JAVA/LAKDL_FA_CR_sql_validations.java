import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_CR_sql_validations extends javax.servlet.http.HttpServlet {
	
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
			
			// Added by Udara on 30-05-2011
			else if(m_chksql.trim().equals("get_pd_return_count")){
				
				String pd_no = req.getParameter("pd_no").trim();	
				
				rs = stmt.executeQuery(" "+
					" SELECT COUNT(*) "+
					" FROM "+m_schema_name+".FA_OP_PRO_POD_CHQ_REBANK A "+
					" WHERE A.POD_REF_NO = '"+pd_no+"' "+
					" ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");   
				
			}
			
			// End by Udara on 30-05-2011
			
			
			else if (m_chksql.trim().equals("m_NEW_PRODUCT_FEATURE")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				String m_va11 = req.getParameter("client_code").trim();	
				
				/*m_sql="SELECT A.FA_FEATURE_CODE,B.FA_FEATURE_DESC,A.FA_PROD_COMMENT,NVL(A.PARAMETER_VALUE,' ') "+
				  " FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_PACK A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
					" WHERE A.FA_FEATURE_CODE=B.FA_FEATURE_CODE AND "+
					" A.FA_PRODUCT_CODE='"+m_va1+"' ";
				*/
				m_sql="SELECT B.FA_FEATURE_CODE,B.FA_FEATURE_DESC,NVL(B.FA_FEATURE_COMMENTS,'-'),NVL(A.PARAMETER_VALUE,'-') "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
					" WHERE A.PRODUCT_FEATURE_CODE=B.FA_FEATURE_CODE AND "+
					" A.FACILITY_NO='"+m_va1+"' AND A.CLIENT_CODE='"+m_va11+"'";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_NEW_PRODUCT_FEATURE_FACILITY")){
				
				String m_va1 = req.getParameter("product_code").trim();		
				
				m_sql="SELECT A.FA_FEATURE_CODE,B.FA_FEATURE_DESC,A.FA_PROD_COMMENT,NVL(A.PARAMETER_VALUE,' ') "+
					" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_PACK A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
					" WHERE A.FA_FEATURE_CODE=B.FA_FEATURE_CODE AND "+
					" A.FA_PRODUCT_CODE='"+m_va1+"' ";
				
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_NEW_FEE_FEATURE")){
				
				//String m_va1 = req.getParameter("fee_code").trim();		
				String m_va1 = req.getParameter("facility_code").trim();		
				String m_va11 = req.getParameter("client_code").trim();	
				/*m_sql="SELECT A.FEE_CODE,NVL(B.FEE_DESC,'-'),DECODE(B.FEE_TYPE,'C','CHARGE','O','OTHER'),NVL(A.MINIUM_VALUE,0) "+
				  " FROM "+m_schema_name+".FA_CO_MAS_FEE_PACK_DET A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE AND "+
					" A.FEE_PACK_CODE='"+m_va1+"' ";*/
				
				m_sql="SELECT A.FEE_CODE,NVL(B.FEE_DESC,'-'),DECODE(B.FEE_TYPE,'C','CHARGE','P','PRIMARY CHARGES','OTHER'),NVL(A.APPLICABLE_VALUE,0) "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE AND "+
					" A.FACILITY_NO='"+m_va1+"' AND A.CLIENT_CODE='"+m_va11+"'";
				
				rs= stmt.executeQuery (m_sql);
				
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
			
			else if (m_chksql.trim().equals("m_NEW_FEE_FEATURE_FACILITY")){
				
				String m_va1 = req.getParameter("fee_code").trim();		
				
				m_sql="SELECT A.FEE_CODE,NVL(B.FEE_DESC,'-'),DECODE(B.FEE_TYPE,'C','CHARGE','P','PRIMARY CHARGES','OTHER'),NVL(A.MINIUM_VALUE,0) "+
					" FROM "+m_schema_name+".FA_CO_MAS_FEE_PACK_DET A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE AND "+
					" A.FEE_PACK_CODE='"+m_va1+"' ";
				
				rs= stmt.executeQuery (m_sql);
				
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
			
			else if (m_chksql.trim().equals("m_EDIT_MAIN")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				
				m_sql=" SELECT  "+
					" FACILITY_NO,   "+
					" CLIENT_CODE,   "+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),  "+
					" FACILITY_MGR_CODE,  "+
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),  "+
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C'),"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N'),  "+
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','C'),"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N'),  "+
					" FA_PRODUCT_CODE,"+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),   "+
					" FEE_PACK_CODE,"+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE),  "+
					" TO_CHAR(FACILITY_START_DATE,'DD'),TO_CHAR(FACILITY_START_DATE,'MM'),TO_CHAR(FACILITY_START_DATE,'YYYY'),  "+
					" TO_CHAR(FACILITY_END_DATE,'DD'),TO_CHAR(FACILITY_END_DATE,'MM'),TO_CHAR(FACILITY_END_DATE,'YYYY'),  "+
					" CREDIT_LIMIT,  "+
					" CREDIT_PERIOD,   "+
					" TOLERANCE_CREDIT_PERIOD,   "+
					" RESERVE_MARGIN,  "+
					" INT_RATE, "+
					" NVL(ENTRY_COMMENTS,' ') "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY   "+
					" WHERE FACILITY_NO='"+m_va1+"' ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");out.print("<R10>"+rs.getString(10)+"</R10>");out.print("<R11>"+rs.getString(11)+"</R11>");out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");out.print("<R14>"+rs.getString(14)+"</R14>");out.print("<R15>"+rs.getString(15)+"</R15>");out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(17)+"</R17>");out.print("<R18>"+rs.getString(18)+"</R18>");out.print("<R19>"+rs.getString(19)+"</R19>");out.print("<R20>"+nf.format(rs.getDouble(20))+"</R20>");
					out.print("<R21>"+nf.format(rs.getDouble(21))+"</R21>");out.print("<R22>"+nf.format(rs.getDouble(22))+"</R22>");out.print("<R23>"+nf.format(rs.getDouble(23))+"</R23>");
					out.print("<R24>"+nf.format(rs.getDouble(24))+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_EDIT_PRODUCT")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				String m_va11 = req.getParameter("product_code").trim();	
				
				m_sql=" SELECT  "+
					" A.PRODUCT_FEATURE_CODE,  "+
					" NVL(B.FA_FEATURE_DESC,' '),  "+
					" NVL(B.FA_FEATURE_COMMENTS,'-'),  "+
					" NVL(A.PARAMETER_VALUE,' '),  "+
					" 'Y' "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B  "+
					" WHERE   A.FACILITY_NO='"+m_va1+"'  AND   A.PRODUCT_FEATURE_CODE=B.FA_FEATURE_CODE "+
					" UNION "+
					" SELECT A.FA_FEATURE_CODE, "+
					" NVL(B.FA_FEATURE_DESC,' '),  "+
					" NVL(B.FA_FEATURE_COMMENTS,'-'),  "+
					" NVL(A.PARAMETER_VALUE,' '),  "+
					" 'N' "+
					" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_PACK A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
					" WHERE A.FA_FEATURE_CODE=B.FA_FEATURE_CODE "+
					" AND A.FA_PRODUCT_CODE='"+m_va11+"'  AND "+
					" A.FA_FEATURE_CODE NOT IN (SELECT PRODUCT_FEATURE_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_PROD WHERE FACILITY_NO='"+m_va1+"') ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_EDIT_FEE")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				String m_va11 = req.getParameter("fee_code").trim();	
				
				m_sql=" SELECT  "+
					" A.FEE_CODE, "+
					" B.FEE_DESC, "+
					" DECODE(B.FEE_TYPE,'C','CHARGE','P','PRIMARY CHARGES','OTHER'), "+
					" NVL(A.APPLICABLE_VALUE,0), "+
					" 'Y' "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE AND A.FACILITY_NO='"+m_va1+"' "+
					" UNION "+
					" SELECT  "+
					" A.FEE_CODE, "+
					" B.FEE_DESC, "+
					" DECODE(B.FEE_TYPE,'C','CHARGE','P','PRIMARY CHARGES','OTHER'), "+
					" NVL(A.MINIUM_VALUE,B.MINIUM_VALUE), "+
					" 'N' "+
					" FROM "+m_schema_name+".FA_CO_MAS_FEE_PACK_DET A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE "+
					" AND A.FEE_PACK_CODE='"+m_va11+"' "+
					" AND A.FEE_CODE NOT IN (SELECT FEE_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE WHERE FACILITY_NO='"+m_va1+"') ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_EDIT_GRANTEE")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				
				m_sql=" SELECT  "+
					" NVL(A.GURANT_NAME,' '), "+
					" NVL(A.GURANT_BANK,' '), "+
					" NVL(A.GURANT_CONT_PERSON,' '),  "+
					" DECODE(A.GURANT_START_DATE,'',' ',TO_CHAR(A.GURANT_START_DATE,'DD-MM-YYYY')),  "+
					" DECODE(A.GURANT_END_DATE,'',' ',TO_CHAR(A.GURANT_END_DATE,'DD-MM-YYYY')), "+
					" NVL(A.GURANT_VALUE,0),  "+
					" NVL(A.GURANT_COMMENTS,' ') "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_GURNT A "+
					" WHERE A.FACILITY_NO='"+m_va1+"' ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			//added by disnaka on 2011-10-05
			
			
			else if (m_chksql.trim().equals("m_EDIT_GRANTOR")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				
				m_sql=" SELECT  "+
					" A.GUARANTOR_CODE, "+
					" "+m_schema_name+".AF_GET_CLIENT_FULL_NAME(A.GUARANTOR_CODE) "+
					" FROM "+m_schema_name+".FA_CO_PRO_FACILITY_GUARANTOR A "+
					" WHERE A.FACILITY_NO='"+m_va1+"' ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//---
			else if (m_chksql.trim().equals("m_EDIT_DEBTOR_MAIN")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				String m_va11 = req.getParameter("debtor_code").trim();		
				
				m_sql=" SELECT  "+
					" A.FACILITY_NO,"+//1
					" A.CLIENT_CODE,"+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),"+//5
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'M','O'),"+//6
					" A.MKT_CODE,"+//7
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(A.MKT_CODE,'O','O'),"+//8
					" "+m_schema_name+".FA_GET_CLIENT_TOTAL_CR_LIMIT(A.CLIENT_CODE),"+//9
					" A.FA_PRODUCT_CODE,"+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(A.FA_PRODUCT_CODE),"+//11
					" A.FEE_PACK_CODE,"+m_schema_name+".FA_GET_FEE_PACK_NAME(A.FEE_PACK_CODE),"+//13
					" A.CREDIT_LIMIT,"+//14
					" A.CREDIT_PERIOD,"+//15
					" A.TOLERANCE_CREDIT_PERIOD,"+//16
					" A.RESERVE_MARGIN, "+//17
					" NVL(A.DESIGNATION_PAYMENT,NVL(B.DESIGNATION_PAYMENT,'-')), "+//18
					" NVL(A.CONTACT_PERSON,NVL(B.REGISTERED_CONTACT_PERSON,'-')) "+//19
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.FACILITY_NO='"+m_va1+"' AND A.DEBTOR_CODE='"+m_va11+"' ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");out.print("<R10>"+rs.getString(10)+"</R10>");out.print("<R11>"+rs.getString(11)+"</R11>");out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");out.print("<R14>"+nf.format(rs.getDouble(14))+"</R14>");out.print("<R15>"+nf.format(rs.getDouble(15))+"</R15>");out.print("<R16>"+nf.format(rs.getDouble(16))+"</R16>");
					out.print("<R17>"+nf.format(rs.getDouble(17))+"</R17>");
					out.print("<R18>"+rs.getString(18)+"</R18>");
					out.print("<R19>"+rs.getString(19)+"</R19>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_EDIT_DEBTOR_PRODUCT")){
				
				String m_va1 = req.getParameter("facility_code").trim();	
				String m_va11 = req.getParameter("debtor_code").trim();	
				
				m_sql=" SELECT  "+
					" A.PRODUCT_FEATURE_CODE,  "+
					" NVL(B.FA_FEATURE_DESC,' '),  "+
					" NVL(B.FA_FEATURE_COMMENTS,'-'),  "+
					" NVL(A.PARAMETER_VALUE,' ')  "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBT_PROD A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B  "+
					" WHERE FACILITY_NO='"+m_va1+"' AND DEBTOR_CODE='"+m_va11+"'  AND   A.PRODUCT_FEATURE_CODE=B.FA_FEATURE_CODE ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_EDIT_DEBTOR_FEE")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				String m_va11 = req.getParameter("debtor_code").trim();	
				
				m_sql=" SELECT  "+
					" A.FEE_CODE, "+
					" B.FEE_DESC, "+
					" DECODE(B.FEE_TYPE,'C','CHARGE','P','PRIMARY CHARGES','OTHER'), "+
					" NVL(A.APPLICABLE_VALUE,0) "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBT_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE AND FACILITY_NO='"+m_va1+"' AND DEBTOR_CODE='"+m_va11+"' ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_LOAD_QUOTATION")){
				
				String m_va1 = req.getParameter("quotation_code").trim();		
				
				m_sql=" SELECT "+
					" QUOTATION_NO, "+//1
					" CLIENT_CODE,  "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//3
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') CLIENT_CODE, "+//4
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','C') CLIENT_CODE, "+//6
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') CLIENT_MANAGER, "+//7
					" NVL(CREDIT_LIMIT,0), "+//8
					" NVL(CREDIT_PERIOD,0), "+//9
					" NVL(TOLERANCE_CREDIT_PERIOD,0), "+//10
					" NVL(RESERVE_MARGIN,0), "+//11
					" NVL(INT_RATE,0), "+//12
					" FA_PRODUCT_CODE,  "+//13
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE), "+//14
					" FEE_PACK_CODE, "+//15
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE) "+//16
					" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION  "+
					" WHERE QUOTATION_STATUS='Y' AND QUOTATION_NO='"+m_va1+"'";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
					out.print("<R9>"+nf.format(rs.getDouble(9))+"</R9>");out.print("<R10>"+nf.format(rs.getDouble(10))+"</R10>");out.print("<R11>"+nf.format(rs.getDouble(11))+"</R11>");out.print("<R12>"+nf.format(rs.getDouble(12))+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");out.print("<R14>"+rs.getString(14)+"</R14>");out.print("<R15>"+rs.getString(15)+"</R15>");out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_LOAD_QUOTATION_PRODUCT")){
				
				String m_va1 = req.getParameter("quotation_code").trim();		
				String m_va11 = req.getParameter("product_code").trim();		
				
				m_sql=" SELECT A.FA_FEATURE_CODE,"+
					" B.FA_FEATURE_DESC,"+
					" '-',"+
					" NVL(A.PARAMETER_VALUE,' '), "+
					" 'Y' "+
					" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION_PRODUCT A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
					" WHERE A.FA_FEATURE_CODE=B.FA_FEATURE_CODE AND "+
					" A.QUOTATION_NO='"+m_va1+"' "+
					" UNION "+
					" SELECT A.FA_FEATURE_CODE, "+
					" NVL(B.FA_FEATURE_DESC,' '),  "+
					" NVL(B.FA_FEATURE_COMMENTS,'-'),  "+
					" NVL(A.PARAMETER_VALUE,' '),  "+
					" 'N' "+
					" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_PACK A,"+m_schema_name+".FA_CO_MAS_PROD_FEATURES B "+
					" WHERE A.FA_FEATURE_CODE=B.FA_FEATURE_CODE "+
					" AND A.FA_PRODUCT_CODE='"+m_va11+"'  AND "+
					" A.FA_FEATURE_CODE NOT IN (SELECT FA_FEATURE_CODE FROM "+m_schema_name+".FA_MK_PRO_QUOTATION_PRODUCT WHERE QUOTATION_NO='"+m_va1+"') ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_LOAD_QUOTATION_FEE")){
				
				String m_va1 = req.getParameter("quotation_code").trim();		
				String m_va11 = req.getParameter("fee_code").trim();		
				
				m_sql=
					" SELECT A.FEE_CODE,"+
					" NVL(B.FEE_DESC,'-'),"+
					" DECODE(B.FEE_TYPE,'C','CHARGE','P','PRIMARY CHARGES','OTHER'),"+
					" NVL(A.FEE_VALUE,0), "+
					" 'Y' "+
					" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE AND "+
					" A.QUOTATION_NO='"+m_va1+"' "+
					" UNION "+
					" SELECT  "+
					" A.FEE_CODE, "+
					" B.FEE_DESC, "+
					" DECODE(B.FEE_TYPE,'C','CHARGE','P','PRIMARY CHARGES','OTHER'), "+
					" NVL(A.MINIUM_VALUE,B.MINIUM_VALUE), "+
					" 'N' "+
					" FROM "+m_schema_name+".FA_CO_MAS_FEE_PACK_DET A,"+m_schema_name+".FA_CO_MAS_FEES B "+
					" WHERE A.FEE_CODE=B.FEE_CODE "+
					" AND A.FEE_PACK_CODE='"+m_va11+"' "+
					" AND A.FEE_CODE NOT IN (SELECT FEE_CODE FROM "+m_schema_name+".FA_MK_PRO_QUOTATION_FEE WHERE QUOTATION_NO='"+m_va1+"') ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.trim().equals("m_LOAD_QUOTATION_FACILITY")){
				
				String m_va1 = req.getParameter("facility_code").trim();		
				
				m_sql="SELECT NVL(QUOTATION_NO,' ') FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY WHERE FACILITY_NO='"+m_va1+"' ";
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if(m_chksql.trim().equals("get_exchange_rate")){
				
				String m_curren_code=req.getParameter("CURR_CODE");
				String m_value_date =req.getParameter("VAL_DATE");
				
				rs = stmt.executeQuery(" SELECT EXCHANGE_RATE,CURR_CODE,TRN_DATE "+
					" FROM   "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
					" WHERE  CURR_CODE=UPPER('"+m_curren_code+"') AND "+
					" TRN_DATE=(SELECT MAX(TRN_DATE) FROM "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE WHERE TRN_DATE<=TO_DATE('"+m_value_date+"','DD-MM-YYYY') AND CURR_CODE=UPPER('"+m_curren_code+"'))");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<EXR>"+rs.getString(1)+"</EXR>");
					out.println("<CUR>"+rs.getString(2)+"</CUR>");
					out.println("<TRD>"+rs.getString(3)+"</TRD>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
			}
			//Added by Mahela on 27-12-2006
			else if(m_chksql.trim().equals("get_debtor_name")){
				
				String m_debtor_code = req.getParameter("debtor_code").trim();
				String m_client_code = req.getParameter("client_code").trim();
				String m_facility_no = req.getParameter("facility_no").trim();
				
				rs = stmt.executeQuery("	SELECT A.DEBTOR_CODE,B.FULL_NAME "+
					"	FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
					"	WHERE A.FACILITY_NO='"+m_facility_no+"' AND "+
					"	A.CLIENT_CODE='"+m_client_code+"' AND "+
					" B.CLIENT_CODE=A.DEBTOR_CODE AND "+
					" B.ACTIVE_STATUS NOT IN('N','B','T') AND "+
					" (A.DEBTOR_CODE=UPPER('"+m_debtor_code+"')  OR UPPER(FULL_NAME)=UPPER('"+m_debtor_code+"'))");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<DCODE>"+rs.getString(1)+"</DCODE>");
					out.println("<NAME>"+rs.getString(2)+"</NAME>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
			}
			
			//Added by Mahela on 15-12-2006
			else if (m_chksql.trim().equals("get_CLIENT_CREDIT_PERIOD")){
				
				String m_val = req.getParameter("client_code").trim();
				
				rs=stmt.executeQuery("SELECT CLIENT_CODE,NVL(AVG_CREDIT_PERIOD_EXT,0),NVL(AVG_TOLARENCE_PERIOD,0) FROM "+m_schema_name+".FA_CO_MAS_CLIENT_DEBT_DET "+
					" WHERE CLIENT_CODE = UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");
					out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if(m_chksql.trim().equals("get_check_invoice_no")){
				
				String m_client_code = req.getParameter("client_code").trim();
				String m_invoice_no = req.getParameter("invoice_no").trim();
				
				rs = stmt.executeQuery(" SELECT A.INVOICE_SEQ_NO,B.BATCH_NO "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE B.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND A.INVOICE_NO='"+m_invoice_no+"' ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<DCODE>"+rs.getString(1)+"</DCODE>");
					out.println("<DCODE>"+rs.getString(2)+"</DCODE>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
			}
			else if(m_chksql.trim().equals("get_check_serial_no")){
				
				/*String m_client_code = req.getParameter("client_code").trim();
				String m_invoice_no = req.getParameter("serial_no").trim();
				
				rs = stmt.executeQuery(" SELECT A.INVOICE_SEQ_NO,B.BATCH_NO "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
						" WHERE B.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.BATCH_NO=B.BATCH_NO "+
						" AND A.INVOICE_NO='"+m_invoice_no+"' ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<DCODE>"+rs.getString(1)+"</DCODE>");
					out.println("<DCODE>"+rs.getString(2)+"</DCODE>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");*/      
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


