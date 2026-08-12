//ID         :
//SCREEN NAME:Document Printing - NIBSM Letter
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 12-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Dishonoured_Cheques_Letter extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs1;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_od_date;
	public double m_amount_due;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
		//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
		// out.println("conn"+conn);
		
		
				//Decaring variables
			
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			double m_NIBSM=0;
			 int m_data_count=0;
		  String m_status ="";
			String m_client_code ="";
			String m_fin_number ="";
			String m_loan_fin_number="";
			String m_opleas_fin_number="";
			String m_hiring_fin_number="";
			String m_hirpur_fin_number="";
			
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			
			
						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
		 
		stmt = conn.createStatement ();
						
		//String m_finance_no = req.getParameter("finance_no");
		m_client_code  = req.getParameter("client_code");
		//String m_client_code ="";		
		String m_document_code	="";	
		String m_print=req.getParameter("print");
			
			
		rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth')||' '|| TO_CHAR(SYSDATE,'MONTH')||''||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
								
		boolean more = rs.next();
		if(more){
		m_Letter_date=rs.getString(1);
		}
				
			
		rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM DUAL ");
			
		more = rs.next();
		if(more){
		m_od_date=rs.getString(1);
		}
			
				
				
					 rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							  more = rs.next();		
											
											if(more)
											{
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_orient_vat_rate=rs.getString(7);			
											}
		
					
					
					  rs = stmt.executeQuery(" SELECT "+
        												" NVL(TITLE,''), "+
																" NVL(FULL_NAME,' '), "+
																" REPLACE(REPLACE(NVL(ADDRESS1,' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(ADDRESS2,' '),'-',' '),'null',' '),"+
                                " "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE), "+
																" CLIENT_CODE "+
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE   CLIENT_CODE = '"+m_client_code+"'");
    
			 more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_client_code=rs.getString(6);
							
				
				}
				
				
				
			rs = stmt.executeQuery("SELECT NVL(FINANCE_NO,' ') "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					 " WHERE CLIENT_CODE='"+m_client_code+"' AND TRANSACTION_TYPE='FINLEASE' AND FINANCE_NO IS NOT NULL "+
					 " GROUP BY FINANCE_NO ");
						
			more = rs.next();	
			
			int i=1;
			while(more){
			m_fin_number = m_fin_number +rs.getString(1)+" ";
			more = rs.next();
			}
			
			
			rs = stmt.executeQuery("SELECT NVL(FINANCE_NO,' ') "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					 " WHERE CLIENT_CODE='"+m_client_code+"' AND TRANSACTION_TYPE='LOANS' AND FINANCE_NO IS NOT NULL "+
					 " GROUP BY FINANCE_NO ");
						
			more = rs.next();	
			
			
			while(more){
			m_loan_fin_number = m_loan_fin_number +rs.getString(1)+" ";
			more = rs.next();
			}
			
			
			rs = stmt.executeQuery("SELECT NVL(FINANCE_NO,' ') "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					 " WHERE CLIENT_CODE='"+m_client_code+"' AND TRANSACTION_TYPE='OPELEASE' AND FINANCE_NO IS NOT NULL "+
					 " GROUP BY FINANCE_NO ");
						
			more = rs.next();	
			
			
			while(more){
			m_opleas_fin_number = m_opleas_fin_number +rs.getString(1)+" ";
			more = rs.next();
			}
			
			
		
		  rs = stmt.executeQuery("SELECT NVL(FINANCE_NO,' ') "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					 " WHERE CLIENT_CODE='"+m_client_code+"' AND TRANSACTION_TYPE='HIRING' AND FINANCE_NO IS NOT NULL "+
					 " GROUP BY FINANCE_NO ");
						
			more = rs.next();	
			
			
			while(more){
			m_hiring_fin_number = m_hiring_fin_number +rs.getString(1)+" ";
			more = rs.next();
			}
		
		
			 rs = stmt.executeQuery("SELECT NVL(FINANCE_NO,' ') "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					 " WHERE CLIENT_CODE='"+m_client_code+"' AND TRANSACTION_TYPE='HIREPURCH' AND FINANCE_NO IS NOT NULL "+
					 " GROUP BY FINANCE_NO ");
						
			more = rs.next();	
			
			
			while(more){
			m_hirpur_fin_number = m_hirpur_fin_number +rs.getString(1)+" ";
			more = rs.next();
			}
			
			
				
			  out.println("<html><head>"); 
				out.println("<title>Acceptance Receipt </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      out.println("<script>");
			
			out.println("function save_data(){");			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Dishonoured_Cheques_Letter?chksql=main_page&client_code="+m_client_code+"&print=FALSE\";");  
		  out.println(" window.location.href=m_url;");		
			out.println("m_table.innerHTML=\"\" ");		
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
			
				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
      
			out.println("<hr>");		
			
			out.println("<blockquote><font size=3><p style='text-align:left'>");					
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"80%\" class='rep-body'><b></b></td>");
			out.println("<td width=\"20%\" class='rep-body'><b>Registered Post</b></td></tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>"+m_Letter_date+"</td></tr>");
			out.println("</table>");
			
		//	out.println("m_fin_number"+m_fin_number);
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' ><b>"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' ><b>"+m_add2+"</td></tr>");
			//out.println("<tr><td width='*%' class='rep-body' ><b>"+m_city_name+"</td></tr>");
			out.println("</table>");	
			
			out.println("<br><br>");
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>Dear Sir,</td></tr>");
			out.println("</table>");
			out.println("<br><br>");
			
					
			String fin_lease="";
			if(!m_fin_number.equals("")){
			fin_lease="Finance Lease Agreement No. "+m_fin_number ;
			}
			
			String fin_loan=""; 
			if(!m_loan_fin_number.equals("")){
			fin_loan="and Loan Agreement No. "+m_loan_fin_number ;
			}
			
			String fin_opel=""; 
			if(!m_opleas_fin_number.equals("")){
			fin_opel="and Operating Lease Agreement No. "+m_opleas_fin_number ;
			}
			
			
			String fin_hiring=""; 
			if(!m_hiring_fin_number.equals("")){
			fin_hiring="and Hiring Agreement No. "+m_hiring_fin_number ;
			}
			
			String fin_hirpur="";
			if(!m_hirpur_fin_number.equals("")){
			fin_hirpur="and Hire Purchase Agreement No. "+m_hirpur_fin_number ;
			}

			
			
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			//out.println("<tr><td width='*%' class='rep-body' ><b><U>Dishonoured cheque(s) drawn in favour of Orient Finance Services Corperation Limited on Master Finance Lease Agreement No. "+m_fin_number+"</U></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' ><b><U>Dishonoured cheque(s) drawn in favour of Orient Finance Services Corperation Limited on Master "+fin_lease+" "+fin_loan+" "+fin_opel+" "+fin_hiring+" "+fin_hirpur+"</U></td></tr>");
			out.println("</table>");
			
			out.println("<br><br>");
			out.println("</font></p></blockquote>");
			
		
	
		 out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
		
		 								
		 String data="We are writing on the instructions of our client "+m_orient_name+" of "+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+". ";  						
								
								
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
						
                  
     data=  "We hereby inform you that the following cheque(s) which had been submitted to our client in settlement of dues under "+
			      "leasing facilities granted by our client has/have been dishonoured on presentment with the following remark(s) ";
		  
			out.println("<br>");									
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'><tr>"); 		
			out.println("<td width='20%' class='rep-body' style='text-align:center' >Cheque No.</td>"); //justify
			out.println("<td width='15%' class='rep-body' style='text-align:center' >Date</td>");
			out.println("<td width='15%' class='rep-body' style='text-align:center' >Bank</td>");
			out.println("<td width='15%' class='rep-body' style='text-align:right' >Amount</td>");
			out.println("<td width='35%' class='rep-body' style='text-align:center' >Remark</td>");
			
			out.println("</tr>");	
			
			
			
			
		/*	rs1 = stmt.executeQuery(" SELECT CHEQUE_NO,NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YY'),'-'),BRANCH_CODE,REC_AMOUNT,OTH_COMMENTS,CLIENT_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
			" WHERE SETTLE_MODE='CHEQUE' AND CLIENT_CODE='COR100' "); */
			
			
			rs1 = stmt.executeQuery(" SELECT NVL(A.CHEQUE_NO,'-'),NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MON-YY'),'-'),A.BRANCH_CODE,A.REC_AMOUNT,NVL(A.OTH_COMMENTS,'-'),A.CLIENT_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B "+
			" WHERE SETTLE_MODE='CHEQUE' AND CLIENT_CODE='"+m_client_code+"' AND "+ //m_client_code
			" A.REC_NO =B.RECEIPT_NO ");
			
			
			
			more = rs1.next();
			
			while(more){			
			out.println("<tr>");
			out.println("<td width='20%' class='rep-body' style='text-align:center' >"+rs1.getString(1)+"</td>");
			out.println("<td width='15%' class='rep-body' style='text-align:center' >"+rs1.getString(2)+"</td>");
			out.println("<td width='15%' class='rep-body' style='text-align:center' >"+rs1.getString(3)+"</td>");
			out.println("<td width='15%' class='rep-body' style='text-align:right' >"+nf.format(rs1.getDouble(4))+"</td>");
			out.println("<td width='35%' class='rep-body' style='text-align:center' >"+rs1.getString(5)+"</td>");			
			out.println("</tr>");
			more = rs1.next();
			}
			
			out.println("</table>");
						
			out.println("<br>");
			
			
			
			
			
		///////////////////////////////////////////////////////////////////////////////		
		double rate = 0.00;
		double due_amount = 0.00;
		
		rs = stmt.executeQuery(" SELECT RATE "+
		      " FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE ");
					
		more = rs.next();			
		
		if(more){
		rate = rs.getDouble(1);
		}
		 
			
			
		rs = stmt.executeQuery(" SELECT SUM(DUE_AMOUNT) "+
		     " FROM "+
				 " (SELECT FINANCE_NO,CLIENT_CODE,LAKDL.AF_CO_GET_DUE_AMOUNT( '',FINANCE_NO,SYSDATE) DUE_AMOUNT "+
				 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
				 " WHERE CLIENT_CODE='"+m_client_code+"') ");
			
		more = rs.next();			
		
		if(more){
		due_amount = rs.getDouble(1);
		}	
			
    
		data="We have been intructed to demand from you a sum of Rs "+nf.format(due_amount)+" due as at "+m_od_date+" together with "+
		     "interest at "+nf.format(rate)+"% per annum until payment in full be paid to our client within 14 days of this demand. ";
			
			out.println("<br>");									
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
								
			             data="On your failure to pay the said amount within the stipulated time we are further instructed "+
										"to file action under Debt Recovery (Special Provisions) Act No. 02 of 1990 ";
			
			out.println("<br>");									
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
																		
									
		             data="Under the above Act";
					String data1= " it is acriminal offence which can be filed action in the Magistrate Court to draw a cheque "+
					              "knowing that there are no funds, or no sufficient funds in the bank to honour such cheque or "+
												"countermanding the authority given to the bank and punishable with imprisonment or a fine or with both. "; 	
			
			out.println("<br>");									
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"<B>"+data1+"</B></td>");
			out.println("</tr></table>");		
			
			
			
		
		       data= "Should you wish to avoid such consequences please pay the amount demand within the stipulated time. ";
			
			out.println("<br>");									
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
									
					
									
									
	     data=     "If you have already paid the said amount you may disregard this demand. ";
			out.println("<br>");									
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			

                 data="Yours faithfully,";
	out.println("<br>");																	
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			
			 data="Attorneys-at-Law ";
	out.println("<br>");	
	out.println("<br>");
	out.println("<br>");
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			
			
			
			
			

out.println("<br><br><br><br>");
						out.println("<br>");
    		
		out.println("</font></p></blockquote>");		
		
				
		
			
			
				
		
												
		  out.println("</form></body></html>");
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
