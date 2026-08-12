//Created by Minal for #14286 on 10-10-2014
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Insurance_P_H_Report extends javax.servlet.http.HttpServlet { 

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


			String m_chksql = req.getParameter("chksql");			
			String m_to_date     = req.getParameter("to_date");			
			String m_from_date   = req.getParameter("from_date");		
			String m_id          = req.getParameter("id");
			
			
			if(m_chksql.equals("View_Report")){	
			
		/*	String m_insurence_done          = req.getParameter("insurance_done");
			if(m_insurence_done.equals("ALL")){
				m_insurence_done = "";
			}*/
			
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
			
				 // rs=stmt.executeQuery();
															 
	
			//boolean more = rs.next();
			int j=0;
			double m_tot_amt = 0.0;
			int m_tot_cases   = 0;
			
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr>");
			out.println("<td width='100%' align ='center'><b>Insurance Payment History Report </td>"); 
			out.println("</tr>");
			out.println("</tr>");
			out.println("<td width='100%' align ='center'><b>From "+m_from_date+" To "+m_to_date+"</td>"); 			
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			
			
		/*	if(!more){
			out.println("<br><br>");
			out.println("<table align='center' width='100%' class='table' border=0>");
			out.println("<tr>");
			out.println("<td width='100%' align ='center'><font color='red'>No Data Found..!</td>"); 					
			out.println("</tr>");
			out.println("</table>");
			}
			else{	*/		
			out.println("<table align='center' width='100%' class='table' border=0>");
		  out.println("<tr class=pdn_txtpos2 >");
			out.println("<td width='20%' >Date</td>");
			out.println("<td width='20%' >Payment Number</td>");						
			out.println("<td width='10%' >Insurance Provider</td>");
			out.println("<td width='20%' >Payment Amount</td>");
			out.println("<td width='10%' align ='right'>No.of Cases</td>");		
			out.println("</tr>");
			
			//while(more){
			
		/*	if(j>0 && j%2==1){
          out.println("<tr class=tr_input1 >");
				}
				else{
          out.println("<tr class=tr_input >");
				}*/
			//out.println("<td width='20%' align ='left' STYLE='cursor:hand;' onclick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u></td>");
			out.println("<td width='20%' align ='left'>DD/MM/YYYY</td>");			
			out.println("<td width='10%' align ='left'>0.00</td>");
			out.println("<td width='10%' align ='left'>####</td>");
			out.println("<td width='20%' align ='right'>0.00</td>");
			out.println("<td width='20%' align ='right'>0</td>");
			
			out.println("</tr>");			
			m_tot_amt = m_tot_amt;//+rs.getDouble(3)
			m_tot_cases   = m_tot_cases;//+rs.getDouble(4)
			
			//more = rs.next();			
			//j++;
			
			
			
			out.println("<tr >");
			
			out.println("<td colspan=4  width='20%' align='right'><b>"+nf.format(m_tot_amt)+"</td>");
			out.println("<td width='20%' align='right'><b>"+nf.format(m_tot_cases)+"</td>");
			out.println("</tr>");	
			out.println("</table>");
			
    //}
		
					
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

