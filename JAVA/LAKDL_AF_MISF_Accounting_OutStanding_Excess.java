//created by nuwan de silva on 13-11-2007----------------------------

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Accounting_OutStanding_Excess extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	CallableStatement callstmt1 =null;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username=m_sn_methods.username;
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
				
				
			if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Accounting - Arrears For Three Months Or More Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			/*out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");*/
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");
			 
			//To validate from date & to date
			out.println("function validate_date(){");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
      out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
      out.println("			else {");
			out.println("   		alert(' Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");

			
			
			
			
			out.println("}");
			
	    
			out.println("function run_report() {");
			out.println("	if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
			out.println(" 	m_cap_funds = unformat_noobject(document.Form1.TXT_CAP_FUNDS.value) ");
			out.println("		m_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
		  out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Accounting_OutStanding_Excess?chksql=run_report&m_date=\"+m_date+\"&cap_funds=\"+m_cap_funds+\"&sort_by=ASC\";");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=40,top=200,width=1000,height=400,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			//out.println("   set_timer_actions();");
			out.println("}");
			out.println("}");
			
			
			out.println("function makeRequest_detail() {");
			out.println(" run_report();");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println(" 	m_cap_funds = unformat_noobject(document.Form1.TXT_CAP_FUNDS.value) ");
			//out.println(" 	alert(m_cap_funds)");
			out.println("		if(document.Form1.TXT_CAP_FUNDS.value != '') {");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Accounting_OutStanding_Excess?chksql=outstanding_excess&m_date=\"+m_to_date+\"&cap_funds=\"+m_cap_funds+\"&order_by=ENT_DATE&sort_by=ASC\";");
			//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=40,top=200,width=1000,height=400,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("   }else{");
			out.println(" 	alert('Capital cannt be null.');");
			out.println("   }");
			
			out.println("}");

			
			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	return true;"); 
			out.println("}"); 			

			out.println("function before_submit(){ "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Accounting_OutStanding_Excess?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Accounting_OutStanding_Excess?chksql=main_page';"); 
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
			out.println("	help_box.innerHTML=\" Accounting - Accommodations Granted And Outstanding In Excess Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Accounting - Accommodations Granted And Outstanding In Excess Report \";"); 
			out.println("}"); 
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=chksql_get_sysdate\";");
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
			
			out.println("function help_update() {"); 
			out.println(" document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_facility() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_REPORT_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_1() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_3() {"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_assign_1();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_assign_2();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_update_value_assign_3();"); 
	  	out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("	 	}"); 
			out.println("	}"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Accounting - Accommodations Granted And Outstanding In Excess Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");
			
			out.println("<table class='table' width='100%'  >"); 
			out.println("</table>");
			out.println("<BR><BR>");			
			out.println("<table class='table' width='100%'  >"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>Date as at *</b></DIV></td>"); 
			out.println(" <TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"makeRequest_detail()\">");	
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
			out.println("<tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CAP_FUNDS'  class=div_input>Capital Funds Of</b></DIV></td>"); 
			out.println(" <TD WIDTH=\"20%\" ><input class=\"txt_input\" style='width:150;text-align:right;' type=\"text\" name=TXT_CAP_FUNDS maxlength=\"15\" size=\"15\" ONCHANGE = \"format_num(document.Form1.TXT_CAP_FUNDS,4)\"></Td>");
			out.println("<td width='*%' ></td>");
			out.println("<tr >"); 
			
			out.println("</table>");  
			out.println("<br>"); 
			out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
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
			
			
			
			else if (m_chksql.trim().equals("run_report")) {
						
				String m_date=req.getParameter("m_date");
				String m_cap_funds=req.getParameter("cap_funds");
				String m_user_name=m_sn_methods.username;
				//m_username = "OFSCLALL";
						
				
				try{				
				
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_OUTSTAND_EXCESS(:1,:2,:3);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_cap_funds);
				callstmt1.setString(3,m_user_name);
				callstmt1.execute();
				out.println(" Processing...");

				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}
			
			
			
			
			
			
			}
			else if (m_chksql.trim().equals("outstanding_excess")) {
			
			String m_date       = req.getParameter("m_date"); 
			String m_cap_funds  = req.getParameter("cap_funds");
			      		
			 
				String Comp_Name = " SELECT COMPANY_NAME, "+
				       " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD')||' '|| "+
							 " INITCAP(TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
							 " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'YYYY') "+
							 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
      rs1 = stmt1.executeQuery(Comp_Name);
			
			boolean more1 = rs1.next();			
							
			  out.println("<HTML>"); 
			  out.println("<HEAD>"); 
			  out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			  out.println("</HEAD>"); 
			  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
			
			  out.println("function get_detail(val){");
				//out.println("alert('qqqqqqqqq=='+val);"); 
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=Load_Details&State=\"+val+\"&m_date="+m_date+" \";");
			  //out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
		  	out.println("    popupwin=window.open(m_url,'displayWindow2','left=40,top=300,width=1000,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				
				out.println("}"); 
							
			
			
			  out.println("</script>"); 
			  out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='1' width='100%' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASING ACT NO 56 OF 2000");
				out.println("<BR><b>FINANCE LEASING (SINGLE BORROWER LIMIT) DIRECTION NO 3 OF 2006</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>ACCOMODATIONS GRANTED AND OUTSTANDING IN EXCESS OF 10% OF CAPITAL FUNDS</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Format No</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >SNBFI/FL/03/02</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Name of RFLE</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' ><b>"+rs1.getString(1)+"</b></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >As at Month Ended</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >"+rs1.getString(2)+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				String m_month_end = rs1.getString(2);
					
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor='black' >");
				out.println("<tr>");
				out.println("<td width=\"18%\"><b>Name of the Borrower </td>");
				out.println("<td colspan=\"4\"><b>Particulars of Advances </td>");
				out.println("<td width=\"17%\" rowspan=\"2\"><b>Total Amount Outstanding</td>");
				out.println("<td width=\"9%\" rowspan=\"2\"><b>as a % of the Capitalfund </td>");
				out.println("<td width=\"12%\" rowspan=\"2\"><b>Type &amp; value of security </td>");
				out.println("<td width=\"12%\" rowspan=\"2\"><b>Out standing of advances granted to connected parties </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td></td>");
				out.println("<td width=\"8%\"><p><b>Contact No</p>");
				out.println("<p></p></td>");
				out.println("<td width=\"8%\"><p><b>Type of Advance</p>");
				out.println("<p></p></td>");
				out.println("<td width=\"8%\"><p><b>Amount Granted-Rs</p>");
				out.println("<p></p></td>");
				out.println("<td width=\"8%\"><p><b>Amount outstanding</p>");
				out.println("<p></p></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input1>");
				out.println("<td><B>Individual Borrowers &nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
						 
    // COMMENT BY NUWAN DE SILVA ON 05-08-2008------------------------------------------------------------
   /*  rs = stmt.executeQuery(" SELECT client_code,ROUND(AMT_GRANT,2),AF_CO_GET_CLIENT_NAME(client_code) "+
			   " FROM( "+
					" SELECT DISTINCT C.client_code,AF_CO_GET_CLIENT_NAME(C.client_code), C.finance_no, "+
					" SUM((VAT_PERCENTAGE-VAT_APP)/100*NET_AMOUNT + NET_AMOUNT) AMT_GRANT "+
					" FROM "+
					" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
					" "+m_schema_name+".af_co_pro_application_details C "+
					" WHERE A.application_no = B.application_no AND "+
					" A.invoice_no = B.pro_invoice_no AND "+
					" A.application_no = C.application_no AND "+
					" C.CLIENT_CODE NOT IN ( SELECT MEMBER_ID FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP ) AND "+
					" C.application_status = 'ACTIVATED' AND "+
					" c.activated_date < TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" GROUP BY C.client_code,AF_CO_GET_CLIENT_NAME(C.client_code)) "+
					" WHERE (AMT_GRANT/"+m_cap_funds+")*100 >10 "); 

		*/		
		
					rs = stmt.executeQuery(" SELECT client_code,ROUND(AMT_GRANT,2),"+m_schema_name+".AF_CO_GET_CLIENT_NAME(client_code)  "+
					" FROM(  "+
					" SELECT DISTINCT C.client_code,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.client_code),  "+
					" SUM(A.capital_amount) AMT_GRANT "+ //core capital
					" FROM "+m_schema_name+".af_co_pro_application_details C   , "+m_schema_name+".af_co_pro_app_installment A "+
					" WHERE C.application_no=A.application_no AND "+
					" C.CLIENT_CODE NOT IN ( SELECT MEMBER_ID FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP ) AND  "+
					" C.application_status = 'ACTIVATED' AND  "+
					" c.activated_date < TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
					" GROUP BY C.client_code,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.client_code))  "+
					" WHERE (AMT_GRANT/"+m_cap_funds+")*100 >10  ");
		
				boolean client_more = rs.next();
				String m_client = "";
				int k = 1;
				
				while(client_more){
			 
			 m_client = rs.getString(1);
			 rs1 = stmt1.executeQuery(" SELECT FINANCE_NO, "+
				                      " TRANSACTION_DESC, "+
															" CAP_AMOUNT, "+
 														  " CAP_OUTSTANDING, "+
															" EXCESS, "+
															" CLIENT_NAME, "+
															" CLIENT_CODE "+
															" FROM "+
															" ( SELECT FINANCE_NO, "+
															" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE) TRANSACTION_DESC, "+
															" "+m_schema_name+".AF_CO_GET_APP_CAP_AMOUNT(APPLICATION_NO) CAP_AMOUNT, "+
															//" "+m_schema_name+".AF_CO_GET_CAP_OUTSTND_TODATE(APPLICATION_NO,finance_no,CLIENT_CODE,'"+m_date+"') CAP_OUTSTANDING, "+
															" (NVL("+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTAN_2(A.APPLICATION_NO,'"+m_date+"'),0) + NVL("+m_schema_name+".af_co_get_arr_cap_outstanding(A.finance_no,'"+m_date+"'),0)) CAP_OUTSTANDING, "+
															" 0 EXCESS, "+
															" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME, "+
															" CLIENT_CODE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
															" WHERE CLIENT_CODE NOT IN ( SELECT MEMBER_ID "+
															" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
															" ) "+
															" AND FINANCE_NO IS NOT NULL "+
															" and application_status = 'ACTIVATED' "+
															" ) "+
															" WHERE CLIENT_CODE = '"+m_client+"' "+
															" GROUP BY CLIENT_CODE,FINANCE_NO,TRANSACTION_DESC,CAP_AMOUNT, "+
															" CAP_OUTSTANDING,EXCESS,CLIENT_NAME ");
				
				boolean ind_more = rs1.next();
				
				String borrwer_code = "";
				double m_tot_outstand = 0.00;
				
				
				while(ind_more){	
				if(borrwer_code.equals(rs1.getString(7))){
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>"+rs1.getString(1)+"&nbsp;</td>");
				out.println("<td>"+rs1.getString(2)+"&nbsp;</td>");
				out.println("<td align='right' >"+nf.format(rs1.getDouble(3))+"&nbsp;</td>");
				out.println("<td align='right' >"+nf.format(rs1.getDouble(4))+"&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align='center'>Lease Asset &nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				m_tot_outstand += rs1.getDouble(4); 
				}else{
			  out.println("<tr>");
				out.println("<td>"+k+"]&nbsp"+rs1.getString(6)+"&nbsp;</td>");
				out.println("<td>"+rs1.getString(1)+"&nbsp;</td>");
				out.println("<td>"+rs1.getString(2)+"&nbsp;</td>");
				out.println("<td align='right' >"+nf.format(rs1.getDouble(3))+"&nbsp;</td>");
				out.println("<td align='right' >"+nf.format(rs1.getDouble(4))+"&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align='center'>Lease Asset&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>"); 
				m_tot_outstand += rs1.getDouble(4);
				borrwer_code =rs1.getString(7);
     		}
        
				ind_more = rs1.next();
				}
				
		    
		/*		rs2 = stmt2.executeQuery(" SELECT client_code,ROUND(AMT_GRANT,2) ,ROUND((AMT_GRANT/"+m_cap_funds+")*100,2) "+
				      "FROM( "+
							"SELECT DISTINCT C.client_code,AF_CO_GET_CLIENT_NAME(C.client_code), C.finance_no, "+
							"SUM((VAT_PERCENTAGE-VAT_APP)/100*NET_AMOUNT + NET_AMOUNT) AMT_GRANT "+
							"FROM "+
							""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
							""+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
							""+m_schema_name+".af_co_pro_application_details C "+
							"WHERE A.application_no = B.application_no AND "+
							"A.invoice_no = B.pro_invoice_no AND "+
							"A.application_no = C.application_no AND "+
							"C.CLIENT_CODE NOT IN ( SELECT MEMBER_ID FROM LAKDL.AF_CO_MAS_CLIENT_GROUP ) AND "+
							"C.application_status = 'ACTIVATED' AND "+
							"c.activated_date < TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							"GROUP BY C.client_code,AF_CO_GET_CLIENT_NAME(C.client_code)) "+
							"WHERE  client_code = '"+m_client+"' ");
			*/
			
																		

              //comment by nuwan de silva on 05-08-2008-------------------------------------------
							/*							rs2 = stmt2.executeQuery(" SELECT client_code,ROUND(AMT_GRANT,2) ,ROUND((AMT_GRANT/"+m_cap_funds+")*100,2) "+
							"FROM( "+
							"SELECT DISTINCT C.client_code,AF_CO_GET_CLIENT_NAME(C.client_code), C.finance_no, "+
							"SUM((VAT_PERCENTAGE-VAT_APP)/100*NET_AMOUNT + NET_AMOUNT) AMT_GRANT "+
							"FROM "+
							""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
							""+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
							""+m_schema_name+".af_co_pro_application_details C "+
							"WHERE A.application_no = B.application_no AND "+
							"A.invoice_no = B.pro_invoice_no AND "+
							"A.application_no = C.application_no AND "+
							"C.CLIENT_CODE NOT IN ( SELECT MEMBER_ID FROM LAKDL.AF_CO_MAS_CLIENT_GROUP ) AND "+
							"C.application_status = 'ACTIVATED' AND "+
							"c.activated_date < TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							"GROUP BY C.client_code,AF_CO_GET_CLIENT_NAME(C.client_code)) "+
							"WHERE  client_code = '"+m_client+"' ");
							*/

							rs2 = stmt2.executeQuery(" SELECT client_code,ROUND(AMT_GRANT,2) ,ROUND((AMT_GRANT/"+m_cap_funds+")*100,2) "+
				      " FROM( "+
							" SELECT DISTINCT C.client_code,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.client_code), "+
							" SUM(NVL("+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTAN_2(C.application_no,'"+m_date+"'),0) + NVL("+m_schema_name+".af_co_get_arr_cap_outstanding(C.finance_no,'"+m_date+"'),0)) AMT_GRANT "+
							//" SUM(A.capital_amount) AMT_GRANT "+
							" FROM "+
							" "+m_schema_name+".af_co_pro_application_details C /*, "+m_schema_name+".af_co_pro_app_installment A */"+
							//" WHERE C.application_no=A.application_no AND "+
							" WHERE C.CLIENT_CODE NOT IN ( SELECT MEMBER_ID FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP ) AND "+
							" C.application_status = 'ACTIVATED' AND "+
							" c.activated_date < TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" GROUP BY C.client_code,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.client_code)  ) "+
							" WHERE  client_code = '"+m_client+"' ");
							
				
				boolean ind_percent = rs2.next();
				double m_percent = 0.00;
				if(ind_percent){
				  m_percent = rs2.getDouble(3);
				}
				
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align='right' >"+nf.format(m_tot_outstand)+"&nbsp;</td>");
				out.println("<td align='right'>"+nf.format(m_percent)+"&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				k +=1;
				client_more = rs.next();
				}
				
				
				
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input1>");
				out.println("<td><B>Group of Borrowers &nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
								
				String group_id    ="";
				String master_name ="";
				String member_id   ="";
				String member_name ="";
				String member_code ="";
				int i = 1;
				int j = 1;
				
				
				rs = stmt.executeQuery( " SELECT GROUP_ID, "+
					                       " "+m_schema_name+".af_co_get_client_name(GROUP_MASTER_ID), "+
																 " "+m_schema_name+".af_co_get_client_name(MEMBER_ID) "+
																 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
																 " WHERE CLIENT_TYPE = 'M' AND "+
																 " GROUP_MASTER_ID <>'0000010800'  /*'OFSCL'*/ ");
																	
				boolean group_more = rs.next();
				
				while(group_more){
				
				group_id = rs.getString(1);
				master_name= rs.getString(2);
				
				/*
        rs1 = stmt1.executeQuery("SELECT CLIENT_CODE,CLIENT_NAME "+
				       " FROM ( SELECT CLIENT_CODE,EXCESS,CLIENT_NAME "+
							 " FROM "+
							 " ( SELECT FINANCE_NO, "+
							 " DECODE("+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTANDING(APPLICATION_NO),0,0, ROUND(("+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTANDING(APPLICATION_NO)/"+m_schema_name+".AF_CO_GET_APP_CAP_AMOUNT(APPLICATION_NO)  )*100,1)) EXCESS, "+
							 " "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME, "+
							 " CLIENT_CODE "+
							 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
							 " WHERE CLIENT_CODE IN ( SELECT MEMBER_ID "+
							 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
							 " WHERE GROUP_ID = '"+group_id+"') "+
							 " AND FINANCE_NO IS NOT NULL "+
							 " ) WHERE EXCESS >= 10 ) "+
							 " GROUP BY CLIENT_CODE,CLIENT_NAME "); */
								
								
								
				 //added by nuwan de silva on 05-08-2008----------------------
					/*rs1 = stmt1.executeQuery(" SELECT CLIENT_CODE,CLIENT_NAME "+
				      " FROM ( SELECT CLIENT_CODE,CAP_OUTSTND,CLIENT_NAME "+
							" FROM "+
							" (SELECT SUM("+m_schema_name+".AF_CO_GET_CAP_OUTSTND_TODATE(APPLICATION_NO,finance_no,CLIENT_CODE,'"+m_date+"')) CAP_OUTSTND, "+
							" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME, "+
							" CLIENT_CODE "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
							" WHERE CLIENT_CODE IN ( SELECT MEMBER_ID "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
							" WHERE GROUP_ID = '"+group_id+"') "+
							" AND FINANCE_NO IS NOT NULL "+
							" GROUP BY CLIENT_CODE,af_co_get_client_name(CLIENT_CODE)) "+
							" WHERE (CAP_OUTSTND/"+m_cap_funds+")*100 >= 10 ) "+
							" GROUP BY CLIENT_CODE,CLIENT_NAME ");
					*/
										
					    rs1 = stmt1.executeQuery(" SELECT CLIENT_CODE,CLIENT_NAME "+
				      " FROM ( SELECT CLIENT_CODE,CAP_OUTSTND,CLIENT_NAME "+
							" FROM "+
							" ( SELECT "+
							//"  SUM("+m_schema_name+".AF_CO_GET_CAP_OUTSTND_TODATE(APPLICATION_NO,finance_no,CLIENT_CODE,'"+m_date+"')) CAP_OUTSTND, "+
							//" SUM(NVL("+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTAN_2(finance_no,'"+m_date+"'),0) + NVL("+m_schema_name+".af_co_get_arr_cap_outstanding(finance_no,'"+m_date+"'),0)) CAP_OUTSTND ,"+
							" SUM(A.capital_amount) CAP_OUTSTND, "+
							" "+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE) CLIENT_NAME, "+
							" CLIENT_CODE "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C , "+m_schema_name+".af_co_pro_app_installment A "+
							" WHERE C.application_no=A.application_no AND  C.CLIENT_CODE IN ( SELECT MEMBER_ID "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
							" WHERE GROUP_ID = '"+group_id+"') "+
							" AND FINANCE_NO IS NOT NULL "+
							" GROUP BY C.CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE)) "+
							" WHERE (CAP_OUTSTND/"+m_cap_funds+")*100 >= 10 ) "+
							" GROUP BY CLIENT_CODE,CLIENT_NAME ");
					
								


			
				boolean member_more = rs1.next();
				
				double m_tot_outstand = 0.00;
				int m=0;
				
				if(member_more){
				while(member_more){
				
				m_tot_outstand = 0.00;
				member_id = rs1.getString(1);
				member_name=rs1.getString(2);
				
			//COMMENT BY NUWAN DE SILVA ON 08-05-2008----------------	
			/*rs2 = stmt2.executeQuery( " SELECT DISTINCT FINANCE_NO, "+
				                        " TRANSACTION_DESC, "+
																" CAP_AMOUNT, "+
																" CAP_OUTSTANDING, "+
																" EXCESS, "+
																" CLIENT_NAME, "+
															  " CLIENT_CODE "+
																" FROM "+
																" ( SELECT FINANCE_NO, "+
																" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE) TRANSACTION_DESC, "+
																" "+m_schema_name+".AF_CO_GET_APP_CAP_AMOUNT(APPLICATION_NO) CAP_AMOUNT, "+
																" "+m_schema_name+".AF_CO_GET_CAP_OUTSTND_TODATE(APPLICATION_NO,finance_no,CLIENT_CODE,'"+m_date+"') CAP_OUTSTANDING, "+
																" 0.00 EXCESS, "+
																" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME, "+
																" CLIENT_CODE "+
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																" "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP B "+
																" WHERE CLIENT_CODE = '"+member_id+"' "+
																" AND FINANCE_NO IS NOT NULL "+
																" AND A.CLIENT_CODE = B.MEMBER_ID "+
																" ) "); 
			 */
				
							rs2 = stmt2.executeQuery( " SELECT DISTINCT FINANCE_NO, "+
				                        " TRANSACTION_DESC, "+
																" CAP_AMOUNT, "+
																" CAP_OUTSTANDING, "+
																" EXCESS, "+
																" CLIENT_NAME, "+
															  " CLIENT_CODE "+
																" FROM "+
																" ( SELECT FINANCE_NO, "+
																" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE) TRANSACTION_DESC, "+
																" "+m_schema_name+".AF_CO_GET_APP_CAP_AMOUNT(APPLICATION_NO) CAP_AMOUNT, "+
																//" "+m_schema_name+".AF_CO_GET_CAP_OUTSTND_TODATE(APPLICATION_NO,finance_no,CLIENT_CODE,'"+m_date+"') CAP_OUTSTANDING, "+
																" (NVL("+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTAN_2(A.APPLICATION_NO,'"+m_date+"'),0) + NVL("+m_schema_name+".af_co_get_arr_cap_outstanding(A.finance_no,'"+m_date+"'),0)) CAP_OUTSTANDING ,"+
																" 0.00 EXCESS, "+
																" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME, "+
																" CLIENT_CODE "+
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																" "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP B "+
																" WHERE CLIENT_CODE = '"+member_id+"' "+
																" AND FINANCE_NO IS NOT NULL "+
																" AND A.CLIENT_CODE = B.MEMBER_ID "+
																" ) "); 

			
																	
				
				boolean member_det_more = rs2.next();
				int n=0;
				while(member_det_more){
								
				if(n==0){
				out.println("<tr>");
				if(m==0){
				out.println("<td>&nbsp;"+j+"]&nbsp;&nbsp;"+member_name+"&nbsp;</td>");
				}else{
				out.println("<td>&nbsp;"+j+"."+m+"]&nbsp;&nbsp;"+member_name+"&nbsp;</td>");
				}
				n+=1;
				}else{
				out.println("<td>&nbsp;</td>");
				}
				out.println("<td>"+rs2.getString(1)+"&nbsp;</td>");
				out.println("<td>"+rs2.getString(2)+"&nbsp;</td>");
				out.println("<td align='right'>"+nf.format(rs2.getDouble(3))+"&nbsp;</td>");
				out.println("<td align='right'>"+nf.format(rs2.getDouble(4))+"&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align='center'>Lease Assets&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
							
				m_tot_outstand += rs2.getDouble(4);
							
				member_det_more = rs2.next();									
				}
			  				
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align='right'>&nbsp;</td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td align='right'>"+nf.format(m_tot_outstand)+"&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
							
			  member_more = rs1.next();
				m +=1;
				}
				
				}else{				
			  out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan=\"7\">&nbsp; Accommodations granted to the Group of Borrowers - outstanding value is not exceeding 10% of Capital Fund as at "+m_month_end+"</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				}
				
				
				
				
				j += 1;
				group_more = rs.next();
				}
				
							
				out.println("<tr class=tr_input1>");
				out.println("<td><B>Directors, holding company or subsidiary companies &nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				rs = stmt.executeQuery( " SELECT GROUP_ID,GROUP_MASTER_ID, "+
				     " "+m_schema_name+".af_co_get_client_name(GROUP_MASTER_ID), "+
						 " MEMBER_ID, "+
						 " NVL("+m_schema_name+".af_co_get_client_name(MEMBER_ID),'0000010800') "+ ///*'OFSCL'*/
						 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
						 " WHERE GROUP_MASTER_ID ='0000010800' /*'OFSCL'*/ ");
				
				boolean hold_com_more = rs.next();
				String _group_id ="";
				String _master_id ="";
				String _member_id ="";
				String _member_name ="";
				int h=1; 
				
				if(hold_com_more){
				while(hold_com_more){
				
				_group_id = rs.getString(1);
				_master_id = rs.getString(2);
				_member_id = rs.getString(4); 
				_member_name = rs.getString(5); 
				
						
    /*  rs1 = stmt1.executeQuery(" SELECT FINANCE_NO, "+
				                      " TRANSACTION_DESC, "+
															" CAP_AMOUNT, "+
 														  " CAP_OUTSTANDING, "+
															" EXCESS, "+
															" CLIENT_NAME, "+
															" CLIENT_CODE "+
															" FROM "+
															" ( SELECT FINANCE_NO, "+
															" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE) TRANSACTION_DESC, "+
															" "+m_schema_name+".AF_CO_GET_APP_CAP_AMOUNT(APPLICATION_NO) CAP_AMOUNT, "+
															" "+m_schema_name+".AF_CO_GET_CAP_OUTSTND_TODATE(APPLICATION_NO,finance_no,CLIENT_CODE,'"+m_date+"') CAP_OUTSTANDING, "+
															" 0.00 EXCESS, "+
															" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME, "+
															" CLIENT_CODE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
															" WHERE CLIENT_CODE IN ( SELECT MEMBER_ID "+
															" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
															" ) "+
															" AND FINANCE_NO IS NOT NULL "+
															" ) "+
															" WHERE (CAP_OUTSTANDING/"+m_cap_funds+")*100 >= 10 AND "+
															" CLIENT_CODE = '"+_member_id+"' "+
															" GROUP BY CLIENT_CODE,FINANCE_NO,TRANSACTION_DESC,CAP_AMOUNT, "+
															" CAP_OUTSTANDING,EXCESS,CLIENT_NAME ");
																	
				*/
				
				
				      rs1 = stmt1.executeQuery(" SELECT FINANCE_NO, "+
				                      " TRANSACTION_DESC, "+
															" CAP_AMOUNT, "+
 														  " CAP_OUTSTANDING, "+
															" EXCESS, "+
															" CLIENT_NAME, "+
															" CLIENT_CODE "+
															" FROM "+
															" ( SELECT FINANCE_NO, "+
															" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE) TRANSACTION_DESC, "+
															" "+m_schema_name+".AF_CO_GET_APP_CAP_AMOUNT(APPLICATION_NO) CAP_AMOUNT, "+
															//" "+m_schema_name+".AF_CO_GET_CAP_OUTSTND_TODATE(APPLICATION_NO,finance_no,CLIENT_CODE,'"+m_date+"') CAP_OUTSTANDING, "+
															" (NVL("+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTAN_2(A.APPLICATION_NO,'"+m_date+"'),0) + NVL("+m_schema_name+".af_co_get_arr_cap_outstanding(A.finance_no,'"+m_date+"'),0)) CAP_OUTSTANDING ,"+
															" 0.00 EXCESS, "+
															" "+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME, "+
															" CLIENT_CODE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
															" WHERE CLIENT_CODE IN ( SELECT MEMBER_ID "+
															" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
															" ) "+
															" AND FINANCE_NO IS NOT NULL "+
															" ) "+
															" WHERE (CAP_OUTSTANDING/"+m_cap_funds+")*100 >= 10 AND "+
															" CLIENT_CODE = '"+_member_id+"' "+
															" GROUP BY CLIENT_CODE,FINANCE_NO,TRANSACTION_DESC,CAP_AMOUNT, "+
															" CAP_OUTSTANDING,EXCESS,CLIENT_NAME ");
																	


				boolean member_det_more = rs1.next();
				int s=0;
				double tot_outstand =0.00;
				
				
				while(member_det_more){				
				out.println("<tr>");
				if(s==0){
				out.println("<td>"+h+"]&nbsp;"+_member_name+"&nbsp;</td>");
				s+=1;
				h+=1;
				}else{
				out.println("<td>&nbsp;</td>");
				}
				out.println("<td>"+rs1.getString(1)+"&nbsp;</td>");
				out.println("<td>"+rs1.getString(2)+"&nbsp;</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(3))+"&nbsp;</td>");
				out.println("<td align='right'>"+nf.format(rs1.getDouble(4))+"&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align='center'>Lease Assets&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				tot_outstand +=rs1.getDouble(4);
				
				member_det_more = rs1.next();
				}
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align='right'>&nbsp;</td>");
				out.println("<td align='right'>&nbsp;</td>");
				out.println("<td align='right'>"+nf.format(tot_outstand)+"&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				
				hold_com_more = rs.next();
				}
				}else{
				
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan=\"7\">&nbsp; Accommodations granted to the holding company - outstanding value is not exceeding 10% of Capital Fund as at "+m_month_end+"</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				}
				
				out.println("</table>");

							
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");

				
					
			}

			
			
						
			
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
