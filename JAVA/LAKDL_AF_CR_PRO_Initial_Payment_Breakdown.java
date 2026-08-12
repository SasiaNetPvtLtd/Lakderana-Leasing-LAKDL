import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : NUWAN DE SILVA    DATE:27-03-2007

public class LAKDL_AF_CR_PRO_Initial_Payment_Breakdown extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt_doc,stmt,stmt_invoice,stmt_app;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
  java.lang.Math a;

    
  public ResultSet rs1,rs_doc_charge;
  public ResultSet rs,rs_invoice,rs_app;

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
				String m_orient_name="",m_orient_add1="",m_orient_add2="",m_orient_city_name="",m_orient_tel_no="",m_orient_fax_no="",m_orient_vat_rate="";
        String m_finance_no="";
				//modified by madhawa 2009-10-19 undefined query parameter "client_no" replaced with "client_code" query parameter
				//String m_client_code=req.getParameter("client_no");
				String m_client_code=req.getParameter("client_code");
				//end modified by madhawa 2009-10-19 undefined query parameter "client_no" replaced with client_code
				
				m_application_no=req.getParameter("application_no");
				
					//out.println(" SELECT "+ 
					//									" "+m_schema_name+".AF_CO_GET_CLIENT_NAME('"+m_client_code+"') ,"+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') FROM DUAL ");
														
				rs1=stmt1.executeQuery(" SELECT "+ 
														" "+m_schema_name+".AF_CO_GET_CLIENT_NAME('"+m_client_code+"') ,"+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') FROM DUAL ");
			
			boolean more = rs1.next();										
				if(more){
					m_client_name=rs1.getString(1);
					m_finance_no=rs1.getString(2);
				//	m_date=rs1.getString(2);
					}		
					
					
					rs = stmt.executeQuery(" SELECT "+
					" NVL(UPPER(COMPANY_NAME),' '), "+
					" NVL(UPPER(ADDRESS1),' '), "+
					" NVL(UPPER(ADDRESS2),' '), "+
					" NVL(UPPER(CITY),' '), "+
					" NVL(TEL_NO,' '), "+
					" NVL(FAX_NO,' '),  "+
					" NVL(VAT_RATE,'0') "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
					
					more = rs.next();		
					
					if(more){
					m_orient_name=rs.getString(1);
					m_orient_add1=rs.getString(2);
					m_orient_add2=rs.getString(3);
					m_orient_city_name=rs.getString(4);
					m_orient_tel_no=rs.getString(5);
					m_orient_fax_no=rs.getString(6);
					m_orient_vat_rate=rs.getString(7);			
					}

								
				
							String		Sql_other_inv=" SELECT "+ 
							"	  FINANCE_NO,INVOICE_NO,SUM(BALANCE_TO_BE_RECEIVED) OTHER_INV ,A.CURRENCY_CODE ,MAX(B.EXCHANGE_RATE),TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
							"   TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),SUM(TOTAL_AMOUNT), "+
							"   NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE)) INVOICE_TYPE"+
							"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
							"		WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							"   A.CURRENCY_CODE=B.CURR_CODE AND "+
						  "   TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
							"   INVOICE_TYPE NOT IN ('INV_GENER','TERMINA')   AND "+
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
							" SUM(BALANCE_AMOUNT), "+
							" A.CURR_CODE , "+
							" MAX(B.EXCHANGE_RATE) ,"+
							" TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY' ), "+
							" SUM(AMOUNT), "+
							" TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY' ) "+
							" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A ,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
							"	WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							" A.CURR_CODE=B.CURR_CODE AND "+
						  " TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
							" BALANCE_AMOUNT >0 "+
							" GROUP BY FINANCE_NO,TERMINATION_NO,A.CURR_CODE,A.TERMINATION_VALIDITY_DATE,APPLY_DATE ";
							
				      String		Sql_odi_interest="SELECT B.FINANCE_NO,A.INVOICE_NO,A.ODI ,A.CURR_CODE,A.EXCHANGE_RATE,A.DUE_DATE,A.VALUE_DATE, TOTAL_AMOUNT, INVOICE_TYPE "+
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
					" STATUS NOT IN('RET','C')    "+
					" GROUP BY A.RECEIPT_NO,A.RECEIPT_AMOUNT,B.SETTLE_MODE,B.CURR_CODE "+
					
					
					" UNION  "+
					
					" SELECT   "+
					" DISTINCT REC_NO RECEIPT_NO,    "+ //1
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order') SET_TYPE,   "+ //2
					" NVL(REC_AMOUNT,0) REC_TOTAL , "+ //3
					" A.CURR_CODE  CURR_CODE ,"+ //4
					" MAX(B.EXCHANGE_RATE) EXCHANGE_RATE "+ //5
					"    FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+ 
					" "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
					"    WHERE  UPPER(CLIENT_CODE) =UPPER('"+m_client_code+"')    "+
					"    AND A.STATUS NOT IN('RET','C') AND   "+
					"    A.CURR_CODE=B.CURR_CODE AND "+
					"    RENTAL_OTER_INVOICE >0  AND  "+
					"    REC_NO NOT IN (  "+
					"    SELECT RECEIPT_NO   "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+   
					"    WHERE ALLOCATION_NO IS NOT NULL ) "+
					"    GROUP BY REC_NO,SETTLE_MODE,REC_AMOUNT,A.CURR_CODE "+
					"   )  "+
					"   WHERE REC_TOTAL >0  "+
					"   ORDER BY RECEIPT_NO ";
					
					 out.println("<HTML><HEAD><TITLE>Initail Payment Brakdown </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

					 /*out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Charges Details for Client Code - "+m_client_code+"  Client Name - "+m_client_name+" </B></TD></TR>");
					 out.println("</TABLE>");
						*/
						
						out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> "+m_orient_name+" </B></TD></TR>");
						out.println("<TR><TD align='Center' ><B> Initial Payment Breakdown </B></TD></TR>");
					 out.println("</TABLE>");
						
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'  ></td>"); 
						out.println("<td width='20%' ><b>Contract No</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='40%' align='left' onClick=\"show_finance_detail_drill('"+m_finance_no+"')\" style='cursor:hand' ><DIV class=div_input><u><b>"+m_finance_no+"</u></DIV></td>");
						out.println("<td width='10%' ><b>A.P No</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='20*%' align='left' ><DIV class=div_input>s</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</table>");
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'  ></td>"); 
						out.println("<td width='20%' ><b>Name</td>");
						out.println("<td width='2%'><b>:</td>"); 
						out.println("<td width='*%'>"+m_client_name+"</td>"); 
						out.println("</table>");
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'  ></td>"); 
						out.println("<td width='20%' ><b>Bank</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='40%'></td>"); 
						out.println("<td width='10%' ><b>Cheque No</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</table>");
						
						
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");						
						out.println("<tr class='pdn_txtpos2' >");
						out.println("<td width='1%'  ></td>"); 
						out.println("<td width='80%'>Description</td>"); 
						out.println("<td width='20%' align='right'>Amount</td>"); 
						out.println("</tr>");
						
						rs= stmt.executeQuery ("SELECT "+
							" SUM(GRENTAL_AMOUNT) "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
							" AND INSTALLMENT_NO=0 ");
							more=rs.next();
							if(more)
							{
							m_general_amount=rs.getDouble(1);
							}
						
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>1st Rental</td>"); 
						out.println("<td width='20%' align='right'>"+nf.format(m_general_amount)+"</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Document Charges</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Insuarance</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Advance Monthly Instruments</td>"); 
						out.println("<td width='20%' align='right' >&nbsp;</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>RMV Charges</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Semi luxury / Luxury Tax</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Governement Levy</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Insuarance Claims</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Lease Receivable - Settlement</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>ODI</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>Sale Price</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'>&nbsp;</td>"); 
						out.println("<td width='20%' align='right'>&nbsp;</td>"); 
						out.println("</tr>");
						
						
						out.println("<tr >");
												out.println("<td width='1%'  ></td>"); 

						out.println("<td width='80%'><b>Total</td>"); 
						out.println("<td width='20%' align='right'><b>&nbsp;</td>"); 
						out.println("</tr>");
						
						out.println("</table>");
						
						
						out.println("<br><br><br>");
						
						out.println("<table align='center' width='100%' class='table' >");						
						
						out.println("<tr >");
						out.println("<td width='1%'  ></td>"); 
						out.println("<td width='80%'><b>__________________________________</td>"); 
						out.println("<td width='20%'><b>______________</td>"); 
						out.println("</tr>");
						
						out.println("<tr >");
						out.println("<td width='1%'  ></td>"); 
						//modified msadhawa 2009-10-15 removed align='center'  from Signature,Date 
						out.println("<td width='80%' ><b>Signature</td>"); 
						out.println("<td width='20%' ><b>Date</td>"); 
						out.println("</tr>");
            out.println("</table>");
						
						
						
					 out.println("<BR>");
					 out.println("<table align='center' width='100%' class='table' >");						
						
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
				
				/*if(more_app){
				
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
				*/
				
				/*while(more_app){
						
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
								
				}*/
				
				    
				 /*	  out.println("<tr>");
						out.println("<td width='5%'>&nbsp;</td>"); 
						out.println("<td width='20%'  align='left'  ><b>Applications Sub Total</b></td>"); 
						out.println("<td width='10%'>&nbsp;</td>"); 
						out.println("<td width='10%'>&nbsp;</td>"); 
						out.println("<td width='25%'  align='right' ><b>&nbsp;</b></td>");
						out.println("<td width='25%'  align='right' ><b>"+nf.format(Total_amount_charges_rep)+"</b></td>");
						out.println("</tr>"); 
				    
				*/	
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
			
					/*	while(more){
						
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
						
						*/
						
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
			
						/*while(more){
						
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
						*/
						
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
						
						/*while(more){
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
						*/
						
						//TERMINATION DETAILS =============================================================
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
	*/
	
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
						
						/*while(more){
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
					
					
					
					// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv 04-01-07
					/*out.println("<table align='center' width='100%' class='table' >");
					out.println("<td width='5%' ></td>"); 
					out.println("<td width='30%'  align='left'  ><b>Invoices Sub Total</b></td>"); 
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					//out.println("<td width='15%'  align='right' ><b>&nbsp;</b></td>");
					out.println("<td width='20%'  align='right' ><b>"+nf.format(_m_Invoice_Amount)+"</b></td>");
					out.println("<td width='20%'  align='right' ><b>"+nf.format(_m_total_invoices)+"</b></td>");
					out.println("</table>");
          */

						
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
					
					
					/*while(more){
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
					*/
					
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


