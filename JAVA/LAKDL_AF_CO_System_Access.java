
//Created By ns on 12-11-2010
// this will enable the system status to open and close

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_System_Access extends javax.servlet.http.HttpServlet { 

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
			
			String m_access_status  ="N";
		
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Maintenance - System Access </TITLE>");//Modified by Dineth on 29-04-2009 changed routing to routine 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			//out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			//out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			//out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");			

      out.println("function run_dayend() {");
			out.println("		if(confirm(\"Are you sure you want to process ?\")){ "); 
			out.println("document.Form1.chk_unblock.disabled=false;");
			out.println("document.Form1.chk_block.disabled=false;");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CO_Save_System_Access';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 		
			out.println("		}"); 		

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_System_Access?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 			
		
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Maintenance - System Access - \"+m_val;");//Modified by Dineth on 29-04-2009 changed routing to routine
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Maintenance - System Access \";");//Modified by Dineth on 29-04-2009 changed routing to routine
			out.println("}"); 
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_screen_status(m_val){"); 
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
		  out.println("		else{");  
			out.println("			document.Form1.hid_status.value=\"\";");  
			out.println("		}"); 
			out.println("}"); 
			
			/*
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
			*/
			
			
			out.println("function onLoad_eff_date(){");
			
			rs = stmt.executeQuery("SELECT ACCESS_STATUS FROM "+m_schema_name+".AF_CO_MAS_SYS_ACCESS ");
			if(rs.next()){
			m_access_status  = rs.getString(1);
			}
			
			
			if(m_access_status.equals("N")){
			out.println("document.Form1.chk_unblock.value='yes';");
			out.println("document.Form1.chk_unblock.checked=true;");
			out.println("document.Form1.chk_unblock.disabled=true;");
			}
			else{
			out.println("document.Form1.chk_block.value='yes';");
			out.println("document.Form1.chk_block.checked=true;");
			out.println("document.Form1.chk_block.disabled=true;");
			}
			
			
			out.println("}");
			
			
			out.println("function check_exec(obj,obj2){ ");
			out.println("if (obj.checked==true){");
			out.println("obj.checked=true;");
			out.println("obj.value='on';");	
			out.println("obj.disabled=true;");	
			out.println("obj2.checked=false;");
			out.println("obj2.value='off';");	
			out.println("obj2.disabled=false;");	
			out.println("}");
			out.println("}");

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"onLoad_eff_date()\">"); //get_system_date(),onLoad_eff_date();
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Maintenance - System Access </td>"); //Modified by Dineth on 29-04-2009 changed routing to routine 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
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
			
			//Modified by Dineth on 29-04-2009 changed the order
			/***
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>Current Date</DIV></td>"); 
			out.println(" <TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" disabled>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" disabled>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\"  disabled>");
			out.println("<td width='*%' ></td>");
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_EFF_DATE'  class=div_input>Last Processed Date</DIV></td>"); 
			out.println(" <TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_EFF_DATE_DD maxlength=\"2\" size=\"2\" disabled>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_DATE_MM  maxlength=\"2\" size=\"2\" disabled>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_DATE_YY maxlength=\"4\" size=\"4\"  disabled>");	
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
			out.println("</tr>");
			//End by Dineth on 29-04-2009
			
			
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PRO_DATE'  class=div_input>Date to be Processed</DIV></td>"); 
			out.println(" <TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_PRO_DATE_DD maxlength=\"2\" size=\"2\" disabled>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_PRO_DATE_MM  maxlength=\"2\" size=\"2\" disabled>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_PRO_DATE_YY maxlength=\"4\" size=\"4\" disabled>&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' name='BUT_VIEW' value=\"Run Day End Routine\" onClick=\"run_dayend()\" style={width=110px}>");//Modified by Dineth on 29-04-2009 changed routing to routine	
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
			out.println("</tr>");
			**/
		
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV class=div_input>System Transactions Block</DIV></td>"); 
			out.println("<td width='15%' ><DIV class=div_input>Un Block</DIV></td>"); 
			out.println("<td width='15%' ><DIV class=div_input>Block</DIV></td>"); 
			out.println("<td width='*%' ></td>");
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV class=div_input>&nbsp;</DIV></td>"); 
			out.println("<td width=\"10%\" ><input type='checkbox' name='chk_unblock'  value='off' onclick='check_exec(document.Form1.chk_unblock,document.Form1.chk_block)'></td>");					
			out.println("<td width=\"10%\" ><input type='checkbox' name='chk_block'   value='off' onclick='check_exec(document.Form1.chk_block,document.Form1.chk_unblock)'></td>");
			out.println("<td width=\"*%\"  ><input class='but_input' type='button' name='BUT_VIEW' value=\"Process\" onClick=\"run_dayend()\" style=\"{width=110px}\" </td>");
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
