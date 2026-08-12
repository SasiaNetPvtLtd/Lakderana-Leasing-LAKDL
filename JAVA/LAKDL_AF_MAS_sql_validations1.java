import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_sql_validations1 extends HttpServlet {
	
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
			
			
			
			
			//--------------------- ID  		:Employee ---------------------------------//
			//---------------------Purpose 	:Fill Division for Selected Designation Code------------------------------------------------
			//---------------------Name     :Yohan Gunarathna----------------------------------------------------
			//---------------------Date     :22-09-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_employee_fill_division")){
				
				
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet = statement.executeQuery(" SELECT "+
					" DESIGNATION_CODE, "+
					" DIVISION "+
					" FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION "+
					" WHERE DESIGNATION_CODE = '"+m_val+"'  AND ACTIVE_STATUS='Y' ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					
					out.print("<ITEM>");
					
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//---------------------ID  			:1.15 Business Sector Creation Process---------------------------------//
			//---------------------Purpose:To validate the Business Sector Description-----------------------------------
			//---------------------Name    :Nuwan De Silva---------------------------------------------------
			//---------------------Date     :22-09-2006--------------------------------------------------
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_business_sector_desc")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT SECTOR_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID :1.48 Sub Model Creation Process-----------------------------//	
			//--------------------Sub Model Code Validation -------------------------------------------------//
			//--------------------Mahela Wickramasekara------------------------------------------------------//
			//--------------------25-07-2006---------------------------------------------------------//
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_sub_model1")){
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT SUB_CODE,MODEL_CODE,DESCRIPTION,ENGINE_CAPACITY,OPTION_TYPE,"+
					"COUNTRY_CODE,YEAR_OF_MANUFACTURE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_SUB_MODLE "+
					" WHERE UPPER(SUB_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_score_sub_category1")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  SCORE_SUB_CODE,SCORE_CODE,DESCRIPTION,DISPALY_POSITION  FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_sub_model_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				//&& ACTIVE_STATUS='"+m_status+"'
				
				resultSet= statement.executeQuery ("SELECT SUB_CODE"+
					" FROM LAKDL.AF_CO_MAS_SUB_MODLE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID :1.46 Valuer Creation Process-----------------------------//	
			//--------------------Valuer Code Validation -------------------------------------------------//
			//--------------------Mahela Wickramasekara------------------------------------------------------//
			//--------------------26-09-2006---------------------------------------------------------//
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_valuer1")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				//String m_status = req.getParameter("ac_status");
				
				//--(2007-02-28)--------------------------------------------------------------------------------------------------------------------------------------------
				
				resultSet= statement.executeQuery ("SELECT  VALUER_CODE,FIRST_NAME,LAST_NAME,ADDRESS,ADDRESS2,CITY_CODE,TEL_NO,MOBILE_NO,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_VALUERS "+
					" WHERE UPPER(FIRST_NAME)=UPPER('"+m_val+"') AND UPPER(LAST_NAME)=UPPER('"+m_val2+"')");
				
				//resultSet= statement.executeQuery ("SELECT  VALUER_CODE,FIRST_NAME,LAST_NAME,ADDRESS,ADDRESS2,CITY_CODE,TEL_NO,MOBILE_NO,DEFAULT_VALUE,VALUER_AMOUNT  FROM LAKDL.AF_CO_MAS_VALUERS "+
				//" WHERE UPPER(FIRST_NAME)=UPPER('"+m_val+"') AND UPPER(LAST_NAME)=UPPER('"+m_val2+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>");
					
					//	out.print("<R10>"+resultSet.getDouble(10)+"</R10>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_valuer2")){
				String m_val = req.getParameter("data_val").trim();
				//--(2007-02-28)--------------------------------------------------------------------------------------------------------------------------------------------
				resultSet= statement.executeQuery ("SELECT  VALUER_CODE,FIRST_NAME,LAST_NAME,ADDRESS,ADDRESS2,CITY_CODE,TEL_NO,MOBILE_NO,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_VALUERS "+
					" WHERE UPPER(VALUER_CODE)=UPPER('"+m_val+"') ");
				//resultSet= statement.executeQuery ("SELECT  VALUER_CODE,FIRST_NAME,LAST_NAME,ADDRESS,ADDRESS2,CITY_CODE,TEL_NO,MOBILE_NO,DEFAULT_VALUE,VALUER_AMOUNT  FROM LAKDL.AF_CO_MAS_VALUERS "+
				//" WHERE UPPER(VALUER_CODE)=UPPER('"+m_val+"') ");
				
				//----------------------------------------------------------------------------------------------------------------------------------------------------------
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>");
					
					//out.print("<R10>"+resultSet.getDouble(10)+"</R10>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_documents_required_desc")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION,DOC_APP_TYPE  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_document_applicable")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
					" WHERE UPPER(DOC_APP_TYPE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2).replace('&','*')+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_val3 = req.getParameter("data_val3").trim();
				
				//AND DOC_APP_TYPE=UPPER('"+m_val+"')
				
				//!--MODIFIED (2006-12-22)
				/*
                resultSet= statement.executeQuery ("SELECT a.CODE,b.DESCRIPTION,a.ACTIVE_STATUS,a.FROM_SCREEN_NO,a.TO_SCREEN_NO,a.DIVISION_CODE "+
                "FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE A, LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED B "+
                " where  ENTITY_TYPE=UPPER('"+m_val+"') and a.code=b.code "+
                " union all "+
                " SELECT CODE,DESCRIPTION,ACTIVE_STATUS,0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE "+
                " FROM LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED "+
                " WHERE CODE NOT IN (SELECT CODE FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE) AND DOC_APP_TYPE=UPPER('"+m_val2+"') ");
                */
				
				/*
                resultSet= statement.executeQuery ("SELECT A.CODE,B.DESCRIPTION,ENTITY_TYPE,A.FROM_SCREEN_NO,A.TO_SCREEN_NO,A.DIVISION_CODE,PRODUCT_CODE,'' "+
                "FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A, "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
                " WHERE  ENTITY_TYPE=UPPER('"+m_val+"') AND A.CODE=B.CODE AND DOC_APP_TYPE=UPPER('"+m_val2+"') "+
                " UNION ALL"+
                " SELECT CODE,DESCRIPTION,'',0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE,'','' "+
                " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
                " WHERE CODE NOT IN (SELECT CODE FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE WHERE ENTITY_TYPE=UPPER('"+m_val+"')) "+
                " AND ACTIVE_STATUS='Y' AND DOC_APP_TYPE=UPPER('"+m_val2+"') ");
            */
				
				resultSet= statement.executeQuery ("SELECT A.CODE,B.DESCRIPTION,ENTITY_TYPE,A.FROM_SCREEN_NO,A.TO_SCREEN_NO,A.DIVISION_CODE,a.PRODUCT_CODE,'' "+
					"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A, "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
					" WHERE  ENTITY_TYPE=UPPER('"+m_val+"') AND A.CODE=B.CODE AND DOC_APP_TYPE=UPPER('"+m_val2+"') "+
					" UPPER(A.PRODUCT_CODE) =UPPER('"+m_val3+"') "+
					" UNION ALL"+
					" SELECT CODE,DESCRIPTION,'',0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE,'','' "+
					" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
					" WHERE CODE NOT IN (SELECT CODE FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE WHERE ENTITY_TYPE=UPPER('"+m_val+"')) "+
					" AND ACTIVE_STATUS='Y' AND DOC_APP_TYPE=UPPER('"+m_val2+"') ");
				
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2).replace('&','*')+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill_new")){
				String m_type = req.getParameter("type").trim();
				String m_client = req.getParameter("client").trim();
				String m_product = req.getParameter("product").trim();
				if(m_type.equals("CLIENT")){
					//	out.println("#1");
					
					resultSet= statement.executeQuery ("SELECT distinct A.CODE CODE,B.DESCRIPTION,ENTITY_TYPE,A.FROM_SCREEN_NO,A.TO_SCREEN_NO,A.DIVISION_CODE,a.PRODUCT_CODE,'',A.ACTIVE_STATUS "+
						"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A, "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
						" WHERE  ENTITY_TYPE=UPPER('"+m_client+"') AND A.CODE=B.CODE AND DOC_APP_TYPE=UPPER('"+m_type+"') "+
						" AND UPPER(A.PRODUCT_CODE) =UPPER('"+m_product+"') "+
						" UNION "+
						" SELECT DISTINCT CODE,DESCRIPTION,'',0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE,'','',ACTIVE_STATUS "+
						" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
						" WHERE CODE NOT IN (SELECT CODE FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE WHERE ENTITY_TYPE=UPPER('"+m_client+"')  AND UPPER(PRODUCT_CODE) =UPPER('"+m_product+"')) "+
						" AND ACTIVE_STATUS='Y' AND DOC_APP_TYPE=UPPER('"+m_type+"') ORDER BY CODE ASC");
					
				}
				if(m_type.equals("ASSET")){
					//			out.println("#2");
					
					
					resultSet= statement.executeQuery ("SELECT distinct a.CODE CODE,INITCAP(b.DESCRIPTION),'"+m_type+"',a.FROM_SCREEN_NO,a.TO_SCREEN_NO,a.DIVISION_CODE,a.ITEM_CAT_CODE,a.PRODUCT_CODE, A.ACTIVE_STATUS "+
						"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A, "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
						" where  upper(a.ITEM_CAT_CODE)=UPPER('"+m_client+"') and upper(a.code)=upper(b.code) and upper(DOC_APP_TYPE)=UPPER('"+m_type+"') "+
						" and upper(a.PRODUCT_CODE)=upper('"+m_product+"') "+
						" union "+
						" SELECT distinct CODE,INITCAP(DESCRIPTION),'"+m_type+"',0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE,'','', ACTIVE_STATUS "+
						" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
						" WHERE upper(CODE) NOT IN (SELECT upper(CODE) FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE WHERE ITEM_CAT_CODE=UPPER('"+m_client+"')  and upper(PRODUCT_CODE)=upper('"+m_product+"') ) AND DOC_APP_TYPE=UPPER('"+m_type+"') AND ACTIVE_STATUS='Y' ORDER BY CODE ASC ");
				}
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2).replace('&','*')+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>"); // added by udara 06-08-2015
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill2")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				//AND DOC_APP_TYPE=UPPER('"+m_val+"')
				/*
                resultSet= statement.executeQuery ("SELECT a.CODE,INITCAP(b.DESCRIPTION),'"+m_val2+"',a.FROM_SCREEN_NO,a.TO_SCREEN_NO,a.DIVISION_CODE "+
                "FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE A, LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED B "+
                " where  ITEM_CAT_CODE=UPPER('"+m_val+"') and a.code=b.code "+
                " union all "+
                " SELECT CODE,INITCAP(DESCRIPTION),ACTIVE_STATUS,0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE "+
                " FROM LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED "+
                " WHERE CODE NOT IN (SELECT CODE FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE) AND DOC_APP_TYPE=UPPER('"+m_val2+"') ");

                
            */
				resultSet= statement.executeQuery ("SELECT a.CODE,INITCAP(b.DESCRIPTION),'"+m_val2+"',a.FROM_SCREEN_NO,a.TO_SCREEN_NO,a.DIVISION_CODE,ITEM_CAT_CODE,PRODUCT_CODE "+
					"FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE A, LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED B "+
					" where  ITEM_CAT_CODE=UPPER('"+m_val+"') and a.code=b.code and DOC_APP_TYPE=UPPER('"+m_val2+"')"+
					" union all "+
					" SELECT CODE,INITCAP(DESCRIPTION),'"+m_val2+"',0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE,'','' "+
					" FROM LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED "+
					" WHERE CODE NOT IN (SELECT CODE FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE WHERE ITEM_CAT_CODE=UPPER('"+m_val+"')) AND DOC_APP_TYPE=UPPER('"+m_val2+"') AND ACTIVE_STATUS='Y' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2).replace('&','*')+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_document_applicable_fill_new_1")){
				String m_type = req.getParameter("type").trim();
				String m_asset = req.getParameter("asset").trim();
				String m_product = req.getParameter("product").trim();
				
				
				
				resultSet= statement.executeQuery ("SELECT distinct a.CODE,INITCAP(b.DESCRIPTION),'"+m_type+"',a.FROM_SCREEN_NO,a.TO_SCREEN_NO,a.DIVISION_CODE,a.ITEM_CAT_CODE,a.PRODUCT_CODE "+
					"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A, "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
					" where  upper(a.ITEM_CAT_CODE)=UPPER('"+m_asset+"') and upper(a.code)=upper(b.code) and upper(DOC_APP_TYPE)=UPPER('"+m_type+"') "+
					" and upper(a.PRODUCT_CODE)=upper('"+m_product+"') "+
					" union "+
					" SELECT distinct CODE,INITCAP(DESCRIPTION),'"+m_type+"',0  FROM_SCREEN_NO,0 TO_SCREEN_NO,'' DIVISION_CODE,'','' "+
					" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
					" WHERE upper(CODE) NOT IN (SELECT upper(CODE) FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE WHERE ITEM_CAT_CODE=UPPER('"+m_asset+"')  and upper(PRODUCT_CODE)=upper('"+m_product+"') ) AND DOC_APP_TYPE=UPPER('"+m_type+"') AND ACTIVE_STATUS='Y' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2).replace('&','*')+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_broker_details")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT  BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,"+
					" LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO,MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,"+
					" COMMISSION_AMOUNT,NVL(COMMENTS,'-'),BROKER_STATUS  FROM "+m_schema_name+".AF_CO_MAS_BROKER "+
					" WHERE UPPER(BROKER_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='Y' AND BROKER_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>");
					out.print("<R10>"+resultSet.getString(10)+"</R10>");
					out.print("<R11>"+resultSet.getString(11)+"</R11>");
					out.print("<R12>"+resultSet.getString(12)+"</R12>");
					out.print("<R13>"+resultSet.getString(13)+"</R13>");
					out.print("<R14>"+resultSet.getString(14)+"</R14>");
					out.print("<R15>"+resultSet.getString(15)+"</R15>");
					out.print("<R16>"+resultSet.getString(16)+"</R16>");
					out.print("<R17>"+resultSet.getString(17)+"</R17>");
					out.print("<R18>"+resultSet.getString(18)+"</R18>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
			
			
			
			
			
			
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_lawyer1")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				resultSet= statement.executeQuery ("SELECT LAWYER_CODE,FIRST_NAME,LAST_NAME,NAME_WITH_INITIALS,ADDRESS1,NVL(ADDRESS2,'N/A'),CITY_CODE,TEL_NO,OFFICE_TEL_NO,MOBILE_NO,FAX_NO,OFFICE_FAX_NO,FEE_PER_CASE,MONTHLY_FEE FROM LAKDL.AF_CO_MAS_LAWYER"+
					" WHERE UPPER(FIRST_NAME)=UPPER('"+m_val+"') AND UPPER(LAST_NAME)=UPPER('"+m_val2+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>");
					out.print("<R10>"+resultSet.getString(10)+"</R10>");
					out.print("<R11>"+resultSet.getString(11)+"</R11>");
					out.print("<R12>"+resultSet.getString(12)+"</R12>");
					out.print("<R13>"+resultSet.getString(13)+"</R13>");
					out.print("<R14>"+resultSet.getString(14)+"</R14>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_score_sub_category2")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  SCORE_SUB_CODE,SCORE_CODE,DESCRIPTION,DISPALY_POSITION  FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE UPPER(SCORE_SUB_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_lawyer2")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT LAWYER_CODE,FIRST_NAME,LAST_NAME,NAME_WITH_INITIALS,ADDRESS1,NVL(ADDRESS2,'N/A'),CITY_CODE,TEL_NO,OFFICE_TEL_NO,MOBILE_NO,FAX_NO,OFFICE_FAX_NO,FEE_PER_CASE,MONTHLY_FEE FROM "+m_schema_name+".AF_CO_MAS_LAWYER"+
					" WHERE UPPER(LAWYER_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>");
					out.print("<R10>"+resultSet.getString(10)+"</R10>");
					out.print("<R11>"+resultSet.getString(11)+"</R11>");
					out.print("<R12>"+resultSet.getString(12)+"</R12>");
					out.print("<R13>"+resultSet.getString(13)+"</R13>");
					out.print("<R14>"+resultSet.getString(14)+"</R14>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_licencee_settlement")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				/* Comment by Prabash on 24-10-2014
                resultSet= statement.executeQuery ("SELECT BRANCH_CODE,ACC_NO,ACC_SYS_REFNO,CURR_CODE,ACC_CODE,ACC_DESC FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT"+
                    " WHERE UPPER(ACC_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ");
                */
				resultSet= statement.executeQuery ("SELECT A.BRANCH_CODE,A.ACC_NO,A.ACC_SYS_REFNO,A.CURR_CODE,A.ACC_CODE,A.ACC_DESC,B.PREFIX"+
					"FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT A,"+
					" "+m_schema_name+".AF_CO_LIC_SETTL_ACC_PREFIX_SEQ B "+
					" WHERE UPPER(ACC_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') "+
					" AND  A.BRANCH_CODE=B.BRANCH_CODE(+) "+
					" AND  A.ACC_NO=B.ACC_NO(+)"+
					" ");
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");	
					out.print("<R7>"+resultSet.getString(7)+"</R7>");		
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//----Added by Prabash on 24-10-2014---------
			else if (m_chksql.equals("check_pfxcode")){
				String m_prefix = req.getParameter("prefix");
				
				resultSet= statement.executeQuery ("SELECT PREFIX FROM "+m_schema_name+".AF_CO_LIC_SETTL_ACC_PREFIX_SEQ"+
					" WHERE UPPER(PREFIX)=UPPER('"+m_prefix+"')  ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//-----------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_code")){
				
				//String m_val = req.getParameter("entity_type").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT LPAD("+m_schema_name+".AF_SEQ_CLIENT_NO.NEXTVAL,10,'0') FROM DUAL ");	
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_app_client_doc")){
				
				String m_val = req.getParameter("entity_type").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT ROWNUM,A.CODE,INITCAP(B.DESCRIPTION) "+
					"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
					"  WHERE UPPER(A.CODE)=UPPER(B.CODE) AND UPPER(A.ENTITY_TYPE)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3).replace('&','*')+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_app_client_doc_fill")){
				
				String m_val = req.getParameter("client_code").trim();
				String m_val2 = req.getParameter("entity_type").trim();
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				//String m_status = req.getParameter("ac_status");
				if(m_help_status.equals("N"))
				{
					resultSet= statement.executeQuery (" SELECT P.NO,P.CODE,P.DESCRIPTION,P.STATUS,P.REMARKS "+
						" FROM (SELECT ROWNUM NO,CODE CODE,DESCRIPTION DESCRIPTION,STATUS STATUS,REMARKS  REMARKS "+
						" FROM(SELECT A.CODE CODE ,INITCAP(B.DESCRIPTION) DESCRIPTION,'' STATUS,'' REMARKS  "+
						" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
						" WHERE UPPER(A.CODE)=UPPER(B.CODE) AND UPPER(A.ENTITY_TYPE)=UPPER('"+m_val2+"') and (a.CODE) "+
						" not in (select DOCUMENT_CODE from "+m_schema_name+".AF_CO_MAS_APP_CLIENT_DOCS where UPPER(CLIENT_CODE)=UPPER('"+m_val+"') ) "+
						" union all "+
						" SELECT A.DOCUMENT_CODE CODE,INITCAP(B.DESCRIPTION) DESCRIPTION,A.STATUS STATUS,A.REMARKS REMARKS "+
						" FROM "+m_schema_name+".AF_CO_MAS_APP_CLIENT_DOCS A,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND UPPER(A.DOCUMENT_CODE)=UPPER(B.CODE) "+
						" ))P ");
					
					//" WHERE P.NO>=1 AND P.NO<=2000 ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3).replace('&','*')+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else if(m_help_status.equals("Y"))
				{
					resultSet= statement.executeQuery (" SELECT P.NO,P.CODE,P.DESCRIPTION,P.STATUS,P.REMARKS "+
						" FROM (SELECT ROWNUM NO,CODE CODE,DESCRIPTION DESCRIPTION,STATUS STATUS,REMARKS  REMARKS "+
						" FROM(SELECT A.CODE CODE ,INITCAP(B.DESCRIPTION) DESCRIPTION,'' STATUS,'' REMARKS  "+
						" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
						" WHERE UPPER(A.CODE)=UPPER(B.CODE) AND UPPER(A.ENTITY_TYPE)=UPPER('"+m_val2+"') and (a.CODE) "+
						" not in (select DOCUMENT_CODE from "+m_schema_name+".AF_CO_MAS_APP_CLIENT_DOCS_TM where UPPER(CLIENT_CODE)=UPPER('"+m_val+"') ) "+
						" union all "+
						" SELECT A.DOCUMENT_CODE CODE,INITCAP(B.DESCRIPTION) DESCRIPTION,A.STATUS STATUS,A.REMARKS REMARKS "+
						" FROM "+m_schema_name+".AF_CO_MAS_APP_CLIENT_DOCS_TM A,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED B "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND UPPER(A.DOCUMENT_CODE)=UPPER(B.CODE) "+
						" ))P ");
					
					//" WHERE P.NO>=1 AND P.NO<=2000 ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3).replace('&','*')+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				
			} 	
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_licencee_settlement1")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				/*   //Comment by Prabash on 24-10-2014---*
					resultSet= statement.executeQuery ("SELECT BRANCH_CODE,ACC_NO,ACC_SYS_REFNO,CURR_CODE,ACC_CODE,ACC_DESC FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT"+
						" WHERE UPPER(ACC_NO)=UPPER('"+m_val+"') ");
				*/ //------------------------------------**
				resultSet= statement.executeQuery ("SELECT A.BRANCH_CODE,A.ACC_NO,A.ACC_SYS_REFNO,A.CURR_CODE,A.ACC_CODE,A.ACC_DESC,B.PREFIX"+
					"FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT A,"+
					" "+m_schema_name+".AF_CO_LIC_SETTL_ACC_PREFIX_SEQ B "+
					" WHERE UPPER(ACC_NO)=UPPER('"+m_val+"')"+
					" AND  A.BRANCH_CODE=B.BRANCH_CODE(+) "+
					" AND  A.ACC_NO=B.ACC_NO(+)"+
					" ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");	
					out.print("<R7>"+resultSet.getString(7)+"</R7>");  //Added by Prabash on 24-10-2014 
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_branch")){
				
				String m_val = req.getParameter("data_val");
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT BRANCH_CODE,BRANCH_NAME,BANK_CODE,ADDRESS1,NVL(ADDRESS2,'N/A'),NVL(CITY_CODE,'N/A'),NVL(TEL_NO,'N/A'),NVL(FAX_NO,'N/A'),DAYS_TO_REALISE,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
					" WHERE UPPER(BRANCH_CODE)=UPPER('"+m_val+"')  ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>");
					out.print("<R10>"+resultSet.getString(10)+"</R10>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------  ID  			  :1.52 Client Creation Process   ---------------------------------//
			//---------------------Purpose 	  :To validate the Client Code-----------------------------------
			//---------------------Name       :M.M.Wickramasekara-------------------------------------------
			//---------------------Date       :18-09-2006--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_bus_cert_no")){
				
				String m_val = req.getParameter("bus_no");
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CLIENT_CODE,ACTIVE_STATUS FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE UPPER(BUSINESS_CERTIFICATE_NO)=UPPER('"+m_val+"')  ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//-------------------  ID  			  :1.52 Client Creation Process   ---------------------------------//
			//---------------------Purpose 	  :To validate the Client Code-----------------------------------
			//---------------------Name       :nuwan de silva-------------------------------------------
			//---------------------Date       :07-08-07--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_LAKDL_client_creation_client_code_validate")){
				
				String m_val =    req.getParameter("client_code");
				String m_status = req.getParameter("ac_status");
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					resultSet= statement.executeQuery ("SELECT CLIENT_CODE,CLIENT_TYPE FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ");
				}
				else if(m_help_status.equals("Y"))
				{
					resultSet= statement.executeQuery ("SELECT CLIENT_CODE,CLIENT_TYPE FROM "+m_schema_name+".AF_CO_MAS_CLIENT_TM "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ");
				}
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_ind")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					
					resultSet= statement.executeQuery (" SELECT DISTINCT "+
						" A.CLIENT_CODE, A.CLIENT_TYPE,A.TITLE,A.FIRST_NAME,A.SURNAME,A.INITIALS,A.FULL_NAME,A.OTHER_NAME,A.RESIDENTIAL_STATUS,A.TEL_NO,"+
						" A.ADDRESS1,A.ADDRESS2,A.OFFICE_TEL_NO,A.FAX_NO,A.MOBILE_NO,A.EMAIL,A.DURATION_AT_YEARS,A.DURATION_AT_MONTHS,NVL(A.EMP_NAME,'-'),"+
						" A.EMP_ADDRESS1,A.EMP_ADDRESS2,A.EMP_REFERENCE,A.EMP_RDESIGNATION,A.EMP_TEL_NO,A.EMP_FAX_NO,B.NAME,B.ADDRESS1 REL_ADD1,B.ADDRESS2 REL_ADD2,"+
						" B.RELATIONSHIP,B.HOME_TEL_NO,B.OFFICE_TEL_NO REL_OFF_TEL,B.MOBILE_NO REL_MOB,A.NIC_NO,to_char(A.DATE_OF_BIRTH,'DD-MM-YYYY'),A.PASSPORT_NO,"+
						" A.NATIONALITY,A.MARITAL_STATUS,A.GENDER,A.BA_NATURE_OF_BUSINESS,A.BA_PROFESSION,A.BA_QUALIFICATIONS,A.BA_DESIGNATION,A.NO_OF_CHILDREN,"+
						" A.DEPENDENTS,A.CITY_CODE,A.VAT_REG_NO,A.DRIVING_LICENSE_NO,NVL(A.POSTALCODE,'-') POSTALCODE,A.GRIB_NO,nvl(a.BUSINESS_SUB_SECTOR,'-'),nvl(a.SECTOR_CODE,'-'),"+m_schema_name+".AF_CO_GET_CITY_NAME(a.CITY_CODE),"+m_schema_name+".AF_CO_GET_POSTAL_DESC(A.POSTALCODE), "+
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
						" ,"+m_schema_name+".AF_CO_GET_ACTIVE_CONTR_STATUS(A.CLIENT_CODE) ACTIVE_STATUS, "+
						" NVL(A.PRE_NIC_NO,'-') "+ //Added by Kanchana on 2016-08-19
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE B "+
						" WHERE A.CLIENT_CODE = UPPER('"+m_val+"') AND A.ACTIVE_STATUS=('"+m_status+"') AND A.CLIENT_CODE=B.CLIENT_CODE(+) AND CLIENT_TYPE='I' "+
						" ORDER BY CLIENT_CODE ASC ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						out.print("<R8>"+resultSet.getString(8)+"</R8>");
						out.print("<R9>"+resultSet.getString(9)+"</R9>");
						out.print("<R10>"+resultSet.getString(10)+"</R10>");
						out.print("<R11>"+resultSet.getString(11)+"</R11>");
						out.print("<R12>"+resultSet.getString(12)+"</R12>");
						out.print("<R13>"+resultSet.getString(13)+"</R13>");
						out.print("<R14>"+resultSet.getString(14)+"</R14>");
						out.print("<R15>"+resultSet.getString(15)+"</R15>");
						out.print("<R16>"+resultSet.getString(16)+"</R16>");
						out.print("<R17>"+resultSet.getString(17)+"</R17>");
						out.print("<R18>"+resultSet.getString(18)+"</R18>");
						// out.print("<R19>"+resultSet.getString(19)+"</R19>"); // commented by udara on 05-08-2011
						out.print("<R19> "+resultSet.getString(19).replace('&','$')+" </R19>"); // added by udara on 05-08-2011
						out.print("<R20>"+resultSet.getString(20)+"</R20>");
						out.print("<R21>"+resultSet.getString(21)+"</R21>");
						out.print("<R22>"+resultSet.getString(22)+"</R22>");
						out.print("<R23>"+resultSet.getString(23)+"</R23>");
						out.print("<R24>"+resultSet.getString(24)+"</R24>");
						out.print("<R25>"+resultSet.getString(25)+"</R25>");
						out.print("<R26>"+resultSet.getString(26)+"</R26>");
						out.print("<R27>"+resultSet.getString(27)+"</R27>");
						out.print("<R28>"+resultSet.getString(28)+"</R28>");
						out.print("<R29>"+resultSet.getString(29)+"</R29>");
						out.print("<R30>"+resultSet.getString(30)+"</R30>");
						out.print("<R31>"+resultSet.getString(31)+"</R31>");
						out.print("<R32>"+resultSet.getString(32)+"</R32>");
						out.print("<R33>"+resultSet.getString(33)+"</R33>");
						out.print("<R34>"+resultSet.getString(34)+"</R34>");
						out.print("<R35>"+resultSet.getString(35)+"</R35>");
						out.print("<R36>"+resultSet.getString(36)+"</R36>");
						out.print("<R37>"+resultSet.getString(37)+"</R37>");
						out.print("<R38>"+resultSet.getString(38)+"</R38>");
						out.print("<R39>"+resultSet.getString(39)+"</R39>");
						out.print("<R40>"+resultSet.getString(40)+"</R40>");
						out.print("<R41>"+resultSet.getString(41)+"</R41>");
						out.print("<R42>"+resultSet.getString(42)+"</R42>");
						out.print("<R43>"+resultSet.getString(43)+"</R43>");
						out.print("<R44>"+resultSet.getString(44)+"</R44>");
						out.print("<R45>"+resultSet.getString(45)+"</R45>");
						out.print("<R46>"+resultSet.getString(46)+"</R46>");
						out.print("<R47>"+resultSet.getString(47)+"</R47>");
						out.print("<R48>"+resultSet.getString(48)+"</R48>");
						out.print("<R49>"+resultSet.getString(49)+"</R49>");
						
						out.print("<R50>"+resultSet.getString(50)+"</R50>");
						out.print("<R51>"+resultSet.getString(51)+"</R51>");
						out.print("<R52>"+resultSet.getString(52)+"</R52>");
						out.print("<R53>"+resultSet.getString(53)+"</R53>");	
						out.print("<R54>"+resultSet.getString(54)+"</R54>");	
						out.print("<R55>"+resultSet.getString(55)+"</R55>");				
						out.print("<R56>"+resultSet.getString(56)+"</R56>");	
						out.print("<R57>"+resultSet.getString(57)+"</R57>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				
				if(m_help_status.equals("Y"))
				{
					
					resultSet= statement.executeQuery (" SELECT DISTINCT "+
						" A.CLIENT_CODE, A.CLIENT_TYPE,A.TITLE,A.FIRST_NAME,A.SURNAME,A.INITIALS,A.FULL_NAME,A.OTHER_NAME,A.RESIDENTIAL_STATUS,A.TEL_NO,"+
						" A.ADDRESS1,A.ADDRESS2,A.OFFICE_TEL_NO,A.FAX_NO,A.MOBILE_NO,A.EMAIL,A.DURATION_AT_YEARS,A.DURATION_AT_MONTHS,NVL(A.EMP_NAME,'-'),"+
						" A.EMP_ADDRESS1,A.EMP_ADDRESS2,A.EMP_REFERENCE,A.EMP_RDESIGNATION,A.EMP_TEL_NO,A.EMP_FAX_NO,B.NAME,B.ADDRESS1 REL_ADD1,B.ADDRESS2 REL_ADD2,"+
						" B.RELATIONSHIP,B.HOME_TEL_NO,B.OFFICE_TEL_NO REL_OFF_TEL,B.MOBILE_NO REL_MOB,A.NIC_NO,to_char(A.DATE_OF_BIRTH,'DD-MM-YYYY'),A.PASSPORT_NO,"+
						" A.NATIONALITY,A.MARITAL_STATUS,A.GENDER,A.BA_NATURE_OF_BUSINESS,A.BA_PROFESSION,A.BA_QUALIFICATIONS,A.BA_DESIGNATION,A.NO_OF_CHILDREN,"+
						" A.DEPENDENTS,A.CITY_CODE,A.VAT_REG_NO,A.DRIVING_LICENSE_NO,NVL(A.POSTALCODE,'-') POSTALCODE,A.GRIB_NO,nvl(a.BUSINESS_SUB_SECTOR,'-'),nvl(a.SECTOR_CODE,'-'),"+m_schema_name+".AF_CO_GET_CITY_NAME(a.CITY_CODE),"+m_schema_name+".AF_CO_GET_POSTAL_DESC(A.POSTALCODE), "+
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
						" ,"+m_schema_name+".AF_CO_GET_ACTIVE_CONTR_STATUS(A.CLIENT_CODE) ACTIVE_STATUS, "+
						" NVL(A.PRE_NIC_NO,'-') "+ //Added by Kanchana on 2016-08-19
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_TM A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE_TM B "+
						" WHERE A.CLIENT_CODE = UPPER('"+m_val+"') AND A.CLIENT_CODE=B.CLIENT_CODE(+) AND CLIENT_TYPE='I' "+
						" ORDER BY CLIENT_CODE ASC ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						out.print("<R8>"+resultSet.getString(8)+"</R8>");
						out.print("<R9>"+resultSet.getString(9)+"</R9>");
						out.print("<R10>"+resultSet.getString(10)+"</R10>");
						out.print("<R11>"+resultSet.getString(11)+"</R11>");
						out.print("<R12>"+resultSet.getString(12)+"</R12>");
						out.print("<R13>"+resultSet.getString(13)+"</R13>");
						out.print("<R14>"+resultSet.getString(14)+"</R14>");
						out.print("<R15>"+resultSet.getString(15)+"</R15>");
						out.print("<R16>"+resultSet.getString(16)+"</R16>");
						out.print("<R17>"+resultSet.getString(17)+"</R17>");
						out.print("<R18>"+resultSet.getString(18)+"</R18>");
						//out.print("<R19>"+resultSet.getString(19)+"</R19>"); // commented by udara on 05-08-2011
						out.print("<R19> "+resultSet.getString(19).replace('&','$')+" </R19>"); // added by udara on 05-08-2011
						out.print("<R20>"+resultSet.getString(20)+"</R20>");
						out.print("<R21>"+resultSet.getString(21)+"</R21>");
						out.print("<R22>"+resultSet.getString(22)+"</R22>");
						out.print("<R23>"+resultSet.getString(23)+"</R23>");
						out.print("<R24>"+resultSet.getString(24)+"</R24>");
						out.print("<R25>"+resultSet.getString(25)+"</R25>");
						out.print("<R26>"+resultSet.getString(26)+"</R26>");
						out.print("<R27>"+resultSet.getString(27)+"</R27>");
						out.print("<R28>"+resultSet.getString(28)+"</R28>");
						out.print("<R29>"+resultSet.getString(29)+"</R29>");
						out.print("<R30>"+resultSet.getString(30)+"</R30>");
						out.print("<R31>"+resultSet.getString(31)+"</R31>");
						out.print("<R32>"+resultSet.getString(32)+"</R32>");
						out.print("<R33>"+resultSet.getString(33)+"</R33>");
						out.print("<R34>"+resultSet.getString(34)+"</R34>");
						out.print("<R35>"+resultSet.getString(35)+"</R35>");
						out.print("<R36>"+resultSet.getString(36)+"</R36>");
						out.print("<R37>"+resultSet.getString(37)+"</R37>");
						out.print("<R38>"+resultSet.getString(38)+"</R38>");
						out.print("<R39>"+resultSet.getString(39)+"</R39>");
						out.print("<R40>"+resultSet.getString(40)+"</R40>");
						out.print("<R41>"+resultSet.getString(41)+"</R41>");
						out.print("<R42>"+resultSet.getString(42)+"</R42>");
						out.print("<R43>"+resultSet.getString(43)+"</R43>");
						out.print("<R44>"+resultSet.getString(44)+"</R44>");
						out.print("<R45>"+resultSet.getString(45)+"</R45>");
						out.print("<R46>"+resultSet.getString(46)+"</R46>");
						out.print("<R47>"+resultSet.getString(47)+"</R47>");
						out.print("<R48>"+resultSet.getString(48)+"</R48>");
						out.print("<R49>"+resultSet.getString(49)+"</R49>");
						out.print("<R50>"+resultSet.getString(50)+"</R50>");
						out.print("<R51>"+resultSet.getString(51)+"</R51>");
						out.print("<R52>"+resultSet.getString(52)+"</R52>");
						out.print("<R53>"+resultSet.getString(53)+"</R53>");			
						out.print("<R54>"+resultSet.getString(54)+"</R54>");	
						out.print("<R55>"+resultSet.getString(55)+"</R55>");
						out.print("<R56>"+resultSet.getString(56)+"</R56>");
						out.print("<R57>"+resultSet.getString(57)+"</R57>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				} 
				
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_nic")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery (" SELECT  CLIENT_CODE,NIC_NO,ACTIVE_STATUS"+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE UPPER(NIC_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_nic_edit")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_client = req.getParameter("client_code").trim();
				
				// commented by udara on 30-10-2013
				/*
                resultSet= statement.executeQuery (" SELECT  CLIENT_CODE,NIC_NO,ACTIVE_STATUS"+
                    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                    " WHERE CLIENT_CODE NOT IN UPPER('"+m_client+"') AND UPPER(NIC_NO)=UPPER('"+m_val+"') ");
				*/
				
				// added by udara 30-10-2013
				resultSet= statement.executeQuery (" SELECT  CLIENT_CODE,NIC_NO,ACTIVE_STATUS"+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE UPPER(NIC_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_property")){
				
				String m_client = req.getParameter("client_code").trim();
				String m_status = req.getParameter("ac_status");
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					
					resultSet= statement.executeQuery ("SELECT PROPERTY_TYPE,LOCATION,MAKE_CODE,MODEL_CODE,COMPANY,VALUE,REG_DEED_NO"+
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_PROPERTY "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client+"') AND ACTIVE_STATUS='"+m_status+"' ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else if(m_help_status.equals("Y"))
				{
					
					resultSet= statement.executeQuery ("SELECT PROPERTY_TYPE,LOCATION,MAKE_CODE,MODEL_CODE,COMPANY,VALUE,REG_DEED_NO"+
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_PROPERTY_TM "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client+"') ");							
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
				}
				
			}
			
			
			
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_cor")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					
					
					resultSet= statement.executeQuery (" SELECT "+
						" CLIENT_CODE,FULL_NAME,REGISTERED_STATUS,KEY_DECISION_MAKER,DESIGNATION_PAYMENT,DIRECT_TEL_NO,"+
						" CONTACT_FOR_PAYMENT,CORRESPONDENCE_STATUS,DESIGNATION,TEL_NO_GEN,FAX_NO_GEN,EMAIL_GEN,FACTORY_STATUS,"+
						" F_CONTACT_PERSON,F_TEL_NO,F_FAX_NO,F_EMAIL,CLIENT_CATEGORY,ISSUED_SHARE_CAPITAL,BUSINESS_CERTIFICATE_NO,"+
						" to_char(DATE_OF_INCORPORATION,'DD-MM-YYYY'),VAT_REG_NO,to_char(VAT_REG_DATE,'DD-MM-YYYY'),REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,ADDRESS1,ADDRESS2,FACTORY_ADDRESS1,FACTORY_ADDRESS2,CITY_CODE,NVL(POSTALCODE,'-') POSTALCODE,GRIB_NO,nvl(BUSINESS_SUB_SECTOR,'-'),nvl(SECTOR_CODE,'-'),"+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),"+m_schema_name+".AF_CO_GET_POSTAL_DESC(POSTALCODE),  "+
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
						" ,"+m_schema_name+".AF_CO_GET_ACTIVE_CONTR_STATUS(CLIENT_CODE) ACTIVE_STATUS "+
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
						" WHERE CLIENT_CODE = UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') AND CLIENT_TYPE='C' "+
						" ORDER BY CLIENT_CODE ASC ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						out.print("<R8>"+resultSet.getString(8)+"</R8>");
						out.print("<R9>"+resultSet.getString(9)+"</R9>");
						out.print("<R10>"+resultSet.getString(10)+"</R10>");
						out.print("<R11>"+resultSet.getString(11)+"</R11>");
						out.print("<R12>"+resultSet.getString(12)+"</R12>");
						out.print("<R13>"+resultSet.getString(13)+"</R13>");
						out.print("<R14>"+resultSet.getString(14)+"</R14>");
						out.print("<R15>"+resultSet.getString(15)+"</R15>");
						out.print("<R16>"+resultSet.getString(16)+"</R16>");
						out.print("<R17>"+resultSet.getString(17)+"</R17>");
						out.print("<R18>"+resultSet.getString(18)+"</R18>");
						//out.print("<R18>"+nf.format(resultSet.getDouble(18))+"</R18>");
						out.print("<R19>"+nf.format(resultSet.getDouble(19))+"</R19>");//Modified Nuwan de Silva
						out.print("<R20>"+resultSet.getString(20)+"</R20>");
						out.print("<R21>"+resultSet.getString(21)+"</R21>");
						out.print("<R22>"+resultSet.getString(22)+"</R22>");
						out.print("<R23>"+resultSet.getString(23)+"</R23>");
						out.print("<R24>"+resultSet.getString(24)+"</R24>");
						out.print("<R25>"+resultSet.getString(25)+"</R25>");
						out.print("<R26>"+resultSet.getString(26)+"</R26>");
						out.print("<R27>"+resultSet.getString(27)+"</R27>");
						out.print("<R28>"+resultSet.getString(28)+"</R28>");
						out.print("<R29>"+resultSet.getString(29)+"</R29>");
						out.print("<R30>"+resultSet.getString(30)+"</R30>");
						out.print("<R31>"+resultSet.getString(31)+"</R31>");
						out.print("<R32>"+resultSet.getString(32)+"</R32>");
						out.print("<R33>"+resultSet.getString(33)+"</R33>");
						out.print("<R34>"+resultSet.getString(34)+"</R34>");
						out.print("<R35>"+resultSet.getString(35)+"</R35>");		
						out.print("<R36>"+resultSet.getString(36)+"</R36>");
						out.print("<R37>"+resultSet.getString(37)+"</R37>");
						out.print("<R38>"+resultSet.getString(38)+"</R38>");
						out.print("<R39>"+resultSet.getString(39)+"</R39>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else if(m_help_status.equals("Y"))
				{
					resultSet= statement.executeQuery (" SELECT "+
						" CLIENT_CODE,FULL_NAME,REGISTERED_STATUS,KEY_DECISION_MAKER,DESIGNATION_PAYMENT,DIRECT_TEL_NO,"+
						" CONTACT_FOR_PAYMENT,CORRESPONDENCE_STATUS,DESIGNATION,TEL_NO_GEN,FAX_NO_GEN,EMAIL_GEN,FACTORY_STATUS,"+
						" F_CONTACT_PERSON,F_TEL_NO,F_FAX_NO,F_EMAIL,CLIENT_CATEGORY,ISSUED_SHARE_CAPITAL,BUSINESS_CERTIFICATE_NO,"+
						" to_char(DATE_OF_INCORPORATION,'DD-MM-YYYY'),VAT_REG_NO,to_char(VAT_REG_DATE,'DD-MM-YYYY'),REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,ADDRESS1,ADDRESS2,FACTORY_ADDRESS1,FACTORY_ADDRESS2,CITY_CODE,NVL(POSTALCODE,'-') POSTALCODE,GRIB_NO,nvl(BUSINESS_SUB_SECTOR,'-'),nvl(SECTOR_CODE,'-'),"+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),"+m_schema_name+".AF_CO_GET_POSTAL_DESC(POSTALCODE)  "+
						","+m_schema_name+".AF_CO_GET_POSTAL_DESC(POSTALCODE), "+
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
						" ,"+m_schema_name+".AF_CO_GET_ACTIVE_CONTR_STATUS(CLIENT_CODE) ACTIVE_STATUS "+
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_TM "+
						" WHERE CLIENT_CODE = UPPER('"+m_val+"') AND CLIENT_TYPE='C' "+
						" ORDER BY CLIENT_CODE ASC ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						out.print("<R8>"+resultSet.getString(8)+"</R8>");
						out.print("<R9>"+resultSet.getString(9)+"</R9>");
						out.print("<R10>"+resultSet.getString(10)+"</R10>");
						out.print("<R11>"+resultSet.getString(11)+"</R11>");
						out.print("<R12>"+resultSet.getString(12)+"</R12>");
						out.print("<R13>"+resultSet.getString(13)+"</R13>");
						out.print("<R14>"+resultSet.getString(14)+"</R14>");
						out.print("<R15>"+resultSet.getString(15)+"</R15>");
						out.print("<R16>"+resultSet.getString(16)+"</R16>");
						out.print("<R17>"+resultSet.getString(17)+"</R17>");
						out.print("<R18>"+resultSet.getString(18)+"</R18>");
						//out.print("<R18>"+nf.format(resultSet.getDouble(18))+"</R18>");
						out.print("<R19>"+nf.format(resultSet.getDouble(19))+"</R19>");//Modified Nuwan de Silva
						out.print("<R20>"+resultSet.getString(20)+"</R20>");
						out.print("<R21>"+resultSet.getString(21)+"</R21>");
						out.print("<R22>"+resultSet.getString(22)+"</R22>");
						out.print("<R23>"+resultSet.getString(23)+"</R23>");
						out.print("<R24>"+resultSet.getString(24)+"</R24>");
						out.print("<R25>"+resultSet.getString(25)+"</R25>");
						out.print("<R26>"+resultSet.getString(26)+"</R26>");
						out.print("<R27>"+resultSet.getString(27)+"</R27>");
						out.print("<R28>"+resultSet.getString(28)+"</R28>");
						out.print("<R29>"+resultSet.getString(29)+"</R29>");
						out.print("<R30>"+resultSet.getString(30)+"</R30>");
						out.print("<R31>"+resultSet.getString(31)+"</R31>");
						out.print("<R32>"+resultSet.getString(32)+"</R32>");
						out.print("<R33>"+resultSet.getString(33)+"</R33>");
						out.print("<R34>"+resultSet.getString(34)+"</R34>");
						out.print("<R35>"+resultSet.getString(35)+"</R35>");
						out.print("<R36>"+resultSet.getString(36)+"</R36>");
						out.print("<R37>"+resultSet.getString(37)+"</R37>");
						out.print("<R38>"+resultSet.getString(38)+"</R38>");
						out.print("<R39>"+resultSet.getString(39)+"</R39>");
						
						out.print("</ITEM>");
					}
					out.print("</DATA>");			
					
				}	
				
			}
			
			
			
			
			//---------------------Purpose: Client Creation -----------------------------------
			//---------------------Name    :Mahela Wickramasekara--------------------------------------------------
			//---------------------Date     :22-09-2006--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_credit_cor")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					
					resultSet= statement.executeQuery (" SELECT  INSTITUTION,CONTACT_PERSON,TYPE_OF_FACILITY,EQUIPMENT,APPROVED_AMOUNT,"+
						" MONTHLY_RENTAL,MONTHS,BALANCE_AMOUNT "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APP_CREDIT_FACILITIE "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+nf1.format(resultSet.getDouble(5))+"</R5>");
						out.print("<R6>"+nf1.format(resultSet.getDouble(6))+"</R6>");
						out.print("<R7>"+nf1.format(resultSet.getDouble(7))+"</R7>");
						out.print("<R8>"+nf.format(resultSet.getDouble(8))+"</R8>"); //Modified Nuwan De Silva 16-05-07
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else if(m_help_status.equals("Y"))
				{
					resultSet= statement.executeQuery (" SELECT  INSTITUTION,CONTACT_PERSON,TYPE_OF_FACILITY,EQUIPMENT,APPROVED_AMOUNT,"+
						" MONTHLY_RENTAL,MONTHS,BALANCE_AMOUNT "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APP_CREDIT_FACILI_TM "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+nf1.format(resultSet.getDouble(5))+"</R5>");
						out.print("<R6>"+nf1.format(resultSet.getDouble(6))+"</R6>");
						out.print("<R7>"+nf1.format(resultSet.getDouble(7))+"</R7>");
						out.print("<R8>"+nf.format(resultSet.getDouble(8))+"</R8>");//Modified Nuwan De Silva 16-05-07
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				
			}
			
			
			//---------------------Purpose: Client Creation -----------------------------------
			//---------------------Name    :Nuwan De Silva-------------------------------------------------
			//---------------------Date     :11-03-2007--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_aud")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					
					resultSet= statement.executeQuery (" SELECT "+
						" NAME,ADDRESS1,RELATIONSHIP,REFERENCE,TEL_NO,FAX_NO,ADDRESS2 "+           
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_AUDITOR "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
					
					
					
					
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else if(m_help_status.equals("Y"))
				{
					resultSet= statement.executeQuery (" SELECT "+
						" NAME,ADDRESS1,RELATIONSHIP,REFERENCE,TEL_NO,FAX_NO,ADDRESS2 "+           
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_AUDITOR_TM "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("<R7>"+resultSet.getString(7)+"</R7>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				
			}
			
			
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_cus")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					
					resultSet= statement.executeQuery (" SELECT CUSTOMER_NAME,TYPE,ADDRESS,RELATIONSHIP,CONTACT_PERSON,TEL_NO"+
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_CUSTOMERS "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else if(m_help_status.equals("Y"))
				{
					
					resultSet= statement.executeQuery (" SELECT CUSTOMER_NAME,TYPE,ADDRESS,RELATIONSHIP,CONTACT_PERSON,TEL_NO"+
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_CUSTOM_TM "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+resultSet.getString(6)+"</R6>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");		
					
				}
				
			}
			
			
			//--------------------- ID  		:Employee ---------------------------------//
			//---------------------Purpose 	:Fill City Code for Selected Designation Code------------------------------------------------
			//---------------------Name     :Yohan Gunarathna----------------------------------------------------
			//---------------------Date     :22-09-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_employee_fill_city")){
				
				
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet = statement.executeQuery(" SELECT "+
					" AREA_CODE, "+
					" CITY_CODE "+
					" FROM "+m_schema_name+".AF_CO_MAS_AREA "+
					" WHERE AREA_CODE = '"+m_val+"'  AND ACTIVE_STATUS='Y' ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					
					out.print("<ITEM>");
					
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}			
			
			
			//--------------------- ID  		:Employee ---------------------------------//
			//---------------------Purpose 	:Check for duplicate Names------------------------------------------------
			//---------------------Name     :Yohan Gunarathna----------------------------------------------------
			//---------------------Date     :22-09-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_employee_chk_name")){
				
				
				
				String m_fname = req.getParameter("fname").trim();
				String m_lname = req.getParameter("lname").trim();
				
				resultSet = statement.executeQuery(" SELECT "+
					" EMP_CODE, "+
					" FIRST_NAME, "+
					" LAST_NAME "+
					" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
					" WHERE UPPER(FIRST_NAME) = UPPER('"+m_fname+"') AND UPPER(LAST_NAME) = UPPER('"+m_lname+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					
					out.print("<ITEM>");
					
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}			
			
			/*------------------ID         :1.22 Item Category Creation Process-------------------------------
            --------------------Purpose    :Item Category Code validation------------------------------------------------
          ------------------- Added By   :delanji (Updated Nuwan)------------------------------------------------------
          --------------------Date       :20-07-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_item_category1")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT ITEM_CAT_CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
					" WHERE UPPER(ITEM_CAT_CODE)=UPPER('"+m_val+"')");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_item_category_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT ITEM_CAT_CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			//--------------------- ID  		:RMV Agents ---------------------------------//
			//---------------------Purpose 	:Check for duplicate Names------------------------------------------------
			//---------------------Name     :Yohan Gunarathna----------------------------------------------------
			//---------------------Date     :22-09-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_rmv_agents_chk_name")){
				
				
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet = statement.executeQuery(" SELECT "+
					" RMV_AGENT_CODE, "+
					" NAME "+
					" FROM "+m_schema_name+".AF_CO_MAS_RMV_AGENTS "+
					" WHERE UPPER(NAME) = UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					
					out.print("<ITEM>");
					
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}				
			
			/*------------------ID         :1.25 Engine Capacity Creation Process-----------------------------------------
            --------------------Purpose    :Engine Capacity Code Validation ----------------------------------------------
          -------------------   Added By :Nuwan De Silva------------------------------------------------------
          --------------------  Date     :22-09-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_engine_capacity_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT CAPACITY_CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_ENGINE_CAPACITY "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_engine_capacity_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT CAPACITY_CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_ENGINE_CAPACITY "+
					" WHERE UPPER(CAPACITY_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			/*------------------ID         :1.26 Fields Creation Process-----------------------------------------
        --------------------Purpose    :Fields Code Validation ----------------------------------------------
        -------------------   Added By :Nuwan De Silva------------------------------------------------------
        --------------------  Date     :22-09-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_fields_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT  FILED_CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_FILEDS "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_fields_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT  FILED_CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_FILEDS "+
					" WHERE UPPER(FILED_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			//---------------------  ID  			:1.19 Condition of Asset Creation Process ---------------------------------//
			//---------------------Purpose 	:To Prevent Entering Duplicate Description-----------------------------------
			//---------------------Name     :Yohan Gunarathna--------------------------------------------------
			//---------------------Date     :22-09-2006--------------------------------------------------
			
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_condition_of_asset_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET"+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			/*------------------ID         : 1.10 Designation Creation Process-----------------------------------------
        --------------------Purpose    :Designation Code Validation ----------------------------------------------
        -------------------   Added By :NUWAN DE SILVA------------------------------------------------------
        --------------------  Date     :23-09-2006---------------------------------------------------------*/
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_designation_code")){
				
				String m_val = req.getParameter("data_val");
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT DESIGNATION_CODE,DESIGNATION_NAME,DESIGNATION_LEVEL,DIVISION FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION "+
					" WHERE UPPER(DESIGNATION_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_designation_desc")){
				
				String m_val = req.getParameter("data_val");
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT DESIGNATION_CODE,DESIGNATION_NAME,DESIGNATION_LEVEL,DIVISION FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION "+
					" WHERE UPPER(DESIGNATION_NAME)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//-------------------- ID : 1.30 Garage Creation Process -----------------------------//	
			//--------------------Prevent from Entering Duplicate Garage Name -------------------------------------------------//
			//--------------------Yohan Gunarathna------------------------------------------------------//
			//--------------------23-09-2006---------------------------------------------------------//
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_garage_name")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT  GARAGE_CODE,NAME  FROM "+m_schema_name+".AF_CO_MAS_GARAGE "+
					" WHERE UPPER(NAME)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			/*------------------ID         :1.28 Fuel Type Creation Process-----------------------------------------
            --------------------Purpose    :Fuel Type Code Validation ----------------------------------------------
          -------------------   Added By :Nuwan De Silva------------------------------------------------------
          --------------------  Date     :20-07-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_fuel_type_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE "+
					" WHERE UPPER(CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_fuel_type_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//---------------------ID  			:1.16 Business Sub Sector Creation Process---------------------------------//
			//---------------------Purpose 	:To validate the Sub Business Sector Code-----------------------------------
			//---------------------Name     :Nuwan De Silva---------------------------------------------------
			//---------------------Date     :23-09-2006--------------------------------------------------
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_sub_business_sectors_desc")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT SUB_CODE,SECTOR_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2).replace('&','$')+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
				
				
			}
			
			/*------------------ID         :1.9 User Creation Process-----------------------------------------
            --------------------Purpose    :User ID Validation ----------------------------------------------
          -------------------   Added By :Nuwan De Silva------------------------------------------------------
          --------------------  Date     :20-07-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_user_name")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE, USER_TYPE, EMP_ID,"+
					" DIVISION_CODE, DESIGNATION_CODE FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" WHERE UPPER(NAME)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			/*--------------------ID       :1.38 Seizer Creation Process-------------------------------
        --------------------Purpose    :Seizer Code validation------------------------------------------------
        ------------------- Added By   :Delanjali------------------------------------------------------
        --------------------Date       :24-07-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_seizer_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				//	String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT  SEIZER_CODE,FIRST_NAME,LAST_NAME,ADDRESS1,ADDRESS2,MOBILE_NO,TEL_NO,CITY_CODE,FEE_PER_CASE,MONTHLY_FEE,"+
					" DEFAULT_VALUE,VALIDITY_PERIOD,NIC_NO FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
					" WHERE UPPER(FIRST_NAME)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("<R1>"+resultSet.getString(5)+"</R1>");
					out.print("<R1>"+resultSet.getString(6)+"</R1>");
					out.print("<R1>"+resultSet.getString(7)+"</R1>");
					out.print("<R1>"+resultSet.getString(8)+"</R1>");
					out.print("<R1>"+resultSet.getString(9)+"</R1>");
					out.print("<R1>"+resultSet.getString(10)+"</R1>");
					out.print("<R1>"+resultSet.getString(11)+"</R1>");
					out.print("<R1>"+resultSet.getString(12)+"</R1>");
					out.print("<R1>"+resultSet.getString(13)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//------------------NIC VALIDATION ADDED MILINDA 2015-10-22---------------------
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_nic")){
				
				String m_val = req.getParameter("data_val").trim();
				//	String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT  SEIZER_CODE,FIRST_NAME,LAST_NAME,ADDRESS1,ADDRESS2,MOBILE_NO,TEL_NO,CITY_CODE,FEE_PER_CASE,MONTHLY_FEE,"+
					" DEFAULT_VALUE,VALIDITY_PERIOD FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
					" WHERE UPPER(NIC_NO)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("<R1>"+resultSet.getString(5)+"</R1>");
					out.print("<R1>"+resultSet.getString(6)+"</R1>");
					out.print("<R1>"+resultSet.getString(7)+"</R1>");
					out.print("<R1>"+resultSet.getString(8)+"</R1>");
					out.print("<R1>"+resultSet.getString(9)+"</R1>");
					out.print("<R1>"+resultSet.getString(10)+"</R1>");
					out.print("<R1>"+resultSet.getString(11)+"</R1>");
					out.print("<R1>"+resultSet.getString(12)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_seizer_code")){
				
				String m_val = req.getParameter("data_val").trim();
				//	String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT  SEIZER_CODE,FIRST_NAME,LAST_NAME,ADDRESS1,ADDRESS2,MOBILE_NO,TEL_NO,CITY_CODE,FEE_PER_CASE,MONTHLY_FEE,"+
					" DEFAULT_VALUE,VALIDITY_PERIOD,NVL(NIC_NO,' ') FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
					" WHERE UPPER(SEIZER_CODE)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("<R1>"+resultSet.getString(5)+"</R1>");
					out.print("<R1>"+resultSet.getString(6)+"</R1>");
					out.print("<R1>"+resultSet.getString(7)+"</R1>");
					out.print("<R1>"+resultSet.getString(8)+"</R1>");
					out.print("<R1>"+resultSet.getString(9)+"</R1>");
					out.print("<R1>"+resultSet.getString(10)+"</R1>");
					out.print("<R1>"+resultSet.getString(11)+"</R1>");
					out.print("<R1>"+resultSet.getString(12)+"</R1>");
					out.print("<R1>"+resultSet.getString(13)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_user_fill_empdata")){
				
				String m_val = req.getParameter("data_val").trim();
				//	String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT  EMP_CODE,LOCATION_CODE,DIVISION_CODE,DESIGNATION_CODE "+
					" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
					" WHERE UPPER(EMP_CODE)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}			
			
			/*------------------ID         : 1.29 Team Creation Process-----------------------------------------
            --------------------Purpose    :Team Description Validation ----------------------------------------------
          -------------------   Added By :Yohan Gunarathna------------------------------------------------------
          --------------------  Date     :23-09-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_team_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT TEAM_ID,TEAM_DESC,NVL(TEAM_HEAD,'N/A'),NVL(DIVISION_CODE,'N/A'),NVL(SUB_DIVISION_CODE,'N/A') FROM "+m_schema_name+".AF_CO_MAS_TEAMS"+
					" WHERE UPPER(TEAM_DESC)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			//-------------------  ID  			  :1.51 Applicable Document Type Creation Process ---------------------------------//
			//---------------------Purpose 	  :To validate the Entity Type-----------------------------------
			//---------------------Name       :Nuwan De Silva---------------------------------------------------
			//---------------------Date       :25-07-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_legal_entity_desc")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT ENTITY_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')" );
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			/*--------------------ID       :1.35  Phone Area Code Creation Process-------------------------------
            --------------------Purpose    :Phone Area Code validation------------------------------------------------
          ------------------- Added By   :Delanjali------------------------------------------------------
          --------------------Date       :21-07-2006---------------------------------------------------------*/
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_phone_area_codes_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT PHONE_AREA_CODE,CITY_CODE,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_PHONE_CODES "+
					" WHERE UPPER(PHONE_AREA_CODE)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*------------------ID         :1.23 Item Sub Category Creation Process-------------------------------
        --------------------Purpose    :Item Sub Category Code validation------------------------------------------------
        ------------------- Added By   :Delanjali------------------------------------------------------
        --------------------Date       :25-09-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_item_sub_category_desc1")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT DISTINCT DESCRIPTION FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
					" WHERE UPPER(DESCRIPTION)= UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					//out.print("<R2>"+resultSet.getString(2)+"</R2>");
					//out.print("<R3>"+resultSet.getString(3)+"</R3>");
					//out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//------------------------
			//added by Prabash on 23-05-2012---------*
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_reg_no")){
				
				String m_val = req.getParameter("data_val");
				
				//		String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT REG_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+
					" WHERE UPPER(REG_NO) = UPPER('"+m_val+"') AND STATUS ='B' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_reg_no_ract")){
				
				String m_val = req.getParameter("data_val");
				
				//		String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT REG_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+
					" WHERE UPPER(REG_NO) LIKE UPPER('"+m_val+"%') AND STATUS ='B' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_reg_no_dact")){
				
				String m_val = req.getParameter("data_val");
				
				//		String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT REG_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+
					" WHERE UPPER(REG_NO) LIKE UPPER('"+m_val+"%') AND STATUS ='A' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//---------------------------------------*
			
			//---------------------------
			//-------------------- Transaction Process-----------------------------//	
			//--------------------Transaction Code Validation -------------------------------------------------//
			//--------------------Delanjali------------------------------------------------------//
			//--------------------25-09-2006---------------------------------------------------------//
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_transaction1")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT TRAN_CODE FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE UPPER(TRAN_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//---------------------Purpose 	:To validate the leasing-----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 09 25--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_leasing_div")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_val1 = req.getParameter("data_val1").trim();
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT DIVISION FROM "+m_schema_name+".AF_CO_LEASE_PROCESS_STAGE " +
					" WHERE UPPER(DIVISION)=TRIM(UPPER('"+m_val+"')) " +
					" AND ACTIVE_STATUS=UPPER('"+m_status+"')" );
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//--------------------ID         :1.78 Follow up Category Creation Process------------------------------------------------
			//--------------------Purpose    :Follow up Category Code validation------------------------------------------------
			//------------------- Added By   :Delanjali------------------------------------------------------
			//--------------------Date       :25-09-2006---------------------------------------------------------*/
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_follow_up_action_category_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT CATEGORY_CODE,CATEGORY_NAME,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY"+
					" WHERE UPPER(CATEGORY_NAME)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					//out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_follow_up_action_category_r")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT CATEGORY_CODE,CATEGORY_NAME,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY"+
					" WHERE UPPER(CATEGORY_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					//out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//--------------------ID         :1.77 Discount Rate Creation Process------------------------------------------------
			//--------------------Purpose    :Rate validation------------------------------------------------
			//------------------- Added By   :Delanjali------------------------------------------------------
			//--------------------Date       :25-09-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_discount_rate_process1")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT RATE,TO_CHAR(FROM_DATE,'DD-MM-YYYY')FROM "+m_schema_name+".AF_CO_MAS_DISCOUNT_RATE"+
					" WHERE TO_CHAR(FROM_DATE,'DD-MM-YYYY')=UPPER('"+m_val+"')");
				//AND ACTIVE_STATUS='"+m_status+"' "
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			//--------------------Lead Source Creation Process-----------------------------//	
			//--------------------Lead Source Code Validation -------------------------------------------------//
			//--------------------DelanjaLi------------------------------------------------------//
			//--------------------25-09-2006---------------------------------------------------------//
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_lead_source_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_lead_source_r")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE "+
					" WHERE UPPER(CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//-------------------- ID : 1.31 Income / Expense Type Creation Process -----------------------------//	
			//--------------------Prevent Entering Duplicate Income Expense Description  -------------------------------------------------//
			//--------------------Yohan Gunarathna------------------------------------------------------//
			//--------------------25-09-2006---------------------------------------------------------//
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_income_expence_type_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT  I_E_CODE,DESCRIPTION,DEFAULT_VALUE  FROM  "+m_schema_name+".AF_CO_MAS_INCOME_EXPENCE_TYPE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			//----------------------ID    : -----------------------------------//
			//----------------------Purpose :Repament Type  -------------------------------------------------//
			//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
			//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_repayment_method_code")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT REPAYMENT_TYPE,NVL(DESCRIPTION,'N/A'),DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_METHOD "+
					" WHERE UPPER(REPAYMENT_TYPE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_repayment_method_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT REPAYMENT_TYPE,NVL(DESCRIPTION,'N/A'),DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_METHOD "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//----------------------ID    : -----------------------------------//
			//----------------------Purpose :Assest Usage  -------------------------------------------------//
			//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
			//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_asset_usage_type_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT USAGE_TYPE,NVL(DESCRIPTION,'N/A'),DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_ASSET_USAGE_TYPE "+
					" WHERE UPPER(USAGE_TYPE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_asset_usage_type_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT USAGE_TYPE,NVL(DESCRIPTION,'N/A'),DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_ASSET_USAGE_TYPE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//----------------------ID      :1.74 Early termination Charge Process -----------------------------------//
			//----------------------Purpose :Early Termination  -------------------------------------------------//
			//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
			//----------------------Date    :31-07-2006--------------------------------------------------------//
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_early_termination_charge_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT TERMINATION_TYPE,DESCRIPTION,AMOUNT FROM "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "+
					" WHERE UPPER(TERMINATION_TYPE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_early_termination_charge_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT TERMINATION_TYPE,DESCRIPTION,AMOUNT FROM "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//---------------------ID  			:Holiday Process  ---------------------------------//
			//---------------------Purpose 	:Holiday_Date Validation----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 28--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_holidays_code")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY'),DESCRIPTION FROM "+m_schema_name+".CO_CO_MAS_HOLIDAY "+
					" WHERE TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY')='"+m_val+"' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//---------------------ID  			:Holiday Process  ---------------------------------//
			//---------------------Purpose 	:Holiday_Date Validation----------------------------------
			//---------------------Name     :Delanjali---------------------------------------------------
			//---------------------Date     :2006 07 28--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_holidays_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY'),DESCRIPTION FROM "+m_schema_name+".CO_CO_MAS_HOLIDAY "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//--------------------  Lead Source Category Process-----------------------------//	
			//--------------------Source Code Validation -------------------------------------------------//
			//--------------------Delanjali------------------------------------------------------//
			//--------------------26-09-2006---------------------------------------------------------//
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_lead_source_cat_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT DISTINCT NAME FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT "+
					" WHERE UPPER(NAME)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_lead_source_cat_r")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT  SOURCE_CODE,NAME,DEFAULT_VALUE  FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT "+
					" WHERE UPPER(SOURCE_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//---------------------ID  			:1.71 Variable Interest Base Process ---------------------------------//
			//---------------------Purpose 	:To validate the Base Code-----------------------------------
			//---------------------Name     :Nuwan De Silva---------------------------------------------------
			//---------------------Date     :2006 09 26--------------------------------------------------
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_variable_interest_base_process_code")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT BASE_CODE,DESCRIPTION,RATE FROM "+m_schema_name+".AF_CO_MAS_INTEREST_BASE "+
					" WHERE UPPER(BASE_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_variable_interest_base_process_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT BASE_CODE,DESCRIPTION,RATE FROM "+m_schema_name+".AF_CO_MAS_INTEREST_BASE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID :1.70 Transaction Sub Type Process-----------------------------//	
			//--------------------Transaction Sub Type Code Validation -------------------------------------------------//
			//--------------------Nuwan De Silva------------------------------------------------------//
			//--------------------26-09-2006---------------------------------------------------------//
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_transaction_sub_type_code")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT  TRN_SUB_TYPE,TRN_CODE,DESCRIPTION,RATE,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE "+
					" WHERE UPPER(TRN_SUB_TYPE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_transaction_sub_type_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT  TRN_SUB_TYPE,TRN_CODE,DESCRIPTION,RATE,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID :1.69 Transaction Process-----------------------------//	
			//--------------------Transaction Code Validation -------------------------------------------------//
			//--------------------nuwan de silva------------------------------------------------------//
			//--------------------26-09-2006---------------------------------------------------------//
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_transaction_code")){
				String m_val = req.getParameter("data_val").trim();
				
				//	String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE UPPER(TRAN_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_transaction_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				//		String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			/*--------------------ID       :1.39 Yard Creation Process-------------------------------
            --------------------Purpose    :Yard Code validation------------------------------------------------
          ------------------- Added By   :Delanjali------------------------------------------------------
          --------------------Date       :24-07-2006---------------------------------------------------------*/
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_yard_creation_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT YARD_CODE,NAME,ADDRESS1,ADDRESS2,CITY_CODE,TEL_NO,FAX_NO,DEFAULT_VALUE"+
					" FROM "+m_schema_name+".AF_CO_MAS_YARD"+
					" WHERE UPPER(YARD_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("<R1>"+resultSet.getString(5)+"</R1>");
					out.print("<R1>"+resultSet.getString(6)+"</R1>");
					out.print("<R1>"+resultSet.getString(7)+"</R1>");
					out.print("<R1>"+resultSet.getString(8)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			//---------------------  ID  			:1.40 Make Creation Process ---------------------------------//
			//---------------------Purpose 	:To validate the Make Code-----------------------------------
			//---------------------Name     :N.V.P.Chandana---------------------------------------------------
			//---------------------Date     :24-07-2006--------------------------------------------------
			
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_make_creation_desc")){
				String m_val = req.getParameter("data_val").trim();
				//	String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT MAKE_CODE,MAKE_DESC,ITEM_SUB_CAT,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
					" WHERE UPPER(MAKE_DESC)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			///////////----------Nuwan De Silva---------------------------------//////////////
			////////-------------26-09-2006 -----------------------------------//////////////
			////////-------------account code -------------------------------////////////
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_account_code_desc")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				//	resultSet= statement.executeQuery ("SELECT ACCOUNT_CODE,DESCRIPTION,REPORT_TYPE,STATUS  FROM LAKDL.AF_CO_ACC_ACCOUNTS_CODE "+
				//	" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')");
				
				resultSet= statement.executeQuery ("SELECT ACC_TYPE_CODE,ACC_TYPE_DESC,ACC_TYPE_CATEGORY,STATUS  FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE "+
					" WHERE UPPER(ACC_TYPE_DESC) LIKE UPPER('"+m_val+"%')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//-------------------ID  			  :1.44 Initiation Type Creation Process ---------------------------------//
			//---------------------Purpose 	  :To validate the Initiation Type Code-----------------------------------
			//---------------------Name       :nuwan de silva---------------------------------------------------
			//---------------------Date       :26-09-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_initiation_type_desc")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT INITIATION_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_MK_MAS_INITIATION_TYPE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			/*------------------ID         : 1.29 Team Creation Process-----------------------------------------
            --------------------Purpose    :Sub Division Code Validation ----------------------------------------------
          -------------------   Added By :Nuwan De Silva------------------------------------------------------
          --------------------  Date     :26-09-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_sub_division_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT SUB_DIVISION_CODE,DESCRIPTION,DIVISION_CODE FROM LAKDL.CO_CO_MAS_SUB_DIVISION"+
					" WHERE UPPER(SUB_DIVISION_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_sub_division_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT SUB_DIVISION_CODE,DESCRIPTION,DIVISION_CODE FROM LAKDL.CO_CO_MAS_SUB_DIVISION"+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//---------------------  ID  			:1.41 Model Creation Process ---------------------------------//
			//---------------------Purpose 	:To validate the Model Code-----------------------------------
			//---------------------Name     :Nuwan De Silva---------------------------------------------------
			//---------------------Date     :26-09-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_model_creation_desc")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT MODEL_CODE,DESCRIPTION,MAKE_CODE,FUEL_TYPE,TAX_RATE,TAX_FOR_LEASE,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID :1.47 Mileage Creation Process-----------------------------//	
			//--------------------condition of asset code Validation ----------------------------//
			//--------------------delanjali------------------------------------------------------//
			//--------------------27-09-2006-----------------------------------------------------//
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_conasst_1")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION  "+  
					" FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET "+
					" WHERE CODE = UPPER('"+m_val+"')" );
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID : 1.34 Nationality Creation Process-----------------------------//	
			//--------------------Nationality Code Validation -------------------------------------------------//
			//--------------------Delanjali------------------------------------------------------//
			//--------------------26-09-2006---------------------------------------------------------//
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_nationality_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT DESCRIPTION  FROM LAKDL.AF_CO_MAS_NATIONALITY "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//thamali
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_nationality_default_value")){
				//String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT NATIONALITY_CODE,DESCRIPTION  FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY "+
					" WHERE DEFAULT_VALUE = 'Y' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//thamali 2011.12.27
			
			// added by udara on 24-05-2012
			else if (m_chksql.equals("m_chk_scedule_code_check")){
				
				String m_sch_code   = req.getParameter("sch_code").trim();
				resultSet= statement.executeQuery ("SELECT SHEDULE_REF FROM "+m_schema_name+".AF_CO_PRO_APP_SHEDULE WHERE UPPER(SHEDULE_REF)=UPPER('"+m_sch_code+"') "+
					"   ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}			
			// end by udara on 24-05-2012
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_nationality")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT  NATIONALITY_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY "+
					" WHERE UPPER(NATIONALITY_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_nationality_r")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT  NATIONALITY_CODE,DESCRIPTION  FROM LAKDL.AF_CO_MAS_NATIONALITY "+
					" WHERE UPPER(NATIONALITY_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			/*--------------------ID       :1.62 Score Category Process-------------------------------
            --------------------Purpose    :Score Category validation------------------------------------------------
          ------------------- Added By   :Nuwan De Silva------------------------------------------------------
          --------------------Date       :24-07-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_score_category_code")){
				
				String m_val = req.getParameter("data_val").trim();
				///String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT SCORE_CODE,DESCRIPTION,DISPLAY_POSITION FROM LAKDL.AF_CR_MAS_SCORE_CATEGORY"+
					" WHERE UPPER(SCORE_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_score_category_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT SCORE_CODE,DESCRIPTION,DISPLAY_POSITION FROM LAKDL.AF_CR_MAS_SCORE_CATEGORY"+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//-------------------- ID : 1.32 Nationality Creation Process-----------------------------//	
			//--------------------Inquiry Code Validation -------------------------------------------------//
			//--------------------Delanjali------------------------------------------------------//
			//--------------------27-09-2006---------------------------------------------------------//
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_inquiry_status_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CODE,DESCRIPTION,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_INQUARY_STATUS "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			/*--------------------ID       :1.73 Authorization Limits Process-------------------------------
            --------------------Purpose    :user id validation------------------------------------------------
          ------------------- Added By   :delanjali------------------------------------------------------
          --------------------Date       :27-09-2006---------------------------------------------------------*/
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_authorization_limits_r")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val1 = req.getParameter("data_val1").trim();
				//	String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT USER_ID,AUTHORIZATION_LEVEL,LIMIT,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_AUTHORIZATION_LIMITS"+
					" WHERE UPPER(USER_ID)=UPPER('"+m_val+"') and AUTHORIZATION_LEVEL=('"+m_val1+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			/*--------------------ID       :1.64 Score Rating Process-------------------------------
    --------------------Purpose    :Score Rating Code validation------------------------------------------------
    ------------------- Added By   :Nuwan De Silva------------------------------------------------------
    --------------------Date       :25-07-2006---------------------------------------------------------*/
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_score_rating_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT RATING_CODE,NVL(DESCRIPTION,'N/A'),NVL(FROM_RAGE,0),NVL(TO_RANGE,0) FROM LAKDL.AF_CR_MAS_SCORE_RATING"+
					" WHERE UPPER(RATING_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_score_rating_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT RATING_CODE,NVL(DESCRIPTION,'N/A'),NVL(FROM_RAGE,0),NVL(TO_RANGE,0) FROM LAKDL.AF_CR_MAS_SCORE_RATING"+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//--------------------- ID  			:1.43 Customer Category Creation Process ---------------------------------//
			//---------------------Purpose 	  :To validate the Customer Category Code-----------------------------------
			//---------------------Name       :N.V.P.Chandana---------------------------------------------------
			//---------------------Date       :25-07-2006--------------------------------------------------
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_customer_category_desc")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT CAT_TYPE_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_MK_MAS_CUSTOMER_CATOGORY "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//-------------------- ID :1.47 Mileage Creation Process-----------------------------//	
			//--------------------Model Code Validation -------------------------------------------------//
			//--------------------Delanjali------------------------------------------------------//
			//--------------------27-09-2006---------------------------------------------------------//
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_mileage_r")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT SUB_MODEL,MODEL,CONDITION_OF_ASSET,USAGE_FROM,USAGE_TO,AMOUNT FROM LAKDL.AF_CO_MAS_MILEAGE "+
					" WHERE UPPER(SUB_MODEL)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			
			/*--------------------ID       :1.55 Repayment Interval Creation Process-------------------------------
        --------------------Purpose    :Duration validation------------------------------------------------
        ------------------- Added By   :Nuwan ------------------------------------------------------
        --------------------Date       :28-09-2006---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_repayment_interval_code")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT DURATION,DESCRIPTION,DURATION_TYPE,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL"+
					" WHERE UPPER(DURATION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_repayment_interval_desc")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT DURATION,DESCRIPTION,DURATION_TYPE,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL"+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//------------------------------------------------------------------------------------------
			
			
			//// Added by Chandana on 12/07/07 for Agreement Printing
			
			else if (m_chksql.equals("m_chk_get_client_type")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery (" SELECT CLIENT_CODE, DECODE(CLIENT_CATEGORY,'INDIVIDUAL','IN','CO') "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE CLIENT_CODE ='"+m_val+"' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			//// End by Chandana //////
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_applicable_charges")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT ITEM_SUB_CAT,SUB_TYPE_CODE,FUAL_TYPE_CODE, "+
					"TO_CHAR(FROM_DATE,'DD-MM-YYYY'),TO_CHAR(TO_DATE,'DD-MM-YYYY'),AMOUNT, "+
					"PERCENTAGE,DEFAULT_VALUE "+
					"FROM "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE "+
					"WHERE ITEM_SUB_CAT=UPPER('"+m_val+"') AND ACTIVE_STATUS=('Y') ");
				
				
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*--------------------ID       :CR DB Narrations Process-------------------------------
            --------------------Purpose    :Narration code------------------------------------------------
          ------------------- Added By   :delanjali ------------------------------------------------------
          --------------------Date       :01-03-2007---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_cr_db_narrations")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();	
				
				resultSet= statement.executeQuery ("SELECT NARRATIONS_CODE,NARRATIONS,ACTIVE_STATUS,DEFAULT_VALUE "+
					"FROM "+m_schema_name+".AF_CO_MAS_CR_DB_NARRATIONS "+
					"WHERE UPPER(NARRATIONS_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"')");
				
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//Added by Mahela on 16-05-2007
			//Purpose : Admin Screens - Refinemenst
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_cr_db_narrations1")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT NARRATIONS_CODE,NARRATIONS,ACTIVE_STATUS,DEFAULT_VALUE "+
					"FROM "+m_schema_name+".AF_CO_MAS_CR_DB_NARRATIONS "+
					"WHERE UPPER(NARRATIONS_CODE)=UPPER('"+m_val+"') ");
				
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*--------------------ID       :Cheque return Narrations Process-------------------------------
            --------------------Purpose    :Narration code------------------------------------------------
          ------------------- Added By   :delanjali ------------------------------------------------------
          --------------------Date       :14-08-2007---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_cheque_return_narrations")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();	
				
				resultSet= statement.executeQuery ("SELECT CHQ_NARR_CODE,CHQ_NARRATIONS,ACTIVE_STATUS,DEFAULT_VALUE "+
					"FROM "+m_schema_name+".AF_CO_MAS_CHEQUE_NARRATIONS "+
					"WHERE UPPER(CHQ_NARR_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"')");
				
				
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_cheque_return_narrations1")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT CHQ_NARR_CODE,CHQ_NARRATIONS,ACTIVE_STATUS,DEFAULT_VALUE "+
					"FROM "+m_schema_name+".AF_CO_MAS_CHEQUE_NARRATIONS "+
					"WHERE UPPER(CHQ_NARR_CODE)=UPPER('"+m_val+"') ");
				
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*--------------------ID       :CRIB -------------------------------
            --------------------Purpose    :CRIB------------------------------------------------
          ------------------- Added By   :Nuwan ------------------------------------------------------
          --------------------Date       :02-03-2007---------------------------------------------------------*/
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_CRIB")){
				
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				
				
				resultSet= statement.executeQuery (" SELECT "+
					" NVL(FULL_NAME,'-'),NVL(NIC_NO,'-'),NVL((TO_CHAR(DATE_OF_BIRTH,'DD-MM-YYYY')),'-'),CLIENT_CATEGORY, "+
					" NVL(ADDRESS1,'-') ,NVL(ADDRESS2,'-'),NVL(CITY_CODE,'-'),CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE ACTIVE_STATUS=UPPER('"+m_status+"')  AND "+
					" GRIB_NO IS NULL AND "+
					" ACTIVE_STATUS=UPPER('"+m_status+"') AND "+
					" (AF_CLIENT=UPPER('"+m_status+"') OR "+
					" AF_GUARANTORS=UPPER('"+m_status+"')) "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
				//  CLIENT_CODE ,TEL_NO,FAX_NO
				
				
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					//out.print("<R9>"+resultSet.getString(9)+"</R9>");
					//out.print("<R10>"+resultSet.getString(10)+"</R10>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//Added by Mahela on 16-05-2007
			// Purpose : admin screens : refinements	
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_rmv_agents1")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				resultSet= statement.executeQuery ("SELECT RMV_AGENT_CODE,NAME,ADDRESS1,ADDRESS2,CITY_CODE,MOBILE_NO,TEL_NO,MONTHLY_FEE,FEE_FOR_CASE,DEFAULT_VALUE"+
					" FROM "+m_schema_name+".AF_CO_MAS_RMV_AGENTS "+
					" WHERE UPPER(RMV_AGENT_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R1>"+resultSet.getString(2)+"</R1>");
					out.print("<R1>"+resultSet.getString(3)+"</R1>");
					out.print("<R1>"+resultSet.getString(4)+"</R1>");
					out.print("<R1>"+resultSet.getString(5)+"</R1>");
					out.print("<R1>"+resultSet.getString(6)+"</R1>");
					out.print("<R1>"+resultSet.getString(7)+"</R1>");
					out.print("<R1>"+resultSet.getString(8)+"</R1>");
					out.print("<R1>"+resultSet.getString(9)+"</R1>");
					out.print("<R1>"+resultSet.getString(10)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//Added by Mahela on 16-05-2007
			//Purpose : admin screens : refinements		
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_score_sub_category3")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  SCORE_SUB_CODE,SCORE_CODE,DESCRIPTION,DISPALY_POSITION  FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+
					" WHERE UPPER(SCORE_SUB_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3).replace('&','$')+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//Added by Mahela on 16-05-2007
			//Purpose : admin screens : refinements
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_income_expence_type")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  I_E_CODE,DESCRIPTION,DEFAULT_VALUE,TYPE  FROM "+m_schema_name+".AF_CO_MAS_INCOME_EXPENCE_TYPE "+
					" WHERE UPPER(I_E_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//Added by Mahela on 16-05-2007
			//Purpose : admin screens : refinements
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_garage")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  GARAGE_CODE,NAME  FROM "+m_schema_name+".AF_CO_MAS_GARAGE "+
					" WHERE UPPER(GARAGE_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			} 
			//Added by Mahela on 16-05-2007
			//Purpose : admin screens : refinements
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_applicable_fields")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT FILED_CODE,ITEM_CATEGORY,PROCESS_STAGE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_FILEDS_APPLICABLE "+
					" WHERE UPPER(FILED_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//Added by Mahela on 16-05-2007
			//Purpose : admin screens : refinements	
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_option")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  OPTION_CODE,OPTION_DESC,DEFAULT_VALUE  FROM LAKDL.AF_CO_MAS_OPTION_TYPE "+
					" WHERE UPPER(OPTION_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			//Added by Mahela on 16-05-2007
			//Purpose : admin screens : refinements
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_sub_charge")){
				
				String m_val = req.getParameter("data_val").trim();	
				
				resultSet= statement.executeQuery ("SELECT SUB_TYPE_CODE,TYPE_CODE,DESCRIPTION,DEFAULT_VALUE,MAINTENANCE_STATUS,CHARGE_TYPE FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES"+
					" WHERE UPPER(SUB_TYPE_CODE)=UPPER('"+m_val+"')  ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_sub_charge1")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				resultSet= statement.executeQuery ("SELECT SUB_TYPE_CODE,TYPE_CODE,DESCRIPTION,DEFAULT_VALUE,MAINTENANCE_STATUS,CHARGE_TYPE,ACCOUNT_TYPE FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES"+
					" WHERE UPPER(SUB_TYPE_CODE)=UPPER('"+m_val+"')  AND ACTIVE_STATUS='"+m_status+"'  ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			
			
			
			//Added by Mahela on 16-05-2007
			//Purpose : admin screens : refinements	
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_broker")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,"+
					" LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO,MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,"+
					" COMMISSION_AMOUNT  FROM "+m_schema_name+".AF_CO_MAS_BROKER "+
					" WHERE UPPER(BROKER_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("<R5>"+resultSet.getString(5)+"</R5>");
					out.print("<R6>"+resultSet.getString(6)+"</R6>");
					out.print("<R7>"+resultSet.getString(7)+"</R7>");
					out.print("<R8>"+resultSet.getString(8)+"</R8>");
					out.print("<R9>"+resultSet.getString(9)+"</R9>");
					out.print("<R10>"+resultSet.getString(10)+"</R10>");
					out.print("<R11>"+resultSet.getString(11)+"</R11>");
					out.print("<R12>"+resultSet.getString(12)+"</R12>");
					out.print("<R13>"+resultSet.getString(13)+"</R13>");
					out.print("<R14>"+resultSet.getString(14)+"</R14>");
					out.print("<R15>"+resultSet.getString(15)+"</R15>");
					out.print("<R16>"+resultSet.getString(16)+"</R16>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			// Added by Chandana on 16-05-2007
			// Purpose : admin screens : refinements(Banks Screen)		
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_bank1")){
				
				String m_val = req.getParameter("data_val");
				
				resultSet= statement.executeQuery ("SELECT BANK_CODE,NAME,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
					" WHERE UPPER(BANK_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			// Added by Chandana on 16-05-2007
			// Purpose : admin screens : refinements(Bank Branchers Screen)		
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_branch1")){
				
				String m_val = req.getParameter("data_val");
				
				resultSet= statement.executeQuery ("SELECT BRANCH_CODE,BRANCH_NAME,BANK_CODE,ADDRESS1,NVL(ADDRESS2,'N/A'),NVL(CITY_CODE,'N/A'),NVL(TEL_NO,'N/A'),NVL(FAX_NO,'N/A'),DAYS_TO_REALISE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_BANK_BRANCH "+
					" WHERE UPPER(BRANCH_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("<R4>"+resultSet.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			// Added by Chandana on 16-05-2007
			// Purpose : admin screens : refinements(Location Screen)		
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_branch")){
				
				String m_val = req.getParameter("data_val");
				
				resultSet= statement.executeQuery ("SELECT BRANCH_CODE,BRANCH_NAME,BANK_CODE,ADDRESS1,NVL(ADDRESS2,'N/A'),NVL(CITY_CODE,'N/A'),NVL(TEL_NO,'N/A'),NVL(FAX_NO,'N/A'),DAYS_TO_REALISE,DEFAULT_VALUE FROM LAKDL.AF_CO_MAS_BANK_BRANCH "+
					" WHERE UPPER(BRANCH_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			// Added by Chandana on 16-05-2007
			// Purpose : admin screens : refinements(Employees Screen)	
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_employee1")){
				
				String m_val = req.getParameter("data_val");
				
				resultSet= statement.executeQuery ("SELECT EMP_CODE,TITLE,FIRST_NAME,LAST_NAME,ADDRESS,LOCATION_CODE,NVL(AREA_CODE,'N/A'),"+
					" NVL(CITY_CODE,'N/A'),NVL(CONTACT_NO,'N/A'),DESIGNATION_CODE,DIVISION_CODE,NVL(EPF_NO,'N/A'),ID_NO FROM LAKDL.CO_CO_MAS_EMPLOYEE "+
					" WHERE UPPER(EMP_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			// Added by Chandana on 16-05-2007
			// Purpose : admin screens : refinements(Team Screen)	
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_team")){
				
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT TEAM_ID,TEAM_DESC,NVL(TEAM_HEAD,'N/A'),NVL(DIVISION_CODE,'N/A'),NVL(SUB_DIVISION_CODE,'N/A') FROM LAKDL.AF_CO_MAS_TEAMS"+
					" WHERE UPPER(TEAM_ID)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			// Added by Chandana on 16-05-2007
			// Purpose : admin screens : refinements(Broker Screen)		
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_broker")){
				String m_val = req.getParameter("data_val").trim();
				
				resultSet= statement.executeQuery ("SELECT  BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,"+
					" LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO,MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,"+
					" COMMISSION_AMOUNT  FROM "+m_schema_name+".AF_CO_MAS_BROKER "+
					" WHERE UPPER(BROKER_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
			
			
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_credit")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{
					
					resultSet= statement.executeQuery (" SELECT  TYPE_OF_FACILITY,INSTITUTION,CONTACT_PERSON,CONTRACT_NO,"+
						" SECURITY,APPROVED_AMOUNT,BALANCE_AMOUNT,MONTHS "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APP_CREDIT_FACILITIE "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+nf1.format(resultSet.getDouble(6))+"</R6>");
						out.print("<R7>"+nf.format(resultSet.getDouble(7))+"</R7>");
						out.print("<R8>"+nf1.format(resultSet.getDouble(8))+"</R8>"); //Modified Nuwan De Silva -16-05-07
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else  if(m_help_status.equals("Y"))
				{
					
					resultSet= statement.executeQuery (" SELECT  TYPE_OF_FACILITY,INSTITUTION,CONTACT_PERSON,CONTRACT_NO,"+
						" SECURITY,APPROVED_AMOUNT,BALANCE_AMOUNT,MONTHS "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APP_CREDIT_FACILI_TM "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') ");
					
					out.print("<DATA>");
					while(resultSet.next()){
						out.print("<ITEM>");
						out.print("<R1>"+resultSet.getString(1)+"</R1>");
						out.print("<R2>"+resultSet.getString(2)+"</R2>");
						out.print("<R3>"+resultSet.getString(3)+"</R3>");
						out.print("<R4>"+resultSet.getString(4)+"</R4>");
						out.print("<R5>"+resultSet.getString(5)+"</R5>");
						out.print("<R6>"+nf1.format(resultSet.getDouble(6))+"</R6>");
						out.print("<R7>"+nf.format(resultSet.getDouble(7))+"</R7>");
						out.print("<R8>"+nf1.format(resultSet.getDouble(8))+"</R8>"); //Modified Nuwan De Silva -16-05-07
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
			}
			else if(m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_get_odi_balace")){//Added by Sandun on 09-08-2009
				
				String m_finance_no = req.getParameter("finance_no");
				
				resultSet = statement.executeQuery (" SELECT NVL(SUM(A.ODI_BAL_AMOUNT),0) "+
					" FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO = B.INVOICE_NO "+ 
					" AND B.FINANCE_NO   = '"+m_finance_no+"' "+
					" GROUP BY  B.FINANCE_NO ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getDouble(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if(m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_get_no_of_odi_invoices")){//Added by Sandun on 09-08-2009
				
				String m_finance_no = req.getParameter("finance_no");
				
				resultSet = statement.executeQuery (" SELECT COUNT(A.INVOICE_NO) "+
					" FROM  "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO =  B.INVOICE_NO "+
					" AND   B.FINANCE_NO =  '"+m_finance_no+"' "+
					" AND   A.STATUS     =  'ENT' ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getInt(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if(m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_get_odi_count_app1")){////Added by Sandun on 01-07-2009
				
				
				resultSet = statement.executeQuery (" SELECT COUNT(DISTINCT  FINANCE_NO) "+
					" FROM  "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO =  B.INVOICE_NO "+
					" AND   A.STATUS     =  'ENT' "+
					" AND   FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERM_TO','TERMI','REPOSSESS'))"); // REPOSSESS by udara 27-01-2014
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getInt(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if(m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_get_odi_count_app2")){//Added by Sandun on 01-07-2009
				
				
				
				resultSet = statement.executeQuery (" SELECT COUNT(DISTINCT FINANCE_NO) "+
					" FROM  "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO =  B.INVOICE_NO "+
					" AND   A.STATUS     =  'AP1' "+
					" AND   FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERM_TO','TERMI','REPOSSESS'))"); // REPOSSESS by udara 27-01-2014
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getInt(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//comment by nuwan de silva on 27-09-07-----------------move to sql_val2--------------------
			/*else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_director")){
                
                String m_val = req.getParameter("data_val").trim();
                String m_help_status = req.getParameter("tmp_help_status").trim();
                
                if(m_help_status.equals("N"))
                {

                resultSet= statement.executeQuery (" SELECT  NAME,NIC_NO,STAKE,NO_OF_SHARES,VALUE,POSITION,ADDRESS "+//modified by nuwan de silva 07-09-07
                " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS"+
        " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"')");
                
                out.print("<DATA>");
                while(resultSet.next()){
                    out.print("<ITEM>");
                    out.print("<R1>"+resultSet.getString(1)+"</R1>");
                    out.print("<R2>"+resultSet.getString(2)+"</R2>");
                    out.print("<R3>"+resultSet.getString(3)+"</R3>");
                    out.print("<R4>"+resultSet.getString(4)+"</R4>");
                    out.print("<R5>"+resultSet.getString(5)+"</R5>");
                    out.print("<R6>"+resultSet.getString(6)+"</R6>");
                    out.print("<R6>"+resultSet.getString(7)+"</R6>");
                    out.print("</ITEM>");
                }
                out.print("</DATA>");
                
                }
                else if(m_help_status.equals("Y"))
                {
                  
                    resultSet= statement.executeQuery (" SELECT  NAME,NIC_NO,STAKE,NO_OF_SHARES,VALUE,POSITION,ADDRESS "+ //modified by nuwan de silva 07-09-07
                " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS_TM"+
          " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"')");
                
                 out.print("<DATA>");
                 while(resultSet.next()){
                     out.print("<ITEM>");
                     out.print("<R1>"+resultSet.getString(1)+"</R1>");
                     out.print("<R2>"+resultSet.getString(2)+"</R2>");
                     out.print("<R3>"+resultSet.getString(3)+"</R3>");
                     out.print("<R4>"+resultSet.getString(4)+"</R4>");
                     out.print("<R5>"+resultSet.getString(5)+"</R5>");
                     out.print("<R6>"+resultSet.getString(6)+"</R6>");
                     out.print("<R7>"+resultSet.getString(7)+"</R7>");
                     out.print("</ITEM>");
                 }
                 out.print("</DATA>");
                 
              }
                
          }
            
          */ 
			
			
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
			//Added by Kanchana on 2016-08-08
			else if(m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_app_level_contracts")) {
				
				String m_val = req.getParameter("data_val").trim();
				
				String SQL22="SELECT COUNT(CLIENT_CODE)  "+
 													" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS   "+
 													" WHERE CLIENT_CODE = UPPER('"+m_val+"')      "+
 													" AND APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','NORM_TERMI','REPOSSESS','TERMI','TERMINATE') "+
													" ";
				resultSet = statement.executeQuery (SQL22);
				out.print("<DATA>");
				while (resultSet.next()) {
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getInt(1)+"</R1>");//" + resultSet.getInt(1) + "
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//End Kanchana on 2016-08-08
			
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
			
			//ADDED BY UDARA ON 07-02-2019
			else if (m_chksql.equals("m_prime_chk_LAKDL_AF_MAS_display_main_transaction_code")){
				String m_val = req.getParameter("data_val").trim();
				
				//	String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_MAIN_TRAN_TYPE "+
					" WHERE UPPER(TRAN_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			else if (m_chksql.equals("m_prime_chk_MRFL_AF_MAS_display_main_transaction_desc")){
				String m_val = req.getParameter("data_val").trim();
				
				//		String m_status = req.getParameter("ac_status");
				
				resultSet= statement.executeQuery ("SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_MAIN_TRAN_TYPE "+
					" WHERE UPPER(DESCRIPTION)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(resultSet.next()){
					out.print("<ITEM>");
					out.print("<R1>"+resultSet.getString(1)+"</R1>");
					out.print("<R2>"+resultSet.getString(2)+"</R2>");
					out.print("<R3>"+resultSet.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//END
			
			
			//------------------------------------------------------------------------------------------			
			else {
				out.println("Undefined");
			}
			
			out.close();
			connection.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
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
