import java.io.*; 
				import javax.servlet.*;   
				import javax.servlet.http.*; 
				import java.sql.*; 
				import java.util.*; 
				
				public class LAKDL_AF_MISF_Central_Bank_Reports_Main extends javax.servlet.http.HttpServlet { 
				
				ServletOutputStream out = null;
				Connection conn;
				Statement stmt,stmt1,stmt2,stmt3;
				java.text.NumberFormat nf,nf1;
				public ResultSet rs,rs1,rs2,rs3;
				
				public String m_chksql;
				
				public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
				
				try { 
				
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
				out = res.getOutputStream(); 
				conn = m_sn_methods.met_user_validate(req); 
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_schema_name = m_sn_methods.schema_name;
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_header_name=m_sn_methods.header_name.trim();
				String m_username = m_sn_methods.username;
				nf = java.text.NumberFormat.getInstance(Locale.US);
				nf.setMinimumFractionDigits(2);
				nf.setMaximumFractionDigits(2);      
				res.setStatus(HttpServletResponse.SC_OK); 
				res.setContentType("text/html"); 
				m_chksql=req.getParameter("chksql");
				stmt=conn.createStatement();
				stmt1=conn.createStatement();
				stmt2=conn.createStatement(); 
				CallableStatement callstmt1 =null;				
				
				if(m_chksql.equals("run_report")){ 
			
				String m_date=req.getParameter("date");
				String m_user_id=req.getParameter("user_id");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CB_REPORTS(:1,:2);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_user_id); //  m_username
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}
						
			 else	if(m_chksql.equals("please_wait")){
										
				out.println("<HTML>");
				out.println("<body leftmargin=0 topmargin=0 >");
				out.println("<img src=\""+m_html_client_url+"/images/please_wait.gif\">");
				out.println("</body>");
				out.println("</html>");
							
			 }
				else	if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Accounting - Arrears repaid in fixed installments Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
				out.println("		}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				//out.println("			print_report2();"); 
				out.println("			alert('Details Saved Successfully');");
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
				out.println("		m_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				
				out.println(" makeRequest_detail();");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_accomadations_in_Fixed_Repaid_Instalments?chksql=print_report_new_all&date=\"+m_date;");	
				//out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				
				out.println("function view_report(opt){");
				out.println("	if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
				out.println("		m_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				
				out.println("if(opt==\"OEX_1_(i)_(A)\") {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Sql_Validation?chksql=OEX_1_(i)_(A)&date=\"+m_date;");	
				out.println("	}");
				
				out.println("if(opt==\"OEX_1_(i)_(B)\") {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Sql_Validation?chksql=OEX_1_(i)_(B)&date=\"+m_date;");	
				out.println("	}");
				
				out.println("if(opt==\"OEX_2\") {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Sql_Validation?chksql=OEX_2&date=\"+m_date;");	
				out.println("	}");
				
				out.println("if(opt==\"OEX_6\") {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Sql_Validation?chksql=OEX_6&date=\"+m_date;");	
				out.println("	}");
				
				out.println("if(opt==\"OEX_1_(iii)\") {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Sql_Validation?chksql=OEX_1_(iii)&date=\"+m_date;");	
				out.println("	}");
				
				out.println("if(opt==\"OEX_11\") {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Sql_Validation?chksql=OEX_11&date=\"+m_date;");	
				out.println("	}");
				
				
				
				
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				
				
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
				//out.println("   alert('"+m_username+"');");
				out.println("	if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
				out.println("		m_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Main?chksql=run_report&user_id="+m_username+"&date=\"+m_date;"); 
								
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Central_Bank_Reports_Main?chksql=please_wait;\""); 
		    //out.println("popupwin = window.open(m_url,'popupwin','left=290,top=250,width=330,height=165,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=0,resizable=1');");
		    //out.println("popupwin.focus();");
		    out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				
				
				
				
				
				out.println("function makeRequest_detail() {");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				/*out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println(" 	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				*/
				out.println(" 	m_to_date   = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_accomadations_in_Fixed_Repaid_Instalments?chksql=load_details&m_from_date=\"+m_from_date+\"&m_to_date=\"+m_to_date+\"&order_by=ENT_DATE&sort_by=ASC\";");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=40,top=200,width=1000,height=400,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("   }");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_accomadations_in_Fixed_Repaid_Instalments?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_accomadations_in_Fixed_Repaid_Instalments?chksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Accounting - Arrears repaid in fixed installments Report- \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Accounting - Arrears repaid in fixed installments Report \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=get_quart_date\";");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Accounting - Arrears repaid in fixed installments Report</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='6%'></td>");  
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
				out.println("<td width='10%' ><DIV id='DIV_TXT_TO_DATE'  class=div_input>As at Date</DIV></td>"); 
				out.println(" <TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)\" size=\"4\" >");	
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run CB Reports\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("<td width='*%' ></td>");
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
			  out.println("<tr>");  
			  out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		    out.println("</tr>"); 
			  out.println("</table>"); 

				
				out.println("<br><br><br>");  
				
				out.println("<table align='center' width='100%' class='table'>"); 
			  
				out.println("<tr>");  
				out.println("<td width=\"100%\" onClick=\"\" ><input class='but_input' type='button' name='BUT_PRINT' value=\"OEX 1 (i) (A)\" onClick=\"view_report('OEX_1_(i)_(A)')\" style=\"{width:250px;}\"></td>"); 
		    out.println("</tr>"); 
			  
				out.println("<tr>");  
				out.println("<td width=\"100%\" onClick=\"\" ><input class='but_input' type='button' name='BUT_PRINT' value=\"OEX 1 (i) (B)\" onClick=\"view_report('OEX_1_(i)_(B)')\" style=\"{width:250px;}\"></td>"); 
		    out.println("</tr>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\" onClick=\"\" ><input class='but_input' type='button' name='BUT_PRINT' value=\"OEX 1 (iii)\" onClick=\"view_report('OEX_1_(iii)')\" style=\"{width:250px;}\"></td>"); 
		    out.println("</tr>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\" onClick=\"\" ><input class='but_input' type='button' name='BUT_PRINT' value=\"OEX 2 \" onClick=\"view_report('OEX_2')\" style=\"{width:250px;}\"></td>"); 
		    out.println("</tr>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\" onClick=\"\" ><input class='but_input' type='button' name='BUT_PRINT' value=\"OEX 6 \" onClick=\"view_report('OEX_6')\" style=\"{width:250px;}\"></td>"); 
		    out.println("</tr>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\" onClick=\"\" ><input class='but_input' type='button' name='BUT_PRINT' value=\"OEX 11 \" onClick=\"view_report('OEX_11')\" style=\"{width:250px;}\"></td>"); 
		    out.println("</tr>"); 
				
				
							
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
				
				

				
				
				}
				//-------------------------------------------
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
