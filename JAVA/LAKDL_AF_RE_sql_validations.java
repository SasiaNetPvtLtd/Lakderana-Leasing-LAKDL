import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_sql_validations extends javax.servlet.http.HttpServlet {
	
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
		
		Connection conn =null;
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
			
			String m_username = m_sn_methods.username;
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
			//added by nuwan de silva on 19-09-07------------------------
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
			//added by kanishka dilshan on 27-05-2013
			else if(m_chksql.trim().equals("get_insurance_due_date")){ 
				String m_date = req.getParameter("date");
				String m_type = req.getParameter("type");
				//int days=30;  //45 [request from lakdl] - modified by kanishka dilshan on 13-08-2013 // commented by udara on 09-09-2013
				
				// added by udara on 09-09-2013
				int days=30;
				rs = stmt.executeQuery(" SELECT NVL(CURRENT_PERIOD,0) FROM "+m_schema_name+".AF_CO_MAS_GRACE_PERIOD ");
				
				if(rs.next()){
					days = rs.getInt(1);
				}
				// end by udara on 09-09-2013
				
				if(!m_type.equals("INSURANCE")){
					days=0;
				}
				rs = stmt.executeQuery(" SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY')+"+days+",'DD-MM-YYYY') FROM DUAL ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
				
			}
			//added by nuwan de silva 05-11-07-----------------
			else if(m_chksql.trim().equals("get_Repossess_no")){
				
				String m_reposs_no = req.getParameter("repossess_no");
				
				rs = stmt.executeQuery(" SELECT A.REPOSSESSION_NO, A.FINANCE_NO, A.SEIZER_CODE, "+
					"        A.LETTER_VALIDITY_PERIOD, A.INVOICE_AMOUNT, "+
					"        A.INVOICE_AMOUNT_CURR, A.EXCHANGE_RATE, "+
					"      	A.TRN_CURR_CODE,A.VEHICLE_INVENTORY_STATUS, "+
					"      	A.INVENTORY_NO,A.TRN_DATE, A.REPOSSESSED_DATE, "+
					"        TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),A.ACTIVE_STATUS, "+
					"        B.FIRST_NAME || ' ' || B.LAST_NAME , "+	 //ADDED BY NUWAN DE SILVA 13-06-07
					"        A.PRO_INVOICE_NO, "+ //ADDED BY NUWAN DE SILVA 31-10-07
					"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE), "+ //ADDED BY NUWAN DE SILVA 31-10-07
					"        NVL(A.REPOSSESS_TYPE,'-'), "+
					"        NVL(A.REPOSSESS_OFFICER,'-'), "+
					"        NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.REPOSSESS_OFFICER),'-') "+ //ADDED BY NUWAN DE SILVA 1-11-07
					" FROM   "+m_schema_name+".AF_RE_PRO_REPOSSESSION A ,"+m_schema_name+".AF_CO_MAS_SEIZER  B ,"+
					"        "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					" WHERE  A.SEIZER_CODE=B.SEIZER_CODE(+) AND A.FINANCE_NO=C.FINANCE_NO  AND REPOSSESSION_NO = '"+m_reposs_no+"' ");	
				
				
				boolean flag = rs.next();
				out.println("<DATA>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<REP>"     + rs.getString(1)  + "</REP>");
					out.println("<FIN>"     + rs.getString(2)  + "</FIN>");
					out.println("<SEZ>"     + rs.getString(3)  + "</SEZ>");
					out.println("<LVP>"     + rs.getString(4)  + "</LVP>");
					out.println("<INA>"     + rs.getString(5)  + "</INA>");
					out.println("<IAC>"     + rs.getString(6)  + "</IAC>");
					out.println("<EXR>"     + rs.getString(7)  + "</EXR>");
					out.println("<TCC>"     + rs.getString(8)  + "</TCC>");
					out.println("<VIS>"     + rs.getString(9)  + "</VIS>");
					out.println("<INN>"     + rs.getString(10) + "</INN>");
					out.println("<TRD>"     + rs.getString(11) + "</TRD>");
					out.println("<RED>"     + rs.getString(12) + "</RED>");
					out.println("<EFD>"     + rs.getString(13) + "</EFD>");
					out.println("<ACT>"     + rs.getString(14) + "</ACT>");
					out.println("<SEN>"     + rs.getString(15) + "</SEN>"); //ADDED BY NUWAN DE SILVA 13-06-07
					out.println("<INV>"     + rs.getString(16) + "</INV>"); //ADDED BY NUWAN DE SILVA 31-10-07
					out.println("<NAM>"     + rs.getString(17) + "</NAM>"); //ADDED BY NUWAN DE SILVA 31-10-07
					out.println("<RTY>"     + rs.getString(18) + "</RTY>"); //ADDED BY NUWAN DE SILVA 1-11-07
					out.println("<ROF>"     + rs.getString(19) + "</ROF>"); //ADDED BY NUWAN DE SILVA 1-11-07
					out.println("<RON>"     + rs.getString(20) + "</RON>"); //ADDED BY NUWAN DE SILVA 1-11-07
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
			}
			
			
			//added by nuwan de silva on 26-10-07------------------
			else if(m_chksql.trim().equals("get_pod_cheques_dates")){
				
				String m_date = req.getParameter("m_date");
				int m_count = Integer.parseInt(req.getParameter("count"));
				out.println("<DATA>");
				for(int i=0;i<m_count;i++){
					
					rs = stmt.executeQuery(" SELECT TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),'"+i+"'),'DD'), "+
						" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),'"+i+"'),'MM'), "+
						" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),'"+i+"'),'YYYY') "+
						" FROM DUAL ");
					
					
					if(rs.next()){
						out.println("<ITEM>");
						out.println("<R1>"+rs.getString(1)+"</R1>");
						out.println("<R2>"+rs.getString(2)+"</R2>");
						out.println("<R3>"+rs.getString(3)+"</R3>");
						out.println("</ITEM>");
					}
					
				}
				out.println("</DATA>");      
				
			}
			
			
			else if(m_chksql.trim().equals("check_data")){
				//String f_month = req.getParameter("f_month");
				//String f_year = req.getParameter("f_year");
				//String s_date="01-"+f_month+"-"+f_year;	
				String m_from_date = req.getParameter("data_val");
				rs = stmt.executeQuery(" SELECT "+										
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,A.NAME ,NVL(C.TARGET_AMT,'0.00') "+
					"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
					" WHERE C.TARGET_START_DATE>=TO_DATE('"+m_from_date+"','DD-MON-YYYY') "+
					" AND   C.TARGET_START_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+m_from_date+"','DD-MON-YYYY'),12) ) "+
					"	AND   A.EMP_ID=B.EMP_CODE "+
					"	AND   A.USER_ID=C.USER_ID "+
					"	AND   A.ACTIVE_STATUS='Y' ");
				
				
				out.println("<DATA>");
				while(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getString(3)+"</R3>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
				
				
			}
			
			
			//Added by nuwan de silva on 07-02-2008________________________________________		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_Assign_Collection_Officer")){
				String m_val = req.getParameter("data_val").trim();
				rs= stmt.executeQuery ("SELECT  EMP_CODE "+
					"FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
					" WHERE UPPER(EMP_CODE)=UPPER('"+m_val+"')  AND ACTIVE_STATUS='Y' ");
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//Added by nuwan de silva on 07-02-2008________________________________________		
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_Assign_Collection_Officer_New")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ("SELECT  EMP_CODE "+
					"FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
					" WHERE UPPER(EMP_CODE)=UPPER('"+m_val+"') AND UPPER(EMP_CODE)!=UPPER('"+m_val2+"') AND ACTIVE_STATUS='Y' ");
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			
			//added by nuwan de silva on 16-10-07------------------
			else if(m_chksql.trim().equals("get_to_month_and_year"))
			{
				String m_from_date = req.getParameter("data_val");
				rs = stmt.executeQuery("SELECT TO_CHAR((ADD_MONTHS(TO_DATE('"+m_from_date+"','DD-MON-YYYY'),11)),'MON'), TO_CHAR((ADD_MONTHS(TO_DATE('"+m_from_date+"','DD-MON-YYYY'),11)),'YYYY')FROM DUAL");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
				
				
			}	
			
			
			//Added by Chandana on 02-10-2007 for reciept screen client state display -------	
			else if (m_chksql.trim().equals("m_chk_LAKDL_AF_RE_val_client_state")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT CLIENT_CODE,ACTIVE_STATUS,DECODE(ACTIVE_STATUS,'B','Black Listed','N','Deactive','Y','Y') "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_val+"') "); 
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_assign_team")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT "+m_schema_name+".AF_CO_GET_USER(A.USER_ID),"+m_schema_name+".AF_CO_GET_EMP_NAME(B.EMP_CODE),B.EMP_CODE,B.DIVISION_CODE "+
					" FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+
					" "+m_schema_name+".CO_CO_MAS_EMPLOYEE B "+
					" WHERE UPPER(A.TEAM_ID)=UPPER('"+m_val+"') "+
					" AND UPPER(A.USER_ID)=UPPER(B.EMP_CODE)");
				
				
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
			
			
			
			
			
			//added by nuwan de silva on 20-09-07----------------------------------------	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_location")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");						
				
				rs= stmt.executeQuery ("SELECT LOCATION_CODE,LOCATION_DESC,ADDRESS1,NVL(ADDRESS2,'N/A'),CITY_CODE,NVL(POSTAL_CODE,'N/A'),NVL(COUNTRY_CODE,'N/A') FROM "+m_schema_name+".AF_CO_MAS_LOCATION "+
					" WHERE UPPER(LOCATION_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
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
			
			// added by udara 27-01-2016
			else if (m_chksql.trim().equals("m_prime_chk_run_report_validation")){
			
				/*
				rs= stmt.executeQuery ("SELECT REPORT_STATUS,ENT_USER FROM "+m_schema_name+".AF_MIS_COLL_SUM_RUN_LOG ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				*/
				
				int count = 0;
				
				rs= stmt.executeQuery ("SELECT COUNT(REPORT_STATUS) FROM "+m_schema_name+".AF_MIS_COLL_SUM_RUN_LOG ");

				if(rs.next()){
					count = rs.getInt(1);
				}
				
				if(count>0){
					
					rs= stmt.executeQuery ("SELECT REPORT_STATUS,ENT_USER FROM "+m_schema_name+".AF_MIS_COLL_SUM_RUN_LOG ");
				
					out.print("<DATA>");
					if(rs.next()){
						out.print("<ITEM>");
						out.print("<R1>"+rs.getString(1)+"</R1>");
						out.print("<R2>"+rs.getString(2)+"</R2>");
						out.print("</ITEM>");
					}
					out.print("</DATA>");
					
				}
				else{
					out.print("<DATA>");
					out.print("<ITEM>");
					out.print("<R1>COMPLETED</R1>");
					out.print("<R2>-</R2>");
					out.print("</ITEM>");
					out.print("</DATA>");
				}
				
				
			}
			// end by udara 27-01-2016
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_COLLECTION_PROCES_get_receipt")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_bank_date = req.getParameter("bank_date").trim();
				String m_bank_date2 = req.getParameter("bank_date2").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_user_location =req.getParameter("user_location").trim();
				
				
				/*         rs= stmt.executeQuery (" SELECT "+
						" REC_NO, "+
						" NVL(CHEQUE_NO,'-'), "+
						" NVL(PAYER_ACC_NO,'-'), "+
					//    " NVL(PAYER_BRANCH_CODE,'-'), "+
							" "+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE) BANK_NAME, "+
								" NVL(REC_AMOUNT,0.00) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						" WHERE SETTLE_MODE=UPPER('"+m_val2+"') "+
						" AND STATUS=('"+m_status+"') "+
						" AND CURR_CODE= "+
						" (SELECT "+
						" CURR_CODE "+
						" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
						" WHERE ACC_NO=UPPER('"+m_val+"')) ");
								
				*/
				if(m_val.trim().equals("CHEQUE")){
					
					rs= stmt.executeQuery (" SELECT "+
						" REC_NO, "+//1
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(CHEQUE_NO,'-'),'CASH','-'), "+ //2
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(PAYER_ACC_NO,' ' ),'CASH','-'), "+ //3
						//" DECODE(SETTLE_MODE,'CHEQUE',NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),' '),'CASH','-'), "+ //4
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(PAYER_BRANCH_CODE)), ' '),'CASH','-'), "+ //4
						" NVL(REC_AMOUNT,0.00), "+ //5
						" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') ,"+ //6
						" NVL(B.BANK_CODE,'-'), "+  //modified by nuwan de silva 17-07-07 //7
						" NVL(SUBSTR(OTH_COMMENTS,INSTR(OTH_COMMENTS,'@')+1,LENGTH(OTH_COMMENTS)),' ') "+  //8
						","+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)) "+ //9
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
						" WHERE SETTLE_MODE=UPPER('"+m_val+"')  "+
						" AND UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)) LIKE UPPER('"+m_user_location+"%') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_bank_date+"','DD-MM-YYYY')   "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_bank_date2+"','DD-MM-YYYY')   "+
						" AND GROUP_REC_NO IS NULL "+//MODIFIED BY DELANJALI
						" AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE "+
						" AND STATUS=('"+m_status+"') ORDER BY  EFF_VALDATE,REC_NO ");
					
				}
				else
				{
					
					rs= stmt.executeQuery (" SELECT "+
						" REC_NO, "+//1
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(CHEQUE_NO,'-'),'CASH','-'), "+ //2
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(PAYER_ACC_NO,' '),'CASH','-'), "+ //3
						//" DECODE(SETTLE_MODE,'CHEQUE',NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE), ' '),'CASH','-'), "+ //4
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(PAYER_BRANCH_CODE)), ' '),'CASH','-'), "+ //4
						" NVL(REC_AMOUNT,0.00), "+ //5
						" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') ,"+ //6
						" '-' BANK_CODE, "+  //modified by nuwan de silva 17-07-07 //7
						" NVL(SUBSTR(OTH_COMMENTS,INSTR(OTH_COMMENTS,'@')+1,LENGTH(OTH_COMMENTS)),' ') "+  //8
						","+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER)) "+ //9
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						" WHERE SETTLE_MODE=UPPER('"+m_val+"') "+
						" AND UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER)) LIKE UPPER('"+m_user_location+"%') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_bank_date+"','DD-MM-YYYY')  "+ //modified 21-12-07
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_bank_date2+"','DD-MM-YYYY')   "+
						" AND GROUP_REC_NO IS NULL "+//MODIFIED BY DELANJALI
						" AND STATUS=('"+m_status+"') ORDER BY  EFF_VALDATE,REC_NO  ");
				}
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>"); //added by nuwan de silva 17-07-07
					out.print("<R8>"+rs.getString(8)+"</R8>"); //added by nuwan de silva 17-07-07
					out.print("<R9>"+rs.getString(9)+"</R9>"); //added by nuwan de silva 17-07-07
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_deposit_receipts")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				/*    rs= stmt.executeQuery (" SELECT "+
				" REC_NO, "+
				" NVL(CHEQUE_NO,'-'), "+
				" NVL(PAYER_ACC_NO,'-'), "+
			//    " NVL(PAYER_BRANCH_CODE,'-'), "+
					" "+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE) BANK_NAME, "+
						" NVL(REC_AMOUNT,0.00) "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE SETTLE_MODE=UPPER('"+m_val2+"') "+
				" AND STATUS=('"+m_status+"') "+
				" AND CURR_CODE= "+
				" (SELECT "+
				" CURR_CODE "+
				" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
				" WHERE ACC_NO=UPPER('"+m_val+"')) ");
						*/
				
				rs= stmt.executeQuery (" SELECT "+
					" A.RECEIPT_NO, "+
					" NVL(B.CHEQUE_NO,'-'), "+
					" NVL(B.PAYER_ACC_NO,'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(B.PAYER_BRANCH_CODE),'-') BANK_NAME, "+
					" A.AMOUNT, "+
					" NVL(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'-') ,"+
					" NVL(C.BANK_CODE,'-') "+  //modified by nuwan de silva 17-07-07
					" ,NVL(SUBSTR(OTH_COMMENTS,INSTR(OTH_COMMENTS,'@')+1,LENGTH(OTH_COMMENTS)),' ') "+  //8
					","+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)) "+ //9
					" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B ,"+
					" "+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
					" WHERE A.RECEIPT_NO=B.REC_NO AND A.DIPOSIT_NO=UPPER('"+m_val+"') AND  A.STATUS=('"+m_status+"') AND B.PAYER_BRANCH_CODE=C.BRANCH_CODE(+) ");
				
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
			
			//added by nuwan de silva on 07-03-2008------------------------
			else if(m_chksql.trim().equals("get_sub_charge_account_type")){
				
				String m_sub_charge_code = req.getParameter("data_val");
				rs = stmt.executeQuery (" SELECT  ACCOUNT_TYPE "+
					" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
					" WHERE SUB_TYPE_CODE='"+m_sub_charge_code+"' AND ACTIVE_STATUS='Y' ");
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");      
				
			}
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_account_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" ACCOUNT_NO, "+
					" BRANCH_CODE, "+
					" REFERENCE "+
					"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
					" WHERE ACCOUNT_NO = UPPER('"+m_val+"')  AND ACTIVE_STATUS='Y' "); 	
				
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_account_no_receipt")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" ACCOUNT_NO, "+
					" BRANCH_CODE, "+
					" REFERENCE, "+
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE), "+ //added by nuwan de silva 06-06-07
					" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) "+
					"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
					" WHERE ( UPPER(ACCOUNT_NO) =  UPPER('"+m_val+"') OR  "+
					"         UPPER(BRANCH_CODE) = UPPER('"+m_val+"') OR  "+
					"         UPPER("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE)) = UPPER('"+m_val+"')  ) AND "+
					"         UPPER(CLIENT_CODE) = UPPER('"+m_val2+"')  AND "+
					"         ACTIVE_STATUS='Y' "); 	
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_account_no_group_receipt")){ //Added by Chandana for Ref No.874 on 15/10/2007
				
				String m_val = req.getParameter("data_val").trim();
				//String m_val2 = req.getParameter("data_val2").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" ACCOUNT_NO, "+
					" BRANCH_CODE, "+
					" REFERENCE, "+
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE), "+ 
					" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) "+
					"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
					" WHERE ( UPPER(ACCOUNT_NO) =  UPPER('"+m_val+"') OR  "+
					"         UPPER(BRANCH_CODE) = UPPER('"+m_val+"') OR  "+
					"         UPPER("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE)) = UPPER('"+m_val+"')  ) AND "+
					"         ACTIVE_STATUS='Y' "); 	
				
				
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
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_receipt_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" REC_NO,REC_AMOUNT,CHEQUE_NO "+
					"	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO = UPPER('"+m_val+"')  AND STATUS='E' "); 	
				
				
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
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_client_code")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" CLIENT_CODE, "+
					" FULL_NAME, "+
					" ACTIVE_STATUS "+
					"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE UPPER(CLIENT_CODE) = UPPER('"+m_val+"')  AND ACTIVE_STATUS='Y' "); 	
				
				
				/*
				rs= stmt.executeQuery (" SELECT "+
					" A.CLIENT_CODE, "+
						" A.FULL_NAME, "+
						" A.ACTIVE_STATUS "+
						" FROM LAKDL.AF_CO_MAS_CLIENT A, "+
						" LAKDL.AF_CO_PRO_APPLICATION_DETAILS B, "+
						" LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS C "+
						" WHERE (UPPER(A.CLIENT_CODE) = UPPER('"+m_val+"') OR "+
						" UPPER(A.FULL_NAME) LIKE UPPER('%"+m_val+"%') OR "+
						" UPPER(A.NIC_NO)  LIKE UPPER('%"+m_val+"%') OR "+
						" UPPER(A.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_val+"%') OR "+
						" UPPER(B.FINANCE_NO) LIKE UPPER('%"+m_val+"%') OR "+
						" UPPER(C.ENGINE_NO) LIKE UPPER('%"+m_val+"%') OR "+
						" UPPER(C.CHASSIS_NO) LIKE UPPER('%"+m_val+"%') OR "+
						" UPPER(C.REG_NO) LIKE UPPER('%"+m_val+"%')) "+
						" AND A.CLIENT_CODE = B.CLIENT_CODE "+
						" AND B.APPLICATION_NO = C.APPLICATION_NO "+
						" AND A.ACTIVE_STATUS='Y' "); */
				
				
				
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_finance_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" FINANCE_NO, "+
					" CLIENT_CODE, "+
					" CURRENCY_CODE "+
					" ,NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') "+
					"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO = UPPER('"+m_val+"') AND  APPLICATION_STATUS='ACTIVATED' "); 	
				
				
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
			
			
			
			//Added by: Samith Dilshan on 2015-25-27
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_get_finance_no_not_in_termination")){
				
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" A.FINANCE_NO, "+
					" A.CLIENT_CODE, "+
					" A.CURRENCY_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') "+
					"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
					"  WHERE A.FINANCE_NO = UPPER('"+m_val+"') AND  APPLICATION_STATUS='ACTIVATED' "+
					"  AND A.FINANCE_NO NOT IN (SELECT DISTINCT B.FINANCE_NO FROM AF_CR_PRO_TERMINATION B ) "); 	
				
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
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_term_finance_no")){//Added By Sandun on 22-09-2009
				
				String m_val = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" FINANCE_NO, "+
					" CLIENT_CODE, "+
					" CURRENCY_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(FINANCE_NO,CLIENT_CODE,TO_CHAR(SYSDATE,'DD-MM-YYYY'),''),0) AMOUNT "+
					"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE UPPER(FINANCE_NO) = UPPER('"+m_val+"') "+
					" AND  APPLICATION_STATUS IN ('TERM_TO','TERMI','TERMINATED','NORM_TERMI') "); 	
				
				
				out.print("<DATA>");	
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getDouble(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
			}
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_invoice_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT A.INVOICE_NO, A.FINANCE_NO, to_char(A.VALUE_DATE,'DD-MM-YYYY'), "+
					"        A.NET_AMOUNT,A.VAT_AMOUNT,A.TOTAL_AMOUNT,to_char(A.DUE_DATE,'DD-MM-YYYY'),A.CLIENT_CODE CLIENT,  "+
					"        A.REMARKS,A.CURRENCY_CODE, A.EXCHANGE_RATE,A.INVOICE_TYPE "+
					" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
					" WHERE  INVOICE_NO=UPPER('"+m_val+"') AND "+
					//"        OR FINANCE_NO LIKE UPPER('"+m_val+"')) AND "+
					"        ACTIVE_STATUS='Y' AND INVOICE_TYPE NOT IN('INV_GENER')"); 	
				
				
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
			
			
			
			else if(m_chksql.trim().equals("get_excharate")){
				
				String m_curren_code = req.getParameter("CURR_CODE");
				String m_value_date  = req.getParameter("VAL_DATE");
				
				rs = stmt.executeQuery(" SELECT EXCHANGE_RATE,CURR_CODE, TRN_DATE "+
					" FROM   "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
					" WHERE  CURR_CODE=UPPER('"+m_curren_code+"') AND "+
					"        TRN_DATE =(SELECT MAX(TRN_DATE) "+
					"                  FROM    "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
					"                  WHERE   TRN_DATE<=TO_DATE('"+m_value_date+"','DD-MM-YYYY'))");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<EXR>"     + rs.getString(1)  + "</EXR>");
					out.println("<CUR>"     + rs.getString(2)  + "</CUR>");
					out.println("<TRD>"     + rs.getString(3)  + "</TRD>");
					out.println("</ITEM>");
				}	
				out.println("</Root>");
			}	
			
			
			// added by udara on 04-07-2013
			else if(m_chksql.trim().equals("check_premium")){
				String m_fin_no = req.getParameter("FINANCE_NO");
				int fin_count = 0;
				rs = stmt.executeQuery(" "+
					" SELECT COUNT(FINANCE_NO) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
						" WHERE FINANCE_NO = '"+m_fin_no+"' "+
						//" AND INVOICE_TYPE = 'INSURANCE' "+
						//" AND BALANCE_TO_BE_RECEIVED = 0 "+
						" AND ( INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') "+
						" AND BALANCE_TO_BE_RECEIVED > 0 "+
						" AND ACTIVE_STATUS = 'Y' "+ // added by udara on 08-10-2013
					" ");
				
				if(rs.next()){
					fin_count = rs.getInt(1);
				}
				
				out.println("<Root>");
				out.println("  <ITEM>");
				out.println("    <COUNTS>"     + fin_count  + "</COUNTS>");
				out.println("  </ITEM>");
				out.println("</Root>");
				
				
			}
			// end by udara on 04-07-2013
			
			// added by udara on 05-07-2013
			else if(m_chksql.trim().equals("check_app_status")){
				
				String m_fin_no   = req.getParameter("FINANCE_NO");
				String app_status = "";
					rs = stmt.executeQuery(" "+
						" SELECT APPLICATION_STATUS "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
						" WHERE FINANCE_NO ='"+m_fin_no+"' "+
						" ");
					if(rs.next()){
						app_status = rs.getString(1);
					}
					
				out.println("<Root>");
				out.println("  <ITEM>");
				out.println("    <APP_STATUS>"     + app_status  + "</APP_STATUS>");
				out.println("  </ITEM>");
				out.println("</Root>");	
				
			}
			// end by udara on 05-07-2013	
			
			// added by udara on 30-07-2013
			else if(m_chksql.trim().equals("check_vehicle_no")){
				
				String m_app_no     = req.getParameter("APPLICATION_NO");
				String m_vehi_no    = req.getParameter("VEHICLE_NO");
				String m_chasis_no  = req.getParameter("CHASIS_NO");
				String app_status = "N";
				int count = 0;
				
				rs = stmt.executeQuery(" "+
					" SELECT COUNT(*) "+
					" FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+
					//" WHERE  APPLICATION_NO = '"+m_app_no+"' AND   CHASIS_NO = '"+m_chasis_no+"' "+
					" WHERE  CHASIS_NO = '"+m_chasis_no+"' "+
					" ");
				
				if(rs.next()){
					count = rs.getInt(1);
				}
				
				if(count>0)
					app_status = "Y";
				
				out.println("<Root>");
				out.println("  <ITEM>");
				out.println("    <APP_STATUS>"     + app_status  + "</APP_STATUS>");
				out.println("  </ITEM>");
				out.println("</Root>");
				
			}
			
			else if(m_chksql.trim().equals("check_vehicle_no2")){
				
				String m_app_no     = req.getParameter("APPLICATION_NO");
				String m_vehi_no    = req.getParameter("VEHICLE_NO");
				String m_chasis_no  = req.getParameter("CHASIS_NO");
				String app_status = "N";
				int count = 0;
				
				rs = stmt.executeQuery(" "+
					" SELECT COUNT(*) "+
					" FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+
					//" WHERE  APPLICATION_NO = '"+m_app_no+"' AND   VEHICLE_NO = '"+m_vehi_no+"' "+
					" WHERE  VEHICLE_NO = '"+m_vehi_no+"' "+
					" ");
				
				if(rs.next()){
					count = rs.getInt(1);
				}
				
				if(count>0)
					app_status = "Y";
				
				out.println("<Root>");
				out.println("  <ITEM>");
				out.println("    <APP_STATUS>"     + app_status  + "</APP_STATUS>");
				out.println("  </ITEM>");
				out.println("</Root>");
				
			}
			
			// end by udara on 30-07-2013
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_return_realization")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_from_date = req.getParameter("from_date").trim();
				String m_to_date = req.getParameter("to_date").trim();
				
				/*	rs= stmt.executeQuery ("SELECT UPPER(REC_NO),"+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO),TO_CHAR(BANK_DATE,'DD-MM-YYYY'),SETTLE_MODE,RECON_STATUS, "+
					"REALISED_DATE,NVL(UPPER(CHEQUE_NO),'-'),REC_AMOUNT_CURR "+
									"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
									"WHERE STATUS='B' AND REALISED_DATE IS NULL AND SETTLE_MODE IN('CHEQUE','STD_ORD') "+ //modified by nuwan de silva on 30-10-07
									"AND GROUP_REC_NO IS NULL "+//Modified by Delanjali on 2007-09-20
									"AND REC_NO IN   "+
										"(SELECT RECEIPT_NO "+
											"FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
											"WHERE DIPOSIT_NO IN "+
										"(SELECT DIPOSIT_NO "+
											" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
											" WHERE   ACC_NO=UPPER('"+m_val+"') "+
														" AND DIPOSIT_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')	"+
														" AND DIPOSIT_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY')	  "+
														" )) "+
														"ORDER BY BANK_DATE,2,1 ");			*/
				
				
				rs= stmt.executeQuery ("SELECT UPPER(REC_NO),AF_CO_GET_DEPOSIT_NO(REC_NO),TO_CHAR(BANK_DATE,'DD-MM-YYYY'),SETTLE_MODE,RECON_STATUS,  "+
					" REALISED_DATE,NVL(UPPER(CHEQUE_NO),'-'),REC_AMOUNT_CURR  "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  a, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS  b , "+m_schema_name+".AF_CO_PRO_DIPOSIT  c "+
					" where b.diposit_no=c.diposit_no "+
					" and a.rec_no=b.receipt_no "+
					" AND c.DIPOSIT_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')	 "+
					" AND c.DIPOSIT_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY')	   "+
					" and a.status='B' "+
					" AND A.SETTLE_MODE IN('CHEQUE','STD_ORD')   "+
					" AND C.ACC_NO=UPPER('"+m_val+"') "+
					" AND A.REALISED_DATE IS NULL  "+
					" AND A.GROUP_REC_NO IS NULL  "+
					" ORDER BY B.diposit_no ");
				
				
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
					out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_return_realization_del")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_real_date_from = req.getParameter("data_val2").trim();
				String m_real_date_to = req.getParameter("data_val3").trim();
				
				//out.println(m_real_date_to);			
				//out.println(m_real_date_from);			
				rs= stmt.executeQuery (" SELECT UPPER(REC_NO),"+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO),TO_CHAR(BANK_DATE,'DD-MM-YYYY'),SETTLE_MODE,RECON_STATUS, "+
					" TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),UPPER(NVL(CHEQUE_NO,'-')),REC_AMOUNT_CURR "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE RECON_STATUS='Y' AND  "+
					" GROUP_REC_NO IS NULL  AND "+//Added by delanjali on 2007-09-20
					" TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_real_date_from+"','DD-MM-YYYY') AND "+
					" TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_real_date_to+"','DD-MM-YYYY')  AND "+
					" REC_NO IN "+
					"(SELECT RECEIPT_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
					" WHERE DIPOSIT_NO IN "+
					" (SELECT DIPOSIT_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
					" WHERE   UPPER(ACC_NO)=UPPER('"+m_val+"'))) ");
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_return_realization2")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" ACC_NO,"+
					" BRANCH_CODE,"+	
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+	
					" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME "+		
					" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
					" WHERE UPPER(ACC_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('Y')");										
				
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
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_val_deposit_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" DIPOSIT_NO, "+
					//" DIPOSIT_DATE, "+
					" TO_CHAR(DIPOSIT_DATE,'DD-MM-YYYY') DIPOSIT_DATE, "+
					" "+m_schema_name+".AF_CO_GET_SETTLE_MODE(DIPOSIT_NO) SETTLE_MODE, "+
					" NVL(ACC_NO,'-'), "+
					" NVL(BRANCH_CODE,'-'), "+
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
					" NVL(REFERENCE,'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
					" WHERE DIPOSIT_NO=UPPER('"+m_val+"')  AND  STATUS=('"+m_status+"') ");
				
				
				
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
			
			
			//added by nuwan de silva on 16-08-07-----------------------------------	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_val_cheque_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" DISTINCT NVL(CHEQUE_NO,'-') CHEQUE_NO, "+ 
					" NVL(D.FINANCE_NO,'-') FINANCE_NO, "+
					" B.CLIENT_CODE, "+
					" NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+
					" RETURN_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B ,"+
					" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE D "+
					" WHERE A.RECEIPT_NO=B.REC_NO "+
					"  AND B.REC_NO=C.RECEIPT_NO "+
					"  AND C.INVOICE_NO=D.INVOICE_NO "+
					" AND STATUS='RET' "+
					" AND UPPER(CHEQUE_NO) = UPPER('"+m_val+"')  ");
				
				
				
				
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
			
			
			/*-------------------------------------------------------------------------------------------------------------	
				Purpose     :Validate The Account Number
				Used In     :Collection Temp receipts
				Created By  :Nuwan De Silva(02-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_val_account_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.ACC_NO, "+
					" NVL(A.BRANCH_CODE,'-') BRANCH_CODE, "+
					" NVL(B.BRANCH_NAME,'-') BRANCH_NAME, "+
					//" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) BRANCH_NAME, "+
					" NVL(A.ACC_SYS_REFNO,'-') "+
					" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT A ,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
					" WHERE A.BRANCH_CODE=B.BRANCH_CODE AND "+
					" (UPPER(A.ACC_NO)=UPPER('"+m_val+"') OR "+
					" UPPER(B.BRANCH_NAME)=UPPER('"+m_val+"') OR "+
					" UPPER(A.BRANCH_CODE)=UPPER('"+m_val+"') ) AND  "+
					" A.ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
				
				
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
			
			/*-------------------------------------------------------------------------------------------------------------	
					Purpose     :Validate Temp Receipt Number
					Used In     :Collection Temp receipts
					Created By  :Nuwan De Silva(02-11-2006)
				---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_temp_rec_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery ( " SELECT "+
					" A.TEMP_REC_NO, "+
					" A.FINANCE_NO, "+
					//" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
					" A.CLIENT_CODE, "+
					" B.FULL_NAME, "+
					" TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'), "+
					" A.AMOUNT, "+
					" A.SETTELMENT_MODE, "+
					" A.BANK_CODE, "+
					" A.BRANCH_CODE, "+
					" A.ACCOUNT_NO, "+
					" A.CURR_CODE, "+
					" A.EXCHANGE_RATE, "+
					" A.TRN_AMOUNT_CURR, "+
					" A.COLLECTION_OFFICER, "+
					" A.RECEIPT_NO, "+ //15
					" A.REC_BOOK_NO, "+
					" A.CHEQUE_NO, "+
					" NVL(A.RENTAL_OTER_INVOICE,0), "+
					" A.INSURANCE, "+
					" A.LUXURY_TAX, "+
					" A.REVANUE_LICENCE, "+
					" A.RMV_CHARGES, "+		
					" TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+
					" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+
					" NVL(A.THIRD_PARTY_NAME,'-'), "+ //25
					" NVL(A.THIRD_PARTY_ADD,'-'), "+
					" NVL(A.CLIENT_ADD,'-'), "+		
					" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE),'-') BRANCH_NAME "+
					
					" FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT A, "+
					"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
					"  FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
					"  ) B  "+
					"  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
					"  AND   "+
					"  (UPPER(B.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  A.TEMP_REC_NO=UPPER('"+m_val+"') OR  "+
					"  A.FINANCE_NO=UPPER('"+m_val+"')  "+
					"  )  "+
					"  AND A.STATUS=('"+m_status+"')  "+
					"  ORDER BY A.TEMP_REC_NO DESC  ");
				
				//" WHERE A.TEMP_REC_NO = UPPER('"+m_val+"')  AND A.STATUS=('"+m_status+"') ");
				
				
				
				
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
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getString(26)+"</R26>");
					out.print("<R27>"+rs.getString(27)+"</R27>");
					out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Validate Temp Receipt Number
		Used In     :Collection Temp receipts
		Created By  :Nuwan De Silva(02-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_legal_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery ( " SELECT "+
					" A.LEGAL_NO, "+
					" A.FINANCE_NO, "+
					" A.CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ 
					" A.LAWYER_CODE, "+
					" B.NAME_WITH_INITIALS, "+	
					" A.LEGAL_TYPE, "+
					" A.LEGAL_POSITION, "+
					" A.REMARKS, "+
					" TO_CHAR(A.COURT_DATE,'DD-MM-YYYY') COURT_DATE, "+
					" A.AMOUNT_DUE "+
					" FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIVITIES A ,"+m_schema_name+".AF_CO_MAS_LAWYER B "+
					" WHERE A.LAWYER_CODE=B.LAWYER_CODE  "+
					" AND (UPPER(A.LEGAL_NO) = UPPER('"+m_val+"')  "+
					" OR UPPER(A.FINANCE_NO) = UPPER('"+m_val+"')  "+
					" OR UPPER(A.CLIENT_CODE) = UPPER('"+m_val+"') "+
					" OR UPPER(A.LAWYER_CODE) = UPPER('"+m_val+"') "+
					" )AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
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
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate The Client Code
			Used In     :Collection Temp receipts
			Created By  :Nuwan De Silva(02-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_client_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery (" SELECT CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2  "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					" WHERE UPPER(CLIENT_CODE) =UPPER('"+m_val+"') OR "+
					" (UPPER(FULL_NAME) =UPPER('"+m_val+"') OR "+
					" UPPER(ADDRESS1)   =UPPER('"+m_val+"') OR "+
					" UPPER(CITY_CODE)  =UPPER('"+m_val+"') OR "+
					" UPPER(MOBILE_NO)  =UPPER('"+m_val+"') OR "+
					" UPPER(TEL_NO)     =UPPER('"+m_val+"') OR "+
					" UPPER(EMAIL)      =UPPER('"+m_val+"') OR "+
					" UPPER(NIC_NO)     =UPPER('"+m_val+"') OR "+
					" UPPER(BUSINESS_CERTIFICATE_NO) =UPPER('"+m_val+"') AND "+
					" ACTIVE_STATUS=('"+m_status+"') "+
					" 	) "+
					" ORDER BY FULL_NAME  ");
				
				
				
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
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Validate The Client Code
		Used In     :Collection Temp receipts
		Created By  :Nuwan De Silva(02-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_user_id")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" TEAM_HEAD "+
					" FROM "+m_schema_name+".AF_CO_MAS_TEAMS "+
					" WHERE UPPER(TEAM_HEAD)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate The Branch Code
			Used In     :Collection Temp receipts
			Created By  :Nuwan De Silva(02-11-2006)
		  ---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code")){
				
				String m_val = req.getParameter("data_val");
				
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT BRANCH_CODE,BRANCH_NAME,BANK_CODE,ADDRESS1,NVL(ADDRESS2,'N/A'),NVL(CITY_CODE,'N/A'),NVL(TEL_NO,'N/A'),NVL(FAX_NO,'N/A'),DAYS_TO_REALISE,DEFAULT_VALUE,"+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
					" WHERE (UPPER(BRANCH_CODE)=UPPER('"+m_val+"') OR UPPER(BRANCH_NAME)=UPPER('"+m_val+"'))  AND ACTIVE_STATUS='"+m_status+"' ");
				
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
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate The Account Number
			Used In     :Collection Temp receipts
			Created By  :Nuwan De Silva(02-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Receipt_licencee_settlement")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				//rs= stmt.executeQuery ("SELECT BRANCH_CODE,ACC_NO,ACC_SYS_REFNO,CURR_CODE,ACC_CODE,ACC_DESC FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT"+
				//" WHERE UPPER(ACC_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ");
				
				rs= stmt.executeQuery (	" SELECT "+
					" A.ACC_NO ACC_NO, "+
					" B.BRANCH_CODE BRANCH_CODE, "+
					" NVL(B.BANK_CODE,'-') BANK_CODE "+
					" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT A, "+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
					" WHERE A.BRANCH_CODE=B.BRANCH_CODE AND A.ACC_NO=UPPER('"+m_val+"') AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					//		out.print("<R4>"+rs.getString(4)+"</R4>");
					//		out.print("<R5>"+rs.getString(5)+"</R5>");
					//		out.print("<R6>"+rs.getString(6)+"</R6>");					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Validate Currency Code
		Used In     :Collection Temp receipts
		Created By  :Nuwan De Silva(02-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Curr_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT CURR_CODE,CURR_SYMBOL,REP_CURR,TO_CHAR(TRN_DATE,'DD-MM-YYYY'),DEFAULT_VALUE"+
					" FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
					" WHERE UPPER(CURR_CODE)=UPPER('"+m_val+"') "+
					" AND ACTIVE_STATUS=UPPER('"+m_status+"') ");
				
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
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate The Collectin Officer
			Used In     :Collection Temp receipts
			Created By  :Nuwan De Silva(02-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_User")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE, USER_TYPE, EMP_ID,"+
					" DIVISION_CODE, DESIGNATION_CODE,PASSWORD FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" WHERE UPPER(USER_ID)=UPPER('"+m_val+"')   ");
				/*" UPPER(NAME)=UPPER('"+m_val+"') OR  "+
				" UPPER(LOCATION_CODE)=UPPER('"+m_val+"') OR  "+
				" UPPER(USER_TYPE)=UPPER('"+m_val+"') OR  "+
				" UPPER(USER_ID)=UPPER('"+m_val+"') OR  "+
				" UPPER(EMP_ID)=UPPER('"+m_val+"') OR  "+
				" UPPER(DIVISION_CODE)=UPPER('"+m_val+"')) ");	
				*/
				
				
				
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
			
			//added by nuwan de silva on 05-11-07----------------------------------------
			else if(m_chksql.trim().equals("get_SeizerCode")){
				
				String m_seizer_code = req.getParameter("seizer_code");
				
				rs = stmt.executeQuery(" SELECT A.SEIZER_CODE, A.FIRST_NAME, A.LAST_NAME,"+ 
					"        A.ADDRESS1, A.ADDRESS2,A.MOBILE_NO, "+
					"        A.TEL_NO, A.ACTIVE_STATUS,A.CITY_CODE, "+
					"        NVL(A.FEE_PER_CASE,'0'),A.MONTHLY_FEE, A.DEFAULT_VALUE, "+
					"        VALIDITY_PERIOD	"+
					" FROM   "+m_schema_name+".AF_CO_MAS_SEIZER A "+
					" WHERE  SEIZER_CODE	='"+m_seizer_code+"' AND ACTIVE_STATUS='Y' ");
				
				boolean flag = rs.next();
				out.print("<DATA>");
				while(rs.next()){
					out.println("<ITEM>");
					out.println("<SZC>"     + rs.getString(1)  + "</SZC>");
					out.println("<FIN>"     + rs.getString(2)  + "</FIN>");
					out.println("<LAN>"     + rs.getString(3)  + "</LAN>");
					out.println("<AD1>"     + rs.getString(4)  + "</AD1>");
					out.println("<AD2>"     + rs.getString(5)  + "</AD2>");
					out.println("<MOB>"     + rs.getString(6)  + "</MOB>");
					out.println("<TEL>"     + rs.getString(7)  + "</TEL>");
					out.println("<ACT>"     + rs.getString(8)  + "</ACT>");
					out.println("<CIT>"     + rs.getString(9)  + "</CIT>");
					out.println("<FCP>"     + rs.getString(10) + "</FCP>");
					out.println("<MOF>"     + rs.getString(11) + "</MOF>");
					out.println("<DEF>"     + rs.getString(12) + "</DEF>");
					out.println("<VAP>"     + rs.getString(13) + "</VAP>");
					out.println("</ITEM>");
				}
				
				out.print("</DATA>");
				
			}
			
			
			
			
			//added by nuwan de silva on 19-10-07------------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_assign_user_valiate")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE, USER_TYPE, EMP_ID,"+
					" DIVISION_CODE, DESIGNATION_CODE,PASSWORD FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" WHERE UPPER(USER_ID)=UPPER('"+m_val+"')  AND UPPER(USER_ID)!=UPPER('"+m_val2+"') ");
				
				
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
			
			
			
			//added by nuwan de silva on 14-09-07--------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_mk_officer")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("	SELECT "+
					"		USER_ID "+
					"		FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" 	WHERE UPPER(USER_ID)=UPPER('"+m_val+"')  "+
					"   AND ACTIVE_STATUS	 =('"+m_status+"') "+
					"		AND USER_ID     IN (SELECT DISTINCT MK_OFFICER "+
					"												FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					"												WHERE  B.INQUARY_NO=A.INQUIRY_CODE) ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//added by nuwan de silva on 14-09-07--------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_col_officer")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("	SELECT "+
					"		USER_ID "+
					"		FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" 	WHERE UPPER(USER_ID)=UPPER('"+m_val+"')  "+
					"   AND ACTIVE_STATUS	 =('"+m_status+"') "+
					"		AND USER_ID        IN  (SELECT DISTINCT COLLECTION_OFFICER "+
					" 													FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					" 													WHERE COLLECTION_OFFICER IS NOT NULL "+
					" 													AND APPLICATION_NO IN  (  "+
					" 													SELECT DISTINCT B.APPLICATION_NO "+
					" 													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" 													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
					" 													AND    UPPER(MK_OFFICER) LIKE  UPPER('"+m_val2+"%') ))");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Validate The Collectin Officer
		Used In     :Collection Temp receipts
		Created By  :Nuwan De Silva(02-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_team_User")){
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" USER_ID "+
					" FROM "+m_schema_name+".AF_CO_MAS_TEAMS A, "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS B "+
					" WHERE A.TEAM_ID=B.TEAM_ID "+
					" AND  A.ACTIVE_STATUS='Y'  "+
					" AND  B.ACTIVE_STATUS='Y'  "+
					" AND  UPPER(USER_ID)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//added by nuwan de silv aon 20-09-07
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_Re_marketing_offcer")){
				String m_val = req.getParameter("data_val").trim();
				String m_val_2 = req.getParameter("data_val2").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					"		USER_ID,NAME,LOCATION_CODE,USER_TYPE, "+
					"		EMP_ID,ACTIVE_STATUS,DIVISION_CODE,DESIGNATION_CODE "+
					"		FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" 	WHERE   UPPER(USER_ID) 				=UPPER('"+m_val+"')  "+
					" 	AND			UPPER(LOCATION_CODE) 	LIKE 	UPPER('"+m_val_2+"%')  "+
					"   AND ACTIVE_STATUS	 ='Y' "+
					"		AND USER_ID        IN( SELECT DISTINCT MK_OFFICER "+
					"													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					"													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
					"                         AND    UPPER(A.MK_OFFICER) =UPPER('"+m_val+"') ) ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_Re_credit_offcer")){
				String m_val = req.getParameter("data_val").trim();
				String m_val_2 = req.getParameter("data_val2").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					"		EMP_ID,NAME,LOCATION_CODE,USER_TYPE, "+
					"		EMP_ID,ACTIVE_STATUS,DIVISION_CODE,DESIGNATION_CODE "+
					"		FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" 	WHERE   UPPER(EMP_ID) 				=UPPER('"+m_val+"')  "+
					" 	AND			UPPER(LOCATION_CODE) 	LIKE 	UPPER('"+m_val_2+"%')  "+
					"   AND ACTIVE_STATUS	 ='Y' "+
					"		AND EMP_ID        IN( SELECT DISTINCT NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO),'-') CR_OFFICER "+
					"													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					"													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
					"                         AND    UPPER(A.CR_OFFICER) =UPPER('"+m_val+"') ) ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get The Receipt Book Status
			Used In     :Collection Temp receipts
			Created By  :Nuwan De Silva(03-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Receipt_Boook_No")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery ("SELECT "+
					" BOOK_STATUS "+
					" FROM "+m_schema_name+".AF_RE_PRO_COLLECTION_RECEIPT "+
					" WHERE RECEIPT_BOOK_NO=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
				Purpose     :Get The Last Receipt Number
				Used In     :Collection Temp receipts
				Created By  :Nuwan De Silva(03-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Last_Receipt_No")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				
				
				rs= stmt.executeQuery (	 " SELECT "+
					" NVL(MAX(RECEIPT_NO),'-') RECEIPT_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT "+
					" WHERE COLLECTION_OFFICER=UPPER('"+m_val+"') AND REC_BOOK_NO=UPPER('"+m_val2+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Get The Finance Number
		Used In     :Collection Temp receipts
		Created By  :Nuwan De Silva(03-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Temp_Finance_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_status2 = req.getParameter("ac_status2").trim();
				
				
				
				rs= stmt.executeQuery (	" SELECT "+
					//" NVL(FINANCE_NO,'-') FINANCE_NO, "+
					//" NVL(CLIENT_CODE,'-') CLIENT_CODE ,"+
					//" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME "+
					//" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					//" SELECT "+
					" A.FINANCE_NO, "+
					" A.CLIENT_CODE, "+
					" B.FULL_NAME FULL_NAME, "+ 
					" B.TEL_NO TEL_NO,  "+
					" B.NIC_NO NIC_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
					"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
					"  ) B  "+
					"  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
					"  AND   "+
					"  (UPPER(B.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  B.TEL_NO=UPPER('"+m_val+"') OR  "+
					"  B.NIC_NO=UPPER('"+m_val+"') OR  "+
					"  A.FINANCE_NO=UPPER('"+m_val+"')  "+
					"  )  "+
					"  AND A.APPLICATION_STATUS IN('"+m_status+"','"+m_status2+"')  "+
					"  ORDER BY A.APPLICATION_NO DESC  ");
				
				//" WHERE FINANCE_NO=UPPER('"+m_val+"') "+
				//" AND APPLICATION_STATUS IN ('"+m_status+"','"+m_status2+"') ");
				
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
			
			/*-------------------------------------------------------------------------------------------------------------	
	Purpose     :Get The Finance Number
	Used In     :Collection Legal Activities
	Created By  :Nuwan De Silva(08-12-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_activities_Finance_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				
				rs= stmt.executeQuery (	" SELECT "+
					" NVL(FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(CLIENT_CODE,'-') CLIENT_CODE ,"+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
					"APPLICATION_STATUS "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO=UPPER('"+m_val+"') "+
					" AND APPLICATION_STATUS=('"+m_status+"') ");
				
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
			
			
			/*--------------------ID       :1.75 Lawyer Process-------------------------------
		--------------------Purpose    :Lawyer id validation------------------------------------------------
		------------------- Added By   :Nuwan De Silva------------------------------------------------------
		--------------------Date       :25-07-2006---------------------------------------------------------*/
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_activities_Lawyer_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT LAWYER_CODE,FIRST_NAME,LAST_NAME,NAME_WITH_INITIALS FROM "+m_schema_name+".AF_CO_MAS_LAWYER"+
					" WHERE (UPPER(LAWYER_CODE)=UPPER('"+m_val+"') OR UPPER(FIRST_NAME)=UPPER('"+m_val+"') OR UPPER(LAST_NAME)=UPPER('"+m_val+"') ) AND ACTIVE_STATUS='"+m_status+"' ");
				
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
			
			
			/*-------------------------------------------------------------------------------------------------------------	
				Purpose     :Get Application Numbers.
				Used In     :Assignig Lease Process
				Created By  :Nuwan De Silva(03-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					//  " NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.CLIENT_CODE,'-') CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"') "+
					" AND A.COLLECTION_OFFICER IS NULL "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");//Added Nuwan De Silva 19-04-2007
					//out.print("<R8>"+rs.getString(8)+"</R8>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get Application Numbers.
			Used In     :Assignig Lease Process
			Created By  :Nuwan De Silva(03-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no")){
				
				String m_finanace_no = req.getParameter("finanace_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					" NVL(B.FULL_NAME,'-') CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.COLLECTION_OFFICER,'-') COLLECTION_OFFICER, "+
					" NVL(B.CLIENT_CODE,'-') CLIENT_CODE "+ //Added By Nuwan De Silva 19-04-2007
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"')  "+
					" AND (UPPER(A.FINANCE_NO)=UPPER('"+m_finanace_no+"') OR UPPER(B.FULL_NAME)=UPPER('"+m_finanace_no+"') ) "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
	Purpose     :Get The Last Receipt Number
	Used In     :Collection Temp receipts
	Created By  :Nuwan De Silva(03-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Due_Status")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				
				
				
				//	rs= stmt.executeQuery (	" SELECT "+
				//  " NVL(FINANCE_NO,'-') FINANCE_NO, "+
				// " NVL(CLIENT_CODE,'-') CLIENT_CODE "+
				// " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
				//" WHERE FINANCE_NO=UPPER('"+m_val+"') "+
				//" AND APPLICATION_STATUS=UPPER('"+m_status+"') ");
				
				rs= stmt.executeQuery (" SELECT "+
					" INVOICE_NO, "+
					//   " CLIENT_CODE, "+
					" FINANCE_NO, "+
					" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY') INVOICE_DATE, "+
					" NO_OF_DAYS_DUE, "+
					" AMOUNT_DUE, "+
					" COLLECTION_OFFICER, "+
					" TO_CHAR(TRN_DATE,'DD-MM-YYYY') TRN_DATE , "+
					" RECEIPT_NOT_ALLO "+
					" FROM LAKDL.AF_RE_PRO_RPT_COLLECTION_DUE "+
					" WHERE CLIENT_CODE=UPPER('"+m_val+"') AND ADD_MONTHS(TRN_DATE,2)>SYSDATE "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+nf.format(rs.getDouble(8))+"</R8>");
					//   out.print("<R9>"+rs.getString(9)+"</R9>");
					
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate The Repossession number
			Used In     :Collection - Vehicle inventory Process
			Created By  :Nuwan De Silva(10-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" REPOSSESSION_NO, "+
					" FINANCE_NO, "+
					" SEIZER_CODE "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE UPPER(REPOSSESSION_NO) =UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') ");
				
				
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
			
			
			/*--------------Purpose    :Validate Engine Number - Valuation
			----------------Created By :Nuwan De Silva  
			----------------Date       :26/12/2006------------------------  */
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_engine_no")){
				
				String m_engine_no = req.getParameter("data_val_engine_no").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				//	String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("SELECT "+
					" ENGINE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					//	" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					" UPPER(ENGINE_NO)      =UPPER('"+m_engine_no+"') AND "+
					" ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			
			
			
			/*--------------Purpose    :Validate Engine Number - Valuation
			----------------Created By :Nuwan De Silva  
			----------------Date       :26/12/2006------------------------  */
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_chassis_no")){
				
				String m_chassis_no = req.getParameter("data_val_chassis_no").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				//String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("SELECT "+
					" CHASSIS_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					//	" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+ 
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					" UPPER(CHASSIS_NO)      =UPPER('"+m_chassis_no+"') AND "+
					" ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get Customer Name
			Used In     :Collection - Vehicle inventory Process
			Created By  :Nuwan De Silva(10-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_customer_Data")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" NVL(CLIENT_CODE,'-') CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) "+	
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE  UPPER(FINANCE_NO)=UPPER('"+m_val+"') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			/*-------------------------------------------------------------------------------------------------------------	
	Purpose     :Get Customer Name
	Used In     :Collection - Vehicle inventory Process
	Created By  :Nuwan De Silva(10-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_vehicle_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				//  String m_status = req.getParameter("ac_status").trim();
				// String m_status2 = req.getParameter("ac_status2").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					/*  " DISTINCT REG_NO, "+
					" ENGINE_NO, "+
					" CHASSIS_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO =( "+
					" SELECT "+
					" APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO =(SELECT FINANCE_NO FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  WHERE UPPER(SEIZER_CODE)=UPPER('"+m_val2+"'))   "+
					" AND APPLICATION_STATUS IN ('"+m_status+"','"+m_status2+"') ) AND UPPER(VEHICLE_NO) =UPPER('"+m_val+"') ");
						*/
					
					//		" SELECT  "+
					"  REG_NO,  "+
					"  ENGINE_NO,  "+
					"  CHASSIS_NO  "+
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"  WHERE  APPLICATION_NO IN (  "+
					"  SELECT  "+
					"  APPLICATION_NO  "+
					"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					"  WHERE FINANCE_NO IN ( "+
					"  SELECT FINANCE_NO "+
					"  FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					"  WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_val2+"')))    "+
					"  AND PURCHASE_ORDER_NO IS NOT NULL "+
					"  AND UPPER(VEHICLE_NO) NOT IN ( "+
					"  SELECT UPPER(VEHICLE_NO) "+
					"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+ 
					"  WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_val2+"')) "+
					"  AND UPPER(REG_NO)=UPPER('"+m_val+"') ");
				
				
				
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
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get Customer Name
			Used In     :Collection - Vehicle inventory Process
			Created By  :Nuwan De Silva(10-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_vehicle_no_edit")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					//" DISTINCT VEHICLE_NO, "+
					" DISTINCT REG_NO, "+//MOd BY Sandun on 03-07-2009
					" ENGINE_NO, "+
					" CHASSIS_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					//" VEHICLE_NO IN ( "+
					" REG_NO IN ( "+
					" SELECT "+
					" VEHICLE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE UPPER(INVENTORY_NO)=UPPER('"+m_val+"') AND  ACTIVE_STATUS=('"+m_status+"')) ");
				
				
				
				
				
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
			
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
				Purpose     :Get Customer Name
				Used In     :Collection - Vehicle inventory Process
				Created By  :Nuwan De Silva(10-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			//Modified by Mahela on 23-05-2007 (Yard Name)
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_data")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.ASSET_DESCRIPTION, "+
					" A.MILEAGE, "+
					//  " ADVERTISMENT_STATUS, "+
					//  " OFFER_STATU, "+
					" A.COMMENTS, "+
					" A.KEY, "+
					" A.LICENSE, "+
					" A.INSURANCE, "+ 
					" A.VEHICLE_ID_CARD, "+
					" A.CASSETTE, "+
					" A.RADIO, "+
					" A.CD_PLAYER, "+
					" A.TOOL_KIT, "+
					" A.SPEAR_WHEEL, "+
					" A.JACK, "+
					" A.LIGHTER, "+
					" A.FUEL_CAP, "+
					" A.CARPETS, "+
					" A.WHEEL, "+
					" A.BODY, "+
					" A.MIRROR, "+
					" A.LEFT_SIDE_MIRROR, "+
					" A.RIGHT_SIDE_MIRROR, "+
					" A.LEFT_SIGNAL_LIGHT_FRONT, "+
					" A.RIGHT_SIGNAL_LIGHT_FRONT, "+
					" A.LEFT_SIGNAL_LIGHT_REAR, "+
					" A.RIGHT_SIGNAL_LIGHT_REAR, "+
					" A.POLICE_REPORT, "+
					" A.CUSTOMERS_SIGNATURE, "+
					" A.SEIZERS_SIGNATURE, "+
					" A.RECEIVERS_SIGNATURE, "+
					" A.YARD_CODE, "+
					" B.NAME, "+
					" A.D_KEY "+ //added by nuwan de silva on 06-11-07
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A ,"+m_schema_name+".AF_CO_MAS_YARD B "+
					" WHERE A.YARD_CODE=B.YARD_CODE(+) AND UPPER(A.REPOSSESSION_NO)=UPPER('"+m_val+"') "+ 
					" AND UPPER(A.VEHICLE_NO)=UPPER('"+m_val2+"')"+
					" AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
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
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getString(26)+"</R26>");
					out.print("<R27>"+rs.getString(27)+"</R27>");
					out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("<R29>"+rs.getString(29)+"</R29>");
					out.print("<R30>"+rs.getString(30)+"</R30>");
					out.print("<R31>"+rs.getString(31)+"</R31>");
					out.print("<R32>"+rs.getString(32)+"</R32>");
					//	out.print("<R31>"+rs.getString(31)+"</R31>");
					//	out.print("<R32>"+rs.getString(32)+"</R32>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get repossision Number
			Used In     :Collection - Vehicle inventory Process
			Created By  :Nuwan De Silva(10-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_repossission_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" REPOSSESSION_NO, "+
					//" SEIZER_CODE, "+
					" DECODE(REPOSSESS_TYPE,'OFFICER',REPOSSESS_OFFICER,'SEIZER',SEIZER_CODE,'COMPANY',REPOSSESS_OFFICER)  SEIZER_CODE,"+//Sandun on 03-07-2009
					" FINANCE_NO ,"+
					" INVENTORY_NO  "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
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
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get repossision Number
			Used In     :Collection - Vehicle inventory Process
			Created By  :Nuwan De Silva(10-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_repossission_no_edit")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" DISTINCT A.REPOSSESSION_NO, "+
					" A.SEIZER_CODE,  "+
					" B.FINANCE_NO,  "+
					" A.INVENTORY_NO  "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
					" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO)=UPPER('"+m_val+"')  AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
				
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
			
			/*-------------------------------------------------------------------------------------------------------------	
				Purpose     :Get Finanace Number
				Used In     :Collection - LeasevAssign Process
				Created By  :Nuwan De Silva(15-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_validate_finace_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				
				/*rs= stmt.executeQuery (" SELECT "+
				" NVL(FINANCE_NO,'-') FINANCE_NO, "+
				" NVL(CLIENT_CODE,'-') CLIENT_CODE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE UPPER(FINANCE_NO)=UPPER('"+m_val+"') AND APPLICATION_STATUS=('"+m_status+"') ");
				*/	
				
				rs= stmt.executeQuery (" SELECT "+
					" A.FINANCE_NO FINANCE_NO,"+
					" NVL(A.CLIENT_CODE,'-') CITY_CODE, "+
					" NVL(B.FULL_NAME,'-') CLIENT_NAME "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"') AND A.COLLECTION_OFFICER IS NOT NULL "+
					" AND (UPPER(A.FINANCE_NO)=UPPER('"+m_val+"') OR UPPER(B.FULL_NAME)=UPPER('"+m_val+"') ) ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			/*-------------------------------------------------------------------------------------------------------------	
				Purpose     :Validate Yard code
				Used In     :Collection - Vehicle Inventory Process
				Created By  :Nuwan De Silva(24-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_Yard_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					
					" YARD_CODE ,"+
					" NAME  "+			
					" FROM "+m_schema_name+".AF_CO_MAS_YARD "+
					" WHERE (YARD_CODE =UPPER('"+m_val+"') OR UPPER(NAME)=UPPER('"+m_val+"'))  AND ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate Inventory Code
			Used In     :Collection - Vehicle Advertistment Generation 
			Created By  :Nuwan De Silva(27-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_Inv_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					// " DISTINCT INVENTORY_NO  "+
					
					//" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY  "+
					//" WHERE UPPER(INVENTORY_NO) =UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') AND COMPLETED_OFFER_NO IS NULL ");
					
					
					" DISTINCT A.INVENTORY_NO,  "+
					" B.FINANCE_NO,  "+
					" C.CLIENT_CODE , "+
					" C.FULL_NAME  "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
					
					//" WHERE UPPER(INVENTORY_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND COMPLETED_OFFER_NO IS NULL "+
					//" ORDER BY INVENTORY_NO DESC )P)L  "+
					
					" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
					"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
					"  ) C  "+
					"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
					"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
					"  AND   "+
					"  (UPPER(C.FULL_NAME) =UPPER('"+m_val+"') OR  "+
					"  UPPER(C.CLIENT_CODE) =UPPER('"+m_val+"') OR  "+
					"  A.INVENTORY_NO =UPPER('"+m_val+"') OR  "+
					"  B.FINANCE_NO =UPPER('"+m_val+"')  "+
					"  ) "+
					"  AND A.COMPLETED_OFFER_NO IS NULL "+
					"  AND A.ACTIVE_STATUS=('"+m_status+"')  "+
					"  ORDER BY A.INVENTORY_NO DESC  ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate Inventory Code
			Used In     :Collection - Vehicle Advertistment Generation 
			Created By  :Nuwan De Silva(27-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_Veh_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_status2 = req.getParameter("ac_status2").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					//" DISTINCT VEHICLE_NO "+
					//" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					//" WHERE INVENTORY_NO=UPPER('"+m_val2+"') AND ACTIVE_STATUS=UPPER('"+m_status+"')  "+
					//" AND (KEY=('"+m_status2+"') AND INSURANCE=('"+m_status2+"') AND VEHICLE_ID_CARD=('"+m_status2+"')) AND UPPER(VEHICLE_NO) = UPPER('"+m_val+"') ");
					
					//	" SELECT  "+
					" DISTINCT A.VEHICLE_NO , "+
					" C.CHASSIS_NO, "+
					" C.ENGINE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
					" (SELECT "+
					"    Y.FINANCE_NO,X.ENGINE_NO,X.CHASSIS_NO,X.REG_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS X,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
					"    WHERE X.APPLICATION_NO=Y.APPLICATION_NO) C "+
					"    WHERE A.INVENTORY_NO=B.INVENTORY_NO AND "+
					"    C.FINANCE_NO=B.FINANCE_NO AND       "+
					"    A.INVENTORY_NO=UPPER('"+m_val2+"') AND "+
					"    A.KEY=('"+m_status2+"') AND INSURANCE=('"+m_status2+"') AND VEHICLE_ID_CARD=('"+m_status2+"') AND "+
					"    (UPPER(A.VEHICLE_NO)= UPPER('"+m_val+"')  OR"+
					"    UPPER(C.CHASSIS_NO) = UPPER('"+m_val+"')  OR"+
					"    UPPER(C.ENGINE_NO)= UPPER('"+m_val+"')) AND "+
					"    A.ACTIVE_STATUS=UPPER('"+m_status+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Validate Inventory Code
			Used In     :Collection - Vehicle Advertistment Generation 
			Created By  :Nuwan De Silva(27-11-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_advertisment_data")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				/*	" ADVER_NO, "+
				" INVENTORY_NO, "+
				" VEHICLE_NO, "+
				" TO_CHAR(ADVER_DATE,'DD-MM-YYYY') ADVER_DATE, "+
				" AMOUNT, "+
				" VAT_AMOUNT, "+
				" TOTAL_AMOUNT "+
				// " NO_OF_OFFERS "+
				" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
					" WHERE ADVER_NO=UPPER('"+m_val+"')  AND STATUS=UPPER('"+m_status+"')  ");
					*/
				rs= stmt.executeQuery (" SELECT "+
					" D.ADVER_NO, "+
					" D.INVENTORY_NO, "+
					" D.VEHICLE_NO, "+
					" TO_CHAR(D.ADVER_DATE,'DD-MM-YYYY') ADVER_DATE, "+
					" D.AMOUNT, "+
					" D.VAT_AMOUNT, "+
					" D.TOTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL D , "+
					" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
					"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V   "+ 
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+
					"  ) C   "+
					"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  AND D.INVENTORY_NO=A.INVENTORY_NO "+
					"  AND A.INVENTORY_NO=C.INVENTORY_NO   "+
					"  AND    "+
					"  (UPPER(C.FULL_NAME)=UPPER('"+m_val+"') OR   "+
					"  UPPER(C.CLIENT_CODE)=UPPER('"+m_val+"') OR   "+
					"  A.INVENTORY_NO=UPPER('"+m_val+"') OR   "+
					"  B.FINANCE_NO=UPPER('"+m_val+"')  OR "+
					"  D.ADVER_NO=UPPER('"+m_val+"')    "+
					"  )  "+
					"  AND D.STATUS=UPPER('"+m_status+"')  "+ 
					"  ORDER BY D.ADVER_NO DESC   ");
				
				
				
				
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
					//	out.print("<R8>"+rs.getInt(8)+"</R8>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
					Purpose     :Get The Advertise Number
					Used In     :Collection - Vehicle Advertistment Generation 
					Created By  :Nuwan De Silva(28-11-2006)
				---------------------------------------------------------------------------------------------------------------*/	
			
			/*			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers")){
						
							String m_val = req.getParameter("data_val").trim();
							String m_status = req.getParameter("ac_status").trim();
							
					rs= stmt.executeQuery (" SELECT "+
					"  DISTINCT A.ADVER_NO , "+
				"  B.INVENTORY_NO, "+
				"  B.VEHICLE_NO "+
				"  FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS A,LAKDL.AF_RE_PRO_ADVERTISEMENT_DETAIL B "+
				" WHERE A.ADVER_NO=B.ADVER_NO AND UPPER(A.ADVER_NO) =UPPER('"+m_val+"')  AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
								
							
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
						
			*/						
			/*-------------------------------------------------------------------------------------------------------------	
					Purpose     :Get The Advertise Number
					Used In     :Collection - Vehicle Advertistment Generation 
					Created By  :Nuwan De Silva(28-11-2006)
				---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				//modified by nuwan de silva 17-07-07---------------
				rs= stmt.executeQuery (" SELECT "+
					" OFFER_NO, "+
					" NVL(FULL_NAME,'-'), "+
					" NVL(ADDRESS,'-'), "+
					" NVL(TEL_NO,'-'), "+
					" NVL(AMOUNT,0) "+
					" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS "+
					" WHERE UPPER(INVENTORY_NO) =UPPER('"+m_val+"')  AND ACTIVE_STATUS=('"+m_status+"') ORDER BY AMOUNT DESC  ");
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Get The Advertise Number
		Used In     :Collection - Vehicle Advertistment Generation 
		Created By  :Nuwan De Silva(28-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offer_Issue_validation")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				//String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" ADVETIST_NO, "+
					" INVENTORY_NO, "+
					" VEHICLE_NO, "+
					" NVL(OUTSTANDING_VALUE,0) OUTSTANDING_VALUE, "+
					" NVL(OUTSTANDING_INVOICE_VAL,0) OUTSTANDING_INVOICE_VAL, "+
					" NVL(TOTAL_OUTSTANDING_VAL,0) TOTAL_OUTSTANDING_VAL, "+
					" RELEASE_TYPE "+
					" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+
					" WHERE UPPER(ADVETIST_NO)=UPPER('"+m_val+"') AND UPPER(INVENTORY_NO) =UPPER('"+m_val2+"') ");	
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
					out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("<R6>"+nf.format(rs.getDouble(6))+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
					Purpose     :Get The Outstanding Amount
					Used In     :Collection - Vehicle Advertistment Offers
					Created By  :Nuwan De Silva(29-11-2006)
				---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers_outstanding")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				
				rs= stmt.executeQuery (" SELECT SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  "+
					" FROM( "+
					
					" SELECT "+
					" SUM(TOTAL_AMOUNT) TOTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') "+ // AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					
					
					" UNION  "+
					
					" SELECT "+
					" INVOICE_AMOUNT TOTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO=( "+
					" SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"')  "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" ) "+
					
					" UNION "+
					
					
					" SELECT TBL_RE.REMANING_PERIOD*GRENTAL_AMOUNT  TOTAL_AMOUNT "+
					" FROM "+
					" ( "+
					" SELECT (A.PERIOD - B.INSTALMENT_NO) REMANING_PERIOD "+
					" FROM "+
					
					" (SELECT "+
					" PERIOD "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
					" WHERE PRICING_NO IN "+
					" ( "+
					" SELECT "+
					" PRICING_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO=( "+
					" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO= "+
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO= "+
					" (SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) A, "+
					
					" (SELECT "+
					" MAX(INSTALLMENT_NO) INSTALMENT_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE  PRICING_NO IN "+
					" ( "+
					" SELECT "+
					" PRICING_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO=( "+
					" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO= "+
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO= "+
					" (SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					
					" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) B "+
					" ) TBL_RE, "+
					
					" (SELECT "+
					" DISTINCT GRENTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE  PRICING_NO IN "+
					" ( "+
					" SELECT "+
					" PRICING_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO=( "+
					" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO= "+
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION"+
					" WHERE REPOSSESSION_NO= "+
					" (SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					
					" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) TBL_GEN "+
					" ) ");
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			// added by udara 25-11-2013
			else if (m_chksql.trim().equals("m_prime_chk_normal_closing")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				double ceasing_charges     = 0.00;
				double insurance_charges   = 0.00;
				double visiting_charges    = 0.00;
				double total_arrears       = 0.00;
				double rental_amount       = 0.00;

						
				double normal_closing = 0.00;
						
				double total_amount = 0.00;
						
				double other_charges = 0.00; 
						
				double arrears_excess      = 0.00;
				
				double no_of_future_rentals = 0.00;

				
				String m_finance_no = "";
				
				rs=stmt.executeQuery(" "+
					" SELECT  "+
					 " FINANCE_NO  "+
					 " FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  "+
					 " WHERE REPOSSESSION_NO = "+ 
					 " (SELECT  "+
					 " REPOSSESSION_NO  "+
					 " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY  "+
					 " WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+
					 " ");
				
				if(rs.next()){
					m_finance_no = rs.getString(1);
				}
				
				
				
				
				rs=stmt.executeQuery(" "+
									" SELECT "+
									// added by udara on 12-08-2013
									" "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO), "+     // 1 rental amount
									" "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(A.APPLICATION_NO), "+ // 2 future rental
									" NVL(( "+
									" SELECT  SUM(ODI_BAL_AMOUNT) FROM ( "+
					                 " SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT "+
					                  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					                      " "+m_schema_name+".AF_CO_PRO_INVOICE B,  "+
					                      " "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					                      " WHERE A.FINANCE_NO ='"+m_finance_no+"' AND  "+
					                      " A.FINANCE_NO = B.FINANCE_NO AND  "+
					                      " B.ACTIVE_STATUS='Y' AND  "+
					                      " B.INVOICE_NO = C.INVOICE_NO   "+
					
					                " ) "+
									" ) ,0)ODI, "+
																		
									" NVL((	"+									
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       
										" AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') "+ // added by udara on 23-07-2013
								      
									" ),0) INSURANCE_CHARGE, "+
									
									" NVL((	"+									
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       //" AND VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
								       " AND INVOICE_TYPE = 'VISIT'  "+
								       
									" ),0) VISIT_CHARGES, "+
									
									" NVL((	"+	
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       //" AND VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
								       " AND INVOICE_TYPE = 'CEASEINGC'  "+
								       
									" ),0) CEASEINGC, "+
									
									// end by udara on 13-03-2013
									
									" "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS('"+m_finance_no+"',"+m_schema_name+".AF_CO_GET_CLIENT_CODE(AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),TO_CHAR(SYSDATE,'DD-MM-YYYY'),'"+m_username+"') EXCESS_AMNT, "+
									
									" NVL((SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
						            " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
									" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
									" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
									" A.FINANCE_NO = B.FINANCE_NO AND "+
									" B.VALUE_DATE >SYSDATE  AND "+
									" B.ACTIVE_STATUS='Y' AND "+
									" B.INVOICE_TYPE = 'INV_GENER'),0) NEXT_DUE, "+

										
									// added by udara on 16-09-2013
										
									 " NVL((SELECT SUM(B.BAL_TOBE_RECEIVE) "+
							         " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B  "+
							         " WHERE  A.REC_NO=B.REC_NO    "+
							         " AND    A.CLIENT_CODE=B.CLIENT_CODE    "+
							         " AND    B.FINANCE_NO = '"+m_finance_no+"'  "+
							         " AND    B.BAL_TOBE_RECEIVE<>0  "+
							         " AND    NVL ( A.INSURANCE,0 ) > 0  "+
							         " AND    STATUS NOT IN ('CAD','RET','CANCLE','C') ),0) INS_EXCESS  "+
	
									
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
									 	" WHERE A.FINANCE_NO = '"+m_finance_no+"' "+
									" ");
							
							if(rs.next()){

								rental_amount        = rs.getDouble(1);
								no_of_future_rentals = rs.getInt(2);

								arrears_excess       = rs.getDouble("EXCESS_AMNT")+rs.getDouble("ODI")+rs.getDouble("NEXT_DUE")-rs.getDouble("CEASEINGC")-rs.getDouble("VISIT_CHARGES"); // added by udara on 16-09-2013
								//arrears_excess       = rs.getDouble("EXCESS_AMNT")+m_odi_net_value-rs.getDouble("CEASEINGC")-rs.getDouble("VISIT_CHARGES"); // added by udara on 24-07-2013
								ceasing_charges      = rs.getDouble("CEASEINGC"); // added by udara on 13-03-2013
								visiting_charges     = rs.getDouble("VISIT_CHARGES"); // added by udara on 13-03-2013
								insurance_charges    = rs.getDouble("INSURANCE_CHARGE")-rs.getDouble("INS_EXCESS"); // mod by udara on 02-10-2013 // added by udara on 13-03-2013
								
							}
							
							//closing_rate = Double.parseDouble(m_closing_rate);
							

							total_arrears = arrears_excess + ceasing_charges + insurance_charges + visiting_charges; // added by udara on 12-08-2013
							
							//other_charges = ceasing_charges + insurance_charges + visiting_charges; // added by udara on 17-01-20136
						
							normal_closing = (no_of_future_rentals * rental_amount) + total_arrears;

							
				
						out.print("<DATA>");
						//while(rs.next()){
							out.print("<ITEM>");
							out.print("<R1>"+nf.format(normal_closing)+"</R1>");
							out.print("</ITEM>");
						//}
						out.print("</DATA>");
				
			}
			
			
			// end by udara 25-11-2013
			
			
			// added by udara 26-11-2013
			else if (m_chksql.trim().equals("chk_Offers_outstanding_values")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				double ceasing_charges     = 0.00;
				double insurance_charges   = 0.00;
				double visiting_charges    = 0.00;
				double total_arrears       = 0.00;
				double rental_amount       = 0.00;

						
				double normal_closing = 0.00;
						
				double total_amount = 0.00;
						
				double other_charges = 0.00; 
						
				double arrears_excess      = 0.00;
				
				double no_of_future_rentals = 0.00;
				
				double outstanding_val = 0;

				
				String m_finance_no = "";
				
				rs=stmt.executeQuery(" "+
					" SELECT  "+
					 " FINANCE_NO  "+
					 " FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  "+
					 " WHERE REPOSSESSION_NO = "+ 
					 " (SELECT  "+
					 " REPOSSESSION_NO  "+
					 " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY  "+
					 " WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+
					 " ");
				
				if(rs.next()){
					m_finance_no = rs.getString(1);
				}
				
				
				
				
				rs=stmt.executeQuery(" "+
									" SELECT "+
									// added by udara on 12-08-2013
									" "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO), "+     // 1 rental amount
									" "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(A.APPLICATION_NO), "+ // 2 future rental
									" NVL(( "+
									" SELECT  SUM(ODI_BAL_AMOUNT) FROM ( "+
					                 " SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT "+
					                  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					                      " "+m_schema_name+".AF_CO_PRO_INVOICE B,  "+
					                      " "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					                      " WHERE A.FINANCE_NO ='"+m_finance_no+"' AND  "+
					                      " A.FINANCE_NO = B.FINANCE_NO AND  "+
					                      " B.ACTIVE_STATUS='Y' AND  "+
					                      " B.INVOICE_NO = C.INVOICE_NO   "+
					
					                " ) "+
									" ) ,0)ODI, "+
																		
									" NVL((	"+									
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       
										" AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') "+ // added by udara on 23-07-2013
								      
									" ),0) INSURANCE_CHARGE, "+
									
									" NVL((	"+									
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       //" AND VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
								       " AND INVOICE_TYPE = 'VISIT'  "+
								       
									" ),0) VISIT_CHARGES, "+
									
									" NVL((	"+	
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       //" AND VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
								       " AND INVOICE_TYPE = 'CEASEINGC'  "+
								       
									" ),0) CEASEINGC, "+
									
									// end by udara on 13-03-2013
									
									" "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS('"+m_finance_no+"',"+m_schema_name+".AF_CO_GET_CLIENT_CODE(AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),TO_CHAR(SYSDATE,'DD-MM-YYYY'),'"+m_username+"') EXCESS_AMNT, "+
									
									" NVL((SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
						            " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
									" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
									" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
									" A.FINANCE_NO = B.FINANCE_NO AND "+
									" B.VALUE_DATE >SYSDATE  AND "+
									" B.ACTIVE_STATUS='Y' AND "+
									" B.INVOICE_TYPE = 'INV_GENER'),0) NEXT_DUE, "+

										
									// added by udara on 16-09-2013
										
									 " NVL((SELECT SUM(B.BAL_TOBE_RECEIVE) "+
							         " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B  "+
							         " WHERE  A.REC_NO=B.REC_NO    "+
							         " AND    A.CLIENT_CODE=B.CLIENT_CODE    "+
							         " AND    B.FINANCE_NO = '"+m_finance_no+"'  "+
							         " AND    B.BAL_TOBE_RECEIVE<>0  "+
							         " AND    NVL ( A.INSURANCE,0 ) > 0  "+
							         " AND    STATUS NOT IN ('CAD','RET','CANCLE','C') ),0) INS_EXCESS , "+
										
										" NVL("+m_schema_name+".AF_CO_GET_INVO_OUTSTAND_BAL_2('"+m_finance_no+"',NULL,NULL),0) OUTSTANDING_VAL "+
	
									
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
									 	" WHERE A.FINANCE_NO = '"+m_finance_no+"' "+
									" ");
							
							if(rs.next()){

								rental_amount        = rs.getDouble(1);
								no_of_future_rentals = rs.getInt(2);
								
								outstanding_val      = rs.getDouble("OUTSTANDING_VAL");

								arrears_excess       = rs.getDouble("EXCESS_AMNT")+rs.getDouble("ODI")+rs.getDouble("NEXT_DUE")-rs.getDouble("CEASEINGC")-rs.getDouble("VISIT_CHARGES"); // added by udara on 16-09-2013
								//arrears_excess       = rs.getDouble("EXCESS_AMNT")+m_odi_net_value-rs.getDouble("CEASEINGC")-rs.getDouble("VISIT_CHARGES"); // added by udara on 24-07-2013
								ceasing_charges      = rs.getDouble("CEASEINGC"); // added by udara on 13-03-2013
								visiting_charges     = rs.getDouble("VISIT_CHARGES"); // added by udara on 13-03-2013
								insurance_charges    = rs.getDouble("INSURANCE_CHARGE")-rs.getDouble("INS_EXCESS"); // mod by udara on 02-10-2013 // added by udara on 13-03-2013
								
							}
							
							//closing_rate = Double.parseDouble(m_closing_rate);
							

							total_arrears = arrears_excess + ceasing_charges + insurance_charges + visiting_charges; // added by udara on 12-08-2013
							
							//other_charges = ceasing_charges + insurance_charges + visiting_charges; // added by udara on 17-01-20136
						
							normal_closing = (no_of_future_rentals * rental_amount) + total_arrears;

							
				
						out.print("<DATA>");
						//while(rs.next()){
							out.print("<ITEM>");
							out.print("<R1>"+nf.format(outstanding_val)+"</R1>");
							out.print("<R2>"+nf.format(normal_closing-outstanding_val)+"</R2>");
							out.print("<R3>"+nf.format(normal_closing)+"</R3>");
							out.print("</ITEM>");
						//}
						out.print("</DATA>");
				
			}

			// end by udara 26-11-2013
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Get The Outstanding Amount
		Used In     :Collection - Vehicle Advertistment Offers
		Created By  :Nuwan De Silva(29-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers_outstanding_values")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				
				rs= stmt.executeQuery (" SELECT A.TOTAL_AMOUNT,B.TOTAL_AMOUNT  "+
					" FROM( "+
					" SELECT "+
					" SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  " +
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE APPLICATION_NO=( "+
					" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO= "+
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO= "+
					" (SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ 
					" )) "+
					
					" ) A, "+
					
					
					" (SELECT TBL_RE.REMANING_PERIOD*GRENTAL_AMOUNT TOTAL_AMOUNT "+
					" FROM "+
					" ( "+
					" SELECT (A.PERIOD - B.INSTALMENT_NO) REMANING_PERIOD "+
					" FROM "+
					
					" (SELECT "+
					" DISTINCT PERIOD "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
					" WHERE PRICING_NO IN "+
					" ( "+
					" SELECT "+
					" PRICING_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO=( "+
					" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO= "+
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO= "+
					" (SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) A, "+
					
					" (SELECT "+
					" DISTINCT MAX(INSTALLMENT_NO) INSTALMENT_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE  PRICING_NO IN "+
					" ( "+
					" SELECT "+
					" PRICING_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO=( "+
					" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO= "+
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO= "+
					" (SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					
					" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) B "+
					" ) TBL_RE, "+
					
					" (SELECT "+
					" DISTINCT GRENTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE  PRICING_NO IN "+
					" ( "+
					" SELECT "+
					" PRICING_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO=( "+
					" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO= "+
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION"+
					" WHERE REPOSSESSION_NO= "+
					" (SELECT "+
					" REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') ) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					
					" )) "+ //AND UPPER(VEHICLE_NO)=UPPER('"+m_val2+"')
					" )) TBL_GEN "+
					" )B ");//)
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");
					out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Get The Inventory Number
		Used In     :Collection - Vehicle Inventory Process
		Created By  :Nuwan De Silva(30-11-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Vehicle_inventory_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					//  " DISTINCT A.INVENTORY_NO,  "+
					//  " A.SEIZER_CODE,  "+
					//	" B.FINANCE_NO  "+
					
					//" SELECT "+
					" DISTINCT A.INVENTORY_NO,  "+
					//" A.SEIZER_CODE,  "+
					" DECODE(B.REPOSSESS_TYPE,'OFFICER',B.REPOSSESS_OFFICER,'SEIZER',B.SEIZER_CODE,'COMPANY',B.REPOSSESS_OFFICER)  SEIZER_CODE,"+//Sandun on 03-07-2009
					" B.FINANCE_NO  "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
					" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
					"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
					"  ) C  "+
					"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
					"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
					"  AND   "+
					"  (UPPER(C.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(C.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  A.INVENTORY_NO=UPPER('"+m_val+"') OR  "+
					"  B.FINANCE_NO=UPPER('"+m_val+"') OR "+
					"  A.SEIZER_CODE=UPPER('"+m_val+"')   "+
					"  ) AND  "+
					"  A.REPOSSESSION_NO=UPPER('"+m_val2+"')   "+
					"  AND A.ACTIVE_STATUS=('"+m_status+"')  "+
					"  ORDER BY A.INVENTORY_NO DESC  ");
				
				//	" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
				//	" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO)=UPPER('"+m_val+"')  AND UPPER(A.INVENTORY_NO)=UPPER('"+m_val2+"')  AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
				
				
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
			
			/*-------------------------------------------------------------------------------------------------------------	
	Purpose     :Get The Advertistment Number
	Used In     :Collection - Offer Process Invoice
	Created By  :Nuwan De Silva(07-12-2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Offer_Invoice_View_Letter")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" DISTINCT ADVETIST_NO "+
					" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+
					" WHERE ADVETIST_NO =UPPER('"+m_val+"') AND RELEASE_TYPE=UPPER('"+m_val2+"') AND ACTIVE_STATUS=UPPER('"+m_status+"') ");
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get The Advertistment Number
			Used In     :Collection - Offer Process Invoice
			Created By  :Nuwan De Silva(07-12-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_Actions_Legal_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.LEGAL_NO, "+
					" A.FINANCE_NO, "+
					" A.CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ 
					" TO_CHAR(A.COURT_NEXT_DATE,'DD-MM-YYYY') COURT_DATE "+
					" FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIVITIES A ,"+m_schema_name+".AF_CO_MAS_LAWYER B "+
					" WHERE A.LAWYER_CODE=B.LAWYER_CODE AND (UPPER(A.LEGAL_NO)=UPPER('"+m_val+"') OR UPPER(A.FINANCE_NO)=UPPER('"+m_val+"') OR UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR UPPER(A.LAWYER_CODE)=UPPER('"+m_val+"')) AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
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
			
			
			
			
			
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get The Asset Details
			Used In     :Collection - Vehicle Inventory Valuation
			Created By  :Nuwan De Silva(01-12-2006)
			---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Vehicle_inventory_valuation")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				/*		rs= stmt.executeQuery (" SELECT "+
					" APPLICATION_NO,"+
					" MODEL_CODE, "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE, "+
					" SUB_MODEL_CODE, "+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
					" WHERE ASSET_ID=(SELECT "+
					" ASSET_ID "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
					" WHERE APPLICATION_NO=( "+
					" SELECT "+
					" APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO=( "+
					" SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO=( "+
					" SELECT "+
					" DISTINCT   REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') "+
					" )))) AND "+
					" APPLICATION_NO =(SELECT "+
					" APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO=( "+
					" SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO=( "+
					" SELECT "+
					" DISTINCT REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"')))) ");
					*/
				
				rs= stmt.executeQuery (" SELECT "+	
					" A.APPLICATION_NO, "+
					" A.ASSET_ID, "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(A.MODEL_CODE) ITEM_CAT_CODE,   "+
					" A.MODEL_CODE, "+
					" A.SUB_MODEL_CODE, "+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(A.MODEL_CODE) FUEL_TYPE, "+
					" B.YEAR_OF_MANUFACTURE "+ //MODIFIED NUWAN  DE SILVA 19-04-2007
					" FROM   "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+m_schema_name+".AF_CO_MAS_SUB_MODLE B     "+
					" WHERE A.SUB_MODEL_CODE=B.SUB_CODE AND  ASSET_ID=(SELECT "+
					" ASSET_ID "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE APPLICATION_NO=( "+
					" SELECT "+
					" APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
					" WHERE FINANCE_NO=(SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+ 
					" WHERE REPOSSESSION_NO=(SELECT "+
					" DISTINCT   REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') "+
					" )))AND REG_NO=(SELECT "+//Modified by Dineth on 03-04-2009
					" VEHICLE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ) "+
					" ) "+
					" AND "+
					" APPLICATION_NO =(SELECT "+
					" APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO=(SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					" WHERE REPOSSESSION_NO=(SELECT "+
					" DISTINCT   REPOSSESSION_NO "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					" WHERE INVENTORY_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') "+
					" ))) ");
				
				
				
				
				
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inspection_and_valuation_report")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_val1 = req.getParameter("data_val1").trim();
				String m_status = req.getParameter("ac_status");	
				
				/*	rs= stmt.executeQuery ("SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS,"+
				" TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING,"+
				" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR,FILED_CODE,B.STATUS,REMARK,GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	"+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,APPLICATION_NO,INVENTORY_NO "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET B"+
						" WHERE A.VALUATION_NO=B.VALUATION_NO AND A.VALUATION_NO=UPPER('"+m_val+"')"+
						" AND A.ASSET_ID=B.ASSET_ID AND ACTIVE_STATUS=('"+m_status+"') AND A.INVENTORY_NO IS NOT NULL ");
					*/				
				
				
				rs= stmt.executeQuery ("SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS,"+
					" TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING,"+
					" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR,FILED_CODE,B.STATUS,REMARK,GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	"+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,NVL(A.INVENTORY_NO,'-') INVENTORY_NO,NVL(A.VALUER_CODE,'-') VALUER_CODE, "+
					"	NVL(A.YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
					"	NVL(A.CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
					"	NVL(A.FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+
					"	NVL(A.APPLICATION_NO,'-') APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET B"+
					" WHERE A.VALUATION_NO=B.VALUATION_NO AND A.VALUATION_NO=UPPER('"+m_val+"')"+
					" AND A.ASSET_ID=B.ASSET_ID AND ACTIVE_STATUS=('"+m_status+"') AND A.INVENTORY_NO IS NOT NULL ");
				
				
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
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getString(26)+"</R26>");
					out.print("<R27>"+rs.getString(27)+"</R27>");
					out.print("<R28>"+nf.format(rs.getDouble(28))+"</R28>");
					out.print("<R29>"+rs.getString(29)+"</R29>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}		
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :POD Cheques
			Used In     :Collection - POD Cheques
			Created By  :Delanjali (11/12/2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_licencee_settlement_new")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				//rs= stmt.executeQuery ("SELECT ACC_NO FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH A,"+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT B"+
				//" WHERE UPPER(A.BRANCH_CODE)=UPPER(B.BRANCH_CODE) AND UPPER(ACC_NO)=UPPER('"+m_val+"') AND B.ACTIVE_STATUS=('"+m_status+"') ");
				
				rs= stmt.executeQuery (" SELECT "+
					" ACCOUNT_NO "+	
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
					" WHERE UPPER(ACCOUNT_NO) = UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_PRO_CR_display_finance_no")){
				
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE UPPER(FINANCE_NO)=UPPER('"+m_val+"') AND APPLICATION_STATUS=('"+m_status+"') ");
				
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_POD_Cheque_new")){
				
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT FINANCE_NO,CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE UPPER(FINANCE_NO)=UPPER('"+m_val+"') AND APPLICATION_STATUS=('"+m_status+"') ");
				
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_POD_Cheque_edit")){
				
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				
				rs= stmt.executeQuery (" SELECT "+
					" DISTINCT C.FINANCE_NO, "+
					" A.CLIENT_CODE, "+
					" B.FULL_NAME "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_RE_PRO_POD_CHEQUES C ,"+
					"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
					"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
					"  ) B  "+
					"  WHERE A.FINANCE_NO=B.FINANCE_NO AND A.FINANCE_NO=C.FINANCE_NO   "+
					"  AND   "+
					"  (UPPER(B.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  UPPER(B.TEL_NO)=UPPER('"+m_val+"') OR  "+
					"  UPPER(B.NIC_NO)=UPPER('"+m_val+"') OR  "+
					"  UPPER(A.FINANCE_NO)=UPPER('"+m_val+"')  "+
					"  )  "+
					"  AND A.APPLICATION_STATUS IN('"+m_status+"')  "+
					"  ORDER BY C.FINANCE_NO DESC  ");
				
				
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
			
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
		Purpose     :Get The Offer Numbers
		Used In     :Collection - Offer Issue
		Created By  :Nuwan De Silva (14/12/2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Offer_Issues_Offer_Numbers")){
				
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery (" SELECT "+
					" OFFER_NO, "+
					" OFFER_FULL_NAME, "+ 
					" OFFER_ADDRESS, "+
					" OFFER_TEL_NO, "+
					" OFFER_VALUE "+
					" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+ 
					" WHERE  UPPER(ADVETIST_NO)=UPPER('"+m_val+"')  AND "+
					" UPPER(INVENTORY_NO)=UPPER('"+m_val2+"') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+nf.format(rs.getDouble(5))+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			/*-------------------------------------------------------------------------------------------------------------	
	Purpose     :Get The Finance Numbers
	Used In     :Collection - Finance Cost Recoering
	Created By  :Nuwan De Silva (18/12/2006)
	---------------------------------------------------------------------------------------------------------------*/	
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Finance_Cost_Recover")){
				
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				
				
				rs= stmt.executeQuery ( " SELECT R.FINANCE_NO FINANCE_NO,R.FULL_NAME FULL_NAME,Q.TOTAL_COST TOTAL_COST,NVL((SELECT "+
					" SUM(SETTELE_AMOUNT) OTHER_CHARGES "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE FINANCE_NO IN( "+
					" SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_STATUS=('"+m_status+"') "+
					"  ) "+
					"  AND FINANCE_NO=R.FINANCE_NO "+
					" AND INVOICE_TYPE <>'INV_GENER' "+
					" AND BALANCE_TO_BE_RECEIVED > 0 "+
					" GROUP BY FINANCE_NO) ,0) AS OTHER_CHARGES, "+
					" R.CLIENT_CODE "+	
					
					" 	FROM "+
					
					" (SELECT  A.APPLICATION_NO YY,A.GRENTAL_AMOUNT,B.TOTAL_AMOUNT,A.GRENTAL_AMOUNT+B.TOTAL_AMOUNT TOTAL_COST "+
					" FROM "+
					
					" (SELECT "+
					" APPLICATION_NO,SUM(GRENTAL_AMOUNT) GRENTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE APPLICATION_NO IN "+
					" (SELECT "+
					" APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_STATUS=('"+m_status+"')) "+
					" GROUP BY APPLICATION_NO "+
					" ORDER BY APPLICATION_NO ) A, "+ 
					
					" (SELECT "+
					" APPLICATION_NO ,SUM(TOTAL_AMOUNT) TOTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE ACTIVE_STATUS='Y' AND "+
					" APPLICATION_NO IN "+
					" (SELECT "+
					" APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_STATUS=('"+m_status+"')) "+
					" GROUP BY APPLICATION_NO "+
					" ORDER BY APPLICATION_NO) B  "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
					" )Q, "+
					
					" (SELECT "+
					" X.APPLICATION_NO XX, "+
					" X.FINANCE_NO, "+
					" Y.FULL_NAME, "+
					" Y.CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT Y "+
					" WHERE X.CLIENT_CODE=Y.CLIENT_CODE AND APPLICATION_STATUS=('"+m_status+"') "+
					" ORDER BY X.FINANCE_NO "+
					" )R "+
					" WHERE R.XX=Q.YY  "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
					out.print("<R4>"+nf.format(rs.getDouble(4))+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			
			
			
			
			
			
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :Get The Outstanding Amount
			Used In     :Collection - Vehicle Advertistment Offers
			Created By  :Nuwan De Silva(29-11-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Legal_Activities_due_value")){
				
				String m_val = req.getParameter("data_val").trim();
				//	String m_val2 = req.getParameter("data_val2").trim();
				
				
				rs= stmt.executeQuery (" SELECT SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  "+
					"	 FROM(  "+
					
					"	 SELECT  "+
					"	 SUM(TOTAL_AMOUNT) TOTAL_AMOUNT  "+
					"	 FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL  "+
					"	 WHERE INVENTORY_NO IN( "+                  
					"	     SELECT DISTINCT INVENTORY_NO "+
					"	     FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					"	     WHERE  REPOSSESSION_NO IN( "+
					"	     SELECT DISTINCT REPOSSESSION_NO "+
					"	     FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  "+
					"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
					"	     )) AND VEHICLE_NO IN( "+
					"	     SELECT VEHICLE_NO "+
					"	     FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					"	     WHERE APPLICATION_NO IN(     "+
					"	     SELECT "+
					"	     DISTINCT APPLICATION_NO "+
					"	     FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"	     WHERE FINANCE_NO=UPPER('"+m_val+"') "+ 
					"	     )) "+
					
					
					"	 UNION   "+
					
					"	 SELECT  "+
					"	 INVOICE_AMOUNT TOTAL_AMOUNT  "+
					"	 FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  "+
					"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
					
					"	 UNION  "+
					
					"	 SELECT TBL_RE.REMANING_PERIOD*GRENTAL_AMOUNT  TOTAL_AMOUNT  "+
					"	 FROM  "+
					"	 (  "+
					"	 SELECT (A.PERIOD - B.INSTALMENT_NO) REMANING_PERIOD  "+
					"	 FROM  "+
					
					"	 (SELECT  "+
					"	 DISTINCT PERIOD  "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING  "+ 
					"	 WHERE PRICING_NO IN(  "+ 
					"	 SELECT  "+
					"	 DISTINCT PRICING_NO  "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"	 WHERE  APPLICATION_NO=(  "+
					"	 SELECT DISTINCT APPLICATION_NO  "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					"	 WHERE FINANCE_NO=UPPER('"+m_val+"')  "+
					"	 ))) A,  "+
					
					"	 (SELECT  "+
					"	 MAX(INSTALLMENT_NO) INSTALMENT_NO  "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					"	 WHERE  PRICING_NO IN  (  "+ 
					"	 SELECT  "+
					"	 DISTINCT PRICING_NO   "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"	 WHERE  APPLICATION_NO=(  "+
					"	 SELECT DISTINCT APPLICATION_NO  "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+ 
					"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
					"	 ))) B  "+ 
					"	 ) TBL_RE,  "+
					
					"	 (SELECT  "+
					"	 DISTINCT GRENTAL_AMOUNT  "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					"	 WHERE  PRICING_NO IN (  "+ 
					"	 SELECT  "+
					"	 DISTINCT PRICING_NO   "+ 
					"	 FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"	 WHERE  APPLICATION_NO=(  "+
					"	 SELECT DISTINCT APPLICATION_NO  "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					"	 WHERE FINANCE_NO=UPPER('"+m_val+"') "+
					"	 ))) TBL_GEN  "+
					"	 )  ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			/*-------------------------------------------------------------------------------------------------------------	
			Purpose     :To get records from payment settlement table
			Used In     :Collection - Payment Settlement
			Created By  :Delanjali(20-12-2006)
		---------------------------------------------------------------------------------------------------------------*/	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_PRO_display_payment_settlement")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				
				rs= stmt.executeQuery ("SELECT PAYMENT_NO,A.SUS_REF_NO,CLIENT_CODE,SETTLE_MODE,ENTRY_TYPE,PAY_AMOUNT, "+
					" LIC_BRANCH_CODE,LIC_ACC_NO,PAYEE_BRANCH_CODE,PAYEE_ACC_NO,PROCESS_STATUS, "+
					" ENTDATE,RECON_STATUS,RECON_DATE,RECON_BY,TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),REALISED_DATE, "+
					" PAYEE_NAME,PAY_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR, "+
					" REC_AMMOUNT_REP_CURR,EXCHANGE_GAIN_LOSS,COMMENTS,CURR_CODE,EXCHANGE_RATE,BAL_TO_BE_PAID,RECEIVER "+
					" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B"+
					" WHERE UPPER(PAYMENT_NO)=UPPER('"+m_val+"') AND UPPER(A.SUS_REF_NO)=UPPER(B.SUS_REF_NO) ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getDouble(6)+"</R6>");
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
					out.print("<R20>"+rs.getString(20)+"</R20>");
					out.print("<R21>"+rs.getString(21)+"</R21>");
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getDouble(26)+"</R26>");
					out.print("<R27>"+rs.getDouble(27)+"</R27>");
					out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_PRO_display_payment_settlement_other")){
				
				String m_val = req.getParameter("data_val").trim();
				
				
				
				/*rs= stmt.executeQuery ("SELECT PAYMENT_NO,A.SUS_REF_NO,CLIENT_CODE,SETTLE_MODE,ENTRY_TYPE,PAY_AMOUNT, "+
				" LIC_BRANCH_CODE,LIC_ACC_NO,PAYEE_BRANCH_CODE,PAYEE_ACC_NO,PROCESS_STATUS, "+
				" ENTDATE,RECON_STATUS,RECON_DATE,RECON_BY,TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),REALISED_DATE, "+
				" PAYEE_NAME,PAY_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR, "+
				" REC_AMMOUNT_REP_CURR,EXCHANGE_GAIN_LOSS,COMMENTS,CURR_CODE,EXCHANGE_RATE,BAL_TO_BE_PAID,RECEIVER "+
				" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B"+
				" WHERE UPPER(PAYMENT_NO)=UPPER('"+m_val+"') AND UPPER(A.SUS_REF_NO)=UPPER(B.SUS_REF_NO) ");
				*/
				
				
				rs= stmt.executeQuery ("SELECT query2.PAYMENT_NO PAYMENT_NO, "+//1
					"query2.SUS_REF_NO SUS_REF_NO, "+//2
					"query2.CLIENT_CODE CLIENT_CODE, "+//3
					"query2.SETTLE_MODE SETTLE_MODE, "+//4
					"query2.ENTRY_TYPE ENTRY_TYPE, "+//5
					"query2.PAY_AMOUNT PAY_AMOUNT, "+//6
					"query2.LIC_BRANCH_CODE LIC_BRANCH_CODE, "+//7
					"query2.LIC_ACC_NO LIC_ACC_NO, "+//8
					"query2.PAYEE_BRANCH_CODE PAYEE_BRANCH_CODE, "+//9
					"query2.PAYEE_ACC_NO PAYEE_ACC_NO, "+//10
					"query2.PROCESS_STATUS PROCESS_STATUS, "+//11
					"query2.ENTDATE ENTDATE, "+//12
					"query2.RECON_STATUS RECON_STATUS, "+//13
					"query2.RECON_DATE RECON_DATE, "+//14
					"query2.RECON_BY RECON_BY, "+//15
					"query2.EFF_VALDATE EFF_VALDATE, "+//16
					"query2.REALISED_DATE REALISED_DATE, "+//17
					"query2.PAYEE_NAME PAYEE_NAME, "+//18
					"query2.PAY_AMOUNT_CURR PAY_AMOUNT_CURR, "+//19
					"query2.EXCHANGE_RATE_BANK EXCHANGE_RATE_BANK, "+//20
					"query2.EXCHANGE_RATE_REP_CURR EXCHANGE_RATE_REP_CURR, "+//21
					"query2.REC_AMMOUNT_REP_CURR REC_AMMOUNT_REP_CURR, "+//22
					"query2.EXCHANGE_GAIN_LOSS EXCHANGE_GAIN_LOSS, "+//23
					"query2.COMMENTS COMMENTS, "+//24
					"query2.CURR_CODE CURR_CODE, "+//25
					"query2.EXCHANGE_RATE EXCHANGE_RATE, "+//26
					"query2.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+//27
					"query2.RECEIVER RECEIVER , "+//28
					"query2.ref_no ref_no, "+//29
					"query2.finance_no finance_no, "+//30
					"query2.receiver_name receiver_name, "+//31
					"query2.client_code1 client_code1, "+//32
					""+m_schema_name+".AF_CO_GET_CLIENT_NAME(client_code1), "+//33
					"query2.payer payer, "+//34
					""+m_schema_name+".AF_CO_GET_CLIENT_NAME(payer) "+//35
					"from "+
					"(SELECT query1.PAYMENT_NO PAYMENT_NO, "+
					"query1.SUS_REF_NO SUS_REF_NO, "+
					"query1.CLIENT_CODE CLIENT_CODE, "+
					"query1.SETTLE_MODE SETTLE_MODE, "+
					"query1.ENTRY_TYPE ENTRY_TYPE, "+
					"query1.PAY_AMOUNT PAY_AMOUNT, "+
					"query1.LIC_BRANCH_CODE LIC_BRANCH_CODE, "+
					"query1.LIC_ACC_NO LIC_ACC_NO, "+
					"query1.PAYEE_BRANCH_CODE PAYEE_BRANCH_CODE, "+
					"query1.PAYEE_ACC_NO PAYEE_ACC_NO, "+
					"query1.PROCESS_STATUS PROCESS_STATUS, "+
					"query1.ENTDATE ENTDATE, "+
					"query1.RECON_STATUS RECON_STATUS, "+
					"query1.RECON_DATE RECON_DATE, "+
					"query1.RECON_BY RECON_BY, "+
					"query1.EFF_VALDATE EFF_VALDATE, "+
					"query1.REALISED_DATE REALISED_DATE, "+
					"query1.PAYEE_NAME PAYEE_NAME, "+
					"query1.PAY_AMOUNT_CURR PAY_AMOUNT_CURR, "+
					"query1.EXCHANGE_RATE_BANK EXCHANGE_RATE_BANK, "+
					"query1.EXCHANGE_RATE_REP_CURR EXCHANGE_RATE_REP_CURR, "+
					"query1.REC_AMMOUNT_REP_CURR REC_AMMOUNT_REP_CURR, "+
					"query1.EXCHANGE_GAIN_LOSS EXCHANGE_GAIN_LOSS, "+
					"query1.COMMENTS COMMENTS, "+
					"query1.CURR_CODE CURR_CODE, "+
					"query1.EXCHANGE_RATE EXCHANGE_RATE, "+
					"query1.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+
					"query1.RECEIVER RECEIVER , "+
					"query1.ref_no ref_no, "+
					"query1.finance_no finance_no, "+
					"query1.receiver_name receiver_name, "+
					""+m_schema_name+".af_co_get_client_CODE(finance_no) client_code1, "+
					"query1.payer payer "+
					"FROM "+
					"(SELECT PAYMENT_NO, "+
					"A.SUS_REF_NO SUS_REF_NO, "+
					"CLIENT_CODE, "+
					"SETTLE_MODE, "+
					"ENTRY_TYPE, "+
					"PAY_AMOUNT, "+
					"LIC_BRANCH_CODE, "+
					"LIC_ACC_NO, "+
					"PAYEE_BRANCH_CODE, "+
					"PAYEE_ACC_NO, "+
					"PROCESS_STATUS, "+
					"ENTDATE,RECON_STATUS, "+
					"RECON_DATE,RECON_BY, "+
					"TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
					"REALISED_DATE, "+
					"PAYEE_NAME, "+
					"PAY_AMOUNT_CURR, "+
					"EXCHANGE_RATE_BANK, "+
					"EXCHANGE_RATE_REP_CURR, "+
					"REC_AMMOUNT_REP_CURR, "+
					"EXCHANGE_GAIN_LOSS, "+
					"COMMENTS, "+
					"CURR_CODE, "+
					"EXCHANGE_RATE, "+
					"BAL_TO_BE_PAID, "+
					"RECEIVER , "+
					"ref_no, "+
					""+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO) as finance_no, "+
					""+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,receiver) as receiver_name, "+
					"payer "+
					"FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B "+
					"WHERE UPPER(PAYMENT_NO)=UPPER('"+m_val+"') AND UPPER(A.SUS_REF_NO)=UPPER(B.SUS_REF_NO) ) query1)query2 ");
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getDouble(6)+"</R6>");
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
					out.print("<R20>"+rs.getString(20)+"</R20>");
					out.print("<R21>"+rs.getString(21)+"</R21>");
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getDouble(26)+"</R26>");
					out.print("<R27>"+rs.getDouble(27)+"</R27>");
					out.print("<R28>"+rs.getString(28)+"</R28>");
					
					out.print("<R29>"+rs.getString(29)+"</R29>");
					out.print("<R30>"+rs.getString(30)+"</R30>");
					out.print("<R31>"+rs.getString(31)+"</R31>");
					out.print("<R32>"+rs.getString(32)+"</R32>");
					out.print("<R33>"+rs.getString(33)+"</R33>");
					out.print("<R34>"+rs.getString(34)+"</R34>");
					out.print("<R35>"+rs.getString(35)+"</R35>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_PRO_display_payment_settle_bal")){
				
				String m_val = req.getParameter("data_val").trim();
				//	int m_val = Integer.parseInt(req.getParameter("data_val"));
				
				String m_val1 = req.getParameter("data_val1").trim();
				rs= stmt.executeQuery ("SELECT SUS_REF_NO,BAL_TO_BE_PAID,EXCHANGE_RATE,SUSPENSE_ENTRY_TYPE,RECEIVER,PAYER,REF_NO,TOT_SETTLE_AMOUNT, "+
					" INT_BAL_SETTLE_AMOUNT,0,ENT_DATE,LAST_MOD_DATE, "+
					" '','',CURR_CODE,EXCHANGE_RATE*('"+m_val+"')  "+
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
					" WHERE UPPER(SUS_REF_NO)=UPPER('"+m_val1+"') AND BAL_TO_BE_PAID >=('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getDouble(2)+"</R2>");
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
					out.print("<R16>"+rs.getDouble(16)+"</R16>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_LIC_Acc_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val1 = req.getParameter("data_val1").trim();
				
				
				
				rs= stmt.executeQuery ("SELECT "+
					" ACCOUNT_NO,BRANCH_CODE, "+	
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+		
					" BANK_CODE, "+
					" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME,CLIENT_CODE"+		
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
					" WHERE UPPER(ACCOUNT_NO) = UPPER('"+m_val+"') AND UPPER(CLIENT_CODE) = UPPER('"+m_val1+"')  AND ACTIVE_STATUS=('Y')");
				
				
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
			
			
			
			
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_sus_ref_code")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val1 = req.getParameter("data_val1").trim();
				
				
				
				rs= stmt.executeQuery ("SELECT"+
					" SUS_REF_NO,  "+
					" SUSPENSE_ENTRY_TYPE,  "+
					" RECEIVER,  "+
					"	PAYER,  "+
					" REF_NO,  "+
					" TOT_SETTLE_AMOUNT,  "+
					" INT_BAL_SETTLE_AMOUNT,  "+
					" '0',  "+
					" BAL_TO_BE_PAID,0,0,  "+
					// "  PAY_FROM_INT_BAL_AMT_CURR,  "+
					// " BAL_TO_BE_PAID_CURR,  "+
					" CURR_CODE,  "+
					" EXCHANGE_RATE,  "+
					""+m_schema_name+".AF_CO_GET_CLIENT_NAME(RECEIVER) RECEIVER_NAME, "+	
					"	TO_CHAR(ENT_DATE,'DD-MM-YYYY') ENT_DATE"+
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
					" WHERE UPPER(SUS_REF_NO) = UPPER('"+m_val+"') ");
				//	"AND UPPER(PAYER) = UPPER('"+m_val1+"') ");//modified by : delanjali date:2007-06-13
				
				
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
					//	out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_get_sus_ref_code1")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val1 = req.getParameter("data_val1").trim();
				
				
				
				/*rs= stmt.executeQuery ("SELECT"+
					" SUS_REF_NO,  "+
					" SUSPENSE_ENTRY_TYPE,  "+
					" RECEIVER,  "+
					"	PAYER,  "+
					" REF_NO,  "+
					" TOT_SETTLE_AMOUNT,  "+
					" INT_BAL_SETTLE_AMOUNT,  "+
				" '0',  "+
					" BAL_TO_BE_PAID,0,0,  "+
				// "  PAY_FROM_INT_BAL_AMT_CURR,  "+
				// " BAL_TO_BE_PAID_CURR,  "+
					" CURR_CODE,  "+
					" EXCHANGE_RATE,  "+
						""+m_schema_name+".AF_CO_GET_CLIENT_NAME(RECEIVER) RECEIVER_NAME, "+	
						"	TO_CHAR(ENT_DATE,'DD-MM-YYYY') ENT_DATE"+
						" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
					" WHERE UPPER(SUS_REF_NO) = UPPER('"+m_val+"') ");
					//	"AND UPPER(PAYER) = UPPER('"+m_val1+"') ");//modified by : delanjali date:2007-06-13
					
		*/
				
				rs= stmt.executeQuery 
					
					("SELECT "+
					"query2.SUS_REF_NO, "+
					"query2.SUSPENSE_ENTRY_TYPE , "+
					"query2.RECEIVER, "+
					"query2.PAYER, "+
					"query2.REF_NO, "+
					"query2.TOT_SETTLE_AMOUNT, "+
					"query2.INT_BAL_SETTLE_AMOUNT, "+
					"query2.PAY_FROM_INT_BAL_AMT, "+
					"query2.BAL_TO_BE_PAID, "+
					"query2.PAY_FROM_INT_BAL_AMT_CURR, "+
					"query2.BAL_TO_BE_PAID_CURR, "+
					"query2.CURR_CODE, "+
					"query2.EXCHANGE_RATE, "+
					"NVL(query2.PAYER_NAME,'-') PAYER_NAME, "+
					"query2.ENT_DATE, "+
					"NVL(query2.APPLICATION_NO,'-') AS APPLICATION_NO, "+
					"query2.CLIENT_CODE, "+
					"query2.REF_NAME , "+
					""+m_schema_name+".af_co_get_client_name(query2.CLIENT_CODE) as CLIENT_NAME "+
					"FROM "+
					"( "+
					"SELECT "+
					"query1.SUS_REF_NO, "+
					"query1.SUSPENSE_ENTRY_TYPE , "+
					"query1.RECEIVER, "+
					"query1.PAYER, "+
					"query1.REF_NO, "+
					"query1.TOT_SETTLE_AMOUNT, "+
					"query1.INT_BAL_SETTLE_AMOUNT, "+
					"query1.PAY_FROM_INT_BAL_AMT, "+
					"query1.BAL_TO_BE_PAID, "+
					"query1.PAY_FROM_INT_BAL_AMT_CURR, "+
					"query1.BAL_TO_BE_PAID_CURR, "+
					"query1.CURR_CODE, "+
					"query1.EXCHANGE_RATE, "+
					"NVL(query1.PAYER_NAME,'-') PAYER_NAME, "+ 
					"query1.ENT_DATE,"+
					"NVL(query1.dd,'-') AS APPLICATION_NO, "+
					"NVL(("+m_schema_name+".af_co_get_client_CODE(query1.dd)),'-') CLIENT_CODE, "+
					"nvl("+m_schema_name+".AF_CO_GET_REF_NAME(query1.REF_NO,query1.RECEIVER),'-') AS REF_NAME  "+
					"FROM "+
					"(SELECT "+
					"SUS_REF_NO, "+
					"nvl(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE , "+
					"RECEIVER, "+
					"PAYER, "+
					"REF_NO, "+
					"TOT_SETTLE_AMOUNT, "+
					"INT_BAL_SETTLE_AMOUNT, "+
					"0 PAY_FROM_INT_BAL_AMT, "+
					"BAL_TO_BE_PAID, "+
					"0 PAY_FROM_INT_BAL_AMT_CURR, "+
					"0 BAL_TO_BE_PAID_CURR, "+
					"CURR_CODE, "+
					"EXCHANGE_RATE, "+
					""+m_schema_name+".AF_CO_GET_CLIENT_NAME(PAYER) PAYER_NAME, "+
					"TO_CHAR(ENT_DATE,'DD-MM-YYYY') ENT_DATE,"+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)dd "+
					"FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
					"WHERE UPPER(SUS_REF_NO) = UPPER('"+m_val+"') "+ 
					")  query1 )query2 ");
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_POD_CHEQUE")){
				String m_val = req.getParameter("data_val").trim();
				String m_br_code = req.getParameter("branch_code").trim();
				
				
				rs = stmt.executeQuery ("SELECT CHEQUE_NO "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
					"WHERE UPPER(CHEQUE_NO)=UPPER('"+m_val+"') AND UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_br_code+"')  "); //modified nuwan de silva 27-07-07
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//added by nuwan de silva 02-08-07----------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_SETT_RECEIPT_CHEQUE_VALIDATE")){
				String m_val = req.getParameter("data_val").trim();
				String m_br_code = req.getParameter("branch_code").trim();
				
				/*rs = stmt.executeQuery ("SELECT CHEQUE_NO "+
																"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
																	"WHERE UPPER(CHEQUE_NO)=UPPER() AND UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_br_code+"')  "); 
																	
				
				*/
				
				rs = stmt.executeQuery (" SELECT CHEQUE_NO FROM "+
					" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE UPPER(CHEQUE_NO)=UPPER('"+m_val+"')  "+
					" AND UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_br_code+"')   "+
					" UNION ALL "+
					" SELECT CHEQUE_NO FROM "+
					" "+m_schema_name+".AF_RE_PRO_POD_CHEQUES  "+
					" WHERE UPPER(CHEQUE_NO)=UPPER('"+m_val+"')  "+
					" AND UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_br_code+"')  ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			//==========================================================================================
			
			//-----------------------------------------------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_POD_CHEQUE2")){
				String m_val = req.getParameter("data_val").trim();
				String m_br_code = req.getParameter("branch_code").trim();
				String m_finance_no = req.getParameter("finance_no").trim();
				
				rs = stmt.executeQuery ("SELECT CHEQUE_NO "+
					"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
					"WHERE UPPER(CHEQUE_NO)=UPPER('"+m_val+"')AND UPPER(FINANCE_NO)!=UPPER('"+m_finance_no+"') AND UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_br_code+"')  "); //modified nuwan de silva 27-07-07
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}		
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Offer_Processs_Inventory_val")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs = stmt.executeQuery ("SELECT  "+
					" DISTINCT A.INVENTORY_NO,  "+
					" B.FINANCE_NO,  "+
					" C.CLIENT_CODE , "+
					" C.FULL_NAME,  "+
					" A.VEHICLE_NO  "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
					" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
					"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
					"  ) C  "+
					"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
					"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
					"  AND   "+
					"  (UPPER(C.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(C.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  A.INVENTORY_NO=UPPER('"+m_val+"') OR  "+
					"  B.FINANCE_NO=UPPER('"+m_val+"') OR "+
					"  UPPER(A.VEHICLE_NO)=UPPER('"+m_val+"')  "+
					"  ) "+
					"  AND A.COMPLETED_OFFER_NO IS NULL "+
					"  AND A.ACTIVE_STATUS=('"+m_status+"')  ");
				
				
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Offer_Issue_Inventory_val")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs = stmt.executeQuery ("SELECT  "+
					" DISTINCT A.INVENTORY_NO,  "+
					" C.CLIENT_CODE , "+
					" C.FULL_NAME,  "+
					" A.VEHICLE_NO  "+
					" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS B, "+
					
					" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
					"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
					"  ) C  "+
					"  WHERE A.INVENTORY_NO=B.INVENTORY_NO  "+
					"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
					"  AND   "+
					"  (UPPER(C.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(C.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  A.INVENTORY_NO=UPPER('"+m_val+"') OR  "+
					"  UPPER(A.VEHICLE_NO)=UPPER('"+m_val+"')  "+
					"  ) "+
					"  AND A.COMPLETED_OFFER_NO IS NULL "+
					//	"  AND B.ADVER_NO='-' "+
					"  AND A.ACTIVE_STATUS=('"+m_status+"')  "+
					"  ORDER BY A.INVENTORY_NO DESC  ");
				
				
				
				
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
			
			
			
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_item_category")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ITEM_CAT_CODE,DESCRIPTION,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
					" WHERE (UPPER(ITEM_CAT_CODE)=UPPER('"+m_val+"') OR UPPER(DESCRIPTION)=UPPER('"+m_val+"')) "+
					" AND ACTIVE_STATUS=UPPER('"+m_status+"')");
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_settlement_tendered_amount")){
				
				String m_val1 = req.getParameter("data_val1").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				//rs= stmt.executeQuery ("SELECT "+m_val1+" - "+m_val2+" FROM DUAL  ");
				rs= stmt.executeQuery ("SELECT "+m_val2+" - "+m_val1+" FROM DUAL  ");	 //modified by nuwan de silva 06-07-07
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+nf.format(rs.getDouble(1))+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_settlement_branch_code")){
				
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT BRANCH_CODE FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH WHERE UPPER(BRANCH_CODE)=UPPER('"+m_val+"')  ");	 
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*--------------------ID       :Cheque return Narrations Process-------------------------------
			--------------------Purpose    :Narration code------------------------------------------------
		  ------------------- Added By   :Nuwan ------------------------------------------------------
		  --------------------Date       :16-08-2007---------------------------------------------------------*/
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_cheque_return_narrations")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();	
				
				rs= stmt.executeQuery ("SELECT CHQ_NARR_CODE,CHQ_NARRATIONS,ACTIVE_STATUS,DEFAULT_VALUE "+
					"FROM "+m_schema_name+".AF_CO_MAS_CHEQUE_NARRATIONS "+
					"WHERE UPPER(CHQ_NARR_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"')");
				
				
				
				
				
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
			
			//------modified by : delanjali on 2007-09-04-for ref no :845-------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new")){
				
				//String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				String m_client_code = req.getParameter("client_code").trim();					
				String m_finance_no= req.getParameter("finance_no").trim();	 //comment by nuwan de silva on 18-10-07
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					//  " NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.CLIENT_CODE,'-') CLIENT_CODE "+
					" ,SUBSTR(NVL(A.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // added by udara 15-07-2015
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND"+
					//" A.APPLICATION_STATUS=UPPER('"+m_status+"') "+
					" A.APPLICATION_STATUS IN ('ACTIVATED','NORM_TERMI','TERMI','LEGAL') "+//Mod by Sandun on 25-05-2009
					//" and application_no in ('AP20070213-0366','AP20070315-0444','AP20070315-0447','AP20070315-0452') "+
					//" AND A.COLLECTION_OFFICER IS NULL "+ // commented by 31-12-2012
					" AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+ 
					" AND UPPER(A.FINANCE_NO) LIKE UPPER('"+m_finance_no+"%') "+  //comment by nuwan de silva on 18-10-07
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");//Added Nuwan De Silva 19-04-2007
					//out.print("<R8>"+rs.getString(8)+"</R8>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Insu_Officer_Assign_Ind")){//Added BY Sandun on 03-03-2009
				
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				String m_client_code = req.getParameter("client_code").trim();					
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.CLIENT_CODE,'-') CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"') "+
					" AND A.INSURANCE_OFFICER IS NULL "+
					" AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+ 
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");         
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//---client no-------------------------------------------------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_client")){
				
				
				String m_val   				  =  req.getParameter("data_val").trim();
				String m_ac_status      =  req.getParameter("ac_status").trim();
				
				
				
				rs=stmt.executeQuery("SELECT a.CLIENT_CODE "+
					"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					"	 WHERE 	UPPER(A.CLIENT_CODE) = UPPER('"+m_val+"') "+
					"  AND A.CLIENT_CODE=B.CLIENT_CODE "+
					"  AND B.APPLICATION_STATUS='ACTIVATED' "+
					"	 AND a.ACTIVE_STATUS ='"+m_ac_status+"' " );	
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
					" AND COLLECTION_OFFICER IS NULL "+
					" AND APPLICATION_STATUS=('ACTIVATED') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_finance_new")){
				
				
				String m_val   				  =  req.getParameter("data_val").trim();
				String m_ac_status      =  req.getParameter("ac_status").trim();
				
				rs=stmt.executeQuery(" SELECT FINANCE_NO,APPLICATION_NO  "+
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
					" WHERE   upper(FINANCE_NO) = UPPER('"+m_val+"') "+
					" AND COLLECTION_OFFICER IS NOT NULL "+
					" AND APPLICATION_STATUS=('ACTIVATED') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");			
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			//Added by Dineth on 2008-09-30
			else if (m_chksql.trim().equals("m_chk_LAKDL_AF_CR_PRO_Change_Payee_Code_Assigning_finance_no")){
				String m_finance_no=req.getParameter("fin_no");
				String m_as_at_date=req.getParameter("as_at_date");
				/*rs = stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE, "+
				" B.SUB_TYPE_CODE, "+
				" A.TOTAL_AMOUNT, "+
				" B.PAYEE_NAME "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B"+
		" WHERE A.FINANCE_NO LIKE ('"+m_finance_no+"%') "+
				" AND B.SUB_TYPE_CODE=A.INVOICE_TYPE "+
				" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')"+
				" ORDER BY A.CLIENT_CODE ASC ");*/
				
				rs=stmt.executeQuery(" SELECT "+ 
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') ,"+
					" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUB_TYPE_CODE),' '), "+
					" NVL(A.BAL_TO_BE_PAID,0), "+ 
					" NVL(B.PAYEE_NAME,' '), "+
					" B.PAYEE_CODE, "+
					" A.REF_NO "+
					" FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
					" WHERE "+ 
					" A.RECEIVER=B.PAYEE_CODE "+
					" AND "+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)) LIKE ('"+m_finance_no+"%') "+
					" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
					" AND A.SUSPENSE_ENTRY_TYPE NOT IN('V','L')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
			}
			//End by Dineth on 2008-09-30
			
			// Added by Dineth on 2008-10-01
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_CR_PRO_Changing_Payee_Details_new")){
				String m_payee=req.getParameter("payee");
				String m_as_at_date=req.getParameter("as_at_date");
				/*rs = stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE, "+
				" B.SUB_TYPE_CODE, "+
				" A.TOTAL_AMOUNT, "+
				" B.PAYEE_NAME "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B"+
		" WHERE B.PAYEE_CODE LIKE ('"+m_payee+"%') "+
				" AND B.SUB_TYPE_CODE=A.INVOICE_TYPE "+
				" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')"+
				" ORDER BY A.CLIENT_CODE ASC ");*/
				rs=stmt.executeQuery(" SELECT "+ 
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))),' ') ,"+
					" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUB_TYPE_CODE),' '), "+
					" NVL(A.BAL_TO_BE_PAID,0), "+ 
					" NVL(B.PAYEE_NAME,' '), "+
					" B.PAYEE_CODE, "+
					" A.REF_NO "+
					" FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
					" WHERE "+ 
					" A.RECEIVER=B.PAYEE_CODE "+
					" AND B.PAYEE_CODE LIKE ('"+m_payee+"%') "+
					" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
					" AND A.SUSPENSE_ENTRY_TYPE NOT IN ('V','L')"	);
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
			}
			
			
			else if (m_chksql.trim().equals("m_prime_chk_insurance_officer_edit")){
				
				//	String m_finanace_no = req.getParameter("finanace_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				String m_client_code = req.getParameter("client_code").trim();					
				//String m_finance_no= req.getParameter("finance_no").trim();	//comment by nuwan de silva on 18-10-07
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					" NVL(B.FULL_NAME,'-') CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.COLLECTION_OFFICER,'-') COLLECTION_OFFICER, "+
					" NVL(B.CLIENT_CODE,'-') CLIENT_CODE "+ //Added By Nuwan De Silva 19-04-2007
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME(A.INSURANCE_OFFICER) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"')  "+
					" AND A.CLIENT_CODE LIKE ('"+m_client_code+"%') "+
					//" AND A.FINANCE_NO LIKE ('"+m_finance_no+"%') "+ //comment by nuwan de silva on 18-10-07
					//" AND (UPPER(A.FINANCE_NO)=UPPER('"+m_finanace_no+"') OR UPPER(B.FULL_NAME)=UPPER('"+m_finanace_no+"') ) "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			// end by Dineth on 2008-10-01
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new")){
				
				//	String m_finanace_no = req.getParameter("finanace_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				String m_client_code = req.getParameter("client_code").trim();					
				String m_finance_no= req.getParameter("finance_no").trim();	//comment by nuwan de silva on 18-10-07
				String m_user_location_set= req.getParameter("user_location_set").trim();	// added by udara on 13-09-2013
				
				if(m_user_location_set.equals("HO"))
					m_user_location_set = "";				
					
	
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					" NVL(B.FULL_NAME,'-') CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.COLLECTION_OFFICER,'-') COLLECTION_OFFICER, "+
					" NVL(B.CLIENT_CODE,'-') CLIENT_CODE "+ //Added By Nuwan De Silva 19-04-2007
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) "+
					" ,SUBSTR(NVL(A.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // added by udara 15-07-2015
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
					//" A.APPLICATION_STATUS=UPPER('"+m_status+"')  "+ 
					//" A.APPLICATION_STATUS IN ('ACTIVATED','NORM_TERMI','TERMI','LEGAL') "+//Mod by Sandun on 25-05-2009 //commented by kanchana on 2016-06-09 for issue 20554
					" A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+//Added by kanchana on 2016-06-09 for issue 20554
					" AND A.CLIENT_CODE LIKE ('"+m_client_code+"%') "+
					" AND A.FINANCE_NO LIKE ('"+m_finance_no+"%') "+ //comment by nuwan de silva on 18-10-07
					//" AND (UPPER(A.FINANCE_NO)=UPPER('"+m_finanace_no+"') OR UPPER(B.FULL_NAME)=UPPER('"+m_finanace_no+"') ) "+
					" AND A.BRANCH_CODE LIKE '"+m_user_location_set+"%'  "+ // added by udara on 13-09-2013
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			// ADDED BY SAJITH MENDIS ON 12-02-2014
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_users")){

				
				String m_user_location_set= req.getParameter("user_location_set").trim();	
				String m_year = req.getParameter("user_year").trim();
				String m_month = req.getParameter("user_month").trim();
				
				
	/*
				rs= stmt.executeQuery (" SELECT A.NAME CLIENT_NAME,  "+
										" B.DESIGNATION_NAME DESIGNATION "+
										" FROM "+m_schema_name+".CO_CO_MAS_USER A, "+m_schema_name+".CO_CO_MAS_DESIGNATION B "+
										" WHERE A.DESIGNATION_CODE = B.DESIGNATION_CODE   "+
										" AND A.LOCATION_CODE = '"+m_user_location_set+"'");
				
				
				rs= stmt.executeQuery (" SELECT  A.NAME, B.DESIGNATION_NAME  ,0,0,A.LOCATION_CODE  FROM "+m_schema_name+".CO_CO_MAS_USER A, "+m_schema_name+".CO_CO_MAS_DESIGNATION B "+ 
									    " WHERE A.USER_ID  NOT IN (SELECT C.USER_ID FROM "+m_schema_name+".CO_MAS_LEND_TARGETS C) "+ 
										" AND A.DESIGNATION_CODE = B.DESIGNATION_CODE "+ 
										" UNION    "+ 
										" SELECT  "+ 
										" A.NAME, "+ 
										" A.DESIGNATION_NAME, "+ 
										" A.TARGET_CASES, "+ 
										" A.TARGET_VALUE, "+ 
										" A.LOCATION_CODE FROM  "+m_schema_name+".CO_MAS_LEND_TARGETS A ");
				*/
				
				
				// commented by udara 11-05-2015
				/*
				rs= stmt.executeQuery (" SELECT  A.EMP_CODE, "+m_schema_name+".AF_CO_GET_EMP_NAME(A.EMP_CODE), B.DESIGNATION_NAME  ,0,0, "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE), A.LOCATION_CODE  FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".CO_CO_MAS_DESIGNATION B "+
									 " WHERE A.EMP_CODE  NOT IN (SELECT C.USER_ID FROM CO_MAS_LEND_TARGETS C WHERE C.PERIOD_MONTH = '"+m_month+"' AND C.PERIOD_YEAR = '"+m_year+"')    "+
									 " AND A.DESIGNATION_CODE = B.DESIGNATION_CODE  "+
									 " AND A.LOCATION_CODE = '"+m_user_location_set+"'  "+
								     " AND UPPER(A.ACTIVE_STATUS) = 'Y' "+ 		
									 " UNION     "+
									 " SELECT   "+
									 " A.USER_ID,  "+
									 " "+m_schema_name+".AF_CO_GET_EMP_NAME(A.USER_ID),  "+
									 " A.DESIGNATION_NAME,  "+
									 " A.TARGET_CASES,  "+
									 " A.TARGET_VALUE,  "+
									 " "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE),  A.LOCATION_CODE FROM  "+m_schema_name+".CO_MAS_LEND_TARGETS A  "+
									 " WHERE A.LOCATION_CODE = '"+m_user_location_set+"'  "+
									 " AND A.PERIOD_MONTH = '"+m_month+"' AND A.PERIOD_YEAR = '"+m_year+"' ");
				*/
				
				// commented by udara 13-08-2015
				/*
				// added by udara 11-05-2015
				rs= stmt.executeQuery (" SELECT  A.EMP_CODE, "+m_schema_name+".AF_CO_GET_EMP_NAME(A.EMP_CODE), B.DESIGNATION_NAME  ,0,0, "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE), A.LOCATION_CODE  FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".CO_CO_MAS_DESIGNATION B "+
									 " WHERE A.EMP_CODE  NOT IN (SELECT C.USER_ID FROM CO_MAS_LEND_TARGETS C WHERE C.PERIOD_MONTH = '"+m_month+"' AND C.PERIOD_YEAR = '"+m_year+"')    "+
									 " AND A.DESIGNATION_CODE = B.DESIGNATION_CODE  "+
									 " AND A.LOCATION_CODE = '"+m_user_location_set+"'  "+
								     " AND UPPER(A.ACTIVE_STATUS) = 'Y' "+ 		
									 " UNION     "+
									 " SELECT   "+
									 " A.USER_ID,  "+
									 " "+m_schema_name+".AF_CO_GET_EMP_NAME(A.USER_ID),  "+
									 " A.DESIGNATION_NAME,  "+
									 " A.TARGET_CASES,  "+
									 " A.TARGET_VALUE,  "+
									 " "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE),  A.LOCATION_CODE FROM  "+m_schema_name+".CO_MAS_LEND_TARGETS A , "+m_schema_name+".CO_CO_MAS_EMPLOYEE B "+
									 " WHERE A.LOCATION_CODE = '"+m_user_location_set+"'  "+
									 " AND  A.USER_ID = B.EMP_CODE   "+
									 " AND UPPER(B.ACTIVE_STATUS) = 'Y' "+ 
									 " AND A.PERIOD_MONTH = '"+m_month+"' AND A.PERIOD_YEAR = '"+m_year+"' ");
				*/
				
				// added by udara 13-08-2015
				rs= stmt.executeQuery (" SELECT  A.EMP_CODE, "+m_schema_name+".AF_CO_GET_EMP_NAME(A.EMP_CODE), B.DESIGNATION_NAME  ,0,0, "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE), A.LOCATION_CODE  FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".CO_CO_MAS_DESIGNATION B "+
									 " WHERE A.EMP_CODE  NOT IN (SELECT C.USER_ID FROM "+m_schema_name+".CO_MAS_LEND_TARGETS C WHERE C.PERIOD_MONTH = '"+m_month+"' AND C.PERIOD_YEAR = '"+m_year+"')    "+
									 " AND A.DESIGNATION_CODE = B.DESIGNATION_CODE  "+
									 " AND A.LOCATION_CODE = '"+m_user_location_set+"'  "+
								     //" AND UPPER(A.ACTIVE_STATUS) = 'Y' "+ // released by udara 25-03-2016 //  	COMMENTED BY KANCHANA on 2016-02-08 FOR #19439	
									 
									 " AND A.EMP_CODE IN (SELECT EMP_ID FROM "+m_schema_name+".CO_CO_MAS_USER WHERE ACTIVE_STATUS='Y' AND EMP_ID=A.EMP_CODE) "+ // added by udara 03-05-2016	
										
									 " UNION     "+
									 " SELECT   "+
									 " A.USER_ID,  "+
									 " "+m_schema_name+".AF_CO_GET_EMP_NAME(A.USER_ID),  "+
									 " A.DESIGNATION_NAME,  "+
									 " A.TARGET_CASES,  "+
									 " A.TARGET_VALUE,  "+
									 " "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE), "+
										" A.LOCATION_CODE "+ // commented by udara 20-07-2016
										//" C.LOCATION_CODE "+ // added by udara 20-07-2016
										" FROM  "+m_schema_name+".CO_MAS_LEND_TARGETS A , "+m_schema_name+".CO_CO_MAS_EMPLOYEE B, "+m_schema_name+".CO_CO_MAS_USER C "+
									 //" WHERE A.LOCATION_CODE = '"+m_user_location_set+"'  "+ // commented by udara 11-07-2016
									 //" AND A.USER_ID = B.EMP_CODE   "+ // commented by udara 11-07-2016
									 
									 " WHERE A.USER_ID = B.EMP_CODE   "+ // added by udara 11-07-2016
									 " AND A.LOCATION_CODE = '"+m_user_location_set+"'  "+ // added by udara 20-07-2016
									 //" AND B.LOCATION_CODE = '"+m_user_location_set+"'  "+ // added by udara 11-07-2016
										
									 //" AND UPPER(B.ACTIVE_STATUS) = 'Y' "+ // released by udara 25-03-2016 //  COMMENTED BY KANCHANA FOR #19439 on 2016-02-08
									 //"AND UPPER(B.ACTIVE_STATUS) IN ('N','Y') "+ // commented by udara 25-03-2016 // Added by Kanchana on 2016-02-08 for #19439
									 " AND A.USER_ID  = C.EMP_ID  "+
									 " AND UPPER(C.ACTIVE_STATUS) = 'Y' "+  // released by udara 25-03-2016
									// " AND UPPER(C.ACTIVE_STATUS) NOT IN ('N') "+  COMMENTED BY KANCHANA FOR #19439
									 //"  AND UPPER(C.ACTIVE_STATUS) IN ('N','Y') "+ // commented by udara 25-03-2016  //Added by Kanchana on 2016-02-08 for #19439
									 " AND A.PERIOD_MONTH = '"+m_month+"' AND A.PERIOD_YEAR = '"+m_year+"' ");
				// end by udara 13-08-2015
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R2>"+rs.getString(3)+"</R2>");
					out.print("<R2>"+rs.getString(4)+"</R2>");
					out.print("<R2>"+rs.getString(5)+"</R2>");
					out.print("<R2>"+rs.getString(6)+"</R2>");
					out.print("<R2>"+rs.getString(7)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			//added by nuwan de silva on 19-10-07---------------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Collection_assign_data")){
				String m_status = req.getParameter("ac_status").trim();
				String m_column = req.getParameter("sort_column").trim();
				String m_type = req.getParameter("order_by_type").trim();
				String m_col_officer = req.getParameter("col_officer").trim();					
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					" NVL(B.FULL_NAME,'-') CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.COLLECTION_OFFICER,'-') COLLECTION_OFFICER, "+
					" NVL(B.CLIENT_CODE,'-') CLIENT_CODE "+ //Added By Nuwan De Silva 19-04-2007
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) "+
					" ,SUBSTR(NVL(A.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // added by udara 15-07-2015
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
					///" AND A.APPLICATION_STATUS=UPPER('"+m_status+"')  "+ //COMMENT BY NUWAN DE SILVA ON 08-06-2010
					" AND A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+//Added by kanchana on 2016-06-09 for issue 20554
					" AND UPPER(A.COLLECTION_OFFICER)=UPPER('"+m_col_officer+"') "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>"); //ADDED BY NUWAN DE SILVA ON 08-04-208
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_Ins_Officer_Assign_Data")){ //Added BY Sandun on 03-03-2009
				String m_status      = req.getParameter("ac_status").trim();
				String m_column      = req.getParameter("sort_column").trim();
				String m_type        = req.getParameter("order_by_type").trim();
				String m_ins_officer = req.getParameter("col_officer").trim();					
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO APPLICATION_NO,"+
					" NVL(B.FULL_NAME,'-') CLIENT_NAME, "+
					" NVL(B.CITY_CODE,'-') CITY_CODE, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
					" NVL(A.TOTAL_FINANCE_AMOUNT,0.00) TOTAL_FINANCE_AMOUNT, "+
					" NVL(A.CURRENT_FINANCE_AMOUNT,0.00) CURRENT_FINANCE_AMOUNT, "+
					" NVL(A.INSURANCE_OFFICER,'-') INSURANCE_OFFICER, "+
					" NVL(B.CLIENT_CODE,'-') CLIENT_CODE "+ 
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME(A.INSURANCE_OFFICER) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_status+"')  "+
					" AND UPPER(A.INSURANCE_OFFICER)=UPPER('"+m_ins_officer+"') "+
					" ORDER BY "+m_column+" "+m_type+" ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>"); //ADDED BY NUWAN DE SILVA ON 08-04-208
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			//--added by delanjali on 2007-09-12-------------------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_group_invoices")){
				
				String m_status = req.getParameter("ac_status").trim();
				//	String m_client_code = req.getParameter("client_code").trim();					
				String m_group_code= req.getParameter("data_val").trim();	
				rs= stmt.executeQuery ( "SELECT DISTINCT A.GROUP_CODE, "+
					"B.CLIENT_CODE, "+
					"NVL(B.REMARKS,'-'), "+ 
					"TO_CHAR((NVL(B.AMOUNT,0)),'9,999,999,999,999,999,999,999.99'), "+
					"A.GROUP_NAME , "+
					"A.GROUP_ADDRESS ,"+
					"A.BRC_NO,'-',  "+
					""+m_schema_name+".af_co_get_client_name(B.CLIENT_CODE), "+
					" NVL(B.APPLICATION_NO,'-') "+
					"FROM "+m_schema_name+".AF_RE_PRO_GROUP_INVOICES A,"+m_schema_name+".AF_RE_PRO_ASSN_GROUP_INV B "+
					"WHERE A.GROUP_CODE=B.GROUP_CODE "+
					"AND UPPER(A.GROUP_CODE) =UPPER('"+m_group_code+"') "+
					"AND A.ACTIVE_STAUS='"+m_status+"' ");
				
				
				
				
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
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_group_invoices1")){
				
				//String m_status = req.getParameter("ac_status").trim();
				//	String m_client_code = req.getParameter("client_code").trim();					
				String m_group_code= req.getParameter("data_val").trim();	
				
				rs= stmt.executeQuery ( "SELECT DISTINCT A.GROUP_CODE "+
					"FROM "+m_schema_name+".AF_RE_PRO_GROUP_INVOICES A "+
					"WHERE  "+
					"UPPER(A.GROUP_CODE) =UPPER('"+m_group_code+"') ");
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//Added by Chandana on 02-10-2007 for reciept screen client state display ---------------------------
			else if (m_chksql.trim().equals("m_chk_LAKDL_AF_RE_val_client_comment")){
				String m_val = req.getParameter("data_val").trim();
				rs=stmt.executeQuery(" SELECT COMMENTS "+
					" FROM (SELECT ROWNUM  NO,COMMENTS "+
					" FROM (SELECT ENT_DATE M_DATE,COMMENTS "+
					" FROM  "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT  "+
					" WHERE UPPER(CLIENT_CODE) =UPPER('"+m_val+"') "+
					" AND   CASHIER_STATUS='Y' "+ //ADDED BY NUWAN DE SILVA 14-07-2009
					" ORDER BY ENT_DATE DESC "+
					" )) WHERE NO='1'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");						
				
			}
			
			//-----------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_display_group_receipts")){
				String m_receipt_no= req.getParameter("data_val").trim();	
				
				
				rs = stmt.executeQuery(" SELECT REC_NO,TO_CHAR(EFF_VALDATE,'DD'),TO_CHAR(EFF_VALDATE,'MM'),"+
					"        TO_CHAR(EFF_VALDATE,'YYYY'),CLIENT_CODE,CURR_CODE,"+
					"     	  REC_AMOUNT,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_CURR, "+
					"        SETTLE_MODE, PAYER_BRANCH_CODE, PAYER_ACC_NO, CHEQUE_NO,"+
					"        OTH_COMMENTS, BRANCH_CODE,ENTRY_TYPE, "+
					"        ACC_NO, STATUS, ENT_USER, ENT_DATE, RECON_STATUS, RECON_DATE,"+
					"        RECON_BY, EFF_VALDATE, REALISED_DATE,  "+
					"        EXCHANGE_RATE_BANK,  EXCHANGE_GAIN_LOSS,"+
					"        MOD_USER, MOD_DATE,TO_CHAR(CHEQUE_DATE,'DD'),TO_CHAR(CHEQUE_DATE,'MM'),TO_CHAR(CHEQUE_DATE,'YYYY'),nvl(to_char(TENDER_AMOUNT,'9,999,999,999,999,999,999,999,999.9999'),0),nvl(to_char(RETURN_AMOUNT,'9,999,999,999,999,999,999,999,999.9999'),0), "+
					" 				NVL(RENTAL_OTER_INVOICE,0),NVL(INSURANCE,0),NVL(LUXURY_TAX,0),NVL(REVANUE_LICENCE,0),NVL(RMV_CHARGES,0), "+	
					"        NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
					"        NVL((THIRD_PARTY_NAME),'-') ,"+	 //added by nuwan de silva on 01-08-07
					"        NVL((THIRD_PARTY_ADDRESS),'-') "+ //added by nuwan de silva on 01-08-07	
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
					" WHERE  REC_NO = '"+m_receipt_no+"' ");	
				
				
				
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
					out.print("<R15>"+rs.getString(30)+"</R15>");
					out.print("<R16>"+rs.getString(31)+"</R16>");    
					out.print("<R17>"+rs.getString(32)+"</R17>");  
					
					
					//out.print("<R15>"+rs.getString(15)+"</R15>");
					//out.print("<R16>"+rs.getString(16)+"</R16>");
					//out.print("<R17>"+rs.getString(17)+"</R17>");
					out.print("<R18>"+rs.getString(33)+"</R18>");
					out.print("<R19>"+rs.getString(34)+"</R19>");
					
					
					out.print("<R20>"+nf.format(rs.getDouble(35))+"</R20>");
					out.print("<R21>"+nf.format(rs.getDouble(36))+"</R21>");
					out.print("<R22>"+nf.format(rs.getDouble(37))+"</R22>");
					out.print("<R23>"+nf.format(rs.getDouble(38))+"</R23>");
					out.print("<R24>"+nf.format(rs.getDouble(39))+"</R24>");
					
					
					out.print("<R25>"+rs.getString(40)+"</R25>");
					out.print("<R26>"+rs.getString(41)+"</R26>");
					out.print("<R27>"+rs.getString(42)+"</R27>");
					
					//out.print("<R28>"+rs.getString(28)+"</R28>");
					//out.print("<R29>"+rs.getString(29)+"</R29>");
					//out.print("<R30>"+rs.getString(30)+"</R30>");
					//out.print("<R31>"+rs.getString(31)+"</R31>");         
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
				
				
				
				/* boolean flag = rs.next();
					out.println("<Root>");
					for(; flag; flag = rs.next())				{
						out.println("<ITEM>");
						out.println("<REP>"     + rs.getString(1)  + "</REP>");
						out.println("<FIN>"     + rs.getString(2)  + "</FIN>");
						out.println("<SEZ>"     + rs.getString(3)  + "</SEZ>");
						out.println("<LVP>"     + rs.getString(4)  + "</LVP>");
						out.println("<INA>"     + rs.getString(5)  + "</INA>");
						out.println("<IAC>"     + rs.getString(6)  + "</IAC>");
						out.println("<EXR>"     + rs.getString(7)  + "</EXR>");
						out.println("<TCC>"     + rs.getString(8)  + "</TCC>");
						out.println("<VIS>"     + rs.getString(9)  + "</VIS>");
						out.println("<INN>"     + rs.getString(10) + "</INN>");
						out.println("<TRD>"     + rs.getString(11) + "</TRD>");
						out.println("<RED>"     + rs.getString(12) + "</RED>");
						out.println("<TRD1>"    + rs.getString(13) + "</TRD1>");
						out.println("<RED1>"    + rs.getString(14) + "</RED1>");
						out.println("<CHQDD>"   + rs.getString(30) + "</CHQDD>");
						out.println("<CHQMM>"   + rs.getString(31) + "</CHQMM>");
						out.println("<CHQYY>"   + rs.getString(32) + "</CHQYY>");
						
						//-DATE:2007-03-05----------------------------------------------------------
						out.println("<TEN>"   + rs.getString(33) + "</TEN>");
						out.println("<RET>"   + rs.getString(34) + "</RET>");
						//--------------------------------------------------------------------------
					
						out.println("<CHA1>"   + nf.format(rs.getDouble(35)) + "</CHA1>");
						out.println("<CHA2>"   + nf.format(rs.getDouble(36)) + "</CHA2>");
						out.println("<CHA3>"   + nf.format(rs.getDouble(37)) + "</CHA3>");
						out.println("<CHA4>"   + nf.format(rs.getDouble(38)) + "</CHA4>");
						out.println("<CHA5>"   + nf.format(rs.getDouble(39)) + "</CHA5>");
					out.println("<CHA6>"   + rs.getString(40) + "</CHA6>");
						out.println("<TNAME>"   + rs.getString(41) + "</TNAME>"); //added by nuwan de silva on 01-08-07
						out.println("<TADD>"   + rs.getString(42) + "</TADD>");  //added by nuwan de silva on 01-08-07
						out.println("</ITEM>");
				
		*/
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
			
			else if(m_chksql.equals("get_fin")){ 
				String m_client_code  = req.getParameter("client");
				out.println("<DATA>");
				rs = stmt.executeQuery(  " SELECT DISTINCT FINANCE_NO "+
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE CLIENT_CODE = '"+m_client_code+"' "+
					//" AND "+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO) IN ('LEGAL','ACTIVATED')  "+
					" AND APPLICATION_STATUS IN ('LEGAL','ACTIVATED') "+ // chaned by ns 17-12-2009
					" AND FINANCE_NO IS NOT NULL ");
				
				boolean more = rs.next();
				
				while(more){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("</ITEM>");
					more = rs.next();
				}
				
				out.println("</DATA>");
			}	
			
			// added by udara 11-02-2014
			else if(m_chksql.equals("set_end_date")){
				String m_start_date  = req.getParameter("start_date");
				
				rs = stmt.executeQuery( " "+
					" SELECT  "+
						//" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)+1,'DD'), "+
						//" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)+1,'MM'), "+
						//" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)+1,'YYYY') "+
						" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'DD'), "+
						" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'MM'), "+
						" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'YYYY') "+
							" FROM DUAL "+
					" ");
				
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
			// end by udara 11-02-2014
			
			//Added by Kanchana.
				else if(m_chksql.equals("check_date_range")){
				String m_start_date  = req.getParameter("start_date");
				String m_hidden_fin_no  = req.getParameter("hidden_fin_no");
				rs = stmt.executeQuery( " "+
					" SELECT  COUNT(FINANCE_NO),TO_CHAR(MAX(END_DATE),'DD-MM-YYYY') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA "+
					" WHERE  FINANCE_NO='"+m_hidden_fin_no+"' AND  TRUNC(END_DATE,'DD') > =TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					" AND TRUNC(START_DATE,'DD')<=TO_DATE('"+m_start_date+"','DD-MM-YYYY') ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");					
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
			}
			
			else if(m_chksql.equals("start_date_check")){
				String m_start_date  = req.getParameter("start_date").trim();
				String m_hidden_fin_no  = req.getParameter("hidden_fin_no");
				String mm_policy_no  = req.getParameter("hidden_policy_no");
				String mm_debit_note_no  = req.getParameter("hidden_debit_note_no");
				
				rs=stmt.executeQuery(" SELECT  TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+  
							           " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //2
									 //  " DATEDIFF(NOW(),'"+m_start_date+"') "+
									  " TO_CHAR (TRUNC(sysdate,'DD') - TRUNC(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),'DD')),"+
							         // " TO_CHAR((SELECT TO_DATE(SYSDATE, 'YYYY-MM-DD') -  TO_DATE(A.START_DATE, 'YYYY-MM-DD') DAYS FROM   dual)) "+ //3
							        //   " TRUNC ((SELECT TO_DATE(SYSDATE, 'YYYY-MM-DD') -  TO_DATE(A.END_DATE, 'YYYY-MM-DD') DAY FROM   dual)) "+ //4
									 //  " TO_CHAR ((SELECT SYSDATE -  A.START_DATE DAYS FROM   DUAL)) "+
										
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'DD'), "+
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'MM'), "+
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'YYYY') "+	
										
							           " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
							           " WHERE A.POLICY_NO = '"+mm_policy_no+"' "+
									   " AND A.DEBIT_NOTE_NO='"+mm_debit_note_no+"' ");
									
									
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getInt(3)+"</R3>");
					out.println("<R4>"+rs.getString(4)+"</R4>");
					out.println("<R5>"+rs.getString(5)+"</R5>");
					out.println("<R6>"+rs.getString(6)+"</R6>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
			}
			
			
			
		//Ended by Kanchana.	
		//ADDED BY JITHENDRA FOR SECURITY INSURANCE
		else if(m_chksql.equals("check_date_range_sec")){
				String m_start_date  = req.getParameter("start_date");
				String m_hidden_fin_no  = req.getParameter("hidden_fin_no");
				rs = stmt.executeQuery( " "+
					" SELECT  COUNT(FINANCE_NO),TO_CHAR(MAX(END_DATE),'DD-MM-YYYY') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC "+
					" WHERE  FINANCE_NO='"+m_hidden_fin_no+"' AND  TRUNC(END_DATE,'DD') > =TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					" AND TRUNC(START_DATE,'DD')<=TO_DATE('"+m_start_date+"','DD-MM-YYYY') ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");					
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
			}
			
			else if(m_chksql.equals("start_date_check_sec")){
				String m_start_date  = req.getParameter("start_date").trim();
				String m_hidden_fin_no  = req.getParameter("hidden_fin_no");
				String mm_policy_no  = req.getParameter("hidden_policy_no");
				String mm_debit_note_no  = req.getParameter("hidden_debit_note_no");
				
				rs=stmt.executeQuery(" SELECT  TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+  
							           " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //2
									 //  " DATEDIFF(NOW(),'"+m_start_date+"') "+
									  " TO_CHAR (TRUNC(sysdate,'DD') - TRUNC(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),'DD')),"+
							         // " TO_CHAR((SELECT TO_DATE(SYSDATE, 'YYYY-MM-DD') -  TO_DATE(A.START_DATE, 'YYYY-MM-DD') DAYS FROM   dual)) "+ //3
							        //   " TRUNC ((SELECT TO_DATE(SYSDATE, 'YYYY-MM-DD') -  TO_DATE(A.END_DATE, 'YYYY-MM-DD') DAY FROM   dual)) "+ //4
									 //  " TO_CHAR ((SELECT SYSDATE -  A.START_DATE DAYS FROM   DUAL)) "+
										
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'DD'), "+
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'MM'), "+
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'YYYY') "+	
										
							           " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC A "+
							           " WHERE A.POLICY_NO = '"+mm_policy_no+"' "+
									   " AND A.DEBIT_NOTE_NO='"+mm_debit_note_no+"' ");
									
									
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getInt(3)+"</R3>");
					out.println("<R4>"+rs.getString(4)+"</R4>");
					out.println("<R5>"+rs.getString(5)+"</R5>");
					out.println("<R6>"+rs.getString(6)+"</R6>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
			}
		
					else if(m_chksql.equals("start_date_check_new")){
				String m_start_date  = req.getParameter("start_date").trim();
				String m_hidden_fin_no  = req.getParameter("hidden_fin_no");
				String mm_policy_no  = req.getParameter("hidden_policy_no");
				String mm_debit_note_no  = req.getParameter("hidden_debit_note_no");
				
				rs=stmt.executeQuery(" SELECT   "+  
							       //    " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //2
									 //  " DATEDIFF(NOW(),'"+m_start_date+"') "+
									  " TO_CHAR (TRUNC(sysdate,'DD') - TRUNC(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),'DD')),"+
							         // " TO_CHAR((SELECT TO_DATE(SYSDATE, 'YYYY-MM-DD') -  TO_DATE(A.START_DATE, 'YYYY-MM-DD') DAYS FROM   dual)) "+ //3
							        //   " TRUNC ((SELECT TO_DATE(SYSDATE, 'YYYY-MM-DD') -  TO_DATE(A.END_DATE, 'YYYY-MM-DD') DAY FROM   dual)) "+ //4
									 //  " TO_CHAR ((SELECT SYSDATE -  A.START_DATE DAYS FROM   DUAL)) "+
										
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'DD'), "+
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'MM'), "+
									 " TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),12)-1,'YYYY') "+	
										
							           " FROM DUAL  ");
									
									
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getString(3)+"</R3>");
					out.println("<R4>"+rs.getString(4)+"</R4>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
			}
			
		//Ended by Kanchana.
		
		
		
		
		
			// added by udara 13-02-2014
			else if(m_chksql.equals("set_pricing_no")){
				
				String m_app_no         = req.getParameter("app_no");
				String m_devision_type  = req.getParameter("devision_type");
				String m_check_value    = req.getParameter("check_value");
				
				rs=stmt.executeQuery("SELECT A.PRICING_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+
							"      "+m_schema_name+".AF_CO_MAS_MODEL B, "+
							"      "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C,"+
							"	   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS D"+
							" WHERE A.APPLICATION_NO=C.APPLICATION_NO "+
							" AND A.APPLICATION_NO=D.APPLICATION_NO"+
							" AND A.APPLICATION_NO='"+m_app_no+"' "+
							" AND A.ASSET_ID=C.ASSET_ID "+
							" AND A.MODEL_CODE=B.MODEL_CODE "+
							//" AND D.DIVISION_CODE     = '"+m_devision_type+"'  "+ 
							" AND B.MAKE_CODE || '-' || B.MODEL_CODE || '-' ||A.ENGINE_NO || '-'|| A.REG_NO || '@' || A.INVOICE_NO || '@' = '"+m_check_value+"' "+
							" AND A.ACTIVE_STATUS = 'Y'							"+							
							" AND (D.FINANCE_NO ,A.INVOICE_NO) NOT IN( "+
							" SELECT E.FINANCE_NO ,E.PRO_INVOICE_NO "+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E )");
				
				
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
			}	
			// end by udara 13-02-2014
			
			// added by udara 13-02-2014
			else if(m_chksql.equals("get_basic_rcc_comm_rate")){
				
				String m_insurance_agent = req.getParameter("insurance_agent");
				String m_sum_insured = req.getParameter("sum_insured");
				String m_sub_cat = req.getParameter("sub_cat");
				
				rs=stmt.executeQuery(" "+					
					" SELECT BASIC_COMM_RATE, "+
					" RCC_COMM_RATE, "+
					" BASIC_PREMIUM, "+
					" PAYABLE_PREMIUM, "+ // added by udara 16-04-2014
					" CEIL(PAYABLE_PREMIUM)   "+ // added by udara 03-11-2014
					" FROM "+m_schema_name+".AF_UPLOAD_ASSET_DETAILS "+
					//" WHERE INSURANCE_AGENT = '"+m_insurance_agent+"' "+
					//" WHERE VEHICLE_TYPE = '"+m_insurance_agent+"' "+
					" WHERE INSURANCE_AGENT = '"+m_insurance_agent+"' "+
					" AND  VEHICLE_TYPE = '"+m_sub_cat+"' "+
					" AND SUM_INSURED = '"+m_sum_insured+"'	"+	 			
					" ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getDouble(3)+"</R3>");
					out.println("<R4>"+rs.getDouble(4)+"</R4>");
					out.println("<R5>"+rs.getDouble(5)+"</R5>"); // added by udara 03-11-2014
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
				
			}
			// end by udara 13-02-2014
			
			
			// added by udara 19-07-2018
			else if(m_chksql.equals("get_flag_status")){
				
				String m_insurance_agent = req.getParameter("insurance_agent");
				String m_sum_insured = req.getParameter("sum_insured");
				String m_sub_cat = req.getParameter("sub_cat");
				
				
				rs=stmt.executeQuery(" "+					
					" SELECT NVL(FLAG_THIS,'N') "+
					" FROM "+m_schema_name+".AF_UPLOAD_ASSET_DETAILS "+
					" WHERE INSURANCE_AGENT = '"+m_insurance_agent+"' "+
					" AND  VEHICLE_TYPE = '"+m_sub_cat+"' "+
					" AND SUM_INSURED = '"+m_sum_insured+"'	"+	 			
					" ");
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
				
			}
			// end by udara 19-07-2018
			
			
			// added by udara 20-02-2014
			else if(m_chksql.equals("set_pricing_no_edit")){
				
				String m_app_no         = req.getParameter("app_no");
				String m_devision_type  = req.getParameter("devision_type");
				String m_check_value    = req.getParameter("check_value");
				
				int m_count = 0;
				
				
				/*
				rs=stmt.executeQuery("SELECT A.PRICING_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+
							"      "+m_schema_name+".AF_CO_MAS_MODEL B, "+
							"      "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C,"+
							"	   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS D"+
							" WHERE A.APPLICATION_NO=C.APPLICATION_NO "+
							" AND A.APPLICATION_NO=D.APPLICATION_NO"+
							" AND A.APPLICATION_NO='"+m_app_no+"' "+
							" AND A.ASSET_ID=C.ASSET_ID "+
							" AND A.MODEL_CODE=B.MODEL_CODE "+
							" AND D.DIVISION_CODE     = '"+m_devision_type+"'  "+ 
							" AND B.MAKE_CODE || '-' || B.MODEL_CODE || '-' ||A.ENGINE_NO || '-'|| A.REG_NO || '@' || A.INVOICE_NO || '@' = '"+m_check_value+"' "+
							" AND A.ACTIVE_STATUS = 'Y'							"+							
							" AND (D.FINANCE_NO ,A.INVOICE_NO) IN( "+
							" SELECT E.FINANCE_NO ,E.PRO_INVOICE_NO "+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E )" +
							" ");
				*/
				
				
				rs=stmt.executeQuery(" "+
				  " SELECT COUNT(C.PRICING_NO) "+
				  " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E,  "+m_schema_name+".AF_CO_PRO_INVOICE B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES D  "+
				  " WHERE E.FINANCE_NO = AF_CO_GET_FINANCE_NO('"+m_app_no+"') "+
				  " AND E.REF_DEBIT_NOTE_NO = B.INVOICE_NO  "+
				  " AND E.PRO_INVOICE_NO = C.INVOICE_NO  "+
				  " AND C.PRICING_NO = D.PRICING_NO  "+
				  " AND D.CHARGE_TYPE = 'INV'  "+
				  " AND E.ASSET_DESCRIPTION || '@' || E.PRO_INVOICE_NO ||'@' = '"+m_check_value+"' "+ 
					" ");
				if(rs.next()){
					m_count = rs.getInt(1);
				}
				
				
				if(m_count>0){
				
						rs=stmt.executeQuery(" "+
						  " SELECT C.PRICING_NO "+
						  " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E,  "+m_schema_name+".AF_CO_PRO_INVOICE B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES D  "+
						  " WHERE E.FINANCE_NO = AF_CO_GET_FINANCE_NO('"+m_app_no+"') "+
						  " AND E.REF_DEBIT_NOTE_NO = B.INVOICE_NO  "+
						  " AND E.PRO_INVOICE_NO = C.INVOICE_NO  "+
						  " AND C.PRICING_NO = D.PRICING_NO  "+
						  " AND D.CHARGE_TYPE = 'INV'  "+
						  " AND E.ASSET_DESCRIPTION || '@' || E.PRO_INVOICE_NO ||'@' = '"+m_check_value+"' "+ 
							" ");
						
				}
				else{
					
						rs=stmt.executeQuery(" "+
							
							" select C.PRICING_NO  "+
			                   " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+  
			                   " WHERE E.FINANCE_NO = AF_CO_GET_FINANCE_NO('"+m_app_no+"')  "+
			                   " and E.PRO_INVOICE_NO = C.INVOICE_NO   "+
			                   " AND E.ASSET_DESCRIPTION || '@' || E.PRO_INVOICE_NO ||'@' = '"+m_check_value+"' "+
							   " ORDER BY E.ENT_DATE DESC  "+
							" ");
					
				}
				
				
				
				out.println("<DATA>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("</ITEM>");
				}
				out.println("</DATA>");
				
				
			}	
			// end by udara 20-02-2014
			
			
			// Added by Thamali Jayatunga 2011-07-07
			else if(m_chksql.equals("edit_tax_asset_insurance")){ 
				String m_policy_no  = req.getParameter("policy_no");		
				String m_debit_note_no  = req.getParameter("debit_note_no");
				
				rs = stmt.executeQuery(  " SELECT A.TAX_CODE, B.TAX_DESC, NVL(A.TAX_AMOUNT,0.00) "+
					" FROM   "+m_schema_name+".AF_IS_PRO_ASET_TAX_DETA A, "+m_schema_name+".AF_CO_MAS_INS_TAX B "+
					" WHERE  A.TAX_CODE = B.TAX_CODE AND A.POLICY_NO = '"+m_policy_no+"' AND A.DEBIT_NOTE_NO = '"+m_debit_note_no+"' ");
				
				boolean more = rs.next();
				out.println("<DATA>");
				while(more){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getString(1)+"</R1>");
					out.println("<R2>"+rs.getString(2)+"</R2>");
					out.println("<R3>"+rs.getDouble(3)+"</R3>");
					out.println("</ITEM>");
					more = rs.next();
				}
				
				out.println("</DATA>");
			}
			
			// added by udara 30-06-2014
			else if(m_chksql.equals("val_policy_no")){
				String m_policy_no  = req.getParameter("policy_no");		
				String m_debit_note_no  = req.getParameter("debit_note_no");
				
				String m_policy_no_fin      = "";		
				String m_debit_note_no_fin  = "";
				
				rs = stmt.executeQuery(" "+
				" select "+
				" (select COUNT(POLICY_NO) from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA where POLICY_NO = '"+m_policy_no+"') CNT_POLICY_NO, "+
				" (select COUNT(DEBIT_NOTE_NO) from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA where DEBIT_NOTE_NO = '"+m_debit_note_no+"' ) CNT_DEBIT_NOTE_NO, "+
				" (select FINANCE_NO from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA where POLICY_NO = '"+m_policy_no+"' and rownum=1) CNT_POLICY_NO_FIN, "+
				" (select FINANCE_NO from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA where DEBIT_NOTE_NO = '"+m_debit_note_no+"' and rownum=1 ) CNT_DEBIT_NOTE_NO_FIN "+
				" from DUAL ");
				
				int cnt_policy_no = 0;
				int cnt_debit_note_no = 0;
				boolean more = rs.next();
				
				if(more){
					cnt_policy_no = rs.getInt(1);
					cnt_debit_note_no = rs.getInt(2);
					
					m_policy_no_fin = rs.getString(3);
					m_debit_note_no_fin = rs.getString(4);
					
					more = rs.next();
				}
				
				
				out.println("<DATA>");
				out.println("<ITEM>");
				out.println("<R1>"+cnt_policy_no+"</R1>");
				out.println("<R2>"+cnt_debit_note_no+"</R2>");
				
				out.println("<R3>"+m_policy_no_fin+"</R3>");
				out.println("<R4>"+m_debit_note_no_fin+"</R4>");
				
				out.println("</ITEM>");
				out.println("</DATA>");
				
				
			}
			// end by udara 30-06-2014
			
			
			// Added by Thamali Jayatunga 2011-09-03
			else if(m_chksql.equals("edit_commision")){ 
				String m_policy_no  = req.getParameter("policy_no");		
				String m_debit_note_no  = req.getParameter("debit_note_no");
				int count = 0;
				
				rs = stmt.executeQuery(  " SELECT COUNT(*) "+
					" FROM   "+m_schema_name+".AF_IS_PRO_ASET_INSUR_COMMISION  "+
					" WHERE  POLICY_NO = '"+m_policy_no+"' AND DEBIT_NOTE_NO = '"+m_debit_note_no+"' ");
				
				while(rs.next())
					count = rs.getInt(1);
				
				if(count==0){
					rs = stmt.executeQuery(  " SELECT NVL(A.BASIC_PREMIUM_COMMISION,0.00), NVL(A.RCC_TC_COMMISION,0.00), NVL(A.VAT_ON_TOTAL_COMMISION,0.00), "+
						" 0.00, 0.00, '','','','',NVL(A.BASIC_PREMIUM_COMMISION,0.00), NVL(A.RCC_TC_COMMISION,0.00) "+
						" FROM   "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A"+
						" WHERE  A.POLICY_NO = '"+m_policy_no+"' AND A.DEBIT_NOTE_NO = '"+m_debit_note_no+"' ");
					
				}
				else {
					rs = stmt.executeQuery(  " SELECT NVL(A.BASIC_PREMIUM_COMMISION,0.00), NVL(A.RCC_TC_COMMISION,0.00), NVL(A.VAT_ON_TOTAL_COMMISION,0.00), "+
						" NVL(B.BASIC_COMMISION_RECEIVED,0.00), NVL(B.RCC_COMMISION_RECEIVED,0.00), "+
						" B.CHEQUE_NO, B.BANK, TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'), TO_CHAR(B.RECEIVED_DATE,'DD-MM-YYYY'), "+
						" NVL((A.BASIC_PREMIUM_COMMISION - B.BASIC_COMMISION_RECEIVED),0.00), NVL((A.RCC_TC_COMMISION - B.RCC_COMMISION_RECEIVED),0.00)"+
						" FROM   "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+m_schema_name+".AF_IS_PRO_ASET_INSUR_COMMISION B "+
						" WHERE  A.POLICY_NO = B.POLICY_NO AND A.DEBIT_NOTE_NO = B.DEBIT_NOTE_NO AND A.POLICY_NO = '"+m_policy_no+"' AND A.DEBIT_NOTE_NO = '"+m_debit_note_no+"' ");
					
				}
				
				boolean more = rs.next();
				out.println("<DATA>");
				while(more){
					out.println("<ITEM>");
					out.println("<R1>"+rs.getDouble(1)+"</R1>");
					out.println("<R2>"+rs.getDouble(2)+"</R2>");
					out.println("<R3>"+rs.getDouble(3)+"</R3>");
					out.println("<R4>"+rs.getDouble(4)+"</R4>");
					out.println("<R5>"+rs.getDouble(5)+"</R5>");
					out.println("<R6>"+rs.getString(6)+"</R6>");
					out.println("<R7>"+rs.getString(7)+"</R7>");
					out.println("<R8>"+rs.getString(8)+"</R8>");
					out.println("<R9>"+rs.getString(9)+"</R9>");
					out.println("<R10>"+rs.getDouble(10)+"</R10>");
					out.println("<R11>"+rs.getDouble(11)+"</R11>");
					out.println("</ITEM>");
					more = rs.next();
				}
				
				out.println("</DATA>");
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


