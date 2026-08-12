
//SCREEN NAME:CLIENT DEBTOR APPROVAL LETTER 1
//DEVELOPED BY MAHELA FOR FACTORING ON 08-01-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_CR_Client_Debtor_Approval_Letter1 extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url;

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
			
			String m_Letter_date="",m_c_code="",m_add1="",m_add2="",m_name="",m_city_desc="",m_client_no="",m_print="";
		  double m_credit_limit,m_reverse_margin,m_int_rate=0;
			String m_debtor_code="",m_client_name="",m_facility_no="";
	
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_client_no=req.getParameter("client_no");		
			m_debtor_code=req.getParameter("debtor_code");		
			m_print=req.getParameter("print");
			//=======Modified by Dineth
			m_facility_no=req.getParameter("facility_no");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();		

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>To be sent by the client to the debtors </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			//===========Modified by Dineth on 2008-09-19
			out.println("function print_data(){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_CR_client_debtor_doc_issue_updation?doc_code=D001&client_code="+m_client_no+"&facility_no="+m_facility_no+"&debtor_code="+m_debtor_code+"\";"); 
			out.println(" window.location.href=m_url;");
			//===========End by Dineth on 2008-09-19
			out.println(" m_table.innerHTML=\"\" ");
			out.println(" window.print();");
			
			
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
			
			//out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			//out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	//out.println("m_writedata+'</table>';");
			out.println("}");

			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
			/*out.println("<INPUT TYPE='HIDDEN' NAME='HID_CLIENT_CODE' VALUE=\""+m_client_no+"\">");
			out.println("<INPUT TYPE='HIDDEN' NAME='HID_FACILITY_NO' VALUE=\""+m_facility_no+"\">");
			out.println("<INPUT TYPE='HIDDEN' NAME='HID_DEBTOR_CODE' VALUE=\""+m_debtor_code+"\">");
			out.println("<INPUT TYPE='HIDDEN' NAME='HID_DOC_CODE' VALUE=\"D001\">");*/
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			String m_cont_person="";
			String m_desig_payment="";
			String m_client_type="C";
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more){
				m_Letter_date=rs.getString(1);
				}
	
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
						m_c_code=rs1.getString(1);
						m_name=rs1.getString(2);
						m_add1=rs1.getString(3);
						m_add2=rs1.getString(4);
						m_city_desc=rs1.getString(5);
						m_cont_person=rs1.getString(6);
						m_desig_payment=rs1.getString(7);
						m_client_type=rs1.getString(8);
					}


				rs2 = stmt2.executeQuery (" SELECT "+
  				  " NVL(CLIENT_CODE,'-'), "+
  				  " NVL(FULL_NAME,'-') "+
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs2.next();
						if(more){
							m_client_name = rs2.getString(2);
						 }
						
			
			out.println("<p style='text-align:left'>");										
			out.println("<br><br><br><br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'><input class='txt_input6' type='text' name='TXT_DATE' maxlength='100' value='"+m_Letter_date+"' size='100' style='width:100'></td></tr>");
			if(m_client_type.equals("C")){
			//out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_cont_person+" </td></tr>");
			out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_desig_payment+" </td></tr>");
			}
	  	out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_name+" </td></tr>");
		  out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_add1+" </td></tr>");
		  out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_add2+" </td></tr>");
			out.println("<tr><td width='*%' class='factoring-letter-body'  >"+m_city_desc+" </td></tr>");//Added by Susitha 07-03-2011
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");
			out.println("<br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify'>");				
			out.println("<table border='0' width='100%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:left;}'><br>Dear Sir/s ,</td></tr>");
			out.println("</TABLE><br>");		
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='factoring-letter-body' >");			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("We hereby inform you that we have been granted factoring facilities for  "+
									" debts due from you by <b>Lakderana Investments Limited</b> having "+
									" its registered office No. 100, Buthgamuwa Road, Rajagiriya and the principle "+
									" place of business at No. 100, Buthgamuwa Road, Rajagiriya carrying on Factoring business "+
									" under the name and style of <b><i>'Lakderana Factor'</i></b> (referred to as <b><i>'Lakderana Factor'</i></b> herein and in "+
									" all other documents as per the terms of <b>'Factoring Agreement' </b> entered into with "+
									" <b><i>'Lakderana Factor'</i></b>.) Due to this arrangements we have been granted several benefits which "+
									" include specialized sales accounting services.<br>");
  		out.println("</td></tr>");									
			out.println("</table>");
			out.println("</font></p></blockquote>");				
			out.println("<blockquote><font size=2><p style='text-align:justify' class='factoring-letter-body'  >");				
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("In terms of the factoring agreement all our trade debts are assigned to <b><i>'Lakderana Factor'</i></b> "+
									" as such though you will be directly invoiced by us, <b><i>'Lakderana Factor'</i></b> will be sending you "+
									" statements of Accounts giving details of debts approved by them.");
			out.println("</td></tr>");									
			out.println("</table><br>");						
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='factoring-letter-body'  >");				
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("Please ensure that all dues of invoices issued by us should be paid to the payee <b>Lakderana Financial Services "+
									" Corporation Limited</b> from now on at No:46,48, Dr.N.M.Perera Mawatha, Colombo 08. We hereby advise you debts will be "+
									" discharged only on payment made directly to <b>Lakderana Financial Services Corporation Ltd.</b>");
  		out.println("</td></tr>");									
			out.println("</table><br>");															
			out.println("</font></p></blockquote>");
			
			// Added by Udara Somathilake on 17-05-2210
			out.println("<blockquote><font size=2><p style='text-align:justify' class='factoring-letter-body'  >");				
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body'  style='text-align:justify'>");
			out.println("<b>"+
								" This agreement will only be cancelled with the written consent of Lakderana Financial Services Corporation Limited 'Lakderana Factor'. "+
								" </b>");
  		   out.println("</td></tr>");									
			out.println("</table><br>");															
			out.println("</font></p></blockquote>");
			
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='factoring-letter-body'  >");				
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='factoring-letter-body' >");
			out.println("Please corporate with us enabling to provide you better service. ");
			out.println("</td></tr>");									
			out.println("</table><br><br>");								
			out.println("</font></p></blockquote>");
			out.println("<br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='factoring-letter-body' >");	
			out.println("Yours faithfully,<br>");	
			out.println(" "+m_client_name+" <br><br><br><br>");	
			out.println(" ................................<br><br>");
    	out.println(" Authorised Signatory.  <br>");	
			out.println("</font></p></blockquote></blockquote>");		
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
