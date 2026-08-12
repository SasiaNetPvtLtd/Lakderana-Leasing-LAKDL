

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
// DEVELOPED BY : udara 
// DATE:08-01-2013

public class LAKDL_AF_CR_PRO_Enter_Deletion_letter_process_copy extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt;
	public ResultSet rs;
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
			stmt=conn.createStatement();
			
		
							 
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
			out.println("<TITLE> Deletion Letter Process Copy </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
					
					
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;"); //Boolean Variable To Hold The Status.

						
			out.println("var application_no=\"\"; ");
			
			
							
			out.println("function get_vector(data_vec) {");
			out.println("			 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
			out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
            out.println("      display_data(data_vec);"); 		
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
			//out.println("       document.Form1.elements[m_gur_code].value=\"\"; "); 
			//out.println("				help_button_5(document.Form1.hid_row_no.value);");
			//out.println("       document.Form1.elements[m_gur_code].focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
			out.println("			 alert('Guarantor code already exist ')	");
			out.println("			 m_gur_name=\"TXT_GAURANTOR_NAME\"+document.Form1.hid_row_no.value");
			out.println("			 m_tel_no=\"TXT_TEL_NO\"+document.Form1.hid_row_no.value;");
			out.println("			 m_nic_reg_no=\"TXT_NIC_REG_NO\"+document.Form1.hid_row_no.value;");	
			out.println("      document.Form1.elements[m_gur_code].value=\"\"; "); 
			out.println("      document.Form1.elements[m_gur_name].value=\"\"; "); 
			out.println("      document.Form1.elements[m_tel_no].value=\"\"; "); 
			out.println("      document.Form1.elements[m_nic_reg_no].value=\"\"; "); 
			//out.println("      document.Form1.elements[m_gur_code].focus()  "); 
			out.println("			}");
			out.println("}");
			

				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Enter_Deletion_letter_process_copy?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 

				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Enter_Deletion_letter_process_copy?chksql=main_page';"); 
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
				out.println("help_box.innerHTML=\" Deletion Letter Process Copy - \"+m_val;"); 
				out.println("}"); 
		
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Deletion Letter Process Copy  \";"); 
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
					
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("		if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("	if(oBj.valout[1]=='Next')  {");
			out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
			out.println("	}");
			out.println("	else if  (oBj.valout[1]=='Prev') {");
			out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
			out.println("	}		");
			out.println("	else if(oBj.valout[1] == 'Close'){");
			out.println("	}");
			out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
			out.println("	if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='2'){"); 
			out.println("		finance_assign(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='3'){"); 
			out.println("		help_value_assign_client(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='6'){"); 
			out.println("		help_value_assign_seizer_code(oBj);"); 
			out.println("	}");
			
			
			out.println("	}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}");  

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
					
			
		
					out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
					
					out.println("  if(document.Form1.SCREEN_NAME.value==\"EDIT\"){  ");
					out.println("    document.Form1.hid_help_type.value=\"99\";"); 
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_DELETION_LETTER_EDIT','99');");
					out.println("  }");
					out.println("  else{");
					out.println("    document.Form1.hid_help_type.value=\"99\";"); 
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENTERED@\"+\"ENT_CON@\";"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("  }");
					
					out.println("}"); 
		
					out.println("function help_update_value_assign_99(oBj) {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 					
					out.println("		 if(oBj.valout[3]=='' || oBj.valout[3]=='null' ){");
					out.println("     document.Form1.TXT_FINANCE_NO.value='-';"); 
					out.println("		 }");
					out.println("		 else{");
					out.println("     document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("    }");
					out.println("     document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("     document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];"); 
					
					/*
					out.println("  if(document.Form1.SCREEN_NAME.value==\"EDIT\"){  ");
					out.println("     document.Form1.TXT_SIEZER_CODE.value=oBj.valout[6];"); 
					out.println("  }");
					*/
					
					out.println("}"); 
					
					out.println("function help_finance() {"); 
					out.println("    document.Form1.hid_help_type.value=\"2\";"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"\"+\"@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_ClientSql_Receipt_cp','2');");
					out.println("}");
				
					out.println("function finance_assign(oBj) {"); 
					out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					out.println("   document.Form1.hid_app_no.value=oBj.valout[3];"); 					
					out.println("   document.Form1.hid_cli_no.value=oBj.valout[4];"); 
					//out.println(" 	makeRequest(); ");
					out.println("}");
					
					out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					//out.println("		 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value;");
					out.println("    Crit =document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function help_value_assign_client(oBj) {"); 
					out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 					
					out.println("   document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];"); 
					out.println("}"); 
					
					// added by udara 18-12-2012
					out.println("function help_seizer_code() {"); 
					out.println("    document.Form1.hid_help_type.value=\"6\";"); 
					out.println("    Crit = document.Form1.TXT_SIEZER_CODE.value+\"@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_SIEZER_CODE','6');");
					out.println("}"); 
					
					out.println("function help_value_assign_seizer_code(oBj) {"); 
					out.println("   document.Form1.TXT_SIEZER_CODE.value=oBj.valout[2];"); 
					out.println("}"); 
					// end by udara 18-12-2012
					
					out.println("function clear_fields(){"); 
					out.println("	 if(document.Form1.hid_help_type.value==\"99\") {" ); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value =''; ");
					out.println("   }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
					out.println("   document.Form1.TXT_FINANCE_NO.value='';"); 
					out.println("  }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
					out.println("   document.Form1.TXT_APPLICANT_CODE.value='';"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value='';"); 
					out.println("  }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" ); 
					out.println("			 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
					out.println("			 m_gur_name=\"TXT_GAURANTOR_NAME\"+document.Form1.hid_row_no.value");
					out.println("			 m_tel_no=\"TXT_TEL_NO\"+document.Form1.hid_row_no.value;");
					out.println("			 m_nic_reg_no=\"TXT_NIC_REG_NO\"+document.Form1.hid_row_no.value;");	
					out.println("      document.Form1.elements[m_gur_code].value=\"\"; "); 
					out.println("      document.Form1.elements[m_gur_name].value=\"\"; "); 
					out.println("      document.Form1.elements[m_tel_no].value=\"\"; "); 
					out.println("      document.Form1.elements[m_nic_reg_no].value=\"\"; "); 
					out.println("  }		"); 
					/*
					out.println("	 else	if(document.Form1.hid_help_type.value==\"6\") {" ); 
					out.println("   	document.Form1.TXT_SIEZER_CODE.value='';"); 
					out.println("  }		"); 
					*/
					out.println("}		"); 					

					
		            out.println("function disable_app_no(){")			;
		   	        out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
					//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				    out.println("}");
						
					out.println("function load_guarantor(){");
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_application_guarantors?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value;"); 
			        out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 			out.println("}");
					
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

          
					out.println("function but_view(){");
          			out.println("  m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Printing_View?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&FIN_NO='+document.Form1.TXT_FINANCE_NO.value+'&CLIENT_NO='+document.Form1.TXT_APPLICANT_CODE.value;");  
			    	out.println("  window.open(m_url,'displayWindow4','left=20,top=330,width=980,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
          			out.println("}");
						
					out.println("function validate_data(){"); 
					out.println("   if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
					out.println("     DIV_TXT_FINANCE_NO.style.color='red';");
					out.println("     return false;"); 
					out.println("  }"); 
					out.println("  else{"); 
					out.println("    return true;"); 
					out.println("  }"); 
					out.println("}");	
			
					out.println("function before_submit(){ "); 
				    out.println("	if(validate_data()){"); 
					out.println("		if(confirm(\"Are you sure you want to save?\")){ ");
					out.println("		   document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Enter_Deletion_letter_process_copy_save';");  
					out.println("		   document.Form1.submit();	"); 
					out.println("		}");
					out.println("	}"); 
					out.println("   else{");
					out.println("     alert(\"Please enter all required fields marked with a '*' on screen\");");
					out.println("   } "); 
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
				    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
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
					
					out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cli_no' VALUE=\"\">");

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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Deletion Letter Process Copy</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>");
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='before_submit();' value=\"Save\"></td>");  
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
					/*
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
					//out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"help_update('1','10','5','m_help_TXT_APPLICATION_NO_4','99')\">"); 
					//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update('1','10','5','m_help_TXT_APPLICATION_NO_4','99')\" ></td>"); //M_APPLICATION_PROCESS_APPLICATION_HELP m_help_TXT_APPLICATION_NO
					out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"help_update('1','10','5','m_help_DELETION_LETTER','99')\">"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update('1','10','5','m_help_DELETION_LETTER','99')\" ></td>");  // 
					out.println("</tr>"); 
					*/ 
					out.println("<tr >"); 
					out.println("<td  width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
					out.println("<td  width='35%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='15'  OnBlur=\"help_finance()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"...\" onClick=\"help_finance()\" > </td>"); 
					out.println("<td  width='*%' > &nbsp; </td>"); 
					out.println("</tr>"); 
					
					
					out.println("<tr >"); 
					out.println("<td  width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Remarks</DIV></td>"); 
					out.println("<td  width='35%' ><input  type='textarea' class='txt_input'  name='TXT_REMARKS' maxlength='200' size='100' style=\"width:250px; height:50px;\" > </textarea></td>"); //  size='50'
					out.println("<td  width='*%' > &nbsp; </td>"); 
					out.println("</tr>");
					
					/*
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICANT_CODE'  class=div_input>Client Code *</DIV></td>"); 
					out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_APPLICANT_CODE' maxlength='10' size='10' onblur=\"help_button_2('1','10','0','m_help_TXT_CLIENT_NO_4','3')\">"); 
					//out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICANT_CODE' value=\"...\" onClick=\"help_button_2('1','10','0','m_help_TXT_CLIENT_NO_4','3')\">&nbsp&nbsp<input class='but_input' type='button' name='BUT_VIEW' value=\" View \" onClick=\"but_view()\"></td>"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICANT_CODE' value=\"...\" onClick=\"help_button_2('1','10','0','m_help_TXT_CLIENT_NO_4','3')\"> </td>"); 
					out.println("<td >Client Name </td>"); 
					out.println("<td ><input class='txt_input' type='text' name='TXT_APPLICANT_NAME' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
					out.println("</tr>"); 
					*/
					
					/*
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_SIEZER_CODE'  class=div_input>Seizer Code</DIV></td>"); 
					out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_SIEZER_CODE' maxlength='10' size='10' onblur=\"\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_SEIZER_CODE' value=\"...\" onClick=\"help_seizer_code();\" > </td>"); 
					out.println("</tr>");
					*/
					
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
	    //if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}
}


