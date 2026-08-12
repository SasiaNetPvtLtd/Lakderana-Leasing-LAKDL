import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : NUWAN DE SILVA    DATE:27-03-2007

public class LAKDL_AF_RE_Receipt_Charges_Details extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt1,stmt_doc,stmt,stmt_invoice,stmt_app,stmt2,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
  java.lang.Math a;

    
  public ResultSet rs1,rs_doc_charge,rs2,rs3;
  public ResultSet rs,rs_invoice,rs_app;

	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		
		Connection conn=null;
		Statement stmt1=null,stmt_doc=null,stmt=null,stmt_invoice=null,stmt_app=null,stmt2=null,stmt3=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null,nf1=null;
  		java.lang.Math a=null;

    
   		ResultSet rs1=null,rs_doc_charge=null,rs2=null,rs3=null;
   		ResultSet rs=null,rs_invoice=null,rs_app=null;

	 	String m_chksql=null;
		
		
		
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
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");

      stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt_doc=conn.createStatement();
			stmt=conn.createStatement();
			stmt_invoice=conn.createStatement();
			stmt_app=conn.createStatement();
			

			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CHARGES_DETAILS")){
				
				String m_string="";				
				String m_sql="";	
				String m_batch_no="";
				String m_client_name="";
				String m_application_no="";
				String m_Payement_Mode="";
				double m_other_invoice=0;
				double m_invoice=0;
				double m_invoice_termina=0;
				double m_odi_interest=0;
				double m_total=0;
				double rep_amount=0;  
				double _total_ter_amount=0;
				
				double m_Other_Charges=0;
				int m_Count=0;
				double m_NIBSM=0;
				int m_AMI=0;
				int m_PERIOD=0;
				double m_AMI_VALUES=0;
				double m_total_charge=0;
				double m_total_charge_rep=0;
				double m_Invoice_Amount=0; 
				double _m_Invoice_Amount=0; 
				double m_doc_charges=0;
				double _m_total_invoices=0; 
					
				double m_amo_charges=0;
			  int b_flag_date=0;
				double m_general_amount=0;
				double m_receipt_charges=0;
				double receipt_total=0;
				double receipt_total_rep=0;
				double m_nibsm_charges=0;
				double tot=0;
				double Total_amount_charges=0;
				double Total_amount_charges_rep=0;
				double amount_to_be_charged=0;
				
				String m_curr_code="";
				double m_exchange_rate=0;
				double tot_rep=0;
				String m_date="";
				
				String m_client_code=req.getParameter("client_code");
						
				rs1=stmt1.executeQuery(" SELECT "+ 
														" "+m_schema_name+".AF_CO_GET_CLIENT_NAME('"+m_client_code+"') FROM DUAL ");
			
			boolean more = rs1.next();										
				if(more){
					m_client_name=rs1.getString(1);
				//	m_date=rs1.getString(2);
					}		
					
								
				
							String		Sql_other_inv=" SELECT "+ 
							"	  FINANCE_NO,INVOICE_NO,SUM(BALANCE_TO_BE_RECEIVED) OTHER_INV ,A.CURRENCY_CODE ,MAX(B.EXCHANGE_RATE),TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
							"   TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),SUM(TOTAL_AMOUNT), "+
							"   NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE)) INVOICE_TYPE"+
							"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
							"		WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							"   A.CURRENCY_CODE=B.CURR_CODE AND "+
						  "   TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
							"   INVOICE_TYPE NOT IN ('INV_GENER')   AND "+
							"   ACTIVE_STATUS='Y' AND "+
							"   BALANCE_TO_BE_RECEIVED>0 "+
							"   GROUP BY FINANCE_NO,INVOICE_NO ,A.CURRENCY_CODE,A.DUE_DATE,A.VALUE_DATE,INVOICE_TYPE "; 
							
							String		Sql_rent_inv=" SELECT "+ 
							"	  FINANCE_NO,INVOICE_NO,SUM(BALANCE_TO_BE_RECEIVED) INV,A.CURRENCY_CODE ,MAX(B.EXCHANGE_RATE),TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+
							"   TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),SUM(TOTAL_AMOUNT), "+
							"   "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) INVOICE_TYPE"+   
							"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
							"		WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							"   A.CURRENCY_CODE=B.CURR_CODE AND "+
						  "   TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
							"   INVOICE_TYPE='INV_GENER'   AND "+
							"   BALANCE_TO_BE_RECEIVED>0 "+
							"   GROUP BY FINANCE_NO,INVOICE_NO,A.CURRENCY_CODE,A.DUE_DATE,VALUE_DATE,INVOICE_TYPE ";
										
							
							String		Sql_termination_inv=" SELECT "+ 
							" FINANCE_NO, "+
							" TERMINATION_NO, "+
							" SUM(BALANCE_AMOUNT+DUE_AMOUNT+ODI_NET), "+
							" A.CURR_CODE , "+
							" MAX(B.EXCHANGE_RATE) ,"+
							" TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY' ), "+
							" SUM(AMOUNT+DUE_AMOUNT+ODI_NET), "+
							" TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY' ) "+
							" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A ,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
							"	WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							" A.CURR_CODE=B.CURR_CODE AND "+
						  " TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
							" BALANCE_AMOUNT >0 "+
							" GROUP BY FINANCE_NO,TERMINATION_NO,A.CURR_CODE,A.TERMINATION_VALIDITY_DATE,APPLY_DATE ";
							
							
							/*
							String		Sql_odi_interest="SELECT B.FINANCE_NO,A.INVOICE_NO,A.ODI ,A.CURR_CODE,A.EXCHANGE_RATE,A.DUE_DATE "+
							"               FROM    "+
							"           (SELECT INVOICE_NO,SUM(ODI_BAL_AMOUNT) ODI,A.CURR_CODE CURR_CODE ,MAX(B.EXCHANGE_RATE) EXCHANGE_RATE,TO_CHAR(A.DUE_DATE,'DD-MM-YYYY') DUE_DATE   "+ 
							" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B   "+
							" WHERE A.CURR_CODE=B.CURR_CODE AND "+
						  " TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
							" INVOICE_NO IN "+
							" (SELECT  "+
							" INVOICE_NO  "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+ 
							"		WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							" ACTIVE_STATUS='Y'   "+
							" )  "+
							"   GROUP BY INVOICE_NO,A.CURR_CODE,A.DUE_DATE "+
							"               )A, "+
							"                "+
							"               (SELECT "+
							" FINANCE_NO,INVOICE_NO  "+
							"							 FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
							"		WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							"							 ACTIVE_STATUS='Y'   "+
							"							 )  B    "+
							"               WHERE A.INVOICE_NO=B.INVOICE_NO "; */
							
							
				      String	Sql_odi_interest="SELECT B.FINANCE_NO,A.INVOICE_NO,A.ODI ,A.CURR_CODE,A.EXCHANGE_RATE,A.DUE_DATE,A.VALUE_DATE, TOTAL_AMOUNT, INVOICE_TYPE "+
							          " FROM "+
												" (SELECT A.INVOICE_NO,SUM(ODI_BAL_AMOUNT) ODI,A.CURR_CODE CURR_CODE ,MAX(B.EXCHANGE_RATE) EXCHANGE_RATE, "+
												" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY') DUE_DATE, TO_CHAR(C.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
												" SUM(TOTAL_AMOUNT) TOTAL_AMOUNT, "+
												" "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) INVOICE_TYPE "+
												" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A, "+
												" "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B, "+
												" "+m_schema_name+".AF_CO_PRO_INVOICE C "+
												" WHERE A.CURR_CODE=B.CURR_CODE AND "+
												" A.INVOICE_NO = C.INVOICE_NO AND "+
												" TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
												" A.INVOICE_NO IN "+
												" (SELECT INVOICE_NO "+
												" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
												" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
												" ACTIVE_STATUS='Y' "+
												" ) "+
												" GROUP BY A.INVOICE_NO,A.CURR_CODE,A.DUE_DATE,C.VALUE_DATE,INVOICE_TYPE "+
												" )A, "+
												" (SELECT FINANCE_NO,INVOICE_NO "+
												" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
												" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
												" ACTIVE_STATUS='Y' "+
												" ) B "+
												" WHERE A.INVOICE_NO=B.INVOICE_NO ";				
				
				
				
				
			/*
					String Sql_Receipt="SELECT "+
					" RECEIPT_NO,SET_TYPE,REC_TOTAL,CURR_CODE,EXCHANGE_RATE "+
					" FROM "+
					" (   "+
					" SELECT "+
					" RECEIPT_NO RECEIPT_NO, "+//1
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order') SET_TYPE,  "+ //2
					" (RECEIPT_AMOUNT - SUM(INVOICED_AMOUNT)) REC_TOTAL, "+ //3
					" B.CURR_CODE  CURR_CODE,"+ //4
					" MAX(C.EXCHANGE_RATE) EXCHANGE_RATE "+ //5
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B ,"+
					" "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE C "+
					" WHERE B.REC_NO=A.RECEIPT_NO AND  UPPER(CLIENT_CODE) =UPPER('"+m_client_code+"') AND  "+
					" B.CURR_CODE=C.CURR_CODE AND "+
					" STATUS NOT IN('RET','C','CAD') "+//CAD Added by Sandun on 26-08-2008   
					" GROUP BY A.RECEIPT_NO,A.RECEIPT_AMOUNT,B.SETTLE_MODE,B.CURR_CODE "+
					
					
					" UNION  "+
					
					" SELECT   "+
					" DISTINCT REC_NO RECEIPT_NO,    "+ //1
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order') SET_TYPE,   "+ //2
					" NVL(REC_AMOUNT,0) REC_TOTAL , "+ //3
					" A.CURR_CODE  CURR_CODE ,"+ //4
					" MAX(B.EXCHANGE_RATE) EXCHANGE_RATE "+ //5
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+ 
					" "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
					"    WHERE  UPPER(CLIENT_CODE) =UPPER('"+m_client_code+"')    "+
					"    AND A.STATUS NOT IN('RET','C','CAD') AND   "+ //CAD Added by Sandun on 26-08-2008 
					"    A.CURR_CODE=B.CURR_CODE AND "+
					"    RENTAL_OTER_INVOICE >0  AND  "+
					"    REC_NO NOT IN (  "+
					"    SELECT RECEIPT_NO   "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+   
					"    WHERE ALLOCATION_NO IS NOT NULL ) "+
					"    GROUP BY REC_NO,SETTLE_MODE,REC_AMOUNT,A.CURR_CODE "+
					"   )  "+
					"   WHERE REC_TOTAL >0  "+
					"   ORDER BY RECEIPT_NO ";     //Commented By Sandun on 07-10-2008
					
					
					*/
					
									
					
					 String Sql_Receipt= " SELECT "+   //Added By Sandun on 07-10-2008
															 " DISTINCT A.REC_NO RECEIPT_NO,  "+   
															 " DECODE(A.SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order') SET_TYPE,    "+
															 " NVL(C.BAL_TOBE_RECEIVE,0) BAL_TOBE_RECEIVE ,  "+
															 " A.CURR_CODE  CURR_CODE , "+
															 " MAX(B.EXCHANGE_RATE) EXCHANGE_RATE "+  
															 " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A,"+  
															 "      "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B ,"+
										           "      "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL C "+
															 " WHERE A.STATUS NOT IN('RET','C','CAD')"+ 
										           " AND   A.CURR_CODE=B.CURR_CODE "+
										           " AND   A.REC_NO = C.REC_NO "+
										           " AND   C.BAL_TOBE_RECEIVE >0 "+ 
										           " AND   UPPER(CLIENT_CODE) =UPPER('"+m_client_code+"') "+  
															 " GROUP BY A.REC_NO,A.SETTLE_MODE,C.BAL_TOBE_RECEIVE,A.CURR_CODE,B.EXCHANGE_RATE ";
					
					
					
					
					
					 out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Charges Details for Client Code - "+m_client_code+"  Client Name - "+m_client_name+" </B></TD></TR>");
					 out.println("</TABLE>");
						
 						out.println("<table align='center' width='100%' class='table' >");						

						out.println("<tr >");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' >Client Code</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='*%' align='left' onClick=\"show_client('"+m_client_code+"')\" style='cursor:hand' ><DIV class=div_input><u><b>"+m_client_code+"</u></DIV></td>");
						//out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' >Client Name</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='*%' align='left' onClick=\"show_client('"+m_client_code+"')\" style='cursor:hand' ><DIV class=div_input><u><b>"+m_client_name+"</u></DIV></td>");
						//out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
					 out.println("<BR>");
					 out.println("<table align='center' width='100%' class='table' >");						
						
				/*		rs_app= stmt_app.executeQuery ("SELECT "+
						" APPLICATION_NO,CURRENCY_CODE  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
						" APPLICATION_STATUS='VERIFYL' ");	
			 */			
						
						rs_app= stmt_app.executeQuery ("SELECT "+
						" APPLICATION_NO,CURRENCY_CODE ,MAX(B.EXCHANGE_RATE) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
						" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND  "+
						" A.CURRENCY_CODE=B.CURR_CODE AND "+
						" TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
						" APPLICATION_STATUS='VERIFYL' 	 "+
						" GROUP BY APPLICATION_NO,CURRENCY_CODE "+
						" ORDER BY CURRENCY_CODE ");
					 
				    boolean more_app=rs_app.next();
				
				if(more_app){
				
				    out.println("<table align='center' width='100%' class='table' >");						

						out.println("<tr>");//class=txt_report_column
						out.println("<td width='1%'>&nbsp;</td>"); 
						out.println("<td width='60%' ><DIV class=div_input><b><u>Initial Charge For Finance Facilities</u></DIV></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");

						
						
						out.println("<table align='center' width='100%' class='table' >");						
						
						out.println("<tr  > ");//class=txt_report_column
						out.println("<td width='5%'>&nbsp;</td>"); 
						out.println("<td width='25%' class=report_row align='left' ><b>Application No</td>"); 
						out.println("<td width='10%' class=report_row align='center' ><b>Currency Code</td>"); 
						out.println("<td width='10%' class=report_row align='right' ><b>Exchange Rate</td>"); 
						out.println("<td width='25%' class=report_row align='right'><b>Chargeable Amount</td>");
						out.println("<td width='25%' class=report_row align='right' ><b>Rep.Curr.Chargeable Amount</td>"); 
						out.println("</tr>"); 
						
	  		}
					
				while(more_app){
						
						m_application_no=rs_app.getString(1);
						m_exchange_rate=rs_app.getDouble(3);
						m_curr_code=rs_app.getString(2);
				
						rs_invoice= stmt_invoice.executeQuery ("SELECT INVOICE_NO,PRICING_NO "+
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
						" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
						"  PURCHASE_ORDER_NO IS NULL ");
						
						boolean 	more_invoice=rs_invoice.next();	
						
						String m_invoice_no="";
						String m_pricing_no="";

						
						//Assiging Variables To Zero Values At The Begining
						
						tot=0;
						m_NIBSM=0;
						m_AMI_VALUES=0;
						m_amo_charges=0;
						m_general_amount=0;
						m_nibsm_charges=0;
						tot_rep=0;
						
						while(more_invoice)
						
						{
						
							m_invoice_no=rs_invoice.getString(1);
							m_pricing_no=rs_invoice.getString(2);

							
						/*	rs= stmt.executeQuery ("SELECT "+
							" NIBSM, "+
							" AMI, "+
							" PERIOD "+	
							" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
							
							more=rs.next();
							
							if(more)
							{
							m_NIBSM=rs.getDouble(1);
							m_AMI=rs.getInt(2);
							m_PERIOD=rs.getInt(3);
							
							}*/
							
							/*int count=0;
							if(m_AMI>0){
							count=(m_PERIOD-m_AMI);
							
							rs= stmt.executeQuery ("SELECT "+
							"	SUM(GRENTAL_AMOUNT) SUM "+
							"	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
							"	AND AMI_AMOUNT >0  AND INSTALLMENT_NO >=("+count+") "); //AND INSTALLMENT_NO >=("+count+")
							
							}
							
							more=rs.next();
							if(more)
							{
							m_AMI_VALUES=rs.getDouble(1);
							}
							*/
							
						//added by nuwan de silva on 27-08-07	
						rs= stmt.executeQuery ("SELECT "+
					  " NVL("+m_schema_name+".AF_CO_GET_AMI_AMOUNT('"+m_application_no+"','"+m_pricing_no+"','"+m_invoice_no+"'),0) FROM DUAL ");

						more=rs.next();
						if(more){
						m_AMI_VALUES=rs.getDouble(1);
						}
							
							rs= stmt.executeQuery ("SELECT "+
							"  NVL(SUM(AMOUNT),0) "+
							"  FROM  "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES "+
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') AND "+
							"  CHARGE_TYPE='INV' ");
							
							more=rs.next();
							if(more)
							{
							m_amo_charges=rs.getDouble(1); 
							}
							
							
							rs= stmt.executeQuery ("SELECT "+
							" SUM(GRENTAL_AMOUNT) "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
							" AND INSTALLMENT_NO=0 ");
							
							
							more=rs.next();
							if(more)
							{
							m_general_amount=rs.getDouble(1);
							}
							
							
							
							rs= stmt.executeQuery ("SELECT "+
							" SUM(NIBSM) "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
							
							
							more=rs.next();
							if(more)
							{
							m_nibsm_charges=rs.getDouble(1);
							
							}
							
						
							
							tot=tot+m_AMI_VALUES+m_amo_charges+m_general_amount+m_nibsm_charges;
							more_invoice=rs_invoice.next();				
						
						}
						
						tot_rep=tot*m_exchange_rate;		
					
					  out.println("<tr>");
						out.println("<td width='5%'>&nbsp;</td>"); 
						out.println("<td width='25%'  align='left'  onClick=\"show_application_detail_drill('"+m_application_no+"')\" style='cursor:hand' ><u>"+m_application_no+"</u></td>"); 
						out.println("<td width='10%'  align='center'   >"+m_curr_code+"</td>"); 
						out.println("<td width='10%'  align='right'   >"+m_exchange_rate+"</td>"); 
						out.println("<td width='25%'  align='right' onClick=\"show_application_charge_drill('"+m_application_no+"',"+tot+")\" style='cursor:hand' ><u>"+nf.format(tot)+"</u></td>");
						out.println("<td width='25%'  align='right' onClick=\"show_application_charge_drill('"+m_application_no+"',"+tot+")\" style='cursor:hand' ><u>"+nf.format(tot_rep)+"</u></td>");
						out.println("</tr>"); 
					//================================================================================
						Total_amount_charges=Total_amount_charges+tot;									
						Total_amount_charges_rep=Total_amount_charges_rep+tot_rep;		
					
					  more_app=rs_app.next();			
								
				}
				    
				 	  out.println("<tr>");
						out.println("<td width='5%'>&nbsp;</td>"); 
						out.println("<td width='20%'  align='left'  ><b>Applications Sub Total</b></td>"); 
						out.println("<td width='10%'>&nbsp;</td>"); 
						out.println("<td width='10%'>&nbsp;</td>"); 
						out.println("<td width='25%'  align='right' ><b>&nbsp;</b></td>");
						out.println("<td width='25%'  align='right' ><b>"+nf.format(Total_amount_charges_rep)+"</b></td>");
						out.println("</tr>"); 
				    
					
				
								
				
						out.println("</table>");
						
						//ODI INTEREST ===========================================================================					
						rs1=stmt1.executeQuery(Sql_odi_interest);
						more=rs1.next();
						if(more)
						{
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr>");//class=txt_report_column
						out.println("<td width='1%'>&nbsp;</td>"); 
						out.println("<td width='60%' ><DIV class=div_input><b><u>Over Due Interest</u></DIV></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						out.println("<table align='center' width='100%' class='table' >");			
						out.println("<tr  > ");//class=txt_report_column
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%' class=report_row align='left' ><b>Finance No</td>"); 
						out.println("<td width='12%' class=report_row align='left'><b>Invoice No</td>");
						out.println("<td width='12%' class=report_row align='left'><b>Invoice Type</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Invoice Date</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Due Date</td>");
						//out.println("<td width='8%' class=report_row align='center'><b>Currency Code</td>");
						//out.println("<td width='10%' class=report_row align='left'><b>Exchange Rate</td>");
						out.println("<td width='15%' class=report_row align='left'><b>Invoice Amount</td>");
						out.println("<td width='15%' class=report_row align='right' ><b>Balance To Be Received</td>"); 
						//out.println("<td width='20%' class=report_row align='right' ><b>Rep.Curr.Balance To Be Received</td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						}
						
						rep_amount=0;
					  double _total_app=0;
						double _total_app_rep=0;
			
						while(more){
						
						out.println("<table align='center' width='100%' class='table' >");			
						String m_fin_no=rs1.getString(1);
						_total_app=0;
						_total_app_rep=0;
						while(m_fin_no.equals(rs1.getString(1))){
						
						rep_amount=rs1.getDouble(3)*rs1.getDouble(5);
						out.println("<tr>");
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%'  align='left' onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); 
						out.println("<td width='12%'  align='left' onClick=\"show_invoice_drill('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='12%'  align='center'>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%'  align='center'>"+rs1.getString(7)+"</td>");
						out.println("<td width='10%'  align='center'>"+rs1.getString(6)+"</td>"); 
						//out.println("<td width='8%'  align='center'>"+rs1.getString(4)+"</td>"); 
						//out.println("<td width='10%'  align='right'>"+rs1.getDouble(5)+"</td>"); 
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(8))+"</td>"); 
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(3))+"</td>"); 
						//out.println("<td width='20%'  align='right'>"+nf.format(rep_amount)+"</td>"); 
						out.println("</tr>"); 
						m_total_charge=m_total_charge+rs1.getDouble(3);
						m_total_charge_rep=m_total_charge_rep+rep_amount;
						_total_app=_total_app+rs1.getDouble(3);
						m_Invoice_Amount = m_Invoice_Amount+rs1.getDouble(8);
						_total_app_rep=_total_app_rep+rep_amount;
						more=rs1.next();
						if(!more){
						break;
						}
				
						}
						
						out.println("<tr>");
						out.println("<td width ='5%' >&nbsp;</td>"); 
						out.println("<td width ='20%'><b><b>Sub Total</td>"); 
						out.println("<td width ='12%'>&nbsp;</td>"); 
						out.println("<td width ='12%'>&nbsp;</td>");
						out.println("<td width ='10%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>"); 
						//out.println("<td width ='8%'>&nbsp;</td>"); 
						//out.println("<td width ='10%' align='center'>&nbsp;</td>"); 
						out.println("<td width ='15%' align='right'><b>"+nf.format(m_Invoice_Amount)+"</td>");
						out.println("<td width ='15%' align='right'><b>"+nf.format(_total_app)+"</td>"); 
						//out.println("<td width ='10%' align='right'><b>"+nf.format(_total_app_rep)+"</td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						out.println("<br><br>");
						_m_total_invoices+=_total_app;
						_m_Invoice_Amount+=m_Invoice_Amount;
						
					 }
						
											
						//OTHER INVOICES ======================================================================
						rs1=stmt1.executeQuery(Sql_other_inv);
						 more=rs1.next();
						if(more)
						{
						
						out.println("<br><br>");
						
						out.println("<table align='center' width='100%' class='table' >");						

						out.println("<tr>");//class=txt_report_column
						out.println("<td width='1%'>&nbsp;</td>"); 
						out.println("<td width='60%' ><DIV class=div_input><b><u>Other Invoices</u></DIV></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						out.println("<table align='center' width='100%' class='table' >");			
						out.println("<tr  > ");//class=txt_report_column
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%' class=report_row align='left' ><b>Finance No</td>"); 
						out.println("<td width='12%' class=report_row align='left'><b>Invoice No</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Invoice Type</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Due Date</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Invoice Date</td>");
						//out.println("<td width='8%' class=report_row align='center'><b>Currency Code</td>");
						//out.println("<td width='10%' class=report_row align='left'><b>Exchange Rate</td>");
						out.println("<td width='15%' class=report_row align='left'><b>Invoice Amount</td>");
						out.println("<td width='15%' class=report_row align='right' ><b>Balance To Be Received</td>"); 
						//out.println("<td width='20%' class=report_row align='right' ><b>Rep.Curr.Balance To Be Received</td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						}
					
					
						rep_amount=0;
					   _total_app=0;
						 _total_app_rep=0;
			
						while(more){
						
						out.println("<table align='center' width='100%' class='table' >");			
						String m_fin_no=rs1.getString(1);
						_total_app=0;
						_total_app_rep=0;
						m_Invoice_Amount=0;
						while(m_fin_no.equals(rs1.getString(1))){
						
						rep_amount=rs1.getDouble(3)*rs1.getDouble(5);
						out.println("<tr>");
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%'  align='left' onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); 
						out.println("<td width='12%'  align='left' onClick=\"show_invoice_drill('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%'  align='center'>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%'  align='center'>"+rs1.getString(6)+"</td>"); 
						out.println("<td width='10%'  align='center'>"+rs1.getString(7)+"</td>"); 
						//out.println("<td width='8%'  align='center'>"+rs1.getString(4)+"</td>"); 
						//out.println("<td width='10%' align='right'>"+rs1.getDouble(5)+"</td>"); 
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(8))+"</td>"); 
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(3))+"</td>"); 
						//out.println("<td width='20%'  align='right'>"+nf.format(rep_amount)+"</td>"); 
						out.println("</tr>"); 
						m_total_charge=m_total_charge+rs1.getDouble(3);
						m_Invoice_Amount = m_Invoice_Amount+rs1.getDouble(8); 
						m_total_charge_rep=m_total_charge_rep+rep_amount;
												
						_total_app=_total_app+rs1.getDouble(3);
						_total_app_rep=_total_app_rep+rep_amount;
						//out.println("more"+more);
						more=rs1.next();
						//out.println("moresssssssssss"+more);
						if(!more){
						break;
						}
				
						}
						
						out.println("<tr>");
						out.println("<td width ='5%' >&nbsp;</td>"); 
						out.println("<td width ='20%'><b><b>Sub Total</td>"); 
						out.println("<td width ='12%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>");
						//out.println("<td width ='8%'>&nbsp;</td>"); 
						//out.println("<td width ='10%' align='center'>&nbsp;</td>"); 
						out.println("<td width ='15%' align='right'><b>"+nf.format(m_Invoice_Amount)+"</td>"); 
						out.println("<td width ='15%' align='right'><b>"+nf.format(_total_app)+"</td>"); 
						//out.println("<td width ='10%' align='right'><b>"+nf.format(_total_app_rep)+"</td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						out.println("<br><br>");
						_m_total_invoices+=_total_app_rep;
						
					 }
						
						//out.println("</table>");
						
						
						
						//RENTAL INVOICES-----------------------------------------------
						rs1=stmt1.executeQuery(Sql_rent_inv);
						 more=rs1.next();
						if(more)
						{
						
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr>");//class=txt_report_column
						out.println("<td width='1%'>&nbsp;</td>"); 
						out.println("<td width='60%' ><DIV class=div_input><b><u>Rental Invoices</u></DIV></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						out.println("<table align='center' width='100%' class='table' >");			
						out.println("<tr  > ");//class=txt_report_column
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%' class=report_row align='left' ><b>Finance No</td>"); 
						out.println("<td width='12%' class=report_row align='left'><b>Invoice No</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Invoice Type</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Due Date</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Invoice Date</td>");
						out.println("<td width='15%' class=report_row align='left'><b>Invoice Amount</td>");
						//out.println("<td width='8%' class=report_row align='center'><b>Currency Code</td>");
						//out.println("<td width='10%' class=report_row align='left'><b>Exchange Rate</td>");
						out.println("<td width='15%' class=report_row align='right' ><b>Balance To Be Received</td>"); 
						//out.println("<td width='20%' class=report_row align='right' ><b>Rep.Curr.Balance To Be Received</td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						}
					  rep_amount=0;
					  _total_app=0;
						_total_app_rep=0;
						while(more){
						out.println("<table align='center' width='100%' class='table' >");			
						String m_fin_no=rs1.getString(1);
						_total_app=0;
						m_Invoice_Amount=0; 
						_total_app_rep=0;
						while(m_fin_no.equals(rs1.getString(1))){
						rep_amount=rs1.getDouble(3)*rs1.getDouble(5);
						
						out.println("<tr>");
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%'  align='left' onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); 
						out.println("<td width='12%'  align='left' onClick=\"show_invoice_drill('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%'  align='center'>"+rs1.getString(9)+"</td>"); 
						out.println("<td width='10%'  align='center'>"+rs1.getString(6)+"</td>"); 
						out.println("<td width='10%'  align='center'>"+rs1.getString(7)+"</td>");
						//out.println("<td width='8%'  align='center'>"+rs1.getString(4)+"</td>"); 
						//out.println("<td width='10%'  align='right'>"+rs1.getDouble(5)+"</td>");
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(8))+"</td>"); 
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(3))+"</td>"); 
						//out.println("<td width='20%'  align='right'>"+nf.format(rep_amount)+"</td>"); 
						out.println("</tr>"); 
						m_total_charge=m_total_charge+rs1.getDouble(3);
						m_total_charge_rep=m_total_charge_rep+rep_amount;
						
						_total_app=_total_app+rs1.getDouble(3);
						m_Invoice_Amount = m_Invoice_Amount+rs1.getDouble(8); 
						_total_app_rep=_total_app_rep+rep_amount;
						
						more=rs1.next();
						if(!more){
						break;
						}
				
						}
						
						out.println("<tr>");
						out.println("<td width ='5%' >&nbsp;</td>"); 
						out.println("<td width ='20%'><b><b>Sub Total</td>"); 
						out.println("<td width ='12%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>");
						out.println("<td width ='10%'>&nbsp;</td>"); 
						//out.println("<td width ='8%'>&nbsp;</td>"); 
						//out.println("<td width ='10%' align='center'>&nbsp;</td>"); 
						out.println("<td width ='15%' align='right'><b>"+nf.format(m_Invoice_Amount)+"</td>"); 
						out.println("<td width ='15%' align='right'><b>"+nf.format(_total_app)+"</td>"); 
						//out.println("<td width ='10%' align='right'><b>"+nf.format(_total_app_rep)+"</td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						out.println("<br><br>");
						_m_total_invoices+=_total_app;
						_m_Invoice_Amount+=m_Invoice_Amount;
						}
										
						//TERMINATION DETAILS =============================================================
						/*
						rs1=stmt1.executeQuery(Sql_termination_inv);
						more=rs1.next();
						if(more)
						{
						out.println("<br><br>");
						
						out.println("<table align='center' width='100%' class='table' >");						

						out.println("<tr>");//class=txt_report_column
						out.println("<td width='1%'>&nbsp;</td>"); 
						out.println("<td width='60%' ><DIV class=div_input><b><u>Termination</u></DIV></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
/*						out.println("<table align='center' width='100%' class='table' >");		
						out.println("<tr  > ");//class=txt_report_column
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%' class=report_row align='left' ><b>Finance No</td>"); 
						out.println("<td width='20%' class=report_row align='left'><b>Termination No</td>");
						out.println("<td width='10%' class=report_row align='center'><b>Currency Code</td>");
						out.println("<td width='10%' class=report_row align='right'><b>Exchange Rate</td>");
						out.println("<td width='15%' class=report_row align='right' ><b>Balance To Be Received</td>"); 
						out.println("<td width='20%' class=report_row align='right' ><b>Rep.Curr.Balance To Be Received</td>"); 
					  out.println("</tr>"); 
	*
	
						out.println("<table align='center' width='100%' class='table' >");			
						out.println("<tr  > ");//class=txt_report_column
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%' class=report_row align='left' ><b>Finance No</td>"); 
						out.println("<td width='12%' class=report_row align='left'><b>Termination No</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Apply Date</td>");
						out.println("<td width='10%' class=report_row align='left'><b>Due Date</td>");
						//out.println("<td width='8%' class=report_row align='center'><b>Currency Code</td>");
						//out.println("<td width='10%' class=report_row align='left'><b>Exchange Rate</td>");
						out.println("<td width='15%' class=report_row align='right' ><b>Amount</td>"); 
						out.println("<td width='15%' class=report_row align='right' ><b>Balance To Be Received</td>"); 
						//out.println("<td width='20%' class=report_row align='right' ><b>Rep.Curr.Balance To Be Received</td>"); 
						out.println("</tr>"); 
						out.println("</table>");

						
						}
					
					  rep_amount=0;
					  _total_app=0;
						_total_app_rep=0;
						while(more){
						out.println("<table align='center' width='100%' class='table' >");			
						String m_fin_no=rs1.getString(1);
						_total_app=0;
						_total_app_rep=0;
						while(m_fin_no.equals(rs1.getString(1))){
						rep_amount=rs1.getDouble(3)*rs1.getDouble(5);
						
						out.println("<tr>");
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%'  align='left' onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); 
						out.println("<td width='12%'  align='left' onClick=\"show_invoice_drill('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%'  align='center'>"+rs1.getString(8)+"</td>"); 
						out.println("<td width='10%'  align='center'>"+rs1.getString(6)+"</td>"); 
						//out.println("<td width='8%'  align='center'>"+rs1.getString(4)+"</td>"); 
						//out.println("<td width='10%'  align='right'>"+rs1.getDouble(5)+"</td>"); 
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
						out.println("<td width='15%'  align='right'>"+nf.format(rs1.getDouble(3))+"</td>"); 
						//out.println("<td width='20%'  align='right'>"+nf.format(rep_amount)+"</td>"); 
						out.println("</tr>"); 
						m_total_charge=m_total_charge+rs1.getDouble(3);
						m_total_charge_rep=m_total_charge_rep+rep_amount;
						
						_total_app=_total_app+rs1.getDouble(3);
						_total_ter_amount=_total_ter_amount+rs1.getDouble(7);
						_total_app_rep=_total_app_rep+rep_amount;
						
						more=rs1.next();
						if(!more){
						break;
						}
				
						}
						
						out.println("<tr>");
						out.println("<td width ='5%' >&nbsp;</td>"); 
						out.println("<td width ='20%'><b><b>Sub Total</td>"); 
						out.println("<td width ='12%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>"); 
						out.println("<td width ='10%'>&nbsp;</td>"); 
						//out.println("<td width ='8%'>&nbsp;</td>"); 
						//out.println("<td width ='10%' align='center'>&nbsp;</td>"); 
						out.println("<td width ='15%' align='right'><b>"+nf.format(_total_ter_amount)+"</td>");
						out.println("<td width ='15%' align='right'><b>"+nf.format(_total_app)+"</td>"); 
						//out.println("<td width ='10%' align='right'><b>"+nf.format(_total_app_rep)+"</td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						out.println("<br><br>");
						_m_Invoice_Amount+=_total_ter_amount;
						_m_total_invoices+=_total_app;
						}
	*/										
					//	out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<td width='5%' ></td>"); 
					out.println("<td width='30%'  align='left'  ><b>Invoices Sub Total</b></td>"); 
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					//out.println("<td width='15%'  align='right' ><b>&nbsp;</b></td>");
					out.println("<td width='20%'  align='right' ><b>"+nf.format(_m_Invoice_Amount)+"</b></td>");
					out.println("<td width='20%'  align='right' ><b>"+nf.format(_m_total_invoices)+"</b></td>");
					out.println("</table>");


						
						out.println("<br>");
						
					//=====================Receipt Details=================================================================	
					rs= stmt.executeQuery(Sql_Receipt);
					more=rs.next();
					out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");//class=txt_report_column
						out.println("<td width='1%'>&nbsp;</td>"); 
						out.println("<td width='60%' ><DIV class=div_input><b><u>Receipt Details</u></DIV></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
					out.println("</table>");
					
					if(more){
				   out.println("<br>");	
					 out.println("<table align='center' width='100%' class='table' >");
						out.println("<td width='5%'></td>"); 
						out.println("<td width='20%' class=report_row align='left' ><b>Rec. Number</td>"); 
						out.println("<td width='20%' class=report_row align='left'><b>Sett. Mode</td>");
						out.println("<td width='10%' class=report_row align='center'><b>Currency Code</td>");
						out.println("<td width='10%' class=report_row align='right'><b>Exchange Rate</td>");
						out.println("<td width='15%' class=report_row align='right' ><b>Receipt Amount</td>"); 
						out.println("<td width='20%' class=report_row align='right' ><b>Rep.Curr.Receipt Amount</td>"); 
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					}
					rep_amount=0;
					while(more){
					rep_amount=rs.getDouble(3)*rs.getDouble(5);
					out.println("<tr>");
					out.println("<td width='5%' ></td>"); 
					out.println("<td width='20%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input align='center' >"+rs.getString(4)+"</td>");
					out.println("<td width='10%' class=div_input align='right' >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rep_amount)+"</td>");
				 	out.println("</tr>");
					receipt_total=receipt_total+rs.getDouble(3);
					receipt_total_rep=receipt_total_rep+rep_amount;
					more=rs.next();
					//_m_total_invoices+=_total_app_rep;
					
			  	}
																
      	  out.println("</table>");
					
					
					
			    out.println("<table align='center' width='100%' class='table' >");
					out.println("<td width='5%' ></td>"); 
					out.println("<td width='20%'  align='left'  ><b>Receipts Sub Total</b></td>"); 
					out.println("<td width='20%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='15%'  align='right' ><b>&nbsp;</b></td>");
					out.println("<td width='20%'  align='right' ><b>"+nf.format(receipt_total_rep)+"</b></td>");
					out.println("</table>");
					
					out.println("<br>"); 						
					out.println("<table align='center' width='100%' class='table' >");						
					
					out.println("<tr  > ");//class=txt_report_column
					out.println("<td width='5%'>&nbsp;</td>"); 
					out.println("<td width='20%' align='left'><b>Payable Amount</b></td>");
					out.println("<td width='20%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					
					Total_amount_charges_rep+=_m_total_invoices; //added by nuwan de silva on 29-10-07----------------
					
					//if(Total_amount_charges>receipt_total){
						if(Total_amount_charges_rep>receipt_total_rep){
					amount_to_be_charged=receipt_total_rep-Total_amount_charges_rep;
					out.println("<td width='15%' align='right'><b>"+nf.format(a.abs(amount_to_be_charged))+"</b></td>"); 
					}
					else
					{
					//amount_to_be_charged=0;
					amount_to_be_charged=receipt_total_rep-Total_amount_charges_rep;
					out.println("<td width='20%' align='right'><b>("+nf.format(amount_to_be_charged)+")</b></td>"); 
					}
														
					out.println("</tr>"); 
					out.println("</table>");
					
						
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			else if(m_chksql.equals("LOAD_CHARGES_DETAILS_IMPROVED")){
					String m_client_code=req.getParameter("client_code");
					String m_sys_date="";
					String m_sys_date_1="";
					String m_fullname="";
					String m_address1="";
					String m_address2="";
					String m_city_name="";
					
					rs=stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY'),TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
					if(rs.next()){
						m_sys_date=rs.getString(1);
						m_sys_date_1=rs.getString(2);
					}
					out.println("<html><head>"); 
					out.println("<title>Charges Details</title></head>");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					
					out.println("<script>");
					out.println("function save_data(){");
					out.println("m_table.innerHTML=\"\" ");
					out.println("window.print();");
		      out.println("}");
					
					/*out.println("function add_button(){");
					if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					}else{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
					}
					out.println("}");*/
			    out.println("</script>");
				
				 //out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				 out.println("<body leftmargin='0' topmargin='0' class=body>");	
					//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				 out.println("<body bgcolor='white'><br>");
				 out.println("<form name='Form1'>");
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
			out.println("<font size=3><p style='text-align:left'>");					
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  out.println("</table>");
			out.println("</font></p></blockquote>");	
			out.println("<font size=3><p style='text-align:left'>");				
			out.println("<table border='0' width='100%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' align='left' >Run Date:&nbsp;&nbsp;"+m_sys_date+"</td></tr>");
			out.println("</table>");
			out.println("</font></p>");
			   
					
          rs=stmt.executeQuery(" SELECT NVL(FULL_NAME,' '),NVL(ADDRESS1,' '),NVL(ADDRESS2,' '),NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),' ') "+
															 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE='"+m_client_code+"'");
			
			if(rs.next()){
					m_fullname=rs.getString(1);
					m_address1=rs.getString(2);
					m_address2=rs.getString(3);
					m_city_name=rs.getString(4);
			
			}
			
			out.println("<table border='0' width='100%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' align='left' ><b>"+m_fullname.toUpperCase()+"</b></td></tr>");
			out.println("<tr><td width='*%' align='left' ><b>"+m_address1.toUpperCase()+"</b></td></tr>");
			out.println("<tr><td width='*%' align='left' ><b>"+m_address2.toUpperCase()+"</b></td></tr>");
			out.println("<tr><td width='*%' align='left' ><b>"+m_city_name.toUpperCase()+"</b></td></tr>");
			out.println("</table>");
			
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td width='*%' align='left' ><b>Statement of Accounts as at "+m_sys_date+"</b></td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td width='*%' align='right' >In Rupees</td></tr>");
			out.println("</table>");
			
			/*rs1=stmt1.executeQuery(" SELECT A.FINANCE_NO,NVL(SUM(A.TOTAL_AMOUNT),0), "+
			                     " NVL("+m_schema_name+".AF_CO_GET_RENTALS_PAID_AMT(A.FINANCE_NO,'"+m_sys_date_1+"'),0), "+
                           " NVL("+m_schema_name+".AF_CO_GET_RENTAL_ARREARS(A.FINANCE_NO,'"+m_sys_date_1+"'),0), "+
                           " NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(A.FINANCE_NO,'"+m_sys_date_1+"'),0), "+
                           " NVL("+m_schema_name+".AF_CO_GET_OTH_CHG_AMT(A.FINANCE_NO,'"+m_sys_date_1+"'),0) "+
                           " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
                           " WHERE A.CLIENT_CODE='"+m_client_code+"' "+
                           " GROUP BY A.FINANCE_NO ");*/
			
			rs1=stmt1.executeQuery(" SELECT A.FINANCE_NO,NVL(SUM(A.TOTAL_AMOUNT),0), "+
			                     " NVL(SUM(A.SETTELE_AMOUNT),0), "+
                           " NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0), "+
                           " NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(A.FINANCE_NO,'"+m_sys_date_1+"'),0), "+
                           " NVL("+m_schema_name+".AF_CO_GET_OTH_CHG_AMT(A.FINANCE_NO,'"+m_sys_date_1+"'),0) "+
                           " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
                           " WHERE A.CLIENT_CODE='"+m_client_code+"'  AND ACTIVE_STATUS='Y' "+
													 " AND A.INVOICE_TYPE='INV_GENER' "+
													 " AND A.DUE_DATE<=TO_DATE('"+m_sys_date_1+"','DD-MM-YYYY') "+
                           " GROUP BY A.FINANCE_NO ");
			
			
			out.println("<table class=table width=100% cellpadding='0' bordercolor=black cellspacing='0' border='1'>");
			out.println("<tr>");
			out.println("<td width='16%'><b>Agreement No</b></td>");
			out.println("<td width='14%'><b>Gross Rental Due</b></td>");
			out.println("<td width='14%'><b>Rentals Paid</b></td>");
      out.println("<td width='14%'><b>Rental Arrears</b></td>");
      out.println("<td width='14%'><b>ODI as on date</b></td>");
      out.println("<td width='14%'><b>Other charges as on date</b></td>");
      out.println("<td width='14%'><b>Total Due</b></td>");
      out.println("</tr>");
			double m_total_due=0;
			double m_rental_due=0;
			double m_rentals_paid=0;
			double m_rental_arrears=0;
			double m_odi_amt=0;
			double m_other_charges=0;
			double m_total=0;
			while(rs1.next()){
			m_total_due=rs1.getDouble(4)+rs1.getDouble(5)+rs1.getDouble(6);
			
			out.println("<tr>");
			out.println("<td ><b>"+rs1.getString(1)+"</b></td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(2))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(3))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(4))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(5))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(6))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(m_total_due)+"</td>");
			out.println("</tr>");
			m_rental_due=m_rental_due+rs1.getDouble(2);
			m_rentals_paid=m_rentals_paid+rs1.getDouble(3);
			m_rental_arrears=m_rental_arrears+rs1.getDouble(4);
			m_odi_amt=m_odi_amt+rs1.getDouble(5);
			m_other_charges=m_other_charges+rs1.getDouble(6);
			m_total=m_total+m_total_due;
			}
			out.println("<tr>");
			out.println("<td>Total</td>");
			out.println("<td style='text-align:right'><b>"+nf.format(m_rental_due)+"</b></td>");
			out.println("<td style='text-align:right'><b>"+nf.format(m_rentals_paid)+"</b></td>");
			out.println("<td style='text-align:right'><b>"+nf.format(m_rental_arrears)+"</b></td>");
			out.println("<td style='text-align:right'><b>"+nf.format(m_odi_amt)+"</b></td>");
			out.println("<td style='text-align:right'><b>"+nf.format(m_other_charges)+"</b></td>");
			out.println("<td style='text-align:right'><b>"+nf.format(m_total)+"</b></td>");
			out.println("</tr>");
			
			
			out.println("</table>");
			
			
			String Sql_Receipt= " SELECT "+  
													" DISTINCT A.REC_NO RECEIPT_NO,  "+ 
													" NVL(C.REC_AMOUNT,0), "+
													" NVL(C.ALLOCATED_AMOUNT,0), "+
													" NVL(C.BAL_TOBE_RECEIVE,0) BAL_TOBE_RECEIVE   "+
													" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A,"+  
													"      "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B ,"+
										      "      "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL C "+
													" WHERE A.STATUS NOT IN('RET','C','CAD')"+ 
										      " AND   A.CURR_CODE=B.CURR_CODE "+
										      " AND   A.REC_NO = C.REC_NO "+
										      " AND   C.BAL_TOBE_RECEIVE >0 "+ 
										      " AND   UPPER(CLIENT_CODE) =UPPER('"+m_client_code+"') ";  
												  
					
			rs1=stmt1.executeQuery(Sql_Receipt);
			out.println("<br>");
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td width='*%' align='left' ><b>Unallocated receipt details</b></td></tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td>");
			out.println("<table border='1' width='60%' class='table' align='left' cellspacing='0' cellpadding='0' bordercolor='black'>");
			out.println("<tr>");
			out.println("<td width='15%'><b>Receipt No</b></td>");
			out.println("<td width='15%'><b>Receipt Amount</b></td>");
			out.println("<td width='15%'><b>Allocated Amount</b></td>");
			out.println("<td width='15%'><b>Balance Amount</b></td>");
			out.println("</tr>");
			double m_tot_balance_amt=0;
			while(rs1.next()){
			out.println("<tr>");
			out.println("<td><b>"+rs1.getString(1)+"</b></td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(2))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(3))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(4))+"</td>");
			out.println("</tr>");
			m_tot_balance_amt=m_tot_balance_amt+rs1.getDouble(4);
			}
			out.println("<tr>");
			out.println("<td><b>Total</b></td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td style='text-align:right'><b>"+nf.format(m_tot_balance_amt)+"</b></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</td></tr>");
			out.println("</table>");
			/*
      rs1=stmt1.executeQuery(" SELECT A.FINANCE_NO, "+
                             " TO_CHAR(A.VALUE_DATE,'MONTH YYYY'), "+
														 " NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0), "+
                             " NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(A.FINANCE_NO,TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY')),0) "+
                             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
                             " WHERE A.INVOICE_TYPE='INV_GENER' "+
                             " AND A.VALUE_DATE<=TO_DATE('"+m_sys_date_1+"','DD-MM-YYYY') "+
                             " AND A.CLIENT_CODE='"+m_client_code+"' "+
                             " AND A.BALANCE_TO_BE_RECEIVED <>0 "+
//" OR LAKDL.AF_CO_GET_ODI_ARREARS(A.FINANCE_NO,TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'))>0
                             " GROUP BY A.FINANCE_NO,A.VALUE_DATE ");
															*/ //---------------Commented By Sandun on 26-01-2009
			/*
			 rs1=stmt1.executeQuery(" SELECT FINANCE_NO,VALUE_DATE,SUM(BALANCE),SUM(ARREARS) "+ //Added By Sandun on 26-01-2009
															" FROM( "+
															" SELECT A.FINANCE_NO  FINANCE_NO,  "+
															" TO_CHAR(A.VALUE_DATE,'MONTH YYYY') VALUE_DATE, "+
															" NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) BALANCE, "+
															" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(A.FINANCE_NO,TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY')),0)  ARREARS"+
															" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
															" WHERE A.INVOICE_TYPE='INV_GENER' "+
															" AND A.VALUE_DATE<=TO_DATE('"+m_sys_date_1+"','DD-MM-YYYY')  "+
															" AND A.CLIENT_CODE='"+m_client_code+"' "+
															" AND A.BALANCE_TO_BE_RECEIVED <>0 "+ 
															" GROUP BY A.FINANCE_NO,A.VALUE_DATE "+
															" UNION ALL"+														
															" SELECT A.FINANCE_NO FINANCE_NO, "+
															" TO_CHAR(A.VALUE_DATE,'MONTH YYYY') VALUE_DATE, "+
															" NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) BALANCE, "+
															" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(A.FINANCE_NO,TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY')),0) ARREARS"+
															" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY B"+
															" WHERE A.INVOICE_NO = B.INVOICE_NO(+) "+
															" AND A.INVOICE_TYPE='INV_GENER' "+
															" AND A.VALUE_DATE<=TO_DATE('"+m_sys_date_1+"','DD-MM-YYYY') "+
															" AND A.CLIENT_CODE='"+m_client_code+"' "+
															" AND A.BALANCE_TO_BE_RECEIVED <> 0 "+
															" AND B.ODI_BAL_AMOUNT > 0 "+
															" GROUP BY A.FINANCE_NO,A.VALUE_DATE "+
															" ) "+
															" GROUP BY FINANCE_NO,VALUE_DATE ORDER BY FINANCE_NO,TO_DATE(VALUE_DATE,'MM-YYYY') DESC ");
			*/
			
			   rs1=stmt1.executeQuery(" SELECT A.FINANCE_NO, "+//1 //Added By Sandun on 29-01-2009
																" TO_CHAR(A.VALUE_DATE,'Month YYYY'), "+//2																
																" SUM(A.BALANCE_TO_BE_RECEIVED), "+//4
																" SUM(B.ODI_BAL_AMOUNT) "+//3
																" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY B "+
																" WHERE A.INVOICE_NO= B.INVOICE_NO "+
																" AND A.CLIENT_CODE='"+m_client_code+"' "+
																" AND A.VALUE_DATE <= TO_DATE('"+m_sys_date_1+"','DD-MM-YYYY')  "+
																" AND B.ODI_BAL_AMOUNT > 0 "+
																" GROUP BY A.VALUE_DATE,A.FINANCE_NO ");

			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td width='*%' align='left' ><b>Breakup Of Rental And ODI Outstanding as at "+m_sys_date+"</b></td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td>");
			//Added by Dineth on 2009-02-10
			double gross_due=0;
			double odi_amt_1=0;
			String fin_no_2="";
			//End by Dineth on 2009-02-10
			out.println("<table border='1' width='60%' class='table' align='left' cellspacing='0' cellpadding='0' bordercolor='black'>");
			out.println("<tr>");
			out.println("<td width='15%'><b>Agreement No</b></td>");
			out.println("<td width='15%'><b>Month</b></td>");
			out.println("<td width='15%' align='right' ><b>Gross Due</b></td>");
			out.println("<td width='15%' align='right' ><b>ODI</b></td>");
			out.println("</tr>");
      boolean more5=rs1.next();
			boolean mflag=false;
			if(more5){
			while(more5){
			
			if(!fin_no_2.equals(rs1.getString(1)) && mflag){
				out.println("<tr>");
				out.println("<td><b>Total</b></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf.format(gross_due)+"</td>");
				out.println("<td style='text-align:right'>"+nf.format(odi_amt_1)+"</td>");
				out.println("</tr>");
				gross_due=0;
				odi_amt_1=0;
			
			}
			out.println("<tr>");
			out.println("<td><b>"+rs1.getString(1)+"</b></td>");
			out.println("<td>"+rs1.getString(2)+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(3))+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(4))+"</td>");
			out.println("</tr>");
			gross_due=gross_due+rs1.getDouble(3);
			odi_amt_1=odi_amt_1+rs1.getDouble(4);
			fin_no_2=rs1.getString(1);
			mflag=true;
			more5=rs1.next();
			
      }
				out.println("<tr>");
				out.println("<td><b>Total</b></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf.format(gross_due)+"</td>");
				out.println("<td style='text-align:right'>"+nf.format(odi_amt_1)+"</td>");
				out.println("</tr>");
				
			}
			out.println("</table>");
			out.println("</td></tr>");
			out.println("</table>");
			
			rs1=stmt1.executeQuery(" SELECT A.FINANCE_NO, "+
			                       " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE),'-'), "+
														 " A.INVOICE_NO,SUM(BALANCE_TO_BE_RECEIVED) OTHER_INV "+
                             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
														 " "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+ 
                             " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+ 
                             " A.CURRENCY_CODE=B.CURR_CODE AND "+ 
                             " TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
                             " INVOICE_TYPE NOT IN ('INV_GENER')   AND "+
                             " ACTIVE_STATUS='Y' AND "+
                             " BALANCE_TO_BE_RECEIVED>0 "+
                             " GROUP BY A.FINANCE_NO,"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE),A.INVOICE_NO ");
      
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td width='*%' align='left' ><b>Breakup Of Other Dues Outstanding as at "+m_sys_date+"</b></td></tr>");
			out.println("</table>");
			
			
      out.println("<br>");
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td>");

			out.println("<table border='1' width='60%' class='table' align='left' cellspacing='0' cellpadding='0' bordercolor='black'>");
			out.println("<tr>");
			out.println("<td width='15%'><b>Agreement No</b></td>");
			out.println("<td width='15%'><b>Charge Category</b></td>");
			out.println("<td width='15%'><b>Invoice No</b></td>");
			out.println("<td width='15%'><b>Amount</b></td>");
			out.println("</tr>");
      
			while(rs1.next()){
			out.println("<tr>");
			out.println("<td><b>"+rs1.getString(1)+"</b></td>");
			out.println("<td>"+rs1.getString(2)+"</td>");
			out.println("<td>"+rs1.getString(3)+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(4))+"</td>");
			out.println("</tr>");
      }
			out.println("</table>");
			out.println("</td></tr>");
			out.println("</table>");

			rs1=stmt1.executeQuery(" SELECT A.FINANCE_NO,D.INVOICE_NO, "+
			                       " NVL("+m_schema_name+".AF_CO_GET_ASSET_DESC(B.APPLICATION_NO),'-'), "+
														 " NVL(D.REG_NO,'-'), "+
                             " NVL(ROUND(SUM(A.BALANCE_TO_BE_RECEIVED/A.TOTAL_AMOUNT),2),0), "+
                             " NVL(ROUND(SUM(A.SETTELE_AMOUNT/A.TOTAL_AMOUNT),2),0) "+
                             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
														 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
														 " "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C, "+
                             " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
                             " WHERE A.FINANCE_NO=B.FINANCE_NO "+ 
														 " AND A.TOTAL_AMOUNT <> 0 "+
														 " AND B.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI') "+
                             " AND B.APPLICATION_NO=C.APPLICATION_NO "+
                             " AND B.APPLICATION_NO=D.APPLICATION_NO "+ 
                             " AND A.CLIENT_CODE='"+m_client_code+"' "+
                             " GROUP BY A.FINANCE_NO,D.INVOICE_NO,"+m_schema_name+".AF_CO_GET_ASSET_DESC(B.APPLICATION_NO),D.REG_NO ");
			
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table' align='center'>");
			out.println("<tr><td width='*%' align='left' ><b>Asset & Age as at "+m_sys_date+"</b></td></tr>");
			out.println("</table>");
			
			
			out.println("<br>");
			out.println("<table border='1' width='100%' class='table' cellspacing='0' cellpadding='0' bordercolor='black'>");
			out.println("<tr>");
			out.println("<td width='15%'><b>Agreement No</b></td>");
			out.println("<td width='15%'><b>No. of assets</b></td>");
      out.println("<td width='25%'><b>Asset Make & Model</b></td>");
			out.println("<td width='15%'><b>Reg. Number</b></td>");
      out.println("<td width='15%'><b>No of Rentals Paid</b></td>");
			out.println("<td width='15%'><b>No of Rental Arrears</b></td>");
			out.println("</tr>");
      String m_fin_no_1="";
			boolean flag1=false;
			int m_count_1=0;
			double m_rentals_paid_2=0;
			double m_rental_arrears_2=0;
			while(rs1.next()){
			out.println("<tr>");
			
			rs2=stmt2.executeQuery(" SELECT DISTINCT NVL(COUNT(A.INVOICE_NO),0) "+
                             " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
                             " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                             " WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
                             " AND B.FINANCE_NO ='"+rs1.getString(1)+"' ");
			
			if(rs2.next()){
			   m_count_1=rs2.getInt(1);
			}
			
			
			if(m_fin_no_1.equals(rs1.getString(1))){
			flag1=true;
			}
			else{
			flag1=false;
			}
			if(!flag1){
			out.println("<td>"+rs1.getString(1)+"</td>");
			}
			else{
			out.println("<td>&nbsp;</td>");
			}
			if(!flag1){
			out.println("<td>"+m_count_1+"</td>");
			}
			else{
			out.println("<td>&nbsp;</td>");
			}
			out.println("<td>"+rs1.getString(3)+"</td>");
			out.println("<td>"+rs1.getString(4)+"</td>");
			rs3=stmt3.executeQuery(" SELECT NVL(ROUND(SUM(A.BALANCE_TO_BE_RECEIVED/A.TOTAL_AMOUNT),2),0), "+
                             " NVL(ROUND(SUM(A.SETTELE_AMOUNT/A.TOTAL_AMOUNT),2),0) "+
                             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+ 
                             " WHERE FINANCE_NO='"+rs1.getString(1)+"' "+
														 " AND INVOICE_TYPE='INV_GENER' "+
														 " AND VALUE_DATE<=TO_DATE('"+m_sys_date_1+"','DD-MM-YYYY') "+
														 " AND A.TOTAL_AMOUNT <> 0 ");
			if(rs3.next()){
			m_rentals_paid_2=rs3.getDouble(2);
			m_rental_arrears_2=rs3.getDouble(1);
			}
				
			if(!flag1){	
			out.println("<td style='text-align:right'>"+nf.format(m_rentals_paid_2)+"</td>");
			out.println("<td style='text-align:right'>"+nf.format(m_rental_arrears_2)+"</td>");
			}
			else{
			out.println("<td style='text-align:right'>&nbsp;</td>");
			out.println("<td style='text-align:right'>&nbsp;</td>");
			}
			m_fin_no_1=rs1.getString(1);
			
			out.println("</tr>");
			}
			out.println("</table>");
			
			out.println("<br><br>");
			//out.println("This is a computer-generated statement and hence signature is not required");
			out.println("<table border='0' width='100%' class='table' >");
			out.println("<tr><td width='*%'><b>This is a computer-generated statement and hence signature is not required</b></td></tr>");
			out.println("</table>");
			
			
      out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
			}
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


