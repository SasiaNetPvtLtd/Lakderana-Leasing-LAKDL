import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MISF_sql_validations extends javax.servlet.http.HttpServlet {
	
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
			stmt1=conn.createStatement();

			
			
			String m_username =  m_sn_methods.username;

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}		
			//Added by Mahela on 21-02-2007
			//Purpose : validate Guarantor ( Screen : Credit - Add Guarantor )
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_VAL_GUARANTOR")){
			
				String m_guarantor = req.getParameter("data_val").trim();
				String m_app_no = req.getParameter("data_val2").trim();
				String m_status = req.getParameter("ac_status");

				rs = stmt.executeQuery ("SELECT  APPLICATION_NO,"+
  				"  GUARANTOR_CODE "+
 					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
 					" WHERE APPLICATION_NO='"+m_app_no+"' "+
 					" AND GUARANTOR_CODE='"+m_guarantor+"' AND ACTIVE_STATUS='"+m_status+"' "); 

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }		
			
			
		/* ---------- Added by Chandana on 22/11/2007  for CB Reports------------- */	
	 		else if(m_chksql.trim().equals("get_quart_date")){
					
				rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'), "+
            "TO_CHAR(ADD_MONTHS(SYSDATE,0),'DD'),TO_CHAR(ADD_MONTHS(SYSDATE,0),'MM'),TO_CHAR(ADD_MONTHS(SYSDATE,0),'YYYY') FROM DUAL ");

				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getString(3)+"</R3>");
 					out.println("<R4>"+rs.getString(4)+"</R4>");
					out.println("<R5>"+rs.getString(5)+"</R5>");
					out.println("<R6>"+rs.getString(6)+"</R6>");
       out.println("</ITEM>");
				}
				out.println("</DATA>");      
    }


   	 else if(m_chksql.trim().equals("get_month_date")){
					
				rs = stmt.executeQuery(" SELECT TO_CHAR(LAST_DAY(SYSDATE),'DD'),TO_CHAR(LAST_DAY(SYSDATE),'MM'),TO_CHAR(LAST_DAY(SYSDATE),'YYYY'), "+
              " TO_CHAR((ADD_MONTHS(LAST_DAY(SYSDATE),-1) +1),'DD'), "+
							" TO_CHAR((ADD_MONTHS(LAST_DAY(SYSDATE),-1) +1),'MM'), "+
							" TO_CHAR((ADD_MONTHS(LAST_DAY(SYSDATE),-1) +1),'YYYY') "+
							" FROM DUAL ");

				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getString(3)+"</R3>");
 					out.println("<R4>"+rs.getString(4)+"</R4>");
					out.println("<R5>"+rs.getString(5)+"</R5>");
					out.println("<R6>"+rs.getString(6)+"</R6>");
       out.println("</ITEM>");
				}
				out.println("</DATA>");      
    }


	/*-----------------  End 22/11/2007    ----------------------*/		
			
		
		else if (m_chksql.trim().equals("chksql_get_sysdate")){	
		
	  		rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') "+
				                       " FROM DUAL "); 
  		
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
		else if (m_chksql.trim().equals("m_prime_chk_AF_CR_Finance_status_inqury_no")){	
		
		String m_inquary_no = req.getParameter("data_val").trim();
			
		rs = stmt.executeQuery (" SELECT "+
     " INQUIRY_CODE,CLIENT_NAME "+ 
		 " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
		 " WHERE INQUIRY_CODE='"+m_inquary_no+"' ");
		
			out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			
		else if (m_chksql.trim().equals("m_prime_chk_AF_CR_Finance_status_application_no")){
		
		String m_application_no = req.getParameter("data_val").trim();
		
		
		rs = stmt.executeQuery ("SELECT "+
		" APPLICATION_NO,CLIENT_CODE "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE APPLICATION_NO = '"+m_application_no+"' ");
		
			out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		
		}
		
		else if (m_chksql.trim().equals("m_prime_chk_AF_CR_Finance_status_finance_no")){
		
		String m_finance_no = req.getParameter("data_val").trim();
		
		rs = stmt.executeQuery ("SELECT "+
		" FINANCE_NO,APPLICATION_NO,INQUARY_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO ='"+m_finance_no+"'");
		
					out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		
		}
		
	
	else if (m_chksql.trim().equals("m_prime_chk_AF_CR_Finance_status_vihicle_no")){
		
		String m_vihicle_no = req.getParameter("data_val").trim();
		
		rs = stmt.executeQuery ("SELECT "+
		" REG_NO,INVOICE_NO,APPLICATION_NO "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
    " WHERE REG_NO ='"+m_vihicle_no+"'");
		
			out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		
		}	
			
			
	else if (m_chksql.trim().equals("m_prime_chk_AF_CR_Finance_status_client_no")){
		
		String m_client_no = req.getParameter("data_val").trim();
		
		rs = stmt.executeQuery ("SELECT DISTINCT A.CLIENT_CODE,B.FULL_NAME "+
     " FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_MAS_CLIENT B, LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS C "+
     " WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
		 " A.APPLICATION_NO=C.APPLICATION_NO AND "+
     " (A.CLIENT_CODE ='"+m_client_no+"' OR UPPER(B.FULL_NAME)=UPPER('"+m_client_no+"'))");
		
			out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		
		}			
			
			
			
			
			
			
			
			
			
			
			
			
			//---------------------Purpose 	:Display Guarantor Details for particular Application No-----------------------------------
			//---------------------Name     :Yohan---------------------------------------------------
			//---------------------Date     :13-09-2006--------------------------------------------------

		else if (m_chksql.trim().equals("m_pop_LAKDL_AF_CR_display_guarantor_data")){
				
				String m_application_no = req.getParameter("application_no").trim();
				
				rs= stmt.executeQuery ("SELECT A.APPLICATION_NO,NVL(A.GUARANTOR_CODE,'N/A'),NVL(B.FULL_NAME,'N/A'),NVL(REGISTERED_ADDRESS1,'-'),NVL(REGISTERED_ADDRESS2,'-'),nvl(decode(client_type,'C',vat_reg_no,'I',NIC_NO),'-'),CLIENT_TYPE,INQUARY_NO,DECODE(NVL(AF_CLIENT,'-'),'Y','Client','-') AF_CLIENT ,DECODE(NVL(FA_CLIENT,'-'),'Y','Factoring Client','-') FA_CLIENT,DECODE(NVL(FA_DEBTOR,'-'),'Y','Factoring Debtor','-') FA_DEBTOR  FROM "+
				                       " "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A , "+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
			                         " WHERE A.GUARANTOR_CODE = B.CLIENT_CODE AND UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
															"  AND UPPER(A.APPLICATION_NO)=UPPER(C.APPLICATION_NO)");
				
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
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			
			//---------------------Purpose 	:Display Documents Relavant To the Screen(Purchase Order)----------
			//---------------------Name     :Nuwan De Silva---------------------------------------------------
			//---------------------Date     :14-09-2006--------------------------------------------------
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_doc")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
	 rs= stmt.executeQuery (" SELECT CODE, DESCRIPTION "+
   " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
   " WHERE CODE  IN "+
   " ( SELECT "+
   " CODE "+
   " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
   " WHERE ITEM_CAT_CODE IN "+
   " (SELECT "+
   " DISTINCT ITEM_CATEGORY "+
   " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
   " WHERE PRE_INVOICE_NO=UPPER('"+m_val+"'))) AND  ACTIVE_STATUS=('"+m_status+"') AND DOC_APP_TYPE='ASSET' ");


				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
						//---------------------Purpose 	:Display Documents Relavant To the Screen(Purchase Order)----------
			//---------------------Name     :Nuwan De Silva---------------------------------------------------
			//---------------------Date     :14-09-2006--------------------------------------------------
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_approval")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				
			if(m_column.equals("ENT_DATE")){
			m_column="A.ENT_DATE";
			}
	 rs = stmt.executeQuery (" SELECT "+
    " NVL(PURCHASE_ORDER_NO,'-') PURCHASE_ORDER_NO,  "+
		" NVL(APPLICATION_NO,'-') APPLICATION_NO, "+
    " "+m_schema_name+".AF_CO_GET_CLI_NAME(APPLICATION_NO) CLIENT_NAME, "+
	  " "+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE) VENDER_NAME, "+
		" NVL(TOTAL_NET,0.00) TOTAL_NET , "+
    " NVL(TOTAL_VAT,0.00) TOTAL_VAT, "+
    " NVL(TO_CHAR(PURCHASE_ORDER_DATE),'-') PURCHASE_ORDER_DATE,LIMIT,NVL((TOTAL_VAT+TOTAL_NET),0)  "+
    " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS B "+
    " WHERE A.ACTIVE_STATUS='"+m_status+"' "+
		" and b.user_id='"+m_username+"' "+
		" ORDER BY "+m_column+" "+m_type+" ");




				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
		
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
	
		//---------------------Purpose 	:Display Valuers ? record value disputes----------
		//---------------------Name     :Chandana---------------------------------------------------
		//---------------------Date     :16-03-2007--------------------------------------------------
			
	
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_valuers_record_disputes")){
	
	String m_status = req.getParameter("ac_status").trim();
	String m_column = req.getParameter("sort_column").trim();
	String m_type = req.getParameter("order_by_type").trim();
	
	
	/* rs = stmt.executeQuery ("SELECT VALUATION_NO, "+
		                       "NVL(APPLICATION_NO,'-'), "+
													 "NVL(REG_NO,'-'), "+
													 "NVL(TO_CHAR(VALUATION_DATE,'DD-MON-YY'),'-'), "+
													 "NVL(VALUER_CODE,'-'), "+
													 "APPLICATION_NO, "+
													 "REG_NO "+
                           "FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
													 "WHERE APPLICATION_NO='AP20061003-0029' "+  
													 " ORDER BY "+m_column+" "+m_type+" ");	*/
														
														
	rs = stmt.executeQuery ("SELECT A.VALUATION_NO,A.APP_NO,A.REG_NO,A.VALUATION_DATE,A.VALUER,A.APPLICATION_NO,ENT_DATE "+
                          "FROM (SELECT VALUATION_NO , "+
													"NVL(APPLICATION_NO,'-') APP_NO, "+
													"NVL(REG_NO,'-') REG_NO, "+
													"NVL(TO_CHAR(VALUATION_DATE,'DD-MON-YY'),'-') VALUATION_DATE , "+
													"NVL(VALUER_CODE,'-') VALUER , "+
													"APPLICATION_NO , "+
													"ENT_DATE "+
													"FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
													"WHERE DISP_STATUS='N' ORDER BY ENT_DATE DESC)A "+
													"WHERE ROWNUM<16 "+
													" ORDER BY "+m_column+" "+m_type+" ");	
														
														
														
														
														
														
														
														
														
	
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
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
	
	
	
	
	
			
	//--------Used in  :purchase order----------------------------------------------------------
	//------- Purpose  :get the documents-------------------------------------------------------
			
			
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_documents")){
				
	String m_val = req.getParameter("data_val").trim();
	String m_status = req.getParameter("ac_status").trim();
															
  rs= stmt.executeQuery (" SELECT "+
  " CODE, "+
  " DESCRIPTION "+
  " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
  " WHERE   DOC_APP_TYPE='ASSET' AND  ACTIVE_STATUS=('"+m_status+"') "+
  " AND  CODE IN "+
  " (SELECT "+
  "    CODE "+
  " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
  " WHERE FROM_SCREEN_NO  <= "+
  " (SELECT "+
  " POSITION "+
  " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
  " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
	" AND TO_SCREEN_NO >= "+
	" (SELECT "+
  " POSITION "+
  " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
  " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
	" AND  ACTIVE_STATUS=('"+m_status+"') AND "+
  " ITEM_CAT_CODE =(SELECT "+
  " ITEM_CATEGORY "+
  " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
  " WHERE  PRO_INVOICE_NO =UPPER('"+m_val+"')) "+
  " ) ");


				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
   }
	//********************************************************************************************		
			
			
						else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_documents_entity")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
	
 /* rs= stmt.executeQuery (" SELECT "+
 " CODE, "+
 " DESCRIPTION "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
 " WHERE DOC_APP_TYPE='CLIENT' AND  ACTIVE_STATUS=('"+m_status+"') "+
 " AND  CODE IN "+
 " (SELECT "+
 " CODE "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
 " WHERE FROM_SCREEN_NO  BETWEEN "+
 " (SELECT "+
 " POSITION "+
 " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER' "+
 " ) AND TO_SCREEN_NO  AND  ACTIVE_STATUS='Y' AND  "+
 " ENTITY_TYPE IN (SELECT "+
 " ENTITY_CODE "+
 " FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
 " WHERE ACTIVE_STATUS=('"+m_status+"')) "+
 " ) ");*/
	
	
rs= stmt.executeQuery ( " SELECT DISTINCT CODE,DESCRIPTION "+
     " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
     " WHERE DOC_APP_TYPE='CLIENT' AND ACTIVE_STATUS=('"+m_status+"') "+
     " AND CODE IN "+
     " (SELECT CODE "+
     " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
     " WHERE FROM_SCREEN_NO <= "+
     " (SELECT "+
     " POSITION "+
     " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
     " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
     " AND TO_SCREEN_NO >="+
     " (SELECT "+
     " POSITION "+
     " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
     " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
			
			
		 " AND ACTIVE_STATUS=('"+m_status+"') AND "+
     " ENTITY_TYPE IN (SELECT "+
     " ENTITY_CODE "+
     " FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
     " WHERE ACTIVE_STATUS=('"+m_status+"') AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY "+ //DECODE(CLIENT_TYPE,'I','INDIVIDUAL','C','CORPORATE') 
     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
     " WHERE A.CLIENT_CODE=B.CLIENT_CODE))) ");


				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2).replace('&','$')+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			
						
						else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_documents_entity_app_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
	

	
	
/*rs= stmt.executeQuery ( " SELECT DISTINCT CODE,DESCRIPTION "+
		

			
     " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
     " WHERE DOC_APP_TYPE='CLIENT' AND ACTIVE_STATUS=('"+m_status+"') "+
     " AND CODE IN "+
     " (SELECT CODE "+
     " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
     " WHERE FROM_SCREEN_NO <= "+
     " (SELECT "+
     " POSITION "+
     " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
     " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
     " AND TO_SCREEN_NO >="+
     " (SELECT "+
     " POSITION "+
     " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
     " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
		 " AND PRODUCT_CODE=( "+
 		 " SELECT "+
 		 " TRANSACTION_TYPE  "+
 		 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 		 " WHERE APPLICATION_NO=UPPER('"+m_val+"'))	 "+	
			
		 " AND ACTIVE_STATUS=('"+m_status+"') AND "+
     " ENTITY_TYPE IN (SELECT "+
     " ENTITY_CODE "+
     " FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
     " WHERE ACTIVE_STATUS=('"+m_status+"') AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY "+ //DECODE(CLIENT_CATEGORY,'I','INDIVIDUAL','C','CORPORATE') 
     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
     " WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_NO=UPPER('"+m_val+"') ))) ");
*/
    
	/*	 rs= stmt.executeQuery ( " SELECT DISTINCT A.CODE,DESCRIPTION ,B.TO_SCREEN_NO "+
	   " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A,"+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B "+
     "  WHERE A.CODE=B.CODE AND   A.DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('Y') "+
     "  AND FROM_SCREEN_NO <=  "+
     "  (SELECT "+
     "  POSITION "+
     "  FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
     "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
     "  AND TO_SCREEN_NO >= "+
     "  (SELECT "+
     "  POSITION "+
     "  FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
     "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
	   " AND PRODUCT_CODE=( "+
 		 "  SELECT "+
 		 "  TRANSACTION_TYPE  "+
 		 "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 		 "  WHERE APPLICATION_NO=UPPER('"+m_val+"'))	 	 "+
		 " 	  AND  "+
     "  ENTITY_TYPE IN (SELECT  "+
     "  ENTITY_CODE  "+
     "  FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
     "  WHERE ACTIVE_STATUS=('Y') AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY "+
     "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
     "  WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_NO=UPPER('"+m_val+"') )) ");
			
			*/
			
			rs= stmt.executeQuery ("  SELECT DISTINCT A.CODE ,DESCRIPTION,NVL(NULL,'-') REMARK ,NVL(NULL,'-') STATUS,B.TO_SCREEN_NO "+
 			"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B "+
 			"  WHERE "+
  
  		"  B.ENTITY_TYPE IN (SELECT   "+
      "   ENTITY_CODE   "+
      "   FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY  "+
      "   WHERE ACTIVE_STATUS=('"+m_status+"') AND "+
      "   ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY  "+
      "   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
      "   WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
      "   A.APPLICATION_NO=UPPER('"+m_val+"'))) "+

  "  AND "+
  "  DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('"+m_status+"') "+
  "  AND A.CODE IN "+
  "  (SELECT CODE "+
  "  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
  "  WHERE FROM_SCREEN_NO <= "+
  "  (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
  "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
  "  AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
  "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND "+

  "  ENTITY_TYPE IN (SELECT   "+
  "       ENTITY_CODE   "+
  "       FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY  "+
  "       WHERE ACTIVE_STATUS=('"+m_status+"') AND "+
  "       ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY  "+ 
  "       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
  "       WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
  "       A.APPLICATION_NO=UPPER('"+m_val+"'))) "+
  
  "  AND PRODUCT_CODE=( "+
  "  SELECT "+
  "  TRANSACTION_TYPE "+
  "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
  "  WHERE APPLICATION_NO=UPPER('"+m_val+"')) "+
  "  AND B.CODE=A.CODE "+
  "  AND ACTIVE_STATUS=('"+m_status+"')) "+
  "  AND A.CODE NOT IN( "+
  "  SELECT DOCUMENT_TYPE  "+
  "  FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
  "  WHERE APPLICATION_NO=UPPER('"+m_val+"')    "+
  "  ) "+
  
  "  UNION "+
  
  "  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION ,NVL(C.REMARK,'-') REMARK ,NVL(C.STATUS,'-') STATUS,B.TO_SCREEN_NO "+
  "  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C "+
  "  WHERE "+
  
  "    B.ENTITY_TYPE IN (SELECT   "+
  "       ENTITY_CODE   "+
  "       FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY  "+
  "       WHERE ACTIVE_STATUS=('"+m_status+"') AND "+
  "       ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY  "+
  "       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
  "       WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
  "       A.APPLICATION_NO=UPPER('"+m_val+"'))) "+

  
  "  AND "+
  "  DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('"+m_status+"') "+
  "  AND C.DOCUMENT_TYPE IN "+
  "  (SELECT CODE "+
  "  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
  "  WHERE FROM_SCREEN_NO <= "+
  "  (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
  "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
  "  AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
  "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND "+

  "    ENTITY_TYPE IN (SELECT   "+
  "       ENTITY_CODE   "+
  "      FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY  "+
  "       WHERE ACTIVE_STATUS=('"+m_status+"') AND "+
  "       ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY  "+
  "       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
  "       WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
  "       A.APPLICATION_NO=UPPER('"+m_val+"'))) "+
  "  AND PRODUCT_CODE=( "+
  "  SELECT "+
  "  TRANSACTION_TYPE "+
  "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
  "  WHERE APPLICATION_NO=UPPER('"+m_val+"')) "+
  "  AND B.CODE=A.CODE "+
  "  AND ACTIVE_STATUS=('"+m_status+"')) "+
  "  AND C.DOCUMENT_TYPE=A.CODE  "+
  "  AND C.APPLICATION_NO=UPPER('"+m_val+"')   "+ 
  "  AND C.CLIENT_CODE= "+
  "  (SELECT B.CLIENT_CODE  "+
  "       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
  "       WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
  "       A.APPLICATION_NO=UPPER('"+m_val+"')) "+
  "  AND C.PRO_INVOICE_NO IS NULL "+
	" ORDER BY STATUS ASC " );



	
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2).replace('&','$')+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			

			
			
	   //---------------------Purpose  :Display Asset Details for particular Application No-----------------------------------
   //---------------------Name     :Yohan---------------------------------------------------
   //---------------------Date     :15-09-2006--------------------------------------------------
   
  else if (m_chksql.trim().equals("m_pop_LAKDL_AF_CR_display_asset_data")){
    
    String m_application_no = req.getParameter("application_no").trim();
    
    
    rs= stmt.executeQuery (" SELECT NVL(A.ASSET_ID,'N/A'),NVL(B.NAME,'N/A'),NVL(A.QTY,0), "+
                           " NVL(A.COST,0),NVL(C.DESCRIPTION,'N/A'),NVL(D.DESCRIPTION,'N/A'), "+
                           " NVL(A.STATUS,'-'),NVL(A.PURPOSE,'-'),NVL(A.PERIOD,0) "+
                           " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+m_schema_name+".AF_CO_MAS_VENDORS B ,"+m_schema_name+".AF_CO_MAS_MODEL C ,"+m_schema_name+". AF_CO_MAS_SUB_MODLE D "+
                           " WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
                     " AND A.SUPPLIER_CODE = B.VENDOR_CODE "+
                     " AND A.MODEL_CODE = C.MODEL_CODE "+
                     " AND A.SUB_MODEL_CODE = D.SUB_CODE "+
              " AND A.MODEL_CODE=D.MODEL_CODE ");
    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
     out.print("<R2>"+rs.getString(2)+"</R2>");
     out.print("<R3>"+rs.getInt(3)+"</R3>");
     out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
     out.print("<R5>"+rs.getString(5)+"</R5>");
     out.print("<R6>"+rs.getString(6)+"</R6>");
     out.print("<R7>"+rs.getString(7)+"</R7>");
     out.print("<R8>"+rs.getString(8)+"</R8>");
     out.print("<R9>"+rs.getString(9)+"</R9>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
			


			//---------------------Purpose 	:ASSEST DETAILS (Payment Screen)----------
			//---------------------Name     :Delanjali-------------------------------------------------
			//---------------------Date     :18-09-2006--------------------------------------------------
					else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_branch")){
			
				String m_val = req.getParameter("data_val");
				
				//String m_status = req.getParameter("ac_status");

				rs= stmt.executeQuery ("SELECT BRANCH_CODE FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
				" WHERE UPPER(BRANCH_CODE)=UPPER('"+m_val+"')  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}

	
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT ACC_NO FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH A,"+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT B"+
			" WHERE UPPER(A.BRANCH_CODE)=UPPER(B.BRANCH_CODE) AND UPPER(ACC_NO)=UPPER('"+m_val+"') AND B.ACTIVE_STATUS=('"+m_status+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
			     	}

	
	
	
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_settle")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			String m_val1=	req.getParameter("data_val1").trim();
			String m_inv=	req.getParameter("inv").trim();
	
			//	rs= stmt.executeQuery ("SELECT A.PURCHASE_ORDER_NO FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A "+
			//	","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,  "+
			////	""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
			//	"WHERE UPPER(A.PURCHASE_ORDER_NO)=UPPER('"+m_val+"') "+
			//	"AND A.APPLICATION_NO=E.APPLICATION_NO "+
			//	"AND E.APPLICATION_NO            =C.APPLICATION_NO  "+
			//	"AND B.REF_NO                    =C.INVOICE_NO  "+
			//	"AND nvl(BAL_TO_BE_PAID,0)- (select nvl(sum(SETTLE_AMT),0) from "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_sett  "+
			//	"WHERE UPPER(PURCHASE_ORDER_NO)=UPPER('"+m_val+"'))< ('"+m_val1+"') ");



				rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A "+
				","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,  "+
				""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E , "+
        "        "+m_schema_name+".AF_CR_PRO_PUR_ORDER_setdet g,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_sett j  "+ 
				"WHERE UPPER(A.PURCHASE_ORDER_NO)=UPPER('"+m_val+"') "+ 
				"AND A.APPLICATION_NO=E.APPLICATION_NO  "+ 
				"AND E.APPLICATION_NO            =C.APPLICATION_NO   "+ 
				"AND B.REF_NO                    =C.INVOICE_NO   "+ 
        "and j.requ_no=g.requ_no "+  
        "and g.invoice_no=b.ref_no "+ 
				"and ref_no=('"+m_inv+"') "+
        "AND nvl(BAL_TO_BE_PAID,0)<('"+m_val1+"') ");



				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
			     	}

	
	
	
	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement_new")){
			
			/*String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
				
			rs= stmt.executeQuery ("SELECT ACC_NO FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH A,"+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT B"+
			" WHERE UPPER(A.BRANCH_CODE)=UPPER(B.BRANCH_CODE) AND UPPER(ACC_NO)=UPPER('"+m_val+"') AND B.ACTIVE_STATUS=('"+m_status+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			*/
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			
			String m_ac = req.getParameter("ac_no").trim();
			String m_br = req.getParameter("br_code").trim();



			rs= stmt.executeQuery ("SELECT PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,VENDOR_CODE,FULL_NAME, "+
 			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,NVL(TO_CHAR(PRINTED_DATE,'DD-MM-YYYY'),'-'),A.ACC_NO "+
 			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH X,"+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT Y "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			//"AND A.BRANCH_CODE =('"+m_br+"') "+
			"AND A.ACC_NO=('"+m_ac+"') "+
			"AND PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
 			"AND A.ACTIVE_STATUS='"+m_status+"' "+
			"AND UPPER(X.BRANCH_CODE)=UPPER(Y.BRANCH_CODE) "+
			"AND UPPER(Y.ACC_NO)=UPPER('"+m_ac+"') AND Y.ACTIVE_STATUS=('Y')	");											
					
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

					out.print("</ITEM>");
				}
				out.print("</DATA>");

     	}
	
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_details")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
			rs= stmt.executeQuery("SELECT A.PURCHASE_ORDER_NO,A.APPLICATION_NO,FINANCE_NO,TO_CHAR(NVL(A.TOTAL_NET,0),'999,999,999.99'),TO_CHAR(NVL(A.TOTAL_VAT,0),'999,999,999.99'), "+
			"VENDOR_CODE,NAME,D.CLIENT_CODE,FULL_NAME,TO_CHAR(NVL(TOTAL_NET+TOTAL_VAT,0),'999,999,999.99') "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B, "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,"+m_schema_name+".AF_CO_MAS_CLIENT D "+
			"WHERE  A.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND A.ACTIVE_STATUS=('"+m_status+"') AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"AND B.VENDOR_CODE=A.VENDER_CODE "+
			"AND D.CLIENT_CODE=C.CLIENT_CODE ");
			
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


					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	


	/*else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_payment_details")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
	
		 rs= stmt.executeQuery("SELECT DISTINCT CODE,DESCRIPTION "+ //,A.AA "+
			 "FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED, "+
			 "(SELECT DISTINCT ITEM_CAT_CODE AS AA FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE X,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED Y WHERE X.CODE=Y.CODE AND ENTITY_TYPE IS NULL)A "+
			 "WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS=('"+m_status+"') "+
			 "AND CODE IN "+
			 "(SELECT CODE "+
			 "FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			 "WHERE FROM_SCREEN_NO BETWEEN "+
			 "(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			 "WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CRO_PRO_PAYMENT_DETAILS') "+
			 "AND TO_SCREEN_NO  AND ACTIVE_STATUS=('"+m_status+"'))");
		*/				
		/*	 rs= stmt.executeQuery("	SELECT PURCHASE_ORDER_NO,A.APPLICATION_NO,FINANCE_NO,TOTAL_NET,TOTAL_VAT,VENDOR_CODE,NAME,D.CLIENT_CODE, "+
			"FULL_NAME,(TOTAL_NET+TOTAL_VAT)AS TOTAL "+
			"FROM LAKDL.AF_CR_PRO_PURCHASE_ORDER A,LAKDL.AF_CO_MAS_VENDORS B,LAKDL.AF_CO_PRO_APPLICATION_DETAILS C,LAKDL.AF_CO_MAS_CLIENT D "+
			"WHERE  A.PURCHASE_ORDER_NO LIKE UPPER('%') AND A.ACTIVE_STATUS=('Y') AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"AND B.VENDOR_CODE=A.VENDER_CODE "+
			"AND D.CLIENT_CODE=C.CLIENT_CODE ");
		*/	
		
	
		
			/*

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					//out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	

*/

else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_payment_details_invoice")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
	
		rs= stmt.executeQuery("SELECT PRO_INVOICE_NO,ENGINE_NO,CHASSIS_NO "+
		"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET "+
		"WHERE PURCHASE_ORDER_NO=('"+m_val+"') "+
		"AND ACTIVE_STATUS=('"+m_status+"')");

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




	
	
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_payment_details")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
	
		 rs= stmt.executeQuery("SELECT DISTINCT CODE,DESCRIPTION "+ //,A.AA "+
			 "FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED, "+
			 "(SELECT DISTINCT ITEM_CAT_CODE AS AA FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE X,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED Y WHERE X.CODE=Y.CODE AND ITEM_CAT_CODE IS NOT NULL)A "+
			 "WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS='Y' "+//('"+m_status+"') "+
			 "AND CODE IN "+
			 "(SELECT CODE "+
			 "FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			 "WHERE FROM_SCREEN_NO BETWEEN "+
			 "(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			 "WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CRO_PRO_PAYMENT_DETAILS') "+
			 "AND TO_SCREEN_NO  AND ACTIVE_STATUS='Y' )");//('"+m_status+"'))");

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					//out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	


		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Doc_details")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
																	
	
		 rs= stmt.executeQuery("SELECT DISTINCT CODE,DESCRIPTION "+ //,A.AA "+
			 "FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED, "+
			 "(SELECT DISTINCT ITEM_CAT_CODE AS AA FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE X,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED Y WHERE X.CODE=Y.CODE AND ITEM_CAT_CODE IS NOT NULL)A "+
			 "WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS=('Y') "+
			 "AND CODE IN "+
			 "(SELECT CODE "+
			 "FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			 "WHERE FROM_SCREEN_NO BETWEEN "+
			 "(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			 "WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CRO_PRO_PAYMENT_DETAILS') "+
			 "AND TO_SCREEN_NO  AND ACTIVE_STATUS=('Y'))");

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					//out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	
			
			//TEST-----
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Doc_details_test")){
				String m_val1 = req.getParameter("data_val2").trim();
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_screen = req.getParameter("screen").trim();
				String m_app_no = req.getParameter("m_app_no").trim();
			
			//out.println(m_screen);
			rs= stmt.executeQuery("SELECT	PRO_INVOICE_NO,DOCUMENT_TYPE,REPLACE(d1.DESCRIPTION,'&',' '),REMARK,STATUS , "+
			"D3.FROM_SCREEN_NO,D3.TO_SCREEN_NO "+
			"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT D2, "+
			""+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3 "+
			"WHERE DOC_APP_TYPE='ASSET' AND D1.ACTIVE_STATUS=('"+m_status+"') "+
			"AND PRODUCT_CODE=( "+
		  "SELECT "+
		  "TRANSACTION_TYPE "+
		  "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		  "WHERE APPLICATION_NO=UPPER('"+m_app_no+"')) "+
			"AND OTHER_NO=UPPER('"+m_val+"') "+
			"AND D1.CODE=DOCUMENT_TYPE "+
			"AND D1.CODE=D3.CODE "+
			"AND  (D1.CODE,ITEM_CAT_CODE) IN "+
			"(SELECT CODE,ITEM_CAT_CODE "+
			"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"WHERE (FROM_SCREEN_NO <= "+
			"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"') "+
			"AND TO_SCREEN_NO >= "+
			"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"')) "+
			"AND ITEM_CAT_CODE IN (SELECT "+
			""+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
			"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			"WHERE APPLICATION_NO=('"+m_app_no+"') AND PRO_INVOICE_NO=UPPER('"+m_val1+"')))"+
			
			"UNION "+
			"SELECT DISTINCT G.INV,D1.CODE,DESCRIPTION,NULL N1,NULL N2,D3.FROM_SCREEN_NO,D3.TO_SCREEN_NO "+//,ITEM_CAT_CODE "+
			"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3, "+
			"(SELECT PRO_INVOICE_NO INV FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
			"WHERE B.PURCHASE_ORDER_NO=UPPER('"+m_val+"') "+
			"AND B.PRO_INVOICE_NO=A.INVOICE_NO AND B.PRO_INVOICE_NO=UPPER('"+m_val1+"') "+
			"AND A.ACTIVE_STATUS=('"+m_status+"') )G "+
			"WHERE DOC_APP_TYPE='ASSET' AND D1.ACTIVE_STATUS=('"+m_status+"') "+
			"AND PRODUCT_CODE=( "+
	    "SELECT "+
	    "TRANSACTION_TYPE "+
	    "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
	    "WHERE APPLICATION_NO=UPPER('"+m_app_no+"')) "+
			"AND ITEM_CAT_CODE IS NOT NULL "+
			"AND D1.CODE NOT IN (SELECT "+
			"DISTINCT DOCUMENT_TYPE D "+
			"FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"WHERE OTHER_NO=UPPER('"+m_val+"') "+
			"AND PRO_INVOICE_NO=UPPER('"+m_val1+"')) "+
			"AND D1.CODE=D3.CODE "+
			"AND ITEM_CAT_CODE IN (SELECT "+
			""+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
			"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			"WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_val1+"')) "+
			"AND (D1.CODE,ITEM_CAT_CODE )IN "+
			"(SELECT CODE,ITEM_CAT_CODE "+
			"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"WHERE FROM_SCREEN_NO <= "+
			"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"') "+
			"AND TO_SCREEN_NO >= "+
			"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"')) "+
			"AND D1.ACTIVE_STATUS=('"+m_status+"') ORDER BY STATUS ASC");
						
		 out.print("<DATA>");
	   while(rs.next()){
		 out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2)+"</R2>");
		 out.print("<R3>"+rs.getString(3).replace('&','$')+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		 out.print("<R5>"+rs.getString(5)+"</R5>");
		 out.print("<R6>"+rs.getString(6)+"</R6>");
		 out.print("<R7>"+rs.getString(7)+"</R7>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
				
	     }	
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Asset_details")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_invoice=req.getParameter("inv").trim();
				
				String m_cr="";
				String m_dd="";
				String m_mm="";
				String m_yy="";

		

		rs= stmt.executeQuery("SELECT PRO_INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID,A.ENGINE_NO, "+ 
		"A.CHASSIS_NO ,NVL(REG_NO,'##') ,NVL(TO_CHAR(INSURANCE_DATE,'DD-MM-YYYY'),'##') "+
		",NVL(TO_CHAR(REVENUE_LICENSE_DATE,'DD-MM-YYYY'),'##'),  "+
    "NVL(TO_CHAR(LUXURY_TAX_DATE,'DD-MM-YYYY'),'##'),NVL(DISTRICT_CODE,'##'),NVL(TO_CHAR(DRIVING_LICENSE_DATE,'DD-MM-YYYY'),'##'),INVOICE_DOC_NO , "+
    "CR_BOOK_NO,TO_CHAR(CR_PRINT_DATE,'DD'),TO_CHAR(CR_PRINT_DATE,'MM'),TO_CHAR(CR_PRINT_DATE,'YYYY') "+
		"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
		"WHERE B.PURCHASE_ORDER_NO=('"+m_val+"') "+
    "AND B.PRO_INVOICE_NO=A.INVOICE_NO "+
		"AND A.ACTIVE_STATUS=('"+m_status+"')"+
		"AND B.PRO_INVOICE_NO like ('"+m_invoice+"%') "+
		"ORDER BY PRO_INVOICE_NO ASC ");

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
			
		 if(rs.getString(13)==null){
		 m_cr="";
		 }
		 else{
		 m_cr=rs.getString(13);
		 }
		 out.print("<R13>"+m_cr+"</R13>");
     //out.print("<R13>"+rs.getString(13)+"</R13>");
					 if(rs.getString(14)==null){
		 m_dd="";
		 }
		 else{
		 m_dd=rs.getString(14);
		 }
     out.print("<R14>"+m_dd+"</R14>");
		 if(rs.getString(15)==null){
		 m_mm="";
		 }
		 else{
		 m_mm=rs.getString(15);
		 }
     out.print("<R15>"+m_mm+"</R15>");
		 if(rs.getString(16)==null){
		 m_yy="";
		 }
		 else{
		 m_yy=rs.getString(16);
		 }

     out.print("<R16>"+m_yy+"</R16>");

     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }    

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_district")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT DISTRICT_CODE FROM "+m_schema_name+".AF_CO_MAS_DISTRICT "+
				" WHERE UPPER(DISTRICT_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
 	
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_othr_Doc_details_test")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_screen = req.getParameter("screen").trim();
				String m_app_no = req.getParameter("m_app_no").trim();

				rs= stmt.executeQuery("SELECT OTHER_NO T1,DOCUMENT_TYPE T2,D1.DESCRIPTION T3,REMARK T4,STATUS T5 , "+
				"D3.FROM_SCREEN_NO,D3.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT D2, "+
				""+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3 "+
				"WHERE DOC_APP_TYPE='CLIENT' AND d1.ACTIVE_STATUS=('"+m_status+"') "+
				"AND PRODUCT_CODE=( "+
			  "SELECT "+
			  "TRANSACTION_TYPE "+
			  "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			  "WHERE APPLICATION_NO=UPPER('"+m_app_no+"')) "+
					
				"and d1.code=d3.code "+
				"AND OTHER_NO LIKE UPPER('"+m_val+"%') "+
				"and d1.code=document_type "+
				"AND (d1.CODE,entity_type) IN (SELECT CODE,entity_type "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
				"where (FROM_SCREEN_NO <= "+
				"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
				"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"') "+
				"AND TO_SCREEN_NO  >= "+
				"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
				"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"')) "+
				"and entity_type in (SELECT ENTITY_CODE FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
				"WHERE ACTIVE_STATUS=('"+m_status+"') "+
				"AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY "+
				"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
				"WHERE A.CLIENT_CODE=B.CLIENT_CODE ) ))  "+
				
				"UNION all "+
				
				"SELECT DISTINCT null,D1.CODE,REPLACE(DESCRIPTION,'&'),NULL N1,NULL N2, "+
				"D3.FROM_SCREEN_NO,D3.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1, "+
				""+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3 "+
				"WHERE DOC_APP_TYPE='CLIENT' AND D1.ACTIVE_STATUS=('"+m_status+"') "+
				"AND PRODUCT_CODE=( "+
			  "SELECT "+
			  "TRANSACTION_TYPE "+
			  "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			  "WHERE APPLICATION_NO=UPPER('"+m_app_no+"')) "+
						
				"and d1.code=d3.code "+
				"AND d1.ACTIVE_STATUS=('"+m_status+"') "+
				"AND (d1.CODE,entity_type) IN "+
				"(SELECT CODE ,entity_type "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
				"where (FROM_SCREEN_NO <= "+
				"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
				"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"') "+
				"AND TO_SCREEN_NO  >= "+
				"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
				"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"')) "+
				"and entity_type in (SELECT ENTITY_CODE "+
				"FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
				"WHERE ACTIVE_STATUS=('"+m_status+"') AND ACTIVE_STATUS=('"+m_status+"') "+
				"AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY "+
				"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,LAKDL.AF_CO_MAS_CLIENT B "+
				"WHERE A.CLIENT_CODE=B.CLIENT_CODE ))) "+
				"and d1.code not in (SELECT d1.code "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT D2, "+
				""+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3 "+
				"WHERE DOC_APP_TYPE='CLIENT' AND d1.ACTIVE_STATUS=('"+m_status+"') "+
				"and d1.code=d3.code "+
				"AND OTHER_NO LIKE UPPER('"+m_val+"%') "+
				"and d1.code=document_type "+
				"AND (d1.CODE,entity_type) IN (SELECT CODE,entity_type "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
				"where (FROM_SCREEN_NO <=  "+
				"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"') "+
				"AND TO_SCREEN_NO  >= "+
				"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_screen+"')) "+
				"and entity_type in (SELECT ENTITY_CODE  "+
				"FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY  "+
				"WHERE ACTIVE_STATUS=('"+m_status+"') AND ACTIVE_STATUS=('"+m_status+"') "+
				"AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY   "+
				"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
				"WHERE A.CLIENT_CODE=B.CLIENT_CODE ) )) "+
				") ORDER BY T5 ASC");


	
	
	
		out.print("<DATA>");
    while(rs.next()){
   
		 out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2)+"</R2>");
		 out.print("<R3>"+rs.getString(3).replace('&','$')+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		 out.print("<R5>"+rs.getString(5)+"</R5>");
			out.print("<R6>"+rs.getString(6)+"</R6>");
		 out.print("<R7>"+rs.getString(7)+"</R7>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
}
	
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_othr_Doc_details")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();

				rs= stmt.executeQuery ("SELECT DISTINCT CODE,DESCRIPTION "+
 				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
 				"WHERE DOC_APP_TYPE='CLIENT' AND ACTIVE_STATUS=('"+m_status+"') "+
 				"AND CODE IN "+
			  "(SELECT CODE "+
 				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
 				"WHERE FROM_SCREEN_NO BETWEEN "+
 				"(SELECT "+
 				"POSITION "+
 				"FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 				"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_MAIN' "+
 				")AND TO_SCREEN_NO  AND ACTIVE_STATUS=('"+m_status+"') AND "+
 				"ENTITY_TYPE IN (SELECT "+
 				"ENTITY_CODE "+
 				"FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
 				"WHERE ACTIVE_STATUS=('"+m_status+"') AND ENTITY_CODE IN (SELECT DISTINCT DECODE(CLIENT_TYPE,'I','INDIVIDUAL','C','CORPORATE') "+
 				"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
 				"WHERE A.CLIENT_CODE=B.CLIENT_CODE))) ");
					
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					//out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }	


			  //---------------------Purpose  :Display Proforma Invoice Details for particular Application No-----------------------------------
   //---------------------Name     :Yohan---------------------------------------------------
   //---------------------Date     :19-09-2006--------------------------------------------------
   
  else if (m_chksql.trim().equals("m_pop_LAKDL_AF_CR_display_proforma_data")){
    
    String m_application_no = req.getParameter("application_no").trim();
    
    
    rs= stmt.executeQuery (" SELECT NVL(A.INVOICE_NO,'N/A'), NVL(A.ASSET_ID,'N/A'), NVL(A.PRICING_NO,'N/A'), "+
                           " NVL(B.DESCRIPTION,'N/A'), NVL(C.DESCRIPTION,'N/A'), "+
                           " NVL(A.NET_PRICE,0), NVL(A.VAT,0), NVL(A.TOTAL_AMOUNT,0), "+
                           " NVL(A.TO_BE_DELIVERD_TO,'N/A'), NVL(A.VALUE,0), NVL(A.ENGINE_NO,'N/A'), "+
                           " NVL(A.CHASSIS_NO,'N/A'), NVL(A.REG_NO,'N/A'), NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'N/A') "+
                           " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B ,"+m_schema_name+". AF_CO_MAS_SUB_MODLE C "+
                           " WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
                        " AND A.MODEL_CODE = B.MODEL_CODE "+
                        " AND A.SUB_MODEL_CODE = C.SUB_CODE "+
                        " AND A.MODEL_CODE=C.MODEL_CODE ");
    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
     out.print("<R2>"+rs.getString(2)+"</R2>");
     out.print("<R3>"+rs.getString(3)+"</R3>");
     out.print("<R4>"+rs.getString(4)+"</R4>");
     out.print("<R5>"+rs.getString(5)+"</R5>");
     out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
     out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");
     out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
     out.print("<R9>"+rs.getString(9)+"</R9>");
     out.print("<R10>"+nf.format(rs.getDouble(10))+"</R10>");
     out.print("<R11>"+rs.getString(11)+"</R11>");
     out.print("<R12>"+rs.getString(12)+"</R12>");
     out.print("<R13>"+rs.getString(13)+"</R13>");
     out.print("<R14>"+rs.getString(14)+"</R14>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }    
			
			
			   //---------------------Purpose  :Display Valuation Details for particular Application No-----------------------------------
   //---------------------Name     :Yohan---------------------------------------------------
   //---------------------Date     :19-09-2006--------------------------------------------------
   
 
  else if (m_chksql.trim().equals("m_pop_LAKDL_AF_CR_display_valuation_data")){
    
    String m_application_no = req.getParameter("application_no").trim();
    
    
    rs= stmt.executeQuery (" SELECT NVL(A.VALUATION_NO,'N/A'), NVL(A.ASSET_ID,'N/A'), NVL(B.DESCRIPTION,'N/A'), NVL(C.DESCRIPTION,'N/A'), "+
                           " NVL(A.REG_NO,'N/A'), NVL(A.ENGINE_NO,'N/A'), NVL(CHASSIS_NO,'N/A'),NVL(A.COLOUR,'N/A'), NVL(A.VALUE,0),"+
                           " NVL(A.REMARKS,'N/A'), NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'N/A'), NVL(TO_CHAR(A.VALUATION_DATE,'DD-MM-YYYY'),'N/A'), "+
              " NVL(TYPE_OF_BODY,'N/A'),NVL(METER_READING,0)"+
                           " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_MAS_MODEL B ,"+m_schema_name+". AF_CO_MAS_SUB_MODLE C "+
                           " WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
                        " AND A.MODEL_CODE = B.MODEL_CODE "+
                        " AND A.SUB_MODEL_CODE = C.SUB_CODE "+
                        " AND A.MODEL_CODE=C.MODEL_CODE ");
    
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
     out.print("<R9>"+nf.format(rs.getDouble(9))+"</R9>");
     out.print("<R10>"+rs.getString(10)+"</R10>");
     out.print("<R11>"+rs.getString(11)+"</R11>");
     out.print("<R12>"+rs.getString(12)+"</R12>");
     out.print("<R13>"+rs.getString(13)+"</R13>");
     out.print("<R14>"+nf.format(rs.getDouble(14))+"</R14>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }     
 
			
			
	 //---------------------Purpose  :Validate Application No-----------------------------------
   //--------------------- Name     :Yohan---------------------------------------------------
   //--------------------- Date     :20-09-2006--------------------------------------------------
   
 
  else if (m_chksql.trim().equals("m_pop_LAKDL_AF_CR_Val_application_no")){
    
    String m_application_no = req.getParameter("application_no").trim();
    
    
    rs= stmt.executeQuery (" SELECT NVL(APPLICATION_NO,'-'),NVL(CLIENT_CODE,'-') "+
                           " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
                           " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");
    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
     out.print("<R2>"+rs.getString(2)+"</R2>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }      
			
			
			 
       
			
			
			
			
			////////////////////Nuwan De Silva////////////////////////////////////////////////
			
		//Get The Branch ID.........................	
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_BRANCH_ID")){
    
    String m_val = req.getParameter("data_val").trim();
    String m_val2 = req.getParameter("data_val2").trim();   
    
    rs= stmt.executeQuery (" SELECT "+m_schema_name+".AF_CO_GET_SUP_BRANCH_ID('"+m_val+"','"+m_val2+"') BRANCH_ID FROM DUAL ");
              
              
     
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_doc_applicant")){
    
    String m_val1 = req.getParameter("data_val1").trim();
    String m_val2 = req.getParameter("data_val2").trim();   
		String m_val3 = req.getParameter("data_val3").trim();   
    
    rs= stmt.executeQuery (" SELECT "+
    "  DOCUMENT_TYPE, "+
		" "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),"+
    "  STATUS, "+
    "  REMARK "+
    "  FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
    "  WHERE APPLICATION_NO=UPPER('"+m_val1+"') AND PRO_INVOICE_NO='"+m_val2+"'  AND DOCUMENT_TYPE ='"+m_val3+"' ");

              
     
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
			
			
			
			
			//...............................................
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_curr_code")){
    
    String m_val = req.getParameter("data_val").trim();
	  String m_val2 = req.getParameter("data_val2").trim();   
       
    rs= stmt.executeQuery (" SELECT "+
    " CURR_CODE, "+
    " EXCHANGE_RATE "+
    " FROM "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
    " WHERE  CURR_CODE="+
		" (SELECT "+
    " CURR_CODE "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
    " WHERE APPLICATION_NO=UPPER('"+m_val+"') AND  INVOICE_NO=UPPER('"+m_val2+"') )");
		
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
			 out.print("<R2>"+rs.getString(2)+"</R2>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }
			
			
			
			 else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val")){
    
    String m_val = req.getParameter("data_val").trim();
    String m_status = req.getParameter("ac_status").trim();
    
 														
	/* 	rs= stmt.executeQuery (" SELECT "+
         " INVOICE_NO, "+
         " (NET_PRICE+VAT) GROSS, "+
         " VAT, "+
         " NET_PRICE "+
         " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
         " WHERE INVOICE_NO IN "+
         " (SELECT  PRO_INVOICE_NO         "+
         " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT"+
         " WHERE OTHER_NO=UPPER('"+m_val+"'))");
	*/
					
		rs= stmt.executeQuery (" SELECT  "+
          " INVOICE_NO, "+
          " (NET_PRICE+VAT) GROSS, "+
          " VAT, "+
          " NET_PRICE, "+
					" NVL(CAPITAL_ALLOWANCE,0) CAPITAL_ALLOWANCE, "+
					" NVL(ENGINE_NO,'-') ENGINE_NO, "+
					" NVL(CHASSIS_NO,'-') CHASSIS_NO, "+
					" NVL(ASSET_ID,'-') ASSET_ID "+
          " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
          " WHERE INVOICE_NO IN "+
          " (SELECT PRO_INVOICE_NO "+
          " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET "+
          " WHERE PURCHASE_ORDER_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"'))");
					

    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
     out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");
		 out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
     out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
		 out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
		 out.print("<R6>"+rs.getString(6)+"</R6>");
		 out.print("<R7>"+rs.getString(7)+"</R7>");
		 out.print("<R8>"+rs.getString(8)+"</R8>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }      
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_edit_doc")){
    
    String m_val = req.getParameter("data_val").trim();
    String m_val2 = req.getParameter("data_val2").trim();  
    
 								
			 			
								
 rs= stmt.executeQuery (" SELECT "+
 " DOCUMENT_TYPE, "+
 " "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),"+
 " NVL(REMARK,'-'), "+
 " STATUS "+
 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
 " WHERE OTHER_NO=UPPER('"+m_val+"') AND  PRO_INVOICE_NO=UPPER('"+m_val2+"') ");
					

    
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
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_doc_entity_edit")){
    
    String m_val = req.getParameter("data_val").trim();
  
  								
 				
       rs= stmt.executeQuery (" SELECT "+
              " DOCUMENT_TYPE, "+
							" "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),"+
              " NVL(REMARK,'-'), "+
							" STATUS "+ 
              " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
              " WHERE  OTHER_NO=UPPER('"+m_val+"') AND PRO_INVOICE_NO IS NULL ");
    
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
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_invoice_no_doc")){
    
    String m_val = req.getParameter("data_val").trim();
    								
rs= stmt.executeQuery (" SELECT "+
" INVOICE_NO, "+
" (NET_PRICE+VAT) GROSS, "+
" VAT, "+
" NET_PRICE "+
" FROM LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS  "+
" WHERE INVOICE_NO IN( "+
" SELECT "+
" PRO_INVOICE_NO "+
" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET "+
" WHERE PURCHASE_ORDER_NO=UPPER('"+m_val+"')  AND  PRO_INVOICE_NO  NOT IN (SELECT "+
" NVL(PRO_INVOICE_NO ,'-') "+
" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT WHERE OTHER_NO=UPPER('"+m_val+"')))");

    
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_show_invocie_doc")){
    
    String m_val = req.getParameter("data_val").trim();
  
  								
				rs= stmt.executeQuery (" SELECT "+
         " INVOICE_NO, "+
         " (NET_PRICE+VAT) GROSS, "+
         " VAT, "+
         " NET_PRICE "+
         " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
         " WHERE INVOICE_NO=UPPER('"+m_val+"')");

    
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
			
			/*--------------------------------------------------------------------------------------
			used In :Purchase Order
			Purpose :Validate The Vendors
			
			----------------------------------------------------------------------------------------*/
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_supplier")){
    
    String m_val = req.getParameter("data_val").trim();
      String m_val2 = req.getParameter("data_val2").trim();
			  String m_status = req.getParameter("ac_status").trim();
  								
					
		rs= stmt.executeQuery (" SELECT "+
    " VENDOR_CODE,NAME "+
		" FROM "+m_schema_name+".AF_CO_MAS_VENDORS A "+
    " WHERE A.VENDOR_CODE IN  "+
    " (SELECT "+
    " B.VENDOR_CODE "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
    " WHERE UPPER(B.APPLICATION_NO) =UPPER('"+m_val+"') )  "+
    " AND UPPER(A.VENDOR_CODE) =UPPER('"+m_val2+"') AND A.ACTIVE_STATUS=('"+m_status+"') ");
	

    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2)+"</R2>");
		
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
			
			
			
		
			
			//Nuwan de silva------------------------------------------------------------------------------------------------
			//Get THE Documents.............................................................................................
			
			
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_get_documents")){
    
    String m_app_no = req.getParameter("data_val").trim();
    String m_inv_no = req.getParameter("data_val2").trim();
		String m_status = req.getParameter("ac_status").trim();

  
  	
/* rs= stmt.executeQuery (" SELECT "+
 " DOCUMENT_TYPE,D1.DESCRIPTION,STATUS,REMARK "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT D2, "+
 " (SELECT DISTINCT CODE C,DESCRIPTION D,NVL(NULL,'-') N1,NVL(NULL,'-') N2 "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED, "+
 " (SELECT DISTINCT ITEM_CAT_CODE AA FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE X, "+
 " "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED Y WHERE X.CODE=Y.CODE AND ITEM_CAT_CODE IS NOT NULL "+ 
 " AND "+
 " ITEM_CAT_CODE=(SELECT "+
 " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"') "+
 " ) ) A "+
	
 " WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS=('"+m_status+"') "+
 " AND CODE IN "+
 " (SELECT CODE "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
 " WHERE FROM_SCREEN_NO <= "+
 " (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
 " AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND "+
 " ITEM_CAT_CODE=(SELECT "+
 " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"')) "+
 
 " AND PRODUCT_CODE=( "+
 " SELECT "+
 " TRANSACTION_TYPE  "+
 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 " WHERE APPLICATION_NO=UPPER('"+m_app_no+"'))	 "+	
	
 " AND ACTIVE_STATUS=('"+m_status+"'))) X "+
	
 " WHERE DOCUMENT_TYPE <>X.C "+
 " AND APPLICATION_NO=UPPER('"+m_app_no+"') "+ 
 " AND PRO_INVOICE_NO=UPPER('"+m_inv_no+"') "+ 
 " AND D1.code=DOCUMENT_TYPE "+
	
 " UNION "+
	
 " SELECT DISTINCT CODE,DESCRIPTION,NVL(NULL,'-'),NVL(NULL,'-') "+ 
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED, "+
 " (SELECT DISTINCT ITEM_CAT_CODE AA FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE X, "+
 " "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED Y WHERE X.CODE=Y.CODE AND ITEM_CAT_CODE IS NOT NULL "+
 " AND "+
 " ITEM_CAT_CODE=(SELECT "+
 " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"') "+
 " ) ) A "+
 " WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS=('"+m_status+"') "+
 " AND CODE IN "+
 " (SELECT CODE "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
 " WHERE FROM_SCREEN_NO <= "+
 " (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
 " AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND "+
 
 " ITEM_CAT_CODE=(SELECT "+

 " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"')) "+
 " AND PRODUCT_CODE=( "+
 " SELECT "+
 " TRANSACTION_TYPE  "+
 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 " WHERE APPLICATION_NO=UPPER('"+m_app_no+"'))	 "+	

 " AND ACTIVE_STATUS=('"+m_status+"')) "+
 " AND CODE NOT IN (SELECT "+
 " DISTINCT DOCUMENT_TYPE D "+
 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
 " WHERE APPLICATION_NO=UPPER('"+m_app_no+"')  "+ 
 " AND PRO_INVOICE_NO=UPPER('"+m_inv_no+"')) ");  
	
	*/
	
	
	
	
	
	
	rs= stmt.executeQuery (" SELECT DISTINCT A.CODE C,DESCRIPTION D,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS,B.TO_SCREEN_NO  "+
  " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B "+
  " WHERE  "+
  " B.ITEM_CAT_CODE=(SELECT  "+
  " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+ 
  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
  " WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"')  "+
  " ) "+
  " AND  "+
  " DOC_APP_TYPE='ASSET' AND A.ACTIVE_STATUS=('"+m_status+"')  "+
  " AND A.CODE IN  "+
  " (SELECT CODE  "+
  " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
  " WHERE FROM_SCREEN_NO <=  "+
  " (SELECT POSITION FROM LAKDL.CO_CO_MAS_USER_SCREEN  "+
  " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER')  "+
  " AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
  " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND  "+
  " ITEM_CAT_CODE=(SELECT  "+
  " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
  " WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"'))  "+
  " AND PRODUCT_CODE=(  "+
  " SELECT  "+
  " TRANSACTION_TYPE   "+
  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+ 
  " WHERE APPLICATION_NO=UPPER('"+m_app_no+"'))	 	 "+
  " AND B.CODE=A.CODE	"+
  " AND ACTIVE_STATUS=('"+m_status+"'))"+
	" AND A.CODE NOT IN( "+
  " SELECT DOCUMENT_TYPE "+ 
  " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
  " WHERE APPLICATION_NO=UPPER('"+m_app_no+"')    "+
  " AND PRO_INVOICE_NO=UPPER('"+m_inv_no+"')"+
  " ) "+
	
	" UNION "+
	
	" SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK,NVL(C.STATUS,'-') STATUS, B.TO_SCREEN_NO "+
  " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C "+
  " WHERE "+
  " B.ITEM_CAT_CODE=(SELECT "+
  " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
  " WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"')) "+
  "  AND "+
  " DOC_APP_TYPE='ASSET' AND A.ACTIVE_STATUS=('"+m_status+"') "+
  " AND C.DOCUMENT_TYPE IN "+
  " (SELECT CODE "+
  " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
  " WHERE FROM_SCREEN_NO <= "+
  " (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
  " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
  " AND TO_SCREEN_NO >=(SELECT POSITION FROM LAKDL.CO_CO_MAS_USER_SCREEN "+
  " WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND "+
  " ITEM_CAT_CODE=(SELECT "+
  " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
  " WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"')) "+
  " AND PRODUCT_CODE=( "+
  " SELECT "+
  " TRANSACTION_TYPE "+ 
  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
  " WHERE APPLICATION_NO=UPPER('"+m_app_no+"')) "+
  " AND B.CODE=A.CODE "+
  " AND ACTIVE_STATUS=('"+m_status+"')) "+
  " AND C.DOCUMENT_TYPE =A.CODE "+
  " AND C.APPLICATION_NO=UPPER('"+m_app_no+"')  "+
  " AND C.PRO_INVOICE_NO=UPPER('"+m_inv_no+"')  "+
	" ORDER BY STATUS ASC " );

	
	

    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2).replace('&','$')+"</R2>");
		 out.print("<R3>"+rs.getString(3)+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		 out.print("<R5>"+rs.getString(5)+"</R5>");
		
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   //---------------------------------------------------------------------------------------------------------------------------
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_get_documents_edit")){
    
    String m_pur_ord_no = req.getParameter("data_val").trim();
    String m_inv_no     = req.getParameter("data_val2").trim();
		String m_status     = req.getParameter("ac_status").trim();
		String m_app_no     = req.getParameter("data_val3").trim();

 rs= stmt.executeQuery (" SELECT "+  
" PRO_INVOICE_NO,DOCUMENT_TYPE,d1.DESCRIPTION,NVL(REMARK,'-'),STATUS,NVL(FOLLOWUP_REMARKS,'-'),NVL(REF_NO,'-') REF_NO "+
" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT D2, "+
" (SELECT DISTINCT G.INV AS INV1,CODE C,DESCRIPTION D,NVL(NULL,'-') N1,NVL(NULL,'-') N2 "+
" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED, "+
" (SELECT PRO_INVOICE_NO INV FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
" WHERE B.PURCHASE_ORDER_NO=UPPER('"+m_pur_ord_no+"') "+
" AND B.PRO_INVOICE_NO=A.INVOICE_NO AND B.PRO_INVOICE_NO=UPPER('"+m_inv_no+"') "+
" AND A.ACTIVE_STATUS=('"+m_status+"'))G, "+
" (SELECT DISTINCT ITEM_CAT_CODE AS AA FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE X, "+
" "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED Y WHERE X.CODE=Y.CODE AND ITEM_CAT_CODE IS NOT NULL "+
 " AND "+
 " ITEM_CAT_CODE IN (SELECT "+
 " "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"') "+
 " ) ) A "+
" WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS=('"+m_status+"') "+
" AND CODE IN "+
" (SELECT CODE "+
" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
" WHERE FROM_SCREEN_NO <= "+
" (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
" WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
" AND TO_SCREEN_NO >="+
" (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
" WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND "+
" ITEM_CAT_CODE IN (SELECT "+
" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"')) "+
	
 "   AND PRODUCT_CODE=( "+
 "  SELECT "+
 "  TRANSACTION_TYPE  "+
 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 " WHERE APPLICATION_NO=UPPER('"+m_app_no+"'))	 "+
	
	
" AND ACTIVE_STATUS=('"+m_status+"')))X "+
" WHERE DOCUMENT_TYPE <>X.C AND "+
" OTHER_NO=UPPER('"+m_pur_ord_no+"') "+
" AND PRO_INVOICE_NO=INV1 AND PRO_INVOICE_NO=UPPER('"+m_inv_no+"') "+
" AND D1.code=DOCUMENT_TYPE "+

" UNION "+

" SELECT DISTINCT G.INV,CODE,DESCRIPTION,NVL(NULL,'-') N1,NVL(NULL,'-') N2,NVL(NULL,'-') N3,NVL(NULL,'-') N4 "+
" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED, "+
" (SELECT PRO_INVOICE_NO INV FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B "+
" WHERE B.PURCHASE_ORDER_NO=UPPER('"+m_pur_ord_no+"') "+
" AND B.PRO_INVOICE_NO=A.INVOICE_NO AND B.PRO_INVOICE_NO=UPPER('"+m_inv_no+"') "+
" AND A.ACTIVE_STATUS=('"+m_status+"') ) G, "+
" (SELECT DISTINCT ITEM_CAT_CODE AS AA FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE X, "+
" "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED Y WHERE X.CODE=Y.CODE AND ITEM_CAT_CODE IS NOT NULL "+
" AND "+
" ITEM_CAT_CODE IN (SELECT "+
" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"') "+
" ) ) A "+
" WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS=('"+m_status+"') "+
" AND CODE IN "+
" (SELECT CODE "+
" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
" WHERE FROM_SCREEN_NO <= "+
" (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
" WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') "+
" AND TO_SCREEN_NO >= "+
" (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
" WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER') AND "+
" ITEM_CAT_CODE IN (SELECT "+
" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
 " WHERE APPLICATION_NO=('"+m_app_no+"') AND INVOICE_NO=UPPER('"+m_inv_no+"')) "+
	
	 "   AND PRODUCT_CODE=( "+
 "  SELECT "+
 "  TRANSACTION_TYPE  "+
 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 " WHERE APPLICATION_NO=UPPER('"+m_app_no+"'))	 "+
	
	
" AND ACTIVE_STATUS=('"+m_status+"')) "+
" AND CODE NOT IN (SELECT "+
" DISTINCT DOCUMENT_TYPE D "+
" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+ 
" WHERE OTHER_NO=UPPER('"+m_pur_ord_no+"') "+
" AND PRO_INVOICE_NO=G.INV AND PRO_INVOICE_NO=UPPER('"+m_inv_no+"')) ");

 
	
	

    
    out.print("<DATA>");
    while(rs.next()){
   
		 out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2)+"</R2>");
		 out.print("<R3>"+rs.getString(3).replace('&','$')+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		 out.print("<R5>"+rs.getString(5)+"</R5>");
		 out.print("<R6>"+rs.getString(6)+"</R6>");
		 out.print("<R7>"+rs.getString(7)+"</R7>");	
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
    }   
		//---------------------------------------------------------------------------------------------------------------------------------
			
			

	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_validate")){


    String m_val = req.getParameter("data_val").trim();
    String m_status1     = req.getParameter("ac_status").trim();
    String m_status2     = req.getParameter("ac_status2").trim();

  /*  rs= stmt.executeQuery (" SELECT "+ 
    " PURCHASE_ORDER_NO, "+
    " APPLICATION_NO, "+
    " VENDER_CODE, "+
		" "+m_schema_name+".AF_CO_GET_SUP_BRANCH_ID(APPLICATION_NO,VENDER_CODE) BRANCH_ID "+
    " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
    " WHERE PURCHASE_ORDER_NO=UPPER('"+m_pur_ord_no+"') AND ACTIVE_STATUS=('"+m_status+"')");
		*/
		
		
  rs= stmt.executeQuery (" SELECT  "+
" A.PURCHASE_ORDER_NO,A.APPLICATION_NO,A.VENDER_CODE,"+m_schema_name+".AF_CO_GET_VEN_NAME(A.VENDER_CODE) VENDER_NAME,A.BRANCH_CODE,(SELECT LOCATION_CODE FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION WHERE UPPER(BRANCH)=UPPER(A.BRANCH_CODE)) LOCATION_CODE,B.CLIENT_CODE,B.FULL_NAME,B.TEL_NO,B.NIC_NO "+
" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
" (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+
" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V "+
" WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
" AND V.ACTIVE_STATUS=('"+m_status2+"') "+
" ) B "+
" WHERE A.APPLICATION_NO =B.APPLICATION_NO "+
" AND  "+
" (UPPER(B.FULL_NAME) =UPPER('"+m_val+"') OR "+
" UPPER(B.CLIENT_CODE) =UPPER('"+m_val+"') OR "+
" B.TEL_NO =UPPER('"+m_val+"') OR "+
" B.NIC_NO  =UPPER('"+m_val+"') OR "+
" A.PURCHASE_ORDER_NO =UPPER('"+m_val+"') "+
" ) "+
" AND A.ACTIVE_STATUS=('"+m_status1+"') "+
" ORDER BY A.PURCHASE_ORDER_NO DESC ");



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
		 out.print("<R10>"+rs.getString(9)+"</R10>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
    } 
		
		
		
		
		
		
		
		
		
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_validate_view_Letter")){


    String m_pur_ord_no = req.getParameter("data_val").trim();
    String m_status     = req.getParameter("ac_status").trim();
		String m_status2     = req.getParameter("ac_status2").trim();
  //  String m_status2     = req.getParameter("ac_status2").trim();

  /*  rs= stmt.executeQuery (" SELECT "+ 
    " PURCHASE_ORDER_NO, "+
    " APPLICATION_NO, "+
    " VENDER_CODE, "+
		" "+m_schema_name+".AF_CO_GET_SUP_BRANCH_ID(APPLICATION_NO,VENDER_CODE) BRANCH_ID "+
    " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
    " WHERE PURCHASE_ORDER_NO=UPPER('"+m_pur_ord_no+"') AND ACTIVE_STATUS=('"+m_status+"')");
		
	*/	
		
rs= stmt.executeQuery (" SELECT "+ 
" A.PURCHASE_ORDER_NO PURCHASE_ORDER_NO , "+ 
" A.APPLICATION_NO APPLICATION_NO, "+ 
" A.VENDER_CODE VENDER_CODE, "+
" "+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE) VENDER_NAME,"+
" A.BRANCH_CODE BRANCH_CODE, "+
" NVL((TO_CHAR(A.PRINTED_DATE,'DD-MM-YYYY')),'-') PRINTED_DATE  "+

" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
" (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+
" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V "+
" WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
" AND V.ACTIVE_STATUS=('"+m_status2+"') "+
" ) B "+
" WHERE A.APPLICATION_NO =B.APPLICATION_NO "+
" AND  "+
" (UPPER(B.FULL_NAME)=UPPER('"+m_pur_ord_no+"') OR "+
" UPPER(B.CLIENT_CODE)=UPPER('"+m_pur_ord_no+"') OR "+
" B.TEL_NO=UPPER('"+m_pur_ord_no+"') OR "+
" B.NIC_NO=UPPER('"+m_pur_ord_no+"') OR "+
" A.PURCHASE_ORDER_NO=UPPER('"+m_pur_ord_no+"') "+
" ) "+
" AND A.ACTIVE_STATUS=('"+m_status+"') "+
" ORDER BY A.PURCHASE_ORDER_NO DESC ");



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
		
		
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_invoice_val")){
			String m_val = req.getParameter("data_val").trim();
			String m_val2 = req.getParameter("data_val2").trim();
			String m_val3 = req.getParameter("data_val3").trim();
			String m_status = req.getParameter("ac_status");
												
     rs= stmt.executeQuery (" SELECT "+
    " A.INVOICE_NO, "+
		" (A.NET_PRICE + A.VAT) GROSS_AMOUNT ,"+
    " A.VAT ,"+
	  " A.NET_PRICE "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
    " WHERE A.APPLICATION_NO IN "+
    " (SELECT "+
    " APPLICATION_NO "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
    " WHERE  SUPPLIER LIKE UPPER('"+m_val+"%') AND APPLICATION_NO LIKE UPPER('"+m_val2+"%')) AND A.INVOICE_NO LIKE UPPER('"+m_val3+"%') AND A.ACTIVE_STATUS=('"+m_status+"') ");
    
 
				
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
			
					else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_invoice_validate_display")){
			String m_val = req.getParameter("data_val").trim();
			String m_val2 = req.getParameter("data_val2").trim();
			String m_val3 = req.getParameter("data_val3").trim();
			String m_status = req.getParameter("ac_status");
												
  	
	
	rs= stmt.executeQuery (" SELECT "+
    " A.INVOICE_NO, "+
    " A.ASSET_ID, "+
    " NVL(A.ENGINE_NO,'-')  ENGINE_NO, "+
    " NVL(A.CHASSIS_NO,'-') CHASSIS_NO, "+
    " A.NET_PRICE, "+
    " A.VAT, "+
    " (A.NET_PRICE + A.VAT) GROSS_AMOUNT, "+
		//"  NVL(B.CAPITAL_ALLOWANCE,0) CAPITAL_ALLOWANCE   "+
		" DECODE(C.TRANSACTION_TYPE,'FINLEASE',B.CAPITAL_ALLOWANCE,0) CAPITAL_ALLOWANCE "+
		
	  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C   "+
    " WHERE  VENDOR_CODE LIKE UPPER('"+m_val+"%') AND "+
		" A.APPLICATION_NO=C.APPLICATION_NO AND"+
    " A.APPLICATION_NO LIKE UPPER('"+m_val2+"%') AND "+
    "  A.INVOICE_NO =UPPER('"+m_val3+"') AND "+ 
    "  A.ACTIVE_STATUS=('"+m_status+"') AND "+ 
  "   A.CURR_CODE IN "+
   "  (SELECT CURR_CODE FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
   "  WHERE INVOICE_NO =UPPER('"+m_val3+"'))  "+
  "   AND A.INVOICE_NO NOT IN(SELECT  "+
  " 	DISTINCT  PRO_INVOICE_NO  "+
  "   FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET  "+
  "   WHERE PURCHASE_ORDER_NO IN  "+
  "   (SELECT  "+
  "   PURCHASE_ORDER_NO  "+
  "   FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER  "+
  "   WHERE   VENDER_CODE LIKE UPPER('"+m_val+"%'))) AND "+
	"   B.ITEM_SUB_CAT=  "+
  "      (SELECT ITEM_SUB_CAT "+
  "             FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
  "             WHERE  MODEL_CODE=( SELECT MODEL_CODE "+
  "                    FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+ 
  "                    WHERE "+
  "                    APPLICATION_NO LIKE UPPER('"+m_val2+"%') AND "+
  "                    INVOICE_NO=A.INVOICE_NO )) AND "+
  "     B.ACTIVE_STATUS=('"+m_status+"')   ");
		
		
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
					out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");
          out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			
			
			
	
			
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				//String m_status2 = req.getParameter("ac_status2");
				 				
				
					rs= stmt.executeQuery ("SELECT APPLICATION_NO,NVL(FACILITY_NO,'-'),CLIENT_CODE,NVL(CO_APPLICANT,'-'),NVL(INQUARY_NO,'-'),"+
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE "+
														    "	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
														    "	 WHERE APPLICATION_NO =('"+m_val+"') AND APPLICATION_STATUS='"+m_status+"'  "); 
														
														
					
    

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
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			/*--------------------------------------------------------------------------------------------
			
			Purpose          :Validate Application Number
			Used In          :Purchase Order 
			Created by       :Nuwan De Silva
			-----------------------------------------------------------------------------------------------*/
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_CRO_PRO_Application_no_validation")){
			
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_status2 = req.getParameter("ac_status2");
						
														
														
			rs= stmt.executeQuery ("  SELECT   "+
			"  A.APPLICATION_NO APPLICATION_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
			"  (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  AND V.ACTIVE_STATUS=('"+m_status2+"')  "+
			"  ) B  "+
			"  WHERE A.APPLICATION_NO =B.APPLICATION_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) =UPPER('"+m_val+"') OR  "+
			"  UPPER(B.CLIENT_CODE) =UPPER('"+m_val+"') OR  "+
			"  B.TEL_NO = UPPER('"+m_val+"') OR  "+
			"  B.NIC_NO = UPPER('"+m_val+"') OR  "+
			"  A.APPLICATION_NO=UPPER('"+m_val+"')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS=('"+m_status+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  ");
    

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			


			
			
			
			
			//---------------------------------------------------------------------------------------------------------------------------
			
			
					else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_get_entity_doc_edit")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_val2 = req.getParameter("data_val2").trim();

	
	
 rs= stmt.executeQuery(" SELECT DISTINCT NULL,D1.CODE,REPLACE(DESCRIPTION,'&'),NVL(NULL,'-') N1,NVL(NULL,'-') N2,NVL(NULL,'-') N3,NVL(NULL,'-') N4  "+
		
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1, "+
 " "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3 "+
 " WHERE DOC_APP_TYPE='CLIENT' AND D1.ACTIVE_STATUS=('"+m_status+"') "+
 " AND D1.CODE IN "+
 " (SELECT CODE "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
	
 "  WHERE  ENTITY_TYPE IN (SELECT  "+
 "      ENTITY_CODE  "+
 "      FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY  "+
 "      WHERE ACTIVE_STATUS=('Y') AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY "+
 "      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
 "      WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_NO=UPPER('"+m_val2+"'))) "+
           
 "      AND FROM_SCREEN_NO <=  "+
 "      (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
 "      WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER')  "+
 "      AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
 "      WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER')	 "+
	
 "   AND PRODUCT_CODE=( "+
 "  SELECT "+
 "  TRANSACTION_TYPE  "+
 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 " WHERE APPLICATION_NO=UPPER('"+m_val2+"'))	 "+
	
 " AND ACTIVE_STATUS=('"+m_status+"')) "+
 " AND D1.CODE NOT IN (SELECT "+
 " DISTINCT DOCUMENT_TYPE D "+
 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
 " WHERE OTHER_NO LIKE UPPER('"+m_val+"%') "+
 " AND D1.CODE=D3.CODE "+
 " AND D3.ITEM_CAT_CODE IS NULL "+
 " AND PRO_INVOICE_NO IS NULL) "+
    
 " AND D1.CODE NOT IN( "+
 " SELECT T.T2 AS DV2 FROM (SELECT DOCUMENT_TYPE T2 "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT D2, "+
 " "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3, "+
 " (SELECT DISTINCT NULL,CODE C,DESCRIPTION D,NVL(NULL,'-') N1,NVL(NULL,'-') N2 "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
 " WHERE DOC_APP_TYPE='CLIENT' AND ACTIVE_STATUS=('"+m_status+"') "+
 " AND CODE IN "+
 " (SELECT CODE "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
	
 "      WHERE FROM_SCREEN_NO <=  "+
 "      (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
 "      WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER')  "+
 "      AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
 "      WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER')	 "+
	
 "   AND PRODUCT_CODE=( "+
 "  SELECT "+
 "  TRANSACTION_TYPE  "+
 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 " WHERE APPLICATION_NO=UPPER('"+m_val2+"'))	 "+
	
 "	AND ACTIVE_STATUS=('"+m_status+"')))X "+
 //" WHERE DOCUMENT_TYPE <>X.C "+
 " WHERE OTHER_NO LIKE UPPER('"+m_val+"%') "+ //AND
 " AND D1.CODE=DOCUMENT_TYPE "+
 " AND D1.CODE=D3.CODE "+
 " AND D3.ITEM_CAT_CODE IS NULL "+
   
 " AND D2.PRO_INVOICE_NO IS NULL)T ) "+
	
 " UNION "+
	 
 " SELECT OTHER_NO T1,DOCUMENT_TYPE T2,D1.DESCRIPTION T3,NVL(REMARK,'-') T4,STATUS T5,NVL(FOLLOWUP_REMARKS,'-') T6,NVL(REF_NO,'-') T7 "+
	
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED D1,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT D2, "+
 " "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE D3, "+
 " (SELECT DISTINCT NULL,CODE C,DESCRIPTION D,NVL(NULL,'-') N1,NVL(NULL,'-') N2 "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
 " WHERE DOC_APP_TYPE='CLIENT' AND ACTIVE_STATUS=('"+m_status+"') "+
 " AND CODE IN "+
 " (SELECT CODE "+
 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
	
 "  WHERE  ENTITY_TYPE IN (SELECT  "+
 "      ENTITY_CODE  "+
 "      FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY  "+
 "      WHERE ACTIVE_STATUS=('Y') AND ENTITY_CODE IN (SELECT DISTINCT CLIENT_CATEGORY    "+
 "      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
 "      WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_NO=UPPER('"+m_val2+"'))) "+
           
 "      AND FROM_SCREEN_NO <=  "+
 "      (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
 "      WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER')  "+
 "      AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
 "      WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PURCHASE_ORDER')	 "+
	
 "   AND PRODUCT_CODE=( "+
 "  SELECT "+
 "  TRANSACTION_TYPE  "+
 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 " WHERE APPLICATION_NO=UPPER('"+m_val2+"'))	 "+

 " AND ACTIVE_STATUS=('"+m_status+"')))X "+
 //" WHERE DOCUMENT_TYPE <>X.C AND "+
 " WHERE OTHER_NO LIKE UPPER('"+m_val+"%') "+ //AND
 " AND D1.CODE=DOCUMENT_TYPE "+
 " AND D1.CODE=D3.CODE "+
 " AND D3.ITEM_CAT_CODE IS NULL "+
 " AND D2.PRO_INVOICE_NO IS NULL ");
	
	
		
	out.print("<DATA>");
    while(rs.next()){
   
		 out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2)+"</R2>");
		 out.print("<R3>"+rs.getString(3).replace('&','$')+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		 out.print("<R5>"+rs.getString(5)+"</R5>");
		 out.print("<R6>"+rs.getString(6)+"</R6>");
		 out.print("<R7>"+rs.getString(7)+"</R7>");
		//	out.print("<R8>"+rs.getString(8)+"</R8>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
}
			
		//------------------------------------------------------------------------------------------
		//---------------------Purpose 	:Payment Main Screen (Payment Screen)-----------------------
		//---------------------Name     :Delanjali--------------------------------------------------
		//---------------------Date     :25-10-2006--------------------------------------------------

else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_temp_sort")){
			String m_val = req.getParameter("data_val").trim();
			String m_val1 = req.getParameter("data_val1").trim();

			String m_status = req.getParameter("ac_status").trim();
			
			String m_order = req.getParameter("sort_column").trim();
			String m_type = req.getParameter("order_by_type").trim();
			
			if(m_order=="PURCHASE_ORDER_NO"){
			m_order="E.PURCHASE_ORDER_NO";
			}
					
			//(2007-03-20)------------------------------------------------------------------------	
			
			
			rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,PRO_INVOICE_NO,NVL(E.ENGINE_NO,'-'),NVL(E.CHASSSIS_NO,'-'),NVL(E.REG_NO,'-'),TO_CHAR(A.PURCHASE_ORDER_DATE,'dd-mm-yyyy') PURCHASE_ORDER_DATE "+ 
			",NVL(F.ENGINE_NO,'-'),NVL(F.CHASSIS_NO,'-'),NVL(F.REG_NO,'-'),'"+m_order+"','"+m_type+"','','' "+//,REQU_NO,nvl(TO_CHAR(SETTLE_AMT,'999,999,999,999,999,999,999,999.99'),0) "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CR_PRO_PURORDER_APP_DETAIL E,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS F "+ 
			"WHERE E.PURCHASE_ORDER_NO=a.PURCHASE_ORDER_NO "+ 
			"AND A.PURCHASE_ORDER_NO  LIKE UPPER('"+m_val+"%') "+
			"AND A.ACTIVE_STATUS='"+m_val1+"' "+
      "AND INVOICE_NO=PRO_INVOICE_NO "+
			"ORDER BY "+m_order+" "+m_type+" ");	
				
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
				//out.print(m_val1);
				out.print("</DATA>");
			
     }
					
	

else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_temp")){
			String m_val = req.getParameter("data_val").trim();
			String m_val1 = req.getParameter("data_val1").trim();

			String m_status = req.getParameter("ac_status").trim();
					
					
					
			rs= stmt.executeQuery ("SELECT A.PURCHASE_ORDER_NO,PRO_INVOICE_NO,NVL(E.ENGINE_NO,'-'),NVL(E.CHASSSIS_NO,'-'),NVL(E.REG_NO,'-'),TO_CHAR(A.PURCHASE_ORDER_DATE,'dd-mm-yyyy') PURCHASE_ORDER_DATE "+ 
			",NVL(F.ENGINE_NO,'-'),NVL(F.CHASSIS_NO,'-'),NVL(F.REG_NO,'-') "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CR_PRO_PURORDER_APP_DETAIL E,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS F "+ 
			"WHERE E.PURCHASE_ORDER_NO=a.PURCHASE_ORDER_NO "+ 
			"AND A.PURCHASE_ORDER_NO  LIKE UPPER('"+m_val+"%') "+
			"AND A.ACTIVE_STATUS='"+m_val1+"' "+
      "AND INVOICE_NO=PRO_INVOICE_NO "+
      "AND E.ROWID in (SELECT max(E.ROWID) FROM LAKDL.AF_CR_PRO_PURCHASE_ORDER A,LAKDL.AF_CR_PRO_PURORDER_APP_DETAIL E "+
			"WHERE E.PURCHASE_ORDER_NO=a.PURCHASE_ORDER_NO "+ 
			"AND E.PURCHASE_ORDER_NO  LIKE UPPER('"+m_val+"%') "+
			"AND E.ACTIVE_STATUS='"+m_status+"' "+
     	"GROUP BY E.PURCHASE_ORDER_NO) ");		
				
				
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
					out.print("</ITEM>");
				}
				//out.print(m_val1);
				out.print("</DATA>");
			
     }
					
	
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_details")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			String m_sus = req.getParameter("sus_ref").trim();
			String m_ref = req.getParameter("ref").trim();

			//(2007-03-20)------------------------------------------------------------------------	

			if(m_status.equals("VERIFY")){
			rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE,INITCAP(FULL_NAME), "+
 			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET,TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy') "+
 			",A.MOD_USER,ACC_NO,BRANCH_CODE,PAYER_NAME,TO_CHAR(LETTER_DATE,'DD-MM-YYYY') "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT G,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J  "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"AND A.APPLICATION_NO=J.APPLICATION_NO "+
			"AND G.SUS_REF_NO='"+m_sus+"' "+
			"AND G.REF_NO='"+m_ref+"' "+
      "AND G.REF_NO=J.INVOICE_NO  "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND A.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"ORDER BY C.APPLICATION_NO ASC ");		
			
			}
			
			else{

			
			rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE,INITCAP(FULL_NAME), "+
			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET,TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT, "+
			"TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY') "+
			",A.MOD_USER,ACC_NO,BRANCH_CODE,PAYER_NAME,TO_CHAR(LETTER_DATE,'DD-MM-YYYY') "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			""+m_schema_name+".AF_CO_MAS_CLIENT D,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT G,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J, "+
			""+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT H,"+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET K "+
			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"AND J.APPLICATION_NO=C.APPLICATION_NO "+
			"AND H.PURCHASE_ORDER_NO=A.PURCHASE_ORDER_NO "+
			"AND K.REQU_NO=H.REQU_NO "+
			"AND J.INVOICE_NO=K.INVOICE_NO "+
			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND upper(A.PURCHASE_ORDER_NO) LIKE UPPER('"+m_val+"%') "+
			"ORDER BY C.APPLICATION_NO ASC ");

			}
			

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
					out.print("<R14>"+rs.getString(14)+"</R14>");
					out.print("<R15>"+rs.getString(15)+"</R15>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_details_sort")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			String m_order_col=req.getParameter("sort_column").trim();
			String m_order_type=req.getParameter("order_by_type").trim();
			String m_order_col_1=req.getParameter("sort_column").trim();
			String m_app_status = req.getParameter("app_status").trim();
			String m_pay_status = req.getParameter("payment_status").trim();
			String m_screen=req.getParameter("screen_type").trim();
			
			if(m_order_col.equals("PURCHASE_ORDER_NO")){
			m_order_col="A.PURCHASE_ORDER_NO";
			}	
			
			//(2007-03-20)------------------------------------------------------------------------	
			if(m_screen.equals("Requisition Approval")){
			
		
			
			if (m_status.equals("VERIFY")) {
		
			rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME), "+
			"B.VENDOR_CODE,INITCAP(FULL_NAME),TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET, "+
			"TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT, "+
			"TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL, "+
			"TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
			",NVL(A.MOD_USER,'-'),'"+m_username+"','','', "+
			"SUS_REF_NO,REF_NO,'','' "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			""+m_schema_name+".AF_CO_MAS_VENDORS B, "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			""+m_schema_name+".AF_CO_MAS_CLIENT D , "+
			""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT G, "+
			""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J "+			
			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
			"AND  G.REF_NO=J.INVOICE_NO "+
			"and j.purchase_order_no=a.purchase_order_no "+
			"AND C.APPLICATION_NO=J.APPLICATION_NO "+
			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND NVL(MPAY_STATUS,'-') <> ('COM') "+
			"AND A.ACTIVE_STATUS='VERIFY' "+
			"AND UPPER(APPLICATION_STATUS) =UPPER('ACTIVATED') "+
			"and a.purchase_order_no not in (select purchase_order_no from LAKDL.AF_CR_PRO_PURCHASE_ORDER_SETT dd,LAKDL.AF_CR_PRO_PUR_ORDER_SETdet tt "+
      "where dd.requ_no=tt.requ_no) "+
			"UNION "+			
			"SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME), "+
			"B.VENDOR_CODE,INITCAP(FULL_NAME), "+
			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET, "+
			"TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT, "+
			"TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL, "+
			"TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
			",NVL(A.MOD_USER,'-'),'"+m_username+"','','' "+
			",SUS_REF_NO,REF_NO,'',''  "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			""+m_schema_name+".AF_CO_MAS_VENDORS B, "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			""+m_schema_name+".AF_CO_MAS_CLIENT D , "+
			""+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT AA, "+
			""+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET BB, "+
			""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT G, "+
			""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J "+			
			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
			"AND  G.REF_NO=J.INVOICE_NO "+
			"AND C.APPLICATION_NO=J.APPLICATION_NO "+
			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"and j.purchase_order_no=a.purchase_order_no "+
			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND NVL(MPAY_STATUS,'-') <> ('COM') "+
			"AND A.ACTIVE_STATUS='VERIFY' "+
			"AND UPPER(APPLICATION_STATUS) =UPPER('ACTIVATED') "+
			"AND AA.REQU_NO=BB.REQU_NO "+
			"AND AA.PURCHASE_ORDER_NO=A.PURCHASE_ORDER_NO "+
			"AND NVL(BAL_TO_BE_PAID,0)<>0 "+
			"ORDER BY "+m_order_col+" "+m_order_type+" ");



			}
			//---------------------------------------------------------------------------
					else if (m_status.equals("RE-APP") || m_status.equals("")) {
				

			
		  rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE,INITCAP(FULL_NAME), "+
 			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET,TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
 			",NVL(A.MOD_USER,'-'),'"+m_username+"',G.REQU_NO,nvl(TO_CHAR(SETTLE_AMT,'999,999,999,999,999,999,999,999.99'),0) "+
			",SUS_REF_NO,REF_NO,'','' "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+				
			","+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT G,"+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET Y "+
			","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT H,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND  H.REF_NO=J.INVOICE_NO "+
      "AND C.APPLICATION_NO=J.APPLICATION_NO "+  
			"and j.purchase_order_no=a.purchase_order_no "+
			"AND 	A.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND A.ACTIVE_STATUS in ('VERIFY') "+
			"AND A.PURCHASE_ORDER_NO=G.PURCHASE_ORDER_NO "+
			"AND G.ACTIVE_STATUS='RE-APP' "+
			"AND APPLICATION_STATUS ='"+m_app_status+"' "+
			"AND G.REQU_NO=Y.REQU_NO "+
      "AND Y.INVOICE_NO=J.INVOICE_NO "+
			"ORDER BY "+m_order_col+" "+m_order_type+" ");
	
			}

			}
			//---------------------------------------------------------------------------


				if(m_screen.equals("Approval 1")){


		  rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE,INITCAP(FULL_NAME), "+
 			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET,TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
 			",NVL(A.MOD_USER,'-'),'"+m_username+"',G.REQU_NO,nvl(TO_CHAR(SETTLE_AMT,'999,999,999,999,999,999,999,999.99'),0) "+
			",SUS_REF_NO,REF_NO,LIMIT,(TOTAL_NET+TOTAL_VAT) "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+//,"+m_schema_name+".AF_CR_PRO_PURORDER_APP_DETAIL E	"+
			","+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT G,"+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET Y "+
			","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT H,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J,"+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS dd "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND  H.REF_NO=J.INVOICE_NO "+
			"and j.purchase_order_no=a.purchase_order_no "+
      "AND C.APPLICATION_NO=J.APPLICATION_NO "+  
			"AND 	A.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND A.ACTIVE_STATUS in ('VERIFY') "+
			"AND A.PURCHASE_ORDER_NO=G.PURCHASE_ORDER_NO "+
			"AND G.ACTIVE_STATUS='"+m_status+"' "+
			"AND APPLICATION_STATUS ='"+m_app_status+"' "+
			"AND G.REQU_NO=Y.REQU_NO "+
      "AND Y.INVOICE_NO=J.INVOICE_NO "+
			"AND DD.USER_ID='"+m_username+"' "+
			"ORDER BY "+m_order_col+" "+m_order_type+" ");
	


			}
					
			if(m_screen.equals("Approval 2")){

			
		 rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE, "+
	   "INITCAP(FULL_NAME), "+
	 	 "TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET, "+
	   "TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT, "+
	   "TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL, "+
	   "TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"'  "+
	   ",NVL(A.MOD_USER,'-'),'"+m_username+"',G.REQU_NO,nvl(TO_CHAR(SETTLE_AMT,'999,999,999,999,999,999,999,999.99'),0) "+ 
		 ",SUS_REF_NO,REF_NO,LIMIT,(TOTAL_NET+TOTAL_VAT)  "+
		 "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
	   ""+m_schema_name+".AF_CO_MAS_VENDORS B, "+
	   ""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	 	 ""+m_schema_name+".AF_CO_MAS_CLIENT D  "+//--//,"+m_schema_name+".AF_CR_PRO_PURORDER_APP_DETAIL E	
		 ","+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT G, "+
	   ""+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET Y  "+
		 ","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT H, "+
	   ""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J,"+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS dd  "+
	 	 "WHERE A.VENDER_CODE=B.VENDOR_CODE  "+
	 	 "AND A.APPLICATION_NO=C.APPLICATION_NO  "+
	 	 "AND C.CLIENT_CODE=D.CLIENT_CODE  "+
		 "AND  H.REF_NO=J.INVOICE_NO  "+
			"and j.purchase_order_no=a.purchase_order_no "+
	   "AND C.APPLICATION_NO=J.APPLICATION_NO   "+ 
		 "AND 	A.PURCHASE_ORDER_NO LIKE UPPER('%')  "+
		 "AND A.ACTIVE_STATUS in ('VERIFY')  "+
		 "AND A.PURCHASE_ORDER_NO=G.PURCHASE_ORDER_NO  "+
		 "AND G.ACTIVE_STATUS='"+m_status+"'  "+
		 "AND G.REQU_NO=Y.REQU_NO  "+
	   "AND Y.INVOICE_NO=J.INVOICE_NO  "+
			"AND DD.USER_ID='"+m_username+"' "+
		 "ORDER BY "+m_order_col+" "+m_order_type+" ");

			}

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
					out.print("<R14>"+rs.getString(14)+"</R14>");					
					out.print("<R15>"+rs.getString(15)+"</R15>");
					out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(17)+"</R17>");
					out.print("<R18>"+rs.getString(18)+"</R18>");
					out.print("<R19>"+rs.getDouble(19)+"</R19>");
					out.print("<R20>"+rs.getDouble(20)+"</R20>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		
		//------------------------------------------------------------------------------------------
		//---------------------Purpose 	:Cheque printing (Payment Screen)-----------------------
		//---------------------Name     :Delanjali--------------------------------------------------
		//---------------------Date     :31-10-2006--------------------------------------------------

		//!!To sort 
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_cheque_printing_details_sort")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			
			String m_ac = req.getParameter("ac_no").trim();
			String m_br = req.getParameter("br_code").trim();
			String m_order = req.getParameter("sort_column").trim();
			String m_type = req.getParameter("order_by_type").trim();
			

			rs= stmt.executeQuery ("SELECT PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,VENDOR_CODE,FULL_NAME, "+
 			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,NVL(TO_CHAR(PRINTED_DATE,'DD-MM-YYYY'),'-'),'"+m_type+"' "+
 			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND BRANCH_CODE =('"+m_br+"') "+
			"AND ACC_NO=('"+m_ac+"') "+
			"AND PAYMENT_STATUS='"+m_status+"' "+
			"AND PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
 			"AND A.ACTIVE_STATUS='"+m_status+"' "+
			"ORDER BY "+m_order+" "+m_type+" ");											
					
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
					//out.print("<R12>"+rs.getString(12)+"</R12>");
					//out.print("<R13>"+rs.getString(13)+"</R13>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_cheque_cancel_details_sort")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			
			String m_ac = req.getParameter("ac_no").trim();
			String m_br = req.getParameter("br_code").trim();
			//String m_day= req.getParameter("days").trim();
			String m_order = req.getParameter("sort_column").trim();
			String m_type = req.getParameter("order_by_type").trim();
			

					
			rs= stmt.executeQuery ("SELECT PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,VENDOR_CODE,FULL_NAME, "+
 			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PRINTED_DATE,'DD-MM-YYYY'),'"+m_type+"' "+
 			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			//"AND BRANCH_CODE =('"+m_br+"') "+
			"AND ACC_NO=('"+m_ac+"') "+
			"AND PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND PAYMENT_STATUS='"+m_status+"' "+
			"AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(PRINTED_DATE,'DD-MM-YYYY') >7 "+//> 7
 			"AND A.ACTIVE_STATUS='"+m_status+"' "+
			"ORDER BY "+m_order+" "+m_type+" ");					
					
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

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_cheque_disburse_details_sort")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			
			String m_ac = req.getParameter("ac_no").trim();
			String m_br = req.getParameter("br_code").trim();
			//String m_day= req.getParameter("days").trim();
			String m_order = req.getParameter("sort_column").trim();
			String m_type = req.getParameter("order_by_type").trim();


			rs= stmt.executeQuery ("SELECT PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,VENDOR_CODE,FULL_NAME, "+
 			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PRINTED_DATE,'DD-MM-YYYY'),'"+m_type+"' "+
 			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND BRANCH_CODE =('"+m_br+"') "+
			"AND ACC_NO=('"+m_ac+"') "+
			"AND PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND (TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(PRINTED_DATE,'DD-MM-YYYY')) <7 "+//< 7 "+
 			"AND A.ACTIVE_STATUS='"+m_status+"' "+
			"ORDER BY "+m_order+" "+m_type+" ");									
					
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

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		

		//!!
			
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_cheque_printing_details")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			
			String m_ac = req.getParameter("ac_no").trim();
			String m_br = req.getParameter("br_code").trim();

			//(2007-03-20)------------------------------------------------------------------------	


			
					rs= stmt.executeQuery ("SELECT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,VENDOR_CODE,FULL_NAME, "+
 			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,NVL(TO_CHAR(PRINTED_DATE,'DD-MM-YYYY'),'-'),PRINTED_USER,REQU_NO "+
 			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT E "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND BRANCH_CODE =('"+m_br+"') "+
			"AND ACC_NO=('"+m_ac+"') "+
			"AND A.PURCHASE_ORDER_NO =E.PURCHASE_ORDER_NO "+
			"AND E.ACTIVE_STATUS='"+m_status+"' "+
			"AND A.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
 			//"AND A.ACTIVE_STATUS='"+m_status+"' "+
			"ORDER BY PURCHASE_ORDER_NO ASC  ");											
	
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

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		
			
			
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_cheque_cancel_details")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			
			String m_ac = req.getParameter("ac_no").trim();
			String m_br = req.getParameter("br_code").trim();
			//String m_day= req.getParameter("days").trim();

					
			rs= stmt.executeQuery ("SELECT a.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,VENDOR_CODE,FULL_NAME, "+
 			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PRINTED_DATE,'DD-MM-YYYY'),PRINTED_USER,requ_no "+
 			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT E "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
		//	"AND BRANCH_CODE =('"+m_br+"') "+
			"AND ACC_NO=('"+m_ac+"') "+
			"AND A.PURCHASE_ORDER_NO =E.PURCHASE_ORDER_NO "+
			"AND E.ACTIVE_STATUS='"+m_status+"' "+

			"AND a.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(PRINTED_DATE,'DD-MM-YYYY') <1 "+//> 7
 			//"AND A.ACTIVE_STATUS='"+m_status+"' "+
			"ORDER BY PURCHASE_ORDER_NO ASC ");											
					
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

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_cheque_disburse_details")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			
			String m_ac = req.getParameter("ac_no").trim();
			String m_br = req.getParameter("br_code").trim();
			//String m_day= req.getParameter("days").trim();


			rs= stmt.executeQuery ("SELECT PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,VENDOR_CODE,FULL_NAME, "+
 			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PRINTED_DATE,'DD-MM-YYYY'),PRINTED_USER "+
 			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND BRANCH_CODE =('"+m_br+"') "+
			"AND ACC_NO=('"+m_ac+"') "+
			"AND PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND (TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(PRINTED_DATE,'DD-MM-YYYY')) <7 "+//< 7 "+
 			"AND A.ACTIVE_STATUS='"+m_status+"' "+
			"ORDER BY PURCHASE_ORDER_NO ASC");											
					
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

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		

//credit verification approval process			
//delanjali
//2006-11-09

	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_client")){
    
    String m_val = req.getParameter("data_val").trim();
    String m_val1 = req.getParameter("data_val1").trim();
		String m_val2 = req.getParameter("data_val2").trim();
  								
			
			rs= stmt.executeQuery ("SELECT DISTINCT APPLICATION_NO,FACILITY_NO,A.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) APPLICANT_NAME, "+
			"CO_APPLICANT,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME,INQUARY_NO,CLIENT_TYPE "+
			"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			"WHERE APPLICATION_NO LIKE UPPER('"+m_val+"') "+
			"AND A.CLIENT_CODE=B.CLIENT_CODE "+
		////	"AND CLIENT_TYPE='C' "+
			"AND (APPLICATION_STATUS=('"+m_val1+"')OR APPLICATION_STATUS=('"+m_val2+"'))");


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
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
			
			//credit verification approval process			
			//delanjali
			//2006-11-09

	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_branch_code")){
    
    String m_val = req.getParameter("data_val").trim();
		String m_val2 = req.getParameter("data_val2").trim();
    String m_val3 = req.getParameter("data_val3").trim();
		String m_status = req.getParameter("ac_status").trim();
  								
					
			
			rs= stmt.executeQuery (" SELECT "+
      " DISTINCT BRANCH,LOCATION_CODE "+
 			" FROM  "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+
 			" WHERE BRANCH IN( "+
 			" SELECT "+
 			" DISTINCT  BRANCH_ID "+
 			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
 			" WHERE UPPER(VENDOR_CODE)=UPPER('"+m_val+"') AND APPLICATION_NO=UPPER('"+m_val2+"')) "+
 			" AND UPPER(BRANCH)=UPPER('"+m_val3+"') AND ACTIVE_STATUS=('"+m_status+"') ");

			

    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		  out.print("<R2>"+rs.getString(2)+"</R2>"); 
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
	
						
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Application_no")){
    
    String m_val = req.getParameter("data_val").trim();
    //String m_val2 = req.getParameter("data_val2").trim();
		String m_status = req.getParameter("ac_status").trim();
  					

			

	rs= stmt.executeQuery ("SELECT APPLICATION_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE UPPER(APPLICATION_NO)=UPPER('"+m_val+"') AND APPLICATION_STATUS=('"+m_status+"') ");
    
    out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     } 
			
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_user")){
			
			String m_val = req.getParameter("data_val").trim();
			
									
			rs= stmt.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE, USER_TYPE, EMP_ID,"+
   " DIVISION_CODE, DESIGNATION_CODE,PASSWORD FROM LAKDL.CO_CO_MAS_USER "+
			" WHERE UPPER(USER_ID)=UPPER('"+m_val+"') ");
				
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
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
		
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_score_model_creation")){
				String m_val = req.getParameter("data_val");
				
				rs= stmt.executeQuery ("SELECT  SCORE_MODEL_CODE  FROM LAKDL.AF_CR_MAS_SCORE_MODEL "+
				" WHERE UPPER(SCORE_MODEL_CODE)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_client_1")){
    
    String m_val = req.getParameter("data_val").trim();
    String m_val1 = req.getParameter("data_val1").trim();
		String m_val2 = req.getParameter("data_val2").trim();
  								

			rs= stmt.executeQuery ("SELECT DISTINCT APPLICATION_NO,A.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+
			"NVL(ADDRESS1,'-'),NVL(ADDRESS2,'-'),NVL(NIC_NO,'-'),DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') AF_GUARANTORS ,DECODE(NVL(FA_CLIENT,'-'),'Y','Factory Client','-') FA_CLIENT,DECODE(NVL(FA_DEBTOR,'-'),'Y','Factory Debtor','-') FA_DEBTOR ,INQUARY_NO,CLIENT_TYPE,CO_APPLICANT,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+
			"(SELECT NVL(ADDRESS1,'-')FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS1, "+
			"(SELECT NVL(ADDRESS2,'-')FROM "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) ADDRESS2, "+
			"(SELECT NVL(NIC_NO,'-')FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) NIC, "+
			"(SELECT DECODE(NVL(AF_GUARANTORS,'-'),'Y','Gurantor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) AF_GUARANTORS, "+
			"(SELECT DECODE(NVL(FA_CLIENT,'-'),'Y','Factory Client','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_CLIENT, "+
			"(SELECT DECODE(NVL(FA_DEBTOR,'-'),'Y','Factory Debtor','-') FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) FA_DEBTOR, "+
			"(SELECT CLIENT_TYPE FROM   "+m_schema_name+".AF_CO_MAS_CLIENT A WHERE  CLIENT_CODE= CO_APPLICANT) CLIENT_TYPE1, "+
			"(SELECT INQUARY_NO FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_CLIENT A WHERE A.CLIENT_CODE=B.CO_APPLICANT AND APPLICATION_NO LIKE UPPER('"+m_val+"%')  AND  A.CLIENT_CODE= CO_APPLICANT) INQUIRY_NO1 "+
			"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			"WHERE APPLICATION_NO LIKE UPPER('"+m_val+"%') "+
			"AND A.CLIENT_CODE=B.CLIENT_CODE "+
		////	"AND CLIENT_TYPE='C' "+
			"AND (APPLICATION_STATUS=('"+m_val1+"')OR APPLICATION_STATUS=('"+m_val2+"'))");


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
		 out.print("<R14>"+rs.getString(14)+"</R14>");
		 out.print("<R15>"+rs.getString(15)+"</R15>");
			out.print("<R16>"+rs.getString(16)+"</R16>");
			out.print("<R17>"+rs.getString(17)+"</R17>");
			out.print("<R18>"+rs.getString(18)+"</R18>");
			out.print("<R19>"+rs.getString(19)+"</R19>");
			out.print("<R20>"+rs.getString(20)+"</R20>");
			out.print("<R21>"+rs.getString(21)+"</R21>");
     out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
			
			
			
			
			
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_FolowUp_number")){
    
    String m_app_no = req.getParameter("data_val").trim();
  	
			
			
    	rs= stmt.executeQuery ("SELECT  "+
																 															
    														 "	    NVL(FOLLOW_UP_NO,'-')  FOLLOW_UP_NO "+
																 "	FROM "+
																 "			"+m_schema_name+".AF_CO_PRO_FOLLOW_UP		 "+
  															 "  WHERE ID_NO=UPPER('"+m_app_no+"') ");
			
				
				
						out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		  out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_invoice_adjustments_finance_no")){
    
    String m_val    =  req.getParameter("data_val").trim();
    String m_status =  req.getParameter("ac_status").trim();	
			
	
					rs= stmt.executeQuery ("SELECT  "+
 				 " 		DISTINCT A.FINANCE_NO, "+
 				 " 			A.CLIENT_CODE, "+
				 " 			 B.FULL_NAME FULL_NAME   "+
				 " 				 FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
				 "         (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
				 "          FROM "+m_schema_name+".AF_CO_PRO_INVOICE X,"+m_schema_name+".AF_CO_MAS_CLIENT V   "+
				 "          WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
				 "          )B   "+
				 " 				 WHERE A.FINANCE_NO=B.FINANCE_NO  "+ 
				 "          AND    "+
			   "  			(UPPER(B.FULL_NAME)=UPPER('"+m_val+"') OR  "+
         "  			UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
         "  			B.TEL_NO=UPPER('"+m_val+"') OR  "+
         "  			B.NIC_NO=UPPER('"+m_val+"') OR  "+
         "  			A.FINANCE_NO=UPPER('"+m_val+"')  "+
			   "        )   "+
				 " 			 AND A.ACTIVE_STATUS=('"+m_status+"') "+
				 "  			 ORDER BY FINANCE_NO DESC ");
			
				
				
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
			
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_invoice_adjustments_invoice_no")){
    
    String m_val    =  req.getParameter("data_val").trim();
		String m_val2    =  req.getParameter("data_val2").trim();
    String m_status =  req.getParameter("ac_status").trim();	
			
	
					rs= stmt.executeQuery ("SELECT  "+
												 				"  INVOICE_NO, "+
												  			"  TOTAL_AMOUNT, "+
																"  BALANCE_TO_BE_RECEIVED, "+
												  		  //"  (BALANCE_TO_BE_RECEIVED - (NVL(ADJUSTED_AMOUNT,0))) BALANCE_TO_BE_RECEIVED, "+
																"  TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE "+
													 			"	 FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
												 				"	 WHERE   FINANCE_NO=UPPER('"+m_val+"') AND "+
																"  INVOICE_NO=UPPER('"+m_val2+"') AND "+	
												    	  "  BALANCE_TO_BE_RECEIVED>0 AND "+
												    	  "  ACTIVE_STATUS=('"+m_status+"') ");
			
				
				
						out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");
		 out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		  out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_invoice_adjustments_credit_no")){
    
    String m_val    =  req.getParameter("data_val").trim();
	  String m_status =  req.getParameter("ac_status").trim();	
			
	
																	
	/*															
	rs= stmt.executeQuery ("SELECT  "+
    " A.REF_NO, "+
    " A.FINANCE_NO, "+
    " B.CLIENT_CODE, "+
    " B.FULL_NAME, "+
    " A.INVOICE_NO, "+
		" C.TOTAL_AMOUNT, "+
		" C.BALANCE_TO_BE_RECEIVED, "+
  //  " (C.BALANCE_TO_BE_RECEIVED - (NVL(C.ADJUSTED_AMOUNT,0))) BALANCE_TO_BE_RECEIVED, "+
    " A.ADJUSTED_AMOUNT, "+
		" TO_CHAR(C.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
    " A.CREDIT_TYPE, "+
		" NVL((TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY')),'-') ADJUSTED_DATE, "+
		" NVL(A.REMARKS,'-') REMARKS, "+
		" NVL(A.DOC_REF_NO,'-') DOC_REF_NO, "+
		" NVL(A.ADJUST_TYPE,'-') ADJUST_TYPE " +
    " FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B, "+m_schema_name+".AF_CO_PRO_INVOICE C "+
        
    " WHERE  "+
    "  A.REF_NO=UPPER('"+m_val+"') AND "+
    "  A.ACTIVE_STATUS=('"+m_status+"') AND  "+
		"  A.FINANCE_NO=C.FINANCE_NO AND "+
    "  A.INVOICE_NO=C.INVOICE_NO AND "+
 //   "  C.BALANCE_TO_BE_RECEIVED>0 AND "+
		"  C.CLIENT_CODE=B.CLIENT_CODE ");
			
				
				
						out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2)+"</R2>");	
		 out.print("<R3>"+rs.getString(3)+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		 out.print("<R5>"+rs.getString(5)+"</R5>");
		 out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
		 out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");
		 out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
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
			*/
			
			
			
				rs= stmt.executeQuery ("SELECT  "+
    " A.REF_NO, "+
    " A.FINANCE_NO, "+
    " B.CLIENT_CODE, "+
    " B.FULL_NAME, "+
    " A.INVOICE_NO, "+
		" C.TOTAL_AMOUNT, "+
		" C.BALANCE_TO_BE_RECEIVED, "+
  //  " (C.BALANCE_TO_BE_RECEIVED - (NVL(C.ADJUSTED_AMOUNT,0))) BALANCE_TO_BE_RECEIVED, "+
    " A.ADJUSTED_AMOUNT, "+
		" TO_CHAR(C.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
    " A.CREDIT_TYPE, "+
		" NVL((TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY')),'-') ADJUSTED_DATE, "+
		" NVL(A.REMARKS,'-') REMARKS, "+
		" NVL(A.DOC_REF_NO,'-') DOC_REF_NO, "+
		" NVL(A.ADJUST_TYPE,'-') ADJUST_TYPE, NARRATIONS_CODE" +
    " FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B, "+m_schema_name+".AF_CO_PRO_INVOICE C "+
        
    " WHERE  "+
    "  A.REF_NO=UPPER('"+m_val+"') AND "+
    "  A.ACTIVE_STATUS=('"+m_status+"') AND  "+
		"  A.FINANCE_NO=C.FINANCE_NO AND "+
    "  A.INVOICE_NO=C.INVOICE_NO AND "+
 //   "  C.BALANCE_TO_BE_RECEIVED>0 AND "+
		"  C.CLIENT_CODE=B.CLIENT_CODE ");
			
				
				
						out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("<R2>"+rs.getString(2)+"</R2>");	
		 out.print("<R3>"+rs.getString(3)+"</R3>");
		 out.print("<R4>"+rs.getString(4)+"</R4>");
		 out.print("<R5>"+rs.getString(5)+"</R5>");
		 out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
		 out.print("<R7>"+nf.format(rs.getDouble(7))+"</R7>");
		 out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
		 out.print("<R9>"+rs.getString(9)+"</R9>");
		 out.print("<R10>"+rs.getString(10)+"</R10>");
		 out.print("<R11>"+rs.getString(11)+"</R11>");
		 out.print("<R12>"+rs.getString(12)+"</R12>");
		 out.print("<R13>"+rs.getString(13)+"</R13>");
		 out.print("<R14>"+rs.getString(14)+"</R14>");
		 out.print("<R15>"+rs.getString(15)+"</R15>");
	
		 
		 out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }

			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions")){
    
    String m_val    =  req.getParameter("data_val").trim();
	  String m_status =  req.getParameter("ac_status").trim();	
			
		
	rs= stmt.executeQuery(" SELECT  "+
   "  FOLLOW_UP_NO, "+
   "  INITCAP(ENT_REMARKS), "+
	 "  INITCAP(STATUS) "+
   " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
	 " WHERE ID_NO=UPPER('"+m_val+"') ");

					
				
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
			
			
			

			
			
	 	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application")){
    
    String m_val    =  req.getParameter("data_val").trim();
	
	   rs = stmt.executeQuery ("SELECT PURCHASE_ORDER_NO "+
    "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER a,"+m_schema_name+".AF_CO_MAS_CURRENCY b "+
    "WHERE APPLICATION_NO='"+m_val+"' and a.curr_code=b.curr_code ");

					
				
		 out.print("<DATA>");
     while(rs.next()){
     out.print("<ITEM>");
     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
     }
     out.print("</DATA>");
   
     }   
			


		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application_client")){
    
    String m_val    =  req.getParameter("data_val").trim();
			
		
			rs = stmt.executeQuery ("SELECT DISTINCT A.CLIENT_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE upper(A.CLIENT_CODE) = UPPER('"+m_val+"')  "+			
			"  AND     A.ACTIVE_STATUS=('Y')  "+
			"  AND A.CLIENT_CODE=B.CLIENT_CODE ");
		
					
				
		out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
			
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application_finance")){
    
    String m_val    =  req.getParameter("data_val").trim();
			
		
			rs = stmt.executeQuery (" SELECT FINANCE_NO  "+
												" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
												" WHERE   upper(FINANCE_NO) = UPPER('"+m_val+"') ");

		
					
				
		out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   
	
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_history_of_application_reg")){
    
    String m_val    =  req.getParameter("data_val").trim();
			
		

		rs = stmt.executeQuery (" SELECT "+
		" REG_NO "+  
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
    " WHERE REG_NO = UPPER('"+m_val+"') "+ 
		"AND A.ACTIVE_STATUS=('Y') "+
		" AND A.APPLICATION_NO=B.APPLICATION_NO ");

					
				
		out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   

			/*
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_finance")){
    
    String m_val    =  req.getParameter("data_val").trim();
	
		
		rs = stmt.executeQuery (" SELECT "+
		" FINANCE_NO "+  
    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
    " WHERE upper(FINANCE_NO) = UPPER('"+m_val+"') ");

				
		out.print("<DATA>");
    while(rs.next()){


     out.print("<ITEM>");
     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
			}
		 out.print("</DATA>");
			
					
}

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_invoice")){
    
    String m_val    =  req.getParameter("data_val").trim();
	
		
		rs = stmt.executeQuery ("SELECT distinct invoice_no "+
		"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		"WHERE PURCHASE_ORDER_NO IS NULL "+
		"AND  UPPER(APPLICATION_NO)=UPPER('"+m_val+"') ");


				
		out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
			}
		 out.print("</DATA>");
			
					
}
		*/
		
					else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_finance")){
    
    String m_val    =  req.getParameter("data_val").trim();
	    String m_val1    =  req.getParameter("data_val1").trim();

		
		rs = stmt.executeQuery (" SELECT "+
		" count(FINANCE_NO) "+  
    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
    " WHERE upper(FINANCE_NO) = UPPER('"+m_val+"') ");


		boolean more=rs.next();
		if(rs.getString(1).equals("0")){
	
		
		rs1 = stmt1.executeQuery ("SELECT invoice_no "+
		"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		"WHERE PURCHASE_ORDER_NO IS NULL "+
		"AND  UPPER(APPLICATION_NO)=UPPER('"+m_val1+"') ");


				
				
				
		out.print("<DATA>");
    while(rs1.next()){


     out.print("<ITEM>");
     
		 out.print("<R1>"+rs1.getString(1)+"</R1>");
		 out.print("</ITEM>");
			}
		 out.print("</DATA>");
			
					
}

else{
		out.print("<DATA>");


     out.print("<ITEM>");
     
		 out.print("<R1>@j@</R1>");
		 out.print("</ITEM>");
		 out.print("</DATA>");
			

}
}

/*
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_invoice")){
    
    String m_val    =  req.getParameter("data_val").trim();
	
		
		rs = stmt.executeQuery ("SELECT distinct invoice_no "+
		"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		"WHERE PURCHASE_ORDER_NO IS NULL "+
		"AND  UPPER(APPLICATION_NO)=UPPER('"+m_val+"') ");


				
		out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
			}
		 out.print("</DATA>");
			
					
}
		*/
	
		
		
	else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_no")){
    
    String m_val   				=  req.getParameter("data_val").trim();
		String m_ac_status    =  req.getParameter("ac_status").trim();
	
		

		rs = stmt.executeQuery (" SELECT "+
		" FINANCE_NO "+  
    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
    " WHERE upper(FINANCE_NO) = UPPER('"+m_val+"') "+ 
		"AND upper(APPLICATION_STATUS)=upper('"+m_ac_status+"') ");

					
				
		out.print("<DATA>");
    while(rs.next()){
     out.print("<ITEM>");
     
		 out.print("<R1>"+rs.getString(1)+"</R1>");
		 out.print("</ITEM>");
    }
    out.print("</DATA>");
   
     }   

else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_sysdate")){
			
				
				rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'dd'), "+
					"TO_CHAR(SYSDATE,'mm'), "+
					"TO_CHAR(SYSDATE,'yyyy') "+
					"FROM DUAL");
				 
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
		

else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order")){
			
			    
    String m_val   				  =  req.getParameter("data_val").trim();
		String m_ac_status      =  req.getParameter("ac_status").trim();
    String m_val1   				=  req.getParameter("data_val1").trim();
			
				
				rs=stmt.executeQuery("SELECT DISTINCT A.CLIENT_CODE "+
				"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				"WHERE upper(A.CLIENT_CODE) = UPPER('"+m_val+"') "+
				"AND A.ACTIVE_STATUS=('"+m_ac_status+"') "+
				//"AND upper(APPLICATION_NO) = ('"+m_val1+"') "+
				"AND A.CLIENT_CODE=B.CLIENT_CODE ");
				 
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order_client")){
			
			    
    String m_val   				  =  req.getParameter("data_val").trim();
		String m_ac_status      =  req.getParameter("ac_status").trim();
			
			
				 rs=stmt.executeQuery("SELECT FINANCE_NO "+
				"FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
				"WHERE   UPPER(FINANCE_NO) = UPPER('"+m_val+"') "+
				"AND APPLICATION_STATUS =('"+m_ac_status+"') ");


				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}





			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order_acc")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();
		  String m_val1   				  =  req.getParameter("data_val1").trim();

			

			rs=stmt.executeQuery("SELECT ACCOUNT_NO "+
			"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"WHERE UPPER(ACCOUNT_NO) = UPPER('"+m_val+"') "+
			"AND UPPER(A.CLIENT_CODE) = UPPER('"+m_val1+"') "+
			"AND ACTIVE_STATUS=('"+m_ac_status+"') "+
			"AND A.CLIENT_CODE=B.CLIENT_CODE  ");

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order_so")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();

			rs=stmt.executeQuery("SELECT  "+
        "SO_NO,  "+
				"A.FINANCE_NO, "+
        "B.APPLICATION_NO, "+
        "CLIENT_CODE, "+
        "REG_NO, "+
        "TO_CHAR(END_DATE,'DD-MM-YYYY') END_DATE,  "+
        "TO_CHAR(START_DATE,'DD-MM-YYYY') START_DATE,  "+
        "ACC_NO, "+
        "BANK_CODE,  "+
        "AMOUNT  "+
				"FROM  "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS  A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
        "WHERE UPPER(SO_NO) = UPPER('"+m_val+"')  "+
        "AND A.FINANCE_NO=B.FINANCE_NO "+
        "AND B.APPLICATION_NO=C.APPLICATION_NO "+
				"AND STATUS =('"+m_ac_status+"') ");
	
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
					out.print("<R10>"+rs.getDouble(10)+"</R10>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
		
					else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_Rental_dates")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			//String m_ac_status      =  req.getParameter("ac_status").trim();

			rs=stmt.executeQuery("SELECT  "+
  			" NVL(INVOICE_NO,'-') INVOICE_NO, "+
				" INSTALLMENT_NO, "+
				" BALANCE_TO_BE_RECEIVED, "+
				" TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') RENTAL_DATE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
				" WHERE APPLICATION_NO=UPPER('"+m_val+"') "+
				" ORDER BY TO_NUMBER(INSTALLMENT_NO) ASC ");

	
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
          
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
		
		
		



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_temp_invoice")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_inv_no   				  =  req.getParameter("inv_no").trim();


			rs=stmt.executeQuery("SELECT purchase_order_no, "+
  			" PRO_INVOICE_NO "+
				" FROM "+m_schema_name+".AF_CR_PRO_PURORDER_APP_DETAIL "+
				" WHERE PURCHASE_ORDER_NO=UPPER('"+m_val+"') "+
				" AND ACTIVE_STATUS='T' ");

	
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
          					out.print("<R2>"+rs.getString(2)+"</R2>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
		
		
		

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Autherization_higher_Pay_1_sort")){
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status").trim();
			String m_order_col=req.getParameter("sort_column").trim();
			String m_order_type=req.getParameter("order_by_type").trim();
			String m_order_col_1=req.getParameter("sort_column").trim();
			String m_app_status = req.getParameter("app_status").trim();
			String m_pay_status = req.getParameter("payment_status").trim();
			String m_screen=req.getParameter("screen_type").trim();
			
			if(m_order_col.equals("PURCHASE_ORDER_NO")){
			m_order_col="A.PURCHASE_ORDER_NO";
			}	
			
			//(2007-03-20)------------------------------------------------------------------------	
			if(m_screen.equals("Requisition Approval")){
			
		
			
			if (m_status.equals("VERIFY")) {
		
			rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME), "+
			"B.VENDOR_CODE,INITCAP(FULL_NAME),TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET, "+
			"TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT, "+
			"TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL, "+
			"TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
			",NVL(A.MOD_USER,'-'),'"+m_username+"','','', "+
			"SUS_REF_NO,REF_NO "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			""+m_schema_name+".AF_CO_MAS_VENDORS B, "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			""+m_schema_name+".AF_CO_MAS_CLIENT D , "+
			""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT G, "+
			""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J "+			
			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
			"AND  G.REF_NO=J.INVOICE_NO "+
			"and j.purchase_order_no=a.purchase_order_no "+
			"AND C.APPLICATION_NO=J.APPLICATION_NO "+
			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND NVL(MPAY_STATUS,'-') <> ('COM') "+
			"AND A.ACTIVE_STATUS='VERIFY' "+
			"AND UPPER(APPLICATION_STATUS) =UPPER('ACTIVATED') "+
			"and a.purchase_order_no not in (select purchase_order_no from LAKDL.AF_CR_PRO_PURCHASE_ORDER_SETT dd,LAKDL.AF_CR_PRO_PUR_ORDER_SETdet tt "+
      "where dd.requ_no=tt.requ_no) "+
			"UNION "+			
			"SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME), "+
			"B.VENDOR_CODE,INITCAP(FULL_NAME), "+
			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET, "+
			"TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT, "+
			"TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL, "+
			"TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
			",NVL(A.MOD_USER,'-'),'"+m_username+"','','' "+
			",SUS_REF_NO,REF_NO  "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			""+m_schema_name+".AF_CO_MAS_VENDORS B, "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			""+m_schema_name+".AF_CO_MAS_CLIENT D , "+
			""+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT AA, "+
			""+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET BB, "+
			""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT G, "+
			""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J "+			
			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
			"AND  G.REF_NO=J.INVOICE_NO "+
			"AND C.APPLICATION_NO=J.APPLICATION_NO "+
			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
			"and j.purchase_order_no=a.purchase_order_no "+
			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND NVL(MPAY_STATUS,'-') <> ('COM') "+
			"AND A.ACTIVE_STATUS='VERIFY' "+
			"AND UPPER(APPLICATION_STATUS) =UPPER('ACTIVATED') "+
			"AND AA.REQU_NO=BB.REQU_NO "+
			"AND AA.PURCHASE_ORDER_NO=A.PURCHASE_ORDER_NO "+
			"AND NVL(BAL_TO_BE_PAID,0)<>0 "+
			"ORDER BY "+m_order_col+" "+m_order_type+" ");



			}
			//---------------------------------------------------------------------------
					else if (m_status.equals("RE-APP") || m_status.equals("")) {
				

			
		  rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE,INITCAP(FULL_NAME), "+
 			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET,TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
 			",NVL(A.MOD_USER,'-'),'"+m_username+"',G.REQU_NO,nvl(TO_CHAR(SETTLE_AMT,'999,999,999,999,999,999,999,999.99'),0) "+
			",SUS_REF_NO,REF_NO "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+				
			","+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT G,"+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET Y "+
			","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT H,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND  H.REF_NO=J.INVOICE_NO "+
      "AND C.APPLICATION_NO=J.APPLICATION_NO "+  
			"and j.purchase_order_no=a.purchase_order_no "+
			"AND 	A.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND A.ACTIVE_STATUS in ('VERIFY') "+
			"AND A.PURCHASE_ORDER_NO=G.PURCHASE_ORDER_NO "+
			"AND G.ACTIVE_STATUS='RE-APP' "+
			"AND APPLICATION_STATUS ='"+m_app_status+"' "+
			" AND G.REQU_NO=Y.REQU_NO "+
       "     AND Y.INVOICE_NO=J.INVOICE_NO "+
			"ORDER BY "+m_order_col+" "+m_order_type+" ");
	
			}

			}
			//---------------------------------------------------------------------------


				if(m_screen.equals("Approval 1")){


		  rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE,INITCAP(FULL_NAME), "+
 			"TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET,TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL,TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"' "+
 			",NVL(A.MOD_USER,'-'),'"+m_username+"',G.REQU_NO,nvl(TO_CHAR(SETTLE_AMT,'999,999,999,999,999,999,999,999.99'),0) "+
			",SUS_REF_NO,REF_NO "+
			"FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
 			""+m_schema_name+".AF_CO_MAS_CLIENT D "+//,"+m_schema_name+".AF_CR_PRO_PURORDER_APP_DETAIL E	"+
			","+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT G,"+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET Y "+
			","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT H,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J "+
			","+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL X,"+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS T "+
 			"WHERE A.VENDER_CODE=B.VENDOR_CODE "+
 			"AND A.APPLICATION_NO=C.APPLICATION_NO "+
 			"AND C.CLIENT_CODE=D.CLIENT_CODE "+
			"AND  H.REF_NO=J.INVOICE_NO "+
			"and j.purchase_order_no=a.purchase_order_no "+
      "AND C.APPLICATION_NO=J.APPLICATION_NO "+  
			"AND 	A.PURCHASE_ORDER_NO LIKE UPPER('"+m_val+"%') "+
			"AND A.ACTIVE_STATUS in ('VERIFY') "+
			"AND A.PURCHASE_ORDER_NO=G.PURCHASE_ORDER_NO "+
			"AND G.ACTIVE_STATUS='"+m_status+"' "+
			"AND APPLICATION_STATUS ='"+m_app_status+"' "+
			"AND G.REQU_NO=Y.REQU_NO "+
			"AND USER_ID=AUTHORAIZED_USER  "+
			"AND C.APPLICATION_NO=X.APPLICATION_NO  "+
		  "AND LIMIT < NVL((TOTAL_NET+TOTAL_VAT),0) "+			
      "AND Y.INVOICE_NO=J.INVOICE_NO "+
			"ORDER BY "+m_order_col+" "+m_order_type+" ");
	


			}
					
			if(m_screen.equals("Approval 2")){

			
		 rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO APPLICATION_NO,INITCAP(NAME),B.VENDOR_CODE, "+
	   "INITCAP(FULL_NAME), "+
	 	 "TO_CHAR(TOTAL_NET,'999,999,999,999,999,999,999,999.99') TOTAL_NET, "+
	   "TO_CHAR(TOTAL_VAT,'999,999,999,999,999,999,999,999.99') TOTAL_VAT, "+
	   "TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL, "+
	   "TO_CHAR(PURCHASE_ORDER_DATE,'dd-mm-yyyy'),'"+m_order_col_1+"','"+m_order_type+"'  "+
	   ",NVL(A.MOD_USER,'-'),'"+m_username+"',G.REQU_NO,nvl(TO_CHAR(SETTLE_AMT,'999,999,999,999,999,999,999,999.99'),0) "+ 
		 ",SUS_REF_NO,REF_NO  "+
		 "FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
	   ""+m_schema_name+".AF_CO_MAS_VENDORS B, "+
	   ""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	 	 ""+m_schema_name+".AF_CO_MAS_CLIENT D  "+//--//,"+m_schema_name+".AF_CR_PRO_PURORDER_APP_DETAIL E	
		 ","+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT G, "+
	   ""+m_schema_name+".AF_CR_PRO_PUR_ORDER_SETDET Y  "+
		 ","+m_schema_name+".AF_RE_ACC_SUS_PAYMENT H, "+
	   ""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J  "+
		 ","+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL X,"+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS T "+
	 	 "WHERE A.VENDER_CODE=B.VENDOR_CODE  "+
	 	 "AND A.APPLICATION_NO=C.APPLICATION_NO  "+
	 	 "AND C.CLIENT_CODE=D.CLIENT_CODE  "+
		 "AND  H.REF_NO=J.INVOICE_NO  "+
			"and j.purchase_order_no=a.purchase_order_no "+
	   "AND C.APPLICATION_NO=J.APPLICATION_NO   "+ 
		 "AND 	A.PURCHASE_ORDER_NO LIKE UPPER('%')  "+
		 "AND A.ACTIVE_STATUS in ('VERIFY')  "+
		 "AND A.PURCHASE_ORDER_NO=G.PURCHASE_ORDER_NO  "+
		 "AND G.ACTIVE_STATUS='"+m_status+"'  "+
		 "AND G.REQU_NO=Y.REQU_NO  "+
		 "AND USER_ID=AUTHORAIZED_USER  "+
		 "AND C.APPLICATION_NO=X.APPLICATION_NO  "+
		 "AND LIMIT < NVL((TOTAL_NET+TOTAL_VAT),0) "+			
	   "AND Y.INVOICE_NO=J.INVOICE_NO  "+
		 "ORDER BY "+m_order_col+" "+m_order_type+" ");

			}
					
	
					
					
					
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
					out.print("<R14>"+rs.getString(14)+"</R14>");					
					out.print("<R15>"+rs.getString(15)+"</R15>");
					out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(17)+"</R17>");
					out.print("<R18>"+rs.getString(18)+"</R18>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
					



	//---purpose : for payment report-------------------------------------------------------------------------------------------
	//---date :2007-03-28	-------------------------------------------------------------------------------------------
	//---created by :delanjali-------------------------------------------------------------------------------------------
	
	//---purchase order-------------------------------------------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_purchase_order")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();
			
			rs=stmt.executeQuery(" SELECT "+
			" PURCHASE_ORDER_NO "+
		" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,"+m_schema_name+".AF_CO_MAS_CLIENT D "+
			" WHERE  A.PURCHASE_ORDER_NO = UPPER('"+m_val+"') "+
			"AND A.ACTIVE_STATUS=('"+m_ac_status+"') AND A.APPLICATION_NO=C.APPLICATION_NO  "+
			"AND B.VENDOR_CODE=A.VENDER_CODE "+
			"AND D.CLIENT_CODE=C.CLIENT_CODE ");

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}


	//---application no-------------------------------------------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_application")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();

			
			rs=stmt.executeQuery("SELECT APPLICATION_NO "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   upper(APPLICATION_NO) = UPPER('"+m_val+"') and application_status='ACTIVATED' ");
			

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}

	//---application no-------------------------------------------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_application_1")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();

			
			rs=stmt.executeQuery("SELECT APPLICATION_NO "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   UPPER(APPLICATION_NO) = UPPER('"+m_val+"') AND APPLICATION_STATUS IN ('ACTIVATED','VERIFY2','VERIFYL') ");
			

				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}


	//---client no-------------------------------------------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_client")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();

			
			
			rs=stmt.executeQuery("SELECT CLIENT_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE 	UPPER(CLIENT_CODE) = UPPER('"+m_val+"') "+
			"	 AND ACTIVE_STATUS ='"+m_ac_status+"' " );	
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}

	//---invocie no-------------------------------------------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_invoice")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();

			
		rs=stmt.executeQuery(" SELECT "+
    " A.INVOICE_NO "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
    " WHERE UPPER(A.INVOICE_NO) LIKE UPPER('"+m_val+"') "+ 
		" AND A.ACTIVE_STATUS='"+m_ac_status+"' "+
		" AND APPLICATION_STATUS='ACTIVATED' "+
		" AND A.APPLICATION_NO=B.APPLICATION_NO ");
			
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}

	//---inquiry no-------------------------------------------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inquiry")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();

			rs=stmt.executeQuery(" SELECT "+
		      " INQUIRY_CODE "+
		      " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			  	" WHERE UPPER(INQUIRY_CODE) = UPPER('"+m_val+"') "+
					" AND STATUS=('"+m_ac_status+"') ");

		
			
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
	//---finance no-------------------------------------------------------------------------------------------

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_finance")){
			
			    
	    String m_val   				  =  req.getParameter("data_val").trim();
			String m_ac_status      =  req.getParameter("ac_status").trim();

			rs=stmt.executeQuery(" SELECT FINANCE_NO,APPLICATION_NO  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   upper(FINANCE_NO) = UPPER('"+m_val+"') "+
			" AND APPLICATION_STATUS=('ACTIVATED') ");
		
			
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}


//***********************************************************************************

			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_inquiry")){
			
			String m_val = req.getParameter("data_val").trim();
			
			rs=stmt.executeQuery("SELECT INQUIRY_CODE FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
													" WHERE INQUIRY_CODE = UPPER('"+m_val+"')");
		 				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					//out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_price")){
				String m_val = req.getParameter("data_val").trim();
				 				
			rs=stmt.executeQuery("SELECT PRICING_NO FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
			"WHERE PRICING_NO = UPPER('"+ m_val +"')");
					
    	out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }


				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");

			rs=stmt.executeQuery("SELECT QUOTATION_NO,STATUS FROM "+m_schema_name+".AF_MK_PRO_QUOTATION "+
													 "WHERE QUOTATION_NO	= UPPER('"+m_val+"') AND STATUS=('"+m_status+"')");
		 				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }


			else if (m_chksql.trim().equals("m_get_Rate")){
			
				String m_from_date=req.getParameter("start_date");

      	rs=stmt.executeQuery(" SELECT INT_RATE  FROM  "+m_schema_name+".FA_FACTORING_FIN_INT "+
                             " WHERE TO_CHAR(FIN_START_DATE,'MON-YYYY') = TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),'MON-YYYY') ");	 				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }




				else if (m_chksql.trim().equals("m_Rate")){
			
				String m_from_date=req.getParameter("start_date");
				String m_to_date=req.getParameter("end_date");

      	rs=stmt.executeQuery(" SELECT INT_RATE  FROM  "+m_schema_name+".FA_FACTORING_FIN_INT "+
                             "WHERE TO_CHAR(FIN_START_DATE,'MON-YYYY') >= TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),'MON-YYYY') AND "+
                             "TO_CHAR(FIN_START_DATE,'MON-YYYY') <= TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MON-YYYY')	");	 				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }

	 else if (m_chksql.trim().equals("check_active_status")){
			
				String user_id=req.getParameter("data_val");

				rs=stmt.executeQuery(  " SELECT "+
					" USER_ID, "+
					" NAME, "+
					" USER_TYPE, "+
					" EMP_ID, "+
					" DIVISION_CODE, "+
					" DESIGNATION_CODE "+ 
					" FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" WHERE UPPER(USER_ID) = UPPER('"+user_id+"') AND ACTIVE_STATUS='Y' ");	

			out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
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


