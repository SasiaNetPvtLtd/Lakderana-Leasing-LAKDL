// DEVELOP BY : DISNAKA FOR OFSCL FACTORING    DATE:2011-09-14

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_FA_MIS_report_email extends javax.servlet.http.HttpServlet { 
	
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
			nf.setMaximumFractionDigits(2);
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			stmt = conn.createStatement ();
			
			if (m_chksql == null ) {
				m_chksql = "main_page";
			}
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			else if(m_chksql.trim().equals("main_page")){
				
				
				String m_string        = "";
				
				rs= stmt.executeQuery( " SELECT "+
					" ROWNUM, "+ //1
					" REPORT_ID, "+ //2
					" NVL(REPORT_NAME,'-') "+ //3
					" FROM "+m_schema_name+".FA_MIS_EMAIL_REPORTS "+
					" WHERE REPORT_CATEGORY = 'CLIENT_STATEMENT' ");
				
				m_string=m_string+"<option value='ALL' selected>All</option>";
				while(rs.next()){
					
					m_string=m_string+"<option value='"+rs.getString(2)+"' >"+rs.getString(3)+"</option>";
				}
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Operation Reports - Client Statement Email </TITLE>"); 
				
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</HEAD>");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
				out.println("		}");
				out.println("}");
				
				//To validate from date & to date
				/*out.println("function validate_date(){");
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
			*/
				
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		report_data.innerHTML=m_data;");
				out.println("}");
				
				
				out.println(" function loadReports() {");
				out.println("       var date = document.Form1.TXT_FROM_DATE_DD.value + '-' + document.Form1.TXT_FROM_DATE_MM.value + '-' + document.Form1.TXT_FROM_DATE_YY.value; ");
				out.println("       var id = document.Form1.REP_ID.value; ");
				out.println("	    m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MIS_report_email?chksql=CLIENT_STATEMENT_EMAIL&date=\"+date+\"&id=\"+id+\"\" ");
				out.println("    	load_interface(m_url, 'NO');");
				out.println(" }");
				
				
				out.println("function validate_data(){");
				
				out.println("	var count = parseInt(document.Form1.elements[\"NUM_CHKS\"].value) ");
				out.println("   for (var i = 1; i <= count ; i++) {"); 
				
				out.println("		if (document.Form1.elements[\"RECEIVED_\"+i].checked == true) {");
				out.println("	        return true; ");
				out.println("		}");
				out.println("   }");
				
				out.println("	alert(\"Select record \"); ");
				out.println("	return false;"); 
				out.println("}"); 		
				
				
				
				out.println("function before_submit(){ "); 
				out.println("	get_display_msg();");
				out.println("	if(validate_data()){"); 
				out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
				out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("				document.Form1.elements[i].disabled=false;");
				out.println("			}");
				out.println("			if(validate_data()){"); 
				out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_MIS_report_generation_email_save';");  
				out.println("				document.Form1.submit();	"); 
				out.println("			}"); 
				out.println("		}"); 
				out.println("	}"); 
				out.println("} "); 
				
				
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MIS_report_generation';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MIS_report_generation';"); 
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
				out.println("	help_box.innerHTML=\" Operation Reports - Client Statement Email  - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Operation Reports - Client Statement Email  \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	    document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				
				
				out.println("}");
				
			
				out.println("function validateEmail(num) {");
				
				out.println("	     var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/; ");
			
				out.println("       var x = document.Form1.elements['TXT_EMAIL_NO_'+num]; ");
			
				out.println("		 if ( (filter.test(x.value) == false)) {");  //(x.value.length > 0) &&
				out.println("		    alert('E-mail address is invalid.');");
				//out.println("		    setTimeout('document.forms[0].elements[\'' + object.name + '\'].select()', 1);");
				
				out.println("        }");
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
				//out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_sql\";"); 
				out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_VIEW_sql\";"); //added by nuwan de silva 11-08-2009
				
				out.println(" 	m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\";");
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
				out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
				out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
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
				
				
				//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"request_reports();\">");   //get_system_date();load_lock();
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' onload=\"get_system_date();load_lock();\">");//request_reports()
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Reports - Client Statement Email </td>"); 
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
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Email\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");   
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REP_ID'  class=div_input><B>Report Name</B></DIV></td>"); 
				out.println("<td width='18%' ><select name=\"REP_ID\" class=\"txt_input\" style ='width:155px;' onChange=\"\">"); 
				out.println(""+m_string+""); 
				out.println("</select></td>");
				out.println("<td width='*%' align='left' ></td>");
				out.println("</tr>"); 
				
				
				out.println("<tr>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>Date</b></DIV></td>"); 
				out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" ></td>");	
				out.println("<td align=\"left\" ><input class=\"but_input\" type='button' name='BUT_SEARCH' value=\"Search\" onClick=\"loadReports()\" style=\"width: 100px;\"</td>");
				out.println("<td width='*%' ></td>");
				out.println("</tr>"); 
				
				out.println("</table>"); 
				
				out.println("<br>"); 
				out.println("<DIV id='report_data'  class=div_input></DIV>");
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
			
			else if (m_chksql.trim().equals("CLIENT_STATEMENT_EMAIL")){
				String m_string    = "";
				String m_rep_id    = req.getParameter("id").trim(); 
				String m_run_date  = req.getParameter("date").trim(); 
				
				
				if ( m_rep_id.equals("ALL") ) {
					
					rs= stmt.executeQuery( " SELECT "+
					//out.println( " SELECT "+
						" DISTINCT A.FACILITY_NO, "+ //1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)  FULL_NAME, "+ //2
						" NVL(B.REGISTERED_EMAIL,'-') "+
						" FROM "+m_schema_name+".FA_MIS_EMAIL_REPORTS_LOG A ,"+m_schema_name+".FA_CO_MAS_CLIENT B"+
						" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
						" AND A.RUN_DATE = TO_DATE('"+m_run_date+"','DD-MM-YYYY')");
					
				}else {
					rs= stmt.executeQuery( " SELECT "+
						" A.FACILITY_NO, "+ //1
						" A.CLIENT_CODE, "+//2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)  FULL_NAME, "+ //2
						" NVL(B.REGISTERED_EMAIL,'-') "+
						" FROM "+m_schema_name+".FA_MIS_EMAIL_REPORTS_LOG A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
						" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
						" AND A.RUN_DATE = TO_DATE('"+m_run_date+"','DD-MM-YYYY')"+
						" AND A.REPORT_ID = '"+m_rep_id+"' ");
					
					
				}
				
				
				
				m_string=m_string+"<table align='left' width='700px' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%' ></td>"; 
				//m_string=m_string+"<td width='20%' ><DIV class=div_input>Report Id</DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Facility No</DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Client Code</DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Client Name</DIV></td>";
				m_string=m_string+"<td width='25%' ><DIV class=div_input>Email</DIV></td>";
				m_string=m_string+"<td width='7%' ><DIV class=div_input>Selection</DIV></td>";
				//m_string=m_string+"<td width='*%'></td>";
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
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='FACILITY_NO_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\">"+rs.getString(1)+"</td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>";
					m_string=m_string+"<td width='20%' >"+rs.getString(3)+"</td>";
					m_string=m_string+"<td width='25%' ><input class='txt_input' type='text' name='TXT_EMAIL_NO_"+chk_nums+"' value='"+rs.getString(4)+"' maxlength='200' size='10' style='width:160' onblur=\"validateEmail('"+chk_nums+"')\"></td>";
					m_string=m_string+"<td width='7%' ><INPUT TYPE='CHECKBOX' NAME='RECEIVED_"+chk_nums+"'></td>"; 
					m_string=m_string+"</tr>";
					
					
				} 
				
				
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
