// DEVELOP BY : UDARA FOR MRFL   DATE:11-10-2013

    
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_display_Income_provision_reverse extends javax.servlet.http.HttpServlet { 

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
			Statement stmt1;
			ResultSet rs1;
			conn = m_sn_methods.met_user_validate(req); 
			stmt1=conn.createStatement();
			
			String m_start_date="";
			String m_end_date="";
			
			rs1= stmt1.executeQuery("SELECT TO_CHAR(ADD_MONTHS(PROVISION_DATE,1),'DD-MM-YYYY'),"+
			" TO_CHAR(LAST_DAY(ADD_MONTHS(PROVISION_DATE,1)),'DD-MM-YYYY') "+
 			" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_ROUTINE ");
			
			if(rs1.next()){
			m_start_date=rs1.getString(1);
			m_end_date=rs1.getString(2);
			}
			
			// added by udara on 14-10-2013

			int check_count = 0;

			rs1= stmt1.executeQuery(" "+
					" SELECT COUNT(*) "+
					" FROM  "+m_schema_name+".AF_CO_INCOME_SUS_PRO_LOG "+
					" WHERE SCREEN = 'AF_INCOME_SUS_PROV_SUGGEST' "+
				" ");
			
			if(rs1.next()){
				check_count = rs1.getInt(1);
			}
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension Reversals</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			 
			out.println("var m_sav_msg='';");
			out.println("var timerID;");
			out.println("var durationID=0;");
			
			out.println("function set_timer_actions() {");
			out.println("   durationID=durationID+1;");
			out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
			out.println("		product_feature_details.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
			out.println("}");
			
			out.println("function run_report() {");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to Run Provision for Bad and Doubtful Debts and Income Suspension Reversals ?\")){ ");
			out.println("   	set_timer_actions();");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Income_Provision_sql_validations_normal?chksql=INCOME_SUSPENCE_REPORT&start_date=\"+document.Form1.TXT_REPORT_ST_PERIOD.value+\"&end_date=\"+document.Form1.TXT_REPORT_END_PERIOD.value;");
			//out.println("			window.open(m_url);");
			out.println("			load_interface(m_url,'NO');");
			out.println("		}");
			out.println("	}");
			out.println("}");
			
			out.println("function edit_report() {");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to Edit Provision for Bad and Doubtful Debts and Income Suspension Reversals ?\")){ ");
			out.println("   	set_timer_actions();");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Income_Provision_sql_validations_normal?chksql=INCOME_SUSPENCE_REPORT_EDIT&start_date=\"+document.Form1.TXT_REPORT_ST_PERIOD_VIEW.value+\"&end_date=\"+document.Form1.TXT_REPORT_END_PERIOD_VIEW.value;");
			//out.println("			window.open(m_url);");
			out.println("			load_interface(m_url,'NO');");
			out.println("		}");
			out.println("	}");
			out.println("}");
			
			out.println("function view_report() {");
			out.println("	if(validate_data()){"); 
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Income_Provision_sql_validations_normal?chksql=INCOME_SUSPENCE_REPORT_RPT_REVERSE&start_date=\"+document.Form1.TXT_REPORT_ST_PERIOD_VIEW.value+\"&end_date=\"+document.Form1.TXT_REPORT_END_PERIOD_VIEW.value;");
			out.println("			window.open(m_url);");
			out.println("	}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		clearTimeout(timerID);");
			out.println("		product_feature_details.innerHTML=m_data;");
			out.println("}");
			
			out.println("function clear_data(){"); 
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	//validations goes here"); 
			out.println("	if(document.Form1.TXT_REPORT_ST_PERIOD.value==\"\"){"); 
			out.println("		DIV_TXT_1.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_REPORT_END_PERIOD.value==\"\"){"); 
			out.println("		DIV_TXT_1.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_REPORT_ST_PERIOD_VIEW.value==\"\"){"); 
			out.println("		DIV_TXT_2.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_REPORT_END_PERIOD_VIEW.value==\"\"){"); 
			out.println("		DIV_TXT_2.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else{");
			out.println("		return true;");
			out.println("	}");
			out.println("}"); 			

			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+m_sav_msg+\" ?\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_Income_provision_reverse_save';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("else { "); 
			out.println("	alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("	}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("//document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_Income_provision_reverse';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_Income_provision_reverse';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_MAS_FEE_PACK\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Provision for Bad and Doubtful Debts and Income Suspension Reversals - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Provision for Bad and Doubtful Debts and Income Suspension Reversals - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("}"); 
			
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Save\";"); 
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
			out.println("		m_sav_msg=\"Modify\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
			out.println("		m_sav_msg=\"Deactivate\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
			out.println("		m_sav_msg=\"Reactivate\";");  
			out.println("	}");
			out.println("	else{");  
			out.println("		m_sav_msg=\"\";");  
			out.println("	}"); 
			out.println("}"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Provision for Bad and Doubtful Debts and Income Suspension Reversals</td>"); 
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
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\" disabled></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='20%'><DIV id='DIV_TXT_1'  class=div_input>Income Suspension & Provision Period</DIV></td>"); 
			out.println("<td width='10%'>From Date</td>");
			out.println("<td width='15%'><input class='txt_input' type='text' name='TXT_REPORT_ST_PERIOD' maxlength='10' size='10' value=\""+m_start_date+"\"  style='width:70' disabled></td>"); 
			out.println("<td width='10%'>To Date</td>");
			out.println("<td width='15%'><input class='txt_input' type='text' name='TXT_REPORT_END_PERIOD' maxlength='10' size='10' value=\""+m_end_date+"\"  style='width:70' disabled></td>"); 
			out.println("<td width='30%'> &nbsp; </td>");  // out.println("<td width='30%'><input class='but_input' type='button' name='BUT_RUN_REPORT' value=\"Run Report\" onClick=\"run_report()\" style='width:100'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='20%'><DIV id='DIV_TXT_2'  class=div_input>Report Period - (View)</DIV></td>"); 
			out.println("<td width='10%'>From Date</td>");
			out.println("<td width='15%'><input class='txt_input' type='text' name='TXT_REPORT_ST_PERIOD_VIEW' maxlength='10' size='10' value=\""+m_start_date+"\"  style='width:70' ></td>"); 
			out.println("<td width='10%'>To Date</td>");
			out.println("<td width='15%'><input class='txt_input' type='text' name='TXT_REPORT_END_PERIOD_VIEW' maxlength='10' size='10' value=\""+m_end_date+"\"  style='width:70' ></td>"); 
			
			//if(check_count>0)
				out.println("<td width='30%'><input class='but_input' type='button' name='BUT_RUN_REPORT' value=\"View Report\" onClick=\"view_report()\" style='width:100'></td>"); // out.println("<td width='30%'><input class='but_input' type='button' name='BUT_EDIT_REPORT' value=\"Edit Report\" onClick=\"edit_report()\" style='width:100'><input class='but_input' type='button' name='BUT_RUN_REPORT' value=\"View Report\" onClick=\"view_report()\" style='width:100'></td>"); 
			//else
				//out.println("<td width='30%'><input class='but_input' type='button' name='BUT_RUN_REPORT' value=\"View Report\" onClick=\"view_report()\" style='width:100' disabled></td>");
			
			out.println("<td width='*%'></td>"); 
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
