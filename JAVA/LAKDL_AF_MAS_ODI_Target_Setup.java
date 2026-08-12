

//SCREEN NAME:
//CREATED BY:SANDUN
//DATE/TIME:19/06/2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_ODI_Target_Setup extends javax.servlet.http.HttpServlet { 

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
			
			String chksql = req.getParameter("chksql"); 
			
			
			if(chksql.equals("main_page")){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>System Administration</title>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
		 
			 out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'MON'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
				out.println("document.Form1.TXT_MONTH.value = '"+rs1.getString(1)+"';");
				out.println("document.Form1.TXT_YEAR.value  = '"+rs1.getString(2)+"';");
				}
				out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  System Administration - ODI Target Setup - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  System Administration - ODI Target Setup - \"+document.Form1.hid_status.value;"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_ODI_Target_Setup?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_ODI_Target_Setup?chksql=main_page';"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println(" if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_src_name.value=m_val;"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";"); 
			out.println("document.Form1.hid_src_name.value=m_val;"); 
			out.println("mod_data();");
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 	
			
			
			out.println("function validate_data(){");
			out.println("if(document.Form1.TXT_YEAR.value==\"\"){");
			out.println("alert('Year Cannt be Empty...!')");
			out.println("document.Form1.TXT_YEAR.focus();");	
			out.println("return false;");
			out.println("}");	
		  out.println("else{");							
			out.println("return true;");
			out.println("}");			
			out.println("}");
		  
			out.println("function befor_save(){");
			out.println("submit_data();");
			out.println("}");	
			
			out.println("function val_fields(){");
			out.println("if(document.Form1.TXT_ODI_RATE.value==\"\"){");
			out.println("alert('Please enter ODI rate value...!');");
			out.println("return false;");
			out.println("}else");
			out.println("{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			out.println("function submit_data(){");
			out.println("if(validate_data()){");
			out.println(" val_fields();");
			out.println("if(document.Form1.TXT_ODI_RATE.disabled==true){");
			out.println("document.Form1.hid_src_name.value=\"EDIT\";");
			out.println("mod_data();");
			out.println("}else{");
			out.println("	if(confirm(\"Are you sure, you want to save data?\")){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Save_ODI_Target_Setup';");  
			out.println("		document.Form1.submit();	");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function mod_data(){");
			out.println("	if(confirm(\"Are you sure, you want to modify data?\")){ ");
			out.println(" makeRequest();");
			out.println("}");
			out.println("}");
			
			
			out.println("function check() {");			
			out.println("if(document.Form1.TXT_ODI_RATE.value > 100){");
			out.println("alert('Cannot setup rate more than 100%..!');");
			out.println("document.Form1.TXT_ODI_RATE.value=0");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.TXT_ODI_RATE.value=0;");
			out.println("target_month = document.Form1.TXT_MONTH.value+'-'+document.Form1.TXT_YEAR.value;");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=odi_target&target_month=\"+target_month+\" \";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			out.println("function get_vector(data_vec) {");
			out.println("	if(data_vec.length>0 && document.Form1.hid_src_name.value==\"EDIT\" ){");
			out.println(" assign_data(data_vec);");	
			out.println("document.Form1.TXT_ODI_RATE.disabled=false;");
			out.println(" }");
			out.println("	else if(data_vec.length>0 && document.Form1.hid_src_name.value==\"NEW\" ){");
			out.println(" assign_data(data_vec);");
			out.println("document.Form1.TXT_ODI_RATE.disabled=true;");
			out.println(" }");
			out.println("	else if(data_vec.length<=0){");
			out.println("document.Form1.TXT_ODI_RATE.disabled=false;");
			out.println("document.Form1.hid_src_name.value=\"NEW\";");
			out.println(" }");
			out.println(" }");			
						
			out.println("function assign_data(data){");
			out.println("document.Form1.TXT_ODI_RATE.value = data[0];");
			out.println("}");
			
			// Modified by Thamali Jayatunga on 2009.10.12, Added function val_rate.
			out.println("function val_rate(obj){");
			out.println("   if(isNaN(obj.value) ) { ");
			out.println("   alert('You have typed an incorrect character as a number');");
			out.println("   obj.value='';");
			out.println("   obj.focus();");
			out.println("   }");
			out.println("}");
	
			
			out.println("</Script>");
			
			out.println("<body onload=\"load_sysdate();makeRequest();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input  type='hidden' value='ODI_TARGET_SETUP' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<input type=hidden name=\"hid_src_name\" value=\"NEW\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
		
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>System Administration - ODI Target Setup</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='load_screen_status(\"SAVE\"),befor_save()' value=\"Save\"></td>");
			out.println("<td width='6%'></td>");			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
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
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("</table>");
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border='0'>"); 			
			out.println("<tr>");
			out.println("<td width='15%'>Month</td>");
			out.println("<td width='10%'>");
			out.println("<select name='TXT_MONTH' class='txt_input' style='width:100' onchange='makeRequest()'>");
			out.println("<option value='JAN'>January</option>");
			out.println("<option value='FEB'>February</option>");
			out.println("<option value='MAR'>March</option>");
			out.println("<option value='APR'>April</option>");
			out.println("<option value='MAY'>May</option>");
			out.println("<option value='JUN'>June</option>");
			out.println("<option value='JUL'>July</option>");
			out.println("<option value='AUG'>August</option>");
			out.println("<option value='SEP'>September</option>");
			out.println("<option value='OCT'>October</option>");
			out.println("<option value='NOV'>November</option>");
			out.println("<option value='DEC'>December</option>");
			out.println("</select>&nbsp;<input type='text' name='TXT_YEAR' maxlength=4 class='txt_input' style='width:50;text-align:right'>");
			out.println("</td>");			
			out.println("<td width='*%'>&nbsp;</td>");
			out.println("</tr>");			
			
			// Modified by Thamali Jayatunga on 2009.10.12, Added code to call function val_rate in onblur on TXT_ODI_RATE.
			out.println("<tr >"); 
			out.println("<td  width='15%' ><DIV id='DIV_TXT_ODI_RATE'  class=div_input>Rate</DIV></td>"); 
			out.println("<td width='10%' ><input class='txt_input' type='text' name='TXT_ODI_RATE' maxlength='3' style='width:100;text-align:right' onclick='this.select()' onblur='check(),val_rate(this)'>%"); //
			out.println("</td>"); 
			out.println("<td width='*%'></td>");			
			out.println("</tr>"); 
			
				
			out.println("</table>");	
							
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
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


