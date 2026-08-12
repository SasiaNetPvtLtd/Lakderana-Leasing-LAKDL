
//SCREEN NAME:LEGAL LETTER
//DEVELOPED BY MAHELA FOR FACTORING ON 15-03-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_OP_Legal_Letter_to_debtor extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;

  public String m_d_code,m_d_add1,m_d_add2,m_d_name,m_d_city_desc;
	
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
			String m_contact_person="-",m_contact_desig="-";
			String m_d_contact_person="-",m_d_contact_desig="-";
			double m_chq_amount=0;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_facility_no=req.getParameter("facility_no");
			m_client_no=req.getParameter("client_no");			
			m_print=req.getParameter("print");
			m_debtor_code=req.getParameter("debtor_code");			
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Legal Letter to debtor</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			//out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_save_Legal_Letter?client_no="+m_client_no+"&facility_no="+m_facility_no+"&print="+m_print+"\";");  
			//out.println("  window.location.href=m_url;"); 
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
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
			//out.println("<br>");
			/*out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b><font size=4> GUNAWARDENA & RANASINGHE ASSOCIATES </font></b></td></tr>");
			out.println("</TABLE>");		
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='15%' class='rep-body'></td><td width='55%' class='rep-body'>Attorneys-at-Law &</td><td width='*%' class='rep-body'>No. 1056, Maradana Road,</td></tr>");
			out.println("<tr><td width='15%' class='rep-body'></td><td width='55%' class='rep-body'>Notaries Public</td><td width='*%' class='rep-body'>Colombo 08</td></tr>");
			out.println("<tr><td width='15%' class='rep-body'></td><td width='55%' class='rep-body'>Partners</td><td width='*%' class='rep-body'>Tel. 2686626</td></tr>");
			out.println("<tr><td width='15%' class='rep-body'></td><td width='55%' class='rep-body'>Mrs. Shiranthi N M Gunawardena</td><td width='*%' class='rep-body'>Fax. 2683022</td></tr>");
			out.println("<tr><td width='15%' class='rep-body'></td><td width='55%' class='rep-body'>Mrs. Chathuri P R Ranasinghe</td><td width='*%' class='rep-body'></td></tr>");
			out.println("</TABLE>");
			out.println("<hr>");*/
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
			out.println("<table border='0' width='100%' class='table'>");  		
			out.println("<tr><td width='70%'class='rep-body' style='{font:10px;text-align:left;}'></td><td width='70%'class='rep-body' style='{font:10px;text-align:left;}'><b><u>Registered post</u></b></td></tr>");
			out.println("</TABLE>");
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
						m_contact_person = rs1.getString(6);
						m_contact_desig = rs1.getString(7);
					}
					
				rs1 = stmt1.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+//1
  				  " NVL(FULL_NAME,'-'), "+//2
  				  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
  				  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
  				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'), "+//6
						" NVL(DESIGNATION_PAYMENT,'-') "+//7
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_debtor_code+"' ");

					more = rs1.next();
					if(more){
						m_d_code=rs1.getString(1);
						m_d_name=rs1.getString(2);
						m_d_add1=rs1.getString(3);
						m_d_add2=rs1.getString(4);
						m_d_city_desc=rs1.getString(5);
						m_d_contact_person = rs1.getString(6);
						m_d_contact_desig = rs1.getString(7);
					}
	
				
					rs2 = stmt2.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+
  				  " NVL(FULL_NAME,'-') "+
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs2.next();
						if(more){
							m_client_name = rs2.getString(2);
						 }

			
			out.println("<blockquote><font size=2><p style='text-align:left'>");										
			//out.println("<br><br><br><br><br><br><br>");
			out.println("<table border='0' width='100%' class='table'>");  		
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_Letter_date+"</td><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'><b>Debtor</b></td><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'><b>Client</b></td></tr>");
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_contact_person+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_contact_person+",</td></tr>");
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_contact_desig+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_contact_desig+",</td></tr>");
	  	out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_name+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_name+",</td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_add1+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add1+",</td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_add2+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_add2+",</td></tr>");
      out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_city_desc+".</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_city_desc+".</td></tr>");
			out.println("</TABLE>");
			//out.println("<br>");
			
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body'  style='{font:10px;text-align:left;}'><br>Dear Sir/Madam,</td></tr>");
			out.println("</TABLE><br>");		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>Facility No :- "+m_facility_no+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>Supplier code :- "+m_client_no+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>Supplier Name :- "+m_client_name+"</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'>Debtor code :- "+m_debtor_code+"</td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We are writing on the instructions of our client Lakderana Investments Limited, "+
									" No. 100,   Buthgamuwa Road,  Rajagiriya carrying on factoring business under the trade name 'Orient Factor' ");
			out.println("</td></tr></table>");						
			out.println("<br>");						
					
					
				/*rs2 = stmt2.executeQuery (" SELECT "+
  				 " CLIENT_CODE, "+
				   " FACILITY_CODE, "+
  				 " DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1) "+
 				" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE "+
 				" WHERE CLIENT_CODE='"+m_client_no+"' AND FACILITY_CODE='"+m_facility_no+"' ");
 				more = rs2.next();
				double m_amount=0;
				
				while(more){
				  m_amount = m_amount + rs2.getDouble(3);
					more = rs2.next();
				}*/
				
				  rs2 = stmt2.executeQuery ("SELECT SUM(A.BALANCE_AMOUNT)"+
 					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_INVOICE B  "+
 					" WHERE B.FACILITY_NO='"+m_facility_no+"' AND A.DEBTOR_CODE='"+m_debtor_code+"' AND A.BATCH_NO=B.BATCH_NO "+
          " AND B.CLIENT_CODE='"+m_client_no+"' AND A.BALANCE_AMOUNT > 0 AND A.INVOICE_STATUS='CONF' "+
					" AND (TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))<=(-30) ");
					
					more = rs2.next();
					String m_amount="";
					String m_amount2="";
					
      if(more){ 
			m_amount = nf.format(rs2.getDouble(1));
			m_amount2 = m_sn_methods.met_unformat_number(m_amount);
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We are instructed to inform you that you have failed and neglected to act in compliance with the "+
									" several reminders which has been sent to you by our client Lakderana Investments Limited "+
									" , to settle the overdue amount of Rs "+nf.format(rs2.getDouble(1))+" due from you to our client "+
									" on account of the invoices purchased by our client, the details of which are already with you.");		
			out.println("</td></tr></table>");						
			}
			else {
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We are instructed to inform you that you have failed and neglected to act in compliance with the "+
									" several reminders which has been sent to you by our client Lakderana Investments Limited "+
									" , to settle the overdue amount of Rs 0.00 due from you to our client "+
									" on account of the invoices purchased by our client, the details of which are already with you.");		
			out.println("</td></tr></table>");						
			}
			
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We are instructed to demand from you and demand is hereby made for a sum of Rs. "+
									" "+numbersToChar_inside(m_amount2)+" being the amount due "+
									" from you to be paid to our client. Within 14 days of receipt of the demand.");
			out.println("</td></tr></table>");						
			out.println("<br>");	

			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" Should you fail to do so, we are instructed by our client to proceed with necessary actions "+
									" to safeguard our client's "+
									" interest without further warning.	");
			out.println("</td></tr></table>");						
			out.println("<br>");	
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We are instructed to inform you that our client is legally entitled to sue you as "+
			             " a Debtor in a Court of Law and to obtain judgment against you. ");
			out.println("</td></tr></table>");						
			out.println("<br>");	
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Yours faithfully,<br>");
			out.println("</td></tr></table>");						
			out.println("<input class='txt_input6' type='text' name='TXT_LAWYER' maxlength='100' value='Gunawardena & Ranasinghe Associates' size='10' style='width:250' onblur=\"\"> <br>");	
			out.println("<br>");	
			out.println(" ................................");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
    	out.println(" Attorneys-at-Law ");	
			out.println("</td></tr></table>");						
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

public String numbersToChar_inside(String obj){

		Object[] ar_ones  = new Object[10];
		Object[] ar_tens  = new Object[10];
		Object[] ar_teens = new Object[10];
		
		ar_ones[0]        = "";
		ar_ones[1]        = "One";
		ar_ones[2]        = "Two"; 
		ar_ones[3]		  =	"Three";
		ar_ones[4]        = "Four";
		ar_ones[5]        = "Five";
		ar_ones[6]        = "Six"; 
		ar_ones[7]		  =	"Seven";
		ar_ones[8]        = "Eight";
		ar_ones[9]        = "Nine";
		
		ar_tens[0]        = "";
		ar_tens[1]        = "Ten";
		ar_tens[2]        = "Twenty"; 
		ar_tens[3]		  =	"Thirty";
		ar_tens[4]        = "Forty";
		ar_tens[5]        = "Fifty";
		ar_tens[6]        = "Sixty"; 
		ar_tens[7]		  =	"Seventy";
		ar_tens[8]        = "Eighty";
		ar_tens[9]        = "Ninety";
		
		ar_teens[0]        = "";
		ar_teens[1]        = "Eleven";
		ar_teens[2]        = "Twelve"; 
		ar_teens[3]		   = "Thirteen";
		ar_teens[4]        = "Fourteen";
		ar_teens[5]        = "Fifteen";
		ar_teens[6]        = "Sixteen"; 
		ar_teens[7]		   = "Seventeen";
		ar_teens[8]        = "Eighteen";
		ar_teens[9]        = "Nineteen";
        
		String m_value;
		String m_cents="";
		
		String m_full_str="";
		String m_digit="0";
		int m_length=obj.length();
		m_value=obj.toString();
		int m_dot=obj.indexOf(".");
		if (m_dot!= -1 ) {
			m_cents=obj.substring(m_dot+1,m_length);
			int num = m_cents.length();
			if (num==1) {
				m_cents=m_cents+"0";
			}
			else if (m_cents.length()==2) {
				m_cents=m_cents+"00";
			}
			m_length=m_value.substring(0,m_dot).length();
			m_value=obj.substring(0,m_dot);
		}
		
		
		for (int j=1 ; j<= 15-m_length; j++) {
			m_value="0"+m_value;
		}
		int m_cnt=15-m_length;
		for (int i=m_cnt; i<=14; i++) {
			
			m_digit = m_value.substring(m_cnt,m_cnt+1);
			//System.out.println("m_digit="+m_digit);
			if ((m_cnt)==3) {  // 100,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Billion ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==4) {  // 10,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Billion ";
						m_cnt+=1;
						i+=1;
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Billion ";
					}
					
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if (m_cnt==5) {  // 1,000,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Billion ";
				}
			}
			
			
			else if ((m_cnt)==6) {  // 100,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Million ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==7) {  // 10,000,000
				//System.out.println("m_value="+m_value+" m_value.substring(m_cnt,m_cnt+1)="+m_value.substring(m_cnt,m_cnt+1)+"  m_value.substring(m_cnt+1,m_cnt+2)="+m_value.substring(m_cnt+1,m_cnt+2));
				if (!m_digit.equals("0")) {
					//if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Million ";
						m_cnt+=1;
						i+=1;
						//System.out.println("m_full_str="+m_full_str);
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Million ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==8) {  // 1,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Million ";
				}
			}
			
			else if ((m_cnt)==9) {  // 100,000
				if (!m_digit.equals("0")) {
					if ((Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (Integer.parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Thousand ";
						m_cnt+=2;
						i+=2;
					}
					else {						
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if ((m_cnt)==10) {  // 10,000
				if (!m_digit.equals("0")) {
					if (Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Thousand ";
						m_cnt+=1;
						i+=1;
						//m_cnt+=2;
					}
					else if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Thousand ";
						m_cnt+=1;
						i+=1;
					}
					else {						
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==11) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Thousand ";
				}
			}
			
			else if ((m_cnt)==12) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
				}
			}
			
			else if ((m_cnt)==13) {  
				if (!m_digit.equals("0")) {
					if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) {
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==14) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" ";
				}
			}
			
			m_cnt++;
		}
		
		
		m_cnt=0;
		if (!m_cents.equals("")){
			for (int i=m_cnt; i<=1; i++) {			
				m_digit = m_cents.substring(m_cnt,m_cnt+1);
				if ((m_cnt)==0) {  // 10
					if (!m_digit.equals("0")) {
						if ((m_digit.equals("1")) && (!m_cents.substring(m_cnt+1,m_cnt+2).equals("0"))) {
							if(!m_full_str.toString().equals(""))
							{
								//m_full_str=m_full_str+"and "+ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							else 
							{
								//m_full_str=ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							m_cnt+=1;
						}
						else {
							//m_full_str=m_full_str+"and "+ar_tens[Integer.parseInt(m_digit)]+"";
						}
					}
				}
				
				else if ((m_cnt)==1) {  // 1
					if (!m_digit.equals("0")) {
						if (m_cents.substring(m_cnt-1,m_cnt).equals("0")) {
							if(!m_full_str.toString().equals(""))
							{
								//m_full_str=m_full_str+"and "+ar_ones[Integer.parseInt(m_digit)]+"";						
							}
							else
							{
								//m_full_str=ar_ones[Integer.parseInt(m_digit)]+"";		
							}
						}
						else {
							//m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+"";	
						}
					}
				}
				
				
				m_cnt++;
				if (m_cnt>1){
					//m_full_str=m_full_str +" cents ";
				}
				
			}
		}
		
		return m_full_str.toString();
	}

	
}  
