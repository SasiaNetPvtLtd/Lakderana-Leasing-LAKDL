import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
  

public class LAKDL_FA_RPT_display_accounts_interest_report2 extends javax.servlet.http.HttpServlet { 

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
				String m_int_rate=req.getParameter("int_rate");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Factoring Finance Interest</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_drill_1(m_from_date) {");
				//out.println("alert('dddf');");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_accounts_interest_report2?chksql=DRILL_1&date=\"+m_from_date;");	
				out.println("		window.open(m_url,'popupwin3','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println("}");
				
				out.println("function show_drill_2(m_from_date) {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_accounts_interest_report2?chksql=DRILL_2&date=\"+m_from_date;");	
				out.println("		window.open(m_url,'popupwin3','status=0,menubar=0,scrollbars=1,height=500,width=700');");
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
				out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Factoring Finance Interest for Period of "+m_from_date+" to "+m_to_date+"</td></tr>"); 
				out.println("</table>");  
				out.println("</table>");  
				out.println("<BR>");
				out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_column' align='left'>Applied Interest Rate = "+m_int_rate+"%</TD>");
				out.println("</table>");  
				out.println("<BR>");
				out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_column' align='left'>Tran. Date</TD>");
				out.println("<TD class='txt_report_column' align='right'>Payment</TD>");
				out.println("<TD class='txt_report_column' align='right'>Collections</TD>");
				out.println("<TD class='txt_report_column' align='right'>Balance</TD>");
				out.println("<TD class='txt_report_column' align='right'>Interest Amt</TD>");
				out.println("</tr>"); 
				//---------------------------------------------------------------------
				double m_caa_balance=0;
				double m_caa_int_balance=0;
				double m_pay_amt=0;
				double m_col_amt=0;
				double m_int_op_bal=0;
				double m_int_balance=0;
				
				//rs=stmt.executeQuery("SELECT "+m_schema_name+".FA_FACTORING_FIN_AMT_OP('"+m_from_date+"') + "+m_schema_name+".FA_GET_FIN_AMT_OP_INT('"+m_from_date+"')  FROM DUAL ");
				rs=stmt.executeQuery("SELECT "+m_schema_name+".FA_FACTORING_FIN_AMT_OP_NEW('"+m_from_date+"') FROM DUAL ");
				while(rs.next()){
				m_int_op_bal=rs.getDouble(1);
				}
				
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Open Balance</b></TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ></TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ></TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' >"+nf.format(m_int_op_bal)+"</TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ></TD>");
				out.println("</tr>"); 
				
				m_caa_balance=m_caa_balance+m_int_op_bal;
				
				int m_no_days=0;
				double m_int_rate_day=0;
				
				rs=stmt.executeQuery("SELECT (TO_DATE('"+m_to_date+"','DD-MM-YYYY')-TO_DATE('"+m_from_date+"','DD-MM-YYYY')),("+m_int_rate+")/(100*365) FROM DUAL ");
				if(rs.next()){
				m_no_days=rs.getInt(1);
				m_int_rate_day=rs.getDouble(2);
				}
				
				for(int m_cu_no_date=0;m_cu_no_date<=m_no_days;m_cu_no_date++){
					
					rs1=stmt1.executeQuery(" SELECT "+
					" TO_CHAR(TO_DATE('"+m_from_date+"','DD-MM-YYYY')+"+m_cu_no_date+",'DD-MM-YYYY'),"+
					" NVL("+m_schema_name+".FA_FACTORING_PAY_COLL_AMT(TO_DATE('"+m_from_date+"','DD-MM-YYYY')+"+m_cu_no_date+",'PAY'),0),"+
					" NVL("+m_schema_name+".FA_FACTORING_PAY_COLL_AMT(TO_DATE('"+m_from_date+"','DD-MM-YYYY')+"+m_cu_no_date+",'COL'),0) "+
					" FROM DUAL ");
					
					if(rs1.next()){
						out.println("<tr class=tr_input>");
						out.println("<TD class='txt_report_data' align='left'>"+rs1.getString(1)+"</TD>");
						out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onClick=\"show_drill_1('"+rs1.getString(1)+"')\" >"+nf.format(rs1.getDouble(2))+"</TD>");
						out.println("<TD class='txt_report_data' align='right' style='cursor:hand' onClick=\"show_drill_2('"+rs1.getString(1)+"')\" >"+nf.format(rs1.getDouble(3))+"</TD>");
						
						m_caa_balance=m_caa_balance+(rs1.getDouble(2)-rs1.getDouble(3));
						m_pay_amt=m_pay_amt+rs1.getDouble(2);
						m_col_amt=m_col_amt+rs1.getDouble(3);
						
						m_int_balance=m_caa_balance*m_int_rate_day;
						
						if(m_caa_balance>=0){
							out.println("<TD class='txt_report_data' align='right' style='cursor:hand' >"+nf.format(m_caa_balance)+"</TD>");
						}
						else{
							out.println("<TD class='txt_report_data' align='right' style='cursor:hand' >("+nf.format(m_caa_balance*-1)+")</TD>");
						}
						m_caa_int_balance=m_caa_int_balance+m_int_balance;
						
						if(m_int_balance>=0){
							out.println("<TD class='txt_report_data' align='right' style='cursor:hand' >"+nf.format(m_int_balance)+"</TD>");
						}
						else{
							out.println("<TD class='txt_report_data' align='right' style='cursor:hand' >("+nf.format(m_int_balance)+")</TD>");
						}
						out.println("</tr>"); 
					}
				}

				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</b></TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ><b>"+nf.format(m_pay_amt)+"</b></TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ><b>"+nf.format(m_col_amt)+"</b></TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ><b>"+nf.format(m_caa_balance)+"</b></TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ><b>"+nf.format(m_caa_int_balance)+"</b></TD>");
				out.println("</tr>"); 
	
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr><td width='100%' class='note'></td></tr>"); 
				out.println("</table>"); 
				out.println("</form>");
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				//rs.close();
				//conn.close();
				//out.flush();
				//out.close();
				}
				
				
				else if(m_screen_type.equals("DRILL_1")){
				
				String m_from_date=req.getParameter("date");
				//stmt = conn.createStatement();
				//String m_from_date="";
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Factoring Finance Interest</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
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
				
				out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_column' align='left'>Payment No</TD>");
				out.println("<TD class='txt_report_column' align='left'>Facility Code</TD>");
				out.println("<TD class='txt_report_column' align='right'>Amount</TD>");
				out.println("</tr>"); 
				
				//---------------------------------------------------------------------
				double m_sub_total=0;
				

				rs=stmt.executeQuery(
				"SELECT "+
				" A.FACILITY_NO,A.PAYMENT_CODE,NVL(PAYMENT_AMOUNT,0)  "+
				" FROM   "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
				" WHERE  PAY_STATUS IN('DISB') "+
				" AND    TO_DATE(TO_CHAR(REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    REALISE_STATUS='YES' ");
				
				while(rs.next()){
				
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'>"+rs.getString(1)+"</TD>");
				out.println("<TD class='txt_report_data' align='left'>"+rs.getString(2)+"</TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' >"+nf.format(rs.getDouble(3))+"</b></TD>");
				out.println("</tr>"); 
				m_sub_total=m_sub_total+rs.getDouble(3);
				}
				

				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</TD>");
				out.println("<TD class='txt_report_data' align='left'>&nbsp;</TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ><b>"+nf.format(m_sub_total)+"</b></TD>");
				out.println("</tr>"); 
	
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
				//conn.close();
				out.flush();
				out.close();
				}
				
				else if(m_screen_type.equals("DRILL_2")){
				stmt = conn.createStatement();
				String m_from_date=req.getParameter("date");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Factoring Finance Interest</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
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
				
				out.println("<table align='center' width='100%' class='table' border=1 cellpadding=3 cellspacing=0>"); 
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_column' align='left'>Receipt No</TD>");
				out.println("<TD class='txt_report_column' align='left'>Facility Code</TD>");
				out.println("<TD class='txt_report_column' align='right'>Amount</TD>");
				out.println("</tr>"); 
				
				//---------------------------------------------------------------------
				double m_sub_total=0;
				
				
				rs=stmt.executeQuery("SELECT "+
				" A.RECEIPT_NO,A.FACILITY_NO,NVL(REC_AMOUNT,0)   "+
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
				" WHERE REC_STATUS='Y' "+
				" AND TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
				
				while(rs.next()){
				
				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'>"+rs.getString(1)+"</TD>");
				out.println("<TD class='txt_report_data' align='left'>"+rs.getString(2)+"</TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' >"+nf.format(rs.getDouble(3))+"</b></TD>");
				out.println("</tr>"); 
				m_sub_total=m_sub_total+rs.getDouble(3);
				}
				

				out.println("<tr class=tr_input>");
				out.println("<TD class='txt_report_data' align='left'><b>Total</TD>");
				out.println("<TD class='txt_report_data' align='left'>&nbsp;</TD>");
				out.println("<TD class='txt_report_data' align='right' style='cursor:hand' ><b>"+nf.format(m_sub_total)+"</b></TD>");
				out.println("</tr>"); 
	
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
				//conn.close();
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


