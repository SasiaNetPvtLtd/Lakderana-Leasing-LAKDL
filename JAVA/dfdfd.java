else if(m_chksql.equals("CALCULATION_REPORT2")){					
						
						String m_finance_no   = req.getParameter("finance_no");
						String m_closing_rate = req.getParameter("closing_rate");
						String m_date         = req.getParameter("termi_date");
						
						// added by udara on 24-07-2013
						String m_odi_net      = req.getParameter("odi_net");
						
						double m_odi_net_value = 0;
						
						if(m_odi_net!=null)
							m_odi_net_value = Double.parseDouble(m_odi_net);
						
						String company_name = "";
						
						String client_name  = "";
						String reg_no       = "";
						
						double arrears_amount      = 0.00;
						double ceasing_charges     = 0.00;
						double insurance_charges   = 0.00;
						double visiting_charges    = 0.00;
						double total_arrears       = 0.00;
						double rental_amount       = 0.00;
						
						int no_of_future_rentals   = 0;
						
						double closing_rate        = 0.00;
						
						double normal_closing = 0.00;
						double closing_amount = 0.00;
						
						double final_closing_amount = 0.00;
						
						double rebate_amount  = 0.00;
						
						double future_capital = 0.00;
						double balance_capital = 0.00;
						double interest_for_capital = 0.00;
						
						double total_amount = 0.00;
						
						double other_charges = 0.00; // added by udara on 17-01-2013
						
						double arrears_excess      = 0.00; // added by udara on 13-03-2013
						
						String m_date_show = ""; // added by udara on 14-08-2013
						
						String client_code = "";
						
						try{
							
						
							
							
							rs=stmt.executeQuery(" "+
								" SELECT COMPANY_NAME, TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
								" 	FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS "+
								" ");
							
							if(rs.next()){
								company_name = rs.getString(1);
								m_date_show = rs.getString(2);
							}
							
								
							 rs=stmt.executeQuery(
										"SELECT  "+
										"A.APPLICATION_NO  "+
										"FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS A , LAKDL.AF_CO_MAS_CLIENT B,LAKDL.AF_CO_MAS_TRANSACTION_TYPE C   "+
										"WHERE A.CLIENT_CODE = B.CLIENT_CODE   "+
										"AND   A.TRANSACTION_TYPE=C.TRAN_CODE   "+
										"AND A.FINANCE_NO = '"+m_finance_no+"' ");
								if(rs.next()){
										m_app_no= rs.getString(1);		
								}

							rs=stmt.executeQuery(
										//out.println(
										" SELECT SUM(B.NET_PRICE), "+//1
										" SUM(A.INTEREST_AMOUNT), "+//2
										" 0 , "+//3
										" COUNT(*) "+	//4
										" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
										" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
										" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
										" AND A.PRICING_NO       = B.PRICING_NO "+
										" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
										" AND B.ACTIVE_STATUS    IN ('T','Y')");		
										
										
										if(rs.next()){
										m_rental_count= rs.getInt(4);
										m_capitl      = rs.getDouble(1)/m_rental_count;
										m_interest    = rs.getDouble(2);
										m_vat         = rs.getDouble(3);
										m_tot_agree   = m_capitl+m_interest+m_vat;
										
							}

							rs=stmt.executeQuery(
									"SELECT TO_CHAR(MAX(A.RENTAL_DATE),'DD') DUE_DATE "+
									"FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT A  "+
									"WHERE A.APPLICATION_NO = '"+m_app_no+"' ");
								if(rs.next()){
										rental_date= rs.getString(1);		
								}
								
							rs=stmt.executeQuery(
							//out.println(	
								 "SELECT DISTINCT C.FINANCE_NO, SUM(A.REC_AMOUNT) "+
								 "FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+ 
								 "WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  "+ 
								 "AND NVL(A.INSURANCE,0) = 0 "+
								 "AND C.FINANCE_NO = '"+m_finance_no+"' "+
								 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= SYSDATE "+
								 "GROUP BY C.FINANCE_NO ");
								 if(rs.next()){
										tot_cash_coll= rs.getDouble(2);		
								 }	
						
						
							rs=stmt.executeQuery(	
								 "SELECT DISTINCT C.FINANCE_NO, SUM(A.REC_AMOUNT) "+
								 "FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+ 
								 "WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  "+ 
								 "AND NVL(A.INSURANCE,0) = 0 "+
								 "AND C.FINANCE_NO = '"+m_finance_no+"' "+
								 "AND A.EFF_VALDATE >= TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE ,-6),'DD-MM-YYYY'),'DD-MM-YYYY') "+
                 				 "AND A.EFF_VALDATE <= SYSDATE   "+
								 "GROUP BY C.FINANCE_NO ");
								 if(rs.next()){
										tot_cash_coll_6months= rs.getDouble(2);		
								 }
							
							rs=stmt.executeQuery
							(" "+
									" SELECT "+
									" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+ // 1 client name
									" NVL((SELECT REG_NO FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE APPLICATION_NO = A.APPLICATION_NO AND REG_NO IS NOT NULL AND ROWNUM=1 ),'-') REG_NO, "+ //" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(A.APPLICATION_NO),'-') REG_NO, "+ // 2 mod by udara 21-10-2013 
									//" NVL((SELECT REG_NO FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE APPLICATION_NO = A.APPLICATION_NO),'-') REG_NO, "+ // 2 registration no.
									" "+m_schema_name+".AF_CO_GET_CONTRACT_BAL(FINANCE_NO,CLIENT_CODE,'"+m_date+"',NULL) DUE_AMOUNT, "+ 
									" "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO), "+     // 4 rental amount
									" "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(A.APPLICATION_NO), "+ // 5 future rental
									" "+m_schema_name+".AF_CO_CLOSING_RATE(A.APPLICATION_NO), "+ // 6 closing rate
									
									" ( SELECT  SUM(NVL(CAPITAL_AMOUNT,0)) "+
						            " FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+ 
						            " WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
						                  " FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
						                  " WHERE  APPLICATION_NO =   A.APPLICATION_NO  "+
						                  " AND       ACTIVE_STATUS  =   'Y' "+
						                  " ) "+
						                  " AND INVOICE_NO IS NULL  "+
						                  " AND APPLICATION_NO = A.APPLICATION_NO  "+
									  " ) OUTS_CAPITAL, "+ // 7 total outstanding capital
										
										" ( "+
										 " SELECT "+ 
										 " SUM(BALANCE_TO_BE_RECEIVED) "+
										 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
										 " WHERE     ACTIVE_STATUS='Y' "+
										 " AND  A.FINANCE_NO = '"+m_finance_no+"' "+
										 " AND  VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+ /*added by ns on 18-01-2012*/
										 " AND FINANCE_NO IN "+   
										    " (SELECT "+
										        " FINANCE_NO "+
										        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										        " WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO) "+
										        " AND APPLICATION_STATUS<>'CANCEL') "+ 
											" ) DUE_AMOUNT_INV, "+ // 3 due amount / arrears*/
									//"        NVL("+m_schema_name+".AF_CO_GET_ODI_DUE(A.FINANCE_NO),0) ODI,  "+ // commented by udara on 24-07-2013
									//" "+m_schema_name+".AF_CO_GET_ODI_AMT(A.FINANCE_NO,'"+m_date+"') ODI, "+ // added by udara on 24-07-2013
									
									// added by udara on 24-07-2013
									// commented by udara on 12-08-2013
									/*
									" (SELECT SUM(ODI_BAL_AMOUNT) "+
										" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
										" WHERE INVOICE_NO IN ( "+
										  " SELECT INVOICE_NO "+
										  " FROM AF_CO_PRO_INVOICE A "+
										  " WHERE FINANCE_NO = '"+m_finance_no+"' "+
										  " AND INVOICE_TYPE = 'ODI' "+
										" ) "+
									 " ) ODI, "+
									*/
									
									// added by udara on 12-08-2013
									" ( "+
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
									" ) ODI, "+
									// end by udara on 12-08-2013
									
									// "      +  NVL("+m_schema_name+".AF_CO_CAL_FUTURE_ODI(A.FINANCE_NO,'"+m_date+"'),0) ODI, "+ // commeted by thamali 2013.05.21
									
									// added below by udara on 13-03-2013			  							
									" (	"+									
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       //" AND VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
								       //" AND INVOICE_TYPE = 'INSURANCE'  "+  // commented by udara on 23-07-2013
										" AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') "+ // added by udara on 23-07-2013
								       /*
										" AND FINANCE_NO IN  "+   
								          " (SELECT "+
								              " FINANCE_NO "+
								              " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								              " WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO) "+
								              " AND APPLICATION_STATUS<>'CANCEL' "+
								              " ) "+ 
												*/
									" ) INSURANCE_CHARGE, "+
									
									" (	"+									
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       //" AND VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
								       " AND INVOICE_TYPE = 'VISIT'  "+
								       /*
										" AND FINANCE_NO IN  "+   
								          " (SELECT "+
								              " FINANCE_NO "+
								              " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								              " WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO) "+
								              " AND APPLICATION_STATUS<>'CANCEL' "+
								              " ) "+ 
												*/
									" ) VISIT_CHARGES, "+
									
									" (	"+	
									" SELECT  "+
								       " SUM(BALANCE_TO_BE_RECEIVED) "+
								       " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								       " WHERE     ACTIVE_STATUS='Y' "+
								       " AND  A.FINANCE_NO =  '"+m_finance_no+"' "+
								       //" AND VALUE_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
								       " AND INVOICE_TYPE = 'CEASEINGC'  "+
								       /*
										" AND FINANCE_NO IN  "+   
								          " (SELECT "+
								              " FINANCE_NO "+
								              " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								              " WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO) "+
								              " AND APPLICATION_STATUS<>'CANCEL' "+
								              " ) "+ 	
												*/
									" ) CEASEINGC, "+
									
									//" "+m_schema_name+".AF_CO_GET_CONTRACT_BAL_ARREARS('"+m_finance_no+"',"+m_schema_name+".AF_CO_GET_CLIENT_CODE(AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'"+m_date+"','"+m_username+"') EXCESS_AMNT, "+ // added by udara on 12-07-2013
									" "+m_schema_name+".AF_CO_NEW_CON_BAL_ARR('"+m_finance_no+"',"+m_schema_name+".AF_CO_GET_CLIENT_CODE(AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'"+m_date+"','"+m_username+"') EXCESS_AMNT, "+
									
									" (SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
						            " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
									" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
									" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
									" A.FINANCE_NO = B.FINANCE_NO AND "+
									" B.VALUE_DATE >SYSDATE  AND "+
									" B.ACTIVE_STATUS='Y' AND "+
									" B.INVOICE_TYPE = 'INV_GENER') NEXT_DUE, "+
									
									// end by udara on 13-03-2013
									
									
									// added by udara on 16-09-2013
									
									 " (SELECT SUM(B.BAL_TOBE_RECEIVE) "+
							         " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B  "+
							         " WHERE  A.REC_NO=B.REC_NO    "+
							         " AND    A.CLIENT_CODE=B.CLIENT_CODE    "+
							         " AND    B.FINANCE_NO = '"+m_finance_no+"'  "+
							         " AND    B.BAL_TOBE_RECEIVE<>0  "+
							         " AND    NVL ( A.INSURANCE,0 ) > 0  "+
							         " AND    STATUS NOT IN ('CAD','RET','CANCLE','C') ) INS_EXCESS , "+
										
									 " A.CLIENT_CODE  CLIENT_CODE "+
									
									// end by udara on 16-09-2013
									

									// added by udara on 01-10-2013
									/*
									" ( "+
										" SELECT SUM(TOTAL_AMOUNT) FROM (  "+
											" SELECT DISTINCT A.REC_NO, (A.REC_AMOUNT-C.TOTAL_AMOUNT) TOTAL_AMOUNT "+
												" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+m_schema_name+".AF_CO_PRO_INVOICE C  "+
												" WHERE C.FINANCE_NO = '"+m_finance_no+"' "+
												" AND A.INSURANCE > 0 "+
												" AND  A.REC_NO = B.RECEIPT_NO  "+
												" AND B.INVOICE_NO = C.INVOICE_NO  "+
												" AND (C.INVOICE_TYPE = 'INSURANCE' OR C.REMARKS = 'CHARGES - INSURANCE')  "+
												" ) "+
									" ) INS_EXCESS "+
									*/
									// end by udara on 01-10-2013
									
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
									 	" WHERE A.FINANCE_NO = '"+m_finance_no+"' "+
									" ");
							
							if(rs.next()){
								
								//out.println( rs.getDouble("EXCESS_AMNT") + "   " +rs.getDouble("ODI")+ "   " +rs.getDouble("NEXT_DUE")+"   "+rs.getDouble("CEASEINGC")+"   "+rs.getDouble("VISIT_CHARGES") + "   "+ rs.getDouble("INS_EXCESS"));
								
								client_code          = rs.getString("CLIENT_CODE");
								
								client_name          = rs.getString(1);
								reg_no               = rs.getString(2);
								arrears_amount       = rs.getDouble("DUE_AMOUNT")+rs.getDouble("DUE_AMOUNT_INV")+rs.getDouble("ODI"); // commented by udara on 24-07-2013
								//arrears_amount       = rs.getDouble("DUE_AMOUNT")+rs.getDouble("DUE_AMOUNT_INV")+m_odi_net_value; // added by udara on 24-07-2013
								rental_amount        = rs.getDouble(4);
								no_of_future_rentals = rs.getInt(5);
								//closing_rate         = rs.getDouble(6);
								future_capital       = rs.getDouble(7);
								
								//arrears_excess       = rs.getDouble("DUE_AMOUNT") + rs.getDouble("ODI"); // added by udara on 13-03-2013
								//arrears_excess       = rs.getDouble("DUE_AMOUNT_INV") + rs.getDouble("ODI") + rs.getDouble("EXCESS_AMNT"); // added by udara on 12-07-2013 // added by udara on 23-07-2013
								//arrears_excess       = rs.getDouble("EXCESS_AMNT")+rs.getDouble("ODI")+rs.getDouble("NEXT_DUE")-rs.getDouble("CEASEINGC")-rs.getDouble("VISIT_CHARGES"); // commented by udara on 16-09-2013 // commented by udara on 24-07-2013 // added by udara on 23-07-2013
								arrears_excess       = rs.getDouble("EXCESS_AMNT")+rs.getDouble("ODI")+rs.getDouble("NEXT_DUE")-rs.getDouble("CEASEINGC")-rs.getDouble("VISIT_CHARGES"); // added by udara on 16-09-2013
								//arrears_excess       = rs.getDouble("EXCESS_AMNT")+m_odi_net_value-rs.getDouble("CEASEINGC")-rs.getDouble("VISIT_CHARGES");
								ceasing_charges      = rs.getDouble("CEASEINGC"); // added by udara on 13-03-2013
								visiting_charges     = rs.getDouble("VISIT_CHARGES"); // added by udara on 13-03-2013
								insurance_charges    = rs.getDouble("INSURANCE_CHARGE")-rs.getDouble("INS_EXCESS"); // mod by udara on 01-10-2013 // added by udara on 13-03-2013
								
							}
							
							//if(m_closing_rate==null || m_closing_rate.equals(""))
							//	closing_rate = 0;
							
							closing_rate = Double.parseDouble(m_closing_rate);
							
							//total_arrears = arrears_amount + ceasing_charges + insurance_charges + visiting_charges; // commented by udara on 17-01-2013
							
							//total_arrears = arrears_amount; // commented by udara on 12-08-2013 // added by udara on 17-01-2013
							total_arrears = arrears_excess + ceasing_charges + insurance_charges + visiting_charges; // added by udara on 12-08-2013
							
							
							//other_charges = ceasing_charges + insurance_charges + visiting_charges; // added by udara on 17-01-20136
						
							normal_closing = (no_of_future_rentals * rental_amount) + total_arrears;
							
							closing_amount = (rental_amount * closing_rate) + total_arrears;
							
							final_closing_amount = closing_amount;
							
							rebate_amount  = normal_closing - closing_amount;
							
							// future_capital = closing_amount - rebate_amount;
							
							balance_capital = future_capital;
							
							interest_for_capital = future_capital * 0.05;
							
							total_amount = future_capital + interest_for_capital + total_arrears;
						
						}
						catch(Exception ee){
							
							out.println(ee.toString());
							
						}
			
			            out.println("<HTML><HEAD><TITLE>Closing Report</TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");

						out.println("<SCRIPT language=\"JavaScript\">"); 
			
						out.println("function print_window(){");
						out.println("    m_table.innerHTML=\"\" ");
						out.println("    window.print();");
			            out.println("}");
			
						out.println("function add_button(){");
						out.println("   m_writedata='<tr><td width=\"*%\" align=\"left\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_window()\"></td></tr>';"); 
						out.println("   m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    					out.println("   m_writedata+'</table>';");
						out.println("}");
						
						// added by udara on 08-10-2013
						
						out.println("function show_transaction_history_new(val,val2){ "); 
		                out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
		                out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		                out.println("}");
						
						// end by udara on 08-10-2013

			            out.println("</script>");
						
						
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' onload='add_button();' >");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
						out.println("<table align='center' width='100%' class='table'>"); 
						out.println("<tr>");  
						out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
						out.println("</tr>"); 
						out.println("</table>");
						
						out.println("<br>"); 
						
						out.println("<TABLE  WIDTH='90%' >");
						out.println("<TR><TD><CENTER><B> "+company_name+" </B></TD></TR>");
						out.println("</TABLE>");
						
						out.println("<TABLE  WIDTH='90%' >");
						out.println("<TR><TD><CENTER><B>Closing Report</B></TD></TR>");
						out.println("</TABLE>");
						
						out.println("<BR><BR>");
						
						out.println("<TABLE  WIDTH='90%' align=center border=0 >");
						
						// added by udara on 14-08-2013
						out.println("   <TR>");
						out.println("      <TD WIDTH='20%' ><B> Date </B></TD>");
						out.println("      <TD WIDTH='30%' > "+m_date_show+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						// end by udara on 14-08-2013
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='20%' ><B> Customer Name </B></TD>");
						out.println("      <TD WIDTH='30%' > "+client_name+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Con-Number </B></TD>");
						out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_transaction_history_new('"+client_code+"','"+m_finance_no+"')\" ><u> "+m_finance_no+" </u></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						// added by udara 03-04-2014
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Security Details </B></TD>");
						out.println("      <TD WIDTH='20%' style=cursor:hand onClick=\"show_run_con_det('"+m_app_no+"')\" ><u> View </u></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						// end by udara 03-04-2014
						
						
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Vehicle No </B></TD>");
						out.println("      <TD WIDTH='20%' > "+reg_no+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("<tr>");
						out.println("      <TD WIDTH='20%' ><b> Rental Date </b></TD>");
						out.println("      <TD WIDTH='15%' > "+rental_date+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("</tr>");
					
						out.println("<tr>");
						out.println("      <TD WIDTH='20%' ><b> No of Rental </b></TD>");
						out.println("      <TD WIDTH='15%' > "+m_rental_count+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("      <TD WIDTH='20%' ><b> Capital </b></TD>");
						out.println("      <TD WIDTH='15%' > "+nf.format(m_capitl)+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("      <TD WIDTH='20%' ><b> Cash Collection Without Insuarance </b></TD>");
						out.println("      <TD WIDTH='15%' > "+nf.format(tot_cash_coll)+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("      <TD WIDTH='20%' ><b> Last Six Months Rental Payments Without Insuarance </b></TD>");
						out.println("      <TD WIDTH='15%' > "+nf.format(tot_cash_coll_6months)+" </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("      <TD WIDTH='20%' ><b>  </b></TD>");
						out.println("      <TD WIDTH='15%' ></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("</tr>");
						
						
						out.println("</TABLE>");
						
						out.println("<BR><BR>");
						
						out.println("<TABLE  WIDTH='90%' align=center border=0 >");
						
						// released comments by udara on 21-02-2013
						out.println("   <TR>");
						out.println("      <TD WIDTH='20%' ><b> Arrears/(Excess) </b></TD>");
						out.println("      <TD WIDTH='15%' > &nbsp; </TD>");
						//out.println("      <TD WIDTH='15%' align=right ><b> "+nf.format(arrears_amount)+" </b></TD>"); 
						out.println("      <TD WIDTH='15%' align=right ><b> "+nf.format(arrears_excess)+" </b></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Ceasing Charges </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><b> "+nf.format(ceasing_charges)+" </b></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Insurance </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><b> "+nf.format(insurance_charges)+" </b></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Visiting Chargers </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><b> "+nf.format(visiting_charges)+" </b></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						// released comments end by udara on 21-02-2013
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Total Arrears </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						//out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(total_arrears)+" </B></TD>"); 
						//out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(arrears_excess+"<BR>"+ ceasing_charges +"<BR>" +insurance_charges +"<BR>"+ visiting_charges)+" </B></TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(arrears_excess + ceasing_charges + insurance_charges + visiting_charges)+" </B></TD>"); // commented above and added  by thamali 2013.05.20

						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						/*
						// added by udara on 17-01-2013
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Other Charges </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(other_charges)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						// end by udara on 17-01-2013
						*/
						
						out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");

						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Balance Period </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+no_of_future_rentals+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Rental </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(rental_amount)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Closing Rate </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(closing_rate)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
						
						// added by udara on 08-01-2013
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Option 1 </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right > &nbsp; </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Normal Closing </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> "+nf.format(normal_closing)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Closing </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(closing_amount)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Rebate </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						
						if(rebate_amount<0)
							out.println("      <TD WIDTH='10%' align=right ><B> ("+nf.format(rebate_amount*-1)+") </B></TD>");
						else
							out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(rebate_amount)+" </B></TD>");
						
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
						
						// added by udara on 08-01-2013
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Option 2 </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right > &nbsp; </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Final Closing Amount </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> "+nf.format(final_closing_amount)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
						
						// added by udara on 08-01-2013
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><b> Option 3 </b></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right > &nbsp; </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Future Capital </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(future_capital)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Balance capital </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(balance_capital)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Interest for capital </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(interest_for_capital)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Arrears /<B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right ><B> "+nf.format(total_arrears)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='15%' ><B> Total </B></TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' align=right color=\"#006600\" ><B> "+nf.format(total_amount)+" </B></TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						
						out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
						out.println("   <TR><TD colspan=4 height=10px > &nbsp; </TD></TR>");
						
						out.println("   <TR>");
						out.println("      <TD WIDTH='10%' > ............................ </TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' > ............................ </TD>");
						out.println("      <TD WIDTH='10%'  > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' > ............................ </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("   <TR>");
						out.println("      <TD WIDTH='10%' > Prepared By </TD>");
						out.println("      <TD WIDTH='10%' > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' > Authorized Signature 1 </TD>");
						out.println("      <TD WIDTH='10%'  > &nbsp; </TD>");
						out.println("      <TD WIDTH='10%' > Authorized Signature 2 </TD>");
						out.println("      <TD WIDTH='*%'  > &nbsp; </TD>");
						out.println("   </TR>");
						out.println("</TABLE>");
						
						// udara 03-04-2014
						out.println("</form>"); 
					    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					    out.println("</BODY></HTML>");
						
						
			  		}