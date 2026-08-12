// DEVELOP BY : Indika FOR OFSCL Invoice Verification Report    
// DATE:04-09-2008

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_OP_PRO_Invoice_Verification_rpt extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf1,nf;
	Statement stmt1;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1;
  
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name=m_sn_methods.schema_name;
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username=m_sn_methods.username;
			String m_client_name = "";
			String m_invoice_number = "";
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
				
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);			
				
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			stmt1 = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");
			String m_finance_no=req.getParameter("finance_no");
			String Insurance_Sql="";
			
			int count = 1;
			double m_tot_invoice=0.0;
			double m_tot_settled=0.0;
			double m_tot_balanced=0.0;
			double m_tot_amount=0.0;
			
			String m_to_date; 
			String m_from_date;
	
	  	 	if(m_chksql.equals("run_report")){ 
				String m_date=req.getParameter("date");	
				String m_client_code=req.getParameter("client_code").trim();
				String m_coll_officer=req.getParameter("coll_officer").trim();
			
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_INSURANCE_LEDGER(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_client_code);
					callstmt1.setString(3,m_coll_officer);
					callstmt1.setString(4,m_username);
					callstmt1.execute();
					
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}

			}
			else if(m_chksql.equals("LOAD_INVOICE_VERIFICATION_REPORT")){ 
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Operation Reports Details </TITLE>"); 
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

				out.println("function show_invoice_detail_report() {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_exception_detail?chksql=LOAD_INVOICE_DETAIL_REPORT&status=\"+m_status; ");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1'); ");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_Invoice_Verification_rpt?chksql=LOAD_INVOICE_VERIFICATION_REPORT';");
				//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_Invoice_Verification_rpt';");
				out.println("		}"); 
				out.println("}"); 
			
				out.println("function close_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_Invoice_Verification_rpt';"); 
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
				out.println("	help_box.innerHTML=\" Invoice Verification Report - \"+m_val;"); 
				out.println("}"); 

				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Invoice Verification Report \";"); 
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
				out.println(" m_sql = \"m_help_DIV_TXT_FACTOR_CLIENT_NAME_sql\";"); 
				out.println(" m_criteria = document.Form1.TXT_CLIENT_NAME.value+\"@\";"); 
				out.println(" HelpBox('1','10','0');"); 
				out.println("}"); 
			
				out.println("function help_update_facility() {"); 
				//out.println("m_help=\"1\";");
				out.println("    document.Form1.hid_help_type.value=\"2\";");
				out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql_report_sql\";"); 
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("  m_criteria = document.Form1.TXT_INVOICE_NO.value+\"@\"+document.Form1.TXT_CLIENT_NAME.value+\"@\"+m_to_date+\"@\"+m_from_date+\"@\";"); 
				out.println(" 	HelpBox('2','10','0');"); 
				out.println("}");
			
				out.println("function help_update_value_assign_1() {"); 
				out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[2];"); 
				//out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
				out.println("}");
				
				out.println("function help_update_value_assign_2() {"); 
				out.println("		document.Form1.TXT_INVOICE_NO.value=oBj.valout[2];"); 
				out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
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
			 
				// Modified by Thamali Jayatunga on 2009.10.14, Added if condition
				out.println("function search_client_invoice_details() {");
				out.println("if (validate_date()){");
				out.println("    document.Form1.hid_help_type.value=\"2\";");
				out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql_report_sql\";"); 
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_Invoice_Verification_rpt?chksql=search_result_client_invoice&client_name=\"+document.Form1.TXT_CLIENT_NAME.value+\"&invoice_number=\"+document.Form1.TXT_INVOICE_NO.value;");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_Invoice_Verification_rpt?chksql=search_result_client_invoice&client_name=\"+document.Form1.TXT_CLIENT_NAME.value+\"&invoice_number=\"+document.Form1.TXT_INVOICE_NO.value+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Reports Detail</td>"); 
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
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" >");	
				out.println("</td> ");
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
				out.println(" <TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
				out.println("</td> ");
				out.println("<td width='*%' ></td>");
				out.println("</table>");
			
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input><b>Client Name</b></DIV></td>"); 
				out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='50' style='width:100' onblur=\"help_update()\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("<td width='*%' align='left' ></td>");
				out.println("</tr>"); 
				out.println("</table>");
			
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input><B>Invoice No</B></DIV></td>");//TXT_INVOICE_NO
				out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='20' size='10' style='width:100' onblur=\"help_update_facility()\">"); //TXT_INVOICE_NO
				out.println("<input class='but_input' type='button' name='BUT_PURCHASE_ORDER_NO' value=\"Help\" onClick=\"help_update_facility()\"></td>"); 
				out.println("<td width='*%' align='left' ></td>");
				out.println("<td width='*%'>");
				out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_INVOICE_SEARCH' value=\"Search\" onClick=\"search_client_invoice_details()\"></td>");
				out.println("</tr>"); 
				out.println("</table>");
			
				out.println("<br><br>");
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
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
		   }
		   else if(m_chksql.equals("search_result_client_invoice")) {	
			
				String client_name = req.getParameter("client_name");
				String invoice_number = req.getParameter("invoice_number");
				m_from_date = req.getParameter("from_date");
				m_to_date = req.getParameter("to_date");
				
				out.println("<HTML><HEAD><TITLE> Client & Invoice Details </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B></B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");

		     	Insurance_Sql = "	SELECT "+
									 " INVOICE_DATE, "+ 
									 " INVOICE_AMOUNT, "+
									 " BALANCE_AMOUNT, "+
									 " ENT_USER "+
									 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL "+
									 " WHERE "+
									 " INVOICE_NO = '"+invoice_number+"' "+
									 " ORDER BY ENT_DATE ";

			
				rs = stmt1.executeQuery(Insurance_Sql);
				boolean more =rs.next();	
									 			
        		if (!more) {
				  	out.println("<table align='center' width='90%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>YOU DO NOT SELECT A INVOICE NUMBER</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if (more) {
					out.println("<table width='90%' border='0' class='table' >");
					out.println("<tr>");
					out.println("<td width='20%' class=div_input align='left'><b>Invoice No &nbsp&nbsp:&nbsp "+invoice_number+"</b></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='20%' class=div_input align='left'><b>From Date &nbsp&nbsp:&nbsp "+m_from_date+"</b></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='20%' class=div_input align='left'><b>To Date &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp:&nbsp "+m_to_date+"</b></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='50' class=div_input align='left'>&nbsp</td>");
					out.println("<td width='2' class=div_input align='left'>&nbsp</td>");
					out.println("<td width='50' class=div_input align='left'>&nbsp</td>");
					out.println("</tr>");
					//out.println("<td width='*%'></td>");
					//out.println("</tr>");
					out.println("</table>");
				 	out.println("<table width='90%' border='0' class='table' >");
					out.println("<tr>");
					out.println("<td width='5%' class=div_input align='left'><b>Client Name</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Invoice Date</b></td>");
					out.println("<td width='15%' class=div_input align='left' ><b>Invoice Amount</b></td>");
					out.println("<td width='15%' class=div_input align='left' ><b>Invoice Balance</b></td>");
					out.println("<td width='18%' class=div_input align='left' ><b>Insurance Officer</b></td>");
					out.println("</tr>");
								
					while(more){				
						out.println("<tr>"); 
						out.println("<td width='15%' class=div_input align='left'  >"+client_name+"</td>");
						out.println("<td width='10%' class=div_input align='left' >"+rs.getString(1)+"</td>");
						out.println("<td width='15%' class=div_input align='left' >"+rs.getString(2)+"&nbsp;</td>");
						out.println("<td width='15%' class=div_input align='left' >"+rs.getString(3)+"&nbsp;</td>");
						out.println("<td width='15%' class=div_input align='left' >"+rs.getString(4)+"&nbsp;</td>");
						out.println("<td width='3%' class=div_input align='left' ><b>&nbsp;</b></td>");
						out.println("</tr>");
						more = rs.next();
					}
				}
				out.println("</table>");			
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}					
	  }
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		
				if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
