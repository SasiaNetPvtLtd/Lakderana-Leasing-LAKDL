//ID         :
//SCREEN NAME:PURCHASE ORDER LETTER
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 19-JUL-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_MK_Quatation_Approvalt_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2,rs3;
	

	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_invoice_no,m_client_no,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	
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
			m_invoice_no	  =req.getParameter("invoice_no");		
			m_client_no	      =req.getParameter("client_no");		
			m_no_of_due_date	=req.getParameter("no_of_due_date");		
			m_print=req.getParameter("print");
			
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();			
										
				if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
								
			else if (m_chksql.trim().equals("main_page")) {
			

				
			  out.println("<html><head>"); 
				out.println("<title>Collection Due Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		 //	out.println(" <style> body { background-color: white; font: 12pt arial; } ");
			//	out.println(" td 	{ font: 11pt ; } th { font: bold 12pt ; } </style>");
			
			out.println("<script>");
			out.println("function save_data(){");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save_Collection_Due_Report?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_code+"&no_of_due_date="+m_no_of_due_date+"&scr_name=AF_RE_RPT_COLLECTION_DUE;); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Due_Report?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_no+"&scr_name=AF_RE_RPT_COLLECTION_DUE&no_of_due_date="+m_no_of_due_date+"\";"); 
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
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
								
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
				
		rs1 = stmt1.executeQuery (	" SELECT "+
    " CLIENT_CODE, "+
    " FULL_NAME, "+
    " ADDRESS1, "+
    " ADDRESS2, "+
    " "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) "+
    " FROM LAKDL.AF_CO_MAS_CLIENT "+
    " WHERE CLIENT_CODE='"+m_client_no+"' ");
		

					
			more = rs1.next();
			if(more){
			m_c_code=rs1.getString(1);
			m_name=rs1.getString(2);
			m_add1=rs1.getString(3);
			m_add2=rs1.getString(4);
			m_city_desc=rs1.getString(5);
			}
			
			
			
			
			rs2 = stmt2.executeQuery (" SELECT "+
			" AMOUNT_DUE, "+
			" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'), "+
			" NVL(FINANCE_NO,'-') "+
			" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE "+
			" WHERE  INVOICE_NO=UPPER('"+m_invoice_no+"') AND  CLIENT_CODE=UPPER('"+m_client_no+"') ");


			more = rs2.next();
			if(more){
			m_amount_due=rs2.getDouble(1);
			m_due_date  =rs2.getString(2);
			m_finance_no =rs2.getString(3);
			}
			
			
				
			
			
			if (m_no_of_due_date.trim().equals("3")) {
			
			out.println("<blockquote><blockquote><font size=2><p style='text-align:left'>");				
						
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
			
			out.println("<blockquote><font size=2><p style='text-align:justify'>");				
			out.println("<table border='0' width='100%' class='table'> ");	
			
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
			
			out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");


				
			out.println("<blockquote><font size=2><p style='text-align:left'>");			
			
			out.println("<table border='0' width='100%' class='table'>");	
			out.println("<tr><td width='10%'class='rep-body'><b>Lease No</b></td><td width='10%' class='rep-body' style='{text-align:left;}'><b>"+m_finance_no+"</b></td><td width='40%' class='rep-body' >&nbsp;</td>");
			out.println("</tr>");
			out.println("</table>");
			//out.println("    <td width='40%' class='rep-body'>"+m_name+"</td></tr>");
			out.println("<table border='0' width='1000%' class='table'>");	
			out.println("<tr><td width='*%' class='rep-body'><br><br>Total Outstanding as at "+m_Letter_date+"  is Rs. "+nf.format(m_amount_due)+" </td></tr>");
		 	out.println("</table>");
			
			out.println("</font></p></blockquote>");			
			
				
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			
				out.println("Please treat this information as a helpful reminder on above lease "+
                    "agreement.We presume that due to your busy work schedule, you may"+
                    " have missed lease rental payment which was due on "+m_due_date+".");
                  
     out.println("<br><br>If you have not settled this payment, please be good enough to "+
                  "arrange for same at your earliest.<br><br>");
									
     out.println("Kindly disregard this letter if you have already paid.<br><br>");
     
		 out.println("If you wish to inquire about this payment, then please call us on "+
                  "011-5577513 or 0115577508 or 011557522 <br><br>");

		out.println("Thank you,<br><br>");	
		
		out.println("Yours in service<br><br><br>");	
		
		out.println("Collection Department<br><br><br>");	
		
		out.println("This is a computer generated letter and does not require any<br>");	
    out.println("signature<br>");	
		
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
			out.println("<table border='0' width='100%' class='table'> ");	

			out.println("<tr ><td width='50%' class='rep-body' style='{text-align:left;}'><br><b>MASTER AGREEMENT NUMBER : "+m_finance_no+" </b></td>");
			out.println("<td width='50%' class='rep-body' style='{text-align:left;}'><br><b>SHEDULE NUMBER   :"+m_finance_no+"</b></td><td width='*%' class='rep-body' >&nbsp;</td></tr>");
			
			out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");



			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			
				out.println("We refer our previous telephone conversations, reminders and regret to "+
                    "note that you were unable to regularize the dues on the above contract.<br><br>");
                    
                  
     out.println("The overdue amount as at "+m_Letter_date+" is Rs."+m_amount_due+" and detailed "+
                  "statement is annexed herewith for your information<br><br>");
									
     out.println("Kindly disregard this letter if you have already paid.<br><br>");
     
		 out.println("The lease agreement states that the payment will be received "+
                  "promptly on the due date of each month for the life of the ageement "+
                  "without the need for written and verbal reminders.We trust that you will "+
									"respond to this request for payment without the need for further action "+
									"by us.<br><br>");
									
			
		out.println("Your immediate attention on this matter is requested.<br><br>");	
		
		out.println("In the event payment has already been made please disregard this "+
 	              "reminder.<br><br>");
		
		out.println("If you need further clarification, please feel free to contact our "+	
		            "collection staff on +94 117 586 500 or +94 117 586 501 to 508.<br><br>");
								
		out.println("Yours faithfully ,<br><br>");	
		
    out.println("LAKDERANA INVESTMENTS LIMITED<br><br><br><br>");	
		
		out.println("Authorized signatory<br><br><br>");
		
		out.println("</font></p></blockquote>");		
			
			
			
			}
			
			
				
		
												
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
