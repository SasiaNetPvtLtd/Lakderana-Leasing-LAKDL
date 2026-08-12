import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_MAS_sql_validations extends javax.servlet.http.HttpServlet {
	
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
				out.println("idle");
			}	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_PRODUCT_FEATURES")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();		
				String m_va3 = req.getParameter("data_va3").trim();		

				m_sql="";
				
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
				
				if(m_va2.equals("1")){
				m_sql="SELECT FA_FEATURE_CODE,FA_FEATURE_DESC,NVL(FA_FEATURE_COMMENTS,'-'),DEFAULT_VALUE,ACTIVE_STATUS,BASIS_CODE "+
				" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES "+
			  " WHERE FA_FEATURE_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT FA_FEATURE_CODE,FA_FEATURE_DESC,NVL(FA_FEATURE_COMMENTS,'-'),DEFAULT_VALUE,ACTIVE_STATUS,BASIS_CODE "+
				" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES "+
			  " WHERE UPPER(FA_FEATURE_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT FA_FEATURE_CODE,FA_FEATURE_DESC,NVL(FA_FEATURE_COMMENTS,'-'),DEFAULT_VALUE,ACTIVE_STATUS,BASIS_CODE "+
				" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES "+
			  " WHERE (FA_FEATURE_CODE = UPPER('"+m_va1+"') OR UPPER(FA_FEATURE_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}

				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_PRODUCT_CATEGORY")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();		
				String m_va3 = req.getParameter("data_va3").trim();		

				m_sql="";
				
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
				
				if(m_va2.equals("1")){
				m_sql="SELECT PRODUCT_CATEGORY,PRODUCT_CATEGORY_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY "+
			  " WHERE PRODUCT_CATEGORY = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT PRODUCT_CATEGORY,PRODUCT_CATEGORY_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY "+
			  " WHERE UPPER(PRODUCT_CATEGORY_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT PRODUCT_CATEGORY,PRODUCT_CATEGORY_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY "+
			  " WHERE (PRODUCT_CATEGORY = UPPER('"+m_va1+"') OR UPPER(PRODUCT_CATEGORY_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}

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
			
			
						//--------------------added by ashini on 26-02-2008--------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_REMARKS")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();		
				String m_va3 = req.getParameter("data_va3").trim();		

				m_sql="";
				
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
				
				if(m_va2.equals("1")){
				m_sql="SELECT REMARK_CODE,REMARK_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_REMARKS "+
			  " WHERE REMARK_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT REMARK_CODE,REMARK_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_REMARKS "+
			  " WHERE UPPER(REMARK_CODE) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT REMARK_CODE,REMARK_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_REMARKS "+
			  " WHERE (REMARK_CODE = UPPER('"+m_va1+"') OR UPPER(REMARK_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}

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
			
			//--------end modifications done by ashini-----------------------------------------------------
			
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_CLIENT_GROUP")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();		
				String m_va3 = req.getParameter("data_va3").trim();		
				
				m_sql="";
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
				
				if(m_va2.equals("1")){
				m_sql="SELECT GROUP_CODE,GROUP_DESC,GROUP_APPLI,DEFAULT_VALUE,ACTIVE_STATUS "+
				" FROM "+m_schema_name+".FA_CO_MAS_GROUPS "+
			  " WHERE GROUP_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT GROUP_CODE,GROUP_DESC,GROUP_APPLI,DEFAULT_VALUE,ACTIVE_STATUS "+
				" FROM "+m_schema_name+".FA_CO_MAS_GROUPS "+
			  " WHERE UPPER(GROUP_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT GROUP_CODE,GROUP_DESC,GROUP_APPLI,DEFAULT_VALUE,ACTIVE_STATUS "+
				" FROM "+m_schema_name+".FA_CO_MAS_GROUPS "+
			  " WHERE (GROUP_CODE = UPPER('"+m_va1+"') OR UPPER(GROUP_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				
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
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_EXPOSURE_CAT")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();		
				String m_va3 = req.getParameter("data_va3").trim();		
				
				m_sql="";
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
	
				if(m_va2.equals("1")){
				m_sql="SELECT EXPOSURE_CODE,EXPOSURE_DESC,DEFAULT_VALUE,ACTIVE_STATUS "+
				" FROM "+m_schema_name+".AF_CO_MAS_BUSI_EXPOSURE_CAT "+
			  " WHERE EXPOSURE_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT EXPOSURE_CODE,EXPOSURE_DESC,DEFAULT_VALUE,ACTIVE_STATUS "+
				" FROM "+m_schema_name+".AF_CO_MAS_BUSI_EXPOSURE_CAT "+
			  " WHERE UPPER(EXPOSURE_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT EXPOSURE_CODE,EXPOSURE_DESC,DEFAULT_VALUE,ACTIVE_STATUS "+
				" FROM "+m_schema_name+".AF_CO_MAS_BUSI_EXPOSURE_CAT "+
			  " WHERE (EXPOSURE_CODE = UPPER('"+m_va1+"') OR UPPER(EXPOSURE_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				
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
			
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_FEES")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();
				String m_va3 = req.getParameter("data_va3").trim();		
				
				m_sql="";		
				
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
				
				if(m_va2.equals("1")){
				m_sql="SELECT FEE_CODE,FEE_DESC,DEFAULT_VALUE,NVL(MINIUM_VALUE,0),FEE_TYPE,APP_CLIENT,APP_DEBTOR,APP_ACT_LEDGER,APP_INACT_LEDGER,CAL_BASIS,FEE_RATIO,ACTIVATION_POINT,TAX_APPLICABILITY "+
				" FROM "+m_schema_name+".FA_CO_MAS_FEES "+
			  " WHERE FEE_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT FEE_CODE,FEE_DESC,DEFAULT_VALUE,NVL(MINIUM_VALUE,0),FEE_TYPE,APP_CLIENT,APP_DEBTOR,APP_ACT_LEDGER,APP_INACT_LEDGER,CAL_BASIS,FEE_RATIO,ACTIVATION_POINT,TAX_APPLICABILITY "+
				" FROM "+m_schema_name+".FA_CO_MAS_FEES "+
			  " WHERE UPPER(FEE_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT FEE_CODE,FEE_DESC,DEFAULT_VALUE,NVL(MINIUM_VALUE,0),FEE_TYPE,APP_CLIENT,APP_DEBTOR,APP_ACT_LEDGER,APP_INACT_LEDGER,CAL_BASIS,FEE_RATIO,ACTIVATION_POINT,TAX_APPLICABILITY "+
				" FROM "+m_schema_name+".FA_CO_MAS_FEES "+
			  " WHERE (FEE_CODE = UPPER('"+m_va1+"') OR UPPER(FEE_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				
				rs= stmt.executeQuery (m_sql);
		  		
				out.print("<DATA>");
				while(rs.next()){
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
					out.print("</ITEM>");
				}
				out.print("</DATA>");
     }
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_PRODUCT")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();
				String m_va3 = req.getParameter("data_va3").trim();		
				
				m_sql="";		
				
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
	
				if(m_va2.equals("1")){
				m_sql="SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			  " WHERE FA_PRODUCT_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			  " WHERE UPPER(FA_PRODUCT_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			  " WHERE (FA_PRODUCT_CODE = UPPER('"+m_va1+"') OR UPPER(FA_PRODUCT_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
     }
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_FEE_PACK")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();
				String m_va3 = req.getParameter("data_va3").trim();		
				
				m_sql="";		
				
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
	
				if(m_va2.equals("1")){
				m_sql="SELECT FEE_PACK_CODE,FEE_PACK_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			  " WHERE FEE_PACK_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT FEE_PACK_CODE,FEE_PACK_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			  " WHERE UPPER(FEE_PACK_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT FEE_PACK_CODE,FEE_PACK_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			  " WHERE (FEE_PACK_CODE = UPPER('"+m_va1+"') OR UPPER(FEE_PACK_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
     }
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_COLLECTION_ROUTE")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();
				String m_va3 = req.getParameter("data_va3").trim();		
				
				m_sql="";		
				
				String m_active_stat="'%'";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y%'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N%'";
				}
	
				if(m_va2.equals("1")){
				
				m_sql="SELECT COLL_ROUTE_CODE,COLL_ROUTE_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES "+
			  " WHERE COLL_ROUTE_CODE = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else if(m_va2.equals("2")){
				m_sql="SELECT COLL_ROUTE_CODE,COLL_ROUTE_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES "+
			  " WHERE UPPER(COLL_ROUTE_DESC) = UPPER('"+m_va1+"') AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				else{
				m_sql="SELECT FEE_PACK_CODE,COLL_ROUTE_DESC,DEFAULT_VALUE "+
				" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES "+
			  " WHERE (COLL_ROUTE_CODE = UPPER('"+m_va1+"') OR UPPER(COLL_ROUTE_DESC) = UPPER('"+m_va1+"')) AND ACTIVE_STATUS LIKE "+m_active_stat;
				}
				
				rs= stmt.executeQuery (m_sql);
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
     }
		 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_COLLECTION_ROUTE_ASSIGN")){
			
				String m_va1 = req.getParameter("data_val").trim();		
				String m_va2 = req.getParameter("data_va2").trim();
				String m_va3 = req.getParameter("data_va3").trim();		
				
				m_sql="";		
				
				String m_active_stat="''";
			
				if(m_va3.equals("EDIT")|| m_va3.equals("DACT")){
				m_active_stat="'Y'";
				}
				else if(m_va3.equals("RACT")){
				m_active_stat="'N'";
				}
				
				if(m_va2.equals("1") && m_va3.equals("NEW") ){
				
				m_sql="	SELECT C.EMP_CODE ROUTE_OFFICER_CODE,C.TITLE || ' ' || C.FIRST_NAME || ' ' || C.LAST_NAME ROUTE_OFFICER_NAME,C.CONTACT_NO,B.ACTIVE_STATUS "+
 					 		"	FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES A,"+m_schema_name+".AF_CO_MAS_COLL_ROUT_ASSIGN B,LAKDL.CO_CO_MAS_EMPLOYEE C "+
 					 		"  WHERE A.COLL_ROUTE_CODE=B.COLL_ROUTE_CODE "+
 					 		"	AND   B.EMP_CODE = C.EMP_CODE AND B.EMP_CODE =UPPER('"+m_va1+"') ";
				}
				else {
				
				m_sql="	SELECT C.EMP_CODE ROUTE_OFFICER_CODE,C.TITLE || ' ' || C.FIRST_NAME || ' ' || C.LAST_NAME ROUTE_OFFICER_NAME,C.CONTACT_NO,B.ACTIVE_STATUS "+
 					 		"	FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES A,"+m_schema_name+".AF_CO_MAS_COLL_ROUT_ASSIGN B,LAKDL.CO_CO_MAS_EMPLOYEE C "+
 					 		"  WHERE A.COLL_ROUTE_CODE=B.COLL_ROUTE_CODE "+
 					 		"	AND   B.EMP_CODE = C.EMP_CODE AND B.EMP_CODE =UPPER('"+m_va1+"') AND  B.ACTIVE_STATUS = "+m_active_stat;
				
				}

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
		
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_DETAILS")){
		 		String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT  "+
				" CLIENT_CODE, CLIENT_TYPE, FACTORING_TYPE, FULL_NAME,  "+//1
				" NVL(BUSINESS_SUB_SECTOR,' '),NVL(EXPOSURE_CODE,' '),NVL(CLIENT_GROUP,' '),NVL(CRIB_STATUS,' '),  "+//2
				" NVL(CRIB_COMMENT,' '),NVL(REGISTERED_RESIDENTIAL_STATUS,' '),NVL(REGISTERED_ADDRESS1,' '),NVL(REGISTERED_ADDRESS2,' '),  "+//3
				" NVL(CITY_CODE,' '),NVL(AREA_CODE,' '),NVL(REGISTERED_REFERENCE,' '),NVL(REGISTERED_TEL_NO,' '),  "+//4
				" NVL(REGISTERED_FAX_NO,' '),NVL(REGISTERED_OFFICE_TEL_NO,' '),NVL(REGISTERED_EMAIL,' '),NVL(REGISTERED_MOBILE_NO,' '),  "+//5
				" NVL(KEY_DECISION_MAKER,' '),NVL(REGISTERED_CONTACT_PERSON,' '),NVL(DESIGNATION_PAYMENT,' '),NVL(F_STATUS,' '),  "+//6
				" NVL(F_ADDRESS1,' '),NVL(F_ADDRESS2,' '),NVL(F_CONTACT_PERSON,' '),NVL(F_TEL_NO,' '),  "+//7--
				" NVL(F_FAX_NO,' '),NVL(F_EMAIL,' '),NVL(CORRES_STATUS,' '),NVL(CORRES_ADDRESS1,' '),  "+//8--
				" NVL(CORRES_ADDRESS2,' '),NVL(CORRES_CITY_CODE,' '),NVL(CORRES_TEL_NO,' '),NVL(CORRES_FAX_NO,' '),  "+//9--
				" NVL(CORRES_EMAIL,' '),NVL(CLIENT_CATEGORY,' '),NVL(BUSINESS_CERTIFICATE_NO,' '),NVL(ISSUED_SHARE_CAPITAL,0),  "+//10--
				" NVL(TO_CHAR(DATE_OF_INCORPORATION,'DD'),' '),NVL(TO_CHAR(DATE_OF_INCORPORATION,'MM'),' '),NVL(TO_CHAR(DATE_OF_INCORPORATION,'YYYY'),' '),NVL(VAT_REG_NO,' '),  "+//11--
				" NVL(TO_CHAR(VAT_REG_DATE,'DD'),' '),NVL(TO_CHAR(VAT_REG_DATE,'MM'),' '),NVL(TO_CHAR(VAT_REG_DATE,'YYYY'),' '),NVL(WITH_HOLDING_TAX,' '),  "+//12--
				" NVL(NIC_NO,' '),NVL(PASSPORT_NO,' '),NVL(TITLE,'MR'),NVL(FIRST_NAME,' '),  "+//13--
				" NVL(SURNAME,' '),NVL(INITIALS,' '),NVL(OTHER_NAME,' '),NVL(TO_CHAR(DATE_OF_BIRTH,'DD'),' '),  "+//14--
				" NVL(TO_CHAR(DATE_OF_BIRTH,'MM'),' '),NVL(TO_CHAR(DATE_OF_BIRTH,'YYYY'),' '),NVL(GENDER,'M'),NVL(THIRD_PART_DEBTOR_DET,' ')  "+//15
  			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");out.print("<R10>"+rs.getString(10)+"</R10>");out.print("<R11>"+rs.getString(11)+"</R11>");out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");out.print("<R14>"+rs.getString(14)+"</R14>");out.print("<R15>"+rs.getString(15)+"</R15>");out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(17)+"</R17>");out.print("<R18>"+rs.getString(18)+"</R18>");out.print("<R19>"+rs.getString(19)+"</R19>");out.print("<R20>"+rs.getString(20)+"</R20>");
					out.print("<R21>"+rs.getString(21)+"</R21>");out.print("<R22>"+rs.getString(22)+"</R22>");out.print("<R23>"+rs.getString(23)+"</R23>");out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");out.print("<R26>"+rs.getString(26)+"</R26>");out.print("<R27>"+rs.getString(27)+"</R27>");out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("<R29>"+rs.getString(29)+"</R29>");out.print("<R30>"+rs.getString(30)+"</R30>");out.print("<R31>"+rs.getString(31)+"</R31>");out.print("<R32>"+rs.getString(32)+"</R32>");
					out.print("<R33>"+rs.getString(33)+"</R33>");out.print("<R34>"+rs.getString(34)+"</R34>");out.print("<R35>"+rs.getString(35)+"</R35>");out.print("<R36>"+rs.getString(36)+"</R36>");
					out.print("<R37>"+rs.getString(37)+"</R37>");out.print("<R38>"+rs.getString(38)+"</R38>");out.print("<R39>"+rs.getString(39)+"</R39>");out.print("<R40>"+nf.format(rs.getDouble(40))+"</R40>");
					out.print("<R41>"+rs.getString(41)+"</R41>");out.print("<R42>"+rs.getString(42)+"</R42>");out.print("<R43>"+rs.getString(43)+"</R43>");out.print("<R44>"+rs.getString(44)+"</R44>");
					out.print("<R45>"+rs.getString(45)+"</R45>");out.print("<R46>"+rs.getString(46)+"</R46>");out.print("<R47>"+rs.getString(47)+"</R47>");out.print("<R48>"+rs.getString(48)+"</R48>");
					out.print("<R49>"+rs.getString(49)+"</R49>");out.print("<R50>"+rs.getString(50)+"</R50>");out.print("<R51>"+rs.getString(51)+"</R51>");out.print("<R52>"+rs.getString(52)+"</R52>");
					out.print("<R53>"+rs.getString(53)+"</R53>");out.print("<R54>"+rs.getString(54)+"</R54>");out.print("<R55>"+rs.getString(55)+"</R55>");out.print("<R56>"+rs.getString(56)+"</R56>");
					out.print("<R57>"+rs.getString(57)+"</R57>");out.print("<R58>"+rs.getString(58)+"</R58>");out.print("<R59>"+rs.getString(59)+"</R59>");out.print("<R60>"+rs.getString(60)+"</R60>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
		 }
		 //Modified by Mahela on 15-12-2005	
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_DEBTOR_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT  "+
				" CLIENT_CODE,NVL(NO_OF_DEBTORS,0),NVL(BASIS_OF_CREDIT_GRANT,'Y'),NVL(AVG_CREDIT_PERIOD_EXT,0),  "+
				" NVL(AVG_CHEQUE_RETURN,0),NVL(AVG_DEPOSIT_ON_BANK,0),NVL(AVG_GOODS_CR_RETURNS,0),  "+
        " NVL(AVG_BILLING_CYCLE,0),NVL(AVG_SALES_PER_MONTH,0),NVL(AVG_SALES_PER_DEBTOR,0),  "+
				" NVL(AVG_INVOICE_SIZE_PER_TOP_DEBT,0),NVL(CREDIT_GRANTED_SECURE_DETAIL,' '),NVL(AVG_TOLARENCE_PERIOD,0)  "+
  			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT_DEBT_DET  "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");		
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
					out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");out.print("<R9>"+nf.format(rs.getDouble(9))+"</R9>");
					out.print("<R10>"+nf.format(rs.getDouble(10))+"</R10>");out.print("<R11>"+nf.format(rs.getDouble(11))+"</R11>");out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+nf.format(rs.getDouble(13))+"</R13>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
		 }
		 //Modified by Mahela on 15-12-2005		
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_DIRECTOR_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,NVL(DIR_NAME,' '),NVL(DIR_NIC_NO,' '),NVL(DIR_STAKE,0),  "+
       	" NVL(DIR_NO_OF_SHARES,0),NVL(DIR_VALUE,0),NVL(DIR_POSITION,' '),NVL(GUARANTOR_STATUS,'N'),NVL(ADDRESS,' '),NVL(COMMENTS,' ') "+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_COMP_DIR   "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");		
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		 
			//3rd Party Guarantor ---  Added by Mahela on 13-12-2006
			else if (m_chksql.trim().equals("m_EDIT_CLIENT_GUARANTOR_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,GUA_NAME,NVL(GUA_NIC_NO,' '),NVL(GUA_ADDRESS,' '),"+
       	" NVL(GUA_CONTACT_NO,' '),NVL(COMMENTS,' ')"+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_3RD_GUARAN "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
	
	    //Subsidiaries & Associated Companies  ---  Added by Mahela on 13-12-2006
			else if (m_chksql.trim().equals("m_EDIT_CLIENT_SUBSIDIA_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,NAME,NVL(STAKE,0),NVL(VALUE,0),"+
       	" NVL(TEL_NO,' '),NVL(OFFICER,' '),NVL(ACTIVITIES,' '),NVL(COMMENTS,' ')"+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_SUBSIDIA "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }

		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_BANK_ACC_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,BANK_CODE,BRANCH_CODE,ACCOUNT_NO,"+
       	" NVL(TO_CHAR(FROM_DATE,'DD-MM-YYYY'),' '),NVL(REFERENCE,' '),NVL(TEL_NO,' '),NVL(FAX_NO,' '),NVL(RELATIONSHIP,0),NVL(COMMENTS,' ') "+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_BANKS "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		
		  // Auditors  ---  Added by Mahela on 13-12-2006
			else if (m_chksql.trim().equals("m_EDIT_CLIENT_AUDITOR_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,AUDITOR_NAME,NVL(AUDITOR_ADD,' '),NVL(REFERENCE,' '),"+
       	" NVL(TEL_NO,' '),NVL(FAX_NO,' '),NVL(RELATIONSHIP,0),NVL(COMMENTS,' ')"+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_AUDITORS "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		 
		  // Credit Facilities  ---  Added by Mahela on 13-12-2006
			else if (m_chksql.trim().equals("m_EDIT_CLIENT_CREDIT_FACILITY_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,INSTITUTION,NVL(CONTACT_PERSON,' '),NVL(TYPE_OF_FACILITY,' '),"+
       	" NVL(SECURITY,' '),NVL(APPROVED_AMOUNT,0),NVL(MONTHLY_RENTAL,0),NVL(MONTHS,0),NVL(PAYABLE,0),"+
				" NVL(BALANCE_AMOUNT,0),NVL(COMMENTS,' ')"+	
  			" FROM "+m_schema_name+".FA_CO_MAS_CREDIT_FACILITIES "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
					out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+nf.format(rs.getDouble(10))+"</R10>");out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }	
			
		  // Proposed Security  ---  Added by Mahela on 13-12-2006
			else if (m_chksql.trim().equals("m_EDIT_CLIENT_PROP_SECURITY_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,OWNERSHIP,NVL(TYPE_OF_SECURITY,' '),NVL(VALUE,0),NVL(COMMENTS,' ')"+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_PROP_SECU "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }	
			
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_SUPPLIER_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,CUSTOMER_NAME,NVL(ADDRESS,' '),NVL(TO_CHAR(RELATIONSHIP),' '),"+
				" NVL(CONTACT_PERSON,' '),NVL(TEL_NO,' '),NVL(TYPE,'SUPPLIER'),NVL(COMMENTS,' ')"+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_CUSTOMERS "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_PRODUCT_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,NVL(NATURE_OF_PRODUCT,'G'),NVL(PRODUCT_DESC,' '), "+
       	" NVL(ESTABLISH_IN_MKT,'N'),NVL(ESTABLISH_IN_MKT_COMMENT,' '),NVL(PROT_GROWTH_IN_MKT,'N'),"+
				" NVL(PROT_GROWTH_IN_MKT_COMMENT,' '),NVL(PRESENT_MKT_SHARE,0),NVL(PRODUCT_SEASONAL,'N'),PRODUCT_NAME,NVL(PRODUCT_SUB_SECTOR,' '), "+
				" PRODUCT_CATEGORY,NVL(DETAIL_PRODUCT_MARKET_SHARE,' '),NVL(COMMENTS,' ')  "+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_PROD "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");out.print("<R11>"+rs.getString(11)+"</R11>");out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");out.print("<R14>"+rs.getString(14)+"</R14>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		  //Modified by Mahela on 15-12-2005	
			else if (m_chksql.trim().equals("m_EDIT_CLIENT_TOP_DEBTORS_DETAILS")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,NVL(DEBTOR_NAME,' '),NVL(DEBTOR_ADDRESS,' '),NVL(DEBTOR_TELE_NO,' '), "+
				" NVL(DEBTOR_FAX_NO,' '),NVL(DEBTOR_CONT_PERSON,' '),NVL(TO_CHAR(DEBTOR_AVG_SALES),0),NVL(TO_CHAR(DEBTOR_INVOICE_AMT),0), "+
				" NVL(DEBTOR_COMMENTS,' ') "+
				" FROM  "+m_schema_name+".FA_CO_MAS_CLIENT_TOP_DEBT  "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				   
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		/////-------------------loading from leasing
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_DETAILS_LEASING")){
		 		String m_client_code = req.getParameter("client_code").trim();	
 
				rs= stmt.executeQuery (
				" SELECT  "+
				" CLIENT_CODE, CLIENT_TYPE,'C',NVL(FULL_NAME,' '),  "+//1
				" NVL(BUSINESS_SUB_SECTOR,' '),' ',' ',' ',  "+//2
				" ' ',NVL(RESIDENTIAL_STATUS,' '),NVL(ADDRESS1,' '),NVL(ADDRESS2,' '),  "+//3
				" NVL(CITY_CODE,' '),' ',NVL(REFERENCE,' '),NVL(TEL_NO,' '),  "+//4
				" NVL(FAX_NO,' '),NVL(OFFICE_TEL_NO,' '),NVL(EMAIL,' '),NVL(MOBILE_NO,' '),  "+//5
				" NVL(KEY_DECISION_MAKER,' '),NVL(CONTACT_FOR_PAYMENT,' '),NVL(DESIGNATION_PAYMENT,' '),NVL(FACTORY_STATUS,' '),  "+//6
				" NVL(FACTORY_ADDRESS1,' '),NVL(FACTORY_ADDRESS2,' '),NVL(F_CONTACT_PERSON,' '),NVL(F_TEL_NO,' '),  "+//7--
				" NVL(F_FAX_NO,' '),NVL(F_EMAIL,' '),NVL(CORRESPONDENCE_STATUS,' '),NVL(REGISTERED_ADDRESS1,' '),  "+//8----------------
				" NVL(REGISTERED_ADDRESS2,' '),NVL(REGISTERED_CITY_CODE,' '),NVL(TEL_NO_GEN,' '),NVL(FAX_NO_GEN,' '),  "+//9--
				" NVL(EMAIL_GEN,' '),NVL(CLIENT_CATEGORY,' '),NVL(BUSINESS_CERTIFICATE_NO,' '),NVL(ISSUED_SHARE_CAPITAL,0),  "+//10--
				" NVL(TO_CHAR(DATE_OF_INCORPORATION,'DD'),' '),NVL(TO_CHAR(DATE_OF_INCORPORATION,'MM'),' '),NVL(TO_CHAR(DATE_OF_INCORPORATION,'YYYY'),' '),NVL(VAT_REG_NO,' '),  "+//11--
				" NVL(TO_CHAR(VAT_REG_DATE,'DD'),' '),NVL(TO_CHAR(VAT_REG_DATE,'MM'),' '),NVL(TO_CHAR(VAT_REG_DATE,'YYYY'),' '),'N',  "+//12--
				" NVL(NIC_NO,' '),NVL(PASSPORT_NO,' '),NVL(TITLE,'MR'),NVL(FIRST_NAME,' '),  "+//13--
				" NVL(SURNAME,' '),NVL(INITIALS,' '),NVL(OTHER_NAME,' '),NVL(TO_CHAR(DATE_OF_BIRTH,'DD'),' '),  "+//14--
				" NVL(TO_CHAR(DATE_OF_BIRTH,'MM'),' '),NVL(TO_CHAR(DATE_OF_BIRTH,'YYYY'),' '),NVL(GENDER,'M')  "+//15
  			"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");out.print("<R10>"+rs.getString(10)+"</R10>");out.print("<R11>"+rs.getString(11)+"</R11>");out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");out.print("<R14>"+rs.getString(14)+"</R14>");out.print("<R15>"+rs.getString(15)+"</R15>");out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(17)+"</R17>");out.print("<R18>"+rs.getString(18)+"</R18>");out.print("<R19>"+rs.getString(19)+"</R19>");out.print("<R20>"+rs.getString(20)+"</R20>");
					out.print("<R21>"+rs.getString(21)+"</R21>");out.print("<R22>"+rs.getString(22)+"</R22>");out.print("<R23>"+rs.getString(23)+"</R23>");out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");out.print("<R26>"+rs.getString(26)+"</R26>");out.print("<R27>"+rs.getString(27)+"</R27>");out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("<R29>"+rs.getString(29)+"</R29>");out.print("<R30>"+rs.getString(30)+"</R30>");out.print("<R31>"+rs.getString(31)+"</R31>");out.print("<R32>"+rs.getString(32)+"</R32>");
					out.print("<R33>"+rs.getString(33)+"</R33>");out.print("<R34>"+rs.getString(34)+"</R34>");out.print("<R35>"+rs.getString(35)+"</R35>");out.print("<R36>"+rs.getString(36)+"</R36>");
					out.print("<R37>"+rs.getString(37)+"</R37>");out.print("<R38>"+rs.getString(38)+"</R38>");out.print("<R39>"+rs.getString(39)+"</R39>");out.print("<R40>"+rs.getString(40)+"</R40>");
					out.print("<R41>"+rs.getString(41)+"</R41>");out.print("<R42>"+rs.getString(42)+"</R42>");out.print("<R43>"+rs.getString(43)+"</R43>");out.print("<R44>"+rs.getString(44)+"</R44>");
					out.print("<R45>"+rs.getString(45)+"</R45>");out.print("<R46>"+rs.getString(46)+"</R46>");out.print("<R47>"+rs.getString(47)+"</R47>");out.print("<R48>"+rs.getString(48)+"</R48>");
					out.print("<R49>"+rs.getString(49)+"</R49>");out.print("<R50>"+rs.getString(50)+"</R50>");out.print("<R51>"+rs.getString(51)+"</R51>");out.print("<R52>"+rs.getString(52)+"</R52>");
					out.print("<R53>"+rs.getString(53)+"</R53>");out.print("<R54>"+rs.getString(54)+"</R54>");out.print("<R55>"+rs.getString(55)+"</R55>");out.print("<R56>"+rs.getString(56)+"</R56>");
					out.print("<R57>"+rs.getString(57)+"</R57>");out.print("<R58>"+rs.getString(58)+"</R58>");out.print("<R59>"+rs.getString(59)+"</R59>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
		 }
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_DIRECTOR_DETAILS_LEASING")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,NVL(NAME,' '),NVL(NIC_NO,' '),NVL(STAKE,0),  "+
       	" NVL(NO_OF_SHARES,0),NVL(VALUE,0),NVL(POSITION,' ')  "+
  			" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS   "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");		
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_BANK_ACC_DETAILS_LEASING")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,BANK_CODE,BRANCH_CODE,ACCOUNT_NO,"+
       	" NVL(TO_CHAR(FROM_DATE,'DD-MM-YYYY'),' '),NVL(REFERENCE,' '),NVL(TEL_NO,' '),NVL(FAX_NO,' '),NVL(RELATIONSHIP,0) "+
  			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");out.print("<R8>"+rs.getString(8)+"</R8>");out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		 }
		 else if (m_chksql.trim().equals("m_EDIT_CLIENT_SUPPLIER_DETAILS_LEASING")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,CUSTOMER_NAME,NVL(ADDRESS,' '),NVL(TO_CHAR(RELATIONSHIP),' '),"+
				" NVL(CONTACT_PERSON,' '),NVL(TEL_NO,' '),NVL(TYPE,'SUPPLIER')"+
  			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_CUSTOMERS "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");out.print("<R2>"+rs.getString(2)+"</R2>");out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");out.print("<R5>"+rs.getString(5)+"</R5>");out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		}
		else if (m_chksql.trim().equals("m_CHECK_CLIENT_CODE")){
				String m_client_code = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,FULL_NAME,"+
				" DECODE(FACTORING_TYPE,'C','CLIENT','D','DEBTOR','CLIENT AND DEBTOR'),ACTIVE_STATUS "+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
				
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
		else if (m_chksql.trim().equals("m_CHECK_CLIENT_NAME")){
				String m_client_name = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT CLIENT_CODE,FULL_NAME,"+
				" DECODE(FACTORING_TYPE,'C','CLIENT','D','DEBTOR','CLIENT AND DEBTOR'),ACTIVE_STATUS "+
  			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				" WHERE UPPER(FULL_NAME)=UPPER('"+m_client_name+"')");
				
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
		else if (m_chksql.trim().equals("m_CHECK_CLIENT_INQUERY")){
				String m_client_name = req.getParameter("client_code").trim();	
					
				rs= stmt.executeQuery (
				" SELECT INQUIRY_CODE "+
  			" FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO "+
				" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_name+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_factoring_default_val")){

				rs= stmt.executeQuery (
				" SELECT CREDIT_LIMIT,CREDIT_PERIOD,TOLERANCE_CREDIT_PERIOD,RESERVE_MARGIN,INT_RATE "+
  			" FROM "+m_schema_name+".FA_MK_PRO_QUOTA_DEFAULT ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("get_id_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_client = req.getParameter("client_name").trim();
				
				rs= stmt.executeQuery (" SELECT  CLIENT_NAME,ID_NO"+
 				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
        " WHERE CLIENT_NAME NOT IN UPPER('"+m_client+"') AND UPPER(ID_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		}
		else if (m_chksql.trim().equals("get_id_no2")){
				
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery (" SELECT  CLIENT_NAME,ID_NO"+
 				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
        " WHERE  UPPER(ID_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		}
		
		//Added by Mahela on 05-01-2007
		else if (m_chksql.trim().equals("m_CHECK_CLIENT_NIC")){
				
				String m_nic = req.getParameter("nic_no").trim();

				rs= stmt.executeQuery (" SELECT  CLIENT_CODE,NIC_NO"+
 				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
        " WHERE UPPER(NIC_NO)=UPPER('"+m_nic+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		}	
		//Added by Mahela on 05-01-2007
		else if (m_chksql.trim().equals("m_CHECK_CLIENT_NIC_EDIT")){
				
				String m_nic = req.getParameter("nic_no").trim();
				String m_client = req.getParameter("client_code").trim();
				
				rs= stmt.executeQuery (" SELECT  CLIENT_CODE,NIC_NO"+
 				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
        " WHERE CLIENT_CODE NOT IN UPPER('"+m_client+"') AND UPPER(NIC_NO)=UPPER('"+m_nic+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		}
		//Added by Mahela on 05-01-2007
		else if (m_chksql.trim().equals("m_CHECK_CLIENT_BUSINESS_CERTIFICATE_NO")){
				
				String m_business_no = req.getParameter("business_no").trim();

				rs= stmt.executeQuery (" SELECT  CLIENT_CODE,BUSINESS_CERTIFICATE_NO"+
 				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
        " WHERE UPPER(BUSINESS_CERTIFICATE_NO)=UPPER('"+m_business_no+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		}	
		//Added by Mahela on 05-01-2007
		else if (m_chksql.trim().equals("m_CHECK_CLIENT_BUSINESS_CERTIFICATE_NO_EDIT")){
				
				String m_business_no = req.getParameter("business_no").trim();
				String m_client = req.getParameter("client_code").trim();
				
				rs= stmt.executeQuery (" SELECT  CLIENT_CODE,BUSINESS_CERTIFICATE_NO"+
 				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
        " WHERE CLIENT_CODE NOT IN UPPER('"+m_client+"') AND UPPER(BUSINESS_CERTIFICATE_NO)=UPPER('"+m_business_no+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
		}
		////----------------------------------------
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



