// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006


import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_MAS_display_factoring_default_val extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Factoring Default Values</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("	if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.TXT_CREDIT_LIMIT.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_CREDIT_PERIOD.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_TOLERANCE_CREDIT_PERIOD.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_RESERVE_MARGIN.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_INT_RATE.value=data_vec[4];"); 
			out.println("	}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_factoring_default_val\";");
			out.println("	load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function enable_all(){");	
			out.println("document.Form1.TXT_CREDIT_LIMIT.disabled=false;"); 
			out.println("document.Form1.TXT_CREDIT_PERIOD.disabled=false;"); 
			out.println("document.Form1.TXT_TOLERANCE_CREDIT_PERIOD.disabled=false;"); 
			out.println("document.Form1.TXT_RESERVE_MARGIN.disabled=false;"); 
			out.println("document.Form1.TXT_INT_RATE.disabled=false;");  
			out.println("}"); 
			
			out.println("function disable_all(){");	
			out.println("document.Form1.TXT_CREDIT_LIMIT.disabled=true;"); 
			out.println("document.Form1.TXT_CREDIT_PERIOD.disabled=true;"); 
			out.println("document.Form1.TXT_TOLERANCE_CREDIT_PERIOD.disabled=true;"); 
			out.println("document.Form1.TXT_RESERVE_MARGIN.disabled=true;"); 
			out.println("document.Form1.TXT_INT_RATE.disabled=true;");  
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("	if(document.Form1.TXT_CREDIT_LIMIT.value==\"\"){  "); 
			out.println("	DIV_TXT_CREDIT_LIMIT.style.color='red';");
			out.println("	return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_CREDIT_PERIOD.value==\"\"){  "); 
			out.println("	DIV_TXT_CREDIT_PERIOD.style.color='red';");
			out.println("	return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_TOLERANCE_CREDIT_PERIOD.value==\"\"){  "); 
			out.println("	DIV_TXT_TOLERANCE_CREDIT_PERIOD.style.color='red';");
			out.println("	return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_RESERVE_MARGIN.value==\"\"){  "); 
			out.println("	DIV_TXT_RESERVE_MARGIN.style.color='red';");
			out.println("	return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_INT_RATE.value==\"\"){  "); 
			out.println("	DIV_TXT_INT_RATE.style.color='red';");
			out.println("	return false;"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("	return true;"); 
			out.println("	}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("			if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("				for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("					document.Form1.elements[i].disabled=false;");
			out.println("				}");
			out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_MAS_save_factoring_default_val';");  
			out.println("			document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("		else{");
			out.println("			alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("		} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_factoring_default_val';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_factoring_default_val';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_factoring_default_val\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" System Administration - Factoring Default Values - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" System Administration - Factoring Default Values - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 


			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_CREDIT_LIMIT.disabled=true;"); 
			out.println("document.Form1.TXT_CREDIT_PERIOD.disabled=true;"); 
			out.println("document.Form1.TXT_TOLERANCE_CREDIT_PERIOD.disabled=true;"); 
			out.println("document.Form1.TXT_RESERVE_MARGIN.disabled=true;"); 
			out.println("document.Form1.TXT_INT_RATE.disabled=true;"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
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
			out.println("}"); 
			
			out.println("function val_num(obj){");
			out.println("if(!isPosInteger(obj.value)){");
			out.println("alert('Please Enter a Number');");
			out.println("obj.value=\"\" ");
			out.println("obj.focus();}");
			out.println("}");
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_View_Help_Servlet?class_in=\"+client_name+\"FA_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
									
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_view_TXT_FACTORING_DEFAULT_VALUES_sql\";");
			out.println("    m_criteria = \"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),disable_all(),load_roll_value('New'),makeRequest()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration -  Factoring Default Values</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");'  onclick='enable_all()' value=\"Edit\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CREDIT_LIMIT'  class=div_input>Credit Limit *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CREDIT_LIMIT' maxlength='25' size='25' STYLE='{text-align:right;}' onBlur=\"format_num(document.Form1.TXT_CREDIT_LIMIT,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CREDIT_PERIOD'  class=div_input>Credit Period *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CREDIT_PERIOD' maxlength='25' size='25' STYLE='{text-align:right;}' onBlur=\"format_num(document.Form1.TXT_CREDIT_PERIOD,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOLERANCE_CREDIT_PERIOD'  class=div_input>Tolerance Credit Period *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOLERANCE_CREDIT_PERIOD' maxlength='25' size='25' STYLE='{text-align:right;}' onBlur=\"format_num(document.Form1.TXT_TOLERANCE_CREDIT_PERIOD,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_RESERVE_MARGIN'  class=div_input>Reserve Margin % *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESERVE_MARGIN' maxlength='25' size='25' STYLE='{text-align:right;}' onBlur=\"format_num(document.Form1.TXT_RESERVE_MARGIN,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INT_RATE'  class=div_input>Interest Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INT_RATE' maxlength='25' size='25' STYLE='{text-align:right;}' onBlur=\"format_num(document.Form1.TXT_INT_RATE,4)\"></td>"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
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
