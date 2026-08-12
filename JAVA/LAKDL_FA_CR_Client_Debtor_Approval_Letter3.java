
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_CR_Client_Debtor_Approval_Letter3 extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
	java.text.NumberFormat nf;
	 
  public ResultSet rs;
  public ResultSet rs1,rs2,rs3,rs4;
	public String m_chksql,m_html_client_url,m_user_name;

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
			
			String m_Letter_date="",m_debtor_code="",m_add1="",m_add2="",m_name="",m_city_desc="",m_client_no="",m_print="",m_letter_type="";
			double m_credit_limit,m_reverse_margin,m_int_rate;
			String m_debtor_code2="",m_client_name="",m_contact_person="",m_emp_id="",m_designation="-",m_client_code="",m_facility_no="";
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_client_no=req.getParameter("client_no");		
			m_debtor_code=req.getParameter("debtor_code");		
			m_print=req.getParameter("print");
			m_letter_type=req.getParameter("letter_type");
			//=======Modified by Dineth
			m_facility_no=req.getParameter("facility_no");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();			
			stmt4 = conn.createStatement();			

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>To be sent on the letter head of  Orient Factor </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			out.println("function print_data(){");
			//Modified by Dineth on 2008-09-19
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_CR_client_debtor_doc_issue_updation?doc_code=D003&client_code="+m_client_no+"&facility_no="+m_facility_no+"&debtor_code="+m_debtor_code+"\";"); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_CR_client_debtor_doc_issue_updation?doc_code=D003&client_code="+m_client_no+"&facility_no="+m_facility_no+"&debtor_code="+m_debtor_code+"\";");  //change
			out.println(" window.location.href=m_url;");
			//End by Dineth on 2008-09-19
			out.println(" m_table.innerHTML=\"\" ");
			out.println(" window.print();");
			out.println("}");
			/*out.println("function add_button(){");
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("}");*/
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
			
			//out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			//out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	//out.println("m_writedata+'</table>';");
			out.println("}");

			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
			//out.println("<body bgcolor='white'><br>");
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
  			
				String m_desig_payment="";
				String m_client_type="C";
				
				rs1 = stmt1.executeQuery (	" SELECT "+
				" NVL(A.CLIENT_CODE,'-'), "+//1
				" NVL(UPPER(A.FULL_NAME),'-'), "+//2
				" NVL(UPPER(A.REGISTERED_ADDRESS1),'-'), "+//3
				" NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+//4
				" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+//5
				" NVL(UPPER(B.CONTACT_PERSON),'-'),"+//6
				" NVL(UPPER(B.DESIGNATION_PAYMENT),'-'), "+//7
				" A.CLIENT_TYPE "+//8
				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B "+
				" WHERE B.DEBTOR_CODE=A.CLIENT_CODE "+
				" AND B.DEBTOR_CODE='"+m_debtor_code+"' "+
				" AND B.CLIENT_CODE='"+m_client_no+"' ");

				more = rs1.next();
				if(more){
					m_debtor_code2=rs1.getString(1);
					m_name=rs1.getString(2);
					m_add1=rs1.getString(3);
					m_add2=rs1.getString(4);
					m_city_desc=rs1.getString(5);
					m_contact_person=rs1.getString(6);
					m_desig_payment=rs1.getString(7);
					m_client_type=rs1.getString(8);
				}

				rs2 = stmt2.executeQuery (	" SELECT "+
  				  " NVL(CLIENT_CODE,'-'), "+
  				  " NVL(FULL_NAME,'-') "+
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

				more = rs2.next();
				if(more){
				m_client_code = rs2.getString(1);
				m_client_name = rs2.getString(2);
				}
							
				rs3 = stmt3.executeQuery(" SELECT NVL(NAME,'-'),NVL(EMP_ID,'-') "+
				" FROM "+m_schema_name+".CO_CO_MAS_USER "+
				" WHERE USER_ID='"+m_username+"'");
				
				more = rs3.next();	
				if(more){
				m_user_name = rs3.getString(1);
				m_emp_id = rs3.getString(2);
				}
				
				rs4 = stmt4.executeQuery("SELECT NVL(DESIGNATION_NAME,'-') "+ 
				" FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION  "+
				" WHERE DESIGNATION_CODE=(SELECT DESIGNATION_CODE FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE  "+
				" WHERE EMP_CODE=upper('"+m_emp_id+"')) ");			
				
				more = rs4.next();				
				if(more){
				m_designation = rs4.getString(1);
				}			

			
			out.println("<blockquote><p style='text-align:left'>");										
			out.println("<br><br><br><br><br>");
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='50%' class='factoring-letter-body'><input class='txt_input6' type='text' name='TXT_DATE' maxlength='100' value='"+m_Letter_date+"' size='100' style='width:100'></td>");
			out.println("<td width='30%' class='factoring-letter-body'></td>");
			if(m_letter_type.equals("P")){
			out.println("<td width='20%' class='factoring-letter-body'><b>REGISTERED POST</b></td></tr>");
			}
			else if(m_letter_type.equals("H")){
			out.println("<td width='20%' class='factoring-letter-body'><b>BY HAND</b></td></tr>");
			}
			else{
			out.println("<td width='20%' class='factoring-letter-body'></td></tr>");
			}
			
			out.println("</table>");
			out.println("<table border='0' width='80%' class='table'>"); 
			if(m_client_type.equals("C")){
			//out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_contact_person+" </td></tr>");
			out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_desig_payment+" </td></tr>");
			}
	  	out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_name+" </td></tr>");
		  out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_add1+"</td></tr>");
		  out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_city_desc+"</td></tr>"); // Added by susitha 07-03-2011
			out.println("<tr><td width='50%'></td><td width='20%' class='factoring-letter-body'  >Client No      </td><td width='2%' class='factoring-letter-body'  > : </td><td width='*%' class='factoring-letter-body'  align='left' > "+m_client_code+"</td></tr>");
		  out.println("<tr><td width='50%'></td><td width='20%' class='factoring-letter-body'  >Debtor No    	 </td><td width='2%' class='factoring-letter-body'  > : </td><td width='*%' class='factoring-letter-body'  align='left' > "+m_debtor_code2+"</td></tr>");
		  out.println("<tr><td width='50%'></td><td width='20%' class='factoring-letter-body'  >Supplier     	 </td><td width='2%' class='factoring-letter-body'  > : </td><td width='*%' class='factoring-letter-body'  align='left' > "+m_client_name+"</td></tr>");
			out.println("</TABLE>");

			out.println("</p></blockquote>");
			out.println("<blockquote><p style='text-align:justify' class='factoring-letter-body'>");				
			out.println("<table border='0' width='80%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body'  style='{text-align:left;}'>Dear Sir/s ,</td></tr>");
			out.println("</TABLE><br>");		
			out.println("</p></blockquote>");
			out.println("<blockquote><p style='text-align:justify' class='factoring-letter-body' >");	
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("We the <b>Lakderana Investments Limited</b> having its registered office at , "+
			" No. 100, Buthgamuwa Road, Rajagiriya and the principle place of business at No. 100, Buthgamuwa Road, Rajagiriya carrying on "+
			" Factoring business under the name and style of <b><i>'Lakderana Factor'</i></b> (referred to as <b><i>'Lakderana Factor'</i></b> herein and in all other "+
			" documents as per the terms of <b>'Factoring Agreement'</b> entered into with <b><i>'Lakderana Factor'</i></b>.)now provide a factoring service for "+
			" your above named supplier, the benefit of whose invoices have been assigned to <b><i>'Lakderana Factor'</i></b> and therefore to whom payments should be made. "+
			" Your co-operation is required in ensuring the following procedure is adhered to:  ");						
			out.println("</td></tr>");									
			out.println("</table>");
			out.println("</p></blockquote>");							
			out.println("<blockquote><p style='text-align:justify' class='factoring-letter-body'  >");				
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("<li style='text-align:justify' class='factoring-letter-body' >Please send your remittances drawn in favour of <b>Lakderana Investments Limited</b> together with your payment advice to us at No:46,48, Dr.N.M.Perera Mawatha, Colombo 08. Your debts will be discharged only on payment made directly to us. "+
									" <br><br> "+
									"<li style='text-align:justify' class='factoring-letter-body' > Queries relating to invoices, credit notes, statements, and the actual goods or services should be directed to the supplier (and copied to <b><i>Orient Factor</i></b> for information purposes only).<br><br>"+
									"<li style='text-align:justify' class='factoring-letter-body' > In view of the benefit of all supplier's invoices having been assigned to <b><i>Orient Factor</i></b>, it is stressed that any existing arrangement for settlement or reduction of an account due to the supplier by contra assignments should be discounted. Should you have a counter claim on the supplier for goods supplied or work done, the relative cost should be invoiced by you in the usual manner and they should then pay you directly.<br><br> "+
									"<li style='text-align:justify' class='factoring-letter-body' > If any invoice assigned to Orient Factor is found to be incorrect in any respect, such discrepancies must be notified to <b><i>Orient Factor</i></b> immediately.<br> ");
			out.println("</td></tr>");								
			
			
			//added by nuwan de silva 16-10-2009
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("<br><b>This agreement will only be cancelled with the written consent of Lakderana Investments Limited 'Lakderana Factor'.</b>");
			out.println("</td></tr>");
						
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("<br>We take this opportunity to thank you in advance for your co-operation which will enable us to maintain a smooth operation with <b>"+m_client_name+"</b>.");
			out.println("</td></tr>");
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("<br>Looking forward to a continued mutually beneficial business relationship.");
			out.println("</td></tr>");				
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("<br>Please sign and return the duplicate copy of this letter in acceptance of the above arrangement.");
			out.println("</td></tr>");
			out.println("</table>");																		
			out.println("</p></blockquote>");
			out.println("<blockquote><p style='text-align:justify' class='factoring-letter-body' >");	
			out.println("Yours faithfully,<br>");	
			out.println("Lakderana Investments Limited,<br> ");	
			out.println("Orient Factor, <br><br><br> ");	
			out.println(" ----------------------- <br>");
			out.println(" Authorized Signatory<br>");	
			out.println("</p><p style='text-align:justify' class='factoring-letter-body'  >");										
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println(" I/We hereby agree to the above terms and conditions and confirm that I/We have not received any written notice that my/our debts owing to <b>"+m_client_name+"</b> have been assigned to a third party.");
 			out.println("</td></tr>");									
			out.println("</table>");																					
			out.println("</p><p style='text-align:justify' class='factoring-letter-body'><br><br><br>");										
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='30%' align='left' >--------------------------------</td>");
			out.println("<td width='5%' align='left' ></td>");
			out.println("<td width='20%' align='center' class='factoring-letter-body' >------------------------------</td>");
			out.println("<td width='5%' align='left' ></td>");
			out.println("<td width='20%' align='center' class='factoring-letter-body' >------------------------------</td>");
			out.println("</tr>");
			out.println("<tr><td width='30%' align='left' class='factoring-letter-body' >Name and Designation</td>");
			out.println("<td width='5%' align='left' ></td>");
			out.println("<td width='20%' align='center' class='factoring-letter-body' >Rubber Stamp/Signature</td>");
			out.println("<td width='5%' align='left' ></td>");
			out.println("<td width='20%' align='center' class='factoring-letter-body' >Date</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</p></blockquote>");	
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
