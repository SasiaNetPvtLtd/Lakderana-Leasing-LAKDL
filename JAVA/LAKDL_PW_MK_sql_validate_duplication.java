import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_PW_MK_sql_validate_duplication extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest request, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn=null;
		Statement stmt=null;
		Statement stmt1=null;
		Statement stmt2=null;
		Statement stmt3=null;
		java.text.NumberFormat nf,nf1;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		String m_chksql=null;
		
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
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);   
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=request.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(request); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			stmt3=conn.createStatement(); 	
			String m_username 	= m_sn_methods.username;
			int row_count = 0;
			if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Receipt Report </TITLE>"); 
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
				
				out.println("function makeRequest_detail() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				out.println("function makeRequest_detail_cancel() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_cancel&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				
				
				out.println("function makeRequest_detail_Stand_Order() {");//Added By Sandun On 16-09-2009
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_stdo&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				out.println("function makeRequest_detail_Stand_Temp() {");//Added By Sandun On 16-09-2009
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_temp&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				//Added by Dineth on 2008-09-29
				out.println("function makeRequest_detail_return() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_return&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				
				//End by Dineth on 2008-09-29
				//Added by Dineth on 16-06-2009
				out.println("function makeRequest_detail_PDC() {");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		if(validate_date()) {");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts_PDC&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=CHEQUE_DATE&sort_by=ASC\";");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				out.println("   }");
				out.println("}");
				//End by Dineth on 16-06-2009
				out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_reportchksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Collection - Receipt Report - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Collection - Receipt Report \";"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection - Receipt Report </td>"); 
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
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
				out.println("<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\"  >"); //value=\"01\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" >"); //value=\"04\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"   >");	 //value=\"2007\"
				out.println("</td> ");
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
				out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
				out.println("</td> ");
				out.println("<td width='*%' >");
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' style=\"{width:110px;}\" value=\"View All Receipts\" onClick=\"makeRequest_detail()\">");
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_2' style=\"{width:110px;}\" value=\"View Cancel Receipts\" onClick=\"makeRequest_detail_cancel()\">");
				// Added by Dineth on 2008-09-29
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_3' style=\"{width:110px;}\" value=\"View Return Receipts\" onClick=\"makeRequest_detail_return()\">");
				// End by Dineth
				
				
				out.println("</td> ");
				//			out.println("<td width='*%' ></td>");
				
				out.println("<tr>");
				out.println("<td colspan=4>&nbsp;</td>");
				out.println("<td>");
				//Added by Dineth on 16-06-2009
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_4' style=\"{width:110px;}\" value=\"View PDC Receipts\" onClick=\"makeRequest_detail_PDC()\">");
				//End by Dineth on 16-06-2009
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_5' style=\"{width:110px;}\" value=\"View Standing Orders \" onClick=\"makeRequest_detail_Stand_Order()\">");//Added By Sandun on 16-09-2009
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_6' style=\"{width:110px;}\" value=\"View Temp. Receipts\" onClick=\"makeRequest_detail_Stand_Temp()\">");//Added By Sandun on 16-09-2009
				out.println("</td>");
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
			else if(m_chksql.trim().equals("check_nationality")){
				
				String nationality_code 		= request.getParameter("data_val").trim();
				
				rs=stmt.executeQuery(" SELECT NATIONALITY_CODE FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY WHERE  NATIONALITY_CODE = '"+nationality_code+"' ");
				
				out.print("<DATA>");
				while(rs.next())
				{
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
				}
				out.print("</DATA>");
				
			}
			
			
			
			
			else if(m_chksql.trim().equals("get_sub_sections")){
				
				rs=stmt.executeQuery("SELECT SUB_SEC_ID, DESC_1, DESC_2, HEIGHT_1 FROM "+m_schema_name+".DH_DASH_SUB_SECTION");
				
				out.print("<rows>");
				
				while(rs.next()){
					out.print("<row id='"+row_count+"'>"); 
					out.print("<cell><![CDATA["+rs.getString(1)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(2)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(3)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(4)+"]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("</row>");
					row_count++;
				}
				out.print("</rows>");
				
			}
			
			
			else if(m_chksql.trim().equals("dash_graph_main")){
				
				String sub_sec_id 		= request.getParameter("sub_sec_id").trim();
				
				rs=stmt.executeQuery("SELECT DASH_ID, "+
					"GRAPH_TYPE_ID, "+
					//"ACTIVE_STATUS, "+
					"DASH_X_POS, "+
					"DASH_Y_POS, "+
					"GRAPH_HEIGHT, "+
					"GRAPH_WIDHT "+
					//"SUB_SEC_ID "+
					"FROM "+m_schema_name+".DH_DASH_GRAPH_MAIN WHERE SUB_SEC_ID = '"+sub_sec_id+"' ");
				
				out.print("<rows>");
				
				while(rs.next()){
					out.print("<row id='"+row_count+"'>"); 
					out.print("<cell><![CDATA["+rs.getString(1)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(2)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(3)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(4)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(5)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(6)+"]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("</row>");
					row_count++;
				}
				out.print("</rows>");
				
			}
			
			
			
			else if(m_chksql.trim().equals("dash_get_sql")){
				
				String dash_id 		= request.getParameter("dash_id").trim();
				
				rs=stmt.executeQuery("SELECT DASH_ID, "+
					"SQL_TYPE, "+
					"SQL_STR "+
					"FROM "+m_schema_name+".DH_GRAPH_SQL WHERE DASH_ID = '"+dash_id+"'");
				
				out.print("<rows>");
				
				while(rs.next()){
					out.print("<row id='"+row_count+"'>"); 
					out.print("<cell><![CDATA["+rs.getString(1)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(2)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(3)+"]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("</row>");
					row_count++;
				}
				out.print("</rows>");
				
			}
			
			
			else if(m_chksql.trim().equals("dash_get_sql_property")){
				
				String dash_id 		= request.getParameter("dash_id").trim();
				String sql_type 	= request.getParameter("sql_type").trim();
				
				
				rs=stmt.executeQuery("SELECT DASH_ID, "+
					"SQL_TYPE, "+
					"PROPERTY_NAME, "+
					"PROPERTY_VALUE "+
					"FROM "+m_schema_name+".DH_GRAPH_SQL_PROPERTY WHERE DASH_ID = '"+dash_id+"' AND SQL_TYPE = '"+sql_type+"'");
				
				out.print("<rows>");
				
				while(rs.next()){
					out.print("<row id='"+row_count+"'>"); 
					out.print("<cell><![CDATA["+rs.getString(1)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(2)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(3)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(4)+"]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("</row>");
					row_count++;
				}
				out.print("</rows>");
				
			}
			
			
			
			else if(m_chksql.trim().equals("dash_get_graph_properties")){
				
				String dash_id 		= request.getParameter("dash_id").trim();
				
				rs=stmt.executeQuery("SELECT DASH_ID, "+
					//out.println("SELECT DASH_ID, "+
					"PROPERTY_NAME, "+
					"PROPERTY_VALUE "+
					"FROM "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY WHERE DASH_ID = '"+dash_id+"'");
				
				out.print("<rows>");
				
				while(rs.next()){
					out.print("<row id='"+row_count+"'>"); 
					out.print("<cell><![CDATA["+rs.getString(1)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(2)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(3)+"]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("</row>");
					row_count++;
				}
				out.print("</rows>");
				
			}
			
			
			else if(m_chksql.trim().equals("get_sql_query")){
				
				String SUB_SEC_NUM 		= request.getParameter("SUB_SEC_NUM").trim();
				String HID_DASH_ID 		= request.getParameter("HID_DASH_ID").trim();
				
				rs=stmt.executeQuery(" SELECT SQL_STR FROM "+m_schema_name+".DH_GRAPH_SQL WHERE "+
					//out.println(" SELECT SQL_STR FROM "+m_schema_name+"DH_GRAPH_SQL WHERE "+
					"DASH_ID = '"+HID_DASH_ID+"' AND "+
					"SQL_TYPE = '"+SUB_SEC_NUM+"' ");
				
				
				while(rs.next())
				{
					out.print("<DATA>");
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>");
					out.print("</ITEM>");
					out.print("</DATA>");
				}
				
				
			}
			
			
			else if(m_chksql.trim().equals("get_pending_auth_limits")){
				
				rs=stmt.executeQuery("SELECT DISTINCT USER_ID, ENT_USER, TO_CHAR(ENT_DATE, 'DD-MM-YYYY') FROM "+m_schema_name+".AF_CO_MAS_AUTH_LIMITS_TEMP");
				
				out.print("<rows>");
				
				while(rs.next()){
					out.print("<row id='"+row_count+"'>"); 
					out.print("<cell><![CDATA["+rs.getString(1)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(1)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(2)+"]]></cell>");
					out.print("<cell><![CDATA["+rs.getString(3)+"]]></cell>");
					out.print("<cell><![CDATA[]]></cell>");
					out.print("</row>");
					row_count++;
				}
				out.print("</rows>");
				
			}
			
			
			
			else {
				out.println("Undefined");
			}
			
			out.close();
			conn.close();
			this.destroy();
			
			
		
		
		
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