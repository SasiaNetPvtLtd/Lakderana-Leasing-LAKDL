 
//SCREEN NAME:FINAL CHEQUE RETURN REMINDER(WITH REMARK)
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_OP_Final_Chq_Return_Reminder_Letter_with_rem extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;
	
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
			
			String m_debtor_code="-",m_client_name="-",m_receipt_no="-",m_batch_no="-",m_return_no="-",m_facility_no="-";
			String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_remark="",m_invoice_no="-";
			double m_chq_amount=0;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_client_no=req.getParameter("client_no");		
			m_debtor_code=req.getParameter("debtor_code");		
			m_print=req.getParameter("print");
			m_receipt_no=req.getParameter("receipt_no");
			m_remark=req.getParameter("remark");
			m_return_no=req.getParameter("return_no");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Final Cheque Return Reminder (With Remark)</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			//out.println(" m_return_no='"+m_return_no+"'; ");
			//out.println(" alert('return no'+m_return_no)");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_save_final_chq_return_rem_with_rk_count?client_no="+m_client_no+"&debtor_code="+m_debtor_code+"&print="+m_print+"&receipt_no="+m_receipt_no+"&return_no="+m_return_no+"&remark="+m_remark+"\";");  
			out.println("  window.location.href=m_url;"); 
			out.println("	 m_table.innerHTML=\"\" ");
			out.println("	 window.print();");
			out.println("}");
			
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
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
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY HH24:MI:SS') FROM DUAL ");								
			boolean more = rs.next();
			
			if(more){
			m_Letter_date=rs.getString(1);
			}
		 
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE+7,'DD-MM-YYYY') FROM DUAL ");								
			more = rs.next();
			
			if(more){
			m_deadline_date=rs.getString(1);
			}

			rs2 = stmt2.executeQuery (	" SELECT "+
			" CLIENT_CODE, "+
			" NVL(FULL_NAME,'-') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			" WHERE CLIENT_CODE='"+m_client_no+"' ");
			
			more = rs2.next();
			if(more){
			m_client_name = rs2.getString(2);
			}
			
			rs2 = stmt2.executeQuery (" SELECT NVL(UPPER(RETURN_COMMENTS),'-') "+
		  " FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS  "+
			" WHERE RECEIPT_NO='"+m_receipt_no+"'");
			
			if(rs2.next()){
			m_remark=rs2.getString(1);
			}
			
			String m_receipt_type="CS";
			
			rs2 = stmt2.executeQuery ("SELECT  NVL(A.CHEQUE_NO,'-'), "+//1 
			" NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+ //2
			"	A.PAYER_BRANCH_CODE, "+//3
			" NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE),'-'), "+//4
			" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-'), "+//5
			" NVL(A.REC_AMOUNT,0), "+//6
			" A.RECEIPT_TYPE "+//7
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
			" WHERE A.RECEIPT_NO='"+m_receipt_no+"' ");
			
			more = rs2.next();
			if(more){
			m_chq_no=rs2.getString(1);
			m_chq_date=rs2.getString(2);
			m_bank=rs2.getString(4);
			m_branch=rs2.getString(5);
			m_chq_amount=rs2.getDouble(6);
			m_receipt_type=rs2.getString(7);
			}
			
			//added by ns on 27-09-2010
			if (m_debtor_code.equals("-")){
			m_receipt_type="CS";
			}
			
			if(m_receipt_type.equals("CS")){
			rs1 = stmt1.executeQuery (" SELECT "+
			" CLIENT_CODE, "+
			" NVL(FULL_NAME,'-'), "+
			" NVL(REGISTERED_ADDRESS1,'-'), "+
			" NVL(REGISTERED_ADDRESS2,'-'), "+
			" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			" WHERE CLIENT_CODE='"+m_client_no+"' ");
			}
			else{
			rs1 = stmt1.executeQuery (" SELECT "+
			" CLIENT_CODE, "+
			" NVL(FULL_NAME,'-'), "+
			" NVL(REGISTERED_ADDRESS1,'-'), "+
			" NVL(REGISTERED_ADDRESS2,'-'), "+
			" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			" WHERE CLIENT_CODE='"+m_debtor_code+"' ");
			}
			
			more = rs1.next();
			if(more){
			m_c_code=rs1.getString(1);
			m_name=rs1.getString(2);
			m_add1=rs1.getString(3);
			m_add2=rs1.getString(4);
			m_city_desc=rs1.getString(5);
			}
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");										
			out.println("<br><br><br><br><br><br><br>");
			// Commented below by Udara on 07-06-2011
			/*
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr><td width='*%'class='rep-body'>Co.Reg.No PB75</td></tr>");
			out.println("<tr><td width='*%'class='rep-body'>Factoring Division of</td></tr>");
	  	   out.println("<tr><td width='*%' class='rep-body'>Orient Financial Services Corporation Ltd</td></tr>");
		   out.println("<tr><td width='*%' class='rep-body'>525, Union Place</td></tr>");
		   out.println("<tr><td width='*%' class='rep-body'>Colombo 02.</td></tr>");
			out.println("<tr><td width='*%'class='rep-body'>"+m_Letter_date+"</td></tr>");
			out.println("</TABLE>");
			out.println("<br><br><br><br>");
			*/
			out.println("<table border='0' width='100%' class='table'>"); 		
	  	   out.println("<tr><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
		   out.println("<tr><td width='*%' class='rep-body' >"+m_add1+"</td></tr>");
		   out.println("<tr><td width='*%' class='rep-body' >"+m_add2+"</td></tr>");
         out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+"</td></tr>");
			out.println("<tr><td width='*%'class='rep-body'>"+m_Letter_date+"</td></tr>"); // Added by Udara on 07-06-2011
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");
			out.println("<br>");
			out.println("<blockquote><font size=2><p style='text-align:justify'>");				
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:center;}'><b><u>Cheque Return</u></b></td></tr>");
			out.println("</TABLE><br>");		
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir,</td></tr>");
			out.println("</TABLE><br><br>");		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='50%' class='rep-body' style='{text-align:left;}'>Supplier code :- "+m_client_no+"</td>");
			out.println("<td width='50%' class='rep-body' style='{text-align:right;}'>Supplier Name :- "+m_client_name+"</td>");
			out.println("</tr>");
			out.println("<tr ><td width='50%' class='rep-body' style='{text-align:left;}'>Debtor code :- "+m_debtor_code+"</td>");
			out.println("<td width='*%'></td> ");
			out.println("</tr>");
			out.println("</TABLE>");		
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:justify;}'>");
			out.println("We regret to inform you that the Cheque bearing No <b>"+m_chq_no+"</b> "+
									" dated <b>"+m_chq_date+"</b> drawn on <b>"+m_bank+","+m_branch+"</b> has been dishonored for "+
									" payments and returned to us with the remark of <b>"+m_remark+" </b>. The said cheque amounting to <b> Rs. "+nf.format(m_chq_amount)+"</b> "+
									" was being the settlement of the invoices, details given below. ");
			out.println("</td></tr></table>");						
			out.println("</font></p></blockquote>");				
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body' >");				
			
			rs2 = stmt2.executeQuery ("SELECT NVL(BATCH_NO,'-'),"+//1
			" NVL(INVOICE_NO,'-'),"+//2
			" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'),"+//3
			" TO_CHAR(DUE_DATE,'DD-MM-YYYY'),"+//4
			" NVL(INVOICE_AMOUNT,0),"+//5
			" NVL(BALANCE_AMOUNT,0) "+//6
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL "+
			" WHERE DEBTOR_CODE='"+m_debtor_code+"'  "+
			" AND CLIENT_CODE='"+m_client_no+"' "+
			" AND INVOICE_NO IN( "+
			" SELECT INVOICE_NO INVOICE_NO FROM "+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO	WHERE RECEIPT_NO='"+m_receipt_no+"' "+
			" UNION ALL "+
			" SELECT A.INVOICE_NO INVOICE_NO FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
			" WHERE B.RECEIPT_NO='"+m_receipt_no+"' AND	A.POD_REF_NO=B.SUS_REF_NO AND	B.RECEIPT_TYPE='POD')");    
	
			more = rs2.next();
			
			if(more){
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='12%' class='rep-body' style='{text-align:left;}'><b><u>Invoice No</u></b></td>");
			out.println("<td width='15%' class='rep-body' style='{text-align:left;}'><b><u>Invoice Date</u></b></td> ");
			out.println("<td width='12%' class='rep-body' style='{text-align:left;}'><b><u>Due Date</u></b></td> ");
			out.println("<td width='20%' class='rep-body' style='{text-align:right;}'><b><u>Invoice Amount</u></b></td> ");
			out.println("<td width='20%' class='rep-body' style='{text-align:right;}'><b><u>Balance Amount</u></b></td> ");
			out.println("</tr>");
			out.println("</TABLE>");
			}
			
			while(more){
				out.println("<table border='0' width='90%' class='table'> ");	
				out.println("<tr ><td width='12%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(2)+"</td>");
				out.println("<td width='15%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(3)+"</td> ");
				out.println("<td width='12%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(4)+"</td> ");
				out.println("<td width='20%' class='rep-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(5))+"</td> ");
				out.println("<td width='20%' class='rep-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(6))+"</td> ");
				out.println("</tr>");	
				out.println("</TABLE>");	
		 		more = rs2.next();
			}
			
			out.println("</font></p></blockquote>");		
			out.println("<br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:justify;}'>");
			out.println("While point out to you that under the <b>Debt Recovery Special Provision Act</b> dishonoring of"+
			" a cheque is an offence punishable and we demand payment upon receipt of this letter, failing"+
			" which will result in our filling legal action against you for the recovery of the said amount"+
			" due together with the cost of the action. ");
			out.println("</td></tr></table>");						
			out.println("</font></p></blockquote>");				
			out.println("<br><br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("Yours faithfully,<br>");	
			out.println(" Factoring Division of <br>");	
			out.println(" Lakderana Investments Limited <br><br><br><br>");	
			out.println(" ................................<br><br>");
			out.println(" Authorized Signatory  <br>Executive Operations");	
			out.println("</font></p></blockquote>");		
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
