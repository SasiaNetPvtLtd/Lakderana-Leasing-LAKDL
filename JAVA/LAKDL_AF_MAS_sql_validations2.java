import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_sql_validations2 extends javax.servlet.http.HttpServlet {
	
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
	public  void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
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
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			

		
		//--------------------- ID  			:1.42 Vendor Creation Process ---------------------------------//
		//---------------------Purpose 	  :To validate the Vendor Code-----------------------------------
		//---------------------Name       :Delanjali---------------------------------------------------
		//---------------------Date       :27-09-2006--------------------------------------------------
		
				
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_vendor_creation2")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");						

				rs= stmt.executeQuery ("SELECT A.VENDOR_CODE,A.NAME,A.CATEGORY,nvl(A.TYPE,'-'),A.ACTIVE_STATUS,A.DEFAULT_VALUE, "+
				" B.BRANCH,B.LOCATION_CODE,B.TITLE,B.FIRST_NAME,B.LAST_NAME,B.ID_NO,B.ADDRESS,B.CITY_CODE,B.ACTIVE_STATUS, "+
				" B.DEFAULT_VALUE,REPLACE(C.TEL_NO,'null','-'),nvl(REPLACE(C.FAX_NO,'null','-'),'-'),C.ACTIVE_STATUS,NVL(D.CITY_DESC,'-'), "+
				" NVL(B.COUNTRY_CODE,' '),"+ //added by nuwan de silva 20-11-2007
				" NVL("+m_schema_name+".AF_CO_GET_COUNTRY_NAME(B.COUNTRY_CODE),' ') "+ //added by nuwan de silva 20-11-2007
				"FROM "+m_schema_name+".AF_CO_MAS_VENDORS A, "+
				""+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION B,"+m_schema_name+".AF_CO_MAS_VENDOR_LOC_CONTACT C, "+
				" "+m_schema_name+".AF_CO_MAS_CITY D "+
				//"WHERE (UPPER(A.VENDOR_CODE) LIKE UPPER('%"+m_val+"%') "+
				"WHERE UPPER(A.VENDOR_CODE) = UPPER('"+m_val+"') "+
				//"OR  UPPER(A.NAME) LIKE UPPER('%"+m_val+"%')) "+
				"AND A.ACTIVE_STATUS=UPPER('"+m_status+"') "+
				"AND A.VENDOR_CODE=B.VENDOR_CODE "+
				"AND C.VENDOR_CODE=B.VENDOR_CODE "+
				"AND B.BRANCH=C.BRANCH_CODE "+
				"AND B.CITY_CODE =D.CITY_CODE ORDER BY  A.VENDOR_CODE,BRANCH ");

 
	
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
					out.print("<R21>"+rs.getString(21)+"</R21>");//added by nuwan de silva on 20-11-07
					out.print("<R22>"+rs.getString(22)+"</R22>");//added by nuwan de silva on 20-11-07
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
						else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_team")){
			
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			
			rs= stmt.executeQuery ("SELECT SUB_TEAM_ID,SUB_TEAM_DESC,NVL(SUB_TEAM_HEAD,'N/A'),NVL(DIVISION_CODE,'N/A'),NVL(SUB_DIVISION_CODE,'N/A') FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS "+
			" WHERE UPPER(SUB_TEAM_ID)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
			
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
			
			else if (m_chksql.trim().equals("m_prime_LAKDL_count_client_contracts")){
			
			String m_val = req.getParameter("client_code").trim();
						
			rs= stmt.executeQuery ("SELECT COUNT(a.application_no) "+
			                       " FROM "+m_schema_name+".af_co_pro_application_details a "+
			                       " WHERE UPPER(A.client_code) =UPPER('"+m_val+"') "+
			                       " AND   A.application_status ='ACTIVATED' ");
			
			out.print("<DATA>");
			while(rs.next()){
			out.print("<ITEM>");
			out.print("<R1>"+rs.getInt(1)+"</R1>");
			out.print("</ITEM>");
			}
			out.print("</DATA>");
			
			}
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_assign_team")){
			
			String m_val = req.getParameter("data_val").trim();
			//String m_status = req.getParameter("ac_status");	
				
						
				rs= stmt.executeQuery ("SELECT "+m_schema_name+".AF_CO_GET_USER(A.USER_ID),"+m_schema_name+".AF_CO_GET_EMP_NAME(B.emp_code),B.emp_code,B.DIVISION_CODE FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+
				" "+m_schema_name+".co_co_mas_employee B "+
				"WHERE UPPER(A.TEAM_ID)=UPPER('"+m_val+"') AND UPPER(A.USER_ID)=UPPER(B.emp_code)");
      

				
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
				
			 else if (m_chksql.trim().equals("m_prime_chk_display_loan_facilities")){
			
			 String m_val = req.getParameter("data_val").trim();
					
								
				rs= stmt.executeQuery ("SELECT A.finance_no,  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.client_code) "+
				" FROM "+m_schema_name+".af_co_mas_loan_facili_assign a, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b "+
				" where a.finance_no=b.finance_no "+
				" and a.active_status='Y' "+
				" AND UPPER(A.loan_facility_no)=UPPER('"+m_val+"') ");
      

				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     	}

				
				
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_assign_team_2")){
			
			String m_val = req.getParameter("data_val").trim();
				
						
      
			rs= stmt.executeQuery (" SELECT "+
			" DISTINCT C.SUB_TEAM_ID, "+
			" SUB_TEAM_DESC ,"+
			" "+m_schema_name+".AF_CO_GET_EMP_NAME_TEAM(SUB_TEAM_HEAD), "+// TEAM_HEAD, "+
			" A.DIVISION_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_TEAMS a ,"+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN b, "+m_schema_name+".AF_CO_MAS_SUB_TEAMS C "+
			" WHERE A.TEAM_ID=B.TEAM_ID "+
			" AND C.SUB_TEAM_ID=B.SUB_TEAM_ID "+
			" AND UPPER(A.TEAM_ID)=UPPER('"+m_val+"') "+
			" AND A.ACTIVE_STATUS='Y' "+
			" AND B.ACTIVE_STATUS='Y' "+
			" AND C.ACTIVE_STATUS='Y' ");
				
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



			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_team_desc")){
			
			String m_val = req.getParameter("data_val").trim();
			
			rs= stmt.executeQuery ("SELECT SUB_TEAM_ID,SUB_TEAM_DESC,NVL(SUB_TEAM_HEAD,'N/A'),NVL(DIVISION_CODE,'N/A'),NVL(SUB_DIVISION_CODE,'N/A') FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS"+
			" WHERE UPPER(SUB_TEAM_DESC)=UPPER('"+m_val+"') ");
			
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
			

			
			


       //added by nuwan de silva on 14-11-07---------------------------------------------------
   			else if (m_chksql.trim().equals("m_get_client_group_members")){
				String m_val = req.getParameter("data_val").trim();					
				
				rs= stmt.executeQuery ( " SELECT "+
				" MEMBER_ID, "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(MEMBER_ID), "+
				" CLIENT_STATUS "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
				" WHERE GROUP_ID='"+m_val+"' "+
				" AND CLIENT_TYPE='O' ");
 
	
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


			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_vendor_creation3")){
				String m_val = req.getParameter("data_val").trim();					

				rs= stmt.executeQuery ("SELECT A.VENDOR_CODE,A.NAME,A.CATEGORY,nvl(A.TYPE,'-'),A.ACTIVE_STATUS,A.DEFAULT_VALUE, "+
				"B.BRANCH,B.LOCATION_CODE,B.TITLE,B.FIRST_NAME,B.LAST_NAME,B.ID_NO,B.ADDRESS,B.CITY_CODE,B.ACTIVE_STATUS, "+
				"B.DEFAULT_VALUE,REPLACE(C.TEL_NO,'null','-'),REPLACE(C.FAX_NO,'null','-'),C.ACTIVE_STATUS "+
				"FROM "+m_schema_name+".AF_CO_MAS_VENDORS A, "+
				""+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION B,"+m_schema_name+".AF_CO_MAS_VENDOR_LOC_CONTACT C "+
				"WHERE UPPER(A.VENDOR_CODE) = UPPER('"+m_val+"') "+
				//" OR  UPPER(A.NAME) LIKE UPPER('%"+m_val+"%')) "+
				//"AND A.ACTIVE_STATUS=UPPER('"+m_status+"') "+
				"AND A.VENDOR_CODE=B.VENDOR_CODE "+
				"AND C.VENDOR_CODE=B.VENDOR_CODE "+
				"AND B.BRANCH=C.BRANCH_CODE ");

 
	
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

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			

			//----added by delanjali-on 2007-09-27-------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_termination")){
			String m_val = req.getParameter("data_val").trim();					
				rs= stmt.executeQuery ("SELECT TERMINATION_NO "+
														   "FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
															 "WHERE UPPER(TERMINATION_NO)=UPPER('"+m_val+"') ");
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");

					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_receipt_no")){
		
		  String m_val = req.getParameter("data_val").trim();

		 rs= stmt.executeQuery ( "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
			                      "(SELECT ROWNUM NO, REC_NO,REC_AMOUNT, "+
			                      " EFF_VALDATE,CHEQUE_NO,CLIENT_CODE, "+
														"PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
														"        (REC_NO LIKE UPPER('%"+m_val+"%') OR "+
														"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_val+"%') OR "+
														"        UPPER(B.NIC_NO)    LIKE UPPER('%"+m_val+"%') OR "+
														"        A.CLIENT_CODE LIKE UPPER('%"+m_val+"%')) AND "+
														"        A.STATUS='REC' "+
												" ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME )) P ");  	
		
			
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
				out.print("</DATA>");
			

	  }					
			
			
			
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_client_creation_director")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_help_status = req.getParameter("tmp_help_status").trim();
				
				if(m_help_status.equals("N"))
				{

				rs= stmt.executeQuery (" SELECT  NAME,NIC_NO,STAKE,NO_OF_SHARES,VALUE,POSITION,ADDRESS "+//modified by nuwan de silva 07-09-07
  				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS"+
        " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R6>"+rs.getString(7)+"</R6>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				}
				else if(m_help_status.equals("Y"))
				{
				  
					rs= stmt.executeQuery (" SELECT  NAME,NIC_NO,STAKE,NO_OF_SHARES,VALUE,POSITION,ADDRESS "+ //modified by nuwan de silva 07-09-07
  				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS_TM"+
          " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"')");
				
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
				
		  }
			 
				// added by nuwan de silva on 10-12-2007 ___________________________________________
			 
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_model_creation")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				//rs= stmt.executeQuery ("SELECT MODEL_CODE,DESCRIPTION,MAKE_CODE,FUEL_TYPE,TAX_RATE,TAX_FOR_LEASE,DEFAULT_VALUE,ITEM_SUB_CAT,"+m_schema_name+".AF_CO_GET_MAKE_DESC(MAKE_CODE)  FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
				//" WHERE (UPPER(MODEL_CODE)=UPPER('"+m_val+"') OR UPPER(DESCRIPTION)=UPPER('"+m_val+"')) AND ACTIVE_STATUS='"+m_status+"' ");
				
				rs= stmt.executeQuery (" SELECT "+
				" A.MODEL_CODE, "+
				" A.DESCRIPTION,  "+ 
				" MAKE_CODE,  "+
				" FUEL_TYPE,  "+ 
				" TAX_RATE,  "+
				" TAX_FOR_LEASE,  "+
				" A.DEFAULT_VALUE,  "+
				" NVL(ITEM_SUB_CAT,'-') ITEM_SUB_CAT,  "+
				" "+m_schema_name+".AF_CO_GET_MAKE_DESC(MAKE_CODE) MAKE_DESC , "+
				" B.ENGINE_CAPACITY, "+
				" B.OPTION_TYPE, "+
				" B.COUNTRY_CODE, "+
				" B.YEAR_OF_MANUFACTURE "+
				" FROM "+m_schema_name+".AF_CO_MAS_MODEL A, "+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
				" WHERE A.MODEL_CODE=B.SUB_CODE "+
				" AND (UPPER(A.MODEL_CODE)=UPPER('"+m_val+"') OR UPPER(A.DESCRIPTION)=UPPER('"+m_val+"')) AND A.ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getDouble(5)+"</R5>");
					out.print("<R6>"+rs.getDouble(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getDouble(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getDouble(13)+"</R13>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
		}
		
		
		//__________________  User Access Rights ________________________________________________	
		//___________________ Nuwan de silva on 13-12-2007_______________________________________
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_group_access")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT A.STATUS,B.ROW_ID "+
				                       "FROM "+m_schema_name+".CO_CO_MAS_GROUP_USER_ACCESS A,"+
															 "     "+m_schema_name+".CO_CO_MAS_USER_SCREEN B "+
															 "WHERE UPPER(A.GROUP_ID)=UPPER('"+m_val+"') AND "+
															 "      A.SCREEN_NAME=B.SCREEN_NAME AND "+
															 "      B.DISPLAY_STATUS='Y' AND B.SUB_OPTION_STATUS='N' ");
			
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			 /* -------------------   Added By :Nuwan De Silva------------------------------------------------------
		  --------------------  Date     :13-12-2007---------------------------------------------------------*/
		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_group_user")){
			
			String m_val = req.getParameter("data_val").trim();
			
									
			rs= stmt.executeQuery ("SELECT GROUP_ID,GROUP_DESC "+
  	  " FROM "+m_schema_name+".CO_CO_MAS_GROUP "+
			" WHERE UPPER(GROUP_ID)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			
     }
			
			
			
				//----------------------- Modified by Dineth Meemanage------------------------------
				//----------------------- On 30th July 2008-----------------------------------------
				else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_target_months")){
				
				String sql1      = "SELECT PRECENTAGE FROM LAKDL.AF_CO_MAS_TARGET_MONTHS ORDER BY AGE";
				rs=stmt.executeQuery(sql1);
				boolean more 		 = rs.next();
				
				out.print("<DATA>");
				while(more){
				out.print("<ITEM>");
				out.print("<R1>"+rs.getDouble(1)+"</R1>");
				out.print("</ITEM>");
				more 		 = rs.next();
				}
				out.print("</DATA>");

				
				}
				
			else if (m_chksql.trim().equals("m_base_code_check_LAKDL_AF_MAS_display_var_base_rate")){//Added By Sandun on 12-09-2008
				String m_base = req.getParameter("base_code");
				String m_date = req.getParameter("app_date");
				
				String sql1      = " SELECT BASE_CODE, "+
													 " TO_CHAR(APPLY_DATE,'MM-DD-YYYY') "+
													 " FROM "+m_schema_name+".AF_CO_PRO_INTEREST_BASE_RATE "+
													 " WHERE BASE_CODE='"+m_base+"' AND APPLY_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') ";
				rs=stmt.executeQuery(sql1);
				boolean more 		 = rs.next();
				
				out.print("<DATA>");
				while(more){
				out.print("<ITEM>");
				out.print("<BASE>"+rs.getString(1)+"</BASE>");
				out.print("<APPDATE>"+rs.getString(2)+"</APPDATE>");
				out.print("</ITEM>");
				more 		 = rs.next();
				}
				out.print("</DATA>");

				
				}	
				
				else if (m_chksql.trim().equals("get_date_format")){
			
	  String m_date          =  req.getParameter("date").trim();
		
				rs=stmt.executeQuery(" SELECT  "+
				  " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD'),    "+
					" TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MON') ,  "+
					" TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'YYYY')   "+
					" FROM DUAL");
				 
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
				
				
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_inspection_details")){
			
			
		
			String m_val = req.getParameter("data_val").trim();
			String m_status = req.getParameter("ac_status");	
			
			System.out.println(m_val+"m_val");
			
			
			
			rs= stmt.executeQuery (" SELECT  FEILD_DES,NVL(OK_VAL,'-'),NVL(NEED_ATT,'-') FROM  AF_MK_VEHICLE_INSPEC_DET_LIST WHERE FINANCE_NO = '"+m_val+"'  ");
			
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
			
else if(m_chksql.trim().equals("get_bussiness_vol")){ //Added By Sandun on 18-11-2008

        String m_month  = req.getParameter("budget_month");
        String m_type   = req.getParameter("vol_type"); //added by ns on 07-10-2010
			
				rs = stmt.executeQuery(
				" SELECT A.BUDGET_MONTH,  "+//1
				" NVL(A.HEAD_OFFICE_BUDGET,0), "+ //2
				" NVL(A.BRANCH_BUDGET,0), "+ //3
				" NVL(A.BIKE_BUDGET,0) "+ //4
				" FROM "+m_schema_name+".AF_CO_MAS_BSNESS_VOL_BUDGET A "+
				" WHERE A.BUDGET_MONTH               = '"+m_month+"' "+
				" AND   NVL(A.VOLUME_TYPE,'BUDGET')  = '"+m_type+"' ");	
				
				
				out.print("<DATA>");
				
				if(rs.next()){
				out.print("<ITEM>");
				out.print("<R1>"+rs.getString(1)+"</R1>");
				out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");
				out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
				out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
				out.print("</ITEM>");
				}
				
				out.print("</DATA>");
       
}

			else if(m_chksql.trim().equals("odi_target")){ //Added By Sandun on 19-06-2009

      String m_month  = req.getParameter("target_month");
			
			rs = stmt.executeQuery(" SELECT NVL(A.ODI_RATE,0) "+//1
														 " FROM "+m_schema_name+".AF_CO_MAS_ODI_TARGET_SETUP A "+
														 " WHERE A.TARGET_MONTH  =  '"+m_month+"' ");	
				
				out.print("<DATA>");
				
				if(rs.next()){
				out.print("<ITEM>");
				out.print("<R1>"+rs.getDouble(1)+"</R1>");
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


