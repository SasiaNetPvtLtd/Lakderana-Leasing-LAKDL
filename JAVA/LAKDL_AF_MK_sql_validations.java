import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MK_sql_validations extends javax.servlet.http.HttpServlet {
	
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
		
		
		Connection conn = null;
		Statement stmt = null,stmt1= null;
		CallableStatement callstmt= null;
		java.text.NumberFormat nf= null;
		java.text.NumberFormat nf1= null;
		
		ResultSet rs= null,rs1= null;
		String m_chksql= null;
		
		
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
			
			
			//---------------------  ID  		:Indicative Quatation Process---------------------------------//
			//---------------------Purpose 	:To find details-----------------------------------
			//---------------------Name     :delanjli--------------------------------------------------
			//---------------------Date     :28-08-2006--------------------------------------------------
			
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
			
			
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_check_amount_value")){
				
				double m_amt_value  = Double.parseDouble(req.getParameter("data_val_amt_value"));
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_asset_no   = req.getParameter("data_val_asset_no").trim();
				
				
				rs=stmt.executeQuery(" SELECT "+
					" ROUND(NET_AMOUNT*(VAT_PERCENTAGE-VAT_APP)/100 + NET_AMOUNT ) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A, "+
					" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE A.PRO_INVOICE_NO = B.INVOICE_NO AND "+
					" B.INVOICE_NO='"+m_invoice_no+"' AND B.ASSET_ID ='"+m_asset_no+"' "+
					" AND ROUND(NET_AMOUNT*(VAT_PERCENTAGE-VAT_APP)/100 + NET_AMOUNT )>'"+m_amt_value+"' ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					//out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
			}
			
			
			
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_dispute_valuation_report_validate_valuation_no_new")){
				
				String m_val = req.getParameter("data_val_valuation_no").trim();
				String m_status = req.getParameter("disp_status").trim();
				
				rs=stmt.executeQuery("SELECT VALUATION_NO, "+ //1
					" ASSET_ID, "+ 
					" APPLICATION_NO, "+ //3
					" SUB_MODEL_CODE, "+ //4
					" REG_NO, "+    //5
					" ENGINE_NO, "+ //6
					" CHASSIS_NO, "+ //7
					" NVL(COLOUR,'-'), "+    //8
					" MODEL_CODE, "+ //9
					" NVL(NOTES,'-'), "+      //10
					" NVL(REMARKS,'-'), "+    //11
					" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'), "+ //12
					" NVL(VALUE,0), "+
					" TYPE_OF_BODY, "+ //14
					" TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'), "+  //15
					" METER_READING, "+ //16
					" ACTIVE_STATUS, "+
					" GENERAL_INDEX, "+ //18
					" SEATING_CAPACITY, "+ //19
					" NO_OF_CYLINDERS, "+  //20
					" PRO_INVOICE_NO, "+  //21
					" INVENTORY_NO, "+
					" VAL_STATUS, "+
					" NVL(VALUER_CODE,'-'), "+   //24
					" NVL(YEAR_OF_MANUFACTURE,0), "+ //25
					" CONDITION_OF_ASSET, "+ //26
					" NVL(FORCED_SALES_VALUE,0), "+ //27
					" DISP_COMMENTS, "+   //28
					" DISP_STATUS "+ //29
					" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
					" WHERE DISP_STATUS='"+m_status+"' AND "+
					" ( VALUATION_NO = UPPER('"+m_val+"') OR "+
					" APPLICATION_NO = UPPER('"+m_val+"') OR "+
					" UPPER(REG_NO) = UPPER('"+m_val+"') OR "+
					" UPPER(ENGINE_NO) = UPPER('"+m_val+"') OR "+
					" UPPER(CHASSIS_NO) = UPPER('"+m_val+"') ) ");		
				
				
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
					out.print("<R13>"+rs.getDouble(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>");
					out.print("<R15>"+rs.getString(15)+"</R15>");
					out.print("<R16>"+rs.getString(16)+"</R16>");
					out.print("<R17>"+rs.getString(18)+"</R17>");
					out.print("<R18>"+rs.getString(19)+"</R18>");
					
					out.print("<R19>"+rs.getString(20)+"</R19>");
					out.print("<R20>"+rs.getString(21)+"</R20>");
					out.print("<R21>"+rs.getString(23)+"</R21>");
					out.print("<R22>"+rs.getString(24)+"</R22>");
					out.print("<R23>"+rs.getString(25)+"</R23>");
					out.print("<R24>"+rs.getString(26)+"</R24>");
					out.print("<R25>"+rs.getString(27)+"</R25>");
					out.print("<R26>"+rs.getString(28)+"</R26>");
					out.print("<R27>"+rs.getString(29)+"</R27>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//*modifed(2006-10-02)
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_conasst")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_val1 = req.getParameter("data_val1").trim();
				
				rs=stmt.executeQuery("SELECT CODE,DESCRIPTION "+
					"FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET  WHERE DESCRIPTION= ('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//*modified(2006-10-02)	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_make")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_val1 = req.getParameter("data_val1").trim();
				
				rs=stmt.executeQuery("SELECT MAKE_DESC "+
					"FROM "+m_schema_name+".AF_CO_MAS_MAKE WHERE UPPER(MAKE_DESC)=UPPER('"+m_val+"')");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					//out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//*modifed(2006-0-10-02)
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_model")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_val1 = req.getParameter("data_val1").trim();
				
				/*rs=stmt.executeQuery(" SELECT DESCRIPTION "+
															"FROM "+m_schema_name+".AF_CO_MAS_MODEL  WHERE REPLACE(DESCRIPTION,' ','-')= ('"+m_val+"')");
			*/
				rs=stmt.executeQuery(" SELECT DESCRIPTION "+
					"FROM "+m_schema_name+".AF_CO_MAS_MODEL  WHERE DESCRIPTION=('"+m_val+"')");
				
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation_pricing_det")){
				
				String m_val = req.getParameter("data_val").trim();
				
				//modified by : delanjali
				//date				: 2007-07-02
				//ref No			: 526
				/*	rs=stmt.executeQuery("SELECT TO_CHAR(NVL(NET_RENTAL_AMOUNT,0),'9,999,999,999,999,999,999,999,999.9999') AS AMT1,TO_CHAR(NVL((GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0),'9,999,999,999,999,999,999,999,999.9999')AS AMT2 "+//TO_CHAR(NVL((GRENTAL_AMOUNT),0),'999,999.99') AS AMT1,TO_CHAR(NVL((VAT_RENT_AMOUNT),0),'999,999.99')AS AMT2 "+
																"FROM "+m_schema_name+".AF_MK_PRO_PRICING A ,"+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT B "+
																"WHERE A.PRICING_NO = UPPER('"+m_val+"')"+
																"AND A.PRICING_NO=B.PRICING_NO "+
																"AND INSTALLMENT_NO IN ('0','1') "+
																"GROUP BY A.PRICING_NO,NET_RENTAL_AMOUNT,(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT) ");
				*/
				rs=stmt.executeQuery("SELECT TO_CHAR(NVL(NET_RENTAL_AMOUNT,0),'9,999,999,999,999,999,999,999,999.9999') AS AMT1,TO_CHAR(NVL((GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0),'9,999,999,999,999,999,999,999,999.9999')AS AMT2 "+//TO_CHAR(NVL((GRENTAL_AMOUNT),0),'999,999.99') AS AMT1,TO_CHAR(NVL((VAT_RENT_AMOUNT),0),'999,999.99')AS AMT2 "+
					"FROM "+m_schema_name+".AF_MK_PRO_PRICING A ,"+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT B "+
					"WHERE A.PRICING_NO = UPPER('"+m_val+"')"+
					"AND A.PRICING_NO=B.PRICING_NO "+
					"AND INSTALLMENT_NO IN ('1') "+
					"GROUP BY A.PRICING_NO,NET_RENTAL_AMOUNT,(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT) ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_indicative_quotation")){
				
				String m_val = req.getParameter("data_val").trim();
				
				rs=stmt.executeQuery("SELECT TO_CHAR(NVL(SUM(GRENTAL_AMOUNT),0),'9,999,999,999,999,999,999,999,999.9999') AS AMT1,TO_CHAR(NVL(SUM(VAT_RENT_AMOUNT ),0),'9,999,999,999,999,999,999,999,999.9999') AS AMT2 "+
					"FROM "+m_schema_name+".AF_MK_PRO_PRICING A ,"+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT B "+
					"WHERE A.PRICING_NO = UPPER('"+m_val+"')"+
					"AND A.PRICING_NO=B.PRICING_NO "+
					"AND INSTALLMENT_NO IN ('0','1') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//*modifed(2006-0-10-02)
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_model1")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				/*rs= stmt.executeQuery ("SELECT DISTINCT REPLACE(A.MODEL_CODE,' ','-'),REPLACE(DESCRIPTION,' ','-') FROM "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_MK_PRO_PRICING B "+
				" WHERE (A.MODEL_CODE LIKE UPPER('"+m_val+"%') OR A.MODEL_CODE LIKE REPLACE(UPPER('"+m_val+"%'),'-',' '))" + //AND A.ACTIVE_STATUS='"+m_status+"'" +
				" AND A.MODEL_CODE=B.MODEL_CODE");
				*/
				rs= stmt.executeQuery ("SELECT DISTINCT A.MODEL_CODE,DESCRIPTION FROM "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_MK_PRO_PRICING B "+
					" WHERE (A.MODEL_CODE LIKE UPPER('"+m_val+"%') OR A.MODEL_CODE LIKE UPPER('"+m_val+"%'))" + //AND A.ACTIVE_STATUS='"+m_status+"'" +
					" AND A.MODEL_CODE=B.MODEL_CODE");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_rate")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT COUNT(*)  "+  
					" FROM "+m_schema_name+".AF_MK_PRO_PRICING A,"+m_schema_name+".AF_CO_MAS_INTEREST_RATE B"+
					//" WHERE A.ENT_DATE=APPLY_DATE"+
					" WHERE A.TRANSACION_TYPE=B.TRN_TYPE "+
					" AND PRICING_NO=UPPER('"+m_val+"') "+
					//" AND A.RATE < B.RATE ");
					" AND A.RATE < "+m_schema_name+".AF_CO_GET_INTEREST_RATE");
				//" AND A.RATE< B.RATE ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					//	out.print("<R2>"+rs.getString(2)+"</R2>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_conasst")){
				String m_val = req.getParameter("data_val").trim();
				
				//String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT CODE,DESCRIPTION  "+  
					" FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET "+
					" WHERE CODE LIKE UPPER('"+m_val+"%')" );
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			//*modied(2006-10-02)
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_make")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT DISTINCT MAKE_CODE,MAKE_DESC FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
					" WHERE UPPER(MAKE_CODE)=UPPER('"+m_val+"') OR UPPER(MAKE_DESC)=UPPER('"+m_val+"') " );
				//AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					//out.print("<R3>"+rs.getString(3)+"</R3>");
					//out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_model")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT REPLACE(MODEL_CODE,' ','-'),REPLACE(DESCRIPTION,' ','-') FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
					" WHERE UPPER(MODEL_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			//---------------------  ID  		:Approve Quatation Process---------------------------------//
			//---------------------Purpose 	:Extract data------------------------------------------------
			//---------------------Name     :delanjli----------------------------------------------------
			//---------------------Date     :28-08-2006--------------------------------------------------
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_approve_quotations_price_no")){
				String m_val = req.getParameter("data_val");
				
				rs=stmt.executeQuery("SELECT A.QUOTATION_NO,C.INQUIRY_NO,A.PRICING_NO "+
					"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION_DET A,"+m_schema_name+".AF_MK_PRO_PRICING B,"+m_schema_name+".AF_MK_PRO_QUOTATION C "+
					"WHERE A.QUOTATION_NO=C.QUOTATION_NO AND A.PRICING_NO=B.PRICING_NO AND A.QUOTATION_NO =UPPER('"+ m_val +"') ");
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_approve_quotations_sysdate")){
				
				
				rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'dd'),"+
					"TO_CHAR(SYSDATE,'mm'),"+
					"TO_CHAR(SYSDATE,'yyyy')"+
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
			
			
			
			
			
			/*
		else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_approve_quotations")){
		
		
		
		String m_status = req.getParameter("ac_status");
					
				//	rs=stmt.executeQuery("SELECT DISTINCT A.QUOTATION_NO,A.INQUIRY_NO,CLIENT_NAME,TO_CHAR(NVL(SUM(C.GROSS_AMOUNT),0),'999,999.99') AS GROSS_AMOUNT,A.STATUS,C.PRICING_NO,D.RATE  "+
				//	"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_MK_PRO_QUOTATION_DET C,"+ m_schema_name +".AF_MK_PRO_PRICING D  "+
				//	"WHERE C.PRICING_NO=D.PRICING_NO AND A.QUOTATION_NO=C.QUOTATION_NO AND A.INQUIRY_NO=B.INQUIRY_CODE AND A.STATUS='"+m_status+"' " +
				//	"GROUP BY A.QUOTATION_NO,A.INQUIRY_NO,A.STATUS,C.PRICING_NO,D.RATE,CLIENT_NAME " );
							
				rs=stmt.executeQuery("SELECT DISTINCT A.QUOTATION_NO,INQUIRY_NO,REPLACE(CLIENT_NAME,' ','-'),TO_CHAR(NVL(SUM(GROSS_AMOUNT),0),'999,999.99') AS GROSS_AMOUNT,A.STATUS,APPR_USER,APPR_DATE "+
					"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_MK_PRO_QUOTATION_DET C "+
					"WHERE A.QUOTATION_NO=C.QUOTATION_NO AND INQUIRY_NO=INQUIRY_CODE AND A.STATUS='"+m_status+"' " +
					"GROUP BY A.QUOTATION_NO,INQUIRY_NO,A.STATUS,APPR_USER,APPR_DATE,CLIENT_NAME " );
					
				out.print("<DATA>");
						while(rs.next()){
						
							out.print("<ITEM>");
							
							out.print("<R1>"+rs.getString(1)+"</R1>");
							out.print("<R2>"+rs.getString(2)+"</R2>");
							out.print("<R3>"+rs.getString(3)+"</R3>");
							out.print("<R4>"+rs.getString(4)+"</R4>");
							out.print("<R5>"+rs.getString(5)+"</R5>");
							out.print("<R6>"+rs.getString(6)+"</R6>");
							out.print("<R7>"+rs.getString(6)+"</R7>");
							out.print("</ITEM>");
						}
						out.print("</DATA>");
					
				}
					*/
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_approve_quotations")){
				
				
				
				String m_status = req.getParameter("ac_status");
				
				//	rs=stmt.executeQuery("SELECT DISTINCT A.QUOTATION_NO,A.INQUIRY_NO,CLIENT_NAME,TO_CHAR(NVL(SUM(C.GROSS_AMOUNT),0),'999,999.99') AS GROSS_AMOUNT,A.STATUS,C.PRICING_NO,D.RATE  "+
				//	"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_MK_PRO_QUOTATION_DET C,"+ m_schema_name +".AF_MK_PRO_PRICING D  "+
				//	"WHERE C.PRICING_NO=D.PRICING_NO AND A.QUOTATION_NO=C.QUOTATION_NO AND A.INQUIRY_NO=B.INQUIRY_CODE AND A.STATUS='"+m_status+"' " +
				//	"GROUP BY A.QUOTATION_NO,A.INQUIRY_NO,A.STATUS,C.PRICING_NO,D.RATE,CLIENT_NAME " );
				
				rs=stmt.executeQuery("SELECT DISTINCT A.QUOTATION_NO,INQUIRY_NO,replace(CLIENT_NAME,'null','-'),TO_CHAR(NVL(SUM(GROSS_AMOUNT),0),'999,999,999,999,999,999,999,999,999.99') AS GROSS_AMOUNT,A.STATUS,APPR_USER,APPR_DATE,C.PRICING_NO "+
					"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_MK_PRO_QUOTATION_DET C "+
					"WHERE A.QUOTATION_NO=C.QUOTATION_NO AND INQUIRY_NO=INQUIRY_CODE AND A.STATUS='"+m_status+"' " +
					"GROUP BY A.QUOTATION_NO,INQUIRY_NO,A.STATUS,APPR_USER,APPR_DATE,CLIENT_NAME,C.PRICING_NO " );
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_approve_quotations_price")){
				
				
				
				String m_val = req.getParameter("data_val").trim();
				
				rs=stmt.executeQuery("SELECT A.PRICING_NO,RATE "+
					"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION_DET A,"+m_schema_name+".AF_MK_PRO_PRICING B "+
					"WHERE A.PRICING_NO=B.PRICING_NO AND A.QUOTATION_NO ='"+m_val+"'" );
				
				
				out.print("<DATA>");
				while(rs.next()){
					
					out.print("<ITEM>");
					
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_eng_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				m_status = "I"; // assign "I" to the status variable to avoid the front end validation. (To prevent passed value check for "Y") // added by udara 10-02-2026
				
				rs= stmt.executeQuery ("SELECT INVOICE_NO,ENGINE_NO,APPLICATION_NO, "+
					" NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),APPLICATION_NO) "+ // added by udara 06-11-2013
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					//" WHERE UPPER(ENGINE_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' "); // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(ENGINE_NO, chr(32), '')) = UPPER(REPLACE('"+m_val+"', chr(32), '')) "+
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // commented by udara 10-04-2014 // added by udara on 01-01-2014
					
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) NOT IN ('REJECT','CANCEL') "+ // commented by udara 12-09-2014 // commented by udara 11-11-2014 // added by udara 10-04-2014
					 
					// added by udara 09-12-2014
					" AND ( (SELECT COUNT(APPLICATION_STATUS) "+
			           " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			           " WHERE APPLICATION_NO = A.APPLICATION_NO "+
			           //" AND APPLICATION_STATUS NOT IN ('REJECT','CANCEL')) > 0 "+ // commented by udara 16-06-2015
						" AND APPLICATION_STATUS NOT IN ('REJECT','CANCEL','TERMI','TERMINATED','NORM_TERMI','CANCEL_PO')) > 0 "+ // added by udara 16-06-2015
			           " ) "+
					
					
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) NOT IN ('REJECT','CANCEL','TERMI','TERMINATED','NORM_TERMI') "+ // added by udara 11-11-2014
					" AND ACTIVE_STATUS='"+m_status+"' "); // added by udara 24-12-2013
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>"); //added by nuwan de silva 13-11-07
					out.print("<R4>"+rs.getString(4)+"</R4>"); // added by udara 06-11-2013
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_chassi_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				m_status = "I"; // assign "I" to the status variable to avoid the front end validation. (To prevent passed value check for "Y") // added by udara 10-02-2026
				
				rs= stmt.executeQuery ("SELECT INVOICE_NO,CHASSIS_NO,APPLICATION_NO, "+
					" NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),APPLICATION_NO) "+ // added by udara 06-11-2013
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					//" WHERE UPPER(CHASSIS_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' "); // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(CHASSIS_NO, chr(32), '')) = UPPER(REPLACE('"+m_val+"', chr(32), '')) "+
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014 // commented by udara 10-04-2014
					
					
					// " AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) NOT IN ('REJECT','CANCEL') "+ // commented by udara 10-12-2014 // commented by udara 11-11-2014  // added by udara 10-04-2014
					
					// added by udara 10-12-2014
					" AND ( (SELECT COUNT(APPLICATION_STATUS) "+
			           " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			           " WHERE APPLICATION_NO = A.APPLICATION_NO "+
			           //" AND APPLICATION_STATUS NOT IN ('REJECT','CANCEL')) > 0 "+ // commented by udara 16-06-2015
						" AND APPLICATION_STATUS NOT IN ('REJECT','CANCEL','TERMI','TERMINATED','NORM_TERMI','CANCEL_PO')) > 0 "+ // added by udara 16-06-2015
			           " ) "+
						// end by udara 10-12-2014
					
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) NOT IN ('REJECT','CANCEL','TERMI','TERMINATED','NORM_TERMI') "+ // added by udara 11-11-2014
					" AND ACTIVE_STATUS='"+m_status+"' "); // added by udara 24-12-2013
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>"); //added by nuwan de silva 13-11-07
					out.print("<R4>"+rs.getString(4)+"</R4>"); // added by udara 06-11-2013
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			// added by udara 13-03-2014
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_reg_no")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				m_status = "I"; // assign "I" to the status variable to avoid the front end validation. (To prevent passed value check for "Y") // added by udara 10-02-2026
				
				rs= stmt.executeQuery ("SELECT INVOICE_NO,REG_NO,APPLICATION_NO, "+
					" NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),APPLICATION_NO) "+ // added by udara 06-11-2013
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					//" WHERE UPPER(CHASSIS_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' "); // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(REG_NO, chr(32), '')) = UPPER(REPLACE('"+m_val+"', chr(32), '')) "+
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // commented by udara 10-04-2014 // added by udara on 01-01-2014
					
					
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) NOT IN ('REJECT','CANCEL') "+ // commented by udara 10-12-2014 // commented by udara 11-11-2014 // added by udara 10-04-2014
					
					// added by udara 10-12-2014
					" AND ( (SELECT COUNT(APPLICATION_STATUS) "+
			           " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			           " WHERE APPLICATION_NO = A.APPLICATION_NO "+
			           //" AND APPLICATION_STATUS NOT IN ('REJECT','CANCEL')) > 0 "+ // commented by udara 16-06-2015
						" AND APPLICATION_STATUS NOT IN ('REJECT','CANCEL','TERMI','TERMINATED','NORM_TERMI','CANCEL_PO')) > 0 "+ // added by udara 16-06-2015
			           " ) "+
						// end by udara 10-12-2014
					
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) NOT IN ('REJECT','CANCEL','TERMI','TERMINATED','NORM_TERMI') "+ // added by udara 11-11-2014
					" AND ACTIVE_STATUS='"+m_status+"' "); // added by udara 24-12-2013
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>"); //added by nuwan de silva 13-11-07
					out.print("<R4>"+rs.getString(4)+"</R4>"); // added by udara 06-11-2013
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			// end by udara 13-03-2014
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_pop_totals")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT SUM(A) , SUM(B) , "+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS', '"+m_val+"') , "+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL', '"+m_val+"') ,"+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI', '"+m_val+"')  ,"+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PR', '"+m_val+"'),"+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('SE', '"+m_val+"')  "+ //add by waruna ,"+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('SE', '"+m_val+"')
					"FROM ( "+
					" (SELECT NVL(SUM(TOTAL_AMOUNT),0) A ,0 B "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A WHERE APPLICATION_NO='"+m_val+"' AND ACTIVE_STATUS='Y' )"+ //MODIFIED NUWAN DE SILVA
					" UNION ALL"+
					" (SELECT 0,NVL(SUM(A.GROSS_AMOUNT),0) B "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A WHERE APPLICATION_NO='"+m_val+"' AND "+//modified by nuwan de silva 01-08-07
					" 	A.PRO_INVOICE_NO IN (SELECT DISTINCT INVOICE_NO "+
					" 	FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" 	WHERE APPLICATION_NO='"+m_val+"'  AND ACTIVE_STATUS='Y') "+
					" ))X ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+nf1.format(rs.getDouble(1))+"</R1>");//INVOICE MODIFIED NUWAN DE SILVA 11/05/07
					out.print("<R2>"+nf1.format(rs.getDouble(2))+"</R2>");//PRICING MODIFIED NUWAN DE SILVA 11/05/07
					out.print("<R3>"+rs.getInt(3)+"</R3>");
					out.print("<R4>"+rs.getInt(4)+"</R4>");
					out.print("<R5>"+rs.getInt(5)+"</R5>");
					out.print("<R6>"+rs.getInt(6)+"</R6>");
					out.print("<R7>"+rs.getInt(7)+"</R7>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			
			//------- Purpose : Validate Pricing No in Proforma Screen -------
			//------- Add by  : Yohan Gunarathna ------------------------------
			//------- Date    : 29-09-2006 -------
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_val_pricing")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT PRICING_NO,NVL(VAT_AMOUNT,0),NVL(NET_AMOUNT,0),CURRENCY_CODE FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
					" WHERE UPPER(PRICING_NO)=UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");//modified nuwan de silva 11/05/07
					out.print("<R3>"+nf.format(rs.getDouble(3))+"</R3>");//modified nuwan de silva 11/05/07
					out.print("<R4>"+rs.getString(4)+"</R4>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			//------- Purpose : Validate Pricing No in Proforma Screen -------
			//------- Add by  : Yohan Gunarathna ------------------------------
			//------- Date    : 29-09-2006 -------
			
			//------- Modified by  : Mahela Wickramasekara ------------------------------
			//------- Date    : 24-10-2006 -------
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_Application_Process_val_inq_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");
				
				
				rs= stmt.executeQuery ("SELECT INQUIRY_CODE,NVL(CLIENT_NAME,'-'),NVL(TEL_NO,'-'),NVL(MOBILE_NO,'-'),NVL(FAX_NO,'-'),"+
					"NVL(ADDRESS,'-'),NVL(ADDRESS2,'-'),NVL(CITY_CODE,'-'),NVL(ID_NO,'-'),NVL(LEGAL_ENTITY,'-'), NVL(INITCAP(CLIENT_LAST_NAME),'-') FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
					" WHERE UPPER(INQUIRY_CODE)=UPPER('"+m_val+"') ");
				
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
					out.print("<R11>"+rs.getString(11)+"</R11>"); //Modified by Nuwan De Silva 14-05-07--
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_vendor_creation2")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");						
				
				rs= stmt.executeQuery ("SELECT A.VENDOR_CODE,A.NAME,A.CATEGORY,A.TYPE,A.ACTIVE_STATUS,A.DEFAULT_VALUE, "+
					"B.BRANCH,B.LOCATION_CODE,B.TITLE,B.FIRST_NAME,B.LAST_NAME,B.ID_NO,B.ADDRESS,B.CITY_CODE,B.ACTIVE_STATUS, "+
					"B.DEFAULT_VALUE,C.TEL_NO,C.FAX_NO,C.ACTIVE_STATUS "+
					"FROM "+m_schema_name+".AF_CO_MAS_VENDORS A, "+
					""+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION B,"+m_schema_name+".AF_CO_MAS_VENDOR_LOC_CONTACT C "+
					"WHERE UPPER(A.VENDOR_CODE)=UPPER('"+m_val+"') "+
					"AND A.ACTIVE_STATUS=UPPER('"+m_status+"') "+
					"AND A.VENDOR_CODE=B.VENDOR_CODE "+
					"AND C.VENDOR_CODE=B.VENDOR_CODE "+
					"AND B.BRANCH=C.BRANCH_CODE ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("<R1>"+rs.getString(4)+"</R1>");
					out.print("<R1>"+rs.getString(5)+"</R1>");
					out.print("<R1>"+rs.getString(6)+"</R1>");
					out.print("<R1>"+rs.getString(7)+"</R1>");
					out.print("<R1>"+rs.getString(8)+"</R1>");
					out.print("<R1>"+rs.getString(9)+"</R1>");
					out.print("<R1>"+rs.getString(10)+"</R1>");
					out.print("<R1>"+rs.getString(11)+"</R1>");
					out.print("<R1>"+rs.getString(12)+"</R1>");
					out.print("<R1>"+rs.getString(13)+"</R1>");
					out.print("<R1>"+rs.getString(14)+"</R1>");
					out.print("<R1>"+rs.getString(15)+"</R1>");
					out.print("<R1>"+rs.getString(16)+"</R1>");
					out.print("<R1>"+rs.getString(17)+"</R1>");
					out.print("<R1>"+rs.getString(18)+"</R1>");
					out.print("<R1>"+rs.getString(19)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_performa_invoice")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				//20070124
				rs= stmt.executeQuery ("SELECT A.INVOICE_NO,NVL(A.APPLICATION_NO,'-'),NVL(A.ASSET_ID,'-'),NVL(A.ENGINE_NO,'-'),NVL(A.MODEL_CODE,'-'), "+//5
					"NVL(A.CHASSIS_NO,'-'),NVL(A.REG_NO,'-'),NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'-'),NVL(A.PRICING_NO,'-'),NVL(A.SUB_MODEL_CODE,'-'),NVL(A.COLOUR,'-'),NVL(A.SEATING_CAPACITY,0),NVL(A.NET_PRICE,0), "+//8 // 13
					"NVL(A.VAT,0),NVL(A.TOTAL_AMOUNT,0),NVL(A.TO_BE_DELIVERD_TO,'-'),NVL(A.VALUE,0),NVL(A.CURR_CODE,'-'),NVL(A.BRANCH_ID,'-'),replace(NVL(A.VENDOR_CODE,'-'),'&','$') , "+ // 20
					//"NVL(A.ADDRESS,'-'),NVL(A.CITY_CODE,'-'), "+
					
					"(SELECT DECODE(Y.ADDRESS1||','||Y.ADDRESS2,',','-',Y.ADDRESS1||','||Y.ADDRESS2) "+
					"FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS X, " + m_schema_name + ".AF_CO_MAS_CLIENT Y "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND "+
					"X.APPLICATION_NO = A.APPLICATION_NO), "+
					
					
					"(SELECT NVL(Y.CITY_CODE,'-') "+
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT Y "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND "+
					"X.APPLICATION_NO = A.APPLICATION_NO), "+
					
					
					//"NVL(A.CITY_CODE,'-'), "+
					
					
					"NVL( (SELECT replace(B.NAME,'&','$') FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , "+
					"NVL(INVOICE_DOC_NO,'-'),NVL(FUEL_CONVERTION_STATUS,'-'),TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
					" ( SELECT B.DESCRIPTION FROM   " + m_schema_name + ".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC,  "+ //MODIFED NUWAN DE SILVA 21-05-07
					" ( SELECT C.DESCRIPTION FROM   " + m_schema_name + ".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE ) SUB_MODEL_DESC,  "+ //MODIFED NUWAN DE SILVA 21-05-07
					" A.YEAR_OF_MANUFACTURE, "+//modified by mahela on 05-07-2007
					" A.EXTRAS_INCLUDED, "+ //added by nuwan de silva on 19-09-07
					" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ') CITY_NAME  "+ //added by nuwan de silva on 19-09-07
					" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC  "+
					" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC   "+
					" ,(SELECT CHASSIS_NO FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO = A.APPLICATION_NO) VAL_CHASSIS_NO "+ // 34 // added by udara 29-05-2015
					" ,(SELECT ENGINE_NO FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO = A.APPLICATION_NO) VAL_CHASSIS_NO "+ // 35 // added by udara 29-05-2015
					" ,(SELECT REG_NO FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO = A.APPLICATION_NO) VAL_CHASSIS_NO "+ // 36 // added by udara 29-05-2015
					"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					"WHERE UPPER(INVOICE_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') "); //MODIFED NUWAN DE SILVA 07-12-07
				
				
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
					out.print("<R21>"+rs.getString(21).trim()+"</R21>");
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getString(26)+"</R26>");
					out.print("<R27>"+rs.getString(27)+"</R27>");
					out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("<R29>"+rs.getString(29)+"</R29>");
					
					out.print("<R30>"+rs.getString(30)+"</R30>"); //added by nuwan de silva on 19-09-07
					out.print("<R31>"+rs.getString(31)+"</R31>"); //added by nuwan de silva on 19-09-07
					out.print("<R29>"+rs.getString(32)+"</R29>"); //added by nuwan de silva on 19-09-07
					out.print("<R29>"+rs.getString(33)+"</R29>"); //added by nuwan de silva on 19-09-07
					
					out.print("<R34>"+rs.getString(34)+"</R34>"); // added by udara 29-05-2015
					out.print("<R35>"+rs.getString(35)+"</R35>"); // added by udara 29-05-2015
					out.print("<R36>"+rs.getString(36)+"</R36>"); // added by udara 29-05-2015
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			
			
			//--------------------------------------------------------------------//
			
			
			// added by udara on 01-08-2013
			else if (m_chksql.trim().equals("m_check_vehicle_no_against_application")){	
				
				String m_app_no = req.getParameter("application_no").trim();
				int m_count = 0;
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(REG_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE REG_NO = '"+m_app_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(REG_NO, chr(32), '')) = UPPER(REPLACE('"+m_app_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				
				if(rs.next()){
					m_count = rs.getInt(1);
				}
				
				
				out.print("<DATA>");
				out.print(" <ITEM>");
				out.print("  <R1>"+m_count+"</R1>"); 
				out.print(" </ITEM>");
				out.print("</DATA>");
				
				
			}
			
			else if (m_chksql.trim().equals("m_check_chasis_no_against_application")){	
				
				String m_app_no = req.getParameter("application_no").trim();
				int m_count = 0;
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(CHASSIS_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE CHASSIS_NO = '"+m_app_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(CHASSIS_NO, chr(32), '')) = UPPER(REPLACE('"+m_app_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				if(rs.next()){
					m_count = rs.getInt(1);
				}
				
				out.print("<DATA>");
				out.print(" <ITEM>");
				out.print("  <R1>"+m_count+"</R1>"); 
				out.print(" </ITEM>");
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_check_engine_no_against_application")){	
				
				String m_app_no = req.getParameter("application_no").trim();
				int m_count = 0;
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(ENGINE_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE ENGINE_NO = '"+m_app_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(ENGINE_NO, chr(32), '')) = UPPER(REPLACE('"+m_app_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				if(rs.next()){
					m_count = rs.getInt(1);
				}
				
				out.print("<DATA>");
				out.print(" <ITEM>");
				out.print("  <R1>"+m_count+"</R1>"); 
				out.print(" </ITEM>");
				out.print("</DATA>");
				
			}
			
			// end by udara on 01-08-2013
			
				
				
					
			else if (m_chksql.trim().equals("m_check_vehicle_engine_chasis_no_refinance")){	
				
				String m_app_no = req.getParameter("application_no").trim();
				String m_reg_no = "";
				String m_cha_no = "";
				String m_eng_no = "";
				
				String m_reg_no_fin = "";
				String m_cha_no_fin = "";
				String m_eng_no_fin = "";
				
				int m_reg_no_count = 0;
				int m_engine_no_count = 0;
				int m_chasis_no_count = 0;
				
				rs= stmt.executeQuery (" "+
					" SELECT REG_NO,CHASSIS_NO,ENGINE_NO "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					" WHERE APPLICATION_NO = '"+m_app_no+"' "+
					" ");	
				
				if(rs.next()){
					m_reg_no = rs.getString(1);
					m_cha_no = rs.getString(2);
					m_eng_no = rs.getString(3);
				}
				
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(REG_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE REG_NO = '"+m_reg_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(REG_NO, chr(32), '')) = UPPER(REPLACE('"+m_reg_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND REG_NO <> '-' "+
					//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI') ) "+ // added by udara on 26-08-2013
					" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				
				if(rs.next()){
					m_reg_no_count = rs.getInt(1);
				}
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(CHASSIS_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE CHASSIS_NO = '"+m_cha_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(CHASSIS_NO, chr(32), '')) = UPPER(REPLACE('"+m_cha_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND CHASSIS_NO <> '-' "+
					//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI')   ) "+ // added by udara on 26-08-2013
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				if(rs.next()){
					m_chasis_no_count = rs.getInt(1);
				}
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(ENGINE_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE ENGINE_NO = '"+m_eng_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(ENGINE_NO, chr(32), '')) = UPPER(REPLACE('"+m_eng_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND ENGINE_NO <> '-' "+
					//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI')   ) "+ // added by udara on 26-08-2013
					//" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				if(rs.next()){
					m_engine_no_count = rs.getInt(1);
				}
				
				
				// added by udara on 15-08-2013
				/*if(m_reg_no_count>0){
					
					rs= stmt.executeQuery (" "+
						" SELECT NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),'-') "+
						" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
						//" WHERE REG_NO = '"+m_reg_no+"' "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(REG_NO, chr(32), '')) = UPPER(REPLACE('"+m_reg_no+"', chr(32), ''))  "+ // added by udara on 24-12-2013
						//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+
						" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI')   ) "+
						" AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO) IS NOT NULL "+
						" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
						" ORDER BY NVL(MOD_DATE,ENT_DATE) DESC "+
						" ");
					
					
					if(rs.next()){
						m_reg_no_fin = rs.getString(1);
					}
					
				}
				
				if(m_chasis_no_count>0){
					
					rs= stmt.executeQuery (" "+
						" SELECT NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),'-') "+
						" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
						//" WHERE CHASSIS_NO = '"+m_cha_no+"' "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(CHASSIS_NO, chr(32), '')) = UPPER(REPLACE('"+m_cha_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
						//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+
						" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+
						" AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO) IS NOT NULL "+
						" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
						" ORDER BY NVL(MOD_DATE,ENT_DATE) DESC "+
						" ");
					
					
					if(rs.next()){
						m_cha_no_fin = rs.getString(1);
					}
					
				}
				
				if(m_engine_no_count>0){
					
					rs= stmt.executeQuery (" "+
						" SELECT NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),'-') "+
						" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
						//" WHERE ENGINE_NO = '"+m_eng_no+"' "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(ENGINE_NO, chr(32), '')) = UPPER(REPLACE('"+m_eng_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
						//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+
						" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+
						" AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO) IS NOT NULL "+
						" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
						" ORDER BY NVL(MOD_DATE,ENT_DATE) DESC "+
						" ");
					
					
					if(rs.next()){
						m_eng_no_fin = rs.getString(1);
					}
					
				}*/
				// end by udara on 15-08-2013
				
				out.print("<DATA>");
				out.print(" <ITEM>");
				out.print("  <R1>"+m_reg_no_count+"</R1>"); 
				out.print("  <R2>"+m_chasis_no_count+"</R2>"); 
				out.print("  <R3>"+m_engine_no_count+"</R3>");
				//out.print("  <R4>"+m_reg_no_fin+"</R4>");
				//out.print("  <R5>"+m_cha_no_fin+"</R5>");
				//out.print("  <R6>"+m_eng_no_fin+"</R6>");
				out.print(" </ITEM>");
				out.print("</DATA>");
				
				
			}
			
			// added by udara 18-10-2018
			else if (m_chksql.trim().equals("m_get_pledge_status")){	
				
				String m_finance_no = req.getParameter("finance_no").trim();
				
				rs= stmt.executeQuery (" "+
					" SELECT APPLICATION_NO "+
					" FROM " + m_schema_name + ".AF_MAS_PLEDGE_CONTRACTS "+
					" WHERE PLEDGE_CONTRACT =  '" + m_finance_no + "'  "+
					" ");
				
				res.setContentType("text/xml");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			// end by udara 18-10-2018
			
			
				// added by udara 08-04-2021
			else if (m_chksql.trim().equals("m_get_broker_status")){	
				
				String m_broker_code = req.getParameter("broker_code").trim();
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(BROKER_CODE) "+
					" FROM " + m_schema_name + ".AF_CO_MAS_BROKER "+
					" WHERE BROKER_CODE =  '" + m_broker_code + "'  "+
					" AND ACTIVE_STATUS = 'Y' "+ 
					" ");
				
				res.setContentType("text/xml");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getInt(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			// end by udara 08-04-2021
			
			// added by udara on 05-08-2013
			else if (m_chksql.trim().equals("m_check_vehicle_engine_chasis_no")){	
				
				String m_app_no = req.getParameter("application_no").trim();
				String m_reg_no = "";
				String m_cha_no = "";
				String m_eng_no = "";
				
				String m_reg_no_fin = "";
				String m_cha_no_fin = "";
				String m_eng_no_fin = "";
				
				int m_reg_no_count = 0;
				int m_engine_no_count = 0;
				int m_chasis_no_count = 0;
				
				rs= stmt.executeQuery (" "+
					" SELECT REG_NO,CHASSIS_NO,ENGINE_NO "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					" WHERE APPLICATION_NO = '"+m_app_no+"' "+
					" ");	
				
				if(rs.next()){
					m_reg_no = rs.getString(1);
					m_cha_no = rs.getString(2);
					m_eng_no = rs.getString(3);
				}
				
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(REG_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE REG_NO = '"+m_reg_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(REG_NO, chr(32), '')) = UPPER(REPLACE('"+m_reg_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND REG_NO <> '-' "+
					//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+ // added by udara on 26-08-2013
					" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				
				if(rs.next()){
					m_reg_no_count = rs.getInt(1);
				}
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(CHASSIS_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE CHASSIS_NO = '"+m_cha_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(CHASSIS_NO, chr(32), '')) = UPPER(REPLACE('"+m_cha_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND CHASSIS_NO <> '-' "+
					//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+ // added by udara on 26-08-2013
					" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				if(rs.next()){
					m_chasis_no_count = rs.getInt(1);
				}
				
				rs= stmt.executeQuery (" "+
					" SELECT COUNT(ENGINE_NO) "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE ENGINE_NO = '"+m_eng_no+"' "+ // commented by udara 24-12-2013
					" WHERE UPPER(REPLACE(ENGINE_NO, chr(32), '')) = UPPER(REPLACE('"+m_eng_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
					" AND ENGINE_NO <> '-' "+
					//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+ // added by udara on 05-08-2013
					" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+ // added by udara on 26-08-2013
					" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
					" ");	
				
				if(rs.next()){
					m_engine_no_count = rs.getInt(1);
				}
				
				
				// added by udara on 15-08-2013
				if(m_reg_no_count>0){
					
					rs= stmt.executeQuery (" "+
						" SELECT NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),'-') "+
						" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
						//" WHERE REG_NO = '"+m_reg_no+"' "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(REG_NO, chr(32), '')) = UPPER(REPLACE('"+m_reg_no+"', chr(32), ''))  "+ // added by udara on 24-12-2013
						//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+
						" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+
						" AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO) IS NOT NULL "+
						" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
						" ORDER BY NVL(MOD_DATE,ENT_DATE) DESC "+
						" ");
					
					
					if(rs.next()){
						m_reg_no_fin = rs.getString(1);
					}
					
				}
				
				if(m_chasis_no_count>0){
					
					rs= stmt.executeQuery (" "+
						" SELECT NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),'-') "+
						" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
						//" WHERE CHASSIS_NO = '"+m_cha_no+"' "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(CHASSIS_NO, chr(32), '')) = UPPER(REPLACE('"+m_cha_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
						//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+
						" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+
						" AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO) IS NOT NULL "+
						" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
						" ORDER BY NVL(MOD_DATE,ENT_DATE) DESC "+
						" ");
					
					
					if(rs.next()){
						m_cha_no_fin = rs.getString(1);
					}
					
				}
				
				if(m_engine_no_count>0){
					
					rs= stmt.executeQuery (" "+
						" SELECT NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO),'-') "+
						" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
						//" WHERE ENGINE_NO = '"+m_eng_no+"' "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(ENGINE_NO, chr(32), '')) = UPPER(REPLACE('"+m_eng_no+"', chr(32), ''))  "+ // added by udara 24-12-2013
						//" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'TERMI'  ) "+
						" AND APPLICATION_NO IN (SELECT APPLICATION_NO FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_STATUS = 'ACTIVATED'  ) "+
						" AND " + m_schema_name + ".AF_CO_GET_FINANCE_NO(APPLICATION_NO) IS NOT NULL "+
						" AND "+m_schema_name+".AF_CO_GET_APP_STATUS_2(APPLICATION_NO) <> 'REJECT' "+ // added by udara on 01-01-2014
						" ORDER BY NVL(MOD_DATE,ENT_DATE) DESC "+
						" ");
					
					
					if(rs.next()){
						m_eng_no_fin = rs.getString(1);
					}
					
				}
				// end by udara on 15-08-2013
				
				out.print("<DATA>");
				out.print(" <ITEM>");
				out.print("  <R1>"+m_reg_no_count+"</R1>"); 
				out.print("  <R2>"+m_chasis_no_count+"</R2>"); 
				out.print("  <R3>"+m_engine_no_count+"</R3>");
				out.print("  <R4>"+m_reg_no_fin+"</R4>");
				out.print("  <R5>"+m_cha_no_fin+"</R5>");
				out.print("  <R6>"+m_eng_no_fin+"</R6>");
				out.print(" </ITEM>");
				out.print("</DATA>");
				
				
			}
			// end by udara on 05-08-2013
			
			//added by prabash on 30-04-2012---**
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_change_invoice_details2")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				//20070124
				rs= stmt.executeQuery ("SELECT A.INVOICE_NO,NVL(A.APPLICATION_NO,'-'),NVL(A.ASSET_ID,'-'),NVL(A.ENGINE_NO,'-'),NVL(A.MODEL_CODE,'-'), "+//5
					"NVL(A.CHASSIS_NO,'-'),NVL(A.REG_NO,'-'),NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'-'),NVL(A.PRICING_NO,'-'),NVL(A.SUB_MODEL_CODE,'-'),NVL(A.COLOUR,'-'),NVL(A.SEATING_CAPACITY,0),NVL(A.NET_PRICE,0), "+//8
					"NVL(A.VAT,0),NVL(A.TOTAL_AMOUNT,0),NVL(A.TO_BE_DELIVERD_TO,'-'),NVL(A.VALUE,0),NVL(A.CURR_CODE,'-'),NVL(A.BRANCH_ID,'-'),replace(NVL(A.VENDOR_CODE,'-'),'&','$') , "+
					//"NVL(A.ADDRESS,'-'),NVL(A.CITY_CODE,'-'), "+
					"(SELECT DECODE(Y.ADDRESS1||','||Y.ADDRESS2,',','-',Y.ADDRESS1||','||Y.ADDRESS2) "+
					"FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS X, " + m_schema_name + ".AF_CO_MAS_CLIENT Y "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND "+
					"X.APPLICATION_NO = A.APPLICATION_NO), "+
					"(SELECT NVL(Y.CITY_CODE,'-') "+
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT Y "+
					"WHERE X.CLIENT_CODE = Y.CLIENT_CODE AND "+
					"X.APPLICATION_NO = A.APPLICATION_NO), "+
					"NVL( (SELECT replace(B.NAME,'&','$') FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , "+
					"NVL(INVOICE_DOC_NO,'-'),NVL(FUEL_CONVERTION_STATUS,'-'),TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
					" ( SELECT B.DESCRIPTION FROM   " + m_schema_name + ".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC,  "+ //MODIFED NUWAN DE SILVA 21-05-07
					" ( SELECT C.DESCRIPTION FROM   " + m_schema_name + ".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE ) SUB_MODEL_DESC,  "+ //MODIFED NUWAN DE SILVA 21-05-07
					" A.YEAR_OF_MANUFACTURE, "+//modified by mahela on 05-07-2007
					" A.EXTRAS_INCLUDED, "+ //added by nuwan de silva on 19-09-07
					" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ') CITY_NAME  "+ //added by nuwan de silva on 19-09-07
					" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC  "+
					" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC,NVL(B.SUM_INSURED,0),B.AREA,B.POLICE,B.OWNER_ADDRESS,B.COLLECTON_SECURITY,B.LIC_AUTH,B.VEHICAL_AGA   "+
					"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_SUM_INSURED_DETAILS B "+
					"WHERE UPPER(A.INVOICE_NO)=UPPER('"+m_val+"') AND ACTIVE_STATUS=('"+m_status+"') AND A.INVOICE_NO=B.INVOICE_NO"); //MODIFED NUWAN DE SILVA 07-12-07
				
				res.setContentType("text/xml");
				
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
					out.print("<R21>"+rs.getString(21).trim()+"</R21>");
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("<R26>"+rs.getString(26)+"</R26>");
					out.print("<R27>"+rs.getString(27)+"</R27>");
					out.print("<R28>"+rs.getString(28)+"</R28>");
					out.print("<R29>"+rs.getString(29)+"</R29>");
					
					out.print("<R30>"+rs.getString(30)+"</R30>"); //added by nuwan de silva on 19-09-07
					out.print("<R31>"+rs.getString(31)+"</R31>"); //added by nuwan de silva on 19-09-07
					out.print("<R29>"+rs.getString(32)+"</R29>"); //added by nuwan de silva on 19-09-07
					out.print("<R29>"+rs.getString(33)+"</R29>"); //added by nuwan de silva on 19-09-07
					
					out.print("<R34>"+rs.getString(34)+"</R34>"); //added by Prabash de silva on 30-04-2012
					out.print("<R35>"+rs.getString(35)+"</R35>"); //added by Prabash de silva on 30-04-2012
					out.print("<R36>"+rs.getString(36)+"</R36>"); //added by Prabash de silva on 30-04-2012
					out.print("<R37>"+rs.getString(37)+"</R37>"); //added by Prabash de silva on 30-04-2012
					out.print("<R38>"+rs.getString(38)+"</R38>"); //added by Prabash de silva on 30-04-2012
					out.print("<R39>"+rs.getString(39)+"</R39>"); //added by Prabash de silva on 30-04-2012
					out.print("<R40>"+rs.getString(40)+"</R40>"); //added by Prabash de silva on 30-04-2012
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}	
			//--------------------------------------------------------------------//
			
			
			//added by prabash on25-04-2012----*
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_performa_sumins")){
				//	res.setContentType("text/html");
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				
				//20070124
				/*rs= stmt.executeQuery (" SELECT SUM_INSURED"+ //,AREA,POLICE,OWNER_ADDRESS,COLLECTON_SECURITY,LIC_AUTH,VEHICAL_AGA  "+ 
			//	out.println (" SELECT SUM_INSURED,AREA,POLICE,OWNER_ADDRESS,COLLECTON_SECURITY,LIC_AUTH,VEHICAL_AGA  "+ 
									" FROM "+m_schema_name+".AF_CO_SUM_INSURED_DETAILS  "+
								" WHERE UPPER(INVOICE_NO)=UPPER('"+m_val+"')  ");*/
				
				
				rs= stmt.executeQuery ("SELECT NVL(A.SUM_INSURED,0),AREA,POLICE,OWNER_ADDRESS,COLLECTON_SECURITY,LIC_AUTH,VEHICAL_AGA   "+//5
					"FROM "+m_schema_name+".AF_CO_SUM_INSURED_DETAILS A "+
					"WHERE UPPER(A.INVOICE_NO)=UPPER('"+m_val+"')  "); //MODIFED NUWAN DE SILVA 07-12-07
				
				
				
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
			//---------------------------------*
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_valuer")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");						
				
				rs= stmt.executeQuery ("SELECT VALUER_CODE,NVL((FIRST_NAME || ' ' || LAST_NAME),'-') FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
					" WHERE UPPER(VALUER_CODE)=UPPER('"+m_val+"') AND ACTIVE_STATUS='Y' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>"); //added by nuwan de silva 18-07-07
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}				
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_val_vendor_branch_location")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");						
				
				rs= stmt.executeQuery ("SELECT BRANCH,LOCATION_CODE,ADDRESS FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+ // LOCATION CHANGED TO ADDRESS BY ASHINI
					" WHERE UPPER(BRANCH)=UPPER('"+m_val+"') AND ACTIVE_STATUS='Y' ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//added by Prabash on 17-05-2012------**
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_blacklist_reg_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");						
				
				//rs= stmt.executeQuery ("SELECT REG_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+ 
				//	" WHERE UPPER(REG_NO)=UPPER('"+m_val+"') AND STATUS='B' ");
				
				// commented by udara 31-10-2013
				
				rs= stmt.executeQuery (" "+
						" SELECT A.REG_NO, "+
						" NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(B.APPLICATION_NO),B.APPLICATION_NO) "+
						" FROM  "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						//" WHERE UPPER(A.REG_NO) = UPPER('"+m_val+"') "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(A.REG_NO, chr(32), '')) = UPPER(REPLACE('"+m_val+"', chr(32), '')) "+ // added by udara 24-12-2013
						" AND   A.STATUS='B' "+
						//" AND   UPPER(A.REG_NO) = UPPER(B.REG_NO) "+ // commented by udara 24-12-2013
						" AND   UPPER(REPLACE(A.REG_NO, chr(32), '')) = UPPER(REPLACE(B.REG_NO, chr(32), '')) "+ // added by udara 24-12-2013
						" ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>"); // added by udara on 06-11-2013
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
	
				
			}
			
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_blacklist_engin_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");						
				
				//rs= stmt.executeQuery ("SELECT ENGINE_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+ 
				//	" WHERE UPPER(ENGINE_NO)=UPPER('"+m_val+"') AND STATUS='B' ");

				// commented by udara on 31-10-2013
				
				rs= stmt.executeQuery (" "+
						" SELECT A.ENGINE_NO, "+
						" NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(B.APPLICATION_NO),B.APPLICATION_NO) "+
						" FROM  "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						//" WHERE UPPER(A.ENGINE_NO) = UPPER('"+m_val+"') "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(A.ENGINE_NO, chr(32), '')) = UPPER(REPLACE('"+m_val+"', chr(32), '')) "+ // added by udara 24-12-2013
						" AND   A.STATUS='B' "+
						" AND   UPPER(A.ENGINE_NO) = UPPER(B.ENGINE_NO) "+
						" ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>"); // added by udara on 06-11-2013
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
				
			}
			
			
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_performa_invoice_blacklist_chass_no")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");						
				
				
				//rs= stmt.executeQuery ("SELECT CHASSIS_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+ 
				//	" WHERE UPPER(CHASSIS_NO)=UPPER('"+m_val+"') AND STATUS='B' ");
				
				// commented by udara 31-10-2013
				
				rs= stmt.executeQuery (" "+
						" SELECT A.CHASSIS_NO, "+
						" NVL(" + m_schema_name + ".AF_CO_GET_FINANCE_NO(B.APPLICATION_NO),B.APPLICATION_NO) "+
						" FROM  "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						//" WHERE UPPER(A.CHASSIS_NO) = UPPER('"+m_val+"') "+ // commented by udara 24-12-2013
						" WHERE UPPER(REPLACE(A.CHASSIS_NO, chr(32), '')) = UPPER(REPLACE('"+m_val+"', chr(32), '')) "+ // added by udara 24-12-2013
						" AND   A.STATUS='B' "+
						" AND   UPPER(A.CHASSIS_NO) = UPPER(B.CHASSIS_NO) "+
						" ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>"); // added by udara on 06-11-2013
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
				
				
				
				
			}
			
			
			//------------------------------------**
			
			
			
			else if (m_chksql.trim().equals("LAKDL_AF_MK_display_inspection_and_valuation_report_chk_valudate")){
				
				String m_val = req.getParameter("data_val").trim();
				
				rs= stmt.executeQuery ("SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE('"+m_val+"','DD-MM-YYYY') FROM DUAL ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getInt(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//Added by Chandana on 30/11/2007	
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_mkt_officer")){
				
				String m_val    = req.getParameter("data_val").trim(); 
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT USER_ID "+
					" FROM "+m_schema_name+".CO_CO_MAS_USER "+
					" WHERE ACTIVE_STATUS ='"+m_status+"' AND "+
					" UPPER(USER_ID) = UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}	
			
			// added by udara 18-08-2017
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_mkt_officer_app")){
				
				String m_val    = req.getParameter("data_val").trim(); 
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery (" SELECT EMP_CODE "+
					" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
					" WHERE ACTIVE_STATUS ='"+m_status+"' AND "+
					" UPPER(EMP_CODE) = UPPER('"+m_val+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			// end by udara 18-08-2017
			
			
			//////////////////////////apllcation process//////////////////////////////////////////
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				
				
				rs= stmt.executeQuery ("SELECT CLIENT_CODE, FULL_NAME, NVL(TEL_NO,'-'), NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) "+ //MODIFIED NUWAN DE SILVA 04-06-07
					" ,"+m_schema_name+".AF_CO_CHK_GUARANTER_EXIST(CLIENT_CODE) "+ // added by udara 06-01-2013
					"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					"	 WHERE CLIENT_CODE =('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' "+
					//"    AND "+m_schema_name+".AF_CO_CHK_GUARANTER_EXIST(CLIENT_CODE) = 'NO'  "+ // added by udara 03-01-2013
					"  ORDER BY FULL_NAME "); 		
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>"); // added by udara 06-01-2013
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_status2 = req.getParameter("ac_status2");
				
				
				/*	rs= stmt.executeQuery ("SELECT APPLICATION_NO,NVL(FACILITY_NO,'-'),CLIENT_CODE,NVL(CO_APPLICANT,'-'),NVL(INQUARY_NO,'-'),"+
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE,TRANSACTION_TYPE "+
														    "	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
														    "	WHERE UPPER(APPLICATION_NO) =UPPER('"+m_val+"') AND  APPLICATION_STATUS  IN('"+m_status+"','"+m_status2+"')  "); 
														
				*/						
				
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO, "+
					" NVL(A.FACILITY_NO,'-') FACILITY_NO, "+
					" NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') APPLICANT_NAME, "+
					" NVL(A.CO_APPLICANT,'-') CO_APPLICANT,"+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(A.CLIENT_CODE),'-') CLIENT_TYPE, "+
					" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') MK_OFFICER, "+
					" A.TRANSACTION_TYPE, "+
					" NVL(A.INSURANCE_DONE_BY,'-') INSURANCE_DONE_BY,   "+
					" NVL(A.PRIORITY,'-') PRIORITY,   "+
					" NVL(B.REMARK,'-') REMARK ,"+
					" NVL(A.BRANCH_CODE,'-') BRANCH_CODE,  "+ //added by nuwan de silva 0n 25-06-07
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-') BRANCH_DESC, "+ //added by nuwan de silva 0n 25-06-07
					
					
					//      " B.FULL_NAME FULL_NAME, "+ 
					//     " B.TEL_NO TEL_NO,  "+
					//     " B.NIC_NO NIC_NO  "+
					" DECODE(TER_TYPE,NULL,'-',"+m_schema_name+".AF_CO_GET_TERMINATION_DESC(TER_TYPE)),"+
					" TERMINATION_NO,PRE_APPLICATION_NO,NVL(TER_TYPE,'-'), "+
					" "+m_schema_name+".AF_CO_GET_TERMINATED_AMOUNT(TERMINATION_NO) , "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE(PRE_APPLICATION_NO), "+
					" NVL(A.LEAD_SOURCE_CATEGORY,'N/A') LEAD_SOURCE_CATEGORY ,"+ //added by nuwan de silva on 19-11-2007
					" NVL(A.LEAD_SOURCE_NAME,' ') LEAD_SOURCE_NAME ,"+	           //added by nuwan de silva on 19-11-2007
					" NVL(A.DIVISION_CODE,' ') DIVISION_CODE, "+	           //added by nuwan de silva on 27-11-2007
					" NVL("+m_schema_name+".af_co_get_MK_OFFICER_ID(A.INQUARY_NO),''), "+ //Added by Chandana on 30/11/2007
					" NVL(A.INSURANCE_OFFICER,'-'), "+
					" NVL(A.FINANCE_NO,'-'), "+  //Added by prabash on 13/07/2013
					" NVL((SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ),A.LEAD_SOURCE_NAME) BROKER_CODE  "+ // thamali 2013.08.29
					" ,NVL(A.RE_FIN_NO,'-'),   "+ // 29 added by udara 17-03-2014
					//" NVL("+m_schema_name+".AF_CO_GET_CLOSE_REC_AMNT(A.RE_FIN_NO),0), "+ // 30 added by udara 07-05-2014 // commented by udara 20-02-2017
					" NVL("+m_schema_name+".AF_CO_GET_REFIN_REC_AMOUNT(A.RE_FIN_NO),0), "+ // 30  added by udara 20-02-2017
					" NVL("+m_schema_name+".AF_MAS_INSURANCE_COUNT(A.FINANCE_NO),0),  "+ // 31 added by udara 11-12-2015
					//" NVL("+m_schema_name+".AF_GET_TOTAL_ARREARS("+m_schema_name+".AF_CO_GET_FINANCE_NO(A.PRE_APPLICATION_NO),TO_CHAR(SYSDATE,'DD-MM-YYYY'),'LAKDLALL'),0)  "+ // 32 added by udara 01-01-2016
					" NVL("+m_schema_name+".AF_CO_GET_BAL_TRANSFER("+m_schema_name+".AF_CO_GET_FINANCE_NO(A.PRE_APPLICATION_NO),TO_CHAR(SYSDATE,'DD-MM-YYYY'),'LAKDLALL'),0)  "+ // 32 added by udara 08-02-2016
					//" NVL("+m_schema_name+".af_co_get_arreas_3(A.RE_FIN_NO),0)  "+  //  32 added by udara 01-01-2016
					" ,A.TRANSACTION_TYPE TRANSACTION_TYPE  "+ // 33 added by udara 18-10-2018
					" ,NVL("+m_schema_name+".AF_CO_GET_PLEDGE_CONTRACT(A.APPLICATION_NO,NULL),'-') PLEDGE_CONTRACT  "+ // 34 added by udara 18-10-2018 
					" ,NVL("+m_schema_name+".AF_CO_GET_VENDOR_CODE_HG(A.APPLICATION_NO),'-') VENDOR_CODE  "+  //added by kasun on 26-11-2024
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					"(SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,M.REMARK  "+
					"  FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS X,LAKDL.AF_CO_MAS_CLIENT V, LAKDL.AF_CO_PRO_APPLICATION_APPROVAL M  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE AND "+
					"  X.APPLICATION_NO=M.APPLICATION_NO "+
					//  "  AND V.ACTIVE_STATUS=('"+vector.elementAt(2)+"')  "+
					"  ) B  "+
					"  WHERE A.APPLICATION_NO =B.APPLICATION_NO(+)  "+
					"  AND   "+
					"  (UPPER(B.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  B.TEL_NO =UPPER('"+m_val+"') OR  "+
					"  B.NIC_NO =UPPER('"+m_val+"') OR  "+
					"  A.APPLICATION_NO =UPPER('"+m_val+"')  "+
					"  )  "+
					"  AND A.APPLICATION_STATUS IN('"+m_status+"','"+m_status2+"')  "+
					"  ORDER BY A.APPLICATION_NO DESC  ");					
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4).replace('&','$')+"</R4>"); //Modifed Nuwan De Silva 01-06-07
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6).replace('&','$')+"</R6>"); //Modifed Nuwan De Silva 01-06-07
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>"); //added by nuwan de silva 25-06-07
					out.print("<R15>"+rs.getString(15)+"</R15>"); //added by nuwan de silva 25-06-07
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
					out.print("<R27>"+rs.getString(27)+"</R27>");  //added by prabash de silva 13-07-2012
					out.print("<R28>"+rs.getString(28)+"</R28>");  //added by thamali 2013.08.29
					out.print("<R29>"+rs.getString(29)+"</R29>");  // added by udara 17-03-2014
					out.print("<R30>"+rs.getString(30)+"</R30>");  // added by udara 07-05-2014
					out.print("<R31>"+rs.getString(31)+"</R31>");  // added by udara 11-12-2015
					out.print("<R32>"+rs.getString(32)+"</R32>");  // added by udara 01-01-2016
					out.print("<R33>"+rs.getString("TRANSACTION_TYPE")+"</R33>");  // added by udara 18-10-2018
					out.print("<R34>"+rs.getString("PLEDGE_CONTRACT")+"</R34>");  // added by udara 18-10-2018
					out.print("<R35>"+rs.getString("VENDOR_CODE")+"</R35>"); //added by kasun on 26-11-2024
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//ADDED MILINDA 2013-10-15
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_CK")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_status2 = req.getParameter("ac_status2");
				
				
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO, "+
					" NVL(A.FACILITY_NO,'-') FACILITY_NO, "+
					" NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') APPLICANT_NAME, "+
					" NVL(A.CO_APPLICANT,'-') CO_APPLICANT,"+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
					" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(A.CLIENT_CODE),'-') CLIENT_TYPE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CREDIT_OFFICER_ID(A.INQUARY_NO),'-') CR_OFFICER, "+
					" A.TRANSACTION_TYPE, "+
					" NVL(A.INSURANCE_DONE_BY,'-') INSURANCE_DONE_BY,   "+
					" NVL(A.PRIORITY,'-') PRIORITY,   "+
					" NVL(B.REMARK,'-') REMARK ,"+
					" NVL(A.BRANCH_CODE,'-') BRANCH_CODE,  "+ //added by nuwan de silva 0n 25-06-07
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-') BRANCH_DESC, "+ //added by nuwan de silva 0n 25-06-07
					
					
					//      " B.FULL_NAME FULL_NAME, "+ 
					//     " B.TEL_NO TEL_NO,  "+
					//     " B.NIC_NO NIC_NO  "+
					" DECODE(TER_TYPE,NULL,'-',"+m_schema_name+".AF_CO_GET_TERMINATION_DESC(TER_TYPE)),"+
					" TERMINATION_NO,PRE_APPLICATION_NO,NVL(TER_TYPE,'-'), "+
					" "+m_schema_name+".AF_CO_GET_TERMINATED_AMOUNT(TERMINATION_NO) , "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE(PRE_APPLICATION_NO), "+
					" NVL(A.LEAD_SOURCE_CATEGORY,'N/A') LEAD_SOURCE_CATEGORY ,"+ //added by nuwan de silva on 19-11-2007
					" NVL(A.LEAD_SOURCE_NAME,' ') LEAD_SOURCE_NAME ,"+	           //added by nuwan de silva on 19-11-2007
					" NVL(A.DIVISION_CODE,' ') DIVISION_CODE, "+	           //added by nuwan de silva on 27-11-2007
					" NVL("+m_schema_name+".af_co_get_MK_OFFICER_ID(A.INQUARY_NO),''), "+ //Added by Chandana on 30/11/2007
					" NVL(A.INSURANCE_OFFICER,'-'), "+
					" NVL(A.FINANCE_NO,'-'), "+  //Added by prabash on 13/07/2013
					" NVL((SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ),A.LEAD_SOURCE_NAME) BROKER_CODE  "+ // thamali 2013.08.29

					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					"(SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,M.REMARK  "+
					"  FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS X,LAKDL.AF_CO_MAS_CLIENT V, LAKDL.AF_CO_PRO_APPLICATION_APPROVAL M  "+
					"  WHERE V.CLIENT_CODE=X.CLIENT_CODE AND "+
					"  X.APPLICATION_NO=M.APPLICATION_NO "+
					//  "  AND V.ACTIVE_STATUS=('"+vector.elementAt(2)+"')  "+
					"  ) B  "+
					"  WHERE A.APPLICATION_NO =B.APPLICATION_NO(+)  "+
					"  AND   "+
					"  (UPPER(B.FULL_NAME)=UPPER('"+m_val+"') OR  "+
					"  UPPER(A.CLIENT_CODE)=UPPER('"+m_val+"') OR  "+
					"  B.TEL_NO =UPPER('"+m_val+"') OR  "+
					"  B.NIC_NO =UPPER('"+m_val+"') OR  "+
					"  A.APPLICATION_NO =UPPER('"+m_val+"')  "+
					"  )  "+
					"  AND A.APPLICATION_STATUS IN('ACTIVATED')  "+
					"  ORDER BY A.APPLICATION_NO DESC  ");					
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4).replace('&','$')+"</R4>"); //Modifed Nuwan De Silva 01-06-07
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6).replace('&','$')+"</R6>"); //Modifed Nuwan De Silva 01-06-07
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("<R9>"+rs.getString(9)+"</R9>");
					out.print("<R10>"+rs.getString(10)+"</R10>");
					out.print("<R11>"+rs.getString(11)+"</R11>");
					out.print("<R12>"+rs.getString(12)+"</R12>");
					out.print("<R13>"+rs.getString(13)+"</R13>");
					out.print("<R14>"+rs.getString(14)+"</R14>"); //added by nuwan de silva 25-06-07
					out.print("<R15>"+rs.getString(15)+"</R15>"); //added by nuwan de silva 25-06-07
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
					out.print("<R27>"+rs.getString(27)+"</R27>");  //added by prabash de silva 13-07-2012
					out.print("<R28>"+rs.getString(28)+"</R28>");  //added by thamali 2013.08.29
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			/*------------------------------------------------------------------------------------------
			PURPOSE : VALIDATE ASSET ID
			USED IN : ASSET DETAIL OF EQUIPMENT
			--MODIFIED BY NUWAN DE SILVA 26-06-07 9.15AM
			---------------------------------------------------------------------------------------------*/
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_asset_validation")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" INVOICE_NO "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE APPLICATION_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS='Y' AND ASSET_ID=UPPER('"+m_val2+"') "+
					" UNION "+
					" SELECT "+
					" VALUATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
					" WHERE APPLICATION_NO=UPPER('"+m_val+"') AND ACTIVE_STATUS='Y' AND ASSET_ID=UPPER('"+m_val2+"') ");					
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			
			
			//////////////////////////apllcation process//////////////////////////////////////////
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				String m_val3 = req.getParameter("data_val3").trim();
				
				String m_status = req.getParameter("ac_status");
				
				
				
				rs= stmt.executeQuery ("SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO "+
					"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					//"	 WHERE CLIENT_CODE =('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' "+
					//" ORDER BY FULL_NAME "); 		
					
					"  WHERE  "+m_schema_name+".AF_CO_VAL_CLIENT(CLIENT_CODE,'"+m_val2+"','"+m_val3+"')='NO' AND    "+
					" (UPPER(FULL_NAME)=UPPER('"+m_val+"') OR    "+
					" UPPER(CLIENT_CODE)=UPPER('"+m_val+"') OR  "+  
					" TEL_NO=UPPER('"+m_val+"') OR    "+
					" NIC_NO=UPPER('"+m_val+"') OR    "+
					" BUSINESS_CERTIFICATE_NO=UPPER('"+m_val+"') ) "+
					" AND ACTIVE_STATUS=('"+m_status+"') "+
					" ORDER BY FULL_NAME ");
				
				
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_data")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				
				
				rs= stmt.executeQuery ("SELECT A.GUARANTOR_CODE, A.RELATIONSHIP,A.PERIOD, NVL(A.TEL_NO,'-'),B.FULL_NAME, "+
					"  NVL(DECODE(B.CLIENT_TYPE,'I',B.NIC_NO,'C',B.BUSINESS_CERTIFICATE_NO),'-') "+
					"	 FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
					"	 WHERE A.GUARANTOR_CODE=B.CLIENT_CODE AND A.APPLICATION_NO =('"+m_val+"') AND A.ACTIVE_STATUS='"+m_status+"' ORDER BY GUAR_ID "); 
				
				
				
				
				
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Asset_Detail_Equipment")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				//String m_status2 = req.getParameter("ac_status2");
				
				
				rs= stmt.executeQuery ("  SELECT APPLICATION_NO "+
					"	FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
					"	WHERE APPLICATION_NO =('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' AND STATUS <> 'C' "); 
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_make_creation")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT MAKE_CODE,MAKE_DESC,DEFAULT_VALUE  FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
					" WHERE (UPPER(MAKE_CODE)=UPPER('"+m_val+"') OR UPPER(MAKE_DESC)=UPPER('"+m_val+"')) AND ACTIVE_STATUS='"+m_status+"' ");
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_model_creation")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT MODEL_CODE,DESCRIPTION,MAKE_CODE,FUEL_TYPE,TAX_RATE,TAX_FOR_LEASE,DEFAULT_VALUE,ITEM_SUB_CAT  FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
					" WHERE (UPPER(MODEL_CODE)=UPPER('"+m_val+"') OR UPPER(DESCRIPTION)=UPPER('"+m_val+"')) AND ACTIVE_STATUS='"+m_status+"' ");
				
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
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_sub_model")){
				String m_val = req.getParameter("data_val").trim();
				
				String m_status = req.getParameter("ac_status");
				//&& ACTIVE_STATUS='"+m_status+"'
				
				// comment by nuwan de silva on 11-12-2007  don't delete ________________________________________________
				/*rs= stmt.executeQuery ("SELECT A.SUB_CODE,A.MODEL_CODE,A.DESCRIPTION,A.ENGINE_CAPACITY,A.OPTION_TYPE,"+
				"A.COUNTRY_CODE,A.YEAR_OF_MANUFACTURE,A.DEFAULT_VALUE,C.DESCRIPTION FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE A, "+m_schema_name+".AF_CO_MAS_MODEL B, "+m_schema_name+".AF_CO_MAS_FUEL_TYPE C "+
				" WHERE (UPPER(A.SUB_CODE)=UPPER('"+m_val+"') OR UPPER(A.DESCRIPTION)=UPPER('"+m_val+"'))  AND A.MODEL_CODE=B.MODEL_CODE AND B.FUEL_TYPE=C.CODE AND A.ACTIVE_STATUS='"+m_status+"' ");
				*/
				
				rs= stmt.executeQuery ("SELECT A.SUB_CODE,A.MODEL_CODE,A.DESCRIPTION,A.ENGINE_CAPACITY,A.OPTION_TYPE,"+
					"A.COUNTRY_CODE,A.YEAR_OF_MANUFACTURE,A.DEFAULT_VALUE,C.DESCRIPTION FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE A, "+m_schema_name+".AF_CO_MAS_MODEL B, "+m_schema_name+".AF_CO_MAS_FUEL_TYPE C "+
					" WHERE (UPPER(B.MODEL_CODE)=UPPER('"+m_val+"') OR UPPER(B.DESCRIPTION)=UPPER('"+m_val+"'))  AND A.SUB_CODE=B.MODEL_CODE AND B.FUEL_TYPE=C.CODE AND A.ACTIVE_STATUS='"+m_status+"' ");
				
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
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_city")){
				String m_val = req.getParameter("data_val").trim();
				//String m_val2 = req.getParameter("data_val2");
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery("SELECT CITY_CODE,CITY_DESC,DISTRICT_CODE,DEFAULT_VALUE FROM "+m_schema_name+".AF_CO_MAS_CITY "+
					" WHERE UPPER(CITY_CODE)=UPPER('"+m_val+"') "+
					"	AND ACTIVE_STATUS=('"+m_status+"')");
				
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
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Asset_Detail_Equipment_val")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				//String m_status2 = req.getParameter("ac_status2");
				
				
				//					rs= stmt.executeQuery ("  SELECT ASSET_ID,LAKDL.AF_CO_GET_MAKE_CODE(MODEL_CODE),MODEL_CODE,SUB_MODEL_CODE,STATUS,/*SUPPLIER_CODE,*/COST,PURPOSE,/*ADDRESS,CITY_CODE,PERIOD,*/QTY "+
				//														     "	FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
				//														     "	WHERE APPLICATION_NO =('"+m_val+"') AND ACTIVE_STATUS='"+m_status+"' AND STATUS <> 'C' "); 
				
				//MODIFIED BY NUWAN DE SILVA 									
				
				rs= stmt.executeQuery (" SELECT "+
					"				          A.ASSET_ID, "+//1
					"									B.MAKE_CODE, "+//2
					"									INITCAP(B.MAKE_DESC), "+//3
					"									A.MODEL_CODE, "+//4
					"									INITCAP(C.DESCRIPTION), "+ //5
					"									A.SUB_MODEL_CODE, "+//6
					"									INITCAP(D.DESCRIPTION), "+//7
					"									A.STATUS, "+//8
					"									A.COST, "+ //9
					"									A.PURPOSE, "+ //10
					"                 A.QTY,  "+//11
					"                 E.DESCRIPTION , "+//12
					"                 D.YEAR_OF_MANUFACTURE  "+//13
					
					"					FROM  "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
					"								"+m_schema_name+".AF_CO_MAS_MAKE B, "+
					"								"+m_schema_name+".AF_CO_MAS_MODEL C, "+
					"								"+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+ 
					"               " + m_schema_name + ".AF_CO_MAS_ITEM_SUB_CATEGORY E "+
					
					
					"					WHERE A.MODEL_CODE=C.MODEL_CODE AND "+
					"								D.SUB_CODE=A.SUB_MODEL_CODE AND "+ 
					"								C.MAKE_CODE=B.MAKE_CODE AND "+
					"								A.APPLICATION_NO =('"+m_val+"') AND "+
					"	              C.ITEM_SUB_CAT(+)=E.ITEM_SUB_CAT AND "+
					"								A.ACTIVE_STATUS='"+m_status+"' AND A.STATUS <> 'C'  ");
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					//out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					//out.print("<R9>"+rs.getString(9)+"</R9>");
					//out.print("<R10>"+rs.getString(10)+"</R10>");
					//out.print("<R9>"+rs.getString(9)+"</R9>");
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
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_asset_details")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery (" SELECT A.ASSET_ID,NVL(A.MODEL_CODE,''),NVL(A.SUB_MODEL_CODE,''),"+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(A.MODEL_CODE) FUEL_TYPE, "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(A.MODEL_CODE) ITEM_CAT_CODE, "+
					" B.YEAR_OF_MANUFACTURE , "+ 	
					" NVL("+m_schema_name+".AF_CO_GET_MODEL_DESC(A.MODEL_CODE),' ' ) MODLE_DESC  "+
					" ,NVL(B.DESCRIPTION,' ') SUB_MODLE_DESC  "+ //added by nuwan de silva 21-12-07
					" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC  "+ //added by nuwan de silva on 21-12-07
					" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC   "+
					
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
					" WHERE UPPER(A.ASSET_ID)=UPPER('"+m_val+"') AND A.SUB_MODEL_CODE=B.SUB_CODE ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>"); // added by nuwan de silva on 11-12-2007
					out.print("<R8>"+rs.getString(8)+"</R8>"); // added by nuwan de silva on 11-12-2007
					out.print("<R9>"+rs.getString(9)+"</R9>"); // added by nuwan de silva on 11-12-2007
					out.print("<R10>"+rs.getString(10)+"</R10>"); // added by nuwan de silva on 11-12-2007
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}				
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_asset_details")){
				String m_val = req.getParameter("data_val").trim();
				//String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery (" SELECT ASSET_ID,NVL(MODEL_CODE,''),NVL(SUB_MODEL_CODE,''),"+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
					" WHERE UPPER(ASSET_ID)=UPPER('"+m_val+"') ");
				
				
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
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inspection_and_valuation_report")){
				
				String m_val = req.getParameter("data_val").trim();
				//String m_val1 = req.getParameter("data_val1").trim();
				String m_status = req.getParameter("ac_status");
				String m_type = req.getParameter("m_type");//Added By Sandun 16-12-2008
				
				/*	rs= stmt.executeQuery ("SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS,"+
				" TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING,"+
				" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR,FILED_CODE,B.STATUS,REMARK,GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	"+
					" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
					" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,NVL(A.PRO_INVOICE_NO,'-') PRO_INVOICE_NO,NVL(A.VALUER_CODE,'-') VALUER_CODE, "+
					"	NVL(A.YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
					"	NVL(A.CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
					"	NVL(A.FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+
					" (SELECT X.FIRST_NAME FROM "+m_schema_name+".AF_CO_MAS_VALUERS X WHERE X.VALUER_CODE = A.VALUER_CODE) VALUER_NAME "+			
						" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET B"+
						" WHERE A.VALUATION_NO=B.VALUATION_NO AND A.VALUATION_NO=UPPER('"+m_val+"')"+
						" AND A.ASSET_ID=B.ASSET_ID AND ACTIVE_STATUS=('"+m_status+"')");
				*/
				
				if(m_type.equals("CURR")){
					rs= stmt.executeQuery ("SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS,"+
						" TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING,"+
						" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR, "+
						//"FILED_CODE,B.STATUS,REMARK, "+
						"'','','', "+
						"GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	"+
						" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
						" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,NVL(A.PRO_INVOICE_NO,'-') PRO_INVOICE_NO,NVL(A.VALUER_CODE,'-') VALUER_CODE, "+
						"	NVL(A.YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
						"	NVL(A.CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
						"	NVL(A.FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+
						//--------MODIFIED BY : DELANJALI-------------------------
						//--------DATE				: 2007-07-11------------------------
						" "+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE) VALUER_NAME "+
						//-----------------------------------------------------------------
						
						//" (SELECT X.FIRST_NAME FROM "+m_schema_name+".AF_CO_MAS_VALUERS X WHERE X.VALUER_CODE = A.VALUER_CODE) VALUER_NAME "+			
						
						" ,(SELECT C.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=SUB_MODEL_CODE) SUB_MODEL_DESC "+
						" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(MODEL_CODE)),' ') MAKE_DESC  "+ //added by nuwan de silva on 21-12-07
						" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(MODEL_CODE),' ') ITEM_SUB_DESC   "+
						
						" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A "+
						//		","+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET B"+
						" WHERE "+
						//	"A.VALUATION_NO=B.VALUATION_NO AND "+
						" A.VALUATION_NO=UPPER('"+m_val+"')"+
						//	" AND A.ASSET_ID=B.ASSET_ID "+
						"AND ACTIVE_STATUS=('"+m_status+"')");
				}
				
				else if(m_type.equals("PRV")){//Added By Sandun 16-12-2008
					rs= stmt.executeQuery ("SELECT A.VALUATION_NO,A.ASSET_ID,MODEL_CODE,SUB_MODEL_CODE,NVL(REG_NO,'-'),ENGINE_NO,CHASSIS_NO,NOTES,REMARKS,"+
						" TYPE_OF_BODY,NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-'),VALUE,METER_READING,"+
						" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY'),COLOUR, "+
						" '','','', "+
						" GENERAL_INDEX,SEATING_CAPACITY,NO_OF_CYLINDERS,	"+
						" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
						" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE,NVL(A.PRO_INVOICE_NO,'-') PRO_INVOICE_NO,NVL(A.VALUER_CODE,'-') VALUER_CODE, "+
						"	NVL(A.YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
						"	NVL(A.CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
						"	NVL(A.FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+
						" "+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE) VALUER_NAME "+
						" ,(SELECT C.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=SUB_MODEL_CODE) SUB_MODEL_DESC "+
						" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(MODEL_CODE)),' ') MAKE_DESC  "+ //added by nuwan de silva on 21-12-07
						" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(MODEL_CODE),' ') ITEM_SUB_DESC   "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION_BK A "+
						" WHERE "+
						" A.VALUATION_NO=UPPER('"+m_val+"')"+
						" AND ACTIVE_STATUS=('"+m_status+"')");
					
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
					out.print("<R29>"+rs.getString(30)+"</R29>");
					out.print("<R29>"+rs.getString(31)+"</R29>");
					out.print("<R29>"+rs.getString(32)+"</R29>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}						
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inspect2")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				
				rs= stmt.executeQuery ("SELECT A.FILED_CODE,B.DESCRIPTION"+
					" FROM "+m_schema_name+".AF_CO_MAS_FILEDS_APPLICABLE A,"+m_schema_name+".AF_CO_MAS_FILEDS B"+
					" WHERE A.FILED_CODE=B.FILED_CODE"+
					" AND A.ITEM_CATEGORY = UPPER('"+m_val+"') AND A.ACTIVE_STATUS='Y'" );
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					//out.print("<R1>"+rs.getString(3)+"</R1>");
					//	out.print("<R1>"+rs.getString(4)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inspect4")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_item = req.getParameter("data_val1").trim();
				String m_status = req.getParameter("ac_status");
				
				/* rs= stmt.executeQuery ("SELECT A.FILED_CODE,REPLACE(C.DESCRIPTION,' ','-'),STATUS,NVL(REMARK,'-') "+
				" FROM LAKDL.AF_CO_PRO_APP_VALUATION_DET A,LAKDL.AF_CO_PRO_APP_VALUATION B ,LAKDL.AF_CO_MAS_FILEDS C"+
				" WHERE A.VALUATION_NO = UPPER('"+m_val+"') AND A.VALUATION_NO=B.VALUATION_NO AND A.FILED_CODE=C.FILED_CODE"+
	
 				"	UNION "+             
	 			"	SELECT DISTINCT B.FILED_CODE,REPLACE(C.DESCRIPTION,' ','-'),'xx','xx' FROM"+
        " "+m_schema_name+".AF_CO_MAS_FILEDS_APPLICABLE B,"+m_schema_name+".AF_CO_MAS_FILEDS C"+
        " WHERE (B.FILED_CODE NOT IN(SELECT A.FILED_CODE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET A WHERE A.VALUATION_NO LIKE UPPER('"+m_val+"%'))"+
        " AND B.ITEM_CATEGORY = UPPER('"+m_item+"')"+
        " AND B.FILED_CODE=C.FILED_CODE)");
				
				*/
				rs= stmt.executeQuery (" SELECT A.FILED_CODE,REPLACE(C.DESCRIPTION,' ','-'),STATUS,NVL(REMARK,'-')  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET A,"+m_schema_name+".AF_CO_PRO_APP_VALUATION B,"+m_schema_name+".AF_CO_MAS_FILEDS C,"+m_schema_name+".AF_CO_MAS_FILEDS_APPLICABLE V "+
					" WHERE A.VALUATION_NO = UPPER('"+m_val+"') AND A.VALUATION_NO=B.VALUATION_NO AND A.FILED_CODE=C.FILED_CODE "+
					" AND C.ACTIVE_STATUS='Y' "+
					
					// " AND (V.FILED_CODE NOT IN(SELECT A.FILED_CODE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET A WHERE A.VALUATION_NO LIKE UPPER('"+m_val+"%'))) "+
					"  AND V.ITEM_CATEGORY =UPPER('"+m_item+"') "+
					"  AND V.FILED_CODE=C.FILED_CODE "+
					
					"     UNION              "+
					
					" SELECT DISTINCT B.FILED_CODE,REPLACE(C.DESCRIPTION,' ','-'),'xx','xx' FROM "+
					" "+m_schema_name+".AF_CO_MAS_FILEDS_APPLICABLE B,"+m_schema_name+".AF_CO_MAS_FILEDS C "+
					" WHERE (B.FILED_CODE NOT IN(SELECT A.FILED_CODE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET A WHERE A.VALUATION_NO LIKE UPPER('"+m_val+"%')) "+
					" AND B.ITEM_CATEGORY =UPPER('"+m_item+"') "+
					" AND C.ACTIVE_STATUS='Y' "+
					" AND B.FILED_CODE=C.FILED_CODE) ");
				
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					//out.print("<R1>"+rs.getString(5)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inspect3")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT  A.MODEL_CODE,A.MAKE_CODE,B.ITEM_SUB_CAT,C.ITEM_CAT_CODE "+  
					"	FROM "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_MAKE B,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY C"+
					"	WHERE A.MAKE_CODE=B.MAKE_CODE"+
					"	AND B.ITEM_SUB_CAT=C.ITEM_SUB_CAT"+
					"	AND A.MODEL_CODE LIKE UPPER('"+m_val+"%')");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R1>"+rs.getString(2)+"</R1>");
					out.print("<R2>"+rs.getString(3)+"</R2>");
					out.print("<R3>"+rs.getString(4)+"</R3>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}						
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_fual2")){
				
				String m_val = req.getParameter("data_val").trim();
				
				rs=stmt.executeQuery("SELECT A.CODE,A.DESCRIPTION "+
					"FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE A,LAKDL.AF_CO_MAS_MODEL B "+
					"WHERE B.FUEL_TYPE=A.CODE "+
					"AND B.MODEL_CODE LIKE UPPER('"+m_val+"%')"+
					"AND B.ACTIVE_STATUS='Y'");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_inspect1")){
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				
				rs= stmt.executeQuery ("SELECT A.MAKE_CODE,A.MAKE_DESC,A.ITEM_SUB_CAT,C.ITEM_CAT_CODE FROM "+m_schema_name+".AF_CO_MAS_MAKE A,"+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY B,"+
					" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY C"+
					" WHERE A.ITEM_SUB_CAT=C.ITEM_SUB_CAT"+
					" AND C.ITEM_CAT_CODE LIKE UPPER('"+m_val+"%')"+
					"	AND B.ITEM_CAT_CODE=C.ITEM_CAT_CODE");
				
				
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
			
			/*else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MAS_display_approve_quotations_seq")){
			//String m_val = req.getParameter("data_val").trim();
			
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'YYYY')||TO_CHAR(SYSDATE,'MM')||TO_CHAR(SYSDATE,'DD')||'-' "+
			"||LPAD(TO_CHAR(LAKDL.AF_SEQ_QUOTATION_NO.NEXTVAL),4,'0') FROM DUAL " );		 		
			
			
			//rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'YYYY')||TO_CHAR(SYSDATE,'MM')||TO_CHAR(SYSDATE,'DD')||'-'||LPAD(TO_CHAR(LAKDL.AF_SEQ_QUOTATION_NO.NEXTVAL),4,'0') FROM DUAL " );		 		
			
				out.print("<DATA>");
				while(rs.next()){				
							out.print("<ITEM>");
							out.print("<R1>"+rs.getString(1)+"</R1>");
							out.print("</ITEM>");
							}
				out.print("</DATA>");
					
				}*/
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_enable")){
				String m_val = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery ("SELECT "+
					" CLIENT_STATUS, "+
					" GUARANTO_STATUS, "+
					" ASSET_STATUS, "+
					" PRICING_STATUS, "+
					" PRO_FORMA_STATUS, "+
					" VALUATION_STATUS "+
					
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE  APPLICATION_NO=UPPER('"+m_val+"') ");
				
				
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
			
			/*--------------Purpose    :Get The Documets Required For Client
			----------------Created By :Nuwan De Silva  
			----------------Date       :26/12/2006------------------------  */
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_Documents_client")){
				
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_client_code = req.getParameter("data_val_client_code").trim();
				String m_txt_type = req.getParameter("data_val_txt_type").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_Hid_scr_name = req.getParameter("Hid_Scr_name").trim();				
				
				/*rs= stmt.executeQuery ("SELECT "+
					"    CODE, "+
					"    DESCRIPTION, "+
					"    NVL(NULL,'-') REMARK,  "+
							"    NVL(NULL,'-') STATUS,  "+
							"    NVL(NULL,'-') FOLLOWUP_REMARKS, "+
							"    NVL(NULL,'-') REF_NO "+
								" FROM "+
					"    "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
								" WHERE   "+
					"    CODE IN ( "+
					"         SELECT "+ 
					"              CODE "+
					"         FROM "+
					"             "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
					"         WHERE                               "+ 
					"             ENTITY_TYPE=(    "+
							"                      SELECT "+
											" CLIENT_CATEGORY "+
											" FROM "+
											" "+m_schema_name+".AF_CO_MAS_CLIENT "+
											" WHERE "+
											" CLIENT_CODE=UPPER('"+m_client_code+"'))  AND "+
							"             DIVISION_CODE='AF'       AND    "+
					"             ITEM_CAT_CODE IS NULL    AND    "+
					"             ACTIVE_STATUS=('"+m_status+"')        AND    "+
					"             PRODUCT_CODE=('"+m_txt_type+"')          AND    "+
					"             CODE NOT IN ( "+
					"                     SELECT "+ 
					"                        DOCUMENT_TYPE "+
					"                     FROM "+
					"                        "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
					"                     WHERE "+
					"                        APPLICATION_NO=UPPER('"+m_app_no+"')  AND  "+
					"                        CLIENT_CODE=UPPER('"+m_client_code+"') "+
					"                         ) "+
					"            ) AND            "+
					"            DOC_APP_TYPE='CLIENT' AND "+
					"            ACTIVE_STATUS=('"+m_status+"') "+
					
					" UNION "+
					
					" SELECT "+
					"     DOCUMENT_TYPE, "+
					"    (SELECT "+
					"    DESCRIPTION "+ 
					"    FROM "+
					"    "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
					"    WHERE "+
					"    CODE=DOCUMENT_TYPE "+
					"    ) DESCRIPTION, "+
					"    NVL(REMARK,'-') REMARK ,"+
							"    NVL(STATUS,'Y') STATUS , "+
							"    NVL(FOLLOWUP_REMARKS,'-') FOLLOWUP_REMARKS, "+
							"    NVL(REF_NO,'-') REF_NO "+
					" FROM "+
					"    "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
					" WHERE "+
					"    APPLICATION_NO=UPPER('"+m_app_no+"')  AND  "+
					"    CLIENT_CODE=UPPER('"+m_client_code+"') "+
							" ORDER BY STATUS ASC ");
							
							*/
				
				/*2007-02-13
								rs= stmt.executeQuery ("SELECT "+
        "     CODE,DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS, "+
        "     NVL(NULL,'-') FOLLOWUP_REMARKS, NVL(NULL,'-') REF_NO  "+
			  " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
 			  " WHERE   "+
        "     CODE IN ( SELECT  CODE  "+
        "          FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
        "          WHERE  ENTITY_TYPE=(    "+
				"                       SELECT CLIENT_CATEGORY  "+
        "                         FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
        "                         WHERE CLIENT_CODE=UPPER('"+m_client_code+"'))  AND  "+
        "              DIVISION_CODE='AF'       AND     "+
        "              ITEM_CAT_CODE IS NULL    AND     "+
        "              ACTIVE_STATUS=('"+m_status+"')        AND     "+
				"  						FROM_SCREEN_NO <=  "+
				"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"')  "+
				"  						AND TO_SCREEN_NO >=  "+
				"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND  "+
				"            PRODUCT_CODE=('"+m_txt_type+"')          AND    "+ 
        "              CODE NOT IN ( SELECT  DOCUMENT_TYPE  "+
        "                      FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
        "                      WHERE APPLICATION_NO=UPPER('"+m_app_no+"')  AND   "+
        "                         CLIENT_CODE=UPPER('"+m_client_code+"') AND   "+
				" 				PRO_INVOICE_NO IS NULL   "+
        "                          )  "+
        "             ) AND             "+
        "             DOC_APP_TYPE='CLIENT' AND  "+ 
        "             ACTIVE_STATUS=('"+m_status+"')  "+

        "  UNION  "+
        
 "  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK, C.STATUS STATUS,NVL(C.FOLLOWUP_REMARKS,'-') FOLLOWUP_REMARKS,NVL(C.REF_NO,'-') REF_NO  "+
 "  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C "+
 "  WHERE "+
     
 "  B.ENTITY_TYPE=( SELECT CLIENT_CATEGORY  "+
 "                  FROM   "+m_schema_name+".AF_CO_MAS_CLIENT  "+
 "                  WHERE CLIENT_CODE=UPPER('"+m_client_code+"') "+
 "                )  "+
            
 "  AND "+
 "  DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('"+m_status+"') "+
 "  AND C.DOCUMENT_TYPE IN "+
 "  (SELECT CODE "+
 "  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
 "  WHERE FROM_SCREEN_NO <= "+
 "  (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') "+
 "  AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
 "  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND "+
 "  ENTITY_TYPE=( SELECT  "+
 "                CLIENT_CATEGORY  "+
 "                FROM  "+
 "                "+m_schema_name+".AF_CO_MAS_CLIENT  "+
 "                WHERE  "+
 "                CLIENT_CODE=UPPER('"+m_client_code+"') "+
 "                    )  "+
  
 "  AND  PRODUCT_CODE=('"+m_txt_type+"')       "+
 "  AND B.CODE=A.CODE "+
 "  AND ACTIVE_STATUS=('"+m_status+"')) "+
 "  AND C.DOCUMENT_TYPE=A.CODE "+
  " AND C.APPLICATION_NO=UPPER('"+m_app_no+"') "+
  " AND C.CLIENT_CODE=UPPER('"+m_client_code+"') "+
 "  AND C.PRO_INVOICE_NO IS NULL "+
 " ORDER BY STATUS ASC ");

      */
				//******modified :2007-02-13
				//replaced the & with #-----
				
				rs= stmt.executeQuery ("SELECT "+
					"     CODE,replace(DESCRIPTION,'&','$'),NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS, "+
					"     NVL(NULL,'-') FOLLOWUP_REMARKS, NVL(NULL,'-') REF_NO  "+
					" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
					" WHERE   "+
					"     CODE IN ( SELECT  CODE  "+
					"          FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
					"          WHERE  ENTITY_TYPE=(    "+
					"                       SELECT CLIENT_CATEGORY  "+
					"                         FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					"                         WHERE CLIENT_CODE=UPPER('"+m_client_code+"'))  AND  "+
					"              DIVISION_CODE='AF'       AND     "+
					"              ITEM_CAT_CODE IS NULL    AND     "+
					"              ACTIVE_STATUS=('"+m_status+"')        AND     "+
					"  						FROM_SCREEN_NO <=  "+
					"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
					"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"')  "+
					"  						AND TO_SCREEN_NO >=  "+
					"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
					"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND  "+
					"            PRODUCT_CODE=('"+m_txt_type+"')          AND    "+ 
					"              CODE NOT IN ( SELECT  DOCUMENT_TYPE  "+
					"                      FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
					"                      WHERE APPLICATION_NO=UPPER('"+m_app_no+"')  AND   "+
					"                         CLIENT_CODE=UPPER('"+m_client_code+"') AND   "+
					" 				PRO_INVOICE_NO IS NULL   "+
					"                          )  "+
					"             ) AND             "+
					"             DOC_APP_TYPE='CLIENT' AND  "+ 
					"             ACTIVE_STATUS=('"+m_status+"')  "+
					
					"  UNION  "+
					
					"  SELECT DISTINCT C.DOCUMENT_TYPE ,replace(a.DESCRIPTION,'&','$') D, "+//A.DESCRIPTION D,
					"NVL(C.REMARK,'-') REMARK, C.STATUS STATUS,NVL(C.FOLLOWUP_REMARKS,'-') FOLLOWUP_REMARKS,NVL(C.REF_NO,'-') REF_NO  "+
					"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C "+
					"  WHERE "+
					
					"  B.ENTITY_TYPE=( SELECT CLIENT_CATEGORY  "+
					"                  FROM   "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					"                  WHERE CLIENT_CODE=UPPER('"+m_client_code+"') "+
					"                )  "+
					
					"  AND "+
					"  DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('"+m_status+"') "+
					"  AND C.DOCUMENT_TYPE IN "+
					"  (SELECT CODE "+
					"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
					"  WHERE FROM_SCREEN_NO <= "+
					"  (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
					"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') "+
					"  AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
					"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND "+
					"  ENTITY_TYPE=( SELECT  "+
					"                CLIENT_CATEGORY  "+
					"                FROM  "+
					"                "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					"                WHERE  "+
					"                CLIENT_CODE=UPPER('"+m_client_code+"') "+
					"                    )  "+
					
					"  AND  PRODUCT_CODE=('"+m_txt_type+"')       "+
					"  AND B.CODE=A.CODE "+
					"  AND ACTIVE_STATUS=('"+m_status+"')) "+
					"  AND C.DOCUMENT_TYPE=A.CODE "+
					" AND C.APPLICATION_NO=UPPER('"+m_app_no+"') "+
					" AND C.CLIENT_CODE=UPPER('"+m_client_code+"') "+
					"  AND C.PRO_INVOICE_NO IS NULL "+
					" ORDER BY STATUS ASC ");
				
				
				
				
				
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
			
			
			/*--------------Purpose    :Get The Documets Required For Client
		----------------Created By :Nuwan De Silva  
		----------------Date       :26/12/2006------------------------  */
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Procee_Documents_gurantor")){
				
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				/*rs= stmt.executeQuery ("SELECT "+
				" GUARANTOR_CODE "+
				" FROM "+
				" "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
				" WHERE "+
				" 		APPLICATION_NO=UPPER('"+m_app_no+"') AND  "+
				" 		ACTIVE_STATUS=('"+m_status+"') ");
					*/
				
				
				rs= stmt.executeQuery ("SELECT "+					
					" CLIENT_CODE, "+
					" FULL_NAME "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE    "+
					"     CLIENT_CODE IN "+
					"                ( "+
					"              SELECT "+
					"                GUARANTOR_CODE "+
					" 									FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
					"                   WHERE "+
					" 		                  APPLICATION_NO=UPPER('"+m_app_no+"') AND  "+
					" 		                  ACTIVE_STATUS=('"+m_status+"')) ");
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			// added by udara 05-11-2014
			
			else if (m_chksql.trim().equals("m_check_more_than_20")){
				
				String m_app_no = req.getParameter("data_val_app_no").trim();
				
				rs= stmt.executeQuery (" "+
					" SELECT TO_CHAR(SYSDATE,'YYYY'), "+
					" YEAR_OF_MANUFACTURE, "+
					" TO_CHAR(SYSDATE,'YYYY') - NVL(YEAR_OF_MANUFACTURE,'0') GAP "+
					  " FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					  " WHERE  APPLICATION_NO='"+m_app_no+"' "+
					  " AND REG_NO IS NOT NULL "+
					  " AND ROWNUM = 1 "+
						" ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(3)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
				
			}
			
			// end by udara 05-11-2014
			
			
			/*--------------Purpose    :Validate Engine Number - Valuation
			----------------Created By :Nuwan De Silva  
			----------------Date       :26/12/2006------------------------  */
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_engine_no")){
				
				String m_engine_no = req.getParameter("data_val_engine_no").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				//m_status = "I"; // assign "I" to the status variable to avoid the front end validation. (To prevent passed value check for "Y") // added by udara 10-02-2026
				
				rs= stmt.executeQuery ("SELECT "+
					" ENGINE_NO, APPLICATION_NO "+ // added by udara 24-10-2014 // " ENGINE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					" UPPER(ENGINE_NO)      =UPPER('"+m_engine_no+"')  "+
					" AND UPPER(ENGINE_NO) NOT IN (SELECT UPPER(ENGINE_NO) FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE WHERE ENGINE_NO = '"+m_engine_no+"' AND STATUS = 'B' )   "+ // added by udara 28-10-2014
					" AND ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>"); // added by udara 24-10-2014
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			//Added by Mahela on 22-05-2007
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_engine_no2")){
				
				//String m_engine_no = req.getParameter("data_val_engine_no").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("SELECT "+
					" NVL(ENGINE_NO,'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					//" UPPER(ENGINE_NO)      =UPPER('"+m_engine_no+"') AND "+
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
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_engine_chassis_no")){
				
				String m_invoice_no = req.getParameter("data_val").trim();
				//	String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("SELECT "+
					" NVL(ENGINE_NO,'-') ENGINE_NO , "+
					" NVL(CHASSIS_NO,'-') CHASSIS_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE UPPER(INVOICE_NO)=UPPER('"+m_invoice_no+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
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
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				//m_status = "I"; // assign "I" to the status variable to avoid the front end validation. (To prevent passed value check for "Y") // added by udara 10-02-2026
				
				rs= stmt.executeQuery ("SELECT "+
					" CHASSIS_NO, APPLICATION_NO "+ // udara 24-10-2014 // " CHASSIS_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+ 
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					" UPPER(CHASSIS_NO)      =UPPER('"+m_chassis_no+"')  "+
					" AND UPPER(CHASSIS_NO) NOT IN (SELECT UPPER(CHASSIS_NO) FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE WHERE CHASSIS_NO = '"+m_chassis_no+"' AND STATUS = 'B' )   "+ // added by udara 28-10-2014
					" AND ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>"); // udara 24-10-2014
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			// added by udara 14-03-2014
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_reg_no")){
				
				String m_reg_no = req.getParameter("data_val_reg_no").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				//m_status = "I"; // assign "I" to the status variable to avoid the front end validation. (To prevent passed value check for "Y") // added by udara 10-02-2026
				
				rs= stmt.executeQuery ("SELECT "+
					" REG_NO, APPLICATION_NO "+ // mod by udara 24-10-2014 // " REG_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+ 
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					" UPPER(REG_NO)      =UPPER('"+m_reg_no+"')  "+
					" AND UPPER(REG_NO) NOT IN (SELECT UPPER(REG_NO) FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE WHERE REG_NO = '"+m_reg_no+"' AND STATUS = 'B' )   "+ // added by udara 28-10-2014
					" AND ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>"); // added by udara 24-10-2014
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			// end by udara 14-03-2014
			
			
			
			
			
			//Added by Mahela on 22-05-2007
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_chassis_no2")){
				
				//String m_chassis_no = req.getParameter("data_val_chassis_no").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("SELECT "+
					" NVL(CHASSIS_NO,'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+ 
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					//" UPPER(CHASSIS_NO)      =UPPER('"+m_chassis_no+"') AND "+
					" ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			// added by udara 02-01-2014
			
			
			
			// end by udara 02-01-2014
			
			
			//added by Prabash on 24-06-2012-------------**
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_lic_date")){
				
				String lic_date = req.getParameter("data_val_lic_date").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("SELECT "+
					" REG_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+ 
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					" REG_DATE      = TO_DATE('"+lic_date+"','DD-MM-YYYY') AND "+
					" ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			else if (m_chksql.trim().equals("m_LAKDL_AF_MK_display_valuation_report_validate_lic_date2")){
				
				String lic_date = req.getParameter("data_val_lic_date").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_asset_no = req.getParameter("data_val_asset_no").trim();
				String m_model_no = req.getParameter("data_val_model_no").trim();
				String m_sub_model = req.getParameter("data_val_sub_model").trim();
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				rs= stmt.executeQuery ("SELECT "+
					" REG_DATE"+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  "+
					" UPPER(APPLICATION_NO) =UPPER('"+m_app_no+"') AND "+ 
					" UPPER(ASSET_ID)       =UPPER('"+m_asset_no+"')  AND "+
					" UPPER(INVOICE_NO)       =UPPER('"+m_invoice_no+"')  AND "+
					" UPPER(SUB_MODEL_CODE) =UPPER('"+m_sub_model+"') AND "+ 
					" UPPER(MODEL_CODE)     =UPPER('"+m_model_no+"') AND "+
					// " REG_DATE      = TO_DATE('"+lic_date+"','DD-MM-YYYY') AND "+
					" ACTIVE_STATUS  =('"+m_status+"') ");
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			//----Pra---------------------------------------**
			
			
			
			/*--------------Purpose    :Get The Invoice Numbers - Application Processing Doc Req
			----------------Created By :Nuwan De Silva  
			----------------Date       :12/01/2007----------------------------------  */
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_Document_req_get_invoice")){
				
				
				
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				/*		rs= stmt.executeQuery ("SELECT "+
					" 		INVOICE_NO "+ 
						" FROM "+
						" 		"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
						" WHERE "+
						" 	 APPLICATION_NO=UPPER('"+m_app_no+"') AND "+
						" ACTIVE_STATUS  =('"+m_status+"') ");
				*/
				
				//Modified Nuwan De Silva 25-04-2007----------------------------------------------
				rs= stmt.executeQuery (" SELECT "+
					" A.INVOICE_NO, "+
					" B.MAKE_CODE, "+
					" A.MODEL_CODE, "+
					" NVL(A.ENGINE_NO,'-') ENGINE_NO, "+
					" NVL(A.CHASSIS_NO,'-') CHASSIS_NO, "+
					" NVL(A.REG_NO,'-') REG_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B "+
					" WHERE A.MODEL_CODE=B.MODEL_CODE AND UPPER(A.APPLICATION_NO)=UPPER('"+m_app_no+"')  AND A.ACTIVE_STATUS=('"+m_status+"') ");
				
				
				
				
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_Document_req_get_invoice_doc")){
				
				
				String m_invoice_no = req.getParameter("data_val_invoice_no").trim();
				String m_app_no = req.getParameter("data_val_app_no").trim();
				String m_client_code = req.getParameter("data_val_client_code").trim();
				String m_txt_type = req.getParameter("data_val_txt_type").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_Hid_scr_name = req.getParameter("Hid_Scr_name").trim();	
				
				
				/*  rs= stmt.executeQuery ("SELECT "+
				" CODE,  "+
				" DESCRIPTION, "+
				" NVL(NULL,'-') REMARK,   "+
						" NVL(NULL,'-') STATUS,   "+
						" NVL(NULL,'-') FOLLOWUP_REMARKS,  "+
						" NVL(NULL,'-') REF_NO  "+
								" FROM  "+
				"   "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+ 
							" WHERE   "+
				" CODE IN (  "+ 
				"      SELECT   "+
				"           CODE  "+
				"      FROM  "+
				"          "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
				"       WHERE  ITEM_CAT_CODE=( "+
				"                   SELECT "+
						"                     ITEM_CAT_CODE "+
								"  									FROM "+
							"                     "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
								"									 WHERE "+
				"                     ITEM_SUB_CAT=( "+
				"                            SELECT "+
				"                                ITEM_SUB_CAT "+
				"                            FROM "+
				"                                "+m_schema_name+".AF_CO_MAS_MODEL "+
				"                            WHERE "+
				"                                MODEL_CODE=( "+
				"                                      SELECT "+
				"                                         MODEL_CODE "+
				"                                      FROM "+
				"                                      "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				"                                      WHERE "+
				"                                      APPLICATION_NO=UPPER('"+m_app_no+"') AND "+
				"                                      INVOICE_NO=UPPER('"+m_invoice_no+"')  "+
							
				"                                          ) "+
				"                                   ) "+
				"                               )                          "+
				"   AND "+
				"   DIVISION_CODE='AF'       AND    "+
				"   ENTITY_TYPE IS NULL    AND    "+
				"   ACTIVE_STATUS=('"+m_status+"')        AND     "+
							"   UPPER(PRODUCT_CODE)=UPPER('"+m_txt_type+"') "+
							" AND "+
				"           CODE NOT IN (  "+
				"                  SELECT   "+
				"                     DOCUMENT_TYPE  "+
				"                  FROM  "+
				"                     "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
				"                  WHERE  "+
				"                     APPLICATION_NO=UPPER('"+m_app_no+"')  AND   "+ 
				"                     CLIENT_CODE=UPPER('"+m_client_code+"') AND  "+
							" 									 PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
				"                       )  "+
				"         ) AND            "+
				"         DOC_APP_TYPE='ASSET' AND  "+
				"         ACTIVE_STATUS=('"+m_status+"')  "+
							
				" UNION "+
							
				" SELECT  "+
				"  DOCUMENT_TYPE,  "+
				" (SELECT  "+
				" DESCRIPTION   "+
				" FROM  "+
				" "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
				" WHERE  "+
				" CODE=DOCUMENT_TYPE  "+ 
				" ) DESCRIPTION,  "+
				" NVL(REMARK,'-') REMARK , "+
						" STATUS, "+
						" NVL(FOLLOWUP_REMARKS,'-') FOLLOWUP_REMARKS,  "+
						" NVL(REF_NO,'-') REF_NO  "+
						"   FROM  "+
				" "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
				"    WHERE  "+
				" APPLICATION_NO=UPPER('"+m_app_no+"')  AND   "+
							" PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') AND "+
				" CLIENT_CODE=UPPER('"+m_client_code+"')  "+
							" ORDER BY STATUS ASC ");
							*/
				
				rs= stmt.executeQuery ("SELECT "+
					"  CODE,DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS,  "+
					"  NVL(NULL,'-') FOLLOWUP_REMARKS, NVL(NULL,'-') REF_NO   "+
					" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED   "+
					" WHERE   "+
					"  CODE IN ( SELECT  CODE   "+
					"       FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE   "+
					"    WHERE ITEM_CAT_CODE=(  "+
					"              SELECT ITEM_CAT_CODE  "+
					"  FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY  "+
					"  WHERE ITEM_SUB_CAT=(  "+
					"              SELECT ITEM_SUB_CAT  "+
					"              FROM  "+m_schema_name+".AF_CO_MAS_MODEL  "+
					"                    WHERE MODEL_CODE=(  "+
					"                         SELECT MODEL_CODE  "+
					"                    FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"                    WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND  "+
					"                          INVOICE_NO=UPPER('"+m_invoice_no+"')   "+
					"                )  "+
					"                           )  "+
					"                        )    "+
					"           AND   "+
					"           DIVISION_CODE='AF'       AND     "+
					"           ENTITY_TYPE IS NULL    AND      "+
					"           ACTIVE_STATUS=('"+m_status+"')        AND      "+
					" 					FROM_SCREEN_NO <=   "+
					" 					(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
					" 					WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"')   "+
					" 					AND TO_SCREEN_NO >=   "+
					" 					(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
					" 					WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND   "+
					"        PRODUCT_CODE=UPPER('"+m_txt_type+"')          AND      "+
					"           CODE NOT IN ( SELECT  DOCUMENT_TYPE   "+
					"                   FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT   "+
					"                   WHERE APPLICATION_NO=UPPER('"+m_app_no+"')  AND    "+
					"                     CLIENT_CODE=UPPER('"+m_client_code+"') AND    "+
					" 		PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') AND "+
					"                     OTHER_NO IS NULL "+
					"                       )   "+
					"           ) AND              "+
					"          DOC_APP_TYPE='ASSET' AND    "+
					"          ACTIVE_STATUS=('"+m_status+"')   "+
					
					" UNION   "+
					" SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK ,C.STATUS STATUS,NVL(C.FOLLOWUP_REMARKS,'-') FOLLOWUP_REMARKS ,NVL(C.REF_NO,'-') REF_NO  "+
					"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C  "+
					"               WHERE B.ITEM_CAT_CODE=(  "+
					"              SELECT ITEM_CAT_CODE  "+
					" 	 FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY  "+
					"  WHERE ITEM_SUB_CAT=(  "+
					"              SELECT ITEM_SUB_CAT  "+
					"              FROM  "+m_schema_name+".AF_CO_MAS_MODEL  "+
					"                    WHERE MODEL_CODE=(  "+
					"                         SELECT MODEL_CODE  "+
					"                    FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"                    WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND  "+
					"                          INVOICE_NO=UPPER('"+m_invoice_no+"')   "+
					"                )  "+
					"                           )  "+
					"                        )    "+
					" AND  "+
					" DOC_APP_TYPE='ASSET' AND A.ACTIVE_STATUS=('"+m_status+"')  "+
					"  AND C.DOCUMENT_TYPE IN  "+
					"  (SELECT CODE  "+
					"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+ 
					"  WHERE FROM_SCREEN_NO <=  "+
					"  (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
					"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"')  "+
					"  AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
					"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND  "+
					"     ITEM_CAT_CODE=(  "+
					"              SELECT ITEM_CAT_CODE  "+
					"  FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY  "+
					"  WHERE ITEM_SUB_CAT=(  "+
					"              SELECT ITEM_SUB_CAT  "+
					"              FROM  "+m_schema_name+".AF_CO_MAS_MODEL  "+
					"                    WHERE MODEL_CODE=(  "+
					"                         SELECT MODEL_CODE  "+
					"                    FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
					"                    WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND  "+
					"                          INVOICE_NO=UPPER('"+m_invoice_no+"')   "+
					"                ) "+
					"                           )  "+
					"                        )   "+
					
					"  AND  PRODUCT_CODE=UPPER('"+m_txt_type+"')        "+
					"  AND B.CODE=A.CODE  "+
					"  AND ACTIVE_STATUS=('"+m_status+"'))  "+
					"  AND C.DOCUMENT_TYPE=A.CODE AND  "+
					" C.APPLICATION_NO=UPPER('"+m_app_no+"')  AND   "+
					" C.PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') AND "+
					" C.CLIENT_CODE=UPPER('"+m_client_code+"')  "+
					"  AND C.OTHER_NO IS NULL "+
					" ORDER BY STATUS ASC ");
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2).replace('&','$')+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*--------------Purpose    :Get The Transaction Type - Application Processing
			----------------Created By :Nuwan De Silva  
			----------------Date       :16/01/2007----------------------------------  */
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_txt_type")){
				
				
				String m_app_no = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery ("SELECT "+
					//"               TRANSACTION_TYPE "+ // commented by udara 08-02-2019
					" NVL("+m_schema_name+".AF_GET_PRIMARY_TRANSACTION(TRANSACTION_TYPE),TRANSACTION_TYPE) "+ // added by udara 08-02-2019
					"           FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"           WHERE "+
					"               APPLICATION_NO=UPPER('"+m_app_no+"') "); 
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			/*--------------Purpose    :Validate Invoice Valuation - Application Processing
			----------------Created By :Nuwan De Silva  
			----------------Date       :31/01/2007----------------------------------  */
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_inv_valuation")){
				
				
				String m_app_no = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				/*rs= stmt.executeQuery ("SELECT "+
												"	DISTINCT  A.INVOICE_NO "+
							//					"	A.APPLICATION_NO, "+
							//					"	A.ASSET_ID, "+
						//						"	A.ENGINE_NO, "+
						//						"	A.CHASSIS_NO "+
												"	FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_VALUATION B "+
												"	WHERE A.APPLICATION_NO=B.APPLICATION_NO AND A.APPLICATION_NO=UPPER('"+m_app_no+"') AND "+ 
												"	A.ACTIVE_STATUS=('"+m_status+"') AND "+
												"	B.ACTIVE_STATUS=('"+m_status+"') AND "+
												"	A.INVOICE_NO=B.PRO_INVOICE_NO AND "+
												"	A.ENGINE_NO=B.ENGINE_NO AND "+
												"	A.CHASSIS_NO=B.CHASSIS_NO ");
					*/
				
				rs= stmt.executeQuery ("SELECT "+m_schema_name+".AF_CO_VALIDATE_ENG_CHASSIS_NO(APPLICATION_NO,INVOICE_NO) RESULT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					"	WHERE APPLICATION_NO=UPPER('"+m_app_no+"') AND "+ 	
					"	ACTIVE_STATUS=('"+m_status+"')  ");
				
				
				
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getInt(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			/*--------------Purpose    :Validate Loan Pricing - Application Processing ###L---  
			----------------Created By :SH            ----------------------------------
			----------------Date       :15/04/2008    ----------------------------------  */
			
			else if (m_chksql.trim().equals("m_chk_Loan_price")){
				
				
				String m_app_no = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status").trim();
				
				
				rs= stmt.executeQuery ("SELECT "+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PP',UPPER('"+m_app_no+"')), "+
					"       "+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PR',UPPER('"+m_app_no+"')) "+
					" FROM  DUAL ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<PP_COUNT>"+rs.getInt(1)+"</PP_COUNT>");
					out.print("<PR_COUNT>"+rs.getInt(2)+"</PR_COUNT>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			/*--------------Purpose    :get lead source name - Lead source created report---
			----------------Created By :delanjali-------------------------------------------
			----------------Date       :08-02-2007------------------------------------------  */
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_lead_source_report")){
				
				
				String m_app_no = req.getParameter("data_val").trim();
				
				
				rs= stmt.executeQuery (" SELECT DISTINCT TRIM(lead_source_category) lead_source_category "+
					" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY  "+
					" WHERE UPPER(trim(lead_source_category))= UPPER('"+m_app_no+"') ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_lead_source_report_data")){
				
				
				String m_app_no = req.getParameter("data_val").trim();
				
				
				rs = stmt.executeQuery ("SELECT LEAD_SOURCE_NAME "+
					"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"WHERE upper(LEAD_SOURCE_CATEGORY) like upper('"+m_app_no+"%')  "+ 
					"AND INQUARY_NO=INQUIRY_CODE "+
					"UNION "+
					"SELECT LEAD_SOURCE_NAME "+
					"FROM "+m_schema_name+".AF_MK_HIS_INQUIRY a, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"WHERE upper(LEAD_SOURCE_CATEGORY) like upper('"+m_app_no+"%') "+ 
					"AND INQUARY_NO=INQUIRY_CODE ");
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_bank_guarantor")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				//Added Branch name To THe Query Nuwan de silva 14-05-07------------
				
				rs= stmt.executeQuery ("SELECT APPLICATION_NO,ISSUER_CODE,AMOUNT,TO_CHAR(ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(START_DATE,'DD-MM-YYYY'), "+
					"TO_CHAR(END_DATE,'DD-MM-YYYY'),STATUS,B.BRANCH_NAME  "+
					"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES A ,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B  "+	
					"WHERE A.ISSUER_CODE=B.BRANCH_CODE AND UPPER(A.APPLICATION_NO)=UPPER('"+m_val+"')  "+
					"AND STATUS='"+m_status+"' ");
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+nf1.format(rs.getDouble(3))+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getString(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>"); 
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_bank_1")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");
				
				rs= stmt.executeQuery ("SELECT ISSUER_CODE "+
					"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES "+	
					"WHERE UPPER(ISSUER_CODE)=UPPER('"+m_val+"')  "+
					"AND STATUS='Y' ");
				
				
				
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_vehicle_details")){
				
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_display_change_invoice_details")){
				
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_val+"') AND APPLICATION_STATUS=('"+m_status+"') ");
				
				
				
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
			
			
			else if (m_chksql.trim().equals("m_prime_chk_display_change_invoice_details_finance")){
				
				
				String m_val = req.getParameter("data_val").trim();
				String m_status = req.getParameter("ac_status");	
				
				rs= stmt.executeQuery ("SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE UPPER(FINANCE_NO)=UPPER('"+m_val+"') "); // AND APPLICATION_STATUS=('"+m_status+"')
				
				
				
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
			
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Document_req_asset_details")){
				
				
				String m_val = req.getParameter("data_val_invoice_no").trim();
				
				
				rs= stmt.executeQuery (" SELECT "+
					" B.MAKE_CODE, "+
					" A.MODEL_CODE, "+
					" NVL(A.ENGINE_NO,'-') ENGINE_NO, "+
					" NVL(A.CHASSIS_NO,'-') CHASSIS_NO, "+
					" NVL(A.REG_NO,'-') REG_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B "+
					"   WHERE A.MODEL_CODE=B.MODEL_CODE AND UPPER(A.INVOICE_NO)=UPPER('"+m_val+"') ");
				
				
				
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
			
			
			
			
			/*------------------ID         : 1.7 Location Creation Process-----------------------------------------
		--------------------Purpose    :Location Code Validation ----------------------------------------------
		-------------------   Added By :Nuwan De Silva------------------------------------------------------
		--------------------  Date     :25-06-2007---------------------------------------------------------*/
			
			else if (m_chksql.trim().equals("m_prime_chk_application_process_branch_code")){
				
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
			
			
			
			// Added by Thamali Jayatunga on 2010.02.25
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_sec_vehicle")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ("SELECT VEHICLE_NO "+
					"FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+	
					"WHERE UPPER(VEHICLE_NO)=UPPER('"+m_val+"') AND UPPER(APPLICATION_NO)=UPPER('"+m_val2+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_sec_land")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ("SELECT MORTGAGE_NO "+
					"FROM "+m_schema_name+".AF_MK_APP_SECURITY_LAND "+	
					"WHERE UPPER(MORTGAGE_NO)=UPPER('"+m_val+"') AND UPPER(APPLICATION_NO)=UPPER('"+m_val2+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_sec_fixed_deposit")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ("SELECT FD_ACC_NO "+
					"FROM "+m_schema_name+".AF_MK_APP_SECURITY_FIXED_DEP "+	
					"WHERE UPPER(FD_ACC_NO)=UPPER('"+m_val+"') AND UPPER(APPLICATION_NO)=UPPER('"+m_val2+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_sec_vehicle_edit")){
				
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery (" SELECT VEHICLE_NO,"+
					" CUSTOMER_NAME, "+
					" TO_CHAR(REG_DATE,'DD-MM-YYYY') REG_DATE, "+
					" NVL(VEHICLE_TYPE,'-') VEHICLE_TYPE, "+
					" NVL(MODEL_CODE,'-') MODEL_CODE, "+ 
					" NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
					" NVL(CUBIC_CAPACITY,'-') CUBIC_CAPACITY, "+
					" NVL(CHASIS_NO,'-') CHASIS_NO, "+
					" NVL(ENGINE_NO,'-') ENGINE_NO, "+ 
					" NVL(EXTEND,'-') EXTEND, "+
					" NVL(FUEL_TYPE,'-') FUEL_TYPE, "+ 
					" NVL(ADDRESS,'-') ADDRESS, "+
					" TO_CHAR(CR_BOOK_DATE,'DD-MM-YYYY') CR_BOOK_DATE, "+
					" NVL(PROVINCE,'-') PROVINCE, "+
					" NVL(CR_BOOK_NO,'-') CR_BOOK_NO, "+
					" NVL(VALUER_CODE,'-') VALUER_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE),'-') VALUER_NAME, "+
					" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+
					" NVL(VALUE,0) VALUE, "+
					" NVL(FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+
					" NVL(METER_READING,0) METER_READING, "+
					" NVL(CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
					" NVL(NOTES,'-') NOTES, "+
					" NVL(REMARKS,'-') REMARKS, "+
					" NVL(TYPE_OF_BODY,'-') TYPE_OF_BODY "+
					" FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+	
					" WHERE UPPER(VEHICLE_NO)=UPPER('"+m_val+"') "+
					" AND   UPPER(APPLICATION_NO)=UPPER('"+m_val2+"')  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getInt(6)+"</R6>");
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
					out.print("<R19>"+nf.format(rs.getDouble(19))+"</R19>");
					out.print("<R20>"+nf.format(rs.getDouble(20))+"</R20>");
					out.print("<R21>"+rs.getInt(21)+"</R21>");
					out.print("<R22>"+rs.getString(22)+"</R22>");
					out.print("<R23>"+rs.getString(23)+"</R23>");
					out.print("<R24>"+rs.getString(24)+"</R24>");
					out.print("<R25>"+rs.getString(25)+"</R25>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_sec_land_edit")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ( " SELECT MORTGAGE_NO, "+
					" MORTGAGE_TYPE, "+
					" NVL(DEED_NO,'-') DEED_NO, "+ 
					" NVL(ADDRESS,'-') ADDRESS, "+   
					" NVL(VALUE,0) VALUE, "+ 
					" NVL(VALUES_NAME,'-') VALUES_NAME, "+ 
					" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+
					" NVL(REMARKS,'-') REMARKS "+
					" FROM "+m_schema_name+".AF_MK_APP_SECURITY_LAND "+	
					" WHERE UPPER(MORTGAGE_NO)=UPPER('"+m_val+"') AND UPPER(APPLICATION_NO)=UPPER('"+m_val2+"') ");
				
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
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_sec_fd_edit")){
				String m_val = req.getParameter("data_val").trim();
				String m_val2 = req.getParameter("data_val2").trim();
				
				rs= stmt.executeQuery ( " SELECT FD_ACC_NO, "+
					" NVL(AMOUNT,0) AMOUNT, "+
					" TO_CHAR(STARTING_DATE,'DD-MM-YYYY') STARTING_DATE, "+
					" TO_CHAR(MATURITY_DATE,'DD-MM-YYYY') MATURITY_DATE, "+
					" TO_CHAR(INTEREST_DATE,'DD-MM-YYYY') INTEREST_DATE, "+
					" NVL(INTEREST_PAYABLE,'-') INTEREST_PAYABLE, "+
					" NVL(PERIOD,0) PERIOD, "+
					" NVL(REMARKS,'-') REMARKS "+
					" FROM "+m_schema_name+".AF_MK_APP_SECURITY_FIXED_DEP "+	
					" WHERE UPPER(FD_ACC_NO)=UPPER('"+m_val+"') AND UPPER(APPLICATION_NO)=UPPER('"+m_val2+"') ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+nf.format(rs.getDouble(2))+"</R2>");
					out.print("<R3>"+rs.getString(3)+"</R3>");
					out.print("<R4>"+rs.getString(4)+"</R4>");
					out.print("<R5>"+rs.getString(5)+"</R5>");
					out.print("<R6>"+rs.getString(6)+"</R6>");
					out.print("<R7>"+rs.getInt(7)+"</R7>");
					out.print("<R8>"+rs.getString(8)+"</R8>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			// added by udara 03-04-2014
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_running_contract_edit")){
				String m_val   = req.getParameter("data_val").trim();
				String m_val_2 = req.getParameter("run_no").trim(); 
				
				rs= stmt.executeQuery ( " SELECT ASSIGNED_FINANCE_NO "+
					" FROM "+m_schema_name+".AF_ASSET_RUN_CONTRACTS "+	
					" WHERE UPPER(APP_NO)=UPPER('"+m_val+"') AND ASSIGNED_FINANCE_NO = '"+m_val_2+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			// end by udara 03-04-2014
			
			
			// added by udara 09-05-2014
			else if (m_chksql.trim().equals("m_check_refin_reg_no")){
				
				String m_val   = req.getParameter("refin_no").trim();
				
				rs= stmt.executeQuery (" "+
					" SELECT REG_NO "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE CHASSIS_NO <> '-' "+
					" AND APPLICATION_NO = AF_CO_GET_APPLICATION_NO('"+m_val+"')  "+ 
					" ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_check_refin_engine_no")){
				
				String m_val   = req.getParameter("refin_no").trim();
				
				rs= stmt.executeQuery (" "+
					" SELECT ENGINE_NO "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE ENGINE_NO <> '-' "+
					" AND APPLICATION_NO = AF_CO_GET_APPLICATION_NO('"+m_val+"')  "+ 
					" ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			else if (m_chksql.trim().equals("m_check_refin_chasis_no")){
				
				String m_val   = req.getParameter("refin_no").trim();
				
				rs= stmt.executeQuery (" "+
					" SELECT CHASSIS_NO "+
					" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE CHASSIS_NO <> '-' "+
					" AND APPLICATION_NO = AF_CO_GET_APPLICATION_NO('"+m_val+"')  "+ 
					" ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}
			
			
			// end by udara 09-05-2014
			
			// added by udara 24-07-2015
			else if (m_chksql.equals("cheque_no_validate")){
				
					String m_cheque_no = req.getParameter("cheque_no");
					String m_payment_no = req.getParameter("payment_no");
					String m_value_str = "N";
					int m_value = 0;
				
					rs = stmt.executeQuery (" "+
							" SELECT COUNT(PAYMENT_NO) "+
							" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  "+
							" WHERE CHEQUE_NO = '"+m_cheque_no+"'  "+
						" ");
				
					if(rs.next()){
						m_value = rs.getInt(1);
					}
				
					if(m_value>0)
						m_value_str = "Y";
					else
						m_value_str = "N";

				    out.print("<DATA>");
					out.print("<ITEM>");
					out.print("<R1>"+m_value_str+"</R1>");
					out.print("</ITEM>");
				    out.print("</DATA>");
				
				
			}
			
			else if (m_chksql.equals("cheque_no_validate_disburse")){
				
					String m_cheque_no = req.getParameter("cheque_no");
					String m_payment_no = req.getParameter("payment_no");
					String m_value_str = "N";
					int m_value = 0;
				
					rs = stmt.executeQuery (" "+
							 " SELECT COUNT(PAYMENT_NO) "+
							 " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  "+
							 " WHERE CHEQUE_NO = '"+m_cheque_no+"'  "+
               				 " AND ((PAYMENT_NO <> '"+m_payment_no+"') AND (GROUP_PAYMENT_NO <> '"+m_payment_no+"')) "+
						" ");
				
					if(rs.next()){
						m_value = rs.getInt(1);
					}
				
					if(m_value>0)
						m_value_str = "Y";
					else
						m_value_str = "N";

				    out.print("<DATA>");
					out.print("<ITEM>");
					out.print("<R1>"+m_value_str+"</R1>");
					out.print("<R2>"+m_value+"</R2>");
					out.print("<R3>"+m_cheque_no+"</R3>");
					out.print("<R4>"+m_payment_no+"</R4>");
					out.print("</ITEM>");
				    out.print("</DATA>");
				
				
			}
			
			
			
			// end by udara 24-07-2015

			// added by kasun on 2024.11.18
			else if (m_chksql.trim().equals("m_prime_chk_LAKDL_AF_MK_Application_Process_product_hadagasma_edit")){
				String m_val   = req.getParameter("data_val").trim();
				String m_val_2 = req.getParameter("sub_cat").trim(); 
				
				rs= stmt.executeQuery ( " SELECT ITEM_SUB_CAT, NVL(REMARKS,'-') REMARKS "+
					" FROM "+m_schema_name+".AF_ASSET_PRO_HADAGASMA "+	
					" WHERE UPPER(APP_NO)=UPPER('"+m_val+"') AND ITEM_SUB_CAT = '"+m_val_2+"'  ");
				
				out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("<R2>"+rs.getString(2)+"</R2>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
			}


			// end by kasun on 2024.11.18
			
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


