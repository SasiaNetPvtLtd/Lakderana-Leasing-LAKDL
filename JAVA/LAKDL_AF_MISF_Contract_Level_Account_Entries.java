// Created by Dineth Meemanage on 2008-09-16
// System LAKDL
// Display Name Contract Level Account Entries

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MISF_Contract_Level_Account_Entries extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_val;
	ServletOutputStream out = null;
	
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req);
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			m_chksql         = req.getParameter("chksql");
			
			if(m_chksql.trim().equals("main_page")){
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Contract Level Account Entries </TITLE>"); 
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
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("var date1='' ");
				out.println("var date2='' ");
				out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
				out.println("		v_date=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_date.length<2)");
				out.println("			v_date=0+v_date");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("			v_month=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_month.length<2)");
				out.println("			v_month=0+v_month");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_date;");
				out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_month;");
				out.println("     document.Form1.TXT_FROM_DATE_YY.value=val;");
				//out.println("			date1=v_date+'-'+v_month+'-'+val;");
				//out.println("			document.Form1.hid_from_date.value=date1");
				out.println("	}");
				out.println(" if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("		v_date=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_date.length<2)");
				out.println("			v_date=0+v_date");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("			v_month=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_month.length<2)");
				out.println("			v_month=0+v_month");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.TXT_TO_DATE_DD.value=v_date;");
				out.println("     document.Form1.TXT_TO_DATE_MM.value=v_month;");
				out.println("     document.Form1.TXT_TO_DATE_YY.value=val;");
				//out.println("			date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				//out.println("			document.Form1.hid_to_date.value=date2");
				out.println(" }");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				
				out.println("function validate_data(){"); 
				out.println(" if(document.Form1.TXT_CLIENT_CODE.value==\"\"){ ");
				out.println(" alert('Client Code cannot be empty'); ");
				out.println(" DIV_TXT_CLIENT_CODE.style.color='red';");
				out.println(" return false;");
				out.println(" }else{ ");
				out.println("	return true;");
				out.println(" }");
				out.println("}"); 			
				
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Level_Account_Entries?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				out.println("function new_window(){	"); 
				
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Level_Account_Entries?chksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Contract Level Account Entries - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Contract Level Account Entries \";"); 
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
				
				out.println("function help_update_client() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"N@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_1() {"); 
				out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function help_update_finance_no() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";");
				out.println("    if(document.Form1.TXT_CLIENT_CODE.value==''){");
				out.println("    m_sql = \"m_help_TXT_FINANCENO_sql2\";"); 
				out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
				out.println("    }else{ ");
				out.println("    m_sql = \"m_help_TXT_FINANCENO_sql1\";"); 
				out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
				out.println("		 } ");
				out.println("    HelpBox('1','10','0');"); 
				out.println("}");   
				
				out.println("function help_update_value_assign_2() {"); 
				out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function clear(){");			
				out.println("if(document.Form1.hid_help_type.value==\"1\"){");
				out.println(" document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
				out.println("}");
				out.println("else if(document.Form1.hid_help_type.value==\"2\"){");
				out.println(" document.Form1.TXT_FINANCE_NO.value=\"\";"); 
				out.println("}");
				out.println("}");
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
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
				out.println("					if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("						help_update_value_assign_4();"); 
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
				out.println("	else{");
				out.println("clear()");
				out.println("	}");
				out.println("	}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("clear()");
				out.println("	}	"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function show_con_level_account_report() {");
				
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" if(validate_data()){");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Level_Account_Entries?chksql=LOAD_CON_LEVEL_REPORT&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("   }");
				out.println("  }");
				out.println("}");
				
				
				
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Contract Level Account Entries </td>"); 
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
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
				out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>  ");	
				out.println("</td> ");
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
				out.println(" <TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" > <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");	
				out.println("</td> ");
				out.println("<td width='*%' ></td>");
				out.println("</table>");  
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input><b>Client Code*</b></DIV></td>"); 
				out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='50' style='width:100' onblur=\"help_update_client()\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_client()\" ></td>"); 
				out.println("<td width='*%' align='left' ></td>");
				out.println("</tr>"); 
				out.println("</table>");  
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input><B>Finance No</B></DIV></td>"); 
				out.println("<td width='17%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='10' style='width:100' onblur=\"help_update_finance_no()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_finance_no()\"></td>"); 
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_VIEW_MAIN' value=\"View\" onClick=\"show_con_level_account_report()\"></td>"); 
				
				out.println("</tr>"); 
				out.println("</table>");  
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
				
				
				
				
				
			}
			else if(m_chksql.trim().equals("LOAD_CON_LEVEL_REPORT")){
				String m_client_code=req.getParameter("client_code");
				String m_finance_no=req.getParameter("finance_no");
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_acc_type_code="";
				String m_acc_desc="";
				boolean mflag=true;
				double m_dr_tot=0;
				double m_cr_tot=0;
				
				
				
				//==================Start sql dineth
				rs=stmt.executeQuery(" SELECT "+
					" FN,CLIENT_CODE, "+
					" ACC_TYPE_CODE, "+
					" SUM(DECODE(DRCR_STATUS,'DR',AMT,0) ), "+
					" SUM(DECODE(DRCR_STATUS,'CR',AMT,0) ), "+
					" SUM(DECODE(DRCR_STATUS,'DR',AMT,AMT*-1) ) "+
					" FROM ( "+
					" SELECT "+
					" DRCR_STATUS, "+
					" TRNAMOUNT AMT, "+
					" DOCREFNO, "+
					" FINANCE_NO FN, "+
					" CLIENT_CODE, "+
					" ACC_TYPE_CODE, "+
					" TRANSACTION_CODE "+//ADD BY DINETH
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE A.DOCREFNO = B.FINANCE_NO AND "+
					" TRNDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')"+ //--AND ACC_TYPE_CODE='20002'
					" AND DOCREFNO NOT LIKE 'IN%' AND DOCREFNO NOT LIKE 'SP%'  AND DOCREFNO NOT LIKE 'SR%' "+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" UNION ALL "+
					" SELECT "+
					" DRCR_STATUS, "+
					" TRNAMOUNT AMT, "+
					" DOCREFNO, "+
					" FINANCE_NO FN, "+
					" CLIENT_CODE, "+
					" ACC_TYPE_CODE, "+
					" TRANSACTION_CODE "+//ADD BY DINETH
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE INVOICE_NO = DOCREFNO AND TRNDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//AND ACC_TYPE_CODE='20002' "+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" UNION ALL "+
					" SELECT "+
					" DRCR_STATUS, "+
					" APP_REC_AMOUNT AMT, "+
					" DOCREFNO, "+
					" FINANCE_NO FN, "+
					" B.CLIENT_CODE, "+
					" ACC_TYPE_CODE, "+
					" TRANSACTION_CODE "+//ADD BY DINETH
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
					" WHERE B.REC_NO = DOCREFNO AND TRNDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//AND ACC_TYPE_CODE='20002' "+
					" AND B.REC_NO=C.REC_NO AND STATUS NOT IN ('CAD') AND PROC_DESC <>'AF RECEIPT COLLECTION - OTHER' "+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" UNION ALL "+
					" SELECT "+
					" DRCR_STATUS, "+
					" BAL_TOBE_RECEIVE AMT, "+
					" DOCREFNO, "+
					" '-' FN,"+
					" CLIENT_CODE, "+
					" ACC_TYPE_CODE, "+
					" TRANSACTION_CODE "+//ADD BY DINETH
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
					" WHERE B.REC_NO = DOCREFNO AND TRNDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//AND ACC_TYPE_CODE='20002' "+
					" AND B.REC_NO=C.REC_NO AND STATUS NOT IN ('CAD') AND PROC_DESC <>'AF RECEIPT COLLECTION - OTHER' AND BAL_TOBE_RECEIVE>0"+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" ) WHERE FN LIKE '"+m_finance_no+"%' AND CLIENT_CODE ='"+m_client_code+"' "+
					" GROUP BY FN,CLIENT_CODE,ACC_TYPE_CODE");
				//==================End sql by dineth
				
				out.println("<HTML><HEAD><TITLE>Contract Level Account Entries </TITLE></HEAD>");
				out.println("<SCRIPT>");
				out.println("function show_account_details(acc_code,fin_no,m_from_date,m_to_date){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Level_Account_Entries?chksql=LOAD_CON_LEVEL_REPORT2&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&acc_code=\"+acc_code+\"&finance_no=\"+fin_no;");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Level_Account_Entries?chksql=LOAD_CON_LEVEL_REPORT2&acc_code=\"+acc_code+\"&finance_no=\"+fin_no;");
				
				out.println("    popupwin=window.open(m_url,'displayWindow2','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				
				out.println("}");
				out.println("</SCRIPT>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<br>");
				out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Contract Level Account Entries Report From "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<hr>");	
				out.println("<BR>");
				boolean	more=rs.next();
				if(!more){
					
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");	
					
				}					
				if(more){
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' ><DIV class=div_input><b>Account Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
					out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
					out.println("<td width='12%' align='right' ><DIV class=div_input><b>Credit</b></DIV></td>"); 
					out.println("</tr>"); 
					
				}
				while(more){
					m_acc_type_code=rs.getString(3);
					rs1=stmt1.executeQuery( "SELECT DISTINCT "+
						"  A.ACC_TYPE_CODE ACC_TYPE_CODE,"+
						"  A.ACC_TYPE_DESC ACC_TYPE_DESC "+
						" FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE A "+
						" WHERE A.ACC_TYPE_CODE='"+m_acc_type_code+"'");
					boolean more1=rs1.next();
					if(more1){
						
						m_acc_desc=rs1.getString(2);
					}
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					//m_dr_tot+=rs.getDouble(4);
					//m_cr_tot+=rs.getDouble(5);
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input >"+m_acc_type_code+"</td>");
					out.println("<td width='20%' class=div_input >"+m_acc_desc+"</td>");
					if(rs.getDouble(4)>rs.getDouble(5)){
						out.println("<td width='12%' class=div_input align='right' onClick=\"show_account_details('"+m_acc_type_code+"','"+m_finance_no+"','"+m_from_date+"','"+m_to_date+"')\" style='cursor:hand'><u>"+nf.format(rs.getDouble(4)-rs.getDouble(5))+"</u></td>");
						out.println("<td width='12%' class=div_input align='right' onClick=\"show_account_details('"+m_acc_type_code+"','"+m_finance_no+"','"+m_from_date+"','"+m_to_date+"')\" style='cursor:hand'><u>0.00</u></td>");
						m_dr_tot+=rs.getDouble(4)-rs.getDouble(5);
					}
					else if(rs.getDouble(5)>rs.getDouble(4)){
						out.println("<td width='12%' class=div_input align='right' onClick=\"show_account_details('"+m_acc_type_code+"','"+m_finance_no+"','"+m_from_date+"','"+m_to_date+"')\" style='cursor:hand'><u>0.00</u></td>");
						out.println("<td width='12%' class=div_input align='right' onClick=\"show_account_details('"+m_acc_type_code+"','"+m_finance_no+"','"+m_from_date+"','"+m_to_date+"')\" style='cursor:hand'><u>"+nf.format(rs.getDouble(5)-rs.getDouble(4))+"</u></td>");
						m_cr_tot+=rs.getDouble(5)-rs.getDouble(4);
					}
					else{
						out.println("<td width='12%' class=div_input align='right' onClick=\"show_account_details('"+m_acc_type_code+"','"+m_finance_no+"','"+m_from_date+"','"+m_to_date+"')\" style='cursor:hand'><u>0.00</u></td>");
						out.println("<td width='12%' class=div_input align='right' onClick=\"show_account_details('"+m_acc_type_code+"','"+m_finance_no+"','"+m_from_date+"','"+m_to_date+"')\" style='cursor:hand'><u>0.00</u></td>");
					}
					more=rs.next();
					
				}
				out.println("<table align='center' width='100%' class='table' >");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='33%' align='center'> <B>Total</B> </td>"); 
				
				
				
		
				
				out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot)+"</b></DIV></td>"); 
				out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(m_cr_tot)+"</b></DIV></td>"); 
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='33%' align='center' > <B>Difference</B> </td>"); 
				if(m_dr_tot-m_cr_tot >= 0){
					out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot-m_cr_tot)+"</b></DIV></td>"); 
				}
				else {
					out.println("<td width='12%' align='right'><DIV class=div_input><b>("+nf.format(m_cr_tot-m_dr_tot)+")</b></DIV></td>"); 
				}
				out.println("<td width='12%' align='right' ><DIV class=div_input><b>0.00</b></DIV></td>"); 
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.trim().equals("LOAD_CON_LEVEL_REPORT2")){
				//end trial balance
				boolean mflag=true;
				String m_acc_code_1=req.getParameter("acc_code");
				String m_fin_no_1=req.getParameter("finance_no");
				String m_from_date_1=req.getParameter("from_date");
				String m_to_date_1=req.getParameter("to_date");
				//out.println(m_acc_code_1);
				//out.println(m_fin_no_1);
				double m_cr_tot=0,m_dr_tot=0;
				rs2=stmt2.executeQuery(
					" SELECT DISTINCT "+
					"  NVL(P.TRANSACTION_CODE,'-') TRANSACTION_CODE, "+//1
					"  Q.DESCRIPTION DESCRIPTION, "+//2
					"	 DECODE(P.DRCR_STATUS,'CR','Credit','DR','Debit') DRCR_STATUS, "+
					//"  DECODE(P.DRCR_STATUS,'DR',AMT,0), "+
					//"  DECODE(P.DRCR_STATUS,'CR',AMT,0), "+
					" AMT, "+
					" DOCREFNO, "+
					"  P.FN "+
					"  FROM( "+
					" SELECT "+
					" TRANSACTION_CODE, "+
					" DRCR_STATUS, "+
					" TRNAMOUNT AMT, "+
					" DOCREFNO, "+
					" FINANCE_NO FN, "+
					" CLIENT_CODE, "+
					" ACC_TYPE_CODE "+
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE A.DOCREFNO = B.FINANCE_NO "+
					" AND TRNDATE<=TO_DATE('"+m_to_date_1+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date_1+"','DD-MM-YYYY')"+ //--
					" AND ACC_TYPE_CODE='"+m_acc_code_1+"' "+
					
					" AND DOCREFNO NOT LIKE 'IN%' AND DOCREFNO NOT LIKE 'SP%'  AND DOCREFNO NOT LIKE 'SR%' "+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" UNION ALL "+
					" SELECT "+
					" TRANSACTION_CODE, "+
					" DRCR_STATUS, "+
					" TRNAMOUNT AMT, "+
					" DOCREFNO, "+
					" FINANCE_NO FN, "+
					" CLIENT_CODE, "+
					" ACC_TYPE_CODE "+
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE INVOICE_NO = DOCREFNO "+
					" AND TRNDATE<=TO_DATE('"+m_to_date_1+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date_1+"','DD-MM-YYYY') "+//
					"AND ACC_TYPE_CODE='"+m_acc_code_1+"' "+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" UNION ALL "+
					" SELECT "+
					" TRANSACTION_CODE, "+
					" DRCR_STATUS, "+
					" APP_REC_AMOUNT AMT, "+
					" DOCREFNO, "+
					" FINANCE_NO FN, "+
					" B.CLIENT_CODE, "+
					" ACC_TYPE_CODE "+
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
					" WHERE B.REC_NO = DOCREFNO "+ 
					"	AND TRNDATE<=TO_DATE('"+m_to_date_1+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date_1+"','DD-MM-YYYY') "+//
					"AND ACC_TYPE_CODE='"+m_acc_code_1+"' "+
					
					" AND B.REC_NO=C.REC_NO AND STATUS NOT IN ('CAD') AND PROC_DESC <>'AF RECEIPT COLLECTION - OTHER' "+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" UNION ALL "+
					" SELECT "+
					" TRANSACTION_CODE, "+
					" DRCR_STATUS, "+
					" BAL_TOBE_RECEIVE AMT, "+
					" DOCREFNO, "+
					" '-' FN,"+
					" CLIENT_CODE, "+
					" ACC_TYPE_CODE "+
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
					" WHERE B.REC_NO = DOCREFNO "+
					" AND TRNDATE<=TO_DATE('"+m_to_date_1+"','DD-MM-YYYY') AND TRNDATE>=TO_DATE('"+m_from_date_1+"','DD-MM-YYYY') "+//
					" AND ACC_TYPE_CODE='"+m_acc_code_1+"' "+
					" AND B.REC_NO=C.REC_NO AND STATUS NOT IN ('CAD') AND PROC_DESC <>'AF RECEIPT COLLECTION - OTHER' AND BAL_TOBE_RECEIVE>0"+
					" AND TRANSACTION_CODE NOT IN('100020','100021','100022') "+//ADD BY DINETH
					" )P,"+m_schema_name+".CO_FN_MAS_TRANSACTION_CODE Q"+
					" WHERE P.TRANSACTION_CODE=Q.TRANSACTION_CODE AND P.FN='"+m_fin_no_1+"'");
				
				/*rs3= stmt3.executeQuery(" SELECT DISTINCT "+
				"  NVL(A.TRANSACTION_CODE,'-') TRANSACTION_CODE, "+//1
				"  B.DESCRIPTION DESCRIPTION, "+//2
				"	 DECODE(A.DRCR_STATUS,'CR','Credit','DR','Debit') DRCR_STATUS, "+//3
				"	 SUM(A.TRNAMOUNT) TRNAMOUNT "+//4
			" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A, "+m_schema_name+".CO_FN_MAS_TRANSACTION_CODE B  "+
			" WHERE TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
			" TO_DATE(TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" AND A.TRANSACTION_CODE=B.TRANSACTION_CODE(+) "+
			" AND A.DIVISION_CODE LIKE UPPER('"+m_div_code+"%') "+
			" AND A.PRODUCT_CODE LIKE UPPER('"+m_prod_code+"%') "+
			" AND A.ACC_TYPE_CODE LIKE UPPER('"+m_acc_type_code+"%') "+
			//" AND A.DRCR_STATUS='"+m_cr_dr_type+"' "+
			" AND A.ACC_TYPE_CODE='"+m_acc_type_code+"' "+
			" GROUP BY A.TRANSACTION_CODE,DESCRIPTION,DRCR_STATUS ");*/
				out.println("<HTML><HEAD><TITLE>Contract Level Account Entries </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				//out.println("<TR><TD align='Center' ><B> Trial Balance Report </B></TD></TR>");
				out.println("<TR><TD align='Center' ><B> Contract Level Account Entries</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<hr>");	
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				//out.println("<TR><TD align='Center' ><B> "+m_acc_desc+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<hr>");	
				out.println("<BR>");	
				boolean more =rs2.next();
				
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' ><DIV class=div_input><b>Transaction Code</b></DIV></td>"); 
					out.println("<td width='20%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input><b>Document Ref No</b></DIV></td>"); 
					out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
					out.println("<td width='12%' align='right' ><DIV class=div_input><b>Credit</b></DIV></td>"); 
					out.println("</tr>"); 
				}
				
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input >"+rs2.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs2.getString(2)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs2.getString(5)+"</td>");
					if(rs2.getString(3).equals("Debit")){
						m_dr_tot=m_dr_tot+rs2.getDouble(4);
						out.println("<td width='12%' align='right' class=div_input >"+nf.format(rs2.getDouble(4))+"</td>");
					}
					else {
						out.println("<td width='12%' align='right' ><DIV class=div_input>0.00</DIV></td>"); 
					}
					if(rs2.getString(3).equals("Credit")){
						m_cr_tot=m_cr_tot+rs2.getDouble(4);
						out.println("<td width='12%' align='right' class=div_input >"+nf.format(rs2.getDouble(4))+"</td>");
					}
					else {
						out.println("<td width='12%' align='right' ><DIV class=div_input>0.00</DIV></td>"); 
					}
					out.println("</tr>");
					more = rs2.next();
					
				}	
				out.println("</table>");
				out.println("<br>"); 
				out.println("<table align='center' width='100%' class='table' >");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='45%' align='center'> <B>Total</B> </td>"); 
				out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot)+"</b></DIV></td>"); 
				out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(m_cr_tot)+"</b></DIV></td>"); 
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='45%' align='center' > <B>Difference</B> </td>"); 
				if(m_dr_tot-m_cr_tot >= 0){
					out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot-m_cr_tot)+"</b></DIV></td>"); 
				}
				else {
					out.println("<td width='12%' align='right'><DIV class=div_input><b>("+nf.format((m_dr_tot-m_cr_tot)*-1)+")</b></DIV></td>"); 
				}
				out.println("<td width='12%' align='right' ><DIV class=div_input><b>0.00</b></DIV></td>"); 
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");   
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
			}
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}

