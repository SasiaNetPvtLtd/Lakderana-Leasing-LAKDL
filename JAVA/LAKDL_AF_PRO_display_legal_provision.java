//Develop by Dineth on 16-04-2009
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
public class LAKDL_AF_PRO_display_legal_provision extends javax.servlet.http.HttpServlet { 
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			Connection conn;
			Statement stmt;
			ResultSet rs;
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			String m_date_dd = "";
			String m_date_mm = "";
			String m_date_yy = "";
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Legal Provisioning</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("function get_sys_date(){");
					rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
					if(rs.next()){
							m_date_dd = rs.getString(1).substring(0,2);
							m_date_mm = rs.getString(1).substring(3,5);
							m_date_yy = rs.getString(1).substring(6,10);
					}
			out.println("document.Form1.TXT_ASAT_DAY.value   =\""+m_date_dd+"\"");
			out.println("document.Form1.TXT_ASAT_MONTH.value =\""+m_date_mm+"\"");
			out.println("document.Form1.TXT_ASAT_YEAR.value  =\""+m_date_yy+"\"");
			out.println("}");
			
			out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");			
     	out.println("}");
			out.println("}");
			    
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_legal_provision';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function load_lock(){	"); 
			out.println("//document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_legal_provision';"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Legal Provisioning - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Legal Provisioning - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("}"); 
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		 	out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.TXT_ASAT_DAY.value=v_date;");
			out.println("     document.Form1.TXT_ASAT_MONTH.value=v_month;");
			out.println("     document.Form1.TXT_ASAT_YEAR.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form1.hid_asat_date.value=date1");			
			out.println("}");
			out.println("}");
			
			out.println("function run_report(){");
			out.println("asat_date = document.Form1.TXT_ASAT_DAY.value+'-'+document.Form1.TXT_ASAT_MONTH.value+'-'+document.Form1.TXT_ASAT_YEAR.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=run_report&asat_date=\"+asat_date+\"&sort_column=AGGREGATE_AGE&order_by_type=ASC\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println("}");
					
			out.println("function view_report(){ ");	
			out.println("asat_date = document.Form1.TXT_ASAT_DAY.value+'-'+document.Form1.TXT_ASAT_MONTH.value+'-'+document.Form1.TXT_ASAT_YEAR.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=view_report&asat_date=\"+asat_date+\"&sort_column=AGGREGATE_AGE&order_by_type=ASC\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");	
					
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_sys_date();load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_asat_date' VALUE=\"\">");
					
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Legal Provisioning</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\" disabled></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table' border=0>"); 
  		out.println("<tr class=tr_input>");
			out.println("<td width='20%' ID=DIV_FROM_DATE>As At Date</td>");
			out.println("<td width='30%' ><input name=\"TXT_ASAT_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=checkMonthLength(document.Form1.TXT_ASAT_DAY,document.Form1.TXT_ASAT_MONTH,document.Form1.TXT_ASAT_YEAR)> "); // Added number validations by Udara on 12/10/2009
			out.println("    <input name=\"TXT_ASAT_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=checkMonthLength(document.Form1.TXT_ASAT_DAY,document.Form1.TXT_ASAT_MONTH,document.Form1.TXT_ASAT_YEAR)> "); // Added number validations by Udara on 12/10/2009
			out.println("    <input name=\"TXT_ASAT_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=checkMonthLength(document.Form1.TXT_ASAT_DAY,document.Form1.TXT_ASAT_MONTH,document.Form1.TXT_ASAT_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> "); // Added number validations by Udara on 12/10/2009
			out.println("</td>");
			out.println("<td width='30%'><input class='but_input' type='button' name='BUT_RUN_REPORT' value=\"Run Report\" onClick=\"run_report()\" style='width:100'><input class='but_input' type='button' name='BUT_VIEW_REPORT' value=\"View Report\" onClick=\"view_report()\" style='width:100'></td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>"); 
			out.println("<DIV id='product_feature_details'  class=div_input></DIV>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			
			
			}
			catch (Exception ex) {
				try{out.println("Error:"+ex.toString());
				}catch(Exception e){}
			}
			finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
