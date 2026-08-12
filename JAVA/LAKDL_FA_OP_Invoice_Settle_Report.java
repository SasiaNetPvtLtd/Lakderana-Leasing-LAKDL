
//SCREEN NAME:Invoices Settlement Report
//DEVELOPED BY MAHELA FOR FACTORING ON 08-02-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_OP_Invoice_Settle_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;
	public String m_contact_person,m_contact_design;
	
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
			
			String m_debtor_code="-",m_client_name="-",m_invoice_seq_no="-",m_batch_no="-",m_return_no="-",m_facility_no="-";
			String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-";
			double m_chq_amount=0;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_client_no=req.getParameter("client_no");		
			m_print=req.getParameter("print");
			m_facility_no=req.getParameter("facility_no");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Invoices Settlement Report</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_save_Invoice_Settle_Rep?client_no="+m_client_no+"&debtor_code="+m_debtor_code+"&print="+m_print+"&facility_no="+m_facility_no+"\";");  
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
			out.println("<br>");
			out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
		
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more){
				m_Letter_date=rs.getString(1);
				}
  		 
				/*rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE+7,'DD-MM-YYYY') FROM DUAL ");								
				more = rs.next();
				
				if(more){
				m_deadline_date=rs.getString(1);
				}*/
				
				rs1 = stmt1.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+//1
  				  " NVL(FULL_NAME,'-'), "+//2
  				  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
  				  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
  				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'), "+//6
						" NVL(DESIGNATION_PAYMENT,'-') "+//7
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs1.next();
					if(more){
						m_c_code=rs1.getString(1);
						m_name=rs1.getString(2);
						m_add1=rs1.getString(3);
						m_add2=rs1.getString(4);
						m_city_desc=rs1.getString(5);
						m_contact_person=rs1.getString(6);
						m_contact_design=rs1.getString(7);
					}


				/*rs2 = stmt2.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+
  				  " NVL(FULL_NAME,'-') "+
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs2.next();
						if(more){
							m_client_name = rs2.getString(2);
						 }*/

			
			out.println("<blockquote><font size=2><p style='text-align:left'>");										
			//out.println("<br><br><br><br><br><br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%'class='rep-body'>Factoring Division of</td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body'>Lakderana Investments Limited</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body'>4No. 100,Buthgamuwa Road,</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body'> Rajagiriya.</td></tr>");
			out.println("<tr><td width='*%'class='rep-body'>"+m_Letter_date+"</td></tr>");
			out.println("</TABLE>");
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
	    //out.println("<tr><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'>"+m_Letter_date+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:12px;text-align:left;}'>"+m_contact_person+",</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{font:12px;text-align:left;}'>"+m_contact_design+",</td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body' style='{font:12px;text-align:left;}'>"+m_name+",</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' style='{font:12px;text-align:left;}'>"+m_add1+",</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' style='{font:12px;text-align:left;}'>"+m_add2+",</td></tr>");
      out.println("<tr><td width='*%' class='rep-body' style='{font:12px;text-align:left;}'>"+m_city_desc+".</td></tr>");
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");
			//out.println("<br>");
			out.println("<blockquote><font size=2><p style='text-align:justify'>");				
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:center;}'><b><u>Invoices Settlement Report</u></b></td></tr>");
			out.println("</TABLE><br>");		
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='50%' class='rep-body' style='{text-align:left;}'>Client code :- "+m_client_no+"</td>");
			out.println("</tr>");
			out.println("</TABLE><br>");		
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir,</td></tr>");
			out.println("</TABLE><br>");		
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:justify;}'>");
			out.println("We inform to you that the following invoice amounts were settled against your payments.");
			out.println("</td></tr></table>");						
			out.println("</font></p></blockquote>");				
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body' >");				

					
				 /*rs2=stmt2.executeQuery ("SELECT DISTINCT "+
  				 " A.BATCH_NO, "+//1
  				 " TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//2
  				 " A.DEBTOR_CODE, "+//3
  				 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//4
  				 " D.CHEQUE_NO, "+//5
  				 " D.PAYER_BRANCH_CODE, "+//6
  				 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(D.PAYER_BRANCH_CODE), "+//7
  				 " NVL(D.CHEQUE_AMOUNT,0), "+//8
  				 " NVL(A.INVOICE_NO,'-'), "+//9
  				 " NVL(A.INVOICE_AMOUNT,0), "+//10
  				 " NVL(A.SETTLE_AMOUNT,0), "+//11
  				 " NVL(A.BALANCE_AMOUNT,0) "+//12
 				 "	FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO C,"+
 				 "	"+m_schema_name+".FA_OP_PRO_POD_CHEQUES D "+
 				 "	WHERE A.BATCH_NO=B.BATCH_NO "+
 				 "	AND C.POD_REF_NO=D.POD_REF_NO "+
 				 "	AND B.FACILITY_NO=C.FACILITY_NO AND B.CLIENT_CODE=C.CLIENT_CODE "+
 				 "	AND B.FACILITY_NO='"+m_facility_no+"' AND B.CLIENT_CODE='"+m_client_no+"' ");*/
				 
					
				 rs2=stmt2.executeQuery ("SELECT DISTINCT "+
  			 " A.BATCH_NO, "+//1
  			 " TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//2
  			 " A.DEBTOR_CODE, "+//3
  			 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//4
  			 " E.CHEQUE_NO, "+//5
  			 " E.PAYER_BRANCH_CODE, "+//6
  			 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(E.PAYER_BRANCH_CODE), "+//7
  			 " E.REC_AMOUNT, "+//8
  			 " A.INVOICE_NO, "+//9
  			 " NVL(A.INVOICE_AMOUNT,0), "+//10
  			 " NVL(A.SETTLE_AMOUNT,0), "+//11
  			 " NVL(A.BALANCE_AMOUNT,0),"+//12
  			 " E.RECEIPT_TYPE, "+//13
  			 " D.CHEQUE_NO, "+//14
  			 " D.PAYER_BRANCH_CODE, "+//15
  			 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(D.PAYER_BRANCH_CODE), "+//16
  			 " NVL(D.CHEQUE_AMOUNT,0) "+//17
 			 "	FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO C, "+
 			 "	"+m_schema_name+".FA_OP_PRO_POD_CHEQUES D,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT E "+
 			 "	WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.INVOICE_STATUS='CONF' "+	
 			 "	AND B.FACILITY_NO=E.FACILITY_NO AND B.CLIENT_CODE = E.CLIENT_CODE "+
 			 "	AND C.POD_REF_NO=D.POD_REF_NO(+) "+
 			 "	AND B.FACILITY_NO=C.FACILITY_NO(+) AND B.CLIENT_CODE = C.CLIENT_CODE(+) "+
 			 "	AND B.FACILITY_NO='"+m_facility_no+"' AND B.CLIENT_CODE='"+m_client_no+"' ");
						
					more = rs2.next(); 

			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='10%' class='rep-body' style='{text-align:left;}'><b><u>Date</u></b></td>");
			out.println("<td width='10%' class='rep-body' style='{text-align:left;}'><b><u>Debtor Code</u></b></td> ");
			out.println("<td width='15%' class='rep-body' style='{text-align:left;}'><b><u>Debtor Name</u></b></td> ");
			out.println("<td width='10%' class='rep-body' style='{text-align:left;}'><b><u>Cheque/voucher No</u></b></td> ");
			out.println("<td width='12%' class='rep-body' style='{text-align:left;}'><b><u>Bank/Branch Name</u></b></td> ");
			out.println("<td width='12%' class='rep-body' style='{text-align:right;}'><b><u>Cheque Amt</u></b></td> ");
			out.println("<td width='3%' class='rep-body'></td> ");
			out.println("<td width='10%' class='rep-body' style='{text-align:left;}'><b><u>Invoice No</u></b></td> ");
			out.println("<td width='12%' class='rep-body' style='{text-align:right;}'><b><u>Invoice Amt</u></b></td> ");
			out.println("<td width='3%' class='rep-body'></td> ");
			out.println("<td width='15%' class='rep-body' style='{text-align:right;}'><b><u>Settled Amt</u></b></td> ");
			out.println("<td width='3%' class='rep-body'></td> ");
			out.println("<td width='15%' class='rep-body' style='{text-align:right;}'><b><u>Balance Amt</u></b></td> ");
			out.println("</tr>");
			
			
				while(more){
					out.println("<tr ><td width='10%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(2)+"</td>");
					out.println("<td width='10%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(3)+"</td> ");
					out.println("<td width='15%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(4)+"</td> ");
					if(rs2.getString(13).equals("POD")){
					out.println("<td width='10%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(14)+"</td> ");
					out.println("<td width='12%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(16)+"</td> ");
					out.println("<td width='12%' class='rep-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(17))+"</td> ");
					}
					else {
					out.println("<td width='10%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(5)+"</td> ");
					out.println("<td width='12%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(7)+"</td> ");
					out.println("<td width='12%' class='rep-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(8))+"</td> ");
					}
					out.println("<td width='3%' class='rep-body'></td> ");
					out.println("<td width='10%' class='rep-body' style='{text-align:left;}'>"+rs2.getString(9)+"</td> ");
					out.println("<td width='12%' class='rep-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(10))+"</td> ");
					out.println("<td width='3%' class='rep-body'></td> ");
					out.println("<td width='15%' class='rep-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(11))+"</td> ");
					out.println("<td width='3%' class='rep-body'></td> ");
					out.println("<td width='15%' class='rep-body' style='{text-align:right;}'>"+nf.format(rs2.getDouble(12))+"</td> ");
					//out.println("<td width='3%' class='rep-body'></td> ");
					//out.println("<td width='*%'></td> ");
					out.println("</tr>");	
					
			 		more = rs2.next();
				}
			out.println("</TABLE>");	
			out.println("</font></p></blockquote>");		
			out.println("<br><br>");
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:justify;}'>");
			out.println(" If you have any queries pertaining to the above please do not hesitate to  "+
									" contact the undersigned ");
			out.println("</td></tr></table>");												
			out.println("<br><br>");						
			out.println("Thanking you ");
			out.println("<br><br>");						
			out.println("</font></p></blockquote>");				
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("Yours faithfully,<br><br>");	
			out.println(" Factoring Division of <br>");	
			out.println(" Lakderana Investments Limited <br><br><br>");	
			out.println(" ................................<br><br>");
    	//out.println(" Authorized Signatory  <br>");	
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
