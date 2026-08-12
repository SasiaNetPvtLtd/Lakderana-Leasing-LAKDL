/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
// CREATED BY SANJEEWA ON 2010-07-14
// DISPLAY NAME INVOICE DETAIL REPORT
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_FA_OP_invoice_detail_report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_header_name=m_sn_methods.header_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Invoice Detail Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			out.println("var m_sav_msg='';");
						out.println("function get_vector(data_vec) {");
						out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
						out.println("			document.Form1.TXT_AS_AT_DATE_DD.value=data_vec[0];");
						out.println("			document.Form1.TXT_AS_AT_DATE_MM.value=data_vec[1];");
						out.println("			document.Form1.TXT_AS_AT_DATE_YY.value=data_vec[2];");
						out.println("			document.Form1.TXT_AS_AT_DATE_DD1.value=data_vec[0];");
						out.println("			document.Form1.TXT_AS_AT_DATE_MM1.value=data_vec[1];");
						out.println("			document.Form1.TXT_AS_AT_DATE_YY1.value=data_vec[2];");
						out.println("		}");
						out.println("}");
						
						out.println("function validate_date(){");
						out.println(" 	m_as_at_dd = document.Form1.TXT_AS_AT_DATE_DD.value ");
						out.println(" 	m_as_at_mm = document.Form1.TXT_AS_AT_DATE_MM.value ");
						out.println("		m_as_at_yy = document.Form1.TXT_AS_AT_DATE_YY.value ");
						
						out.println(" if(m_as_at_dd != '' && m_as_at_mm !='' && m_as_at_yy !=''  ) { ");
						out.println("    if(!checkMonthLength(document.Form1.TXT_AS_AT_DATE_DD,document.Form1.TXT_AS_AT_DATE_MM,document.Form1.TXT_AS_AT_DATE_YY)){  "); 
						out.println("     return false;"); 
						out.println("     }");
						out.println("    else {");
						//out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
						//out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
						out.println("      	  return true;"); 
						//out.println("  	  	 }");
						//out.println("        else "); 
						//out.println("         return false; "); 
						//out.println("     }");
						//out.println("			else {");
						//out.println("   		alert('To Date cannot be null ')");
						//out.println("   		return false;"); 
						//out.println("     }");
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
						
						out.println("function show_report() {");
						
						out.println(" 	m_as_at_dd = document.Form1.TXT_AS_AT_DATE_DD.value ");
						out.println(" 	m_as_at_mm = document.Form1.TXT_AS_AT_DATE_MM.value ");
						out.println("	m_as_at_yy = document.Form1.TXT_AS_AT_DATE_YY.value ");
						out.println(" 	m_as_at_dd1 = document.Form1.TXT_AS_AT_DATE_DD1.value ");
						out.println(" 	m_as_at_mm1 = document.Form1.TXT_AS_AT_DATE_MM1.value ");
						out.println("	m_as_at_yy1 = document.Form1.TXT_AS_AT_DATE_YY1.value ");
						out.println(" 	m_as_at_date = m_as_at_dd+\"-\"+m_as_at_mm+\"-\"+m_as_at_yy; ");
						out.println(" 	m_as_at_date1 = m_as_at_dd1+\"-\"+m_as_at_mm1+\"-\"+m_as_at_yy1; "); 	
						//out.println(" if(validate_data()){");
						out.println("		if(validate_date()) {");
						//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_client_daily_statement_display?chksql=LOAD_CLI_DAILY_REPORT&as_at_date=\"+m_as_at_date+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value;");
						out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_detail_report_display?as_at_date=\"+m_as_at_date+\"&as_at_date1=\"+m_as_at_date1+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value;");

						out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
						//out.println("   }");
						out.println("  }");
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
						out.println("     document.Form1.TXT_AS_AT_DATE_DD.value=v_date;");
						out.println("     document.Form1.TXT_AS_AT_DATE_MM.value=v_month;");
						out.println("     document.Form1.TXT_AS_AT_DATE_YY.value=val;");
					
						
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
						out.println("     document.Form1.TXT_AS_AT_DATE_DD1.value=v_date;");
						out.println("     document.Form1.TXT_AS_AT_DATE_MM1.value=v_month;");
						out.println("     document.Form1.TXT_AS_AT_DATE_YY1.value=val;");
				        //out.println("			date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
						//out.println("			document.Form1.hid_to_date.value=date2");
						out.println(" }");
						out.println("}");

			
						out.println("function get_vector_normal(m_data){");
						out.println("		invoice_detail_data.innerHTML=m_data;");
						out.println("}");
						out.println("function load_lock(){	"); 
						//out.println("document.oncontextmenu=new Function(\"return false\");"); 
						out.println("}	"); 

						out.println("function clear_window(){	"); 
						out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
						out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_detail_report';"); 
						out.println("		}"); 
						out.println("}"); 
						out.println("function new_window(){	"); 
			
						out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_detail_report';"); 
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
						out.println("	help_box.innerHTML=\" Invoice Detail Report - \"+m_val;"); 
						out.println("}"); 

						out.println("function load_roll_out_value(){");
						out.println("	help_box.innerHTML=\" Invoice Detail Report \";"); 
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
						
						
						
			
						out.println("function clear(){");			
						out.println("if(document.Form1.hid_help_type.value==\"1\"){");
						out.println(" document.Form1.TXT_CLIENT_CODE.value=\"\";"); 
						out.println("}");
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
						out.println("	if(oBj.valout[1] !=\"Close\"){"); 
						out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
						out.println("	if(oBj.valout[1]!=\"Next\"){"); 
						out.println("	if(document.Form1.hid_help_type.value==\"1\"){"); 
						out.println("					help_update_value_assign_1();"); 
	  				out.println("					}"); 
						/*out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
						out.println("						help_update_value_assign_2();"); 
	  				out.println("					}"); 
						out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
						out.println("						help_update_value_assign_3();"); 
	  				out.println("					}"); 
						out.println("					if(document.Form1.hid_help_type.value==\"4\"){"); 
						out.println("						help_update_value_assign_4();"); 
	  				out.println("					}");*/ 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Detail Report </td>"); 
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
			out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_MM  maxlength=\"2\" size=\"2\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_YY maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>  ");	
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
			out.println("</tr >");
			out.println("</table>");  
			
			
			//sanjeewa
			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE1'  class=div_input><b>To Date</b></DIV></td>"); 
			out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_DD1 maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_MM1  maxlength=\"2\" size=\"2\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_YY1 maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>  ");	
			out.println("</td> ");
						
			out.println("<td width='*%' ></td>");
			out.println("</table>"); 
			//sanjeewa
			

			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input><b>Client Code</b></DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='50' style='width:100' onblur=\"help_update_client()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_client()\" ></td>"); 
			out.println("<td width='*%'><input class='but_input' type='button' name='BUT_VIEW_MAIN' value=\"View\" onClick=\"show_report()\"></td>"); 
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
			     	
			
			
			
						
			
			
			
