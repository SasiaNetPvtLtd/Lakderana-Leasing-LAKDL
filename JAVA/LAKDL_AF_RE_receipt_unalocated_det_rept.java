

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_receipt_unalocated_det_rept extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
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
			out.println("<TITLE>Collection - Receipt Unallocated Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");
			 
			//To validate from date & to date
			out.println("function validate_date(){");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
			out.println("    if(!checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
			out.println("     return false;"); 
			out.println("     }");
			out.println("    else {");
			out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
			out.println("			else {");
			out.println("   		alert('To Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");
			out.println("    }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From Date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			out.println(" }");
			
			out.println("function makeRequest_detail() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_DATE&sort_by=ASC\";");
			//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("}");
			
			out.println("function makeRequest_not_allocated_to_contract() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=not_allocated_to_contracts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_DATE&sort_by=ASC\";");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("}");
						
			out.println("function makeRequest_not_allocated_to_invoices() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=not_allocated_to_invoices&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_DATE&sort_by=ASC\";");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=main_page';"); 
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
			out.println("	help_box.innerHTML=\" Collection - Receipt Unallocated Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Collection - Receipt Unallocated Report \";"); 
			out.println("}"); 
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection - Receipt Unallocated Report </td>"); 
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
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
			out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"01\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"04\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"2007\" >");	
			out.println("</td> ");
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
			out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"makeRequest_detail()\">");	
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");
			//out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"makeRequest_detail()\" style=\"{width:110px};\" >");	
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"To Be Allocated - Contracts\" onClick=\"makeRequest_not_allocated_to_contract()\" style=\"{width:140px};\" >");	
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"To Be Allocated - Invoices\" onClick=\"makeRequest_not_allocated_to_invoices()\" style=\"{width:140px};\" >");	
			
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
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
			
			
			else if(m_chksql.equals("not_allocated_to_contracts")){
			
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
			  String m_order_by = req.getParameter("order_by");
			  String m_sort_by = req.getParameter("sort_by");
										
			if(m_from_date==null){
			m_from_date="";
			}
			if(m_to_date==null){
			m_to_date="";
			}

			if(!m_from_date.equals("") && !m_to_date.equals("")){	
			
					/*rs = stmt.executeQuery (" SELECT "+
					" A.REC_NO, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME  ,"+
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE,  "+
					" B.REC_AMOUNT, "+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
					" OTH_COMMENTS ,"+ 
					" CLIENT_CODE "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL A, "+
					" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE A.REC_NO=B.REC_NO "+
					" AND   EFF_VALDATE >=  TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND   EFF_VALDATE <=  TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" AND   BAL_TOBE_RECEIVE >0 "+
					//" AND   ALLOCATED_AMOUNT =0 
					" AND   B.STATUS NOT IN('C','CAD','RET') "+
					" AND   B.GROUP_REC_NO IS NULL "+
					" AND  A.REC_NO NOT IN "+
					" ( "+
					" SELECT REC_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL "+
					" ) "+
					" ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
          */
					
					rs = stmt.executeQuery (" SELECT "+
					" A.REC_NO, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME  ,"+
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE,  "+
					" BAL_TOBE_RECEIVE /*A.REC_AMOUNT*/, "+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
					" OTH_COMMENTS ,"+ 
					" CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLI_CONTRACT_COUNT(CLIENT_CODE) Con "+  
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					" WHERE A.REC_NO=B.REC_NO AND  EFF_VALDATE >=  TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND   EFF_VALDATE <=  TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" AND   BAL_TOBE_RECEIVE <>0 "+
					//" AND   ALLOCATED_AMOUNT =0 
					" AND BAL_TOBE_RECEIVE>0 AND  A.STATUS NOT IN('C','CAD','RET') "+
					/*" AND  A.REC_NO NOT IN "+
					" ( "+
					" SELECT REC_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL "+
					" ) "+*/
				  " AND A.REC_NO NOT IN ( "+
					" SELECT GROUP_REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE GROUP_REC_NO IS NOT NULL "+
					" ) "+

					
					" ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
			
                      						
			}
			
			if(m_from_date.equals("") || m_to_date.equals("")){	

					/*rs = stmt.executeQuery (" SELECT "+
					" A.REC_NO, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME  ,"+
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE,  "+
					" B.REC_AMOUNT, "+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
					" OTH_COMMENTS ,"+ 
					" CLIENT_CODE "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL A, "+
					" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE A.REC_NO=B.REC_NO "+
					" AND   BAL_TOBE_RECEIVE >0 "+
					//" AND   ALLOCATED_AMOUNT 0 
					" AND   B.STATUS NOT IN('C','CAD','RET') "+
					" AND   B.GROUP_REC_NO IS NULL "+
					" AND  A.REC_NO NOT IN "+
					" ( "+
					" SELECT REC_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL "+
					" ) "+
					" ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
					*/
					
					rs = stmt.executeQuery (" SELECT "+
					" A.REC_NO, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME  ,"+
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE,  "+
					" BAL_TOBE_RECEIVE /*A.REC_AMOUNT*/, "+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
					" OTH_COMMENTS ,"+ 
					" CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLI_CONTRACT_COUNT(CLIENT_CODE) Con "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B  "+
					" WHERE A.REC_NO=B.REC_NO AND BAL_TOBE_RECEIVE>0 AND A.STATUS NOT IN('C','CAD','RET') "+
					/*" AND  A.REC_NO NOT IN "+
					" ( "+
					" SELECT REC_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL "+
					" ) "+*/
					" AND A.REC_NO NOT IN ( "+
					" SELECT GROUP_REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE GROUP_REC_NO IS NOT NULL "+
					" ) "+
					" ORDER BY "+m_order_by+"  "+m_sort_by+" ");	

			}		
						
				
					boolean mflag=true;							
					boolean more = rs.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Unallocated Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("function sort_data(m_sort_col) {");
					 out.println(" m_from_date ='"+m_from_date+"';");	
					 out.println(" m_to_date='"+m_to_date+"';"); 	
		  		 out.println("	 m_order_by_type = 'ASC'; ");  
					 out.println("	 if(m_sort_col=='"+m_order_by+"'){");
					 out.println("	   if('"+m_sort_by+"'=='DESC'){");
					 out.println("	      m_order_by_type = 'ASC'; ");  
					 out.println("    }else{");
					 out.println("       m_order_by_type = 'DESC'; ");
					 out.println("    }");
					 out.println("  }else{");
					 out.println("    m_order_by_type = 'ASC'; ");
					 out.println("  }");
					 //out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					 //out.println(" window.location.href=m_url;"); 
					 out.println("}");
 	
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Receipt Unallocated Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
					
					out.println("<table align='center' border=\"0\" width='100%' class='table'>");  //colspan=13
					
					out.println("<tr></tr>");
		
				  out.println("<tr class=pdn_txtpos2 >");
					out.println("<td width='2%' align='left'>No</td>");
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Receipt  No  '    onclick=sort_data('REC_NO') >Receipt  No</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Client Name  '    onclick=sort_data('CLIENT_CODE') >Client Code</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Settlement Mode  '    onclick=sort_data('SETTLE_MODE') >Settlement Mode</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt Amount '    onclick=sort_data('REC_AMOUNT') >Receipt Amount</td>"); 
					//out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Allocated Amount '    onclick=sort_data('ALLOCATED_AMOUNT') >Allocated Amount</td>"); 
					//out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Balance Amount '    onclick=sort_data('BAL_TOBE_RECEIVE') >Balance Amount</td>"); 
				  out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Effective Value Date '    onclick=sort_data('EFF_VALDATE') >Effective Value Date</td>"); 
					out.println("<td width='5%' style= cursor:hand; title='Click here to sort by - Count  '    onclick=sort_data('Con') >Contracts</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Comments  '    onclick=sort_data('EFF_VALDATE') >Comments</td>"); 
					out.println("</tr>");

					}
					
					int j=1;
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='2%' align='left'>"+j+"</td>");
						out.println("<td width='15%' align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
			      out.println("<td width='15%' align='left' style= cursor:hand; onclick=\"show_client('"+rs.getString(7)+"') \" ><u>"+rs.getString(2)+"</u></td>");
			      out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
					  out.println("<td width='10%' align='left'>"+rs.getDouble(4)+"</td>");
					  out.println("<td width='10%' align='left'>"+rs.getString(5)+"</td>");
					  out.println("<td width='15%' align='left'>"+rs.getString(8)+"</td>");
			      out.println("<td width='15%' align='left'>"+rs.getString(6)+"</td>");
			      out.println("</tr>");
							more = rs.next();
						j = j+1;	
							
					}	
					     
      	 		out.println("</table>");
							
		//added by SH on 26-04-2010 					
						out.println("<BR><BR>");
			
			if(!m_from_date.equals("") && !m_to_date.equals("")){	
					
					rs = stmt.executeQuery ("SELECT "+
					" A.REC_NO, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME  ,"+
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE,  "+
					" BAL_TOBE_RECEIVE /*A.REC_AMOUNT*/, "+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
					" OTH_COMMENTS ,"+ 
					" CLIENT_CODE, "+
					" "+m_schema_name+".AF_CO_GET_CLI_CONTRACT_COUNT(CLIENT_CODE) Con "+  
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					" WHERE A.REC_NO=B.REC_NO AND  EFF_VALDATE >=  TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND   EFF_VALDATE <=  TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" AND   BAL_TOBE_RECEIVE <>0 "+
					//" AND   ALLOCATED_AMOUNT =0 
					" AND BAL_TOBE_RECEIVE>0 AND ( (A.STATUS IN('C','CAD') "+
					" AND   rec_cancel_date >  TO_DATE('"+m_to_date+"','DD-MM-YYYY') ) "+
					" OR (A.STATUS IN('RET') "+
					" AND   REALISED_DATE >  TO_DATE('"+m_to_date+"','DD-MM-YYYY'))) "+
					/*" AND  A.REC_NO NOT IN "+
					" ( "+
					" SELECT REC_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL "+
					" ) "+*/
				  " AND A.REC_NO NOT IN ( "+
					" SELECT GROUP_REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE GROUP_REC_NO IS NOT NULL "+
					" ) "+

					///////////////////////////////////////

					
					" ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
			
                      						
			}
			
			mflag=true;							
		  more = rs.next();
			   if(more){
			    out.println("<table align='center' border=\"0\" width='100%' class='table'>");  //colspan=13
					
					out.println("<tr></tr>");
		
				  out.println("<tr class=pdn_txtpos2 >");
					out.println("<td width='2%' align='left'>No</td>");
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Receipt  No  '    onclick=sort_data('REC_NO') >Receipt  No</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Client Name  '    onclick=sort_data('CLIENT_CODE') >Client Code</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Settlement Mode  '    onclick=sort_data('SETTLE_MODE') >Settlement Mode</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt Amount '    onclick=sort_data('REC_AMOUNT') >Receipt Amount</td>"); 
					//out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Allocated Amount '    onclick=sort_data('ALLOCATED_AMOUNT') >Allocated Amount</td>"); 
					//out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Balance Amount '    onclick=sort_data('BAL_TOBE_RECEIVE') >Balance Amount</td>"); 
				  out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Effective Value Date '    onclick=sort_data('EFF_VALDATE') >Effective Value Date</td>"); 
					out.println("<td width='5%' style= cursor:hand; title='Click here to sort by - Count  '    onclick=sort_data('Con') >Contracts</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Comments  '    onclick=sort_data('EFF_VALDATE') >Comments</td>"); 
					out.println("</tr>");

					}
					
					 j=1;
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='2%' align='left'>"+j+"</td>");
						out.println("<td width='15%' align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
			      out.println("<td width='15%' align='left' style= cursor:hand; onclick=\"show_client('"+rs.getString(7)+"') \" ><u>"+rs.getString(2)+"</u></td>");
			      out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
					  out.println("<td width='10%' align='left'>"+rs.getDouble(4)+"</td>");
					  out.println("<td width='10%' align='left'>"+rs.getString(5)+"</td>");
					  out.println("<td width='15%' align='left'>"+rs.getString(8)+"</td>");
			      out.println("<td width='15%' align='left'>"+rs.getString(6)+"</td>");
			      out.println("</tr>");
							more = rs.next();
						j = j+1;	
							
					}	
					     
      	 		out.println("</table>");
			
			// end of addition 
			      out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					
			}
			
				else if(m_chksql.equals("not_allocated_to_invoices")){
			
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
			  String m_order_by = req.getParameter("order_by");
			  String m_sort_by = req.getParameter("sort_by");
										
			if(m_from_date==null){
			m_from_date="";
			}
			if(m_to_date==null){
			m_to_date="";
			}

			if(!m_from_date.equals("") && !m_to_date.equals("")){	
			
					rs = stmt.executeQuery (" SELECT "+
					" A.REC_NO, "+ //1
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME  ,"+ //2
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE,  "+ //3
					" B.REC_AMOUNT, "+ //4
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+ //5
					" OTH_COMMENTS ,"+  //6
					" B.CLIENT_CODE ,"+  //7
					" A.FINANCE_NO FINANCE_NO, "+ //8
					" A.BAL_TOBE_RECEIVE BAL_TOBE_RECEIVE,"+ //9
					" NVL("+m_schema_name+".AF_CO_GET_INVO_OUTSTAND_BAL_2(A.FINANCE_NO,'"+m_from_date+"','"+m_to_date+"'),0) PEN_INV  , "+ //10
					" NVL("+m_schema_name+".af_co_get_emp_name("+m_schema_name+".AF_GET_COLL_OFFICER(A.FINANCE_NO)),'-' ) EMP_NAME "+ //11					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
					" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE A.REC_NO=B.REC_NO "+
					" AND   EFF_VALDATE >=  TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND   EFF_VALDATE <=  TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					" AND   BAL_TOBE_RECEIVE <>0 "+
					" AND A.REC_NO NOT IN ( "+
					" SELECT GROUP_REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE GROUP_REC_NO IS NOT NULL "+
					" ) "+
					//" AND   ALLOCATED_AMOUNT =0  "+
					" AND   B.STATUS NOT IN('C','CAD','RET') "+
					//" AND   B.GROUP_REC_NO IS NULL "+
					" ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
										

			}
			
			if(m_from_date.equals("") || m_to_date.equals("")){	
				  
					rs = stmt.executeQuery (" SELECT "+
					" A.REC_NO, "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME  ,"+
					" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE,  "+
					" B.REC_AMOUNT, "+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
					" OTH_COMMENTS ,"+ 
					" B.CLIENT_CODE ,"+ 
					" A.FINANCE_NO FINANCE_NO, "+ //8
					" A.BAL_TOBE_RECEIVE BAL_TOBE_RECEIVE,"+ //9
					" NVL("+m_schema_name+".AF_CO_GET_INVO_OUTSTAND_BAL_2(A.FINANCE_NO,'"+m_from_date+"','"+m_to_date+"'),0) PEN_INV, "+ //10
					" NVL("+m_schema_name+".af_co_get_emp_name("+m_schema_name+".AF_GET_COLL_OFFICER(A.FINANCE_NO)),'-' ) EMP_NAME "+ //11					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+
					" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE A.REC_NO=B.REC_NO "+
					" AND   BAL_TOBE_RECEIVE <>0 "+
					//" AND   ALLOCATED_AMOUNT =0  "+
					" AND   B.STATUS NOT IN('C','CAD','RET') "+
					" AND   B.GROUP_REC_NO IS NULL "+
					" ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
						
			}		
						
				
					boolean mflag=true;							
					boolean more = rs.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Unallocated Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("function sort_data(m_sort_col) {");
					 out.println(" m_from_date ='"+m_from_date+"';");	
					 out.println(" m_to_date='"+m_to_date+"';"); 	
		  		 out.println("	 m_order_by_type = 'ASC'; ");  
					 out.println("	 if(m_sort_col=='"+m_order_by+"'){");
					 out.println("	   if('"+m_sort_by+"'=='DESC'){");
					 out.println("	      m_order_by_type = 'ASC'; ");  
					 out.println("    }else{");
					 out.println("       m_order_by_type = 'DESC'; ");
					 out.println("    }");
					 out.println("  }else{");
					 out.println("    m_order_by_type = 'ASC'; ");
					 out.println("  }");
					// out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					// out.println(" window.location.href=m_url;"); 
					 out.println("}");
 	
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Receipt Unallocated Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
					
					out.println("<table align='center' border=\"0\" width='100%' class='table'>");  //colspan=13
					
					out.println("<tr></tr>");
		
				  out.println("<tr class=pdn_txtpos2 >");
					out.println("<td width='2%' align='left'>No</td>");
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Receipt  No  '    onclick=sort_data('REC_NO') >Receipt  No</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Finance  No  '    onclick=sort_data('FINANCE_NO') >Finance  No</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Collection Officer  '    onclick=sort_data('EMP_NAME') >Collection Officer</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Client Name  '    onclick=sort_data('CLIENT_CODE') >Client Code</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Settlement Mode  '    onclick=sort_data('SETTLE_MODE') >Settlement Mode</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt Amount '    onclick=sort_data('REC_AMOUNT') >Receipt Amount</td>"); 
				  out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Effective Value Date '    onclick=sort_data('EFF_VALDATE') >Effective Value Date</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Comments  '    onclick=sort_data('EFF_VALDATE') >Comments</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Un Allocated Amount  '    onclick=sort_data('BAL_TOBE_RECEIVE') >Un Allocated Amount</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Pending Invoices  '    onclick=sort_data('PEN_INV') >Pending Invoices</td>"); 
					
					out.println("</tr>");

					}
					
					int j=1;
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='2%' align='left'>"+j+"</td>");
						out.println("<td width='15%' align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='15%' align='left'>"+rs.getString(8)+"</td>");
						out.println("<td width='15%' align='left'>"+rs.getString(11)+"</td>");
			      out.println("<td width='15%' align='left' style= cursor:hand; onclick=\"show_client('"+rs.getString(7)+"')\" ><u>"+rs.getString(2)+"</u></td>");
			      out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
					  out.println("<td width='10%' align='left'>"+rs.getDouble(4)+"</td>");
					  out.println("<td width='10%' align='left'>"+rs.getString(5)+"</td>");
					  out.println("<td width='15%' align='left'>"+rs.getString(6)+"</td>");
						out.println("<td width='15%' align='left'>"+rs.getString(9)+"</td>");
						out.println("<td width='15%' align='left'>"+rs.getString(10)+"</td>");
			      out.println("</tr>");
							more = rs.next();
						j = j+1;	
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					
			}
			else if(m_chksql.equals("load_receipts")){
			
			  String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
			  String m_order_by = req.getParameter("order_by");
			  String m_sort_by = req.getParameter("sort_by");
			
							
			if(m_from_date==null){
			m_from_date="";
			}
			if(m_to_date==null){
			m_to_date="";
			}

			if(!m_from_date.equals("") && !m_to_date.equals("")){	
			
			
			/*
			rs = stmt.executeQuery ("SELECT A.REC_NO,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Chaeque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,OTH_COMMENTS,CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,A.ENT_DATE "+
					 " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					 " "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+ 
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					 " AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			     " ORDER BY "+m_sort_by+"  "+m_order_by+" ");	 */
			
			
			
			rs = stmt.executeQuery ("SELECT A.REC_NO,LAKDL.af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,NVL(OTH_COMMENTS,'-'),CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,ENT_DATE "+
					 " FROM   LAKDL.AF_CO_PRO_SETTL_RECEIPT A, "+
					 " LAKDL.AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+  
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					 " AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					 " ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
						
					 //" TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('01-01-2007','DD-MM-YYYY') "+ 
					 //" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('01-01-2009','DD-MM-YYYY') ");
			
			
			
			
			
			
			



			}
			
			if(m_from_date.equals("") || m_to_date.equals("")){	

						
			rs = stmt.executeQuery ("SELECT A.REC_NO,LAKDL.af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Chaeque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,NVL(OTH_COMMENTS,'-'),CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,A.ENT_DATE "+
					 " FROM   LAKDL.AF_CO_PRO_SETTL_RECEIPT A, "+
					 " LAKDL.AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+  
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " ORDER BY "+m_order_by+"  "+m_sort_by+" ");		
						
						
		
			}		
						
						
						
				
					boolean mflag=true;							
					boolean more = rs.next();
					
					 out.println("<HTML><HEAD><TITLE>Receipt Unallocated Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("function sort_data(m_sort_col) {");
					 out.println(" m_from_date ='"+m_from_date+"';");	
					 out.println(" m_to_date='"+m_to_date+"';"); 	
		  		 out.println("	 m_order_by_type = 'ASC'; ");  
					 out.println("	 if(m_sort_col=='"+m_order_by+"'){");
					 out.println("	   if('"+m_sort_by+"'=='DESC'){");
					 out.println("	      m_order_by_type = 'ASC'; ");  
					 out.println("    }else{");
					 out.println("       m_order_by_type = 'DESC'; ");
					 out.println("    }");
					 out.println("  }else{");
					 out.println("    m_order_by_type = 'ASC'; ");
					 out.println("  }");
					// out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					// out.println(" window.location.href=m_url;"); 
					 out.println("}");
 	
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Receipt Unallocated Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
					
					out.println("<table align='center' border=\"0\" width='100%' class='table'>");  //colspan=13
					
					out.println("<tr></tr>");
		
				  out.println("<tr class=pdn_txtpos2 >");
		
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Receipt  No  '    onclick=sort_data('REC_NO') >Receipt  No</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Client Name  '    onclick=sort_data('CLIENT_CODE') >Client Code</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Settlement Mode  '    onclick=sort_data('SETTLE_MODE') >Settlement Mode</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by -  Receipt Amount '    onclick=sort_data('REC_AMOUNT') >Receipt Amount</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Allocated Amount '    onclick=sort_data('ALLOCATED_AMOUNT') >Allocated Amount</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Balance Amount '    onclick=sort_data('BAL_TOBE_RECEIVE') >Balance Amount</td>"); 
				  out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Effective Value Date '    onclick=sort_data('EFF_VALDATE') >Effective Value Date</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Comments  '    onclick=sort_data('EFF_VALDATE') >Comments</td>"); 
					out.println("</tr>");
					  

					}
					
					int j=0;
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='15%' align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
			      out.println("<td width='15%' align='left' style= cursor:hand;  ><u>"+rs.getString(2)+"</u></td>");
			      out.println("<td width='10%' align='left'>"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_START_DATE_"+j+" value=\""+rs.getString(3)+"\"></td>");
			      out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"<input class='txt_input' type='hidden' name=TXT_END_DATE_"+j+" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
			      out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"<input class='txt_input' type='hidden' name=TXT_ACC_NO_"+j+" value=\""+nf.format(rs.getDouble(5))+"\"></td>");
			      out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"<input class='txt_input' type='hidden' name=TXT_BRANCH_CODE_"+j+" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
			      out.println("<td width='10%' align='center'>"+rs.getString(7)+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(7)+"\"></td>"); 
						out.println("<td width='15%' align='left'>"+rs.getString(8)+"<input class='txt_input' type='hidden' name=TXT_AMOUNT_"+j+" value=\""+rs.getString(8)+"\"></td>");
			      out.println("</tr>");
							more = rs.next();
						j = j+1;	
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					
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
