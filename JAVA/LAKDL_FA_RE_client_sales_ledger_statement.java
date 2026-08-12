import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_RE_client_sales_ledger_statement extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1;
	public String m_chksql;
  
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_class_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control","No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			m_chksql=req.getParameter("chksql");

			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if (m_chksql.trim().equals("get_report")){
			
				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				String m_today="";
				
				out.println("<html><head><font 10pt arial><title>Client Sales Ledger</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				out.println("<table border='0' width='100%' class=table>");
				out.println("</table>");
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				rs1.close();
				
				rs1 = stmt1.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
					  " NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			  " NVL(DESIGNATION_PAYMENT,'-') "+//7
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_client_no+"' ");
	
				if(rs1.next()){
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");	
						out.println("</table></blockquote>");
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>CLIENT CODE:"+m_client_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>FACILITY NO:"+m_facility_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>REPORT PERIOD: "+m_date_from+" - "+m_date_to+"</b></td></tr>");	
						out.println("</table></blockquote>");			
				}
				
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center><b>SUMMARY OF SALES LEDGER</b><center></td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center>PRINT DATE/TIME "+m_today+"<center></td></tr>");
				out.println("</table>");			
				
				//--------------------------------------------------------------------------
				double m_active_tot=0;
				double m_inactive_tot=0;
				double m_active_bal=0;
				double m_inactive_bal=0;

				/*rs = stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'OPENABAL','"+m_date_from+"','"+m_date_to+"')) "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_no+"' ");*/
				
				rs = stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'OPENABAL','"+m_date_from+"','"+m_date_to+"')), "+//1
					" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'SET','"+m_date_from+"','"+m_date_to+"')),"+//2
					" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'INR','"+m_date_from+"','"+m_date_to+"')),"+//3
					" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'INA','"+m_date_from+"','"+m_date_to+"')) "+//4
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_no+"' "+
					
					"");
				
				
				/*"SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'SET','"+m_date_from+"','"+m_date_to+"'),),"+//1
				" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INR','"+m_date_from+"','"+m_date_to+"'),),"+//2
				" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INA','"+m_date_from+"','"+m_date_to+"'),),"+//3
				" SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'ABAL','"+m_date_from+"','"+m_date_to+"'),) "+//4
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
				" WHERE "+
				" TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
				" AND A.FACILITY_NO='"+m_facility_no+"' "+
				" AND A.CLIENT_CODE='"+m_client_no+"' ");*/
				
				double m_ac_open=0;
				double m_sett_open=0;
				double m_reassign_open=0;
				double m_adjust_open=0;
				double mm_active_open=0;
				while(rs.next()){
					m_active_bal=rs.getDouble(1);
					m_inactive_bal=0;
					m_active_tot=m_active_tot+m_active_bal;
					m_inactive_tot=m_inactive_tot+m_inactive_bal;
					m_ac_open=rs.getDouble(1);
					m_sett_open=rs.getDouble(2);
					m_reassign_open=rs.getDouble(3);
					m_adjust_open=rs.getDouble(4);
				}
				
				mm_active_open= m_active_bal+m_adjust_open - (m_sett_open+m_reassign_open);//added by ns on 23-08-2011
				
				out.println("<blockquote><table border='0' width='100%' class=table >");
				out.println("<tr><td width='10%' class='factoring-letter-body' align='center'><b>Date</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>Reference No</b></td>");
				//out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Description</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Amount of Invoice Batch</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Settled Amount</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Reassignments</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice Adjustments</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Active Balance</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Inactive Balance</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("<tr><td width='10%' class='factoring-letter-body' align='center'><b>"+m_date_from+"</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>Openning Balance</b></td>");
				//out.println("<td width='10%' class='factoring-letter-body' align='center'>Invoice Batch</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_ac_open)+"</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_sett_open)+"</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_reassign_open)+"</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_adjust_open)+"</b></td>");
				//out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_ac_open-m_sett_open)+"</b></td>"); //comment by ns on 23-08-2011
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(mm_active_open)+"</b></td>");  //added by ns on 23-08-2011
				//out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_ac_open-m_sett_open-m_reassign_open+m_adjust_open)+"</b></td>");// added 2011-08-12 adjust ment not yet hit for the acoount is +m_adjust_open -->credit adjust ments reduce balance -->debit adjust ment increase the balance
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_inactive_bal)+"</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
			
				rs = stmt.executeQuery("SELECT A.BATCH_NO,"+//1
				" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),"+//2
				" SUM(A.TOTAL_BATCH_AMOUNT), "+//3
				" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'SET','"+m_date_from+"','"+m_date_to+"'),"+//4
				" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INR','"+m_date_from+"','"+m_date_to+"'),"+//5
				" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'INA','"+m_date_from+"','"+m_date_to+"'),"+//6
				" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'ABAL','"+m_date_from+"','"+m_date_to+"'),"+//7
				" 0, "+//8
				" "+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(A.BATCH_NO,'ABALI','"+m_date_from+"','"+m_date_to+"')"+//9
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
				" WHERE "+
				" TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
				" AND TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
				" AND A.FACILITY_NO='"+m_facility_no+"' "+
				" AND A.CLIENT_CODE='"+m_client_no+"' "+
				
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE "+
				" ORDER BY A.INVOICE_BATCH_DATE ");
	

				while(rs.next()){
					//m_active_tot=m_active_tot+rs.getDouble(7); //comment by ns on 23-08-2011
					//m_inactive_tot=m_inactive_tot+rs.getDouble(8); //comment by ns on 23-08-2011
					out.println("<tr><td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(1)+"</td>");
					//out.println("<td width='10%' class='factoring-letter-body' align='center'>Invoice Batch</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(7))+"</td>"); 
					//out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(7)-rs.getDouble(5))+"</td>"); // added 2011-08-12
					out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				}
				
				rs = stmt.executeQuery(" SELECT SUM("+m_schema_name+".FA_CLIENT_BATCH_ADJUST_AMT(B.BATCH_NO,'CLOSEABAL','"+m_date_from+"','"+m_date_to+"')) "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_no+"'  "+
			
					"");
				
				while(rs.next()){
					m_active_tot=rs.getDouble(1);
					m_inactive_tot=0;
				}
				
				out.println("<tr><td width='10%' class='factoring-letter-body' align='center'><b>"+m_date_to+"</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>Closing Balance</b></td>");
				//out.println("<td width='10%' class='factoring-letter-body' align='center'>Invoice Batch</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_active_tot)+"</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'><b>"+nf.format(m_inactive_tot)+"</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table></blockquote>");					
				out.println("<blockquote><table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>Any errors or discrepancies should be reported to the <b><i>Orient Factor</i></b> within 14 days of receipt of this statement</td></tr>");
				out.println("</table></blockquote>");	
			}

			else if (m_chksql.trim().equals("get_report_annex_1")){
			
				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				String m_today="";
				
				out.println("<html><head><font 10pt arial><title>Client Sales Ledger</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				out.println("<table border='0' width='100%' class=table>");
				out.println("</table>");
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				rs1.close();
				
				rs1 = stmt1.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(DESIGNATION_PAYMENT,'-') "+//7
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_client_no+"' ");
	
				if(rs1.next()){
						out.println("<blockquote><table border='0' width='100%' class=table >");
						//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");	
						out.println("</table></blockquote>");
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>CLIENT CODE:"+m_client_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>FACILITY NO:"+m_facility_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>REPORT PERIOD: "+m_date_from+" - "+m_date_to+"</b></td></tr>");	
						out.println("</table></blockquote>");			
				}
				
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center><b>NOTIFICATION SCHEDULE - INVOICE BATCH</b></td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center>PRINT DATE/TIME "+m_today+"<center></td></tr>");
				out.println("</table>");			
				
				//--------------------------------------------------------------------------
				out.println("<blockquote><table border='0' width='100%' class=table>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>Dear Sir</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>We acknowledge receipt of under mentioned notification schedule(Invoice Batch)</td></tr>");
				out.println("</table></blockquote>");	
				
				out.println("<blockquote><table border='0' width='100%' class=table >");
				out.println("<td width='30%' class='factoring-letter-body' align='left'><b>Debtor Name</b></td>");
				out.println("<td width='25%' class='factoring-letter-body' align='center'><b>Batch No</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice No</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>Invoice Date</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='right'><b>Invoice Amount</b></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				double m_total=0;
				
				rs = stmt.executeQuery("SELECT A.BATCH_NO,"+//1
				""+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2
				" A.INVOICE_NO,"+//3
				" A.INVOICE_AMOUNT,"+//4
        " TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY') "+//5
  			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
				" AND B.FACILITY_NO='"+m_facility_no+"' "+
				" AND B.CLIENT_CODE='"+m_client_no+"' "+
				" ORDER BY A.BATCH_NO ");
				
				while(rs.next()){
					out.println("<tr><td width='30%' class='factoring-letter-body' align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='25%' class='factoring-letter-body' align='center'>"+rs.getString(1)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					m_total=m_total+rs.getDouble(4);
				}
				out.println("<tr><td width='30%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='25%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='right'><b>"+nf.format(m_total)+"</b></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				out.println("</table></blockquote>");		
				out.println("<blockquote>");
				out.println("<p class='factoring-letter-body'>If you have any queries pertaining to the above please do not hesitate to contact the undersigned.</p>");
				out.println("<p class='factoring-letter-body'><br>Thanking you</p>");
				out.println("<p class='factoring-letter-body'>Yours faithfully</p>");
				out.println("<p class='factoring-letter-body'>Factoring Division of Lakderana Investments Limited</p>");
				out.println("<p class='factoring-letter-body'><br><br><br></p>");
				out.println("<p class='factoring-letter-body'>..........................</p>");
				out.println("<p class='factoring-letter-body'>Authorized Signatory<br>Executive Operations</p>");
				out.println("</table></blockquote>");	
			}
			else if (m_chksql.trim().equals("get_report_annex_2")){
			
				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				String m_today="";
				
				out.println("<html><head><font 10pt arial><title>Client Sales Ledger</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				out.println("<table border='0' width='100%' class=table>");
				out.println("</table>");
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				rs1.close();
				
				rs1 = stmt1.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(DESIGNATION_PAYMENT,'-') "+//7
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_client_no+"' ");
	
				if(rs1.next()){
						out.println("<blockquote><table border='0' width='100%' class=table >");
						//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");	
						out.println("</table></blockquote>");
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>CLIENT CODE:"+m_client_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>FACILITY NO:"+m_facility_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>REPORT PERIOD: "+m_date_from+" - "+m_date_to+"</b></td></tr>");	
						out.println("</table></blockquote>");			
				}
				 
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center><b>INVOICE SETTLEMENT REPORT</b></td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center>PRINT DATE/TIME "+m_today+"<center></td></tr>");
				out.println("</table>");			
				
				//--------------------------------------------------------------------------
				out.println("<blockquote><table border='0' width='100%' class=table>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>Dear Sir</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>We inform to you that the following invoice amounts were settled against your payments.</td></tr>");
				out.println("</table></blockquote>");	
				
				out.println("<blockquote><table border='0' width='90%' class=table >");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Settle Date</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Debtor Name</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Chq. No</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Branch Name</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Chq. Amt</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice No</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice Amt</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Sett. Amt</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Bal. Amt</b></td>");				
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				rs = stmt.executeQuery("SELECT "+
						" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),"+//1
						" A.RECEIPT_NO, "+//2
						" NVL(A.ALLOCATED_AMOUNT,0),"+//3
						" NVL("+m_schema_name+".FA_CLIENT_SETTLE_DATA(A.RECEIPT_NO,'CHQ'),'-'),"+//4
						" NVL("+m_schema_name+".FA_CLIENT_SETTLE_DATA(A.RECEIPT_NO,'BANK'),'-'),"+//5
						" B.INVOICE_NO,"+//6
						" B.DEBTOR_CODE,"+//7
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),"+//8
						" NVL(B.INVOICE_AMOUNT,0),"+//9
						" NVL(B.BALANCE_AMOUNT,0), "+//10
						" NVL(A.RECEIPT_AMOUNT,0), "+//11
						" ("+m_schema_name+".FA_CLIENT_PRE_INV_BAL('"+m_client_no+"','"+m_facility_no+"',A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'))-A.ALLOCATED_AMOUNT) "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+
						" WHERE A.INVOICE_NO=B.INVOICE_SEQ_NO "+
						" AND B.BATCH_NO=C.BATCH_NO "+
						" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND C.FACILITY_NO='"+m_facility_no+"' "+
						" AND C.CLIENT_CODE='"+m_client_no+"' "+
						" AND A.ALLOCATED_AMOUNT>0 "+
						" ORDER BY A.ALLOCATED_DATE ");

				while(rs.next()){
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(1)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(8)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(4)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(5)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(12))+"</td>");				
					out.println("</tr>");
				}
				out.println("</table></blockquote>");		
				out.println("<blockquote>");
				out.println("<p class='factoring-letter-body'>If you have any queries pertaining to the above please do not hesitate to contact the undersigned.</p>");
				out.println("<p class='factoring-letter-body'><br>Thanking you</p>");
				out.println("<p class='factoring-letter-body'>Yours faithfully</p>");
				out.println("<p class='factoring-letter-body'>Factoring Division of Lakderana Investments Limited</p>");
				out.println("<p class='factoring-letter-body'><br><br><br></p>");
				out.println("<p class='factoring-letter-body'>..........................</p>");
				out.println("<p class='factoring-letter-body'>Authorized Signatory<br>Executive Operations</p>");
				out.println("</table></blockquote>");	
			}
			else if (m_chksql.trim().equals("get_report_annex_3")){
			
				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				String m_today="";
				
				out.println("<html><head><font 10pt arial><title>Client Sales Ledger</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				out.println("<table border='0' width='100%' class=table>");
				out.println("</table>");
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				rs1.close();
				
				rs1 = stmt1.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(DESIGNATION_PAYMENT,'-') "+//7
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_client_no+"' ");
	
				if(rs1.next()){
						out.println("<blockquote><table border='0' width='100%' class=table >");
						//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");	
						out.println("</table></blockquote>");
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>CLIENT CODE:"+m_client_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>FACILITY NO:"+m_facility_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>REPORT PERIOD: "+m_date_from+" - "+m_date_to+"</b></td></tr>");	
						out.println("</table></blockquote>");			
				}
				
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center><b>INVOICE ADJUSTMENT REPORT</b></td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center>PRINT DATE/TIME "+m_today+"<center></td></tr>");
				out.println("</table>");			
				
				//--------------------------------------------------------------------------
				out.println("<blockquote><table border='0' width='100%' class=table>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>Dear Sir</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>We inform to you that the following adjustments have been made against your sales ledger.The information as follows</td></tr>");
				out.println("</table></blockquote>");	
				
				out.println("<blockquote><table border='0' width='90%' class=table >");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Adj. Date</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Debtor Name</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice No</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice Date</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Batch No</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice. Amt</b></td>");		
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Adj. Amt</b></td>");	
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Comments</b></td>");	
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				rs = stmt.executeQuery(" SELECT "+
						" TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),"+//1
						" A.DEBTOR_CODE,"+//2
						" A.INVOICE_NO,"+//3
						" B.INVOICE_AMOUNT,"+//4
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'),"+//5
						" A.BATCH_NO,"+//6
						" A.ADJUSTMENT_NO,"+//7
						" NVL(A.ADJUST_AMOUNT,0),"+//8
						" A.ADJUST_TYPE,"+//9
						" A.SOURCE_DOCUMENT,"+//10
						" NVL(A.ADJUSTMENT_COMMENTS,'-'),"+//11
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) "+//12
						" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+
						" WHERE B.BATCH_NO=C.BATCH_NO "+
						" AND A.BATCH_NO=B.BATCH_NO "+
						" AND A.INVOICE_NO=B.INVOICE_NO "+
						" AND A.ADJUST_CATEGORY='INA' "+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND C.FACILITY_NO='"+m_facility_no+"' "+
						" AND C.CLIENT_CODE='"+m_client_no+"' "+
						" ORDER BY A.ADJUST_DATE ");

				while(rs.next()){
					// Modified by Thamali Jayatunga on 2009.10.30
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(1)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(12)+" "+rs.getString(2)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(3)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(5)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(4))+"</td>");	
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(8))+"("+rs.getString(9)+")</td>");		
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(10)+" "+rs.getString(11)+"</td>");	
					out.println("</tr>");
				}
				out.println("</table></blockquote>");		
				out.println("<blockquote>");
				out.println("<p class='factoring-letter-body'>If you have any queries pertaining to the above please do not hesitate to contact the undersigned.</p>");
				out.println("<p class='factoring-letter-body'><br>Thanking you</p>");
				out.println("<p class='factoring-letter-body'>Yours faithfully</p>");
				out.println("<p class='factoring-letter-body'>Factoring Division of Lakderana Investments Limited</p>");
				out.println("<p class='factoring-letter-body'><br><br><br></p>");
				out.println("<p class='factoring-letter-body'>..........................</p>");
				out.println("<p class='factoring-letter-body'>Authorized Signatory<br>Executive Operations</p>");
				out.println("</table></blockquote>");	
			}
			else if (m_chksql.trim().equals("get_report_annex_4")){
			
				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				String m_today="";
				
				out.println("<html><head><font 10pt arial><title>Client Sales Ledger</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				out.println("<table border='0' width='100%' class=table>");
				out.println("</table>");
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				rs1.close();
				
				rs1 = stmt1.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(DESIGNATION_PAYMENT,'-') "+//7
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_client_no+"' ");
	
				if(rs1.next()){
						out.println("<blockquote><table border='0' width='100%' class=table >");
						//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");	
						out.println("</table></blockquote>");
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>CLIENT CODE:"+m_client_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>FACILITY NO:"+m_facility_no+"</b></td></tr>");	
						out.println("<tr><td width='*%' class='factoring-letter-body'><b>REPORT PERIOD: "+m_date_from+" - "+m_date_to+"</b></td></tr>");	
						out.println("</table></blockquote>");			
				}
				
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center><b>LETTER OF REASSIGNED OF THE INVOICES</b></td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'><center>PRINT DATE/TIME "+m_today+"<center></td></tr>");
				out.println("</table>");			
				
				//--------------------------------------------------------------------------
				out.println("<blockquote><table border='0' width='100%' class=table>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>Dear Sir</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>We inform to you that the following unsettled invoices amounts which received under notification schedules have been re-assigned and details given below.</td></tr>");
				out.println("</table></blockquote>");	
				
				out.println("<blockquote><table border='0' width='90%' class=table >");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Adj. Date</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Debtor Name</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice No</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice Date</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Batch No</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Reassign. Amt</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice. Amt</b></td>");				
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Sett. Amt</b></td>");		
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Comments</b></td>");	
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				rs = stmt.executeQuery(" SELECT "+
						" TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),"+//1
						" A.DEBTOR_CODE,"+//2
						" A.INVOICE_NO,"+//3
						" B.INVOICE_AMOUNT,"+//4
						" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'),"+//5
						" A.BATCH_NO,"+//6
						" A.ADJUSTMENT_NO,"+//7
						" "+m_schema_name+".FA_CLIENT_INVICE_AMT_DETAIL(B.BATCH_NO,B.DEBTOR_CODE,B.INVOICE_NO,'INR'),"+//8
						" A.ADJUST_TYPE,"+//9
						" A.SOURCE_DOCUMENT,"+//10
						" NVL(A.ADJUSTMENT_COMMENTS,'-'),"+//11
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//12
						" B.SETTLE_AMOUNT "+//13
						" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_CR_PRO_INVOICE C "+
						" WHERE B.BATCH_NO=C.BATCH_NO "+
						" AND A.BATCH_NO=B.BATCH_NO "+
						" AND A.INVOICE_NO=B.INVOICE_NO "+
						" AND A.ADJUST_CATEGORY='INR' "+
						" AND A.INVOICE_STATUS='Y' "+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
						" AND TO_DATE(TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
						" AND C.FACILITY_NO='"+m_facility_no+"' "+
						" AND C.CLIENT_CODE='"+m_client_no+"' "+
						" ORDER BY A.ADJUST_DATE ");

				while(rs.next()){
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(1)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(12)+" "+rs.getString(2)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(3)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(5)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(4))+"</td>");				
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+nf.format(rs.getDouble(13))+"</td>");	
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(10)+" "+rs.getString(11)+"</td>");	
					out.println("</tr>");
				}
				out.println("</table></blockquote>");		
				out.println("<blockquote>");
				out.println("<p class='factoring-letter-body'>If you have any queries pertaining to the above please do not hesitate to contact the undersigned.</p>");
				out.println("<p class='factoring-letter-body'><br>Thanking you</p>");
				out.println("<p class='factoring-letter-body'>Yours faithfully</p>");
				out.println("<p class='factoring-letter-body'>Factoring Division of Lakderana Investments Limited</p>");
				out.println("<p class='factoring-letter-body'><br><br><br></p>");
				out.println("<p class='factoring-letter-body'>..........................</p>");
				out.println("<p class='factoring-letter-body'>Authorized Signatory<br>Executive Operations</p>");
				out.println("</table></blockquote>");	
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

