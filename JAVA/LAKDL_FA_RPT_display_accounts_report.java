import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
  

public class LAKDL_FA_RPT_display_accounts_report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1;
		public ResultSet rs,rs1;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_header_name=m_sn_methods.header_name.trim();
			conn = m_sn_methods.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			ServletOutputStream out = res.getOutputStream(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
   
			String m_screen_type=req.getParameter("chksql");
		
			String m_schema_name = m_sn_methods.schema_name;

			if(m_screen_type.equals("MAIN")){
			
			String m_from_date=req.getParameter("start_date");
			String m_to_date=req.getParameter("end_date");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Accounts Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function drill_down_1(m_type,m_from_date,m_to_date) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_accounts_report?chksql=DRILL_1&DRILL_TYPE=\"+m_type+\"&start_date=\"+m_from_date+\"&end_date=\"+m_to_date;");	
			out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println("}");
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System "+m_header_name+"</td></tr>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Operation Process - Accounts Report - for Period of "+m_from_date+" to "+m_to_date+"</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
				
		
			
			out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_column' align='left'>DESCRIPTION</TD>");
			out.println("<TD class='txt_report_column' align='right'><b>(Rs.)</b></TD>");
			out.println("</tr>"); 
			///--------------------------------------------------------------------
			
			rs=stmt.executeQuery("SELECT "+
				" CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+
				" NVL(SUM(TRNAMOUNT),0) CAMT  "+
				" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
				" WHERE PROC_DESC='DAILY INTEREST' "+
				" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY CLIENT_CODE ");
			
			double m_normal_int=0;

			while(rs.next()){ 
				m_normal_int=m_normal_int+rs.getDouble(3);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>NORMAL INTEREST AMOUNT</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"NI\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_normal_int)+"</b></TD>");
			out.println("</tr>"); 
			
			///--------------------------------------------------------------------
			
			rs=stmt.executeQuery("SELECT "+
				" CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+
				" NVL(SUM(TRNAMOUNT),0) CAMT  "+
				" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
				" WHERE PROC_DESC='OVERPAY INTEREST' "+
				" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY CLIENT_CODE ");
			
			double m_over_int=0;

			while(rs.next()){
				m_over_int=m_over_int+rs.getDouble(3);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>OVERDUE INTEREST AMOUNT</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"OI\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_over_int)+"</b></TD>");
			out.println("</tr>"); 
			
			///--------------------------------------------------------------------
			
			rs=stmt.executeQuery("SELECT "+
			" A.CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
			" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1)),0) CAMT "+
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
			" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" AND A.FEE_CODE=B.FEE_CODE "+
			" GROUP BY A.CLIENT_CODE");
			
			double m_charges_amt=0;

			while(rs.next()){ 
				m_charges_amt=m_charges_amt+rs.getDouble(3);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>CHARGES AMOUNT</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"CH\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_charges_amt)+"</b></TD>");
			out.println("</tr>"); 
		
			//--------------------------------------------------------------------
			double m_total_income=0;
			m_total_income=m_normal_int+m_over_int+m_charges_amt;
			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<TD class='pdn_txtpos2' align='left'><b>TOTAL INCOME</b></TD>");
			out.println("<TD class='pdn_txtpos2' align='right' ><b>"+nf.format(m_total_income)+"</b></TD>");
			out.println("</tr>"); 			
			
			//---------------------------------------------------------------------
			double m_caa_balance=0;
			rs=stmt.executeQuery(" SELECT "+
			" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1)),0) CAMOUNT "+
			" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
			" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
			
			double m_ca_balance=0;
			while(rs.next()){
				m_caa_balance=rs.getDouble(1);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>CURRENT AC BALANCE</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"CAA\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_caa_balance)+"</b></TD>");
			out.println("</tr>"); 
			
			///--------------------------------------------------------------------
			rs=stmt.executeQuery(" SELECT "+
			" A.CLIENT_CODE, "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
			" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1)),0) CAMOUNT "+
			" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
			" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" GROUP BY A.CLIENT_CODE ");
			
			m_ca_balance=0;
			while(rs.next()){
				m_ca_balance=m_ca_balance+rs.getDouble(3);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>CURRENT AC MOVEMENT</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"CA\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_ca_balance)+"</b></TD>");
			out.println("</tr>"); 

			///--------------------------------------------------------------------
			
			rs=stmt.executeQuery("SELECT "+
			" A.CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
			" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))*(15/100),0) CAMT "+
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
			" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" AND A.FEE_CODE=B.FEE_CODE "+
			" AND B.TAX_APPLICABILITY='Y' "+
			" GROUP BY A.CLIENT_CODE");
			
			double m_vat=0;

			while(rs.next()){
				m_vat=m_vat+rs.getDouble(3);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>VAT AMOUNT</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"VAT\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_vat)+"</b></TD>");
			out.println("</tr>"); 
			
			///--------------------------------------------------------------------
			rs=stmt.executeQuery(" SELECT "+
			" SUM(A.NET_INVOICE_AMOUNT) "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
			" WHERE A.BATCH_NO=B.BATCH_NO "+
			" AND A.INVOICE_STATUS='CONF' "+
			" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");

			double m_invoice_due=0;

			while(rs.next()){
				m_invoice_due=m_invoice_due+rs.getDouble(1);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>SALES LEDGER</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"INV\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_invoice_due)+"</b></TD>");
			out.println("</tr>"); 
			//---------------------------------------------------------------------------

			rs=stmt.executeQuery(" SELECT "+
			" NVL(SUM(A.NET_INVOICE_AMOUNT-"+m_schema_name+".FA_GET_SETTLE_AMOUNTS(A.INVOICE_SEQ_NO,'"+m_to_date+"')),0) "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
			" WHERE A.BATCH_NO=B.BATCH_NO "+
			" AND A.INVOICE_STATUS='CONF' "+
			" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");

			double m_invoice_bal=0;

			while(rs.next()){
				m_invoice_bal=m_invoice_bal+rs.getDouble(1);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>SALES LEDGER-BALANCE</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"INVBAL\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_invoice_bal)+"</b></TD>");
			out.println("</tr>"); 
			//-------------------------------------------------------------------------------
			
			rs=stmt.executeQuery("SELECT NVL(SUM(PAYMENT_AMOUNT),0) "+
		  " FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
		  " WHERE A.PAY_STATUS IN('DISB','PRINT','CONF') "+
			" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");

			double m_payments=0;

			while(rs.next()){
				m_payments=m_payments+rs.getDouble(1);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>TOTAL PAYMENTS</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"TOTPAY\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_payments)+"</b></TD>");
			out.println("</tr>"); 
			//-------------------------------------------------------------------------------
			double m_adj_balance=0;
			rs=stmt.executeQuery(" SELECT "+
			" NVL(SUM(DECODE(ADJUST_TYPE,'DR',ADJUST_AMOUNT,ADJUST_AMOUNT*-1)),0) CAMOUNT "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+
			" WHERE TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" AND ADJUST_CATEGORY='CLA' "+
			" AND INVOICE_STATUS='Y' ");
			
			m_adj_balance=0;
			while(rs.next()){
				m_adj_balance=rs.getDouble(1);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>ADJUSTMENTS</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"ADJ\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_adj_balance)+"</b></TD>");
			out.println("</tr>"); 
			
			///--------------------------------------------------------------------
			double m_coll_balance=0;
			rs=stmt.executeQuery(" SELECT "+
			" NVL(SUM(REC_AMOUNT),0) CAMOUNT "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
			" WHERE TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" AND REC_STATUS='Y' ");
			
			m_coll_balance=0;
			while(rs.next()){
				m_coll_balance=rs.getDouble(1);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>COLLECTIONS</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"COL\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>"+nf.format(m_coll_balance)+"</b></TD>");
			out.println("</tr>"); 
			
			///-------------------------------------------------------------------
			double m_financial_cost=0;
			rs=stmt.executeQuery(" SELECT "+
			" "+m_schema_name+".FA_FACTORING_MONTH_INT_RATE('"+m_from_date+"','"+m_to_date+"') "+
			" FROM DUAL ");
			
			m_financial_cost=0;
			while(rs.next()){
				m_financial_cost=rs.getDouble(1);
			}
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>FINANCIAL COST</b></TD>");
			out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_financial_cost)+"</b></TD>");
			out.println("</tr>"); 
			///--------------------------------------------------------------------
			out.println("<tr class=pdn_txtpos2>");
			out.println("<TD class='pdn_txtpos2' align='left'><b>CONTRIBUTION</b></TD>");
			out.println("<TD class='pdn_txtpos2' align='right'><b>"+nf.format(m_total_income-m_financial_cost)+"</b></TD>");
			out.println("</tr>"); 
			
			///--------------------------------------------------------------------
			
			out.println("<tr class=tr_input>");
			out.println("<TD class='txt_report_data' align='left'><b>DAILY LOAN BALANCE DISTRIBUTION</b></TD>");
			out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\"DC\",\""+m_from_date+"\",\""+m_to_date+"\")'><b>DISPLAY</b></TD>");
			out.println("</tr>"); 
			
			///----------------------------------------------------------------
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			conn.close();
			out.flush();
			out.close();
			}
			else if(m_screen_type.equals("DRILL_1")){
			
			String m_drill_type=req.getParameter("DRILL_TYPE");
			String m_from_date=req.getParameter("start_date");
			String m_to_date=req.getParameter("end_date");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Accounts Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function drill_down_1(m_client,m_type,m_from_date,m_to_date) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_accounts_report?chksql=DRILL_2&CLIENT_CODE=\"+m_client+\"&DRILL_TYPE=\"+m_type+\"&start_date=\"+m_from_date+\"&end_date=\"+m_to_date;");	
			out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println("}");
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System "+m_header_name+"</td></tr>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("</table>");  
			out.println("</BR>");
			
			if(m_drill_type.equals("CAA")){
				rs=stmt.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
					" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1)),0) CAMOUNT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
					" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" GROUP BY A.CLIENT_CODE ");
				
				double m_ca_balance1=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CURRENT ACCOUNT</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>CURRENT AC BALANCE (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"CAA\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_ca_balance1=m_ca_balance1+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_ca_balance1)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("CA")){
				rs=stmt.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
					" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1)),0) CAMOUNT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
					" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" GROUP BY A.CLIENT_CODE ");
				
				double m_ca_balance2=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CURRENT ACCOUNT MOVEMENT</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>CURRENT AC MOVEMENT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"CA\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_ca_balance2=m_ca_balance2+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_ca_balance2)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("NI")){
				rs=stmt.executeQuery("SELECT "+
					" CLIENT_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+
					" NVL(SUM(TRNAMOUNT),0) CAMT  "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" GROUP BY CLIENT_CODE ");
				
				double m_normal_int=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - NORMAL INTEREST</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>NORMAL INTEREST AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"NI\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_normal_int=m_normal_int+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_normal_int)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("OI")){
				rs=stmt.executeQuery("SELECT "+
					" CLIENT_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+
					" NVL(SUM(TRNAMOUNT),0) CAMT  "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" GROUP BY CLIENT_CODE ");
				
				double m_over_int=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - OVERDUE INTEREST</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>OVERDUE INTEREST AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"OI\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_over_int=m_over_int+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_over_int)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("CH")){
			
				rs=stmt.executeQuery("SELECT "+
				" B.FEE_CODE,"+
				" B.FEE_DESC, "+
				" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1)),0) CAMT "+
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
				" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.FEE_CODE=B.FEE_CODE "+
				" GROUP BY B.FEE_CODE,B.FEE_DESC");
				
				double m_charges_amt=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CHARGE AMOUNT</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>FEE DESCRIPTION</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>CHARGES AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"CHF\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_charges_amt=m_charges_amt+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_charges_amt)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<BR><BR>"); 
				
				rs=stmt.executeQuery("SELECT "+
				" A.CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
				" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1)),0) CAMT "+
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
				" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.FEE_CODE=B.FEE_CODE "+
				" GROUP BY A.CLIENT_CODE");
				
				m_charges_amt=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CHARGE AMOUNT</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>CHARGES AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"CH\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_charges_amt=m_charges_amt+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_charges_amt)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("VAT")){
				rs=stmt.executeQuery("SELECT "+
				" A.CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
				" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1))*(15/100),0) CAMT "+
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
				" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.FEE_CODE=B.FEE_CODE "+
				" AND B.TAX_APPLICABILITY='Y' "+
				" GROUP BY A.CLIENT_CODE");
				
				double m_vat=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - VAT DETAIL</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>VAT AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"VAT\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_vat=m_vat+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_vat)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("INV")){
			
				rs=stmt.executeQuery(" SELECT "+
				" B.CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+
				" SUM(A.NET_INVOICE_AMOUNT) "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.INVOICE_STATUS='CONF' "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY B.CLIENT_CODE");
				
				double m_invoice_due=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - SALES LEDGER</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>SALES LEDGER (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"INV\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_invoice_due=m_invoice_due+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_invoice_due)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("INVBAL")){
			
				rs=stmt.executeQuery(" SELECT "+
				" B.CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+
				" NVL(SUM(A.NET_INVOICE_AMOUNT-"+m_schema_name+".FA_GET_SETTLE_AMOUNTS(A.INVOICE_SEQ_NO,'"+m_to_date+"')),0) "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.INVOICE_STATUS='CONF' "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY B.CLIENT_CODE");
				
				double m_invoice_due=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - SALES LEDGER - BALANCE</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>SALES LEDGER-BALANCE (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"INVBAL\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_invoice_due=m_invoice_due+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_invoice_due)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("TOTPAY")){

				rs=stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
				" SUM(A.PAYMENT_AMOUNT) "+
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
				" WHERE A.PAY_STATUS IN('DISB','PRINT','CONF') "+
				" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY A.CLIENT_CODE");
				
				double m_invoice_due=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - PAYMENTS</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>PAYMENTS (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"TOTPAY\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_invoice_due=m_invoice_due+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_invoice_due)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("ADJ")){

				rs=stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
				" NVL(SUM(DECODE(ADJUST_TYPE,'DR',ADJUST_AMOUNT,ADJUST_AMOUNT*-1)),0) "+
				" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A "+
				" WHERE TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND ADJUST_CATEGORY='CLA' "+
				" AND INVOICE_STATUS='Y' "+
				" GROUP BY A.CLIENT_CODE");
				
				double m_invoice_due=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - ADJUSTMENTS</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>ADJUSTMENTS (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"ADJ\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_invoice_due=m_invoice_due+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_invoice_due)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("COL")){
	
				rs=stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
				" SUM(A.REC_AMOUNT) "+
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
				" WHERE "+
				" TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.REC_STATUS='Y' "+
				" GROUP BY A.CLIENT_CODE");
				
				double m_coll_due=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - COLLECTIONS</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT NAME</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onclick='drill_down_1(\""+rs.getString(1)+"\",\"COL\",\""+m_from_date+"\",\""+m_to_date+"\")'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_coll_due=m_coll_due+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_coll_due)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			//-------------------------------------------------------
			if(m_drill_type.equals("DC")){
				
				rs=stmt.executeQuery(" SELECT "+
				" NVL(SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1)),0) CAMOUNT "+
				" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
				" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
				
				double m_ca_balance=0;
				while(rs.next()){
					m_ca_balance=rs.getDouble(1);
				}
				
				rs=stmt.executeQuery(" SELECT TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),SUM(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1)) "+
				" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
				" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" GROUP BY A.TRNDATE "+
				" ORDER BY A.TRNDATE ");

				double m_coll_due=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - DAILY LOAN BALANCE DISTRIBUTION</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
			  m_coll_due=m_coll_due+m_ca_balance;
				
				while(rs.next()){
					m_coll_due=m_coll_due+rs.getDouble(2);
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue >"+rs.getString(1)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand'>"+nf.format(m_coll_due)+"</TD>");
					out.println("</tr >"); 
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_coll_due)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}

			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			conn.close();
			out.flush();
			out.close();
			}
			else if(m_screen_type.equals("DRILL_2")){
			
			String m_drill_type=req.getParameter("DRILL_TYPE");
			String m_from_date=req.getParameter("start_date");
			String m_to_date=req.getParameter("end_date");
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_CODE");//Added by Dineth on 01-04-2009
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Accounts Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function drill_down_1(m_client,m_type,m_from_date,m_to_date) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_accounts_report?chksql=DRILL_2&CLIENT_CODE=\"+m_client+\"&DRILL_TYPE=\"+m_type+\"&start_date=\"+m_from_date+\"&end_date=\"+m_to_date;");	
			out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println("}");
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System "+m_header_name+"</td></tr>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("</table>");  
			out.println("</BR>");
			
			if(m_drill_type.equals("CAA")){
			
				rs=stmt.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+//1
					" NVL(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1),0), "+//2
					" A.DRCR_STATUS, "+//3
					" TO_CHAR(A.TRNDATE,'DD-MM-YYYY'), "+//4
       		" A.PROC_DESC, "+//5
					" A.FACILITY_CODE "+//6
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
					" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_CODE='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
					" ORDER BY A.TRNDATE ");
				
				double m_ca_balance22=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CURRENT ACCOUNT - CLIENT</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='70%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>TRANACTION DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>DESCRIPTION</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>TYPE</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='right'>AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(6)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(3)+"</TD>");
					if(rs.getDouble(2)<0){
						out.println("<TD class='txt_report_data' align='right' >("+nf.format(rs.getDouble(2)*-1)+")</TD>");
					}
					else{
						out.println("<TD class='txt_report_data' align='right' >"+nf.format(rs.getDouble(2))+"</TD>");
					}
					out.println("</tr >"); 
					m_ca_balance22=m_ca_balance22+rs.getDouble(2);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left' ></TD>");
				out.println("<TD class='txt_report_data' align='left' ></TD>");
				out.println("<TD class='txt_report_data' align='left' ></TD>");
				out.println("<TD class='txt_report_data' align='left' >Total</TD>");
				if(m_ca_balance22<0){
					out.println("<TD class='txt_report_data' align='right' >("+nf.format(m_ca_balance22*-1)+")</TD>");
				}
				else{
					out.println("<TD class='txt_report_data' align='right' >"+nf.format(m_ca_balance22)+"</TD>");
				}
				out.println("</tr >"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("CA")){
			
				rs=stmt.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+//1
					" NVL(DECODE(A.DRCR_STATUS,'DR',A.TRNAMOUNT,A.TRNAMOUNT*-1),0), "+//2
					" A.DRCR_STATUS, "+//3
					" TO_CHAR(A.TRNDATE,'DD-MM-YYYY'), "+//4
       		" A.PROC_DESC, "+//5
					" A.FACILITY_CODE "+//6
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE A "+
					" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_CODE='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
					" ORDER BY A.TRNDATE ");
				
				double m_ca_balance21=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CURRENT ACCOUNT MOVEMENT- CLIENT</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='70%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>TRANACTION DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>DESCRIPTION</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>TYPE</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='right'>AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(6)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(3)+"</TD>");
					if(rs.getDouble(2)<0){
						out.println("<TD class='txt_report_data' align='right' >("+nf.format(rs.getDouble(2)*-1)+")</TD>");
					}
					else{
						out.println("<TD class='txt_report_data' align='right' >"+nf.format(rs.getDouble(2))+"</TD>");
					}
					out.println("</tr >"); 
					m_ca_balance21=m_ca_balance21+rs.getDouble(2);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left' ></TD>");
				out.println("<TD class='txt_report_data' align='left' ></TD>");
				out.println("<TD class='txt_report_data' align='left' ></TD>");
				out.println("<TD class='txt_report_data' align='left' >Total</TD>");
				if(m_ca_balance21<0){
					out.println("<TD class='txt_report_data' align='right' >("+nf.format(m_ca_balance21*-1)+")</TD>");
				}
				else{
					out.println("<TD class='txt_report_data' align='right' >"+nf.format(m_ca_balance21)+"</TD>");
				}
				out.println("</tr >"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("NI")){
			
				rs=stmt.executeQuery("SELECT "+
					" CLIENT_CODE,"+
					" NVL(TRNAMOUNT,0), "+
					" TO_CHAR(TRNDATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
					" ORDER BY TRNDATE ");
				
				double m_normal_int=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - NORMAL INTEREST</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>TRAN DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>INTEREST AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(3)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(2))+"</TD>");
					out.println("</tr >"); 
					m_normal_int=m_normal_int+rs.getDouble(2);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_normal_int)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("OI")){
				rs=stmt.executeQuery("SELECT "+
					" CLIENT_CODE,"+
					" TO_CHAR(TRNDATE,'DD-MM-YYYY'),"+
					" NVL(TRNAMOUNT,0)  "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					" WHERE PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
					" ORDER BY TRNDATE ");
				
				double m_over_int=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - OVERDUE INTEREST</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>TRAN DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='right'>INTEREST AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_over_int=m_over_int+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_over_int)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("CHF")){
			
				rs=stmt.executeQuery("SELECT "+
				" A.CLIENT_CODE,"+//1
				" A.DRCR_STATUS, "+//2
				" NVL(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1),0),"+//3
				" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+//4
				" NVL(A.FEE_DESC,'-'), "+//5
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//6
				" A.FACILITY_NO,"+//7
				" A.CHARGES_REF_NO "+//8
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
				" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.FEE_CODE=B.FEE_CODE "+
				" AND A.FEE_CODE='"+m_client_code+"' "+
				" ORDER BY A.EFF_DATE ");
				
				double m_charges_amt=0;
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CHARGES DETAIL</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='70%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='15%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='20%' class='txt_report_column' align='left'>CLIENT NAME</td>");
				out.println("<td width='15%' class='txt_report_column' align='left'>TRAN DATE</td>");
				out.println("<td width='20%' class='txt_report_column' align='right'>CHARGES AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(6)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onClick=\"show_charges_details('"+rs.getString(8)+"')\">"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_charges_amt=m_charges_amt+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_charges_amt)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			}
			if(m_drill_type.equals("CH")){
			
				rs=stmt.executeQuery("SELECT "+
				" A.CLIENT_CODE,"+
				" A.DRCR_STATUS, "+
				" NVL(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1),0),"+
				" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
				" NVL(A.FEE_DESC,'-') "+
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
				" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.FEE_CODE=B.FEE_CODE "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
				" ORDER BY A.EFF_DATE ");
				
				double m_charges_amt=0;
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CHARGE DETAIL</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='15%' class='txt_report_column' align='left'>TRAN DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>DESCRIPTION</td>");
				out.println("<td width='15%' class='txt_report_column' align='left'>TYPE</td>");
				out.println("<td width='20%' class='txt_report_column' align='right'>CHARGES AMOUNT (Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_charges_amt=m_charges_amt+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_charges_amt)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("VAT")){
			
				rs=stmt.executeQuery("SELECT "+
				" A.CLIENT_CODE,"+
				" A.DRCR_STATUS, "+
				" NVL(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1),0),"+
				" NVL(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1),0)*(15/100),"+
				" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
				" NVL(A.FEE_DESC,'-') "+
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A,"+m_schema_name+".FA_CO_MAS_FEES B  "+
				" WHERE TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.FEE_CODE=B.FEE_CODE "+
				" AND B.TAX_APPLICABILITY='Y' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
				" ORDER BY A.EFF_DATE ");
				
				double m_charges_amt=0;
				double m_vat=0;
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - CHARGES VAT DETAIL</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='50%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='15%' class='txt_report_column' align='left'>TRAN DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>DESCRIPTION</td>");
				out.println("<td width='20%' class='txt_report_column' align='right'>CHARGES AMOUNT (Rs.)</td>"); 
				out.println("<td width='20%' class='txt_report_column' align='right'>VAT AMOUNT(Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(6)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' >"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</TD>");
					out.println("</tr >"); 
					m_charges_amt=m_charges_amt+rs.getDouble(3);
					m_vat=m_vat+rs.getDouble(4);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_charges_amt)+"</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_vat)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
			}
			if(m_drill_type.equals("INV")){
				
				double m_temp1=0;
				
				rs=stmt.executeQuery(" SELECT "+
				" B.CLIENT_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2
				" A.NET_INVOICE_AMOUNT, "+//3
				" B.FACILITY_NO, "+//4
				" B.BATCH_NO,"+//5
				" A.DEBTOR_CODE, "+//6
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//7
				" A.INVOICE_NO "+//8
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.INVOICE_STATUS='CONF' "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND B.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.FACILITY_NO='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
				" ORDER BY A.DEBTOR_CODE ");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - SALES LEDGER</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='75%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='left'>BATCH NO</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>DEBTOR NAME</td>");
				out.println("<td width='10%' class='txt_report_column' align='right'>INVOICE NO</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='right'>INVOICE AMOUNT(Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_facility('"+rs.getString(4)+"')\">"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs.getString(5)+"')\">"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_client('"+rs.getString(6)+"')\">"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_invoice_details('"+rs.getString(6)+"','"+rs.getString(8)+"')\">"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' >"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_temp1=m_temp1+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_temp1)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 

			}
			if(m_drill_type.equals("INVBAL")){
				
				double m_temp1=0;
				double m_temp2=0;
				
				rs=stmt.executeQuery(" SELECT "+
				" B.CLIENT_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//2
				" A.NET_INVOICE_AMOUNT, "+//3
				" B.FACILITY_NO, "+//4
				" B.BATCH_NO,"+//5
				" A.DEBTOR_CODE, "+//6
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//7
				" A.INVOICE_NO, "+//8
				" NVL("+m_schema_name+".FA_GET_SETTLE_AMOUNTS(A.INVOICE_SEQ_NO,'"+m_to_date+"'),0) "+//9
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.INVOICE_STATUS='CONF' "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND B.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.FACILITY_NO='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
				" ORDER BY A.DEBTOR_CODE ");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - SALES LEDGER-BALANCE</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='75%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='left'>BATCH NO</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>DEBTOR NAME</td>");
				out.println("<td width='10%' class='txt_report_column' align='right'>INVOICE NO</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='right'>INVOICE AMOUNT(Rs.)</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='right'>SETTLE AMOUNT(Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_facility('"+rs.getString(4)+"')\">"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs.getString(5)+"')\">"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_client('"+rs.getString(6)+"')\">"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_invoice_details('"+rs.getString(6)+"','"+rs.getString(8)+"')\">"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' >"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("<TD class='txt_report_data' align='right' >"+nf.format(rs.getDouble(9))+"</TD>");
					out.println("</tr >"); 
					m_temp1=m_temp1+rs.getDouble(3);
					m_temp2=m_temp2+rs.getDouble(9);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_temp1)+"</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_temp2)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			}
			if(m_drill_type.equals("TOTPAY")){
				
				double m_temp1=0;
	
				rs=stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
				" A.PAYMENT_AMOUNT, "+//3
				" A.FACILITY_NO, "+//4  A.PAY_DATE,A.CHEQUE_NO
				" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),"+//5
				" A.CHEQUE_NO, "+//6
				" A.PAYMENT_CODE "+//7
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
				" WHERE A.PAY_STATUS IN('DISB','PRINT','CONF') "+
				" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
				" ORDER BY A.PAY_DATE ");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - PAYMENTS</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='75%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='left'>PAYMENT DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CHEQUE NO</td>");
				out.println("<td width='15%' class='txt_report_column' align='right'>PAYMENT(Rs.)</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_facility('"+rs.getString(4)+"')\">"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(6)+"</TD>");
					out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onClick=\"show_payment_details('"+rs.getString(7)+"')\">"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("</tr >"); 
					m_temp1=m_temp1+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_temp1)+"</b></TD>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 

			}
			if(m_drill_type.equals("ADJ")){
				
				double m_temp1=0;
				
				rs=stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
				" A.ADJUST_AMOUNT, "+//3
				" A.FACILITY_NO, "+//4
				" TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),"+//5
				" A.SOURCE_DOCUMENT, "+//6
				" A.ADJUSTMENT_COMMENTS, "+//7
				" A.ADJUST_TYPE "+//8
				" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A "+
				" WHERE "+
				" TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND ADJUST_CATEGORY='CLA' "+
				" AND INVOICE_STATUS='Y' "+
				" AND CLIENT_CODE='"+m_client_code+"' "+
				" AND FACILITY_NO='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
				" ORDER BY A.ADJUST_DATE ");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - PAYMENTS</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='75%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='left'>ADJUSTMENT DATE</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>ADJUST TYPE</td>");
				out.println("<td width='15%' class='txt_report_column' align='right'>AMOUNT (Rs.)</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='right'>SOURCE DOCUMENT</td>"); 
				out.println("<td width='15%' class='txt_report_column' align='right'>COMMENTS</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_facility('"+rs.getString(4)+"')\">"+rs.getString(4)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(6)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(7)+"</TD>");
					out.println("</tr >"); 
					m_temp1=m_temp1+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_temp1)+"</b></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 

			}
			if(m_drill_type.equals("COL")){
				
				double m_temp1=0;
				double m_temp2=0;
				
				rs=stmt.executeQuery(" SELECT "+
				" A.CLIENT_CODE,"+//1
				" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE)), "+//2
				" A.REC_AMOUNT, "+//3
				" A.BALANCE_AMOUNT,"+//4
				" A.FACILITY_NO, "+//5
				" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),"+//6
				" TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),"+//7
				" A.RECEIPT_NO, "+//8
				" DECODE(A.SETTLE_MODE,'CHEQUE',A.SETTLE_MODE || '-' || A.CHEQUE_NO,A.SETTLE_MODE) "+//9
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
				" WHERE "+
				" TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.REC_STATUS='Y' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+//Added by Dineth on 01-04-2009
				" ORDER BY A.RECON_DATE ");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>OPERATION PROCESS - ACCOUNTS REPORT - COLLECTIONS</td></tr>"); 
				out.println("</table>"); 
				out.println("<BR><BR>");
				out.println("<table align='center' width='75%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=pdn_txtpos2>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>FACILITY NO</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='left'>RECEIPT NO</td>"); 
				out.println("<td width='25%' class='txt_report_column' align='left'>CLIENT/DEBTOR NAME</td>");
				out.println("<td width='10%' class='txt_report_column' align='right'>RECEIPT AMOUNT (Rs.)</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='right'>RECEIPT DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='right'>REALISE DATE</td>"); 
				out.println("<td width='10%' class='txt_report_column' align='right'>SETTLE MODE</td>"); 
				out.println("</tr >"); 
	
				while(rs.next()){
					out.println("<tr class=tr_input>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_facility('"+rs.getString(5)+"')\">"+rs.getString(5)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' style='cursor:hand' onClick=\"show_receipt_details('"+rs.getString(8)+"')\">"+rs.getString(8)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(2)+"</TD>");
					out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(6)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(7)+"</TD>");
					out.println("<TD class='txt_report_data' align='left' >"+rs.getString(9)+"</TD>");
					out.println("</tr >"); 
					m_temp1=m_temp1+rs.getDouble(3);
					m_temp2=m_temp2+rs.getDouble(3);
				}
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right'><b>"+nf.format(m_temp1)+"</b></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("<TD class='txt_report_data' align='left'></TD>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 

			}
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
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


