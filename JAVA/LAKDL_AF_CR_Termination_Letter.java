//KANISHKA DILSHAN ON 26-01-2015

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_Termination_Letter extends javax.servlet.http.HttpServlet {
	
	
	
	public /*synchronized*/ void service(HttpServletRequest req, HttpServletResponse res)
	{
		Connection conn= null;
		Statement stmt= null,stmt1= null,stmt3= null;
		java.text.NumberFormat nf,nf1,nf2;
		ResultSet rs= null,rs1= null,rs2=null,rs_3= null;
		String m_chksql= null;
		ServletOutputStream out = null;	
		try {
			
			//************************************************************	
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();		
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_fschema_name=con_method.client_name.trim();
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			//nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(0);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);   
			nf2.setMinimumFractionDigits(2);
			nf2.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			nf1.setMaximumFractionDigits(4);
			
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			stmt3= conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("generate_letter")){
				
				String m_Letter_date="";
				String m_c_code="";
				String m_name="";
				String m_add1="";
				String m_add2="";
				String m_city_desc="";
				String m_designation="";
				String m_termiDate="";
				
				String m_cont_person="";
				String m_desig_payment="";
				String m_client_type="C";
				
				String m_print 				= req.getParameter("print");
				String m_due_amount			= req.getParameter("due_amount");
				double m_due_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("due_amount")));
				String m_due_rent			= req.getParameter("due_rent");
				String m_odi_amount 		= req.getParameter("odi_amount");
				String m_sale_price			= req.getParameter("sale_price");//stk
				String m_tot_chrg			= req.getParameter("tot_chrg");
				String m_client				= req.getParameter("client");
				String m_agreemnt_no 		= req.getParameter("agreemnt_no");
				String m_term_type			= req.getParameter("term_type");
				String m_tDate	   			= req.getParameter("t_date");
				String mm_tDate	   			= req.getParameter("t_date");
				
				String m_future_capital			= req.getParameter("capital_outs");
				double m_future_capital_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("capital_outs")));
				String m_future_interest 		= req.getParameter("future_intrst");
				double m_future_interest_double = Double.parseDouble(con_method.met_unformat_number(req.getParameter("future_intrst")));
				String m_rebate_rate			= req.getParameter("rebate_rate");
				double m_rebate_rate_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("rebate_rate")));
				String m_rebate_amount			= req.getParameter("rebate_intrst");
				double m_rebate_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("rebate_intrst")));
				String future_min_rebate    	= req.getParameter("future_min_rebate");
				double future_min_rebate_double = Double.parseDouble(con_method.met_unformat_number(req.getParameter("future_min_rebate")));
				String odi 						= req.getParameter("odi");
				double odi_double 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi")));
				String odi_adj					= req.getParameter("odi_adj");
				double odi_adj_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_adj")));
				String odi_net					= req.getParameter("odi_net");
				double odi_net_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_net")));
				String termi					= req.getParameter("termi");
				double termi_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi")));
				//String odi_net				= req.getParameter("odi_net");
				double termi_vat 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi_vat")));
				double charges_vat 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("charges_vat")));
				double m_sale_price_double		= Double.parseDouble(con_method.met_unformat_number(req.getParameter("sale_price")));
				
				double m_gain_loss_double		= Double.parseDouble(con_method.met_unformat_number(req.getParameter("gain_loss")));
				double m_tot_payable_double		= Double.parseDouble(con_method.met_unformat_number(req.getParameter("tot_payable")));
				double m_unallocated_receipt    = Double.parseDouble(con_method.met_unformat_number(req.getParameter("unallocated_receipt")));
				
				double m_future_debit_notes     = Double.parseDouble(con_method.met_unformat_number(req.getParameter("future_debit_notes"))); // added by udara 06-01-2021
				
				double m_add_total_due_amt      = m_due_amount_double-m_unallocated_receipt;
				
				
				
				
				
				
				
				out.println("<html><head>"); 
				out.println("<title></title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
				out.println("<script>");
				out.println("function print_data(){");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_Letter?chksql=generate_letter&due_amount="+m_due_amount+"&due_rent="+m_due_rent+"&odi_amount="+m_odi_amount+"&sale_price="+m_sale_price+"&tot_chrg="+m_tot_chrg+"&agreemnt_no="+m_agreemnt_no+"&term_type="+m_term_type+"&client="+m_client+"&print=false&t_date="+m_tDate+"&capital_outs="+m_future_capital+"&future_intrst="+m_future_interest+"&rebate_rate="+m_rebate_rate+"&rebate_intrst="+m_rebate_amount+"&future_min_rebate="+future_min_rebate+"&odi="+odi+"&odi_adj="+odi_adj+"&odi_net="+odi_net+"&termi="+termi+"&termi_vat="+termi_vat+"&charges_vat="+charges_vat+"\";");
				out.println(" window.location.href=m_url;");
				out.println(" m_table.innerHTML=\"\"; ");
				out.println(" window.print();");
				out.println("}");
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("false")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else
				{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
				}
				
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>");				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");	
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/legal_letter_head.gif\"></DIV>");
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'ddth Month YYYY') FROM DUAL ");								
				
				if(rs.next()){
					m_Letter_date=rs.getString(1);
				}
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_tDate+"','DD-MM-YYYY'),'ddth Month YYYY') FROM DUAL ");								
				
				
				if(rs.next()){
					m_tDate=rs.getString(1);
				}
				
				rs1 = stmt1.executeQuery (	" SELECT "+
					" NVL(A.CLIENT_CODE,'-'), "+//1
					" NVL(UPPER(A.FULL_NAME),'-'), "+//2
					" NVL(UPPER(A.REGISTERED_ADDRESS1),'-'), "+//3
					" NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+//4
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+//5
					" NVL(DESIGNATION,'-'), "+ //6
					" TO_CHAR(SYSDATE,'ddth Month YYYY') "+ //7
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
					" WHERE  A.CLIENT_CODE='"+m_client+"' ");
				
				
				if(rs1.next()){
					m_c_code=rs1.getString(1);
					m_name=rs1.getString(2);
					m_add1=rs1.getString(3);
					m_add2=rs1.getString(4);
					m_city_desc=rs1.getString(5);
					m_designation=rs1.getString(6);
					m_termiDate=rs1.getString(7);
				}
				
				// commented by udara 15-11-2018
				/*
				// added by udara 14-11-2018
				rs1=stmt1.executeQuery
				//out.println
						(" "+
						" SELECT "+

						" ( "+
						" SELECT  SUM(ODI_BAL_AMOUNT) FROM ( "+
						" SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B,  "+
						" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
						" WHERE A.FINANCE_NO ='"+m_agreemnt_no+"' AND  "+
						" A.FINANCE_NO = B.FINANCE_NO AND  "+
						" B.ACTIVE_STATUS='Y' AND  "+
						" B.INVOICE_NO = C.INVOICE_NO   "+
						
						" ) "+
						" ) ODI, "+
		  							
						" (	"+									
						" SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') "+ 

						" ) INSURANCE_CHARGE, "+
						
						" (	"+									
						" SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND INVOICE_TYPE = 'VISIT'  "+

						" ) VISIT_CHARGES, "+
						
						" (	"+	
						" SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND INVOICE_TYPE = 'CEASEINGC'  "+

						" ) CEASEINGC, "+

						" NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR('"+m_agreemnt_no+"',A.CLIENT_CODE,'"+mm_tDate+"','"+m_username+"'),0) EXCESS_AMNT, "+

						" (SELECT  SUM(TOTAL_AMOUNT) "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE A.FINANCE_NO ='"+m_agreemnt_no+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO AND "+
						" B.VALUE_DATE > TO_DATE('"+mm_tDate+"','DD-MM-YYYY')  AND "+ 
						" B.ACTIVE_STATUS='Y' AND "+
						" B.INVOICE_TYPE = 'INV_GENER') NEXT_DUE, "+
						
						" NVL("+m_schema_name+".AF_CO_INS_EXCESS_NEW('"+m_agreemnt_no+"',NULL),0) INS_EXCESS, "+ 
						
						" A.CLIENT_CODE  CLIENT_CODE, "+

						" (SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND INVOICE_TYPE  = 'N1'  "+
						")REPOSSESS, "+ 
						
						" (SELECT  SUM(TOTAL_AMOUNT) "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE A.FINANCE_NO ='"+m_agreemnt_no+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO AND "+
						" B.VALUE_DATE > TO_DATE('"+mm_tDate+"','DD-MM-YYYY')  AND "+ 
						" B.ACTIVE_STATUS='Y' AND "+
						" B.INVOICE_TYPE = 'INV_GENER') NEXT_DUE "+

						
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						" WHERE A.FINANCE_NO = '"+m_agreemnt_no+"' "+
						" ");
				
				   	double arrears_excess = 0;
				   	double ceasing_charges = 0;
					double visiting_charges = 0;
					double insurance_charges = 0;
					double m_repossess_charge = 0;
				
				   if(rs1.next()){
						arrears_excess       = rs1.getDouble("EXCESS_AMNT")+rs1.getDouble("ODI")+rs1.getDouble("NEXT_DUE")-rs1.getDouble("REPOSSESS")-rs1.getDouble("CEASEINGC")-rs1.getDouble("VISIT_CHARGES");  
						ceasing_charges      = rs1.getDouble("CEASEINGC"); 
						visiting_charges     = rs1.getDouble("VISIT_CHARGES"); 
						insurance_charges    = rs1.getDouble("INSURANCE_CHARGE")-rs1.getDouble("INS_EXCESS"); 
						m_repossess_charge   = rs1.getDouble("REPOSSESS");
					}
				*/	
				
				// added by udara 15-11-2018
				
				rs1=stmt1.executeQuery
				//out.println
						(" "+
						" SELECT "+
		  							
						" (	"+									
						" SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE') "+ 

						" ) INSURANCE_CHARGE, "+
						
						" (	"+									
						" SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND INVOICE_TYPE = 'VISIT'  "+

						" ) VISIT_CHARGES, "+
						
						" (	"+	
						" SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND INVOICE_TYPE = 'CEASEINGC'  "+

						" ) CEASEINGC, "+

						" (SELECT  "+
						" SUM(BALANCE_TO_BE_RECEIVED) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND  A.FINANCE_NO =  '"+m_agreemnt_no+"' "+
						" AND INVOICE_TYPE  = 'N1'  "+
						")REPOSSESS, "+ 
						
						" NVL(TER_TYPE,'-') TER_TYPE "+ // added by udara 23-05-2019


						
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						" WHERE A.FINANCE_NO = '"+m_agreemnt_no+"' "+
						" ");
				
				   	double arrears_excess = 0;
				   	double ceasing_charges = 0;
					double visiting_charges = 0;
					double insurance_charges = 0;					
					String m_ter_type = "";
				
				   if(rs1.next()){
						ceasing_charges      = rs1.getDouble("CEASEINGC") + rs1.getDouble("REPOSSESS"); 
						visiting_charges     = rs1.getDouble("VISIT_CHARGES"); 
						insurance_charges    = rs1.getDouble("INSURANCE_CHARGE"); 						
						m_ter_type           = rs1.getString("TER_TYPE");
					}
					
				
				arrears_excess = m_add_total_due_amt - ceasing_charges - visiting_charges - insurance_charges;
				
				if(arrears_excess<0){
					arrears_excess = 0;
				}
				
				// end by udara 14-11-2018
				
				// commented by udara 05-11-2018
				/*
				rs1 = stmt1.executeQuery (	" SELECT "+
					" NVL(SUM(A.FINANCED_AMOUNT),0), "+//1
					" ROUND(TO_NUMBER(C.PERIOD)), "+//2
					" C.RATE, "+//3
					" D.NET_RENTAL_AMOUNT, "+//4
					" 0 interest_arrears_funct, "+//5
					" 0 other_charges_funct "+//6
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
					"      "+m_schema_name+".AF_CO_PRO_APP_PRICING C, "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
					" WHERE  A.APPLICATION_NO = B.APPLICATION_NO "+
					" AND    A.APPLICATION_NO = C.APPLICATION_NO "+
					" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
					" AND    TO_NUMBER(D.INSTALLMENT_NO) = TO_NUMBER(ROUND(C.PERIOD/2)) "+
					" AND    A.ACTIVE_STATUS  = 'Y' "+
					" AND    B.FINANCE_NO     = '"+m_agreemnt_no+"' "+
					" GROUP BY C.PERIOD,C.RATE,D.NET_RENTAL_AMOUNT  ");
				*/
				
				// added by udara 05-11-2018
				
				
				
				/*
				
				rs1=stmt1.executeQuery(
						"SELECT  A.FINANCE_NO, "+m_schema_name+".AF_CO_GET_RENTAL_COLLECTION(A.FINANCE_NO,'',TO_CHAR(TRUNC(SYSDATE),'DD-MM-YYYY')) "+ 
						"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+ 
						"WHERE  A.FINANCE_NO='"+m_agreemnt_no+"'");
					
					
				if(rs1.next()){
					tot_cash_coll= rs1.getDouble(2);		
				}
					
					
				
					
				rs1=stmt1.executeQuery(
						"SELECT  A.FINANCE_NO, "+m_schema_name+".AF_CO_GET_RENTAL_COLLECTION(A.FINANCE_NO,'6MONTH',TO_CHAR(TRUNC(SYSDATE),'DD-MM-YYYY')) "+ 
						"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+ 
						"WHERE  A.FINANCE_NO='"+m_agreemnt_no+"'");
				if(rs1.next()){
					tot_cash_coll_6months= rs1.getDouble(2);		
				}	
				*/	
				
				// commented by udara 08-07-2019
				/*
				if(m_ter_type.equals("BAL_TRANSF")){ // added if condition on 23-05-2019
					
						rs1 = stmt1.executeQuery (	" SELECT "+
						//" NVL(SUM(A.FINANCED_AMOUNT),0), "+//1 // commented by udara 23-05-2019
						" A.FINANCED_AMOUNT, "+ // added by udara 23-05-2019
						" ROUND(TO_NUMBER(C.PERIOD)), "+//2
						" C.RATE, "+//3
						" D.NET_RENTAL_AMOUNT, "+//4
						" 0 interest_arrears_funct, "+//5
						" 0 other_charges_funct, "+//6
						" DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO)) APPLICATION_STATUS, "+ // 7 added by udara 05-11-2018
						" A.REG_NO REG_NO, "+ // 8 added by udara 05-11-2018
						" TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(B.APPLICATION_NO),'DD-MM-YYYY'),'DD') MATURITY_DATE, "+ // 9 added by udara 05-11-2018
						" B.APPLICATION_NO  APPLICATION_NO, "+
						" NVL("+m_schema_name+".AF_GET_SECURITY_INFO_COUNT(B.APPLICATION_NO),0), "+ // 11 added by udara 23-08-2018
						" "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(B.APPLICATION_NO), "+ // 12 future rental
						" "+m_schema_name+".AF_CO_GET_STRUCTURED_STATUS(B.APPLICATION_NO) "+ // 13
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
						"      "+m_schema_name+".AF_CO_PRO_APP_PRICING C, "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
						" WHERE  A.APPLICATION_NO = B.APPLICATION_NO "+
						" AND    A.APPLICATION_NO = C.APPLICATION_NO "+
						" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
						//" AND    TO_NUMBER(D.INSTALLMENT_NO) = TO_NUMBER(ROUND(C.PERIOD/2)) "+ // commented by udara 23-05-2019 to fix balance transfer issue
						" AND    A.ACTIVE_STATUS  = 'Y' "+
						" AND    B.FINANCE_NO     = '"+m_agreemnt_no+"' "+
						" GROUP BY C.PERIOD,C.RATE,D.NET_RENTAL_AMOUNT,B.APPLICATION_NO, A.REG_NO, A.FINANCED_AMOUNT   ");
					
				}
				*/
				//else{ // commented by udara 08-07-2019
				
						rs1 = stmt1.executeQuery (	" SELECT "+
							" NVL(SUM(A.FINANCED_AMOUNT),0), "+//1
							" ROUND(TO_NUMBER(C.PERIOD)), "+//2
							" C.RATE, "+//3
							" D.NET_RENTAL_AMOUNT, "+//4
							" 0 interest_arrears_funct, "+//5
							" 0 other_charges_funct, "+//6
							" DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO)) APPLICATION_STATUS, "+ // 7 added by udara 05-11-2018
							" A.REG_NO REG_NO, "+ // 8 added by udara 05-11-2018
							" TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(B.APPLICATION_NO),'DD-MM-YYYY'),'DD') MATURITY_DATE, "+ // 9 added by udara 05-11-2018
							" B.APPLICATION_NO  APPLICATION_NO, "+
							" NVL("+m_schema_name+".AF_GET_SECURITY_INFO_COUNT(B.APPLICATION_NO),0), "+ // 11 added by udara 23-08-2018
							" "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(B.APPLICATION_NO), "+ // 12 future rental
							" "+m_schema_name+".AF_CO_GET_STRUCTURED_STATUS(B.APPLICATION_NO) "+ // 13
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
							"      "+m_schema_name+".AF_CO_PRO_APP_PRICING C, "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
							" WHERE  A.APPLICATION_NO = B.APPLICATION_NO "+
							" AND    A.APPLICATION_NO = C.APPLICATION_NO "+
							" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
							" AND    TO_NUMBER(D.INSTALLMENT_NO) = TO_NUMBER(ROUND(C.PERIOD/2)) "+
							" AND    A.ACTIVE_STATUS  = 'Y' "+
							" AND    B.FINANCE_NO     = '"+m_agreemnt_no+"' "+
							" GROUP BY C.PERIOD,C.RATE,D.NET_RENTAL_AMOUNT,B.APPLICATION_NO, A.REG_NO   ");
						// end by udara 05-11-2018
				
				//} // added if condition on 23-05-2019 // commented by udara 08-07-2019
				
				double finance_amount    	= 0.00;
				int    period         		= 0;
				double interest_rate  		= 0.00;
				double net_rental     		= 0.00;
				double interest_arrears     = 0.00;
				double other_charges        = 0.00;
				
				String m_contract_status = ""; // added by udara 05-11-2018
				String m_reg_no = ""; // added by udara 05-11-2018
				String m_rental_date = ""; // added by udara 05-11-2018
				String m_app_no = ""; // added by udara 05-11-2018
				int mm_security_info_count = 0; // added by udara 05-11-2018
				int m_balance_period = 0; // added by udara 05-11-2018
				String m_structured_status = "";
				
				int check_count = 0; // added by udara 13-08-2021
				
				if(rs1.next()){
					
					check_count = check_count + 1; // added by udara 13-08-2021
					
					finance_amount 		= rs1.getDouble(1);
					period         		= rs1.getInt(2);
					interest_rate  		= rs1.getDouble(3);
					net_rental     		= rs1.getDouble(4);
					interest_arrears 	= rs1.getDouble(5);	
					other_charges       = rs1.getDouble(6);	
					
					m_contract_status   = rs1.getString(7); // added by udara 05-11-2018
					m_reg_no            = rs1.getString(8); // added by udara 05-11-2018
					m_rental_date       = rs1.getString(9); // added by udara 05-11-2018
					m_app_no 			= rs1.getString(10); // added by udara 05-11-2018
					mm_security_info_count = rs1.getInt(11); // added by udara 05-11-2018
					m_balance_period = rs1.getInt(12); // added by udara 05-11-2018
					m_structured_status = rs1.getString(13); // added by udara 16-11-2018
					
				}
				
				// added by udara 13-08-2021
				if(check_count==0 && m_ter_type.equals("BAL_TRANSF")){
					
					rs1 = stmt1.executeQuery (	" SELECT "+
							" NVL(SUM(A.FINANCED_AMOUNT),0), "+//1
							" ROUND(TO_NUMBER(C.PERIOD)), "+//2
							" C.RATE, "+//3
							" D.NET_RENTAL_AMOUNT, "+//4
							" 0 interest_arrears_funct, "+//5
							" 0 other_charges_funct, "+//6
							" DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO)) APPLICATION_STATUS, "+ // 7 added by udara 05-11-2018
							" A.REG_NO REG_NO, "+ // 8 added by udara 05-11-2018
							" TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(B.APPLICATION_NO),'DD-MM-YYYY'),'DD') MATURITY_DATE, "+ // 9 added by udara 05-11-2018
							" B.APPLICATION_NO  APPLICATION_NO, "+
							" NVL("+m_schema_name+".AF_GET_SECURITY_INFO_COUNT(B.APPLICATION_NO),0), "+ // 11 added by udara 23-08-2018
							" "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(B.APPLICATION_NO), "+ // 12 future rental
							" "+m_schema_name+".AF_CO_GET_STRUCTURED_STATUS(B.APPLICATION_NO) "+ // 13
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
							"      "+m_schema_name+".AF_CO_PRO_APP_PRICING C, "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D "+
							" WHERE  A.APPLICATION_NO = B.APPLICATION_NO "+
							" AND    A.APPLICATION_NO = C.APPLICATION_NO "+
							" AND    A.APPLICATION_NO = D.APPLICATION_NO "+
							//" AND    TO_NUMBER(D.INSTALLMENT_NO) = TO_NUMBER(ROUND(C.PERIOD/2)) "+
							" AND    A.ACTIVE_STATUS  = 'Y' "+
							" AND    B.FINANCE_NO     = '"+m_agreemnt_no+"' "+
							" GROUP BY C.PERIOD,C.RATE,D.NET_RENTAL_AMOUNT,B.APPLICATION_NO, A.REG_NO   ");
					
					
							if(rs1.next()){
							
									finance_amount 		= rs1.getDouble(1);
									period         		= rs1.getInt(2);
									interest_rate  		= rs1.getDouble(3);
									net_rental     		= rs1.getDouble(4);
									interest_arrears 	= rs1.getDouble(5);	
									other_charges       = rs1.getDouble(6);	
									
									m_contract_status   = rs1.getString(7); // added by udara 05-11-2018
									m_reg_no            = rs1.getString(8); // added by udara 05-11-2018
									m_rental_date       = rs1.getString(9); // added by udara 05-11-2018
									m_app_no 			= rs1.getString(10); // added by udara 05-11-2018
									mm_security_info_count = rs1.getInt(11); // added by udara 05-11-2018
									m_balance_period = rs1.getInt(12); // added by udara 05-11-2018
									m_structured_status = rs1.getString(13); // added by udara 16-11-2018
							
							}
					
					
				}
				// end by udara 13-08-2021
				
				double tot_cash_coll = 0;
				double tot_cash_coll_6months = 0;
				double down_payment_6_month = 0;
				double down_payment = 0;
				double charges_collections = 0; // added by udara 27-06-2019
				
				rs1=stmt1.executeQuery(" "+
					" SELECT DISTINCT C.FINANCE_NO, NVL(SUM(A.RENTAL_OTER_INVOICE),0) "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+  
					" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO "+  
					" AND NVL(A.RENTAL_OTER_INVOICE,0) > 0 "+
					" AND A.CLOSING_FLAG NOT IN ('I','S') "+
					" AND C.FINANCE_NO = '"+m_agreemnt_no+"' "+
					" AND A.EFF_VALDATE >= TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE ,-6),'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" AND A.EFF_VALDATE <= SYSDATE  "+ 
					" GROUP BY C.FINANCE_NO "+
					" ");
				
				if(rs1.next()){
					tot_cash_coll_6months= rs1.getDouble(2);		
				}	
				
				rs1=stmt1.executeQuery(" "+
					" SELECT NVL(SUM(CAPITAL_AMOUNT),0) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A  "+	 					
					" WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
					" AND A.INSTALLMENT_NO = 0  "+
					" AND A.RENTAL_DATE >= TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE ,-6),'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" AND A.RENTAL_DATE <= SYSDATE "+
					" ");
				
				if(rs1.next()){
					down_payment_6_month= rs1.getDouble(1);		
				}	
				
				// added by udara 27-06-2019
				rs1=stmt1.executeQuery(" "+
					" SELECT SUM(B.SETTELED_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
					" WHERE A.FINANCE_NO = '"+m_agreemnt_no+"' "+
					" AND A.INVOICE_NO = B.INVOICE_NO "+
					" AND B.RECEIPT_NO = C.REC_NO "+
					" AND C.EFF_VALDATE >= TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE ,-6),'DD-MM-YYYY'),'DD-MM-YYYY') "+ 
					" AND C.EFF_VALDATE <= SYSDATE "+
					" AND A.INVOICE_TYPE NOT IN ('INV_GENER') "+
					" AND A.INVOICE_TYPE <> 'INSURANCE' "+      // added by udara 03-07-2019
					" AND A.REMARKS <> 'CHARGES - INSURANCE' "+ // added by udara 03-07-2019
					" GROUP BY A.FINANCE_NO "+
					" ");
				
				if(rs1.next()){
					charges_collections = rs1.getDouble(1);		
				}	
				// end by udara 27-06-2019
				
				//tot_cash_coll_6months = tot_cash_coll_6months - down_payment_6_month; // commented by udara 27-06-2019
				tot_cash_coll_6months = tot_cash_coll_6months - down_payment_6_month - charges_collections; // added by udara 27-06-2019
				
				rs1=stmt1.executeQuery(" "+
					" SELECT DISTINCT C.FINANCE_NO, SUM(A.RENTAL_OTER_INVOICE) "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+  
					" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO "+  
					" AND NVL(A.RENTAL_OTER_INVOICE,0) > 0 "+
					" AND A.CLOSING_FLAG NOT IN ('I','S') "+
					" AND C.FINANCE_NO = '"+m_agreemnt_no+"' "+
					" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= SYSDATE "+
					" GROUP BY C.FINANCE_NO "+
					" ");
				
				if(rs1.next()){
					tot_cash_coll = rs1.getDouble(2);		
				}	
				
				
				rs1=stmt1.executeQuery(" "+
					" SELECT SUM(CAPITAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A  "+						
					" WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
					" AND A.INSTALLMENT_NO = 0 "+
					" ");
				
				if(rs1.next()){
					down_payment = rs1.getDouble(1);		
				}
				
				tot_cash_coll = tot_cash_coll - down_payment;
				
				double amount_to_be_paid = m_due_amount_double + m_future_capital_double +(m_future_interest_double - m_rebate_amount_double)-m_unallocated_receipt;
				
				out.println("<BR><BR><BR>");
				out.println("<table border='0' width='100%' class='table'>"); 		
				out.println("<tr><td STYLE='{TEXT-ALIGN:CENTER}' width='*%' ><B>CALCULATIONS FOR THE EARLY SETTLEMENT<B></td></tr>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				
				/*out.println("<p style='text-align:left'>");										
				out.println("<blockquote><font size=2><p style='text-align:justify'>");	
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' >"+m_Letter_date+"</td></tr>");
				out.println("<tr><td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='*%'  >"+m_designation+" </td></tr>");
				out.println("<tr><td width='*%'  >"+m_name+" </td></tr>");//
				out.println("<tr><td width='*%'  >"+m_add1+" </td></tr>");//
				out.println("<tr><td width='*%'  > "+m_add2+"</td></tr>");//
				out.println("<tr><td width='*%'  > "+m_city_desc+"</td></tr>");//
				out.println("</TABLE>");
				out.println("</font></p></blockquote>");
				out.println("<br><br>");*/
				
				//String m_colour_code = "#cce5ff"; // "#ccff99";
				String m_colour_code = "#e6ffff"; 
				
				out.println("<blockquote><font size=2><p style='text-align:justify'>");				
				out.println("<table border='0' width='100%' class='table'> ");	
				
				out.println(" <tr>");
				out.println(" 		<td width = '10%' style='{text-align:center;}'> </td>");
				out.println(" 		<td width = '40%' style='{text-align:left;}'> </td>");
				out.println(" 		<td width = '10%' style='{text-align:center;}'> </td>");
				out.println(" 		<td width = '25%' style='{text-align:left;}'> </td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+">");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Facility No </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;cursor:hand;}' colspan=2 onClick=\"show_transaction_history_new('"+m_client+"','"+m_agreemnt_no+"')\" ><u> "+m_agreemnt_no+"</u></td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Client Name </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+m_name+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Address </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+m_add1+" <BR> "+m_add2+" <BR> "+m_city_desc+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Date </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+m_tDate+"</td>");
				out.println(" </tr>");
				
				// added by udara 05-11-2018
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Contract Status </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+m_contract_status+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Reg. No. </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+m_reg_no+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Rental Date </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2 > "+m_rental_date+"</td>");
				out.println(" </tr>");
				
				
				// added by udara 16-11-2018
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Structured Status </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;cursor:hand;}'  colspan=2 onClick=\"show_rental_schedule('"+m_app_no+"');\" ><u> "+m_structured_status+" </u></td>");
				out.println(" </tr>");
				// end by udara 16-11-2018
				
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Security Details </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				
				if(mm_security_info_count>0)
					out.println(" 		<td style='{text-align:left;cursor:hand;}'  colspan=2 onClick=\"show_run_con_det('"+m_app_no+"');\" ><u> Yes </u></td>");
				else
					out.println(" 		<td style='{text-align:left;cursor:hand;}'  colspan=2 onClick=\"show_run_con_det('"+m_app_no+"');\" ><u> No </u></td>");
				
				out.println(" </tr>");
				
				
				// end by udara 05-11-2018
				
				out.println(" <tr  >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Facility Amount </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				//out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf.format(finance_amount)+"</td>"); // commented by udara 25-06-2019
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf.format(finance_amount-down_payment)+"</td>"); // added by udara 25-06-2019
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Balance Period </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2 > "+m_balance_period+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Period </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+Math.round(period)+"</td>");
				out.println(" </tr>");
				
				// added by udara 14-11-2018
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> No. of elapsed rentals </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+ (Math.round(period) - m_balance_period)+"</td>");
				out.println(" </tr>");
				// end by udara 14-11-2018
				
				out.println(" <tr  >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Interest Rate </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(interest_rate)+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Net Installment </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(net_rental)+"</td>");
				out.println(" </tr>");
				
				// added by udara 05-11-2018
				out.println(" <tr  >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Cash Collection Without Insurance </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(tot_cash_coll)+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Last Six Months Rental Payments Without Insurance </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(tot_cash_coll_6months)+"</td>");
				out.println(" </tr>");
				// end by udara 05-11-2018
				
				
				
				/*
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Interest In Arrears </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(interest_arrears)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> B.T.T </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> 0.00</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Defence Levy </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> 0.00</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				*/
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> ADD Capital Outstanding </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_future_capital_double)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Future Interest Payable </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_future_interest_double)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> LESS Rebate </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				//out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_rebate_amount_double)+"</td>");
				out.println(" 		<td style='{text-align:right;}'>("+nf2.format(m_rebate_amount_double)+")</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> "+nf2.format(m_rebate_rate_double)+"%</td>");
				out.println(" 		<td style='{text-align:left;}'> </td>");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:right;}'> </td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				
				
				// added by udara 14-11-2018
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Ceasing charges </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(ceasing_charges)+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Insurance </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(insurance_charges)+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Visiting </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(visiting_charges)+"</td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Rental & Other Arrears </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				//out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(arrears_excess)+"</td>"); // commented by udara 01-02-2021
				
				// added by udara 01-02-2021
				if ((arrears_excess - m_future_debit_notes) >= 0) {
					out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(arrears_excess - m_future_debit_notes)+"</td>"); 
				}
				else{
					out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(arrears_excess)+"</td>");
				}
				// end by udara 01-02-2021
				
				
				out.println(" </tr>");

				// end by udara 14-11-2018
				
				
				// added by udara 01-02-2021
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Future Moratorium Debit Notes </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:left;}'  colspan=2> "+nf2.format(m_future_debit_notes)+"</td>"); 
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				// end by udara 01-02-2021
				
				
				/*
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> ADD Sundry Charges To Be Paid </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(other_charges)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				*/
				
				// comment above and add below by KD ON 09-02-2015
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> ADD Total Due Amount </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				
				// commented by udara 08-11-2018
				/*
				if(m_add_total_due_amt >0 ){
					out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_due_amount_double-m_unallocated_receipt )+"</td>");
				}else if(m_add_total_due_amt < 0 && m_due_amount_double == 0){
					out.println(" 		<td style='{text-align:right;}'> ("+nf2.format(m_unallocated_receipt )+")</td>");
				}
				*/
				
				// added by udara 08-11-2018
				if(m_add_total_due_amt >= 0){
					out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_add_total_due_amt)+"</td>");
				}
				else{
					out.println(" 		<td style='{text-align:right;}'> ("+nf2.format(m_add_total_due_amt*-1)+") </td>");
				}
				// end by udara 08-11-2018
				
				
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Amount To Be Paid </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(amount_to_be_paid)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Total Overdue Interest </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(odi_double)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> LESS Waive Off Overdue </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				//out.println(" 		<td style='{text-align:right;}'>"+nf2.format(odi_adj_double)+"</td>");
				out.println(" 		<td style='{text-align:right;}'>("+nf2.format(odi_adj_double)+")</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> ADD Overdue Interest </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'>"+nf2.format(odi_net_double)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				//out.println(" 		<td style='{text-align:left;}'> ADD Transfer Fees </td>");
				out.println(" 		<td style='{text-align:left;}'> Termination Charge </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				//out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_sale_price_double)+"</td>"); // commented by udara 14-11-2018
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(termi_double)+"</td>"); // added by udara 14-11-2018 - termination charge
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Total Payable </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_tot_payable_double-m_unallocated_receipt)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Profit On Early Settlement </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_gain_loss_double)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				out.println(" <tr bgcolor="+m_colour_code+" >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Agreed value </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_tot_payable_double)+" </td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				
				// commented by udara 01-02-2021
				/*
				// added by udara 06-01-2021
				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Future Debit Notes </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(m_future_debit_notes)+" </td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				// end by udara 06-01-2021
				*/
				
				// added by udara 15-03-2021
				double interest_for_capital = m_future_capital_double * 0.05;
				//double refinance_amount = m_future_capital_double + interest_for_capital + m_add_total_due_amt; // commented by udara 19-04-2021
				double refinance_amount = m_future_capital_double + interest_for_capital + m_add_total_due_amt + odi_net_double; // added by udara 19-04-2021

				out.println(" <tr >");
				out.println(" 		<td style='{text-align:center;}'> </td>");
				out.println(" 		<td style='{text-align:left;}'> Refinance value </td>");
				out.println(" 		<td style='{text-align:center;}'> :</td>");
				out.println(" 		<td style='{text-align:right;}'> "+nf2.format(refinance_amount)+"</td>");
				out.println(" 		<td width = '15%' style='{text-align:left;}'> </td>");
				out.println(" </tr>");
				// end by udara 15-03-2021
				
				
				
				out.println(" <tr height='45px;'><td colspan=4> </td></tr>");
				
				out.println(" <tr >");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> ....................................... </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> ....................................... </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> Prepared By </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> Checked By </td>");
				out.println(" </tr>");
				
				out.println(" <tr height='30px;'><td colspan=4> </td></tr>");
				
				out.println(" <tr >");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> ....................................... </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> ....................................... </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				//out.println(" 		<td colspan=2 style='{text-align:center;}'> Finance Controler </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> AGM/DGM </td>");
				//out.println(" 		<td colspan=2 style='{text-align:center;}'> Assist.General Manager </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> COO </td>");
				out.println(" </tr>");
				
				out.println(" <tr height='30px;'><td colspan=4> </td></tr>");
				
				out.println(" <tr >");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> ....................................... </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> ....................................... </td>");
				out.println(" </tr>");
				
				out.println(" <tr >");
				//out.println(" 		<td colspan=2 style='{text-align:center;}'> Director / General Manager Director </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> CEO </td>");
				//out.println(" 		<td colspan=2 style='{text-align:center;}'> Chief Executive </td>");
				out.println(" 		<td colspan=2 style='{text-align:center;}'> Director </td>");
				out.println(" </tr>");
				out.println("</TABLE>");
				
				/*
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='*%'  style='{text-align:left;}'><u><b>AGREEMENT NO:"+m_agreemnt_no+"</b></u></td></tr>"); //LEASE 
				out.println("</TABLE><br>");		
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=5><p style='text-align:justify'  >");			
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%'  style='text-align:justify'>");
				out.println("We refer to your recent inquiry on the captioned subject and we give below"+
					" the early termination settlement amount <b>as at "+m_tDate+"</b> .<br>");
				out.println("</td></tr>");									
				out.println("</table>");
				out.println("</font></p></blockquote>");				
				out.println("<blockquote><font size=5><p style='text-align:justify'  >");				
				out.println("<table border='0' width='500' class='table'>"); 
				out.println("<tr><td width='50%'  style='text-align:justify'></td>");
				out.println("<td width='10%'  style='text-align:right'>&nbsp;</td>");
				out.println("<td width='20%'  style='text-align:right'>Rs.</td>");
				out.println("<td width='20%'  style='text-align:right'>Rs.</td>");
				out.println("</tr>");
				out.println("<tr><td style='text-align:justify'>Future Capital</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double)+"</td>");
				out.println("</tr>");
				out.println("<tr><td style='text-align:justify'>Future Interest</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(m_future_interest_double)+"</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("</tr>");	
				out.println("<tr><td style='text-align:justify'>Less: Rebate on Future Interest</td>");
				out.println("<td style='text-align:right'>"+nf2.format(m_rebate_rate_double)+"%</td>");
				out.println("<td style='text-align:right'>"+nf2.format(m_rebate_amount_double)+"</td>");
				out.println("<td style='text-align:right'>"+nf2.format(future_min_rebate_double)+"</td>");
				out.println("</tr>");
				out.println("<tr height='1px'><td style='text-align:justify'></td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>______________</td>");
				out.println("</tr>");
				out.println("<tr><td style='text-align:justify'>Future Capital + Interest</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double + future_min_rebate_double)+"</td>");
				out.println("</tr>");	
				
				out.println("<tr><td style='text-align:justify'>Rentals Outstanding (Capital)   </td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+m_due_amount+"</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr><td style='text-align:justify'>VAT on Future Capital + Interest </td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(termi_vat)+"</td>");
				out.println("<td style='text-align:right'>"+nf2.format(termi_vat + m_due_amount_double)+"</td>");
				out.println("</tr>");
				
				out.println("<tr><td style='text-align:justify'>Default Interest Charges</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(odi_double)+"</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr><td style='text-align:justify'>Less: Rebate on Default Interest Charges</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(odi_adj_double)+"</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr><td style='text-align:justify'>Net Interest Charges</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(odi_net_double)+"</td>");
				out.println("</tr>");
				
				
				out.println("<tr><td style='text-align:justify'>Termination Fee</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(termi_double)+"</td>");
				out.println("</tr>");
				
				out.println("<tr><td style='text-align:justify'>VAT on Transfer Fees</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(charges_vat)+"</td>");
				out.println("<td style='text-align:right'>"+nf2.format(termi_double + charges_vat)+"</td>");
				out.println("</tr>");
				
				out.println("<tr><td style='text-align:justify'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>______________</td>");
				out.println("</tr>");
				out.println("<tr><td style='text-align:justify'>Total Settlement Amount</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double+future_min_rebate_double+m_due_amount_double+odi_net_double+termi_double+charges_vat+termi_vat)+"</td>");
				out.println("</tr>");
				out.println("<tr><td style='text-align:justify'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>&nbsp;</td>");
				out.println("<td style='text-align:right'>==========</td>");
				out.println("</tr>");
				
				out.println("</table><br>");						
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' style='text-align:justify'>");
				out.println("Since you wish to terminate the above mentioned agreement, please settle the balance shown above on or before the settlement date.");
				out.println("</td></tr>");									
				out.println("</table><br>");															
				out.println("</font></p></blockquote>");
				
				out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' style='text-align:justify'>");
				out.println(""+
					" This offer is treated as expired after the settlement date mentioned above."+
					" ");
				out.println("</td></tr>");									
				out.println("</table><br>");															
				out.println("</font></p></blockquote>");
				
				
				out.println("<br><br></td><br><br></td> ");
				
				
				
				out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%'   style='text-align:justify'>Yours faithfully.</td> ");
				out.println("</tr>");	
				out.println("<tr><td width='*%'   style='text-align:justify'><B>UB Finance Company Limited</B></BR></BR></BR></td> ");
				out.println("</tr>");	
				out.println("<tr><td width='*%'   style='text-align:justify'>................................<br><br></td> ");
				out.println("</tr>");	
				out.println("<tr><td width='*%'   style='text-align:justify'>Authorised Signatory.</td> ");
				out.println("</tr>");	
				out.println("</table><br>");
			*/
				out.println("</font></p></blockquote>");
				
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body></html>");
				
				
				
				
			}else{
				out.println("Undefined Letter");
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
