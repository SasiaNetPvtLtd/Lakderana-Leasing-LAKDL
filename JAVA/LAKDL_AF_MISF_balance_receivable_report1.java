//--
//SCREEN NAME	:BALANCE RECEIVABLE REPORT
//CREATED BY	:delanjali 
//MODIFIED BY :Mahela
//DATE/TIME		:2007-05-04
//NOTES				:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_display_payment_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_balance_receivable_report1 extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7;
		public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt5 = conn.createStatement();
			stmt6 = conn.createStatement();
			stmt7 = conn.createStatement();
					
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 

			String m_schema_name = m_sn_methods.schema_name;
			String m_screen_type= req.getParameter("chksql");
			String m_date=req.getParameter("allocation_date");
			String m_client_code=req.getParameter("client_code");
			String m_finance_no=req.getParameter("finance_no");
			String m_client_name=req.getParameter("client_name");
			String m_fschema_name=m_sn_methods.client_name.trim();

						
			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Balance Receivable Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
		
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
							
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Balance Receivable Report - \"+m_val;"); 
			out.println("}");
			out.println("function load_roll_out_value(){"); 
			out.println("help_box.innerHTML=\"Balance Receivable Report  \";"); 
			out.println("}");
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System</td></tr>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>");
			

			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Balance Receivable Report</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");



			rs1 = stmt1.executeQuery ("SELECT nvl(to_char(to_date('"+m_date+"','fmddth-mm-yyyy'),'fmddth  Month  yyyy'),' ') from dual ");
		
			String m_date_type="";
		 double m_other_inv =0;


			pstmt = conn.prepareStatement
			
 		("SELECT "+
     "INVOICE_NO, "+
     "FINANCE_NO, "+
     "TO_CHAR(DUE_DATE,'dd/mm/yyyy'), "+
		 "CLIENT_CODE, "+
		 ""+m_schema_name+".af_co_get_client_name(CLIENT_CODE), "+
		 "UPPER("+m_schema_name+".AF_CO_GET_NARRATIONS_CODE(INVOICE_NO)), "+
     "NET_AMOUNT, "+
     "VAT_AMOUNT, "+
     "TOTAL_AMOUNT, "+
     "DUE_DATE, "+
     "SETTELE_AMOUNT, "+
     "BALANCE_TO_BE_RECEIVED, "+
     "CURRENCY_CODE, "+
     "EXCHANGE_RATE "+
 		 "FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
		 "WHERE to_date(to_char(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= to_date('"+m_date+"','DD-MM-YYYY')  "+
		 "AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND INVOICE_TYPE='INV_GENER' "+
		 " AND ACTIVE_STATUS = 'Y' "+//Added By Sandun on 07-11-2009
		 "AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') order by DUE_DATE");
		
			
					
			
			
	
			/*rs2 = stmt2.executeQuery 
			("SELECT "+
	    " DISTINCT A.REC_NO, "+  //modified by nuwan de silva on 22-10-07
	    "A.SUS_REF_NO,"+
	   	"InitCap(A.SETTLE_MODE), "+
			"TO_CHAR(A.eff_valdate,'DD/MM/YYYY'), "+
	    "A.PAYER_BRANCH_CODE, "+
	   	"A.PAYER_ACC_NO, "+
	   	"A.CLIENT_CODE, "+
	   	"A.ENTRY_TYPE, "+
	   	"A.REC_AMOUNT, "+
	    "nvl(A.CHEQUE_NO,'-'), "+
	    "A.CHEQUE_DATE, "+
	    "nvl(A.INSURANCE,0) , "+
	    "A.PRINT_DATE, "+
			"NVL(B.RECEIPT_AMOUNT,0) "+		
	 		"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS	B	"+
			"WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
			"AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
			"AND B.RECEIPT_NO=A.REC_NO AND A.STATUS NOT IN('C','CAD','RET')");//modified by Dineth on 2008-12-08
      */
			//-------------------------Mod By Sandun on 06-03-2009--------------------------
			
			rs2 = stmt2.executeQuery(" SELECT REC_NO,SUS_REF_NO,SETTLE_MODE,EFF_VALDATE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CLIENT_CODE,ENTRY_TYPE, "+
		                           " SUM(REC_AMOUNT),CHEQUE_NO,CHEQUE_DATE,INSURANCE,PRINT_DATE,SUM(AMOUNT),VALDATE "+
			                         " FROM( "+
			                         " SELECT DISTINCT A.REC_NO REC_NO ,  "+
															 " A.SUS_REF_NO SUS_REF_NO , "+
															 " DECODE(A.SETTLE_MODE,'CHEQUE','Cheque','STD_ORD','Standing Order','CASH','Cash','DIR_DEP','Direct Deposit',A.SETTLE_MODE) SETTLE_MODE,  "+//3
															 " TO_CHAR(A.EFF_VALDATE,'DD/MM/YYYY') EFF_VALDATE ,  "+
															 " A.PAYER_BRANCH_CODE PAYER_BRANCH_CODE,  "+
															 " A.PAYER_ACC_NO PAYER_ACC_NO,  "+
															 " A.CLIENT_CODE CLIENT_CODE,  "+
															 " A.ENTRY_TYPE ENTRY_TYPE,  "+
															 //" A.REC_AMOUNT REC_AMOUNT, "+
															 " SUM(APP_REC_AMOUNT) REC_AMOUNT, "+	
															 " NVL(A.CHEQUE_NO,'-') CHEQUE_NO,  "+
															 " A.CHEQUE_DATE CHEQUE_DATE,  "+
															 " NVL(A.INSURANCE,0) INSURANCE,  "+
															 " A.PRINT_DATE PRINT_DATE,  "+
															 " SUM(NVL(B.REC_AMOUNT,0)) AMOUNT, A.EFF_VALDATE VALDATE "+
															 " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
															 " WHERE A.REC_NO = B.REC_NO "+
															 " AND UPPER(B.FINANCE_NO) = UPPER('"+m_finance_no+"') "+
															 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															 " AND A.STATUS NOT IN ('C','CAD','RET') "+
															 " GROUP BY A.REC_NO,A.SUS_REF_NO,A.SETTLE_MODE,A.EFF_VALDATE,A.PAYER_BRANCH_CODE,A.PAYER_ACC_NO,A.CLIENT_CODE,A.ENTRY_TYPE, "+
		                           " A.CHEQUE_NO,A.CHEQUE_DATE,A.INSURANCE,A.PRINT_DATE,A.EFF_VALDATE "+
															 //" ORDER BY A.EFF_VALDATE ");
															 //-----------------------Added By Sandun on 10-07-2009-------------------------
															 " UNION ALL "+
																
															 " SELECT DISTINCT C.REC_NO REC_NO, "+  
															 " C.SUS_REF_NO SUS_REF_NO,  "+
															 " DECODE(C.SETTLE_MODE,'CHEQUE','Cheque','STD_ORD','Standing Order','CASH','Cash','DIR_DEP','Direct Deposit',C.SETTLE_MODE) SETTLE_MODE,  "+//3
															 " TO_CHAR(C.EFF_VALDATE,'DD/MM/YYYY') EFF_VALDATE,   "+
															 " C.PAYER_BRANCH_CODE  PAYER_BRANCH_CODE,   "+
															 " C.PAYER_ACC_NO PAYER_ACC_NO,   "+
															 " C.CLIENT_CODE CLIENT_CODE,  "+
															 " C.ENTRY_TYPE ENTRY_TYPE,   "+
															 //" C.REC_AMOUNT REC_AMOUNT,  "+
															 " SUM(SETTELED_AMOUNT) REC_AMOUNT, "+	
															 " NVL(C.CHEQUE_NO,'-') CHEQUE_NO,   "+
															 " C.CHEQUE_DATE CHEQUE_DATE,   "+
															 " NVL(C.INSURANCE,0) INSURANCE,   "+
															 " C.PRINT_DATE PRINT_DATE,   "+
															 " SUM(NVL(A.RECEIPT_AMOUNT,0)) AMOUNT ,  "+
															 " C.EFF_VALDATE VALDATE  "+
															 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A/*,"+m_schema_name+".AF_CO_PRO_INVOICE B*/,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
															 " WHERE /* A.INVOICE_NO = B.INVOICE_NO */"+
															 " A.RECEIPT_NO = C.REC_NO  "+
															 //" AND UPPER(B.FINANCE_NO) = UPPER('"+m_finance_no+"') "+
															 " AND TO_DATE(TO_CHAR(C.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															 " AND C.STATUS NOT IN ('C','CAD','RET') "+	
															 " AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
															 "                   WHERE FINANCE_NO='"+m_finance_no+"' "+
															 "                   AND   ACTIVE_STATUS='Y' "+
											         "                   UNION ALL "+
											         "                   SELECT ODI_REF_NO "+
											         "                   FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
											         "                   WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
											         "                   WHERE FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='Y'   )) "+
															 " AND A.RECEIPT_NO NOT IN (SELECT REC_NO  FROM  "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL WHERE FINANCE_NO = '"+m_finance_no+"' ) "+
															 " GROUP BY C.REC_NO,C.SUS_REF_NO,C.SETTLE_MODE,C.EFF_VALDATE,C.PAYER_BRANCH_CODE,C.PAYER_ACC_NO,C.CLIENT_CODE,C.ENTRY_TYPE, "+
		                           " C.CHEQUE_NO,C.CHEQUE_DATE,C.INSURANCE,C.PRINT_DATE,C.EFF_VALDATE "+
															 " )	"+
															 " GROUP BY REC_NO,SUS_REF_NO,SETTLE_MODE,EFF_VALDATE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CLIENT_CODE,ENTRY_TYPE, "+
		                           " CHEQUE_NO,CHEQUE_DATE,INSURANCE,PRINT_DATE,VALDATE "+
			                         " ORDER BY VALDATE ");
																
			
			
			
			
			/*rs3 = stmt3.executeQuery 
			("SELECT "+
	    //"SUM(A.GRENTAL_AMOUNT) "+
			"SUM(A.CAPITAL_AMOUNT+A.INTEREST_AMOUNT)+SUM(A.GRENTAL_AMOUNT-A.NET_RENTAL_AMOUNT) "+
	 		"FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= to_date('"+m_date+"','DD-MM-YYYY') "+
			"AND A.APPLICATION_NO=B.APPLICATION_NO "+
			"AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') "+
			"AND A.INVOICE_NO IS NOT NULL ");
			*/
		
			//rs3 = stmt3.executeQuery(" SELECT /*SUM(NVL(A.NET_RENTAL_AMOUNT,0)*(100+C.VAT_RATE))/100*/ SUM(NVL(A.NET_RENTAL_AMOUNT,0)) "+     
				/*										     " FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL C "+
													     " WHERE  A.APPLICATION_NO=B.APPLICATION_NO"+
													     " AND   B.TRANSACTION_TYPE = C.TRN_CODE "+
													     " AND B.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
													     " AND A.RENTAL_DATE  > TO_DATE('"+m_date+"','DD-MM-YYYY')   "+
													     " AND A.INVOICE_NO IS NULL ");   
																
			
			*/
			//Modified By Sandun on 04-08-2009
			rs3 = stmt3.executeQuery(" SELECT SUM(NVL(A.NET_RENTAL_AMOUNT,0)) "+     
														   " FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
													     " WHERE  A.APPLICATION_NO=B.APPLICATION_NO "+
													     " AND B.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
													     " AND A.RENTAL_DATE  > TO_DATE('"+m_date+"','DD-MM-YYYY')   "+
													     " AND A.INVOICE_NO IS NULL ");  
			
			
			
			//-------------------------Mod By Sandun on 06-03-2009--------------------------
			/*rs4 = stmt4.executeQuery 
			("SELECT "+   
		 	"SUM(ODI_BAL_AMOUNT) "+
		 	"FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_DAILY "+
		 	"WHERE TO_DATE(TO_CHAR(ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= to_date('"+m_date+"','DD-MM-YYYY') ");*/

			/*rs4 = stmt4.executeQuery( " SELECT   "+
															  " NVL(SUM(ODI_BAL_AMOUNT),0)"+
																" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_DAILY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																" WHERE TO_DATE(TO_CHAR(ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY')"+
																" AND A.INVOICE_NO=B.INVOICE_NO"+
																" AND B.FINANCE_NO=UPPER('"+m_finance_no+"') ");
																
																*/

          rs4 = stmt4.executeQuery(   " SELECT SUM(ODI_BAL_AMOUNT) "+
																			" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
																			" WHERE INVOICE_NO IN "+ 
																			" (SELECT INVOICE_NO "+
																			" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
																			" WHERE FINANCE_NO IN   ( SELECT FINANCE_NO  "+
																			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
																			" WHERE FINANCE_NO = UPPER('"+m_finance_no+"') "+
																			" AND TO_DATE(TO_CHAR(ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																			" AND  APPLICATION_STATUS <> 'CANCEL' )) " );
			
			rs=pstmt.executeQuery(); 
			
			
						rs6 = stmt6.executeQuery("  SELECT A.REF_NO,  "+//1
																		 "	A.ADJUSTED_AMOUNT, "+//2
																		 "	TO_CHAR(A.ADJUSTED_DATE,'DD/MM/YYYY'), "+//3
																		 "	A.NARRATIONS_CODE "+//4
																		 " 	FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A "+
																		 "	WHERE A.CREDIT_TYPE =  'CR' "+
																		 "	AND A.ADJUST_TYPE   =  'CR_NOTE' "+
																		 "  AND A.ACTIVE_STATUS = 'Y' "+
																		 "	AND A.FINANCE_NO    =  UPPER('"+m_finance_no+"')  "+
																		 "	AND A.ADJUSTED_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																		 
																			"  UNION "+
																		 
																			" SELECT "+
																		  " REF_NO, "+
																		  " ADJUSTED_AMOUNT AMOUNT, "+
																			"	TO_CHAR(ADJUSTED_DATE,'DD/MM/YYYY'), "+//3
																		  " DECODE(CREDIT_TYPE,'CR','Credit Note - Legal Termination') DESCRIPTION "+
																		  " FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
																		  " WHERE INVOICE_NO IN "+
																		  " ( "+
																		  " SELECT INVOICE_NO "+
																		  "    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																		  "    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
																		  "    ACTIVE_STATUS='Y' "+
																		  " ) "+					
																		  " AND   ACTIVE_STATUS IN ('Y') "+
																		  " AND   ADJUST_TYPE = 'LEGAL_AD' "+
																		  " AND ADJUSTED_DATE <=TO_DATE('"+m_date+"','DD-MM-YYYY') ");

			
			
			   /* rs7 = stmt7.executeQuery(" SELECT "+     
															     " SUM(TOTAL_AMOUNT) "+     
															 		 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
																	 " WHERE TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= to_date('"+m_date+"','DD-MM-YYYY')  "+
																	 " AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND INVOICE_TYPE <> 'INV_GENER' "+
																	 " AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') "+
																	 " UNION "+
																	 " SELECT "+
																	 " SUM(C.SETTELED_AMOUNT) "+																
																	 " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+
																	 "      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
																	 " WHERE "+
																	 "     C.INVOICE_NO=A.ODI_REF_NO "+
																	 " AND ODI_SETTLED_AMOUNT > 0 "+
																	 " AND A.INVOICE_NO IN "+
																	 " ( SELECT INVOICE_NO "+
																	 "    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																	 "    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
																	 "            ACTIVE_STATUS IN ('Y','DB_CAN')  "+
																	 ") "+
																	 " AND C.ALLOCATED_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') ");
		
				if(rs7.next()){
				m_other_inv = rs7.getDouble(1);
				}
			
			*/
			int j=0;
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>");
		  out.println("<td class='txt_report_data' align='left'><B>LAKDERANA INVESTMENTS LIMITED</td></tr>");
			out.println("<tr><td class='txt_report_data' align='left'><B>"+m_client_name+"</td></tr>");
			out.println("<tr><td class='txt_report_data' align='left'><B>"+m_client_code+"</td></tr>");
			out.println("<tr><td class='txt_report_data' align='left'><B>"+m_finance_no+"</td></tr>");
			out.println("</table>");
			out.println("<BR>");
	
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr>");
			out.println("<td width='10%' class='txt_report_column' >Date</td>");
			out.println("<td width='20%' class='txt_report_column' >Doc. Ref.</td>");
			out.println("<td width='20%' class='txt_report_column' >Narration</td>");
			out.println("<td width='10%' class='txt_report_column' >Cheque No</td>");
			out.println("<td width='15%' class='txt_report_column' >Amount</td>");
			out.println("<td width='15%' class='txt_report_column' >Amount</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<BR>");

			out.println("<table align='center' width='100%' class='table' border=1>"); 
			double m_amount=0;

			while(rs1.next()){
			m_date_type=rs1.getString(1);

			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='100%' class='txt_report_data'><b>Total Invoices As At     "+rs1.getString(1)+" </td>");		
			out.println("</tr>");		
				}
			out.println("</table>");
			out.println("<BR>");

				
		
			boolean more=rs.next();
			out.println("<table align='center' width='100%' class='table' border=1>"); 

			while(more){
			
			m_amount=m_amount+rs.getDouble(9);

			out.println("<tr><td width='10%' class='txt_report_data' align='left'><B>"+rs.getString(3)+"</td>");
			//Modified by Mahela on 11-05-2007
			if((rs.getString(1).substring(0,2)).equals("SR")){
			out.println("<td width='20%' class='txt_report_data' onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" style='cursor:hand' ><u><b>"+rs.getString(1)+"</b></u></td>");
			}
			else if((rs.getString(1).substring(0,2)).equals("IN")){
			out.println("<td width='20%' class='txt_report_data' onClick=\"show_invoice_drill('"+rs.getString(1)+"')\" style='cursor:hand' ><u><b>"+rs.getString(1)+"</b></u></td>");
			}
			else {
			out.println("<td width='20%' class='txt_report_data' align='left'><B>"+rs.getString(1)+"</td>");
			}
			
			
			if(rs.getString(6)==null){
			out.println("<td width='20%' class='txt_report_data' align='left'>&nbsp</td>");
			}
			if(rs.getString(6)!=null){
			out.println("<td width='20%' class='txt_report_data' align='left'><B>"+rs.getString(6)+"</td>");
			}
				
			out.println("<td width='10%' class='txt_report_data' align='left'><B>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(rs.getDouble(9))+"</td>");
			out.println("<td width='15%' class='txt_report_data' align='left'><B>&nbsp</td></tr>");
	
			
			out.println("</tr >"); 
			more=rs.next(); 
			j=j+1;
			} 
			out.println("<tr><td width='10%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(m_amount)+"</td></tr>");
			out.println("</table>");
			out.println("<BR>");
			
			//------------------------------------------------------------------------
		  		//Added by Sandun on 04-11-2009			
				rs5 = stmt5.executeQuery(" SELECT AMOUNT,TYP,DESCRIPTION,REF_NO,VALUE_DATE "+
				                         " FROM ( "+
				                         " SELECT A.TOTAL_AMOUNT AMOUNT , "+
				                         " A.INVOICE_TYPE TYP, "+
																 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE),A.INVOICE_TYPE) DESCRIPTION, "+
																 " A.INVOICE_NO REF_NO, "+
																 " TO_CHAR(A.VALUE_DATE,'DD/MM/YYYY') VALUE_DATE "+
																 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
																 " WHERE A.FINANCE_NO = UPPER('"+m_finance_no+"')  "+
																 " AND A.VALUE_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																 " AND A.INVOICE_TYPE NOT IN ('INV_GENER','LEGAL_ARR','LEGAL_CAP')  "+
																 " AND A.ACTIVE_STATUS = 'Y' "+
																 
																 " UNION ALL "+
																	
																 " SELECT "+
																 " C.SETTELED_AMOUNT  AMOUNT, "+
																 " 'ODI' TYP, "+
																 " 'Over Due Interst' DESCRIPTION, "+																	
																 " A.INVOICE_NO REF_NO, "+																	
																 " TO_CHAR(C.ALLOCATED_DATE,'DD/MM/YYYY') VALUE_DATE "+
																 " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+
																 "      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
																 " WHERE C.INVOICE_NO=A.ODI_REF_NO "+
																 " AND ODI_SETTLED_AMOUNT > 0 "+
																 " AND A.INVOICE_NO IN "+
																 " ( SELECT INVOICE_NO "+
																 "    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																 "    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
																 "    ACTIVE_STATUS IN ('Y')  "+
																 ") "+
																 " AND C.ALLOCATED_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																 " ) "+
																 " ORDER BY VALUE_DATE");
																 
					
																	
			
			boolean more5 = rs5.next();
			double m_other_tot =0.0;
			
			
			if(more5){
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='100%' class='txt_report_data'><b>Total Other Invoices As At "+m_date+" </td>");		
			out.println("</tr>");		
			out.println("</table>");
			out.println("<BR>");
			
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			while(more5){
			m_other_tot =m_other_tot + rs5.getDouble(1);		
			
			out.println("<tr><td width='10%' class='txt_report_data' align='left'><B>"+rs5.getString(5)+"</td>");
			if((rs5.getString(4).substring(0,2)).equals("SR")){
			out.println("<td width='20%' class='txt_report_data' onClick=\"show_settle_receipt_drill('"+rs5.getString(4)+"')\" style='cursor:hand' ><u><b>"+rs5.getString(4)+"</b></u></td>");
			}
			else if((rs5.getString(4).substring(0,2)).equals("IN")){
			out.println("<td width='20%' class='txt_report_data' onClick=\"show_invoice_drill('"+rs5.getString(4)+"')\" style='cursor:hand' ><u><b>"+rs5.getString(4)+"</b></u></td>");
			}
			else {
			out.println("<td width='20%' class='txt_report_data' align='left'><B>"+rs5.getString(4)+"</td>");
			}
			
			
			
			out.println("<td width='20%' class='txt_report_data' align='left'><B>"+rs5.getString(3)+"</td>");
			
				
			out.println("<td width='10%' class='txt_report_data' align='left'><B>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(rs5.getDouble(1))+"</td>");
			out.println("<td width='15%' class='txt_report_data' align='left'><B>&nbsp</td></tr>");
	
			
			out.println("</tr >"); 
			more5 = rs5.next(); 			
			} 
			out.println("<tr><td width='10%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(m_other_tot)+"</td></tr>");
			out.println("</table>");
			out.println("<BR>");
			
			
			}
			
			
			out.println("</table>");
			
			//------------------------------------------------------------------------
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='100%' class='txt_report_data'><b>Less :- Total Reciepts As At     "+m_date_type+" </td>");		
			out.println("</tr>");	
			out.println("</table>");
			out.println("<BR>");



			out.println("<table align='center' width='100%' class='table' border=1>"); 
			double m_amount1=0;
			double m_insurance=0;


			while(rs2.next()){
			m_amount1=m_amount1+rs2.getDouble(9);
			m_insurance=m_insurance+rs2.getDouble(12);
			out.println("<tr><td width='10%' class='txt_report_data' align='left'><B>"+rs2.getString(4)+"</td>");
			//Modified by Mahela on 11-05-2007
			if((rs2.getString(1).substring(0,2)).equals("SR")){
			out.println("<td width='20%' class='txt_report_data' onClick=\"show_settle_receipt_drill('"+rs2.getString(1)+"')\" style='cursor:hand' ><u><b>"+rs2.getString(1)+"</b></u></td>");
			}
			else if((rs2.getString(1).substring(0,2)).equals("IN")){
			out.println("<td width='20%' class='txt_report_data' onClick=\"show_invoice_drill('"+rs2.getString(1)+"')\" style='cursor:hand' ><u><b>"+rs2.getString(1)+"</b></u></td>");
			}
			else {
			out.println("<td width='20%' class='txt_report_data' align='left'><B>"+rs2.getString(1)+"</td>");
			}				
			
			if(rs2.getString(3)==null){
			out.println("<td width='20%' class='txt_report_data' align='left'>&nbsp</td>");
			}
			if(rs2.getString(3)!=null){
			out.println("<td width='20%' class='txt_report_data' align='left'><B>"+rs2.getString(3)+"</td>");
			}
		
			out.println("<td width='10%' class='txt_report_data' align='left'><B>"+rs2.getString(10)+"</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(rs2.getDouble(9))+"</td>");
			out.println("<td width='15%' class='txt_report_data' align='left'><B>&nbsp</td></tr>");

			}

			out.println("<tr><td width='10%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(m_amount1)+"</td></tr>");

			out.println("<tr></tr>");	
			
			out.println("<tr></tr>");	
			out.println("<tr></tr>");	
			
			
			
			//---------------------------Added By Sandun on 06-04-2009-------------------------------------------------------
			boolean more6 = rs6.next();
			double m_amount_cr  = 0;
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='100%' class='txt_report_data'><b>Invoice Adjustments</td>");		
			out.println("</tr>");	
			out.println("</table>");
			out.println("<BR>");
			
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			
			while(more6){
			
			out.println("<tr><td width='10%' class='txt_report_data' align='left'><B>"+rs6.getString(3)+"</td>");
			out.println("<td width='20%' class='txt_report_data' align='left'  style='cursor:hand' ><B>"+rs6.getString(1)+"</td>");//onClick=\"show_invoice_drill('"+rs6.getString(1)+"')\"
			out.println("<td width='20%' class='txt_report_data' align='right'><B>&nbsp;</td>");
			out.println("<td width='10%' class='txt_report_data' align='left'><B>&nbsp;</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(rs6.getDouble(2))+"</td>");
			out.println("<td width='15%' class='txt_report_data' align='left'><B>&nbsp;</td></tr>");
      m_amount_cr = m_amount_cr + rs6.getDouble(2);
			more6 = rs6.next();
			}

			out.println("<tr><td width='10%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(m_amount_cr)+"</td></tr>");

			out.println("<tr></tr>");	
			
			out.println("</table>"); 
		
			//----------------------------------------------------------------------------------
			
			
			

			out.println("<table align='center' width='100%' class='table' border=0>"); 

			double m_odi_amt=0;
			//m_odi_amt=m_amount - m_amount1-m_amount_cr;
			m_odi_amt=m_amount+ m_other_tot - m_amount1-m_amount_cr;//Mod By Sandun on 04-11-2009

			out.println("<tr><td width='30%' class='txt_report_data'><b>Overdue Amount As At "+m_date_type+"</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			

			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(m_odi_amt)+"</td></tr>");
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' border=1>"); 



			double m_rental=0;
			
			while(rs3.next()){
			m_rental=m_rental+rs3.getDouble(1);


			out.println("<tr><td width='30%' class='txt_report_data'><b>Future Rental Receivables (Gross)</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><b>"+nf.format(rs3.getDouble(1))+"</td>");		
			out.println("</tr>");
			}


			out.println("</table>"); 



			out.println("<table align='center' width='100%' class='table' border=1>"); 
			double m_charges=0;
		
			while(rs4.next()){
			m_charges=m_charges+rs4.getDouble(1);

			out.println("<tr><td width='30%' class='txt_report_data'><b>Overdue Interest Charges As At "+m_date_type+" </td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			out.println("<td width='15%' class='txt_report_data' align='right'><b>"+nf.format(rs4.getDouble(1))+"</td>");		
			out.println("</tr>");



			}


			out.println("</table>"); 
			
			/*rs5 = stmt5.executeQuery(" SELECT SUM(A.BALANCE_TO_BE_RECEIVED),A.INVOICE_TYPE,B.DESCRIPTION "+
														 	 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
														   " WHERE A.INVOICE_TYPE = B.SUB_TYPE_CODE "+
														   " AND A.FINANCE_NO = UPPER('"+m_finance_no+"') "+
														   " AND A.VALUE_DATE < TO_DATE('"+m_date+"','DD-MM-YYYY')"+
														   " GROUP BY A.INVOICE_TYPE,B.DESCRIPTION ");
																*/
					//Mod By Sandun on 29-10-2009											
				/*rs5 = stmt5.executeQuery(" SELECT SUM(A.BALANCE_TO_BE_RECEIVED),A.INVOICE_TYPE,NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE),A.INVOICE_TYPE)  "+
																 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
																 " WHERE A.FINANCE_NO = UPPER('"+m_finance_no+"')  "+
																 " AND A.VALUE_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																 " AND A.INVOICE_TYPE NOT IN ('INV_GENER','LEGAL_ODI')  "+
																 " AND A.BALANCE_TO_BE_RECEIVED > 0 "+
																 " AND A.ACTIVE_STATUS = 'Y' "+
																 " GROUP BY A.INVOICE_TYPE ");
			
			boolean more5 = rs5.next();
			double m_other_tot =0.0;
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			while(more5){
			m_other_tot =m_other_tot + rs5.getDouble(1);
			out.println("<tr><td width='83%' class='txt_report_data'><b>"+rs5.getString(3)+"</td>");
			out.println("<td width='17%' class='txt_report_data' align='right'><B>"+nf.format(rs5.getDouble(1))+"</td></tr>");
			more5 = rs5.next();
			}
			out.println("</table>");
			*/
			/*out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr><td width='30%'>&nbsp</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");
			out.println("<td width='15%' >&nbsp</td></tr>");
			out.println("</table>");
			*/					
							
								
			out.println("<BR>");
			double m_total=0;
			//m_total=m_odi_amt+ m_rental+ m_charges+ m_insurance;
			//m_total=m_odi_amt+m_rental+ m_charges+ m_other_tot;
			m_total=m_odi_amt+m_rental+ m_charges;//Mod By Sandun on 04-11-2009
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr><td width='30%' class='txt_report_data'><b>Total Receivables As At "+m_date_type+"</td>");
			out.println("<td width='20%'>&nbsp</td>");
			out.println("<td width='10%'>&nbsp</td>");
			out.println("<td width='15%'>&nbsp</td>");


			out.println("<td width='15%' class='txt_report_data' align='right'><B>"+nf.format(m_total)+"</td></tr>");
			out.println("</table>");
			out.println("</table>");
			out.println("</table>"); 
			out.println("<BR>");
			out.println("<BR>");
		
			/*//Sandun on 08-04-2009
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>");
			out.println("<td width='10%'>&nbsp</td>");
		
			out.println("<td width='20%'><b>....................................................................</td>");
			out.println("<td width='40%'>&nbsp</td>");
			out.println("<td width='20%'><b>....................................................................</td>");
			out.println("<td width='10%'>&nbsp</td>");


			out.println("</tr>");
				
			out.println("<tr>");
			out.println("<td width='10%'>&nbsp</td>");
	
			out.println("<td width='20%' class='txt_report_data' align=center><b>Prepared By</td>");
			out.println("<td width='40%'>&nbsp</td>");
			out.println("<td width='20%' class='txt_report_data' align=center><b>Authorised By</td>");
			out.println("<td width='10%'>&nbsp</td>");


			out.println("</tr>");
			out.println("</table>"); 
		*/
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
			conn.close();
			out.flush();
			out.close();
			}


			}

			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


