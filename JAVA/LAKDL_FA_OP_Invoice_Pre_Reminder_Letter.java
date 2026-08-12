
//SCREEN NAME:INVOICE PRE REMINDER LETTER
//DEVELOPED BY MAHELA FOR FACTORING ON 08-02-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

    
     
public class LAKDL_FA_OP_Invoice_Pre_Reminder_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
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
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			String m_chksql=req.getParameter("chksql");
			String m_client_no=req.getParameter("client_no");		
			String m_debtor_code=req.getParameter("debtor_code");		
			String m_facility_no=req.getParameter("facility_no");
			String m_client_name="";
			
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Pre Reminder</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<body leftmargin='0' topmargin='0' class=body>");	
			out.println("<body bgcolor='white'>");
			out.println("<form name='Form1'>");	
		
			rs1 = stmt1.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");								
			boolean more = rs1.next();
			if(more){
			m_Letter_date=rs1.getString(1);
			}

			rs1 = stmt1.executeQuery ("SELECT CLIENT_CODE,NVL(FULL_NAME,' ') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			" WHERE CLIENT_CODE='"+m_client_no+"' ");
			
			more = rs1.next();
			if(more){
			m_client_name = rs1.getString(2);
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
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b><u>PRE REMINDER</u></b></td></tr>");
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
			out.println("<td width='*%'></td> ");
			out.println("</tr>");
			out.println("</TABLE>");		
			out.println("</p>");
			
			out.println("<p style='text-align:justify' class='factoring-letter-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:justify;}'>");
			out.println("We refer to the factoring agreement we have with your above named supplier under which you have"+
									" been advised to pay all debts due to the said supplier, which have been assigned to us, "+
									" direct to Lakderana Investments Limited "+
									" carrying on factoring business under the name and style of <b>'Orient Factor'</b> at No. 100,   Buthgamuwa Road,  Rajagiriya,"+
									" Colombo 08. ");
			out.println("</td></tr></table>");						
			out.println("</font></p>");				

			out.println("<p style='text-align:justify' class='factoring-letter-body'>");	

			out.println(" For your convenience the amount falling due is given below. ");		
			out.println("</p>");				
			out.println("<p style='text-align:justify' class='factoring-letter-body' >");				

				 
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
			" AND (A.DUE_DATE-10)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')");
					
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='15%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Invoice No</u></b></td>");
			out.println("<td width='14%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Invoice Date</u></b></td> ");
			out.println("<td width='12%' class='factoring-letter-body' style='{text-align:left;}'><b><u>Due Date</u></b></td> ");
			out.println("<td width='18%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Invoice Amount</u></b></td> ");
			out.println("<td width='20%' class='factoring-letter-body' style='{text-align:right;}'><b><u>Balance Amount</u></b></td> ");
			out.println("</tr>");
			out.println("</TABLE>");		
			
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
			out.println(" If you have already made appropriate remittances in advance please advise us accordingly "+
									" at your earliest convenience. Also if you have any queries concerning this account please "+ 
									" do not hesitate to contact the undersigned.");
			out.println("</td></tr></table>");						
			out.println("<br>");						
			out.println("<b>Early attention to this matter is highly appreciated.</b>");
			out.println("<br><br>");						
			out.println("Thanking you ");
			out.println("<br><br>");						
			out.println("</p>");				
			out.println("<p style='text-align:justify' class='factoring-letter-body'>");	
			out.println(" Yours faithfully,<br>");	
			out.println(" Factoring Division of <br>");	
			out.println(" Orient Financial Services Corporation Ltd <br><br><br><br>");	
			out.println(" ................................<br><br>");
    	out.println(" Authorized Signatory  <br>");	
			out.println("</p>");		
		  out.println("</form></body></html>");
			}//End of main page
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
