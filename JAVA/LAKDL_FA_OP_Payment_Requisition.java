//CREATED BY DINETH ON 27-07-2009


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


 
public class LAKDL_FA_OP_Payment_Requisition extends javax.servlet.http.HttpServlet {


   ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
  public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_payment_no,m_print;
	
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
			String m_payee="";
			double m_total_amt=0;
			String m_facility_no="";
			String m_pay_date="";
			String m_cheque_no="";
			String m_branch_name="";
			String m_bank_name="";
			String m_pay_desc="";
			String m_acc_no="";
			String m_amt_in_word="";
			String m_user_id="";
			String m_designation="";
			m_chksql=req.getParameter("chksql");
      m_print=req.getParameter("print");
			
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			   if (m_chksql.trim().equals("main_page")) {
					
					  m_payment_no=req.getParameter("payment_no");
						
						rs = stmt.executeQuery(" SELECT NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),' '), "+
						                       " NVL(A.PAYMENT_AMOUNT,0), "+
																	 " NVL(A.FACILITY_NO,' '), "+
																	 " NVL(TO_CHAR(A.PAY_DATE,'DD/MM/YYYY'),' '), "+
																	 " NVL(A.PAY_COMMENTS,' '), "+
                                   " NVL(A.CHEQUE_NO,' '), "+
																	 " NVL(A.LIC_BRANCH_CODE,' '), "+
																	 " NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.LIC_BRANCH_CODE),' '), "+
																	 " NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.LIC_BRANCH_CODE),' '), "+
																	 " NVL(A.LIC_ACC_NO,' ') "+
																	 " ,ENT_USER "+
                                   " FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
                                   " WHERE A.PAYMENT_CODE='"+m_payment_no+"'");
					  
						boolean more=rs.next();
						if(more){
						m_payee=rs.getString(1);
						m_total_amt=rs.getDouble(2);
						m_facility_no=rs.getString(3);
						m_pay_date=rs.getString(4);
						m_pay_desc=rs.getString(5);
						m_cheque_no=rs.getString(6);
						m_branch_name=rs.getString(8);
						m_bank_name=rs.getString(9);
						m_acc_no=rs.getString(10);
						m_amt_in_word=rs.getString(2);
						m_user_id=rs.getString(11);
						}
						
						
						rs = stmt.executeQuery(
						" SELECT "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(A.DESIGNATION_CODE) "+
						" FROM   "+m_schema_name+".CO_CO_MAS_USER A "+
						" WHERE  A.USER_ID='"+m_user_id+"' ");
						
						if(rs.next()){
						m_designation=rs.getString(1);
						}
						
						

						
						out.println("<html><head>"); 
						out.println("<title>Payment Requisition</title></head>");
						out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
						out.println("<script>");
			
						out.println("function print_data(){");
						
						//out.println(" alert('return no'+m_return_no);");
						out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_OP_Payment_Requisition?chksql=main_page&payment_no="+m_payment_no+"&print=FALSE\";");
						out.println("  window.location.href=m_url;"); 
						out.println("  m_table.innerHTML=\"\"; ");
						out.println("	 window.print();");
						out.println("}");
						
						out.println("function add_button(){");
						if (m_print.trim().equals("FALSE")) {
						out.println("m_table.innerHTML=\"\" ");
						}
						else
						{
						out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
						out.println("m_table.innerHTML='<table align=\"center\" width=\"90%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
						}
						out.println("}");

						out.println("</script>");				
						out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
						out.println("<body bgcolor='white'><br>");
						out.println("<form name='Form1'>");
						
		  			out.println("<blockquote><table align='center' width='90%' class='table' cellpadding='0' cellspacing='0'>"); 
						out.println("<tr>");  
						out.println("<td width=\"90%\"><DIV ID='m_table'></DIV></td>");
		  			out.println("</tr>"); 
						out.println("</table>");
						out.println("</blockquote>");
						out.println("<br><br><br>");//br
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
						out.println("<table border='0' width='90%' class='table'>");  		
						/*out.println("<tr>");
						out.println("<td width='20%'class='rep-body' style='{font:10px;text-align:left;}'></td><td width='28%'class='rep-body' style='{font:12px;text-align:center;}'><b><u>PAYMENT REQUISITION</u></b></td>");
						out.println("<td width='14%'class='rep-body' style='{font:10px;text-align:center;}'><b>PAYMENT CODE</b></td>");
						out.println("<td width='2%'class='rep-body' style='{font:10px;text-align:center;}'><b>:</b></td>");
						out.println("<td width='*%'class='rep-body' style='{font:12px;text-align:center;}'><b>"+m_payment_no+"</td></tr>");
						*/
						out.println("<table border='0' width='90%' class='table'>");  		
						out.println("<tr>");
						out.println("<td width='*%'class='rep-body' style='{font:10px;text-align:center;}'><b><u>PAYMENT REQUISITION</u></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<table border='0' width='90%' class='table'>");  		
						out.println("<tr>");
						out.println("<td width='50%'class='rep-body' style='{font:10px;text-align:right;}'><b>PAYMENT CODE &nbsp;:</td>");
						out.println("<td width='*%'class='rep-body' style='{font:10px;text-align:left;}'><b>"+m_payment_no+"</td>");
						out.println("</tr>");
						out.println("</TABLE>");
						
						out.println("</font></blockquote>");
						
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
						out.println("<table border='0' width='90%' class='table'>");  		
						out.println("<tr>");
						out.println("<td width='20%'class='rep-body' style='{font:10px;text-align:left;}'>TO</td>");
						out.println("<td width='5%'class='rep-body' style='{font:10px;text-align:center;}'>:</td>");
						out.println("<td width='65%'class='rep-body' style='{font:10px;text-align:left;}'><b>AGM- FINANCE & TREASURY</b></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='20%'class='rep-body' style='{font:10px;text-align:left;}'>PAYEE</td>");
						out.println("<td width='5%'class='rep-body' style='{font:10px;text-align:center;}'>:</td>");
						out.println("<td width='65%'class='rep-body' style='{font:10px;text-align:left;}'><b>"+m_payee+"</b></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='20%'class='rep-body' style='{font:10px;text-align:left;}'>TOTAL AMT</td>");
						out.println("<td width='5%'class='rep-body' style='{font:10px;text-align:center;}'>:</td>");
						out.println("<td width='65%'class='rep-body' style='{font:10px;text-align:left;}'><b>"+nf.format(m_total_amt)+"</b></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='20%'class='rep-body' style='{font:10px;text-align:left;}'>AMT IN WORD</td>");
						out.println("<td width='5%'class='rep-body' style='{font:10px;text-align:center;}'>:</td>");
						out.println("<td width='65%'class='rep-body' style='{font:10px;text-align:left;}'><b>Rupees "+m_sn_methods.numbersToChar(m_amt_in_word)+" only</b></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='20%'class='rep-body' style='{font:10px;text-align:left;}'>FACILITY NO</td>");
						out.println("<td width='5%'class='rep-body' style='{font:10px;text-align:center;}'>:</td>");
						out.println("<td width='65%'class='rep-body' style='{font:10px;text-align:left;}'><b>"+m_facility_no+"</b></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='20%'class='rep-body' style='{font:10px;text-align:left;}'>DATE</td>");
						out.println("<td width='5%'class='rep-body' style='{font:10px;text-align:center;}'>:</td>");
						out.println("<td width='65%'class='rep-body' style='{font:10px;text-align:left;}'><b>"+m_pay_date+"</b></td>");
						out.println("</tr>");
						
						
						
						out.println("</table>");
						out.println("</font></blockquote>");
						
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
            out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr>");
						out.println("<td width='30%'class='rep-body' style='{font:10px;text-align:left;}'><b>");
						out.println("DESCRIPTION OF PAYMENT");
						out.println("</b></td>");
						out.println("<td width='60%'class='rep-body' style='{font:10px;text-align:center;}'></td>");
            out.println("</tr>");
						out.println("</table>");
						out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr><td>");
						out.println(m_pay_desc);
						out.println("</td></tr>");
						out.println("</table>");
						out.println("</font></blockquote>");
						
						out.println("<br>");
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
						out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr>");
						out.println("<td width='52%'class='rep-body' style='{font:10px;text-align:left;}'><b>");
						out.println("RECOMENDED BY");
						out.println("</b></td>");
						out.println("<td width='48%'class='rep-body' style='{font:10px;text-align:left;}'><b>");
            out.println("PAYMENT RAISED BY");
						out.println("</b></td></tr>");
						out.println("</table>");

						out.println("<table border='1' width='90%' class='table' cellpadding='0' cellspacing='0'>");
						out.println("<tr valign='bottom'>");
						out.println("<td width='24%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='24%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='4%'>&nbsp;</td>");
						out.println("<td width='24%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("....  <b>"+m_user_id+"</b>  ......</td>");
						out.println("<td width='24%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("</tr>");
            out.println("<tr>");
						out.println("<td width='24%' style='{font:10px;text-align:center;}'><b>");
						out.println("Asst M-CR</b></td>");
						out.println("<td width='24%' style='{font:10px;text-align:center;}'><b>");
						out.println("Snr Exe-OP</b></td>");
						out.println("<td width='4%'>&nbsp;</td>");
						out.println("<td width='24%' style='{font:10px;text-align:center;}'><b>");
						out.println(""+m_designation+"</b></td>");//DESIGNATION
						out.println("<td width='24%' style='{font:10px;text-align:center;}'><b>");
						out.println("Checked By</b></td>");
						out.println("</tr>");

						out.println("</table>");
						out.println("</font></blockquote>");
						
						///out.println("<br>");//br
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
						out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr>");
						out.println("<td width='30%' class='rep-body' style='{font:10px;text-align:left;}'><b>");
						out.println("APPROVED BY");
						out.println("</b></td>");
						out.println("<td width='70%' class='rep-body' style='{font:10px;text-align:left;}'>");
            
						out.println("</td></tr>");
						out.println("</table>");
						out.println("<table border='1' width='90%' class='table' cellpadding='0' cellspacing='0'>");
						out.println("<tr valign='bottom'>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						
						out.println("</tr>");
            out.println("<tr>");
						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-FACTORING</b></td>");
						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-BD</b></td>");
 						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-SP</b></td>");
						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-F&T</b></td>");
            out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-OP</b></td>");
            out.println("</tr>");

						out.println("</table>");


            out.println("</font></blockquote>");
						
						
						
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
						out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr>");
						out.println("<td width='30%' class='rep-body' style='{font:10px;text-align:left;}'><b>");
						out.println("CONFIRMED BY");
						out.println("</b></td>");
						out.println("<td width='70%' class='rep-body' style='{font:10px;text-align:left;}'>");
            
						out.println("</td></tr>");
						out.println("</table>");
						out.println("<table border='1' width='90%' class='table' cellpadding='0' cellspacing='0'>");
						out.println("<tr valign='bottom'>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						
						out.println("</tr>");
            out.println("<tr>");
						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("CHAIRMAN/DIRECTOR/CEO</b></td>");
						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-FACTORING</b></td>");
						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-BD</b></td>");
 						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-SP</b></td>");
						out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-F&T</b></td>");
            out.println("<td width='25%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-OP</b></td>");
            out.println("</tr>");

						out.println("</table>");


            out.println("</font></blockquote>");
            
						/*
						
						out.println("<br>");
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
						out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr>");
						out.println("<td width='30%' class='rep-body' style='{font:10px;text-align:left;}'><b>");
						out.println("CONFIRMED BY");
						out.println("</b></td>");
						out.println("<td width='70%' class='rep-body' style='{font:10px;text-align:left;}'>");
            
						out.println("</td></tr>");
						out.println("</table>");
						out.println("<table border='1' width='90%' class='table' cellpadding='0' cellspacing='0'>");
						out.println("<tr valign='bottom'>");
						out.println("<td width='30%' style='{height:60px;font:10px;text-align:left;}'>");
						out.println("...............................................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:left;}'>");
						out.println("............................</td>");
						out.println("<td width='20%' style='{height:60px;font:10px;text-align:center;}'>");
						out.println("............................</td>");
						out.println("<td width='25%' style='{height:60px;font:10px;text-align:left;}'>");
						out.println("............................</td>");
						
						out.println("</tr>");
            out.println("<tr>");
						out.println("<td width='30%' style='{font:10px;text-align:left;}'><b>");
						out.println("CHAIRMAN/DIRECTOR/CEO</b></td>");
            out.println("<td width='25%' style='{font:10px;text-align:left;}'><b>");
						out.println("DATE</b></td>");
						out.println("<td width='20%' style='{font:10px;text-align:center;}'><b>");
						out.println("AGM-OP</b></td>");
            out.println("<td width='25%' style='{font:10px;text-align:left;}'><b>");
						out.println("DATE</b></td>");

            out.println("</tr>");

						out.println("</table>");
            out.println("</font></blockquote>");
						*/
						
						
						out.println("<br>");//br
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
						out.println("<table border='0' width='90%' class='table'>");  		
			      out.println("<tr>");
						out.println("<td width='55%'class='rep-body' style='{font:10px;text-align:left;}'><b>");
						out.println("DISBURSEMENT DETAILS");
						out.println("</b></td>");
						out.println("<td width='45%'class='rep-body' style='{font:10px;text-align:left;}'><b>");
            out.println("COLLECTED BY");
						out.println("</b></td></tr>");
						out.println("</table>");
			   
					  out.println("<table valign='bottom' border='0' width='90%' class='table' cellspacing='0'>");  		
			   
					  out.println("<tr>");
						out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>CHQ NO</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>"+m_cheque_no+"</td>");
						out.println("<td width='10%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'></td>");
            out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>SIGNATORY</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>..........................................</td>");

						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>BANKNAME</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>"+m_bank_name+"</td>");
						out.println("<td width='10%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'></td>");
            out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>NAME</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>..........................................</td>");

						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>BRANCH NAME</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>"+m_branch_name+"</td>");
            out.println("<td width='10%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'></td>");
            out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>NIC/DL/PP NO</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>..........................................</td>");

            out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>A/C NO</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>"+m_acc_no+"</td>");
						out.println("<td width='10%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'></td>");
            out.println("<td width='15%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>DATE</td>");
						out.println("<td width='30%'class='rep-body' style='{height:30px;font:10px;text-align:left;}'>..........................................</td>");

            out.println("</tr>");
						out.println("</table>");
						
						
						out.println("</font></blockquote>");
						out.println("<br>");
						out.println("<blockquote>");
						out.println("<font size=2><p style='text-align:left'>");
            out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr>");
						out.println("<td width='30%'class='rep-body' style='{font:10px;text-align:left;}'><b>");
						out.println("DIRECT DEPOSIT DETAILS");
						out.println("</b></td>");
						out.println("<td width='60%'class='rep-body' style='{font:10px;text-align:center;}'></td>");
            out.println("</tr>");
						out.println("</table>");
						out.println("<table border='0' width='90%' class='table'>");
						out.println("<tr><td width='100%' style='text-align:left'>");
						out.println(".....................................................................................................................................</td></tr>");
						out.println("<tr><td width='100%' style='text-align:left'>");
						out.println(".....................................................................................................................................</td></tr>");
						out.println("<tr><td width='100%' style='text-align:left'>");
						out.println(".....................................................................................................................................</td></tr>");
						out.println("</table>");
						out.println("</font></blockquote>");
						
						
						
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




