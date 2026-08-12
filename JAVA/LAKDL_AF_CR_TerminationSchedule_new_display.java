//KANISHKA DILSHAN ON 05-12-2013
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
//import sun.misc.BASE64Decoder; 
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_TerminationSchedule_new_display extends javax.servlet.http.HttpServlet {
	
	
	
	public /*synchronized*/ void service(HttpServletRequest req, HttpServletResponse res)
	{
		Connection conn= null;
		Statement stmt= null,stmt1= null,stmt3= null;
		java.text.NumberFormat nf,nf1,nf2;
		ResultSet rs= null,rs1= null,rs2=null,rs_3= null;
		String m_chksql= null;
		ServletOutputStream out = null;	
		try {
			
			//************************************************************	
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();		
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			//nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(0);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);   
			nf2.setMinimumFractionDigits(2);
			nf2.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			nf1.setMaximumFractionDigits(4);
			
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			stmt3= conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("generate_letter")){
				
				
				
				String m_Letter_date="";
				String m_c_code="";
				String m_name="";
				String m_add1="";
				String m_add2="";
				String m_city_desc="";
				String m_designation="";
				String m_termiDate="";
				
				String m_cont_person="";
				String m_desig_payment="";
				String m_client_type="C";
				
				String m_print=req.getParameter("print");
				String m_due_amount	=	req.getParameter("due_amount");
				double m_due_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("due_amount")));
				String m_due_rent	=	req.getParameter("due_rent");
				String m_odi_amount = req.getParameter("odi_amount");
				String m_sale_price	=req.getParameter("sale_price");//stk
				String m_tot_chrg	= req.getParameter("tot_chrg");
				String m_client	= req.getParameter("client");
				String m_agreemnt_no = req.getParameter("agreemnt_no");
				String m_term_type	=	req.getParameter("term_type");
				String m_tDate	   =	req.getParameter("t_date");
				
				String m_future_capital			= req.getParameter("capital_outs");
				double m_future_capital_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("capital_outs")));
				String m_future_interest 		= req.getParameter("future_intrst");
				double m_future_interest_double = Double.parseDouble(con_method.met_unformat_number(req.getParameter("future_intrst")));
				String m_rebate_rate			= req.getParameter("rebate_rate");
				double m_rebate_rate_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("rebate_rate")));
				String m_rebate_amount			= req.getParameter("rebate_intrst");
				double m_rebate_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("rebate_intrst")));
				String future_min_rebate    	= req.getParameter("future_min_rebate");
				double future_min_rebate_double = Double.parseDouble(con_method.met_unformat_number(req.getParameter("future_min_rebate")));
				String odi 						= req.getParameter("odi");
				double odi_double 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi")));
				String odi_adj					= req.getParameter("odi_adj");
				double odi_adj_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_adj")));
				String odi_net					= req.getParameter("odi_net");
				double odi_net_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_net")));
				String termi					= req.getParameter("termi");
				double termi_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi")));
				//String odi_net				= req.getParameter("odi_net");
				double termi_vat 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi_vat")));
				
				double charges_vat 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("charges_vat")));
				
				
				out.println("<html><head>"); 
				out.println("<title></title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
				out.println("<script>");
				out.println("function print_data(){");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=generate_letter&due_amount="+m_due_amount+"&due_rent="+m_due_rent+"&odi_amount="+m_odi_amount+"&sale_price="+m_sale_price+"&tot_chrg="+m_tot_chrg+"&agreemnt_no="+m_agreemnt_no+"&term_type="+m_term_type+"&client="+m_client+"&print=false&t_date="+m_tDate+"&capital_outs="+m_future_capital+"&future_intrst="+m_future_interest+"&rebate_rate="+m_rebate_rate+"&rebate_intrst="+m_rebate_amount+"&future_min_rebate="+future_min_rebate+"&odi="+odi+"&odi_adj="+odi_adj+"&odi_net="+odi_net+"&termi="+termi+"&termi_vat="+termi_vat+"&charges_vat="+charges_vat+"\";");
				out.println(" window.location.href=m_url;");
				out.println(" m_table.innerHTML=\"\"; ");
				out.println(" window.print();");
				out.println("}");
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("false")) {
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
				out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/legal_letter_head.gif\"></DIV>");
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'ddth Month YYYY') FROM DUAL ");								
				
				if(rs.next()){
					m_Letter_date=rs.getString(1);
				}
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_tDate+"','DD-MM-YYYY'),'ddth Month YYYY') FROM DUAL ");								
				
				
				if(rs.next()){
					m_tDate=rs.getString(1);
				}
				
				
				
				
				//out.println (	" SELECT "+
				rs1 = stmt1.executeQuery (	" SELECT "+
					" NVL(A.CLIENT_CODE,'-'), "+//1
					" NVL(UPPER(A.FULL_NAME),'-'), "+//2
					" NVL(UPPER(A.REGISTERED_ADDRESS1),'-'), "+//3
					" NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+//4
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+//5
					" NVL(DESIGNATION,'-'), "+ //6
					" TO_CHAR(SYSDATE,'ddth Month YYYY') "+ //7
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
					" WHERE  A.CLIENT_CODE='"+m_client+"' ");
				
				
				if(rs1.next()){
					m_c_code=rs1.getString(1);
					m_name=rs1.getString(2);
					m_add1=rs1.getString(3);
					m_add2=rs1.getString(4);
					m_city_desc=rs1.getString(5);
					m_designation=rs1.getString(6);
					m_termiDate=rs1.getString(7);
				}
				
				if(m_term_type.trim().equals("ERL_TER")){
					
					out.println("<p style='text-align:left'>");										
					out.println("<blockquote><font size=2><p style='text-align:justify'>");	
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' >"+m_Letter_date+"</td></tr>");
					out.println("<tr><td width='*%'  >&nbsp;</td></tr>");
					out.println("<tr><td width='*%'  >"+m_designation+" </td></tr>");
					out.println("<tr><td width='*%'  >"+m_name+" </td></tr>");//
					out.println("<tr><td width='*%'  >"+m_add1+" </td></tr>");//
					out.println("<tr><td width='*%'  > "+m_add2+"</td></tr>");//
					out.println("<tr><td width='*%'  > "+m_city_desc+"</td></tr>");//
					out.println("</TABLE>");
					out.println("</font></p></blockquote>");
					out.println("<br><br>");
					out.println("<blockquote><font size=2><p style='text-align:justify'>");				
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
					out.println("</TABLE><br><br>");
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><u><b>AGREEMENT NO:"+m_agreemnt_no+"</b></u></td></tr>"); //LEASE 
					out.println("</TABLE><br>");		
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");			
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'  style='text-align:justify'>");
					out.println("We refer to your recent inquiry on the captioned subject and we give below"+
						" the early termination settlement amount <b>as at "+m_tDate+"</b> .<br>");
					out.println("</td></tr>");									
					out.println("</table>");
					out.println("</font></p></blockquote>");				
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");				
					out.println("<table border='0' width='500' class='table'>"); 
					out.println("<tr><td width='50%'  style='text-align:justify'></td>");
					out.println("<td width='10%'  style='text-align:right'>&nbsp;</td>");
					out.println("<td width='20%'  style='text-align:right'>Rs.</td>");
					out.println("<td width='20%'  style='text-align:right'>Rs.</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Future Capital</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double)+"</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Future Interest</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_interest_double)+"</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("</tr>");	
					out.println("<tr><td style='text-align:justify'>Less: Rebate on Future Interest</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_rebate_rate_double)+"%</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_rebate_amount_double)+"</td>");
					out.println("<td style='text-align:right'>"+nf2.format(future_min_rebate_double)+"</td>");
					out.println("</tr>");
					out.println("<tr height='1px'><td style='text-align:justify'></td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>______________</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Future Capital + Interest</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double + future_min_rebate_double)+"</td>");
					out.println("</tr>");	
					
					out.println("<tr><td style='text-align:justify'>Rentals Outstanding (Capital)   </td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+m_due_amount+"</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>VAT on Future Capital + Interest </td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_vat)+"</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_vat + m_due_amount_double)+"</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>Default Interest Charges</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(odi_double)+"</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>Less: Rebate on Default Interest Charges</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(odi_adj_double)+"</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>Net Interest Charges</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(odi_net_double)+"</td>");
					out.println("</tr>");
					
					
					out.println("<tr><td style='text-align:justify'>Termination Fee</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_double)+"</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>VAT on Transfer Fees</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(charges_vat)+"</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_double + charges_vat)+"</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>______________</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Total Settlement Amount</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double+future_min_rebate_double+m_due_amount_double+odi_net_double+termi_double+charges_vat+termi_vat)+"</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>==========</td>");
					out.println("</tr>");
					
					out.println("</table><br>");						
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' style='text-align:justify'>");
					out.println("Since you wish to terminate the above mentioned agreement, please settle the balance shown above on or before the settlement date.");
					out.println("</td></tr>");									
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' style='text-align:justify'>");
					out.println(""+
						" This offer is treated as expired after the settlement date mentioned above."+
						" ");
					out.println("</td></tr>");									
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					
					out.println("<br><br></td><br><br></td> ");
					
					
					
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'   style='text-align:justify'>Yours faithfully.</td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'><B>LAKDERANA INVESTMENTS LTD</B></BR></BR></BR></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>................................<br><br></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>Authorised Signatory.</td> ");
					out.println("</tr>");	
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					out.println("</form></body></html>");
					
					
				}else{
					
					
					
					out.println("<p style='text-align:left'>");										
					out.println("<blockquote><font size=2><p style='text-align:justify'>");	
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' >"+m_Letter_date+"</td></tr>");
					out.println("<tr><td width='*%'  >&nbsp;</td></tr>");
					out.println("<tr><td width='*%'  >"+m_designation+" </td></tr>");
					out.println("<tr><td width='*%'  >"+m_name+" </td></tr>");//
					out.println("<tr><td width='*%'  >"+m_add1+" </td></tr>");//
					out.println("<tr><td width='*%'  > "+m_add2+"</td></tr>");//
					out.println("<tr><td width='*%'  > "+m_city_desc+"</td></tr>");//
					out.println("</TABLE>");
					out.println("</font></p></blockquote>");
					out.println("<br><br>");
					out.println("<blockquote><font size=2><p style='text-align:justify'>");				
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
					out.println("</TABLE><br><br>");
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><u><b>AGREEMENT NO: "+m_agreemnt_no+"</b></u></td></tr>");
					out.println("</TABLE><br>");		
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");			
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'  style='text-align:justify'>");
					out.println("We wish to inform you that the above lease has expired on "+m_termiDate+","+
						"please be informed that the sum of <b>Rs."+m_tot_chrg+"</b> is due as detailed below.<br>");
					out.println("</td></tr>");									
					out.println("</table>");
					out.println("</font></p></blockquote>");				
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");				
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");				
					out.println("<table border='0' width='500' class='table'>"); 
					out.println("<tr><td width='50%'  style='text-align:justify'></td>");
					out.println("<td width='10%'  style='text-align:right'>&nbsp;</td>");
					out.println("<td width='20%'  style='text-align:right'>Rs.</td>");
					out.println("<td width='20%'  style='text-align:right'>Rs.</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Future Capital</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double)+"</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Future Interest</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_interest_double)+"</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("</tr>");	
					out.println("<tr><td style='text-align:justify'>Less: Rebate on Future Interest</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_rebate_rate_double)+"%</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_rebate_amount_double)+"</td>");
					out.println("<td style='text-align:right'>"+nf2.format(future_min_rebate_double)+"</td>");
					out.println("</tr>");
					out.println("<tr height='1px'><td style='text-align:justify'></td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>______________</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Future Capital + Interest</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double + future_min_rebate_double)+"</td>");
					out.println("</tr>");	
					
					out.println("<tr><td style='text-align:justify'>Rentals Outstanding (Capital)   </td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+m_due_amount+"</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>VAT on Future Capital + Interest   </td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_vat)+"</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_vat + m_due_amount_double)+"</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>Default Interest Charges</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(odi_double)+"</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>Less: Rebate on Default Interest Charges</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(odi_adj_double)+"</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>Net Interest Charges</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(odi_net_double)+"</td>");
					out.println("</tr>");
					
					
					out.println("<tr><td style='text-align:justify'>Termination Fee</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_double)+"</td>");
					out.println("</tr>");
					
					out.println("<tr><td style='text-align:justify'>VAT on Transfer Fees</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(charges_vat)+"</td>");
					out.println("<td style='text-align:right'>"+nf2.format(termi_double + charges_vat)+"</td>");
					out.println("</tr>");
					
					out.println("<tr height='1px'><td style='text-align:justify'></td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>______________</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'>Total Settlement Amount</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>"+nf2.format(m_future_capital_double+future_min_rebate_double+m_due_amount_double+odi_net_double+termi_double+charges_vat+termi_vat)+"</td>");
					out.println("</tr>");
					out.println("<tr><td style='text-align:justify'></td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>&nbsp;</td>");
					out.println("<td style='text-align:right'>==========</td>");
					out.println("</tr>");
					out.println("</table><br>");						
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' style='text-align:justify'>");
					out.println("In order to finalize this transaction by sale of vehicles, please forward your "+
						" remittance for <b>Rs."+m_tot_chrg+"</b> on or before "+m_Letter_date+" . "+
						" Overdue interest would be charged on this date onwards.");
					out.println("</td></tr>");									
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					
					
					
					
					out.println("<br><br></td><br><br></td> ");
					
					
					
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'   style='text-align:justify'>Yours faithfully.</td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'><B>LAKDERANA INVESTMENTS LTD</></BR></BR></BR></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>................................<br><br></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>Authorised Signatory.</td> ");
					out.println("</tr>");	
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					out.println("</form></body></html>");
					
					
				}
				
				
				
				
				/*
				out.println(" SELECT "+
				//rs2 = stmt2.executeQuery (" SELECT "+
  				  " NVL(CLIENT_CODE,'-'), "+
  				  " NVL(FULL_NAME,'-') "+
  				  " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client+"' ");

					more = rs2.next();
						if(more){
							m_client_name = rs2.getString(2);
						 }
						
			*/
				
				
				
			}
			else if(m_chksql.trim().equals("generate_Erl_letter")){//Create by kanishka Dilshan on 19-02-2014
				
				String m_Letter_date="";
				String m_c_code="";
				String m_name="";
				String m_name2="";
				String m_add1="";
				String m_add2="";
				String m_city_desc="";
				String m_designation="";
				String m_termiDate="";
				
				String m_cont_person="";
				String m_desig_payment="";
				String m_client_type="C";
				
				String m_print				= req.getParameter("print");
				String m_due_amount			= req.getParameter("due_amount");
				double m_due_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("due_amount")));
				String m_due_rent			= req.getParameter("due_rent");
				String m_odi_amount 		= req.getParameter("odi_amount");
				String m_sale_price			= req.getParameter("sale_price");//stk
				String m_tot_chrg			= req.getParameter("tot_chrg");
				String m_client				= req.getParameter("client");
				String m_agreemnt_no 		= req.getParameter("agreemnt_no");
				String m_term_type			= req.getParameter("term_type");
				String m_tDate	   			= req.getParameter("t_date");
				
				String m_future_capital			= req.getParameter("capital_outs");
				double m_future_capital_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("capital_outs")));
				String m_future_interest 		= req.getParameter("future_intrst");
				double m_future_interest_double = Double.parseDouble(con_method.met_unformat_number(req.getParameter("future_intrst")));
				String m_rebate_rate			= req.getParameter("rebate_rate");
				double m_rebate_rate_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("rebate_rate")));
				String m_rebate_amount			= req.getParameter("rebate_intrst");
				double m_rebate_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("rebate_intrst")));
				String future_min_rebate    	= req.getParameter("future_min_rebate");
				double future_min_rebate_double = Double.parseDouble(con_method.met_unformat_number(req.getParameter("future_min_rebate")));
				String odi 						= req.getParameter("odi");
				double odi_double 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi")));
				String odi_adj					= req.getParameter("odi_adj");
				double odi_adj_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_adj")));
				String odi_net					= req.getParameter("odi_net");
				double odi_net_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_net")));
				String termi					= req.getParameter("termi");
				double termi_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi")));
				//String odi_net				= req.getParameter("odi_net");
				double termi_vat 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi_vat")));
				
				double charges_vat 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("charges_vat")));
				
				String vehicle_no				= req.getParameter("vehicle_no");//kanishka
				vehicle_no  					= vehicle_no.replaceAll("@","");//kanishka
				vehicle_no 						= ((vehicle_no.equals("null")) ? "-" : vehicle_no);//kanishka
				
				String m_print_name				= req.getParameter("name");
				String m_print_add1 			= req.getParameter("add1");
				String m_print_add2				= req.getParameter("add2");
				String m_print_city	   			= req.getParameter("city");
				
				out.println("<html><head>"); 
				out.println("<title></title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
				out.println("<script>");
				out.println("function print_data(){");
				
				out.println("  var m_name = document.Form1.name.value;");
				out.println("  var m_add1 = document.Form1.add1.value;");
				out.println("  var m_add2 = document.Form1.add2.value;");
				out.println("  var m_city = document.Form1.city.value;");
				
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=generate_Erl_letter&due_amount="+m_due_amount+"&due_rent="+m_due_rent+"&odi_amount="+m_odi_amount+"&sale_price="+m_sale_price+"&tot_chrg="+m_tot_chrg+"&agreemnt_no="+m_agreemnt_no+"&term_type="+m_term_type+"&client="+m_client+
					"&print=false&t_date="+m_tDate+"&capital_outs="+m_future_capital+"&future_intrst="+m_future_interest+"&rebate_rate="+m_rebate_rate+
					"&rebate_intrst="+m_rebate_amount+"&future_min_rebate="+future_min_rebate+"&odi="+odi+"&odi_adj="+odi_adj+"&odi_net="+odi_net+"&termi="+termi+
					"&termi_vat="+termi_vat+"&vehicle_no="+vehicle_no+"&charges_vat="+charges_vat+
					"&name=\"+m_name+\"&add1=\"+m_add1+\"&add2=\"+m_add2+\"&city=\"+m_city;");
				out.println(" window.location.href=m_url;");
				out.println(" m_table.innerHTML=\"\"; ");
				out.println(" setTimeout(window.print(),3000);");
				out.println("}");
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("false")) {
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
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/legal_letter_head.gif\"></DIV>");//temp commented by milinda 2014-05-20
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'ddth Month YYYY') FROM DUAL ");								
				
				if(rs.next()){
					m_Letter_date=rs.getString(1);
				}
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_tDate+"','DD-MM-YYYY'),'ddth Month YYYY') FROM DUAL ");								
				
				if(rs.next()){
					m_tDate=rs.getString(1);
				}
				
				//out.println (	" SELECT "+
				rs1 = stmt1.executeQuery (	" SELECT "+
					" NVL(A.CLIENT_CODE,'-'), "+//1
					//" NVL(UPPER(A.FULL_NAME),'-'), "+//2
					" NVL(INITCAP(A.TITLE),'')||' '||NVL(A.INITIALS,'')||' '|| NVL(INITCAP(A.SURNAME),''),"+//2
					//" NVL(DECODE(UPPER(A.REGISTERED_ADDRESS1)),'-'), "+//3
					//" NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+//4
					" NVL(DECODE(A.CLIENT_TYPE,'C',A.REGISTERED_ADDRESS1,'I',A.ADDRESS1),'-'),"+// 3 ADDED MILINDA 2014-05-20
					" NVL(DECODE(A.CLIENT_TYPE,'C',A.REGISTERED_ADDRESS2,'I',A.ADDRESS2),'-'),"+//4
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+//5
					" NVL(DESIGNATION,'-'), "+ //6
					" TO_CHAR(SYSDATE,'ddth Month YYYY'), "+ //7
					" NVL(INITCAP(A.TITLE),'')||' '||NVL(INITCAP(A.FIRST_NAME),'')||' '|| NVL(INITCAP(A.SURNAME),'')"+//8
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
					" WHERE  A.CLIENT_CODE='"+m_client+"' ");
				
				
				
				
				if(rs1.next()){
					m_c_code	= rs1.getString(1);
					m_name		= rs1.getString(2);
					m_add1		= rs1.getString(3);
					m_add2		= rs1.getString(4);
					m_city_desc	= rs1.getString(5);
					m_designation = rs1.getString(6);
					m_termiDate	  = rs1.getString(7);
					m_name2		  = rs1.getString(8);
				}
				
				out.println("<p style='text-align:left'>");										
				out.println("<blockquote><font size=2><p style='text-align:justify'>");	
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' >"+m_Letter_date+"</td></tr>");
				out.println("<tr><td width='*%'  >&nbsp;</td></tr>");
				//out.println("<tr><td width='*%'  >"+m_designation+" </td></tr>");
				if (m_print.trim().equals("false")) {
					out.println("<tr><td width='*%'  >"+m_print_name+"</td></tr>");
					out.println("<tr><td width='*%'  >"+m_print_add1+"</td></tr>");
					out.println("<tr><td width='*%'  >"+m_print_add2+"</td></tr>");
					out.println("<tr><td width='*%'  >"+m_print_city+"</td></tr>");
				}else{
					out.println("<tr><td width='*%'  ><font size=5><input type=text name=\"name\" value=\""+m_name+"\" style=\"width:350;\" ></font></td></tr>");
					out.println("<tr><td width='*%'  ><font size=5><input type=text name=\"add1\" value=\""+m_add1+"\" style=\"width:350;\" ></font></td></tr>");
					out.println("<tr><td width='*%'  ><font size=5><input type=text name=\"add2\" value=\""+m_add2+"\" style=\"width:350;\" ></font></td></tr>");
					out.println("<tr><td width='*%'  ><font size=5><input type=text name=\"city\" value=\""+m_city_desc+"\" style=\"width:350;\" ></font></td></tr>");
				}
				out.println("</TABLE>");
				out.println("</font></p></blockquote>");
				out.println("<br>");
				out.println("<blockquote><font size=2><p style='text-align:justify'>");				
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='*%'  style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
				out.println("</TABLE><br><br>");
				out.println("<table border='0' width='100%' class='table'> ");	
				out.println("<tr ><td width='200px' style='{text-align:left;}'><b>REFERENCE NUMBER</b></td> <td width='*%'  style='{text-align:left;}'><b>: "+m_agreemnt_no+"</b></td></tr>");
				out.println("<tr ><td width='200px' style='{text-align:left;}'><b>CLIENT NAME</b></td>      <td width='*%'  style='{text-align:left;}'><b>: "+m_name2+"</b></td></tr>");
				out.println("<tr ><td width='200px' style='{text-align:left;}'><b>VEHICLE NO</b></td> 		<td width='*%'  style='{text-align:left;}'><b>: "+vehicle_no+"</b></td></tr>");
				out.println("</TABLE><br>");		
				out.println("</font></p></blockquote>");
				out.println("<blockquote><font size=5><p style='text-align:justify'  >");			
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%'  style='text-align:justify'>");
				out.println("We write with reference to the above and wish to inform that the settlement balance pertaining to the above contract "+
					"is Rs: "+nf2.format(m_future_capital_double+future_min_rebate_double+m_due_amount_double+odi_net_double+termi_double+charges_vat+termi_vat)+" with interest concessions. This settlement is valid for a period of 14 days from the above date.<br>");
				out.println("</td></tr>");
				out.println("<tr><td width='*%'  style='text-align:justify'>");
				out.println("This letter is issued at the request of our client "+m_name2+".<br>");
				out.println("</td></tr>");
				out.println("</table>");
				out.println("</font></p></blockquote>");				
				
				out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%'   style='text-align:justify'>Yours faithfully.</td> ");
				out.println("</tr>");	
				out.println("<tr><td width='*%'   style='text-align:justify'><B>LAKDERANA INVESTMENTS LTD</></BR></BR></BR></td> ");
				out.println("</tr>");	
				out.println("<tr><td width='*%'   style='text-align:justify'>................................<br><br></td> ");
				out.println("</tr>");	
				out.println("<tr><td width='*%'   style='text-align:justify'>SNR.MANAGER - RECOVERIES</td> ");
				out.println("</tr>");	
				out.println("</table><br>");															
				out.println("</font></p></blockquote>");
				out.println("</form></body></html>");
				
				
				
			}else if(m_chksql.trim().equals("generate_Erl_Quatation")){//Create by kanishka Dilshan on 20-02-2014
				
				String m_Letter_date="";
				String m_c_code="";
				String m_name="";
				String m_name2="";
				String m_add1="";
				String m_add2="";
				String m_city_desc="";
				String m_designation="";
				String m_termiDate="";
				
				String m_cont_person="";
				String m_desig_payment="";
				String m_client_type="C";
				
				String m_print				= req.getParameter("print");
				String m_due_amount			= req.getParameter("due_amount");
				double m_due_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("due_amount")));
				String m_due_rent			= req.getParameter("due_rent");
				double m_due_rent_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("due_rent")));
				String m_odi_amount 		= req.getParameter("odi_amount");
				double m_odi_amount_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_amount")));
				String m_sale_price			= req.getParameter("sale_price");//stk
				String m_tot_chrg			= req.getParameter("tot_chrg");
				String m_client				= req.getParameter("client");
				String m_agreemnt_no 		= req.getParameter("agreemnt_no");
				String m_term_type			= req.getParameter("term_type");
				String m_tDate	   			= req.getParameter("t_date");
				String m_tDate2             = m_tDate;
				
				String m_rebate_rate			= req.getParameter("rebate_rate");
				double m_rebate_rate_double 	= Double.parseDouble(con_method.met_unformat_number(req.getParameter("rebate_rate")));
				String odi 						= req.getParameter("odi");
				double odi_double 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi")));
				String odi_adj					= req.getParameter("odi_adj");
				double odi_adj_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_adj")));
				String odi_net					= req.getParameter("odi_net");
				double odi_net_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("odi_net")));
				String termi					= req.getParameter("termi");
				double termi_double 			= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi")));
				//String odi_net				= req.getParameter("odi_net");
				double termi_vat 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("termi_vat")));
				
				double charges_vat 				= Double.parseDouble(con_method.met_unformat_number(req.getParameter("charges_vat")));
				/*
				String vehicle_no				= req.getParameter("vehicle_no");//kanishka
				vehicle_no  					= vehicle_no.replaceAll("@",",");//kanishka
				vehicle_no 						= ((vehicle_no.equals("null")) ? "-" : vehicle_no);//kanishka
				
				String m_print_name				= req.getParameter("name");
				String m_print_add1 			= req.getParameter("add1");
				String m_print_add2				= req.getParameter("add2");
				String m_print_city	   			= req.getParameter("city");
				*/
				out.println("<html><head>"); 
				out.println("<title></title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
				out.println("<script>");
				out.println("function print_data(){");
				
				//out.println("  var m_name = document.Form1.name.value;");
				//out.println("  var m_add1 = document.Form1.add1.value;");
				//out.println("  var m_add2 = document.Form1.add2.value;");
				//out.println("  var m_city = document.Form1.city.value;");
				
				out.println("  var m_name;");
				out.println("  var m_add1;");
				out.println("  var m_add2;");
				out.println("  var m_city;");
				
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=generate_Erl_Quatation&due_amount="+m_due_amount+"&due_rent="+m_due_rent+"&odi_amount="+m_odi_amount+"&sale_price="+m_sale_price+"&tot_chrg="+m_tot_chrg+"&agreemnt_no="+m_agreemnt_no+"&term_type="+m_term_type+"&client="+m_client+
					"&print=false&t_date="+m_tDate+"&capital_outs=&future_intrst=&rebate_rate="+m_rebate_rate+"&rebate_intrst=&future_min_rebate=&odi="+odi+"&odi_adj="+odi_adj+"&odi_net="+odi_net+"&termi="+termi+
					"&termi_vat="+termi_vat+"&charges_vat="+charges_vat+ //&vehicle_no="+vehicle_no+"
					"&name=\"+m_name+\"&add1=\"+m_add1+\"&add2=\"+m_add2+\"&city=\"+m_city;");
				
				out.println(" window.location.href=m_url;");
				out.println(" m_table.innerHTML=\"\"; ");
				out.println(" window.print();");
				out.println("}");
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("false")) {
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
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/legal_letter_head.gif\"></DIV>");//COMPANY LETTER HEAD
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY HH:MI AM') FROM DUAL ");								
				
				if(rs.next()){
					m_Letter_date=rs.getString(1);
				}
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(TO_DATE('"+m_tDate+"','DD-MM-YYYY'),'ddth Month YYYY') FROM DUAL ");								
				
				if(rs.next()){
					m_tDate=rs.getString(1);
				}
				
				String Sql_data =	"  SELECT "+												
					//"  NVL(SUM(D.TOTAL_AMOUNT),0), "+ //1
					"  NVL("+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(B.APPLICATION_NO),0),"+
					"  TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY'), "+//2I
					"  NVL(C.PAYMENT_INTERVAL,0), "+//3
					"  NVL(C.RATE,0), "+//4
					"  NVL(D.NET_RENTAL_AMOUNT,0), "+//5
					"  NVL(D.GRENTAL_AMOUNT,0), "+//6
					"  NVL(C.PERIOD,0) "+//7
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
					"  "+m_schema_name+".AF_CO_PRO_APP_PRICING C,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT D"+
					"  WHERE A.APPLICATION_NO="+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_agreemnt_no+"')  "+
					"  AND A.APPLICATION_NO = B.APPLICATION_NO  "+
					"  AND A.PRICING_NO 	= C.PRICING_NO  "+
					"  AND A.PRICING_NO     = D.PRICING_NO  "+
					"  AND A.APPLICATION_NO = D.APPLICATION_NO  "+
					"  AND A.ACTIVE_STATUS='Y' "+
					"  GROUP BY TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY'),NVL(C.PAYMENT_INTERVAL,0),NVL(C.RATE,0), "+
					"  NVL(D.NET_RENTAL_AMOUNT,0),NVL(D.GRENTAL_AMOUNT,0),NVL(C.PERIOD,0),B.APPLICATION_NO ";
				
				String Sql_data_asset_details = "  SELECT "+												
					" A.INVOICE_NO,"+ //1
					" A.APPLICATION_NO,  "+ //2
					" A.ASSET_ID, "+ //3
					" C.MAKE_CODE, "+ //4
					" D.MAKE_DESC, "+ //5
					" A.MODEL_CODE, "+ //6
					" C.DESCRIPTION, "+ //7
					" NVL(A.ENGINE_NO,'-'), "+ //8
					" NVL(A.CHASSIS_NO,'-'), "+ //9
					" '1' /*QTY*/, "+//10  
					" DECODE(B.STATUS,'U','Used','N','New','R','Re-Condition'), "+ //11
					" ROUND((E.VAT_PERCENTAGE-E.VAT_APP)/100*E.NET_AMOUNT + E.NET_AMOUNT,0), "+//12
					" NVL(A.REG_NO,'-'), "+ //13
					" NVL(F.DESCRIPTION,'-'), "+ //14
					" NVL("+m_schema_name+".AF_CO_GET_MODEL_DESC(A.MODEL_CODE),'-') "+ //15
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B, "+
					" "+m_schema_name+".AF_CO_MAS_MODEL C,"+m_schema_name+".AF_CO_MAS_MAKE D, "+
					" "+m_schema_name+".AF_CO_PRO_APP_PRICING E, "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F "+
					" WHERE A.APPLICATION_NO="+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_agreemnt_no+"')  AND "+
					" A.ASSET_ID=B.ASSET_ID AND "+
					" A.APPLICATION_NO=E.APPLICATION_NO AND "+
					" A.INVOICE_NO=E.PRO_INVOICE_NO AND  "+
					" C.MODEL_CODE=A.MODEL_CODE AND "+
					" C.MAKE_CODE=D.MAKE_CODE AND "+
					" C.ITEM_SUB_CAT=F.ITEM_SUB_CAT AND "+
					" A.ACTIVE_STATUS='Y' AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" C.ACTIVE_STATUS='Y' AND "+ 
					" D.ACTIVE_STATUS='Y' AND  "+
					" "+m_schema_name+".AF_CO_GET_TRANS_TYPE(A.APPLICATION_NO) NOT IN (SELECT TRAN_CODE FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE WHERE TRAN_CODE LIKE '%LT%') "+
					" ORDER BY A.ASSET_ID ";
				
				String Sql_data_rent_details =	" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+//1
					"	     "+m_schema_name+".AF_CO_GET_RENTALS_FALLEN_DUE(FINANCE_NO),"+//2
					"	     "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+//3
					"        NVL("+m_schema_name+".AF_CO_GET_ODI_ARR_2(FINANCE_NO,'"+m_tDate2+"'),0), "+//4
					//"        NVL("+m_schema_name+".AF_CO_GET_APP_CHARG_ARR_AMT(APPLICATION_NO,'"+m_tDate2+"'),0), "+//5
					"        NVL("+m_schema_name+".AF_CO_GET_APP_CHARG_ARR_AMT_2(APPLICATION_NO,'"+m_tDate2+"'),0), "+//5// Change By Kanishka On 30-10-2014
					"        NVL("+m_schema_name+".AF_CO_GET_APP_CHARG_SETTLE_AMT(APPLICATION_NO,'"+m_tDate2+"'),0), "+//6
					"        NVL("+m_schema_name+".AF_CO_GET_RENTAL_PAID_AMT(FINANCE_NO),0), "+//7
					"        NVL("+m_schema_name+".AF_CO_GET_APP_INT_SUSPENSE2(APPLICATION_NO,FINANCE_NO,CLIENT_CODE,'"+m_tDate2+"'),0), "+//8
					//"        NVL("+m_schema_name+".AF_CO_GET_RENTAL_ARREARS(FINANCE_NO,'"+m_tDate2+"'),0) "+//9 // commented by udara 13-05-2014
					"        NVL("+m_schema_name+".AF_CO_GET_RENTAL_ARREARS_2(FINANCE_NO,'"+m_tDate2+"'),0) "+//9 // added by udara 13-05-2014
					
					"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"	WHERE  FINANCE_NO= '"+m_agreemnt_no+"'";
				
				String	Sql_data_valuation_details="  SELECT "+												
					" TO_CHAR(A.VALUATION_DATE,'DD-MM-YYYY'), "+ //1
					" A.VALUER_CODE, "+ //2
					" UPPER(B.FIRST_NAME || ' ' || B.LAST_NAME), "+ //3
					" YEAR_OF_MANUFACTURE, "+ //4
					" NVL(VALUE,0), "+ //5
					" NVL(FORCED_SALES_VALUE,0)   "+  //6
					" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A, "+m_schema_name+".AF_CO_MAS_VALUERS B "+
					" WHERE A.APPLICATION_NO="+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_agreemnt_no+"') AND "+
					" B.VALUER_CODE=A.VALUER_CODE AND  "+
					" A.ACTIVE_STATUS='Y' AND "+
					" B.ACTIVE_STATUS='Y' "+
					" ORDER BY A.ASSET_ID ";
				
				String Sql_data_TERMI_details =		" SELECT SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),  "+
					"        SUM(TERMINATION_PV), "+
					"        SUM(TERMINATION_PV-TERMINATION_AMOUNT),SUM(INTEREST), "+
					"        SUM(INTEREST)-SUM(TERMINATION_AMOUNT) "+
					" FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
					" WHERE  ENT_USER='"+m_username+"' ";
				
				String Sql_data_invoice_details =	" SELECT SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),  "+
					"        SUM(TERMINATION_PV), "+
					"        SUM(TERMINATION_PV-TERMINATION_AMOUNT),SUM(INTEREST), "+
					"        SUM(INTEREST)-SUM(TERMINATION_AMOUNT) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE  FINANCE_NO= '"+m_agreemnt_no+"' ";
				
				
				
				//out.println (	" SELECT "+
				rs1 = stmt1.executeQuery (	" SELECT "+
					" NVL(A.CLIENT_CODE,'-'), "+//1
					//" NVL(UPPER(A.FULL_NAME),'-'), "+//2
					" NVL(INITCAP(A.TITLE),'')||' '||NVL(A.INITIALS,'')||' '|| NVL(INITCAP(A.SURNAME),''),"+//2
					" NVL(UPPER(A.REGISTERED_ADDRESS1),'-'), "+//3
					" NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+//4
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+//5
					" NVL(DESIGNATION,'-'), "+ //6
					" TO_CHAR(SYSDATE,'ddth Month YYYY'), "+ //7
					" NVL(INITCAP(A.TITLE),'')||' '||NVL(INITCAP(A.FIRST_NAME),'')||' '|| NVL(INITCAP(A.SURNAME),'')"+//8
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
					" WHERE  A.CLIENT_CODE='"+m_client+"' ");
				
				if(rs1.next()){
					m_c_code	= rs1.getString(1);
					m_name		= rs1.getString(2);
					m_add1		= rs1.getString(3);
					m_add2		= rs1.getString(4);
					m_city_desc	= rs1.getString(5);
					m_designation = rs1.getString(6);
					m_termiDate	  = rs1.getString(7);
					m_name2		  = rs1.getString(8);
				}
				
				//out.println (	" SELECT "+
				rs1 = stmt1.executeQuery ("SELECT COMPANY_NAME,ADDRESS1,ADDRESS2,CITY FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
				String m_com_name="", m_com_add1="", m_com_add2="", m_city="";
				
				if(rs1.next()){
					m_com_name=rs1.getString(1);
					m_com_add1=rs1.getString(2);
					m_com_add2=rs1.getString(3);
					m_city=rs1.getString(4);
				}
				
				rs1 = stmt1.executeQuery (Sql_data);
				
				String m_grant_date="", m_period="",m_payment_interval="";
				double m_interest   = 0.00, m_net_installment = 0.00 ,m_gross_installment = 0.00 , m_amnt_grant = 0.00;
				
				if(rs1.next()){
					m_amnt_grant 		=	rs1.getDouble(1);
					m_grant_date 		=	rs1.getString(2);
					m_payment_interval	=	rs1.getString(3);
					m_interest 			=	rs1.getDouble(4);
					m_net_installment 	=	rs1.getDouble(5);
					m_gross_installment =	rs1.getDouble(6);
					m_period 			=	rs1.getString(7);
					
				}
				
				rs1 = stmt1.executeQuery(Sql_data_rent_details);
				double m_future_rental_no = 0,	m_rental_fallen_due =0 ,m_rental_areas_no = 0;
				double  m_oth_chrgs_arrs = 0.00 , m_oth_chrgs_settle = 0.00 , m_odi_arrs = 0.00, m_int_suspense = 0.00,m_rental_paid = 0.00;
				
				if(rs1.next()){
					m_future_rental_no		=	rs1.getDouble(1);
					m_rental_fallen_due		=	rs1.getDouble(2);
					m_rental_areas_no		=	rs1.getDouble(3);
					m_odi_arrs				=	rs1.getDouble(4);
					m_oth_chrgs_arrs		=	rs1.getDouble(5);
					m_oth_chrgs_settle		=	rs1.getDouble(6);
					m_rental_paid      		=	rs1.getDouble(7);
					m_int_suspense      	=	rs1.getDouble(8);
					m_due_rent_double		=	rs1.getDouble(9);
					
				}
				
				rs1 = stmt1.executeQuery(Sql_data_TERMI_details);
				double m_rental_amount = 0.00,	m_future_capital =0.00 ,m_termination_amount = 0.00 , m_future_interest = 0.00, m_future_int_rebate = 0.00;
				
				if(rs1.next()){
					m_rental_amount			=	rs1.getDouble(1);
					m_future_capital		=	rs1.getDouble(2);
					m_termination_amount	=	rs1.getDouble(3);
					m_future_interest		=	rs1.getDouble(6);
					m_future_int_rebate		=	rs1.getDouble(7);
				}
				//out.println(Sql_data_asset_details);
				//out.println(Sql_data_valuation_details);
				
				
				String temp = "&nbsp;"; // temporary variable for testing 
				int bdr 	= 0;  // table border width for below tables [test alignments]
				int bdr_1 	= 1;  // table border width for below tables [required border] KEEP IT '1'
				
				out.println("<p style='text-align:left;font-size:8px;'>");										
				out.println("<blockquote><p style='text-align:justify'>");	
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' style='text-align:right'  colspan='4'>&nbsp;</td><td width='10%'>REF NO :</td></tr>");
				out.println("<tr><td width='*%' style='text-align:center' colspan='5'><b><font size=2>"+m_com_name+"</b></font></td></tr>");
				out.println("<tr><td width='*%' style='text-align:center' colspan='5'><b>Quotation for Early Settlement</b></td></tr>");
				
				out.println("<tr><td width='160px;'  >Client Name		</td><td width='200px;'  >: "+m_name2+"</td> 		<td width='*%' colspan='3'>&nbsp;</td></tr>");
				out.println("<tr><td width='160px;'  >Facility No		</td><td width='200px;'  >: "+m_agreemnt_no+"</td> 	<td width='*%' colspan='3'>&nbsp;</td></tr>");
				out.println("<tr><td width='160px;'  >Date & Print Time	</td><td width='200px;'  >: "+m_Letter_date+"</td> 	<td width='*%' colspan='3'>&nbsp;</td></tr>");
				out.println("<tr><td width='160px;'  >Termination as at	</td><td width='200px;'  >: "+m_tDate+"</td> 		<td width='*%' colspan='3'>&nbsp;</td></tr>");
				out.println("</TABLE>");
				
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 
				out.println("<tr height='5px;'><td width='*%' colspan='5' >&nbsp;</td></tr>");
				out.println("<tr><td width='*%' colspan='5' ><U><B>Facility Details</B></U></td></tr>");
				
				out.println("<tr><td width='160px;'  >Amount Granted	 </td><td width='50px;' >&nbsp;</td><td width='*%' colspan='3' >: "+nf2.format(m_amnt_grant)        +"</td></tr>");
				out.println("<tr><td width='160px;'  >Granted Date		 </td><td width='50px;' >&nbsp;</td><td width='*%' colspan='3' >: "+m_grant_date                   +"</td></tr>");
				out.println("<tr><td width='160px;'  >Interest Rate		 </td><td width='50px;' >&nbsp;</td><td width='*%' colspan='3' >: "+nf2.format(m_interest) 		   +"%</td></tr>");
				out.println("<tr><td width='160px;'  >Period			 </td><td width='50px;' >&nbsp;</td><td width='*%' colspan='3' >: "+m_period         			   +"</td></tr>");
				out.println("<tr><td width='160px;'  >Monthly Installment</td><td width='50px;' >Net   </td><td width='*%' colspan='3' >: "+nf2.format(m_net_installment)   +"</td></tr>");
				out.println("<tr><td width='160px;'  >&nbsp;			 </td><td width='50px;' >Gross </td><td width='*%' colspan='3' >: "+nf2.format(m_gross_installment) +"</td></tr>");
				out.println("</TABLE>");
				
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 
				out.println("<tr height='5px;'><td width='*%' colspan='5' >&nbsp;</td></tr>");
				out.println("<tr><td width='*%' colspan='5' ><U><B>Asset Details</B></U></td></tr>");
				
				rs1  = stmt3.executeQuery(Sql_data_asset_details);
				
				out.println("<tr><td width='20%'  >Asset Type</td><td width='20%' >Asset</td><td width='20%' >Make</td><td width='20%' >Model</td><td width='20%' >Vehicle No</td></tr>");
				boolean asset_flag=false;
				
				if(rs1.next()){
					out.println("<tr><td width='20%'  >"+rs1.getString(11)+"</td><td width='20%' >"+rs1.getString(7)+"</td><td width='20%' >"+rs1.getString(5)+"</td><td width='20%' >"+rs1.getString(15)+"</td><td width='20%' >"+rs1.getString(13)+"</td></tr>");
					asset_flag = rs1.next();
				}else{
					out.println("<tr><td width='20%'  > N/A </td><td width='20%' > N/A </td><td width='20%' > N/A </td><td width='20%' > N/A </td><td width='20%' > N/A </td></tr>");
				}
				
				while(asset_flag){
					out.println("<tr><td width='20%'  >"+rs1.getString(11)+"</td><td width='20%' >"+rs1.getString(7)+"</td><td width='20%' >"+rs1.getString(5)+"</td><td width='20%' >"+rs1.getString(15)+"</td><td width='20%' >"+rs1.getString(13)+"</td></tr>");
					asset_flag = rs1.next();	
				}
				
				out.println("<tr height='5px;'><td  width='*%' colspan='5' >&nbsp;</td></tr>");
				
				rs1 = stmt3.executeQuery(Sql_data_valuation_details);
				
				boolean valuer_flag=false;
				if(rs1.next()){
					out.println("<tr><td width='20%'  >1st Valuation </td><td width='20%' >:- Date : "+rs1.getString(1)+"</td><td width='20%' >Market Value : "+nf2.format(rs1.getDouble(5))+"</td><td width='20%' >Forced Sale Value : "+nf2.format(rs1.getDouble(6))+"</td><td width='20%' >Valuer : "+rs1.getString(3)+"</td></tr>");
					valuer_flag = rs1.next();
				}else{
					out.println("<tr><td width='20%'  >1st Valuation </td><td width='20%' >:- Date : N/A </td><td width='20%' >Market Value : N/A </td><td width='20%' >Forced Sale Value : N/A </td><td width='20%' >Valuer : N/A </td></tr>");
				}
				while(valuer_flag){
					out.println("<tr><td width='20%'  >&nbsp;</td><td width='20%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs1.getString(1)+"</td><td width='20%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+nf2.format(rs1.getDouble(5))+"</td><td width='20%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:"+nf2.format(rs1.getDouble(6))+"</td><td width='20%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:"+rs1.getString(3)+"</td></tr>");
					valuer_flag = rs1.next();
				}
				//out.println("<tr><td width='20%'  >New Valuation </td><td width='20%' >:- Date :"+temp+"</td><td width='20%' >Market Value :"+temp+"</td><td width='20%' >Forced Sale Value :"+temp+"</td><td width='20%' >Valuer :"+temp+"</td></tr>");
				out.println("</TABLE>");
				
				
				
				
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 
				out.println("<tr height='5px;'><td width='*%' colspan='6' >&nbsp;</td></tr>");
				out.println("<tr><td width='*%' colspan='6' ><U><B>Payment Details</B></U></td></tr>");
				
				//out.println("<tr><td width='20%'  >Capital Paid           </td><td width='2%' >:</td><td width='18%' style='{text-align:right;}'>"+temp+"</td><td width='5%' >&nbsp;</td><td width='35%' >No.Of Installment Fallen Due</td> 	<td width='20%' >: "+temp+"??</td></tr>");
				//out.println("<tr><td width='20%'  >Interest Paid          </td><td width='2%' >:</td><td width='18%' style='{text-align:right;}'>"+temp+"</td><td width='5%' >&nbsp;</td><td width='35%' >No.Of Installment Settled</td> 		<td width='20%' >: "+m_rental_fallen_due+"</td></tr>");
				double tot_paid = m_rental_paid + m_odi_amount_double + m_oth_chrgs_settle;
				out.println("<tr><td width='20%'  >Rental Paid            </td><td width='2%' >:</td><td width='18%' style='{text-align:right;}'>"+nf2.format(m_rental_paid)+"</td><td width='5%' >&nbsp;</td><td width='35%' >No.Of Installment Fallen Due</td> 	<td width='20%' >: "+m_rental_fallen_due+"</td></tr>");
				out.println("<tr><td width='20%'  >Over Due Interest Paid </td><td width='2%' >:</td><td width='18%' style='{text-align:right;}'>"+nf2.format(m_odi_amount_double)+"</td><td width='5%' >&nbsp;</td><td width='35%' >No.Of Installment Settled</td><td width='20%' >: "+nf1.format((m_rental_fallen_due - m_rental_areas_no < 0 ) ? 0 : (m_rental_fallen_due - m_rental_areas_no))+"</td></tr>");
				out.println("<tr><td width='20%'  >Other Charges Paid     </td><td width='2%' >:</td><td width='18%' style='{text-align:right;}'>"+nf2.format(m_oth_chrgs_settle) +"</td><td width='5%' >&nbsp;</td><td width='35%' >No.Of Installment in Arrears</td> 	<td width='20%' >: "+nf1.format(m_rental_areas_no)+"</td></tr>");
				out.println("<tr><td width='20%'  >Total Paid			  </td><td width='2%' >:</td><td width='18%' style='{text-align:right;}'>"+nf2.format(tot_paid)+"</td><td width='5%' >&nbsp;</td><td width='35%' >No.Of Installment future</td> 		<td width='20%' >: "+m_future_rental_no+"</td></tr>");
				out.println("</TABLE>");
				
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 
				out.println("<tr height='5px;'><td width='*%' colspan='5' >&nbsp;</td></tr>");
				out.println("<tr><td width='*%' colspan='5' ><U><B>Settlement Details</B></U></td></tr>");
				double tot_recevibles = m_future_capital + m_future_interest + m_due_rent_double + m_odi_amount_double + m_oth_chrgs_arrs; //m_oth_chrgs_arrs added by Kanishka On 30-10-2014
				out.println("<tr><td width='20%'  >Future Capital			</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_future_capital)   +"</td><td width='*%' colspan='2' >&nbsp;</td></tr>");
				out.println("<tr><td width='20%'  >Future Interest			</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_future_interest)  +"</td><td width='*%' colspan='2' >&nbsp;</td></tr>");
				out.println("<tr><td width='20%'  >Rental Arrears			</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_due_rent_double)  +"</td><td width='*%' colspan='2' >&nbsp;</td></tr>");
				out.println("<tr><td width='20%'  >ODI Arrears				</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_odi_arrs)         +"</td><td width='*%' colspan='2' >&nbsp;</td></tr>");
				out.println("<tr><td width='20%'  >Other Charges Arrears	</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_oth_chrgs_arrs)   +"</td><td width='*%' colspan='2' >&nbsp;</td></tr>");
				out.println("<tr><td width='20%'  >Total Receivables		</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(tot_recevibles)     +"</td><td width='*%' colspan='2' >&nbsp;</td></tr>");
				
				out.println("<tr height='5px;'><td width='*%' colspan='5' >&nbsp;</td></tr>");
				out.println("</TABLE>");
				
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 
				out.println("<tr><td width='20%'  >Interest in Suspense	</td><td width='20%' >:"+nf2.format(m_int_suspense)+"</td><td width='7%' >&nbsp;</td><td width='20%'  >Bad Debt Provision ("+temp+"%)</td><td width='*%' >:"+temp+"</td></tr>");
				out.println("</TABLE>");
				
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 
				out.println("<tr height='5px;'><td width='*%' colspan='7' >&nbsp;</td></tr>");
				out.println("<tr><td width='*%' colspan='5' ><U><B>Settlement Proposal</B></U></td></tr>");
				
				out.println("<tr><td width='20%'  >Rental Arrears			</td><td width='*%' colspan='4' >&nbsp;</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_due_rent_double)+"</td></tr>");
				out.println("<tr><td width='20%'  >Future Capital			</td><td width='*%' colspan='4' >&nbsp;</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_future_capital) +"</td></tr>");
				
				out.println("<tr height='3px;'><td width='*%' colspan='7' >&nbsp;</td></tr>");
				
				out.println("<tr><td width='20%'  >Future Interest 			</td><td width='2%' >:</td><td width='15%' style='{text-align:right;}' >"+nf2.format(m_future_interest)+"</td><td width='34%' >Less :-Discount("+nf2.format(m_rebate_rate_double)+"%): ( "+nf2.format(m_future_int_rebate)+")   </td><td width='7%' >Balance</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+nf2.format(m_future_interest - m_future_int_rebate)+"</td></tr>");
				out.println("<tr><td width='20%'  >ODI Arrears				</td><td width='2%' >:</td><td width='15%' style='{text-align:right;}' >"+nf2.format(m_odi_arrs)       +"</td><td width='34%' >Less :-Discount("+temp+"%):( "+temp+")   </td><td width='7%' >Balance</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+temp+"</td></tr>");
				out.println("<tr><td width='20%'  >Other Charges Arrears	</td><td width='2%' >:</td><td width='15%' style='{text-align:right;}' >"+nf2.format(m_oth_chrgs_arrs) +"</td><td width='34%' >Less :-Discount("+temp+"%):( "+temp+")   </td><td width='7%' >Balance</td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+temp+"</td></tr>");
				out.println("<tr><td width='20%'  >Total Receivables		</td><td colspan='3'style='text-align:center;'><b>Propose Final Settlement (as at "+m_tDate+")</B></td><td width='7%' >&nbsp; </td><td width='2%' >:</td><td width='20%' style='{text-align:right;}'>"+temp+"</td></tr>");
				out.println("</TABLE>");
				
				out.println("<table border='"+bdr+"' width='90%' class='table'>"); 
				out.println("<tr height='5px;'><td width='*%' colspan='5' >&nbsp;</td></tr>");
				out.println("<tr><td width='*%' colspan='5' ><U><B>Impact of Proposal Settlement</B></U></td></tr>");
				
				out.println("<tr><td width='20%'  >Impact to P&L &nbsp;:"+temp+" </td><td width='20%' >Future Interest : "+nf2.format(m_future_interest)+"</td><td width='20%' >ODI :"+temp+"</td><td width='20%' >Int.In Sus :"+temp+"</td><td width='20%' >Dad Debt Pro :"+temp+"</td></tr>");
				out.println("<tr><td width='20%'  >IRR :"+temp+" %</td>               <td colspan='4' >&nbsp;</td></tr>");
				
				out.println("<tr><td width='*%' colspan='5' ><B>Client Total Exposure : "+temp+"	</B></td></tr>");
				out.println("<tr><td width='*%' colspan='5' ><B>Remarks : "+temp+"	</B></td></tr>");
				out.println("<tr height='5px;'><td width='*%' colspan='5' >&nbsp;</td></tr>");
				out.println("<tr><td width='*%' colspan='5' >&nbsp;</td></tr>");
				
				out.println("</TABLE>");
				
				out.println("<table border='"+bdr_1+"' width='90%' style='border-style:solid;' bordercolor='black'   cellspaing='0' cellpadding='0' >"); 
				out.println("<tr style='text-align:center;'> 	<td width='20%' >Prepared by  </td><td width='20%' >Confirmed by      </td><td width='20%' >Recommended                   </td><td width='20%' >Approved by </td></tr>");
				out.println("<tr height='30px;'> 				<td width='20%' >&nbsp;       </td><td width='20%' >&nbsp;            </td><td width='20%' >&nbsp;                        </td><td width='20%' >&nbsp;      </td></tr>");
				out.println("<tr style='text-align:center;'>	<td width='20%' >&nbsp;       </td><td width='20%' >Finance Department</td><td width='20%' >Snr.Manager-Rec / HO R & Legal</td><td width='20%' >COO / CEO   </td></tr>");
				
				out.println("</TABLE>");
				
				out.println("</form></body></html>");
				
			}
			else if(m_chksql.trim().equals("main_page")){
				
				// added by udara 30-10-2018
				String m_charge_applicable = "N";
				double m_ODI_FROM = 0; // added by udara 01-11-2018
				double m_ODI_TO = 0; // added by udara 01-11-2018
				
				rs = stmt.executeQuery (" "+
					"SELECT NVL(CHARGE_APPLICABLE,'N'),  "+
							" REBATE_FROM, "+
							" REBATE_TO, "+
							" ODI_FROM, "+
							" ODI_TO "+
					" FROM "+m_schema_name+".AF_AD_REBATE_CONFIGURATION "+
					" WHERE USER_ID = '"+m_username+"'  "+ 
				" ");								
				
				if(rs.next()){
					m_charge_applicable = rs.getString(1);
					m_ODI_FROM = rs.getDouble(4); // added by udara 01-11-2018
					m_ODI_TO = rs.getDouble(5); // added by udara 01-11-2018
				}
				// end by dara 30-10-2018
				
				String m_Followu_no   = "";//req.getParameter("Followu_no");
				//Added By Kanishka Dilshan On 12-01-2015
				String can_save = "YES";
				if(req.getParameter("calculate_only") !=null && req.getParameter("calculate_only").equals("Y")){
					can_save = "NO"; 
				}
				//End By Kanishka Dilshan On 12-01-2015
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Asset Financing System</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//Check Values Using AJAX
				out.println("function makeRequest(url,opt,type) {");
				out.println("var http_request = false;");
				out.println("if (window.XMLHttpRequest) {"); // Mozilla, Safari,...
				out.println("    http_request = new XMLHttpRequest();");
				out.println("    if (http_request.overrideMimeType) {");
				out.println("        http_request.overrideMimeType('text/xml');");
				out.println("    }");
				out.println("} else if (window.ActiveXObject) { ");// IE
				out.println("    try {");
				out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
				out.println("    } catch (e) {");
				out.println("        try {");
				out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
				out.println("        } catch (e) {}");
				out.println("    }");
				out.println("}");
				out.println("if (!http_request) {");
				out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
				out.println("    return false;");
				out.println("}");
				out.println(" if(opt=='1'){");
				out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
				out.println("  http_request.open('POST',url, true);");
				out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
				out.println("	 m_send_val = \"chksql=get_advance_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
				
				out.println("  http_request.send(m_send_val);");
				
				out.println(" }else{");
				
				out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt,type); };");
				out.println("  http_request.open('GET',url, true);");
				//out.println("  window.open(url);");
				out.println("  http_request.send(null);");
				out.println(" }");
				out.println("}");
				
				out.println("function alertContents(http_request,type) {");
				//out.println(" alert('test');");
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				//	out.println("         window.open(http_request.responseText);");
				out.println("         price_cal.innerHTML=http_request.responseText; ");
				out.println("    } else {");
				out.println("        alert('There was a problem with the request.');");
				out.println("    }");
				out.println(" }");
				out.println("}");
				
				out.println("function alertGetContents(http_request,opt,type) {");
				//out.println(" alert('test--'+opt);");
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				out.println("      if(opt==\"2\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         window.open(http_request.responseText);");
				out.println("         rec.innerHTML=http_request.responseText; ");
				out.println("         assign_div();");
				out.println("      }else if(opt==\"7\"){");
				out.println("         veh.innerHTML=http_request.responseText; ");
				//Added by Dineth on 2008-12-03
				out.println("         var no_veh=document.Form1.hid_vcount.value;");
				out.println("         if(parseInt(no_veh)>1){ ");
				out.println("             document.Form1.BUT_ARREARS.disabled=false;");
				out.println("            }");
				//End by Dineth on 2008-12-03
				out.println("         get_term_details();");
				out.println("      }else if(opt==\"3\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				out.println("         f=document.Form1.hid_opt_val.value ;");
				out.println("         g=document.Form1.hid_win_opt.value ;");
				out.println("         if(parseFloat(unformat_noobject(document.Form1.tot_val.value))<=parseFloat(document.Form1.elements[\"BAL_AMOUNT_\"+f].value)){");
				out.println("           popupwin.document.Form1.tot_val.value=document.Form1.tot_val.value; ");
				out.println("           popupwin.total.innerHTML=format_noobject(document.Form1.tot_val.value); ");
				out.println("           document.Form1.elements[\"SETT_AMOUN_\"+f].value=format_noobject(document.Form1.tot_val.value); ");
				
				out.println("         }else{");
				out.println("           popupwin.document.Form1.elements['Text_standard'+g].value      =\"NO\";");
				out.println("           popupwin.document.Form1.elements['Text_sett_amount'+g].disabled=false;");
				out.println("           popupwin.document.Form1.elements['Text_standard'+g].checked    =false;");
				
				out.println("         }");
				
				out.println("      }else if(opt==\"6\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				out.println("         document.Form1.elements[\"SETT_AMOUN_0\"].value=format_noobject(document.Form1.tot_val.value); ");
				
				out.println("      } ");
				
				// added by udara 23-10-2018
				out.println("      else if(opt==\"8\"){");
				//out.println("           alert(http_request.responseText); ");
				
				out.println("   	data_vec= new Array(); ");
				out.println("       var xmlbody=http_request.responseXML.documentElement;");
				out.println("       var vsize=0;");
				out.println("  		for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("    		for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("      			data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("       		vsize++;");
				out.println("    		}");
				out.println("   	}");
				
				//out.println("       alert(data_vec.length);   ");
				out.println("       	var data_vec_length = data_vec.length;   ");
				out.println("       	if(data_vec_length==0 && document.Form1.TER_RATE.value!=''){   ");
				out.println("              alert('Invalid rebate percentage');  ");
				out.println("              document.Form1.TER_RATE.value = ''; ");
				out.println("           } ");
				
				
				out.println("      } ");
				
				out.println("      else if(opt==\"9\"){");
				//out.println("           alert(http_request.responseText); ");
				
				out.println("   	data_vec= new Array(); ");
				out.println("       var xmlbody=http_request.responseXML.documentElement;");
				out.println("       var vsize=0;");
				out.println("  		for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("    		for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("      			data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("       		vsize++;");
				out.println("    		}");
				out.println("   	}");
				
				//out.println("       alert(data_vec.length);   ");
				out.println("       	var data_vec_length = data_vec.length;   ");
				out.println("       	if(data_vec_length==0 && document.Form1.ODI_ADJ_PER.value!='0.00'){   ");
				out.println("              alert('Invalid ODI percentage');  ");
				out.println("              document.Form1.ODI_ADJ_PER.value = '0.00'; ");
				//out.println("         			document.Form1.ODI.value='0.00'; "); // added by udara 01-02-2019 // commented by udara 13-02-2019
				//out.println("         			document.Form1.ODI_NET.value='0.00'; "); // added by udara 01-02-2019 // commented by udara 13-02-2019
				out.println("           } ");
				out.println("           else{ ");
				out.println("               cal_odi_per('1'); ");
				out.println("           } ");
				
				
				out.println("      } ");
				// end by udara 23-10-2018
				
				out.println("      else if(opt==\"5\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         rec.innerHTML=http_request.responseText; ");
				out.println("      } ");
				
				// added by udara 06-01-2021
				out.println(" else if(opt==\"77\"){");
				out.println("         var respondValue = http_request.responseText; ");
				out.println("         var respondValueArry = respondValue.split('-'); ");
				out.println("  		var obj = document.getElementById('FUTURE_DEBIT'); ");
				out.println(" 			if(respondValueArry[0]=='Yes'){ ");
				out.println("  	 		document.getElementById('moratorium').innerHTML = '<b>Moratorium - Yes</b>'"); 
				out.println(" 			}else{ ");
				out.println("  		 	document.getElementById('moratorium').innerHTML = '<b>Moratorium - No</b>'");
				out.println(" 		 	}");
 
				out.println("  	document.getElementById('FUTURE_DEBIT').value = respondValueArry[1];"); 
				out.println("  	check_number_decimal(obj,21);"); 
				out.println(" }");
				// end by udara 06-01-2021
					
				out.println(" else if(opt==\"4\"){");
				out.println("   				data_vec= new Array(); ");
				
				out.println("           var xmlbody=http_request.responseXML.documentElement;");
				out.println("           var vsize=0;");
				
				out.println("  				  for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("    				 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("      			  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("       			vsize++;");
				//alert(data_vec[vsize]);
				out.println("    				}");
				out.println("   		   }");
				
				out.println("          addrow(data_vec,type);");
				
				out.println("      }");
				out.println("    } else {");
				//out.println("        load_followup.innerHTML='';");
				out.println("    }");
				out.println(" }");
				out.println("}");
				
				out.println(" var prev_ODI_NET_AMOUNT = '';   "); // added by udara 14-02-2019
				
				out.println("function addrow( data,type) {");
				out.println(" str=\"\";");
				out.println(" i=0;");
				out.println(" if(data.length>0){");
				out.println("   if(type=='Rec'){"); 
				out.println("     document.Form1.TERMINATION_NO.value       =data[0];"); 
				//out.println("     get_Receipt_del();");
				out.println("   }else if(type=='Cli'){"); 
				out.println("     document.Form1.CLIENT_CODE.value     =data[0];"); 
				out.println("     document.Form1.CLIENT_NAME.value     =data[1];"); 
				//out.println("     get_Receipt();");
				out.println("   }else if(type=='Veh'){"); 
				//out.println("     document.Form1.VEHICLE_NO.value      =data[0];"); 
				out.println("     document.Form1.LEASE_NO.value        =data[0];"); 
				out.println("     document.Form1.APPLICATION_NO.value  =data[1];"); 
				out.println("     document.Form1.CLIENT_CODE.value     =data[2];"); 
				out.println("     check_due_inv();");
				//out.println("     get_Receipt();");
				out.println("   }else if(type=='Lea'){"); 
				out.println("     document.Form1.LEASE_NO.value        =data[3];"); 
				out.println("     document.Form1.APPLICATION_NO.value  =data[0];"); 
				out.println("     document.Form1.CLIENT_CODE.value     =data[1];"); 
				out.println("     document.Form1.TRN_TYPE.value        =data[4];"); 
				out.println("     document.Form1.ODI.value             =data[12];"); 
				out.println("     document.Form1.ODI_NET.value         =data[12];"); 	
				out.println("     document.Form1.Unallo_Rec.value      =data[13];"); 
				
				out.println("     document.Form1.LEASE_RATE.value      =data[14];"); 
				out.println("     document.Form1.DUE_AMOUNT.value      =data[15];"); 
				out.println("     document.Form1.DUE_NET.value         =data[16];"); 	
				out.println("     document.Form1.DUE_VAT.value         =data[17];"); 	
				out.println("     get_due_rent_sum();");
				//out.println("     check_due_inv();");
				out.println("   }else if(type=='ODI_NET'){"); 
				out.println("     prev_ODI_NET_AMOUNT = document.Form1.ODI_NET.value; "); // added by udara 14-02-2019
				out.println("     document.Form1.ODI_NET.value         =data[0];"); 	
				//out.println("     cal_odi_per('2'); ");//Added By Kanishka On 31-12-2014 // commented by udara 01-11-2018
				out.println("     check_odi_adj_amount_new(); "); // added by udara 01-11-2018
				out.println("   }else if(type=='DUE'){"); 
				out.println("     document.Form1.DUE_AMOUNT.value      =data[0];"); 
				out.println("     document.Form1.DUE_NET.value         =data[1];"); 	
				out.println("     document.Form1.DUE_VAT.value         =data[2];"); 	
				out.println("     check_lease_rate();");
				out.println("   }else if(type=='IRR'){"); 
				out.println("	   document.Form1.CLOSURE_IRR.value     =data[0];");
				out.println("   }else if(type=='CAP'){");//       CAP_SETT   CAP_SETT_PER
				out.println("     document.Form1.AMOUNT_FINANCE.value  =data[0];"); 
				out.println("     document.Form1.NIBSM.value           =data[1];"); 
				out.println("     document.Form1.AMI.value             =data[2];"); 
				out.println("     document.Form1.CAP_OUT.value         =data[3];"); 
				out.println("     document.Form1.CAP_OUT_PER.value     =data[5];"); 
				out.println("     document.Form1.VAT_PER.value         =data[6];"); 
				out.println("     document.Form1.hid_int_amount.value  =data[7];");
				out.println("     document.Form1.FU_INTE.value         =data[7];");//ADDED MILINDA
				//out.println("     alert(document.Form1.hid_int_amount.value);");	
				out.println("   }else if(type=='TCOUNT'){");
				out.println("     document.Form1.TER_COUNT.value       =data[0];"); 
				out.println("     if(document.Form1.TER_COUNT.value>'0'){");
				out.println("     	 check_term_char();");
				out.println("     }else{ ");
				out.println("       document.Form1.TERM_AMOUNT.value       ='0'; ");
				out.println("       get_term_vehicles();");
				//out.println("       check_due_inv();");
				out.println("     } ");
				out.println("   }else if(type=='TCHAR'){"); 
				out.println("     document.Form1.TERM_AMOUNT.value      =data[0];");
				out.println("     get_term_vehicles();");
				out.println("   }else if(type=='LEASERATE'){"); 
				out.println("     document.Form1.LEASE_RATE.value       =data[0];"); 
				out.println("     check_client();");
				out.println("   }else if(type=='TERATE'){"); 
				out.println("   }else if(type=='LERATE'){"); 
				out.println("     document.Form1.LEASE_RATE.value       =data[0];"); 
				out.println("     get_due_rent_sum();");
				out.println("   }else if(type=='DUER'){"); 
				out.println("     document.Form1.DUE_RENTALS.value       =data[0];"); 
				out.println("     document.Form1.DUE_RENTALS_NET.value   =data[1];"); 
				out.println("     document.Form1.DUE_RENTALS_VAT.value   =data[2];"); 
				
				out.println("     document.Form1.TER_COUNT.value       =data[3];"); 
				out.println("       document.Form1.TERM_AMOUNT.value   =data[4]; ");
				out.println("       get_term_vehicles();");
				
				//out.println("     check_term_count();");
				out.println("       setTimeout(function(){ getMoratoriumValues(); }, 500);  "); // added by udara 06-01-2021
				
				out.println("   }else if(type=='TermNo'){"); 
				out.println("     document.Form1.TERMINATION_NO.value   =data[0];"); 
				out.println("     document.Form1.LEASE_NO.value         =data[1];"); 
				out.println("     document.Form1.APPLICATION_NO.value   =data[2];"); 
				out.println("     document.Form1.CLIENT_CODE.value      =data[3];"); 
				//out.println("     document.Form1.LEASE_RATE.value       =data[4];"); 
				//out.println("     document.Form1.LEASE_RATE.value       =data[5];"); 
				out.println("     document.Form1.REQ_BY.value           =data[6];"); 
				out.println("     document.Form1.TER_RATE.value         =data[7];"); 
				//out.println("     document.Form1.TERM_AMOUNT.value      =data[8];"); 
				out.println("     document.Form1.REMARK.value           =data[9];"); 
				out.println("     document.Form1.TERM_AMOUNT.value      =data[10];"); 
				out.println("     document.Form1.TER_COUNT.value        =data[11];");
				out.println("     document.Form1.DUE_AMOUNT.value       =data[12];");
				out.println("     document.Form1.hid_cal_date.value='3';");
				out.println("     load_c_date(data[4]);");
				out.println("     document.Form1.hid_cal_date.value='2';");
				out.println("     load_c_date(data[5]);");
				out.println("     befor_cal();	");
				out.println("   }"); 
				out.println(" }else{");
				out.println("  if(type=='Cli'){"); 
				out.println("   client_help();"); //Added by Chandana on 06/08/2007 for Ref no.759  
				out.println("  }");	
				out.println("}");	
				out.println("}");
				//End Of Checking Values
				
				out.println("function Generate_Letter(m_due_amount,m_due_rent,m_odi_amount,m_sale_price,m_tot_chrg,m_client,m_agreemnt_no){");
				out.println("var m_term_type    = document.Form1.TERM_TYPE.value;");
				out.println("var terDate        = document.Form1.TER_V_DAY.value+'-'+document.Form1.TER_V_MONTH.value+'-'+document.Form1.TER_V_YEAR.value;");
				out.println("var capital_outs   = document.Form1.CAP_OUT.value;");//kanishka
				//out.println("var future_intrst  = document.Form1.h_in.value;");//kanishka // commented by udara 26-02-2019
				out.println("var future_intrst  = document.Form1.FU_INTE.value;"); // added by udara 26-02-2019
				out.println("var rebate_rate    = document.Form1.TER_RATE.value;");//kanishka
				out.println("var rebate_intrst  = document.Form1.h_inr.value;");//kanishka
				out.println("var future_min_rebate  = parseFloat(unformat_noobject(future_intrst)) - parseFloat(unformat_noobject(rebate_intrst));");//kanishka
				out.println("var odi            = document.Form1.ODI.value;");//kanishka
				out.println("var odi_adj        = document.Form1.ODI_ADJ.value;");//kanishka
				out.println("var odi_net        = document.Form1.ODI_NET.value;");//kanishka
				out.println("var termi        	= document.Form1.TERM_AMOUNT.value;");//kanishka
				out.println("var termi_vat      = document.Form1.h_vat.value;");//kanishka
				out.println("var charges_vat    = document.Form1.T_V.value;");//kanishka
				out.println("var gain_loss      = document.Form1.h_term.value;");//kanishka
				out.println("var tot_payable    = document.Form1.hid_total_payable.value;");//kanishka
				out.println("var unallocated_receipt    = document.Form1.Unallo_Rec.value;");//MILINDA
				
				out.println(" document.Form1.cash_f.disabled = true;  "); // added by udara 05-02-2019
				out.println("var future_debit_notes    = document.Form1.FUTURE_DEBIT.value;"); // added by udara 06-01-2021
				
				/*
				//out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=generate_letter&due_amount=\"+m_due_amount+ ");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_Letter?chksql=generate_letter&due_amount=\"+m_due_amount+ ");
				out.println("      \"&due_rent=\"+m_due_rent+\"&odi_amount=\"+m_odi_amount+\"&sale_price=\"+m_sale_price+\"&tot_chrg=\"+m_tot_chrg+\"&agreemnt_no=\"+m_agreemnt_no+");
				out.println("      \"&term_type=\"+m_term_type+\"&client=\"+m_client+\"&print=true&t_date=\"+terDate+\"&capital_outs=\"+capital_outs+ ");
				out.println("      \"&future_intrst=\"+future_intrst+\"&rebate_rate=\"+rebate_rate+\"&rebate_intrst=\"+rebate_intrst+\"&future_min_rebate=\"+future_min_rebate+ ");
				out.println("      \"&odi=\"+odi+\"&odi_adj=\"+odi_adj+\"&odi_net=\"+odi_net+\"&termi=\"+termi+\"&termi_vat=\"+termi_vat+\"&charges_vat=\"+charges_vat+\"&gain_loss=\"+gain_loss+\"&tot_payable=\"+tot_payable+\"&unallocated_receipt=\"+unallocated_receipt;");
				
				out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=700,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				*/
				
				out.println("if(m_sub_odi=='1'){");
				out.println("    alert('ODI values are changed and please re-calculate'); "); 
				out.println("}  ");
				out.println("else{  ");
				out.println("    if(m_sub_rebate=='1'){");
				out.println("        alert('Rebate values are changed and please re-calculate'); "); 
				out.println("    }"); 
				out.println("    else{"); 
				out.println("         m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_Letter?chksql=generate_letter&due_amount=\"+m_due_amount+ ");
				out.println("         \"&due_rent=\"+m_due_rent+\"&odi_amount=\"+m_odi_amount+\"&sale_price=\"+m_sale_price+\"&tot_chrg=\"+m_tot_chrg+\"&agreemnt_no=\"+m_agreemnt_no+");
				out.println("         \"&term_type=\"+m_term_type+\"&client=\"+m_client+\"&print=true&t_date=\"+terDate+\"&capital_outs=\"+capital_outs+ ");
				out.println("         \"&future_intrst=\"+future_intrst+\"&rebate_rate=\"+rebate_rate+\"&rebate_intrst=\"+rebate_intrst+\"&future_min_rebate=\"+future_min_rebate+ ");
				out.println("         \"&odi=\"+odi+\"&odi_adj=\"+odi_adj+\"&odi_net=\"+odi_net+\"&termi=\"+termi+\"&termi_vat=\"+termi_vat+\"&charges_vat=\"+charges_vat+\"&gain_loss=\"+gain_loss+\"&tot_payable=\"+tot_payable+\"&unallocated_receipt=\"+unallocated_receipt+\"&future_debit_notes=\"+future_debit_notes;"); // added future_debit_notes by udara 06-01-2021
				
				out.println("          window.open(m_url,'displayWindow2','left=50,top=60,width=700,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("    }"); 
				
				out.println("}  ");
				
				out.println("}");	
				
				
				out.println("function Generate_Erl_Quatation(m_due_amount,m_due_rent,m_odi_amount,m_sale_price,m_tot_chrg,m_client,m_agreemnt_no,m_vehicle_no){");
				out.println("var m_term_type    	= document.Form1.TERM_TYPE.value;");
				out.println("var terDate        	= document.Form1.TER_V_DAY.value+'-'+document.Form1.TER_V_MONTH.value+'-'+document.Form1.TER_V_YEAR.value;");
				out.println("var capital_outs   	= document.Form1.CAP_OUT.value;");//kanishka
				out.println("var future_intrst  	= document.Form1.h_in.value;");//kanishka
				out.println("var rebate_rate    	= document.Form1.TER_RATE.value;");//kanishka
				out.println("var rebate_intrst  	= document.Form1.h_inr.value;");//kanishka
				out.println("var future_min_rebate  = parseFloat(unformat_noobject(future_intrst)) - parseFloat(unformat_noobject(rebate_intrst));");//kanishka
				out.println("var odi            	= document.Form1.ODI.value;");//kanishka
				out.println("var odi_adj        	= document.Form1.ODI_ADJ.value;");//kanishka
				out.println("var odi_net        	= document.Form1.ODI_NET.value;");//kanishka
				out.println("var termi        		= document.Form1.TERM_AMOUNT.value;");//kanishka
				out.println("var termi_vat      	= document.Form1.h_vat.value;");//kanishka
				out.println("var charges_vat    	= document.Form1.T_V.value;");//kanishka
				
				
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=generate_Erl_Quatation&due_amount=\"+m_due_amount+ ");
				out.println("      \"&due_rent=\"+m_due_rent+\"&odi_amount=\"+m_odi_amount+\"&sale_price=\"+m_sale_price+\"&tot_chrg=\"+m_tot_chrg+\"&agreemnt_no=\"+m_agreemnt_no+");
				out.println("      \"&term_type=\"+m_term_type+\"&client=\"+m_client+\"&print=true&t_date=\"+terDate+\"&capital_outs=\"+capital_outs+ ");
				out.println("      \"&future_intrst=\"+future_intrst+\"&rebate_rate=\"+rebate_rate+\"&rebate_intrst=\"+rebate_intrst+\"&future_min_rebate=\"+future_min_rebate+ ");
				out.println("      \"&odi=\"+odi+\"&odi_adj=\"+odi_adj+\"&odi_net=\"+odi_net+\"&termi=\"+termi+\"&termi_vat=\"+termi_vat+\"&charges_vat=\"+charges_vat;");//+\"&vehicle_no=\"+m_vehicle_no+\"&name=&add1=&add2=&city=\" 'Commented By Kanishka on 26-05-2014'
				
				out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=700,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");		
				
				out.println("function Generate_Erl_Letter(m_due_amount,m_due_rent,m_odi_amount,m_sale_price,m_tot_chrg,m_client,m_agreemnt_no,m_vehicle_no){");
				out.println("var m_term_type    	= document.Form1.TERM_TYPE.value;");
				out.println("var terDate        	= document.Form1.TER_V_DAY.value+'-'+document.Form1.TER_V_MONTH.value+'-'+document.Form1.TER_V_YEAR.value;");
				out.println("var capital_outs   	= document.Form1.CAP_OUT.value;");//kanishka
				out.println("var future_intrst  	= document.Form1.h_in.value;");//kanishka
				out.println("var rebate_rate    	= document.Form1.TER_RATE.value;");//kanishka
				out.println("var rebate_intrst  	= document.Form1.h_inr.value;");//kanishka
				out.println("var future_min_rebate  = parseFloat(unformat_noobject(future_intrst)) - parseFloat(unformat_noobject(rebate_intrst));");//kanishka
				out.println("var odi            	= document.Form1.ODI.value;");//kanishka
				out.println("var odi_adj        	= document.Form1.ODI_ADJ.value;");//kanishka
				out.println("var odi_net        	= document.Form1.ODI_NET.value;");//kanishka
				out.println("var termi        		= document.Form1.TERM_AMOUNT.value;");//kanishka
				out.println("var termi_vat      	= document.Form1.h_vat.value;");//kanishka
				out.println("var charges_vat    	= document.Form1.T_V.value;");//kanishka
				
				
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=generate_Erl_letter&due_amount=\"+m_due_amount+ ");
				out.println("      \"&due_rent=\"+m_due_rent+\"&odi_amount=\"+m_odi_amount+\"&sale_price=\"+m_sale_price+\"&tot_chrg=\"+m_tot_chrg+\"&agreemnt_no=\"+m_agreemnt_no+");
				out.println("      \"&term_type=\"+m_term_type+\"&client=\"+m_client+\"&print=true&t_date=\"+terDate+\"&capital_outs=\"+capital_outs+ ");
				out.println("      \"&future_intrst=\"+future_intrst+\"&rebate_rate=\"+rebate_rate+\"&rebate_intrst=\"+rebate_intrst+\"&future_min_rebate=\"+future_min_rebate+ ");
				out.println("      \"&odi=\"+odi+\"&odi_adj=\"+odi_adj+\"&odi_net=\"+odi_net+\"&termi=\"+termi+\"&termi_vat=\"+termi_vat+\"&charges_vat=\"+charges_vat+\"&vehicle_no=\"+m_vehicle_no+\"&name=&add1=&add2=&city=\";");
				
				out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=700,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");	
				
				
				out.println(" function process_print(){");
				out.println("   M4.innerHTML=\"\"; ");
				out.println(" 	window.print(); ");
				out.println("	M4.innerHTML='<input type=button name=cash_f  value=\"Letter\" class=mainbut onclick=Generate_Letter(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\")>';");
				out.println("	M4.innerHTML+='&nbsp;&nbsp;<input type=\"button\" name=\"Termination_Sheet\" Style=\"width:180px;\" value=\"Print Termination Sheet\" class=\"mainbut\" onclick=\"process_print();\">';");//Added By Kanishka Dilshan On 06-01-2015
				out.println("   if(document.Form1.TERM_TYPE.value ==\"ERL_TER\"){ ");//Created by kanishka dilshan on 19-02-2014
				out.println(" 		var vehicle_no		= document.Form1.VEHICLE_NO.value;");
				out.println("		M4.innerHTML+='&nbsp;&nbsp;<input type=button name=early_ter_letter Style=\"width:150px;\" value=\"Early Termination Letter\" class=mainbut onclick=Generate_Erl_Letter(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\",\"'+vehicle_no+'\");>';");
				//out.println("		M4.innerHTML+='&nbsp;&nbsp;<input type=\"button\" name=\"early_ter_quatation\" Style=\"width:180px;\" value=\"Early Termination Quatation\" class=\"mainbut\" onclick=Generate_Erl_Quatation(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\",\"'+vehicle_no+'\");>';");
				out.println("		M4.innerHTML+='&nbsp;&nbsp;';");
				out.println("   }");
				out.println(" } ");
				
				out.println("function assign_div(){");
				
				out.println("var due_amount=document.Form1.DUE_AMOUNT.value;");
				out.println("var due_rent=document.Form1.DUE_RENTALS.value;");
				out.println("var odi_amount=document.Form1.ODI.value;");
				out.println("var sale_price=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");//stk
				out.println("var agreemnt_no=document.Form1.LEASE_NO.value;");
				out.println("var client=document.Form1.CLIENT_CODE.value;");
				out.println("var tot_chrg=format_noobject(parseFloat(unformat_noobject(due_amount))+parseFloat(unformat_noobject(due_rent))+parseFloat(unformat_noobject(odi_amount))+parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				
				
				out.println("	M4.innerHTML='<input type=button name=cash_f  value=\"Letter\" class=mainbut onclick=Generate_Letter(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\")>';");
				out.println("	M4.innerHTML+='&nbsp;&nbsp;<input type=\"button\" name=\"Termination_Sheet\" Style=\"width:180px;\" value=\"Print Termination Sheet\" class=\"mainbut\" onclick=\"process_print();\">';");//Added By Kanishka Dilshan On 06-01-2015
				
				out.println("   if(document.Form1.TERM_TYPE.value ==\"ERL_TER\"){ ");//Created by kanishka dilshan on 19-02-2014
				out.println(" 		var vehicle_no		= document.Form1.VEHICLE_NO.value;");
				//out.println("       alert(vehicle_no); ");
				//out.println("		M4.innerHTML+='&nbsp;&nbsp;<input type=button name=early_ter_letter Style=\"width:150px;\" value=\"Early Termination Letter\" class=mainbut onclick=Generate_Erl_Letter(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\") >';");
				out.println("		M4.innerHTML+='&nbsp;&nbsp;<input type=button name=early_ter_letter Style=\"width:150px;\" value=\"Early Termination Letter\" class=mainbut onclick=Generate_Erl_Letter(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\",\"'+vehicle_no+'\");>';");
				//out.println("		M4.innerHTML+='&nbsp;&nbsp;<input type=\"button\" name=\"early_ter_quatation\" Style=\"width:180px;\" value=\"Early Termination Quatation\" class=\"mainbut\" onclick=Generate_Erl_Quatation(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\",\"'+vehicle_no+'\");>';");
				out.println("		M4.innerHTML+='&nbsp;&nbsp;';");
				
				out.println("   }");
				out.println(" 	rent.innerHTML=document.Form1.h_rent.value;"); 
				out.println("	term.innerHTML=document.Form1.h_term.value;"); 
				out.println(" 	rpv.innerHTML =document.Form1.h_rpv.value;"); 
				out.println(" 	ina.innerHTML =document.Form1.h_in.value;"); 
				out.println(" 	inr.innerHTML =document.Form1.h_inr.value;"); 
				out.println("	tpv.innerHTML =document.Form1.h_tpv.value;");
				out.println("	gtv.innerHTML =document.Form1.h_gtv.value;");
				
				out.println(" 	H1.innerHTML='';"); 
				out.println("	H2.innerHTML='Net Amount';"); 
				out.println(" 	H3.innerHTML='VAT Amount';"); 
				out.println("	H4.innerHTML='Total Amount';");
				
				out.println(" 	S11.innerHTML='Due Amount';"); 
				out.println("	S12.innerHTML=document.Form1.DUE_NET.value;"); 
				out.println(" 	S13.innerHTML=document.Form1.DUE_VAT.value;"); 
				out.println("	S14.innerHTML=document.Form1.DUE_AMOUNT.value;");
				
				out.println(" 	S21.innerHTML='Rentals Due up to Termination Date';"); 
				out.println("	S22.innerHTML=document.Form1.DUE_RENTALS_NET.value;"); 
				out.println(" 	S23.innerHTML=document.Form1.DUE_RENTALS_VAT.value;"); 
				out.println("	S24.innerHTML=document.Form1.DUE_RENTALS.value;");
				//out.println(" alert(document.Form1.h_term.value+'--'+document.Form1.rpv.value);");  
				out.println(" 	S31.innerHTML='Termination Calculation Amount';"); 
				//out.println("	S32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)));"); 
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)));");
				out.println(" 	S32.innerHTML=format_noobject(Math.round(num*100)/100);");
				out.println(" 	S33.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_vat.value)));"); 
				out.println("	S34.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_gtv.value)));");
				//Modified by Dineth on 2008-10-24
				//out.println(" S41.innerHTML='Residual Amount';");
				out.println(" 	S41.innerHTML='Sales Price';");
				out.println("	S42.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
				out.println(" 	S43.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				out.println("	S44.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				
				out.println(" 	S51.innerHTML='Termination Charges';"); 
				out.println("	S52.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value)));"); 
				out.println(" 	S53.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value)));"); 
				out.println("	S54.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_S.value)));");
				
				out.println(" 	S61.innerHTML='ODI';"); 
				out.println("	S62.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value)));"); 
				out.println(" 	S63.innerHTML=format_noobject(parseFloat(unformat_noobject('0.00')));"); 
				out.println("	S64.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value)));");
				
				out.println(" 	T1.innerHTML='Total Amount';"); 
				//out.println("	T2.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));");
				out.println(" 	T2.innerHTML=format_noobject(Math.round(num*100)/100);");
				//out.println(" 	T3.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));");
				out.println(" 	T3.innerHTML=format_noobject(Math.round(num*100)/100);");
				//out.println("	T4.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				out.println(" 	T4.innerHTML=format_noobject(Math.round(num*100)/100) + \"<input type=hidden name=hid_total_payable value=\"+num+\">\";");//Hidden Variable Added By KD On 27-01-2015
				
				//Added by Dineth on 2008-10-01
				out.println(" 	S65.innerHTML='Unallocated Receipt';"); 
				out.println("	S66.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';"); 
				out.println(" 	S67.innerHTML='('+format_noobject(parseFloat(unformat_noobject('0.00')))+')';"); 
				out.println("	S68.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';");
				
				//out.println(" alert('ODI_NET'+document.Form1.ODI_NET.value)");
				//out.println(" alert('TERM_AMOUNT'+document.Form1.TERM_AMOUNT.value)");
				//out.println(" alert('DUE_NET'+document.Form1.DUE_NET.value)");
				//out.println(" alert('DUE_RENTALS_NET'+document.Form1.DUE_RENTALS_NET.value)");
				//out.println(" alert('h_term'+document.Form1.h_term.value)");
				//out.println(" alert('hid_sn_val'+document.Form1.hid_sn_val.value)");
				//out.println(" alert('Unallo_Rec'+document.Form1.Unallo_Rec.value)");
				//out.println(" alert('Unallo_Rec'+document.Form1.h_rpv.value)");
				
				
				
				out.println(" 	T30.innerHTML='Net Total Amount';");
				out.println(" if(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))+ parseFloat(unformat_noobject(document.Form1.h_rpv.value)) -parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))<0){");
				//out.println("	T31.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';"); 
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T31.innerHTML='('+format_noobject(Math.round(num*100)/100)+')';");
				//out.println(" 	T32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));");
				out.println(" 	T32.innerHTML='('+format_noobject(Math.round(num*100)/100)+')';");
				//out.println("	T33.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T33.innerHTML='('+format_noobject(Math.round(num*100)/100)+')';");
				out.println(" } ");
				out.println(" else{ ");
				//out.println("	T31.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) +parseFloat(unformat_noobject(document.Form1.h_rpv.value))+ parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));"); 
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) +parseFloat(unformat_noobject(document.Form1.h_rpv.value))+ parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T31.innerHTML=format_noobject(Math.round(num*100)/100);");
				//out.println(" 	T32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));");
				out.println(" 	T32.innerHTML=format_noobject(Math.round(num*100)/100);");
				//out.println("	T33.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T33.innerHTML=format_noobject(Math.round(num*100)/100);");
				out.println(" } ");
				//out.println("	REBATE.innerHTML='Rebate %';");
				//out.println("	REBATE_AMT.innerHTML=format_noobject(100-(((parseFloat(unformat_noobject(document.Form1.h_term.value))-parseFloat(unformat_noobject(document.Form1.CAP_OUT.value)))/parseFloat(unformat_noobject(document.Form1.hid_int_amount.value)))*100));");
				
				//End by Dineth on 2008-10-01
				out.println(" 	R1.innerHTML='No of Future Rentals';"); 
				out.println("	R2.innerHTML=document.Form1.F_R.value;"); 
				out.println(" 	R3.innerHTML='No of Rentals Paid';"); 
				out.println("	R4.innerHTML=document.Form1.R_P.value;");
				
				out.println(" 	R5.innerHTML='No of Rentals Arrears';"); 
				out.println("	R6.innerHTML=document.Form1.R_A.value;");
				
				out.println("	R13.innerHTML='Total';");
				out.println("	R14.innerHTML=document.Form1.R_T.value;");
				
				out.println("	R7.innerHTML='Termination Gain / Loss';");
				out.println("	R8.innerHTML=document.Form1.h_term.value;");
				
				
				
				//out.println("	document.Form1.CLOSURE_IRR.value=document.Form1.C_IRR.value;"); 
				
				
				//out.println(" alert(unformat_noobject(document.Form1.h_term.value)+'<'+unformat_noobject(document.Form1.h_rpv.value));");
				
				//out.println(" if(parseFloat(unformat_noobject(document.Form1.h_term.value))<parseFloat(unformat_noobject(document.Form1.h_rpv.value))){");
				//out.println("   document.Form1.b_submit.disabled   = true;");
				//out.println("   document.Form1.b_submit_1.disabled = true;");
				//out.println("   alert('Please enter correct termination rate and continue.');");
				//out.println(" }else{");
				//out.println("   document.Form1.b_submit.disabled   = false;");
				//out.println("   document.Form1.b_submit_1.disabled = false;");
				//out.println(" }");
				
				//out.println("	check_leaserate();");
				out.println("	get_CLOSURE_IRR();");
				out.println("   document.Form1.cal.disabled = false;"); // added by udara 29-01-2019
				
				out.println("}");		
				
				
				out.println("function inv_help(num){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
				out.println("}");		
				
				
				out.println("function cal_amount(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'3');");
				
				out.println("}");	
				
				out.println("function cal_amount_del(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'6');");
				
				out.println("}");	
				
				out.println("function get_term_vehicles() {");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=get_Vehicles&Lease_no=\"+document.Form1.LEASE_NO.value;");
				//comment above and add below by Kanishka On 29-07-2014
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=get_Vehicles&Lease_no=\"+document.Form1.LEASE_NO.value+\"&Ter_date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'7');");
				out.println("}");
				
				
				
				
				/*out.println("function get_Receipt(m_stat,opt) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=get_Receipt&client=\"+document.Form1.CLIENT_CODE.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2');");
				
        out.println("}");	
				
				out.println("function get_Receipt_del(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=get_Receipt_del&rec_no=\"+val+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'5');");
				
        out.println("}");	
				
				out.println("function check_receipt(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_XMLFile?chksql=get_rec_no&rec_no=\"+document.Form1.RECEPT_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Rec');");
				
        out.println("}");
				*/
				
				
				/*out.println("function get_CLOSURE_IRR(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_IRR&finance_no=\"+document.Form1.LEASE_NO.value+\"&tdate=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value;");
				out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','IRR');");
				out.println("}");
				*/
				
				// added by udara 06-01-2021
				out.println("function getMoratoriumValues() {");
				out.println("   m_url=\"" + m_servlet_client_url + ":" + m_client_t3_port + "/" + m_client_name + "AF_CR_TerminationSchedule_new_display?chksql=getMoratoriumValues&Lease_no=\"+document.Form1.LEASE_NO.value;");
				out.println("   makeRequest(m_url,'77');");
				out.println("}");
				// end by udara 06-01-2021
				
				
				out.println("function get_CLOSURE_IRR(val) {");
				out.println("   m_amt=parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value));");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"?chksql=get_IRR&finance_no=\"+document.Form1.LEASE_NO.value+\"&term_amt=\"+m_amt+\"&odi_amt=\"+unformat_noobject(document.Form1.ODI_NET.value)+\"&tdate=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&unallo_amt=\"+unformat_noobject(document.Form1.Unallo_Rec.value);");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','IRR');");
				out.println("}");
				
				out.println("function check_due_inv(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_due_invoice_sum&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','DUE');");
				out.println("}");
				
				out.println("function get_due_rent_sum(val) {");
				out.println(" if(document.Form1.TER_DAY.value!=\"\" && document.Form1.TER_MONTH.value!=\"\" && document.Form1.TER_YEAR.value!=\"\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_due_rent_sum&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&tdate=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','DUER');");
				out.println(" }else{");
				out.println("   alert('Please enter termination date.');");
				out.println(" }");
				out.println("}");
				
				out.println("function build_veh_no(val) {");
				out.println("  m_v_no=\"\";");
				out.println("  m_c_no=\"\";");
				out.println("  m_i_no=\"\";");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=document.Form1.elements['VEHICLE_NO_'+i].value;");    
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				//out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				out.println("}");
				
				
				out.println("function get_term_details(val) {");
				out.println("  m_v_no=\"\";");
				out.println("  m_c_no=\"\";");
				out.println("  m_i_no=\"\";");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");    
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				//out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				//out.println("}");    
				out.println(" if(document.Form1.INVOICE_NO.value!=\"\" && document.Form1.LEASE_NO.value!=\"\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_term_details&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','CAP');");
				out.println(" }else{");
				//out.println("   alert('Please enter termination date.')");
				out.println(" }");
				out.println("}");
				
				
				
				out.println("function check_lease_rate() {");
				out.println("  if(document.Form1.LEASE_NO.value!=''){");
				out.println("   build_veh_no();");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_lease_rate&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','LERATE');");
				out.println("  }"); 
				out.println("}");
				
				out.println("function check_ODI_ADJ(val) {");
				out.println("  if(document.Form1.ODI.value!='' && document.Form1.ODI_ADJ.value!=''){");
				out.println("  if(parseFloat(unformat_noobject(document.Form1.ODI.value))>=parseFloat(unformat_noobject(document.Form1.ODI_ADJ.value))){");
				out.println("   m_url=\"" +m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+ "AF_CR_XMLFile_termination?chksql=get_ODI_NET&ODI=\"+parseFloat(unformat_noobject(document.Form1.ODI.value))+\"&ODI_ADJ=\"+parseFloat(unformat_noobject(document.Form1.ODI_ADJ.value));");
				out.println("   makeRequest(m_url,'4','ODI_NET');");
				out.println("  }else{");
				out.println("   alert('Please check the ODI Adjustment amount.');");
				out.println("   document.Form1.ODI_ADJ.focus(); ");
				out.println("  }");
				out.println("  }");
				out.println("}");
				
				//Added By Kanishka Dilshan On 31-12-2014
				out.println("function cal_odi_per(type){ ");
				out.println(" obj_ODI = document.Form1.ODI; 			");
				out.println(" obj_PER = document.Form1.ODI_ADJ_PER; 	");
				out.println(" obj_ADJ = document.Form1.ODI_ADJ; 		");
				
				out.println(" if(obj_PER.value==\"\"){ obj_PER.value =\"0.00\"; }");
				out.println(" if(parseFloat(unformat_noobject(obj_PER.value)) > 100 || parseFloat(unformat_noobject(obj_PER.value)) < 0 ){ alert('ODI Adjustment % should be between 0 to 100.'); obj_PER.value =\"0.00\"; }");

				out.println(" 	if(type==\"1\"){ ");//Meaning calculate adjustment amount
				out.println(" 		obj_ADJ.value = format_noobject((Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100) * ((Math.round(parseFloat(unformat_noobject(obj_PER.value))*100)/100)      /100) );");
				out.println("       check_ODI_ADJ(); ");
				out.println(" 	}else{");//Meaning calculate adjustment Percentage
				out.println(" 	  	if(parseFloat(unformat_noobject(obj_ODI.value)) > 0 ){");
				out.println(" 			obj_PER.value = format_noobject(((Math.round(parseFloat(unformat_noobject(obj_ADJ.value))*100)/100) / (Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100)) *100);"); // commented by udara 17-09-2018 to block the changing the value of the ODI adj percentage
				out.println("           check_odi_Rate(); "); // added by udara 30-10-2018
				out.println(" 		}");
				out.println(" 	}");
				out.println("}");
				
				out.println("function check_leaserate() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_lease_rate&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','LEASERATE');");
				out.println("}");
				
				out.println("function check_Rate() {");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_term_rate&ter_rate=\"+document.Form1.TER_RATE.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_term_rate_sn&ter_rate=\"+document.Form1.TER_RATE.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				//out.println("   makeRequest(m_url,'4','TERATE');");
				out.println("   makeRequest(m_url,'8','TERATESN');"); // added by udara 24-12-2018
				
				out.println("}");
				
				// added by udara 23-10-2018
				out.println("function check_odi_Rate() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_term_odi_rate_sn&ter_rate=\"+document.Form1.ODI_ADJ_PER.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'9','TERATEODISN');"); 	
				out.println("}");
				// end by udara 23-10-2018
				
				// added by udara 01-11-2018
				out.println("function check_odi_rate_new() {");
				out.println("   if( document.Form1.ODI_ADJ_PER.value != '' && document.Form1.ODI_ADJ_PER.value != '0.00'){ "); 
				out.println("      var m_percentage = parseFloat(unformat_noobject(document.Form1.ODI_ADJ_PER.value)); ");
				out.println("      var m_ODI_FROM = '"+m_ODI_FROM+"'; ");
				out.println("      var m_ODI_TO = '"+m_ODI_TO+"'; ");
				out.println("      if(m_percentage>=m_ODI_FROM && m_percentage<=m_ODI_TO){");
				//out.println("         alert('Valid ODI percentage'); ");
				out.println("         obj_ODI = document.Form1.ODI; 			");
				out.println("         obj_PER = document.Form1.ODI_ADJ_PER; 	");
				out.println("         obj_ADJ = document.Form1.ODI_ADJ; 		");
				//out.println(" 		  obj_ADJ.value = format_noobject((Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100) * ((Math.round(parseFloat(unformat_noobject(obj_PER.value))*100)/100)      /100) );");
				out.println("         var m_obj_ADJ = (Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100) * ((Math.round(parseFloat(unformat_noobject(obj_PER.value))*100)/100)      /100) ; ");
				out.println(" 		  obj_ADJ.value = m_obj_ADJ.toFixed(2); ");
				out.println("         check_ODI_ADJ(); ");
				out.println("      }");
				out.println("      else{");
				out.println("         alert('Invalid ODI percentage'); ");
				out.println("         obj_ADJ.value = '0.00'; ");
				out.println("         obj_PER.value = '0.00'; ");
				//out.println("         			document.Form1.ODI.value='0.00'; "); // added by udara 01-02-2019 // commented by udara 13-02-2019
				//out.println("         			document.Form1.ODI_NET.value='0.00'; "); // added by udara 01-02-2019 // commented by udara 13-02-2019
				out.println("      }");
				out.println("   }");
				out.println("}");
				
				out.println("function check_odi_adj_amount_new() {");
				
				out.println("    obj_ODI = document.Form1.ODI; 			");
				out.println("    obj_PER = document.Form1.ODI_ADJ_PER; 	");
				out.println("    obj_ADJ = document.Form1.ODI_ADJ; 		");
				
				out.println(" 	  	if(parseFloat(unformat_noobject(obj_ODI.value)) > 0 ){");
				
				//out.println(" 			var m_percentage_2 = (((Math.round(parseFloat(unformat_noobject(obj_ADJ.value))*100)/100) / (Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100)) *100);"); 
				//out.println("           alert(m_percentage_2.toFixed(2)); ");
				
				//out.println(" 			var m_percentage = (((Math.round(parseFloat(unformat_noobject(obj_ADJ.value))*100)/100) / (Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100)) *100);"); 
				out.println(" 			var m_percentage = (((Math.round(parseFloat(unformat_noobject(obj_ADJ.value))*100)/100) / (Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100)) *100);"); 
				out.println("           m_percentage = m_percentage.toFixed(2); ");
				
				//out.println("           var m_ODI_FROM = '"+m_ODI_FROM+"'; ");
				//out.println("           var m_ODI_TO = '"+m_ODI_TO+"'; ");
				
				out.println("           var m_ODI_FROM = parseFloat('"+m_ODI_FROM+"'); ");
				out.println("           var m_ODI_TO = parseFloat('"+m_ODI_TO+"'); ");
				
				out.println("       		if(m_percentage>=m_ODI_FROM && m_percentage<=m_ODI_TO){");
				//out.println(" 			         obj_PER.value = format_noobject(((Math.round(parseFloat(unformat_noobject(obj_ADJ.value))*100)/100) / (Math.round(parseFloat(unformat_noobject(obj_ODI.value))*100)/100)) *100);"); 
				out.println(" 				     obj_PER.value = format_noobject(m_percentage); ");
				out.println(" 				}");
				out.println("               else{");
				out.println("         			alert('Invalid ODI percentage : ' + m_percentage); ");
				out.println("         			obj_ADJ.value = '0.00'; ");
				out.println("         			obj_PER.value = '0.00'; ");
				//out.println("         			document.Form1.ODI.value='0.00'; "); // added by udara 01-02-2019 // commented by udara 13-02-2019
				//out.println("         			document.Form1.ODI_NET.value='0.00'; "); // added by udara 01-02-2019 // commented by udara 13-02-2019
				out.println("         			document.Form1.ODI_NET.value=prev_ODI_NET_AMOUNT; "); // added by udara 14-02-2019
				out.println("      			}");
				
				out.println(" 		}");
				
				out.println("}");
				// end  by udara 01-11-2018
				
				
				out.println("function check_term_count() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_term_count&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TCOUNT');");
				out.println("}");
				
				out.println("function check_term_char() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_term_charge&term_type=\"+document.Form1.TERMINATION_TYPE.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TCHAR');");
				out.println("}");
				
				out.println("function check_lease(val) {");
				out.println("  if(document.Form1.LEASE_NO.value!='' && (document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value!='--')){");
				out.println("   document.Form1.TER_V_DAY.value   = document.Form1.TER_DAY.value;");
				out.println("   document.Form1.TER_V_MONTH.value = document.Form1.TER_MONTH.value;");
				out.println("   document.Form1.TER_V_YEAR.value  = document.Form1.TER_YEAR.value;");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_Finance_no&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&m_date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value;"); // commented by udara 01-02-2019
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_Finance_no_new&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&m_date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value;"); // added by udara 01-02-2019
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Lea');");
				out.println("  }");
				out.println("}");
				
				out.println("function check_vehicle(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_veh_no&veh_no=\"+document.Form1.VEHICLE_NO.value+\"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Veh');");
				out.println("}");
				
				out.println("function check_term(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_term_no&term_no=\"+document.Form1.TERMINATION_NO.value+\"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TermNo');");
				out.println("}");
				
				
				out.println("function check_client(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_client_code&client_code=\"+document.Form1.CLIENT_CODE.value+\"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Cli');");
				
				out.println("}");
				
				out.println("function cal_ter(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile_termination?chksql=get_ter&vat=15&ter_char=\"+document.Form1.TERM_AMOUNT.value+\"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TER_VAL');");
				
				out.println("}");
				
				out.println("function load_all_foll(m_stat,opt) {");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup&fno="+m_Followu_no+"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,opt);");
				out.println("}");
				
				
				// added by udara 01-02-2019
				out.println(" var m_sub_odi = '';  ");
				out.println(" var m_sub_rebate = '';  ");
				
				out.println("function check_odi_change(){"); 
				out.println(" m_sub_odi = '1'; ");
				out.println("}");
				
				out.println("function check_rebate_change(){"); 
				out.println(" m_sub_rebate = '1'; ");
				out.println("}");
				// end by udara 01-02-2019
				
				out.println("function validate_data(){"); 
				out.println("m_sub=0;"); 
				
				out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
				out.println("if(document.Form1.LEASE_NO.value==\"\"){  "); 
				out.println("FNO.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}"); 
				out.println("if(document.Form1.CLIENT_CODE.value==\"\"){  "); 
				out.println("CLC.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}"); 
				out.println("if(document.Form1.TER_RATE.value==\"\"){  "); 
				out.println("TMR.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}");
				out.println("if(document.Form1.TER_V_YEAR.value==\"\" || document.Form1.TER_V_MONTH.value==\"\" || document.Form1.TER_V_DAY.value==\"\"){  "); 
				out.println("TMV.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}");
				out.println("if(document.Form1.TER_DAY.value==\"\" || document.Form1.TER_MONTH.value==\"\" || document.Form1.TER_YEAR.value==\"\"){  "); 
				out.println("TMD.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}");
				
				
				out.println("}else {"); 
				
				out.println("if(document.Form1.TERMINATION_NO.value==\"\"){  "); 
				out.println("TMN.style.color='red';");
				out.println("m_sub = 1;"); 
				out.println("}"); 
				out.println("}");
				
				// added by udara 01-02-2019
				out.println("if(m_sub_odi=='1'){");
				out.println("    alert('ODI values are changed and please re-calculate'); "); 
				out.println("    m_sub = 1;"); 
				out.println("}"); 
				
				out.println("if(m_sub_rebate=='1'){");
				out.println("    alert('Rebate values are changed and please re-calculate'); "); 
				out.println("    m_sub = 1;"); 
				out.println("}"); 
				// end by udara 01-02-2019
				
				
				
				/*out.println(" if(document.Form1.hid_count.value==\"0\"){"); //comment by nuwan de silva on 08-10-2009 
				out.println("		alert('Please calculate termination value and continue!') "); 
				out.println("   m_sub = 1;;"); 
				out.println(" }");
				*/
				
				out.println("if(m_sub=='1'){");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				out.println("function befor_submit(){ "); 
				out.println(" if('"+can_save+"' == 'NO') { alert('Sorry.. Only the Termination Calculation Possible by using this Screen.');return false; }");//Added By Kanishka Dilshan On 12-01-2015
				// commented by udara 01-02-2019
				/*
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				*/
				out.println("		if(validate_data()){");				
				out.println(" if(confirm(\"Are you sure, Termination Type is correct?\")){  "); //Start -- Added By Sandun on 26-11-2008
				out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
				out.println("m_v_no='';");
				out.println("m_s_va='';");
				out.println("m_c_no='';");
				out.println("m_i_no='';");
				out.println("m_ss_va='0';");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				out.println("  m_s_va=m_s_va+document.Form1.elements['sele_val_'+i].value+\"@\";");
				out.println("  m_ss_va=parseFloat(m_ss_va)+parseFloat(unformat_noobject(document.Form1.elements['sele_val_'+i].value));");    
				//out.println("  alert('m_s_va='+m_s_va);"); 
				//out.println("  alert('m_v_no='+m_v_no);"); 
				out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				//out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				//out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				////out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				////out.println(" document.Form1.SALE_VALUE.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				//out.println("}");    
				
				// added by udara 01-02-2019
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				// end by udara 01-02-2019
				
				out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Save_new';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); //End
				out.println("		}"); 
				out.println("} "); 
				
				out.println("function befor_cal(){ "); 
				//out.println("		if(confirm(\"Are You Sure?\")){ "); 
				out.println("if(document.Form1.hid_option.value=='NEW'){");
				out.println("	if( document.Form1.LEASE_NO.value==\"\"){");
				out.println("   FNO.style.color='red';");
				out.println("		alert('Please enter finance no and continue!') "); 
				out.println("	}else if(document.Form1.TER_RATE.value==\"\" ){");
				out.println("   TMR.style.color='red';");
				out.println("		alert('Please enter discount rate and continue!') "); 
				out.println("	}else if(document.Form1.TER_DAY.value==\"\" || document.Form1.TER_MONTH.value==\"\" || document.Form1.TER_YEAR.value==\"\"){");
				out.println("   TMD.style.color='red';");
				out.println("		alert('Please enter termination date and continue!') "); 
				out.println("	}else{"); 
				out.println(" m_v_count=0;");    
				out.println(" m_v_no=\"\";");    
				out.println(" m_s_va=\"\";");    
				out.println(" m_c_no=\"\";");    
				out.println(" m_i_no=\"\";");    
				out.println(" m_ss_va=\"0\";");    
				//out.println("alert(document.Form1.hid_vcount.value);");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				out.println("  m_s_va=m_s_va+document.Form1.elements['sele_val_'+i].value;");
				//out.println("  alert('m_s_va='+m_s_va);"); 
				//out.println("  alert('m_ss_va='+document.Form1.elements['sele_val_'+i].value);"); 
				out.println("  m_ss_va=parseFloat(m_ss_va)+parseFloat(unformat_noobject(document.Form1.elements['sele_val_'+i].value));");    
				out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				//out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				//out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				////out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				////out.println(" document.Form1.SALE_VALUE.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				//out.println("}");    
				//out.println("  alert(m_v_count+'---'+m_v_no);"); 
				//out.println("  alert('document.Form1.SUM_SALE_VALUE.value='+document.Form1.SUM_SALE_VALUE.value);"); 
				
				out.println("  if(parseFloat(m_v_count)>0){"); 
				out.println("      document.Form1.cal.disabled=true;"); // added by udara 29-01-2019
				out.println("      m_sub_rebate = ''; "); // added by udara 13-03-2019
				out.println("      m_sub_odi = ''; "); // added by udara 13-03-2019
				//out.println("alert(document.Form1.SALE_VALUE.value+'-'+document.Form1.SUM_SALE_VALUE.value);");
				out.println("    m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=get_Termi_Char&Term_val=\"+document.Form1.TERM_AMOUNT.value+\"&Vehicle_no=\"+document.Form1.VEHICLE_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&lease_rate=\"+document.Form1.LEASE_RATE.value+\"&App_Date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&sum_sal=\"+document.Form1.SUM_SALE_VALUE.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value;");
				//out.println("   window.open(m_url);");
				out.println("    makeRequest(m_url,'2');");
				out.println("	 }else{");
				out.println("	   alert('Sorry there is no selected vehicle to terminate.');"); 
				out.println("	 }"); 
				out.println("	}"); 
				out.println("}else{"); 
				out.println("      document.Form1.cal.disabled=true;"); // added by udara 29-01-2019
				out.println("      m_sub_rebate = ''; "); // added by udara 13-03-2019
				out.println("      m_sub_odi = ''; "); // added by udara 13-03-2019
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=get_Termi_Det&Term_val=\"+document.Form1.TERM_AMOUNT.value+\"&Termination_no=\"+document.Form1.TERMINATION_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&App_Date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value;");
				// out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2');");
				out.println("}"); 
				out.println("} "); 
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=main_page&calculate_only=Y'");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println("   close_window(); ");
				//out.println(" if(confirm(\"Are you sure?\")){  ");
				//out.println("  top.frames[1].location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println(" }  ");
				out.println("}");
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");");
				
				rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') "+	
					" FROM   DUAL");
				if(rs.next()){
					out.println("  document.Form1.TER_DAY.value="+rs.getString(1)+";"); 
					out.println("  document.Form1.TER_MONTH.value="+rs.getString(2)+";"); 
					out.println("  document.Form1.TER_YEAR.value="+rs.getString(3)+";"); 
					out.println("  document.Form1.TER_V_DAY.value="+rs.getString(1)+";"); 
					out.println("  document.Form1.TER_V_MONTH.value="+rs.getString(2)+";"); 
					out.println("  document.Form1.TER_V_YEAR.value="+rs.getString(3)+";"); 
				}
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are You Sure?\")){ "); 
				//if(m_Followu_no==null){
				//out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup';"); 
				//}else{
				out.println("		window.close();");
				//}
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_OFSCL_AF_CO_Followup\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"Finance - Termination - Calculation - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"Finance - Termination - Calculation - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("    document.Form1.hid_option.value    =m_val;"); 
				out.println("if(m_val==\"NEW\"){"); 
				//out.println("new_window();");
				//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
				out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
				out.println("document.Form1.cli_help.disabled=false;"); 
				out.println("document.Form1.lea_help.disabled=false;"); 
				//out.println("document.Form1.veh_help.disabled=false;"); 
				out.println("document.Form1.rec_help.disabled=true;"); 
				out.println("document.Form1.TERMINATION_NO.disabled=true;}"); 
				
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val==\"DELETE\"){"); 
				out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
				out.println("document.Form1.cli_help.disabled=true;"); 
				out.println("document.Form1.lea_help.disabled=true;"); 
				//out.println("document.Form1.veh_help.disabled=true;"); 
				out.println("document.Form1.rec_help.disabled=false;"); 
				out.println("document.Form1.TERMINATION_NO.disabled=false;"); 
				
				out.println("}"); 
				out.println("else{");
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
				out.println("document.Form1.OPTION_DESC.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"DELETE\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");  
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
				out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
				out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
				out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
				*/
				//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CR_Ter_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		receipt_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		vehicle_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		lease_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		term_assign(oBj);"); 
				out.println("		}");
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	}	"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				//client Help
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.LEASE_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				//out.println(" get_Receipt();");
				out.println("}");
				
				//Lease Help
				out.println("function lease_help(){");
				
				// added by udara 22-05-2019
				out.println(" var m_string = document.Form1.LEASE_NO.value; ");
				out.println(" if(m_string!=''){ ");
				out.println("   m_string = m_string.replace(/^\\s+|\\s+$/g, ''); ");
				out.println(" } ");
				out.println(" document.Form1.LEASE_NO.value = m_string; ");
				out.println("Crit=m_string+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				// end by udara 22-05-2019
				
				//out.println("Crit=document.Form1.LEASE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";"); // commented by udara 22-05-2019
				out.println("HelpBox('1','10','0',Crit,'LeaseSql','3');");
				out.println("}");	
				
				out.println("function lease_assign(oBj){");
				out.println(" document.Form1.LEASE_NO.value =oBj.valout[2]");
				out.println(" document.Form1.APPLICATION_NO.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[4]");//Added By Sandun on 26-11-2008
				//out.println("alert(document.Form1.LEASE_NO.value);");				
				out.println(" check_lease(document.Form1.LEASE_NO.value);");
				out.println("}");
				//Vehicle Help
				out.println("function vehicle_help(){");
				out.println("Crit=document.Form1.VEHICLE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.LEASE_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'VehicleSql','4');");
				out.println("}");	
				
				
				
				out.println("function vehicle_assign(oBj){");
				out.println(" document.Form1.VEHICLE_NO.value =oBj.valout[2]");
				out.println(" check_vehicle(document.Form1.VEHICLE_NO.value);");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
				
				//Vehicle Help
				out.println("function term_help(){");
				out.println("Crit=document.Form1.TERMINATION_NO.value+\"@\";");
				out.println("HelpBox('1','10','5',Crit,'TerminationNoSql','5');");
				out.println("}");	
				
				out.println("function term_assign(oBj){");
				out.println(" document.Form1.TERMINATION_NO.value =oBj.valout[2]");
				out.println(" check_term(document.Form1.TERMINATION_NO.value);");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
				
				/*
						out.println("function help_update_value_assign_99() {"); 
						out.println("    document.Form1.TXT_FOLLOW_UP_NO.value=oBj.valout[2];"); 
						out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[3];"); 
						out.println("    document.Form1.TXT_ACTION_TOBE_TAKEN.value=oBj.valout[4];"); 
						out.println("    document.Form1.TXT_EFF_VAL_DATE.value=oBj.valout[5];"); 
						out.println("    document.Form1.TXT_ACTION_TOOK.value=oBj.valout[6];"); 
						out.println("    document.Form1.TXT_ACTION_TOOK_DATE.value=oBj.valout[7];"); 
						out.println("    document.Form1.TXT_ACTION_SET_FOR.value=oBj.valout[8];"); 
						out.println("    document.Form1.TXT_SCREEN_NAME.value=oBj.valout[9];"); 
						out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[10];"); 
						out.println("    document.Form1.TXT_ENT_REMARKS.value=oBj.valout[11];"); 
						out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[12];"); 
						out.println("    document.Form1.TXT_ACTION_ENT_DATE.value=oBj.valout[13];"); 
						
						out.println("}"); 
					*/	
				out.println("function load_edit_window(i,foll_no,type) {");
				out.println("   ");
				
				out.println("}"); 
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("  if(document.Form1.hid_cal_date.value=='1'){");
				out.println("     document.Form1.TXT_EFF_VAL_DATE.value=val;");
				out.println("  }else if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("     document.Form1.TXT_ACTION_TOOK_DATE.value=val;");
				out.println("  }else if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("     document.Form1.TXT_next_day.value=val;");
				out.println("  }");				
				out.println("}");				
				
				out.println("function load_data(num) {");
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_history(num) {");
				out.println("	if(num!=''){ ");
				out.println("	  popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_History&deal_no=\"+num+\"\", \"oBj\",\"left=100,top=200,width=650,height=400\");"); 
				out.println("	}else{");
				out.println("	  alert('Please enter Followup Number and continue!');");
				out.println("	}");
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function ch_status(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
				//ADDED BY SH ON 21/05/2007	
				out.println("m_vat='';");
				out.println("m_vcount='0';");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked && i!=num){");
				out.println("  m_vat=parseFloat(unformat_noobject(document.Form1.elements['VAT_'+i].value));");    
				out.println("  m_vcount=m_vcount+1;");    
				out.println(" }");    
				out.println("}");    
				out.println(" if(m_vcount=='0'){");
				out.println("   m_vat=parseFloat(unformat_noobject(document.Form1.elements['VAT_'+num].value));");    
				out.println(" }");    
				
				//END	
				out.println("if(document.Form1.elements['ch_v_'+num].checked && (m_vat==parseFloat(unformat_noobject(document.Form1.elements['VAT_'+num].value)))){");
				out.println(" document.Form1.elements['ch_v_'+num].value=\"YES\";");
				out.println(" document.Form1.elements['ch_v_'+num].checked=true;");
				out.println("}else{");
				out.println(" if(document.Form1.elements['ch_v_'+num].checked && (m_vat!=parseFloat(unformat_noobject(document.Form1.elements['VAT_'+num].value)))){");
				out.println("  alert('Please check the VAT rate and continue.');");
				out.println(" }");
				out.println(" document.Form1.elements['ch_v_'+num].value=\"NO\";");
				out.println(" document.Form1.elements['ch_v_'+num].checked=false;");
				out.println("}");
				out.println(" get_term_details();");
				out.println("}");
				
				out.println("function check_amount(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
				out.println("if(Number(document.Form1.elements['Text_sett_amount'+num].value)>Number(document.Form1.elements['Hid_amount'+num].value)){");
				out.println(" alert('Amount cannot be greater than Net Amount');");
				out.println(" document.Form1.elements['Text_sett_amount'+num].value = document.Form1.elements['Hid_amount'+num].value;");
				out.println("}");
				out.println("}");
				
				
				out.println("function check_status_del(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
				out.println("if(document.Form1.elements['Text_standard_0_'+num].checked){");
				out.println(" document.Form1.elements['Text_standard_0_'+num].value=\"YES\";");
				out.println(" cal_amount_del('add',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
				out.println("}else{");
				out.println(" document.Form1.elements['Text_standard_0_'+num].value=\"NO\";");
				out.println(" cal_amount_del('min',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
				out.println("}");
				out.println("}");
				
				out.println("function check_Date(val1,val2,val3) {");
				//out.println("  alert(val1.value+'-'+val2.value+'-'+val3.value+'-');");
				out.println(" if(val1.value!='' && val2.value!='' && val3.value!=''){"); 
				//out.println("  alert(val1+'-'+val2+'-'+val3+'-');");
				
				out.println("  checkMonthLength(val1,val2,val3); ");
				out.println(" }");
				out.println("}");
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("     document.Form1.TER_DAY.value=val.substr(0,2);");
				out.println("     document.Form1.TER_MONTH.value=val.substr(3,2);");
				out.println("     document.Form1.TER_YEAR.value=val.substr(6,4);");
				out.println("     check_lease();");
				out.println("  }else if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("     document.Form1.TER_V_DAY.value=val.substr(0,2);");
				out.println("     document.Form1.TER_V_MONTH.value=val.substr(3,2);");
				out.println("     document.Form1.TER_V_YEAR.value=val.substr(6,4);");
				out.println("  }");				
				out.println("}");				
				
				out.println("function load_data(num) {");
				out.println(" if(num!=\"\"){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=Report&print=TRUE&applicaton_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Finance Number and continue!');");
				out.println(" }");
				out.println("}");
				
				out.println("function load_cdata(num) {");
				out.println(" if(num!=\"\"){");	
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_CLIENT_INFORMATION&client_code=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Client Code and continue!');");
				out.println(" }");
				out.println("}");
				
				out.println("function load_inv_data(num) {");
				out.println(" if(num!=\"\" ){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RENT_DETAIL_INVOICED_DRILL&application_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Finance Number and continue!');");
				out.println(" }");
				out.println("}");
				http://www.ofscl-netasset.lk:/myserver/servlet/OFSCL_AF_MISF_balance_receivable_report1?chksql=MAIN&client_name=TEST&finance_no=AP20070511-0624&client_code=0000000233&allocation_date=01-12-2007
					
					//Added by Dineth on 2008-12-03
					out.println("function load_arrears_data(num) {");
				out.println(" if(num!=\"\" ){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_ARREARS_DETAIL_DRILL&lease_no=\"+num+\"\", \"oBj1\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Finance Number and continue!');");
				out.println(" }");
				out.println("}");
				
				
				
				
				//End by Dineth on 2008-12-03
				
				
				out.println("function load_Rec_data(num) {");
				out.println(" if(num!=\"\" ){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new_display?chksql=SHOW_BALANCES_INFO&url=&client_code=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Client Code and continue!');");
				out.println(" }");
				out.println("}");	
				
				int m_cnt = 0;
				String m_fin_no = "";
				
				
				out.println("function load_remarks(val){");//Added By Sandun on 10-12-2008
				out.println("if(val==\"\"){");
				out.println("FNO.style.color='red';");
				out.println("}");	
				out.println("else{");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationApproval1?chksql=REMARKS_HISTORY&finance_no=\"+val+\" \";");  
				out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=800,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' name='Hid_scr_name' value='AF_CR_TERMINATION_CAL' > ");
				//out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_CR_TERMINATION_CAL' > ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
				out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
				out.println("<input type=hidden name=\"VEHICLE_NO\" value=\"\">");
				out.println("<input type=hidden name=\"CHASSIS_NO\" value=\"\">");
				out.println("<input type=hidden name=\"INVOICE_NO\" value=\"\">");
				out.println("<input type=hidden name=\"SALE_VALUE\" value=\"\">");
				out.println("<input type=hidden name=\"SUM_SALE_VALUE\" value=\"\">");
				out.println("<input type=hidden name=\"hid_int_amount\" value=\"\">");
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				
				
				out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>"); 
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);' disabled ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
				
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMN>Termination No</td>");
				out.println("<td><input name=\"TERMINATION_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=check_term()> ");
				out.println("<input type=button name=rec_help value=Help class=\"but_input\" onclick=\"term_help()\" disabled></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//Modified by Dineth on 2008-10-24
				out.println("<td id=fod>Termination Type</td>");
				String m_ter_type="<SELECT name=\"TERM_TYPE\" class=\"txt_input\">";
				rs2 = stmt.executeQuery("SELECT TERMINATION_TYPE, TERMINATION_DESC "+
					"FROM   "+m_schema_name+".AF_CO_MAS_TERMINATION_TYPE ");
				while(rs2.next()){
					if(rs2.getString(1).trim().equals("ERL_TER")){
						m_ter_type=m_ter_type+"<OPTION value=\""+rs2.getString(1)+"\" selected >"+rs2.getString(2)+"</OPTION>";
					}
					else{
						m_ter_type=m_ter_type+"<OPTION value=\""+rs2.getString(1)+"\">"+rs2.getString(2)+"</OPTION>";
					}
				}
				m_ter_type=m_ter_type+"</SELECT>";
				out.println("<td>"+m_ter_type+"<input type=hidden name=TERMINATION_TYPE value=''></TD>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				//End by Dineth on 2008-10-24
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMD>Termination Date *</td>");
				out.println("<td><input name=\"TER_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);check_lease(); disabled > ");
				out.println("    <input name=\"TER_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);check_lease(); disabled > ");
				out.println("    <input name=\"TER_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);check_lease(); disabled ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td id=tod>Requested By</td>");
				out.println("<td><select name=REQ_BY class=\"txt_input\"><option value=\"CLIENT\" Selected>Client</option> ");
				out.println("<option value=\"MANAGEMENT\" >Management</option><select> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=CLC>Client Code *</td>");
				out.println("<td><input name=\"CLIENT_CODE\" type=\"text\"  AF_CR_TEMP_TERMINATION_SAVE_N class=\"txt_input\" onchange=check_client()> ");
				out.println("<input type=button name=cli_help value=Help class=\"but_input\" onclick=\"client_help()\">");
				out.println("<input class='but_input' type='button' name='BUT_HELP_DET' value=\"Detail\" onClick=\"load_cdata(document.Form1.CLIENT_CODE.value)\" >"); 
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod>Client Name</td>");
				out.println("<td> <input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"200\" class=\"txt_input\" disabled style=\"width:300px;\">");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");		
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=FNO>Finance No *</td>");
				out.println("<td><input name=\"LEASE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=check_lease()><input name=\"APPLICATION_NO\" type=\"hidden\">");
				out.println("<input type=button name=lea_help value=Help class=\"but_input\" onclick=\"lease_help()\" >");
				out.println("<input class='but_input' type='button' name='BUT_HELP_DET' value=\"Detail\" onClick=\"load_data(document.Form1.APPLICATION_NO.value)\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_REMARK_DET' value=\"Remarks\" onClick=\"load_remarks(document.Form1.LEASE_NO.value)\" >"); 
				out.println("<span id='moratorium' style='color:black'></span> "); // added by udara 06-01-2021
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td >Termination Count</td>");
				out.println("<td><input name=\"TER_COUNT\"   type=\"text\" maxlength=\"2\"  class=\"txt_input\" disabled > ");
				out.println("</td>");
				//out.println("<td ></td>");
				//out.println("<td></td>");
				/*out.println("<td >Vehicle No</td>");
				out.println("<td> <input name=\"VEHICLE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=check_vehicle()> ");
				out.println("<input type=button name=veh_help value=Help class=\"but_input\" onclick=\"vehicle_help()\" >");
				out.println("</td>");*/
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TRNT>Transaction Type</td>");
				out.println("<td><input name=\"TRN_TYPE\" type=\"text\" maxlength=\"15\" class=\"txt_input\" Disable>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td ></td>");//Closure IRR
				//out.println("<td><input name=\"CLOSURE_IRR\" type=\"text\" maxlength=\"15\" class=\"txt_input\" Disable STYLE=\"{text-align:right;}>");
				//out.println("</td>");
				out.println("<td ><input name=\"CLOSURE_IRR\"  type=\"Hidden\" maxlength=\"6\"  class=\"txt_input\" disabled onchange=\"\" STYLE=\"{text-align:right;}\"></td>");
				//out.println("<td></td>");
				/*out.println("<td >Vehicle No</td>");
				out.println("<td> <input name=\"VEHICLE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=check_vehicle()> ");
				out.println("<input type=button name=veh_help value=Help class=\"but_input\" onclick=\"vehicle_help()\" >");
				out.println("</td>");*/
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMR>Rebate % *</td>");
				//out.println("<td><input name=\"TER_RATE\" type=\"text\" maxlength=\"6\" class=\"txt_input\" onchange=check_Rate(document.Form1.TER_RATE.value) STYLE=\"{text-align:right;}\" value=\"0\" >"); // commented by udara 24-12-2018
				out.println("<td><input name=\"TER_RATE\" type=\"text\" maxlength=\"6\" class=\"txt_input\" onblur=check_Rate(document.Form1.TER_RATE.value) STYLE=\"{text-align:right;}\" value=\"0\" onchange=\"check_rebate_change();\" >"); // added by udara 24-12-2018
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td >Finance Rate</td>");
				out.println("<td><input name=\"LEASE_RATE\"  type=\"text\" maxlength=\"6\"  class=\"txt_input\" disabled onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR) STYLE=\"{text-align:right;}\"> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMV>Termination Valid Date *</td>");
				out.println("<td><input name=\"TER_V_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR) disabled > ");
				out.println("    <input name=\"TER_V_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR) disabled > ");
				out.println("    <input name=\"TER_V_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR) disabled ><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td >Due Amount</td>");
				out.println("<td><input name=\"DUE_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("<input name=\"DUE_NET\" type=\"hidden\" ><input name=\"DUE_VAT\" type=\"hidden\" >");
				out.println("<input class='but_input' type='button' name='BUT_INV' value=\"Invoice Detail\" onClick=\"load_inv_data(document.Form1.APPLICATION_NO.value)\" style=\"width:90px;\">");
				//Added by Dineth on 2008-12-03
				out.println("<input class='but_input' type='button' name='BUT_ARREARS' value=\"Arrears Detail\" onClick=\"load_arrears_data(document.Form1.LEASE_NO.value)\" style=\"width:90px;\" disabled>");
				out.println("</td>"); 
				//out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Remark</td>");
				out.println("<td><input name=\"REMARK\"   type=\"text\" maxlength=\"200\"  class=\"txt_input\" style=\"width:300px;\"> ");
				out.println("</td>");
				out.println("<td >Termination Charge</td>");
				
				//out.println("<td><input name=\"TERM_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\">"); // commented by udara 30-10-2018
				
				// added by udara 30-10-2018
				//out.println("m_charge_applicable "+m_charge_applicable);
				if(m_charge_applicable.equals("Y"))
					out.println("<td><input name=\"TERM_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\"  >");
				else
					out.println("<td><input name=\"TERM_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled >");
				// end by udara 30-10-2018
				
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >VAT %</td>");
				out.println("<td><input name=\"VAT_PER\" type=\"text\" maxlength=\"5\" class=\"txt_input\" STYLE=\"{text-align:right;}\" disabled > ");//
				out.println("</td>");
				out.println("<td >Normal Rentals Due up to Termination Date</td>");
				out.println("<td><input name=\"DUE_RENTALS\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("<input name=\"DUE_RENTALS_NET\" type=\"hidden\" ><input name=\"DUE_RENTALS_VAT\" type=\"hidden\" >");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				//amount finance , NIBSM ,AMI(Amount) , Capital Repayment ,Total Capital already settled, % (Amount Setteled/Financed Amount) 
				out.println("<tr class=tr_input>");
				out.println("<td >Amount Finance</td>");
				out.println("<td><input name=\"AMOUNT_FINANCE\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td >NIBSM</td>");
				out.println("<td><input name=\"NIBSM\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>AMI</td>");
				out.println("<td><input name=\"AMI\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td>Unallocated Receipt</td>");
				out.println("<td><input name=\"Unallo_Rec\" type=\"text\" disabled class=\"txt_input\" STYLE=\"{text-align:right;}\">");
				out.println("<input class='but_input' type='button' name='BUT_REC' value=\"Receipt Detail\" onClick=\"load_Rec_data(document.Form1.CLIENT_CODE.value)\" style=\"width:90px;\">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>Total Capital Outstanding</td>");
				out.println("<td><input name=\"CAP_OUT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td>%</td>");
				out.println("<td><input name=\"CAP_OUT_PER\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>ODI</td>");
				out.println("<td><input name=\"ODI\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td>ODI Adjustment</td>");
				out.println("<td> ");
			    //out.println("<input name=\"ODI_ADJ_PER\" type=\"text\" maxlength=\"5\" class=\"txt_input\" style=\"{width:0.8cm;}\" onchange=\"cal_odi_per('1')\" STYLE=\"{text-align:right;}\" VALUE='0.00'> %"); // commented by udara 23-10-2018
				
				// commented by udara 01-11-2018
				/*
				out.println("<input name=\"ODI_ADJ_PER\" type=\"text\" maxlength=\"5\" class=\"txt_input\" style=\"{width:0.8cm;}\" onblur=\"check_odi_Rate()\" STYLE=\"{text-align:right;}\" VALUE='0.00'> %"); // added by udara 23-10-2018 // cal_odi_per('1')
				out.println("<input name=\"ODI_ADJ\" type=\"text\" maxlength=\"25\" size=\"20\" style=\"{width:2cm;}\" class=\"txt_input\" onchange=\"check_ODI_ADJ()\" STYLE=\"{text-align:right;}\" VALUE='0.00'> ");
				*/
				
				// added by udara 01-11-2018
				out.println("<input name=\"ODI_ADJ_PER\" type=\"text\" maxlength=\"5\" class=\"txt_input\" style=\"{width:0.8cm;}\" onblur=\"check_odi_rate_new()\" STYLE=\"{text-align:right;}\" VALUE='0.00' onchange=\"check_odi_change()\" > %"); 
				out.println("<input name=\"ODI_ADJ\" type=\"text\" maxlength=\"25\" size=\"20\" style=\"{width:2cm;}\" class=\"txt_input\" onblur=\"check_ODI_ADJ()\" STYLE=\"{text-align:right;}\" VALUE='0.00' onchange=\"check_odi_change()\" > ");
				// end by udara 01-11-2018
				
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>ODI Net</td>");
				out.println("<td><input name=\"ODI_NET\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("<td>Future Interest</td>");
				out.println("<td><input name=\"FU_INTE\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,21)\"  STYLE=\"{text-align:right;}\" VALUE='0.00' disabled >  "); //onchange=\"check_ODI_ADJ()\"
				out.println("</td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//Modified by Dineth on 2008-10-21
				out.println("<td>&nbsp;</td>");
				
				
				out.println("<td>&nbsp;</td>");
				//End by Dineth on 2008-10-21
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				// added by udara 06-01-2021
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp; </td>");
				out.println("<td>&nbsp; </td>");
					
				out.println("<td>Future Debit</td>");
				out.println("<td><input style='text-align:right' name=\"FUTURE_DEBIT\" id=\"FUTURE_DEBIT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" disabled> ");
				out.println("</td>");
					
				out.println("</tr>");
				// end by udara 06-01-2021
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=4><div id=veh><input type=hidden name=hid_vcount value=0></div></td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=inv>");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td WIDTH=25% id=H1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td WIDTH=25% id=H2 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td WIDTH=25% id=H3 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td WIDTH=25% id=H4 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S11 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S12 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S13 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S14 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S21 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S22 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S23 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S24 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S31 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S32 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S33 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S34 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S41 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S42 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S43 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S44 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S45 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S46 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S47 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S48 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=S51 STYLE=\"{font-weight: bold}\"></td>");
				out.println("<td id=S52  align=right></td>");
				out.println("<td id=S53  align=right></td>");
				out.println("<td id=S54  align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S61 STYLE=\"{font-weight: bold}\"></td>");
				out.println("<td id=S62  align=right></td>");
				out.println("<td id=S63  align=right></td>");
				out.println("<td id=S64  align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=T1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=T2 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T3 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T4 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				
				//Added by Dineth on 2008-10-01
				
				out.println("<tr>");
				out.println("<td id=S65 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S66 align=right></td>");
				out.println("<td id=S67 align=right></td>");
				out.println("<td id=S68 align=right></td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td id=T30 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=T31 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T32 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T33 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=REBATE STYLE=\"{font-weight: bold}\"></td><td ></td><td id=REBATE_AMT  align=right></td><td></td>");
				out.println("</tr>");
				//End by Dineth on 2008-10-01
				out.println("<tr>");
				out.println("<td>&nbsp;</td><td></td><td></td><td></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R2  align=right></td>");
				out.println("<td id=R7 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("<td id=R8 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R3  STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R4   align=right></td>");
				out.println("<td id=R9  STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=R10 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=R5  STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R6   align=right></td>");
				out.println("<td id=R11 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("<td id=R12 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R13 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R14  align=right></td>");
				out.println("<td id=R15 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=R16 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=M1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.UBFL-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=M2  align=right></td>");
				out.println("<td id=M3 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=M4 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</div>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=rec><input type=hidden name=hid_count value=0></div>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr class=tr_input>");
				
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				
				//out.println("<td><input type=button name=edit_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=cal_1 value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
				
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</form>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				
			}
			/*else if(m_chksql.trim().equals("get_term_details")){
				
			String m_finance_no = req.getParameter("finance_no");
			String m_vehicle_no = req.getParameter("veh_no");
				
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
							"AF_CR_TEMP_TERMINATION_CAPITAL(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
				callstmt1.setString(1 ,m_username);
		callstmt1.setString(2 ,m_vehicle_no);
		callstmt1.setString(3 ,m_finance_no);
				callstmt1.registerOutParameter(4,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(5,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(6,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(7,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(8,java.sql.Types.CHAR);
		callstmt1.registerOutParameter(9,java.sql.Types.CHAR);
		
				//out.println("t5");
			callstmt1.execute();
				//out.println("t6");
			
		//boolean flag = rs.next();
			out.println("<Root>");
			//for(; flag; flag = rs.next())				{
				out.println("<ITEM>");
				out.println("<CAP_AMT>"+  nf.format(callstmt1.getDouble(4))  + "</CAP_AMT>");
				out.println("<NIBSM>"  +  nf.format(callstmt1.getDouble(5))  + "</NIBSM>");
				out.println("<AMI_AMT>"+  nf.format(callstmt1.getDouble(6))  + "</AMI_AMT>");
				out.println("<CAP_OUT>"+  nf.format(callstmt1.getDouble(7))  + "</CAP_OUT>");
				out.println("<AMI_CAP>"+  nf.format(callstmt1.getDouble(8))  + "</AMI_CAP>");
				
				double m_per = ((callstmt1.getDouble(7))/callstmt1.getDouble(4))*100; 
				out.println("<CAP_PER>"+  nf.format(m_per)  + "</CAP_PER>");
				out.println("<CAP_PER>"+  nf.format(callstmt1.getDouble(9))  + "</CAP_PER>");
				out.println("</ITEM>");
			//}
																	
			//out.println("</DATA>");
			out.println("</Root>");
	
	}*/
			
			else if(m_chksql.trim().equals("get_Vehicles")){
				
				String m_Lease_no     = req.getParameter("Lease_no");
				String m_term_date    = req.getParameter("Ter_date");//ADDED BY KD 29-07-2014
				
				/* Commented By Kanishka Dilshan On 29-07-2014
				rs = stmt.executeQuery(" SELECT B.CHASSIS_NO,REPLACE(B.REG_NO,' ','') ,VAT_PERCENTAGE,VAT_ON_RENTAL,VAT_APP,B.INVOICE_NO, "+ 
					"        "+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE) "+ 
					" FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE  A.PRICING_NO     = B.PRICING_NO AND "+
					"        A.APPLICATION_NO = B.APPLICATION_NO AND "+  
					"        A.PRO_INVOICE_NO = B.INVOICE_NO AND "+
					"		 B.APPLICATION_NO IN (SELECT APPLICATION_NO "+
					"                             FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"                             WHERE  FINANCE_NO='"+m_Lease_no+"') AND "+
					"        B.ACTIVE_STATUS='Y' ");*/
				
				//Added By Kanishka Dilshan On 29-07-2014
				
				rs = stmt.executeQuery(" SELECT B.CHASSIS_NO,REPLACE(B.REG_NO,' ','') ,"+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(C.TRANSACTION_TYPE,'"+m_term_date+"') VATPERCENTAGE, "+
					" "+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(C.TRANSACTION_TYPE,'"+m_term_date+"') VATONRENTAL, "+
					" "+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(C.TRANSACTION_TYPE,'"+m_term_date+"') VATAPP,B.INVOICE_NO, "+ 
					"        "+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE) "+ 
					" FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
					" 		 "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					" WHERE  A.PRICING_NO     = B.PRICING_NO AND "+
					"        A.APPLICATION_NO = B.APPLICATION_NO AND "+  
					"        A.PRO_INVOICE_NO = B.INVOICE_NO AND "+
					"		 B.APPLICATION_NO = C.APPLICATION_NO AND"+
					"        C.FINANCE_NO     = '"+m_Lease_no+"' AND"+
					"        B.ACTIVE_STATUS='Y' ");
				
				/*"SELECT REG_NO "+
										"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
										"WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
										"                         FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"                         WHERE  FINANCE_NO='"+m_Lease_no+"') AND "+
										"      ACTIVE_STATUS='Y' "); */
				
				out.println("<table class=table border='0' width='100%' >");
				
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td WIDTH=10%>Id No</td>");
				out.println("<td WIDTH=10%>Chassis No</td>");
				out.println("<td WIDTH=10%>Vehicle No</td>");
				out.println("<td WIDTH=10%>Asset Description</td>");
				out.println("<td WIDTH=10%>VAT</td>");
				//Modified by Dineth on 2008-10-24
				//out.println("<td colspan=6>Residual value</td>");
				out.println("<td colspan=6>Sales Price</td>");
				out.println("</tr>");
				int j=0;
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input >");
					out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(7)+"</td>");
					out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
					if(rs.getString(1)==null){
						out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");//remove disabled
						out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
					}else{
						out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
						out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
					}
					j=j+1;
					/*if(rs.next()){
						out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
						if(rs.getString(1)==null){
							out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
						}else{
							out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\"></td>");
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
						}
						j=j+1;
					}else{
						out.println("<td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td>");
					}*/
					out.println("</tr>");
				}
				
				out.println("<input type=hidden name=hid_vcount value=\""+j+"\"></table>");
				
			}
			else if(m_chksql.trim().equals("get_Termi_Det")){
				
				String m_Termination_no    = req.getParameter("Termination_no");
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					"       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
					"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
					"WHERE  TERMINATION_NO='"+m_Termination_no+"' "+
					"ORDER BY to_date(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')"); 
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Installment Date</td>");
				out.println("<td  width='5%'  align=right>Percentage</td>");
				out.println("<td  width='20%' align=right>Rental</td>");
				out.println("<td  width='20%' align=right>P.V.</td>");
				out.println("<td  width='20%' align=right>Termination Amount</td>");
				out.println("<td  width='20%' align=right>P.V.</td>");
				out.println("</tr>");
				
				int j = 0;
				double rent=0;
				double rpv =0;
				double term=0;
				double tpv =0;
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ ><b>");
				out.println("<td></td>");
				out.println("<td ></td>");
				out.println("<td align=right id=rent><b></b></td>");
				out.println("<td align=right id=rpv ><b></b></td>");
				out.println("<td align=right id=term><b></b></td>");
				out.println("<td align=right id=tpv ><b></b></td>");
				out.println("</b></tr>");
				
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input >");
					out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
					out.println("");
					out.println("</tr>");
					rent = rent  + rs.getDouble(3);
					rpv  = rpv   + rs.getDouble(4);
					term = term  + rs.getDouble(5);
					tpv  = tpv   + rs.getDouble(6);
					j=j+1;
					
					if(rs.next()){
						out.println("<tr class=tr_input1 >");
						out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
						out.println("");
						out.println("</tr>");
						rent = rent  + rs.getDouble(3);
						rpv  = rpv   + rs.getDouble(4);
						term = term  + rs.getDouble(5);
						tpv  = tpv   + rs.getDouble(6);
						j=j+1;
					}
					
					
				}
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td><input type=hidden name=hid_count value="+j+"></td>");
				out.println("<td ></td>");
				out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
				out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
				out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
				out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				
				out.println("</table>");
				
			}
			
			else if(m_chksql.trim().equals("get_Termi_Char")){
				
				String m_Lease_no     = req.getParameter("Lease_no");
				String m_Vehicle_no   = req.getParameter("Vehicle_no");
				String m_Disco_rate   = req.getParameter("Disco_rate");
				String m_App_date     = req.getParameter("App_Date");
				String m_client       = req.getParameter("Client");
				String m_lease_rate   = req.getParameter("lease_rate");
				String m_vat_rate     = req.getParameter("vat_rate");
				String m_SaleVal      = req.getParameter("SaleVal");
				String m_Chassis_no   = req.getParameter("chas_no");
				String m_Invoice_no   = req.getParameter("invo_no");
				String m_sum_sal      = req.getParameter("sum_sal");
				String m_Term_val     = req.getParameter("Term_val");
				
				
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					"AF_CR_TEMP_TERMINATION_SAVE_N(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11);END;");
				callstmt1.setString(1 ,m_Disco_rate);
				callstmt1.setString(2 ,m_Vehicle_no);
				callstmt1.setString(3 ,m_Lease_no);
				callstmt1.setString(4 ,m_username);
				callstmt1.setString(5 ,m_App_date);
				callstmt1.setString(6 ,m_client);
				callstmt1.setString(7 ,m_lease_rate);
				callstmt1.setString(8 ,m_vat_rate);
				callstmt1.setString(9 ,m_SaleVal);
				callstmt1.setString(10,m_Chassis_no);
				callstmt1.setString(11,m_Invoice_no); 
				
				//out.println("t5");
				callstmt1.execute();
				//out.println("t6");
				out.println("<table class=table border='0' width='100%' >");
				
				//af_cr_get_no_future_rental,af_co_get_rentals_paid,af_co_get_no_rentals_arries,m_Term_val
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
					"       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
					"       SUM(TERMINATION_PV),INSTALLMENT_NO,"+
					"       SUM(TERMINATION_PV-TERMINATION_AMOUNT),SUM(INTEREST), "+
					"       SUM(INTEREST)-SUM(TERMINATION_AMOUNT) "+
					"FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
					"WHERE  ENT_USER='"+m_username+"' "+
					"GROUP  BY INSTALLMENT_NO, "+
					"       INSTALLMENT_DATE, PERCENTAGE "+
					"ORDER BY TO_DATE(TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),TO_NUMBER(INSTALLMENT_NO) ");
				
				
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Installment Date</td>");
				out.println("<td  width='5%'  align=right>Percentage</td>");
				out.println("<td  width='15%' align=right>Rental</td>");
				out.println("<td  width='15%' align=right>Capital</td>");
				out.println("<td  width='10%' align=right>Interest</td>");
				out.println("<td  width='10%' align=right>Int. Rebate</td>");
				
				out.println("<td  width='10%' align=right>Net Interest</td>");
				out.println("<td  width='10%' align=right>Vat</td>");
				out.println("<td  width='15%' align=right>Gross Termination</td>");
				out.println("</tr>");
				
				int j = 0;
				double rent=0;
				double rpv =0;
				double term=0;
				double tpv =0;
				double gtv =0;
				double vat =0;	
				double in  =0;	
				double inr =0;	
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td></td>");
				out.println("<td ></td>");
				out.println("<td align=right id=rent></td>");
				out.println("<td align=right id=rpv ></td>");
				out.println("<td align=right id=ina  ></td>");
				out.println("<td align=right id=inr ></td>");
				out.println("<td align=right id=term></td>");
				out.println("<td align=right id=tpv></td>");
				out.println("<td align=right id=gtv ></td>");
				out.println("</tr>");
				
				/* added decimals by Kanishka Dilshan to Below Details on 30-10-2014 */
				
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input >");
					if(rs.getDouble(3)>=0){ 
						out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					}else{
						out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					}
					out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf2.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
					out.println("<td align=right>"+nf2.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right>"+nf2.format(rs.getDouble(9))+"</td>");//kanishka
					out.println("<td align=right>"+nf2.format(rs.getDouble(10))+"</td>");//kanishka
					out.println("<td align=right>"+nf2.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
					//out.println("<td align=right>"+nf2.format(rs.getDouble(6)-(rs.getDouble(5)+rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j+"\" value=\""+(rs.getDouble(5)+rs.getDouble(4))+"\"></td>");
					out.println("<td align=right>"+nf2.format(rs.getDouble(6)-(rs.getDouble(5)+rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j+"\" value=\"0\"></td>");
					out.println("<td align=right>"+nf2.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
					out.println("");
					out.println("</tr>");
					rent = rent  + rs.getDouble(3);
					rpv  = rpv   + rs.getDouble(4);
					term = term  + rs.getDouble(5);
					tpv  = tpv   + rs.getDouble(6)-(rs.getDouble(5)+rs.getDouble(4));
					gtv  = gtv   + rs.getDouble(6);
					vat  = vat   + rs.getDouble(8);
					in   = in    + rs.getDouble(9);
					inr  = inr   + rs.getDouble(10);
					j=j+1;
					
					if(rs.next()){
						out.println("<tr class=tr_input1 >");
						out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
						out.println("<td align=right>"+nf2.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
						out.println("<td align=right>"+nf2.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
						out.println("<td align=right>"+nf2.format(rs.getDouble(9))+"</td>");//kanishka
						out.println("<td align=right>"+nf2.format(rs.getDouble(10))+"</td>");//kanishka
						out.println("<td align=right>"+nf2.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
						out.println("<td align=right>"+nf2.format(rs.getDouble(6)-(rs.getDouble(5)+rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j+"\" value=\""+(rs.getDouble(5)+rs.getDouble(4))+"\"></td>");
						out.println("<td align=right>"+nf2.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
						out.println("");
						out.println("</tr>");
						rent = rent  + rs.getDouble(3);
						rpv  = rpv   + rs.getDouble(4);
						term = term  + rs.getDouble(5);
						tpv  = tpv   + rs.getDouble(6)-(rs.getDouble(5)+rs.getDouble(4));
						gtv  = gtv   + rs.getDouble(6);
						vat  = vat   + rs.getDouble(8);
						in   = in    + rs.getDouble(9);
						inr  = inr   + rs.getDouble(10);
						j=j+1;
					}
					
					
				}
				
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td><input type=hidden name=hid_count value="+j+">");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td align=right>"+nf2.format(rent)+"<input type=hidden name=h_rent value="+nf2.format(rent)+"></td>");
				out.println("<td align=right>"+nf2.format(rpv) +"<input type=hidden name=h_rpv  value="+nf2.format(rpv)+"></td>");
				out.println("<td align=right>"+nf2.format(in)  +"<input type=hidden name=h_in   value="+nf2.format(in)+"></td>");
				out.println("<td align=right>"+nf2.format(inr) +"<input type=hidden name=h_inr  value="+nf2.format(inr)+"></td>");
				out.println("<td align=right>"+nf2.format(term)+"<input type=hidden name=h_term value="+nf2.format(term)+"></td>");
				out.println("<td align=right>"+nf2.format(tpv) +"<input type=hidden name=h_tpv  value="+nf2.format(tpv)+"></td>");
				out.println("<td align=right>"+nf2.format(gtv) +"<input type=hidden name=h_gtv  value="+nf2.format(gtv)+"><input type=hidden name=h_vat  value="+nf2.format(tpv)+"></td>");
				out.println("</tr>");
				
				rs = stmt.executeQuery (" SELECT '"+m_sum_sal+"' , "+
					"        round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)), "+
					"        '"+m_sum_sal+"'+ round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)) "+
					" FROM DUAL");
				if(rs.next()){       
					out.println("<tr class=tr_input>");
					out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value="+rs.getString(1)+"><input type=hidden name=hid_sv_val value="+rs.getString(2)+"><input type=hidden name=hid_sg_val value="+rs.getString(3)+"></td>");
					out.println("</tr>");
				}else{
					out.println("<tr class=tr_input>");
					out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value=0><input type=hidden name=hid_sv_val value=0><input type=hidden name=hid_sg_val value=0></td>");
					out.println("</tr>");
					
				}
				
				//out.println("************"+m_Term_val);
				
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
					"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
					"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
					//comment by ns on 20-05-2015
					//"        "+con_method.met_unformat_number(m_Term_val)+"*("+con_method.met_unformat_number(m_vat_rate)+"/100),"+
					//"        "+con_method.met_unformat_number(m_Term_val)+"+"+con_method.met_unformat_number(m_Term_val)+"*("+con_method.met_unformat_number(m_vat_rate)+"/100) "+//,"+
					"        0,"+
					"        "+con_method.met_unformat_number(m_Term_val)+" "+
					
					"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"	WHERE  FINANCE_NO= '"+m_Lease_no+"'");
				
				/*out.println (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
															"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
															"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
															"        round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),"+
															"        '"+m_Term_val+"'+round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)) "+//,"+
															//"        "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_App_date+"') "+
															"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															"	WHERE  FINANCE_NO= '"+m_Lease_no+"'");*/
				if(rs.next()){
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\""+nf1.format(rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=R_P value=\""+nf1.format(rs.getDouble(2))+"\"><input type=hidden name=R_A value=\""+nf1.format(rs.getDouble(3))+"\"><input type=hidden name=R_T value=\""+nf1.format(rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(1))+"\">");
					//out.println("<input type=hidden name=T_V value=\""+nf2.format(Math.round(rs.getDouble(4)))+"\"><input type=hidden name=T_S value=\""+nf2.format(Math.round(rs.getDouble(5)))+"\"><input type=hidden name=C_IRR value=\"\">");
					//Commented above By Kanishka Dilshan and add below on 31-12-2014
					out.println("<input type=hidden name=T_V value=\""+nf2.format(rs.getDouble(4))+"\"><input type=hidden name=T_S value=\""+nf2.format(rs.getDouble(5))+"\"><input type=hidden name=C_IRR value=\"\">");
				}else{
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\"\">");
					out.println("<input type=hidden name=R_P value=\"\"><input type=hidden name=R_A value=\"\">");
					out.println("<input type=hidden name=T_V value=\"\"><input type=hidden name=T_S value=\"\"><input type=hidden name=C_IRR value=\"\">");
					
				}
				out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("</table>");
				
			}else if(m_chksql.trim().equals("get_invoice_det")){
				
				String m_client      = req.getParameter("client");
				String m_finance_no  = req.getParameter("finance_no");
				
				rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
					"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
					"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
					"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
					"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
					"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
					"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
					"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Receipt No</td>");
				out.println("<td  width='15%' align=right>Receipt Amount</td>");
				out.println("<td  width='15%' align=right>Allocated Amount</td>");
				out.println("<td  width='20%' align=right>Balance Amount</td>");
				out.println("<td  width='35%' align=right>Amount</td>");
				//out.println("<td  width='10%'  ></td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
					out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
					out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
					//out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
					//out.println("     </td>");
					out.println("</tr>");
					out.println("<tr class=tr_input>");
					out.println("<td></TD>");
					out.println("<td colspan=5 ><div id='inv_"+j+"'><input type=hidden name=hid_invoice_count_"+j+" value=0></div>");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr class=tr_input>");
					out.println("<td>&nbsp;</TD>");
					out.println("<td colspan=5 >");
					out.println("</td>");
					out.println("</tr>");
					j=j+1;
					
				}
				
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
				
				
			}
			
			
			else if(m_chksql.trim().equals("get_Receipt")){
				
				String m_client      = req.getParameter("client");
				
				rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
					"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
					"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
					"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
					"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
					"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
					"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
					"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Receipt No</td>");
				out.println("<td  width='15%' align=right>Receipt Amount</td>");
				out.println("<td  width='15%' align=right>Allocated Amount</td>");
				out.println("<td  width='20%' align=right>Balance Amount</td>");
				out.println("<td  width='35%' align=right>Amount</td>");
				//out.println("<td  width='10%'  ></td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
					out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
					out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
					//out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
					//out.println("     </td>");
					out.println("</tr>");
					out.println("<tr class=tr_input>");
					out.println("<td></TD>");
					out.println("<td colspan=5 ><div id='inv_"+j+"'><input type=hidden name=hid_invoice_count_"+j+" value=0></div>");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr class=tr_input>");
					out.println("<td>&nbsp;</TD>");
					out.println("<td colspan=5 >");
					out.println("</td>");
					out.println("</tr>");
					j=j+1;
					
				}
				
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
				
				
			} 	
			
			else if(m_chksql.equals("SHOW_BALANCES_INFO")){
				
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Asset Financing System</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				//out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
				
				/*
				String		Sql_pending_invoice=" SELECT "+
																	"    INVOICE_NO, "+
																	"    NET_AMOUNT, "+
																	"    VAT_AMOUNT, "+
																	"    TOTAL_AMOUNT, "+
																	"    SETTELE_AMOUNT, "+
																	"    BALANCE_TO_BE_RECEIVED, "+ 
																	"    TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+ 
																	"   FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																	"   WHERE     ACTIVE_STATUS='Y' AND "+
																	"   FINANCE_NO IN   "+
																	"   (SELECT "+
																	"    FINANCE_NO "+ 
																	"    FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
																	"           APPLICATION_STATUS<>'CANCEL' AND BALANCE_TO_BE_RECEIVED>0) ";
				
				
				
				
					rs=stmt1.executeQuery(Sql_pending_invoice);
					boolean  more =rs.next();
							
					
					if (more) {
					
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='80%' class=div_input><u><b>Invoice Pending</b></u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						
						out.println("<br>");
					
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
						out.println("<td width='10%' class=div_input ><b>Date</b></td>");
						out.println("<td width='10%' class=div_input align='right'><b>Net</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>VAT</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Gross</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
					}
					out.println("<table align='center' width='100%' class='table' >");
					while(more){
						count++;
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input align='left'>"+rs.getString(7)+"</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
						
						out.println("</tr>");
						
					
						
						more = rs.next();
					}
						out.println("</table>");
					
																	
																	
									String		Sql_ODI=" SELECT "+
										"    ODI_REF_NO, "+
										"    INVOICE_NO, "+
										"    TO_DATE(ODI_DATE,'DD-MM-YYYY'), "+
										"    ODI_CAL_AMOUNT, "+
										"    ODI_BAL_AMOUNT, "+
										"    ODI_SETTLED_AMOUNT "+
										"  FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
										" WHERE INVOICE_NO IN "+
										"   (SELECT "+
										"    INVOICE_NO "+
										"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										"    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
										"           ACTIVE_STATUS='Y') "+
										"   AND    ODI_BAL_AMOUNT=0     ";
			
					rs=stmt1.executeQuery(Sql_ODI);
						more =rs.next();
						
					
					
					if (more) {
					
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='80%' class=div_input><u><b>Over Due Interest Pending</b></u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						
						out.println("<br>");
					
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input ><b>ODI Ref No</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Invoice No</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>ODI Date</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Balance Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
					}
					out.println("<table align='center' width='100%' class='table' >");
					while(more){
						count++;
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
						
						out.println("</tr>");
						
						more = rs.next();
					}
						out.println("</table>");
					
					*/
				String		Sql_Unallocated=" SELECT "+ 
					"  A.REC_NO, "+
					"  NVL(A.PAYER_ACC_NO,'-'), "+
					"  NVL(A.PAYER_BRANCH_CODE,'-'), "+
					"  A.REC_AMOUNT, "+
					"  NVL(A.ACC_NO,'-'), "+
					"  NVL(A.BRANCH_CODE,'-') ,"+
					"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
					//  "  STATUS "+
					"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+
					"       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"  WHERE  A.REC_NO=B.REC_NO  AND "+
					"         B.BAL_TOBE_RECEIVE<>0 AND STATUS <> 'C' "+
					//"         AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  "; // commented by udara 11-03-2019
					"         AND CLIENT_CODE='"+m_client_code+"'  "; // added by udara 11-03-2019
				
				rs=stmt1.executeQuery(Sql_Unallocated);
				boolean more =rs.next();
				
				
				
				if (more) {
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipt</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input ><b>Payer Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input ><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Branch Code</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					
					out.println("</tr>");
					
					more = rs.next();
				}
				out.println("</table>");
				
				
				/*				
										String		Sql_Realisation=" SELECT "+
								"    REC_NO,"+
								"    SETTLE_MODE,"+
								"    NVL(PAYER_ACC_NO,'-'), "+
								"    NVL(PAYER_BRANCH_CODE,'-'),"+
								"    REC_AMOUNT, "+
								"    DECODE(STATUS,'B','Banked','E','Entered') ,"+
								"    TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								"    FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
								"    WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
								"    SETTLE_MODE <>'CASH'  AND "+
								"    STATUS IN('B','E') ";
					
						
								rs=stmt1.executeQuery(Sql_Realisation);
									more =rs.next();
									
								
								
								if (more) {
								
									
									out.println("<br>");
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='80%' class=div_input><u><b>Receipt Pending Realisation </b></u></td>");
									out.println("<td width='*%'></td>");
									out.println("</tr>");
									out.println("</table>");
									
									out.println("<br>");
								
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input><b>Receipt</b></td>");
									out.println("<td width='10%' class=div_input ><b>Date</b></td>");
									out.println("<td width='10%' class=div_input><b>Settle Mode</b></td>");
									out.println("<td width='15%' class=div_input><b>Account No</b></td>");
									out.println("<td width='15%' class=div_input><b>Payer Branch Code</b></td>");
									out.println("<td width='15%' class=div_input><b>Status</b></td>");
									out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
									out.println("</tr>");
									
									out.println("</table>");
									out.println("<br>");
								}
								out.println("<table align='center' width='100%' class='table' >");
								while(more){
									count++;
									
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
									out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
									out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
									
									out.println("</tr>");
									
									more = rs.next();
								}
									out.println("</table>");
								
												String		Sql_POD=" SELECT "+
												"  POD_REF_NO, "+
												// "  FINANCE_NO, "+
												"  NVL(CHEQUE_NO,'-'), "+
												"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),  "+
												"  NVL(PAYER_ACC_NO,'-'), "+
												"  NVL(PAYER_BRANCH_CODE,'-'), "+
												"  CHEQUE_AMOUNT "+
												"  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
												"  WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
												"  STATUS='INV'   ";
					
					
						
								rs=stmt1.executeQuery(Sql_POD);
									more =rs.next();
									
								
								
								if (more) {
								
									
									out.println("<br>");
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='80%' class=div_input><u><b>Post Dated Cheque </b></u></td>");
									out.println("<td width='*%'></td>");
									out.println("</tr>");
									out.println("</table>");
									
									out.println("<br>");
								
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input><b>POD Ref.No</b></td>");
									out.println("<td width='15%' class=div_input><b>Cheque No</b></td>");
									out.println("<td width='15%' class=div_input><b>Cheque Date</b></td>");
									out.println("<td width='15%' class=div_input><b>Payer Account No</b></td>");
									out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
									out.println("<td width='15%' class=div_input align='right' ><b>cheque Amount</b></td>");
									out.println("</tr>");
									
									out.println("</table>");
									out.println("<br>");
								}
								out.println("<table align='center' width='100%' class='table' >");
								while(more){
									count++;
									
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
									out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
									
									out.println("</tr>");
									
									more = rs.next();
								}
									out.println("</table>");
					*/
				out.println("</body>"); 
				out.println("</HTML>"); 
				
				
			}
			
			// added by udara 06-01-2021
			if (m_chksql.trim().equals("getMoratoriumValues")){
								String finNo = req.getParameter("Lease_no");
								
								
					rs = stmt.executeQuery(" SELECT " + m_schema_name + ".AF_CO_GET_MORA_FLAG_STATUS(FINANCE_NO) MORATORIUM_STATUS " + 
									",NVL(" + m_schema_name + ".AF_CO_GET_FUTURE_DEBIT_AMOUNT(A.FINANCE_NO,NULL),0) FUTURE_DEBIT "+
									" FROM AF_CO_PRO_APPLICATION_DETAILS A "+
									" WHERE FINANCE_NO = '"+finNo+"' ");
								
					while (rs.next()){
							out.println(rs.getString(1) +"-"+rs.getString(2));									
					}
								
			}
			// added by udara 06-01-2021
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
