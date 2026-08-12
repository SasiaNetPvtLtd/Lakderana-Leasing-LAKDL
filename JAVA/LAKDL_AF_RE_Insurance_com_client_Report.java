

//SCREEN NAME:INSURANCE DETAIL HISTORY 
//CREATED BY:SANDUN
//DATE/TIME:12/01/2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Insurance_com_client_Report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1;
		public ResultSet rs,rs1;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
      nf.setMaximumFractionDigits(2);
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();			

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;		

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type = req.getParameter("chksql");			
			String m_to_date     = req.getParameter("to_date");			
			String m_from_date   = req.getParameter("from_date");		
			String m_id          = req.getParameter("id");
			
			
			if(m_screen_type.trim().equals("client_report")){	
			
			String m_insurence_done          = req.getParameter("insurance_done");
			if(m_insurence_done.equals("ALL")){
				m_insurence_done = "";
			}
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
		 			 
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Insurance History Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Insurance History Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}");		
			
			out.println("function load_screen_status(m_val){"); 
			out.println(" if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 			
						
		
			out.println("</Script>");
			
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			out.println("<tr>");			
			out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
			out.println("<td class=\"border_wht\" valign=\"top\"> ");
			out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
			out.println("<tr> ");
			out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
			out.println("</tr>");
			out.println("<tr> ");
			out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style=\"height: 327px\">");
						
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Insurance History Report</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
				
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
      out.println("</table>");
			out.println("</td>	");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");			
			
				  rs=stmt.executeQuery(" SELECT  A.INVOICE_NO, "+//1
												       " A.FINANCE_NO, "+       //2
												       " NVL(A.TOTAL_AMOUNT,0), "+//3
												       " NVL(A.SETTELE_AMOUNT,0), "+//4
												       " NVL(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'-'), "+//5
												       " NVL(TO_CHAR(C.RECON_DATE,'DD-MM-YYYY'),'-') "+//6
															 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A , "+
															 "      "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
															 "      "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C, "+
															 "      "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D  "+
															 " WHERE A.INVOICE_NO = B.REF_NO "+
															 " AND B.SUS_REF_NO   = D.SUS_REF_NO "+
															 " AND C.PAYMENT_NO   = D.PAYMENT_NO "+
															 " AND A.FINANCE_NO LIKE '%"+m_id+"%' "+
																"	AND UPPER("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_FIN_NO(B.REF_NO))) LIKE TRIM(UPPER('%"+m_insurence_done+"%')) "+
															 " AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
															 " AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')");
															 
			
			boolean more = rs.next();
			int j=0;
			double m_tot_debit = 0.0;
			double m_tot_set   = 0.0;
			
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr>");
			out.println("<td width='100%' align ='center'><b>Insurance History Detail - Client Wise </td>"); 
			out.println("</tr>");
			out.println("</tr>");
			out.println("<td width='100%' align ='center'><b>From "+m_from_date+" To "+m_to_date+"</td>"); 			
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			
			
			if(!more){
			out.println("<br><br>");
			out.println("<table align='center' width='100%' class='table' border=0>");
			out.println("<tr>");
			out.println("<td width='100%' align ='center'><font color='red'>No Data Found..!</td>"); 					
			out.println("</tr>");
			out.println("</table>");
			}
			else{			
			out.println("<table align='center' width='100%' class='table' border=0>");
		  out.println("<tr class=pdn_txtpos2 >");
			out.println("<td width='20%' >Finance No</td>");
			out.println("<td width='20%' >Debit Note No</td>");						
			out.println("<td width='10%' >Value Date</td>");
			out.println("<td width='20%' >Disbursed Date</td>");
			out.println("<td width='10%' align ='right'>Debit Amount</td>");
			out.println("<td width='20%' align ='right'>Client Settlment</td>");			
			out.println("</tr>");
			
			while(more){
			
			if(j>0 && j%2==1){
          out.println("<tr class=tr_input1 >");
				}
				else{
          out.println("<tr class=tr_input >");
				}
			out.println("<td width='20%' align ='left' STYLE='cursor:hand;' onclick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u></td>");
			out.println("<td width='20%' align ='left'>"+rs.getString(1)+"</td>");			
			out.println("<td width='10%' align ='left'>"+rs.getString(5)+"</td>");
			out.println("<td width='10%' align ='left'>"+rs.getString(6)+"</td>");
			out.println("<td width='20%' align ='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='20%' align ='right'>"+nf.format(rs.getDouble(4))+"</td>");
			
			out.println("</tr>");			
			m_tot_debit = m_tot_debit+rs.getDouble(3);
			m_tot_set   = m_tot_set+rs.getDouble(4);
			
			more = rs.next();			
			j++;
			}
			
			
			out.println("<tr >");
			out.println("<td colspan=4 width='60%' align='right'><b>Total</td>");
			out.println("<td width='20%' align='right'><b>"+nf.format(m_tot_debit)+"</td>");
			out.println("<td width='20%' align='right'><b>"+nf.format(m_tot_set)+"</td>");
			out.println("</tr>");	
			out.println("</table>");
			
    }
		
					
			out.println("</table>");
		  
		
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</html>");
			
			}
		
			else if(m_screen_type.trim().equals("company_report")){
								
			String m_insurence_done          = req.getParameter("insurance_done");
			if(m_insurence_done.equals("ALL")){
				m_insurence_done = "";
			}
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
		 			 
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Insurance History Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Insurance History Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}");			
			
			out.println("function load_screen_status(m_val){"); 
			out.println(" if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}");
			
			out.println("function load_details(val) {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Payment_Voucher?chksql=main_page&payment_no=\"+val+\"&print=TRUE;\"");
			out.println("window.open(m_url,'popupwin','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=0')");
			out.println("} "); 
						
		
			out.println("</Script>");
			
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			out.println("<tr>");			
			out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
			out.println("<td class=\"border_wht\" valign=\"top\"> ");
			out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
			out.println("<tr> ");
			out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
			out.println("</tr>");
			out.println("<tr> ");
			out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style=\"height: 327px\">");
						
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Insurance History Report</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
				
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
      out.println("</table>");
			out.println("</td>	");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");			
					
				 
				rs=stmt.executeQuery(" SELECT B.PAYMENT_NO, "+
												       " NVL(A.PAYEE_NAME,'-'), "+
												       " NVL(SUM(C.INT_BAL_SETTLE_AMOUNT),0), "+
												       " NVL(TO_CHAR(B.RECON_DATE,'DD-MM-YYYY'),'-') "+
															 " FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF A, "+
														   "	    "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B, "+
															 "	    "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
															 " 	    "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D "+
															 "	WHERE C.SUS_REF_NO = D.SUS_REF_NO "+
															 "	AND A.PAYEE_CODE = C.RECEIVER "+
															 "	AND B.PAYMENT_NO = D.PAYMENT_NO "+
															 "  AND C.BAL_TO_BE_PAID = 0 "+
															 " 	AND TO_DATE(TO_CHAR(B.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
															 "	AND TO_DATE(TO_CHAR(B.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
															 "	AND TRIM(UPPER(A.PAYEE_NAME)) LIKE TRIM(UPPER('%"+m_id+"%')) "+
															"	AND UPPER("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_FIN_NO(C.REF_NO))) LIKE TRIM(UPPER('%"+m_insurence_done+"%')) "+
															 "  GROUP BY B.PAYMENT_NO,A.PAYEE_NAME,TO_CHAR(B.RECON_DATE,'DD-MM-YYYY')");
			
			
			boolean more = rs.next();
			int j=0;
			double m_tot = 0.0;	
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr>");
			out.println("<td width='100%' align ='center'><b>Insurance History Detail - Company Wise </td>"); 
			out.println("</tr>");
			out.println("</tr>");
			out.println("<td width='100%' align ='center'><b>From "+m_from_date+" To "+m_to_date+"</td>"); 			
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			
			if(!more){
			out.println("<br><br>");
			out.println("<table align='center' width='100%' class='table' border=0>");
			out.println("<tr>");
			out.println("<td width='100%' align ='center'><font color='red'>No Data Found..!</td>"); 					
			out.println("</tr>");
			
			}
			else{			
		  out.println("<table align='center' width='100%' class='table' border=0>");
		  out.println("<tr class=pdn_txtpos2 >");
			out.println("<td width='20%' >Payment No</td>");
			out.println("<td width='30%' >Company Name</td>");						
			out.println("<td width='20%' >Disbursed Date</td>");
			out.println("<td width='20%' align ='right'>Amount</td>");
			out.println("<td width='10%' align ='center'>Detail</td>");			
			out.println("</tr>");
			
			while(more){
			
			if(j>0 && j%2==1){
          out.println("<tr class=tr_input1 >");
				}
				else{
          out.println("<tr class=tr_input >");
				}
			out.println("<td width='20%' align ='left' style= 'cursor:hand;cursor' onclick=\"show_payment_drill('"+rs.getString(1)+"')\"><u>"+rs.getString(1)+"</u></td>");
			out.println("<td width='30%' align ='left'>"+rs.getString(2)+"</td>");			
			out.println("<td width='20%' align ='left'>"+rs.getString(4)+"</td>");
			out.println("<td width='20%' align ='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='10%' align ='center'><input type='button' name='DETA_BUTTON_"+j+"' value='Detail' class='but_input' onClick=load_details('"+rs.getString(1)+"')></td>");
			
			out.println("</tr>");			
			m_tot = m_tot+rs.getDouble(3);		
			
			more = rs.next();			
			j++;
			}
			
			
			out.println("<tr >");
			out.println("<td colspan=3 width='60%' align='right'><b>Total</td>");
			out.println("<td width='20%' align='right'><b>"+nf.format(m_tot)+"</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("</tr>");			
			
    }
		
					
			out.println("</table>");
		  out.println("</table>");
		
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</html>");
			
			
			}
					
      
			}

			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


