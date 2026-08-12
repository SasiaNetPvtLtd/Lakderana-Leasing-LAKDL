//--
//SCREEN NAME: VAT Recoverable Report
//CREATED BY : SH
//DATE/TIME  : 11/06/2007
//NOTES			 :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Int_Invoiced_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url= m_sn_methods.html_client_url.trim(); 
			String m_class_url      = m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_client_name    = m_sn_methods.client_name.trim();
			String m_schema_name    = m_sn_methods.schema_name;
			String header_name      = m_sn_methods.header_name;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			conn = m_sn_methods.met_user_validate(req); 
			PreparedStatement pstmt;
			stmt=conn.createStatement();
			
      m_chksql=req.getParameter("chksql");
			out = res.getOutputStream();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			String m_fin_code,m_invoice;
			
			

		if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Trial Balance Report </TITLE>"); 
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
			
			out.println("function show_trail_balance_report() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=LOAD_TRAIL_BAL_REPORT_LEVEL_1&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&div_code=\"+document.Form1.TXT_DIVISION_CODE.value+\"&prod_code=\"+document.Form1.TXT_PRODUCT_CODE.value+\"&acc_type_code=\"+document.Form1.TXT_ACC_CODE.value;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("}");
			
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
			out.println("	return true;"); 
			out.println("}"); 			


			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report';"); 
			out.println("		}"); 
			out.println("}"); 
			out.println("function new_window(){	"); 
			
			out.println("	window.location.href='"+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report';"); 
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
			out.println("	help_box.innerHTML=\"VAT Recoverable Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"VAT Recoverable Report \";"); 
			out.println("}"); 
			   
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_sys_date\";");
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
			out.println("	}"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
		  out.println("function show_trail_balance_report() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=get_VAT_Payable&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"\";");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("}");
			
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
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>VAT Recoverable Report </td>"); 
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
			out.println("<td width='*%' ><input class='but_input' type='button' name='BUT_VIEW_MAIN' value=\"View\" onClick=\"show_trail_balance_report()\"></td>");
			out.println("</tr> ");
			out.println("</table>");  
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>");
			/*
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>  VAT Recoverable Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_cnt=0");
			out.println("var m_chk=0");
			out.println("var b_flag=0;");
				
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			out.println(" ");
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_no_val.value==\"0\"){");
			out.println("alert('No records')");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_st.value='T'");
			out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=request_details&m_val=\"+document.Form1.hid_status.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_client_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_PRO_display_security_and_marketing_file\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" VAT Recoverable Report  \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" VAT Recoverable Report  \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"Request\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
			out.println("document.Form1.hid_save.value=\"Return\";"); 
			out.println("document.Form1.hid_status.value=\"Return\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

      out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){");
			out.println(" document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"100\"){ ");
			out.println(" document.Form1.TXT_MKT_OFFICER.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"101\"){ ");
			out.println(" document.Form1.TXT_SUP_NAME.value=\"\";");
			out.println("} ");
			out.println("} ");
			out.println("");
			
			out.println("function clear_screen(){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("request_details.innerHTML = ''; ");
			out.println("}"); 

			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("}");

     out.println("function Generate_Report() {");
		 out.println(" 	m_from_dd = document.Form1.VAL_DAY.value ");
		 out.println(" 	m_from_mm = document.Form1.VAL_MONTH.value ");
		 out.println(" 	m_from_yy = document.Form1.VAL_YEAR.value ");
		 out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
		 //out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=Report&from_date="+m_from_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"");			
     out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=Report&from_date=\"+m_from_date+\"&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
      
     out.println("function get_invoice() {");
		 out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=get_invoice&from_date=&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
     
     out.println("function get_receipt() {");
		 out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=get_receipt&from_date=&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
     
		 out.println("function get_debtor() {");
		 out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Int_Invoiced_Report?chksql=get_debtor&from_date=&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
     	
//______________________________________________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value(' ')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>  Rental Details Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
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

			
			
			rs = stmt.executeQuery ("SELECT SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)), "+
			                        "       "+m_schema_name+".AF_CO_GET_INVO_OUTSTAND_BAL(TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
															"       "+m_schema_name+".AF_CO_GET_REC_UNALLO_BAL(TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
														  "FROM   "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT "+
														  "WHERE  ACC_TYPE_CODE='20002' AND "+
														  "       ENTDATE >='12-JUN-2007' AND "+  
															"       TRNDATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
								
					boolean more1 = rs.next();
						
			if (more1){
      			
	      out.println("<tr class=pdn_txtpos2>"); 
			  out.println("<td width='20%' class='txt_report_column2'>Outstanding Invoices</td>");
				out.println("<td width='20%' class='txt_report_column2'>Unallocated Rceipts</td>");
				out.println("<td width='20%' class='txt_report_column2'>Debtor Account Balance</td>");
				out.println("</tr>");
			  
				out.println("<tr>");
				out.println("<td onclick=get_invoice() class='txt_report_column2' style= cursor:hand; ><u>"+nf.format(rs.getDouble(2))+"</u></td>");
				out.println("<td onclick=get_receipt() class='txt_report_column2' style= cursor:hand; ><u>"+nf.format(rs.getDouble(3))+"</u></td>");
				
				if(rs.getDouble(1)>=0){ 
				  out.println("<td onclick=get_debtor() class='txt_report_column2' style= cursor:hand; ><u> "+nf.format(rs.getDouble(1))+"</u></td>");
				}else{
				  out.println("<td onclick=get_debtor() class='txt_report_column2' style= cursor:hand; ><u> ("+nf.format((rs.getDouble(1)*-1))+")</u></td>");
				}
				out.println("</tr>");
			
			  
			}
			out.println("</table>");
			 
			out.println("<br>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=request_details></div></td></tr></table>");
			
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			*/
		}
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	



else if(m_chksql.equals("get_VAT_Payable")){

String m_to_date   = req.getParameter("to_date");
String m_from_date = req.getParameter("from_date");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>VAT Recoverable Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>VAT Recoverable- Invoices </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			/*rs =	stmt.executeQuery(" SELECT INVOICE_NO,TRIM(FINANCE_NO),TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
															"        TOTAL_AMOUNT, SETTELE_AMOUNT, BALANCE_TO_BE_RECEIVED "+
															" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
															" WHERE  ACTIVE_STATUS='Y' AND "+ 
															"        ENT_DATE >='12-JUN-2007' AND "+  
															"        BALANCE_TO_BE_RECEIVED>0  AND "+ 
															"        VALUE_DATE <= TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
				*/
				rs = stmt.executeQuery ("SELECT SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)), "+
			                        "       "+m_schema_name+".AF_CO_GET_INVO_OUTSTAND_BAL(TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
															"       "+m_schema_name+".AF_CO_GET_REC_UNALLO_BAL(TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
														  "FROM   "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT "+
														  "WHERE  ACC_TYPE_CODE='20001' AND DRCR_STATUS='DR' AND "+
														  "       TRNDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
															"       TRNDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Invoice No</td>"); 
			out.println("<td width='20%' class='txt_report_column'>Finance No</td>");
			out.println("<td width='15%' class='txt_report_column'>Value Date</td>");
			out.println("<td width='15%' class='txt_report_column2'>Total Amount</td>");
			
			out.println("<td width='15%' class='txt_report_column2' >Settled Amount</td>"); 
			out.println("<td width='15%' class='txt_report_column2' >Balance Amount</td>"); 
			out.println("</tr >"); 
			//out.println("</table>");
			
																			
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; onClick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; onClick=show_finance_detail_drill('"+rs.getString(2)+"')><U>"+rs.getString(2)+"</U></td>"); 
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(3)+"</td>");
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("</tr >"); 	
			
			tot_retal=tot_retal + rs.getDouble(4);
			tot_capital=tot_capital + rs.getDouble(5);
			tot_interest=tot_interest + rs.getDouble(6);
			
			more = rs.next();
			
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_retal)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_capital)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_interest)+"</B></td>"); 
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	


else if(m_chksql.equals("get_receipt")){

String m_appl_no   = req.getParameter("appl_no");
String m_from_date = req.getParameter("from_date");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>VAT Recoverable Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>VAT Recoverable- Invoices </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			rs =	stmt.executeQuery(" SELECT A.REC_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),A.REC_AMOUNT, "+
			                        "        B.ALLOCATED_AMOUNT,B.BAL_TOBE_RECEIVE "+
                              " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+
															"        "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
															" WHERE  A.REC_NO=B.REC_NO  AND "+
															"        A.ENT_DATE >='12-JUN-2007' AND "+  
															"        STATUS<>'C' AND "+
															"        A.EFF_VALDATE<= TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
																			
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Receipt No</td>"); 
			out.println("<td width='15%' class='txt_report_column'>Value Date</td>");
			out.println("<td width='15%' class='txt_report_column2'>Total Amount</td>");
			
			out.println("<td width='15%' class='txt_report_column2' >Allocated Amount</td>"); 
			out.println("<td width='15%' class='txt_report_column2' >Balance Amount</td>"); 
			out.println("</tr >"); 
			//out.println("</table>");
			
																			
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style=cursor:hand; onClick=show_settle_receipt_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(2)+"</td>");
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("</tr >"); 	
			
			tot_retal=tot_retal + rs.getDouble(3);
			tot_capital=tot_capital + rs.getDouble(4);
			tot_interest=tot_interest + rs.getDouble(5);
			
			more = rs.next();
			
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_retal)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_capital)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_interest)+"</B></td>"); 
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

else if(m_chksql.equals("get_debtor")){

String m_appl_no   = req.getParameter("appl_no");
String m_from_date = req.getParameter("from_date");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>VAT Recoverable Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
       
			
				
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>VAT Recoverable- Debtor </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			rs =	stmt.executeQuery(" SELECT A.DOCREFNO,TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),A.DRCR_STATUS,A.TRNAMOUNT "+
															" FROM   "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+ 
															" WHERE  ACC_TYPE_CODE='20002' AND "+
															"        A.ENTDATE >='12-JUN-2007' AND "+  
															"        TRNDATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
																			
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Reference No</td>"); 
			out.println("<td width='15%' class='txt_report_column'>Value Date</td>");
			out.println("<td width='15%' class='txt_report_column'>Dr/Cr</td>");
			
			out.println("<td width='15%' class='txt_report_column2' >Amount</td>"); 
			out.println("</tr >"); 
			//out.println("</table>");
			
																			
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style=cursor:hand; onClick=call_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(2)+"</td>");
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(3)+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("</tr >"); 	
			if(rs.getString(3).equals("DR")){
			  tot_capital=tot_capital + rs.getDouble(4);
		  }else{
			  tot_capital=tot_capital - rs.getDouble(4);
			}
			more = rs.next();
			
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			if(tot_capital>=0){
			 out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_capital)+"</B></td>"); 
			}else{
			 out.println("<td class='txt_report_data' align='right'><B>("+nf.format(tot_capital*-1)+")</B></td>"); 
			}
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	
		
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		    if(rs!=null)  {try{rs.close();  }catch(Exception e){}}
				if(stmt!=null){try{stmt.close();}catch(Exception e){}}
				if(conn!=null){try{conn.close();}catch(Exception e){}}
				if(out!=null) {try{out.close(); }catch(Exception e){}}
		}
	}
}
