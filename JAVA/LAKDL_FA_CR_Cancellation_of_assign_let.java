
//SCREEN NAME:LEGAL LETTER
//DEVELOPED BY MAHELA FOR FACTORING ON 15-03-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_CR_Cancellation_of_assign_let extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print,m_Letter_date_2;

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
			
			String m_debtor_code="-",m_client_name="-",m_facility_no="-";
			String m_d_contact_person="-",m_d_contact_desig="-";
			
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
			out.println("<title>Cancellation of assigned letters</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			out.println("	  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_save_Legal_Letter_to_debtor?client_no="+m_client_no+"&facility_no="+m_facility_no+"&debtor_code="+m_debtor_code+"&type=FA_OP_CANCEL_ASSIGN_LETTER\";");  
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
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
		
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-Mon-YYYY'),TO_CHAR(SYSDATE,'ddth-Month-YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more){
				m_Letter_date=rs.getString(1);
				m_Letter_date_2=rs.getString(2);
				}
					
				rs1 = stmt1.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+//1
  				  " NVL(FULL_NAME,'-'), "+//2
  				  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
  				  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
  				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'), "+//6
						" NVL(DESIGNATION_PAYMENT,'CHIEF FINANCIAL OFFICER / ACCOUNTANT') "+//7 //modified by nuwan de silva on 20-01-2009
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
			out.println("<br><br><br><br><br><br><br>");
			out.println("<table border='0' width='100%' class='table'>");  		
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_Letter_date+"</td><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
			//out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'><b>Debtor</b></td><td width='*%'class='rep-body' style='{font:10px;text-align:left;}'><b>Client</b></td></tr>");
			//out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_contact_person+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>");  	
			out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_contact_desig+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
	  	out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_name+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_add1+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
		  out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_add2+",</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
      out.println("<tr><td width='60%' class='rep-body' style='{font:10px;text-align:left;}'>"+m_d_city_desc+".</td><td width='*%' class='rep-body' style='{font:10px;text-align:left;}'></td></tr>");
			out.println("</TABLE>");
			//out.println("<br>");
			   
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body'  style='{font:10px;text-align:left;}'><br>Dear Sir,</td></tr>");
			out.println("</TABLE><br>");		
			
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" We refer to the factoring agreement we have with <b>"+m_client_name+"</b> under which you are requested to pay "+
									" all debts due to <b>"+m_client_name+"</b> to <b>Lakderana Investments Limited </b> having its registered "+
									" office at No. 100, Buthgamuwa Road, Rajagiriya and the principle place of business at No. 100, Buthgamuwa Road, Rajagiriya carrying "+
									" on Factoring business under the name and style of <b><i>'Lakderana Factor'</i></b> (referred to as <b><i>'Lakderana Factor'</i></b> "+
									" herein and in all other documents as per the terms of <b>'Factoring Agreement'</b> entered into with <b><i>'Lakderana Factor'</i></b>) "+
									" ");
			out.println("</td></tr></table>");						
			out.println("<br>");						
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" This is to inform you that we, <b>Lakderana Factor</b> and our client <b>"+m_client_name+"</b> have now "+
									" mutually agreed to discontinue the factoring agreement with effect from <input class='txt_input6' type='text' name='TXT_LAWYER' maxlength='80' value='"+m_Letter_date_2+"' size='10' style='{font-family:Tahoma;font:10px;text-align:justify;width:100px;}' onblur=\"\"> "+
									" therefore, all outstanding and future debts now become payable to <b>"+m_client_name+"</b>. ");
			out.println("</td></tr></table>");						
			
			out.println("<br>");						
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("We take this opportunity to thank you for "+
									" the co-operation extended to us during the tenure of the factoring agreement and wish your company continued success. ");
			out.println("</td></tr></table>");						
			
			out.println("<br>");						
			

			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Yours faithfully,<br>");
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Lakderana Investments Limited<br>");	
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println("Lakderana Factor,<br><br><br><br>");				
			out.println("</td></tr> ");
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
			out.println(" ................................");
			out.println("</td></tr> ");
			out.println("</table>");						
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{font:10px;text-align:justify;}'>");
    	out.println(" Authorized Signatory ");	
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


	
}  
