// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:21-09-2006

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_receipt_report_new_gui extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
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
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			stmt3=conn.createStatement(); 	
			String m_username 	= m_sn_methods.username;
			
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_cancel&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_stdo&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_temp&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_return&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
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
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_PDC&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=CHEQUE_DATE&sort_by=ASC\";");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_guichksql=main_page';"); 
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
			else if(m_chksql.equals("load_receipts")){
				
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code"); 
				
				String mm_location_code = req.getParameter("location_code"); // added by udara on 03-12-2012
				//out.println("test test "+m_location_code);
				
				
				// added by udara on 29-10-2013
				rs1= stmt1.executeQuery(" SELECT "+
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_branch_id = rs1.getString(1);
				}
				
				if(m_branch_id.equals("HO")){
					m_branch_id = req.getParameter("branch_id");  // m_branch_id = "";
				}
				else{
					m_branch_id = m_branch_id;
				}
				// end by udara on 29-10-2013
				
				
				
				
				
				String m_user = req.getParameter("user");
				String m_user_query=""; 
				String m_user_query2=""; 
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND UPPER(A.ENT_USER) = UPPER('"+m_user+"') ";
					m_user_query2 = "AND UPPER(REC_ENT_BY) = UPPER('"+m_user+"') ";
				}
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					
					//location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER) = '"+m_branch_id+"' "; 
					location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN_3(A.ENT_USER,A.REC_NO) = '"+m_branch_id+"' ";
					//location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN_2(A.ENT_USER,A.SUB_REC_NO) = '"+m_branch_id+"' ";
					
				
				    //location_query_part = " AND A.REC_LOC = '"+m_branch_id+"' "; 
					//location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN_2(A.ENT_USER,A.SUB_REC_NO) = '"+m_branch_id+"' ";
					//location_query_part = "  AND NVL(REGEXP_SUBSTR(A.SUB_REC_NO,'[^/]+', 1),"+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER)) = '"+m_branch_id+"' ";
					//location_query_part = " AND  ("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"'  OR NVL(REGEXP_SUBSTR(A.SUB_REC_NO,'[^/]+', 1),'AAAA') = '"+m_branch_id+"' ) "; // added by udara on 16-10-2013	
					// location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"' "; // commented by udara on 16-10-2013	
				
				// end by udara on 25-07-2013
				
				/*if (m_location_code.trim().equals("ALL")) {
				m_location_code="";
				}*/
				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				
				String m_branch_name_2="";
				
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+mm_location_code+"'),'-'),"+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_branch_id+"'),'-')"+ // added by udara on 28-08-2013
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
					m_branch_name_2 = rs1.getString(4);
				}
				
				
				double m_tot_rec=0;
				
				/*added by ns on 12/11/2012*/
				if (m_location_code.trim().equals("ALL")) {
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+  //"  ENT_USER, "+//4 // mod by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-')"+ //added by kanishka on 03-07-2013  //14
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ m_user_query + // commented by udara on 25-07-2013 for performance issue
						" "+ m_user_query + " "+ // added by udara on 25-07-2013
						// " AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08 // commented by udara on 28-08-2013
						//"  AND STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013  //comment by kanishka dilshan on 17-09-2013
						//" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // commented by udara on 25-07-2013 for performance
						" "+ location_query_part + " "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "); // " ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else{
					/*
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-') SUB_REC_NO "+ //added by kanishka on 03-07-2013  //14
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ m_user_query +
						" AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013 for performance
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					*/
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" B.FINANCE_NO  FIN_NO, "+ // " NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // "  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-') SUB_REC_NO "+ //added by kanishka on 03-07-2013  //14
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ m_user_query + //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'))) = '"+m_location_code+"' "+ m_user_query +
						//" AND A.STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08 // commented by udara on 28-08-2013
						//" AND A.STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013 //comment by kanishka dilshan on 17-09-2013
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013 for performance
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
					
				}
				
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_location_code='"+mm_location_code+"';"); // added by udara on 03-12-2012
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
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
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&user=&location_code=\"+m_location_code;");// commented by udara on 27-02-2013
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&user=&location_code=\"+m_location_code+\"&branch_id=\"+m_branch_id;"); // added by udara on 27-02-2013
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");
				
				// added by udara on 26-08-2013
				out.println("	function show_deposit_slip(m_deposit_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Receipt_Deposit_Slip?chksql=print_deposit_slip&deposit_no=\"+m_deposit_no;");
				out.println("    window.open(m_url); ");
				out.println("	}");
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				
				out.println("<TABLE  WIDTH='100%'  align='Center'>");
				out.println("<TR><TD align='Center' ><B>LAKDERANA INVESTMENTS LIMITED.</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%'  >");
				out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Location : "+m_branch_name_2+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					/*out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='5%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='15%' ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='8%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('EFF_VALDATE') ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
					out.println("<td width='5%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='13%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='5%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>"); */
					out.println("<table width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%'><b>Receipt No</b></td>"); 
					out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
					out.println("<td width='5%'  style= cursor:hand; onclick=sort_data('SETTLE_MODE') ><b>Settle Mode</b></td>"); // mod by udara on 29-08-2013
					out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
					out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
					out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
					out.println("<td width='3%'  style= cursor:hand; onclick=sort_data('STATUS') ><b>Status</b></td>"); 
					//out.println("<td width='10%' ><b>Account</b></td>"); 
					out.println("</tr>"); 
					
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td >"+i+"</td>"); //width='1%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
					out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
					out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
					out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
					if(rs1.getDouble(15) == 0 ){
						out.println("<td class=div_input >Insurance</td>");  //pathum 30/11/2012
					}else{
						out.println("<td class=div_input >Rental</td>");
					}
					out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
					out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
					out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
					//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input align='right' >___________</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				
				// added by udara on 28-08-2013
				
				if (m_location_code.trim().equals("")) {
					m_location_code="ALL";
				}
				
				if (m_location_code.trim().equals("ALL")) {
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+  //"  ENT_USER, "+//4 // mod by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-')"+ //added by kanishka on 03-07-2013  //14
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+
						" WHERE  "+//TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+	//COMMENTED BY KANISHKA ON 03-09-2013
						//	" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+	//COMMENTED BY KANISHKA ON 03-09-2013
						" TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013
						" AND TO_DATE(TO_CHAR(REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013
						
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ m_user_query + // commented by udara on 25-07-2013 for performance issue
						" "+ m_user_query + " "+ // added by udara on 25-07-2013
						// " AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08 // commented by udara on 28-08-2013
						"  AND STATUS  IN ('C','CAD') "+ // added by udara on 28-08-2013
						//" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // commented by udara on 25-07-2013 for performance
						" "+ location_query_part + " "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "); // " ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else{					
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" B.FINANCE_NO  FIN_NO, "+ // " NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // "  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-') SUB_REC_NO "+ //added by kanishka on 03-07-2013  //14
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
					//	" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ //commented by kanishka on 03-09-2013
					//	" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ //commented by kanishka on 03-09-2013
						" TO_DATE(TO_CHAR(A.REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013
						" AND TO_DATE(TO_CHAR(A.REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+//ADDED BY KANISHKA ON 03-09-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ m_user_query + //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'))) = '"+m_location_code+"' "+ m_user_query +
						//" AND A.STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08 // commented by udara on 28-08-2013
						" AND A.STATUS  IN ('C','CAD') "+ // added by udara on 28-08-2013
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013 for performance
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
					
				}
				
				
				//boolean mflag=true;							
				//boolean 
				more = rs1.next();
				
				double m_tot_rec_can = 0;
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
				out.println("<TR><TD align='Center' ><B> Cancelled Receipts  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					
					out.println("<table width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%'><b>Receipt No</b></td>"); 
					out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
					out.println("<td width='5%' ><b>Settle Mode</b></td>"); 
					out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
					out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
					out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
					out.println("<td width='3%' ><b>Status</b></td>"); 
					//out.println("<td width='10%' ><b>Account</b></td>"); 
					out.println("</tr>"); 
					
				}
				
				int i2=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td >"+i2+"</td>"); //width='1%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
					out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
					out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
					out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
					if(rs1.getDouble(15) == 0 ){
						out.println("<td class=div_input >Insurance</td>");  //pathum 30/11/2012
					}else{
						out.println("<td class=div_input >Rental</td>");
					}
					out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
					out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
					out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
					//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("</tr>");
					m_tot_rec_can=m_tot_rec_can+rs1.getDouble(2);
					i2++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input align='right' >___________</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec_can)+"</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				
				// end by udara on 28-08-2013
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				/*rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
				" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08
				" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
				
				" GROUP BY SETTLE_MODE ");*/
				
				
				//if (m_location_code.trim().equals("ALL")) {  // added by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+ // commented by udara on 28-08-2013
						//" AND STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013 // commented by Kanishka on 20-09-2013
						" "+ location_query_part + " "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod  by udara on 25-07-2013 for performance
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ // commented by udara on 25-07-2013 for performance
						" "+ m_user_query + " "+
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS <>'C' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}	
				else{
					
					// commented by udara on 25-07-2013
					/*
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ 
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS <>'C' "+
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+m_user_query +
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS <>'C' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					*/
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND A.STATUS <>'C' "+ // commented by udara on 28-08-2013
						//" AND STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013 // commented by Kanishka on 20-09-2013
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+  m_user_query + //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+m_user_query +
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS <>'C' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}	
				
				out.println("<p style=\"page-break-after:always\"></p>");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br>"); 
				
				
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A "+  // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						// " AND STATUS <>'C' "+ // commented by udara on 28-08-2013
						//" AND STATUS NOT IN ('C','CAD') "+  // added by udara on 28-08-2013// // commented by Kanishka on 20-09-2013
						" AND SETTLE_MODE = 'STD_ORD' "+
						" AND ACC_NO = 'TRANSFERAC' "+
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ // commented by udara on 25-07-2013 for performance issue
						" "  +m_user_query + " "+
						" GROUP BY SETTLE_MODE "+
						" ORDER BY SETTLE_MODE ");
				} else {
					
					// commented by udara on 25-07-2013
					/*
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ 
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS <>'C' "+
						" AND SETTLE_MODE = 'STD_ORD' "+
						" AND ACC_NO = 'TRANSFERAC' "+
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+m_user_query +
						" GROUP BY SETTLE_MODE "+
						" ORDER BY SETTLE_MODE ");
					*/
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT A.SETTLE_MODE ,SUM(A.REC_AMOUNT) ,COUNT(A.REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND A.STATUS <>'C' "+ // commented by udara on 28-08-2013
						//" AND A.STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013 // commented by Kanishka on 20-09-2013
						" AND A.SETTLE_MODE = 'STD_ORD' "+
						" AND A.ACC_NO = 'TRANSFERAC' "+
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ m_user_query + //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+m_user_query +
						" GROUP BY SETTLE_MODE "+
						" ORDER BY SETTLE_MODE ");
					
				}
				
				
				
				
				double m_transfer_account_count = 0;
				double m_transfer_account_amt = 0;
				
				while(rs1.next()){
					m_transfer_account_count = rs1.getDouble(3);
					m_transfer_account_amt = rs1.getDouble(2);
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' class=div_input >STD_ORD - Transfer Account</td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_transfer_account_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_transfer_account_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				
				
				m_rec_count=0;
				m_rec_amt=0;
				
				/*rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
				" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08
				" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
				" GROUP BY ENT_USER,SETTLE_MODE "+
				" ORDER BY ENT_USER,SETTLE_MODE ");*/
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER,TYPE,SUM(TOTAL),COUNT(COUNT), DUMMY FROM( "+
						" SELECT ENT_USER ENT_USER,SETTLE_MODE TYPE,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						// " AND STATUS <>'C' "+m_user_query + // commented by udara on 28-08-2013
						//" AND STATUS NOT IN ('C','CAD') "+m_user_query + // added by udara on 28-08-2013  // commented by Kanishka on 20-09-2013
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ // commented by udara on 25-07-2013
						//" --GROUP BY ENT_USER,SETTLE_MODE "+
						//" --ORDER BY ENT_USER,SETTLE_MODE "+
						" UNION ALL "+
						" SELECT REC_ENT_BY ENT_USER,DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT ,2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  "+
						" TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query2 +
						" AND DEPOSIT_STATUS <>'C' "+
						//" --AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" --GROUP BY REC_ENT_BY,PAY_TYPE "+
						//" --ORDER BY REC_ENT_BY,PAY_TYPE "+
						" ) "+
						" GROUP BY ENT_USER,TYPE,DUMMY "+
						" ORDER BY ENT_USER,TYPE,DUMMY ");
				} else{
					
					// commented by udara on 25-07-2013
					/*
					rs1= stmt1.executeQuery(" SELECT ENT_USER,TYPE,SUM(TOTAL),COUNT(COUNT), DUMMY FROM( "+
						" SELECT ENT_USER ENT_USER,SETTLE_MODE TYPE,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS <>'C' "+m_user_query +
						" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						//" --GROUP BY ENT_USER,SETTLE_MODE "+
						//" --ORDER BY ENT_USER,SETTLE_MODE "+
						" UNION ALL "+
						" SELECT REC_ENT_BY ENT_USER,DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT ,2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  "+
						" TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query2 +
						" AND DEPOSIT_STATUS <>'C' "+
						//" --AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" --GROUP BY REC_ENT_BY,PAY_TYPE "+
						//" --ORDER BY REC_ENT_BY,PAY_TYPE "+
						" ) "+
						" GROUP BY ENT_USER,TYPE,DUMMY "+
						" ORDER BY ENT_USER,TYPE,DUMMY ");
					*/
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" "+
					//out.println(" "+
						" SELECT ENT_USER,TYPE,SUM(TOTAL),COUNT(COUNT), DUMMY FROM( "+
						" SELECT A.ENT_USER ENT_USER,A.SETTLE_MODE TYPE,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND A.STATUS <>'C' "+m_user_query + // commented by udara on 28-08-2013
						//" AND A.STATUS NOT IN ('C','CAD') <>'C' "+m_user_query + // added by udara on 28-08-2013 // udara 18-09-2013
						//" AND A.STATUS NOT IN ('C','CAD')  "+m_user_query +  // commented by Kanishka on 20-09-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						//" --GROUP BY ENT_USER,SETTLE_MODE "+
						//" --ORDER BY ENT_USER,SETTLE_MODE "+
						" UNION ALL "+
						" SELECT REC_ENT_BY ENT_USER,DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT ,2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  "+
						" TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query2 +
						" AND DEPOSIT_STATUS <>'C' "+
						//" --AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" --GROUP BY REC_ENT_BY,PAY_TYPE "+
						//" --ORDER BY REC_ENT_BY,PAY_TYPE "+
						" ) "+
						" GROUP BY ENT_USER,TYPE,DUMMY "+
						" ORDER BY ENT_USER,TYPE,DUMMY ");
					
					
				}
				
				
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br>"); 
				
				// commented by udara on 25-07-2013
				/*
				rs1= stmt1.executeQuery(" SELECT ENT_USER ,SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS <>'C' "+
					" AND SETTLE_MODE = 'STD_ORD' "+
					" AND ACC_NO = 'TRANSFERAC' "+m_user_query +
					" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
					" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
					" GROUP BY ENT_USER,SETTLE_MODE "+
					" ORDER BY ENT_USER,SETTLE_MODE ");
				*/
				
				
				// added by udara on 25-07-2013
				if(mm_location_code.trim().equals("ALL")){
					
					rs1= stmt1.executeQuery(" SELECT ENT_USER ,SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+ // commented by udara on 28-08-2013
						//" AND STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013 // commented by Kanishka on 20-09-2013
						" AND SETTLE_MODE = 'STD_ORD' "+
						" AND ACC_NO = 'TRANSFERAC' "+m_user_query +
						// " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY ENT_USER,SETTLE_MODE "+
						" ORDER BY ENT_USER,SETTLE_MODE ");
					
				}
				else{
					
					// commented by udara on 25-07-2013
					/*
					rs1= stmt1.executeQuery(" SELECT ENT_USER ,SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND STATUS <>'C' "+
					" AND SETTLE_MODE = 'STD_ORD' "+
					" AND ACC_NO = 'TRANSFERAC' "+m_user_query +
					" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
					" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
					" GROUP BY ENT_USER,SETTLE_MODE "+
					" ORDER BY ENT_USER,SETTLE_MODE ");
					*/
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT A.ENT_USER ,A.SETTLE_MODE ,SUM(A.REC_AMOUNT) ,COUNT(A.REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND A.STATUS <>'C' "+ // commented by udara on 28-08-2013
						//" AND A.STATUS NOT IN ('C','CAD') "+  // added by udara on 28-08-2013  // commented by Kanishka on 20-09-2013
						" AND A.SETTLE_MODE = 'STD_ORD' "+
						" AND A.ACC_NO = 'TRANSFERAC' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.ENT_USER,A.SETTLE_MODE "+
						" ORDER BY A.ENT_USER,A.SETTLE_MODE ");
					
				}
				// end by udara on 25-07-2013
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				
				m_transfer_account_count =0;
				m_transfer_account_amt   =0;
				while(rs1.next()){
					
					m_transfer_account_count=m_transfer_account_count+rs1.getDouble(4);
					m_transfer_account_amt=m_transfer_account_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >STD_ORD - Transfer Account</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_transfer_account_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_transfer_account_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				
				
				// added by ns on 05-04-2011
				/*rs1= stmt1.executeQuery(" SELECT DECODE(STATUS,'B','Banked','E','Entered'),SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
				" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND STATUS IN ('E','B') "+ //ADDED BY NUWAN ON 01-04-08
				" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
				" GROUP BY STATUS "+
				" ORDER BY STATUS ");*/
				
				
				/*out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Status</b></DIV></td>");
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				m_rec_count=0;
				m_rec_amt=0;
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				//out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); */
				
				//end section 
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND STATUS = 'B' "+m_user_query +
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%'  "+ // commented by udara on 25-07-2013 for performance issue
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				} else{
					
					// commented by udara on 25-07-2013
					/*
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ 
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND STATUS = 'B' "+m_user_query +
						" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					*/
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
					
				}
				
				
				// commented by udara 05-08-2013
				/*
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Status</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 
				
			
				
				double m_rec_count_1 =0;
				double m_rec_amt_1   =0;
				double m_rec_count_final =0;
				double m_rec_amt_final   =0;
				
				//m_rec_count_final = m_rec_count_final + tot_count; // added by udara on 02-08-2013
				//m_rec_amt_final = m_rec_amt_final + total_bbf; // added by udara on 02-08-2013
				
				while(rs1.next()){
					
					m_rec_count_1=m_rec_count_1+rs1.getDouble(3);
					m_rec_amt_1=m_rec_amt_1+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				m_rec_count_final = m_rec_count_final + m_rec_count_1;
				m_rec_amt_final   = m_rec_amt_final + m_rec_amt_1;
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>BANKED</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count_1)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt_1)+"</b></td>");
				out.println("</tr>");

				
				out.println("<tr>");
				//out.println("<td width='20%' class=div_input ><B>CASH IN HAND</B></td>");
				//out.println("<td width='15%' class=div_input align='right' ></td>");
				//out.println("<td width='15%' class=div_input align='right' ></td>");
				out.println("<td width='50%' class=div_input colspan=3 ><B>CASH IN HAND</B></td>");
				out.println("</tr>");
				
				//double tot_cash_in_hand = 0; // added by udara on 02-08-2013
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ 
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND STATUS NOT IN ('C','B') "+m_user_query +
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%'  "+ // commented by udara on 25-07-2013 for performance issue
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND A.STATUS NOT IN ('C','B') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				double m_rec_count_2 =0;
				double m_rec_amt_2   =0;
				while(rs1.next()){
					
					//tot_cash_in_hand = tot_cash_in_hand + rs1.getDouble(2); // added by udara on 02-08-2013
					
					m_rec_count_2=m_rec_count_2+rs1.getDouble(3);
					m_rec_amt_2=m_rec_amt_2+rs1.getDouble(2);
					
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				m_rec_count_final = m_rec_count_final + m_rec_count_2;
				m_rec_amt_final   = m_rec_amt_final + m_rec_amt_2;

				
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>ENTERED</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count_2)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt_2)+"</b></td>");
				out.println("</tr>");
				
				//m_rec_count_final = m_rec_count_final + tot_count; // added by udara on 02-08-2013
				//m_rec_amt_final = m_rec_amt_final + total_bbf; // added by udara on 02-08-2013
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count_final)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt_final)+"</b></td>");
				out.println("</tr>");

				
				out.println("</table>"); 
				
				*/
				
				//out.println("<br><br><br>"); 
				
				
				// added by udara on 05-08-2013 ===================================================================================
				out.println("<table align='center' width='50%' class='table' border='1'>");	
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B>B/B/F</B></U></td>");
				out.println("</tr>");
				
				double tot_count = 0;
				double total_bbf = 0;
				
				double total_collections = 0;
				double total_banked = 0;
				
				// BBF
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" "+
						//out.println(" "+
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+m_user_query + // " AND STATUS NOT IN ('C','B') "+m_user_query + // //commented by kanishka on 27-09-2013
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND A.REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B') "+ // comented by udara on 24-09-2013
						
						 // added by udara on 24-09-2013
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT REC_NO "+
						            " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						            " WHERE MOD_USER  = 'DEPOSIT' AND STATUS='B' "+
						 " ) "+
						            
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT RECEIPT_NO  "+
						            " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
						            " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						 " ) "+
						 // end by udara on 24-09-2013
						
						//" AND A.REC_NO NOT IN (SELECT RECEIPT_NO FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS WHERE RECEIPT_NO = A.REC_NO )  "+ // added by udara on 18-09-2013
						
						// added by Udara & Kanishka 30-09-2013
						" UNION ALL  "+ 
						
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ 
						" WHERE A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						//" AND  A.EFF_VALDATE  < TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + 
						" "+location_query_part+"  "+
						
						
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" "+
						//out.println(" "+
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+m_user_query + // mod by udara on 28-08-2013 //" AND A.STATUS NOT IN ('C') "+m_user_query +  // " AND A.STATUS NOT IN ('C','B') "+m_user_query +  //commented on 27-09-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013 
						" AND A.REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B') "+ // commented by udara on 24-09-2013
						
						// added by udara on 24-09-2013
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT REC_NO "+
						            " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						            " WHERE MOD_USER  = 'DEPOSIT' AND STATUS='B' "+
						 " ) "+
						            
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT RECEIPT_NO  "+
						            " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
						            " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						 " ) "+
						 // end by udara on 24-09-2013
						
						// added by udara & kanishka 30-09-2013
						"  UNION ALL "+	
						
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO   "+
						" AND A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						//" AND A.EFF_VALDATE < TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+ 	
						
						
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				
				while(rs1.next()){
					
					tot_count = tot_count + rs1.getDouble(3);
					total_bbf = total_bbf + rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				//CANCELLED RECEIPTS [BEFORE REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				
				double total_bbf_cancelled = 0;
				int cancelled_bbf_count = 0;
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT SUM(TOTAL),COUNT(COUNT) FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ 
						" WHERE A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						//" AND  A.EFF_VALDATE  < TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + 
						" "+location_query_part+"  "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						//" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" WHERE TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ 
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						"");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT SUM(TOTAL),COUNT(COUNT) FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO   "+
						" AND A.EFF_VALDATE < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						//" AND A.EFF_VALDATE < TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.REC_CANCEL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+ 
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						//" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" WHERE TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ 
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" ");
					
				}
				
				while(rs1.next()){
					
					total_bbf_cancelled = total_bbf_cancelled + rs1.getDouble(1);
					cancelled_bbf_count = cancelled_bbf_count +  rs1.getInt(2);
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >CANCELLED </td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(2))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(1))+"</b></td>");
					out.println("</tr>");
				}
				
				total_bbf	= 	total_bbf -  total_bbf_cancelled;
				tot_count   = 	tot_count - cancelled_bbf_count;
				//END CANCELLED RECEIPTS [BEFORE REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL B/B/F</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(tot_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_bbf)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> COLLECTIONS </B></U></td>");
				out.println("</tr>");
				
				// collections
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND A.STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013  // commented by Kanishka on 20-09-2013
						"  "+m_user_query + // " AND STATUS NOT IN ('C') "+m_user_query + //" AND STATUS NOT IN ('C','B') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						//" AND A.STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013  // commented by Kanishka on 20-09-2013
						"  "+m_user_query + // " AND A.STATUS NOT IN ('C') "+m_user_query + // " AND A.STATUS NOT IN ('C','B') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					
					total_collections = total_collections + rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				
				
				//CANCELLED RECEIPTS [ REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				
				double total_cancelled = 0;
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT SUM(TOTAL),COUNT(COUNT)FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ 
						" WHERE A.EFF_VALDATE = TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  A.EFF_VALDATE  = TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND  REC_CANCEL_DATE = TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  REC_CANCEL_DATE = TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query +
						" "+location_query_part+"  "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ 
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT SUM(TOTAL),COUNT(COUNT) FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" A.EFF_VALDATE = TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.EFF_VALDATE = TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND  A.REC_CANCEL_DATE = TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  A.REC_CANCEL_DATE = TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+"  "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ 
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" ");
					
				}
				
				while(rs1.next()){
					
					total_cancelled = total_cancelled + rs1.getDouble(1);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >CANCELLED </td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(2))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(1))+"</b></td>");
					out.println("</tr>");
				}
				
				total_collections	= 	total_collections -  total_cancelled;
				//END CANCELLED RECEIPTS [ REPORT RUNNING DATE ] -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL COLLECTIONS</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b> &nbsp; </b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_collections)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				//CANCELLED RECEIPTS -- ADDED BY KANISHKA DILSHAN ON 20-09-2013
				/*
				double total_cancelled = 0;
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> CANCELLED </B></U></td>");
				out.println("</tr>");
				
				// collections
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + // " AND STATUS NOT IN ('C') "+m_user_query + //" AND STATUS NOT IN ('C','B') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS IN ('C','CAD') "+ 
						"  "+m_user_query + // " AND A.STATUS NOT IN ('C') "+m_user_query + // " AND A.STATUS NOT IN ('C','B') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					
					total_cancelled = total_cancelled + rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL CANCELLED</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b> &nbsp; </b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_cancelled)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				*/
				//END KANISHKA DILSHAN 
				
				
				
				// banked
				
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" "+
						// commented by udara on 18-09-2013
						/*
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						//" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // commented by udara on 13-08-2013
						//" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // commented by udara on 13-08-2013
						" WHERE  TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						//" AND STATUS <>'C' "+
						" AND STATUS = 'B' "+m_user_query +
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%'  "+ // commented by udara on 25-07-2013 for performance issue
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						*/
						
						// added by udara on 18-09-2013
						
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B  "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						" AND A.REC_NO = B.RECEIPT_NO  "+
						" AND B.STATUS = 'Y' "+ 
						" AND A.STATUS = 'B' "+m_user_query +
						" "+location_query_part+"  "+ 
						
						
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				} else{
					
					
					rs1= stmt1.executeQuery(" "+
					//out.println(" "+
						" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						
						// commented by udara on 20-09-2013
						/*
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO   "+
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // commented by udara on 20-09-2013
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // commented by udara on 20-09-2013
						
						" AND  TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 20-09-2013
						" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 20-09-2013
						
						
						//" AND STATUS <>'C' "+
						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						*/
						
						
						// added by udara on 20-09-2013
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS D"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.REC_NO = D.RECEIPT_NO AND B.FINANCE_NO = C.FINANCE_NO   "+
						
						" AND  TO_DATE(TO_CHAR(D.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(D.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						" AND D.STATUS = 'Y' "+ 

						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+" "+ 
						// end by udara on 20-09-2013
						
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
					
				}
				
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> BANKED </B></U></td>");
				out.println("</tr>");
				
				while(rs1.next()){
					
					
					total_banked = total_banked +rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>BANKED TOTAL </B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_banked)+"</b></td>");
				out.println("</tr>");
				
				
				// cash in hand added by udara on 14-08-2013
				
				double cash_in_hand = 0;
				cash_in_hand = (total_bbf + total_collections) - total_banked;
				//cash_in_hand = (total_bbf + total_collections) - (total_banked + total_cancelled);//cash in hand modified by kanishka dilshan on 20-09-2013
				
				/*
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND STATUS IN ('E') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					

					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('E') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013

						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					cash_in_hand = cash_in_hand + rs1.getDouble(2);
				}
				*/
				// end by udara on 14-08-2013
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>CASH IN HAND</B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				//out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format((total_bbf + total_collections) - total_banked)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(cash_in_hand)+"</b></td>");
				out.println("</tr>");
				
				
				
				out.println("</table>"); 
				
				out.println("<br><br><br>");
				
				// end by udara on 05-08-2013 ===================================================================================
				
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>5000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>2000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>1000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>500.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>100.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>50.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>20.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>10.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>Coins</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input ><b>TOTAL</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>PETTY CASH FLOAT</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input > &nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>Excess/Shortage</b></td>");
				out.println("<td width='15%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				// added by udara on 08-08-2013
				if(!m_branch_id.equals("")){
					
					out.println("<table align='center' width='50%' class='table' border='0'>");	
					
					out.println("<tr>");
					out.println("<td width='20%' class=div_input ><B> Deposit Numbers </B></td>");
					out.println("<td width='15%' class=div_input align=right ><B> Amount </B></td>");
					out.println("<td width='15%' class=div_input > &nbsp; </td>");
					out.println("</tr>");
					
					// commented by udara on 26-08-2013
					/*
					rs1= stmt1.executeQuery(" "+
						 " SELECT DISTINCT B.DIPOSIT_NO "+
							 " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
								 " WHERE A.REC_NO = B.RECEIPT_NO "+ 
								 " AND A.STATUS = 'B' "+
								 " AND "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(B.ENT_USER) = '"+m_branch_id+"' "+ // added by udara on 09-08-2013
								 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
								 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						" ");
					*/
					
					// added by udara on 26-08-2013
					rs1= stmt1.executeQuery(" "+
						" SELECT DISTINCT B.DIPOSIT_NO, SUM(B.AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
						" WHERE A.REC_NO = B.RECEIPT_NO "+ 
						" AND A.STATUS = 'B' "+
						" AND "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(B.ENT_USER) = '"+m_branch_id+"' "+ // added by udara on 09-08-2013
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						
						//" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // commented by udara on 18-09-2013
						//" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+ // commented by udara on 18-09-2013
						" AND B.STATUS = 'Y' "+ // added by udara on 18-09-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 18-09-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+ // added by udara on 18-09-2013
						
						" AND A.SETTLE_MODE NOT IN ('DIR_DEP') "+ 
						" GROUP BY B.DIPOSIT_NO "+
						" ");
					
					while(rs1.next()){
						
						out.println("<tr>");
						out.println("<td width='20%' class=div_input style=\"cursor:hand\" onclick=\"show_deposit_slip('"+rs1.getString(1)+"')\" ><u> "+rs1.getString(1)+" </u></td>");
						out.println("<td width='15%' class=div_input align=right > "+nf.format(rs1.getDouble(2))+" </td>");
						out.println("<td width='15%' class=div_input > &nbsp;</td>");
						out.println("</tr>");
						
					}
					
					
					out.println("</table>");
					
					out.println("<br><br><br>"); 
				}
				// end by udara on 08-08-2013
				
				
				out.println("<table align='center' width='50%' class='table' border='0'>");		
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'>.......................</td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input align='center'>.......................</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'><b>Cashier</b></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input align='center'><b>Date</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				out.println("<table align='center' width='50%' class='table' border='0'>");		
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'>.......................</td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'><b>Checked by</b></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				// added by udara on 29-08-2013
				
				
				if(!m_branch_id.equals("")){
					
					rs1= stmt1.executeQuery("  "+
						" SELECT "+
						" A.REC_NO, "+ //1
						" A.REC_AMOUNT, "+ //2
						" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+ //3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER,  "+
						" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+ //5
						" A.CLIENT_CODE, "+ //6
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ //7
						" NVL(A.SUS_REF_NO,'-'), "+ //8
						" A.SETTLE_MODE, "+ //9
						" NVL(A.OTH_COMMENTS,'-') , "+ //10
						" B.FINANCE_NO  FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						" SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT, "+
						" NVL(A.SUB_REC_NO,'-') SUB_REC_NO, "+ //14
						" DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE   "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_branch_id+"' "+
						//" AND A.STATUS NOT IN ('C','CAD')  "+
						" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) <> '"+m_branch_id+"' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "+
						" ");
					
					
					more = rs1.next();
					
					double m_tot_rec_nn = 0;
					
					out.println("<br><br><br>");
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
					out.println("<TR><TD align='Center' ><B> Receipts of Application Location  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more){
						
						out.println("<table width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%'><b>Receipt No</b></td>"); 
						out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
						out.println("<td width='5%' ><b>Settle Mode</b></td>"); 
						out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
						out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
						out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
						out.println("<td width='3%' ><b>Status</b></td>"); 
						out.println("</tr>"); 
						
					}
					
					int i3=1;
					while(more){
						
						if(mflag){
							out.println("<tr class=tr_input>");
							mflag=false;
						}
						else{
							out.println("<tr class=tr_input1>");
							mflag=true;
						}
						out.println("<td >"+i3+"</td>"); //width='1%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
						out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
						out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
						out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
						if(rs1.getDouble(15) == 0 ){
							out.println("<td class=div_input >Insurance</td>");  //pathum 30/11/2012
						}else{
							out.println("<td class=div_input >Rental</td>");
						}
						out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
						out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
						out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
						//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
						out.println("</tr>");
						m_tot_rec_nn=m_tot_rec_nn+rs1.getDouble(2);
						i3++;
						more = rs1.next();
						
					}	
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' >___________</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec_nn)+"</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br><br>"); 
				}
				
				// end by udara on 29-08-2013
				
				
				// added by udara 11-11-2013
				/*
				if(!m_branch_id.equals("")){
					
					rs1= stmt1.executeQuery("  "+
						" SELECT "+
						" A.REC_NO, "+ //1
						" A.REC_AMOUNT, "+ //2
						" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+ //3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER,  "+
						" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+ //5
						" A.CLIENT_CODE, "+ //6
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ //7
						" NVL(A.SUS_REF_NO,'-'), "+ //8
						" A.SETTLE_MODE, "+ //9
						" NVL(A.OTH_COMMENTS,'-') , "+ //10
						" B.FINANCE_NO  FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						" SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT, "+
						" NVL(A.SUB_REC_NO,'-') SUB_REC_NO, "+ //14
						" DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE   "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_branch_id+"' "+
						" AND A.STATUS  IN ('C','CAD')  "+
						" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) <> '"+m_branch_id+"' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "+
						" ");
					
					
					more = rs1.next();
					
					double m_tot_rec_nn = 0;
					
					out.println("<br><br><br>");
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
					out.println("<TR><TD align='Center' ><B> Cancelled Receipts of Application Location  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more){
						
						out.println("<table width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%'><b>Receipt No</b></td>"); 
						out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
						out.println("<td width='5%' ><b>Settle Mode</b></td>"); 
						out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
						out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
						out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
						out.println("<td width='3%' ><b>Status</b></td>"); 
						out.println("</tr>"); 
						
					}
					
					int i3=1;
					while(more){
						
						if(mflag){
							out.println("<tr class=tr_input>");
							mflag=false;
						}
						else{
							out.println("<tr class=tr_input1>");
							mflag=true;
						}
						out.println("<td >"+i3+"</td>"); //width='1%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
						out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
						out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
						out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
						if(rs1.getDouble(15) == 0 ){
							out.println("<td class=div_input >Insurance</td>");  //pathum 30/11/2012
						}else{
							out.println("<td class=div_input >Rental</td>");
						}
						out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
						out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
						out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
						//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
						out.println("</tr>");
						m_tot_rec_nn=m_tot_rec_nn+rs1.getDouble(2);
						i3++;
						more = rs1.next();
						
					}	
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' >___________</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec_nn)+"</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br><br>"); 
				}
				*/
				// end by udara on 11-11-2013
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
			}
			
			
			
			else if(m_chksql.equals("load_active_receipts")){
				
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code");
				String mm_location_code = req.getParameter("location_code"); // added by udara on 03-12-2012
				String m_user = req.getParameter("user");
				String m_user_query=""; 
				String m_user_query2=""; 
				
				// added by udara on 29-10-2013
				rs1= stmt1.executeQuery(" SELECT "+
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_branch_id = rs1.getString(1);
				}
				
				if(m_branch_id.equals("HO")){
					m_branch_id = "";
				}
				else{
					m_branch_id = m_branch_id;
				}
				// end by udara on 29-10-2013
				
				
				
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND UPPER(A.ENT_USER) = UPPER('"+m_user+"') ";
					m_user_query2 = "AND UPPER(REC_ENT_BY) = UPPER('"+m_user+"') ";
				}
				
				/*if (m_location_code.trim().equals("ALL")) {
				m_location_code="";
				}*/
				
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"' "; 	
				// end by udara on 25-07-2013
				
				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				String m_branch_name_2 = "";
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+mm_location_code+"'),'-'),"+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_branch_id+"'),'-')"+ // added by udara on 28-08-2013
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
					m_branch_name_2 = rs1.getString(4); // added by udara on 28-08-2013
				}
				
				
				double m_tot_rec=0;
				
				if (m_location_code.trim().equals("ALL")) {
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-') SUB_REC_NO "+//KANISHKA
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+ // mod by udara on 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ // commented by udara on 25-07-2013
						" "+ m_user_query +
						" AND STATUS NOT IN ('C','CAD')"+ //ADDED BY NUWAN ON 01-04-08
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				} else{
					
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" B.FINANCE_NO FIN_NO,  "+ //" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // "  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-') SUB_REC_NO "+//KANISHKA
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ 
						" "+ m_user_query +" "+
						" AND A.STATUS NOT IN ('C','CAD')"+ //ADDED BY NUWAN ON 01-04-08
						"  "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
				}
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_from_date ='"+m_from_date+"';");	
				out.println(" m_to_date='"+m_to_date+"';"); 
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
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
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_active_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&user="+m_user+"&location_code="+mm_location_code+"\";"); // commented by udara on 27-02-2013
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_active_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&user="+m_user+"&location_code="+mm_location_code+"&branch_id=\"+m_branch_id;"); // added by udara on 27-02-2013
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");
				
				// added by udara on 26-08-2013
				out.println("	function show_deposit_slip(m_deposit_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Receipt_Deposit_Slip?chksql=print_deposit_slip&deposit_no=\"+m_deposit_no;");
				out.println("    window.open(m_url); ");
				out.println("	}");
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				
				out.println("<TABLE  WIDTH='100%'  align='Center'>");
				out.println("<TR><TD align='Center' ><B>LAKDERANA INVESTMENTS LIMITED.</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%'  >");
				out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				// added by udara on 28-08-2013
				out.println("<TR><TD align='left' ><B>Location : "+m_branch_name_2+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					
					out.println("<table width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%'><b>Receipt No</b></td>"); 
					out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
					out.println("<td width='5%'  style= cursor:hand; onclick=sort_data('SETTLE_MODE') ><b>Settle Mode</b></td>"); // mod by udara on 29-08-2013
					out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); 
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT') align='right'><b>Amount</b></td>");
					out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
					out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>"); 
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); 
					out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
					out.println("<td width='3%'  style= cursor:hand; onclick=sort_data('STATUS') ><b>Status</b></td>");  // mod by udara on 29-08-2013
					//out.println("<td width='10%' ><b>Account</b></td>"); 
					out.println("</tr>"); 
					
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td >"+i+"</td>"); //width='1%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
					out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
					out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
					out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
					if(rs1.getDouble(15) == 0 ){
						out.println("<td class=div_input >Insurance</td>"); //pathum 30/11/2012
					}else{
						out.println("<td class=div_input >Rental</td>");
					}
					out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
					out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
					out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
					//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input align='right' >___________</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				
				// added by udara on 28-08-2013
				
				// added by udara on 28-08-2013
				
				if (m_location_code.trim().equals("")) {
					m_location_code="ALL";
				}
				
				if (m_location_code.trim().equals("ALL")) {
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+  //"  ENT_USER, "+//4 // mod by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-')"+ //added by kanishka on 03-07-2013  //14
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ m_user_query + // commented by udara on 25-07-2013 for performance issue
						" "+ m_user_query + " "+ // added by udara on 25-07-2013
						// " AND STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08 // commented by udara on 28-08-2013
						"  AND STATUS  IN ('C','CAD') "+ // added by udara on 28-08-2013
						//" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // commented by udara on 25-07-2013 for performance
						" "+ location_query_part + " "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "); // " ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else{					
					
					// added by udara on 25-07-2013
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara on 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" B.FINANCE_NO  FIN_NO, "+ // " NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT "+ // "  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-') SUB_REC_NO "+ //added by kanishka on 03-07-2013  //14
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ m_user_query + //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'))) = '"+m_location_code+"' "+ m_user_query +
						//" AND A.STATUS <>'C'"+ //ADDED BY NUWAN ON 01-04-08 // commented by udara on 28-08-2013
						" AND A.STATUS  IN ('C','CAD') "+ // added by udara on 28-08-2013
						" "+ location_query_part + " "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013 for performance
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
					
				}
				
				
				//boolean mflag=true;							
				//boolean 
				more = rs1.next();
				
				double m_tot_rec_can = 0;
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
				out.println("<TR><TD align='Center' ><B> Cancelled Receipts  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					
					out.println("<table width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%'><b>Receipt No</b></td>"); 
					out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
					out.println("<td width='5%' ><b>Settle Mode</b></td>"); 
					out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
					out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
					out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
					out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
					out.println("<td width='3%' ><b>Status</b></td>"); 
					//out.println("<td width='10%' ><b>Account</b></td>"); 
					out.println("</tr>"); 
					
				}
				
				int i2=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td >"+i2+"</td>"); //width='1%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
					out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
					out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
					out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
					if(rs1.getDouble(15) == 0 ){
						out.println("<td class=div_input >Insurance</td>");  //pathum 30/11/2012
					}else{
						out.println("<td class=div_input >Rental</td>");
					}
					out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
					out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
					out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
					//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("</tr>");
					m_tot_rec_can=m_tot_rec_can+rs1.getDouble(2);
					i2++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input align='right' >___________</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td ></td>"); 
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
				out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec_can)+"</b></td>");
				out.println("<td  class=div_input >&nbsp;</td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				out.println("<td  class=div_input ></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				
				// end by udara on 28-08-2013
				
				// end by udara on 28-082013
				
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if (mm_location_code.trim().equals("ALL")) { // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS NOT IN ('C','CAD')"+
						"  "+location_query_part+"  "+  //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" "+m_user_query +
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS <>'C' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}  else {
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS NOT IN ('C','CAD')"+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" "+m_user_query +
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS <>'C' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				out.println("<p style=\"page-break-after:always\"></p>");
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br>"); 
				
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS NOT IN ('C','CAD') "+
						" AND SETTLE_MODE = 'STD_ORD' "+
						" AND ACC_NO = 'TRANSFERAC' "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" "+m_user_query +
						" GROUP BY SETTLE_MODE "+
						" ORDER BY SETTLE_MODE ");
				} else {
					
					rs1= stmt1.executeQuery(" SELECT A.SETTLE_MODE ,SUM(A.REC_AMOUNT) ,COUNT(A.REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS NOT IN ('C','CAD') "+
						" AND A.SETTLE_MODE = 'STD_ORD' "+
						" AND A.ACC_NO = 'TRANSFERAC' "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+m_user_query +
						" "+m_user_query +
						" GROUP BY A.SETTLE_MODE "+
						" ORDER BY A.SETTLE_MODE ");
					
				}
				
				
				double m_transfer_account_count = 0;
				double m_transfer_account_amt = 0;
				
				while(rs1.next()){
					m_transfer_account_count = rs1.getDouble(3);
					m_transfer_account_amt = rs1.getDouble(2);
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' class=div_input >STD_ORD - Transfer Account</td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_transfer_account_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_transfer_account_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				
				
				m_rec_count=0;
				m_rec_amt=0;
				
				
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER,TYPE,SUM(TOTAL),COUNT(COUNT), DUMMY FROM( "+
						" SELECT ENT_USER ENT_USER,SETTLE_MODE TYPE,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+m_user_query + // commented by udara on 04-03-2013
						" AND STATUS NOT IN ('C','CAD') "+m_user_query + // added by udara on 04-03-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+
						//" --GROUP BY ENT_USER,SETTLE_MODE "+
						//" --ORDER BY ENT_USER,SETTLE_MODE "+
						" UNION ALL "+
						" SELECT REC_ENT_BY ENT_USER,DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT ,2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  "+
						"  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query2 +
						" AND DEPOSIT_STATUS <>'C' "+
						//" --AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" --GROUP BY REC_ENT_BY,PAY_TYPE "+
						//" --ORDER BY REC_ENT_BY,PAY_TYPE "+
						" ) "+
						" GROUP BY ENT_USER,TYPE,DUMMY "+
						" ORDER BY ENT_USER,TYPE,DUMMY ");
				} else {
					
					rs1= stmt1.executeQuery(" SELECT ENT_USER,TYPE,SUM(TOTAL),COUNT(COUNT), DUMMY FROM( "+
						" SELECT A.ENT_USER ENT_USER,A.SETTLE_MODE TYPE,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+m_user_query + // commented by udara on 04-03-2013
						" AND A.STATUS NOT IN ('C','CAD') "+m_user_query + // added by udara on 04-03-2013 
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+
						//" --GROUP BY ENT_USER,SETTLE_MODE "+
						//" --ORDER BY ENT_USER,SETTLE_MODE "+
						" UNION ALL "+
						" SELECT REC_ENT_BY ENT_USER,DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT ,2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE "+
						"  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query2 +
						" AND DEPOSIT_STATUS <>'C' "+
						//" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" --AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" --GROUP BY REC_ENT_BY,PAY_TYPE "+
						//" --ORDER BY REC_ENT_BY,PAY_TYPE "+
						" ) "+
						" GROUP BY ENT_USER,TYPE,DUMMY "+
						" ORDER BY ENT_USER,TYPE,DUMMY ");
					
				}
				
				
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				
				out.println("<br>"); 
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER ,SETTLE_MODE ,SUM(REC_AMOUNT) ,COUNT(REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS NOT IN ('C','CAD') "+
						" AND SETTLE_MODE = 'STD_ORD' "+
						" AND ACC_NO = 'TRANSFERAC' "+m_user_query +
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" "+location_query_part+"  "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY ENT_USER,SETTLE_MODE "+
						" ORDER BY ENT_USER,SETTLE_MODE ");
				} else {
					
					rs1= stmt1.executeQuery(" SELECT A.ENT_USER ,A.SETTLE_MODE ,SUM(A.REC_AMOUNT) ,COUNT(A.REC_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS NOT IN ('C','CAD') "+
						" AND A.SETTLE_MODE = 'STD_ORD' "+
						" AND A.ACC_NO = 'TRANSFERAC' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" "+location_query_part+"  "+  //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.ENT_USER,A.SETTLE_MODE "+
						" ORDER BY A.ENT_USER,A.SETTLE_MODE ");
					
					
				}
				
				
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				
				m_transfer_account_count =0;
				m_transfer_account_amt   =0;
				while(rs1.next()){
					
					m_transfer_account_count=m_transfer_account_count+rs1.getDouble(4);
					m_transfer_account_amt=m_transfer_account_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >STD_ORD - Transfer Account</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_transfer_account_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_transfer_account_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				
				/*
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND STATUS = 'B' "+m_user_query +
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%'  "+
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				} else {
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				*/
				
				
				/*
				// added by udara on 05-08-2013 ===================================================================================
				out.println("<table align='center' width='50%' class='table' border='1'>");	
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B>B/B/F</B></U></td>");
				out.println("</tr>");
				
				double tot_count = 0;
				double total_bbf = 0;
				
				double total_collections = 0;
				double total_banked = 0;
				
				// BBF
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND STATUS NOT IN ('C','CAD') "+m_user_query + // " AND STATUS NOT IN ('C','B') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B' ) "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					

					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND STATUS IN ('E') "+m_user_query + // " AND A.STATUS NOT IN ('C','B','CAD') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						" AND REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B' ) "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				

				while(rs1.next()){
					
					tot_count = tot_count + rs1.getDouble(3);
					total_bbf = total_bbf + rs1.getDouble(2);
									
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL B/B/F</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(tot_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_bbf)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> COLLECTIONS </B></U></td>");
				out.println("</tr>");
				
				
				
				// collections
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND STATUS NOT IN ('C','B','CAD') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					

					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','B') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013

						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					
					total_collections = total_collections + rs1.getDouble(2);
									
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL COLLECTIONS</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b> &nbsp; </b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_collections)+"</b></td>");
				out.println("</tr>");
				
				
				// banked
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 06-08-2013
						//" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // commented by udara on 13-08-2013
						//" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // commented by udara on 13-08-2013
						" WHERE  TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						//" AND STATUS <>'C' "+
						" AND STATUS = 'B' "+m_user_query +
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%'  "+ // commented by udara on 25-07-2013 for performance issue
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				} else{
					
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
					
				}

				
				
				while(rs1.next()){
					

					total_banked = total_banked +rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				
				// added by udara on 14-08-2013
				double cash_in_hand = 0;
				
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND STATUS IN ('E') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					

					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('E') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013

						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					cash_in_hand = cash_in_hand + rs1.getDouble(2);
				}
				
				
				// end by udara on 14-08-2013

				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>BANKED</B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_banked)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>CASH IN HAND</B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				//out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format((total_bbf + total_collections) - total_banked)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(cash_in_hand)+"</b></td>");
				out.println("</tr>");
				
				
				
				out.println("</table>"); 
				
				out.println("<br><br><br>");
				*/
				
				// added by udara on 16-08-2013
				out.println("<table align='center' width='50%' class='table' border='1'>");	
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B>B/B/F</B></U></td>");
				out.println("</tr>");
				
				double tot_count = 0;
				double total_bbf = 0;
				
				double total_collections = 0;
				double total_banked = 0;
				
				// BBF
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+m_user_query + // " AND STATUS NOT IN ('C','B') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						" AND A.REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B') "+ // commented by udara on 24-09-2013
						
						 // added by udara on 24-09-2013
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT REC_NO "+
						            " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						            " WHERE MOD_USER  = 'DEPOSIT' AND STATUS='B' "+
						 " ) "+
						            
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT RECEIPT_NO  "+
						            " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
						            " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						 " ) "+
						 // end by udara on 24-09-2013
						
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+m_user_query +  // mod by udara on 28-08-2013 // " AND A.STATUS NOT IN ('C') "+m_user_query +  // " AND A.STATUS NOT IN ('C','B') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						" AND A.REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND STATUS='B') "+
						
						// added by udara on 24-09-2013
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT REC_NO "+
						            " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						            " WHERE MOD_USER  = 'DEPOSIT' AND STATUS='B' "+
						 " ) "+
						            
						 " AND A.REC_NO NOT IN ( "+
						            " SELECT RECEIPT_NO  "+
						            " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
						            " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						 " ) "+
						 // end by udara on 24-09-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				
				while(rs1.next()){
					
					tot_count = tot_count + rs1.getDouble(3);
					total_bbf = total_bbf + rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL B/B/F</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(tot_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_bbf)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> COLLECTIONS </B></U></td>");
				out.println("</tr>");
				
				// collections
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013
						"  "+m_user_query + // " AND STATUS NOT IN ('C') "+m_user_query + //" AND STATUS NOT IN ('C','B') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('C','CAD') "+ // added by udara on 28-08-2013
						"  "+m_user_query + // " AND A.STATUS NOT IN ('C') "+m_user_query + // " AND A.STATUS NOT IN ('C','B') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					
					total_collections = total_collections + rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL COLLECTIONS</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b> &nbsp; </b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_collections)+"</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				
				// banked
				
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						
						// commented by udara on 20-09-2013
						/*
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						//" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // commented by udara on 13-08-2013
						//" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // commented by udara on 13-08-2013
						" WHERE  TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						//" AND STATUS <>'C' "+
						" AND STATUS = 'B' "+m_user_query +
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%'  "+ // commented by udara on 25-07-2013 for performance issue
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						*/
						
						// added by udara on 20-09-2013
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B  "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						" AND A.REC_NO = B.RECEIPT_NO  "+
						" AND B.STATUS = 'Y' "+ 
						" AND A.STATUS = 'B' "+m_user_query +
						" "+location_query_part+"  "+ 
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				} else{
					
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						
						/*
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND STATUS <>'C' "+
						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						//" GROUP BY SETTLE_MODE "+
						*/
						
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS D"+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.REC_NO = D.RECEIPT_NO AND B.FINANCE_NO = C.FINANCE_NO   "+
						
						" AND  TO_DATE(TO_CHAR(D.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 13-08-2013
						" AND TO_DATE(TO_CHAR(D.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara on 13-08-2013
						" AND D.STATUS = 'Y' "+ 

						" AND A.STATUS = 'B' "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ 
						" "+location_query_part+" "+ 
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						/////" AND DEPOSIT_STATUS <>'C' "+
						" AND DEPOSIT_STATUS ='Y' "+m_user_query2 +
						//" AND LAKDL.AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '%' "+
						//" GROUP BY PAY_TYPE "+
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
					
				}
				
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 ><U><B> BANKED </B></U></td>");
				out.println("</tr>");
				
				while(rs1.next()){
					
					
					total_banked = total_banked +rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>BANKED TOTAL </B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(total_banked)+"</b></td>");
				out.println("</tr>");
				
				
				// cash in hand added by udara on 14-08-2013
				
				double cash_in_hand = 0;
				cash_in_hand = (total_bbf + total_collections) - total_banked;
				/*
				if(mm_location_code.trim().equals("ALL")){  
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT SETTLE_MODE TYPE ,REC_AMOUNT TOTAL,REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND  TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND STATUS IN ('E') "+m_user_query +
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara on 25-07-2013
						
						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
				}
				else {
					

					rs1= stmt1.executeQuery(" SELECT TYPE,SUM(TOTAL),COUNT(COUNT),DUMMY FROM ( "+
						" SELECT A.SETTLE_MODE TYPE ,A.REC_AMOUNT TOTAL,A.REC_AMOUNT COUNT, 1 DUMMY "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
						" AND A.STATUS NOT IN ('E') "+m_user_query +
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"'  "+
						" "+location_query_part+"  "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013 // mod by udara 25-07-2013

						" UNION ALL	"+				 
						
						" SELECT DECODE(PAY_TYPE,'CASH','Web Cash','CHEQUE','Web Cheque') TYPE,REC_TOTAL TOTAL,REC_TOTAL COUNT, 2 DUMMY "+
						" FROM LAKDAC.WEBAC_TRN_RECEIPTS  "+
						" WHERE  TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REC_ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('01-04-2013','DD-MM-YYYY') "+ // added by udara on 07-12-2012 to block receipt till LAKDLAC live start
						" AND DEPOSIT_STATUS NOT IN ('C','Y') "+m_user_query2 +
						" ) "+
						" GROUP BY TYPE, DUMMY "+
						" ORDER BY TYPE ");
					
				}
				
				while(rs1.next()){
					cash_in_hand = cash_in_hand + rs1.getDouble(2);
				}
				*/
				// end by udara on 14-08-2013
				
				out.println("<tr>");
				out.println("<td width='50%' class=div_input colspan=3 > &nbsp; </td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>CASH IN HAND</B></td>");
				out.println("<td width='15%' class=div_input align='right' > &nbsp; </td>");
				//out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format((total_bbf + total_collections) - total_banked)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(cash_in_hand)+"</b></td>");
				out.println("</tr>");
				
				
				
				out.println("</table>"); 
				
				out.println("<br><br><br>");
				
				
				// end by udara on 05-08-2013 ===================================================================================
				
				
				// end by udara on 05-08-2013
				
				
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>5000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>2000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>1000.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>500.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>100.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>50.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>20.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("<td width='15%' class=div_input >&nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='right'><B>10.00</B></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>Coins</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input ><b>TOTAL</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>PETTY CASH FLOAT</b></td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("<td width='15%' class=div_input >&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input > &nbsp; </td>");
				out.println("<td width='15%' class=div_input > &nbsp; </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><b>Excess/Shortage</b></td>");
				out.println("<td width='15%' class=div_input > &nbsp;</td>");
				out.println("<td width='15%' class=div_input > &nbsp;</td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				
				
				// added by udara on 08-08-2013
				if(!m_branch_id.equals("")){
					out.println("<table align='center' width='50%' class='table' border='0'>");	
					
					out.println("<tr>");
					out.println("<td width='20%' class=div_input ><B> Deposit Numbers </B></td>");
					out.println("<td width='15%' class=div_input align='right' ><B> Amount </B></td>");
					out.println("<td width='15%' class=div_input > &nbsp; </td>");
					out.println("</tr>");
					
					// commented by udara on 26-08-2013
					/*
					rs1= stmt1.executeQuery(" "+
						 " SELECT DISTINCT B.DIPOSIT_NO "+
							 " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
								 " WHERE A.REC_NO = B.RECEIPT_NO "+ 
								 " AND A.STATUS = 'B' "+
								 " AND "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(B.ENT_USER) = '"+m_branch_id+"' "+ // added by udara on 09-08-2013
								 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
								 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						" ");
					
					*/
					
					// added by udara on 26-08-2013
					// commented by udara on 20-09-2013
					/*
					rs1= stmt1.executeQuery(" "+
						" SELECT DISTINCT B.DIPOSIT_NO, SUM(B.AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
						" WHERE A.REC_NO = B.RECEIPT_NO "+ 
						" AND A.STATUS = 'B' "+
						" AND "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(B.ENT_USER) = '"+m_branch_id+"' "+ // added by udara on 09-08-2013
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						" AND A.SETTLE_MODE NOT IN ('DIR_DEP') "+
						" GROUP BY B.DIPOSIT_NO "+
						" ");
					*/
					
					 // added by udara on 20-09-2013
					 rs1= stmt1.executeQuery(" "+
						" SELECT DISTINCT B.DIPOSIT_NO, SUM(B.AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
						" WHERE A.REC_NO = B.RECEIPT_NO "+ 
						" AND A.STATUS = 'B' "+
						" AND "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(B.ENT_USER) = '"+m_branch_id+"' "+ // added by udara on 09-08-2013
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						//" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						
						//" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // commented by udara on 18-09-2013
						//" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+ // commented by udara on 18-09-2013
						" AND B.STATUS = 'Y' "+ // added by udara on 18-09-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  // added by udara on 18-09-2013
						" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+ // added by udara on 18-09-2013
						
						" AND A.SETTLE_MODE NOT IN ('DIR_DEP') "+ 
						" GROUP BY B.DIPOSIT_NO "+
						" ");   
					
					while(rs1.next()){
						
						out.println("<tr>");
						out.println("<td width='20%' class=div_input style=\"cursor:hand\" onclick=\"show_deposit_slip('"+rs1.getString(1)+"')\" ><u> "+rs1.getString(1)+" </u></td>");
						out.println("<td width='15%' class=div_input align='right' > "+nf.format(rs1.getDouble(2))+" </td>");
						out.println("<td width='15%' class=div_input > &nbsp;</td>");
						out.println("</tr>");
						
					}
					
					
					out.println("</table>");
					
					out.println("<br><br><br>"); 
				}
				// end by udara on 08-08-2013
				
				
				out.println("<table align='center' width='50%' class='table' border='0'>");		
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'>.......................</td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input align='center'>.......................</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'><b>Cashier</b></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input align='center'><b>Date</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				out.println("<table align='center' width='50%' class='table' border='0'>");		
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'>.......................</td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' class=div_input align='center'><b>Checked by</b></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				// added by udara on 29-08-2013
				
				
				if(!m_branch_id.equals("")){
					
					rs1= stmt1.executeQuery("  "+
						" SELECT "+
						" A.REC_NO, "+ //1
						" A.REC_AMOUNT, "+ //2
						" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+ //3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER,  "+
						" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+ //5
						" A.CLIENT_CODE, "+ //6
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ //7
						" NVL(A.SUS_REF_NO,'-'), "+ //8
						" A.SETTLE_MODE, "+ //9
						" NVL(A.OTH_COMMENTS,'-') , "+ //10
						" B.FINANCE_NO  FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						" SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT, "+
						" NVL(A.SUB_REC_NO,'-') SUB_REC_NO, "+ //14
						" DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE   "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_branch_id+"' "+
						//" AND A.STATUS NOT IN ('C','CAD')  "+
						" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) <> '"+m_branch_id+"' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "+
						" ");
					
					
					more = rs1.next();
					
					double m_tot_rec_nn = 0;
					
					out.println("<br><br><br>");
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
					out.println("<TR><TD align='Center' ><B> Receipts of Application Location  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more){
						
						out.println("<table width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%'><b>Receipt No</b></td>"); 
						out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
						out.println("<td width='5%' ><b>Settle Mode</b></td>"); 
						out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
						out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
						out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
						out.println("<td width='3%' ><b>Status</b></td>"); 
						out.println("</tr>"); 
						
					}
					
					int i3=1;
					while(more){
						
						if(mflag){
							out.println("<tr class=tr_input>");
							mflag=false;
						}
						else{
							out.println("<tr class=tr_input1>");
							mflag=true;
						}
						out.println("<td >"+i3+"</td>"); //width='1%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
						out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
						out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
						out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
						if(rs1.getDouble(15) == 0 ){
							out.println("<td class=div_input >Insurance</td>");  //pathum 30/11/2012
						}else{
							out.println("<td class=div_input >Rental</td>");
						}
						out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
						out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
						out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
						//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
						out.println("</tr>");
						m_tot_rec_nn=m_tot_rec_nn+rs1.getDouble(2);
						i3++;
						more = rs1.next();
						
					}	
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' >___________</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec_nn)+"</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br><br>"); 
				}
				
				// end by udara on 29-08-2013
				
				// added by udara 11-11-2013
				/*
				if(!m_branch_id.equals("")){
					
					rs1= stmt1.executeQuery("  "+
						" SELECT "+
						" A.REC_NO, "+ //1
						" A.REC_AMOUNT, "+ //2
						" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+ //3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER,  "+
						" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+ //5
						" A.CLIENT_CODE, "+ //6
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ //7
						" NVL(A.SUS_REF_NO,'-'), "+ //8
						" A.SETTLE_MODE, "+ //9
						" NVL(A.OTH_COMMENTS,'-') , "+ //10
						" B.FINANCE_NO  FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0), "+
						" SUBSTR(NVL(B.FINANCE_NO,'-'),-4) FINANCE_NO_SORT, "+
						" NVL(A.SUB_REC_NO,'-') SUB_REC_NO, "+ //14
						" DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE   "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_branch_id+"' "+
						" AND A.STATUS IN ('C','CAD')  "+
						" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) <> '"+m_branch_id+"' "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" "+
						" ");
					
					
					more = rs1.next();
					
					double m_tot_rec_nn = 0;
					
					out.println("<br><br><br>");
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' >");
					out.println("<TR><TD align='Center' ><B> Cancelled Receipts of Application Location  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more){
						
						out.println("<table width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%'><b>Receipt No</b></td>"); 
						out.println("<td width='9%'  style= cursor:hand; onclick=sort_data('SUB_REC_NO') ><b>Sub Receipt No</b></td>"); 
						out.println("<td width='5%' ><b>Settle Mode</b></td>"); 
						out.println("<td width='13%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><b>Finance No/s</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('REC_AMOUNT')  align='right'><b>Amount</b></td>");
						out.println("<td width='15%' style= cursor:hand; onclick=sort_data('EFF_VALDATE') ><b>&nbsp;Value Date</b></td>");
						out.println("<td width='6%'  style= cursor:hand; onclick=sort_data('N_TYPE') ><b>Type</b></td>");  
						out.println("<td width='10%' style= cursor:hand; onclick=sort_data('ENT_USER') ><b>Entered User</b></td>"); // modified by udara on 03-12-2012
						out.println("<td width='24%' style= cursor:hand; onclick=sort_data('ENT_DATE') ><b>Entered Date</b></td>"); 
						out.println("<td width='3%' ><b>Status</b></td>"); 
						out.println("</tr>"); 
						
					}
					
					int i3=1;
					while(more){
						
						if(mflag){
							out.println("<tr class=tr_input>");
							mflag=false;
						}
						else{
							out.println("<tr class=tr_input1>");
							mflag=true;
						}
						out.println("<td >"+i3+"</td>"); //width='1%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
						out.println("<td  class=div_input >"+rs1.getString(9)+"</td>"); //width='5%'
						out.println("<td  class=div_input ><p>"+rs1.getString(11)+"</p></td>"); //width='15%'
						out.println("<td  class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>"); //width='10%'
						out.println("<td  class=div_input >&nbsp;"+rs1.getString(3)+"</td>"); //width='14%'
						if(rs1.getDouble(15) == 0 ){
							out.println("<td class=div_input >Insurance</td>");  //pathum 30/11/2012
						}else{
							out.println("<td class=div_input >Rental</td>");
						}
						out.println("<td  class=div_input >"+rs1.getString(4)+"</td>"); //width='10%'
						out.println("<td class=div_input >"+rs1.getString(5)+"</td>"); // width='20%'
						out.println("<td class=div_input >"+rs1.getString(14)+"</td>"); //width='5%' 
						//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
						out.println("</tr>");
						m_tot_rec_nn=m_tot_rec_nn+rs1.getDouble(2);
						i3++;
						more = rs1.next();
						
					}	
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' >___________</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td ></td>"); 
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>"); // added by udara on 31-07-2013
					out.println("<td  class=div_input align='right' ><b>"+nf.format(m_tot_rec_nn)+"</b></td>");
					out.println("<td  class=div_input >&nbsp;</td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					out.println("<td  class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br><br>"); 
				}
				*/
				// end by udara 11-11-2013
				
				
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
			}
			
			
			else if(m_chksql.equals("load_receipts_cancel")){
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code");
				String mm_location_code = req.getParameter("location_code");
				String m_user = req.getParameter("user");
				String m_user_query=""; 
				String m_user_query2=""; 
				
				
				// added by udara on 29-10-2013
				rs1= stmt1.executeQuery(" SELECT "+
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_branch_id = rs1.getString(1);
				}
				
				if(m_branch_id.equals("HO")){
					m_branch_id = "";
				}
				else{
					m_branch_id = m_branch_id;
				}
				// end by udara on 29-10-2013
				
				
				
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND UPPER(A.ENT_USER) = UPPER('"+m_user+"') ";
					m_user_query2 = "AND UPPER(A.REC_ENT_BY) = UPPER('"+m_user+"') ";
				}
				
				/*if (m_location_code.trim().equals("ALL")) {
				m_location_code="";
				}*/
				
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"' "; 	
				// end by udara on 25-07-2013
				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location_code+"'),'-')"+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
				}
				
				
				
				String m_mod_user ="-";//Added By Sandun on 16-01-2009
				String m_mod_date ="-";
				
				double m_tot_rec=0;
				
				if (m_location_code.trim().equals("ALL")) {
					
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						"  A.MOD_USER, "+//4
						"  TO_CHAR(A.MOD_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-'), "+//10
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // " A.ENT_USER , "+//11 // mod by udara 17-07-2013             
						" NVL(B.COMMENTS,'-'),NVL(A.RENTAL_OTER_INVOICE,0) "+//12    //Added By Sandun on 19-11-2008
						" ,NVL(A.SUB_REC_NO,'-')"+//KANISHKA
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 15
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A ,"+m_schema_name+".AF_CO_PRO_RECEIPT_CANCEL B  "+
						" WHERE  A.REC_NO = B.REC_NO "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND STATUS ='CAD'"+ //ADDED BY NUWAN ON 01-04-08
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						"  A.MOD_USER, "+//4
						"  TO_CHAR(A.MOD_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-'), "+//10
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // " A.ENT_USER , "+//11   // mod by udara 17-07-2013           
						" NVL(B.COMMENTS,'-'),NVL(A.RENTAL_OTER_INVOICE,0) "+//12    //Added By Sandun on 19-11-2008
						" ,NVL(A.SUB_REC_NO,'-')"+//KANISHKA
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 15
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A ,"+m_schema_name+".AF_CO_PRO_RECEIPT_CANCEL B ,  "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL C, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS D "+
						" WHERE  A.REC_NO = B.REC_NO "+
						" AND  B.REC_NO = C.REC_NO AND C.FINANCE_NO = D.FINANCE_NO   "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND A.STATUS ='CAD'"+ //ADDED BY NUWAN ON 01-04-08
						" AND D.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
				}
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Cancelation Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_from_date ='"+m_from_date+"';");	
				out.println(" m_to_date='"+m_to_date+"';"); 
				out.println("    m_location_code = '"+mm_location_code+"'; ");
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
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
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_cancel&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;"); // commented by udara on 05-12-2012
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_cancel&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;"); // added by udara on 05-12-2012
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_cancel&location_code=\"+m_location_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&branch_id=\"+m_branch_id;"); // added by udara on 27-02-2013
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
				
				// added by udara on 04-12-2012
				out.println("<TABLE  WIDTH='100%'  >");
				//out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				//out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				out.println("</TABLE>");	
				out.println("<BR>");	
				// end by udara on 04-12-2012
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Cancelation Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='100%' class='table' >");	
					// commented below by udara on 05-12-2012
					/*
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='8%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('EFF_VALDATE') ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='6%' style= cursor:hand; ><DIV class=div_input ><b>Type</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>");
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Cancel Remarks</b></DIV></td>");
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Modified User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Enter User</b></DIV></td>");
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Modified User'    onclick=sort_data('MOD_USER') ><DIV class=div_input ><b>Modified User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Modified Date'    onclick=sort_data('MOD_DATE') ><DIV class=div_input ><b>Modified Date</b></DIV></td>"); 
					out.println("</tr>"); 
					*/
					
					// added below by udara on 05-12-2012
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='10%'><b>Sub Receipt No</b></td>"); 
					out.println("<td width='8%'  style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('EFF_VALDATE') ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='6%'  style= cursor:hand; title='Click here to sort by - Type'    onclick=sort_data('N_TYPE') ><DIV class=div_input ><b>Type</b></DIV></td>"); 
					out.println("<td width='20%'  ><DIV class=div_input ><b>Remarks</b></DIV></td>");
					out.println("<td width='20%'  ><DIV class=div_input ><b>Cancel Remarks</b></DIV></td>");
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Modified User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Enter User</b></DIV></td>");
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Modified User'    onclick=sort_data('MOD_USER') ><DIV class=div_input ><b>Modified User</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Modified Date'    onclick=sort_data('MOD_DATE') ><DIV class=div_input ><b>Modified Date</b></DIV></td>"); 
					out.println("</tr>"); 
					
				}
				
				int i=1;
				while(more){
					
					//----------------------------------------------------------------------
					rs3= stmt3.executeQuery(" SELECT MOD_USER,MOD_DATE "+  //Added By Sandun on 16-01-2009
						"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BK "+
						"  WHERE REC_NO = '"+rs1.getString(1)+"' "+
						"  AND MOD_DATE IN (SELECT MAX(MOD_DATE) FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BK GROUP BY REC_NO) ");
					
					if(rs3.next()){
						m_mod_user = rs3.getString(1);
						m_mod_date = rs3.getString(2);
					}
					//----------------------------------------------------------------------
					
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					//out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%' // commented by udara on 08-07-2013
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(14)+"</u></td>"); // added by udara on 08-07-2013 
					out.println("<td width='8%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					if(rs1.getDouble(13) == 0){
						out.println("<td width='6%' class=div_input >Insurance</td>"); //pathum 30/11/2012
					}else{
						out.println("<td width='6%' class=div_input >Rental</td>");
					}
					
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					//out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					//out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+m_mod_user+"</td>");
					out.println("<td width='12%' class=div_input >"+m_mod_date+"</td>");
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				
				
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Dineth on 2008-09-29
			
			else if(m_chksql.equals("load_receipts_return")){
				
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code");
				String mm_location_code = req.getParameter("location_code"); // added by udara on 03-12-2012
				
				
				// added by udara on 29-10-2013
				rs1= stmt1.executeQuery(" SELECT "+
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_branch_id = rs1.getString(1);
				}
				
				if(m_branch_id.equals("HO")){
					m_branch_id = "";
				}
				else{
					m_branch_id = m_branch_id;
				}
				// end by udara on 29-10-2013
				
				String m_user = req.getParameter("user");
				String m_user_query=""; 
				String m_user_query2=""; 
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND UPPER(A.ENT_USER) = UPPER('"+m_user+"') ";
					m_user_query2 = "AND UPPER(REC_ENT_BY) = UPPER('"+m_user+"') ";
				}
				
				/*if (m_location_code.trim().equals("ALL")) {
				m_location_code="";
				}
				*/
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"' "; 	
				// end by udara on 25-07-2013
				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+mm_location_code+"'),'-')"+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
				}
				
				
				
				double m_tot_rec=0;
				
				if (m_location_code.trim().equals("ALL")) {
					
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // mod by udara on 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'), NVL(RENTAL_OTER_INVOICE,0),"+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-')"+//KANISHKA 17
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+
						" WHERE  TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else {
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // mod by udara on 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'), NVL(A.RENTAL_OTER_INVOICE,0),"+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-')"+//KANISHKA
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 18
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND A.STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
						" "+location_query_part+"  "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
				}
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_from_date ='"+m_from_date+"';");	
				out.println(" m_to_date='"+m_to_date+"';"); 
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
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
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_return&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"\";");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_return&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"&branch_id=\"+m_branch_id;");
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
				
				out.println("<TABLE  WIDTH='100%'  >");
				// out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				//out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Sub Receipt No'    onclick=sort_data('SUB_REC_NO') ><DIV class=div_input ><b>Sub Receipt No</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('EFF_VALDATE') ><DIV class=div_input ><b>Value Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='6%'  style= cursor:hand; title='Click here to sort by - Type' onclick=sort_data('N_TYPE') ><DIV class=div_input ><b>Type</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					if(rs1.getDouble(15) == 0){
						out.println("<td width='6%' class=div_input >Insurance</td>");  //pathum 30/11/2012
					}else{
						out.println("<td width='6%' class=div_input >Rental</td>");
					}
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				//if (m_location_code.trim().equals("ALL")) { // commented by udara on 07-12-2012
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
						" "+location_query_part+" "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" "+m_user_query+ //ADDED BY NS ON 28-03-2011
						
						" GROUP BY SETTLE_MODE ");
					
				} else {
					
					rs1= stmt1.executeQuery(" SELECT A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" "+ m_user_query+ //ADDED BY NS ON 28-03-2011
						
						" GROUP BY A.SETTLE_MODE ");
					
				}
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" "+ m_user_query+ //ADDED BY NS ON 28-03-2011
						" GROUP BY ENT_USER,SETTLE_MODE "+
						" ORDER BY ENT_USER,SETTLE_MODE ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT A.ENT_USER,A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.STATUS ='RET'"+ //ADDED BY NUWAN ON 01-04-08
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+m_user_query+ //ADDED BY NS ON 28-03-2011
						" GROUP BY A.ENT_USER,A.SETTLE_MODE "+
						" ORDER BY A.ENT_USER,A.SETTLE_MODE ");
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
			}
			
			
			
			
			//End by Dineth on 2008-09-29
			
			//Added by Dineth on 16-06-2009
			else if(m_chksql.equals("load_receipts_PDC")){
				String m_string="";				
				String m_sql="";
				
				// commented below by udara on 03-12-2012
				/*
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
			    String m_order_by = req.getParameter("order_by");
			    String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code");
				String m_user = req.getParameter("user");
				*/
				
				// added by udara on 03-12-2012
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code");
				String mm_location_code = req.getParameter("location_code"); // added by udara on 03-12-2012
				String m_user = req.getParameter("user");
				
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				
				// added by udara on 29-10-2013
				rs1= stmt1.executeQuery(" SELECT "+
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_branch_id = rs1.getString(1);
				}
				
				if(m_branch_id.equals("HO")){
					m_branch_id = "";
				}
				else{
					m_branch_id = m_branch_id;
				}
				// end by udara on 29-10-2013
				
				/*if (m_location_code.trim().equals("ALL")) {
			  m_location_code="";
				}*/
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"' "; 	
				// end by udara on 25-07-2013
				
				
				String m_user_query=""; 
				String m_user_query2=""; 
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND UPPER(A.ENT_USER) = UPPER('"+m_user+"') ";
					m_user_query2 = "AND UPPER(REC_ENT_BY) = UPPER('"+m_user+"') ";
				}
				
				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+mm_location_code+"'),'-')"+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
				}
				
				
				double m_tot_rec=0;
				
				if (m_location_code.trim().equals("ALL")) {
					
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // modified by udara 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'),CHEQUE_DATE, NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'RENTAL'),0), "+//Added by Dineth on 11-06-2009
						//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'ODI'),0), "+//Added by Dineth on 11-06-2009
						//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'OTHER'),0) "+//Added by Dineth on 11-06-2009
						" ,NVL(SUB_REC_NO,'-')"+//KANISHKA
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 19
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+m_user_query+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						"  "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND REC_TYPE ='POD' "+ 
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // mod by udara 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						//"  NVL(CHEQUE_NO,'-'), "+//6
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'),A.CHEQUE_DATE, NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'RENTAL'),0), "+//Added by Dineth on 11-06-2009
						//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'ODI'),0), "+//Added by Dineth on 11-06-2009
						//" NVL("+m_schema_name+".AF_CO_GET_RECEIPT_AMT(REC_NO,'OTHER'),0) "+//Added by Dineth on 11-06-2009
						" ,NVL(A.SUB_REC_NO,'-')"+//KANISHKA
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 19
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+m_user_query+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND A.REC_TYPE ='POD' "+ 
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
				}
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_from_date ='"+m_from_date+"';");	
				out.println(" m_to_date='"+m_to_date+"';"); 
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
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
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;"); // commented by udara on 03-12-2012
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_PDC&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"\";");
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_PDC&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"&branch_id=\"+m_branch_id;");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				
				out.println("	function receipt_doc(val1){");
				
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=print_receipt&receipt_no=\"+val1+\" \";");
				//out.println("   window.open(m_url,'popupwin1','left=100,top=10,status=0,menubar=0,scrollbars=1,height=700,width=650');	");
				out.println(" window.location.href=m_url;"); 
				out.println("	}");					
				
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				out.println("<TABLE  WIDTH='100%'  >");
				// out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				//out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("</TABLE>");
				
				
				out.println("<TABLE  WIDTH='120%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='120%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Sub Receipt No'    onclick=sort_data('SUB_REC_NO') ><DIV class=div_input ><b>Sub Receipt No</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('CHEQUE_DATE') ><DIV class=div_input ><b>Cheque Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='6%'  style= cursor:hand; title='Click here to sort by - Type' onclick=sort_data('N_TYPE') ><DIV class=div_input ><b>Type</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					out.println("<td width='12%' >&nbsp;</td>"); 
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					if(rs1.getDouble(16) == 0){
						out.println("<td width='6%' class=div_input >Insurance</td>"); //pathum 30/11/2012
					}else{
						out.println("<td width='6%' class=div_input >Rental</td>");
					}
					out.println("<td width='12%' class=div_input style='WORD-BREAK:BREAK-ALL'>"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					//out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Detail\" onclick=\"receipt_doc('"+rs1.getString(1)+"','"+rs1.getString(6)+"')\"></td>"); 
					out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Print\" onclick=\"receipt_doc('"+rs1.getString(1)+"')\"></td>"); //Mod By Sandun  on 21-07-2009
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND REC_TYPE='POD'"+ //Added by Dineth on 16-06-2009
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY SETTLE_MODE ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND A.REC_TYPE='POD'"+ //Added by Dineth on 16-06-2009
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.SETTLE_MODE ");
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND REC_TYPE='POD' "+ //Added by Dineth on 16-06-2009
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY ENT_USER,SETTLE_MODE "+
						" ORDER BY ENT_USER,SETTLE_MODE ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT A.ENT_USER,A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND A.REC_TYPE='POD' "+ //Added by Dineth on 16-06-2009
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.ENT_USER,A.SETTLE_MODE "+
						" ORDER BY A.ENT_USER,A.SETTLE_MODE ");
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
				
				
				
				
			}
			//End by Dineth on 16-06-2009
			
			
			else if(m_chksql.equals("load_receipts_temp")){//Added By Sandun on 16-09-2009
				
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code");
				String mm_location_code = req.getParameter("location_code"); // added by udara on 03-12-2012
				String m_user = req.getParameter("user");
				String m_user_query=""; 
				String m_user_query2=""; 
				
				
				// added by udara on 29-10-2013
				rs1= stmt1.executeQuery(" SELECT "+
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_branch_id = rs1.getString(1);
				}
				
				if(m_branch_id.equals("HO")){
					m_branch_id = "";
				}
				else{
					m_branch_id = m_branch_id;
				}
				// end by udara on 29-10-2013
				
				
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND UPPER(A.ENT_USER) = UPPER('"+m_user+"') ";
					m_user_query2 = "AND UPPER(REC_ENT_BY) = UPPER('"+m_user+"') ";
				}
				
				
				/*if (m_location_code.trim().equals("ALL")) {
				m_location_code="";
				}*/
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"' "; 	
				// end by udara on 25-07-2013
				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+mm_location_code+"'),'-')"+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
				}
				
				
				double m_tot_rec=0;
				
				if (m_location_code.trim().equals("ALL")) {
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // mod by udara 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'),CHEQUE_DATE , NVL(RENTAL_OTER_INVOICE,0),"+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-')"+//KANISHKA // 18
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 19
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+
						" AND REC_TYPE='TMP' "+ 
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						"  "+location_query_part+" "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // mod by udara 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'),A.CHEQUE_DATE , NVL(A.RENTAL_OTER_INVOICE,0),"+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-')"+//KANISHKA
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 19
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+
						" AND A.REC_TYPE='TMP' "+ 
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						"  "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
				}
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_from_date ='"+m_from_date+"';");	
				out.println(" m_to_date='"+m_to_date+"';"); 
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
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
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;"); // commented by udara on 03-12-2012
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_temp&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"\";"); // added by udara on 03-12-2012
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_temp&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"&branch_id=\"+m_branch_id;");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				
				out.println("	function receipt_doc(val1){");
				
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=print_receipt&receipt_no=\"+val1+\" \";");
				//out.println("   window.open(m_url,'popupwin1','left=100,top=10,status=0,menubar=0,scrollbars=1,height=700,width=650');	");
				out.println(" window.location.href=m_url;"); 
				out.println("	}");					
				
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<TABLE  WIDTH='100%'  >");
				// out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				//out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("</TABLE>");
				
				
				out.println("<TABLE  WIDTH='120%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='120%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('SUS_REF_NO') ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Sub Receipt No'    onclick=sort_data('SUB_REC_NO') ><DIV class=div_input ><b>Sub Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('CHEQUE_DATE') ><DIV class=div_input ><b>Cheque Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='6%'  style= cursor:hand; title='Click here to sort by - Type' onclick=sort_data('N_TYPE') ><DIV class=div_input ><b>Type</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					//out.println("<td width='12%' >&nbsp;</td>"); 
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					//out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					if(rs1.getDouble(16) == 0){
						out.println("<td width='6%' class=div_input >Insurance</td>"); //pathum 30/11/2012
					}else{
						out.println("<td width='6%' class=div_input >Rental</td>");
					}
					out.println("<td width='12%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					//out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Detail\" onclick=\"receipt_doc('"+rs1.getString(1)+"','"+rs1.getString(6)+"')\"></td>"); 
					//out.println("<td width='12%' class=div_input ><input type='button' class='but_input' value=\"Print\" onclick=\"receipt_doc('"+rs1.getString(1)+"')\"></td>"); //Mod By Sandun  on 21-07-2009
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A  "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND REC_TYPE='TMP' "+ m_user_query+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY SETTLE_MODE ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.REC_TYPE='TMP' "+ m_user_query+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.SETTLE_MODE ");
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND REC_TYPE='TMP'  "+ m_user_query+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+  // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY ENT_USER,SETTLE_MODE "+
						" ORDER BY ENT_USER,SETTLE_MODE ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT A.ENT_USER,A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.REC_TYPE='TMP'  "+ m_user_query+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.ENT_USER,A.SETTLE_MODE "+
						" ORDER BY A.ENT_USER,A.SETTLE_MODE ");
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
			}
			
			
			else if(m_chksql.equals("load_receipts_stdo")){//Added By Sandun on 16-09-2009
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_order_by = req.getParameter("order_by");
				String m_sort_by = req.getParameter("sort_by");
				String m_location_code = req.getParameter("location_code");
				String mm_location_code = req.getParameter("location_code"); // added by udara on 03-12-2012
				String m_branch_id = req.getParameter("branch_id"); // added by udara on 27-02-2013
				String m_user = req.getParameter("user");
				String m_user_query=""; 
				String m_user_query2=""; 
				
				// added by udara on 29-10-2013
				rs1= stmt1.executeQuery(" SELECT "+
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_branch_id = rs1.getString(1);
				}
				
				if(m_branch_id.equals("HO")){
					m_branch_id = "";
				}
				else{
					m_branch_id = m_branch_id;
				}
				// end by udara on 29-10-2013
				
				
				if(m_user!= null && !m_user.equals("")){
					m_user_query = "AND UPPER(A.ENT_USER) = UPPER('"+m_user+"') ";
					m_user_query2 = "AND UPPER(REC_ENT_BY) = UPPER('"+m_user+"') ";
				}
				
				/*if (m_location_code.trim().equals("ALL")) {
				m_location_code="";
				}*/
				
				// added by udara on 25-07-2013
				String location_query_part = "";
				if(!m_branch_id.equals(""))
					location_query_part = " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER) = '"+m_branch_id+"' "; 	
				// end by udara on 25-07-2013
				
				String m_date_time ="";
				String m_logged_user="";
				String m_branch_name="";
				rs1= stmt1.executeQuery(" SELECT "+
					" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
					" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"'), "+
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+mm_location_code+"'),'-')"+
					" FROM DUAL ");
				
				while(rs1.next()){
					m_date_time = rs1.getString(1);
					m_logged_user=rs1.getString(2);
					m_branch_name = rs1.getString(3);
				}
				
				
				double m_tot_rec=0;
				
				if (m_location_code.trim().equals("ALL")) {
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT "+
						"  REC_NO, "+//1
						"  REC_AMOUNT, "+//2
						"  TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
						" (ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // mod by udara 17-07-2013
						"  TO_CHAR(ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						"  CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(SUS_REF_NO,'-'), "+//8
						" SETTLE_MODE,"+//9
						" NVL(OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-') FIN_NO, "+ //11
						" NVL(BRANCH_CODE,'-'),NVL(ACC_NO,'-'), NVL(STATUS,'-'),CHEQUE_DATE, NVL(RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(SUB_REC_NO,'-') SUB_REC_NO "+//KANISHKA 18
						" ,DECODE(NVL(RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 19
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND SETTLE_MODE ='STD_ORD' "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND STATUS IN ('E','B') "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT "+
						"  A.REC_NO, "+//1
						"  A.REC_AMOUNT, "+//2
						"  TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE_APP, "+//3
						" (A.ENT_USER || '-' || "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(A.ENT_USER))) ENT_USER, "+ // "  ENT_USER, "+//4 // mod by udara 17-07-2013
						"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY  HH24:MI:SS') ENT_DATE, "+//5
						"  A.CLIENT_CODE, "+//6
						"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+//7
						" NVL(A.SUS_REF_NO,'-'), "+//8
						" A.SETTLE_MODE,"+//9
						" NVL(A.OTH_COMMENTS,'-') , "+//10
						" NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-') FIN_NO, "+ //11
						" NVL(A.BRANCH_CODE,'-'),NVL(A.ACC_NO,'-'), NVL(A.STATUS,'-'),A.CHEQUE_DATE, NVL(A.RENTAL_OTER_INVOICE,0), "+
						"  SUBSTR(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'),-4) FINANCE_NO_SORT "+ // added by udara on 06-12-2012
						" ,NVL(A.SUB_REC_NO,'-')"+//KANISHKA
						" ,DECODE(NVL(A.RENTAL_OTER_INVOICE,0),'0','Insurance','Rental') N_TYPE  "+ // 19
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+m_user_query+
						" AND A.SETTLE_MODE ='STD_ORD' "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" AND A.STATUS IN ('E','B') "+
						" ORDER BY "+m_order_by+" "+m_sort_by+" ");
					
				}
				
				boolean mflag=true;							
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function sort_data(m_sort_col) {");
				out.println(" m_from_date ='"+m_from_date+"';");	
				out.println(" m_to_date='"+m_to_date+"';");
				out.println(" m_branch_id='"+m_branch_id+"';");  // added by udara on 27-02-2013
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
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_stdo&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;"); // commented by udara on 03-12-2012
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_stdo&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"\";"); // added by udara on 03-12-2012
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=load_receipts_stdo&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&location_code="+mm_location_code+"&user="+m_user+"&branch_id=\"+m_branch_id;"); // added by udara on 03-12-2012
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");	
				
				out.println("	function receipt_doc(val1){");
				
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report_new_gui?chksql=print_receipt&receipt_no=\"+val1+\" \";");
				//out.println("   window.open(m_url,'popupwin1','left=100,top=10,status=0,menubar=0,scrollbars=1,height=700,width=650');	");
				out.println(" window.location.href=m_url;"); 
				out.println("	}");					
				
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 	
				
				out.println("<TABLE  WIDTH='100%'  >");
				// out.println("<TR><TD align='left' ><B>Report Generated By : "+m_logged_user+" </B></TD>");
				//out.println("<TD align='right' ><B>Date : "+m_date_time+"</B></TD></TR>");
				
				out.println("<TR><TD align='left' ><B>Branch : "+m_branch_name+" </B></TD>");
				out.println("<TD align='right' ><B> &nbsp; </B></TD></TR>");
				
				out.println("</TABLE>");
				
				
				out.println("<TABLE  WIDTH='120%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Receipt Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='120%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('REC_NO') ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Sub Receipt No'    onclick=sort_data('SUB_REC_NO') ><DIV class=div_input ><b>Sub Receipt No</b></DIV></td>");
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('SETTLE_MODE') ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; onclick=sort_data('FINANCE_NO_SORT') ><DIV class=div_input ><b>Finance No/s</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('REC_AMOUNT') align='right'><DIV class=div_input ><b>Amount</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Value Date'    onclick=sort_data('CHEQUE_DATE') ><DIV class=div_input ><b>Cheque Date</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
					out.println("<td width='6%'  style= cursor:hand; title='Click here to sort by - Type' onclick=sort_data('N_TYPE') ><DIV class=div_input ><b>Type</b></DIV></td>"); 
					out.println("<td width='20%' style= cursor:hand; title='Click here to sort by - Client'    onclick=sort_data('CLIENT_NAME') ><DIV class=div_input ><b>Remarks</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered User'    onclick=sort_data('ENT_USER') ><DIV class=div_input ><b>Entered User</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Entered Date'    onclick=sort_data('ENT_DATE') ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') ><DIV class=div_input ><b>Status</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') ><DIV class=div_input ><b>Branch</b></DIV></td>"); 
					out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Account'    onclick=sort_data('ACC_NO') ><DIV class=div_input ><b>Account</b></DIV></td>"); 
					
					out.println("</tr>"); 
				}
				
				int i=1;
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'>"+i+"</td>"); 
					out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td  class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>"); //width='20%'
					out.println("<td width='12%' class=div_input >"+rs1.getString(9)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'>"+rs1.getString(7)+"</td>");
					if(rs1.getDouble(16) == 0){
						out.println("<td width='6%' class=div_input >Insurance</td>"); //pathum 30/11/2012
					}else{
						out.println("<td width='6%' class=div_input >Rental</td>");
					}
					out.println("<td width='12%' class=div_input style='WORD-BREAK:BREAK-ALL' >"+rs1.getString(10)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(14)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("</tr>");
					m_tot_rec=m_tot_rec+rs1.getDouble(2);
					i++;
					more = rs1.next();
					
				}	
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' >____________________</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input align='right' ><b>"+nf.format(m_tot_rec)+"</b></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				out.println("<td width='12%' class=div_input ></td>");
				//out.println("<td width='12%' class=div_input ></td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br><br>"); 
				
				double m_rec_count=0;
				double m_rec_amt=0;
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND SETTLE_MODE ='STD_ORD' "+ m_user_query+
						" AND STATUS IN ('E','B') "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+  //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY SETTLE_MODE ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.SETTLE_MODE ='STD_ORD' "+ m_user_query+
						" AND A.STATUS IN ('E','B') "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ // " AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.SETTLE_MODE ");
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					
					m_rec_count=m_rec_count+rs1.getDouble(3);
					m_rec_amt=m_rec_amt+rs1.getDouble(2);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(3))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(2))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<br><br><br>"); 
				
				m_rec_count=0;
				m_rec_amt=0;
				
				//if (m_location_code.trim().equals("ALL")) {
				if(mm_location_code.trim().equals("ALL")){  // added by udara on 07-12-2012
					m_location_code="";
					rs1= stmt1.executeQuery(" SELECT ENT_USER,SETTLE_MODE,SUM(REC_AMOUNT),COUNT(REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ // mod by udara on 09-08-2013
						" WHERE  TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND SETTLE_MODE ='STD_ORD' "+ m_user_query+
						" AND STATUS IN ('E','B') "+
						//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) LIKE '%"+m_location_code+"%' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+ // " AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY ENT_USER,SETTLE_MODE "+
						" ORDER BY ENT_USER,SETTLE_MODE ");
				}
				else {
					
					rs1= stmt1.executeQuery(" SELECT A.ENT_USER,A.SETTLE_MODE,SUM(A.REC_AMOUNT),COUNT(A.REC_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A , "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
						" WHERE  A.REC_NO = B.REC_NO AND B.FINANCE_NO = C.FINANCE_NO  AND  "+
						" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.SETTLE_MODE ='STD_ORD' "+ m_user_query+
						" AND A.STATUS IN ('E','B') "+
						" AND C.BRANCH_CODE = '"+m_location_code+"'  "+ //" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION("+m_schema_name+".AF_CO_GET_APPLICATION_NO(NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(REC_NO),'-'))) = '"+m_location_code+"' "+ //ADDED BY NS ON 28-03-2011
						" "+location_query_part+" "+  //" AND  "+m_schema_name+".AF_CO_GET_USER_LOCATION_RRN(ENT_USER) LIKE '"+m_branch_id+"%'  "+ // added by udara on 27-02-2013
						" GROUP BY A.ENT_USER,A.SETTLE_MODE "+
						" ORDER BY A.ENT_USER,A.SETTLE_MODE ");
					
				}
				
				out.println("<table align='center' width='50%' class='table' border='1'>");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Enter User</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input ><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Count</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
				out.println("</tr>"); 		
				
				while(rs1.next()){
					m_rec_count=m_rec_count+rs1.getDouble(4);
					m_rec_amt=m_rec_amt+rs1.getDouble(3);
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(rs1.getDouble(4))+"</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='20%' class=div_input ></td>");
				out.println("<td width='20%' class=div_input ><B>TOTAL</B></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf1.format(m_rec_count)+"</b></td>");
				out.println("<td width='15%' class=div_input align='right' ><b>"+nf.format(m_rec_amt)+"</b></td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");			
			}
			
			
			/*else if(m_chksql.equals("print_receipt")){
			
			String m_rec_no = req.getParameter("receipt_no");
			
			Bulk_Printing_Main pdc_print =new Bulk_Printing_Main();
			int m_ginvoice_no=pdc_print.printing_interface_pdc(m_rec_no);
			
			out.println("m_ginvoice_no "+m_ginvoice_no);
			
			}*/
			
			
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
