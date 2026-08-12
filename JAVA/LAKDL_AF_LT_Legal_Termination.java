// DEVELOP BY : DISNAKA FOR OFSCL FACTORING    DATE:2011-09-14

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_LT_Legal_Termination extends javax.servlet.http.HttpServlet { 
	
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
				out.println("<TITLE>Operation Reports - Legal Termination Settlement</TITLE>"); 
				
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
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
				
				
				
				
				out.println("function validate_data(){");

				out.println("	var count = parseInt(document.Form1.elements[\"NUM_CHKS\"].value) ");
				out.println("   for (var i = 1; i <= count ; i++) {"); 
				
				out.println("		if (document.Form1.elements[\"received_\"+i].checked == false) {");
				out.println("	        alert(\"Select all invoices before save \"); ");
				out.println("	        return false; ");
				out.println("		}");
				out.println("   }");
				
				
				out.println("	return true;"); 
				out.println("}"); 			
				
				
				
				out.println("function before_submit(){ "); 
				//out.prin
				
				out.println("	get_display_msg();");
				out.println("	if(validate_data()){"); 
				out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
				out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("				document.Form1.elements[i].disabled=false;");
				out.println("			}");
				
				out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination_save';");  
				out.println("				document.Form1.submit();	"); 
				
				out.println("		}"); 
				out.println("	}"); 
				out.println("} "); 
				
				
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				
				
				
				
				
				
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_LT_Legal_Termination';"); 
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
				out.println("	help_box.innerHTML=\" Operation Reports - Legal Termination Settlement - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Operation Reports - Legal Termination Settlement \";"); 
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
				out.println(" m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@LEGAL@\";"); 
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
				
				out.println("function viewInvoice() {");
				
				out.println(" if(document.Form1.TXT_FINANCE_NO.value!=\"\"){"); 
				out.println("      makeRequestInvDet();");
				out.println(" }");
				
				out.println("}");
				
				
				out.println(" function makeRequestInvDet() {");
				//out.println("    	m_url = '" + m_class_url + "/" + m_fschema_name + "AF_LT_Legal_Termination?chksql=LEGAL_INV_DET&financeNo="'+document.Form1.TXT_FINANCE_NO.value ;"); 
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_LT_sql_validations_normal?chksql=LEGAL_INV_DET&financeNo=\"+document.Form1.TXT_FINANCE_NO.value;");
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
				
				
				
				out.println("</script>"); 
				
				
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' onload=\"load_lock();\">");//request_reports()
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Reports - Legal Termination Settlement</td>"); 
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
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number *</DIV></td>"); 
				out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"\" >"); //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\"
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"...\" onClick=\"help_button_finance_no()\"></td>"); 
				out.println("<td width='20%'><input class='but_input' type='button' name='BUT_TXT_VIEW' value=\"View\" onClick=\"viewInvoice()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				
				out.println("<br>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='report_data'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
			
				
				//out.println("<DIV id='report_data'  class=div_input></DIV>");
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
			
			else if (m_chksql.trim().equals("LEGAL_INV_DET")){
				
				String m_finance_no = req.getParameter("financeNo");
				String m_string        = "";
				double m_bal_total     = 0.00 ;
				rs= stmt.executeQuery(""+
				//out.println(""+
					" SELECT "+
					" 	A.INVOICE_NO, "+
					" 	"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(A.INVOICE_TYPE) , "+   
					" 	A.TOTAL_AMOUNT, "+
					" 	A.SETTELE_AMOUNT,"+
					" 	A.BALANCE_TO_BE_RECEIVED, "+
					" 	A.CLIENT_CODE, "+
					" 	A.ACTIVE_STATUS,"+
					" 	A.INVOICE_TYPE, "+
					" 	A.FINANCE_NO ,"+
					" 	A.SETTLED_STATUS "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A WHERE FINANCE_NO = '"+m_finance_no+"'  AND ACTIVE_STATUS = 'Y' "+ 
					" AND INVOICE_TYPE IN ('LEGAL_ARR','LEGAL_CAP','LEGAL_ODI','LEGAL_OTHER') AND BALANCE_TO_BE_RECEIVED > 0 "+
					" AND INVOICE_NO NOT IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_LT_FIN_INVOICE_DET ) ");
				
				
				
				
				m_string=m_string+"<table align='left' width='1000px' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Description</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Total Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Settle Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Balanace Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Wave off Amount</b></DIV></td>";
				m_string=m_string+"<td width='6%'  align='center' ><DIV class=div_input><b>Select</b></DIV></td>";
				
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
					
					m_string=m_string+"<td width='15%' > <input type='hidden' name='invNo_"+chk_nums+"'       value=\""+rs.getString(1)+"\" > <DIV class=div_input>"+rs.getString(1)+"</DIV></td>";
					m_string=m_string+"<td width='15%' > <input type='hidden' name='invType_"+chk_nums+"'     value=\""+rs.getString(8)+"\" > <DIV class=div_input>"+rs.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='hidden' class='txt_input' name='totAmt_"+chk_nums+"' value=\""+rs.getDouble(3)+"\"><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='hidden' class='txt_input' name='setAmt_"+chk_nums+"' value=\""+rs.getDouble(4)+"\"><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='hidden' class='txt_input' name='balAmtId_"+chk_nums+"' value=\""+rs.getDouble(5)+"\">"+nf.format(rs.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='text' style='text-align:right' class='txt_input' name='waveAmtId_"+chk_nums+"' value=\""+nf.format(rs.getDouble(5))+"\" disabled  ></td>"; //format_num(document.Form1.waveAmtId_"+chk_nums+",4);check_value("+chk_nums+")
					m_string=m_string+"<td width='6%' align='center' ><input type='checkbox' name='received_"+chk_nums+"' onclick=\"calWaveVal()\" ></td>"; 
					
					m_string=m_string+"</tr>";
					
					m_bal_total = m_bal_total + rs.getDouble(5);
					
					
				} 
				
				
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input></DIV></td>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Total</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_bal_total)+"</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='right' ><DIV id='div_wave_amt' class=div_input><b>"+nf.format(0)+"</b></DIV></td>";
				m_string=m_string+"<td width='6%'  align='center' ><DIV class=div_input></DIV></td>";
				
				m_string=m_string+"</tr>";
				
				
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
