

//SCREEN NAME:INSURANCE DETAIL HISTORY FOR ASSET INSURANCE DETAIL REPORT
//CREATED BY:SANDUN
//DATE/TIME:07/01/2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Asset_Insurnce_History extends javax.servlet.http.HttpServlet { 

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


			String m_screen_type= req.getParameter("chksql");			
			String m_finance_no  = req.getParameter("finace_no");
			String m_invoice_no  ="";
			
			if(m_screen_type.trim().equals("main_page")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
		 			 
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Asset Insurance History - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Asset Insurance History - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}");
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=main_page';"); 
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Asset Insurance History </td>");
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
					
				  rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+ //1
												       " A.PRO_INVOICE_NO,"+ //2
												       " A.POLICY_NO, "+ //3
												       " A.ASSET_DESCRIPTION, "+ //4
												       " TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //5
												       " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //6
												       " A.SUM_INSSURED, "+ //7
												       " A.PREMIUM, "+ //8
												       " INITCAP(A.INSURED_BY), "+ //9
												       " A.INSUR_COM, "+  //10
															 " NVL(A.REMARKS,'-'), "+//11
															 " ENT_USER, "+//12
															 " TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+//13
															 " NVL(TO_CHAR(MOD_DATE,'DD-MM-YYYY HH24:MI:SS'),'-'), "+//14
															 " NVL(MOD_USER,'-') "+//15
												       " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK A "+		
															 " WHERE A.FINANCE_NO = '"+m_finance_no+"' ");
															 
			
			boolean more = rs.next();
			out.println("<br>");
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr>");
			out.println("<td width='10%' align ='left'><b>Finance No : "+m_finance_no+"</td>"); 
			out.println("<td width='20%' align ='left'></td>"); 			
			out.println("</tr>");
			out.println("</table>");
			if(!more){
			out.println("<br><br>");
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr>");
			out.println("<td width='100%' align ='center'><font color='red'>No Insurance History Details</td>"); 					
			out.println("</tr>");
			out.println("</table>");
			}
			
						
			while(more){
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr>");
			out.println("<td width='10%' align ='left'><b>Policy No</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(3)+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='10%' align ='left'><b>Start Date</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(5)+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='10%' align ='left'><b>End Date</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(6)+"</td>");
			out.println("</tr>");
			
						
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Sum Inssured</td>"); 
			out.println("<td width='20%' align ='left'>"+nf.format(rs.getDouble(7))+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Premium</td>"); 
			out.println("<td width='20%' align ='left'>"+nf.format(rs.getDouble(8))+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Insured By</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(9)+"</td>");
			out.println("</tr>");
					
					
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Insurance company</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(10)+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Remarks</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(11)+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>&nbsp;</td>"); 
			out.println("<td width='20%' align ='left'>&nbsp;</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Enter User</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(12)+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Enter Date</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(13)+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Modify User</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(15)+"</td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'><b>Modify Date</td>"); 
			out.println("<td width='20%' align ='left'>"+rs.getString(14)+"</td>"); 			
			out.println("</tr>");
			
			out.println("<hr>");
			more = rs.next();			
			
			}
			

					
			out.println("</table>");
		  out.println("</table>");
		
			out.println("</form>");
			out.println("</body>");
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


