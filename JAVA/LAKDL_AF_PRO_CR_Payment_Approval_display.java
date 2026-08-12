/*
 * SCREEN NAME  : Credit Process - Main Screen Payment New 2
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-09-14
 * NOTES        :
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LAKDL_AF_PRO_CR_Payment_Approval_display extends HttpServlet {
	
	LAKDL_AF_CO_conn_methods m_sn_methods;
	String m_html_client_url;
	String m_fschema_name;
	String m_schema_name;
	String m_username;
	String m_class_url;
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	String sql;
	
	ServletOutputStream out;
	
	NumberFormat numberFormat;
	
	String m_chksql;
	String m_sort_column;
	String m_order_by_type;
	String m_approval_level;
	
	String process_status_get;
	String process_status_post;
	String group_payment_no;
	
	String m_screen_title;
	String m_paymno;      //Added by Prabash on 13-02-2012
	
	
	int j;
	
	public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		
		try {
			
			m_sn_methods = new LAKDL_AF_CO_conn_methods();
			connection = m_sn_methods.met_user_validate(httpServletRequest);
			
			m_html_client_url   = m_sn_methods.html_client_url;
			m_fschema_name      = m_sn_methods.client_name;
			m_schema_name       = m_sn_methods.schema_name;
			m_username          = m_sn_methods.username;
			m_class_url         = m_sn_methods.servlet_client_url + ":" + m_sn_methods.client_t3_port;
			
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			httpServletResponse.setContentType("text/html");
			out = httpServletResponse.getOutputStream();
			
			
			m_chksql            = httpServletRequest.getParameter("chksql");
			m_approval_level    = httpServletRequest.getParameter("approval_level");
			
			
			// m_chksql1 = httpServletRequest.getParameter("chksql2");
			// m_screen_type= httpServletRequest.getParameter("screen_type");
			// m_status_new= httpServletRequest.getParameter("status_new");
			// m_status_edit= httpServletRequest.getParameter("status_edit");
			
			if (m_approval_level.equals("1")) {
				process_status_get = "RE-APP";
				process_status_post = "APPRO1";
			}
			else if (m_approval_level.equals("2")) {
				process_status_get = "APPRO1";
				process_status_post = "APPRO2";
			}
			
			m_screen_title = "Finance - Payment Approval " + m_approval_level;
			
			
			numberFormat = NumberFormat.getInstance(Locale.US);
			numberFormat.setMinimumFractionDigits(2);
			numberFormat.setMaximumFractionDigits(2);
			
			
			if (m_chksql.equals("main_page")) {
				
				out.println("<html>");
				out.println("   <head>");
				out.println("       <title>" + m_screen_title + "</title>");
				
				out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url+"/css/Asset_Financing_System.css\" />");
				
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/leasing_drill_down.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
				
				out.println("       <script type=\"text/javascript\">");
				
				out.println("           var g_get_data_type = 0;");
				
				
				out.println("           function clear_window() {");
				out.println("               if (confirm('Are you sure you want to clear the screen?')) {");
				out.println("                   window.location.href = window.location.href;");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function new_window() {");
				out.println("               window.location.href = window.location.href;");
				out.println("           }");
				
				
				out.println("           function load_help_msg() {");
				// if(m_chksql1.equals("B")){
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_1\";"); 
				// }
				// else if(m_chksql1.equals("A")){
				// out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_2\";"); 
				// }
				// else if(m_chksql1.equals("R")){
				// out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_req\";"); 
				// }
				
				out.println("               HelpBox_msg(m_help_message);");
				out.println("           }");
				
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				
				out.println("           function load_roll_value(m_val) {");
				out.println("               document.getElementById('help_box').innerHTML = '" + m_screen_title + " - ' + m_val;");
				out.println("           }");
				
				
				out.println("           function load_roll_out_value() {");
				out.println("               document.getElementById('help_box').innerHTML = '" + m_screen_title + " - ' + document.getElementById('hid_status').value;");
				out.println("           }");
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}");
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val==\"REVERSE\"){"); //Added BY Sandun on 19-01-2009
				out.println("edit_window();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println("}"); 
				out.println("else{");
				out.println("edit_window();"); 
				out.println("}");
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save.value=\"save\";");
				out.println("}else if(m_val==\"EDIT\"){");
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save.value=\"Delete\";");
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}");
				out.println("}");
				
				
				out.println("           function load_payments() {");
				out.println("               g_get_data_type = 1;");
				out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Approval_display?chksql=load_data&paymno='+document.Form1.TXT_PAYMENT_NO.value+'&approval_level=" + m_approval_level + "';");
				out.println("               load_interface(m_url, 'NORM');");
				out.println("           }");
				
				
				out.println("           function show_details(row_id) {");
				// out.println("               g_get_data_type = 2;");
				out.println("               var group_payment_no = document.getElementById('HID_GROUP_PAYMENT_NO_' + row_id).value;");
				out.println("               var m_url = servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_PRO_CR_Group_Payment_Details_display?group_payment_no=' + group_payment_no;");
				// out.println("               load_interface(m_url, 'NORM');");
				
				out.println("               var sFeatures = '';");
				out.println("               sFeatures += 'dialogHeight: ' + (parseInt(screen.availHeight) - 100) + 'px;';");
				out.println("               sFeatures += 'dialogWidth: ' + (parseInt(screen.availWidth) - 150) + 'px;';");
				// out.println("               sFeatures += 'status: no;';");
				
				out.println("               window.showModalDialog(");
				out.println("                   m_url,");
				out.println("                   group_payment_details,");
				out.println("                   sFeatures");
				out.println("               );");
				out.println("           }");
				
				
				out.println("           function group_payment_details() {");
				out.println("               var group_payment_details;");
				out.println("           }");
				
				
				out.println("           function get_vector_normal(http_response) {");
				out.println("               if (g_get_data_type == 1) {");
				out.println("                   document.getElementById('DIV_PAYMENTS').innerHTML = http_response;");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function check_approve(row_id) {");
				out.println("               var check_status = document.getElementById('CHK_APPROVE_' + row_id).checked;");
				out.println("               var checked_count = parseInt(document.getElementById('hid_selected_checkbox_count').value);");
				
				out.println("               if (check_status == true) {");
				out.println("                   checked_count += 1;");
				out.println("               }");
				out.println("               else {");
				out.println("                   checked_count -= 1;");
				out.println("               }");
				out.println("               document.getElementById('hid_selected_checkbox_count').value = checked_count;");
				out.println("           }");
				
				
				out.println("           function validate_data() {");
				out.println("               if (document.getElementById('hid_selected_checkbox_count').value == '0') {");
				out.println("                   alert('Please select at leaset one payment.');");
				out.println("                   return false;");
				out.println("               }");
				out.println("               return true;");
				out.println("           }");
				
				
				out.println("           function before_submit() {");
				out.println("               if ((validate_data()) && (confirm('Are you sure you want to save?'))) {");
				out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Approval_save';");
				out.println("                   document.Form1.submit();");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function save_window() {");
				out.println("               before_submit();");
				out.println("           }");
				
				//=====Added by Prabash on 10-02-2012 ==========================================
				out.println("function payment_no_help(){");
				out.println("Crit=document.Form1.TXT_PAYMENT_NO.value+\"@\"+document.Form1.hid_process_status_get.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'Paymentno1','1');");
				out.println("}");
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.TXT_PAYMENT_NO.value =oBj.valout[2]");
				out.println("}");
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_CO_Help_Servlet?class_in="+m_fschema_name+"AF_CR_Ter_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		receipt_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		vehicle_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		lease_assign(oBj);"); 
				out.println("		}");
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
				out.println("	}	"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function check_client(val) {");
				out.println(" document.Form1.TXT_PAYMENT_NO.value = ''  ");
				out.println("}");	
				//=======================================================================	
				
				out.println("       </script>");
				out.println("   </head>");
				
				//  out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" onload=\"load_payments();\">");
				out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\">");
				out.println("       <form name=\"Form1\" method=\"post\">");
				out.println("           <input type=\"hidden\" name=\"SCREEN_NAME\" id=\"SCREEN_NAME\" value=\"NEW\" /> ");
				out.println("           <input type=\"hidden\" name=\"hid_help_type\" id=\"hid_help_type\" value=\"\" />");
				out.println("           <input type=\"hidden\" name=\"hid_status\" id=\"hid_status\" value=\"New\" />");
				out.println("           <input type=\"hidden\" name=\"hid_no\" id=\"hid_no\" value=\"\" />");
				out.println("           <input type=\"hidden\" name=\"hid_save\" id=\"hid_save\" value=\"Save\" />");
				out.println("           <input type=\"hidden\" name=\"Hid_scr_name\" id=\"Hid_scr_name\" value=\"AF_CR_PRO_PAYMENT_REQ_MAIN\" />");
				out.println("           <input type=\"hidden\" name=\"hid_selected_checkbox_count\" id=\"hid_selected_checkbox_count\" value=\"0\" />");
				out.println("           <input type=\"hidden\" name=\"hid_process_status_post\" id=\"hid_process_status_post\" value=\"" + process_status_post + "\" />");
				out.println("           <input type=\"hidden\" name=\"hid_approval_level\" id=\"hid_approval_level\" value=\"" + m_approval_level + "\" />");
				out.println("           <input type=\"hidden\" name=\"hid_process_status_get\" id=\"hid_process_status_get\" value=\"" + process_status_get + "\" />"); // added by Prabash on 13-02-2012
				
				out.println("           <table width=\"100%\" class=\"table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
				out.println("               <tr>"); 
				// out.println("                   <td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("                   <td width='8' valign='top'></td>");
				out.println("                   <td class='border_wht' valign='top'> "); 
				out.println("                       <table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("                           <tr> "); 
				out.println("                               <td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
				out.println("                           </tr>"); 
				out.println("                           <tr> "); 
				out.println("                               <td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("                           </tr>"); 
				out.println("                           <tr>"); 
				// out.println("                               <td style='height: 327px'>"); 
				out.println("                               <td>");
				out.println("                                   <table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>");
				out.println("                                       <tr>"); 
				out.println("                                           <td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("                                       </tr>");
				out.println("                                       <tr>");
				out.println("                                           <td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>" + m_screen_title + " - New</td>"); 
				out.println("                                       </tr>"); 
				out.println("                                       <tr>"); 
				out.println("                                           <td  height='10px' class='pdn_txtpos'>"); 
				out.println("                                               <table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("                                                   <tr><td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\")' name=\"bt_dele\" value=\"Delete\"></td>");
				// out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reverse\");' onClick='load_screen_status(\"REVERSE\")' name=\"bt_rev\" value=\"Reverse\"></td>");  
				out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window();' name=\"bt_save\" value=\"Save\"></td>");
				// out.println("<td width='10%' align='center'><input name =\"save\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("                                                       <td width='100%'></td>");  
				/*if(m_screen_type.equals("NEW")){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
                }
                if(m_screen_type.equals("EDIT") ){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
                }
                */
				// if(m_screen_type.equals("REVERSE") ){
				// out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
				// }
				out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("                                                       <td><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
				out.println("                                                   </tr>");  
				out.println("                                               </table>");  
				
				
				//=======added by Prabash on 13-02-2012=====================
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr class=tr_input>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYMENT_NO'  class=div_input>Payment No </DIV></td>"); 
				out.println("<td><input class='txt_input' type='text' name='TXT_PAYMENT_NO' maxlength='15' style=\"{width:250px;}\" size='15' >");  //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\"
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"payment_no_help()\">");//Added by prabash on 10-02-2012
				out.println("<input class='but_input' type='button' name='BUT_TXT_PAYMENT_NO_SEARCH' value=\"Search\" onClick=\"load_payments();\"></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				//=========================================================
				
				out.println("                                           </td>");  
				out.println("                                       </tr>");  
				out.println("                                       <tr>");  
				out.println("                                           <td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("                                       </tr>");
				
				out.println("                                       <tr class='pdn_txtpos' valign='top'>");
				out.println("                                           <td>");
				out.println("                                               <br />");
				out.println("                                               <div id=\"DIV_PAYMENTS\"></div>");
				out.println("                                           </td>");
				out.println("                                       </tr>");
				
				// // out.println("                                               <table width='100%' class=\"table\">");
				// out.println("                                               <table class=\"table\">");
				// out.println("                                                   <tr>");  
				// // out.println("                                                       <td width=\"100%\"><div id=\"DIV_PAYMENTS\"></div></td>");
				// out.println("                                                       <td><div id=\"DIV_PAYMENTS\"></div></td>");
				// out.println("                                                   </tr>"); 
				// out.println("                                               </table>"); 
				
				
				/*out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\")' name=\"bt_dele\" value=\"Delete\"></td>");  
                out.println("<td width='6%'></td>");  
                if(m_screen_type.equals("NEW")){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
                }
                if(m_screen_type.equals("EDIT") ){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
                }
                
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
                out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
                out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
                out.println("</table>"); 
                */
				
				out.println("                                   </table>");
				out.println("                               </td>");
				out.println("                           </tr>");
				out.println("                       </table>");
				out.println("                   </td>");
				out.println("               </tr>");
				out.println("           </table>");
				out.println("       </form>");
				out.println("   </body>");
				out.println("</html>");
				
			}
			
			else if (m_chksql.equals("load_data")) {
				m_paymno      = httpServletRequest.getParameter("paymno"); //Added by Prabash on 13-02-2012
				
				// if ((httpServletRequest.getParameter("sort_column") != null) && (httpServletRequest.getParameter("order_by_type") != null)) {
				// m_sort_column = httpServletRequest.getParameter("sort_column");
				// m_order_by_type = httpServletRequest.getParameter("order_by_type");
				// }
				
				sql = " " +
					"   SELECT B.GROUP_PAYMENT_NO, " +
					"          NVL(B.PAYEE_NAME, '-') PAYEE_NAME, " +
					"          SUM(A.SETTELED_AMOUNT) PAY_AMOUNT, " +
					"          DECODE(B.SETTLE_MODE, 'CHQ', 'Cheque', 'Cash', '-') SETTLE_MODE " +
					"   FROM   " + m_schema_name + ".AF_CR_PRO_SET_PAY_BREAKDOWN A, " +
					"          " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT B " +
					"   WHERE  A.PAYMENT_NO = B.PAYMENT_NO " +
					"   AND    B.PROCESS_STATUS = '" + process_status_get + "' " +
					"   AND    A.ENTRY_TYPE = 'V' " +
					"   AND    B.GROUP_PAYMENT_NO IS NOT NULL " +
					"   AND    B.GROUP_PAYMENT_NO LIKE '%"+m_paymno+"%'  "+ // Added by Prabash on 10-02-2012
					"   GROUP BY B.GROUP_PAYMENT_NO, " +
					"            B.PAYEE_NAME, " +
					"            B.SETTLE_MODE " +
					// "   ORDER BY " + m_sort_column + " " + m_order_by_type + " " +
					" ";
				
				
				//out.println(sql);
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				
				j = 0;
				
				out.println("<table class=\"table\" align=\"left\" border=\"0\">");
				
				while (resultSet.next()) {
					
					j++;
					
					if (j == 1) {
						out.println("<tr class=\"pdn_txtpos2\">");
						out.println("<td width=\"2%\" align=\"center\">No.</td>");
						out.println("<td width=\"10%\" align=\"center\">Payment No.</td>");
						out.println("<td width=\"9%\" align=\"center\">Receiver</td>");
						out.println("<td width=\"9%\" align=\"center\">Amount</td>"); 
						out.println("<td width=\"4%\" align=\"center\">Type</td>"); 
						out.println("<td width=\"3%\" align=\"center\">User</td>");
						out.println("<td width=\"4%\" align=\"center\">Approved</td>");
						out.println("<td width=\"4%\" align=\"center\">Details</td>");
						out.println("</tr>");
					}
					
					if (j % 2 == 1) {
						out.println("<tr class=tr_input>");
					}
					else {
						out.println("<tr class=tr_input1>");
					}
					
					out.println("<td style=\"text-align: right;\">" + j + ".</td>");
					out.println("<td style=\"text-align: center;\"><div>" + resultSet.getString("GROUP_PAYMENT_NO") + "</div><input type=\"hidden\" name=\"HID_GROUP_PAYMENT_NO_" + j + "\" id=\"HID_GROUP_PAYMENT_NO_" + j + "\" value=\"" + resultSet.getString("GROUP_PAYMENT_NO") + "\" /></td>");
					out.println("<td style=\"text-align: left;\">" + resultSet.getString("PAYEE_NAME") + "</td>");
					out.println("<td style=\"text-align: right;\">" + numberFormat.format(resultSet.getBigDecimal("PAY_AMOUNT")) + "</td>");
					out.println("<td style=\"text-align: center;\">" + resultSet.getString("SETTLE_MODE") + "</td>");
					out.println("<td style=\"text-align: center;\">" + m_username + "</td>");
					out.println("<td style=\"text-align: center;\"><input type=\"checkbox\" name=\"CHK_APPROVE_"+j+"\" id=\"CHK_APPROVE_"+j+"\" value=\"A\" onclick=\"check_approve('" + j + "');\" /></td>");
					out.println("<td style=\"text-align: center;\"><input class=\"but_input\" type=\"button\" name=\"BUT_DETAILS_"+j+"\" id=\"BUT_DETAILS_"+j+"\" value=\"Details\" onclick=\"show_details('" + j + "');\" /></td>");
					
				}
				resultSet.close();
				statement.close();
				
				out.println("</table>");
				out.println("<input type=\"hidden\" name=\"hid_count\" id=\"hid_count\" value=\"" + j + "\" />");
				
			}
			
			else {
				out.println("idle");
			}
			
		}
		
		catch (Exception exception) {
			
			try {
				out.println("Error : " + exception.toString());
			}
			catch(Exception e) {}
			
			exception.printStackTrace();
			
		}
		
		finally {
			
			if (resultSet != null) {
				try {
					resultSet.close();
				}
				catch(Exception e) {}
			}
			if (statement != null) {
				try {
					statement.close();
				}
				catch(Exception e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				}
				catch(Exception e) {}
			}
			if (out != null) {
				try {
					out.close();
				}
				catch(Exception e) {}
			}
			
		}
		
	}
	
}
