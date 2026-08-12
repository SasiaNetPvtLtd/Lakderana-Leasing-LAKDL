


//CREATED BY:SANDUN
//DATE/TIME:29/01/2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_Alteration_Odi extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();			

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;		

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			ServletOutputStream out = res.getOutputStream(); 
			
			String m_rate      ="";
			String m_eff_year  ="";
			String m_eff_month ="";
			String m_eff_date  ="";		
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>System Administration</title>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
		 			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  System Administration - ODI Alteration - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  System Administration - ODI Alteration - \"+document.Form1.hid_status.value;"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Alteration_Odi';"); 
			out.println("		}"); 
			out.println("}"); 
			
			
			out.println("function load_screen_status(m_val){"); 
			out.println(" if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";"); 
			out.println("document.Form1.hid_src_name.value=m_val;"); 
			out.println("mod_data();");
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 	
			
		
			
			// Added by Disnaka Jayasuriya on 2009.10.12, Added code to call function format_number.
			out.println("function val_odi_rate(obj){");
			out.println("   if(isNaN(obj.value) ) { ");
			out.println("   alert('You have typed an incorrect character as a number');");
			out.println("   obj.value='';");
			out.println("   obj.focus();");
			out.println("   }");
			out.println("if(obj.value!=\"\"){");
			out.println("format_number(obj,2)");
			out.println("}");

			out.println("}");
			
			
			out.println("function validate_data(){");
			out.println("if(document.Form1.VAL_DAY.value==\"\" ||document.Form1.VAL_MONTH.value==\"\" || document.Form1.VAL_YEAR.value==\"\"){");
			out.println("VDATE.style.color='red';");
			out.println("return false;");
			out.println("}");	
   		out.println("else if(document.Form1.TXT_ODI_RATE.value==\"\") {");
			out.println("DIV_TXT_ODI_RATE.style.color='red';");
			out.println("return false;");
			out.println("}else{");
			out.println("return true;");
			out.println("}");			
			out.println("}");
		  
			out.println("function befor_save(){");
			out.println("submit_data();");
			out.println("}");				
			
			
			out.println("function submit_data(){");
			out.println("if(document.Form1.VAL_DAY.disabled==false || document.Form1.VAL_MONTH.disabled==false || document.Form1.VAL_YEAR.disabled==false || document.Form1.TXT_ODI_RATE.disabled==false){");
			out.println("if(validate_data()){");
			out.println("	if(confirm(\"Are you sure, you want to save data?\")){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_ODI_Alteration_Save';");  
			out.println("		document.Form1.submit();	");
			out.println("}");
			out.println("}");
			out.println("}else{");
			out.println("alert('No new data to save..!');");
			out.println("}");
			out.println("}");
			
			out.println("function mod_data(){");
			out.println("	if(confirm(\"Are you sure, you want to modify data?\")){ ");
			out.println("document.Form1.VAL_DAY.disabled=false;");
			out.println("document.Form1.VAL_MONTH.disabled=false;");
			out.println("document.Form1.VAL_YEAR.disabled=false;");
			out.println("document.Form1.TXT_ODI_RATE.disabled=false;");
			out.println("}");
			out.println("}");			
			
			out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");			
      out.println("}");
			out.println("}");
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=290,top=180,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		  out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY.value=v_date;");
			out.println("     document.Form1.VAL_MONTH.value=v_month;");
			out.println("     document.Form1.VAL_YEAR.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
		  out.println("}");
			out.println("}");
			
			out.println("function onLoad_data(){");
			rs = stmt.executeQuery("SELECT TO_CHAR(APPLY_DATE,'DD-MM-YYYY'), RATE FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE ");
			if(rs.next()){
			m_eff_date  = rs.getString(1).substring(0,2);
			m_eff_month = rs.getString(1).substring(3,5);
			m_eff_year  = rs.getString(1).substring(6,10);
      m_rate      = rs.getString(2);	
			}
			out.println("document.Form1.VAL_DAY.value=\""+m_eff_date+"\";");
			out.println("document.Form1.VAL_MONTH.value=\""+m_eff_month+"\";");
			out.println("document.Form1.VAL_YEAR.value=\""+m_eff_year+"\";");
			out.println("document.Form1.TXT_ODI_RATE.value=\""+m_rate+"\";");
			out.println("}");
			
			out.println("</Script>");
			
			out.println("<body onload=\"onLoad_data()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input  type='hidden' value='BUSSINESS_VOL_SETUP' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"hid_src_name\" value=\"AF_AD_ODI_ALTERATION\"></td>");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>System Administration - ODI Alteration</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
			out.println("<tr>");  
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
			out.println("<tr >"); 
			out.println("<td width='15%' ID=VDATE>Effective Date</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) disabled> ");
			out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) disabled> ");
			out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) disabled><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>");			
			// Modified by Disnaka Jayasuriya on 2009.10.12, Replace maxlength 22 with 6.
			out.println("<tr>"); 
			out.println("<td  width='15%' ><DIV id='DIV_TXT_ODI_RATE'  class=div_input>Rate</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_ODI_RATE' maxlength='6' onblur='val_odi_rate(this)' style='width:100;text-align:right' disabled >"); 
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


