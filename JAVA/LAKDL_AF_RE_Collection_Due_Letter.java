//ID         :
//SCREEN NAME:COLLECTION DUE LETTER
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 19-JUL-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_AF_RE_Collection_Due_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2,rs3;
	

	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_invoice_no,m_client_no,m_no_of_due_date,m_finance_no,m_print,m_app_no,m_activated_date;
	public String m_tran_type,m_title,m_vehicle,m_master_lease_no="";
	public double m_amount_due=0,m_amount_odi=0,m_amount_total=0;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
		
		// 	BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
	//		reqstr = input.readLine();   	
			 
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
						
			m_chksql=req.getParameter("chksql");
			//m_invoice_no	  =req.getParameter("invoice_no");		
			m_finance_no	  =req.getParameter("finance_no").trim();		
			m_client_no	      =req.getParameter("client_no").trim();		
			m_no_of_due_date	=req.getParameter("no_of_due_date").trim();		
			m_print=req.getParameter("print").trim();
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";

			
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();			
										
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
								
			else if (m_chksql.trim().equals("main_page")) {
			

				//out.println(m_invoice_no);
			  out.println("<html><head>"); 
				out.println("<title>Collection Due Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
			
			out.println("<script>");
			out.println("function save_data(){");
			out.println("m_table.innerHTML=\"\" ");

			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save_Collection_Due_Report?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_code+"&no_of_due_date="+m_no_of_due_date+"&scr_name=AF_RE_RPT_COLLECTION_DUE;); 
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Due_Report?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_no+"&scr_name=AF_RE_RPT_COLLECTION_DUE&no_of_due_date="+m_no_of_due_date+"\";"); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Due_Report?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_no+"&finance_no="+m_finance_no+"&scr_name=AF_RE_RPT_COLLECTION_DUE&no_of_due_date="+m_no_of_due_date+"\";"); 
			
		  out.println(" window.location.href=m_url;"); 
			
			//out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			//out.println("window.preview();");
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
			
			//out.println("m_table.inerHTML=");
			out.println("}");
			
			out.println("</script>");
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
				
			//	out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
				out.println("<body bgcolor='white'><br>");
				
			
				
				out.println("<form name='Form1'>");
				
			//	if (m_print.trim().equals("FALSE")) {
				
				//out.println("<table border='0' width='100%' class='table'>"); 		
				//out.println("<tr >"); 
			  //out.println("<td width='*%' align='right' class='rep-body'><input class='but_input' type='button' name='BUT_TXT_REC_BOOK_NO' value=\"Print\" onClick=\"save_data('"+m_invoice_no +"','"+m_client_no +"',"+m_no_of_due_date +")\"></td>"); 
			  //out.println("</tr>"); 
				//out.println("</TABLE>");
				
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
       
		//		}
					//out.println("<br>");
				//	out.println("<font point-size='7'><p style='text-align:justify'>");
				
				
				
						 rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

						boolean	  more = rs.next();		
											
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
											
				
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
								
				 more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
				
		rs1 = stmt1.executeQuery (	" SELECT "+
    " CLIENT_CODE, "+
    " NVL(FULL_NAME,' '), "+
    " NVL(ADDRESS1,' '), "+
    " NVL(ADDRESS2,' '), "+
    " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),' '), "+
		" INITCAP(TITLE) "+ //Added by Mahela
    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
    " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_no+"') ");
		

					
			more = rs1.next();
			if(more){
			m_c_code=rs1.getString(1);
			m_name=rs1.getString(2);
			m_add1=rs1.getString(3);
			m_add2=rs1.getString(4);
			m_city_desc=rs1.getString(5);
			m_title=rs1.getString(6);
			}
							
			
			/*rs2 = stmt2.executeQuery (" SELECT "+
			" NVL(AMOUNT_DUE,0), "+
			" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'), "+
			" NVL(FINANCE_NO,'-') "+
			" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE "+
			" WHERE  INVOICE_NO=UPPER('"+m_invoice_no+"') AND  CLIENT_CODE=UPPER('"+m_client_no+"') ");
     */
			
			rs2 = stmt2.executeQuery (" SELECT "+
			" NVL(SUM(AMOUNT_DUE),0), "+
			" "+m_schema_name+".AF_CO_GET_OLDEST_INV_DATE(FINANCE_NO) "+
			" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE "+
			" WHERE CLIENT_CODE=UPPER('"+m_client_no+"') "+
			" AND UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') "+
			" GROUP BY FINANCE_NO ");
   

			more = rs2.next();
			if(more){
			m_amount_due=rs2.getDouble(1);
			m_due_date  =rs2.getString(2);
			//m_finance_no =rs2.getString(3);
			}
			
		 /*rs2 = stmt2.executeQuery (" SELECT "+
     " NVL(SUM(ODI_BAL_AMOUNT) ,0) "+
     " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_DAILY "+ 
     " WHERE UPPER(INVOICE_NO)=UPPER('"+m_invoice_no+"') "); */		
			
			
		 rs2 = stmt2.executeQuery (" SELECT SUM(ODI_BAL_AMOUNT) "+
														   " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A , "+m_schema_name+".AF_CO_PRO_INVOICE  B "+
														   " WHERE A.INVOICE_NO = B.INVOICE_NO "+
														   " AND  B.FINANCE_NO  = UPPER('"+m_finance_no+"') ");
			more = rs2.next();
			if(more){
			m_amount_odi=rs2.getDouble(1);
			}

			m_amount_total=m_amount_due+m_amount_odi;
			
			//---- Added by Mahela on 12-06-2007 -----------------------	
 			rs2 = stmt2.executeQuery ("	SELECT "+
    	" APPLICATION_NO, "+
			" NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-') ,"+
			" NVL(MASTER_AGREEMENT_NO,'-') "+
			"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 			"	WHERE  FINANCE_NO='"+m_finance_no+"' ");
			more = rs2.next();
			if(more){
			m_app_no=rs2.getString(1);
			m_activated_date=rs2.getString(2);
			m_master_lease_no=rs2.getString(3);
			}
			//----- End of Addition ------------------------------------
			
			if (m_no_of_due_date.trim().equals("3")) {
			
			out.println("<blockquote><blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%'class='rep-body'>"+m_Letter_date+"</td></tr>");
			//out.println("<br><br>");
			//out.println("<tr><td width='*%' class='rep-body' ><br><br>To&nbsp; :</td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' >"+m_add1+", "+m_add2+"</td></tr>");
		 // out.println("<tr><td width='*%' class='rep-body' >"+m_add2+",</td></tr>");
      out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			
			out.println("<br><br><br>");
			
			out.println("<blockquote><font size=2><p style='text-align:justify'>");				
			out.println("<table border='0' width='100%' class='table'> ");	
			
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
			
			out.println("</table>");
					
			out.println("</font></p></blockquote>");


				
			out.println("<blockquote><font size=2><p style='text-align:left'>");			
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='12%'class='rep-body'>Lease Agreement No :</td><td width='20%' class='rep-body' style='{text-align:left;}'><b>"+m_finance_no+"</b></td><td width='20%' class='rep-body' >&nbsp;</td>");
			out.println("</tr>");
			out.println("</table>");
			//out.println("    <td width='40%' class='rep-body'>"+m_name+"</td></tr>");
			out.println("<table border='0' width='1000%' class='table'>");	
			//out.println("<tr><td width='*%' class='rep-body'><br><br>Total Outstanding as at "+m_Letter_date+"  is Rs. "+nf.format(m_amount_total)+" (inclusive of ODI)  </td></tr>");
		 	out.println("</table>");
			out.println("<br><br>");	
			out.println("<table border='0' width='50%' class='table'> ");	
			out.println("<tr><td width='35%' class='rep-body' style='{text-align:left;}'>Rental in arrears</td><td width=15%>:</td><td width='5%'>Rs.</td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:right;}'>"+nf.format(m_amount_due)+"</td><td width='*%'>&nbsp;</td></tr>");
			
			out.println("<tr><td width='35%' class='rep-body' style='{text-align:left;}'>Overdue Interest</td><td width=15%>:</td><td width='5%'>Rs.</td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:right;}'>"+nf.format(m_amount_odi)+"</td><td width='*%'>&nbsp;</td></tr>");
			out.println("<tr><td>&nbsp;</td></tr>");
			out.println("<tr><td width='35%' class='rep-body' style='{text-align:left;}'>Total Overdue</td><td width=15%>:</td><td width='5%'>Rs.</td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:right;}'>"+nf.format(m_amount_total)+"</td><td width='*%'>&nbsp;</td></tr>");
			
			out.println("</TABLE>");
			
			out.println("</font></p></blockquote>");			
			
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			
			out.println("We note that the rental shown above does not appear to have been received by us yet possibly due to an oversight.<br><br> ");
			/*out.println("Please treat this information as a helpful reminder on above lease "+
                    "agreement.We presume that due to your busy work schedule, you may"+
                    " have missed lease rental payment which was due on "+m_due_date+".");
                  
     out.println("<br><br>If you have not settled this payment, please be good enough to "+
                  "arrange for same at your earliest.<br><br>");
									
     out.println("Kindly disregard this letter if you have already paid.<br><br>");
     
		 out.println("If you wish to inquire about this payment, then please call us on "+
                  "011-5577513 or 0115577508 or 0115577546 <br><br>");
   */
		out.println("Kindly note that in terms of the lease agreement, overdue interest would "+
								"accrue on arrears outstanding.<br><br>");
		
		out.println("Please arrange for immediate remittance of this rental. This will ensure that you will not "+
								"be called upon to unnecessarily pay any further overdue interest and please arrange regular "+
								"payments in the further promptly.<br><br>");
								
		out.println("In the event payment has already been made please disregard this reminder and apologize for "+
								"the inconvenience and thank you for your payment.<br><br><br><br>");		
								
								
		out.println(""+m_orient_name.toUpperCase()+"<br><br><br><br><br>");							
		/*out.println("Thank you,<br><br>");	
		
		out.println("Yours in service<br><br><br><br><br>");	
		
		out.println("Collection Department<br><br><br>");	
		
		out.println("This is a computer generated letter and does not require any "+	
                "signature<br>");	*/
		out.println("This is a system generated letter hence the signature is not required "+	
                " <br>");							
		
		out.println("</font></p></blockquote>");		
		
		
			
			}
			
			else	if (m_no_of_due_date.trim().equals("15")) {
			out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body'>");				
						
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%'class='rep-body'>"+m_Letter_date+"</td></tr>");
			//out.println("<br><br>");
			//out.println("<tr><td width='*%' class='rep-body' ><br><br>To&nbsp; :</td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' >"+m_add1+",</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' >"+m_add2+",</td></tr>");
      out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			
			out.println("<br><br><br>");
			
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
			out.println("<table border='0' width='100%' class='table'> ");	
			
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
			
			out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			
			//out.println("<br>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
			out.println("<table border='0' width='50%' class='table'> ");	
			/*
			out.println("<tr ><td width='50%' class='rep-body' style='{text-align:left;}'><br><b>MASTER AGREEMENT NUMBER : "+m_master_lease_no+" </b></td>");
			out.println("<td width='50%' class='rep-body' style='{text-align:left;}'><br><b>SCHEDULE NUMBER   :"+m_finance_no+"</b></td><td width='*%' class='rep-body' >&nbsp;</td></tr>");
			*/
			
			out.println("<tr><td width='35%' class='rep-body' style='{text-align:left;}'>Rental in arrears</td><td width=15%>:</td><td width='5%'>Rs.</td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:right;}'>"+nf.format(m_amount_due)+"</td><td width='*%'>&nbsp;</td></tr>");
			
			out.println("<tr><td width='35%' class='rep-body' style='{text-align:left;}'>Overdue Interest</td><td width=15%>:</td><td width='5%'>Rs.</td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:right;}'>"+nf.format(m_amount_odi)+"</td><td width='*%'>&nbsp;</td></tr>");
			out.println("<tr><td>&nbsp;</td></tr>");
			out.println("<tr><td width='35%' class='rep-body' style='{text-align:left;}'>Total Overdue</td><td width=15%>:</td><td width='5%'>Rs.</td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:right;}'>"+nf.format(m_amount_total)+"</td><td width='*%'>&nbsp;</td></tr>");
			
			out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");



			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			
				//out.println("We refer to our previous telephone conversations, reminders and regret to "+
          //          "note that you were unable to regularize the dues on the above contract.<br><br>");
     out.println("We refer to our previous reminder and regret to "+
                 "note that the arrears above have still not been paid to date.<br><br>");
                                   
                  
    // out.println("The arrears amount as at "+m_Letter_date+" is Rs."+nf.format(m_amount_total)+" (inclusive of ODI) "+ //overdue //and detailed
    //              "<br><br>");//statement is annexed herewith for your information
									
   //  out.println("Kindly disregard this letter if you have already paid.<br><br>");
		
		out.println("Unless the arrears and the overdue interest mentioned above are settled within seven (07) "+
								"days hereof we will have no alternative but to turn your account over to "+
								"Recoveries Division for collection.");
    /* 
		 out.println("The lease agreement states that the payment will be received "+
                  "promptly on the due date of each month for the life of the agreement "+
                  "without the need for written and verbal reminders.We trust that you will "+
									"respond to this request for payment without the need for further action "+
									"by us.<br><br>");
			*/						
			
		out.println("Your immediate attention to this matter is requested.<br><br>");	
		/*
		out.println("In the event payment has already been made please disregard this "+
 	              "reminder.<br><br>");
		
		out.println("If you need further clarification, please feel free to contact our "+	
		            "collection staff on 011-5577513 or 011-5577508 or 011-5577546.<br><br>");
			*/					
		out.println("Yours faithfully ,<br>");	
		
    out.println(""+m_orient_name.toUpperCase()+"<br><br><br><br><br>");	
		
		out.println("MANAGER LEASING/RECOVERIES<br><br><br>");
		
		out.println("</font></p></blockquote>");		
			
			
			
			}
			
			//------------------- Added by Mahela on 12-06-2007  ---- 30 Days Due Letter  --------------------------------------------------------------------------
			
			else	if (m_no_of_due_date.trim().equals("30")) {

			
       rs = stmt.executeQuery ("SELECT  TRANSACTION_TYPE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO='"+m_app_no+"' ");
    	 more = rs.next();
				
			 if(more){
				m_tran_type=rs.getString(1);
			 }
			
			 if(m_tran_type.equals("FINLEASE")) {
			
							rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'MonthDD, YYYY') FROM DUAL ");					
							more = rs.next();
							if(more){
								m_Letter_date=rs.getString(1);
							}
							
							out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body'>");				
							out.println("<table border='0' width='100%' class='table'>"); 		
							out.println("<tr><td width='*%'class='rep-body'><b><u>BY REGISTERED POST</u></b></td></tr>");
							out.println("<tr><td width='70%'class='rep-body'></td><td width='*%'class='rep-body'><b>"+m_Letter_date+"</b></td></tr>");
					  	out.println("<tr><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
						  out.println("<tr><td width='*%' class='rep-body' >"+m_add1+",</td></tr>");
						  out.println("<tr><td width='*%' class='rep-body' >"+m_add2+",</td></tr>");
				      out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
							out.println("</TABLE>");
							out.println("</font></p></blockquote>");
							out.println("<br><br><br>");
							out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir/Madam,</td></tr>");
							out.println("</TABLE>");
							out.println("</font></p></blockquote>");			
							out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr><td width='50%' class='rep-body' style='{text-align:left;}'><u><b>NOTICE OF SUBSTANTIAL FAILURE OF</b></u></td></tr>");
							out.println("<tr><td width='50%' class='rep-body' style='{text-align:left;}'><u><b>LEASE AGREEMENT NO  : "+m_finance_no+"</b></u></td></tr>");
							out.println("</TABLE>");
							out.println("</font></p></blockquote>");
							out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
							out.println("You entered in to the above Lease Agreement on <b>"+m_activated_date+"</b> on the terms and conditions mentioned therein.<br><br> ");
				      out.println("We hereby inform you that despite our repeated requests and reminders,you have failed to "+
													" pay the arrears of lease rentals due on your above lease totaling <b>Rs."+nf.format(m_amount_due)+" /= </b> in terms of the "+
				                  " conditions in the Lease Agreement, your non-payment of lease rentals on the due dates has caused a substantial failure of this lease. <br><br>");
				      out.println("In the circumstance, we have to inform you that, unless you settle the above arrears in full within 7 days from the date of receipt of this letter, "+
													"we will be compelled to take steps to terminate the Lease, in accordance with the provisions of the Lease Agreement. <br><br>");
						  out.println("We urge you therefore; to kindly make arrangements to settle the arrears without any further delay.<br><br>");					
						  out.println("Yours faithfully ,<br><br>");	
				      out.println(""+m_orient_name.toUpperCase()+"<br><br><br>");	
						  out.println("Assistant General Manager Recoveries<br><br><br><br><br><br>");
							out.println("<b>* A copy of this letter is being forwarded to the guarantor/s for their information and necessary action.</b><br><br>");
							
							    //Guarantor Details
									rs = stmt.executeQuery(" SELECT "+
				  			  " A.APPLICATION_NO,"+
				    			" A.GUARANTOR_CODE,"+
				  			  " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE),'-'), "+	 		
									" NVL(B.ADDRESS1,'-'),"+
				          " NVL(B.ADDRESS2,'-'), "+
									" NVL(INITCAP(B.TITLE),'-')"+
				 					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
				 				  " WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE  ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ");
									more = rs.next();			
									int count=0;
				
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr>");
				      out.println("<td width='*%' class='rep-body' valign='top' >(Guarantor/s) </td>");
							out.println("</tr>");
							out.println("</table>");
							while(more) {
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr>");
				      out.println("<td colspan='5' class='rep-body'>&nbsp;<b> "+rs.getString(6)+". "+rs.getString(3)+" &nbsp;&nbsp;&nbsp;&nbsp; of &nbsp;&nbsp;&nbsp;&nbsp; "+rs.getString(4)+","+rs.getString(5)+" </b></td>");
							out.println("</tr>");
							out.println("</table>");
							more = rs.next();			
							}
						  out.println("</font></p></blockquote>");		
			 }
			 else if(m_tran_type.equals("HIREPURCH")) {
			
							rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'MonthDD, YYYY') FROM DUAL ");					
							more = rs.next();
							if(more){
								m_Letter_date=rs.getString(1);
							}

							rs = stmt.executeQuery (" SELECT "+
							"  A.SUB_MODEL_CODE,"+
							"  NVL(B.DESCRIPTION,'-') "+
							"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
							"WHERE A.SUB_MODEL_CODE=B.SUB_CODE "+
							" AND A.APPLICATION_NO = C.APPLICATION_NO "+
							" AND C.FINANCE_NO = '"+m_finance_no+"' ");
							//" AND A.INVOICE_NO='"+m_invoice_no+"' ");
							more = rs.next();
								
							if(more){
							 m_vehicle = rs.getString(2);
							}
							else { m_vehicle="-"; }
								
							out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body'>");				
							out.println("<table border='0' width='100%' class='table'>"); 		
							out.println("<tr><td width='*%'class='rep-body'><b><u>BY REGISTERED POST</u></b></td></tr>");
							out.println("<tr><td width='70%'class='rep-body'></td><td width='*%'class='rep-body'><b>"+m_Letter_date+"</b></td></tr>");
					  	out.println("<tr><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
						  out.println("<tr><td width='*%' class='rep-body' >"+m_add1+",</td></tr>");
						  out.println("<tr><td width='*%' class='rep-body' >"+m_add2+",</td></tr>");
				      out.println("<tr><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
							out.println("</TABLE>");
							out.println("</font></p></blockquote>");
							out.println("<br><br><br>");
							out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir/Madam,</td></tr>");
							out.println("</TABLE>");
							out.println("</font></p></blockquote>");			
							out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr><td width='50%' class='rep-body' style='{text-align:left;}'><b>Termination of Hire Purchase  </b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> :  "+m_title+"."+m_name+" </b></td></tr>");
							out.println("<tr><td width='50%' class='rep-body' style='{text-align:left;}'><b>Agreement No  </b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> :  "+m_finance_no+" </b></td></tr>");
							out.println("<tr><td width='50%' class='rep-body' style='{text-align:left;}'><b>Description of Vehicle/Equipment  </b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> :  "+m_vehicle+"</b></td></tr>");
							out.println("</TABLE>");
							out.println("<hr>");
							out.println("</font></p></blockquote>");
							
							rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE, 'fmddth Month YYYY') FROM DUAL ");					
							more = rs.next();
							if(more){
								m_Letter_date=rs.getString(1);
							}
							
							out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");				
							out.println("Further to our Notice of Termination of Lease Purchase dated  <b>"+m_activated_date+"</b> sent to you in accordance with the "+
													"terms of Lease Purchase Agreement notwithstanding the said notice, you have failed and neglected to remedy the default/breach/Act "+
													" specified in the said notice within the time limit stipulated therein.<br><br> ");
				      out.println("We therefore, declare that the Lease Purchase in terms of the aforesaid Lease Purchase Agreement is "+
													" terminated with immediate effect. We further inform you that our property Leased to you is no longer in "+
				                  " your possession with our permission and consent. Therefore, your possession of the property is unlawful.<br><br> ");
				      out.println("In the circumstances, you should either deliver the property leased forthwith to our office at 46/58, Navam Mawatha, Colombo 02, "+
													"during our normal business hours or pay at once the sum of <b>Rs."+nf.format(m_amount_due)+" /= </b> being the full balance "+
													" due as at <b>"+m_Letter_date+"</b> Should you fail to do so, we will proceed with the necessary action to safeguard our interest without further Notice. <br><br>");				
						  out.println("In the circumstances, you will remain fully laible under the conditions of the Lease Purchase Agreement entered into and executed by you.<br><br>");					
						  out.println("Yours faithfully ,<br><br>");	
				      out.println(""+m_orient_name.toUpperCase()+"<br><br><br>");	
						  out.println("Assistant General Manager Recoveries<br><br><br><br><br><br>");
							out.println("<b>* A copy of this letter is being forwarded to the guarantor/s for their information and necessary action.</b><br><br>");
							
							    //Guarantor Details
									rs = stmt.executeQuery(" SELECT "+
				  			  " A.APPLICATION_NO,"+
				    			" A.GUARANTOR_CODE,"+
				  			  " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE),'-'), "+	 		
									" NVL(B.ADDRESS1,'-'),"+
				          " NVL(B.ADDRESS2,'-'), "+
									" NVL(INITCAP(B.TITLE),'-')"+
				 					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
				 				  " WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.GUARANTOR_CODE=B.CLIENT_CODE   ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ");
									more = rs.next();			
									int count=0;
				
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr>");
				      out.println("<td width='*%' class='rep-body' valign='top' >(Guarantor/s) </td>");
							out.println("</tr>");
							out.println("</table>");
							while(more) {
							out.println("<table border='0' width='100%' class='table'> ");	
							out.println("<tr>");
				      out.println("<td colspan='5' class='rep-body'>&nbsp;<b> "+rs.getString(6)+". "+rs.getString(3)+" &nbsp;&nbsp;&nbsp;&nbsp; of &nbsp;&nbsp;&nbsp;&nbsp; "+rs.getString(4)+","+rs.getString(5)+" </b></td>");
							out.println("</tr>");
							out.println("</table>");
							more = rs.next();			
							}
						  out.println("</font></p></blockquote>");		
			 }	
			
			}
			
			
			//---------------------------------------- End of Addition --------------------------------------------------------------------------------------------------------------------
			
				
		
												
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
