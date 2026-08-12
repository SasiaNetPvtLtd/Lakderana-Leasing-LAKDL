
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
// DEVELOPED BY : SH 
// DATE:24-07-2008

public class LAKDL_AF_CR_PRO_Cancelation_After_PO extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	//Statement stmt;
	//public ResultSet rs;
	ServletOutputStream out =  null;
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			Connection conn=m_sn_methods.met_user_validate(req);
      String m_username = m_sn_methods.username;
			//stmt=conn.createStatement();
			
		
							 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_CLOSE=""; 
			
			String m_chksql = req.getParameter("chksql");
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
			
			String m_application_no = req.getParameter("application_no");

			m_CLOSE = req.getParameter("CLOSE");
			
			if(req.getParameter("CLOSE")==null){
			m_CLOSE="N";
			}

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Credit - Reverse Application </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
					
									
			out.println("var application_no=\"\"; ");
			
		
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length==0 && document.Form1.TXT_APPLICANT_CODE.value!=\"\"   && document.Form1.hid_text.value==\"J1\"){");
			out.println("		help_button_1();");
			out.println("			} else if(data_vec.length>0 && document.Form1.TXT_APPLICANT_CODE.value!=\"\"   && document.Form1.hid_text.value==\"J1\"){");
			out.println("document.Form1.TXT_APPLICANT_CODE.value=data_vec[0]");
			out.println("document.Form1.TXT_APPLICANT_NAME.value=data_vec[1]");
			out.println("	 } else if(data_vec.length==0 && document.Form1.TXT_APPLICATION_NO.value!=\"\"  && document.Form1.hid_text.value==\"J2\"){");
			out.println("		help_button_2();");
			out.println("			}else if(data_vec.length>0 && document.Form1.TXT_APPLICATION_NO.value!=\"\"   && document.Form1.hid_text.value==\"J2\"){");
			out.println("document.Form1.TXT_APPLICATION_NO.value=data_vec[0]");
			out.println("document.Form1.TXT_FINANCE_NO.value    =data_vec[1]");
			out.println("document.Form1.TXT_APPLICANT_CODE.value=data_vec[2]");
			out.println("document.Form1.TXT_APPLICANT_NAME.value=data_vec[3]");
			out.println("document.Form1.TXT_TOT_REC.value       =data_vec[4]");
			out.println("	 } else if(data_vec.length==0 && document.Form1.TXT_FINANCE_NO.value!=\"\"  && document.Form1.hid_text.value==\"J3\"){");
			out.println("		help_button_3();");
			out.println("			}else if(data_vec.length>0 && document.Form1.TXT_FINANCE_NO.value!=\"\"   && document.Form1.hid_text.value==\"J3\"){");
			out.println("document.Form1.TXT_FINANCE_NO.value    =data_vec[0]");
			out.println("document.Form1.TXT_APPLICATION_NO.value=data_vec[1]");
			out.println("document.Form1.TXT_APPLICANT_CODE.value=data_vec[2]");
			out.println("document.Form1.TXT_APPLICANT_NAME.value=data_vec[3]");			
			out.println("document.Form1.TXT_TOT_REC.value       =data_vec[4]");
			out.println("			}else if(data_vec.length>0 && document.Form1.TXT_APPLICATION_NO.value!=\"\"   && document.Form1.hid_text.value==\"J4\"){");
			out.println("document.Form1.TXT_TOT_PENALTY.value    =data_vec[0]");
			out.println("document.Form1.TXT_TOT_REPAY.value      =data_vec[1]");
			out.println("	 } else if(data_vec.length==0 && document.Form1.hid_text.value==\"J4\"){");
			out.println("document.Form1.TXT_TOT_PENALTY.value    =\"0\";");
			out.println("document.Form1.TXT_TOT_REPAY.value      =document.Form1.TXT_TOT_REC.value");
			out.println("			}");
	
	
	/*out.println("document.Form1.TXT_TOT_PENALTY.value    =data_vec[0]");
			out.println("document.Form1.TXT_APPLICATION_NO.value =data_vec[1]");
			*/
			out.println("	}");
			
				
						

				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Reverse_Application?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 

				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Reverse_Application?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}");
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
					
		
					
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Credit - Reverse Application - \"+m_val;"); 
				out.println("}"); 
		
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Credit - Reverse Application  \";"); 
				out.println("}"); 
		
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
				out.println("}"); 
				out.println("else{");
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
		
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				
				
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[4] ==\" \"){"); 
			out.println(" clear(); ");
			out.println(" }");

			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("if(document.Form1.hid_help_type.value=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{");
			out.println("clear()");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");
			out.println("clear()");
			out.println("	}	"); 
			out.println("}"); 
			
			
	 		out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function clear(){");
			out.println("   if(document.Form1.hid_help_type.value==\"1\"){;"); 
			out.println("document.Form1.TXT_APPLICANT_CODE.value=\"\"");
			out.println("document.Form1.TXT_APPLICANT_NAME.value=\"\"");
			out.println("}");
			out.println(" else  if(document.Form1.hid_help_type.value==\"2\"){;"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\"");
			out.println("}");
			out.println(" else  if(document.Form1.hid_help_type.value==\"3\"){;"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\"");
			out.println("}");
			out.println("}");

			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_NO_5\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\";");
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_1(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[3];");
			out.println("}"); 

			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_5\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\";");
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_2(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];");
			out.println("    document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];");
			out.println("}");
				
			
			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_FINANCE_NO_5\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\";");
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_3(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[5];");
			out.println("}");
			
							
					out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
					out.println("    document.Form1.hid_help_type.value=\"99\";"); 
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENTERED@\"+\"ENT_CON@\";"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
		
			
					

				

					

					out.println(""); 
		

					
		

		
					out.println("function close_screen() {");
					//out.println(" alert(document.Form1.hid_close_sts.value);");
					out.println("   document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");
					out.println("		if(document.Form1.hid_close_sts.value=='Y' ){ "); 
					out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		      window.close();"); 
					out.println("		     }"); 
					out.println("		 }"); 
					out.println("		else if(document.Form1.hid_CLOSE.value==\"Y\"){ "); 
					out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		      window.close();"); 
					out.println("					window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Status_Report?chksql=main_page';");
					out.println("		     }"); 
					out.println("		 }"); 
					out.println("		else { "); 
					out.println("		     close_window();"); 
					out.println("		}"); 
					out.println("}");

         
					
			out.println("function assig(val) {");
			out.println("document.Form1.hid_text.value=val");
			out.println("}");
						
									
			out.println("function check_client() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_reverse_app_client&data_val1=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
   
					
			out.println("function check_appnum() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_reverse_app_appnum&data_val1=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_finnum() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_reverse_app_finnum&data_val1=\"+document.Form1.TXT_FINANCE_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function Chk_Amount() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_chk_amount&rec_amt=\"+unformat_noobject(document.Form1.TXT_TOT_REC.value)+\"&pen_amt=\"+unformat_noobject(document.Form1.TXT_TOT_PENALTY.value);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_APPLICANT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICANT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}");
					
					
			out.println("function before_submit(){ "); 
		  out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_save_Reverse_Application';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}");
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
					
					
					
					

					//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
					
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_text' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_price_tot' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_profo_tot' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_tot_fin_amt' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_cur_fin_amt' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_close_sts' VALUE=\"N\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_transaction_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_CLOSE' VALUE=\"N\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_insurance_done' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_APPLICATION_PROCESS\">"); 

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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Reverse Application</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>");
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='load_screen_status(\"SAVE\"), before_submit()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
	    		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
		
					out.println("<table align='center' width='100%' border=0 class='' bordercolor=''>"); 
			   	out.println("<tr align='right'>");  
			   	out.println("<td width='100%'>");	
			   
					out.println("<table align='center' width='100%' border=0 class='table' >"); 
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICANT_CODE'  class=div_input>Client Code *</DIV></td>"); 
					out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_APPLICANT_CODE' maxlength='10' size='10' onblur=\"assig('J1'),check_client()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICANT_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
					out.println("<td >Client Name </td>");  //&nbsp&nbsp<input class='but_input' type='button' name='BUT_VIEW' value=\" View \" onClick=\"but_view()\"> 
					out.println("<td ><input class='txt_input' type='text' name='TXT_APPLICANT_NAME' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
					out.println("</tr>"); 
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
					out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assig('J2'),check_appnum()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_button_2()\" ></td>"); //M_APPLICATION_PROCESS_APPLICATION_HELP m_help_TXT_APPLICATION_NO
					out.println("</tr>"); 
					out.println("<tr >"); 
					out.println("<td  width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number *</DIV></td>"); 
					out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='15'  OnBlur=\"assig('J3'),check_finnum()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_3()\" > </td>"); 
					out.println("</tr>"); 
					/////////////////////
					out.println("<tr >"); 
					out.println("<td >Receipt Amount</td>"); 
					out.println("<td ><input class='txt_input' type='text' name='TXT_TOT_REC' maxlength='20' size='10' disabled></td>"); 
					out.println("<td ></td>");  
					out.println("<td ></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr >"); 
					out.println("<td >Penalty Amount</td>"); 
					out.println("<td ><input class='txt_input' type='text' name='TXT_TOT_PENALTY' maxlength='20' size='10' onblure=\"assig('J4'),Chk_Amount()\"></td>"); 
					out.println("<td ></td>");  
					out.println("<td ></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr >"); 
					out.println("<td >Repayment Amount</td>"); 
					out.println("<td ><input class='txt_input' type='text' name='TXT_TOT_REPAY' maxlength='20' size='10' disabled></td>"); 
					out.println("<td ></td>");  
					out.println("<td ></td>"); 
					out.println("</tr>"); 
					
					/////////////////////
				 	out.println("</table>"); 
			   	out.println("</td>"); 
			   	out.println("</tr>"); 
			   	out.println("</table>");
				 	out.println("<br>");  
				 	out.println("<table align='center' width='100%' border=0 class='' bordercolor=''>"); 
			   	out.println("<tr align='right'>");  
			   	out.println("<td width='100%'>");	
				 	out.println("</td>"); 
			   	out.println("<tr>");  
				 	out.println("</table>");	
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
      }
	  }catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  //if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}
}


