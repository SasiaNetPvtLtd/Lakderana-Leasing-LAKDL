
//Develop by Dineth on 16-04-2009
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
public class LAKDL_AF_PRO_legal_provision_report extends javax.servlet.http.HttpServlet { 
	ServletOutputStream out = null;
	public String m_chksql;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			Connection conn;
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req);
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			
			Statement stmt;
			ResultSet rs;
			 
			stmt=conn.createStatement();
			m_chksql=req.getParameter("chksql");
			String m_sort_column   = "AGGREGATE_AGE";	
			String m_order_by_type = "ASC";
			String m_transact_type="";
			boolean flag=true;
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			  else if(m_chksql.equals("run_report")){
			String m_asat_date=req.getParameter("asat_date");
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");

			
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_LEGAL_PROVISION_PROC(:1,:2); END;");
				callstmt.setString(1,m_asat_date);
				callstmt.setString(2,m_username);
				callstmt.execute();
				callstmt.close();
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Legal Provisioning</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=run_report&asat_date="+m_asat_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
				//out.println(" m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Rpt_Collection_Reminder_Det?chksql=main_page&no_of_days="+m_no_of_due_days+"&as_at_date="+m_as_at_date+"&sort_column='+m_sort_col+'&order_by_type='+m_order_by_type+'';");
				out.println(" window.location.href=m_url;");
				
				out.println("}");
			
			
			out.println("</script>"); 
      out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			rs=stmt.executeQuery(" SELECT FINANCE_NO,"+ //1
			                     " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+ //2
													 " GRANTED_AMOUNT, "+ //3
													 " TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'), "+ //4
                           " TO_CHAR(SETTLEMENT_DATE,'DD-MM-YYYY'), "+ //5
													 " PERIOD, "+ //6
													 " AGGREGATE_AGE, "+ //7
													 " OTHER_CHARGES, "+ //8
                           " LEGAL_BALANCE, "+ //9
													 " LAST_RECEIPT_AMT, "+
													 " NVL(TO_CHAR(LAST_RECEIPT_DATE,'DD-MM-YYYY'),'-'), "+
                           " NVL(REMARKS,'-'), "+
													 " NVL(FOLLOWUPS,'-'), "+
													 " ENT_USER, "+
													 " ENT_DATE, "+
													 " CLIENT_CODE, "+
													 " NVL("+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE),'-'), "+
                           " nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_asat_date+"'),0),  "+//Added BY Sandun 12-06-2009
													 " NVL(VALUATION,0), "+//Added by Dineth on 16-06-2009
													 " NVL(RECEIVABLE,0)   "+ //Added by nuwan de silva 04-12-2009
													 " FROM "+m_schema_name+".AF_PRO_LEGAL_PROVISION_TAB WHERE ENT_USER='"+m_username+"' "+
													 " ORDER BY TRANSACTION_TYPE DESC,"+m_sort_column+" "+m_order_by_type+" ");
			int j=1;				
			boolean more=rs.next();
			if(!more){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Legal Provision Report</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
			out.println("</table>");
			
			}
			if(more){
			/*out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Legal Provision Report</td>");
			out.println("</tr>");
			out.println("</table>"); 
			*/
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			
			out.println("<tr class=\"pdn_txtpos2\">");
			out.println("<td align=\"left\" width='20%'>Format Name</td><td width='5%'>:</td><td width='*%'>Legal Balance Report</td>");
			out.println("</tr>");
			out.println("<tr class=\"pdn_txtpos2\">");
			out.println("<td align=\"left\" width='20%'>Name of RFLE</td><td width='5%'>:</td><td width='*%'>Lakderana Investments Limited</td>");
			out.println("</tr>");
			
			out.println("<tr class=\"pdn_txtpos2\">");
			out.println("<td align=\"left\" width='20%' >As at</td><td width='5%'>:</td><td width='*%'>"+m_asat_date+"</td>");
			out.println("</tr>");
			
			out.println("</table>"); 
			
			
			out.println("<br><br>");
			
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			//out.println("<td width='10%'>Transaction Type</td>");
			out.println("<td width='10%'>Finance No</td>");
			out.println("<td width='10%'>Client Name</td>");
			out.println("<td width='10%'>Amount Granted</td>");
			out.println("<td width='10%'>Date Granted</td>");
			out.println("<td width='10%'>Date Of Settlement</td>");
			out.println("<td width='10%'>Terms Of Repayment Monthly(Months)</td>");
			out.println("<td width='10%' style= cursor:hand; onClick=\"sort_data('AGGREGATE_AGE')\">Aggregate Age After Termination(Months)</td>");
			out.println("<td width='10%'>Other Charges</td>");
			out.println("<td width='10%'>Legal Balance</td>");
			out.println("<td width='10%'>Receivables</td>");
			out.println("<td width='10%'>Amount Of Last Payment</td>");
			out.println("<td width='10%'>Date Of Last Payment</td>");
			out.println("<td width='10%'>Remarks</td>");
			out.println("<td width='10%'>Followups</td>");
			out.println("<td width='10%'>Valuation</td>");//Added by Dineth on 16-06-2009
			out.println("</tr>");
			while(more){
			if(j>1){
			
			
				if(m_transact_type.equals(rs.getString(17))){
					flag=false;
				}
				else{
					flag=true;
				}
			}
			if(flag==true){
				out.println("<tr class='pdn_txtpos2'>");
				out.println("<td colspan=15 style='text-align:left'>"+rs.getString(17)+"</td>");
				out.println("</tr>");
			}
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input >");
			}
			else{
      	out.println("<tr class=tr_input1 >");
			}
			//out.println("<td width='10%'>"+rs.getString(17)+"</td>");
			out.println("<td width='10%' style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\"><u>"+rs.getString(1)+"</u></td>");
			out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(16)+"')\"><u>"+rs.getString(2)+"</u></td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='10%'>"+rs.getString(4)+"</td>");
			out.println("<td width='10%'>"+rs.getString(5)+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>");
			//out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(8)+rs.getDouble(9))+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"); //added by nuwan de silva
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>");
			out.println("<td width='10%'>"+rs.getString(11)+"</td>");
			out.println("<td width='10%'>"+rs.getString(12)+"</td>");
			out.println("<td width='10%'>"+rs.getString(13)+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>");//Added by Dineth on 16-06-2009
			out.println("</tr>");
			
			m_transact_type=rs.getString(17);
			more=rs.next();
			j=j+1;
			}
			out.println("</table>"); 
			}
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 


			
			}
			  else if(m_chksql.equals("view_report")){
			String m_asat_date=req.getParameter("asat_date");
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Legal Provisioning</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=view_report&asat_date="+m_asat_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
				//out.println(" m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Rpt_Collection_Reminder_Det?chksql=main_page&no_of_days="+m_no_of_due_days+"&as_at_date="+m_as_at_date+"&sort_column='+m_sort_col+'&order_by_type='+m_order_by_type+'';");
				out.println(" window.location.href=m_url;");
				
				out.println("}");
			
			
			out.println("</script>"); 
      out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			/*
			rs=stmt.executeQuery(" SELECT FINANCE_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),GRANTED_AMOUNT,TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'), "+
                           " TO_CHAR(SETTLEMENT_DATE,'DD-MM-YYYY'),PERIOD,AGGREGATE_AGE,OTHER_CHARGES, "+
                           " LEGAL_BALANCE,LAST_RECEIPT_AMT,NVL(TO_CHAR(LAST_RECEIPT_DATE,'DD-MM-YYYY'),'-'), "+
                           " NVL(REMARKS,'-'),NVL(FOLLOWUPS,'-'),ENT_USER,ENT_DATE,CLIENT_CODE,NVL("+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE),'-'), "+
													 " nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_asat_date+"'),0),  "+//Added BY Sandun 12-06-2009
                           " NVL(VALUATION,0) "+//Added by Dineth on 16-06-2009
													 " FROM "+m_schema_name+".AF_PRO_LEGAL_PROVISION_TAB WHERE ENT_USER='"+m_username+"' "+
													 " ORDER BY TRANSACTION_TYPE DESC,"+m_sort_column+" "+m_order_by_type+" ");
														
			*/
			
			
			
						rs=stmt.executeQuery(" SELECT FINANCE_NO,"+ //1
			                     " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+ //2
													 " GRANTED_AMOUNT, "+ //3
													 " TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'), "+ //4
                           " TO_CHAR(SETTLEMENT_DATE,'DD-MM-YYYY'), "+ //5
													 " PERIOD, "+ //6
													 " AGGREGATE_AGE, "+ //7
													 " OTHER_CHARGES, "+ //8
                           " LEGAL_BALANCE, "+ //9
													 " LAST_RECEIPT_AMT, "+
													 " NVL(TO_CHAR(LAST_RECEIPT_DATE,'DD-MM-YYYY'),'-'), "+
                           " NVL(REMARKS,'-'), "+
													 " NVL(FOLLOWUPS,'-'), "+
													 " ENT_USER, "+
													 " ENT_DATE, "+
													 " CLIENT_CODE, "+
													 " NVL("+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE),'-'), "+
                           " nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_asat_date+"'),0),  "+//Added BY Sandun 12-06-2009
													 " NVL(VALUATION,0)  , "+//Added by Dineth on 16-06-2009
													 " NVL(RECEIVABLE,0)   "+ //Added by nuwan de silva 04-12-2009
													 " FROM "+m_schema_name+".AF_PRO_LEGAL_PROVISION_TAB WHERE ENT_USER='"+m_username+"' "+
													 " ORDER BY TRANSACTION_TYPE DESC,"+m_sort_column+" "+m_order_by_type+" ");
														
			int j=1;				
			boolean more=rs.next();
			if(!more){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Legal Provision Report</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
			out.println("</table>");
			
			}
			if(more){
			/*out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Legal Provision Report</td>");
			out.println("</tr>");
			out.println("</table>"); 
			*/
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr >");//class=\"pdn_txtpos2\"
			out.println("<td align=\"left\" width='20%'>Format Name</td><td width='5%'>:</td><td width='*%'>Legal Balance Report</td>");
			out.println("</tr>");
			out.println("<tr class=tr_input1>");
			out.println("<td align=\"left\" width='20%'>Name of RFLE</td><td width='5%'>:</td><td width='*%'>Lakderana Investments Limited</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input1>");
			out.println("<td align=\"left\" width='20%' >As at</td><td width='5%'>:</td><td width='*%'>"+m_asat_date+"</td>");
			out.println("</tr>");
			
			out.println("</table>"); 
			
			
			out.println("<br><br>");
			
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=tr_input1 >");
			//out.println("<td width='10%'>Transaction Type</td>");
			out.println("<td width='10%'>Finance No</td>");
			out.println("<td width='10%'>Client Name</td>");
			out.println("<td width='10%'>Amount Granted</td>");
			out.println("<td width='10%'>Date Granted</td>");
			out.println("<td width='10%'>Date Of Settlement</td>");
			out.println("<td width='10%'>Terms Of Repayment Monthly(Months)</td>");
			out.println("<td width='10%' style= cursor:hand; onClick=\"sort_data('AGGREGATE_AGE')\">Aggregate Age After Termination(Months)</td>");
			out.println("<td width='10%'>Other Charges</td>");
			out.println("<td width='10%'>Legal Balance</td>");
			out.println("<td width='10%'>Receivables</td>");
			out.println("<td width='10%'>Amount Of Last Payment</td>");
			out.println("<td width='10%'>Date Of Last Payment</td>");
			out.println("<td width='10%'>Remarks</td>");
			out.println("<td width='10%'>Followups</td>");
			out.println("<td width='10%'>Valuation</td>");
			out.println("</tr>");
			while(more){
			if(j>1){
			if(m_transact_type.equals(rs.getString(17))){
					flag=false;
				}
				else{
					flag=true;
				}
			}
			if(flag==true){
				out.println("<tr class='pdn_txtpos2'>");
				out.println("<td colspan=15 style='text-align:left'>"+rs.getString(17)+"</td>");
				out.println("</tr>");
			}
			
			
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input >");
			}
			else{
      	out.println("<tr class=tr_input1 >");
			}
			//out.println("<td width='10%'>"+rs.getString(17)+"</td>");
			out.println("<td width='10%' style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\"><u>"+rs.getString(1)+"</u></td>");
			out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(16)+"')\"><u>"+rs.getString(2)+"</u></td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='10%'>"+rs.getString(4)+"</td>");
			out.println("<td width='10%'>"+rs.getString(5)+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>");
			//out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(8)+rs.getDouble(9))+"</td>");
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"); //added by nuwan de silva
			out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>");
			out.println("<td width='10%'>"+rs.getString(11)+"</td>");
			out.println("<td width='10%'>"+rs.getString(12)+"</td>");
			out.println("<td width='10%'>"+rs.getString(13)+"</td>");
	    out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>");//Added by Dineth on 16-06-2009
	    out.println("</tr>");
			
			m_transact_type=rs.getString(17);
			more=rs.next();
			j=j+1;
			}
			out.println("</table>"); 
			}
			
			
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 


			
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
			
			

