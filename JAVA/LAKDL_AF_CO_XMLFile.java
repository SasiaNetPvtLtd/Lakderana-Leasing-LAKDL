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

public class LAKDL_AF_CO_XMLFile extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public  void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		Connection conn=null;
		Statement stmt=null;
		java.text.NumberFormat nf=null;
		 ResultSet rs=null;
		 String m_chksql=null;
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
			String m_username 						= con_method.username;
			
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
		else if(m_chksql.trim().equals("get_Invoice")){
			
			    String m_client      = req.getParameter("client");
          
					rs = stmt.executeQuery ("SELECT INVOICE_NO,FINANCE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),"+
					                        "       TOTAL_AMOUNT,SETTELE_AMOUNT, "+
																	"       BALANCE_TO_BE_RECEIVED,VAT_AMOUNT, "+
																	"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																	"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																	"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																	"       INVOICE_TYPE "+
																	"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																	"  WHERE CLIENT_CODE='"+m_client+"' AND "+
																	"        BALANCE_TO_BE_RECEIVED>0 AND "+
																	"	      ACTIVE_STATUS = 'Y' "+
																	"	ORDER BY INVOICE_TYPE	");

		
	      boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<INVNO>"     + rs.getString(1)  + "</INVNO>");
					out.println("<FINNO>"     + rs.getString(2)  + "</FINNO>");
					out.println("<VDATE>"     + rs.getString(3)  + "</FINNO>");
					out.println("<AMOUN>"     + rs.getString(4)  + "</AMOUN>");
					out.println("<SETTE>"     + rs.getString(5)  + "</SETTE>");
					out.println("<BALRE>"     + rs.getString(6)  + "</BALRE>");
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
				
				
				
		//----added by Prabash on 03-05-2012----------------------------
		
		else if(m_chksql.trim().equals("get_dayend_date")) {
				rs = stmt.executeQuery ("SELECT TO_CHAR(LAST_DAYEND_PROCESS,'DD'), TO_CHAR(LAST_DAYEND_PROCESS,'MM'), TO_CHAR(LAST_DAYEND_PROCESS,'YYYY'),PROCESS_USER,TO_CHAR(PROCESS_DATE,'DD'), TO_CHAR(PROCESS_DATE,'MM'), TO_CHAR(PROCESS_DATE,'YYYY'),'"+m_username+"' FROM "+m_schema_name+".FA_OP_DAYEND_ROUTINE");
				 boolean flag = rs.next();
				out.println("<Root>");
				for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<FDD>"     + rs.getString(1)  + "</FDD>");
					out.println("<FMM>"     + rs.getString(2)  + "</FMM>");
					out.println("<FYY>"     + rs.getString(3)  + "</FYY>");
					out.println("<TUS>"     + rs.getString(4)  + "</TUS>");
					out.println("<TDD>"     + rs.getString(5)  + "</TDD>");
					out.println("<TMM>"     + rs.getString(6)  + "</TMM>");
					out.println("<TYY>"     + rs.getString(7)  + "</TYY>");
					out.println("<NUS>"     + rs.getString(8)  + "</NUS>");
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
		}
		//-----------------------------------------------------------------
				
				
				
				
				
	    else if(m_chksql.trim().equals("get_Repossess_no")){
			       
							String m_reposs_no = req.getParameter("repossess_no");
			        
							rs = stmt.executeQuery(" SELECT A.REPOSSESSION_NO, A.FINANCE_NO, A.SEIZER_CODE, "+
							                       "        A.LETTER_VALIDITY_PERIOD, A.INVOICE_AMOUNT, "+
																		 "        A.INVOICE_AMOUNT_CURR, A.EXCHANGE_RATE, "+
																		 "      	A.TRN_CURR_CODE,A.VEHICLE_INVENTORY_STATUS, "+
																		 "      	A.INVENTORY_NO,A.TRN_DATE, A.REPOSSESSED_DATE, "+
																		 "        A.ACTIVE_STATUS "+
																		 " FROM   "+m_schema_name+".AF_RE_PRO_REPOSSESSION A "+
																		 " WHERE  REPOSSESSION_NO = '"+m_reposs_no+"' ");	
							
						
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
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }

			else if(m_chksql.trim().equals("get_Finance_no")){
			       
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
      
      }

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
      
      }else if(m_chksql.trim().equals("get_client_code")){
			       
							String m_client_code = req.getParameter("client_code");
			    
							rs = stmt.executeQuery(" SELECT CLIENT_CODE, FULL_NAME, ACTIVE_STATUS, TEMP_ACTIVE_STATUS "+
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
					out.println("</ITEM>");
				}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }else if(m_chksql.trim().equals("get_rec_no")){
			       
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
