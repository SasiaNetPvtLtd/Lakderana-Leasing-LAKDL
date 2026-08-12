// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:17-03-2008
         
          
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_RPT_Collection_Remarks_Main extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			stmt = conn.createStatement();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_dd="";
			String m_mm="";
			String m_yy="";
			
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL");
			if(rs.next()){
			m_dd=rs.getString(1);
			m_mm=rs.getString(2);
			m_yy=rs.getString(3);
			}
			    
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Remarks Report </TITLE>"); // modified by udara
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function validate_data(){"); 
			// commented by udara somathilake on 17-11-2010
			/*
			out.println("if(document.Form1.TXT_ST_DD.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_START.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("if(document.Form1.TXT_ST_MM.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_START.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("if(document.Form1.TXT_ST_YY.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_START.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			*/
			out.println("if(document.Form1.TXT_END_DD.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_END.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("if(document.Form1.TXT_END_MM.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_END.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("if(document.Form1.TXT_END_YY.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_END.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else{"); 
			out.println("		return true;"); 
			out.println("	}"); 
			out.println("}"); 	
			
			out.println("function load_calendar() {");
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			// commented by udara somathilake on 17-11-2010
			/*
			out.println("function view_report(){");
			out.println("if(validate_data()){");
			out.println("		m_rpt_st_date=document.Form1.TXT_ST_DD.value+'-'+document.Form1.TXT_ST_MM.value+'-'+document.Form1.TXT_ST_YY.value;");
			out.println("		m_rpt_end_date=document.Form1.TXT_END_DD.value+'-'+document.Form1.TXT_END_MM.value+'-'+document.Form1.TXT_END_YY.value;");
			out.println("	window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_RPT_display_collection_remarks_report?chksql=MAIN&REPORT_S_DATE=\"+m_rpt_st_date+\"&REPORT_E_DATE=\"+m_rpt_end_date+\"&REMARK_TYPE=\"+document.Form1.TXT_REMARK_TYPE.value);"); 
			out.println("}");
			out.println("}");
			*/
			
			// added by udara somathilake on 17-11-2010
			out.println("function view_report(){");
			out.println("if(validate_data()){");
			out.println("		m_rpt_st_date='';");
			out.println("		m_rpt_end_date=document.Form1.TXT_END_DD.value+'-'+document.Form1.TXT_END_MM.value+'-'+document.Form1.TXT_END_YY.value;");
			out.println("	window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_RPT_display_collection_remarks_report?chksql=MAIN&REPORT_S_DATE=\"+m_rpt_st_date+\"&REPORT_E_DATE=\"+m_rpt_end_date+\"&REMARK_TYPE=\"+document.Form1.TXT_REMARK_TYPE.value);"); 
			out.println("}");
			out.println("}");
			

			out.println("function makeRequest() {");
			out.println("	if(validate_data()){");
			out.println("		m_rpt_st_date=document.Form1.TXT_ST_DD.value+'-'+document.Form1.TXT_ST_MM.value+'-'+document.Form1.TXT_ST_YY.value;");
			out.println("		m_rpt_end_date=document.Form1.TXT_END_DD.value+'-'+document.Form1.TXT_END_MM.value+'-'+document.Form1.TXT_END_YY.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_Rate&start_date=\"+m_rpt_st_date+\"&end_date=\"+m_rpt_end_date+\"\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("	}");
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println(" 		if(document.Form1.hid_help_count.value==1){");
			out.println("     document.Form1.TXT_ST_DD.value=v_dd;");
			out.println("     document.Form1.TXT_ST_MM.value=v_mm;");
			out.println("     document.Form1.TXT_ST_YY.value=v_yy;");
			out.println("     }");
			out.println("     else{");
			out.println("     document.Form1.TXT_END_DD.value=v_dd;");
			out.println("     document.Form1.TXT_END_MM.value=v_mm;");
			out.println("     document.Form1.TXT_END_YY.value=v_yy;");
			out.println("     }");
			out.println("}");		


			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_RPT_Collection_Remarks_Main';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_RPT_Collection_Remarks_Main';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_COLLECTION\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\"  Collection Process - Remarks Report - \"+m_val;"); // modified by udara
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Collection Process - Remarks Report - \"+document.Form1.hid_status.value;"); // modified by udara
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("	if(confirm(\"Are You Sure\")){ ");
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
			out.println("	}"); 
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
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
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

			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");

			out.println("function help_update_facility() {"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_sql\";");
			out.println("  	m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\";"); 
			out.println("	}");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_client() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_CLIENT_AVAILABLE_ENTER_sql\";");
			out.println(" 	m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println("		HelpBox('1','10','0');"); 
			out.println("}"); 
			
  		out.println("</script>"); 
		  	
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Remarks Report</td>"); // modified by udara
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
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<br>");

			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			/*
			out.println("<tr >");
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_START\" class=div_input>Start Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_DD\" maxlength=\"2\" size=\"2\"  value=\""+m_dd+"\" tabindex=\"1\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_MM\" maxlength=\"2\" size=\"2\" value=\""+m_mm+"\" tabindex=\"2\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_YY\" maxlength=\"4\" size=\"4\" value=\""+m_yy+"\" onblur=\"checkMonthLength(document.Form1.TXT_ST_DD,document.Form1.TXT_ST_MM,document.Form1.TXT_ST_YY)\"  tabindex=\"3\">[DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=\"load_calendar(1)\">   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_END\" class=div_input>End Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_END_DD\" maxlength=\"2\" size=\"2\"  value=\""+m_dd+"\" tabindex=\"4\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_END_MM\" maxlength=\"2\" size=\"2\" value=\""+m_mm+"\" tabindex=\"5\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_END_YY\" maxlength=\"4\" size=\"4\" value=\""+m_yy+"\" onblur=\"checkMonthLength(document.Form1.TXT_END_DD,document.Form1.TXT_END_MM,document.Form1.TXT_END_YY),makeRequest()\" tabindex=\"6\">[DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=\"load_calendar(2)\">   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			*/
			
			out.println("<tr >");
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_END\" class=div_input>Action Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_END_DD\" maxlength=\"2\" size=\"2\"  value=\""+m_dd+"\" tabindex=\"4\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_END_MM\" maxlength=\"2\" size=\"2\" value=\""+m_mm+"\" tabindex=\"5\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_END_YY\" maxlength=\"4\" size=\"4\" value=\""+m_yy+"\" onblur=\"checkMonthLength(document.Form1.TXT_END_DD,document.Form1.TXT_END_MM,document.Form1.TXT_END_YY),makeRequest()\" tabindex=\"6\">[DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=\"load_calendar(2)\">   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("</table>"); 
			
			rs = stmt.executeQuery ("SELECT REMARK_CODE,REMARK_DESC "+
				" FROM "+m_schema_name+".FA_CO_MAS_REMARKS "+
				" WHERE ACTIVE_STATUS='Y' ");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");
			out.println("<td width='15%' ><DIV id='DIV_TXT_REMARK_TYPE'  class=div_input>Remarks</DIV></td>"); 
			//out.println("</tr>");
			//out.println("<tr>");
			out.println("<td width='40%' ><select class=txt_input type=text name=TXT_REMARK_TYPE maxlength=1 size=1 >");  
			out.println("<option value='NO' selected ></option>");
			    while(rs.next()){
			out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option> "); 
					}
			out.println("</select></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			
			/*out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");
			out.println("<td width='30%' ><TEXTAREA class='txt_input' name='TXT_COMMENT' style=\"width:250px; height:50px;\" maxlength=\"200\" size=\"100\"></TEXTAREA></td>");  
			out.println("</tr>");			
			out.println("</table>"); */
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_COLLECT_MODE'  class=div_input></DIV></td>");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_RPT' value=\"View Report\" onClick=\"view_report()\" style='width:100'>");
			out.println("</tr>");
			out.println("</table>"); 
			
			
			
			
			
			out.println("<hr>");
			
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
