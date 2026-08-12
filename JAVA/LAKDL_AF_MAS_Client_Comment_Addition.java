import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
// DEVELOPED BY : Chandana 
// DATE:03-10-2007

public class LAKDL_AF_MAS_Client_Comment_Addition extends javax.servlet.http.HttpServlet { 
	
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
			out.println("<TITLE> System Administration - Client Comment Addition </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
							
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;"); 
			out.println("var application_no=\"\"; ");
							
			out.println("function get_vector(data_vec) {");
			out.println("			 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
			out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
      out.println("      display_data(data_vec);"); 		
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
			out.println("			 alert('Guarantor code already exist ')	");
			out.println("			}");
			out.println("}");
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Client_Comment_Addition?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Client_Comment_Addition?chksql=main_page';"); 
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
			out.println("help_box.innerHTML=\" System Administration - Client Special Comment Addition - \"+m_val;"); 
			out.println("}"); 
	
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Client Special Comment Addition  \";"); 
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
			out.println("}	"); 
					
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
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\"+\"ENT_CON@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
	
				out.println("function help_update_value_assign_99(oBj) {"); 
				out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 					
				out.println("}"); 
				
				
				out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
				out.println("    document.Form1.hid_help_type.value=\"3\";"); 
				out.println("    Crit =document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
	
				out.println("function help_value_assign_client(oBj) {"); 
				out.println("   document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[2];"); 
				out.println("   document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[3];"); 
				out.println("}"); 
				
				out.println("function clear_fields(){"); 
				out.println("	 if(document.Form1.hid_help_type.value==\"99\") {" ); 
				out.println("   document.Form1.TXT_APPLICATION_NO.value =''; ");
				out.println("   }		"); 
				out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
				out.println("   document.Form1.TXT_APPLICANT_CODE.value='';"); 
				out.println("   document.Form1.TXT_APPLICANT_NAME.value='';"); 
				out.println("  }		"); 
				out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" ); 
				out.println("  }		"); 
				out.println("}		"); 					
	
				out.println("function close_screen() {");
				out.println("   document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");
				out.println("		if(document.Form1.hid_close_sts.value=='Y' ){ "); 
				out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
				out.println("		      window.close();"); 
				out.println("		     }"); 
				out.println("		 }"); 
				out.println("		else if(document.Form1.hid_CLOSE.value==\"Y\"){ "); 
				out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
				out.println("		      window.close();"); 
				out.println("		     }"); 
				out.println("		 }"); 
				out.println("		else { "); 
				out.println("		     close_window();"); 
				out.println("		}"); 
				out.println("}");
				
				out.println("function validate_data(){"); 
				out.println("m_sub=0;"); 
				out.println("if(document.Form1.TXT_APPLICANT_CODE.value==\"\"){  "); 
		    out.println("DIV_TXT_APPLICANT_CODE.style.color='red';");
				out.println("m_sub=1;"); 
				out.println("		}"); 
				out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
		    out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
				out.println("m_sub=1;"); 
				out.println("		}"); 
				out.println("if(m_sub==0){"); 
				out.println("return true;");
				out.println("}else if(m_sub==1){ "); 	
        out.println("return false;");
				out.println("} ");
				out.println("} ");
				
				
				out.println("function befor_submit(){ "); 
				out.println("		if(validate_data()){");
				out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
				out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_save_client_comments';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}");
				out.println("		}"); 
				out.println("} "); 
				
				out.println("	function chk_comment_length(obj){ ");
  			out.println(" var remarks_length=obj.value.toString().length;");
				out.println("if(remarks_length>obj.maxlength) ");
	  		out.println("		window.event.keyCode=\"\"; ");
				out.println("} ");
				
				out.println("function count_length(obj){ ");
			  out.println("var remarks_length=obj.value.toString().length; ");
			  out.println("var remarks=obj.value.toString(); ");
			  out.println("if(remarks_length>obj.maxlength){ ");
			  out.println("obj.value=remarks.substring(0,obj.maxlength); ");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Client Special Comment Addition</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>");
				
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onclick='befor_submit()' value=\"Save\"></td>"); 
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
				out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_APPLICANT_CODE' maxlength='10' size='10' onblur=\"help_button_2('1','10','0','m_help_TXT_CLIENT_NO_7','3')\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICANT_CODE' value=\"Help\" onClick=\"help_button_2('1','10','0','m_help_TXT_CLIENT_NO_7','3')\"></td>"); 
				out.println("<td >Client Name </td>"); 
				out.println("<td ><input class='txt_input' type='text' name='TXT_APPLICANT_NAME' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
				out.println("</tr>"); 
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
				out.println("<td width='35%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"help_update('1','10','0','m_help_TXT_APPLICATION_NO_7','99')\">"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('1','10','0','m_help_TXT_APPLICATION_NO_7','99')\" ></td>"); //M_APPLICATION_PROCESS_APPLICATION_HELP m_help_TXT_APPLICATION_NO
				out.println("</tr>"); 
				
				out.println("<tr >");
				out.println("<td width='20%' >Cashier Dispaly </td>");
				out.println("<td width='35%' ><select class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'>");
				out.println("<option value='N' selected> No </option>");
				out.println("<option value='Y' >Yes</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>"); 

				out.println("<tr >"); 
				out.println("<td width='20%' >&nbsp</td>"); 
				out.println("<td width='35%' >&nbsp</td>");
				out.println("</tr>"); 
				
				
				out.println("</table>");
				
				
				out.println("<table align='center' width='100%' border=0 class='table' >");
				out.println("<tr >"); 
				out.println("<td width='24%' valign='top'><DIV id='DIV_TXT_COMMENT'  class=div_input >Special Comment</DIV></td>"); 
				out.println("<td width='35%' ><TEXTAREA class='txt_input' name='TXT_COMMENTS'  maxlength='3000' style='{width:410px; height:250px;}' size='200' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"></TEXTAREA>");  //onKeypress=\"chk_comment_length(this)\" 
				out.println("</td>"); 
				out.println("<td width='*%' >"); 
				out.println("</td>");
				out.println("</tr>"); 
				
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


