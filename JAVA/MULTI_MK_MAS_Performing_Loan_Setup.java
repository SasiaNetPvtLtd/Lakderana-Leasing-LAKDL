
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - FIELDS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class MULTI_MK_MAS_Performing_Loan_Setup extends javax.servlet.http.HttpServlet { 
  Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;
	ServletOutputStream out = null;
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
	
	
		try { 
			 
			MULTI_AF_CO_conn_methods m_sn_methods = new MULTI_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			String m_chksql = req.getParameter("chksql");
				
			if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Performing Loans Setup</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FILED_CODE.value==\"\"){  "); 
			//out.println("alert('Field Code Can not Be Blank');"); 
			out.println("DIV_TXT_FILED_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DESCRIPTION.value==\"\"){  "); 
		//	out.println("alert('Description Can not Be Blank');"); 
			out.println("DIV_TXT_DESCRIPTION.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			//out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			//out.println("		if(validate_data()){"); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Save_Perform_loan';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			//out.println("		}"); 
			//out.println("		}"); 
			//out.println("else{");
			//out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			//out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Performing_Loan_Setup?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Performing_Loan_Setup?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_MULTI_MK_MAS_Performing_Loan_Setup\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Performing Loans Setup - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Performing Loans Setup - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_DESCRIPTION.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Deactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else"); 
			
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
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{");
			out.println("	clear_data();");
			out.println("	}");
			
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_data() {");
		
			
			
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_FILED_CODE.value='';");
			out.println("document.Form1.TXT_FILED_CODE.focus();");
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"1\"){");
			out.println("document.Form1.TXT_DESCRIPTION.value='';");
			out.println("document.Form1.TXT_DESCRIPTION.focus();");
			out.println("}");
		
			out.println("}");
			
			
			out.println("function help_update_finance() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"ClientSql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_FIN_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_FIN_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[9];"); 
			out.println("}"); 
						
			out.println("function help_value_assign_1() {"); 
			//out.println("    document.Form1.TXT_AREA_DESC.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=\"\";"); 
			out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
			out.println("}"); 
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_FILED_DESC_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
				out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			//out.println("		help_update_value_assign_99();"); 
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
			out.println("    m_sql = \"m_view_TXT_FILED_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_FILED_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" details.innerHTML = ''; ");
			out.println(" details.innerHTML = http_response; ");
			out.println("}");
			
			out.println("function make_request() {");
			out.println(" 	m_fin_no      = document.Form1.TXT_FIN_NO.value ");
			out.println(" 	m_client_code = document.Form1.TXT_CLIENT_CODE.value ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_Performing_Loan_Setup?chksql=load_details&finance_no=\"+m_fin_no+\"&client_code=\"+m_client_code+\"\";"); 
			//out.println("alert(m_url)");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
	
      
			out.println(" function change_perform_status(num){");
			out.println(" if(document.Form1.elements[\"TXT_PERF_\"+num].checked==true){");
			out.println("document.Form1.elements[\"TXT_PERF_\"+num].value='on';");
			out.println("}");
			out.println("if(document.Form1.elements[\"TXT_PERF_\"+num].checked==false){");
			out.println("document.Form1.elements[\"TXT_PERF_\"+num].value='off';");
			out.println("}");
			//out.println("alert(num+'  '+document.Form1.elements[\"TXT_PERF_\"+num].value);");
			out.println("}");
			
			out.println(" function change_nperform_status(num){");
			out.println(" if(document.Form1.elements[\"TXT_NPERF_\"+num].checked==true){");
			out.println("document.Form1.elements[\"TXT_NPERF_\"+num].value='on';");
			out.println("}");
			out.println("if(document.Form1.elements[\"TXT_NPERF_\"+num].checked==false){");
			out.println("document.Form1.elements[\"TXT_NPERF_\"+num].value='off';");
			out.println("}");
			//out.println("alert(num+'  '+document.Form1.elements[\"TXT_NPERF_\"+num].value);");
			out.println("}");
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Performing Loans Setup</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%'></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' border=0 class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='10%' ><DIV id='DIV_TXT_FIN_NO'  class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_FIN_NO' maxlength='25' size='10' onblur=\"help_update_finance()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_finance()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='20' onblur=\"help_update_finance()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_finance()\" ></td>"); 
			out.println("<td width='*%'><input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"View\" onClick=\"make_request()\" ></td>"); 
			out.println("</tr>"); 
			
				
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<div id=details></div>");
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			else if(m_chksql.equals("load_details")){
			
			String m_finance_no = req.getParameter("finance_no");
			String m_client_code = req.getParameter("client_code");
			
			
			String m_non_perform_sql = " SELECT DISTINCT B.APPLICATION_NO,B.FINANCE_NO,B.CLIENT_CODE "+
					                       " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					                       " WHERE B.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','NORM_TERMI','LEGAL') "+
					                       " AND NVL("+m_schema_name+".AF_CO_GET_LEGAL_AGE(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0) + NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF_LEGAL(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0)>=6 "+
																 " AND B.FINANCE_NO LIKE '"+m_finance_no+"%' "+
																 " AND B.CLIENT_CODE LIKE '"+m_client_code+"%' "+
																 " AND B.FINANCE_NO NOT IN ( SELECT FINANCE_NO "+
																 " FROM "+m_schema_name+".AF_CO_PERFORMING_CONTACTS "+
																 " /*WHERE STATUS = 'NPERFORM' */) "+
																 " UNION ALL "+
																 " SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
																 " FROM "+m_schema_name+".AF_CO_PERFORMING_CONTACTS "+
																 " WHERE FINANCE_NO LIKE '"+m_finance_no+"%' "+
																 " AND CLIENT_CODE LIKE '"+m_client_code+"%' "+
																 " AND STATUS = 'NPERFORM' ";
     
			String m_perform_sql   = " SELECT DISTINCT B.APPLICATION_NO,B.FINANCE_NO,B.CLIENT_CODE "+
				                       " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				                       " WHERE B.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','NORM_TERMI','LEGAL') "+
				                       " AND NVL("+m_schema_name+".AF_CO_GET_LEGAL_AGE(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0) + NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF_LEGAL(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0)< 6 "+
															 " AND B.FINANCE_NO LIKE '"+m_finance_no+"%' "+
															 " AND B.CLIENT_CODE LIKE '"+m_client_code+"%' "+
															 " AND B.FINANCE_NO NOT IN ( SELECT FINANCE_NO "+
															 " FROM "+m_schema_name+".AF_CO_PERFORMING_CONTACTS "+
															 " /*WHERE STATUS = 'NPERFORM'*/ ) "+
															 " UNION ALL "+
															 " SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
															 " FROM "+m_schema_name+".AF_CO_PERFORMING_CONTACTS "+
															 " WHERE FINANCE_NO LIKE '"+m_finance_no+"%' "+
															 " AND CLIENT_CODE LIKE '"+m_client_code+"%' "+
															 " AND STATUS = 'PERFORM' ";
  
			rs1 = stmt1.executeQuery(m_perform_sql);
			boolean more1 = rs1.next();
			
			rs2 = stmt2.executeQuery(m_non_perform_sql);
			boolean more2 = rs2.next();
			int j=0;
			int k=0;
			  out.println("<br>");
			  out.println("<table width='60%' border='0' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='25%' align='left'><b>Finance No</b></td>");//1
				out.println("<td width='25%' align='left'><b>Application No</b></td>");//2
				out.println("<td width='10%' align='center'><b>Status</b></td>");//3
				out.println("</tr>");
				if(more1){
				out.println("<tr height=40>");
				out.println("<td colspan='4'><u><b>Performing Loans</td>");
				out.println("</tr>");
				
				while(more1){
			
				out.println("<tr height=25>");
				out.println("<td width='25%' align='left'>"+rs1.getString(2)+"<input type='hidden' name=\"TXT_HID_FIN_"+j+"\" value="+rs1.getString(2)+"></td>");//1
				out.println("<td width='25%' align='left'>"+rs1.getString(1)+"<input type='hidden' name=\"TXT_HID_APP_"+j+"\" value="+rs1.getString(1)+"><input type='hidden' name=\"TXT_HID_CLIENT_"+j+"\" value="+rs1.getString(3)+"></td>");//2
				out.println("<td width='10%' align='center'><input type='checkbox' name=\"TXT_PERF_"+j+"\" value=\"off\" onclick=change_perform_status("+j+")></b></td>");//3
				out.println("</tr>");				
				more1 = rs1.next();	
				j=j+1;
				}
				
				}
				
				if(more2){
				out.println("<tr height=40>");
				out.println("<td colspan='4'><u><b>Non Performing Loans</td>");
				out.println("</tr>");
				
				while(more2){
				
				out.println("<tr height=25>");
				out.println("<td width='25%' align='left'>"+rs2.getString(2)+"<input type='hidden' name=\"TXT_HID_NFIN_"+k+"\" value="+rs2.getString(2)+"></td>");//1
				out.println("<td width='25%' align='left'>"+rs2.getString(1)+"<input type='hidden' name=\"TXT_HID_NAPP_"+k+"\" value="+rs2.getString(1)+"><input type='hidden' name=\"TXT_HID_NCLIENT_"+k+"\" value="+rs2.getString(3)+"></td>");//2
				out.println("<td width='10%' align='center'><input type='checkbox' name=\"TXT_NPERF_"+k+"\" value=\"off\" onclick=change_nperform_status("+k+") ></td>");//3
				out.println("</tr>");		
				more2 = rs2.next();
				k=k+1;
				}
				}
				out.println("<input type='hidden' name='perf_num' value='"+j+"'>");
				out.println("<input type='hidden' name='nperf_num' value='"+k+"'>");
				out.println("</table>");			
			
			
			}
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
