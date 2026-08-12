                   


// Addedby ishani jayasundara 2013.08.08
				import java.io.*; 
				import javax.servlet.*;   
				import javax.servlet.http.*; 
				import java.sql.*; 
				import java.util.*; 
				
				public class LAKDL_AF_MISF_CB_Maturity_Analysis_New extends javax.servlet.http.HttpServlet { 
				
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
				String m_branch=req.getParameter("branch"); // added by udara 19-04-2017
				
				
				try{
				//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_MAT_ANALYSIS(:1,:2);END;");
				//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_TBD_MATURITY_RPT(:1,:2);END;"); // commented by udara 19-04-2017
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_TBD_MATURITY_RPT(:1,:2,:3);END;"); // added by udara 19-04-2017
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_user_id); 
				callstmt1.setString(3,m_branch); // added by udara 19-04-2017
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}
				
			}
							
			 else	if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Accounting - Arrears repaid not in fixed installments Report</TITLE>"); 
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
				out.println("			print_report2();"); 
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
				
				out.println("   m_branch = document.Form1.TXT_LOCATION_CODE.value; "); // added by udara 19-04-2017
				
				out.println("	if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
				out.println("		m_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_Maturity_Analysis_New?chksql=run_report&user_id="+m_username+"&date=\"+m_date;"); // commented by udara 19-04-2017
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_Maturity_Analysis_New?chksql=run_report&user_id="+m_username+"&date=\"+m_date+\"&branch=\"+m_branch;"); // added by udara 19-04-2017
				
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
				
				out.println(" 	m_to_date= m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_branch = document.Form1.TXT_LOCATION_CODE.value "); 
				
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_Maturity_Analysis_New?chksql=load_details&m_branch=\"+m_branch+\"&m_to_date=\"+m_to_date+\"&order_by=ENT_DATE&sort_by=ASC\";");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_Maturity_Analysis_New?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CB_Maturity_Analysis_New?chksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Accounting - Maturity Analysis New - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Accounting - Maturity Analysis New\";"); 
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
				out.println(" m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				//out.println(" m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\";"); 
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
				out.println("		document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
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
				out.println("	    else{");
				out.println("	         clear_data(Hid_No);");//Added To The Clear 
				out.println("	    }");
				out.println("	}"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}");
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"0\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_USER.value='';");
				out.println("		}"); 
				out.println("}");
				
				out.println("function load_calendar(num) {");
	      out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=280,width=320,height=230\");"); 
				out.println("}");
								
				out.println("function load_c_date(val) {");
				out.println("var date1='' ");
				out.println("var date2='' ");
			  out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.TXT_TO_DATE_DD.value=v_date;");
				out.println("     document.Form1.TXT_TO_DATE_MM.value=v_month;");
				out.println("     document.Form1.TXT_TO_DATE_YY.value=val;");
				out.println("date1=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_from_date.value=date1");			
				out.println("}");
				out.println("}");
					
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			  out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			  out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Accounting - Maturity Analysis New</td>"); 
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
				out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>Date As At*</DIV></td>"); 
				out.println(" <TD WIDTH=\"60%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>");
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\" style=\"{width:110px;}\" onClick=\"makeRequest_detail()\">");	
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("<td width='*%' ></td>");
				out.println("</table>");  
				
				out.println("<table align='center' width='100%' class='table'>"); 
			  out.println("<tr>");  
			  out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		    out.println("</tr>"); 
			  out.println("</table>"); 
						
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
				
				
				else if(m_chksql.equals("load_details")){
				String m_string="";				
				String m_sql="";	
				
				String m_to_date=req.getParameter("m_to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_branch = req.getParameter("m_branch");
				
				
				String contract_det   = ""; 
				String fixed_instalments = "";
				
				String m_branch_sql = ""; // added by udara 27-07-2017
				
				if(m_branch!=null){
				
					if(!m_branch.equals("")){
						m_branch_sql = " AND A.BRANCH_CODE = '"+m_branch+"' ";
					}
					else{
						m_branch_sql = "  ";
					}
				
				}
				else{
					m_branch_sql = "  ";
				}
				
			
							
				String Comp_Name = " SELECT COMPANY_NAME, "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
				" INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
				" TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				rs1 = stmt1.executeQuery(Comp_Name);
				
				boolean more1 = rs1.next();	 				
				
					
				
    		out.println("<HTML><HEAD><TITLE>Maturity Analysis</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
					
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				
				out.println("<br>");
				out.println("<br>");
				out.println("<table class='table' align='center' border='0' width='1600'>");
				out.println("<tr >");
				out.println("<td align='left' width='*%' ><B>"+rs1.getString(1).toUpperCase()+"</td>");
				out.println("</tr>");
				out.println("<tr >");
				//out.println("<td align='left' width='*%' ><B>PRODUCT - LEASES / HIRE PURCHASES / MB	</B></td>");
				out.println("<td align='left' width='*%' ><B>PRODUCT - HIRE PURCHASE	</B></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' ><B>REPORT - MATURITY ANALYSIS AS AT "+m_to_date+" </td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				
						
				out.println("<table width='2562'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr BGCOLOR=#a5b6c6 >");
				out.println("<td width='12' rowspan='3' align='left'><b>No.</b></td>");//0
				out.println("<td width='200' rowspan='3' align='left'><b>Name of the Borrower</b></td>");//1
				out.println("<td width='100' rowspan='3' align='left'><b>Contact no</b></td>");//2
				
				out.println("<td width='100' rowspan='3' align='left'><b>Date Granted</b></td>");//2
				out.println("<td width='100' rowspan='3' align='left'><b>Date Settlement</b></td>");//2
				
				out.println("<td width='100' rowspan='3' align='right'><b>Total O/S</b></td>");//3
				out.println("<td width='100' rowspan='3' align='right'><b>Capital</b></td>");//4
				out.println("<td width='100' rowspan='3' align='right'><b>Interest</b></td>");//5
				//out.println("<td width='100' rowspan='3' align='right'><b>Others</b></td>");//6
				out.println("<td width='2550' colspan='51' align='center'><b>ANALYSIS OF FUTURE CASH OUTFLOWS</b></td>");
				out.println("</tr>");
				out.println("<tr BGCOLOR=#a5b6c6 height=10>");
				out.println("<td width='150' colspan='3' align='center'><b>1 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>2 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>3 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>4 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>5 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>6 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>7 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>8 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>9 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>10 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>11 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>12 Months</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 12-24 Months &nbsp;&nbsp;&nbsp; ( 1-2  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 24-36 Months &nbsp;&nbsp;&nbsp; ( 2-3  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 36-48 Months &nbsp;&nbsp;&nbsp; ( 3-4  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>  &nbsp;&nbsp;&nbsp; 48-60 Months &nbsp;&nbsp;&nbsp; ( 3-5  years )</b></td>");
				out.println("<td width='150' colspan='3' align='center'><b>Above 60 Months(more than 5 years)</b></td>");
				out.println("</tr>");
				out.println("<tr BGCOLOR=#a5b6c6 height=10>");
				// 1 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//7
				out.println("<td width='50' align='right'><b>Interest</b></td>");//8
				out.println("<td width='50' align='right'><b>Total</b></td>");//10
				// 2 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//11
				out.println("<td width='50' align='right'><b>Interest</b></td>");//12
				out.println("<td width='50' align='right'><b>Total</b></td>");//14
				// 3 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//15
				out.println("<td width='50' align='right'><b>Interest</b></td>");//16
				out.println("<td width='50' align='right'><b>Total</b></td>");//18
				// 4 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//19
				out.println("<td width='50' align='right'><b>Interest</b></td>");//20
				out.println("<td width='50' align='right'><b>Total</b></td>");//22
				// 5 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//23
				out.println("<td width='50' align='right'><b>Interest</b></td>");//24
				out.println("<td width='50' align='right'><b>Total</b></td>");//26
				// 6 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//27
				out.println("<td width='50' align='right'><b>Interest</b></td>");//28
				out.println("<td width='50' align='right'><b>Total</b></td>");//30
				// 7 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//31
				out.println("<td width='50' align='right'><b>Interest</b></td>");//32
				out.println("<td width='50' align='right'><b>Total</b></td>");//34
				// 8 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//35
				out.println("<td width='50' align='right'><b>Interest</b></td>");//36
				out.println("<td width='50' align='right'><b>Total</b></td>");//38
				// 9 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//39
				out.println("<td width='50' align='right'><b>Interest</b></td>");//40
				out.println("<td width='50' align='right'><b>Total</b></td>");//42
				// 10 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//43
				out.println("<td width='50' align='right'><b>Interest</b></td>");//44
				out.println("<td width='50' align='right'><b>Total</b></td>");//46
				// 11 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//47
				out.println("<td width='50' align='right'><b>Interest</b></td>");//48
				out.println("<td width='50' align='right'><b>Total</b></td>");//50
				// 12 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 12-24 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 24-36 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 36-48 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// 48-60 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				// abov 60 month
				out.println("<td width='50' align='right'><b>Capital</b></td>");//51
				out.println("<td width='50' align='right'><b>Interest</b></td>");//52
				out.println("<td width='50' align='right'><b>Total</b></td>");//54
				
				out.println("</tr>");
				
				// added by udara 23-09-2016
				if(conn==null){
					conn = m_sn_methods.met_user_validate(req); 
					stmt  = conn.createStatement ();
					stmt1  = conn.createStatement ();
					stmt2  = conn.createStatement ();
				}
				// added by udara 23-09-2016
				
				
				/*rs = stmt.executeQuery(" SELECT FINANCE_NO,  "+//1
				                       " APPLICATION_NO, "+//2
															 " CLIENT_CODE, "+//3
															 " CLIENT_NAME, "+//4
															 " TRANSACTION_TYPE, "+//5
															 " NVL(AGE,0), "+//6
															 " NVL(OUT_STANDING_AMT,0), "+//7
															 " NVL(CAPITAL,0), "+//8
												       " NVL(INTEREST,0), "+//9
															 " NVL(OTHER_CHARGES,0), "+//10
															 " NVL(CAPITAL_0_7,0),   NVL(INTERES_0_7,0),   NVL(OTHER_0_7,0), "+//13
															 " NVL(CAPITAL_8_14,0),  NVL(INTERES_8_14,0),  NVL(OTHER_8_14,0), "+//16
															 " NVL(CAPITAL_15_21,0), NVL(INTERES_15_21,0), NVL(OTHER_15_21,0), "+//19
															 " NVL(CAPITAL_22_31,0), NVL(INTERES_22_31,0), NVL(OTHER_22_31,0), "+//22
															 " NVL(CAPITAL_3_12,0),  NVL(INTERES_3_12,0),  NVL(OTHER_3_12,0), "+//25
															 " NVL(CAPITAL_12_24,0), NVL(INTERES_12_24,0), NVL(OTHER_12_24,0), "+//28
															 " NVL(CAPITAL_24_36,0), NVL(INTERES_24_36,0), NVL(OTHER_24_36,0), "+//31
															 " NVL(CAPITAL_36_48,0), NVL(INTERES_36_48,0), NVL(OTHER_36_48,0),"+//34
															 " NVL(CAPITAL_48_60,0), NVL(INTERES_48_60,0), NVL(OTHER_48_60,0), "+//37
															 " NVL(CAPITAL_60_OVER,0), NVL(INTERES_60_OVER,0), NVL(OTHER_60_OVER,0), "+//40
															 " NVL(CAPITAL_1_2,0), NVL(INTERES_1_2,0), NVL(OTHER_1_2,0), "+//43
															 " NVL(CAPITAL_2_3,0), NVL(INTERES_2_3,0), NVL(OTHER_2_3,0) "+//46
															 " FROM "+m_schema_name+".AF_MISF_TBD_MAT_ANALYSIS "+
															 " WHERE ENT_USER = '"+m_username+"' ");
  */
	
	
								// commented by udara 27-07-2017
								/*
								rs = stmt.executeQuery("SELECT DISTINCT A.FINANCE_NO, "+//1
								    " A.APPLICATION_NO, "+//2
										" A.CLIENT_CODE,  "+//3
										" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+//4
										" TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY') , "+ //5
										" TO_CHAR(LAST_PAYMNT_DATE,'DD-MM-YYYY') ,  "+ //6
										" NVL(ARREARS_AMOUNT,0) -NVL(INS_SUSPENSE,0) "+ //7
										" FROM "+m_schema_name+".AF_TBD_MATURITY A,"+m_schema_name+".AF_TBD_MATURITY_DET B "+
										" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
										" AND  A.ENT_USER = '"+m_username+"' "+
										" AND  B.ENT_USER = '"+m_username+"' "+
										" AND  STATUS IN ('1','2') "+
										" AND (A.BRANCH_CODE like '%"+m_branch+"%' OR  A.BRANCH_CODE IS NULL)"+
									 //	" AND  A.BRANCH_CODE like '%"+m_branch+"%' "+
										" ORDER BY FINANCE_NO  ");
								*/
								
								// added by udara 27-07-2017
								String m_sql_query = "";
								
								m_sql_query =  m_sql_query + " SELECT DISTINCT A.FINANCE_NO, "+//1
								        " A.APPLICATION_NO, "+//2
										" A.CLIENT_CODE,  "+//3
										" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+//4
										" TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY') , "+ //5
										" TO_CHAR(LAST_PAYMNT_DATE,'DD-MM-YYYY') ,  "+ //6
										" NVL(ARREARS_AMOUNT,0) -NVL(INS_SUSPENSE,0) "+ //7
										" FROM "+m_schema_name+".AF_TBD_MATURITY A,"+m_schema_name+".AF_TBD_MATURITY_DET B "+
										" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
										" AND  A.ENT_USER = '"+m_username+"' "+
										" AND  B.ENT_USER = '"+m_username+"' "+
										" AND  STATUS IN ('1','2') ";
										m_sql_query = m_sql_query + m_branch_sql; 
										m_sql_query = m_sql_query + " ORDER BY FINANCE_NO  ";
								
								//out.println(m_sql_query);		
										
								rs = stmt.executeQuery(m_sql_query);
								// end by udara 27-07-2017
	
	
	
	
				
				boolean more = rs.next();
				int j=0;
				String m_app_no="",m_finance_no ="";
				String m_activate_date="", m_last_date="";
				
				double m_cap_0_7=0,m_int_0_7=0,m_oth_0_7;
				double m_cap_1_2=0,m_int_1_2=0;
				double m_cap_2_3=0,m_int_2_3=0;
				double m_cap_3_4=0,m_int_3_4=0;
				double m_cap_4_5=0,m_int_4_5=0;
				double m_cap_5_6=0,m_int_5_6=0;
				double m_cap_6_7=0,m_int_6_7=0;
				double m_cap_7_8=0,m_int_7_8=0;
				double m_cap_8_9=0,m_int_8_9=0;
				double m_cap_9_10=0,m_int_9_10=0;
				double m_cap_10_11=0,m_int_10_11=0;
				double m_cap_11_12=0,m_int_11_12=0;
				double m_cap_12_24=0,m_int_12_24=0;
				double m_cap_24_36=0,m_int_24_36=0;
				double m_cap_36_48=0,m_int_36_48=0;
				double m_cap_48_60=0,m_int_48_60=0;
				double m_cap_60=0,m_int_60=0;
				double m_cap_tot=0,m_int_tot=0;
			    

				double m_sub_cap_tot = 0;
				double m_sub_int_tot = 0;
				double m_sub_oth_0_7 = 0;
				
				// 1 Month
				double m_sub_cap_0_7=0;
				double m_sub_int_0_7=0;
				// 2 Month
				double m_sub_cap_1_2=0;
				double m_sub_int_1_2=0;
				// 3 Month
				double m_sub_cap_2_3=0;
				double m_sub_int_2_3=0;
				// 4 Month
				double m_sub_cap_3_4=0;
				double m_sub_int_3_4=0;
                // 5 Month
				double m_sub_cap_4_5=0;
				double m_sub_int_4_5=0;
				// 6 Month
				double m_sub_cap_5_6=0;
				double m_sub_int_5_6=0;
				// 7 Month
				double m_sub_cap_6_7=0;
				double m_sub_int_6_7=0;
				// 8 Month
				double m_sub_cap_7_8=0;
				double m_sub_int_7_8=0;
				// 9 Month
				double m_sub_cap_8_9=0;
				double m_sub_int_8_9=0;
				// 10 Month
				double m_sub_cap_9_10=0;
				double m_sub_int_9_10=0;
				// 11 Month
				double m_sub_cap_10_11=0;
				double m_sub_int_10_11=0;
				// 12 Month
				double m_sub_cap_11_12=0;
				double m_sub_int_11_12=0;
				// 12-24 Month
				double m_sub_cap_12_24=0;
				double m_sub_int_12_24=0;
				// 24-36 Month
				double m_sub_cap_24_36=0;
				double m_sub_int_24_36=0;
				// 36-48 Month
				double m_sub_cap_36_48=0;
				double m_sub_int_36_48=0;
				// 48-60 Month
				double m_sub_cap_48_60=0;
				double m_sub_int_48_60=0;
				// over 60 Month
				double m_sub_cap_60=0;
				double m_sub_int_60=0;
			while(more){	
				j++;
				
				m_app_no = rs.getString(2);
				m_finance_no = rs.getString(1);
				
				m_activate_date = rs.getString(5);
				m_last_date     = rs.getString(6);
				
				
				m_oth_0_7 = rs.getDouble(7);	
				
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_tot = rs2.getDouble(1);
				m_int_tot = rs2.getDouble(2);
				}
				
				//// 1 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),0) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),1) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_0_7 = rs2.getDouble(1);
				m_int_0_7 = rs2.getDouble(2);
				}
				
				
				
				// 2 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),1) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),2) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_1_2 = rs2.getDouble(1);
				m_int_1_2 = rs2.getDouble(2);
				}
				// 3 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),2) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),3) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_2_3 = rs2.getDouble(1);
				m_int_2_3 = rs2.getDouble(2);
				}
				// 4 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),3) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),4) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_3_4 = rs2.getDouble(1);
				m_int_3_4 = rs2.getDouble(2);
				}
				
				// 5 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),4) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),5) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_4_5 = rs2.getDouble(1);
				m_int_4_5 = rs2.getDouble(2);
				}
				
				// 6 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),5) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),6) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_5_6 = rs2.getDouble(1);
				m_int_5_6 = rs2.getDouble(2);
				}
				
				// 7 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),6) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),7) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_6_7 = rs2.getDouble(1);
				m_int_6_7 = rs2.getDouble(2);
				}
				
				// 8 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),7) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),8) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_7_8 = rs2.getDouble(1);
				m_int_7_8 = rs2.getDouble(2);
				}
				
				// 9 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),8) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),9) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_8_9 = rs2.getDouble(1);
				m_int_8_9 = rs2.getDouble(2);
				}
				
				// 10 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),9) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),10) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_9_10 = rs2.getDouble(1);
				m_int_9_10 = rs2.getDouble(2);
				}
				
				
				// 11 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),10) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),11) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_10_11 = rs2.getDouble(1);
				m_int_10_11 = rs2.getDouble(2);
				}
				
				// 12 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),11) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),12) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_11_12 = rs2.getDouble(1);
				m_int_11_12 = rs2.getDouble(2);
				}
				
				// 12-24 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),12) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),24) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_12_24 = rs2.getDouble(1);
				m_int_12_24 = rs2.getDouble(2);
				}
				
				// 24-36 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),24) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),36) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_24_36 = rs2.getDouble(1);
				m_int_24_36 = rs2.getDouble(2);
				}
				
				// 36-48 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),36) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),48) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_36_48 = rs2.getDouble(1);
				m_int_36_48 = rs2.getDouble(2);
				}
				
				// 48-60 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),48) "+
																 " AND A.RENTAL_DATE < ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),60) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_48_60 = rs2.getDouble(1);
				m_int_48_60 = rs2.getDouble(2);
				}
				
				// over 60 Month
				rs2 = stmt2.executeQuery(" SELECT  SUM(A.CAPITAL_AMOUNT), "+
																 " SUM(A.INTEREST_AMOUNT) "+
																 " FROM "+m_schema_name+".AF_TBD_MATURITY_DET A "+
																 " WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
																 " AND A.RENTAL_DATE >= ADD_MONTHS(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),60) "+
																 " AND A.ENT_USER = '"+m_username+"' ") ;
				
				if(rs2.next()){
				m_cap_60 = rs2.getDouble(1);
				m_int_60 = rs2.getDouble(2);
				}
								
				/*rs2 = stmt2.executeQuery(" SELECT   "+
																 " NVL(SUM(A.TOTAL_AMOUNT-SETTELE_AMOUNT),0) OTHER_AMT "+
																 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
																 " WHERE A.INVOICE_TYPE <> 'INV_GENER'  "+
																 " AND A.FINANCE_NO     = '"+m_finance_no+"' "+
																 " AND A.VALUE_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  ");
				*/
				
				
				/**
				rs2 = stmt2.executeQuery(" SELECT   "+
				" NVL("+m_schema_name+".AF_CO_GET_APP_CHARG_ARR_AMT('"+m_app_no+"','"+m_to_date+"'),0) FROM DUAL ");
				if(rs2.next()){
				m_oth_0_7 = rs2.getDouble(1);				
				}
				**/
				
				out.println("<tr height=30>");
				out.println("<td width='12' align='left'>"+j+"</td>");//0
				out.println("<td width='200' align='left'>"+rs.getString(4)+"</td>");//1
				out.println("<td width='100' align='left'>"+rs.getString(1)+"</td>");//2
				
				
				out.println("<td width='100' align='left'>"+m_activate_date+"</td>");//2
				out.println("<td width='100' align='left'>"+m_last_date+"</td>");//2
				
				
				out.println("<td width='100' align='right'>"+nf.format(m_cap_tot+m_int_tot+m_oth_0_7)+"</td>");//3
				out.println("<td width='100' align='right'>"+nf.format(m_cap_tot)+"</td>");//4
				out.println("<td width='100' align='right'>"+nf.format(m_int_tot)+"</td>");//5
				//out.println("<td width='100' align='right'>"+nf.format(m_oth_0_7)+"</td>");//6
				//1
				out.println("<td width='100' align='right'>"+nf.format(m_cap_0_7)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_0_7)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_0_7+m_int_0_7)+"</td>");//10
				//2
				/*out.println("<td width='100' align='right'>"+nf.format(m_cap_8_14)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_8_14)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_8_14+m_int_8_14)+"</td>");//10
				//3
				out.println("<td width='100' align='right'>"+nf.format(m_cap_15_21)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_15_21)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_15_21+m_int_15_21)+"</td>");//10
				//4
				out.println("<td width='100' align='right'>"+nf.format(m_cap_22_31)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_22_31)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_22_31+m_int_22_31)+"</td>");//10*/
				//2
				out.println("<td width='100' align='right'>"+nf.format(m_cap_1_2)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_1_2)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_1_2+m_int_1_2)+"</td>");//10
				//3
				out.println("<td width='100' align='right'>"+nf.format(m_cap_2_3)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_2_3)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_2_3+m_int_2_3)+"</td>");//10
				//4
				out.println("<td width='100' align='right'>"+nf.format(m_cap_3_4)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_3_4)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_3_4+m_int_3_4)+"</td>");//10
				//5
				out.println("<td width='100' align='right'>"+nf.format(m_cap_4_5)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_4_5)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_4_5+m_int_4_5)+"</td>");//10
				//6
				out.println("<td width='100' align='right'>"+nf.format(m_cap_5_6)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_5_6)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_5_6+m_int_5_6)+"</td>");//10
				//7
				out.println("<td width='100' align='right'>"+nf.format(m_cap_6_7)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_6_7)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_6_7+m_int_6_7)+"</td>");//10
				//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_7_8)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_7_8)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_7_8+m_int_7_8)+"</td>");//10
				//9
				out.println("<td width='100' align='right'>"+nf.format(m_cap_8_9)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_8_9)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_8_9+m_int_8_9)+"</td>");//10
				//10
				out.println("<td width='100' align='right'>"+nf.format(m_cap_9_10)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_9_10)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_9_10+m_int_9_10)+"</td>");//10
				//11
				out.println("<td width='100' align='right'>"+nf.format(m_cap_10_11)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_10_11)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_10_11+m_int_10_11)+"</td>");//10
				//12
				out.println("<td width='100' align='right'>"+nf.format(m_cap_11_12)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_11_12)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_11_12+m_int_11_12)+"</td>");//10
				//12-24
				out.println("<td width='100' align='right'>"+nf.format(m_cap_12_24)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_12_24)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_12_24+m_int_12_24)+"</td>");//10
				//24-36
				out.println("<td width='100' align='right'>"+nf.format(m_cap_24_36)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_24_36)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_24_36+m_int_24_36)+"</td>");//10
				//36-48
				out.println("<td width='100' align='right'>"+nf.format(m_cap_36_48)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_36_48)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_36_48+m_int_36_48)+"</td>");//10
				//48-60				
				out.println("<td width='100' align='right'>"+nf.format(m_cap_48_60)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_48_60)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_48_60+m_int_48_60)+"</td>");//10
				//60
				out.println("<td width='100' align='right'>"+nf.format(m_cap_60)+"</td>");//7
				out.println("<td width='100' align='right'>"+nf.format(m_int_60)+"</td>");//8
				out.println("<td width='100' align='right'>"+nf.format(m_cap_60+m_int_60)+"</td>");//10
				
				out.println("</tr>");
				
				more = rs.next();
				
				m_sub_cap_tot = m_sub_cap_tot+m_cap_tot;
				m_sub_int_tot = m_sub_int_tot+m_int_tot;
				m_sub_oth_0_7 = m_sub_oth_0_7+m_oth_0_7;
				
				m_sub_cap_0_7=m_sub_cap_0_7+m_cap_0_7;
				m_sub_int_0_7=m_sub_int_0_7+m_int_0_7;
				
				m_sub_cap_1_2=m_sub_cap_1_2+m_cap_1_2;
				m_sub_int_1_2=m_sub_int_1_2+m_int_1_2;
				
				m_sub_cap_2_3=m_sub_cap_2_3+m_cap_2_3;
				m_sub_int_2_3=m_sub_int_2_3+m_int_2_3;
				
				m_sub_cap_3_4=m_sub_cap_3_4+m_cap_3_4;
				m_sub_int_3_4=m_sub_int_3_4+m_int_3_4;
				
				m_sub_cap_4_5=m_sub_cap_4_5+m_cap_4_5;
				m_sub_int_4_5=m_sub_int_4_5+m_int_4_5;
				
				m_sub_cap_5_6=m_sub_cap_5_6+m_cap_5_6;
				m_sub_int_5_6=m_sub_int_5_6+m_int_5_6;
				
				m_sub_cap_6_7=m_sub_cap_6_7+m_cap_6_7;
				m_sub_int_6_7=m_sub_int_6_7+m_int_6_7;
				
				m_sub_cap_7_8=m_sub_cap_7_8+m_cap_7_8;
				m_sub_int_7_8=m_sub_int_7_8+m_int_7_8;
				
				m_sub_cap_8_9=m_sub_cap_8_9+m_cap_8_9;
				m_sub_int_8_9=m_sub_int_8_9+m_int_8_9;
				
				m_sub_cap_9_10=m_sub_cap_9_10+m_cap_9_10;
				m_sub_int_9_10=m_sub_int_9_10+m_int_9_10;
				
				m_sub_cap_10_11=m_sub_cap_10_11+m_cap_10_11;
				m_sub_int_10_11=m_sub_int_10_11+m_int_10_11;
				
				m_sub_cap_11_12=m_sub_cap_11_12+m_cap_11_12;
				m_sub_int_11_12=m_sub_int_11_12+m_int_11_12;

				m_sub_cap_12_24=m_sub_cap_12_24+m_cap_12_24;
				m_sub_int_12_24=m_sub_int_12_24+m_int_12_24;
				
				m_sub_cap_24_36=m_sub_cap_24_36+m_cap_24_36;
				m_sub_int_24_36=m_sub_int_24_36+m_int_24_36;
				
				m_sub_cap_36_48=m_sub_cap_36_48+m_cap_36_48;
				m_sub_int_36_48=m_sub_int_36_48+m_int_36_48;
				
				m_sub_cap_48_60=m_sub_cap_48_60+m_cap_48_60;
				m_sub_int_48_60=m_sub_int_48_60+m_int_48_60;
				
				m_sub_cap_60=m_sub_cap_60+m_cap_60;
				m_sub_int_60=m_sub_int_60+m_int_60;
				
				
				}
				
				out.println("<tr height=30>");
				out.println("<td width='12' align='left'>&nbsp;</td>");//0
				out.println("<td width='200' align='left'>&nbsp;</td>");//1
				out.println("<td width='100' align='left'>&nbsp;</td>");//2
				out.println("<td width='100' align='left'>&nbsp;</td>");//2
				out.println("<td width='100' align='left'><b>Total</td>");//2
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_tot+m_sub_int_tot+m_sub_oth_0_7)+"</td>");//3
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_tot)+"</td>");//4
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_tot)+"</td>");//5
				//out.println("<td width='100' align='right'><b>"+nf.format(m_sub_oth_0_7)+"</td>");//6
				//1 Mont
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_0_7)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_0_7)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_0_7+m_sub_int_0_7)+"</td>");//10
				//8-14
				/*out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_8_14)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_8_14)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_8_14+m_sub_int_8_14)+"</td>");//10
				//15-21
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_15_21)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_15_21)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_15_21+m_sub_int_15_21)+"</td>");//10
				//22-31
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_22_31)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_22_31)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_22_31+m_sub_int_22_31)+"</td>");//10*/
				//2 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_1_2)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_1_2)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_1_2+m_sub_int_1_2)+"</td>");//10
				//3 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_2_3)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_2_3)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_2_3+m_sub_int_2_3)+"</td>");//10
				//4 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_3_4)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_3_4)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_3_4+m_sub_int_3_4)+"</td>");//10
				//5 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_4_5)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_4_5)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_4_5+m_sub_int_4_5)+"</td>");//10
				//6 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_5_6)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_5_6)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_5_6+m_sub_int_5_6)+"</td>");//10
				//7 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_6_7)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_6_7)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_6_7+m_sub_int_6_7)+"</td>");//10
				//8 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_7_8)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_7_8)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_7_8+m_sub_int_7_8)+"</td>");//10
				//9 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_8_9)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_8_9)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_8_9+m_sub_int_8_9)+"</td>");//10
				//10 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_9_10)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_9_10)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_9_10+m_sub_int_9_10)+"</td>");//10
				//11 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_10_11)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_10_11)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_10_11+m_sub_int_10_11)+"</td>");//10
				//12 Month
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_11_12)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_11_12)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_11_12+m_sub_int_11_12)+"</td>");//10
				//12-24
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_12_24)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_12_24)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_12_24+m_sub_int_12_24)+"</td>");//10
				//24-36
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_24_36)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_24_36)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_24_36+m_sub_int_24_36)+"</td>");//10
				//36-48
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_36_48)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_36_48)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_36_48+m_sub_int_36_48)+"</td>");//10
				//48-60				
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_48_60)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_48_60)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_48_60+m_sub_int_48_60)+"</td>");//10
				//60
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_60)+"</td>");//7
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_int_60)+"</td>");//8
				out.println("<td width='100' align='right'><b>"+nf.format(m_sub_cap_60+m_sub_int_60)+"</td>");//10
				out.println("</tr>");
								
				out.println("</table>");
				
				out.println("<br><br><br><br>");
				
				/*out.println("<table class='table' align='center' border='0' width='1600'>");
				out.println("<tr >");
				out.println("<td align='left' width='*%' ><B>"+rs1.getString(1)+"</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' ><B>Summary as at "+m_to_date+" </td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<table width='120%'  border='1' cellspacing='0' cellpadding='0' bordercolor=#eeeeee >");
				out.println("<tr BGCOLOR=#a5b6c6 >");
				out.println("<td width='10%' rowspan=2 style='text-align:left' ><b>Period</b></td>");//1
				out.println("<td width='30%' colspan=3 style='text-align:center' ><b>Finance Leases </b></td>");
				out.println("<td width='30%' colspan=3 style='text-align:center' ><b>Hire Purchases</b></td>");
				out.println("<td width='30%' colspan=3 style='text-align:center' ><b>MB Loans</b></td>");
				out.println("<td width='30%' colspan=3 style='text-align:center' ><b>FD Loans</b></td>");
				out.println("<td width='30%' colspan=3 style='text-align:center' ><b>Loans</b></td>");
				out.println("<td width='30%' rowspan=2 style='text-align:center' ><b>Total</b></td>");
				out.println("</tr>");
				
				out.println("<tr BGCOLOR=#a5b6c6 >");
				out.println("<td width='10%' align='right'>Capital</b></td>");//2
				out.println("<td width='10%' align='right'>Interest</b></td>");//3
				out.println("<td width='10%' align='right'>Others</b></td>");//4
				out.println("<td width='10%' align='right'>Capital</b></td>");//5
				out.println("<td width='10%' align='right'>Interest</b></td>");//6
				out.println("<td width='10%' align='right'>Others</b></td>");//7
				out.println("<td width='10%' align='right'>Capital</b></td>");//8
				out.println("<td width='10%' align='right'>Interest</b></td>");//9
				out.println("<td width='10%' align='right'>Others</b></td>");//10
				out.println("<td width='10%' align='right'>Capital</b></td>");//11
				out.println("<td width='10%' align='right'>Interest</b></td>");//12
				out.println("<td width='10%' align='right'>Others</b></td>");//13
				out.println("<td width='10%' align='right'>Capital</b></td>");//14
				out.println("<td width='10%' align='right'>Interest</b></td>");//15
				out.println("<td width='10%' align='right'>Others</b></td>");//16			
				out.println("</tr>");
												
				
				rs = stmt.executeQuery(" SELECT PERIOD, "+//1
				                       " TOT_FL_CAP, TOT_FL_INT, TOT_FL_OTH, "+//4
															 " TOT_HP_CAP, TOT_HP_INT, TOT_HP_OTH, "+//7
															 " TOT_MB_CAP, TOT_MB_INT, TOT_MB_OTH, "+//10
															 " TOT_FD_CAP, TOT_FD_INT, TOT_FD_OTH, "+//13
                               " TOT_LN_CAP, TOT_LN_INT, TOT_LN_OTH,  "+//16
															 " ORDER_ID "+
                               " FROM "+m_schema_name+".AF_MISF_TBD_MAT_SUMMREY A "+
															 " WHERE ENT_USER = '"+m_username+"' "+
															 " ORDER BY ORDER_ID");
				
				boolean more2 = rs.next();
				double m_total =0;
				
				double m_grant_total=0,m_grant_tot1=0,m_grant_tot2=0,m_grant_tot3=0,m_grant_tot4=0,m_grant_tot5=0,m_grant_tot6=0,m_grant_tot7=0,m_grant_tot8=0,m_grant_tot9=0,m_grant_tot10=0,
				              m_grant_tot11=0,m_grant_tot12=0,m_grant_tot13=0,m_grant_tot14=0,m_grant_tot15=0;
				while(more2){
				m_total = rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(4)+rs.getDouble(5)+rs.getDouble(6)+rs.getDouble(7)+
				          rs.getDouble(8)+rs.getDouble(9)+rs.getDouble(10)+rs.getDouble(11)+rs.getDouble(12)+rs.getDouble(13)+
									rs.getDouble(14)+rs.getDouble(15)+rs.getDouble(16);
				out.println("<tr height=30>");
				out.println("<td width='30%' align='left'>"+rs.getString(1)+"</td>");//1
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(2))+"</b></td>");//2
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</b></td>");//3
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</b></td>");//4
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"</b></td>");//5
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"</b></td>");//6
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(7))+"</b></td>");//7
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(8))+"</b></td>");//8
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(9))+"</b></td>");//9
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(10))+"</b></td>");//10
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(11))+"</b></td>");//11
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(12))+"</b></td>");//12
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(13))+"</b></td>");//13
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(14))+"</b></td>");//14
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(15))+"</b></td>");//15
				out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(16))+"</b></td>");//16			
				out.println("<td width='30%' align='right'>"+nf.format(m_total)+"</td>");
				out.println("</tr>");
				
				
				m_grant_tot1=m_grant_tot1+rs.getDouble(2);
				m_grant_tot2=m_grant_tot2+rs.getDouble(3);
				m_grant_tot3=m_grant_tot3+rs.getDouble(4);
				m_grant_tot4=m_grant_tot4+rs.getDouble(5);
				m_grant_tot5=m_grant_tot5+rs.getDouble(6);
				m_grant_tot6=m_grant_tot6+rs.getDouble(7);
				m_grant_tot7=m_grant_tot7+rs.getDouble(8);
				m_grant_tot8=m_grant_tot8+rs.getDouble(9);
				m_grant_tot9=m_grant_tot9+rs.getDouble(10);
				m_grant_tot10=m_grant_tot10+rs.getDouble(11);
				m_grant_tot11=m_grant_tot11+rs.getDouble(12);
				m_grant_tot12=m_grant_tot12+rs.getDouble(13);
				m_grant_tot13=m_grant_tot13+rs.getDouble(14);
				m_grant_tot14=m_grant_tot14+rs.getDouble(15);
				m_grant_tot15=m_grant_tot15+rs.getDouble(16);
				
				m_grant_total=m_grant_tot1+m_grant_tot2+m_grant_tot3+m_grant_tot4+m_grant_tot5+m_grant_tot6+m_grant_tot7+m_grant_tot8+m_grant_tot9+m_grant_tot10
				              +m_grant_tot11+m_grant_tot12+m_grant_tot13+m_grant_tot14+m_grant_tot15;
				
				more2 = rs.next();
				}
				
				out.println("<tr height=30>");
				out.println("<td width='30%' align='left'><b>Total</td>");//1
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot1)+"</b></td>");//2
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot2)+"</b></td>");//3
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot3)+"</b></td>");//4
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot4)+"</b></td>");//5
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot5)+"</b></td>");//6
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot6)+"</b></td>");//7
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot7)+"</b></td>");//8
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot8)+"</b></td>");//9
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot9)+"</b></td>");//10
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot10)+"</b></td>");//11
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot11)+"</b></td>");//12
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot12)+"</b></td>");//13
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot13)+"</b></td>");//14
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot14)+"</b></td>");//15
				out.println("<td width='10%' align='right'><b>"+nf.format(m_grant_tot15)+"</b></td>");//16			
				out.println("<td width='30%' align='right'><b>"+nf.format(m_grant_total)+"</td>");
				out.println("</tr>");
				
				
				out.println("</table>");*/
				
				out.println("<br><br><br><br>");
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
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