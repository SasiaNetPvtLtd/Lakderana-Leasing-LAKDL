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

public class LAKDL_AF_CR_XMLFile extends javax.servlet.http.HttpServlet {
	/*
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	public ResultSet rs,rs2,rs3;
	public String m_chksql;
	ServletOutputStream out = null;
	CallableStatement callstmt1 =null;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public void service(HttpServletRequest req, HttpServletResponse res) // synchronized
	{
		
		Connection conn=null;
		Statement stmt=null;
		java.text.NumberFormat nf;
		ResultSet rs=null,rs2=null,rs3=null;
		String m_chksql=null;
		ServletOutputStream out = null;
		CallableStatement callstmt1 =null;
		
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
			String m_username 						= con_method.username;
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
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
			else if(m_chksql.trim().equals("get_rec_det")){
				
				String m_fin_no = req.getParameter("lea_no");
				//String m_to_date   = req.getParameter("to_date");
				
				rs = stmt.executeQuery(" SELECT REC_NO "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					"        "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B "+
					" WHERE  A.REC_NO=B.RECEIPT_NO AND "+ 
					"        B.INVOICE_NO IN ( SELECT INVOICE_NO "+
					"                          FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"                          WHERE  FINANCE_NO = '"+m_fin_no+"') AND "+ 
					"        STATUS IN ('E','B') AND SETTLE_MODE NOT IN ('CASH','STD_ORD') "+
					" UNION ALL "+
					" SELECT REC_NO "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					"        "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B  "+
					" WHERE  A.REC_NO=B.RECEIPT_NO AND  "+
					"        B.BAL_TOBE_RECEIVE>0 AND "+
					"        B.FINANCE_NO = M_FINANCE_NO AND "+
					"        STATUS IN ('E','B') AND SETTLE_MODE NOT IN ('CASH','STD_ORD')");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<RNO>"     + rs.getString(1)  + "</RNO>");
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
			
			else if(m_chksql.trim().equals("get_Finance_no")){
				
				try{				
					
					String m_finance_no  = req.getParameter("finance_no");
					String m_client_code = req.getParameter("client_code");
					String m_date        = req.getParameter("m_date");
					
					rs = stmt.executeQuery
						(" SELECT A.APPLICATION_NO, A.CLIENT_CODE, A.INQUARY_NO, "+
						"        A.FINANCE_NO,TRANSACTION_TYPE,'-', "+//A.INSURANCE_DATE, A.REVENUE_LICENSE_DATE
						"        '-', '-', '-', "+//A.LUXURY_TAX_DATE,A.DRIVING_LICENSE_DATE,A.DISTRICT_CODE
						"        A.APPLICATION_STATUS, A.CO_APPLICANT, A.FACILITY_NO, "+
						"        NVL("+m_schema_name+".AF_CO_GET_ODI_DUE(A.FINANCE_NO),0)+ "+
						"        NVL("+m_schema_name+".AF_CO_CAL_FUTURE_ODI(A.FINANCE_NO,'"+m_date+"'),0), "+
						"        NVL("+m_schema_name+".AF_CO_GET_UNALLO_REC_CON_AMT(A.FINANCE_NO),0), "+
						"        "+m_schema_name+".AF_CO_GET_APP_RATE('"+m_finance_no+"','') "+//"+m_vehicle_no+"				   	
						" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						" WHERE  FINANCE_NO = UPPER('"+m_finance_no+"') ");//AND A.CLIENT_CODE=UPPER('"+m_client_code+"') ");	
					
					
					boolean flag = rs.next();
					out.println("<Root>");
					for(; flag; flag = rs.next()){ 
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
						out.println("<ODI>"     + nf.format(rs.getDouble(13)) + "</ODI>");
						out.println("<UAR>"     + nf.format(rs.getDouble(14)) + "</UAR>");
						out.println("<APP_RATE>"+ nf.format(rs.getDouble(15))  + "</APP_RATE>");
						
						
						rs = stmt.executeQuery(" SELECT SUM(BALANCE_TO_BE_RECEIVED), "+
							"        SUM(BALANCE_TO_BE_RECEIVED-(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED))), "+
							"     	  SUM(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED)) "+
							" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
							" WHERE  FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='Y' AND TOTAL_AMOUNT<>0 ");
						
						flag = rs.next();
						if(flag){
							for(; flag; flag = rs.next())				{
								if(rs.getString(1)==null){
									out.println("<BAL_INV_AMOUNT>0</BAL_INV_AMOUNT>");
									out.println("<BAL_NET_AMOUNT>0</BAL_NET_AMOUNT>");
									out.println("<BAL_VAT_AMOUNT>0</BAL_VAT_AMOUNT>");
								}else{
									out.println("<BAL_INV_AMOUNT>"  + nf.format(rs.getDouble(1))  + "</BAL_INV_AMOUNT>");
									out.println("<BAL_NET_AMOUNT>"  + nf.format(rs.getDouble(2))  + "</BAL_NET_AMOUNT>");
									out.println("<BAL_VAT_AMOUNT>"  + nf.format(rs.getDouble(3))  + "</BAL_VAT_AMOUNT>");
								}
							}
						}else{
							out.println("<BAL_INV_AMOUNT>0</BAL_INV_AMOUNT>");
							out.println("<BAL_NET_AMOUNT>0</BAL_NET_AMOUNT>");
							out.println("<BAL_VAT_AMOUNT>0</BAL_VAT_AMOUNT>");
						}
						
						
						out.println("</ITEM>");
					}
					
					//out.println("</DATA>");
					out.println("</Root>");
					
				}
				catch(Exception e)
				{
					out.println("xx"+e.toString());
				}
			}
			//added by SH on 10-03-2008
			
			else if(m_chksql.trim().equals("get_ODI_NET")){
				
				String m_odi     = req.getParameter("ODI");
				String m_odi_adj = req.getParameter("ODI_ADJ");
				
				rs = stmt.executeQuery(" SELECT '"+m_odi+"'-'"+m_odi_adj+"' "+	
					" FROM   DUAL  ");	
				
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<ODN>"     + nf.format(rs.getDouble(1)) + "</ODN>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			//added by SH on 22-05-2007
			else if(m_chksql.trim().equals("get_inv_gen_date")){
				
				rs = stmt.executeQuery("SELECT TO_CHAR(TO_DATE+1,'DD'),TO_CHAR(TO_DATE+1,'MM'), "+
					"       TO_CHAR(TO_DATE+1,'YYYY'),TO_CHAR(ADD_MONTHS(TO_DATE,1),'DD'), "+
					"       TO_CHAR(ADD_MONTHS(TO_DATE,1),'MM'),TO_CHAR(ADD_MONTHS(TO_DATE,1),'YYYY'), "+
					"			 TO_CHAR(FROM_DATE,'DD'),TO_CHAR(FROM_DATE,'MM'),TO_CHAR(FROM_DATE,'YYYY'), "+
					" 			 TO_CHAR(TO_DATE,'DD'),TO_CHAR(TO_DATE,'MM'),TO_CHAR(TO_DATE,'YYYY') "+
					"FROM  (SELECT MAX(A.TO_DATE) TO_DATE,MAX(A.FROM_DATE) FROM_DATE  "+
					" 			 FROM  "+m_schema_name+".AF_CO_PRO_RESIDUAL_DATE A)");
				
				
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
			else if(m_chksql.trim().equals("get_date_dif")){
				
				String m_from_date = req.getParameter("from_date");
				String m_to_date   = req.getParameter("to_date");
				
				rs = stmt.executeQuery(" SELECT TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),'DD'), "+
					"        TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),'MM'), "+
					"        TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYYY'),'YYYY'),"+
					"        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD'), "+
					"        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MM'), "+
					"        TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
					"  FROM  DUAL "+
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
			
			//end 
			else if(m_chksql.trim().equals("get_veh_no")){
				
				String m_vehicle_no = req.getParameter("veh_no");
				
				rs = stmt.executeQuery(" SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE  APPLICATION_NO IN (SELECT APPLICATION_NO "+
					"                           FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					"                           WHERE  REG_NO= UPPER('"+m_vehicle_no+"'))");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FIN>"     + rs.getString(1)  + "</FIN>");
					out.println("<APN>"     + rs.getString(2)  + "</APN>");
					out.println("<CLC>"     + rs.getString(3)  + "</CLC>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}else if(m_chksql.trim().equals("get_client_code")){
				
				String m_client_code = req.getParameter("client_code");
				
				rs = stmt.executeQuery(" SELECT CLIENT_CODE, FULL_NAME, ACTIVE_STATUS, TEMP_ACTIVE_STATUS "+
					" FROM   "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE  CLIENT_CODE like UPPER('%"+m_client_code+"%')");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<CLIENT>"  + rs.getString(1)  + "</CLIENT>");
					out.println("<FNAME>"   + rs.getString(2)  + "</FNAME>");
					out.println("<ACTIVE>"  + rs.getString(3)  + "</ACTIVE>");
					out.println("<T_ACT>"   + rs.getString(4)  + "</T_ACT>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			
			// added by udara on 06-02-2014
			
			else if(m_chksql.trim().equals("check_termi_date")){
				
				String m_finance_no = req.getParameter("finance_no");
				String m_termi_date = req.getParameter("termi_date");
				String m_termi_valid_date = req.getParameter("termi_valid_date");
				
				String m_termi_date_status = "";
				String m_termi_valid_date_status = "";
				
				// commented by udara 03-03-2014
				/*
				// added by udara 12-02-2014
				rs = stmt.executeQuery(" "+
							" SELECT "+
									" ( "+
							" CASE WHEN  TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)  THEN "+
								" CASE WHEN "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"'))=TO_CHAR(TO_DATE('"+m_termi_date+"','DD-MM-YYYY'),'DD-MM-YYYY') THEN 'TRUE' "+
								" ELSE "+
								" 'FALSE' "+
								" END "+
							" ELSE 'TRUE'  "+
							" END   "+
									" ) TERMI_DATE,  "+
							
							" ( "+
							" CASE WHEN  TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)  THEN    "+
								" CASE WHEN "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"'))=TO_CHAR(TO_DATE('"+m_termi_valid_date+"','DD-MM-YYYY'),'DD-MM-YYYY') THEN 'TRUE' "+
								" ELSE "+
								" 'FALSE' "+
								" END "+
							" ELSE 'TRUE'  "+
							" END   "+
									" ) TERMI_VALID_DATE "+
			
									" FROM DUAL ");
				*/
				
				// added by udara 03-03-2014
				rs = stmt.executeQuery(" "+
					" SELECT "+
					" ( "+
					" CASE WHEN  TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)  THEN "+ // commented by udara 27-09-2017 // re-enabled to reverse the modification 28-11-2017 - in live replaced with previous backup while reversing
					//" CASE WHEN  (TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)) OR (TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') <= TRUNC(SYSDATE))  THEN "+
					" CASE WHEN "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"'))=TO_CHAR(TO_DATE('"+m_termi_date+"','DD-MM-YYYY'),'DD-MM-YYYY') THEN 'TRUE' "+
					" ELSE "+
					" 'FALSE' "+
					" END "+
					" ELSE 'TRUE'  "+
					" END   "+
					" ) TERMI_DATE,  "+
					
					" ( "+
					" CASE WHEN  TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)  THEN    "+ // commented by udara 27-09-2017 // re-enabled to reverse the modification 28-11-2017 - in live replaced with previous backup while reversing
					//" CASE WHEN  (TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)) OR (TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') <= TRUNC(SYSDATE))  THEN    "+ // added by udara 27-09-2017
					" CASE WHEN "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"'))=TO_CHAR(TO_DATE('"+m_termi_valid_date+"','DD-MM-YYYY'),'DD-MM-YYYY') THEN 'TRUE' "+
					" ELSE "+
					" 'FALSE' "+
					" END "+
					" ELSE 'TRUE'  "+
					" END   "+
					" ) TERMI_VALID_DATE, "+
					
					
					" ( "+
					//" CASE WHEN  TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)  THEN "+
					" CASE WHEN "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE_2("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"'))=TO_CHAR(TO_DATE('"+m_termi_date+"','DD-MM-YYYY'),'DD-MM-YYYY') THEN 'TRUE' "+
					" ELSE "+
					" 'FALSE' "+
					" END "+
					//" ELSE 'TRUE'  "+
					//" END   "+
					" ) TERMI_DATE_ET,  "+
					
					" ( "+
					//" CASE WHEN  TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'DD-MM-YYYY') >= TRUNC(SYSDATE)  THEN    "+
					" CASE WHEN "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE_2("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"'))=TO_CHAR(TO_DATE('"+m_termi_valid_date+"','DD-MM-YYYY'),'DD-MM-YYYY') THEN 'TRUE' "+
					" ELSE "+
					" 'FALSE' "+
					" END "+
					//" ELSE 'TRUE'  "+
					//" END   "+
					" ) TERMI_VALID_DATE_ET, "+	
					
					
					// added by udara 10-04-2014
					" ( "+
					"CASE WHEN (SELECT MAX(DUE_DATE) "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE FINANCE_NO = '"+m_finance_no+"' AND INVOICE_TYPE = 'INV_GENER') > TO_DATE('"+m_termi_date+"','DD-MM-YYYY') THEN 'FALSE' "+
					" ELSE "+
					" 'TRUE' "+
					" END "+
					" ) TERMI_DATE_DUE_ET, "+
					
					
					" ( "+
					"CASE WHEN (SELECT MAX(DUE_DATE) "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE FINANCE_NO = '"+m_finance_no+"' AND INVOICE_TYPE = 'INV_GENER') > TO_DATE('"+m_termi_valid_date+"','DD-MM-YYYY') THEN 'FALSE' "+
					" ELSE "+
					" 'TRUE' "+
					" END "+
					" ) TERMI_VALID_DATE_DUE_ET "+
					
					// end by udara 10-04-2014
					
					
					
					" FROM DUAL ");
				
				
				out.println("<Root>");
				if(rs.next()){
					out.println("<ITEM>");
					out.println("<TERMI_DATE>"  + rs.getString(1)  + "</TERMI_DATE>");
					out.println("<TERMI_VALID_DATE>"   + rs.getString(2)  + "</TERMI_VALID_DATE>");
					out.println("<TERMI_DATE_ET>"   + rs.getString(3)  + "</TERMI_DATE_ET>"); // added by udara 03-03-2014
					out.println("<TERMI_VALID_DATE_ET>"   + rs.getString(4)  + "</TERMI_VALID_DATE_ET>"); // added by udara 03-03-2014 
					
					out.println("<TERMI_DATE_DUE_ET>"   + rs.getString(5)  + "</TERMI_DATE_DUE_ET>"); // added by udara 10-04-2014
					out.println("<TERMI_VALID_DATE_DUE_ET>"   + rs.getString(6)  + "</TERMI_VALID_DATE_DUE_ET>"); // added by udara 10-04-2014
					
					out.println("</ITEM>");
				}
				out.println("</Root>");
				
				
				
			}
			
			// end by udara on 06-02-2014
			
			else if(m_chksql.trim().equals("get_due_invoice_sum")){
				
				String m_finance_no  = req.getParameter("finance_no");
				
				rs = stmt.executeQuery(" SELECT SUM(BALANCE_TO_BE_RECEIVED), "+
					"        SUM(BALANCE_TO_BE_RECEIVED-(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED))), "+
					"     	  SUM(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED)) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE  FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS<>'CANCEL' AND TOTAL_AMOUNT<>0 ");
				
				boolean flag = rs.next();
				out.println("<Root>");
				if(flag){
					for(; flag; flag = rs.next())				{
						out.println("<ITEM>");
						if(rs.getString(1)==null){
							out.println("<BAL_INV_AMOUNT>0</BAL_INV_AMOUNT>");
							out.println("<BAL_NET_AMOUNT>0</BAL_NET_AMOUNT>");
							out.println("<BAL_VAT_AMOUNT>0</BAL_VAT_AMOUNT>");
						}else{
							out.println("<BAL_INV_AMOUNT>"  + nf.format(rs.getDouble(1))  + "</BAL_INV_AMOUNT>");
							out.println("<BAL_NET_AMOUNT>"  + nf.format(rs.getDouble(2))  + "</BAL_NET_AMOUNT>");
							out.println("<BAL_VAT_AMOUNT>"  + nf.format(rs.getDouble(3))  + "</BAL_VAT_AMOUNT>");
						}
						out.println("</ITEM>");
					}
				}else{
					out.println("<ITEM>");
					out.println("<BAL_INV_AMOUNT>0</BAL_INV_AMOUNT>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}else if(m_chksql.trim().equals("get_due_rent_sum")){
				
				String m_finance_no = req.getParameter("finance_no");
				String m_vehicle_no = req.getParameter("veh_no");
				String m_date       = req.getParameter("tdate");
				
				//rs = stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_NOT_INV_RENTAL('"+m_finance_no+"','"+m_vehicle_no+"','"+m_date+"') "+
				//                       " FROM   DUAL");
				//rs = stmt.executeQuery("SELECT NVL(SUM(NET_RENTAL_AMOUNT*(1+("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_date+"','DD-MM-YYYY'))/100))),0), "+ // commented by udara 21-08-2017
				
				
				
				
				// commented by udara 23-08-2017	
				/*	
					rs = stmt.executeQuery("SELECT NVL(SUM(NET_RENTAL_AMOUNT*(1+("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_date+"','DD-MM-YYYY'))/100))),0) - SUM(NVL("+m_schema_name+".AF_CR_FREEZED_RENTAL_AMOUNT(B.APPLICATION_NO,B.FINANCE_NO,NULL),0)), "+ // added by udara 21-08-2017
					//out.println("SELECT NVL(SUM(NET_RENTAL_AMOUNT*(1+("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_date+"','DD-MM-YYYY'))/100))),0), "+
											//"       NVL(SUM(NET_RENTAL_AMOUNT),0), "+ // commented by udara 22-08-2017
											"       NVL(SUM(NET_RENTAL_AMOUNT),0) - NVL(SUM(NVL("+m_schema_name+".AF_CR_FREEZED_RENTAL_AMOUNT(B.APPLICATION_NO,B.FINANCE_NO,NULL),0)),0), "+ // added by udara 22-08-2017
																	"       NVL(SUM(NET_RENTAL_AMOUNT*"+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_date+"','DD-MM-YYYY'))/100),0) "+
																"FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																"WHERE  A.APPLICATION_NO = B.APPLICATION_NO AND "+
																	"       INVOICE_NO IS NULL AND    "+
																"       RENTAL_DATE < TO_DATE('"+m_date+"','DD-MM-YYYY')  AND "+
																"      (PRO_INVOICE_NO,a.APPLICATION_NO,PRICING_NO) IN (SELECT INVOICE_NO, APPLICATION_NO,PRICING_NO "+
																"                                                      FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
																"                                                      WHERE  APPLICATION_NO  = (SELECT APPLICATION_NO "+
																"                                                                                FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																"                                                                                WHERE  FINANCE_NO    = '"+m_finance_no+"') AND "+
																	"                                                                                       ACTIVE_STATUS = 'Y' )"); //added by SH on 29/05/2009
					
				*/	
				
				// added by udara 23-08-2017
				
				rs = stmt.executeQuery("SELECT NVL(SUM(NET_RENTAL_AMOUNT*(1+("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_date+"','DD-MM-YYYY'))/100))),0) - SUM(NVL("+m_schema_name+".AF_CR_FREEZED_RENTAL_AMOUNT_1(B.APPLICATION_NO,B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),A.INSTALLMENT_NO),0)), "+ // added by udara 21-08-2017
					//out.println("SELECT NVL(SUM(NET_RENTAL_AMOUNT*(1+("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_date+"','DD-MM-YYYY'))/100))),0), "+
					//"       NVL(SUM(NET_RENTAL_AMOUNT),0), "+ // commented by udara 22-08-2017
					"       NVL(SUM(NET_RENTAL_AMOUNT),0) - NVL(SUM(NVL("+m_schema_name+".AF_CR_FREEZED_RENTAL_AMOUNT_1(B.APPLICATION_NO,B.FINANCE_NO,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),A.INSTALLMENT_NO),0)),0), "+ // added by udara 22-08-2017
					"       NVL(SUM(NET_RENTAL_AMOUNT*"+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_date+"','DD-MM-YYYY'))/100),0) "+
					"FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					"WHERE  A.APPLICATION_NO = B.APPLICATION_NO AND "+
					"       INVOICE_NO IS NULL AND    "+
					"       RENTAL_DATE < TO_DATE('"+m_date+"','DD-MM-YYYY')  AND "+
					"      (PRO_INVOICE_NO,a.APPLICATION_NO,PRICING_NO) IN (SELECT INVOICE_NO, APPLICATION_NO,PRICING_NO "+
					"                                                      FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"                                                      WHERE  APPLICATION_NO  = (SELECT APPLICATION_NO "+
					"                                                                                FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"                                                                                WHERE  FINANCE_NO    = '"+m_finance_no+"') AND "+
					"                                                                                       ACTIVE_STATUS = 'Y' )");
				
				// end by udara 23-08-2017
				
				
				
				
				/*	
						rs = stmt.executeQuery("SELECT NVL(SUM(GRENTAL_AMOUNT),0),NVL(SUM(NET_RENTAL_AMOUNT),0),NVL(SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
																		"FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
																		"WHERE  INVOICE_NO IS NULL AND    "+
																		"       RENTAL_DATE < TO_DATE('"+m_date+"','DD-MM-YYYY')  AND "+
																		"      (PRO_INVOICE_NO, APPLICATION_NO,PRICING_NO) IN (SELECT INVOICE_NO, APPLICATION_NO,PRICING_NO "+
																		"                                                      FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
																		"                                                      WHERE  APPLICATION_NO  = (SELECT APPLICATION_NO "+
																		"                                                                                FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																		"                                                                                WHERE  FINANCE_NO    = '"+m_finance_no+"') AND "+
																			"                                                                                       ACTIVE_STATUS = 'Y' )"); //added by SH on 29/05/2009
				
			*/
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next()) {
					out.println("<ITEM>");
					out.println("<BAL_INV_AMOUNT>"  +  nf.format(rs.getDouble(1))  + "</BAL_INV_AMOUNT>");
					out.println("<NET_INV_AMOUNT>"  +  nf.format(rs.getDouble(2))  + "</NET_INV_AMOUNT>");
					out.println("<VAT_INV_AMOUNT>"  +  nf.format(rs.getDouble(3))  + "</VAT_INV_AMOUNT>");
					
					rs2 = stmt.executeQuery(" SELECT COUNT(*) "+
						" FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION "+
						" WHERE  FINANCE_NO='"+m_finance_no+"' AND REQUESTED_BY='CLIENT' ");
					
					flag = rs2.next();
					if(flag) {
						out.println("<TER_COUNT>"  + rs2.getString(1)  + "</TER_COUNT>");
						
						rs3 = stmt.executeQuery(" SELECT AMOUNT "+
							" FROM   "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "/*+
														 " WHERE  TERMINATION_TYPE = '"+m_term_type+"' "*/);
						
						flag = rs3.next();
						if(flag){
							out.println("<TER_AMOUNT>"  +  nf.format(rs3.getDouble(1))  + "</TER_AMOUNT>");
						}else{
							out.println("<TER_AMOUNT>0</TER_AMOUNT>");
						}
					}else{
						out.println("<TER_COUNT>0</TER_COUNT>");
						out.println("<TER_AMOUNT>0</TER_AMOUNT>");
					}	
					
					out.println("</ITEM>");
				}											   
				//out.println("</DATA>");
				out.println("</Root>");
			}
			//
			else if(m_chksql.trim().equals("get_term_details")){
				
				String m_finance_no = req.getParameter("finance_no");
				String m_vehicle_no = req.getParameter("veh_no");
				String m_chassis_no = req.getParameter("chas_no");
				String m_invoice_no = req.getParameter("invo_no");
				
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					"AF_CR_TEMP_TERMINATION_CAPITAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12);END;");
				callstmt1.setString(1 ,m_username);
				callstmt1.setString(2 ,m_vehicle_no);
				callstmt1.setString(3 ,m_chassis_no);
				callstmt1.setString(4 ,m_finance_no);
				callstmt1.registerOutParameter(5,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(6,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(7,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(8,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(9,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(10,java.sql.Types.CHAR);
				callstmt1.setString(11 ,m_invoice_no);
				callstmt1.registerOutParameter(12,java.sql.Types.CHAR);
				
				//out.println("t5");
				callstmt1.execute();
				//out.println("t6");
				
				//boolean flag = rs.next();
				out.println("<Root>");
				//for(; flag; flag = rs.next())				{
				out.println("<ITEM>");
				out.println("<CAP_AMT>"+  nf.format(callstmt1.getDouble(5))  + "</CAP_AMT>");
				out.println("<NIBSM>"  +  nf.format(callstmt1.getDouble(6))  + "</NIBSM>");
				out.println("<AMI_AMT>"+  nf.format(callstmt1.getDouble(7))  + "</AMI_AMT>");
				out.println("<CAP_OUT>"+  nf.format(callstmt1.getDouble(8))  + "</CAP_OUT>");
				out.println("<AMI_CAP>"+  nf.format(callstmt1.getDouble(9))  + "</AMI_CAP>");
				double m_per=0;
				if (callstmt1.getDouble(5)!=0){
					m_per = ((callstmt1.getDouble(8))/callstmt1.getDouble(5))*100; 
				}else{
					m_per = 0;
				}
				out.println("<CAP_PER>"+  nf.format(m_per)  + "</CAP_PER>");
				out.println("<VAT>"+  nf.format(callstmt1.getDouble(10))  + "</VAT>");
				out.println("<INT_AMT>"+  nf.format(callstmt1.getDouble(12))  + "</INT_AMT>");
				out.println("</ITEM>");
				//}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			} 
			//
			else if(m_chksql.trim().equals("get_term_no")){
				
				String m_term_no  = req.getParameter("term_no");
				
				rs = stmt.executeQuery(" SELECT TERMINATION_NO, FINANCE_NO, APPLICATION_NO, CLIENT_CODE, "+
					"        TO_CHAR(TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'), "+
					"        TO_CHAR(APPLY_DATE,'DD-MM-YYYY'), "+   
					"        REQUESTED_BY,RATE, AMOUNT, REMARKS, "+
					"        CHARGES, TERMINATION_COUNT,DUE_AMOUNT,ACTIVE_STATUS "+
					"  FROM  "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
					"  WHERE ACTIVE_STATUS = 'ENT' AND "+
					"        TERMINATION_NO = '"+m_term_no+"'");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TER_NO>"  + rs.getString(1)  + "</TER_NO>");
					out.println("<FIN_NO>"  + rs.getString(2)  + "</FIN_NO>");
					out.println("<APP_NO>"  + rs.getString(3)  + "</APP_NO>");
					out.println("<CLI_NO>"  + rs.getString(4)  + "</CLI_NO>");
					out.println("<TVD_NO>"  + rs.getString(5)  + "</TVD_NO>");
					out.println("<APD_NO>"  + rs.getString(6)  + "</APD_NO>");
					out.println("<REB_NO>"  + rs.getString(7)  + "</REB_NO>");
					out.println("<RAT_NO>"  + rs.getString(8)  + "</RAT_NO>");
					out.println("<AMO_NO>"  + rs.getString(9)  + "</AMO_NO>");
					out.println("<REM_NO>"  + rs.getString(10) + "</REM_NO>");
					out.println("<CHA_NO>"  + rs.getString(11) + "</CHA_NO>");
					out.println("<TCO_NO>"  + rs.getString(12) + "</TCO_NO>");
					out.println("<DUE_NO>"  + rs.getString(13) + "</DUE_NO>");
					out.println("<STA_NO>"  + rs.getString(14) + "</STA_NO>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}else if(m_chksql.trim().equals("get_term_rate")){
				
				String m_ter_rate  = req.getParameter("ter_rate");
				
				rs = stmt.executeQuery(" SELECT '"+m_ter_rate+"' "+
					" FROM   "+m_schema_name+".AF_CO_MAS_TERMNATION_RATE "+
					" WHERE  MIN_CHARGE>='"+m_ter_rate+"' AND MAX_CHARGES<='"+m_ter_rate+"' ");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TER_RATE>"  +  nf.format(rs.getDouble(1))  + "</TER_RATE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}else if(m_chksql.trim().equals("get_term_count")){
				
				String m_finance_no  = req.getParameter("finance_no");
				
				rs = stmt.executeQuery(" SELECT COUNT(*) "+
					" FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION "+
					" WHERE  FINANCE_NO='"+m_finance_no+"' AND REQUESTED_BY='CLIENT' ");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TER_COUNT>"  + rs.getString(1)  + "</TER_COUNT>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			
			else if(m_chksql.trim().equals("get_IRR")){
				
				String m_tdate        = req.getParameter("tdate");
				String m_finance_no   = req.getParameter("finance_no");
				String term_amt       = req.getParameter("term_amt");
				String odi_amt        = req.getParameter("odi_amt");
				String unallo_amt     = req.getParameter("unallo_amt");
				
				
				//rs = stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_CLOSURE_IRR_N('"+m_finance_no+"','"+m_tdate+"','"+term_amt+"','"+unallo_amt+"') "+ // commented by udara on 10-04-2013
				rs = stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_CLOSURE_IRR_N('"+m_finance_no+"','"+m_tdate+"','"+term_amt+"') "+ // added by udara on 10-04-2013
					" FROM   DUAL");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TER_IRR>"  +  nf.format(rs.getDouble(1))  + "</TER_IRR>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			else if(m_chksql.trim().equals("get_term_charge")){
				
				String m_term_type  = req.getParameter("term_type");
				
				rs = stmt.executeQuery(" SELECT AMOUNT "+
					" FROM   "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "/*+
															 " WHERE  TERMINATION_TYPE = '"+m_term_type+"' "*/);
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<TER_AMOUNT>"  +  nf.format(rs.getDouble(1))  + "</TER_AMOUNT>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}else if(m_chksql.trim().equals("get_lease_rate")){
				
				String m_finance_no  = req.getParameter("finance_no");
				String m_vehicle_no = req.getParameter("veh_no");
				
				rs = stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_APP_RATE('"+m_finance_no+"','') "+//"+m_vehicle_no+"
					" FROM   DUAL ");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<APP_RATE>"  +  nf.format(rs.getDouble(1))  + "</APP_RATE>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			else if(m_chksql.trim().equals("get_rec_no")){
				
				String m_receipt_no = req.getParameter("rec_no");
				
				rs = stmt.executeQuery(" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
					"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE "+
					"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
					"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
					"	       ALLOCATED_AMOUNT>0 AND A.REC_NO = '"+m_receipt_no+"' ");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<REC_NO>"  + rs.getString(1)  + "</REC_NO>");
					out.println("<REC_AM>"  + rs.getString(2)  + "</REC_AM>");
					out.println("<ALL_AM>"  + rs.getString(3)  + "</ALL_AM>");
					out.println("<BAL_RE>"  + rs.getString(4)  + "</BAL_RE>");
					out.println("<OTH_CO>"  + rs.getString(4)  + "</OTH_CO>");
					out.println("<CURR_CO>" + rs.getString(4)  + "</CURR_CO>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
			else if(m_chksql.trim().equals("get_sysdate")){//ADDED MILINDA FOR GET SYSDATE
				
				
				
				rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') "+//"+m_vehicle_no+"
					" FROM   DUAL ");
				
				boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<DD>"  + rs.getString(1)  + "</DD>");
					out.println("<MM>"  + rs.getString(2)  + "</MM>");
					out.println("<YYYY>"  + rs.getString(3)  + "</YYYY>");
					out.println("</ITEM>");
				}
				
				//out.println("</DATA>");
				out.println("</Root>");
				
			}
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
