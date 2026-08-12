import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;



public class LAKDL_FA_OP_Invoice_Second_Reminder_Letter extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt1,stmt2;
	java.text.NumberFormat nf;
	
	public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_pre_reminder_date="-",m_first_reminder_date="-";
			double m_chq_amount=0;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			String m_client_no=req.getParameter("client_no");		
			String m_debtor_code=req.getParameter("debtor_code");		
			String m_facility_no=req.getParameter("facility_no");
			String m_batch_no=req.getParameter("batch_no");
			String m_invoice_seq=req.getParameter("invoice_seq");
			String m_from_date=req.getParameter("from_date");
			String m_to_date=req.getParameter("to_date");
			
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			
			if (m_chksql.trim().equals("main_page")) {
				out.println("<html><head>"); 
				out.println("<title>2nd Reminder</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");				
				out.println("<body leftmargin='0' topmargin='0' class=body>");	
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");	
				
				String m_Letter_date="";
				rs1 = stmt1.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");											
				if(rs1.next()){
					m_Letter_date=rs1.getString(1);
				}
				
				String m_deadline_date="";
				rs1 = stmt1.executeQuery("SELECT TO_CHAR(SYSDATE+7,'DD-MM-YYYY') FROM DUAL ");								
				if(rs1.next()){
					m_deadline_date=rs1.getString(1);
				}
				
				rs2 = stmt2.executeQuery (" SELECT "+
					" CLIENT_CODE, "+
					" NVL(FULL_NAME,'-') "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					" WHERE CLIENT_CODE='"+m_client_no+"' ");
				String m_client_name="";
				
				if(rs2.next()){
					m_client_name = rs2.getString(2);
				}
				
				out.println("<p style='text-align:left'>");										
				out.println("<br><br><br><br><br><br><br>");
				out.println("<table border='0' width='100%' class='table'>"); 		
				/*out.println("<tr><td width='*%' class='factoring-letter-body'>FACTORING DIVISION OF</td></tr>");
		  	out.println("<tr><td width='*%' class='factoring-letter-body'>ORIENT FINANCIAL SERVICES CORPORATION LTD</td></tr>");
			  out.println("<tr><td width='*%' class='factoring-letter-body'>525, UNION PLACE</td></tr>");
			  out.println("<tr><td width='*%' class='factoring-letter-body'>COLOMBO 02.</td></tr>");*/
				out.println("<tr><td width='*%' class='factoring-letter-body'>Co.Reg.No. PB75</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>"+m_Letter_date+"</td></tr>");
				out.println("</TABLE>");
				out.println("<br><br><br><br>");
				out.println("<table border='0' width='100%' class='table'>"); 	
				
				rs1 = stmt1.executeQuery (	" SELECT "+
					" NVL(A.CLIENT_CODE,'-'), "+//1
					" NVL(UPPER(A.FULL_NAME),' '), "+//2
					" NVL(UPPER(A.REGISTERED_ADDRESS1),' '), "+//3
					" NVL(UPPER(A.REGISTERED_ADDRESS2),' '), "+//4
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ')), "+//5
					" NVL(UPPER(B.CONTACT_PERSON),' '),"+//6
					" NVL(UPPER(B.DESIGNATION_PAYMENT),' '), "+//7
					" A.CLIENT_TYPE "+//8
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B "+
					" WHERE B.DEBTOR_CODE=A.CLIENT_CODE "+
					" AND B.FACILITY_NO='"+m_facility_no+"' "+
					" AND B.CLIENT_CODE='"+m_client_no+"' "+
					" AND A.CLIENT_CODE='"+m_debtor_code+"' ");
				
				while(rs1.next()){
					if(rs1.getString(8).equals("C")){
						//out.println("<tr><td width='*%' class='factoring-letter-body' >"+m_cont_person+" </td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body' >"+rs1.getString(7)+" </td></tr>");
					}
					out.println("<tr><td width='*%' class='factoring-letter-body' >"+rs1.getString(2)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body' >"+rs1.getString(3)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body' >"+rs1.getString(4)+"</td></tr>");
					out.println("<tr><td width='*%' class='factoring-letter-body' >"+rs1.getString(5)+"</td></tr>");
				}
				out.println("</TABLE>");
				out.println("</p>");
				out.println("<br><br>");
				out.println("<p style='text-align:justify'>");				
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b><u>2ND REMINDER</u></b></td></tr>");
				out.println("</TABLE><br>");		
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:left;}'><br>Dear Sir,</td></tr>");
				out.println("</TABLE><br>");		
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='50%' class='factoring-letter-body' style='{text-align:left;}'>Supplier code :- "+m_client_no+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr ><td width='50%' class='factoring-letter-body' style='{text-align:left;}'>Supplier Name :- "+m_client_name+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr ><td width='50%' class='factoring-letter-body' style='{text-align:left;}'>Debtor code :- "+m_debtor_code+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</TABLE>");		
				out.println("</p>");
				
				/*rs2 = stmt2.executeQuery ("SELECT NVL(SUM(A.BALANCE_AMOUNT),0) "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B  "+
					" WHERE B.FACILITY_NO='"+m_facility_no+"'"+
					" AND A.DEBTOR_CODE='"+m_debtor_code+"' "+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND B.CLIENT_CODE='"+m_client_no+"' "+
					" AND A.BALANCE_AMOUNT>0 "+
					" AND A.INVOICE_SEQ_NO='"+m_invoice_seq+"'");*/
				
				
				rs2 = stmt2.executeQuery ("SELECT NVL(SUM(A.BALANCE_AMOUNT),0) "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B  "+
					" WHERE B.FACILITY_NO='"+m_facility_no+"'"+
					" AND A.DEBTOR_CODE='"+m_debtor_code+"' "+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND B.CLIENT_CODE='"+m_client_no+"' "+
					" AND A.BALANCE_AMOUNT>0 "+
					" AND A.INVOICE_STATUS='CONF' "+
					//" AND A.INVOICE_SEQ_NO NOT IN (SELECT INVOICE_SEQ_NO FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
 					//" WHERE LETTER_NAME='SECOND_INVOICE_REMINDER' AND INVOICE_SEQ_NO=A.INVOICE_SEQ_NO ) "+
					" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+14>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+14<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				    " GROUP BY A.DEBTOR_CODE ");
				
				
			
				
				double m_total_bal=0;
				while(rs2.next()){
					m_total_bal=rs2.getDouble(1);
				}
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:left;}'>");
				
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");			
				out.println("According to our records, your Account is overdue by Rs <b>"+nf.format(m_total_bal)+"</b> , ");
				String m_pre_reminder="-";
				rs2 = stmt2.executeQuery ("SELECT  NVL(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'-') "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
					" WHERE INVOICE_SEQ_NO='"+m_invoice_seq+"' "+
					" AND LETTER_NAME='PRE_INVOICE_REMINDER' ");
				
				if(rs2.next()){
					m_pre_reminder=rs2.getString(1);
				}
				out.print(" and regret to note, that you have not responded to our pre reminder letter dated <b> "+m_pre_reminder+"</b>  ");			
				
				
				String m_first_reminder="-";
				rs2 = stmt2.executeQuery ("SELECT  NVL(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'-') "+
					" FROM "+m_schema_name+".FA_OP_PRO_INVOICE_LETTER "+
					" WHERE INVOICE_SEQ_NO='"+m_invoice_seq+"' "+
					" AND LETTER_NAME='FIRST_INVOICE_REMINDER' ");
				
				if(rs2.next()){
					m_first_reminder=rs2.getString(1);
				}
				
				out.println(" and 1st reminder letter dated <b>"+m_first_reminder+"</b> sent to you in this connection. Please make immediate ");
				out.println(" settlement of the said amount and in any case on or before <b>"+m_deadline_date+"</b> failure to do will compel us taking further action against you to recover the said amount. ");
				out.println("<br><br> ");		
				out.println(" The invoice details are as follows. <br>");
				out.println("</p>");	
				out.println("</td></tr></table>");	
				out.println("<p style='text-align:justify' class='factoring-letter-body' >");				
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='15%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Invoice No</u></b></td>");
				out.println("<td width='14%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Invoice Date</u></b></td> ");
				out.println("<td width='12%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Due Date</u></b></td> ");
				out.println("<td width='18%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Invoice Amount</u></b></td> ");
				out.println("<td width='20%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Balance Amount</u></b></td> ");
				out.println("</tr>");
				out.println("</table>");	
				
				/*rs2 = stmt2.executeQuery ("SELECT NVL(A.BATCH_NO,'-'),"+
				" NVL(A.INVOICE_NO,'-'), "+
				" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+
				" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
				" NVL(A.INVOICE_AMOUNT,0), "+
				" NVL(A.BALANCE_AMOUNT,0) "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B  "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.INVOICE_SEQ_NO='"+m_invoice_seq+"' "+
				" AND A.BALANCE_AMOUNT>0 "+
				" AND A.INVOICE_STATUS='CONF' ");*/
				
				rs2 = stmt2.executeQuery ("SELECT NVL(A.BATCH_NO,'-'),"+
					" NVL(A.INVOICE_NO,'-'), "+
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+
					" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
					" NVL(A.INVOICE_AMOUNT,0), "+
					" NVL(A.BALANCE_AMOUNT,0) "+
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B  "+
					" WHERE B.FACILITY_NO='"+m_facility_no+"'"+
					" AND A.DEBTOR_CODE='"+m_debtor_code+"' "+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND B.CLIENT_CODE='"+m_client_no+"' "+
					" AND A.BALANCE_AMOUNT>0 "+
					" AND A.INVOICE_STATUS='CONF' "+
					" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+14>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+14<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
				
				
				
				while(rs2.next()){
					out.println("<table border='0' width='90%' class='table'> ");	
					out.println("<tr ><td width='15%' class='factoring-letter-body' style='{text-align:left;}'>"+rs2.getString(2)+"</td>");
					out.println("<td width='14%' class='factoring-letter-body' style='{text-align:left;}'>"+rs2.getString(3)+"</td> ");
					out.println("<td width='12%' class='factoring-letter-body' style='{text-align:left;}'>"+rs2.getString(4)+"</td> ");
					out.println("<td width='18%' class='factoring-letter-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(5))+"</td> ");
					out.println("<td width='20%' class='factoring-letter-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(6))+"</td> ");
					out.println("</tr>");	
					out.println("</TABLE>");
				}
				
				out.println("</p>");		
				out.println("<br>");
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");			
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:justify;}'>");
				out.println(" If you have already made appropriate remittances, please advise us accordingly at your earliest convenience and disregard this letter.If you have any queries concerning this account please "+ 
					" do not hesitate to contact the undersigned.");
				out.println("</td></tr></table>");									
				out.println("<br><br>");						
				out.println("<b>Early attention to this matter is highly appreciated.</b>");
				out.println("<br><br>");						
				out.println("Thanking you ");
				out.println("<br><br>");						
				out.println("</font></p>");				
				out.println("<p style='text-align:justify' class='factoring-letter-body'>");	
				out.println("Yours faithfully,<br>");	
				out.println("Factoring Division of <br>");	
				out.println("Lakderana Investments Limited <br><br><br><br>");	
				out.println("................................<br><br>");
				out.println("Authorized Signatory  <br>");	
				out.println("</p>");		
				out.println("</form></body></html>");
			}
			else  {
				out.println("idle");
			}	
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}  
