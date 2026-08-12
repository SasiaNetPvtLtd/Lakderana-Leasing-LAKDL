import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
import java.math.BigDecimal;



public class LAKDL_AF_CMS_Sql_Data extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs1,rs3;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
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
			else if(m_chksql.trim().equals("LOAD_CONTRACT_DATA")){
				String m_client=req.getParameter("client");
				
				try{
					
					rs = stmt.executeQuery (
						//out.println(
						" SELECT "+
						//" ROWNUM, "+ 
						" '', "+
						" FINANCE_NO, "+
						" SUM(INV_AMOUNT), "+
						" SUM(INV_BALANCE_AMOUNT), "+
						" SUM(ODI_AMOUNT), "+
						" SUM(ODI_BAL_AMOUNT), "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
						" FROM "+
						" ((SELECT FINANCE_NO, "+
						" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
						" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
						" 0 ODI_AMOUNT, "+
						" 0 ODI_BAL_AMOUNT  "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						" BALANCE_TO_BE_RECEIVED>0 AND "+
						" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
						" A.ACTIVE_STATUS = 'Y'  "+
						" AND A.INVOICE_TYPE IN ('INV_GENER','ODI')  "+ //ADDED BY NUWAN DE SILVA
						" GROUP BY FINANCE_NO ) "+
						
						" UNION ALL "+
						" (SELECT a.FIN_NO FINANCE_NO, "+
						" 0 INV_AMOUNT, "+
						" 0 INV_BALANCE_AMOUNT, "+
						" SUM(ODI_CAL_AMOUNT) ODI_AMOUNT, "+
						" SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE  CLIENT_CODE='"+m_client+"' AND "+
						" A.INVOICE_NO=B.INVOICE_NO AND "+
						" B.INVOICE_TYPE IN ('INV_GENER','ODI') AND "+
						" ODI_BAL_AMOUNT>0 "+
						" GROUP BY a.FIN_NO )) "+
						" GROUP BY  FINANCE_NO ");   
					
					
					
					
					
					out.println("<?xml version='1.0' encoding='utf-8' ?>");
					out.println("<rows>"); 
					
					while(rs.next()){
						
						/*out.print("<row id='"+ rs.getString(1) +"'>"); 
						
						out.print("<cell><![CDATA["+ rs.getString(14)+"]]></cell>"); 
						out.print("<cell><![CDATA["+ rs.getString(2)+"]]></cell>"); 
						out.print("<cell><![CDATA["+ rs.getString(3)+"]]></cell>"); 
						out.print("<cell><![CDATA["+ rs.getString(4)+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(5)+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(6)+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(7)+"]]></cell>");
						BigDecimal amount = new BigDecimal("0.00");
						amount = amount.add(rs.getBigDecimal(8));	
						out.print("<cell><![CDATA["+ nf.format(amount)+"]]></cell>");
						out.print("<cell><![CDATA["+rs.getString(9)+"/"+ rs.getString(10)+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(11)+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(12)+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(12)+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(13)+"]]></cell>");
						out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(6)+"]]></cell>");
						out.print("</row>");*/
						
						out.print("<row id='"+ rs.getString(1) +"'>"); 
						out.print("<cell><![CDATA["+ rs.getString(2)+"]]></cell>"); 
						out.print("<cell><![CDATA["+ rs.getString(7)+"]]></cell>"); 
						
						BigDecimal amount1 = new BigDecimal("0.00");
						amount1 = amount1.add(rs.getBigDecimal(3));	
						out.print("<cell><![CDATA["+ nf.format(amount1)+"]]></cell>");
						
						BigDecimal amount2 = new BigDecimal("0.00");
						amount2 = amount2.add(rs.getBigDecimal(4));	
						out.print("<cell><![CDATA["+ nf.format(amount2)+"]]></cell>");
						
						BigDecimal amount3 = new BigDecimal("0.00");
						amount3 = amount3.add(rs.getBigDecimal(5));	
						out.print("<cell><![CDATA["+ nf.format(amount3)+"]]></cell>");
						
						BigDecimal amount4 = new BigDecimal("0.00");
						amount4 = amount4.add(rs.getBigDecimal(6));	
						out.print("<cell><![CDATA["+ nf.format(amount4)+"]]></cell>");
						out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(2)+"]]></cell>");
						/*out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA[N]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString(6)+"]]></cell>");
						*/
						out.print("</row>");
						
						
					} 
					
					
					
					out.print("</rows>");
					
				}catch(Exception e){
					out.println(e.toString());
				}
				
				
			}
			
			
			
			else if(m_chksql.trim().equals("LOAD_INVOICE_ALLOCATION")){
				String m_finance_no=req.getParameter("finance_no");
				String m_id=req.getParameter("id");
				String m_rental=req.getParameter("rentel");
				String m_other=req.getParameter("other");
				
				BigDecimal m_big_rental=new BigDecimal(m_rental);
				BigDecimal m_big_other=new BigDecimal(m_other);
				
				try{
					
					
					
					rs = stmt.executeQuery(	
						//out.println(
						" SELECT ROWNUM,INVOICE_TYPE,INVOICE_NO,  "+
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT,VALUE_DATE,DUE_DATE,ORDER_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE_FMATTER,TO_CHAR(DUE_DATE,'DD-MM-YYYY') DUE_DATE_FMATTER "+ 
						" FROM (  "+
						" SELECT INVOICE_TYPE,INVOICE_NO,  "+
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT,VALUE_DATE , DUE_DATE ,ORDER_NO "+
						
						" FROM "+  
						" ( "+
						
						" SELECT DECODE(INVOICE_TYPE,'INV_GENER','RENTAL','INV_OTHER',(SELECT REMARKS FROM AF_CO_PRO_INVOICE WHERE INVOICE_NO=A.INVOICE_NO)"+
						" ,'OTHER') INVOICE_TYPE, INVOICE_NO, "+//Modified by Jithendra 30-09-2016
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT,VALUE_DATE,DUE_DATE,DECODE(INVOICE_TYPE,'INV_GENER','3','2') ORDER_NO "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,  "+
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B  "+
						" WHERE    "+
						" BALANCE_TO_BE_RECEIVED>0 AND   "+
						" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND  "+
						" A.ACTIVE_STATUS = 'Y'  AND  "+
						" FINANCE_NO = '"+m_finance_no+"'  "+
						
						" UNION ALL  "+
						
						" SELECT 'ODI' INVOICE_TYPE, ODI_REF_NO,  "+
						" ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT,ODI_DATE VALUE_DATE ,ODI_DATE DUE_DATE,'1' ORDER_NO "+
						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,  "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B  "+
						" WHERE   A.INVOICE_NO=B.INVOICE_NO AND  "+
						" A.FIN_NO = '"+m_finance_no+"' AND  "+
						" ODI_BAL_AMOUNT>0  "+
						" ) "+
						
						" ) "+
						" ORDER BY ORDER_NO,VALUE_DATE  "+
						"");
					
					
					
					
					
					out.println("<?xml version='1.0' encoding='utf-8' ?>");
					out.println("<rows>"); 
					int i=1;
					while(rs.next()){
						
						
						
						out.print("<row id='"+ i +"'>"); 
						out.print("<cell><![CDATA["+m_id.trim()+"]]></cell>");
						out.print("<cell><![CDATA["+ rs.getString("INVOICE_NO")+"]]></cell>"); 
						out.print("<cell><![CDATA["+ rs.getString("INVOICE_TYPE")+"]]></cell>"); 
						out.print("<cell><![CDATA["+ rs.getString("VALUE_DATE_FMATTER")+"]]></cell>"); 
						out.print("<cell><![CDATA["+ rs.getString("DUE_DATE_FMATTER")+"]]></cell>"); 
						
						BigDecimal amount1 = new BigDecimal("0.00");
						amount1 = amount1.add(rs.getBigDecimal("TOTAL_AMOUNT"));	
						out.print("<cell><![CDATA["+ nf.format(amount1)+"]]></cell>");
						
						BigDecimal amount2 = new BigDecimal("0.00");
						amount2 = amount2.add(rs.getBigDecimal("BALANCE_TO_BE_RECEIVED"));	
						out.print("<cell><![CDATA["+ nf.format(amount2)+"]]></cell>");
						
						String check="NO";
						BigDecimal amount3 = new BigDecimal("0.00");
						BigDecimal amount4 = new BigDecimal("0.00");
						if(rs.getString("INVOICE_TYPE").equals("OTHER")){
							int res1=amount2.compareTo(m_big_other);
							//out.println("DDDDDD"+m_big_other);
							if(res1 >= 0 && m_big_other.compareTo(new BigDecimal("0.00")) > 0  ){
								amount3=m_big_other;
								m_big_other=m_big_other=new BigDecimal("0.00");
								//out.println("AAAAAAA"+m_big_other);
							}else if(res1 < 0 && m_big_other.compareTo(new BigDecimal("0.00")) > 0){
								amount3=amount2;
								m_big_other=m_big_other.subtract(amount2);
								//out.println("SSSSSS"+m_big_other);
							}else {
								amount3=new BigDecimal("0.00");
							}
							
							if(amount3.compareTo(new BigDecimal("0.00")) > 0){
								check="YES";
							}else{
								check="NO";
							}
							
						}else {
							int res2=amount2.compareTo(m_big_rental);
							//out.println("QQQQQQ"+m_big_rental);
							if(res2 >= 0 && m_big_rental.compareTo(new BigDecimal("0.00")) > 0 ){
								amount3=m_big_rental;
								m_big_rental=m_big_rental=new BigDecimal("0.00");
								//out.println("WWWWWWW"+m_big_rental);
							}else if(res2 < 0 && m_big_rental.compareTo(new BigDecimal("0.00")) > 0){
								amount3=amount2;
								m_big_rental=m_big_rental.subtract(amount2);
								//out.println("EEEEEE"+m_big_rental);
							}else {
								amount3=new BigDecimal("0.00");
							}
							
							
							if(amount3.compareTo(new BigDecimal("0.00")) > 0){
								check="YES";
							}else{
								check="NO";
							}
							
						}
						amount4 = amount4.add(amount3);	
						out.print("<cell><![CDATA["+ nf.format(amount3)+"]]></cell>");
						out.print("<cell><![CDATA["+ check +"]]></cell>");
						out.print("<cell><![CDATA["+ nf.format(amount4)+"]]></cell>");
						out.print("</row>");
						
						i++;
						
					} 
					
					
					
					out.print("</rows>");
					
				}catch(Exception e){
					out.println(e.toString());
				}
				
				
			}	
			
			///--- Added by DSP Chathuranga 2013-04-22 ---///
			
			else if(m_chksql.trim().equals("LOAD_CONTRACT_DETAILS")){
				String m_client=req.getParameter("client");
				
				try{
					
					/*
					
					rs = stmt.executeQuery (
						//out.println(
						" SELECT "+
						//" ROWNUM, "+ 
						" '', "+
						" FINANCE_NO, "+
						" SUM(INV_AMOUNT), "+
						" SUM(INV_BALANCE_AMOUNT), "+
						" SUM(ODI_AMOUNT), "+
						" SUM(ODI_BAL_AMOUNT), "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
						" FROM "+
						" ((SELECT FINANCE_NO, "+
						" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
						" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
						" 0 ODI_AMOUNT, "+
						" 0 ODI_BAL_AMOUNT  "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						" BALANCE_TO_BE_RECEIVED>0 AND "+
						" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
						" A.ACTIVE_STATUS = 'Y'  "+
						" AND A.INVOICE_TYPE IN ('INV_GENER','ODI')  "+ //ADDED BY NUWAN DE SILVA
						" GROUP BY FINANCE_NO ) "+
						
						" UNION ALL "+
						
						" (SELECT a.FIN_NO FINANCE_NO, "+
						" 0 INV_AMOUNT, "+
						" 0 INV_BALANCE_AMOUNT, "+
						" SUM(ODI_CAL_AMOUNT) ODI_AMOUNT, "+
						" SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE  CLIENT_CODE='"+m_client+"' AND "+
						" A.INVOICE_NO=B.INVOICE_NO AND "+
						" B.INVOICE_TYPE IN ('INV_GENER','ODI') AND "+
						" ODI_BAL_AMOUNT>0 "+
						" GROUP BY a.FIN_NO ) "+
						
						" UNION  "+
						
						" (SELECT a.FINANCE_NO,  "+
						" 0 INV_AMOUNT, "+
						" 0 INV_BALANCE_AMOUNT, "+
						" 0 ODI_AMOUNT, "+
						" 0 ODI_BAL_AMOUNT "+
						" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  A "+
						" WHERE  CLIENT_CODE='"+m_client+"' "+
						" AND APPLICATION_STATUS NOT IN('NORM_TERMI','TERMI','WRITE_OFF','LG_SETTLED','RP_SOLD','RP_SETTLED') "+//Added by Jithendra 31-05-2017 prevent Loading terminated contracts
						" GROUP BY a.FINANCE_NO ) "+
						
						" ) "+
						" GROUP BY  FINANCE_NO ");
				
				       */
					
					
					rs = stmt.executeQuery (
						
						"  SELECT "+
						" FINANCE_NO, "+
						" SUM(RENTAL_BALANCE), "+
						" SUM(INSURANCE_BALANCE), "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-')  REGISTRATION_NO, "+
						//" NVL("+m_schema_name+".AF_CO_GET_TOT_NO_RENTALS(FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0) TOTAL_NO_RENTAL, "+
						" 0 TOTAL_NO_RENTAL, "+
						//" NVL("+m_schema_name+".AF_CO_GET_INSTALMENT_AMT("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)),0) RENTAL_AMT, "+
						" 0 RENTAL_AMT, "+
						//" NVL("+m_schema_name+".AF_CO_GET_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)),'-') RENTAL_DATE, "+ 
						" '_' RENTAL_DATE, "+ 
						" DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO))) APPLICATION_STATUS, "+ 
						//" '_' APPLICATION_STATUS, "+ 
						" DECODE(NVL("+m_schema_name+".AF_GET_PERFORM_STATUS(FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),'PERFORM'),'PERFORM','Perform','NPERFORM','Non Perform',NVL("+m_schema_name+".AF_GET_PERFORM_STATUS(FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),'PERFORM')) PERFORM_STATUS"+ 
						//" '_' PERFORM_STATUS"+ 
						
						" ,"+m_schema_name+".AF_CO_GET_CMS_PAY_CAN(FINANCE_NO) CMS_PAY_CAN "+
						" ,"+m_schema_name+".AF_CO_GET_FIN_TRAN_TYPE(FINANCE_NO) TRAN_TYPE   "+
						" , SUM(RENTAL_BALANCE_DUE) "+ // line added by udara 14-12-2018
						" , SUM(LOAN_EXCESS)  "+ // added by udara 07-01-2019
						" FROM (   "+
						
						// block added by udara 14-12-2018
						/*
						" SELECT     "+
						" 		  FINANCE_NO,   "+
						" 		  SUM(BALANCE_TO_BE_RECEIVED)   RENTAL_BALANCE,  "+
						"           0 INSURANCE_BALANCE, "+
						"           SUM(BALANCE_TO_BE_RECEIVED) RENTAL_BALANCE_DUE "+
						" 		  FROM     "+m_schema_name+".AF_CO_PRO_INVOICE   "+
						" 		  WHERE    CLIENT_CODE='"+m_client+"'  "+
						" 		  AND      ACTIVE_STATUS = 'Y'  "+
						" 			  AND 		( INVOICE_TYPE <> 'INSURANCE'   "+
						"               AND REMARKS <> 'CHARGES - INSURANCE' )  "+
						" 		  AND      BALANCE_TO_BE_RECEIVED>0   "+
						"         AND DUE_DATE <= SYSDATE  "+ 
						"    		  GROUP BY FINANCE_NO   "+ 
						
						" UNION ALL "+
						
						" SELECT a.FIN_NO FINANCE_NO,  "+
						"	 SUM(ODI_BAL_AMOUNT) RENTAL_BALANCE , "+
						"       0 INSURANCE_BALANCE,  "+
						"           SUM(BALANCE_TO_BE_RECEIVED) RENTAL_BALANCE_DUE "+
						"			 FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,  "+
						"			 AF_CO_PRO_INVOICE B  "+
						"			 WHERE  CLIENT_CODE='"+m_client+"' AND  "+
						"			 A.INVOICE_NO=B.INVOICE_NO AND  "+
						"			 ODI_BAL_AMOUNT>0  "+
						"            AND B.DUE_DATE <= SYSDATE "+
						"			 GROUP BY a.FIN_NO "+
						
						" UNION ALL "+
						*/
						
						" SELECT "+   
									" A.FINANCE_NO, "+  
									//" SUM(A.BALANCE_TO_BE_RECEIVED)   RENTAL_BALANCE, "+ // commented by udara 31-12-2018
									" 0   RENTAL_BALANCE, "+ // added by udara 31-12-2018
									" 0 INSURANCE_BALANCE, "+
									" SUM(A.BALANCE_TO_BE_RECEIVED) RENTAL_BALANCE_DUE, "+
									" 0 LOAN_EXCESS  "+ // added by udara 07-01-2019
											" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
											" WHERE A.FINANCE_NO = B.FINANCE_NO "+  
						          			" AND A.CLIENT_CODE='"+m_client+"' "+ 
											" AND A.ACTIVE_STATUS = 'Y' "+ 
											" AND ( A.INVOICE_TYPE <> 'INSURANCE'  "+ 
						                	" AND A.REMARKS <> 'CHARGES - INSURANCE' )  "+
											" AND   A.BALANCE_TO_BE_RECEIVED>0   "+
											//" AND A.VALUE_DATE <= SYSDATE "+ // commented by udara 07-01-2018 // commented by udara 25-10-2019
											//" AND A.VALUE_DATE <= LAST_DAY(SYSDATE) "+ // added by udara 07-01-2019
						          			//" AND B.TRANSACTION_TYPE = 'LOANS' "+ // commented by udara 03-12-2024
												" AND B.TRANSACTION_TYPE IN ('LOANS','HADAGASMA') "+  // added by udara 03-12-2024
											" GROUP BY A.FINANCE_NO "+    
												
						" UNION ALL "+
												
						" SELECT "+
						      " A.FIN_NO FINANCE_NO, "+ 
						      //" SUM(A.ODI_BAL_AMOUNT) RENTAL_BALANCE , "+ // commente4d by udara 31-12-2018
								" 0 RENTAL_BALANCE , "+ // added by udara 31-12-2019
									" 0 INSURANCE_BALANCE,  "+
									" SUM(B.BALANCE_TO_BE_RECEIVED) RENTAL_BALANCE_DUE, "+
									" 0 LOAN_EXCESS  "+ // added by udara 07-01-2019
											" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+ 
											" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
						          			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
											" WHERE  B.CLIENT_CODE='"+m_client+"' "+   
											" AND A.INVOICE_NO=B.INVOICE_NO "+ 
						          			" AND B.FINANCE_NO = C.FINANCE_NO "+
											" AND A.ODI_BAL_AMOUNT>0  "+
											//" AND B.VALUE_DATE <= SYSDATE "+ // commented by udara 07-01-2018 // commented by udara 25-10-2019
											//" AND B.VALUE_DATE <= LAST_DAY(SYSDATE) "+ // added by udara 07-01-2019
						          			//" AND C.TRANSACTION_TYPE = 'LOANS' "+ // commented by udara 03-12-2024
												" AND C.TRANSACTION_TYPE IN ('LOANS','HADAGASMA') "+ // added by udara 03-12-2024
											" GROUP BY A.FIN_NO "+
						
						" UNION ALL "+
						
						// added by udara 07-01-2019
						" SELECT "+
						 " A.FINANCE_NO, "+
						 " 0 INV_AMOUNT, "+
						 " 0 INSURANCE, "+
						 " 0 RENTAL_BALANCE_DUE,  "+
						 " SUM(A.BAL_TOBE_RECEIVE) LOAN_EXCESS "+
					            " FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					            " WHERE A.FINANCE_NO = B.FINANCE_NO "+
						        " AND B.CLIENT_CODE = '"+m_client+"' "+ 
					            //" AND B.TRANSACTION_TYPE = 'LOANS' "+ // commented by udara 03-12-2024
								" AND B.TRANSACTION_TYPE IN  ('LOANS','HADAGASMA') "+ // added by udara 03-12-2024
					            " GROUP BY A.FINANCE_NO "+
								
						" UNION ALL "+
						// end by udara 07-01-2019		
						
						// block end by udara 14-12-2018
						
						" SELECT     "+
						" 		  FINANCE_NO,   "+
						" 		  SUM(BALANCE_TO_BE_RECEIVED)   RENTAL_BALANCE,  "+
						"           0 INSURANCE_BALANCE, "+
						"           0 RENTAL_BALANCE_DUE, "+ // line added by udara 14-12-2018
						"           0 LOAN_EXCESS  "+ // added by udara 07-01-2019
						" 		  FROM     "+m_schema_name+".AF_CO_PRO_INVOICE   "+
						" 		  WHERE    CLIENT_CODE='"+m_client+"'  "+
						" 		  AND      ACTIVE_STATUS = 'Y'  "+
						" 			  AND 		( INVOICE_TYPE <> 'INSURANCE'   "+
						"               AND REMARKS <> 'CHARGES - INSURANCE' )  "+
						"         AND INVOICE_TYPE <> 'MORATO-INT' "+ // added by udara 22-01-2021
						" 		  AND      BALANCE_TO_BE_RECEIVED>0   "+
						"    		  GROUP BY FINANCE_NO   "+ 
						
						// added by udara 22-01-2021
						
						" UNION ALL "+
						
						" SELECT     "+
						" 		  FINANCE_NO,   "+
						" 		  SUM(BALANCE_TO_BE_RECEIVED)   RENTAL_BALANCE,  "+
						"           0 INSURANCE_BALANCE, "+
						"           0 RENTAL_BALANCE_DUE, "+ 
						"           0 LOAN_EXCESS  "+ 
						" 		  FROM   "+m_schema_name+".AF_CO_PRO_INVOICE   "+
						" 		  WHERE  CLIENT_CODE='"+m_client+"'  "+
						" 		  AND    ACTIVE_STATUS = 'Y'  "+
						"         AND    INVOICE_TYPE = 'MORATO-INT' "+ 
						"         AND    VALUE_DATE <= SYSDATE "+
						" 		  AND    BALANCE_TO_BE_RECEIVED>0   "+
						"    	  GROUP BY FINANCE_NO   "+ 
						
						
						// end by udara 22-01-2021
						
						" UNION ALL "+
						
						" SELECT a.FIN_NO FINANCE_NO,  "+
						"	 SUM(ODI_BAL_AMOUNT) RENTAL_BALANCE , "+
						"       0 INSURANCE_BALANCE,  "+
						"           0 RENTAL_BALANCE_DUE, "+ // line added by udara 14-12-2018
						"           0 LOAN_EXCESS  "+ // added by udara 07-01-2019
						"			 FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,  "+
						"			 AF_CO_PRO_INVOICE B  "+
						"			 WHERE  CLIENT_CODE='"+m_client+"' AND  "+
						"			 A.INVOICE_NO=B.INVOICE_NO AND  "+
						"			 ODI_BAL_AMOUNT>0  "+
						"			 GROUP BY a.FIN_NO "+
						
						
						"  UNION ALL "+
						
						"  SELECT    "+
						"			  FINANCE_NO,  "+
						"  0 INV_BALANCE_AMOUNT, "+
						"			  SUM(BALANCE_TO_BE_RECEIVED)  INSURANCE_BALANCE, "+
						"           0 RENTAL_BALANCE_DUE, "+ // line added by udara 14-12-2018
						"             0 LOAN_EXCESS  "+ // added by udara 07-01-2019
						"			  FROM     "+m_schema_name+".AF_CO_PRO_INVOICE   "+
						"			  WHERE    CLIENT_CODE='"+m_client+"'  "+
						"			  AND      ACTIVE_STATUS = 'Y'  "+
						"			  AND 		( INVOICE_TYPE = 'INSURANCE'  "+
						"			  			OR REMARKS = 'CHARGES - INSURANCE' )  "+
						"			  AND      BALANCE_TO_BE_RECEIVED>0   "+
						//================ 2017-09-13 IGNORE FREE INSURENCE
						/*
						" AND INVOICE_NO NOT IN ("+
						" 		SELECT INVOICE_NO "+
						" 		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" 		WHERE  A.FINANCE_NO = FINANCE_NO "+
						" 		AND (SELECT INSUR_COM FROM AF_IS_PRO_ASET_INSUR_DETA  B "+
						" 			 WHERE FINANCE_NO=A.FINANCE_NO "+
						" 			 AND REF_DEBIT_NOTE_NO=A.INVOICE_NO) IN (SELECT PAYEE_CODE FROM AF_CR_PRO_INSURANCE_CREDIT_REF) "+
						" 		AND (SELECT INSURANCE_DONE_BY FROM AF_CO_PRO_APPLICATION_DETAILS WHERE FINANCE_NO=A.FINANCE_NO)='LICENSEE' "+
						" 	)"+
						*/
						
						
						" AND (SELECT COUNT(A.INSUR_COM) "+
			            " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CR_PRO_INSURANCE_CREDIT_REF C "+
			            " WHERE A.REF_DEBIT_NOTE_NO = INVOICE_NO "+
			            " AND B.CLIENT_CODE = '"+m_client+"' "+
			            " AND A.FINANCE_NO = B.FINANCE_NO  "+
			            " AND A.INSUR_COM  = C.PAYEE_CODE "+
			            " AND B.INSURANCE_DONE_BY = 'LICENSEE') = 0 "+
						
						//================ 2017-09-13 IGNORE FREE INSURENCE
						"			  GROUP BY FINANCE_NO   "+
						
						" UNION ALL "+
						
						" SELECT "+
						" FINANCE_NO, "+
						" 0 INV_AMOUNT, "+
						" 0 INSURANCE, "+
						" 0 RENTAL_BALANCE_DUE, "+ // line added by udara 14-12-2018
						" 0 LOAN_EXCESS  "+ // added by udara 07-01-2019
						" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  A  "+
						"			 WHERE  CLIENT_CODE='"+m_client+"'  "+
						"			 AND APPLICATION_STATUS NOT IN('NORM_TERMI','TERMI','WRITE_OFF','LG_SETTLED','RP_SOLD','RP_SETTLED', "+
						" 			 'TERMINATED','CANCEL','REJECT','CANCEL_PO')  "+
						"			 GROUP BY a.FINANCE_NO  "+
						" ) GROUP BY FINANCE_NO "+
						"");
					
					
					
					
					
					
					
					out.print("<DATA>");
					while(rs.next()){
						
						
						
						out.print("<ITEM>");
						out.print("<FINANCE_NO>"+rs.getString(1)+"</FINANCE_NO>");
						out.print("<VEHICLE_NO>"+rs.getString(4)+"</VEHICLE_NO>");
						
						BigDecimal amount1 = new BigDecimal("0.00");
						amount1 = amount1.add(rs.getBigDecimal(2));	
						out.print("<RENTAL>"+nf.format(amount1)+"</RENTAL>");
						
						BigDecimal amount2 = new BigDecimal("0.00");
						amount2 = amount2.add(rs.getBigDecimal(2));	
						out.print("<ODI>"+nf.format(amount2)+"</ODI>");
						
						BigDecimal amount3 = new BigDecimal("0.00");
						amount3 = amount3.add(rs.getBigDecimal(3));	
						out.print("<OTHER>"+nf.format(amount3)+"</OTHER>");
						
						BigDecimal amount4 = new BigDecimal("0.00");
						//amount4 = amount4.add(rs.getBigDecimal(4)).add(rs.getBigDecimal(6));
						amount4 = amount4.add(rs.getBigDecimal(2));
						out.print("<RENTAL_ODI>"+nf.format(amount4)+"</RENTAL_ODI>");
						
						//out.print("<RENTAL_DATE>"+rs.getString("RENTAL_DATE")+"</RENTAL_DATE>");							
						out.print("<RENTAL_DATE>"+rs.getString("CMS_PAY_CAN")+"</RENTAL_DATE>");// for enable and disable		
						
						out.print("<RENTAL_AMT>"+rs.getString("RENTAL_AMT")+"</RENTAL_AMT>");							
						out.print("<TOTAL_NO_RENTAL>"+rs.getString("TOTAL_NO_RENTAL")+"</TOTAL_NO_RENTAL>");							
						out.print("<APPLICATION_STATUS>"+rs.getString("APPLICATION_STATUS")+" - "+rs.getString("PERFORM_STATUS")+"</APPLICATION_STATUS>");							
						
						out.print("<TRAN_TYPE>"+rs.getString("TRAN_TYPE")+"</TRAN_TYPE>");
						
						// added by udara 14-12-2018
						BigDecimal amount_due = new BigDecimal("0.00");
						amount_due = amount_due.add(rs.getBigDecimal(12));
						out.print("<AMOUNT_DUE>"+nf.format(amount_due)+"</AMOUNT_DUE>");
						// end by udara 14-12-2018
						
						// added by udara 07-01-2019
						BigDecimal loan_excess_amount = new BigDecimal("0.00");
						loan_excess_amount = loan_excess_amount.add(rs.getBigDecimal(13));
						out.print("<LOAN_EXCESS_AMOUNT>"+nf.format(loan_excess_amount)+"</LOAN_EXCESS_AMOUNT>");
						// end by udara 07-01-2019
						
						
						out.print("</ITEM>");
						
						
						
						/*
						out.print("<ITEM>");
						out.print("<FINANCE_NO>"+rs.getString(2)+"</FINANCE_NO>");
						out.print("<VEHICLE_NO>"+rs.getString(7)+"</VEHICLE_NO>");
						
						BigDecimal amount1 = new BigDecimal("0.00");
						amount1 = amount1.add(rs.getBigDecimal(3));	
						out.print("<RENTAL>"+nf.format(amount1)+"</RENTAL>");
						
						BigDecimal amount2 = new BigDecimal("0.00");
						amount2 = amount2.add(rs.getBigDecimal(4));	
						out.print("<ODI>"+nf.format(amount2)+"</ODI>");
						
						BigDecimal amount3 = new BigDecimal("0.00");
						amount3 = amount3.add(rs.getBigDecimal(5));	
						out.print("<OTHER>"+nf.format(amount3)+"</OTHER>");
						
						BigDecimal amount4 = new BigDecimal("0.00");
						//amount4 = amount1.add(amount2); // commented by udara to correct rental+odi amount 27-06-2016	
						amount4 = amount4.add(rs.getBigDecimal(4)).add(rs.getBigDecimal(6));
						out.print("<RENTAL_ODI>"+nf.format(amount4)+"</RENTAL_ODI>");
						
						out.print("</ITEM>");
						*/
						
					}
					out.print("</DATA>");
					
					
				}catch(Exception e){
					out.println(e.toString());
				}
				
				
			}
			
			///------------------------------------End---------------------------------///
			
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

