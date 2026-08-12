import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Accounting_Operating_Lease_Age_Above_Three_Months extends javax.servlet.http.HttpServlet { 

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
			
			 //ring m_from_date  = req.getParameter("from_date");
			 String m_to_date    = req.getParameter("date");

								
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_CB_OP_LEASE_RPT(:1,:2);END;");
				callstmt1.setString(1,m_to_date);
				//llstmt1.setString(2,m_to_date);
				callstmt1.setString(2,m_username);
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
			out.println("<TITLE>Accounting - Operating Leases in Arrears for Three Months or More </TITLE>"); 
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
				//out.println("alert('sdf');");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Accounting_Operating_Lease_Age_Above_Three_Months?chksql=run_report&date=\"+m_date;"); 
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
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
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Accounting_Operating_Lease_Age_Above_Three_Months?chksql=print_report_new&date=\"+m_date;");	
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
        
			out.println("var m_sav_msg='';");
			
		
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.VAL_DAY.value=data_vec[0];");
			out.println("			document.Form1.VAL_MONTH.value=data_vec[1];");
			out.println("			document.Form1.VAL_YEAR.value=data_vec[2];");
			out.println("		}");
			out.println("}");

			 
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
			out.println("	help_box.innerHTML=\" Accounting - Operating Leases in Arrears for Three Months or More Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Accounting - Operating Leases in Arrears for Three Months or More Report \";"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Accounting - Operating Leases in Arrears for Three Months or More </td>"); 
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
			out.println(" <TD WIDTH=\"60%\"><input class=\"txt_input5\" type=\"text\" name=VAL_DAY maxlength=\"2\" size=\"2\" onBlur=\"checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=VAL_MONTH  maxlength=\"2\" size=\"2\" onBlur=\"checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=VAL_YEAR maxlength=\"4\" size=\"4\" onBlur=\"checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)\">");
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
			
			else if (m_chksql.trim().equals("print_report_new")) {
			
			String Sql_arrears="",m_transaction_type="";
			String m_to_date    = req.getParameter("date");
									

						
			int m_age_3_count=0,m_age_6_count=0,m_age_12_count=0,m_age_18_count=0;
			double m_age_3_m_amount=0,m_age_6_m_amount=0,m_age_12_m_amount=0,m_age_18_m_amount=0;
			double m_amount=0;
			double m_age=0;
			int tot_m_age_3_count=0,tot_m_age_6_count=0,tot_m_age_12_count=0,tot_m_age_18_count=0;
			double tot_m_age_3_m_amount=0,tot_m_age_6_m_amount=0,tot_m_age_12_m_amount=0,tot_m_age_18_m_amount=0;
 						
			  out.println("<HTML>"); 
			  out.println("<HEAD>"); 
			  out.println("<TITLE>Collection - Accounting Report</TITLE>"); 
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
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>OPERATING LEASES IN ARREARS FOR THREE MONTHS OR MORE</td>");
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
				out.println("<td align='left' width='*%' >SNBFI/FL/02/03</td>"); 
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
				
   //  out.println(
		  rs = stmt1.executeQuery(
			 " SELECT  "+
			 " a.finance_no,"+ //1
       " a.client_full_name, "+ //2
			 " a.asset_desc, "+ //3
			 " a.facility_amount, "+ //4
			 " TO_CHAR(a.activated_date,'DD-MM-YYYY'),  "+ //5
			 " TO_CHAR(a.end_date,'DD-MM-YYYY'), "+ //6
       " a.terms_of_contract, "+ //7
			 " a.age, "+ //8
			 //" nvl(a.total_amount,0) -( nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0) ) , "+ //9
			 " NVL(a.NET_RENT_ARREARS,0) ,"+ //9
       " nvl(a.interest_suspense,0),  "+ //10
       " nvl(a.cap_arr_portion,0) + nvl(a.future_receivable,0) ,"+ //11
       " nvl(a.nibsm,0), "+ //12
			 " nvl(a.ami,0) ,"+ //13
			 " nvl(A.WDV,0),"+ //14
			 " "+m_schema_name+".AF_CO_GET_MONTHS_DIFF(a.application_no,'"+m_to_date+"') ,"+ //15
			 " NVL(a.CHARGES_ARREARS,0 ) "+ //16
       " FROM "+m_schema_name+".af_re_tbd_cb_op_leasing a "+
			 " WHERE NVL(a.age,0) > 3 "+
			 " AND  ENT_USER='"+m_username+"' "+
			 " AND a.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");

				
			  boolean more = rs.next();
				
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
				out.println("<td width=\"10%\">Contract No</td>");
				out.println("<td width=\"10%\">Name of The Borrower</td>");
				out.println("<td width=\"7%\">Type of Equipment</td>");
				out.println("<td width=\"7%\">Value of the equipment at the commecement</td>");
				out.println("<td width=\"7%\">Date of inspection & expiry of the contract</td>");
				out.println("<td width=\"7%\">Terms of contracts</td>");
				out.println("<td width=\"7%\">No of rental in arrears</td>");
				out.println("<td width=\"7%\">Amount Rental in arrears and other charges</td>");
				out.println("<td width=\"7%\">WDV of the equipment</td>");
				out.println("<td width=\"7%\">Rental income suspense</td>");
				out.println("<td width=\"7%\">deposit/prepaid rentals</td>");
				out.println("<td width=\"7%\">Date</td>");
				out.println("<td width=\"7%\">Valuation</td>");
				out.println("<td width=\"7%\">Net Exposure</td>");
				out.println("<td width=\"7%\">action taken on</td>");
				out.println("<td width=\"7%\">age</td>");
				out.println("</tr>");
				double m_net_exp=0;				
				while(more){
				
				m_net_exp=rs.getDouble(14);
				
				out.println("<tr  > ");
				out.println("<td width=\"10%\">"+rs.getString(1)+"</td>");
				out.println("<td width=\"10%\">"+rs.getString(2)+"</td>");
				out.println("<td width=\"7%\" >"+rs.getString(3)+"</td>");
				out.println("<td width=\"7%\">"+nf.format(rs.getDouble(4))+"</td>");
				out.println("<td width=\"7%\"><p>"+rs.getString(5)+"  to <br> "+rs.getString(6)+"  </p></td>");
				out.println("<td width=\"7%\">"+rs.getString(7)+"</td>");
				out.println("<td width=\"7%\">"+nf.format(rs.getDouble(8))+"</td>");
				out.println("<td width=\"7%\">"+nf.format(rs.getDouble(9) +rs.getDouble(16))+"</td>");
				out.println("<td width=\"7%\">"+nf.format(rs.getDouble(14))+"</td>");
				out.println("<td width=\"7%\">"+nf.format(rs.getDouble(9) +rs.getDouble(16))+"</td>");
				out.println("<td width=\"7%\">-</td>");
				out.println("<td width=\"7%\">-</td>");
				out.println("<td width=\"7%\">-</td>");
				out.println("<td width=\"7%\">"+nf.format(m_net_exp)+"</td>");
				out.println("<td width=\"7%\">-</td>");
				out.println("<td width=\"7%\">"+nf.format(rs.getDouble(8)+rs.getDouble(15))+"</td>");
				out.println("</tr>");
				
				/*tot_m_age_3_count+=m_age_3_count;
				tot_m_age_6_count+=m_age_6_count;
				tot_m_age_12_count+=m_age_12_count;
				tot_m_age_18_count+=m_age_18_count;
				
				tot_m_age_3_m_amount+=m_age_3_m_amount;
				tot_m_age_6_m_amount+=m_age_6_m_amount;
				tot_m_age_12_m_amount+=m_age_12_m_amount;
				tot_m_age_18_m_amount+=m_age_18_m_amount;
				*/
				more=rs.next();
				}
				
				
				
				
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
