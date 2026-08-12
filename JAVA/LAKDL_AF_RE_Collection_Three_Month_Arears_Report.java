import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Three_Month_Arears_Report extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2,rs3;
	
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
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
			
			out = res.getOutputStream(); 
			stmt=conn.createStatement();
			
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			
			if(m_chksql.equals("run_report")){ 
			
			// String m_from_date  = req.getParameter("from_date");
			 String m_to_date    = req.getParameter("date");

								
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_CB_AGE_ANALYSIS_RPT(:1,:2);END;");
				callstmt1.setString(1,m_to_date);
				callstmt1.setString(2,m_username);
				//callstmt1.setString(3,m_username);
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}

				
				
			else if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Accounting - Arrears For Three Months Or More Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			
				out.println("var b_flag=0;");
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				out.println("function run_report() {");
				out.println("alert('sdf');");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=run_report&date=\"+m_date;"); 
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				
				/*out.println("function run_report() {");
				out.println("	if ((document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\")  && (document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\") ) {");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=run_report&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");	
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				*/
				
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
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=print_report_new&date=\"+m_date;");	
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
        
				
				/*out.println("function print_report2(){");
				out.println("	clearTimeout(timerID);");
				out.println("	m_table.innerHTML=\"\";");
				out.println("	if((document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\")  && (document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\") ) {");
				out.println(" m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println(" m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=print_report_new&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");	
				out.println(" window.open(m_url);");
				out.println("	}");
				out.println("}");
			  */
				
			     
			out.println("var m_sav_msg='';");
			
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.VAL_DAY.value=data_vec[0];");
			out.println("			document.Form1.VAL_MONTH.value=data_vec[1];");
			out.println("			document.Form1.VAL_YEAR.value=data_vec[2];");
			out.println("		}");
			out.println("}");
			
		
			/*out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");
      */
			 
			//To validate from date & to date
			out.println("function validate_date(){");
			out.println(" 	m_to_dd = document.Form1.VAL_DAY.value ");
			out.println(" 	m_to_mm = document.Form1.VAL_MONTH.value ");
			out.println(" 	m_to_yy = document.Form1.VAL_YEAR.value ");
      out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)){  "); 
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
			
	
			
			out.println("function makeRequest_detail() {");
			out.println(" 	m_to_dd = document.Form1.VAL_DAY.value ");
			out.println(" 	m_to_mm = document.Form1.VAL_MONTH.value ");
			out.println(" 	m_to_yy = document.Form1.VAL_YEAR.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=invoice_analysis&m_date=\"+m_to_date+\"&order_by=ENT_DATE&sort_by=ASC\";");
			//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=main_page';"); 
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
			out.println("	help_box.innerHTML=\" Accounting - Arrears For Three Months Or More Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Accounting - Arrears For Three Months Or More Report \";"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Accounting - Arrears For Three Months Or More Report </td>"); 
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
			
			
			out.println("<table class='table' width='100%' >"); 
			out.println("<tr >"); 
			// Modified by Thamali Jayatunga on 2009.10.14, Added onblur event.
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>Date As At*</DIV></td>"); 
			out.println(" <TD WIDTH=\"60%\"><input class=\"txt_input5\" type=\"text\" name=VAL_DAY maxlength=\"2\" size=\"2\" onBlur=\"checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=VAL_MONTH  maxlength=\"2\" size=\"2\" onBlur=\"checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=VAL_YEAR maxlength=\"4\" size=\"4\" onBlur=\"checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)\" >");
			//out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"makeRequest_detail()\">");	
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\" style=\"{width:110px;}\" onClick=\"print_report2()\">");	
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"Run Report\" style=\"{width:110px;}\" onClick=\"run_report()\">");	
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
			out.println("</table>");  
			
						
			/*out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>Date As At*</b></DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_FROM_DATE'  class=div_input>From Date</b></DIV></td>"); 
			out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  >");	
			out.println("</td> "); 
			out.println("<td width='10%' ></td>");
			out.println("<td width='10%' ><DIV id='DIV_TXT_TO_DATE'  class=div_input>To Date</b></DIV></td>"); 
			out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" > ");
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\" style=\"{width:110px;}\" onClick=\"print_report2()\">");	
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"Run Report\" style=\"{width:110px;}\" onClick=\"run_report()\">");	
			out.println("</td> ");
			out.println("</table>");  
			*/
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
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
			
			
			else if (m_chksql.trim().equals("invoice_analysis")) {
			
			String m_date       = req.getParameter("m_date");
			
						      
      String Sql_arrears=" SELECT "+
			                   " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('0','"+m_date+"'), "+ //1
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('7','"+m_date+"'), "+ //2
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('1','"+m_date+"'), "+ //3
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('6','"+m_date+"'), "+ //4
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('2','"+m_date+"'), "+ //5
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('5','"+m_date+"'), "+ //6
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('3','"+m_date+"'), "+ //7
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('4','"+m_date+"'), "+ //8
												 " ("+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('0','"+m_date+"') + "+
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('1','"+m_date+"')+ "+
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('2','"+m_date+"') + "+
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('3','"+m_date+"')), "+ //9
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('7','"+m_date+"') + "+
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('6','"+m_date+"')+ "+
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('5','"+m_date+"') + "+
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('4','"+m_date+"'), "+ //10
													
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('8','"+m_date+"'), "+ //11 
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('15','"+m_date+"'), "+ //12 
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('9','"+m_date+"'), "+ //13
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('14','"+m_date+"'), "+ //14
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('10','"+m_date+"'), "+ //15
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('13','"+m_date+"'), "+ //16
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('11','"+m_date+"'), "+ //17
												 " "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('12','"+m_date+"'), "+ //18
													
												" ("+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('8','"+m_date+"')+ "+
												" "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('9','"+m_date+"')+ "+
												" "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('10','"+m_date+"')+ "+
												" "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('11','"+m_date+"')), "+ //19
												 
												" ("+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('12','"+m_date+"')+ "+
												" "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('13','"+m_date+"')+ "+
												" "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('14','"+m_date+"')+ "+
												" "+m_schema_name+".AF_CO_GET_OVR_THREE_MON_ARR('15','"+m_date+"')) "+ //20
													
													
													" FROM DUAL ";
													
			rs = stmt.executeQuery(Sql_arrears);
			
			boolean more = rs.next();
			
			
			
			 
				String Comp_Name = " SELECT COMPANY_NAME, "+
				       " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD')||' '|| "+
							 " INITCAP(TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
							 " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'YYYY') "+
							 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
      rs1 = stmt1.executeQuery(Comp_Name);
			
			boolean more1 = rs1.next();			
			
					
				
			  out.println("<HTML>"); 
			  out.println("<HEAD>"); 
			  out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			  out.println("</HEAD>"); 
			  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
			
			  out.println("function get_detail(val){");
				//out.println("alert('qqqqqqqqq=='+val);"); 
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=Load_Details&State=\"+val+\"&m_date="+m_date+" \";");
			  //out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
		  	out.println("    popupwin=window.open(m_url,'displayWindow2','left=40,top=300,width=1000,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				
				
				out.println("}"); 
							
			
			
			  out.println("</script>"); 
			  out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='1' width='100%' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASING ACT NO 56 OF 2000");
				out.println("<BR><b>FINANCE LEASING (PROVISION FOR BAD DOUBTFUL ACCOMMODATIONS) DIRECTION NO 2 OF 2006</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				
/*				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>OPERATING LEASES IN ARREARS FOR THREE MONTHS OR MORE</td>");
				out.println("</tr>");
				out.println("</table>");
				
*/
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>AGE ANALYSIS OF ACCOMMODATIONS IN ARREARS FOR THREE MONTHS OR MORE</td>");
				out.println("</tr>");
				out.println("</table>");

				

				
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Format No</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >SNBFI/FL/02/04</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Name of RFLE</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' ><b>"+rs1.getString(1)+"</b></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >As at Quarter Ended</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >"+rs1.getString(2)+"</td>");
				out.println("</tr>");
				out.println("</table>");
				
			  out.println("<br>");
				
        if(!more){
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>No Records.</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				} else{
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor='black' >");
				out.println("<tr > ");
				out.println("<td width=\"3%\" rowspan=\"2\">Type of Advances</td>");
				out.println("<td colspan=\"10\"><div align=\"center\"><b>AGE ANALYSIS </div></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td colspan=\"2\" align=\"center\">3 and upto 6 months </td>");
				out.println("<td colspan=\"2\" align=\"center\">6 and upto 12 months </td>");
				out.println("<td colspan=\"2\" align=\"center\">12 and upto 18 months </td>");
				out.println("<td colspan=\"2\" align=\"center\">Over 18 Months </td>");
				out.println("<td colspan=\"2\" align=\"center\">Total</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"10%\">&nbsp;</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure </td>");
				out.println("</tr>");
				out.println("<tr style=\"cursor:hand\" onClick=\"get_detail('1')\">");
				out.println("<td align=\"center\" >&nbsp; Finance Lease & HP</td>");
				out.println("<td align=\"center\">"+rs.getString(7)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(8))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(5)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(6))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(3)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(4))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(1)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(2))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(9)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(10))+"</td>");
				out.println("</tr>");
				out.println("<tr style=\"cursor:hand\" onClick=\"get_detail('2')\">");
				out.println("<td align=\"center\" >&nbsp; Operation Lease</td>");
				out.println("<td align=\"center\">"+rs.getString(17)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(18))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(15)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(16))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(13)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(14))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(11)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(12))+"</td>");
				out.println("<td align=\"center\">"+rs.getString(19)+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(20))+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>&nbsp;</td>");
				out.println("<td align=\"center\">"+(rs.getInt(7)+rs.getInt(17))+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(8)+rs.getDouble(18))+"</td>");
				out.println("<td align=\"center\">"+(rs.getInt(5)+rs.getInt(15))+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(6)+rs.getDouble(16))+"</td>");
				out.println("<td align=\"center\">"+(rs.getInt(3)+rs.getInt(13))+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(4)+rs.getDouble(14))+"</td>");
				out.println("<td align=\"center\">"+(rs.getInt(1)+rs.getInt(11))+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(2)+rs.getDouble(12))+"</td>");
				out.println("<td align=\"center\">"+(rs.getInt(9)+rs.getInt(19))+"</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(10)+rs.getDouble(20))+"</td>");
				out.println("</tr>");
				out.println("</table>");
				}
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");
				
					
			}
			else if(m_chksql.equals("drill_down_details")){
			
			String m_txt_type = req.getParameter("txt_type");
			int m_age      = Integer.parseInt(req.getParameter("age"));
						
		if(m_age ==3){
			 rs=stmt.executeQuery(			
		   " SELECT "+
		   " a.finance_no, "+
			 " a.client_code, "+
       " a.client_full_name, "+
			 " nvl(a.age,0)  ,"+
			 " (nvl(a.total_amount,0) - nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) + "+
			 " (nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0)) "+
			 "  - (nvl(a.nibsm,0) + nvl(a.ami,0) + nvl(a.interest_suspense,0))   "+
			 //" (nvl(a.total_amount,0) - nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) + "+
			 //" (nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0)) "+
				
		   " FROM "+m_schema_name+".af_re_tbd_cb_age_analysis a "+
			 " WHERE  ent_user='"+m_username+"'  "+	
			 " AND  floor(nvl(a.age,0))  >= 3 "+
			 " AND  floor(nvl(a.age,0)) < 6 "+
			 " AND a.transaction_type='"+m_txt_type+"' ORDER BY a.client_code ");
				
    }
		else if(m_age ==6){
			rs=stmt.executeQuery(			
		   " SELECT "+
		   " a.finance_no, "+
			 " a.client_code, "+
       " a.client_full_name, "+
			 " nvl(a.age,0)  ,"+
			 " (nvl(a.total_amount,0) - nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) + "+
			 " (nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0)) "+
			 "  - (nvl(a.nibsm,0) + nvl(a.ami,0) + nvl(a.interest_suspense,0))   "+
		   " FROM "+m_schema_name+".af_re_tbd_cb_age_analysis a "+
			 " WHERE  ent_user='"+m_username+"'  "+	
			 " AND  floor(nvl(a.age,0))  >= 6 "+
			 " AND  floor(nvl(a.age,0)) < 12 "+
			 " AND a.transaction_type='"+m_txt_type+"' ORDER BY a.client_code ");   
		}
		
		else if(m_age ==12){
		
				rs=stmt.executeQuery(			
		   " SELECT "+
		   " a.finance_no, "+
			 " a.client_code, "+
       " a.client_full_name, "+
			 " nvl(a.age,0)  ,"+
			 " (nvl(a.total_amount,0) - nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) + "+
			 " (nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0)) "+
			 "  - (nvl(a.nibsm,0) + nvl(a.ami,0) + nvl(a.interest_suspense,0))   "+
				
		   " FROM "+m_schema_name+".af_re_tbd_cb_age_analysis a "+
			 " WHERE  ent_user='"+m_username+"'  "+	
			 " AND  floor(nvl(a.age,0))  >= 12 "+
			 " AND  floor(nvl(a.age,0)) < 18 "+
			 " AND a.transaction_type='"+m_txt_type+"' ORDER BY a.client_code ");
		
    }
		else if(m_age ==18){
				rs=stmt.executeQuery(			
		   " SELECT "+
		   " a.finance_no, "+
			 " a.client_code, "+
       " a.client_full_name, "+
			 " nvl(a.age,0)  ,"+
			 " (nvl(a.total_amount,0) - nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) + "+
			 " (nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0)) "+
			 "  - (nvl(a.nibsm,0) + nvl(a.ami,0) + nvl(a.interest_suspense,0))   "+
			 " FROM "+m_schema_name+".af_re_tbd_cb_age_analysis a "+
			 " WHERE  ent_user='"+m_username+"'  "+	
			 " AND  floor(nvl(a.age,0))  >= 18 "+
			 " AND a.transaction_type='"+m_txt_type+"' ORDER BY a.client_code ");
		
    }
					
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Accounting - Arrears For Three Months Or More Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>"); 			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
		  out.println("<tr class=tr_input1>");
			out.println("<td width=\"2%\" align=left  ><b>No</td>"); 
			out.println("<td width=\"7%\" align=left  ><b>Finance No</td>"); 
			out.println("<td width=\"10%\" align=left ><b>Client Name</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Age</td>"); 
			out.println("<td width=\"7%\" align=right ><b>Net Exposure</td>");  //
			out.println("</tr >"); 
			
			int j=1;
			boolean more=rs.next();
			double m_tot_amt=0,m_tot_cap=0;
			
			while(more){
			out.println("<tr >");
			out.println("<td width=\"2%\" align=left>"+j+"</td>"); 
			out.println("<td width=\"7%\" align=left>"+rs.getString(1)+"</td>"); 
			out.println("<td width=\"10%\" align=left style=\"{cursor:hand;}\" onClick=\"show_client('"+rs.getString(2)+"')\" ><u>"+rs.getString(3)+"</u></td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(4))+"&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right>"+nf.format(rs.getDouble(5))+"&nbsp;</td>"); 
			out.println("</tr >"); 
			m_tot_cap+=rs.getDouble(5);
			more=rs.next();
			j+=1;
			}
			
			out.println("<tr >");
			out.println("<td width=\"2%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"10%\" align=left>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>&nbsp;</td>"); 
			out.println("<td width=\"7%\" align=right><b>"+nf.format(m_tot_cap)+"&nbsp;</td>"); 
			out.println("</tr >"); 
			
		  out.println("</table >"); 
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>");
			out.println("</html>");
			
			}
						
			else if(m_chksql.equals("Load_Details")){
			String m_State = req.getParameter("State");
			String m_date = req.getParameter("m_date");
			String contract_det   = ""; 
			double m_equipmnt_val =0;
			double m_rent_arears  =0;
			double m_rent_suspens =0;
			double m_valuation_amt=0;
			
			if(m_State.equals("1")){
			         
             contract_det = " SELECT X.FINANCE_NO, "+ //1
			                      " "+m_schema_name+".af_co_get_client_name(X.CLIENT_CODE), "+ //2
														" "+m_schema_name+".AF_CO_MAS_ASSET_DESC(X.APPLICATION_NO)||' / '||NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(X.FINANCE_NO),' '), "+ //3
														" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(X.APPLICATION_NO), "+ //4
														" NVL("+m_schema_name+".AF_CO_GET_APPROVED_DATE(X.APPLICATION_NO,'ACTIVATED'),' ')||' to '||"+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(X.APPLICATION_NO), "+ //5
														" "+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO)||' x '||"+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(X.APPLICATION_NO), "+ //6
														" NVL("+m_schema_name+".AF_CO_GET_MORE_3MON_RENTS_CNT(X.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0), "+ //7
														" "+m_schema_name+".AF_CO_GET_ARREAS_RENT_CHARGS(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //8 
														" '' WDV, "+ //9
														" "+m_schema_name+".AF_CO_GET_RENT_INCOME_SUSPENSE(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //10
														" '' DEPOSIT, "+ //11
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_DATE(X.APPLICATION_NO),'-'), "+ // 12
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_VAL(X.APPLICATION_NO),0), "+ //13
														" '-' ACTION "+  //14
														
														" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X "+
														" WHERE X.FINANCE_NO IN "+
														" (SELECT DISTINCT A.FINANCE_NO "+
														" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
														//" AND  B.APPLICATION_STATUS='ACTIVATED' "+ //MODIFIED BY SJ 02-06-2009
														" AND  B.TRANSACTION_TYPE IN('FINLEASE','HIREPURCH') "+
														" AND  DUE_DATE  <=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
														" AND  A.ACTIVE_STATUS='Y' "+
														" AND  A.INVOICE_TYPE='INV_GENER' "+
														" AND  ADD_MONTHS(DUE_DATE,3) <=  LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))) ";
														
			} else if(m_State.equals("2")){
			
			contract_det = " SELECT X.FINANCE_NO, "+ //1
			                      " "+m_schema_name+".af_co_get_client_name(X.CLIENT_CODE), "+ //2
														" "+m_schema_name+".AF_CO_MAS_ASSET_DESC(X.APPLICATION_NO)||' / '||NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(X.FINANCE_NO),' '), "+ //3
														" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(X.APPLICATION_NO), "+ //4
														" NVL("+m_schema_name+".AF_CO_GET_APPROVED_DATE(X.APPLICATION_NO,'ACTIVATED'),' ')||' to '||"+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(X.APPLICATION_NO), "+ //5
														" "+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO)||' x '||"+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(X.APPLICATION_NO), "+ //6
														" NVL("+m_schema_name+".AF_CO_GET_MORE_3MON_RENTS_CNT(X.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0), "+ //7
														" "+m_schema_name+".AF_CO_GET_ARREAS_RENT_CHARGS(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //8 
														" '' WDV, "+ //9
														" "+m_schema_name+".AF_CO_GET_RENT_INCOME_SUSPENSE(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //10
														" '' DEPOSIT, "+ //11
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_DATE(X.APPLICATION_NO),'-'), "+ // 12
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_VAL(X.APPLICATION_NO),0), "+ //13
														" '-' ACTION "+  //14
														
														" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X "+
														" WHERE X.FINANCE_NO IN "+
														" (SELECT DISTINCT A.FINANCE_NO "+
														" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
														//" AND  B.APPLICATION_STATUS='ACTIVATED' "+
														" AND  B.TRANSACTION_TYPE IN('OPELEASE') "+
														" AND  DUE_DATE  <=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
														" AND  A.ACTIVE_STATUS='Y' "+
														" AND  A.INVOICE_TYPE='INV_GENER' "+
														" AND  ADD_MONTHS(DUE_DATE,3) <=  LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))) ";
			
			        }
			
			
			
			      rs = stmt.executeQuery(contract_det);
			
			      boolean more = rs.next();	
			
			
			
			
			
			
			
			
			
			  				String Comp_Name = " SELECT COMPANY_NAME, "+
				       " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD')||' '|| "+
							 " INITCAP(TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
							 " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'YYYY') "+
							 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
      rs1 = stmt1.executeQuery(Comp_Name);
			
			boolean more1 = rs1.next();	 
			
			  
			
			
			  out.println("<HTML>"); 
			  out.println("<HEAD>"); 
			  out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			  out.println("</HEAD>"); 
			  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
			  			
			  out.println("</script>"); 
			  out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table  width=\"1550\" class='table' align='center' border='1' width='100%' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASING ACT NO 56 OF 2000");
				out.println("<BR><b>FINANCE LEASING (PROVISION FOR BAD DOUBTFUL ACCOMMODATIONS) DIRECTION NO 2 OF 2006</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
		  
		
		    out.println("<table  width=\"1550\" class='table' align='center' border='0' width='100%' cellspacing=\"0\" cellpadding=\"0\" >");
				
				if(m_State.equals("2")){
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>OPERATING LEASES IN ARREARS FOR THREE MONTHS OR MORE</b></td>");
				out.println("</tr>");
				}else if(m_State.equals("1")){ 
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASES & HP IN ARREARS FOR THREE MONTHS OR MORE</b></td>");
				out.println("</tr>");
				}
				out.println("</table>");
				
				out.println("<br>");

	     
				
				out.println("<table class='table' align='center' border='0' width='100%' cellspacing=\"0\" cellpadding=\"0\">");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Format No</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >SNBFI/FL/02/03</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Name of RFLE</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' ><b>"+rs1.getString(1)+"</b></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >As at Quarter Ended</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >"+rs1.getString(2)+"</td>");
				out.println("</tr>");
				out.println("</table>");
				
			  out.println("<br>");
				
				
				
				
				
				out.println(" <table width=\"1550\"  border=\"1\"  bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
        out.println(" <tr>"); 
			  out.println(" <td width=\"100\" rowspan=\"2\">Contract </td>");
			  out.println(" <td width=\"200\" rowspan=\"2\">Name of the Borrower</td>");
			  out.println(" <td width=\"100\" rowspan=\"2\">Type of Equipment</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Value of the equipment at the commencement</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Date of inception & expiry of the contract  </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Terms of Contract   </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">No of rental in arrears   </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Amount of rentals in arrears & othercharges   </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">WDV of the equipment    </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Rental income in suspense   </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Deposit/ Prepaid rentals   </td>");
			   out.println(" <td colspan=\"2\">Equipment Returned repossessd </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Net Exposure   </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Action taken on return/ reposs: equipment  </td>");
			   out.println(" <td width=\"50\" rowspan=\"2\">Age  </td>");
			 out.println(" </tr>");
			 out.println(" <tr>");
			   out.println(" <td width=\"100\">Date  </td>");
			   out.println(" <td width=\"100\">Valuation  </td>");
			 out.println(" </tr>");
			 
				m_equipmnt_val=0;
				m_rent_arears =0;
				m_rent_suspens=0;
				
				while(more){
				out.println(" <tr>");
			   out.println(" <td align=\"center\">&nbsp;"+rs.getString(1)+"</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(2)+"</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(3)+"</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(5)+"</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(6)+"</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+rs.getString(7)+"&nbsp;&nbsp;&nbsp;</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(8))+"&nbsp;</td>");
			   out.println(" <td align=\"right\" >0.00&nbsp;</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(10))+"&nbsp;</td>");
			   out.println(" <td align=\"right\" >0.00&nbsp;</td>");
			   out.println(" <td align=\"center\">&nbsp;"+rs.getString(12)+"</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(13))+"&nbsp;</td>");
			   out.println(" <td align=\"center\">-&nbsp;</td>");
			   out.println(" <td align=\"center\">-&nbsp;</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+rs.getString(7)+"&nbsp;&nbsp;&nbsp;</td>");
			 out.println(" </tr>");
			 				
				m_equipmnt_val = m_equipmnt_val + rs.getDouble(4);
				m_rent_arears  = m_rent_arears  + rs.getDouble(8);
				m_rent_suspens = m_rent_suspens + rs.getDouble(10);
				m_valuation_amt= m_valuation_amt+ rs.getDouble(13);
				
				more = rs.next();
				}
				
				 out.println(" <tr>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_equipmnt_val)+"&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_rent_arears)+"&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_rent_suspens)+"&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_valuation_amt)+"&nbsp;</td>"); 
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			 out.println(" </tr>");
			out.println(" </table>");
							
				
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
			
			
			
			rs = stmt.executeQuery ("SELECT A.REC_NO,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Chaeque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,NVL(OTH_COMMENTS,'-'),CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,ENT_DATE "+
					 " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					 " "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+  
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					 " AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					 " ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
						
					 //" TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('01-01-2007','DD-MM-YYYY') "+ 
					 //" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('01-01-2009','DD-MM-YYYY') ");
			
			
			
			
			
			
			



			}
			
			if(m_from_date.equals("") || m_to_date.equals("")){	

						
			rs = stmt.executeQuery ("SELECT A.REC_NO,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Chaeque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,NVL(OTH_COMMENTS,'-'),CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,A.ENT_DATE "+
					 " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					 " "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+  
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " ORDER BY "+m_order_by+"  "+m_sort_by+" ");		
						
						
		
			}		
						
						
						
				
					boolean mflag=true;							
					boolean more = rs.next();
					
					 out.println("<HTML><HEAD><TITLE>Arrears For Three Months Or More Report </TITLE></HEAD>");
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
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					 out.println(" window.location.href=m_url;"); 
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
						out.println("<td width='15%' align='left' style= cursor:hand; onclick=show_std_order_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
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
									
			else if (m_chksql.trim().equals("print_report_new")) {
			
			String Sql_arrears="",m_transaction_type="";
			//String m_from_date  = req.getParameter("from_date");
			//String m_to_date    = req.getParameter("to_date");
			String m_to_date    = req.getParameter("date");
			
			/*
			Sql_arrears=" SELECT floor(nvl(a.age,0)), "+
			" (nvl(a.total_amount,0) - nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) + "+
			" (nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0)) "+
			"  - (nvl(a.nibsm,0) + nvl(a.ami,0) + nvl(a.interest_suspense,0))  , "+
			" nvl(a.transaction_type,'-') "+
			" FROM "+m_schema_name+".af_re_tbd_cb_age_analysis a "+
			" WHERE NVL(a.age,0) > =3 "+
			" AND  ENT_USER='"+m_username+"' "+
			//" AND a.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND a.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" ORDER BY  a.transaction_type ";
			
			*/
    
		//comment by nuwan de silva 14-07-2009
		/*	Sql_arrears=" SELECT nvl(a.age,0), "+
			" (nvl(a.total_amount,0) - nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) + "+
			" (nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0)) "+
			"  - (nvl(a.nibsm,0) + nvl(a.ami,0) + nvl(a.interest_suspense,0))  , "+
			" nvl(a.transaction_type,'-') "+
			" FROM "+m_schema_name+".af_re_tbd_cb_age_analysis a "+
			" WHERE NVL(a.age,0) > 3 "+
			" AND  ENT_USER='"+m_username+"' "+
			" AND a.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" ORDER BY  a.transaction_type ";
		*/
		
		
			
						
			int m_age_3_count=0,m_age_6_count=0,m_age_12_count=0,m_age_18_count=0;
			double m_age_3_m_amount=0,m_age_6_m_amount=0,m_age_12_m_amount=0,m_age_18_m_amount=0;
			double m_amount=0;
			double m_age=0;
			int tot_m_age_3_count=0,tot_m_age_6_count=0,tot_m_age_12_count=0,tot_m_age_18_count=0;
			double tot_m_age_3_m_amount=0,tot_m_age_6_m_amount=0,tot_m_age_12_m_amount=0,tot_m_age_18_m_amount=0;
									
			 			
						
			  out.println("<HTML>"); 
			  out.println("<HEAD>"); 
			  out.println("<TITLE>Central Bank Reports - Arrears For Three Months Or More Report</TITLE>"); 
			  out.println("</HEAD>"); 
			  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
			
			  out.println("function get_detail(val){");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=Load_Details&State=\"+val+\"&m_date="+m_to_date+" \";");
		  	out.println("    popupwin=window.open(m_url,'displayWindow2','left=40,top=300,width=1000,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
				
				out.println("function show_drill_details(m_txt_type,age){");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Three_Month_Arears_Report?chksql=drill_down_details&txt_type=\"+m_txt_type+\"&age=\"+age;");
		  	out.println("    popupwin=window.open(m_url,'displayWindow2','left=40,top=300,width=1000,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
											
			  out.println("</script>"); 
			  out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='1' width='100%' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASING ACT NO 56 OF 2000");
				out.println("<BR><b>FINANCE LEASING (PROVISION FOR BAD DOUBTFUL ACCOMMODATIONS) DIRECTION NO 2 OF 2006</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				/*out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>OPERATING LEASES IN ARREARS FOR THREE MONTHS OR MORE</td>");
				out.println("</tr>");
				out.println("</table>");
				*/
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>AGE ANALYSIS OF ACCOMMODATIONS IN ARREARS FOR THREE MONTHS OR MORE</td>");
				out.println("</tr>");
				out.println("</table>");

				String Comp_Name = " SELECT COMPANY_NAME, "+
				       " TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
							 " INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
							 " TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
							 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
       rs = stmt1.executeQuery(Comp_Name);
			
			 boolean more1 = rs.next();			
				
				out.println("<br>");
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Format No</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >SNBFI/FL/02/04</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Name of RFLE</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' ><b>"+rs.getString(1)+"</b></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >As at Quarter Ended</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >"+rs.getString(2)+"</td>");
				out.println("</tr>");
				out.println("</table>");
								
			  out.println("<br>");
				
			 
			rs1 = stmt.executeQuery(
			" SELECT  "+
			" NVL(A.AGE,0), "+ //1
			" NVL(A.NET_RENT_ARREARS,0) ,    "+ //2
			" NVL(A.FUTURE_RECEIVABLE,0) ,   "+ //3
			" NVL(A.CHARGES_ARREARS,0),      "+ //4
			" NVL(A.INTEREST_SUSPENSE,0) ,   "+  //5
			" NVL(A.NIBSM,0),                "+ //6
			" NVL(A.AMI,0),                  "+  //7
			" NVL(A.TRANSACTION_TYPE,'-')    "+ //8
			" FROM "+m_schema_name+".AF_RE_TBD_CB_AGE_ANALYSIS A "+
			" WHERE NVL(A.AGE,0) > 3 "+
			" AND   A.ENT_USER='"+m_username+"' "+
			//" AND   A.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" AND   A.APPLICATION_STATUS='ACTIVATED' "+
			" ORDER BY  A.TRANSACTION_TYPE ");

			  boolean more = rs1.next();
				
        if(!more){
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>No Records.</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				}
								
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=#eeeeee >");
				out.println("<tr BGCOLOR=#a5b6c6 > ");
				out.println("<td width=\"3%\" rowspan=\"2\">Type of Advances</td>");
				out.println("<td colspan=\"10\"><div align=\"center\"><b>AGE ANALYSIS </div></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td colspan=\"2\" align=\"center\">3 and upto 6 months </td>");
				out.println("<td colspan=\"2\" align=\"center\">6 and upto 12 months </td>");
				out.println("<td colspan=\"2\" align=\"center\">12 and upto 18 months </td>");
				out.println("<td colspan=\"2\" align=\"center\">Over 18 Months </td>");
				out.println("<td colspan=\"2\" align=\"center\">Total</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"10%\">&nbsp;</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure</td>");
				out.println("<td width=\"9%\">No of contracts </td>");
				out.println("<td width=\"9%\">Total Net Exposure </td>");
				out.println("</tr>");
								
				while(more){
				m_transaction_type=rs1.getString(8);
				m_age_3_count=0;
				m_age_3_m_amount=0;
				m_age_6_count=0;
				m_age_6_m_amount=0;
				m_age_12_count=0;
				m_age_12_m_amount=0;
				m_age_18_count=0;
				m_age_18_m_amount=0;
				while(m_transaction_type.equals(rs1.getString(8))) {
				m_age=rs1.getDouble(1);
				m_amount=rs1.getDouble(2)+rs1.getDouble(3)+rs1.getDouble(4)-rs1.getDouble(5)-rs1.getDouble(6)- rs1.getDouble(7);
				//m_amount=rs1.getDouble(2); comment by nuwan de silva 14-07-2009
				
				
				//modified by nuwan de silva on 04-08-2008---
				if ( m_age >= 3  &&  m_age < 6 ){
				m_age_3_count+=1;
				m_age_3_m_amount+=m_amount;
				}
				else if ( m_age >=6 &&  m_age < 12 ){
				m_age_6_count+=1;
				m_age_6_m_amount+=m_amount;
				}
				else if ( m_age >=12  &&  m_age < 18 ){
				m_age_12_count+=1;
				m_age_12_m_amount+=m_amount;
				}
				else if ( m_age >= 18  ){
				m_age_18_count+=1;
				m_age_18_m_amount+=m_amount;
				}
				
				more=rs1.next();
				if(!more){break;}
				}
				
				out.println("<tr style=\"cursor:hand\" >");
				out.println("<td align=\"center\" >&nbsp;"+m_transaction_type+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',3)\" >"+m_age_3_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',3)\" >"+nf.format(m_age_3_m_amount)+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',6)\" >"+m_age_6_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',6)\" >"+nf.format(m_age_6_m_amount)+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',12)\" >"+m_age_12_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',12)\" >"+nf.format(m_age_12_m_amount)+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',18)\" >"+m_age_18_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',18)\"  >"+nf.format(m_age_18_m_amount)+"</td>");
				out.println("<td align=\"center\">"+(m_age_3_count+m_age_6_count+m_age_12_count+m_age_18_count)+"</td>");
				out.println("<td align=\"right\">"+nf.format(m_age_3_m_amount+m_age_6_m_amount+m_age_12_m_amount+m_age_18_m_amount)+"</td>");
				out.println("</tr>");
				
				tot_m_age_3_count+=m_age_3_count;
				tot_m_age_6_count+=m_age_6_count;
				tot_m_age_12_count+=m_age_12_count;
				tot_m_age_18_count+=m_age_18_count;
				
				tot_m_age_3_m_amount+=m_age_3_m_amount;
				tot_m_age_6_m_amount+=m_age_6_m_amount;
				tot_m_age_12_m_amount+=m_age_12_m_amount;
				tot_m_age_18_m_amount+=m_age_18_m_amount;
				
				}
				
				out.println("<tr style=\"cursor:hand\" >");
				out.println("<td align=\"center\" ><b>&nbsp;</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_3_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_3_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_6_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_6_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_12_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_12_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_18_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_18_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+(tot_m_age_3_count+tot_m_age_6_count+tot_m_age_12_count+tot_m_age_18_count)+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_3_m_amount+tot_m_age_6_m_amount+tot_m_age_12_m_amount+tot_m_age_18_m_amount)+"</td>");
				out.println("</tr>");
				
				
			//LEGAL CASES=============================
			rs1 = stmt.executeQuery(
			" SELECT  "+
			" NVL(A.AGE,0), "+ //1
			" NVL(A.NET_RENT_ARREARS,0) ,    "+ //2
			" NVL(A.FUTURE_RECEIVABLE,0) , "+ //3
			" NVL(A.CHARGES_ARREARS,0),      "+ //4
			" NVL(A.INTEREST_SUSPENSE,0) ,    "+  //5
			" NVL(A.NIBSM,0),                "+ //6
			" NVL(A.AMI,0),                 "+  //7
			" NVL(A.TRANSACTION_TYPE,'-') "+ //8
			" FROM "+m_schema_name+".AF_RE_TBD_CB_AGE_ANALYSIS A "+
			" WHERE NVL(A.AGE,0) > 3 "+
			" AND   A.ENT_USER='"+m_username+"' "+
			//" AND   A.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" AND   A.APPLICATION_STATUS='LEGAL' "+
			" ORDER BY  A.TRANSACTION_TYPE ");

			 more = rs1.next();
				
				tot_m_age_3_count=0;
				tot_m_age_6_count=0;
				tot_m_age_12_count=0;
				tot_m_age_18_count=0;
				
				tot_m_age_3_m_amount=0;
				tot_m_age_6_m_amount=0;
				tot_m_age_12_m_amount=0;
				tot_m_age_18_m_amount=0;

				
				while(more){
				m_transaction_type=rs1.getString(8);
				m_age_3_count=0;
				m_age_3_m_amount=0;
				m_age_6_count=0;
				m_age_6_m_amount=0;
				m_age_12_count=0;
				m_age_12_m_amount=0;
				m_age_18_count=0;
				m_age_18_m_amount=0;
				while(m_transaction_type.equals(rs1.getString(8))) {
				m_age=rs1.getDouble(1);
				m_amount=rs1.getDouble(2)+rs1.getDouble(3)+rs1.getDouble(4)-rs1.getDouble(5)-rs1.getDouble(6)- rs1.getDouble(7);
				//m_amount=rs1.getDouble(2); comment by nuwan de silva 14-07-2009
				
				
				//modified by nuwan de silva on 04-08-2008---
				if ( m_age >= 3  &&  m_age < 6 ){
				m_age_3_count+=1;
				m_age_3_m_amount+=m_amount;
				}
				else if ( m_age >=6 &&  m_age < 12 ){
				m_age_6_count+=1;
				m_age_6_m_amount+=m_amount;
				}
				else if ( m_age >=12  &&  m_age < 18 ){
				m_age_12_count+=1;
				m_age_12_m_amount+=m_amount;
				}
				else if ( m_age >= 18  ){
				m_age_18_count+=1;
				m_age_18_m_amount+=m_amount;
				}
				
				more=rs1.next();
				if(!more){break;}
				}
				
				out.println("<tr style=\"cursor:hand\" >");
				out.println("<td align=\"center\" >&nbsp;LEGAL - "+m_transaction_type+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',3)\" >"+m_age_3_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',3)\" >"+nf.format(m_age_3_m_amount)+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',6)\" >"+m_age_6_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',6)\" >"+nf.format(m_age_6_m_amount)+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',12)\" >"+m_age_12_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',12)\" >"+nf.format(m_age_12_m_amount)+"</td>");
				out.println("<td align=\"center\" onClick=\"show_drill_details('"+m_transaction_type+"',18)\" >"+m_age_18_count+"</td>");
				out.println("<td align=\"right\"  onClick=\"show_drill_details('"+m_transaction_type+"',18)\"  >"+nf.format(m_age_18_m_amount)+"</td>");
				out.println("<td align=\"center\">"+(m_age_3_count+m_age_6_count+m_age_12_count+m_age_18_count)+"</td>");
				out.println("<td align=\"right\">"+nf.format(m_age_3_m_amount+m_age_6_m_amount+m_age_12_m_amount+m_age_18_m_amount)+"</td>");
				out.println("</tr>");
				
				tot_m_age_3_count+=m_age_3_count;
				tot_m_age_6_count+=m_age_6_count;
				tot_m_age_12_count+=m_age_12_count;
				tot_m_age_18_count+=m_age_18_count;
				
				tot_m_age_3_m_amount+=m_age_3_m_amount;
				tot_m_age_6_m_amount+=m_age_6_m_amount;
				tot_m_age_12_m_amount+=m_age_12_m_amount;
				tot_m_age_18_m_amount+=m_age_18_m_amount;
				
				}
				
				out.println("<tr style=\"cursor:hand\" >");
				out.println("<td align=\"center\" ><b>&nbsp;</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_3_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_3_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_6_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_6_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_12_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_12_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+tot_m_age_18_count+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_18_m_amount)+"</td>");
				out.println("<td align=\"center\" ><b>&nbsp;"+(tot_m_age_3_count+tot_m_age_6_count+tot_m_age_12_count+tot_m_age_18_count)+"</td>");
				out.println("<td align=\"right\" ><b>&nbsp;"+nf.format(tot_m_age_3_m_amount+tot_m_age_6_m_amount+tot_m_age_12_m_amount+tot_m_age_18_m_amount)+"</td>");
				out.println("</tr>");

				
				// END LEGAL CASES =======================


				
				
				
				out.println("</table>");
								
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");
			
					
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
