//Option Id is 2.1  
//This File was created by SVA on 17-05-2006 
//Marketing Inquiry Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_XMLFile extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		Connection conn= null;
		Statement stmt= null;
		java.text.NumberFormat nf= null;
		ResultSet rs= null;
		String m_chksql= null;
		ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= "AA";//m_sn_methods.username;
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			//httpservletresponse.setContentType("text/xml");
			res.setStatus(200);
			res.setContentType("text/xml");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
				
			}
			/*			else if(m_chksql.trim().equals("get_inv_gen_date")){
			       
							rs = stmt.executeQuery(" SELECT TO_CHAR(MAX(A.TO_DATE),'DD'), "+
							                       "        TO_CHAR(MAX(A.TO_DATE),'MM'), "+
																		 "        TO_CHAR(MAX(A.TO_DATE),'YYYY'),"+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'DD'), "+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'MM'), "+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'YYYY') "+
																		 "  FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DATE A");

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FDD>"     + rs.getString(1)  + "</FDD>");
					out.println("<FMM>"     + rs.getString(2)  + "</FMM>");
					out.println("<FYY>"     + rs.getString(3)  + "</FYY>");
					out.println("<TDD>"     + rs.getString(4)  + "</TDD>");
					out.println("<TMM>"     + rs.getString(5)  + "</TMM>");
					out.println("<TYY>"     + rs.getString(6)  + "</TYY>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }*/
			else if(m_chksql.trim().equals("get_inv_gen_date")){
			       
							rs = stmt.executeQuery("SELECT TO_CHAR(TO_DATE+1,'DD'),TO_CHAR(TO_DATE+1,'MM'), "+
							                       "       TO_CHAR(TO_DATE+1,'YYYY'),TO_CHAR(ADD_MONTHS(TO_DATE,1),'DD'), "+
																		 "       TO_CHAR(ADD_MONTHS(TO_DATE,1),'MM'),TO_CHAR(ADD_MONTHS(TO_DATE,1),'YYYY'), "+
																		 "			 TO_CHAR(FROM_DATE,'DD'),TO_CHAR(FROM_DATE,'MM'),TO_CHAR(FROM_DATE,'YYYY'), "+
																		 " 			 TO_CHAR(TO_DATE,'DD'),TO_CHAR(TO_DATE,'MM'),TO_CHAR(TO_DATE,'YYYY') "+
																		 "FROM  (SELECT MAX(A.TO_DATE) TO_DATE,MAX(A.FROM_DATE) FROM_DATE  "+
																		 " 			 FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DATE A)");
																			
							                    /*(" SELECT TO_CHAR(MAX(A.TO_DATE),'DD'), "+
							                       "        TO_CHAR(MAX(A.TO_DATE),'MM'), "+
																		 "        TO_CHAR(MAX(A.TO_DATE),'YYYY'),"+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'DD'), "+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'MM'), "+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'YYYY') "+
																		 "  FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DATE A");*/

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FDD>"     + rs.getString(1)  + "</FDD>");
					out.println("<FMM>"     + rs.getString(2)  + "</FMM>");
					out.println("<FYY>"     + rs.getString(3)  + "</FYY>");
					out.println("<TDD>"     + rs.getString(4)  + "</TDD>");
					out.println("<TMM>"     + rs.getString(5)  + "</TMM>");
					out.println("<TYY>"     + rs.getString(6)  + "</TYY>");
					out.println("<LFDD>"    + rs.getString(7)  + "</LFDD>");
					out.println("<LFMM>"    + rs.getString(8)  + "</LFMM>");
					out.println("<LFYY>"    + rs.getString(9)  + "</LFYY>");
					out.println("<LTDD>"    + rs.getString(10) + "</LTDD>");
					out.println("<LTMM>"    + rs.getString(11) + "</LTMM>");
					out.println("<LTYY>"    + rs.getString(12) + "</LTYY>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			
			else if(m_chksql.trim().equals("get_inv_var_rate")){
			       
							String m_from_date = req.getParameter("from_date");
			        String m_to_date   = req.getParameter("to_date");
			
							rs = stmt.executeQuery("SELECT /*"+m_schema_name+".AF_CO_GET_BASE_RATE_STATUS('"+m_from_date+"','"+m_to_date+"')*/ 'YES' FROM DUAL");

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FDD>"     + rs.getString(1)  + "</FDD>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			
				else if(m_chksql.trim().equals("get_date_dif")){
			       
							String m_from_date = req.getParameter("from_date");
			        String m_to_date   = req.getParameter("to_date");
			
							rs = stmt.executeQuery(" SELECT TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYY'),'DD'), "+
							                       "        TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYY'),'MM'), "+
																		 "        TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYY'),'YYYY'),"+
																		 "        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYY'),'DD'), "+
																		 "        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYY'),'MM'), "+
																		 "        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYY'),'YYYY') "+
																		 "  FROM  "+m_schema_name+".DUAL "+
																		 " WHERE  TO_DATE('"+m_from_date+"','DD-MM-YYYY')<=	TO_DATE('"+m_to_date+"','DD-MM-YYYY')");

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FDD>"     + rs.getString(1)  + "</FDD>");
					out.println("<FMM>"     + rs.getString(2)  + "</FMM>");
					out.println("<FYY>"     + rs.getString(3)  + "</FYY>");
					out.println("<TDD>"     + rs.getString(4)  + "</TDD>");
					out.println("<TMM>"     + rs.getString(5)  + "</TMM>");
					out.println("<TYY>"     + rs.getString(6)  + "</TYY>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
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
																		 "        NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.REPOSSESS_OFFICER),'-'), "+ //ADDED BY NUWAN DE SILVA 1-11-07
																		 "        C.CLIENT_CODE "+
																		 " FROM   "+m_schema_name+".AF_RE_PRO_REPOSSESSION A ,"+m_schema_name+".AF_CO_MAS_SEIZER  B ,"+
																		 "        "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
																		 " WHERE  A.SEIZER_CODE=B.SEIZER_CODE(+) AND A.FINANCE_NO=C.FINANCE_NO  AND REPOSSESSION_NO = '"+m_reposs_no+"' ");	
							
						
	      boolean flag = rs.next();
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
					out.println("<EFD>"     + rs.getString(13) + "</EFD>");
					out.println("<ACT>"     + rs.getString(14) + "</ACT>");
					out.println("<SEN>"     + rs.getString(15) + "</SEN>"); //ADDED BY NUWAN DE SILVA 13-06-07
					out.println("<INV>"     + rs.getString(16) + "</INV>"); //ADDED BY NUWAN DE SILVA 31-10-07
					out.println("<NAM>"     + rs.getString(17) + "</NAM>"); //ADDED BY NUWAN DE SILVA 31-10-07
					out.println("<RTY>"     + rs.getString(18) + "</RTY>"); //ADDED BY NUWAN DE SILVA 1-11-07
					out.println("<ROF>"     + rs.getString(19) + "</ROF>"); //ADDED BY NUWAN DE SILVA 1-11-07
					out.println("<RON>"     + rs.getString(20) + "</RON>"); //ADDED BY NUWAN DE SILVA 1-11-07
					out.println("<CCO>"     + rs.getString(21) + "</CCO>"); //ADDED BY NUWAN DE SILVA 1-11-07
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
	    else if(m_chksql.trim().equals("get_receipt")){
			       
							String m_receipt_no = req.getParameter("RECEIPT_NO");
			        /*
							rs = stmt.executeQuery(" SELECT REC_NO,TO_CHAR(EFF_VALDATE,'DD'),TO_CHAR(EFF_VALDATE,'MM'),"+
							                       "        TO_CHAR(EFF_VALDATE,'YYYY'),CLIENT_CODE,CURR_CODE,"+
																		 "     	  REC_AMOUNT,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_CURR, "+
																		 "        SETTLE_MODE, PAYER_BRANCH_CODE, PAYER_ACC_NO, CHEQUE_NO,"+
															       "        OTH_COMMENTS, BRANCH_CODE,ENTRY_TYPE, "+
															       "        ACC_NO, STATUS, ENT_USER, ENT_DATE, RECON_STATUS, RECON_DATE,"+
															       "        RECON_BY, EFF_VALDATE, REALISED_DATE,  "+
															       "        EXCHANGE_RATE_BANK,  EXCHANGE_GAIN_LOSS,"+
															       "        MOD_USER, MOD_DATE,TO_CHAR(CHEQUE_DATE,'DD'),TO_CHAR(CHEQUE_DATE,'MM'),TO_CHAR(CHEQUE_DATE,'YYYY') "+
															       " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
																		 " WHERE  REC_NO = '"+m_receipt_no+"' ");	
							
						*/
						
						//---DATE 			 :(2007-03-05)-----------------------------------------------------------------------------------------------------------------
						//---MODIFIED BY : DELANJALI-----------------------------------------------------------------------------------------------------------------
		
													rs = stmt.executeQuery(" SELECT REC_NO,TO_CHAR(EFF_VALDATE,'DD'),TO_CHAR(EFF_VALDATE,'MM'),"+
							                       "        TO_CHAR(EFF_VALDATE,'YYYY'),CLIENT_CODE,CURR_CODE,"+
																		 "     	  REC_AMOUNT,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_CURR, "+
																		 "        SETTLE_MODE, PAYER_BRANCH_CODE, PAYER_ACC_NO, CHEQUE_NO,"+
															       "        OTH_COMMENTS, BRANCH_CODE,ENTRY_TYPE, "+
															       "        ACC_NO, STATUS, ENT_USER, ENT_DATE, RECON_STATUS, RECON_DATE,"+
															       "        RECON_BY, EFF_VALDATE, REALISED_DATE,  "+
															       "        EXCHANGE_RATE_BANK,  EXCHANGE_GAIN_LOSS,"+
															       "        MOD_USER, MOD_DATE,TO_CHAR(CHEQUE_DATE,'DD'),TO_CHAR(CHEQUE_DATE,'MM'),TO_CHAR(CHEQUE_DATE,'YYYY'),nvl(to_char(TENDER_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),0),nvl(to_char(RETURN_AMOUNT,'9,999,999,999,999,999,999,999,999.99'),0), "+
																		 " 				NVL(RENTAL_OTER_INVOICE,0),NVL(INSURANCE,0),NVL(LUXURY_TAX,0),NVL(REVANUE_LICENCE,0),NVL(RMV_CHARGES,0), "+	
																		 "        NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
																		 "        NVL((THIRD_PARTY_NAME),'-') ,"+	 //added by nuwan de silva on 01-08-07
																		 "        NVL((THIRD_PARTY_ADDRESS),'-') "+ //added by nuwan de silva on 01-08-07	
															       " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
																		 " WHERE  REC_NO = '"+m_receipt_no+"' ");	
							
	      boolean flag = rs.next();
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
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			/*else if(m_chksql.trim().equals("get_Finance_no")){
			       
							String m_finance_no = req.getParameter("finance_no");
			        
							rs = stmt.executeQuery(" SELECT A.APPLICATION_NO, A.CLIENT_CODE, A.INQUARY_NO, "+
																		 "        A.FINANCE_NO, A.INSURANCE_DATE, A.REVENUE_LICENSE_DATE, "+
																		 "        A.LUXURY_TAX_DATE, A.DRIVING_LICENSE_DATE, A.DISTRICT_CODE, "+
																		 "        A.APPLICATION_STATUS, A.CO_APPLICANT, A.FACILITY_NO "+
																		 " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																		 " WHERE  FINANCE_NO = '"+m_finance_no+"' ");	
							
						
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<APN>"     + rs.getString(1)  + "</APN>");
					out.println("<CLC>"     + rs.getString(2)  + "</CLC>");
					out.println("<INQ>"     + rs.getString(3)  + "</INQ>");
					out.println("<FIN>"     + rs.getString(4)  + "</FIN>");
					out.println("<IND>"     + rs.getString(5)  + "</IND>");
					out.println("<RLD>"     + rs.getString(6)  + "</RLD>");
					out.println("<LTD>"     + rs.getString(7)  + "</LTD>");
					out.println("<DLD>"     + rs.getString(8)  + "</DLD>");
					out.println("<DIC>"     + rs.getString(9)  + "</DIC>");
					out.println("<APS>"     + rs.getString(10) + "</APS>");
					out.println("<CAP>"     + rs.getString(11) + "</CAP>");
					out.println("<FAN>"     + rs.getString(12) + "</FAN>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }*/
			else if(m_chksql.trim().equals("get_Finance_no")){
			       
							String m_finance_no = req.getParameter("finance_no");
			        
							rs = stmt.executeQuery(" SELECT A.APPLICATION_NO, A.CLIENT_CODE, A.INQUARY_NO, "+
																		 "        A.FINANCE_NO, '','', "+ //A.INSURANCE_DATE, A.REVENUE_LICENSE_DATE, "+
																		 "        '', '', '', "+//"        A.LUXURY_TAX_DATE, A.DRIVING_LICENSE_DATE, A.DISTRICT_CODE,
																		 "        A.APPLICATION_STATUS, A.CO_APPLICANT, A.FACILITY_NO "+
																		 " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																		 " WHERE  FINANCE_NO = '"+m_finance_no+"' ");	
							
						
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<APN>"     + rs.getString(1)  + "</APN>");
					out.println("<CLC>"     + rs.getString(2)  + "</CLC>");
					out.println("<INQ>"     + rs.getString(3)  + "</INQ>");
					out.println("<FIN>"     + rs.getString(4)  + "</FIN>");
					out.println("<IND>"     + rs.getString(5)  + "</IND>");
					out.println("<RLD>"     + rs.getString(6)  + "</RLD>");
					out.println("<LTD>"     + rs.getString(7)  + "</LTD>");
					out.println("<DLD>"     + rs.getString(8)  + "</DLD>");
					out.println("<DIC>"     + rs.getString(9)  + "</DIC>");
					out.println("<APS>"     + rs.getString(10) + "</APS>");
					out.println("<CAP>"     + rs.getString(11) + "</CAP>");
					out.println("<FAN>"     + rs.getString(12) + "</FAN>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			
			//Nuwan De Silva 17-04-2007-----------------------------------------------------------------------------
			
				else if(m_chksql.trim().equals("get_Finance_no_Repossession")){
			       
							String m_finance_no = req.getParameter("finance_no");
			        String m_status = req.getParameter("ac_status");
							
							rs = stmt.executeQuery(" SELECT A.APPLICATION_NO, A.CLIENT_CODE, A.INQUARY_NO, "+
																		 "        A.FINANCE_NO, '','', "+ //A.INSURANCE_DATE, A.REVENUE_LICENSE_DATE, "+
																		 "        '', '', '', "+//"        A.LUXURY_TAX_DATE, A.DRIVING_LICENSE_DATE, A.DISTRICT_CODE,
																		 "        A.APPLICATION_STATUS, A.CO_APPLICANT, A.FACILITY_NO "+
																		 " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																		 " WHERE  FINANCE_NO = '"+m_finance_no+"' "); //AND APPLICATION_STATUS='"+m_status+"' ");	
							
						
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<APN>"     + rs.getString(1)  + "</APN>");
					out.println("<CLC>"     + rs.getString(2)  + "</CLC>");
					out.println("<INQ>"     + rs.getString(3)  + "</INQ>");
					out.println("<FIN>"     + rs.getString(4)  + "</FIN>");
					out.println("<IND>"     + rs.getString(5)  + "</IND>");
					out.println("<RLD>"     + rs.getString(6)  + "</RLD>");
					out.println("<LTD>"     + rs.getString(7)  + "</LTD>");
					out.println("<DLD>"     + rs.getString(8)  + "</DLD>");
					out.println("<DIC>"     + rs.getString(9)  + "</DIC>");
					out.println("<APS>"     + rs.getString(10) + "</APS>");
					out.println("<CAP>"     + rs.getString(11) + "</CAP>");
					out.println("<FAN>"     + rs.getString(12) + "</FAN>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			
		//-------------------------------------------------------------------------------------------------------------------
			
      else if(m_chksql.trim().equals("get_client_code")){
			       
							String m_client_code = req.getParameter("client_code");
			    
							rs = stmt.executeQuery(" SELECT CLIENT_CODE, FULL_NAME, ACTIVE_STATUS, TEMP_ACTIVE_STATUS, "+
							                       " NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'-') CITY_NAME "+ //added by nuwan de silva 25-07-07
														         " FROM   "+m_schema_name+".AF_CO_MAS_CLIENT "+
														         " WHERE  CLIENT_CODE = UPPER('"+m_client_code+"')");

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<CLIENT>"  + rs.getString(1)  + "</CLIENT>");
					out.println("<FNAME>"   + rs.getString(2)  + "</FNAME>");
					out.println("<ACTIVE>"  + rs.getString(3)  + "</ACTIVE>");
					out.println("<T_ACT>"   + rs.getString(4)  + "</T_ACT>");
					out.println("<C_ADD1>"  + rs.getString(5)  + "</C_ADD1>"); //added by nuwan de silva 25-07-07
					out.println("<C_ADD2>"  + rs.getString(6)  + "</C_ADD2>"); //added by nuwan de silva 25-07-07
					out.println("<C_CITY>"  + rs.getString(7)  + "</C_CITY>"); //added by nuwan de silva 27-07-07
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			else if(m_chksql.trim().equals("get_SeizerCode")){
			       
							String m_seizer_code = req.getParameter("seizer_code");
			        
							rs = stmt.executeQuery(" SELECT A.SEIZER_CODE, A.FIRST_NAME, A.LAST_NAME,"+ 
							                       "        A.ADDRESS1, A.ADDRESS2,A.MOBILE_NO, "+
																		 "        A.TEL_NO, A.ACTIVE_STATUS,A.CITY_CODE, "+
																		 "        NVL(A.FEE_PER_CASE,'0'),A.MONTHLY_FEE, A.DEFAULT_VALUE, "+
																		 "        NVL(VALIDITY_PERIOD,0)	"+ //nuwan de silva 19-11-2008
																		 " FROM   "+m_schema_name+".AF_CO_MAS_SEIZER A "+
																		 " WHERE  SEIZER_CODE	='"+m_seizer_code+"' AND ACTIVE_STATUS='Y' ");
																									
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
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
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			
			
			//----------ADDED BY CHANDANA ON 18/04/2007-------------------//
			else if(m_chksql.trim().equals("get_exist_repossion")){
			       
			String m_finance_no1 = req.getParameter("finance_no");
			
			rs = stmt.executeQuery(" SELECT REPOSSESSION_NO, FINANCE_NO "+
			                       " FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
														 " WHERE  ACTIVE_STATUS ='Y' AND FINANCE_NO='"+m_finance_no1+"' ");
			
			boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<REP>"     + rs.getString(1)  + "</REP>");
					out.println("<FIN>"     + rs.getString(2)  + "</FIN>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
					
			}
			//---------------- END - CHANDANA ON 18/04/2007 ----------------//
			
			
	    else if(m_chksql.trim().equals("get_excharate")){
			       
							String m_curren_code = req.getParameter("CURR_CODE");
							String m_value_date  = req.getParameter("VAL_DATE");
			        
							rs = stmt.executeQuery(" SELECT EXCHANGE_RATE,CURR_CODE, TRN_DATE "+
							                       " FROM   "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
																		 " WHERE  CURR_CODE=UPPER('"+m_curren_code+"') AND "+
																		 "        (TRN_DATE,CURR_CODE) IN (SELECT MAX(TRN_DATE),CURR_CODE "+
                                     "                  FROM    "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE "+
																		 "                  WHERE   TRN_DATE<=TO_DATE('"+m_value_date+"','DD-MM-YYYY') "+
																		 "                  GROUP BY CURR_CODE)");
																									
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<EXR>"     + rs.getString(1)  + "</EXR>");
					out.println("<CUR>"     + rs.getString(2)  + "</CUR>");
					out.println("<TRD>"     + rs.getString(3)  + "</TRD>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			else if(m_chksql.trim().equals("get_sysdate")){
          //out.println (" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') "+
                                //     " FROM   DUAL");
							rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') "+
                                     " FROM   DUAL");
																									
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<SYSD>"     + rs.getString(1)  + "</SYSD>");
					out.println("<SYSM>"     + rs.getString(2)  + "</SYSM>");
					out.println("<SYSY>"     + rs.getString(3)  + "</SYSY>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			else if(m_chksql.trim().equals("cal_exc_rate")){
     	        
					String m_curren_code = req.getParameter("CURR_CODE");
					String m_amount      = req.getParameter("AMOUNT");
			    String m_rep_amount  = req.getParameter("REP_AMOUNT");
					String m_exc_rate    = req.getParameter("EXC_RATE");
					   
			       rs = stmt.executeQuery ("SELECT REP_CURR "+
				                             "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																     "WHERE  CURR_CODE = '"+m_curren_code+"' ");
				     
          		boolean flag = rs.next();
			
              if(flag){
							 if(rs.getString(1).equals("Y")){
							   rs = stmt.executeQuery(" SELECT '"+m_amount+"','"+m_exc_rate+"' "+
							                          " FROM   DUAL");
								}else{
								 rs = stmt.executeQuery(" SELECT '"+m_rep_amount+"','"+m_rep_amount+"'/'"+m_amount+"' "+
							                          " FROM   DUAL");
								}
							}
																									
	       flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<REPA>"     + nf.format(rs.getDouble(1))  + "</REPA>");
					out.println("<EXCR>"     + nf.format(rs.getDouble(2))  + "</EXCR>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			else if(m_chksql.trim().equals("cal_rep_amount")){
     		String m_amount      = req.getParameter("AMOUNT");
			  String m_exc_rate    = req.getParameter("EXC_RATE");
					
			  rs = stmt.executeQuery(" SELECT '"+m_amount+"'*'"+m_exc_rate+"' "+
                               " FROM   DUAL");
																									
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<REPA>"     + nf.format(rs.getDouble(1))  + "</REPA>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
			
			//added by nuwan de silva on 01-11-2007-----------------------------------------
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_RE_User_validate")){
			String m_val = req.getParameter("user_id").trim();
												
			rs= stmt.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE, USER_TYPE, EMP_ID,"+
      " DIVISION_CODE, DESIGNATION_CODE,PASSWORD FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE UPPER(USER_ID)=UPPER('"+m_val+"')   ");
						
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
		//end the user validate nuwan de silva on 01-11-2007-------------------------------	
			/*
			else if(m_chksql.trim().equals("get_inv_gen_date")){
			       
							rs = stmt.executeQuery(" SELECT TO_CHAR(MAX(A.TO_DATE),'DD'), "+
							                       "        TO_CHAR(MAX(A.TO_DATE),'MM'), "+
																		 "        TO_CHAR(MAX(A.TO_DATE),'YYYY'),"+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'DD'), "+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'MM'), "+
																		 "        TO_CHAR(ADD_MONTHS(MAX(A.TO_DATE),1),'YYYY') "+
																		 "  FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DATE A");

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FDD>"     + rs.getString(1)  + "</FDD>");
					out.println("<FMM>"     + rs.getString(2)  + "</FMM>");
					out.println("<FYY>"     + rs.getString(3)  + "</FYY>");
					out.println("<TDD>"     + rs.getString(4)  + "</TDD>");
					out.println("<TMM>"     + rs.getString(5)  + "</TMM>");
					out.println("<TYY>"     + rs.getString(6)  + "</TYY>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
				else if(m_chksql.trim().equals("get_date_dif")){
			       
							String m_from_date = req.getParameter("from_date");
			        String m_to_date   = req.getParameter("to_date");
			
							rs = stmt.executeQuery(" SELECT TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYY'),'DD'), "+
							                       "        TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYY'),'MM'), "+
																		 "        TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYY'),'YYYY'),"+
																		 "        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYY'),'DD'), "+
																		 "        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYY'),'MM'), "+
																		 "        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYY'),'YYYY') "+
																		 "  FROM  "+m_schema_name+".DUAL "+
																		 " WHERE  TO_DATE('"+m_from_date+"','DD-MM-YYYY')<=	TO_DATE('"+m_to_date+"','DD-MM-YYYY')");

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FDD>"     + rs.getString(1)  + "</FDD>");
					out.println("<FMM>"     + rs.getString(2)  + "</FMM>");
					out.println("<FYY>"     + rs.getString(3)  + "</FYY>");
					out.println("<TDD>"     + rs.getString(4)  + "</TDD>");
					out.println("<TMM>"     + rs.getString(5)  + "</TMM>");
					out.println("<TYY>"     + rs.getString(6)  + "</TYY>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }
	    else if(m_chksql.trim().equals("get_invoice_det")){
			       
							String m_invoice_no = req.getParameter("invoice_no");
			        
							rs = stmt.executeQuery(" SELECT A.INVOICE_NO, A.FINANCE_NO, TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+
							                       "        A.NET_AMOUNT,A.VAT_AMOUNT, A.TOTAL_AMOUNT, "+
																		 "        TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), A.SETTELE_AMOUNT,A.BALANCE_TO_BE_RECEIVED, "+
																		 "        A.CLIENT_CODE, A.REMARKS,A.ACTIVE_STATUS, "+
																		 "        A.CURRENCY_CODE, A.EXCHANGE_RATE, A.TOTAL_AMOUNT_CURR, "+
																		 "        A.SETTEL_AMOUNT_CURR, A.BALANCE_TO_BE_RECEIVED_CURR "+
																		 "  FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																		 " WHERE  ACTIVE_STATUS='Y' AND INVOICE_NO='"+m_invoice_no+"'  ");

	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<INN>"     + rs.getString(1)  + "</INN>");
					out.println("<FIN>"     + rs.getString(2)  + "</FIN>");
					out.println("<VAD>"     + rs.getString(3)  + "</VAD>");
					out.println("<NTA>"     + rs.getString(4)  + "</NTA>");
					out.println("<VTA>"     + rs.getString(5)  + "</VTA>");
					out.println("<TTA>"     + rs.getString(6)  + "</TTA>");
					out.println("<DUD>"     + rs.getString(7)  + "</DUD>");
					out.println("<SEA>"     + rs.getString(8)  + "</SEA>");
					out.println("<BTR>"     + rs.getString(9)  + "</BTR>");
					out.println("<CLI>"     + rs.getString(10) + "</CLI>");
					out.println("<REM>"     + rs.getString(11) + "</REM>");
					out.println("<ACT>"     + rs.getString(12) + "</ACT>");
					out.println("<CUR>"     + rs.getString(13) + "</CUR>");
					out.println("<EXR>"     + rs.getString(14) + "</EXR>");
					out.println("<TAC>"     + rs.getString(15) + "</TAC>");
					out.println("<SAC>"     + rs.getString(16) + "</SAC>");
					out.println("<BRC>"     + rs.getString(17) + "</BRC>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }

			*/		
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
