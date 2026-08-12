// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:29-10-2007
         
          
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_AC_Finance_Interest_Rate_Main extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>Operation Process - Factoring Finance Interest Rate</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var alert_msg='Save';");
			
			out.println("function validate_data(){"); 
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
			out.println("if(document.Form1.TXT_INTEREST_AMT.value==\"\"){  "); 
			out.println("		DIV_TXT_INT_RATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			
			out.println("	else{"); 
			out.println("		return true;"); 
			out.println("	}"); 
			out.println("}"); 			  

			out.println("function load_calendar(m_num) {");
			out.println(" document.Form1.hid_help_count.value=m_num;");
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			
			out.println("function makeRequest() {");
			out.println("	if(validate_data()){");
			out.println("		m_rpt_st_date=document.Form1.TXT_ST_DD.value+'-'+document.Form1.TXT_ST_MM.value+'-'+document.Form1.TXT_ST_YY.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_get_Rate&start_date=\"+m_rpt_st_date+\"\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");

					
			out.println("function get_vector(data_vec) {");
			out.println("vec_len = data_vec.length ;");
			out.println("if(data_vec.length==0) {");
			out.println("document.Form1.TXT_INTEREST_AMT.disabled=false;");
			out.println("document.Form1.TXT_INTEREST_AMT.value='';");
			out.println("document.Form1.TXT_INTEREST_AMT.value=0.00;");
			out.println(" }");
			out.println("else {");
			out.println("document.Form1.TXT_INTEREST_AMT.value=data_vec[0];");
			out.println("document.Form1.TXT_INTEREST_AMT.disabled=true;");
			out.println(" }");
			out.println(" }");
			
			
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

			out.println("}");		

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_AC_Finance_Interest_Rate_Main';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_AC_Finance_Interest_Rate_Main';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 



			out.println("function before_submit(){ "); 
			out.println("	if(validate_data()){");
			out.println("		if(confirm(\"Are you sure you want to \"+alert_msg+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_AC_Save_Finance_Interest_Rate';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 

			out.println("} "); 





			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_ACCOUNTS\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Operation Process - Factoring Finance Interest Rate - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Operation Process - Factoring Finance Interest Rate - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("	if(confirm(\"Are You Sure\")){ ");
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("alert_msg=\"Save\";");
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("alert_msg=\"Save\";");
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("alert_msg=\"Modify\";");
			out.println("		}");
			out.println("		else if(m_val==\"DACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("alert_msg=\"Deactivate\";");
			out.println("		}");
			out.println("		else if(m_val==\"RACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("alert_msg=\"Reactivate\";");
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
			out.println("						help_update_value_1();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_2();"); 
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
			
			// Modified by Thamali Jayatunga on 2009.10.14, Added function validate_rate.
			out.println("function validate_rate(obj,size){ ");
			out.println("   if(isNaN(obj.value) ) { ");
			out.println("   alert('You have typed an incorrect character as a number');");
			out.println("   obj.value='';");
			out.println("   obj.focus();");
			out.println("   }");
			out.println(" 	else {");
			out.println(" 	format_number(obj,size)  ;");
			out.println("   }");
			out.println("}");

			out.println("</script>"); 
		  	
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),makeRequest();\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Operation Process - Factoring Finance Interest Rate</td>"); 
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
			
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >");
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_START\" class=div_input>Start Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"hidden\" name=\"TXT_ST_DD\" maxlength=\"2\" size=\"2\"  value=\"01\" tabindex=\"1\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_MM\" maxlength=\"2\" size=\"2\" value=\""+m_mm+"\" tabindex=\"2\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_YY\" maxlength=\"4\" size=\"4\" value=\""+m_yy+"\" onblur=\"checkMonthLength(document.Form1.TXT_ST_DD,document.Form1.TXT_ST_MM,document.Form1.TXT_ST_YY),makeRequest()\"  tabindex=\"3\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

			out.println("<tr >");
			// Modified by Thamali Jayatunga on 2009.10.14, Added code onblur=validate_rate(this,3).
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INT_RATE\" class=div_input>Interest Rate (%)*</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_INTEREST_AMT\" maxlength=\"8\" size=\"8\" value=\"0.00\" onblur=\"validate_rate(this,3) \"   ></td>");
			out.println("<td width='15%' ></td>");
			out.println("<td width='40%' >");
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
