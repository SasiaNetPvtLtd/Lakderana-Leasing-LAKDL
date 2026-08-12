// DEVELOP BY : DISNAKA FOR OFSCL FACTORING    DATE:2011-09-14

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_LT_Legal_Termination_App extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	public String m_chksql;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	Connection conn;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			m_chksql = req.getParameter("chksql");
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			stmt = conn.createStatement ();
			
			if (m_chksql == null ) {
				m_chksql = "main_page";
			}
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			else if(m_chksql.trim().equals("main_page")){
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Operation Reports - Legal Termination Settlement Appvoval</TITLE>"); 
				
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</HEAD>");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				
				//out.println("		    makeRequest();");
				out.println("		}");
				out.println("}");
				
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		report_data.innerHTML=m_data;");
				out.println("}");
				
				
				
				
				
				
				
				out.println("function before_submit(){ "); 
				out.println("	get_display_msg();");
				out.println("	if(validate_data()){"); 
				out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
				out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("				document.Form1.elements[i].disabled=false;");
				out.println("			}");
				//out.println("			if(validate_data()){"); 
				out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination_App_save';");  
				out.println("				document.Form1.submit();	"); 
				//out.println("			}"); 
				out.println("		}"); 
				out.println("	}"); 
				out.println("} "); 
				
				
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				
				out.println("function validate_data(){");
				
				out.println("	var count = parseInt(document.Form1.elements[\"NUM_CHKS\"].value) ");
				out.println("   for (var i = 1; i <= count ; i++) {"); 
				
				out.println("		if (document.Form1.elements[\"received_\"+i].checked == true) {");
				out.println("	        return true; ");
				out.println("		}");
				out.println("   }");
				
				out.println("	alert(\"Select legal termination settlement for approve \"); ");
				out.println("	return false;"); 
				out.println("}"); 
				
				
				
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination_App';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination_App';"); 
				out.println("}"); 
				
				out.println("function save_window(){	"); 
				out.println("	before_submit();"); 
				out.println("}"); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_STATEMENT_REPORT\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("	help_box.innerHTML=\" Operation Reports - Legal Termination Settlement Appvoval - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Operation Reports - Legal Termination Settlement Appvoval \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	    document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				
				
				out.println("}");
				
				out.println("function load_screen_status(m_val){"); 
				out.println("		if(m_val==\"NEW\"){"); 
				out.println("			new_window();"); 
				out.println("		}"); 
				out.println("		else if(m_val==\"HELP\"){"); 
				out.println("			load_help_msg();"); 
				out.println("		}"); 
				out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("		if(m_val==\"NEW\"){");
				out.println("			document.Form1.hid_status.value=\"New\";"); 
				out.println("		}");
				out.println("		else if(m_val==\"EDIT\"){");  
				out.println("			document.Form1.hid_status.value=\"Edit\";");  
				out.println("		}");
				out.println("		else if(m_val==\"DACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("		}");
				out.println("		else if(m_val==\"RACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("		}");
				out.println("		else{");  
				out.println("			document.Form1.hid_status.value=\"\";");  
				out.println("		}"); 
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
				
				out.println("function MyDialog(){"); 
				out.println("	this.valout   = new Array(10);"); 
				out.println("}"); 
				
				out.println("function help_button_finance_no() {"); 
				out.println(" document.Form1.hid_help_type.value=\"1\";"); 
				out.println(" m_sql = \"m_help_TXT_FINANCE_NO_LEGAL_TERMINATION_sql\";"); 
				out.println(" m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@ACTIVATED@\";"); 
				out.println(" HelpBox('1','10','0');"); 
				out.println("}"); 
				
				
				out.println("function help_update_value_assign_1() {"); 
				out.println("		document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("		document.Form1.hid_client_code.value=oBj.valout[3];"); 
				
				out.println("}");
				
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_LT_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(document.Form1.hid_help_type.value);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("if(document.Form1.hid_help_type.value=='1'){"); 
				out.println("		help_update_value_assign_1();"); 
				out.println("}");
				
				
				out.println("	}"); //end next
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); //end prev
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("	}	"); 
				out.println("	}		"); ///close
				out.println("	else{");
				out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
				out.println("	}");
				out.println("	}	"); //
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
				
				out.println(" if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println(" 	document.Form1.TXT_FINANCE_NO.value='';");
				out.println(" 	document.Form1.TXT_FINANCE_NO.focus();");
				out.println(" }");
				
				out.println("}");
				
				/*out.println("function viewInvoice() {");
				
				out.println(" if(document.Form1.TXT_FINANCE_NO.value!=\"\"){"); 
				out.println("      makeRequestInvDet();");
				out.println(" }");
				
				out.println("}");
				*/
				
				out.println(" function makeRequestAppDet() {");
				out.println("    	m_url = '" + m_class_url + "/" + m_fschema_name + "AF_LT_sql_validations_normal?chksql=LEGAL_INV_DET_APP' ;"); 
				out.println("    	load_interface(m_url, 'NO');");
				out.println(" }");
				
				
				
				out.println("function calWaveVal(){");
				out.println("	var m_total    = 0 ;");
				out.println("	var count = parseInt(document.Form1.elements[\"NUM_CHKS\"].value) ");
				out.println("   for (var i = 1; i <= count ; i++) {"); 
				
				out.println("		if (document.Form1.elements[\"received_\"+i].checked == true) {");
				out.println("	        m_total = m_total + parseFloat(unformat_noobject(document.Form1.elements[\"waveAmtId_\"+i].value));");
				out.println("		}");
				out.println("   }");
				out.println("   m_total = parseFloat(m_total);");
				out.println("	document.Form1.elements[\"hid_wave_amt\"].value = m_total");
				out.println("	format_num(document.Form1.hid_wave_amt,4);");
				out.println("	div_wave_amt.innerHTML=document.Form1.hid_wave_amt.value;");
				out.println("}");	
				
				
				
				out.println("           function show_details(i) {");
				out.println("               var legTerminationNo = document.Form1.elements[\"ltNo_\"+i].value; ");
				out.println("               var m_url = servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_LT_Legal_Termination_App_det?legTerminationNo=' + legTerminationNo");
				
				out.println("               var sFeatures = '';");
				out.println("               sFeatures += 'dialogHeight: ' + (parseInt(screen.availHeight) - 100) + 'px;';");
				out.println("               sFeatures += 'dialogWidth: ' + (parseInt(screen.availWidth) - 150) + 'px;';");
				
				out.println("               window.showModalDialog(");
				out.println("                   m_url,");
				out.println("                   legal_termination_detail,");
				out.println("                   sFeatures");
				out.println("               );");
				out.println("           }");
				
				out.println("           function legal_termination_detail() {");
				out.println("               var legal_termination_detail;");
				out.println("           }");
				
				out.println("</script>"); 
				
				
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' onload=\"load_lock();makeRequestAppDet();\">");//request_reports()
				
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_wave_amt' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"0\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Reports - Legal Termination Settlement Appvoval</td>"); 
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
				
				/*out.println("<table align='center' width='100%' class='table'>"); 
				
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number *</DIV></td>"); 
				out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"\" >"); //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\"
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_finance_no()\"></td>"); 
				out.println("<td width='20%'><input class='but_input' type='button' name='BUT_TXT_VIEW' value=\"View\" onClick=\"viewInvoice()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("</table>"); */
				out.println("<br>"); 
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='report_data'></DIV></td>");
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
				out.println("</html>"); 
				
			}
			
			else if (m_chksql.trim().equals("LEGAL_INV_DET_APP")){
				
				
				String m_string        = "";
				
				rs= stmt.executeQuery(""+
					" SELECT "+
					" 	A.TERMINATION_NO, "+
					" 	A.FINANCE_NO , "+ 
					" 	A.CLIENT_CODE, "+
					" 	A.TOT_TER_AMOUNT "+
					" FROM "+m_schema_name+".AF_LT_FIN_INVOICE A WHERE APP_STATUS = 'N' ");
				
				
				
				
				m_string=m_string+"<table align='left' width='800px' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Legal Temination Settlement No.</b></DIV></td>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Finance No.</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Total Wave off Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%'  align='center' ><DIV class=div_input><b>Approve</b></DIV></td>";
				m_string=m_string+"<td width='15%'  align='center' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"<td width='6%'  align='center' ><DIV class=div_input><b>Select</b></DIV></td>";
				//m_string=m_string+"<td width='10%'  align='center' ><DIV class=div_input><b>Details</b></DIV></td>";
				
				m_string=m_string+"</tr>";
				
				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					
					m_string=m_string+"<td width='15%' style= cursor:hand; onClick=\"show_details('"+chk_nums+"')\" ><input type='hidden' class='txt_input' name='ltNo_"+chk_nums+"'  value=\""+rs.getString(1)+"\"><DIV class=div_input><u>"+rs.getString(1)+"</u></DIV></td>";
					m_string=m_string+"<td width='15%' style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\" ><DIV class=div_input><u>"+rs.getString(2)+"</u></DIV></td>";
					m_string=m_string+"<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><DIV class=div_input><u>"+rs.getString(3)+"</u></DIV></td>";   
					m_string=m_string+"<td width='10%' align='right' ><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>";
					
					m_string=m_string+"<td width='10%' align='center'><select class=txt_input type=text name=TXT_APPROVE_TYPE_"+chk_nums+" maxlength=1 size=1  onChange=\"\"  >";  
					m_string=m_string+"<option value=\"A\" selected>Approve</option>";
					m_string=m_string+"<option value=\"D\"  >Disapprove</option>";
					m_string=m_string+"</select></td>";
					
					m_string=m_string+"<td width='15%' ><input type='text' class='txt_input' maxlength=200 style='width:150px' name='comment_"+chk_nums+"' value=\"-\" ></td>";
					
					m_string=m_string+"<td width='6%' align='center' ><input type='checkbox' name='received_"+chk_nums+"' onclick=\"\" ></td>"; 
					
					//m_string=m_string+"<td width='10%' style=\"text-align: center;\"><input class=\"but_input\" type=\"button\" name=\"BUT_DETAILS_"+chk_nums+"\" id=\"BUT_DETAILS_"+chk_nums+"\" value=\"Details\" onclick=\"show_details('" + chk_nums + "');\" /></td>";
					
					m_string=m_string+"</tr>";
					
					
					
					
				} 
				
				
				rs.close();
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				out.println(m_string);
				
				
			}
			out.flush();
		}
		catch (Exception ex) {
			ex.printStackTrace();
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
