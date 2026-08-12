   
//SCREEN NAME:INITIAL CHEQUE RETURN REMINDER
//DEVELOPED BY MAHELA FOR FACTORING ON 29-01-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_OP_Cheque_Return_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print,m_contact_person,m_contact_desig;
	
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
			String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-";
			double m_chq_amount=0;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_client_no=req.getParameter("client_no");		
			m_debtor_code=req.getParameter("debtor_code");		
			m_print=req.getParameter("print");
			m_receipt_no=req.getParameter("receipt_no");
			m_return_no=req.getParameter("return_no");
			m_facility_no=req.getParameter("facility_no");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Initial Cheque Return Reminder</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			out.println(" m_return_no='"+m_return_no+"'; ");
			//out.println(" alert('return no'+m_return_no);");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_save_chq_return_legal_letter?client_no="+m_client_no+"&debtor_code="+m_debtor_code+"&print="+m_print+"&receipt_no="+m_receipt_no+"&return_no="+m_return_no+"&facility_no="+m_facility_no+"\";");  
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
		
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
				rs1 = stmt1.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+//1
  				  " NVL(UPPER(FULL_NAME),'-'), "+//2
  				  " NVL(UPPER(REGISTERED_ADDRESS1),'-'), "+//3
  				  " NVL(UPPER(REGISTERED_ADDRESS2),'-'), "+//4
  				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
						" NVL(UPPER(REGISTERED_CONTACT_PERSON),'-'), "+//6
						" NVL(UPPER(DESIGNATION_PAYMENT),'-') "+//7
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs1.next();
					if(more){
						m_c_code=rs1.getString(1);
						m_name=rs1.getString(2);
						m_add1=rs1.getString(3);
						m_add2=rs1.getString(4);
						m_city_desc=rs1.getString(5);
						m_contact_person = rs1.getString(6);
						m_contact_desig = rs1.getString(7);
					}
					
				/*rs2 = stmt2.executeQuery ("SELECT  NVL(A.CHEQUE_NO,'-'), "+//1 
  			 		" NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+ //2
    				"	A.PAYER_BRANCH_CODE, "+//3
						" NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE),'-'), "+//4
						" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-'), "+//5
  					" NVL(A.REC_AMOUNT,0), "+//6
  	 				" A.RECEIPT_TYPE, "+//7
    				" A.BATCH_NO, "+//8
						" A.INVOICE_NO, "+//9
            " D.INVOICE_NO "+//10
 						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B, "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO D "+
 						" WHERE A.RECEIPT_NO='"+m_receipt_no+"' AND (D.DEBTOR_CODE='"+m_debtor_code+"' OR A.DEBTOR_CODE='"+m_debtor_code+"') AND A.CLIENT_CODE='"+m_client_no+"' AND A.SETTLE_MODE='CHEQUE' "+
						" AND A.SUS_REF_NO=D.POD_REF_NO(+) "+	
						"	AND A.RECEIPT_NO=B.RECEIPT_NO ");
					
					more = rs2.next();
						if(more){
							m_chq_no=rs2.getString(1);
							m_chq_date=rs2.getString(2);
							m_bank=rs2.getString(4);
							m_branch=rs2.getString(5);
							m_chq_amount=rs2.getDouble(6);
							if(rs2.getString(7).equals("IS")){
							 m_batch_no=rs2.getString(8);	
							 m_invoice_no=rs2.getString(9);		
							}
							else if(rs2.getString(7).equals("POD")){
							 m_invoice_no=rs2.getString(10);		
							}
						 }*/

			
			out.println("<blockquote><font size=2><p style='text-align:left'>");										
			//out.println("<br>");
			/*out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b><font size=4> GUNAWARDENA & RANASINGHE ASSOCIATES </font></b></td></tr>");
			out.println("</TABLE>");		
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='55%' class='rep-body'></td><td width='*%' class='rep-body'>No. 1056, Maradana Road,</td></tr>");
			out.println("<tr><td width='55%' class='rep-body'></td><td width='*%' class='rep-body'>Colombo 08</td></tr>");
			out.println("</TABLE>");
			out.println("<table border='0' width='90%' class='table'>"); 		
	    out.println("<tr><td width='15%' class='rep-body'></td><td width='10%' class='rep-body'>Tel NO.</td><td width='60%' class='rep-body'>686626</td><td width='*%' class='rep-body'></td></tr>");
		  out.println("<tr><td width='15%' class='rep-body'></td><td width='10%' class='rep-body'></td><td width='60%' class='rep-body'>683022</td><td width='*%' class='rep-body'></td></tr>");
			//out.println("<tr><td width='*%' class='rep-body'>"+m_Letter_date+"</td></tr>");
			out.println("</TABLE>");
			out.println("<hr>");*/
			out.println("<table border='0' width='100%' class='table'>");  		
			out.println("<tr><td width='70%'class='rep-body' style='{font:10px;text-align:left;}'></td><td width='70%'class='rep-body' style='{font:12px;text-align:left;}'>Registered post</td></tr>");
			out.println("</TABLE>");
			out.println("<table border='0' width='90%' class='table'>");
  		out.println("<tr><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'>Co.Reg.No. PB75</td></tr>");	
			out.println("<tr><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'>"+m_Letter_date+"</td></tr>");
			//out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_contact_person+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_contact_desig+"</td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_name+" </td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add1+" </td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add2+"</td></tr>");
      out.println("<tr><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_city_desc+" </td></tr>");
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");
			//out.println("<br>");
			out.println("<blockquote><font size=2><p style='text-align:justify'>");				
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'><br>Dear Sir,</td></tr>");
			out.println("</TABLE><br><br>");		
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'><b><u>Dishonoured cheques drawn in favour of Lakderana Investments Limited on Factoring  Agreement No. "+m_facility_no+"</u></b></td></tr>");
			out.println("</TABLE><br>");				
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("We are writing on the instructions of our client Lakderana Investments Limited of No. 100,   Buthgamuwa Road,  Rajagiriya carrying on factoring business under the trade name 'OrientFactor' ");
			out.println("</td></tr></table> ");						
			out.println("</font></p></blockquote>");				
			out.println("<blockquote><font size=2><p style='font:10px;text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("We hereby inform you that the below numbered cheques which had been submitted to our client in settlement of dues under factoring facilities granted by our client has been dishonoured on presentment with the remarks. ");
			out.println("</td></tr></table> ");						
			out.println("</font></p></blockquote>");	
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body' >");				
	
						
						rs2 = stmt2.executeQuery (" SELECT "+
						   " CHEQUE_NO,"+//1
						   " NVL(TO_CHAR(REALIZE_DATE,'DD-MM-YYYY'),'-'),"+//2
						   " NVL(BRANCH_CODE,'-'),"+//3
						   " NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-'),"+//4
						   " NVL(DEPOSIT_AMOUNT,0),"+//5
						   " NVL(RETURN_COMMENTS,'-')"+//6
						 " FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS "+
						 " WHERE RETURN_NO='"+m_return_no+"' ");
						
					more = rs2.next();
					double m_amount=0;
					
			//if(more){		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='12%' class='rep-body' style='{font:10px;text-align:left;}'><b><u>Cheque No</u></b></td>");
			out.println("<td width='15%' class='rep-body' style='{font:10px;text-align:left;}'><b><u>Date</u></b></td> ");
			out.println("<td width='12%' class='rep-body' style='{font:10px;text-align:left;}'><b><u>Bank</u></b></td> ");
			out.println("<td width='15%' class='rep-body' style='{font:10px;text-align:right;}'><b><u>Amount</u></b></td> ");
			out.println("<td width='5%' class='rep-body' style='{font:10px;text-align:right;}'></td> ");
			out.println("<td width='20%' class='rep-body' style='{font:10px;text-align:left;}' ><b><u>Remark</u></b></td> ");
			out.println("</tr>");
			out.println("</TABLE>");		
			
				while(more){
				  m_amount=rs2.getDouble(5);
					out.println("<table border='0' width='90%' class='table'> ");	
					out.println("<tr ><td width='12%' class='rep-body' style='{font:10px;text-align:left;}'>"+rs2.getString(1)+"</td>");
					out.println("<td width='15%' class='rep-body' style='{font:10px;text-align:left;}'>"+rs2.getString(2)+"</td> ");
					out.println("<td width='12%' class='rep-body' style='{font:10px;text-align:left;}'>"+rs2.getString(4)+"</td> ");
					out.println("<td width='15%' class='rep-body' style='{font:10px;text-align:right;}'>"+nf.format(rs2.getDouble(5))+"</td> ");
					out.println("<td width='5%' class='rep-body' style='{font:10px;text-align:right;}'></td> ");
					out.println("<td width='20%' class='rep-body' style='{font:10px;text-align:left;}'>"+rs2.getString(6)+"</td> ");
					out.println("</tr>");	
					out.println("</TABLE>");	
			 		more = rs2.next();
				}
			//}
			out.println("</font></p></blockquote>");		
			out.println("<blockquote><font size=2><p style='font:10px;text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("We have been instructed to demand from you a sum of Rs "+nf.format(m_amount)+" /- be paid to our client within 14 days of this demand. ");
			out.println("</td></tr></table> ");						
			out.println("</font></p></blockquote>");	
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("On your failure to pay the said amount within the stipulated time we are further instructed to file action. ");
			out.println("</td></tr></table> ");						
			out.println("</font></p></blockquote>");	
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Under our law <b>it is a criminal offence punishable in the Magistrate Court to issue a cheque for valuable consideration knowing that there are no funds in the Bank to honour the cheque. This is an offecnce punisihable with imprisonment or a fine or with both.</b>");
			out.println("</td></tr></table> ");						
			out.println("</font></p></blockquote>");	
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("We are further instructed to inform you that all signatories to the cheque are also liable. ");
			out.println("</td></tr></table> ");						
			out.println("</font></p></blockquote>");	
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Should you wish to avoid such consequences please pay the amount demanded within the stipulated time. If you have already paid the said amount you may disregard this demand. ");
			out.println("</td></tr></table> ");						
			out.println("</font></p></blockquote>");	

			out.println("<blockquote><font size=2><p style='font:10px;text-align:justify' class='rep-body'>");	
			out.println("Yours faithfully,<br>");	
			out.println(" <b>Gunawardena & Ranasinghe Associates </b>");	
			out.println(" <br><br><br>");	
			out.println(" ................................<br>");
    	out.println(" Attorneys-at-Law  <br>");	
			out.println("</p></font></blockquote>");		
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
