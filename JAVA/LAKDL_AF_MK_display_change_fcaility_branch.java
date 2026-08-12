/*[CREATED BY MILINDA  2014-2-20 FOR CHAGE FACILITY VICE BRANCH]*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MK_display_change_fcaility_branch extends javax.servlet.http.HttpServlet{
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			stmt=conn.createStatement();
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Marketing - Change Facility For Branch </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_chk_value=0;")	 ;
			out.println("var m_sav_msg=''");
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Credit - Change Branch For Facility - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Credit - Change Branch For Facility - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function close_screen() {");
			out.println("	if(document.Form1.close2.value==\"Proceed to Next Level\"){");
			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("	if(document.Form1.close2.value==\"Close\"){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("}");
			
			out.println("function MyDialog(){"); 
			out.println("	this.valout   = new Array(10);"); 
			out.println("}");
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			
			
			out.println("function help_update_finance_no() {");
			out.println("m_help=\"100\";");
			out.println("    document.Form1.hid_help_type.value=\"100\";");
			out.println("    m_sql = \"FinanceSql_finance_CHANGE\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\";"); 
			
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'100');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("reclor();");
			out.println("}");
			
			
			out.println("function help_button_location() {"); 
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    m_sql = \"new_m_help_TXT_LOCATION_CODE_sql\";"); 
			//out.println("alert(document.Form1.TXT_FINANCE_NO.value);");
			//out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"document.Form1.TXT_FINANCE_NO.value@\";"); 
			out.println("      m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\"+document.Form1.TXT_FINANCE_NO.value+\"@\";");
			
			//out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\"+\"document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'9');");  
			out.println("}"); 
			
			
			out.println("function help_value_assign_location() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			//out.println("    document.Form1.TXT_LOCATION_DESC.value=oBj.valout[3];");
			out.println("reclor();");
			
			out.println("}"); 
			
			
			
		
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"100\"){"); 
			out.println("		help_update_value_assign_2();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
			out.println("		}"); 
			
			out.println("       if(IfCount=='9'){"); 
			out.println("		help_value_assign_location();"); 
			out.println("}");
			
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
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	");
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			
			out.println("function clear_fields() {");
			out.println("	if(document.Form1.hid_help_type.value==\"9\"){"); 
			out.println("document.Form1.TXT_LOCATION_CODE.value=\"\";");
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_change_fcaility_branch';"); 
			out.println("		}"); 
			out.println("}"); 
			out.println("function new_window(){	"); 
			
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"LAKDL_AF_MK_display_change_fcaility_branch';"); 
			out.println("}"); 
			
			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println("	m_option = document.Form1.hid_status.value;"); 
			out.println("	if(m_option=='New') {");
			out.println("		m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("	}"); 
			out.println("		if(validate_data()){"); 
			out.println("			if(confirm(m_sav_msg)){ "); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_change_fcaility_branch';");  
			out.println("				document.Form1.submit();"); 
			out.println("			}"); 
			out.println("		    }");
			out.println("	else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("	}");
			out.println("}"); 
			
			out.println("function validate_data(){");
			//validation goes here
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("DIV_TXT_FINANCE_NO.style.color='red'; ");
			out.println("return false; ");
			out.println("}");
			out.println("else if(document.Form1.TXT_LOCATION_CODE.value==\"\"){");
			out.println("DIV_TXT_LOCATION_CODE.style.color='red'; ");
			out.println(" return false; ");
			out.println("}");
			out.println("else{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			out.println("function reclor(){");
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("DIV_TXT_FINANCE_NO.style.color='black';");
			out.println("}");
			out.println("else if(document.Form1.TXT_LOCATION_CODE.value!=\"\"){");
			out.println("DIV_TXT_LOCATION_CODE.style.color='black';");
			out.println("}");
			out.println("}");
			
			
			
			
			out.println("</script>");
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
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
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Change Branch For Facility</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");   
			
			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr>"); 
			out.println("<td width='5%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input><B>Facility No</B></DIV></td>"); 
			out.println("<td width='17%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='10' style='width:100' onblur=\"help_update_finance_no()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_finance_no()\"></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='5%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input><B>New Branch Code</B></DIV></td>"); 
			out.println("<td width='17%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='20' size='10' style='width:100' onblur=\"help_button_location()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_location()\"></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			
			
			
			out.println("</table>");  
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			//}
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