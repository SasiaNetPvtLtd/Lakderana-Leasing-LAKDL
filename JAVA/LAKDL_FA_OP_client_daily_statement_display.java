/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
// CREATED BY DINETH MEEMANAGE
// ON 2008-09-23
// FOR FACTORING 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_FA_OP_client_daily_statement_display extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_val;
	ServletOutputStream out = null;
	
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			     
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
				conn = m_sn_methods.met_user_validate(req);
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_header_name=m_sn_methods.header_name.trim();
				nf = java.text.NumberFormat.getInstance(Locale.US);
				nf.setMinimumFractionDigits(2);
				nf.setMaximumFractionDigits(2);      
				nf1 = java.text.NumberFormat.getInstance(Locale.US);
				nf1.setMinimumFractionDigits(0);
				nf1.setMaximumFractionDigits(0);
			
				
				res.setStatus(HttpServletResponse.SC_OK); 
				res.setContentType("text/html"); 
				out = res.getOutputStream(); 
			 
				stmt=conn.createStatement();
				stmt1=conn.createStatement();
				stmt2=conn.createStatement();
				m_chksql         = req.getParameter("chksql");
				if(m_chksql.trim().equals("LOAD_CLI_DAILY_REPORT")){
				 
				  String m_client_name="";
					String m_client_code=req.getParameter("client_code");
					String m_as_at_date=req.getParameter("as_at_date");
					String m_pod_ref_no="";
				  //out.println(m_client_code);
					//out.println(m_as_at_date);
					String m_invoice_no="";
					double m_opening_bal_sales_ledger=0; 
					double m_settle_inv_amt=0;
					double m_due_date_exceeded_amt=0;
					double m_active_inv_amt=0;
					double m_new_inv_amt=0;
					double m_collection_amt=0;
					double m_unallo_amt=0;
					double m_client_payments=0;
					double m_charges_tot=0;
					double m_normal_interest=0;
					double m_overpaid_interest=0;
					double m_inv_tot=0;
					double m_tot_inv_availability=0;
					double m_opening_current_acc=0;
					double m_closing_balance=0;
					double m_payable_or_overpaid_amt=0;
					rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME('"+m_client_code+"'),'-') FROM DUAL");
					
					boolean more = rs.next();
					if(more){
							m_client_name=rs.getString(1);
					}
					out.println("<HTML><HEAD><TITLE>Client Daily Statement</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' >"); //onLoad=\"add_button()\"
					out.println("<FORM NAME='Form1' method='post'>"); 
						
					
				
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD  ><B> Client Name - "+m_client_name +"</B></TD></TR>");
					 out.println("<TR><TD  ><B> Client Daily Statement as at - "+m_as_at_date+"  </B></TD></TR>");

					 out.println("</TABLE>");
					 out.println("<BR><BR>");
					 
						//newly added by dineth
						
						/*rs=stmt.executeQuery(" SELECT NVL(SUM(TRNAMOUNT),0) "+  
                               " FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
                               " WHERE "+
                               " CLIENT_CODE='"+m_client_code+"'");
					boolean more18 = rs.next();
					if(more18){
						m_opening_bal_sales_ledger=rs.getDouble(1);
						
					}*/
					rs=stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'OPENABAL','"+m_as_at_date+"','"+m_as_at_date+"')) "+//1
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
					" AND B.CLIENT_CODE='"+m_client_code+"' ");
					
					boolean more32=rs.next();
					if(more32){
						m_opening_bal_sales_ledger=rs.getDouble(1);
					}
					
					
					
					/*rs=stmt.executeQuery(" SELECT NVL(SUM(A.SETTLE_AMOUNT),0) "+
															 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
															 " WHERE A.INVOICE_NO=B.INVOICE_NO "+
															 " AND A.CLIENT_CODE='"+m_client_code+"'");*/
					  rs=stmt.executeQuery(" SELECT NVL(SUM(B.ALLOCATED_AMOUNT),0) "+  
															   " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO B "+
                                 " WHERE A.INVOICE_SEQ_NO=B.INVOICE_NO "+
                                 " AND A.INVOICE_STATUS='CONF' "+
                                 " AND A.client_code='"+m_client_code+"'"+
																 " AND TO_DATE(TO_CHAR(B.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')" );
					
					
					boolean more19 = rs.next();
					if(more19){
						m_settle_inv_amt=rs.getDouble(1);
					}
					
					rs = stmt.executeQuery(" SELECT NVL(SUM(A.INVOICE_AMOUNT),0) "+
																	 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A , "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
																	 " WHERE A.DUE_DATE < TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
																	 " AND A.INVOICE_NO=B.INVOICE_NO "+
																	 " AND B.CLIENT_CODE = '"+m_client_code+"'");
																		
																		
																		
					boolean more20 = rs.next();
					if(more20){
						m_due_date_exceeded_amt=rs.getDouble(1);
					}
					
					
					rs = stmt.executeQuery(" SELECT NVL(SUM(A.INVOICE_AMOUNT),0) "+
																	 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A , "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
																	 " WHERE A.DUE_DATE = TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
																	 " AND A.INVOICE_NO=B.INVOICE_NO "+
																	 " AND B.CLIENT_CODE = '"+m_client_code+"'");
																		
																		
																		
					boolean more11 = rs.next();
					if(more11){
						m_active_inv_amt=rs.getDouble(1);
					}
					
					rs=stmt.executeQuery(" SELECT "+  
																 " NVL(SUM(A.INVOICE_AMOUNT),0) "+  
																 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
																 " WHERE A.INVOICE_STATUS='ENTER' "+  
																 " AND A.CLIENT_CODE='"+m_client_code+"'");
																 
					boolean more12 = rs.next();
					if(more12){
						m_new_inv_amt=rs.getDouble(1);
					}
					
					// for invoice availability
					rs=stmt.executeQuery(" SELECT NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+
					                     " FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
															 " WHERE "+
                               " A.BATCH_NO=B.BATCH_NO "+
                               " AND A.CLIENT_CODE='"+m_client_code+"'");
					
					boolean more31=rs.next();
					if(more31){
					m_inv_tot=rs.getDouble(1);
					}
					m_tot_inv_availability=m_inv_tot-m_settle_inv_amt;
					
					
					
					
					
					// end inv availability
					// Opening Balance C/A
					rs=stmt.executeQuery(" SELECT NVL(SUM(TRNAMOUNT),0) "+  
                               " FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
                               " WHERE "+
                               " CLIENT_CODE='"+m_client_code+"'");
					boolean more18 = rs.next();
					if(more18){
						m_opening_current_acc=rs.getDouble(1);
						
					}
					//end opening balance C/A
					
					
					rs=stmt.executeQuery(" SELECT NVL(SUM(REC_AMOUNT),0) "+
															 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
                               " WHERE "+
															 " CLIENT_CODE='"+m_client_code+"'"+
                               " AND RECON_STATUS='Y' ");
					
					
					boolean more13 = rs.next();
					if(more13){
						m_collection_amt=rs.getDouble(1);
					}
					
					
					rs=stmt.executeQuery(" SELECT NVL(SUM(BALANCE_AMOUNT),0) "+
                               " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
                               " WHERE "+
                               " CLIENT_CODE='"+m_client_code+"'"+
                               " AND REC_STATUS='Y' ");
																
					boolean more14 = rs.next();
					if(more14){
						m_unallo_amt=rs.getDouble(1);
					}
					
					
					rs=stmt.executeQuery(" SELECT NVL(SUM(PAYMENT_AMOUNT),0) "+
                               " FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
                               " WHERE "+
                               " CLIENT_CODE='"+m_client_code+"'"+
															 " AND PAY_STATUS IN('CONF','PRINT','DISB')");
																
																
					boolean more15 = rs.next();
					if(more15){
						m_client_payments=rs.getDouble(1);
					}
					
					rs=stmt.executeQuery(" SELECT NVL(SUM(DECODE(DRCR_STATUS,'DR',FEE_CHARGE_AMOUNT,FEE_CHARGE_AMOUNT*-1)),0) "+
                               " FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
                               " WHERE CLIENT_CODE='"+m_client_code+"'"+
                               " AND A.FEE_CODE<>'VAT' ");
					boolean more21 = rs.next();
					if(more21){
						m_charges_tot=rs.getDouble(1);
					}
					
					//for closing balance
					m_closing_balance = m_opening_current_acc + m_client_payments + m_charges_tot - m_collection_amt; 
					
					
					
					//end closing balance
					
					
					//for Payable/OverPaid Amount
					if(m_closing_balance > m_tot_inv_availability){
						m_payable_or_overpaid_amt=m_closing_balance-m_tot_inv_availability;
					}
					else if(m_tot_inv_availability > m_closing_balance){
						m_payable_or_overpaid_amt=m_tot_inv_availability-m_closing_balance;
					}
					else{
						m_payable_or_overpaid_amt=0;
					}
					
					//end Payable/OverPaid Amount
					rs=stmt.executeQuery(" SELECT NVL(SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
                               " FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
                               " WHERE "+
                               " CLIENT_CODE='"+m_client_code+"'"+
                               " AND PROC_DESC='DAILY INTEREST' ");
					
					boolean more16 = rs.next();
					if(more16){
						m_normal_interest=rs.getDouble(1);
					}
					
					
					rs=stmt.executeQuery(" SELECT NVL(SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0)"+
                               " FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
                               " WHERE "+
                               " CLIENT_CODE='"+m_client_code+"'"+
                               " AND PROC_DESC='OVERPAY INTEREST' ");
																
																
					boolean more17 = rs.next();
					if(more17){
						m_overpaid_interest=rs.getDouble(1);
					}
					
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='16%' align=center><b><u>O/B of Sales Ledger<b></u></td>");
						    out.println("<td width='16%' align=center><b><u>Settled Inv Amt<b></u></td>");
								out.println("<td width='16%' align=center><b><u>Due Date Exceeded Amt<b></u></td>");
								out.println("<td width='16%' align=center><b><u>Active Inv Amt<b></u></td>");
						    out.println("<td width='16%' align=center><b><u>New Inv Amt<b></u></td>");
								out.println("<td width='16%' align=center><b><u>Inv Availability<b></u></td>");
								out.println("</tr>"); 
								
								
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_opening_bal_sales_ledger)+"</td>");
						    out.println("<td width='16%' style='text-align:right'>"+nf.format(m_settle_inv_amt)+"</td>");
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_due_date_exceeded_amt)+"</td>");
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_active_inv_amt)+"</td>");
						    out.println("<td width='16%' style='text-align:right'>"+nf.format(m_new_inv_amt)+"</td>");
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_tot_inv_availability)+"</td>");
								out.println("</tr>"); 
					
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='16%' align=center><b><u>O/B of C/A<b></u></td>");
						    out.println("<td width='16%' align=center><b><u>Collections<b></u></td>");
								out.println("<td width='16%' align=center><b><u>Unallocated Amt<b></u></td>");
								out.println("<td width='16%' align=center><b><u>Client Payment<b></u></td>");
						    out.println("<td width='16%' align=center><b><u>Charges<b></u></td>");
								out.println("<td width='16%' align=center><b><u>Closing Balance<b></u></td>");
								out.println("</tr>"); 
								
								
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_opening_current_acc)+"</td>");
						    out.println("<td width='16%' style='text-align:right'>"+nf.format(m_collection_amt)+"</td>");
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_unallo_amt)+"</td>");
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_client_payments)+"</td>");
						    out.println("<td width='16%' style='text-align:right'>"+nf.format(m_charges_tot)+"</td>");
								out.println("<td width='16%' style='text-align:right'>"+nf.format(m_closing_balance)+"</td>");
								out.println("</tr>"); 
						   
								out.println("<tr >");
								out.println("<td colspan='7'>");
								out.println("<table align='center' width='100%' class='table' >");
								out.println("<tr>");
								out.println("<td width='20%'><b>Payable/(Over paid Amount)</b></td>"); 
								out.println("<td width='80%' style='text-align:right'><b>("+m_payable_or_overpaid_amt+")</b></td>");
								out.println("</tr>");
								out.println("<tr>");
								out.println("<td width='20%'>Normal Interest</td>"); 
								out.println("<td width='80%' style='text-align:right'>"+nf.format(m_normal_interest)+"</td>");
								out.println("</tr>");
								out.println("<tr>");
								out.println("<td width='20%'>Over Paid Interest</td>"); 
								out.println("<td width='80%' style='text-align:right'>"+nf.format(m_overpaid_interest)+"</td>");
								out.println("</tr>");
								
						
						
						
						//new details by dineth
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Invoice Settlement Details<b></u></td>");
						
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						
						
						/*rs = stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE), "+
																	 " A.CHEQUE_NO, "+
																	 " A.CHEQUE_AMOUNT, "+
																	 " B.INVOICE_NO, "+
																	 " B.INVOICE_AMOUNT, "+
																	 " B.SETTLE_AMOUNT, "+
                                   " B.BALANCE_AMOUNT, "+
																	 " D.BALANCE_AMOUNT "+
																	 " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A, "+
																	 " "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B, "+
																	 " "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO C, "+
																	 " "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT D "+
                                   " WHERE A.POD_REF_NO=C.POD_REF_NO AND C.INVOICE_NO=B.INVOICE_NO AND B.INVOICE_NO=D.INVOICE_NO"+
																	 " AND C.CLIENT_CODE='"+m_client_code+"'");*/
																		
																		
						/*rs = stmt.executeQuery(" SELECT DISTINCT B.POD_REF_NO, "+
																	 " A.INVOICE_NO, "+
																	 " A.INVOICE_AMOUNT, "+
																	 " A.SETTLE_AMOUNT, "+
																	 " A.BALANCE_AMOUNT "+ 
                                   " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+
																	 " "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
                                   " WHERE A.CLIENT_CODE='"+m_client_code+"'"+
                                   " AND A.INVOICE_NO=B.INVOICE_NO ");*/
																		
						/*												
						rs=stmt.executeQuery(" SELECT A.POD_REF_NO, "+//1
				                    " A.CLIENT_CODE, "+//2
					                  " A.INVOICE_NO, "+//3
				                    " NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),'-'),"+ //4
				                    " B.CHEQUE_NO, "+//5
				                    " B.CHEQUE_AMOUNT, "+//6
				                    " C.INVOICE_AMOUNT, "+//7
														" C.SETTLE_AMOUNT, "+//8
				                    " C.BALANCE_AMOUNT, "+//9
														" A.ALLO_AMOUNT, "+//10
														" D.ALLO_AMOUNT, "+//11
														" D.REC_AMOUNT, "+//12
														" D.BALANCE_AMOUNT "+//13
					                  " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A, "+
														" "+m_schema_name+".FA_OP_PRO_POD_CHEQUES B, "+
														" "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C, "+
														" "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT D "+
				                    " WHERE A.POD_REF_NO=B.POD_REF_NO "+
														" AND B.POD_REF_NO=D.SUS_REF_NO "+
				                    " AND A.CLIENT_CODE=C.CLIENT_CODE "+ 
				                    " AND A.FACILITY_NO=C.FACILITY_NO "+ 
				                    " AND A.BATCH_NO=C.BATCH_NO "+
				                    " AND A.INVOICE_NO=C.INVOICE_NO "+
														" AND D.REC_STATUS='Y' "+
				                    " AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%')"+
														" ORDER BY INVOICE_NO ASC");*/
														
					rs=stmt.executeQuery(" SELECT "+ 
						                   " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), "+//1
						                   " A.RECEIPT_NO, "+ //2
						                   " NVL(A.ALLOCATED_AMOUNT,0), "+//3
						                   " NVL("+m_schema_name+".FA_CLIENT_SETTLE_DATA(A.RECEIPT_NO,'CHQ'),'-'), "+//4
						                   " NVL("+m_schema_name+".FA_CLIENT_SETTLE_DATA(A.RECEIPT_NO,'BANK'),'-'), "+//5
						                   " B.INVOICE_NO, "+//6
						                   " B.DEBTOR_CODE, "+//7
						                   " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//8
						                   " NVL(B.INVOICE_AMOUNT,0), "+//9
						                   " NVL(B.BALANCE_AMOUNT,0), "+//10
						                   " NVL(A.RECEIPT_AMOUNT,0), "+//11
															 " "+m_schema_name+".FA_CLIENT_PRE_INV_BAL('"+m_client_code+"',C.FACILITY_NO,A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'))-A.ALLOCATED_AMOUNT "+//12
						                   " FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+ 
						                   " WHERE A.INVOICE_NO=B.INVOICE_SEQ_NO "+ 
						                   " AND B.BATCH_NO=C.BATCH_NO "+
                               " AND C.CLIENT_CODE='"+m_client_code+"' "+
															 " AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
						                   " AND A.ALLOCATED_AMOUNT>0 "+ 
						                   " ORDER BY A.ALLOCATED_DATE"); 

					 
						
																	 
						
						boolean more1=rs.next();
						
						if(!more1){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='100%' style='text-align:center'><b>No Records</b></td>");
								out.println("</tr>"); 
								out.println("</table>");
						}
						if(more1){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' ><b><u>Bank Branch<b></u></td>");
						    out.println("<td width='12%' style='text-align:center'><b><u>Cheque No<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Cheque Amt<b></u></td>");
								out.println("<td width='12%' style='text-align:center'><b><u>Invoice No<b></u></td>");
						    out.println("<td width='12%' style='text-align:right'><b><u>Invoice Amt<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Settled Amt<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Balance of Invoice<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Unallocated Amt<b></u></td>");
								out.println("</tr>"); 
								
								
					 			while(more1){
									  m_invoice_no=rs.getString(6);
										
										double m_inv_amt_1=rs.getDouble(9);
										double m_set_amt_1=0;
										double m_bal_inv_1=0;
										double m_unallo_amt_1=0;
										
										double m_chq_amt_1=rs.getDouble(11);
										
										/*rs1=stmt1.executeQuery(" SELECT DISTINCT A.POD_REF_NO, "+
										                       " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE), "+
                                           " A.CHEQUE_NO,"+
																					 " A.CHEQUE_AMOUNT, "+
																					 " C.BALANCE_AMOUNT "+
                                           " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A, "+
																					 //" "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
																					 " "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C "+
                                           " WHERE A.POD_REF_NO='"+m_pod_ref_no+"'"+
																						"AND A.CLIENT_CODE=C.CLIENT_CODE");
																					// " AND B.INVOICE_NO=C.INVOICE_NO");*/
																					
										/*	rs1=stmt1.executeQuery(" SELECT A.POD_REF_NO, "+
																						 " A.ALLO_AMOUNT "+
																						 " FROM LAKDL.FA_OP_PRO_POD_CHEQUES_ALLO A "+
																						 " WHERE A.POD_REF_NO='"+m_pod_ref_no+"'");*/
										/*boolean more30=rs1.next();
										while(more30){*/
										out.println("<tr >");
										out.println("<td width='4%'></td>"); 
										out.println("<td width='12%' >"+rs.getString(5)+"</td>");
						    		out.println("<td width='12%' style='text-align:center'>"+rs.getString(4)+"</td>");
										out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(11))+"</td>");
										out.println("<td width='12%' style='text-align:center'>"+rs.getString(6)+"</td>");
						    		out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>");
										out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(3))+"</td>");
										if(rs.getDouble(12)<0){
										out.println("<td width='12%' style='text-align:right'>-</td>");
										out.println("<td width='12%' style='text-align:right'>"+nf.format((-1)*rs.getDouble(12))+"</td>");
										}
										else if(rs.getDouble(12)>0){
										out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(12))+"</td>");
										out.println("<td width='12%' style='text-align:right'>-</td>");
										}
										else{
										out.println("<td width='12%' style='text-align:right'>-</td>");
										out.println("<td width='12%' style='text-align:right'>-</td>");
										}
										/*
										if(m_chq_amt_1 >= m_inv_amt_1){
										m_set_amt_1=m_inv_amt_1;
										m_bal_inv_1=0;
										m_unallo_amt_1=m_chq_amt_1-m_inv_amt_1;
										out.println("<td width='12%' style='text-align:right'>"+nf.format(m_inv_amt_1)+"</td>");
										out.println("<td width='12%' style='text-align:right'>"+nf.format(m_bal_inv_1)+"</td>");
										out.println("<td width='12%' style='text-align:right'>"+nf.format(m_unallo_amt_1)+"</td>");

										}
										else{
										m_set_amt_1=m_chq_amt_1;
										m_bal_inv_1=m_inv_amt_1-m_chq_amt_1;
										m_unallo_amt_1=0;
										out.println("<td width='12%' style='text-align:right'>"+nf.format(m_chq_amt_1)+"</td>");
										out.println("<td width='12%' style='text-align:right'>"+nf.format(m_bal_inv_1)+"</td>");
										out.println("<td width='12%' style='text-align:right'>"+nf.format(m_unallo_amt_1)+"</td>");

										}*/
										
																				out.println("</tr>"); 
																				
										more1=rs.next();
								
								}
								
								out.println("</table>");
								}
						
						
						
																
																		
					  out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Due Date Exceeded Invoices<b></u></td>");
						
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");													
						
						rs = stmt.executeQuery(" SELECT A.INVOICE_NO, "+
																	 " TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),A.INVOICE_AMOUNT "+
																	 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A , "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
																	 " WHERE A.DUE_DATE < TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
																	 " AND A.INVOICE_NO=B.INVOICE_NO "+
																	 " AND B.CLIENT_CODE = '"+m_client_code+"'");
						boolean more2=rs.next();
						
						if(!more2){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='100%' style='text-align:center'><b>No Records</b></td>");
								out.println("</tr>"); 
								out.println("</table>");
						}
						if(more2){
								out.println("<table width='40%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' ><b><u>Inv No<b></u></td>");
						    out.println("<td width='12%' ><b><u>Inv Date<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Inv Amt<b></u></td>");
								out.println("</tr>");
								double inv_tot=0;
						while(more2){
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' >"+rs.getString(1)+"</td>");
						    out.println("<td width='12%' >"+rs.getString(2)+"</td>");
								out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(3))+"</td>");
								out.println("</tr>");
								inv_tot+=rs.getDouble(3);
								more2=rs.next();
								
						}
						out.println("<tr >");
						out.println("<td width='4%'></td>"); 
						out.println("<td width='12%' >&nbsp;</td>");
						out.println("<td width='12%' >&nbsp;</td>");
						out.println("<td width='12%' style='text-align:right'><b>"+nf.format(inv_tot)+"</b></td>");
						out.println("</tr>");
						out.println("</table>");
						}
						
						
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>New Invoice Amt<b></u></td>");
						
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");	
						
						rs=stmt.executeQuery(" SELECT "+  
																 " A.BATCH_NO, "+   
																 " NVL(SUM(A.INVOICE_AMOUNT),0) "+  
																 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
																 " WHERE A.INVOICE_STATUS='ENTER' "+  
																 " AND A.CLIENT_CODE='"+m_client_code+"'"+
																 " GROUP BY A.BATCH_NO ");
					  boolean more3=rs.next();
						
						if(!more3){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='100%' style='text-align:center'><b>No Records</b></td>");
								out.println("</tr>"); 
								out.println("</table>");
						}
						if(more3){
								out.println("<table width='26%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' ><b><u>Inv Batch No<b></u></td>");
						    out.println("<td width='12%' style='text-align:right'><b><u>Batch Amt<b></u></td>");
								out.println("</tr>");
								double batch_tot=0;
						while(more3){
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' >"+rs.getString(1)+"</td>");
						    out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(2))+"</td>");
								out.println("</tr>");
								batch_tot+=rs.getDouble(2);
								more3=rs.next();
								
						}
						out.println("<tr >");
						out.println("<td width='4%'></td>"); 
						out.println("<td width='12%' >&nbsp;</td>");
						out.println("<td width='12%' style='text-align:right'><b>"+nf.format(batch_tot)+"</b></td>");
						out.println("</tr>");
						out.println("</table>");
						}
															
						
						
						
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Inv To Be Due within next 7 days<b></u></td>");
						
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");	
						
						
						
						rs = stmt.executeQuery(" SELECT A.INVOICE_NO, "+
																	 " TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),A.INVOICE_AMOUNT "+
																	 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A , "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
																	 " WHERE A.DUE_DATE - TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') <= 7 "+
																	 " AND A.DUE_DATE - TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') >= 0 "+
																	 " AND A.INVOICE_NO=B.INVOICE_NO "+
																	 " AND B.CLIENT_CODE = '"+m_client_code+"'");
						boolean more4=rs.next();
						
						if(!more4){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='100%' style='text-align:center'><b>No Records</b></td>");
								out.println("</tr>"); 
								out.println("</table>");
						}
						if(more4){
								out.println("<table width='40%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' ><b><u>Inv No<b></u></td>");
						    out.println("<td width='12%' ><b><u>Inv Date<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Inv Amt<b></u></td>");
								out.println("</tr>");
								double inv_amt_tot=0;
						while(more4){
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' >"+rs.getString(1)+"</td>");
						    out.println("<td width='12%' >"+rs.getString(2)+"</td>");
								out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(3))+"</td>");
								out.println("</tr>");
								inv_amt_tot+=rs.getDouble(3);
								more4=rs.next();
								
						}
						out.println("<tr >");
						out.println("<td width='4%'></td>"); 
						out.println("<td width='12%' >&nbsp;</td>");
						out.println("<td width='12%' >&nbsp;</td>");
						out.println("<td width='12%' style='text-align:right'><b>"+nf.format(inv_amt_tot)+"</b></td>");
						out.println("</tr>");
						out.println("</table>");
						}
						
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Inv Re-Assigned Details<b></u></td>");
						
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");	
						
						
						rs=stmt.executeQuery(" SELECT A.INVOICE_NO, "+
						                     " A.INVOICE_AMOUNT, "+
																 " A.SETTLE_AMOUNT, "+
																 " B.ADJUST_AMOUNT "+
                                 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_ADJUSTMENTS B "+
                                 " WHERE A.INVOICE_NO=B.INVOICE_NO "+
                                 " AND A.REASSIGN_STATUS='Y' "+
                                 " AND B.CLIENT_CODE='"+m_client_code+"'");
						
						boolean more5=rs.next();
						
						if(!more5){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='100%' style='text-align:center'><b>No Records</b></td>");
								out.println("</tr>"); 
								out.println("</table>");
						}
						if(more5){
								out.println("<table width='40%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' ><b><u>Inv No<b></u></td>");
						    out.println("<td width='12%' style='text-align:right'><b><u>Inv Amt<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Settled Amt<b></u></td>");
								out.println("<td width='12%' style='text-align:right'><b><u>Re-Assigned Amt<b></u></td>");
								out.println("</tr>");
								
						while(more5){
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='12%' >"+rs.getString(1)+"</td>");
						    out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(2))+"</td>");
								out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(3))+"</td>");
								out.println("<td width='12%' style='text-align:right'>"+nf.format(rs.getDouble(4))+"</td>");
								out.println("</tr>");
								
								more5=rs.next();
								
						}
						
						out.println("</table>");
						}
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Cheques Details<b></u></td>");
						
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						double unset_return_tot=0;
						double bank_cheques=0;
						double next_date_bank_tot=0;
						double after_next_tot=0;
						rs=stmt.executeQuery(" SELECT SUM(A.DEPOSIT_AMOUNT) "+
 																 " FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						 										 " WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
						 										 " AND B.REBANK_STATUS IN('N','C') "+
                         				 " AND B.CLIENT_CODE ='"+m_client_code+"'");
																	
						boolean more6=rs.next();
						if(more6){
						unset_return_tot=rs.getDouble(1);
						}
						
						rs=stmt.executeQuery(" SELECT NVL(SUM(BALANCE_AMOUNT),0) "+
																 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
																 " WHERE "+
																 " TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
                                 " AND SETTLE_MODE='CHEQUE' "+
																 " AND REC_STATUS='B' ");
						boolean more7=rs.next();
						if(more7){
						bank_cheques=rs.getDouble(1);
						}
						
						rs=stmt.executeQuery(" SELECT NVL(SUM(BALANCE_AMOUNT),0) "+
																 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
																 " WHERE "+
																 " TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')-TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')=1 "+
                                 " AND SETTLE_MODE='CHEQUE' "+
																 " AND REC_STATUS='B' ");
																	
						boolean more8=rs.next();
						if(more8){
						next_date_bank_tot=rs.getDouble(1);
						}
						
						rs=stmt.executeQuery(" SELECT NVL(SUM(BALANCE_AMOUNT),0) "+
																 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
																 " WHERE "+
																 " TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')-TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')>1 "+
                                 " AND SETTLE_MODE='CHEQUE' "+
																 " AND REC_STATUS='B' ");
																	
						boolean more9=rs.next();
						if(more9){
						after_next_tot=rs.getDouble(1);
						}
						out.println("<table width='36%' class='table' >");						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='20%' ><b>Total unsettled returned cheques</b></td>");
						    out.println("<td width='12%' style='text-align:right'>"+nf.format(unset_return_tot)+"</td>");
								out.println("</tr>");
						
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='20%' ><b>Total Banked cheques</b></td>");
						    out.println("<td width='12%' style='text-align:right'>"+nf.format(bank_cheques)+"</td>");
								out.println("</tr>");
								
								
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='20%' ><b>To be Banked in next date</b></td>");
						    out.println("<td width='12%' style='text-align:right'>"+nf.format(next_date_bank_tot)+"</td>");
								out.println("</tr>");
								
								
								out.println("<tr >");
								out.println("<td width='4%'></td>"); 
								out.println("<td width='20%' ><b>Total PD Cheques to be banked after next Date</b></td>");
						    out.println("<td width='12%' style='text-align:right'>"+nf.format(after_next_tot)+"</td>");
								out.println("</tr>");
						
						
								out.println("</table>");
						
						
						
						
					  out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
				
				
				
				
				
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
