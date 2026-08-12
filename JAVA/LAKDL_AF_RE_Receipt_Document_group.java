import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Receipt_Document_group extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt_remarks,stmt_remarks1;
	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs_remarks,rs_remarks1;
	public ResultSet rs1,rs2,rs3;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date;
	public String m_c_code="",m_add1="",m_add2="",m_name="",m_city_desc="",m_due_date;
	public String m_receipt_no,m_client_no,m_no_of_due_date,m_finance_no;
	public String m_print,m_inv_type,m_inv_no,m_vat_reg_no="",m_vat_reg_date,m_value_date;
	public double m_amount_due;
	public double m_gross_rent;
	public String m_LAKDL_vat_no="",m_email="";
	public String m_vat_precentage="";
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			int m_count=0;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			m_chksql = req.getParameter("chksql");
			m_receipt_no = req.getParameter("receipt_no");		
			m_client_no = req.getParameter("client_no");		
			m_print = req.getParameter("print");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();			
			
			stmt_remarks=conn.createStatement();
			stmt_remarks1=conn.createStatement();
			
			
			rs = stmt.executeQuery(" SELECT "+
				" UPPER(NVL(COMPANY_NAME,' ')), "+
				" UPPER(NVL(ADDRESS1,' ')), "+
				" UPPER(NVL(ADDRESS2,' ')), "+
				" UPPER(NVL(CITY,' ')), "+
				" NVL(TEL_NO,' '), "+
				" NVL(FAX_NO,' '),  "+
				" NVL(VAT_RATE,0), "+
				" NVL(VAT_REG_NO,' '), "+
				" NVL(EMAIL, ' ') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			boolean  more = rs.next();		
			
			if(more)
			{
				m_orient_name=rs.getString(1);
				m_orient_add1=rs.getString(2);
				m_orient_add2=rs.getString(3);
				m_orient_city_name=rs.getString(4);
				m_orient_tel_no=rs.getString(5);
				m_orient_fax_no=rs.getString(6);
				m_vat_precentage=rs.getString(7);			
				m_LAKDL_vat_no=rs.getString(8);			
				m_email=rs.getString(9);			
			}
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}					
			/*else if (m_chksql.trim().equals("main_page")) {

			out.println("<html><head>"); 
			out.println("<title>Receipt Document</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");			
			out.println("<script>");
			out.println("function save_data(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Receipt_Document?chksql=save_page&receipt_no="+m_receipt_no+"&client_no="+m_client_no+"&scr_name=AF_RE_SETTELMENT\";"); 
		  out.println(" window.location.href=m_url;"); 
			out.println("window.print();");
			out.println("}");
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
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
		
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");								
				more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}

				rs1 = stmt1.executeQuery (	" SELECT "+
  		  " CLIENT_CODE, "+
  		  " UPPER(FULL_NAME), "+
  		  " NVL(UPPER(ADDRESS1),'ADD1'), "+
  		  " NVL(UPPER(ADDRESS2),'ADD2'), "+
  		  " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ,"+
				" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  		  " WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				more = rs1.next();
				if(more){
				m_c_code=rs1.getString(1);
				m_name=rs1.getString(2);
				m_add1=rs1.getString(3);
				m_add2=rs1.getString(4);
				m_city_desc=rs1.getString(5);
				m_vat_reg_no=rs1.getString(6);
				}
				
				
				if(!more){
				
				rs1 = stmt1.executeQuery (	" SELECT "+
  		  " GROUP_CODE, "+
  		  " UPPER(GROUP_NAME), "+
  		  " NVL(UPPER(GROUP_ADDRESS),'ADD1') "+
				" FROM "+m_schema_name+".AF_RE_PRO_GROUP_INVOICES "+
  		  " WHERE GROUP_CODE='"+m_client_no+"' ");
				
				more = rs1.next();
				if(more){
				m_c_code=rs1.getString(1);
				m_name=rs1.getString(2);
				m_add1=rs1.getString(3);
				m_add2="";
				m_city_desc="";
				m_vat_reg_no="";
				}

				
				
				}
				
				
				
   			out.println("<font size=2><p style='text-align:	center'>");						
				out.println("<table border='0' width='100%' class='table' style='text-align:	center'>"); 		
				out.println("<tr><td width='*%'class='rep-body'><b>Receipt Document</b></td></tr>");
				out.println("</TABLE>");		
				out.println("</font></p>");
				out.println("<blockquote><font size=2><p style='text-align:left'>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body' >"+m_orient_name+".</td></tr>");	
				out.println("</table>");
				out.println("<table border='0' width='90%' class='table'>"); 		
		 		out.println("<tr><td width='*%' class='rep-body' >"+m_orient_add1+","+m_orient_add2+","+m_orient_city_name+",SRI LANKA.</td></tr>");
  			out.println("</TABLE>");				
			  out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=2><p style='text-align:left'>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='70%'class='rep-body'>Receipt No : "+m_receipt_no+"</td><td width='30%' class='rep-body' align='right'>Date &nbsp;:"+m_Letter_date+"</td></tr>");
				out.println("</table>");
				out.println("<table border='0' width='90%' class='table'>");		
				out.println("<tr><td width='*%' class='rep-body' >Received with thanks from : "+m_name+"</td></tr>");	
								
  			out.println("</TABLE>");				
			  out.println("</font></p></blockquote>");
			  out.println("<br><br><br>");

 				rs2 = stmt2.executeQuery (" SELECT  NVL(REC_NO,'-'), "+//1
    		"	NVL(CHEQUE_NO,'-'), "+//2
  			" NVL(PAYER_BRANCH_CODE,'-'), "+//3
  			" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+//4
  			" NVL(OTH_COMMENTS,'-'), "+//5
  			" NVL(REC_AMOUNT,0), "+//6
  			" NVL(PAYER_ACC_NO,'-'), "+//7
  			" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),"+//8
  			" CLIENT_CODE "+//9
 				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
 				" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");
  

				more = rs2.next();	
			
				out.println("<blockquote><font size=2><p style='text-align:left'>");
				out.println("<table border='0' width='90%' class='table'>");	
				out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
				out.println("</table>");
		 		out.println("<table border='0' width='90%' class='table'>");//<td width='5%' class='rep-body'>S1No</td>	
				out.println("<tr><td width='15%' class='rep-body'>Receipt No</td>");
				out.println("<td width='12%' class='rep-body'>Cheque No</td>");
				out.println("<td width='10%' class='rep-body'>Bank & Branch</td>");
				out.println("<td width='15%' class='rep-body' >Description</td>");
				out.println("<td width='10%' class='rep-body' style='text-align:right'>Amount Rs.</td></tr>");
				out.println("</table>");
				out.println("<table border='0' width='90%' class='table'>");	
				out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
				out.println("</table>");
			
				int i=1;
				double sum_amount=0;
				int int_sum_amount=0;
				while(more)
				{
					out.println("<table border='0' width='90%' class='table'>");//<td width='5%' class='rep-body'>"+i+"</td>	
					out.println("<tr><td width='15%' class='rep-body'>"+rs2.getString(1)+"</td>");
					out.println("<td width='12%' class='rep-body'>"+rs2.getString(2)+"</td>");
					out.println("<td width='10%' class='rep-body'>"+rs2.getString(4)+"</td>");
					out.println("<td width='15%' class='rep-body'>"+rs2.getString(5)+"</td>");
					
					out.println("<td width='10%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(6))+" </td></tr>");
					out.println("</table>");
					i=i+1;
					sum_amount =sum_amount+rs2.getDouble(6);
					int_sum_amount =int_sum_amount+rs2.getInt(6);
    		 	more=rs2.next();
				}
			out.println("<table border='0' width='90%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			//-----------------------------------------------------------------------------------------------------
			//---Added by delanjali--------------------------------------------------------------------------------------------------
			rs_remarks=stmt_remarks.executeQuery ("SELECT REC_NO,GROUP_REC_NO,NVL(OTH_COMMENTS,'-'),NVL(REC_AMOUNT,0) "+
			"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
			"WHERE UPPER(REC_NO)=UPPER('"+m_receipt_no+"') ");
			
			boolean more_remarks=rs_remarks.next();
			String m_group_rec_no="";
			String m_rec_no="";
			String m_comments="";
			
			out.println("<table border='0' width='90%' class='table'>");	
			int u=0;
			
			while(more_remarks){
			m_rec_no=rs_remarks.getString(1);
			m_group_rec_no=rs_remarks.getString(2);
			m_comments=rs_remarks.getString(3);

			if(m_group_rec_no==null){
			
										rs_remarks1=stmt_remarks1.executeQuery ("SELECT REC_NO,GROUP_REC_NO,NVL(OTH_COMMENTS,'-'),NVL(REC_AMOUNT,0) "+
																														"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
																														"WHERE UPPER(GROUP_REC_NO)=UPPER('"+m_rec_no+"') ");
											boolean more_remarks1=rs_remarks1.next();
														while(more_remarks1){
														if(u==0){
													  out.println("<tr>");
														out.println("<td width='25%' class='rep-body'><u>Sub Receipt Details</td>");
														out.println("<td width='25%' class='rep-body'><u>Sub Receipt Amount</td>");
														out.println("<td width='*%' class='rep-body'><u>Remarks</td>");
														out.println("</tr>");
														}

														out.println("<tr>");

														out.println("<td width='25%' class='rep-body'>"+rs_remarks1.getString(1)+"</td>");
														out.println("<td width='25%' class='rep-body'>"+rs_remarks1.getDouble(4)+"</td>");
														out.println("<td width='*%' class='rep-body'>"+rs_remarks1.getString(3)+"</td>");
														out.println("</tr>");

														more_remarks1=rs_remarks1.next();
														u=u+1;

														}
				}else{
				
				out.println("<tr>");
														rs_remarks1=stmt_remarks1.executeQuery ("SELECT REC_NO,GROUP_REC_NO,NVL(OTH_COMMENTS,'-'),NVL(REC_AMOUNT,0) "+
																														"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
																														"WHERE UPPER(REC_NO)=UPPER('"+m_group_rec_no+"') ");
											boolean more_remarks1=rs_remarks1.next();
														while(more_remarks1){
														if(u==0){
													  out.println("<tr>");
														out.println("<td width='25%' class='rep-body'><u>Group Receipt Details</td>");
														out.println("<td width='25%' class='rep-body'><u>Group Receipt Amount</td>");
														out.println("<td width='*%' class='rep-body'><u>Remarks</td>");
														out.println("</tr>");
														}
												
														out.println("<tr>");
														out.println("<td width='25%' class='rep-body'>"+m_group_rec_no+"</td>");
														out.println("<td width='25%' class='rep-body'>"+rs_remarks1.getDouble(4)+"</td>");
														out.println("<td width='*%' class='rep-body'>"+rs_remarks1.getString(3)+"</td>");
														out.println("</tr>");

														more_remarks1=rs_remarks1.next();
														u=u+1;

														}

}					

			
			more_remarks=rs_remarks.next();

			}
			out.println("</table>");
			//-----------------------------------------------------------------------------------------------------
			out.println("<table border='0' width='90%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			out.println("<table border='0' width='90%' class='table'>");//<td width='5%' class='rep-body'>"+i+"</td>	
			out.println("<tr><td width='15%' class='rep-body'></td>");
			out.println("<td width='12%' class='rep-body'></td>");
			out.println("<td width='10%' class='rep-body'></td>");
			out.println("<td width='15%' class='rep-body' style='text-align:right'>Total Amount</td>");
			out.println("<td width='10%' class='rep-body' style='text-align:right'>"+nf.format(sum_amount)+" </td></tr>");
			out.println("</table>");
			out.println("<br><br>");
			out.println("<table border='0' width='90%' class='table'>");	
			out.println("<tr><td width='5%' class='rep-body'></td><td width='*%' class='rep-body'><b>STAMP DUTY HAS BEEN COMPOUNDED IN TERMS OF SECTION 7 OF<br> THE STAMP DUTY(SPECIAL PROVISIONS) ACT NO.12 OF 2006</b></td><td width='16%' class='rep-body'></td></tr>");
			out.println("</table>");
			out.println("<br><br>");
			out.println("<table border='0' width='90%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			
					 String m_val = nf.format(sum_amount);
					 String m_val2 = m_sn_methods.met_unformat_number(m_val);
					 int m_dot=m_val.indexOf(".");
					 int m_length=m_val.length();
					 String m_cents=m_val.substring(m_dot+1,m_length);
					 //int m_int = 	Integer.parseInt(sum_amount);
					 String m_int_val= int_sum_amount+"";	
						
					
					if(!m_cents.equals("00")) {	
					out.println("<table border='0' width='90%' class='table'>");		
					out.println("<tr><td width='*%' class='rep-body'>Amount in words</td></tr>");
					out.println("</table>");
					out.println("<table border='0' width='90%' class='table'>");		
					out.println("<tr><td width='*%' class='rep-body'> ");
				  out.println(" RUPEES  "+m_sn_methods.numbersToChar(m_val2).toUpperCase()+" ONLY  "); //@@ "+numbersToChar_inside(m_val2)+"
					out.println("</td></tr>");
					out.println("</table>");
					}
					else {
					out.println("<table border='0' width='90%' class='table'>");		
					out.println("<tr><td width='*%' class='rep-body'>Amount in words</td></tr>");
					out.println("</table>");
					out.println("<table border='0' width='90%' class='table'>");		
					out.println("<tr><td width='*%' class='rep-body'> ");
					out.println(" RUPEES "+m_sn_methods.numbersToChar(m_int_val).toUpperCase()+" ONLY  "); //@@ "+numbersToChar_inside(m_val2)+"
					out.println("</td></tr>");
					out.println("</table>");
					}
					
					
			out.println("<table border='0' width='90%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
			out.println("</table>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr><td width='*%' class='rep-body' align='right'>Please inform any discrepancies within 10 days</td></tr>");
			out.println("</table>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr><td width='*%' class='rep-body' align='right'>Receipt is valid subject to realisation of cheque/s</td></tr>");
			out.println("</table>");
			out.println("<br><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='30%' class='rep-body' style='{text-align:justify;}'>");
			out.println(" ................................");
			out.println("</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>");
			out.println(" IF THE CONTRACT IS ALREADY TERMINATED ");
			out.println("</td>");
			out.println("</tr></table>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='30%' class='rep-body' style='{text-align:justify;}'>");
    	out.println(" Authorized Signatory ");	
			out.println("</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>");
			out.println(" PAYMENT IS ACCEPTED WITHOUT PREJUDICE ");
			out.println("</td>");
			out.println("</tr></table>");
			out.println("<br><br><br><br><br>");
			out.println("<blockquote><font size=2><p style='text-align:left'>");						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >If undelivered please return to :</td></tr>");	
			out.println("</table>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_orient_name+".</td></tr>");	
			out.println("</table>");
			out.println("<table border='0' width='90%' class='table'>"); 		
		 	out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_orient_add1+","+m_orient_add2+","+m_orient_city_name+",SRI LANKA.</td></tr>");
			out.println("</table>");	
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >Tel :"+m_orient_tel_no+" Fax :"+m_orient_fax_no+"  Email :"+m_email+"</td></tr>");
			out.println("</table>");	
			out.println("</font></p></blockquote>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='10%' class='rep-body'></td><td width='*%'class='rep-body'><b>To &nbsp;</b> :</td></tr>");
			out.println("</table>");	
			out.println("<blockquote><font size=2><p style='text-align:left'>");						
			out.println("<table border='0' width='90%' class='table'>"); 
			out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
		  if(m_add1.equals("ADD1")){ 
			}else{
			out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_add1+"</td></tr>");
		  }
			if(m_add2.equals("ADD2")){ 
			}else{
		  out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_add2+"</td></tr>");
			}
			if(m_city_desc.equals("CITY")){ 
			}else{			
			out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
			}
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");

			out.println("</font></p></blockquote>");		
			out.println("</form></body></html>");
			} */
			
			
			
			
			
			
			else if (m_chksql.trim().equals("main_page")) {
				
				String m_comments="",m_payer_branch="",m_cheque_cash_type="";
				
				
				out.println("<html><head>"); 
				out.println("<title>Receipt Document</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");			
				out.println("<script>");
				out.println("function save_data(){");
				out.println("m_table.innerHTML=\"\" ");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Receipt_Document?chksql=save_page&receipt_no="+m_receipt_no+"&client_no="+m_client_no+"&scr_name=AF_RE_SETTELMENT\";"); 
				out.println(" window.location.href=m_url;"); 
				out.println("window.print();");
				out.println("}");
				out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else
				{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
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
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");								
				more = rs.next();
				if(more){
					m_Letter_date=rs.getString(1);
				}
				
				rs1 = stmt1.executeQuery
					(	" SELECT "+
					" CLIENT_CODE, "+
					" UPPER(FULL_NAME), "+
					" NVL(UPPER(ADDRESS1),'ADD1'), "+
					" NVL(UPPER(ADDRESS2),'ADD2'), "+
					" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ,"+
					" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				more = rs1.next();
				if(more){
					m_c_code=rs1.getString(1);
					m_name=rs1.getString(2);
					m_add1=rs1.getString(3);
					m_add2=rs1.getString(4);
					m_city_desc=rs1.getString(5);
					m_vat_reg_no=rs1.getString(6);
				}
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),NVL(OTH_COMMENTS,'-') ,NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-') FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");							
				more = rs.next();
				if(more){
					m_Letter_date=rs.getString(1);
					m_comments=rs.getString(2);
					m_cheque_cash_type=rs.getString(3);
					m_payer_branch=rs.getString(4);
				}
				
				
				
				String m_address="";
				
				if(m_add1.equals("ADD1")){
					m_address="";
				}
				else{
					m_address=m_add1;
				}
				if(!m_add2.equals("ADD2")){
					m_address=m_address+","+m_add2;
				}
				
				
				if(!more){
					
					rs1 = stmt1.executeQuery (	" SELECT "+
						" GROUP_CODE, "+
						" UPPER(GROUP_NAME), "+
						" NVL(UPPER(GROUP_ADDRESS),'ADD1') "+
						" FROM "+m_schema_name+".AF_RE_PRO_GROUP_INVOICES "+
						" WHERE GROUP_CODE='"+m_client_no+"' ");
					
					more = rs1.next();
					if(more){
						m_c_code=rs1.getString(1);
						m_name=rs1.getString(2);
						m_add1=rs1.getString(3);
						m_add2="";
						m_city_desc="";
						m_vat_reg_no="";
					}
				}
				
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='15%'class='rep-body'>&nbsp;</td><td width='50%'class='rep-body'>"+m_receipt_no+"</td><td width='10%' class='rep-body' align='right'>&nbsp;</td><td width='20%' class='rep-body' align='right'>"+m_Letter_date+"</td></tr>");
				out.println("</table>");
				out.println("<table border='0' width='90%' class='table'>");		
				out.println("<tr><td width='20%' class='rep-body' >&nbsp;</td><td width='*%' class='rep-body' >"+m_name+"</td></tr>");	
				out.println("</TABLE>");				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				
				/*rs2 = stmt2.executeQuery (" SELECT  NVL(REC_NO,'-'), "+//1
		"	NVL(CHEQUE_NO,'-'), "+//2
			" NVL(PAYER_BRANCH_CODE,'-'), "+//3
			" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+//4
			" NVL(OTH_COMMENTS,'-'), "+//5
			" NVL(REC_AMOUNT,0), "+//6
			" NVL(PAYER_ACC_NO,'-'), "+//7
			" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),"+//8
			" CLIENT_CODE "+//9
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");
				*/
				
				double m_over_payment=0;
				rs2 = stmt2.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_REC_UNALLO_AMOUNT('"+m_receipt_no+"'),0) FROM DUAL ");
				more = rs2.next();
				if(more){
					m_over_payment=rs2.getDouble(1);
				}
				
				
				
				rs2 = stmt2.executeQuery (" SELECT  NVL(REC_NO,'-'), "+
					" DECODE(SETTLE_MODE,'CASH','Cash',CHEQUE_NO), "+
					" NVL(PAYER_BRANCH_CODE,'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
					" NVL(REC_AMOUNT,0), "+
					" DECODE(SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash','DIR_DEP','Derect Deposit','STD_ORD','Standing Order'), "+
					" NVL(PAYER_ACC_NO,'-'), "+
					" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'), "+
					" CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");
				
				String m_Cheque_no   = "";
				String m_Bank_Branch = "";
				String m_Sett_ref    = "";
				String m_Sett_mod    = "";
				double m_amount      = 0;
				String m_amount_wd   ="";
				
				more = rs2.next();	
				
				if(more){
					m_Cheque_no   = rs2.getString(2);
					m_Bank_Branch = rs2.getString(4);
					m_amount      = rs2.getDouble(5);
					m_amount_wd   = rs2.getString(5);
					m_Sett_mod    = rs2.getString(6); 
					m_Sett_ref    = rs2.getString(2);
				}
				out.println("<br><br><br>");
				String flag ="A";
				int i=1;
				double sum_amount=0;
				int int_sum_amount=0;
				
				//more = rs2.next();	
				
				/*	 rs2 = stmt2.executeQuery (" SELECT  NVL(REC_NO,'-'), "+//1
				"	NVL(CHEQUE_NO,'-'), "+//2
					" NVL(PAYER_BRANCH_CODE,'-'), "+//3
					" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+//4
					" NVL(OTH_COMMENTS,'-'), "+//5
					" NVL(REC_AMOUNT,0), "+//6
					" NVL(PAYER_ACC_NO,'-'), "+//7
					" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),"+//8
					" CLIENT_CODE "+//9
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");
			*/
				
				rs2 = stmt2.executeQuery (" SELECT  NVL(REC_NO,'-'), "+
					" DECODE(SETTLE_MODE,'CASH','Cash',CHEQUE_NO), "+
					" NVL(PAYER_BRANCH_CODE,'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
					" NVL(REC_AMOUNT,0), "+
					" DECODE(SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash','DIR_DEP','Derect Deposit','STD_ORD','Standing Order'), "+
					" NVL(PAYER_ACC_NO,'-'), "+
					" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'), "+
					" CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");
				
				more = rs2.next();	
				out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' height='360px'>");
				out.println("<tr><td width='100%' class='rep-body' valign='top'>");
				out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' >");
				
				if(more){
					while(more){
						out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>"+rs2.getString(1)+"</td>");
						out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' >"+rs2.getString(2)+"</td>");
						out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' >"+rs2.getString(3)+"</td>");
						out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >"+rs2.getString(4)+"</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(5))+"</td></tr>");
						i=i+1;
						sum_amount =sum_amount+rs2.getDouble(5);
						int_sum_amount =int_sum_amount+rs2.getInt(5);
						more=rs2.next();				
					}
					/*if(m_over_payment>0){
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >Over Payment</td>");
					out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(m_over_payment)+"</td></tr>");
					sum_amount =sum_amount+m_over_payment;
					}*/
					
					rs_remarks=stmt_remarks.executeQuery ("SELECT REC_NO,GROUP_REC_NO,NVL(OTH_COMMENTS,'-'),NVL(REC_AMOUNT,0) "+
						"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						"WHERE UPPER(REC_NO)=UPPER('"+m_receipt_no+"') ");
					
					boolean more_remarks=rs_remarks.next();
					String m_group_rec_no="";
					String m_rec_no="";
					
					int u=0;
					
					while(more_remarks){
						m_rec_no=rs_remarks.getString(1);
						m_group_rec_no=rs_remarks.getString(2);
						m_comments=rs_remarks.getString(3);
						
						if(m_group_rec_no==null){
							
							rs_remarks1=stmt_remarks1.executeQuery ("SELECT REC_NO,GROUP_REC_NO,NVL(OTH_COMMENTS,'-'),NVL(REC_AMOUNT,0) "+
								"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
								"WHERE UPPER(GROUP_REC_NO)=UPPER('"+m_rec_no+"') ");
							boolean more_remarks1=rs_remarks1.next();
							while(more_remarks1){
								out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>"+rs_remarks1.getString(1)+"</td>");
								out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
								out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
								out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >"+rs_remarks1.getString(3)+"</td>");
								out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(rs_remarks1.getDouble(4))+"</td></tr>");
								more_remarks1=rs_remarks1.next();
							}
						}else{
							
							rs_remarks1=stmt_remarks1.executeQuery ("SELECT REC_NO,GROUP_REC_NO,NVL(OTH_COMMENTS,'-'),NVL(REC_AMOUNT,0) "+
								"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
								"WHERE UPPER(REC_NO)=UPPER('"+m_group_rec_no+"') ");
							boolean more_remarks1=rs_remarks1.next();
							while(more_remarks1){
								
								/*if(u==0){
								out.println("<tr>");
								out.println("<td width='25%' class='rep-body'><u>Group Receipt Details</td>");
								out.println("<td width='25%' class='rep-body'><u>Group Receipt Amount</td>");
								out.println("<td width='*%' class='rep-body'><u>Remarks</td>");
								out.println("</tr>");
								}*/
								/*out.println("<tr>");
								out.println("<td width='25%' class='rep-body'>"+m_group_rec_no+"</td>");
								out.println("<td width='25%' class='rep-body'>"+rs_remarks1.getDouble(4)+"</td>");
								out.println("<td width='*%' class='rep-body'>"+rs_remarks1.getString(3)+"</td>");
								out.println("</tr>");
								*/
								out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>"+m_group_rec_no+"</td>");
								out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
								out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
								out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >"+rs_remarks1.getString(3)+"</td>");
								out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(rs_remarks1.getDouble(4))+"</td></tr>");
								
								more_remarks1=rs_remarks1.next();
								u=u+1;
							}
						}					
						more_remarks=rs_remarks.next();
					}
					
				}
				/*else if(flag.equals("A")){
				
				String charge_desc="Initial Payment / Document Charges / Insuarance / Advance Monthly Instruments / RMV Charges  / "+
				"Semi luxury / Luxury Tax / Governement Levy / Insuarance Claims / Lease Receivable - Settlement / ODI / Sale Price ";
				out.println("<tr><td width='80%'  clospan=4 align='left' class='rep-body' style='text-align:left'>"+charge_desc+"</td>");
				out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(m_amount)+"</td></tr>");
				out.println("<tr><td width='80%'  clospan=4 align='left' class='rep-body' style='text-align:left'>AGREEMENT NO: "+m_comments+"</td>");
				out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'></td></tr>");
				i=i+1;
				sum_amount =sum_amount+m_amount;
				} */ 
				
				
				
				out.println("</table>");
				out.println("</td></tr></table>");
				out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table'>");
				out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
				out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
				out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
				out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
				out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>"+nf.format(sum_amount)+"</td></tr>");
				out.println("</table>");
				out.println("");
				
				out.println("<BR><BR>");
				out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table'>");
				out.println("<tr>");
				out.println("<td width='20%' class='rep-body' >&nbsp;</td>");
				out.println("<td width='*%' class='rep-body' >RUPEES "+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY</td>");
				out.println("</tr>");	
				out.println("</TABLE>");		
				
				//COMMENT ON 02-01-2008 --------------------------------------------------------------
				out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
				out.println("<blockquote><font size=2><p style='text-align:left'>");						
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
				
				out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_address+"</td></tr>");
				if(m_city_desc.equals("CITY")){ //Added by Chandana on 11/06/2007
				}else{			
					out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
				}
				out.println("</TABLE>");
				
				out.println("</font></p></blockquote>");		
				out.println("</form></body></html>");
				
			}
			
			
			out.flush();
		}
		catch (Exception ex) {
		
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
