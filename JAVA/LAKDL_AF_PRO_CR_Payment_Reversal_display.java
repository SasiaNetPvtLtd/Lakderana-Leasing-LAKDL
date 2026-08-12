/*
 * SCREEN NAME  : Credit Process - Payment Reversal
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-09-23
 * NOTES        :
 */


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LAKDL_AF_PRO_CR_Payment_Reversal_display extends HttpServlet {
    
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
    DateFormat dateFormat;
    
    String m_chksql;
    String m_sort_column;
    String m_order_by_type;
    String m_approval_level;
    
    String process_status_get;
    String process_status_post;
    String group_payment_no;
    
    String m_screen_title;
    
    int i;
    
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
            
            m_screen_title = "Finance - Payment Reversal";
            
            
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
                
                out.println("           var g_screen_title = '" + m_screen_title + "';");
                
                
                out.println("           function new_window() {");
                out.println("               window.location.href = window.location.href;");
                out.println("           }");
                
                
                out.println("           function clear_window() {");
                out.println("               if (confirm('Are you sure you want to clear the screen?')) {");
                out.println("                   window.location.href = window.location.href;");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function load_help_msg() {");
                out.println("               var m_help_message = 'm_help_msg_LAKDL_AF_PRO_CR_display_main_screen_1';");
                out.println("               HelpBox_msg(m_help_message);");
                out.println("           }");
                
                
                out.println("           function HelpBox_msg(m_help_message) {");
                out.println("               var popupwin = window.showModalDialog(servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_PRO_CR_Help_Msg_Servlet?class_in=' + client_name + 'AF_PRO_CR_Help_Msg_select&help_message_in=' + m_help_message);");
                out.println("           }");
                
                
                out.println("           function load_roll_value(m_val) {");
                out.println("               document.getElementById('help_box').innerHTML = g_screen_title + ' - ' + m_val;");
                out.println("           }");
                
                
                out.println("           function load_roll_out_value() {");
                out.println("               document.getElementById('help_box').innerHTML = g_screen_title;");
                out.println("           }");
                
                
                out.println("           function load_payments() {");
		
                out.println("               g_get_data_type = 1;");
                out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Reversal_display?chksql=load_data&payee='+ document.getElementById('CLIENT_CODE').value+'&pamt_no='+ document.getElementById('TXT_PAYMENT_NO').value+'&process_status=' + document.getElementById('SEL_REVERSE_TYPE').value;");
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
				
				//out.println("alert(check_status);");
				//out.println("alert(checked_count);");
                
                out.println("               if (check_status == true) {");
                out.println("                   checked_count += 1;");
                out.println("               }");
                out.println("               else {");
                out.println("                   checked_count -= 1;");
                out.println("               }");
                out.println("               document.getElementById('hid_selected_checkbox_count').value = checked_count;");
                out.println("           }");
                
                
                out.println("           function save_window() {");
                out.println("               before_submit();");
                out.println("           }");
                
                
                out.println("           function before_submit() {");
                out.println("               if ((validate_data()) && (confirm('Are you sure you want to save?'))) {");
                out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Reversal_save';");
                out.println("                   document.Form1.submit();");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function validate_data() {");
                out.println("               if (document.getElementById('hid_selected_checkbox_count').value == '0') {");
                out.println("                   alert('Please select at leaset one payment.');");
                out.println("                   return false;");
                out.println("               }");
                out.println("               return true;");
                out.println("           }");
				
				
				// added by udara on 18-02-2012
				 out.println("function check_all_chkbox()");
				 out.println("{ ");
				 out.println("   check_true=false;    ");
				 out.println("       if(document.Form1.CHECK_ALL.checked==true)");
				 out.println("       { ");
				 out.println("          check_true=true;");
				 out.println("       }else  ");
				 out.println("       { ");
				 out.println("         check_true=false;");
				 out.println("       } ");
				 out.println("   for(m=1;m<=parseInt(document.Form1.hid_count.value);m++) ");
				 out.println("   { ");
				 out.println("        document.Form1.elements[\"CHK_APPROVE_\"+m].checked=check_true; ");
				 out.println("        check_approve(m); "); 
				 out.println("   } "); 
				 out.println("} ");
				
				// end by udara on 18-02-2012
				
				//=====Added by Prabash on 17-02-2012 ==========================================
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'receiver','1');");
				out.println("}");
				
				out.println("function payment_no_help(){");
				out.println("Crit=document.Form1.TXT_PAYMENT_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'receiver','2');");
				out.println("}");
		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");  
				out.println("}");
				out.println("function payment_no_assign(oBj){");
				out.println(" document.Form1.TXT_PAYMENT_NO.value =oBj.valout[3]");  
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

			out.println("popupwin = window.showModalDialog('"+ m_class_url +"/"+ m_fschema_name +"AF_CO_Help_Servlet?class_in=" + m_fschema_name +"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  		out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		payment_no_assign(oBj);"); 
	  		out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		client_assign(oBj);"); 
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
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                out.println("           <input type=\"hidden\" name=\"SCREEN_NAME\" id=\"SCREEN_NAME\" value=\"NEW\" /> ");
                out.println("           <input type=\"hidden\" name=\"hid_no\" id=\"hid_no\" value=\"\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_scr_name\" id=\"Hid_scr_name\" value=\"AF_CR_PRO_PAYMENT_REQ_MAIN\" />");
                out.println("           <input type=\"hidden\" name=\"hid_selected_checkbox_count\" id=\"hid_selected_checkbox_count\" value=\"0\" />");
                
                out.println("           <table width=\"100%\" class=\"table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
                out.println("               <tr>");
                out.println("                   <td width=\"8\" valign=\"top\"></td>");
                out.println("                   <td class=\"border_wht\" valign=\"top\">");
                out.println("                       <table class=\"table\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
                out.println("                           <tr>");
                out.println("                               <td height=\"30\" class=\"pdn_mainHD\">Asset Financing System</td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td height=\"1\"></td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td>");
                out.println("                                   <table class=\"table\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">");
                out.println("                                       <tr>");
                out.println("                                           <td height=\"1\"></td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px;\" id=\"help_box\">" + m_screen_title + "</td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td height=\"10px\" class=\"pdn_txtpos\">");
                out.println("                                               <table class=\"table\" cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('New');\" onclick=\"new_window();\" name=\"bt_new\" value=\"New\" /></td>");
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Save');\" onclick=\"save_window();\" name=\"bt_save\" value=\"Save\" /></td>");
                out.println("                                                       <td width=\"100%\"></td>");
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Help');\" onclick=\"load_help_msg();\" value=\"Help\" /></td>");
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Cancel');\" onclick=\"clear_window();\" value=\"Cancel\" /></td>");
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Close');\" onclick=\"close_window();\" name=\"back\" value=\"Close\" /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class=\"line\" height=\"1\"></td>");
                out.println("                                       </tr>");
                
                out.println("                                       <tr>");
                out.println("                                           <td class=\"pdn_txtpos\" valign=\"top\">");
                out.println("                                               <br />");
				
				//== Added by prabash on 15-02-2012====
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 			
					
			
			
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'&gt;</td>");
				out.println("</tr>");
			
				out.println("<tr class=tr_input>");
				out.println("<td>Receiver</td>");
				out.println("<td ><input class='txt_input' type='text' name=\"CLIENT_CODE\" id=\"CLIENT_CODE\"  maxlength='15' style=\"{width:250px;}\" size='15'onBlur='client_help()' >");
				out.println("<input type=button name=cli_help value=Help class=\"but_input\" onclick=\"client_help()\">");
				out.println("<input type='button' class='but_input' name='MAIN_BBT' value= 'Search' OnClick ='load_payments()'></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Payment No</td>");
				out.println("<td><input type=text name=\"TXT_PAYMENT_NO\" id=\"TXT_PAYMENT_NO\" class='txt_input'  style=width:150px onBlur='payment_no_help()'>");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"payment_no_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");
  			//	 out.println("</table>");
				//=====================================		
				
                
          //      out.println("                                               <table align=\"left\" border='1' class=\"table\">");
                out.println("                                                   <tr class=\"tr_input\">");
                out.println("                                                   <td style=\"width: 200px;\"><div class=\"div_input\">Payment Stage</div></td>");
                out.println("                                                    <td style=\"width: 200px;\">");
                out.println("                                                     <select class=\"txt_input\" name=\"SEL_REVERSE_TYPE\" id=\"SEL_REVERSE_TYPE\" style=\"width: 150px;\" onchange=\"load_payments();\" />");
                out.println("                                                       <option value=\"PRINT\" selected=\"selected\">Payment Print</option>");
                out.println("                                                       <option value=\"APPRO2\">Payment Approval 2</option>");
                out.println("                                                       <option value=\"APPRO1\">Payment Approval 1</option>");
                out.println("                                                       <option value=\"RE-APP\">Payment Requisition</option>");
                out.println("                                                           </select>");
                out.println("                                                       </td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
				
				
				
					
                
                out.println("                                           </td>");
                out.println("                                       </tr>");
                
                out.println("                                       <tr class='pdn_txtpos' valign='top'>");
                out.println("                                           <td>");
                out.println("                                               <br />");
                out.println("                                               <div id=\"DIV_PAYMENTS\"></div>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
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
                
                process_status_get 	= httpServletRequest.getParameter("process_status");
				String m_payee   	= httpServletRequest.getParameter("payee");		//added by Prabash on 17-02-2012
				String m_pamt_no   	= httpServletRequest.getParameter("pamt_no");	//added by Prabash on 17-02-2012
                
                if (process_status_get.equals("PRINT")) {
                    process_status_post = "APPRO2";
                }
                else if (process_status_get.equals("APPRO2")) {
                    process_status_post = "APPRO1";
                }
                else if (process_status_get.equals("APPRO1")) {
                    process_status_post = "RE-APP";
                }
                else if (process_status_get.equals("RE-APP")) {
                    process_status_post = "REQ";
                }
                
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
					" AND   UPPER( B.GROUP_PAYMENT_NO) LIKE UPPER('%"+m_pamt_no+"%') "+ //added by Prabash on 17-02-2012
					" AND   UPPER( B.PAYEE_NAME) LIKE UPPER('%"+m_payee+"%') "+ //added by Prabash on 17-02-2012
                    "   GROUP BY B.GROUP_PAYMENT_NO, " +
                    "            B.PAYEE_NAME, " +
                    "            B.SETTLE_MODE " +
                    " ";
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
		//		out.println(sql);
           
                i = 0;
                
                out.println("<table class=\"table\" align=\"left\" border=\"0\">");
                
                while (resultSet.next()) {
                    
                    i++;
                    
                    if (i == 1) {
                        out.println("<tr class=\"pdn_txtpos2\">");
                        out.println("<td width=\"2%\" align=\"center\">No.</td>");
                        out.println("<td width=\"10%\" align=\"center\">Payment No.</td>");
                        out.println("<td width=\"9%\" align=\"center\">Receiver</td>");
                        out.println("<td width=\"9%\" align=\"center\">Amount</td>"); 
                        out.println("<td width=\"4%\" align=\"center\">Type</td>"); 
                        out.println("<td width=\"3%\" align=\"center\">User</td>");
                        //out.println("<td width=\"4%\" align=\"center\">Approved</td>"); // commented by udara on 18-02-2012
						out.println("<td width=\"4%\" align=\"center\">Approved <input type=\"checkbox\" name=\"CHECK_ALL\" id=\"CHECK_ALL\" value=\"\" onclick=\"check_all_chkbox();\" /> </td>"); // added by udara on 18-02-2012
                        out.println("<td width=\"4%\" align=\"center\">Details</td>");
                        out.println("</tr>");
                    }
                    
                    if (i % 2 == 1) {
                        out.println("<tr class=tr_input>");
                    }
                    else {
                        out.println("<tr class=tr_input1>");
                    }
                    
                    out.println("<td style=\"text-align: right;\">" + i + ".</td>");
                    out.println("<td style=\"text-align: center;\"><div>" + resultSet.getString("GROUP_PAYMENT_NO") + "</div><input type=\"hidden\" name=\"HID_GROUP_PAYMENT_NO_" + i + "\" id=\"HID_GROUP_PAYMENT_NO_" + i + "\" value=\"" + resultSet.getString("GROUP_PAYMENT_NO") + "\" /></td>");
                    out.println("<td style=\"text-align: left;\">" + resultSet.getString("PAYEE_NAME") + "</td>");
                    out.println("<td style=\"text-align: right;\">" + numberFormat.format(resultSet.getBigDecimal("PAY_AMOUNT")) + "</td>");
                    out.println("<td style=\"text-align: center;\">" + resultSet.getString("SETTLE_MODE") + "</td>");
                    out.println("<td style=\"text-align: center;\">" + m_username + "</td>");
                    out.println("<td style=\"text-align: center;\"><input type=\"checkbox\" name=\"CHK_APPROVE_"+i+"\" id=\"CHK_APPROVE_"+i+"\" value=\"A\" onclick=\"check_approve('" + i + "');\" /></td>");
                    out.println("<td style=\"text-align: center;\"><input class=\"but_input\" type=\"button\" name=\"BUT_DETAILS_"+i+"\" id=\"BUT_DETAILS_"+i+"\" value=\"Details\" onclick=\"show_details('" + i + "');\" /></td>");
                    
                }
                resultSet.close();
                statement.close();
                
                if (i == 0) {
                    out.println("   <tr>");
                    out.println("       <td style=\"font-weight: bold;\">No payments exist in this level.</td>");
                    out.println("   </tr>");
                }
                
                out.println("</table>");
                out.println("<input type=\"hidden\" name=\"hid_count\" id=\"hid_count\" value=\"" + i + "\" />");
                out.println("<input type=\"hidden\" name=\"hid_process_status_get\" id=\"hid_process_status_get\" value=\"" + process_status_get + "\" />");
                out.println("<input type=\"hidden\" name=\"hid_process_status\" id=\"hid_process_status\" value=\"" + process_status_post + "\" />");
                
            }
            
            else if (m_chksql.equals("load_data_details")) {
                
                group_payment_no = httpServletRequest.getParameter("group_payment_no");
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                sql = " " +
                    "   SELECT A.PAYMENT_NO, " +
                    "          NVL(" + m_schema_name + ".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO), '-') APPLICATION_NO, " +
                    "          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, " +
                    "          A.EFF_VALDATE, " +
                    "          NVL(A.TAX_INV_NO, '-') TAX_INV_NO, " +
                    "          " + m_schema_name + ".AF_CO_GET_ALL_ENGINE_NO(A.FINANCE_NO) ENGINE_NO, " +
                    "          A.PAY_AMOUNT " +
                    "   FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A " +
                    "   WHERE  A.GROUP_PAYMENT_NO = '" + group_payment_no + "' " +
                    " ";
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                
                i = 0;
                
                out.println("<div class=\"div_input\" style=\"font-weight: bold; text-decoration: underline;\">Payment Details</div>");
                out.println("<br />");
                out.println("<table class=\"table\" align=\"left\" border=\"0\" style=\"backroud: color: yellow;\">");
                
                while (resultSet.next()) {
                    
                    i++;
                    
                    if (i == 1) {
                        out.println("<tr class=\"pdn_txtpos2\">");
                        out.println("<td width=\"2%\" style=\"text-align: center;\">No.</td>");
                        out.println("<td width=\"10%\" style=\"text-align: center;\">Payment No.</td>");
                        out.println("<td width=\"10%\" style=\"text-align: center;\">Agreement No.</td>");
                        out.println("<td width=\"10%\" style=\"text-align: center;\">Client Name</td>");
                        out.println("<td width=\"10%\" style=\"text-align: center;\">Value Date</td>");
                        out.println("<td width=\"10%\" style=\"text-align: center;\">Tax Invoice No.</td>");
                        out.println("<td width=\"10%\" style=\"text-align: center;\">Engine No.</td>");
                        out.println("<td width=\"10%\" style=\"text-align: center;\">Amount</td>");
                        out.println("</tr>");
                    }
                    
                    if (i % 2 == 1) {
                        out.println("<tr class=tr_input>");
                    }
                    else {
                        out.println("<tr class=tr_input1>");
                    }
                    
                    out.println("<td style=\"text-align: right;\">" + i + ".</td>");
                    out.println("<td style=\"text-align: center;\">" + resultSet.getString("PAYMENT_NO") + "</td>");
                    out.println("<td style=\"text-align: center;\">" + resultSet.getString("APPLICATION_NO") + "</td>");
                    out.println("<td style=\"text-align: left;\">" + resultSet.getString("CLIENT_NAME") + "</td>");
                    out.println("<td style=\"text-align: center;\">" + dateFormat.format(resultSet.getDate("EFF_VALDATE")) + "</td>");
                    out.println("<td style=\"text-align: left;\">" + resultSet.getString("TAX_INV_NO") + "</td>");
                    out.println("<td style=\"text-align: left;\">" + resultSet.getString("ENGINE_NO") + "</td>");
                    out.println("<td style=\"text-align: right;\">" + numberFormat.format(resultSet.getBigDecimal("PAY_AMOUNT")) + "</td>");
                    
                }
                resultSet.close();
                statement.close();
                
                out.println("</table>");
                
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
